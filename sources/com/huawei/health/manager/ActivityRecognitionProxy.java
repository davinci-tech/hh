package com.huawei.health.manager;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.PowerManager;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.hihonor.android.location.activityrecognition.HwActivityRecognitionEvent;
import com.huawei.aifitness.mobilesensor.AlgSensorListener;
import com.huawei.aifitness.mobilesensor.MobileSensorManager;
import com.huawei.android.location.activityrecognition.HwActivityChangedEvent;
import com.huawei.android.location.activityrecognition.HwActivityChangedExtendEvent;
import com.huawei.android.location.activityrecognition.HwActivityRecognition;
import com.huawei.android.location.activityrecognition.HwActivityRecognitionExtendEvent;
import com.huawei.android.location.activityrecognition.HwActivityRecognitionHardwareSink;
import com.huawei.android.location.activityrecognition.HwActivityRecognitionServiceConnection;
import com.huawei.android.location.activityrecognition.HwEnvironmentChangedEvent;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.icommon.ISimpleResultCallback;
import com.huawei.health.manager.SportStatusDetectManager;
import com.huawei.health.manager.util.AlarmManagerUtils;
import com.huawei.health.manager.util.Consts;
import com.huawei.hms.network.httpclient.util.PreConnectManager;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.mwx;
import health.compact.a.AuthorizationUtils;
import health.compact.a.CommonUtil;
import health.compact.a.KeyValDbManager;
import health.compact.a.LogAnonymous;
import health.compact.a.LogicalStepCounter;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.SharedPerferenceUtils;
import java.util.Iterator;

/* loaded from: classes.dex */
public class ActivityRecognitionProxy {

    /* renamed from: a, reason: collision with root package name */
    private Context f2761a;
    private PowerManager.WakeLock d;
    private AlarmManagerUtils e;
    private PowerManager f;
    private HwActivityRecognition h;
    private com.hihonor.android.location.activityrecognition.HwActivityRecognition j;
    private SportStatusDetectManager l;
    private final SensorEventListener m = new SensorEventListener() { // from class: com.huawei.health.manager.ActivityRecognitionProxy.1
        @Override // android.hardware.SensorEventListener
        public void onAccuracyChanged(Sensor sensor, int i) {
        }

        @Override // android.hardware.SensorEventListener
        public void onSensorChanged(SensorEvent sensorEvent) {
        }
    };
    private boolean g = false;
    private int o = 40;
    private int k = 180;
    private Handler c = null;
    private Runnable n = null;
    private boolean i = false;
    private SportStatusDetectManager.AutoRecognizeStatus b = SportStatusDetectManager.AutoRecognizeStatus.IDLE;

    public ActivityRecognitionProxy(Context context) {
        this.e = null;
        if (context != null) {
            this.f2761a = context;
            this.e = new AlarmManagerUtils(context);
            if (CommonUtil.bj()) {
                this.j = new com.hihonor.android.location.activityrecognition.HwActivityRecognition(this.f2761a);
            } else {
                this.h = new HwActivityRecognition(this.f2761a);
            }
            Object systemService = this.f2761a.getSystemService("power");
            if (systemService instanceof PowerManager) {
                this.f = (PowerManager) systemService;
            }
            PowerManager powerManager = this.f;
            if (powerManager != null) {
                this.d = powerManager.newWakeLock(1, "Health_AutoTrack");
            }
        }
    }

    public void e() {
        boolean a2 = AuthorizationUtils.a(this.f2761a);
        c(a2);
        if (CommonUtil.bj()) {
            a(a2);
        } else {
            b(a2);
            e(a2);
        }
    }

    private void e(final boolean z) {
        if (mwx.d()) {
            this.l = new SportStatusDetectManager(new TriggerSportInterface() { // from class: com.huawei.health.manager.ActivityRecognitionProxy.2
                @Override // com.huawei.health.manager.TriggerSportInterface
                public void startWalking(Bundle bundle) {
                    ReleaseLogUtil.b("Track_ActRecogProxy", "startWalking");
                    ActivityRecognitionProxy activityRecognitionProxy = ActivityRecognitionProxy.this;
                    activityRecognitionProxy.ajX_(activityRecognitionProxy.f2761a, "com.huawei.health.track.fastwalking", bundle);
                }

                @Override // com.huawei.health.manager.TriggerSportInterface
                public void startRunning(Bundle bundle) {
                    ReleaseLogUtil.b("Track_ActRecogProxy", "startRunning");
                    ActivityRecognitionProxy activityRecognitionProxy = ActivityRecognitionProxy.this;
                    activityRecognitionProxy.ajX_(activityRecognitionProxy.f2761a, "com.huawei.health.track.running", bundle);
                }

                @Override // com.huawei.health.manager.TriggerSportInterface
                public void endSport() {
                    ReleaseLogUtil.b("Track_ActRecogProxy", "end sport");
                    ActivityRecognitionProxy activityRecognitionProxy = ActivityRecognitionProxy.this;
                    activityRecognitionProxy.a(activityRecognitionProxy.f2761a, "com.huawei.health.AUTO_TRACK_END");
                }
            });
            try {
                MobileSensorManager.getInstance().initSensor(BaseApplication.e(), new AlgSensorListener() { // from class: com.huawei.health.manager.ActivityRecognitionProxy.3
                    @Override // com.huawei.aifitness.mobilesensor.AlgSensorListener
                    public void getAlgResult(int i) {
                        SportStatusDetectManager.AutoRecognizeStatus autoRecognizeStatus;
                        if (z) {
                            if (i == 2) {
                                ReleaseLogUtil.b("Track_ActRecogProxy", "sensor enter run");
                                autoRecognizeStatus = SportStatusDetectManager.AutoRecognizeStatus.RUN;
                            } else if (i == 1) {
                                ReleaseLogUtil.b("Track_ActRecogProxy", "sensor enter WALK");
                                autoRecognizeStatus = SportStatusDetectManager.AutoRecognizeStatus.WALK;
                            } else {
                                ReleaseLogUtil.b("Track_ActRecogProxy", "sensor enter IDLE");
                                autoRecognizeStatus = SportStatusDetectManager.AutoRecognizeStatus.IDLE;
                            }
                            ActivityRecognitionProxy.this.b = autoRecognizeStatus;
                            ActivityRecognitionProxy.this.l.c(autoRecognizeStatus);
                        }
                    }
                });
            } catch (Throwable th) {
                ReleaseLogUtil.c("Track_ActRecogProxy", "initSensor error", LogAnonymous.b(th));
            }
        }
    }

    private void c(boolean z) {
        if (!z) {
            LogUtil.h("Track_ActRecogProxy", "initArStateListener is not authorized");
            return;
        }
        String[] d = SharedPerferenceUtils.d(this.f2761a);
        if (d != null) {
            try {
                if (d.length >= 3) {
                    boolean parseBoolean = Boolean.parseBoolean(d[0]);
                    this.g = parseBoolean;
                    ReleaseLogUtil.b("Track_ActRecogProxy", "mIsEnableAutoTrack status: ", Boolean.valueOf(parseBoolean));
                    if (this.g) {
                        int parseInt = Integer.parseInt(d[1]);
                        int parseInt2 = Integer.parseInt(d[2]);
                        AlarmManagerUtils alarmManagerUtils = this.e;
                        if (alarmManagerUtils != null) {
                            alarmManagerUtils.e(parseInt, parseInt2);
                        }
                        this.o = parseInt;
                        this.k = parseInt2;
                    }
                }
            } catch (NumberFormatException e) {
                LogUtil.b("Track_ActRecogProxy", "initConfig NumberFormatException ", e.getMessage());
            }
        }
    }

    private void b(final boolean z) {
        try {
            this.h.b(new HwActivityRecognitionHardwareSink() { // from class: com.huawei.health.manager.ActivityRecognitionProxy.4
                @Override // com.huawei.android.location.activityrecognition.HwActivityRecognitionHardwareSink
                public void onActivityExtendChanged(HwActivityChangedExtendEvent hwActivityChangedExtendEvent) {
                }

                @Override // com.huawei.android.location.activityrecognition.HwActivityRecognitionHardwareSink
                public void onEnvironmentChanged(HwEnvironmentChangedEvent hwEnvironmentChangedEvent) {
                }

                @Override // com.huawei.android.location.activityrecognition.HwActivityRecognitionHardwareSink
                public void onActivityChanged(HwActivityChangedEvent hwActivityChangedEvent) {
                    ActivityRecognitionProxy.this.d(hwActivityChangedEvent, z);
                }
            }, new HwActivityRecognitionServiceConnection() { // from class: com.huawei.health.manager.ActivityRecognitionProxy.5
                @Override // com.huawei.android.location.activityrecognition.HwActivityRecognitionServiceConnection
                public void onServiceConnected() {
                    ActivityRecognitionProxy.this.a();
                }

                @Override // com.huawei.android.location.activityrecognition.HwActivityRecognitionServiceConnection
                public void onServiceDisconnected() {
                    if (ActivityRecognitionProxy.this.g) {
                        LogUtil.a("Track_ActRecogProxy", "HwActivityRecognition onServiceDisconnected");
                        if (mwx.d()) {
                            return;
                        }
                        ActivityRecognitionProxy activityRecognitionProxy = ActivityRecognitionProxy.this;
                        activityRecognitionProxy.a(activityRecognitionProxy.f2761a, "com.huawei.health.AUTO_TRACK_END");
                    }
                }
            });
            LogUtil.a("Track_ActRecogProxy", "connectWithArModule finished ");
        } catch (SecurityException e) {
            LogUtil.a("Track_ActRecogProxy", "connectArModule SecurityException", e.getMessage());
        }
    }

    private void a(final boolean z) {
        try {
            this.j.a(new com.hihonor.android.location.activityrecognition.HwActivityRecognitionHardwareSink() { // from class: com.huawei.health.manager.ActivityRecognitionProxy.6
                @Override // com.hihonor.android.location.activityrecognition.HwActivityRecognitionHardwareSink
                public void onActivityExtendChanged(com.hihonor.android.location.activityrecognition.HwActivityChangedExtendEvent hwActivityChangedExtendEvent) {
                }

                @Override // com.hihonor.android.location.activityrecognition.HwActivityRecognitionHardwareSink
                public void onEnvironmentChanged(com.hihonor.android.location.activityrecognition.HwEnvironmentChangedEvent hwEnvironmentChangedEvent) {
                }

                @Override // com.hihonor.android.location.activityrecognition.HwActivityRecognitionHardwareSink
                public void onActivityChanged(com.hihonor.android.location.activityrecognition.HwActivityChangedEvent hwActivityChangedEvent) {
                    ActivityRecognitionProxy.this.c(hwActivityChangedEvent, z);
                }
            }, new com.hihonor.android.location.activityrecognition.HwActivityRecognitionServiceConnection() { // from class: com.huawei.health.manager.ActivityRecognitionProxy.7
                @Override // com.hihonor.android.location.activityrecognition.HwActivityRecognitionServiceConnection
                public void onServiceConnected() {
                    ActivityRecognitionProxy.this.p();
                    ActivityRecognitionProxy.this.h();
                }

                @Override // com.hihonor.android.location.activityrecognition.HwActivityRecognitionServiceConnection
                public void onServiceDisconnected() {
                    if (ActivityRecognitionProxy.this.g) {
                        LogUtil.a("Track_ActRecogProxy", "HnActivityRecognition onServiceDisconnected");
                        ActivityRecognitionProxy activityRecognitionProxy = ActivityRecognitionProxy.this;
                        activityRecognitionProxy.a(activityRecognitionProxy.f2761a, "com.huawei.health.AUTO_TRACK_END");
                    }
                }
            });
            LogUtil.a("Track_ActRecogProxy", "connectWithHnArModule finished ");
        } catch (SecurityException e) {
            LogUtil.a("Track_ActRecogProxy", "connectWithHnArModule SecurityException", e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(com.hihonor.android.location.activityrecognition.HwActivityChangedEvent hwActivityChangedEvent, boolean z) {
        if (!z) {
            LogUtil.h("Track_ActRecogProxy", "connectWithHnArModule onActivityChanged is not authorized");
            return;
        }
        if (this.g) {
            if (hwActivityChangedEvent != null) {
                Iterator<HwActivityRecognitionEvent> it = hwActivityChangedEvent.getActivityRecognitionEvents().iterator();
                while (it.hasNext()) {
                    a(it.next());
                }
                return;
            }
            LogUtil.a("Track_ActRecogProxy", "connectWithHnArModule hwActivityChangedEvent == null");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        if (!this.g) {
            LogUtil.h("Track_ActRecogProxy", "connectWithHnArModule disconnectService");
            this.j.a();
        } else {
            LogUtil.a("Track_ActRecogProxy", "connectWithHnArModule HwActivityRecognitionServiceConnection onServiceConnected()");
            this.j.b("android.activity_recognition.running", 1, 30000000000L);
            this.j.b("android.activity_recognition.running", 2, 30000000000L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        p();
        if (!this.g) {
            LogUtil.h("Track_ActRecogProxy", "connectWithArModule disconnectService");
            this.h.c();
            return;
        }
        LogUtil.a("Track_ActRecogProxy", "HwActivityRecognitionServiceConnection onServiceConnected()");
        this.h.d("android.activity_recognition.running", 1, 30000000000L);
        this.h.d("android.activity_recognition.running", 2, 30000000000L);
        if (mwx.d()) {
            this.h.d("android.activity_recognition.walking", 1, 30000000000L);
            this.h.d("android.activity_recognition.walking", 2, 30000000000L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(HwActivityChangedEvent hwActivityChangedEvent, boolean z) {
        if (mwx.d()) {
            return;
        }
        if (!z) {
            LogUtil.h("Track_ActRecogProxy", "connectWithArModule onActivityChanged is not authorized");
            return;
        }
        if (this.g) {
            if (hwActivityChangedEvent != null) {
                Iterator<com.huawei.android.location.activityrecognition.HwActivityRecognitionEvent> it = hwActivityChangedEvent.getActivityRecognitionEvents().iterator();
                while (it.hasNext()) {
                    c(it.next());
                }
                return;
            }
            LogUtil.a("Track_ActRecogProxy", "hwActivityChangedEvent == null");
        }
    }

    private void c() {
        if (this.i) {
            this.i = false;
            t();
            a(this.f2761a, "com.huawei.health.AUTO_TRACK_END");
        }
        if (CommonUtil.bj()) {
            this.j.a();
        } else {
            this.h.c();
            d();
        }
        LogUtil.a("Track_ActRecogProxy", "disconnectWithArModule finished ");
    }

    private void d() {
        if (mwx.d()) {
            try {
                MobileSensorManager.getInstance().closeSensor(this.f2761a);
            } catch (Throwable th) {
                ReleaseLogUtil.c("Track_ActRecogProxy", "closeSensor error", LogAnonymous.b(th));
            }
            SportStatusDetectManager sportStatusDetectManager = this.l;
            if (sportStatusDetectManager != null) {
                sportStatusDetectManager.c(SportStatusDetectManager.AutoRecognizeStatus.IDLE);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void p() {
        if (CommonUtil.bj()) {
            if (this.j == null) {
                LogUtil.h("Track_ActRecogProxy", "mHnActivityRecognition is null.");
                return;
            }
            if ((!CommonUtil.ao() || CommonUtil.ch()) && !CommonUtil.bf()) {
                return;
            }
            int f = this.j.f();
            LogUtil.a("Track_ActRecogProxy", "getHnArModuleAbility supportedModule = ", Integer.valueOf(f));
            a(f);
            return;
        }
        if (this.h == null) {
            LogUtil.h("Track_ActRecogProxy", "mHwActivityRecognition is null.");
            return;
        }
        if ((!CommonUtil.ao() || CommonUtil.ch()) && !CommonUtil.bf()) {
            return;
        }
        int i = this.h.i();
        LogUtil.a("Track_ActRecogProxy", "getArModuleAbility supportedModule = ", Integer.valueOf(i));
        a(i);
    }

    private void a(int i) {
        if (i == 1 || i == 3) {
            KeyValDbManager.b(this.f2761a).d("SUPPORT_AR_ABILITY", "1", null);
        } else {
            KeyValDbManager.b(this.f2761a).d("SUPPORT_AR_ABILITY", "0", null);
        }
    }

    private void c(com.huawei.android.location.activityrecognition.HwActivityRecognitionEvent hwActivityRecognitionEvent) {
        String activity = hwActivityRecognitionEvent.getActivity();
        activity.hashCode();
        if (activity.equals("android.activity_recognition.walking")) {
            a(hwActivityRecognitionEvent);
        } else if (activity.equals("android.activity_recognition.running")) {
            b(hwActivityRecognitionEvent);
        } else {
            LogUtil.h("Track_ActRecogProxy", "processActivityChange is default");
        }
    }

    private void b(com.huawei.android.location.activityrecognition.HwActivityRecognitionEvent hwActivityRecognitionEvent) {
        int eventType = hwActivityRecognitionEvent.getEventType();
        if (eventType != 1) {
            if (eventType == 2) {
                f();
                return;
            } else {
                LogUtil.h("Track_ActRecogProxy", "processActivityChange ACTIVITY_RUNNING ", "invalid event type");
                return;
            }
        }
        LogUtil.a("Track_ActRecogProxy", "onActivityChanged enter running");
        AlarmManagerUtils alarmManagerUtils = this.e;
        if (alarmManagerUtils != null) {
            alarmManagerUtils.c(AlarmManagerUtils.SportTypeUtils.STATE_ENTER_RUNNING);
        }
    }

    private void a(com.huawei.android.location.activityrecognition.HwActivityRecognitionEvent hwActivityRecognitionEvent) {
        int eventType = hwActivityRecognitionEvent.getEventType();
        if (eventType == 1) {
            LogUtil.a("Track_ActRecogProxy", "onActivityChanged enter fast walking");
            AlarmManagerUtils alarmManagerUtils = this.e;
            if (alarmManagerUtils != null) {
                alarmManagerUtils.c(AlarmManagerUtils.SportTypeUtils.STATE_ENTER_WALKING);
                return;
            }
            return;
        }
        if (eventType == 2) {
            LogUtil.a("Track_ActRecogProxy", "onActivityChanged exit fast walking");
            AlarmManagerUtils alarmManagerUtils2 = this.e;
            if (alarmManagerUtils2 != null) {
                alarmManagerUtils2.c(AlarmManagerUtils.SportTypeUtils.STATE_EXIT_WALKING);
                return;
            }
            return;
        }
        LogUtil.h("Track_ActRecogProxy", "processActivityChange ACTIVITY_FAST_WALKING ", "invalid event type");
    }

    private void a(HwActivityRecognitionEvent hwActivityRecognitionEvent) {
        LogUtil.a("Track_ActRecogProxy", "processHnActivityChange enter ");
        if ("android.activity_recognition.running".equals(hwActivityRecognitionEvent.getActivity())) {
            if (hwActivityRecognitionEvent.getEventType() == 1) {
                LogUtil.a("Track_ActRecogProxy", "processHnActivityChange enter running");
                AlarmManagerUtils alarmManagerUtils = this.e;
                if (alarmManagerUtils != null) {
                    alarmManagerUtils.c(AlarmManagerUtils.SportTypeUtils.STATE_ENTER_RUNNING);
                    return;
                }
                return;
            }
            if (hwActivityRecognitionEvent.getEventType() == 2) {
                f();
                return;
            } else {
                LogUtil.a("Track_ActRecogProxy", "processHnActivityChange ACTIVITY_RUNNING ", "invalid event type");
                return;
            }
        }
        LogUtil.a("Track_ActRecogProxy", "processHnActivityChange ", "invalid activity");
    }

    private void f() {
        LogUtil.a("Track_ActRecogProxy", "onActivityChanged exit running");
        j();
        AlarmManagerUtils alarmManagerUtils = this.e;
        if (alarmManagerUtils != null) {
            alarmManagerUtils.c(AlarmManagerUtils.SportTypeUtils.STATE_EXIT_RUNNING);
            if (this.i) {
                a(this.f2761a, "com.huawei.health.track.exit_running");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Context context, String str) {
        try {
            if (context == null) {
                LogUtil.a("Track_ActRecogProxy", "sendBroadcast ", "context is null");
                return;
            }
            String packageName = context.getPackageName();
            LogUtil.a("Track_ActRecogProxy", "sendBroadcast() ", " action == ", "com.huawei.health.track.broadcast", " ,msg == ", str);
            Intent intent = new Intent();
            intent.setPackage(packageName);
            intent.setAction("com.huawei.health.track.broadcast");
            intent.putExtra("command_type", str);
            context.sendBroadcast(intent, Consts.f2803a);
            e(str);
        } catch (Exception e) {
            ReleaseLogUtil.c("Track_ActRecogProxy", "sendBroadcast exception:", ExceptionUtils.d(e));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ajX_(Context context, String str, Bundle bundle) {
        try {
            if (context == null) {
                LogUtil.a("Track_ActRecogProxy", "sendBroadcast ", "context is null");
                return;
            }
            String packageName = context.getPackageName();
            LogUtil.a("Track_ActRecogProxy", "sendBroadcast() ", " action == ", "com.huawei.health.track.broadcast", " ,msg == ", str);
            Intent intent = new Intent();
            intent.setPackage(packageName);
            intent.setAction("com.huawei.health.track.broadcast");
            intent.putExtra("command_type", str);
            intent.putExtra("paramsBundle", bundle);
            context.sendBroadcast(intent, Consts.f2803a);
            e(str);
        } catch (RuntimeException e) {
            ReleaseLogUtil.c("Track_ActRecogProxy", "sendBroadcast RuntimeException", ExceptionUtils.d(e));
        }
    }

    private void e(String str) {
        if ("com.huawei.health.track.running".equals(str) || "com.huawei.health.track.fastwalking".equals(str)) {
            this.i = true;
            CommonUtil.bj();
            g("android.activity_recognition.running");
        } else if ("com.huawei.health.AUTO_TRACK_END".equals(str)) {
            this.i = false;
            t();
        } else {
            LogUtil.a("Track_ActRecogProxy", "autoTrackHeartBeat ", "invalid msg");
        }
    }

    private void g(final String str) {
        LogUtil.a("Track_ActRecogProxy", "startAutoTrackHeartBeat", str);
        this.c = new Handler();
        Runnable runnable = new Runnable() { // from class: com.huawei.health.manager.ActivityRecognitionProxy.8
            @Override // java.lang.Runnable
            public void run() {
                if (ActivityRecognitionProxy.this.c == null) {
                    return;
                }
                if (ActivityRecognitionProxy.this.g && !ActivityRecognitionProxy.this.d(str)) {
                    LogUtil.a("Track_ActRecogProxy", "startAutoTrackHeartBeat");
                    ActivityRecognitionProxy.this.e.b();
                }
                ActivityRecognitionProxy.this.c.postDelayed(this, 60000L);
            }
        };
        this.n = runnable;
        this.c.postDelayed(runnable, 60000L);
    }

    private void t() {
        Runnable runnable;
        LogUtil.a("Track_ActRecogProxy", "stopAutoTrackHeartBeat");
        Handler handler = this.c;
        if (handler != null && (runnable = this.n) != null) {
            handler.removeCallbacks(runnable);
        }
        this.n = null;
        this.c = null;
    }

    public boolean ajY_(Intent intent) {
        if (intent == null) {
            return false;
        }
        String action = intent.getAction();
        boolean a2 = AuthorizationUtils.a(this.f2761a);
        if (!a2) {
            LogUtil.a("Track_ActRecogProxy", "isAuthorized", Boolean.valueOf(a2));
            return true;
        }
        if (action != null) {
            LogUtil.h("Track_ActRecogProxy", "processAlarmEvent ", action);
            if ("SEND_START_BROADCAST".equals(action)) {
                m();
            } else if ("SEND_STOP_WALK_BROADCAST".equals(action) || "SEND_STOP_BROADCAST".equals(action)) {
                r();
            } else if ("com.huawei.health.track.config".equals(action)) {
                ajW_(intent);
            } else if ("SEND_START_WALK_BROADCAST".equals(action)) {
                n();
            } else if ("STOP_DETECT_SPORT".equals(action)) {
                s();
            } else if ("SEND_ENTER_PRE_SPORT_BROADCAST".equals(action)) {
                g();
            } else if ("SEND_END_RECOGNIZE_BROADCAST".equals(action)) {
                o();
            } else if ("SEND_END_PRE_STOP_BROADCAST".equals(action)) {
                l();
            } else if ("SEND_ENTER_PRE_STOP_BROADCAST".equals(action)) {
                k();
            } else {
                if (!"SEND_EXIT_PRE_SPORT_BROADCAST".equals(action)) {
                    return false;
                }
                b(intent.getIntExtra("type", SportStatusDetectManager.AutoRecognizeStatus.RUN.getStatus()));
            }
        }
        return true;
    }

    private void g() {
        if (this.g && i()) {
            ReleaseLogUtil.b("Track_ActRecogProxy", "## processEnterPreSportBroadcast mCurStatus ", Integer.valueOf(this.b.getStatus()));
            e(SportStatusDetectManager.AutoRecognizeStatus.RUN);
        }
    }

    private void n() {
        if (this.g && i()) {
            ReleaseLogUtil.b("Track_ActRecogProxy", "## processAlarmEvent ");
            d("android.activity_recognition.walking");
            if (mwx.d()) {
                SportStatusDetectManager sportStatusDetectManager = this.l;
                if (sportStatusDetectManager == null) {
                    ReleaseLogUtil.b("Track_ActRecogProxy", "## mSportStatusDetectManager is null");
                    return;
                }
                sportStatusDetectManager.c(SportStatusDetectManager.AutoRecognizeStatus.WALK);
            } else {
                a(this.f2761a, "com.huawei.health.track.fastwalking");
            }
            e(PreConnectManager.CONNECT_INTERNAL);
        }
    }

    private void b(int i) {
        if (this.g && i()) {
            if (i == SportStatusDetectManager.AutoRecognizeStatus.RUN.getStatus()) {
                e(SportStatusDetectManager.AutoRecognizeStatus.RUN);
            } else {
                e(SportStatusDetectManager.AutoRecognizeStatus.WALK);
            }
            ReleaseLogUtil.b("Track_ActRecogProxy", "## processExitPreSportBroadcast ", Integer.valueOf(i));
        }
    }

    private void e(SportStatusDetectManager.AutoRecognizeStatus autoRecognizeStatus) {
        SportStatusDetectManager sportStatusDetectManager = this.l;
        if (sportStatusDetectManager == null) {
            ReleaseLogUtil.b("Track_ActRecogProxy", "## changeSportStatus mSportStatusDetectManager is null");
        } else {
            sportStatusDetectManager.c(autoRecognizeStatus);
            e(PreConnectManager.CONNECT_INTERNAL);
        }
    }

    private void o() {
        if (this.g && i()) {
            ReleaseLogUtil.b("Track_ActRecogProxy", "## processExitRecognizeBroadcast ", Integer.valueOf(this.b.getStatus()));
            e(SportStatusDetectManager.AutoRecognizeStatus.END_CALCULATE);
        }
    }

    private void s() {
        if (this.g && i()) {
            ReleaseLogUtil.b("Track_ActRecogProxy", "## processStopDetect ");
            e(SportStatusDetectManager.AutoRecognizeStatus.IDLE);
        }
    }

    private void k() {
        if (this.g && i()) {
            ReleaseLogUtil.b("Track_ActRecogProxy", "## processExitPreSportStopBroadcast ");
            e(SportStatusDetectManager.AutoRecognizeStatus.IDLE);
        }
    }

    private void l() {
        if (this.g && i()) {
            ReleaseLogUtil.b("Track_ActRecogProxy", "## processExitPreStopBroadcast ", Integer.valueOf(this.b.getStatus()));
            e(this.b);
        }
    }

    private void m() {
        if (this.g && i()) {
            ReleaseLogUtil.b("Track_ActRecogProxy", "## processAlarmEvent ");
            CommonUtil.bj();
            d("android.activity_recognition.running");
            if (mwx.d()) {
                SportStatusDetectManager sportStatusDetectManager = this.l;
                if (sportStatusDetectManager == null) {
                    ReleaseLogUtil.b("Track_ActRecogProxy", "## processStartBroadcast manager is null");
                    return;
                }
                sportStatusDetectManager.c(SportStatusDetectManager.AutoRecognizeStatus.RUN);
            } else {
                a(this.f2761a, "com.huawei.health.track.running");
            }
            e(PreConnectManager.CONNECT_INTERNAL);
        }
    }

    private void r() {
        b();
        j();
        ReleaseLogUtil.b("Track_ActRecogProxy", "enter stop");
        if (mwx.d()) {
            SportStatusDetectManager sportStatusDetectManager = this.l;
            if (sportStatusDetectManager == null) {
                ReleaseLogUtil.b("Track_ActRecogProxy", "## processStopBroadcast manager is null");
                return;
            } else {
                sportStatusDetectManager.c(SportStatusDetectManager.AutoRecognizeStatus.IDLE);
                return;
            }
        }
        a(this.f2761a, "com.huawei.health.AUTO_TRACK_END");
    }

    private void ajW_(Intent intent) {
        boolean booleanExtra = intent.getBooleanExtra("autotrack_enable", this.g);
        int intExtra = intent.getIntExtra("start_delay", this.o);
        int intExtra2 = intent.getIntExtra("stop_delay", this.k);
        boolean a2 = AuthorizationUtils.a(this.f2761a);
        if (booleanExtra && !this.g && a2) {
            if (CommonUtil.bj()) {
                a(a2);
            } else {
                b(a2);
                e(a2);
            }
        } else if (!booleanExtra && this.g) {
            c();
        } else {
            ReleaseLogUtil.b("Track_ActRecogProxy", "processUpdateAutoTrack ", "isAuthorized", Boolean.valueOf(a2));
        }
        ReleaseLogUtil.b("Track_ActRecogProxy", "change startDelay ", Integer.valueOf(intExtra), " stopDelay ", Integer.valueOf(intExtra2), " state ", Boolean.valueOf(booleanExtra));
        AlarmManagerUtils alarmManagerUtils = this.e;
        if (alarmManagerUtils != null) {
            alarmManagerUtils.e(intExtra, intExtra2);
        }
        SharedPerferenceUtils.c(this.f2761a, booleanExtra, intExtra, intExtra2);
        if (this.g != booleanExtra) {
            this.g = booleanExtra;
        }
        LogUtil.a("Track_ActRecogProxy", "processUpdateAutoTrack mIsEnableAutoTrack status: ", Boolean.valueOf(this.g));
    }

    private void b() {
        PowerManager.WakeLock wakeLock = this.d;
        if (wakeLock == null || !wakeLock.isHeld()) {
            return;
        }
        LogUtil.a("Track_ActRecogProxy", "WakeLock release");
        this.d.release();
    }

    private void e(long j) {
        LogUtil.a("Track_ActRecogProxy", "WakeLock acquire");
        this.d.acquire(j);
    }

    private boolean i() {
        boolean z;
        boolean z2;
        Context context = this.f2761a;
        if (context == null) {
            return false;
        }
        LocationManager locationManager = (LocationManager) context.getSystemService("location");
        if (locationManager != null) {
            z = locationManager.isProviderEnabled(GeocodeSearch.GPS);
            z2 = locationManager.isProviderEnabled("GpsMockProvider");
        } else {
            z = false;
            z2 = false;
        }
        return locationManager != null && (z || z2);
    }

    private void j() {
        Context context = this.f2761a;
        if (context == null) {
            LogUtil.a("Track_ActRecogProxy", "flushStepCounterData ", "mContext is null");
            return;
        }
        if (context.getSystemService("sensor") instanceof SensorManager) {
            SensorManager sensorManager = (SensorManager) this.f2761a.getSystemService("sensor");
            LogUtil.a("Track_ActRecogProxy", "flushStepCounterData manager is ", sensorManager);
            if (sensorManager != null) {
                try {
                    ReleaseLogUtil.b("Track_ActRecogProxy", " flushStepCounterData registerListener = ", Boolean.valueOf(sensorManager.registerListener(this.m, sensorManager.getDefaultSensor(19), 0)));
                    sensorManager.unregisterListener(this.m, sensorManager.getDefaultSensor(19));
                } catch (RuntimeException unused) {
                    ReleaseLogUtil.c("Track_ActRecogProxy", "flushStepCounterData happened exception");
                    return;
                }
            }
            LogicalStepCounter.c(this.f2761a).b((ISimpleResultCallback) null);
            return;
        }
        LogUtil.a("Track_ActRecogProxy", "object invalid type");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean d(String str) {
        if (CommonUtil.bj()) {
            if (this.j != null) {
                return c(str).booleanValue();
            }
            return false;
        }
        if (this.h != null) {
            return a(str).booleanValue();
        }
        return false;
    }

    private Boolean c(String str) {
        return Boolean.valueOf(b(str));
    }

    private Boolean a(String str) {
        return Boolean.valueOf(b(str));
    }

    private boolean b(String str) {
        if (CommonUtil.bj()) {
            com.hihonor.android.location.activityrecognition.HwActivityChangedExtendEvent d = this.j.d();
            if (d != null) {
                return d(str, d);
            }
            return false;
        }
        HwActivityChangedExtendEvent f = this.h.f();
        if (f != null) {
            return b(str, f);
        }
        return false;
    }

    private boolean b(String str, HwActivityChangedExtendEvent hwActivityChangedExtendEvent) {
        for (HwActivityRecognitionExtendEvent hwActivityRecognitionExtendEvent : hwActivityChangedExtendEvent.getActivityRecognitionExtendEvents()) {
            if (str.equals(hwActivityRecognitionExtendEvent.getActivity())) {
                LogUtil.a("Track_ActRecogProxy", "curState is ", str);
                return true;
            }
            LogUtil.a("Track_ActRecogProxy", "curState is ", hwActivityRecognitionExtendEvent.getActivity());
        }
        return false;
    }

    private boolean d(String str, com.hihonor.android.location.activityrecognition.HwActivityChangedExtendEvent hwActivityChangedExtendEvent) {
        for (com.hihonor.android.location.activityrecognition.HwActivityRecognitionExtendEvent hwActivityRecognitionExtendEvent : hwActivityChangedExtendEvent.getActivityRecognitionExtendEvents()) {
            if (str.equals(hwActivityRecognitionExtendEvent.getActivity())) {
                LogUtil.a("Track_ActRecogProxy", "mHnActivityRecognition curState is ", str);
                return true;
            }
            LogUtil.a("Track_ActRecogProxy", "mHnActivityRecognition curState is ", hwActivityRecognitionExtendEvent.getActivity());
        }
        return false;
    }
}
