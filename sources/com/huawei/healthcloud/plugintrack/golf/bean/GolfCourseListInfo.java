package com.huawei.healthcloud.plugintrack.golf.bean;

import java.util.List;

/* loaded from: classes4.dex */
public class GolfCourseListInfo {
    private List<GolfCourseInfo> mListInfos;
    private int mRspState;

    public int getRspState() {
        return this.mRspState;
    }

    public void setRspState(int i) {
        this.mRspState = i;
    }

    public List<GolfCourseInfo> getListInfos() {
        return this.mListInfos;
    }

    public void setListInfos(List<GolfCourseInfo> list) {
        this.mListInfos = list;
    }
}
