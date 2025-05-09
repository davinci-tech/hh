package com.google.android.gms.internal.fitness;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.tasks.TaskCompletionSource;

/* loaded from: classes2.dex */
public final class zzen extends zzcr {
    private final BaseImplementation.ResultHolder<Status> zzev;

    public zzen(BaseImplementation.ResultHolder<Status> resultHolder) {
        this.zzev = resultHolder;
    }

    @Override // com.google.android.gms.internal.fitness.zzcq
    public final void onResult(Status status) {
        this.zzev.setResult(status);
    }

    public static zzen zza(TaskCompletionSource<Void> taskCompletionSource) {
        return new zzen(new zzeo(taskCompletionSource));
    }

    public static zzen zzb(TaskCompletionSource<Boolean> taskCompletionSource) {
        return new zzen(new zzep(taskCompletionSource));
    }
}
