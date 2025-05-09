package health.compact.a;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.agconnect.AGConnectInstance;
import com.huawei.agconnect.apms.APMS;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.dfx.DfxBaseHandler;
import com.huawei.haf.common.dfx.DfxMonitorMgr;
import com.huawei.haf.common.dfx.block.MainThreadMonitor;
import com.huawei.haf.common.dfx.block.ThreadPoolMonitor;
import com.huawei.haf.common.dfx.crash.CrashMonitor;
import com.huawei.haf.common.dfx.memory.MemoryMonitor;
import com.huawei.haf.common.dfx.process.ProcessMonitor;
import com.huawei.haf.common.dfx.storage.StorageMonitor;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.hwappdfxmgr.crashmgr.HealthAnrHandler;
import com.huawei.hwappdfxmgr.crashmgr.HealthCrashHandler;
import com.huawei.hwappdfxmgr.crashmgr.HealthMemoryHandler;
import com.huawei.hwappdfxmgr.crashmgr.HealthThreadPoolHandler;
import com.huawei.login.ui.login.LoginInit;
import defpackage.ixh;
import defpackage.ixi;
import defpackage.ixl;
import defpackage.ixm;
import java.io.File;
import java.util.List;

/* loaded from: classes.dex */
public class HealthDfxMonitorMgr implements DfxMonitorMgr {
    @Override // com.huawei.haf.common.dfx.DfxMonitorMgr
    public void onTrimMemory(Context context, String str, int i) {
    }

    @Override // com.huawei.haf.common.dfx.DfxMonitorMgr
    public void installMonitor(Context context) {
        DfxBaseHandler.setLogRootDir(new File(CommonUtil.n(context)));
        HealthCrashHandler healthCrashHandler = new HealthCrashHandler();
        healthCrashHandler.c();
        CrashMonitor.e(healthCrashHandler);
        ThreadPoolMonitor.a(new HealthThreadPoolHandler());
        if (!EnvironmentUtils.c()) {
            MainThreadMonitor.a(new HealthAnrHandler());
        } else {
            StorageMonitor.d(new ixh());
        }
        MemoryMonitor.a(new HealthMemoryHandler());
        if (com.huawei.hwlogsmodel.common.LogConfig.a()) {
            MemoryMonitor.d();
        }
        ixm.c(new ixl());
        List<String> d = ixi.d();
        if (d.contains(ProcessUtil.b())) {
            ProcessMonitor.a(d, new ixi());
        }
        d();
        c();
    }

    @Override // com.huawei.haf.common.dfx.DfxMonitorMgr
    public void uninstallMonitor(Context context) {
        MemoryMonitor.c();
        MainThreadMonitor.e();
        ThreadPoolMonitor.d();
        StorageMonitor.d();
        CrashMonitor.e();
        ixm.c();
        ProcessMonitor.e();
        d();
    }

    private boolean e() {
        return !Utils.o() && (CommonUtil.as() || CommonUtil.bv()) && !EnvironmentUtils.b() && AuthorizationUtils.a(null);
    }

    private void c() {
        if (e()) {
            if (AGConnectInstance.getInstance() == null) {
                AGConnectInstance.initialize(BaseApplication.e());
            }
            APMS.getInstance().start(BaseApplication.e());
            ThreadPoolManager.d().execute(new Runnable() { // from class: health.compact.a.HealthDfxMonitorMgr.2
                @Override // java.lang.Runnable
                public void run() {
                    APMS.getInstance().setCollectionUrl(GRSManager.a(com.huawei.hwcommonmodel.application.BaseApplication.getContext()).getUrl("biOperation"));
                    APMS.getInstance().enableAnrMonitor(true);
                    APMS.getInstance().enableCollection(true);
                    String accountInfo = LoginInit.getInstance(BaseApplication.e()).getAccountInfo(1011);
                    if (TextUtils.isEmpty(accountInfo)) {
                        return;
                    }
                    String e = Sha256.e(accountInfo, "SHA-256");
                    com.huawei.hwlogsmodel.LogUtil.a("HealthDfxMonitorMgr", "For AiOps, user huid is ", e);
                    APMS.getInstance().setUserIdentifier(e);
                }
            });
        }
    }

    private void d() {
        if (e()) {
            if (AGConnectInstance.getInstance() == null) {
                AGConnectInstance.initialize(BaseApplication.e());
            }
            APMS.getInstance().enableAnrMonitor(false);
            APMS.getInstance().enableCollection(false);
        }
    }
}
