package com.huawei.ads.adsrec;

import android.text.TextUtils;
import android.util.LruCache;
import com.huawei.ads.adsrec.bean.RelationScore;
import com.huawei.ads.adsrec.db.table.AdCreativeContentRecord;
import com.huawei.ads.fund.util.ListUtil;
import com.huawei.ads.fund.util.StringUtils;
import defpackage.uw;
import defpackage.vb;
import defpackage.vg;
import defpackage.vh;
import defpackage.vl;
import defpackage.vp;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes2.dex */
public abstract class o0 {

    /* renamed from: a, reason: collision with root package name */
    private static final LruCache<String, String> f1680a = new LruCache<>(50);

    public static int b() {
        int parseIntOrDefault;
        IUtilCallback d = uw.d();
        if (d != null && (parseIntOrDefault = StringUtils.parseIntOrDefault(d.getConfig("sampleSize"), 50)) >= 0) {
            return parseIntOrDefault;
        }
        return 50;
    }

    private static byte[] a() {
        IUtilCallback d = uw.d();
        return d == null ? new byte[0] : d.getDeviceAiParamKey();
    }

    public static void a(vb vbVar, Map<String, RelationScore> map) {
        RelationScore relationScore;
        if (vp.a(map) || vbVar == null || (relationScore = map.get(vbVar.f())) == null) {
            return;
        }
        vbVar.c(relationScore.d());
        vbVar.e(relationScore.b());
        vbVar.b(relationScore.a());
        relationScore.d();
        relationScore.b();
        relationScore.a();
    }

    public static Map<String, List<String>> a(vh vhVar) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        if (vhVar != null && !ListUtil.isEmpty(vhVar.j())) {
            for (vg vgVar : vhVar.j()) {
                if (vgVar != null && !vgVar.g()) {
                    ArrayList arrayList = new ArrayList();
                    for (vb vbVar : vgVar.a()) {
                        if (vbVar != null) {
                            arrayList.add(vbVar.f());
                        }
                    }
                    String h = vgVar.h();
                    if (!StringUtils.isBlank(h)) {
                        linkedHashMap.put(h, arrayList);
                    }
                }
            }
        }
        return linkedHashMap;
    }

    public static int a(vb vbVar, vb vbVar2, int i) {
        if (i != 0) {
            return i;
        }
        String n = vbVar.n();
        String n2 = vbVar2.n();
        return n.equals("1") ? n2.equals("1") ? 0 : -1 : n2.equals("1") ? 1 : 0;
    }

    public static int a(vb vbVar, vb vbVar2) {
        if (vbVar.y().booleanValue()) {
            return 1;
        }
        return !vbVar2.y().booleanValue() ? 0 : -1;
    }

    public static int a(double d, double d2) {
        if (Math.abs(d - d2) < 9.999999974752427E-7d) {
            return 0;
        }
        return Double.compare(d, d2);
    }

    public static double a(vb vbVar) {
        AdCreativeContentRecord d = vbVar.d();
        if (d == null) {
            return 0.0d;
        }
        String c = d.c();
        if (TextUtils.isEmpty(c)) {
            return 0.0d;
        }
        try {
            LruCache<String, String> lruCache = f1680a;
            String str = lruCache.get(c);
            if (str == null) {
                str = vl.c(c, a());
                if (!TextUtils.isEmpty(str)) {
                    lruCache.put(c, str);
                }
            }
            return Double.parseDouble(str);
        } catch (Exception unused) {
            return 0.0d;
        }
    }
}
