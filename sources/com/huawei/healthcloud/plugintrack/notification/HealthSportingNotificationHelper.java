package com.huawei.healthcloud.plugintrack.notification;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.graphics.drawable.Icon;
import android.os.BadParcelableException;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.widget.RemoteViews;
import com.huawei.android.fsm.HwFoldScreenManagerEx;
import com.huawei.haf.common.utils.AppInfoUtils;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.device.api.IndoorEquipManagerApi;
import com.huawei.health.sportservice.SportDataOutputApi;
import com.huawei.health.sportservice.SportLaunchParams;
import com.huawei.health.sportservice.SportLifecycle;
import com.huawei.health.sportservice.constants.SportParamsType;
import com.huawei.healthcloud.plugintrack.manager.inteface.ISportDataFragmentListener;
import com.huawei.healthcloud.plugintrack.notification.HealthSportingNotificationHelper;
import com.huawei.healthcloud.plugintrack.notification.HwScreenFoldDisplayMode;
import com.huawei.healthcloud.plugintrack.ui.activity.TrackMainMapActivity;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.ble.BleConstants;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.watchface.api.WebViewActivity;
import defpackage.gso;
import defpackage.gtx;
import defpackage.gvv;
import defpackage.gwg;
import defpackage.gxf;
import defpackage.ixx;
import defpackage.jdh;
import defpackage.kwx;
import defpackage.nsf;
import defpackage.nsn;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.CommonUtil;
import health.compact.a.EnvironmentInfo;
import health.compact.a.LanguageUtil;
import health.compact.a.LocalBroadcast;
import health.compact.a.LogAnonymous;
import health.compact.a.Services;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.HashMap;

/* loaded from: classes4.dex */
public class HealthSportingNotificationHelper implements ISportDataFragmentListener {
    private static Bundle d;
    private Context c;
    private boolean f;
    private boolean h;
    private boolean j;
    private boolean k;
    private boolean m;
    private boolean n;
    private final boolean p;
    private boolean q;
    private volatile HwScreenFoldDisplayMode s;
    private NotificationSportControlReceiver w;
    private int y;
    private volatile boolean o = false;
    private String g = gvv.e(BaseApplication.getContext());
    private e e = new e();
    private boolean i = PermissionUtil.g();
    private boolean t = true;
    private boolean l = false;
    private int r = 0;

    /* renamed from: a, reason: collision with root package name */
    private Handler f3530a = new Handler(Looper.getMainLooper()) { // from class: com.huawei.healthcloud.plugintrack.notification.HealthSportingNotificationHelper.4
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message != null && message.what == 1) {
                if (!(message.obj instanceof Bundle)) {
                    LogUtil.h("Track_HealthSportingNotificationHelper", "Bundle is null");
                    return;
                }
                Bundle bundle = (Bundle) message.obj;
                if (!HealthSportingNotificationHelper.this.k) {
                    LogUtil.h("Track_HealthSportingNotificationHelper", "updateSportViewFragment bundle null");
                    return;
                }
                if (!bundle.containsKey("bundle_key_service") || HealthSportingNotificationHelper.d == null) {
                    Bundle unused = HealthSportingNotificationHelper.d = bundle;
                }
                HealthSportingNotificationHelper.this.y = bundle.getInt(BleConstants.SPORT_TYPE);
                HealthSportingNotificationHelper.this.aWs_(HealthSportingNotificationHelper.d);
            }
        }
    };
    private final c b = new c();

    public HealthSportingNotificationHelper(Context context, int i, boolean z, boolean z2, boolean z3) {
        this.y = 0;
        this.m = false;
        this.f = false;
        if (context == null) {
            throw new RuntimeException("HealthSportingNotificationHelper invalid params in constructor");
        }
        this.c = context;
        this.y = i;
        this.k = z;
        this.q = CommonUtil.ck();
        this.j = z2;
        this.f = !z2;
        this.n = v();
        this.h = CommonUtil.bh();
        this.p = Build.VERSION.SDK_INT >= 31;
        this.m = CommonUtil.bs();
        b(z3);
    }

    public boolean e() {
        return this.f;
    }

    public void a(boolean z) {
        this.f = z;
    }

    public boolean b() {
        return this.l;
    }

    public void d(boolean z) {
        this.l = z;
    }

    private Notification aVT_(RemoteViews remoteViews) {
        Notification.Builder xf_ = jdh.c().xf_();
        jdh.c().xi_(xf_);
        xf_.setSmallIcon(R.drawable.healthlogo_ic_notification).setWhen(System.currentTimeMillis()).setContentIntent(aVS_()).setOngoing(true);
        if (nsn.p()) {
            xf_.setCustomBigContentView(remoteViews);
        } else {
            xf_.setContent(remoteViews);
        }
        if (this.j && !e()) {
            xf_.setCategory("service");
        } else {
            xf_.setShowWhen(false).setPriority(0);
            xf_.setGroup("Track_HealthSportingNotificationHelper");
        }
        return xf_.build();
    }

    @Override // com.huawei.healthcloud.plugintrack.manager.inteface.ISportDataFragmentListener
    public void updateSportViewFragment(Bundle bundle) {
        Message obtain = Message.obtain();
        obtain.what = 1;
        obtain.obj = bundle;
        this.f3530a.sendMessage(obtain);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void aWs_(Bundle bundle) {
        if (d == null) {
            LogUtil.h("Track_HealthSportingNotificationHelper", "updateHealthNotification sBundle is null");
            return;
        }
        if (this.c == null) {
            LogUtil.h("Track_HealthSportingNotificationHelper", "updateHealthNotification mContext is null");
            return;
        }
        if (bundle == null) {
            LogUtil.b("Track_HealthSportingNotificationHelper", "updateHealthNotification(), sportData == null");
            return;
        }
        String string = bundle.getString("duration");
        String string2 = bundle.getString("distance");
        Resources resources = this.c.getResources();
        if (!this.h || this.t) {
            aWt_(bundle, string, string2, resources);
        }
        if (this.n) {
            if (HwFoldScreenManagerEx.getDisplayMode() == 3) {
                aWl_(string, string2, resources);
                if (this.m) {
                    aWt_(bundle, string, string2, resources);
                }
            }
            if (this.s == null) {
                this.s = new HwScreenFoldDisplayMode(this.b);
                HwFoldScreenManagerEx.registerFoldDisplayMode(this.s);
            }
        }
    }

    private void aWt_(Bundle bundle, String str, String str2, Resources resources) {
        String string = bundle.getString("speed");
        String string2 = bundle.getString("pace");
        String string3 = bundle.getString("calorie");
        String string4 = bundle.getString(IndoorEquipManagerApi.KEY_HEART_RATE);
        LogUtil.a("Track_HealthSportingNotificationHelper", "updateHealthNotification(), totalTime = ", str, ", totalDistance = ", str2, ", speed = ", string, ", pace = ", string2, ", calorie = ", string3, ", heartRate = ", string4);
        if (EnvironmentInfo.m() || CommonUtil.bm()) {
            int i = this.y;
            if (i == 283) {
                a(aWa_(bundle), aVZ_(str, string3, string4, bundle));
                return;
            } else if (i == 265 && b()) {
                a(a(string3), c(str, string4));
                return;
            } else {
                a(g(str2), d(str, string3, string4, string, string2));
                return;
            }
        }
        RemoteViews aVV_ = aVV_();
        aVV_.setImageViewResource(R.id.icon, R.drawable.healthlogo_ic_notification);
        aVV_.setTextViewText(R.id.app_name_text, AppInfoUtils.b());
        if (this.y == 265 && this.l) {
            aWi_(str, string3, aVV_);
            aWj_(string4, aVV_);
        } else {
            aWh_(str, str2, aVV_);
            aWn_(string, string2, aVV_, resources);
            aWq_(aVV_, resources, bundle);
            aWe_(aVV_, resources, bundle);
            aWg_(string3, string4, aVV_);
        }
        aWk_(aVV_);
        jdh.c().xh_(10012, aVT_(aVV_));
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002f  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x003c  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0040  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void aWj_(java.lang.String r5, android.widget.RemoteViews r6) {
        /*
            r4 = this;
            android.content.Context r0 = r4.c
            android.content.res.Resources r0 = r0.getResources()
            r1 = 0
            boolean r2 = r4.m(r5)     // Catch: java.lang.NumberFormatException -> L1d
            if (r2 == 0) goto L14
            r2 = 1
            r3 = 0
            java.lang.String r5 = r4.b(r5, r2, r3)     // Catch: java.lang.NumberFormatException -> L1d
            goto L15
        L14:
            r5 = r1
        L15:
            r2 = 2130841431(0x7f020f57, float:1.7287929E38)
            java.lang.String r1 = r0.getString(r2)     // Catch: java.lang.NumberFormatException -> L1e
            goto L29
        L1d:
            r5 = r1
        L1e:
            java.lang.String r2 = "setSecondHeartRate(), NumberFormatException"
            java.lang.Object[] r2 = new java.lang.Object[]{r2}
            java.lang.String r3 = "Track_HealthSportingNotificationHelper"
            com.huawei.hwlogsmodel.LogUtil.b(r3, r2)
        L29:
            boolean r2 = android.text.TextUtils.isEmpty(r5)
            if (r2 == 0) goto L36
            r5 = 2130849885(0x7f02305d, float:1.7305076E38)
            java.lang.String r5 = r0.getString(r5)
        L36:
            boolean r0 = r4.t()
            if (r0 == 0) goto L40
            r0 = 2131571313(0x7f0d3271, float:1.8768305E38)
            goto L43
        L40:
            r0 = 2131571312(0x7f0d3270, float:1.8768303E38)
        L43:
            r6.setTextViewText(r0, r5)
            boolean r5 = r4.t()
            if (r5 == 0) goto L50
            r5 = 2131571315(0x7f0d3273, float:1.876831E38)
            goto L53
        L50:
            r5 = 2131571314(0x7f0d3272, float:1.8768307E38)
        L53:
            r6.setTextViewText(r5, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.healthcloud.plugintrack.notification.HealthSportingNotificationHelper.aWj_(java.lang.String, android.widget.RemoteViews):void");
    }

    private void aWi_(String str, String str2, RemoteViews remoteViews) {
        remoteViews.setTextViewText(R.id.text_distance, b(str2, 1, 0));
        remoteViews.setTextViewText(R.id.text_distance_unit, this.c.getResources().getString(R.string._2130841384_res_0x7f020f28));
        remoteViews.setTextViewText(R.id.text_duration, str);
    }

    private void aWh_(String str, String str2, RemoteViews remoteViews) {
        remoteViews.setTextViewText(R.id.text_distance, c(str2));
        remoteViews.setTextViewText(R.id.text_distance_unit, o());
        remoteViews.setTextViewText(R.id.text_duration, str);
    }

    private void a(String str, String str2) {
        LogUtil.a("Track_HealthSportingNotificationHelper", "buildLiveNotification title：", str, ",content:", str2);
        Notification.Builder xf_ = jdh.c().xf_();
        jdh.c().xi_(xf_);
        xf_.setSmallIcon(R.drawable.healthlogo_ic_notification).setWhen(System.currentTimeMillis()).setContentTitle(str).setContentText(str2).setContentIntent(aVS_()).setOngoing(true);
        if (Build.VERSION.SDK_INT >= 31) {
            xf_.setForegroundServiceBehavior(1);
        }
        if (this.j && !e()) {
            xf_.setCategory("service");
        } else {
            xf_.setShowWhen(false).setPriority(0);
            xf_.setGroup("Track_HealthSportingNotificationHelper");
        }
        aVQ_(xf_);
        try {
            jdh.c().xh_(10012, xf_.build());
        } catch (SecurityException e2) {
            LogUtil.h("Track_HealthSportingNotificationHelper", "createCommonNotification SecurityException", LogAnonymous.b((Throwable) e2));
        }
    }

    private void aVQ_(Notification.Builder builder) {
        Notification.Action aVR_ = aVR_();
        if (aVR_ != null) {
            builder.addAction(aVR_);
        }
        if (CommonUtil.bm()) {
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putInt("notification.live.operation", this.r);
        bundle.putString("notification.live.event", "Other");
        bundle.putInt("notification.live.type", 1);
        builder.addExtras(bundle);
        if (this.r == 0) {
            ReleaseLogUtil.e("Track_HealthSportingNotificationHelper", "create LiveNotification firstly");
        }
        this.r = 1;
    }

    private Notification.Action aVR_() {
        String h;
        if (gtx.c(gxf.d()).r() == 1 || (this.j && !e())) {
            LogUtil.a("Track_HealthSportingNotificationHelper", "mIsFromSportService", Boolean.valueOf(this.j));
            return null;
        }
        LogUtil.a("Track_HealthSportingNotificationHelper", "setSportControlButton, getSportState == ", Integer.valueOf(q()));
        int q = q();
        int i = R.drawable._2131431847_res_0x7f0b11a7;
        if (q == 1) {
            h = nsf.h(R.string._2130839739_res_0x7f0208bb);
        } else if (q != 2) {
            h = "";
        } else {
            i = LanguageUtil.bc(this.c) ? R.drawable._2131431850_res_0x7f0b11aa : R.drawable._2131431849_res_0x7f0b11a9;
            h = nsf.h(R.string._2130839723_res_0x7f0208ab);
        }
        PendingIntent aVU_ = aVU_();
        if (CommonUtil.bm()) {
            return new Notification.Action.Builder((Icon) null, h, aVU_).build();
        }
        return new Notification.Action(i, h, aVU_);
    }

    private PendingIntent aVU_() {
        Intent intent = new Intent("com.huawei.health.NOTIFICATION_PAUSE_OR_RESUME_SPORT");
        intent.setPackage(this.c.getPackageName());
        return PendingIntent.getBroadcast(this.c, 0, intent, 201326592);
    }

    private String i(String str) {
        return nsf.b(R.string._2130840104_res_0x7f020a28, gwg.e(this.c, this.y), str);
    }

    private String g(String str) {
        return i(d(str));
    }

    private String a(String str) {
        return i(e(str));
    }

    private String aWa_(Bundle bundle) {
        String string = bundle.getString("skippingNum");
        int h = string != null ? CommonUtil.h(string) : 0;
        return i(this.c.getResources().getQuantityString(R.plurals._2130903274_res_0x7f0300ea, h, UnitUtil.e(h, 1, 0)));
    }

    private String d(String str, String str2, String str3, String str4, String str5) {
        String e2 = e(str2, str3);
        String d2 = d(str4, str5);
        if (TextUtils.isEmpty(d2)) {
            return this.c.getResources().getString(R.string._2130840105_res_0x7f020a29, str, e2);
        }
        return this.c.getResources().getString(R.string._2130840106_res_0x7f020a2a, str, d2, e2);
    }

    private String c(String str, String str2) {
        return this.c.getResources().getString(R.string._2130840105_res_0x7f020a29, str, b(str2));
    }

    private String aVZ_(String str, String str2, String str3, Bundle bundle) {
        String str4;
        String e2 = e(str2, str3);
        String string = bundle.getString("skippingSpeed");
        if (string == null || string.isEmpty() || this.g.equals(string)) {
            str4 = this.g;
        } else {
            str4 = UnitUtil.e(CommonUtil.h(string), 1, 0) + this.c.getResources().getString(R.string._2130843710_res_0x7f02183e);
        }
        return this.c.getResources().getString(R.string._2130840106_res_0x7f020a2a, str, str4, e2);
    }

    private String d(String str) {
        return c(str) + o();
    }

    private String o() {
        if (UnitUtil.h()) {
            return this.c.getResources().getString(R.string._2130844081_res_0x7f0219b1);
        }
        return this.c.getResources().getString(R.string._2130844082_res_0x7f0219b2);
    }

    private String c(String str) {
        return UnitUtil.e(k(str) / 1000.0d, 1, 2);
    }

    private String e(String str) {
        String str2;
        String b;
        Resources resources = this.c.getResources();
        String str3 = null;
        try {
            if (TextUtils.isEmpty(str)) {
                b = resources.getString(R.string._2130849885_res_0x7f02305d);
            } else {
                b = b(str, 1, 0);
            }
        } catch (NumberFormatException unused) {
            str2 = null;
        }
        try {
            str3 = resources.getString(R.string._2130841384_res_0x7f020f28);
            return b + str3;
        } catch (NumberFormatException unused2) {
            String str4 = str3;
            str3 = b;
            str2 = str4;
            LogUtil.b("Track_HealthSportingNotificationHelper", "getCalorieText(), NumberFormatException");
            return str3 + str2;
        }
    }

    private String b(String str) {
        String str2;
        Resources resources = this.c.getResources();
        String str3 = null;
        try {
            if (m(str)) {
                str2 = b(str, 1, 0);
            } else {
                str2 = TextUtils.isEmpty(null) ? resources.getString(R.string._2130849885_res_0x7f02305d) : null;
            }
        } catch (NumberFormatException unused) {
            str2 = null;
        }
        try {
            str3 = resources.getString(R.string.IDS_main_watch_heart_rate_unit_string);
        } catch (NumberFormatException unused2) {
            LogUtil.b("Track_HealthSportingNotificationHelper", "getHeartRateText(), NumberFormatException");
            return str2 + str3;
        }
        return str2 + str3;
    }

    private String e(String str, String str2) {
        String string;
        Resources resources = this.c.getResources();
        if (TextUtils.isEmpty(str) && TextUtils.isEmpty(str2)) {
            return resources.getString(R.string._2130849885_res_0x7f02305d) + resources.getString(R.string._2130841384_res_0x7f020f28);
        }
        String str3 = null;
        try {
        } catch (NumberFormatException unused) {
            str = null;
        }
        try {
            if (m(str2)) {
                str = b(str2, 1, 0);
                string = resources.getString(R.string.IDS_main_watch_heart_rate_unit_string);
            } else {
                str = b(str, 1, 0);
                string = resources.getString(R.string._2130841384_res_0x7f020f28);
            }
            str3 = string;
        } catch (NumberFormatException unused2) {
            LogUtil.b("Track_HealthSportingNotificationHelper", "setCalorieOrHeartRate(), NumberFormatException");
            return str + str3;
        }
        return str + str3;
    }

    private String d(String str, String str2) {
        String j;
        String aWb_;
        Resources resources = this.c.getResources();
        int i = this.y;
        if (i == 259 || i == 265 || i == 273 || (i == 264 && this.j)) {
            j = j(str);
            aWb_ = aWb_(resources);
        } else if (i == 274) {
            j = f(str2);
            aWb_ = aVY_(resources);
        } else if (i == 283) {
            j = "";
            aWb_ = j;
        } else {
            j = h(str2);
            aWb_ = "";
        }
        return j + aWb_;
    }

    private String h(String str) {
        if (this.g.equals(str)) {
            return this.g;
        }
        return gvv.a(n(str));
    }

    private String aVY_(Resources resources) {
        if (UnitUtil.h()) {
            return ("/" + UnitUtil.e(100.0d, 1, 0)) + resources.getQuantityString(R.plurals._2130903227_res_0x7f0300bb, 100);
        }
        return ("/" + UnitUtil.e(500.0d, 1, 0)) + resources.getString(R.string._2130841568_res_0x7f020fe0);
    }

    private String f(String str) {
        if (this.g.equals(str)) {
            return this.g;
        }
        float n = n(str);
        if (UnitUtil.h()) {
            n = ((float) UnitUtil.d(n, 2)) / 5.0f;
        }
        return gvv.a(n);
    }

    private String aWb_(Resources resources) {
        if (UnitUtil.h()) {
            return resources.getString(R.string._2130844079_res_0x7f0219af);
        }
        return resources.getString(R.string._2130844078_res_0x7f0219ae);
    }

    private String j(String str) {
        if (str == null || str.isEmpty() || this.g.equals(str)) {
            return this.g;
        }
        if (b()) {
            return b(str, 1, 2);
        }
        return b(str, 1, 1);
    }

    private void aWl_(String str, String str2, Resources resources) {
        int i;
        if (EnvironmentInfo.j()) {
            i = this.m ? R.layout.sport_data_sub_screen_notification_square : R.layout.sport_data_sub_screen_notification_new;
        } else {
            i = R.layout.sport_data_sub_screen_notification;
        }
        RemoteViews remoteViews = new RemoteViews(this.c.getPackageName(), i);
        remoteViews.setImageViewResource(R.id.iv_icon, R.drawable._2131428467_res_0x7f0b0473);
        aWh_(str, str2, remoteViews);
        aWk_(remoteViews);
        Notification.Builder xf_ = jdh.c().xf_();
        jdh.c().xi_(xf_);
        Bundle bundle = new Bundle();
        bundle.putString("notification_remote_flag", "ongoing_remote");
        bundle.putParcelable("notification_remote_value", remoteViews);
        xf_.setExtras(bundle).setWhen(System.currentTimeMillis()).setSmallIcon(R.drawable._2131428467_res_0x7f0b0473).setOngoing(true).setSound(null).setVibrate(null).setPriority(0);
        xf_.setTimeoutAfter(5000L);
        Notification build = xf_.build();
        build.flags |= 64;
        jdh.c().xh_(10012, build);
        if (this.o) {
            return;
        }
        this.o = true;
        LogUtil.a("Track_HealthSportingNotificationHelper", "setSubScreenNotification = ", build.extras.toString());
        r(AnalyticsValue.HEALTH_SPORT_SHOW_SUB_SCREEN_2170020.value());
    }

    private void r(String str) {
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", 1);
        ixx.d().d(BaseApplication.getContext(), str, hashMap, 0);
    }

    private boolean v() {
        if (u()) {
            return Utils.c(BaseApplication.getContext(), "com.huawei.watch.home");
        }
        return false;
    }

    private boolean u() {
        int i;
        int i2 = this.y;
        return i2 == 257 || i2 == 258 || i2 == 259 || (i2 == 265 && !b()) || (i = this.y) == 273 || i == 264 || i == 274 || i == 275 || i == 280 || i == 281 || i == 282 || i == 260;
    }

    private void aWq_(RemoteViews remoteViews, Resources resources, Bundle bundle) {
        if (this.y == 283) {
            String string = bundle.getString("skippingNum");
            int h = string != null ? CommonUtil.h(string) : 0;
            remoteViews.setTextViewText(R.id.text_distance_unit, resources.getQuantityString(R.plurals._2130903274_res_0x7f0300ea, h, ""));
            remoteViews.setTextViewText(R.id.text_distance, UnitUtil.e(h, 1, 0));
            String string2 = bundle.getString("skippingSpeed");
            int i = R.id.text_speed_or_pace;
            if (string2 == null || string2.isEmpty() || this.g.equals(string2)) {
                if (r()) {
                    i = R.id.text_speed_or_pace_2;
                }
                remoteViews.setTextViewText(i, this.g);
            } else {
                if (r()) {
                    i = R.id.text_speed_or_pace_2;
                }
                remoteViews.setTextViewText(i, UnitUtil.e(CommonUtil.h(string2), 1, 0));
            }
            remoteViews.setTextViewText(r() ? R.id.text_speed_or_pace_unit_2 : R.id.text_speed_or_pace_unit, resources.getString(R.string._2130843710_res_0x7f02183e));
        }
    }

    private void aWe_(RemoteViews remoteViews, Resources resources, Bundle bundle) {
        if (bundle.getBoolean("rowerStrength")) {
            int i = bundle.getInt("rowerTimes");
            remoteViews.setTextViewText(R.id.text_distance_unit, resources.getQuantityString(R.plurals._2130903284_res_0x7f0300f4, i, ""));
            remoteViews.setTextViewText(R.id.text_distance, UnitUtil.e(i, 1, 0));
            int i2 = bundle.getInt("rowerWeight");
            if (UnitUtil.h()) {
                i2 = (int) Math.round(UnitUtil.h(i2));
            }
            remoteViews.setTextViewText(r() ? R.id.text_speed_or_pace_2 : R.id.text_speed_or_pace, UnitUtil.e(i2, 1, 0));
            Object[] objArr = {""};
            remoteViews.setTextViewText(r() ? R.id.text_speed_or_pace_unit_2 : R.id.text_speed_or_pace_unit, UnitUtil.h() ? resources.getQuantityString(R.plurals._2130903216_res_0x7f0300b0, i2, objArr) : resources.getQuantityString(R.plurals._2130903344_res_0x7f030130, i2, objArr));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x0088  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0092  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x009c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void aWg_(java.lang.String r13, java.lang.String r14, android.widget.RemoteViews r15) {
        /*
            r12 = this;
            boolean r0 = health.compact.a.CommonUtil.co()
            java.lang.String r1 = "Track_HealthSportingNotificationHelper"
            if (r0 != 0) goto L18
            boolean r0 = health.compact.a.CommonUtil.bf()
            if (r0 != 0) goto L18
            java.lang.String r13 = "setCalorieOrHeartRate(), EMUI version < 10.0"
            java.lang.Object[] r13 = new java.lang.Object[]{r13}
            com.huawei.hwlogsmodel.LogUtil.a(r1, r13)
            return
        L18:
            android.content.Context r0 = r12.c
            android.content.res.Resources r0 = r0.getResources()
            boolean r2 = android.text.TextUtils.isEmpty(r13)
            r3 = 2131571243(0x7f0d322b, float:1.8768163E38)
            r4 = 2131571241(0x7f0d3229, float:1.876816E38)
            r5 = 2130841384(0x7f020f28, float:1.7287834E38)
            r6 = 2130849885(0x7f02305d, float:1.7305076E38)
            r7 = 2131571244(0x7f0d322c, float:1.8768165E38)
            r8 = 2131571242(0x7f0d322a, float:1.8768161E38)
            if (r2 == 0) goto L59
            boolean r2 = android.text.TextUtils.isEmpty(r14)
            if (r2 == 0) goto L59
            java.lang.String r13 = r0.getString(r6)
            java.lang.String r14 = r0.getString(r5)
            boolean r0 = r12.t()
            if (r0 == 0) goto L4b
            r4 = r8
        L4b:
            r15.setTextViewText(r4, r13)
            boolean r13 = r12.t()
            if (r13 == 0) goto L55
            r3 = r7
        L55:
            r15.setTextViewText(r3, r14)
            return
        L59:
            r2 = 0
            boolean r9 = r12.m(r14)     // Catch: java.lang.NumberFormatException -> L78
            r10 = 0
            r11 = 1
            if (r9 == 0) goto L6e
            java.lang.String r13 = r12.b(r14, r11, r10)     // Catch: java.lang.NumberFormatException -> L78
            r14 = 2130841431(0x7f020f57, float:1.7287929E38)
            java.lang.String r14 = r0.getString(r14)     // Catch: java.lang.NumberFormatException -> L79
            goto L76
        L6e:
            java.lang.String r13 = r12.b(r13, r11, r10)     // Catch: java.lang.NumberFormatException -> L78
            java.lang.String r14 = r0.getString(r5)     // Catch: java.lang.NumberFormatException -> L79
        L76:
            r2 = r14
            goto L82
        L78:
            r13 = r2
        L79:
            java.lang.String r14 = "setCalorieOrHeartRate(), NumberFormatException"
            java.lang.Object[] r14 = new java.lang.Object[]{r14}
            com.huawei.hwlogsmodel.LogUtil.b(r1, r14)
        L82:
            boolean r14 = android.text.TextUtils.isEmpty(r13)
            if (r14 == 0) goto L8c
            java.lang.String r13 = r0.getString(r6)
        L8c:
            boolean r14 = r12.t()
            if (r14 == 0) goto L93
            r4 = r8
        L93:
            r15.setTextViewText(r4, r13)
            boolean r13 = r12.t()
            if (r13 == 0) goto L9d
            r3 = r7
        L9d:
            r15.setTextViewText(r3, r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.healthcloud.plugintrack.notification.HealthSportingNotificationHelper.aWg_(java.lang.String, java.lang.String, android.widget.RemoteViews):void");
    }

    private boolean p() {
        return BaseActivity.isSumsung() && nsn.e(1.16f);
    }

    private boolean t() {
        if (p()) {
            return true;
        }
        return this.p ? nsn.k() : r();
    }

    private boolean r() {
        if (p()) {
            return true;
        }
        return nsn.r();
    }

    private RemoteViews aVV_() {
        boolean bf = CommonUtil.bf();
        LogUtil.h("Track_HealthSportingNotificationHelper", "initRemoteViews getFontScale = ", Float.valueOf(nsn.c()));
        if (this.q || bf) {
            return aVW_(bf);
        }
        return aVX_();
    }

    private RemoteViews aVW_(boolean z) {
        if (this.p) {
            RemoteViews remoteViews = new RemoteViews(this.c.getPackageName(), R.layout.sport_data_notification_hm_30);
            aWf_(remoteViews, nsn.k());
            return remoteViews;
        }
        if (CommonUtil.co() || z) {
            RemoteViews remoteViews2 = new RemoteViews(this.c.getPackageName(), R.layout.sport_data_notification_emui_10);
            aWf_(remoteViews2, r());
            return remoteViews2;
        }
        return new RemoteViews(this.c.getPackageName(), R.layout.sport_data_notification);
    }

    private void aWf_(RemoteViews remoteViews, boolean z) {
        if (z) {
            remoteViews.setViewVisibility(R.id.text_speed_or_pace, 8);
            remoteViews.setViewVisibility(R.id.text_speed_or_pace_unit, 8);
            remoteViews.setViewVisibility(R.id.layout_speed_or_pace, 0);
            remoteViews.setViewVisibility(R.id.text_calorie_or_heart_rate, 8);
            remoteViews.setViewVisibility(R.id.text_calorie_or_heart_rate_unit, 8);
            remoteViews.setViewVisibility(R.id.layout_calorie_or_heart_rate, 0);
        }
    }

    private RemoteViews aVX_() {
        if (Build.VERSION.SDK_INT >= 31 && !BaseActivity.isMiui() && !BaseActivity.isSumsung()) {
            return new RemoteViews(this.c.getPackageName(), R.layout.sport_data_notification_tar31);
        }
        if (CommonUtil.ao() && !CommonUtil.aw()) {
            return new RemoteViews(this.c.getPackageName(), R.layout.sport_data_notification_lower_version_emui);
        }
        if (BaseActivity.isSumsung()) {
            RemoteViews remoteViews = new RemoteViews(this.c.getPackageName(), R.layout.sport_data_notification_sumsung);
            aWd_(remoteViews);
            return remoteViews;
        }
        return new RemoteViews(this.c.getPackageName(), R.layout.sport_data_notification_lower_version);
    }

    private void aWd_(RemoteViews remoteViews) {
        if (nsn.e(1.31f)) {
            aWc_(remoteViews);
            remoteViews.setTextViewTextSize(R.id.text_distance, 1, 24.0f);
            remoteViews.setTextViewTextSize(R.id.text_distance_unit, 1, 20.0f);
            remoteViews.setTextViewTextSize(R.id.text_duration, 1, 24.0f);
            remoteViews.setTextViewTextSize(R.id.text_speed_or_pace_2, 1, 24.0f);
            remoteViews.setTextViewTextSize(R.id.text_speed_or_pace_unit_2, 1, 20.0f);
        } else if (nsn.e(1.16f)) {
            aWc_(remoteViews);
        }
        if (this.i) {
            remoteViews.setViewVisibility(R.id.icon, 8);
        } else {
            remoteViews.setViewPadding(R.id.margin_view_start, 96, 0, 0, 0);
        }
    }

    private void aWc_(RemoteViews remoteViews) {
        if (this.i) {
            remoteViews.setViewVisibility(R.id.app_name_text, 8);
        }
        remoteViews.setViewVisibility(R.id.text_speed_or_pace_2, 0);
        remoteViews.setViewVisibility(R.id.text_speed_or_pace_unit_2, 0);
    }

    private void aWk_(RemoteViews remoteViews) {
        if (remoteViews == null) {
            LogUtil.h("Track_HealthSportingNotificationHelper", "setSportControlButton, remoteViews == null");
            return;
        }
        if (gtx.c(gxf.d()).aq() || (this.j && !e())) {
            LogUtil.a("Track_HealthSportingNotificationHelper", "mIsFromSportService", Boolean.valueOf(this.j));
            remoteViews.setViewVisibility(R.id.sport_control_button, 8);
            return;
        }
        LogUtil.a("Track_HealthSportingNotificationHelper", "setSportControlButton, getSportState == ", Integer.valueOf(q()));
        int q = q();
        if (q == 1) {
            remoteViews.setImageViewResource(R.id.sport_control_button, R.drawable._2131431846_res_0x7f0b11a6);
            remoteViews.setContentDescription(R.id.sport_control_button, nsf.h(R.string._2130839739_res_0x7f0208bb));
        } else if (q == 2) {
            if (LanguageUtil.bc(this.c)) {
                remoteViews.setImageViewResource(R.id.sport_control_button, R.drawable._2131431850_res_0x7f0b11aa);
            } else {
                remoteViews.setImageViewResource(R.id.sport_control_button, R.drawable._2131431849_res_0x7f0b11a9);
            }
            remoteViews.setContentDescription(R.id.sport_control_button, nsf.h(R.string._2130839723_res_0x7f0208ab));
        }
        remoteViews.setOnClickPendingIntent(R.id.sport_control_button, aVU_());
    }

    private void b(boolean z) {
        if (z && this.c != null) {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("com.huawei.health.NOTIFICATION_PAUSE_OR_RESUME_SPORT");
            if (this.w == null) {
                this.w = new NotificationSportControlReceiver();
            }
            BroadcastManagerUtil.bFA_(this.c, this.w, intentFilter, LocalBroadcast.c, null);
        }
        Context context = this.c;
        if (context != null) {
            BroadcastManagerUtil.bFA_(context, this.e, new IntentFilter("com.android.systemui.statusbar.visible.change"), WebViewActivity.HW_SIGNATURE_OR_SYSTEM, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void y() {
        ReleaseLogUtil.e("Track_HealthSportingNotificationHelper", "unregisterSportControlReceiver enter");
        if (this.c != null && this.w != null) {
            ReleaseLogUtil.e("Track_HealthSportingNotificationHelper", "unregisterSportControlReceiver");
            this.c.unregisterReceiver(this.w);
            this.w = null;
        }
        if (this.c == null || this.e == null) {
            return;
        }
        ReleaseLogUtil.e("Track_HealthSportingNotificationHelper", "unregisterCoverStateReceiver");
        this.c.unregisterReceiver(this.e);
        this.e = null;
        d = null;
    }

    public class c implements HwScreenFoldDisplayMode.ScreenFoldDisplayModeListener {
        public c() {
        }

        @Override // com.huawei.healthcloud.plugintrack.notification.HwScreenFoldDisplayMode.ScreenFoldDisplayModeListener
        public void onScreenDisplayModeChange(int i) {
            if (i != 3) {
                HealthSportingNotificationHelper.this.o = false;
            } else if (gso.e().i() == 2) {
                HealthSportingNotificationHelper.this.aWs_(HealthSportingNotificationHelper.d);
            }
            LogUtil.a("Track_HealthSportingNotificationHelper", "onScreenDisplayModeChange = ", Integer.valueOf(i));
        }
    }

    public class NotificationSportControlReceiver extends BroadcastReceiver {
        public NotificationSportControlReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                LogUtil.h("Track_HealthSportingNotificationHelper", "intent == null");
                return;
            }
            if (!nsn.a(500)) {
                LogUtil.a("Track_HealthSportingNotificationHelper", "NotificationSportControlReceiver onReceive getSportState== ", Integer.valueOf(HealthSportingNotificationHelper.this.q()));
                if ("com.huawei.health.NOTIFICATION_PAUSE_OR_RESUME_SPORT".equals(intent.getAction())) {
                    int q = HealthSportingNotificationHelper.this.q();
                    if (q == 1) {
                        HealthSportingNotificationHelper.this.w();
                    } else if (q == 2) {
                        HealthSportingNotificationHelper.this.x();
                    }
                    if (HealthSportingNotificationHelper.d == null) {
                        return;
                    }
                    Message obtain = Message.obtain();
                    obtain.what = 1;
                    obtain.obj = HealthSportingNotificationHelper.d;
                    HealthSportingNotificationHelper.this.f3530a.sendMessage(obtain);
                    return;
                }
                return;
            }
            LogUtil.h("Track_HealthSportingNotificationHelper", "onClick too fast");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int q() {
        SportDataOutputApi sportDataOutputApi;
        if (this.j && (sportDataOutputApi = (SportDataOutputApi) Services.a("SportService", SportDataOutputApi.class)) != null) {
            return sportDataOutputApi.getStatus();
        }
        return gso.e().i();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void w() {
        SportLifecycle sportLifecycle;
        if (this.j && (sportLifecycle = (SportLifecycle) Services.a("SportService", SportLifecycle.class)) != null) {
            sportLifecycle.onPauseSport();
        } else {
            gso.e().v();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void x() {
        SportLifecycle sportLifecycle;
        if (this.j && (sportLifecycle = (SportLifecycle) Services.a("SportService", SportLifecycle.class)) != null) {
            sportLifecycle.onResumeSport();
        } else {
            gso.e().y();
        }
    }

    private void aWn_(String str, String str2, RemoteViews remoteViews, Resources resources) {
        int i = this.y;
        if (i == 259 || i == 265 || i == 273 || (i == 264 && this.j)) {
            aWo_(str, remoteViews, resources);
            return;
        }
        if (i == 274) {
            aWp_(str2, remoteViews, resources);
        } else if (i == 283) {
            remoteViews.setViewVisibility(r() ? R.id.text_speed_or_pace_unit_2 : R.id.text_speed_or_pace_unit, 0);
        } else {
            aWm_(str2, remoteViews);
        }
    }

    private void aWm_(String str, RemoteViews remoteViews) {
        remoteViews.setTextViewText(t() ? R.id.text_speed_or_pace_2 : R.id.text_speed_or_pace, h(str));
        remoteViews.setViewVisibility(t() ? R.id.text_speed_or_pace_unit_2 : R.id.text_speed_or_pace_unit, 8);
    }

    private void aWp_(String str, RemoteViews remoteViews, Resources resources) {
        remoteViews.setTextViewText(r() ? R.id.text_speed_or_pace_2 : R.id.text_speed_or_pace, f(str));
        remoteViews.setTextViewText(r() ? R.id.text_speed_or_pace_unit_2 : R.id.text_speed_or_pace_unit, aVY_(resources));
    }

    private void aWo_(String str, RemoteViews remoteViews, Resources resources) {
        remoteViews.setTextViewText(r() ? R.id.text_speed_or_pace_2 : R.id.text_speed_or_pace, j(str));
        remoteViews.setTextViewText(r() ? R.id.text_speed_or_pace_unit_2 : R.id.text_speed_or_pace_unit, aWb_(resources));
    }

    private PendingIntent aVS_() {
        SportLaunchParams sportLaunchParams;
        if (this.c == null) {
            this.c = BaseApplication.getContext();
        }
        Intent launchIntentForPackage = BaseApplication.getContext().getPackageManager().getLaunchIntentForPackage(BaseApplication.getAppPackage());
        if (launchIntentForPackage == null || launchIntentForPackage.getComponent() == null) {
            return PendingIntent.getActivity(this.c, 0, new Intent().setPackage(BaseApplication.getAppPackage()), 201326592);
        }
        SportDataOutputApi sportDataOutputApi = (SportDataOutputApi) Services.a("SportService", SportDataOutputApi.class);
        String launchActivity = (sportDataOutputApi == null || (sportLaunchParams = (SportLaunchParams) sportDataOutputApi.getParas(SportParamsType.SPORT_LAUNCH_PARAS)) == null) ? null : sportLaunchParams.getLaunchActivity();
        LogUtil.a("Track_HealthSportingNotificationHelper", "className = ", launchActivity);
        aWr_(launchIntentForPackage, launchActivity);
        return PendingIntent.getActivity(this.c, 0, launchIntentForPackage, 201326592);
    }

    private boolean o(String str) {
        if (TextUtils.isEmpty(str)) {
            return this.c.getResources().getConfiguration().orientation == 2;
        }
        return "com.huawei.indoorequip.activity.IndoorEquipLandDisplayActivity".equals(str);
    }

    private void aWr_(Intent intent, String str) {
        if (this.j && o(str)) {
            LogUtil.a("Track_HealthSportingNotificationHelper", "start IndoorEquipLandDisplayActivity");
            intent.setClassName(BaseApplication.getAppPackage(), "com.huawei.indoorequip.activity.IndoorEquipLandDisplayActivity");
            intent.setFlags(268435456);
            return;
        }
        if (TextUtils.isEmpty(str)) {
            if (gso.e().p()) {
                Bundle bundle = new Bundle();
                bundle.putInt("map_tracking_sport_type_sportting", gtx.c(gxf.d()).n());
                intent.putExtra("sportdataparams", bundle);
                intent.setClass(this.c, TrackMainMapActivity.class);
            } else {
                intent.setComponent(new ComponentName(BaseApplication.getAppPackage(), intent.getComponent().getClassName()));
            }
        } else {
            intent.setComponent(new ComponentName(BaseApplication.getAppPackage(), str));
        }
        intent.setAction("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.LAUNCHER");
        intent.setFlags(268435456);
        intent.putExtra("mLaunchSource", 5);
    }

    private double k(String str) {
        if (!l(str)) {
            return 0.0d;
        }
        try {
            return Math.round(Float.parseFloat(str) * 1000.0f);
        } catch (NumberFormatException e2) {
            LogUtil.b("Track_HealthSportingNotificationHelper", "parseTotalDistance ", e2.getMessage());
            return 0.0d;
        }
    }

    private int n(String str) {
        if (!l(str)) {
            return 0;
        }
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e2) {
            LogUtil.b("Track_HealthSportingNotificationHelper", "parsePace ", e2.getMessage());
            return 0;
        }
    }

    private boolean l(String str) {
        return (str == null || "".equals(str) || this.g.equals(str)) ? false : true;
    }

    public void d() {
        ReleaseLogUtil.e("Track_HealthSportingNotificationHelper", "closeNotification()");
        if (!HandlerExecutor.b()) {
            LogUtil.a("Track_HealthSportingNotificationHelper", "closeNotification() check is not ui thread");
            this.f3530a.postAtFrontOfQueue(new Runnable() { // from class: com.huawei.healthcloud.plugintrack.notification.HealthSportingNotificationHelper.5
                @Override // java.lang.Runnable
                public void run() {
                    HealthSportingNotificationHelper.this.s();
                }
            });
        } else {
            s();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void s() {
        LogUtil.a("Track_HealthSportingNotificationHelper", "handleCloseNotification()");
        if (this.s != null) {
            HwFoldScreenManagerEx.unregisterFoldDisplayMode(this.s);
            this.s = null;
        }
        this.k = false;
        h();
    }

    private void h() {
        if (this.c != null) {
            a();
            this.f3530a.removeMessages(1);
            y();
        }
    }

    public static void a() {
        try {
            jdh.c().a(10012);
            ReleaseLogUtil.e("Track_HealthSportingNotificationHelper", "cancel NOTIFICATION_ID");
        } catch (IllegalStateException e2) {
            LogUtil.b("Track_HealthSportingNotificationHelper", "deleteHealthNotification() Exception", e2.getMessage());
        }
    }

    public void g() {
        aWs_(d);
    }

    private String b(String str, int i, int i2) {
        return "--".equals(str) ? str : UnitUtil.e(CommonUtil.a(str), i, i2);
    }

    private boolean m(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            return Integer.parseInt(str) > 0;
        } catch (NumberFormatException unused) {
            LogUtil.b("Track_HealthSportingNotificationHelper", "isValidHeartRate(), NumberFormatException");
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        ThreadPoolManager.d().execute(new Runnable() { // from class: gyh
            @Override // java.lang.Runnable
            public final void run() {
                HealthSportingNotificationHelper.this.j();
            }
        });
    }

    public /* synthetic */ void j() {
        if (!EnvironmentInfo.m() || jdh.c(10012)) {
            return;
        }
        this.r = 0;
        ReleaseLogUtil.e("Track_HealthSportingNotificationHelper", "create mLiveOperation: ", 0);
    }

    class e extends BroadcastReceiver {
        private e() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (context == null || intent == null) {
                LogUtil.b("Track_HealthSportingNotificationHelper", "context == null || intent == null");
                return;
            }
            LogUtil.a("Track_HealthSportingNotificationHelper", "CoverStateBroadcastReceiver received, action = ", intent.getAction());
            if ("com.android.systemui.statusbar.visible.change".equals(intent.getAction())) {
                try {
                    String stringExtra = intent.getStringExtra("visible");
                    ReleaseLogUtil.e("Track_HealthSportingNotificationHelper", "CoverStateBroadcastReceiver visible = ", stringExtra);
                    HealthSportingNotificationHelper.this.t = "true".equals(stringExtra);
                    if (kwx.b()) {
                        HealthSportingNotificationHelper.this.y();
                    } else if (HealthSportingNotificationHelper.this.t) {
                        HealthSportingNotificationHelper.this.f();
                        HealthSportingNotificationHelper.this.g();
                    }
                } catch (BadParcelableException e) {
                    LogUtil.b("Track_HealthSportingNotificationHelper", "BadParcelableException getBooleanExtra:", LogAnonymous.b((Throwable) e));
                }
            }
        }
    }
}
