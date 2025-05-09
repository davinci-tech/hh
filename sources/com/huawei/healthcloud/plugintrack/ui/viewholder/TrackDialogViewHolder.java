package com.huawei.healthcloud.plugintrack.ui.viewholder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.huawei.health.R;
import com.huawei.healthcloud.plugintrack.callback.CommonSingleCallback;
import com.huawei.healthcloud.plugintrack.ui.viewholder.TrackDialogViewHolder;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.checkbox.HealthCheckBox;
import com.huawei.ui.commonui.dialog.CustomViewDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.radiobutton.HealthRadioButton;

/* loaded from: classes4.dex */
public final class TrackDialogViewHolder {

    /* renamed from: a, reason: collision with root package name */
    private CommonSingleCallback<Boolean> f3822a;
    private CustomViewDialog b;
    private CommonSingleCallback<Boolean> c;
    private HealthRadioButton d;
    private Context e;
    private CommonSingleCallback<Boolean> f;
    private View g;
    private HealthRadioButton i;
    private HealthCheckBox j;

    public TrackDialogViewHolder(Context context) {
        if (context == null) {
            LogUtil.h("Track_TrackDialogViewHolder", "TrackDialogViewHolder invalid params in constructor, context is null");
        } else {
            this.e = context;
            b();
        }
    }

    private void b() {
        Context context = this.e;
        if (context == null) {
            LogUtil.h("Track_TrackDialogViewHolder", "getDisplayDialogView Context is null");
            return;
        }
        View inflate = LayoutInflater.from(context).inflate(R.layout.retrack_video_setting_view, (ViewGroup) null);
        this.g = inflate;
        LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.retrack_play_video_type_layout);
        HealthTextView healthTextView = (HealthTextView) linearLayout.findViewById(R.id.one_type);
        HealthTextView healthTextView2 = (HealthTextView) linearLayout.findViewById(R.id.other_type);
        healthTextView.setText(this.e.getResources().getString(R.string._2130845844_res_0x7f022094, 720));
        healthTextView2.setText(this.e.getResources().getString(R.string._2130845845_res_0x7f022095, 1080));
        this.i = (HealthRadioButton) linearLayout.findViewById(R.id.save_to_my_route_radio_button);
        this.j = (HealthCheckBox) this.g.findViewById(R.id.no_more_next_tip);
        this.d = (HealthRadioButton) linearLayout.findViewById(R.id.rb_export_route);
        this.i.setClickable(false);
        this.d.setClickable(false);
        linearLayout.findViewById(R.id.standard_video).setOnClickListener(new View.OnClickListener() { // from class: hnn
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TrackDialogViewHolder.this.bmg_(view);
            }
        });
        linearLayout.findViewById(R.id.hd_video).setOnClickListener(new View.OnClickListener() { // from class: hno
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TrackDialogViewHolder.this.bmh_(view);
            }
        });
    }

    public /* synthetic */ void bmg_(View view) {
        if (!this.i.isChecked()) {
            this.i.setChecked(true);
            this.d.setChecked(false);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void bmh_(View view) {
        if (!this.d.isChecked()) {
            this.d.setChecked(true);
            this.i.setChecked(false);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void c() {
        if (this.g == null) {
            return;
        }
        this.b = new CustomViewDialog.Builder(this.e).czg_(this.g).czf_(this.e.getResources().getString(R.string._2130843664_res_0x7f021810), new View.OnClickListener() { // from class: hnk
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TrackDialogViewHolder.this.bme_(view);
            }
        }).czd_(this.e.getResources().getString(R.string._2130844419_res_0x7f021b03), new View.OnClickListener() { // from class: hnr
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TrackDialogViewHolder.this.bmf_(view);
            }
        }).e();
    }

    public /* synthetic */ void bme_(View view) {
        CommonSingleCallback<Boolean> commonSingleCallback;
        CommonSingleCallback<Boolean> commonSingleCallback2;
        HealthCheckBox healthCheckBox = this.j;
        if (healthCheckBox != null && (commonSingleCallback2 = this.f) != null) {
            commonSingleCallback2.callback(Boolean.valueOf(healthCheckBox.isChecked()));
        }
        HealthRadioButton healthRadioButton = this.d;
        if (healthRadioButton != null && (commonSingleCallback = this.c) != null) {
            commonSingleCallback.callback(Boolean.valueOf(healthRadioButton.isChecked()));
        }
        CommonSingleCallback<Boolean> commonSingleCallback3 = this.f3822a;
        if (commonSingleCallback3 != null) {
            commonSingleCallback3.callback(true);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void bmf_(View view) {
        CommonSingleCallback<Boolean> commonSingleCallback = this.f3822a;
        if (commonSingleCallback != null) {
            commonSingleCallback.callback(false);
        }
        this.b.dismiss();
        ViewClickInstrumentation.clickOnView(view);
    }

    public void d(boolean z) {
        if (this.b == null) {
            c();
        }
        a(z);
        CustomViewDialog customViewDialog = this.b;
        if (customViewDialog == null || customViewDialog.isShowing()) {
            return;
        }
        this.b.show();
    }

    private void a(boolean z) {
        HealthRadioButton healthRadioButton = this.d;
        if (healthRadioButton == null || this.i == null) {
            return;
        }
        if (z) {
            healthRadioButton.setChecked(true);
            this.i.setChecked(false);
        } else {
            healthRadioButton.setChecked(false);
            this.i.setChecked(true);
        }
    }

    public TrackDialogViewHolder a(CommonSingleCallback<Boolean> commonSingleCallback) {
        this.f = commonSingleCallback;
        return this;
    }

    public TrackDialogViewHolder c(CommonSingleCallback<Boolean> commonSingleCallback) {
        this.c = commonSingleCallback;
        return this;
    }

    public TrackDialogViewHolder d(CommonSingleCallback<Boolean> commonSingleCallback) {
        this.f3822a = commonSingleCallback;
        return this;
    }
}
