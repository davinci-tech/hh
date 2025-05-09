package defpackage;

import android.app.Activity;
import android.app.Dialog;
import android.content.res.Resources;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.huawei.health.R;
import com.huawei.health.device.ui.DeviceMainActivity;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;

/* loaded from: classes3.dex */
public class cnz {

    /* renamed from: a, reason: collision with root package name */
    private HealthTextView f806a;
    private Handler b = new Handler();
    private Activity c;
    private Dialog d;
    private int e;

    public cnz(Activity activity) {
        this.c = activity;
        this.d = new Dialog(this.c, R.style.dialog_toast_style);
        View inflate = LayoutInflater.from(activity).inflate(R.layout.dialog_toast_layout, (ViewGroup) null);
        this.d.setContentView(inflate);
        this.f806a = (HealthTextView) nsy.cMd_(inflate, R.id.hw_dialog_toast_message);
    }

    public void a(CharSequence charSequence, int i) {
        this.e = i;
        this.f806a.setText(charSequence);
        LogUtil.a("DialogToToast", "dialog toast message:", charSequence, ",duration:", Integer.valueOf(i));
    }

    public void Js_(Activity activity, int i, int i2) {
        String str;
        if (activity == null) {
            LogUtil.h("DialogToToast", "makeText context is null");
            return;
        }
        try {
            str = activity.getString(i);
        } catch (Resources.NotFoundException unused) {
            LogUtil.b("DialogToToast", "makeText Exception : content is null");
            str = "";
        }
        a(str, i2);
    }

    public void c() {
        LogUtil.a("DialogToToast", "dialog toast show");
        Activity activity = this.c;
        if (activity == null) {
            LogUtil.h("DialogToToast", "show mContext is null");
            return;
        }
        if (!(activity instanceof DeviceMainActivity)) {
            LogUtil.h("DialogToToast", "mMainActivity not instanceof DeviceMainActivity");
            return;
        }
        if (activity.isDestroyed() || this.c.isFinishing()) {
            LogUtil.h("DialogToToast", "show Activity is destroyed or finishing");
            return;
        }
        if (this.d.isShowing()) {
            this.d.dismiss();
        }
        this.d.show();
        this.b.postDelayed(new Runnable() { // from class: cnz.2
            @Override // java.lang.Runnable
            public void run() {
                cnz.this.d.dismiss();
            }
        }, this.e);
    }
}
