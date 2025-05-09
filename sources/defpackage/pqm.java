package defpackage;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import com.huawei.haf.common.os.SystemInfo;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.ui.main.R$string;
import health.compact.a.HuaweiHealth;
import health.compact.a.LanguageUtil;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

/* loaded from: classes6.dex */
public class pqm {
    public static void c(pqe pqeVar, int i) {
        if (pqeVar == null) {
            LogUtil.b("FaCardUtils", "jumpToServiceExpressCard faCardHelper == null");
        } else if (!c()) {
            LogUtil.b("FaCardUtils", "jumpToServiceExpressCard not SupportFaCardShow");
        } else {
            pqeVar.d(i);
        }
    }

    public static void d(pqe pqeVar) {
        if (pqeVar == null) {
            LogUtil.b("FaCardUtils", "setCardClickedSp faCardHelper == null");
        } else if (!c()) {
            LogUtil.b("FaCardUtils", "setCardClickedSp not SupportFaCardShow");
        } else {
            pqeVar.e();
        }
    }

    public static boolean b(pqe pqeVar) {
        if (pqeVar == null) {
            LogUtil.b("FaCardUtils", "isCardHaveClicked faCardHelper == null");
            return true;
        }
        if (!c()) {
            LogUtil.b("FaCardUtils", "isCardHaveClicked not SupportFaCardShow");
            return true;
        }
        return pqeVar.d();
    }

    public static void c(pqe pqeVar) {
        if (pqeVar == null) {
            LogUtil.b("FaCardUtils", "close faCardHelper == null");
        } else if (!c()) {
            LogUtil.b("FaCardUtils", "close not SupportFaCardShow");
        } else {
            pqeVar.b();
        }
    }

    public static pqe dsg_(Activity activity, View view, int i) {
        if (!c()) {
            LogUtil.b("FaCardUtils", "createFaCardHelper not SupportFaCardShow");
            return null;
        }
        return new pqe(activity, view, i);
    }

    public static boolean c() {
        return LoginInit.getInstance(HuaweiHealth.a()).getIsLogined() && SystemInfo.b() && !LanguageUtil.bi(HuaweiHealth.a()) && !Utils.o();
    }

    public static void e(pqe pqeVar, int i) {
        if (pqeVar == null) {
            LogUtil.b("FaCardUtils", "close faCardHelper == null");
        } else if (!c()) {
            LogUtil.b("FaCardUtils", "close not SupportFaCardShow");
        } else {
            pqeVar.a(i);
        }
    }

    public static int b(int i) {
        if (i == 0) {
            return 3;
        }
        if (i == 1) {
            return 2;
        }
        LogUtil.b("FaCardUtils", "type error: ", Integer.valueOf(i));
        return -1;
    }

    public static void dsh_(int i, int i2, Intent intent) {
        int i3;
        int i4;
        if (i == 0) {
            i4 = 300;
            i3 = 400;
        } else {
            i3 = 500;
            i4 = 200;
        }
        if (i2 == i4 || i2 == i3) {
            int loadResultCode = dlf.c().getLoadResultCode(intent);
            LogUtil.a("FaCardUtils", "onActivityResult loadResultCode:", Integer.valueOf(loadResultCode), " requestCode:", Integer.valueOf(i2));
            dlf.c().addToDeskTopBi(intent, i2 == 200 ? 3 : 2);
            if (loadResultCode == 3) {
                ReleaseLogUtil.c("R_Sleep_FaCardUtils", "onActivityResult");
                nrh.b(BaseApplication.getContext(), R$string.wallet_unenable_country);
            }
        }
    }
}
