package com.google.android.gms.common.api;

import com.google.android.gms.common.api.PendingResult;

/* loaded from: classes8.dex */
final class zaa implements PendingResult.StatusListener {
    private final /* synthetic */ Batch zabd;

    zaa(Batch batch) {
        this.zabd = batch;
    }

    @Override // com.google.android.gms.common.api.PendingResult.StatusListener
    public final void onComplete(Status status) {
        Object obj;
        int i;
        boolean z;
        boolean z2;
        PendingResult[] pendingResultArr;
        obj = this.zabd.mLock;
        synchronized (obj) {
            if (this.zabd.isCanceled()) {
                return;
            }
            if (status.isCanceled()) {
                Batch.zaa(this.zabd, true);
            } else if (!status.isSuccess()) {
                Batch.zab(this.zabd, true);
            }
            Batch.zab(this.zabd);
            i = this.zabd.zaaz;
            if (i == 0) {
                z = this.zabd.zabb;
                if (z) {
                    super/*com.google.android.gms.common.api.internal.BasePendingResult*/.cancel();
                } else {
                    z2 = this.zabd.zaba;
                    Status status2 = z2 ? new Status(13) : Status.RESULT_SUCCESS;
                    Batch batch = this.zabd;
                    pendingResultArr = batch.zabc;
                    batch.setResult(new BatchResult(status2, pendingResultArr));
                }
            }
        }
    }
}
