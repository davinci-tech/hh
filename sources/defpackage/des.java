package defpackage;

import android.content.ContentValues;
import android.text.TextUtils;
import com.huawei.health.device.api.DeviceDataBaseHelperApi;
import com.huawei.health.device.api.HonourDeviceConstantsApi;
import com.huawei.health.ecologydevice.manager.ProfileManager;
import com.huawei.hiai.awareness.client.AwarenessRequest;
import com.huawei.hms.network.embedded.w9;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdataaccessmodel.utils.StorageDataCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.ble.BleConstants;
import com.huawei.operation.utils.Constants;
import com.huawei.profile.client.profile.ProfileClient;
import com.huawei.profile.profile.DeviceProfile;
import com.huawei.profile.profile.ServiceCharacteristicProfile;
import com.huawei.profile.profile.ServiceProfile;
import health.compact.a.CommonUtil;
import health.compact.a.KeyValDbManager;
import health.compact.a.Services;
import health.compact.a.StorageResult;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class des {
    public boolean a(dep depVar, String str) {
        if (depVar == null || depVar.c() == null || depVar.c().getProfile() == null) {
            ReleaseLogUtil.d("R_smartlife_IntellLife", "cloudValidInformation is invalid");
            return false;
        }
        DeviceProfile c = depVar.c();
        Object obj = c.getProfile().get("prodId");
        boolean isHuaweiOrHonourScaleProdId = obj instanceof String ? ((HonourDeviceConstantsApi) Services.c("PluginDevice", HonourDeviceConstantsApi.class)).isHuaweiOrHonourScaleProdId((String) obj) : false;
        Object obj2 = c.getProfile().get("mac");
        if (isHuaweiOrHonourScaleProdId) {
            if (obj2 instanceof String) {
                c.getProfile().put("mac", e((String) obj2));
            } else {
                ReleaseLogUtil.d("R_smartlife_IntellLife", "mac address is invalid");
            }
        }
        Map<String, Object> b = depVar.b();
        if (!b(c)) {
            ReleaseLogUtil.d("R_smartlife_IntellLife", "fail to put device Profile");
            return false;
        }
        if (!d(dew.d(c, w9.h, dew.c(w9.h)), b)) {
            ReleaseLogUtil.d("R_smartlife_IntellLife", "fail to put device Profile");
            return false;
        }
        Object obj3 = c.getProfile().get("udid");
        String str2 = obj3 instanceof String ? (String) obj3 : "";
        if (isHuaweiOrHonourScaleProdId) {
            LogUtil.a("smartlife_IntellLife", "put device prodId: ", obj);
            i(str2);
            if (obj2 instanceof String) {
                str2 = (String) obj2;
            }
        }
        ReleaseLogUtil.e("R_smartlife_IntellLife", "deviceId or macAddress: ", CommonUtil.l(str2));
        boolean a2 = a(dew.d(c, "managerNode", dew.c("managerNode")), depVar.a(), str);
        if (a2) {
            StorageDataCallback storageDataCallback = new StorageDataCallback() { // from class: det
                @Override // com.huawei.hwdataaccessmodel.utils.StorageDataCallback
                public final void onProcessed(StorageResult storageResult) {
                    des.a(storageResult);
                }
            };
            String d = knl.d(str2);
            String str3 = (String) c.getProfile().get("deviceName");
            ReleaseLogUtil.e("R_smartlife_IntellLife", "deviceName: ", str3);
            KeyValDbManager.b(BaseApplication.getContext()).d(d, str3, storageDataCallback);
        }
        return a2;
    }

    static /* synthetic */ void a(StorageResult storageResult) {
        Object[] objArr = new Object[2];
        objArr[0] = "set device name to cp, result = ";
        objArr[1] = storageResult == null ? Constants.NULL : storageResult.e();
        LogUtil.a("smartlife_IntellLife", objArr);
    }

    private String e(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("smartlife_IntellLife", "empty mac");
            return "";
        }
        String replaceAll = str.replaceAll(":", "");
        int length = replaceAll.length();
        return length > 3 ? replaceAll.substring(length - 3, length) : replaceAll;
    }

    private void i(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("smartlife_IntellLife", "deviceId is empty");
            return;
        }
        String d = dew.d(str);
        LogUtil.a("smartlife_IntellLife", "get wiseDeviceId, result = ", d);
        ContentValues contentValues = new ContentValues();
        contentValues.put("mDeviceId", d);
        if (((DeviceDataBaseHelperApi) Services.c("PluginDevice", DeviceDataBaseHelperApi.class)).update(contentValues, "uniqueId = ? or sn = ?", new String[]{str, str}) <= 0) {
            LogUtil.h("smartlife_IntellLife", "fail to udpate wisedeviceid");
        }
    }

    public boolean c(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("smartlife_IntellLife", "addPeerConnectStatus:device id is empty!!");
            return false;
        }
        List<DeviceProfile> devices = ProfileManager.INSTANCE.getDevices(true, Arrays.asList(ProfileClient.CLOUD_LOCAL_DOMAIN));
        if (devices == null) {
            LogUtil.h("smartlife_IntellLife", "addPeerConnectStatus:deviceCloudProfiles is empty!!");
            return false;
        }
        for (DeviceProfile deviceProfile : devices) {
            if (deviceProfile.getId().equals(str)) {
                HashMap hashMap = new HashMap(16);
                hashMap.put("peerConnectStatus", Integer.valueOf(i));
                return b(deviceProfile, BleConstants.DEV_INFO, BleConstants.DEV_INFO, hashMap);
            }
        }
        LogUtil.h("smartlife_IntellLife", "addPeerConnectStatus:device id not found in cloud.");
        return false;
    }

    private boolean b(DeviceProfile deviceProfile, String str, String str2, Map<String, Object> map) {
        if (map == null || map.isEmpty()) {
            LogUtil.h("smartlife_IntellLife", "characterEntity is empty");
            return false;
        }
        String id = deviceProfile.getId();
        if (e(str, id)) {
            LogUtil.h("smartlife_IntellLife", "serviceId is not exits, put serviceId and character.");
            return d(deviceProfile, str, str2, map, 1);
        }
        ServiceCharacteristicProfile serviceCharacteristics = ProfileManager.INSTANCE.getServiceCharacteristics(id, str, true, Arrays.asList(ProfileClient.CLOUD_LOCAL_DOMAIN));
        if (serviceCharacteristics == null) {
            LogUtil.h("smartlife_IntellLife", "isNewParamServiceIdSuccess serviceProfile is null.");
            return d(deviceProfile, str, str2, map, 1);
        }
        Map<String, Object> profile = serviceCharacteristics.getProfile();
        if (a(profile)) {
            LogUtil.a("smartlife_IntellLife", "new device, put service.");
            return d(deviceProfile, str, str2, map, 1);
        }
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if (!entry.getValue().equals(profile.get(entry.getKey()))) {
                return d(deviceProfile, str, str2, map, 2);
            }
        }
        return true;
    }

    private boolean d(DeviceProfile deviceProfile, String str, String str2, Map<String, Object> map, int i) {
        if ((i & 1) == 1) {
            e(dew.d(deviceProfile, str, str2));
        }
        return d(deviceProfile.getId(), str, dew.d(map));
    }

    private boolean e(String str, String str2) {
        List<ServiceProfile> servicesOfDevice = ProfileManager.INSTANCE.getServicesOfDevice(str2, false, Arrays.asList(ProfileClient.CLOUD_LOCAL_DOMAIN));
        if (servicesOfDevice == null || servicesOfDevice.isEmpty()) {
            LogUtil.h("smartlife_IntellLife", "service id not exit, put all.");
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

    private boolean a(Map<String, Object> map) {
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

    public String a(String str) {
        List<DeviceProfile> devices = ProfileManager.INSTANCE.getDevices(true, Arrays.asList(ProfileClient.CLOUD_LOCAL_DOMAIN));
        if (devices == null) {
            LogUtil.h("smartlife_IntellLife", "deleteDeviceById deviceProfiles is null");
            return "";
        }
        for (DeviceProfile deviceProfile : devices) {
            if (str.equalsIgnoreCase(deviceProfile.getId())) {
                return String.valueOf(deviceProfile.getProfile().get("wiseDeviceId")).equals(Constants.NULL) ? "" : String.valueOf(deviceProfile.getProfile().get("wiseDeviceId"));
            }
        }
        return "";
    }

    public Map<String, String> a(List<String> list) {
        List<DeviceProfile> devices = ProfileManager.INSTANCE.getDevices(true, Arrays.asList(ProfileClient.CLOUD_LOCAL_DOMAIN));
        if (devices == null) {
            LogUtil.h("smartlife_IntellLife", "deleteDeviceById deviceProfiles is null");
            return Collections.EMPTY_MAP;
        }
        LogUtil.a("smartlife_IntellLife", "enter intellife");
        HashMap hashMap = new HashMap();
        for (String str : list) {
            Iterator<DeviceProfile> it = devices.iterator();
            while (true) {
                if (it.hasNext()) {
                    DeviceProfile next = it.next();
                    if (str.equalsIgnoreCase(next.getId())) {
                        if (next.getProfile().get("wiseDeviceId") != null) {
                            hashMap.put(str, String.valueOf(next.getProfile().get("wiseDeviceId")));
                            if (KeyValDbManager.b(BaseApplication.getContext()).e(knl.d(str)) == null) {
                                KeyValDbManager.b(BaseApplication.getContext()).d(next.getProfile().get("udid") instanceof String ? knl.d((String) next.getProfile().get("udid")) : null, next.getProfile().get("deviceName") instanceof String ? (String) next.getProfile().get("deviceName") : null, new StorageDataCallback() { // from class: deu
                                    @Override // com.huawei.hwdataaccessmodel.utils.StorageDataCallback
                                    public final void onProcessed(StorageResult storageResult) {
                                        des.b(storageResult);
                                    }
                                });
                            }
                        }
                    }
                }
            }
        }
        return hashMap;
    }

    static /* synthetic */ void b(StorageResult storageResult) {
        Object[] objArr = new Object[2];
        objArr[0] = "set device name to cp, result = ";
        objArr[1] = storageResult == null ? Constants.NULL : storageResult.e();
        LogUtil.a("smartlife_IntellLife", objArr);
    }

    public void a(String str, String str2, String str3) {
        LogUtil.a("smartlife_IntellLife", "removeDeviceToProfile start deviceId , type ", str2);
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            LogUtil.h("smartlife_IntellLife", "removeDeviceToProfile illegal arguments");
            return;
        }
        String c = c(str);
        LogUtil.a("smartlife_IntellLife", "removeDeviceToProfile currentType:", c);
        if (h(c) && !str2.equals(c)) {
            LogUtil.h("smartlife_IntellLife", "removeDeviceToProfile current device Type is not target type");
            return;
        }
        d(str, str3);
        String d = d(str, "managerNode", "characteristic.managerIdentifierList2");
        if (!dew.a(d) || AwarenessRequest.MessageType.DISCONNECT.equals(d)) {
            return;
        }
        b(str);
    }

    private boolean h(String str) {
        return (TextUtils.isEmpty(str) || Constants.NULL.equalsIgnoreCase(str)) ? false : true;
    }

    private void b(final Map<String, dep> map, final Map<String, DeviceProfile> map2, final List<String> list) {
        cun.c().getDevicesFromCloud("smartlife_IntellLife", new IBaseResponseCallback() { // from class: dev
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                des.this.d(map2, list, map, i, obj);
            }
        });
    }

    /* synthetic */ void d(Map map, List list, Map map2, int i, Object obj) {
        if (i == 0 && koq.e(obj, DeviceProfile.class)) {
            if (obj instanceof List) {
                dew.d((Map<String, DeviceProfile>) map, (List<DeviceProfile>) obj, (List<String>) list);
            }
        } else {
            LogUtil.h("smartlife_IntellLife", "getCloudDeviceList errorCode:", Integer.valueOf(i));
        }
        String d = dew.d();
        d((Map<String, dep>) map2, (Map<String, DeviceProfile>) map, d);
        b((Map<String, DeviceProfile>) map, (Map<String, dep>) map2, d);
    }

    private boolean b(DeviceProfile deviceProfile) {
        if (deviceProfile == null) {
            LogUtil.h("smartlife_IntellLife", "putDeviceProfile profile is null");
            return false;
        }
        boolean putDevice = ProfileManager.INSTANCE.putDevice(deviceProfile);
        LogUtil.a("smartlife_IntellLife", "isPutDeviceSuccess=", Boolean.valueOf(putDevice));
        return putDevice;
    }

    private boolean e(ServiceProfile serviceProfile) {
        if (dew.a(serviceProfile)) {
            return ProfileManager.INSTANCE.putServiceOfDevice(serviceProfile.getDeviceId(), serviceProfile);
        }
        return false;
    }

    private boolean d(String str, String str2, ServiceCharacteristicProfile serviceCharacteristicProfile) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            LogUtil.h("smartlife_IntellLife", "putServiceCharacteristic deviceId or serviceId is null");
            return false;
        }
        boolean putServiceCharacteristic = ProfileManager.INSTANCE.putServiceCharacteristic(str, str2, serviceCharacteristicProfile);
        LogUtil.a("smartlife_IntellLife", "isSuccess=", Boolean.valueOf(putServiceCharacteristic));
        return putServiceCharacteristic;
    }

    private boolean d(ServiceProfile serviceProfile, Map<String, Object> map) {
        if (e(serviceProfile)) {
            return d(serviceProfile.getDeviceId(), w9.h, dew.d(map));
        }
        LogUtil.h("smartlife_IntellLife", "putConnectionService fail");
        return false;
    }

    private boolean a(ServiceProfile serviceProfile, Map<String, Object> map, String str) {
        if (e(serviceProfile)) {
            d(serviceProfile.getDeviceId(), map, str);
            return true;
        }
        LogUtil.h("smartlife_IntellLife", "put manageNode service fail");
        return false;
    }

    public void d(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("smartlife_IntellLife", "removeDeviceToProfile deviceId is null");
            return;
        }
        ServiceCharacteristicProfile serviceCharacteristics = ProfileManager.INSTANCE.getServiceCharacteristics(str, "managerNode", true, Arrays.asList(ProfileClient.CLOUD_LOCAL_DOMAIN));
        if (serviceCharacteristics == null) {
            LogUtil.h("smartlife_IntellLife", "removeServiceCharacterValue characteristicProfiles is null");
            return;
        }
        String e = dew.e(dew.b());
        String d = d(str, "managerNode", "characteristic.managerIdentifierList");
        if (AwarenessRequest.MessageType.DISCONNECT.equals(d)) {
            LogUtil.b("smartlife_IntellLife", "removeServiceCharacterValue phoneSerialNumberList profile disconnect!");
            return;
        }
        String c = dew.c(d, e);
        String d2 = d(str, "managerNode", "characteristic.managerIdentifierList2");
        if (AwarenessRequest.MessageType.DISCONNECT.equals(d2)) {
            LogUtil.b("smartlife_IntellLife", "removeServiceCharacterValue phoneMacAddressList profile disconnect!");
            return;
        }
        String c2 = dew.c(d2, str2);
        if (d.equalsIgnoreCase(c) && d2.equalsIgnoreCase(c2)) {
            LogUtil.h("smartlife_IntellLife", "removeServiceCharacterValue do nothing");
            return;
        }
        Map<String, Object> profile = serviceCharacteristics.getProfile();
        profile.put("characteristic.managerIdentifierList", c);
        profile.put("characteristic.managerIdentifierList2", c2);
        LogUtil.c("smartlife_IntellLife", "removeServiceCharacterValue characteristics ", profile.toString());
        d(str, "managerNode", dew.d(profile));
    }

    public void e(Map<String, dep> map, List<String> list) {
        b(map, new HashMap(10), list);
    }

    private void b(Map<String, DeviceProfile> map, Map<String, dep> map2, String str) {
        Iterator<Map.Entry<String, DeviceProfile>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            String key = it.next().getKey();
            if (!"local".equals(c(key))) {
                LogUtil.h("smartlife_IntellLife", "checkCloudDevices device is not local device");
            } else if (map2.get(key) == null) {
                a(key, "local", str);
            }
        }
    }

    private void d(Map<String, dep> map, Map<String, DeviceProfile> map2, String str) {
        for (Map.Entry<String, dep> entry : map.entrySet()) {
            String key = entry.getKey();
            dep value = entry.getValue();
            Map<String, Object> a2 = value.a();
            if (map2.get(key) != null) {
                d(entry, a2, str);
            } else {
                d(value, a2, str);
            }
        }
    }

    private void d(Map.Entry<String, dep> entry, Map<String, Object> map, String str) {
        String key = entry.getKey();
        dep value = entry.getValue();
        DeviceProfile c = value.c();
        LogUtil.a("smartlife_IntellLife", "localDevice exist in cloud");
        if (!a(key, "managerNode")) {
            a(dew.d(c, "managerNode", dew.c("managerNode")), map, str);
        }
        if (map == null || map.get("type") == "cloud") {
            Object[] objArr = new Object[2];
            objArr[0] = "checkLocalDevices: local device in cloud , but local deviceInfo type is ";
            objArr[1] = map == null ? Constants.NULL : map.get("type");
            LogUtil.h("smartlife_IntellLife", objArr);
            return;
        }
        if (c.getProfile().get("model") == null) {
            LogUtil.h("smartlife_IntellLife", "checkLocalDevices: not Matching device from modeList");
        }
        if (value.c().getProfile().get("udid") instanceof String) {
            d((String) value.c().getProfile().get("udid"), value.a(), str);
        }
    }

    private void d(dep depVar, Map<String, Object> map, String str) {
        if (map.get("type") == "local") {
            LogUtil.c("smartlife_IntellLife", "localDevice not exist in cloud, add advice");
            a(depVar, str);
        } else {
            LogUtil.h("smartlife_IntellLife", " local Device is not bluetooth device");
        }
    }

    private boolean a(String str, String str2) {
        LogUtil.a("smartlife_IntellLife", "checkDeviceServiceProfileIfExist ");
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            LogUtil.h("smartlife_IntellLife", "checkDeviceServiceProfileIfExist illegal arguments!");
            return false;
        }
        List<ServiceProfile> servicesOfDevice = ProfileManager.INSTANCE.getServicesOfDevice(str, true, Arrays.asList(ProfileClient.CLOUD_LOCAL_DOMAIN));
        if (koq.b(servicesOfDevice)) {
            LogUtil.h("smartlife_IntellLife", "checkDeviceServiceProfileIfExist profileList is empty");
            return false;
        }
        for (ServiceProfile serviceProfile : servicesOfDevice) {
            LogUtil.c("smartlife_IntellLife", "checkDeviceServiceProfileIfExist serviceProfile id", serviceProfile.getId());
            if (str2.equals(serviceProfile.getId())) {
                return true;
            }
        }
        return false;
    }

    private void d(String str, Map<String, Object> map, String str2) {
        LogUtil.a("smartlife_IntellLife", "putManageNodeCharacteristics. ");
        String e = dew.e(dew.b());
        String d = d(str, "managerNode", "characteristic.managerIdentifierList");
        String d2 = d(str, "managerNode", "characteristic.managerIdentifierList2");
        if (AwarenessRequest.MessageType.DISCONNECT.equals(d) || AwarenessRequest.MessageType.DISCONNECT.equals(d2)) {
            LogUtil.b("smartlife_IntellLife", "putManageNodeCharacteristics profile disconnect!");
            return;
        }
        String d3 = dew.d(d, e);
        String d4 = dew.d(d2, str2);
        if (d3 == null) {
            LogUtil.h("smartlife_IntellLife", " putManageNodeCharacteristics serialNumberListNew is null");
            d3 = "";
        }
        if (d4 == null) {
            LogUtil.h("smartlife_IntellLife", " putManageNodeCharacteristics phoneMacAddressListNew is null");
        } else {
            if (d3.equalsIgnoreCase(d) && d4.equalsIgnoreCase(d2)) {
                return;
            }
            LogUtil.a("smartlife_IntellLife", " update phoneSerialNumberListNew or phoneMacAddressListNew");
            d(str, "managerNode", dew.d(map, new String[]{d3, d4}));
        }
    }

    private void b(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("smartlife_IntellLife", "deleteDeviceById, deviceId is null");
            return;
        }
        List<DeviceProfile> devices = ProfileManager.INSTANCE.getDevices(true, Arrays.asList(ProfileClient.CLOUD_LOCAL_DOMAIN));
        if (devices == null) {
            LogUtil.h("smartlife_IntellLife", "deleteDeviceById deviceProfiles is null");
            return;
        }
        Iterator<DeviceProfile> it = devices.iterator();
        while (it.hasNext()) {
            String id = it.next().getId();
            if (str.equalsIgnoreCase(id)) {
                d(id);
                LogUtil.a("smartlife_IntellLife", "deleteDeviceById deleteDevice : ", Boolean.valueOf(ProfileManager.INSTANCE.deleteDevice(id)));
                return;
            }
        }
    }

    private void d(String str) {
        List<ServiceProfile> servicesOfDevice = ProfileManager.INSTANCE.getServicesOfDevice(str, true, Arrays.asList(ProfileClient.CLOUD_LOCAL_DOMAIN));
        if (servicesOfDevice == null || servicesOfDevice.isEmpty()) {
            LogUtil.h("smartlife_IntellLife", "deleteAllServices deleteDevice success:", Boolean.valueOf(ProfileManager.INSTANCE.deleteDevice(str)));
            return;
        }
        Iterator<ServiceProfile> it = servicesOfDevice.iterator();
        while (it.hasNext()) {
            String id = it.next().getId();
            LogUtil.c("smartlife_IntellLife", " deleteAllServices serviceId:", id);
            b(str, id);
            LogUtil.c("smartlife_IntellLife", "deleteAllServices deleteDevice success:", Boolean.valueOf(ProfileManager.INSTANCE.deleteServiceOfDevice(str, id)));
        }
    }

    private void b(String str, String str2) {
        ServiceCharacteristicProfile serviceCharacteristics = ProfileManager.INSTANCE.getServiceCharacteristics(str, str2, true, Arrays.asList(ProfileClient.CLOUD_LOCAL_DOMAIN));
        if (serviceCharacteristics == null) {
            LogUtil.h("smartlife_IntellLife", "characteristicProfile is empty");
            return;
        }
        Map<String, Object> profile = serviceCharacteristics.getProfile();
        if (profile == null || profile.isEmpty()) {
            LogUtil.h("smartlife_IntellLife", "deleteAllCharacteristic profile map is empty");
            return;
        }
        Iterator<Map.Entry<String, Object>> it = profile.entrySet().iterator();
        while (it.hasNext()) {
            String key = it.next().getKey();
            LogUtil.c("smartlife_IntellLife", " deleteAllCharacteristic character key:", key);
            LogUtil.c("smartlife_IntellLife", " deleteAllCharacteristic is success:", Boolean.valueOf(ProfileManager.INSTANCE.deleteServiceCharacteristic(str, str2, key)));
        }
    }

    private String c(String str) {
        return d(str, "managerNode", "type");
    }

    private String d(String str, String str2, String str3) {
        LogUtil.c("smartlife_IntellLife", "getServiceCharacteristics device id, ", str);
        if (koq.b(ProfileManager.INSTANCE.getServicesOfDevice(str, true, Arrays.asList(ProfileClient.CLOUD_LOCAL_DOMAIN)))) {
            LogUtil.h("smartlife_IntellLife", "getServiceCharacteristics has not service");
        }
        ServiceCharacteristicProfile serviceCharacteristics = ProfileManager.INSTANCE.getServiceCharacteristics(str, str2, true, Arrays.asList(ProfileClient.CLOUD_LOCAL_DOMAIN));
        if (serviceCharacteristics == null) {
            LogUtil.h("smartlife_IntellLife", "serviceCharacteristicProfiles is null");
            return "";
        }
        String valueOf = String.valueOf(serviceCharacteristics.getProfile().get(str3));
        LogUtil.c("smartlife_IntellLife", "getServiceCharacteristics serviceId ", str2, " key ", str3, " value ", valueOf);
        return valueOf;
    }
}
