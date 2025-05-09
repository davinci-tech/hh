package com.huawei.hms.network.embedded;

import android.content.Context;
import com.huawei.hms.network.ComposedNetworkKit;
import com.huawei.hms.network.api.advance.AdvanceNetworkKit;
import com.huawei.hms.network.api.advance.BandwidthCallBack;
import com.huawei.hms.network.api.advance.ReportCallBack;
import com.huawei.hms.network.api.advance.WrapperLogger;
import com.huawei.hms.network.netdiag.qoe.QoeMetrics;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/* loaded from: classes9.dex */
public class f extends AdvanceNetworkKit {

    /* renamed from: a, reason: collision with root package name */
    public static final String f5236a = "NetworkKitAdvImpl";
    public static final AdvanceNetworkKit b = new f();

    @Override // com.huawei.hms.network.api.advance.AdvanceNetworkKit
    public void setWrapperLogger(WrapperLogger wrapperLogger, boolean z) {
        ComposedNetworkKit.getInstance().setWrapperLogger(wrapperLogger, z);
    }

    @Override // com.huawei.hms.network.api.advance.AdvanceNetworkKit
    public void setReportCallback(ReportCallBack reportCallBack) {
        if (reportCallBack == null) {
            return;
        }
        ComposedNetworkKit.getInstance().setReportCallback(reportCallBack);
    }

    @Override // com.huawei.hms.network.NetworkKit
    public void setOptions(String str) {
        ComposedNetworkKit.getInstance().setOptions(str);
    }

    @Override // com.huawei.hms.network.api.advance.AdvanceNetworkKit
    public void setLogEnableLevel(int i) {
        ComposedNetworkKit.getInstance().setLogEnableLevel(i);
    }

    @Override // com.huawei.hms.network.api.advance.AdvanceNetworkKit
    public void reloadQuic() {
        ComposedNetworkKit.getInstance().reloadQuic();
    }

    @Override // com.huawei.hms.network.api.advance.AdvanceNetworkKit
    public void registerBandwidthCallback(String str, int i, BandwidthCallBack bandwidthCallBack) {
        if (bandwidthCallBack == null) {
            return;
        }
        ComposedNetworkKit.getInstance().registerBandwidthCallback(str, i, bandwidthCallBack);
    }

    @Override // com.huawei.hms.network.NetworkKit
    public void initKit(Context context, String str) {
        ComposedNetworkKit.getInstance().initKit(context, str);
    }

    @Override // com.huawei.hms.network.NetworkKit
    public void initConnectionPool(int i, long j, TimeUnit timeUnit) {
        ComposedNetworkKit.getInstance().initConnectionPool(i, j, timeUnit);
    }

    @Override // com.huawei.hms.network.api.advance.AdvanceNetworkKit
    public Map<String, Integer> getSignalMetrics() {
        return ComposedNetworkKit.getInstance().getSignalMetrics();
    }

    @Override // com.huawei.hms.network.api.advance.AdvanceNetworkKit
    public QoeMetrics getQoeMetrics() {
        return ComposedNetworkKit.getInstance().getQoeMetrics();
    }

    @Override // com.huawei.hms.network.NetworkKit
    public String getOption(String str) {
        return ComposedNetworkKit.getInstance().getOption("", str);
    }

    @Override // com.huawei.hms.network.api.advance.AdvanceNetworkKit
    public String getNetworkMetrics() {
        return ComposedNetworkKit.getInstance().getNetworkMetrics();
    }

    @Override // com.huawei.hms.network.NetworkKit
    public void evictAllConnections() {
        ComposedNetworkKit.getInstance().evictAllConnections();
    }

    @Override // com.huawei.hms.network.api.advance.AdvanceNetworkKit
    public void doAction(String str) {
        ComposedNetworkKit.getInstance().doAction(str);
    }

    @Override // com.huawei.hms.network.api.advance.AdvanceNetworkKit
    public boolean checkConnectivity() {
        return ComposedNetworkKit.getInstance().checkConnectivity();
    }

    @Override // com.huawei.hms.network.NetworkKit
    public void addQuicHint(boolean z, String... strArr) {
        ComposedNetworkKit.getInstance().addQuicHint(z, strArr);
    }

    public static AdvanceNetworkKit getInstance() {
        return b;
    }
}
