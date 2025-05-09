package com.huawei.openalliance.ad;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.openalliance.ad.constant.EventType;

/* loaded from: classes5.dex */
public abstract class qe {
    public static qf a(Context context, sl slVar, String str) {
        return a(context, slVar, a(str));
    }

    public static qf a(Context context, sl slVar, EventType eventType) {
        return a(context, slVar, eventType.getCategory());
    }

    public static qf a(Context context, sl slVar, int i) {
        return i != 1 ? i != 2 ? i != 4 ? new qh(context, slVar) : new qb(context, slVar) : new qd(context, slVar) : new qg(context, slVar);
    }

    private static int a(String str) {
        if (TextUtils.isEmpty(str)) {
            return 3;
        }
        for (EventType eventType : EventType.values()) {
            if (TextUtils.equals(eventType.value(), str)) {
                return eventType.getCategory();
            }
        }
        return 3;
    }
}
