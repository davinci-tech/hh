package com.google.android.gms.fitness;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.ListenerHolders;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.request.DataSourcesRequest;
import com.google.android.gms.fitness.request.OnDataPointListener;
import com.google.android.gms.fitness.request.SensorRequest;
import com.google.android.gms.internal.fitness.zzas;
import com.google.android.gms.internal.fitness.zzea;
import com.google.android.gms.tasks.Task;
import java.util.List;

/* loaded from: classes8.dex */
public class SensorsClient extends GoogleApi<FitnessOptions> {
    private static final SensorsApi zzx = new zzea();

    SensorsClient(Context context, FitnessOptions fitnessOptions) {
        super(context, zzas.zzew, fitnessOptions, GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    SensorsClient(Activity activity, FitnessOptions fitnessOptions) {
        super(activity, zzas.zzew, fitnessOptions, GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    public Task<List<DataSource>> findDataSources(DataSourcesRequest dataSourcesRequest) {
        return PendingResultUtil.toTask(zzx.findDataSources(asGoogleApiClient(), dataSourcesRequest), zzm.zzf);
    }

    public Task<Void> add(SensorRequest sensorRequest, OnDataPointListener onDataPointListener) {
        ListenerHolder<L> registerListener = registerListener(onDataPointListener, "OnDataPointListener");
        return doRegisterEventListener(new zzn(this, registerListener, registerListener, sensorRequest), new zzo(this, registerListener.getListenerKey(), registerListener));
    }

    public Task<Void> add(SensorRequest sensorRequest, PendingIntent pendingIntent) {
        return PendingResultUtil.toVoidTask(zzx.add(asGoogleApiClient(), sensorRequest, pendingIntent));
    }

    public Task<Boolean> remove(OnDataPointListener onDataPointListener) {
        return doUnregisterEventListener(ListenerHolders.createListenerKey(onDataPointListener, "OnDataPointListener"));
    }

    public Task<Void> remove(PendingIntent pendingIntent) {
        return PendingResultUtil.toVoidTask(zzx.remove(asGoogleApiClient(), pendingIntent));
    }
}
