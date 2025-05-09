package com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.h5pro;

import com.google.gson.annotations.SerializedName;
import com.huawei.openalliance.ad.db.bean.ContentResource;
import com.huawei.pluginachievement.manager.model.MedalConstants;
import java.util.List;

/* loaded from: classes9.dex */
public class WatchFaceColorFilter {

    @SerializedName("type")
    private int type;

    @SerializedName("watchFaceBgArr")
    private List<WatchFaceBackgroundBean> watchFaceBgArr;

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }

    public List<WatchFaceBackgroundBean> getWatchFaceBgArr() {
        return this.watchFaceBgArr;
    }

    public void setWatchFaceBgArr(List<WatchFaceBackgroundBean> list) {
        this.watchFaceBgArr = list;
    }

    public static class WatchFaceBackgroundBean {

        @SerializedName("colorStr")
        private String colorStr;

        @SerializedName(ContentResource.FILE_NAME)
        private String fileName;

        @SerializedName("filePath")
        private String filePath;

        @SerializedName(MedalConstants.EVENT_KEY)
        private int key;

        @SerializedName("resultPath")
        private String resultPath;

        @SerializedName("sourceUrl")
        private String sourceUrl;

        public int getKey() {
            return this.key;
        }

        public void setKey(int i) {
            this.key = i;
        }

        public String getFileName() {
            return this.fileName;
        }

        public void setFileName(String str) {
            this.fileName = str;
        }

        public String getColorStr() {
            return this.colorStr;
        }

        public void setColorStr(String str) {
            this.colorStr = str;
        }

        public String getSourceUrl() {
            return this.sourceUrl;
        }

        public void setSourceUrl(String str) {
            this.sourceUrl = str;
        }

        public String getFilePath() {
            return this.filePath;
        }

        public void setFilePath(String str) {
            this.filePath = str;
        }

        public String getResultPath() {
            return this.resultPath;
        }

        public void setResultPath(String str) {
            this.resultPath = str;
        }
    }
}
