package com.huawei.agconnect.apms;

import android.os.Build;
import com.huawei.agconnect.apms.log.AgentLog;
import com.huawei.agconnect.apms.log.AgentLogManager;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes2.dex */
public class d0 {
    public static final AgentLog abc = AgentLogManager.getAgentLog();
    public static final Map<Integer, String> bcd = new abc();

    public class abc extends HashMap<Integer, String> {
        public abc() {
            put(1, "2G");
            put(2, "2G");
            put(4, "2G");
            put(7, "2G");
            put(11, "2G");
            put(3, "3G");
            put(8, "3G");
            put(9, "3G");
            put(10, "3G");
            put(15, "3G");
            put(5, "3G");
            put(6, "3G");
            put(12, "3G");
            put(14, "3G");
            put(13, "4G");
            if (Build.VERSION.SDK_INT >= 29) {
                put(20, "5G");
            }
        }
    }
}
