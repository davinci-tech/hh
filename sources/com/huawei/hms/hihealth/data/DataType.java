package com.huawei.hms.hihealth.data;

import android.os.Parcelable;
import com.huawei.hms.common.internal.Preconditions;
import com.huawei.hms.health.aabq;
import com.huawei.hms.health.aabv;
import com.huawei.hms.health.aabw;
import com.huawei.hms.health.aaby;
import com.huawei.hms.health.aacs;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

/* loaded from: classes4.dex */
public class DataType extends aabq {

    @Deprecated
    public static final String MIME_TYPE_PREFIX = "vnd.huawei.hiHealth.data_type/";

    @aaby(id = 5)
    private List<Field> fieldsList;

    @aaby(id = 7)
    private boolean isFromSelfDefined;

    @aaby(id = 6)
    private boolean isPolymerized;

    @aaby(id = 1)
    private String name;

    @aaby(id = 8)
    private String packageName;

    @aaby(id = 4)
    private String scopeNameBoth;

    @aaby(id = 2)
    private String scopeNameRead;

    @aaby(id = 3)
    private String scopeNameWrite;
    public static final Parcelable.Creator<DataType> CREATOR = new aabq.aab(DataType.class);
    public static final DataType DT_UNUSED_DATA_TYPE = new DataType("com.huawei.unused.datatype", "", "", "", Collections.singletonList(Field.newStringField("emptyfield")));
    public static final DataType DT_CONTINUOUS_STEPS_DELTA = new DataType("com.huawei.continuous.steps.delta", Scopes.HEALTHKIT_STEP_READ, Scopes.HEALTHKIT_STEP_WRITE, "https://www.huawei.com/healthkit/step.both", Collections.singletonList(Field.FIELD_STEPS_DELTA));
    public static final DataType DT_CONTINUOUS_STEPS_TOTAL = new DataType("com.huawei.continuous.steps.total", Scopes.HEALTHKIT_STEP_READ, Scopes.HEALTHKIT_STEP_WRITE, "https://www.huawei.com/healthkit/step.both", Arrays.asList(Field.FIELD_STEPS, Field.FIELD_DURATION)).setPolymerized();
    public static final DataType DT_INSTANTANEOUS_STEPS_RATE = new DataType("com.huawei.instantaneous.steps.rate", Scopes.HEALTHKIT_STEP_READ, Scopes.HEALTHKIT_STEP_WRITE, "https://www.huawei.com/healthkit/step.both", Collections.singletonList(Field.FIELD_STEP_RATE));
    public static final DataType DT_CONTINUOUS_STEPS_RATE_STATISTIC = new DataType("com.huawei.continuous.steps.rate.statistics", Scopes.HEALTHKIT_STEP_READ, Scopes.HEALTHKIT_STEP_WRITE, "https://www.huawei.com/healthkit/step.both", Arrays.asList(Field.FIELD_AVG, Field.FIELD_MAX, Field.FIELD_MIN)).setPolymerized();

    @Deprecated
    public static final DataType DT_CONTINUOUS_ACTIVITY_SEGMENT = new DataType("com.huawei.continuous.activity.fragment", Scopes.HEALTHKIT_ACTIVITY_READ, Scopes.HEALTHKIT_ACTIVITY_WRITE, "https://www.huawei.com/healthkit/activity.both", Collections.singletonList(Field.FIELD_TYPE_OF_ACTIVITY));
    public static final DataType DT_CONTINUOUS_ACTIVITY_FRAGMENT = new DataType("com.huawei.continuous.activity.fragment", Scopes.HEALTHKIT_ACTIVITY_READ, Scopes.HEALTHKIT_ACTIVITY_WRITE, "https://www.huawei.com/healthkit/activity.both", Collections.singletonList(Field.FIELD_TYPE_OF_ACTIVITY));
    public static final DataType DT_CONTINUOUS_CALORIES_CONSUMED = new DataType("com.huawei.continuous.calories.consumed", Scopes.HEALTHKIT_CALORIES_READ, Scopes.HEALTHKIT_CALORIES_WRITE, Scopes.HEALTHKIT_CALORIES_BOTH, Collections.singletonList(Field.FIELD_CALORIES));
    public static final DataType DT_CONTINUOUS_CALORIES_BURNT = new DataType("com.huawei.continuous.calories.burnt", Scopes.HEALTHKIT_CALORIES_READ, Scopes.HEALTHKIT_CALORIES_WRITE, Scopes.HEALTHKIT_CALORIES_BOTH, Collections.singletonList(Field.FIELD_CALORIES));

    @Deprecated
    public static final DataType DT_CONTINUOUS_EXERCISE_INTENSITY = new DataType("com.huawei.continuous.exercise_intensity", Scopes.HEALTHKIT_STRENGTH_READ, Scopes.HEALTHKIT_STRENGTH_WRITE, Scopes.HEALTHKIT_STRENGTH_BOTH, Collections.singletonList(Field.FIELD_INTENSITY));
    public static final DataType DT_CONTINUOUS_EXERCISE_INTENSITY_V2 = new DataType("com.huawei.continuous.exercise_intensity.v2", Scopes.HEALTHKIT_STRENGTH_READ, Scopes.HEALTHKIT_STRENGTH_WRITE, Scopes.HEALTHKIT_STRENGTH_BOTH, Collections.singletonList(Field.EXERCISE_TYPE));
    public static final DataType DT_STATISTICS_EXERCISE_INTENSITY_V2 = new DataType("com.huawei.continuous.exercise_intensity.v2.statistics", Scopes.HEALTHKIT_STRENGTH_READ, Scopes.HEALTHKIT_STRENGTH_WRITE, Scopes.HEALTHKIT_STRENGTH_BOTH, Collections.singletonList(Field.INTENSITY_MAP)).setPolymerized();
    public static final DataType DT_INSTANTANEOUS_CALORIES_BMR = new DataType("com.huawei.instantaneous.calories.bmr", Scopes.HEALTHKIT_CALORIES_READ, Scopes.HEALTHKIT_CALORIES_WRITE, Scopes.HEALTHKIT_CALORIES_BOTH, Collections.singletonList(Field.FIELD_CALORIES));
    public static final DataType DT_INSTANTANEOUS_POWER_SAMPLE = new DataType("com.huawei.instantaneous.power.sample", Scopes.HEALTHKIT_ACTIVITY_READ, Scopes.HEALTHKIT_ACTIVITY_WRITE, "https://www.huawei.com/healthkit/activity.both", Collections.singletonList(Field.FIELD_POWER));
    public static final DataType DT_INSTANTANEOUS_ACTIVITY_SAMPLE = new DataType("com.huawei.instantaneous.activity.sample", Scopes.HEALTHKIT_ACTIVITY_READ, Scopes.HEALTHKIT_ACTIVITY_WRITE, "https://www.huawei.com/healthkit/activity.both", Arrays.asList(Field.FIELD_TYPE_OF_ACTIVITY, Field.FIELD_POSSIBILITY));
    public static final DataType DT_INSTANTANEOUS_ACTIVITY_SAMPLES = new DataType("com.huawei.instantaneous.activity.samples", Scopes.HEALTHKIT_ACTIVITY_READ, Scopes.HEALTHKIT_ACTIVITY_WRITE, "https://www.huawei.com/healthkit/activity.both", Collections.singletonList(Field.FIELD_POSSIBILITY_OF_ACTIVITY));
    public static final DataType DT_INSTANTANEOUS_HEART_RATE = new DataType("com.huawei.instantaneous.heart_rate", Scopes.HEALTHKIT_HEARTRATE_READ, Scopes.HEALTHKIT_HEARTRATE_WRITE, Scopes.HEALTHKIT_HEARTRATE_BOTH, Collections.singletonList(Field.FIELD_BPM));
    public static final DataType DT_INSTANTANEOUS_EXERCISE_HEART_RATE = new DataType("com.huawei.instantaneous.exercise_heart_rate", Scopes.HEALTHKIT_HEARTRATE_READ, Scopes.HEALTHKIT_HEARTRATE_WRITE, Scopes.HEALTHKIT_HEARTRATE_BOTH, Collections.singletonList(Field.FIELD_BPM));
    public static final DataType DT_INSTANTANEOUS_LOCATION_SAMPLE = new DataType("com.huawei.instantaneous.location.sample", Scopes.HEALTHKIT_LOCATION_READ, Scopes.HEALTHKIT_LOCATION_WRITE, Scopes.HEALTHKIT_LOCATION_BOTH, Arrays.asList(Field.FIELD_LATITUDE, Field.FIELD_LONGITUDE, Field.FIELD_PRECISION, Field.FIELD_ALTITUDE, Field.FIELD_COORDINATE));
    public static final DataType DT_INSTANTANEOUS_LOCATION_TRACE = new DataType("com.huawei.instantaneous.location.trace", Scopes.HEALTHKIT_LOCATION_READ, Scopes.HEALTHKIT_LOCATION_WRITE, Scopes.HEALTHKIT_LOCATION_BOTH, Arrays.asList(Field.FIELD_LATITUDE, Field.FIELD_LONGITUDE, Field.FIELD_PRECISION, Field.FIELD_ALTITUDE));
    public static final DataType DT_CONTINUOUS_DISTANCE_DELTA = new DataType("com.huawei.continuous.distance.delta", Scopes.HEALTHKIT_DISTANCE_READ, Scopes.HEALTHKIT_DISTANCE_WRITE, Scopes.HEALTHKIT_DISTANCE_BOTH, Collections.singletonList(Field.FIELD_DISTANCE_DELTA));
    public static final DataType DT_CONTINUOUS_DISTANCE_TOTAL = new DataType("com.huawei.continuous.distance.total", Scopes.HEALTHKIT_DISTANCE_READ, Scopes.HEALTHKIT_DISTANCE_WRITE, Scopes.HEALTHKIT_DISTANCE_BOTH, Collections.singletonList(Field.FIELD_DISTANCE)).setPolymerized();
    public static final DataType DT_CONTINUOUS_CALORIES_BURNT_TOTAL = new DataType("com.huawei.continuous.calories.burnt.total", Scopes.HEALTHKIT_CALORIES_READ, Scopes.HEALTHKIT_CALORIES_WRITE, Scopes.HEALTHKIT_CALORIES_BOTH, Collections.singletonList(Field.FIELD_CALORIES_TOTAL)).setPolymerized();
    public static final DataType DT_INSTANTANEOUS_SPEED = new DataType("com.huawei.instantaneous.speed", Scopes.HEALTHKIT_SPEED_READ, Scopes.HEALTHKIT_SPEED_WRITE, Scopes.HEALTHKIT_SPEED_BOTH, Collections.singletonList(Field.FIELD_SPEED));

    @Deprecated
    public static final DataType DT_CONTINUOUS_BIKING_WHEEL_ROTATION_TOTAL = new DataType("com.huawei.continuous.biking.wheel_rotation.total", Scopes.HEALTHKIT_SPEED_READ, Scopes.HEALTHKIT_SPEED_WRITE, Scopes.HEALTHKIT_SPEED_BOTH, Collections.singletonList(Field.FIELD_ROTATION));

    @Deprecated
    public static final DataType DT_INSTANTANEOUS_BIKING_WHEEL_ROTATION = new DataType("com.huawei.instantaneous.biking.wheel_rotation", Scopes.HEALTHKIT_SPEED_READ, Scopes.HEALTHKIT_SPEED_WRITE, Scopes.HEALTHKIT_SPEED_BOTH, Collections.singletonList(Field.FIELD_RPM));
    public static final DataType DT_INSTANTANEOUS_WHEEL_ROTATION = new DataType("com.huawei.instantaneous.wheel_rotation", Scopes.HEALTHKIT_SPEED_READ, Scopes.HEALTHKIT_SPEED_WRITE, Scopes.HEALTHKIT_SPEED_BOTH, Collections.singletonList(Field.FIELD_RPM));
    public static final DataType DT_CONTINUOUS_BIKING_PEDALING_TOTAL = new DataType("com.huawei.continuous.biking.pedaling.total", Scopes.HEALTHKIT_ACTIVITY_READ, Scopes.HEALTHKIT_ACTIVITY_WRITE, "https://www.huawei.com/healthkit/activity.both", Collections.singletonList(Field.FIELD_ROTATION));
    public static final DataType DT_INSTANTANEOUS_BIKING_PEDALING_RATE = new DataType("com.huawei.instantaneous.biking.pedaling.rate", Scopes.HEALTHKIT_ACTIVITY_READ, Scopes.HEALTHKIT_ACTIVITY_WRITE, "https://www.huawei.com/healthkit/activity.both", Collections.singletonList(Field.FIELD_RPM));
    public static final DataType DT_INSTANTANEOUS_HEIGHT = new DataType("com.huawei.instantaneous.height", Scopes.HEALTHKIT_HEIGHTWEIGHT_READ, Scopes.HEALTHKIT_HEIGHTWEIGHT_WRITE, "https://www.huawei.com/healthkit/heightweight.both", Collections.singletonList(Field.FIELD_HEIGHT));
    public static final DataType DT_INSTANTANEOUS_BODY_WEIGHT = new DataType("com.huawei.instantaneous.body_weight", Scopes.HEALTHKIT_HEIGHTWEIGHT_READ, Scopes.HEALTHKIT_HEIGHTWEIGHT_WRITE, "https://www.huawei.com/healthkit/heightweight.both", Arrays.asList(Field.FIELD_BODY_WEIGHT, Field.FIELD_BMI, Field.FIELD_BODY_FAT, Field.FIELD_BODY_FAT_RATE, Field.FIELD_MUSCLE_MASS, Field.FIELD_BASAL_METABOLISM, Field.FIELD_MOISTURE, Field.FIELD_MOISTURE_RATE, Field.FIELD_VISCERAL_FAT_LEVEL, Field.FIELD_BONE_SALT, Field.FIELD_PROTEIN_RATE, Field.FIELD_BODY_AGE, Field.FIELD_BODY_SCORE, Field.FIELD_SKELETAL_MUSCLEL_MASS, Field.FIELD_IMPEDANCE));
    public static final DataType DT_INSTANTANEOUS_BODY_FAT_RATE = new DataType("com.huawei.instantaneous.body.fat.rate", Scopes.HEALTHKIT_BODYFAT_READ, Scopes.HEALTHKIT_BODYFAT_WRITE, Scopes.HEALTHKIT_BODYFAT_BOTH, Collections.singletonList(Field.FIELD_BODY_FAT_RATE));
    public static final DataType DT_INSTANTANEOUS_NUTRITION_FACTS = new DataType("com.huawei.instantaneous.nutrition_facts", Scopes.HEALTHKIT_NUTRITION_READ, Scopes.HEALTHKIT_NUTRITION_WRITE, Scopes.HEALTHKIT_NUTRITION_BOTH, Arrays.asList(Field.FIELD_NUTRIENTS_FACTS, Field.FIELD_MEAL, Field.FIELD_FOOD));
    public static final DataType DT_INSTANTANEOUS_HYDRATE = new DataType("com.huawei.instantaneous.hydrate", Scopes.HEALTHKIT_NUTRITION_READ, Scopes.HEALTHKIT_NUTRITION_WRITE, Scopes.HEALTHKIT_NUTRITION_BOTH, Collections.singletonList(Field.FIELD_HYDRATE));
    public static final DataType DT_CONTINUOUS_HYDRATE_TOTAL = new DataType("com.huawei.hydrate.total", Scopes.HEALTHKIT_NUTRITION_READ, Scopes.HEALTHKIT_NUTRITION_WRITE, Scopes.HEALTHKIT_NUTRITION_BOTH, Collections.singletonList(Field.FIELD_HYDRATE_TOTAL));
    public static final DataType DT_CONTINUOUS_WORKOUT_DURATION = new DataType("com.huawei.continuous.workout.duration", Scopes.HEALTHKIT_ACTIVITY_READ, Scopes.HEALTHKIT_ACTIVITY_WRITE, "https://www.huawei.com/healthkit/activity.both", Collections.singletonList(Field.FIELD_SPAN));
    public static final DataType DT_STATISTICS_SLEEP = new DataType("com.huawei.continuous.sleep.statistics", Scopes.HEALTHKIT_SLEEP_READ, Scopes.HEALTHKIT_SLEEP_WRITE, "https://www.huawei.com/healthkit/sleep.both", Arrays.asList(Field.FALL_ASLEEP_TIME, Field.WAKE_UP_TIME, Field.LIGHT_SLEEP_TIME, Field.DEEP_SLEEP_TIME, Field.DREAM_TIME, Field.AWAKE_TIME, Field.ALL_SLEEP_TIME, Field.WAKE_UP_CNT, Field.DEEP_SLEEP_PART, Field.SLEEP_SCORE, Field.SLEEP_LATENCY, Field.GO_BED_TIME, Field.SLEEP_EFFICIENCY, Field.GO_BED_TIME_NEW)).setPolymerized();
    public static final DataType DT_CONTINUOUS_SLEEP = new DataType("com.huawei.continuous.sleep.fragment", Scopes.HEALTHKIT_SLEEP_READ, Scopes.HEALTHKIT_SLEEP_WRITE, "https://www.huawei.com/healthkit/sleep.both", Collections.singletonList(Field.SLEEP_STATE));
    public static final DataType DT_SLEEP_ON_OFF_BED = new DataType("com.huawei.sleep.on_off_bed_record", Scopes.HEALTHKIT_SLEEP_READ, Scopes.HEALTHKIT_SLEEP_WRITE, "https://www.huawei.com/healthkit/sleep.both", Collections.singletonList(Field.SLEEP_ON_OFF_BED_STATE));
    public static final DataType DT_INSTANTANEOUS_STRESS = new DataType("com.huawei.instantaneous.stress", Scopes.HEALTHKIT_STRESS_READ, Scopes.HEALTHKIT_STRESS_WRITE, Scopes.HEALTHKIT_STRESS_BOTH, Arrays.asList(Field.SCORE, Field.GRADE, Field.MEASURE_TYPE));
    public static final DataType DT_INSTANTANEOUS_STRESS_STATISTICS = new DataType("com.huawei.instantaneous.stress.statistics", Scopes.HEALTHKIT_STRESS_READ, Scopes.HEALTHKIT_STRESS_WRITE, Scopes.HEALTHKIT_STRESS_BOTH, Arrays.asList(Field.STRESS_AVG, Field.STRESS_MAX, Field.STRESS_MIN, Field.STRESS_LAST, Field.MEASURE_COUNT)).setPolymerized();
    public static final DataType DT_INSTANTANEOUS_RUN_VDOT = new DataType("com.huawei.instantaneous.run.vdot", Scopes.HEALTHKIT_ACTIVITY_READ, Scopes.HEALTHKIT_ACTIVITY_WRITE, "https://www.huawei.com/healthkit/activity.both", Collections.singletonList(Field.VDOT));
    public static final DataType DT_INSTANTANEOUS_RUN_TRAINING_INDEX = new DataType("com.huawei.instantaneous.run.training_index", Scopes.HEALTHKIT_ACTIVITY_READ, Scopes.HEALTHKIT_ACTIVITY_WRITE, "https://www.huawei.com/healthkit/activity.both", Collections.singletonList(Field.TRAINING_INDEX));
    public static final DataType DT_INSTANTANEOUS_RUN_FATIGUE_INDEX = new DataType("com.huawei.instantaneous.run.fatigue_index", Scopes.HEALTHKIT_ACTIVITY_READ, Scopes.HEALTHKIT_ACTIVITY_WRITE, "https://www.huawei.com/healthkit/activity.both", Collections.singletonList(Field.FATIGUE_INDEX));
    public static final DataType DT_INSTANTANEOUS_RUN_PHYSICAL_FITNESS_INDEX = new DataType("com.huawei.instantaneous.run.physical_fitness_index", Scopes.HEALTHKIT_ACTIVITY_READ, Scopes.HEALTHKIT_ACTIVITY_WRITE, "https://www.huawei.com/healthkit/activity.both", Collections.singletonList(Field.PHYSICAL_FITNESS_INDEX));
    public static final DataType DT_INSTANTANEOUS_RUN_STATE_INDEX = new DataType("com.huawei.instantaneous.run.state_index", Scopes.HEALTHKIT_ACTIVITY_READ, Scopes.HEALTHKIT_ACTIVITY_WRITE, "https://www.huawei.com/healthkit/activity.both", Collections.singletonList(Field.STATE_INDEX));
    public static final DataType DT_INSTANTANEOUS_RESTING_HEART_RATE = new DataType("com.huawei.instantaneous.resting_heart_rate", Scopes.HEALTHKIT_HEARTRATE_READ, Scopes.HEALTHKIT_HEARTRATE_WRITE, Scopes.HEALTHKIT_HEARTRATE_BOTH, Collections.singletonList(Field.FIELD_BPM));
    public static final DataType DT_RESTING_HEART_RATE_STATISTICS = new DataType("com.huawei.continuous.resting_heart_rate.statistics", Scopes.HEALTHKIT_HEARTRATE_READ, Scopes.HEALTHKIT_HEARTRATE_WRITE, Scopes.HEALTHKIT_HEARTRATE_BOTH, Arrays.asList(Field.FIELD_AVG, Field.FIELD_MAX, Field.FIELD_MIN, Field.FIELD_LAST)).setPolymerized();
    public static final DataType DT_CONTINUOUS_EXERCISE_HEART_RATE_STATISTICS = new DataType("com.huawei.continuous.exercise_heart_rate.statistics", Scopes.HEALTHKIT_HEARTRATE_READ, Scopes.HEALTHKIT_HEARTRATE_WRITE, Scopes.HEALTHKIT_HEARTRATE_BOTH, Arrays.asList(Field.FIELD_AVG, Field.FIELD_MAX, Field.FIELD_MIN)).setPolymerized();
    public static final DataType DT_CONTINUOUS_JUMP = new DataType("com.huawei.continuous.jump", Scopes.HEALTHKIT_ACTIVITY_READ, Scopes.HEALTHKIT_ACTIVITY_WRITE, "https://www.huawei.com/healthkit/activity.both", Arrays.asList(Field.FIELD_PASSAGE_DURATION, Field.FIELD_JUMP_HEIGHT));
    public static final DataType DT_CONTINUOUS_JUMP_STATISTICS = new DataType("com.huawei.continuous.jump.statistics", Scopes.HEALTHKIT_ACTIVITY_READ, Scopes.HEALTHKIT_ACTIVITY_WRITE, "https://www.huawei.com/healthkit/activity.both", Arrays.asList(Field.FIELD_JUMP_TIMES, Field.FIELD_AVG_JUMP_HEIGHT, Field.FIELD_MAX_JUMP_HEIGHT, Field.FIELD_MIN_JUMP_HEIGHT, Field.FIELD_AVG_PASSAGE_DURATION, Field.FIELD_MAX_PASSAGE_DURATION, Field.FIELD_MIN_PASSAGE_DURATION)).setPolymerized();
    public static final DataType DT_INSTANTANEOUS_ALTITUDE = new DataType("com.huawei.instantaneous.altitude", Scopes.HEALTHKIT_DISTANCE_READ, Scopes.HEALTHKIT_DISTANCE_WRITE, Scopes.HEALTHKIT_DISTANCE_BOTH, Collections.singletonList(Field.ALTITUDE));
    public static final DataType DT_CONTINUOUS_ALTITUDE_STATISTICS = new DataType("com.huawei.continuous.altitude.statistics", Scopes.HEALTHKIT_DISTANCE_READ, Scopes.HEALTHKIT_DISTANCE_WRITE, Scopes.HEALTHKIT_DISTANCE_BOTH, Arrays.asList(Field.FIELD_AVG, Field.FIELD_MAX, Field.FIELD_MIN, Field.FIELD_ASCENT_TOTAL, Field.FIELD_DESCENT_TOTAL)).setPolymerized();
    public static final DataType DT_INSTANTANEOUS_SKIP_SPEED = new DataType("com.huawei.instantaneous.skip_speed", Scopes.HEALTHKIT_ACTIVITY_READ, Scopes.HEALTHKIT_ACTIVITY_WRITE, "https://www.huawei.com/healthkit/activity.both", Collections.singletonList(Field.SKIP_SPEED));
    public static final DataType DT_CONTINUOUS_SKIP_SPEED_STATISTICS = new DataType("com.huawei.continuous.skip_speed.statistics", Scopes.HEALTHKIT_ACTIVITY_READ, Scopes.HEALTHKIT_ACTIVITY_WRITE, "https://www.huawei.com/healthkit/activity.both", Arrays.asList(Field.AVG, Field.MAX, Field.MIN)).setPolymerized();
    public static final DataType DT_CONTINUOUS_RUN_POSTURE = new DataType("com.huawei.continuous.run.posture", Scopes.HEALTHKIT_ACTIVITY_READ, Scopes.HEALTHKIT_ACTIVITY_WRITE, "https://www.huawei.com/healthkit/activity.both", Arrays.asList(Field.GROUND_CONTACT_TIME, Field.GROUND_IMPACT_ACCELERATION, Field.SWING_ANGLE, Field.EVERSION_EXCURSION, Field.HANG_TIME, Field.GROUND_HANG_TIME_RATE, Field.FORE_FOOT_STRIKE_PATTERN, Field.HIND_FOOT_STRIKE_PATTERN, Field.WHOLE_FOOT_STRIKE_PATTERN, Field.IMPACT_PEAK, Field.VERTICAL_OSCILLATION, Field.VERTICAL_RATIO, Field.GC_TIME_BALANCE));
    public static final DataType DT_CONTINUOUS_RUN_POSTURE_STATISTICS = new DataType("com.huawei.continuous.run.posture.statistics", Scopes.HEALTHKIT_ACTIVITY_READ, Scopes.HEALTHKIT_ACTIVITY_WRITE, "https://www.huawei.com/healthkit/activity.both", Arrays.asList(Field.AVG_GROUND_CONTACT_TIME, Field.AVG_GROUND_IMPACT_ACCELERATION, Field.AVG_SWING_ANGLE, Field.AVG_EVERSION_EXCURSION, Field.AVG_HANG_TIME, Field.AVG_GROUND_HANG_TIME_RATE, Field.FORE_FOOT_STRIKE_PATTERN, Field.HIND_FOOT_STRIKE_PATTERN, Field.WHOLE_FOOT_STRIKE_PATTERN, Field.AVG_IMPACT_PEAK, Field.AVG_GC_TIME_BALANCE, Field.AVG_VERTICAL_OSCILLATION, Field.AVG_VERTICAL_RATIO, Field.AVG_VERTICAL_IMPACT_RATE)).setPolymerized();

    @Deprecated
    public static final DataType DT_INSTANTANEOUS_FREEDIVING_ASCENT_SPEED = new DataType("com.huawei.instantaneous.freediving.ascent_speed", Scopes.HEALTHKIT_ACTIVITY_READ, Scopes.HEALTHKIT_ACTIVITY_WRITE, "https://www.huawei.com/healthkit/activity.both", Collections.singletonList(Field.ASCENT_RATE));

    @Deprecated
    public static final DataType DT_INSTANTANEOUS_FREEDIVING_DESCENT_SPEED = new DataType("com.huawei.instantaneous.freediving.descent_speed", Scopes.HEALTHKIT_ACTIVITY_READ, Scopes.HEALTHKIT_ACTIVITY_WRITE, "https://www.huawei.com/healthkit/activity.both", Collections.singletonList(Field.DESCENT_RATE));
    public static final DataType DT_INSTANTANEOUS_STROKE_RATE = new DataType("com.huawei.instantaneous.stroke_rate", Scopes.HEALTHKIT_SPEED_READ, Scopes.HEALTHKIT_SPEED_WRITE, Scopes.HEALTHKIT_SPEED_BOTH, Collections.singletonList(Field.SPM));
    public static final DataType DT_INSTANTANEOUS_PEDALING_RATE = new DataType("com.huawei.instantaneous.pedaling_rate", Scopes.HEALTHKIT_SPEED_READ, Scopes.HEALTHKIT_SPEED_WRITE, Scopes.HEALTHKIT_SPEED_BOTH, Collections.singletonList(Field.RPM));

    @Deprecated
    public static final DataType DT_CONTINUOUS_FREEDIVING_ASCENT_SPEED_STATISTICS = new DataType("com.huawei.continuous.freediving.ascent_speed.statistics", Scopes.HEALTHKIT_ACTIVITY_READ, Scopes.HEALTHKIT_ACTIVITY_WRITE, "https://www.huawei.com/healthkit/activity.both", Arrays.asList(Field.FIELD_AVG, Field.FIELD_MAX, Field.FIELD_MIN)).setPolymerized();

    @Deprecated
    public static final DataType DT_CONTINUOUS_FREEDIVING_DESCENT_SPEED_STATISTICS = new DataType("com.huawei.continuous.freediving.descent_speed.statistics", Scopes.HEALTHKIT_ACTIVITY_READ, Scopes.HEALTHKIT_ACTIVITY_WRITE, "https://www.huawei.com/healthkit/activity.both", Arrays.asList(Field.FIELD_AVG, Field.FIELD_MAX, Field.FIELD_MIN)).setPolymerized();
    public static final DataType DT_CONTINUOUS_STROKE_RATE_STATISTICS = new DataType("com.huawei.continuous.stroke_rate.statistics", Scopes.HEALTHKIT_SPEED_READ, Scopes.HEALTHKIT_SPEED_WRITE, Scopes.HEALTHKIT_SPEED_BOTH, Arrays.asList(Field.FIELD_AVG, Field.FIELD_MAX, Field.FIELD_MIN)).setPolymerized();
    public static final DataType DT_CONTINUOUS_PEDALING_RATE_STATISTICS = new DataType("com.huawei.continuous.pedaling_rate.statistics", Scopes.HEALTHKIT_SPEED_READ, Scopes.HEALTHKIT_SPEED_WRITE, Scopes.HEALTHKIT_SPEED_BOTH, Arrays.asList(Field.FIELD_AVG, Field.FIELD_MAX, Field.FIELD_MIN)).setPolymerized();
    public static final DataType DT_RESISTANCE = new DataType("com.huawei.resistance", Scopes.HEALTHKIT_ACTIVITY_READ, Scopes.HEALTHKIT_ACTIVITY_WRITE, "https://www.huawei.com/healthkit/activity.both", Arrays.asList(Field.RESISTANCE_LEVEL));
    public static final DataType DT_RESISTANCE_STATISTICS = new DataType("com.huawei.resistance.statistics", Scopes.HEALTHKIT_ACTIVITY_READ, Scopes.HEALTHKIT_ACTIVITY_WRITE, "https://www.huawei.com/healthkit/activity.both", Arrays.asList(Field.MAX_RES, Field.MIN_RES, Field.RESISTANCE_LEVEL_ONE_LOWER_LIMIT, Field.RESISTANCE_LEVEL_TWO_LOWER_LIMIT, Field.RESISTANCE_LEVEL_THREE_LOWER_LIMIT, Field.RESISTANCE_LEVEL_FOUR_LOWER_LIMIT, Field.RESISTANCE_LEVEL_FIVE_LOWER_LIMIT, Field.RESISTANCE_LEVEL_FIVE_UPPER_LIMIT, Field.RESISTANCE_LEVEL_ONE_TIME, Field.RESISTANCE_LEVEL_TWO_TIME, Field.RESISTANCE_LEVEL_THREE_TIME, Field.RESISTANCE_LEVEL_FOUR_TIME, Field.RESISTANCE_LEVEL_FIVE_TIME)).setPolymerized();
    public static final DataType DT_VO2MAX = new DataType("com.huawei.vo2max", Scopes.HEALTHKIT_PULMONARY_READ, Scopes.HEALTHKIT_PULMONARY_WRITE, Scopes.HEALTHKIT_PULMONARY_BOTH, Arrays.asList(Field.VO2MAX));
    public static final DataType DT_VO2MAX_STATISTICS = new DataType("com.huawei.vo2max.statistics", Scopes.HEALTHKIT_PULMONARY_READ, Scopes.HEALTHKIT_PULMONARY_WRITE, Scopes.HEALTHKIT_PULMONARY_BOTH, Arrays.asList(Field.AVG, Field.MAX, Field.MIN, Field.LAST)).setPolymerized();
    public static final DataType DT_INSTANTANEOUS_SWIMMING_STROKE_RATE = new DataType("com.huawei.instantaneous.swimming.stroke_rate", Scopes.HEALTHKIT_SPEED_READ, Scopes.HEALTHKIT_SPEED_WRITE, Scopes.HEALTHKIT_SPEED_BOTH, Arrays.asList(Field.SPM));
    public static final DataType DT_CONTINUOUS_SWIMMING_STROKE_RATE_STATISTICS = new DataType("com.huawei.continuous.swimming.stroke_rate.statistics", Scopes.HEALTHKIT_SPEED_READ, Scopes.HEALTHKIT_SPEED_WRITE, Scopes.HEALTHKIT_SPEED_BOTH, Arrays.asList(Field.FIELD_AVG, Field.FIELD_MAX, Field.FIELD_MIN)).setPolymerized();
    public static final DataType DT_INSTANTANEOUS_SWIMMING_SWOLF = new DataType("com.huawei.instantaneous.swimming.swolf", Scopes.HEALTHKIT_ACTIVITY_READ, Scopes.HEALTHKIT_ACTIVITY_WRITE, "https://www.huawei.com/healthkit/activity.both", Arrays.asList(Field.SWOLF));
    public static final DataType DT_CONTINUOUS_SWIMMING_SWOLF_STATISTICS = new DataType("com.huawei.continuous.swimming.swolf.statistics", Scopes.HEALTHKIT_ACTIVITY_READ, Scopes.HEALTHKIT_ACTIVITY_WRITE, "https://www.huawei.com/healthkit/activity.both", Arrays.asList(Field.FIELD_AVG, Field.FIELD_MAX, Field.FIELD_MIN)).setPolymerized();
    public static final DataType DT_DIVING_DEPTH = new DataType("com.huawei.diving_depth", Scopes.HEALTHKIT_ACTIVITY_READ, Scopes.HEALTHKIT_ACTIVITY_WRITE, "https://www.huawei.com/healthkit/activity.both", Arrays.asList(Field.DEPTH));
    public static final DataType DT_DIVING_DEPTH_STATISTICS = new DataType("com.huawei.diving_depth.statistics", Scopes.HEALTHKIT_ACTIVITY_READ, Scopes.HEALTHKIT_ACTIVITY_WRITE, "https://www.huawei.com/healthkit/activity.both", Arrays.asList(Field.FIELD_AVG, Field.FIELD_MAX, Field.FIELD_MIN)).setPolymerized();
    public static final DataType DT_WATER_TEMPERATURE = new DataType("com.huawei.water_temperature", Scopes.HEALTHKIT_ACTIVITY_READ, Scopes.HEALTHKIT_ACTIVITY_WRITE, "https://www.huawei.com/healthkit/activity.both", Arrays.asList(Field.TEMPERATURE));
    public static final DataType DT_WATER_TEMPERATURE_STATISTICS = new DataType("com.huawei.water_temperature.statistics", Scopes.HEALTHKIT_ACTIVITY_READ, Scopes.HEALTHKIT_ACTIVITY_WRITE, "https://www.huawei.com/healthkit/activity.both", Arrays.asList(Field.FIELD_AVG, Field.FIELD_MAX, Field.FIELD_MIN)).setPolymerized();
    public static final DataType DT_DIVING_STATISTICS = new DataType("com.huawei.diving.statistics", Scopes.HEALTHKIT_ACTIVITY_READ, Scopes.HEALTHKIT_ACTIVITY_WRITE, "https://www.huawei.com/healthkit/activity.both", Arrays.asList(Field.MAX_UNDERWATER_TIME, Field.MAX_DEPTH, Field.SURFACE_TIME)).setPolymerized();
    public static final DataType DT_GOLF_AREA_STATISTICS = new DataType("com.huawei.golf_area.statistics", Scopes.HEALTHKIT_ACTIVITY_READ, Scopes.HEALTHKIT_ACTIVITY_WRITE, "https://www.huawei.com/healthkit/activity.both", Arrays.asList(Field.HOLE, Field.SWING_COUNT, Field.HANDICAP, Field.PAR, Field.PUTTER, Field.PENALTY, Field.FAIRWAY_HIT)).setPolymerized();
    public static final DataType DT_MARK_POINT = new DataType("com.huawei.mark_point", Scopes.HEALTHKIT_ACTIVITY_READ, Scopes.HEALTHKIT_ACTIVITY_WRITE, "https://www.huawei.com/healthkit/activity.both", Arrays.asList(Field.SN, Field.TYPE, Field.LONGITUDE, Field.LATITUDE, Field.COLOR, Field.MODE));
    public static final DataType DT_ACTIVITY_FEATURE_JUMPING_ROPE = new DataType("com.huawei.activity.feature.jumping_rope", Scopes.HEALTHKIT_ACTIVITY_READ, Scopes.HEALTHKIT_ACTIVITY_WRITE, "https://www.huawei.com/healthkit/activity.both", Arrays.asList(Field.SKIP_NUM, Field.STUMBLING_ROPE, Field.MAX_SKIPPING_TIMES, Field.DOUBLE_SHAKE, Field.TRIPLE_SHAKE));
    public static final DataType DT_ACTIVITY_FEATURE_BASKETBALL = new DataType("com.huawei.activity.feature.basketball", Scopes.HEALTHKIT_ACTIVITY_READ, Scopes.HEALTHKIT_ACTIVITY_WRITE, "https://www.huawei.com/healthkit/activity.both", Arrays.asList(Field.OVERALL_SCORE, Field.BURST_SCORE, Field.JUMP_SCORE, Field.RUN_SCORE, Field.BREAKTHROUGH_SCORE, Field.SPORT_INTENSITY_SCORE));
    public static final DataType DT_ACTIVITY_FEATURE_FREEDIVING = new DataType("com.huawei.activity.feature.freediving", Scopes.HEALTHKIT_ACTIVITY_READ, Scopes.HEALTHKIT_ACTIVITY_WRITE, "https://www.huawei.com/healthkit/activity.both", Arrays.asList(Field.DIVING_TIME, Field.DIVING_COUNT, Field.MAX_DEPTH, Field.AVG_DEPTH, Field.MAX_UNDERWATER_TIME, Field.NO_FLY_TIME, Field.WATER_TYPE, Field.SURFACE_TIME));
    public static final DataType DT_ACTIVITY_FEATURE_SCUBA_DIVING = new DataType("com.huawei.activity.feature.scuba_diving", Scopes.HEALTHKIT_ACTIVITY_READ, Scopes.HEALTHKIT_ACTIVITY_WRITE, "https://www.huawei.com/healthkit/activity.both", Arrays.asList(Field.DIVING_TIME, Field.DIVING_COUNT, Field.DIVING_MODE, Field.MAX_UNDERWATER_TIME, Field.UNDERWATER_TIME, Field.NO_FLY_TIME, Field.WATER_TYPE, Field.CNS, Field.OTU, Field.WATER_DENSITY, Field.MAX_ASCENT_SPEED, Field.MAX_DESCENT_SPEED));
    public static final DataType DT_ACTIVITY_FEATURE_BREATH_HOLDING_TRAIN = new DataType("com.huawei.activity.feature.breath_holding_train", Scopes.HEALTHKIT_ACTIVITY_READ, Scopes.HEALTHKIT_ACTIVITY_WRITE, "https://www.huawei.com/healthkit/activity.both", Arrays.asList(Field.BREATH_TIME, Field.BREATH_HOLDING_TIME, Field.BREATH_HOLDING_TRAIN_RHYTHM));
    public static final DataType DT_ACTIVITY_FEATURE_BREATH_HOLDING_TEST = new DataType("com.huawei.activity.feature.breath_holding_test", Scopes.HEALTHKIT_ACTIVITY_READ, Scopes.HEALTHKIT_ACTIVITY_WRITE, "https://www.huawei.com/healthkit/activity.both", Arrays.asList(Field.DIAPHRAGM_TIME));
    public static final DataType DT_ACTIVITY_FEATURE_ROWING = new DataType("com.huawei.activity.feature.rowing", Scopes.HEALTHKIT_ACTIVITY_READ, Scopes.HEALTHKIT_ACTIVITY_WRITE, "https://www.huawei.com/healthkit/activity.both", Arrays.asList(Field.STROKES_NUM));
    public static final DataType DT_ACTIVITY_FEATURE_SWIMMING_OPEN_WATER = new DataType("com.huawei.activity.feature.swimming.open_water", Scopes.HEALTHKIT_ACTIVITY_READ, Scopes.HEALTHKIT_ACTIVITY_WRITE, "https://www.huawei.com/healthkit/activity.both", Arrays.asList(Field.PULL_TIMES, Field.SWIMMING_STROKE));
    public static final DataType DT_ACTIVITY_FEATURE_SWIMMING_POOL = new DataType("com.huawei.activity.feature.swimming.pool", Scopes.HEALTHKIT_ACTIVITY_READ, Scopes.HEALTHKIT_ACTIVITY_WRITE, "https://www.huawei.com/healthkit/activity.both", Arrays.asList(Field.PULL_TIMES, Field.TRIP_TIMES, Field.POOL_LENGTH, Field.SWIMMING_STROKE));
    public static final DataType DT_ACTIVITY_FEATURE_SKIING = new DataType("com.huawei.activity.feature.skiing", Scopes.HEALTHKIT_ACTIVITY_READ, Scopes.HEALTHKIT_ACTIVITY_WRITE, "https://www.huawei.com/healthkit/activity.both", Arrays.asList(Field.TRIP_TIMES, Field.MAX_SLOPE_PERCENT, Field.MAX_SLOPE_DEGREE, Field.SKIING_TOTAL_TIME, Field.SKIING_TOTAL_DISTANCE));
    public static final DataType DT_ACTIVITY_FEATURE_SNOWBOARDING = new DataType("com.huawei.activity.feature.snowboarding", Scopes.HEALTHKIT_ACTIVITY_READ, Scopes.HEALTHKIT_ACTIVITY_WRITE, "https://www.huawei.com/healthkit/activity.both", Arrays.asList(Field.TRIP_TIMES, Field.MAX_SLOPE_PERCENT, Field.MAX_SLOPE_DEGREE, Field.SKIING_TOTAL_TIME, Field.SKIING_TOTAL_DISTANCE));
    public static final DataType DT_ACTIVITY_FEATURE_GOLF = new DataType("com.huawei.activity.feature.golf", Scopes.HEALTHKIT_ACTIVITY_READ, Scopes.HEALTHKIT_ACTIVITY_WRITE, "https://www.huawei.com/healthkit/activity.both", Arrays.asList(Field.GOLF_SWING_COUNT, Field.GOLF_SWING_SPEED, Field.GOLF_MAX_SWING_SPEED, Field.GOLF_SWING_TEMPO, Field.GOLF_DOWN_SWING_TIME, Field.GOLF_BACK_SWING_TIME));
    public static final DataType DT_ACTIVITY_FEATURE_GOLF_AREA = new DataType("com.huawei.activity.feature.golf_area", Scopes.HEALTHKIT_ACTIVITY_READ, Scopes.HEALTHKIT_ACTIVITY_WRITE, "https://www.huawei.com/healthkit/activity.both", Arrays.asList(Field.FIELD_GOLF_SWING_COUNT, Field.COURSE_ID, Field.BRANCH_ID1, Field.BRANCH_ID2, Field.HOLES, Field.GIR, Field.DOUBLE_EAGLE, Field.EAGLE, Field.BRIDIE, Field.PAR, Field.BOGEY, Field.DOUBLE_BOGEY, Field.PUTTS, Field.AVG_PUTTS, Field.PAR3, Field.PAR4, Field.PAR5, Field.FAIRWAY_HITS, Field.FAIRWAY_LEFT, Field.FAIRWAY_RIGHT, Field.AVG_HANDICAP, Field.BEST_HANDICAP));
    public static final DataType POLYMERIZE_CONTINUOUS_ACTIVITY_STATISTICS = new DataType("com.huawei.continuous.activity.statistics", Scopes.HEALTHKIT_ACTIVITY_READ, Scopes.HEALTHKIT_ACTIVITY_WRITE, "https://www.huawei.com/healthkit/activity.both", Arrays.asList(Field.FIELD_TYPE_OF_ACTIVITY, Field.FIELD_SPAN, Field.FIELD_FRAGMENTS)).setPolymerized();
    public static final DataType POLYMERIZE_CONTINUOUS_CALORIES_BMR_STATISTICS = new DataType("com.huawei.continuous.calories.bmr.statistics", Scopes.HEALTHKIT_CALORIES_READ, Scopes.HEALTHKIT_CALORIES_WRITE, Scopes.HEALTHKIT_CALORIES_BOTH, Arrays.asList(Field.FIELD_AVG, Field.FIELD_MAX, Field.FIELD_MIN)).setPolymerized();
    public static final DataType POLYMERIZE_STEP_COUNT_DELTA = new DataType("com.huawei.continuous.steps.delta", Scopes.HEALTHKIT_STEP_READ, Scopes.HEALTHKIT_STEP_WRITE, "https://www.huawei.com/healthkit/step.both", Collections.singletonList(Field.FIELD_STEPS_DELTA)).setPolymerized();
    public static final DataType POLYMERIZE_DISTANCE_DELTA = new DataType("com.huawei.continuous.distance.delta", Scopes.HEALTHKIT_DISTANCE_READ, Scopes.HEALTHKIT_DISTANCE_WRITE, Scopes.HEALTHKIT_DISTANCE_BOTH, Collections.singletonList(Field.FIELD_DISTANCE_DELTA)).setPolymerized();
    public static final DataType POLYMERIZE_CALORIES_CONSUMED = new DataType("com.huawei.continuous.calories.consumed", Scopes.HEALTHKIT_CALORIES_READ, Scopes.HEALTHKIT_CALORIES_WRITE, Scopes.HEALTHKIT_CALORIES_BOTH, Collections.singletonList(Field.FIELD_CALORIES)).setPolymerized();
    public static final DataType POLYMERIZE_CALORIES_EXPENDED = new DataType("com.huawei.continuous.calories.burnt", Scopes.HEALTHKIT_CALORIES_READ, Scopes.HEALTHKIT_CALORIES_WRITE, Scopes.HEALTHKIT_CALORIES_BOTH, Collections.singletonList(Field.FIELD_CALORIES)).setPolymerized();

    @Deprecated
    public static final DataType POLYMERIZE_CONTINUOUS_EXERCISE_INTENSITY_STATISTICS = new DataType("com.huawei.continuous.exercise_intensity.statistics", Scopes.HEALTHKIT_STRENGTH_READ, Scopes.HEALTHKIT_STRENGTH_WRITE, Scopes.HEALTHKIT_STRENGTH_BOTH, Arrays.asList(Field.FIELD_INTENSITY, Field.FIELD_SPAN)).setPolymerized();
    public static final DataType POLYMERIZE_CONTINUOUS_HEART_RATE_STATISTICS = new DataType("com.huawei.continuous.heart_rate.statistics", Scopes.HEALTHKIT_HEARTRATE_READ, Scopes.HEALTHKIT_HEARTRATE_WRITE, Scopes.HEALTHKIT_HEARTRATE_BOTH, Arrays.asList(Field.FIELD_AVG, Field.FIELD_MAX, Field.FIELD_MIN, Field.FIELD_LAST)).setPolymerized();
    public static final DataType POLYMERIZE_CONTINUOUS_LOCATION_BOUNDARY_RANGE = new DataType("com.huawei.continuous.location.boundary_range", Scopes.HEALTHKIT_LOCATION_READ, Scopes.HEALTHKIT_LOCATION_WRITE, Scopes.HEALTHKIT_LOCATION_BOTH, Arrays.asList(Field.FIELD_MIN_LATITUDE, Field.FIELD_MIN_LONGITUDE, Field.FIELD_MAX_LATITUDE, Field.FIELD_MAX_LONGITUDE)).setPolymerized();
    public static final DataType POLYMERIZE_CONTINUOUS_POWER_STATISTICS = new DataType("com.huawei.continuous.power.statistics", Scopes.HEALTHKIT_ACTIVITY_READ, Scopes.HEALTHKIT_ACTIVITY_WRITE, "https://www.huawei.com/healthkit/activity.both", Arrays.asList(Field.FIELD_AVG, Field.FIELD_MAX, Field.FIELD_MIN)).setPolymerized();
    public static final DataType POLYMERIZE_CONTINUOUS_SPEED_STATISTICS = new DataType("com.huawei.continuous.speed.statistics", Scopes.HEALTHKIT_SPEED_READ, Scopes.HEALTHKIT_SPEED_WRITE, Scopes.HEALTHKIT_SPEED_BOTH, Arrays.asList(Field.FIELD_AVG, Field.FIELD_MAX, Field.FIELD_MIN)).setPolymerized();
    public static final DataType POLYMERIZE_CONTINUOUS_BODY_FAT_RATE_STATISTICS = new DataType("com.huawei.continuous.body.fat.rate.statistics", Scopes.HEALTHKIT_BODYFAT_READ, Scopes.HEALTHKIT_BODYFAT_WRITE, Scopes.HEALTHKIT_BODYFAT_BOTH, Arrays.asList(Field.FIELD_AVG, Field.FIELD_MAX, Field.FIELD_MIN)).setPolymerized();
    public static final DataType POLYMERIZE_CONTINUOUS_BODY_WEIGHT_STATISTICS = new DataType("com.huawei.continuous.body_weight.statistics", Scopes.HEALTHKIT_HEIGHTWEIGHT_READ, Scopes.HEALTHKIT_HEIGHTWEIGHT_WRITE, "https://www.huawei.com/healthkit/heightweight.both", Arrays.asList(Field.FIELD_AVG, Field.FIELD_MAX, Field.FIELD_MIN, Field.FIELD_LAST, Field.FIELD_AVG_BODY_FAT_RATE, Field.FIELD_MAX_BODY_FAT_RATE, Field.FIELD_MIN_BODY_FAT_RATE, Field.FIELD_AVG_SKELETAL_MUSCLEL_MASS, Field.FIELD_MAX_SKELETAL_MUSCLEL_MASS, Field.FIELD_MIN_SKELETAL_MUSCLEL_MASS)).setPolymerized();
    public static final DataType POLYMERIZE_CONTINUOUS_HEIGHT_STATISTICS = new DataType("com.huawei.continuous.height.statistics", Scopes.HEALTHKIT_HEIGHTWEIGHT_READ, Scopes.HEALTHKIT_HEIGHTWEIGHT_WRITE, "https://www.huawei.com/healthkit/heightweight.both", Arrays.asList(Field.FIELD_AVG, Field.FIELD_MAX, Field.FIELD_MIN)).setPolymerized();
    public static final DataType POLYMERIZE_CONTINUOUS_NUTRITION_FACTS_STATISTICS = new DataType("com.huawei.continuous.nutrition_facts.statistics", Scopes.HEALTHKIT_NUTRITION_READ, Scopes.HEALTHKIT_NUTRITION_WRITE, Scopes.HEALTHKIT_NUTRITION_BOTH, Arrays.asList(Field.FIELD_NUTRIENTS, Field.FIELD_MEAL)).setPolymerized();
    public static final DataType POLYMERIZE_HYDRATION = new DataType("com.huawei.instantaneous.hydrate", Scopes.HEALTHKIT_NUTRITION_READ, Scopes.HEALTHKIT_NUTRITION_WRITE, Scopes.HEALTHKIT_NUTRITION_BOTH, Collections.singletonList(Field.FIELD_HYDRATE)).setPolymerized();
    public static final DataType POLYMERIZE_CONTINUOUS_WORKOUT_DURATION = new DataType("com.huawei.continuous.workout.duration", Scopes.HEALTHKIT_ACTIVITY_READ, Scopes.HEALTHKIT_ACTIVITY_WRITE, "https://www.huawei.com/healthkit/activity.both", Collections.singletonList(Field.FIELD_SPAN)).setPolymerized();
    public static final DataType POLYMERIZE_LOCATION = new DataType("com.huawei.location.statistics", Scopes.HEALTHKIT_LOCATION_READ, Scopes.HEALTHKIT_LOCATION_WRITE, Scopes.HEALTHKIT_LOCATION_BOTH, Arrays.asList(Field.START_LAT, Field.START_LON, Field.END_LAT, Field.END_LON, Field.FIELD_COORDINATE)).setPolymerized();
    public static final DataType POLYMERIZE_BREATH_HOLDING_TRAIN_STATISTICS = new DataType("com.huawei.breath_holding_train.statistics", Scopes.HEALTHKIT_ACTIVITY_READ, Scopes.HEALTHKIT_ACTIVITY_WRITE, "https://www.huawei.com/healthkit/activity.both", Arrays.asList(Field.BREATH_TIME, Field.BREATH_HOLDING_TIME)).setPolymerized();

    @Deprecated
    public static final DataType POLYMERIZE_FREEDIVING_STATISTICS = new DataType("com.huawei.freediving.statistics", Scopes.HEALTHKIT_ACTIVITY_READ, Scopes.HEALTHKIT_ACTIVITY_WRITE, "https://www.huawei.com/healthkit/activity.both", Arrays.asList(Field.DIVING_TIME, Field.MAX_DEPTH, Field.SURFACE_TIME)).setPolymerized();

    @Override // com.huawei.hms.health.aabq, android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public String toString() {
        return String.format(Locale.ENGLISH, "DataType{%s%s}", this.name, this.fieldsList);
    }

    public DataType setPolymerized() {
        this.isPolymerized = true;
        return this;
    }

    public void setPackageName(String str) {
        Preconditions.checkArgument("".equals(str) || aacs.aabc(str), "PackageName Length Is Illegal!");
        this.packageName = str;
    }

    public void setFromSelfDefined(boolean z) {
        this.isFromSelfDefined = z;
    }

    public boolean isPolymerized() {
        return this.isPolymerized;
    }

    public boolean isFromSelfDefined() {
        return this.isFromSelfDefined;
    }

    public final int indexOf(Field field) {
        int indexOf = this.fieldsList.indexOf(field);
        Preconditions.checkArgument(indexOf >= 0, "Illegal input parameter");
        return indexOf;
    }

    public int hashCode() {
        return Objects.hash(this.name, Boolean.valueOf(this.isPolymerized));
    }

    public String getScopeNameWrite() {
        return this.scopeNameWrite;
    }

    public String getScopeNameRead() {
        return this.scopeNameRead;
    }

    public String getScopeNameBoth() {
        return this.scopeNameBoth;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public String getName() {
        return this.name;
    }

    public List<Field> getFields() {
        return this.fieldsList;
    }

    public Field getField(String str) {
        for (Field field : this.fieldsList) {
            if (field.getName().equals(str)) {
                return field;
            }
        }
        return null;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DataType)) {
            return false;
        }
        DataType dataType = (DataType) obj;
        if (this.isPolymerized ^ dataType.isPolymerized) {
            return false;
        }
        return this.name.equals(dataType.name);
    }

    public static List<DataType> getPolymerizesForInput(DataType dataType) {
        List<DataType> list = aabd.aab.get(dataType);
        return list == null ? Collections.emptyList() : Collections.unmodifiableList(list);
    }

    @Deprecated
    public static String getMimeType(DataType dataType) {
        String valueOf = String.valueOf(dataType.getName());
        return valueOf.length() != 0 ? "vnd.huawei.hihealth.data_type/".concat(valueOf) : "vnd.huawei.hihealth.data_type/";
    }

    @aabw
    public DataType(@aabv(id = 1) String str, @aabv(id = 2) String str2, @aabv(id = 3) String str3, @aabv(id = 4) String str4, @aabv(id = 5) List<Field> list, @aabv(id = 6) boolean z, @aabv(id = 7) boolean z2, @aabv(id = 8) String str5) {
        Preconditions.checkArgument(aacs.aaba(str), "DataType Name Length Is Illegal!");
        Preconditions.checkArgument(aacs.aab((List) list), "DataType Field Size Is Illegal!");
        boolean z3 = true;
        Preconditions.checkArgument("".equals(str2) || aacs.aabc(str2), "ScopeNameRead Length Is Illegal!");
        Preconditions.checkArgument("".equals(str3) || aacs.aabc(str3), "ScopeNameWrite Length Is Illegal!");
        if (!"".equals(str4) && !aacs.aabc(str4)) {
            z3 = false;
        }
        Preconditions.checkArgument(z3, "ScopeNameBoth Length Is Illegal!");
        this.name = str;
        this.scopeNameRead = str2;
        this.scopeNameWrite = str3;
        this.scopeNameBoth = str4;
        this.fieldsList = Collections.unmodifiableList(list);
        this.isPolymerized = z;
        this.isFromSelfDefined = z2;
        this.packageName = str5;
    }

    public DataType(String str, String str2, String str3, String str4, List<Field> list, boolean z) {
        Preconditions.checkArgument(aacs.aaba(str), "DataType Name Length Is Illegal!");
        Preconditions.checkArgument(aacs.aab((List) list), "DataType Field Size Is Illegal!");
        boolean z2 = true;
        Preconditions.checkArgument("".equals(str2) || aacs.aabc(str2), "ScopeNameRead Length Is Illegal!");
        Preconditions.checkArgument("".equals(str3) || aacs.aabc(str3), "ScopeNameWrite Length Is Illegal!");
        if (!"".equals(str4) && !aacs.aabc(str4)) {
            z2 = false;
        }
        Preconditions.checkArgument(z2, "ScopeNameBoth Length Is Illegal!");
        this.name = str;
        this.fieldsList = list;
        this.scopeNameRead = str2;
        this.isPolymerized = z;
        this.scopeNameWrite = str3;
        this.packageName = "";
        this.isFromSelfDefined = false;
        this.scopeNameBoth = str4;
    }

    public DataType(String str, String str2, String str3, String str4, List<Field> list) {
        this(str, str2, str3, str4, list, false, false, "");
    }

    public DataType(String str) {
        Preconditions.checkArgument(aacs.aaba(str), "DataType Name Length Is Illegal!");
        this.name = str;
        this.fieldsList = new ArrayList();
    }
}
