package com.huawei.watchface;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorSpace;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.media.ExifInterface;
import android.net.Uri;
import android.opengl.GLES10;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.huawei.secure.android.common.util.SafeString;
import com.huawei.watchface.api.HwWatchFaceBtManager;
import com.huawei.watchface.environment.Environment;
import com.huawei.watchface.manager.WatchFacePhotoAlbumManager;
import com.huawei.watchface.mvp.model.crypt.PversionSdUtils;
import com.huawei.watchface.mvp.model.datatype.ClipOptions;
import com.huawei.watchface.mvp.model.datatype.WatchFaceSupportInfo;
import com.huawei.watchface.mvp.model.latona.provider.WatchFaceProvider;
import com.huawei.watchface.mvp.ui.activity.ClipImageActivity;
import com.huawei.watchface.utils.BackgroundTaskUtils;
import com.huawei.watchface.utils.CommonUtils;
import com.huawei.watchface.utils.FileHelper;
import com.huawei.watchface.utils.HwLog;
import com.huawei.watchface.utils.HwSfpPolicyManagerHelper;
import com.huawei.watchface.videoedit.utils.Utils;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.egl.EGLSurface;

/* loaded from: classes7.dex */
public class de {
    private static ArrayList<String> d;
    private static ArrayList<String> e;
    private static int g;

    /* renamed from: a, reason: collision with root package name */
    private static final Object f10984a = new Object();
    private static String[] b = {".png", ".jpg", ".jpeg", ".bmp", Utils.SUFFIX_GIF, ".psd", WatchFaceProvider.SVG_SUFFIX, ".tif", ".tiff", ".webp", ".pcx"};
    private static String[] c = {".png", ".jpg", ".jpeg"};
    private static String f = HwSfpPolicyManagerHelper.getFileDataUserPath() + File.separator + "watchfaceSearchCache";
    private static ConcurrentHashMap<String, HashMap<String, String>> h = new ConcurrentHashMap<>();

    public static List<String> a() {
        ArrayList<String> arrayList;
        synchronized (f10984a) {
            if (d == null) {
                d = new ArrayList<>(32);
                for (String str : b) {
                    d.add(str);
                }
                HwLog.i("ImageClipper", "SuffixContainers : " + d.toString());
            }
            arrayList = d;
        }
        return arrayList;
    }

    public static List<String> b() {
        synchronized (f10984a) {
            ArrayList<String> arrayList = e;
            if (arrayList != null) {
                return arrayList;
            }
            e = new ArrayList<>(32);
            for (String str : c) {
                e.add(str);
            }
            HwLog.i("ImageClipper", "sSupportSearchSuffixes : " + e.toString());
            return e;
        }
    }

    public static ConcurrentHashMap<String, HashMap<String, String>> c() {
        return h;
    }

    public static String a(Bitmap bitmap) {
        String str;
        try {
            HwLog.i("ImageClipper", "saveSearchImage enter");
            str = f + File.separator + new SimpleDateFormat("yyyyMMddHHmmssSSS", Locale.ENGLISH).format(new Date()) + "_orig.png";
        } catch (Exception e2) {
            HwLog.e("ImageClipper", "saveSearchImage Exception:" + HwLog.printException(e2));
        }
        if (a(bitmap, str, 60)) {
            return str;
        }
        return null;
    }

    public static void d() {
        HwLog.i("ImageClipper", "clearSearchImageFile enter");
        File file = new File(f);
        if (!file.exists()) {
            HwLog.i("ImageClipper", "clearSearchImageFile rootSearchFilePath is not exists");
        } else {
            CommonUtils.b(file);
        }
    }

    public static Bitmap a(Context context, String str, Uri uri) {
        File file = new File(str);
        if (!file.exists() || file.isDirectory()) {
            HwLog.i("ImageClipper", "prepareOriginalImage() PrepareFileNULL");
            return null;
        }
        HwLog.i("ImageClipper", "prepareOriginalImage() PrepareUri is NULL");
        if (uri == null) {
            HwLog.i("ImageClipper", "prepareOriginalImage() CreateUriFromFile");
            uri = a(context.getApplicationContext(), str);
            StringBuilder sb = new StringBuilder("prepareOriginalImage() CreatedUri : ");
            sb.append(uri != null);
            HwLog.i("ImageClipper", sb.toString());
        }
        e();
        Bitmap a2 = a(context, str, uri, e(context, str, uri));
        int c2 = c(context, str, uri);
        HwLog.i("ImageClipper", "prepareOriginalImage() angle: " + c2);
        return (c2 <= 0 || a2 == null) ? a2 : a(c2, a2);
    }

    public static int a(String str) {
        if (str == null) {
            HwLog.i("ImageClipper", "prepareOriginalImage() path is null.");
            return -1;
        }
        String c2 = c(str);
        HwLog.i("ImageClipper", "prepareOriginalImage() fileSuffix : " + c2);
        if (TextUtils.isEmpty(c2)) {
            return -1;
        }
        if (a().contains(c2.toLowerCase(Locale.ENGLISH))) {
            return 0;
        }
        HwLog.i("ImageClipper", "prepareOriginalImage() SourceFile is not supported");
        return 1;
    }

    public static int b(String str) {
        if (str == null) {
            HwLog.i("ImageClipper", "checkSearchFilePath() path is null.");
            return -1;
        }
        String c2 = c(str);
        HwLog.i("ImageClipper", "checkSearchFilePath() fileSuffix : " + c2);
        if (TextUtils.isEmpty(c2)) {
            return -1;
        }
        if (b().contains(c2.toLowerCase(Locale.ENGLISH))) {
            return 0;
        }
        HwLog.i("ImageClipper", "checkSearchFilePath() SourceFile is not supported");
        return 1;
    }

    public static String c(String str) {
        if (str == null) {
            HwLog.i("ImageClipper", "getSuffix() path is null.");
            return null;
        }
        return SafeString.substring(str, str.lastIndexOf("."));
    }

    private static int c(Context context, String str, Uri uri) {
        int i;
        HwLog.i("ImageClipper", "readPictureAngle enter");
        ExifInterface d2 = d(context, str, uri);
        int i2 = 0;
        if (d2 != null) {
            int attributeInt = d2.getAttributeInt(androidx.exifinterface.media.ExifInterface.TAG_ORIENTATION, 1);
            if (attributeInt == 3) {
                i = 180;
            } else if (attributeInt == 6) {
                i = 90;
            } else if (attributeInt != 8) {
                HwLog.w("ImageClipper", "orientation:" + attributeInt);
                HwLog.i("ImageClipper", "readPictureAngle angle:" + i2);
            } else {
                i = 270;
            }
            i2 = i;
            HwLog.i("ImageClipper", "readPictureAngle angle:" + i2);
        }
        return i2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1 */
    /* JADX WARN: Type inference failed for: r0v13 */
    /* JADX WARN: Type inference failed for: r0v3 */
    /* JADX WARN: Type inference failed for: r0v4, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r5v10 */
    /* JADX WARN: Type inference failed for: r5v18, types: [android.os.ParcelFileDescriptor] */
    /* JADX WARN: Type inference failed for: r5v2 */
    /* JADX WARN: Type inference failed for: r5v3 */
    /* JADX WARN: Type inference failed for: r5v4 */
    /* JADX WARN: Type inference failed for: r5v6 */
    /* JADX WARN: Type inference failed for: r5v7, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r5v8 */
    private static ExifInterface d(Context context, String str, Uri uri) {
        Throwable th;
        ?? r5;
        FileInputStream fileInputStream;
        Closeable closeable;
        ExifInterface exifInterface;
        Closeable closeable2;
        HwLog.i("ImageClipper", "getExifInterface start");
        ?? r0 = 0;
        ExifInterface exifInterface2 = null;
        r0 = 0;
        FileInputStream fileInputStream2 = null;
        ExifInterface exifInterface3 = null;
        try {
            try {
                if (Build.VERSION.SDK_INT > 28) {
                    HwLog.i("ImageClipper", "android Q");
                    if (uri == null) {
                        HwLog.w("ImageClipper", "uri is null");
                        cq.a(null);
                        cq.a(null);
                        return null;
                    }
                    r5 = context.getApplicationContext().getContentResolver().openFileDescriptor(uri, "r");
                    if (r5 != null) {
                        try {
                            fileInputStream = new FileInputStream(r5.getFileDescriptor());
                        } catch (IOException unused) {
                            fileInputStream = null;
                            HwLog.e("ImageClipper", "getExifInterface IOException");
                            closeable = r5;
                            cq.a(fileInputStream);
                            cq.a(closeable);
                            HwLog.i("ImageClipper", "getExifInterface end");
                            return exifInterface3;
                        } catch (IllegalStateException unused2) {
                            fileInputStream = null;
                            HwLog.e("ImageClipper", "IllegalStateException getExifInterface");
                            closeable = r5;
                            cq.a(fileInputStream);
                            cq.a(closeable);
                            HwLog.i("ImageClipper", "getExifInterface end");
                            return exifInterface3;
                        } catch (Throwable th2) {
                            th = th2;
                            cq.a(r0);
                            cq.a(r5);
                            throw th;
                        }
                    } else {
                        fileInputStream = null;
                    }
                    if (fileInputStream != null) {
                        try {
                            exifInterface2 = new ExifInterface(fileInputStream);
                        } catch (IOException unused3) {
                            HwLog.e("ImageClipper", "getExifInterface IOException");
                            closeable = r5;
                            cq.a(fileInputStream);
                            cq.a(closeable);
                            HwLog.i("ImageClipper", "getExifInterface end");
                            return exifInterface3;
                        } catch (IllegalStateException unused4) {
                            HwLog.e("ImageClipper", "IllegalStateException getExifInterface");
                            closeable = r5;
                            cq.a(fileInputStream);
                            cq.a(closeable);
                            HwLog.i("ImageClipper", "getExifInterface end");
                            return exifInterface3;
                        }
                    }
                    ExifInterface exifInterface4 = exifInterface2;
                    fileInputStream2 = fileInputStream;
                    exifInterface = exifInterface4;
                    closeable2 = r5;
                } else {
                    HwLog.i("ImageClipper", "android less than Q");
                    exifInterface = new ExifInterface(str);
                    closeable2 = null;
                }
                cq.a(fileInputStream2);
                cq.a(closeable2);
                exifInterface3 = exifInterface;
            } catch (Throwable th3) {
                th = th3;
                r0 = str;
                r5 = context;
            }
        } catch (IOException unused5) {
            r5 = null;
            fileInputStream = null;
        } catch (IllegalStateException unused6) {
            r5 = null;
            fileInputStream = null;
        } catch (Throwable th4) {
            th = th4;
            r5 = null;
        }
        HwLog.i("ImageClipper", "getExifInterface end");
        return exifInterface3;
    }

    private static Bitmap a(int i, Bitmap bitmap) {
        Bitmap bitmap2;
        Matrix matrix = new Matrix();
        matrix.postRotate(i);
        try {
            bitmap2 = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        } catch (OutOfMemoryError unused) {
            HwLog.e("ImageClipper", "rotateImageView OutOfMemoryError");
            bitmap2 = null;
        }
        if (bitmap2 == null) {
            bitmap2 = bitmap;
        }
        if (bitmap != bitmap2) {
            bitmap.recycle();
        }
        return bitmap2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x004e, code lost:
    
        if (r9 != null) goto L22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x005e, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x005b, code lost:
    
        r9.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0059, code lost:
    
        if (r9 == null) goto L23;
     */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0064  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static android.net.Uri a(android.content.Context r9, java.lang.String r10) {
        /*
            java.lang.String r0 = "Call getMediaUriFromPath"
            java.lang.String r1 = "ImageClipper"
            com.huawei.watchface.utils.HwLog.i(r1, r0)
            r0 = 0
            android.net.Uri r8 = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI     // Catch: java.lang.Throwable -> L51 java.lang.Throwable -> L53
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L51 java.lang.Throwable -> L53
            java.lang.String r3 = "MediaStoreUri : "
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L51 java.lang.Throwable -> L53
            if (r8 == 0) goto L15
            r3 = 1
            goto L16
        L15:
            r3 = 0
        L16:
            r2.append(r3)     // Catch: java.lang.Throwable -> L51 java.lang.Throwable -> L53
            java.lang.String r2 = r2.toString()     // Catch: java.lang.Throwable -> L51 java.lang.Throwable -> L53
            com.huawei.watchface.utils.HwLog.i(r1, r2)     // Catch: java.lang.Throwable -> L51 java.lang.Throwable -> L53
            if (r8 != 0) goto L23
            return r0
        L23:
            java.lang.String[] r6 = new java.lang.String[]{r10}     // Catch: java.lang.Throwable -> L51 java.lang.Throwable -> L53
            android.content.ContentResolver r2 = r9.getContentResolver()     // Catch: java.lang.Throwable -> L51 java.lang.Throwable -> L53
            r4 = 0
            java.lang.String r5 = "_data = ?"
            r7 = 0
            r3 = r8
            android.database.Cursor r9 = r2.query(r3, r4, r5, r6, r7)     // Catch: java.lang.Throwable -> L51 java.lang.Throwable -> L53
            boolean r10 = r9.moveToFirst()     // Catch: java.lang.Throwable -> L54 java.lang.Throwable -> L5f
            if (r10 == 0) goto L4e
            java.lang.String r10 = "Cursor.MoveToFirst"
            com.huawei.watchface.utils.HwLog.i(r1, r10)     // Catch: java.lang.Throwable -> L54 java.lang.Throwable -> L5f
            java.lang.String r10 = "_id"
            int r10 = r9.getColumnIndex(r10)     // Catch: java.lang.Throwable -> L54 java.lang.Throwable -> L5f
            long r2 = r9.getLong(r10)     // Catch: java.lang.Throwable -> L54 java.lang.Throwable -> L5f
            android.net.Uri r10 = android.content.ContentUris.withAppendedId(r8, r2)     // Catch: java.lang.Throwable -> L54 java.lang.Throwable -> L5f
            r0 = r10
        L4e:
            if (r9 == 0) goto L5e
            goto L5b
        L51:
            r9 = move-exception
            goto L62
        L53:
            r9 = r0
        L54:
            java.lang.String r10 = "cursor query SQLException"
            com.huawei.watchface.utils.HwLog.i(r1, r10)     // Catch: java.lang.Throwable -> L5f
            if (r9 == 0) goto L5e
        L5b:
            r9.close()
        L5e:
            return r0
        L5f:
            r10 = move-exception
            r0 = r9
            r9 = r10
        L62:
            if (r0 == 0) goto L67
            r0.close()
        L67:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.watchface.de.a(android.content.Context, java.lang.String):android.net.Uri");
    }

    private static void e() {
        synchronized (f10984a) {
            int i = g;
            if (i == 0 || i == 1080) {
                g();
            }
        }
    }

    private static void f() {
        int[] iArr = new int[1];
        GLES10.glGetIntegerv(3379, iArr, 0);
        synchronized (f10984a) {
            g = iArr[0];
            HwLog.i("ImageClipper", "MaxTextureSizeBelowLollipop : " + g);
            if (g == 0) {
                g = 1080;
            }
        }
    }

    private static void g() {
        EGL10 egl10 = (EGL10) EGLContext.getEGL();
        EGLDisplay eglGetDisplay = egl10.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);
        egl10.eglInitialize(eglGetDisplay, new int[2]);
        EGLConfig[] eGLConfigArr = new EGLConfig[1];
        int[] iArr = new int[1];
        egl10.eglChooseConfig(eglGetDisplay, new int[]{12351, 12430, 12329, 0, 12339, 1, 12344}, eGLConfigArr, 1, iArr);
        if (iArr[0] == 0) {
            HwLog.i("ImageClipper", "wrong numConfig 0");
            return;
        }
        EGLConfig eGLConfig = eGLConfigArr[0];
        EGLSurface eglCreatePbufferSurface = egl10.eglCreatePbufferSurface(eglGetDisplay, eGLConfig, new int[]{12375, 64, 12374, 64, 12344});
        EGLContext eglCreateContext = egl10.eglCreateContext(eglGetDisplay, eGLConfig, EGL10.EGL_NO_CONTEXT, new int[]{12440, 1, 12344});
        egl10.eglMakeCurrent(eglGetDisplay, eglCreatePbufferSurface, eglCreatePbufferSurface, eglCreateContext);
        int[] iArr2 = new int[1];
        GLES10.glGetIntegerv(3379, iArr2, 0);
        egl10.eglMakeCurrent(eglGetDisplay, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_CONTEXT);
        egl10.eglDestroySurface(eglGetDisplay, eglCreatePbufferSurface);
        egl10.eglDestroyContext(eglGetDisplay, eglCreateContext);
        egl10.eglTerminate(eglGetDisplay);
        synchronized (f10984a) {
            g = iArr2[0];
            HwLog.i("ImageClipper", "MaxSizeOverLollipop: " + g);
            if (g == 0) {
                g = 1080;
            }
        }
    }

    private static int e(Context context, String str, Uri uri) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        if (Build.VERSION.SDK_INT <= 28) {
            try {
                BitmapFactory.decodeFile(str, options);
            } catch (Exception unused) {
                HwLog.e("ImageClipper", "decodeFile IOException");
            } catch (OutOfMemoryError unused2) {
                HwLog.e("ImageClipper", "decodeFile:OutOfMemoryError");
            }
        } else {
            if (uri == null) {
                HwLog.i("ImageClipper", "calculateInSampleSize uri null");
                return 1;
            }
            ParcelFileDescriptor parcelFileDescriptor = null;
            try {
                try {
                    try {
                        try {
                            parcelFileDescriptor = context.getContentResolver().openFileDescriptor(uri, "r");
                            if (parcelFileDescriptor != null) {
                                BitmapFactory.decodeFileDescriptor(parcelFileDescriptor.getFileDescriptor(), new Rect(), options);
                            }
                        } catch (IllegalStateException unused3) {
                            HwLog.e("ImageClipper", "IllegalStateException calculateInSampleSize");
                        }
                    } catch (IOException unused4) {
                        HwLog.e("ImageClipper", "calculateInSampleSize IOException");
                    }
                } catch (Exception unused5) {
                    HwLog.e("ImageClipper", "calculateInSampleSize exception");
                } catch (OutOfMemoryError unused6) {
                    HwLog.e("ImageClipper", "calculateInSampleSize:OutOfMemoryError");
                }
            } finally {
                cq.a(parcelFileDescriptor);
            }
        }
        HwLog.i("ImageClipper", "Finally decode bounds : {" + options.outWidth + ", " + options.outHeight + " }");
        int a2 = a(options);
        StringBuilder sb = new StringBuilder("calculated inSampleSize : ");
        sb.append(a2);
        HwLog.i("ImageClipper", sb.toString());
        return a2;
    }

    private static int a(BitmapFactory.Options options) {
        int i;
        int i2 = options.outWidth;
        int i3 = options.outHeight;
        synchronized (f10984a) {
            i = 1;
            while (true) {
                int i4 = g;
                if (i2 <= i4 && i3 <= i4 && i2 * i3 * 4 <= 67108864) {
                }
                i *= 2;
                i2 /= 2;
                i3 /= 2;
            }
        }
        return i;
    }

    private static Bitmap a(Context context, String str, Uri uri, int i) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = false;
        options.inSampleSize = i;
        options.inPreferredColorSpace = ColorSpace.get(ColorSpace.Named.SRGB);
        options.inPurgeable = true;
        options.inDither = false;
        options.inInputShareable = true;
        Bitmap a2 = a(context, options, str, uri);
        if (a2 != null) {
            HwLog.i("ImageClipper", "decodeBitmap: {" + a2.getWidth() + ", " + a2.getHeight() + "}");
        }
        return a2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0, types: [android.content.Context] */
    /* JADX WARN: Type inference failed for: r4v10 */
    /* JADX WARN: Type inference failed for: r4v11 */
    /* JADX WARN: Type inference failed for: r4v13 */
    /* JADX WARN: Type inference failed for: r4v15 */
    /* JADX WARN: Type inference failed for: r4v16, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r4v18, types: [android.os.ParcelFileDescriptor] */
    /* JADX WARN: Type inference failed for: r4v19 */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r4v20 */
    /* JADX WARN: Type inference failed for: r4v3 */
    /* JADX WARN: Type inference failed for: r4v4 */
    /* JADX WARN: Type inference failed for: r4v5 */
    /* JADX WARN: Type inference failed for: r4v6 */
    /* JADX WARN: Type inference failed for: r4v7 */
    /* JADX WARN: Type inference failed for: r4v8 */
    /* JADX WARN: Type inference failed for: r4v9 */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v26 */
    /* JADX WARN: Type inference failed for: r6v7, types: [java.io.Closeable] */
    private static Bitmap a(Context context, BitmapFactory.Options options, String str, Uri uri) {
        Throwable th;
        ?? r6;
        FileInputStream fileInputStream;
        Closeable closeable;
        Closeable closeable2 = null;
        Bitmap decodeFileDescriptor = null;
        try {
            try {
                if (Build.VERSION.SDK_INT <= 28) {
                    fileInputStream = new FileInputStream(str);
                    context = 0;
                } else {
                    if (uri == null) {
                        HwLog.i("ImageClipper", "null uri");
                        cq.a(null);
                        cq.a(null);
                        return null;
                    }
                    context = context.getContentResolver().openFileDescriptor(uri, "r");
                    if (context != 0) {
                        try {
                            fileInputStream = new FileInputStream(context.getFileDescriptor());
                            context = context;
                        } catch (FileNotFoundException unused) {
                            fileInputStream = null;
                            HwLog.e("ImageClipper", "FileNotFoundException decodeImage");
                            closeable = context;
                            cq.a(closeable);
                            cq.a(fileInputStream);
                            return null;
                        } catch (IOException unused2) {
                            fileInputStream = null;
                            HwLog.e("ImageClipper", "DecodeBitmap IOException");
                            closeable = context;
                            cq.a(closeable);
                            cq.a(fileInputStream);
                            return null;
                        } catch (IllegalStateException unused3) {
                            fileInputStream = null;
                            HwLog.e("ImageClipper", "IllegalStateException decodeImage");
                            closeable = context;
                            cq.a(closeable);
                            cq.a(fileInputStream);
                            return null;
                        } catch (Exception unused4) {
                            fileInputStream = null;
                            HwLog.e("ImageClipper", "DecodeBitmap exception");
                            closeable = context;
                            cq.a(closeable);
                            cq.a(fileInputStream);
                            return null;
                        } catch (OutOfMemoryError unused5) {
                            fileInputStream = null;
                            HwLog.e("ImageClipper", "DecodeBitmap:OutOfMemoryError");
                            closeable = context;
                            cq.a(closeable);
                            cq.a(fileInputStream);
                            return null;
                        } catch (Throwable th2) {
                            th = th2;
                            str = null;
                            closeable2 = context;
                            r6 = str;
                            cq.a(closeable2);
                            cq.a(r6);
                            throw th;
                        }
                    } else {
                        fileInputStream = null;
                        context = context;
                    }
                }
                if (fileInputStream != null) {
                    try {
                        decodeFileDescriptor = BitmapFactory.decodeFileDescriptor(fileInputStream.getFD(), null, options);
                    } catch (FileNotFoundException unused6) {
                        HwLog.e("ImageClipper", "FileNotFoundException decodeImage");
                        closeable = context;
                        cq.a(closeable);
                        cq.a(fileInputStream);
                        return null;
                    } catch (IOException unused7) {
                        HwLog.e("ImageClipper", "DecodeBitmap IOException");
                        closeable = context;
                        cq.a(closeable);
                        cq.a(fileInputStream);
                        return null;
                    } catch (IllegalStateException unused8) {
                        HwLog.e("ImageClipper", "IllegalStateException decodeImage");
                        closeable = context;
                        cq.a(closeable);
                        cq.a(fileInputStream);
                        return null;
                    } catch (Exception unused9) {
                        HwLog.e("ImageClipper", "DecodeBitmap exception");
                        closeable = context;
                        cq.a(closeable);
                        cq.a(fileInputStream);
                        return null;
                    } catch (OutOfMemoryError unused10) {
                        HwLog.e("ImageClipper", "DecodeBitmap:OutOfMemoryError");
                        closeable = context;
                        cq.a(closeable);
                        cq.a(fileInputStream);
                        return null;
                    }
                }
                cq.a(context);
                cq.a(fileInputStream);
                return decodeFileDescriptor;
            } catch (Throwable th3) {
                th = th3;
            }
        } catch (FileNotFoundException unused11) {
            context = 0;
            fileInputStream = null;
        } catch (IOException unused12) {
            context = 0;
            fileInputStream = null;
        } catch (IllegalStateException unused13) {
            context = 0;
            fileInputStream = null;
        } catch (Exception unused14) {
            context = 0;
            fileInputStream = null;
        } catch (OutOfMemoryError unused15) {
            context = 0;
            fileInputStream = null;
        } catch (Throwable th4) {
            th = th4;
            r6 = 0;
            cq.a(closeable2);
            cq.a(r6);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v0, types: [android.content.Context] */
    /* JADX WARN: Type inference failed for: r5v10 */
    /* JADX WARN: Type inference failed for: r5v15, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r5v19 */
    /* JADX WARN: Type inference failed for: r5v20 */
    /* JADX WARN: Type inference failed for: r5v21 */
    /* JADX WARN: Type inference failed for: r5v23 */
    /* JADX WARN: Type inference failed for: r5v25 */
    /* JADX WARN: Type inference failed for: r5v27 */
    /* JADX WARN: Type inference failed for: r5v29, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r5v33, types: [android.os.ParcelFileDescriptor] */
    /* JADX WARN: Type inference failed for: r5v34 */
    /* JADX WARN: Type inference failed for: r5v35 */
    /* JADX WARN: Type inference failed for: r5v36 */
    /* JADX WARN: Type inference failed for: r5v37 */
    /* JADX WARN: Type inference failed for: r5v38 */
    /* JADX WARN: Type inference failed for: r5v39 */
    /* JADX WARN: Type inference failed for: r5v7 */
    /* JADX WARN: Type inference failed for: r5v8 */
    public static boolean b(Context context, String str, Uri uri) {
        Throwable th;
        FileInputStream fileInputStream;
        FileInputStream fileInputStream2;
        FileInputStream fileInputStream3;
        FileInputStream fileInputStream4;
        FileInputStream fileInputStream5;
        ?? r5;
        FileInputStream fileInputStream6 = null;
        try {
            try {
                if (Build.VERSION.SDK_INT <= 28) {
                    fileInputStream6 = new FileInputStream(str);
                    r5 = 0;
                } else {
                    if (uri == null) {
                        HwLog.i("ImageClipper", "null uri");
                        cq.a(null);
                        cq.a(fileInputStream6);
                        return true;
                    }
                    r5 = context.getContentResolver().openFileDescriptor(uri, "r");
                    r5 = r5;
                    if (r5 != 0) {
                        try {
                            fileInputStream6 = new FileInputStream(r5.getFileDescriptor());
                            r5 = r5;
                        } catch (FileNotFoundException unused) {
                            FileInputStream fileInputStream7 = fileInputStream6;
                            fileInputStream6 = r5;
                            fileInputStream5 = fileInputStream7;
                            HwLog.e("ImageClipper", "imageUriIsEmpty FileNotFoundException");
                            context = fileInputStream5;
                            cq.a(fileInputStream6);
                            cq.a(context);
                            return false;
                        } catch (IOException unused2) {
                            FileInputStream fileInputStream8 = fileInputStream6;
                            fileInputStream6 = r5;
                            fileInputStream4 = fileInputStream8;
                            HwLog.e("ImageClipper", "imageUriIsEmpty IOException");
                            context = fileInputStream4;
                            cq.a(fileInputStream6);
                            cq.a(context);
                            return false;
                        } catch (IllegalStateException unused3) {
                            FileInputStream fileInputStream9 = fileInputStream6;
                            fileInputStream6 = r5;
                            fileInputStream3 = fileInputStream9;
                            HwLog.e("ImageClipper", "imageUriIsEmpty IllegalStateException");
                            context = fileInputStream3;
                            cq.a(fileInputStream6);
                            cq.a(context);
                            return false;
                        } catch (Exception unused4) {
                            FileInputStream fileInputStream10 = fileInputStream6;
                            fileInputStream6 = r5;
                            fileInputStream2 = fileInputStream10;
                            HwLog.e("ImageClipper", "imageUriIsEmpty exception");
                            context = fileInputStream2;
                            cq.a(fileInputStream6);
                            cq.a(context);
                            return false;
                        } catch (Throwable th2) {
                            th = th2;
                            FileInputStream fileInputStream11 = fileInputStream6;
                            fileInputStream6 = r5;
                            fileInputStream = fileInputStream11;
                            cq.a(fileInputStream6);
                            cq.a(fileInputStream);
                            throw th;
                        }
                    }
                }
                if (fileInputStream6 != null && fileInputStream6.available() != 0) {
                    cq.a(r5);
                    cq.a(fileInputStream6);
                    return false;
                }
                HwLog.i("ImageClipper", "imageUriIsEmpty return true");
                cq.a(r5);
                cq.a(fileInputStream6);
                return true;
            } catch (Throwable th3) {
                th = th3;
                FileInputStream fileInputStream12 = fileInputStream6;
                fileInputStream6 = context;
                r5 = fileInputStream12;
            }
        } catch (FileNotFoundException unused5) {
            fileInputStream5 = null;
        } catch (IOException unused6) {
            fileInputStream4 = null;
        } catch (IllegalStateException unused7) {
            fileInputStream3 = null;
        } catch (Exception unused8) {
            fileInputStream2 = null;
        } catch (Throwable th4) {
            th = th4;
            fileInputStream = null;
            cq.a(fileInputStream6);
            cq.a(fileInputStream);
            throw th;
        }
    }

    public static Bitmap a(Bitmap bitmap, ClipOptions clipOptions) {
        int i;
        int i2;
        if (bitmap == null || clipOptions == null || bitmap.isRecycled()) {
            return null;
        }
        WatchFaceSupportInfo watchFaceSupportInfo = HwWatchFaceBtManager.getInstance(Environment.getApplicationContext()).getWatchFaceSupportInfo();
        if (watchFaceSupportInfo != null) {
            i = watchFaceSupportInfo.getWatchFaceWidth();
            i2 = watchFaceSupportInfo.getWatchFaceHeight();
        } else {
            i = 0;
            i2 = 0;
        }
        if (i != 0 && i != clipOptions.getOutputWidth() && i2 != 0 && i2 != clipOptions.getOutputHeight()) {
            HwLog.w("ImageClipper", "clip watchFaceWidth is not equals options.getOutputWidth , watchFaceWidth:" + i + ",options.getOutputWidth():" + clipOptions.getOutputWidth() + ",watchFaceHeight:" + i2 + ",options.getOutputHeight():" + clipOptions.getOutputHeight());
            return null;
        }
        if (clipOptions.getOutputWidth() > bitmap.getWidth() || clipOptions.getOutputHeight() > bitmap.getHeight()) {
            float a2 = a(clipOptions, bitmap.getWidth(), bitmap.getHeight());
            Matrix matrix = new Matrix();
            matrix.postScale(a2, a2);
            bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, false);
        }
        HwLog.i("ImageClipper", "bitmapToClip : {" + bitmap.getWidth() + ", " + bitmap.getHeight() + " }");
        if (clipOptions.getOutputWidth() == clipOptions.getOutputHeight() && !clipOptions.isClipRectangleAnyway()) {
            return c(bitmap, clipOptions);
        }
        return d(bitmap, clipOptions);
    }

    public static void b(Bitmap bitmap, ClipOptions clipOptions) {
        Bitmap a2 = a(bitmap, clipOptions);
        if (a2 == null) {
            return;
        }
        a(bitmap, clipOptions, a(clipOptions, a2));
    }

    private static Bitmap a(ClipOptions clipOptions, Bitmap bitmap) {
        if (bitmap.getWidth() == clipOptions.getOutputWidth() && bitmap.getHeight() == clipOptions.getOutputHeight()) {
            return bitmap;
        }
        if (bitmap.getWidth() < clipOptions.getOutputWidth() || bitmap.getHeight() < clipOptions.getOutputHeight()) {
            Matrix matrix = new Matrix();
            matrix.postScale(clipOptions.getOutputWidth() / bitmap.getWidth(), clipOptions.getOutputHeight() / bitmap.getHeight());
            return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        }
        HwLog.i("ImageClipper", "AreaAveragingUtil start");
        Bitmap a2 = new cp(bitmap).a(clipOptions.getOutputWidth(), clipOptions.getOutputHeight());
        HwLog.i("ImageClipper", "AreaAveragingUtil end");
        return a2;
    }

    private static void a(Bitmap bitmap, ClipOptions clipOptions, Bitmap bitmap2) {
        String str;
        String str2;
        if (Environment.getApplicationContext() == null) {
            HwLog.e("ImageClipper", "Environment.getApplicationContext() is null");
            return;
        }
        if (clipOptions.getOutputDirectory() == null) {
            HwLog.e("ImageClipper", "options.getOutputDirectory() is null");
            return;
        }
        boolean d2 = WatchFacePhotoAlbumManager.getInstance(Environment.getApplicationContext()).d();
        File file = new File(clipOptions.getOutputDirectory());
        if (!file.exists() || !file.isDirectory()) {
            HwLog.i("ImageClipper", "MkDir = " + file.mkdirs());
        }
        String format = new SimpleDateFormat("yyyyMMddHHmmssSSS", Locale.ENGLISH).format(new Date());
        if (clipOptions.getResultPaths().size() > 0) {
            str2 = clipOptions.getResultPaths().get(0);
            str = a(str2, true);
        } else {
            String str3 = file.getPath() + File.separator + (format + ".png");
            str = WatchFacePhotoAlbumManager.getInstance(Environment.getApplicationContext()).m() + File.separator + format + "_orig.png";
            str2 = str3;
        }
        a(bitmap2, str2, clipOptions);
        if (d2) {
            a(bitmap, clipOptions, str2, str);
        }
    }

    private static void a(final Bitmap bitmap, final ClipOptions clipOptions, final String str, final String str2) {
        BackgroundTaskUtils.submit(new Runnable() { // from class: com.huawei.watchface.de$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                de.a(ClipOptions.this, bitmap, str2, str);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void a(ClipOptions clipOptions, Bitmap bitmap, String str, String str2) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("scaleX", String.valueOf(clipOptions.getScaleX()));
        hashMap.put("scaleY", String.valueOf(clipOptions.getScaleY()));
        hashMap.put(ClipImageActivity.SOURCE_IMAGE_TRANS_X, String.valueOf(clipOptions.getTransX()));
        hashMap.put(ClipImageActivity.SOURCE_IMAGE_TRANS_Y, String.valueOf(clipOptions.getTransY()));
        int[] n = CommonUtils.n();
        if (n != null && n.length > 1) {
            hashMap.put(ClipImageActivity.ORG_WINDOWS_HEIGHT, String.valueOf(n[0]));
            hashMap.put(ClipImageActivity.ORG_WINDOWS_WIDTH, String.valueOf(n[1]));
        }
        if (clipOptions.getClipOrgFilePath() != null) {
            if (PversionSdUtils.getFile(clipOptions.getClipOrgFilePath()).exists()) {
                hashMap.put(ClipImageActivity.ORG_IMAGE_PATH, clipOptions.getClipOrgFilePath());
                HwLog.d("ImageClipper", "orgImage exists");
            } else {
                a(bitmap, str, 100);
                hashMap.put(ClipImageActivity.ORG_IMAGE_PATH, str);
                HwLog.d("ImageClipper", "orgImage not exists");
            }
        } else {
            HwLog.d("ImageClipper", "getClipOrgFilePath is null");
            a(bitmap, str, 100);
            hashMap.put(ClipImageActivity.ORG_IMAGE_PATH, str);
        }
        h.put(CommonUtils.g(str2), hashMap);
        HwLog.d("ImageClipper", "writeOrgImageFile success");
    }

    public static String a(String str, boolean z) {
        String g2 = CommonUtils.g(str);
        HwLog.d("ImageClipper", "outputPath fileName:" + g2);
        if (z) {
            for (Map.Entry<String, HashMap<String, String>> entry : h.entrySet()) {
                if (entry.getKey().contains(g2)) {
                    return entry.getValue().get(ClipImageActivity.ORG_IMAGE_PATH);
                }
            }
        }
        return f(g2);
    }

    private static String f(String str) {
        int lastIndexOf;
        if (TextUtils.isEmpty(str) || (lastIndexOf = str.lastIndexOf(".")) < 0) {
            return "";
        }
        return WatchFacePhotoAlbumManager.getInstance(Environment.getApplicationContext()).m() + File.separator + SafeString.substring(str, 0, lastIndexOf) + "_orig" + SafeString.substring(str, lastIndexOf);
    }

    public static HashMap<String, String> d(String str) {
        HashMap<String, String> hashMap = new HashMap<>();
        String g2 = CommonUtils.g(str);
        HwLog.d("ImageClipper", "outputPath fileName:" + g2);
        if (TextUtils.isEmpty(g2)) {
            return hashMap;
        }
        for (Map.Entry<String, HashMap<String, String>> entry : h.entrySet()) {
            if (entry.getKey().contains(g2)) {
                return entry.getValue();
            }
        }
        HwLog.d("ImageClipper", "getOrgFilePath from sp");
        String b2 = dp.b("watchfacePhotoOrgKey", "album_background", "");
        if (TextUtils.isEmpty(b2)) {
            HwLog.d("ImageClipper", "orgMapStr is null");
            return hashMap;
        }
        ConcurrentHashMap concurrentHashMap = (ConcurrentHashMap) new Gson().fromJson(b2, new TypeToken<ConcurrentHashMap<String, HashMap<String, String>>>() { // from class: com.huawei.watchface.de.1
        }.getType());
        if (concurrentHashMap == null) {
            HwLog.d("ImageClipper", "orgMap is null");
            return hashMap;
        }
        for (Map.Entry entry2 : concurrentHashMap.entrySet()) {
            if (TextUtils.equals((CharSequence) entry2.getKey(), g2)) {
                return (HashMap) entry2.getValue();
            }
        }
        return hashMap;
    }

    public static HashMap<String, String> e(String str) {
        HashMap<String, String> d2 = d(str);
        File file = PversionSdUtils.getFile(d2.get(ClipImageActivity.ORG_IMAGE_PATH));
        if (file != null && file.exists()) {
            return d2;
        }
        HwLog.i("ImageClipper", "getCheckOrgFilePath return null");
        return null;
    }

    private static void a(Bitmap bitmap, String str, ClipOptions clipOptions) {
        String filterFilePath = CommonUtils.filterFilePath(str);
        if (filterFilePath == null) {
            HwLog.w("ImageClipper", "writeImageFile path is null");
            return;
        }
        File file = new File(filterFilePath);
        HwLog.i("ImageClipper", "OutputImageFile : " + file.getName());
        FileOutputStream fileOutputStream = null;
        try {
            try {
                fileOutputStream = FileHelper.openOutputStream(file, false);
                boolean compress = bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
                fileOutputStream.flush();
                if (clipOptions.getResultPaths().size() == 0) {
                    clipOptions.getResultPaths().add(file.getPath());
                }
                HwLog.i("ImageClipper", "OutputResult = " + compress);
                if (!compress) {
                    clipOptions.getResultPaths().clear();
                }
                HwSfpPolicyManagerHelper.setDefaultCeLabel(file);
                if (fileOutputStream == null) {
                    return;
                }
            } catch (Throwable th) {
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException unused) {
                        HwLog.i("ImageClipper", "CloseOutputStreamError");
                    }
                }
                throw th;
            }
        } catch (IOException unused2) {
            HwLog.i("ImageClipper", "outputClippedFileError");
            if (fileOutputStream == null) {
                return;
            }
        }
        try {
            fileOutputStream.close();
        } catch (IOException unused3) {
            HwLog.i("ImageClipper", "CloseOutputStreamError");
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v10 */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r4v5 */
    /* JADX WARN: Type inference failed for: r4v6, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r6v0, types: [android.graphics.Bitmap] */
    private static boolean a(Bitmap bitmap, String str, int i) {
        ?? r4;
        FileOutputStream fileOutputStream;
        BufferedOutputStream bufferedOutputStream;
        File file = PversionSdUtils.getFile(str);
        if (bitmap == 0) {
            HwLog.i("ImageClipper", "writeImageFile bitmap is null");
            return false;
        }
        if (file == null) {
            return false;
        }
        StringBuilder sb = new StringBuilder("writeImageFile : ");
        String name = file.getName();
        sb.append(name);
        HwLog.i("ImageClipper", sb.toString());
        BufferedOutputStream bufferedOutputStream2 = null;
        try {
            try {
                fileOutputStream = FileHelper.openOutputStream(file, false);
                try {
                    bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
                } catch (IOException unused) {
                } catch (Exception unused2) {
                }
            } catch (Throwable th) {
                th = th;
                r4 = name;
                bufferedOutputStream2 = null;
            }
        } catch (IOException unused3) {
            fileOutputStream = null;
        } catch (Exception unused4) {
            fileOutputStream = null;
        } catch (Throwable th2) {
            th = th2;
            r4 = 0;
        }
        try {
            boolean compress = bitmap.compress(Bitmap.CompressFormat.JPEG, i, bufferedOutputStream);
            bufferedOutputStream.flush();
            HwLog.i("ImageClipper", "writeImageFile isOutput:" + compress);
            HwSfpPolicyManagerHelper.setDefaultCeLabel(file);
            cq.a(bufferedOutputStream);
            cq.a(fileOutputStream);
            return true;
        } catch (IOException unused5) {
            bufferedOutputStream2 = bufferedOutputStream;
            HwLog.i("ImageClipper", "outputClippedFileError");
            cq.a(bufferedOutputStream2);
            cq.a(fileOutputStream);
            return false;
        } catch (Exception unused6) {
            bufferedOutputStream2 = bufferedOutputStream;
            HwLog.i("ImageClipper", "outputClippedFileError");
            cq.a(bufferedOutputStream2);
            cq.a(fileOutputStream);
            return false;
        } catch (Throwable th3) {
            th = th3;
            bufferedOutputStream2 = bufferedOutputStream;
            r4 = fileOutputStream;
            cq.a(bufferedOutputStream2);
            cq.a(r4);
            throw th;
        }
    }

    private static float a(ClipOptions clipOptions, float f2, float f3) {
        if (f2 < clipOptions.getClipRect().width() || f3 < clipOptions.getClipRect().height()) {
            return Math.max(clipOptions.getOutputWidth() / f2, clipOptions.getOutputHeight() / f3);
        }
        return 1.0f;
    }

    private static Bitmap c(Bitmap bitmap, ClipOptions clipOptions) {
        Bitmap d2 = d(bitmap, clipOptions);
        if (d2 == null) {
            return d2;
        }
        Bitmap createBitmap = Bitmap.createBitmap(d2.getWidth(), d2.getHeight(), Bitmap.Config.ARGB_8888);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setFilterBitmap(true);
        paint.setColor(-12434878);
        Canvas canvas = new Canvas(createBitmap);
        canvas.drawARGB(0, 0, 0, 0);
        Rect rect = new Rect(0, 0, d2.getWidth(), d2.getHeight());
        canvas.drawCircle(d2.getWidth() / 2.0f, d2.getHeight() / 2.0f, d2.getWidth() / 2.0f, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(d2, rect, rect, paint);
        return createBitmap;
    }

    private static Bitmap d(Bitmap bitmap, ClipOptions clipOptions) {
        HwLog.i("ImageClipper", "DoClip, Source width:" + bitmap.getWidth() + " height:" + bitmap.getHeight() + "DoClip toString:" + clipOptions.toString());
        if (!bitmap.isRecycled() && clipOptions.getClipRect().width() > 0 && clipOptions.getClipRect().height() > 0) {
            return Bitmap.createBitmap(bitmap, clipOptions.getClipRect().left, clipOptions.getClipRect().top, clipOptions.getClipRect().width(), clipOptions.getClipRect().height());
        }
        return null;
    }

    public static float a(float f2) {
        return Math.round(f2 * 100.0f) / 100.0f;
    }
}
