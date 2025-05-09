package defpackage;

import android.util.Log;
import com.huawei.health.device.api.IndoorEquipManagerApi;
import com.huawei.health.trusport_ee.Activity;
import com.huawei.health.trusport_ee.Intensity;
import com.huawei.health.trusport_ee_jni.TruSport_EE_instance_t;
import com.huawei.health.trusport_ee_jni.TruSport_EE_profile_t;
import com.huawei.health.trusport_ee_jni.TruSport_EE_result_t;
import com.huawei.health.trusport_ee_jni.TruSport_EE_sample_t;
import com.huawei.openalliance.ad.constant.VastAttribute;
import java.math.BigInteger;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\u0018\u0000 82\u00020\u0001:\u00018B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u001e\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010J\b\u0010\u0011\u001a\u0004\u0018\u00010\u000eJ\b\u0010\u0012\u001a\u0004\u0018\u00010\u0006J\r\u0010\u0013\u001a\u0004\u0018\u00010\u0014¢\u0006\u0002\u0010\u0015J\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017J\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019J\r\u0010\u001a\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\u001bJ\b\u0010\u001c\u001a\u0004\u0018\u00010\u0017J\r\u0010\u001d\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\u001bJ\u0010\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!H\u0002J\u0010\u0010\"\u001a\u00020\u001f2\u0006\u0010#\u001a\u00020$H\u0002J\u0010\u0010%\u001a\u0004\u0018\u00010\u00062\u0006\u0010&\u001a\u00020'J\u0010\u0010(\u001a\u0004\u0018\u00010\u00062\u0006\u0010&\u001a\u00020)J\u0016\u0010*\u001a\u00020\u001f2\u0006\u0010+\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u000eJ\u000e\u0010,\u001a\u00020\u001f2\u0006\u0010-\u001a\u00020\u0014J\u000e\u0010.\u001a\u00020\u001f2\u0006\u0010/\u001a\u00020\u0019J\u000e\u00100\u001a\u00020\u001f2\u0006\u00101\u001a\u00020\bJ\u0015\u00102\u001a\u0004\u0018\u00010\b2\u0006\u00103\u001a\u00020\n¢\u0006\u0002\u00104J\u0010\u00105\u001a\u0004\u0018\u00010\u00062\u0006\u0010+\u001a\u00020\nJ\u0018\u00106\u001a\u0004\u0018\u00010\u00062\u0006\u0010+\u001a\u00020\n2\u0006\u00107\u001a\u00020\nR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00069"}, d2 = {"Lcom/huawei/health/trusport_ee/TruSportEe;", "", "()V", "instanceSwig", "Lcom/huawei/health/trusport_ee_jni/TruSport_EE_instance_t;", "analyseExternalEe", "Lcom/huawei/health/trusport_ee/Result;", "energyExpenditure", "", "duration", "", "eeGoalToDuration", "eeGoal", "activity", "Lcom/huawei/health/trusport_ee/Activity;", "intensity", "Lcom/huawei/health/trusport_ee/Intensity;", "getActivity", "getCurrentEe", "getHrMax", "", "()Ljava/lang/Integer;", "getJniVersion", "Lcom/huawei/health/trusport_ee/Version;", "getProfile", "Lcom/huawei/health/trusport_ee/Profile;", "getRee", "()Ljava/lang/Float;", "getVersion", "getVo2Max", "handleError", "", VastAttribute.ERROR, "Lcom/huawei/health/trusport_ee_jni/TruSport_EE_error_t;", "printLog", "msg", "", "pushSample", "sample", "Lcom/huawei/health/trusport_ee/Sample;", "pushSwimSample", "Lcom/huawei/health/trusport_ee/SwimSample;", "setActivity", "timestamp", "setHrMax", "hrMax", "setProfile", "profile", "setVo2Max", "vo2max", "stepGoalToEeGoal", "stepGoal", "(J)Ljava/lang/Float;", "updateEe", "updateIdleEeFromSteps", IndoorEquipManagerApi.KEY_STEP_COUNT, "Companion", "trusport_ee_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class glh {
    public static final a c = new a(null);
    private final TruSport_EE_instance_t b;

    private final void a(String str) {
    }

    public glh() {
        TruSport_EE_instance_t truSport_EE_instance_t = new TruSport_EE_instance_t();
        this.b = truSport_EE_instance_t;
        a("Initializing TruSportEe instance");
        glr c2 = glt.c(truSport_EE_instance_t);
        uhy.a(c2, "");
        c(c2);
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/huawei/health/trusport_ee/TruSportEe$Companion;", "", "()V", "TAG", "", "trusport_ee_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class a {
        private a() {
        }

        public /* synthetic */ a(uib uibVar) {
            this();
        }
    }

    static {
        try {
            System.loadLibrary("trusport_ee_jni");
        } catch (UnsatisfiedLinkError unused) {
            Log.e("TruSportEe", "Error loading shared library!");
        }
    }

    public final void b(glb glbVar) {
        uhy.e((Object) glbVar, "");
        a("Setting profile: profile=" + glbVar);
        TruSport_EE_profile_t e = toProfile.e(glbVar);
        glr d = glt.d(this.b, e);
        uhy.a(d, "");
        c(d);
        if (e != null) {
            e.c();
        }
    }

    public final void b(float f) {
        a("Setting vo2max: vo2max=" + f);
        glr b = glt.b(this.b, f);
        uhy.a(b, "");
        c(b);
    }

    public final void b(long j, Activity activity) {
        uhy.e((Object) activity, "");
        a("Setting activity: timestamp=" + j + ", activity=" + activity);
        gll e = toActivity.e(activity);
        BigInteger valueOf = BigInteger.valueOf(j);
        uhy.a(valueOf, "");
        glr d = glt.d(this.b, valueOf, e);
        uhy.a(d, "");
        c(d);
    }

    public final glj e(glf glfVar) {
        uhy.e((Object) glfVar, "");
        a("Pushing sample: sample=" + glfVar);
        TruSport_EE_sample_t b = toProfile.b(glfVar);
        TruSport_EE_result_t truSport_EE_result_t = new TruSport_EE_result_t();
        glr d = glt.d(this.b, b, truSport_EE_result_t);
        uhy.a(d, "");
        c(d);
        glj e = toProfile.e(truSport_EE_result_t);
        if (b != null) {
            b.b();
        }
        truSport_EE_result_t.d();
        a("    Ret: " + e);
        return e;
    }

    public final glj e(long j) {
        a("Updating EE: timestamp=" + j);
        TruSport_EE_result_t truSport_EE_result_t = new TruSport_EE_result_t();
        TruSport_EE_instance_t truSport_EE_instance_t = this.b;
        BigInteger valueOf = BigInteger.valueOf(j);
        uhy.a(valueOf, "");
        glr c2 = glt.c(truSport_EE_instance_t, valueOf, truSport_EE_result_t);
        uhy.a(c2, "");
        c(c2);
        glj e = toProfile.e(truSport_EE_result_t);
        truSport_EE_result_t.d();
        a("    Ret: " + e);
        return e;
    }

    public final glj d(long j, long j2) {
        a("Updating Idle EE from steps: timestamp=" + j + ", stepCount=" + j2);
        TruSport_EE_result_t truSport_EE_result_t = new TruSport_EE_result_t();
        TruSport_EE_instance_t truSport_EE_instance_t = this.b;
        BigInteger valueOf = BigInteger.valueOf(j);
        uhy.a(valueOf, "");
        glr c2 = glt.c(truSport_EE_instance_t, valueOf, j2, truSport_EE_result_t);
        uhy.a(c2, "");
        c(c2);
        glj e = toProfile.e(truSport_EE_result_t);
        truSport_EE_result_t.d();
        a("    Ret: " + e);
        return e;
    }

    public final glj e() {
        a("Getting current EE");
        TruSport_EE_result_t truSport_EE_result_t = new TruSport_EE_result_t();
        glr d = glt.d(this.b, truSport_EE_result_t);
        uhy.a(d, "");
        c(d);
        glj e = toProfile.e(truSport_EE_result_t);
        truSport_EE_result_t.d();
        a("    Ret: " + e);
        return e;
    }

    public final Float b(long j) {
        a("Estimating EE from steps: " + j);
        glk d = glt.d();
        uhy.a(d, "");
        glr d2 = glt.d(this.b, j, d);
        uhy.a(d2, "");
        c(d2);
        float e = glt.e(d);
        glt.b(d);
        a("    Ret: " + e);
        return Float.valueOf(e);
    }

    public final long e(float f, Activity activity, Intensity intensity) {
        uhy.e((Object) activity, "");
        uhy.e((Object) intensity, "");
        a("Estimating activity duration from EE: eeGoal=" + f + ", activity=" + activity + ", intensity=" + intensity);
        glm c2 = glt.c();
        uhy.a(c2, "");
        glr e = glt.e(this.b, f, toActivity.e(activity), toProfile.b(intensity), c2);
        uhy.a(e, "");
        c(e);
        long e2 = glt.e(c2);
        glt.d(c2);
        a("    Ret: " + e2);
        return e2;
    }

    private final void c(glr glrVar) {
        if (uhy.e(glrVar, glr.w)) {
            return;
        }
        Log.e("TruSportEe", "TruSport EE failure: " + glrVar);
        throw new gli(glrVar.toString());
    }
}
