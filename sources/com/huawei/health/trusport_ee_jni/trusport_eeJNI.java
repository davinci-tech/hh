package com.huawei.health.trusport_ee_jni;

import java.math.BigInteger;

/* loaded from: classes4.dex */
public class trusport_eeJNI {
    public static final native int TRUSPORT_ACTIVITIES_MAX_get();

    public static final native short TRUSPORT_EE_EMPTY_HR_get();

    public static final native float TRUSPORT_EE_EMPTY_SPEED_get();

    public static final native float TRUSPORT_EE_EMPTY_STEP_RATE_get();

    public static final native int TRUSPORT_EE_ERROR_OK_get();

    public static final native int TRUSPORT_EE_INSTANCE_DATA_LENGTH_get();

    public static final native int TRUSPORT_EE_INTENSITY_LOW_get();

    public static final native int TRUSPORT_EE_SEX_MALE_get();

    public static final native int TS_ACTIVITY_OUTDOOR_RUN_get();

    public static final native int TruSport_EE_EeGoalToDuration(long j, TruSport_EE_instance_t truSport_EE_instance_t, float f, int i, int i2, long j2);

    public static final native int TruSport_EE_GetCurrentEe(long j, TruSport_EE_instance_t truSport_EE_instance_t, long j2, TruSport_EE_result_t truSport_EE_result_t);

    public static final native int TruSport_EE_Init(long j, TruSport_EE_instance_t truSport_EE_instance_t);

    public static final native int TruSport_EE_InitSample(long j, TruSport_EE_sample_t truSport_EE_sample_t);

    public static final native int TruSport_EE_PushSample(long j, TruSport_EE_instance_t truSport_EE_instance_t, long j2, TruSport_EE_sample_t truSport_EE_sample_t, long j3, TruSport_EE_result_t truSport_EE_result_t);

    public static final native int TruSport_EE_SetActivity(long j, TruSport_EE_instance_t truSport_EE_instance_t, BigInteger bigInteger, int i);

    public static final native int TruSport_EE_SetProfile(long j, TruSport_EE_instance_t truSport_EE_instance_t, long j2, TruSport_EE_profile_t truSport_EE_profile_t);

    public static final native int TruSport_EE_SetVo2Max(long j, TruSport_EE_instance_t truSport_EE_instance_t, float f);

    public static final native int TruSport_EE_StepGoalToEeGoal(long j, TruSport_EE_instance_t truSport_EE_instance_t, long j2, long j3);

    public static final native int TruSport_EE_UpdateEe(long j, TruSport_EE_instance_t truSport_EE_instance_t, BigInteger bigInteger, long j2, TruSport_EE_result_t truSport_EE_result_t);

    public static final native int TruSport_EE_UpdateIdleEeFromSteps(long j, TruSport_EE_instance_t truSport_EE_instance_t, BigInteger bigInteger, long j2, long j3, TruSport_EE_result_t truSport_EE_result_t);

    public static final native void TruSport_EE_profile_t_age_set(long j, TruSport_EE_profile_t truSport_EE_profile_t, short s);

    public static final native void TruSport_EE_profile_t_height_set(long j, TruSport_EE_profile_t truSport_EE_profile_t, float f);

    public static final native void TruSport_EE_profile_t_sex_set(long j, TruSport_EE_profile_t truSport_EE_profile_t, int i);

    public static final native void TruSport_EE_profile_t_weight_set(long j, TruSport_EE_profile_t truSport_EE_profile_t, float f);

    public static final native float TruSport_EE_result_t_active_get(long j, TruSport_EE_result_t truSport_EE_result_t);

    public static final native float TruSport_EE_result_t_resting_get(long j, TruSport_EE_result_t truSport_EE_result_t);

    public static final native float TruSport_EE_result_t_total_get(long j, TruSport_EE_result_t truSport_EE_result_t);

    public static final native void TruSport_EE_sample_t_hr_set(long j, TruSport_EE_sample_t truSport_EE_sample_t, short s);

    public static final native void TruSport_EE_sample_t_speed_set(long j, TruSport_EE_sample_t truSport_EE_sample_t, float f);

    public static final native void TruSport_EE_sample_t_steprate_set(long j, TruSport_EE_sample_t truSport_EE_sample_t, float f);

    public static final native void TruSport_EE_sample_t_timestamp_set(long j, TruSport_EE_sample_t truSport_EE_sample_t, BigInteger bigInteger);

    public static final native void delete_TruSport_EE_instance_t(long j);

    public static final native void delete_TruSport_EE_profile_t(long j);

    public static final native void delete_TruSport_EE_result_t(long j);

    public static final native void delete_TruSport_EE_sample_t(long j);

    public static final native void delete_preal_t(long j);

    public static final native void delete_puint32_t(long j);

    public static final native long new_TruSport_EE_instance_t();

    public static final native long new_TruSport_EE_profile_t();

    public static final native long new_TruSport_EE_result_t();

    public static final native long new_TruSport_EE_sample_t();

    public static final native long new_preal_t();

    public static final native long new_puint32_t();

    public static final native float preal_t_value(long j);

    public static final native long puint32_t_value(long j);
}
