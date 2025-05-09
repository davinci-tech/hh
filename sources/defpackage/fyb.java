package defpackage;

import android.content.Context;
import android.os.Bundle;
import com.huawei.basefitnessadvice.callback.DataCallback;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.basefitnessadvice.model.intplan.IntPlan;
import com.huawei.basefitnessadvice.model.intplan.StatInfo;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.router.AppRouter;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.course.api.CourseApi;
import com.huawei.health.courseplanservice.api.RecordApi;
import com.huawei.health.sport.model.WorkoutRecord;
import com.huawei.hihealth.HiUserPreference;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.kwy;
import health.compact.a.CommonUtil;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.Services;
import health.compact.a.UnitUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class fyb {
    private IntPlan b;
    private Context d;
    private CountDownLatch m;
    private HashMap<String, String> n = new HashMap<>();
    private long i = 0;
    private float f = 0.0f;
    private float g = 0.0f;
    private float h = 0.0f;
    private float j = 0.0f;

    /* renamed from: a, reason: collision with root package name */
    private long f12687a = 0;
    private long e = 0;
    private List<WorkoutRecord> c = new ArrayList();

    static /* synthetic */ float c(fyb fybVar, float f) {
        float f2 = fybVar.j + f;
        fybVar.j = f2;
        return f2;
    }

    static /* synthetic */ float d(fyb fybVar, double d2) {
        float f = (float) (fybVar.g + d2);
        fybVar.g = f;
        return f;
    }

    public fyb(IntPlan intPlan, Context context) {
        this.b = intPlan;
        this.d = context;
    }

    public void a(final UiCallback<HashMap<String, String>> uiCallback) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: fya
            @Override // java.lang.Runnable
            public final void run() {
                fyb.this.b(uiCallback);
            }
        });
    }

    /* synthetic */ void b(UiCallback uiCallback) {
        a();
        c();
        uiCallback.onSuccess(this.n);
    }

    private void a() {
        long c;
        long j;
        long currentTimeMillis = System.currentTimeMillis();
        long b = gib.b(currentTimeMillis);
        long d2 = gib.d(currentTimeMillis);
        IntPlan.PlanType planType = this.b.getPlanType();
        if (IntPlan.PlanType.AI_FITNESS_PLAN.equals(planType)) {
            c = DateFormatUtil.c(ase.b(this.b));
        } else {
            c = ase.c(this.b);
        }
        long j2 = c;
        long currentTimeMillis2 = System.currentTimeMillis();
        b(b, d2, 1);
        if (IntPlan.PlanType.AI_FITNESS_PLAN.equals(planType) || IntPlan.PlanType.FIT_PLAN.equals(planType)) {
            c(b, d2, 1);
            b(j2, currentTimeMillis2, 0);
            if (IntPlan.PlanType.AI_FITNESS_PLAN.equals(planType)) {
                j = DateFormatUtil.c(ase.h(this.b));
                a(j2);
            } else {
                j = ase.j(this.b);
            }
            c(j, jdl.e(currentTimeMillis2), 0);
            d();
            return;
        }
        if (IntPlan.PlanType.AI_RUN_PLAN.equals(planType) || IntPlan.PlanType.RUN_PLAN.equals(planType)) {
            b(b, d2);
            d(b, d2);
            h();
            i();
            g();
            return;
        }
        LogUtil.a("Suggestion_PlanShareManager", "plan type is NA_PLAN");
    }

    private void c() {
        if (this.b.getPlanType() == IntPlan.PlanType.AI_FITNESS_PLAN || this.b.getPlanType() == IntPlan.PlanType.FIT_PLAN) {
            if (koq.b(this.c)) {
                return;
            }
            e();
            int size = this.c.size();
            if (size == 1) {
                c(this.c);
                return;
            } else if (size == 2) {
                e(this.c);
                return;
            } else {
                b(this.c);
                return;
            }
        }
        if (this.b.getPlanType() == IntPlan.PlanType.AI_RUN_PLAN || this.b.getPlanType() == IntPlan.PlanType.RUN_PLAN) {
            if (koq.b(this.c)) {
                return;
            }
            if (this.c.size() == 1) {
                a(this.c);
                return;
            } else {
                d(this.c);
                return;
            }
        }
        LogUtil.a("Suggestion_PlanShareManager", "plan type is NA_PLAN");
    }

    public void d(HashMap<String, String> hashMap) {
        LogUtil.a("Suggestion_PlanShareManager", "handleSharePlanData...");
        Bundle bundle = new Bundle();
        ArrayList arrayList = new ArrayList(1);
        feb febVar = new feb();
        if (this.b.getPlanType().equals(IntPlan.PlanType.AI_FITNESS_PLAN) || this.b.getPlanType().equals(IntPlan.PlanType.FIT_PLAN)) {
            febVar.d(1157);
            febVar.e(R.drawable._2131428272_res_0x7f0b03b0);
            bundle.putInt("downLoadId", 401);
        } else if (this.b.getPlanType().equals(IntPlan.PlanType.AI_RUN_PLAN) || this.b.getPlanType().equals(IntPlan.PlanType.RUN_PLAN)) {
            bundle.putInt("downLoadId", 400);
            febVar.d(1153);
            febVar.e(R.drawable._2131431317_res_0x7f0b0f95);
        } else {
            LogUtil.a("Suggestion_PlanShareManager", "plan type is NA_PLAN");
        }
        arrayList.add(febVar);
        feh fehVar = new feh();
        fehVar.d(hashMap);
        bundle.putSerializable("shareWaterMarkData", fehVar);
        bundle.putSerializable("waterMarkIds", arrayList);
        bundle.putBoolean("isDownloadMarkFromCloud", true);
        bundle.putString("shareSource", "plan");
        AppRouter.b("/PluginSocialShare/CustomizeShareActivity").zF_(bundle).c(this.d);
    }

    private void b() {
        int b = (int) (((gib.b(System.currentTimeMillis()) / 86400000) - ((this.b.getPlanTimeInfo().getBeginDate() * 1000) / 86400000)) + 1);
        if (IntPlan.PlanType.AI_FITNESS_PLAN.equals(this.b.getPlanType())) {
            b = jdl.d(ase.h(this.b), DateFormatUtil.b(System.currentTimeMillis()));
        }
        int e = ase.e(this.b);
        if (b > e) {
            b = e;
        }
        String e2 = UnitUtil.e(b, 1, 0);
        String e3 = UnitUtil.e(e, 1, 0);
        String b2 = nsf.b(R.string._2130845622_res_0x7f021fb6, e2, e3);
        String replace = nsf.a(R.plurals._2130903481_res_0x7f0301b9, e, e2, e3).replace(b2, "");
        if (this.b.getPlanType() == IntPlan.PlanType.AI_FITNESS_PLAN || this.b.getPlanType() == IntPlan.PlanType.FIT_PLAN) {
            this.n.put("reduce_fat_complete_day", b2);
            this.n.put("reduce_fat_point_day", e2);
            this.n.put("reduce_fat_unit", replace);
        } else {
            this.n.put("running_complete_day", b2);
            this.n.put("running_point_day", e2);
            this.n.put("running_unit", replace);
        }
    }

    private void d(List<WorkoutRecord> list) {
        a(list);
        if (!koq.b(list) && list.size() >= 2) {
            this.n.put("running_second_name", list.get(1).acquireWorkoutName());
            this.n.put("running_second_value", Integer.toString(Math.round(list.get(1).getDuration() / 60000.0f)));
        } else {
            LogUtil.a("Suggestion_PlanShareManager", "twoRunCourseTemplate: workoutRecords is empty");
        }
        this.n.put("running_second_unit", this.d.getResources().getString(R.string._2130851571_res_0x7f0236f3));
    }

    private void a(List<WorkoutRecord> list) {
        if (!koq.b(list)) {
            this.n.put("running_first_name", list.get(0).acquireWorkoutName());
            this.n.put("running_first_value", Integer.toString(Math.round(list.get(0).getDuration() / 60000.0f)));
        } else {
            LogUtil.a("Suggestion_PlanShareManager", "oneRunCourseTemplate: workoutRecords is empty");
        }
        this.n.put("running_first_unit", this.d.getResources().getString(R.string._2130851571_res_0x7f0236f3));
    }

    private void g() {
        this.n.put("running_sport_name", UnitUtil.e(this.g, 1, 2));
        this.n.put("running_sport_name_unit", this.d.getResources().getString(R.string._2130851570_res_0x7f0236f2));
        this.n.put("running_sport_name_title", this.d.getResources().getString(R.string._2130848680_res_0x7f022ba8));
        this.n.put("running_calorie_name_unit", this.d.getResources().getString(R.string._2130848385_res_0x7f022a81));
        this.n.put("running_calorie_name_title", this.d.getResources().getString(R.string._2130847440_res_0x7f0226d0));
        this.n.put("running_calorie_name", Integer.toString(Math.round(this.j)));
    }

    private void i() {
        b();
        this.n.put("running_name", this.b.getMetaInfo().getName());
        this.n.put("running_distance_unit", this.d.getResources().getString(R.string._2130848676_res_0x7f022ba4));
        this.n.put("running_calorie_unit", this.d.getResources().getString(R.string._2130847441_res_0x7f0226d1));
        this.n.put("running_duration_unit", this.d.getResources().getString(R.string._2130848678_res_0x7f022ba6));
        String string = this.d.getResources().getString(R.string._2130850262_res_0x7f0231d6);
        this.n.put("running_first_value", string);
        this.n.put("running_second_value", string);
        this.n.put("running_sport_name", string);
        this.n.put("running_calorie_value", Integer.toString(Math.round(this.h)));
        this.n.put("running_duration_value", Integer.toString(Math.round(this.i / 60000.0f)));
        this.n.put("running_distance_value", UnitUtil.e(this.f, 1, 2));
    }

    private void b(List<WorkoutRecord> list) {
        e(list);
        if (!koq.b(list) && list.size() >= 3) {
            this.n.put("reduce_fat_third_name", list.get(2).acquireWorkoutName());
            this.n.put("reduce_fat_third_value", Integer.toString(Math.round(list.get(2).getDuration() / 60000.0f)));
        } else {
            LogUtil.h("Suggestion_PlanShareManager", "threeFitnessCourseTemplate: workoutRecords is empty");
        }
        this.n.put("reduce_fat_third_unit", this.d.getResources().getString(R.string._2130851571_res_0x7f0236f3));
    }

    private void e(List<WorkoutRecord> list) {
        c(list);
        if (!koq.b(list) && list.size() >= 2) {
            this.n.put("reduce_fat_second_name", list.get(1).acquireWorkoutName());
            this.n.put("reduce_fat_second_value", Integer.toString(Math.round(list.get(1).getDuration() / 60000.0f)));
        } else {
            LogUtil.h("Suggestion_PlanShareManager", "twoFitnessCourseTemplate: workoutRecords is empty");
        }
        this.n.put("reduce_fat_second_unit", this.d.getResources().getString(R.string._2130851571_res_0x7f0236f3));
    }

    private void c(List<WorkoutRecord> list) {
        if (!koq.b(list)) {
            this.n.put("reduce_fat_first_name", list.get(0).acquireWorkoutName());
            this.n.put("reduce_fat_first_value", Integer.toString(Math.round(list.get(0).getDuration() / 60000.0f)));
        } else {
            LogUtil.h("Suggestion_PlanShareManager", "oneFitnessCourseTemplate: workoutRecords is empty");
        }
        this.n.put("reduce_fat_first_unit", this.d.getResources().getString(R.string._2130851571_res_0x7f0236f3));
    }

    private void e() {
        this.n.put("reduce_fat_train", Integer.toString(Math.round(this.f12687a / 60000.0f)));
        this.n.put("reduce_fat_train_unit", this.d.getResources().getString(R.string._2130851571_res_0x7f0236f3));
        this.n.put("reduce_fat_train_title", this.d.getResources().getString(R.string._2130848679_res_0x7f022ba7));
        this.n.put("reduce_fat_calorie_value_unit", this.d.getResources().getString(R.string._2130848385_res_0x7f022a81));
        this.n.put("reduce_fat_calorie_value_title", this.d.getResources().getString(R.string._2130847440_res_0x7f0226d0));
    }

    private void d() {
        b();
        this.n.put("reduce_fat_name", this.b.getMetaInfo().getName());
        this.n.put("reduce_fat_calorie_unit", this.d.getResources().getString(R.string._2130847441_res_0x7f0226d1));
        this.n.put("reduce_fat_duration_unit", this.d.getResources().getString(R.string._2130848678_res_0x7f022ba6));
        String string = this.d.getResources().getString(R.string._2130850262_res_0x7f0231d6);
        this.n.put("reduce_fat_first_value", string);
        this.n.put("reduce_fat_second_value", string);
        this.n.put("reduce_fat_third_value", string);
        this.n.put("reduce_fat_train", string);
        this.n.put("lose_weight_value", string);
        this.n.put("reduce_fat_duration_value", Integer.toString(Math.round(this.e / 60000.0f)));
    }

    private void c(long j, long j2, int i) {
        LogUtil.a("Suggestion_PlanShareManager", "getFitnessConsumption:", Long.valueOf(j), " ", Long.valueOf(j2));
        List<quh> c = c(j, j2);
        float f = 0.0f;
        while (j <= j2) {
            quh d2 = d(c, j);
            if (d2 != null) {
                f += d2.d().e();
            }
            j = jdl.y(j);
        }
        if (i == 0) {
            this.n.put("reduce_fat_calorie_value", Integer.toString(Math.round(f)));
        } else {
            this.n.put("reduce_fat_day_calorie_value", Integer.toString(Math.round(f)));
        }
    }

    private quh d(List<quh> list, long j) {
        int b = DateFormatUtil.b(j);
        for (quh quhVar : list) {
            if (quhVar != null && quhVar.c() == b) {
                return quhVar;
            }
        }
        return null;
    }

    private List<quh> c(long j, long j2) {
        int b = DateFormatUtil.b(j);
        int b2 = DateFormatUtil.b(j2);
        final ArrayList arrayList = new ArrayList();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        ReleaseLogUtil.b("Suggestion_PlanShareManager", "getDietRecordList startTime ", Long.valueOf(j), " endTime ", Long.valueOf(j2), " startDate ", Integer.valueOf(b), " endDate ", Integer.valueOf(b2));
        grz.b(b, b2, new ResponseCallback() { // from class: fxy
            @Override // com.huawei.hwbasemgr.ResponseCallback
            public final void onResponse(int i, Object obj) {
                fyb.d(arrayList, countDownLatch, i, (List) obj);
            }
        });
        try {
            if (!countDownLatch.await(6000L, TimeUnit.MILLISECONDS)) {
                LogUtil.h("Suggestion_PlanShareManager", "getDietRecordList wait timeout");
            }
        } catch (InterruptedException unused) {
            LogUtil.b("Suggestion_PlanShareManager", "interrupted while waiting for getDietRecordList");
        }
        return arrayList;
    }

    static /* synthetic */ void d(List list, CountDownLatch countDownLatch, int i, List list2) {
        LogUtil.a("Suggestion_PlanShareManager", "getDietRecordList errorCode ", Integer.valueOf(i), " list ", list2);
        if (koq.c(list2) && ((quh) list2.get(0)) != null) {
            list.addAll(list2);
        }
        countDownLatch.countDown();
    }

    private void a(long j) {
        float a2 = grz.a();
        LogUtil.a("Suggestion_PlanShareManager", "current weight is ", Float.valueOf(a2));
        float c = c(j);
        LogUtil.a("Suggestion_PlanShareManager", "origin weight is ", Float.valueOf(c));
        double d2 = c - a2;
        if (d2 > 0.1d) {
            this.n.put("lose_weight_title", this.d.getResources().getString(R.string._2130848692_res_0x7f022bb4));
            if (UnitUtil.h()) {
                this.n.put("lose_weight_value", UnitUtil.e(UnitUtil.h(d2), 1, 1));
                this.n.put("lose_weight_unit", this.d.getResources().getString(R.string._2130843856_res_0x7f0218d0));
                this.n.put("lose_weight_title_and_unit", this.d.getResources().getString(R.string._2130848702_res_0x7f022bbe));
            } else {
                this.n.put("lose_weight_value", UnitUtil.e(d2, 1, 1));
                this.n.put("lose_weight_unit", this.d.getResources().getString(R.string._2130850532_res_0x7f0232e4));
                this.n.put("lose_weight_title_and_unit", this.d.getResources().getString(R.string._2130848693_res_0x7f022bb5));
            }
        }
    }

    private float c(long j) {
        HiUserPreference userPreference = HiHealthManager.d(BaseApplication.e()).getUserPreference("custom.start_weight_base");
        float j2 = userPreference != null ? CommonUtil.j(userPreference.getValue()) : 0.0f;
        return j2 < 1.0f ? grz.c(gib.b(j), gib.d(j)) : j2;
    }

    private void h() {
        StatInfo stat = this.b.getStat("distance");
        if (stat != null) {
            LogUtil.a("Suggestion_PlanShareManager", "ai run plan distance:", stat.getValue());
            this.f = ((Float) stat.getValue()).floatValue() / 1000.0f;
        }
        StatInfo stat2 = this.b.getStat("duration");
        if (stat2 != null) {
            LogUtil.a("Suggestion_PlanShareManager", "plan duration:", stat2.getValue());
            this.i = Math.round(((Float) stat2.getValue()).floatValue());
        }
        StatInfo stat3 = this.b.getStat("calorie");
        if (stat3 != null) {
            LogUtil.a("Suggestion_PlanShareManager", "plan calories:", stat3.getValue());
            this.h = moe.b(((Float) stat3.getValue()).floatValue());
        }
    }

    private void b(long j, long j2) {
        this.m = new CountDownLatch(1);
        asb.d().d(j, j2, new d());
        try {
            if (this.m.await(6000L, TimeUnit.MILLISECONDS)) {
                return;
            }
            LogUtil.h("Suggestion_PlanShareManager", "getHealthTrackData wait timeout");
        } catch (InterruptedException unused) {
            LogUtil.b("Suggestion_PlanShareManager", "interrupted while waiting for getHealthTrackData");
        }
    }

    private void d(long j, long j2) {
        CourseApi courseApi = (CourseApi) Services.a("CoursePlanService", CourseApi.class);
        if (courseApi == null) {
            LogUtil.h("Suggestion_PlanShareManager", "courseApi is null");
            return;
        }
        String j3 = gib.j(j);
        String j4 = gib.j(j2);
        LogUtil.a("Suggestion_PlanShareManager", "startDate:", j3, " endDate:", j4);
        for (WorkoutRecord workoutRecord : courseApi.getWorkoutRecords(this.b.getPlanId(), j3, j4)) {
            if (!workoutRecord.isFitnessRecordFromDevice() && !workoutRecord.isRunWorkout()) {
                this.j += moe.b(workoutRecord.acquireActualCalorie());
            }
        }
    }

    private void b(long j, long j2, int i) {
        RecordApi recordApi = (RecordApi) Services.a("CoursePlanService", RecordApi.class);
        if (recordApi == null) {
            LogUtil.h("Suggestion_PlanShareManager", "recordApi is null");
            return;
        }
        final ArrayList arrayList = new ArrayList();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        recordApi.acquireDetailFitnessRecords(new kwy.a().a(j).e(j2).d(), new IBaseResponseCallback() { // from class: fyb.5
            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Type inference failed for: r3v4, types: [java.util.List] */
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i2, Object obj) {
                ArrayList arrayList2 = new ArrayList();
                if (obj != null) {
                    try {
                        if (koq.e(obj, WorkoutRecord.class)) {
                            arrayList2 = (List) obj;
                        }
                    } catch (ClassCastException e) {
                        LogUtil.h("Suggestion_PlanShareManager", "getFitnessWorkoutData ", e.getMessage());
                    }
                }
                arrayList.addAll(arrayList2);
                countDownLatch.countDown();
            }
        });
        try {
            if (!countDownLatch.await(6000L, TimeUnit.MILLISECONDS)) {
                LogUtil.h("Suggestion_PlanShareManager", "acquireDetailFitnessRecord wait timeout");
            }
        } catch (InterruptedException unused) {
            LogUtil.b("Suggestion_PlanShareManager", "interrupted while waiting for acquireDetailFitnessRecord");
        }
        if (i == 1) {
            this.c = arrayList;
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            if (!((WorkoutRecord) it.next()).isFitnessRecordFromDevice()) {
                if (i == 1) {
                    this.f12687a += r8.getDuration();
                } else {
                    this.e += r8.getDuration();
                }
            }
        }
    }

    class d extends DataCallback {
        private d() {
        }

        @Override // com.huawei.basefitnessadvice.callback.DataCallback
        public void onFailure(int i, String str) {
            LogUtil.a("Suggestion_PlanShareManager", "RunDataCallback onFail,", Integer.valueOf(i), str);
            fyb.this.m.countDown();
        }

        @Override // com.huawei.basefitnessadvice.callback.DataCallback
        public void onSuccess(JSONObject jSONObject) {
            if (jSONObject == null) {
                LogUtil.b("Suggestion_PlanShareManager", "RunDataCallback null.");
                fyb.this.m.countDown();
                return;
            }
            LogUtil.a("Suggestion_PlanShareManager", "RunDataCallback onSuccess,", jSONObject.toString());
            double optDouble = jSONObject.optDouble("distance");
            double optDouble2 = jSONObject.optDouble("calorie");
            fyb.d(fyb.this, optDouble / 1000.0d);
            fyb.c(fyb.this, moe.b((float) optDouble2));
            fyb.this.m.countDown();
        }
    }
}
