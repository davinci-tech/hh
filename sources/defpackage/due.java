package defpackage;

import android.os.Bundle;
import com.apprichtap.haptic.base.PrebakedEffectId;
import com.huawei.health.device.api.IndoorEquipManagerApi;
import com.huawei.health.devicemgr.api.constant.GetDeviceInfoMode;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseConstants;
import com.huawei.hwfoundationmodel.trackmodel.ISportDataCallback;
import com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.ble.BleConstants;
import com.huawei.pluginachievement.manager.model.MedalConstants;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class due {
    private static final Object d = new Object();

    /* renamed from: a, reason: collision with root package name */
    private int f11841a;
    private boolean b;
    private int c;
    private IBaseResponseCallback e;
    private int f;
    private boolean g;
    private boolean h;
    private boolean i;
    private int j;
    private int k;
    private long l;
    private int m;
    private int n;
    private boolean o;
    private ISportDataCallback p;
    private IBaseResponseCallback q;
    private int r;
    private IBaseResponseCallback s;
    private int t;

    static class c {
        private static final due c = new due();
    }

    private due() {
        this.j = 0;
        this.c = 0;
        this.b = false;
        this.h = false;
        this.i = false;
        this.g = false;
        this.o = false;
        this.f = 0;
        this.r = 0;
        this.n = 0;
        this.p = new ISportDataCallback() { // from class: due.4
            @Override // com.huawei.hwfoundationmodel.trackmodel.ISportDataCallback
            public void onSummary(MotionPathSimplify motionPathSimplify) {
            }

            @Override // com.huawei.hwfoundationmodel.trackmodel.ISportDataCallback
            public void getSportInfo(Bundle bundle) {
                int i;
                synchronized (due.d) {
                    LogUtil.c("HWhealthLinkage_", "Bundle sportInfo = " + bundle);
                    if (bundle == null) {
                        LogUtil.a("HealthTrackInteractor", "sportDataCallback getSportInfo sportInfo is null");
                        sqo.w("sportInfo is null");
                        return;
                    }
                    try {
                        due dueVar = due.this;
                        dueVar.f11841a = dueVar.a(bundle.getInt("origintarget"));
                        due.this.m = bundle.getInt(BleConstants.SPORT_TYPE);
                        due.this.t = bundle.getInt("trackType");
                        i = bundle.getInt("sportState");
                    } catch (RuntimeException e) {
                        sqo.w("HealthTrackInteractor sportInfo RuntimeException" + e.getMessage());
                        throw e;
                    } catch (Exception e2) {
                        LogUtil.a("HealthTrackInteractor", "HealthTrackInteractor sportInfo Exception");
                        sqo.w("HealthTrackInteractor sportInfo Exception" + e2.getMessage());
                    }
                    if (i == 6) {
                        LogUtil.h("HealthTrackInteractor", "sportDataCallback getSportInfo mReportTrackState is ", Integer.valueOf(i));
                        return;
                    }
                    due.this.k = i;
                    due.this.n = bundle.getInt("runningCourseVersion");
                    if (due.this.c == due.this.k) {
                        due.this.aaf_(bundle);
                        duk.a().sendDataToDevice(bundle);
                        return;
                    }
                    LogUtil.a("HWhealthLinkage_", "------------------Before----------------");
                    ReleaseLogUtil.e("HWhealthLinkage_", "mReportTrackState is ", Integer.valueOf(due.this.k), ",mPreviousTrackState is ", Integer.valueOf(due.this.j), ",curTrackState is ", Integer.valueOf(due.this.c));
                    due.this.h();
                    due.this.aae_(bundle);
                    LogUtil.a("HWhealthLinkage_", "------------------After----------------");
                    due dueVar2 = due.this;
                    dueVar2.j = dueVar2.c;
                    due dueVar3 = due.this;
                    dueVar3.c = dueVar3.k;
                    due.this.b = false;
                    LogUtil.a("HWhealthLinkage_", "mPreviousTrackState is ", Integer.valueOf(due.this.j), " curTrackState is ", Integer.valueOf(due.this.c));
                    due.this.aaf_(bundle);
                }
            }
        };
        this.e = new IBaseResponseCallback() { // from class: due.1
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                LogUtil.a("HWhealthLinkage_", "err_code is " + i);
                LogUtil.c("HWhealthLinkage_", "objData is " + obj);
            }
        };
        this.s = new IBaseResponseCallback() { // from class: due.2
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                ReleaseLogUtil.e("HWhealthLinkage_", "setOperatorCallback setOperator err_code is " + i);
                if (obj != null) {
                    LogUtil.c("HWhealthLinkage_", "objData is " + obj);
                }
                if (i != 100000 && !due.this.e()) {
                    dum.d().c(false);
                    sqo.w("setOperatorCallback errCode not is SUCCEED");
                } else {
                    dum.d().c(true);
                    dul.c().j();
                }
            }
        };
        this.q = new IBaseResponseCallback() { // from class: due.3
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                ReleaseLogUtil.e("HWhealthLinkage_", "setOperatorHelperCallback setOperator err_code is " + i);
                if (obj != null) {
                    LogUtil.c("HWhealthLinkage_", "objData is " + obj);
                }
                if (i != 100000 && !due.this.e()) {
                    sqo.w("setOperatorHelperCallback errCode not is SUCCEED");
                    dum.d().c(false);
                } else {
                    dum.d().c(true);
                }
            }
        };
    }

    public static final due a() {
        return c.c;
    }

    public int c() {
        int i;
        synchronized (d) {
            i = this.c;
        }
        return i;
    }

    public void d(boolean z) {
        this.b = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void aae_(Bundle bundle) {
        int i = this.k;
        if (i == 1) {
            aag_(bundle);
            return;
        }
        if (i == 2) {
            e(this.m);
        } else if (i == 3) {
            c(this.m);
        } else {
            LogUtil.a("HWhealthLinkage_", "getSportInfo other condition");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        this.i = dum.d().j();
        this.h = dum.d().i();
        this.g = dum.d().g();
        DeviceInfo deviceInfo = cun.c().getDeviceInfo(GetDeviceInfoMode.CONNECTED_MAIN_DEVICES_WITHOUT_AW70, null, "HealthTrackInteractor");
        int i = this.m;
        if (i == 283) {
            this.i = false;
            this.o = true;
        }
        if (lbc.a(i) && (deviceInfo == null || !cwi.c(deviceInfo, 0))) {
            this.i = false;
            this.o = true;
        }
        if (this.t == 1) {
            this.i = false;
            this.o = true;
        }
        LogUtil.a("HWhealthLinkage_", "mIsNewLinkStrategy:", Boolean.valueOf(this.i), "  mIsOldLinkStrategy:", Boolean.valueOf(this.h), "  mIsSupportHeartReport:", Boolean.valueOf(this.g), "  mSportType is:", Integer.valueOf(this.m), " mTrackType:", this.t + "deviceInfo:", deviceInfo);
    }

    private void aag_(Bundle bundle) {
        int i = this.c;
        if (i == 0 || i == 3 || i == 7) {
            dum.d().c(false);
            bnl.a().b();
            dty.b().c();
            this.l = bundle.getLong("sportStartTime");
            this.f = bundle.getInt("linkType");
            this.r = bundle.getInt("forbid_pause", 0);
            d(this.m);
            return;
        }
        b(this.m);
    }

    public boolean d() {
        LogUtil.a("HWhealthLinkage_", "registerTrackStateListener");
        kzc.n().e(this.p);
        gso.e().d(this.p, 1L);
        dul.c().b(new IBaseResponseCallback() { // from class: due.5
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                LogUtil.a("HWhealthLinkage_", "registerBluetoothStateListener invoke, bluetoothState = ", Integer.valueOf(i));
                if (dum.d().f() && i == 4) {
                    synchronized (due.d) {
                        if (due.this.k != 1) {
                            if (due.this.k == 2) {
                                due dueVar = due.this;
                                dueVar.c(dueVar.f11841a, 6);
                                dul.c().a();
                            } else {
                                LogUtil.a("HWhealthLinkage_", "registerBluetoothStateListener other condition");
                            }
                        } else {
                            due dueVar2 = due.this;
                            dueVar2.c(dueVar2.f11841a, 5);
                            dul.c().e();
                        }
                    }
                }
            }
        });
        return true;
    }

    private void c(int i) {
        int i2;
        dub.a().e();
        boolean z = this.h;
        if (z && this.f11841a == 1) {
            LogUtil.a("HWhealthLinkage_", "current link strategy is OldLinkStrategy and single track, stop sport");
            dul.c().b();
        } else if (z && ((i2 = this.f11841a) == 2 || i2 == 4)) {
            LogUtil.a("HWhealthLinkage_", "current link strategy is OldLinkStrategy and suggest plan, stop sport");
            if (!bnl.a().j()) {
                c(this.f11841a, 4);
            }
            dul.c().b();
        } else if (this.i) {
            LogUtil.a("HWhealthLinkage_", "current link strategy is NewLinkStrategy, stop sport");
            c(this.f11841a, 4);
            dul.c().b();
        } else if (this.o) {
            LogUtil.a("HWhealthLinkage_", "current link strategy is SilentLinkStrategy, stop sport");
            dum.d().c(false);
        } else if (this.g) {
            LogUtil.a("HWhealthLinkage_", "current link strategy only support heart rate, stop sport");
            dul.c().b();
        } else {
            LogUtil.a("HWhealthLinkage_", "stop sport other condition");
        }
        if (dum.d().f()) {
            dul.c().a();
        }
        duk.a().closeReport(i, 4);
        dwm.c(false);
    }

    private void e(int i) {
        int i2;
        boolean z = this.h;
        if (z && this.f11841a == 1) {
            LogUtil.a("HWhealthLinkage_", "current link strategy is OldLinkStrategy and single track, pause sport");
            dul.c().b();
        } else if (z && ((i2 = this.f11841a) == 2 || i2 == 4)) {
            LogUtil.a("HWhealthLinkage_", "current link strategy is OldLinkStrategy and suggest plan, pause sport");
            c(this.f11841a, 2);
            dul.c().b();
        } else if (this.i) {
            LogUtil.a("HWhealthLinkage_", "current link strategy is NewLinkStrategy, pause sport");
            c(this.f11841a, 2);
            dul.c().b();
        } else if (this.g) {
            LogUtil.a("HWhealthLinkage_", "current link strategy only support heart rate, pause sport");
            dul.c().b();
        } else {
            LogUtil.a("HWhealthLinkage_", "pause sport other condition");
        }
        if (dum.d().f()) {
            dul.c().a();
        }
        duk.a().closeReport(i, 2);
    }

    private void b(int i) {
        int i2;
        boolean z = this.h;
        if (z && this.f11841a == 1) {
            LogUtil.a("HWhealthLinkage_", "current link strategy is OldLinkStrategy and single track, resume sport");
            dul.c().j();
        } else if (z && ((i2 = this.f11841a) == 2 || i2 == 4)) {
            LogUtil.a("HWhealthLinkage_", "current link strategy is OldLinkStrategy and suggest plan, resume sport");
            c(this.f11841a, 3);
        } else if (this.i) {
            LogUtil.a("HWhealthLinkage_", "current link strategy is NewLinkStrategy, resume sport");
            c(this.f11841a, 3);
        } else if (this.g) {
            LogUtil.a("HWhealthLinkage_", "current link strategy only support heart rate, resume sport");
            dul.c().j();
        } else {
            LogUtil.a("HWhealthLinkage_", "resume sport other condition");
        }
        if (dum.d().f()) {
            dul.c().e();
        }
        duk.a().openReport(i, 3);
    }

    private void d(int i) {
        int i2;
        boolean z = this.h;
        if (z && this.f11841a == 1) {
            ReleaseLogUtil.e("HWhealthLinkage_", "current link strategy is OldLinkStrategy and single track, start sport");
            dul.c().j();
        } else if (z && ((i2 = this.f11841a) == 2 || i2 == 4)) {
            ReleaseLogUtil.e("HWhealthLinkage_", "current link strategy is OldLinkStrategy and suggest plan, start sport");
            c(this.f11841a, 1);
        } else if (this.i) {
            ReleaseLogUtil.e("HWhealthLinkage_", "current link strategy is NewLinkStrategy, start sport");
            c(this.f11841a, 1);
        } else if (this.o) {
            ReleaseLogUtil.e("HWhealthLinkage_", "current link strategy is SilentLinkStrategy, start sport");
            dum.d().c(true);
            dul.c().i();
        } else if (this.g) {
            ReleaseLogUtil.e("HWhealthLinkage_", "only support heart rate, start sport");
            dul.c().j();
        } else {
            ReleaseLogUtil.e("HWhealthLinkage_", "startSport other condition");
        }
        if (dum.d().f()) {
            dul.c().e();
        }
        duk.a().openReport(i, 1);
        dwm.c(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int a(int i) {
        return (i == 4 && "0".equals(SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(PrebakedEffectId.RT_COIN_DROP), "new_run_course"))) ? 4 : 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void aaf_(Bundle bundle) {
        if (dum.d().j() && dum.d().h()) {
            JSONObject jSONObject = new JSONObject();
            try {
                aac_(bundle, jSONObject);
                if (dum.d().f()) {
                    aab_(bundle, jSONObject);
                    aad_(bundle, jSONObject);
                    LogUtil.a("HealthTrackInteractor", "sportInfo toString", bundle);
                }
            } catch (JSONException e) {
                sqo.w("deliverRealTimeSportInfo exception " + e.getMessage());
                LogUtil.h("HWhealthLinkage_", e.getMessage());
            }
            dul.c().e(jSONObject, this.e);
        }
    }

    private void aac_(Bundle bundle, JSONObject jSONObject) throws JSONException {
        if (bundle == null) {
            LogUtil.a("HealthTrackInteractor", "addOldSportData sportInfo is null");
            return;
        }
        try {
            jSONObject.put("exercise_duration", ((Integer) bundle.get("duration")).intValue());
            if (bundle.get("pace") == null) {
                jSONObject.put("pace", 0);
            } else {
                jSONObject.put("pace", bundle.getInt("pace"));
            }
            if (bundle.get("speed") == null) {
                jSONObject.put("speed", 0);
            } else {
                jSONObject.put("speed", Math.round(bundle.getFloat("speed") * 10.0f));
            }
            if (bundle.get("speed_new") == null) {
                jSONObject.put("speed_new", 0);
            } else {
                jSONObject.put("speed_new", bundle.getInt("speed_new"));
            }
            if (bundle.get("avg_speed_new") == null) {
                jSONObject.put("avg_speed_new", 0);
            } else {
                jSONObject.put("avg_speed_new", bundle.getInt("avg_speed_new"));
            }
            jSONObject.put("calorie", ((Integer) bundle.get("calorie")).intValue());
            jSONObject.put("distance", ((Integer) bundle.get("distance")).intValue());
            jSONObject.put(HwExerciseConstants.JSON_NAME_ACTIVECALORIE, ((Integer) bundle.get(HwExerciseConstants.JSON_NAME_ACTIVECALORIE)).intValue());
        } catch (JSONException e) {
            sqo.w("addOldSportData JSONException" + e.getMessage());
            throw e;
        } catch (Exception e2) {
            LogUtil.a("HealthTrackInteractor", "addOldSportData Exception");
            sqo.w("addOldSportData Exception" + e2.getMessage());
        }
    }

    private void aad_(Bundle bundle, JSONObject jSONObject) throws JSONException {
        if (bundle == null) {
            LogUtil.a("HealthTrackInteractor", "addNewSportData sportInfo is null");
            return;
        }
        int i = bundle.getInt(BleConstants.SPORT_TYPE);
        if (i == 273) {
            jSONObject.put("linkage_cadence", bundle.getInt("cadence"));
        } else if (i == 274) {
            jSONObject.put("linkage_pulp_frequency", bundle.getFloat("paddleFrequency"));
            jSONObject.put("linkage_slurry", bundle.getInt("totalPaddle"));
        }
        if (lbc.a(i)) {
            jSONObject.put("linkage_power", bundle.getInt("power"));
            jSONObject.put("linkage_resistance_level", bundle.getInt("resistanceLevel"));
            jSONObject.put("linkage_resistance_level_max", bundle.getInt("resistanceMaxLevel"));
            jSONObject.put("linkage_resistance_level_min", bundle.getInt("resistanceMinLevel"));
        }
    }

    private void aab_(Bundle bundle, JSONObject jSONObject) throws JSONException {
        if (bundle == null) {
            LogUtil.a("HealthTrackInteractor", "addNewSportData sportInfo is null");
            return;
        }
        try {
            jSONObject.put("hr", bundle.getInt(IndoorEquipManagerApi.KEY_HEART_RATE));
            jSONObject.put("total_rise", bundle.getInt("totalCreep"));
            jSONObject.put("total_descend", bundle.getInt(BleConstants.TOTAL_DESCENT));
            jSONObject.put("altitude", bundle.getInt("altitude"));
            jSONObject.put("aerobic_te", bundle.getInt("aerobicExercise"));
            jSONObject.put("anaerobic_te", bundle.getInt("anaerobicExercise"));
            jSONObject.put("performance_condition", bundle.getInt("performanceIndicator"));
            jSONObject.put("operator_type", bundle.getInt("sportState"));
            jSONObject.put("running_course_number", bundle.getInt("runningActionIndex"));
            jSONObject.put("running_course_action_count", bundle.getInt("runningCourseNumber"));
            jSONObject.put("running_course_action_id", bundle.getString("runningActionId"));
            jSONObject.put("running_course_left_type", bundle.getInt("goalType"));
            jSONObject.put("running_course_content", bundle.getInt("goalGapValue"));
            jSONObject.put("step_cadence", bundle.getInt("stepRate"));
            jSONObject.put("step", bundle.getInt(MedalConstants.EVENT_STEPS));
        } catch (JSONException e) {
            sqo.w("addNewSportData JSONException" + e.getMessage());
            throw e;
        } catch (Exception e2) {
            LogUtil.a("HealthTrackInteractor", "addNewSportData Exception");
            sqo.w("addNewSportData Exception" + e2.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean e() {
        List<DeviceCapability> c2;
        DeviceCapability i;
        if (dwo.d().a(true) != null && (i = dwo.d().i()) != null) {
            boolean isSupportExerciseAdvice = i.isSupportExerciseAdvice();
            boolean isSupportWorkoutExerciseDisplayLink = i.isSupportWorkoutExerciseDisplayLink();
            if (isSupportExerciseAdvice && !isSupportWorkoutExerciseDisplayLink) {
                return true;
            }
        }
        if (dwo.d().a(false) == null || (c2 = dwo.d().c()) == null) {
            return false;
        }
        return d(false, c2);
    }

    private boolean d(boolean z, List<DeviceCapability> list) {
        for (DeviceCapability deviceCapability : list) {
            if (deviceCapability != null) {
                boolean isSupportExerciseAdvice = deviceCapability.isSupportExerciseAdvice();
                boolean isSupportWorkoutExerciseDisplayLink = deviceCapability.isSupportWorkoutExerciseDisplayLink();
                if (isSupportExerciseAdvice && !isSupportWorkoutExerciseDisplayLink) {
                    LogUtil.a("HWhealthLinkage_", "isExitOldLinkDevice has existed");
                    return true;
                }
            }
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean c(int i, int i2) {
        ReleaseLogUtil.e("HWhealthLinkage_", "notifyWearDeviceState sportType = " + i + ", sportState = " + i2 + ", deviceReportState=" + this.b);
        if (this.b) {
            if (i2 == 3) {
                dul.c().j();
            }
            return true;
        }
        boolean h = dum.d().h();
        if (i2 != 1 && !h) {
            ReleaseLogUtil.d("HWhealthLinkage_", "failed to linkage and linking status is " + h);
            sqo.w("sportState not is start " + i2);
            return false;
        }
        if (i2 == 1 || h) {
            ReleaseLogUtil.e("HWhealthLinkage_", "notifyWearDeviceState is linking");
            JSONObject jSONObject = new JSONObject();
            a(jSONObject, i, i2);
            if (i2 == 1 || i2 == 3) {
                dul.c().a(jSONObject, this.s);
            } else {
                dul.c().a(jSONObject, this.q);
            }
        }
        return true;
    }

    private void a(JSONObject jSONObject, int i, int i2) {
        try {
            e(jSONObject, i, i2);
        } catch (JSONException e) {
            LogUtil.b("HWhealthLinkage_", "buildJsonObject JSONException ", e.getMessage());
            sqo.w("buildJsonObject JSONException" + e.getMessage());
        }
    }

    private void e(JSONObject jSONObject, int i, int i2) throws JSONException {
        jSONObject.put("sport_type", i);
        jSONObject.put("operator_type", i2);
        jSONObject.put(HwExerciseConstants.JSON_NAME_WORKOUT_TYPE, this.m);
        jSONObject.put("operation_time", System.currentTimeMillis());
        if (dum.d().f()) {
            jSONObject.put("start_time", this.l);
            jSONObject.put("version", this.f);
            int i3 = this.r;
            if (i3 == 1) {
                jSONObject.put("forbid_pause", i3);
            }
            jSONObject.put("run_course_version", this.n);
        }
    }
}
