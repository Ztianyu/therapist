package com.zty.therapist.inter;

import com.zty.therapist.model.MemberModel;

/**
 * Created by zty on 2017/1/19.
 */

public interface OnAdminHandleListener {

    void onSetClass(MemberModel model, int position);

    void onSetGroup(MemberModel model, int position);
}
