package com.huawei.openalliance.ad;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import com.huawei.openalliance.ad.constant.MapKeyNames;

/* loaded from: classes5.dex */
public class vd extends tg {
    private static String d(View view) {
        StringBuilder sb = new StringBuilder();
        int width = view.getWidth();
        int height = view.getHeight();
        sb.append(width);
        sb.append("*");
        sb.append(height);
        String sb2 = sb.toString();
        ho.b("ShowTrackerUtil", "adViewCreativeSize: %s", sb2);
        return sb2;
    }

    private static String c(View view) {
        ViewParent parent = view.getParent();
        for (int i = 0; i < 5 && parent != null; i++) {
            if (a(parent)) {
                return d((ViewGroup) parent);
            }
            parent = parent.getParent();
        }
        return null;
    }

    public static String b(View view) {
        if (view == null) {
            return null;
        }
        return a(view) ? c(view) : d(view);
    }

    public static String b(Bundle bundle) {
        String optString = com.huawei.openalliance.ad.utils.cz.b(bundle).optString(MapKeyNames.SLOT_POSITION, "");
        if (!com.huawei.openalliance.ad.utils.cz.o(optString)) {
            optString = null;
        }
        ho.a("ShowTrackerUtil", "slotPosition: %s", optString);
        return optString;
    }

    public static String a(Bundle bundle) {
        String optString = com.huawei.openalliance.ad.utils.cz.b(bundle).optString(MapKeyNames.CREATIVE_SIZE, "");
        if (!com.huawei.openalliance.ad.utils.cz.p(optString)) {
            optString = null;
        }
        ho.a("ShowTrackerUtil", "creativeSize: %s", optString);
        return optString;
    }
}
