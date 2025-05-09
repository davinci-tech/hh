package com.huawei.healthcloud.plugintrack.golf.cloud.beans;

import com.google.gson.annotations.SerializedName;
import com.huawei.watchface.videoedit.presenter.PresenterUtils;
import java.util.List;

/* loaded from: classes4.dex */
public class CourseData {

    @SerializedName("courseId")
    private Long courseId;

    @SerializedName("distance")
    private double distance;

    @SerializedName("name")
    private String name;

    @SerializedName(PresenterUtils.PHOTO)
    private List<String> photo;

    @SerializedName("totalLength")
    private int totalLength;

    @SerializedName("version")
    private String version;

    public Long getCourseId() {
        return this.courseId;
    }

    public String getName() {
        return this.name;
    }

    public double getDistance() {
        return this.distance;
    }

    public List<String> getPhoto() {
        return this.photo;
    }

    public int getTotalLength() {
        return this.totalLength;
    }

    public String getVersion() {
        return this.version;
    }

    public String toString() {
        return "CourseData{courseId=" + this.courseId + ", name='" + this.name + "', distance=" + this.distance + ", photo=" + this.photo + ", totalLength=" + this.totalLength + ", version='" + this.version + "'}";
    }
}
