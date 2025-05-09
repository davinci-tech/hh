package defpackage;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.huawei.devicesdk.entity.DeviceInfo;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import health.compact.a.LogUtil;

/* loaded from: classes3.dex */
public class bhr {
    public static void a(DeviceCapability deviceCapability, int i) {
        if (deviceCapability == null) {
            LogUtil.a("UpdateOldCapability", "setOldDeviceCapability deviceCapability is null");
            return;
        }
        switch (i) {
            case 7:
            case 14:
            case 16:
                c(deviceCapability);
                break;
            case 8:
                g(deviceCapability);
                break;
            case 9:
            default:
                e(deviceCapability);
                break;
            case 10:
                h(deviceCapability);
                break;
            case 11:
                a(deviceCapability);
                break;
            case 12:
                d(deviceCapability);
                break;
            case 13:
                j(deviceCapability);
                break;
            case 15:
                f(deviceCapability);
                break;
        }
        try {
            LogUtil.c("UpdateOldCapability", "UpdateDeviceCapability:", new Gson().toJson(deviceCapability, DeviceCapability.class));
        } catch (JsonIOException unused) {
            LogUtil.e("UpdateOldCapability", "UpdateDeviceCapability JsonIOException");
        }
    }

    private static void h(DeviceCapability deviceCapability) {
        if (deviceCapability == null) {
            return;
        }
        deviceCapability.configureSupportGetFirmwareVersion(true);
        deviceCapability.configureSupportGetBattery(true);
        deviceCapability.configureGoldCard(true);
        deviceCapability.configureMotionGoalCap(1);
        deviceCapability.configureFitnessFrameType(1);
        deviceCapability.configureEventAlarmNum(0);
        deviceCapability.configureClimb(true);
        deviceCapability.configureWalk(true);
        deviceCapability.configureRun(true);
        deviceCapability.configureStep(true);
        deviceCapability.configureCalorie(true);
        deviceCapability.configureDistance(true);
        deviceCapability.configureIsSupportHelp(true);
        deviceCapability.configureDistanceDetail(true);
    }

    private static void e(DeviceCapability deviceCapability) {
        deviceCapability.configureIsSupportHelp(true);
        deviceCapability.configurePromptPush(-1);
    }

    private static void c(DeviceCapability deviceCapability) {
        deviceCapability.configureSupportTimeSetting(true);
        deviceCapability.configureSupportGetFirmwareVersion(true);
        deviceCapability.configureSupportGetBattery(true);
        deviceCapability.configureAutoLightScreen(true);
        deviceCapability.configureAvoidDisturb(true);
        deviceCapability.configureFactoryReset(true);
        deviceCapability.configureSupportNotificationIntervalInfo(true);
        deviceCapability.configureSupportAuthenticDevice(true);
        deviceCapability.configureMessageAlert(true);
        deviceCapability.configureSupportMessageAlertInfo(true);
        deviceCapability.configureContacts(true);
        deviceCapability.configureMotionGoalCap(1);
        deviceCapability.configureFitnessFrameType(0);
        deviceCapability.configureActivityReminder(true);
        deviceCapability.configureEventAlarm(true);
        deviceCapability.configureSmartAlarm(true);
        deviceCapability.configureOtaUpdate(true);
        deviceCapability.configureMaintenance(true);
        deviceCapability.configureMaintenanceInTime(true);
        deviceCapability.configureMaintenanceGetData(true);
        deviceCapability.configureSupportAntiLost(true);
        deviceCapability.configureLanguage(true);
        deviceCapability.configureSleep(true);
        deviceCapability.configureClimb(true);
        deviceCapability.configureRiding(true);
        deviceCapability.configureSleepShallow(true);
        deviceCapability.configureSleepDeep(true);
        deviceCapability.configureWalk(true);
        deviceCapability.configureRun(true);
        deviceCapability.configureStep(true);
        deviceCapability.configureCalorie(true);
        deviceCapability.configureDistance(true);
        deviceCapability.configurePromptPush(-1);
        deviceCapability.configureIsSupportHelp(true);
        deviceCapability.configureDistanceDetail(true);
    }

    private static void d(DeviceCapability deviceCapability) {
        if (deviceCapability == null) {
            return;
        }
        deviceCapability.configureSupportTimeSetting(true);
        deviceCapability.configureSupportGetFirmwareVersion(true);
        deviceCapability.configureSupportGetBattery(true);
        deviceCapability.configureAutoLightScreen(true);
        deviceCapability.configureAvoidDisturb(true);
        deviceCapability.configureFactoryReset(true);
        deviceCapability.configureSupportNotificationIntervalInfo(true);
        deviceCapability.configureSupportActivityType(true);
        deviceCapability.configureSupportAuthenticDevice(true);
        deviceCapability.configureMessageAlert(true);
        deviceCapability.configureSupportMessageAlertInfo(true);
        deviceCapability.configureSupportMessageSupportInfo(true);
        deviceCapability.configureFitnessFrameType(3);
        deviceCapability.configureActivityReminder(true);
        deviceCapability.configureSupportSetUserInfoEncrypt(true);
        deviceCapability.configureSupportSampleFrame(true);
        deviceCapability.configureSupportThreshold(true);
        deviceCapability.configureReserveSync(true);
        deviceCapability.configureIsSupportHeartRateZone(true);
        deviceCapability.configureEventAlarm(true);
        deviceCapability.configureSmartAlarm(true);
        deviceCapability.configureOtaUpdate(true);
        deviceCapability.configureMaintenance(true);
        deviceCapability.configureMaintenanceInTime(true);
        deviceCapability.configureMaintenanceGetData(true);
        deviceCapability.configureBluetoothOffAlert(true);
        deviceCapability.configureLanguage(true);
        deviceCapability.configureSupportHeartRateInfo(true);
        deviceCapability.configureSleep(true);
        deviceCapability.configureRiding(true);
        deviceCapability.configureSleepShallow(true);
        deviceCapability.configureSleepDeep(true);
        deviceCapability.configureWalk(true);
        deviceCapability.configureRun(true);
        deviceCapability.configureStep(true);
        deviceCapability.configureCalorie(true);
        deviceCapability.configureDistance(true);
        deviceCapability.configureIsSupportHeartRate(true);
        deviceCapability.configurePromptPush(-1);
        deviceCapability.configureIsSupportHelp(true);
        deviceCapability.configureDistanceDetail(true);
    }

    private static void j(DeviceCapability deviceCapability) {
        d(deviceCapability);
        deviceCapability.configureSupportLeftRightHandWearMode(true);
        deviceCapability.configureisSupportHeartRateEnable(true);
        deviceCapability.configureRotateSwitchScreen(true);
    }

    private static void f(DeviceCapability deviceCapability) {
        d(deviceCapability);
    }

    private static void g(DeviceCapability deviceCapability) {
        d(deviceCapability);
        deviceCapability.configureSupportLeftRightHandWearMode(true);
        deviceCapability.configureisSupportHeartRateEnable(true);
        deviceCapability.configureRotateSwitchScreen(true);
    }

    private static void a(DeviceCapability deviceCapability) {
        if (deviceCapability == null) {
            return;
        }
        deviceCapability.configureSupportGetFirmwareVersion(true);
        deviceCapability.configureAvoidDisturb(true);
        deviceCapability.configureFactoryReset(true);
        deviceCapability.configureSupportNotificationIntervalInfo(true);
        deviceCapability.configureSupportActivityType(true);
        deviceCapability.configureSupportAuthenticDevice(true);
        deviceCapability.configureFitnessFrameType(3);
        deviceCapability.configureReserveSync(true);
        deviceCapability.configureIsSupportHeartRateZone(true);
        deviceCapability.configureLanguage(true);
        deviceCapability.configureSupportHeartRateInfo(true);
        deviceCapability.configureWalk(true);
        deviceCapability.configureRun(true);
        deviceCapability.configureStep(true);
        deviceCapability.configureCalorie(true);
        deviceCapability.configureDistance(true);
        deviceCapability.configureIsSupportHeartRate(true);
        deviceCapability.configurePromptPush(-1);
        deviceCapability.configureIsSupportHelp(true);
        deviceCapability.configureDistanceDetail(true);
    }

    private static void b(DeviceCapability deviceCapability) {
        deviceCapability.resetDeviceCapability();
        deviceCapability.configureSupportGetFirmwareVersion(true);
        deviceCapability.configureSupportGetBattery(true);
        deviceCapability.configureSupportGetHandsetInfo(true);
        deviceCapability.configureSupportNotificationIntervalInfo(true);
        deviceCapability.configureSupportActivityType(true);
        deviceCapability.configureSupportAuthenticDevice(true);
        deviceCapability.configureGoldCard(true);
        deviceCapability.configureMotionGoalCap(1);
        deviceCapability.configureFitnessFrameType(3);
        deviceCapability.configureSupportSampleFrame(true);
        deviceCapability.configureSupportThreshold(true);
        deviceCapability.configureReserveSync(true);
        deviceCapability.configureIsSupportHeartRateZone(true);
        deviceCapability.configureSupportGetUserInfo(true);
        deviceCapability.configureEventAlarmNum(0);
        deviceCapability.configureMaintenance(true);
        deviceCapability.configureLanguage(true);
        deviceCapability.configureSupportExerciseAdvice(true);
        deviceCapability.configureSupportExerciseAdviceTime(true);
        deviceCapability.configureSupportWorkoutRecord(true);
        deviceCapability.configureClimb(true);
        deviceCapability.configureWalk(true);
        deviceCapability.configureRun(true);
        deviceCapability.configureStep(true);
        deviceCapability.configureCalorie(true);
        deviceCapability.configureDistance(true);
        deviceCapability.configurePromptPush(0);
        deviceCapability.configureIsSupportHelp(true);
        deviceCapability.configureDistanceDetail(true);
        deviceCapability.configureSupportPay(true);
    }

    public static void b(DeviceInfo deviceInfo, DeviceCapability deviceCapability) {
        if (deviceInfo == null || deviceCapability == null) {
            LogUtil.a("UpdateOldCapability", "device or deviceCapability is null");
            return;
        }
        int deviceType = deviceInfo.getDeviceType();
        String deviceVersion = deviceInfo.getDeviceVersion();
        String deviceSoftVersion = deviceInfo.getDeviceSoftVersion();
        if (deviceType == 10 && "73617766697368".equals(deviceVersion) && "372E312E31".equals(deviceSoftVersion)) {
            LogUtil.c("UpdateOldCapability", "leo problem version, reset capability");
            b(deviceCapability);
        }
        if (deviceType == 10 && "736177736861726B".equals(deviceVersion)) {
            if ("4E5847313250".equals(deviceSoftVersion) || "4E4C4731334E".equals(deviceSoftVersion)) {
                LogUtil.c("UpdateOldCapability", "leo problem version, reset heart rate capability");
                deviceCapability.configureSupportHeartRateInfo(false);
            }
        }
    }
}
