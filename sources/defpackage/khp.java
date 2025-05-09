package defpackage;

import android.graphics.Bitmap;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;

/* loaded from: classes5.dex */
public class khp {
    public static void bNU_(Bitmap bitmap, String str) {
        DataOutputStream dataOutputStream;
        if (bitmap == null) {
            LogUtil.h("NotificationBitmapUtils", "bitmapToBinFileAndSave pictureBitmap is null");
            return;
        }
        DataOutputStream dataOutputStream2 = null;
        try {
            try {
                dataOutputStream = new DataOutputStream(new BufferedOutputStream(FileUtils.openOutputStream(new File(CommonUtil.c(str)))));
            } catch (IOException unused) {
            }
        } catch (Throwable th) {
            th = th;
            dataOutputStream = dataOutputStream2;
        }
        try {
            dataOutputStream.writeShort(17699);
            dataOutputStream.writeShort(34952);
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            d(dataOutputStream, width);
            d(dataOutputStream, height);
            int[] iArr = new int[Math.multiplyExact(width, height)];
            bitmap.getPixels(iArr, 0, width, 0, 0, width, height);
            e(dataOutputStream, iArr);
            dataOutputStream.flush();
            try {
                dataOutputStream.close();
            } catch (IOException unused2) {
                LogUtil.b("NotificationBitmapUtils", "bitmapToBinFileAndSave outputStream.close IOException");
            }
        } catch (IOException unused3) {
            dataOutputStream2 = dataOutputStream;
            LogUtil.b("NotificationBitmapUtils", "bitmapToBinFileAndSave outputStream.flush IOException");
            if (dataOutputStream2 != null) {
                try {
                    dataOutputStream2.close();
                } catch (IOException unused4) {
                    LogUtil.b("NotificationBitmapUtils", "bitmapToBinFileAndSave outputStream.close IOException");
                }
            }
        } catch (Throwable th2) {
            th = th2;
            if (dataOutputStream != null) {
                try {
                    dataOutputStream.close();
                } catch (IOException unused5) {
                    LogUtil.b("NotificationBitmapUtils", "bitmapToBinFileAndSave outputStream.close IOException");
                }
            }
            throw th;
        }
    }

    private static void d(DataOutputStream dataOutputStream, int i) throws IOException {
        dataOutputStream.writeByte(i & 255);
        dataOutputStream.writeByte((i >> 8) & 255);
    }

    private static void e(DataOutputStream dataOutputStream, int[] iArr) throws IOException {
        int i = 1;
        for (int i2 = 0; i2 < iArr.length; i2++) {
            if (i2 == iArr.length - 1 || iArr[i2] != iArr[i2 + 1]) {
                e(dataOutputStream, iArr, i, i2);
                i = 1;
            } else {
                i++;
            }
        }
    }

    private static void e(DataOutputStream dataOutputStream, int[] iArr, int i, int i2) throws IOException {
        if (i >= 4) {
            a(dataOutputStream, 591751049);
            a(dataOutputStream, iArr[i2]);
            a(dataOutputStream, i);
        } else {
            for (int i3 = 0; i3 < i; i3++) {
                a(dataOutputStream, iArr[i2]);
            }
        }
    }

    private static void a(DataOutputStream dataOutputStream, int i) throws IOException {
        dataOutputStream.writeByte(i & 255);
        dataOutputStream.writeByte((i >> 8) & 255);
        dataOutputStream.writeByte((i >> 16) & 255);
        dataOutputStream.writeByte((i >> 24) & 255);
    }
}
