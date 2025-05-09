package com.huawei.healthcloud.plugintrack.golf.cloud.requests;

import com.google.gson.annotations.SerializedName;
import com.huawei.haf.application.BaseApplication;
import com.huawei.networkclient.IRequest;
import health.compact.a.GRSManager;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes8.dex */
public class GolfCourseBriefInfosReq implements IRequest {

    @SerializedName("courseIds")
    List<Long> mCourseIds;

    @SerializedName("language")
    String mLanguage;

    private GolfCourseBriefInfosReq(Builder builder) {
        this.mCourseIds = new ArrayList(builder.mCourseIds);
        this.mLanguage = builder.mLanguage;
    }

    public static final class Builder {
        private List<Long> mCourseIds = new ArrayList();
        private String mLanguage;

        public Builder addCourseId(Long l) {
            this.mCourseIds.add(l);
            return this;
        }

        public Builder language(String str) {
            this.mLanguage = str;
            return this;
        }

        public GolfCourseBriefInfosReq build() {
            return new GolfCourseBriefInfosReq(this);
        }
    }

    public String getLanguage() {
        return this.mLanguage;
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return GRSManager.a(BaseApplication.e()).getUrl("healthOperationUrl") + "/operationgeneral/app/v1/golfcourse/getCourseBriefInfos";
    }
}
