package com.huawei.openalliance.ad.utils;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.view.View;
import com.huawei.openalliance.ad.ho;

/* loaded from: classes5.dex */
public class b {
    private static Activity b(Context context) {
        if (context == null) {
            ho.d("ActivityUtils", "ana_tag getActivity context is null, return");
            return null;
        }
        int i = 0;
        while (context instanceof ContextWrapper) {
            if (context instanceof Activity) {
                return (Activity) context;
            }
            i++;
            if (i > 5) {
                ho.d("ActivityUtils", "ana_tag getActivity loop too much times, return");
                return null;
            }
            context = ((ContextWrapper) context).getBaseContext();
        }
        return null;
    }

    public static String a(Object obj) {
        String str;
        if (obj == null) {
            str = "ana_tag getActivityName obj is null, return";
        } else {
            if (obj instanceof View) {
                return a(((View) obj).getContext());
            }
            str = "ana_tag  getActivityName activityname is not view";
        }
        ho.d("ActivityUtils", str);
        return null;
    }

    public static String a(Context context) {
        Activity b = b(context);
        if (b != null) {
            ho.a("ActivityUtils", "ana_tag  getActivityLocalClassName LocalClassName = %s", b.getLocalClassName());
            return b.getLocalClassName();
        }
        ho.d("ActivityUtils", "ana_tag  getActivityLocalClassName LocalClassName is null");
        return "";
    }
}
