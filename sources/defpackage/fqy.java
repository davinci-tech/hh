package defpackage;

import android.content.Intent;
import android.os.BadParcelableException;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.huawei.basefitnessadvice.model.intplan.IntPlan;
import com.huawei.health.plan.api.PlanApi;
import com.huawei.health.sport.model.WorkoutRecord;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hms.network.embedded.x2;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.WebViewHelp;
import health.compact.a.LogAnonymous;
import health.compact.a.Services;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public class fqy {

    /* renamed from: a, reason: collision with root package name */
    private String f12615a;
    private float aa;
    private int ab;
    private String ad;
    private String ae;
    private int d;
    private boolean h;
    private boolean i;
    private boolean n;
    private String r;
    private long s;
    private String u;
    private int w;
    private ArrayList<WorkoutRecord> y;
    private int z;
    private String ag = "";
    private String ac = "";
    private boolean f = false;
    private boolean k = false;
    private int m = -1;
    private String e = "otherModel";
    private boolean j = false;
    private String p = "";
    private String v = "";
    private String x = "";
    private String t = "";
    private String b = "";
    private String q = "";
    private String o = "";
    private String c = "";
    private boolean g = false;
    private boolean l = false;

    public fqy(Intent intent) {
        try {
            if (intent == null) {
                ReleaseLogUtil.e("Suggestion_TrainDetailIntentData", "TrainDetailIntentData intent null");
                return;
            }
            aEE_(intent);
            aEF_(intent);
            aEH_(intent);
            aEG_(intent);
            aEC_(intent);
            aED_(intent);
        } catch (BadParcelableException e) {
            LogUtil.b("Suggestion_TrainDetailIntentData", "get intent data failed with BadParcelableException", LogAnonymous.b((Throwable) e));
        } catch (ArrayIndexOutOfBoundsException e2) {
            LogUtil.b("Suggestion_TrainDetailIntentData", "get intent data failed with ArrayIndexOutOfBoundsException", LogAnonymous.b((Throwable) e2));
        }
    }

    private void aED_(Intent intent) {
        Bundle bundleExtra = intent.getBundleExtra("bundlekey");
        if (bundleExtra != null) {
            this.n = bundleExtra.getBoolean("isshowbutton");
            this.w = bundleExtra.getInt("track_type");
            this.ab = bundleExtra.getInt("track_target");
            this.aa = bundleExtra.getFloat("track_targetvalue");
        }
    }

    private void aEC_(Intent intent) {
        this.p = intent.getStringExtra(WebViewHelp.BI_KEY_PULL_FROM);
        this.v = intent.getStringExtra("resourceId");
        this.x = intent.getStringExtra("resourceName");
        this.t = intent.getStringExtra("pullOrder");
        this.b = intent.getStringExtra("algId");
        this.q = intent.getStringExtra("promoteTag");
        this.c = intent.getStringExtra(x2.AB_INFO);
        this.o = intent.getStringExtra("itemId");
        if (koq.c(this.y)) {
            if (TextUtils.isEmpty(this.y.get(0).acquirePlanId())) {
                LogUtil.h("Suggestion_TrainDetailIntentData", "getBiIntentData mPlanId null");
                return;
            }
            this.e = "10";
            PlanApi planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
            if (planApi == null) {
                LogUtil.b("Suggestion_TrainDetailIntentData", "bindRunPlanInfo, getCurrentPlan : planApi is null.");
                return;
            }
            IntPlan currentIntPlan = planApi.getCurrentIntPlan();
            if (currentIntPlan == null || currentIntPlan.getMetaInfo() == null) {
                return;
            }
            this.q = currentIntPlan.getMetaInfo().getName();
        }
    }

    private void aEE_(Intent intent) {
        this.ac = intent.getStringExtra("version");
        this.ag = intent.getStringExtra("workoutid");
        this.z = intent.getIntExtra("userDefinedType", 0);
        this.l = intent.getBooleanExtra("isSendCourseDevice", false);
        Bundle aPD_ = gnp.aPD_(intent.getStringExtra("stretch"));
        if (aPD_ != null) {
            try {
                this.y = (ArrayList) HiJsonUtil.b(aPD_.getString("workoutrecord"), new TypeToken<List<WorkoutRecord>>() { // from class: fqy.4
                }.getType());
            } catch (JsonSyntaxException unused) {
                LogUtil.b("Suggestion_TrainDetailIntentData", "parse WorkoutRecord error");
            }
        } else {
            try {
                this.y = intent.getParcelableArrayListExtra("workoutrecord");
            } catch (ArrayIndexOutOfBoundsException unused2) {
                LogUtil.b("Suggestion_TrainDetailIntentData", "getPassedData ArrayIndexOutOfBoundsException");
            }
        }
        if (!koq.e((Object) this.y, WorkoutRecord.class)) {
            this.y = new ArrayList<>();
        }
        if (TextUtils.isEmpty(this.ag) && koq.c(this.y)) {
            this.ag = this.y.get(0).acquireWorkoutId();
            this.ac = this.y.get(0).acquireVersion();
        }
        this.i = intent.getBooleanExtra("isafterrun", false);
        this.f = intent.getBooleanExtra("ISFROMSCHEME", false);
        this.k = intent.getBooleanExtra("isNeedExecutImmediate", false);
        this.m = intent.getIntExtra("executImmediateErrorType", -1);
        this.s = intent.getLongExtra("plan_execute_time", 0L);
        this.j = intent.getBooleanExtra("finish", false);
        this.g = intent.getBooleanExtra("moveTaskToBack", false);
        this.h = intent.getBooleanExtra("ISPLANFIT", false);
        this.ad = intent.getStringExtra("topic_name");
    }

    private void aEF_(Intent intent) {
        String stringExtra = intent.getStringExtra("entrance");
        if (TextUtils.isEmpty(stringExtra)) {
            return;
        }
        this.e = stringExtra;
    }

    private void aEH_(Intent intent) {
        this.f12615a = intent.getStringExtra("entrance_type");
        this.ae = intent.getStringExtra("workout_package_id");
        this.u = intent.getStringExtra("resource_type");
        if (TextUtils.isEmpty(this.ae)) {
            this.d = 0;
        } else {
            this.d = 1;
        }
    }

    private void aEG_(Intent intent) {
        this.u = intent.getStringExtra("resource_type");
        this.r = intent.getStringExtra("planTempId");
    }

    public boolean aa() {
        return this.i;
    }

    public boolean ab() {
        return this.f;
    }

    public boolean ag() {
        return this.k;
    }

    public int f() {
        return this.m;
    }

    public long j() {
        return this.s;
    }

    public int v() {
        return this.z;
    }

    public String i() {
        return this.f12615a;
    }

    public String a() {
        return this.e;
    }

    public String p() {
        return this.u;
    }

    public String x() {
        return this.ae;
    }

    public int c() {
        return this.d;
    }

    public String g() {
        return this.r;
    }

    public String w() {
        return this.ag;
    }

    public String y() {
        return this.ac;
    }

    public ArrayList<WorkoutRecord> m() {
        return this.y;
    }

    public boolean z() {
        return this.j;
    }

    public String k() {
        return this.p;
    }

    public String o() {
        return this.v;
    }

    public String r() {
        return this.x;
    }

    public String n() {
        return this.t;
    }

    public String b() {
        return TextUtils.isEmpty(this.b) ? "" : this.b;
    }

    public String l() {
        return this.q;
    }

    public String e() {
        return this.c;
    }

    public String h() {
        return this.o;
    }

    public boolean ad() {
        return this.g;
    }

    public boolean af() {
        return this.l;
    }

    public void c(String str) {
        this.p = str;
    }

    public boolean ac() {
        return this.h;
    }

    public String q() {
        return this.ad;
    }

    public boolean ai() {
        return this.n;
    }

    public int s() {
        return this.w;
    }

    public int t() {
        return this.ab;
    }

    public float u() {
        return this.aa;
    }

    public Map<String, Object> d() {
        HashMap hashMap = new HashMap(6);
        if (!TextUtils.isEmpty(this.p)) {
            hashMap.put(WebViewHelp.BI_KEY_PULL_FROM, this.p);
        }
        if (!TextUtils.isEmpty(this.v)) {
            hashMap.put("resourceId", this.v);
        }
        if (!TextUtils.isEmpty(this.x)) {
            hashMap.put("resourceName", this.x);
        }
        if (!TextUtils.isEmpty(this.t)) {
            hashMap.put("pullOrder", this.t);
        }
        if (!TextUtils.isEmpty(this.b)) {
            hashMap.put("algId", this.b);
        }
        if (!TextUtils.isEmpty(this.q)) {
            hashMap.put("promoteTag", this.q);
        }
        if (!TextUtils.isEmpty(this.c)) {
            hashMap.put(x2.AB_INFO, this.c);
        }
        if (!TextUtils.isEmpty(this.o)) {
            hashMap.put("itemId", this.o);
        }
        return hashMap;
    }

    public String toString() {
        return "mWorkoutId=" + this.ag + " mVersion=" + this.ac + " mIsAfterRun=" + this.i + " mIsFromScheme=" + this.f + " mIsNeedExecuteImmediate=" + this.k + " mNotDownloadBroadcastType=" + this.m + " mPlanStartTime=" + this.s + " mUserDefinedType=" + this.z + " mEntranceType=" + this.f12615a + " mWorkoutPackageId=" + this.ae + " mResourceType=" + this.u + " mCourseBelongType=" + this.d + " mPlanTempId=" + this.r + " mEntranceStr=" + this.e + " mIsFinish=" + this.j + " mPullFrom=" + this.p + " mResourceId=" + this.v + " mResourceName=" + this.x + " mPullOrder=" + this.t + " mAlgId=" + this.b + " mPromoteTag=" + this.q + " mItemId=" + this.o + " mAbInfo=" + this.c + " mIsFitPlan=" + this.h + " mTopicName=" + this.ad + " mTackType=" + this.w + " mTrackTarget=" + this.ab + " mTrackTargetValue=" + this.aa;
    }
}
