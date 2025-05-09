package defpackage;

import android.bluetooth.BluetoothAdapter;
import android.content.ContentValues;
import android.text.TextUtils;
import com.huawei.featureuserprofile.route.hwgpxmodel.Wpt;
import com.huawei.health.device.api.DeviceDataBaseHelperApi;
import com.huawei.health.device.api.DeviceManagerApi;
import com.huawei.health.device.api.MeasureKitManagerApi;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.device.open.BleMeasureController;
import com.huawei.health.device.open.MeasureController;
import com.huawei.health.device.open.MeasureKit;
import com.huawei.health.ecologydevice.manager.ResourceManager;
import com.huawei.health.ecologydevice.open.HdpMeasureController;
import com.huawei.health.ecologydevice.ui.measure.fragment.utils.BrDevice;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.db.bean.EventMonitorRecord;
import health.compact.a.CommonUtil;
import health.compact.a.Services;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

/* loaded from: classes3.dex */
public final class cwu {
    public ContentValues QO_(String str, String str2, String str3, String str4) {
        String str5;
        dcz d = ResourceManager.e().d(str);
        if (d == null) {
            LogUtil.h("EcologyDeviceManager", "productInfo is null");
            return null;
        }
        String s = d.s();
        MeasureKit healthDeviceKit = ((MeasureKitManagerApi) Services.c("PluginDevice", MeasureKitManagerApi.class)).getHealthDeviceKit(s);
        ContentValues contentValues = new ContentValues();
        contentValues.put("kind", c(d, healthDeviceKit));
        contentValues.put(Wpt.MODE, Integer.valueOf(d(d, healthDeviceKit)));
        boolean a2 = a(d, healthDeviceKit);
        contentValues.put("auto", Integer.valueOf(a2 ? 1 : 0));
        contentValues.put("uniqueId", str2);
        contentValues.put("sn", "");
        if (!TextUtils.isEmpty(str3) || d == null || d.n() == null) {
            str5 = str3;
        } else {
            String e = dks.e(str, d.n().b());
            LogUtil.h("EcologyDeviceManager", "get deviceName from ResourceManager deviceName = ", e);
            str5 = e;
        }
        contentValues.put("name", str5);
        contentValues.put("productId", str);
        contentValues.put(EventMonitorRecord.ADD_TIME, str4);
        contentValues.put("kitUuid", s);
        LogUtil.a("EcologyDeviceManager", "saveDevice deviceInfo : deviceKind is ", c(d, healthDeviceKit), ", deviceMode is ", Integer.valueOf(d(d, healthDeviceKit)), ", auto is ", Integer.valueOf(a2 ? 1 : 0), ", uniqueId is ", CommonUtil.l(str2), ", serialNumber is ", CommonUtil.l(""), ", productId is", CommonUtil.l(str), ", kitUuid is ", s, ", deviceName is ", str5);
        return contentValues;
    }

    private String c(dcz dczVar, MeasureKit measureKit) {
        String name = HealthDevice.HealthDeviceKind.HDK_UNKNOWN.name();
        if (dczVar != null && "4bfc5a27-f2b9-4c41-bd9b-a4a2a18f752c".equals(dczVar.t())) {
            return "SMART_HEADPHONES";
        }
        if (measureKit != null && measureKit.getHealthDataKind() != null) {
            return measureKit.getHealthDataKind().name();
        }
        if (dczVar != null && dczVar.l() != null) {
            return dczVar.l().name();
        }
        LogUtil.h("EcologyDeviceManager", "can not determine device kind, set defualt");
        return name;
    }

    private int d(dcz dczVar, MeasureKit measureKit) {
        if (measureKit != null) {
            return e(measureKit);
        }
        if (dczVar != null) {
            return dczVar.x().c();
        }
        LogUtil.h("EcologyDeviceManager", "can not determine connect mode, set default connect mode");
        return 0;
    }

    private int e(MeasureKit measureKit) {
        MeasureController backgroundController = measureKit.getBackgroundController();
        if (backgroundController == null) {
            backgroundController = measureKit.getMeasureController();
        }
        if (backgroundController == null) {
            return 0;
        }
        if (backgroundController instanceof BleMeasureController) {
            return 1;
        }
        return backgroundController instanceof HdpMeasureController ? 2 : 4;
    }

    private boolean a(dcz dczVar, MeasureKit measureKit) {
        return ((dczVar != null && "10".equals(dczVar.p())) || measureKit == null || measureKit.getBackgroundController() == null) ? false : true;
    }

    public void d(String str, String str2, String str3, String str4) {
        dcz d = ResourceManager.e().d(str3);
        String e = dks.e(str3, d == null ? "" : d.n().b());
        ContentValues deviceInfoByUniqueId = ((DeviceManagerApi) Services.c("PluginDevice", DeviceManagerApi.class)).getDeviceInfoByUniqueId(str);
        if (deviceInfoByUniqueId == null) {
            LogUtil.a("EcologyDeviceManager", "saveDevice");
            e(str, e, str2, str3, str4, String.valueOf(System.currentTimeMillis()));
            dks.d();
        } else {
            if (dij.Vb_(deviceInfoByUniqueId)) {
                LogUtil.a("EcologyDeviceManager", "updateAddTime");
                b(str, str2);
                dks.d();
                return;
            }
            LogUtil.a("EcologyDeviceManager", "All Right.");
        }
    }

    public void b(String str, String str2, String str3) {
        dcz d = ResourceManager.e().d(str3);
        String e = dks.e(str3, d == null ? "" : d.n().b());
        BrDevice brDevice = new BrDevice();
        brDevice.setUniqueId(str);
        brDevice.setMacAddress(str);
        brDevice.setDeviceName(e);
        ((DeviceManagerApi) Services.c("PluginDevice", DeviceManagerApi.class)).registerDeviceInfo("4bfc5a27-f2b9-4c41-bd9b-a4a2a18f752c", brDevice, str2);
    }

    public void e(String str, String str2, String str3, String str4, String str5, String str6) {
        if (!BluetoothAdapter.checkBluetoothAddress(str)) {
            ReleaseLogUtil.d("R_EcologyDeviceManager", "Incorrect MAC address format:", CommonUtil.l(str));
            return;
        }
        ContentValues QO_ = new cwu().QO_(str4, str, str2, str6);
        if (QO_ == null) {
            LogUtil.h("EcologyDeviceManager", "deviceInfo is null");
            return;
        }
        QO_.put("sn", str3);
        QO_.put("mDeviceId", str5);
        LogUtil.a("EcologyDeviceManager", "uniqueId:", CommonUtil.l(str), ", sn:", CommonUtil.l(str3), ", deviceName:", str2, ", addTime:", str6);
        if (((DeviceDataBaseHelperApi) Services.c("PluginDevice", DeviceDataBaseHelperApi.class)).insert(QO_) == -1) {
            LogUtil.h("EcologyDeviceManager", "fail to insert device data base");
        }
    }

    public void b(String str, String str2) {
        LogUtil.a("EcologyDeviceManager", "enter updateAddTime");
        ContentValues contentValues = new ContentValues();
        contentValues.put(EventMonitorRecord.ADD_TIME, Long.valueOf(System.currentTimeMillis()));
        contentValues.put("sn", str2);
        if (((DeviceDataBaseHelperApi) Services.c("PluginDevice", DeviceDataBaseHelperApi.class)).update(contentValues, "uniqueId = ?", new String[]{str}) <= 0) {
            LogUtil.h("EcologyDeviceManager", "fail to update addTime and sn");
        }
    }
}
