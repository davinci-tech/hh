package com.google.android.gms.internal.fitness;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.fitness.FitnessOptions;

/* loaded from: classes2.dex */
public final class zzab extends zzn<zzbx> {
    public static final Api<Api.ApiOptions.NoOptions> API;
    private static final Api.ClientKey<zzab> CLIENT_KEY;
    public static final Api<FitnessOptions> zzew;

    private zzab(Context context, Looper looper, ClientSettings clientSettings, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, 125, connectionCallbacks, onConnectionFailedListener, clientSettings);
    }

    @Override // com.google.android.gms.internal.fitness.zzn, com.google.android.gms.common.internal.GmsClient, com.google.android.gms.common.internal.BaseGmsClient, com.google.android.gms.common.api.Api.Client
    public final int getMinApkVersion() {
        return GooglePlayServicesUtilLight.GOOGLE_PLAY_SERVICES_VERSION_CODE;
    }

    @Override // com.google.android.gms.internal.fitness.zzn, com.google.android.gms.common.internal.BaseGmsClient
    public final /* synthetic */ IInterface createServiceInterface(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.fitness.internal.IGoogleFitGoalsApi");
        if (queryLocalInterface instanceof zzbx) {
            return (zzbx) queryLocalInterface;
        }
        return new zzby(iBinder);
    }

    static {
        Api.ClientKey<zzab> clientKey = new Api.ClientKey<>();
        CLIENT_KEY = clientKey;
        zzac zzacVar = null;
        API = new Api<>("Fitness.GOALS_API", new zzad(), clientKey);
        zzew = new Api<>("Fitness.GOALS_CLIENT", new zzaf(), clientKey);
    }

    @Override // com.google.android.gms.internal.fitness.zzn, com.google.android.gms.common.internal.BaseGmsClient
    public final String getStartServiceAction() {
        return "com.google.android.gms.fitness.GoalsApi";
    }

    @Override // com.google.android.gms.internal.fitness.zzn, com.google.android.gms.common.internal.BaseGmsClient
    public final String getServiceDescriptor() {
        return "com.google.android.gms.fitness.internal.IGoogleFitGoalsApi";
    }
}
