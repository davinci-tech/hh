package com.huawei.openalliance.ad;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public class fd implements fz {

    /* renamed from: a, reason: collision with root package name */
    private static final byte[] f6869a = new byte[0];
    private static fz e;
    private final byte[] b = new byte[0];
    private Context c;
    private final SharedPreferences d;

    @Override // com.huawei.openalliance.ad.fz
    public void a(Map<String, String> map) {
        synchronized (this.b) {
            if (com.huawei.openalliance.ad.utils.bl.a(map)) {
                return;
            }
            Map map2 = (Map) com.huawei.openalliance.ad.utils.be.b(this.d.getString("splashPreloadMode", ""), Map.class, new Class[0]);
            if (com.huawei.openalliance.ad.utils.bl.a(map2)) {
                map2 = new HashMap();
            }
            map2.putAll(map);
            this.d.edit().putString("splashPreloadMode", com.huawei.openalliance.ad.utils.be.b(map2)).commit();
        }
    }

    @Override // com.huawei.openalliance.ad.fz
    public String a() {
        String string;
        synchronized (this.b) {
            string = this.d.getString("splashPreloadMode", "");
        }
        return string;
    }

    @Override // com.huawei.openalliance.ad.fz
    public int a(String str) {
        synchronized (this.b) {
            Map map = (Map) com.huawei.openalliance.ad.utils.be.b(this.d.getString("splashPreloadMode", ""), Map.class, new Class[0]);
            if (com.huawei.openalliance.ad.utils.bl.a(map)) {
                return 1;
            }
            Integer h = com.huawei.openalliance.ad.utils.cz.h((String) map.get(str));
            if (h != null && h.intValue() >= 0) {
                return h.intValue();
            }
            return 1;
        }
    }

    private static fz b(Context context) {
        fz fzVar;
        synchronized (f6869a) {
            if (e == null) {
                e = new fd(context);
            }
            fzVar = e;
        }
        return fzVar;
    }

    public static fz a(Context context) {
        return b(context);
    }

    private fd(Context context) {
        Context i = com.huawei.openalliance.ad.utils.x.i(context.getApplicationContext());
        this.c = i;
        this.d = i.getSharedPreferences("HiAd_preload_sp", 0);
    }
}
