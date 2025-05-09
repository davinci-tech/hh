package com.huawei.healthcloud.plugintrack.manager.voice;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public enum SportAudioScenarioId {
    START_BIKE("S291"),
    START_RUN("S292"),
    START_WALK("S293"),
    START_CLIMB("S283"),
    START_HIKING("S284"),
    START_SPORT("S096"),
    PAUSE_SPORT("S097"),
    RESUME_SPORT("S099"),
    STOP_SPORT("S100"),
    STOP_SPORT_WITH_RELAX("S276"),
    STOP_SPORT_WITH_STRETCH("S277"),
    ACHIEVE_HALF_GOAL("S274"),
    ACHIEVE_GOAL("S289"),
    FIVE_HUNDRED_LEFT("S275"),
    DISTANCE_LEFT_WITH_AMAZING("S015"),
    DISTANCE_LEFT_WITH_HONG_ON_TO_SUCCESS("S016"),
    DISTANCE_LEFT_WITH_GREAT("S017"),
    DISTANCE_LEFT_WITH_COME_ON("S018"),
    DISTANCE_LEFT_WITH_HONG_ON_YOU_CAN("S019"),
    DISTANCE_LEFT_WITH_MARVELOUS("S020"),
    DISTANCE_LEFT_WITH_HONG_ON_A_LITTLE("S021"),
    HALF_MARATHON_COMPLETE("S088"),
    MARATHON_COMPLETE("S089"),
    ENCOURAGE_ZERO("S262"),
    ENCOURAGE_ONE("S263"),
    ENCOURAGE_TWO("S264"),
    ENCOURAGE_THREE("S265"),
    ENCOURAGE_FOUR("S266"),
    ENCOURAGE_FIVE("S267"),
    ENCOURAGE_SIX("S268"),
    ENCOURAGE_NATIONAL_DAY("S269"),
    ENCOURAGE_RUNNING_ZERO("S278"),
    ENCOURAGE_RUNNING_ONE("S279"),
    SENSOR_DISTANCE("S014"),
    DISTANCE_TOO_SHORT("S255"),
    PRIVACY_AUTHORIZE_REMIND("S281"),
    HEART_RATE_TOO_FAST_WARNING("S256"),
    LESS_THEN_ONE_KM("S270"),
    RECENT_ONE_KM_PACE_GREAT("S006"),
    RECENT_ONE_KM_PACE_COMMON_ON("S007"),
    CURRENT_HEART_RATE("S010"),
    HEART_RATE_DEVICE_NOT_CONNECTED("S295"),
    HEART_RATE_MEASURING("S272"),
    PACE_MEASURING("S271"),
    CURRENT_PACE_GREAT("S008"),
    CURRENT_PACE_COME_ON("S009"),
    SPEED_MEASURING("S273"),
    CURRENT_SPEED_GREAT("S011"),
    CURRENT_SPEED_COME_ON("S012"),
    DEVICE_CONNECTED("S254"),
    PRESS_START_ON_DEVICE_TO_SPORT("S280"),
    FOLLOW_DEVICE_GUIDE_TO_SPORT("S282"),
    ADJUST_POSTURE_REMIND("S253"),
    LAND_POSTURE_EXACT_RANGE("S257"),
    LAND_POSTURE_LOWER("S258"),
    LAND_POSTURE_LOWER_TO_RANGE("S259"),
    LAND_POSTURE_LOWER_WITH_ADJUST("S260"),
    LAND_POSTURE_LOWER_WITH_FASTER("S261"),
    CADENCE_APPROPRIATE("S285"),
    CADENCE_IS_FAST("S286"),
    CADENCE_IS_SLOW("S287"),
    RUNNING_POSTURE_NICE("S251"),
    RUNNING_POSTURE_GREAT("S252"),
    WARM_UP_IN_HEART_RATE_RANGE("S013"),
    WARM_UP_NOT_ENOUGH("S296"),
    EXERCISE_DETAIL_WHEN_TIME_TARGET_HANG_ON("S001"),
    EXERCISE_DETAIL_WHEN_TIME_TARGET_COME_ON("S002"),
    EXERCISE_DETAIL_WHEN_DISTANCE_TARGET_HANG_ON("S003"),
    EXERCISE_DETAIL_WHEN_DISTANCE_TARGET_COME_ON("S004"),
    EXERCISE_DETAIL_WITHOUT_TARGET("S005"),
    COUNT_DOWN_FIVE("S501"),
    COUNT_DOWN_FOUR("S502"),
    COUNT_DOWN_THREE("S503"),
    COUNT_DOWN_TWO("S504"),
    COUNT_DOWN_ONE("S505"),
    COUNT_DOWN_GO("S506"),
    EMPTY("S507"),
    DING_DONG("S508");

    private final String mId;

    SportAudioScenarioId(String str) {
        this.mId = str;
    }

    public String getId() {
        return this.mId;
    }

    public static List<SportAudioScenarioId> getEncourageScenarios() {
        return Arrays.asList(ENCOURAGE_ZERO, ENCOURAGE_ONE, ENCOURAGE_TWO, ENCOURAGE_THREE, ENCOURAGE_FOUR, ENCOURAGE_FIVE, ENCOURAGE_SIX);
    }

    public static List<SportAudioScenarioId> getEncourageWithRunningScenarios() {
        return Arrays.asList(ENCOURAGE_ZERO, ENCOURAGE_ONE, ENCOURAGE_TWO, ENCOURAGE_THREE, ENCOURAGE_FOUR, ENCOURAGE_FIVE, ENCOURAGE_SIX, ENCOURAGE_RUNNING_ZERO, ENCOURAGE_RUNNING_ONE);
    }

    public static List<SportAudioScenarioId> getDistanceRemainingScenarios() {
        return Arrays.asList(DISTANCE_LEFT_WITH_AMAZING, DISTANCE_LEFT_WITH_HONG_ON_TO_SUCCESS, DISTANCE_LEFT_WITH_GREAT, DISTANCE_LEFT_WITH_COME_ON, DISTANCE_LEFT_WITH_HONG_ON_YOU_CAN, DISTANCE_LEFT_WITH_MARVELOUS, DISTANCE_LEFT_WITH_HONG_ON_A_LITTLE);
    }

    public static Map<Integer, SportAudioScenarioId> getLandPostureEstimationScenarios() {
        return new HashMap<Integer, SportAudioScenarioId>() { // from class: com.huawei.healthcloud.plugintrack.manager.voice.SportAudioScenarioId.2
            {
                put(12, SportAudioScenarioId.LAND_POSTURE_EXACT_RANGE);
                put(13, SportAudioScenarioId.LAND_POSTURE_LOWER_WITH_FASTER);
                put(14, SportAudioScenarioId.LAND_POSTURE_LOWER_WITH_ADJUST);
                put(15, SportAudioScenarioId.LAND_POSTURE_LOWER);
                put(16, SportAudioScenarioId.LAND_POSTURE_LOWER_TO_RANGE);
            }
        };
    }

    public static Map<Integer, SportAudioScenarioId> getCadenceSpeedScenarios() {
        return new HashMap<Integer, SportAudioScenarioId>() { // from class: com.huawei.healthcloud.plugintrack.manager.voice.SportAudioScenarioId.3
            {
                put(18, SportAudioScenarioId.CADENCE_APPROPRIATE);
                put(19, SportAudioScenarioId.CADENCE_IS_SLOW);
                put(20, SportAudioScenarioId.CADENCE_IS_FAST);
            }
        };
    }

    public static Map<Integer, SportAudioScenarioId> getRunningPostureScenarios() {
        return new HashMap<Integer, SportAudioScenarioId>() { // from class: com.huawei.healthcloud.plugintrack.manager.voice.SportAudioScenarioId.5
            {
                put(25, SportAudioScenarioId.RUNNING_POSTURE_NICE);
                put(26, SportAudioScenarioId.RUNNING_POSTURE_GREAT);
            }
        };
    }

    public static List<SportAudioScenarioId> getCountDownScenarios() {
        return Arrays.asList(COUNT_DOWN_GO, COUNT_DOWN_ONE, COUNT_DOWN_TWO, COUNT_DOWN_THREE, COUNT_DOWN_FOUR, COUNT_DOWN_FIVE);
    }
}
