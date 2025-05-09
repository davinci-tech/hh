package com.huawei.healthcloud.plugintrack.golf.cloud.requests;

import com.amap.api.services.district.DistrictSearchQuery;
import com.google.gson.annotations.SerializedName;
import com.huawei.haf.application.BaseApplication;
import com.huawei.networkclient.IRequest;
import health.compact.a.GRSManager;

/* loaded from: classes4.dex */
public class GetGolfCourseDataReq implements IRequest {

    @SerializedName("categoryId")
    private String categoryId;

    @SerializedName(DistrictSearchQuery.KEYWORDS_CITY)
    private String city;

    @SerializedName("devConStatus")
    private int devConStatus;

    @SerializedName("from")
    private Integer from;

    @SerializedName("language")
    private String language;

    @SerializedName("lat")
    private Double lat;

    @SerializedName("lon")
    private Double lon;

    @SerializedName("size")
    private Integer size;

    private GetGolfCourseDataReq(Builder builder) {
        this.lon = builder.lon;
        this.lat = builder.lat;
        this.city = builder.city;
        this.language = builder.language;
        this.from = builder.from;
        this.size = builder.size;
        this.categoryId = "golfcourse";
        this.devConStatus = builder.devConStatus;
    }

    public static final class Builder {
        private String city;
        private int devConStatus;
        private Integer from;
        private String language;
        private Double lat;
        private Double lon;
        private Integer size;

        public Builder lon(Double d) {
            this.lon = d;
            return this;
        }

        public Builder lat(Double d) {
            this.lat = d;
            return this;
        }

        public Builder city(String str) {
            this.city = str;
            return this;
        }

        public Builder language(String str) {
            this.language = str;
            return this;
        }

        public Builder from(Integer num) {
            this.from = num;
            return this;
        }

        public Builder size(Integer num) {
            this.size = num;
            return this;
        }

        public Builder devConType(int i) {
            this.devConStatus = i;
            return this;
        }

        public GetGolfCourseDataReq build() {
            return new GetGolfCourseDataReq(this);
        }
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return GRSManager.a(BaseApplication.e()).getUrl("healthOperationUrl") + "/operationgeneral/app/v1/golfcourse/getCourses";
    }

    public String getLanguage() {
        return this.language;
    }
}
