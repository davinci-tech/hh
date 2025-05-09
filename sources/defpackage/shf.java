package defpackage;

import android.graphics.Bitmap;
import android.text.TextUtils;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcloudmodel.callback.ICloudOperationResult;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.profile.profile.DeviceProfile;
import com.huawei.ui.thirdpartservice.interactors.callback.SupportDeviceResultCallback;
import health.compact.a.Services;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes7.dex */
public class shf {
    private Bitmap dXN_(DeviceInfo deviceInfo, int i) {
        cvc pluginInfoByUuid = ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).getPluginInfoByUuid(cun.c().getDeviceInfoUx(deviceInfo.getProductType(), "DeviceService").ad());
        return ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).loadImageByImageName(pluginInfoByUuid, cwf.a(pluginInfoByUuid, i, deviceInfo));
    }

    public Bitmap dXO_(DeviceInfo deviceInfo) {
        return dXN_(deviceInfo, 2);
    }

    public List<DeviceInfo> c() {
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.ALL_DEVICES, null, "DeviceService");
        return deviceList != null ? deviceList : new ArrayList();
    }

    public void b(ICloudOperationResult<List<DeviceInfo>> iCloudOperationResult) {
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.ALL_DEVICES, null, "DeviceService");
        if (deviceList == null) {
            deviceList = new ArrayList<>();
        }
        List<DeviceInfo> a2 = a(deviceList);
        LogUtil.a("DeviceService", "crippledList size :", Integer.valueOf(a2.size()));
        if (a2.isEmpty()) {
            iCloudOperationResult.operationResult(deviceList, "", true);
        } else {
            b(deviceList, iCloudOperationResult);
        }
    }

    private List<DeviceInfo> a(List<DeviceInfo> list) {
        ArrayList arrayList = new ArrayList();
        for (DeviceInfo deviceInfo : list) {
            if (TextUtils.isEmpty(deviceInfo.getHiLinkDeviceId())) {
                arrayList.add(deviceInfo);
            }
        }
        return arrayList;
    }

    private void b(final List<DeviceInfo> list, final ICloudOperationResult<List<DeviceInfo>> iCloudOperationResult) {
        cun.c().getDevicesFromCloud("DownloadCloudDeviceResource", new IBaseResponseCallback() { // from class: shi
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                shf.this.e(list, iCloudOperationResult, i, obj);
            }
        });
    }

    /* synthetic */ void e(List list, ICloudOperationResult iCloudOperationResult, int i, Object obj) {
        LogUtil.a("DeviceService", "getDevicesFromCloud: errorCode =", Integer.valueOf(i), " objData = ", obj);
        Iterator it = list.iterator();
        while (it.hasNext()) {
            DeviceInfo deviceInfo = (DeviceInfo) it.next();
            if (TextUtils.isEmpty(deviceInfo.getHiLinkDeviceId())) {
                if (i == 0 && koq.e(obj, DeviceProfile.class)) {
                    List<DeviceProfile> list2 = (List) obj;
                    LogUtil.a("DeviceService", "getDevicesFromCloud list size():", Integer.valueOf(list2.size()));
                    for (DeviceProfile deviceProfile : list2) {
                        String d = d("prodId", deviceProfile.getProfile());
                        String d2 = d("udid", deviceProfile.getProfile());
                        if (deviceInfo.getDeviceUdid().trim().equalsIgnoreCase(d2.trim())) {
                            LogUtil.c("DeviceService", "cloud:deviceUdid = ", d2, " prodId = ", d);
                            LogUtil.c("DeviceService", "crippled:deviceUdid = ", deviceInfo.getDeviceUdid());
                            LogUtil.c("DeviceService", deviceInfo.getDeviceName(), " supplementsHiLinkDeviceId :setHiLinkDeviceId  prodId = ", d);
                            deviceInfo.setHiLinkDeviceId(d);
                        }
                    }
                }
                if (TextUtils.isEmpty(deviceInfo.getHiLinkDeviceId())) {
                    ReleaseLogUtil.e("DeviceService", "deviceInfo name", deviceInfo.getDeviceName());
                    cvz.a(deviceInfo);
                }
            }
        }
        iCloudOperationResult.operationResult(list, "", true);
    }

    private String d(String str, Map<String, Object> map) {
        Object obj = map.get(str);
        return obj instanceof String ? (String) obj : "";
    }

    public List<DeviceInfo> a() {
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_DEVICES, null, "DeviceService");
        return deviceList != null ? deviceList : new ArrayList();
    }

    public void c(final SupportDeviceResultCallback supportDeviceResultCallback) {
        LogUtil.a("DeviceService", "enter requestBindDevices ");
        new shm().a(new ICloudOperationResult() { // from class: shj
            @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
            public final void operationResult(Object obj, String str, boolean z) {
                shf.this.c(supportDeviceResultCallback, (skb) obj, str, z);
            }
        });
    }

    /* synthetic */ void c(SupportDeviceResultCallback supportDeviceResultCallback, skb skbVar, String str, boolean z) {
        if (z) {
            LogUtil.a("DeviceService", "requestBindDevices onSuccess :", str);
            supportDeviceResultCallback.obtainSupportDeviceList(skbVar.getDevices());
            supportDeviceResultCallback.obtainBindDeviceList(e(skbVar));
        } else {
            LogUtil.b("DeviceService", "requestBindDevices onFailure :", str);
            supportDeviceResultCallback.onError(-1, str);
        }
    }

    private List<sjy> e(skb skbVar) {
        List<DeviceInfo> c = c();
        List<sjy> bindDevices = skbVar.getBindDevices();
        if (koq.b(c)) {
            LogUtil.c("DeviceService", "getBindDevices return original device list because local is empty.");
            return bindDevices;
        }
        for (sjy sjyVar : bindDevices) {
            LogUtil.c("DeviceService", "getBindDevices append device info: [" + sjyVar.getDeviceName() + " , " + sjyVar.getDeviceCode() + "]");
            e(c, sjyVar);
        }
        return bindDevices;
    }

    private void e(List<DeviceInfo> list, sjy sjyVar) {
        for (DeviceInfo deviceInfo : list) {
            LogUtil.c("DeviceService", "Bind device append info start = [" + sjyVar.getDeviceName() + " , " + sjyVar.getIdentify() + "]");
            if (d(sjyVar, deviceInfo)) {
                sjyVar.setIcon(dXN_(deviceInfo, 1));
                sjyVar.setDeviceName(deviceInfo.getDeviceName());
                sjyVar.setIdentify(deviceInfo.getDeviceIdentify());
            }
            LogUtil.c("DeviceService", "Bind device append info end = [" + sjyVar.getDeviceName() + " , " + sjyVar.getIdentify() + "]");
        }
    }

    private boolean d(sjy sjyVar, DeviceInfo deviceInfo) {
        String uniqueId = sjyVar.getUniqueId();
        StringBuilder sb = new StringBuilder();
        sb.append(deviceInfo.getUnConvertedUdid());
        sb.append("#ANDROID21");
        return (uniqueId.equalsIgnoreCase(sb.toString()) || sjyVar.getUniqueId().equalsIgnoreCase(deviceInfo.getUnConvertedUdid())) && sjyVar.getProductId() == cun.c().getDeviceInfoUx(deviceInfo.getProductType(), "DeviceService").o();
    }

    public List<sjy> c(List<DeviceInfo> list) {
        ArrayList arrayList = new ArrayList();
        for (DeviceInfo deviceInfo : list) {
            sjy sjyVar = new sjy();
            boolean z = true;
            sjyVar.setIcon(dXN_(deviceInfo, 1));
            sjyVar.setDeviceName(deviceInfo.getDeviceName());
            sjyVar.setIdentify(deviceInfo.getDeviceIdentify());
            if (deviceInfo.getDeviceConnectState() != 2) {
                z = false;
            }
            sjyVar.setConnected(z);
            arrayList.add(sjyVar);
        }
        return arrayList;
    }
}
