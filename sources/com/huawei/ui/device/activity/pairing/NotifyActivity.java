package com.huawei.ui.device.activity.pairing;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.RemoteException;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import com.apprichtap.haptic.base.PrebakedEffectId;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.phoneprocess.mgr.connectmgr.DeviceDialogMessage;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwservicesmgr.IWearPhoneServiceAIDL;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import defpackage.jez;
import defpackage.nrj;
import health.compact.a.CommonUtil;

/* loaded from: classes9.dex */
public class NotifyActivity extends BaseActivity {

    /* renamed from: a, reason: collision with root package name */
    private String f9199a;
    private int c;
    private long d;
    private Handler e = new Handler();

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        LogUtil.a("NotifyActivity", "NotifyActivity onCreate.");
        Window window = getWindow();
        window.setGravity(80);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = -1;
        attributes.height = -2;
        window.setAttributes(attributes);
        Intent intent = getIntent();
        if (intent != null) {
            this.f9199a = intent.getStringExtra("deviceName");
            this.c = intent.getIntExtra("dialog_style", 0);
            this.d = intent.getLongExtra("device_id", -1L);
            if (!TextUtils.isEmpty(this.f9199a)) {
                this.c = 100;
            }
            c(this.c);
        }
    }

    private void c(int i) {
        switch (i) {
            case 100:
                b();
                break;
            case 101:
                if (!isFinishing()) {
                    nrj.cKb_(this, new View.OnClickListener() { // from class: com.huawei.ui.device.activity.pairing.NotifyActivity.4
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view) {
                            NotifyActivity.this.finish();
                            ViewClickInstrumentation.clickOnView(view);
                        }
                    }, new View.OnClickListener() { // from class: com.huawei.ui.device.activity.pairing.NotifyActivity.1
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view) {
                            NotifyActivity.this.finish();
                            ViewClickInstrumentation.clickOnView(view);
                        }
                    });
                    break;
                }
                break;
            case 102:
                if (!isFinishing()) {
                    nrj.cJY_(this, new View.OnClickListener() { // from class: com.huawei.ui.device.activity.pairing.NotifyActivity.3
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view) {
                            NotifyActivity notifyActivity = NotifyActivity.this;
                            notifyActivity.d(notifyActivity.d);
                            NotifyActivity.this.finish();
                            ViewClickInstrumentation.clickOnView(view);
                        }
                    }, new View.OnClickListener() { // from class: com.huawei.ui.device.activity.pairing.NotifyActivity.5
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view) {
                            NotifyActivity.this.finish();
                            ViewClickInstrumentation.clickOnView(view);
                        }
                    });
                    break;
                }
                break;
            default:
                LogUtil.a("NotifyActivity", "unknown dialog style.");
                finish();
                break;
        }
    }

    private void b() {
        CustomTextAlertDialog.Builder builder = new CustomTextAlertDialog.Builder(this);
        builder.b(getResources().getString(R$string.IDS_hwh_open_service_pop_up_notification_note));
        builder.e(getResources().getString(R$string.IDS_hw_health_double_phone_dialog_content1));
        builder.cyS_(getResources().getString(R$string.IDS_settings_button_cancal), new View.OnClickListener() { // from class: com.huawei.ui.device.activity.pairing.NotifyActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("NotifyActivity", "close");
                NotifyActivity.this.a();
                NotifyActivity notifyActivity = NotifyActivity.this;
                notifyActivity.e(1, notifyActivity.f9199a);
                NotifyActivity.this.finish();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        builder.cyV_(getResources().getString(R$string.IDS_btsdk_confirm_connect), new View.OnClickListener() { // from class: com.huawei.ui.device.activity.pairing.NotifyActivity.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NotifyActivity.this.a();
                NotifyActivity notifyActivity = NotifyActivity.this;
                notifyActivity.e(0, notifyActivity.f9199a);
                NotifyActivity.this.finish();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        CustomTextAlertDialog a2 = builder.a();
        a2.setCancelable(false);
        if (isFinishing()) {
            return;
        }
        a2.show();
        c();
    }

    private void c() {
        LogUtil.a("NotifyActivity", "Enter handleDoublePhoneTimeout.");
        Handler handler = this.e;
        if (handler == null) {
            LogUtil.h("NotifyActivity", "handleDoublePhoneTimeout mHandler is null.");
        } else {
            handler.postDelayed(new Runnable() { // from class: com.huawei.ui.device.activity.pairing.NotifyActivity.8
                @Override // java.lang.Runnable
                public void run() {
                    LogUtil.a("NotifyActivity", "double phone dialog select timeout.");
                    NotifyActivity notifyActivity = NotifyActivity.this;
                    notifyActivity.e(1, notifyActivity.f9199a);
                    NotifyActivity.this.finish();
                }
            }, 60000L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        if (this.e != null) {
            LogUtil.a("NotifyActivity", "remove handler message.");
            this.e.removeCallbacksAndMessages(null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i, String str) {
        if (str == null) {
            LogUtil.a("NotifyActivity", "deviceName is null.");
        } else {
            LogUtil.a("NotifyActivity", "send broadcast : ", "com.huawei.health.action.ACTION_DOUBLE_PHONE_CONFIRM_OR_CANCEL", str);
            d(str, i);
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        CommonUtil.a(BaseApplication.getContext());
        super.onDestroy();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(long j) {
        IWearPhoneServiceAIDL e = jez.e();
        if (e != null) {
            try {
                DeviceDialogMessage deviceDialogMessage = new DeviceDialogMessage();
                deviceDialogMessage.setMethod(PrebakedEffectId.RT_SPEED_UP);
                deviceDialogMessage.setIsStatusFlag(false);
                deviceDialogMessage.setSelectId(j);
                deviceDialogMessage.setDeviceName(null);
                e.dialogMessage(deviceDialogMessage, null);
            } catch (RemoteException unused) {
                LogUtil.b("NotifyActivity", "connectDevice RemoteException");
            }
        }
    }

    private void d(String str, int i) {
        IWearPhoneServiceAIDL e = jez.e();
        if (e != null) {
            try {
                DeviceDialogMessage deviceDialogMessage = new DeviceDialogMessage();
                deviceDialogMessage.setMethod(PrebakedEffectId.RT_JUMP);
                deviceDialogMessage.setSelectId(0L);
                deviceDialogMessage.setDeviceName(str);
                if (i == 0) {
                    deviceDialogMessage.setIsStatusFlag(false);
                    e.dialogMessage(deviceDialogMessage, null);
                } else if (i == 1) {
                    deviceDialogMessage.setIsStatusFlag(true);
                    e.dialogMessage(deviceDialogMessage, null);
                } else {
                    LogUtil.a("NotifyActivity", "notifyDeviceDoublePhone else branch");
                }
            } catch (RemoteException unused) {
                LogUtil.b("NotifyActivity", "notifyDeviceDoublePhone RemoteException");
            }
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
