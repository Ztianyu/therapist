package com.zty.therapist.utils;

import com.zty.therapist.model.AbilityOptionModel;
import com.zty.therapist.model.OptionModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zty on 2017/1/13.
 */

public class OptionUtils {

    public static final int TYPE_WORK = 0;
    public static final int TYPE_ORG = 1;
    public static final int TYPE_COMMUNICATION = 2;
    public static final int TYPE_TEMPER = 3;
    public static final int TYPE_FIGURE = 4;

    public static List<String> workList = new ArrayList<>();
    public static List<String> orgList = new ArrayList<>();
    public static List<String> communicationList = new ArrayList<>();
    public static List<String> temperList = new ArrayList<>();
    public static List<String> figureList = new ArrayList<>();

    public static List<Integer> workListCode = new ArrayList<>();
    public static List<Integer> orgListCode = new ArrayList<>();
    public static List<Integer> communicationListCode = new ArrayList<>();
    public static List<Integer> temperListCode = new ArrayList<>();
    public static List<Integer> figureListCode = new ArrayList<>();

    public static int workLastCode = 0;
    public static int orgLastCode = 0;
    public static int communicationLastCode = 0;
    public static int temperLastCode = 0;
    public static int figureLastCode = 0;

    public static void setList(AbilityOptionModel abilityOptionModel) {
        workList.clear();
        workListCode.clear();
        for (OptionModel optionModel : abilityOptionModel.getWorkList()) {
            workList.add(optionModel.getValue());
            workListCode.add(optionModel.getKey());
        }
        orgList.clear();
        orgListCode.clear();
        for (OptionModel optionModel : abilityOptionModel.getOrgList()) {
            orgList.add(optionModel.getValue());
            orgListCode.add(optionModel.getKey());
        }
        communicationList.clear();
        communicationListCode.clear();
        for (OptionModel optionModel : abilityOptionModel.getCommunicationList()) {
            communicationList.add(optionModel.getValue());
            communicationListCode.add(optionModel.getKey());
        }
        temperList.clear();
        temperListCode.clear();
        for (OptionModel optionModel : abilityOptionModel.getTemperList()) {
            temperList.add(optionModel.getValue());
            temperListCode.add(optionModel.getKey());
        }
        figureList.clear();
        figureListCode.clear();
        for (OptionModel optionModel : abilityOptionModel.getFigureList()) {
            figureList.add(optionModel.getValue());
            figureListCode.add(optionModel.getKey());
        }
    }

    public static String getValue(int type, int code) {
        String strValue = "";
        List<String> list = new ArrayList<>();
        List<Integer> listCode = new ArrayList<>();
        switch (type) {
            case TYPE_WORK:
                list = workList;
                listCode = workListCode;
                break;
            case TYPE_ORG:
                list = orgList;
                listCode = orgListCode;
                break;
            case TYPE_COMMUNICATION:
                list = communicationList;
                listCode = communicationListCode;
                break;
            case TYPE_TEMPER:
                list = temperList;
                listCode = temperListCode;
                break;
            case TYPE_FIGURE:
                list = figureList;
                listCode = figureListCode;
                break;
        }

        for (int i = 0; i < listCode.size(); i++) {
            if (code == listCode.get(i))
                strValue = list.get(i);
        }

        return strValue;
    }
}
