package com.huawei.watchface.videoedit.utils;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.opengl.GLES20;
import android.opengl.GLES30;
import android.opengl.GLUtils;
import android.os.BadParcelableException;
import android.text.TextUtils;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.watchface.utils.HwLog;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* loaded from: classes7.dex */
public class Utils {
    private static final float FLOAT_TWO = 2.0f;
    public static final String MIME_TYPE_MP4 = "video/mp4";
    private static final int MS_TO_S_FACTOR = 1000;
    public static final String PREFIX_IMG = "IMG";
    public static final String PREFIX_VID = "VID";
    private static final int SECENDS_IN_HOUR = 3600;
    public static final String SUFFIX_GIF = ".gif";
    public static final String SUFFIX_MP4 = ".mp4";
    private static final String TABLET = "tablet";
    private static final String TABLET_PROP = "default";
    private static final String TAG = "Utils";
    private static final String TIME_STAMP_NAME = "_yyyyMMdd_HHmmss";
    private static final int TOTAL_SECEND = 60;

    /* loaded from: classes9.dex */
    public interface ContentResolverQueryCallback {
        void onCursorResult(Cursor cursor);
    }

    public static int clamp(int i, int i2, int i3) {
        return i > i3 ? i3 : i < i2 ? i2 : i;
    }

    public static boolean isTablet() {
        return false;
    }

    public static float getMax(float f, float f2) {
        return Math.max(f, f2);
    }

    public static float getMin(float f, float f2) {
        return Math.min(f, f2);
    }

    private Utils() {
    }

    public static String getSavePath(String str, String str2, String str3) {
        return new File(str).getParent() + File.separator + str2 + new SimpleDateFormat(TIME_STAMP_NAME).format(new Date(System.currentTimeMillis())) + str3;
    }

    public static int[] convertToArray(List<Integer> list) {
        if (list == null || list.isEmpty()) {
            return new int[0];
        }
        int size = list.size();
        int[] iArr = new int[size];
        for (int i = 0; i < size; i++) {
            iArr[i] = list.get(i).intValue();
        }
        return iArr;
    }

    public static Bitmap resizeAndCropCenter(Bitmap bitmap, int i, int i2, boolean z) {
        if (bitmap == null) {
            HwLog.e("Utils", "in resizeAndCropCenter : bitmap is null");
            return bitmap;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (width == i && height == i2) {
            return bitmap;
        }
        float f = i / width;
        float f2 = i2 / height;
        if (f <= f2) {
            f = f2;
        }
        Bitmap createBitmap = Bitmap.createBitmap(i, i2, getConfig(bitmap));
        int round = Math.round(bitmap.getWidth() * f);
        int round2 = Math.round(bitmap.getHeight() * f);
        Canvas canvas = new Canvas(createBitmap);
        canvas.translate((i - round) / 2.0f, (i2 - round2) / 2.0f);
        canvas.scale(f, f);
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, new Paint(6));
        if (z) {
            bitmap.recycle();
        }
        return createBitmap;
    }

    private static Bitmap.Config getConfig(Bitmap bitmap) {
        Bitmap.Config config = bitmap.getConfig();
        return config == null ? Bitmap.Config.RGB_565 : config;
    }

    public static void safeFinishActivity(Activity activity) {
        if (activity == null) {
            HwLog.w("Utils", "finish activity activity is null");
            return;
        }
        try {
            activity.finish();
        } catch (BadParcelableException unused) {
            HwLog.e("Utils", "finish activity parcelable exception");
        } catch (Exception e) {
            HwLog.e("Utils", "Exception" + HwLog.printException(e));
        }
    }

    public static int parseIntSafely(String str, int i) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException unused) {
            return i;
        } catch (RuntimeException e) {
            HwLog.w("Utils", "parse int failed " + str + " exception:" + HwLog.printException((Exception) e));
            return i;
        }
    }

    public static UiType getUiType(Context context) {
        if (context == null) {
            return UiType.PHONE;
        }
        if (ScreenUtils.isTahitiProduct()) {
            return UiType.TAH_FULL;
        }
        if (isPad(context)) {
            return UiType.LAND_PAD;
        }
        return UiType.PHONE;
    }

    public static boolean isPad(Context context) {
        return context != null && (context.getResources().getConfiguration().screenLayout & 15) >= 3;
    }

    public static String time2String(long j) {
        String format = new SimpleDateFormat(Constants.TIME_FORMAT_WITHOUT_MILLS).format(new Date(Long.parseLong(String.valueOf(j))));
        HwLog.i("Utils", "time2String: " + format);
        return format;
    }

    public static boolean isRtl() {
        return TextUtils.getLayoutDirectionFromLocale(Locale.getDefault()) == 1;
    }

    public static boolean release(Releasable releasable) {
        if (releasable != null) {
            try {
                releasable.release();
            } catch (Exception e) {
                HwLog.d("Utils", "release failed. " + releasable + HwLog.printException(e));
                return false;
            }
        }
        return true;
    }

    public static boolean release(MediaMetadataRetriever mediaMetadataRetriever) {
        if (mediaMetadataRetriever != null) {
            try {
                mediaMetadataRetriever.release();
            } catch (Exception e) {
                HwLog.w("Utils", "release failed. " + mediaMetadataRetriever + HwLog.printException(e));
                return false;
            }
        }
        return true;
    }

    public static String getSafeUriParameter(Uri uri, String str) {
        if (uri == null) {
            HwLog.e("Utils", "get uri data has null ! ");
            return null;
        }
        try {
            return uri.getQueryParameter(str);
        } catch (UnsupportedOperationException e) {
            HwLog.e("Utils", "get uri data support error ! " + str + ",UnsupportedOperationException" + HwLog.printException((Exception) e));
            return null;
        } catch (RuntimeException e2) {
            HwLog.e("Utils", "get uri data error ! " + str + ",RuntimeException" + HwLog.printException((Exception) e2));
            return null;
        } catch (Exception e3) {
            HwLog.e("Utils", "get uri data !" + HwLog.printException(e3));
            return null;
        }
    }

    public static void closeSilently(Closeable closeable) {
        if (closeable == null) {
            return;
        }
        try {
            closeable.close();
        } catch (IOException e) {
            HwLog.w("Utils", "close fail." + HwLog.printException((Exception) e));
        }
    }

    public static <T> boolean isEmpty(List<T> list) {
        return list == null || list.isEmpty();
    }

    public static <T> boolean isEmpty(T[] tArr) {
        return tArr == null || tArr.length == 0;
    }

    public static void createFbos(int[] iArr, int[] iArr2, int i, int i2) {
        int length = iArr.length;
        GLES20.glGenTextures(length, iArr2, 0);
        GLES20.glGenFramebuffers(length, iArr, 0);
        for (int i3 = 0; i3 < length; i3++) {
            GLES20.glBindTexture(3553, iArr2[i3]);
            GLES20.glTexParameteri(3553, 10240, 9729);
            GLES20.glTexParameteri(3553, 10241, 9729);
            GLES20.glTexParameterf(3553, 10242, 33648.0f);
            GLES20.glTexParameterf(3553, 10243, 33648.0f);
            GLES20.glTexImage2D(3553, 0, 6408, i, i2, 0, 6408, 5121, null);
            GLES20.glBindFramebuffer(36160, iArr[i3]);
            GLES20.glFramebufferTexture2D(36160, 36064, 3553, iArr2[i3], 0);
            GLES20.glBindFramebuffer(36160, 0);
        }
    }

    public static void createTextures(int[] iArr) {
        GLES20.glGenTextures(iArr.length, iArr, 0);
        for (int i : iArr) {
            GLES20.glBindTexture(3553, i);
            GLES20.glTexParameteri(3553, 10241, 9729);
            GLES20.glTexParameteri(3553, 10240, 9729);
            GLES20.glTexParameteri(3553, 10242, 33648);
            GLES20.glTexParameteri(3553, 10243, 33648);
            GLES20.glBindTexture(3553, 0);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0, types: [android.content.Context] */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v11, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r2v14 */
    /* JADX WARN: Type inference failed for: r2v15 */
    /* JADX WARN: Type inference failed for: r2v16 */
    /* JADX WARN: Type inference failed for: r2v17 */
    public static Bitmap getPictureFromAssets(Context context, String str) {
        Throwable th;
        InputStream inputStream;
        Closeable closeable = null;
        r1 = null;
        r1 = null;
        Bitmap bitmap = null;
        try {
            if (context == 0) {
                return null;
            }
            try {
                inputStream = context.getAssets().open(str);
                try {
                    bitmap = BitmapFactory.decodeStream(inputStream);
                    context = inputStream;
                } catch (IOException unused) {
                    HwLog.e("Utils", "load pic failed");
                    context = inputStream;
                    closeSilently(context);
                    return bitmap;
                } catch (Exception unused2) {
                    HwLog.e("Utils", "load pic failed Exception");
                    context = inputStream;
                    closeSilently(context);
                    return bitmap;
                } catch (OutOfMemoryError unused3) {
                    HwLog.e("Utils", "load pic :OutOfMemoryError");
                    context = inputStream;
                    closeSilently(context);
                    return bitmap;
                }
            } catch (IOException unused4) {
                inputStream = null;
            } catch (Exception unused5) {
                inputStream = null;
            } catch (OutOfMemoryError unused6) {
                inputStream = null;
            } catch (Throwable th2) {
                th = th2;
                closeSilently(closeable);
                throw th;
            }
            closeSilently(context);
            return bitmap;
        } catch (Throwable th3) {
            closeable = context;
            th = th3;
        }
    }

    public static Bitmap getPicture(String str) {
        try {
            return BitmapFactory.decodeFile(str);
        } catch (Exception unused) {
            HwLog.d("Utils", "getPicture Exception");
            return null;
        } catch (OutOfMemoryError unused2) {
            HwLog.e("Utils", "getPicture:OutOfMemoryError");
            return null;
        }
    }

    public static void vboBindData(int i, float[] fArr) {
        GLES20.glBindBuffer(34962, i);
        GLES20.glBufferData(34962, fArr.length * 4, toFloatBuffer(fArr), 35048);
        GLES20.glBindBuffer(34962, 0);
    }

    public static FloatBuffer toFloatBuffer(float[] fArr) {
        ByteBuffer allocateDirect = ByteBuffer.allocateDirect(fArr.length * 4);
        allocateDirect.order(ByteOrder.nativeOrder());
        FloatBuffer asFloatBuffer = allocateDirect.asFloatBuffer();
        asFloatBuffer.put(fArr);
        asFloatBuffer.position(0);
        return asFloatBuffer;
    }

    public static void checkGlError(String str) {
        int glGetError = GLES30.glGetError();
        if (glGetError != 0) {
            HwLog.e("Utils", "glError :" + str + " error:" + glGetError);
        }
    }

    public static boolean floatEquare(float f, float f2) {
        return ((double) Math.abs(f - f2)) < 1.0E-6d;
    }

    public static void bindVbo(int i, int i2, int[] iArr) {
        GLES20.glBindBuffer(34962, iArr[0]);
        GLES20.glEnableVertexAttribArray(i);
        GLES20.glEnableVertexAttribArray(i2);
        GLES20.glVertexAttribPointer(i, 2, 5126, false, 16, 0);
        GLES20.glVertexAttribPointer(i2, 2, 5126, false, 16, 8);
    }

    public static void textureBindData(int i, Bitmap bitmap) {
        if (bitmap == null) {
            HwLog.e("Utils", "bitmap is null");
            return;
        }
        GLES20.glBindTexture(3553, i);
        GLUtils.texImage2D(3553, 0, bitmap, 0);
        GLES20.glBindTexture(3553, 0);
    }

    public static void setDefaultClearColor() {
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
    }

    static int prevPowerOf2(int i) {
        if (i <= 0) {
            HwLog.e("Utils", "prevPowerOf2 n is illegal.");
            return 0;
        }
        return Integer.highestOneBit(i);
    }

    public static String getUpperCaseString(Context context, int i) {
        if (context == null) {
            HwLog.e("Utils", "in getUpperCaseString : context is null");
            return "";
        }
        if (i <= 0) {
            HwLog.e("Utils", "in getUpperCaseString : resId is less than 0");
            return "";
        }
        return context.getResources().getString(i).toUpperCase(Locale.ROOT);
    }

    public static String stringForTime(int i) {
        int i2 = i / 1000;
        if (i2 == 0 && i > 0) {
            i2 = 1;
        }
        int i3 = i2 % 60;
        int i4 = (i2 / 60) % 60;
        int i5 = i2 / 3600;
        if (i5 > 0) {
            return String.format(Locale.ROOT, "%d:%02d:%02d", Integer.valueOf(i5), Integer.valueOf(i4), Integer.valueOf(i3));
        }
        return String.format(Locale.ROOT, "%02d:%02d", Integer.valueOf(i4), Integer.valueOf(i3));
    }

    public static void dismissDialogSafely(DialogInterface dialogInterface, Activity activity) {
        if (dialogInterface == null) {
            HwLog.e("Utils", "in dismissDialogSafely : dialog is null");
            return;
        }
        try {
            dialogInterface.dismiss();
        } catch (IllegalArgumentException e) {
            String str = "IllegalArgumentException when dismissDialog : " + dialogInterface;
            if (activity != null) {
                str = str + ", current activity distroyed? " + activity.isDestroyed();
            }
            HwLog.w("Utils", str + "." + HwLog.printException((Exception) e));
        }
    }

    public static String getVideoSuffix(String str, int i) {
        return SUFFIX_MP4;
    }
}
