package defpackage;

import android.os.Handler;
import android.os.Looper;
import com.google.gson.Gson;
import com.huawei.health.deviceconnect.callback.PingCallback;
import com.huawei.health.deviceconnect.callback.SendCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginhealthzone.devicelink.callback.DeviceDataListener;
import com.huawei.pluginhealthzone.devicelink.deviceproxy.HealthZoneModelPolicy;
import defpackage.spn;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class mpy implements HealthZoneModelPolicy {

    /* renamed from: a, reason: collision with root package name */
    private static volatile mpy f15103a;
    private static final Object c = new Object();
    private HashMap<Integer, DeviceDataListener> b;

    private mpy() {
    }

    public static mpy d() {
        if (f15103a == null) {
            synchronized (c) {
                if (f15103a == null) {
                    f15103a = new mpy();
                }
            }
        }
        return f15103a;
    }

    @Override // com.huawei.pluginhealthzone.devicelink.deviceproxy.HealthZoneModelPolicy
    public void receiveDataFromDevice(int i, spn spnVar) {
        LogUtil.a("HealthZoneModelPolicyControl", "receiveDataFromDevice code = ", Integer.valueOf(i));
        if (i != 530003) {
            LogUtil.h("HealthZoneModelPolicyControl", "code is not 530003");
            return;
        }
        if (spnVar == null || spnVar.b() == null) {
            LogUtil.h("HealthZoneModelPolicyControl", "receiveDataFromDevice msg = null");
            return;
        }
        byte[] b = spnVar.b();
        LogUtil.a("HealthZoneModelPolicyControl", "messageData = ", b);
        e(b);
    }

    private void e(byte[] bArr) {
        try {
            JSONObject jSONObject = new JSONObject(new String(bArr, StandardCharsets.UTF_8));
            int i = jSONObject.getInt("msgType");
            LogUtil.a("HealthZoneModelPolicyControl", "msgType = ", Integer.valueOf(i));
            HashMap<Integer, DeviceDataListener> hashMap = this.b;
            if (hashMap == null) {
                LogUtil.h("HealthZoneModelPolicyControl", "dataListeners = null");
                return;
            }
            DeviceDataListener deviceDataListener = hashMap.get(Integer.valueOf(i));
            if (deviceDataListener == null) {
                LogUtil.h("HealthZoneModelPolicyControl", "deviceDataListener = null");
            } else {
                deviceDataListener.onResult(jSONObject.getJSONObject("msgBody").getInt("resultCode"), null);
            }
        } catch (JSONException unused) {
            LogUtil.b("HealthZoneModelPolicyControl", "jsonException");
        }
    }

    public void a(DeviceDataListener deviceDataListener, int i) {
        if (this.b == null) {
            this.b = new HashMap<>();
        }
        this.b.put(Integer.valueOf(i), deviceDataListener);
    }

    public void e(int i) {
        HashMap<Integer, DeviceDataListener> hashMap = this.b;
        if (hashMap == null || !hashMap.containsKey(Integer.valueOf(i))) {
            return;
        }
        this.b.remove(Integer.valueOf(i));
    }

    @Override // com.huawei.pluginhealthzone.devicelink.deviceproxy.HealthZoneModelPolicy
    public void activeSendMsgToDevice(int i, final mpx mpxVar, final DeviceDataListener deviceDataListener) {
        LogUtil.a("HealthZoneModelPolicyControl", "activeSendMsgToDevice responseMsgBody = ", mpxVar.toString());
        mpw.d().pingComand(new PingCallback() { // from class: mpz
            @Override // com.huawei.health.deviceconnect.callback.PingCallback
            public final void onPingResult(int i2) {
                mpy.this.b(mpxVar, deviceDataListener, i2);
            }
        }, "com.huawei.watch.family");
    }

    /* synthetic */ void b(final mpx mpxVar, final DeviceDataListener deviceDataListener, int i) {
        LogUtil.a("HealthZoneModelPolicyControl", "onPingResult code = ", Integer.valueOf(i));
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: mqe
            @Override // java.lang.Runnable
            public final void run() {
                mpy.this.a(mpxVar, deviceDataListener);
            }
        }, 200L);
    }

    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public void a(final mpx mpxVar, final DeviceDataListener deviceDataListener) {
        LogUtil.a("HealthZoneModelPolicyControl", "sendMsgToDevice enter");
        if (mpxVar != null) {
            LogUtil.a("HealthZoneModelPolicyControl", "responseMsgBody = ", mpxVar.toString());
            spn.b bVar = new spn.b();
            bVar.c(new Gson().toJson(mpxVar).getBytes(StandardCharsets.UTF_8));
            mpw.d().sendComand(bVar.e(), new SendCallback() { // from class: mpy.4
                @Override // com.huawei.health.deviceconnect.callback.SendCallback
                public void onSendResult(int i) {
                    LogUtil.a("HealthZoneModelPolicyControl", "sendComand onSendResult code = ", Integer.valueOf(i));
                    DeviceDataListener deviceDataListener2 = deviceDataListener;
                    if (deviceDataListener2 != null) {
                        deviceDataListener2.onResult(i == 207 ? 0 : -1, mpxVar);
                    }
                }

                @Override // com.huawei.health.deviceconnect.callback.SendCallback
                public void onSendProgress(long j) {
                    LogUtil.a("HealthZoneModelPolicyControl", "sendComand onSendProgress progress = ", Long.valueOf(j));
                }

                @Override // com.huawei.health.deviceconnect.callback.SendCallback
                public void onFileTransferReport(String str) {
                    LogUtil.a("HealthZoneModelPolicyControl", "sendMsgToDevice onFileTransferReport transferWay: ", str);
                }
            });
            return;
        }
        LogUtil.h("HealthZoneModelPolicyControl", "sendMsgToDevice responseMsgBody = null");
        if (deviceDataListener != null) {
            deviceDataListener.onResult(-1, new mpx());
        }
    }
}
