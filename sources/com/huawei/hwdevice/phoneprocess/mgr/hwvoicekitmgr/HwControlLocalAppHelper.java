package com.huawei.hwdevice.phoneprocess.mgr.hwvoicekitmgr;

import android.content.Context;
import android.text.TextUtils;
import androidx.exifinterface.media.ExifInterface;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.operation.OpAnalyticsUtil;
import com.huawei.operation.OperationKey;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import defpackage.cvx;
import defpackage.jds;
import defpackage.kfj;
import defpackage.kfk;
import defpackage.kfl;
import health.compact.a.CommonUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class HwControlLocalAppHelper {
    private Context b;

    private int e(int i) {
        if (i < 1 || i > 7) {
            return 0;
        }
        return 1 << (i - 1);
    }

    public HwControlLocalAppHelper(Context context) {
        this.b = context;
    }

    private void x(kfj kfjVar) {
        if (kfjVar == null) {
            LogUtil.h("HwControlLocalAppHelper", "dealControlLocalApp but callParam is null");
            return;
        }
        StringBuilder sb = new StringBuilder(16);
        String e = e(kfjVar);
        if (TextUtils.isEmpty(e)) {
            LogUtil.h("HwControlLocalAppHelper", "dealControlLocalApp but commandCodeTlv is null");
            return;
        }
        e(e, sb);
        e(d(kfjVar), sb);
        d(kfjVar, sb);
        e(r(kfjVar), sb);
        e(l(kfjVar), sb);
        e(b(kfjVar), sb);
        e(o(kfjVar), sb);
        e(p(kfjVar), sb);
        b(kfjVar, sb);
        kfk.d(sb.toString(), 5);
    }

    private void e(String str, StringBuilder sb) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        sb.append(str);
        LogUtil.a("HwControlLocalAppHelper", "appendTlv tlv:", str);
    }

    private void d(kfj kfjVar, StringBuilder sb) {
        e(m(kfjVar), sb);
        e(a(kfjVar), sb);
        e(h(kfjVar), sb);
        e(c(kfjVar), sb);
        e(g(kfjVar), sb);
        e(s(kfjVar), sb);
        e(n(kfjVar), sb);
    }

    private void b(kfj kfjVar, StringBuilder sb) {
        e(k(kfjVar), sb);
        e(j(kfjVar), sb);
        e(f(kfjVar), sb);
        e(i(kfjVar), sb);
        e(t(kfjVar), sb);
        e(q(kfjVar), sb);
    }

    private String e(kfj kfjVar) {
        int c;
        if (TextUtils.isEmpty(kfjVar.d()) || (c = jds.c(kfjVar.d(), 10)) <= 0) {
            return "";
        }
        String e = cvx.e(c);
        return cvx.e(1) + cvx.d(e.length() / 2) + e;
    }

    private String b(kfj kfjVar) {
        List<kfj.d> c = kfjVar.c();
        if (c == null || c.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder(c.size());
        for (kfj.d dVar : c) {
            String c2 = cvx.c(dVar.b());
            String e = cvx.e(10);
            String d = cvx.d(c2.length() / 2);
            String c3 = cvx.c(dVar.d());
            String e2 = cvx.e(11);
            String d2 = cvx.d(c3.length() / 2);
            sb.append(e);
            sb.append(d);
            sb.append(c2);
            sb.append(e2);
            sb.append(d2);
            sb.append(c3);
        }
        return cvx.e(137) + cvx.d(sb.length() / 2) + ((Object) sb);
    }

    private String c(kfj kfjVar) {
        String b = !TextUtils.isEmpty(kfjVar.a()) ? b(kfjVar.a(), 1000) : "";
        if (TextUtils.isEmpty(b)) {
            return "";
        }
        return cvx.e(12) + cvx.d(b.length() / 2) + b;
    }

    private String g(kfj kfjVar) {
        String b = !TextUtils.isEmpty(kfjVar.i()) ? b(kfjVar.i(), 1) : "";
        if (TextUtils.isEmpty(b)) {
            return "";
        }
        return cvx.e(13) + cvx.d(b.length() / 2) + b;
    }

    private String s(kfj kfjVar) {
        String b = !TextUtils.isEmpty(kfjVar.u()) ? b(kfjVar.u(), 1) : "";
        if (TextUtils.isEmpty(b)) {
            return "";
        }
        return cvx.e(14) + cvx.d(b.length() / 2) + b;
    }

    private String o(kfj kfjVar) {
        int c;
        if (TextUtils.isEmpty(kfjVar.r()) || (c = jds.c(kfjVar.r(), 10)) <= 0) {
            return "";
        }
        String e = cvx.e(c);
        return cvx.e(15) + cvx.d(e.length() / 2) + e;
    }

    private String p(kfj kfjVar) {
        String b = !TextUtils.isEmpty(kfjVar.q()) ? b(kfjVar.q(), 1) : "";
        if (TextUtils.isEmpty(b)) {
            return "";
        }
        return cvx.e(16) + cvx.d(b.length() / 2) + b;
    }

    private String n(kfj kfjVar) {
        String b = !TextUtils.isEmpty(kfjVar.s()) ? b(kfjVar.s(), 1) : "";
        if (TextUtils.isEmpty(b)) {
            return "";
        }
        return cvx.e(17) + cvx.d(b.length() / 2) + b;
    }

    public boolean e(String str, String str2) {
        if (str2.contains("|")) {
            for (String str3 : str2.split("\\|")) {
                if (!str.equals(str3)) {
                }
            }
            return false;
        }
        if (!str.equals(str2)) {
            return false;
        }
        return true;
    }

    private String c(JSONObject jSONObject, String str) {
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            if (e(str, next)) {
                return jSONObject.optString(next);
            }
        }
        return "";
    }

    private String d(kfj kfjVar) {
        int c;
        String a2;
        if (TextUtils.isEmpty(kfjVar.b())) {
            return "";
        }
        if (kfjVar.e() == null) {
            a2 = cvx.c(kfjVar.b());
        } else {
            String c2 = c(kfjVar.e(), kfjVar.b());
            if (TextUtils.isEmpty(c2) || (c = jds.c(c2, 10)) <= 0) {
                return "";
            }
            a2 = cvx.a(c);
        }
        return cvx.e(2) + cvx.d(a2.length() / 2) + a2;
    }

    private String r(kfj kfjVar) {
        if (TextUtils.isEmpty(kfjVar.o())) {
            return "";
        }
        String b = b(kfjVar.o());
        if (TextUtils.isEmpty(b)) {
            return "";
        }
        return cvx.e(4) + cvx.d(b.length() / 2) + b;
    }

    private String l(kfj kfjVar) {
        if (TextUtils.isEmpty(kfjVar.p())) {
            return "";
        }
        String d = d(kfjVar.p());
        if (TextUtils.isEmpty(d)) {
            return "";
        }
        return cvx.e(5) + cvx.d(d.length() / 2) + d;
    }

    private String a(kfj kfjVar) {
        String b = !TextUtils.isEmpty(kfjVar.f()) ? b(kfjVar.f(), 1000) : "";
        if (!TextUtils.isEmpty(kfjVar.g())) {
            b = b(kfjVar.g(), 1000);
        }
        if (TextUtils.isEmpty(b)) {
            return "";
        }
        return cvx.e(6) + cvx.d(b.length() / 2) + b;
    }

    private String h(kfj kfjVar) {
        if (TextUtils.isEmpty(kfjVar.h()) && TextUtils.isEmpty(kfjVar.m())) {
            return "";
        }
        String e = cvx.e(((TextUtils.isEmpty(kfjVar.m()) || !"Y/indoor".contains(kfjVar.m())) && (TextUtils.isEmpty(kfjVar.h()) || !"Y/indoor".contains(kfjVar.h()))) ? 2 : 1);
        return cvx.e(7) + cvx.d(e.length() / 2) + e;
    }

    private String m(kfj kfjVar) {
        int c;
        String e;
        if (TextUtils.isEmpty(kfjVar.y())) {
            return "";
        }
        if (kfjVar.v() == null) {
            e = cvx.c(kfjVar.y());
        } else {
            String c2 = c(kfjVar.v(), kfjVar.y());
            if (TextUtils.isEmpty(c2) || (c = jds.c(c2, 10)) <= 0) {
                return "";
            }
            e = cvx.e(c);
        }
        return cvx.e(3) + cvx.d(e.length() / 2) + e;
    }

    private String k(kfj kfjVar) {
        if (TextUtils.isEmpty(kfjVar.t())) {
            return "";
        }
        int c = jds.c(kfjVar.t(), 10);
        if (c == -1) {
            LogUtil.h("HwControlLocalAppHelper", "buildSettingItemTlv Invaild settingItem");
            return "";
        }
        String e = cvx.e(c);
        return cvx.e(18) + cvx.d(e.length() / 2) + e;
    }

    private String j(kfj kfjVar) {
        if (TextUtils.isEmpty(kfjVar.n())) {
            return "";
        }
        int c = jds.c(kfjVar.n(), 10);
        if (c == -1) {
            LogUtil.h("HwControlLocalAppHelper", "buildOptCodeTlv Invaild optCode");
            return "";
        }
        String e = cvx.e(c);
        return cvx.e(19) + cvx.d(e.length() / 2) + e;
    }

    private String f(kfj kfjVar) {
        if (TextUtils.isEmpty(kfjVar.j())) {
            return "";
        }
        if (kfjVar.l() == null) {
            LogUtil.h("HwControlLocalAppHelper", "buildMediaTypeTlv mediaTypeDictionary is null");
            return "";
        }
        int c = jds.c(c(kfjVar.l(), kfjVar.j()), 10);
        if (c == -1) {
            LogUtil.h("HwControlLocalAppHelper", "buildMediaTypeTlv Invalid mediaType");
            return "";
        }
        String e = cvx.e(c);
        return cvx.e(20) + cvx.d(e.length() / 2) + e;
    }

    private String i(kfj kfjVar) {
        if (TextUtils.isEmpty(kfjVar.k())) {
            return "";
        }
        int c = jds.c(kfjVar.k(), 10);
        if (c == -1) {
            LogUtil.h("HwControlLocalAppHelper", "buildOptTypeTlv Invalid optType");
            return "";
        }
        String e = cvx.e(c);
        return cvx.e(21) + cvx.d(e.length() / 2) + e;
    }

    private String t(kfj kfjVar) {
        if (TextUtils.isEmpty(kfjVar.w())) {
            return "";
        }
        if (kfjVar.x() == null) {
            LogUtil.h("HwControlLocalAppHelper", "buildVolumeTypeTlv volumeTypeDictionary is null");
            return "";
        }
        int c = jds.c(c(kfjVar.x(), kfjVar.w()), 10);
        if (c == -1) {
            LogUtil.h("HwControlLocalAppHelper", "buildVolumeTypeTlv Invalid volumeType");
            return "";
        }
        String e = cvx.e(c);
        return cvx.e(22) + cvx.d(e.length() / 2) + e;
    }

    private String q(kfj kfjVar) {
        if (TextUtils.isEmpty(kfjVar.ab())) {
            return "";
        }
        String c = cvx.c(kfjVar.ab());
        return cvx.e(23) + cvx.d(c.length() / 2) + c;
    }

    private String b(String str, int i) {
        double d;
        int i2;
        try {
            d = Double.parseDouble(str);
        } catch (NumberFormatException unused) {
            LogUtil.b("HwControlLocalAppHelper", "parse distance find NumberFormatException");
            d = 0.0d;
        }
        return (d > 0.0d && (i2 = (int) (d * ((double) i))) > 0) ? cvx.b(i2) : "";
    }

    private String d(String str) {
        int indexOf = str.indexOf(ExifInterface.GPS_DIRECTION_TRUE);
        if (indexOf < 0) {
            return "";
        }
        String[] split = str.substring(indexOf + 1, indexOf + 6).split(":");
        int c = split.length > 0 ? jds.c(split[0], 10) : -1;
        if (c < 0) {
            return "";
        }
        int c2 = split.length > 1 ? jds.c(split[1], 10) : -1;
        if (c2 < 0) {
            return "";
        }
        return cvx.e(c) + cvx.e(c2);
    }

    private String b(String str) {
        int c;
        if (str.contains(",") && str.contains(Constants.LINK)) {
            c = j(str);
        } else if (str.contains(",")) {
            c = f(str);
        } else if (str.contains(Constants.LINK)) {
            c = h(str);
        } else {
            c = c(str);
        }
        return c == 0 ? "" : cvx.e(c);
    }

    private int j(String str) {
        int c;
        int i = 0;
        for (String str2 : str.split(",")) {
            if (str2.contains(Constants.LINK)) {
                c = h(str2);
            } else {
                c = c(str2);
            }
            i += c;
        }
        return i;
    }

    private int c(String str) {
        if (str.startsWith("W") && str.length() == 2) {
            return e(jds.c(str.substring(1), 10));
        }
        if (str.length() == 1) {
            return e(jds.c(str, 10));
        }
        return 0;
    }

    private int h(String str) {
        String[] split = str.split(Constants.LINK);
        int i = 0;
        if (split.length < 2) {
            return 0;
        }
        String str2 = split[0];
        String str3 = split[1];
        String substring = str2.length() > 0 ? str2.substring(str2.length() - 1) : null;
        String substring2 = str3.length() > 0 ? str3.substring(str3.length() - 1) : null;
        int c = jds.c(substring, 10);
        int c2 = jds.c(substring2, 10);
        if (c < 0 || c2 < 0) {
            return 0;
        }
        if (c > c2) {
            return d(c, c2);
        }
        while (c <= c2) {
            i += e(c);
            c++;
        }
        return i;
    }

    private int d(int i, int i2) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i3 = 1; i3 < i2 + 1; i3++) {
            stringBuffer.append("W").append(Integer.toString(i3)).append(",");
        }
        while (i < 7) {
            stringBuffer.append("W").append(Integer.toString(i)).append(",");
            i++;
        }
        stringBuffer.append("W").append(Integer.toString(7));
        return f(stringBuffer.toString());
    }

    private int f(String str) {
        int i = 0;
        for (String str2 : str.split(",")) {
            i += c(str2);
        }
        return i;
    }

    private void c(JSONObject jSONObject, kfj kfjVar) {
        if (CommonUtil.m(this.b, kfjVar.d()) != 13) {
            LogUtil.h("HwControlLocalAppHelper", "processTimer commandCode != 13");
            return;
        }
        kfjVar.p(jSONObject.optString("timerCode"));
        String optString = jSONObject.optString("duration");
        if (TextUtils.isEmpty(optString)) {
            return;
        }
        kfjVar.t(optString);
    }

    public void b(JSONObject jSONObject, kfj kfjVar) {
        d(jSONObject, kfjVar);
        e(jSONObject, kfjVar);
        c(jSONObject, kfjVar);
        a(jSONObject, kfjVar);
        x(kfjVar);
    }

    private void a(JSONObject jSONObject, kfj kfjVar) {
        String optString = jSONObject.optString("settingItem");
        if (!TextUtils.isEmpty(optString)) {
            kfjVar.k(optString);
        }
        String optString2 = jSONObject.optString("optCode");
        if (!TextUtils.isEmpty(optString2)) {
            kfjVar.h(optString2);
        }
        String optString3 = jSONObject.optString("streamType");
        if (!TextUtils.isEmpty(optString3)) {
            kfjVar.g(optString3);
        }
        try {
            kfjVar.c(new JSONObject("{\"Exercise\":\"0\",\"Ringtone\":\"1\",\"Hivoice\":\"2\",\"Media\":\"1\"}"));
            kfjVar.a(new JSONObject("{\"number\":\"0\",\"level\":\"1\",\"percent\":\"2\",\"fraction\":\"3\",\"other\":\"4\"}"));
        } catch (JSONException unused) {
            LogUtil.b("HwControlLocalAppHelper", "processSystem JSONException, type dictionary init failed");
        }
        String optString4 = jSONObject.optString("optType");
        if (!TextUtils.isEmpty(optString4)) {
            kfjVar.f(optString4);
        }
        String[] strArr = {"number", "level", "percent", "fraction", "other"};
        for (int i = 0; i < 5; i++) {
            String optString5 = jSONObject.optString(strArr[i]);
            if (!TextUtils.isEmpty(optString5)) {
                kfjVar.v(strArr[i]);
                kfjVar.w(optString5);
                return;
            }
        }
    }

    private void d(JSONObject jSONObject, kfj kfjVar) {
        String optString = jSONObject.optString("length");
        if (!TextUtils.isEmpty(optString)) {
            kfjVar.j(optString);
        }
        String optString2 = jSONObject.optString("unit");
        if (!TextUtils.isEmpty(optString2)) {
            kfjVar.r(optString2);
        }
        String optString3 = jSONObject.optString("indoorOrNot");
        if (!TextUtils.isEmpty(optString3)) {
            kfjVar.i(optString3);
        }
        JSONObject optJSONObject = jSONObject.optJSONObject("typeDictionary");
        if (optJSONObject != null) {
            kfjVar.d(optJSONObject);
        }
        String optString4 = jSONObject.optString("distance");
        if (!TextUtils.isEmpty(optString4)) {
            kfjVar.a(optString4);
        }
        String optString5 = jSONObject.optString(ParsedFieldTag.KAKA_TASK_SCENARIO);
        if (!TextUtils.isEmpty(optString5)) {
            kfjVar.n(optString5);
        }
        String optString6 = jSONObject.optString("duration");
        if (!TextUtils.isEmpty(optString6) && CommonUtil.m(this.b, kfjVar.d()) != 13) {
            kfjVar.b(optString6);
        }
        String optString7 = jSONObject.optString("calorie");
        if (!TextUtils.isEmpty(optString7)) {
            kfjVar.e(optString7);
        }
        String optString8 = jSONObject.optString("trip");
        if (!TextUtils.isEmpty(optString8)) {
            kfjVar.q(optString8);
        }
        String optString9 = jSONObject.optString("type");
        if (!TextUtils.isEmpty(optString9)) {
            kfjVar.s(optString9);
        }
        String optString10 = jSONObject.optString("times");
        if (TextUtils.isEmpty(optString10)) {
            return;
        }
        kfjVar.l(optString10);
    }

    private void e(JSONObject jSONObject, kfj kfjVar) {
        ArrayList arrayList = new ArrayList(16);
        d(jSONObject, arrayList);
        kfj.d dVar = new kfj.d();
        String optString = jSONObject.optString("contactId");
        String optString2 = jSONObject.optString("phoneNumberId");
        if (!TextUtils.isEmpty(optString) && !TextUtils.isEmpty(optString2)) {
            dVar.b(optString);
            kfj.d a2 = kfl.a(this.b, optString, jds.c(optString2, 10));
            if (a2 != null) {
                dVar.d(a2.d());
                dVar.a(a2.b());
            }
        }
        String optString3 = jSONObject.optString("contactName");
        if (!TextUtils.isEmpty(optString3)) {
            dVar.a(optString3);
        }
        String optString4 = jSONObject.optString("phoneNumber");
        if (!TextUtils.isEmpty(optString4)) {
            dVar.d(optString4);
        }
        if (!TextUtils.isEmpty(dVar.d())) {
            arrayList.add(dVar);
        }
        if (arrayList.isEmpty()) {
            return;
        }
        kfjVar.c(arrayList);
    }

    private void d(JSONObject jSONObject, List<kfj.d> list) {
        JSONArray optJSONArray = jSONObject.optJSONArray("contactList");
        if (optJSONArray == null || optJSONArray.length() == 0) {
            LogUtil.h("HwControlLocalAppHelper", "processCallList contactList is null");
            return;
        }
        for (int i = 0; i < optJSONArray.length(); i++) {
            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                String optString = optJSONObject.optString("contactId");
                if (!TextUtils.isEmpty(optString)) {
                    kfj.d dVar = new kfj.d();
                    dVar.b(optString);
                    String optString2 = optJSONObject.optString("phoneNumberId");
                    if (!TextUtils.isEmpty(optString2)) {
                        kfj.d a2 = kfl.a(this.b, optString, jds.c(optString2, 10));
                        if (a2 != null) {
                            dVar.d(a2.d());
                            dVar.a(a2.b());
                        }
                    }
                    String optString3 = optJSONObject.optString("contactName");
                    if (!TextUtils.isEmpty(optString3)) {
                        dVar.a(optString3);
                    }
                    if (!TextUtils.isEmpty(dVar.b()) && !TextUtils.isEmpty(dVar.d())) {
                        list.add(dVar);
                    }
                }
            }
        }
    }

    public void a() {
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>(10);
        linkedHashMap.put("type", "1");
        OpAnalyticsUtil.getInstance().setEvent2nd(OperationKey.HEALTH_APP_VOICE_ASSISTANT_2190001.value(), linkedHashMap);
        LogUtil.a("HwControlLocalAppHelper", "mKitRecognizeListener onInit BI");
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x002d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void a(java.lang.String r5) {
        /*
            r4 = this;
            java.lang.String r0 = "HwControlLocalAppHelper"
            java.lang.String r1 = ""
            org.json.JSONObject r2 = new org.json.JSONObject     // Catch: org.json.JSONException -> L1c
            r2.<init>(r5)     // Catch: org.json.JSONException -> L1c
            java.lang.String r5 = "contexts"
            org.json.JSONArray r5 = r2.optJSONArray(r5)     // Catch: org.json.JSONException -> L1c
            if (r5 == 0) goto L25
            int r2 = r5.length()     // Catch: org.json.JSONException -> L1c
            if (r2 <= 0) goto L25
            java.lang.String r5 = defpackage.kfl.d(r5)     // Catch: org.json.JSONException -> L1c
            goto L26
        L1c:
            java.lang.String r5 = "processOnResult JSONException"
            java.lang.Object[] r5 = new java.lang.Object[]{r5}
            com.huawei.hwlogsmodel.LogUtil.b(r0, r5)
        L25:
            r5 = r1
        L26:
            boolean r2 = android.text.TextUtils.isEmpty(r5)
            if (r2 == 0) goto L2d
            goto L2e
        L2d:
            r1 = r5
        L2e:
            java.lang.String r5 = "domainCode:"
            java.lang.Object[] r5 = new java.lang.Object[]{r5, r1}
            com.huawei.hwlogsmodel.LogUtil.a(r0, r5)
            java.util.LinkedHashMap r5 = new java.util.LinkedHashMap
            r2 = 10
            r5.<init>(r2)
            java.lang.String r2 = "type"
            java.lang.String r3 = "2"
            r5.put(r2, r3)
            java.lang.String r2 = "domainCode"
            r5.put(r2, r1)
            com.huawei.operation.OpAnalyticsUtil r1 = com.huawei.operation.OpAnalyticsUtil.getInstance()
            com.huawei.operation.OperationKey r2 = com.huawei.operation.OperationKey.HEALTH_APP_VOICE_ASSISTANT_2190001
            java.lang.String r2 = r2.value()
            r1.setEvent2nd(r2, r5)
            java.lang.String r5 = "censusDomain censusDomainBI "
            java.lang.Object[] r5 = new java.lang.Object[]{r5}
            com.huawei.hwlogsmodel.LogUtil.a(r0, r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hwdevice.phoneprocess.mgr.hwvoicekitmgr.HwControlLocalAppHelper.a(java.lang.String):void");
    }

    public void d(long j) {
        if (j < -2147483648L || j > 2147483647L) {
            LogUtil.h("Integer overflow", new Object[0]);
            return;
        }
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>(10);
        linkedHashMap.put("type", "3");
        linkedHashMap.put("duration", String.valueOf(j));
        OpAnalyticsUtil.getInstance().setEvent2nd(OperationKey.HEALTH_APP_VOICE_ASSISTANT_2190001.value(), linkedHashMap);
        LogUtil.a("HwControlLocalAppHelper", "breakLinkVoice OpAnalyticsUtil spanInitToEndBI");
    }

    public static void e(String str) {
        int i = ("music.play".equals(str) || "music.resume".equals(str)) ? 1 : -1;
        if ("music.pause".equals(str)) {
            i = 2;
        }
        if ("music.prev".equals(str)) {
            i = 3;
        }
        if ("music.next".equals(str)) {
            i = 4;
        }
        LogUtil.h("HwControlLocalAppHelper", "processCommand command is：", Integer.valueOf(i));
        kfk.c(i);
    }
}
