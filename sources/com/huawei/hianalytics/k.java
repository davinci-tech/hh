package com.huawei.hianalytics;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hianalytics.core.common.EnvUtils;
import com.huawei.hianalytics.core.log.HiLog;
import com.huawei.hianalytics.framework.data.WorkKeyHandler;
import com.huawei.hianalytics.kit.AesKeyGetterImpl;
import com.tencent.connect.common.Constants;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes4.dex */
public class k {

    /* renamed from: a, reason: collision with root package name */
    public static k f3880a;

    /* renamed from: a, reason: collision with other field name */
    public Context f46a;

    /* renamed from: a, reason: collision with other field name */
    public final ConcurrentHashMap<String, d1> f47a = new ConcurrentHashMap<>();

    /* renamed from: a, reason: collision with other field name */
    public static final String[] f45a = {"ABTesting", "_default_config_tag"};

    /* renamed from: a, reason: collision with other field name */
    public static final Object f44a = new Object();

    public static k a() {
        if (f3880a == null) {
            synchronized (k.class) {
                if (f3880a == null) {
                    f3880a = new k();
                }
            }
        }
        return f3880a;
    }

    public void a(Context context) {
        synchronized (f44a) {
            if (this.f46a != null) {
                HiLog.i("HADM", "sdk has been initialized");
                return;
            }
            this.f46a = context;
            EnvUtils.setAppContext(context);
            WorkKeyHandler.setAesKeyGetter(new AesKeyGetterImpl());
            i.a().m550a().f = context.getPackageName();
            i.a().m550a().f49a = j.c(j.m572e() ? "ro.build.version.magic" : "ro.build.version.emui", "");
            i.a().m550a().u = j.c("hw_sc.build.platform.version", "");
            m0 a2 = m0.a();
            if (a2.f56a == null) {
                a2.f56a = context;
            }
            i.a().m550a().o = j.b(context);
            String m553a = j.m553a(context);
            i.a().m550a().h = TextUtils.isEmpty(m553a) ? "" : m553a;
            if (j1.f3879a.a()) {
                String a3 = j.a("global_v2", Constants.PARAM_APP_VER, "");
                j.m559a("global_v2", Constants.PARAM_APP_VER, m553a);
                i.a().m550a().i = a3;
                if (TextUtils.isEmpty(m553a) || m553a.equals(a3)) {
                    return;
                }
                i.a().m550a().f50b = System.currentTimeMillis();
                return;
            }
            HiLog.i("HADM", "user lock");
        }
    }

    public d1 a(String str, d1 d1Var) {
        d1 putIfAbsent = this.f47a.putIfAbsent(str, d1Var);
        d1 d1Var2 = this.f47a.get(str);
        if (d1Var2 != null) {
            i a2 = i.a();
            e1 e1Var = d1Var2.f26a;
            a2.getClass();
            i.f40a.put(str, e1Var);
        }
        return putIfAbsent;
    }

    public d1 a(String str) {
        String str2;
        if (str == null) {
            str2 = "tag can't be null";
        } else {
            if (this.f47a.containsKey(str)) {
                HiLog.d("HADM", "tag: " + str + " found");
                return this.f47a.get(str);
            }
            str2 = "tag: " + str + " not found";
        }
        HiLog.sw("HADM", str2);
        return null;
    }
}
