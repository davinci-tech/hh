package com.huawei.hianalytics.visual;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes4.dex */
public class m0 {

    /* renamed from: a, reason: collision with root package name */
    public static final Map<String, ExecutorService> f3938a = new HashMap();

    public static ExecutorService a() {
        ExecutorService newSingleThreadExecutor;
        Map<String, ExecutorService> map = f3938a;
        if (map.containsKey("HA-VISUAL")) {
            return map.get("HA-VISUAL");
        }
        synchronized (map) {
            if (map.containsKey("HA-VISUAL")) {
                newSingleThreadExecutor = map.get("HA-VISUAL");
            } else {
                newSingleThreadExecutor = Executors.newSingleThreadExecutor(new l0("HA-VISUAL"));
                map.put("HA-VISUAL", newSingleThreadExecutor);
            }
        }
        return newSingleThreadExecutor;
    }
}
