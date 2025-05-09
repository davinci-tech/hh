package defpackage;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.CompoundButton;
import com.huawei.haf.router.AppRouter;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.h5pro.core.H5ProLaunchOption;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hms.mlsdk.asr.MLAsrConstants;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.impl.AchieveObserver;
import com.huawei.pluginachievement.manager.db.IAchieveDBMgr;
import com.huawei.pluginachievement.manager.model.KakaConstants;
import com.huawei.pluginachievement.manager.model.ResultCode;
import com.huawei.pluginachievement.manager.model.UserAchieveWrapper;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.switchbutton.HealthSwitchButton;
import health.compact.a.CommonUtil;
import health.compact.a.HiDateUtil;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Locale;

/* loaded from: classes8.dex */
public class mjg implements AchieveObserver {

    /* renamed from: a, reason: collision with root package name */
    private Handler f15019a;
    private meh b;
    private int c;
    private Context d;
    private NoTitleCustomAlertDialog e;
    private gmz f;
    private String h;

    static /* synthetic */ int a(mjg mjgVar) {
        int i = mjgVar.c;
        mjgVar.c = i + 1;
        return i;
    }

    public mjg(Context context, Handler handler) {
        this.d = context;
        this.f15019a = handler;
        meh c = meh.c(context.getApplicationContext());
        this.b = c;
        c.b(this);
        this.h = HiDateUtil.d((String) null);
        this.f = gmz.d();
    }

    public void b() {
        HashMap hashMap = new HashMap(2);
        hashMap.put("timestamp", String.valueOf(System.currentTimeMillis()));
        hashMap.put("timeZone", this.h);
        meh mehVar = this.b;
        if (mehVar != null) {
            mehVar.a(19, hashMap);
        }
    }

    public void c() {
        HashMap hashMap = new HashMap(2);
        hashMap.put("timestamp", String.valueOf(System.currentTimeMillis()));
        hashMap.put("timeZone", this.h);
        meh mehVar = this.b;
        if (mehVar != null) {
            mehVar.a(20, hashMap);
        }
    }

    public void d() {
        HashMap hashMap = new HashMap(2);
        hashMap.put("pageNo", String.valueOf(1));
        hashMap.put(IAchieveDBMgr.PARAM_PAGE_SIZE, String.valueOf(80));
        hashMap.put("timeZone", this.h);
        meh mehVar = this.b;
        if (mehVar != null) {
            mehVar.a(23, hashMap);
        }
    }

    @Override // com.huawei.pluginachievement.impl.AchieveObserver
    public void onDataChanged(int i, UserAchieveWrapper userAchieveWrapper) {
        if (i == -1) {
            if (CommonUtil.bu() || !mle.a(userAchieveWrapper)) {
                return;
            }
            b(1002, (int) 1);
            return;
        }
        b(1002, (int) 0);
        if (userAchieveWrapper == null) {
            return;
        }
        LogUtil.a("KakaActivitySupport", "onDataChanged resultCode=", userAchieveWrapper.getResultCode());
        if (!"0".equals(userAchieveWrapper.getResultCode())) {
            a(userAchieveWrapper.getResultCode(), userAchieveWrapper.getContentType());
            return;
        }
        int contentType = userAchieveWrapper.getContentType();
        if (contentType == 19) {
            b(11200, (int) userAchieveWrapper.getKakaCheckInReturnBody());
        } else if (contentType == 20) {
            b(11201, (int) userAchieveWrapper.getKakaCheckinRecords());
        } else {
            if (contentType != 23) {
                return;
            }
            b(MLAsrConstants.ERR_NO_UNDERSTAND, (int) userAchieveWrapper.getRedeemGiftRecords());
        }
    }

    private <T> void b(int i, T t) {
        Handler handler = this.f15019a;
        if (handler == null) {
            LogUtil.h("KakaActivitySupport", "sendHandlerMessage handler is null!");
            return;
        }
        Message obtain = Message.obtain();
        obtain.what = i;
        obtain.obj = t;
        handler.sendMessage(obtain);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private void a(String str, int i) {
        char c;
        String string;
        LogUtil.h("KakaActivitySupport", "handerErrorMessages errCode = ", str);
        str.hashCode();
        int hashCode = str.hashCode();
        if (hashCode == -1948319192) {
            if (str.equals(ResultCode.CODE_NOT_TODAY_TASK)) {
                c = '\f';
            }
            c = 65535;
        } else if (hashCode != 46730168) {
            switch (hashCode) {
                case -1948319257:
                    if (str.equals("12030003")) {
                        c = 0;
                        break;
                    }
                    c = 65535;
                    break;
                case -1948319256:
                    if (str.equals(ResultCode.CODE_THIRD_PARTY_PLATFORM_ERROR)) {
                        c = 1;
                        break;
                    }
                    c = 65535;
                    break;
                case -1948319255:
                    if (str.equals(ResultCode.CODE_ADD_KAKA_COUNT_MAX)) {
                        c = 2;
                        break;
                    }
                    c = 65535;
                    break;
                default:
                    switch (hashCode) {
                        case -1948319223:
                            if (str.equals(ResultCode.CODE_NON_REAL_TIME_CHECK_IN)) {
                                c = 3;
                                break;
                            }
                            c = 65535;
                            break;
                        case -1948319222:
                            if (str.equals(ResultCode.CODE_GIFT_IS_NOT_EXIST)) {
                                c = 4;
                                break;
                            }
                            c = 65535;
                            break;
                        case -1948319221:
                            if (str.equals(ResultCode.CODE_GIFT_IS_SOLD_OUT)) {
                                c = 5;
                                break;
                            }
                            c = 65535;
                            break;
                        case -1948319220:
                            if (str.equals(ResultCode.CODE_EXCHANGE_OUT_OF_LIMIT)) {
                                c = 6;
                                break;
                            }
                            c = 65535;
                            break;
                        default:
                            switch (hashCode) {
                                case -1948319198:
                                    if (str.equals(ResultCode.CODE_EXCHANGE_OUT_OF_USER_LIMIT)) {
                                        c = 7;
                                        break;
                                    }
                                    c = 65535;
                                    break;
                                case -1948319197:
                                    if (str.equals(ResultCode.CODE_TODAY_IS_CHECKED)) {
                                        c = '\b';
                                        break;
                                    }
                                    c = 65535;
                                    break;
                                case -1948319196:
                                    if (str.equals(ResultCode.CODE_EXCHANGE_IS_STOPED)) {
                                        c = '\t';
                                        break;
                                    }
                                    c = 65535;
                                    break;
                                case -1948319195:
                                    if (str.equals(ResultCode.CODE_EXCHANGE_IS_NOT_BEGIN)) {
                                        c = '\n';
                                        break;
                                    }
                                    c = 65535;
                                    break;
                                case -1948319194:
                                    if (str.equals(ResultCode.CODE_EXCHANGE_OUT_OF_USER_EACH_DAY_LIMIT)) {
                                        c = 11;
                                        break;
                                    }
                                    c = 65535;
                                    break;
                                default:
                                    c = 65535;
                                    break;
                            }
                    }
            }
        } else {
            if (str.equals(ResultCode.CODE_PARAMETER_INVALID)) {
                c = '\r';
            }
            c = 65535;
        }
        switch (c) {
            case 0:
                LogUtil.h("KakaActivitySupport", "Redeem gift is processing.");
                string = null;
                break;
            case 1:
            case '\r':
                if (i != 23 && i != 21) {
                    string = this.d.getString(R.string._2130840884_res_0x7f020d34);
                    break;
                }
                string = null;
                break;
            case 2:
                LogUtil.h("KakaActivitySupport", "add kaka value is out limited");
                string = null;
                break;
            case 3:
                string = this.d.getString(R.string._2130840888_res_0x7f020d38);
                break;
            case 4:
            case '\t':
                string = this.d.getString(R.string._2130840885_res_0x7f020d35);
                break;
            case 5:
                string = this.d.getString(R.string._2130840886_res_0x7f020d36);
                break;
            case 6:
                string = this.d.getString(R.string._2130840990_res_0x7f020d9e);
                break;
            case 7:
            case 11:
                string = this.d.getString(R.string._2130840887_res_0x7f020d37);
                break;
            case '\b':
                LogUtil.h("KakaActivitySupport", "today has checked");
                string = null;
                break;
            case '\n':
                string = this.d.getString(R.string._2130840889_res_0x7f020d39);
                break;
            case '\f':
                string = this.d.getString(R.string._2130840991_res_0x7f020d9f);
                break;
            default:
                LogUtil.h("KakaActivitySupport", "unknow error:", str);
                string = null;
                break;
        }
        if (string != null) {
            b(1000, (int) string);
        }
    }

    public void e() {
        NoTitleCustomAlertDialog noTitleCustomAlertDialog = this.e;
        if (noTitleCustomAlertDialog != null) {
            noTitleCustomAlertDialog.cancel();
            this.e = null;
        }
        this.b.c(this);
        this.d = null;
        Handler handler = this.f15019a;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            this.f15019a = null;
        }
    }

    public void a() {
        Bundle bundle = new Bundle();
        bundle.putString("from", "2");
        AppRouter.b("/HWUserProfileMgr/MyAwardActivity").zF_(bundle).c(this.d);
    }

    public void cih_(Activity activity) {
        if (activity == null || activity.isFinishing()) {
            LogUtil.h("KakaActivitySupport", "showSystemTimeErrorDialog activity is finish");
            return;
        }
        if (this.e == null) {
            final WeakReference weakReference = new WeakReference(activity);
            NoTitleCustomAlertDialog e = new NoTitleCustomAlertDialog.Builder(activity).e(String.format(Locale.ENGLISH, activity.getResources().getString(R.string._2130844222_res_0x7f021a3e), nsn.i(activity))).czC_(R.string._2130841131_res_0x7f020e2b, new View.OnClickListener() { // from class: mjd
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    mjg.cie_(weakReference, view);
                }
            }).e();
            this.e = e;
            e.setCancelable(false);
        }
        if (this.e.isShowing()) {
            return;
        }
        this.e.show();
    }

    static /* synthetic */ void cie_(WeakReference weakReference, View view) {
        Activity activity = (Activity) weakReference.get();
        if (activity == null) {
            LogUtil.a("KakaActivitySupport", "setPositiveButton: activity is null");
            ViewClickInstrumentation.clickOnView(view);
        } else {
            activity.finish();
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    public void c(Context context) {
        if (context == null) {
            LogUtil.h("KakaActivitySupport", "showKakaLotteryPage context is null.");
            return;
        }
        LogUtil.a("KakaActivitySupport", "showKakaLotteryPage enter");
        bzs.e().initH5Pro();
        bzs.e().loadH5ProApp(this.d, KakaConstants.KAKA_H5_PACKAGE_NAME, new H5ProLaunchOption.Builder().addPath("#/lotteryOptimized").setBackgroundColor(Color.rgb(255, 189, 81)));
    }

    public void a(HealthSwitchButton healthSwitchButton) {
        if (!TextUtils.isEmpty(mct.b(this.d, "kaka_checkin_reminder_show_time")) || healthSwitchButton.isChecked()) {
            LogUtil.h("KakaActivitySupport", "showCheckReminderDialog need not to show");
            return;
        }
        b(healthSwitchButton);
        mct.b(this.d, "kaka_checkin_reminder_show_time", "" + System.currentTimeMillis());
    }

    public void c(final HealthSwitchButton healthSwitchButton) {
        if (healthSwitchButton == null) {
            LogUtil.h("KakaActivitySupport", "initReminderSwitch isnull");
            return;
        }
        String c = this.f.c(403);
        LogUtil.a("KakaActivitySupport", "initReminderSwitch status = ", c);
        if (String.valueOf(true).equals(c)) {
            healthSwitchButton.setChecked(true);
        } else {
            healthSwitchButton.setChecked(false);
        }
        healthSwitchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: mjg.1
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (!compoundButton.isPressed()) {
                    ViewClickInstrumentation.clickOnView(compoundButton);
                    return;
                }
                if (z) {
                    LogUtil.a("KakaActivitySupport", "turn on check reminder");
                    mjg.this.e(true);
                    healthSwitchButton.setChecked(true);
                } else {
                    mjg.this.e(healthSwitchButton);
                }
                ViewClickInstrumentation.clickOnView(compoundButton);
            }
        });
    }

    public void b(HealthSwitchButton healthSwitchButton) {
        final WeakReference weakReference = new WeakReference(healthSwitchButton);
        CustomTextAlertDialog a2 = new CustomTextAlertDialog.Builder(this.d).b(this.d.getResources().getString(R.string._2130840998_res_0x7f020da6)).e(this.d.getResources().getString(R.string._2130840999_res_0x7f020da7)).cyU_(R.string._2130841001_res_0x7f020da9, new View.OnClickListener() { // from class: mje
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                mjg.this.cig_(weakReference, view);
            }
        }).cyR_(R.string._2130841000_res_0x7f020da8, new View.OnClickListener() { // from class: mjc
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                mjg.cid_(weakReference, view);
            }
        }).a();
        a2.setCancelable(false);
        a2.show();
        HashMap hashMap = new HashMap(2);
        hashMap.put("type", "1");
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.KAKA_SHOW_REMINDER_DIALOG_1100047.value(), hashMap, 0);
    }

    /* synthetic */ void cig_(WeakReference weakReference, View view) {
        HealthSwitchButton healthSwitchButton = (HealthSwitchButton) weakReference.get();
        if (healthSwitchButton == null) {
            LogUtil.a("KakaActivitySupport", "setPositiveButton: button is null");
            ViewClickInstrumentation.clickOnView(view);
        } else {
            LogUtil.a("KakaActivitySupport", "turn on check reminder");
            e(true);
            healthSwitchButton.setChecked(true);
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    static /* synthetic */ void cid_(WeakReference weakReference, View view) {
        HealthSwitchButton healthSwitchButton = (HealthSwitchButton) weakReference.get();
        if (healthSwitchButton == null) {
            LogUtil.a("KakaActivitySupport", "setNegativeButton: button is null");
            ViewClickInstrumentation.clickOnView(view);
        } else {
            healthSwitchButton.setChecked(false);
            LogUtil.h("KakaActivitySupport", "turn on check reminder cancel");
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    public void e(HealthSwitchButton healthSwitchButton) {
        final WeakReference weakReference = new WeakReference(healthSwitchButton);
        CustomTextAlertDialog a2 = new CustomTextAlertDialog.Builder(this.d).b(this.d.getResources().getString(R.string._2130841003_res_0x7f020dab)).e(this.d.getResources().getString(R.string._2130841004_res_0x7f020dac)).cyU_(R.string._2130841006_res_0x7f020dae, new View.OnClickListener() { // from class: mjf
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                mjg.cic_(weakReference, view);
            }
        }).cyR_(R.string._2130841005_res_0x7f020dad, new View.OnClickListener() { // from class: mjl
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                mjg.this.cif_(weakReference, view);
            }
        }).a();
        a2.setCancelable(false);
        a2.show();
        HashMap hashMap = new HashMap(2);
        hashMap.put("type", "-1");
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.KAKA_SHOW_REMINDER_DIALOG_1100047.value(), hashMap, 0);
    }

    static /* synthetic */ void cic_(WeakReference weakReference, View view) {
        HealthSwitchButton healthSwitchButton = (HealthSwitchButton) weakReference.get();
        if (healthSwitchButton == null) {
            LogUtil.a("KakaActivitySupport", "setPositiveButton: button is null");
            ViewClickInstrumentation.clickOnView(view);
        } else {
            healthSwitchButton.setChecked(true);
            LogUtil.a("KakaActivitySupport", "turn off check reminder cancel");
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    /* synthetic */ void cif_(WeakReference weakReference, View view) {
        HealthSwitchButton healthSwitchButton = (HealthSwitchButton) weakReference.get();
        if (healthSwitchButton == null) {
            LogUtil.a("KakaActivitySupport", "setNegativeButton: button is null");
            ViewClickInstrumentation.clickOnView(view);
        } else {
            LogUtil.a("KakaActivitySupport", "turn off check reminder");
            e(false);
            healthSwitchButton.setChecked(false);
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(final boolean z) {
        int i;
        String str;
        ThreadPoolManager.d().execute(new Runnable() { // from class: mjg.5
            @Override // java.lang.Runnable
            public void run() {
                gmz gmzVar = mjg.this.f;
                boolean z2 = z;
                gmzVar.c(403, z2, String.valueOf(403), new d(mjg.this, z2));
            }
        });
        if (z) {
            mle.c();
            i = R.string._2130841002_res_0x7f020daa;
            str = "1";
        } else {
            mle.a();
            i = R.string._2130841007_res_0x7f020daf;
            str = "-1";
        }
        b(1000, (int) this.d.getString(i));
        HashMap hashMap = new HashMap(2);
        hashMap.put("type", str);
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.KAKA_REMINDER_STATUS_CHANGE_1100048.value(), hashMap, 0);
        LogUtil.a("KakaActivitySupport", "changeReminderStatus isChecked = ", Boolean.valueOf(z));
    }

    static class d implements IBaseResponseCallback {

        /* renamed from: a, reason: collision with root package name */
        private boolean f15020a;
        private WeakReference<mjg> b;

        d(mjg mjgVar, boolean z) {
            this.b = new WeakReference<>(mjgVar);
            this.f15020a = z;
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            mjg mjgVar = this.b.get();
            if (mjgVar == null) {
                LogUtil.h("KakaActivitySupport", "healthKitActivity = null");
                return;
            }
            LogUtil.a("KakaActivitySupport", "MyReminderChangeCallback onResponse errorCode = ", Integer.valueOf(i));
            if (i == 0 || mjgVar.c >= 3) {
                mjgVar.c = 0;
                return;
            }
            gmz.d().c(403, this.f15020a, String.valueOf(403), this);
            mjg.a(mjgVar);
            LogUtil.a("KakaActivitySupport", "retry update reminder status ", Integer.valueOf(mjgVar.c));
        }
    }
}
