package defpackage;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import health.compact.a.CommonUtils;

/* loaded from: classes4.dex */
public class foq {

    /* renamed from: a, reason: collision with root package name */
    private final Dialog f12587a;
    private Context b;

    private foq(Activity activity) {
        this.b = activity;
        this.f12587a = new Dialog(activity, R.style.sugDialogStyle);
    }

    public static foq aBU_(Activity activity) {
        return new foq(activity);
    }

    public foq a(int i) {
        this.f12587a.setContentView(i);
        this.f12587a.setCanceledOnTouchOutside(true);
        return this;
    }

    public foq d(int i) {
        this.f12587a.setContentView(i);
        this.f12587a.setCanceledOnTouchOutside(false);
        return this;
    }

    public Dialog aBV_() {
        return this.f12587a;
    }

    public foq aBW_(int i, View.OnClickListener onClickListener) {
        if (this.f12587a.findViewById(i) != null) {
            this.f12587a.findViewById(i).setOnClickListener(onClickListener);
        }
        return this;
    }

    public foq e(int i, int i2) {
        if (this.f12587a.findViewById(i) != null) {
            if (this.f12587a.findViewById(i) instanceof HealthTextView) {
                ((HealthTextView) this.f12587a.findViewById(i)).setText(i2);
            } else {
                LogUtil.h("Suggestion_DialogHelper", "mDialog.findViewById(layoutId) not instanceof HealthTextView");
            }
        }
        return this;
    }

    public foq d(int i, int i2) {
        if (this.f12587a.findViewById(i) != null) {
            if (this.f12587a.findViewById(i) instanceof HealthButton) {
                ((HealthButton) this.f12587a.findViewById(i)).setText(i2);
            } else {
                LogUtil.h("Suggestion_DialogHelper", "mDialog.findViewById(layoutId) not instanceof HealthButton");
            }
        }
        return this;
    }

    public foq a(String str) {
        Context context = this.b;
        if (context == null) {
            return e();
        }
        if ((context instanceof BaseActivity) && ((BaseActivity) context).isSupportFoldingModel()) {
            this.f12587a.dismiss();
            WindowManager.LayoutParams attributes = this.f12587a.getWindow().getAttributes();
            WindowManager xF_ = CommonUtils.xF_();
            if ("HORIZONTAL_FOLDING_MODE".equals(str) && xF_ != null) {
                DisplayMetrics displayMetrics = new DisplayMetrics();
                xF_.getDefaultDisplay().getMetrics(displayMetrics);
                attributes.y = displayMetrics.heightPixels / 4;
                this.f12587a.getWindow().setAttributes(attributes);
            } else {
                attributes.y = 0;
                attributes.gravity = 17;
                this.f12587a.getWindow().setAttributes(attributes);
            }
            this.f12587a.show();
            return this;
        }
        return e();
    }

    public foq e() {
        this.f12587a.show();
        return this;
    }

    public foq a() {
        this.f12587a.dismiss();
        return this;
    }

    public boolean c() {
        return this.f12587a.isShowing();
    }
}
