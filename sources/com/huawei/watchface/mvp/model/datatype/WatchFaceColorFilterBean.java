package com.huawei.watchface.mvp.model.datatype;

import java.util.List;

/* loaded from: classes9.dex */
public class WatchFaceColorFilterBean {
    private int type;
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

    public class WatchFaceBackgroundBean {
        private String base64;
        private String colorStr;
        private String fileName;
        private String filePath;
        private int key;
        private String resultPath;
        private String sourceUrl;

        public WatchFaceBackgroundBean() {
        }

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

        public String getFilePath() {
            return this.filePath;
        }

        public void setFilePath(String str) {
            this.filePath = str;
        }

        public String getBase64() {
            return this.base64;
        }

        public void setBase64(String str) {
            this.base64 = str;
        }

        public String getColorStr() {
            return this.colorStr;
        }

        public void setColorStr(String str) {
            this.colorStr = str;
        }

        public String getResultPath() {
            return this.resultPath;
        }

        public void setResultPath(String str) {
            this.resultPath = str;
        }

        public String getSourceUrl() {
            return this.sourceUrl;
        }

        public void setSourceUrl(String str) {
            this.sourceUrl = str;
        }
    }
}
