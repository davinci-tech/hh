package defpackage;

import android.os.Bundle;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.basefitnessadvice.model.intplan.IntPlan;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.plan.api.PlanApi;
import com.huawei.health.weight.bean.WeightTargetDifferences;
import com.huawei.hms.support.feature.result.CommonConstant;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.up.model.UserInfomation;
import defpackage.qzw;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.Services;
import health.compact.a.Utils;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes7.dex */
public class qzw {
    public static void e(final ResponseCallback<gse> responseCallback) {
        if (responseCallback == null) {
            ReleaseLogUtil.a("HealthWeight_WeightRateHelper", "getGoalDetail callback is null");
            return;
        }
        if (HandlerExecutor.b()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: raf
                @Override // java.lang.Runnable
                public final void run() {
                    qzw.e(ResponseCallback.this);
                }
            });
            return;
        }
        UserInfomation c = gni.c();
        if (c == null) {
            ReleaseLogUtil.a("HealthWeight_WeightRateHelper", "getGoalDetail userInfomation is null");
            responseCallback.onResponse(-1, null);
            return;
        }
        qvr qvrVar = new qvr();
        qvrVar.e(c.getAge());
        qvrVar.c(c.getGender());
        qvrVar.b(c.getHeight() == 0 ? 170 : c.getHeight());
        qvrVar.c(c.getWeight() < 10.0f ? 65.0d : c.getWeight());
        kpp.d(new qvl(0, 2, qvrVar), new UiCallback<gse>() { // from class: qzw.1
            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            public void onFailure(int i, String str) {
                ResponseCallback.this.onResponse(i, null);
            }

            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public void onSuccess(gse gseVar) {
                ResponseCallback.this.onResponse(0, gseVar);
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void a(Map<String, Object> map, final UiCallback<gse> uiCallback) {
        qvr qvrVar = (qvr) map.get("userInfo");
        IntPlan intPlan = (IntPlan) map.get("intPlan");
        double doubleValue = ((map.get("goalWeight") instanceof Double) || (map.get("goalWeight") instanceof Float)) ? Double.valueOf(String.valueOf(map.get("goalWeight"))).doubleValue() : 0.0d;
        int intValue = map.get("choice") instanceof Number ? ((Number) map.get("choice")).intValue() : 0;
        int booleanValue = map.get("targetSettingChanged") instanceof Boolean ? ((Boolean) map.get("targetSettingChanged")).booleanValue() : 0;
        boolean booleanValue2 = map.get("getIntPlan") instanceof Boolean ? ((Boolean) map.get("getIntPlan")).booleanValue() : false;
        final qvk qvkVar = new qvk();
        qvkVar.e(0);
        qvkVar.b(2);
        qvkVar.a(1);
        qvkVar.c(0);
        qvkVar.d(DateFormatUtil.b(System.currentTimeMillis()));
        qvi qviVar = new qvi();
        qviVar.e(doubleValue);
        qviVar.b(booleanValue);
        qviVar.d(intValue);
        qvkVar.b(qviVar);
        qvkVar.e(qvrVar);
        PlanApi planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
        if (planApi != null) {
            if (intPlan == null && !booleanValue2) {
                planApi.b(new UiCallback<IntPlan>() { // from class: qzw.3
                    @Override // com.huawei.basefitnessadvice.callback.UiCallback
                    public void onFailure(int i, String str) {
                        qzw.e(qvk.this, 0, false, uiCallback);
                    }

                    @Override // com.huawei.basefitnessadvice.callback.UiCallback
                    /* renamed from: d, reason: merged with bridge method [inline-methods] */
                    public void onSuccess(IntPlan intPlan2) {
                        qzw.b(qvk.this, intPlan2, (UiCallback<gse>) uiCallback);
                    }
                });
            } else {
                b(qvkVar, intPlan, uiCallback);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(qvk qvkVar, IntPlan intPlan, UiCallback<gse> uiCallback) {
        if (intPlan != null && intPlan.getPlanType() == IntPlan.PlanType.AI_FITNESS_PLAN) {
            int h = ase.h(intPlan);
            int d = ase.d(intPlan);
            int d2 = jdl.d(h, d);
            LogUtil.a("HealthWeight_WeightRateHelper", "calculateGoal onSuccess startDate ", Integer.valueOf(h), " endDate ", Integer.valueOf(d), " planDays ", Integer.valueOf(d2));
            e(qvkVar, d2, true, uiCallback);
            return;
        }
        LogUtil.h("HealthWeight_WeightRateHelper", "getCurrentIntPlan onSuccess intPlan is not exist");
        e(qvkVar, 0, false, uiCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void e(qvk qvkVar, int i, boolean z, UiCallback<gse> uiCallback) {
        LogUtil.a("HealthWeight_WeightRateHelper", "calculateGoal createGoalReq ", qvkVar);
        qvr h = qvkVar.h();
        if (h.c() < 10.0d) {
            h.c(65.0d);
        }
        if (h.d() == 0) {
            h.b(170);
        }
        if (!z) {
            kpp.d(qvkVar, uiCallback);
            return;
        }
        qvo qvoVar = new qvo();
        qvoVar.f(i);
        d(qvkVar, qvoVar);
        kpp.d(qvoVar, uiCallback);
    }

    private static void d(qvk qvkVar, qvo qvoVar) {
        if (qvkVar == null || qvoVar == null) {
            return;
        }
        qvoVar.b(qvkVar.a());
        qvoVar.e(qvkVar.h());
        qvoVar.e(qvkVar.c());
        qvoVar.b(qvkVar.d());
        qvoVar.c(qvkVar.b());
        qvoVar.d(qvkVar.e());
        qvoVar.a(qvkVar.i());
    }

    public static void dJr_(final Bundle bundle, final ResponseCallback<gsi> responseCallback) {
        if (bundle == null) {
            ReleaseLogUtil.a("HealthWeight_WeightRateHelper", "calculateGoal bundle is null callback ", responseCallback);
            return;
        }
        if (HandlerExecutor.b()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: rac
                @Override // java.lang.Runnable
                public final void run() {
                    qzw.dJr_(bundle, responseCallback);
                }
            });
            return;
        }
        final int i = bundle.getInt("weightManagerType");
        double d = bundle.getDouble("weight");
        final double d2 = bundle.getDouble("targetWeight");
        double d3 = bundle.getDouble("startWeight");
        if (d3 <= 0.0d) {
            ReleaseLogUtil.a("HealthWeight_WeightRateHelper", "calculateGoal sourceStartWeight ", Double.valueOf(d3));
            d3 = d;
        }
        final int i2 = bundle.getInt("fatBurnChoice");
        final long j = bundle.getLong("modifiedTime");
        final boolean z = bundle.getBoolean("isRefreshInitWeight");
        if (!Utils.i()) {
            ReleaseLogUtil.b("HealthWeight_WeightRateHelper", "calculateGoal ifAllowLogin false");
            final double d4 = d3;
            ThreadPoolManager.d().execute(new Runnable() { // from class: rah
                @Override // java.lang.Runnable
                public final void run() {
                    double d5 = d4;
                    kpu.c(kpu.d((gse) null, (float) d5, i, i2), r4, z, (ResponseCallback<gsi>) new ResponseCallback() { // from class: raa
                        @Override // com.huawei.hwbasemgr.ResponseCallback
                        public final void onResponse(int i3, Object obj) {
                            qzw.dJv_(ResponseCallback.this, r2, r4, r6, i3, (gsi) obj);
                        }
                    });
                }
            });
            return;
        }
        if (i != 1) {
            dJx_(bundle);
            kpu.e(d2, j, null);
            kpu.c(kpu.d((gse) null, (float) d3, i, i2), j, z, responseCallback);
            return;
        }
        int i3 = bundle.getInt("age");
        int i4 = bundle.getInt(CommonConstant.KEY_GENDER);
        int i5 = bundle.getInt("height");
        IntPlan intPlan = (IntPlan) bundle.getSerializable("intPlan");
        boolean z2 = bundle.getBoolean("getIntPlan");
        double d5 = d3;
        boolean z3 = bundle.getBoolean("waitSaveWeight", false);
        qvr qvrVar = new qvr();
        qvrVar.e(i3);
        qvrVar.c(i4);
        qvrVar.b(i5);
        qvrVar.c(d);
        boolean z4 = bundle.getBoolean("targetSettingChanged", true);
        HashMap hashMap = new HashMap();
        hashMap.put("userInfo", qvrVar);
        hashMap.put("goalWeight", Double.valueOf(d2));
        hashMap.put("choice", Integer.valueOf(i2));
        hashMap.put("targetSettingChanged", Boolean.valueOf(z4));
        hashMap.put("intPlan", intPlan);
        hashMap.put("getIntPlan", Boolean.valueOf(z2));
        a(hashMap, new AnonymousClass4(responseCallback, bundle, d5, i, i2, Boolean.valueOf(z3), j, d2, z));
    }

    static /* synthetic */ void dJv_(final ResponseCallback responseCallback, double d, long j, final Bundle bundle, int i, final gsi gsiVar) {
        LogUtil.a("HealthWeight_WeightRateHelper", "calculateGoal errorCode ", Integer.valueOf(i), " weightManager ", gsiVar);
        if (i != 0) {
            responseCallback.onResponse(i, gsiVar);
        } else {
            kpu.e(d, j, new ResponseCallback() { // from class: qzx
                @Override // com.huawei.hwbasemgr.ResponseCallback
                public final void onResponse(int i2, Object obj) {
                    ThreadPoolManager.d().execute(new Runnable() { // from class: qzu
                        @Override // java.lang.Runnable
                        public final void run() {
                            qzw.dJt_(r1, i2, r3, r4, r5);
                        }
                    });
                }
            });
        }
    }

    static /* synthetic */ void dJt_(Bundle bundle, int i, Double d, ResponseCallback responseCallback, gsi gsiVar) {
        WeightTargetDifferences dJx_ = dJx_(bundle);
        LogUtil.a("HealthWeight_WeightRateHelper", "calculateGoal resultCode ", Integer.valueOf(i), " doubleData ", d, " weightTargetDifferences ", dJx_);
        if (dJx_ == null) {
            responseCallback.onResponse(-1, gsiVar);
        } else {
            responseCallback.onResponse(i, gsiVar);
        }
    }

    /* renamed from: qzw$4, reason: invalid class name */
    class AnonymousClass4 extends UiCallback<gse> {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ boolean f16671a;
        final /* synthetic */ long b;
        final /* synthetic */ int c;
        final /* synthetic */ Bundle d;
        final /* synthetic */ ResponseCallback e;
        final /* synthetic */ double g;
        final /* synthetic */ int h;
        final /* synthetic */ double i;
        final /* synthetic */ Boolean j;

        AnonymousClass4(ResponseCallback responseCallback, Bundle bundle, double d, int i, int i2, Boolean bool, long j, double d2, boolean z) {
            this.e = responseCallback;
            this.d = bundle;
            this.g = d;
            this.h = i;
            this.c = i2;
            this.j = bool;
            this.b = j;
            this.i = d2;
            this.f16671a = z;
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        public void onFailure(int i, String str) {
            ReleaseLogUtil.a("HealthWeight_WeightRateHelper", "calculateGoal onFailure errorCode ", Integer.valueOf(i), " errorInfo ", str);
            this.e.onResponse(i, null);
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onSuccess(gse gseVar) {
            gsg b;
            LogUtil.a("HealthWeight_WeightRateHelper", "calculateGoal onSuccess goalDetail ", gseVar);
            if (gseVar != null && (b = gseVar.b()) != null) {
                this.d.putInt(TypedValues.CycleType.S_WAVE_PERIOD, b.e());
            }
            final gsi d = kpu.d(gseVar, (float) this.g, this.h, this.c);
            if (this.j.booleanValue()) {
                this.e.onResponse(0, d);
            }
            ThreadPoolManager d2 = ThreadPoolManager.d();
            final Bundle bundle = this.d;
            final long j = this.b;
            final double d3 = this.i;
            final Boolean bool = this.j;
            final boolean z = this.f16671a;
            final ResponseCallback responseCallback = this.e;
            d2.execute(new Runnable() { // from class: rad
                @Override // java.lang.Runnable
                public final void run() {
                    qzw.AnonymousClass4.dJy_(bundle, j, d3, bool, d, z, responseCallback);
                }
            });
        }

        static /* synthetic */ void dJy_(Bundle bundle, long j, double d, Boolean bool, gsi gsiVar, boolean z, ResponseCallback responseCallback) {
            bundle.putLong("targetBeginDate", j);
            qzw.dJx_(bundle);
            kpu.e(d, j, null);
            if (bool.booleanValue()) {
                kpu.c(gsiVar, j, z, (ResponseCallback<gsi>) null);
            } else {
                kpu.c(gsiVar, j, z, (ResponseCallback<gsi>) responseCallback);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static WeightTargetDifferences dJx_(Bundle bundle) {
        long time;
        double b;
        int i;
        if (bundle == null) {
            ReleaseLogUtil.a("HealthWeight_WeightRateHelper", "saveWeightTargetDifferences bundle is null");
            return null;
        }
        double d = bundle.getDouble("weight");
        double d2 = bundle.getDouble("targetWeight");
        int i2 = bundle.getInt("weightManagerType");
        long j = bundle.getLong("modifiedTime", System.currentTimeMillis());
        double d3 = d - d2;
        long j2 = 0;
        if (i2 == 1) {
            int i3 = bundle.getInt(TypedValues.CycleType.S_WAVE_PERIOD);
            if (i3 <= 0) {
                i3 = qsj.e(bundle.getInt("fatBurnChoice"), d, d2);
            }
            Calendar calendar = Calendar.getInstance();
            calendar.add(5, i3 - 1);
            time = calendar.getTime().getTime();
            b = rag.b(j, time, d3);
        } else if (i2 != 2) {
            b = 0.0d;
            time = 0;
        } else {
            long j3 = bundle.getLong("targetFinishDate");
            b = rag.b(j, j3, d3);
            time = j3;
        }
        if (b == 0.0d) {
            i = bundle.getInt("maintainTargetWeightRange");
        } else {
            i = 0;
            j2 = time;
        }
        long j4 = bundle.getLong("targetBeginDate");
        WeightTargetDifferences weightTargetDifferences = new WeightTargetDifferences(b, j4, j2, i);
        boolean b2 = rag.b(weightTargetDifferences);
        LogUtil.a("HealthWeight_WeightRateHelper", "saveWeightTargetDifferences weight ", Double.valueOf(d), " targetWeight ", Double.valueOf(d2), " weightManagerType ", Integer.valueOf(i2), " targetDifferences ", Double.valueOf(b), " targetBeginDate ", Long.valueOf(j4), " targetFinishDate ", Long.valueOf(j2), " maintainTargetWeightRange ", Integer.valueOf(i), " isSuccess ", Boolean.valueOf(b2));
        if (b2) {
            return weightTargetDifferences;
        }
        return null;
    }

    public static void b(gsi gsiVar, long j) {
        LogUtil.a("HealthWeight_WeightRateHelper", "saveWeightTargetDifferences weightManager ", gsiVar, " modifyTime ", Long.valueOf(j));
        if (gsiVar == null) {
            return;
        }
        int g = gsiVar.g();
        if (g == 0) {
            e(gsiVar, j);
        } else {
            if (g != 1) {
                return;
            }
            a(gsiVar, j);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void e(final gsi gsiVar, final long j) {
        LogUtil.a("HealthWeight_WeightRateHelper", "saveDifferencesWhenHold weightManager ", gsiVar, " modifyTime ", Long.valueOf(j));
        if (gsiVar == null) {
            return;
        }
        if (HandlerExecutor.b()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: qzy
                @Override // java.lang.Runnable
                public final void run() {
                    qzw.e(gsi.this, j);
                }
            });
            return;
        }
        if (gni.c() == null) {
            ReleaseLogUtil.a("HealthWeight_WeightRateHelper", "saveDifferencesWhenHold userInfomation is null");
            return;
        }
        float d = gsiVar.d();
        double pow = Math.pow(r3.getHeight() / 100.0d, 2.0d) * 24.0d;
        double d2 = d;
        double min = Math.min(pow, d2);
        double max = Math.max(pow, d2);
        int i = (int) (((max * 1000.0d) - (1000.0d * min)) / 2.0d);
        Bundle bundle = new Bundle();
        bundle.putLong("targetBeginDate", System.currentTimeMillis());
        bundle.putInt("maintainTargetWeightRange", i);
        bundle.putInt("weightManagerType", gsiVar.g());
        WeightTargetDifferences dJx_ = dJx_(bundle);
        LogUtil.a("HealthWeight_WeightRateHelper", "saveDifferencesWhenHold initWeight ", Float.valueOf(d), " bmi24ToWeight ", Double.valueOf(pow), " lowValue ", Double.valueOf(min), " highValue ", Double.valueOf(max), " maintainTargetWeightRange ", Integer.valueOf(i), " weightTargetDifferences ", dJx_);
        if (dJx_ == null) {
            return;
        }
        kpu.e((min + max) / 2.0d, j, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void a(final gsi gsiVar, final long j) {
        final gsb b;
        LogUtil.a("HealthWeight_WeightRateHelper", "saveDifferencesWhenLose weightManager ", gsiVar, " modifyTime ", Long.valueOf(j));
        if (gsiVar == null || (b = gsiVar.b()) == null) {
            return;
        }
        if (HandlerExecutor.b()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: qzt
                @Override // java.lang.Runnable
                public final void run() {
                    qzw.a(gsi.this, j);
                }
            });
        } else {
            ThreadPoolManager.d().execute(new Runnable() { // from class: qzv
                @Override // java.lang.Runnable
                public final void run() {
                    kot.a().c(new ResponseCallback() { // from class: rab
                        @Override // com.huawei.hwbasemgr.ResponseCallback
                        public final void onResponse(int i, Object obj) {
                            qzw.a(gsi.this, r2, r3, i, (Float) obj);
                        }
                    });
                }
            });
        }
    }

    static /* synthetic */ void a(final gsi gsiVar, final gsb gsbVar, final long j, int i, final Float f) {
        LogUtil.a("HealthWeight_WeightRateHelper", "saveDifferencesWhenLose resultCode ", Integer.valueOf(i), " weightGoal ", f);
        if (f == null) {
            return;
        }
        ThreadPoolManager.d().execute(new Runnable() { // from class: qzz
            @Override // java.lang.Runnable
            public final void run() {
                qzw.c(gsi.this, gsbVar, f, j);
            }
        });
    }

    static /* synthetic */ void c(gsi gsiVar, gsb gsbVar, Float f, long j) {
        float d = gsiVar.d();
        int a2 = gsbVar.a();
        Bundle bundle = new Bundle();
        bundle.putDouble("weight", d);
        bundle.putInt("fatBurnChoice", a2);
        bundle.putDouble("targetWeight", f.floatValue());
        bundle.putLong("modifiedTime", j);
        bundle.putLong("targetBeginDate", j);
        bundle.putInt("weightManagerType", gsiVar.g());
        LogUtil.a("HealthWeight_WeightRateHelper", "saveDifferencesWhenLose initWeight ", Float.valueOf(d), " gear ", Integer.valueOf(a2), " weightTargetDifferences ", dJx_(bundle));
    }
}
