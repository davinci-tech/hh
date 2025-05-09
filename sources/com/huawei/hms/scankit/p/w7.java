package com.huawei.hms.scankit.p;

import android.app.Activity;
import android.app.AppOpsManager;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Build;
import android.os.Process;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import com.huawei.hianalytics.core.transport.Utils;
import com.huawei.hms.framework.common.SystemPropUtils;
import com.huawei.hms.hmsscankit.ScanUtil;
import com.huawei.hms.ml.scan.HmsScan;
import com.huawei.hms.ml.scan.HmsScanBase;
import com.huawei.openalliance.ad.beans.inner.CountryCodeBean;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.constant.OsType;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Locale;

/* loaded from: classes4.dex */
public class w7 {

    /* renamed from: a, reason: collision with root package name */
    private static String f5915a = null;
    private static String b = null;
    public static boolean c = true;

    public static boolean a(int[][] iArr, int i) {
        return i >= 0 && i < iArr.length;
    }

    public static int b(int i) {
        if (i == 8191) {
            return HmsScanBase.ALL_SCAN_TYPE;
        }
        if (i == HmsScanBase.QRCODE_SCAN_TYPE) {
            return 256;
        }
        if (i == HmsScanBase.AZTEC_SCAN_TYPE) {
            return 4096;
        }
        if (i == HmsScanBase.DATAMATRIX_SCAN_TYPE) {
            return 16;
        }
        if (i == HmsScanBase.PDF417_SCAN_TYPE) {
            return 2048;
        }
        if (i == HmsScanBase.CODE39_SCAN_TYPE) {
            return 2;
        }
        if (i == HmsScanBase.CODE93_SCAN_TYPE) {
            return 4;
        }
        if (i == HmsScanBase.CODE128_SCAN_TYPE) {
            return 1;
        }
        if (i == HmsScanBase.EAN13_SCAN_TYPE) {
            return 32;
        }
        if (i == HmsScanBase.EAN8_SCAN_TYPE) {
            return 64;
        }
        if (i == HmsScanBase.ITF14_SCAN_TYPE) {
            return 128;
        }
        if (i == HmsScanBase.UPCCODE_A_SCAN_TYPE) {
            return 512;
        }
        if (i == HmsScanBase.UPCCODE_E_SCAN_TYPE) {
            return 1024;
        }
        if (i == HmsScanBase.CODABAR_SCAN_TYPE) {
            return 8;
        }
        if (i == HmsScanBase.WX_SCAN_TYPE) {
            return 16384;
        }
        if (i == HmsScanBase.MULTI_FUNCTIONAL_SCAN_TYPE) {
            return 8192;
        }
        return i;
    }

    public static boolean c(Context context) {
        if (b(context) && TextUtils.isEmpty(b)) {
            b = context.getSharedPreferences("scanExt", 0).getString("scanExt", "unSet");
        }
        return "forbid".equals(b);
    }

    public static int d(Context context) {
        int identifier;
        if (context.getResources() == null || (identifier = context.getResources().getIdentifier("status_bar_height", "dimen", OsType.ANDROID)) <= 0) {
            return 0;
        }
        return context.getResources().getDimensionPixelSize(identifier);
    }

    public static boolean f(Context context) {
        return (context instanceof Activity) && ((Activity) context).isInMultiWindowMode();
    }

    public static boolean h(Context context) {
        Display defaultDisplay = ((WindowManager) context.getSystemService(Constants.NATIVE_WINDOW_SUB_DIR)).getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        defaultDisplay.getMetrics(displayMetrics);
        return Math.sqrt(Math.pow((double) (((float) displayMetrics.widthPixels) / displayMetrics.xdpi), 2.0d) + Math.pow((double) (((float) displayMetrics.heightPixels) / displayMetrics.ydpi), 2.0d)) >= 5.5d;
    }

    public static boolean i(Context context) {
        Display defaultDisplay = ((WindowManager) context.getSystemService(Constants.NATIVE_WINDOW_SUB_DIR)).getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        defaultDisplay.getMetrics(displayMetrics);
        return Math.sqrt(Math.pow((double) (((float) displayMetrics.widthPixels) / displayMetrics.xdpi), 2.0d) + Math.pow((double) (((float) displayMetrics.heightPixels) / displayMetrics.ydpi), 2.0d)) >= 8.0d && ((float) displayMetrics.widthPixels) / ((float) displayMetrics.heightPixels) > 1.3f;
    }

    public static boolean j(Context context) {
        Display defaultDisplay = ((WindowManager) context.getSystemService(Constants.NATIVE_WINDOW_SUB_DIR)).getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        defaultDisplay.getMetrics(displayMetrics);
        return Math.sqrt(Math.pow((double) (((float) displayMetrics.widthPixels) / displayMetrics.xdpi), 2.0d) + Math.pow((double) (((float) displayMetrics.heightPixels) / displayMetrics.ydpi), 2.0d)) >= 7.0d;
    }

    public static boolean k(Context context) {
        return Settings.Global.getInt(context.getContentResolver(), a(), 0) == 0;
    }

    public static boolean a(int[] iArr, int i) {
        return i >= 0 && i < iArr.length;
    }

    public static boolean e() {
        try {
            return "-1".equalsIgnoreCase(SystemPropUtils.getProperty("get", "sys.hw_multiwin_for_camera", "android.os.SystemProperties", "UNKNOWN"));
        } catch (RuntimeException | Exception unused) {
            return false;
        }
    }

    public static boolean f() {
        String str = Build.BRAND;
        return str != null && str.toLowerCase().equals("samsung");
    }

    public static boolean g(Context context) {
        try {
            return "CN".equalsIgnoreCase(SystemPropUtils.getProperty("get", CountryCodeBean.VENDORCOUNTRY_SYSTEMPROP, "android.os.SystemProperties", "UNKNOWN"));
        } catch (RuntimeException | Exception unused) {
            return false;
        }
    }

    public static boolean a(byte[] bArr, int i) {
        return i >= 0 && i < bArr.length;
    }

    public static boolean a(byte[][] bArr, int i) {
        return i >= 0 && i < bArr.length;
    }

    public static boolean e(Context context) {
        String configuration = context.getResources().getConfiguration().toString();
        return configuration.contains("hwMultiwindow-magic") || configuration.contains("hw-magic-windows");
    }

    public static boolean a(String[] strArr, int i) {
        return i >= 0 && i < strArr.length;
    }

    public static boolean d() {
        return "ar".equals(Locale.getDefault().getLanguage()) || Constants.URDU_LANG.equals(Locale.getDefault().getLanguage()) || "ug".equals(Locale.getDefault().getLanguage()) || "iw".equals(Locale.getDefault().getLanguage()) || "fa".equals(Locale.getDefault().getLanguage());
    }

    public static boolean a(float[] fArr, int i) {
        return i >= 0 && i < fArr.length;
    }

    public static HmsScan[] a(HmsScan[] hmsScanArr) {
        if (hmsScanArr != null && hmsScanArr.length != 0) {
            for (HmsScan hmsScan : hmsScanArr) {
                if (hmsScan != null) {
                    hmsScan.scanType = b(hmsScan.scanType);
                }
            }
        }
        return hmsScanArr;
    }

    public static boolean c(Activity activity) {
        int rotation = activity.getWindowManager().getDefaultDisplay().getRotation();
        return rotation == 0 || rotation == 2;
    }

    public static boolean c() {
        String str = Build.MANUFACTURER;
        return str.equalsIgnoreCase("HUAWEI") || str.equalsIgnoreCase("honor");
    }

    public static int a(int i) {
        if (i <= 0) {
            return HmsScanBase.ALL_SCAN_TYPE;
        }
        if (((i - 1) & i) == 0) {
            return i;
        }
        int i2 = HmsScanBase.AZTEC_SCAN_TYPE;
        int b2 = (i & i2) != 0 ? b(i2) : 0;
        int i3 = HmsScanBase.CODABAR_SCAN_TYPE;
        if ((i & i3) != 0) {
            b2 |= b(i3);
        }
        int i4 = HmsScanBase.CODE39_SCAN_TYPE;
        if ((i & i4) != 0) {
            b2 |= b(i4);
        }
        int i5 = HmsScanBase.CODE93_SCAN_TYPE;
        if ((i & i5) != 0) {
            b2 |= b(i5);
        }
        int i6 = HmsScanBase.CODE128_SCAN_TYPE;
        if ((i & i6) != 0) {
            b2 |= b(i6);
        }
        int i7 = HmsScanBase.DATAMATRIX_SCAN_TYPE;
        if ((i & i7) != 0) {
            b2 |= b(i7);
        }
        int i8 = HmsScanBase.EAN8_SCAN_TYPE;
        if ((i & i8) != 0) {
            b2 |= b(i8);
        }
        int i9 = HmsScanBase.EAN13_SCAN_TYPE;
        if ((i & i9) != 0) {
            b2 |= b(i9);
        }
        int i10 = HmsScanBase.QRCODE_SCAN_TYPE;
        if ((i & i10) != 0) {
            b2 |= b(i10);
        }
        int i11 = HmsScanBase.ITF14_SCAN_TYPE;
        if ((i & i11) != 0) {
            b2 |= b(i11);
        }
        int i12 = HmsScanBase.PDF417_SCAN_TYPE;
        if ((i & i12) != 0) {
            b2 |= b(i12);
        }
        int i13 = HmsScanBase.UPCCODE_A_SCAN_TYPE;
        if ((i & i13) != 0) {
            b2 |= b(i13);
        }
        int i14 = HmsScanBase.UPCCODE_E_SCAN_TYPE;
        if ((i & i14) != 0) {
            b2 |= b(i14);
        }
        int i15 = HmsScanBase.MULTI_FUNCTIONAL_SCAN_TYPE;
        if ((i & i15) != 0) {
            b2 |= b(i15);
        }
        int i16 = HmsScanBase.WX_SCAN_TYPE;
        return (i & i16) != 0 ? b2 | b(i16) : b2;
    }

    public static boolean b() {
        try {
            return "-1".equalsIgnoreCase(SystemPropUtils.getProperty("get", "sys.multiwin_for_camera", "android.os.SystemProperties", "UNKNOWN"));
        } catch (RuntimeException | Exception unused) {
            return false;
        }
    }

    public static boolean b(Activity activity) {
        return a(activity) == 102;
    }

    public static boolean b(Context context) {
        if (TextUtils.isEmpty(f5915a)) {
            try {
                f5915a = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData.getString("scanExt", "unSet");
            } catch (PackageManager.NameNotFoundException unused) {
                Log.e("scanerror", "checkPermission NameNotFoundException");
            } catch (Exception unused2) {
                Log.e("scanerror", "checkPermission Exception");
            }
        }
        return "readUri".equals(f5915a);
    }

    private static String b(Context context, Intent intent) {
        Uri data = new Intent(intent).getData();
        if (DocumentsContract.isDocumentUri(context, data)) {
            String documentId = DocumentsContract.getDocumentId(data);
            if ("com.android.providers.media.documents".equals(data.getAuthority())) {
                return a(context, MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "_id=" + documentId.split(":")[1]);
            }
            if (!"com.android.providers.downloads.documents".equals(data.getAuthority())) {
                return null;
            }
            try {
                return a(context, ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.parseLong(documentId)), (String) null);
            } catch (NumberFormatException unused) {
                o4.b("ScankitUtils", "NumberFormatException in withAppendedId");
                return null;
            } catch (Exception unused2) {
                o4.b("ScankitUtils", "Exception in withAppendedId");
                return null;
            }
        }
        if ("content".equalsIgnoreCase(data.getScheme())) {
            return a(context, data, (String) null);
        }
        return null;
    }

    public static Bitmap a(Bitmap bitmap, int i, int i2) {
        if (bitmap.getWidth() <= 0 || bitmap.getHeight() <= 0) {
            return null;
        }
        return a(bitmap, i / bitmap.getWidth(), i2 / bitmap.getHeight());
    }

    public static Bitmap a(Bitmap bitmap, float f, float f2) {
        if (f <= 0.0f || f2 <= 0.0f) {
            return null;
        }
        float f3 = 1.0f / f;
        float f4 = 1.0f / f2;
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int i = (int) (width * f);
        int i2 = (int) (height * f2);
        int[] iArr = new int[width * height];
        bitmap.getPixels(iArr, 0, width, 0, 0, width, height);
        int[] iArr2 = new int[i * i2];
        for (int i3 = 0; i3 < i2; i3++) {
            for (int i4 = 0; i4 < i; i4++) {
                iArr2[(i3 * i) + i4] = iArr[(((int) (i3 * f4)) * width) + ((int) (i4 * f3))];
            }
        }
        Log.d(">>>", "dstPixels:" + i + " x " + i2);
        Bitmap createBitmap = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
        createBitmap.setPixels(iArr2, 0, i, 0, 0, i, i2);
        return createBitmap;
    }

    public static int a(Context context, String str) {
        return context.checkPermission(str, Process.myPid(), Process.myUid()) == -1 ? -1 : 0;
    }

    public static String a(String str) {
        return AppOpsManager.permissionToOp(str);
    }

    private static String a() {
        String str = Build.BRAND;
        return (TextUtils.isEmpty(str) || str.equalsIgnoreCase("HUAWEI")) ? Constants.NAVIGATIONBAR_IS_MIN : str.equalsIgnoreCase("XIAOMI") ? "force_fsg_nav_bar" : (str.equalsIgnoreCase("VIVO") || str.equalsIgnoreCase("OPPO")) ? "navigation_gesture_on" : Constants.NAVIGATIONBAR_IS_MIN;
    }

    public static ResolveInfo a(Intent intent, String str, Activity activity) {
        intent.setPackage(str);
        List<ResolveInfo> queryIntentActivities = activity.getPackageManager().queryIntentActivities(intent, 0);
        if (queryIntentActivities.isEmpty()) {
            return null;
        }
        return queryIntentActivities.get(0);
    }

    public static boolean a(String str, Activity activity) {
        PackageInfo packageInfo;
        try {
            packageInfo = activity.getPackageManager().getPackageInfo(str, 0);
        } catch (PackageManager.NameNotFoundException unused) {
            o4.d(Utils.TAG, "NameNotFoundException Exception");
            packageInfo = null;
        }
        if (packageInfo == null) {
            return false;
        }
        int i = packageInfo.applicationInfo.flags;
        return ((i & 1) == 1) || ((i & 128) == 1);
    }

    public static int a(Activity activity) {
        try {
            Class<?> cls = Class.forName("com.huawei.android.app.ActivityManagerEx");
            Method declaredMethod = cls.getDeclaredMethod("getActivityWindowMode", Activity.class);
            declaredMethod.setAccessible(true);
            Object invoke = declaredMethod.invoke(cls, activity);
            if (invoke == null) {
                return -1;
            }
            return Integer.valueOf(String.valueOf(invoke)).intValue();
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | NumberFormatException | InvocationTargetException unused) {
            return -1;
        }
    }

    public static Bitmap a(Bitmap bitmap, float f) {
        if (bitmap == null) {
            return null;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.setRotate(f, width / 2, height / 2);
        Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, false);
        if (!createBitmap.equals(bitmap) && !bitmap.isRecycled()) {
            bitmap.recycle();
        }
        return createBitmap;
    }

    public static Bitmap a(Bitmap bitmap, int i) {
        if (i == 0) {
            return a(bitmap, 90.0f);
        }
        if (i != 2) {
            return i != 3 ? bitmap : a(bitmap, 180.0f);
        }
        return a(bitmap, 270.0f);
    }

    public static Bitmap a(Context context, Intent intent) {
        Bitmap a2;
        Bitmap compressBitmap;
        if (!b(context)) {
            String b2 = b(context, intent);
            if (TextUtils.isEmpty(b2)) {
                return null;
            }
            if (Build.VERSION.SDK_INT > 28 && context.getApplicationInfo() != null && context.getApplicationInfo().targetSdkVersion > 28) {
                o4.a("ScanBitmap", "compressBitmap above android 29");
                compressBitmap = ScanUtil.compressBitmapForAndroid29(context, b2);
            } else {
                o4.a("ScanBitmap", "compressBitmap below android 29");
                compressBitmap = ScanUtil.compressBitmap(context, b2);
            }
            if (compressBitmap != null) {
                return compressBitmap;
            }
            o4.a("ScanBitmap", "compressBitmap above android 29");
            return ScanUtil.compressBitmapForAndroid29(context, b2);
        }
        Uri data = intent.getData();
        if (data == null || (a2 = a(context, data)) == null) {
            return null;
        }
        return a2;
    }

    private static String a(Context context, Uri uri, String str) {
        try {
            Cursor query = context.getContentResolver().query(uri, new String[]{"_data"}, str, null, null);
            if (query != null) {
                r8 = query.moveToFirst() ? query.getString(query.getColumnIndex("_data")) : null;
                query.close();
            }
            return r8;
        } catch (IllegalStateException unused) {
            o4.b("ScankitUtils", "IllegalStateException in getImagePath");
            return null;
        } catch (Exception unused2) {
            o4.b("ScankitUtils", "Exception in getImagePath");
            return null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0051 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static android.graphics.Bitmap a(android.content.Context r7, android.net.Uri r8) {
        /*
            java.lang.String r0 = "IOException in getImagePath"
            java.lang.String r1 = "ScankitUtils"
            android.graphics.BitmapFactory$Options r2 = new android.graphics.BitmapFactory$Options
            r2.<init>()
            r3 = 1
            r2.inJustDecodeBounds = r3
            android.content.Context r3 = r7.getApplicationContext()
            android.content.ContentResolver r3 = r3.getContentResolver()
            r4 = 0
            java.io.InputStream r8 = r3.openInputStream(r8)     // Catch: java.lang.Throwable -> L3d java.io.IOException -> L3f
            if (r8 == 0) goto L37
            byte[] r3 = a(r8)     // Catch: java.lang.Throwable -> L34 java.io.IOException -> L40
            int r5 = r3.length     // Catch: java.lang.Throwable -> L34 java.io.IOException -> L40
            r6 = 0
            android.graphics.BitmapFactory.decodeByteArray(r3, r6, r5, r2)     // Catch: java.lang.Throwable -> L34 java.io.IOException -> L40
            a(r7, r2)     // Catch: java.lang.Throwable -> L34 java.io.IOException -> L40
            int r7 = r3.length     // Catch: java.lang.Throwable -> L34 java.io.IOException -> L40
            android.graphics.Bitmap r7 = android.graphics.BitmapFactory.decodeByteArray(r3, r6, r7, r2)     // Catch: java.lang.Throwable -> L34 java.io.IOException -> L40
            r8.close()     // Catch: java.io.IOException -> L30
            goto L33
        L30:
            com.huawei.hms.scankit.p.o4.b(r1, r0)
        L33:
            return r7
        L34:
            r7 = move-exception
            r4 = r8
            goto L4f
        L37:
            if (r8 == 0) goto L4e
            r8.close()     // Catch: java.io.IOException -> L4b
            goto L4e
        L3d:
            r7 = move-exception
            goto L4f
        L3f:
            r8 = r4
        L40:
            java.lang.String r7 = "compressBitmapFromUri IOException"
            com.huawei.hms.scankit.p.o4.b(r1, r7)     // Catch: java.lang.Throwable -> L34
            if (r8 == 0) goto L4e
            r8.close()     // Catch: java.io.IOException -> L4b
            goto L4e
        L4b:
            com.huawei.hms.scankit.p.o4.b(r1, r0)
        L4e:
            return r4
        L4f:
            if (r4 == 0) goto L58
            r4.close()     // Catch: java.io.IOException -> L55
            goto L58
        L55:
            com.huawei.hms.scankit.p.o4.b(r1, r0)
        L58:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.scankit.p.w7.a(android.content.Context, android.net.Uri):android.graphics.Bitmap");
    }

    public static byte[] a(InputStream inputStream) throws IOException {
        byte[] bArr = new byte[1024];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        while (true) {
            int read = inputStream.read(bArr);
            if (read != -1) {
                byteArrayOutputStream.write(bArr, 0, read);
            } else {
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                byteArrayOutputStream.close();
                return byteArray;
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0044  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x004c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static void a(android.content.Context r7, android.graphics.BitmapFactory.Options r8) {
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
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.scankit.p.w7.a(android.content.Context, android.graphics.BitmapFactory$Options):void");
    }

    public static boolean a(Context context) {
        return b(context) || context.checkPermission("android.permission.CAMERA", Process.myPid(), Process.myUid()) == 0;
    }
}
