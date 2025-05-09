package com.huawei.hms.ml.common.utils;

import android.graphics.Bitmap;
import android.graphics.ImageFormat;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.media.Image;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;

/* loaded from: classes9.dex */
public final class ImageConvertUtils {
    public static final int INDEX_PLANE_U = 1;
    public static final int INDEX_PLANE_V = 2;
    public static final int INDEX_PLANE_Y = 0;
    private static final ImageConvertUtils INSTANCE = new ImageConvertUtils();
    public static final int YUV_FORMAT_I420 = 1;
    public static final int YUV_FORMAT_NV21 = 2;

    private static int getChannelOffset(int i, int i2, int i3) {
        if (i != 0) {
            return i != 1 ? i2 == 1 ? (int) (i3 * 1.25d) : i3 : i2 == 1 ? i3 : i3 + 1;
        }
        return 0;
    }

    private static int getOutputStride(int i, int i2) {
        return (i == 0 || i2 == 1) ? 1 : 2;
    }

    private ImageConvertUtils() {
    }

    public static ImageConvertUtils getInstance() {
        return INSTANCE;
    }

    public static byte[] buffer2Byte(ByteBuffer byteBuffer) {
        byteBuffer.rewind();
        int limit = byteBuffer.limit();
        byte[] bArr = new byte[limit];
        byteBuffer.get(bArr, 0, limit);
        return bArr;
    }

    public static byte[] nv21ToJpeg(byte[] bArr, int i, int i2) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                new YuvImage(bArr, 17, i, i2, null).compressToJpeg(new Rect(0, 0, i, i2), 100, byteArrayOutputStream);
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                byteArrayOutputStream.close();
                return byteArray;
            } finally {
            }
        } catch (IOException unused) {
            return new byte[0];
        }
    }

    public static byte[] bitmap2Jpeg(Bitmap bitmap, int i) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                bitmap.compress(Bitmap.CompressFormat.JPEG, i, byteArrayOutputStream);
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                byteArrayOutputStream.close();
                return byteArray;
            } finally {
            }
        } catch (IOException unused) {
            return new byte[0];
        }
    }

    public static byte[] yuv2Buffer(Image image) {
        Image.Plane[] planes = image.getPlanes();
        ByteBuffer buffer = planes[0].getBuffer();
        ByteBuffer buffer2 = planes[1].getBuffer();
        ByteBuffer buffer3 = planes[2].getBuffer();
        int remaining = buffer.remaining();
        int remaining2 = buffer2.remaining();
        int remaining3 = buffer3.remaining();
        int i = remaining + remaining2 + remaining3;
        byte[] bArr = new byte[i];
        buffer.get(bArr, 0, remaining);
        buffer3.get(bArr, remaining, remaining3);
        buffer2.get(bArr, i - remaining2, remaining2);
        return bArr;
    }

    public static byte[] byteToNv21(byte[] bArr) {
        int length = bArr.length;
        int i = (length * 2) / 3;
        int i2 = length / 6;
        byte[] bArr2 = new byte[length];
        System.arraycopy(bArr, 0, bArr2, 0, i);
        for (int i3 = 0; i3 < length - i; i3++) {
            if (i3 % 2 == 0) {
                bArr2[i + i3] = bArr[(i3 / 2) + i];
            } else {
                bArr2[i + i3] = bArr[i + i2 + (i3 / 2)];
            }
        }
        return bArr2;
    }

    public static byte[] bitmapToNv21(Bitmap bitmap, int i, int i2) {
        if (bitmap == null || bitmap.getWidth() < i || bitmap.getHeight() < i2) {
            return new byte[0];
        }
        int[] iArr = new int[i * i2];
        bitmap.getPixels(iArr, 0, i, 0, 0, i, i2);
        return argbToNv21(iArr, i, i2);
    }

    private static byte[] argbToNv21(int[] iArr, int i, int i2) {
        int i3 = i * i2;
        int i4 = (i3 * 3) / 2;
        byte[] bArr = new byte[i4];
        int i5 = 0;
        int i6 = 0;
        for (int i7 = 0; i7 < i2; i7++) {
            int i8 = 0;
            while (i8 < i) {
                int i9 = iArr[i6];
                int i10 = (16711680 & i9) >> 16;
                int i11 = (65280 & i9) >> 8;
                int i12 = 255;
                int i13 = i9 & 255;
                int i14 = (((((i10 * 66) + (i11 * 129)) + (i13 * 25)) + 128) >> 8) + 16;
                int i15 = (((((i10 * (-38)) - (i11 * 74)) + (i13 * 112)) + 128) >> 8) + 128;
                int i16 = (((((i10 * 112) - (i11 * 94)) - (i13 * 18)) + 128) >> 8) + 128;
                if (i14 < 0) {
                    i14 = 0;
                } else if (i14 > 255) {
                    i14 = 255;
                }
                bArr[i5] = (byte) i14;
                if (i7 % 2 == 0 && i6 % 2 == 0 && i3 < i4 - 2) {
                    int i17 = i3 + 1;
                    if (i16 < 0) {
                        i16 = 0;
                    } else if (i16 > 255) {
                        i16 = 255;
                    }
                    bArr[i3] = (byte) i16;
                    i3 += 2;
                    if (i15 < 0) {
                        i12 = 0;
                    } else if (i15 <= 255) {
                        i12 = i15;
                    }
                    bArr[i17] = (byte) i12;
                }
                i6++;
                i8++;
                i5++;
            }
        }
        return bArr;
    }

    public static byte[] nv21ToGray(byte[] bArr, int i, int i2) {
        int i3 = i * i2;
        int i4 = i3 / 2;
        byte[] bArr2 = new byte[i4];
        Arrays.fill(bArr2, Byte.MIN_VALUE);
        System.arraycopy(bArr2, 0, bArr, i3, i4);
        return bArr;
    }

    private static boolean isImageFormatSupported(Image image) {
        int format = image.getFormat();
        return format == 35 || format == 17 || format == 842094169;
    }

    public static byte[] getDataFromImage(Image image, int i) {
        Rect rect;
        int i2;
        int i3 = i;
        checkFormat(image, i);
        Rect cropRect = image.getCropRect();
        int format = image.getFormat();
        int width = cropRect.width();
        int height = cropRect.height();
        Image.Plane[] planes = image.getPlanes();
        int i4 = width * height;
        byte[] bArr = new byte[(ImageFormat.getBitsPerPixel(format) * i4) / 8];
        int i5 = 0;
        byte[] bArr2 = new byte[planes[0].getRowStride()];
        int i6 = 0;
        while (i6 < planes.length) {
            ByteBuffer buffer = planes[i6].getBuffer();
            int rowStride = planes[i6].getRowStride();
            int pixelStride = planes[i6].getPixelStride();
            int channelOffset = getChannelOffset(i6, i3, i4);
            int outputStride = getOutputStride(i6, i3);
            int i7 = i6 == 0 ? i5 : 1;
            int i8 = width >> i7;
            int i9 = height >> i7;
            int i10 = width;
            buffer.position(((cropRect.top >> i7) * rowStride) + ((cropRect.left >> i7) * pixelStride));
            int i11 = 0;
            while (i11 < i9) {
                if (pixelStride != 1 || outputStride != 1) {
                    rect = cropRect;
                    i2 = ((i8 - 1) * pixelStride) + 1;
                    buffer.get(bArr2, 0, i2);
                    for (int i12 = 0; i12 < i8; i12++) {
                        bArr[channelOffset] = bArr2[i12 * pixelStride];
                        channelOffset += outputStride;
                    }
                } else {
                    buffer.get(bArr, channelOffset, i8);
                    channelOffset += i8;
                    rect = cropRect;
                    i2 = i8;
                }
                if (i11 < i9 - 1) {
                    buffer.position((buffer.position() + rowStride) - i2);
                }
                i11++;
                cropRect = rect;
            }
            i6++;
            i3 = i;
            width = i10;
            i5 = 0;
        }
        return bArr;
    }

    private static void checkFormat(Image image, int i) {
        if (i != 1 && i != 2) {
            throw new IllegalArgumentException("only support YUV_FORMAT_I420 and YUV_FORMAT_NV21");
        }
        if (isImageFormatSupported(image)) {
            return;
        }
        throw new RuntimeException("can't convert Image to byte array, format " + image.getFormat());
    }
}
