package com.huawei.ui.homehealth.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseDialog;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.radiobutton.HealthRadioButton;
import com.huawei.ui.homehealth.device.callback.ReconnectCallback;

/* loaded from: classes6.dex */
public class CloudReconnectFailDialog extends BaseDialog {
    public CloudReconnectFailDialog(Context context, int i) {
        super(context, i);
    }

    public static class Builder {

        /* renamed from: a, reason: collision with root package name */
        private RelativeLayout f9632a;
        private HealthRadioButton c;
        private Context d;
        private HealthTextView f;
        private HealthTextView g;
        private HealthTextView h;
        private HealthTextView i;
        private CloudReconnectFailDialog j;
        private HealthButton k;
        private RelativeLayout m;
        private HealthRadioButton o;
        private ReconnectCallback p;
        private View q;
        private View s;
        private HealthButton t;
        private boolean l = true;
        private int n = 1;
        private int b = -1;
        private int e = -1;

        public Builder(Context context) {
            this.d = context;
        }

        public Builder c(int i, int i2) {
            this.e = i;
            this.b = i2;
            return this;
        }

        public Builder e(ReconnectCallback reconnectCallback) {
            this.p = reconnectCallback;
            return this;
        }

        public CloudReconnectFailDialog b() {
            LogUtil.a("CloudReconnectFailDialog", "enter create dialog");
            this.j = new CloudReconnectFailDialog(this.d, R.style.CustomDialog);
            Context context = this.d;
            if (context == null) {
                LogUtil.h("CloudReconnectFailDialog", "enter create mContext == null");
                return this.j;
            }
            View inflate = LayoutInflater.from(context).inflate(R.layout.cloud_reconnect_dialog_layout, (ViewGroup) null);
            TypedValue typedValue = new TypedValue();
            this.d.getTheme().resolveAttribute(R.attr._2131099975_res_0x7f060147, typedValue, true);
            TypedArray obtainStyledAttributes = this.d.getTheme().obtainStyledAttributes(typedValue.resourceId, new int[]{R.attr._2131099820_res_0x7f0600ac, R.attr._2131099951_res_0x7f06012f, R.attr._2131100000_res_0x7f060160, R.attr._2131101300_res_0x7f060674});
            Drawable drawable = obtainStyledAttributes.getDrawable(2);
            obtainStyledAttributes.recycle();
            inflate.setBackground(drawable);
            this.j.addContentView(inflate, new ViewGroup.LayoutParams(-2, -2));
            djd_(inflate);
            f();
            g();
            this.j.setContentView(inflate);
            return this.j;
        }

        private void djd_(View view) {
            this.f = (HealthTextView) view.findViewById(R.id.fail_dialog_tv_title);
            this.q = view.findViewById(R.id.tip_layout);
            this.s = view.findViewById(R.id.phone_layout);
            this.f9632a = (RelativeLayout) view.findViewById(R.id.connected_rl);
            this.m = (RelativeLayout) view.findViewById(R.id.no_connected_rl);
            this.c = (HealthRadioButton) view.findViewById(R.id.connected_rb);
            this.o = (HealthRadioButton) view.findViewById(R.id.no_connected_rb);
            this.g = (HealthTextView) view.findViewById(R.id.dialog_content_one);
            this.h = (HealthTextView) view.findViewById(R.id.dialog_content_two);
            this.i = (HealthTextView) view.findViewById(R.id.dialog_content_three);
            this.k = (HealthButton) view.findViewById(R.id.fail_dialog_btn_negative);
            this.t = (HealthButton) view.findViewById(R.id.fail_dialog_btn_positive);
        }

        private void f() {
            this.m.setOnClickListener(new b(false));
            this.f9632a.setOnClickListener(new b(true));
            h();
        }

        private void g() {
            this.k.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.homehealth.view.CloudReconnectFailDialog.Builder.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    Builder.this.a();
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
            this.t.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.homehealth.view.CloudReconnectFailDialog.Builder.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    Builder.this.i();
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a() {
            if (this.j == null) {
                LogUtil.h("CloudReconnectFailDialog", "onNegativeButtonClick mDialog == null");
                return;
            }
            int i = this.n;
            if (i == 1) {
                LogUtil.a("CloudReconnectFailDialog", "onNegativeButtonClick mDialog.dismiss()");
                this.j.dismiss();
                return;
            }
            if (i == 2) {
                LogUtil.a("CloudReconnectFailDialog", "onNegativeButtonClick showDialogOne()");
                j();
            } else if (i == 3) {
                if (this.l) {
                    this.h.setVisibility(0);
                    LogUtil.a("CloudReconnectFailDialog", "onNegativeButtonClick showDialogTwo()");
                    m();
                } else {
                    LogUtil.a("CloudReconnectFailDialog", "onNegativeButtonClick showDialogOne()");
                    j();
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void i() {
            if (this.j == null) {
                LogUtil.h("CloudReconnectFailDialog", "onPositiveButtonClick mDialog == null");
                return;
            }
            int i = this.n;
            if (i == 1) {
                if (this.l) {
                    this.s.setVisibility(8);
                    this.q.setVisibility(0);
                    LogUtil.a("CloudReconnectFailDialog", "onPositiveButtonClick showDialogTwo()");
                    m();
                    return;
                }
                this.s.setVisibility(8);
                this.q.setVisibility(0);
                LogUtil.a("CloudReconnectFailDialog", "onPositiveButtonClick mPage == 1 showDialogThree()");
                o();
                return;
            }
            if (i == 2) {
                LogUtil.a("CloudReconnectFailDialog", "onPositiveButtonClick mPage == 2 showDialogThree()");
                o();
            } else if (i == 3) {
                this.n = 1;
                ReconnectCallback reconnectCallback = this.p;
                if (reconnectCallback != null) {
                    reconnectCallback.reconnect();
                }
                LogUtil.a("CloudReconnectFailDialog", "onPositiveButtonClick mDialog.dismiss()");
                this.j.dismiss();
            }
        }

        private void j() {
            this.n = 1;
            this.f.setText(this.d.getResources().getString(R.string.IDS_failed_connect_device));
            this.s.setVisibility(0);
            this.q.setVisibility(8);
            this.k.setText(this.d.getResources().getString(R.string.IDS_device_user_guide_cancel_btn));
            this.t.setText(this.d.getResources().getString(R.string.IDS_device_user_guide_next_btn));
        }

        private void m() {
            this.n = 2;
            this.i.setVisibility(0);
            this.f.setText(this.d.getResources().getString(R.string._2130846457_res_0x7f0222f9));
            this.g.setText(this.d.getResources().getString(R.string._2130846490_res_0x7f02231a, 1));
            this.h.setText(this.d.getResources().getString(R.string._2130846491_res_0x7f02231b, 2));
            this.i.setText(this.d.getResources().getString(R.string._2130846492_res_0x7f02231c));
            this.k.setText(this.d.getResources().getString(R.string.IDS_device_user_guide_previous_btn));
            this.t.setText(this.d.getResources().getString(R.string.IDS_device_user_guide_next_btn));
        }

        private void o() {
            LogUtil.a("CloudReconnectFailDialog", "mDeviceType = ", Integer.valueOf(this.e));
            this.n = 3;
            this.f.setText(this.d.getResources().getString(R.string.IDS_connect_device_tip_title));
            if (this.b == 2) {
                d();
            } else {
                e();
            }
            this.k.setText(this.d.getResources().getString(R.string.IDS_device_user_guide_previous_btn));
            this.t.setText(this.d.getResources().getString(R.string.IDS_hw_health_wear_connect_device_connect_button));
        }

        private void d() {
            if (this.e == 75) {
                LogUtil.a("CloudReconnectFailDialog", "dialog bolt");
                this.h.setVisibility(8);
                this.i.setVisibility(8);
                this.g.setText(this.d.getResources().getString(R.string.IDS_bolt_awaken_device_second, 3));
                return;
            }
            LogUtil.a("CloudReconnectFailDialog", "dialog other ble");
            this.i.setVisibility(0);
            this.g.setText(this.d.getResources().getString(R.string._2130846485_res_0x7f022315, 1, this.d.getResources().getString(R.string._2130846494_res_0x7f02231e)));
            this.h.setText(this.d.getResources().getString(R.string._2130846485_res_0x7f022315, 2, this.d.getResources().getString(R.string._2130845964_res_0x7f02210c)));
            this.i.setText(this.d.getResources().getString(R.string._2130846485_res_0x7f022315, 3, this.d.getResources().getString(R.string._2130846458_res_0x7f0222fa)));
        }

        private void e() {
            int i = this.e;
            if (i == 58) {
                LogUtil.a("CloudReconnectFailDialog", "dialog b6");
                this.h.setVisibility(0);
                this.i.setVisibility(8);
                this.g.setText(this.d.getResources().getString(R.string.IDS_connect_new_device_b6, 1));
                this.h.setText(this.d.getResources().getString(R.string._2130846485_res_0x7f022315, 2, this.d.getResources().getString(R.string._2130846458_res_0x7f0222fa)));
                return;
            }
            if (i == 16) {
                LogUtil.a("CloudReconnectFailDialog", "dialog b5");
                this.h.setVisibility(0);
                this.i.setVisibility(8);
                this.g.setText(this.d.getResources().getString(R.string._2130846485_res_0x7f022315, 1, this.d.getResources().getString(R.string.IDS_connect_new_device_b5)));
                this.h.setText(this.d.getResources().getString(R.string._2130846485_res_0x7f022315, 2, this.d.getResources().getString(R.string._2130846458_res_0x7f0222fa)));
                return;
            }
            if (i == 7) {
                LogUtil.a("CloudReconnectFailDialog", "dialog b3");
                c();
                return;
            }
            if (i == 14) {
                LogUtil.a("CloudReconnectFailDialog", "dialog b3 youth");
                this.h.setVisibility(0);
                this.i.setVisibility(8);
                this.g.setText(this.d.getResources().getString(R.string._2130846485_res_0x7f022315, 1, this.d.getResources().getString(R.string.IDS_connect_new_device_b3_young)));
                this.h.setText(this.d.getResources().getString(R.string._2130846485_res_0x7f022315, 2, this.d.getResources().getString(R.string._2130846458_res_0x7f0222fa)));
                return;
            }
            LogUtil.a("CloudReconnectFailDialog", "dialog other");
            this.h.setVisibility(0);
            this.i.setVisibility(0);
            this.g.setText(this.d.getResources().getString(R.string.IDS_disconnect_or_restore_device, 1));
            this.h.setText(this.d.getResources().getString(R.string._2130846498_res_0x7f022322, 2));
            this.i.setText(this.d.getResources().getString(R.string._2130846485_res_0x7f022315, 3, this.d.getResources().getString(R.string._2130846458_res_0x7f0222fa)));
        }

        private void c() {
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
            spannableStringBuilder.append((CharSequence) this.d.getResources().getString(R.string._2130846485_res_0x7f022315, 1, this.d.getResources().getString(R.string.IDS_connect_new_device_b3)));
            String spannableStringBuilder2 = spannableStringBuilder.toString();
            int indexOf = spannableStringBuilder2.indexOf("%1$s");
            if (indexOf > 0) {
                Drawable drawable = this.d.getResources().getDrawable(R.drawable._2131430679_res_0x7f0b0d17);
                drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
                spannableStringBuilder.setSpan(new ImageSpan(drawable, 1), indexOf, indexOf + 4, 33);
            }
            int indexOf2 = spannableStringBuilder2.indexOf("%2$s");
            if (indexOf2 > 0) {
                Drawable drawable2 = this.d.getResources().getDrawable(R.drawable._2131430669_res_0x7f0b0d0d);
                drawable2.setBounds(0, 0, drawable2.getIntrinsicWidth(), drawable2.getIntrinsicHeight());
                spannableStringBuilder.setSpan(new ImageSpan(drawable2, 1), indexOf2, indexOf2 + 4, 33);
            }
            this.h.setVisibility(0);
            this.i.setVisibility(8);
            this.g.setText(spannableStringBuilder);
            this.h.setText(this.d.getResources().getString(R.string._2130846485_res_0x7f022315, 2, this.d.getResources().getString(R.string._2130846458_res_0x7f0222fa)));
        }

        class b implements View.OnClickListener {
            private boolean b;

            b(boolean z) {
                this.b = z;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("CloudReconnectFailDialog", "MySelectionOptionOnClickListener onClick(), isSelect:", Boolean.valueOf(this.b));
                Builder.this.l = this.b;
                Builder.this.h();
                ViewClickInstrumentation.clickOnView(view);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void h() {
            if (this.l) {
                this.c.setChecked(true);
                this.o.setChecked(false);
            } else {
                this.c.setChecked(false);
                this.o.setChecked(true);
            }
        }
    }
}
