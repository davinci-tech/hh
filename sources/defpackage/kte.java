package defpackage;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.UiModeManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.huawei.android.os.SystemPropertiesEx;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hms.android.SystemUtils;
import com.huawei.openalliance.ad.constant.ParamConstants;
import defpackage.kse;

/* loaded from: classes5.dex */
public class kte {
    public static int a(Context context) {
        if (!d()) {
            return d(context);
        }
        if (context == null) {
            ksy.b("UIUtil", "getDialogThemeId, context is null", true);
            return 3;
        }
        if (b(context) != 0) {
            ksy.b("UIUtil", "getDialogThemeId, NEEDLESS_SETTING_THEME", true);
            return 0;
        }
        ksy.b("UIUtil", "getDialogThemeId, THEME_HOLO_LIGHT", true);
        return 3;
    }

    public static int b(Context context) {
        if (!d()) {
            return d(context);
        }
        return context.getResources().getIdentifier("androidhwext:style/Theme.Emui", null, null);
    }

    public static int d(Context context) {
        ksy.b("UIUtil", "getDialogThemeId, non huawei rom", true);
        if (c(context)) {
            ksy.b("UIUtil", "getDialogThemeId, non huawei rom , is DarkTheme", true);
            return 4;
        }
        ksy.b("UIUtil", "getDialogThemeId, non huawei rom , is not DarkTheme", true);
        return 5;
    }

    private static boolean d() {
        boolean z = !TextUtils.isEmpty(ksi.q());
        ksy.b("UIUtil", "isHuawei " + z, true);
        return z;
    }

    public static void bQO_(Dialog dialog) {
        if (kst.e()) {
            try {
                dialog.getWindow().getAttributes().getClass().getDeclaredField(ParamConstants.Param.LAYOUT_IN_DISPLAY_CUTOUT_MODE).set(dialog.getWindow().getAttributes(), 1);
            } catch (RuntimeException e) {
                ksy.c("UIUtil", "RuntimeException: " + e.getClass().getSimpleName(), true);
            } catch (Exception e2) {
                ksy.c("UIUtil", "Exception: " + e2.getClass().getSimpleName(), true);
            }
        }
    }

    public static AlertDialog.Builder a(final Context context, String str, String str2, DialogInterface.OnClickListener onClickListener) {
        ksy.b("UIUtil", "createRefusePermissionBuild", true);
        kse.a aVar = new kse.a(context, a(context));
        View inflate = View.inflate(context, R.layout.hwid_auth_refuse_permission_dialog, null);
        TextView textView = (TextView) inflate.findViewById(R.id.text1);
        if (kst.d()) {
            textView.setTextSize(0, context.getResources().getDimensionPixelSize(R.dimen._2131362720_res_0x7f0a03a0));
        }
        textView.setText(str);
        ((TextView) inflate.findViewById(R.id.text2)).setText(str2);
        aVar.setView(inflate).setPositiveButton(R.string._2130841080_res_0x7f020df8, new DialogInterface.OnClickListener() { // from class: kte.3
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                kte.j(context);
                ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
            }
        }).setNegativeButton(android.R.string.cancel, onClickListener);
        return aVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void j(Context context) {
        if (context == null) {
            ksy.c("UIUtil", "context is null.", true);
        } else {
            b(context, Integer.MIN_VALUE);
        }
    }

    private static void b(Context context, int i) {
        if (context == null) {
            ksy.c("UIUtil", "context is null.", true);
            return;
        }
        try {
            ksy.b("UIUtil", "gotoAppDetailForResult", true);
            Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
            intent.setData(Uri.parse("package:" + context.getPackageName()));
            intent.setFlags(268435456);
            if (i < 0) {
                context.startActivity(intent);
            } else {
                ((Activity) context).startActivityForResult(intent, i);
            }
        } catch (Exception e) {
            ksy.c("UIUtil", e.getClass().getSimpleName(), true);
        }
    }

    public static AlertDialog.Builder bQP_(Context context) {
        if (context == null) {
            ksy.c("UIUtil", "createNoNetDialog context is null", true);
            return null;
        }
        kse.a aVar = new kse.a(context, a(context));
        aVar.setMessage(R.string._2130841083_res_0x7f020dfb);
        aVar.setNegativeButton(android.R.string.ok, new DialogInterface.OnClickListener() { // from class: kte.5
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
            }
        });
        return aVar;
    }

    public static boolean c(Context context) {
        Resources resources;
        return (context == null || (resources = context.getResources()) == null || c(context, "hwid_check_theme_color") != resources.getColor(R.color._2131298391_res_0x7f090857)) ? false : true;
    }

    private static int c(Context context, String str) {
        return context.getResources().getColor(context.getResources().getIdentifier(context.getPackageName() + ":color/" + str, null, null));
    }

    public static String g(Context context) {
        return f(context.getApplicationContext()) ? "&themeName=dark" : "&themeName=huawei";
    }

    private static boolean f(Context context) {
        boolean i = i(context);
        boolean c = c(context);
        boolean n = n(context);
        ksy.b("UIUtil", "isHonorNight: " + i, true);
        ksy.b("UIUtil", "isDarkTheme: " + c, true);
        ksy.b("UIUtil", "isNightMode: " + n, true);
        return i || c || n;
    }

    private static boolean i(Context context) {
        if (kst.d()) {
            return "honor".equalsIgnoreCase(SystemPropertiesEx.get(SystemUtils.PRODUCT_BRAND)) && !SystemPropertiesEx.getBoolean("ro.config.hw_themeInsulate", false) && ((UiModeManager) context.getSystemService(UiModeManager.class)).getNightMode() == 2;
        }
        return false;
    }

    private static boolean n(Context context) {
        Resources resources;
        Configuration configuration;
        return (context == null || (resources = context.getResources()) == null || (configuration = resources.getConfiguration()) == null || (configuration.uiMode & 48) != 32) ? false : true;
    }
}
