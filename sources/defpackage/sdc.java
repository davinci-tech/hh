package defpackage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import com.huawei.haf.router.AppRouterExtras;
import com.huawei.health.servicesui.R$string;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.operation.utils.Constants;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import defpackage.sdc;
import health.compact.a.util.LogUtil;

/* loaded from: classes7.dex */
public class sdc {
    public static void e(final Context context, int i, int i2) {
        if (context instanceof Activity) {
            NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(context);
            builder.e(context.getString(i2)).czE_(context.getString(i), new View.OnClickListener() { // from class: com.huawei.ui.main.stories.utils.NoBindDeviceDialogUtil$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    sdc.dWf_(context, view);
                }
            }).czA_(context.getString(R$string.IDS_settings_button_cancal_ios_btn), new View.OnClickListener() { // from class: sdd
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    sdc.dWg_(view);
                }
            });
            builder.e().show();
        }
    }

    public static /* synthetic */ void dWf_(Context context, View view) {
        e(context);
        ViewClickInstrumentation.clickOnView(view);
    }

    static /* synthetic */ void dWg_(View view) {
        LogUtil.d("NoBindDeviceDialogUtil", "onClick negative button");
        ViewClickInstrumentation.clickOnView(view);
    }

    private static void e(Context context) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent();
        intent.setClassName(context, "com.huawei.health.MainActivity");
        intent.setFlags(AppRouterExtras.COLDSTART);
        intent.putExtra(Constants.HOME_TAB_NAME, "DEVICE");
        intent.putExtra("SHORTCUT", "SC_DEVICE");
        gnm.aPB_(context, intent);
    }
}
