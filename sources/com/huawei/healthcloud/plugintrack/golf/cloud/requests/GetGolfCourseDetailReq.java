package com.huawei.healthcloud.plugintrack.golf.cloud.requests;

import com.google.gson.annotations.SerializedName;
import com.huawei.haf.application.BaseApplication;
import com.huawei.networkclient.IRequest;
import health.compact.a.GRSManager;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class GetGolfCourseDetailReq implements IRequest {

    @SerializedName("courseIds")
    private List<Long> courseIds;

    @SerializedName("isAnon")
    private boolean isAnon;

    @SerializedName("language")
    private String language;

    private GetGolfCourseDetailReq(Builder builder) {
        this.courseIds = new ArrayList(builder.courseIds);
        this.language = builder.language;
        this.isAnon = builder.isAnon;
    }

    public static final class Builder {
        private List<Long> courseIds = new ArrayList();
        private boolean isAnon;
        private String language;

        public Builder addCourseId(Long l) {
            this.courseIds.add(l);
            return this;
        }

        public Builder language(String str) {
            this.language = str;
            return this;
        }

        public Builder isAnon(boolean z) {
            this.isAnon = z;
            return this;
        }

        public GetGolfCourseDetailReq build() {
            return new GetGolfCourseDetailReq(this);
        }
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        if (this.isAnon) {
            return GRSManager.a(BaseApplication.e()).getUrl("healthOperationUrlCn") + "/operationgeneral/app/v1/golfcourse/getCourseDetailsAnon";
        }
        return GRSManager.a(BaseApplication.e()).getUrl("healthOperationUrl") + "/operationgeneral/app/v1/golfcourse/getCourseDetails";
    }

    public String getLanguage() {
        return this.language;
    }

    public boolean isAnon() {
        return this.isAnon;
    }
}
