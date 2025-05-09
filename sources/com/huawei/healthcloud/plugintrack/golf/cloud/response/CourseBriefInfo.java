package com.huawei.healthcloud.plugintrack.golf.cloud.response;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes8.dex */
public class CourseBriefInfo {

    @SerializedName("courseId")
    private Long mCourseId;

    @SerializedName("name")
    private String mName;

    public Long getCourseId() {
        return this.mCourseId;
    }

    public void setCourseId(Long l) {
        this.mCourseId = l;
    }

    public String getName() {
        return this.mName;
    }

    public void setName(String str) {
        this.mName = str;
    }
}
