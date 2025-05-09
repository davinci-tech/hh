package defpackage;

import android.content.Context;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.thirdpartservice.interactors.thirdpartyservice.googlefit.GoogleFitDataManage;
import health.compact.a.CommonUtil;

/* loaded from: classes6.dex */
public class odl {
    public static void e(Context context) {
        LogUtil.c("Step_GoalValue", "doSyncThirdPartService start.");
        if (CommonUtil.bx()) {
            b(context);
        }
    }

    private static void b(Context context) {
        if (!CommonUtil.aa(context)) {
            LogUtil.b("Step_GoalValue", "doSyncGoogleFit network is not connected!");
            return;
        }
        boolean c = shu.b().c();
        LogUtil.a("Step_GoalValue", "doSyncGoogleFit.isConnect = ", Boolean.valueOf(c));
        if (c) {
            new GoogleFitDataManage(context).b();
            LogUtil.a("Step_GoalValue", "doSyncGoogleFit end");
        }
    }
}
