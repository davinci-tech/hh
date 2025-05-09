package com.huawei.watchface.mvp.model.helper.systemparam;

import android.text.TextUtils;
import com.huawei.watchface.api.HwWatchFaceApi;
import com.huawei.watchface.ar;
import com.huawei.watchface.bo;
import com.huawei.watchface.bp;
import com.huawei.watchface.d;
import com.huawei.watchface.dg;
import com.huawei.watchface.dp;
import com.huawei.watchface.environment.Environment;
import com.huawei.watchface.flavor.FlavorConfig;
import com.huawei.watchface.mvp.model.helper.GsonHelper;
import com.huawei.watchface.mvp.model.helper.systemparam.SystemParamInfo;
import com.huawei.watchface.utils.ArrayUtils;
import com.huawei.watchface.utils.BackgroundTaskUtils;
import com.huawei.watchface.utils.HwLog;
import com.huawei.watchface.utils.WatchFaceHttpUtil;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes7.dex */
public class NewSystemParamManager {

    /* renamed from: a, reason: collision with root package name */
    private static Set<String> f11081a = new CopyOnWriteArraySet();
    private static ConcurrentHashMap<String, String> b = new ConcurrentHashMap<>();
    private static ConcurrentHashMap<String, String> c = new ConcurrentHashMap<>();
    private static ConcurrentHashMap<String, Set<String>> d = new ConcurrentHashMap<>();
    private static final Map<String, String> e = new ConcurrentHashMap();
    private static CountDownLatch f = new CountDownLatch(1);
    private static LinkedBlockingQueue<a> g = new LinkedBlockingQueue<>();
    private static boolean h = false;
    private static final List<String> i = Arrays.asList("10000001001", "10000001002", "10000001003", "10000001004", "10000001005", "10000001006", "10000001008", "10000001009", "10000001010", "10000001011", "10000001012", "10000001013", "10000001333");
    private static final List<String> j = Arrays.asList("-1");
    private static final List<String> k = Arrays.asList("10000001118");
    private static String l = "0";
    private static boolean m = false;
    private static List<String> n;

    public interface a {
        void a(int i);
    }

    public static String a(String str) {
        return getSystemParam(str, null);
    }

    public static String getSystemParam(String str, String str2) {
        return TextUtils.isEmpty(str) ? "" : dp.b(str, "SystemParamFile", str2);
    }

    public static Set<String> b(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return dp.a(str, "SystemParamFile", (Set<String>) null);
    }

    public static String a(String str, String str2, boolean z) {
        if (TextUtils.isEmpty(str)) {
            return str2;
        }
        if (z) {
            Map<String, String> map = e;
            if (dg.a((Map<?, ?>) map, (Object) str)) {
                FlavorConfig.safeHwLog("NewSystemParamManager", "is cache get" + str);
                return dg.a(map, str, str2);
            }
            FlavorConfig.safeHwLog("NewSystemParamManager", "is sp get" + str);
            String b2 = dp.b(str, "SystemParamFile", str2);
            dg.a(map, str, b2);
            return b2;
        }
        dg.b(e, str);
        return dp.b(str, "SystemParamFile", str2);
    }

    public static boolean b(String str, String str2, boolean z) {
        if (TextUtils.isEmpty(str)) {
            HwLog.i("NewSystemParamManager", "parseBooleanParam TextUtils.isEmpty(paramName)");
            return z;
        }
        String a2 = a(str);
        return TextUtils.isEmpty(a2) ? z : TextUtils.equals(str2, a2);
    }

    public static List<String> c(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return ar.a(str);
    }

    public static void a(a aVar) {
        if (aVar == null) {
            return;
        }
        HwLog.i("NewSystemParamManager", "getNewSystemParam() isLoading: " + h);
        if (!h) {
            aVar.a(0);
        } else {
            g.add(aVar);
        }
    }

    public static void a() {
        HwLog.i("NewSystemParamManager", "startRequestSystemParam() enter.");
        f = new CountDownLatch(1);
        m = false;
        f11081a = new CopyOnWriteArraySet();
        b.clear();
        d.clear();
        AtomicInteger atomicInteger = new AtomicInteger(0);
        h = true;
        if (Environment.getApplicationContext() != null && HwWatchFaceApi.getInstance(Environment.getApplicationContext()).isOversea()) {
            n = i;
        } else {
            n = j;
        }
        HwLog.i("NewSystemParamManager", "startRequestSystemParam() start request whitelist");
        if (WatchFaceHttpUtil.b() != null) {
            b(atomicInteger);
        } else {
            HwLog.i("NewSystemParamManager", "startRequestSystemParam() watchFaceSignBean is null.");
            a(atomicInteger);
        }
    }

    public static void b() {
        HwLog.i("NewSystemParamManager", "startRequestPhoneTypeSystemParam() enter.");
        if (WatchFaceHttpUtil.c() != null) {
            e();
        } else {
            HwLog.i("NewSystemParamManager", "startRequestPhoneTypeSystemParam() watchFaceSignBean is null.");
            d();
        }
    }

    private static void a(final AtomicInteger atomicInteger) {
        HwLog.i("NewSystemParamManager", "getSignBeforeRequestParam watchFaceSignBean is null.");
        BackgroundTaskUtils.submit(new Runnable() { // from class: com.huawei.watchface.mvp.model.helper.systemparam.NewSystemParamManager$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                NewSystemParamManager.c(atomicInteger);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void c(AtomicInteger atomicInteger) {
        bo boVar = new bo();
        if (boVar.c(boVar.c()) == null) {
            HwLog.w("NewSystemParamManager", "getSignBeforeRequestParam() watchFaceSignBean is null.");
        } else {
            b(atomicInteger);
        }
    }

    private static void d() {
        HwLog.i("NewSystemParamManager", "getSignBeforeRequestParam watchFaceSignBean is null.");
        BackgroundTaskUtils.submit(new Runnable() { // from class: com.huawei.watchface.mvp.model.helper.systemparam.NewSystemParamManager$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                NewSystemParamManager.f();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void f() {
        bo boVar = new bo();
        boVar.a(1);
        if (boVar.c(boVar.c()) == null) {
            HwLog.w("NewSystemParamManager", "getSignBeforeRequestParam() watchFaceSignBean is null.");
        } else {
            e();
        }
    }

    private static void e() {
        Iterator<String> it = k.iterator();
        while (it.hasNext()) {
            e(it.next());
        }
    }

    private static void b(AtomicInteger atomicInteger) {
        if (ArrayUtils.isEmpty(n) || atomicInteger == null) {
            HwLog.e("NewSystemParamManager", "requestParamByCodeList() codeList is null.");
            return;
        }
        Iterator<String> it = n.iterator();
        while (it.hasNext()) {
            a(it.next(), atomicInteger);
        }
    }

    private static void a(final String str, final AtomicInteger atomicInteger) {
        if (str == null || atomicInteger == null) {
            return;
        }
        BackgroundTaskUtils.submit(new Runnable() { // from class: com.huawei.watchface.mvp.model.helper.systemparam.NewSystemParamManager.1
            @Override // java.lang.Runnable
            public void run() {
                NewSystemParamManager.c(NewSystemParamManager.f(str));
                HwLog.i("NewSystemParamManager", "requestParamByCode() code: " + str);
                NewSystemParamManager.b(atomicInteger.incrementAndGet());
            }
        });
    }

    private static void e(final String str) {
        if (str == null) {
            return;
        }
        BackgroundTaskUtils.submit(new Runnable() { // from class: com.huawei.watchface.mvp.model.helper.systemparam.NewSystemParamManager$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                NewSystemParamManager.h(str);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void h(String str) {
        HwLog.i("NewSystemParamManager", "requestPhoneTypeParamByCode() code: " + str);
        SystemParamInfo g2 = g(str);
        HwLog.iBetaLog("NewSystemParamManager", "requestPhoneTypeParamByCode systemParamInfo:" + GsonHelper.toJson(g2));
        b(g2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static SystemParamInfo f(String str) {
        bp bpVar = new bp();
        bpVar.b(str);
        return bpVar.c(bpVar.a(""));
    }

    private static SystemParamInfo g(String str) {
        bp bpVar = new bp();
        bpVar.a(1);
        bpVar.b(str);
        return bpVar.c(bpVar.a(""));
    }

    private static void b(SystemParamInfo systemParamInfo) {
        HwLog.i("NewSystemParamManager", "parsePhoneTypeParam");
        if (systemParamInfo == null || !l.equals(systemParamInfo.getResultCode())) {
            HwLog.i("NewSystemParamManager", "mIsRequestFail = true ");
            return;
        }
        List<SystemParamInfo.SystemParam> list = systemParamInfo.getList();
        if (ArrayUtils.isEmpty(list)) {
            dp.a("client_grey_watchface_site", "", "SystemParamFile");
            return;
        }
        boolean z = false;
        for (SystemParamInfo.SystemParam systemParam : list) {
            if (!TextUtils.isEmpty(systemParam.getParamName()) && TextUtils.equals(systemParam.getParamName(), "client_grey_watchface_site")) {
                HwLog.i("NewSystemParamManager", "parsePhoneTypeParam name:" + systemParam.getParamName() + ",content:" + systemParam.getParamContent());
                if (systemParam.getParamContent() != null) {
                    c.put(systemParam.getParamName(), systemParam.getParamContent().trim());
                }
                dp.a(c, "SystemParamFile");
                z = true;
            }
        }
        if (z) {
            return;
        }
        dp.a("client_grey_watchface_site", "", "SystemParamFile");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void c(SystemParamInfo systemParamInfo) {
        HwLog.i("NewSystemParamManager", "parseParam");
        if (systemParamInfo == null || !l.equals(systemParamInfo.getResultCode())) {
            HwLog.i("NewSystemParamManager", "mIsRequestFail = true ");
            m = true;
            return;
        }
        List<SystemParamInfo.SystemParam> list = systemParamInfo.getList();
        if (ArrayUtils.isEmpty(list)) {
            return;
        }
        HwLog.i("NewSystemParamManager", "parseParam -- getList");
        d.a().e();
        d.a().b();
        for (SystemParamInfo.SystemParam systemParam : list) {
            if (!TextUtils.isEmpty(systemParam.getParamName())) {
                f11081a.add(systemParam.getParamName());
                if (ar.b(systemParam.getParamName())) {
                    ar.a(systemParam.getParamName(), systemParam.getParamContent());
                } else {
                    b.put(systemParam.getParamName(), systemParam.getParamContent());
                    if (TextUtils.equals(systemParam.getParamCode(), "10000001333")) {
                        d.a().a(systemParam.getParamName(), systemParam.getParamContent());
                    }
                }
            }
        }
        for (String str : ar.f10905a) {
            List<String> a2 = ar.a(str);
            if (!ArrayUtils.isEmpty(a2)) {
                d.put(str, new HashSet(a2));
            }
        }
        FlavorConfig.addAodTestData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(int i2) {
        if (i2 >= n.size()) {
            HwLog.i("NewSystemParamManager", "caculateIsLoadFinish mIsRequestFail = " + m);
            if (!m) {
                dp.a(b, "SystemParamFile");
                dp.a(d, "SystemParamFile");
                dp.a("paramNameList", f11081a, "SystemParamFile");
            }
            b.clear();
            d.clear();
            dg.b(e);
            h = false;
            f.countDown();
            BackgroundTaskUtils.postInMainThread(new Runnable() { // from class: com.huawei.watchface.mvp.model.helper.systemparam.NewSystemParamManager.2
                @Override // java.lang.Runnable
                public void run() {
                    for (a aVar = (a) NewSystemParamManager.g.poll(); aVar != null; aVar = (a) NewSystemParamManager.g.poll()) {
                        aVar.a(0);
                    }
                }
            });
        }
    }
}
