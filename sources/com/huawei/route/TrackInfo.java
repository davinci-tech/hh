package com.huawei.route;

import java.util.List;

/* loaded from: classes6.dex */
public class TrackInfo {
    private List<HeartInfo> mHeartInfo;
    private LbsInfo mLbsInfo;

    public LbsInfo getLbsInfo() {
        return this.mLbsInfo;
    }

    public void setLbsInfo(LbsInfo lbsInfo) {
        this.mLbsInfo = lbsInfo;
    }

    public List<HeartInfo> getHeartInfoList() {
        return this.mHeartInfo;
    }

    public void setHeartInfo(List<HeartInfo> list) {
        this.mHeartInfo = list;
    }
}
