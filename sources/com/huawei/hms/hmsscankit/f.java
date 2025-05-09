package com.huawei.hms.hmsscankit;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.os.RemoteException;
import com.huawei.agconnect.AGConnectInstance;
import com.huawei.hms.feature.DynamicModuleInitializer;
import com.huawei.hms.feature.dynamic.ObjectWrapper;
import com.huawei.hms.framework.common.hianalytics.WiseOpenHianalyticsData;
import com.huawei.hms.hmsscankit.api.IRemoteCreator;
import com.huawei.hms.hmsscankit.api.IRemoteDecoderDelegate;
import com.huawei.hms.hmsscankit.api.IRemoteFrameDecoderDelegate;
import com.huawei.hms.ml.scan.HmsBuildBitmapOption;
import com.huawei.hms.ml.scan.HmsScan;
import com.huawei.hms.ml.scan.HmsScanAnalyzerOptions;
import com.huawei.hms.ml.scan.HmsScanResult;
import com.huawei.hms.scankit.p.o4;
import com.huawei.hms.scankit.p.s6;
import com.huawei.hms.scankit.p.u6;
import com.huawei.hms.scankit.p.w3;
import com.huawei.hms.scankit.p.x3;
import com.huawei.hms.scankit.p.y3;
import com.huawei.operation.utils.Constants;
import com.huawei.wearengine.sensor.DataResult;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;
import java.util.UUID;

/* loaded from: classes4.dex */
public class f {

    /* renamed from: a, reason: collision with root package name */
    private static volatile x3 f4637a;
    private static volatile IRemoteDecoderDelegate b;
    private static volatile w3 c;
    private static volatile IRemoteFrameDecoderDelegate d;

    class a extends SimpleDateFormat {
        a(String str) {
            super(str);
            setTimeZone(TimeZone.getTimeZone(DataResult.UTC));
        }
    }

    static HmsScan[] a(Context context, Bitmap bitmap, HmsScanAnalyzerOptions hmsScanAnalyzerOptions) {
        return a(context, bitmap, hmsScanAnalyzerOptions, hmsScanAnalyzerOptions.mode);
    }

    static HmsScan[] a(Context context, Bitmap bitmap, HmsScanAnalyzerOptions hmsScanAnalyzerOptions, int i) {
        HmsScan[] hmsScanArr = new HmsScan[0];
        if (b == null) {
            IRemoteCreator c2 = g.c(context);
            if (c2 == null) {
                return hmsScanArr;
            }
            try {
                b = c2.newRemoteDecoderDelegate();
            } catch (RemoteException unused) {
                o4.b("ScankitRemoteDecoder", "RemoteException");
            }
        }
        if (b == null) {
            return hmsScanArr;
        }
        try {
            Bundle bundle = new Bundle();
            if (hmsScanAnalyzerOptions != null) {
                bundle.putInt(DetailRect.FORMAT_FLAG, i);
                bundle.putBoolean(DetailRect.PHOTO_MODE, hmsScanAnalyzerOptions.photoMode);
                bundle.putBoolean(DetailRect.PARSE_RESULT, hmsScanAnalyzerOptions.parseResult);
            }
            bundle.putInt(DetailRect.TYPE_TRANS, 3);
            bundle.putString(DetailRect.CP_PACKAGE, y3.b(context));
            bundle.putBoolean(DetailRect.SUPPORT_ROLLBACK, g.c);
            bundle.putBoolean(DetailRect.USE_APK, g.f4638a);
            bundle.putAll(y3.a(context));
            HmsScan[] decodeWithBitmap = b.decodeWithBitmap(ObjectWrapper.wrap(bitmap), ObjectWrapper.wrap(bundle));
            a(decodeWithBitmap);
            if (g.a()) {
                o4.d("ScankitRemoteDecoder", "iRemoteDecoderDelegate decodeWithBitmap rollback");
                IRemoteCreator c3 = g.c(context);
                if (c3 == null) {
                    return hmsScanArr;
                }
                try {
                    b = c3.newRemoteDecoderDelegate();
                } catch (RemoteException unused2) {
                    o4.b("ScankitRemoteDecoder", "RemoteException");
                }
                decodeWithBitmap = b.decodeWithBitmap(ObjectWrapper.wrap(bitmap), ObjectWrapper.wrap(bundle));
            }
            return decodeWithBitmap != null ? decodeWithBitmap : hmsScanArr;
        } catch (RemoteException unused3) {
            o4.b("ScankitRemoteDecoder", "RemoteException");
            return hmsScanArr;
        }
    }

    public static void a(HmsScan[] hmsScanArr) {
        if (hmsScanArr.length == 1 && a(hmsScanArr[0].getCornerPoints())) {
            g.b = true;
        } else {
            g.b = false;
        }
    }

    public static void a(s6[] s6VarArr) {
        if (s6VarArr.length == 1 && a(s6VarArr[0].j())) {
            g.b = true;
        } else {
            g.b = false;
        }
    }

    private static boolean a(Point[] pointArr) {
        if (pointArr == null || pointArr.length == 0) {
            return false;
        }
        for (Point point : pointArr) {
            if (point.x != -2 && point.y != -2) {
                return false;
            }
        }
        return true;
    }

    private static boolean a(u6[] u6VarArr) {
        if (u6VarArr == null || u6VarArr.length == 0) {
            return false;
        }
        for (u6 u6Var : u6VarArr) {
            if (u6Var.b() + 2.0f > 1.0E-4d && u6Var.c() + 2.0f > 1.0E-4d) {
                return false;
            }
        }
        return true;
    }

    static HmsScanResult a(Context context, byte[] bArr, int i, int i2, HmsScanAnalyzerOptions hmsScanAnalyzerOptions) {
        HmsScanResult hmsScanResult = new HmsScanResult(4096, new HmsScan[0]);
        if (b == null) {
            IRemoteCreator c2 = g.c(context);
            if (c2 == null) {
                return hmsScanResult;
            }
            try {
                b = c2.newRemoteDecoderDelegate();
            } catch (RemoteException unused) {
                o4.b("ScankitRemoteDecoder", "RemoteException");
            }
        }
        if (b == null) {
            return hmsScanResult;
        }
        try {
            Bundle bundle = new Bundle();
            if (hmsScanAnalyzerOptions != null) {
                bundle.putInt(DetailRect.FORMAT_FLAG, hmsScanAnalyzerOptions.mode);
                bundle.putBoolean(DetailRect.PHOTO_MODE, hmsScanAnalyzerOptions.photoMode);
                bundle.putBoolean(DetailRect.PARSE_RESULT, hmsScanAnalyzerOptions.parseResult);
            }
            bundle.putBoolean(DetailRect.SUPPORT_ROLLBACK, g.c);
            bundle.putBoolean(DetailRect.USE_APK, g.f4638a);
            bundle.putInt(DetailRect.TYPE_TRANS, 3);
            bundle.putString(DetailRect.CP_PACKAGE, y3.b(context));
            bundle.putAll(y3.a(context));
            HmsScanResult decodeWithBuffer = b.decodeWithBuffer(bArr, i, i2, ObjectWrapper.wrap(bundle));
            if (decodeWithBuffer != null && decodeWithBuffer.getHmsScans() != null) {
                a(decodeWithBuffer.getHmsScans());
                if (g.a()) {
                    o4.d("ScankitRemoteDecoder", "iRemoteDecoderDelegate decodeWithBuffer rollback");
                    IRemoteCreator c3 = g.c(context);
                    if (c3 == null) {
                        return hmsScanResult;
                    }
                    try {
                        b = c3.newRemoteDecoderDelegate();
                    } catch (RemoteException unused2) {
                        o4.b("ScankitRemoteDecoder", "RemoteException");
                    }
                    decodeWithBuffer = b.decodeWithBuffer(bArr, i, i2, ObjectWrapper.wrap(bundle));
                }
            }
            return decodeWithBuffer != null ? decodeWithBuffer : hmsScanResult;
        } catch (RemoteException unused3) {
            o4.b("ScankitRemoteDecoder", "RemoteException");
            return hmsScanResult;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x008c A[Catch: RemoteException -> 0x0110, TryCatch #0 {RemoteException -> 0x0110, blocks: (B:10:0x0023, B:14:0x008c, B:15:0x0095, B:17:0x00ba, B:18:0x00de, B:20:0x00e6, B:21:0x00eb, B:23:0x00ee, B:25:0x00f2, B:27:0x00f8, B:29:0x0104, B:31:0x010a, B:37:0x007e, B:38:0x0084, B:35:0x005e), top: B:9:0x0023, inners: #1, #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:17:0x00ba A[Catch: RemoteException -> 0x0110, TryCatch #0 {RemoteException -> 0x0110, blocks: (B:10:0x0023, B:14:0x008c, B:15:0x0095, B:17:0x00ba, B:18:0x00de, B:20:0x00e6, B:21:0x00eb, B:23:0x00ee, B:25:0x00f2, B:27:0x00f8, B:29:0x0104, B:31:0x010a, B:37:0x007e, B:38:0x0084, B:35:0x005e), top: B:9:0x0023, inners: #1, #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x00e6 A[Catch: RemoteException -> 0x0110, TryCatch #0 {RemoteException -> 0x0110, blocks: (B:10:0x0023, B:14:0x008c, B:15:0x0095, B:17:0x00ba, B:18:0x00de, B:20:0x00e6, B:21:0x00eb, B:23:0x00ee, B:25:0x00f2, B:27:0x00f8, B:29:0x0104, B:31:0x010a, B:37:0x007e, B:38:0x0084, B:35:0x005e), top: B:9:0x0023, inners: #1, #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0093  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static com.huawei.hms.ml.scan.HmsScanResult a(android.content.Context r13, com.huawei.hms.ml.scan.HmsScanFrame r14, com.huawei.hms.ml.scan.HmsScanAnalyzerOptions r15) {
        /*
            Method dump skipped, instructions count: 285
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.hmsscankit.f.a(android.content.Context, com.huawei.hms.ml.scan.HmsScanFrame, com.huawei.hms.ml.scan.HmsScanAnalyzerOptions):com.huawei.hms.ml.scan.HmsScanResult");
    }

    private static void a(Bundle bundle) {
        if (DynamicModuleInitializer.getContext() == null) {
            try {
                g.b(AGConnectInstance.getInstance().getContext());
            } catch (ClassNotFoundException unused) {
                o4.b("ScankitRemoteDecoder", "buildBitmapLog ClassNotFoundException");
            } catch (IllegalAccessException unused2) {
                o4.b("ScankitRemoteDecoder", "buildBitmapLog IllegalAccessException");
            } catch (Exception unused3) {
                o4.b("ScankitRemoteDecoder", "buildBitmapLog Exception");
            } catch (NoClassDefFoundError unused4) {
                o4.b("ScankitRemoteDecoder", "buildBitmapLog NoClassDefFoundError");
                return;
            } catch (NoSuchMethodException unused5) {
                o4.b("ScankitRemoteDecoder", "buildBitmapLog NoSuchMethodException");
            } catch (InvocationTargetException unused6) {
                o4.b("ScankitRemoteDecoder", "buildBitmapLog InvocationTargetException");
            }
        }
        if (f4637a == null) {
            try {
                f4637a = new x3();
                f4637a.c(bundle);
            } catch (RuntimeException unused7) {
                o4.b("ScankitRemoteDecoder", "buildBitmapLog RuntimeException");
            } catch (Exception unused8) {
                o4.b("ScankitRemoteDecoder", "buildBitmapLog Exception");
            }
        }
    }

    public static Bundle a(String str, int i, int i2, int i3, HmsBuildBitmapOption hmsBuildBitmapOption) {
        Bundle bundle = new Bundle();
        bundle.putInt("contentLength", str == null ? -1 : str.length());
        bundle.putInt("scanType", i);
        bundle.putInt("reqWidth", i2);
        bundle.putInt("reqHeight", i3);
        bundle.putString("buildBitmapOption", hmsBuildBitmapOption == null ? Constants.NULL : hmsBuildBitmapOption.toString());
        bundle.putString("apiName", "BuildBitmap");
        bundle.putLong("callTime", System.currentTimeMillis());
        bundle.putString("transId", UUID.randomUUID().toString());
        return bundle;
    }

    public static void a(int i, Bitmap bitmap, Bundle bundle) {
        if (bundle != null) {
            bundle.putInt("result", i);
            bundle.putInt("outputWidth", bitmap == null ? -1 : bitmap.getWidth());
            bundle.putInt("outputHeight", bitmap != null ? bitmap.getHeight() : -1);
            long j = bundle.getLong("callTime");
            bundle.putLong(WiseOpenHianalyticsData.UNION_COSTTIME, System.currentTimeMillis() - j);
            bundle.putString("callTime", new a("yyyyMMddHHmmss.SSS").format(Long.valueOf(j)));
            a(bundle);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:15:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0034  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static void a(android.content.Context r3) {
        /*
            java.lang.String r0 = "ScankitRemoteDecoder"
            android.content.Context r3 = com.huawei.hms.hmsscankit.g.e(r3)     // Catch: java.lang.Throwable -> L6 java.lang.InstantiationException -> L1e java.lang.ClassNotFoundException -> L24 java.lang.IllegalAccessException -> L2a
        L6:
            java.lang.ClassLoader r1 = r3.getClassLoader()     // Catch: java.lang.InstantiationException -> L1e java.lang.ClassNotFoundException -> L24 java.lang.IllegalAccessException -> L2a
            java.lang.String r2 = "com.huawei.hms.scankit.DecoderCreator"
            java.lang.Class r1 = r1.loadClass(r2)     // Catch: java.lang.InstantiationException -> L1e java.lang.ClassNotFoundException -> L24 java.lang.IllegalAccessException -> L2a
            java.lang.ClassLoader r3 = r3.getClassLoader()     // Catch: java.lang.InstantiationException -> L1e java.lang.ClassNotFoundException -> L24 java.lang.IllegalAccessException -> L2a
            java.lang.String r2 = "com.huawei.hms.scankit.aiscan.common.BarcodeFormat"
            r3.loadClass(r2)     // Catch: java.lang.InstantiationException -> L1e java.lang.ClassNotFoundException -> L24 java.lang.IllegalAccessException -> L2a
            java.lang.Object r3 = r1.newInstance()     // Catch: java.lang.InstantiationException -> L1e java.lang.ClassNotFoundException -> L24 java.lang.IllegalAccessException -> L2a
            goto L30
        L1e:
            java.lang.String r3 = "InstantiationException"
            com.huawei.hms.scankit.p.o4.a(r0, r3)
            goto L2f
        L24:
            java.lang.String r3 = "ClassNotFoundException"
            com.huawei.hms.scankit.p.o4.a(r0, r3)
            goto L2f
        L2a:
            java.lang.String r3 = "IllegalAccessException"
            com.huawei.hms.scankit.p.o4.a(r0, r3)
        L2f:
            r3 = 0
        L30:
            boolean r1 = r3 instanceof android.os.IBinder
            if (r1 == 0) goto L46
            android.os.IBinder r3 = (android.os.IBinder) r3
            com.huawei.hms.hmsscankit.api.IRemoteDecoderCreator r3 = com.huawei.hms.hmsscankit.api.IRemoteDecoderCreator.Stub.asInterface(r3)
            com.huawei.hms.hmsscankit.api.IRemoteFrameDecoderDelegate r3 = r3.newRemoteFrameDecoderDelegate()     // Catch: java.lang.Exception -> L41
            com.huawei.hms.hmsscankit.f.d = r3     // Catch: java.lang.Exception -> L41
            goto L46
        L41:
            java.lang.String r3 = "remoteception"
            com.huawei.hms.scankit.p.o4.a(r0, r3)
        L46:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.hmsscankit.f.a(android.content.Context):void");
    }
}
