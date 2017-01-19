package com.zty.therapist.ui.activity.home;

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
import com.zty.therapist.adapter.SearchMemberAdapter;
import com.zty.therapist.base.BaseActivity;
import com.zty.therapist.base.TherapistApplication;
import com.zty.therapist.config.Config;
import com.zty.therapist.inter.DialogListener;
import com.zty.therapist.inter.OnAddToGroupListener;
import com.zty.therapist.model.MemberModel;
import com.zty.therapist.model.ResultBean;
import com.zty.therapist.service.InviteInformUtils;
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
 * Created by zty on 2017/1/3.
 */

public class AddMemberActivity extends BaseActivity implements View.OnClickListener, OnAddToGroupListener, DialogListener {

    private static final int CODE_GET_MEMBER_LIST = 0;
    private static final int CODE_ADD_TO_GROUP = 1;
    private static final int CODE_GET_GROUP_LIST = 2;
    private static final int CODE_ADD_TO_CLASS = 3;

    @BindView(R.id.editSearch)
    EditText editSearch;
    @BindView(R.id.imgSearch)
    ImageView imgSearch;
    @BindView(R.id.recyclerViewSearchMember)
    RecyclerView recyclerViewSearchMember;

    SearchMemberAdapter adapter;

    private String strSearch = "";

    private String operator;
    private int position;

    private int role;

    @Override
    protected int getContentView() {
        return R.layout.activity_add_member;
    }

    @Override
    protected void initData() {
        role = TherapistApplication.getInstance().getRole();

        if (role == 2) {
            title.setText("添加组");
        } else {
            title.setText("添加成员");
        }


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

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewSearchMember.setLayoutManager(layoutManager);
        recyclerViewSearchMember.addItemDecoration(new RecyclerViewDivider(this, LinearLayoutManager.HORIZONTAL));
        adapter = new SearchMemberAdapter(this, this);
        recyclerViewSearchMember.setAdapter(adapter);

        fetchData();
    }

    private void fetchData() {
        if (role == 2) {
            getGroupList();
        } else {
            getMemberList();
        }
    }

    private void getMemberList() {
        RequestParams params = new RequestParams();
        if (ValidateUtil.isNum(strSearch)) {
            params.put("mobile", strSearch);
        } else {
            params.put("teacherNm", strSearch);
        }
        RequestManager.get(CODE_GET_MEMBER_LIST, Urls.getMemberList, params, this);
    }

    private void getGroupList() {
        RequestParams params = new RequestParams();
        if (ValidateUtil.isNum(strSearch)) {
            params.put("mobile", strSearch);
        } else {
            params.put("teacherNm", strSearch);
        }
        RequestManager.get(CODE_GET_GROUP_LIST, Urls.getGroupList, params, this);
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
                case CODE_GET_GROUP_LIST:
                    List<MemberModel> memberModels = new Gson().fromJson(resultBean.getResult(), new TypeToken<List<MemberModel>>() {
                    }.getType());

                    if (memberModels != null)
                        adapter.notifyTopRefresh(memberModels);
                    break;
                case CODE_ADD_TO_GROUP:
                case CODE_ADD_TO_CLASS:
                    ToastUtils.show(this, "邀请添加成功");
                    break;
            }
        } else {
            ToastUtils.show(this, resultBean.getMsg());
        }
    }

    @OnClick(R.id.imgSearch)
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.imgSearch:
                hideInput();
                fetchData();
                break;
        }
    }

    private void hideInput() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
            imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    @Override
    public void onAdd(String operator, int position) {
        this.position = position;
        this.operator = operator;
        if (role == 2) {
            DialogUtils.show(this, "是否添加该组长到本班？", this);
        } else {
            DialogUtils.show(this, "是否添加该成员到本组？", this);
        }

    }

    @Override
    public void onSure() {
        if (role == 2) {
            InviteInformUtils.submitInviteInform(CODE_ADD_TO_CLASS, Config.CODE_SUBMIT_INCITE_INFORM_1, operator, Config.ADD_TO_GROUP, 0, this);
        } else {
            InviteInformUtils.submitInviteInform(CODE_ADD_TO_GROUP, Config.CODE_SUBMIT_INCITE_INFORM_2, operator, Config.ADD_TO_CLASS, 0, this);
        }
    }
}
