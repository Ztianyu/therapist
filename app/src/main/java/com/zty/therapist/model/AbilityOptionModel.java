package com.zty.therapist.model;

import java.util.List;

/**
 * Created by zty on 2017/1/12.
 */

public class AbilityOptionModel {

    /**
     * communicationList : [{"key":"1","value":"善于交流"},{"key":"2","value":"一般"},{"key":"3","value":"较差"}]
     * figureList : [{"key":"1","value":"端庄"},{"key":"2","value":"稳重"},{"key":"3","value":"和蔼"},{"key":"4","value":"气质佳"},{"key":"5","value":"一般"},{"key":"6","value":"较差"}]
     * msg : 成功
     * orgList : [{"key":"1","value":"善于组织"},{"key":"2","value":"一般"},{"key":"3","value":"较差"}]
     * ret : 0
     * temperList : [{"key":"1","value":"乐观"},{"key":"2","value":"开朗"},{"key":"3","value":"内向"},{"key":"4","value":"外向"},{"key":"5","value":"沉闷"}]
     * workList : [{"key":"1","value":"技能好"},{"key":"2","value":"效率高"},{"key":"3","value":"服务周到"},{"key":"4","value":"认真细心"},{"key":"5","value":"一般"},{"key":"6","value":"较差"}]
     */

    private List<OptionModel> communicationList;
    private List<OptionModel> figureList;
    private List<OptionModel> orgList;
    private List<OptionModel> temperList;
    private List<OptionModel> workList;

    public List<OptionModel> getCommunicationList() {
        return communicationList;
    }

    public void setCommunicationList(List<OptionModel> communicationList) {
        this.communicationList = communicationList;
    }

    public List<OptionModel> getFigureList() {
        return figureList;
    }

    public void setFigureList(List<OptionModel> figureList) {
        this.figureList = figureList;
    }

    public List<OptionModel> getOrgList() {
        return orgList;
    }

    public void setOrgList(List<OptionModel> orgList) {
        this.orgList = orgList;
    }

    public List<OptionModel> getTemperList() {
        return temperList;
    }

    public void setTemperList(List<OptionModel> temperList) {
        this.temperList = temperList;
    }

    public List<OptionModel> getWorkList() {
        return workList;
    }

    public void setWorkList(List<OptionModel> workList) {
        this.workList = workList;
    }
}
