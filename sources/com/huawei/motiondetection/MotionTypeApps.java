package com.huawei.motiondetection;

/* loaded from: classes5.dex */
public final class MotionTypeApps {
    public static final String KEY_ACTIVITY = "motion_activity";
    public static final String KEY_ACTIVITY_RECO = "motion_activity_state";
    public static final String KEY_FLIP = "motion_flip";
    public static final String KEY_FLIP_MUTE_CALL = "motion_flip_mute_call";
    public static final String KEY_FLIP_MUTE_CLOCK = "motion_flip_mute_clock";
    public static final String KEY_HW_STEP_COUNTER = "motion_hw_step_counter";
    public static final String KEY_HW_STEP_COUNTER_HEALTH = "motion_hw_step_counter_health";
    public static final String KEY_PICKUP = "motion_pickup";
    public static final String KEY_PICKUP_END_HINTS = "motion_pickup_end_hints";
    public static final String KEY_PICKUP_REDUCE_CALL = "motion_pickup_reduce_call";
    public static final String KEY_PICKUP_REDUCE_CLOCK = "motion_pickup_reduce_clock";
    public static final String KEY_POCKET = "motion_pocket";
    public static final String KEY_POCKET_CALL_RAISE = "motion_pocket_call_raise";
    public static final String KEY_PROXI = "motion_proximity";
    public static final String KEY_PROXI_ANSWER = "motion_proximity_answer";
    public static final String KEY_PROXI_BLUETOOTHSET = "motion_proximity_bluetoothset";
    public static final String KEY_PROXI_DIAL = "motion_proximity_dial";
    public static final String KEY_PROXI_SCREEN_OFF = "motion_proximity_screen_off";
    public static final String KEY_PROXI_SPEAKER = "motion_proximity_speaker";
    public static final String KEY_SHAKE = "motion_shake";
    public static final String KEY_SHAKE_CHANGE_WALLPAPER = "motion_shake_change_wallpaper";
    public static final String KEY_SHAKE_REARRAGE_WIDGETS = "motion_shake_rearrange_widgets";
    public static final String KEY_SHAKE_REFRESH = "motion_shake_refresh";
    public static final String KEY_SHAKE_START_PRIVACY = "motion_shake_start_privacy";
    public static final String KEY_SWIPE = "motion_swipe";
    public static final String KEY_SWIPE_ANSWER = "motion_swipe_answer";
    public static final String KEY_TAKE_OFF = "motion_take_off";
    public static final String KEY_TAKE_OFF_EAR = "motion_take_off_ear";
    public static final String KEY_TAP = "motion_tap";
    public static final String KEY_TAP_TOP = "motion_tap_top";
    public static final String KEY_TILT_LR = "motion_tilt_lr";
    public static final String KEY_TILT_LR_MOVE_WIDGETS = "motion_tilt_lr_move_widgets";
    public static final String KEY_TILT_LR_SINGLE_HAND = "motion_tilt_lr_single_hand";

    @Deprecated
    public static final String MOTION_KEY_APPS_FLIP_SILENT = "motion_flip_silent";

    @Deprecated
    public static final String MOTION_KEY_APPS_PICKUP_WEAKEN = "motion_pickup_weaken";
    public static final String MOTION_KEY_APPS_PROXI_ANSWER = "motion_proximity_answer_call";
    public static final int MOTION_TYPE_APPS_FLIP_SILENT = 2;
    public static final int MOTION_TYPE_APPS_PICKUP_WEAKEN = 1;
    public static final int MOTION_TYPE_APPS_PROXIMITY_ANSWER = 3;
    public static final int MOTION_TYPE_OPERAND = 100;
    public static final int TYPE_ACTIVITY = 900;
    public static final int TYPE_ACTIVITY_RECO = 901;
    public static final int TYPE_FLIP = 200;
    public static final int TYPE_FLIP_MUTE_CALL = 201;
    public static final int TYPE_FLIP_MUTE_CLOCK = 202;
    public static final int TYPE_HW_STEP_COUNTER = 1100;
    public static final int TYPE_HW_STEP_COUNTER_HEALTH = 1101;
    public static final int TYPE_NOT_SUPPORT = -1;
    public static final int TYPE_PICKUP = 100;
    public static final int TYPE_PICKUP_END_HINTS = 103;
    public static final int TYPE_PICKUP_REDUCE_CALL = 101;
    public static final int TYPE_PICKUP_REDUCE_CLOCK = 102;
    public static final int TYPE_POCKET = 800;
    public static final int TYPE_POCKET_CALL_RAISE = 801;
    public static final int TYPE_PROXIMITY = 300;
    public static final int TYPE_PROXIMITY_ANSWER = 301;
    public static final int TYPE_PROXIMITY_BLUETOOTHSET = 305;
    public static final int TYPE_PROXIMITY_DIAL = 302;
    public static final int TYPE_PROXIMITY_SCREEN_OFF = 303;
    public static final int TYPE_PROXIMITY_SPEAKER = 304;
    public static final int TYPE_ROTATION = 700;
    public static final int TYPE_ROTATION_SCREEN = 701;
    public static final int TYPE_SHAKE = 400;
    public static final int TYPE_SHAKE_CHANGE_WALLPAPER = 402;
    public static final int TYPE_SHAKE_REARRAGE_WIDGETS = 403;
    public static final int TYPE_SHAKE_REFRESH = 401;
    public static final int TYPE_SHAKE_START_PRIVACY = 404;
    public static final int TYPE_SWIPE = 1200;
    public static final int TYPE_SWIPE_ANSWER = 1201;
    public static final int TYPE_TAKE_OFF = 1000;
    public static final int TYPE_TAKE_OFF_EAR = 1001;
    public static final int TYPE_TAP = 500;
    public static final int TYPE_TAP_TOP = 501;
    public static final int TYPE_TILT_LR = 600;
    public static final int TYPE_TILT_LR_MOVE_WIDGETS = 601;
    public static final int TYPE_TILT_LR_SINGLE_HAND = 602;

    private MotionTypeApps() {
    }

    public static int getMotionTypeByMotionApps(int i) {
        return i - (i % 100);
    }

    public static String getMotionKeyByMotionApps(int i) {
        switch (getMotionTypeByMotionApps(i)) {
            case 100:
                return getPickupKey(i);
            case 200:
                return getFlipKey(i);
            case 300:
                return getProxiKey(i);
            case 400:
                return getShakeKey(i);
            case 500:
                return getTapKey(i);
            case 600:
                return getTiltLrKey(i);
            case 800:
                return getPocketKey(i);
            case 900:
                return getActivityKey(i);
            case 1000:
                return getTakeoffKey(i);
            case 1100:
                return getHwStepCounterKey(i);
            case 1200:
                return getSwipeKey(i);
            default:
                return "";
        }
    }

    private static String getTiltLrKey(int i) {
        switch (i) {
            case 600:
                return KEY_TILT_LR;
            case 601:
                return KEY_TILT_LR_MOVE_WIDGETS;
            case 602:
                return KEY_TILT_LR_SINGLE_HAND;
            default:
                return "";
        }
    }

    private static String getTapKey(int i) {
        return i != 500 ? i != 501 ? "" : KEY_TAP_TOP : KEY_TAP;
    }

    private static String getTakeoffKey(int i) {
        return i != 1000 ? i != 1001 ? "" : KEY_TAKE_OFF_EAR : KEY_TAKE_OFF;
    }

    private static String getSwipeKey(int i) {
        return i != 1200 ? i != 1201 ? "" : KEY_SWIPE_ANSWER : KEY_SWIPE;
    }

    private static String getShakeKey(int i) {
        switch (i) {
            case 400:
                return KEY_SHAKE;
            case 401:
                return KEY_SHAKE_REFRESH;
            case 402:
                return KEY_SHAKE_CHANGE_WALLPAPER;
            case 403:
                return KEY_SHAKE_REARRAGE_WIDGETS;
            case 404:
                return KEY_SHAKE_START_PRIVACY;
            default:
                return "";
        }
    }

    private static String getProxiKey(int i) {
        switch (i) {
            case 300:
                return KEY_PROXI;
            case 301:
                return KEY_PROXI_ANSWER;
            case 302:
                return KEY_PROXI_DIAL;
            case 303:
                return KEY_PROXI_SCREEN_OFF;
            case 304:
                return KEY_PROXI_SPEAKER;
            case 305:
                return KEY_PROXI_BLUETOOTHSET;
            default:
                return "";
        }
    }

    private static String getPocketKey(int i) {
        return i != 800 ? i != 801 ? "" : KEY_POCKET_CALL_RAISE : KEY_POCKET;
    }

    private static String getPickupKey(int i) {
        switch (i) {
            case 100:
                return KEY_PICKUP;
            case 101:
                return KEY_PICKUP_REDUCE_CALL;
            case 102:
                return KEY_PICKUP_REDUCE_CLOCK;
            case 103:
                return KEY_PICKUP_END_HINTS;
            default:
                return "";
        }
    }

    private static String getHwStepCounterKey(int i) {
        return i != 1100 ? i != 1101 ? "" : KEY_HW_STEP_COUNTER_HEALTH : KEY_HW_STEP_COUNTER;
    }

    private static String getFlipKey(int i) {
        switch (i) {
            case 200:
                return KEY_FLIP;
            case 201:
                return KEY_FLIP_MUTE_CALL;
            case 202:
                return KEY_FLIP_MUTE_CLOCK;
            default:
                return "";
        }
    }

    private static String getActivityKey(int i) {
        return i != 900 ? i != 901 ? "" : KEY_ACTIVITY_RECO : KEY_ACTIVITY;
    }
}
