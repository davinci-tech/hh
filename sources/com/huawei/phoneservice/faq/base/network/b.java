package com.huawei.phoneservice.faq.base.network;

import android.app.Activity;
import com.huawei.hms.framework.network.restclient.hwhttp.Submit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes9.dex */
class b {
    private static Map<String, List<Submit>> d = new HashMap();

    public static void ccX_(Activity activity) {
        String name = activity.getClass().getName();
        List<Submit> list = d.get(name);
        if (list != null) {
            for (Submit submit : list) {
                if (!submit.isCanceled()) {
                    submit.cancel();
                }
            }
            d.put(name, null);
        }
    }
}
