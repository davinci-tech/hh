package com.huawei.ui.commonui.dialog;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.ui.commonui.R$color;
import com.huawei.ui.commonui.R$drawable;
import com.huawei.ui.commonui.base.BaseDialog;
import com.huawei.ui.commonui.cardview.HealthCardView;
import com.huawei.ui.commonui.checkbox.HealthCheckBox;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.imageview.HealthImageView;
import defpackage.nrf;
import defpackage.nsn;
import defpackage.nsy;

/* loaded from: classes.dex */
public class PurchaseSuccessDialog extends BaseDialog {

    /* renamed from: a, reason: collision with root package name */
    private UiCallback<Boolean> f8818a;
    private HealthCheckBox b;
    private HealthTextView c;
    private HealthTextView d;
    private HealthTextView e;
    private HealthTextView f;
    private HealthImageView g;
    private HealthCardView h;
    private HealthTextView i;
    private HealthTextView j;
    private View k;

    private PurchaseSuccessDialog(Context context, Builder builder) {
        super(context, R.style.CustomDialog);
        if (builder == null) {
            return;
        }
        d(context, builder);
    }

    public static class Builder {

        /* renamed from: a, reason: collision with root package name */
        private String f8819a;
        private CompoundButton.OnCheckedChangeListener b;
        private final Context c;
        private String d;
        private UiCallback<Boolean> e;
        private int f;
        private View.OnClickListener h;
        private String i;

        public Builder(Context context) {
            this.c = context;
        }

        public Builder b(int i) {
            this.f = i;
            return this;
        }

        public Builder a(String str) {
            this.i = str;
            return this;
        }

        public Builder b(String str) {
            this.f8819a = str;
            return this;
        }

        public Builder c(String str) {
            this.d = str;
            return this;
        }

        public Builder czI_(View.OnClickListener onClickListener) {
            this.h = onClickListener;
            return this;
        }

        public Builder e(UiCallback<Boolean> uiCallback) {
            this.e = uiCallback;
            return this;
        }

        public Builder czH_(CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
            this.b = onCheckedChangeListener;
            return this;
        }

        public PurchaseSuccessDialog c() {
            return new PurchaseSuccessDialog(this.c, this);
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseDialog, android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        UiCallback<Boolean> uiCallback;
        HealthCheckBox healthCheckBox = this.b;
        if (healthCheckBox != null && (uiCallback = this.f8818a) != null) {
            uiCallback.onSuccess(Boolean.valueOf(healthCheckBox.isChecked()));
        }
        super.dismiss();
    }

    private void d(Context context, Builder builder) {
        View inflate = ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.common_ui_course_purchase_success_dialog, (ViewGroup) null);
        this.k = inflate;
        this.f = (HealthTextView) inflate.findViewById(R.id.dialog_subtitle);
        this.e = (HealthTextView) this.k.findViewById(R.id.course_title);
        this.h = (HealthCardView) this.k.findViewById(R.id.dialog_cover_img_card);
        this.g = (HealthImageView) this.k.findViewById(R.id.dialog_cover_img);
        this.i = (HealthTextView) this.k.findViewById(R.id.purchase_icon);
        e();
        this.d = (HealthTextView) this.k.findViewById(R.id.course_progress);
        this.b = (HealthCheckBox) this.k.findViewById(R.id.collect_checkbox);
        this.j = (HealthTextView) this.k.findViewById(R.id.go_check_button);
        this.c = (HealthTextView) this.k.findViewById(R.id.cancel_button);
        setContentView(this.k);
        c(builder);
    }

    private void e() {
        HealthTextView healthTextView = this.i;
        if (healthTextView == null) {
            return;
        }
        healthTextView.setTextColor(BaseApplication.getContext().getResources().getColor(R$color.purchase_icon));
        this.i.setBackground(BaseApplication.getContext().getResources().getDrawable(R$drawable.course_purchase_icon_background));
        int c = nsn.c(BaseApplication.getContext(), 4.0f);
        int c2 = nsn.c(BaseApplication.getContext(), 1.0f);
        this.i.setPadding(c, c2, c, c2);
    }

    private void c(Builder builder) {
        a(builder);
        b(builder);
        e(builder);
    }

    private void a(Builder builder) {
        nsy.cMq_(this.f, builder.f);
        nsy.cMs_(this.e, builder.f8819a, true);
        nsy.cMs_(this.d, builder.d, true);
    }

    private void b(Builder builder) {
        if (TextUtils.isEmpty(builder.i)) {
            this.g.setVisibility(8);
        } else {
            nrf.c(builder.i, this.g, (int) BaseApplication.getContext().getResources().getDimension(R.dimen._2131362361_res_0x7f0a0239));
        }
    }

    private void e(final Builder builder) {
        if (builder.h != null) {
            nsy.cMn_(this.j, new View.OnClickListener() { // from class: com.huawei.ui.commonui.dialog.PurchaseSuccessDialog.5
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    builder.h.onClick(view);
                    PurchaseSuccessDialog.this.dismiss();
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
        }
        nsy.cMn_(this.c, new View.OnClickListener() { // from class: com.huawei.ui.commonui.dialog.PurchaseSuccessDialog.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PurchaseSuccessDialog.this.dismiss();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.b.setOnCheckedChangeListener(builder.b);
        this.f8818a = builder.e;
    }
}
