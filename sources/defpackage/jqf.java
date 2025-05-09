package defpackage;

import android.os.Build;
import android.text.TextUtils;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hms.network.embedded.w9;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.outofprocess.mgr.device.ProfileAgent;
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
import health.compact.a.EnvironmentUtils;
import health.compact.a.KeyValDbManager;
import health.compact.a.Services;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes5.dex */
public class jqf {
    private static AtomicInteger e = new AtomicInteger();

    /* renamed from: a, reason: collision with root package name */
    private static List<AtomicBoolean> f14023a = Collections.synchronizedList(new ArrayList());
    private String d = "";
    private String c = "";
    private String b = "";

    public static boolean c() {
        return e.get() != 0;
    }

    public static AtomicBoolean d() {
        AtomicBoolean atomicBoolean = new AtomicBoolean(c());
        f14023a.add(atomicBoolean);
        return atomicBoolean;
    }

    public static void a(AtomicBoolean atomicBoolean) {
        f14023a.remove(atomicBoolean);
    }

    private static void f() {
        Iterator it = new ArrayList(f14023a).iterator();
        while (it.hasNext()) {
            ((AtomicBoolean) it.next()).set(true);
        }
    }

    public void d(DeviceInfo deviceInfo, String str) {
        if (deviceInfo == null) {
            LogUtil.h("IntellLife", "add device is null");
            return;
        }
        if (jpo.c(deviceInfo.getDeviceIdentify()) == 2) {
            LogUtil.h("IntellLife", "not support student mode to cloud");
            return;
        }
        if (d(deviceInfo)) {
            LogUtil.h("IntellLife", "remove device");
            return;
        }
        LogUtil.a("IntellLife", "add device to profile , deviceName is ", deviceInfo.getDeviceName(), " deviceIdType is ", Integer.valueOf(deviceInfo.getDeviceIdType()), " productType is ", Integer.valueOf(deviceInfo.getProductType()), " mSoftVersion is ", deviceInfo.getSoftVersion());
        HashMap hashMap = new HashMap(10);
        if (e(hashMap, deviceInfo)) {
            d(deviceInfo, hashMap, str);
            ReleaseLogUtil.e("DEVMGR_IntellLife", "add device to profile end");
        } else {
            ReleaseLogUtil.d("DEVMGR_IntellLife", "add device to profile end , unknown device type");
        }
    }

    private boolean d(DeviceInfo deviceInfo) {
        if (deviceInfo.getDeviceConnectState() == 2) {
            return false;
        }
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.ALL_DEVICES, null, "IntellLife");
        if (deviceList != null && !deviceList.isEmpty()) {
            String deviceIdentify = deviceInfo.getDeviceIdentify();
            if (TextUtils.isEmpty(deviceIdentify)) {
                return true;
            }
            Iterator<DeviceInfo> it = deviceList.iterator();
            while (it.hasNext()) {
                if (deviceIdentify.equalsIgnoreCase(it.next().getDeviceIdentify())) {
                    return false;
                }
            }
        }
        return true;
    }

    private void b(DeviceInfo deviceInfo, String str) {
        LogUtil.a("IntellLife", "putBtConnectionStatusProfile start");
        HashMap hashMap = new HashMap(16);
        hashMap.put("characteristic.bt.connectionStatus", "0");
        if (b(deviceInfo, w9.h, "bt.connection", hashMap)) {
            a(deviceInfo, str);
        } else {
            ReleaseLogUtil.d("DEVMGR_IntellLife", "putServiceCharacteristic error");
        }
    }

    private void f(DeviceInfo deviceInfo) {
        Integer num = jnv.d().get(Integer.valueOf(deviceInfo.getDeviceConnectState()));
        int intValue = num == null ? 0 : num.intValue();
        LogUtil.a("IntellLife", "putDeviceConnectStatusProfile state:", Integer.valueOf(intValue));
        HashMap hashMap = new HashMap(16);
        hashMap.put("peerConnectStatus", Integer.valueOf(intValue));
        ReleaseLogUtil.e("DEVMGR_IntellLife", "putDeviceConnectStatusProfile : ", Boolean.valueOf(b(deviceInfo, BleConstants.DEV_INFO, BleConstants.DEV_INFO, hashMap)));
    }

    private void n(DeviceInfo deviceInfo) {
        LogUtil.a("IntellLife", "putWearCapabilityProfile start");
        HashMap hashMap = new HashMap(16);
        hashMap.put("digitalTherapy128", Boolean.valueOf(cwi.c(deviceInfo, 128)));
        ReleaseLogUtil.e("DEVMGR_IntellLife", "putWearCapabilityProfile : ", Boolean.valueOf(b(deviceInfo, "wearCapabilityList", "wearCapabilityService", hashMap)));
    }

    private boolean b(DeviceInfo deviceInfo, String str, String str2, Map<String, Object> map) {
        String a2 = a(deviceInfo);
        if (d(str, a2)) {
            LogUtil.h("IntellLife", "serviceId is not exits, put serviceId and character.");
            return b(deviceInfo, str, str2, map, 1);
        }
        ServiceCharacteristicProfile serviceCharacteristics = ProfileAgent.PROFILE_AGENT.getClientAgent().getServiceCharacteristics(a2, str, false, Collections.singletonList(ProfileClient.DEFAULT_TRUST_DOMAIN));
        if (serviceCharacteristics == null) {
            LogUtil.h("IntellLife", "isNewParamServiceIdSuccess serviceProfile is null.");
            return b(deviceInfo, str, str2, map, 1);
        }
        Map<String, Object> profile = serviceCharacteristics.getProfile();
        if (map == null || map.isEmpty()) {
            LogUtil.h("IntellLife", "serviceId wrong ,please check : ", str);
            return false;
        }
        LogUtil.c("IntellLife", "serviceId : ", str, " local : ", profile.toString(), " params : ", map.toString());
        if (c(profile)) {
            LogUtil.a("IntellLife", "new device, put service.");
            return b(deviceInfo, str, str2, map, 1);
        }
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if (!entry.getValue().equals(profile.get(entry.getKey()))) {
                return b(deviceInfo, str, str2, map, 2);
            }
        }
        return true;
    }

    private boolean d(String str, String str2) {
        List<ServiceProfile> servicesOfDevice = ProfileAgent.PROFILE_AGENT.getClientAgent().getServicesOfDevice(str2, false, Collections.singletonList(ProfileClient.DEFAULT_TRUST_DOMAIN));
        if (servicesOfDevice == null || servicesOfDevice.isEmpty()) {
            LogUtil.h("IntellLife", "service id not exit, put all.");
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

    private boolean c(Map<String, Object> map) {
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

    private void i(DeviceInfo deviceInfo) {
        for (Map.Entry<String, Integer> entry : jnv.b().entrySet()) {
            String key = entry.getKey();
            HashMap hashMap = new HashMap(16);
            hashMap.put(key, key);
            hashMap.put("ability", Integer.valueOf(b(deviceInfo.getExpandCapability(), entry.getValue().intValue())));
            LogUtil.h("IntellLife", "putDeviceCapabilityName : ", Boolean.valueOf(b(deviceInfo, "WEAR" + key, "wearCapabilityService", hashMap)));
        }
    }

    private void j(DeviceInfo deviceInfo) {
        LogUtil.a("IntellLife", "capability : ", deviceInfo.getExpandCapability());
        String multiLinkBleMac = deviceInfo.getMultiLinkBleMac();
        if (multiLinkBleMac == null || multiLinkBleMac.isEmpty()) {
            LogUtil.h("IntellLife", "wrong , ble mac is wrong, please check 5.1.7 command");
            return;
        }
        String i = i(multiLinkBleMac);
        HashMap hashMap = new HashMap(16);
        hashMap.put("address", i);
        ReleaseLogUtil.e("DEVMGR_IntellLife", "putDeviceBleLast : ", Boolean.valueOf(b(deviceInfo, "bt", "bt", hashMap)));
    }

    private boolean b(DeviceInfo deviceInfo, String str, String str2, Map<String, Object> map, int i) {
        if ((i & 1) == 1) {
            e(deviceInfo, str, str2);
        }
        return e(a(deviceInfo), str, map);
    }

    private String a(DeviceInfo deviceInfo) {
        if (!TextUtils.isEmpty(deviceInfo.getDeviceUdid())) {
            return deviceInfo.getDeviceUdid();
        }
        return b(deviceInfo.getSecurityUuid());
    }

    private int b(String str, int i) {
        return CommonUtil.a(cvx.a(str), i) ? 1 : 0;
    }

    private void a(DeviceInfo deviceInfo, String str) {
        ReleaseLogUtil.e("DEVMGR_IntellLife", "putManagerNodeProfile start");
        String d = d(g());
        String a2 = a(b(deviceInfo.getSecurityUuid()), "managerNode", "characteristic.managerIdentifierList");
        HashMap hashMap = new HashMap(16);
        if (h(a2)) {
            ReleaseLogUtil.e("DEVMGR_IntellLife", "putManagerNodeProfile characterValue is empty");
            hashMap.put("characteristic.managerIdentifierList", d);
        } else if (!a2.contains(d)) {
            hashMap.put("characteristic.managerIdentifierList", a2 + "," + d);
        } else {
            ReleaseLogUtil.e("DEVMGR_IntellLife", "cloud Characteristics has already exists.");
        }
        e(deviceInfo, hashMap, str);
        ReleaseLogUtil.e("DEVMGR_IntellLife", "putManagerNodeProfile end : ", Boolean.valueOf(b(deviceInfo, "managerNode", "managerNode", hashMap)));
    }

    private void e(DeviceInfo deviceInfo, Map<String, Object> map, String str) {
        LogUtil.a("IntellLife", "putManagerNodeNew start");
        String a2 = a(b(deviceInfo.getSecurityUuid()), "managerNode", "managerIdentifierList2");
        if (h(a2)) {
            LogUtil.a("IntellLife", "putManagerNodeNew characterValue is empty");
            map.put("managerIdentifierList2", str);
        } else {
            if (!a2.contains(str)) {
                LogUtil.a("IntellLife", "putManagerNodeNew characterValue not empty");
                map.put("managerIdentifierList2", a2 + "," + str);
                return;
            }
            LogUtil.h("IntellLife", "putManagerNodeNew cloud Characteristics has already exists characterValue:", a2);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:6:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.String a() {
        /*
            r5 = this;
            java.lang.String r0 = "IntellLife"
            java.lang.String r1 = r5.i()     // Catch: java.lang.Exception -> L7 java.net.SocketException -> L16
            goto L20
        L7:
            r1 = move-exception
            java.lang.String r2 = "getConfusedMac exception："
            java.lang.String r1 = r1.getMessage()
            java.lang.Object[] r1 = new java.lang.Object[]{r2, r1}
            com.huawei.hwlogsmodel.LogUtil.b(r0, r1)
            goto L1f
        L16:
            java.lang.String r1 = "getConfusedMac SocketException"
            java.lang.Object[] r1 = new java.lang.Object[]{r1}
            com.huawei.hwlogsmodel.LogUtil.b(r0, r1)
        L1f:
            r1 = 0
        L20:
            java.lang.String r2 = ""
            if (r1 == 0) goto L72
            boolean r3 = android.text.TextUtils.isEmpty(r1)
            if (r3 == 0) goto L2b
            goto L72
        L2b:
            java.lang.String r3 = ":"
            java.lang.String r1 = r1.replace(r3, r2)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            int r3 = r1.length()
            int r3 = r3 / 2
            r2.<init>(r3)
            r3 = 1
        L3d:
            int r4 = r1.length()
            if (r3 >= r4) goto L4d
            char r4 = r1.charAt(r3)
            r2.append(r4)
            int r3 = r3 + 2
            goto L3d
        L4d:
            java.lang.String r1 = "getConfusedMac evenDigitsMac "
            java.lang.String r3 = r2.toString()
            java.lang.Object[] r1 = new java.lang.Object[]{r1, r3}
            com.huawei.hwlogsmodel.LogUtil.a(r0, r1)
            java.lang.String r1 = r2.toString()
            java.lang.String r2 = "SHA-256"
            java.lang.String r1 = health.compact.a.Sha256.e(r1, r2)
            java.lang.String r2 = "getConfusedMac address:"
            java.lang.String r3 = health.compact.a.CommonUtil.l(r1)
            java.lang.Object[] r2 = new java.lang.Object[]{r2, r3}
            com.huawei.hwlogsmodel.LogUtil.a(r0, r2)
            return r1
        L72:
            java.lang.String r1 = "getConfusedMac mac is null"
            java.lang.Object[] r1 = new java.lang.Object[]{r1}
            com.huawei.hwlogsmodel.LogUtil.h(r0, r1)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.jqf.a():java.lang.String");
    }

    private String i() throws SocketException {
        Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
        String str = null;
        if (networkInterfaces == null) {
            LogUtil.h("IntellLife", "can not get network interface!");
            return null;
        }
        while (networkInterfaces.hasMoreElements()) {
            NetworkInterface nextElement = networkInterfaces.nextElement();
            byte[] hardwareAddress = nextElement.getHardwareAddress();
            if (hardwareAddress == null || hardwareAddress.length == 0) {
                LogUtil.h("IntellLife", "macAddress addressBytes is null");
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
                    LogUtil.a("IntellLife", "network.getName is wlan0");
                    str = sb2;
                }
            }
        }
        return str;
    }

    private String d(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("IntellLife", "checkInvalidSerialNumber number isEmpty");
            return " ";
        }
        if (!"unknown".equals(str) && !Constants.NULL.equals(str)) {
            return str;
        }
        LogUtil.h("IntellLife", "checkInvalidSerialNumber number is null , number:", str);
        return " ";
    }

    private boolean h(String str) {
        if (!Constants.NULL.equals(str) && !"".equals(str)) {
            return false;
        }
        LogUtil.h("IntellLife", "isEmptyCharacterValue characterValue is null");
        return true;
    }

    public void a(String str) {
        LogUtil.a("IntellLife", "removeDeviceToProfile start deviceId:", CommonUtil.l(str));
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("IntellLife", "removeDeviceToProfile deviceId is null");
            return;
        }
        try {
            e.getAndIncrement();
            f();
            c(str);
            b();
            KeyValDbManager.b(BaseApplication.getContext()).e(str, "");
        } finally {
            e.getAndDecrement();
        }
    }

    private void c(String str) {
        String str2;
        LogUtil.a("IntellLife", "deleteDeviceById start");
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("IntellLife", "deleteDeviceById deviceId is null");
            return;
        }
        ProfileClient clientAgent = ProfileAgent.PROFILE_AGENT.getClientAgent();
        List<DeviceProfile> devices = clientAgent.getDevices(true, Arrays.asList(ProfileClient.CLOUD_LOCAL_DOMAIN));
        if (devices == null) {
            LogUtil.h("IntellLife", "deleteDeviceById deviceProfiles is null");
            return;
        }
        for (DeviceProfile deviceProfile : devices) {
            String id = deviceProfile.getId();
            Map<String, Object> profile = deviceProfile.getProfile();
            if (profile.get("sn") instanceof String) {
                str2 = (String) profile.get("sn");
                LogUtil.a("IntellLife", "deleteDeviceById serial string:", CommonUtil.l(str2));
            } else {
                str2 = null;
            }
            if (str.equals(id) || (!TextUtils.isEmpty(str2) && str.equals(str2))) {
                e(id);
                this.d = str2;
                if (profile.get("mac") instanceof String) {
                    String str3 = (String) profile.get("mac");
                    this.c = str3;
                    LogUtil.a("IntellLife", "deleteDeviceById mac:", str3);
                }
                if (profile.get("prodId") instanceof String) {
                    String str4 = (String) profile.get("prodId");
                    this.b = str4;
                    LogUtil.a("IntellLife", "deleteDeviceById prodId:", str4);
                }
                boolean deleteDevice = clientAgent.deleteDevice(id);
                LogUtil.a("IntellLife", "deleteDeviceById deleteDevice : ", Boolean.valueOf(deleteDevice));
                e(deleteDevice);
                return;
            }
        }
    }

    private void b() {
        String str;
        String str2;
        String str3;
        LogUtil.a("IntellLife", "deleteErrorDeviceInfo mDeleteDeviceMac:", this.c, "; mDeleteDeviceSn:", CommonUtil.l(this.d), "; mDeleteDeviceProdId:", this.b);
        ProfileClient clientAgent = ProfileAgent.PROFILE_AGENT.getClientAgent();
        List<DeviceProfile> devices = clientAgent.getDevices(true, Arrays.asList(ProfileClient.CLOUD_LOCAL_DOMAIN));
        if (devices == null) {
            LogUtil.h("IntellLife", "deleteErrorDeviceInfo deviceProfiles is null");
            return;
        }
        for (DeviceProfile deviceProfile : devices) {
            String id = deviceProfile.getId();
            Map<String, Object> profile = deviceProfile.getProfile();
            String str4 = null;
            if (profile.get("sn") instanceof String) {
                str = (String) profile.get("sn");
                LogUtil.a("IntellLife", "deleteErrorDeviceInfo serial string:", CommonUtil.l(str));
            } else {
                str = null;
            }
            if (!TextUtils.isEmpty(str) && (str3 = this.d) != null && str3.equals(str)) {
                e(id);
                boolean deleteDevice = clientAgent.deleteDevice(id);
                LogUtil.a("IntellLife", "deleteErrorDeviceInfo deleteDevice by sn: ", Boolean.valueOf(deleteDevice));
                e(deleteDevice);
            } else {
                if (profile.get("prodId") instanceof String) {
                    str4 = (String) profile.get("prodId");
                    LogUtil.a("IntellLife", "deleteErrorDeviceInfo prodId:", str4);
                }
                if (str4 == null || !TextUtils.equals(this.b, str4)) {
                    LogUtil.a("IntellLife", "deleteErrorDeviceInfo prodId not equal");
                } else if (str != null && !TextUtils.isEmpty(str) && !str.contains("#ANDROID21")) {
                    String i = i(str);
                    LogUtil.a("IntellLife", "deleteErrorDeviceInfo wrongSn: ", i);
                    if (!TextUtils.isEmpty(i) && (str2 = this.c) != null && str2.equals(i)) {
                        e(id);
                        boolean deleteDevice2 = clientAgent.deleteDevice(id);
                        LogUtil.a("IntellLife", "deleteErrorDeviceInfo deleteDevice by wrong sn: ", Boolean.valueOf(deleteDevice2));
                        e(deleteDevice2);
                    }
                }
            }
        }
        this.d = "";
        this.c = "";
        this.b = "";
    }

    private String a(String str, String str2, String str3) {
        if (!ProfileAgent.PROFILE_AGENT.getConnected()) {
            LogUtil.h("IntellLife", "getDevices profile has not connect");
            return "";
        }
        ServiceCharacteristicProfile serviceCharacteristics = ProfileAgent.PROFILE_AGENT.getClientAgent().getServiceCharacteristics(str, str2, true, Arrays.asList(ProfileClient.CLOUD_LOCAL_DOMAIN));
        if (serviceCharacteristics == null) {
            LogUtil.h("IntellLife", "serviceCharacteristicProfiles is null");
            return "";
        }
        return String.valueOf(serviceCharacteristics.getProfile().get(str3));
    }

    private boolean e(Map<String, Object> map, DeviceInfo deviceInfo) {
        if (deviceInfo.getDeviceName() == null) {
            LogUtil.h("IntellLife", "setBasicProfileInfo getDeviceName is null");
            return false;
        }
        jny e2 = e(deviceInfo);
        if (e2 == null) {
            LogUtil.h("IntellLife", "setBasicProfileInfo unknown device");
            return false;
        }
        LogUtil.a("IntellLife", "setBasicProfileInfo deviceModeInfo:", e2.toString());
        map.put("model", c(deviceInfo, e2));
        map.put("devType", e2.a());
        b(map, deviceInfo);
        map.put("prodId", e2.d());
        map.put(ProfileRequestConstants.SWV, deviceInfo.getSoftVersion());
        LogUtil.a("IntellLife", " deviceModeInfo SoftVersion:", deviceInfo.getSoftVersion());
        map.put(ProfileRequestConstants.HIV, "1");
        map.put("deviceName", deviceInfo.getDeviceName());
        if (!TextUtils.isEmpty(deviceInfo.getDeviceUdid())) {
            LogUtil.a("IntellLife", "getDeviceUdid is not null");
            map.put("udid", deviceInfo.getDeviceUdid());
        } else {
            map.put("udid", b(deviceInfo.getSecurityUuid()));
        }
        Object obj = map.get("udid");
        if (obj instanceof String) {
            LogUtil.a("IntellLife", " deviceModeInfo udid:", CommonUtil.l((String) obj));
        }
        String b = b(deviceInfo);
        if (!TextUtils.isEmpty(b)) {
            map.put("sn", b);
        }
        String deviceIdentify = deviceInfo.getDeviceIdentify();
        if (TextUtils.isEmpty(deviceIdentify)) {
            LogUtil.a("IntellLife", "setBasicProfileInfo() subMac is null");
            return false;
        }
        map.put("mac", deviceIdentify);
        map.put(ProfileRequestConstants.PROT_TYPE, 4);
        if (!TextUtils.isEmpty(e2.c())) {
            map.put(ProfileRequestConstants.INTERNAL_MODEL, e2.c());
        }
        if (TextUtils.isEmpty(deviceInfo.getDeviceSubModelId())) {
            return true;
        }
        LogUtil.a("IntellLife", "getDeviceSubModelId is:", deviceInfo.getDeviceSubModelId());
        map.put(ProfileRequestConstants.SUB_PROD_ID, deviceInfo.getDeviceSubModelId());
        return true;
    }

    private String b(DeviceInfo deviceInfo) {
        int deviceIdType = deviceInfo.getDeviceIdType();
        int productType = deviceInfo.getProductType();
        if (deviceIdType != 1 && productType != 10) {
            if (productType < 72) {
                return "";
            }
            return cvx.e(deviceInfo.getUuid()) + "#ANDROID21";
        }
        if (deviceIdType == 1) {
            return cvx.e(deviceInfo.getUuid()) + "#ANDROID21";
        }
        return deviceInfo.getUuid() + "#ANDROID21";
    }

    private String c(DeviceInfo deviceInfo, jny jnyVar) {
        String certModel = deviceInfo.getCertModel();
        if (!TextUtils.isEmpty(certModel) && deviceInfo.getProductType() != 75) {
            return certModel;
        }
        LogUtil.a("IntellLife", "getDeviceModel, deviceModel is empty or device is bolt");
        return jnyVar.c();
    }

    private String i(String str) {
        String replaceAll = str.replaceAll(":", "");
        int length = replaceAll.length();
        if (length > 3) {
            return replaceAll.substring(length - 3, length);
        }
        return null;
    }

    private jny e(DeviceInfo deviceInfo) {
        int productType = deviceInfo.getProductType();
        String deviceName = deviceInfo.getDeviceName();
        LogUtil.a("IntellLife", "getDeviceModeInfo () type:", Integer.valueOf(productType), " deviceNameKey:", deviceName);
        return c(deviceInfo, productType, deviceName);
    }

    private jny a(DeviceInfo deviceInfo, int i, String str) {
        if (i == 7) {
            LogUtil.a("IntellLife", "getLikeByMap device is HUAWEI B3-");
            return new jny("GMN-BX9", "004Z", "06E");
        }
        if (i == 12) {
            return new jny("AW61", "0056", "06E");
        }
        if (i == 19) {
            LogUtil.a("IntellLife", "getLikeByMap device is HUAWEI Band 3 Pro-");
            return new jny("TER-B19", "002K", "06E");
        }
        if (i == 45) {
            LogUtil.a("IntellLife", "getLikeByMap device is HUAWEI Band 3i-");
            return new jny("ADS-B29", "005U", "06E");
        }
        if (i == 23) {
            LogUtil.a("IntellLife", "getLikeByMap device is HUAWEI Band 3e-");
            return new jny("AW70-B29", "004X", "06E");
        }
        if (i == 24) {
            return new jny("AW70-B19", "0053", "06E");
        }
        if (i == 36) {
            return new jny("AW70-B39", "006X", "06E");
        }
        if (i != 37) {
            switch (i) {
                case 14:
                    LogUtil.a("IntellLife", "getLikeByMap device is HUAWEI B3 Lite-");
                    return new jny("GRU-B09", "0050", "06E");
                case 15:
                    LogUtil.a("IntellLife", "getLikeByMap device is HUAWEI Sport");
                    return new jny("ERS-B19", "0051", "06E");
                case 16:
                    LogUtil.a("IntellLife", "getLikeByMap device is HUAWEI B5-");
                    return new jny("JNS-BX9", "004Y", "06E");
                default:
                    return e(deviceInfo, i, str);
            }
        }
        return new jny("AW70-B39HN", "006Y", "06E");
    }

    private jny e(DeviceInfo deviceInfo, int i, String str) {
        if (i == 13) {
            LogUtil.a("IntellLife", "getLikeByMap device is honor Band 3-");
            return new jny("NYX-B10", "0054", "06E");
        }
        if (i != 18) {
            if (i == 44) {
                LogUtil.a("IntellLife", "getLikeByMap device is HONOR Band 5i-");
                return new jny("ADS-B19", "005T", "06E");
            }
            if (i < 55) {
                LogUtil.h("IntellLife", "getDeviceModeInfo() no device Matching");
                return null;
            }
            return h(deviceInfo);
        }
        jny h = h(deviceInfo);
        if (h != null) {
            LogUtil.a("IntellLife", "get HONOR Band 5 model from deviceInfo not null");
            return h;
        }
        if (str.toUpperCase(Locale.ENGLISH).indexOf("HONOR BAND 5-") != -1) {
            LogUtil.a("IntellLife", "getLikeByMap device is HONOR Band5");
            return new jny("CRS-B19S", "005R", "06E");
        }
        LogUtil.a("IntellLife", "getLikeByMap device is HONOR Band4");
        return new jny("CRS-B19", "002I", "06E");
    }

    private jny h(DeviceInfo deviceInfo) {
        String hiLinkDeviceId = deviceInfo.getHiLinkDeviceId();
        String deviceModel = deviceInfo.getDeviceModel();
        if (TextUtils.isEmpty(hiLinkDeviceId) || TextUtils.isEmpty(deviceModel)) {
            LogUtil.h("IntellLife", "getModelInfoFromDeviceInfo() not get DeviceId and DeviceMode");
            return null;
        }
        if (hiLinkDeviceId.length() != 4) {
            LogUtil.h("IntellLife", "getModelInfoFromDeviceInfo() hiLinkDeviceId is Illegal");
            return null;
        }
        return new jny(deviceModel, hiLinkDeviceId, g(deviceInfo));
    }

    private String g(DeviceInfo deviceInfo) {
        String g;
        int ah;
        if (EnvironmentUtils.c()) {
            g = jfu.j(deviceInfo.getProductType());
            LogUtil.a("IntellLife", "mainprocess getModelInfoFromDeviceInfo uuid：", g);
            cuw e2 = jfu.e(g);
            if (e2 != null && e2.i() != -1) {
                ReleaseLogUtil.d("DEVMGR_IntellLife", "getDeviceType DeviceCategory():", Integer.valueOf(e2.i()));
                return b(e2.i());
            }
        } else {
            g = juu.g(deviceInfo.getProductType());
            LogUtil.a("IntellLife", "phoneservice getModelInfoFromDeviceInfo uuid：", g);
            jux d = juu.d(g);
            if (d != null && d.a() != -1) {
                ReleaseLogUtil.d("DEVMGR_IntellLife", "getDeviceType DeviceCategory():", Integer.valueOf(d.a()));
                return b(d.a());
            }
        }
        LogUtil.h("IntellLife", "getModelInfoFromDeviceInfo deviceInfoNew is null");
        cvc pluginInfoByUuid = ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).getPluginInfoByUuid(g);
        if (pluginInfoByUuid == null || pluginInfoByUuid.f() == null || (ah = pluginInfoByUuid.f().ah()) == -1) {
            return (EnvironmentUtils.c() && jfu.h(deviceInfo.getProductType())) ? "06E" : (EnvironmentUtils.e() && juu.j(deviceInfo.getProductType())) ? "06E" : "06D";
        }
        ReleaseLogUtil.d("DEVMGR_IntellLife", "getDeviceType from description DeviceCategory():", Integer.valueOf(ah));
        return b(ah);
    }

    private jny c(DeviceInfo deviceInfo, int i, String str) {
        if (i == 8) {
            LogUtil.a("IntellLife", "getLikeByMap device is honor Watch S1-");
            return new jny("MES-B19", "004W", "06D");
        }
        if (i == 10) {
            if (str.toUpperCase(Locale.ENGLISH).indexOf("PORSCHE DESIGN") != -1) {
                LogUtil.a("IntellLife", "getLikeByMap device is PORSCHE DESIGN");
                return new jny("LEO-BX9", "004U", "06D");
            }
            LogUtil.a("IntellLife", "getLikeByMap device is HUAWEI WATCH 2");
            return new jny("LEO-DLXX", "004Q", "06D");
        }
        if (i != 34) {
            if (i == 20) {
                LogUtil.a("IntellLife", "getLikeByMap device is honor Watch-");
                return new jny("TLS-B19", "002M", "06D");
            }
            if (i == 21) {
                LogUtil.a("IntellLife", "getLikeByMap device is HUAWEI WATCH GT-");
                return new jny("FTN-B19", "003N", "06D");
            }
            return b(deviceInfo, i, str);
        }
        jny h = h(deviceInfo);
        if (h != null) {
            LogUtil.a("IntellLife", "getLikeByMap device is HUAWEI WATCH GT 2 pro vidar");
        } else if (!TextUtils.isEmpty(deviceInfo.getManufacture())) {
            if ("010404".equals(deviceInfo.getManufacture())) {
                jny jnyVar = new jny("LTN-B19", "005W", "06D");
                LogUtil.a("IntellLife", "getLikeByMap device is HUAWEI WATCH GT 2 Latona");
                return jnyVar;
            }
            if ("010303".equals(deviceInfo.getManufacture())) {
                return c(deviceInfo);
            }
            LogUtil.h("IntellLife", "HUAWEI WATCH GT 2 unknown Manufacture");
        } else {
            LogUtil.h("IntellLife", "HUAWEI WATCH GT 2 Manufacture is empty");
        }
        return h;
    }

    private jny b(DeviceInfo deviceInfo, int i, String str) {
        if (i == 35) {
            jny h = h(deviceInfo);
            if (h != null) {
                LogUtil.a("IntellLife", "get MagicWatch 2 model from deviceInfo not null");
            } else if (!TextUtils.isEmpty(deviceInfo.getManufacture())) {
                if ("010404".equals(deviceInfo.getManufacture())) {
                    LogUtil.a("IntellLife", "getLikeByMap device is MagicWatch 2 minos");
                    return new jny("MNS-B19", "005Y", "06D");
                }
                if ("010303".equals(deviceInfo.getManufacture())) {
                    LogUtil.a("IntellLife", "getLikeByMap device is MagicWatch 2 heibe");
                    return new jny("HBE-B19", "005Z", "06D");
                }
                LogUtil.h("IntellLife", "MagicWatch 2 unknown Manufacture");
            } else {
                LogUtil.h("IntellLife", "MagicWatch 2 Manufacture is empty");
            }
            return h;
        }
        return a(deviceInfo, i, str);
    }

    private jny c(DeviceInfo deviceInfo) {
        if (deviceInfo.getDeviceModel().toUpperCase(Locale.ENGLISH).contains("HECTOR")) {
            LogUtil.a("IntellLife", "getLikeByMap device is HUAWEI WATCH Hector");
            return new jny("HCT-B19", "M003", "06D");
        }
        LogUtil.a("IntellLife", "getLikeByMap device is HUAWEI WATCH GT 2 Diana");
        return new jny("DAN-B19", "005X", "06D");
    }

    private void b(Map<String, Object> map, DeviceInfo deviceInfo) {
        String g;
        String str = "002";
        if (EnvironmentUtils.c()) {
            int p = jfu.c(deviceInfo.getProductType()).p();
            ReleaseLogUtil.e("DEVMGR_IntellLife", "ManufactureId is ", Integer.valueOf(p));
            if (p != -1) {
                map.put(ProfileRequestConstants.MANU, p == 2 ? "002" : "001");
                return;
            }
            g = jfu.j(deviceInfo.getProductType());
        } else {
            int i = juu.a(deviceInfo.getProductType()).i();
            ReleaseLogUtil.e("DEVMGR_IntellLife", "ManufactureId is ", Integer.valueOf(i));
            if (i != -1) {
                map.put(ProfileRequestConstants.MANU, i == 2 ? "002" : "001");
                return;
            }
            g = juu.g(deviceInfo.getProductType());
        }
        cvc pluginInfoByUuid = ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).getPluginInfoByUuid(g);
        if (pluginInfoByUuid != null && pluginInfoByUuid.f() != null) {
            String f = pluginInfoByUuid.f().f();
            if (!TextUtils.isEmpty(f)) {
                try {
                    int parseInt = Integer.parseInt(f);
                    ReleaseLogUtil.e("DEVMGR_IntellLife", "getManufactureId manu type : ", Integer.valueOf(parseInt));
                    if (parseInt != 2) {
                        str = "001";
                    }
                    map.put(ProfileRequestConstants.MANU, str);
                    return;
                } catch (NumberFormatException e2) {
                    LogUtil.b("IntellLife", e2.getMessage());
                }
            }
        }
        map.put(ProfileRequestConstants.MANU, "001");
    }

    private void d(DeviceInfo deviceInfo, Map<String, Object> map, String str) {
        if (!ProfileAgent.PROFILE_AGENT.getConnected()) {
            LogUtil.h("IntellLife", "putDeviceProfile profile not connected");
            return;
        }
        String a2 = a(deviceInfo);
        boolean b = b(a2, map);
        ReleaseLogUtil.e("DEVMGR_IntellLife", "putDeviceProfile isNeedNewDeviceProfile", Boolean.valueOf(b));
        boolean z = true;
        if (b) {
            LogUtil.a("IntellLife", "new profile, need put device.");
            DeviceProfile deviceProfile = new DeviceProfile();
            deviceProfile.setId(a(deviceInfo));
            deviceProfile.setIsNeedCloud(true);
            if (map.get("devType") instanceof String) {
                deviceProfile.setType((String) map.get("devType"));
            }
            deviceProfile.addEntities(map);
            z = ProfileAgent.PROFILE_AGENT.getClientAgent().putDevice(deviceProfile);
        }
        ReleaseLogUtil.e("DEVMGR_IntellLife", "putDevice is success: ", Boolean.valueOf(z));
        if (z) {
            b(deviceInfo, str);
            f(deviceInfo);
            j(deviceInfo);
            i(deviceInfo);
            n(deviceInfo);
        }
        j(a2);
    }

    private boolean b(String str, Map<String, Object> map) {
        List<DeviceProfile> devicesById = ProfileAgent.PROFILE_AGENT.getClientAgent().getDevicesById(str, false, Collections.singletonList(ProfileClient.DEFAULT_TRUST_DOMAIN));
        if (devicesById == null || devicesById.isEmpty()) {
            LogUtil.h("IntellLife", "new device profile, need put device.");
            return true;
        }
        for (DeviceProfile deviceProfile : devicesById) {
            LogUtil.c("IntellLife", "test ", str, " deviceProfile : ", devicesById.toString());
            if (!c(map, deviceProfile.getProfile())) {
                return true;
            }
        }
        return false;
    }

    private boolean c(Map<String, Object> map, Map<String, Object> map2) {
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            Object obj = map2.get(key);
            Object value = entry.getValue();
            if (value != null && !value.equals(obj) && !ProfileRequestConstants.HIV.equalsIgnoreCase(key)) {
                return false;
            }
        }
        return true;
    }

    private void e(DeviceInfo deviceInfo, String str, String str2) {
        if (!ProfileAgent.PROFILE_AGENT.getConnected()) {
            LogUtil.h("IntellLife", "putDeviceService profile not connected");
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
        ReleaseLogUtil.e("DEVMGR_IntellLife", "putDeviceService is ", Boolean.valueOf(ProfileAgent.PROFILE_AGENT.getClientAgent().putServiceOfDevice(a2, serviceProfile)));
    }

    private boolean e(String str, String str2, Map<String, Object> map) {
        if (!ProfileAgent.PROFILE_AGENT.getConnected()) {
            LogUtil.h("IntellLife", "putServiceCharacteristicAddEntities profile not connected");
            return false;
        }
        ServiceCharacteristicProfile serviceCharacteristicProfile = new ServiceCharacteristicProfile();
        serviceCharacteristicProfile.addEntities(map);
        serviceCharacteristicProfile.addEntityInfo("timestamp", Long.valueOf(System.currentTimeMillis()));
        serviceCharacteristicProfile.setIsNeedCloud(true);
        LogUtil.c("IntellLife", "putServiceCharacteristicAddEntities serviceCharacteristicProfile ", serviceCharacteristicProfile.toString());
        boolean putServiceCharacteristic = ProfileAgent.PROFILE_AGENT.getClientAgent().putServiceCharacteristic(str, str2, serviceCharacteristicProfile);
        ReleaseLogUtil.e("DEVMGR_IntellLife", "putServiceCharacteristicAddEntities put isSuccess:", Boolean.valueOf(putServiceCharacteristic));
        return putServiceCharacteristic;
    }

    private String g() {
        if (Build.VERSION.SDK_INT >= 29) {
            String androidId = FoundationCommonUtil.getAndroidId(BaseApplication.getContext());
            if (!TextUtils.isEmpty(androidId)) {
                return androidId.toUpperCase(Locale.ENGLISH);
            }
            LogUtil.b("IntellLife", "androidId is isEmpty");
        }
        if (BaseApplication.getContext().getPackageManager().checkPermission("android.permission.READ_PHONE_STATE", BaseApplication.getContext().getPackageName()) == 0) {
            LogUtil.a("IntellLife", "getSerialNumber version > VERSION_CODES.0");
            try {
                return Build.getSerial();
            } catch (SecurityException unused) {
                LogUtil.b("IntellLife", "getSerialNumber SecurityException");
                return CommonUtil.ag();
            }
        }
        LogUtil.a("IntellLife", "getSerialNumber version < VERSION_CODES.0");
        return CommonUtil.ag();
    }

    private void e(String str) {
        ProfileClient clientAgent = ProfileAgent.PROFILE_AGENT.getClientAgent();
        List<ServiceProfile> servicesOfDevice = clientAgent.getServicesOfDevice(str, true, Arrays.asList(ProfileClient.CLOUD_LOCAL_DOMAIN));
        if (servicesOfDevice == null || servicesOfDevice.isEmpty()) {
            boolean deleteDevice = clientAgent.deleteDevice(str);
            LogUtil.h("IntellLife", "deleteAllServices deleteDevice success:", Boolean.valueOf(deleteDevice));
            e(deleteDevice);
        } else {
            Iterator<ServiceProfile> it = servicesOfDevice.iterator();
            while (it.hasNext()) {
                String id = it.next().getId();
                LogUtil.a("IntellLife", " deleteAllServices serviceId:", id);
                e(str, id);
                LogUtil.a("IntellLife", "deleteAllServices deleteDevice success:", Boolean.valueOf(clientAgent.deleteServiceOfDevice(str, id)));
            }
        }
    }

    private void e(String str, String str2) {
        ProfileClient clientAgent = ProfileAgent.PROFILE_AGENT.getClientAgent();
        ServiceCharacteristicProfile serviceCharacteristics = clientAgent.getServiceCharacteristics(str, str2, true, Arrays.asList(ProfileClient.CLOUD_LOCAL_DOMAIN));
        if (serviceCharacteristics == null) {
            LogUtil.h("IntellLife", "characteristicProfile is empty");
            return;
        }
        Map<String, Object> profile = serviceCharacteristics.getProfile();
        if (profile.isEmpty()) {
            LogUtil.h("IntellLife", "deleteAllCharacter profile map is empty");
            return;
        }
        Iterator<Map.Entry<String, Object>> it = profile.entrySet().iterator();
        while (it.hasNext()) {
            String key = it.next().getKey();
            LogUtil.c("IntellLife", " deleteAllCharacter character key:", key);
            LogUtil.c("IntellLife", " deleteAllCharacter is success:", Boolean.valueOf(clientAgent.deleteServiceCharacteristic(str, str2, key)));
        }
    }

    public void e() {
        LogUtil.a("IntellLife", "updateCloudDeviceMessage()");
        h();
        HashMap hashMap = new HashMap(10);
        HashMap hashMap2 = new HashMap(10);
        e(hashMap, hashMap2);
        String a2 = a();
        for (Map.Entry<String, DeviceInfo> entry : hashMap.entrySet()) {
            String key = entry.getKey();
            if (hashMap2.get(key) != null) {
                LogUtil.a("IntellLife", "localDevice the theme as cloudDevice");
                DeviceInfo deviceInfo = hashMap.get(key);
                if (deviceInfo == null) {
                    LogUtil.h("IntellLife", "updateCloudDeviceMessage(): local device in cloud , but local deviceInfo is null");
                } else {
                    if (e(deviceInfo) == null) {
                        LogUtil.h("IntellLife", "updateCloudDeviceMessage(): not Matching device form modeList");
                    }
                    a(deviceInfo, a2);
                    f(deviceInfo);
                }
            } else {
                LogUtil.a("IntellLife", "localDevice not add cloud");
                d(entry.getValue(), a2);
            }
        }
    }

    private void e(Map<String, DeviceInfo> map, final Map<String, DeviceProfile> map2) {
        LogUtil.a("IntellLife", "initDeviceMap()");
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.ALL_DEVICES, null, "IntellLife");
        if (deviceList == null) {
            if (jpp.c()) {
                LogUtil.h("IntellLife", "initDeviceMap() isHasBondedDevice");
                return;
            }
            deviceList = new ArrayList<>(16);
        }
        for (DeviceInfo deviceInfo : deviceList) {
            String a2 = a(deviceInfo);
            LogUtil.c("IntellLife", "localDeviceId:", a2);
            map.put(a2, deviceInfo);
        }
        if (!CommonUtil.bu()) {
            jnw.e().d("IntellLife", new IBaseResponseCallback() { // from class: jqd
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i, Object obj) {
                    jqf.this.d(map2, i, obj);
                }
            });
        } else {
            LogUtil.a("IntellLife", "getCloudDeviceList isStoreDemoVersion");
        }
    }

    /* synthetic */ void d(Map map, int i, Object obj) {
        if (i == 0 && koq.e(obj, DeviceProfile.class)) {
            if (obj instanceof List) {
                List<DeviceProfile> list = (List) obj;
                LogUtil.c("IntellLife", "deviceCloudProfiles:", Integer.valueOf(list.size()));
                c((Map<String, DeviceProfile>) map, list);
                return;
            }
            return;
        }
        LogUtil.h("IntellLife", "getCloudDeviceList errorCode:", Integer.valueOf(i));
    }

    private void c(Map<String, DeviceProfile> map, List<DeviceProfile> list) {
        String str;
        for (DeviceProfile deviceProfile : list) {
            Map<String, Object> profile = deviceProfile.getProfile();
            try {
                String str2 = profile.get("devType") instanceof String ? (String) profile.get("devType") : null;
                if (str2 == null) {
                    LogUtil.h("IntellLife", "cloudDevice type is null");
                } else if (!str2.equals("06E") && !str2.equals("06D") && !str2.equals("A12")) {
                    LogUtil.h("IntellLife", "cloudDevice type not is watch or band");
                } else {
                    if (!(profile.get("sn") instanceof String)) {
                        str = "";
                    } else {
                        str = (String) profile.get("sn");
                        LogUtil.a("IntellLife", "serial string:", CommonUtil.l(str));
                    }
                    String id = deviceProfile.getId();
                    LogUtil.a("IntellLife", "deviceProfileId string:", CommonUtil.l(id));
                    if (!TextUtils.isEmpty(id) && (id.contains("#ANDROID21") || str.contains("#ANDROID21"))) {
                        map.put(id, deviceProfile);
                    }
                }
            } catch (ClassCastException unused) {
                LogUtil.b("IntellLife", "initDeviceMap() ClassCastException");
            } catch (NumberFormatException unused2) {
                LogUtil.b("IntellLife", "initDeviceMap() NumberFormatException");
            }
        }
    }

    private String b(String str) {
        return str + "#ANDROID21";
    }

    private void h() {
        String str;
        String str2;
        List<DeviceProfile> devices = ProfileAgent.PROFILE_AGENT.getClientAgent().getDevices(true, Arrays.asList(ProfileClient.CLOUD_LOCAL_DOMAIN));
        if (devices != null) {
            for (DeviceProfile deviceProfile : devices) {
                try {
                    Map<String, Object> profile = deviceProfile.getProfile();
                    str = profile.get("prodId") instanceof String ? (String) profile.get("prodId") : "";
                    str2 = profile.get("deviceName") instanceof String ? (String) profile.get("deviceName") : "";
                    LogUtil.a("IntellLife", "deleteErrorMessage productId:", str, " deleteErrorMessage deviceName:", str2);
                } catch (ClassCastException unused) {
                    LogUtil.b("IntellLife", "deleteErrorMessage() ClassCastException");
                } catch (NumberFormatException unused2) {
                    LogUtil.b("IntellLife", "deleteErrorMessage() NumberFormatException");
                }
                if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
                    if ("0054".equals(str) && str2.toUpperCase(Locale.ENGLISH).indexOf("HONOR BAND 4") > -1) {
                        String id = deviceProfile.getId();
                        if (!TextUtils.isEmpty(id)) {
                            c(id);
                        }
                    }
                }
                LogUtil.h("IntellLife", "deleteErrorMessage() productId or deviceName is empty");
            }
        }
    }

    private void j(String str) {
        String g = g(str);
        ReleaseLogUtil.e("DEVMGR_IntellLife", "putWiseDeviceId wiseDeviceId:", CommonUtil.l(g));
        if (TextUtils.isEmpty(g)) {
            return;
        }
        KeyValDbManager.b(BaseApplication.getContext()).e(str, g);
    }

    private String g(String str) {
        List<DeviceProfile> devices = ProfileAgent.PROFILE_AGENT.getClientAgent().getDevices(true, Arrays.asList(ProfileClient.CLOUD_LOCAL_DOMAIN));
        if (devices == null) {
            LogUtil.h("IntellLife", "getWiseDeviceIdByDeviceId deviceProfiles is null");
            return "";
        }
        LogUtil.a("IntellLife", "getWiseDeviceIdByDeviceId deviceProfile size: ", Integer.valueOf(devices.size()));
        for (DeviceProfile deviceProfile : devices) {
            if (str.equalsIgnoreCase(deviceProfile.getId())) {
                Map<String, Object> profile = deviceProfile.getProfile();
                if (!(profile.get("mac") instanceof String)) {
                    LogUtil.h("IntellLife", "getWiseDeviceIdByDeviceId mac not instanceof String");
                } else if (TextUtils.isEmpty((String) profile.get("mac"))) {
                    LogUtil.h("IntellLife", "getWiseDeviceIdByDeviceId mac is empty");
                } else if (!(profile.get("wiseDeviceId") instanceof String)) {
                    LogUtil.h("IntellLife", "getWiseDeviceIdByDeviceId wiseDeviceId not instanceof String");
                } else {
                    String str2 = (String) profile.get("wiseDeviceId");
                    if (TextUtils.isEmpty(str2) || Constants.NULL.equals(str2)) {
                        LogUtil.h("IntellLife", "getWiseDeviceIdByDeviceId wiseDeviceId is empty");
                    } else {
                        LogUtil.a("IntellLife", "getWiseDeviceIdByDeviceId wiseDeviceId not empty");
                        return str2;
                    }
                }
            }
        }
        return "";
    }

    private void e(boolean z) {
        if (z) {
            for (int i = 0; i < bfe.b.size(); i++) {
                KeyValDbManager.b(BaseApplication.getContext()).e("profile_devices_last_query_time_01" + bfe.b.get(i), String.valueOf(0));
            }
        }
    }

    private String b(int i) {
        if (i == 1) {
            return "06E";
        }
        return i == 6 ? "A12" : "06D";
    }
}
