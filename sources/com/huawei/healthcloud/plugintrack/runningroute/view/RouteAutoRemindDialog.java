package com.huawei.healthcloud.plugintrack.runningroute.view;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.CompoundButton;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.ui.commonui.base.BaseDialog;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.checkbox.HealthCheckBox;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.imageview.HealthImageView;
import defpackage.gzl;
import defpackage.nrf;
import health.compact.a.ReleaseLogUtil;

/* loaded from: classes4.dex */
public class RouteAutoRemindDialog extends BaseDialog {

    /* renamed from: a, reason: collision with root package name */
    private HealthImageView f3575a;
    private HealthTextView b;
    private boolean c;
    private final Context d;
    private HealthButton e;
    private HealthTextView f;
    private HealthTextView h;

    public RouteAutoRemindDialog(Context context) {
        super(context, R.style.CustomDialog);
        this.c = true;
        this.d = context;
        e();
    }

    private void e() {
        View inflate = View.inflate(this.d, R.layout.dialog_route_auto_remind, null);
        this.b = (HealthTextView) inflate.findViewById(R.id.route_dialog_content);
        this.f = (HealthTextView) inflate.findViewById(R.id.route_dialog_route_name);
        this.f3575a = (HealthImageView) inflate.findViewById(R.id.route_dialog_image);
        this.h = (HealthTextView) inflate.findViewById(R.id.route_dialog_route_times);
        ((HealthCheckBox) inflate.findViewById(R.id.collect_checkbox)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.huawei.healthcloud.plugintrack.runningroute.view.RouteAutoRemindDialog$$ExternalSyntheticLambda0
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                RouteAutoRemindDialog.this.aXq_(compoundButton, z);
            }
        });
        this.e = (HealthButton) inflate.findViewById(R.id.ok_button);
        setContentView(inflate);
    }

    /* synthetic */ void aXq_(CompoundButton compoundButton, boolean z) {
        this.c = z;
        ViewClickInstrumentation.clickOnView(compoundButton);
    }

    public void c(String str) {
        this.b.setText(this.d.getString(R.string._2130847820_res_0x7f02284c, str));
        this.f.setText(str);
    }

    public void c(long j) {
        this.h.setText(this.d.getResources().getQuantityString(R.plurals._2130903137_res_0x7f030061, (int) j, Long.valueOf(j)));
    }

    public void a(String str) {
        nrf.c(this.f3575a, str, nrf.e, 0, R.drawable._2131427562_res_0x7f0b00ea);
    }

    public void aXs_(View.OnClickListener onClickListener) {
        this.f3575a.setOnClickListener(onClickListener);
    }

    public void aXr_(View.OnClickListener onClickListener) {
        this.e.setOnClickListener(onClickListener);
    }

    public boolean c() {
        return this.c;
    }

    @Override // com.huawei.ui.commonui.base.BaseDialog, android.app.Dialog
    public void show() {
        if (!(this.d instanceof Activity) || BaseApplication.wa_() == null) {
            ReleaseLogUtil.c("Track_RouteAutoRemindDialog", "context not activity or top activity is null");
            return;
        }
        if (((Activity) this.d).getClass().getSimpleName().equals(BaseApplication.wa_().getClass().getSimpleName())) {
            super.show();
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseDialog, android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        super.dismiss();
        gzl.c(this.c);
    }
}
