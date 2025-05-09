package com.huawei.hwdevice.phoneprocess.mgr.hwtwsmgr;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.datatype.DeviceCommand;
import com.huawei.haf.common.utils.NetworkUtil;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.profile.client.profile.ProfileClient;
import com.huawei.profile.coordinator.ProfileRequestConstants;
import com.huawei.profile.profile.DeviceProfile;
import com.huawei.profile.profile.ProfileExtendConstants;
import com.huawei.profile.profile.ServiceCharacteristicProfile;
import com.huawei.profile.profile.ServiceProfile;
import defpackage.cvx;
import defpackage.cwd;
import defpackage.cwe;
import defpackage.cwg;
import defpackage.cwl;
import defpackage.jsz;
import defpackage.kfg;
import defpackage.koq;
import health.compact.a.CommonUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public abstract class HwTwsPutDeviceTask extends BaseTwsTask {
    private static final int ACCOUNT_ID_ORDER = 12;
    private static final int CLASS_ORDER = 14;
    private static final int CONVERT_TIME = 2;
    private static final int DEFAULT_VALUE = 0;
    private static final int DEVICE_NAME_ORDER = 7;
    private static final int DEVICE_TYPE_ORDER = 3;
    private static final int HIV_ORDER = 6;
    private static final int LENGTH_VALUE = 1;
    private static final int MAC_ORDER = 8;
    private static final int MANU_ORDER = 4;
    private static final int MODEL_ORDER = 2;
    private static final int PAIR_STATE_ORDER = 10;
    private static final int PRODID_ORDER = 5;
    private static final int PRODUCT_TYPE_WEAR = 4;
    private static final int RESULT_ORDER = 13;
    private static final String SERVICE_ID = "SyncPairToken";
    private static final String SERVICE_TYPE = "syncPairToken";
    private static final int STRING_THRID = 16;
    private static final String TAG = "HwTwsPutDeviceTask";
    private static final String TAG_RELEASE = "R_HwTwsPutDeviceTask";
    private static final int TIMESTAMP_ORDER = 11;
    private static final int TOKEN_ORDER = 9;
    private static final int TOTAL_LENGTH = 32;
    private static final int UDID_ORDER = 1;
    private static final int UUID1_ORDER = 15;
    private static final int UUID2_ORDER = 16;
    private static final int UUID3_ORDER = 17;
    private static final int UUID4_ORDER = 18;
    private static final int UUID5_ORDER = 19;
    private static final int UUID6_ORDER = 20;
    private static final int UUID7_ORDER = 21;
    private static final int UUID8_ORDER = 22;
    private Context mContext;
    private String mData;
    private cwl mTlvUtils;

    @Override // com.huawei.hwdevice.phoneprocess.mgr.hwtwsmgr.BaseTwsTask
    public boolean onWaitFor(String str, Object obj) {
        return false;
    }

    public HwTwsPutDeviceTask(String str, cwl cwlVar, Context context) {
        this.mData = str;
        this.mTlvUtils = cwlVar;
        this.mContext = context;
    }

    @Override // com.huawei.hwdevice.phoneprocess.mgr.hwtwsmgr.BaseTwsTask
    public boolean onExecutor(ProfileClient profileClient) {
        String handlerReceiverCommand20 = handlerReceiverCommand20(profileClient);
        boolean z = !TextUtils.isEmpty(handlerReceiverCommand20);
        ReleaseLogUtil.e(TAG_RELEASE, "Put device to profile is success:", Boolean.valueOf(z));
        sendCommand20(z, handlerReceiverCommand20);
        return true;
    }

    private String handlerReceiverCommand20(ProfileClient profileClient) {
        boolean j = NetworkUtil.j();
        LogUtil.a(TAG, "handlerReceiverCommand20 networkAvailable=", Boolean.valueOf(j));
        if (!j) {
            ReleaseLogUtil.e(TAG_RELEASE, "handlerReceiverCommand20Put networkAvailable is not available");
            return "";
        }
        kfg buildPairDevice = buildPairDevice();
        if (buildPairDevice == null) {
            return "";
        }
        if (!isSameAccount(buildPairDevice.e())) {
            LogUtil.h(TAG, "handlerReceiverCommand20 but account not match");
            return "";
        }
        if (!profileClient.putDevice(buildDeviceProfile(buildPairDevice))) {
            LogUtil.h(TAG, "handlerReceiverCommand20 but put device failed!");
            return "";
        }
        if (!profileClient.putServiceOfDevice(buildPairDevice.n(), buildServiceProfile(buildPairDevice))) {
            LogUtil.h(TAG, "handlerReceiverCommand20 but put service failed!");
            return "";
        }
        if (!profileClient.putServiceCharacteristic(buildPairDevice.n(), buildPairDevice.m(), buildServiceCharacteristicProfile(buildPairDevice))) {
            LogUtil.h(TAG, "handlerReceiverCommand20 but put service characteristic failed!");
            return "";
        }
        if (!CommonUtil.ag(BaseApplication.getContext())) {
            try {
                Thread.sleep(ProfileExtendConstants.TIME_OUT);
            } catch (InterruptedException unused) {
                LogUtil.b(TAG, "handlerReceiverCommand20 occur InterruptedException");
            }
            List<DeviceProfile> devicesByType = profileClient.getDevicesByType(buildPairDevice.d(), true, Arrays.asList(ProfileClient.CLOUD_LOCAL_DOMAIN));
            boolean z = false;
            if (devicesByType != null && devicesByType.size() > 0) {
                LogUtil.a(TAG, "handlerReceiverCommand20 deviceListByType size=", Integer.valueOf(devicesByType.size()));
                for (DeviceProfile deviceProfile : devicesByType) {
                    if (deviceProfile == null || TextUtils.isEmpty(deviceProfile.getId())) {
                        LogUtil.h(TAG, "handlerReceiverCommand20 deviceListByType but profile is null or get id is null");
                    } else {
                        Map<String, Object> profile = deviceProfile.getProfile();
                        if (buildPairDevice.d().equals(getValueFromProfile(profile, "devType"))) {
                            String valueFromProfile = getValueFromProfile(profile, "mac");
                            if (!TextUtils.isEmpty(valueFromProfile) && buildPairDevice.h().equals(valueFromProfile)) {
                                LogUtil.h(TAG, "handlerReceiverCommand20 deviceListByType match macAddress");
                                z = true;
                            }
                        }
                    }
                }
            } else {
                ReleaseLogUtil.e(TAG_RELEASE, "handlerReceiverCommand20 deviceListByType is empty");
            }
            ReleaseLogUtil.e(TAG_RELEASE, "handlerReceiverCommand20 flag=", Boolean.valueOf(z));
            return z ? buildPairDevice.n() : "";
        }
        LogUtil.a(TAG, "handlerReceiverCommand20 release logic");
        return buildPairDevice.n();
    }

    private String getValueFromProfile(Map<String, Object> map, String str) {
        Object obj = map.get(str);
        if (obj == null) {
            LogUtil.h(TAG, "getValueFromProfile: Get fail key.");
            return "";
        }
        if (!(obj instanceof String)) {
            LogUtil.h(TAG, "getValueFromProfile: class type is wrong key.");
            return "";
        }
        return (String) obj;
    }

    private ServiceCharacteristicProfile buildServiceCharacteristicProfile(kfg kfgVar) {
        ServiceCharacteristicProfile serviceCharacteristicProfile = new ServiceCharacteristicProfile();
        serviceCharacteristicProfile.setIsNeedCloud(true);
        serviceCharacteristicProfile.addEntityInfo("pairToken", cvx.d(kfgVar.l()));
        serviceCharacteristicProfile.addEntityInfo("pairState", Integer.valueOf(kfgVar.g()));
        serviceCharacteristicProfile.addEntityInfo("UUID", kfgVar.p());
        if (kfgVar.c() > 0) {
            serviceCharacteristicProfile.addEntityInfo("class", Integer.valueOf(kfgVar.c()));
        }
        if (kfgVar.o() > 0) {
            serviceCharacteristicProfile.addEntityInfo("timestamp", Long.valueOf(kfgVar.o()));
        }
        return serviceCharacteristicProfile;
    }

    private ServiceProfile buildServiceProfile(kfg kfgVar) {
        ServiceProfile serviceProfile = new ServiceProfile();
        serviceProfile.setIsNeedCloud(true);
        serviceProfile.setId(kfgVar.m());
        serviceProfile.setType(kfgVar.k());
        if (kfgVar.o() > 0) {
            serviceProfile.addEntityInfo("timestamp", Long.valueOf(kfgVar.o()));
        }
        return serviceProfile;
    }

    private kfg buildPairDevice() {
        cwe cweVar;
        try {
            cweVar = this.mTlvUtils.a(this.mData);
        } catch (cwg unused) {
            LogUtil.b(TAG, "buildPairDevice but find TlvException");
            cweVar = null;
        }
        if (cweVar == null) {
            LogUtil.h(TAG, "buildPairDevice but not build tlv father");
            return null;
        }
        List<cwd> e = cweVar.e();
        if (koq.b(e)) {
            LogUtil.h(TAG, "buildPairDevice but tlv list is empty");
            return null;
        }
        StringBuilder sb = new StringBuilder(16);
        kfg kfgVar = new kfg();
        buildDeviceFromTlv(kfgVar, sb, e);
        if (TextUtils.isEmpty(kfgVar.h())) {
            LogUtil.h(TAG, "buildPairDevice but not find mac");
            return null;
        }
        if (kfgVar.l().length <= 0) {
            LogUtil.h(TAG, "buildPairDevice but not find token");
            return null;
        }
        kfgVar.k(sb.toString());
        kfgVar.g(SERVICE_ID);
        kfgVar.h(SERVICE_TYPE);
        return kfgVar;
    }

    private void buildDeviceFromTlv(kfg kfgVar, StringBuilder sb, List<cwd> list) {
        for (cwd cwdVar : list) {
            int w = CommonUtil.w(cwdVar.e());
            switch (w) {
                case 1:
                    kfgVar.m(cvx.e(cwdVar.c()));
                    break;
                case 2:
                    kfgVar.j(cvx.e(cwdVar.c()));
                    break;
                case 3:
                    kfgVar.e(cvx.e(cwdVar.c()));
                    break;
                case 4:
                    kfgVar.f(cvx.e(cwdVar.c()));
                    break;
                case 5:
                    kfgVar.i(cvx.e(cwdVar.c()));
                    break;
                case 6:
                    kfgVar.a(cvx.e(cwdVar.c()));
                    break;
                case 7:
                    kfgVar.b(cvx.e(cwdVar.c()));
                    break;
                case 8:
                    kfgVar.d(cvx.e(cwdVar.c()));
                    break;
                case 9:
                    kfgVar.e(cvx.a(cwdVar.c()));
                    break;
                case 10:
                    kfgVar.e(CommonUtil.w(cwdVar.c()));
                    break;
                case 11:
                    kfgVar.a(Long.valueOf(CommonUtil.y(cwdVar.c())));
                    break;
                case 12:
                    kfgVar.c(cvx.e(cwdVar.c()));
                    break;
                case 13:
                default:
                    appendUuid(w, sb, cwdVar);
                    break;
                case 14:
                    kfgVar.d(CommonUtil.w(cwdVar.c()));
                    break;
            }
        }
    }

    private void appendUuid(int i, StringBuilder sb, cwd cwdVar) {
        switch (i) {
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
                sb.append(buildUuid(cwdVar.c()));
                break;
            default:
                LogUtil.h(TAG, "receiverCommand20 default:", Integer.valueOf(i));
                break;
        }
    }

    private boolean isSameAccount(String str) {
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1000);
        if (TextUtils.isEmpty(accountInfo)) {
            return false;
        }
        return accountInfo.equals(str);
    }

    private void addEntityInfo(DeviceProfile deviceProfile, String str, int i) {
        deviceProfile.addEntityInfo(str, Integer.valueOf(i));
    }

    private void addEntityInfo(DeviceProfile deviceProfile, String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        deviceProfile.addEntityInfo(str, str2);
    }

    private DeviceProfile buildDeviceProfile(kfg kfgVar) {
        DeviceProfile deviceProfile = new DeviceProfile();
        deviceProfile.setIsNeedCloud(true);
        deviceProfile.setId(kfgVar.n());
        deviceProfile.setType(kfgVar.d());
        addEntityInfo(deviceProfile, "udid", kfgVar.n());
        addEntityInfo(deviceProfile, "devType", kfgVar.d());
        addEntityInfo(deviceProfile, "model", kfgVar.j());
        addEntityInfo(deviceProfile, ProfileRequestConstants.MANU, kfgVar.f());
        addEntityInfo(deviceProfile, "prodId", kfgVar.i());
        addEntityInfo(deviceProfile, ProfileRequestConstants.HIV, kfgVar.b());
        addEntityInfo(deviceProfile, "mac", kfgVar.h());
        addEntityInfo(deviceProfile, "deviceName", kfgVar.a());
        addEntityInfo(deviceProfile, ProfileRequestConstants.PROT_TYPE, 4);
        return deviceProfile;
    }

    private void sendCommand20(boolean z, String str) {
        String str2;
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(43);
        deviceCommand.setCommandID(20);
        if (str == null || str.length() == 0) {
            str2 = "";
        } else {
            str2 = cvx.e(1) + cvx.e(cvx.c(str).length() / 2) + cvx.c(str);
        }
        String str3 = str2 + cvx.e(13) + cvx.e(1) + cvx.e(!z ? 1 : 0);
        deviceCommand.setDataContent(cvx.a(str3));
        deviceCommand.setDataLen(cvx.a(str3).length);
        LogUtil.a(TAG, "sendCommand20: ", deviceCommand.toString());
        jsz.b(this.mContext).sendDeviceData(deviceCommand);
    }

    private String buildUuid(String str) {
        if (TextUtils.isEmpty(str) || str.length() != 32) {
            return "";
        }
        StringBuilder sb = new StringBuilder(16);
        int[] iArr = {8, 4, 4, 4, 12};
        for (int i = 0; i < 5; i++) {
            int i2 = iArr[i];
            if (str.length() < i2) {
                LogUtil.h(TAG, "out of tempValue length");
            } else {
                sb.append(str.substring(0, i2));
                if (i2 == 12) {
                    sb.append(",");
                } else {
                    sb.append(Constants.LINK);
                    str = str.substring(i2);
                }
            }
        }
        String sb2 = sb.toString();
        LogUtil.a(TAG, "buildUuid success");
        return sb2;
    }
}
