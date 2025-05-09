package com.huawei.ads.adsrec;

import android.content.Context;
import com.huawei.ads.adsrec.db.table.AdIECImpRecord;
import com.huawei.ads.fund.util.JsonUtil;
import com.huawei.ads.fund.util.ListUtil;
import com.huawei.ads.fund.util.StringUtils;
import defpackage.uw;
import defpackage.vb;
import defpackage.vf;
import defpackage.vg;
import defpackage.vh;
import defpackage.vt;
import defpackage.wb;
import defpackage.wc;
import defpackage.wf;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes2.dex */
public abstract class j {
    public static void a(Map<String, Integer> map, Map<String, Integer> map2, Map<String, Integer> map3, Map<String, Integer> map4) {
        vf vfVar = new vf();
        vfVar.b("2500005");
        vfVar.e(JsonUtil.toJsonNoException(map));
        vfVar.a(JsonUtil.toJsonNoException(map2));
        vfVar.d(JsonUtil.toJsonNoException(map3));
        vfVar.c(JsonUtil.toJsonNoException(map4));
        new wf().c(vfVar);
    }

    public static void a(vt vtVar, List<String> list, wb wbVar) {
        vf vfVar = new vf();
        vfVar.b("2500002");
        vfVar.e(w0.a(vtVar.c()));
        vfVar.a(list.toString());
        vfVar.d(String.valueOf(wbVar.c()));
        vfVar.c(wbVar.e());
        vfVar.f(String.valueOf(wbVar.d()));
        vfVar.g(wbVar.b());
        IDsRelationCallback a2 = uw.a();
        if (a2 != null) {
            vfVar.h(String.valueOf(a2.isSupportRelateRank() ? 1 : 0));
        }
        vfVar.c(vtVar.h());
        vfVar.j(vtVar.a());
        vfVar.b(vtVar.l().intValue());
        new wf().c(vfVar);
    }

    public static void a(Context context, vh vhVar, vt vtVar, Map<String, List<String>> map) {
        vf vfVar = new vf();
        vfVar.b("2500001");
        vfVar.e(a(vtVar.d()));
        vfVar.a(a(context, vtVar.d()));
        List<String> a2 = a(vhVar);
        vfVar.d(a2.size() > 0 ? "1" : "0");
        vfVar.c(a(context, a2, vtVar));
        IUtilCallback d = uw.d();
        if (d != null) {
            vfVar.f(String.valueOf(d.isInHmsProcess() ? 1 : 0));
        }
        vfVar.g(a(map, o0.a(vhVar)));
        vfVar.c(vtVar.h());
        vfVar.j(vtVar.a());
        vfVar.b(vtVar.l().intValue());
        new wf().c(vfVar);
    }

    public static void a(Context context, vh vhVar, vt vtVar) {
        String c = vtVar.c();
        String a2 = w0.a(c);
        HashMap hashMap = new HashMap();
        int i = 0;
        if (vhVar.g()) {
            int d = wc.d(context).d(vtVar.d().get(0));
            if (1 == d || 2 == d) {
                i = 1;
            }
        } else {
            int i2 = -1;
            for (vg vgVar : vhVar.j()) {
                if (vgVar != null && !vgVar.g()) {
                    StringBuilder sb = new StringBuilder();
                    for (vb vbVar : vgVar.a()) {
                        sb.append(vbVar.f());
                        sb.append(",");
                        i2 = vbVar.s();
                    }
                    String sb2 = sb.toString();
                    if (!StringUtils.isBlank(sb2)) {
                        sb2 = sb2.substring(0, sb2.length() - 1);
                    }
                    hashMap.put(vgVar.h(), sb2);
                }
            }
            i = i2;
        }
        String jsonNoException = JsonUtil.toJsonNoException(hashMap);
        vf vfVar = new vf();
        vfVar.b("2500004");
        vfVar.e(a2);
        vfVar.a(c);
        vfVar.d(String.valueOf(i));
        vfVar.c(jsonNoException);
        vfVar.c(vtVar.h());
        vfVar.j(vtVar.a());
        vfVar.b(vtVar.l().intValue());
        new wf().c(vfVar);
    }

    public static void a(int i, String str, String str2, String str3, String str4) {
        vf vfVar = new vf();
        vfVar.b("2500003");
        vfVar.e(String.valueOf(i));
        vfVar.a(str4);
        vfVar.d(str);
        vfVar.c(str2);
        vfVar.f(str3);
        new wf().c(vfVar);
    }

    private static List<String> a(vh vhVar) {
        ArrayList arrayList = new ArrayList();
        if (vhVar != null && !ListUtil.isEmpty(vhVar.j())) {
            for (vg vgVar : vhVar.j()) {
                if (vgVar != null && !vgVar.g()) {
                    Iterator<vb> it = vgVar.a().iterator();
                    while (it.hasNext()) {
                        arrayList.add(it.next().f());
                    }
                }
            }
        }
        return arrayList;
    }

    public static String a(Map<String, List<String>> map, Map<String, List<String>> map2) {
        StringBuilder sb = new StringBuilder();
        for (String str : map2.keySet()) {
            List<String> list = map.get(str);
            List<String> list2 = map2.get(str);
            if (list != null && list2 != null) {
                int i = 0;
                for (int i2 = 0; i2 < list2.size(); i2++) {
                    if (i2 >= list.size() || !list2.get(i2).equals(list.get(i2))) {
                        i = i2 + 1;
                        break;
                    }
                }
                if (sb.length() > 0) {
                    sb.append(";");
                }
                sb.append(str);
                sb.append(":");
                sb.append(i);
            }
        }
        return sb.toString();
    }

    private static String a(List<String> list) {
        StringBuilder sb = new StringBuilder();
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            sb.append(it.next());
            sb.append(",");
        }
        String sb2 = sb.toString();
        return !StringUtils.isBlank(sb2) ? sb2.substring(0, sb2.length() - 1) : sb2;
    }

    private static String a(Context context, List<String> list, vt vtVar) {
        e eVar = new e(context);
        String a2 = vtVar.a();
        ArrayList arrayList = new ArrayList();
        for (String str : list) {
            HashMap hashMap = new HashMap();
            hashMap.put(str, String.valueOf(0));
            for (AdIECImpRecord adIECImpRecord : eVar.query(AdIECImpRecord.class, null, "pkgName=? and contentId=?", new String[]{a2, str}, null, null)) {
                hashMap.put(adIECImpRecord.c(), String.valueOf(adIECImpRecord.d()));
            }
            arrayList.add(hashMap);
        }
        return JsonUtil.toJsonNoException(arrayList);
    }

    private static String a(Context context, List<String> list) {
        StringBuilder sb = new StringBuilder();
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            sb.append(wc.d(context).j(it.next()));
            sb.append(",");
        }
        String sb2 = sb.toString();
        return !StringUtils.isBlank(sb2) ? sb2.substring(0, sb2.length() - 1) : sb2;
    }
}
