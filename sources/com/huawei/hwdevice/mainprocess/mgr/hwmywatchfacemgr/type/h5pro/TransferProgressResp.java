package com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.h5pro;

import com.huawei.gson.annotations.SerializedName;

/* loaded from: classes9.dex */
public class TransferProgressResp {

    @SerializedName("currentNum")
    private int currentNum;

    @SerializedName("currentProgress")
    private int currentProgress;

    @SerializedName("resultCode")
    private int resultCode;

    @SerializedName("totalNum")
    private int totalNum;

    public int getResultCode() {
        return this.resultCode;
    }

    public void setResultCode(int i) {
        this.resultCode = i;
    }

    public int getCurrentNum() {
        return this.currentNum;
    }

    public void setCurrentNum(int i) {
        this.currentNum = i;
    }

    public int getCurrentProgress() {
        return this.currentProgress;
    }

    public void setCurrentProgress(int i) {
        this.currentProgress = i;
    }

    public int getTotalNum() {
        return this.totalNum;
    }

    public void setTotalNum(int i) {
        this.totalNum = i;
    }
}
