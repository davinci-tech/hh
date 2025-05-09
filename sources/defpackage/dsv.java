package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.operation.h5pro.jsmodules.complaint.ComplaintConstants;
import java.util.List;

/* loaded from: classes3.dex */
public class dsv {

    @SerializedName("courseList")
    private List<dsu> mCourseInfoList;

    @SerializedName(ComplaintConstants.GROUP_NAME_PARAM_KEY)
    private String mGroupName;

    public String getGroupName() {
        return this.mGroupName;
    }

    public List<dsu> getCourseInfoList() {
        return this.mCourseInfoList;
    }
}
