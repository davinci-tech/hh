package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.health.device.manager.DeviceCloudSharePreferencesManager;
import com.huawei.hwcloudmodel.callback.ICloudOperationResult;
import com.huawei.hwcloudmodel.model.unite.DeviceServiceInfo;
import com.huawei.hwcloudmodel.model.unite.WifiDeviceServiceInfoReq;
import com.huawei.hwcloudmodel.model.unite.WifiDeviceServiceInfoRsp;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes3.dex */
public class coz {
    public static void d(String str, String str2, Context context, String str3) {
        LogUtil.a("OtaUpdateUtil", "setDeviceCurrentVersion,version：", str2, ",tag:", str);
        if (context == null) {
            LogUtil.h("OtaUpdateUtil", "setDeviceCurrentVersion context is null");
            return;
        }
        StorageParams storageParams = new StorageParams();
        storageParams.d(0);
        SharedPreferenceManager.e(context, String.valueOf(1003), "update_key_scale_device_current_version" + str3, str2, storageParams);
    }

    public static void a(String str, String str2, Context context, String str3) {
        LogUtil.a("OtaUpdateUtil", "setWeightDeviceNewVersion,version：", str2, ",tag:", str);
        if (context == null) {
            LogUtil.h("OtaUpdateUtil", "setWeightDeviceNewVersion context is null");
            return;
        }
        StorageParams storageParams = new StorageParams();
        storageParams.d(0);
        SharedPreferenceManager.e(context, String.valueOf(1003), "update_key_scale_device_new_version" + str3, str2, storageParams);
    }

    public static boolean b(Context context, String str) {
        String a2 = a(context, str);
        LogUtil.a("OtaUpdateUtil", "haveWeightDeviceNewVersion currentVersion: ", a2);
        if (TextUtils.isEmpty(a2)) {
            return false;
        }
        String e = e(context, str);
        LogUtil.a("OtaUpdateUtil", "haveWeightDeviceNewVersion newVersion: ", e);
        if (TextUtils.isEmpty(e)) {
            return false;
        }
        return !a2.equals(e);
    }

    public static String a(Context context, String str) {
        if (context == null) {
            LogUtil.h("OtaUpdateUtil", "getDeviceCurrentVersion context is null");
            return "";
        }
        String b = SharedPreferenceManager.b(context, String.valueOf(1003), "update_key_scale_device_current_version" + str);
        LogUtil.a("OtaUpdateUtil", "getDeviceCurrentVersion,version :", b);
        return b;
    }

    public static String e(Context context, String str) {
        if (context == null) {
            LogUtil.h("OtaUpdateUtil", "getWeightDeviceNewVersion context is null");
            return "";
        }
        String b = SharedPreferenceManager.b(context, String.valueOf(1003), "update_key_scale_device_new_version" + str);
        LogUtil.a("OtaUpdateUtil", "getWeightDeviceNewVersion,version :", b);
        return b;
    }

    public static void b(Context context, boolean z, String str) {
        String str2 = "is_new_honor" + knl.d(str);
        LogUtil.a("OtaUpdateUtil", "setNewWeightHonor,isNewHonor ", Boolean.valueOf(z), " newHonorKey ", str2);
        StorageParams storageParams = new StorageParams();
        storageParams.d(0);
        SharedPreferenceManager.e(context, String.valueOf(1003), str2, "" + z, storageParams);
    }

    public static boolean d(Context context, String str) {
        String str2 = "is_new_honor" + knl.d(str);
        String b = SharedPreferenceManager.b(context, String.valueOf(1003), str2);
        boolean parseBoolean = Boolean.parseBoolean(b);
        LogUtil.a("OtaUpdateUtil", "isNewWeightHonor,isNewHonor ", b, " newHonorKey ", str2);
        return parseBoolean;
    }

    public static void e(String str, Context context, final ctk ctkVar, String str2) {
        if (ctkVar == null) {
            LogUtil.h(str, "getDeviceOtaStatus mWiFiDevice is null");
            return;
        }
        if (cqb.d().c(ctkVar, str2)) {
            LogUtil.h(str, "getDeviceOtaStatus is sub devices");
            return;
        }
        WifiDeviceServiceInfoReq wifiDeviceServiceInfoReq = new WifiDeviceServiceInfoReq();
        wifiDeviceServiceInfoReq.setDevId(ctkVar.b().a());
        wifiDeviceServiceInfoReq.setSid("devOtaInfo");
        jbs.a(context).b(wifiDeviceServiceInfoReq, new ICloudOperationResult() { // from class: cpf
            @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
            public final void operationResult(Object obj, String str3, boolean z) {
                coz.e(ctk.this, (WifiDeviceServiceInfoRsp) obj, str3, z);
            }
        });
    }

    static /* synthetic */ void e(ctk ctkVar, WifiDeviceServiceInfoRsp wifiDeviceServiceInfoRsp, String str, boolean z) {
        if (z) {
            d(wifiDeviceServiceInfoRsp, ctkVar);
        } else {
            cpw.d(false, "OtaUpdateUtil", "getDeviceOtaStatus isSuccess:", Boolean.valueOf(z), "|response:", wifiDeviceServiceInfoRsp);
        }
    }

    private static void d(WifiDeviceServiceInfoRsp wifiDeviceServiceInfoRsp, ctk ctkVar) {
        Iterator<DeviceServiceInfo> it;
        boolean z = false;
        if (wifiDeviceServiceInfoRsp == null || !koq.c(wifiDeviceServiceInfoRsp.getDeviceServiceInfo())) {
            cpw.d(false, "OtaUpdateUtil", "getDeviceOtaStatus response is empty");
            return;
        }
        if (ctkVar == null || ctkVar.b() == null) {
            LogUtil.h("OtaUpdateUtil", "setStatusInfo mWiFiDevice is null or deviceInfo is null");
            return;
        }
        Iterator<DeviceServiceInfo> it2 = wifiDeviceServiceInfoRsp.getDeviceServiceInfo().iterator();
        while (it2.hasNext()) {
            DeviceServiceInfo next = it2.next();
            cpw.a(z, "OtaUpdateUtil", "getDeviceOtaStatus device service info:", wifiDeviceServiceInfoRsp.getDeviceServiceInfo());
            Map<String, String> data = next.getData();
            Map<String, String> a2 = new DeviceCloudSharePreferencesManager(BaseApplication.getContext()).a(ctkVar.b().a());
            if (data == null || a2 == null) {
                it = it2;
                LogUtil.h("OtaUpdateUtil", "serviceInfoCloud = null or serviceInfoSp = null");
            } else {
                String str = data.get("fwCurVer");
                String str2 = data.get("fwNewVer");
                String str3 = a2.get("fwCurVer");
                String str4 = a2.get("fwNewVer");
                it = it2;
                LogUtil.a("OtaUpdateUtil", "curVersionCloud=", str, ", newVersionCloud=", str2, ", curVersionSp=", str3, ", newVersionSp=", str4);
                if (CommonUtil.d(str2, str3) < 0) {
                    LogUtil.h("OtaUpdateUtil", "newVersionCloud is smaller than newVersionSp, do not save");
                } else {
                    if (CommonUtil.d(str, str2) == 0) {
                        data.put("fwCurVer", str3);
                        data.put("fwNewVer", str4);
                    }
                    data.put("key_is_exist_new_version", a2.get("key_is_exist_new_version"));
                    csc.d().d(ctkVar.d(), data);
                }
            }
            it2 = it;
            z = false;
        }
    }
}
