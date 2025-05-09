package defpackage;

import android.bluetooth.BluetoothAdapter;
import android.content.ContentValues;
import android.text.TextUtils;
import com.huawei.health.device.api.PluginDeviceApi;
import com.huawei.health.deviceconnect.callback.SendCallback;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.ecologydevice.manager.ResourceManager;
import com.huawei.hihealth.dictionary.constants.ProductMapInfo;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.ble.BleConstants;
import com.huawei.unitedevice.constant.ConnectState;
import com.huawei.unitedevice.constant.WearEngineModule;
import com.huawei.unitedevice.manager.EngineLogicBaseManager;
import defpackage.spn;
import health.compact.a.Services;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class cxt extends EngineLogicBaseManager {

    /* renamed from: a, reason: collision with root package name */
    private static final Object f11534a = new Object();
    private static volatile cxt d;

    private cxt() {
        super(BaseApplication.getContext());
    }

    public static cxt e() {
        if (d == null) {
            synchronized (f11534a) {
                if (d == null) {
                    d = new cxt();
                }
            }
        }
        return d;
    }

    @Override // com.huawei.unitedevice.manager.EngineLogicBaseManager
    public void onReceiveDeviceCommand(DeviceInfo deviceInfo, int i, spn spnVar) {
        if (spnVar == null) {
            LogUtil.h("EcologyDeviceDiscoveryFreeDeviceManager", "onReceiveDeviceCommand message is null");
        } else if (spnVar.d() == 2) {
            LogUtil.a("EcologyDeviceDiscoveryFreeDeviceManager", "onReceiveDeviceCommand message type is file");
        }
    }

    @Override // com.huawei.unitedevice.manager.EngineLogicBaseManager
    public WearEngineModule getManagerModule() {
        return WearEngineModule.WATCH_MODULE;
    }

    @Override // com.huawei.unitedevice.manager.EngineLogicBaseManager
    public void onDeviceConnectionChange(ConnectState connectState, DeviceInfo deviceInfo) {
        LogUtil.a("EcologyDeviceDiscoveryFreeDeviceManager", "connectState ", connectState);
        if (connectState == ConnectState.CONNECTED) {
            LogUtil.a("EcologyDeviceDiscoveryFreeDeviceManager", "onDeviceConnected");
        }
    }

    private String d(ArrayList<ContentValues> arrayList) {
        String str;
        String str2;
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        try {
        } catch (JSONException e) {
            LogUtil.b("EcologyDeviceDiscoveryFreeDeviceManager", "JSONException:", e.getMessage());
        }
        if (koq.b(arrayList)) {
            LogUtil.h("EcologyDeviceDiscoveryFreeDeviceManager", "bondedDevice is empty");
            return jSONObject.put(BleConstants.DEV_INFO, jSONArray).toString();
        }
        Iterator<ContentValues> it = arrayList.iterator();
        while (it.hasNext()) {
            ContentValues next = it.next();
            String asString = next.getAsString("productId");
            String asString2 = next.getAsString("uniqueId");
            if (BluetoothAdapter.checkBluetoothAddress(asString2)) {
                dcz d2 = ResourceManager.e().d(asString);
                String str3 = null;
                String e2 = d2 != null ? dks.e(asString, d2.n().b()) : null;
                ProductMapInfo a2 = dij.a(asString);
                if (a2 != null) {
                    str3 = a2.a();
                    str2 = a2.e();
                    str = a2.f();
                } else {
                    str = null;
                    str2 = null;
                }
                String asString3 = next.getAsString("name");
                LogUtil.a("EcologyDeviceDiscoveryFreeDeviceManager", "deviceName=", e2, " model=", str3, " devType=", str2, " bleName=", asString3, " prodId=", str);
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("deviceName", e2);
                jSONObject2.put("model", str3);
                jSONObject2.put("devType", str2);
                jSONObject2.put("bleName", asString3);
                jSONObject2.put("mac", asString2);
                jSONObject2.put("prodId", str);
                jSONArray.put(jSONObject2);
            }
        }
        jSONObject.put(BleConstants.DEV_INFO, jSONArray);
        return jSONObject.toString();
    }

    public void c() {
        String d2 = d(((PluginDeviceApi) Services.c("PluginDevice", PluginDeviceApi.class)).getBondedDevice());
        if (TextUtils.isEmpty(d2)) {
            LogUtil.h("EcologyDeviceDiscoveryFreeDeviceManager", "sendData is empty");
        } else {
            sendComand(new spn.b().c(d2.getBytes(StandardCharsets.UTF_8)).e(), new SendCallback() { // from class: cxt.5
                @Override // com.huawei.health.deviceconnect.callback.SendCallback
                public void onSendProgress(long j) {
                }

                @Override // com.huawei.health.deviceconnect.callback.SendCallback
                public void onSendResult(int i) {
                    if (i == 206) {
                        LogUtil.h("EcologyDeviceDiscoveryFreeDeviceManager", "sendCommand fail");
                    } else if (i == 207) {
                        LogUtil.a("EcologyDeviceDiscoveryFreeDeviceManager", "sendCommand success");
                    } else {
                        LogUtil.h("EcologyDeviceDiscoveryFreeDeviceManager", "sendCommand other Result = ", Integer.valueOf(i));
                    }
                }

                @Override // com.huawei.health.deviceconnect.callback.SendCallback
                public void onFileTransferReport(String str) {
                    LogUtil.a("EcologyDeviceDiscoveryFreeDeviceManager", "onFileTransferReport transferWay: ", str);
                }
            });
        }
    }

    @Override // com.huawei.unitedevice.manager.EngineLogicBaseManager
    public String getWearPkgName() {
        return "com.huawei.watch.ecocenter";
    }

    @Override // com.huawei.unitedevice.manager.EngineLogicBaseManager
    public String getWearFingerprint() {
        return "com.huawei.watch.ecocenter_BOx7SOHhpgvYBva2HgwiHYsqRO8WMaJyQ/CJ0Wke2W7wNIi/BcC0S2PfPE5vr02mqcCjhbXCtthfTL+k9X0EEYA=";
    }
}
