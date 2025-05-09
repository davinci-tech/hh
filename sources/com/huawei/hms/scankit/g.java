package com.huawei.hms.scankit;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.RemoteException;
import com.huawei.hms.feature.dynamic.IObjectWrapper;
import com.huawei.hms.feature.dynamic.ObjectWrapper;
import com.huawei.hms.hmsscankit.DetailRect;
import com.huawei.hms.hmsscankit.api.IRemoteHmsDecoderDelegate;
import com.huawei.hms.ml.scan.HmsScan;
import com.huawei.hms.scankit.p.o4;
import com.huawei.hms.scankit.p.r3;
import com.huawei.hms.scankit.p.r6;
import com.huawei.hms.scankit.p.w3;
import com.huawei.hms.scankit.p.w7;
import com.huawei.hms.scankit.p.y3;
import com.huawei.hms.scankit.util.OpencvJNI;
import java.nio.ByteBuffer;

/* loaded from: classes9.dex */
public class g extends IRemoteHmsDecoderDelegate.Stub {
    private static volatile g b = new g();

    /* renamed from: a, reason: collision with root package name */
    private volatile w3 f5720a = null;

    static g a() {
        return b;
    }

    @Override // com.huawei.hms.hmsscankit.api.IRemoteHmsDecoderDelegate
    public HmsScan[] decodeInBitmap(DetailRect detailRect, IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2) throws RemoteException {
        boolean z;
        if (!r3.A) {
            OpencvJNI.init();
        }
        o4.d("Scankit", "start decodeInBitmap");
        Bundle a2 = a(iObjectWrapper2);
        String str = "";
        boolean z2 = false;
        if (iObjectWrapper2 == null || !(ObjectWrapper.unwrap(iObjectWrapper2) instanceof Bundle)) {
            z = false;
        } else {
            str = ((Bundle) ObjectWrapper.unwrap(iObjectWrapper2)).getString(DetailRect.CP_PACKAGE, "");
            z = ((Bundle) ObjectWrapper.unwrap(iObjectWrapper2)).getBoolean(DetailRect.USE_APK, false);
            z2 = ((Bundle) ObjectWrapper.unwrap(iObjectWrapper2)).getBoolean(DetailRect.SUPPORT_ROLLBACK, false);
        }
        if (z2 && !r3.f5870a && z) {
            return new HmsScan[]{r6.b()};
        }
        if (this.f5720a == null && y3.a(str, y3.a())) {
            try {
                this.f5720a = new w3(a2, "MultiProcessor");
            } catch (RuntimeException unused) {
                o4.b("IRemoteDecoderDelegateImpl", "Ha error");
            } catch (Exception unused2) {
                o4.b("IRemoteDecoderDelegateImpl", "Ha error");
            }
        }
        o4.d("scankit mul", "end decodeInBitmap");
        return a(iObjectWrapper, iObjectWrapper2);
    }

    @Override // com.huawei.hms.hmsscankit.api.IRemoteHmsDecoderDelegate
    public HmsScan[] detectWithByteBuffer(DetailRect detailRect, IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2) throws RemoteException {
        boolean z;
        if (!r3.A) {
            OpencvJNI.init();
        }
        Bundle a2 = a(iObjectWrapper2);
        String str = "";
        boolean z2 = false;
        if (iObjectWrapper2 == null || !(ObjectWrapper.unwrap(iObjectWrapper2) instanceof Bundle)) {
            z = false;
        } else {
            str = ((Bundle) ObjectWrapper.unwrap(iObjectWrapper2)).getString(DetailRect.CP_PACKAGE, "");
            z = ((Bundle) ObjectWrapper.unwrap(iObjectWrapper2)).getBoolean(DetailRect.USE_APK, false);
            z2 = ((Bundle) ObjectWrapper.unwrap(iObjectWrapper2)).getBoolean(DetailRect.SUPPORT_ROLLBACK, false);
        }
        if (z2 && !r3.f5870a && z) {
            return new HmsScan[]{r6.b()};
        }
        if (this.f5720a == null && y3.a(str, y3.a())) {
            try {
                this.f5720a = new w3(a2, "MultiProcessor");
            } catch (RuntimeException unused) {
                o4.b("IRemoteDecoderDelegateImpl", "Ha error");
            } catch (Exception unused2) {
                o4.b("IRemoteDecoderDelegateImpl", "Ha error");
            }
        }
        return a(detailRect, iObjectWrapper, iObjectWrapper2);
    }

    private Bundle a(IObjectWrapper iObjectWrapper) {
        return (iObjectWrapper == null || !(ObjectWrapper.unwrap(iObjectWrapper) instanceof Bundle)) ? new Bundle() : (Bundle) ObjectWrapper.unwrap(iObjectWrapper);
    }

    private HmsScan[] a(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2) {
        boolean z;
        boolean z2;
        int i;
        boolean z3;
        o4.d("Scankit", "start getHmsMLVisionScanResultByBitmap");
        if (iObjectWrapper == null) {
            o4.b("ScankitRemote", "bitmap is null");
            return new HmsScan[0];
        }
        Object unwrap = ObjectWrapper.unwrap(iObjectWrapper);
        if (iObjectWrapper2 == null || !(ObjectWrapper.unwrap(iObjectWrapper2) instanceof Bundle)) {
            z = false;
            z2 = false;
            i = 0;
            z3 = false;
        } else {
            z2 = ((Bundle) ObjectWrapper.unwrap(iObjectWrapper2)).getBoolean(DetailRect.PHOTO_MODE, false);
            r3.c = z2;
            i = ((Bundle) ObjectWrapper.unwrap(iObjectWrapper2)).getInt(DetailRect.FORMAT_FLAG);
            boolean z4 = ((Bundle) ObjectWrapper.unwrap(iObjectWrapper2)).getBoolean(DetailRect.PARSE_RESULT, true);
            z3 = ((Bundle) ObjectWrapper.unwrap(iObjectWrapper2)).getBoolean(DetailRect.PARSE_RESULT, false);
            int i2 = ((Bundle) ObjectWrapper.unwrap(iObjectWrapper2)).getInt(DetailRect.TYPE_TRANS, 0);
            DetailRect.HMSSCAN_SDK_VALUE = i2;
            r2 = i2 >= 2;
            if (r2) {
                i = w7.b(i);
            }
            z = r2;
            r2 = z4;
        }
        r3.f = r2;
        r3.g = z3;
        if (!(unwrap instanceof Bitmap)) {
            return new HmsScan[0];
        }
        o4.d("Scankit", "end getHmsMLVisionScanResultByBitmap");
        HmsScan[] a2 = r6.a().a((Bitmap) unwrap, i, z2, this.f5720a);
        return !z ? w7.a(a2) : a2;
    }

    private HmsScan[] a(DetailRect detailRect, IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2) {
        boolean z;
        boolean z2;
        int i;
        boolean z3;
        if (iObjectWrapper == null) {
            o4.b("ScankitRemoteS", "bytebuffer is null");
            return new HmsScan[0];
        }
        Object unwrap = ObjectWrapper.unwrap(iObjectWrapper);
        if (iObjectWrapper2 == null || !(ObjectWrapper.unwrap(iObjectWrapper2) instanceof Bundle)) {
            z = false;
            z2 = false;
            i = 0;
            z3 = false;
        } else {
            int i2 = ((Bundle) ObjectWrapper.unwrap(iObjectWrapper2)).getInt(DetailRect.FORMAT_FLAG);
            boolean z4 = ((Bundle) ObjectWrapper.unwrap(iObjectWrapper2)).getBoolean(DetailRect.PHOTO_MODE, false);
            int i3 = ((Bundle) ObjectWrapper.unwrap(iObjectWrapper2)).getInt(DetailRect.TYPE_TRANS, 0);
            DetailRect.HMSSCAN_SDK_VALUE = i3;
            boolean z5 = ((Bundle) ObjectWrapper.unwrap(iObjectWrapper2)).getBoolean(DetailRect.PARSE_RESULT, true);
            z = ((Bundle) ObjectWrapper.unwrap(iObjectWrapper2)).getBoolean(DetailRect.NEW_VERSION, false);
            r1 = i3 >= 2;
            if (r1) {
                i2 = w7.b(i2);
            }
            i = i2;
            z3 = z4;
            z2 = r1;
            r1 = z5;
        }
        r3.f = r1;
        r3.g = z;
        if (!(unwrap instanceof ByteBuffer)) {
            return new HmsScan[0];
        }
        HmsScan[] a2 = r6.a().a((ByteBuffer) unwrap, detailRect == null ? 1000 : detailRect.width, detailRect == null ? 1000 : detailRect.height, i, z3, this.f5720a);
        return !z2 ? w7.a(a2) : a2;
    }
}
