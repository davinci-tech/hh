package defpackage;

import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import health.compact.a.LogUtil;

/* loaded from: classes5.dex */
public class iyz {
    private static void q(int i, boolean z, DeviceCapability deviceCapability) {
        if (deviceCapability == null) {
            return;
        }
        t(i, z, deviceCapability);
        y(i, z, deviceCapability);
    }

    private static void t(int i, boolean z, DeviceCapability deviceCapability) {
        if (i == 4) {
            LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2DMSCapability() setSupportTimeSetting() supportValue :", Boolean.valueOf(z));
            deviceCapability.configureSupportTimeSetting(z);
        } else if (i == 43) {
            LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2DMSCapability() setSupportAppId supportValue :", Boolean.valueOf(z));
            deviceCapability.configureSupportAppId(z);
        } else if (i == 48) {
            LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2DMSCapability() setSupportAppId supportValue :", Boolean.valueOf(z));
            deviceCapability.configureSupportUserSetting(z);
        } else if (i == 7) {
            LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2DMSCapability() setSupportGetFirmwareVersion() supportValue :", Boolean.valueOf(z));
            deviceCapability.configureSupportGetFirmwareVersion(z);
        } else if (i == 8) {
            LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2DMSCapability() setSupportGetBattery() supportValue :", Boolean.valueOf(z));
            deviceCapability.configureSupportGetBattery(z);
        } else if (i == 9) {
            LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2DMSCapability() setAuto_light_screen() supportValue :", Boolean.valueOf(z));
            deviceCapability.configureAutoLightScreen(z);
        } else if (i == 26) {
            LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2DMSCapability() setSupportLeftRightHandWearMode supportValue :", Boolean.valueOf(z));
            deviceCapability.configureSupportLeftRightHandWearMode(z);
        } else if (i == 27) {
            LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2DMSCapability() setRotate_switch_screen() supportValue :", Boolean.valueOf(z));
            deviceCapability.configureRotateSwitchScreen(z);
        }
        s(i, z, deviceCapability);
    }

    private static void s(int i, boolean z, DeviceCapability deviceCapability) {
        if (i == 10) {
            LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2DMSCapability() setAvoid_disturb() supportValue :", Boolean.valueOf(z));
            deviceCapability.configureAvoidDisturb(z);
        }
        if (i == 29) {
            LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2DMSCapability() setQueryAllowDisturbContent() supportValue :", Boolean.valueOf(z));
            deviceCapability.configureSupportQueryAllowDisturbContent(z);
            return;
        }
        switch (i) {
            case 33:
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2DMSCapability() setDeviceDefaultSwitch supportValue :", Boolean.valueOf(z));
                deviceCapability.setSupportDefaultSwitch(z);
                break;
            case 34:
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2DMSCapability() setSupportIntelligentHomeLinkage() supportValue :", Boolean.valueOf(z));
                deviceCapability.configureSupportIntelligentHomeLinkage(z);
                break;
            case 35:
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2DMSCapability() configureIsSupportNotifyDeviceBroadCast supportValue :", Boolean.valueOf(z));
                deviceCapability.configureIsSupportNotifyDeviceBroadCast(z);
                break;
            case 36:
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2DMSCapability() configureIsSupportDoublePhone supportValue :", Boolean.valueOf(z));
                deviceCapability.configureIsSupportPhonesInfo(z);
                break;
            default:
                LogUtil.a("ParseDeviceCapability", "handleV2DMSCapabilityCaseOneElse() unknown commandId.");
                break;
        }
    }

    private static void y(int i, boolean z, DeviceCapability deviceCapability) {
        if (i == 13) {
            LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2DMSCapability() setFactory_reset() supportValue :", Boolean.valueOf(z));
            deviceCapability.configureFactoryReset(z);
        } else if (i == 14) {
            LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2DMSCapability() setSupportPairDevice() supportValue :", Boolean.valueOf(z));
            deviceCapability.configureSupportPairDevice(z);
        } else if (i != 41) {
            switch (i) {
                case 16:
                    LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2DMSCapability() setSupportGetHandsetInfo() supportValue :", Boolean.valueOf(z));
                    deviceCapability.configureSupportGetHandsetInfo(z);
                    break;
                case 17:
                    LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2DMSCapability() setSupportNotificationIntervalInfo() supportValue :", Boolean.valueOf(z));
                    deviceCapability.configureSupportNotificationIntervalInfo(z);
                    break;
                case 18:
                    LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2DMSCapability() setSupportActivityType() supportValue :", Boolean.valueOf(z));
                    deviceCapability.configureSupportActivityType(z);
                    break;
                case 19:
                    LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2DMSCapability() setSupportAuthenticDevice() supportValue :", Boolean.valueOf(z));
                    deviceCapability.configureSupportAuthenticDevice(z);
                    break;
                case 20:
                    LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2DMSCapability() setGold_card() supportValue :", Boolean.valueOf(z));
                    deviceCapability.configureGoldCard(z);
                    break;
                default:
                    LogUtil.a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2DeviceCapability() default");
                    break;
            }
        } else {
            LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2DMSCapability() SupportRemoteCamera() supportValue :", Boolean.valueOf(z));
            deviceCapability.configureSupportRemoteCamera(z);
        }
        u(i, z, deviceCapability);
    }

    private static void u(int i, boolean z, DeviceCapability deviceCapability) {
        switch (i) {
            case 46:
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2DeviceCapability() SupportHttp supportValue :", Boolean.valueOf(z));
                deviceCapability.configureSupportHttps(z);
                break;
            case 47:
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2DeviceCapability() configureSupportSyncWifi supportValue :", Boolean.valueOf(z));
                deviceCapability.configureSupportSyncWifi(z);
                break;
            case 48:
            case 51:
            case 52:
            default:
                LogUtil.a("ParseDeviceCapability", "handleV2DeviceCapabilityCaseTwoElse() unknown commandId.");
                break;
            case 49:
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2DMSCapability() SupportRemoteSetting supportValue :", Boolean.valueOf(z));
                deviceCapability.configureSupportSettingRelated(z);
                break;
            case 50:
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2DeviceCapability() SupportTimeZoneId supportValue :", Boolean.valueOf(z));
                deviceCapability.configureSupportZoneId(z);
                break;
            case 53:
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2DeviceCapability() setConnectStatus() supportValue :", Boolean.valueOf(z));
                deviceCapability.configureSupportConnectStatus(z);
                break;
            case 54:
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2DMSCapability() SupportSyncTime supportValue :", Boolean.valueOf(z));
                deviceCapability.configureSupportSyncTime(z);
                break;
            case 55:
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2DeviceCapability() setExpandCapability() supportValue :", Boolean.valueOf(z));
                deviceCapability.configureSupportExpandCapability(z);
                break;
        }
    }

    private static void aq(int i, boolean z, DeviceCapability deviceCapability) {
        if (deviceCapability != null) {
            if (i == 1) {
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2NotificationCapability() setMessage_alert() supportValue :", Boolean.valueOf(z));
                deviceCapability.configureMessageAlert(z);
            }
            switch (i) {
                case 4:
                    LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2NotificationCapability() setSupportMessageAlertInfo() supportValue :", Boolean.valueOf(z));
                    deviceCapability.configureSupportMessageAlertInfo(z);
                    break;
                case 5:
                    LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2NotificationCapability() setSupportMessageSupportInfo() supportValue :", Boolean.valueOf(z));
                    deviceCapability.configureSupportMessageSupportInfo(z);
                    break;
                case 6:
                    LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2NotificationCapability() setSupportDeleteMsg() supportValue :", Boolean.valueOf(z));
                    deviceCapability.configureSupportDeleteMsg(z);
                    break;
                case 7:
                    LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2NotificationCapability() setMessageCenterPushDevice() supportValue :", Boolean.valueOf(z));
                    deviceCapability.configureupportMessageCenterPushDevice(z);
                    break;
                case 8:
                    LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2DMSCapability() SupportRemoteSetting supportValue :", Boolean.valueOf(z));
                    deviceCapability.configureSupportWearMessagePush(z);
                    break;
                default:
                    LogUtil.a("ParseDeviceCapability", "handleV2NotificationCapability() unknown commandId.");
                    break;
            }
        }
    }

    private static void m(int i, boolean z, DeviceCapability deviceCapability) {
        if (deviceCapability != null) {
            if (i == 1) {
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2AddressBookCapability() setContacts() supportValue :", Boolean.valueOf(z));
                deviceCapability.configureContacts(z);
            } else if (i == 3) {
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2AddressBookCapability() configureSyncContacts() supportValue :", Boolean.valueOf(z));
                deviceCapability.configureSyncHiCall(z);
            } else if (i == 4) {
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2AddressBookCapability() configureReceiveSyncContacts() supportValue :", Boolean.valueOf(z));
                deviceCapability.configureSyncContacts(z);
            } else {
                LogUtil.a("ParseDeviceCapability", "handleV2AddressBookCapability() unknown commandId.");
            }
        }
    }

    private static void p(int i, boolean z, DeviceCapability deviceCapability) {
        if (deviceCapability != null) {
            if (i == 1) {
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2CallingCapability() supportValue :", Boolean.valueOf(z));
                deviceCapability.configureSupportCallingOperationType(z);
            } else {
                LogUtil.a("ParseDeviceCapability", "handleV2CallingCapability() unknown commandId.");
            }
        }
    }

    private static void au(int i, boolean z, DeviceCapability deviceCapability) {
        if (deviceCapability != null) {
            if (i == 1) {
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2PingRingCapability() supportValue :", Boolean.valueOf(z));
            } else {
                LogUtil.a("ParseDeviceCapability", "handleV2PingRingCapability() unknown commandId.");
            }
        }
    }

    private static void am(int i, boolean z, DeviceCapability deviceCapability) {
        if (deviceCapability != null) {
            if (i == 1) {
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2MusicCapability() supportValue :", Boolean.valueOf(z));
            } else {
                LogUtil.a("ParseDeviceCapability", "handleV2MusicCapability() unknown commandId.");
            }
        }
    }

    private static void x(int i, boolean z, DeviceCapability deviceCapability) {
        if (deviceCapability == null) {
            return;
        }
        ab(i, z, deviceCapability);
        ac(i, z, deviceCapability);
    }

    private static void ab(int i, boolean z, DeviceCapability deviceCapability) {
        if (i == 1) {
            LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2FitnessCapability() setMotionGoalCap() supportValue :", Boolean.valueOf(z));
            deviceCapability.configureMotionGoalCap(1);
        }
        if (i == 3) {
            LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2FitnessCapability() setSupportSportTotal() supportValue :", Boolean.valueOf(z));
            deviceCapability.configureSupportSportTotal(z);
            return;
        }
        switch (i) {
            case 5:
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2FitnessCapability() setFitness_frame_type() supportValue :", Boolean.valueOf(z));
                if (z) {
                    deviceCapability.configureFitnessFrameType(0);
                    break;
                }
                break;
            case 6:
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2FitnessCapability() setActivity_reminder() supportValue :", Boolean.valueOf(z));
                deviceCapability.configureActivityReminder(z);
                break;
            case 7:
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2FitnessCapability() COMMAND_ID_SET_ACTIVE_REMINDER() supportValue :", Boolean.valueOf(z));
                deviceCapability.configureActivityReminder(z);
                break;
            case 8:
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2FitnessCapability() setFitness_frame_type() supportValue :", Boolean.valueOf(z));
                if (z) {
                    deviceCapability.configureFitnessFrameType(1);
                    break;
                }
                break;
            case 9:
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2FitnessCapability() setSupportSetUserInfoEncrypt() supportValue :", Boolean.valueOf(z));
                deviceCapability.configureSupportSetUserInfoEncrypt(z);
                break;
            default:
                LogUtil.a("ParseDeviceCapability", "handleV2FitnessCapabilityCaseOne() unknown commandId.");
                break;
        }
    }

    private static void ac(int i, boolean z, DeviceCapability deviceCapability) {
        if (i == 10) {
            LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2FitnessCapability() setSupportSampleFrame() supportValue :", Boolean.valueOf(z));
            deviceCapability.configureSupportSampleFrame(z);
            if (z) {
                deviceCapability.configureFitnessFrameType(3);
            }
        } else if (i == 14) {
            LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2FitnessCapability() setSupportThreshold() supportValue :", Boolean.valueOf(z));
            deviceCapability.configureSupportThreshold(z);
        } else if (i == 16) {
            LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2FitnessCapability() setReserveSync() supportValue :", Boolean.valueOf(z));
            deviceCapability.configureReserveSync(z);
        } else if (i == 19) {
            LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2FitnessCapability() configureIsSupportHeartRateZone() supportValue :", Boolean.valueOf(z));
            deviceCapability.configureIsSupportHeartRateZone(z);
        } else if (i == 22) {
            LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2FitnessCapability() setIsSupportCoreSleep() supportValue :", Boolean.valueOf(z));
            deviceCapability.configureIsSupportCoreSleep(z);
        } else if (i == 24) {
            LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2FitnessCapability() setIsSupportSendCoreSleepOutState() supportValue :", Boolean.valueOf(z));
            deviceCapability.configureIsSupportSendCoreSleepOutState(z);
        } else if (i != 27) {
            LogUtil.a("ParseDeviceCapability", "handleV2FitnessCapabilityCaseTwo() unknown commandId.");
        } else {
            LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2FitnessCapability() setIsSupportQueryDeviceCoreSleepSwitch() supportValue :", Boolean.valueOf(z));
            deviceCapability.configureIsSupportQueryDeviceCoreSleepSwitch(z);
        }
        z(i, z, deviceCapability);
    }

    private static void z(int i, boolean z, DeviceCapability deviceCapability) {
        if (i == 21) {
            LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2FitnessCapability() setSupportGetUserInfo() supportValue :", Boolean.valueOf(z));
            deviceCapability.configureSupportGetUserInfo(z);
            return;
        }
        if (i == 23) {
            LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2FitnessCapability() setIsSupportHeartRateEnable() supportValue :", Boolean.valueOf(z));
            deviceCapability.configureisSupportHeartRateEnable(z);
            return;
        }
        if (i != 33) {
            switch (i) {
                case 28:
                    LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2FitnessCapability() setSupportContinueHeartRate() supportValue :", Boolean.valueOf(z));
                    deviceCapability.configureSupportContinueHeartRate(z);
                    break;
                case 29:
                    LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2FitnessCapability() setSupportHeartRateRaiseAlarm() supportValue :", Boolean.valueOf(z));
                    deviceCapability.configureHeartRateRaiseAlarm(z);
                    break;
                case 30:
                    LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2FitnessCapability() setSupportGetHeartRateRaiseAlarmNumber() supportValue :", Boolean.valueOf(z));
                    deviceCapability.configureGetHeartRateRaiseAlarmNumber(z);
                    break;
                case 31:
                    LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2FitnessCapability() configureGetHighAndMiddleSport() supportValue :", Boolean.valueOf(z));
                    deviceCapability.configureGetHighAndMiddleSport(z);
                    break;
                default:
                    aa(i, z, deviceCapability);
                    break;
            }
            return;
        }
        LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2FitnessCapability() configureSetHrrHeartRateCapability() supportValue :", Boolean.valueOf(z));
        deviceCapability.configureSetHrrHeartRateCapability(z);
    }

    private static void aa(int i, boolean z, DeviceCapability deviceCapability) {
        switch (i) {
            case 34:
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2FitnessCapability() configureHeartRateDownAlarm supportValue: ", Boolean.valueOf(z));
                deviceCapability.configureHeartRateDownAlarm(z);
                break;
            case 35:
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2FitnessCapability() configureSupportRestHeartRateControls supportValue: ", Boolean.valueOf(z));
                deviceCapability.configureSupportRestHeartRateControls(z);
                break;
            case 36:
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2FitnessCapability() configureCycleBloodOxygenSwitch supportValue: ", Boolean.valueOf(z));
                deviceCapability.configureCycleBloodOxygenSwitch(z);
                break;
            case 37:
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2FitnessCapability() configureBloodOxygenDownRemind supportValue: ", Boolean.valueOf(z));
                deviceCapability.configureBloodOxygenDownRemind(z);
                break;
            case 38:
            case 39:
            default:
                LogUtil.a("ParseDeviceCapability", "handleV2FitnessCapabilityCase unknown commandId");
                break;
            case 40:
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2FitnessCapability() configureRunPaceSetCapability supportValue: ", Boolean.valueOf(z));
                deviceCapability.configureRunPaceSetCapability(z);
                break;
            case 41:
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2FitnessCapability() configureSupportMediumToHighStrengthPreValue supportValue: ", Boolean.valueOf(z));
                deviceCapability.configureSupportMediumToHighStrengthPreValue(z);
                break;
        }
    }

    private static void n(int i, boolean z, DeviceCapability deviceCapability) {
        if (deviceCapability != null) {
            if (i == 1) {
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2AlarmCapability() setEvent_alarm() supportValue :", Boolean.valueOf(z));
                deviceCapability.configureEventAlarm(z);
            } else if (i == 2) {
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2AlarmCapability() setSmart_alarm() supportValue :", Boolean.valueOf(z));
                deviceCapability.configureSmartAlarm(z);
            } else if (i == 3) {
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2AlarmCapability() setSmart_alarm() supportValue :", Boolean.valueOf(z));
                deviceCapability.configureChangeAlarm(z);
            } else {
                LogUtil.a("ParseDeviceCapability", "handleV2AlarmCapability commandId is unknown.");
            }
        }
    }

    private static void as(int i, boolean z, DeviceCapability deviceCapability) {
        if (deviceCapability != null) {
            if (i == 1) {
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2OTACapability() supportValue :", Boolean.valueOf(z));
                deviceCapability.configureOtaUpdate(z);
            }
            switch (i) {
                case 11:
                    LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2OTACapability() configureSyncBeforeOta :", Boolean.valueOf(z));
                    deviceCapability.configureSyncBeforeOta(z);
                    break;
                case 12:
                    LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2OTACapability() isSupportAutoUpdate :", Boolean.valueOf(z));
                    deviceCapability.configureIsSupportAutoUpdate(z);
                    break;
                case 13:
                    LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2OTACapability() isSupportUpdateChange :", Boolean.valueOf(z));
                    deviceCapability.configureIsSupportUpdateChange(z);
                    break;
                case 14:
                    LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2OTACapability() isSupportNotifyDeviceNewVersion :", Boolean.valueOf(z));
                    deviceCapability.configureIsSupportNotifyDeviceNewVersion(z);
                    break;
                case 15:
                    LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2OTACapability() isSupportDeviceRequestCheck :", Boolean.valueOf(z));
                    deviceCapability.configureIsSupportDeviceRequestCheck(z);
                    break;
                default:
                    LogUtil.a("ParseDeviceCapability", "handleV2OTACapability commandId is unknown.");
                    break;
            }
        }
    }

    private static void ae(int i, boolean z, DeviceCapability deviceCapability) {
        if (deviceCapability != null) {
            if (i == 1) {
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2MaintenanceCapability() setMaintenance() supportValue :", Boolean.valueOf(z));
                deviceCapability.configureMaintenance(z);
            } else if (i == 9) {
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2MaintenanceCapability() setMaintenance_in_time() supportValue :", Boolean.valueOf(z));
                deviceCapability.configureMaintenanceInTime(z);
            } else if (i == 10) {
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2MaintenanceCapability() setMaintenance_get_data() supportValue :", Boolean.valueOf(z));
                deviceCapability.configureMaintenanceGetData(z);
            } else {
                LogUtil.a("ParseDeviceCapability", "handleV2MaintenanceCapability commandId is unknown.");
            }
        }
    }

    private static void af(int i, boolean z, DeviceCapability deviceCapability) {
        if (deviceCapability != null) {
            if (i == 1) {
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2LinkLossCapability() setSupportAntiLost() supportValue :", Boolean.valueOf(z));
                deviceCapability.configureSupportAntiLost(z);
            } else if (i == 3) {
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2LinkLossCapability() setBluetooth_off_alert() supportValue :", Boolean.valueOf(z));
                deviceCapability.configureBluetoothOffAlert(z);
            } else {
                LogUtil.a("ParseDeviceCapability", "handleV2LinkLossCapability commandId is unknown.");
            }
        }
    }

    private static void ad(int i, boolean z, DeviceCapability deviceCapability) {
        if (deviceCapability != null) {
            if (i == 1) {
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2FontCapability() supportValue :", Boolean.valueOf(z));
                deviceCapability.configureLanguage(z);
            } else {
                LogUtil.a("ParseDeviceCapability", "handleV2FontCapability commandId is unknown.");
            }
        }
    }

    private static void at(int i, boolean z, DeviceCapability deviceCapability) {
        if (deviceCapability != null) {
            if (i == 1) {
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2PhoneBatteryCapability() supportValue :", Boolean.valueOf(z));
            } else {
                LogUtil.a("ParseDeviceCapability", "handleV2PhoneBatteryCapability commandId is unknown.");
            }
        }
    }

    private static void al(int i, boolean z, DeviceCapability deviceCapability) {
        if (deviceCapability != null) {
            if (i == 1) {
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2MotionCapability() supportValue :", Boolean.valueOf(z));
            } else {
                LogUtil.a("ParseDeviceCapability", "handleV2MotionCapability commandId is unknown.");
            }
        }
    }

    private static void bc(int i, boolean z, DeviceCapability deviceCapability) {
        if (deviceCapability != null) {
            if (i == 1) {
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2WeatherCapability() supportValue :", Boolean.valueOf(z));
                deviceCapability.configureWeatherPush(z);
                return;
            }
            if (i == 3) {
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2PushAtmosphereCapability() supportValue :", Boolean.valueOf(z));
                deviceCapability.configureSupportAtmosphere(z);
                return;
            }
            if (i == 10) {
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "setSupportDeviceFutureWeatherCapability supportValue :", Boolean.valueOf(z));
                deviceCapability.setSupportDeviceFutureWeatherCapability(z);
                return;
            }
            if (i == 5) {
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2PushUnitWeatherCapability() supportValue :", Boolean.valueOf(z));
                deviceCapability.configureSupportUnitWeather(z);
                return;
            }
            if (i == 6) {
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2PushAtmosphereSupportExpandCapability() supportValue :", Boolean.valueOf(z));
                deviceCapability.configureAtmosphereSupportExpand(z);
            } else if (i == 7) {
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2PushWeatherSupportErrorCodeCapability() supportValue :", Boolean.valueOf(z));
                deviceCapability.configureWeatherSupportErrorCode(z);
            } else if (i == 8) {
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2PushWeatherSupportDayInfo supportValue :", Boolean.valueOf(z));
                deviceCapability.configureWeatherSupportFutureInfo(z);
            } else {
                az(i, z, deviceCapability);
            }
        }
    }

    private static void az(int i, boolean z, DeviceCapability deviceCapability) {
        if (i == 11) {
            LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "setSupportTide supportValue :", Boolean.valueOf(z));
            deviceCapability.setSupportTide(z);
        } else if (i != 12) {
            LogUtil.a("ParseDeviceCapability", "handleV2CalendarCapability commandId is unknown.");
        } else {
            LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "setSupportWeatherErrorCode supportValue :", Boolean.valueOf(z));
            deviceCapability.setSupportWeatherErrorCode(z);
        }
    }

    private static void r(int i, boolean z, DeviceCapability deviceCapability) {
        if (deviceCapability != null) {
            if (i == 1) {
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2CalendarCapability() supportValue :", Boolean.valueOf(z));
            } else {
                LogUtil.a("ParseDeviceCapability", "handleV2CalendarCapability commandId is unknown.");
            }
        }
    }

    private static void ar(int i, boolean z, DeviceCapability deviceCapability) {
        if (deviceCapability != null) {
            if (i == 1) {
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2MusicManagementCapability() supportValue :", Boolean.valueOf(z));
            } else {
                LogUtil.a("ParseDeviceCapability", "handleV2MusicManagementCapability commandId is unknown.");
            }
        }
    }

    private static void w(int i, boolean z, DeviceCapability deviceCapability) {
        if (deviceCapability != null) {
            if (i == 1) {
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2FileTransferCapability() supportValue :", Boolean.valueOf(z));
            } else {
                LogUtil.a("ParseDeviceCapability", "handleV2FileTransferCapability commandId is unknown.");
            }
        }
    }

    private static void l(int i, boolean z, DeviceCapability deviceCapability) {
        if (deviceCapability != null) {
            if (i == 1) {
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2BTFactoryTestCapability() supportValue :", Boolean.valueOf(z));
            } else {
                LogUtil.a("ParseDeviceCapability", "handleV2ExerciseAdviceCapability commandId is unknown.");
            }
        }
    }

    private static void ah(int i, boolean z, DeviceCapability deviceCapability) {
        if (deviceCapability != null) {
            if (i == 1) {
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2MCUFactoryTestCapability() supportValue :", Boolean.valueOf(z));
            } else {
                LogUtil.a("ParseDeviceCapability", "handleV2ExerciseAdviceCapability commandId is unknown.");
            }
        }
    }

    private static void ax(int i, boolean z, DeviceCapability deviceCapability) {
        if (deviceCapability != null) {
            if (i == 1) {
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2SensorCapability() supportValue :", Boolean.valueOf(z));
            } else {
                LogUtil.a("ParseDeviceCapability", "handleV2ExerciseAdviceCapability unknown commandId.");
            }
        }
    }

    private static void v(int i, boolean z, DeviceCapability deviceCapability) {
        if (deviceCapability != null) {
            if (i == 1) {
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2ExerciseAdviceCapability() setSupportExerciseAdvice() supportValue :", Boolean.valueOf(z));
                deviceCapability.configureSupportExerciseAdvice(z);
            } else if (i == 3) {
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2ExerciseAdviceCapability() setSupportExerciseAdviceTime() supportValue :", Boolean.valueOf(z));
                deviceCapability.configureSupportExerciseAdviceTime(z);
            } else if (i == 7) {
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2ExerciseAdviceCapability() setSupportExerciseAdviceMonitor() supportValue :", Boolean.valueOf(z));
                deviceCapability.configureSupportExerciseAdviceMonitor(z);
            } else {
                LogUtil.a("ParseDeviceCapability", "handleV2ExerciseAdviceCapability unknown commandId.");
            }
        }
    }

    private static void bb(int i, boolean z, DeviceCapability deviceCapability) {
        if (deviceCapability != null) {
            if (i == 1) {
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2WorkoutCapability() setSupportWorkout() supportValue :", Boolean.valueOf(z));
                deviceCapability.configureSupportWorkout(z);
                return;
            }
            if (i == 4) {
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2WorkoutCapability() setSupportWorkoutInfo() supportValue :", Boolean.valueOf(z));
                deviceCapability.configureSupportWorkoutInfo(z);
                return;
            }
            if (i == 6) {
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2WorkoutCapability() setSupportWorkoutReminder() supportValue :", Boolean.valueOf(z));
                deviceCapability.configureSupportWorkoutReminder(z);
                return;
            }
            if (i == 7) {
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2WorkoutCapability() setSupportWorkoutRecord() supportValue :", Boolean.valueOf(z));
                deviceCapability.configureSupportWorkoutRecord(z);
            } else if (i == 11) {
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2WorkoutCapability() setSupportWorkoutExerciseDisplayLink() supportValue :", Boolean.valueOf(z));
                deviceCapability.configureSupportWorkoutExerciseDisplayLink(z);
            } else if (i == 12) {
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2WorkoutCapability() setSupportWorkoutRecordPaceMap() supportvalue :", Boolean.valueOf(z));
                deviceCapability.configureSupportWorkoutRecordPaceMap(z);
            } else {
                ay(i, z, deviceCapability);
                LogUtil.a("ParseDeviceCapability", "handleV2WorkoutCapability unknown commandId.");
            }
        }
    }

    private static void ay(int i, boolean z, DeviceCapability deviceCapability) {
        if (deviceCapability != null) {
            if (i == 16) {
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2WorkoutCapability() COMMAND_ID_RUN_POSTURE_REPORT() supportvalue :", Boolean.valueOf(z));
                deviceCapability.configureSupportRunPosture(z);
                return;
            }
            if (i == 18) {
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2WorkoutCapability() COMMAND_ID_INFORM_CLOSE_OR_OPEN_REPORT() supportvalue :", Boolean.valueOf(z));
                deviceCapability.configureSupportInformCloseOrOpen(z);
            } else if (i == 21) {
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2WorkoutCapability() COMMAND_ID_WORKOUT_CAPABILITY() supportvalue :", Boolean.valueOf(z));
                deviceCapability.configureSupportWorkoutCapabilicy(z);
            } else if (i == 23) {
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2WorkoutCapability() COMMAND_ID_WORKOUT_HEART_RATE_TRUST support value :", Boolean.valueOf(z));
                deviceCapability.configureSupportWorkoutTrustHeartRate(z);
            } else {
                LogUtil.a("ParseDeviceCapability", "handleV2WorkoutCapability unknown commandId.");
            }
        }
    }

    private static void ag(int i, boolean z, DeviceCapability deviceCapability) {
        if (deviceCapability != null) {
            if (i == 1) {
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2GPSLocationCapability() setSupportGPSLocation() supportValue :", Boolean.valueOf(z));
                deviceCapability.configureSupportGpsLocation(z);
                return;
            }
            if (i == 2) {
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2GPSLocationCapability() setSupportGPSData() supportValue :", Boolean.valueOf(z));
                deviceCapability.configureSupportGpsData(z);
                return;
            }
            if (i == 4) {
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2GPSLocationCapability() setSupportGPSSetParameter() supportValue :", Boolean.valueOf(z));
                deviceCapability.configureSupportGpsSetParameter(z);
                return;
            }
            if (i == 5) {
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2GPSLocationCapability() getPostProcessingInfo() supportValue :", Boolean.valueOf(z));
                deviceCapability.configureSupportGpsPostProcessing(z);
            } else if (i == 6) {
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2GPSLocationCapability() getSearchStarOptimization() supportValue :", Boolean.valueOf(z));
                deviceCapability.configureSupportGpsSearchStarOptimization(z);
            } else if (i == 9) {
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2GPSLocationCapability() setSupportActivityRecognitionStatus() supportValue :", Boolean.valueOf(z));
                deviceCapability.setSupportActivityRecognitionStatus(z);
            } else {
                LogUtil.a("ParseDeviceCapability", "handleV2GPSLocationCapability unknown commandId.");
            }
        }
    }

    private static void ak(int i, boolean z, DeviceCapability deviceCapability) {
        if (deviceCapability != null) {
            if (i == 1) {
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2HeartRateCapability() COMMAND_ID_MULTSIM_OPEN_ESIM :", Boolean.valueOf(z));
                deviceCapability.configureSupportEsim(z);
            } else if (i == 6) {
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2HeartRateCapability() COMMAND_ID_MULTSIM_QUIRE_SIM_INFO :", Boolean.valueOf(z));
                deviceCapability.configureSupportMultiSim(z);
            } else if (i == 10) {
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2HeartRateCapability() COMMAND_ID_MULTI_SIM_QUERY_BATTERY_THRESHOLD :", Boolean.valueOf(z));
                deviceCapability.configureSupportNewEsim(z);
            } else {
                LogUtil.a("ParseDeviceCapability", "handleV2MultSimCapability unknown commandId.");
            }
        }
    }

    private static void an(int i, boolean z, DeviceCapability deviceCapability) {
        if (deviceCapability != null) {
            if (i == 1) {
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2MidwareCapability() COMMAND_ID_MID_WARE :", Boolean.valueOf(z));
                deviceCapability.configureSupportMidware(z);
            } else {
                LogUtil.a("ParseDeviceCapability", "handleV2MidwareCapability unknown commandId.");
            }
        }
    }

    private static void ai(int i, boolean z, DeviceCapability deviceCapability) {
        if (deviceCapability != null) {
            if (i == 1) {
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2HeartRateCapability() supportValue :", Boolean.valueOf(z));
                deviceCapability.configureSupportHeartRateInfo(z);
            } else if (i == 4) {
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2HeartRateCapability() stress supportValue :", Boolean.valueOf(z));
                deviceCapability.configureSupportStressInfo(z);
            } else {
                LogUtil.a("ParseDeviceCapability", "handleV2HeartRateCapability unknown commandId.");
            }
        }
    }

    private static void ba(int i, boolean z, DeviceCapability deviceCapability) {
        if (deviceCapability != null) {
            if (i == 1) {
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2WalletCapability() supportValue :", Boolean.valueOf(z));
                deviceCapability.configureSupportPay(z);
                return;
            }
            if (i == 15) {
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2WalletCapability() supportOneTouch :", Boolean.valueOf(z));
                deviceCapability.setSupportOneTouch(z);
            } else if (i == 25) {
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2WalletCapability() support wallet open card: ", Boolean.valueOf(z));
                deviceCapability.configureSupportWalletOpenCard(z);
            } else if (i == 26) {
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2WalletCapability() show wallet entrance: ", Boolean.valueOf(z));
                deviceCapability.configureHideWalletEntrance(z);
            } else {
                LogUtil.a("ParseDeviceCapability", "handleV2WalletCapability unknown commandId.");
            }
        }
    }

    private static void aw(int i, boolean z, DeviceCapability deviceCapability) {
        if (deviceCapability != null) {
            if (i == 1) {
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2StressCapability() supportValue :", Boolean.valueOf(z));
                deviceCapability.configureSupportStress(z);
            } else if (i == 9) {
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "COMMAND_ID_SET_PRESS_SWITCH handleV2StressCapability() supportValue :", Boolean.valueOf(z));
                deviceCapability.configruePressAutoMonitor(z);
            } else if (i == 10) {
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "COMMAND_ID_SET_PRESS_APP_TO_DEVICE handleV2StressCapability() supportValue :", Boolean.valueOf(z));
                deviceCapability.configureIsSupportStressAppToDevice(z);
            } else {
                LogUtil.a("ParseDeviceCapability", "handleV2StressCapability unknown commandId.");
            }
        }
    }

    public static void c(int i, int i2, boolean z, DeviceCapability deviceCapability) {
        if (deviceCapability == null) {
            return;
        }
        b(i, i2, z, deviceCapability);
        i(i, i2, z, deviceCapability);
        a(i, i2, z, deviceCapability);
        e(i, i2, z, deviceCapability);
    }

    private static void k(int i, boolean z, DeviceCapability deviceCapability) {
        if (deviceCapability != null) {
            if (i == 1) {
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2AccountCapability() COMMAND_ID_MID_WARE :", Boolean.valueOf(z));
                deviceCapability.configureSupportAccount(z);
                return;
            }
            if (i == 3) {
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2AccountCapability() COMMAND_ID_REQUEST_ELECTRONIC_CARD :", Boolean.valueOf(z));
                deviceCapability.setSupportElectronicCard(z);
                return;
            }
            if (i == 5) {
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2AccountCapability() COMMAND_ID_GET_ACCOUNT_JUDGMENT_RESULT :", Boolean.valueOf(z));
                deviceCapability.configureSupportChangePhonePair(z);
            } else if (i == 6) {
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2AccountCapability() COMMAND_ID_ACCOUNT_SWITCH :", Boolean.valueOf(z));
                deviceCapability.configureSupportAccountSwitch(z);
            } else if (i == 7) {
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2AccountCapability() COMMAND_ID_WEARABLE_SYNC_ACCOUNT :", Boolean.valueOf(z));
                deviceCapability.setSupportSyncAccount(z);
            } else {
                LogUtil.a("ParseDeviceCapability", "handleV2AccountCapability unknown commandId.");
            }
        }
    }

    private static void ao(int i, boolean z, DeviceCapability deviceCapability) {
        if (deviceCapability != null) {
            if (i == 1) {
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2AccountCapability() COMMAND_ID_MID_WARE :", Boolean.valueOf(z));
                deviceCapability.configureSupportOneLevelMenu(z);
            } else {
                LogUtil.a("ParseDeviceCapability", "handleV2OneLevelMenuCapability unknown commandId.");
            }
        }
    }

    private static void ap(int i, boolean z, DeviceCapability deviceCapability) {
        if (deviceCapability != null) {
            if (i == 2) {
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2AccountCapability() COMMAND_ID_MID_WARE :", Boolean.valueOf(z));
                deviceCapability.configureSupportAtrialOperator(z);
            } else if (i == 11) {
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "COMMAND_ECG_AUTH_SWITCH :", Boolean.valueOf(z));
                deviceCapability.configureSupportEcgAuth(z);
            } else {
                LogUtil.a("ParseDeviceCapability", "handleV2OneLevelRRICapability unknown commandId.");
            }
        }
    }

    private static void av(int i, boolean z, DeviceCapability deviceCapability) {
        if (deviceCapability != null) {
            if (i == 1) {
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2PostureCapability() COMMAND_ID_POSTURE_VERSION :", Boolean.valueOf(z));
                deviceCapability.configureSupportPosture(z);
            } else {
                LogUtil.a("ParseDeviceCapability", "handleV2PostureCapability unknown commandId.");
            }
        }
    }

    private static void aj(int i, boolean z, DeviceCapability deviceCapability) {
        if (deviceCapability != null) {
            if (i == 2) {
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2MusicControlCapability() COMMAND_ID_MUSIC_CONTROL :", Boolean.valueOf(z));
                deviceCapability.configureSupportMusicControl(z);
            } else if (i == 4) {
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2MusicControlCapability() COMMAND_ID_MUSIC_LIST_INFO :", Boolean.valueOf(z));
                deviceCapability.configureIsSupportMusicInfoList(z);
            } else if (i == 14) {
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleV2MusicControlCapability() COMMAND_ID_MUSIC_SWITCH_STATUS :", Boolean.valueOf(z));
                deviceCapability.setSupportSendSwitchStatus(z);
            } else {
                LogUtil.a("ParseDeviceCapability", "handV2WorkModeCapability unknown commandId.");
            }
        }
    }

    private static void j(int i, boolean z, DeviceCapability deviceCapability) {
        if (deviceCapability != null) {
            if (i == 2) {
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handV2WorkModeCapability COMMAND_ID_AUTO_DETECT_MODE :", Boolean.valueOf(z));
                deviceCapability.configureSupportAutoDetectMode(z);
            } else if (i == 3) {
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handV2WorkModeCapability COMMAND_ID_FOOT_WEAR :", Boolean.valueOf(z));
                deviceCapability.configureSupportFootWear(z);
            } else {
                LogUtil.a("ParseDeviceCapability", "handV2WorkModeCapability unknown commandId.");
            }
        }
    }

    private static void g(int i, boolean z, DeviceCapability deviceCapability) {
        if (deviceCapability != null) {
            if (i == 1) {
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handV2WatchFaceCapability COMMAND_ID_WATCHFACE_MANAGER :", Boolean.valueOf(z));
                deviceCapability.setSupportWatchFace(z);
            } else if (i == 14) {
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handV2WatchFaceCapability COMMAND_ID_THEME_SUPPORT_WATCH_FACE :", Boolean.valueOf(z));
                deviceCapability.configureSupportWatchFaceAppId(z);
            } else {
                LogUtil.a("ParseDeviceCapability", "handV2WatchFaceCapability unknown commandId.");
            }
        }
    }

    private static void h(int i, boolean z, DeviceCapability deviceCapability) {
        if (deviceCapability != null) {
            if (i == 1) {
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handV2WatchFaceCapability COMMAND_ID_SLEEP_BREATHE_MANAGER :", Boolean.valueOf(z));
                deviceCapability.configureSupportSleepBreathe(z);
            } else {
                LogUtil.a("ParseDeviceCapability", "handV2SleepBreatheCapability no support parse command id :", Integer.valueOf(i));
            }
        }
    }

    private static void a(int i, boolean z, DeviceCapability deviceCapability) {
        if (deviceCapability != null) {
            if (i == 1) {
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handMarketCapability COMMAND_ID_MARKET :", Boolean.valueOf(z));
                deviceCapability.setSupportMarketFace(z);
            } else if (i == 6) {
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handMarketCapability COMMAND_ID_MARKET_PARAMS :", Boolean.valueOf(z));
                deviceCapability.setSupportMarketParams(z);
            } else {
                LogUtil.a("ParseDeviceCapability", "handMarketCapability no market support parse command id :", Integer.valueOf(i));
            }
        }
    }

    private static void e(int i, boolean z, DeviceCapability deviceCapability) {
        if (deviceCapability != null) {
            if (i == 1) {
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handMenstrualCapability :", Boolean.valueOf(z));
                deviceCapability.configureSupportMenstrual(z);
            } else {
                LogUtil.a("ParseDeviceCapability", "handMenstrualCapability no market support parse command id:", Integer.valueOf(i));
            }
        }
    }

    private static void b(int i, boolean z, DeviceCapability deviceCapability) {
        if (deviceCapability != null) {
            if (i == 1) {
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handV2HiCarCapability COMMAND_ID_HICAR_DRIVER_REMIND :", Boolean.valueOf(z));
                deviceCapability.configureSupportHiCarDriverRemind(z);
            } else if (i == 2) {
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handV2HiCarCapability COMMAND_ID_MESSAGE_FEEDBACK :", Boolean.valueOf(z));
                deviceCapability.configureSupportMessageFeedback(z);
            } else if (i == 3) {
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handV2HiCarCapability COMMAND_ID_WEAR_STATUS :", Boolean.valueOf(z));
                deviceCapability.configureSupportWearStatus(z);
            } else {
                LogUtil.a("ParseDeviceCapability", "handV2HiCarCapability no support parse command id :", Integer.valueOf(i));
            }
        }
    }

    private static void c(int i, boolean z, DeviceCapability deviceCapability) {
        if (deviceCapability != null) {
            if (i == 1) {
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handV2PhdCapability COMMAND_ID_PHD_MANAGER :", Boolean.valueOf(z));
                deviceCapability.configureSupportPhd(z);
            } else {
                LogUtil.a("ParseDeviceCapability", "handV2PhdCapability no support parse command id :", Integer.valueOf(i));
            }
        }
    }

    private static void o(int i, boolean z, DeviceCapability deviceCapability) {
        if (deviceCapability != null) {
            if (i == 1) {
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleSosCapability COMMAND_ID_SOS_MANAGER :", Boolean.valueOf(z));
                deviceCapability.configureSupportSosTransmission(z);
            } else if (i == 2) {
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handleSosCapability COMMAND_ID_SOS_SMS_MANAGER :", Boolean.valueOf(z));
                deviceCapability.configureSupportSendSosSms(z);
            } else {
                LogUtil.a("ParseDeviceCapability", "handleSosCapability no support parse command id :", Integer.valueOf(i));
            }
        }
    }

    private static void d(int i, boolean z, DeviceCapability deviceCapability) {
        if (deviceCapability != null) {
            if (i == 1) {
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handV2HiWearCapability COMMAND_ID_HIWEAR_MANAGER :", Boolean.valueOf(z));
                deviceCapability.configureSupportHiWear(z);
            } else {
                LogUtil.a("ParseDeviceCapability", "handV2HiWearCapability no support parse command id :", Integer.valueOf(i));
            }
        }
    }

    private static void i(int i, boolean z, DeviceCapability deviceCapability) {
        if (deviceCapability != null) {
            if (i == 3) {
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handV2HiWearCapability COMMAND_ID_WEAR_ENGINE_EVENT_NOTIFY :", Boolean.valueOf(z));
                deviceCapability.configureSupportWearEngine(z);
            } else if (i == 4) {
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handV2HiWearCapability COMMAND_ID_WEAR_ENGINE_DEVICE_SPACE :", Boolean.valueOf(z));
                deviceCapability.configureSupportCheckDeviceSpace(z);
            } else {
                LogUtil.a("ParseDeviceCapability", "handV2WearEngineCapability no support parse command id :", Integer.valueOf(i));
            }
        }
    }

    private static void b(int i, int i2, boolean z, DeviceCapability deviceCapability) {
        switch (i) {
            case 1:
                q(i2, z, deviceCapability);
                break;
            case 2:
                aq(i2, z, deviceCapability);
                break;
            case 3:
                m(i2, z, deviceCapability);
                break;
            case 4:
                p(i2, z, deviceCapability);
                break;
            case 5:
                au(i2, z, deviceCapability);
                break;
            case 6:
                am(i2, z, deviceCapability);
                break;
            case 7:
                x(i2, z, deviceCapability);
                break;
        }
    }

    private static void i(int i, int i2, boolean z, DeviceCapability deviceCapability) {
        switch (i) {
            case 8:
                n(i2, z, deviceCapability);
                break;
            case 9:
                as(i2, z, deviceCapability);
                break;
            case 10:
                ae(i2, z, deviceCapability);
                break;
            case 11:
                af(i2, z, deviceCapability);
                break;
            case 12:
                ad(i2, z, deviceCapability);
                break;
            case 13:
                at(i2, z, deviceCapability);
                break;
            case 14:
                al(i2, z, deviceCapability);
                break;
        }
    }

    private static void a(int i, int i2, boolean z, DeviceCapability deviceCapability) {
        switch (i) {
            case 15:
                bc(i2, z, deviceCapability);
                break;
            case 16:
                r(i2, z, deviceCapability);
                break;
            case 17:
                ar(i2, z, deviceCapability);
                break;
            case 18:
                w(i2, z, deviceCapability);
                break;
            case 19:
                l(i2, z, deviceCapability);
                break;
            case 20:
                ah(i2, z, deviceCapability);
                break;
            case 21:
                ax(i2, z, deviceCapability);
                break;
        }
    }

    private static void e(int i, int i2, boolean z, DeviceCapability deviceCapability) {
        switch (i) {
            case 22:
                v(i2, z, deviceCapability);
                break;
            case 23:
                bb(i2, z, deviceCapability);
                break;
            case 24:
                ag(i2, z, deviceCapability);
                break;
            case 25:
                ai(i2, z, deviceCapability);
                break;
            case 26:
                k(i2, z, deviceCapability);
                break;
            case 27:
                ba(i2, z, deviceCapability);
                break;
            case 28:
            case 30:
            case 31:
            default:
                d(i, i2, z, deviceCapability);
                break;
            case 29:
                ak(i2, z, deviceCapability);
                break;
            case 32:
                aw(i2, z, deviceCapability);
                break;
            case 33:
                an(i2, z, deviceCapability);
                break;
            case 34:
                ao(i2, z, deviceCapability);
                break;
            case 35:
                ap(i2, z, deviceCapability);
                break;
        }
    }

    private static void d(int i, int i2, boolean z, DeviceCapability deviceCapability) {
        switch (i) {
            case 36:
                av(i2, z, deviceCapability);
                break;
            case 37:
                aj(i2, z, deviceCapability);
                break;
            case 38:
                j(i2, z, deviceCapability);
                break;
            case 39:
                g(i2, z, deviceCapability);
                break;
            case 42:
                a(i2, z, deviceCapability);
                break;
            case 43:
                f(i2, z, deviceCapability);
                break;
            case 45:
                h(i2, z, deviceCapability);
                break;
            case 46:
                b(i2, z, deviceCapability);
                break;
            case 48:
                c(i2, z, deviceCapability);
                break;
            case 50:
                e(i2, z, deviceCapability);
                break;
            case 51:
                o(i2, z, deviceCapability);
                break;
            case 52:
                d(i2, z, deviceCapability);
                break;
            case 53:
                i(i2, z, deviceCapability);
                break;
        }
    }

    public static void d(int i, DeviceCapability deviceCapability) {
        if (deviceCapability == null) {
            return;
        }
        if (i != 3) {
            if (i != 7) {
                if (i == 8) {
                    iyw.e(deviceCapability);
                    return;
                }
                switch (i) {
                    case 10:
                        break;
                    case 11:
                        iyw.c(deviceCapability);
                        break;
                    case 12:
                        iyw.b(deviceCapability);
                        break;
                    case 13:
                        iyw.i(deviceCapability);
                        break;
                    case 14:
                    case 16:
                        break;
                    case 15:
                        iyw.a(deviceCapability);
                        break;
                    default:
                        e(deviceCapability);
                        break;
                }
                return;
            }
            iyw.d(deviceCapability);
            return;
        }
        izd.c(deviceCapability);
    }

    public static void a(int i, DeviceCapability deviceCapability) {
        if (deviceCapability == null) {
        }
        if (i == 4) {
            deviceCapability.configureWalk(true);
            return;
        }
        switch (i) {
            case 7:
                deviceCapability.configureRun(true);
                break;
            case 8:
                deviceCapability.configureClimb(true);
                break;
            case 9:
                deviceCapability.configureRiding(true);
                break;
            case 10:
                deviceCapability.configureSleep(true);
                deviceCapability.configureSleepShallow(true);
                deviceCapability.configureSleepDeep(true);
                break;
            default:
                LogUtil.a("ParseDeviceCapability", "parseSupportedActivityType unknown dataInfo.");
                break;
        }
    }

    public static void e(int i, DeviceCapability deviceCapability) {
        if (deviceCapability == null) {
            return;
        }
        if (1 == ((i >> 5) & 1)) {
            deviceCapability.configureIsSupportHeartRate(true);
        } else {
            deviceCapability.configureIsSupportHeartRate(false);
        }
    }

    public static void c(int i, DeviceCapability deviceCapability) {
        if (deviceCapability == null) {
            return;
        }
        deviceCapability.configurePromptPush(i);
    }

    private static void e(DeviceCapability deviceCapability) {
        deviceCapability.configureIsSupportHelp(true);
        deviceCapability.configurePromptPush(-1);
    }

    private static void f(int i, boolean z, DeviceCapability deviceCapability) {
        if (deviceCapability != null) {
            if (i == 18) {
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "ParseDeviceCapability", "handV2TwsCapability COMMAND_ID_TWS_MANAGER :", Boolean.valueOf(z));
                deviceCapability.setSupportTws(z);
            } else {
                LogUtil.a("ParseDeviceCapability", "handV2TwsCapability unknown commandId.");
            }
        }
    }
}
