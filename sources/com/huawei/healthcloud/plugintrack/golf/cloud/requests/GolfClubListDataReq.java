package com.huawei.healthcloud.plugintrack.golf.cloud.requests;

import com.google.gson.annotations.SerializedName;
import com.huawei.haf.application.BaseApplication;
import com.huawei.networkclient.IRequest;
import health.compact.a.GRSManager;

/* loaded from: classes4.dex */
public class GolfClubListDataReq implements IRequest {

    @SerializedName("language")
    private String mLanguage;

    private GolfClubListDataReq(Builder builder) {
        this.mLanguage = builder.mLanguage;
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return GRSManager.a(BaseApplication.e()).getUrl("achievementUrl") + "/achievement/golfclub/getGolfClubList";
    }

    public static final class Builder {
        private String mLanguage;

        public Builder language(String str) {
            this.mLanguage = str;
            return this;
        }

        public GolfClubListDataReq build() {
            return new GolfClubListDataReq(this);
        }
    }

    public String getLanguage() {
        return this.mLanguage;
    }
}
