package com.huawei.healthcloud.plugintrack.golf.cloud.beans;

import com.google.gson.annotations.SerializedName;
import com.huawei.health.sport.model.WorkoutRecord;
import com.huawei.watchface.videoedit.presenter.PresenterUtils;
import java.util.List;

/* loaded from: classes4.dex */
public class CourseDetail {

    @SerializedName("address")
    private String address;

    @SerializedName("branchinfos")
    private List<BranchInfo> branchInfos;

    @SerializedName("courseId")
    private Long courseId;

    @SerializedName(WorkoutRecord.Extend.FIT_EXTEND_COURSE_TYPE)
    private String courseType;

    @SerializedName("description")
    private String description;

    @SerializedName("designer")
    private String designer;

    @SerializedName("fairwayType")
    private String fairwayType;

    @SerializedName("greenType")
    private String greenType;

    @SerializedName("name")
    private String name;

    @SerializedName(PresenterUtils.PHOTO)
    private List<String> photo;

    @SerializedName("version")
    private String version;

    public Long getCourseId() {
        return this.courseId;
    }

    public String getName() {
        return this.name;
    }

    public List<BranchInfo> getBranchInfos() {
        return this.branchInfos;
    }

    public String getDesigner() {
        return this.designer;
    }

    public String getCourseType() {
        return this.courseType;
    }

    public String getGreenType() {
        return this.greenType;
    }

    public String getFairwayType() {
        return this.fairwayType;
    }

    public String getDescription() {
        return this.description;
    }

    public List<String> getPhoto() {
        return this.photo;
    }

    public String getAddress() {
        return this.address;
    }

    public String getVersion() {
        return this.version;
    }

    public String toString() {
        return "CourseDetail{courseId=" + this.courseId + ", name='" + this.name + "', branchInfos=" + this.branchInfos + ", designer='" + this.designer + "', courseType='" + this.courseType + "', greenType='" + this.greenType + "', fairwayType='" + this.fairwayType + "', description='" + this.description + "', photo=" + this.photo + ", address='" + this.address + "', version='" + this.version + "'}";
    }
}
