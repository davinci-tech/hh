package com.huawei.health.h5pro.jsbridge.system.media;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.ExifInterface;
import android.text.TextUtils;
import android.util.Base64;
import com.huawei.health.h5pro.utils.CommonUtil;
import com.huawei.health.h5pro.utils.GeneralUtil;
import com.huawei.health.h5pro.utils.LogUtil;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLConnection;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes3.dex */
public class BitmapUtils {

    public interface OnCompressPictureCallback {
        void onFailure(String str);

        void onSuccess(File file, String str);
    }

    public static String saveBitmap(Context context, String str, Bitmap bitmap, Bitmap.CompressFormat compressFormat) {
        File saveFilePath;
        String str2 = "";
        if (bitmap != null && !TextUtils.isEmpty(str) && (saveFilePath = getSaveFilePath(context, str)) != null) {
            try {
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(saveFilePath, false));
                try {
                    bitmap.compress(compressFormat, 100, bufferedOutputStream);
                    str2 = saveFilePath.getCanonicalPath();
                    bufferedOutputStream.close();
                } finally {
                }
            } catch (IOException e) {
                LogUtil.e("H5PRO_BitmapUtils", "saveBitmap: exception -> " + e.getMessage());
            }
        }
        return str2;
    }

    public static String parseBitmapToBase64(Bitmap bitmap) {
        String str;
        ByteArrayOutputStream byteArrayOutputStream;
        ByteArrayOutputStream byteArrayOutputStream2 = null;
        if (bitmap != null) {
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
                try {
                    try {
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
                        byteArrayOutputStream.flush();
                        str = Base64.encodeToString(byteArrayOutputStream.toByteArray(), 0);
                        byteArrayOutputStream2 = byteArrayOutputStream;
                    } catch (IOException e) {
                        e = e;
                        LogUtil.e("H5PRO_BitmapUtils", "parseBitmapToBase64: " + e.getMessage());
                        CommonUtil.closeStream(byteArrayOutputStream);
                        return null;
                    }
                } catch (Throwable th) {
                    th = th;
                    byteArrayOutputStream2 = byteArrayOutputStream;
                    CommonUtil.closeStream(byteArrayOutputStream2);
                    throw th;
                }
            } catch (IOException e2) {
                e = e2;
                byteArrayOutputStream = null;
            } catch (Throwable th2) {
                th = th2;
                CommonUtil.closeStream(byteArrayOutputStream2);
                throw th;
            }
        } else {
            str = null;
        }
        CommonUtil.closeStream(byteArrayOutputStream2);
        return str;
    }

    public static File getSaveFilePath(Context context, String str) {
        File file = new File(context.getCacheDir(), "save_bitmap" + File.separator + str);
        try {
            file = file.getCanonicalFile();
        } catch (IOException e) {
            LogUtil.e("H5PRO_BitmapUtils", "getSaveFilePath: exception -> " + e.getMessage());
        }
        File parentFile = file.getParentFile();
        if (parentFile != null && !parentFile.exists()) {
            parentFile.mkdirs();
        }
        return file;
    }

    public static int getPictureRotate(String str) {
        int i = 0;
        if (!GeneralUtil.isSafePath(str)) {
            LogUtil.w("H5PRO_BitmapUtils", "getPictureRotate: invalid file path");
            return 0;
        }
        try {
            int attributeInt = new ExifInterface(str).getAttributeInt(androidx.exifinterface.media.ExifInterface.TAG_ORIENTATION, 0);
            if (attributeInt == 6) {
                i = 90;
            } else if (attributeInt == 3) {
                i = 180;
            } else if (attributeInt == 8) {
                i = 270;
            }
        } catch (IOException e) {
            LogUtil.e("H5PRO_BitmapUtils", "getPictureRotate: exception -> " + e.getMessage());
        }
        LogUtil.i("H5PRO_BitmapUtils", "getPictureRotate: rotate -> " + i);
        return i;
    }

    public static Bitmap decodeFileToBitmap(File file) {
        int pictureRotate = getPictureRotate(file.getAbsolutePath());
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        Bitmap decodeFile = BitmapFactory.decodeFile(file.getAbsolutePath(), options);
        if (pictureRotate == 0) {
            return decodeFile;
        }
        Matrix matrix = new Matrix();
        matrix.postRotate(pictureRotate);
        Bitmap createBitmap = Bitmap.createBitmap(decodeFile, 0, 0, decodeFile.getWidth(), decodeFile.getHeight(), matrix, true);
        if (!decodeFile.isRecycled()) {
            decodeFile.recycle();
        }
        return createBitmap;
    }

    public static Bitmap cropBitmap(Bitmap bitmap) {
        int min = Math.min(bitmap.getWidth(), bitmap.getHeight());
        return Bitmap.createBitmap(bitmap, (bitmap.getWidth() - min) / 2, (bitmap.getHeight() - min) / 2, min, min);
    }

    public static void compressToTargetSize(File file, long j, boolean z, OnCompressPictureCallback onCompressPictureCallback) {
        OnCompressPictureCallback onCompressPictureCallback2;
        long length = file.length();
        LogUtil.i("H5PRO_BitmapUtils", "compressToTargetSize: targetSize -> " + j + ", sourceFileSize -> " + length);
        Bitmap decodeFileToBitmap = decodeFileToBitmap(file);
        if (decodeFileToBitmap == null) {
            onCompressPictureCallback.onFailure("bitmap is null");
            return;
        }
        int width = decodeFileToBitmap.getWidth();
        int height = decodeFileToBitmap.getHeight();
        LogUtil.i("H5PRO_BitmapUtils", "compressToTargetSize: bitmapWidth -> " + width + ", bitmapHeight -> " + height + ", bitmapByteCount -> " + decodeFileToBitmap.getByteCount());
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int max = length / j >= 10 ? Math.max(width / 1000, 2) : 2;
        int i = width / max;
        int i2 = height / max;
        LogUtil.i("H5PRO_BitmapUtils", "compressToTargetSize: compressRatio -> " + max);
        do {
            byteArrayOutputStream.reset();
            Bitmap createBitmap = Bitmap.createBitmap(i, i2, Bitmap.Config.RGB_565);
            new Canvas(createBitmap).drawBitmap(decodeFileToBitmap, (Rect) null, new Rect(0, 0, i, i2), (Paint) null);
            createBitmap.compress(Bitmap.CompressFormat.JPEG, 80, byteArrayOutputStream);
            if (!createBitmap.isRecycled()) {
                createBitmap.recycle();
            }
            max++;
            i = width / max;
            i2 = height / max;
            if (max > 12 || byteArrayOutputStream.size() <= j || i < 500) {
                break;
            }
        } while (i2 >= 500);
        if (!decodeFileToBitmap.isRecycled()) {
            decodeFileToBitmap.recycle();
        }
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        LogUtil.i("H5PRO_BitmapUtils", "compressToTargetSize: compressRatio -> " + max + ", resultFileSize -> " + byteArray.length);
        String encodeToString = Base64.encodeToString(byteArray, 16);
        if (z) {
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                try {
                    fileOutputStream.write(byteArray);
                    fileOutputStream.flush();
                    fileOutputStream.close();
                } finally {
                }
            } catch (IOException e) {
                LogUtil.e("H5PRO_BitmapUtils", "compressToTargetSize: exception-> " + e.getMessage());
                onCompressPictureCallback2 = onCompressPictureCallback;
                onCompressPictureCallback2.onFailure(e.getMessage());
            }
        }
        onCompressPictureCallback2 = onCompressPictureCallback;
        onCompressPictureCallback2.onSuccess(file, String.format(Locale.ENGLISH, "data:%s;base64,%s", URLConnection.getFileNameMap().getContentTypeFor(file.getAbsolutePath()), encodeToString));
    }

    public static void compress(final File file, final long j, final boolean z, final OnCompressPictureCallback onCompressPictureCallback) {
        if (file == null || !file.exists()) {
            onCompressPictureCallback.onFailure("compress: the file does not exist");
        } else {
            if (j <= 0) {
                onCompressPictureCallback.onSuccess(file, "");
                return;
            }
            ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
            newSingleThreadExecutor.execute(new Runnable() { // from class: com.huawei.health.h5pro.jsbridge.system.media.BitmapUtils.1
                @Override // java.lang.Runnable
                public void run() {
                    BitmapUtils.compressToTargetSize(file, j, z, onCompressPictureCallback);
                }
            });
            newSingleThreadExecutor.shutdown();
        }
    }

    public static void compress(File file, long j, OnCompressPictureCallback onCompressPictureCallback) {
        compress(file, j, true, onCompressPictureCallback);
    }

    public static Bitmap compress(File file, long j) {
        if (file == null || !file.exists()) {
            LogUtil.w("H5PRO_BitmapUtils", "compress: the sourceImageFile does not exist");
            return null;
        }
        return compress(BitmapFactory.decodeFile(file.getAbsolutePath(), new BitmapFactory.Options()), j);
    }

    public static Bitmap compress(Bitmap bitmap, long j) {
        if (j <= 0 || bitmap == null) {
            LogUtil.w("H5PRO_BitmapUtils", "compress: Invalid parameter");
            return bitmap;
        }
        if (bitmap.getByteCount() <= j) {
            return bitmap;
        }
        Bitmap cropBitmap = cropBitmap(bitmap);
        if (cropBitmap.getByteCount() <= j) {
            return cropBitmap;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Bitmap.CompressFormat compressFormat = Bitmap.CompressFormat.JPEG;
        int i = 100;
        while (true) {
            cropBitmap.compress(compressFormat, i, byteArrayOutputStream);
            if (byteArrayOutputStream.toByteArray().length <= j || i < 10) {
                break;
            }
            i -= 10;
            byteArrayOutputStream.reset();
            compressFormat = Bitmap.CompressFormat.JPEG;
        }
        return BitmapFactory.decodeStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()), null, null);
    }
}
