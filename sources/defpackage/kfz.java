package defpackage;

import android.os.Build;
import android.text.TextUtils;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hms.network.embedded.w9;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.phoneprocess.mgr.intelligentlifemgr.ProfileAgent;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.ble.BleConstants;
import com.huawei.operation.utils.Constants;
import com.huawei.profile.client.profile.ProfileClient;
import com.huawei.profile.coordinator.ProfileRequestConstants;
import com.huawei.profile.profile.DeviceProfile;
import com.huawei.profile.profile.ServiceCharacteristicProfile;
import com.huawei.profile.profile.ServiceProfile;
import com.huawei.utils.FoundationCommonUtil;
import health.compact.a.CommonUtil;
import health.compact.a.KeyValDbManager;
import health.compact.a.Sha256;
import java.lang.reflect.InvocationTargetException;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes9.dex */
public class kfz {
    private static final Object c = new Object();
    private static volatile kfz e;
    private String d = "";

    /* renamed from: a, reason: collision with root package name */
    private String f14356a = "";
    private String b = "";

    private kfz() {
    }

    private static String h(String str) {
        try {
            Object invoke = Class.forName("android.os.SystemProperties").getMethod("get", String.class).invoke(null, str);
            if (invoke instanceof String) {
                return (String) invoke;
            }
        } catch (ClassNotFoundException unused) {
            LogUtil.b("HwAccessoryLife", "getAndroidOsSystemProperties ClassNotFoundException");
        } catch (IllegalAccessException unused2) {
            LogUtil.b("HwAccessoryLife", "getAndroidOsSystemProperties IllegalAccessException");
        } catch (NoSuchMethodException unused3) {
            LogUtil.b("HwAccessoryLife", "getAndroidOsSystemProperties NoSuchMethodException");
        } catch (InvocationTargetException unused4) {
            LogUtil.b("HwAccessoryLife", "getAndroidOsSystemProperties InvocationTargetException");
        }
        return "";
    }

    public static kfz a() {
        if (e == null) {
            synchronized (c) {
                if (e == null) {
                    e = new kfz();
                }
            }
        }
        return e;
    }

    public void e(DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            LogUtil.h("HwAccessoryLife", "add device is null");
            return;
        }
        LogUtil.a("HwAccessoryLife", "add device to profile , device is ", deviceInfo, ",HiLinkDeviceId is ", deviceInfo.getHiLinkDeviceId(), ",DeviceModel is ", deviceInfo.getDeviceModel());
        HashMap hashMap = new HashMap(10);
        if (e(hashMap, deviceInfo)) {
            a(deviceInfo, hashMap);
            LogUtil.a("HwAccessoryLife", "add device to profile end");
        } else {
            LogUtil.h("HwAccessoryLife", "add device to profile end , unknown device type");
        }
    }

    private void f(DeviceInfo deviceInfo) {
        LogUtil.a("HwAccessoryLife", "putBtConnectionStatusProfile start");
        HashMap hashMap = new HashMap(16);
        hashMap.put("characteristic.bt.connectionStatus", "0");
        if (c(deviceInfo, w9.h, "bt.connection", hashMap)) {
            i(deviceInfo);
        } else {
            LogUtil.h("HwAccessoryLife", "putServiceCharacteristic error");
        }
    }

    private void j(DeviceInfo deviceInfo) {
        Integer num = kfx.b().get(Integer.valueOf(deviceInfo.getDeviceConnectState()));
        int intValue = num == null ? 0 : num.intValue();
        HashMap hashMap = new HashMap(16);
        hashMap.put("peerConnectStatus", Integer.valueOf(intValue));
        LogUtil.h("HwAccessoryLife", "putDeviceConnectStatusProfile : ", Boolean.valueOf(c(deviceInfo, BleConstants.DEV_INFO, BleConstants.DEV_INFO, hashMap)));
    }

    private boolean c(DeviceInfo deviceInfo, String str, String str2, Map<String, Object> map) {
        String a2 = a(deviceInfo);
        if (b(str, a2)) {
            LogUtil.h("HwAccessoryLife", "serviceId is not exits, put serviceId and character.");
            return b(deviceInfo, str, str2, map, 1);
        }
        ServiceCharacteristicProfile serviceCharacteristics = ProfileAgent.PROFILE_AGENT.getClientAgent().getServiceCharacteristics(a2, str, false, Collections.singletonList(ProfileClient.DEFAULT_TRUST_DOMAIN));
        if (serviceCharacteristics == null) {
            LogUtil.h("HwAccessoryLife", "isNewParamServiceIdSuccess serviceProfile is null.");
            return b(deviceInfo, str, str2, map, 1);
        }
        Map<String, Object> profile = serviceCharacteristics.getProfile();
        if (map == null || map.isEmpty()) {
            LogUtil.h("HwAccessoryLife", "serviceId wrong ,please check : ", str);
            return false;
        }
        LogUtil.c("HwAccessoryLife", "serviceId : ", str, " local : ", profile.toString(), " params : ", map.toString());
        if (d(profile)) {
            LogUtil.a("HwAccessoryLife", "new device, put service.");
            return b(deviceInfo, str, str2, map, 1);
        }
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if (!entry.getValue().equals(profile.get(entry.getKey()))) {
                return b(deviceInfo, str, str2, map, 2);
            }
        }
        return true;
    }

    private boolean b(String str, String str2) {
        List<ServiceProfile> servicesOfDevice = ProfileAgent.PROFILE_AGENT.getClientAgent().getServicesOfDevice(str2, false, Collections.singletonList(ProfileClient.DEFAULT_TRUST_DOMAIN));
        if (servicesOfDevice == null || servicesOfDevice.isEmpty()) {
            LogUtil.h("HwAccessoryLife", "service id not exit, put all.");
            return true;
        }
        Iterator<ServiceProfile> it = servicesOfDevice.iterator();
        while (it.hasNext()) {
            if (str.equalsIgnoreCase(it.next().getId())) {
                return false;
            }
        }
        return true;
    }

    private boolean d(Map<String, Object> map) {
        if (map.isEmpty()) {
            return true;
        }
        Iterator<Map.Entry<String, Object>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Object value = it.next().getValue();
            if (value == null || TextUtils.isEmpty(value.toString())) {
                return true;
            }
        }
        return false;
    }

    private void g(DeviceInfo deviceInfo) {
        for (Map.Entry<String, Integer> entry : kfx.c().entrySet()) {
            String key = entry.getKey();
            HashMap hashMap = new HashMap(16);
            hashMap.put(key, key);
            hashMap.put("ability", Integer.valueOf(a(deviceInfo.getExpandCapability(), entry.getValue().intValue())));
            LogUtil.h("HwAccessoryLife", "putDeviceCapabilityName : ", Boolean.valueOf(c(deviceInfo, "WEAR" + key, "wearCapabilityService", hashMap)));
        }
    }

    private void h(DeviceInfo deviceInfo) {
        LogUtil.a("HwAccessoryLife", "capability : ", deviceInfo.getExpandCapability());
        String multiLinkBleMac = deviceInfo.getMultiLinkBleMac();
        if (TextUtils.isEmpty(multiLinkBleMac)) {
            LogUtil.h("HwAccessoryLife", "wrong , ble mac is wrong, please check 5.1.7 command");
            return;
        }
        String f = f(multiLinkBleMac);
        HashMap hashMap = new HashMap(16);
        hashMap.put("address", f);
        LogUtil.h("HwAccessoryLife", "putDeviceBleLast : ", Boolean.valueOf(c(deviceInfo, "bt", "bt", hashMap)));
    }

    private boolean b(DeviceInfo deviceInfo, String str, String str2, Map<String, Object> map, int i) {
        if ((i & 1) == 1) {
            c(deviceInfo, str, str2);
        }
        return c(a(deviceInfo), str, map);
    }

    private String a(DeviceInfo deviceInfo) {
        if (!TextUtils.isEmpty(deviceInfo.getDeviceUdid())) {
            return deviceInfo.getDeviceUdid();
        }
        return i(deviceInfo.getSecurityUuid());
    }

    private int a(String str, int i) {
        return CommonUtil.a(cvx.a(str), i) ? 1 : 0;
    }

    private void i(DeviceInfo deviceInfo) {
        LogUtil.a("HwAccessoryLife", "putManagerNodeProfile start");
        String c2 = c(e());
        String b = b(i(deviceInfo.getSecurityUuid()), "managerNode", "characteristic.managerIdentifierList");
        HashMap hashMap = new HashMap(16);
        if (g(b)) {
            LogUtil.a("HwAccessoryLife", "putManagerNodeProfile characterValue is empty");
            hashMap.put("characteristic.managerIdentifierList", c2);
        } else if (!b.contains(c2)) {
            hashMap.put("characteristic.managerIdentifierList", b + "," + c2);
        } else {
            LogUtil.h("HwAccessoryLife", "cloud Characteristics has already exists.");
        }
        b(deviceInfo, hashMap);
        LogUtil.a("HwAccessoryLife", "putManagerNodeProfile end : ", Boolean.valueOf(c(deviceInfo, "managerNode", "managerNode", hashMap)));
    }

    private void b(DeviceInfo deviceInfo, Map<String, Object> map) {
        LogUtil.a("HwAccessoryLife", "putManagerNodeNew start");
        String b = b();
        String b2 = b(i(deviceInfo.getSecurityUuid()), "managerNode", "managerIdentifierList2");
        if (g(b2)) {
            LogUtil.a("HwAccessoryLife", "putManagerNodeNew characterValue is empty");
            map.put("managerIdentifierList2", b);
        } else {
            if (!b2.contains(b)) {
                LogUtil.a("HwAccessoryLife", "putManagerNodeNew characterValue not empty");
                map.put("managerIdentifierList2", b2 + "," + b);
                return;
            }
            LogUtil.h("HwAccessoryLife", "putManagerNodeNew cloud Characteristics has already exists characterValue:", b2);
        }
    }

    private String b() {
        String str;
        try {
            str = d();
        } catch (SocketException unused) {
            LogUtil.b("HwAccessoryLife", "getConfusedMac SocketException");
            str = null;
        }
        if (str == null || TextUtils.isEmpty(str)) {
            LogUtil.h("HwAccessoryLife", "getConfusedMac mac is null");
            return "";
        }
        String replace = str.replace(":", "");
        StringBuilder sb = new StringBuilder(replace.length() / 2);
        for (int i = 1; i < replace.length(); i += 2) {
            sb.append(replace.charAt(i));
        }
        LogUtil.a("HwAccessoryLife", "getConfusedMac evenDigitsMac ", sb.toString());
        String e2 = Sha256.e(sb.toString(), "SHA-256");
        LogUtil.a("HwAccessoryLife", "getConfusedMac address:", CommonUtil.l(e2));
        return e2;
    }

    private String d() throws SocketException {
        Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
        String str = null;
        if (networkInterfaces == null) {
            LogUtil.h("HwAccessoryLife", "can not get network interface!");
            return null;
        }
        while (networkInterfaces.hasMoreElements()) {
            NetworkInterface nextElement = networkInterfaces.nextElement();
            byte[] hardwareAddress = nextElement.getHardwareAddress();
            if (hardwareAddress == null || hardwareAddress.length == 0) {
                LogUtil.h("HwAccessoryLife", "macAddress addressBytes is null");
            } else {
                StringBuilder sb = new StringBuilder(16);
                for (byte b : hardwareAddress) {
                    sb.append(String.format(Locale.ROOT, "%02X:", Byte.valueOf(b)));
                }
                if (sb.length() > 0) {
                    sb.deleteCharAt(sb.length() - 1);
                }
                String sb2 = sb.toString();
                if ("wlan0".equals(nextElement.getName())) {
                    LogUtil.a("HwAccessoryLife", "network.getName is wlan0");
                    str = sb2;
                }
            }
        }
        return str;
    }

    private String c(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("HwAccessoryLife", "checkInvalidSerialNumber number isEmpty");
            return " ";
        }
        if (!"unknown".equals(str) && !Constants.NULL.equals(str)) {
            return str;
        }
        LogUtil.h("HwAccessoryLife", "checkInvalidSerialNumber number is null , number:", str);
        return " ";
    }

    private boolean g(String str) {
        if (!Constants.NULL.equals(str) && !"".equals(str)) {
            return false;
        }
        LogUtil.h("HwAccessoryLife", "isEmptyCharacterValue characterValue is null");
        return true;
    }

    public void a(String str) {
        LogUtil.a("HwAccessoryLife", "removeDeviceToProfile start deviceId:", CommonUtil.l(str));
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("HwAccessoryLife", "removeDeviceToProfile deviceId is null");
            return;
        }
        b(str);
        c();
        KeyValDbManager.b(BaseApplication.getContext()).e(str, "");
    }

    private void b(String str) {
        String str2;
        LogUtil.a("HwAccessoryLife", "deleteDeviceById start");
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("HwAccessoryLife", "deleteDeviceById deviceId is null");
            return;
        }
        ProfileClient clientAgent = ProfileAgent.PROFILE_AGENT.getClientAgent();
        List<DeviceProfile> devices = clientAgent.getDevices(true, Arrays.asList(ProfileClient.CLOUD_LOCAL_DOMAIN));
        if (devices == null) {
            LogUtil.h("HwAccessoryLife", "deleteDeviceById deviceProfiles is null");
            return;
        }
        for (DeviceProfile deviceProfile : devices) {
            String id = deviceProfile.getId();
            Map<String, Object> profile = deviceProfile.getProfile();
            if (profile.get("sn") instanceof String) {
                str2 = (String) profile.get("sn");
                LogUtil.a("HwAccessoryLife", "deleteDeviceById serial string:", CommonUtil.l(str2));
            } else {
                str2 = null;
            }
            if (str.equals(id) || (!TextUtils.isEmpty(str2) && str.equals(str2))) {
                e(id);
                this.d = str2;
                if (profile.get("mac") instanceof String) {
                    String str3 = (String) profile.get("mac");
                    this.f14356a = str3;
                    LogUtil.a("HwAccessoryLife", "deleteDeviceById mac:", str3);
                }
                if (profile.get("prodId") instanceof String) {
                    String str4 = (String) profile.get("prodId");
                    this.b = str4;
                    LogUtil.a("HwAccessoryLife", "deleteDeviceById prodId:", str4);
                }
                LogUtil.a("HwAccessoryLife", "deleteDeviceById deleteDevice : ", Boolean.valueOf(clientAgent.deleteDevice(id)));
                return;
            }
        }
    }

    private void c() {
        String str;
        String str2;
        String str3;
        LogUtil.a("HwAccessoryLife", "deleteErrorDeviceInfo mDeleteDeviceMac:", this.f14356a, "; mDeleteDeviceSn:", CommonUtil.l(this.d), "; mDeleteDeviceProdId:", this.b);
        ProfileClient clientAgent = ProfileAgent.PROFILE_AGENT.getClientAgent();
        List<DeviceProfile> devices = clientAgent.getDevices(true, Arrays.asList(ProfileClient.CLOUD_LOCAL_DOMAIN));
        if (devices == null) {
            LogUtil.h("HwAccessoryLife", "deleteErrorDeviceInfo deviceProfiles is null");
            return;
        }
        for (DeviceProfile deviceProfile : devices) {
            String id = deviceProfile.getId();
            Map<String, Object> profile = deviceProfile.getProfile();
            String str4 = null;
            if (profile.get("sn") instanceof String) {
                str = (String) profile.get("sn");
                LogUtil.a("HwAccessoryLife", "deleteErrorDeviceInfo serial string:", CommonUtil.l(str));
            } else {
                str = null;
            }
            if (!TextUtils.isEmpty(str) && (str3 = this.d) != null && str3.equals(str)) {
                e(id);
                LogUtil.a("HwAccessoryLife", "deleteErrorDeviceInfo deleteDevice by sn: ", Boolean.valueOf(clientAgent.deleteDevice(id)));
            } else {
                if (profile.get("prodId") instanceof String) {
                    str4 = (String) profile.get("prodId");
                    LogUtil.a("HwAccessoryLife", "deleteErrorDeviceInfo prodId:", str4);
                }
                if (str4 == null || !TextUtils.equals(this.b, str4)) {
                    LogUtil.a("HwAccessoryLife", "deleteErrorDeviceInfo prodId not equal");
                } else if (str != null && !TextUtils.isEmpty(str) && !str.contains("#ANDROID21")) {
                    String f = f(str);
                    LogUtil.a("HwAccessoryLife", "deleteErrorDeviceInfo wrongSn: ", f);
                    if (!TextUtils.isEmpty(f) && (str2 = this.f14356a) != null && str2.equals(f)) {
                        e(id);
                        LogUtil.a("HwAccessoryLife", "deleteErrorDeviceInfo deleteDevice by wrong sn: ", Boolean.valueOf(clientAgent.deleteDevice(id)));
                    }
                }
            }
        }
        this.d = "";
        this.f14356a = "";
        this.b = "";
    }

    private String b(String str, String str2, String str3) {
        if (!ProfileAgent.PROFILE_AGENT.getConnected()) {
            LogUtil.h("HwAccessoryLife", "getDevices profile has not connect");
            return "";
        }
        ServiceCharacteristicProfile serviceCharacteristics = ProfileAgent.PROFILE_AGENT.getClientAgent().getServiceCharacteristics(str, str2, true, Arrays.asList(ProfileClient.CLOUD_LOCAL_DOMAIN));
        if (serviceCharacteristics == null) {
            LogUtil.h("HwAccessoryLife", "serviceCharacteristicProfiles is null");
            return "";
        }
        return String.valueOf(serviceCharacteristics.getProfile().get(str3));
    }

    private boolean e(Map<String, Object> map, DeviceInfo deviceInfo) {
        if (deviceInfo.getDeviceName() == null) {
            LogUtil.h("HwAccessoryLife", "setBasicProfileInfo getDeviceName is null");
            return false;
        }
        kga b = b(deviceInfo);
        if (b == null) {
            LogUtil.h("HwAccessoryLife", "setBasicProfileInfo unknown device");
            return false;
        }
        LogUtil.a("HwAccessoryLife", "setBasicProfileInfo deviceModeInfo:", b.toString());
        map.put("model", b.a());
        map.put("devType", b.d());
        map.put(ProfileRequestConstants.MANU, "001");
        map.put("prodId", b.c());
        map.put(ProfileRequestConstants.HIV, "1");
        map.put("deviceName", deviceInfo.getDeviceName());
        if (!TextUtils.isEmpty(deviceInfo.getDeviceUdid())) {
            LogUtil.a("HwAccessoryLife", "getDeviceUdid is not null");
            map.put("udid", deviceInfo.getDeviceUdid());
        } else {
            map.put("udid", i(deviceInfo.getSecurityUuid()));
        }
        map.put("sn", i(deviceInfo.getSecurityUuid()));
        String f = f(deviceInfo.getDeviceIdentify());
        if (TextUtils.isEmpty(f)) {
            LogUtil.a("HwAccessoryLife", "setBasicProfileInfo() subMac is null");
            return false;
        }
        map.put("mac", f);
        map.put(ProfileRequestConstants.PROT_TYPE, 4);
        return true;
    }

    private String f(String str) {
        String replaceAll = str.replaceAll(":", "");
        int length = replaceAll.length();
        if (length > 3) {
            return replaceAll.substring(length - 3, length);
        }
        return null;
    }

    private kga b(DeviceInfo deviceInfo) {
        int productType = deviceInfo.getProductType();
        LogUtil.a("HwAccessoryLife", "getDeviceModeInfo () type:", Integer.valueOf(productType), " deviceNameKey:", deviceInfo.getDeviceName());
        return d(deviceInfo);
    }

    private kga d(DeviceInfo deviceInfo) {
        String hiLinkDeviceId = deviceInfo.getHiLinkDeviceId();
        String deviceModel = deviceInfo.getDeviceModel();
        if (TextUtils.isEmpty(hiLinkDeviceId) || TextUtils.isEmpty(deviceModel)) {
            LogUtil.h("HwAccessoryLife", "getModelInfoFromDeviceInfo() not get DeviceId and DeviceMode");
            return null;
        }
        if (hiLinkDeviceId.length() != 4) {
            LogUtil.h("HwAccessoryLife", "getModelInfoFromDeviceInfo() hiLinkDeviceId is Illegal");
            return null;
        }
        String c2 = c(deviceInfo);
        LogUtil.a("HwAccessoryLife", "getModelInfoFromDeviceInfo() not get DeviceId and curDeviceType: ", c2, hiLinkDeviceId, deviceModel);
        return new kga(deviceModel, hiLinkDeviceId, c2);
    }

    private void a(DeviceInfo deviceInfo, Map<String, Object> map) {
        if (!ProfileAgent.PROFILE_AGENT.getConnected()) {
            LogUtil.h("HwAccessoryLife", "putDeviceProfile profile not connected");
            return;
        }
        boolean z = true;
        if (b(a(deviceInfo), map)) {
            LogUtil.a("HwAccessoryLife", "new profile, need put device.");
            DeviceProfile deviceProfile = new DeviceProfile();
            deviceProfile.setId(a(deviceInfo));
            deviceProfile.setIsNeedCloud(true);
            if (map.get("devType") instanceof String) {
                deviceProfile.setType((String) map.get("devType"));
            }
            deviceProfile.addEntities(map);
            z = ProfileAgent.PROFILE_AGENT.getClientAgent().putDevice(deviceProfile);
        } else {
            LogUtil.h("HwAccessoryLife", "profile put device age. no need put age.");
        }
        LogUtil.a("HwAccessoryLife", "putDevice is success: ", Boolean.valueOf(z));
        if (z) {
            f(deviceInfo);
            j(deviceInfo);
            h(deviceInfo);
            g(deviceInfo);
            d(a(deviceInfo));
            return;
        }
        LogUtil.h("HwAccessoryLife", "putDeviceProfile error");
    }

    private boolean b(String str, Map<String, Object> map) {
        List<DeviceProfile> devicesById = ProfileAgent.PROFILE_AGENT.getClientAgent().getDevicesById(str, false, Collections.singletonList(ProfileClient.DEFAULT_TRUST_DOMAIN));
        if (devicesById == null || devicesById.isEmpty()) {
            LogUtil.h("HwAccessoryLife", "new device profile, need put device.");
            return true;
        }
        for (DeviceProfile deviceProfile : devicesById) {
            LogUtil.c("HwAccessoryLife", "test ", str, " deviceProfile : ", devicesById.toString());
            if (!a(map, deviceProfile.getProfile())) {
                return true;
            }
        }
        return false;
    }

    private boolean a(Map<String, Object> map, Map<String, Object> map2) {
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            if (!entry.getValue().equals(map2.get(key)) && !ProfileRequestConstants.HIV.equalsIgnoreCase(key)) {
                return false;
            }
        }
        return true;
    }

    private void c(DeviceInfo deviceInfo, String str, String str2) {
        if (!ProfileAgent.PROFILE_AGENT.getConnected()) {
            LogUtil.h("HwAccessoryLife", "putDeviceService profile not connected");
            return;
        }
        String a2 = a(deviceInfo);
        ServiceProfile serviceProfile = new ServiceProfile();
        serviceProfile.setDeviceId(a2);
        serviceProfile.setIsNeedCloud(true);
        serviceProfile.setId(str);
        serviceProfile.setType(str2);
        HashMap hashMap = new HashMap(10);
        hashMap.put("serviceId", str);
        hashMap.put("type", str2);
        serviceProfile.addEntities(hashMap);
        LogUtil.a("HwAccessoryLife", "putDeviceService is ", Boolean.valueOf(ProfileAgent.PROFILE_AGENT.getClientAgent().putServiceOfDevice(a2, serviceProfile)));
    }

    private boolean c(String str, String str2, Map<String, Object> map) {
        if (!ProfileAgent.PROFILE_AGENT.getConnected()) {
            LogUtil.h("HwAccessoryLife", "putServiceCharacteristicAddEntities profile not connected");
            return false;
        }
        ServiceCharacteristicProfile serviceCharacteristicProfile = new ServiceCharacteristicProfile();
        serviceCharacteristicProfile.addEntities(map);
        serviceCharacteristicProfile.addEntityInfo("timestamp", Long.valueOf(System.currentTimeMillis()));
        serviceCharacteristicProfile.setIsNeedCloud(true);
        LogUtil.c("HwAccessoryLife", "putServiceCharacteristicAddEntities serviceCharacteristicProfile ", serviceCharacteristicProfile.toString());
        boolean putServiceCharacteristic = ProfileAgent.PROFILE_AGENT.getClientAgent().putServiceCharacteristic(str, str2, serviceCharacteristicProfile);
        LogUtil.a("HwAccessoryLife", "putServiceCharacteristicAddEntities put isSuccess:", Boolean.valueOf(putServiceCharacteristic));
        return putServiceCharacteristic;
    }

    private String e() {
        if (Build.VERSION.SDK_INT >= 29) {
            String androidId = FoundationCommonUtil.getAndroidId(BaseApplication.getContext());
            if (!TextUtils.isEmpty(androidId)) {
                return androidId.toUpperCase(Locale.ENGLISH);
            }
            LogUtil.b("HwAccessoryLife", "androidId is isEmpty");
        }
        if (BaseApplication.getContext().getPackageManager().checkPermission("android.permission.READ_PHONE_STATE", BaseApplication.getContext().getPackageName()) == 0) {
            LogUtil.a("HwAccessoryLife", "getSerialNumber version > VERSION_CODES.0");
            return CommonUtil.g();
        }
        LogUtil.a("HwAccessoryLife", "getSerialNumber version < VERSION_CODES.0");
        return h("ro.boot.serialno");
    }

    private void e(String str) {
        ProfileClient clientAgent = ProfileAgent.PROFILE_AGENT.getClientAgent();
        List<ServiceProfile> servicesOfDevice = clientAgent.getServicesOfDevice(str, true, Arrays.asList(ProfileClient.CLOUD_LOCAL_DOMAIN));
        if (servicesOfDevice == null || servicesOfDevice.isEmpty()) {
            LogUtil.h("HwAccessoryLife", "deleteAllServices deleteDevice success:", Boolean.valueOf(clientAgent.deleteDevice(str)));
            return;
        }
        Iterator<ServiceProfile> it = servicesOfDevice.iterator();
        while (it.hasNext()) {
            String id = it.next().getId();
            LogUtil.a("HwAccessoryLife", "deleteAllServices serviceId:", id);
            c(str, id);
            LogUtil.a("HwAccessoryLife", "deleteAllServices deleteDevice success:", Boolean.valueOf(clientAgent.deleteServiceOfDevice(str, id)));
        }
    }

    private void c(String str, String str2) {
        ProfileClient clientAgent = ProfileAgent.PROFILE_AGENT.getClientAgent();
        ServiceCharacteristicProfile serviceCharacteristics = clientAgent.getServiceCharacteristics(str, str2, true, Arrays.asList(ProfileClient.CLOUD_LOCAL_DOMAIN));
        if (serviceCharacteristics == null) {
            LogUtil.h("HwAccessoryLife", "characteristicProfile is empty");
            return;
        }
        Map<String, Object> profile = serviceCharacteristics.getProfile();
        if (profile.isEmpty()) {
            LogUtil.h("HwAccessoryLife", "deleteAllCharacter profile map is empty");
            return;
        }
        Iterator<Map.Entry<String, Object>> it = profile.entrySet().iterator();
        while (it.hasNext()) {
            String key = it.next().getKey();
            LogUtil.c("HwAccessoryLife", " deleteAllCharacter character key:", key);
            LogUtil.c("HwAccessoryLife", " deleteAllCharacter is success:", Boolean.valueOf(clientAgent.deleteServiceCharacteristic(str, str2, key)));
        }
    }

    private String i(String str) {
        return str + "#ANDROID21";
    }

    public void d(String str) {
        String j = j(str);
        LogUtil.a("HwAccessoryLife", "putWiseDeviceId wiseDeviceId:", CommonUtil.l(j));
        KeyValDbManager.b(BaseApplication.getContext()).e(str, j);
    }

    private String j(String str) {
        List<DeviceProfile> devices = ProfileAgent.PROFILE_AGENT.getClientAgent().getDevices(true, Arrays.asList(ProfileClient.CLOUD_LOCAL_DOMAIN));
        if (devices == null) {
            LogUtil.h("HwAccessoryLife", "getWiseDeviceIdByDeviceId deviceProfiles is null");
            return "";
        }
        for (DeviceProfile deviceProfile : devices) {
            if (str.equalsIgnoreCase(deviceProfile.getId())) {
                Map<String, Object> profile = deviceProfile.getProfile();
                if (!(profile.get("mac") instanceof String)) {
                    LogUtil.h("HwAccessoryLife", "getWiseDeviceIdByDeviceId mac not instanceof String");
                } else if (TextUtils.isEmpty((String) profile.get("mac"))) {
                    LogUtil.h("HwAccessoryLife", "getWiseDeviceIdByDeviceId mac is empty");
                } else if (!(profile.get("wiseDeviceId") instanceof String)) {
                    LogUtil.h("HwAccessoryLife", "getWiseDeviceIdByDeviceId wiseDeviceId not instanceof String");
                } else {
                    String str2 = (String) profile.get("wiseDeviceId");
                    if (TextUtils.isEmpty(str2) || Constants.NULL.equals(str2)) {
                        LogUtil.h("HwAccessoryLife", "getWiseDeviceIdByDeviceId wiseDeviceId is empty");
                    } else {
                        LogUtil.a("HwAccessoryLife", "getWiseDeviceIdByDeviceId wiseDeviceId not empty");
                        return str2;
                    }
                }
            }
        }
        return "";
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0059 A[ORIG_RETURN, RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.String c(com.huawei.health.devicemgr.business.entity.DeviceInfo r5) {
        /*
            r4 = this;
            int r0 = r5.getProductType()
            java.lang.String r0 = defpackage.juu.d(r0)
            java.lang.String r1 = "getModelInfoFromDeviceInfo uuid："
            java.lang.Object[] r1 = new java.lang.Object[]{r1, r0}
            java.lang.String r2 = "HwAccessoryLife"
            com.huawei.hwlogsmodel.LogUtil.h(r2, r1)
            jux r0 = defpackage.juu.d(r0)
            if (r0 == 0) goto L43
            int r1 = r0.a()
            r3 = -1
            if (r1 == r3) goto L43
            int r5 = r0.a()
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            java.lang.String r1 = "getModelInfoFromDeviceInfo DeviceCategory():"
            java.lang.Object[] r5 = new java.lang.Object[]{r1, r5}
            com.huawei.hwlogsmodel.LogUtil.h(r2, r5)
            int r5 = r0.a()
            r1 = 1
            if (r5 != r1) goto L39
            goto L56
        L39:
            int r5 = r0.a()
            r0 = 6
            if (r5 != r0) goto L59
            java.lang.String r5 = "A12"
            goto L5b
        L43:
            java.lang.String r0 = "getModelInfoFromDeviceInfo deviceInfoNew is null"
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            com.huawei.hwlogsmodel.LogUtil.h(r2, r0)
            int r5 = r5.getProductType()
            boolean r5 = defpackage.juu.j(r5)
            if (r5 == 0) goto L59
        L56:
            java.lang.String r5 = "06E"
            goto L5b
        L59:
            java.lang.String r5 = "06D"
        L5b:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.kfz.c(com.huawei.health.devicemgr.business.entity.DeviceInfo):java.lang.String");
    }
}
