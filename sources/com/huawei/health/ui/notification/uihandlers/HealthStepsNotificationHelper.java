package com.huawei.health.ui.notification.uihandlers;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.net.Uri;
import android.os.BadParcelableException;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.widget.RemoteViews;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.common.utils.AppInfoUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.manager.util.UnitUtilExt;
import com.huawei.health.ui.notification.common.IReporter;
import com.huawei.health.ui.notification.uihandlers.HealthStepsNotificationHelper;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.watchface.api.WebViewActivity;
import defpackage.jdh;
import defpackage.jdl;
import defpackage.jed;
import defpackage.nip;
import defpackage.niv;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.CommonUtil;
import health.compact.a.EnvironmentInfo;
import health.compact.a.LanguageUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.NotificationHelper;
import health.compact.a.NotificationUtil;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.SharedPerferenceUtils;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StepNotificationByHardWareUtils;
import health.compact.a.StepsRecord;
import health.compact.a.UnitUtil;

/* loaded from: classes.dex */
public class HealthStepsNotificationHelper extends IReporter {

    /* renamed from: a, reason: collision with root package name */
    private static final Object f3480a = new Object();
    private static volatile HealthStepsNotificationHelper e;
    private Context c;
    private e d;
    private boolean f;
    private b h;
    private boolean j;
    private long k;
    private final boolean n;
    private boolean o;
    private long t = 0;
    private long b = 0;
    private boolean q = false;
    private int r = 12;
    private boolean g = false;
    private StepsRecord s = new StepsRecord();
    private long m = 0;
    private int p = 0;
    private boolean l = true;
    private Handler i = new Handler(Looper.getMainLooper()) { // from class: com.huawei.health.ui.notification.uihandlers.HealthStepsNotificationHelper.5
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what == 100 && (message.obj instanceof Bundle)) {
                Bundle bundle = (Bundle) message.obj;
                HealthStepsNotificationHelper.this.a(bundle.getInt("bundle_key_steps"), bundle.getInt("bundle_key_calorie"), bundle.getInt("bundle_key_step_goal"), bundle.getInt("bundle_key_active_count"));
            }
        }
    };

    private HealthStepsNotificationHelper(Context context) {
        boolean z = false;
        this.c = context;
        if (!"xiaomi".equalsIgnoreCase(Build.MANUFACTURER) && Build.VERSION.SDK_INT >= 31) {
            z = true;
        }
        this.n = z;
    }

    public static HealthStepsNotificationHelper d(Context context) {
        if (e == null) {
            synchronized (f3480a) {
                if (e == null) {
                    e = new HealthStepsNotificationHelper(context);
                }
            }
        }
        return e;
    }

    @Override // com.huawei.health.ui.notification.common.IReporter
    public void onStart(Bundle bundle) {
        super.onStart(bundle);
        if (!CommonUtil.bh()) {
            this.g = true;
        }
        this.l = true;
        this.p = 0;
        SharedPerferenceUtils.e(this.c, true);
        h();
    }

    private void h() {
        this.f = NotificationUtil.j();
        this.o = NotificationUtil.f();
        if (!this.s.c()) {
            LogUtil.h("Step_HealthStepNHlp", "onStart mRecord.isStepDataValidate()");
            b(0, 0, -1, -1);
        } else {
            b(this.s.g, this.s.d, this.s.i, this.s.e);
        }
        e();
        f();
    }

    private void e() {
        this.k = SharedPreferenceManager.b("fitness_step_module_id", "has_enter_hour", 0L);
    }

    private void f() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("ENTER_HOUR_LAYOUT");
        if (this.d == null) {
            this.d = new e();
        }
        BroadcastManagerUtil.bFE_(this.c, this.d, intentFilter);
        if (CommonUtil.bd()) {
            if (this.h == null) {
                this.h = new b();
            }
            Context context = this.c;
            if (context != null) {
                BroadcastManagerUtil.bFA_(context, this.h, new IntentFilter("com.android.systemui.statusbar.visible.change"), CommonUtil.bj() ? "hihonor.android.permission.HW_SIGNATURE_OR_SYSTEM" : WebViewActivity.HW_SIGNATURE_OR_SYSTEM, null);
            }
        }
    }

    @Override // com.huawei.health.ui.notification.common.IReporter
    public void refresh(StepsRecord stepsRecord) {
        synchronized (this) {
            try {
                if (StepNotificationByHardWareUtils.a()) {
                    d(stepsRecord);
                }
            } catch (Exception unused) {
                LogUtil.h("Step_HealthStepNHlp", "stepsNotification refresh exception!!!");
            }
        }
    }

    @Override // com.huawei.health.ui.notification.common.IReporter
    public void onStop() {
        super.onStop();
        c();
        SharedPerferenceUtils.e(this.c, false);
    }

    @Override // com.huawei.health.ui.notification.common.IReporter
    public void languageChanged() {
        this.j = true;
        refresh(this.s);
    }

    private void c() {
        LogUtil.a("Step_HealthStepNHlp", "closeNotification...");
        try {
            if (!CommonUtil.bh()) {
                Context context = this.c;
                if (context instanceof Service) {
                    ((Service) context).stopForeground(true);
                }
            }
        } catch (NumberFormatException e2) {
            LogUtil.b("Step_HealthStepNHlp", "NumberFormatException", e2.getMessage());
        }
        NotificationUtil.a(this.c, 10010);
        j();
        Handler handler = this.i;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
    }

    private void j() {
        Context context;
        b bVar;
        BroadcastManagerUtil.bFK_(this.c, this.d);
        if (!CommonUtil.bd() || (context = this.c) == null || (bVar = this.h) == null) {
            return;
        }
        context.unregisterReceiver(bVar);
        this.h = null;
    }

    private Notification aOh_(int i, int i2, int i3, int i4) {
        String a2 = NotificationUtil.a(this.c, UnitUtilExt.e(this.c, Math.round(i2 / 1000.0f), 1, 0));
        Notification.Builder xf_ = jdh.c().xf_();
        NotificationHelper.aOt_(this.c, xf_);
        jdh.c().xi_(xf_);
        xf_.setWhen(System.currentTimeMillis());
        xf_.setShowWhen(false);
        Intent intent = new Intent("steps_notify_delete");
        intent.setPackage(this.c.getPackageName());
        xf_.setDeleteIntent(PendingIntent.getBroadcast(this.c, 10010, intent, 201326592));
        xf_.setPriority(0);
        xf_.setOngoing(true);
        aOp_(i, i3, a2, xf_, i4);
        xf_.setContentIntent(NotificationUtil.aOx_(this.c));
        return xf_.build();
    }

    private Notification aOe_(int i, int i2, int i3, int i4) {
        Notification.Builder xf_ = jdh.c().xf_();
        NotificationHelper.aOt_(this.c, xf_);
        jdh.c().xi_(xf_);
        xf_.setWhen(System.currentTimeMillis());
        xf_.setShowWhen(false);
        xf_.setSmallIcon(R.drawable.healthlogo_ic_notification);
        Intent intent = new Intent("steps_notify_delete");
        intent.setPackage(this.c.getPackageName());
        xf_.setDeleteIntent(PendingIntent.getBroadcast(this.c, 10010, intent, 201326592));
        xf_.setPriority(0);
        xf_.setOngoing(true);
        int b2 = b(i4);
        if (TextUtils.isEmpty(a(i, i3, b2))) {
            b2 = -1;
        }
        xf_.setContentTitle(a(i, i2));
        xf_.setContentIntent(NotificationUtil.aOx_(this.c));
        xf_.setContentText(a(i, i3, b2));
        aOf_(i, i3, xf_, b2);
        return xf_.build();
    }

    private void aOf_(int i, int i2, Notification.Builder builder, int i3) {
        if (i3 == -1) {
            builder.setProgress(100, b(i, i2), false);
        } else {
            builder.addAction(aOi_(i3));
        }
        if (CommonUtil.bm()) {
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putInt("notification.live.operation", this.p);
        bundle.putString("notification.live.event", "Other");
        bundle.putInt("notification.live.type", 1);
        builder.addExtras(bundle);
        if (this.p == 0) {
            ReleaseLogUtil.b("Step_HealthStepNHlp", "create LiveNotification firstly");
        }
        this.p = 1;
    }

    private Notification.Action aOi_(int i) {
        int i2;
        PendingIntent aOk_;
        CharSequence e2;
        if (i == 1) {
            aOk_ = aOj_(this.c);
            e2 = e(R.string.accessibility_stand_detail_view);
            i2 = R.drawable._2131430051_res_0x7f0b0aa3;
        } else {
            i2 = LanguageUtil.bc(this.c) ? R.drawable._2131430039_res_0x7f0b0a97 : R.drawable._2131430038_res_0x7f0b0a96;
            aOk_ = aOk_(this.c);
            e2 = e(R.string.accessibility_start_walk);
        }
        return new Notification.Action(i2, e2, aOk_);
    }

    private CharSequence e(int i) {
        Context context = this.c;
        if (context == null) {
            ReleaseLogUtil.a("Step_HealthStepNHlp", "getContentDescription mContext is null");
            return "";
        }
        Resources resources = context.getResources();
        if (resources == null) {
            ReleaseLogUtil.a("Step_HealthStepNHlp", "getContentDescription resources is null");
            return "";
        }
        try {
            return resources.getString(i);
        } catch (Resources.NotFoundException e2) {
            ReleaseLogUtil.c("Step_HealthStepNHlp", "getContentDescription exception ", ExceptionUtils.d(e2));
            return "";
        }
    }

    private int b(int i, int i2) {
        int round = (int) Math.round((i / i2) * 100.0d);
        if (round == 100 && i < i2) {
            return 99;
        }
        if (round > 100) {
            return 100;
        }
        return round;
    }

    private PendingIntent aOj_(Context context) {
        if (context == null) {
            LogUtil.h("Step_HealthStepNHlp", "getStartAppIntent() context is null");
            return null;
        }
        Intent intent = new Intent(new Intent(CommonConstant.ACTION.HWID_SCHEME_URL, Uri.parse("huaweischeme://healthapp/router/stepCard/ActiveHours?&fromType=1")));
        intent.setPackage(context.getPackageName());
        return aOl_(context, intent);
    }

    private PendingIntent aOk_(Context context) {
        if (context == null) {
            LogUtil.h("Step_HealthStepNHlp", "getStartAppIntent() context is null");
            return null;
        }
        Intent intent = new Intent(CommonConstant.ACTION.HWID_SCHEME_URL, Uri.parse("huaweischeme://healthapp/track?sportType=1&targetType=-1&targetValue=-1&fromType=1"));
        intent.setPackage(context.getPackageName());
        intent.putExtra("mLaunchSource", 12);
        return aOl_(context, intent);
    }

    private static PendingIntent aOl_(Context context, Intent intent) {
        try {
            return PendingIntent.getActivity(context, 0, intent, 201326592);
        } catch (Exception e2) {
            ReleaseLogUtil.c("Step_HealthStepNHlp", "getPendingSafeIntent exception:", ExceptionUtils.d(e2));
            return null;
        }
    }

    private String a(int i, int i2) {
        String b2 = NotificationUtil.b(this.c, String.valueOf(i));
        Context context = this.c;
        return this.c.getResources().getString(R.string.IDS_plugindameon_hw_show_notification_info, b2, NotificationUtil.a(context, UnitUtilExt.e(context, Math.round(i2 / 1000.0f), 1, 0)));
    }

    private String a(int i, int i2, int i3) {
        int max = Math.max(i, 0);
        if (i3 == -1) {
            return this.c.getResources().getString(R.string.IDS_plugindameon_step_ratio, jed.b(b(max, i2), 2, 0));
        }
        if (i3 == 1) {
            return this.c.getResources().getString(R.string.IDS_plugindameon_remind_stand);
        }
        if (i3 != 2 || max >= i2) {
            return "";
        }
        int i4 = i2 - max;
        return this.c.getResources().getQuantityString(R.plurals.IDS_plugindameon_step_goal, i4, jed.b(i4, 1, 0));
    }

    private void aOp_(int i, int i2, String str, Notification.Builder builder, int i3) {
        String b2 = NotificationUtil.b(this.c, String.valueOf(i));
        int b3 = b(i3);
        if (b3 == -1) {
            builder.setContentTitle(b2).setContentText(str);
            return;
        }
        if (b3 == 1) {
            builder.setContentTitle(b2 + " " + str).setContentText(this.c.getResources().getString(R.string.IDS_plugindameon_remind_stand));
            return;
        }
        if (b3 != 2) {
            return;
        }
        if (i < i2) {
            int i4 = i2 - i;
            builder.setContentTitle(b2 + " " + str).setContentText(this.c.getResources().getQuantityString(R.plurals.IDS_plugindameon_step_goal, i4, UnitUtil.e(i4, 1, 0)));
            return;
        }
        builder.setContentTitle(b2).setContentText(str);
    }

    private Notification aOg_(int i, int i2, int i3, int i4) {
        Notification.Builder xf_ = jdh.c().xf_();
        jdh.c().xi_(xf_);
        xf_.setSmallIcon(R.drawable.healthlogo_ic_notification);
        xf_.setWhen(System.currentTimeMillis());
        xf_.setShowWhen(false);
        xf_.setContentIntent(NotificationUtil.aOx_(this.c));
        Intent intent = new Intent("steps_notify_delete");
        intent.setPackage(this.c.getPackageName());
        xf_.setDeleteIntent(PendingIntent.getBroadcast(this.c, 10010, intent, 201326592));
        xf_.setPriority(0);
        xf_.setOngoing(true);
        xf_.setGroup("Step_HealthStepNHlp");
        RemoteViews aOm_ = aOm_(i, i2, i3, i4);
        Notification build = xf_.build();
        build.contentView = aOm_;
        return build;
    }

    private RemoteViews aOm_(int i, int i2, int i3, int i4) {
        RemoteViews remoteViews;
        if (NotificationUtil.c()) {
            remoteViews = new RemoteViews(this.c.getPackageName(), this.n ? R.layout.step_notify_3_2_fonts_hm30 : R.layout.step_notify_3_2_fonts);
        } else if (NotificationUtil.b()) {
            remoteViews = new RemoteViews(this.c.getPackageName(), this.n ? R.layout.step_notify_1_75_fonts_hm30 : R.layout.step_notify_1_75_fonts);
        } else if (NotificationUtil.e()) {
            remoteViews = new RemoteViews(this.c.getPackageName(), this.n ? R.layout.step_notify_1_45_fonts_hm30 : R.layout.step_notify_1_45_fonts);
        } else {
            remoteViews = new RemoteViews(this.c.getPackageName(), this.n ? R.layout.step_notify_hm30 : R.layout.step_notify);
        }
        remoteViews.setImageViewResource(R.id.icon, R.drawable.healthlogo_ic_notification);
        String d = NotificationUtil.d(this.c, i);
        String e2 = NotificationUtil.e(this.c, i2);
        remoteViews.setTextViewText(R.id.app_name_text, AppInfoUtils.b());
        remoteViews.setTextViewText(R.id.textStep, d);
        remoteViews.setTextViewText(R.id.textStepUnit, this.c.getResources().getString(R.string.IDS_plugindameon_hw_phonecounter_widget_step_unit));
        remoteViews.setTextViewText(R.id.textKcal, e2);
        remoteViews.setTextViewText(R.id.textKcalUnit, this.c.getResources().getString(R.string.IDS_plugindameon_hw_phonecounter_widget_kalo_unit));
        aOo_(i, i3, remoteViews, i4);
        return remoteViews;
    }

    private void aOo_(int i, int i2, RemoteViews remoteViews, int i3) {
        int b2 = b(i3);
        if (b2 == -1) {
            aOq_(i, i2, remoteViews);
        } else if (b2 == 1) {
            aOr_(remoteViews);
        } else {
            if (b2 != 2) {
                return;
            }
            aOn_(i, i2, remoteViews);
        }
    }

    private void aOr_(RemoteViews remoteViews) {
        remoteViews.setImageViewResource(R.id.iv_start_step, R.drawable._2131430051_res_0x7f0b0aa3);
        remoteViews.setViewVisibility(R.id.iv_start_step, 0);
        remoteViews.setViewVisibility(R.id.tv_target, 0);
        remoteViews.setViewVisibility(R.id.rl_progress_container, 8);
        remoteViews.setTextViewText(R.id.tv_target, this.c.getResources().getString(R.string.IDS_plugindameon_remind_stand));
        remoteViews.setOnClickPendingIntent(R.id.iv_start_step, aOj_(this.c));
        remoteViews.setContentDescription(R.id.iv_start_step, e(R.string.accessibility_stand_detail_view));
    }

    private void aOn_(int i, int i2, RemoteViews remoteViews) {
        if ((i2 == -1 || i < i2) && this.f) {
            if (LanguageUtil.bc(this.c)) {
                remoteViews.setImageViewResource(R.id.iv_start_step, R.drawable._2131430039_res_0x7f0b0a97);
            } else {
                remoteViews.setImageViewResource(R.id.iv_start_step, R.drawable._2131430038_res_0x7f0b0a96);
            }
            aOs_(i, i2, remoteViews);
            remoteViews.setOnClickPendingIntent(R.id.iv_start_step, aOk_(this.c));
            remoteViews.setContentDescription(R.id.iv_start_step, e(R.string.accessibility_start_walk));
            return;
        }
        aOq_(i, i2, remoteViews);
    }

    private void aOq_(int i, int i2, RemoteViews remoteViews) {
        remoteViews.setViewVisibility(R.id.iv_start_step, 8);
        remoteViews.setViewVisibility(R.id.tv_target, 8);
        remoteViews.setViewVisibility(R.id.rl_progress_container, 0);
        if (i2 != -1) {
            int b2 = b(i, i2);
            remoteViews.setTextViewText(R.id.right_icon_text, NotificationUtil.c(this.c, b2));
            remoteViews.setImageViewResource(R.id.right_icon, NotificationUtil.b(b2));
        }
    }

    private void aOs_(int i, int i2, RemoteViews remoteViews) {
        remoteViews.setViewVisibility(R.id.iv_start_step, 0);
        remoteViews.setViewVisibility(R.id.tv_target, 0);
        remoteViews.setViewVisibility(R.id.rl_progress_container, 8);
        int max = Math.max(i2 - i, 0);
        remoteViews.setTextViewText(R.id.tv_target, this.c.getResources().getQuantityString(R.plurals.IDS_plugindameon_step_goal, max, UnitUtil.e(max, 1, 0)));
    }

    public void b(int i, int i2, int i3, int i4) {
        int max = Math.max(i, 0);
        if (!CommonUtil.bd() || this.l) {
            long b2 = LogUtil.b(2000, this.m);
            if (b2 != -1) {
                LogUtil.a("Step_HealthStepNHlp", "updateHealthNotification: steps = ", LogAnonymous.b(i));
                this.m = b2;
            }
            if (d()) {
                g(max, i2, i3, i4);
            } else {
                a(max, i2, i3, i4);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, int i2, int i3, int i4) {
        Notification aOe_;
        if (EnvironmentInfo.m() || CommonUtil.bm()) {
            aOe_ = aOe_(i, i2, i3, i4);
        } else if (CommonUtil.ck() || CommonUtil.bf()) {
            aOe_ = aOg_(i, i2, i3, i4);
        } else {
            aOe_ = aOh_(i, i2, i3, i4);
        }
        try {
            if (this.g) {
                this.g = false;
                LogUtil.a("Step_HealthStepNHlp", "startNotification...");
                ((Service) this.c).startForeground(10010, aOe_);
                return;
            }
        } catch (IllegalStateException e2) {
            ReleaseLogUtil.a("Step_HealthStepNHlp", "IllegalStateException exception", LogAnonymous.b((Throwable) e2));
        } catch (NumberFormatException e3) {
            LogUtil.b("Step_HealthStepNHlp", "numberFormatException", e3.getMessage());
        }
        try {
            jdh.c().xh_(10010, aOe_);
        } catch (SecurityException e4) {
            LogUtil.h("Step_HealthStepNHlp", "createCommonNotification SecurityException", LogAnonymous.b((Throwable) e4));
        }
    }

    public void d(StepsRecord stepsRecord) {
        if (stepsRecord == null) {
            return;
        }
        if (!stepsRecord.equals(this.s) || this.j) {
            this.j = false;
            this.s.b(stepsRecord);
            b(stepsRecord.g, stepsRecord.d, stepsRecord.i, stepsRecord.e);
        }
    }

    public void a(boolean z) {
        this.f = z;
    }

    public void d(boolean z) {
        this.o = z;
    }

    private int b(int i) {
        if (d() && i != -1 && !jdl.d(this.b, System.currentTimeMillis())) {
            if (this.r == 0) {
                this.r = 12;
            }
            int i2 = (i * 100) / this.r;
            this.q = i2 < 60 && !jdl.d(this.k, System.currentTimeMillis());
            LogUtil.a("Step_HealthStepNHlp", "getShowNotificationType activeCount is ", Integer.valueOf(i), " completedPercent is ", Integer.valueOf(i2), " mStandGoalValue is ", Integer.valueOf(this.r), " mNeedShowActiveStyle is ", Boolean.valueOf(this.q));
            this.b = System.currentTimeMillis();
        }
        if (d() && this.q) {
            return 1;
        }
        return this.f ? 2 : -1;
    }

    private boolean d() {
        boolean a2 = niv.a(this.c);
        boolean a3 = SharedPreferenceManager.a("fitness_step_module_id", "active_hours_set_alert_toggle", true);
        LogUtil.c("Step_HealthStepNHlp", "isShowStandUpNotifyByBase mIsShowStandUpNotify ", Boolean.valueOf(this.o), " mIsShowNewStepNotify ", Boolean.valueOf(this.f), " cardType ", Boolean.valueOf(a2), " isStandUpRemindOpen ", Boolean.valueOf(a3), " mNeedShowActiveStyle ", Boolean.valueOf(this.q));
        return !a2 && a3 && this.o;
    }

    class b extends BroadcastReceiver {
        private b() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (context == null || intent == null) {
                LogUtil.b("Step_HealthStepNHlp", "context == null || intent == null");
                return;
            }
            LogUtil.a("Step_HealthStepNHlp", "CoverStateBroadcastReceiver received ");
            if ("com.android.systemui.statusbar.visible.change".equals(intent.getAction())) {
                try {
                    String stringExtra = intent.getStringExtra("visible");
                    ReleaseLogUtil.b("Step_HealthStepNHlp", "visible ", stringExtra);
                    HealthStepsNotificationHelper.this.l = "true".equals(stringExtra);
                    if (HealthStepsNotificationHelper.this.l) {
                        HealthStepsNotificationHelper.this.a();
                        if (!HealthStepsNotificationHelper.this.s.c()) {
                            HealthStepsNotificationHelper.this.b(0, 0, -1, -1);
                        } else {
                            HealthStepsNotificationHelper healthStepsNotificationHelper = HealthStepsNotificationHelper.this;
                            healthStepsNotificationHelper.b(healthStepsNotificationHelper.s.g, HealthStepsNotificationHelper.this.s.d, HealthStepsNotificationHelper.this.s.i, HealthStepsNotificationHelper.this.s.e);
                        }
                    }
                } catch (BadParcelableException e) {
                    LogUtil.b("Step_HealthStepNHlp", "BadParcelableException getBooleanExtra:", LogAnonymous.b((Throwable) e));
                }
            }
        }
    }

    class e extends BroadcastReceiver {
        private e() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (context == null || intent == null) {
                LogUtil.b("Step_HealthStepNHlp", "context == null || intent == null");
                return;
            }
            LogUtil.a("Step_HealthStepNHlp", "DynamicBroadcastReceiver receive enterHourLayout");
            if ("ENTER_HOUR_LAYOUT".equals(intent.getAction()) && NotificationUtil.f()) {
                SharedPreferenceManager.e("fitness_step_module_id", "has_enter_hour", System.currentTimeMillis());
                HealthStepsNotificationHelper.this.k = System.currentTimeMillis();
                HealthStepsNotificationHelper.this.q = false;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        ThreadPoolManager.d().execute(new Runnable() { // from class: glp
            @Override // java.lang.Runnable
            public final void run() {
                HealthStepsNotificationHelper.this.b();
            }
        });
    }

    public /* synthetic */ void b() {
        if (!EnvironmentInfo.m() || jdh.c(10010)) {
            return;
        }
        this.p = 0;
        ReleaseLogUtil.b("Step_HealthStepNHlp", "create mLiveOperation: ", 0);
    }

    private void g(final int i, final int i2, final int i3, final int i4) {
        if (jdl.d(this.t, System.currentTimeMillis())) {
            LogUtil.c("Step_HealthStepNHlp", "requestTodayActiveHourGoal is same day");
            a(i, i2, i3, i4);
        } else {
            nip.d("900200009", new IBaseResponseCallback() { // from class: com.huawei.health.ui.notification.uihandlers.HealthStepsNotificationHelper.4
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i5, Object obj) {
                    if (obj instanceof Integer) {
                        HealthStepsNotificationHelper.this.r = ((Integer) obj).intValue();
                    } else {
                        LogUtil.h("Step_HealthStepNHlp", "requestTodayActiveHourGoal objData is not instanceof ActiveGoalModel");
                    }
                    HealthStepsNotificationHelper.this.t = System.currentTimeMillis();
                    LogUtil.a("Step_HealthStepNHlp", "requestTodayActiveHourGoal mStandGoalValue is ", Integer.valueOf(HealthStepsNotificationHelper.this.r));
                    Bundle bundle = new Bundle();
                    bundle.putInt("bundle_key_steps", i);
                    bundle.putInt("bundle_key_calorie", i2);
                    bundle.putInt("bundle_key_step_goal", i3);
                    bundle.putInt("bundle_key_active_count", i4);
                    Message obtain = Message.obtain();
                    obtain.obj = bundle;
                    obtain.what = 100;
                    HealthStepsNotificationHelper.this.i.sendMessage(obtain);
                }
            });
        }
    }
}
