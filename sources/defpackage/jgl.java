package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.callback.BluetoothDataReceiveCallback;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwbasemgr.HwBaseManager;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public class jgl extends HwBaseManager {
    private static jgl b;
    private static final Object e = new Object();
    private BluetoothDataReceiveCallback c;
    private Map<String, jgk> d;

    private jgl(Context context) {
        super(context);
        this.d = new HashMap(1);
        this.c = new BluetoothDataReceiveCallback() { // from class: jgl.4
            @Override // com.huawei.callback.BluetoothDataReceiveCallback
            public void onDataReceived(int i, DeviceInfo deviceInfo, byte[] bArr) {
                LogUtil.a("HwDeviceModeMgr", "onResponse errCode:", Integer.valueOf(i), ", and objData:", cvx.d(bArr));
                if (bArr.length < 1) {
                    LogUtil.h("HwDeviceModeMgr", "onResponse responseData length less than 1");
                } else if (deviceInfo == null || TextUtils.isEmpty(deviceInfo.getDeviceIdentify())) {
                    LogUtil.h("HwDeviceModeMgr", "device info error");
                } else {
                    jgl.this.c(deviceInfo.getDeviceIdentify()).onReceivedData(i, bArr);
                }
            }
        };
        jfq.c().e(38, this.c);
    }

    @Override // com.huawei.hwbasemgr.HwBaseManager
    public Integer getModuleId() {
        return 38;
    }

    public static jgl d(Context context) {
        jgl jglVar;
        synchronized (e) {
            if (b == null && context != null) {
                b = new jgl(context);
            }
            jglVar = b;
        }
        return jglVar;
    }

    public void b(String str, IBaseResponseCallback iBaseResponseCallback) {
        c(str).a(iBaseResponseCallback);
    }

    public void e(String str, int i, IBaseResponseCallback iBaseResponseCallback) {
        c(str).e(i, iBaseResponseCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public jgk c(String str) {
        jgk jgkVar = this.d.get(str);
        if (jgkVar != null) {
            return jgkVar;
        }
        jgk jgkVar2 = new jgk(str);
        this.d.put(str, jgkVar2);
        return jgkVar2;
    }
}
