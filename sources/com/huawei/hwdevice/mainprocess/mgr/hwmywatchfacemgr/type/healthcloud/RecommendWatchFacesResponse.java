package com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.healthcloud;

import android.graphics.Bitmap;
import java.util.List;

/* loaded from: classes5.dex */
public class RecommendWatchFacesResponse extends Response {
    private int total;
    private List<RecommendWatchFace> watchFaces;

    public int getTotal() {
        return this.total;
    }

    public void setTotal(int i) {
        this.total = i;
    }

    public List<RecommendWatchFace> getWatchFaces() {
        return this.watchFaces;
    }

    public void setWatchFaces(List<RecommendWatchFace> list) {
        this.watchFaces = list;
    }

    public static class RecommendWatchFace {
        private String iconPicture;
        private Bitmap iconPictureBitmap;
        private int iconPictureStatus;
        private boolean isUpdate;
        private String jumpUrl;
        private String latestVersion;
        private String name;
        private String version;
        private String watchFaceId;

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

        public String getVersion() {
            return this.version;
        }

        public void setVersion(String str) {
            this.version = str;
        }

        public String getIconPicture() {
            return this.iconPicture;
        }

        public void setIconPicture(String str) {
            this.iconPicture = str;
        }

        public Bitmap getIconPictureBitmap() {
            return this.iconPictureBitmap;
        }

        public void setIconPictureBitmap(Bitmap bitmap) {
            this.iconPictureBitmap = bitmap;
        }

        public int getIconPictureStatus() {
            return this.iconPictureStatus;
        }

        public void setIconPictureStatus(int i) {
            this.iconPictureStatus = i;
        }

        public String getLatestVersion() {
            return this.latestVersion;
        }

        public void setLatestVersion(String str) {
            this.latestVersion = str;
        }

        public boolean isUpdate() {
            return this.isUpdate;
        }

        public void setUpdate(boolean z) {
            this.isUpdate = z;
        }

        public String getJumpUrl() {
            return this.jumpUrl;
        }

        public void setJumpUrl(String str) {
            this.jumpUrl = str;
        }
    }
}
