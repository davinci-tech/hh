package defpackage;

import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.deviceconnect.callback.PingCallback;
import com.huawei.health.deviceconnect.callback.SendCallback;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwdevicemgr.R$string;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.maps.offlinedata.health.p2p.OfflineMapWearEngineManager;
import com.huawei.unitedevice.constant.ConnectState;
import com.huawei.unitedevice.constant.WearEngineModule;
import com.huawei.unitedevice.manager.EngineLogicBaseManager;
import defpackage.spn;
import health.compact.a.HEXUtils;
import java.nio.charset.StandardCharsets;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public class pry extends EngineLogicBaseManager {
    private static final Object c = new Object();
    private static volatile pry d;

    private pry() {
        super(BaseApplication.e());
    }

    public static pry e() {
        if (d == null) {
            synchronized (c) {
                if (d == null) {
                    d = new pry();
                }
            }
        }
        return d;
    }

    private void c() {
        jdh.c().a(20211202);
        psa.dsA_(psa.dsz_(com.huawei.hwcommonmodel.application.BaseApplication.getContext().getResources().getString(R$string.IDS_ecg_collection_title), com.huawei.hwcommonmodel.application.BaseApplication.getContext().getResources().getString(R$string.IDS_ecg_remind_measure), false));
    }

    private void a(final byte[] bArr) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: prx
            @Override // java.lang.Runnable
            public final void run() {
                pry.this.b(bArr);
            }
        });
    }

    /* synthetic */ void b(byte[] bArr) {
        LogUtil.a("EcgReminderEngineLogicMgr", "parseMessage");
        try {
            JSONObject jSONObject = new JSONObject(new String(bArr, StandardCharsets.UTF_8));
            int i = jSONObject.getInt("version");
            if (i != 1) {
                LogUtil.h("EcgReminderEngineLogicMgr", "parseMessage, unknown version = ", Integer.valueOf(i));
            } else {
                c(jSONObject);
            }
        } catch (JSONException unused) {
            LogUtil.b("EcgReminderEngineLogicMgr", "parseMessage, JSONException");
        }
    }

    private void c(JSONObject jSONObject) {
        try {
            int i = jSONObject.getInt("msgType");
            JSONObject jSONObject2 = jSONObject.getJSONObject("msgBody");
            if (i != 900) {
                LogUtil.h("EcgReminderEngineLogicMgr", "unknow case, type = ", Integer.valueOf(i));
            } else if (jSONObject2.getInt("errorCode") != 2) {
                c();
            }
        } catch (JSONException unused) {
            LogUtil.b("EcgReminderEngineLogicMgr", "parseMessageVersionOne, JSONException");
        }
    }

    @Override // com.huawei.unitedevice.manager.EngineLogicBaseManager
    public void onReceiveDeviceCommand(DeviceInfo deviceInfo, int i, spn spnVar) {
        if (spnVar == null) {
            LogUtil.h("EcgReminderEngineLogicMgr", "message is null");
        } else {
            a(spnVar.b());
        }
    }

    @Override // com.huawei.unitedevice.manager.EngineLogicBaseManager
    public WearEngineModule getManagerModule() {
        return WearEngineModule.ECG_REMINDER_MODULE;
    }

    @Override // com.huawei.unitedevice.manager.EngineLogicBaseManager
    public void onDeviceConnectionChange(ConnectState connectState, DeviceInfo deviceInfo) {
        LogUtil.a("EcgReminderEngineLogicMgr", "connectState ", connectState);
        if (connectState == ConnectState.CONNECTED) {
            LogUtil.a("EcgReminderEngineLogicMgr", "onDeviceConnected");
        } else {
            LogUtil.a("EcgReminderEngineLogicMgr", "onDeviceDisconnected");
        }
    }

    @Override // com.huawei.unitedevice.manager.EngineLogicBaseManager
    public void pingComand(PingCallback pingCallback, String str) {
        super.pingComand(pingCallback, str);
    }

    public JSONObject d(int i, String str, String str2, String str3, String str4) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("version", 1);
        jSONObject.put("msgType", i);
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("title", str);
        jSONObject2.put("content", str2);
        jSONObject2.put("button1Text", str3);
        jSONObject2.put("button2Text", str4);
        jSONObject.put("msgBody", jSONObject2);
        return jSONObject;
    }

    public void b(String str) {
        sendComand(new spn.b().c(psa.d(HEXUtils.c("00000000"), str.getBytes(StandardCharsets.UTF_8))).e(), new SendCallback() { // from class: pry.5
            @Override // com.huawei.health.deviceconnect.callback.SendCallback
            public void onSendResult(int i) {
                LogUtil.a("EcgReminderEngineLogicMgr", "sendCommand, onSendResult, i = ", Integer.valueOf(i));
            }

            @Override // com.huawei.health.deviceconnect.callback.SendCallback
            public void onSendProgress(long j) {
                LogUtil.a("EcgReminderEngineLogicMgr", "sendCommand, onSendProgress, l = ", Long.valueOf(j));
            }

            @Override // com.huawei.health.deviceconnect.callback.SendCallback
            public void onFileTransferReport(String str2) {
                LogUtil.a("EcgReminderEngineLogicMgr", "sendCommand transferWay: ", str2);
            }
        });
    }

    @Override // com.huawei.unitedevice.manager.EngineLogicBaseManager
    public String getWearPkgName() {
        return "hw.watch.ecgrecorder.p2p";
    }

    @Override // com.huawei.unitedevice.manager.EngineLogicBaseManager
    public String getWearFingerprint() {
        return OfflineMapWearEngineManager.WEAR_FINGERPRINT;
    }
}
