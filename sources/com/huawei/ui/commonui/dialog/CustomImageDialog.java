package com.huawei.ui.commonui.dialog;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$drawable;
import com.huawei.ui.commonui.base.BaseDialog;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.dialog.CustomImageDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.nrf;
import defpackage.nsn;

/* loaded from: classes6.dex */
public class CustomImageDialog extends BaseDialog {
    private View d;

    private CustomImageDialog(Context context, int i) {
        super(context, i, 0);
    }

    @Override // com.huawei.ui.commonui.base.BaseDialog, android.app.Dialog
    public void show() {
        c();
        super.show();
    }

    @Override // com.huawei.ui.commonui.base.BaseDialog
    public void configDialog() {
        c();
    }

    private void c() {
        Window window = getWindow();
        if (window != null) {
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.height = -2;
            if (nsn.ag(getContext())) {
                window.setGravity(17);
                attributes.width = (int) getContext().getResources().getDimension(R.dimen._2131363059_res_0x7f0a04f3);
                this.d.setBackgroundResource(R$drawable.activity_dialog_bg);
            } else {
                window.setGravity(81);
                attributes.width = -1;
                this.d.setBackgroundResource(R$drawable.activity_dialog_top_radius_bg);
            }
            window.setWindowAnimations(R.style.track_dialog_anim);
        }
    }

    public static class Builder {

        /* renamed from: a, reason: collision with root package name */
        private String f8800a;
        private String b;
        private View.OnClickListener c;
        private View.OnClickListener d;
        private String e;
        private View.OnClickListener f;
        private final Context g;
        private View.OnClickListener h;
        private CustomImageDialog i;
        private String j;
        private int n;
        private String o;

        public Builder(Context context) {
            this.g = context;
        }

        public Builder d(int i) {
            this.n = i;
            return this;
        }

        public Builder d(String str) {
            this.o = str;
            return this;
        }

        public Builder a(String str) {
            this.j = str;
            return this;
        }

        public Builder b(String str) {
            this.b = str;
            return this;
        }

        public Builder e(String str) {
            this.e = str;
            return this;
        }

        public Builder c(String str) {
            this.f8800a = str;
            return this;
        }

        public Builder cyF_(View.OnClickListener onClickListener) {
            this.h = onClickListener;
            return this;
        }

        public Builder cyD_(View.OnClickListener onClickListener) {
            this.d = onClickListener;
            return this;
        }

        public Builder cyE_(View.OnClickListener onClickListener) {
            this.f = onClickListener;
            return this;
        }

        public Builder cyC_(View.OnClickListener onClickListener) {
            this.c = onClickListener;
            return this;
        }

        public CustomImageDialog d() {
            LayoutInflater layoutInflater = (LayoutInflater) this.g.getSystemService("layout_inflater");
            CustomImageDialog customImageDialog = new CustomImageDialog(this.g, R.style.CustomDialog);
            this.i = customImageDialog;
            customImageDialog.d = layoutInflater.inflate(R.layout.layout_image_three_btn_dialog, (ViewGroup) null);
            HealthTextView healthTextView = (HealthTextView) this.i.d.findViewById(R.id.custom_text_dialog_title);
            ImageView imageView = (ImageView) this.i.d.findViewById(R.id.dialog_image_content);
            HealthTextView healthTextView2 = (HealthTextView) this.i.d.findViewById(R.id.custom_text_dialog_content);
            ImageView imageView2 = (ImageView) this.i.d.findViewById(R.id.dialog_close_icon);
            HealthButton healthButton = (HealthButton) this.i.d.findViewById(R.id.dialog_one_btn);
            HealthButton healthButton2 = (HealthButton) this.i.d.findViewById(R.id.dialog_two_btn);
            HealthButton healthButton3 = (HealthButton) this.i.d.findViewById(R.id.dialog_three_btn);
            if (!TextUtils.isEmpty(this.o)) {
                healthTextView.setText(this.o);
            }
            cyz_(healthTextView2, this.j);
            cyz_(healthButton, this.b);
            cyz_(healthButton2, this.e);
            cyz_(healthButton3, this.f8800a);
            int i = this.n;
            if (i != 0) {
                nrf.cIM_(i, imageView, (int) this.g.getResources().getDimension(R.dimen._2131362601_res_0x7f0a0329));
            } else {
                LogUtil.a("CustomImageDialog", "mImage = null");
            }
            cyA_(healthButton, this.i.d, this.c);
            cyA_(healthButton2, this.i.d, this.d);
            cyA_(healthButton3, this.i.d, this.f);
            cyA_(imageView2, this.i.d, this.h);
            CustomImageDialog customImageDialog2 = this.i;
            customImageDialog2.setContentView(customImageDialog2.d);
            return this.i;
        }

        private void cyz_(TextView textView, String str) {
            if (!TextUtils.isEmpty(str)) {
                textView.setText(str);
            } else {
                textView.setVisibility(8);
            }
        }

        private void cyA_(View view, final View view2, final View.OnClickListener onClickListener) {
            view.setOnClickListener(new View.OnClickListener() { // from class: nle
                @Override // android.view.View.OnClickListener
                public final void onClick(View view3) {
                    CustomImageDialog.Builder.this.cyB_(onClickListener, view2, view3);
                }
            });
        }

        public /* synthetic */ void cyB_(View.OnClickListener onClickListener, View view, View view2) {
            CustomImageDialog customImageDialog = this.i;
            if (customImageDialog != null) {
                customImageDialog.dismiss();
            }
            if (onClickListener != null) {
                onClickListener.onClick(view);
            }
            ViewClickInstrumentation.clickOnView(view2);
        }
    }
}
