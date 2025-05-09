package com.huawei.agconnect.abtest;

import android.content.Context;
import com.huawei.agconnect.abtest.a.c;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes2.dex */
public abstract class AGConnectABTesting {
    private static final Map<String, AGConnectABTesting> ABT_INSTANCE = new HashMap();

    public abstract void removeAllExperiments();

    public abstract void replaceAllExperiments(List<Map<String, String>> list);

    public static AGConnectABTesting get(Context context, String str) {
        AGConnectABTesting aGConnectABTesting;
        synchronized (AGConnectABTesting.class) {
            Map<String, AGConnectABTesting> map = ABT_INSTANCE;
            if (!map.containsKey(str)) {
                map.put(str, new c(context, str));
            }
            aGConnectABTesting = map.get(str);
        }
        return aGConnectABTesting;
    }
}
