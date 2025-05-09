package com.huawei.ui.main.stories.messageremind.receiver;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.huawei.health.R;
import com.huawei.hihealth.data.model.RelativeSportData;
import com.huawei.hihealth.motion.HealthOpenSDK;
import com.huawei.hihealth.motion.ICommonReport;
import com.huawei.hihealth.motion.IExecuteResult;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.ble.BleConstants;
import com.huawei.ui.main.stories.nps.npsstate.NpsUserShowController;
import defpackage.dss;
import defpackage.jdh;
import defpackage.kor;
import defpackage.nsj;
import defpackage.nsn;
import defpackage.rip;
import defpackage.riy;
import defpackage.sde;
import defpackage.sqa;
import health.compact.a.NotificationUtil;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.UnitUtil;
import java.lang.ref.WeakReference;
import java.security.SecureRandom;
import java.util.Date;

/* loaded from: classes7.dex */
public class SportReminderReceiver extends BroadcastReceiver {

    /* renamed from: a, reason: collision with root package name */
    private Context f10396a;
    private int b;
    private int d;
    private Intent e;
    private e f = new e(this);
    private c j = new c(this);
    private int i = 0;
    private HealthOpenSDK h = null;
    private Handler c = new Handler() { // from class: com.huawei.ui.main.stories.messageremind.receiver.SportReminderReceiver.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message == null) {
                LogUtil.h("Track_SportReminderReceiver", "message is null.");
                return;
            }
            super.handleMessage(message);
            int i = message.what;
            if (i == 10 || i == 11) {
                SportReminderReceiver.this.a();
            }
        }
    };

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        LogUtil.a("Track_SportReminderReceiver", "onReceive intent ", intent);
        this.f10396a = context.getApplicationContext();
        if (intent != null && "npsNotification".equals(intent.getAction())) {
            LogUtil.a("Track_SportReminderReceiver", "jump to NPS page");
            NpsUserShowController.getInstance(BaseApplication.getContext()).setNpsNotificationBi(2);
            NpsUserShowController.getInstance(BaseApplication.getContext()).showNpsPage(this.f10396a);
        } else if (intent != null && "npsAlarm".equals(intent.getAction())) {
            LogUtil.a("Track_SportReminderReceiver", "go to send nps notification");
            NpsUserShowController.getInstance(BaseApplication.getContext()).sendNpsNotification();
        } else {
            dPw_(intent);
        }
    }

    private void dPw_(Intent intent) {
        String stringExtra = intent.getStringExtra("sportReminderHuid");
        String accountInfo = LoginInit.getInstance(this.f10396a).getAccountInfo(1011);
        LogUtil.a("Track_SportReminderReceiver", "huid ", stringExtra, "currentUserId ", accountInfo);
        if (TextUtils.isEmpty(stringExtra) || TextUtils.isEmpty(accountInfo) || !stringExtra.equals(accountInfo)) {
            return;
        }
        this.e = intent;
        int intExtra = intent.getIntExtra("reminderId", -1);
        LogUtil.a("Track_SportReminderReceiver", "reminderType: ", Integer.valueOf(intExtra), ", reminderTime: ", new Date(intent.getLongExtra("sportReminderTime", 0L)));
        if (intExtra == 1000) {
            LogUtil.a("Track_SportReminderReceiver", "enter get step");
            if (this.h == null) {
                LogUtil.a("Track_SportReminderReceiver", "openSdk == null init");
                this.h = dss.c(this.f10396a).d();
            }
            this.c.sendEmptyMessageDelayed(11, 100L);
            return;
        }
        if (intExtra == 1001 || intExtra == 1002 || intExtra == 1003 || intExtra == 1004 || intExtra == 1005 || intExtra == 1006 || intExtra == 1007) {
            kor.a().b(new d(this, intent, intExtra));
        } else {
            dPx_(intent, intExtra);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dPx_(Intent intent, int i) {
        int i2;
        int i3;
        boolean z = i == 1000;
        if (z && ((i2 = this.b) > (i3 = this.d) || i3 == 0)) {
            LogUtil.h("Track_SportReminderReceiver", "mCurrentStep ", Integer.valueOf(i2), " mCurrentGoal ", Integer.valueOf(this.d));
            return;
        }
        if (sqa.ekw_(intent)) {
            int i4 = z ? 1 : 7;
            long eku_ = sqa.eku_(intent, 5, i4);
            riy.b(i, eku_, i4 * 86400000);
            ReleaseLogUtil.b("Track_SportReminderReceiver", "sendNotification reminderId ", Integer.valueOf(i), " amount ", Integer.valueOf(i4), " timeMillis ", Long.valueOf(eku_));
            return;
        }
        LogUtil.a("Track_SportReminderReceiver", "setNotify enter reminderId ", Integer.valueOf(i));
        int intValue = rip.a().get(Integer.valueOf(i)).intValue();
        jdh.c().a(intValue);
        Notification.Builder xf_ = jdh.c().xf_();
        nsn.cLB_(xf_);
        String c2 = c(i);
        xf_.setContentTitle(a(i));
        xf_.setContentText(c2);
        xf_.setTicker(c2);
        Intent aOv_ = NotificationUtil.aOv_(this.f10396a);
        aOv_.putExtra("isToSportTab", true);
        if (intValue == 20210605) {
            aOv_.putExtra("mLaunchSource", 9);
            aOv_.putExtra(BleConstants.SPORT_TYPE, 257);
            aOv_.putExtra("remind_from_type", 1);
        } else {
            aOv_.putExtra("mLaunchSource", 10);
            aOv_.putExtra("remind_from_type", 2);
        }
        xf_.setContentIntent(PendingIntent.getActivity(this.f10396a, 0, aOv_, 201326592));
        xf_.setAutoCancel(true);
        xf_.setDefaults(1);
        xf_.setOngoing(false);
        xf_.setOnlyAlertOnce(true);
        jdh.c().xh_(intValue, xf_.build());
        LogUtil.a("Track_SportReminderReceiver", "sendNotification, notificationId: ", Integer.valueOf(intValue), ", reminderId: ", Integer.valueOf(i));
        if (i == 1000) {
            SharedPreferenceManager.e(this.f10396a, "goal_not_achieve_module_id", "goal_not_achieve_sp_key", nsj.b(System.currentTimeMillis()), new StorageParams(0));
            b();
        }
    }

    private String a(int i) {
        switch (i) {
            case 1000:
                return this.f10396a.getResources().getString(R.string._2130844753_res_0x7f021c51, UnitUtil.e(this.b, 1, 0), UnitUtil.e(this.d != 0 ? Math.round((float) ((this.b * 100.0f) / (r8 * 1.0d))) : 0, 2, 0));
            case 1001:
            case 1002:
            case 1003:
            case 1004:
            case 1005:
            case 1006:
            case 1007:
                return this.f10396a.getResources().getString(R.string._2130844748_res_0x7f021c4c);
            default:
                return "";
        }
    }

    private String c(int i) {
        switch (i) {
            case 1000:
                return this.f10396a.getResources().getString(R.string._2130844754_res_0x7f021c52);
            case 1001:
            case 1002:
            case 1003:
            case 1004:
            case 1005:
            case 1006:
            case 1007:
                return this.f10396a.getResources().getString(rip.e().get(Integer.valueOf(new SecureRandom().nextInt(4))).intValue());
            default:
                return "";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        LogUtil.a("Track_SportReminderReceiver", "enter registerStepReport ", this.h);
        HealthOpenSDK healthOpenSDK = this.h;
        if (healthOpenSDK != null) {
            healthOpenSDK.b(this.f, this.j);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        LogUtil.a("Track_SportReminderReceiver", "enter unRegisterStepReport ", this.h);
        HealthOpenSDK healthOpenSDK = this.h;
        if (healthOpenSDK != null) {
            healthOpenSDK.d((ICommonReport) this.f);
        }
    }

    static final class e implements ICommonReport {
        private final WeakReference<SportReminderReceiver> e;

        e(SportReminderReceiver sportReminderReceiver) {
            this.e = new WeakReference<>(sportReminderReceiver);
        }

        @Override // com.huawei.hihealth.motion.ICommonReport
        public void report(Bundle bundle) {
            LogUtil.a("Track_SportReminderReceiver", "SportDataCallback enter ");
            SportReminderReceiver sportReminderReceiver = this.e.get();
            if (sportReminderReceiver == null) {
                LogUtil.h("Track_SportReminderReceiver", "sportReminderReceiver == null");
                return;
            }
            if (bundle != null) {
                if (!nsj.b(System.currentTimeMillis()).equals(SharedPreferenceManager.b(sportReminderReceiver.f10396a, "goal_not_achieve_module_id", "goal_not_achieve_sp_key"))) {
                    sportReminderReceiver.b = bundle.getInt("step", -1);
                    sportReminderReceiver.d = bundle.getInt("stepTarget", -1);
                    LogUtil.a("Track_SportReminderReceiver", "report sport step ", Integer.valueOf(sportReminderReceiver.b), " goal ", Integer.valueOf(sportReminderReceiver.d));
                    sportReminderReceiver.dPx_(sportReminderReceiver.e, 1000);
                    return;
                }
                LogUtil.h("Track_SportReminderReceiver", "SportDataCallback report unRegisterStepReport");
                sportReminderReceiver.b();
                return;
            }
            LogUtil.h("Track_SportReminderReceiver", "report sport data fail, bundle is null");
        }
    }

    static class c implements IExecuteResult {
        private final WeakReference<SportReminderReceiver> e;

        c(SportReminderReceiver sportReminderReceiver) {
            this.e = new WeakReference<>(sportReminderReceiver);
        }

        @Override // com.huawei.hihealth.motion.IExecuteResult
        public void onSuccess(Object obj) {
            if (this.e.get() == null) {
                LogUtil.h("Track_SportReminderReceiver", "OpenSdkRegisterListener reader is null");
            } else {
                LogUtil.a("Track_SportReminderReceiver", "onSuccess");
            }
        }

        @Override // com.huawei.hihealth.motion.IExecuteResult
        public void onFailed(Object obj) {
            LogUtil.h("Track_SportReminderReceiver", "onFailed");
            SportReminderReceiver sportReminderReceiver = this.e.get();
            if (sportReminderReceiver != null) {
                sportReminderReceiver.c();
            } else {
                LogUtil.h("Track_SportReminderReceiver", "OpenSdkRegisterListener reader is null");
            }
        }

        @Override // com.huawei.hihealth.motion.IExecuteResult
        public void onServiceException(Object obj) {
            LogUtil.h("Track_SportReminderReceiver", "onServiceException");
        }
    }

    static class d implements IBaseResponseCallback {

        /* renamed from: a, reason: collision with root package name */
        private final Intent f10398a;
        private final int b;
        private final WeakReference<SportReminderReceiver> c;

        d(SportReminderReceiver sportReminderReceiver, Intent intent, int i) {
            this.c = new WeakReference<>(sportReminderReceiver);
            this.f10398a = intent;
            this.b = i;
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            LogUtil.a("Track_SportReminderReceiver", "LastTrackCallback onResponse()");
            SportReminderReceiver sportReminderReceiver = this.c.get();
            if (sportReminderReceiver == null) {
                LogUtil.h("Track_SportReminderReceiver", "LastTrackCallback reader is null");
                return;
            }
            if (obj instanceof MotionPathSimplify) {
                MotionPathSimplify motionPathSimplify = (MotionPathSimplify) obj;
                int requestSportType = motionPathSimplify.requestSportType();
                long requestStartTime = motionPathSimplify.requestStartTime();
                if (requestSportType == 512) {
                    RelativeSportData requestFatherSportItem = motionPathSimplify.requestFatherSportItem();
                    if (requestFatherSportItem == null) {
                        LogUtil.h("Track_SportReminderReceiver", "LastTrackCallback relativeSportData == null");
                        return;
                    }
                    requestStartTime = requestFatherSportItem.getStartTime();
                }
                if (!sde.c(requestStartTime, System.currentTimeMillis())) {
                    sportReminderReceiver.dPx_(this.f10398a, this.b);
                } else {
                    LogUtil.a("Track_SportReminderReceiver", "hadSportToday TRUE");
                }
            }
            if (obj == null) {
                LogUtil.a("Track_SportReminderReceiver", "get last record is null, send notification ");
                sportReminderReceiver.dPx_(this.f10398a, this.b);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        Handler handler = this.c;
        if (handler == null || handler.hasMessages(10)) {
            return;
        }
        int i = this.i + 1;
        this.i = i;
        LogUtil.a("Track_SportReminderReceiver", "retryRegister mRegisterTimes=", Integer.valueOf(i));
        if (this.i <= 3) {
            this.c.sendEmptyMessageDelayed(10, 200L);
        }
    }
}
