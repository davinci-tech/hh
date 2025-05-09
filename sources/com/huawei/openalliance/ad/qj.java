package com.huawei.openalliance.ad;

import android.content.Context;
import com.huawei.openalliance.ad.beans.metadata.Ad30;
import com.huawei.openalliance.ad.beans.metadata.FlowControl;
import com.huawei.openalliance.ad.beans.parameter.AdSlotParam;
import com.huawei.openalliance.ad.beans.server.AdContentRsp;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public class qj {
    public static boolean a(Context context, AdSlotParam adSlotParam) {
        HashMap hashMap = new HashMap();
        if (adSlotParam == null) {
            return false;
        }
        List<String> a2 = adSlotParam.a();
        if (com.huawei.openalliance.ad.utils.bg.a(a2) || context == null) {
            return false;
        }
        ArrayList arrayList = new ArrayList(a2);
        try {
        } catch (Throwable th) {
            ho.c("FlowControlManager", "filter err, %s", th.getClass().getSimpleName());
        }
        if (fh.b(context).ch() == 0) {
            adSlotParam.a(a(a2));
            return true;
        }
        SecureRandom secureRandom = new SecureRandom();
        for (String str : a2) {
            FlowControl v = fh.b(context).v(str);
            if (v != null) {
                a(context, hashMap, arrayList, secureRandom, str, v, adSlotParam.D());
            } else {
                hashMap.put(str, 0);
            }
        }
        if (com.huawei.openalliance.ad.utils.bg.a(arrayList)) {
            return false;
        }
        adSlotParam.a(hashMap);
        adSlotParam.a(arrayList);
        return true;
    }

    private static void a(Context context, Map<String, Integer> map, List<String> list, SecureRandom secureRandom, String str, FlowControl flowControl, int i) {
        if (System.currentTimeMillis() > flowControl.b()) {
            a(context, str, i);
            map.put(str, 0);
            return;
        }
        int a2 = flowControl.a();
        if (a2 <= 0 || a2 > 100) {
            map.put(str, 0);
        } else {
            if (secureRandom.nextInt(101) > a2) {
                map.put(str, 1);
                return;
            }
            ho.b("FlowControlManager", "discard slot %s", str);
            fh.b(context).w(str);
            list.remove(str);
        }
    }

    private static void a(final Context context, final String str, final int i) {
        com.huawei.openalliance.ad.utils.m.h(new Runnable() { // from class: com.huawei.openalliance.ad.qj.1
            @Override // java.lang.Runnable
            public void run() {
                Context context2 = context;
                if (context2 == null) {
                    return;
                }
                gc b = fh.b(context2);
                int x = b.x(str);
                ho.a("FlowControlManager", "fc counts: %d", Integer.valueOf(x));
                if (x <= 0) {
                    ho.b("FlowControlManager", "no need report");
                } else {
                    new com.huawei.openalliance.ad.analysis.g(context).a(str, x, i);
                    b.y(str);
                }
            }
        });
    }

    public static void a(Context context, AdContentRsp adContentRsp) {
        try {
            if (context == null || adContentRsp == null) {
                ho.b("FlowControlManager", "para is empty");
                return;
            }
            List<Ad30> c = adContentRsp.c();
            if (com.huawei.openalliance.ad.utils.bg.a(c)) {
                return;
            }
            for (Ad30 ad30 : c) {
                if (ad30 != null && ad30.i() != null) {
                    fh.b(context).a(ad30.a(), ad30.i());
                }
            }
        } catch (Throwable th) {
            ho.c("FlowControlManager", "save fc err, %s", th.getClass().getSimpleName());
        }
    }

    private static Map<String, Integer> a(List<String> list) {
        HashMap hashMap = new HashMap();
        if (com.huawei.openalliance.ad.utils.bg.a(list)) {
            return hashMap;
        }
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            hashMap.put(it.next(), 0);
        }
        return hashMap;
    }
}
