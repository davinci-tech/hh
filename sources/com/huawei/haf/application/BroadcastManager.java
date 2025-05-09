package com.huawei.haf.application;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.huawei.haf.common.security.SecurityConstant;
import com.huawei.operation.utils.Constants;
import com.huawei.watchface.api.HwWatchFaceApi;
import health.compact.a.LogUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public final class BroadcastManager {
    private static final Map<BroadcastReceiver, List<BroadcastReceiver>> b = new HashMap();
    private static final Map<String, BroadcastReceiver> c = new HashMap();
    private static final Object e = new Object();

    private BroadcastManager() {
    }

    public static void wk_(BroadcastReceiver broadcastReceiver) {
        if (broadcastReceiver == null) {
            return;
        }
        IntentFilter intentFilter = new IntentFilter("android.intent.action.LOCALE_CHANGED");
        LocalBroadcastManager.getInstance(BaseApplication.e()).registerReceiver(broadcastReceiver, intentFilter);
        wr_(broadcastReceiver, intentFilter, null, "android.intent.action.LOCALE_CHANGED");
    }

    public static void wy_(BroadcastReceiver broadcastReceiver) {
        if (wF_(broadcastReceiver, "android.intent.action.LOCALE_CHANGED")) {
            LocalBroadcastManager.getInstance(BaseApplication.e()).unregisterReceiver(broadcastReceiver);
        }
    }

    public static void wt_(BroadcastReceiver broadcastReceiver) {
        if (broadcastReceiver == null) {
            return;
        }
        IntentFilter intentFilter = new IntentFilter("com.huawei.haf.intent.action.UPDATE_LANGUAGE_READY");
        LocalBroadcastManager.getInstance(BaseApplication.e()).registerReceiver(broadcastReceiver, intentFilter);
        ws_(broadcastReceiver, intentFilter, SecurityConstant.b, false, "com.huawei.haf.intent.action.UPDATE_LANGUAGE_READY");
    }

    public static void wm_(BroadcastReceiver broadcastReceiver) {
        wo_(broadcastReceiver);
        wn_(broadcastReceiver);
        wu_(broadcastReceiver);
    }

    public static void wA_(BroadcastReceiver broadcastReceiver) {
        wC_(broadcastReceiver);
        wB_(broadcastReceiver);
        wG_(broadcastReceiver);
    }

    public static void wo_(BroadcastReceiver broadcastReceiver) {
        if (broadcastReceiver == null) {
            return;
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.SCREEN_ON");
        LocalBroadcastManager.getInstance(BaseApplication.e()).registerReceiver(broadcastReceiver, intentFilter);
        wr_(broadcastReceiver, intentFilter, null, "android.intent.action.SCREEN_ON");
    }

    public static void wC_(BroadcastReceiver broadcastReceiver) {
        if (wF_(broadcastReceiver, "android.intent.action.SCREEN_ON")) {
            LocalBroadcastManager.getInstance(BaseApplication.e()).unregisterReceiver(broadcastReceiver);
        }
    }

    public static void wn_(BroadcastReceiver broadcastReceiver) {
        if (broadcastReceiver == null) {
            return;
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.SCREEN_OFF");
        LocalBroadcastManager.getInstance(BaseApplication.e()).registerReceiver(broadcastReceiver, intentFilter);
        wr_(broadcastReceiver, intentFilter, null, "android.intent.action.SCREEN_OFF");
    }

    public static void wB_(BroadcastReceiver broadcastReceiver) {
        if (wF_(broadcastReceiver, "android.intent.action.SCREEN_OFF")) {
            LocalBroadcastManager.getInstance(BaseApplication.e()).unregisterReceiver(broadcastReceiver);
        }
    }

    public static void wu_(BroadcastReceiver broadcastReceiver) {
        if (broadcastReceiver == null) {
            return;
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.USER_PRESENT");
        LocalBroadcastManager.getInstance(BaseApplication.e()).registerReceiver(broadcastReceiver, intentFilter);
        wr_(broadcastReceiver, intentFilter, null, "android.intent.action.USER_PRESENT");
    }

    public static void wG_(BroadcastReceiver broadcastReceiver) {
        if (wF_(broadcastReceiver, "android.intent.action.USER_PRESENT")) {
            LocalBroadcastManager.getInstance(BaseApplication.e()).unregisterReceiver(broadcastReceiver);
        }
    }

    public static void wh_(BroadcastReceiver broadcastReceiver) {
        if (broadcastReceiver == null) {
            return;
        }
        IntentFilter intentFilter = new IntentFilter(Constants.CLICK_STATUS_BAR_ACTION);
        LocalBroadcastManager.getInstance(BaseApplication.e()).registerReceiver(broadcastReceiver, intentFilter);
        ws_(broadcastReceiver, intentFilter, Constants.SYSTEM_UI_PERMISSION, true, Constants.CLICK_STATUS_BAR_ACTION);
    }

    public static void wv_(BroadcastReceiver broadcastReceiver) {
        if (wF_(broadcastReceiver, Constants.CLICK_STATUS_BAR_ACTION)) {
            LocalBroadcastManager.getInstance(BaseApplication.e()).unregisterReceiver(broadcastReceiver);
        }
    }

    public static void wj_(BroadcastReceiver broadcastReceiver) {
        if (broadcastReceiver == null) {
            return;
        }
        IntentFilter intentFilter = new IntentFilter(HwWatchFaceApi.ACTION_FOREGROUND_STATUS);
        LocalBroadcastManager.getInstance(BaseApplication.e()).registerReceiver(broadcastReceiver, intentFilter);
        ws_(broadcastReceiver, intentFilter, SecurityConstant.d, false, HwWatchFaceApi.ACTION_FOREGROUND_STATUS);
    }

    public static void wx_(BroadcastReceiver broadcastReceiver) {
        if (wF_(broadcastReceiver, HwWatchFaceApi.ACTION_FOREGROUND_STATUS)) {
            LocalBroadcastManager.getInstance(BaseApplication.e()).unregisterReceiver(broadcastReceiver);
        }
    }

    public static void wl_(BroadcastReceiver broadcastReceiver) {
        if (broadcastReceiver == null) {
            return;
        }
        LocalBroadcastManager.getInstance(BaseApplication.e()).registerReceiver(broadcastReceiver, new IntentFilter("com.huawei.haf.intent.action.PROCESS_STATUS_CHANGE"));
    }

    public static void wz_(BroadcastReceiver broadcastReceiver) {
        if (broadcastReceiver != null) {
            LocalBroadcastManager.getInstance(BaseApplication.e()).unregisterReceiver(broadcastReceiver);
        }
    }

    public static void wi_(BroadcastReceiver broadcastReceiver) {
        if (broadcastReceiver == null) {
            return;
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.huawei.haf.intent.action.DEBUG_SWITCH_LOG");
        intentFilter.addAction("com.huawei.haf.intent.action.DEBUG_SWITCH_APP");
        LocalBroadcastManager.getInstance(BaseApplication.e()).registerReceiver(broadcastReceiver, intentFilter);
        ws_(broadcastReceiver, intentFilter, "android.permission.SET_DEBUG_APP", true, "com.huawei.haf.intent.action.DEBUG_SWITCH_LOG");
    }

    public static void ww_(BroadcastReceiver broadcastReceiver) {
        if (wF_(broadcastReceiver, "com.huawei.haf.intent.action.DEBUG_SWITCH_LOG")) {
            LocalBroadcastManager.getInstance(BaseApplication.e()).unregisterReceiver(broadcastReceiver);
        }
    }

    private static void wr_(BroadcastReceiver broadcastReceiver, IntentFilter intentFilter, String str, String str2) {
        if (broadcastReceiver == null) {
            return;
        }
        synchronized (e) {
            wq_(broadcastReceiver, intentFilter, str, -1, str2);
        }
    }

    private static void ws_(BroadcastReceiver broadcastReceiver, IntentFilter intentFilter, String str, boolean z, String str2) {
        if (broadcastReceiver == null) {
            return;
        }
        int i = Build.VERSION.SDK_INT >= 33 ? z ? 2 : 4 : -1;
        synchronized (e) {
            wq_(broadcastReceiver, intentFilter, str, i, str2);
        }
    }

    private static void wq_(BroadcastReceiver broadcastReceiver, IntentFilter intentFilter, String str, int i, String str2) {
        Map<String, BroadcastReceiver> map = c;
        BroadcastReceiver broadcastReceiver2 = map.get(str2);
        if (broadcastReceiver2 == null) {
            broadcastReceiver2 = wg_(str2);
        }
        Map<BroadcastReceiver, List<BroadcastReceiver>> map2 = b;
        List<BroadcastReceiver> list = map2.get(broadcastReceiver2);
        if (list == null) {
            list = new ArrayList<>(1);
            list.add(broadcastReceiver);
            map.put(str2, broadcastReceiver2);
            map2.put(broadcastReceiver2, list);
            wp_(broadcastReceiver2, intentFilter, str, i);
        } else if (list.contains(broadcastReceiver)) {
            LogUtil.a("HAF_BroadcastManager", "duplicate registration exists action=", str2, ", receiver=", broadcastReceiver.toString());
        } else {
            list.add(broadcastReceiver);
        }
        int size = list.size();
        if (!Constants.CLICK_STATUS_BAR_ACTION.equals(str2) || size % 10 == 1) {
            LogUtil.c("HAF_BroadcastManager", "registerSystemReceiver action=", str2, ", size=", Integer.valueOf(size), ", map=", Integer.valueOf(map2.size()));
        }
    }

    private static void wp_(BroadcastReceiver broadcastReceiver, IntentFilter intentFilter, String str, int i) {
        Context e2 = BaseApplication.e();
        if (i < 0) {
            e2.registerReceiver(broadcastReceiver, intentFilter, str, null);
        } else {
            e2.registerReceiver(broadcastReceiver, intentFilter, str, null, i);
        }
    }

    private static BroadcastReceiver wg_(String str) {
        if ("com.huawei.haf.intent.action.DEBUG_SWITCH_LOG".equals(str)) {
            return new DebugSwitchReceiver();
        }
        return new InnerBroadcastReceiver();
    }

    private static boolean wF_(BroadcastReceiver broadcastReceiver, String str) {
        if (broadcastReceiver == null) {
            return false;
        }
        synchronized (e) {
            wE_(broadcastReceiver, str);
        }
        return true;
    }

    private static void wE_(BroadcastReceiver broadcastReceiver, String str) {
        Map<String, BroadcastReceiver> map = c;
        BroadcastReceiver broadcastReceiver2 = map.get(str);
        List<BroadcastReceiver> list = broadcastReceiver2 != null ? b.get(broadcastReceiver2) : null;
        if (list == null) {
            return;
        }
        if (!list.remove(broadcastReceiver)) {
            LogUtil.c("HAF_BroadcastManager", "duplicate deregistration exists action=", str, ", receiver=", broadcastReceiver.toString());
            return;
        }
        int size = list.size();
        if (size == 0) {
            wD_(broadcastReceiver2);
            b.remove(broadcastReceiver2);
            map.remove(str);
        }
        if (!Constants.CLICK_STATUS_BAR_ACTION.equals(str) || size % 10 == 0) {
            LogUtil.c("HAF_BroadcastManager", "unregisterSystemReceiver action=", str, ", size=", Integer.valueOf(size), ", map=", Integer.valueOf(b.size()));
        }
    }

    private static void wD_(BroadcastReceiver broadcastReceiver) {
        try {
            BaseApplication.e().unregisterReceiver(broadcastReceiver);
        } catch (IllegalArgumentException e2) {
            LogUtil.a("HAF_BroadcastManager", "unregisterSystemReceiver ex=", LogUtil.a(e2));
        }
    }

    static class InnerBroadcastReceiver extends BroadcastReceiver {
        private InnerBroadcastReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (!HwWatchFaceApi.ACTION_FOREGROUND_STATUS.equals(action) && !"com.huawei.haf.intent.action.UPDATE_LANGUAGE_READY".equals(action)) {
                LogUtil.c("HAF_BroadcastManager", "onReceive ", intent);
            }
            LocalBroadcastManager.getInstance(BaseApplication.e()).sendBroadcast(intent);
        }
    }
}
