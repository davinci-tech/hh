package defpackage;

import android.text.TextUtils;
import com.google.gson.JsonObject;
import com.huawei.health.devicemgr.business.callback.DataReceiveCallback;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.HEXUtils;
import health.compact.a.ProcessUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class bgj implements DataReceiveCallback {
    private final Map<String, WeakReference<IBaseResponseCallback>> d = new ConcurrentHashMap();

    static class b {

        /* renamed from: a, reason: collision with root package name */
        private static final bgj f359a = new bgj();
    }

    public static bgj c() {
        if (ProcessUtil.d()) {
            return b.f359a;
        }
        throw new RuntimeException("DevicePullPageManager must run on main process");
    }

    public void a(DeviceInfo deviceInfo, String str, IBaseResponseCallback iBaseResponseCallback) {
        if (iBaseResponseCallback == null) {
            ReleaseLogUtil.e("UIDV_DevicePullPageManager", "no callback to response, not send");
            return;
        }
        b();
        if (d(deviceInfo, str)) {
            this.d.put(str, new WeakReference<>(iBaseResponseCallback));
        } else {
            iBaseResponseCallback.d(-1, -1);
        }
    }

    public boolean d(DeviceInfo deviceInfo, String str) {
        cvp cvpVar = new cvp();
        cvpVar.setSrcPkgName("hw.unitedevice.pullPage");
        cvpVar.setWearPkgName("hw.wearable.pullPage");
        cvpVar.setCommandId(2);
        cvpVar.a(800100010L);
        cvpVar.b(0);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("pingUrl", str);
        LogUtil.a("UIDV_DevicePullPageManager", "event data: ", jsonObject);
        cvpVar.e(HEXUtils.c(HEXUtils.b(jsonObject.toString())));
        boolean sendSampleEventCommand = cuk.b().sendSampleEventCommand(deviceInfo, cvpVar, "UIDV_DevicePullPageManager");
        ReleaseLogUtil.e("UIDV_DevicePullPageManager", "sendEvent result data: ", Boolean.valueOf(sendSampleEventCommand));
        return sendSampleEventCommand;
    }

    public void b() {
        ReleaseLogUtil.e("UIDV_DevicePullPageManager", "registerDevicePullPage");
        cuk.b().registerDeviceSampleListener("hw.unitedevice.pullPage", this);
    }

    @Override // com.huawei.health.devicemgr.business.callback.DataReceiveCallback
    public void onDataReceived(int i, DeviceInfo deviceInfo, cvr cvrVar) {
        ReleaseLogUtil.e("UIDV_DevicePullPageManager", "receive device event.", Integer.valueOf(i));
        if (cvrVar instanceof cvp) {
            cvp cvpVar = (cvp) cvrVar;
            if (cvpVar.e() != 800100010 || !"hw.wearable.pullPage".equals(cvpVar.getSrcPkgName())) {
                ReleaseLogUtil.d("UIDV_DevicePullPageManager", "message commandId or pkgName not match.");
                return;
            }
            ReleaseLogUtil.e("UIDV_DevicePullPageManager", "message:", cvpVar);
            String d = HEXUtils.d(HEXUtils.a(cvpVar.c()));
            if (TextUtils.isEmpty(d)) {
                ReleaseLogUtil.c("UIDV_DevicePullPageManager", "onDataReceived eventData is null");
            } else {
                a(d);
            }
        }
    }

    private void a(String str) {
        LogUtil.a("UIDV_DevicePullPageManager", "string data:", str);
        try {
            JSONObject jSONObject = new JSONObject(str);
            String string = jSONObject.getString("pingUrl");
            int i = jSONObject.getInt("pingResult");
            if (this.d.containsKey(string)) {
                WeakReference<IBaseResponseCallback> weakReference = this.d.get(string);
                if (weakReference != null && weakReference.get() != null) {
                    weakReference.get().d(0, Integer.valueOf(i));
                    LogUtil.a("UIDV_DevicePullPageManager", "callback onResponse: ", Integer.valueOf(i));
                    this.d.remove(string);
                    return;
                }
                ReleaseLogUtil.d("UIDV_DevicePullPageManager", "weakReference is null");
                this.d.remove(string);
            }
        } catch (JSONException unused) {
            ReleaseLogUtil.c("UIDV_DevicePullPageManager", "parse json exception");
        }
    }
}
