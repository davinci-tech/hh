package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingApi;
import com.google.android.gms.location.GeofencingRequest;
import java.util.List;

/* loaded from: classes8.dex */
public final class zzaf implements GeofencingApi {
    @Override // com.google.android.gms.location.GeofencingApi
    public final PendingResult<Status> removeGeofences(GoogleApiClient googleApiClient, List<String> list) {
        return zza(googleApiClient, com.google.android.gms.location.zzal.zza(list));
    }

    @Override // com.google.android.gms.location.GeofencingApi
    public final PendingResult<Status> removeGeofences(GoogleApiClient googleApiClient, PendingIntent pendingIntent) {
        return zza(googleApiClient, com.google.android.gms.location.zzal.zza(pendingIntent));
    }

    @Override // com.google.android.gms.location.GeofencingApi
    @Deprecated
    public final PendingResult<Status> addGeofences(GoogleApiClient googleApiClient, List<Geofence> list, PendingIntent pendingIntent) {
        GeofencingRequest.Builder builder = new GeofencingRequest.Builder();
        builder.addGeofences(list);
        builder.setInitialTrigger(5);
        return addGeofences(googleApiClient, builder.build(), pendingIntent);
    }

    @Override // com.google.android.gms.location.GeofencingApi
    public final PendingResult<Status> addGeofences(GoogleApiClient googleApiClient, GeofencingRequest geofencingRequest, PendingIntent pendingIntent) {
        return googleApiClient.execute(new zzag(this, googleApiClient, geofencingRequest, pendingIntent));
    }

    private final PendingResult<Status> zza(GoogleApiClient googleApiClient, com.google.android.gms.location.zzal zzalVar) {
        return googleApiClient.execute(new zzah(this, googleApiClient, zzalVar));
    }
}
