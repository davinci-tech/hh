package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hihealth.util.HiScopeUtil;
import com.huawei.hms.framework.common.hianalytics.WiseOpenHianalyticsData;
import com.huawei.hms.network.embedded.r3;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.OpAnalyticsUtil;
import com.huawei.operation.OperationKey;
import health.compact.a.CommonUtil;
import health.compact.a.Sha256;
import health.compact.a.Utils;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes7.dex */
public class irc {
    private static final Map<String, Integer> d;

    /* renamed from: a, reason: collision with root package name */
    private long f13551a;
    private long b;
    private Context c;
    private iqy e;

    static {
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap(3);
        d = concurrentHashMap;
        concurrentHashMap.put("cn.xuexi.android", 0);
        concurrentHashMap.put("com.xunmeng.pinduoduo", 0);
        concurrentHashMap.put("com.eg.android.AlipayGphone", 0);
    }

    public irc() {
        this.b = System.currentTimeMillis();
    }

    public irc(Context context, iqy iqyVar) {
        this();
        this.c = context;
        this.e = iqyVar;
    }

    public iqy c() {
        return this.e;
    }

    public void d() {
        iqy iqyVar;
        Context context = this.c;
        if (context != null && (iqyVar = this.e) != null) {
            c(context, iqyVar);
        } else {
            LogUtil.h("HiHealthKitDotUtil", "report error");
        }
    }

    public void c(List<Integer> list, List<Integer> list2) {
        Iterator<Integer> it = list.iterator();
        while (it.hasNext()) {
            this.e.e(String.valueOf(it.next().intValue()));
            c(this.c, this.e);
        }
        Iterator<Integer> it2 = list2.iterator();
        while (it2.hasNext()) {
            int intValue = it2.next().intValue();
            this.e.b(4);
            this.e.e(String.valueOf(intValue));
            c(this.c, this.e);
        }
    }

    public void c(final Context context, final iqy iqyVar) {
        if (context == null) {
            LogUtil.h("HiHealthKitDotUtil", "apiEventReport:context is null");
            return;
        }
        String a2 = iqyVar.a();
        if (!TextUtils.isEmpty(a2) && a2.startsWith("dkplugin")) {
            LogUtil.a("HiHealthKitDotUtil", "twin app, change packageName");
            iqyVar.a("dkplugin");
        }
        jdx.b(new Runnable() { // from class: irb
            @Override // java.lang.Runnable
            public final void run() {
                irc.this.d(iqyVar, context);
            }
        });
    }

    /* synthetic */ void d(iqy iqyVar, Context context) {
        boolean o = Utils.o();
        iqyVar.d(iqz.c(iqyVar.a(), o));
        a(context, iqyVar, o);
        a(iqyVar);
        if (b(iqyVar)) {
            e(context, iqyVar, OperationKey.HEALTH_SDK_EXTENDED_HEALTH_KIT_88010001.value());
        }
    }

    private void a(iqy iqyVar) {
        if (e(iqyVar)) {
            Map<String, Integer> map = d;
            int intValue = map.get(iqyVar.a()).intValue() + 1;
            map.put(iqyVar.a(), Integer.valueOf(intValue <= 10 ? intValue : 1));
            r1 = intValue;
        }
        iqyVar.e(r1);
    }

    private boolean e(iqy iqyVar) {
        return iqyVar.a() != null && d.containsKey(iqyVar.a()) && String.valueOf(40002).equals(iqyVar.d()) && "execQuery".equals(iqyVar.b()) && iqyVar.g() == 0;
    }

    private boolean b(iqy iqyVar) {
        return !e(iqyVar) || d.get(iqyVar.a()).intValue() >= 10;
    }

    private void a(Context context, iqy iqyVar, boolean z) {
        LogUtil.a("HiHealthKitDotUtil", "tickBiWhenInvoke");
        this.f13551a = System.currentTimeMillis();
        String value = AnalyticsValue.HI_HEALTH_KIT_API_INVOKE_2150100.value();
        if (z) {
            value = "60000";
        }
        HashMap hashMap = new HashMap(16);
        if (!TextUtils.isEmpty(iqyVar.c()) && iqyVar.c().matches("^[0-9]*$")) {
            hashMap.put("appId", iqyVar.c());
        } else {
            hashMap.put("package", iqyVar.a());
        }
        if (!TextUtils.isEmpty(iqyVar.d())) {
            hashMap.put("dataType", iqyVar.d());
        }
        hashMap.put("version", iqyVar.f());
        hashMap.put("service", "hiHealthKit");
        hashMap.put("apiName", iqyVar.b());
        hashMap.put("result", String.valueOf(iqyVar.g()));
        hashMap.put(WiseOpenHianalyticsData.UNION_COSTTIME, String.valueOf(this.f13551a - this.b));
        ixx.d().d(context, value, hashMap, 0);
    }

    private void e(Context context, iqy iqyVar, String str) {
        String str2;
        LogUtil.a("HiHealthKitDotUtil", "setOpAnalyticsEvent");
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>(16);
        linkedHashMap.put("package", iqyVar.a());
        linkedHashMap.put("version", iqyVar.f());
        linkedHashMap.put("appVersion", CommonUtil.e(context));
        if (!TextUtils.isEmpty(iqyVar.c())) {
            str2 = iqyVar.c();
        } else if (!TextUtils.isEmpty(HiScopeUtil.c(context, iqyVar.a()))) {
            str2 = HiScopeUtil.c(context, iqyVar.a());
        } else {
            LogUtil.a("HiHealthKitDotUtil", "appId is null");
            str2 = null;
        }
        linkedHashMap.put("appId", str2);
        linkedHashMap.put("service", "HealthCore");
        linkedHashMap.put("apiName", iqyVar.b());
        linkedHashMap.put("result", String.valueOf(iqyVar.g()));
        linkedHashMap.put(WiseOpenHianalyticsData.UNION_COSTTIME, String.valueOf(this.f13551a - this.b));
        linkedHashMap.put("callTime", String.valueOf(this.b));
        linkedHashMap.put(r3.y, c(CommonUtil.l(context)));
        linkedHashMap.put("udid", Sha256.e(CommonUtil.a(context, false), "SHA-256"));
        linkedHashMap.put("country", CommonUtil.c());
        linkedHashMap.put("dataType", iqyVar.d());
        linkedHashMap.put("count", String.valueOf(iqyVar.e()));
        OpAnalyticsUtil.getInstance().setEvent2nd(str, linkedHashMap);
    }

    public void d(Context context, iqy iqyVar) {
        LogUtil.a("HiHealthKitDotUtil", "callWearKit");
        if (context == null) {
            LogUtil.b("HiHealthKitDotUtil", "callWearKit context null");
            return;
        }
        this.f13551a = System.currentTimeMillis();
        HashMap hashMap = new HashMap(16);
        hashMap.put("package", iqyVar.a());
        hashMap.put("version", iqyVar.f());
        hashMap.put("service", "wearKit");
        hashMap.put("apiName", iqyVar.b());
        hashMap.put("result", String.valueOf(iqyVar.g()));
        hashMap.put(WiseOpenHianalyticsData.UNION_COSTTIME, String.valueOf(this.f13551a - this.b));
        ixx.d().d(context, AnalyticsValue.WEAR_KIT_API_INVOKE_2150101.value(), hashMap, 0);
        iqyVar.e(1);
        e(context, iqyVar, OperationKey.HEALTH_SDK_WEAR_KIT_88010002.value());
    }

    private String c(int i) {
        return i != 1 ? i != 2 ? i != 3 ? i != 4 ? i != 5 ? "unknown" : "5G" : "4G" : "3G" : "2G" : "wifi";
    }
}
