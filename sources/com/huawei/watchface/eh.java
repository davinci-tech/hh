package com.huawei.watchface;

import android.net.Uri;
import android.os.SystemClock;
import android.text.TextUtils;
import androidx.core.util.Pair;
import com.huawei.hms.support.api.entity.pay.HwPayConstant;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.ui.main.stories.recommendcloud.constants.RecommendConstants;
import com.huawei.watchface.flavor.FlavorConfig;
import com.huawei.watchface.utils.ArrayUtils;
import com.huawei.watchface.utils.CommonUtils;
import com.huawei.watchface.utils.HwLog;
import com.huawei.watchface.utils.analytice.data.BaseEvent;
import com.huawei.watchface.utils.constants.WatchFaceConstant;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class eh extends BaseEvent<eh> {
    private static final String[] r = {"resultCode", "resultcode"};
    private static final String[] s = {"resultInfo", "resultinfo"};
    private static final String[] t = {HwPayConstant.KEY_SIGN, WatchFaceConstant.X_SIGN};

    /* renamed from: a, reason: collision with root package name */
    private Class f11007a;
    private String h;
    private String i;
    private String j;
    private Map<String, List<String>> l;
    private LinkedHashMap<String, String> m;
    private String n;
    private String p;
    private String q;
    private StringBuilder k = new StringBuilder();
    private StringBuilder o = new StringBuilder();

    private String[] f() {
        return (String[]) r.clone();
    }

    private String[] g() {
        return (String[]) s.clone();
    }

    @Override // com.huawei.watchface.em
    public LinkedHashMap<String, String> b() {
        return h();
    }

    public void a(Map<String, List<String>> map) {
        this.l = map;
    }

    public void a(Class cls) {
        this.f11007a = cls;
    }

    private LinkedHashMap<String, String> h() {
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
        try {
            b(linkedHashMap);
            c(linkedHashMap);
            a(this.i, linkedHashMap, "bodyParams:");
            d(linkedHashMap);
        } catch (Exception e) {
            HwLog.e("AnalyticsInterfaceEvent", "convertData e" + HwLog.printException(e));
        }
        linkedHashMap.put("totalTime", String.valueOf(this.c - this.b));
        linkedHashMap.put("startts", this.b + "");
        linkedHashMap.put("endts", this.c + "");
        StringBuilder sb = this.o;
        linkedHashMap.put(BaseEvent.KEY_DESCINFO, sb == null ? "" : sb.toString());
        StringBuilder sb2 = this.k;
        linkedHashMap.put("request", sb2 != null ? sb2.toString() : "");
        linkedHashMap.put("request_engine", dg.b(this.l, "request_engine", "url_connection"));
        linkedHashMap.put("report_app_version", CommonUtils.getVersionCode() + "|" + CommonUtils.m());
        return linkedHashMap;
    }

    public eh d() {
        this.b = SystemClock.elapsedRealtime();
        return this;
    }

    public eh e() {
        this.c = SystemClock.elapsedRealtime();
        return this;
    }

    public eh a(String str) {
        if (this.o == null) {
            this.o = new StringBuilder();
        }
        if (!TextUtils.isEmpty(this.o)) {
            this.o.append('|');
        }
        this.o.append(str);
        return this;
    }

    public eh b(String str) {
        if (this.k == null) {
            this.k = new StringBuilder();
        }
        if (!TextUtils.isEmpty(this.k)) {
            this.k.append('|');
        }
        this.k.append(str);
        return this;
    }

    public void c(String str) {
        this.h = str;
    }

    public void d(String str) {
        this.i = str;
    }

    public void e(String str) {
        this.j = str;
    }

    public void a(LinkedHashMap<String, String> linkedHashMap) {
        this.m = linkedHashMap;
    }

    private void b(LinkedHashMap<String, String> linkedHashMap) {
        String str;
        String str2;
        String str3;
        if (TextUtils.isEmpty(this.h)) {
            return;
        }
        Uri parse = Uri.parse(this.h);
        String query = parse.getQuery();
        if (parse.isOpaque()) {
            str = "";
            str2 = str;
            str3 = str2;
        } else {
            str2 = CommonUtils.getAuthorityByUri(parse);
            str3 = parse.getScheme();
            str = parse.getPath();
        }
        String b = dg.b(this.l, "server_ip", "");
        if (TextUtils.isEmpty(b)) {
            try {
                InetAddress[] allByName = InetAddress.getAllByName(dj.a(this.h + this.i));
                StringBuilder sb = new StringBuilder("get all ips=");
                sb.append(Arrays.toString(allByName));
                FlavorConfig.safeHwLog("AnalyticsInterfaceEvent", sb.toString());
                InetAddress inetAddress = (InetAddress) ArrayUtils.a(allByName, 0);
                if (inetAddress != null) {
                    b = inetAddress.getHostAddress();
                }
            } catch (NullPointerException | UnknownHostException unused) {
            }
            b = "UnknownHost";
        }
        linkedHashMap.put("server_ip", b);
        linkedHashMap.put("protocal", str3);
        linkedHashMap.put("url", str2);
        linkedHashMap.put("interface_name", str);
        a(query, linkedHashMap, "urlParams:");
    }

    private void c(LinkedHashMap<String, String> linkedHashMap) {
        String str;
        ek[] ekVarArr;
        String[] strArr;
        LinkedHashMap<String, String> linkedHashMap2 = this.m;
        if (linkedHashMap2 != null) {
            str = linkedHashMap2.containsKey("x-intfpath") ? this.m.get("x-intfpath") : "no key";
            if (this.m.containsKey(WatchFaceConstant.X_CLIENTTRACEID)) {
                this.n = this.m.get(WatchFaceConstant.X_CLIENTTRACEID);
            }
            Class cls = this.f11007a;
            if (cls.isAnnotationPresent(el.class)) {
                el elVar = (el) cls.getAnnotation(el.class);
                ekVarArr = elVar.c();
                strArr = elVar.d();
            } else {
                ekVarArr = null;
                strArr = null;
            }
            if (!ArrayUtils.isEmpty(ekVarArr)) {
                for (ek ekVar : ekVarArr) {
                    if (this.m.containsKey(ekVar.a())) {
                        a(ekVar.a() + ':' + (ekVar.b() ? ep.a(this.m.get(ekVar.a())) : this.m.get(ekVar.a())));
                    }
                }
            }
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<String, String> entry : this.m.entrySet()) {
                if (ArrayUtils.b(strArr, entry.getKey())) {
                    sb.append(entry.getKey() + ':' + a(entry.getKey(), entry.getValue()) + ",");
                } else {
                    sb.append(entry.getKey() + ':' + entry.getValue() + ",");
                }
            }
            b("headerParams:" + ((Object) sb));
        } else {
            str = "empty";
        }
        linkedHashMap.put("trace_id", this.n);
        linkedHashMap.put("x-intfpath", str);
        HwLog.i("AnalyticsInterfaceEvent", "clzName: " + this.f11007a.getName() + " traceId: " + this.n);
    }

    private String a(String str, String str2) {
        if (ArrayUtils.b(t, str)) {
            return ep.b(str2);
        }
        return ep.a(str2);
    }

    private void a(String str, LinkedHashMap<String, String> linkedHashMap, String str2) {
        ek[] ekVarArr;
        String[] strArr;
        String[] strArr2;
        String str3;
        boolean z;
        String str4 = str;
        Class cls = this.f11007a;
        if (!TextUtils.isEmpty(str)) {
            if (cls.isAnnotationPresent(el.class)) {
                el elVar = (el) cls.getAnnotation(el.class);
                ekVarArr = elVar.b();
                strArr = elVar.e();
            } else {
                ekVarArr = new ek[0];
                strArr = new String[]{"userToken", HwPayConstant.KEY_RESERVEDINFOR, "licenseReq", "authCode", "deviceId", "userRefreshToken", HwPayConstant.KEY_SIGN, "phoneId", "publicKey"};
            }
            if (ArrayUtils.isEmpty(ekVarArr)) {
                strArr2 = null;
            } else {
                strArr2 = new String[ekVarArr.length];
                for (int i = 0; i < ekVarArr.length; i++) {
                    strArr2[i] = ekVarArr[i].a();
                }
            }
            Matcher matcher = dk.f10988a.matcher(str4);
            if (matcher.groupCount() > 1) {
                StringBuffer stringBuffer = new StringBuffer();
                z = false;
                while (matcher.find()) {
                    String group = matcher.group(1);
                    String group2 = matcher.group(2);
                    if (RecommendConstants.VER.equalsIgnoreCase(group)) {
                        this.q = group2;
                    }
                    if (ArrayUtils.a(group, strArr2)) {
                        linkedHashMap.put(group, group2);
                    }
                    String group3 = matcher.group();
                    if (!TextUtils.isEmpty(group3)) {
                        if (ArrayUtils.a(group, strArr)) {
                            group3 = group + '=' + a(group, group2);
                        }
                        matcher.appendReplacement(stringBuffer, group3);
                    }
                    z = true;
                }
                str3 = stringBuffer.toString();
            } else {
                str3 = str4;
                z = false;
            }
            if (!z) {
                try {
                    JSONObject jSONObject = new JSONObject(str4);
                    this.q = jSONObject.optString(RecommendConstants.VER);
                    if (!ArrayUtils.isEmpty(ekVarArr)) {
                        for (ek ekVar : ekVarArr) {
                            String optString = jSONObject.optString(ekVar.a());
                            if (!TextUtils.isEmpty(optString)) {
                                linkedHashMap.put(ekVar.a(), optString);
                            }
                        }
                    }
                    if (!ArrayUtils.isEmpty(strArr)) {
                        for (String str5 : strArr) {
                            String optString2 = jSONObject.optString(str5);
                            if (!TextUtils.isEmpty(optString2)) {
                                jSONObject.put(str5, a(str5, optString2));
                            }
                        }
                    }
                    str4 = jSONObject.toString();
                } catch (JSONException e) {
                    HwLog.i("AnalyticsInterfaceEvent", cls.getName() + " processUrlParams:" + HwLog.printException((Exception) e));
                }
            }
            str4 = str3;
        }
        b(str2 + str4);
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0173  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0183 A[EDGE_INSN: B:19:0x0183->B:20:0x0183 BREAK  A[LOOP:0: B:13:0x0171->B:16:0x0180], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0141  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x013e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void d(java.util.LinkedHashMap<java.lang.String, java.lang.String> r14) {
        /*
            Method dump skipped, instructions count: 400
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.watchface.eh.d(java.util.LinkedHashMap):void");
    }

    private String a(ek ekVar, JSONObject jSONObject) {
        if (TextUtils.isEmpty(ekVar.a()) || jSONObject == null) {
            return "";
        }
        String optString = jSONObject.optString(ekVar.a());
        return ekVar.b() ? ep.a(optString) : optString;
    }

    private Pair<Boolean, String[]> b(Class<?> cls) {
        if (cls == null) {
            return new Pair<>(false, null);
        }
        if (!cls.isAnnotationPresent(en.class)) {
            return new Pair<>(false, null);
        }
        return new Pair<>(true, ((en) Objects.requireNonNull((en) cls.getAnnotation(en.class))).a());
    }

    private String a(String[] strArr, JSONObject jSONObject) {
        JSONObject optJSONObject;
        String str = "";
        if (!ArrayUtils.isEmpty(strArr) && jSONObject != null) {
            for (String str2 : strArr) {
                if (str2.contains(Constants.LINK)) {
                    String[] split = str2.split(Constants.LINK);
                    if (ArrayUtils.a(split) == 2 && (optJSONObject = jSONObject.optJSONObject(split[0])) != null) {
                        str = optJSONObject.optString(split[1]);
                    }
                } else {
                    str = jSONObject.optString(str2);
                }
                if (!TextUtils.isEmpty(str)) {
                    break;
                }
            }
        }
        return str;
    }

    @Override // com.huawei.watchface.em
    public String a() {
        return "WATCHFACE_INTERFACE";
    }
}
