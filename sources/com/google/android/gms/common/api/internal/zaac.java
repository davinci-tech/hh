package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import java.util.Map;

/* loaded from: classes2.dex */
final class zaac implements PendingResult.StatusListener {
    private final /* synthetic */ BasePendingResult zafm;
    private final /* synthetic */ zaab zafn;

    zaac(zaab zaabVar, BasePendingResult basePendingResult) {
        this.zafn = zaabVar;
        this.zafm = basePendingResult;
    }

    @Override // com.google.android.gms.common.api.PendingResult.StatusListener
    public final void onComplete(Status status) {
        Map map;
        map = this.zafn.zafk;
        map.remove(this.zafm);
    }
}
