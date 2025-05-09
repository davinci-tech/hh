package com.huawei.hihealth;

import com.huawei.hiai.awareness.AwarenessConstants;
import com.huawei.hihealthservice.old.model.OldToNewMotionPath;

/* loaded from: classes.dex */
public class CharacteristicConstant {

    public static final class BloodPressureAbnormal {
    }

    public static final class GoalType {
    }

    public static final class RecurrenceUnit {
    }

    public static final class SubscriptionMode {
    }

    public static final class TrendPeriods {
    }

    public enum StepValueInterval {
        STEP_SUM_500(500),
        STEP_SUM_1000(1000),
        STEP_SUM_2000(2000),
        STEP_SUM_5000(5000);

        int value;

        StepValueInterval(int i) {
            this.value = i;
        }

        public static boolean containsValue(int i) {
            for (StepValueInterval stepValueInterval : values()) {
                if (i == stepValueInterval.getValue()) {
                    return true;
                }
            }
            return false;
        }

        public int getValue() {
            return this.value;
        }
    }

    public enum CaloriesValueInterval {
        CALORIES_SUM_20(20000),
        CALORIES_SUM_50(50000),
        CALORIES_SUM_100(100000),
        CALORIES_SUM_200(AwarenessConstants.REGISTER_SUCCESS_CODE);

        int value;

        CaloriesValueInterval(int i) {
            this.value = i;
        }

        public static boolean containsValue(int i) {
            for (CaloriesValueInterval caloriesValueInterval : values()) {
                if (i == caloriesValueInterval.getValue()) {
                    return true;
                }
            }
            return false;
        }

        public int getValue() {
            return this.value;
        }
    }

    public enum DistanceValueInterval {
        DISTANCE_SUM_500(500),
        DISTANCE_SUM_1000(1000),
        DISTANCE_SUM_2000(2000),
        DISTANCE_SUM_5000(5000);

        int value;

        DistanceValueInterval(int i) {
            this.value = i;
        }

        public static boolean containsValue(int i) {
            for (DistanceValueInterval distanceValueInterval : values()) {
                if (i == distanceValueInterval.getValue()) {
                    return true;
                }
            }
            return false;
        }

        public int getValue() {
            return this.value;
        }
    }

    public enum ReportDataType {
        DATA_POINT_STEP_SUM(40002),
        DATA_POINT_CALORIES_SUM(40003),
        DATA_POINT_DISTANCE_SUM(40004);

        int dataType;

        ReportDataType(int i) {
            this.dataType = i;
        }

        public static boolean containsType(int i) {
            for (ReportDataType reportDataType : values()) {
                if (i == reportDataType.getDataType()) {
                    return true;
                }
            }
            return false;
        }

        public int getDataType() {
            return this.dataType;
        }
    }

    public enum ReportType {
        TARGET(1),
        VALUE_INTERVAL(2);

        int reportTypeValue;

        ReportType(int i) {
            this.reportTypeValue = i;
        }

        public static boolean containsType(int i) {
            for (ReportType reportType : values()) {
                if (i == reportType.getReportTypeValue()) {
                    return true;
                }
            }
            return false;
        }

        public static boolean containsAuthIgnore(int i) {
            return i == TARGET.getReportTypeValue();
        }

        public int getReportTypeValue() {
            return this.reportTypeValue;
        }
    }

    public enum FtmpType {
        FTMP_NOT_SUPPORTED(0),
        FTMP_SUPPORTED(1);

        int ftmpTypeValue;

        FtmpType(int i) {
            this.ftmpTypeValue = i;
        }

        public int getFtmpTypeValue() {
            return this.ftmpTypeValue;
        }
    }

    public enum DeviceType {
        TYPE_ROWER_INDEX(261),
        TYPE_TREADMILL_INDEX(31),
        TYPE_INDOORBIKE_INDEX(259),
        TYPE_CROSSTRAINER_INDEX(260);

        int deviceTypeValue;

        DeviceType(int i) {
            this.deviceTypeValue = i;
        }

        public int getDeviceTypeValue() {
            return this.deviceTypeValue;
        }
    }

    public enum EnhanceSportType {
        SPORT_TYPE_ROWER(290),
        SPORT_TYPE_ROWERSTRENGTH(291),
        SPORT_TYPE_ROW_MACHINE(OldToNewMotionPath.SPORT_TYPE_ROW_MACHINE),
        SPORT_TYPE_ROPE_SKIPPING(283),
        SPORT_TYPE_TREADMILL(264),
        SPORT_TYPE_CROSS_TRAINER(OldToNewMotionPath.SPORT_TYPE_CROSS_TRAINER),
        SPORT_TYPE_INDOOR_BIKE(OldToNewMotionPath.SPORT_TYPE_INDOOR_BIKE),
        SPORT_TYPE_WALK(257),
        SPORT_TYPE_RUN(258),
        SPORT_TYPE_BIKE(259),
        SPORT_TYPE_OTHER_SPORT(OldToNewMotionPath.SPORT_TYPE_OTHER_SPORT);

        int enhanceSportTypeValue;

        EnhanceSportType(int i) {
            this.enhanceSportTypeValue = i;
        }

        public int getEnhanceSportTypeValue() {
            return this.enhanceSportTypeValue;
        }
    }

    public enum SportModeType {
        FOREGROUND_SPORT_MODE(0),
        BACKGROUND_SPORT_MODE(1);

        int type;

        SportModeType(int i) {
            this.type = i;
        }

        public int getType() {
            return this.type;
        }
    }

    public enum SubscribeType {
        SPO2_RESP_INFECTION(700005),
        RRI_RESP_INFECTION(700006),
        TEMPERATURE_RESP_INFECTION(700007),
        SLEEP_FRAGMENT_RESP_INFECTION(700008),
        DATA_SEQUENCE_CNTBP_PPG_DATA(700010),
        BG_DAILY_PROB_WIN(700015),
        BG_COMBINED_PPG_FEATURE(700016);

        int dataType;

        SubscribeType(int i) {
            this.dataType = i;
        }

        public static boolean containsType(int i) {
            for (SubscribeType subscribeType : values()) {
                if (i == subscribeType.getType()) {
                    return true;
                }
            }
            return false;
        }

        public int getType() {
            return this.dataType;
        }
    }

    public enum StopSportSource {
        AIDL_DISCONNECTED("aidl_disconnected");

        String source;

        StopSportSource(String str) {
            this.source = str;
        }

        public String getSource() {
            return this.source;
        }
    }

    public enum SportRecordSaveModel {
        NOT_SAVE(0),
        SAVE(1);

        int value;

        SportRecordSaveModel(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }
    }
}
