package defpackage;

import android.text.TextUtils;
import com.huawei.devicesdk.callback.MessageReceiveCallback;
import com.huawei.devicesdk.entity.DeviceInfo;
import com.huawei.health.device.kit.wsp.task.BleTaskQueueUtil;
import com.huawei.health.ecologydevice.callback.DataChangedCallback;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes3.dex */
public class cwm implements MessageReceiveCallback {
    private static Map<String, DataChangedCallback> b = new HashMap();
    private String c;
    private final BleTaskQueueUtil d;

    public cwm(String str) {
        this(str, null);
    }

    public cwm(String str, BleTaskQueueUtil bleTaskQueueUtil) {
        this.c = str;
        this.d = bleTaskQueueUtil;
    }

    @Override // com.huawei.devicesdk.callback.MessageReceiveCallback
    public void onDataReceived(DeviceInfo deviceInfo, biu biuVar, int i) {
        if (biuVar != null) {
            String b2 = biuVar.b();
            LogUtil.a("EcologyDevice_ThirdPartyMessageReceiveCallback", "ThirdPartyMessageReceiveCallback#onDataReceived errorCode is ", Integer.valueOf(i));
            if (TextUtils.isEmpty(b2)) {
                LogUtil.h("EcologyDevice_ThirdPartyMessageReceiveCallback", "characterUuid is null");
                return;
            }
            DataChangedCallback dataChangedCallback = b.get(this.c);
            if (dataChangedCallback != null) {
                dataChangedCallback.onDataChanged(biuVar);
                return;
            } else {
                LogUtil.h("EcologyDevice_ThirdPartyMessageReceiveCallback", "DataChangedCallback is null");
                return;
            }
        }
        LogUtil.a("EcologyDevice_ThirdPartyMessageReceiveCallback", "ThirdPartyMessageReceiveCallback#onDataReceived dataContents is null");
    }

    @Override // com.huawei.devicesdk.callback.MessageReceiveCallback
    public void onChannelEnable(DeviceInfo deviceInfo, String str, int i) {
        LogUtil.a("EcologyDevice_ThirdPartyMessageReceiveCallback", "onChannelEnable errorCode:", Integer.valueOf(i));
        BleTaskQueueUtil bleTaskQueueUtil = this.d;
        if (bleTaskQueueUtil != null) {
            bleTaskQueueUtil.c();
        }
    }

    public static void b(String str, DataChangedCallback dataChangedCallback) {
        b.put(str, dataChangedCallback);
    }

    public static void b() {
        b.clear();
    }

    public static int d() {
        Map<String, DataChangedCallback> map = b;
        if (map == null) {
            return 0;
        }
        return map.size();
    }
}
