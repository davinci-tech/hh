package com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.healthcloud;

/* loaded from: classes9.dex */
public class PackageInfoResponse extends Response {
    private PackageInfo packageInfo;

    public PackageInfo getPackageInfo() {
        return this.packageInfo;
    }

    public void setPackageInfo(PackageInfo packageInfo) {
        this.packageInfo = packageInfo;
    }

    public static class PackageInfo {
        private String packageSize;
        private String packageUrl;
        private String version;
        private String watchFaceId;

        public String getWatchFaceId() {
            return this.watchFaceId;
        }

        public void setWatchFaceId(String str) {
            this.watchFaceId = str;
        }

        public String getVersion() {
            return this.version;
        }

        public void setVersion(String str) {
            this.version = str;
        }

        public String getPackageSize() {
            return this.packageSize;
        }

        public void setPackageSize(String str) {
            this.packageSize = str;
        }

        public String getPackageUrl() {
            return this.packageUrl;
        }

        public void setPackageUrl(String str) {
            this.packageUrl = str;
        }
    }
}
