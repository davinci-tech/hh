package com.huawei.hms.network.embedded;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hms.framework.common.ContextHolder;
import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.framework.common.ReflectionUtils;
import com.huawei.hms.framework.common.StringUtils;
import com.huawei.hms.framework.common.Utils;
import com.huawei.hms.framework.common.hianalytics.HianalyticsBaseData;
import com.huawei.hms.framework.common.hianalytics.HianalyticsHelper;
import com.huawei.hms.framework.common.hianalytics.InitReport;
import com.huawei.hms.hquic.HQUICException;
import com.huawei.hms.hquic.HQUICManager;
import com.huawei.hms.hquic.HQUICProvider;
import com.huawei.hms.network.base.util.HttpUtils;
import com.huawei.hms.network.conf.api.ConfigAPI;
import com.huawei.hms.network.embedded.k;
import com.huawei.hms.network.inner.api.PolicyNetworkService;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import org.chromium.net.ExperimentalCronetEngine;
import org.chromium.net.NetworkException;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public class g2 {
    public static final String H3 = "h3";
    public static final String QUIC = "quic";
    public static final String f = "CronetNegotiateManager";
    public static final String g = "org.chromium.net.CronetEngine";
    public static final String h = "com.huawei.hms.hquic.HQUICManager";
    public static final String i = "hquic_load";
    public static final int j = 0;
    public static final int k = 1;
    public static final int l = 2;
    public static final int m = 3;
    public static final int n = 443;
    public static final int o = 10000910;
    public static final int p = 10000911;
    public static volatile g2 q = null;
    public static final int r = 1;
    public static final int s = 0;
    public volatile d c;

    /* renamed from: a, reason: collision with root package name */
    public ConcurrentHashMap<String, m2> f5257a = new ConcurrentHashMap<>();
    public int b = 0;
    public String d = null;
    public boolean e = false;

    public void updateQuicHints(String str, int i2, boolean z) {
        if (str != null && !z) {
            this.f5257a.remove(str);
        }
        if (str == null) {
            Logger.v(f, "host == null");
            return;
        }
        m2 m2Var = this.f5257a.get(str);
        if (m2Var == null || !(i2 == -1 || i2 == m2Var.getPort())) {
            Logger.v(f, "server negotiate port is %d, but there is not compatible config or historical success record", Integer.valueOf(i2));
        } else {
            Logger.v(f, "the host:%s will use cronet next time", str);
            m2Var.setEnableQuic(z);
        }
        Logger.v(f, "updateQuicHints is execute,and the map is: %s", this.f5257a.keySet().toString());
    }

    public void maybeRecordCongestionTypeInfo(String str, boolean z) {
        if (z && this.e) {
            l1.getInstance().recordCongestionTypeInfo(str);
            return;
        }
        Logger.v(f, "the CronetEngine can‘t use for bandwidth, and congestionType = " + str);
    }

    public void maybeExtraBandwidthEvaluation() {
        this.e = true;
    }

    public int mappingCronetErrorCode(Exception exc) {
        if (exc == null || !isAvailable() || !(exc instanceof NetworkException)) {
            return 10000802;
        }
        switch (((NetworkException) exc).getErrorCode()) {
        }
        return 10000802;
    }

    public void loadQuicConf() {
        String valueOf = String.valueOf(ConfigAPI.getValue(PolicyNetworkService.QuicConstants.QUICHINT));
        Logger.v(f, "quichint is %s", valueOf);
        if (TextUtils.isEmpty(valueOf)) {
            return;
        }
        a(valueOf);
    }

    public void lazyInitHmsQuicLoader(boolean z) {
        synchronized (this) {
            if (!z) {
                if (this.b != 0) {
                    Logger.i(f, "run lazyInitHmsQuicLoader before");
                    return;
                }
            }
            this.c = new d();
            this.c.setLoadStartTime(Utils.getCurrentTime(false));
            try {
                Class.forName(h);
                this.b = 1;
                a();
            } catch (ClassNotFoundException e) {
                Logger.w(f, "load com.huawei.hms.hquic.HQUICManagerclass failed, exception:%s", e.getClass().getSimpleName());
                this.b = 3;
                this.c.setLoadEndTime(Utils.getCurrentTime(false));
                this.c.setLoadError(e);
                this.c.setErrorCode(p);
                HianalyticsHelper.getInstance().getReportExecutor().execute(new b());
            }
        }
    }

    public boolean isSupportExtraBandwidthEvaluation() {
        return this.e;
    }

    public boolean isSupportCronet() {
        try {
            Class.forName(g);
            return true;
        } catch (ClassNotFoundException e) {
            Logger.w(f, "load network-quic CronetEngine class failed, exception:%s", e.getClass().getSimpleName());
            return false;
        }
    }

    public boolean isHquicProviderSupportSelectQuic() {
        return ReflectionUtils.checkCompatible(h, "asyncInit", Context.class, String.class, HQUICManager.HQUICInitCallback.class);
    }

    public Boolean isEnableQuic(String str, int i2) {
        boolean z;
        Logger.v(f, "isEnableQuic is execute,and the map is: %s", this.f5257a.keySet().toString());
        m2 m2Var = this.f5257a.get(str);
        if (m2Var != null && m2Var.getEnableQuic() && (i2 == -1 || i2 == m2Var.getPort())) {
            Logger.v(f, "use cronet and request");
            z = true;
        } else {
            z = false;
        }
        return Boolean.valueOf(z);
    }

    public boolean isAvailable() {
        Logger.v(f, "initHmsQuicProviderState code is: " + this.b);
        return this.b == 2;
    }

    public ConcurrentHashMap<String, m2> getQuicHints() {
        return this.f5257a;
    }

    public String getAltSvcType() {
        return k.c.f5333a.equals(this.d) ? H3 : QUIC;
    }

    public void addQuicHint(List<String> list, boolean z) {
        if (list == null) {
            Logger.e(f, "invalid argument");
            return;
        }
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            addQuicHint(it.next(), z);
        }
    }

    public static final class d {

        /* renamed from: a, reason: collision with root package name */
        public LinkedHashMap<String, String> f5261a;
        public long b;
        public long c;
        public Exception d;
        public int e;
        public String f;
        public String g;

        public void setLoadStartTime(long j) {
            this.b = j;
        }

        public void setLoadSdkVersion(String str) {
            this.f = str;
        }

        public void setLoadSdkName(String str) {
            this.g = str;
        }

        public void setLoadError(Exception exc) {
            this.d = exc;
        }

        public void setLoadEndTime(long j) {
            this.c = j;
        }

        public void setErrorCode(int i) {
            this.e = i;
        }

        public long getLoadTime() {
            long j = this.c - this.b;
            if (j > 0) {
                return j;
            }
            return 0L;
        }

        public LinkedHashMap<String, String> finiInfo2HaMap() {
            this.f5261a.put("error_code", String.valueOf(this.e));
            this.f5261a.put("total_time", String.valueOf(getLoadTime()));
            this.f5261a.put("req_start_time", String.valueOf(this.b));
            this.f5261a.put("kit_provider", this.g);
            this.f5261a.put("kit_version", this.f);
            Exception exc = this.d;
            if (exc != null) {
                this.f5261a.put("exception_name", exc.getClass().getSimpleName());
                this.f5261a.put("message", StringUtils.anonymizeMessage(this.d.getMessage()));
            }
            return this.f5261a;
        }

        public d() {
            LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
            this.f5261a = linkedHashMap;
            linkedHashMap.put(HianalyticsBaseData.SDK_NAME, "networkkit");
            this.f5261a.put("sdk_type", "UxPP");
            this.f5261a.put("sdk_version", "8.0.1.307");
        }
    }

    public void addQuicHint(String str, boolean z) {
        a(str, z, false);
    }

    public static g2 getInstance() {
        if (q == null) {
            synchronized (g2.class) {
                if (q == null) {
                    q = new g2();
                }
            }
        }
        return q;
    }

    private void a(String str, boolean z, boolean z2) {
        String str2;
        String str3;
        if (TextUtils.isEmpty(str)) {
            str3 = "invalid argument";
        } else {
            if (HttpUtils.isHttpUrl(str)) {
                str2 = str;
            } else {
                str2 = "https://" + str;
            }
            try {
                URL url = new URL(str2);
                m2 m2Var = this.f5257a.get(url.getHost());
                int port = url.getPort() == -1 ? n : url.getPort();
                if (m2Var == null || z2 || port != m2Var.getPort()) {
                    m2Var = new m2();
                    m2Var.setHost(url.getHost());
                    m2Var.setPort(port);
                    m2Var.setAlternatePort(port);
                    m2Var.setEnableQuic(z);
                    Logger.w(f, "QuicHit:" + m2Var);
                }
                this.f5257a.put(m2Var.getHost(), m2Var);
                return;
            } catch (MalformedURLException unused) {
                str3 = "add QuicHit failed，please check domian format:" + str;
            }
        }
        Logger.e(f, str3);
    }

    private void a(String str) {
        Logger.v(f, "the configInfo is %s", str);
        try {
            JSONObject jSONObject = new JSONObject(str).getJSONObject("services");
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                String string = jSONObject.getString(next);
                if (!TextUtils.isEmpty(next) && !TextUtils.isEmpty(string)) {
                    int stringToInteger = StringUtils.stringToInteger(string, -1);
                    if (stringToInteger == 0) {
                        a(next, false, true);
                    } else if (stringToInteger == 1) {
                        a(next, true, true);
                    }
                }
                Logger.e(f, "config file has been broken.");
            }
            Logger.v(f, "loadConfigToCache is execute,and the map size is:%s---%s", Integer.valueOf(this.f5257a.size()), this.f5257a.keySet().toString());
        } catch (JSONException e) {
            Logger.w(f, "parse JSON occur error.", e);
        }
    }

    public class a implements HQUICManager.HQUICInitCallback {

        /* renamed from: a, reason: collision with root package name */
        public HQUICProvider f5258a = new HQUICProvider(ContextHolder.getResourceContext());

        public void onSuccess() {
            Logger.i(g2.f, "Init Quic Success");
            try {
                a();
                g2.this.b = 2;
                a(g2.o);
                g2 g2Var = g2.this;
                g2Var.a(g2Var.c);
            } catch (Throwable th) {
                Logger.w(g2.f, "load quic pro success but init engine fail: " + th.getMessage());
                onFail(new Exception(th));
            }
        }

        public void onFail(Exception exc) {
            StringBuilder sb;
            Logger.i(g2.f, "Init Quic Fail");
            g2.this.b = 3;
            if (!(exc instanceof HQUICException)) {
                if (exc instanceof IllegalArgumentException) {
                    sb = new StringBuilder("invalid argument, reason:");
                }
                g2.this.c.setLoadError(exc);
                a(g2.p);
                g2 g2Var = g2.this;
                g2Var.a(g2Var.c);
            }
            sb = new StringBuilder("Init Hms Quic Loader failed, reason:");
            sb.append(exc.getMessage());
            Logger.i(g2.f, sb.toString());
            g2.this.c.setLoadError(exc);
            a(g2.p);
            g2 g2Var2 = g2.this;
            g2Var2.a(g2Var2.c);
        }

        private void a(int i) {
            g2.this.c.setLoadEndTime(Utils.getCurrentTime(false));
            if (a.class.getClassLoader() != null) {
                g2.this.c.setLoadSdkName(a.class.getClassLoader().getClass().getCanonicalName());
            } else {
                g2.this.c.setLoadSdkName(this.f5258a.getName());
            }
            g2.this.c.setErrorCode(i);
            g2.this.c.setLoadSdkVersion(this.f5258a.getVersion());
        }

        private void a() {
            new ExperimentalCronetEngine.Builder(new z1(ContextHolder.getResourceContext())).enableQuic(true).enableHttp2(true).enableNetworkQualityEstimator(true).build().shutdown();
        }

        public a() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(d dVar) {
        if (HianalyticsHelper.getInstance().isEnableReport(ContextHolder.getAppContext())) {
            InitReport.reportWhenInit(new c(dVar));
        } else {
            Logger.i(f, "HianalyticsHelper report disable");
        }
    }

    private void a() {
        String valueOf = String.valueOf(ConfigAPI.getValue(PolicyNetworkService.QuicConstants.MODULE_NAME));
        this.d = valueOf;
        Logger.i(f, "module name is %s", valueOf);
        a aVar = new a();
        if (isHquicProviderSupportSelectQuic()) {
            HQUICManager.asyncInit(ContextHolder.getResourceContext(), this.d, aVar);
        } else {
            Logger.i(f, "not support select quic");
            HQUICManager.asyncInit(ContextHolder.getResourceContext(), aVar);
        }
    }

    public class b implements Runnable {
        @Override // java.lang.Runnable
        public void run() {
            g2 g2Var = g2.this;
            g2Var.a(g2Var.c);
        }

        public b() {
        }
    }

    public class c implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        public final /* synthetic */ d f5260a;

        @Override // java.lang.Runnable
        public void run() {
            HianalyticsHelper.getInstance().onEvent(this.f5260a.finiInfo2HaMap(), g2.i);
        }

        public c(d dVar) {
            this.f5260a = dVar;
        }
    }
}
