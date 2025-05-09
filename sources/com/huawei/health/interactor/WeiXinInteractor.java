package com.huawei.health.interactor;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import com.huawei.health.R;
import com.huawei.health.interactor.WeiXinInteractor;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.checkbox.HealthCheckBox;
import com.huawei.ui.commonui.dialog.CustomViewDialog;
import defpackage.gnm;
import defpackage.nsn;
import defpackage.sbk;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;

/* loaded from: classes3.dex */
public class WeiXinInteractor {

    /* renamed from: a, reason: collision with root package name */
    private CustomViewDialog f2519a = null;
    private final sbk c;
    private final Context d;

    public boolean c() {
        return false;
    }

    public WeiXinInteractor(Context context) {
        this.d = context;
        this.c = sbk.a(context);
    }

    public void d() {
        f();
        LogUtil.a("Login_WeiXinInteractor", "showWechatDialog() enter");
        if (this.d instanceof Activity) {
            CustomViewDialog.Builder builder = new CustomViewDialog.Builder(this.d);
            View inflate = LayoutInflater.from(this.d).inflate(R.layout.hw_health_wechat_tips_view, (ViewGroup) null);
            HealthCheckBox healthCheckBox = (HealthCheckBox) inflate.findViewById(R.id.cb_wechat_tips_not_remind);
            LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.cb_wechat_not_remind_layout);
            healthCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.huawei.health.interactor.WeiXinInteractor$$ExternalSyntheticLambda0
                @Override // android.widget.CompoundButton.OnCheckedChangeListener
                public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                    WeiXinInteractor.abP_(compoundButton, z);
                }
            });
            builder.czg_(inflate);
            builder.czf_(this.d.getString(R.string._2130841449_res_0x7f020f69), new View.OnClickListener() { // from class: dzy
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    WeiXinInteractor.this.abQ_(view);
                }
            });
            if (e()) {
                linearLayout.setVisibility(0);
                builder.czd_(this.d.getString(R.string._2130841130_res_0x7f020e2a), new View.OnClickListener() { // from class: dzx
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        WeiXinInteractor.this.abR_(view);
                    }
                });
            } else {
                linearLayout.setVisibility(8);
            }
            this.f2519a = builder.e();
            if (gnm.aPA_((Activity) this.d)) {
                this.f2519a.show();
            }
        }
    }

    static /* synthetic */ void abP_(CompoundButton compoundButton, boolean z) {
        StorageParams storageParams = new StorageParams();
        if (z) {
            SharedPreferenceManager.e(BaseApplication.getContext(), Integer.toString(10018), "health_is_allowed_wechat_tips", "false", storageParams);
        } else {
            SharedPreferenceManager.e(BaseApplication.getContext(), Integer.toString(10018), "health_is_allowed_wechat_tips", "true", storageParams);
        }
        ViewClickInstrumentation.clickOnView(compoundButton);
    }

    public /* synthetic */ void abQ_(View view) {
        this.c.e(this.d);
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void abR_(View view) {
        CustomViewDialog customViewDialog = this.f2519a;
        if (customViewDialog != null && customViewDialog.isShowing()) {
            a();
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void a() {
        if (((Activity) this.d).isDestroyed() || ((Activity) this.d).isFinishing()) {
            return;
        }
        this.f2519a.dismiss();
        this.f2519a = null;
    }

    public void b() {
        CustomViewDialog customViewDialog = this.f2519a;
        if (customViewDialog != null) {
            customViewDialog.dismiss();
        }
        this.f2519a = null;
    }

    private boolean e() {
        String b = SharedPreferenceManager.b(this.d, Integer.toString(10018), "health_binded_times");
        LogUtil.a("Login_WeiXinInteractor", "isShowNotRemindBox bindTimes=", b);
        boolean z = nsn.e(b) >= 4;
        LogUtil.a("Login_WeiXinInteractor", "isShowNotRemindBox isShow=", Boolean.valueOf(z));
        return z;
    }

    private void f() {
        StorageParams storageParams = new StorageParams();
        String b = SharedPreferenceManager.b(this.d, Integer.toString(10018), "health_binded_times");
        LogUtil.a("Login_WeiXinInteractor", "bindTimes =", b);
        if ("".equals(b)) {
            SharedPreferenceManager.e(BaseApplication.getContext(), Integer.toString(10018), "health_binded_times", "1", storageParams);
        } else if ("1".equals(b)) {
            SharedPreferenceManager.e(BaseApplication.getContext(), Integer.toString(10018), "health_binded_times", "2", storageParams);
        } else if ("2".equals(b)) {
            SharedPreferenceManager.e(BaseApplication.getContext(), Integer.toString(10018), "health_binded_times", "3", storageParams);
        } else if ("3".equals(b)) {
            SharedPreferenceManager.e(BaseApplication.getContext(), Integer.toString(10018), "health_binded_times", "4", storageParams);
        } else {
            LogUtil.h("Login_WeiXinInteractor", "saveWechatSp is other condition");
        }
        SharedPreferenceManager.e(BaseApplication.getContext(), Integer.toString(10019), "health_last_binded_time", String.valueOf(System.currentTimeMillis()), storageParams);
    }
}
