package defpackage;

import android.content.Context;
import android.content.pm.PackageInfo;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwversionmgr.utils.handler.AppCheckNewVersionHandler;
import com.huawei.hwversionmgr.utils.handler.AppDownloadHandler;
import com.huawei.hwversionmgr.utils.handler.AppPullChangeLogHandler;
import health.compact.a.CommonUtil;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes5.dex */
public class kxs {
    private static ExecutorService d = Executors.newFixedThreadPool(3);

    public void c(Context context, AppPullChangeLogHandler appPullChangeLogHandler, boolean z, boolean z2, boolean z3) {
        kym.d(false);
        new kym(context, appPullChangeLogHandler, z, z2, z3).e();
    }

    public void e(Context context, AppPullChangeLogHandler appPullChangeLogHandler, boolean z, String str, String str2) {
        LogUtil.a("HwSelfUpdate", "startAw70SaveChangeLog: ", str2);
        kym.d(false);
        kym.a(str2);
        kym.c(str);
        new kym(context, appPullChangeLogHandler, z, true, false).e();
    }

    public void a(Context context, AppPullChangeLogHandler appPullChangeLogHandler, boolean z, String str, String str2) {
        LogUtil.a("HwSelfUpdate", "startSaveChangeLog: ", str2);
        kym.d(false);
        kym.a(str2);
        kym.c(str);
        new kym(context, appPullChangeLogHandler, z, false, false).e();
    }

    public void b(Context context, String str, String str2) {
        kxu.d(context, str, str2);
    }

    public void a(Context context, AppDownloadHandler appDownloadHandler, boolean z, boolean z2, boolean z3) {
        e(Boolean.valueOf(z), z2, z3);
        d.execute(new kyq(context, appDownloadHandler, z, z2, z3));
        kxi kxiVar = new kxi();
        kxiVar.d(1);
        if (CommonUtil.br()) {
            kxiVar.b(CommonUtil.h());
        } else {
            kxiVar.b(CommonUtil.a(context, false));
        }
        if (z) {
            if (kxu.e() != null) {
                kxiVar.c(kxu.e().w());
            }
        } else if (z2) {
            if (kxu.d() != null) {
                kxiVar.c(kxu.d().w());
            }
        } else if (z3) {
            if (kxu.l() != null) {
                kxiVar.c(kxu.l().w());
            }
        } else if (kxu.b() != null) {
            kxiVar.c(kxu.b().w());
        }
        if (z2) {
            kxiVar.e(kxu.a(kxu.a(), context));
        } else if (z3) {
            kxiVar.e(kxu.a(kxu.n(), context));
        } else {
            kxiVar.e(kxu.a(kxu.j(), context));
        }
        kxiVar.d("");
        d.execute(new kyk(kxiVar, z, z3));
    }

    private void e(Boolean bool, boolean z, boolean z2) {
        LogUtil.c("HwSelfUpdate", "startDownloadApp isApp:", bool, " isAW70:", Boolean.valueOf(z), " isScale ", Boolean.valueOf(z2));
        long i = kxu.i();
        long currentTimeMillis = System.currentTimeMillis();
        LogUtil.c("HwSelfUpdate", "downloadStartTime = ", Long.valueOf(i), "; currentTime = ", Long.valueOf(currentTimeMillis));
        LogUtil.c("HwSelfUpdate", "start download");
        kyw.e(true);
        kxu.d(currentTimeMillis);
    }

    public void bSu_(PackageInfo packageInfo, kxn kxnVar, Context context, AppCheckNewVersionHandler appCheckNewVersionHandler) {
        LogUtil.c("HwSelfUpdate", "startCheckNewVersionForBone");
        if (kxnVar == null) {
            LogUtil.h("HwSelfUpdate", "versionForBoneInfo is null");
            return;
        }
        kys kysVar = new kys(context, packageInfo.packageName, appCheckNewVersionHandler, kxnVar);
        kysVar.bSP_(packageInfo);
        kysVar.d(kxnVar.i());
        kys.b(false);
        d.execute(kysVar);
    }

    public void bSv_(PackageInfo packageInfo, Context context, AppCheckNewVersionHandler appCheckNewVersionHandler, String str, String str2) {
        LogUtil.c("HwSelfUpdate", "startCheckScaleNewVersionForBone");
        kyt kytVar = new kyt(context, packageInfo.packageName, appCheckNewVersionHandler, str, str2);
        kytVar.bSW_(packageInfo);
        kys.b(false);
        d.execute(kytVar);
    }

    public void b() {
        LogUtil.c("HwSelfUpdate", "cancelDownloadApp");
        kxu.d(-1L);
        kyw.e(true);
    }
}
