package com.huawei.health.devicemgr.business.entity;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import defpackage.bmb;
import defpackage.cut;

/* loaded from: classes.dex */
public class DeviceCapability implements Parcelable {
    public static final Parcelable.Creator<DeviceCapability> CREATOR = new Parcelable.Creator<DeviceCapability>() { // from class: com.huawei.health.devicemgr.business.entity.DeviceCapability.3
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public DeviceCapability[] newArray(int i) {
            return new DeviceCapability[i];
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: QF_, reason: merged with bridge method [inline-methods] */
        public DeviceCapability createFromParcel(Parcel parcel) {
            return new cut().NY_(parcel);
        }
    };
    public static final int DEFAULT_EVENT_ALARM_NUMBER = 5;
    public static final int DEFAULT_SMART_WATCH_VERSION_VALUE = 0;
    public static final int DEVICE_DATA_TYPE_AF = 4;
    public static final int DEVICE_DATA_TYPE_COMMON = 1;
    public static final int DEVICE_DATA_TYPE_COMPRESSED = 0;
    public static final int DEVICE_DATA_TYPE_SEPARATED = 3;
    public static final int MOTION_GOAL_CAP_CALORIE = 2;
    public static final int MOTION_GOAL_CAP_DISTANCE = 4;
    public static final int MOTION_GOAL_CAP_STEPS = 1;
    private boolean isSupportTimeSetting = false;
    private boolean isSupportGetFirmwareVersion = false;
    private boolean isSupportGetBattery = false;
    private boolean isSupportAutoLightScreen = false;
    private boolean isSupportAvoidDisturb = false;
    private boolean isSupportFactoryReset = false;
    private boolean isSupportPairDevice = false;
    private boolean isSupportGetHandsetInfo = false;
    private boolean isSupportNotificationIntervalInfo = false;
    private boolean isSupportActivityType = false;
    private boolean isSupportAuthenticDevice = false;
    private boolean isSupportGoldCard = false;
    private boolean isSupportRemoteCamera = false;
    private boolean isSupportMessageAlert = false;
    private boolean isSupportMessageAlertInfo = false;
    private boolean isSupportMessageSupportInfo = false;
    private boolean isSupportDeleteMsg = false;
    private boolean isSupportMessageCenterPushDevice = false;
    private boolean isSupportWearMessagePush = false;
    private boolean isSupportIntelligentHomeLinkage = false;
    private boolean isSupportContacts = false;
    private boolean isSupportCallingOperationType = false;
    private boolean isSupportSportTotal = false;
    private int motionGoalCap = 1;
    private int fitnessFrameType = 0;
    private boolean isSupportActivityReminder = false;
    private boolean isSupportSetUserInfoEncrypt = false;
    private boolean isSupportSampleFrame = false;
    private boolean isSupportThreshold = false;
    private boolean isSupportReserveSync = false;
    private boolean isSupportHeartRateZone = false;
    private boolean isSupportCoreSleep = false;
    private boolean isSupportGetUserInfo = false;
    private boolean isSupportHeartRateEnable = false;
    private boolean isSupportSendCoreSleepOutState = false;
    private boolean isSupportQueryCoreSleepSwitch = false;
    private boolean isSupportEventAlarm = false;
    private int eventAlarmNum = 5;
    private int smartWatchVersionTypeValue = 0;
    private boolean isSupportSmartAlarm = false;
    private boolean isSupportOtaUpdate = false;
    private boolean isSupportMaintenance = false;
    private boolean isSupportMaintenanceInTime = false;
    private boolean isSupportMaintenanceGetData = false;
    private boolean isSupportAntiLost = false;
    private boolean isSupportBluetoothOffAlert = false;
    private boolean isSupportLanguage = false;
    private boolean isSupportAtmosphereSupportExpand = false;
    private boolean isSupportWeatherSupportErrorCode = false;
    private boolean isSupportWeatherPush = false;
    private boolean isSupportExerciseAdvice = false;
    private boolean isSupportExerciseAdviceTime = false;
    private boolean isSupportExerciseAdviceMonitor = false;
    private boolean isSupportWorkout = false;
    private boolean isSupportWorkoutInfo = false;
    private boolean isSupportWorkoutReminder = false;
    private boolean isSupportWorkoutRecord = false;
    private boolean isSupportWorkoutExerciseDisplayLink = false;
    private boolean isSupportWorkoutRecordPaceMap = false;
    private boolean isSupportWorkoutCapabilicy = false;
    private boolean isSupportWorkoutTrustHeartRate = false;
    private boolean isSupportGpsLocation = false;
    private boolean isSupportGpsData = false;
    private boolean isSupportGpsSetParameter = false;
    private boolean isSupportGpsPostProcessing = false;
    private boolean isSupportGpsSearchStarOptimization = false;
    private boolean isSupportHeartRateInfo = false;
    private boolean isSupportStressInfo = false;
    private boolean isSupportSleep = false;
    private boolean isSupportClimb = false;
    private boolean isSupportRiding = false;
    private boolean isSupportStand = false;
    private boolean isSupportSleepShallow = false;
    private boolean isSupportSleepDeep = false;
    private boolean isSupportWalk = false;
    private boolean isSupportRun = false;
    private boolean isSupportStep = false;
    private boolean isSupportCalorie = false;
    private boolean isSupportDistance = false;
    private boolean isSupportHeartRate = false;
    private int promptPush = 0;
    private boolean isSupportCallMute = false;
    private boolean isSupportHelp = false;
    private boolean isSupportDistanceDetail = false;
    private boolean isSupportEsim = false;
    private boolean isSupportMultiSim = false;
    private boolean isSupportNewEsim = false;
    private boolean isSupportPay = false;
    private boolean isSupportRotateSwitchScreen = false;
    private boolean isSupportLeftRightHandWearMode = false;
    private boolean isSupportStress = false;
    private boolean isSupportMidware = false;
    private boolean isSupportAccount = false;
    private boolean isSupportOneLevelMenu = false;
    private boolean isSupportMusicControl = false;
    private boolean isSupportQueryAllowDisturbContent = false;
    private boolean isSupportDefaultSwitch = false;
    private boolean isSupportPosture = false;
    private boolean isSupportPressAutoMonitor = false;
    private boolean isSupportContinueHeartRate = false;
    private boolean isSupportAtmosphere = false;
    private boolean isSupportFootWear = false;
    private boolean isSupportAutoDetectMode = false;
    private boolean isSupportRunPosture = false;
    private boolean isSupportInformCloseOrOpen = false;
    private boolean isSupportHeartRateRaiseAlarm = false;
    private boolean isSupportGetHeartRateRaiseAlarmNumber = false;
    private boolean isSupportGetHighAndMiddleSport = false;
    private boolean isSupportHeartRateDownAlarm = false;
    private boolean isSupportRestHeartRateControls = false;
    private boolean isSupportCycleBloodOxygenSwitch = false;
    private boolean isSupportBloodOxygenDownRemind = false;
    private boolean isSupportRunPaceSet = false;
    private boolean isSupportMediumToHighStrengthPreValue = false;
    private boolean isSupportChangeAlarm = false;
    private boolean isSupportNeedSyncBeforeOta = false;
    private boolean isSupportAutoUpdate = false;
    private boolean isSupportUpdateChange = false;
    private boolean isSupportNotifyDeviceNewVersion = false;
    private boolean isSupportDeviceRequestCheck = false;
    private boolean isSupportPhonesInfo = false;
    private boolean isSupportNotifyDeviceBroadCast = false;
    private boolean isSupportUnitWeather = false;
    private boolean isSupportHrrHeartRateCapability = false;
    private boolean isSupportWatchFace = false;
    private boolean isSupportMarket = false;
    private boolean isSupportMarketParams = false;
    private boolean isSupportTws = false;
    private boolean isSupportStressAppToDevice = false;
    private boolean isSupportMusicInfoList = false;
    private boolean isSupportElectronicCard = false;
    private boolean isSupportSyncAccount = false;
    private boolean isSupportOneTouch = false;
    private boolean isSupportSleepBreathe = false;
    private boolean isSupportHiCarDriverRemind = false;
    private boolean isSupportMessageFeedback = false;
    private boolean isSupportWearStatus = false;
    private boolean isSupportPhd = false;
    private boolean isSupportHiWear = false;
    private boolean isSupportWearEngine = false;
    private boolean isSupportCheckDeviceSpace = false;
    private boolean isSupportWeatherFutureInfo = false;

    @SerializedName("isSupport_atrial_operator")
    private boolean isSupportAtrialOperator = false;
    private boolean isSupportMenstrual = false;
    private boolean isSupportSosTransmission = false;
    private boolean isSupportSendSosSms = false;
    private boolean isSupportDeviceFutureWeatherCapability = false;
    private boolean isSupportTide = false;
    private boolean isSupportAppId = false;
    private boolean isSupportWalletOpenCard = false;
    private boolean isSupportSendSwitchStatusInfo = false;
    private boolean isSupportWatchFaceAppId = false;
    private boolean isSupportZoneId = false;
    private boolean isSupportHttp = false;
    private boolean isSupportSyncHiCall = false;
    private boolean isSupportSyncContacts = false;
    private boolean isSupportChangePhonePair = false;
    private boolean isSupportAccountSwitch = false;
    private boolean isSupportSettingRelated = false;
    private boolean isSupportLegalPrivacy = false;
    private boolean isSupportLegalUserAgreement = false;
    private boolean isSupportLegalOpenSource = false;
    private boolean isSupportLegalServiceStatement = false;
    private boolean isSupportLegalSourceStatement = false;
    private boolean isSupportLegalSystemWebView = false;
    private boolean isSupportSelfUploadDeviceLog = false;
    private boolean isSupportSmartWatchVersionStatus = false;
    private boolean isHmsAutoUpdate = false;
    private boolean isHmsAutoUpdateWifi = false;
    private boolean isHmsNotifyUpdate = false;
    private boolean isSupportUserSetting = false;
    private boolean isSupportSyncWifi = false;
    private boolean isHideWalletEntrance = false;
    private boolean isSupportEcgAuth = false;
    private boolean isSupportActivityRecognitionStatus = false;
    private boolean isSupportWeatherErrorCode = false;
    private boolean isSupportCoreSleepNewFile = false;
    private boolean isSupportSyncTime = false;
    private boolean isSupportRriNewFile = false;
    private boolean isSupportUploadGpsAndPdrFile = false;
    private boolean isSupportConnectStatus = false;
    private boolean isSupportExpandCapability = false;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean isSupportWeatherErrorCode() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportWeatherErrorCode))).booleanValue();
    }

    public void setSupportWeatherErrorCode(boolean z) {
        this.isSupportWeatherErrorCode = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isSupportActivityRecognitionStatus() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportActivityRecognitionStatus))).booleanValue();
    }

    public void setSupportActivityRecognitionStatus(boolean z) {
        this.isSupportActivityRecognitionStatus = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isSupportSendSwitchStatus() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportSendSwitchStatusInfo))).booleanValue();
    }

    public void setSupportSendSwitchStatus(boolean z) {
        this.isSupportSendSwitchStatusInfo = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isSupportSmartWatchVersionStatus() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportSmartWatchVersionStatus))).booleanValue();
    }

    public void setSupportSmartWatchVersionStatus(boolean z) {
        this.isSupportSmartWatchVersionStatus = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isHmsNotifyUpdate() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isHmsNotifyUpdate))).booleanValue();
    }

    public void configureHmsNotifyUpdate(boolean z) {
        this.isHmsNotifyUpdate = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isHmsAutoUpdateWifi() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isHmsAutoUpdateWifi))).booleanValue();
    }

    public void configureHmsAutoUpdateWifi(boolean z) {
        this.isHmsAutoUpdateWifi = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isHmsAutoUpdate() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isHmsAutoUpdate))).booleanValue();
    }

    public void configureHmsAutoUpdate(boolean z) {
        this.isHmsAutoUpdate = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isSupportAppId() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportAppId))).booleanValue();
    }

    public void configureSupportAppId(boolean z) {
        this.isSupportAppId = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isSupportUserSetting() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportUserSetting))).booleanValue();
    }

    public void configureSupportUserSetting(boolean z) {
        this.isSupportUserSetting = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isSupportWalletOpenCard() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportWalletOpenCard))).booleanValue();
    }

    public void configureSupportWalletOpenCard(boolean z) {
        this.isSupportWalletOpenCard = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isSupportTide() {
        return this.isSupportTide;
    }

    public void setSupportTide(boolean z) {
        this.isSupportTide = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isSupportDeviceFutureWeatherCapability() {
        return this.isSupportDeviceFutureWeatherCapability;
    }

    public void setSupportDeviceFutureWeatherCapability(boolean z) {
        this.isSupportDeviceFutureWeatherCapability = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isSupportElectronicCard() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportElectronicCard))).booleanValue();
    }

    public void setSupportElectronicCard(boolean z) {
        this.isSupportElectronicCard = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isSupportSyncAccount() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportSyncAccount))).booleanValue();
    }

    public void setSupportSyncAccount(boolean z) {
        this.isSupportSyncAccount = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isSupportOneTouch() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportOneTouch))).booleanValue();
    }

    public void setSupportOneTouch(boolean z) {
        this.isSupportOneTouch = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isSupportWatchFace() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportWatchFace))).booleanValue();
    }

    public void setSupportWatchFace(boolean z) {
        this.isSupportWatchFace = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isSupportMarketFace() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportMarket))).booleanValue();
    }

    public void setSupportMarketFace(boolean z) {
        this.isSupportMarket = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isSupportMarketParams() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportMarketParams))).booleanValue();
    }

    public void setSupportMarketParams(boolean z) {
        this.isSupportMarketParams = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isSupportTws() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportTws))).booleanValue();
    }

    public void setSupportTws(boolean z) {
        this.isSupportTws = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isSupportUnitWeather() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportUnitWeather))).booleanValue();
    }

    public void configureSupportUnitWeather(boolean z) {
        this.isSupportUnitWeather = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isAtmosphereSupportExpand() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportAtmosphereSupportExpand))).booleanValue();
    }

    public void configureAtmosphereSupportExpand(boolean z) {
        this.isSupportAtmosphereSupportExpand = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isWeatherSupportErrorCode() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportWeatherSupportErrorCode))).booleanValue();
    }

    public void configureWeatherSupportErrorCode(boolean z) {
        this.isSupportWeatherSupportErrorCode = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isWeatherSupportFutureInfo() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportWeatherFutureInfo))).booleanValue();
    }

    public void configureWeatherSupportFutureInfo(boolean z) {
        this.isSupportWeatherFutureInfo = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isSupportAtrialOperator() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportAtrialOperator))).booleanValue();
    }

    public boolean isSupportMenstrual() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportMenstrual))).booleanValue();
    }

    public void configureSupportMenstrual(boolean z) {
        this.isSupportMenstrual = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isSupportSosTransmission() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportSosTransmission))).booleanValue();
    }

    public void configureSupportSosTransmission(boolean z) {
        this.isSupportSosTransmission = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isSupportSendSosSms() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportSendSosSms))).booleanValue();
    }

    public void configureSupportSendSosSms(boolean z) {
        this.isSupportSendSosSms = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public void configureSupportAtrialOperator(boolean z) {
        this.isSupportAtrialOperator = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isSupportIntelligentHomeLinkage() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportIntelligentHomeLinkage))).booleanValue();
    }

    public void configureSupportIntelligentHomeLinkage(boolean z) {
        this.isSupportIntelligentHomeLinkage = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isSupportDefaultSwitch() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportDefaultSwitch))).booleanValue();
    }

    public void setSupportDefaultSwitch(boolean z) {
        this.isSupportDefaultSwitch = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isSupportMidware() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportMidware))).booleanValue();
    }

    public void configureSupportMidware(boolean z) {
        this.isSupportMidware = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isSupportMusicControl() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportMusicControl))).booleanValue();
    }

    public void configureSupportMusicControl(boolean z) {
        this.isSupportMusicControl = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isSupportLeftRightHandWearMode() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportLeftRightHandWearMode))).booleanValue();
    }

    public void configureSupportLeftRightHandWearMode(boolean z) {
        this.isSupportLeftRightHandWearMode = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isSupportQueryAllowDisturbContent() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportQueryAllowDisturbContent))).booleanValue();
    }

    public void configureSupportQueryAllowDisturbContent(boolean z) {
        this.isSupportQueryAllowDisturbContent = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isSupportStress() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportStress))).booleanValue();
    }

    public void configureSupportStress(boolean z) {
        this.isSupportStress = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isSupportPay() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportPay))).booleanValue();
    }

    public void configureSupportPay(boolean z) {
        this.isSupportPay = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isSupportEsim() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportEsim))).booleanValue();
    }

    public boolean isSupportMultiSim() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportMultiSim))).booleanValue();
    }

    public void configureSupportEsim(boolean z) {
        this.isSupportEsim = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public void configureSupportMultiSim(boolean z) {
        this.isSupportMultiSim = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isSupportNewEsim() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportNewEsim))).booleanValue();
    }

    public void configureSupportNewEsim(boolean z) {
        this.isSupportNewEsim = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isLanguage() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportLanguage))).booleanValue();
    }

    public void configureLanguage(boolean z) {
        this.isSupportLanguage = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isGoldCard() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportGoldCard))).booleanValue();
    }

    public void configureGoldCard(boolean z) {
        this.isSupportGoldCard = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isSupportRemoteCamera() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportRemoteCamera))).booleanValue();
    }

    public void configureSupportRemoteCamera(boolean z) {
        this.isSupportRemoteCamera = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isClimb() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportClimb))).booleanValue();
    }

    public void configureClimb(boolean z) {
        this.isSupportClimb = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isRiding() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportRiding))).booleanValue();
    }

    public void configureRiding(boolean z) {
        this.isSupportRiding = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isStand() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportStand))).booleanValue();
    }

    public void configureStand(boolean z) {
        this.isSupportStand = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isSleepShallow() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportSleepShallow))).booleanValue();
    }

    public void configureSleepShallow(boolean z) {
        this.isSupportSleepShallow = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isSleepDeep() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportSleepDeep))).booleanValue();
    }

    public void configureSleepDeep(boolean z) {
        this.isSupportSleepDeep = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isWalk() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportWalk))).booleanValue();
    }

    public void configureWalk(boolean z) {
        this.isSupportWalk = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isRun() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportRun))).booleanValue();
    }

    public void configureRun(boolean z) {
        this.isSupportRun = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isStep() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportStep))).booleanValue();
    }

    public void configureStep(boolean z) {
        this.isSupportStep = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isCalorie() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportCalorie))).booleanValue();
    }

    public void configureCalorie(boolean z) {
        this.isSupportCalorie = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isDistance() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportDistance))).booleanValue();
    }

    public void configureDistance(boolean z) {
        this.isSupportDistance = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isSleep() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportSleep))).booleanValue();
    }

    public void configureSleep(boolean z) {
        this.isSupportSleep = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isActivityReminder() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportActivityReminder))).booleanValue();
    }

    public void configureActivityReminder(boolean z) {
        this.isSupportActivityReminder = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isBluetoothOffAlert() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportBluetoothOffAlert))).booleanValue();
    }

    public void configureBluetoothOffAlert(boolean z) {
        this.isSupportBluetoothOffAlert = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isEventAlarm() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportEventAlarm))).booleanValue();
    }

    public void configureEventAlarm(boolean z) {
        this.isSupportEventAlarm = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public int getEventAlarmNum() {
        return this.eventAlarmNum;
    }

    public void configureEventAlarmNum(int i) {
        this.eventAlarmNum = ((Integer) bmb.d(Integer.valueOf(i))).intValue();
    }

    public int getSmartWatchVersionTypeValue() {
        return ((Integer) bmb.d(Integer.valueOf(this.smartWatchVersionTypeValue))).intValue();
    }

    public void configureSmartWatchVersionTypeValue(int i) {
        this.smartWatchVersionTypeValue = ((Integer) bmb.d(Integer.valueOf(i))).intValue();
    }

    public boolean isSmartAlarm() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportSmartAlarm))).booleanValue();
    }

    public void configureSmartAlarm(boolean z) {
        this.isSupportSmartAlarm = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isAvoidDisturb() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportAvoidDisturb))).booleanValue();
    }

    public void configureAvoidDisturb(boolean z) {
        this.isSupportAvoidDisturb = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isMessageAlert() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportMessageAlert))).booleanValue();
    }

    public void configureMessageAlert(boolean z) {
        this.isSupportMessageAlert = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isSupportCallMute() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportCallMute))).booleanValue();
    }

    public void configureCallMute(boolean z) {
        this.isSupportCallMute = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isWeatherPush() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportWeatherPush))).booleanValue();
    }

    public void configureWeatherPush(boolean z) {
        this.isSupportWeatherPush = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isSupportAutoLightScreen() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportAutoLightScreen))).booleanValue();
    }

    public void configureAutoLightScreen(boolean z) {
        this.isSupportAutoLightScreen = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public void configruePressAutoMonitor(boolean z) {
        this.isSupportPressAutoMonitor = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isSupportPressAutoMonitor() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportPressAutoMonitor))).booleanValue();
    }

    public boolean isSupportRotateSwitchScreen() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportRotateSwitchScreen))).booleanValue();
    }

    public void configureRotateSwitchScreen(boolean z) {
        this.isSupportRotateSwitchScreen = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isContacts() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportContacts))).booleanValue();
    }

    public void configureContacts(boolean z) {
        this.isSupportContacts = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isOtaUpdate() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportOtaUpdate))).booleanValue();
    }

    public void configureOtaUpdate(boolean z) {
        this.isSupportOtaUpdate = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isFactoryReset() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportFactoryReset))).booleanValue();
    }

    public void configureFactoryReset(boolean z) {
        this.isSupportFactoryReset = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public void configureFitnessFrameType(int i) {
        this.fitnessFrameType = ((Integer) bmb.d(Integer.valueOf(i))).intValue();
    }

    public int getFitnessFrameType() {
        return ((Integer) bmb.d(Integer.valueOf(this.fitnessFrameType))).intValue();
    }

    public int isPromptPush() {
        return ((Integer) bmb.d(Integer.valueOf(this.promptPush))).intValue();
    }

    public void configurePromptPush(int i) {
        this.promptPush = ((Integer) bmb.d(Integer.valueOf(i))).intValue();
    }

    public void configureMotionGoalCap(int i) {
        this.motionGoalCap = ((Integer) bmb.d(Integer.valueOf(i))).intValue();
    }

    public int getMotionGoalCap() {
        return ((Integer) bmb.d(Integer.valueOf(this.motionGoalCap))).intValue();
    }

    public void configureIsSupportHeartRate(boolean z) {
        this.isSupportHeartRate = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isSupportHeartRate() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportHeartRate))).booleanValue();
    }

    public void configureIsSupportHeartRateZone(boolean z) {
        this.isSupportHeartRateZone = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isSupportHeartRateZone() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportHeartRateZone))).booleanValue();
    }

    public void configureIsSupportCoreSleep(boolean z) {
        this.isSupportCoreSleep = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isSupportCoreSleep() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportCoreSleep))).booleanValue();
    }

    public boolean isDeviceSupportCoreSleep() {
        return isSupportCoreSleep() || isSupportQueryDeviceCoreSleepSwitch();
    }

    public boolean isSupportSendCoreSleepOutState() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportSendCoreSleepOutState))).booleanValue();
    }

    public void configureIsSupportSendCoreSleepOutState(boolean z) {
        this.isSupportSendCoreSleepOutState = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isSupportQueryDeviceCoreSleepSwitch() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportQueryCoreSleepSwitch))).booleanValue();
    }

    public void configureIsSupportQueryDeviceCoreSleepSwitch(boolean z) {
        this.isSupportQueryCoreSleepSwitch = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public void configureisSupportHeartRateEnable(boolean z) {
        this.isSupportHeartRateEnable = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isSupportHeartRateEnable() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportHeartRateEnable))).booleanValue();
    }

    public boolean isSupportGetUserInfo() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportGetUserInfo))).booleanValue();
    }

    public void configureSupportGetUserInfo(boolean z) {
        this.isSupportGetUserInfo = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public void configureIsSupportHelp(boolean z) {
        this.isSupportHelp = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isSupportHelp() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportHelp))).booleanValue();
    }

    public void configureSupportExerciseAdvice(boolean z) {
        this.isSupportExerciseAdvice = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isSupportExerciseAdvice() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportExerciseAdvice))).booleanValue();
    }

    public void configureSupportWorkout(boolean z) {
        this.isSupportWorkout = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isSupportWorkout() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportWorkout))).booleanValue();
    }

    public void configureReserveSync(boolean z) {
        this.isSupportReserveSync = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isReserveSync() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportReserveSync))).booleanValue();
    }

    public void configureSupportGpsLocation(boolean z) {
        this.isSupportGpsLocation = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isSupportGpsLocation() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportGpsLocation))).booleanValue();
    }

    public boolean isMaintenance() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportMaintenance))).booleanValue();
    }

    public void configureMaintenance(boolean z) {
        this.isSupportMaintenance = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isMaintenanceInTime() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportMaintenanceInTime))).booleanValue();
    }

    public void configureMaintenanceInTime(boolean z) {
        this.isSupportMaintenanceInTime = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isMaintenanceGetData() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportMaintenanceGetData))).booleanValue();
    }

    public void configureMaintenanceGetData(boolean z) {
        this.isSupportMaintenanceGetData = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public void configureDistanceDetail(boolean z) {
        this.isSupportDistanceDetail = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean getDistanceDetail() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportDistanceDetail))).booleanValue();
    }

    public boolean isSupportTimeSetting() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportTimeSetting))).booleanValue();
    }

    public void configureSupportTimeSetting(boolean z) {
        this.isSupportTimeSetting = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isSupportGetFirmwareVersion() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportGetFirmwareVersion))).booleanValue();
    }

    public void configureSupportGetFirmwareVersion(boolean z) {
        this.isSupportGetFirmwareVersion = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isSupportGetBattery() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportGetBattery))).booleanValue();
    }

    public void configureSupportGetBattery(boolean z) {
        this.isSupportGetBattery = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isSupportPairDevice() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportPairDevice))).booleanValue();
    }

    public void configureSupportPairDevice(boolean z) {
        this.isSupportPairDevice = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isSupportGetHandsetInfo() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportGetHandsetInfo))).booleanValue();
    }

    public void configureSupportGetHandsetInfo(boolean z) {
        this.isSupportGetHandsetInfo = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isSupportNotificationIntervalInfo() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportNotificationIntervalInfo))).booleanValue();
    }

    public void configureSupportNotificationIntervalInfo(boolean z) {
        this.isSupportNotificationIntervalInfo = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isSupportActivityType() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportActivityType))).booleanValue();
    }

    public void configureSupportActivityType(boolean z) {
        this.isSupportActivityType = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isSupportAuthenticDevice() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportAuthenticDevice))).booleanValue();
    }

    public void configureSupportAuthenticDevice(boolean z) {
        this.isSupportAuthenticDevice = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isSupportMessageAlertInfo() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportMessageAlertInfo))).booleanValue();
    }

    public void configureSupportMessageAlertInfo(boolean z) {
        this.isSupportMessageAlertInfo = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isSupportMessageSupportInfo() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportMessageSupportInfo))).booleanValue();
    }

    public void configureSupportMessageSupportInfo(boolean z) {
        this.isSupportMessageSupportInfo = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isSupportMessageCenterPushDevice() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportMessageCenterPushDevice))).booleanValue();
    }

    public void configureupportMessageCenterPushDevice(boolean z) {
        this.isSupportMessageCenterPushDevice = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isSupportWearMessagePush() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportWearMessagePush))).booleanValue();
    }

    public void configureSupportWearMessagePush(boolean z) {
        this.isSupportWearMessagePush = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isSupportCallingOperationType() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportCallingOperationType))).booleanValue();
    }

    public void configureSupportCallingOperationType(boolean z) {
        this.isSupportCallingOperationType = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isSupportSetUserInfoEncrypt() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportSetUserInfoEncrypt))).booleanValue();
    }

    public void configureSupportSetUserInfoEncrypt(boolean z) {
        this.isSupportSetUserInfoEncrypt = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isSupportSampleFrame() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportSampleFrame))).booleanValue();
    }

    public void configureSupportSampleFrame(boolean z) {
        this.isSupportSampleFrame = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isSupportThreshold() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportThreshold))).booleanValue();
    }

    public void configureSupportThreshold(boolean z) {
        this.isSupportThreshold = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isSupportAntiLost() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportAntiLost))).booleanValue();
    }

    public void configureSupportAntiLost(boolean z) {
        this.isSupportAntiLost = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isSupportExerciseAdviceTime() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportExerciseAdviceTime))).booleanValue();
    }

    public void configureSupportExerciseAdviceTime(boolean z) {
        this.isSupportExerciseAdviceTime = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isSupportExerciseAdviceMonitor() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportExerciseAdviceMonitor))).booleanValue();
    }

    public void configureSupportExerciseAdviceMonitor(boolean z) {
        this.isSupportExerciseAdviceMonitor = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isSupportWorkoutInfo() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportWorkoutInfo))).booleanValue();
    }

    public void configureSupportWorkoutInfo(boolean z) {
        this.isSupportWorkoutInfo = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isSupportWorkoutReminder() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportWorkoutReminder))).booleanValue();
    }

    public void configureSupportWorkoutReminder(boolean z) {
        this.isSupportWorkoutReminder = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isSupportWorkoutRecord() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportWorkoutRecord))).booleanValue();
    }

    public void configureSupportWorkoutRecord(boolean z) {
        this.isSupportWorkoutRecord = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public void configureSupportWorkoutTrustHeartRate(boolean z) {
        this.isSupportWorkoutTrustHeartRate = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isSupportWorkoutTrustHeartRate() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportWorkoutTrustHeartRate))).booleanValue();
    }

    public boolean isSupportWorkoutExerciseDisplayLink() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportWorkoutExerciseDisplayLink))).booleanValue();
    }

    public void configureSupportWorkoutExerciseDisplayLink(boolean z) {
        this.isSupportWorkoutExerciseDisplayLink = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isSupportWorkoutRecordPaceMap() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportWorkoutRecordPaceMap))).booleanValue();
    }

    public void configureSupportWorkoutRecordPaceMap(boolean z) {
        this.isSupportWorkoutRecordPaceMap = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isSupportWorkoutCapabilicy() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportWorkoutCapabilicy))).booleanValue();
    }

    public boolean isSupportSleepBreathe() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportSleepBreathe))).booleanValue();
    }

    public void configureSupportSleepBreathe(boolean z) {
        this.isSupportSleepBreathe = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isSupportHiCarDriverRemind() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportHiCarDriverRemind))).booleanValue();
    }

    public void configureSupportHiCarDriverRemind(boolean z) {
        this.isSupportHiCarDriverRemind = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isSupportMessageFeedback() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportMessageFeedback))).booleanValue();
    }

    public void configureSupportMessageFeedback(boolean z) {
        this.isSupportMessageFeedback = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isSupportWearStatus() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportWearStatus))).booleanValue();
    }

    public void configureSupportWearStatus(boolean z) {
        this.isSupportWearStatus = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isSupportPhd() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportPhd))).booleanValue();
    }

    public void configureSupportPhd(boolean z) {
        this.isSupportPhd = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isSupportHiWear() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportHiWear))).booleanValue();
    }

    public void configureSupportHiWear(boolean z) {
        this.isSupportHiWear = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isSupportWearEngine() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportWearEngine))).booleanValue();
    }

    public void configureSupportWearEngine(boolean z) {
        this.isSupportWearEngine = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isSupportCheckDeviceSpace() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportCheckDeviceSpace))).booleanValue();
    }

    public void configureSupportCheckDeviceSpace(boolean z) {
        this.isSupportCheckDeviceSpace = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public void configureSupportWorkoutCapabilicy(boolean z) {
        this.isSupportWorkoutCapabilicy = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isSupportGpsData() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportGpsData))).booleanValue();
    }

    public void configureSupportGpsData(boolean z) {
        this.isSupportGpsData = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isSupportGpsSetParameter() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportGpsSetParameter))).booleanValue();
    }

    public void configureSupportGpsSetParameter(boolean z) {
        this.isSupportGpsSetParameter = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isSupportGpsPostProcessing() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportGpsPostProcessing))).booleanValue();
    }

    public void configureSupportGpsPostProcessing(boolean z) {
        this.isSupportGpsPostProcessing = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isSupportGpsSearchStarOptimization() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportGpsSearchStarOptimization))).booleanValue();
    }

    public void configureSupportGpsSearchStarOptimization(boolean z) {
        this.isSupportGpsSearchStarOptimization = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isSupportHeartRateInfo() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportHeartRateInfo))).booleanValue();
    }

    public void configureSupportHeartRateInfo(boolean z) {
        this.isSupportHeartRateInfo = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public void configureSupportStressInfo(boolean z) {
        this.isSupportStressInfo = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isSupportStressInfo() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportStressInfo))).booleanValue();
    }

    public boolean isSupportSportTotal() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportSportTotal))).booleanValue();
    }

    public void configureSupportSportTotal(boolean z) {
        this.isSupportSportTotal = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isSupportAccount() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportAccount))).booleanValue();
    }

    public void configureSupportAccount(boolean z) {
        this.isSupportAccount = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isSupportOneLevelMenu() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportOneLevelMenu))).booleanValue();
    }

    public void configureSupportOneLevelMenu(boolean z) {
        this.isSupportOneLevelMenu = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isSupportDeleteMsg() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportDeleteMsg))).booleanValue();
    }

    public void configureSupportDeleteMsg(boolean z) {
        this.isSupportDeleteMsg = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isSupportPosture() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportPosture))).booleanValue();
    }

    public void configureSupportPosture(boolean z) {
        this.isSupportPosture = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isSupportContinueHeartRate() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportContinueHeartRate))).booleanValue();
    }

    public void configureSupportContinueHeartRate(boolean z) {
        this.isSupportContinueHeartRate = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isSupportAtmosphere() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportAtmosphere))).booleanValue();
    }

    public void configureSupportAtmosphere(boolean z) {
        this.isSupportAtmosphere = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public void configureHeartRateRaiseAlarm(boolean z) {
        this.isSupportHeartRateRaiseAlarm = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public void configureHeartRateDownAlarm(boolean z) {
        this.isSupportHeartRateDownAlarm = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isSupportHeartRateRaiseAlarm() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportHeartRateRaiseAlarm))).booleanValue();
    }

    public boolean isSupportRestHeartRateControls() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportRestHeartRateControls))).booleanValue();
    }

    public void configureSupportRestHeartRateControls(boolean z) {
        this.isSupportRestHeartRateControls = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isSupportHeartRateDownAlarm() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportHeartRateDownAlarm))).booleanValue();
    }

    public boolean isSupportCycleBloodOxygenSwitch() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportCycleBloodOxygenSwitch))).booleanValue();
    }

    public void configureCycleBloodOxygenSwitch(boolean z) {
        this.isSupportCycleBloodOxygenSwitch = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isSupportBloodOxygenDownRemind() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportBloodOxygenDownRemind))).booleanValue();
    }

    public void configureBloodOxygenDownRemind(boolean z) {
        this.isSupportBloodOxygenDownRemind = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isSupportGetHeartRateRaiseAlarmNumber() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportGetHeartRateRaiseAlarmNumber))).booleanValue();
    }

    public void configureRunPaceSetCapability(boolean z) {
        this.isSupportRunPaceSet = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isSupportRunPaceSetCapability() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportRunPaceSet))).booleanValue();
    }

    public boolean isSupportMediumToHighStrengthPreValue() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportMediumToHighStrengthPreValue))).booleanValue();
    }

    public void configureSupportMediumToHighStrengthPreValue(boolean z) {
        this.isSupportMediumToHighStrengthPreValue = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isSupportGetHighAndMiddleSport() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportGetHighAndMiddleSport))).booleanValue();
    }

    public void configureGetHeartRateRaiseAlarmNumber(boolean z) {
        this.isSupportGetHeartRateRaiseAlarmNumber = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public void configureGetHighAndMiddleSport(boolean z) {
        this.isSupportGetHighAndMiddleSport = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean getIsSupportHrrHeartRateCapability() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportHrrHeartRateCapability))).booleanValue();
    }

    public void configureSetHrrHeartRateCapability(boolean z) {
        this.isSupportHrrHeartRateCapability = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isSupportChangeAlarm() {
        return this.isSupportChangeAlarm;
    }

    public void configureChangeAlarm(boolean z) {
        this.isSupportChangeAlarm = z;
    }

    public boolean isSupportPhonesInfo() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportPhonesInfo))).booleanValue();
    }

    public void configureIsSupportPhonesInfo(boolean z) {
        this.isSupportPhonesInfo = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isSupportNotifyDeviceBroadCast() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportNotifyDeviceBroadCast))).booleanValue();
    }

    public void configureIsSupportNotifyDeviceBroadCast(boolean z) {
        this.isSupportNotifyDeviceBroadCast = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public void configureIsSupportStressAppToDevice(boolean z) {
        this.isSupportStressAppToDevice = z;
    }

    public boolean isSupportStressAppToDevice() {
        return this.isSupportStressAppToDevice;
    }

    public void configureIsSupportMusicInfoList(boolean z) {
        this.isSupportMusicInfoList = z;
    }

    public boolean getIsSupportAutoUpdate() {
        return this.isSupportAutoUpdate;
    }

    public void configureIsSupportAutoUpdate(boolean z) {
        this.isSupportAutoUpdate = z;
    }

    public boolean getIsSupportUpdateChange() {
        return this.isSupportUpdateChange;
    }

    public void configureIsSupportUpdateChange(boolean z) {
        this.isSupportUpdateChange = z;
    }

    public boolean getIsSupportNotifyDeviceNewVersion() {
        return this.isSupportNotifyDeviceNewVersion;
    }

    public void configureIsSupportNotifyDeviceNewVersion(boolean z) {
        this.isSupportNotifyDeviceNewVersion = z;
    }

    public boolean getIsSupportDeviceRequestCheck() {
        return this.isSupportDeviceRequestCheck;
    }

    public void configureIsSupportDeviceRequestCheck(boolean z) {
        this.isSupportDeviceRequestCheck = z;
    }

    public boolean isSupportMusicInfoList() {
        return this.isSupportMusicInfoList;
    }

    public boolean isSupportZoneId() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportZoneId))).booleanValue();
    }

    public void configureSupportZoneId(boolean z) {
        this.isSupportZoneId = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isSupportHttps() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportHttp))).booleanValue();
    }

    public void configureSupportHttps(boolean z) {
        this.isSupportHttp = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isSupportSettingRelated() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportSettingRelated))).booleanValue();
    }

    public void configureSupportSettingRelated(boolean z) {
        this.isSupportSettingRelated = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isSupportLegalPrivacy() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportLegalPrivacy))).booleanValue();
    }

    public void configureSupportLegalPrivacy(boolean z) {
        this.isSupportLegalPrivacy = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isSupportLegalUserAgreement() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportLegalUserAgreement))).booleanValue();
    }

    public void configureSupportLegalUserAgreement(boolean z) {
        this.isSupportLegalUserAgreement = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isSupportLegalOpenSource() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportLegalOpenSource))).booleanValue();
    }

    public void configureSupportOpenSourceOpen(boolean z) {
        this.isSupportLegalOpenSource = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isSupportLegalServiceStatement() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportLegalServiceStatement))).booleanValue();
    }

    public void configureSupportLegalServiceStatement(boolean z) {
        this.isSupportLegalServiceStatement = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isSupportLegalSourceStatement() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportLegalSourceStatement))).booleanValue();
    }

    public void configureSupportLegalSourceStatement(boolean z) {
        this.isSupportLegalSourceStatement = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isSupportLegalSystemWebView() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportLegalSystemWebView))).booleanValue();
    }

    public void configureSupportLegalSystemWebView(boolean z) {
        this.isSupportLegalSystemWebView = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public void resetDeviceCapability() {
        new cut().c(this);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        new cut().NZ_(parcel, this);
    }

    public boolean isSupportFootWear() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportFootWear))).booleanValue();
    }

    public void configureSupportFootWear(boolean z) {
        this.isSupportFootWear = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isSupportAutoDetectMode() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportAutoDetectMode))).booleanValue();
    }

    public void configureSupportAutoDetectMode(boolean z) {
        this.isSupportAutoDetectMode = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isSupportRunPosture() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportRunPosture))).booleanValue();
    }

    public void configureSupportRunPosture(boolean z) {
        this.isSupportRunPosture = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isSupportInformCloseOrOpen() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportInformCloseOrOpen))).booleanValue();
    }

    public void configureSupportInformCloseOrOpen(boolean z) {
        this.isSupportInformCloseOrOpen = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isNeedSyncBeforeOta() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportNeedSyncBeforeOta))).booleanValue();
    }

    public void configureSyncBeforeOta(boolean z) {
        this.isSupportNeedSyncBeforeOta = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public void configureSyncHiCall(boolean z) {
        this.isSupportSyncHiCall = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isSupportSyncHiCall() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportSyncHiCall))).booleanValue();
    }

    public void configureSyncContacts(boolean z) {
        this.isSupportSyncContacts = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isSupportSyncContacts() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportSyncContacts))).booleanValue();
    }

    public boolean isSupportSelfUploadDeviceLog() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportSelfUploadDeviceLog))).booleanValue();
    }

    public void setSupportSelfUploadDeviceLog(boolean z) {
        this.isSupportSelfUploadDeviceLog = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isSupportChangePhonePair() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportChangePhonePair))).booleanValue();
    }

    public void configureSupportChangePhonePair(boolean z) {
        this.isSupportChangePhonePair = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isSupportAccountSwitch() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportAccountSwitch))).booleanValue();
    }

    public void configureSupportAccountSwitch(boolean z) {
        this.isSupportAccountSwitch = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isSupportWatchFaceAppId() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportWatchFaceAppId))).booleanValue();
    }

    public void configureSupportWatchFaceAppId(boolean z) {
        this.isSupportWatchFaceAppId = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isHideWalletEntrance() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isHideWalletEntrance))).booleanValue();
    }

    public void configureHideWalletEntrance(boolean z) {
        this.isHideWalletEntrance = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isSupportEcgAuth() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportEcgAuth))).booleanValue();
    }

    public void configureSupportEcgAuth(boolean z) {
        this.isSupportEcgAuth = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isSupportCoreSleepNewFile() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportCoreSleepNewFile))).booleanValue();
    }

    public void configureSupportCoreSleepNewFile(boolean z) {
        this.isSupportCoreSleepNewFile = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isSupportSyncTime() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportSyncTime))).booleanValue();
    }

    public void configureSupportSyncTime(boolean z) {
        this.isSupportSyncTime = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isSupportRriNewFile() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportRriNewFile))).booleanValue();
    }

    public void configureSupportRriNewFile(boolean z) {
        this.isSupportRriNewFile = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isSupportUploadGpsAndPdrFile() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportUploadGpsAndPdrFile))).booleanValue();
    }

    public void configureIsSupportUploadGpsAndPdrFile(boolean z) {
        this.isSupportUploadGpsAndPdrFile = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isSupportConnectStatus() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportConnectStatus))).booleanValue();
    }

    public void configureSupportConnectStatus(boolean z) {
        this.isSupportConnectStatus = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isSupportExpandCapability() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportExpandCapability))).booleanValue();
    }

    public void configureSupportExpandCapability(boolean z) {
        this.isSupportExpandCapability = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isSupportSyncWifi() {
        return ((Boolean) bmb.d(Boolean.valueOf(this.isSupportSyncWifi))).booleanValue();
    }

    public void configureSupportSyncWifi(boolean z) {
        this.isSupportSyncWifi = ((Boolean) bmb.d(Boolean.valueOf(z))).booleanValue();
    }
}
