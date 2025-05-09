package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.wearable.NodeApi;
import java.util.ArrayList;

/* loaded from: classes2.dex */
final class zzgu extends zzgm<NodeApi.GetConnectedNodesResult> {
    public zzgu(BaseImplementation.ResultHolder<NodeApi.GetConnectedNodesResult> resultHolder) {
        super(resultHolder);
    }

    @Override // com.google.android.gms.wearable.internal.zza, com.google.android.gms.wearable.internal.zzek
    public final void zza(zzea zzeaVar) {
        ArrayList arrayList = new ArrayList();
        if (zzeaVar.zzdx != null) {
            arrayList.addAll(zzeaVar.zzdx);
        }
        zza((zzgu) new zzfj(zzgd.zzb(zzeaVar.statusCode), arrayList));
    }
}
