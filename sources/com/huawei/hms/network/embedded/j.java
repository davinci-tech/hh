package com.huawei.hms.network.embedded;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.hms.framework.common.BundleUtil;
import com.huawei.hms.framework.common.ContextHolder;
import com.huawei.hms.framework.common.ExecutorsUtils;
import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.framework.common.PLSharedPreferences;
import com.huawei.hms.framework.common.ReflectionUtils;
import com.huawei.hms.framework.common.StringUtils;
import com.huawei.hms.framework.common.check.ProviderCheckUtil;
import com.huawei.hms.framework.common.hianalytics.HianalyticsHelper;
import com.huawei.hms.framework.common.hianalytics.InitReport;
import com.huawei.hms.network.abtest.ABHelper;
import com.huawei.hms.network.conf.api.ConfigAPI;
import com.huawei.hms.network.inner.api.PolicyNetworkService;
import com.huawei.hms.network.inner.utils.CheckConfigUtils;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public class j {
    public static final String h = "ConfigManager";
    public static final String i = "content://com.huawei.hms.contentprovider/com.huawei.hms.networkkit/remoteconfig";
    public static final String j = "retCode";
    public static final String k = "retDesc";
    public static final int l = 100301;
    public static final String m = "getConfig";
    public static final String n = "remote_configs";
    public static final int o = 5000;
    public static final int p = 10000;
    public static final int q = 500;
    public static final String u = "_";
    public static final String v = "get_config_time";
    public static final String w = "get_ab_time";
    public static final String y = "share_pre_config";

    /* renamed from: a, reason: collision with root package name */
    public Map<String, m> f5312a;
    public Map<String, Object> b;
    public String c;
    public String d;
    public ExecutorService e;
    public PLSharedPreferences f;
    public volatile CountDownLatch g;
    public static final String s = "profile_base";
    public static final String r = "profile_restful";
    public static final String t = "profile_filemanager";
    public static final List<String> x = Collections.unmodifiableList(Arrays.asList(s, r, t));

    public static class d {

        /* renamed from: a, reason: collision with root package name */
        public static j f5316a = new j(null);
    }

    public void b(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.f.putString(i.f, str);
    }

    /* JADX WARN: Can't wrap try/catch for region: R(13:0|1|(2:(1:52)(1:54)|53)|5|(3:6|7|(1:10))|12|(1:14)|15|16|(2:18|(3:20|(4:23|(3:25|26|27)(1:29)|28|21)|30))(1:45)|31|(2:38|(2:43|44)(1:42))(2:35|36)|(1:(0))) */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x007c, code lost:
    
        a("sharedPreferences get failed", com.huawei.hms.network.embedded.i.h);
        r0 = null;
     */
    /* JADX WARN: Removed duplicated region for block: B:14:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0087  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00d6  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00e6  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00c5  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void a(android.content.Context r7) {
        /*
            Method dump skipped, instructions count: 261
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.network.embedded.j.a(android.content.Context):void");
    }

    public Object a(String str) {
        q qVar;
        if (str == null || str.length() == 0) {
            return null;
        }
        Logger.d(h, "getValue:" + str);
        if (str.contains("|")) {
            String[] split = str.split("\\|");
            if (split.length != 2) {
                return null;
            }
            if (TextUtils.equals(split[0], PolicyNetworkService.ProfileConstants.RESTFUL)) {
                str = split[1];
                qVar = q.RESTFUL;
            } else if (TextUtils.equals(split[0], PolicyNetworkService.ProfileConstants.FILE_MANAGER)) {
                str = split[1];
                qVar = q.FILE_MANAGER;
            } else {
                str = split[1];
            }
            return c(str, qVar);
        }
        qVar = q.DEFAULT;
        return c(str, qVar);
    }

    public Bundle a(Context context, String str, String str2, Bundle bundle) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        Uri parse = Uri.parse(i);
        if (!ProviderCheckUtil.isValid(parse)) {
            Logger.w(h, "package name is invalid");
            return null;
        }
        bundle.putString(u3.p, "content://com.huawei.hms.networkkit");
        bundle.putString("appversion", this.d);
        try {
            return context.getContentResolver().call(parse, str, str2, bundle);
        } catch (RuntimeException unused) {
            Logger.e(h, "remote config base service kit not exist ");
            Bundle bundle2 = new Bundle();
            bundle2.putInt("retCode", i.g);
            bundle2.putString(k, "cross process call failed");
            return bundle2;
        }
    }

    private void j() {
        this.f.putLong(v, System.currentTimeMillis());
    }

    private void i() {
        Object value = ConfigAPI.getValue(PolicyNetworkService.GlobalConstants.AB_EXPIRED_TIME);
        if (value == null) {
            return;
        }
        ABHelper.getInstance().setAbExpiredTime(StringUtils.stringToLong(String.valueOf(value), 0L));
    }

    private boolean h() {
        Object c2 = c(k.e);
        if (c2 instanceof String) {
            return Boolean.parseBoolean((String) c2);
        }
        if (c2 instanceof Boolean) {
            return ((Boolean) c2).booleanValue();
        }
        return false;
    }

    private boolean g() {
        Object value;
        Object obj = this.b.get(v);
        return obj == null || (value = ConfigAPI.getValue(PolicyNetworkService.GlobalConstants.CONFIG_EXPIRED_TIME)) == null || ((Long) obj).longValue() + (a(value) * 1000) <= System.currentTimeMillis();
    }

    private boolean f() {
        return this.f.getLong(w, -1L) == ABHelper.getInstance().getLastUpdateAbTime();
    }

    public static j e() {
        return d.f5316a;
    }

    private JSONObject d(String str) {
        try {
            return TextUtils.isEmpty(str) ? new JSONObject() : new JSONObject(str);
        } catch (JSONException unused) {
            Logger.e(h, "call method stringToJson occur JSONException");
            return null;
        }
    }

    private Object d(String str, q qVar) {
        String lowerCase = str.toLowerCase(Locale.ENGLISH);
        if (!q.DEFAULT.name().equals(qVar.name()) && this.g != null && this.g.getCount() > 0) {
            try {
                try {
                    if (!this.g.await(1L, TimeUnit.SECONDS)) {
                        Logger.w(h, "Wait timeout!");
                    }
                } catch (InterruptedException unused) {
                    Logger.e(h, "InterruptedException,countDownLatch await error !");
                }
            } finally {
                this.g.countDown();
            }
        }
        if (!TextUtils.equals("core_configversion", lowerCase) && h() && d() >= 100301) {
            return b(lowerCase, qVar);
        }
        return c(lowerCase);
    }

    private int d() {
        String str = (String) this.b.get("core_configversion");
        if (str == null) {
            return 0;
        }
        String[] split = str.split("\\.");
        StringBuilder sb = new StringBuilder();
        for (String str2 : split) {
            sb.append(str2);
        }
        return Integer.parseInt(sb.toString());
    }

    private void c(Bundle bundle) {
        this.f5312a.clear();
        this.f5312a.put(s, new n(l.REMOTE_AGC));
        this.f5312a.put(r, new p(l.REMOTE_AGC));
        this.f5312a.put(t, new o(l.REMOTE_AGC));
        if (h()) {
            for (String str : x) {
                if (this.b.containsKey(str)) {
                    a(this.f5312a.get(str), (String) this.b.get(str));
                    this.b.remove(str);
                }
            }
            Map<String, m> map = this.f5312a;
            if (map == null || map.size() <= 0) {
                return;
            }
            a();
        }
    }

    private void c() {
        String string = this.f.getString(i.f);
        String string2 = this.f.getString(n);
        long j2 = this.f.getLong(v, -1L);
        long j3 = this.f.getLong(w, -1L);
        this.f.clear();
        b(string);
        this.f.putString(n, string2);
        this.f.putLong(v, j2);
        this.f.putLong(w, j3);
    }

    private Object c(String str, q qVar) {
        try {
            return d(str.toLowerCase(Locale.ENGLISH), qVar);
        } catch (Exception unused) {
            Logger.e(h, "search configs occurs error, return default config");
            return null;
        }
    }

    private Object c(String str) {
        return this.b.get(str);
    }

    private void b(Bundle bundle) {
        this.b.clear();
        for (String str : k.b().a()) {
            Object obj = bundle.get(str);
            if (obj != null && CheckConfigUtils.checkIsCorrect(str, obj)) {
                this.b.put(str, obj);
                Logger.d(h, "put independentConfigs key: %s, value: %s", str, obj);
            }
        }
        Map<String, Object> map = this.b;
        if (map == null || map.size() == 0) {
            return;
        }
        b();
        try {
            for (String str2 : this.b.keySet()) {
                this.f.putString(str2, String.valueOf(this.b.get(str2)));
            }
        } catch (RuntimeException | Exception unused) {
            a("sharedPreferences put failed", i.i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(Context context) {
        Bundle a2;
        boolean z = true;
        boolean z2 = false;
        boolean z3 = ReflectionUtils.isAbTestEnable() && ABHelper.getInstance().isInAllowList(String.valueOf(ConfigAPI.getValue(PolicyNetworkService.GlobalConstants.AB_ALLOWED_LIST)));
        boolean g = g();
        if (z3) {
            ABHelper.getInstance().init();
            i();
            z2 = ABHelper.getInstance().isExpired();
            z = f();
        }
        Logger.i(h, "isAbTestEnable:%s, isRemoteConfigExpired:%s, isAbConfigExpired:%s", Boolean.valueOf(z3), Boolean.valueOf(g), Boolean.valueOf(z2));
        if (!a(z3, g, z2, z)) {
            Logger.i(h, "config is not expired");
            this.g.countDown();
            return;
        }
        if (g) {
            Logger.i(h, "update configs");
            a2 = a(context, m, this.c, (Bundle) null);
            this.f.putString(n, BundleUtil.bundleToJson(a2));
            j();
        } else {
            a2 = BundleUtil.jsonToBundle(this.f.getString(n));
        }
        if (a2 == null) {
            a2 = new Bundle();
        }
        Logger.v(h, "remoteConfigs:%s", a2);
        if (z3) {
            Map abParamsMap = ABHelper.getInstance().getAbParamsMap();
            Logger.v(h, "abConfigs:%s", abParamsMap);
            for (String str : abParamsMap.keySet()) {
                a2.putString(str, (String) abParamsMap.get(str));
            }
            Logger.v(h, "after the merger remoteConfigs:%s", a2);
            this.f.putLong(w, ABHelper.getInstance().getLastUpdateAbTime());
        }
        c();
        b(a2);
        c(a2);
        this.g.countDown();
        a(a2);
        if (z3 && z2) {
            InitReport.executeDelay(new b());
        }
    }

    private void b() {
        Object obj = this.b.get("core_connect_timeout");
        Object obj2 = this.b.get("core_concurrent_connect_delay");
        if (obj == null || obj2 == null) {
            return;
        }
        int i2 = 10000;
        int stringToInteger = StringUtils.stringToInteger(String.valueOf(obj), 10000);
        int i3 = 500;
        int stringToInteger2 = StringUtils.stringToInteger(String.valueOf(obj2), 500);
        if (stringToInteger2 >= stringToInteger) {
            Logger.d(h, "concurrent_connect_delay :" + stringToInteger2 + ",is not less than connect_timeout: " + stringToInteger + ",reset the two values to the default values");
        } else {
            i2 = stringToInteger;
            i3 = stringToInteger2;
        }
        this.b.put("core_connect_timeout", Integer.valueOf(i2));
        this.b.put("core_concurrent_connect_delay", Integer.valueOf(i3));
    }

    private Object b(String str, q qVar) {
        return a(str, qVar);
    }

    private boolean a(boolean z, boolean z2, boolean z3, boolean z4) {
        String str;
        if (z2) {
            str = "remote config expired";
        } else if (z && z3) {
            str = "ab config expired";
        } else {
            if (!z || z4) {
                return false;
            }
            str = "ab config has not merges";
        }
        Logger.d(h, str);
        return true;
    }

    private void a(String str, int i2) {
        Logger.e(h, str);
        Bundle bundle = new Bundle();
        bundle.putInt("retCode", i2);
        bundle.putString(k, str);
        a(bundle);
    }

    private void a(m mVar, String str) {
        Object obj;
        Logger.d(h, "parseJsonStr: %s", str);
        JSONObject d2 = d(str);
        if (d2 != null) {
            Iterator<String> keys = d2.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                try {
                    obj = d2.get(next);
                } catch (JSONException e2) {
                    Logger.e(h, "JSONException: %s", e2.getMessage());
                    obj = null;
                }
                if (next.startsWith(k.f5330a)) {
                    next = next.substring(11);
                }
                if (CheckConfigUtils.checkIsCorrect(next, obj)) {
                    mVar.a(next, obj);
                    Logger.d(h, "put key: %s, value: %s", next, obj);
                }
            }
        }
    }

    private void a(Bundle bundle) {
        if (HianalyticsHelper.getInstance().isEnableReport(ContextHolder.getAppContext())) {
            i iVar = new i();
            iVar.put("error_code", bundle.getInt("retCode"));
            iVar.put(i.d, bundle.getString(k));
            iVar.put("config_version", bundle.getString("core_configversion"));
            String string = this.f.getString(i.f);
            if (!TextUtils.isEmpty(string)) {
                iVar.put(i.f, string);
            }
            InitReport.reportWhenInit(new c(iVar));
            Logger.d(h, "add to init report ConfigHianalyticsData：" + iVar.toString());
        }
    }

    private void a() {
        for (String str : x) {
            m mVar = this.f5312a.get(str);
            Map<String, Object> a2 = mVar.a();
            Object obj = a2.get("core_connect_timeout");
            Object obj2 = a2.get("core_concurrent_connect_delay");
            if (obj != null && obj2 != null) {
                int i2 = 10000;
                int stringToInteger = StringUtils.stringToInteger(String.valueOf(obj), 10000);
                int i3 = 500;
                int stringToInteger2 = StringUtils.stringToInteger(String.valueOf(obj2), 500);
                if (stringToInteger2 >= stringToInteger) {
                    Logger.d(h, "concurrent_connect_delay :" + stringToInteger2 + ",is not less than connect_timeout: " + stringToInteger + ",reset the two values to the default values");
                } else {
                    i3 = stringToInteger2;
                    i2 = stringToInteger;
                }
                mVar.a("core_connect_timeout", Integer.valueOf(i2));
                mVar.a("core_concurrent_connect_delay", Integer.valueOf(i3));
            }
            this.f5312a.put(str, mVar);
        }
    }

    private Object a(String str, String str2) {
        if (this.f5312a.get(str).d()) {
            return null;
        }
        return this.f5312a.get(str).a(str2);
    }

    private Object a(String str, q qVar) {
        String str2;
        int i2 = a.f5313a[qVar.ordinal()];
        if (i2 == 1) {
            str2 = r;
        } else if (i2 == 2) {
            str2 = t;
        } else {
            if (i2 != 3) {
                return null;
            }
            str2 = s;
        }
        return a(str2, str);
    }

    private long a(Object obj) {
        return StringUtils.stringToLong(String.valueOf(obj), 0L);
    }

    public class b implements Runnable {
        @Override // java.lang.Runnable
        public void run() {
            ABHelper.getInstance().fetchABInfo();
        }

        public b() {
        }
    }

    public static class c implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        public final i f5315a;

        @Override // java.lang.Runnable
        public void run() {
            Logger.v(j.h, "remote config sdk report data to aiops is: %s", new JSONObject(this.f5315a.get()));
            HianalyticsHelper.getInstance().onEvent(this.f5315a.get(), i.b);
        }

        public c(i iVar) {
            this.f5315a = iVar;
        }
    }

    public class e implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        public Context f5317a;

        @Override // java.lang.Runnable
        public void run() {
            try {
                try {
                    j.this.b(this.f5317a);
                } catch (Exception unused) {
                    Logger.e(j.h, "updateConfigs has an exception");
                }
            } finally {
                j.this.g.countDown();
            }
        }

        public e(Context context) {
            this.f5317a = context;
        }
    }

    public /* synthetic */ j(a aVar) {
        this();
    }

    public static /* synthetic */ class a {

        /* renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f5313a;

        static {
            int[] iArr = new int[q.values().length];
            f5313a = iArr;
            try {
                iArr[q.RESTFUL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f5313a[q.FILE_MANAGER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f5313a[q.DEFAULT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public j() {
        this.e = ExecutorsUtils.newSingleThreadExecutor("CfgManager");
        this.b = new ConcurrentHashMap();
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        this.f5312a = concurrentHashMap;
        concurrentHashMap.put(s, new n(l.REMOTE_AGC));
        this.f5312a.put(r, new p(l.REMOTE_AGC));
        this.f5312a.put(t, new o(l.REMOTE_AGC));
    }
}
