package com.google.android.gms.internal.fitness;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.ClientSettings;

/* loaded from: classes2.dex */
final class zzai extends Api.AbstractClientBuilder<zzag, Api.ApiOptions.NoOptions> {
    private zzai() {
    }

    @Override // com.google.android.gms.common.api.Api.AbstractClientBuilder
    public final /* synthetic */ zzag buildClient(Context context, Looper looper, ClientSettings clientSettings, Api.ApiOptions.NoOptions noOptions, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        return new zzag(context, looper, clientSettings, connectionCallbacks, onConnectionFailedListener);
    }
}
