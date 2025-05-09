package com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.text.TextUtils;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.common.os.FileUtils;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.Utils;
import com.huawei.watchface.utils.ColorPickerUtil;
import defpackage.jls;
import defpackage.nrf;
import health.compact.a.CommonUtil;
import health.compact.a.IoUtils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/* loaded from: classes9.dex */
public class DiyWatchFaceBitmapUtils {
    private static final int BITMAP_COMPRESS_QUALITY = 100;
    private static final float RADIUS_WEAR_RECT = 24.0f;
    private static final String TAG = "DiyWatchFaceBitmapUtils";
    private static final String TAG_RELEASE = "DEVMGR_DiyWatchFaceBitmapUtils";

    public static int colorPickerBitmap(Bitmap bitmap, Rect rect) {
        if (bitmap == null || rect == null) {
            ReleaseLogUtil.d(TAG_RELEASE, "colorPickerBitmap bitmap or rect is null");
            return -1;
        }
        LogUtil.a(TAG, "colorPickerBitmap Rect left:", Integer.valueOf(rect.left), "Rect top:", Integer.valueOf(rect.top), "Rect right:", Integer.valueOf(rect.right), "Rect bottom:", Integer.valueOf(rect.bottom));
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int rowBytes = bitmap.getRowBytes();
        int[] iArr = new int[width * height];
        bitmap.getPixels(iArr, 0, width, 0, 0, width, height);
        byte[] colorInt2bgra = colorInt2bgra(iArr);
        if (colorInt2bgra.length == 0) {
            ReleaseLogUtil.d(TAG_RELEASE, "colorPickerBitmap data length is 0");
            return -1;
        }
        int processNewBitmap = ColorPickerUtil.processNewBitmap(colorInt2bgra, width, height, rowBytes * height, rect.left, rect.top, rect.width(), rect.height());
        ReleaseLogUtil.e(TAG_RELEASE, "colorPickerBitmap color :", Integer.valueOf(processNewBitmap));
        ReleaseLogUtil.e(TAG_RELEASE, "colorPickerBitmap hex :", Utils.int2Hex(processNewBitmap));
        return processNewBitmap;
    }

    public static int obtainWidgetColor(Bitmap bitmap, Rect rect) {
        if (bitmap == null || rect == null) {
            ReleaseLogUtil.d(TAG_RELEASE, "obtainWidgetColor bitmap or rect is null!");
            return -1;
        }
        int colorPickerBitmap = colorPickerBitmap(bitmap, rect);
        ReleaseLogUtil.e(TAG_RELEASE, "obtainWidgetColor pickedColor", Integer.valueOf(colorPickerBitmap));
        return colorPickerBitmap;
    }

    public static byte[] colorInt2bgra(int[] iArr) {
        if (iArr == null) {
            ReleaseLogUtil.d(TAG_RELEASE, "colorInt2bgra colorInt is null");
            return new byte[0];
        }
        byte[] bArr = new byte[iArr.length * 4];
        for (int i = 0; i < iArr.length; i++) {
            int i2 = i * 4;
            int i3 = iArr[i];
            bArr[i2] = (byte) (i3 & 255);
            bArr[i2 + 1] = (byte) ((i3 >> 8) & 255);
            bArr[i2 + 2] = (byte) ((i3 >> 16) & 255);
            bArr[i2 + 3] = (byte) ((i3 >> 24) & 255);
        }
        return bArr;
    }

    public static void createBinFile(String str, String str2, int i) {
        if (TextUtils.isEmpty(str)) {
            ReleaseLogUtil.d(TAG_RELEASE, "createSizeBinFile pngPath is empty");
            return;
        }
        Bitmap cHA_ = nrf.cHA_(str);
        if (cHA_ == null) {
            ReleaseLogUtil.d(TAG_RELEASE, "createSizeBinFile decode source bitmap file null");
            return;
        }
        LogUtil.a(TAG, "createBinFile bmWidth:", Integer.valueOf(cHA_.getWidth()), ", bmHeight:", Integer.valueOf(cHA_.getHeight()));
        jls.b(str, str2, i);
    }

    public static void createSizeBinFile(String str, String str2, int i, int i2, int i3) {
        if (TextUtils.isEmpty(str)) {
            ReleaseLogUtil.d(TAG_RELEASE, "createSizeBinFile pngPath is empty");
            return;
        }
        Bitmap cHA_ = nrf.cHA_(str);
        if (cHA_ == null) {
            ReleaseLogUtil.d(TAG_RELEASE, "createSizeBinFile decode source bitmap file null");
            return;
        }
        if (i2 == 0 || i3 == 0) {
            ReleaseLogUtil.d(TAG_RELEASE, "createSizeBinFile dial width or height is 0");
            return;
        }
        int width = cHA_.getWidth();
        int height = cHA_.getHeight();
        LogUtil.a(TAG, "createSizeBinFile bmWidth:", Integer.valueOf(width), ", bmHeight:", Integer.valueOf(height), ", width:", Integer.valueOf(i2), ",height:", Integer.valueOf(i3));
        if (i2 == width && i3 == height) {
            ReleaseLogUtil.e(TAG_RELEASE, "createSizeBinFile finish");
            jls.b(str, str2, i);
        } else {
            ReleaseLogUtil.e(TAG_RELEASE, "createSizeBinFile bitmap width or height is not dial width or height");
        }
    }

    public static Bitmap tintBitmap(Bitmap bitmap, int i) {
        if (bitmap == null) {
            ReleaseLogUtil.d(TAG_RELEASE, "tintBitmap bitmap is null");
            return bitmap;
        }
        Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        paint.setColorFilter(new PorterDuffColorFilter(i, PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, paint);
        return createBitmap;
    }

    public static Bitmap tintColorBitmap(Bitmap bitmap, int i) {
        if (bitmap == null || bitmap.isRecycled()) {
            ReleaseLogUtil.d(TAG_RELEASE, "tintColorBitmap bitmap is null or isRecycled");
            return null;
        }
        if (i == 0) {
            ReleaseLogUtil.d(TAG_RELEASE, "tintColorBitmap tintColor is 0");
            return bitmap;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int[] iArr = new int[width * height];
        bitmap.getPixels(iArr, 0, width, 0, 0, width, height);
        for (int i2 = 0; i2 < height; i2++) {
            for (int i3 = 0; i3 < width; i3++) {
                int i4 = (width * i2) + i3;
                int i5 = iArr[i4];
                int i6 = (((((16711680 & i5) >> 16) * 76) + (((65280 & i5) >> 8) * 150)) + ((i5 & 255) * 30)) >> 8;
                iArr[i4] = (i5 & (-16777216)) | (i6 << 16) | (i6 << 8) | i6;
            }
        }
        Bitmap createBitmap = Bitmap.createBitmap(width, height, bitmap.getConfig());
        createBitmap.setPixels(iArr, 0, width, 0, 0, width, height);
        new Canvas(createBitmap).drawColor(i, PorterDuff.Mode.MULTIPLY);
        return createBitmap;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r11v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r11v10 */
    /* JADX WARN: Type inference failed for: r11v11 */
    /* JADX WARN: Type inference failed for: r11v3 */
    /* JADX WARN: Type inference failed for: r11v7, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1 */
    /* JADX WARN: Type inference failed for: r4v13 */
    /* JADX WARN: Type inference failed for: r4v6 */
    /* JADX WARN: Type inference failed for: r4v7 */
    /* JADX WARN: Type inference failed for: r8v0, types: [android.graphics.Bitmap] */
    public static void saveBitmapToFile(Bitmap bitmap, Bitmap.CompressFormat compressFormat, String str, String str2) {
        FileOutputStream fileOutputStream;
        FileOutputStream fileOutputStream2;
        String c;
        BufferedOutputStream bufferedOutputStream;
        if (bitmap == 0) {
            ReleaseLogUtil.d(TAG_RELEASE, "saveBitmapToFile bitmap is null");
            return;
        }
        BufferedOutputStream bufferedOutputStream2 = 0;
        r4 = null;
        BufferedOutputStream bufferedOutputStream3 = null;
        BufferedOutputStream bufferedOutputStream4 = null;
        try {
            try {
                c = CommonUtil.c(str);
            } catch (Throwable th) {
                th = th;
                fileOutputStream = str2;
                bufferedOutputStream4 = bufferedOutputStream2;
            }
        } catch (IOException e) {
            e = e;
            fileOutputStream2 = null;
        } catch (Throwable th2) {
            th = th2;
            fileOutputStream = null;
        }
        if (TextUtils.isEmpty(c)) {
            ReleaseLogUtil.d(TAG_RELEASE, "saveBitmapToFile legalPath is empty");
            IoUtils.e((Closeable) null);
            IoUtils.e((Closeable) null);
            return;
        }
        File file = new File(c);
        if (!file.exists()) {
            ReleaseLogUtil.e(TAG_RELEASE, "saveBitmapToFile isMkdirs :", Boolean.valueOf(file.mkdirs()));
        }
        File e2 = FileUtils.e(file, (String) str2);
        if (e2.exists()) {
            FileUtils.i(e2);
        }
        fileOutputStream2 = new FileOutputStream(e2);
        try {
            bufferedOutputStream = new BufferedOutputStream(fileOutputStream2);
            bufferedOutputStream2 = 100;
        } catch (IOException e3) {
            e = e3;
        }
        try {
            bitmap.compress(compressFormat, 100, bufferedOutputStream);
            bufferedOutputStream.flush();
            IoUtils.e(bufferedOutputStream);
            str2 = fileOutputStream2;
        } catch (IOException e4) {
            e = e4;
            bufferedOutputStream3 = bufferedOutputStream;
            ReleaseLogUtil.c(TAG_RELEASE, "saveBitmapToFile IOException :", ExceptionUtils.d(e));
            IoUtils.e(bufferedOutputStream3);
            bufferedOutputStream2 = bufferedOutputStream3;
            str2 = fileOutputStream2;
            IoUtils.e((Closeable) str2);
        } catch (Throwable th3) {
            th = th3;
            bufferedOutputStream4 = bufferedOutputStream;
            fileOutputStream = fileOutputStream2;
            IoUtils.e(bufferedOutputStream4);
            IoUtils.e(fileOutputStream);
            throw th;
        }
        IoUtils.e((Closeable) str2);
    }

    public static void cropPngToRectF(String str, int i, int i2, int i3) throws IOException {
        float f;
        Bitmap decodeStream = BitmapFactory.decodeStream(new FileInputStream(str));
        Bitmap createBitmap = Bitmap.createBitmap(decodeStream.getWidth(), decodeStream.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Path path = new Path();
        float f2 = i;
        RectF rectF = new RectF(0.0f, 0.0f, f2, i2);
        if (i == i2) {
            if (i3 <= 0) {
                f = f2 / 2.0f;
            }
            f = i3;
        } else {
            if (i3 <= 0) {
                f = RADIUS_WEAR_RECT;
            }
            f = i3;
        }
        path.addRoundRect(rectF, f, f, Path.Direction.CW);
        canvas.clipPath(path);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        canvas.drawBitmap(decodeStream, 0.0f, 0.0f, paint);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(new File(str)));
        createBitmap.compress(Bitmap.CompressFormat.PNG, 100, bufferedOutputStream);
        bufferedOutputStream.flush();
        bufferedOutputStream.close();
    }
}
