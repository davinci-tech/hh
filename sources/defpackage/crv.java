package defpackage;

import android.content.ContentValues;
import android.text.TextUtils;
import com.huawei.health.device.manager.ProfileManager;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hiai.awareness.client.AwarenessRequest;
import com.huawei.hms.network.embedded.w9;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdataaccessmodel.utils.StorageDataCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.Constants;
import com.huawei.profile.client.profile.ProfileClient;
import com.huawei.profile.profile.DeviceProfile;
import com.huawei.profile.profile.ServiceCharacteristicProfile;
import com.huawei.profile.profile.ServiceProfile;
import health.compact.a.KeyValDbManager;
import health.compact.a.StorageResult;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class crv {
    public boolean c(DeviceInfo deviceInfo) {
        LogUtil.a("Weight_IntellLife", "add device to profile start");
        if (!a(cry.d(deviceInfo))) {
            ReleaseLogUtil.d("R_Weight_IntellLife", "fail to put device Profile");
            return false;
        }
        b(deviceInfo);
        if (!a(deviceInfo)) {
            ReleaseLogUtil.d("R_Weight_IntellLife", "fail to put device Profile");
            return false;
        }
        boolean d = d(deviceInfo);
        if (d) {
            StorageDataCallback storageDataCallback = new StorageDataCallback() { // from class: crv.3
                @Override // com.huawei.hwdataaccessmodel.utils.StorageDataCallback
                public void onProcessed(StorageResult storageResult) {
                    Object[] objArr = new Object[2];
                    objArr[0] = "set device name to cp, reseult = ";
                    objArr[1] = storageResult == null ? Constants.NULL : storageResult.e();
                    LogUtil.a("Weight_IntellLife", objArr);
                }
            };
            String d2 = knl.d(deviceInfo.getDeviceIdentify());
            LogUtil.a("Weight_IntellLife", "add device success, save device name to cp unique id ", d2);
            ReleaseLogUtil.e("R_Weight_IntellLife", "deviceName ", deviceInfo.getDeviceName());
            KeyValDbManager.b(BaseApplication.getContext()).d(d2, deviceInfo.getDeviceName(), storageDataCallback);
        }
        return d;
    }

    private void b(DeviceInfo deviceInfo) {
        String d = dew.d(deviceInfo.getUuid());
        LogUtil.a("Weight_IntellLife", "enter get wiseDeviceId, result = ", cpw.d(d));
        ContentValues contentValues = new ContentValues();
        contentValues.put("mDeviceId", d);
        if (cen.b().DU_("device", contentValues, "uniqueId= ?", new String[]{deviceInfo.getDeviceIdentify()}) == -1) {
            LogUtil.h("Weight_IntellLife", "fail to udpate wisedeviceid");
        }
    }

    public void a(String str, String str2) {
        LogUtil.a("Weight_IntellLife", "removeDeviceToProfile start deviceId , type ", str2);
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            LogUtil.h("Weight_IntellLife", "removeDeviceToProfile illegal argments");
            return;
        }
        String b = b(str);
        if (!str2.equals(b)) {
            LogUtil.h("Weight_IntellLife", "removeDeviceToProfile current device Type is not target type, current type", b);
            return;
        }
        c(str);
        String d = d(str, "managerNode", "characteristic.managerIdentifierList2");
        if (!cry.b(d) || AwarenessRequest.MessageType.DISCONNECT.equals(d)) {
            return;
        }
        a(str);
    }

    private boolean a(DeviceProfile deviceProfile) {
        if (deviceProfile == null) {
            LogUtil.h("Weight_IntellLife", "putDeviceProfile profile is null");
            return false;
        }
        boolean putDevice = ProfileManager.INSTANCE.putDevice(deviceProfile);
        LogUtil.a("Weight_IntellLife", "putDeviceProfile is success: ", Boolean.valueOf(putDevice));
        return putDevice;
    }

    private boolean e(ServiceProfile serviceProfile) {
        if (!cry.d(serviceProfile)) {
            return false;
        }
        boolean putServiceOfDevice = ProfileManager.INSTANCE.putServiceOfDevice(serviceProfile.getDeviceId(), serviceProfile);
        LogUtil.a("Weight_IntellLife", "putServiceProfile ", "serviceId ", serviceProfile.getId(), "is success ", Boolean.valueOf(putServiceOfDevice));
        return putServiceOfDevice;
    }

    private boolean c(String str, String str2, ServiceCharacteristicProfile serviceCharacteristicProfile) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            LogUtil.h("Weight_IntellLife", "putServiceCharacteristic deviceId or serviceId is null");
            return false;
        }
        boolean putServiceCharacteristic = ProfileManager.INSTANCE.putServiceCharacteristic(str, str2, serviceCharacteristicProfile);
        LogUtil.a("Weight_IntellLife", "putServiceCharacteristic put deviceId = ", cpw.a(str), " serviceId = ", str2, " is success ", Boolean.valueOf(putServiceCharacteristic));
        return putServiceCharacteristic;
    }

    private boolean a(DeviceInfo deviceInfo) {
        if (e(cry.d(deviceInfo, w9.h, cry.e(w9.h)))) {
            return c(deviceInfo.getUuid(), w9.h, cry.c(cry.d(deviceInfo, w9.h)));
        }
        LogUtil.h("Weight_IntellLife", "putConnectionService fail");
        return false;
    }

    private boolean d(DeviceInfo deviceInfo) {
        LogUtil.a("Weight_IntellLife", "putManagerNodeProfile start");
        if (e(cry.d(deviceInfo, "managerNode", cry.e("managerNode")))) {
            return e(deviceInfo);
        }
        LogUtil.h("Weight_IntellLife", "put manageNode service fail");
        return false;
    }

    private boolean e(DeviceInfo deviceInfo) {
        String c = cry.c(cry.a());
        String c2 = cry.c();
        String d = d(deviceInfo.getUuid(), "managerNode", "characteristic.managerIdentifierList");
        String d2 = d(deviceInfo.getUuid(), "managerNode", "characteristic.managerIdentifierList2");
        if (AwarenessRequest.MessageType.DISCONNECT.equals(d) || AwarenessRequest.MessageType.DISCONNECT.equals(d2)) {
            LogUtil.b("Weight_IntellLife", "putManageNodeCharactistics profile disconnect!");
            return false;
        }
        String c3 = cry.c(d, c);
        String c4 = cry.c(d2, c2);
        if (d.equalsIgnoreCase(c3) && d2.equalsIgnoreCase(c4)) {
            LogUtil.h("Weight_IntellLife", "not need to update manageNode");
        }
        boolean c5 = c(deviceInfo.getUuid(), "managerNode", cry.e(deviceInfo, new String[]{c3, c4}));
        if (c3 != null && c4 != null) {
            LogUtil.a("Weight_IntellLife", "putManagerNodeProfile phoneSerialNumberList'size ", Integer.valueOf(d.length()), " new size ", Integer.valueOf(c3.length()), " phoneAddress's size ", Integer.valueOf(d2.length()), " new size ", Integer.valueOf(c4.length()));
        }
        return c5;
    }

    public void c(String str) {
        LogUtil.a("Weight_IntellLife", "removeDeviceToProfile start deviceId");
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("Weight_IntellLife", "removeDeviceToProfile deviceId is null");
            return;
        }
        ServiceCharacteristicProfile serviceCharacteristics = ProfileManager.INSTANCE.getServiceCharacteristics(str, "managerNode", true, Arrays.asList(ProfileClient.CLOUD_LOCAL_DOMAIN));
        if (serviceCharacteristics == null) {
            LogUtil.h("Weight_IntellLife", "removeServiceCharacterValue characteristicProfiles is null");
            return;
        }
        String c = cry.c(cry.a());
        String d = d(str, "managerNode", "characteristic.managerIdentifierList");
        if (AwarenessRequest.MessageType.DISCONNECT.equals(d)) {
            LogUtil.b("Weight_IntellLife", "removeServiceCharacterValue phoneSerialNumberList profile disconnect!");
            return;
        }
        String d2 = cry.d(d, c);
        String c2 = cry.c();
        String d3 = d(str, "managerNode", "characteristic.managerIdentifierList2");
        if (AwarenessRequest.MessageType.DISCONNECT.equals(d3)) {
            LogUtil.b("Weight_IntellLife", "removeServiceCharacterValue phoneMacAddressList profile disconnect!");
            return;
        }
        String d4 = cry.d(d3, c2);
        if (d.equalsIgnoreCase(d2) && d3.equalsIgnoreCase(d4)) {
            LogUtil.h("Weight_IntellLife", "removeServiceCharacterValue do nothing");
            return;
        }
        Map<String, Object> profile = serviceCharacteristics.getProfile();
        profile.put("characteristic.managerIdentifierList", d2);
        profile.put("characteristic.managerIdentifierList2", d4);
        LogUtil.c("Weight_IntellLife", "removeServiceCharacterValue characteristics ", profile.toString());
        c(str, "managerNode", cry.c(profile));
    }

    private void a(String str) {
        LogUtil.a("Weight_IntellLife", "deleteDeviceById start");
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("Weight_IntellLife", "deleteDeviceById deviceId is null");
            return;
        }
        List<DeviceProfile> devices = ProfileManager.INSTANCE.getDevices(true, Arrays.asList(ProfileClient.CLOUD_LOCAL_DOMAIN));
        if (devices == null) {
            LogUtil.h("Weight_IntellLife", "deleteDeviceById deviceProfiles is null");
            return;
        }
        Iterator<DeviceProfile> it = devices.iterator();
        while (it.hasNext()) {
            String id = it.next().getId();
            if (str.equalsIgnoreCase(id)) {
                d(id);
                LogUtil.a("Weight_IntellLife", "deleteDeviceById deleteDevice : ", Boolean.valueOf(ProfileManager.INSTANCE.deleteDevice(id)));
                return;
            }
        }
    }

    private void d(String str) {
        List<ServiceProfile> servicesOfDevice = ProfileManager.INSTANCE.getServicesOfDevice(str, true, Arrays.asList(ProfileClient.CLOUD_LOCAL_DOMAIN));
        if (servicesOfDevice == null || servicesOfDevice.isEmpty()) {
            LogUtil.h("Weight_IntellLife", "deleteAllServices deleteDevice success:", Boolean.valueOf(ProfileManager.INSTANCE.deleteDevice(str)));
            return;
        }
        Iterator<ServiceProfile> it = servicesOfDevice.iterator();
        while (it.hasNext()) {
            String id = it.next().getId();
            LogUtil.c("Weight_IntellLife", " deleteAllServices serviceId:", id);
            b(str, id);
            LogUtil.c("Weight_IntellLife", "deleteAllServices deleteDevice success:", Boolean.valueOf(ProfileManager.INSTANCE.deleteServiceOfDevice(str, id)));
        }
    }

    private void b(String str, String str2) {
        ServiceCharacteristicProfile serviceCharacteristics = ProfileManager.INSTANCE.getServiceCharacteristics(str, str2, true, Arrays.asList(ProfileClient.CLOUD_LOCAL_DOMAIN));
        if (serviceCharacteristics == null) {
            LogUtil.h("Weight_IntellLife", "characteristicProfile is empty");
            return;
        }
        Map<String, Object> profile = serviceCharacteristics.getProfile();
        if (profile == null || profile.isEmpty()) {
            LogUtil.h("Weight_IntellLife", "deleteAllCharacter profile map is empty");
            return;
        }
        Iterator<Map.Entry<String, Object>> it = profile.entrySet().iterator();
        while (it.hasNext()) {
            String key = it.next().getKey();
            LogUtil.c("Weight_IntellLife", " deleteAllCharacter character key:", key);
            LogUtil.c("Weight_IntellLife", " deleteAllCharacter is success:", Boolean.valueOf(ProfileManager.INSTANCE.deleteServiceCharacteristic(str, str2, key)));
        }
    }

    private String b(String str) {
        return d(str, "managerNode", "type");
    }

    private String d(String str, String str2, String str3) {
        LogUtil.c("Weight_IntellLife", "getServiceCharacteristics device id ,", str);
        if (koq.b(ProfileManager.INSTANCE.getServicesOfDevice(str, true, Arrays.asList(ProfileClient.CLOUD_LOCAL_DOMAIN)))) {
            LogUtil.h("Weight_IntellLife", "getServiceCharacteristics has not service");
        }
        ServiceCharacteristicProfile serviceCharacteristics = ProfileManager.INSTANCE.getServiceCharacteristics(str, str2, true, Arrays.asList(ProfileClient.CLOUD_LOCAL_DOMAIN));
        if (serviceCharacteristics == null) {
            LogUtil.h("Weight_IntellLife", "serviceCharacteristicProfiles is null");
            return "";
        }
        String valueOf = String.valueOf(serviceCharacteristics.getProfile().get(str3));
        LogUtil.c("Weight_IntellLife", "getServiceCharacteristics serviceId ", str2, " key ", str3, " value ", valueOf);
        return valueOf;
    }
}
