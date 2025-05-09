package com.google.android.gms.internal.fitness;

import android.app.PendingIntent;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.PendingResults;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.SensorsApi;
import com.google.android.gms.fitness.request.DataSourcesRequest;
import com.google.android.gms.fitness.request.OnDataPointListener;
import com.google.android.gms.fitness.request.SensorRequest;
import com.google.android.gms.fitness.result.DataSourcesResult;

/* loaded from: classes2.dex */
public final class zzea implements SensorsApi {
    @Override // com.google.android.gms.fitness.SensorsApi
    public final PendingResult<DataSourcesResult> findDataSources(GoogleApiClient googleApiClient, DataSourcesRequest dataSourcesRequest) {
        return googleApiClient.enqueue(new zzeb(this, googleApiClient, dataSourcesRequest));
    }

    @Override // com.google.android.gms.fitness.SensorsApi
    public final PendingResult<Status> add(GoogleApiClient googleApiClient, SensorRequest sensorRequest, OnDataPointListener onDataPointListener) {
        return zza(googleApiClient, sensorRequest, com.google.android.gms.fitness.request.zzan.zzw().zza(onDataPointListener, googleApiClient.getLooper()), null);
    }

    @Override // com.google.android.gms.fitness.SensorsApi
    public final PendingResult<Status> add(GoogleApiClient googleApiClient, SensorRequest sensorRequest, PendingIntent pendingIntent) {
        return zza(googleApiClient, sensorRequest, null, pendingIntent);
    }

    private final PendingResult<Status> zza(GoogleApiClient googleApiClient, SensorRequest sensorRequest, com.google.android.gms.fitness.data.zzt zztVar, PendingIntent pendingIntent) {
        return googleApiClient.enqueue(new zzec(this, googleApiClient, sensorRequest, zztVar, pendingIntent));
    }

    @Override // com.google.android.gms.fitness.SensorsApi
    public final PendingResult<Status> remove(GoogleApiClient googleApiClient, OnDataPointListener onDataPointListener) {
        com.google.android.gms.fitness.request.zzal zzb = com.google.android.gms.fitness.request.zzan.zzw().zzb(onDataPointListener, googleApiClient.getLooper());
        if (zzb == null) {
            return PendingResults.immediatePendingResult(Status.RESULT_SUCCESS, googleApiClient);
        }
        return zza(googleApiClient, zzb, null);
    }

    @Override // com.google.android.gms.fitness.SensorsApi
    public final PendingResult<Status> remove(GoogleApiClient googleApiClient, PendingIntent pendingIntent) {
        return zza(googleApiClient, null, pendingIntent);
    }

    private final PendingResult<Status> zza(GoogleApiClient googleApiClient, com.google.android.gms.fitness.data.zzt zztVar, PendingIntent pendingIntent) {
        return googleApiClient.execute(new zzed(this, googleApiClient, zztVar, pendingIntent));
    }
}
