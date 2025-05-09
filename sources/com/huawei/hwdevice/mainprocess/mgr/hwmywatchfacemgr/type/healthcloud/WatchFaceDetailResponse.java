package com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.healthcloud;

import java.util.List;

/* loaded from: classes.dex */
public class WatchFaceDetailResponse extends Response {
    private WatchFaceDetail watchFace;

    public WatchFaceDetail getWatchFace() {
        return this.watchFace;
    }

    public void setWatchFace(WatchFaceDetail watchFaceDetail) {
        this.watchFace = watchFaceDetail;
    }

    /* loaded from: classes9.dex */
    public static class WatchResourceStyle {
        private String coverPicture;
        private Boolean isDefault;
        private String styleNo;
        private String styleValue;

        public String getStyleNo() {
            return this.styleNo;
        }

        public void setStyleNo(String str) {
            this.styleNo = str;
        }

        public String getCoverPicture() {
            return this.coverPicture;
        }

        public void setCoverPicture(String str) {
            this.coverPicture = str;
        }

        public String getStyleValue() {
            return this.styleValue;
        }

        public void setStyleValue(String str) {
            this.styleValue = str;
        }

        public Boolean getDefault() {
            return this.isDefault;
        }

        public void setDefault(Boolean bool) {
            this.isDefault = bool;
        }

        public String toString() {
            return "WatchResourceStyle{styleNo='" + this.styleNo + "', coverPicture='" + this.coverPicture + "', styleValue='" + this.styleValue + "', isDefault=" + this.isDefault + '}';
        }
    }

    /* loaded from: classes9.dex */
    public static class WatchFaceDetail {
        private String aodPicture;
        private String aodPictureBase64;
        private String author;
        private String briefInfo;
        private List<String> compatibleWatchFaceIds;
        private String coverPicture;
        private String coverPictureBase64;
        private String latestVersion;
        private String mainWatchFaceId;
        private String name;
        private int packageSize;
        private int powerLevel;
        private List<WatchResourceStyle> styles;
        private String version;
        private String watchFaceId;

        public String getMainWatchFaceId() {
            return this.mainWatchFaceId;
        }

        public void setMainWatchFaceId(String str) {
            this.mainWatchFaceId = str;
        }

        public List<String> getCompatibleWatchFaceIds() {
            return this.compatibleWatchFaceIds;
        }

        public void setCompatibleWatchFaceIds(List<String> list) {
            this.compatibleWatchFaceIds = list;
        }

        public String getWatchFaceId() {
            return this.watchFaceId;
        }

        public void setWatchFaceId(String str) {
            this.watchFaceId = str;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String str) {
            this.name = str;
        }

        public String getAuthor() {
            return this.author;
        }

        public void setAuthor(String str) {
            this.author = str;
        }

        public String getVersion() {
            return this.version;
        }

        public void setVersion(String str) {
            this.version = str;
        }

        public int getPackageSize() {
            return this.packageSize;
        }

        public void setPackageSize(int i) {
            this.packageSize = i;
        }

        public int getPowerLevel() {
            return this.powerLevel;
        }

        public void setPowerLevel(int i) {
            this.powerLevel = i;
        }

        public String getBriefInfo() {
            return this.briefInfo;
        }

        public void setBriefInfo(String str) {
            this.briefInfo = str;
        }

        public String getCoverPicture() {
            return this.coverPicture;
        }

        public void setCoverPicture(String str) {
            this.coverPicture = str;
        }

        public String getAodPicture() {
            return this.aodPicture;
        }

        public void setAodPicture(String str) {
            this.aodPicture = str;
        }

        public String getCoverPictureBase64() {
            return this.coverPictureBase64;
        }

        public void setCoverPictureBase64(String str) {
            this.coverPictureBase64 = str;
        }

        public String getAodPictureBase64() {
            return this.aodPictureBase64;
        }

        public void setAodPictureBase64(String str) {
            this.aodPictureBase64 = str;
        }

        public String getLatestVersion() {
            return this.latestVersion;
        }

        public void setLatestVersion(String str) {
            this.latestVersion = str;
        }

        public List<WatchResourceStyle> getStyles() {
            return this.styles;
        }

        public void setStyles(List<WatchResourceStyle> list) {
            this.styles = list;
        }
    }
}
