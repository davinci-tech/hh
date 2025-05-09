package com.google.android.gms.internal.fitness;

import com.huawei.openalliance.ad.constant.JsbMapKeyNames;
import com.huawei.pluginachievement.manager.model.MedalConstants;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/* loaded from: classes2.dex */
public final class zzk {
    private static final double zzel = 10.0d / TimeUnit.SECONDS.toNanos(1);
    private static final double zzem = 1000.0d / TimeUnit.SECONDS.toNanos(1);
    private static final double zzen = 2000.0d / TimeUnit.HOURS.toNanos(1);
    private static final double zzeo = 100.0d / TimeUnit.SECONDS.toNanos(1);
    public static final Set<String> zzep = Collections.unmodifiableSet(new HashSet(Arrays.asList("altitude", "duration", "food_item", "meal_type", "repetitions", "resistance", "resistance_type", "debug_session", "google.android.fitness.SessionV2")));
    private static final zzk zzes = new zzk();
    private final Map<String, Map<String, zzm>> zzeq;
    private final Map<String, zzm> zzer;

    private zzk() {
        HashMap hashMap = new HashMap();
        hashMap.put(JsbMapKeyNames.H5_LOC_LAT, new zzm(-90.0d, 90.0d));
        hashMap.put(JsbMapKeyNames.H5_LOC_LON, new zzm(-180.0d, 180.0d));
        hashMap.put("accuracy", new zzm(0.0d, 10000.0d));
        hashMap.put("bpm", new zzm(0.0d, 1000.0d));
        hashMap.put("altitude", new zzm(-100000.0d, 100000.0d));
        hashMap.put("percentage", new zzm(0.0d, 100.0d));
        hashMap.put("confidence", new zzm(0.0d, 100.0d));
        hashMap.put("duration", new zzm(0.0d, 9.223372036854776E18d));
        hashMap.put("height", new zzm(0.0d, 3.0d));
        hashMap.put("weight", new zzm(0.0d, 1000.0d));
        hashMap.put("speed", new zzm(0.0d, 11000.0d));
        this.zzer = Collections.unmodifiableMap(hashMap);
        HashMap hashMap2 = new HashMap();
        hashMap2.put("com.google.step_count.delta", zza(MedalConstants.EVENT_STEPS, new zzm(0.0d, zzel)));
        hashMap2.put("com.google.calories.consumed", zza("calories", new zzm(0.0d, zzem)));
        hashMap2.put("com.google.calories.expended", zza("calories", new zzm(0.0d, zzen)));
        hashMap2.put("com.google.distance.delta", zza("distance", new zzm(0.0d, zzeo)));
        this.zzeq = Collections.unmodifiableMap(hashMap2);
    }

    private static <K, V> Map<K, V> zza(K k, V v) {
        HashMap hashMap = new HashMap();
        hashMap.put(k, v);
        return hashMap;
    }

    final zzm zzk(String str) {
        return this.zzer.get(str);
    }

    final zzm zza(String str, String str2) {
        Map<String, zzm> map = this.zzeq.get(str);
        if (map != null) {
            return map.get(str2);
        }
        return null;
    }

    public static zzk zzs() {
        return zzes;
    }
}
