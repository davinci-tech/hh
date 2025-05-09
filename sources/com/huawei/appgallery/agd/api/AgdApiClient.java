package com.huawei.appgallery.agd.api;

import android.content.Context;
import com.huawei.appgallery.coreservice.api.ApiClient;
import defpackage.aer;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes2.dex */
public interface AgdApiClient extends ApiClient {

    public interface ConnectionCallbacks {
        void onConnected();

        void onConnectionFailed(ConnectionResult connectionResult);

        void onConnectionSuspended(int i);
    }

    @Override // com.huawei.appgallery.coreservice.api.ApiClient
    void connect();

    @Override // com.huawei.appgallery.coreservice.api.ApiClient
    void disconnect();

    @Override // com.huawei.appgallery.coreservice.api.ApiClient
    Context getContext();

    @Override // com.huawei.appgallery.coreservice.api.ApiClient
    boolean isConnected();

    @Override // com.huawei.appgallery.coreservice.api.ApiClient
    boolean isConnecting();

    public static class Builder {
        private String b;
        private String c;
        private final Set<ConnectionCallbacks> d = new HashSet();
        private final Context e;

        public Builder setHomeCountry(String str) {
            this.b = str;
            return this;
        }

        @Deprecated
        public Builder setGrsAppName(String str) {
            this.c = str;
            return this;
        }

        public AgdApiClient build() {
            return new aer(new ApiClient.Builder(this.e).setHomeCountry(this.b).setGrsAppName(this.c), this.e, this.d);
        }

        public Builder addConnectionCallbacks(ConnectionCallbacks connectionCallbacks) {
            this.d.add(connectionCallbacks);
            return this;
        }

        public Builder(Context context) {
            this.e = context.getApplicationContext();
        }
    }
}
