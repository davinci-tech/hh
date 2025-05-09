package com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.h5pro;

import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.healthcloud.PackageInfoResponse;

/* loaded from: classes5.dex */
public class ProgressResp {
    private int resultCode;
    private Data resultData;
    private String resultDesc;

    public int getResultCode() {
        return this.resultCode;
    }

    public void setResultCode(int i) {
        this.resultCode = i;
    }

    public Data getResultData() {
        return this.resultData;
    }

    public void setResultData(Data data) {
        this.resultData = data;
    }

    public String getResultDesc() {
        return this.resultDesc;
    }

    public void setResultDesc(String str) {
        this.resultDesc = str;
    }

    public static class Data {
        PackageInfoResponse.PackageInfo packageInfo;
        private int progress;
        private int state;
        private String watchFaceId;

        public int getState() {
            return this.state;
        }

        public void setState(int i) {
            this.state = i;
        }

        public String getWatchFaceId() {
            return this.watchFaceId;
        }

        public void setWatchFaceId(String str) {
            this.watchFaceId = str;
        }

        public int getProgress() {
            return this.progress;
        }

        public void setProgress(int i) {
            this.progress = i;
        }

        public PackageInfoResponse.PackageInfo getPackageInfo() {
            return this.packageInfo;
        }

        public void setPackageInfo(PackageInfoResponse.PackageInfo packageInfo) {
            this.packageInfo = packageInfo;
        }
    }
}
