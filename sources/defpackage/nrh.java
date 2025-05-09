package defpackage;

import android.content.Context;
import android.os.Build;
import android.os.Looper;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.core.view.GravityCompat;
import com.huawei.android.view.WindowManagerEx;
import com.huawei.android.widget.ToastEx;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;

/* loaded from: classes.dex */
public final class nrh {
    private static long b = 0;
    private static boolean d = true;
    private static Toast e;

    private nrh() {
    }

    public static void c(Context context, String str) {
        e();
        if (context == null || str == null) {
            return;
        }
        b(context, str, 1);
    }

    public static void e(Context context, int i) {
        e();
        if (context == null) {
            return;
        }
        b(context, i, 1);
    }

    public static void d(Context context, String str) {
        e();
        if (context == null || str == null) {
            return;
        }
        b(context, str, 0);
    }

    public static void b(Context context, int i) {
        e();
        if (context == null) {
            return;
        }
        b(context, i, 0);
    }

    public static void c(Context context, int i) {
        e();
        if (context == null) {
            LogUtil.h("ToastUtil", "showShortToastByFrequencyLimit, context is null");
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - b < 2000) {
            LogUtil.h("ToastUtil", "showShortToastByFrequencyLimit, show Short Toast too fast!");
        } else {
            b = currentTimeMillis;
            b(context, i, 0);
        }
    }

    public static void e() {
        synchronized (nrh.class) {
            Toast toast = e;
            if (toast != null) {
                toast.cancel();
                e = null;
            }
        }
    }

    private static void b(Context context, int i, int i2) {
        b(context, nsf.h(i), i2);
    }

    private static void b(final Context context, final String str, final int i) {
        if (BaseApplication.j() && Looper.getMainLooper() == Looper.myLooper()) {
            a(context, str, i, true);
        } else {
            HandlerExecutor.a(new Runnable() { // from class: nrh.5
                @Override // java.lang.Runnable
                public void run() {
                    nrh.a(context, str, i, false);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void a(Context context, String str, int i, boolean z) {
        try {
            Toast makeText = Toast.makeText(context, str, i);
            if (Build.VERSION.SDK_INT < 30 || z) {
                cGV_(makeText, com.huawei.hwcommonmodel.application.BaseApplication.getContext(), str, i);
                cGW_(makeText);
            }
            makeText.show();
            e = makeText;
        } catch (InflateException unused) {
            LogUtil.b("ToastUtil", "inflate toast layout fail");
        }
    }

    private static void cGV_(Toast toast, Context context, String str, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.commonui_toast_layout, (ViewGroup) null);
        HealthTextView healthTextView = (HealthTextView) inflate.findViewById(R.id.commonui_toast_text);
        healthTextView.setText(str);
        if (((int) healthTextView.getPaint().measureText(str)) + healthTextView.getPaddingStart() + healthTextView.getPaddingEnd() > healthTextView.getMaxWidth()) {
            healthTextView.setGravity(GravityCompat.START);
            healthTextView.setTextAlignment(5);
        }
        toast.setView(inflate);
        toast.setDuration(i);
        toast.setGravity(80, 0, 0);
    }

    private static void cGW_(Toast toast) {
        if (d) {
            try {
                new WindowManagerEx.LayoutParamsEx(ToastEx.getWindowParams(toast)).addHwFlags(128);
            } catch (Throwable th) {
                if (th instanceof ClassNotFoundException) {
                    d = false;
                }
                LogUtil.h("ToastUtil", "setShowExNonSystemOverlayWindows Throwable:", th.getCause());
            }
        }
    }
}
