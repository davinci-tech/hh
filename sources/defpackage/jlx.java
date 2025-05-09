package defpackage;

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
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.watchface.mvp.model.latona.provider.WatchFaceProvider;
import com.huawei.watchface.videoedit.utils.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.egl.EGLSurface;
import org.apache.commons.io.FileUtils;

/* loaded from: classes5.dex */
public class jlx {
    private static int d;
    private static ArrayList<String> e;
    private static final Object b = new Object();

    /* renamed from: a, reason: collision with root package name */
    private static String[] f13949a = {".png", ".jpg", ".jpeg", ".bmp", Utils.SUFFIX_GIF, ".psd", WatchFaceProvider.SVG_SUFFIX, ".tif", ".tiff", ".webp", ".pcx"};

    public static List<String> c() {
        ArrayList<String> arrayList;
        synchronized (b) {
            if (e == null) {
                e = new ArrayList<>(16);
                for (String str : f13949a) {
                    e.add(str);
                }
                LogUtil.a("ImageClipper", "SuffixContainers : ", e.toString());
            }
            arrayList = e;
        }
        return arrayList;
    }

    public static Bitmap bIv_(String str, Uri uri) {
        if (str == null) {
            LogUtil.h("ImageClipper", "preparePathNULL");
            return null;
        }
        try {
            String substring = str.substring(str.lastIndexOf("."));
            LogUtil.a("ImageClipper", "fileSuffix : ", substring);
            if (!c().contains(substring.toLowerCase(Locale.ENGLISH))) {
                LogUtil.a("ImageClipper", "SourceFile is not supported");
                return null;
            }
            File file = new File(str);
            if (!file.exists() || file.isDirectory()) {
                LogUtil.a("ImageClipper", "PrepareFileNULL");
                return null;
            }
            LogUtil.a("ImageClipper", "PrepareUri is NULL");
            if (uri == null) {
                LogUtil.a("ImageClipper", "CreateUriFromFile");
                uri = bIu_(BaseApplication.getContext(), str);
                Object[] objArr = new Object[2];
                objArr[0] = "CreatedUri : ";
                objArr[1] = Boolean.valueOf(uri != null);
                LogUtil.a("ImageClipper", objArr);
            }
            a();
            Bitmap bIs_ = bIs_(str, uri, bIl_(str, uri));
            int bIw_ = bIw_(str, uri);
            return (bIw_ <= 0 || bIs_ == null) ? bIs_ : bIx_(bIw_, bIs_);
        } catch (StringIndexOutOfBoundsException unused) {
            LogUtil.b("ImageClipper", "StringIndexOutOfBoundsException");
            return null;
        }
    }

    private static int bIw_(String str, Uri uri) {
        int i;
        LogUtil.a("ImageClipper", "readPictureAngle enter");
        ExifInterface bIt_ = bIt_(str, uri);
        int i2 = 0;
        if (bIt_ != null) {
            int attributeInt = bIt_.getAttributeInt(androidx.exifinterface.media.ExifInterface.TAG_ORIENTATION, 1);
            if (attributeInt == 3) {
                i = 180;
            } else if (attributeInt == 6) {
                i = 90;
            } else if (attributeInt != 8) {
                LogUtil.h("ImageClipper", "orientation:", Integer.valueOf(attributeInt));
                LogUtil.a("ImageClipper", "readPictureAngle angle:", Integer.valueOf(i2));
            } else {
                i = 270;
            }
            i2 = i;
            LogUtil.a("ImageClipper", "readPictureAngle angle:", Integer.valueOf(i2));
        }
        return i2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00b4 A[Catch: IOException -> 0x00b8, TRY_LEAVE, TryCatch #3 {IOException -> 0x00b8, blocks: (B:37:0x00af, B:33:0x00b4), top: B:36:0x00af }] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00af A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0096 A[Catch: IOException -> 0x009a, TRY_LEAVE, TryCatch #1 {IOException -> 0x009a, blocks: (B:46:0x0091, B:42:0x0096), top: B:45:0x0091 }] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0091 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v16 */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r4v3, types: [java.io.FileInputStream] */
    /* JADX WARN: Type inference failed for: r8v11, types: [android.os.ParcelFileDescriptor] */
    /* JADX WARN: Type inference failed for: r8v2 */
    /* JADX WARN: Type inference failed for: r8v21, types: [android.os.ParcelFileDescriptor] */
    /* JADX WARN: Type inference failed for: r8v3 */
    /* JADX WARN: Type inference failed for: r8v4 */
    /* JADX WARN: Type inference failed for: r8v6 */
    /* JADX WARN: Type inference failed for: r8v7, types: [android.os.ParcelFileDescriptor] */
    /* JADX WARN: Type inference failed for: r8v9, types: [android.os.ParcelFileDescriptor] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static android.media.ExifInterface bIt_(java.lang.String r8, android.net.Uri r9) {
        /*
            Method dump skipped, instructions count: 227
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.jlx.bIt_(java.lang.String, android.net.Uri):android.media.ExifInterface");
    }

    private static Bitmap bIx_(int i, Bitmap bitmap) {
        Bitmap bitmap2;
        Matrix matrix = new Matrix();
        matrix.postRotate(i);
        try {
            bitmap2 = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        } catch (OutOfMemoryError unused) {
            LogUtil.b("ImageClipper", "rotateImageView OutOfMemoryError");
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

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00a0  */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static android.net.Uri bIu_(android.content.Context r13, java.lang.String r14) {
        /*
            java.lang.String r0 = "Call getMediaUriFromPath"
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            java.lang.String r1 = "ImageClipper"
            com.huawei.hwlogsmodel.LogUtil.a(r1, r0)
            r0 = 2
            r2 = 1
            r3 = 0
            r4 = 0
            android.net.Uri r11 = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI     // Catch: java.lang.Throwable -> L74 java.lang.IllegalArgumentException -> L76 android.database.SQLException -> L8b
            java.lang.Object[] r5 = new java.lang.Object[r0]     // Catch: java.lang.Throwable -> L74 java.lang.IllegalArgumentException -> L76 android.database.SQLException -> L8b
            java.lang.String r6 = "MediaStoreUri:"
            r5[r3] = r6     // Catch: java.lang.Throwable -> L74 java.lang.IllegalArgumentException -> L76 android.database.SQLException -> L8b
            if (r11 == 0) goto L1b
            r6 = r2
            goto L1c
        L1b:
            r6 = r3
        L1c:
            java.lang.Boolean r6 = java.lang.Boolean.valueOf(r6)     // Catch: java.lang.Throwable -> L74 java.lang.IllegalArgumentException -> L76 android.database.SQLException -> L8b
            r5[r2] = r6     // Catch: java.lang.Throwable -> L74 java.lang.IllegalArgumentException -> L76 android.database.SQLException -> L8b
            com.huawei.hwlogsmodel.LogUtil.a(r1, r5)     // Catch: java.lang.Throwable -> L74 java.lang.IllegalArgumentException -> L76 android.database.SQLException -> L8b
            if (r11 == 0) goto L6c
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L74 java.lang.IllegalArgumentException -> L76 android.database.SQLException -> L8b
            java.lang.String r6 = "_data='"
            r5.<init>(r6)     // Catch: java.lang.Throwable -> L74 java.lang.IllegalArgumentException -> L76 android.database.SQLException -> L8b
            r5.append(r14)     // Catch: java.lang.Throwable -> L74 java.lang.IllegalArgumentException -> L76 android.database.SQLException -> L8b
            java.lang.String r14 = "'"
            r5.append(r14)     // Catch: java.lang.Throwable -> L74 java.lang.IllegalArgumentException -> L76 android.database.SQLException -> L8b
            java.lang.String r8 = r5.toString()     // Catch: java.lang.Throwable -> L74 java.lang.IllegalArgumentException -> L76 android.database.SQLException -> L8b
            android.content.ContentResolver r5 = r13.getContentResolver()     // Catch: java.lang.Throwable -> L74 java.lang.IllegalArgumentException -> L76 android.database.SQLException -> L8b
            r7 = 0
            r9 = 0
            r10 = 0
            r6 = r11
            android.database.Cursor r13 = r5.query(r6, r7, r8, r9, r10)     // Catch: java.lang.Throwable -> L74 java.lang.IllegalArgumentException -> L76 android.database.SQLException -> L8b
            if (r13 == 0) goto L68
            boolean r14 = r13.moveToFirst()     // Catch: java.lang.IllegalArgumentException -> L66 android.database.SQLException -> L8c java.lang.Throwable -> L9b
            if (r14 == 0) goto L68
            java.lang.Object[] r14 = new java.lang.Object[r2]     // Catch: java.lang.IllegalArgumentException -> L66 android.database.SQLException -> L8c java.lang.Throwable -> L9b
            java.lang.String r5 = "Cursor.MoveToFirst"
            r14[r3] = r5     // Catch: java.lang.IllegalArgumentException -> L66 android.database.SQLException -> L8c java.lang.Throwable -> L9b
            com.huawei.hwlogsmodel.LogUtil.a(r1, r14)     // Catch: java.lang.IllegalArgumentException -> L66 android.database.SQLException -> L8c java.lang.Throwable -> L9b
            java.lang.String r14 = "_id"
            int r14 = r13.getColumnIndex(r14)     // Catch: java.lang.IllegalArgumentException -> L66 android.database.SQLException -> L8c java.lang.Throwable -> L9b
            long r5 = r13.getLong(r14)     // Catch: java.lang.IllegalArgumentException -> L66 android.database.SQLException -> L8c java.lang.Throwable -> L9b
            android.net.Uri r4 = android.content.ContentUris.withAppendedId(r11, r5)     // Catch: java.lang.IllegalArgumentException -> L66 android.database.SQLException -> L8c java.lang.Throwable -> L9b
            goto L68
        L66:
            r14 = move-exception
            goto L79
        L68:
            r12 = r4
            r4 = r13
            r13 = r12
            goto L6d
        L6c:
            r13 = r4
        L6d:
            if (r4 == 0) goto L72
            r4.close()
        L72:
            r4 = r13
            goto L9a
        L74:
            r13 = move-exception
            goto L9e
        L76:
            r13 = move-exception
            r14 = r13
            r13 = r4
        L79:
            java.lang.Object[] r0 = new java.lang.Object[r0]     // Catch: java.lang.Throwable -> L9b
            java.lang.String r5 = "cursor query IllegalArgumentException. msg: "
            r0[r3] = r5     // Catch: java.lang.Throwable -> L9b
            java.lang.String r14 = com.huawei.haf.common.exception.ExceptionUtils.d(r14)     // Catch: java.lang.Throwable -> L9b
            r0[r2] = r14     // Catch: java.lang.Throwable -> L9b
            com.huawei.hwlogsmodel.LogUtil.b(r1, r0)     // Catch: java.lang.Throwable -> L9b
            if (r13 == 0) goto L9a
            goto L97
        L8b:
            r13 = r4
        L8c:
            java.lang.Object[] r14 = new java.lang.Object[r2]     // Catch: java.lang.Throwable -> L9b
            java.lang.String r0 = "cursor query SQLException"
            r14[r3] = r0     // Catch: java.lang.Throwable -> L9b
            com.huawei.hwlogsmodel.LogUtil.b(r1, r14)     // Catch: java.lang.Throwable -> L9b
            if (r13 == 0) goto L9a
        L97:
            r13.close()
        L9a:
            return r4
        L9b:
            r14 = move-exception
            r4 = r13
            r13 = r14
        L9e:
            if (r4 == 0) goto La3
            r4.close()
        La3:
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.jlx.bIu_(android.content.Context, java.lang.String):android.net.Uri");
    }

    private static void a() {
        synchronized (b) {
            int i = d;
            if (i == 0 || i == 1080) {
                e();
            }
        }
    }

    private static void e() {
        if (!(EGLContext.getEGL() instanceof EGL10)) {
            LogUtil.h("ImageClipper", "getGlesTextureLimitEqualAboveLollipop instanceof err.");
            return;
        }
        EGL10 egl10 = (EGL10) EGLContext.getEGL();
        EGLDisplay eglGetDisplay = egl10.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);
        egl10.eglInitialize(eglGetDisplay, new int[2]);
        EGLConfig[] eGLConfigArr = new EGLConfig[1];
        int[] iArr = new int[1];
        egl10.eglChooseConfig(eglGetDisplay, new int[]{12351, 12430, 12329, 0, 12339, 1, 12344}, eGLConfigArr, 1, iArr);
        if (iArr[0] == 0) {
            LogUtil.a("ImageClipper", "wrong numConfig 0");
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
        synchronized (b) {
            int i = iArr2[0];
            d = i;
            LogUtil.a("ImageClipper", "MaxSizeOverLollipop: ", Integer.valueOf(i));
            if (d == 0) {
                d = 1080;
            }
        }
    }

    private static int bIl_(String str, Uri uri) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        int i = 1;
        options.inJustDecodeBounds = true;
        if (Build.VERSION.SDK_INT <= 28) {
            BitmapFactory.decodeFile(str, options);
        } else {
            if (uri == null) {
                LogUtil.h("ImageClipper", "calculateInSampleSize uri null");
                return 1;
            }
            ParcelFileDescriptor parcelFileDescriptor = null;
            try {
                try {
                    try {
                        parcelFileDescriptor = BaseApplication.getContext().getContentResolver().openFileDescriptor(uri, "r");
                        if (parcelFileDescriptor != null) {
                            BitmapFactory.decodeFileDescriptor(parcelFileDescriptor.getFileDescriptor(), new Rect(), options);
                        }
                        if (parcelFileDescriptor != null) {
                            try {
                                parcelFileDescriptor.close();
                            } catch (IOException unused) {
                                LogUtil.b("ImageClipper", "parcelFileDescriptor close IOException");
                            }
                        }
                    } catch (Throwable th) {
                        if (parcelFileDescriptor != null) {
                            try {
                                parcelFileDescriptor.close();
                            } catch (IOException unused2) {
                                LogUtil.b("ImageClipper", "parcelFileDescriptor close IOException");
                            }
                        }
                        throw th;
                    }
                } catch (IllegalStateException unused3) {
                    LogUtil.b("ImageClipper", "IllegalStateException calculateInSampleSize");
                    if (parcelFileDescriptor != null) {
                        try {
                            parcelFileDescriptor.close();
                        } catch (IOException unused4) {
                            LogUtil.b("ImageClipper", "parcelFileDescriptor close IOException");
                        }
                    }
                }
            } catch (IOException unused5) {
                LogUtil.b("ImageClipper", "calculateInSampleSize IOException");
                if (parcelFileDescriptor != null) {
                    try {
                        parcelFileDescriptor.close();
                    } catch (IOException unused6) {
                        LogUtil.b("ImageClipper", "parcelFileDescriptor close IOException");
                    }
                }
            }
        }
        LogUtil.a("ImageClipper", "Finally decode bounds : {", Integer.valueOf(options.outWidth), ", ", Integer.valueOf(options.outHeight), " }");
        int i2 = options.outWidth;
        int i3 = options.outHeight;
        synchronized (b) {
            while (true) {
                int i4 = d;
                if (i2 <= i4 && i3 <= i4 && i2 * i3 * 4 <= 67108864) {
                }
                i *= 2;
                i2 /= 2;
                i3 /= 2;
            }
        }
        LogUtil.a("ImageClipper", "calculated inSampleSize:", Integer.valueOf(i));
        return i;
    }

    private static Bitmap bIs_(String str, Uri uri, int i) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = false;
        options.inSampleSize = i;
        options.inPreferredColorSpace = ColorSpace.get(ColorSpace.Named.SRGB);
        options.inPurgeable = true;
        options.inDither = false;
        options.inInputShareable = true;
        Bitmap bIr_ = bIr_(options, str, uri);
        if (bIr_ != null) {
            LogUtil.a("ImageClipper", "decodeBitmap: {", Integer.valueOf(bIr_.getWidth()), ", ", Integer.valueOf(bIr_.getHeight()), "}");
        }
        return bIr_;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:28:0x008b A[Catch: IOException -> 0x008f, TRY_LEAVE, TryCatch #4 {IOException -> 0x008f, blocks: (B:35:0x0086, B:28:0x008b), top: B:34:0x0086 }] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0086 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00a9 A[Catch: IOException -> 0x00ad, TRY_LEAVE, TryCatch #7 {IOException -> 0x00ad, blocks: (B:45:0x00a4, B:41:0x00a9), top: B:44:0x00a4 }] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00a4 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00bd A[Catch: IOException -> 0x00c1, TRY_LEAVE, TryCatch #13 {IOException -> 0x00c1, blocks: (B:56:0x00b8, B:51:0x00bd), top: B:55:0x00b8 }] */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00b8 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r9v0, types: [android.net.Uri] */
    /* JADX WARN: Type inference failed for: r9v1 */
    /* JADX WARN: Type inference failed for: r9v10 */
    /* JADX WARN: Type inference failed for: r9v14 */
    /* JADX WARN: Type inference failed for: r9v2 */
    /* JADX WARN: Type inference failed for: r9v22 */
    /* JADX WARN: Type inference failed for: r9v24 */
    /* JADX WARN: Type inference failed for: r9v25 */
    /* JADX WARN: Type inference failed for: r9v3 */
    /* JADX WARN: Type inference failed for: r9v4 */
    /* JADX WARN: Type inference failed for: r9v5, types: [java.io.FileInputStream] */
    /* JADX WARN: Type inference failed for: r9v6, types: [java.io.FileInputStream] */
    /* JADX WARN: Type inference failed for: r9v7 */
    /* JADX WARN: Type inference failed for: r9v8, types: [java.io.FileInputStream] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static android.graphics.Bitmap bIr_(android.graphics.BitmapFactory.Options r7, java.lang.String r8, android.net.Uri r9) {
        /*
            Method dump skipped, instructions count: 201
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.jlx.bIr_(android.graphics.BitmapFactory$Options, java.lang.String, android.net.Uri):android.graphics.Bitmap");
    }

    public static Bitmap bIn_(Bitmap bitmap, jlz jlzVar) {
        if (bitmap == null || jlzVar == null || bitmap.isRecycled()) {
            return null;
        }
        if (jlzVar.e() > bitmap.getWidth() || jlzVar.a() > bitmap.getHeight()) {
            float b2 = b(jlzVar, bitmap.getWidth(), bitmap.getHeight());
            Matrix matrix = new Matrix();
            matrix.postScale(b2, b2);
            bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, false);
        }
        LogUtil.a("ImageClipper", "bitmapToClip : {", Integer.valueOf(bitmap.getWidth()), ", ", Integer.valueOf(bitmap.getHeight()), " }");
        if (jlzVar.e() == jlzVar.a() && !jlzVar.j()) {
            return bIp_(bitmap, jlzVar);
        }
        return bIq_(bitmap, jlzVar);
    }

    public static void bIo_(Bitmap bitmap, jlz jlzVar) {
        String str;
        Bitmap bIn_ = bIn_(bitmap, jlzVar);
        if (bIn_ == null) {
            return;
        }
        if (bIn_.getWidth() != jlzVar.e() || bIn_.getHeight() != jlzVar.a()) {
            if (bIn_.getWidth() < jlzVar.e() || bIn_.getHeight() < jlzVar.a()) {
                Matrix matrix = new Matrix();
                matrix.postScale(jlzVar.e() / bIn_.getWidth(), jlzVar.a() / bIn_.getHeight());
                bIn_ = Bitmap.createBitmap(bIn_, 0, 0, bIn_.getWidth(), bIn_.getHeight(), matrix, true);
            } else {
                LogUtil.a("ImageClipper", "AreaAveragingUtil start");
                bIn_ = new jly(bIn_).bIi_(jlzVar.e(), jlzVar.a());
                LogUtil.a("ImageClipper", "AreaAveragingUtil end");
            }
        }
        File file = new File(jlzVar.d());
        if (!file.exists() || !file.isDirectory()) {
            ReleaseLogUtil.e("R_ImageClipper", "MkDir:", Boolean.valueOf(file.mkdirs()));
        }
        if (jlzVar.c().size() > 0) {
            str = jlzVar.c().get(0);
        } else {
            str = file.getPath() + File.separator + (new SimpleDateFormat("yyyyMMddHHmmssSSS", Locale.ENGLISH).format(new Date()) + ".png");
        }
        bIy_(bIn_, str, jlzVar);
    }

    private static void bIy_(Bitmap bitmap, String str, jlz jlzVar) {
        File file = new File(str);
        ReleaseLogUtil.e("R_ImageClipper", "OutputImageFile:", file.getName());
        FileOutputStream fileOutputStream = null;
        try {
            try {
                fileOutputStream = FileUtils.openOutputStream(file);
                if (fileOutputStream != null && bitmap != null) {
                    boolean compress = bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
                    fileOutputStream.flush();
                    if (jlzVar.c().size() == 0) {
                        jlzVar.c().add(file.getPath());
                    }
                    ReleaseLogUtil.e("R_ImageClipper", "OutputResult:", Boolean.valueOf(compress));
                    if (!compress) {
                        jlzVar.c().clear();
                    }
                }
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException unused) {
                        LogUtil.b("ImageClipper", "CloseOutputStreamError");
                    }
                }
            } catch (Throwable th) {
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException unused2) {
                        LogUtil.b("ImageClipper", "CloseOutputStreamError");
                    }
                }
                throw th;
            }
        } catch (IOException unused3) {
            ReleaseLogUtil.c("R_ImageClipper", "outputClippedFileError");
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException unused4) {
                    LogUtil.b("ImageClipper", "CloseOutputStreamError");
                }
            }
        }
    }

    public static void bIm_(jlz jlzVar, Bitmap bitmap) {
        int i;
        int i2;
        int i3;
        int i4;
        if (jlzVar == null || bitmap == null) {
            return;
        }
        if (jlzVar.bIj_().width() <= 0 || jlzVar.bIj_().height() <= 0) {
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            LogUtil.a("ImageClipper", "bitmap size : {", Integer.valueOf(width), ", ", Integer.valueOf(height), "}");
            float e2 = jlzVar.e() / jlzVar.a();
            if (width > height) {
                int i5 = (int) (height * e2);
                i4 = (width - i5) / 2;
                i3 = i5;
                i = height;
                i2 = 0;
            } else {
                i = (int) (width / e2);
                i2 = (height - i) / 2;
                i3 = width;
                i4 = 0;
            }
            Rect rect = new Rect(Math.max(i4, 0), Math.max(i2, 0), i4 + i3, i2 + i);
            jlzVar.bIk_(rect);
            LogUtil.a("ImageClipper", "FixedClipRect:", rect.toString());
        }
    }

    private static float b(jlz jlzVar, float f, float f2) {
        if (f < jlzVar.bIj_().width() || f2 < jlzVar.bIj_().height()) {
            return Math.max(jlzVar.e() / f, jlzVar.a() / f2);
        }
        return 1.0f;
    }

    private static Bitmap bIp_(Bitmap bitmap, jlz jlzVar) {
        Bitmap bIq_ = bIq_(bitmap, jlzVar);
        if (bIq_ == null) {
            return bIq_;
        }
        Bitmap createBitmap = Bitmap.createBitmap(bIq_.getWidth(), bIq_.getHeight(), Bitmap.Config.ARGB_8888);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setFilterBitmap(true);
        paint.setColor(-12434878);
        Canvas canvas = new Canvas(createBitmap);
        canvas.drawARGB(0, 0, 0, 0);
        Rect rect = new Rect(0, 0, bIq_.getWidth(), bIq_.getHeight());
        canvas.drawCircle(bIq_.getWidth() / 2.0f, bIq_.getHeight() / 2.0f, bIq_.getWidth() / 2.0f, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bIq_, rect, rect, paint);
        return createBitmap;
    }

    private static Bitmap bIq_(Bitmap bitmap, jlz jlzVar) {
        LogUtil.a("ImageClipper", "DoClip, Source width:", Integer.valueOf(bitmap.getWidth()), " height:", Integer.valueOf(bitmap.getHeight()), "DoClip toString:", jlzVar.toString());
        if (bitmap.isRecycled()) {
            return null;
        }
        try {
            if (jlzVar.bIj_().width() > 0 && jlzVar.bIj_().height() > 0) {
                int min = Math.min(bitmap.getWidth(), jlzVar.bIj_().width());
                int min2 = Math.min(bitmap.getHeight(), jlzVar.bIj_().height());
                LogUtil.a("ImageClipper", "clipRectangle, optionWidth :", Integer.valueOf(min), " , optionHeight :", Integer.valueOf(min2));
                return Bitmap.createBitmap(bitmap, jlzVar.bIj_().left, jlzVar.bIj_().top, min, min2);
            }
        } catch (IllegalArgumentException unused) {
            LogUtil.b("ImageClipper", "IllegalArgumentException error");
        }
        return null;
    }
}
