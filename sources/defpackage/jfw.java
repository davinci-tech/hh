package defpackage;

import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes5.dex */
public class jfw {
    public static void c(byte[] bArr) {
        IBaseResponseCallback c = jfr.c();
        if (c != null) {
            LogUtil.a("BluetoothCommandTestMgr", "requestBluetoothInterface request success data :", cvx.d(bArr));
            c.onResponse(0, bArr);
        } else {
            LogUtil.h("BluetoothCommandTestMgr", "requestBluetoothInterface callback is null");
        }
    }
}
