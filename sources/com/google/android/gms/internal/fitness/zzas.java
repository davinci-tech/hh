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
public final class zzas extends zzn<zzcd> {
    public static final Api<Api.ApiOptions.NoOptions> API;
    private static final Api.ClientKey<zzas> CLIENT_KEY;
    public static final Api<FitnessOptions> zzew;

    private zzas(Context context, Looper looper, ClientSettings clientSettings, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, 55, connectionCallbacks, onConnectionFailedListener, clientSettings);
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
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.fitness.internal.IGoogleFitSensorsApi");
        if (queryLocalInterface instanceof zzcd) {
            return (zzcd) queryLocalInterface;
        }
        return new zzce(iBinder);
    }

    static {
        Api.ClientKey<zzas> clientKey = new Api.ClientKey<>();
        CLIENT_KEY = clientKey;
        zzat zzatVar = null;
        API = new Api<>("Fitness.SENSORS_API", new zzau(), clientKey);
        zzew = new Api<>("Fitness.SENSORS_CLIENT", new zzaw(), clientKey);
    }

    @Override // com.google.android.gms.internal.fitness.zzn, com.google.android.gms.common.internal.BaseGmsClient
    public final String getStartServiceAction() {
        return "com.google.android.gms.fitness.SensorsApi";
    }

    @Override // com.google.android.gms.internal.fitness.zzn, com.google.android.gms.common.internal.BaseGmsClient
    public final String getServiceDescriptor() {
        return "com.google.android.gms.fitness.internal.IGoogleFitSensorsApi";
    }
}
