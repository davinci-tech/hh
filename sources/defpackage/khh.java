package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.health.deviceconnect.callback.SendCallback;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.maps.offlinedata.health.p2p.OfflineMapWearEngineManager;
import com.huawei.unitedevice.constant.ConnectState;
import com.huawei.unitedevice.constant.WearEngineModule;
import com.huawei.unitedevice.manager.EngineLogicBaseManager;
import defpackage.spn;
import java.io.File;
import java.io.UnsupportedEncodingException;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class khh extends EngineLogicBaseManager {
    private static khh d;
    private static final Object e = new Object();

    private khh(Context context) {
        super(context);
    }

    public static khh c() {
        khh khhVar;
        synchronized (e) {
            if (d == null) {
                d = new khh(BaseApplication.getContext());
            }
            khhVar = d;
        }
        return khhVar;
    }

    @Override // com.huawei.unitedevice.manager.EngineLogicBaseManager
    public void onReceiveDeviceCommand(DeviceInfo deviceInfo, int i, spn spnVar) {
        LogUtil.a("HwNotificationEngineManager", "onReceiveDeviceCommand errorCode:", Integer.valueOf(i));
        if (spnVar == null) {
            LogUtil.h("HwNotificationEngineManager", "onReceiveDeviceCommand message is null");
            return;
        }
        byte[] b = spnVar.b();
        if (b == null || b.length == 0) {
            LogUtil.h("HwNotificationEngineManager", "onReceiveDeviceCommand messageData is invalid");
        } else {
            b(b);
        }
    }

    private void b(byte[] bArr) {
        String str;
        try {
            str = new String(bArr, "UTF-8");
        } catch (UnsupportedEncodingException unused) {
            LogUtil.b("HwNotificationEngineManager", "handleReceiveJsonMessage UnsupportedEncodingException");
            str = null;
        }
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("HwNotificationEngineManager", "handleReceiveJsonMessage requestInfo is empty");
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            int i = jSONObject.getInt("msgType");
            LogUtil.a("HwNotificationEngineManager", "handleReceiveJsonMessage msgType:", Integer.valueOf(i), " jsonObject:", jSONObject.toString());
            JSONObject jSONObject2 = jSONObject.getJSONObject("msgBody");
            if (jSONObject2 == null) {
                LogUtil.h("HwNotificationEngineManager", "handleReceiveJsonMessage msgBody is null");
                return;
            }
            if (i == 6001) {
                khg.d().d(jSONObject2);
            } else if (i == 6002) {
                khg.d().e(jSONObject2);
            } else {
                LogUtil.h("HwNotificationEngineManager", "handleReceiveJsonMessage msgType is default");
            }
        } catch (JSONException unused2) {
            LogUtil.b("HwNotificationEngineManager", "handleReceiveJsonMessage JSONException");
        }
    }

    public void a(spn spnVar, SendCallback sendCallback) {
        sendComand(spnVar, sendCallback);
    }

    public void a(String str) throws UnsupportedEncodingException {
        sendComand(new spn.b().c(str.getBytes("UTF-8")).e(), new SendCallback() { // from class: khh.1
            @Override // com.huawei.health.deviceconnect.callback.SendCallback
            public void onSendProgress(long j) {
            }

            @Override // com.huawei.health.deviceconnect.callback.SendCallback
            public void onSendResult(int i) {
                LogUtil.a("HwNotificationEngineManager", "sendJsonCommand onSendResult: ", Integer.valueOf(i));
            }

            @Override // com.huawei.health.deviceconnect.callback.SendCallback
            public void onFileTransferReport(String str2) {
                LogUtil.a("HwNotificationEngineManager", "sendJsonCommand onFileTransferReport transferWay: ", str2);
            }
        });
    }

    public void d(File file, SendCallback sendCallback) {
        spn.b bVar = new spn.b();
        bVar.a(file);
        a(bVar.e(), sendCallback);
    }

    @Override // com.huawei.unitedevice.manager.EngineLogicBaseManager
    public WearEngineModule getManagerModule() {
        return WearEngineModule.NOTIFICATION_PUSH_APP_MODULE;
    }

    @Override // com.huawei.unitedevice.manager.EngineLogicBaseManager
    public void onDeviceConnectionChange(ConnectState connectState, DeviceInfo deviceInfo) {
        LogUtil.a("HwNotificationEngineManager", "onDeviceConnectionChange", connectState);
    }

    @Override // com.huawei.unitedevice.manager.EngineLogicBaseManager
    public String getWearPkgName() {
        return "in.huawei.NotificationAppIcon";
    }

    @Override // com.huawei.unitedevice.manager.EngineLogicBaseManager
    public String getWearFingerprint() {
        return OfflineMapWearEngineManager.WEAR_FINGERPRINT;
    }
}
