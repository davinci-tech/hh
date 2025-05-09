package com.huawei.pluginachievement.manager.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public class NewMedalTabDataBean {
    private Map<String, ArrayList<String>> mFirstTabRelationship;
    private List<MedalInfoDesc> mMedalInfoDesc;
    private Map<String, ArrayList<MedalInfoDesc>> mSecondTabRelationship;
    private Map<String, ArrayList<MedalInfoDesc>> mSecondTabRelationshipForSport;
    private List<String> mTabNewList;

    public List<String> getTabNewList() {
        return this.mTabNewList;
    }

    public void setTabNewList(List<String> list) {
        this.mTabNewList = list;
    }

    public Map<String, ArrayList<String>> getFirstTabRelationship() {
        return this.mFirstTabRelationship;
    }

    public void setFirstTabRelationship(Map<String, ArrayList<String>> map) {
        this.mFirstTabRelationship = map;
    }

    public Map<String, ArrayList<MedalInfoDesc>> getSecondTabRelationship() {
        return this.mSecondTabRelationship;
    }

    public void setSecondTabRelationship(Map<String, ArrayList<MedalInfoDesc>> map) {
        this.mSecondTabRelationship = map;
    }

    public List<MedalInfoDesc> getMedalInfoDesc() {
        return this.mMedalInfoDesc;
    }

    public void setMedalInfoDesc(List<MedalInfoDesc> list) {
        this.mMedalInfoDesc = list;
    }

    public Map<String, ArrayList<MedalInfoDesc>> getSecondTabRelationshipForSport() {
        return this.mSecondTabRelationshipForSport;
    }

    public void setSecondTabRelationshipForSport(Map<String, ArrayList<MedalInfoDesc>> map) {
        this.mSecondTabRelationshipForSport = map;
    }
}
