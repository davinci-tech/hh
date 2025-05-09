package defpackage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;

/* loaded from: classes5.dex */
public class jrt {
    private static jrt c;
    private static final Object e = new Object();

    private jrt() {
    }

    public static jrt b() {
        jrt jrtVar;
        LogUtil.a("WatchFaceBitmapUtil", "getInstance");
        synchronized (e) {
            LogUtil.a("WatchFaceBitmapUtil", "getInstance() LOCK");
            if (c == null) {
                c = new jrt();
            }
            LogUtil.a("WatchFaceBitmapUtil", "getInstance");
            jrtVar = c;
        }
        return jrtVar;
    }

    public void c(String str, String str2) {
        DataOutputStream dataOutputStream;
        Bitmap decodeFile;
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("WatchFaceBitmapUtil", "createBinFile Failed, bitmapPath fileInputStream null");
            return;
        }
        DataOutputStream dataOutputStream2 = null;
        DataOutputStream dataOutputStream3 = null;
        try {
            try {
                dataOutputStream = new DataOutputStream(new BufferedOutputStream(FileUtils.openOutputStream(new File(CommonUtil.c(str2)))));
            } catch (Throwable th) {
                th = th;
                dataOutputStream = dataOutputStream2;
            }
        } catch (IOException unused) {
        }
        try {
            dataOutputStream.writeShort(17699);
            dataOutputStream.writeShort(34952);
            decodeFile = BitmapFactory.decodeFile(CommonUtil.c(str));
        } catch (IOException unused2) {
            dataOutputStream3 = dataOutputStream;
            LogUtil.b("WatchFaceBitmapUtil", "createBinFile, outputStream.flush IOException");
            dataOutputStream2 = dataOutputStream3;
            if (dataOutputStream3 != null) {
                try {
                    dataOutputStream3.close();
                    dataOutputStream2 = dataOutputStream3;
                } catch (IOException unused3) {
                    LogUtil.b("WatchFaceBitmapUtil", "createBinFile，outputStream.close IOException");
                    dataOutputStream2 = dataOutputStream3;
                }
            }
        } catch (Throwable th2) {
            th = th2;
            if (dataOutputStream != null) {
                try {
                    dataOutputStream.close();
                } catch (IOException unused4) {
                    LogUtil.b("WatchFaceBitmapUtil", "createBinFile，outputStream.close IOException");
                }
            }
            throw th;
        }
        if (decodeFile == null) {
            LogUtil.h("WatchFaceBitmapUtil", "decode source pictureBitmap file null");
            try {
                dataOutputStream.close();
                return;
            } catch (IOException unused5) {
                LogUtil.b("WatchFaceBitmapUtil", "createBinFile，outputStream.close IOException");
                return;
            }
        }
        int width = decodeFile.getWidth();
        int height = decodeFile.getHeight();
        b(dataOutputStream, width);
        b(dataOutputStream, height);
        int[] iArr = new int[width * height];
        decodeFile.getPixels(iArr, 0, width, 0, 0, width, height);
        a(dataOutputStream, iArr);
        dataOutputStream.flush();
        try {
            dataOutputStream.close();
            dataOutputStream2 = iArr;
        } catch (IOException unused6) {
            LogUtil.b("WatchFaceBitmapUtil", "createBinFile，outputStream.close IOException");
            dataOutputStream2 = iArr;
        }
    }

    private void a(DataOutputStream dataOutputStream, int[] iArr) throws IOException {
        int i = 1;
        for (int i2 = 0; i2 < iArr.length; i2++) {
            if (i2 == iArr.length - 1 || iArr[i2] != iArr[i2 + 1]) {
                a(dataOutputStream, iArr, i, i2);
                i = 1;
            } else {
                i++;
            }
        }
    }

    private void a(DataOutputStream dataOutputStream, int[] iArr, int i, int i2) throws IOException {
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

    /* JADX WARN: Multi-variable type inference failed */
    public void e(String str, String str2) {
        DataOutputStream dataOutputStream;
        Bitmap decodeFile;
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("WatchFaceBitmapUtil", "createBinFile Failed, bitmapPath fileInputStream null");
            return;
        }
        DataOutputStream dataOutputStream2 = null;
        DataOutputStream dataOutputStream3 = null;
        try {
            try {
                dataOutputStream = new DataOutputStream(new BufferedOutputStream(FileUtils.openOutputStream(new File(CommonUtil.c(str2)))));
            } catch (Throwable th) {
                th = th;
                dataOutputStream = dataOutputStream2;
            }
        } catch (IOException unused) {
        }
        try {
            dataOutputStream.writeShort(17699);
            dataOutputStream.writeShort(34824);
            decodeFile = BitmapFactory.decodeFile(CommonUtil.c(str));
        } catch (IOException unused2) {
            dataOutputStream3 = dataOutputStream;
            LogUtil.b("WatchFaceBitmapUtil", "createBinFile, dataOutputStream.flush IOException");
            dataOutputStream2 = dataOutputStream3;
            if (dataOutputStream3 != null) {
                try {
                    dataOutputStream3.close();
                    dataOutputStream2 = dataOutputStream3;
                } catch (IOException unused3) {
                    LogUtil.b("WatchFaceBitmapUtil", "createBinFile，dataOutputStream.close IOException");
                    dataOutputStream2 = dataOutputStream3;
                }
            }
        } catch (Throwable th2) {
            th = th2;
            if (dataOutputStream != null) {
                try {
                    dataOutputStream.close();
                } catch (IOException unused4) {
                    LogUtil.b("WatchFaceBitmapUtil", "createBinFile，dataOutputStream.close IOException");
                }
            }
            throw th;
        }
        if (decodeFile == null) {
            LogUtil.h("WatchFaceBitmapUtil", "decode source bitmap file null");
            try {
                dataOutputStream.close();
                return;
            } catch (IOException unused5) {
                LogUtil.b("WatchFaceBitmapUtil", "createBinFile，dataOutputStream.close IOException");
                return;
            }
        }
        int width = decodeFile.getWidth();
        int height = decodeFile.getHeight();
        b(dataOutputStream, width);
        b(dataOutputStream, height);
        int i = width * height;
        int[] iArr = new int[i];
        LogUtil.a("WatchFaceBitmapUtil", "width * height = " + width + " * " + height + " = " + i);
        decodeFile.getPixels(iArr, 0, width, 0, 0, width, height);
        StringBuilder sb = new StringBuilder("org size : ");
        sb.append(i * 4);
        LogUtil.a("WatchFaceBitmapUtil", sb.toString());
        c(dataOutputStream, iArr);
        Object[] objArr = {"bin dataOutputStream size : " + dataOutputStream.size()};
        LogUtil.a("WatchFaceBitmapUtil", objArr);
        dataOutputStream.flush();
        try {
            dataOutputStream.close();
            dataOutputStream2 = objArr;
        } catch (IOException unused6) {
            LogUtil.b("WatchFaceBitmapUtil", "createBinFile，dataOutputStream.close IOException");
            dataOutputStream2 = objArr;
        }
    }

    private void c(DataOutputStream dataOutputStream, int[] iArr) throws IOException {
        int i = 1;
        for (int i2 = 0; i2 < iArr.length; i2++) {
            if (i2 == iArr.length - 1 || iArr[i2] != iArr[i2 + 1]) {
                d(dataOutputStream, iArr, i, i2);
                i = 1;
            } else {
                i++;
            }
        }
    }

    private void d(DataOutputStream dataOutputStream, int[] iArr, int i, int i2) throws IOException {
        if (i >= 4) {
            e(dataOutputStream, 4548489);
            e(dataOutputStream, iArr[i2]);
            e(dataOutputStream, i);
        } else {
            for (int i3 = 0; i3 < i; i3++) {
                e(dataOutputStream, iArr[i2]);
            }
        }
    }

    private void e(DataOutputStream dataOutputStream, int i) throws IOException {
        dataOutputStream.writeByte(i & 255);
        dataOutputStream.writeByte((i >> 8) & 255);
        dataOutputStream.writeByte((i >> 16) & 255);
    }
}
