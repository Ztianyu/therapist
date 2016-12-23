package com.zty.therapist.inter;

import com.zty.therapist.model.SendHelpModel;
import com.zty.therapist.ui.fragment.home.SendHelpFragment;

/**
 * Created by zty on 2016/12/20.
 */

public interface onSendHelp {

    void onSend(SendHelpFragment fragment, SendHelpModel model);
}
