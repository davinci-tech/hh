package defpackage;

import android.text.TextUtils;
import health.compact.a.LogUtil;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes8.dex */
public class bjb {
    private static final Object b = new Object();
    private static bjb d;

    public static bjb e() {
        bjb bjbVar;
        synchronized (b) {
            if (d == null) {
                d = new bjb();
            }
            bjbVar = d;
        }
        return bjbVar;
    }

    public bik c(byte[] bArr) {
        LogUtil.c("ParseDeviceLocalName", "enter parseBroadcastData");
        if (bArr == null) {
            return new bik(null, 0);
        }
        return e(bArr);
    }

    private bik e(byte[] bArr) {
        byte b2;
        String str = null;
        if (bArr == null) {
            return null;
        }
        ByteBuffer order = ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN);
        int i = 0;
        while (order.remaining() > 2 && (b2 = order.get()) > 0) {
            byte b3 = order.get();
            if (b3 != 1) {
                if (b3 != 9) {
                    int position = (order.position() + b2) - 1;
                    if (position <= order.limit() && position >= 0) {
                        order.position(position);
                    }
                } else {
                    int i2 = b2 - 1;
                    if (i2 > 0) {
                        byte[] bArr2 = new byte[i2];
                        if (i2 > order.remaining()) {
                            LogUtil.a("ParseDeviceLocalName", "parseBroadcastData BufferUnderflowException");
                        } else {
                            order.get(bArr2);
                            try {
                                str = new String(bArr2, "utf-8");
                            } catch (UnsupportedEncodingException unused) {
                                LogUtil.e("ParseDeviceLocalName", "parseBroadcastData UnsupportedEncodingException");
                            }
                        }
                    }
                }
            } else if ((order.get() & 4) == 0) {
                i = 3;
            }
            if (!TextUtils.isEmpty(str)) {
                break;
            }
        }
        return new bik(str, i);
    }
}
