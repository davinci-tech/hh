package defpackage;

import android.text.TextUtils;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.dbn;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.json.JSONObject;

/* loaded from: classes8.dex */
public class dcf {
    private static List<JSONObject> e(List<JSONObject> list, dbn.a.b bVar) {
        if (bVar == null) {
            return list;
        }
        d(list, (dbn.a.i) null, bVar);
        return list;
    }

    /* JADX WARN: Code restructure failed: missing block: B:41:0x00d5, code lost:
    
        return null;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static org.json.JSONObject b(dbn.a.e r12, java.util.List<org.json.JSONObject> r13) {
        /*
            r0 = 0
            if (r12 == 0) goto Lee
            java.util.List r1 = r12.getFilterConditions()
            if (r1 == 0) goto Lee
            java.util.List r1 = r12.getFilterConditions()
            int r1 = r1.size()
            if (r1 > 0) goto L15
            goto Lee
        L15:
            boolean r1 = defpackage.bkz.e(r13)
            if (r1 == 0) goto L1c
            return r0
        L1c:
            dbn$a$b r1 = r12.getAllDataSortCondition()
            java.util.List r13 = e(r13, r1)
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            java.util.HashMap r3 = new java.util.HashMap
            r4 = 16
            r3.<init>(r4)
            java.util.Iterator r13 = r13.iterator()
            java.lang.String r4 = ""
            r5 = r0
        L3c:
            boolean r6 = r13.hasNext()
            if (r6 == 0) goto Ld6
            java.lang.Object r6 = r13.next()
            org.json.JSONObject r6 = (org.json.JSONObject) r6
            java.util.List r7 = r12.getFilterConditions()
            java.util.Iterator r7 = r7.iterator()
        L50:
            boolean r8 = r7.hasNext()
            if (r8 == 0) goto L3c
            java.lang.Object r8 = r7.next()
            dbn$a$c r8 = (dbn.a.c) r8
            java.util.List r9 = r8.getMatchingCondition()
            if (r9 == 0) goto Ld5
            java.util.List r9 = r8.getMatchingCondition()
            int r9 = r9.size()
            if (r9 > 0) goto L6d
            goto Ld5
        L6d:
            r3.clear()
            e(r4, r2, r6, r3, r8)
            java.lang.String r9 = "params "
            java.lang.String r10 = " conditionRules "
            java.lang.Object[] r9 = new java.lang.Object[]{r9, r3, r10, r4}
            java.lang.String r10 = "FilterDataUtil"
            com.huawei.hwlogsmodel.LogUtil.a(r10, r9)
            boolean r9 = android.text.TextUtils.isEmpty(r4)
            if (r9 != 0) goto La8
            dcn r9 = defpackage.dcn.c()
            boolean r9 = r9.a(r4, r3)
            if (r9 == 0) goto La8
            java.lang.String r7 = "use conditionRules "
            java.lang.Object[] r7 = new java.lang.Object[]{r7}
            com.huawei.hwlogsmodel.LogUtil.a(r10, r7)
            java.lang.String r4 = e(r4, r8)
            d(r2, r8)
            dbn$a$i r5 = a(r5, r8)
            r1.add(r6)
            goto L3c
        La8:
            boolean r9 = android.text.TextUtils.isEmpty(r4)
            if (r9 == 0) goto L50
            dcn r9 = defpackage.dcn.c()
            java.lang.String r11 = r8.getConditionRules()
            boolean r9 = r9.a(r11, r3)
            if (r9 == 0) goto L50
            java.lang.String r7 = "conditionRules is null "
            java.lang.Object[] r7 = new java.lang.Object[]{r7}
            com.huawei.hwlogsmodel.LogUtil.a(r10, r7)
            java.lang.String r4 = e(r4, r8)
            d(r2, r8)
            dbn$a$i r5 = a(r5, r8)
            r1.add(r6)
            goto L3c
        Ld5:
            return r0
        Ld6:
            if (r5 != 0) goto Ldf
            boolean r12 = r1.isEmpty()
            if (r12 == 0) goto Ldf
            return r0
        Ldf:
            if (r5 != 0) goto Le9
            r12 = 0
            java.lang.Object r12 = r1.get(r12)
            org.json.JSONObject r12 = (org.json.JSONObject) r12
            goto Led
        Le9:
            org.json.JSONObject r12 = c(r5, r1)
        Led:
            return r12
        Lee:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.dcf.b(dbn$a$e, java.util.List):org.json.JSONObject");
    }

    private static void e(String str, List<String> list, JSONObject jSONObject, Map<String, Object> map, dbn.a.c cVar) {
        if (TextUtils.isEmpty(str)) {
            c(jSONObject, map, cVar.getMatchingCondition());
        } else {
            c(jSONObject, map, list);
        }
    }

    private static dbn.a.i a(dbn.a.i iVar, dbn.a.c cVar) {
        return iVar == null ? cVar.getSortCondition() : iVar;
    }

    private static void d(List<String> list, dbn.a.c cVar) {
        if (list.size() <= 0) {
            list.addAll(cVar.getMatchingCondition());
        }
    }

    private static String e(String str, dbn.a.c cVar) {
        return TextUtils.isEmpty(str) ? cVar.getConditionRules() : str;
    }

    private static JSONObject c(dbn.a.i iVar, List<JSONObject> list) {
        if (iVar == null || list.size() <= 0) {
            LogUtil.a("FilterDataUtil", "sortCondition == null || filterDataList.size() <= 0 ");
            return null;
        }
        d(list, iVar, (dbn.a.b) null);
        return list.get(0);
    }

    private static void d(List<JSONObject> list, final dbn.a.i iVar, final dbn.a.b bVar) {
        if (iVar == null && bVar == null) {
            return;
        }
        Collections.sort(list, new Comparator() { // from class: dcj
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return dcf.e(dbn.a.i.this, bVar, (JSONObject) obj, (JSONObject) obj2);
            }
        });
    }

    static /* synthetic */ int e(dbn.a.i iVar, dbn.a.b bVar, JSONObject jSONObject, JSONObject jSONObject2) {
        if (!TextUtils.isEmpty(c(iVar, bVar))) {
            if (c(a(jSONObject, c(iVar, bVar)), a(jSONObject2, c(iVar, bVar))) && b(jSONObject, jSONObject2, iVar, bVar) != -10) {
                return b(jSONObject, jSONObject2, iVar, bVar);
            }
            return d(d(iVar, bVar), a(jSONObject, c(iVar, bVar)), a(jSONObject2, c(iVar, bVar)));
        }
        LogUtil.a("FilterDataUtil", "The configuration does not exist.");
        return d(d(iVar, bVar), a(jSONObject, "startTime"), a(jSONObject2, "startTime"));
    }

    private static String a(JSONObject jSONObject, String str) {
        if (jSONObject != null && jSONObject.has(str)) {
            return jSONObject.optString(str);
        }
        LogUtil.a("FilterDataUtil", "jsonObject or key is null");
        return "";
    }

    private static String c(dbn.a.i iVar, dbn.a.b bVar) {
        if (iVar != null) {
            return iVar.getKey();
        }
        if (bVar != null) {
            return bVar.getKey();
        }
        LogUtil.a("FilterDataUtil", "key is null");
        return "";
    }

    private static String d(dbn.a.i iVar, dbn.a.b bVar) {
        if (iVar != null) {
            return iVar.getSortRules();
        }
        if (bVar != null) {
            return bVar.getSortRules();
        }
        LogUtil.a("FilterDataUtil", "sortCondition or allDataSortCondition is null");
        return "";
    }

    private static int b(JSONObject jSONObject, JSONObject jSONObject2, dbn.a.i iVar, dbn.a.b bVar) {
        List<dbn.a.C0294a> a2;
        if ((iVar == null && bVar == null) || jSONObject == null || jSONObject2 == null || (a2 = a(iVar, bVar)) == null || a2.size() <= 0) {
            return -10;
        }
        for (int i = 0; i < a2.size(); i++) {
            dbn.a.C0294a c0294a = a2.get(i);
            if (!c(a(jSONObject, c0294a.getKey()), a(jSONObject2, c0294a.getKey()))) {
                return d(d(iVar, bVar), a(jSONObject, c0294a.getKey()), a(jSONObject2, c0294a.getKey()));
            }
        }
        return -10;
    }

    private static List<dbn.a.C0294a> a(dbn.a.i iVar, dbn.a.b bVar) {
        List<dbn.a.C0294a> arrayList = new ArrayList<>();
        if (iVar != null) {
            arrayList = iVar.getParamsLists();
        }
        return bVar != null ? bVar.getParamsLists() : arrayList;
    }

    private static boolean c(String str, String str2) {
        return Objects.equals(str, str2);
    }

    private static int d(String str, String str2, String str3) {
        str.hashCode();
        if (str.equals("Reverse")) {
            return dcn.c().d(str3, str2);
        }
        if (str.equals("Positive")) {
            return dcn.c().d(str2, str3);
        }
        return dcn.c().d(str3, str2);
    }

    private static void c(JSONObject jSONObject, Map<String, Object> map, List<String> list) {
        for (String str : list) {
            if ("currentTime".equals(str)) {
                map.put(str, String.valueOf(System.currentTimeMillis()));
            } else {
                map.put(str, a(jSONObject, str));
            }
        }
    }
}
