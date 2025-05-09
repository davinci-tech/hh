package com.huawei.ui.device.activity.notification;

import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwdevice.outofprocess.util.NotificationContentProviderUtil;
import com.huawei.hwdevice.phoneprocess.service.HandleIntentService;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.dialog.CustomAlertDialog;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.progressbar.HealthProgressBar;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.switchbutton.HealthSwitchButton;
import com.huawei.ui.device.interactors.NotificationPushInteractor;
import defpackage.bfg;
import defpackage.jjd;
import defpackage.jrg;
import defpackage.kxz;
import defpackage.nsn;
import defpackage.nsy;
import defpackage.nwx;
import defpackage.nwy;
import defpackage.ocp;
import defpackage.sqo;
import health.compact.a.CommonUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;

/* loaded from: classes9.dex */
public class NotificationOpenActivity extends NotificationBaseActivity {
    private Dialog d;

    /* renamed from: a, reason: collision with root package name */
    private boolean f9159a = false;
    private boolean b = false;
    private CompoundButton.OnCheckedChangeListener e = new CompoundButton.OnCheckedChangeListener() { // from class: com.huawei.ui.device.activity.notification.NotificationOpenActivity.2
        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            String str;
            LogUtil.a("NotificationOpenActivity", "mNotificationSwitch clicked isChecked: ", Boolean.valueOf(z));
            NotificationOpenActivity.this.setReminderSwitchEnable(z);
            NotificationOpenActivity.this.setHarmonyReminderSwitchEnable(z);
            if (z) {
                NotificationOpenActivity.this.mNotificationPushInteractor.d(1);
                if (!NotificationOpenActivity.this.mNotificationPushInteractor.e()) {
                    LogUtil.a("NotificationOpenActivity", "mNotificationSwitch true isAuthorizeEnabled is false");
                    NotificationOpenActivity.this.d();
                } else {
                    LogUtil.a("NotificationOpenActivity", "mNotificationSwitch true isAuthorizeEnabled is true");
                    if (!NotificationOpenActivity.this.mIsThreadRun) {
                        NotificationOpenActivity.this.mNotificationAppListView.setVisibility(0);
                        NotificationOpenActivity.this.mNotificationAppAdapter.b();
                        NotificationOpenActivity.this.mUpdateListHandler.sendEmptyMessage(0);
                    }
                    NotificationOpenActivity.this.reportStatusToMidware();
                }
                NotificationOpenActivity.this.mNotificationDescription.setVisibility(8);
                NotificationOpenActivity.this.mNotificationOpenErrorTipLayout.setVisibility(8);
                if (nwy.h()) {
                    LogUtil.a("NotificationOpenActivity", "is support notification push icon.");
                    NotificationOpenActivity.this.i();
                }
                NotificationOpenActivity.this.a();
                jjd.b(BaseApplication.getContext()).c(true, true);
                NotificationOpenActivity.this.createNotificationEnableIntent(true);
                str = "1";
            } else {
                NotificationOpenActivity.this.mNotificationDescription.setVisibility(0);
                NotificationOpenActivity.this.setNotificationDescription();
                LogUtil.a("NotificationOpenActivity", "mNotificationSwitch false isAuthorizeEnabled is false");
                NotificationOpenActivity.this.mNotificationAppAdapter.a();
                if (NotificationOpenActivity.this.mNotificationPushInteractor.b()) {
                    LogUtil.a("NotificationOpenActivity", "mNotificationSwitch false isAuthorizeEnabled is true");
                    NotificationOpenActivity.this.showAuthorizeDialog();
                } else {
                    NotificationOpenActivity.this.createNotificationEnableIntent();
                }
                if (PermissionUtil.g()) {
                    NotificationOpenActivity.this.mNotificationOpenErrorTipLayout.setVisibility(0);
                } else {
                    NotificationOpenActivity.this.mNotificationOpenErrorTipLayout.setVisibility(8);
                }
                str = "0";
            }
            nwx.d().c(NotificationOpenActivity.this.mContext, str);
            ViewClickInstrumentation.clickOnView(compoundButton);
        }
    };

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        LogUtil.a("NotificationOpenActivity", "enter saveFirstOpenAppToDb");
        int intValue = Integer.valueOf("1").intValue();
        for (String str : bfg.d) {
            if (this.mNotificationPushInteractor.d(str) == intValue) {
                NotificationContentProviderUtil.b(str, intValue);
            }
        }
        if (this.mNotificationPushInteractor.d(bfg.e) == intValue) {
            NotificationContentProviderUtil.b(bfg.e, intValue);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        Intent intent = new Intent(this, (Class<?>) HandleIntentService.class);
        intent.setPackage(this.mContext.getPackageName());
        intent.setAction("com.huawei.bone.ACTION_NOTIFICATION_PUSH");
        Bundle bundle = new Bundle();
        bundle.putString("notificationSwitchChangeType", "notificationSwitchChangeType");
        intent.putExtras(bundle);
        try {
            startService(intent);
        } catch (IllegalStateException | SecurityException e) {
            LogUtil.b("NotificationOpenActivity", "startHandleIntentService", LogAnonymous.b(e));
            sqo.z("startHandleIntentService error");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        ContentResolver bJc_ = jrg.bJc_(this.mContext, "NotificationOpenActivity");
        boolean bJe_ = bJc_ != null ? jrg.bJe_(bJc_, true, "NotificationOpenActivity") : false;
        LogUtil.a("NotificationOpenActivity", "checkedChangeSub isSuccess is ", Boolean.valueOf(bJe_));
        if (bJe_) {
            this.mNotificationDescription.setVisibility(8);
            if (!this.mIsThreadRun) {
                this.mNotificationAppListView.setVisibility(0);
                this.mNotificationAppAdapter.b();
                this.mUpdateListHandler.sendEmptyMessage(0);
            }
            reportStatusToMidware();
            return;
        }
        openNotificationAccess();
        this.mUpdateListHandler.sendEmptyMessageDelayed(1, 100L);
    }

    @Override // com.huawei.ui.device.activity.notification.NotificationBaseActivity
    protected void initInteractor() {
        LogUtil.a("NotificationOpenActivity", "initInteractor");
        setContentView(R.layout.notification_open_layout);
        Intent intent = getIntent();
        if (intent != null) {
            this.mCurrentDeviceId = intent.getStringExtra("device_id");
            this.f9159a = intent.getBooleanExtra("isFromWear", false);
            this.b = intent.getBooleanExtra("isShowWalletOobeActivity", false);
        }
        this.mNotificationPushInteractor = new NotificationPushInteractor(this.mContext);
        b();
    }

    private void b() {
        this.mNotificationSwitch = (HealthSwitchButton) nsy.cMc_(this, R.id.switch_button_notification);
        this.mNotificationSwitch.setChecked(this.mNotificationPushInteractor.b());
        this.mNotificationSwitch.setOnCheckedChangeListener(this.e);
        this.mNotificationAppListView = (HealthRecycleView) findViewById(R.id.notification_app_list);
        this.mNotificationAppAdapter = new NotificationAppAdapter(this.mContext, this.mCurrentDeviceId, this.mNotificationPushInteractor);
        this.mNotificationAppListView.setLayoutManager(new LinearLayoutManager(this));
        this.mNotificationAppListView.setAdapter(this.mNotificationAppAdapter);
        this.mNotificationAppListView.setItemAnimator(new DefaultItemAnimator());
        this.mProgressBar = (HealthProgressBar) findViewById(R.id.notify_load_app_progress);
        this.mProgressBar.setLayerType(1, null);
        this.mProgressBar.setVisibility(0);
        this.mCompleteButton = (HealthButton) findViewById(R.id.complete_button);
        this.mCompleteButton.setEnabled(false);
        this.mCompleteButton.setBackground(getResources().getDrawable(R.drawable.button_background_emphasize));
        this.mCompleteButton.setOnClickListener(this);
        this.mNotificationDescription = (HealthTextView) findViewById(R.id.notification_push_open_description);
        this.mNotificationOpenErrorTipLayout = (LinearLayout) nsy.cMc_(this, R.id.notification_push_error_tip_layout);
        this.mNotificationOpenErrorTip = (HealthTextView) nsy.cMc_(this, R.id.notification_open_error_tip_text);
        setNotificationOpenErrorTip();
        setNotificationDescription();
        if (!this.mNotificationPushInteractor.e()) {
            c();
        } else {
            this.mNotificationSwitch.setChecked(true);
            LogUtil.a("NotificationOpenActivity", "notification switch opened");
        }
    }

    private void c() {
        NoTitleCustomAlertDialog.Builder czz_ = new NoTitleCustomAlertDialog.Builder(this.mContext).czC_(R.string._2130842176_res_0x7f021240, new View.OnClickListener() { // from class: com.huawei.ui.device.activity.notification.NotificationOpenActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("NotificationOpenActivity", "notification clicked enable");
                NotificationOpenActivity.this.mNotificationSwitch.setChecked(true);
                ViewClickInstrumentation.clickOnView(view);
            }
        }).czz_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: com.huawei.ui.device.activity.notification.NotificationOpenActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("NotificationOpenActivity", "notification clicked cancel");
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        if (jrg.bJc_(this.mContext, "NotificationOpenActivity") != null) {
            LogUtil.a("NotificationOpenActivity", "can open notification authorize directly");
            String string = this.mContext.getString(R.string.IDS_device_msgnotif_emui_auth_right);
            if (nsn.ae(BaseApplication.getContext())) {
                string = this.mContext.getString(R.string.IDS_pad_device_auth_right);
            }
            czz_.e(string);
        } else {
            LogUtil.a("NotificationOpenActivity", "cannot open notification authorize directly");
            czz_.e(this.mContext.getString(R.string._2130843382_res_0x7f0216f6));
        }
        NoTitleCustomAlertDialog e = czz_.e();
        e.setCancelable(false);
        if (e.isShowing()) {
            return;
        }
        e.show();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        LogUtil.a("03", 1, "NotificationOpenActivity", "onResume() mNotificationSwitch : isChecked: ", Boolean.valueOf(this.mNotificationSwitch.isChecked()));
        super.onResume();
        if (!this.mNotificationPushInteractor.e()) {
            LogUtil.a("NotificationOpenActivity", "isNotificationAuthorizeEnabled = false");
            this.mNotificationPushInteractor.e(0);
        }
        if (this.mNotificationPushInteractor.b()) {
            LogUtil.a("NotificationOpenActivity", "onResume() isAuthorizeEnabled = true");
            this.mNotificationSwitch.setChecked(true);
            if (!this.mIsThreadRun) {
                this.mNotificationAppListView.setVisibility(0);
                this.mNotificationAppAdapter.b();
                this.mUpdateListHandler.sendEmptyMessage(0);
            }
        } else {
            LogUtil.a("NotificationOpenActivity", "onResume() isAuthorizeEnabled = false");
            this.mNotificationSwitch.setChecked(false);
            this.mNotificationAppAdapter.a();
            if (PermissionUtil.g()) {
                this.mNotificationOpenErrorTipLayout.setVisibility(0);
            } else {
                this.mNotificationOpenErrorTipLayout.setVisibility(8);
            }
        }
        reportStatusToMidware();
        createNotificationEnableIntent();
    }

    @Override // com.huawei.ui.device.activity.notification.NotificationBaseActivity, android.view.View.OnClickListener
    public void onClick(View view) {
        super.onClick(view);
        if (view.getId() == this.mCompleteButton.getId()) {
            if (this.mNotificationPushInteractor.b()) {
                LogUtil.a("NotificationOpenActivity", "notification open successfully");
                e();
            } else {
                b(kxz.d(), this.mContext);
                c(this.mContext);
                LogUtil.a("NotificationOpenActivity", "notification remind twice");
                g();
            }
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void g() {
        NoTitleCustomAlertDialog.Builder czz_ = new NoTitleCustomAlertDialog.Builder(this.mContext).czC_(R.string._2130842176_res_0x7f021240, new View.OnClickListener() { // from class: com.huawei.ui.device.activity.notification.NotificationOpenActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NotificationOpenActivity.this.mNotificationSwitch.setChecked(true);
                LogUtil.a("NotificationOpenActivity", "notification remind twice clicked open");
                ViewClickInstrumentation.clickOnView(view);
            }
        }).czz_(R.string._2130842831_res_0x7f0214cf, new View.OnClickListener() { // from class: com.huawei.ui.device.activity.notification.NotificationOpenActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NotificationOpenActivity.this.j();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        czz_.e(this.mContext.getString(R.string._2130842833_res_0x7f0214d1));
        NoTitleCustomAlertDialog e = czz_.e();
        e.setCancelable(false);
        if (e.isShowing()) {
            return;
        }
        e.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        if (this.b) {
            ocp.cVT_(this.mCurrentDeviceId, this, this.f9159a, true);
        } else {
            Intent intent = new Intent();
            intent.putExtra("isFromWear", this.f9159a);
            intent.putExtra("device_id", this.mCurrentDeviceId);
            intent.setClassName(this.mContext, "com.huawei.ui.homewear21.home.WearHomeActivity");
            startActivity(intent);
        }
        finish();
    }

    private void e() {
        LogUtil.a("NotificationOpenActivity", "showAlertDialog");
        View inflate = LayoutInflater.from(this.mContext).inflate(R.layout.notification_alert_dialog, (ViewGroup) null);
        ImageView imageView = (ImageView) inflate.findViewById(R.id.set_notification_alert_dialog_image);
        HealthTextView healthTextView = (HealthTextView) inflate.findViewById(R.id.set_nitification_alert_dialog_text);
        imageView.setImageResource(R.drawable._2131430227_res_0x7f0b0b53);
        healthTextView.setText(R.string.IDS_device_msgnotif_alert_dialog);
        CustomAlertDialog.Builder cyn_ = new CustomAlertDialog.Builder(this.mContext).cyn_(R.string._2130841554_res_0x7f020fd2, new DialogInterface.OnClickListener() { // from class: com.huawei.ui.device.activity.notification.NotificationOpenActivity.8
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                NotificationOpenActivity.this.d.dismiss();
                NotificationOpenActivity.this.j();
                ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
            }
        });
        this.d = cyn_.c();
        cyn_.cyp_(inflate);
        this.d.setCancelable(false);
        if (this.d.isShowing() || isFinishing()) {
            return;
        }
        this.d.show();
    }

    private void b(String str, Context context) {
        LogUtil.a("NotificationOpenActivity", "setDialogCheckTime,time-----------", str);
        SharedPreferenceManager.e(context, String.valueOf(10000), "sp_dialog_check_time", str, new StorageParams(0));
    }

    private void c(Context context) {
        StorageParams storageParams = new StorageParams(0);
        String b = SharedPreferenceManager.b(this.mContext, String.valueOf(10000), "dialog_show_time");
        LogUtil.a("NotificationOpenActivity", "setDialogshowTime,number-----------", b);
        if (TextUtils.isEmpty(b)) {
            LogUtil.a("NotificationOpenActivity", "numberIsNull", b);
            SharedPreferenceManager.e(context, String.valueOf(10000), "dialog_show_time", "0", storageParams);
        } else {
            int m = CommonUtil.m(context, b);
            LogUtil.a("NotificationOpenActivity", "numberPlusOne", b);
            SharedPreferenceManager.e(context, String.valueOf(10000), "dialog_show_time", String.valueOf(m + 1), storageParams);
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
