package com.huawei.watchface;

import com.huawei.watchface.flavor.FlavorConfig;
import java.util.HashMap;

/* loaded from: classes7.dex */
public class c {

    /* renamed from: a, reason: collision with root package name */
    private static final HashMap<String, String> f10942a;

    static {
        HashMap<String, String> hashMap = new HashMap<>();
        f10942a = hashMap;
        hashMap.put("watchFace", FlavorConfig.SERVICE_WATCH_FACE);
        hashMap.put("watchFaceH5", FlavorConfig.SERVICE_WATCH_FACE);
        hashMap.put("hianalyticsV3", FlavorConfig.SERVICE_WATCH_FACE);
        hashMap.put("campaign", FlavorConfig.SERVICE_WATCH_FACE);
        hashMap.put("privacy", FlavorConfig.SERVICE_WATCH_FACE);
    }

    public static String a(String str) {
        return f10942a.get(str);
    }
}
