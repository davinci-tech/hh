package defpackage;

import android.text.TextUtils;
import com.apprichtap.haptic.base.PrebakedEffectId;
import com.huawei.devicesdk.entity.ExternalDeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.unitedevice.entity.UniteDevice;
import health.compact.a.KeyValDbManager;
import health.compact.a.LogUtil;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.SharedPreferenceManager;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import org.slf4j.Marker;

/* loaded from: classes3.dex */
public class blc {

    /* renamed from: a, reason: collision with root package name */
    private static final Map<String, String> f430a = new HashMap(5);
    private static final Map<String, String> c = new HashMap(5);
    private static Map<String, DeviceCapability> e = new ConcurrentHashMap(5);
    private static volatile Boolean d = null;

    public static DeviceInfo d(com.huawei.devicesdk.entity.DeviceInfo deviceInfo) {
        UniteDevice uniteDevice = new UniteDevice();
        uniteDevice.setDeviceInfo(deviceInfo);
        return c(uniteDevice);
    }

    public static DeviceInfo c(UniteDevice uniteDevice) {
        if (uniteDevice == null || uniteDevice.getDeviceInfo() == null) {
            LogUtil.a("DeviceConvertUtil", "deviceInfo or getDeviceInfo is null.");
            return null;
        }
        com.huawei.devicesdk.entity.DeviceInfo deviceInfo = uniteDevice.getDeviceInfo();
        DeviceInfo deviceInfo2 = new DeviceInfo();
        deviceInfo2.setmDeviceId("");
        deviceInfo2.setDeviceName(deviceInfo.getDeviceName());
        deviceInfo2.setProductId("");
        deviceInfo2.setDeviceIdentify(deviceInfo.getDeviceMac());
        deviceInfo2.setDeviceProtocol(2);
        deviceInfo2.setProductType(deviceInfo.getDeviceType());
        deviceInfo2.setDeviceActiveState(deviceInfo.isUsing() ? 1 : 0);
        deviceInfo2.setDeviceConnectState(deviceInfo.getDeviceConnectState());
        deviceInfo2.setEncryptType(1);
        deviceInfo2.setDeviceBluetoothType(deviceInfo.getDeviceBtType());
        deviceInfo2.setUuid(deviceInfo.getDeviceSn());
        deviceInfo2.setDeviceModel(deviceInfo.getDeviceMode());
        deviceInfo2.setDeviceIdType(deviceInfo.getIdToServerType());
        deviceInfo2.setAuthVersion(2);
        deviceInfo2.setAutoDetectSwitchStatus(deviceInfo.getAutoDetectSwitchStatus());
        deviceInfo2.setFootWearPosition(deviceInfo.getFootWearPosition());
        deviceInfo2.setBluetoothVersion(deviceInfo.getBtVersion());
        deviceInfo2.setSoftVersion(deviceInfo.getDeviceSoftVersion());
        deviceInfo2.setNodeId(null);
        deviceInfo2.setCertModel(deviceInfo.getCertMode());
        deviceInfo2.setManufacture(deviceInfo.getDeviceBtMode());
        deviceInfo2.setDeviceUdid(a(deviceInfo2, deviceInfo2.getProductType()));
        deviceInfo2.setLastConnectedTime(deviceInfo.getLastConnectedTime());
        deviceInfo2.setVersionType(deviceInfo.getDeviceVersionType());
        deviceInfo2.setDeviceOtaPackageName(deviceInfo.getDeviceOtaPackageName());
        deviceInfo2.setHiLinkDeviceId(deviceInfo.getDeviceHilinkId());
        deviceInfo2.setPowerSaveModel(deviceInfo.getPowerSaveMode());
        deviceInfo2.setUdidFromDevice(deviceInfo.getDfxDeviceUdid());
        deviceInfo2.setUnConvertedUdid(deviceInfo.getDfxDeviceUdidParameter());
        deviceInfo2.setDeviceFactoryReset(deviceInfo.getDeviceFactoryReset());
        deviceInfo2.setExpandCapability(b(uniteDevice));
        deviceInfo2.setWearEngineDeviceId(deviceInfo.getWearEngineDeviceId());
        deviceInfo2.setSupportAccountSwitch(e(uniteDevice) ? 1 : 0);
        a(deviceInfo2, deviceInfo);
        return deviceInfo2;
    }

    private static void a(DeviceInfo deviceInfo, com.huawei.devicesdk.entity.DeviceInfo deviceInfo2) {
        deviceInfo.setCountryCode(deviceInfo2.getCountryCode());
        deviceInfo.setEmuiVersion(deviceInfo2.getEmuiVersion());
        deviceInfo.setMultiLinkBleMac(deviceInfo2.getMultiLinkBleMac());
        deviceInfo.setMultiLink(deviceInfo2.isMultiLink());
        deviceInfo.setDeviceEnterpriseVersion(deviceInfo2.getDeviceEnterpriseVersion());
        deviceInfo.setDeviceSplicingProductVersion(deviceInfo2.getDeviceSplicingProductVersion());
        deviceInfo.setDeviceOStVersion(deviceInfo2.getDeviceOStVersion());
        deviceInfo.setDeviceOtaSignatureLength(deviceInfo2.getDeviceOtaSignatureLength());
        deviceInfo.setFieldList(deviceInfo2.getFieldList());
        deviceInfo.setDeviceOtaAreaType(deviceInfo2.getDeviceOtaAreaType());
        deviceInfo.setSportVersion(deviceInfo2.getSportVersion());
        deviceInfo.setSaleInfo(deviceInfo2.getSaleInfo());
        deviceInfo.setPsiSignature(deviceInfo2.getPsiSignature());
        deviceInfo.setDeviceSubModelId(deviceInfo2.getDeviceSubProdId());
        deviceInfo.setIsDemoWatch(deviceInfo2.getIsDemoWatch());
        deviceInfo.setSingleFrameDevice(deviceInfo2.getSingleFrameDevice());
    }

    private static String b(UniteDevice uniteDevice) {
        ExternalDeviceCapability capability;
        if (uniteDevice == null || (capability = uniteDevice.getCapability()) == null) {
            return null;
        }
        return capability.getCapacity();
    }

    private static boolean e(UniteDevice uniteDevice) {
        DeviceCapability a2;
        if (uniteDevice == null || (a2 = a(uniteDevice)) == null) {
            return false;
        }
        return a2.isSupportAccountSwitch();
    }

    public static DeviceCapability a(UniteDevice uniteDevice) {
        DeviceCapability deviceCapability = null;
        if (uniteDevice == null) {
            return null;
        }
        ExternalDeviceCapability capability = uniteDevice.getCapability();
        if (capability != null) {
            deviceCapability = capability.getCompatibleCapacity();
            deviceCapability.configureIsSupportHelp(true);
        }
        return deviceCapability == null ? b(uniteDevice.getIdentify()) : deviceCapability;
    }

    public static void d(String str, DeviceCapability deviceCapability) {
        if (TextUtils.isEmpty(str) || deviceCapability == null) {
            return;
        }
        e.put(str, deviceCapability);
    }

    private static DeviceCapability b(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return e.get(str);
    }

    public static Map<String, DeviceCapability> b() {
        return e;
    }

    public static String a(DeviceInfo deviceInfo, int i) {
        String d2;
        if (deviceInfo == null) {
            return null;
        }
        String deviceIdentify = deviceInfo.getDeviceIdentify();
        if (deviceIdentify == null) {
            return deviceIdentify;
        }
        String securityDeviceId = deviceInfo.getSecurityDeviceId();
        Map<String, String> map = c;
        String str = map.containsKey(deviceIdentify) ? map.get(deviceIdentify) : "";
        if (!TextUtils.isEmpty(str) && str.equals(securityDeviceId)) {
            Map<String, String> map2 = f430a;
            if (map2.containsKey(deviceIdentify)) {
                return map2.get(deviceIdentify);
            }
        }
        c();
        String str2 = securityDeviceId + deviceIdentify;
        if (deviceIdentify.equals(securityDeviceId)) {
            str2 = deviceIdentify;
        }
        if (i >= 34 && !snu.e().noCloudVersion()) {
            d2 = bmr.a(str2);
        } else {
            d2 = d(securityDeviceId);
        }
        map.put(deviceIdentify, securityDeviceId);
        f430a.put(deviceIdentify, d2);
        return d2;
    }

    private static void c() {
        if (d == null) {
            d = Boolean.valueOf(SharedPreferenceManager.a(String.valueOf(PrebakedEffectId.RT_FLY), "DeviceConvertIsNoCloudVersion", snu.e().noCloudVersion()));
            SharedPreferenceManager.e(String.valueOf(PrebakedEffectId.RT_FLY), "DeviceConvertIsNoCloudVersion", d.booleanValue());
            SharedPreferenceManager.c(String.valueOf(PrebakedEffectId.RT_FLY), "DeviceConvertUserId", SharedPreferenceManager.e(String.valueOf(PrebakedEffectId.RT_FLY), "DeviceConvertUserId", bmr.a(KeyValDbManager.b(BaseApplication.getContext()).e("user_id"))));
        }
        if (d.booleanValue() != snu.e().noCloudVersion()) {
            d = Boolean.valueOf(snu.e().noCloudVersion());
            SharedPreferenceManager.e(String.valueOf(PrebakedEffectId.RT_FLY), "DeviceConvertIsNoCloudVersion", d.booleanValue());
            String a2 = bmr.a(KeyValDbManager.b(BaseApplication.getContext()).e("user_id"));
            if (!Objects.equals(a2, SharedPreferenceManager.e(String.valueOf(PrebakedEffectId.RT_FLY), "DeviceConvertUserId", ""))) {
                SharedPreferenceManager.c(String.valueOf(PrebakedEffectId.RT_FLY), "DeviceConvertUserId", a2);
                return;
            }
            String str = "getDeviceUdid isNoCloudVersion change: " + d;
            ReleaseLogUtil.a("A", str);
            iyv.c("ConnectMessage", str);
        }
    }

    private static String d(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("DeviceConvertUtil", "setDeviceMac deviceMac is null");
            return null;
        }
        if (!snu.e().noCloudVersion() && (str.contains(":") || str.length() <= 12)) {
            return str.replace(":", "");
        }
        String b = bmr.b(str);
        return b.length() >= 24 ? b.replace(Marker.ANY_NON_NULL_MARKER, "A").replace("/", "A").replace("=", "A").substring(0, 24) : b;
    }
}
