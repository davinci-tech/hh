package com.google.android.gms.fitness;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.ListenerHolders;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.fitness.data.BleDevice;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.request.BleScanCallback;
import com.google.android.gms.internal.fitness.zzct;
import com.google.android.gms.internal.fitness.zzeq;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import java.util.List;

/* loaded from: classes8.dex */
public class BleClient extends GoogleApi<FitnessOptions> {
    private static final BleApi zze;

    BleClient(Context context, FitnessOptions fitnessOptions) {
        super(context, com.google.android.gms.internal.fitness.zzp.zzew, fitnessOptions, GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    BleClient(Activity activity, FitnessOptions fitnessOptions) {
        super(activity, com.google.android.gms.internal.fitness.zzp.zzew, fitnessOptions, GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    public Task<Void> startBleScan(List<DataType> list, int i, BleScanCallback bleScanCallback) {
        if (!PlatformVersion.isAtLeastJellyBeanMR2()) {
            return Tasks.forException(new ApiException(zzeq.zzgd));
        }
        ListenerHolder<L> registerListener = registerListener(bleScanCallback, "BleScanCallback");
        return doRegisterEventListener(new zzb(this, registerListener, registerListener, list, i), new zzc(this, registerListener.getListenerKey(), registerListener));
    }

    public Task<Boolean> stopBleScan(BleScanCallback bleScanCallback) {
        if (!PlatformVersion.isAtLeastJellyBeanMR2()) {
            return Tasks.forException(new ApiException(zzeq.zzgd));
        }
        return doUnregisterEventListener(ListenerHolders.createListenerKey(bleScanCallback, "BleScanCallback"));
    }

    public Task<Void> claimBleDevice(BleDevice bleDevice) {
        return PendingResultUtil.toVoidTask(zze.claimBleDevice(asGoogleApiClient(), bleDevice));
    }

    public Task<Void> claimBleDevice(String str) {
        return PendingResultUtil.toVoidTask(zze.claimBleDevice(asGoogleApiClient(), str));
    }

    public Task<Void> unclaimBleDevice(String str) {
        return PendingResultUtil.toVoidTask(zze.unclaimBleDevice(asGoogleApiClient(), str));
    }

    public Task<Void> unclaimBleDevice(BleDevice bleDevice) {
        return PendingResultUtil.toVoidTask(zze.unclaimBleDevice(asGoogleApiClient(), bleDevice));
    }

    public Task<List<BleDevice>> listClaimedBleDevices() {
        return PendingResultUtil.toTask(zze.listClaimedBleDevices(asGoogleApiClient()), zza.zzf);
    }

    static {
        BleApi zzeqVar;
        if (PlatformVersion.isAtLeastJellyBeanMR2()) {
            zzeqVar = new zzct();
        } else {
            zzeqVar = new zzeq();
        }
        zze = zzeqVar;
    }
}
