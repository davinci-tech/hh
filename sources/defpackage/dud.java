package defpackage;

import android.os.Bundle;
import com.huawei.health.device.api.IndoorEquipManagerApi;
import com.huawei.health.devicemgr.api.constant.GetDeviceInfoMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.suggestion.CoachController;
import com.huawei.health.suggestion.fit.callback.CourseLinkageLifecycle;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseConstants;
import com.huawei.hwfoundationmodel.trackmodel.ISportDataCallback;
import com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class dud {
    private static final Object e = new Object();

    /* renamed from: a, reason: collision with root package name */
    protected int f11837a = 0;
    protected long d = 0;
    private ISportDataCallback i = new ISportDataCallback() { // from class: dud.4
        @Override // com.huawei.hwfoundationmodel.trackmodel.ISportDataCallback
        public void onSummary(MotionPathSimplify motionPathSimplify) {
        }

        @Override // com.huawei.hwfoundationmodel.trackmodel.ISportDataCallback
        public void getSportInfo(Bundle bundle) {
            synchronized (dud.e) {
                if (bundle != null) {
                    dud.this.ZY_(bundle);
                } else {
                    ReleaseLogUtil.c("HWhealthLinkage_HealthFitnessInteractor", "sportDataCallback getSportInfo sportInfo is null");
                }
            }
        }
    };
    protected CourseLinkageLifecycle c = new CourseLinkageLifecycle() { // from class: dud.2
        @Override // com.huawei.health.suggestion.fit.callback.CourseLinkageLifecycle
        public void start(int i, Bundle bundle) {
            dud.this.f11837a = i;
            if (bundle != null) {
                dud.this.d = bundle.getLong("startSportTime");
                ReleaseLogUtil.e("HWhealthLinkage_HealthFitnessInteractor", " startTime:", Long.valueOf(dud.this.d));
            }
            dud.this.i();
        }

        @Override // com.huawei.health.suggestion.fit.callback.CourseLinkageLifecycle
        public void resume(int i) {
            ReleaseLogUtil.e("HWhealthLinkage_HealthFitnessInteractor", "mCourseLinkageLifecycle app resume, sportType:", Integer.valueOf(i));
            dud.this.f11837a = i;
            dud.this.h();
        }

        @Override // com.huawei.health.suggestion.fit.callback.CourseLinkageLifecycle
        public void pause(int i) {
            ReleaseLogUtil.e("HWhealthLinkage_HealthFitnessInteractor", "mCourseLinkageLifecycle app pause, sportType:", Integer.valueOf(i));
            dud.this.f11837a = i;
            dud.this.f();
        }

        @Override // com.huawei.health.suggestion.fit.callback.CourseLinkageLifecycle
        public void stop(int i) {
            ReleaseLogUtil.e("HWhealthLinkage_HealthFitnessInteractor", "mCourseLinkageLifecycle app stop, sportType:", Integer.valueOf(i));
            dud.this.f11837a = i;
            dud.this.j();
        }
    };
    private IBaseResponseCallback h = new IBaseResponseCallback() { // from class: dud.1
        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            ReleaseLogUtil.e("HWhealthLinkage_HealthFitnessInteractor", "setOperatorHelperCallback setOperator err_code is ", Integer.valueOf(i));
            if (i == 100000) {
                dum.d().c(true);
                CoachController.d().d(true);
            } else {
                dum.d().c(false);
                CoachController.d().d(false);
            }
        }
    };
    private IBaseResponseCallback g = new IBaseResponseCallback() { // from class: dud.3
        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            ReleaseLogUtil.e("HWhealthLinkage_HealthFitnessInteractor", "setOperatorCallback setOperator err_code is ", Integer.valueOf(i));
            if (i == 100000) {
                dum.d().c(true);
                dul.c().j();
                CoachController.d().d(true);
            } else {
                dum.d().c(false);
                CoachController.d().d(false);
            }
        }
    };
    private IBaseResponseCallback b = new IBaseResponseCallback() { // from class: dud.5
        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            LogUtil.c("HealthFitnessInteractor", "deliverRealTimeSportData err_code is ", Integer.valueOf(i));
        }
    };

    public void c() {
        ReleaseLogUtil.e("HWhealthLinkage_HealthFitnessInteractor", "reset fitness interactor.");
        CoachController.d().c();
        this.f11837a = 0;
        this.d = 0L;
    }

    protected void i() {
        b(this.f11837a, 1);
        if (dum.d().f()) {
            dul.c().e();
        }
    }

    protected void f() {
        b(this.f11837a, 2);
        dul.c().b();
        if (dum.d().f()) {
            dul.c().a();
        }
    }

    protected void h() {
        b(this.f11837a, 3);
        dul.c().j();
        if (dum.d().f()) {
            dul.c().e();
        }
    }

    protected void j() {
        dub.a().e();
        b(this.f11837a, 4);
        dul.c().b();
        if (dum.d().f()) {
            dul.c().a();
        }
    }

    protected void a() {
        CoachController.d().e(this.i);
        kzc.n().d(this.i);
        dul.c().b(new IBaseResponseCallback() { // from class: dud.8
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                ReleaseLogUtil.e("HWhealthLinkage_HealthFitnessInteractor", "registerBluetoothStateListener invoke, bluetoothState = ", Integer.valueOf(i));
                if (dum.d().f() && i == 4) {
                    synchronized (dud.e) {
                        dum.d().c(true);
                        int a2 = CoachController.d().a();
                        ReleaseLogUtil.e("HWhealthLinkage_HealthFitnessInteractor", "startSportTime=", Long.valueOf(dud.this.d), " currentState=", Integer.valueOf(a2), " linkStatus=", Boolean.valueOf(dum.d().h()));
                        if (a2 == 1) {
                            dud dudVar = dud.this;
                            dudVar.b(dudVar.f11837a, 5);
                            dul.c().e();
                        } else if (a2 == 2) {
                            dud dudVar2 = dud.this;
                            dudVar2.b(dudVar2.f11837a, 6);
                            dul.c().a();
                        } else if (a2 == 3) {
                            dud dudVar3 = dud.this;
                            dudVar3.b(dudVar3.f11837a, 4);
                            dul.c().a();
                        }
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean b(int i, int i2) {
        ReleaseLogUtil.e("HWhealthLinkage_HealthFitnessInteractor", "notifyWearDeviceState sportType = ", Integer.valueOf(i), ", sportState = ", Integer.valueOf(i2));
        boolean h = dum.d().h();
        if (i2 != 1 && !h) {
            ReleaseLogUtil.d("HWhealthLinkage_HealthFitnessInteractor", "failed to linkage.");
            return false;
        }
        if (i2 == 1 || h) {
            ReleaseLogUtil.e("HWhealthLinkage_HealthFitnessInteractor", "notifyWearDeviceState is linking");
            JSONObject jSONObject = new JSONObject();
            d(jSONObject, i, i2);
            if (i2 == 1 || i2 == 3) {
                dul.c().a(jSONObject, this.g);
                DeviceInfo deviceInfo = cun.c().getDeviceInfo(GetDeviceInfoMode.CONNECTED_MAIN_DEVICES_WITHOUT_AW70, null, "HealthFitnessInteractor");
                if (dwh.b(deviceInfo) && i2 == 3 && CoachController.d().b().getCourseSleepTime() > 0) {
                    dwh.a(CoachController.d().b(), 7, deviceInfo);
                }
            } else {
                DeviceInfo deviceInfo2 = cun.c().getDeviceInfo(GetDeviceInfoMode.CONNECTED_MAIN_DEVICES_WITHOUT_AW70, null, "HealthFitnessInteractor");
                if (dwh.b(deviceInfo2)) {
                    ReleaseLogUtil.e("HWhealthLinkage_HealthFitnessInteractor", "recover course link");
                    dwh.d(CoachController.d().b(), deviceInfo2);
                }
                dul.c().a(jSONObject, this.h);
            }
        }
        return true;
    }

    private void d(JSONObject jSONObject, int i, int i2) {
        try {
            jSONObject.put("sport_type", 1);
            jSONObject.put(HwExerciseConstants.JSON_NAME_WORKOUT_TYPE, i);
            jSONObject.put("operator_type", i2);
            jSONObject.put("operation_time", System.currentTimeMillis());
            if (dum.d().f()) {
                jSONObject.put("start_time", this.d);
                jSONObject.put("version", 1);
            }
        } catch (JSONException e2) {
            ReleaseLogUtil.c("HWhealthLinkage_HealthFitnessInteractor", "buildJsonObject JSONException ", LogAnonymous.b((Throwable) e2));
            sqo.w("buildJsonObject JSONException：" + e2.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ZY_(Bundle bundle) {
        if (dum.d().j() && dum.d().h()) {
            JSONObject jSONObject = new JSONObject();
            ZW_(bundle, jSONObject);
            if (dum.d().f()) {
                ZX_(bundle, jSONObject);
                LogUtil.c("HealthFitnessInteractor", "sportInfo:", jSONObject.toString());
            }
            dul.c().e(jSONObject, this.b);
        }
    }

    private void ZW_(Bundle bundle, JSONObject jSONObject) {
        if (bundle == null) {
            ReleaseLogUtil.c("HWhealthLinkage_HealthFitnessInteractor", "addOldSportData sportInfo is null");
            return;
        }
        try {
            jSONObject.put("exercise_duration", bundle.getInt("duration"));
            jSONObject.put("calorie", bundle.getInt("calorie"));
            jSONObject.put("distance", bundle.getInt("distance"));
            jSONObject.put("pace", bundle.getInt("pace"));
            jSONObject.put("speed", bundle.getFloat("speed"));
            jSONObject.put(HwExerciseConstants.JSON_NAME_ACTIVECALORIE, bundle.getInt(HwExerciseConstants.JSON_NAME_ACTIVECALORIE));
        } catch (JSONException e2) {
            ReleaseLogUtil.c("HWhealthLinkage_HealthFitnessInteractor", "add old sport data:", LogAnonymous.b((Throwable) e2));
            sqo.w("add old sport data：" + e2.getMessage());
        }
    }

    private void ZX_(Bundle bundle, JSONObject jSONObject) {
        if (bundle == null) {
            ReleaseLogUtil.c("HWhealthLinkage_HealthFitnessInteractor", "addNewSportData sportInfo is null");
            return;
        }
        try {
            jSONObject.put("hr", bundle.getInt(IndoorEquipManagerApi.KEY_HEART_RATE));
            jSONObject.put("aerobic_te", bundle.getInt("aerobicExercise"));
            jSONObject.put("anaerobic_te", bundle.getInt("anaerobicExercise"));
            jSONObject.put("performance_condition", bundle.getInt("performanceIndicator"));
            jSONObject.put("operator_type", bundle.getInt("sportState"));
        } catch (JSONException e2) {
            ReleaseLogUtil.c("HWhealthLinkage_HealthFitnessInteractor", "add new sport data:", LogAnonymous.b((Throwable) e2));
            sqo.w("add new sport data：" + e2.getMessage());
        }
    }
}
