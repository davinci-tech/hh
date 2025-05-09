package com.huawei.healthcloud.plugintrack.golf.cloud.requests;

import com.google.gson.annotations.SerializedName;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.utils.CollectionUtils;
import com.huawei.networkclient.IRequest;
import health.compact.a.GRSManager;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class GetGolfCourseMapDataReq implements IRequest {

    @SerializedName("courseIds")
    List<Long> courseIds;

    @SerializedName("isAnon")
    boolean isAnon;

    @SerializedName("language")
    String language;

    @SerializedName("deviceLevel")
    private String mMapLevel;

    @SerializedName("publicKey")
    String publicKey;

    @SerializedName("type")
    String type;

    public static class MapLevel {
        public static String ADVANCED_LEVEL = "advanced";
        public static String BASIC_LEVEL = "basic";
    }

    private GetGolfCourseMapDataReq(Builder builder) {
        this.courseIds = new ArrayList(builder.courseIds);
        this.language = builder.language;
        this.publicKey = builder.publicKey;
        this.type = builder.type;
        this.mMapLevel = builder.mMapLevel;
        this.isAnon = builder.isAnon;
    }

    public static final class Builder {
        List<Long> courseIds = new ArrayList();
        boolean isAnon;
        String language;
        private String mMapLevel;
        String publicKey;
        String type;

        public Builder addCourseId(Long l) {
            this.courseIds.add(l);
            return this;
        }

        public Builder addCourseIds(List<Long> list) {
            if (CollectionUtils.d(list)) {
                return this;
            }
            this.courseIds.addAll(list);
            return this;
        }

        public Builder publicKey(String str) {
            this.publicKey = str;
            return this;
        }

        public Builder language(String str) {
            this.language = str;
            return this;
        }

        public Builder type(String str) {
            this.type = str;
            return this;
        }

        public Builder mapLevel(String str) {
            this.mMapLevel = str;
            return this;
        }

        public Builder isAnon(boolean z) {
            this.isAnon = z;
            return this;
        }

        public GetGolfCourseMapDataReq build() {
            return new GetGolfCourseMapDataReq(this);
        }
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        if (this.isAnon) {
            return GRSManager.a(BaseApplication.e()).getUrl("healthOperationUrlCn") + "/operationgeneral/app/v1/golfcourse/getCourseMapDataAnon";
        }
        return GRSManager.a(BaseApplication.e()).getUrl("healthOperationUrl") + "/operationgeneral/app/v1/golfcourse/getCourseMapData";
    }

    public String getLanguage() {
        return this.language;
    }

    public boolean isAnon() {
        return this.isAnon;
    }
}
