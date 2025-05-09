package com.huawei.hidatamanager;

import android.content.Context;
import android.provider.Settings;
import android.text.TextUtils;
import com.huawei.openalliance.ad.constant.Constants;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes4.dex */
public class SwitchManager {
    private static final String HEALTH_SWITCH = "pengine_health";
    private static final String HIBOARD_SWITCH = "pengine_hiboard";
    private static final String HISUGGESTION_SWITCH = "pengine_hisuggestion";
    private static final String HIVOICE_SWITCH = "pengine_hivoice";
    private static final String SMART_LEARNING_ENABLED = "smartlearning_enabled";
    private static final int SWITCH_STATUS_ON = 1;
    private static final String TAG = "SwitchManager";
    private static Map<String, String> sPackageSwitchMap;

    static {
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        sPackageSwitchMap = concurrentHashMap;
        concurrentHashMap.put(Constants.HW_INTELLIEGNT_PACKAGE, HIBOARD_SWITCH);
        sPackageSwitchMap.put("com.huawei.suggestion", HISUGGESTION_SWITCH);
        sPackageSwitchMap.put("com.huawei.vassistant", HIVOICE_SWITCH);
        sPackageSwitchMap.put("com.huawei.health", HEALTH_SWITCH);
        sPackageSwitchMap.put("com.huawei.testhidatamanager", HEALTH_SWITCH);
    }

    public static boolean getModuleSwitchStatus(Context context) {
        String packageName = context.getPackageName();
        if (TextUtils.isEmpty(packageName)) {
            return false;
        }
        int i = Settings.Global.getInt(context.getContentResolver(), "smartlearning_enabled", 0) == 1 ? 1 : 0;
        if (i == 0) {
            return false;
        }
        String str = sPackageSwitchMap.get(packageName);
        return !TextUtils.isEmpty(str) && Settings.Global.getInt(context.getContentResolver(), str, i) == 1;
    }
}
