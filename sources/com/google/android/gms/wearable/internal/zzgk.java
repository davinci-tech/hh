package com.google.android.gms.wearable.internal;

import com.google.android.gms.wearable.CapabilityInfo;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes8.dex */
final class zzgk {
    /* JADX INFO: Access modifiers changed from: private */
    public static Map<String, CapabilityInfo> zza(List<zzah> list) {
        HashMap hashMap = new HashMap();
        if (list != null) {
            for (zzah zzahVar : list) {
                hashMap.put(zzahVar.getName(), new zzw(zzahVar));
            }
        }
        return hashMap;
    }
}
