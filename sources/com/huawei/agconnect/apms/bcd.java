package com.huawei.agconnect.apms;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.HandlerThread;
import android.os.Looper;
import android.text.TextUtils;
import android.view.WindowManager;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.huawei.agconnect.apms.collect.HiAnalyticsManager;
import com.huawei.agconnect.apms.collect.model.CollectData;
import com.huawei.agconnect.apms.collect.model.EventType;
import com.huawei.agconnect.apms.collect.model.HeaderType;
import com.huawei.agconnect.apms.collect.model.basic.ApplicationInformation;
import com.huawei.agconnect.apms.collect.model.basic.DeviceInformation;
import com.huawei.agconnect.apms.collect.model.basic.PlatformInformation;
import com.huawei.agconnect.apms.collect.model.basic.UserSettingsInformation;
import com.huawei.agconnect.apms.collect.model.event.resource.CPUMemoryEvent;
import com.huawei.agconnect.apms.exception.APMSException;
import com.huawei.agconnect.apms.i0;
import com.huawei.agconnect.apms.log.AgentLog;
import com.huawei.agconnect.apms.log.AgentLogManager;
import com.huawei.openalliance.ad.constant.Constants;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* loaded from: classes.dex */
public class bcd implements def, j {
    public static final AgentLog abc = AgentLogManager.getAgentLog();
    public final Context cde;
    public ghi def;
    public final cde efg;
    public DeviceInformation fgh;
    public PlatformInformation ghi;
    public UserSettingsInformation hij;
    public ApplicationInformation ijk;
    public String jkl;
    public final Object klm = new Object();
    public final long bcd = System.currentTimeMillis();

    public class abc implements Runnable {
        public final /* synthetic */ String abc;
        public final /* synthetic */ Map bcd;

        public abc(String str, Map map) {
            this.abc = str;
            this.bcd = map;
        }

        @Override // java.lang.Runnable
        public void run() {
            LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
            linkedHashMap.put(HeaderType.AGENT_VERSION, Agent.getVersion());
            linkedHashMap.put(HeaderType.USER_IDENTIFIER, Agent.getUserIdentifier());
            linkedHashMap.put(HeaderType.EVENT_ID, this.abc);
            linkedHashMap.put("device", bcd.this.efg().toJsonString());
            linkedHashMap.put("platform", bcd.this.klm().toJsonString());
            linkedHashMap.put(EventType.USER_SETTINGS, bcd.this.hij().toJsonString());
            linkedHashMap.put("app", bcd.this.ijk.toJsonString());
            JsonArray jsonArray = new JsonArray();
            jsonArray.add(bcd.this.ijk().asJsonArray());
            jsonArray.add(new Gson().toJson(this.bcd));
            linkedHashMap.put(EventType.QOES_EVENT, jsonArray.toString());
            linkedHashMap.put(HeaderType.NQOES_TRANSACTION_ID, "");
            bcd.abc.info("report custom data size: " + linkedHashMap.size());
            HiAnalyticsManager.getInstance().onEvent(HiAnalyticsManager.APM_EVENT_ID, linkedHashMap);
        }
    }

    public bcd(Context context, cde cdeVar) throws Throwable {
        Context abc2 = abc(context);
        this.cde = abc2;
        this.efg = cdeVar;
        this.def = new ghi(abc2);
        n.abc().abc(this);
        h hVar = new h();
        if (abc2 instanceof Application) {
            ((Application) abc2).registerActivityLifecycleCallbacks(hVar);
        }
        context.registerComponentCallbacks(hVar);
        qrs();
        cdeVar.cde(this.def.nop());
        cdeVar.bcd(this.def.mno());
        cdeVar.bcd(this.def.klm());
        cdeVar.abc(this.def.jkl());
        cdeVar.abc(this.def.lmn());
        cdeVar.def(this.def.opq());
        cdeVar.abc(pqr());
        n.abc().abc(o0.jkl());
    }

    @Override // com.huawei.agconnect.apms.def
    public long abc() {
        return this.bcd;
    }

    @Override // com.huawei.agconnect.apms.def
    public void bcd(int i) {
        w wVar;
        synchronized (w.class) {
            if (w.bcd == null) {
                w.bcd = new w();
            }
            wVar = w.bcd;
        }
        wVar.getClass();
        if (Agent.isDisabled()) {
            return;
        }
        AgentLog agentLog = w.abc;
        Locale locale = Locale.ENGLISH;
        agentLog.debug(String.format(locale, "start collect cpu and memory.", new Object[0]));
        AgentLog agentLog2 = t.abc;
        int i2 = i <= 0 ? -1 : i;
        if (i2 > 0) {
            t tVar = wVar.cde;
            tVar.getClass();
            t.abc.debug("collectAtFixedFrequency =" + i2);
            if (tVar.hij > 0 && i2 > 0) {
                ScheduledFuture scheduledFuture = tVar.efg;
                if (scheduledFuture != null) {
                    if (i2 != tVar.fgh) {
                        scheduledFuture.cancel(false);
                        tVar.efg = null;
                        tVar.fgh = -1L;
                    }
                }
                synchronized (tVar) {
                    long j = i2;
                    tVar.fgh = j;
                    try {
                        tVar.efg = tVar.def.scheduleAtFixedRate(new s(tVar), 0L, j, TimeUnit.MILLISECONDS);
                    } catch (RejectedExecutionException e) {
                        t.abc.warn(String.format(Locale.ENGLISH, "unable to start collecting cpu metrics: %s", e.getMessage()));
                    }
                }
            }
        } else {
            agentLog.debug(String.format(locale, "invalid cpu metrics collection frequency-%d. Did not report cpu metrics.", Integer.valueOf(i2)));
        }
        if (i2 <= 0) {
            w.abc.debug(String.format(Locale.ENGLISH, "invalid memory metrics collection frequency-%d. Did not report memory metrics.", Integer.valueOf(i2)));
            return;
        }
        v vVar = wVar.def;
        vVar.getClass();
        if (i2 <= 0) {
            return;
        }
        ScheduledFuture scheduledFuture2 = vVar.efg;
        if (scheduledFuture2 != null) {
            if (i2 == vVar.fgh) {
                return;
            }
            scheduledFuture2.cancel(false);
            vVar.efg = null;
            vVar.fgh = -1L;
        }
        synchronized (vVar) {
            long j2 = i2;
            vVar.fgh = j2;
            try {
                vVar.efg = vVar.cde.scheduleAtFixedRate(new u(vVar), 0L, j2, TimeUnit.MILLISECONDS);
            } catch (RejectedExecutionException e2) {
                v.abc.warn(String.format(Locale.ENGLISH, "unable to start collecting memory metrics: %s", e2.getMessage()));
            }
        }
    }

    @Override // com.huawei.agconnect.apms.def
    public void cde() {
        try {
            if (bcd()) {
                ghi(false);
            } else {
                rst();
                nop.cde();
            }
        } catch (Throwable th) {
            abc.error("failed to start APMS. " + th.getMessage());
        }
    }

    @Override // com.huawei.agconnect.apms.def
    public void def() {
        nop.abc(false);
    }

    /* JADX WARN: Can't wrap try/catch for region: R(15:(6:5|6|7|8|9|10)|11|(15:13|14|15|16|17|(2:19|(1:21))|154|23|(1:25)|26|(2:32|(2:34|(2:36|(1:(1:39))(1:40))(1:41))(1:42))|43|44|45|(14:47|48|49|50|51|52|53|(3:54|55|(2:57|(1:101)(1:62))(4:103|104|106|107))|69|70|71|73|74|(8:76|77|78|79|80|81|82|83)(9:86|(7:88|78|79|80|81|82|83)|77|78|79|80|81|82|83))(13:151|49|50|51|52|53|(4:54|55|(0)(0)|101)|69|70|71|73|74|(0)(0)))|157|17|(0)|154|23|(0)|26|(4:28|30|32|(0)(0))|43|44|45|(0)(0)) */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x00a7, code lost:
    
        if (r3.length() != 0) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x0135, code lost:
    
        r2 = r8.split(":")[1];
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x013b, code lost:
    
        r7.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:98:0x013f, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:99:0x0140, code lost:
    
        com.huawei.agconnect.apms.b0.abc.warn("failed to close /proc/cpuinfo file: " + r0.getMessage());
     */
    /* JADX WARN: Removed duplicated region for block: B:103:0x0172 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:13:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x010d A[Catch: all -> 0x010f, TRY_LEAVE, TryCatch #10 {all -> 0x010f, blocks: (B:45:0x00fd, B:151:0x010d), top: B:44:0x00fd }] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0085  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00b6  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00c2  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00e1  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00f5  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x010c  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0126 A[Catch: all -> 0x01a8, TryCatch #5 {all -> 0x01a8, blocks: (B:55:0x0120, B:57:0x0126, B:60:0x012e, B:63:0x0135), top: B:54:0x0120 }] */
    /* JADX WARN: Removed duplicated region for block: B:76:0x0218  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0219 A[Catch: all -> 0x021d, TRY_LEAVE, TryCatch #19 {all -> 0x021d, blocks: (B:74:0x020e, B:86:0x0219), top: B:73:0x020e }] */
    /* JADX WARN: Removed duplicated region for block: B:88:0x023b  */
    @Override // com.huawei.agconnect.apms.def
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.huawei.agconnect.apms.collect.model.basic.DeviceInformation efg() {
        /*
            Method dump skipped, instructions count: 667
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.agconnect.apms.bcd.efg():com.huawei.agconnect.apms.collect.model.basic.DeviceInformation");
    }

    @Override // com.huawei.agconnect.apms.def
    public String fgh() {
        w wVar;
        synchronized (w.class) {
            if (w.bcd == null) {
                w.bcd = new w();
            }
            wVar = w.bcd;
        }
        t tVar = wVar.cde;
        ScheduledFuture scheduledFuture = tVar.efg;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(false);
            tVar.efg = null;
            tVar.fgh = -1L;
        }
        v vVar = wVar.def;
        ScheduledFuture scheduledFuture2 = vVar.efg;
        if (scheduledFuture2 != null) {
            scheduledFuture2.cancel(false);
            vVar.efg = null;
            vVar.fgh = -1L;
        }
        w.abc.debug(String.format(Locale.ENGLISH, "stop collect cpu and memory.", new Object[0]));
        CPUMemoryEvent cPUMemoryEvent = new CPUMemoryEvent();
        cPUMemoryEvent.setTimestamp(System.currentTimeMillis());
        while (!wVar.cde.ijk.isEmpty()) {
            cPUMemoryEvent.getCpuResourceList().add(wVar.cde.ijk.poll());
        }
        while (!wVar.def.ghi.isEmpty()) {
            cPUMemoryEvent.getAppMemoryResourceList().add(wVar.def.ghi.poll());
        }
        return cPUMemoryEvent.changeCpuAndMemoryAsJsonObject().toString();
    }

    public final void ghi(boolean z) {
        if (z) {
            try {
                nop.abc(true);
            } catch (Throwable th) {
                abc.error("failed to stop APMS. " + th.getMessage());
                return;
            }
        }
        Future future = yza.def;
        if (future != null) {
            future.cancel(true);
            yza.def = null;
        }
        if (nop.bcd()) {
            nop.def();
            nop nopVar = nop.bcd;
            stu stuVar = nopVar.efg;
            if (stuVar != null) {
                stuVar.abc();
                stuVar.bcd.shutdownNow();
            }
            CollectData collectData = nopVar.fgh;
            if (collectData != null) {
                collectData.shutDownCollectData();
            }
            nopVar.efg = null;
            nopVar.def = null;
            nopVar.fgh = null;
        }
        Future future2 = rst.def;
        if (future2 != null) {
            future2.cancel(true);
            rst.def = null;
        }
        AgentLog agentLog = i0.abc;
        i0 i0Var = i0.abc.abc;
        if (i0Var.cde) {
            Looper.getMainLooper().setMessageLogging(null);
            h0 h0Var = i0Var.bcd;
            h0Var.fgh.abc();
            HandlerThread handlerThread = h0Var.cde;
            if (handlerThread != null) {
                handlerThread.quit();
            }
            i0Var.bcd = null;
            i0Var.cde = false;
            i0.abc.debug("stop trace");
        }
    }

    @Override // com.huawei.agconnect.apms.def
    public UserSettingsInformation hij() {
        UserSettingsInformation userSettingsInformation = this.hij;
        if (userSettingsInformation != null) {
            return userSettingsInformation;
        }
        UserSettingsInformation userSettingsInformation2 = new UserSettingsInformation();
        TimeZone timeZone = TimeZone.getDefault();
        userSettingsInformation2.setTimeZone(timeZone.getID() + "/" + timeZone.getDisplayName(false, 0));
        Context context = this.cde;
        if (context == null || context.getResources() == null || this.cde.getResources().getConfiguration() == null || this.cde.getResources().getConfiguration().locale == null) {
            userSettingsInformation2.setDefaultLanguage("");
        } else {
            Locale locale = this.cde.getResources().getConfiguration().locale;
            userSettingsInformation2.setDefaultLanguage(locale.getLanguage() + "_" + locale.getCountry());
        }
        this.hij = userSettingsInformation2;
        return userSettingsInformation2;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(16:3|(2:4|5)|(1:7)(2:66|(13:69|9|10|11|(8:13|14|15|16|17|(1:19)|(1:56)(3:24|(3:26|(2:28|(2:31|32)(1:30))|34)|(2:36|(1:(1:55)(2:38|(5:41|42|(1:44)(1:54)|45|(2:47|(2:49|(1:51)(0))(1:52))(1:53))(1:40))))(0))|33)|63|15|16|17|(0)|(1:22)|56|33))|8|9|10|11|(0)|63|15|16|17|(0)|(0)|56|33) */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x009d, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x009e, code lost:
    
        com.huawei.agconnect.apms.d0.abc.warn("cannot get network state, ensure permission android.permission.ACCESS_NETWORK_STATE in the manifest: " + r0.getMessage());
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x006e, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x006f, code lost:
    
        com.huawei.agconnect.apms.a0.abc.warn("failed to get device battery percentage: " + r0.getMessage());
     */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0058 A[Catch: all -> 0x006e, TRY_LEAVE, TryCatch #5 {all -> 0x006e, blocks: (B:11:0x004d, B:13:0x0058), top: B:10:0x004d }] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0098 A[Catch: all -> 0x009d, TRY_LEAVE, TryCatch #4 {all -> 0x009d, blocks: (B:17:0x008e, B:19:0x0098), top: B:16:0x008e }] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00b7  */
    @Override // com.huawei.agconnect.apms.def
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.huawei.agconnect.apms.collect.model.basic.RuntimeEnvInformation ijk() {
        /*
            Method dump skipped, instructions count: 566
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.agconnect.apms.bcd.ijk():com.huawei.agconnect.apms.collect.model.basic.RuntimeEnvInformation");
    }

    @Override // com.huawei.agconnect.apms.def
    public Context jkl() {
        return this.cde;
    }

    @Override // com.huawei.agconnect.apms.def
    public PlatformInformation klm() {
        String str = "";
        PlatformInformation platformInformation = this.ghi;
        if (platformInformation != null) {
            return platformInformation;
        }
        PlatformInformation platformInformation2 = new PlatformInformation();
        platformInformation2.setOsVersion(Build.VERSION.RELEASE);
        platformInformation2.setRomName(a0.bcd());
        try {
            Object abc2 = a0.abc() ? a0.abc("android.os.SystemProperties", "get", new Class[]{String.class, String.class}, new Object[]{"ro.build.version.magic", ""}) : a0.abc("android.os.SystemProperties", "get", new Class[]{String.class, String.class}, new Object[]{"ro.build.version.emui", ""});
            if (abc2 != null) {
                str = (String) abc2;
            }
        } catch (Throwable th) {
            a0.abc.warn("failed to get EMUI version: " + th.getMessage());
        }
        platformInformation2.setRomVersion(str);
        this.ghi = platformInformation2;
        return platformInformation2;
    }

    @Override // com.huawei.agconnect.apms.def
    public boolean lmn() {
        return !this.efg.fgh;
    }

    @Override // com.huawei.agconnect.apms.def
    public cde mno() {
        return this.efg;
    }

    @Override // com.huawei.agconnect.apms.def
    public ApplicationInformation nop() {
        return this.ijk;
    }

    @Override // com.huawei.agconnect.apms.def
    public boolean opq() {
        return this.efg.ghi;
    }

    public final int pqr() {
        WindowManager windowManager = (WindowManager) this.cde.getSystemService(Constants.NATIVE_WINDOW_SUB_DIR);
        if (windowManager != null) {
            return (int) windowManager.getDefaultDisplay().getRefreshRate();
        }
        return 0;
    }

    public final void qrs() throws APMSException {
        String str;
        if (this.ijk != null) {
            return;
        }
        this.ijk = new ApplicationInformation();
        String packageName = this.cde.getPackageName();
        AgentLog agentLog = abc;
        agentLog.debug("using app packageId " + packageName);
        this.ijk.setPackageId(packageName);
        PackageManager packageManager = this.cde.getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(packageName, 0);
            if (packageInfo == null || (str = packageInfo.versionName) == null || str.length() <= 0) {
                throw new APMSException("the app doesn't have a version defined, ensure 'versionName' has been defined in build.gradle or AndroidManifest.xml.");
            }
            String str2 = packageInfo.versionName;
            agentLog.debug("using app version " + str2);
            this.ijk.setAppVersion(str2);
            int i = packageInfo.versionCode;
            agentLog.debug("using app versionCode " + i);
            this.ijk.setVersionCode(i);
            try {
                packageName = packageManager.getApplicationLabel(packageManager.getApplicationInfo(packageName, 0)).toString();
            } catch (Throwable th) {
                abc.warn("failed to get appName, use packageName instead: " + th.getMessage());
            }
            abc.debug("using app name " + packageName);
            this.ijk.setAppName(packageName);
        } catch (Throwable th2) {
            throw new APMSException("could not get package version: " + th2.getMessage());
        }
    }

    public final void rst() {
        nop.abc(this.def);
        nop.abc(o0.abc);
        cde cdeVar = this.efg;
        try {
            nop nopVar = nop.bcd;
            if (nopVar != null) {
                nopVar.abc(cdeVar);
                for (qrs qrsVar : nop.cde) {
                    if (qrsVar != null) {
                        nop.bcd.def.abc(qrsVar);
                    }
                }
                nop.cde.clear();
            }
        } catch (Throwable th) {
            nop.abc.error("apms initialize failed, detail is " + th.getMessage());
        }
        nop.abc(this.def.abc);
        if (rst.def == null) {
            rst.def = rst.bcd.scheduleAtFixedRate(rst.efg, 0L, 1000L, TimeUnit.MILLISECONDS);
        }
        if (yza.def == null) {
            yza.def = yza.bcd.scheduleAtFixedRate(yza.efg, 0L, 1000L, TimeUnit.MILLISECONDS);
        }
        if (this.efg.hij) {
            AgentLog agentLog = i0.abc;
            i0 i0Var = i0.abc.abc;
            if (!i0Var.cde) {
                if (i0Var.bcd == null) {
                    i0Var.bcd = new h0();
                }
                Looper.getMainLooper().setMessageLogging(i0Var.bcd);
                i0Var.cde = true;
                i0.abc.debug("start trace");
            }
        }
        abc.info("APMS v" + Agent.getVersion());
    }

    public final Context abc(Context context) {
        return context instanceof Application ? context : context.getApplicationContext();
    }

    @Override // com.huawei.agconnect.apms.def
    public void def(boolean z) {
        synchronized (this.klm) {
            this.efg.ghi = z;
        }
    }

    @Override // com.huawei.agconnect.apms.def
    public void abc(boolean z) {
        this.efg.efg = z;
        this.def.bcd("apms.enable_anr_monitor", z);
    }

    @Override // com.huawei.agconnect.apms.def
    public void cde(boolean z) {
        synchronized (this.klm) {
            this.efg.fgh = z;
            this.def.bcd("apms.enable_web_view_monitor", z);
        }
    }

    @Override // com.huawei.agconnect.apms.def
    public void abc(String str) {
        this.jkl = str;
    }

    @Override // com.huawei.agconnect.apms.j
    public void abc(i iVar) {
        abc.debug("APMS: application foregrounded.");
        cde();
    }

    public final boolean abc(String str, String str2) {
        if (str != null && str.length() != 0 && str2 != null && str2.length() != 0) {
            for (String str3 : str.split(",")) {
                if (str2.trim().equalsIgnoreCase(str3.trim())) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override // com.huawei.agconnect.apms.def
    public void abc(String str, Map<String, String> map) {
        if (bcd() || map == null || map.size() == 0) {
            return;
        }
        Agent.getExecutor().execute(new abc(str, map));
    }

    @Override // com.huawei.agconnect.apms.def
    public void abc(int i) {
        synchronized (this.klm) {
            cde cdeVar = this.efg;
            cdeVar.getClass();
            int i2 = 2;
            if (i > 2) {
                i2 = Math.min(i, 60);
            }
            cdeVar.ijk = ((i2 + 1) * 1000) / cdeVar.jkl;
        }
    }

    @Override // com.huawei.agconnect.apms.def
    public void fgh(boolean z) {
        synchronized (this.klm) {
            this.efg.hij = z;
        }
    }

    @Override // com.huawei.agconnect.apms.def
    public String ghi() {
        return TextUtils.isEmpty(this.jkl) ? "" : this.jkl;
    }

    @Override // com.huawei.agconnect.apms.def
    public void bcd(boolean z) {
        synchronized (this.klm) {
            try {
                boolean z2 = !z;
                this.efg.bcd = z2;
                this.def.bcd("apms.disable_collection", z2);
            } finally {
                if (z) {
                    abc.info("APMS: enable collection.");
                    cde();
                } else {
                    abc.info("APMS: disable collection.");
                    ghi(false);
                }
            }
        }
    }

    @Override // com.huawei.agconnect.apms.def
    public void bcd(String str) {
        synchronized (this.klm) {
            abc.info("APMS: disable collection for app versions: " + str);
            try {
                this.efg.cde = str;
                ghi ghiVar = this.def;
                ghiVar.def.lock();
                try {
                    ghiVar.cde.putString("apms.disabled_app_versions", str);
                    ghiVar.cde.apply();
                } finally {
                    ghiVar.def.unlock();
                }
            } finally {
                if (abc(str, this.ijk.getAppVersion())) {
                    ghi(false);
                } else {
                    cde();
                }
            }
        }
    }

    @Override // com.huawei.agconnect.apms.def
    public boolean bcd() {
        cde cdeVar = this.efg;
        return cdeVar.abc || cdeVar.bcd || abc(cdeVar.cde, this.ijk.getAppVersion());
    }

    @Override // com.huawei.agconnect.apms.j
    public void bcd(i iVar) {
        abc.debug("APMS: application backgrounded.");
        ghi(true);
    }

    @Override // com.huawei.agconnect.apms.def
    public void efg(boolean z) {
        synchronized (this.klm) {
            if (z) {
                abc.info("APMS: enable collection by user.");
                try {
                    this.efg.abc = false;
                    this.def.bcd("apms.disable_collection_by_user", false);
                    cde();
                } catch (Throwable th) {
                    cde();
                    throw th;
                }
            } else {
                abc.info("APMS: disable collection by user.");
                try {
                    this.efg.abc = true;
                    this.def.bcd("apms.disable_collection_by_user", true);
                } finally {
                    ghi(false);
                }
            }
        }
    }
}
