package com.huawei.healthcloud.plugintrack.golf.cloud.beans;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes4.dex */
public class CourseMapData {

    @SerializedName("courseId")
    private Long courseId;

    @SerializedName("cryptoAlg")
    private String cryptoAlg;

    @SerializedName("secretKey")
    private String secretKey;

    @SerializedName("size")
    private double size;

    @SerializedName("url")
    private String url;

    @SerializedName("version")
    private String version;

    public Long getCourseId() {
        return this.courseId;
    }

    public String getVersion() {
        return this.version;
    }

    public String getUrl() {
        return this.url;
    }

    public double getSize() {
        return this.size;
    }

    public String getSecretKey() {
        return this.secretKey;
    }

    public String getCryptoAlg() {
        return this.cryptoAlg;
    }

    public String toString() {
        return "CourseMapData{courseId=" + this.courseId + ", version='" + this.version + "', url='" + this.url + "', size=" + this.size + ", secretKey='" + this.secretKey + "', cryptoAlg='" + this.cryptoAlg + "'}";
    }
}
