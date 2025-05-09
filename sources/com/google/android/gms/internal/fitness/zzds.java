package com.google.android.gms.internal.fitness;

import android.util.Log;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.fitness.result.DataReadResult;

/* loaded from: classes8.dex */
final class zzds extends zzbi {
    private final BaseImplementation.ResultHolder<DataReadResult> zzev;
    private int zzfp;
    private DataReadResult zzfq;

    private zzds(BaseImplementation.ResultHolder<DataReadResult> resultHolder) {
        this.zzfp = 0;
        this.zzfq = null;
        this.zzev = resultHolder;
    }

    @Override // com.google.android.gms.internal.fitness.zzbh
    public final void zza(DataReadResult dataReadResult) {
        synchronized (this) {
            if (Log.isLoggable("Fitness", 2)) {
                int i = this.zzfp;
                StringBuilder sb = new StringBuilder(33);
                sb.append("Received batch result ");
                sb.append(i);
                Log.v("Fitness", sb.toString());
            }
            DataReadResult dataReadResult2 = this.zzfq;
            if (dataReadResult2 == null) {
                this.zzfq = dataReadResult;
            } else {
                dataReadResult2.zzb(dataReadResult);
            }
            int i2 = this.zzfp + 1;
            this.zzfp = i2;
            if (i2 == this.zzfq.zzaa()) {
                this.zzev.setResult(this.zzfq);
            }
        }
    }

    /* synthetic */ zzds(BaseImplementation.ResultHolder resultHolder, zzdk zzdkVar) {
        this(resultHolder);
    }
}
