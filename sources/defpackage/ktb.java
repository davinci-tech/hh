package defpackage;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Point;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.watchface.videoedit.gles.Constant;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/* loaded from: classes5.dex */
public class ktb {
    private static int a(int i) {
        if (i > 1000) {
            return 60;
        }
        if (i > 750) {
            return 40;
        }
        return i > 500 ? 20 : 10;
    }

    public static Uri bQI_(Context context) {
        String b = b(context);
        if (TextUtils.isEmpty(b)) {
            return null;
        }
        return Uri.fromFile(new File(b));
    }

    private static String b(Context context) {
        String e = e(context);
        if (TextUtils.isEmpty(e)) {
            return "";
        }
        return e + "/" + e();
    }

    private static String e(Context context) {
        if (context == null) {
            ksy.c("PicUtil", "context is null.", true);
            return "";
        }
        try {
            String externalStorageState = Environment.getExternalStorageState();
            if ("mounted_ro".equals(externalStorageState)) {
                ksy.b("PicUtil", "read only.", true);
                return "";
            }
            if (!"mounted".equals(externalStorageState)) {
                ksy.b("PicUtil", "media mounted.", true);
                return "";
            }
            return g(context);
        } catch (RuntimeException unused) {
            ksy.c("PicUtil", "getExternalStorageState failed", true);
            return "";
        }
    }

    private static String g(Context context) {
        File externalCacheDir = context.getExternalCacheDir();
        if (externalCacheDir == null) {
            ksy.c("PicUtil", "External cache dir not existence.", true);
            return "";
        }
        try {
            String str = externalCacheDir.getCanonicalPath() + "/tmpFiles";
            File file = new File(str);
            if (file.exists() || file.mkdir()) {
                return str;
            }
            ksy.c("PicUtil", "External cache tmp dir not existence.", true);
            return "";
        } catch (IOException e) {
            ksy.b("PicUtil", "IOException " + e.getClass().getSimpleName(), true);
            return "";
        }
    }

    private static String e() {
        Date date = new Date(System.currentTimeMillis());
        return "HwID_card_" + new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(date) + ".jpg";
    }

    public static Point bQK_(Context context) {
        ksy.b("PicUtil", "getRealScreenSize start.", true);
        Display defaultDisplay = ((WindowManager) context.getApplicationContext().getSystemService(Constants.NATIVE_WINDOW_SUB_DIR)).getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        defaultDisplay.getMetrics(displayMetrics);
        int i = displayMetrics.heightPixels;
        int i2 = displayMetrics.widthPixels;
        try {
            Point point = new Point();
            Display.class.getMethod("getRealSize", Point.class).invoke(defaultDisplay, point);
            i = point.y;
            i2 = point.x;
            ksy.b("PicUtil", "heightPixels is " + i, false);
            ksy.b("PicUtil", "widthPixels is " + i2, false);
        } catch (IllegalAccessException e) {
            ksy.b("PicUtil", "IllegalAccessException " + e.getClass().getSimpleName(), true);
        } catch (IllegalArgumentException e2) {
            ksy.b("PicUtil", "IllegalArgumentException " + e2.getClass().getSimpleName(), true);
        } catch (NoSuchMethodException e3) {
            ksy.b("PicUtil", "getRealScreenSize NoSuchMethodException " + e3.getClass().getSimpleName(), true);
        } catch (InvocationTargetException e4) {
            ksy.b("PicUtil", "InvocationTargetException " + e4.getClass().getSimpleName(), true);
        } catch (Exception e5) {
            ksy.b("PicUtil", "getRealScreenSize Exception " + e5.getClass().getSimpleName(), true);
        }
        return new Point(i2, i);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v19 */
    /* JADX WARN: Type inference failed for: r10v20 */
    /* JADX WARN: Type inference failed for: r10v34 */
    /* JADX WARN: Type inference failed for: r10v35 */
    /* JADX WARN: Type inference failed for: r10v36 */
    /* JADX WARN: Type inference failed for: r10v37 */
    /* JADX WARN: Type inference failed for: r10v38 */
    /* JADX WARN: Type inference failed for: r10v4 */
    /* JADX WARN: Type inference failed for: r10v5 */
    /* JADX WARN: Type inference failed for: r10v6, types: [java.io.FileOutputStream] */
    public static boolean e(Context context, String str, int i, String str2) {
        StringBuilder sb;
        FileOutputStream fileOutputStream;
        Point bQK_ = bQK_(context);
        Bitmap a2 = a(str, bQK_.x > 0 ? bQK_.x : Constant.FBO_HEIGHT, bQK_.y > 0 ? bQK_.y : 1080);
        if (a2 == null) {
            ksy.b("PicUtil", "bitmap is null", true);
            return false;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i2 = 100;
        a2.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        int length = byteArrayOutputStream.toByteArray().length;
        ksy.b("PicUtil", "compressBitmap length = " + length, true);
        if (length > 20971520) {
            ksy.b("PicUtil", "compressBitmap too large return false", true);
            return false;
        }
        while (byteArrayOutputStream.toByteArray().length > i * 1024) {
            int a3 = a(byteArrayOutputStream.toByteArray().length / 1024);
            byteArrayOutputStream.reset();
            i2 -= a3;
            a2.compress(Bitmap.CompressFormat.JPEG, i2, byteArrayOutputStream);
        }
        ?? r10 = 0;
        FileOutputStream fileOutputStream2 = null;
        FileOutputStream fileOutputStream3 = null;
        try {
            try {
                fileOutputStream = new FileOutputStream(new File(str2));
            } catch (Throwable th) {
                th = th;
            }
        } catch (IOException e) {
            e = e;
        } catch (Exception e2) {
            e = e2;
        }
        try {
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            ksy.b("PicUtil", "compressBitmap final length = " + byteArray.length, true);
            fileOutputStream.write(byteArray);
            fileOutputStream.flush();
            fileOutputStream.close();
            try {
                fileOutputStream.close();
                r10 = byteArray;
            } catch (IOException e3) {
                e = e3;
                sb = new StringBuilder("error = ");
                sb.append(e.getClass().getSimpleName());
                ksy.c("PicUtil", sb.toString(), true);
                a2.recycle();
                return true;
            }
        } catch (IOException e4) {
            e = e4;
            fileOutputStream2 = fileOutputStream;
            ksy.c("PicUtil", "error = " + e.getClass().getSimpleName(), true);
            r10 = fileOutputStream2;
            if (fileOutputStream2 != null) {
                try {
                    fileOutputStream2.close();
                    r10 = fileOutputStream2;
                } catch (IOException e5) {
                    e = e5;
                    sb = new StringBuilder("error = ");
                    sb.append(e.getClass().getSimpleName());
                    ksy.c("PicUtil", sb.toString(), true);
                    a2.recycle();
                    return true;
                }
            }
            a2.recycle();
            return true;
        } catch (Exception e6) {
            e = e6;
            fileOutputStream3 = fileOutputStream;
            ksy.c("PicUtil", "error = " + e.getClass().getSimpleName(), true);
            r10 = fileOutputStream3;
            if (fileOutputStream3 != null) {
                try {
                    fileOutputStream3.close();
                    r10 = fileOutputStream3;
                } catch (IOException e7) {
                    e = e7;
                    sb = new StringBuilder("error = ");
                    sb.append(e.getClass().getSimpleName());
                    ksy.c("PicUtil", sb.toString(), true);
                    a2.recycle();
                    return true;
                }
            }
            a2.recycle();
            return true;
        } catch (Throwable th2) {
            th = th2;
            r10 = fileOutputStream;
            if (r10 != 0) {
                try {
                    r10.close();
                } catch (IOException e8) {
                    ksy.c("PicUtil", "error = " + e8.getClass().getSimpleName(), true);
                }
            }
            throw th;
        }
        a2.recycle();
        return true;
    }

    private static Bitmap a(String str, int i, int i2) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        try {
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(str, options);
            int i3 = options.outWidth / i;
            int i4 = options.outHeight / i2;
            if (i3 >= i4) {
                i3 = i4;
            }
            if (i3 < 1) {
                i3 = 1;
            }
            ksy.b("PicUtil", "Picture resolution compression scale：" + i3, true);
            options.inSampleSize = i3;
            options.inJustDecodeBounds = false;
            Bitmap decodeFile = BitmapFactory.decodeFile(str, options);
            int e = e(str);
            ksy.b("PicUtil", "Picture resolution degree：" + e, true);
            return e != 0 ? bQH_(e, decodeFile) : decodeFile;
        } catch (OutOfMemoryError unused) {
            ksy.c("PicUtil", "compressByResolution OutOfMemoryError", true);
            return null;
        } catch (RuntimeException unused2) {
            ksy.c("PicUtil", "compressByResolution RuntimeException", true);
            return null;
        } catch (Exception unused3) {
            ksy.c("PicUtil", "compressByResolution Exception", true);
            return null;
        }
    }

    private static int e(String str) {
        int attributeInt;
        try {
            attributeInt = new ExifInterface(str).getAttributeInt(androidx.exifinterface.media.ExifInterface.TAG_ORIENTATION, 1);
        } catch (IOException e) {
            ksy.c("PicUtil", "readImageDegree IOException" + e.getClass().getSimpleName(), true);
        }
        if (attributeInt == 3) {
            return 180;
        }
        if (attributeInt != 6) {
            return attributeInt != 8 ? 0 : 270;
        }
        return 90;
    }

    private static Bitmap bQH_(int i, Bitmap bitmap) {
        if (i == 0 || bitmap == null) {
            return bitmap;
        }
        ksy.b("PicUtil", "rotaingImageView", true);
        Matrix matrix = new Matrix();
        matrix.postRotate(i);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    public static boolean bQJ_(Context context, Uri uri, Uri uri2, boolean z) {
        Throwable th;
        FileOutputStream fileOutputStream;
        AssetFileDescriptor assetFileDescriptor;
        InputStream inputStream = null;
        try {
            assetFileDescriptor = context.getContentResolver().openAssetFileDescriptor(uri2, "rw");
            if (assetFileDescriptor == null || uri2 == null || uri == null) {
                fileOutputStream = null;
            } else {
                try {
                    fileOutputStream = assetFileDescriptor.createOutputStream();
                    try {
                        inputStream = context.getContentResolver().openInputStream(uri);
                        byte[] bArr = new byte[16384];
                        if (inputStream != null) {
                            while (true) {
                                int read = inputStream.read(bArr);
                                if (read <= 0) {
                                    break;
                                }
                                fileOutputStream.write(bArr, 0, read);
                            }
                        }
                    } catch (IOException unused) {
                        if (inputStream != null) {
                            try {
                                inputStream.close();
                            } catch (IOException e) {
                                ksy.b("PicUtil", "error e = " + e.getClass().getSimpleName(), true);
                                return false;
                            }
                        }
                        if (fileOutputStream != null) {
                            fileOutputStream.close();
                        }
                        if (z && uri != null) {
                            context.getContentResolver().delete(uri, "", new String[0]);
                        }
                        if (assetFileDescriptor != null) {
                            assetFileDescriptor.close();
                        }
                        return false;
                    } catch (Throwable th2) {
                        th = th2;
                        if (inputStream != null) {
                            try {
                                inputStream.close();
                            } catch (IOException e2) {
                                ksy.b("PicUtil", "error e = " + e2.getClass().getSimpleName(), true);
                                throw th;
                            }
                        }
                        if (fileOutputStream != null) {
                            fileOutputStream.close();
                        }
                        if (z && uri != null) {
                            context.getContentResolver().delete(uri, "", new String[0]);
                        }
                        if (assetFileDescriptor != null) {
                            assetFileDescriptor.close();
                        }
                        throw th;
                    }
                } catch (IOException unused2) {
                    fileOutputStream = null;
                } catch (Throwable th3) {
                    th = th3;
                    fileOutputStream = null;
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e3) {
                    ksy.b("PicUtil", "error e = " + e3.getClass().getSimpleName(), true);
                }
            }
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            if (z && uri != null) {
                context.getContentResolver().delete(uri, "", new String[0]);
            }
            if (assetFileDescriptor != null) {
                assetFileDescriptor.close();
            }
            return true;
        } catch (IOException unused3) {
            fileOutputStream = null;
            assetFileDescriptor = null;
        } catch (Throwable th4) {
            th = th4;
            fileOutputStream = null;
            assetFileDescriptor = null;
        }
    }

    public static void a(Context context) {
        ksy.b("PicUtil", "deleteCachedFiles", true);
        try {
            String e = e(context);
            if (TextUtils.isEmpty(e)) {
                return;
            }
            File file = new File(e);
            if (file.exists() && file.isDirectory()) {
                d(file);
            }
        } catch (RuntimeException unused) {
            ksy.c("PicUtil", "deleteCachedFiles RuntimeException", true);
        } catch (Exception unused2) {
            ksy.c("PicUtil", "deleteCachedFiles Exception", true);
        }
    }

    private static void d(File file) {
        if (file == null) {
            return;
        }
        File[] listFiles = file.listFiles();
        if (listFiles == null) {
            ksy.b("PicUtil", "deleteDirInnerPicFile files == null", true);
            return;
        }
        for (File file2 : listFiles) {
            if (file2.getName().startsWith("HwID_card_") && !file2.delete()) {
                ksy.b("PicUtil", "delete file fail", true);
            }
        }
    }
}
