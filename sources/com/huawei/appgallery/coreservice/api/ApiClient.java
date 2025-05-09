package com.huawei.appgallery.coreservice.api;

import android.content.Context;
import android.text.TextUtils;
import defpackage.aeo;
import defpackage.afr;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes2.dex */
public interface ApiClient {

    public interface ConnectionCallback {
        void onConnected();

        void onConnectionFailed(IConnectionResult iConnectionResult);

        void onConnectionSuspended(int i);
    }

    void connect();

    void disconnect();

    Context getContext();

    ApiClient getDelegate();

    boolean isConnected();

    boolean isConnecting();

    public static class Builder {

        /* renamed from: a, reason: collision with root package name */
        private final Set<ConnectionCallback> f1865a = new HashSet();
        private String b;
        private final Context c;
        private String d;
        private ConnectConfig e;

        public Builder setVendorConnectInfo(ConnectConfig connectConfig) {
            if (connectConfig != null && !TextUtils.isEmpty(connectConfig.getConnectServiceAction()) && !TextUtils.isEmpty(connectConfig.getConnectAppPkg()) && !TextUtils.isEmpty(connectConfig.getInstallAppName()) && !TextUtils.isEmpty(connectConfig.getAppSignCertchain()) && !TextUtils.isEmpty(connectConfig.getAppFingerprintSignature())) {
                this.e = connectConfig.m127clone();
            }
            return this;
        }

        public Builder setHomeCountry(String str) {
            this.b = str;
            return this;
        }

        @Deprecated
        public Builder setGrsAppName(String str) {
            this.d = str;
            return this;
        }

        public ApiClient build() {
            if (!TextUtils.isEmpty(this.d)) {
                afr.e().e(this.c, this.d);
            }
            if (!TextUtils.isEmpty(this.b)) {
                afr.e().d(this.c, this.b);
            }
            return new aeo(this.c, this.f1865a, this.e);
        }

        public Builder addConnectionCallbacks(ConnectionCallback connectionCallback) {
            this.f1865a.add(connectionCallback);
            return this;
        }

        public Builder(Context context) {
            this.c = context.getApplicationContext();
        }
    }
}
