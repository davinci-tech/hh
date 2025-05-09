package com.huawei.watchface.mvp.model.datatype;

/* loaded from: classes7.dex */
public class VideoStruct {
    private int videoAttribute;
    private String videoName;
    private String videoPreviewName;
    private int videoStatus;

    public VideoStruct() {
    }

    public VideoStruct(String str, String str2, int i, int i2) {
        this.videoName = str;
        this.videoPreviewName = str2;
        this.videoAttribute = i;
        this.videoStatus = i2;
    }

    public String getVideoName() {
        return this.videoName;
    }

    public void setVideoName(String str) {
        this.videoName = str;
    }

    public int getVideoAttribute() {
        return this.videoAttribute;
    }

    public void setVideoAttribute(int i) {
        this.videoAttribute = i;
    }

    public String getVideoPreviewName() {
        return this.videoPreviewName;
    }

    public void setVideoPreviewName(String str) {
        this.videoPreviewName = str;
    }

    public int getVideoStatus() {
        return this.videoStatus;
    }

    public void setVideoStatus(int i) {
        this.videoStatus = i;
    }

    public String toString() {
        return "VideoStruct{videoName='" + this.videoName + "', videoPreviewName='" + this.videoPreviewName + "', videoAttribute=" + this.videoAttribute + ", videoStatus=" + this.videoStatus + '}';
    }
}
