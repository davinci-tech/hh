package com.huawei.hwdevice.phoneprocess.mgr.exercise.utils;

import com.huawei.haf.bundle.extension.ComponentInfo;
import com.huawei.hihealthservice.old.model.OldToNewMotionPath;
import com.huawei.hms.kit.awareness.barrier.internal.e.a;
import com.huawei.hwcommonmodel.fitnessdatatype.HeartRateThresholdConfig;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.indoorequip.datastruct.MachineControlPointResponse;
import com.huawei.operation.share.HiHealthError;
import com.huawei.up.model.UserInfomation;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* loaded from: classes5.dex */
public class WorkoutTypeMapManager {
    private static final String TAG = "WorkoutTypeMapManager";
    private static Map<Integer, Integer> sWorkoutTypeMap = new HashMap(16);
    private static Map<Integer, Integer> sCloudWorkoutTypeMap = new HashMap(16);
    private static Set<Integer> sNewSportTypeSet = new HashSet(16);

    static {
        sWorkoutTypeMap.put(1, 258);
        sWorkoutTypeMap.put(9, 258);
        sWorkoutTypeMap.put(10, 258);
        sWorkoutTypeMap.put(2, 257);
        sWorkoutTypeMap.put(3, 259);
        sWorkoutTypeMap.put(4, 260);
        sWorkoutTypeMap.put(6, 262);
        sWorkoutTypeMap.put(8, Integer.valueOf(OldToNewMotionPath.SPORT_TYPE_OPEN_AREA_SWIM));
        sWorkoutTypeMap.put(5, 264);
        sWorkoutTypeMap.put(7, Integer.valueOf(OldToNewMotionPath.SPORT_TYPE_INDOOR_BIKE));
        sWorkoutTypeMap.put(11, Integer.valueOf(OldToNewMotionPath.SPORT_TYPE_CROSS_COUNTRY_RACE));
        sWorkoutTypeMap.put(255, Integer.valueOf(OldToNewMotionPath.SPORT_TYPE_OTHER_SPORT));
        sWorkoutTypeMap.put(12, 512);
        sWorkoutTypeMap.put(14, 282);
        sWorkoutTypeMap.put(13, 281);
        sWorkoutTypeMap.put(135, Integer.valueOf(OldToNewMotionPath.SPORT_TYPE_ROW_MACHINE));
        sWorkoutTypeMap.put(134, Integer.valueOf(OldToNewMotionPath.SPORT_TYPE_CROSS_TRAINER));
        sWorkoutTypeMap.put(Integer.valueOf(UserInfomation.WEIGHT_DEFAULT_ENGLISH), Integer.valueOf(OldToNewMotionPath.SPORT_TYPE_BASKETBALL));
        sWorkoutTypeMap.put(131, 270);
        sWorkoutTypeMap.put(136, Integer.valueOf(OldToNewMotionPath.SPORT_TYPE_TREAD_MACHINE));
        sWorkoutTypeMap.put(177, 261);
        sWorkoutTypeMap.put(15, 217);
        sWorkoutTypeMap.put(16, 218);
        sWorkoutTypeMap.put(17, 219);
        sWorkoutTypeMap.put(18, Integer.valueOf(HeartRateThresholdConfig.HEART_RATE_LIMIT));
        sWorkoutTypeMap.put(21, 283);
        sWorkoutTypeMap.put(22, 287);
        sWorkoutTypeMap.put(23, 288);
        sWorkoutTypeMap.put(24, Integer.valueOf(ComponentInfo.PluginPay_A_N));
        sWorkoutTypeMap.put(19, 286);
        sWorkoutTypeMap.put(25, 291);
        initNewSportTypes();
        initCloudType();
    }

    private WorkoutTypeMapManager() {
    }

    private static void initCloudType() {
        sCloudWorkoutTypeMap.put(258, 1);
        sCloudWorkoutTypeMap.put(259, 3);
        sCloudWorkoutTypeMap.put(264, 1);
    }

    private static void initNewSportTypes() {
        sNewSportTypeSet.add(128);
        sNewSportTypeSet.add(129);
        sNewSportTypeSet.add(Integer.valueOf(OldToNewMotionPath.SPORT_TYPE_TENNIS));
        sNewSportTypeSet.add(Integer.valueOf(OldToNewMotionPath.SPORT_TYPE_VOLLEYBALL));
        sNewSportTypeSet.add(140);
        sNewSportTypeSet.add(141);
        sNewSportTypeSet.add(136);
        sNewSportTypeSet.add(142);
        sNewSportTypeSet.add(143);
        sNewSportTypeSet.add(Integer.valueOf(OldToNewMotionPath.SPORT_TYPE_AEROBICS));
        sNewSportTypeSet.add(144);
        sNewSportTypeSet.add(137);
        sNewSportTypeSet.add(Integer.valueOf(OldToNewMotionPath.SPORT_TYPE_PILATES));
        sNewSportTypeSet.add(145);
        sNewSportTypeSet.add(146);
        sNewSportTypeSet.add(147);
        sNewSportTypeSet.add(148);
        sNewSportTypeSet.add(149);
        sNewSportTypeSet.add(150);
        sNewSportTypeSet.add(Integer.valueOf(HiHealthError.ERR_WRONG_DEVICE));
        sNewSportTypeSet.add(152);
        sNewSportTypeSet.add(153);
        sNewSportTypeSet.add(154);
        sNewSportTypeSet.add(155);
        sNewSportTypeSet.add(156);
        sNewSportTypeSet.add(157);
        sNewSportTypeSet.add(158);
        sNewSportTypeSet.add(159);
        sNewSportTypeSet.add(160);
        sNewSportTypeSet.add(Integer.valueOf(MachineControlPointResponse.OP_CODE_EXTENSION_SET_TOTAL_ENERGY));
        sNewSportTypeSet.add(Integer.valueOf(MachineControlPointResponse.OP_CODE_EXTENSION_SET_DYNAMIC_ENERGY));
        sNewSportTypeSet.add(Integer.valueOf(MachineControlPointResponse.OP_CODE_EXTENSION_SET_STEP_COUNT));
        sNewSportTypeSet.add(Integer.valueOf(MachineControlPointResponse.OP_CODE_EXTENSION_MULTI_DATA));
        sNewSportTypeSet.add(Integer.valueOf(MachineControlPointResponse.OP_CODE_EXTENSION_SET_UNLOCK_CODE));
        sNewSportTypeSet.add(Integer.valueOf(MachineControlPointResponse.OP_CODE_EXTENSION_SET_SUPPRESS_AUTO_PAUSE));
        sNewSportTypeSet.add(Integer.valueOf(MachineControlPointResponse.OP_CODE_EXTENSION_SET_TARGETED_EXPENDED_ENERGY));
        sNewSportTypeSet.add(168);
        sNewSportTypeSet.add(169);
        sNewSportTypeSet.add(170);
        sNewSportTypeSet.add(171);
        sNewSportTypeSet.add(172);
        sNewSportTypeSet.add(173);
        sNewSportTypeSet.add(174);
        sNewSportTypeSet.add(175);
        sNewSportTypeSet.add(176);
        sNewSportTypeSet.add(177);
        sNewSportTypeSet.add(178);
        initNewSportType();
    }

    private static void initNewSportType() {
        sNewSportTypeSet.add(179);
        sNewSportTypeSet.add(180);
        sNewSportTypeSet.add(181);
        sNewSportTypeSet.add(182);
        sNewSportTypeSet.add(183);
        sNewSportTypeSet.add(184);
        sNewSportTypeSet.add(185);
        sNewSportTypeSet.add(186);
        sNewSportTypeSet.add(187);
        sNewSportTypeSet.add(188);
        sNewSportTypeSet.add(189);
        sNewSportTypeSet.add(190);
        sNewSportTypeSet.add(191);
        sNewSportTypeSet.add(192);
        sNewSportTypeSet.add(193);
        sNewSportTypeSet.add(194);
        sNewSportTypeSet.add(195);
        sNewSportTypeSet.add(196);
        sNewSportTypeSet.add(197);
        sNewSportTypeSet.add(198);
        sNewSportTypeSet.add(199);
        sNewSportTypeSet.add(200);
        sNewSportTypeSet.add(201);
        sNewSportTypeSet.add(202);
        sNewSportTypeSet.add(203);
        sNewSportTypeSet.add(204);
        sNewSportTypeSet.add(205);
        sNewSportTypeSet.add(206);
        sNewSportTypeSet.add(Integer.valueOf(a.w));
        sNewSportTypeSet.add(Integer.valueOf(a.z));
        sNewSportTypeSet.add(Integer.valueOf(a.A));
        sNewSportTypeSet.add(Integer.valueOf(a.C));
        sNewSportTypeSet.add(Integer.valueOf(a.D));
        sNewSportTypeSet.add(Integer.valueOf(a.K));
        sNewSportTypeSet.add(Integer.valueOf(a.L));
        sNewSportTypeSet.add(Integer.valueOf(a.M));
        sNewSportTypeSet.add(Integer.valueOf(a.N));
        sNewSportTypeSet.add(216);
        sNewSportTypeSet.add(15);
        sNewSportTypeSet.add(16);
        sNewSportTypeSet.add(17);
        sNewSportTypeSet.add(18);
        sNewSportTypeSet.add(20);
        sNewSportTypeSet.add(21);
        sNewSportTypeSet.add(19);
        addNewSportType();
    }

    private static void addNewSportType() {
        sNewSportTypeSet.add(22);
        sNewSportTypeSet.add(23);
        sNewSportTypeSet.add(24);
        sNewSportTypeSet.add(25);
        sNewSportTypeSet.add(222);
        sNewSportTypeSet.add(Integer.valueOf(UserInfomation.WEIGHT_DEFAULT_ENGLISH));
        sNewSportTypeSet.add(131);
        sNewSportTypeSet.add(223);
        sNewSportTypeSet.add(224);
    }

    public static Map<Integer, Integer> getWorkoutTypeMap() {
        return sWorkoutTypeMap;
    }

    public static Map<Integer, Integer> getCloudWorkTypeMap() {
        return sCloudWorkoutTypeMap;
    }

    public static int getWorkoutType(int i) {
        LogUtil.a(TAG, "getWorkoutType deviceSportType:", Integer.valueOf(i));
        Integer num = sWorkoutTypeMap.get(Integer.valueOf(i));
        if (num == null) {
            LogUtil.h(TAG, "getWorkoutType() sportType of APP is the same as device");
            return 0;
        }
        LogUtil.a(TAG, "getWorkoutType workoutType:", num);
        return num.intValue();
    }

    public static boolean isNewSportType(int i) {
        LogUtil.a(TAG, "isNewSportType85() deviceSportType:", Integer.valueOf(i));
        return sNewSportTypeSet.contains(Integer.valueOf(i));
    }
}
