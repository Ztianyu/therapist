package com.zty.therapist.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.zty.therapist.R;
import com.zty.therapist.base.TherapistApplication;
import com.zty.therapist.inter.OnHandleListener;
import com.zty.therapist.model.DoctorOrderModel;
import com.zty.therapist.recycler.FooterRefreshAdapter;
import com.zty.therapist.recycler.ViewHolder;
import com.zty.therapist.utils.ResourceUtil;

/**
 * Created by zty on 2017/1/18.
 */

public class DoctorHandleAdapter extends FooterRefreshAdapter<DoctorOrderModel> {

    private int role;

    private OnHandleListener listener;

    public DoctorHandleAdapter(Context context, OnHandleListener listener) {
        super(context);
        this.listener = listener;
        role = TherapistApplication.getInstance().getRole();
    }

    @Override
    protected void convert(final RecyclerView.ViewHolder holder, final DoctorOrderModel doctorOrderModel) {
        final ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.setText(R.id.textHandleApplyName, "申请人：" + doctorOrderModel.getTeacherNm());
        viewHolder.setText(R.id.textHandleApplyPhone, doctorOrderModel.getMobile());
        viewHolder.setText(R.id.textHandlePro, "医疗项目：" + doctorOrderModel.getItem());
        viewHolder.setText(R.id.textHandleHospital, "医院名称：" + doctorOrderModel.getHospital());
        viewHolder.setText(R.id.textHandleDoctorName, "专家医生：" + doctorOrderModel.getDoctorNm());
        viewHolder.setText(R.id.textHandleAddress, "患者地址：" + doctorOrderModel.getAddress());
        viewHolder.setText(R.id.textHandleTime, "期望手术时间：" + doctorOrderModel.getStartDate());
        viewHolder.setText(R.id.textHandlePatientName, "患者姓名：" + doctorOrderModel.getPatientNm());
        viewHolder.setText(R.id.textHandlePatientPhone, doctorOrderModel.getContactsTel());

        switch (doctorOrderModel.getState()) {
            case 0:
                viewHolder.setText(R.id.textHandleState, "待处理");
                ((TextView) viewHolder.getView(R.id.textHandleState)).setTextColor(ResourceUtil.resToColor(mContext, R.color.colorAccent));
                if (role == 3) {
                    if (!TextUtils.isEmpty(doctorOrderModel.getTransactor())) {
                        viewHolder.getView(R.id.textHandleName).setVisibility(View.VISIBLE);
                        viewHolder.getView(R.id.btnHandle).setVisibility(View.INVISIBLE);
                        viewHolder.setText(R.id.textHandleName, "处理人：" + doctorOrderModel.getTransactorNm());
                    } else {
                        viewHolder.getView(R.id.textHandleName).setVisibility(View.INVISIBLE);
                        viewHolder.getView(R.id.btnHandle).setVisibility(View.VISIBLE);
                        viewHolder.setText(R.id.btnHandle, "分配班长");
                    }
                } else {
                    viewHolder.getView(R.id.textHandleName).setVisibility(View.INVISIBLE);
                    viewHolder.getView(R.id.btnHandle).setVisibility(View.VISIBLE);
                    viewHolder.setText(R.id.btnHandle, "处 理");
                }
                break;
            case 1:
                viewHolder.setText(R.id.textHandleState, "待确认");
                ((TextView) viewHolder.getView(R.id.textHandleState)).setTextColor(ResourceUtil.resToColor(mContext, R.color.canSend));
                if (role == 3) {
                    viewHolder.getView(R.id.textHandleName).setVisibility(View.VISIBLE);
                    viewHolder.getView(R.id.btnHandle).setVisibility(View.INVISIBLE);
                    viewHolder.setText(R.id.textHandleName, "处理人：" + doctorOrderModel.getTransactorNm());
                } else {
                    viewHolder.getView(R.id.textHandleName).setVisibility(View.INVISIBLE);
                    viewHolder.getView(R.id.btnHandle).setVisibility(View.VISIBLE);
                    viewHolder.setText(R.id.btnHandle, "确 认");
                }
                break;
            case 2:
                viewHolder.setText(R.id.textHandleState, "已完成");
                ((TextView) viewHolder.getView(R.id.textHandleState)).setTextColor(ResourceUtil.resToColor(mContext, R.color.gray));
                if (role == 3) {
                    viewHolder.getView(R.id.textHandleName).setVisibility(View.VISIBLE);
                    viewHolder.getView(R.id.btnHandle).setVisibility(View.INVISIBLE);
                    viewHolder.setText(R.id.textHandleName, "处理人：" + doctorOrderModel.getTransactorNm());
                } else {
                    viewHolder.getView(R.id.textHandleName).setVisibility(View.INVISIBLE);
                    viewHolder.getView(R.id.btnHandle).setVisibility(View.INVISIBLE);
                }
                break;
            case 3:
                viewHolder.setText(R.id.textHandleState, "已取消");
                ((TextView) viewHolder.getView(R.id.textHandleState)).setTextColor(ResourceUtil.resToColor(mContext, R.color.gray));
                if (role == 3) {
                    viewHolder.getView(R.id.textHandleName).setVisibility(View.VISIBLE);
                    viewHolder.getView(R.id.btnHandle).setVisibility(View.INVISIBLE);
                    viewHolder.setText(R.id.textHandleName, "处理人：" + doctorOrderModel.getTransactorNm());
                } else {
                    viewHolder.getView(R.id.textHandleName).setVisibility(View.INVISIBLE);
                    viewHolder.getView(R.id.btnHandle).setVisibility(View.INVISIBLE);
                }
                break;
        }

        viewHolder.setOnClick(R.id.btnHandle, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (doctorOrderModel.getState()) {
                    case 0:
                        if (role == 3) {
                            if (TextUtils.isEmpty(doctorOrderModel.getTransactor())) {
                                listener.onSetTransactor(doctorOrderModel.getId(), viewHolder.getLayoutPosition());
                            }
                        } else {
                            listener.onHandleConfirm(doctorOrderModel.getId(), viewHolder.getLayoutPosition());
                        }
                        break;
                    case 1:
                        listener.onHandleSure(doctorOrderModel.getId(), viewHolder.getLayoutPosition());
                        break;
                }
            }
        });
    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.item_doctor_handle;
    }
}
