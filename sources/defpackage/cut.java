package defpackage;

import android.os.Parcel;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;

/* loaded from: classes3.dex */
public class cut {

    /* renamed from: a, reason: collision with root package name */
    private cuu f11487a = new cuu();

    private int e(boolean z) {
        return z ? 1 : 0;
    }

    private void MZ_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureHmsNotifyUpdate(parcel.readByte() == 1);
    }

    private void MY_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureHmsAutoUpdateWifi(parcel.readByte() == 1);
    }

    private void MX_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureHmsAutoUpdate(parcel.readByte() == 1);
    }

    private void Nb_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureIsSupportNotifyDeviceNewVersion(parcel.readByte() == 1);
    }

    private void Na_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureIsSupportDeviceRequestCheck(parcel.readByte() == 1);
    }

    private void Nk_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureSupportHiWear(parcel.readByte() == 1);
    }

    private void Nu_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureSupportWearEngine(parcel.readByte() == 1);
    }

    private void Nf_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureSupportWearEngine(parcel.readByte() == 1);
    }

    private void Nv_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureSupportWorkoutTrustHeartRate(parcel.readByte() == 1);
    }

    private void Nh_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureSupportCoreSleepNewFile(parcel.readByte() == 1);
    }

    private void Nn_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureSupportRriNewFile(parcel.readByte() == 1);
    }

    private void NS_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureIsSupportUploadGpsAndPdrFile(parcel.readByte() == 1);
    }

    private void Nm_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureSupportRestHeartRateControls(parcel.readByte() == 1);
    }

    private void NC_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.setSupportDeviceFutureWeatherCapability(parcel.readByte() == 1);
    }

    private void Ni_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureCycleBloodOxygenSwitch(parcel.readByte() == 1);
    }

    private void Ne_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureBloodOxygenDownRemind(parcel.readByte() == 1);
    }

    private void No_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureRunPaceSetCapability(parcel.readByte() == 1);
    }

    private void NR_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.setSupportTide(parcel.readByte() == 1);
    }

    private void Nd_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureSupportAppId(parcel.readByte() == 1);
    }

    private void Nt_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureSupportWalletOpenCard(parcel.readByte() == 1);
    }

    private void NN_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.setSupportSendSwitchStatus(parcel.readByte() == 1);
    }

    private void NV_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureSupportZoneId(parcel.readByte() == 1);
    }

    private void NK_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.setSupportMarketParams(parcel.readByte() == 1);
    }

    private void ND_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureSupportHttps(parcel.readByte() == 1);
    }

    private void NB_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureSupportChangePhonePair(parcel.readByte() == 1);
    }

    private void Nz_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureSupportAccountSwitch(parcel.readByte() == 1);
    }

    private void Np_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureSyncContacts(parcel.readByte() == 1);
    }

    private void Nq_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureSyncHiCall(parcel.readByte() == 1);
    }

    private void NQ_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.setSupportSyncAccount(parcel.readByte() == 1);
    }

    private void NO_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureSupportSettingRelated(parcel.readByte() == 1);
    }

    private void NF_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureSupportLegalPrivacy(parcel.readByte() == 1);
    }

    private void NJ_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureSupportLegalUserAgreement(parcel.readByte() == 1);
    }

    private void NE_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureSupportOpenSourceOpen(parcel.readByte() == 1);
    }

    private void NG_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureSupportLegalServiceStatement(parcel.readByte() == 1);
    }

    private void NH_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureSupportLegalSourceStatement(parcel.readByte() == 1);
    }

    private void NI_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureSupportLegalSystemWebView(parcel.readByte() == 1);
    }

    private void Nl_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureSupportNewEsim(parcel.readByte() == 1);
    }

    private void NM_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.setSupportSelfUploadDeviceLog(parcel.readByte() == 1);
    }

    private void NT_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureSupportUserSetting(parcel.readByte() == 1);
    }

    private void Nw_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureSupportWatchFaceAppId(parcel.readByte() == 1);
    }

    private void NP_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.setSupportSmartWatchVersionStatus(parcel.readByte() == 1);
    }

    private void NU_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.setSupportWeatherErrorCode(parcel.readByte() == 1);
    }

    private void Nc_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureSmartWatchVersionTypeValue(parcel.readInt());
    }

    private void Ny_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureHideWalletEntrance(parcel.readByte() == 1);
    }

    private void Nx_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureSupportEcgAuth(parcel.readByte() == 1);
    }

    private void NA_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.setSupportActivityRecognitionStatus(parcel.readByte() == 1);
    }

    private void NL_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureSupportMediumToHighStrengthPreValue(parcel.readByte() == 1);
    }

    private void Nr_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureSupportSyncTime(parcel.readByte() == 1);
    }

    private void Ng_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureSupportConnectStatus(parcel.readByte() == 1);
    }

    private void Nj_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureSupportExpandCapability(parcel.readByte() == 1);
    }

    private void Ns_(Parcel parcel, DeviceCapability deviceCapability) {
        deviceCapability.configureSupportSyncWifi(parcel.readByte() == 1);
    }

    private void MV_(Parcel parcel, DeviceCapability deviceCapability) {
        NC_(parcel, deviceCapability);
        NR_(parcel, deviceCapability);
        Nd_(parcel, deviceCapability);
        Nt_(parcel, deviceCapability);
        NN_(parcel, deviceCapability);
        NV_(parcel, deviceCapability);
        NK_(parcel, deviceCapability);
        ND_(parcel, deviceCapability);
        Nk_(parcel, deviceCapability);
        Nq_(parcel, deviceCapability);
        Np_(parcel, deviceCapability);
        NQ_(parcel, deviceCapability);
        NO_(parcel, deviceCapability);
        NF_(parcel, deviceCapability);
        NJ_(parcel, deviceCapability);
        NE_(parcel, deviceCapability);
        NG_(parcel, deviceCapability);
        NH_(parcel, deviceCapability);
        Nl_(parcel, deviceCapability);
        NM_(parcel, deviceCapability);
        NB_(parcel, deviceCapability);
        Nz_(parcel, deviceCapability);
        NT_(parcel, deviceCapability);
        Nw_(parcel, deviceCapability);
        NP_(parcel, deviceCapability);
        Nc_(parcel, deviceCapability);
        Ny_(parcel, deviceCapability);
        Nx_(parcel, deviceCapability);
        Nv_(parcel, deviceCapability);
        NA_(parcel, deviceCapability);
        Nm_(parcel, deviceCapability);
        Nb_(parcel, deviceCapability);
        Na_(parcel, deviceCapability);
        Ni_(parcel, deviceCapability);
        Ne_(parcel, deviceCapability);
        NU_(parcel, deviceCapability);
        No_(parcel, deviceCapability);
        NL_(parcel, deviceCapability);
        Nh_(parcel, deviceCapability);
        Nr_(parcel, deviceCapability);
        MX_(parcel, deviceCapability);
        MY_(parcel, deviceCapability);
        MZ_(parcel, deviceCapability);
        Nn_(parcel, deviceCapability);
        NS_(parcel, deviceCapability);
        Ng_(parcel, deviceCapability);
        Nu_(parcel, deviceCapability);
        MW_(parcel, deviceCapability);
    }

    private void MW_(Parcel parcel, DeviceCapability deviceCapability) {
        Nj_(parcel, deviceCapability);
        Ns_(parcel, deviceCapability);
        Nf_(parcel, deviceCapability);
        NI_(parcel, deviceCapability);
    }

    public DeviceCapability NY_(Parcel parcel) {
        DeviceCapability deviceCapability = new DeviceCapability();
        if (parcel == null) {
            return deviceCapability;
        }
        this.f11487a.Qz_(parcel, deviceCapability);
        this.f11487a.QA_(parcel, deviceCapability);
        this.f11487a.QB_(parcel, deviceCapability);
        MV_(parcel, deviceCapability);
        return deviceCapability;
    }

    public void NZ_(Parcel parcel, DeviceCapability deviceCapability) {
        if (parcel == null || deviceCapability == null) {
            return;
        }
        this.f11487a.QC_(parcel, deviceCapability);
        this.f11487a.QD_(parcel, deviceCapability);
        this.f11487a.QE_(parcel, deviceCapability);
        NW_(parcel, deviceCapability);
    }

    private void NW_(Parcel parcel, DeviceCapability deviceCapability) {
        parcel.writeByte((byte) e(deviceCapability.isSupportSosTransmission()));
        parcel.writeByte((byte) e(deviceCapability.isSupportSendSosSms()));
        parcel.writeByte((byte) e(deviceCapability.isSupportDeviceFutureWeatherCapability()));
        parcel.writeByte((byte) e(deviceCapability.isSupportTide()));
        parcel.writeByte((byte) e(deviceCapability.isSupportAppId()));
        parcel.writeByte((byte) e(deviceCapability.isSupportWalletOpenCard()));
        parcel.writeByte((byte) e(deviceCapability.isSupportSendSwitchStatus()));
        parcel.writeByte((byte) e(deviceCapability.isSupportZoneId()));
        parcel.writeByte((byte) e(deviceCapability.isSupportMarketParams()));
        parcel.writeByte((byte) e(deviceCapability.isSupportHttps()));
        parcel.writeByte((byte) e(deviceCapability.isSupportHiWear()));
        parcel.writeByte((byte) e(deviceCapability.isSupportSyncHiCall()));
        parcel.writeByte((byte) e(deviceCapability.isSupportSyncContacts()));
        parcel.writeByte((byte) e(deviceCapability.isSupportSyncAccount()));
        parcel.writeByte((byte) e(deviceCapability.isSupportSettingRelated()));
        parcel.writeByte((byte) e(deviceCapability.isSupportLegalPrivacy()));
        parcel.writeByte((byte) e(deviceCapability.isSupportLegalUserAgreement()));
        parcel.writeByte((byte) e(deviceCapability.isSupportLegalOpenSource()));
        parcel.writeByte((byte) e(deviceCapability.isSupportLegalServiceStatement()));
        parcel.writeByte((byte) e(deviceCapability.isSupportLegalSourceStatement()));
        parcel.writeByte((byte) e(deviceCapability.isSupportNewEsim()));
        parcel.writeByte((byte) e(deviceCapability.isSupportSelfUploadDeviceLog()));
        parcel.writeByte((byte) e(deviceCapability.isSupportChangePhonePair()));
        parcel.writeByte((byte) e(deviceCapability.isSupportAccountSwitch()));
        parcel.writeByte((byte) e(deviceCapability.isSupportUserSetting()));
        parcel.writeByte((byte) e(deviceCapability.isSupportWatchFaceAppId()));
        parcel.writeByte((byte) e(deviceCapability.isSupportSmartWatchVersionStatus()));
        parcel.writeInt(deviceCapability.getSmartWatchVersionTypeValue());
        parcel.writeByte((byte) e(deviceCapability.isHideWalletEntrance()));
        parcel.writeByte((byte) e(deviceCapability.isSupportEcgAuth()));
        parcel.writeByte((byte) e(deviceCapability.isSupportWorkoutTrustHeartRate()));
        parcel.writeByte((byte) e(deviceCapability.isSupportActivityRecognitionStatus()));
        parcel.writeByte((byte) e(deviceCapability.isSupportRestHeartRateControls()));
        parcel.writeByte((byte) e(deviceCapability.getIsSupportNotifyDeviceNewVersion()));
        parcel.writeByte((byte) e(deviceCapability.getIsSupportDeviceRequestCheck()));
        parcel.writeByte((byte) e(deviceCapability.isSupportCycleBloodOxygenSwitch()));
        parcel.writeByte((byte) e(deviceCapability.isSupportBloodOxygenDownRemind()));
        parcel.writeByte((byte) e(deviceCapability.isSupportWeatherErrorCode()));
        parcel.writeByte((byte) e(deviceCapability.isSupportRunPaceSetCapability()));
        parcel.writeByte((byte) e(deviceCapability.isSupportMediumToHighStrengthPreValue()));
        parcel.writeByte((byte) e(deviceCapability.isSupportCoreSleepNewFile()));
        parcel.writeByte((byte) e(deviceCapability.isSupportSyncTime()));
        parcel.writeByte((byte) e(deviceCapability.isHmsAutoUpdate()));
        parcel.writeByte((byte) e(deviceCapability.isHmsAutoUpdateWifi()));
        parcel.writeByte((byte) e(deviceCapability.isHmsNotifyUpdate()));
        NX_(parcel, deviceCapability);
    }

    private void NX_(Parcel parcel, DeviceCapability deviceCapability) {
        parcel.writeByte((byte) e(deviceCapability.isSupportRriNewFile()));
        parcel.writeByte((byte) e(deviceCapability.isSupportUploadGpsAndPdrFile()));
        parcel.writeByte((byte) e(deviceCapability.isSupportConnectStatus()));
        parcel.writeByte((byte) e(deviceCapability.isSupportWearEngine()));
        parcel.writeByte((byte) e(deviceCapability.isSupportExpandCapability()));
        parcel.writeByte((byte) e(deviceCapability.isSupportSyncWifi()));
        parcel.writeByte((byte) e(deviceCapability.isSupportCheckDeviceSpace()));
        parcel.writeByte((byte) e(deviceCapability.isSupportLegalSystemWebView()));
    }

    public void c(DeviceCapability deviceCapability) {
        this.f11487a.a(deviceCapability);
        this.f11487a.b(deviceCapability);
        this.f11487a.e(deviceCapability);
        d(deviceCapability);
    }

    private void d(DeviceCapability deviceCapability) {
        deviceCapability.configureSupportSendSosSms(false);
        deviceCapability.setSupportDeviceFutureWeatherCapability(false);
        deviceCapability.setSupportTide(false);
        deviceCapability.configureSupportAppId(false);
        deviceCapability.configureSupportWalletOpenCard(false);
        deviceCapability.setSupportSendSwitchStatus(false);
        deviceCapability.configureSupportZoneId(false);
        deviceCapability.setSupportMarketParams(false);
        deviceCapability.configureSupportHttps(false);
        deviceCapability.configureSupportHiWear(false);
        deviceCapability.configureSyncHiCall(false);
        deviceCapability.configureSyncContacts(false);
        deviceCapability.setSupportSyncAccount(false);
        deviceCapability.configureSupportSettingRelated(false);
        deviceCapability.configureSupportLegalPrivacy(false);
        deviceCapability.configureSupportLegalUserAgreement(false);
        deviceCapability.configureSupportOpenSourceOpen(false);
        deviceCapability.configureSupportLegalServiceStatement(false);
        deviceCapability.configureSupportLegalSourceStatement(false);
        deviceCapability.configureSupportNewEsim(false);
        deviceCapability.setSupportSelfUploadDeviceLog(false);
        deviceCapability.configureSupportChangePhonePair(false);
        deviceCapability.configureSupportAccountSwitch(false);
        deviceCapability.configureSupportUserSetting(false);
        deviceCapability.configureSupportWatchFaceAppId(false);
        deviceCapability.setSupportSmartWatchVersionStatus(false);
        deviceCapability.configureSmartWatchVersionTypeValue(0);
        deviceCapability.configureHideWalletEntrance(false);
        deviceCapability.configureSupportEcgAuth(false);
        deviceCapability.configureSupportWorkoutTrustHeartRate(false);
        deviceCapability.setSupportActivityRecognitionStatus(false);
        deviceCapability.configureSupportRestHeartRateControls(false);
        deviceCapability.configureIsSupportNotifyDeviceNewVersion(false);
        deviceCapability.configureIsSupportDeviceRequestCheck(false);
        deviceCapability.configureCycleBloodOxygenSwitch(false);
        deviceCapability.configureBloodOxygenDownRemind(false);
        deviceCapability.setSupportWeatherErrorCode(false);
        deviceCapability.configureRunPaceSetCapability(false);
        deviceCapability.configureSupportMediumToHighStrengthPreValue(false);
        deviceCapability.configureSupportCoreSleepNewFile(false);
        deviceCapability.configureSupportSyncTime(false);
        deviceCapability.configureHmsAutoUpdate(false);
        deviceCapability.configureHmsAutoUpdateWifi(false);
        deviceCapability.configureHmsNotifyUpdate(false);
        deviceCapability.configureSupportRriNewFile(false);
        deviceCapability.configureIsSupportUploadGpsAndPdrFile(false);
        deviceCapability.configureSupportConnectStatus(false);
        a(deviceCapability);
    }

    private void a(DeviceCapability deviceCapability) {
        deviceCapability.configureSupportWearEngine(false);
        deviceCapability.configureSupportExpandCapability(false);
        deviceCapability.configureSupportSyncWifi(false);
        deviceCapability.configureSupportCheckDeviceSpace(false);
        deviceCapability.configureSupportLegalSystemWebView(false);
    }
}
