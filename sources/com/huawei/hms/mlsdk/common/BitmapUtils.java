package com.huawei.hms.mlsdk.common;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.YuvImage;
import com.huawei.hms.ml.common.utils.StreamUtils;
import com.huawei.hms.mlsdk.common.MLFrame;
import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;

/* loaded from: classes9.dex */
public final class BitmapUtils {
    private static final String TAG = "BitmapUtils";

    public static Bitmap getBitmap(ByteBuffer byteBuffer, MLFrame.Property property) {
        ByteArrayOutputStream byteArrayOutputStream;
        try {
            byteBuffer.rewind();
            int limit = byteBuffer.limit();
            byte[] bArr = new byte[limit];
            byteBuffer.get(bArr, 0, limit);
            YuvImage yuvImage = new YuvImage(bArr, 17, property.getWidth(), property.getHeight(), null);
            byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                yuvImage.compressToJpeg(new Rect(0, 0, property.getWidth(), property.getHeight()), 100, byteArrayOutputStream);
                Bitmap rotateBitmap = rotateBitmap(BitmapFactory.decodeByteArray(byteArrayOutputStream.toByteArray(), 0, byteArrayOutputStream.size()), property.getQuadrant());
                StreamUtils.closeStreams(byteArrayOutputStream);
                return rotateBitmap;
            } catch (Throwable th) {
                th = th;
                StreamUtils.closeStreams(byteArrayOutputStream);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            byteArrayOutputStream = null;
        }
    }

    public static Bitmap rotateBitmap(Bitmap bitmap, int i) {
        Matrix matrix = new Matrix();
        matrix.postRotate(i != 1 ? i != 2 ? i != 3 ? 0 : 270 : 180 : 90);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    public static Bitmap cut(Bitmap bitmap, Point[] pointArr, int i) {
        if (bitmap == null || pointArr == null || pointArr.length != 4) {
            return null;
        }
        for (Point point : pointArr) {
            if (point == null) {
                return null;
            }
        }
        Point point2 = pointArr[0];
        Point point3 = pointArr[1];
        Point point4 = pointArr[2];
        double atan2 = Math.atan2(point3.y - point2.y, point3.x - point2.x);
        float degrees = (float) Math.toDegrees(atan2);
        if (Math.abs((int) degrees) <= 0) {
            return cutPaddingExtendRect(bitmap, point2, point4, 0, 0, i);
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int i2 = width >> 1;
        int i3 = height >> 1;
        Point point5 = new Point(i2, i3);
        Matrix matrix = new Matrix();
        matrix.setRotate(-degrees, i2, i3);
        Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
        return cutPaddingExtendRect(createBitmap, rotateCoordinate(bitmap, point5, point2, atan2), rotateCoordinate(bitmap, point5, point4, atan2), (createBitmap.getWidth() - width) >> 1, (createBitmap.getHeight() - height) >> 1, i);
    }

    private static Point rotateCoordinate(Bitmap bitmap, Point point, Point point2, double d) {
        int height = bitmap.getHeight();
        int i = point2.x;
        int i2 = point2.y;
        int i3 = point.x;
        int i4 = height - point.y;
        double d2 = i - i3;
        double d3 = (height - i2) - i4;
        return new Point((int) (((Math.cos(d) * d2) - (Math.sin(d) * d3)) + i3), height - ((int) (((d2 * Math.sin(d)) + (d3 * Math.cos(d))) + i4)));
    }

    private static Bitmap cutPaddingExtendRect(Bitmap bitmap, Point point, Point point2, int i, int i2, int i3) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int i4 = (point.x + i) - i3;
        if (i4 < 0) {
            i4 = 0;
        }
        int i5 = (point.y + i2) - i3;
        int i6 = i5 >= 0 ? i5 : 0;
        int i7 = point2.x + i + i3;
        if (i7 <= width) {
            width = i7;
        }
        int i8 = point2.y + i2 + i3;
        if (i8 <= height) {
            height = i8;
        }
        return Bitmap.createBitmap(bitmap, i4, i6, width - i4, height - i6);
    }
}
