package defpackage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.text.TextUtils;
import com.huawei.haf.common.exception.ExceptionUtils;
import health.compact.a.LogUtil;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;

/* loaded from: classes9.dex */
public class snv {
    private static snv c;
    private static final Object d = new Object();

    private int a(int i) {
        return (-16777216) | ((((63488 & i) >> 8) << 16) + (((i & 2016) >> 3) << 8) + ((i & 31) << 3));
    }

    private snv() {
    }

    public static snv b() {
        snv snvVar;
        synchronized (d) {
            LogUtil.c("BitmapUtil", "getInstance() LOCK");
            if (c == null) {
                c = new snv();
            }
            snvVar = c;
        }
        return snvVar;
    }

    public void d(String str, String str2) {
        DataOutputStream dataOutputStream;
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            LogUtil.a("BitmapUtil", "createBinFile Failed, bitmapPath fileInputStream null");
            return;
        }
        DataOutputStream dataOutputStream2 = null;
        try {
            try {
                dataOutputStream = new DataOutputStream(new BufferedOutputStream(FileUtils.openOutputStream(new File(bky.d(str2)))));
            } catch (IOException unused) {
            }
        } catch (Throwable th) {
            th = th;
            dataOutputStream = dataOutputStream2;
        }
        try {
            dataOutputStream.writeShort(17699);
            dataOutputStream.writeShort(34952);
            String d2 = bky.d(str);
            if (new File(d2).length() >= 10485760) {
                LogUtil.a("BitmapUtil", "The file size exceeds 10 MB.");
                try {
                    dataOutputStream.close();
                    return;
                } catch (IOException unused2) {
                    LogUtil.e("BitmapUtil", "createBinFile，outputStream.close IOException");
                    return;
                }
            }
            Bitmap decodeFile = BitmapFactory.decodeFile(d2);
            if (decodeFile == null) {
                LogUtil.a("BitmapUtil", "decode source pictureBitmap file null");
                try {
                    dataOutputStream.close();
                    return;
                } catch (IOException unused3) {
                    LogUtil.e("BitmapUtil", "createBinFile，outputStream.close IOException");
                    return;
                }
            }
            int width = decodeFile.getWidth();
            int height = decodeFile.getHeight();
            b(dataOutputStream, width);
            b(dataOutputStream, height);
            int[] iArr = new int[Math.multiplyExact(width, height)];
            decodeFile.getPixels(iArr, 0, width, 0, 0, width, height);
            e(dataOutputStream, iArr);
            dataOutputStream.flush();
            try {
                dataOutputStream.close();
            } catch (IOException unused4) {
                LogUtil.e("BitmapUtil", "createBinFile，outputStream.close IOException");
            }
        } catch (IOException unused5) {
            dataOutputStream2 = dataOutputStream;
            LogUtil.e("BitmapUtil", "createBinFile, outputStream.flush IOException");
            if (dataOutputStream2 != null) {
                try {
                    dataOutputStream2.close();
                } catch (IOException unused6) {
                    LogUtil.e("BitmapUtil", "createBinFile，outputStream.close IOException");
                }
            }
        } catch (Throwable th2) {
            th = th2;
            if (dataOutputStream != null) {
                try {
                    dataOutputStream.close();
                } catch (IOException unused7) {
                    LogUtil.e("BitmapUtil", "createBinFile，outputStream.close IOException");
                }
            }
            throw th;
        }
    }

    private void e(DataOutputStream dataOutputStream, int[] iArr) throws IOException {
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

    private void e(DataOutputStream dataOutputStream, int[] iArr, int i, int i2) throws IOException {
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

    private int[] e(DataInputStream dataInputStream, int i) throws IOException {
        int[] iArr = new int[i];
        int i2 = 0;
        while (i2 < i) {
            int c2 = c(dataInputStream);
            if (c2 == 591751049) {
                int c3 = c(dataInputStream);
                int c4 = c(dataInputStream);
                for (int i3 = 0; i3 < c4; i3++) {
                    iArr[i2] = c3;
                    i2++;
                }
            } else {
                iArr[i2] = c2;
                i2++;
            }
        }
        return iArr;
    }

    private void b(DataOutputStream dataOutputStream, int i) throws IOException {
        dataOutputStream.writeByte(i & 255);
        dataOutputStream.writeByte((i >> 8) & 255);
    }

    private void a(DataOutputStream dataOutputStream, int i) throws IOException {
        dataOutputStream.writeByte(i & 255);
        dataOutputStream.writeByte((i >> 8) & 255);
        dataOutputStream.writeByte((i >> 16) & 255);
        dataOutputStream.writeByte((i >> 24) & 255);
    }

    private int d(DataInputStream dataInputStream) throws IOException {
        return ((dataInputStream.readByte() & 255) << 8) | (dataInputStream.readByte() & 255);
    }

    private int c(DataInputStream dataInputStream) throws IOException {
        return ((dataInputStream.readByte() & 255) << 24) | (dataInputStream.readByte() & 255) | ((dataInputStream.readByte() & 255) << 8) | ((dataInputStream.readByte() & 255) << 16);
    }

    private int b(DataInputStream dataInputStream) throws IOException {
        return ((dataInputStream.readByte() & 255) << 16) | (dataInputStream.readByte() & 255) | ((dataInputStream.readByte() & 255) << 8);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Not initialized variable reg: 5, insn: 0x00e5: MOVE (r4 I:??[OBJECT, ARRAY]) = (r5 I:??[OBJECT, ARRAY]), block:B:52:0x00e4 */
    /* JADX WARN: Removed duplicated region for block: B:20:0x00b7 A[Catch: IOException -> 0x00bb, TRY_LEAVE, TryCatch #6 {IOException -> 0x00bb, blocks: (B:18:0x00b2, B:20:0x00b7), top: B:17:0x00b2 }] */
    /* JADX WARN: Removed duplicated region for block: B:24:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00ed A[Catch: IOException -> 0x00f1, TRY_LEAVE, TryCatch #4 {IOException -> 0x00f1, blocks: (B:60:0x00e8, B:55:0x00ed), top: B:59:0x00e8 }] */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00e8 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r4v0, types: [java.io.FileOutputStream] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void e(java.lang.String r14, java.lang.String r15) {
        /*
            Method dump skipped, instructions count: 259
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.snv.e(java.lang.String, java.lang.String):void");
    }

    private int[] c(DataInputStream dataInputStream, int i) {
        int[] iArr = new int[i];
        int i2 = 0;
        while (i2 < i) {
            try {
                int c2 = c(dataInputStream);
                if (c2 == 591751049) {
                    int c3 = c(dataInputStream);
                    int c4 = c(dataInputStream);
                    for (int i3 = 0; i3 < c4 * 2; i3++) {
                        iArr[i2] = a(c3 >> 16);
                        i2++;
                    }
                } else {
                    iArr[i2] = a(c2 >> 16);
                    iArr[i2 + 1] = a(c2 & 65535);
                    i2 += 2;
                }
            } catch (IOException e) {
                LogUtil.c("BitmapUtil", "read16BitPixels IOException: " + ExceptionUtils.d(e));
                return iArr;
            }
        }
        LogUtil.c("BitmapUtil", "read16BitPixels length: " + i);
        return iArr;
    }

    private int[] b(DataInputStream dataInputStream, int i, int i2) throws IOException {
        int[] iArr = new int[i2];
        if (i == 1159956488) {
            return b(dataInputStream, i2);
        }
        if (i == 1159947765) {
            return c(dataInputStream, i2);
        }
        return e(dataInputStream, i2);
    }

    private int[] b(DataInputStream dataInputStream, int i) throws IOException {
        int[] iArr = new int[i];
        int i2 = 0;
        while (true) {
            if (i2 >= i) {
                break;
            }
            int b = b(dataInputStream);
            if (b == 4548489) {
                int b2 = b(dataInputStream);
                int b3 = b(dataInputStream);
                if (b3 > i) {
                    LogUtil.a("BitmapUtil", "length less than count.");
                    break;
                }
                for (int i3 = 0; i3 < b3; i3++) {
                    iArr[i2] = b2 | (-16777216);
                    i2++;
                }
            } else {
                iArr[i2] = b | (-16777216);
                i2++;
            }
        }
        LogUtil.c("BitmapUtil", "read24BitPixels length : " + i);
        return iArr;
    }

    private Bitmap ejF_(Bitmap bitmap) {
        Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setFilterBitmap(true);
        paint.setColor(-12434878);
        Canvas canvas = new Canvas(createBitmap);
        canvas.drawARGB(0, 0, 0, 0);
        Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        canvas.drawCircle(bitmap.getWidth() / 2.0f, bitmap.getHeight() / 2.0f, bitmap.getWidth() / 2.0f, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return createBitmap;
    }
}
