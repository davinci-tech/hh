package com.huawei.watchface.mvp.model.datatype;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes7.dex */
public class WatchFaceInfo {
    private String description;
    private String digest;

    @SerializedName("dirName")
    private String directoryName;
    private String fileName;
    private int fileSize;
    private String fileType;
    private String form;
    private int installationType;
    private String modifyDate;
    private String permissions;
    private String prompt;
    private String version;
    private String watchFaceName;
    private String watchFaceUuid;

    public int getInstallationType() {
        return this.installationType;
    }

    public void setInstallationType(int i) {
        this.installationType = i;
    }

    public String fetchWatchFaceName() {
        return this.watchFaceName;
    }

    public void configWatchFaceName(String str) {
        this.watchFaceName = str;
    }

    public String fetchWatchFaceUuid() {
        return this.watchFaceUuid;
    }

    public void configWatchFaceUuid(String str) {
        this.watchFaceUuid = str;
    }

    public String fetchVersion() {
        return this.version;
    }

    public void configVersion(String str) {
        this.version = str;
    }

    public String fetchModifyDate() {
        return this.modifyDate;
    }

    public void configModifyDate(String str) {
        this.modifyDate = str;
    }

    public String fetchDirectoryName() {
        return this.directoryName;
    }

    public void configDirectoryName(String str) {
        this.directoryName = str;
    }

    public int fetchFileSize() {
        return this.fileSize;
    }

    public void configFileSize(int i) {
        this.fileSize = i;
    }

    public String fetchDigest() {
        return this.digest;
    }

    public void configDigest(String str) {
        this.digest = str;
    }

    public String fetchForm() {
        return this.form;
    }

    public void configForm(String str) {
        this.form = str;
    }

    public String fetchDescription() {
        return this.description;
    }

    public void configDescription(String str) {
        this.description = str;
    }

    public String fetchPrompt() {
        return this.prompt;
    }

    public void configPrompt(String str) {
        this.prompt = str;
    }

    public String fetchPermissions() {
        return this.permissions;
    }

    public void configPermissions(String str) {
        this.permissions = str;
    }

    public String fetchFileName() {
        return this.fileName;
    }

    public void configFileName(String str) {
        this.fileName = str;
    }

    public String fetchFileType() {
        return this.fileType;
    }

    public void configFileType(String str) {
        this.fileType = str;
    }
}
