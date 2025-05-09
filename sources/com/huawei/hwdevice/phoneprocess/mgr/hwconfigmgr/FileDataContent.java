package com.huawei.hwdevice.phoneprocess.mgr.hwconfigmgr;

import java.util.List;

/* loaded from: classes5.dex */
public class FileDataContent {
    private boolean autoDelete;
    protected String configName;
    protected List<FileData> fileData;
    private boolean needNotice;
    private String resourceType;
    private String selectRulekey;
    private String selectRulevalue;
    private String srcPkgName;

    FileDataContent() {
    }

    public boolean isAutoDelete() {
        return this.autoDelete;
    }

    public void setAutoDelete(boolean z) {
        this.autoDelete = z;
    }

    public boolean isNeedNotice() {
        return this.needNotice;
    }

    public void setNeedNotice(boolean z) {
        this.needNotice = z;
    }

    public String getSrcPkgName() {
        return this.srcPkgName;
    }

    public void setSrcPkgName(String str) {
        this.srcPkgName = str;
    }

    public String getSelectRulekey() {
        return this.selectRulekey;
    }

    public void setSelectRulekey(String str) {
        this.selectRulekey = str;
    }

    public String getSelectRulevalue() {
        return this.selectRulevalue;
    }

    public void setSelectRulevalue(String str) {
        this.selectRulevalue = str;
    }

    public String getConfigName() {
        return this.configName;
    }

    public void setConfigName(String str) {
        this.configName = str;
    }

    public List<FileData> getFileData() {
        return this.fileData;
    }

    public void setFileData(List<FileData> list) {
        this.fileData = list;
    }

    public String getResourceType() {
        return this.resourceType;
    }

    public void setResourceType(String str) {
        this.resourceType = str;
    }

    public static class FileData {
        private String fileName;
        private int fileSize;
        private int version;

        public String getFileName() {
            return this.fileName;
        }

        public void setFileName(String str) {
            this.fileName = str;
        }

        public int getVersion() {
            return this.version;
        }

        public void setVersion(int i) {
            this.version = i;
        }

        public int getFileSize() {
            return this.fileSize;
        }

        public void setFileSize(int i) {
            this.fileSize = i;
        }
    }
}
