package defpackage;

import com.huawei.callback.BluetoothDataReceiveCallback;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;

/* loaded from: classes5.dex */
public class jou {
    public static void c(int i, final IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.b("ServiceCallback", "Enter registerNotificationCallback method. serviceId : ", Integer.valueOf(i));
        if (iBaseResponseCallback == null) {
            LogUtil.b("ServiceCallback", "registerNotificationCallback callback is null");
        } else {
            jfr.e(i, new BluetoothDataReceiveCallback() { // from class: jou.2
                @Override // com.huawei.callback.BluetoothDataReceiveCallback
                public void onDataReceived(int i2, DeviceInfo deviceInfo, byte[] bArr) {
                    if (bArr.length > 1) {
                        LogUtil.a("ServiceCallback", "FAQ onResponse recv bt bytes" + cvx.d(bArr));
                        byte b = bArr[1];
                        if (b == 7) {
                            int e = jou.e(bArr);
                            LogUtil.a("ServiceCallback", "FAQ onResponse resCode = " + e);
                            if (e == 100000) {
                                IBaseResponseCallback.this.d(0, Integer.valueOf(e));
                                return;
                            } else {
                                IBaseResponseCallback.this.d(e, Integer.valueOf(e));
                                return;
                            }
                        }
                        if (b == 8) {
                            int e2 = jou.e(bArr);
                            LogUtil.a("ServiceCallback", "wear push onResponse resCodeWear = " + e2);
                            if (e2 == 100000) {
                                IBaseResponseCallback.this.d(0, Integer.valueOf(e2));
                                return;
                            } else {
                                IBaseResponseCallback.this.d(e2, Integer.valueOf(e2));
                                return;
                            }
                        }
                        LogUtil.a("ServiceCallback", "enter mNotificationServiceCallback default branch");
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int e(byte[] bArr) {
        String d = cvx.d(bArr);
        if (d.length() < 9) {
            LogUtil.h("ServiceCallback", "messageHex length is illegal");
            return 0;
        }
        return CommonUtil.w(d.substring(8));
    }
}
