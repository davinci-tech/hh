package defpackage;

import com.huawei.health.devicemgr.business.entity.DeviceCapability;

/* loaded from: classes5.dex */
public class izd {
    public static void c(DeviceCapability deviceCapability) {
        if (deviceCapability == null) {
            return;
        }
        deviceCapability.configureSupportTimeSetting(false);
        deviceCapability.configureSupportGetFirmwareVersion(true);
        deviceCapability.configureSupportGetBattery(true);
        deviceCapability.configureAutoLightScreen(false);
        deviceCapability.configureAvoidDisturb(false);
        deviceCapability.configureFactoryReset(false);
        deviceCapability.configureSupportPairDevice(false);
        deviceCapability.configureSupportGetHandsetInfo(false);
        deviceCapability.configureSupportNotificationIntervalInfo(false);
        deviceCapability.configureSupportActivityType(false);
        deviceCapability.configureSupportAuthenticDevice(false);
        deviceCapability.configureGoldCard(true);
        deviceCapability.configureSupportLeftRightHandWearMode(false);
        deviceCapability.configureMessageAlert(false);
        deviceCapability.configureSupportMessageAlertInfo(false);
        deviceCapability.configureSupportMessageSupportInfo(false);
        deviceCapability.configureupportMessageCenterPushDevice(false);
        deviceCapability.configureContacts(false);
        deviceCapability.configureSupportCallingOperationType(false);
        deviceCapability.configureMotionGoalCap(1);
        deviceCapability.configureFitnessFrameType(1);
        deviceCapability.configureActivityReminder(false);
        deviceCapability.configureSupportSetUserInfoEncrypt(false);
        deviceCapability.configureSupportSampleFrame(false);
        deviceCapability.configureSupportThreshold(false);
        deviceCapability.configureReserveSync(false);
        deviceCapability.configureIsSupportHeartRateZone(false);
        deviceCapability.configureIsSupportCoreSleep(false);
        deviceCapability.configureIsSupportSendCoreSleepOutState(false);
        deviceCapability.configureIsSupportQueryDeviceCoreSleepSwitch(false);
        deviceCapability.configureEventAlarm(false);
        deviceCapability.configureEventAlarmNum(0);
        deviceCapability.configureSmartAlarm(false);
        deviceCapability.configureOtaUpdate(false);
        deviceCapability.configureMaintenance(false);
        deviceCapability.configureMaintenanceInTime(false);
        deviceCapability.configureMaintenanceGetData(false);
        deviceCapability.configureSupportAntiLost(false);
        deviceCapability.configureBluetoothOffAlert(false);
        deviceCapability.configureLanguage(false);
        deviceCapability.configureWeatherPush(false);
        deviceCapability.configureSupportUnitWeather(false);
        deviceCapability.configureAtmosphereSupportExpand(false);
        e(deviceCapability);
    }

    private static void e(DeviceCapability deviceCapability) {
        deviceCapability.configureSupportExerciseAdvice(false);
        deviceCapability.configureSupportExerciseAdviceTime(false);
        deviceCapability.configureSupportExerciseAdviceMonitor(false);
        deviceCapability.configureSupportWorkout(false);
        deviceCapability.configureSupportWorkoutInfo(false);
        deviceCapability.configureSupportWorkoutReminder(false);
        deviceCapability.configureSupportWorkoutRecord(false);
        deviceCapability.configureSupportWorkoutExerciseDisplayLink(false);
        deviceCapability.configureSupportWorkoutRecordPaceMap(false);
        deviceCapability.configureSupportGpsLocation(false);
        deviceCapability.configureSupportGpsData(false);
        deviceCapability.configureSupportHeartRateInfo(false);
        deviceCapability.configureSleep(false);
        deviceCapability.configureClimb(true);
        deviceCapability.configureRiding(false);
        deviceCapability.configureStand(false);
        deviceCapability.configureSleepShallow(false);
        deviceCapability.configureSleepDeep(false);
        deviceCapability.configureWalk(true);
        deviceCapability.configureRun(true);
        deviceCapability.configureStep(true);
        deviceCapability.configureCalorie(true);
        deviceCapability.configureDistance(true);
        deviceCapability.configureIsSupportHeartRate(false);
        deviceCapability.configurePromptPush(0);
        deviceCapability.configureCallMute(false);
        deviceCapability.configureIsSupportHelp(true);
        deviceCapability.configureDistanceDetail(true);
        deviceCapability.configureRotateSwitchScreen(false);
        deviceCapability.configureSupportQueryAllowDisturbContent(false);
    }
}
