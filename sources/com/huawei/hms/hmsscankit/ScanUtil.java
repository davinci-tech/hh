package com.huawei.hms.hmsscankit;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.text.TextUtils;
import android.util.Log;
import com.huawei.hms.feature.dynamic.DynamicModule;
import com.huawei.hms.ml.scan.HmsBuildBitmapOption;
import com.huawei.hms.ml.scan.HmsScan;
import com.huawei.hms.ml.scan.HmsScanAnalyzerOptions;
import com.huawei.hms.ml.scan.HmsScanFrame;
import com.huawei.hms.ml.scan.HmsScanFrameOptions;
import com.huawei.hms.ml.scan.HmsScanResult;
import com.huawei.hms.mlsdk.common.MLFrame;
import com.huawei.hms.scankit.p.c5;
import com.huawei.hms.scankit.p.o4;
import com.huawei.hms.scankit.p.w7;
import com.huawei.phoneservice.faq.base.constants.TrackConstants$Events;
import java.io.FileNotFoundException;
import java.io.IOException;

/* loaded from: classes4.dex */
public class ScanUtil {
    public static final int CAMERA_INIT_ERROR = -1000;
    static final String CONTEXT_METHOD = "initializeModule";
    static final String CONTEXT_PATH = "com.huawei.hms.feature.DynamicModuleInitializer";
    static final String CREATOR_PATH = "com.huawei.hms.scankit.Creator";
    public static final int ERROR_ABNORMAL_RESTART = 3;
    public static final int ERROR_INVALID_PARAM = 4;
    public static final int ERROR_NO_CAMERA_PERMISSION = 1;
    public static final int ERROR_NO_READ_PERMISSION = 2;
    private static final int LITE_VERSION = 10320300;
    private static final int MAX_BITMAP_SIZE = 52428800;
    static final String MODULE_SCANKIT = "huawei_module_scankit";
    static final String MODULE_SCANKIT_LOCAL = "huawei_module_scankit_local";
    private static final int NEW_VERSION = 21002300;
    public static final String RESULT = "SCAN_RESULT";
    public static final String RESULT_CODE = "SCAN_RESULT_CODE";
    public static final int SCAN_NO_DETECTED = 4096;
    public static final int SUCCESS = 0;
    private static final int WR_VERSION = 201000300;

    public static Bitmap buildBitmap(String str, int i, int i2, int i3, HmsBuildBitmapOption hmsBuildBitmapOption) throws WriterException {
        return new c5().a(str, i, i2, i3, hmsBuildBitmapOption);
    }

    private static HmsScanResult checkHmsScan(HmsScan[] hmsScanArr, HmsScanFrameOptions hmsScanFrameOptions) {
        return hmsScanArr.length == 0 ? new HmsScanResult(4096, null) : (hmsScanArr[0].getOriginalValue() != "" || hmsScanArr[0].getZoomValue() <= 1.0d) ? hmsScanArr[0].getOriginalValue() != "" ? new HmsScanResult(0, hmsScanArr) : new HmsScanResult(4096, hmsScanArr) : new HmsScanResult(4098, hmsScanArr);
    }

    private static boolean checkVersion(int i, int i2) {
        if (i == LITE_VERSION && (i2 < NEW_VERSION || i2 == WR_VERSION)) {
            return true;
        }
        if (i != LITE_VERSION) {
            return i < NEW_VERSION || i2 == WR_VERSION || i2 < NEW_VERSION;
        }
        return false;
    }

    public static Bitmap compressBitmap(Context context, String str) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        dealBitmapFactoryOption(context, options);
        return BitmapFactory.decodeFile(str, options);
    }

    /* JADX WARN: Not initialized variable reg: 5, insn: 0x008d: MOVE (r4 I:??[OBJECT, ARRAY]) = (r5 I:??[OBJECT, ARRAY]), block:B:42:0x008d */
    /* JADX WARN: Removed duplicated region for block: B:25:0x005b A[Catch: Exception -> 0x0077, TRY_ENTER, TRY_LEAVE, TryCatch #2 {Exception -> 0x0077, blocks: (B:25:0x005b, B:23:0x0067, B:14:0x0073), top: B:3:0x0008 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static android.graphics.Bitmap compressBitmapForAndroid29(android.content.Context r11, java.lang.String r12) {
        /*
            java.lang.String r0 = "_id"
            java.lang.String r1 = "Exception"
            java.lang.String r2 = "exception"
            r3 = 1
            r4 = 0
            android.content.ContentResolver r5 = r11.getContentResolver()     // Catch: java.lang.Throwable -> L5f java.lang.Exception -> L61 java.lang.NullPointerException -> L6b
            android.net.Uri r6 = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI     // Catch: java.lang.Throwable -> L5f java.lang.Exception -> L61 java.lang.NullPointerException -> L6b
            java.lang.String[] r7 = new java.lang.String[r3]     // Catch: java.lang.Throwable -> L5f java.lang.Exception -> L61 java.lang.NullPointerException -> L6b
            r8 = 0
            r7[r8] = r0     // Catch: java.lang.Throwable -> L5f java.lang.Exception -> L61 java.lang.NullPointerException -> L6b
            java.lang.String[] r9 = new java.lang.String[]{r12}     // Catch: java.lang.Throwable -> L5f java.lang.Exception -> L61 java.lang.NullPointerException -> L6b
            java.lang.String r8 = "_data=?"
            r10 = 0
            android.database.Cursor r5 = r5.query(r6, r7, r8, r9, r10)     // Catch: java.lang.Throwable -> L5f java.lang.Exception -> L61 java.lang.NullPointerException -> L6b
            if (r5 == 0) goto L39
            boolean r6 = r5.moveToFirst()     // Catch: java.lang.Exception -> L62 java.lang.NullPointerException -> L6c java.lang.Throwable -> L8c
            if (r6 == 0) goto L39
            int r12 = r5.getColumnIndex(r0)     // Catch: java.lang.Exception -> L62 java.lang.NullPointerException -> L6c java.lang.Throwable -> L8c
            int r12 = r5.getInt(r12)     // Catch: java.lang.Exception -> L62 java.lang.NullPointerException -> L6c java.lang.Throwable -> L8c
            android.net.Uri r0 = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI     // Catch: java.lang.Exception -> L62 java.lang.NullPointerException -> L6c java.lang.Throwable -> L8c
            java.lang.String r12 = java.lang.String.valueOf(r12)     // Catch: java.lang.Exception -> L62 java.lang.NullPointerException -> L6c java.lang.Throwable -> L8c
            android.net.Uri r12 = android.net.Uri.withAppendedPath(r0, r12)     // Catch: java.lang.Exception -> L62 java.lang.NullPointerException -> L6c java.lang.Throwable -> L8c
            goto L58
        L39:
            java.io.File r0 = new java.io.File     // Catch: java.lang.Exception -> L62 java.lang.NullPointerException -> L6c java.lang.Throwable -> L8c
            r0.<init>(r12)     // Catch: java.lang.Exception -> L62 java.lang.NullPointerException -> L6c java.lang.Throwable -> L8c
            boolean r0 = r0.exists()     // Catch: java.lang.Exception -> L62 java.lang.NullPointerException -> L6c java.lang.Throwable -> L8c
            if (r0 == 0) goto L59
            android.content.ContentValues r0 = new android.content.ContentValues     // Catch: java.lang.Exception -> L62 java.lang.NullPointerException -> L6c java.lang.Throwable -> L8c
            r0.<init>()     // Catch: java.lang.Exception -> L62 java.lang.NullPointerException -> L6c java.lang.Throwable -> L8c
            java.lang.String r6 = "_data"
            r0.put(r6, r12)     // Catch: java.lang.Exception -> L62 java.lang.NullPointerException -> L6c java.lang.Throwable -> L8c
            android.content.ContentResolver r12 = r11.getContentResolver()     // Catch: java.lang.Exception -> L62 java.lang.NullPointerException -> L6c java.lang.Throwable -> L8c
            android.net.Uri r6 = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI     // Catch: java.lang.Exception -> L62 java.lang.NullPointerException -> L6c java.lang.Throwable -> L8c
            android.net.Uri r12 = r12.insert(r6, r0)     // Catch: java.lang.Exception -> L62 java.lang.NullPointerException -> L6c java.lang.Throwable -> L8c
        L58:
            r4 = r12
        L59:
            if (r5 == 0) goto L7a
            r5.close()     // Catch: java.lang.Exception -> L77
            goto L7a
        L5f:
            r11 = move-exception
            goto L8e
        L61:
            r5 = r4
        L62:
            com.huawei.hms.scankit.p.o4.b(r2, r1)     // Catch: java.lang.Throwable -> L8c
            if (r5 == 0) goto L7a
            r5.close()     // Catch: java.lang.Exception -> L77
            goto L7a
        L6b:
            r5 = r4
        L6c:
            java.lang.String r12 = "NullPointerException"
            com.huawei.hms.scankit.p.o4.b(r2, r12)     // Catch: java.lang.Throwable -> L8c
            if (r5 == 0) goto L7a
            r5.close()     // Catch: java.lang.Exception -> L77
            goto L7a
        L77:
            com.huawei.hms.scankit.p.o4.b(r2, r1)
        L7a:
            android.graphics.BitmapFactory$Options r12 = new android.graphics.BitmapFactory$Options
            r12.<init>()
            r12.inJustDecodeBounds = r3
            getBitmapFromUri(r11, r4, r12)
            dealBitmapFactoryOption(r11, r12)
            android.graphics.Bitmap r11 = getBitmapFromUri(r11, r4, r12)
            return r11
        L8c:
            r11 = move-exception
            r4 = r5
        L8e:
            if (r4 == 0) goto L97
            r4.close()     // Catch: java.lang.Exception -> L94
            goto L97
        L94:
            com.huawei.hms.scankit.p.o4.b(r2, r1)
        L97:
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.hmsscankit.ScanUtil.compressBitmapForAndroid29(android.content.Context, java.lang.String):android.graphics.Bitmap");
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0044  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x004c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static void dealBitmapFactoryOption(android.content.Context r7, android.graphics.BitmapFactory.Options r8) {
        /*
            java.lang.String r0 = "exception"
            int r1 = r8.outWidth
            int r2 = r8.outHeight
            if (r1 == 0) goto L5a
            if (r2 != 0) goto Lb
            goto L5a
        Lb:
            if (r1 <= r2) goto Le
            r1 = r2
        Le:
            java.lang.String r2 = "activity"
            java.lang.Object r7 = r7.getSystemService(r2)     // Catch: java.lang.Exception -> L34 java.lang.NullPointerException -> L3a
            android.app.ActivityManager r7 = (android.app.ActivityManager) r7     // Catch: java.lang.Exception -> L34 java.lang.NullPointerException -> L3a
            android.app.ActivityManager$MemoryInfo r2 = new android.app.ActivityManager$MemoryInfo     // Catch: java.lang.Exception -> L34 java.lang.NullPointerException -> L3a
            r2.<init>()     // Catch: java.lang.Exception -> L34 java.lang.NullPointerException -> L3a
            r7.getMemoryInfo(r2)     // Catch: java.lang.Exception -> L34 java.lang.NullPointerException -> L3a
            r3 = 4652218415073722368(0x4090000000000000, double:1024.0)
            r5 = 4613937818241073152(0x4008000000000000, double:3.0)
            double r3 = java.lang.Math.pow(r3, r5)     // Catch: java.lang.Exception -> L34 java.lang.NullPointerException -> L3a
            long r5 = r2.totalMem     // Catch: java.lang.Exception -> L34 java.lang.NullPointerException -> L3a
            double r5 = (double) r5
            double r5 = r5 / r3
            r2 = 4617878467915022336(0x4016000000000000, double:5.5)
            int r7 = (r5 > r2 ? 1 : (r5 == r2 ? 0 : -1))
            if (r7 >= 0) goto L31
            goto L3f
        L31:
            r7 = 3000(0xbb8, float:4.204E-42)
            goto L41
        L34:
            java.lang.String r7 = "Exception"
            com.huawei.hms.scankit.p.o4.b(r0, r7)
            goto L3f
        L3a:
            java.lang.String r7 = "NullPointerException"
            com.huawei.hms.scankit.p.o4.b(r0, r7)
        L3f:
            r7 = 1200(0x4b0, float:1.682E-42)
        L41:
            r0 = 1
            if (r1 <= r7) goto L4c
            float r1 = (float) r1
            float r7 = (float) r7
            float r1 = r1 / r7
            int r7 = java.lang.Math.round(r1)
            goto L4d
        L4c:
            r7 = r0
        L4d:
            r8.inSampleSize = r7
            r7 = 0
            r8.inJustDecodeBounds = r7
            android.graphics.Bitmap$Config r7 = android.graphics.Bitmap.Config.ARGB_8888
            r8.inPreferredConfig = r7
            r8.inPurgeable = r0
            r8.inInputShareable = r0
        L5a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.hmsscankit.ScanUtil.dealBitmapFactoryOption(android.content.Context, android.graphics.BitmapFactory$Options):void");
    }

    public static HmsScanResult decode(Context context, HmsScanFrame hmsScanFrame, HmsScanFrameOptions hmsScanFrameOptions) {
        if (hmsScanFrame == null || (hmsScanFrame.getYuvImage() == null && hmsScanFrame.getBitmap() == null)) {
            return new HmsScanResult(4, new HmsScan[0]);
        }
        o4.d("Scankit", "frame height " + hmsScanFrame.getHeight() + " width " + hmsScanFrame.getWidth());
        if (hmsScanFrame.getHeight() * hmsScanFrame.getHeight() > 52428800 || hmsScanFrame.getHeight() * hmsScanFrame.getHeight() == 0) {
            o4.e("ScanUtil", "input image is invalid:" + hmsScanFrame.getWidth() + " " + hmsScanFrame.getHeight());
            return new HmsScanResult(4, new HmsScan[0]);
        }
        try {
            if (g.d == Integer.MIN_VALUE) {
                g.d = g.a(context);
            }
            if (g.e == Integer.MIN_VALUE) {
                g.e = DynamicModule.getRemoteVersion(context.getApplicationContext(), MODULE_SCANKIT);
            }
        } catch (Exception unused) {
            o4.b("Scankit", "get remote version failed");
        }
        if (hmsScanFrameOptions != null && hmsScanFrameOptions.isMultiMode()) {
            HmsScan[] a2 = b.a(context, hmsScanFrame, new HmsScanAnalyzerOptions.Creator().setHmsScanTypes(hmsScanFrameOptions.getType(), new int[0]).setPhotoMode(hmsScanFrameOptions.isPhotoMode()).setParseResult(hmsScanFrameOptions.isParseResult()).create());
            return a2.length == 0 ? new HmsScanResult(4096, a2) : (a2.length == 1 && a2[0].getZoomValue() > 1.0d && TextUtils.isEmpty(a2[0].getOriginalValue())) ? new HmsScanResult(4098, a2) : (a2.length < 1 || TextUtils.isEmpty(a2[0].getOriginalValue())) ? new HmsScanResult(4096, a2) : new HmsScanResult(0, a2);
        }
        HmsScanAnalyzerOptions create = hmsScanFrameOptions == null ? new HmsScanAnalyzerOptions.Creator().create() : new HmsScanAnalyzerOptions.Creator().setHmsScanTypes(hmsScanFrameOptions.getType(), new int[0]).setPhotoMode(hmsScanFrameOptions.isPhotoMode()).setParseResult(hmsScanFrameOptions.isParseResult()).create();
        if (hmsScanFrame.getBitmap() != null) {
            return checkHmsScan(f.a(context, hmsScanFrame.getBitmap(), create), hmsScanFrameOptions);
        }
        if (hmsScanFrame.getYuvImage() == null) {
            return new HmsScanResult(4, new HmsScan[0]);
        }
        Log.i("scankit", "local version " + g.d + " remote version" + g.e);
        return checkVersion(g.d, g.e) ? f.a(context, hmsScanFrame, create) : f.a(context, hmsScanFrame.getYuvImage().getYuvData(), hmsScanFrame.getYuvImage().getWidth(), hmsScanFrame.getYuvImage().getHeight(), create);
    }

    public static HmsScan[] decodeWithBitmap(Context context, Bitmap bitmap, HmsScanAnalyzerOptions hmsScanAnalyzerOptions) {
        if (bitmap != null && bitmap.getWidth() * bitmap.getHeight() <= 52428800) {
            return f.a(context, bitmap, hmsScanAnalyzerOptions);
        }
        if (bitmap != null) {
            o4.e("ScanUtil", "input image is too large:" + bitmap.getWidth());
        }
        return new HmsScan[0];
    }

    public static HmsScan[] detectForHmsDector(Context context, MLFrame mLFrame, HmsScanAnalyzerOptions hmsScanAnalyzerOptions) {
        return b.a(context, mLFrame, hmsScanAnalyzerOptions);
    }

    private static Bitmap getBitmapFromUri(Context context, Uri uri, BitmapFactory.Options options) {
        if (uri == null) {
            o4.a("ScanBitmap", "uri == null");
            return null;
        }
        try {
            ParcelFileDescriptor openFileDescriptor = context.getContentResolver().openFileDescriptor(uri, "r");
            Bitmap decodeFileDescriptor = BitmapFactory.decodeFileDescriptor(openFileDescriptor.getFileDescriptor(), null, options);
            openFileDescriptor.close();
            return decodeFileDescriptor;
        } catch (FileNotFoundException unused) {
            o4.b(TrackConstants$Events.EXCEPTION, "FileNotFoundException");
            return null;
        } catch (IOException unused2) {
            o4.b(TrackConstants$Events.EXCEPTION, "IOException");
            return null;
        } catch (Exception unused3) {
            o4.b(TrackConstants$Events.EXCEPTION, "Exception");
            return null;
        }
    }

    public static boolean isScanAvailable(Context context) {
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:6:0x0033  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x003a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static int startScan(android.app.Activity r5, int r6, com.huawei.hms.ml.scan.HmsScanAnalyzerOptions r7) {
        /*
            java.lang.String r0 = "exception"
            java.lang.String r1 = "startScan before"
            java.lang.String r2 = "ScanUtil"
            com.huawei.hms.scankit.p.o4.d(r2, r1)
            android.content.pm.PackageManager r1 = r5.getPackageManager()     // Catch: java.lang.RuntimeException -> L1c android.content.pm.PackageManager.NameNotFoundException -> L22
            java.lang.String r3 = r5.getPackageName()     // Catch: java.lang.RuntimeException -> L1c android.content.pm.PackageManager.NameNotFoundException -> L22
            r4 = 16384(0x4000, float:2.2959E-41)
            android.content.pm.PackageInfo r1 = r1.getPackageInfo(r3, r4)     // Catch: java.lang.RuntimeException -> L1c android.content.pm.PackageManager.NameNotFoundException -> L22
            android.content.pm.ApplicationInfo r1 = r1.applicationInfo     // Catch: java.lang.RuntimeException -> L1c android.content.pm.PackageManager.NameNotFoundException -> L22
            int r0 = r1.targetSdkVersion     // Catch: java.lang.RuntimeException -> L1c android.content.pm.PackageManager.NameNotFoundException -> L22
            goto L29
        L1c:
            java.lang.String r1 = "RuntimeException"
            com.huawei.hms.scankit.p.o4.b(r0, r1)
            goto L27
        L22:
            java.lang.String r1 = "NameNotFoundException"
            com.huawei.hms.scankit.p.o4.b(r0, r1)
        L27:
            r0 = 28
        L29:
            java.lang.String r1 = "android.permission.CAMERA"
            boolean r0 = selfPermissionGranted(r5, r0, r1)
            boolean r1 = com.huawei.hms.scankit.p.w7.c
            if (r1 != 0) goto L3a
            java.lang.String r5 = "startScan failed"
            com.huawei.hms.scankit.p.o4.d(r2, r5)
            r5 = 3
            return r5
        L3a:
            if (r0 != 0) goto L45
            if (r7 == 0) goto L43
            boolean r0 = r7.showGuide
            if (r0 == 0) goto L43
            goto L45
        L43:
            r5 = 1
            return r5
        L45:
            android.content.Intent r0 = new android.content.Intent
            java.lang.Class<com.huawei.hms.hmsscankit.ScanKitActivity> r1 = com.huawei.hms.hmsscankit.ScanKitActivity.class
            r0.<init>(r5, r1)
            if (r7 == 0) goto L6a
            java.lang.String r1 = "ScanFormatValue"
            int r3 = r7.mode
            r0.putExtra(r1, r3)
            java.lang.String r1 = "ScanViewValue"
            int r3 = r7.viewType
            r0.putExtra(r1, r3)
            java.lang.String r1 = "ScanErrorCheck"
            boolean r3 = r7.errorCheck
            r0.putExtra(r1, r3)
            java.lang.String r1 = "ScanGuide"
            boolean r7 = r7.showGuide
            r0.putExtra(r1, r7)
        L6a:
            java.lang.String r7 = "startScan success"
            com.huawei.hms.scankit.p.o4.d(r2, r7)
            r7 = 0
            com.huawei.hms.scankit.p.w7.c = r7
            r5.startActivityForResult(r0, r6)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.hmsscankit.ScanUtil.startScan(android.app.Activity, int, com.huawei.hms.ml.scan.HmsScanAnalyzerOptions):int");
    }

    public static boolean selfPermissionGranted(Context context, int i, String str) {
        if (i >= 23) {
            if (w7.a(str) == null || context.checkSelfPermission(str) == 0) {
                return true;
            }
        } else if (w7.a(context, str) == 0) {
            return true;
        }
        return false;
    }
}
