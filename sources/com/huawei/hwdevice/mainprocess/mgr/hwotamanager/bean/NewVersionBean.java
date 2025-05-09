package com.huawei.hwdevice.mainprocess.mgr.hwotamanager.bean;

/* loaded from: classes9.dex */
public class NewVersionBean {
    private String hotaDisplayVersion = "";
    private String hotaChangeLogContent = "";
    private long hotaByteSize = 0;
    private String patchDisplayVersion = "";
    private String patchChangeLogContent = "";
    private long patchByteSize = 0;

    public String getHotaDisplayVersion() {
        return this.hotaDisplayVersion;
    }

    public void setHotaDisplayVersion(String str) {
        this.hotaDisplayVersion = str;
    }

    public String getHotaChangeLogContent() {
        return this.hotaChangeLogContent;
    }

    public void setHotaChangeLogContent(String str) {
        this.hotaChangeLogContent = str;
    }

    public long getHotaByteSize() {
        return this.hotaByteSize;
    }

    public void setHotaByteSize(long j) {
        this.hotaByteSize = j;
    }

    public String getPatchDisplayVersion() {
        return this.patchDisplayVersion;
    }

    public void setPatchDisplayVersion(String str) {
        this.patchDisplayVersion = str;
    }

    public String getPatchChangeLogContent() {
        return this.patchChangeLogContent;
    }

    public void setPatchChangeLogContent(String str) {
        this.patchChangeLogContent = str;
    }

    public long getPatchByteSize() {
        return this.patchByteSize;
    }

    public void setPatchByteSize(long j) {
        this.patchByteSize = j;
    }

    public String toString() {
        return "NewVersionBean{hotaDisplayVersion='" + this.hotaDisplayVersion + "', hotaChangeLogContent='" + this.hotaChangeLogContent + "', hotaByteSize=" + this.hotaByteSize + ", patchDisplayVersion='" + this.patchDisplayVersion + "', patchChangeLogContent='" + this.patchChangeLogContent + "', patchByteSize=" + this.patchByteSize + '}';
    }
}
