package defpackage;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import java.util.Locale;

/* loaded from: classes8.dex */
public class sec {
    public static void d(Context context, int i, int i2) {
        if (context instanceof Activity) {
            NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(context);
            builder.e(context.getString(i2)).czE_(context.getString(i).toUpperCase(Locale.ENGLISH), new View.OnClickListener() { // from class: sec.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    LogUtil.c("DialogUtils", "onclick");
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
            NoTitleCustomAlertDialog e = builder.e();
            e.setCancelable(false);
            Activity activity = (Activity) context;
            if (activity.isDestroyed() || activity.isFinishing()) {
                return;
            }
            e.show();
        }
    }

    public static boolean b(Context context, String str) {
        if (jdm.b(context, str)) {
            return true;
        }
        LogUtil.h("DialogUtils", "isInstallApp the APP is not installed");
        return false;
    }
}
