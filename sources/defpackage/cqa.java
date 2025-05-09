package defpackage;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;

/* loaded from: classes3.dex */
public class cqa {

    /* renamed from: a, reason: collision with root package name */
    private static final Object f11409a = new Object();
    private static cqa d;

    public static cqa a() {
        cqa cqaVar;
        synchronized (f11409a) {
            if (d == null) {
                d = new cqa();
            }
            cqaVar = d;
        }
        return cqaVar;
    }

    public void Kq_(Context context, View view, double d2, boolean z) {
        int intValue;
        if (context == null) {
            LogUtil.h("PluginDevice_ScaleActivityHelper", "adjustForPad context is null");
            return;
        }
        if (context.getSystemService(Constants.NATIVE_WINDOW_SUB_DIR) instanceof WindowManager) {
            WindowManager windowManager = (WindowManager) context.getSystemService(Constants.NATIVE_WINDOW_SUB_DIR);
            DisplayMetrics displayMetrics = new DisplayMetrics();
            windowManager.getDefaultDisplay().getRealMetrics(displayMetrics);
            int i = displayMetrics.widthPixels;
            int i2 = displayMetrics.heightPixels;
            Object a2 = cri.a("screenHeight", new Integer(0));
            if ((a2 instanceof Integer) && (intValue = ((Integer) a2).intValue()) != 0) {
                i2 = Math.max(i2, intValue);
            }
            LogUtil.a("PluginDevice_ScaleActivityHelper", "adjustForPad height: ", Integer.valueOf(i2));
            int min = (int) (Math.min(i, i2 * 0.5d) * d2);
            if (z) {
                i = min;
            }
            view.setLayoutParams(new LinearLayout.LayoutParams(i, min));
            cri.b("screenHeight", Integer.valueOf(i2));
        }
    }

    public boolean d(int i) {
        LogUtil.a("PluginDevice_ScaleActivityHelper", "isNextStartPairGuide downloadStatus: ", Integer.valueOf(i));
        if (i == -2) {
            return true;
        }
        if (i == 0) {
            nrh.c(BaseApplication.getContext(), R.string.IDS_device_download_resource);
            return false;
        }
        if (i == 1) {
            return true;
        }
        LogUtil.a("PluginDevice_ScaleActivityHelper", "isNextStartPairGuide default downloadStatus:", Integer.valueOf(i));
        return true;
    }

    public boolean a(Context context, cld cldVar) {
        if (BluetoothAdapter.getDefaultAdapter().isEnabled()) {
            if (b(cldVar)) {
                return true;
            }
            d(context, context.getString(R.string.IDS_device_hygride_device_not_wake_up_prompt_content), context.getString(R.string.IDS_device_measureactivity_result_confirm));
            return false;
        }
        if (cldVar != null) {
            cldVar.b(1);
        } else {
            LogUtil.h("PluginDevice_ScaleActivityHelper", "bluetooth is off, but mWeightInteractor is null");
            nrh.b(context, R.string._2130843214_res_0x7f02164e);
        }
        return false;
    }

    private void d(Context context, String str, String str2) {
        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(context);
        builder.e(str).czE_(str2, new View.OnClickListener() { // from class: cqa.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        NoTitleCustomAlertDialog e = builder.e();
        e.setCancelable(false);
        e.show();
    }

    private boolean b(cld cldVar) {
        if (cldVar != null) {
            return cldVar.b();
        }
        return false;
    }

    public void b() {
        e();
    }

    private static void e() {
        synchronized (f11409a) {
            d = null;
        }
    }
}
