package com.huawei.wallet.proxy.utils;

import android.app.Activity;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes9.dex */
public class WearActivityManager {
    private static List<Activity> d = new ArrayList();

    public static void eLS_(Activity activity) {
        synchronized (WearActivityManager.class) {
            d.add(activity);
        }
    }

    public static void c(String str) {
        synchronized (WearActivityManager.class) {
            if (!TextUtils.isEmpty(str)) {
                for (Activity activity : d) {
                    if (str.equals(activity.getClass().getName())) {
                        activity.finish();
                    }
                }
            }
        }
    }
}
