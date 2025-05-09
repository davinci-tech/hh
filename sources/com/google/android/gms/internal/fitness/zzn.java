package com.google.android.gms.internal.fitness;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.GmsClient;
import com.google.android.gms.common.util.DeviceProperties;
import java.util.Set;

/* loaded from: classes2.dex */
public abstract class zzn<T extends IInterface> extends GmsClient<T> {
    protected zzn(Context context, Looper looper, int i, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener, ClientSettings clientSettings) {
        super(context, looper, i, clientSettings, connectionCallbacks, onConnectionFailedListener);
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public abstract T createServiceInterface(IBinder iBinder);

    @Override // com.google.android.gms.common.internal.GmsClient, com.google.android.gms.common.internal.BaseGmsClient, com.google.android.gms.common.api.Api.Client
    public int getMinApkVersion() {
        return GooglePlayServicesUtilLight.GOOGLE_PLAY_SERVICES_VERSION_CODE;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public abstract String getServiceDescriptor();

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public abstract String getStartServiceAction();

    @Override // com.google.android.gms.common.internal.BaseGmsClient, com.google.android.gms.common.api.Api.Client
    public boolean requiresAccount() {
        return true;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient, com.google.android.gms.common.api.Api.Client
    public boolean requiresSignIn() {
        return !DeviceProperties.isWearable(getContext());
    }

    @Override // com.google.android.gms.common.internal.GmsClient
    public Set<Scope> validateScopes(Set<Scope> set) {
        return com.google.android.gms.fitness.zzg.zza(set);
    }
}
