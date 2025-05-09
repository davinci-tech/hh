package defpackage;

import android.text.TextUtils;
import health.compact.a.LogUtil;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes5.dex */
public class izc {
    private static final Object d = new Object();
    private static izc e;

    public static izc d() {
        izc izcVar;
        synchronized (d) {
            if (e == null) {
                e = new izc();
            }
            izcVar = e;
        }
        return izcVar;
    }

    public iyn d(byte[] bArr) {
        LogUtil.c("ParseDeviceLocalName", "enter parseBroadcastData");
        if (bArr == null) {
            return new iyn(null, 0);
        }
        return e(bArr);
    }

    private iyn e(byte[] bArr) {
        byte b;
        ByteBuffer order = ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN);
        String str = null;
        int i = 0;
        while (order.remaining() > 2 && (b = order.get()) > 0) {
            byte b2 = order.get();
            if (b2 != 1) {
                if (b2 != 9) {
                    int position = (order.position() + b) - 1;
                    if (position <= order.limit() && position >= 0) {
                        order.position(position);
                    }
                } else {
                    int i2 = b - 1;
                    if (i2 > 0) {
                        byte[] bArr2 = new byte[i2];
                        if (i2 > order.remaining()) {
                            LogUtil.e("ParseDeviceLocalName", "parseBroadcastData BufferUnderflowException");
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
        return new iyn(str, i);
    }
}
