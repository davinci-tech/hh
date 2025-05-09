package com.huawei.openalliance.ad.utils;

import com.huawei.openalliance.ad.beans.parameter.AdSlotParam;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.td;

/* loaded from: classes5.dex */
public class dp {
    public static void a(final AdSlotParam adSlotParam) {
        m.f(new Runnable() { // from class: com.huawei.openalliance.ad.utils.dp.1
            @Override // java.lang.Runnable
            public void run() {
                Integer num;
                try {
                    num = Integer.valueOf(new td().a());
                } catch (Throwable th) {
                    ho.c("WLACManagerUtils", "getWlacStatus ex: %s", th.getClass().getSimpleName());
                    num = null;
                }
                AdSlotParam adSlotParam2 = AdSlotParam.this;
                if (adSlotParam2 != null) {
                    adSlotParam2.e(num);
                }
            }
        });
    }
}
