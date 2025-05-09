package com.huawei.ui.device.activity.notification;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.ConcatAdapter;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwdevice.phoneprocess.service.HandleIntentService;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.activity.WebViewActivity;
import com.huawei.operation.utils.Constants;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.progressbar.HealthProgressBar;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.switchbutton.HealthSwitchButton;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.device.interactors.DeviceSettingsInteractors;
import com.huawei.ui.device.interactors.NotificationPushInteractor;
import defpackage.ixx;
import defpackage.jjd;
import defpackage.jrg;
import defpackage.nsn;
import defpackage.nsy;
import defpackage.nwx;
import defpackage.nwy;
import health.compact.a.CommonUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.HashMap;

/* loaded from: classes6.dex */
public class NotificationSettingActivity extends NotificationBaseActivity {

    /* renamed from: a, reason: collision with root package name */
    private DeviceSettingsInteractors f9162a;
    private LinearLayout d;
    private HealthTextView f;
    private LinearLayout g;
    private NotificationHeaderAdapter i;
    private boolean e = false;
    private DeviceCapability c = null;
    private CompoundButton.OnCheckedChangeListener b = new CompoundButton.OnCheckedChangeListener() { // from class: com.huawei.ui.device.activity.notification.NotificationSettingActivity.3
        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            String str;
            LogUtil.a("NotificationSettingActivity", "mNotificationSwitch clicked isChecked: ", Boolean.valueOf(z));
            NotificationSettingActivity.this.setReminderSwitchEnable(z);
            NotificationSettingActivity.this.setHarmonyReminderSwitchEnable(z);
            if (!z) {
                NotificationSettingActivity.this.g.setVisibility(8);
                NotificationSettingActivity.this.d.setVisibility(0);
                NotificationSettingActivity.this.setNotificationDescription();
                LogUtil.a("NotificationSettingActivity", "mNotificationSwitch false isAuthorizeEnabled is false");
                NotificationSettingActivity.this.mNotificationAppAdapter.a();
                if (NotificationSettingActivity.this.mNotificationPushInteractor.b()) {
                    LogUtil.a("NotificationSettingActivity", "mNotificationSwitch false isAuthorizeEnabled is true");
                    NotificationSettingActivity.this.showAuthorizeDialog();
                } else {
                    NotificationSettingActivity.this.createNotificationEnableIntent();
                }
                if (PermissionUtil.g()) {
                    NotificationSettingActivity.this.mNotificationOpenErrorTipLayout.setVisibility(0);
                } else {
                    NotificationSettingActivity.this.mNotificationOpenErrorTipLayout.setVisibility(8);
                }
                str = "0";
            } else {
                NotificationSettingActivity.this.mNotificationPushInteractor.d(1);
                if (!NotificationSettingActivity.this.mNotificationPushInteractor.e()) {
                    LogUtil.a("NotificationSettingActivity", "mNotificationSwitch true isAuthorizeEnabled is false");
                    NotificationSettingActivity.this.e();
                } else {
                    LogUtil.a("NotificationSettingActivity", "mNotificationSwitch true isAuthorizeEnabled is true");
                    NotificationSettingActivity.this.g.setVisibility(0);
                    NotificationSettingActivity.this.d.setVisibility(8);
                    if (!NotificationSettingActivity.this.mIsThreadRun) {
                        NotificationSettingActivity.this.mNotificationAppListView.setVisibility(0);
                        NotificationSettingActivity.this.mNotificationAppAdapter.b();
                        NotificationSettingActivity.this.mUpdateListHandler.sendEmptyMessage(0);
                    }
                    NotificationSettingActivity.this.reportStatusToMidware();
                }
                if (nwy.h()) {
                    LogUtil.a("NotificationSettingActivity", "is support notification push icon.");
                    Intent intent = new Intent(NotificationSettingActivity.this, (Class<?>) HandleIntentService.class);
                    intent.setPackage(NotificationSettingActivity.this.mContext.getPackageName());
                    intent.setAction("com.huawei.bone.ACTION_NOTIFICATION_PUSH");
                    Bundle bundle = new Bundle();
                    bundle.putString("notificationSwitchChangeType", "notificationSwitchChangeType");
                    intent.putExtras(bundle);
                    NotificationSettingActivity.this.startService(intent);
                }
                NotificationSettingActivity.this.createNotificationEnableIntent(true);
                str = "1";
            }
            nwx.d().c(NotificationSettingActivity.this.mContext, str);
            ViewClickInstrumentation.clickOnView(compoundButton);
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        ContentResolver bJc_ = jrg.bJc_(this.mContext, "NotificationSettingActivity");
        boolean bJe_ = bJc_ != null ? jrg.bJe_(bJc_, true, "NotificationSettingActivity") : false;
        LogUtil.a("NotificationSettingActivity", "checkedChangeSub isSuccess is ", Boolean.valueOf(bJe_));
        if (bJe_) {
            this.g.setVisibility(0);
            this.d.setVisibility(8);
            if (!this.mIsThreadRun) {
                this.mNotificationAppListView.setVisibility(0);
                this.mNotificationAppAdapter.b();
                this.mUpdateListHandler.sendEmptyMessage(0);
            }
            reportStatusToMidware();
        } else {
            openNotificationAccess();
            this.mUpdateListHandler.sendEmptyMessageDelayed(1, 100L);
        }
        jjd.b(BaseApplication.getContext()).c(true, true);
    }

    @Override // com.huawei.ui.device.activity.notification.NotificationBaseActivity
    protected void initInteractor() {
        LogUtil.a("NotificationSettingActivity", "initInteractor");
        setContentView(R.layout.notification_setting_layout);
        getWindow().setFlags(16777216, 16777216);
        Intent intent = getIntent();
        if (intent != null) {
            this.mCurrentDeviceId = intent.getStringExtra("device_id");
        }
        DeviceSettingsInteractors d = DeviceSettingsInteractors.d(this.mContext);
        this.f9162a = d;
        this.c = d.e(this.mCurrentDeviceId);
        this.mNotificationPushInteractor = new NotificationPushInteractor(this.mContext);
        c();
    }

    private void c() {
        this.mNotificationAppListView = (HealthRecycleView) findViewById(R.id.notification_app_list);
        this.mNotificationAppListView.setLayoutManager(new LinearLayoutManager(this));
        this.mNotificationHeader = LayoutInflater.from(this.mContext).inflate(R.layout.notification_setting_list_header, (ViewGroup) this.mNotificationAppListView, false);
        this.i = new NotificationHeaderAdapter(this.mNotificationHeader);
        this.mNotificationAppAdapter = new NotificationAppAdapter(this.mContext, this.mCurrentDeviceId, this.mNotificationPushInteractor);
        this.mNotificationAppListView.setAdapter(new ConcatAdapter((RecyclerView.Adapter<? extends RecyclerView.ViewHolder>[]) new RecyclerView.Adapter[]{this.i, this.mNotificationAppAdapter}));
        this.mNotificationAppListView.setItemAnimator(new DefaultItemAnimator());
        ((CustomTitleBar) findViewById(R.id.notification_setting_title)).setLeftButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.device.activity.notification.NotificationSettingActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (nsn.a(400)) {
                    LogUtil.h("NotificationSettingActivity", "click too fast before leaving");
                    ViewClickInstrumentation.clickOnView(view);
                } else {
                    NotificationSettingActivity.this.finish();
                    ViewClickInstrumentation.clickOnView(view);
                }
            }
        });
        this.mNotificationSwitch = (HealthSwitchButton) nsy.cMd_(this.mNotificationHeader, R.id.switch_button_notification);
        this.mNotificationSwitch.setChecked(this.mNotificationPushInteractor.b());
        this.mNotificationSwitch.setOnCheckedChangeListener(this.b);
        this.mNotificationSwitch.setOnTouchListener(new View.OnTouchListener() { // from class: com.huawei.ui.device.activity.notification.NotificationSettingActivity.4
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() != 1) {
                    return false;
                }
                if (nsn.a(800)) {
                    LogUtil.h("NotificationSettingActivity", "click too fast");
                    return true;
                }
                if (SystemClock.elapsedRealtime() - NotificationSettingActivity.this.mLastCloseTime >= 400) {
                    return false;
                }
                LogUtil.h("NotificationSettingActivity", "click too fast after close");
                return true;
            }
        });
        this.g = (LinearLayout) nsy.cMd_(this.mNotificationHeader, R.id.notification_error_layout);
        HealthTextView healthTextView = (HealthTextView) nsy.cMd_(this.mNotificationHeader, R.id.notification_error_tip_text);
        this.f = healthTextView;
        healthTextView.setOnClickListener(this);
        this.mProgressBar = (HealthProgressBar) nsy.cMd_(this.mNotificationHeader, R.id.notify_load_app_progress);
        this.mProgressBar.setLayerType(1, null);
        if (this.mNotificationPushInteractor.b()) {
            this.g.setVisibility(0);
        }
        this.mProgressBar.setVisibility(0);
        this.d = (LinearLayout) nsy.cMd_(this.mNotificationHeader, R.id.notification_push_open_layout);
        this.mNotificationDescription = (HealthTextView) nsy.cMd_(this.mNotificationHeader, R.id.notification_push_open_description);
        this.mNotificationOpenErrorTipLayout = (LinearLayout) nsy.cMd_(this.mNotificationHeader, R.id.notification_push_error_tip_layout);
        this.mNotificationOpenErrorTip = (HealthTextView) nsy.cMd_(this.mNotificationHeader, R.id.notification_open_error_tip_text);
        setNotificationOpenErrorTip();
        setNotificationDescription();
    }

    private void a() {
        Intent intent = getIntent();
        if (intent != null) {
            boolean booleanExtra = intent.getBooleanExtra("isFromDialog", false);
            this.e = booleanExtra;
            if (booleanExtra) {
                if (jrg.bJc_(this.mContext, "NotificationSettingActivity") != null) {
                    LogUtil.a("NotificationSettingActivity", "support OpenNotification by system");
                } else {
                    this.mNotificationSwitch.setChecked(true);
                }
            }
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        ReleaseLogUtil.e("Notify_NotificationSettingActivity", "onResume() mNotificationSwitch : isChecked : ", Boolean.valueOf(this.mNotificationSwitch.isChecked()));
        if (!this.mNotificationPushInteractor.e()) {
            this.mNotificationPushInteractor.e(0);
            ReleaseLogUtil.e("Notify_NotificationSettingActivity", "onResume() isNotificationAuthorizeEnabled is false");
        }
        a();
        if (this.mNotificationPushInteractor.b()) {
            LogUtil.a("NotificationSettingActivity", "onResume() isAuthorizeEnabled = true");
            this.mNotificationSwitch.setChecked(true);
            this.g.setVisibility(0);
            this.d.setVisibility(8);
            if (!this.mIsThreadRun) {
                this.mNotificationAppListView.setVisibility(0);
                this.mNotificationAppAdapter.b();
                this.mUpdateListHandler.sendEmptyMessage(0);
            }
        } else {
            LogUtil.a("NotificationSettingActivity", "onResume() isAuthorizeEnabled = false");
            this.mNotificationSwitch.setChecked(false);
            this.g.setVisibility(8);
            this.d.setVisibility(0);
            setNotificationDescription();
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
        if (view.getId() == R.id.notification_error_tip_text) {
            HashMap hashMap = new HashMap(16);
            hashMap.put("click", "1");
            ixx.d().d(this.mContext, AnalyticsValue.NOTIFY_ERROR_ALERT_1090014.value(), hashMap, 0);
            if (TextUtils.isEmpty(this.mNotificationSettingGuideUrlHost) || TextUtils.isEmpty(this.mNotificationSettingGuideUrlHostOversea)) {
                getGrsNotificationSettingUrl();
            }
            String b = b();
            LogUtil.a("NotificationSettingActivity", "onClick url: ", Uri.parse(b).getPath());
            Intent intent = new Intent(this.mContext, (Class<?>) WebViewActivity.class);
            intent.putExtra("url", b);
            intent.putExtra(Constants.JUMP_MODE_KEY, 0);
            startActivity(intent);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private String b() {
        String str;
        int m = CommonUtil.m(BaseApplication.getContext(), LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1009));
        LogUtil.a("NotificationSettingActivity", "getNotificationTipUrl siteId:", Integer.valueOf(m));
        if (m == 1) {
            if (LanguageUtil.m(BaseApplication.getContext())) {
                DeviceCapability deviceCapability = this.c;
                if (deviceCapability != null && deviceCapability.isAvoidDisturb()) {
                    str = this.mNotificationSettingGuideUrlHost + "/Android_Note_zh/EMUI8.0/C001B001/zh-CN/index.html";
                } else {
                    str = this.mNotificationSettingGuideUrlHost + "/Android_Note_zh/EMUI8.0/C001B001/zh-CN/index2.html";
                }
            } else {
                DeviceCapability deviceCapability2 = this.c;
                if (deviceCapability2 != null && deviceCapability2.isAvoidDisturb()) {
                    str = this.mNotificationSettingGuideUrlHost + "/Android_Note_en/EMUI8.0/C001B001/en-US/index.html";
                } else {
                    str = this.mNotificationSettingGuideUrlHost + "/Android_Note_en/EMUI8.0/C001B001/en-US/index2.html";
                }
            }
        } else if (CommonUtil.ad(BaseApplication.getContext())) {
            DeviceCapability deviceCapability3 = this.c;
            if (deviceCapability3 != null && deviceCapability3.isAvoidDisturb()) {
                str = this.mNotificationSettingGuideUrlHost + "/Android_Note_fr_FR/EMUI8.0/C001B001/fr-FR/index.html";
            } else {
                str = this.mNotificationSettingGuideUrlHost + "/Android_Note_fr_FR/EMUI8.0/C001B001/fr-FR/index2.html";
            }
        } else if (d() && LanguageUtil.r(BaseApplication.getContext())) {
            str = this.mNotificationSettingGuideUrlHost + "/Android_Note_en/EMUI8.0/C001B001/de-DE/index.html";
        } else {
            str = this.mNotificationSettingGuideUrlHostOversea + "/handbook/Jumppage/EMUI8.0/C001B001/en-US/index.html?lang=%s&devicetype=Notification".replace("lang=%s", "lang=" + getLanguageTag());
        }
        LogUtil.a("NotificationSettingActivity", "getNotificationTipUrl url : ", str);
        return str;
    }

    private boolean d() {
        return !TextUtils.isEmpty(this.mNotificationSettingGuideUrlHost) && this.mNotificationSettingGuideUrlHost.contains("dra");
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4 && nsn.a(400)) {
            LogUtil.h("NotificationSettingActivity", "click too fast before key_back");
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
