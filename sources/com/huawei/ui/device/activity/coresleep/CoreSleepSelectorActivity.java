package com.huawei.ui.device.activity.coresleep;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.share.HiHealthError;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.dialog.CustomAlertDialog;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.switchbutton.HealthSwitchButton;
import com.huawei.ui.device.activity.coresleep.CoreSleepSelectorActivity;
import defpackage.cwi;
import defpackage.ixx;
import defpackage.jlj;
import defpackage.jpt;
import defpackage.jqi;
import defpackage.jqp;
import defpackage.nlg;
import defpackage.nrf;
import defpackage.nrh;
import defpackage.nsn;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.HashMap;

/* loaded from: classes.dex */
public class CoreSleepSelectorActivity extends BaseActivity {

    /* renamed from: a, reason: collision with root package name */
    private boolean f9075a;
    private Context b;
    private boolean d = false;
    private HealthSwitchButton e;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_core_sleep_selector);
        DeviceInfo d = jpt.d("CORESLEEPMISSON_CoreSleepSelectorActivity");
        final boolean c = cwi.c(d, HiHealthError.ERR_WRONG_DEVICE);
        final boolean e = jlj.a().e(d);
        a(c);
        ReleaseLogUtil.e("BTSYNC_CoreSleep_CORESLEEPMISSON_CoreSleepSelectorActivity", "isSupportNormalSleepSunset ", Boolean.valueOf(c), ", isSupportSleepBreathe ", Boolean.valueOf(e));
        this.e.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.huawei.ui.device.activity.coresleep.CoreSleepSelectorActivity.5
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                ReleaseLogUtil.e("BTSYNC_CoreSleep_CORESLEEPMISSON_CoreSleepSelectorActivity", "onCheckedChanged, isChecked is", Boolean.valueOf(z));
                if (compoundButton.isPressed()) {
                    LogUtil.a("CORESLEEPMISSON_CoreSleepSelectorActivity", "user change switch.");
                    CoreSleepSelectorActivity.this.d = true;
                }
                if (!z && compoundButton.isPressed() && c && !e) {
                    CoreSleepSelectorActivity.this.c(false);
                } else if (z || !compoundButton.isPressed() || !e) {
                    CoreSleepSelectorActivity.this.b(z);
                } else {
                    CoreSleepSelectorActivity.this.e();
                }
                ViewClickInstrumentation.clickOnView(compoundButton);
            }
        });
        Intent intent = getIntent();
        if (intent != null) {
            boolean booleanExtra = intent.getBooleanExtra("core_sleep_active_open_control_btn", false);
            this.f9075a = booleanExtra;
            LogUtil.a("CORESLEEPMISSON_CoreSleepSelectorActivity", "mIsFromRemindToast：", Boolean.valueOf(booleanExtra));
        } else {
            LogUtil.h("CORESLEEPMISSON_CoreSleepSelectorActivity", "Intent is null.");
        }
        b();
    }

    private void cPx_(ImageView imageView) {
        int min = (int) (Math.min(nsn.j() / 2, nsn.n()) * 0.8d);
        imageView.getLayoutParams().height = min;
        imageView.getLayoutParams().width = min;
        imageView.setImageBitmap(nrf.cJK_(nrf.cHN_(R.mipmap._2131820854_res_0x7f110136, BaseApplication.getContext()), imageView));
    }

    private void a() {
        View inflate = View.inflate(this, R.layout.dialog_close_core_sleep_layout, null);
        CustomAlertDialog.Builder builder = new CustomAlertDialog.Builder(this);
        builder.cyp_(inflate).cyo_(R.string._2130846394_res_0x7f0222ba, new DialogInterface.OnClickListener() { // from class: com.huawei.ui.device.activity.coresleep.CoreSleepSelectorActivity.4
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                CoreSleepSelectorActivity.this.e.setChecked(true);
                LogUtil.a("CORESLEEPMISSON_CoreSleepSelectorActivity", "showNewAlertDialog no");
                ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
            }
        }).cyn_(R.string._2130846393_res_0x7f0222b9, new DialogInterface.OnClickListener() { // from class: com.huawei.ui.device.activity.coresleep.CoreSleepSelectorActivity.2
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                CoreSleepSelectorActivity.this.b(false);
                CoreSleepSelectorActivity.this.d();
                LogUtil.a("CORESLEEPMISSON_CoreSleepSelectorActivity", "showNewAlertDialog yes");
                ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
            }
        });
        CustomAlertDialog c = builder.c();
        c.setCancelable(false);
        c.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(final boolean z) {
        NoTitleCustomAlertDialog e = new NoTitleCustomAlertDialog.Builder(this).e(getResources().getString(R.string._2130846392_res_0x7f0222b8)).czC_(R.string._2130846394_res_0x7f0222ba, new View.OnClickListener() { // from class: com.huawei.ui.device.activity.coresleep.CoreSleepSelectorActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                CoreSleepSelectorActivity.this.e.setChecked(true);
                LogUtil.a("CORESLEEPMISSON_CoreSleepSelectorActivity", "showAlertDialog no");
                ViewClickInstrumentation.clickOnView(view);
            }
        }).czz_(R.string._2130846393_res_0x7f0222b9, new View.OnClickListener() { // from class: nut
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CoreSleepSelectorActivity.this.cPy_(z, view);
            }
        }).e();
        e.setCancelable(false);
        e.show();
    }

    public /* synthetic */ void cPy_(boolean z, View view) {
        b(false);
        if (z) {
            d();
            nrh.d(getApplicationContext(), getString(R.string._2130846640_res_0x7f0223b0));
        }
        LogUtil.a("CORESLEEPMISSON_CoreSleepSelectorActivity", "showAlertDialog yes");
        ViewClickInstrumentation.clickOnView(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(boolean z) {
        if (!z) {
            nlg.c(this.b, "core_sleep_btn_tips_do_not_remind_again", "core_sleep_btn_tips_cnt");
        }
        e(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        jqi.a().setSwitchSetting("sleep_breathe_key", "false", new IBaseResponseCallback() { // from class: nus
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                CoreSleepSelectorActivity.a(i, obj);
            }
        });
    }

    public static /* synthetic */ void a(int i, Object obj) {
        LogUtil.a("CORESLEEPMISSON_CoreSleepSelectorActivity", "setSleepBreatheSwitch errorCode: ", Integer.valueOf(i));
        if (i == 0) {
            jlj.a().a("false");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        jqi.a().getSwitchSetting("sleep_breathe_key", new IBaseResponseCallback() { // from class: nur
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                CoreSleepSelectorActivity.this.b(i, obj);
            }
        });
    }

    public /* synthetic */ void b(int i, Object obj) {
        LogUtil.a("CORESLEEPMISSON_CoreSleepSelectorActivity", "changeSleepBreatheSwitch errorCode: ", Integer.valueOf(i));
        if (i == 0 && ("false".equals(obj) || "0".equals(obj))) {
            runOnUiThread(new Runnable() { // from class: nuu
                @Override // java.lang.Runnable
                public final void run() {
                    CoreSleepSelectorActivity.this.c();
                }
            });
        } else {
            final boolean d = jlj.a().d();
            runOnUiThread(new Runnable() { // from class: nuq
                @Override // java.lang.Runnable
                public final void run() {
                    CoreSleepSelectorActivity.this.d(d);
                }
            });
        }
    }

    public /* synthetic */ void c() {
        c(false);
    }

    public /* synthetic */ void d(boolean z) {
        if (z) {
            a();
        } else {
            c(true);
        }
    }

    private void a(boolean z) {
        this.b = this;
        this.e = (HealthSwitchButton) findViewById(R.id.event_core_sleep_switch_btn);
        ((HealthTextView) findViewById(R.id.content_2)).setText(getResources().getString(R.string._2130842958_res_0x7f02154e, 6));
        ((HealthTextView) findViewById(R.id.content_3)).setText(getResources().getString(R.string._2130842959_res_0x7f02154f, 200));
        cPx_((ImageView) findViewById(R.id.core_sleep_image));
        HealthTextView healthTextView = (HealthTextView) findViewById(R.id.core_switch_detail);
        if (z) {
            return;
        }
        healthTextView.setVisibility(8);
    }

    private void b() {
        LogUtil.a("CORESLEEPMISSON_CoreSleepSelectorActivity", "initData()");
        this.d = false;
        final String a2 = jqp.a();
        this.e.setChecked("1".equals(a2));
        jqi.a().getSwitchSetting("core_sleep_button", new IBaseResponseCallback() { // from class: nuk
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                CoreSleepSelectorActivity.this.b(a2, i, obj);
            }
        });
    }

    public /* synthetic */ void b(String str, int i, Object obj) {
        final boolean z;
        LogUtil.a("CORESLEEPMISSON_CoreSleepSelectorActivity", "CORE_SLEEP_BUTTON errorCode = ", Integer.valueOf(i));
        if (i == 0 && (obj instanceof String)) {
            String str2 = (String) obj;
            z = "1".equals(str2);
            if (!str.equals(str2)) {
                jqp.a(str2);
            }
        } else {
            z = false;
        }
        LogUtil.a("CORESLEEPMISSON_CoreSleepSelectorActivity", "isEnable: ", Boolean.valueOf(z));
        runOnUiThread(new Runnable() { // from class: com.huawei.ui.device.activity.coresleep.CoreSleepSelectorActivity.1
            @Override // java.lang.Runnable
            public void run() {
                LogUtil.a("CORESLEEPMISSON_CoreSleepSelectorActivity", "runOnUiThread");
                if (!CoreSleepSelectorActivity.this.d) {
                    if (CoreSleepSelectorActivity.this.f9075a) {
                        boolean isChecked = CoreSleepSelectorActivity.this.e.isChecked();
                        LogUtil.a("CORESLEEPMISSON_CoreSleepSelectorActivity", "switchButtonIsOpen", Boolean.valueOf(isChecked));
                        if (isChecked) {
                            CoreSleepSelectorActivity.this.e(true);
                        } else {
                            CoreSleepSelectorActivity.this.e.setChecked(true);
                        }
                        CoreSleepSelectorActivity.this.f9075a = false;
                        return;
                    }
                    LogUtil.a("CORESLEEPMISSON_CoreSleepSelectorActivity", "default : ", Boolean.valueOf(z));
                    CoreSleepSelectorActivity.this.e.setChecked(z);
                    return;
                }
                LogUtil.h("CORESLEEPMISSON_CoreSleepSelectorActivity", "user already change switch, no need auto change");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(boolean z) {
        String str;
        ReleaseLogUtil.e("BTSYNC_CoreSleep_CORESLEEPMISSON_CoreSleepSelectorActivity", "checked isChecked:", Boolean.valueOf(z));
        Intent intent = new Intent();
        Intent intent2 = new Intent();
        intent2.setAction("action_change_core_sleep_button");
        if (z) {
            intent.putExtra("status", "1");
            intent2.putExtra("status", "1");
            str = "1";
        } else {
            str = "0";
            intent.putExtra("status", "0");
            intent2.putExtra("status", "0");
        }
        BroadcastManagerUtil.bFG_(this.b, intent2);
        setResult(-1, intent);
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", "1");
        hashMap.put("status", str);
        String value = AnalyticsValue.SETTING_1090005.value();
        ixx.d().d(this.b, value, hashMap, 0);
        LogUtil.a("CORESLEEPMISSON_CoreSleepSelectorActivity", "BI save coreSleep click event finish, value is ", value);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        LogUtil.a("CORESLEEPMISSON_CoreSleepSelectorActivity", "onDestroy()");
        finish();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
