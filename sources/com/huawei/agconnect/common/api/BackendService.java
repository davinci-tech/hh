package com.huawei.agconnect.common.api;

import com.huawei.agconnect.AGConnectInstance;
import com.huawei.agconnect.common.api.RequestThrottle;
import com.huawei.agconnect.credential.obs.ad;
import com.huawei.agconnect.https.Adapter;
import com.huawei.hmf.tasks.Task;
import java.util.concurrent.TimeUnit;

/* loaded from: classes2.dex */
public class BackendService {

    public enum AccessTokenType {
        MUST,
        EXPECT
    }

    public static class Options {
        private final boolean accessToken;
        private final boolean accessTokenExtra;
        private boolean accessTokenRefreshed;
        private final AccessTokenType accessTokenType;
        private final boolean apiKey;
        private final AGConnectInstance app;
        private final boolean clientToken;
        private boolean clientTokenRefreshed;
        private final Adapter.Factory factory;
        private final RequestThrottle.Throttle throttle;
        private final TimeUnit timeUnit;
        private final long timeout;

        public void setClientTokenRefreshed(boolean z) {
            this.clientTokenRefreshed = z;
        }

        public void setAccessTokenRefreshed(boolean z) {
            this.accessTokenRefreshed = z;
        }

        public Builder newBuilder() {
            return new Builder(this);
        }

        public boolean isClientTokenRefreshed() {
            return this.clientTokenRefreshed;
        }

        public boolean isClientToken() {
            return this.clientToken;
        }

        public boolean isApiKey() {
            return this.apiKey;
        }

        public boolean isAccessTokenRefreshed() {
            return this.accessTokenRefreshed;
        }

        public boolean isAccessTokenExtra() {
            return this.accessTokenExtra;
        }

        public boolean isAccessToken() {
            return this.accessToken;
        }

        public long getTimeout() {
            return this.timeout;
        }

        public TimeUnit getTimeUnit() {
            return this.timeUnit;
        }

        public RequestThrottle.Throttle getThrottle() {
            return this.throttle;
        }

        public Adapter.Factory getFactory() {
            return this.factory;
        }

        public static class Builder {
            private boolean accessToken;
            private boolean accessTokenExtra;
            private AccessTokenType accessTokenType;
            private boolean apiKey;
            private AGConnectInstance app;
            private boolean clientToken;
            private Adapter.Factory factory;
            private RequestThrottle.Throttle throttle;
            private TimeUnit timeUnit;
            private long timeout;

            public Builder timeout(long j) {
                this.timeout = j;
                return this;
            }

            public Builder timeUnit(TimeUnit timeUnit) {
                this.timeUnit = timeUnit;
                return this;
            }

            public Builder throttle(RequestThrottle.Throttle throttle) {
                this.throttle = throttle;
                return this;
            }

            public Builder factory(Adapter.Factory factory) {
                this.factory = factory;
                return this;
            }

            public Builder clientToken(boolean z) {
                this.clientToken = z;
                return this;
            }

            public Options build() {
                return new Options(this.apiKey, this.clientToken, this.accessToken, this.throttle, this.accessTokenType, this.factory, this.timeout, this.timeUnit, this.app, this.accessTokenExtra);
            }

            public Builder app(AGConnectInstance aGConnectInstance) {
                this.app = aGConnectInstance;
                return this;
            }

            public Builder apiKey(boolean z) {
                this.apiKey = z;
                return this;
            }

            public Builder accessTokenType(AccessTokenType accessTokenType) {
                this.accessTokenType = accessTokenType;
                return this;
            }

            public Builder accessTokenExtra(boolean z) {
                this.accessTokenExtra = z;
                return this;
            }

            public Builder accessToken(boolean z) {
                this.accessToken = z;
                return this;
            }

            public Builder(Options options) {
                this.accessTokenType = AccessTokenType.MUST;
                this.apiKey = options.apiKey;
                this.clientToken = options.clientToken;
                this.accessToken = options.accessToken;
                this.accessTokenExtra = options.accessTokenExtra;
                this.throttle = options.throttle;
                this.accessTokenType = options.accessTokenType;
                this.factory = options.factory;
                this.timeout = options.timeout;
                this.timeUnit = options.timeUnit;
                this.app = options.app;
            }

            public Builder() {
                this.accessTokenType = AccessTokenType.MUST;
            }
        }

        public AGConnectInstance getApp() {
            return this.app;
        }

        public AccessTokenType getAccessTokenType() {
            return this.accessTokenType;
        }

        private Options(boolean z, boolean z2, boolean z3, RequestThrottle.Throttle throttle, AccessTokenType accessTokenType, Adapter.Factory factory, long j, TimeUnit timeUnit, AGConnectInstance aGConnectInstance, boolean z4) {
            this.apiKey = z;
            this.clientToken = z2;
            this.accessToken = z3;
            this.throttle = throttle;
            this.accessTokenType = accessTokenType;
            this.factory = factory;
            this.timeout = j;
            this.timeUnit = timeUnit;
            this.app = aGConnectInstance;
            this.accessTokenExtra = z4;
        }
    }

    public static <Rsp> Task<Rsp> sendRequest(BaseRequest baseRequest, int i, Class<Rsp> cls, Options options) {
        return ad.b(baseRequest, i, cls, options);
    }
}
