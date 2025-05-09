package com.huawei.ui.device.activity.notification;

import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.BulletSpan;
import android.text.style.ClickableSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwdevice.outofprocess.util.NotificationContentProviderUtil;
import com.huawei.hwdevice.phoneprocess.service.HandleIntentService;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.activity.WebViewActivity;
import com.huawei.operation.utils.Constants;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.dialog.CustomAlertDialog;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.progressbar.HealthProgressBar;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.switchbutton.HealthSwitchButton;
import com.huawei.ui.device.activity.notification.NotificationBaseActivity;
import com.huawei.ui.device.interactors.NotificationPushInteractor;
import defpackage.bfg;
import defpackage.cvs;
import defpackage.cvz;
import defpackage.gnm;
import defpackage.iyg;
import defpackage.jfu;
import defpackage.jjb;
import defpackage.jje;
import defpackage.jpt;
import defpackage.jqi;
import defpackage.jrg;
import defpackage.kiq;
import defpackage.nsi;
import defpackage.nsn;
import defpackage.nsy;
import defpackage.nwx;
import defpackage.nwy;
import defpackage.nxa;
import defpackage.sqo;
import health.compact.a.CommonUtil;
import health.compact.a.GRSManager;
import health.compact.a.LanguageUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.MagicBuild;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* loaded from: classes6.dex */
public abstract class NotificationBaseActivity extends BaseActivity implements View.OnClickListener {
    private static final int CORE_POOL_SIZE = 1;
    private static final String DIFFERENCE = "/handbook";
    private static final String EN_LANGUAGE = "en-US";
    private static final String ERROR_CALENDAR_NAME = "Calendar data migrator";
    private static final int GAP_WIDTH = 20;
    private static final String LOCAL_URL_SYMBOL = "drcn";
    protected static final int MESSAGE_DELAYED = 100;
    private static final int MSG_SHOW_REMINDER_SWITCH = 1;
    private static final int MSG_SHOW_SMART_REMINDER_SWITCH = 2;
    private static final long NORMAL_LOADING_TIME = 1000;
    protected static final String NOTIFICATION_SWITCH_OPEN_CHINA = "/hwtips/scenepage/faq_switch/%s/index.html";
    public static final int NOTIFY_DATA_CHANGE = 0;
    protected static final String OPEN_ERROR_URL_OVERSEA = "/handbook/Jumppage/EMUI8.0/C001B001/en-US/index.html?lang=%s&devicetype=faq_switch";
    protected static final String OVERSEA_URL_SYMBOL = "dra";
    private static final String POCO_BRAND = "poco";
    private static final String REDMI_BRAND = "redmi";
    private static final int REQUEST_CODE_ADDRESS = 100;
    public static final int SHOW_TIP = 1;
    private static final String TAG = "NotificationBaseActivity";
    private static final String TAG_RELEASE = "Notify_NotificationBaseActivity";
    private static final int THREAD_MAX_SIZE = 3;
    private static final String XIAOMI_BRAND = "xiaomi";
    private static final String ZH_LANGUAGE = "zh-CN";
    protected HealthButton mCompleteButton;
    public Context mContext;
    private String mDefaultSms;
    private GRSManager mGrsManager;
    protected LinearLayout mHarmonyNotifyReminderSwitchLayout;
    private HealthSwitchButton mHarmonyPhoneButton;
    private HealthTextView mHarmonyPhoneContent;
    private HealthTextView mHarmonyPhoneMore;
    private HealthTextView mHarmonyPhoneTitle;
    private boolean mIsDomestic;
    private boolean mIsSmsPkg;
    protected long mLastCloseTime;
    public NotificationAppAdapter mNotificationAppAdapter;
    protected HealthRecycleView mNotificationAppListView;
    protected HealthTextView mNotificationDescription;
    protected View mNotificationHeader;
    protected HealthTextView mNotificationOpenErrorTip;
    protected LinearLayout mNotificationOpenErrorTipLayout;
    protected NotificationPushInteractor mNotificationPushInteractor;
    protected HealthSwitchButton mNotificationSwitch;
    protected LinearLayout mNotifyReminderSwitchLayout;
    private HealthSwitchButton mPhoneButton;
    private HealthTextView mPhoneContent;
    private HealthTextView mPhoneTitle;
    protected HealthProgressBar mProgressBar;
    private String mSystemSms;
    protected ThreadPoolManager mThreadPool;
    protected String mNotificationSettingGuideUrlHost = "";
    protected String mNotificationSettingGuideUrlHostOversea = "";
    protected boolean mIsThreadRun = false;
    protected nxa mUpdateListHandler = null;
    protected String mCurrentDeviceId = "";
    private boolean mIsOnCreated = false;
    private String mDeviceName = "";
    private int mReminderSwitchStatus = -1;
    private boolean mIsSupportSmartNotify = false;
    private boolean mIsSupportNotify = false;
    private boolean mIsFirstShowSmartSwitch = false;
    private Handler mHandler = new Handler(Looper.getMainLooper()) { // from class: com.huawei.ui.device.activity.notification.NotificationBaseActivity.3
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message == null) {
                return;
            }
            int i = message.what;
            if (i == 1) {
                NotificationBaseActivity.this.setNotifyReminderView(message);
            } else if (i == 2) {
                NotificationBaseActivity.this.setSmartNotifyReminderView(message);
            } else {
                super.handleMessage(message);
            }
        }
    };
    private BroadcastReceiver mAppInstalledReceiver = new BroadcastReceiver() { // from class: com.huawei.ui.device.activity.notification.NotificationBaseActivity.6
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                LogUtil.h(NotificationBaseActivity.TAG, "mAppInstalledReceiver intent is null");
                return;
            }
            String action = intent.getAction();
            if ("android.intent.action.PACKAGE_ADDED".equals(action) || "android.intent.action.PACKAGE_REMOVED".equals(action)) {
                LogUtil.a(NotificationBaseActivity.TAG, "mAppInstalledReceiver action:", action, " packageName:", intent.getDataString());
                NotificationBaseActivity.this.mThreadPool.execute(new d());
            }
        }
    };

    protected abstract void initInteractor();

    @Override // com.huawei.ui.commonui.base.BaseActivity, android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
    }

    class d implements Runnable {
        private d() {
        }

        @Override // java.lang.Runnable
        public void run() {
            NotificationBaseActivity.this.mIsThreadRun = true;
            DeviceInfo a2 = jpt.a(NotificationBaseActivity.TAG);
            if (a2 != null) {
                NotificationBaseActivity.this.mDeviceName = jfu.c(a2.getProductType(), a2.getDeviceName(), false);
            }
            final long currentTimeMillis = System.currentTimeMillis();
            final List loadAllApps = NotificationBaseActivity.this.loadAllApps();
            NotificationBaseActivity.this.runOnUiThread(new Runnable() { // from class: com.huawei.ui.device.activity.notification.NotificationBaseActivity.d.3
                @Override // java.lang.Runnable
                public void run() {
                    if (NotificationBaseActivity.this.isFinishing() || NotificationBaseActivity.this.isDestroyed() || NotificationBaseActivity.this.mNotificationAppAdapter == null) {
                        LogUtil.h(NotificationBaseActivity.TAG, "LoadAppRunnable run finish");
                        return;
                    }
                    NotificationBaseActivity.this.mIsThreadRun = false;
                    NotificationBaseActivity.this.mNotificationAppAdapter.d(loadAllApps);
                    NotificationBaseActivity.this.mNotificationAppAdapter.b(NotificationBaseActivity.this.mDeviceName);
                    long currentTimeMillis2 = System.currentTimeMillis();
                    NotificationBaseActivity.this.mNotificationAppAdapter.notifyDataSetChanged();
                    NotificationBaseActivity.this.updateUi();
                    if (loadAllApps == null || System.currentTimeMillis() - currentTimeMillis <= 1000) {
                        return;
                    }
                    ReleaseLogUtil.e(NotificationBaseActivity.TAG_RELEASE, "appList size is ", Integer.valueOf(loadAllApps.size()), "and loading cost ", Long.valueOf(currentTimeMillis2 - currentTimeMillis), "ms, UiUpdate cost ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis2), "ms");
                }
            });
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mContext = this;
        this.mUpdateListHandler = new nxa(this);
        this.mThreadPool = ThreadPoolManager.a(1, 3);
        this.mGrsManager = GRSManager.a(BaseApplication.getContext());
        getGrsNotificationSettingUrl();
        initInteractor();
        initReminderView();
        this.mThreadPool.execute(new d());
        registerReceiver();
        this.mIsOnCreated = true;
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        LogUtil.a(TAG, "onDestroy");
        super.onDestroy();
        if (!this.mIsOnCreated) {
            LogUtil.h(TAG, "onDestroy mIsOnCreated is false");
            return;
        }
        unregisterReceiver(this.mAppInstalledReceiver);
        ThreadPoolManager threadPoolManager = this.mThreadPool;
        if (threadPoolManager != null) {
            threadPoolManager.shutdown();
        }
        nxa nxaVar = this.mUpdateListHandler;
        if (nxaVar != null) {
            nxaVar.removeMessages(0);
        }
        CommonUtil.a(this.mContext);
    }

    private void initReminderView() {
        this.mThreadPool.execute(new Runnable() { // from class: com.huawei.ui.device.activity.notification.NotificationBaseActivity.10
            @Override // java.lang.Runnable
            public void run() {
                NotificationBaseActivity.this.getSupportNotifyValue();
                NotificationBaseActivity.this.runOnUiThread(new Runnable() { // from class: com.huawei.ui.device.activity.notification.NotificationBaseActivity.10.4
                    @Override // java.lang.Runnable
                    public void run() {
                        if (NotificationBaseActivity.this.isDestroyed()) {
                            return;
                        }
                        NotificationBaseActivity.this.initReminderSwitch();
                    }
                });
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getSupportNotifyValue() {
        this.mIsSupportSmartNotify = nwy.c() && nwy.i();
        this.mIsSupportNotify = nwy.j() && cvz.a() != 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<jje> loadAllApps() {
        runOnUiThread(new Runnable() { // from class: com.huawei.ui.device.activity.notification.NotificationBaseActivity.8
            @Override // java.lang.Runnable
            public void run() {
                NotificationBaseActivity.this.mNotificationSwitch.setEnabled(false);
                NotificationBaseActivity.this.mProgressBar.setVisibility(0);
                NotificationBaseActivity.this.mNotificationAppAdapter.c();
            }
        });
        LogUtil.a(TAG, "loadAllApps isChecked:", Boolean.valueOf(this.mNotificationSwitch.isChecked()));
        Intent intent = new Intent("android.intent.action.MAIN", (Uri) null);
        intent.addCategory("android.intent.category.LAUNCHER");
        List<ResolveInfo> queryIntentActivities = getPackageManager().queryIntentActivities(intent, 0);
        this.mDefaultSms = kiq.e(TAG);
        this.mSystemSms = getSystemSmsPackageName();
        this.mIsDomestic = LanguageUtil.ak(this.mContext);
        LogUtil.a(TAG, "loadAllApps resolveInfos size:", Integer.valueOf(queryIntentActivities.size()), " mDefaultSms:", this.mDefaultSms, " mSystemSms:", this.mSystemSms, " mIsDomestic:", Boolean.valueOf(this.mIsDomestic));
        return loadAllAppInfo(queryIntentActivities);
    }

    private String getSystemSmsPackageName() {
        String str;
        List unmodifiableList = Collections.unmodifiableList(Arrays.asList("com.android.mms", "cn.nubia.mms", "com.hihonor.mms", "com.samsung.android.messaging", "com.htc.sense.mms", "com.oneplus.mms", "com.google.android.apps.messaging"));
        Iterator<ResolveInfo> it = BaseApplication.getContext().getPackageManager().queryIntentActivities(new Intent("android.intent.action.SENDTO", Uri.parse("smsto:")), 1048576).iterator();
        while (true) {
            if (!it.hasNext()) {
                str = "";
                break;
            }
            ResolveInfo next = it.next();
            if (next != null && next.activityInfo != null && unmodifiableList.contains(next.activityInfo.packageName)) {
                str = next.activityInfo.packageName;
                break;
            }
        }
        if (!TextUtils.isEmpty(str)) {
            LogUtil.a(TAG, "getSystemSmsPackageName resolveInfos packageName: ", str);
            return str;
        }
        Iterator it2 = unmodifiableList.iterator();
        while (true) {
            if (!it2.hasNext()) {
                break;
            }
            String str2 = (String) it2.next();
            if (jrg.bJd_(str2) != null) {
                str = str2;
                break;
            }
        }
        LogUtil.a(TAG, "getSystemSmsPackageName matchThirdPart packageName", str);
        return str;
    }

    private List<jje> loadAllAppInfo(List<ResolveInfo> list) {
        ArrayList arrayList = new ArrayList(16);
        ArrayList arrayList2 = new ArrayList(16);
        ArrayList arrayList3 = new ArrayList(16);
        obtainDefaultPushApp(arrayList, arrayList2);
        obtainIntelligentPushApp(arrayList, arrayList2);
        obtainOtherPushApp(list, arrayList, arrayList2, arrayList3);
        Collections.sort(arrayList3, new jje.b());
        ArrayList arrayList4 = new ArrayList(arrayList.size() + arrayList2.size() + arrayList3.size());
        arrayList4.addAll(arrayList);
        arrayList4.addAll(arrayList2);
        arrayList4.addAll(arrayList3);
        arrayList.clear();
        arrayList2.clear();
        arrayList3.clear();
        LogUtil.a(TAG, "loadAllAppInfo size:", Integer.valueOf(arrayList4.size()));
        return arrayList4;
    }

    private void obtainDefaultPushApp(List<jje> list, List<jje> list2) {
        ArrayList arrayList = new ArrayList(16);
        arrayList.addAll(bfg.d);
        for (int i = 0; i < arrayList.size(); i++) {
            String str = (String) arrayList.get(i);
            jje jjeVar = new jje();
            if (hasObtainSingleAppInfo(str, jjeVar)) {
                if (jjeVar.d() == 1) {
                    list.add(jjeVar);
                } else {
                    list2.add(jjeVar);
                }
            }
        }
    }

    private void obtainOtherPushApp(List<ResolveInfo> list, List<jje> list2, List<jje> list3, List<jje> list4) {
        ArrayList arrayList = new ArrayList(16);
        ArrayList arrayList2 = new ArrayList(16);
        for (int i = 0; i < list.size(); i++) {
            String str = list.get(i).activityInfo.packageName;
            if (!bfg.c.contains(str) && !bfg.d.contains(str)) {
                if (arrayList2.contains(str)) {
                    LogUtil.h(TAG, "obtainOtherPushApp contains packageName:", str);
                } else {
                    jje jjeVar = new jje();
                    if (hasObtainSingleAppInfo(str, jjeVar)) {
                        arrayList2.add(str);
                        if (jjeVar.d() == 1) {
                            list2.add(jjeVar);
                        } else if (bfg.f348a.contains(str)) {
                            arrayList.add(jjeVar);
                        } else {
                            list4.add(jjeVar);
                        }
                    }
                }
            }
        }
        arrayList2.clear();
        list3.addAll(nwy.e(arrayList));
    }

    private boolean hasObtainSingleAppInfo(String str, jje jjeVar) {
        PackageInfo bJd_ = jrg.bJd_(str);
        if (bJd_ == null) {
            LogUtil.h(TAG, "hasObtainSingleAppInfo packageInfo is null, packageName:", str);
            return false;
        }
        String c = nwy.c(this.mContext, str);
        if (TextUtils.isEmpty(c) || TextUtils.equals(str, c) || ERROR_CALENDAR_NAME.equals(c)) {
            LogUtil.h(TAG, "hasObtainSingleAppInfo appName is invalid, packageName:", str);
            return false;
        }
        int cRn_ = nwy.cRn_(getPackageManager(), str);
        jjeVar.c(str);
        jjeVar.d(String.valueOf(bJd_.versionCode));
        jjeVar.c(cRn_);
        if ((this.mIsDomestic && TextUtils.equals(str, this.mDefaultSms)) || (!this.mIsDomestic && TextUtils.equals(str, this.mSystemSms))) {
            if (this.mIsSmsPkg) {
                return false;
            }
            this.mIsSmsPkg = true;
            jjeVar.a(this.mContext.getResources().getString(R.string.IDS_short_message));
            jjeVar.bHw_(getResources().getDrawable(R.mipmap.notification_icon_sms));
            LogUtil.a(TAG, "hasObtainSingleAppInfo smsApp style changed, origin name:", c, " packageName:", str);
        } else {
            jjeVar.a(c);
        }
        int d2 = this.mNotificationPushInteractor.d(str);
        LogUtil.a(TAG, "hasObtainSingleAppInfo packageName:", str, " appName:", jjeVar.c(), " authorizeFlag:", Integer.valueOf(d2));
        jjeVar.b(getDefaultAppPushEnable(str, d2, jjeVar));
        return true;
    }

    private void obtainIntelligentPushApp(List<jje> list, List<jje> list2) {
        jje jjeVar = new jje();
        if (!nwx.d().c(this.mContext, jjeVar, this.mNotificationPushInteractor)) {
            LogUtil.h(TAG, "obtainIntelligentPushApp isSupportIntelligent false");
            return;
        }
        int d2 = this.mNotificationPushInteractor.d(bfg.e);
        if (d2 == 1) {
            list.add(nwx.d().e(this.mContext, jjeVar, this.mNotificationPushInteractor));
        } else if (getIsPushEnableIntelligent(bfg.e, d2, jjeVar) == 1) {
            list.add(nwx.d().e(this.mContext, jjeVar, this.mNotificationPushInteractor));
        } else {
            LogUtil.a(TAG, "obtainIntelligentPushApp intelligent add mainAppList");
            list2.add(nwx.d().e(this.mContext, jjeVar, this.mNotificationPushInteractor));
        }
    }

    private int getIsPushEnableIntelligent(String str, int i, jje jjeVar) {
        jjeVar.c(bfg.e);
        if ("true".equals(jqi.a().getSwitchSettingFromLocal("KEY_NOTIFICATION_SETTINGS_FIRST_OPEN_FLAG_ADD", 10001))) {
            LogUtil.h(TAG, "getIsPushEnableIntelligent already set value");
            return i;
        }
        boolean d2 = nwy.d(jjeVar);
        if (i == 1 || !d2) {
            return i;
        }
        jjeVar.b(1);
        this.mNotificationPushInteractor.a(str, 1);
        LogUtil.a("03", 1, TAG, "set authorizeFlag auto!");
        reportStatusToMidware();
        return 1;
    }

    private int getDefaultAppPushEnable(String str, int i, jje jjeVar) {
        if ((!TextUtils.equals(this.mDefaultSms, str) && !bfg.d.contains(str)) || "true".equals(jqi.a().getSwitchSettingFromLocal("KEY_NOTIFICATION_SETTINGS_FIRST_OPEN_FLAG", 10001)) || i == 1) {
            return i;
        }
        jjeVar.b(1);
        this.mNotificationPushInteractor.a(str, 1);
        ReleaseLogUtil.e(TAG_RELEASE, "getIsPushEnable set authorizeFlag auto! ", str);
        return 1;
    }

    protected void showAuthorizeDialog() {
        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(this.mContext);
        builder.e(this.mContext.getString(R.string._2130841911_res_0x7f021137));
        builder.czC_(R.string._2130841325_res_0x7f020eed, new View.OnClickListener() { // from class: com.huawei.ui.device.activity.notification.NotificationBaseActivity.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a(NotificationBaseActivity.TAG, "showAuthorizeDialog closed click");
                NotificationBaseActivity.this.mNotificationPushInteractor.d(0);
                ContentResolver bJc_ = jrg.bJc_(NotificationBaseActivity.this.mContext, NotificationBaseActivity.TAG);
                if (bJc_ != null) {
                    jrg.bJe_(bJc_, false, NotificationBaseActivity.TAG);
                }
                NotificationBaseActivity.this.reportStatusToMidware();
                NotificationBaseActivity.this.createNotificationEnableIntent();
                NotificationBaseActivity.this.mLastCloseTime = SystemClock.elapsedRealtime();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        builder.czz_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: com.huawei.ui.device.activity.notification.NotificationBaseActivity.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a(NotificationBaseActivity.TAG, "showAuthorizeDialog cancel click");
                NotificationBaseActivity.this.mNotificationSwitch.setChecked(true);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        NoTitleCustomAlertDialog e = builder.e();
        e.setCancelable(false);
        if (e.isShowing()) {
            return;
        }
        e.show();
    }

    protected void createNotificationEnableIntent() {
        createNotificationEnableIntent(false);
    }

    protected void createNotificationEnableIntent(boolean z) {
        DeviceCapability e = cvs.e(this.mCurrentDeviceId);
        if (e != null && e.isSupportMessageAlertInfo()) {
            boolean c = jjb.b().c();
            LogUtil.a(TAG, "createNotificationEnableIntent notificationStatus is ", Boolean.valueOf(c), ",isTurnOnSwitch is.", Boolean.valueOf(z));
            Intent intent = new Intent(this, (Class<?>) HandleIntentService.class);
            intent.setAction("com.huawei.health.ACTION_NOTIFICATION_ENABLE_PUSH");
            intent.putExtra("notificationEnablePushType", "notificationEnablePushMainSwitch");
            intent.putExtra("notificationEnableStatus", c);
            intent.putExtra("notificationEnableTurnOnSwitch", z);
            try {
                this.mContext.startService(intent);
                return;
            } catch (IllegalStateException | SecurityException e2) {
                LogUtil.b(TAG, "createNotificationEnableIntent", LogAnonymous.b(e2));
                sqo.z("createNotificationEnableIntent error");
                return;
            }
        }
        LogUtil.h(TAG, "createNotificationEnableIntent currentDevice is not support notification");
    }

    protected void reportStatusToMidware() {
        if (MagicBuild.f13130a && !CommonUtil.y(BaseApplication.getContext())) {
            LogUtil.h(TAG, "reportStatusToMidware isNewHonor oversea phone return");
            return;
        }
        boolean z = this.mNotificationPushInteractor.d(bfg.e) != 0;
        LogUtil.a(TAG, "isAuthorizeEnabled:", Boolean.valueOf(this.mNotificationPushInteractor.b()), " prompt: ", Boolean.valueOf(z));
        if (cvs.e(this.mCurrentDeviceId) == null || !cvs.e(this.mCurrentDeviceId).isSupportMidware()) {
            return;
        }
        jjb.b().b(this.mNotificationPushInteractor.b(), z);
    }

    protected void setNotificationDescription() {
        Context context = this.mContext;
        if (context == null || jrg.bJc_(context, TAG) == null) {
            return;
        }
        String string = this.mContext.getString(R.string.IDS_device_msgnotif_emui_auth_right);
        if (nsn.ae(BaseApplication.getContext())) {
            string = this.mContext.getString(R.string.IDS_pad_device_auth_right);
        }
        HealthTextView healthTextView = this.mNotificationDescription;
        if (healthTextView == null) {
            return;
        }
        healthTextView.setText(string);
    }

    public void updateUi() {
        this.mProgressBar.setVisibility(8);
        this.mNotificationSwitch.setEnabled(true);
        HealthButton healthButton = this.mCompleteButton;
        if (healthButton != null) {
            healthButton.setEnabled(true);
        }
        if (this.mNotificationPushInteractor.b()) {
            LogUtil.a(TAG, "updateUi isAuthorizeEnabled is true");
            this.mNotificationAppListView.setVisibility(0);
            this.mNotificationAppAdapter.b();
        } else {
            LogUtil.a(TAG, "updateUi isAuthorizeEnabled is false");
            this.mNotificationAppAdapter.a();
        }
    }

    private void registerReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.PACKAGE_ADDED");
        intentFilter.addAction("android.intent.action.PACKAGE_REMOVED");
        intentFilter.addDataScheme("package");
        registerReceiver(this.mAppInstalledReceiver, intentFilter);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initReminderSwitch() {
        if (this.mNotificationHeader == null) {
            this.mNotificationHeader = findViewById(android.R.id.content);
        }
        if (this.mIsSupportSmartNotify) {
            initSmartNotifyReminderView();
        } else if (this.mIsSupportNotify) {
            initNotifyReminderView();
        } else {
            LogUtil.h(TAG, "initReminderSwitch else");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initNotifyReminderView() {
        LogUtil.a(TAG, "initNotifyReminderView ENTER");
        this.mNotifyReminderSwitchLayout = (LinearLayout) nsy.cMd_(this.mNotificationHeader, R.id.notify_reminder_switch_layout);
        this.mPhoneTitle = (HealthTextView) nsy.cMd_(this.mNotificationHeader, R.id.phone_status_title);
        this.mPhoneContent = (HealthTextView) nsy.cMd_(this.mNotificationHeader, R.id.phone_status_content);
        this.mPhoneButton = (HealthSwitchButton) nsy.cMd_(this.mNotificationHeader, R.id.phone_status_switch_button);
        jqi.a().getSwitchSetting("switch_silent_notify_using_phone", new IBaseResponseCallback() { // from class: com.huawei.ui.device.activity.notification.NotificationBaseActivity.13
            /* JADX WARN: Type inference failed for: r4v11 */
            /* JADX WARN: Type inference failed for: r4v3 */
            /* JADX WARN: Type inference failed for: r4v4, types: [boolean, int] */
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                ?? r4;
                LogUtil.a(NotificationBaseActivity.TAG, "initNotifyReminderView errorCode:", Integer.valueOf(i));
                if (i != 0) {
                    NotificationBaseActivity.this.getReminderOldSwitch();
                    return;
                }
                if (obj instanceof String) {
                    boolean z = !"0".equals(obj);
                    LogUtil.a(NotificationBaseActivity.TAG, "initNotifyReminderView isNewSwitchEnable:", Boolean.valueOf(z));
                    r4 = z;
                } else {
                    r4 = 1;
                }
                NotificationBaseActivity.this.mReminderSwitchStatus = r4;
                Message obtainMessage = NotificationBaseActivity.this.mHandler.obtainMessage(1);
                obtainMessage.obj = Boolean.valueOf((boolean) r4);
                NotificationBaseActivity.this.mHandler.sendMessage(obtainMessage);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getReminderOldSwitch() {
        jqi.a().getSwitchSetting("lock_screen_reminder_switch", new IBaseResponseCallback() { // from class: com.huawei.ui.device.activity.notification.NotificationBaseActivity.14
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                LogUtil.a(NotificationBaseActivity.TAG, "getReminderOldSwitch errorCode:", Integer.valueOf(i));
                boolean z = i != 0 && nwy.f();
                if (i == 0 && (obj instanceof String)) {
                    boolean equals = "0".equals(obj);
                    z = !equals;
                    NotificationBaseActivity.this.mReminderSwitchStatus = equals ? 1 : 0;
                    LogUtil.a(NotificationBaseActivity.TAG, "getReminderOldSwitch isChecked:", Boolean.valueOf(z));
                }
                Message obtainMessage = NotificationBaseActivity.this.mHandler.obtainMessage(1);
                obtainMessage.obj = Boolean.valueOf(!z);
                NotificationBaseActivity.this.mHandler.sendMessage(obtainMessage);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setNotifyReminderView(Message message) {
        if (this.mIsSupportSmartNotify) {
            setSmartNotifyReminderView(null);
            return;
        }
        Object obj = message.obj;
        if (!(obj instanceof Boolean)) {
            LogUtil.h(TAG, "setNotifyReminderView object not instanceof Boolean");
            return;
        }
        boolean booleanValue = ((Boolean) obj).booleanValue();
        LinearLayout linearLayout = this.mNotifyReminderSwitchLayout;
        if (linearLayout == null) {
            LogUtil.h(TAG, "mNotifyReminderSwitchLayout is null");
            return;
        }
        linearLayout.setVisibility(0);
        setReminderSwitchEnable(this.mNotificationPushInteractor.b());
        this.mPhoneButton.setChecked(booleanValue);
        this.mPhoneButton.setOnCheckedChangeListener(new NotifyReminderSwitchListener(this.mContext, "switch_silent_notify_using_phone"));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0056, code lost:
    
        if (r6 == 1) goto L21;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void setSmartNotifyReminderView(android.os.Message r6) {
        /*
            r5 = this;
            boolean r0 = r5.mIsFirstShowSmartSwitch
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)
            java.lang.String r1 = "setSmartNotifyReminderView mIsFirstShowSmartSwitch is "
            java.lang.Object[] r0 = new java.lang.Object[]{r1, r0}
            java.lang.String r1 = "NotificationBaseActivity"
            com.huawei.hwlogsmodel.LogUtil.a(r1, r0)
            boolean r0 = r5.mIsFirstShowSmartSwitch
            if (r0 != 0) goto L40
            if (r6 != 0) goto L21
            java.lang.String r6 = "setSmartNotifyReminderView message is null"
            java.lang.Object[] r6 = new java.lang.Object[]{r6}
            com.huawei.hwlogsmodel.LogUtil.h(r1, r6)
            return
        L21:
            java.lang.Object r6 = r6.obj
            boolean r0 = r6 instanceof java.lang.Boolean
            if (r0 != 0) goto L31
            java.lang.String r6 = "setSmartNotifyReminderView object not instanceof Boolean"
            java.lang.Object[] r6 = new java.lang.Object[]{r6}
            com.huawei.hwlogsmodel.LogUtil.h(r1, r6)
            return
        L31:
            java.lang.Boolean r6 = (java.lang.Boolean) r6
            boolean r6 = r6.booleanValue()
            com.huawei.ui.commonui.switchbutton.HealthSwitchButton r0 = r5.mHarmonyPhoneButton
            r0.setChecked(r6)
            r5.syncSwitchSetting(r6)
            return
        L40:
            int r6 = r5.mReminderSwitchStatus
            r0 = -1
            r2 = 1
            if (r6 != r0) goto L56
            boolean r6 = defpackage.nwy.f()
            if (r6 == 0) goto L5a
            java.lang.String r6 = "setSmartNotifyReminderView isSupportNotifyReminderSwitchClose"
            java.lang.Object[] r6 = new java.lang.Object[]{r6}
            com.huawei.hwlogsmodel.LogUtil.a(r1, r6)
            goto L59
        L56:
            if (r6 != r2) goto L59
            goto L5a
        L59:
            r2 = 0
        L5a:
            int r6 = r5.mReminderSwitchStatus
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            java.lang.String r0 = " isEnable:"
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r2)
            java.lang.String r4 = "setSmartNotifyReminderView mReminderSwitchStatus:"
            java.lang.Object[] r6 = new java.lang.Object[]{r4, r6, r0, r3}
            com.huawei.hwlogsmodel.LogUtil.a(r1, r6)
            com.huawei.ui.commonui.switchbutton.HealthSwitchButton r6 = r5.mHarmonyPhoneButton
            r6.setChecked(r2)
            r5.showSmartReminderDialog()
            r5.syncSwitchSetting(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.ui.device.activity.notification.NotificationBaseActivity.setSmartNotifyReminderView(android.os.Message):void");
    }

    private void syncSwitchSetting(boolean z) {
        jqi.a().setSwitchSetting("switch_smart_notify_reminder", z ? "1" : "0", new IBaseResponseCallback() { // from class: nwt
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                NotificationBaseActivity.lambda$syncSwitchSetting$0(i, obj);
            }
        });
    }

    public static /* synthetic */ void lambda$syncSwitchSetting$0(int i, Object obj) {
        if (i == 0) {
            NotificationContentProviderUtil.f();
        }
    }

    private void initSmartNotifyReminderView() {
        LinearLayout linearLayout = (LinearLayout) nsy.cMd_(this.mNotificationHeader, R.id.notify_reminder_switch_harmony_layout);
        this.mHarmonyNotifyReminderSwitchLayout = linearLayout;
        if (linearLayout != null) {
            linearLayout.setVisibility(0);
        }
        this.mHarmonyPhoneTitle = (HealthTextView) nsy.cMd_(this.mNotificationHeader, R.id.phone_status_title_harmony);
        this.mHarmonyPhoneContent = (HealthTextView) nsy.cMd_(this.mNotificationHeader, R.id.phone_status_content_harmony);
        this.mHarmonyPhoneButton = (HealthSwitchButton) nsy.cMd_(this.mNotificationHeader, R.id.phone_status_switch_button_harmony);
        HealthTextView healthTextView = (HealthTextView) nsy.cMd_(this.mNotificationHeader, R.id.notify_reminder_switch_harmony_more);
        this.mHarmonyPhoneMore = healthTextView;
        healthTextView.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.device.activity.notification.NotificationBaseActivity.12
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Intent intent = new Intent(NotificationBaseActivity.this.mContext, (Class<?>) NotificationSmartReminderDescriptionActivity.class);
                intent.putExtra("deviceName", NotificationBaseActivity.this.mDeviceName);
                NotificationBaseActivity.this.startActivity(intent);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        setHarmonyReminderSwitchEnable(this.mNotificationPushInteractor.b());
        this.mHarmonyPhoneButton.setOnCheckedChangeListener(new NotifyReminderSwitchListener(this.mContext, "switch_smart_notify_reminder"));
        jqi.a().getSwitchSetting("switch_smart_notify_reminder", new IBaseResponseCallback() { // from class: com.huawei.ui.device.activity.notification.NotificationBaseActivity.2
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                LogUtil.a(NotificationBaseActivity.TAG, "initSmartNotifyReminderView errorCode:", Integer.valueOf(i), " mReminderSwitchStatus:", Integer.valueOf(NotificationBaseActivity.this.mReminderSwitchStatus));
                if (i != 0) {
                    NotificationBaseActivity.this.mIsFirstShowSmartSwitch = true;
                    NotificationBaseActivity.this.initNotifyReminderView();
                } else {
                    if (obj instanceof String) {
                        NotificationBaseActivity.this.mIsFirstShowSmartSwitch = false;
                        boolean z = !"0".equals(obj);
                        LogUtil.a(NotificationBaseActivity.TAG, "initSmartNotifyReminderView isChecked:", Boolean.valueOf(z));
                        Message obtainMessage = NotificationBaseActivity.this.mHandler.obtainMessage(2);
                        obtainMessage.obj = Boolean.valueOf(z);
                        NotificationBaseActivity.this.mHandler.sendMessage(obtainMessage);
                        return;
                    }
                    LogUtil.h(NotificationBaseActivity.TAG, "initSmartNotifyReminderView objectData not instanceof String");
                }
            }
        });
    }

    private void showSmartReminderDialog() {
        String string = getString(R.string._2130846085_res_0x7f022185);
        String string2 = getString(R.string._2130846086_res_0x7f022186);
        SpannableString spannableString = new SpannableString(string);
        SpannableString spannableString2 = new SpannableString(string2);
        spannableString.setSpan(new BulletSpan(20), 0, string.length(), 0);
        spannableString2.setSpan(new BulletSpan(20), 0, string2.length(), 0);
        String string3 = getString(R.string._2130850299_res_0x7f0231fb);
        int indexOf = string.toString().indexOf(string3);
        String string4 = getString(R.string._2130850300_res_0x7f0231fc);
        int indexOf2 = string2.toString().indexOf(string4);
        LogUtil.a(TAG, "showSmartReminderDialog index1:", Integer.valueOf(indexOf), "index2:", Integer.valueOf(indexOf2));
        if (LanguageUtil.m(this.mContext) && indexOf >= 0 && indexOf2 >= 0) {
            spannableString.setSpan(new StyleSpan(1), indexOf, string3.length() + indexOf, 33);
            spannableString2.setSpan(new StyleSpan(1), indexOf2, string4.length() + indexOf2, 33);
        }
        View inflate = LayoutInflater.from(this).inflate(R.layout.notification_dialog_smart_guide, (ViewGroup) null);
        ((HealthTextView) inflate.findViewById(R.id.notification_guide_tips)).setText(TextUtils.concat(spannableString, System.lineSeparator(), spannableString2));
        CustomAlertDialog.Builder builder = new CustomAlertDialog.Builder(this);
        builder.e(R.string._2130846089_res_0x7f022189).cyp_(inflate).cyn_(R.string._2130841794_res_0x7f0210c2, new DialogInterface.OnClickListener() { // from class: com.huawei.ui.device.activity.notification.NotificationBaseActivity.5
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                LogUtil.h(NotificationBaseActivity.TAG, "showSmartReminderDialog click known button");
                ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
            }
        });
        CustomAlertDialog c = builder.c();
        c.setCancelable(false);
        c.show();
    }

    public void setHarmonyReminderSwitchEnable(boolean z) {
        LinearLayout linearLayout = this.mHarmonyNotifyReminderSwitchLayout;
        if (linearLayout == null || linearLayout.getVisibility() != 0) {
            LogUtil.h(TAG, "setHarmonyReminderSwitchEnable mHarmonyNotifyReminderSwitchLayout not visible");
            return;
        }
        if (z) {
            this.mHarmonyPhoneButton.setEnabled(true);
            this.mHarmonyPhoneTitle.setTextColor(this.mContext.getResources().getColor(R.color._2131299236_res_0x7f090ba4));
            this.mHarmonyPhoneContent.setTextColor(this.mContext.getResources().getColor(R.color._2131299236_res_0x7f090ba4));
            this.mHarmonyPhoneMore.setVisibility(0);
            return;
        }
        this.mHarmonyPhoneButton.setEnabled(false);
        this.mHarmonyPhoneTitle.setTextColor(this.mContext.getResources().getColor(R.color._2131297863_res_0x7f090647));
        this.mHarmonyPhoneContent.setTextColor(this.mContext.getResources().getColor(R.color._2131297863_res_0x7f090647));
        this.mHarmonyPhoneMore.setVisibility(8);
    }

    public void setReminderSwitchEnable(boolean z) {
        LinearLayout linearLayout = this.mNotifyReminderSwitchLayout;
        if (linearLayout == null || linearLayout.getVisibility() != 0) {
            LogUtil.h(TAG, "setReminderSwitchEnable mNotifyReminderSwitchLayout not visible");
            return;
        }
        if (z) {
            this.mPhoneButton.setEnabled(true);
            this.mPhoneTitle.setTextColor(this.mContext.getResources().getColor(R.color._2131299236_res_0x7f090ba4));
            this.mPhoneContent.setTextColor(this.mContext.getResources().getColor(R.color._2131299236_res_0x7f090ba4));
        } else {
            this.mPhoneButton.setEnabled(false);
            this.mPhoneTitle.setTextColor(this.mContext.getResources().getColor(R.color._2131297863_res_0x7f090647));
            this.mPhoneContent.setTextColor(this.mContext.getResources().getColor(R.color._2131297863_res_0x7f090647));
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.notification_open_error_tip_text) {
            if (TextUtils.isEmpty(this.mNotificationSettingGuideUrlHost) || TextUtils.isEmpty(this.mNotificationSettingGuideUrlHostOversea)) {
                getGrsNotificationSettingUrl();
            }
            String notificationOpenTipUrl = getNotificationOpenTipUrl();
            LogUtil.a(TAG, "onClick url: ", Uri.parse(notificationOpenTipUrl).getPath());
            Intent intent = new Intent(this.mContext, (Class<?>) WebViewActivity.class);
            intent.putExtra("url", notificationOpenTipUrl);
            intent.putExtra(Constants.JUMP_MODE_KEY, 0);
            startActivity(intent);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    protected String getNotificationOpenTipUrl() {
        String str;
        LogUtil.a(TAG, "getNotificationOpenTipUrl siteId:", Integer.valueOf(CommonUtil.m(BaseApplication.getContext(), LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1009))));
        String languageTag = getLanguageTag();
        if (Utils.o()) {
            str = this.mNotificationSettingGuideUrlHostOversea + OPEN_ERROR_URL_OVERSEA.replace("lang=%s", "lang=" + languageTag);
        } else {
            str = this.mNotificationSettingGuideUrlHostOversea + String.format(Locale.ENGLISH, NOTIFICATION_SWITCH_OPEN_CHINA, getUrlLanguage());
        }
        LogUtil.a(TAG, "getNotificationOpenTipUrl url : ", str);
        return str;
    }

    private String getUrlLanguage() {
        String language = BaseApplication.getContext().getResources().getConfiguration().locale.getLanguage();
        return ("ZH".equalsIgnoreCase(language) || "BO".equalsIgnoreCase(language) || "UG".equalsIgnoreCase(language)) ? "zh-CN" : "en-US";
    }

    protected void getGrsNotificationSettingUrl() {
        this.mThreadPool.execute(new Runnable() { // from class: com.huawei.ui.device.activity.notification.NotificationBaseActivity.1
            @Override // java.lang.Runnable
            public void run() {
                String countryCode = NotificationBaseActivity.this.mGrsManager.getCountryCode();
                NotificationBaseActivity notificationBaseActivity = NotificationBaseActivity.this;
                notificationBaseActivity.mNotificationSettingGuideUrlHost = notificationBaseActivity.mGrsManager.getNoCheckUrl("domainTipsResDbankcdn", countryCode);
                NotificationBaseActivity notificationBaseActivity2 = NotificationBaseActivity.this;
                notificationBaseActivity2.mNotificationSettingGuideUrlHostOversea = notificationBaseActivity2.mGrsManager.getNoCheckUrl("domainTipsResDbankcdnNew", countryCode);
                if (TextUtils.isEmpty(NotificationBaseActivity.this.mNotificationSettingGuideUrlHostOversea)) {
                    LogUtil.h(NotificationBaseActivity.TAG, "Get URL oversea is null");
                    return;
                }
                if (TextUtils.isEmpty(NotificationBaseActivity.this.mNotificationSettingGuideUrlHost)) {
                    LogUtil.h(NotificationBaseActivity.TAG, "Get URL is null");
                    return;
                }
                if (NotificationBaseActivity.this.mNotificationSettingGuideUrlHost.contains(NotificationBaseActivity.LOCAL_URL_SYMBOL)) {
                    LogUtil.a(NotificationBaseActivity.TAG, "The location is domestic");
                    return;
                }
                if (NotificationBaseActivity.this.mNotificationSettingGuideUrlHost.contains(NotificationBaseActivity.OVERSEA_URL_SYMBOL)) {
                    NotificationBaseActivity.this.mNotificationSettingGuideUrlHost = NotificationBaseActivity.this.mNotificationSettingGuideUrlHost + NotificationBaseActivity.DIFFERENCE;
                    return;
                }
                LogUtil.a(NotificationBaseActivity.TAG, "Get URL completed");
            }
        });
    }

    protected String getLanguageTag() {
        Configuration configuration = BaseApplication.getContext().getResources().getConfiguration();
        String str = configuration.locale.getLanguage() + com.huawei.openalliance.ad.constant.Constants.LINK + configuration.locale.getCountry();
        LogUtil.a(TAG, "getLanguageTag languageTag is: ", str);
        return str;
    }

    protected void setNotificationOpenErrorTip() {
        String string = getResources().getString(R.string.IDS_device_msgnotif_auth_method);
        String string2 = getResources().getString(R.string.IDS_device_msgnotif_tip_open_error, string);
        SpannableString spannableString = new SpannableString(string2);
        int length = string2.length();
        int length2 = string.length();
        nsi.cKG_(spannableString, length - length2, spannableString.toString().length(), new ClickableSpan() { // from class: com.huawei.ui.device.activity.notification.NotificationBaseActivity.4
            @Override // android.text.style.ClickableSpan
            public void onClick(View view) {
                NotificationBaseActivity.this.onClick(view);
                ViewClickInstrumentation.clickOnView(view);
            }

            @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
            public void updateDrawState(TextPaint textPaint) {
                if (textPaint != null) {
                    super.updateDrawState(textPaint);
                    textPaint.setColor(NotificationBaseActivity.this.getResources().getColor(R.color._2131299061_res_0x7f090af5));
                    textPaint.setUnderlineText(false);
                }
            }
        });
        this.mNotificationOpenErrorTip.setMovementMethod(LinkMovementMethod.getInstance());
        this.mNotificationOpenErrorTip.setHighlightColor(getResources().getColor(android.R.color.transparent));
        this.mNotificationOpenErrorTip.setText(spannableString);
    }

    protected void showOverSeaGuideDialog() {
        String string;
        if (PermissionUtil.g() && Utils.o() && isShowDialogBoard() && !jrg.b()) {
            NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(this.mContext);
            if (isXiaoMiSeries()) {
                string = this.mContext.getString(R.string._2130847187_res_0x7f0225d3);
            } else {
                string = this.mContext.getString(R.string.IDS_device_msgnotif_permission_dialog_overseas);
            }
            builder.e(string);
            builder.czC_(R.string._2130845370_res_0x7f021eba, new View.OnClickListener() { // from class: nww
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    NotificationBaseActivity.this.m783x3aff74d3(view);
                }
            });
            builder.czz_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: nwv
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    NotificationBaseActivity.lambda$showOverSeaGuideDialog$2(view);
                }
            });
            NoTitleCustomAlertDialog e = builder.e();
            LogUtil.a(TAG, "showOverSeaGuideDialog is create.");
            e.setCancelable(false);
            if (e.isShowing()) {
                return;
            }
            e.show();
        }
    }

    /* renamed from: lambda$showOverSeaGuideDialog$1$com-huawei-ui-device-activity-notification-NotificationBaseActivity, reason: not valid java name */
    public /* synthetic */ void m783x3aff74d3(View view) {
        LogUtil.a(TAG, "showAuthorizeDialog closed click");
        Intent intent = new Intent();
        intent.setFlags(268435456);
        if (isXiaoMiSeries()) {
            intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            intent.setData(Uri.fromParts("package", this.mContext.getPackageName(), null));
        } else {
            intent.setAction("android.settings.APPLICATION_SETTINGS");
        }
        gnm.aPB_(this.mContext, intent);
        ViewClickInstrumentation.clickOnView(view);
    }

    public static /* synthetic */ void lambda$showOverSeaGuideDialog$2(View view) {
        LogUtil.a(TAG, "showAuthorizeDialog cancel click");
        ViewClickInstrumentation.clickOnView(view);
    }

    private boolean isShowDialogBoard() {
        return (iyg.e() || iyg.c() || CommonUtil.bd()) ? false : true;
    }

    private boolean isXiaoMiSeries() {
        String lowerCase = Build.BRAND.toLowerCase(Locale.ENGLISH);
        return lowerCase.contains(XIAOMI_BRAND) || lowerCase.contains(REDMI_BRAND) || lowerCase.contains(POCO_BRAND);
    }

    public void openNotificationAccess() {
        startActivityForResult(new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS"), 100);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 100) {
            showOverSeaGuideDialog();
        }
    }
}
