package com.huawei.watchface.mvp.model.filedownload;

/* loaded from: classes7.dex */
public class PullTask {
    private String destPath;
    private String digest;
    private String fileUrlJson;
    private String filedID;
    private String httpUrl;
    private int installationType;
    private boolean isOneClick;
    private boolean isZip;
    private PullListenerInterface listener;
    private int option;
    private String param;
    private int status;
    private int totalSize;
    private String uuid;

    public int getInstallationType() {
        return this.installationType;
    }

    public void setInstallationType(int i) {
        this.installationType = i;
    }

    public String fetchDestPath() {
        return this.destPath;
    }

    public void configDestPath(String str) {
        this.destPath = str;
    }

    public String fetchDigest() {
        return this.digest;
    }

    public void configDigest(String str) {
        this.digest = str;
    }

    public void configOption(int i) {
        this.option = i;
    }

    public int fetchOption() {
        return this.option;
    }

    public String fetchHttpUrl() {
        return this.httpUrl;
    }

    public void configHttpUrl(String str) {
        this.httpUrl = str;
    }

    public void configTotalSize(int i) {
        this.totalSize = i;
    }

    public int fetchTotalSize() {
        return this.totalSize;
    }

    public void configListener(PullListenerInterface pullListenerInterface) {
        this.listener = pullListenerInterface;
    }

    public void configParam(String str) {
        this.param = str;
    }

    public String fetchParam() {
        return this.param;
    }

    protected void informResult(PullResult pullResult) {
        PullListenerInterface pullListenerInterface = this.listener;
        if (pullListenerInterface != null) {
            pullListenerInterface.onPullingChange(this, pullResult);
        }
    }

    public String fetchUUID() {
        return this.uuid;
    }

    public void configUUID(String str) {
        this.uuid = str;
    }

    public String fetchFiledID() {
        return this.filedID;
    }

    public void configFiledID(String str) {
        this.filedID = str;
    }

    public String fetchFileUrlJson() {
        return this.fileUrlJson;
    }

    public void configFileUrlJson(String str) {
        this.fileUrlJson = str;
    }

    public int fetchStatus() {
        return this.status;
    }

    public void configStatus(int i) {
        this.status = i;
    }

    public boolean fetchZip() {
        return this.isZip;
    }

    public boolean fetchOneClick() {
        return this.isOneClick;
    }

    public void configZip(boolean z) {
        this.isZip = z;
    }

    public void configOneCLick(boolean z) {
        this.isOneClick = z;
    }
}
