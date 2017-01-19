package com.zty.therapist.ui.fragment.administrator;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.RequestParams;
import com.zty.therapist.R;
import com.zty.therapist.adapter.AdminMemberAdapter;
import com.zty.therapist.base.BaseFragment;
import com.zty.therapist.inter.DialogListener;
import com.zty.therapist.inter.OnAdminHandleListener;
import com.zty.therapist.model.MemberModel;
import com.zty.therapist.model.ResultBean;
import com.zty.therapist.recycler.FooterRefreshAdapter;
import com.zty.therapist.url.RequestManager;
import com.zty.therapist.url.Urls;
import com.zty.therapist.utils.DialogUtils;
import com.zty.therapist.utils.ResultUtil;
import com.zty.therapist.utils.ToastUtils;
import com.zty.therapist.utils.ValidateUtil;
import com.zty.therapist.widget.RecyclerViewDivider;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zty on 2017/1/18.
 */

public class GroupFragment extends BaseFragment implements OnAdminHandleListener {

    private static final int CODE_GET_MEMBER_LIST = 0;
    private static final int CODE_SET_GROUP = 1;
    private static final int CODE_SET_CLASS = 2;

    @BindView(R.id.editSearch)
    EditText editSearch;
    @BindView(R.id.imgSearch)
    ImageView imgSearch;
    @BindView(R.id.recyclerViewSearchMember)
    RecyclerView recyclerViewSearchMember;

    private String strSearch = "";

    private AdminMemberAdapter adapter;

    public int mLastVisibleItemPosition;
    public int pageNo = 1;
    public int mTempPageCount = 2;
    public boolean isLoadMore;

    private int position;

    @Override
    public int getContentVew() {
        return R.layout.activity_add_member;
    }

    @Override
    public void initData() {

        editSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                strSearch = editable.toString();
            }
        });

        adapter = new AdminMemberAdapter(context, this);

        recyclerViewSearchMember.addItemDecoration(new RecyclerViewDivider(context, LinearLayoutManager.HORIZONTAL));
        recyclerViewSearchMember.setAdapter(adapter);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewSearchMember.setLayoutManager(layoutManager);

        recyclerViewSearchMember.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    mLastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
                    //防止重复请求
                    if (pageNo == mTempPageCount) {
                        return;
                    }
                    if (mLastVisibleItemPosition > 0 && mLastVisibleItemPosition + 1 == adapter.getItemCount()) {
                        //已到达底部，开始加载更多
                        isLoadMore = true;
                        adapter.updateRefreshState(FooterRefreshAdapter.STATE_START);
                        pageNo = mTempPageCount;
                        fetchData();
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                mLastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
            }
        });

        fetchData();
    }

    private void fetchData() {
        RequestParams params = new RequestParams();
        if (ValidateUtil.isNum(strSearch)) {
            params.put("mobile", strSearch);
        } else {
            params.put("teacherNm", strSearch);
        }
        params.put("pageNo", pageNo);
        RequestManager.get(CODE_GET_MEMBER_LIST, Urls.getAdminMemberList, params, this);
    }

    @OnClick(R.id.imgSearch)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgSearch:
                hideInput();
                pageNo = 1;
                fetchData();
                break;
        }
    }


    private void hideInput() {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
            imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    @Override
    public void onFailureCallback(int requestCode, String errorMsg) {

    }

    @Override
    public void onSuccessCallback(int requestCode, String response) {
        ResultBean resultBean = ResultUtil.getResult(response);
        if (resultBean.isSuccess()) {
            switch (requestCode) {
                case CODE_GET_MEMBER_LIST:
                    List<MemberModel> memberModels = new Gson().fromJson(resultBean.getResult(), new TypeToken<List<MemberModel>>() {
                    }.getType());

                    if (memberModels != null && memberModels.size() > 0) {
                        if (isLoadMore) {
                            if (memberModels.size() == 0) {
                                adapter.updateRefreshState(FooterRefreshAdapter.STATE_FINISH);
                            } else {
                                adapter.notifyBottomRefresh(memberModels);
                                mTempPageCount++;
                            }
                        } else {
                            adapter.notifyTopRefresh(memberModels);
                        }
                    } else {
                        if (pageNo == 1) {
                            adapter.clearData();
                        }
                        adapter.updateRefreshState(FooterRefreshAdapter.STATE_FINISH);
                    }
                    break;
                case CODE_SET_GROUP:
                case CODE_SET_CLASS:
                    ToastUtils.show(context, "设置成功");
                    adapter.remove(position);
                    break;
            }
        } else {
            ToastUtils.show(context, resultBean.getMsg());
        }
    }

    @Override
    public void onSetClass(final MemberModel model, int position) {
        this.position = position;
        DialogUtils.show(context, "是否设为" + model.getTeacherNm() + "为班长？", new DialogListener() {
            @Override
            public void onSure() {
                RequestParams params = new RequestParams();
                params.put("teamUserId", model.getUserId());
                RequestManager.post(CODE_SET_CLASS, Urls.submitMonitor, params, GroupFragment.this);
            }
        });
    }

    @Override
    public void onSetGroup(final MemberModel model, int position) {
        this.position = position;
        DialogUtils.show(context, "是否设为" + model.getTeacherNm() + "为组长？", new DialogListener() {
            @Override
            public void onSure() {
                RequestParams params = new RequestParams();
                params.put("groupUserId", model.getUserId());
                RequestManager.post(CODE_SET_GROUP, Urls.submitGroupLeader, params, GroupFragment.this);
            }
        });
    }
}
