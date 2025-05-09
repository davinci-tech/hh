package com.huawei.picture.security.account.bean;

import android.app.Application;
import com.huawei.hms.support.api.entity.auth.Scope;
import java.util.List;

/* loaded from: classes6.dex */
public class AccountInitParams {
    private final String accessToken;
    private final String appIds;
    private final Application application;
    private final List<AuthErrorBean> authErrorParamList;
    private final String deviceId;
    private final String grsAppName;
    private final boolean hasHmsCore;
    private final boolean isMcp;
    private final List<Scope> scopes;
    private final String subAppId;

    private AccountInitParams(Builder builder) {
        this.application = builder.application;
        this.scopes = builder.scopes;
        this.appIds = builder.appIds;
        this.authErrorParamList = builder.authErrorParamList;
        this.isMcp = builder.isMcp;
        this.hasHmsCore = builder.hasHmsCore;
        this.deviceId = builder.deviceId;
        this.subAppId = builder.subAppId;
        this.accessToken = builder.accessToken;
        this.grsAppName = builder.grsAppName;
    }

    public Application getApplication() {
        return this.application;
    }

    public List<Scope> getScopes() {
        return this.scopes;
    }

    public String getAppIds() {
        return this.appIds;
    }

    public List<AuthErrorBean> getAuthErrorParamList() {
        return this.authErrorParamList;
    }

    public boolean isMcp() {
        return this.isMcp;
    }

    public boolean hasHmsCore() {
        return this.hasHmsCore;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public String getSubAppId() {
        return this.subAppId;
    }

    public String getAccessToken() {
        return this.accessToken;
    }

    public String getGrsAppName() {
        return this.grsAppName;
    }

    public static class Builder {
        private String accessToken;
        private String appIds;
        private Application application;
        private List<AuthErrorBean> authErrorParamList;
        private String deviceId;
        private String grsAppName;
        private List<Scope> scopes;
        private String subAppId;
        private boolean isMcp = false;
        private boolean hasHmsCore = true;

        public Builder setApplication(Application application) {
            this.application = application;
            return this;
        }

        public Builder setScopes(List<Scope> list) {
            this.scopes = list;
            return this;
        }

        public Builder setAppIds(String str) {
            this.appIds = str;
            return this;
        }

        public Builder setAuthErrorParamList(List<AuthErrorBean> list) {
            this.authErrorParamList = list;
            return this;
        }

        public Builder setMcp(boolean z) {
            this.isMcp = z;
            return this;
        }

        public Builder hasHmsCore(boolean z) {
            this.hasHmsCore = z;
            return this;
        }

        public Builder setDeviceId(String str) {
            this.deviceId = str;
            return this;
        }

        public Builder setSubAppId(String str) {
            this.subAppId = str;
            return this;
        }

        public Builder setAccessToken(String str) {
            this.accessToken = str;
            return this;
        }

        public Builder setGrsAppName(String str) {
            this.grsAppName = str;
            return this;
        }

        public String getGrsAppName() {
            return this.grsAppName;
        }

        public AccountInitParams build() {
            return new AccountInitParams(this);
        }
    }
}
