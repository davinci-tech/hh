package com.huawei.hms.network;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.hms.framework.common.ContextHolder;
import com.huawei.hms.framework.common.ExecutorsUtils;
import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.framework.common.hianalytics.HianalyticsHelper;
import com.huawei.hms.framework.common.hianalytics.InitReport;
import com.huawei.hms.network.api.advance.AdvanceNetworkKit;
import com.huawei.hms.network.api.advance.BandwidthCallBack;
import com.huawei.hms.network.api.advance.ReportCallBack;
import com.huawei.hms.network.api.advance.WrapperLogger;
import com.huawei.hms.network.embedded.a1;
import com.huawei.hms.network.embedded.a3;
import com.huawei.hms.network.embedded.g2;
import com.huawei.hms.network.embedded.g4;
import com.huawei.hms.network.embedded.h1;
import com.huawei.hms.network.embedded.l1;
import com.huawei.hms.network.embedded.m4;
import com.huawei.hms.network.embedded.s2;
import com.huawei.hms.network.embedded.u3;
import com.huawei.hms.network.embedded.y0;
import com.huawei.hms.network.embedded.z0;
import com.huawei.hms.network.httpclient.excutor.PolicyExecutor;
import com.huawei.hms.network.httpclient.okhttp.OkHttpClientGlobal;
import com.huawei.hms.network.inner.api.InterceptorNetworkService;
import com.huawei.hms.network.inner.api.NetDiagnosisNetworkService;
import com.huawei.hms.network.inner.api.NetworkKitInnerImpl;
import com.huawei.hms.network.inner.api.NetworkService;
import com.huawei.hms.network.inner.api.PolicyNetworkService;
import com.huawei.hms.network.netdiag.qoe.QoeMetrics;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* loaded from: classes9.dex */
public class ComposedNetworkKit {
    public static final String c = "ComposedNetworkKit";
    public static final ComposedNetworkKit d = new ComposedNetworkKit();

    /* renamed from: a, reason: collision with root package name */
    public PolicyExecutor f5102a;
    public CyclicBarrier b = new CyclicBarrier(2);

    public void setWrapperLogger(WrapperLogger wrapperLogger, boolean z) {
        Logger.setExtLogger(new h1.k(wrapperLogger), z);
    }

    public void setReportCallback(ReportCallBack reportCallBack) {
        HianalyticsHelper.getInstance().setReportCallback(new a(reportCallBack));
    }

    public void setOptions(String str) {
        if (str == null) {
            Logger.w(c, "setOptions == null");
            return;
        }
        PolicyExecutor policyExecutor = this.f5102a;
        if (policyExecutor != null) {
            policyExecutor.setOptions(str);
            NetworkKitInnerImpl.getInstance().setPolicyExecutor(this.f5102a);
            b();
        }
    }

    public void setLogEnableLevel(int i) {
        Logger.setLogEnableLevel(i);
    }

    public void reloadQuic() {
        a1.reloadQuic();
    }

    public void registerBandwidthCallback(String str, int i, BandwidthCallBack bandwidthCallBack) {
        l1.getInstance().init(str, i);
        l1.getInstance().registerBandwidthCallback(bandwidthCallBack);
    }

    public void initKit(Context context, String str) {
        ContextHolder.setAppContext(context);
        ExecutorsUtils.newThread(new b(context), "ComposedNetworkKit_initKit").start();
        NetworkKitInnerImpl.getInstance().init(context, str, a()).setProtocolStackManager(new z0());
        this.f5102a = new PolicyExecutor();
        NetworkKitInnerImpl.getInstance().setPolicyExecutor(this.f5102a);
        b();
        Boolean c2 = m4.c().c(NetworkService.Constants.REMOTE_SCENE_SWITCH);
        if (c2 != null && c2.booleanValue()) {
            u3.getInstance().registerSceneChangeListener(context);
        }
        try {
            this.b.await(1L, TimeUnit.SECONDS);
        } catch (InterruptedException | BrokenBarrierException | TimeoutException unused) {
            Logger.w(c, "cyclicBarrier  await error!");
        }
    }

    public void initConnectionPool(int i, long j, TimeUnit timeUnit) {
        OkHttpClientGlobal.init(i, j, timeUnit);
    }

    public Map<String, Integer> getSignalMetrics() {
        InterceptorNetworkService interceptorNetworkService = NetworkKitInnerImpl.getInstance().getInterceptorNetworkService("netdiag");
        if (interceptorNetworkService != null) {
            return ((NetDiagnosisNetworkService) interceptorNetworkService).getSignalMetrics();
        }
        Logger.w(c, "the netdiag service is not reachable for signal");
        return new HashMap();
    }

    public QoeMetrics getQoeMetrics() {
        InterceptorNetworkService interceptorNetworkService = NetworkKitInnerImpl.getInstance().getInterceptorNetworkService("netdiag");
        if (interceptorNetworkService != null) {
            return ((NetDiagnosisNetworkService) interceptorNetworkService).getQoeMetrics(false);
        }
        Logger.w(c, "the netdiag service is not reachable for qoe");
        return null;
    }

    public String getOption(String str, String str2) {
        PolicyExecutor policyExecutor = this.f5102a;
        return policyExecutor != null ? policyExecutor.getValue(str, str2) : "";
    }

    public String getNetworkMetrics() {
        InterceptorNetworkService interceptorNetworkService = NetworkKitInnerImpl.getInstance().getInterceptorNetworkService("netdiag");
        if (interceptorNetworkService != null) {
            return ((NetDiagnosisNetworkService) interceptorNetworkService).getNetworkMetrics();
        }
        Logger.w(c, "the netdiag service is not reachable for NetworkMetrics");
        return null;
    }

    public void evictAllConnections() {
        OkHttpClientGlobal.getInstance().evictAll();
    }

    public void doAction(String str) {
        if (str == null) {
            Logger.e(c, "invalid actionkey!");
        } else if (str.equals(AdvanceNetworkKit.ACTION_CLEAN_CONNECTDATA)) {
            clear();
            InitReport.disableConnectNet();
        }
    }

    public void clear() {
        Iterator<NetworkService> it = m4.c().a().iterator();
        while (it.hasNext()) {
            it.next().clear();
        }
    }

    public boolean checkConnectivity() {
        InterceptorNetworkService interceptorNetworkService = NetworkKitInnerImpl.getInstance().getInterceptorNetworkService("netdiag");
        if (interceptorNetworkService != null) {
            return ((NetDiagnosisNetworkService) interceptorNetworkService).checkConnectivity();
        }
        Logger.w(c, "the netdiag service is not reachable for connect");
        return false;
    }

    public void addQuicHint(boolean z, String... strArr) {
        g2.getInstance().addQuicHint(Arrays.asList(strArr), z);
    }

    public static ComposedNetworkKit getInstance() {
        return d;
    }

    private void b() {
        HianalyticsHelper.getInstance().setHaTag(this.f5102a.getString("", "core_ha_tag"));
        HianalyticsHelper.getInstance().enablePrivacyPolicy(this.f5102a.getBoolean("", "core_enable_privacy_policy"));
        HianalyticsHelper.getInstance().setRate(this.f5102a.getInt("", PolicyNetworkService.GlobalConstants.REPORT_RATE));
        HianalyticsHelper.getInstance().setQuicRate(this.f5102a.getInt("", PolicyNetworkService.GlobalConstants.QUIC_REPORT_RATE));
        g4.getInstance().enableAllLinkDelayAnalysis(this.f5102a.getBoolean("", "core_enable_alllink_delay_analysis"));
        g4.getInstance().userSet(!TextUtils.isEmpty(this.f5102a.getUserConfigValue("core_enable_alllink_delay_analysis")));
    }

    public class a implements HianalyticsHelper.ReportCallBack {

        /* renamed from: a, reason: collision with root package name */
        public final /* synthetic */ ReportCallBack f5103a;

        @Override // com.huawei.hms.framework.common.hianalytics.HianalyticsHelper.ReportCallBack
        public void onReport(int i, String str, LinkedHashMap<String, String> linkedHashMap) {
            this.f5103a.onReport(i, str, linkedHashMap);
        }

        public a(ReportCallBack reportCallBack) {
            this.f5103a = reportCallBack;
        }
    }

    public class b implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        public Context f5104a;

        @Override // java.lang.Runnable
        public void run() {
            new s2().init();
            a3.init();
            y0.registerNetworkState(this.f5104a);
            try {
                ComposedNetworkKit.this.b.await(1L, TimeUnit.SECONDS);
            } catch (InterruptedException | BrokenBarrierException | TimeoutException unused) {
                Logger.w(ComposedNetworkKit.c, "cyclicBarrier  await error!");
            }
        }

        public b(Context context) {
            this.f5104a = context;
        }
    }

    private Bundle a() {
        Bundle bundle = new Bundle();
        bundle.putBoolean(PolicyNetworkService.NetworkServiceConstants.IS_DYNAMIC, ContextHolder.getKitContext() != null);
        return bundle;
    }
}
