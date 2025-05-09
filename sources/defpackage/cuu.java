package defpackage;

import android.os.Parcel;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;

/* loaded from: classes3.dex */
public class cuu {
    private int c(boolean z) {
        return z ? 1 : 0;
    }

    private void Oa_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureSupportTimeSetting(parcel.readByte() == 1);
    }

    private void Ps_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureSupportGetFirmwareVersion(parcel.readByte() == 1);
    }

    private void Pr_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureSupportGetBattery(parcel.readByte() == 1);
    }

    private void Od_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureAutoLightScreen(parcel.readByte() == 1);
    }

    private void Oe_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureAvoidDisturb(parcel.readByte() == 1);
    }

    private void Op_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureFactoryReset(parcel.readByte() == 1);
    }

    private void PQ_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureSupportPairDevice(parcel.readByte() == 1);
    }

    private void Pt_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureSupportGetHandsetInfo(parcel.readByte() == 1);
    }

    private void PO_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureSupportNotificationIntervalInfo(parcel.readByte() == 1);
    }

    private void Pd_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureSupportActivityType(parcel.readByte() == 1);
    }

    private void Ph_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureSupportAuthenticDevice(parcel.readByte() == 1);
    }

    private void Ot_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureGoldCard(parcel.readByte() == 1);
    }

    private void PV_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureSupportRemoteCamera(parcel.readByte() == 1);
    }

    private void OM_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureMessageAlert(parcel.readByte() == 1);
    }

    private void PH_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureSupportMessageAlertInfo(parcel.readByte() == 1);
    }

    private void PK_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureSupportMessageSupportInfo(parcel.readByte() == 1);
    }

    private void PI_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureupportMessageCenterPushDevice(parcel.readByte() == 1);
    }

    private void Qf_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureSupportWearMessagePush(parcel.readByte() == 1);
    }

    private void Ok_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureContacts(parcel.readByte() == 1);
    }

    private void Pj_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureSupportCallingOperationType(parcel.readByte() == 1);
    }

    private void ON_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureMotionGoalCap(parcel.readInt());
    }

    private void Oq_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureFitnessFrameType(parcel.readInt());
    }

    private void Ob_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureActivityReminder(parcel.readByte() == 1);
    }

    private void PY_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureSupportSetUserInfoEncrypt(parcel.readByte() == 1);
    }

    private void PX_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureSupportSampleFrame(parcel.readByte() == 1);
    }

    private void Qd_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureSupportThreshold(parcel.readByte() == 1);
    }

    private void OR_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureReserveSync(parcel.readByte() == 1);
    }

    private void Oz_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureIsSupportHeartRateZone(parcel.readByte() == 1);
    }

    private void Ox_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureIsSupportCoreSleep(parcel.readByte() == 1);
    }

    private void Pu_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureSupportGetUserInfo(parcel.readByte() == 1);
    }

    private void OF_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureIsSupportSendCoreSleepOutState(parcel.readByte() == 1);
    }

    private void OE_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureIsSupportQueryDeviceCoreSleepSwitch(parcel.readByte() == 1);
    }

    private void On_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureEventAlarm(parcel.readByte() == 1);
    }

    private void Oo_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureEventAlarmNum(parcel.readInt());
    }

    private void OZ_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureSmartAlarm(parcel.readByte() == 1);
    }

    private void OO_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureOtaUpdate(parcel.readByte() == 1);
    }

    private void OJ_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureMaintenance(parcel.readByte() == 1);
    }

    private void OL_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureMaintenanceInTime(parcel.readByte() == 1);
    }

    private void OK_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureMaintenanceGetData(parcel.readByte() == 1);
    }

    private void Pe_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureSupportAntiLost(parcel.readByte() == 1);
    }

    private void Of_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureBluetoothOffAlert(parcel.readByte() == 1);
    }

    private void OI_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureLanguage(parcel.readByte() == 1);
    }

    private void Qp_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureWeatherPush(parcel.readByte() == 1);
    }

    private void Qe_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureSupportUnitWeather(parcel.readByte() == 1);
    }

    private void Oc_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureAtmosphereSupportExpand(parcel.readByte() == 1);
    }

    private void Qq_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureWeatherSupportErrorCode(parcel.readByte() == 1);
    }

    private void Pn_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureSupportExerciseAdvice(parcel.readByte() == 1);
    }

    private void Pp_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureSupportExerciseAdviceTime(parcel.readByte() == 1);
    }

    private void Po_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureSupportExerciseAdviceMonitor(parcel.readByte() == 1);
    }

    private void Qh_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureSupportWorkout(parcel.readByte() == 1);
    }

    private void Qk_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureSupportWorkoutInfo(parcel.readByte() == 1);
    }

    private void Qs_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureSupportWorkoutReminder(parcel.readByte() == 1);
    }

    private void Ql_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureSupportWorkoutRecord(parcel.readByte() == 1);
    }

    private void Qj_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureSupportWorkoutExerciseDisplayLink(parcel.readByte() == 1);
    }

    private void Qm_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureSupportWorkoutRecordPaceMap(parcel.readByte() == 1);
    }

    private void Qi_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureSupportWorkoutCapabilicy(parcel.readByte() == 1);
    }

    private void Pw_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureSupportGpsLocation(parcel.readByte() == 1);
    }

    private void Pv_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureSupportGpsData(parcel.readByte() == 1);
    }

    private void Pz_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureSupportGpsSetParameter(parcel.readByte() == 1);
    }

    private void Px_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureSupportGpsPostProcessing(parcel.readByte() == 1);
    }

    private void PB_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureSupportHeartRateInfo(parcel.readByte() == 1);
    }

    private void OW_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureSleep(parcel.readByte() == 1);
    }

    private void Oj_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureClimb(parcel.readByte() == 1);
    }

    private void OS_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureRiding(parcel.readByte() == 1);
    }

    private void Pa_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureStand(parcel.readByte() == 1);
    }

    private void OY_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureSleepShallow(parcel.readByte() == 1);
    }

    private void OX_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureSleepDeep(parcel.readByte() == 1);
    }

    private void Qo_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureWalk(parcel.readByte() == 1);
    }

    private void OU_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureRun(parcel.readByte() == 1);
    }

    private void Pb_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureStep(parcel.readByte() == 1);
    }

    private void Oh_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureCalorie(parcel.readByte() == 1);
    }

    private void Ol_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureDistance(parcel.readByte() == 1);
    }

    private void Oy_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureIsSupportHeartRate(parcel.readByte() == 1);
    }

    private void OQ_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configurePromptPush(parcel.readInt());
    }

    private void Og_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureCallMute(parcel.readByte() == 1);
    }

    private void OA_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureIsSupportHelp(parcel.readByte() == 1);
    }

    private void Om_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureDistanceDetail(parcel.readByte() == 1);
    }

    private void Pm_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureSupportEsim(parcel.readByte() == 1);
    }

    private void PM_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureSupportMultiSim(parcel.readByte() == 1);
    }

    private void PR_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureSupportPay(parcel.readByte() == 1);
    }

    private void PA_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureisSupportHeartRateEnable(parcel.readByte() == 1);
    }

    private void OT_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureRotateSwitchScreen(parcel.readByte() == 1);
    }

    private void PU_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureSupportQueryAllowDisturbContent(parcel.readByte() == 1);
    }

    private void PF_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureSupportLeftRightHandWearMode(parcel.readByte() == 1);
    }

    private void Qc_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureSupportStress(parcel.readByte() == 1);
    }

    private void PL_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureSupportMidware(parcel.readByte() == 1);
    }

    private void Qb_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureSupportSportTotal(parcel.readByte() == 1);
    }

    private void Pc_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureSupportAccount(parcel.readByte() == 1);
    }

    private void PP_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureSupportOneLevelMenu(parcel.readByte() == 1);
    }

    private void Pl_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureSupportDeleteMsg(parcel.readByte() == 1);
    }

    private void PT_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureSupportPosture(parcel.readByte() == 1);
    }

    private void PN_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureSupportMusicControl(parcel.readByte() == 1);
    }

    private void OP_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configruePressAutoMonitor(parcel.readByte() == 1);
    }

    private void Pk_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureSupportContinueHeartRate(parcel.readByte() == 1);
    }

    private void Pf_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureSupportAtmosphere(parcel.readByte() == 1);
    }

    private void Pi_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureSupportAutoDetectMode(parcel.readByte() == 1);
    }

    private void Pq_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureSupportFootWear(parcel.readByte() == 1);
    }

    private void PW_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureSupportRunPosture(parcel.readByte() == 1);
    }

    private void Ov_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureHeartRateRaiseAlarm(parcel.readByte() == 1);
    }

    private void Or_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureGetHeartRateRaiseAlarmNumber(parcel.readByte() == 1);
    }

    private void Os_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureGetHighAndMiddleSport(parcel.readByte() == 1);
    }

    private void Qt_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.setSupportDefaultSwitch(parcel.readByte() == 1);
    }

    private void Ou_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureHeartRateDownAlarm(parcel.readByte() == 1);
    }

    private void Oi_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureChangeAlarm(parcel.readByte() == 1);
    }

    private void PE_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureSupportIntelligentHomeLinkage(parcel.readByte() == 1);
    }

    private void Qn_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureSyncBeforeOta(parcel.readByte() == 1);
    }

    private void OD_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureIsSupportPhonesInfo(parcel.readByte() == 1);
    }

    private void OC_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureIsSupportNotifyDeviceBroadCast(parcel.readByte() == 1);
    }

    private void Pg_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureSupportAtrialOperator(parcel.readByte() == 1);
    }

    private void OV_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureSetHrrHeartRateCapability(parcel.readByte() == 1);
    }

    private void Qy_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.setSupportWatchFace(parcel.readByte() == 1);
    }

    private void Qv_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.setSupportMarketFace(parcel.readByte() == 1);
    }

    private void PD_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureSupportInformCloseOrOpen(parcel.readByte() == 1);
    }

    private void OG_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureIsSupportStressAppToDevice(parcel.readByte() == 1);
    }

    private void OB_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureIsSupportMusicInfoList(parcel.readByte() == 1);
    }

    private void Qw_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.setSupportOneTouch(parcel.readByte() == 1);
    }

    private void Qx_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.setSupportTws(parcel.readByte() == 1);
    }

    private void PZ_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureSupportSleepBreathe(parcel.readByte() == 1);
    }

    private void PC_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureSupportHiCarDriverRemind(parcel.readByte() == 1);
    }

    private void PJ_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureSupportMessageFeedback(parcel.readByte() == 1);
    }

    private void Qg_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureSupportWearStatus(parcel.readByte() == 1);
    }

    private void PS_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureSupportPhd(parcel.readByte() == 1);
    }

    private void Ow_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureIsSupportAutoUpdate(parcel.readByte() == 1);
    }

    private void Py_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureSupportGpsSearchStarOptimization(parcel.readByte() == 1);
    }

    private void OH_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureIsSupportUpdateChange(parcel.readByte() == 1);
    }

    private void Qu_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.setSupportElectronicCard(parcel.readByte() == 1);
    }

    private void Qr_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureWeatherSupportFutureInfo(parcel.readByte() == 1);
    }

    private void PG_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureSupportMenstrual(parcel.readByte() == 1);
    }

    private void Qa_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureSupportSosTransmission(parcel.readByte() == 1);
        deviceCapability.configureSupportSendSosSms(parcel.readByte() == 1);
    }

    public void Qz_(Parcel parcel, DeviceCapability deviceCapability) {
        if (parcel == null || deviceCapability == null) {
            return;
        }
        Oa_(parcel, deviceCapability);
        Ps_(parcel, deviceCapability);
        Pr_(parcel, deviceCapability);
        Od_(parcel, deviceCapability);
        Oe_(parcel, deviceCapability);
        Op_(parcel, deviceCapability);
        PQ_(parcel, deviceCapability);
        Pt_(parcel, deviceCapability);
        PO_(parcel, deviceCapability);
        Pd_(parcel, deviceCapability);
        Ph_(parcel, deviceCapability);
        Ot_(parcel, deviceCapability);
        PV_(parcel, deviceCapability);
        OM_(parcel, deviceCapability);
        PH_(parcel, deviceCapability);
        PK_(parcel, deviceCapability);
        PI_(parcel, deviceCapability);
        Qf_(parcel, deviceCapability);
        Ok_(parcel, deviceCapability);
        Pj_(parcel, deviceCapability);
        ON_(parcel, deviceCapability);
        Oq_(parcel, deviceCapability);
        Ob_(parcel, deviceCapability);
        PY_(parcel, deviceCapability);
        PX_(parcel, deviceCapability);
        Qd_(parcel, deviceCapability);
        OR_(parcel, deviceCapability);
        Oz_(parcel, deviceCapability);
        Ox_(parcel, deviceCapability);
        Pu_(parcel, deviceCapability);
        OF_(parcel, deviceCapability);
        OE_(parcel, deviceCapability);
        On_(parcel, deviceCapability);
        Oo_(parcel, deviceCapability);
        OZ_(parcel, deviceCapability);
        OO_(parcel, deviceCapability);
        OJ_(parcel, deviceCapability);
        OL_(parcel, deviceCapability);
        OK_(parcel, deviceCapability);
    }

    public void QA_(Parcel parcel, DeviceCapability deviceCapability) {
        if (parcel == null || deviceCapability == null) {
            return;
        }
        Pe_(parcel, deviceCapability);
        Of_(parcel, deviceCapability);
        OI_(parcel, deviceCapability);
        Qp_(parcel, deviceCapability);
        Qe_(parcel, deviceCapability);
        Oc_(parcel, deviceCapability);
        Qq_(parcel, deviceCapability);
        Pn_(parcel, deviceCapability);
        Pp_(parcel, deviceCapability);
        Po_(parcel, deviceCapability);
        Qh_(parcel, deviceCapability);
        Qk_(parcel, deviceCapability);
        Qs_(parcel, deviceCapability);
        Ql_(parcel, deviceCapability);
        Qj_(parcel, deviceCapability);
        Qm_(parcel, deviceCapability);
        Qi_(parcel, deviceCapability);
        Pw_(parcel, deviceCapability);
        Pv_(parcel, deviceCapability);
        Pz_(parcel, deviceCapability);
        Px_(parcel, deviceCapability);
        PB_(parcel, deviceCapability);
        OW_(parcel, deviceCapability);
        Oj_(parcel, deviceCapability);
        OS_(parcel, deviceCapability);
        Pa_(parcel, deviceCapability);
        OY_(parcel, deviceCapability);
        OX_(parcel, deviceCapability);
        Qo_(parcel, deviceCapability);
        OU_(parcel, deviceCapability);
        Pb_(parcel, deviceCapability);
        Oh_(parcel, deviceCapability);
        Ol_(parcel, deviceCapability);
        Oy_(parcel, deviceCapability);
        OQ_(parcel, deviceCapability);
        Og_(parcel, deviceCapability);
        OA_(parcel, deviceCapability);
        Om_(parcel, deviceCapability);
        Pm_(parcel, deviceCapability);
        PM_(parcel, deviceCapability);
        PR_(parcel, deviceCapability);
        PA_(parcel, deviceCapability);
        OT_(parcel, deviceCapability);
        PU_(parcel, deviceCapability);
        PF_(parcel, deviceCapability);
    }

    public void QB_(Parcel parcel, DeviceCapability deviceCapability) {
        if (parcel == null || deviceCapability == null) {
            return;
        }
        Qc_(parcel, deviceCapability);
        PL_(parcel, deviceCapability);
        Qb_(parcel, deviceCapability);
        Pc_(parcel, deviceCapability);
        PP_(parcel, deviceCapability);
        Pl_(parcel, deviceCapability);
        PT_(parcel, deviceCapability);
        PN_(parcel, deviceCapability);
        OP_(parcel, deviceCapability);
        Pk_(parcel, deviceCapability);
        Pf_(parcel, deviceCapability);
        Pi_(parcel, deviceCapability);
        Pq_(parcel, deviceCapability);
        PW_(parcel, deviceCapability);
        Ov_(parcel, deviceCapability);
        Or_(parcel, deviceCapability);
        Os_(parcel, deviceCapability);
        Qt_(parcel, deviceCapability);
        Ou_(parcel, deviceCapability);
        Oi_(parcel, deviceCapability);
        PE_(parcel, deviceCapability);
        Qn_(parcel, deviceCapability);
        OD_(parcel, deviceCapability);
        OC_(parcel, deviceCapability);
        Pg_(parcel, deviceCapability);
        OV_(parcel, deviceCapability);
        Qy_(parcel, deviceCapability);
        Qv_(parcel, deviceCapability);
        PD_(parcel, deviceCapability);
        OG_(parcel, deviceCapability);
        OB_(parcel, deviceCapability);
        Qw_(parcel, deviceCapability);
        Qx_(parcel, deviceCapability);
        PZ_(parcel, deviceCapability);
        PC_(parcel, deviceCapability);
        PJ_(parcel, deviceCapability);
        Qg_(parcel, deviceCapability);
        PS_(parcel, deviceCapability);
        Ow_(parcel, deviceCapability);
        Py_(parcel, deviceCapability);
        OH_(parcel, deviceCapability);
        Qu_(parcel, deviceCapability);
        Qr_(parcel, deviceCapability);
        PG_(parcel, deviceCapability);
        Qa_(parcel, deviceCapability);
    }

    public void QC_(Parcel parcel, DeviceCapability deviceCapability) {
        if (parcel == null || deviceCapability == null) {
            return;
        }
        parcel.writeByte((byte) c(deviceCapability.isSupportTimeSetting()));
        parcel.writeByte((byte) c(deviceCapability.isSupportGetFirmwareVersion()));
        parcel.writeByte((byte) c(deviceCapability.isSupportGetBattery()));
        parcel.writeByte((byte) c(deviceCapability.isSupportAutoLightScreen()));
        parcel.writeByte((byte) c(deviceCapability.isAvoidDisturb()));
        parcel.writeByte((byte) c(deviceCapability.isFactoryReset()));
        parcel.writeByte((byte) c(deviceCapability.isSupportPairDevice()));
        parcel.writeByte((byte) c(deviceCapability.isSupportGetHandsetInfo()));
        parcel.writeByte((byte) c(deviceCapability.isSupportNotificationIntervalInfo()));
        parcel.writeByte((byte) c(deviceCapability.isSupportActivityType()));
        parcel.writeByte((byte) c(deviceCapability.isSupportAuthenticDevice()));
        parcel.writeByte((byte) c(deviceCapability.isGoldCard()));
        parcel.writeByte((byte) c(deviceCapability.isSupportRemoteCamera()));
        parcel.writeByte((byte) c(deviceCapability.isMessageAlert()));
        parcel.writeByte((byte) c(deviceCapability.isSupportMessageAlertInfo()));
        parcel.writeByte((byte) c(deviceCapability.isSupportMessageSupportInfo()));
        parcel.writeByte((byte) c(deviceCapability.isSupportMessageCenterPushDevice()));
        parcel.writeByte((byte) c(deviceCapability.isSupportWearMessagePush()));
        parcel.writeByte((byte) c(deviceCapability.isContacts()));
        parcel.writeByte((byte) c(deviceCapability.isSupportCallingOperationType()));
        parcel.writeInt(deviceCapability.getMotionGoalCap());
        parcel.writeInt(deviceCapability.getFitnessFrameType());
        parcel.writeByte((byte) c(deviceCapability.isActivityReminder()));
        parcel.writeByte((byte) c(deviceCapability.isSupportSetUserInfoEncrypt()));
        parcel.writeByte((byte) c(deviceCapability.isSupportSampleFrame()));
        parcel.writeByte((byte) c(deviceCapability.isSupportThreshold()));
        parcel.writeByte((byte) c(deviceCapability.isReserveSync()));
        parcel.writeByte((byte) c(deviceCapability.isSupportHeartRateZone()));
        parcel.writeByte((byte) c(deviceCapability.isSupportCoreSleep()));
        parcel.writeByte((byte) c(deviceCapability.isSupportGetUserInfo()));
        parcel.writeByte((byte) c(deviceCapability.isSupportSendCoreSleepOutState()));
        parcel.writeByte((byte) c(deviceCapability.isSupportQueryDeviceCoreSleepSwitch()));
        parcel.writeByte((byte) c(deviceCapability.isEventAlarm()));
        parcel.writeInt(deviceCapability.getEventAlarmNum());
        parcel.writeByte((byte) c(deviceCapability.isSmartAlarm()));
        parcel.writeByte((byte) c(deviceCapability.isOtaUpdate()));
        parcel.writeByte((byte) c(deviceCapability.isMaintenance()));
        parcel.writeByte((byte) c(deviceCapability.isMaintenanceInTime()));
        parcel.writeByte((byte) c(deviceCapability.isMaintenanceGetData()));
    }

    public void QD_(Parcel parcel, DeviceCapability deviceCapability) {
        if (parcel == null || deviceCapability == null) {
            return;
        }
        parcel.writeByte((byte) c(deviceCapability.isSupportAntiLost()));
        parcel.writeByte((byte) c(deviceCapability.isBluetoothOffAlert()));
        parcel.writeByte((byte) c(deviceCapability.isLanguage()));
        parcel.writeByte((byte) c(deviceCapability.isWeatherPush()));
        parcel.writeByte((byte) c(deviceCapability.isSupportUnitWeather()));
        parcel.writeByte((byte) c(deviceCapability.isAtmosphereSupportExpand()));
        parcel.writeByte((byte) c(deviceCapability.isWeatherSupportErrorCode()));
        parcel.writeByte((byte) c(deviceCapability.isSupportExerciseAdvice()));
        parcel.writeByte((byte) c(deviceCapability.isSupportExerciseAdviceTime()));
        parcel.writeByte((byte) c(deviceCapability.isSupportExerciseAdviceMonitor()));
        parcel.writeByte((byte) c(deviceCapability.isSupportWorkout()));
        parcel.writeByte((byte) c(deviceCapability.isSupportWorkoutInfo()));
        parcel.writeByte((byte) c(deviceCapability.isSupportWorkoutReminder()));
        parcel.writeByte((byte) c(deviceCapability.isSupportWorkoutRecord()));
        parcel.writeByte((byte) c(deviceCapability.isSupportWorkoutExerciseDisplayLink()));
        parcel.writeByte((byte) c(deviceCapability.isSupportWorkoutRecordPaceMap()));
        parcel.writeByte((byte) c(deviceCapability.isSupportWorkoutCapabilicy()));
        parcel.writeByte((byte) c(deviceCapability.isSupportGpsLocation()));
        parcel.writeByte((byte) c(deviceCapability.isSupportGpsData()));
        parcel.writeByte((byte) c(deviceCapability.isSupportGpsSetParameter()));
        parcel.writeByte((byte) c(deviceCapability.isSupportGpsPostProcessing()));
        parcel.writeByte((byte) c(deviceCapability.isSupportHeartRateInfo()));
        parcel.writeByte((byte) c(deviceCapability.isSleep()));
        parcel.writeByte((byte) c(deviceCapability.isClimb()));
        parcel.writeByte((byte) c(deviceCapability.isRiding()));
        parcel.writeByte((byte) c(deviceCapability.isStand()));
        parcel.writeByte((byte) c(deviceCapability.isSleepShallow()));
        parcel.writeByte((byte) c(deviceCapability.isSleepDeep()));
        parcel.writeByte((byte) c(deviceCapability.isWalk()));
        parcel.writeByte((byte) c(deviceCapability.isRun()));
        parcel.writeByte((byte) c(deviceCapability.isStep()));
        parcel.writeByte((byte) c(deviceCapability.isCalorie()));
        parcel.writeByte((byte) c(deviceCapability.isDistance()));
        parcel.writeByte((byte) c(deviceCapability.isSupportHeartRate()));
        parcel.writeInt(deviceCapability.isPromptPush());
        parcel.writeByte((byte) c(deviceCapability.isSupportCallMute()));
        parcel.writeByte((byte) c(deviceCapability.isSupportHelp()));
        parcel.writeByte((byte) c(deviceCapability.getDistanceDetail()));
        parcel.writeByte((byte) c(deviceCapability.isSupportEsim()));
        parcel.writeByte((byte) c(deviceCapability.isSupportMultiSim()));
        parcel.writeByte((byte) c(deviceCapability.isSupportPay()));
        parcel.writeByte((byte) c(deviceCapability.isSupportHeartRateEnable()));
        parcel.writeByte((byte) c(deviceCapability.isSupportRotateSwitchScreen()));
        parcel.writeByte((byte) c(deviceCapability.isSupportQueryAllowDisturbContent()));
        parcel.writeByte((byte) c(deviceCapability.isSupportLeftRightHandWearMode()));
    }

    public void QE_(Parcel parcel, DeviceCapability deviceCapability) {
        if (parcel == null || deviceCapability == null) {
            return;
        }
        parcel.writeByte((byte) c(deviceCapability.isSupportStress()));
        parcel.writeByte((byte) c(deviceCapability.isSupportMidware()));
        parcel.writeByte((byte) c(deviceCapability.isSupportSportTotal()));
        parcel.writeByte((byte) c(deviceCapability.isSupportAccount()));
        parcel.writeByte((byte) c(deviceCapability.isSupportOneLevelMenu()));
        parcel.writeByte((byte) c(deviceCapability.isSupportDeleteMsg()));
        parcel.writeByte((byte) c(deviceCapability.isSupportPosture()));
        parcel.writeByte((byte) c(deviceCapability.isSupportMusicControl()));
        parcel.writeByte((byte) c(deviceCapability.isSupportPressAutoMonitor()));
        parcel.writeByte((byte) c(deviceCapability.isSupportContinueHeartRate()));
        parcel.writeByte((byte) c(deviceCapability.isSupportAtmosphere()));
        parcel.writeByte((byte) c(deviceCapability.isSupportAutoDetectMode()));
        parcel.writeByte((byte) c(deviceCapability.isSupportFootWear()));
        parcel.writeByte((byte) c(deviceCapability.isSupportRunPosture()));
        parcel.writeByte((byte) c(deviceCapability.isSupportHeartRateRaiseAlarm()));
        parcel.writeByte((byte) c(deviceCapability.isSupportGetHeartRateRaiseAlarmNumber()));
        parcel.writeByte((byte) c(deviceCapability.isSupportGetHighAndMiddleSport()));
        parcel.writeByte((byte) c(deviceCapability.isSupportDefaultSwitch()));
        parcel.writeByte((byte) c(deviceCapability.isSupportHeartRateDownAlarm()));
        parcel.writeByte((byte) c(deviceCapability.isSupportChangeAlarm()));
        parcel.writeByte((byte) c(deviceCapability.isSupportIntelligentHomeLinkage()));
        parcel.writeByte((byte) c(deviceCapability.isNeedSyncBeforeOta()));
        parcel.writeByte((byte) c(deviceCapability.isSupportPhonesInfo()));
        parcel.writeByte((byte) c(deviceCapability.isSupportNotifyDeviceBroadCast()));
        parcel.writeByte((byte) c(deviceCapability.isSupportAtrialOperator()));
        parcel.writeByte((byte) c(deviceCapability.getIsSupportHrrHeartRateCapability()));
        parcel.writeByte((byte) c(deviceCapability.isSupportWatchFace()));
        parcel.writeByte((byte) c(deviceCapability.isSupportMarketFace()));
        parcel.writeByte((byte) c(deviceCapability.isSupportInformCloseOrOpen()));
        parcel.writeByte((byte) c(deviceCapability.isSupportStressAppToDevice()));
        parcel.writeByte((byte) c(deviceCapability.isSupportMusicInfoList()));
        parcel.writeByte((byte) c(deviceCapability.isSupportOneTouch()));
        parcel.writeByte((byte) c(deviceCapability.isSupportTws()));
        parcel.writeByte((byte) c(deviceCapability.isSupportSleepBreathe()));
        parcel.writeByte((byte) c(deviceCapability.isSupportHiCarDriverRemind()));
        parcel.writeByte((byte) c(deviceCapability.isSupportMessageFeedback()));
        parcel.writeByte((byte) c(deviceCapability.isSupportWearStatus()));
        parcel.writeByte((byte) c(deviceCapability.isSupportPhd()));
        parcel.writeByte((byte) c(deviceCapability.getIsSupportAutoUpdate()));
        parcel.writeByte((byte) c(deviceCapability.isSupportGpsSearchStarOptimization()));
        parcel.writeByte((byte) c(deviceCapability.getIsSupportUpdateChange()));
        parcel.writeByte((byte) c(deviceCapability.isSupportElectronicCard()));
        parcel.writeByte((byte) c(deviceCapability.isWeatherSupportFutureInfo()));
        parcel.writeByte((byte) c(deviceCapability.isSupportMenstrual()));
    }

    public void a(DeviceCapability deviceCapability) {
        if (deviceCapability == null) {
            return;
        }
        deviceCapability.configureSupportTimeSetting(false);
        deviceCapability.configureSupportGetFirmwareVersion(false);
        deviceCapability.configureSupportGetBattery(false);
        deviceCapability.configureAutoLightScreen(false);
        deviceCapability.configureAvoidDisturb(false);
        deviceCapability.configureFactoryReset(false);
        deviceCapability.configureSupportPairDevice(false);
        deviceCapability.configureSupportGetHandsetInfo(false);
        deviceCapability.configureSupportNotificationIntervalInfo(false);
        deviceCapability.configureSupportActivityType(false);
        deviceCapability.configureSupportAuthenticDevice(false);
        deviceCapability.configureGoldCard(false);
        deviceCapability.configureSupportRemoteCamera(false);
        deviceCapability.configureMessageAlert(false);
        deviceCapability.configureSupportMessageAlertInfo(false);
        deviceCapability.configureSupportMessageSupportInfo(false);
        deviceCapability.configureupportMessageCenterPushDevice(false);
        deviceCapability.configureSupportWearMessagePush(false);
        deviceCapability.configureContacts(false);
        deviceCapability.configureSupportCallingOperationType(false);
        deviceCapability.configureMotionGoalCap(1);
        deviceCapability.configureFitnessFrameType(0);
        deviceCapability.configureActivityReminder(false);
        deviceCapability.configureSupportSetUserInfoEncrypt(false);
        deviceCapability.configureSupportSampleFrame(false);
        deviceCapability.configureSupportThreshold(false);
        deviceCapability.configureReserveSync(false);
        deviceCapability.configureIsSupportHeartRateZone(false);
        deviceCapability.configureIsSupportCoreSleep(false);
        deviceCapability.configureSupportGetUserInfo(false);
        deviceCapability.configureIsSupportSendCoreSleepOutState(false);
        deviceCapability.configureIsSupportQueryDeviceCoreSleepSwitch(false);
        deviceCapability.configureEventAlarm(false);
        deviceCapability.configureEventAlarmNum(5);
        deviceCapability.configureSmartAlarm(false);
        deviceCapability.configureOtaUpdate(false);
        deviceCapability.configureMaintenance(false);
        deviceCapability.configureMaintenanceInTime(false);
        deviceCapability.configureMaintenanceGetData(false);
    }

    public void b(DeviceCapability deviceCapability) {
        if (deviceCapability == null) {
            return;
        }
        deviceCapability.configureSupportAntiLost(false);
        deviceCapability.configureBluetoothOffAlert(false);
        deviceCapability.configureLanguage(false);
        deviceCapability.configureWeatherPush(false);
        deviceCapability.configureSupportUnitWeather(false);
        deviceCapability.configureAtmosphereSupportExpand(false);
        deviceCapability.configureWeatherSupportErrorCode(false);
        deviceCapability.configureSupportExerciseAdvice(false);
        deviceCapability.configureSupportExerciseAdviceTime(false);
        deviceCapability.configureSupportExerciseAdviceMonitor(false);
        deviceCapability.configureSupportWorkout(false);
        deviceCapability.configureSupportWorkoutInfo(false);
        deviceCapability.configureSupportWorkoutReminder(false);
        deviceCapability.configureSupportWorkoutRecord(false);
        deviceCapability.configureSupportWorkoutExerciseDisplayLink(false);
        deviceCapability.configureSupportWorkoutRecordPaceMap(false);
        deviceCapability.configureSupportWorkoutCapabilicy(false);
        deviceCapability.configureSupportGpsLocation(false);
        deviceCapability.configureSupportGpsData(false);
        deviceCapability.configureSupportGpsSetParameter(false);
        deviceCapability.configureSupportGpsPostProcessing(false);
        deviceCapability.configureSupportHeartRateInfo(false);
        deviceCapability.configureSleep(false);
        deviceCapability.configureClimb(false);
        deviceCapability.configureRiding(false);
        deviceCapability.configureStand(false);
        deviceCapability.configureSleepShallow(false);
        deviceCapability.configureSleepDeep(false);
        deviceCapability.configureWalk(false);
        deviceCapability.configureRun(false);
        deviceCapability.configureStep(false);
        deviceCapability.configureCalorie(false);
        deviceCapability.configureDistance(false);
        deviceCapability.configureIsSupportHeartRate(false);
        deviceCapability.configurePromptPush(0);
        deviceCapability.configureCallMute(false);
        deviceCapability.configureIsSupportHelp(false);
        deviceCapability.configureDistanceDetail(false);
        deviceCapability.configureSupportEsim(false);
        deviceCapability.configureSupportMultiSim(false);
        deviceCapability.configureSupportPay(false);
        deviceCapability.configureisSupportHeartRateEnable(false);
        deviceCapability.configureRotateSwitchScreen(false);
        deviceCapability.configureSupportQueryAllowDisturbContent(false);
        deviceCapability.configureSupportLeftRightHandWearMode(false);
    }

    public void e(DeviceCapability deviceCapability) {
        if (deviceCapability == null) {
            return;
        }
        deviceCapability.configureSupportStress(false);
        deviceCapability.configureSupportMidware(false);
        deviceCapability.configureSupportSportTotal(false);
        deviceCapability.configureSupportAccount(false);
        deviceCapability.configureSupportOneLevelMenu(false);
        deviceCapability.configureSupportDeleteMsg(false);
        deviceCapability.configureSupportPosture(false);
        deviceCapability.configureSupportMusicControl(false);
        deviceCapability.configruePressAutoMonitor(false);
        deviceCapability.configureSupportContinueHeartRate(false);
        deviceCapability.configureSupportAtmosphere(false);
        deviceCapability.configureSupportAutoDetectMode(false);
        deviceCapability.configureSupportFootWear(false);
        deviceCapability.configureSupportRunPosture(false);
        deviceCapability.configureHeartRateRaiseAlarm(false);
        deviceCapability.configureGetHeartRateRaiseAlarmNumber(false);
        deviceCapability.configureGetHighAndMiddleSport(false);
        deviceCapability.setSupportDefaultSwitch(false);
        deviceCapability.configureHeartRateDownAlarm(false);
        deviceCapability.configureChangeAlarm(false);
        deviceCapability.configureSupportIntelligentHomeLinkage(false);
        deviceCapability.configureSyncBeforeOta(false);
        deviceCapability.configureIsSupportPhonesInfo(false);
        deviceCapability.configureIsSupportNotifyDeviceBroadCast(false);
        deviceCapability.configureSupportAtrialOperator(false);
        deviceCapability.configureSetHrrHeartRateCapability(false);
        deviceCapability.setSupportWatchFace(false);
        deviceCapability.setSupportMarketFace(false);
        deviceCapability.configureSupportInformCloseOrOpen(false);
        deviceCapability.configureIsSupportStressAppToDevice(false);
        deviceCapability.configureIsSupportMusicInfoList(false);
        deviceCapability.setSupportOneTouch(false);
        deviceCapability.setSupportTws(false);
        deviceCapability.configureSupportSleepBreathe(false);
        deviceCapability.configureSupportHiCarDriverRemind(false);
        deviceCapability.configureSupportMessageFeedback(false);
        deviceCapability.configureSupportWearStatus(false);
        deviceCapability.configureSupportPhd(false);
        deviceCapability.configureIsSupportAutoUpdate(false);
        deviceCapability.configureSupportGpsSearchStarOptimization(false);
        deviceCapability.configureIsSupportUpdateChange(false);
        deviceCapability.setSupportElectronicCard(false);
        deviceCapability.configureWeatherSupportFutureInfo(false);
        deviceCapability.configureSupportMenstrual(false);
        deviceCapability.configureSupportSosTransmission(false);
    }
}
