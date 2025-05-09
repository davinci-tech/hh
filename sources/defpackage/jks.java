package defpackage;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwdevice.mainprocess.mgr.hwotamanager.HwUpdateService;
import com.huawei.hwlogsmodel.LogUtil;
import java.lang.ref.WeakReference;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class jks extends Handler {
    private final WeakReference<HwUpdateService> e;

    public jks(Looper looper, HwUpdateService hwUpdateService) {
        super(looper);
        this.e = new WeakReference<>(hwUpdateService);
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        if (message == null) {
            LogUtil.h("UpdateHandler", "message is null");
            return;
        }
        super.handleMessage(message);
        HwUpdateService hwUpdateService = this.e.get();
        if (hwUpdateService == null) {
            LogUtil.h("UpdateHandler", "service is null");
            return;
        }
        LogUtil.a("UpdateHandler", "receive message:", Integer.valueOf(message.what));
        int i = message.what;
        if (i == 1) {
            int i2 = message.arg1;
            if (message.obj instanceof Bundle) {
                hwUpdateService.a(i2, ((Bundle) message.obj).getString("extra_band_imei"));
                return;
            }
            return;
        }
        if (i == 2) {
            int i3 = message.arg1;
            if (message.obj instanceof Bundle) {
                hwUpdateService.b(i3, ((Bundle) message.obj).getString("extra_band_imei"), true);
                return;
            }
            return;
        }
        if (i == 3) {
            int i4 = message.arg1;
            if (message.obj instanceof Bundle) {
                Bundle bundle = (Bundle) message.obj;
                String string = bundle.getString("extra_band_imei");
                boolean z = bundle.getBoolean("is_device_request_check");
                boolean z2 = bundle.getBoolean("is_auto");
                DeviceInfo deviceInfo = (DeviceInfo) bundle.getParcelable("device_info");
                LogUtil.a("UpdateHandler", "DO_UPGRADE_SEND_PACKAGE_TYPE isAuto:", Boolean.valueOf(z2), " isDeviceRequestCheck ", Boolean.valueOf(z));
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("transferPath", "");
                } catch (JSONException unused) {
                    LogUtil.b("UpdateHandler", "handleMessage DO_UPGRADE_SEND_MESSAGE JSONException");
                }
                jrd.b(deviceInfo, "090002", "1", "", jSONObject.toString());
                if (z2) {
                    hwUpdateService.e(i4, deviceInfo, false, string);
                    return;
                } else {
                    hwUpdateService.b(i4, deviceInfo, z, false, string);
                    return;
                }
            }
            return;
        }
        LogUtil.h("UpdateHandler", "handleMessage error");
    }
}
