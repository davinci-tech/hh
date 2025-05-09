package com.huawei.hms.scankit;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.RemoteException;
import com.bumptech.glide.Registry;
import com.huawei.hms.feature.dynamic.IObjectWrapper;
import com.huawei.hms.feature.dynamic.ObjectWrapper;
import com.huawei.hms.hmsscankit.DetailRect;
import com.huawei.hms.hmsscankit.WriterException;
import com.huawei.hms.hmsscankit.api.IRemoteDecoderDelegate;
import com.huawei.hms.ml.scan.HmsBuildBitmapOption;
import com.huawei.hms.ml.scan.HmsScan;
import com.huawei.hms.ml.scan.HmsScanResult;
import com.huawei.hms.scankit.p.c5;
import com.huawei.hms.scankit.p.o4;
import com.huawei.hms.scankit.p.r3;
import com.huawei.hms.scankit.p.r6;
import com.huawei.hms.scankit.p.w3;
import com.huawei.hms.scankit.p.w7;
import com.huawei.hms.scankit.p.x3;
import com.huawei.hms.scankit.p.y3;
import com.huawei.hms.scankit.util.OpencvJNI;

/* loaded from: classes9.dex */
public class f extends IRemoteDecoderDelegate.Stub {
    private static volatile f c = new f();

    /* renamed from: a, reason: collision with root package name */
    private volatile w3 f5719a = null;
    private volatile x3 b = null;

    static f a() {
        return c;
    }

    @Override // com.huawei.hms.hmsscankit.api.IRemoteDecoderDelegate
    public IObjectWrapper buildBitmap(IObjectWrapper iObjectWrapper) throws RemoteException {
        if (iObjectWrapper == null || !(ObjectWrapper.unwrap(iObjectWrapper) instanceof Bundle)) {
            throw new RemoteException("Bundle is Null");
        }
        Bundle bundle = (Bundle) ObjectWrapper.unwrap(iObjectWrapper);
        String string = bundle.getString(HmsBuildBitmapOption.TYPE_BUILD_BITMAP_CONTENT);
        int i = bundle.getInt(HmsBuildBitmapOption.TYPE_BUILD_BITMAP_FOTMAT);
        int i2 = bundle.getInt(HmsBuildBitmapOption.TYPE_BUILD_BITMAP_WIDTH);
        int i3 = bundle.getInt(HmsBuildBitmapOption.TYPE_BUILD_BITMAP_HEIGHT);
        int i4 = bundle.getInt(HmsBuildBitmapOption.TYPE_BUILD_BITMAP_MARGIN, 1);
        int i5 = bundle.getInt(HmsBuildBitmapOption.TYPE_BUILD_BITMAP_COLOR, -1);
        try {
            Bitmap a2 = new c5().a(string, i, i2, i3, new HmsBuildBitmapOption.Creator().setBitmapMargin(i4).setBitmapColor(i5).setBitmapBackgroundColor(bundle.getInt(HmsBuildBitmapOption.TYPE_BUILD_BITMAP_BACKCOLOR, -1)).create());
            if (a2 != null) {
                return ObjectWrapper.wrap(a2);
            }
            throw new RemoteException("Bitmap is Null");
        } catch (WriterException e) {
            throw new RemoteException(e.getMessage());
        }
    }

    @Override // com.huawei.hms.hmsscankit.api.IRemoteDecoderDelegate
    public void buildBitmapLog(IObjectWrapper iObjectWrapper) {
        if (iObjectWrapper == null || !(ObjectWrapper.unwrap(iObjectWrapper) instanceof Bundle)) {
            return;
        }
        Bundle bundle = (Bundle) ObjectWrapper.unwrap(iObjectWrapper);
        if (this.b == null) {
            try {
                this.b = new x3();
                this.b.c(bundle);
            } catch (RuntimeException unused) {
                o4.b("IRemoteDecoderDelegateImpl", "buildBitmapLog RuntimeException");
            } catch (Exception unused2) {
                o4.b("IRemoteDecoderDelegateImpl", "buildBitmapLog Exception");
            }
        }
    }

    @Override // com.huawei.hms.hmsscankit.api.IRemoteDecoderDelegate
    public HmsScan[] decodeWithBitmap(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2) throws RemoteException {
        boolean z;
        if (!r3.A) {
            OpencvJNI.init();
        }
        Bundle bundle = (iObjectWrapper2 == null || !(ObjectWrapper.unwrap(iObjectWrapper2) instanceof Bundle)) ? new Bundle() : (Bundle) ObjectWrapper.unwrap(iObjectWrapper2);
        boolean z2 = true;
        String str = "";
        boolean z3 = false;
        if (iObjectWrapper2 == null || !(ObjectWrapper.unwrap(iObjectWrapper2) instanceof Bundle)) {
            z = false;
        } else {
            str = ((Bundle) ObjectWrapper.unwrap(iObjectWrapper2)).getString(DetailRect.CP_PACKAGE, "");
            z = ((Bundle) ObjectWrapper.unwrap(iObjectWrapper2)).getBoolean(DetailRect.USE_APK, false);
            z3 = ((Bundle) ObjectWrapper.unwrap(iObjectWrapper2)).getBoolean(DetailRect.SUPPORT_ROLLBACK, false);
            z2 = ((Bundle) ObjectWrapper.unwrap(iObjectWrapper2)).getBoolean(DetailRect.PARSE_RESULT, true);
        }
        r3.f = z2;
        if (z3 && !r3.f5870a && z) {
            return new HmsScan[]{r6.b()};
        }
        if (this.f5719a == null && y3.a(str, y3.a())) {
            try {
                this.f5719a = new w3(bundle, Registry.BUCKET_BITMAP);
            } catch (RuntimeException unused) {
                o4.b("IRemoteDecoderDelegateImpl", "Ha error");
            } catch (Exception unused2) {
                o4.b("IRemoteDecoderDelegateImpl", "Ha error");
            }
        }
        return a(iObjectWrapper, iObjectWrapper2);
    }

    @Override // com.huawei.hms.hmsscankit.api.IRemoteDecoderDelegate
    public HmsScanResult decodeWithBuffer(byte[] bArr, int i, int i2, IObjectWrapper iObjectWrapper) throws RemoteException {
        int i3;
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4 = false;
        if (bArr == null) {
            o4.b("IRemoteDecoder", "buffer is null");
            return new HmsScanResult(4096, new HmsScan[0]);
        }
        if (!r3.A) {
            OpencvJNI.init();
        }
        if (iObjectWrapper == null || !(ObjectWrapper.unwrap(iObjectWrapper) instanceof Bundle)) {
            i3 = 0;
            z = false;
            z2 = true;
        } else {
            int i4 = ((Bundle) ObjectWrapper.unwrap(iObjectWrapper)).getInt(DetailRect.FORMAT_FLAG);
            boolean z5 = ((Bundle) ObjectWrapper.unwrap(iObjectWrapper)).getBoolean(DetailRect.PHOTO_MODE, false);
            r3.c = z5;
            int i5 = ((Bundle) ObjectWrapper.unwrap(iObjectWrapper)).getInt(DetailRect.TYPE_TRANS, 0);
            boolean z6 = ((Bundle) ObjectWrapper.unwrap(iObjectWrapper)).getBoolean(DetailRect.PARSE_RESULT, true);
            DetailRect.HMSSCAN_SDK_VALUE = i5;
            r5 = i5 >= 2;
            if (r5) {
                i4 = w7.b(i4);
            }
            z = r5;
            z2 = z5;
            r5 = z6;
            i3 = i4;
        }
        Bundle bundle = (iObjectWrapper == null || !(ObjectWrapper.unwrap(iObjectWrapper) instanceof Bundle)) ? new Bundle() : (Bundle) ObjectWrapper.unwrap(iObjectWrapper);
        String str = "";
        if (iObjectWrapper == null || !(ObjectWrapper.unwrap(iObjectWrapper) instanceof Bundle)) {
            z3 = false;
        } else {
            str = ((Bundle) ObjectWrapper.unwrap(iObjectWrapper)).getString(DetailRect.CP_PACKAGE, "");
            z3 = ((Bundle) ObjectWrapper.unwrap(iObjectWrapper)).getBoolean(DetailRect.USE_APK, false);
            z4 = ((Bundle) ObjectWrapper.unwrap(iObjectWrapper)).getBoolean(DetailRect.SUPPORT_ROLLBACK, false);
        }
        if (z4 && !r3.f5870a && z3) {
            return new HmsScanResult(4096, new HmsScan[]{r6.b()});
        }
        if (this.f5719a == null && y3.a(str, y3.a())) {
            try {
                this.f5719a = new w3(bundle, Registry.BUCKET_BITMAP);
            } catch (RuntimeException unused) {
                o4.b("IRemoteDecoderDelegateImpl", "Ha error");
            } catch (Exception unused2) {
                o4.b("IRemoteDecoderDelegateImpl", "Ha error");
            }
        }
        r3.f = r5;
        return r6.a().a(bArr, i, i2, i3, z2, z, this.f5719a);
    }

    @Override // com.huawei.hms.hmsscankit.api.IRemoteDecoderDelegate
    public IObjectWrapper queryDeepLinkInfo(IObjectWrapper iObjectWrapper) throws RemoteException {
        return null;
    }

    private HmsScan[] a(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2) {
        boolean z;
        int i;
        boolean z2;
        if (iObjectWrapper == null) {
            o4.b("IRemoteDecoder", "bitmap is null");
            return new HmsScan[0];
        }
        Object unwrap = ObjectWrapper.unwrap(iObjectWrapper);
        if (iObjectWrapper2 == null || !(ObjectWrapper.unwrap(iObjectWrapper2) instanceof Bundle)) {
            z = false;
            i = 0;
            z2 = true;
        } else {
            i = ((Bundle) ObjectWrapper.unwrap(iObjectWrapper2)).getInt(DetailRect.FORMAT_FLAG);
            z2 = ((Bundle) ObjectWrapper.unwrap(iObjectWrapper2)).getBoolean(DetailRect.PHOTO_MODE, false);
            int i2 = ((Bundle) ObjectWrapper.unwrap(iObjectWrapper2)).getInt(DetailRect.TYPE_TRANS, 0);
            boolean z3 = ((Bundle) ObjectWrapper.unwrap(iObjectWrapper2)).getBoolean(DetailRect.PARSE_RESULT, true);
            DetailRect.HMSSCAN_SDK_VALUE = i2;
            r1 = i2 >= 2;
            if (r1) {
                i = w7.b(i);
            }
            boolean z4 = r1;
            r1 = z3;
            z = z4;
        }
        r3.f = r1;
        if (!(unwrap instanceof Bitmap)) {
            return new HmsScan[0];
        }
        HmsScan[] b = r6.a().b((Bitmap) unwrap, i, z2, this.f5719a);
        return !z ? w7.a(b) : b;
    }
}
