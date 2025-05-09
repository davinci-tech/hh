package com.huawei.agconnect.apms;

import android.content.Context;
import com.huawei.agconnect.apms.collect.model.basic.ApplicationInformation;
import com.huawei.agconnect.apms.collect.model.basic.DeviceInformation;
import com.huawei.agconnect.apms.collect.model.basic.PlatformInformation;
import com.huawei.agconnect.apms.collect.model.basic.RuntimeEnvInformation;
import com.huawei.agconnect.apms.collect.model.basic.UserSettingsInformation;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes.dex */
public class Agent {
    private static final Object IMPL_LOCK;
    public static final String NAME = "AndroidAgent";
    private static final def NOOP_AGENT_IMPL;
    public static final String OS_NAME = "Android";
    public static final String VERSION = "1.4.4.502";
    private static ExecutorService executor;
    private static def impl;

    static {
        fgh fghVar = new fgh();
        NOOP_AGENT_IMPL = fghVar;
        impl = fghVar;
        IMPL_LOCK = new Object();
        executor = Executors.newSingleThreadExecutor(new j0("ApmsAgent"));
    }

    public static void disableAppVersions(String str) {
        getImpl().bcd(str);
    }

    public static void enableAnrMonitor(boolean z) {
        getImpl().abc(z);
    }

    public static void enableCollection(boolean z) {
        getImpl().bcd(z);
    }

    public static void enableCollectionByUser(boolean z) {
        getImpl().efg(z);
    }

    public static void enableFpsDropTracer(boolean z) {
        getImpl().fgh(z);
    }

    public static void enableNuwaTracer(boolean z) {
        getImpl().def(z);
    }

    public static void enableWebViewMonitor(boolean z) {
        getImpl().cde(z);
    }

    public static cde getAgentConfiguration() {
        return getImpl().mno();
    }

    public static ApplicationInformation getApplicationInformation() {
        return getImpl().nop();
    }

    public static Context getContext() {
        return getImpl().jkl();
    }

    public static long getCreateTime() {
        return getImpl().abc();
    }

    public static DeviceInformation getDeviceInformation() {
        return getImpl().efg();
    }

    public static ExecutorService getExecutor() {
        return executor;
    }

    private static def getImpl() {
        def defVar;
        synchronized (IMPL_LOCK) {
            defVar = impl;
        }
        return defVar;
    }

    public static PlatformInformation getPlatformInformation() {
        return getImpl().klm();
    }

    public static RuntimeEnvInformation getRuntimeEnvInformation() {
        return getImpl().ijk();
    }

    public static String getUserIdentifier() {
        return getImpl().ghi();
    }

    public static UserSettingsInformation getUserSettingsInformation() {
        return getImpl().hij();
    }

    public static boolean isDisabled() {
        return getImpl().bcd();
    }

    public static boolean isNuwaTracerEnable() {
        return getImpl().opq();
    }

    public static boolean isWebViewMonitorDisabled() {
        return getImpl().lmn();
    }

    public static void onEvent(String str, Map<String, String> map) {
        getImpl().abc(str, map);
    }

    public static void onReport() {
        getImpl().def();
    }

    public static void setFpsDropCount(int i) {
        getImpl().abc(i);
    }

    public static void setImpl(def defVar) {
        synchronized (IMPL_LOCK) {
            if (defVar == null) {
                impl = NOOP_AGENT_IMPL;
            } else {
                impl = defVar;
            }
        }
    }

    public static void setUserIdentifier(String str) {
        getImpl().abc(str);
    }

    public static void start() {
        getImpl().cde();
    }

    public static void startCollectCpuAndMemoryData(int i) {
        getImpl().bcd(i);
    }

    public static String stopCollectCpuAndMemoryData() {
        return getImpl().fgh();
    }

    public static String getVersion() {
        return VERSION;
    }
}
