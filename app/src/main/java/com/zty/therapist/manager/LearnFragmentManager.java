package com.zty.therapist.manager;

import com.zty.therapist.ui.fragment.home.LearnFragment;

import java.util.HashMap;

/**
 * Created by zty on 2016/12/3.
 */

public class LearnFragmentManager {

    private static HashMap<Integer, LearnFragment> map = new HashMap<>();

    public static LearnFragment getFragment(int type) {
        LearnFragment fragment = null;

        if (map.get(type) == null) {
            fragment = LearnFragment.newInstance(type);
            map.put(type, fragment);
        }
        return map.get(type);
    }
}
