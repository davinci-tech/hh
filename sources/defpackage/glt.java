package defpackage;

import com.huawei.health.trusport_ee_jni.TruSport_EE_instance_t;
import com.huawei.health.trusport_ee_jni.TruSport_EE_profile_t;
import com.huawei.health.trusport_ee_jni.TruSport_EE_result_t;
import com.huawei.health.trusport_ee_jni.TruSport_EE_sample_t;
import com.huawei.health.trusport_ee_jni.trusport_eeConstants;
import com.huawei.health.trusport_ee_jni.trusport_eeJNI;
import java.math.BigInteger;

/* loaded from: classes4.dex */
public class glt implements trusport_eeConstants {
    public static glr c(TruSport_EE_instance_t truSport_EE_instance_t) {
        return glr.c(trusport_eeJNI.TruSport_EE_Init(TruSport_EE_instance_t.c(truSport_EE_instance_t), truSport_EE_instance_t));
    }

    public static glr d(TruSport_EE_instance_t truSport_EE_instance_t, TruSport_EE_profile_t truSport_EE_profile_t) {
        return glr.c(trusport_eeJNI.TruSport_EE_SetProfile(TruSport_EE_instance_t.c(truSport_EE_instance_t), truSport_EE_instance_t, TruSport_EE_profile_t.c(truSport_EE_profile_t), truSport_EE_profile_t));
    }

    public static glr b(TruSport_EE_instance_t truSport_EE_instance_t, float f) {
        return glr.c(trusport_eeJNI.TruSport_EE_SetVo2Max(TruSport_EE_instance_t.c(truSport_EE_instance_t), truSport_EE_instance_t, f));
    }

    public static glr d(TruSport_EE_instance_t truSport_EE_instance_t, BigInteger bigInteger, gll gllVar) {
        return glr.c(trusport_eeJNI.TruSport_EE_SetActivity(TruSport_EE_instance_t.c(truSport_EE_instance_t), truSport_EE_instance_t, bigInteger, gllVar.e()));
    }

    public static glr e(TruSport_EE_sample_t truSport_EE_sample_t) {
        return glr.c(trusport_eeJNI.TruSport_EE_InitSample(TruSport_EE_sample_t.d(truSport_EE_sample_t), truSport_EE_sample_t));
    }

    public static glr d(TruSport_EE_instance_t truSport_EE_instance_t, TruSport_EE_sample_t truSport_EE_sample_t, TruSport_EE_result_t truSport_EE_result_t) {
        return glr.c(trusport_eeJNI.TruSport_EE_PushSample(TruSport_EE_instance_t.c(truSport_EE_instance_t), truSport_EE_instance_t, TruSport_EE_sample_t.d(truSport_EE_sample_t), truSport_EE_sample_t, TruSport_EE_result_t.e(truSport_EE_result_t), truSport_EE_result_t));
    }

    public static glr c(TruSport_EE_instance_t truSport_EE_instance_t, BigInteger bigInteger, TruSport_EE_result_t truSport_EE_result_t) {
        return glr.c(trusport_eeJNI.TruSport_EE_UpdateEe(TruSport_EE_instance_t.c(truSport_EE_instance_t), truSport_EE_instance_t, bigInteger, TruSport_EE_result_t.e(truSport_EE_result_t), truSport_EE_result_t));
    }

    public static glr c(TruSport_EE_instance_t truSport_EE_instance_t, BigInteger bigInteger, long j, TruSport_EE_result_t truSport_EE_result_t) {
        return glr.c(trusport_eeJNI.TruSport_EE_UpdateIdleEeFromSteps(TruSport_EE_instance_t.c(truSport_EE_instance_t), truSport_EE_instance_t, bigInteger, j, TruSport_EE_result_t.e(truSport_EE_result_t), truSport_EE_result_t));
    }

    public static glr d(TruSport_EE_instance_t truSport_EE_instance_t, TruSport_EE_result_t truSport_EE_result_t) {
        return glr.c(trusport_eeJNI.TruSport_EE_GetCurrentEe(TruSport_EE_instance_t.c(truSport_EE_instance_t), truSport_EE_instance_t, TruSport_EE_result_t.e(truSport_EE_result_t), truSport_EE_result_t));
    }

    public static glr d(TruSport_EE_instance_t truSport_EE_instance_t, long j, glk glkVar) {
        return glr.c(trusport_eeJNI.TruSport_EE_StepGoalToEeGoal(TruSport_EE_instance_t.c(truSport_EE_instance_t), truSport_EE_instance_t, j, glk.b(glkVar)));
    }

    public static glr e(TruSport_EE_instance_t truSport_EE_instance_t, float f, gll gllVar, glo gloVar, glm glmVar) {
        return glr.c(trusport_eeJNI.TruSport_EE_EeGoalToDuration(TruSport_EE_instance_t.c(truSport_EE_instance_t), truSport_EE_instance_t, f, gllVar.e(), gloVar.e(), glm.c(glmVar)));
    }

    public static float b() {
        return trusport_eeJNI.TRUSPORT_EE_EMPTY_SPEED_get();
    }

    public static float e() {
        return trusport_eeJNI.TRUSPORT_EE_EMPTY_STEP_RATE_get();
    }

    public static short a() {
        return trusport_eeJNI.TRUSPORT_EE_EMPTY_HR_get();
    }

    public static glk d() {
        long new_preal_t = trusport_eeJNI.new_preal_t();
        if (new_preal_t == 0) {
            return null;
        }
        return new glk(new_preal_t, false);
    }

    public static void b(glk glkVar) {
        trusport_eeJNI.delete_preal_t(glk.b(glkVar));
    }

    public static float e(glk glkVar) {
        return trusport_eeJNI.preal_t_value(glk.b(glkVar));
    }

    public static glm c() {
        long new_puint32_t = trusport_eeJNI.new_puint32_t();
        if (new_puint32_t == 0) {
            return null;
        }
        return new glm(new_puint32_t, false);
    }

    public static void d(glm glmVar) {
        trusport_eeJNI.delete_puint32_t(glm.c(glmVar));
    }

    public static long e(glm glmVar) {
        return trusport_eeJNI.puint32_t_value(glm.c(glmVar));
    }
}
