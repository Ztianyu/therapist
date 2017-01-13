package com.zty.therapist.model;

import java.io.Serializable;

/**
 * Created by zty on 2016/12/3.
 */

public class OptionModel implements Serializable {
    /**
     * key : 2
     * value : 神经类
     */

    private int key;
    private String value;

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


}
