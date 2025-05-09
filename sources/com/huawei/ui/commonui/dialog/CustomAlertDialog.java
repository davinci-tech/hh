package com.huawei.ui.commonui.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.core.content.ContextCompat;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$drawable;
import com.huawei.ui.commonui.R$styleable;
import com.huawei.ui.commonui.base.BaseDialog;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.nrs;
import java.lang.ref.WeakReference;

/* loaded from: classes6.dex */
public class CustomAlertDialog extends BaseDialog {

    /* renamed from: a, reason: collision with root package name */
    private static Handler f8797a;
    private Context b;
    private b c;
    private boolean d;
    private View e;

    private CustomAlertDialog(Context context, int i) {
        super(context, i);
        this.b = context;
        this.c = new b(context);
    }

    @Override // android.app.Dialog
    public void setTitle(int i) {
        String str;
        try {
            str = this.b.getString(i);
        } catch (Resources.NotFoundException unused) {
            LogUtil.b("CustomAlertDialog", "Resources NotFound");
            str = "";
        }
        this.c.b(str);
    }

    public void a(String str) {
        if (str == null) {
            return;
        }
        this.c.e(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public b b() {
        return this.c;
    }

    public static class Builder {
        private boolean b;
        private Context c;
        private CustomAlertDialog d;
        private b e;

        public Builder(Context context) {
            this(context, R.style.CustomDialog);
        }

        public Builder(Context context, int i) {
            this.c = context;
            this.b = true;
            CustomAlertDialog customAlertDialog = new CustomAlertDialog(context, i);
            this.d = customAlertDialog;
            this.e = customAlertDialog.b();
            Handler unused = CustomAlertDialog.f8797a = new ButtonHandler(this.d);
        }

        public Builder a(String str) {
            if (!TextUtils.isEmpty(str)) {
                this.e.b(str);
            }
            return this;
        }

        public Builder e(int i) {
            String str;
            try {
                str = this.c.getString(i);
            } catch (Resources.NotFoundException unused) {
                LogUtil.b("CustomAlertDialog", "Resources NotFound");
                str = "";
            }
            if (!TextUtils.isEmpty(str)) {
                this.e.b(str);
            }
            return this;
        }

        public Builder c(String str) {
            if (!TextUtils.isEmpty(str)) {
                this.e.e(str);
            }
            return this;
        }

        public Builder a(int i) {
            if (i != 0) {
                this.e.c(i);
            }
            return this;
        }

        public Builder e(int i, int i2) {
            this.e.a(i, i2);
            return this;
        }

        public Builder c(int i) {
            String str;
            try {
                str = this.c.getString(i);
            } catch (Resources.NotFoundException unused) {
                LogUtil.b("CustomAlertDialog", "Resources NotFound");
                str = "";
            }
            if (!TextUtils.isEmpty(str)) {
                this.e.e(str);
            }
            return this;
        }

        public Builder cyp_(View view) {
            this.e.cyv_(view);
            return this;
        }

        public Builder cyo_(int i, DialogInterface.OnClickListener onClickListener) {
            return cyl_(i, 0, onClickListener, true);
        }

        public Builder cyn_(int i, DialogInterface.OnClickListener onClickListener) {
            return cyl_(i, 0, onClickListener, false);
        }

        public Builder cym_(int i, int i2, DialogInterface.OnClickListener onClickListener) {
            return cyl_(i, i2, onClickListener, false);
        }

        public HealthButton b() {
            return this.e.c();
        }

        public HealthButton d() {
            return this.e.d();
        }

        public CustomAlertDialog c() {
            return a();
        }

        public Builder e(boolean z) {
            this.b = z;
            return this;
        }

        private CustomAlertDialog a() {
            Drawable drawable;
            if (this.c == null) {
                return this.d;
            }
            TypedValue typedValue = new TypedValue();
            this.c.getTheme().resolveAttribute(R.attr._2131099975_res_0x7f060147, typedValue, true);
            TypedArray obtainStyledAttributes = this.c.getTheme().obtainStyledAttributes(typedValue.resourceId, R$styleable.customDialogDefinition);
            if (typedValue.resourceId != 0) {
                drawable = obtainStyledAttributes.getDrawable(R$styleable.customDialogDefinition_dialogBackground);
            } else {
                drawable = ContextCompat.getDrawable(this.c, R$drawable.activity_dialog_bg);
            }
            obtainStyledAttributes.recycle();
            this.e.cys_().setBackground(drawable);
            this.d.addContentView(this.e.cys_(), new ViewGroup.LayoutParams(-2, -2));
            this.d.setContentView(this.e.cys_());
            this.d.setCancelable(this.b);
            return this.d;
        }

        public Builder cyl_(int i, int i2, DialogInterface.OnClickListener onClickListener, boolean z) {
            String str;
            if (onClickListener == null) {
                return this;
            }
            try {
                str = (String) this.c.getText(i);
            } catch (Resources.NotFoundException unused) {
                LogUtil.b("CustomAlertDialog", "Resources NotFound");
                str = "";
            }
            if (!TextUtils.isEmpty(str)) {
                if (z) {
                    this.e.cyu_(str, i2, onClickListener);
                } else {
                    this.e.cyt_(str, i2, onClickListener);
                }
            }
            return this;
        }
    }

    static final class ButtonHandler extends Handler {
        private WeakReference<DialogInterface> d;

        ButtonHandler(DialogInterface dialogInterface) {
            this.d = new WeakReference<>(dialogInterface);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            int i = message.what;
            if ((i == -3 || i == -2 || i == -1) && (message.obj instanceof DialogInterface.OnClickListener)) {
                ((DialogInterface.OnClickListener) message.obj).onClick(this.d.get(), message.what);
            }
        }
    }

    class b {

        /* renamed from: a, reason: collision with root package name */
        private HealthButton f8798a;
        View.OnClickListener b = new View.OnClickListener() { // from class: com.huawei.ui.commonui.dialog.CustomAlertDialog.b.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                int id = view.getId();
                Message obtain = id == R.id.dialog_btn_positive ? Message.obtain(b.this.i) : null;
                if (id == R.id.dialog_btn_negative) {
                    obtain = Message.obtain(b.this.e);
                }
                if (obtain != null) {
                    obtain.sendToTarget();
                }
                CustomAlertDialog.this.dismiss();
                ViewClickInstrumentation.clickOnView(view);
            }
        };
        private HealthButton d;
        private Message e;
        private LinearLayout f;
        private HealthTextView g;
        private LinearLayout h;
        private Message i;
        private RelativeLayout j;
        private HealthTextView k;
        private View l;
        private RelativeLayout o;

        b(Context context) {
            e(context);
        }

        private void e(Context context) {
            View inflate = ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.commonui_custom_dialog, (ViewGroup) null);
            this.l = inflate;
            this.f = (LinearLayout) inflate.findViewById(R.id.dialog_rlyt_title);
            this.j = (RelativeLayout) this.l.findViewById(R.id.dialog_llyt_btn_panel);
            this.o = (RelativeLayout) this.l.findViewById(R.id.dialog_rlyt_content);
            this.h = (LinearLayout) this.l.findViewById(R.id.dialog_llyt_message);
            this.k = (HealthTextView) this.l.findViewById(R.id.dialog_tv_title);
            this.g = (HealthTextView) this.l.findViewById(R.id.dialog_tv_message);
            this.f8798a = (HealthButton) this.l.findViewById(R.id.dialog_btn_positive);
            this.d = (HealthButton) this.l.findViewById(R.id.dialog_btn_negative);
        }

        public void b(String str) {
            if (str == null) {
                return;
            }
            this.f.setVisibility(0);
            this.k.setVisibility(0);
            this.k.setText(str);
        }

        public void e(String str) {
            if (str == null) {
                return;
            }
            this.o.setVisibility(0);
            this.h.setVisibility(0);
            this.g.setVisibility(0);
            this.g.setText(str);
            if (b()) {
                this.g.setGravity(8388627);
            } else {
                this.g.setGravity(17);
            }
        }

        public void c(int i) {
            RelativeLayout relativeLayout = this.o;
            if (relativeLayout != null) {
                relativeLayout.getLayoutParams().height = i;
            }
        }

        public void a(int i, int i2) {
            RelativeLayout relativeLayout = this.o;
            if (relativeLayout != null) {
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) relativeLayout.getLayoutParams();
                layoutParams.setMarginStart(i);
                layoutParams.setMarginEnd(i2);
            }
        }

        private boolean b() {
            HealthTextView healthTextView = this.k;
            return healthTextView != null && healthTextView.getVisibility() == 0;
        }

        public void cyv_(View view) {
            if (view == null) {
                return;
            }
            CustomAlertDialog.this.e = view;
            this.o.setVisibility(0);
            this.o.removeAllViews();
            this.o.addView(view, new ViewGroup.LayoutParams(-1, -2));
        }

        public View cys_() {
            return this.l;
        }

        public void cyu_(String str, int i, DialogInterface.OnClickListener onClickListener) {
            this.j.setVisibility(0);
            this.f8798a.setVisibility(0);
            if (!TextUtils.isEmpty(str)) {
                this.f8798a.setText(str);
            }
            if (i != 0) {
                this.f8798a.setTextColor(CustomAlertDialog.this.b.getColor(i));
            }
            if (onClickListener != null) {
                this.i = CustomAlertDialog.f8797a.obtainMessage(-1, onClickListener);
                this.f8798a.setOnClickListener(this.b);
            }
        }

        public void cyt_(String str, int i, DialogInterface.OnClickListener onClickListener) {
            this.j.setVisibility(0);
            this.d.setVisibility(0);
            if (!TextUtils.isEmpty(str)) {
                this.d.setText(str);
            }
            if (i != 0) {
                this.d.setTextColor(CustomAlertDialog.this.b.getColor(i));
            }
            if (onClickListener != null) {
                this.e = CustomAlertDialog.f8797a.obtainMessage(-2, onClickListener);
                this.d.setOnClickListener(this.b);
            }
        }

        public HealthButton c() {
            return this.f8798a;
        }

        public HealthButton d() {
            return this.d;
        }
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (this.d) {
            cyk_(this.e);
        }
    }

    private int c() {
        return (int) (nrs.d(this.b) * 0.8d);
    }

    private void cyk_(View view) {
        if (view == null) {
            LogUtil.h("CustomAlertDialog", "setDialogViewHeight, view is null");
            return;
        }
        int c = c();
        LogUtil.a("CustomAlertDialog", "setDialogViewHeight, dialogMaxHeight:", Integer.valueOf(c));
        int measuredHeight = view.getMeasuredHeight();
        int height = (c - view.getRootView().findViewById(R.id.dialog_rlyt_title).getHeight()) - view.getRootView().findViewById(R.id.dialog_btn_negative).getHeight();
        LogUtil.a("CustomAlertDialog", "setDialogViewHeight, contentHeight:", Integer.valueOf(measuredHeight), ", usableHeight:", Integer.valueOf(height));
        if (height < measuredHeight) {
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            layoutParams.height = height;
            view.setLayoutParams(layoutParams);
            view.requestLayout();
        }
    }
}
