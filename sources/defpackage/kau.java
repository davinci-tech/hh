package defpackage;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.huawei.datatype.DeviceCommand;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwbasemgr.HwBaseManager;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.phoneprocess.binder.ParserInterface;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.LocalBroadcast;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class kau extends HwBaseManager implements ParserInterface {

    /* renamed from: a, reason: collision with root package name */
    private static final Object f14239a = new Object();
    private static kau e;
    private IBaseResponseCallback c;
    private final Object d;

    private kau(Context context) {
        super(context);
        this.d = new Object();
    }

    public static kau b() {
        kau kauVar;
        synchronized (f14239a) {
            if (e == null) {
                e = new kau(BaseApplication.getContext());
            }
            kauVar = e;
        }
        return kauVar;
    }

    @Override // com.huawei.hwdevice.phoneprocess.binder.ParserInterface
    public void getResult(DeviceInfo deviceInfo, byte[] bArr) {
        if (bArr == null || bArr.length < 2) {
            LogUtil.h("HwDeviceAppMarketManager", "5.44 get wrong data.");
            return;
        }
        blt.d("HwDeviceAppMarketManager", bArr, "5.44 tlv: ");
        if (bArr[1] == 3) {
            c(bArr);
            LogUtil.a("HwDeviceAppMarketManager", "getResult sendDataToMainProcess ACTION_GET_INSTALL_APP_LIST");
            b("getInstallAppList", bArr, deviceInfo);
            return;
        }
        LogUtil.h("HwDeviceAppMarketManager", "no support");
    }

    private void b(String str, byte[] bArr, DeviceInfo deviceInfo) {
        jqi.a().setSwitchSettingToDb(str, cvx.d(bArr));
        if (deviceInfo != null) {
            jqi.a().setSwitchSettingToDb(jja.e(str), deviceInfo.getDeviceIdentify());
        } else {
            LogUtil.h("HwDeviceAppMarketManager", "sendDataToMainProcess deviceInfo is null");
        }
        Intent intent = new Intent();
        intent.setPackage(BaseApplication.getAppPackage());
        intent.setAction(jja.a(str));
        BaseApplication.getContext().sendBroadcast(intent, LocalBroadcast.c);
    }

    private void c(byte[] bArr) {
        String d = cvx.d(bArr);
        if (TextUtils.isEmpty(d) || d.length() <= 4) {
            LogUtil.h("HwDeviceAppMarketManager", "dataInfo is null or dataInfo length less than 4.");
            return;
        }
        try {
            cwe a2 = new cwl().a(d.substring(4));
            List<cwd> e2 = a2.e();
            List<cwe> a3 = a2.a();
            for (cwd cwdVar : e2) {
                if (jds.c(cwdVar.e(), 16) == 127) {
                    d(jds.c(cwdVar.c(), 16), "");
                    return;
                }
                LogUtil.h("HwDeviceAppMarketManager", "no support normal tlv.");
            }
            JSONArray jSONArray = new JSONArray();
            Iterator<cwe> it = a3.iterator();
            while (it.hasNext()) {
                Iterator<cwe> it2 = it.next().a().iterator();
                while (it2.hasNext()) {
                    Iterator<cwd> it3 = it2.next().e().iterator();
                    while (it3.hasNext()) {
                        e(jSONArray, it3.next());
                    }
                }
            }
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("appList", jSONArray);
            d(0, jSONObject.toString());
        } catch (cwg e3) {
            LogUtil.b("HwDeviceAppMarketManager", "dealAppList tlv exception: ", e3.getMessage());
            d(-1, "");
        } catch (JSONException unused) {
            LogUtil.b("HwDeviceAppMarketManager", "json exception.");
            d(-1, "");
        }
    }

    private void e(JSONArray jSONArray, cwd cwdVar) {
        if (jds.c(cwdVar.e(), 16) == 3) {
            jSONArray.put(cvx.e(cwdVar.c()));
        } else {
            LogUtil.h("HwDeviceAppMarketManager", "no support struct tlv.");
        }
    }

    private void d(int i, String str) {
        synchronized (this.d) {
            IBaseResponseCallback iBaseResponseCallback = this.c;
            if (iBaseResponseCallback != null) {
                iBaseResponseCallback.d(i, str);
            }
        }
        e();
    }

    private void e() {
        synchronized (this.d) {
            this.c = null;
        }
    }

    @Override // com.huawei.hwbasemgr.HwBaseManager
    public Integer getModuleId() {
        return 42;
    }

    public void e(IBaseResponseCallback iBaseResponseCallback) {
        DeviceInfo deviceInfo;
        if (iBaseResponseCallback == null) {
            LogUtil.h("HwDeviceAppMarketManager", "getDeviceAppNames callback is null");
            return;
        }
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_MAIN_DEVICES, null, "HwDeviceAppMarketManager");
        if (deviceList.size() > 0) {
            Iterator<DeviceInfo> it = deviceList.iterator();
            while (it.hasNext()) {
                deviceInfo = it.next();
                if (!cvt.c(deviceInfo.getProductType())) {
                    break;
                }
            }
        }
        deviceInfo = null;
        if (deviceInfo == null || deviceInfo.getDeviceConnectState() != 2) {
            LogUtil.h("HwDeviceAppMarketManager", "getDeviceAppNames get device info error");
            iBaseResponseCallback.d(300004, null);
        }
        synchronized (this.d) {
            this.c = iBaseResponseCallback;
        }
        c(deviceInfo);
    }

    private void c(DeviceInfo deviceInfo) {
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(42);
        deviceCommand.setCommandID(3);
        StringBuilder sb = new StringBuilder(16);
        sb.append(cvx.e(129));
        sb.append(cvx.e(0));
        deviceCommand.setDataLen(sb.length() / 2);
        deviceCommand.setDataContent(cvx.a(sb.toString()));
        if (deviceInfo != null) {
            deviceCommand.setmIdentify(deviceInfo.getDeviceIdentify());
        }
        blt.d("HwDeviceAppMarketManager", deviceCommand.getDataContent(), "sendGetDeviceAppNames command data is ");
        jsz.b(BaseApplication.getContext()).sendDeviceData(deviceCommand);
    }
}
