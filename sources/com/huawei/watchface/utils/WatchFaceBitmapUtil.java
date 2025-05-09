package com.huawei.watchface.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.media.MediaMetadataRetriever;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.constant.WatchFaceConstant;
import com.huawei.secure.android.common.util.SafeString;
import com.huawei.watchface.cq;
import com.huawei.watchface.environment.Environment;
import com.huawei.watchface.flavor.FlavorConfig;
import com.huawei.watchface.mvp.model.crypt.PversionSdUtils;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/* loaded from: classes7.dex */
public class WatchFaceBitmapUtil {
    private static WatchFaceBitmapUtil c;

    /* renamed from: a, reason: collision with root package name */
    Bitmap f11197a;
    Bitmap b;

    private int a(int i) {
        return (-16777216) | ((((63488 & i) >> 8) << 16) + (((i & 2016) >> 3) << 8) + ((i & 31) << 3));
    }

    private WatchFaceBitmapUtil() {
    }

    public static WatchFaceBitmapUtil getInstance() {
        HwLog.i("WatchFaceBitmapUtil", "getInstance");
        if (c == null) {
            synchronized (WatchFaceBitmapUtil.class) {
                if (c == null) {
                    c = new WatchFaceBitmapUtil();
                }
            }
        }
        return c;
    }

    public void a(String str, String str2, int i, int i2, int i3) {
        if (TextUtils.isEmpty(str)) {
            HwLog.i("WatchFaceBitmapUtil", "createBinFile Failed, bitmapPath fileInputStream null");
            return;
        }
        Bitmap safeDecodeFile = getSafeDecodeFile(CommonUtils.filterFilePath(str));
        if (safeDecodeFile == null) {
            HwLog.i("WatchFaceBitmapUtil", "decode source bitmap file null");
            return;
        }
        if (i2 == 0 || i3 == 0) {
            HwLog.e("WatchFaceBitmapUtil", "dial width or height is 0");
            return;
        }
        int width = safeDecodeFile.getWidth();
        int height = safeDecodeFile.getHeight();
        if (i2 == width && i3 == height) {
            a(str, str2, i);
        } else {
            HwLog.e("WatchFaceBitmapUtil", "bitmap width or height is not dial width or height");
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:42:0x00be, code lost:
    
        if (r3 == 0) goto L61;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v10 */
    /* JADX WARN: Type inference failed for: r3v11 */
    /* JADX WARN: Type inference failed for: r3v14 */
    /* JADX WARN: Type inference failed for: r3v15 */
    /* JADX WARN: Type inference failed for: r3v16 */
    /* JADX WARN: Type inference failed for: r3v17 */
    /* JADX WARN: Type inference failed for: r3v18 */
    /* JADX WARN: Type inference failed for: r3v2 */
    /* JADX WARN: Type inference failed for: r3v3 */
    /* JADX WARN: Type inference failed for: r3v4 */
    /* JADX WARN: Type inference failed for: r3v6 */
    /* JADX WARN: Type inference failed for: r3v7 */
    /* JADX WARN: Type inference failed for: r3v8 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void a(java.lang.String r17, java.lang.String r18, int r19) {
        /*
            r16 = this;
            r1 = r16
            r0 = r19
            java.lang.String r2 = "createBinFile，dataOutputStream.close IOException"
            boolean r3 = android.text.TextUtils.isEmpty(r17)
            java.lang.String r4 = "WatchFaceBitmapUtil"
            if (r3 == 0) goto L14
            java.lang.String r0 = "createBinFile Failed, bitmapPath fileInputStream null"
            com.huawei.watchface.utils.HwLog.i(r4, r0)
            return
        L14:
            r3 = 0
            java.io.DataOutputStream r5 = new java.io.DataOutputStream     // Catch: java.lang.Throwable -> Laf java.lang.Exception -> Lb1 java.io.IOException -> Lb9
            java.io.BufferedOutputStream r6 = new java.io.BufferedOutputStream     // Catch: java.lang.Throwable -> Laf java.lang.Exception -> Lb1 java.io.IOException -> Lb9
            java.io.File r7 = new java.io.File     // Catch: java.lang.Throwable -> Laf java.lang.Exception -> Lb1 java.io.IOException -> Lb9
            java.lang.String r8 = com.huawei.watchface.utils.CommonUtils.filterFilePath(r18)     // Catch: java.lang.Throwable -> Laf java.lang.Exception -> Lb1 java.io.IOException -> Lb9
            r7.<init>(r8)     // Catch: java.lang.Throwable -> Laf java.lang.Exception -> Lb1 java.io.IOException -> Lb9
            r8 = 0
            java.io.FileOutputStream r7 = com.huawei.watchface.utils.FileHelper.openOutputStream(r7, r8)     // Catch: java.lang.Throwable -> Laf java.lang.Exception -> Lb1 java.io.IOException -> Lb9
            r6.<init>(r7)     // Catch: java.lang.Throwable -> Laf java.lang.Exception -> Lb1 java.io.IOException -> Lb9
            r5.<init>(r6)     // Catch: java.lang.Throwable -> Laf java.lang.Exception -> Lb1 java.io.IOException -> Lb9
            r3 = 17699(0x4523, float:2.4802E-41)
            r5.writeShort(r3)     // Catch: java.lang.Throwable -> La9 java.lang.Exception -> Lab java.io.IOException -> Lad
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> La9 java.lang.Exception -> Lab java.io.IOException -> Lad
            java.lang.String r6 = "createBinFile binFileType: "
            r3.<init>(r6)     // Catch: java.lang.Throwable -> La9 java.lang.Exception -> Lab java.io.IOException -> Lad
            r3.append(r0)     // Catch: java.lang.Throwable -> La9 java.lang.Exception -> Lab java.io.IOException -> Lad
            java.lang.String r3 = r3.toString()     // Catch: java.lang.Throwable -> La9 java.lang.Exception -> Lab java.io.IOException -> Lad
            com.huawei.watchface.utils.HwLog.i(r4, r3)     // Catch: java.lang.Throwable -> La9 java.lang.Exception -> Lab java.io.IOException -> Lad
            r3 = 1
            r6 = 2
            if (r0 != r6) goto L4d
            r7 = 26101(0x65f5, float:3.6575E-41)
            r5.writeShort(r7)     // Catch: java.lang.Throwable -> La9 java.lang.Exception -> Lab java.io.IOException -> Lad
            goto L5c
        L4d:
            if (r0 != r3) goto L56
            r7 = 34824(0x8808, float:4.8799E-41)
            r5.writeShort(r7)     // Catch: java.lang.Throwable -> La9 java.lang.Exception -> Lab java.io.IOException -> Lad
            goto L5c
        L56:
            r7 = 34952(0x8888, float:4.8978E-41)
            r5.writeShort(r7)     // Catch: java.lang.Throwable -> La9 java.lang.Exception -> Lab java.io.IOException -> Lad
        L5c:
            java.lang.String r7 = com.huawei.watchface.utils.CommonUtils.filterFilePath(r17)     // Catch: java.lang.Throwable -> La9 java.lang.Exception -> Lab java.io.IOException -> Lad
            android.graphics.Bitmap r8 = getSafeDecodeFile(r7)     // Catch: java.lang.Throwable -> La9 java.lang.Exception -> Lab java.io.IOException -> Lad
            if (r8 != 0) goto L76
            java.lang.String r0 = "decode source bitmap file null"
            com.huawei.watchface.utils.HwLog.i(r4, r0)     // Catch: java.lang.Throwable -> La9 java.lang.Exception -> Lab java.io.IOException -> Lad
            com.huawei.watchface.utils.HwSfpPolicyManagerHelper.setDefaultCeLabel(r18)     // Catch: java.lang.Throwable -> La9 java.lang.Exception -> Lab java.io.IOException -> Lad
            r5.close()     // Catch: java.io.IOException -> L72
            goto L75
        L72:
            com.huawei.watchface.utils.HwLog.i(r4, r2)
        L75:
            return
        L76:
            int r14 = r8.getWidth()     // Catch: java.lang.Throwable -> La9 java.lang.Exception -> Lab java.io.IOException -> Lad
            int r15 = r8.getHeight()     // Catch: java.lang.Throwable -> La9 java.lang.Exception -> Lab java.io.IOException -> Lad
            r1.a(r5, r14)     // Catch: java.lang.Throwable -> La9 java.lang.Exception -> Lab java.io.IOException -> Lad
            r1.a(r5, r15)     // Catch: java.lang.Throwable -> La9 java.lang.Exception -> Lab java.io.IOException -> Lad
            int r7 = r14 * r15
            int[] r7 = new int[r7]     // Catch: java.lang.Throwable -> La9 java.lang.Exception -> Lab java.io.IOException -> Lad
            r10 = 0
            r12 = 0
            r13 = 0
            r9 = r7
            r11 = r14
            r8.getPixels(r9, r10, r11, r12, r13, r14, r15)     // Catch: java.lang.Throwable -> La9 java.lang.Exception -> Lab java.io.IOException -> Lad
            if (r6 != r0) goto L96
            r1.c(r5, r7)     // Catch: java.lang.Throwable -> La9 java.lang.Exception -> Lab java.io.IOException -> Lad
            goto L9f
        L96:
            if (r3 != r0) goto L9c
            r1.b(r5, r7)     // Catch: java.lang.Throwable -> La9 java.lang.Exception -> Lab java.io.IOException -> Lad
            goto L9f
        L9c:
            r1.a(r5, r7)     // Catch: java.lang.Throwable -> La9 java.lang.Exception -> Lab java.io.IOException -> Lad
        L9f:
            r5.flush()     // Catch: java.lang.Throwable -> La9 java.lang.Exception -> Lab java.io.IOException -> Lad
            com.huawei.watchface.utils.HwSfpPolicyManagerHelper.setDefaultCeLabel(r18)     // Catch: java.lang.Throwable -> La9 java.lang.Exception -> Lab java.io.IOException -> Lad
            r5.close()     // Catch: java.io.IOException -> Lc4
            goto Lc7
        La9:
            r0 = move-exception
            goto Lc9
        Lab:
            r3 = r5
            goto Lb1
        Lad:
            r3 = r5
            goto Lb9
        Laf:
            r0 = move-exception
            goto Lc8
        Lb1:
            java.lang.String r0 = "createBinFile, exception"
            com.huawei.watchface.utils.HwLog.i(r4, r0)     // Catch: java.lang.Throwable -> Laf
            if (r3 == 0) goto Lc7
            goto Lc0
        Lb9:
            java.lang.String r0 = "createBinFile, dataOutputStream.flush IOException"
            com.huawei.watchface.utils.HwLog.i(r4, r0)     // Catch: java.lang.Throwable -> Laf
            if (r3 == 0) goto Lc7
        Lc0:
            r3.close()     // Catch: java.io.IOException -> Lc4
            goto Lc7
        Lc4:
            com.huawei.watchface.utils.HwLog.i(r4, r2)
        Lc7:
            return
        Lc8:
            r5 = r3
        Lc9:
            if (r5 == 0) goto Ld2
            r5.close()     // Catch: java.io.IOException -> Lcf
            goto Ld2
        Lcf:
            com.huawei.watchface.utils.HwLog.i(r4, r2)
        Ld2:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.watchface.utils.WatchFaceBitmapUtil.a(java.lang.String, java.lang.String, int):void");
    }

    public boolean a(String str, String str2, String str3, int i, int i2, int i3) {
        DataOutputStream dataOutputStream;
        DataOutputStream dataOutputStream2;
        DataOutputStream dataOutputStream3;
        if (TextUtils.isEmpty(str)) {
            HwLog.i("WatchFaceBitmapUtil", "createBinFile Failed, foregroundBitmapPath fileInputStream null");
            return false;
        }
        if (TextUtils.isEmpty(str2)) {
            HwLog.i("WatchFaceBitmapUtil", "createBinFile Failed, backgroundBitmapPath fileInputStream null");
            return false;
        }
        if (i2 == 0 || i3 == 0) {
            HwLog.e("WatchFaceBitmapUtil", "dial width or height is 0");
            return false;
        }
        String replace = SafeString.replace(str3, WatchFaceConstant.BIN_SUFFIX, "_fg.bin");
        String replace2 = SafeString.replace(str3, WatchFaceConstant.BIN_SUFFIX, "_bg.bin");
        HwLog.i("WatchFaceBitmapUtil", "createBinFile binFileType: " + i);
        DataOutputStream dataOutputStream4 = null;
        try {
            FlavorConfig.safeHwLog("WatchFaceBitmapUtil", "fg path" + str);
            FlavorConfig.safeHwLog("WatchFaceBitmapUtil", "bg path" + str2);
            this.f11197a = getSafeDecodeFile(CommonUtils.filterFilePath(str));
            Bitmap safeDecodeFile = getSafeDecodeFile(CommonUtils.filterFilePath(str2));
            this.b = safeDecodeFile;
            Bitmap bitmap = this.f11197a;
            if (bitmap == null) {
                HwLog.i("WatchFaceBitmapUtil", "decode source foregroundBitmap file null");
                return false;
            }
            if (safeDecodeFile == null) {
                HwLog.i("WatchFaceBitmapUtil", "decode source backgroundBitmap file null");
                return false;
            }
            int width = bitmap.getWidth();
            int height = this.f11197a.getHeight();
            if (i2 != width || height != i3) {
                HwLog.e("WatchFaceBitmapUtil", "foregroundBitmap with or height is not dial with or height");
                return false;
            }
            int width2 = this.b.getWidth();
            int height2 = this.b.getHeight();
            if (i2 == width2 && height2 == i3) {
                dataOutputStream2 = new DataOutputStream(new BufferedOutputStream(FileHelper.openOutputStream(new File(CommonUtils.filterFilePath(str3)), false)));
                try {
                    dataOutputStream = new DataOutputStream(new BufferedOutputStream(FileHelper.openOutputStream(new File(CommonUtils.filterFilePath(replace)), false)));
                    try {
                        try {
                            dataOutputStream3 = new DataOutputStream(new BufferedOutputStream(FileHelper.openOutputStream(new File(CommonUtils.filterFilePath(replace2)), false)));
                        } catch (IOException unused) {
                        }
                    } catch (Throwable th) {
                        th = th;
                    }
                } catch (IOException unused2) {
                    dataOutputStream = null;
                } catch (Throwable th2) {
                    th = th2;
                    dataOutputStream = null;
                }
                try {
                    int[] iArr = new int[width * height];
                    this.f11197a.getPixels(iArr, 0, width, 0, 0, width, height);
                    int[] iArr2 = new int[width2 * height2];
                    this.b.getPixels(iArr2, 0, width2, 0, 0, width2, height2);
                    if (2 == i) {
                        c(dataOutputStream, iArr);
                        c(dataOutputStream3, iArr2);
                    } else if (1 == i) {
                        b(dataOutputStream, iArr);
                        b(dataOutputStream3, iArr2);
                    } else {
                        a(dataOutputStream, iArr);
                        a(dataOutputStream3, iArr2);
                    }
                    int size = dataOutputStream.size();
                    int size2 = dataOutputStream3.size();
                    b(dataOutputStream2, size);
                    b(dataOutputStream2, size2);
                    HwLog.i("WatchFaceBitmapUtil", "createBinFile binFileType: size of fg is" + size + " size of bg is " + size2);
                    dataOutputStream2.writeShort(17699);
                    if (i == 2) {
                        dataOutputStream2.writeShort(26101);
                    } else if (i == 1) {
                        dataOutputStream2.writeShort(34824);
                    } else {
                        dataOutputStream2.writeShort(34952);
                    }
                    a(dataOutputStream2, width);
                    a(dataOutputStream2, height);
                    if (2 == i) {
                        c(dataOutputStream2, iArr);
                    } else if (1 == i) {
                        b(dataOutputStream2, iArr);
                    } else {
                        a(dataOutputStream2, iArr);
                    }
                    dataOutputStream2.writeShort(17699);
                    if (i == 2) {
                        dataOutputStream2.writeShort(26101);
                    } else if (i == 1) {
                        dataOutputStream2.writeShort(34824);
                    } else {
                        dataOutputStream2.writeShort(34952);
                    }
                    a(dataOutputStream2, width2);
                    a(dataOutputStream2, height2);
                    if (2 == i) {
                        c(dataOutputStream2, iArr2);
                    } else if (1 == i) {
                        b(dataOutputStream2, iArr2);
                    } else {
                        a(dataOutputStream2, iArr2);
                    }
                    HwLog.i("WatchFaceBitmapUtil", "createBinFile binFileType: Total count of stream:" + dataOutputStream2.size());
                    dataOutputStream2.flush();
                    dataOutputStream.flush();
                    dataOutputStream3.flush();
                    try {
                        dataOutputStream2.close();
                        dataOutputStream.close();
                        dataOutputStream3.close();
                        return true;
                    } catch (IOException unused3) {
                        HwLog.i("WatchFaceBitmapUtil", "createBinFile，dataOutputStream.close IOException");
                        return true;
                    }
                } catch (IOException unused4) {
                    dataOutputStream4 = dataOutputStream3;
                    HwLog.i("WatchFaceBitmapUtil", "createBinFile, dataOutputStream.flush IOException");
                    if (dataOutputStream2 != null) {
                        try {
                            dataOutputStream2.close();
                        } catch (IOException unused5) {
                            HwLog.i("WatchFaceBitmapUtil", "createBinFile，dataOutputStream.close IOException");
                            return false;
                        }
                    }
                    if (dataOutputStream != null) {
                        dataOutputStream.close();
                    }
                    if (dataOutputStream4 == null) {
                        return false;
                    }
                    dataOutputStream4.close();
                    return false;
                } catch (Throwable th3) {
                    th = th3;
                    dataOutputStream4 = dataOutputStream3;
                    if (dataOutputStream2 != null) {
                        try {
                            dataOutputStream2.close();
                        } catch (IOException unused6) {
                            HwLog.i("WatchFaceBitmapUtil", "createBinFile，dataOutputStream.close IOException");
                            throw th;
                        }
                    }
                    if (dataOutputStream != null) {
                        dataOutputStream.close();
                    }
                    if (dataOutputStream4 != null) {
                        dataOutputStream4.close();
                    }
                    throw th;
                }
            }
            HwLog.e("WatchFaceBitmapUtil", "foregroundBitmap with or height is not dial with or height");
            return false;
        } catch (IOException unused7) {
            dataOutputStream = null;
            dataOutputStream2 = null;
        } catch (Throwable th4) {
            th = th4;
            dataOutputStream = null;
            dataOutputStream2 = null;
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
            b(dataOutputStream, 591751049);
            b(dataOutputStream, iArr[i2]);
            b(dataOutputStream, i);
        } else {
            for (int i3 = 0; i3 < i; i3++) {
                b(dataOutputStream, iArr[i2]);
            }
        }
    }

    private int[] a(DataInputStream dataInputStream, int i) throws IOException {
        int[] iArr = new int[i];
        int i2 = 0;
        while (i2 < i) {
            int b = b(dataInputStream);
            if (b == 591751049) {
                int b2 = b(dataInputStream);
                int b3 = b(dataInputStream);
                for (int i3 = 0; i3 < b3; i3++) {
                    iArr[i2] = b2;
                    i2++;
                }
            } else {
                iArr[i2] = b;
                i2++;
            }
        }
        return iArr;
    }

    private void a(DataOutputStream dataOutputStream, int i) throws IOException {
        dataOutputStream.writeByte(i & 255);
        dataOutputStream.writeByte((i >> 8) & 255);
    }

    private void b(DataOutputStream dataOutputStream, int[] iArr) throws IOException {
        int i = 1;
        for (int i2 = 0; i2 < iArr.length; i2++) {
            if (i2 == iArr.length - 1 || iArr[i2] != iArr[i2 + 1]) {
                b(dataOutputStream, iArr, i, i2);
                i = 1;
            } else {
                i++;
            }
        }
    }

    private void c(DataOutputStream dataOutputStream, int[] iArr) throws IOException {
        int i = 0;
        int i2 = 1;
        while (i < iArr.length) {
            if (i == iArr.length - 1 || iArr[i] != iArr[i + 1]) {
                if (i2 == 1) {
                    c(dataOutputStream, iArr, i2, i);
                    i++;
                } else if (i2 >= 2 && i2 % 2 != 0) {
                    c(dataOutputStream, iArr, i2 - 1, i);
                    i--;
                } else {
                    c(dataOutputStream, iArr, i2, i);
                }
                i2 = 1;
            } else {
                i2++;
            }
            i++;
        }
    }

    private void b(DataOutputStream dataOutputStream, int[] iArr, int i, int i2) throws IOException {
        if (i >= 4 || iArr[i2] == 4548489) {
            c(dataOutputStream, 4548489);
            c(dataOutputStream, iArr[i2]);
            c(dataOutputStream, i);
        } else {
            for (int i3 = 0; i3 < i; i3++) {
                c(dataOutputStream, iArr[i2]);
            }
        }
    }

    private void c(DataOutputStream dataOutputStream, int[] iArr, int i, int i2) throws IOException {
        if (i == 1) {
            d(dataOutputStream, iArr[i2]);
            if (i2 < iArr.length - 1) {
                d(dataOutputStream, iArr[i2 + 1]);
                return;
            } else {
                d(dataOutputStream, 0);
                return;
            }
        }
        if (i < 8) {
            for (int i3 = 0; i3 < i; i3++) {
                d(dataOutputStream, iArr[i2]);
            }
        } else {
            b(dataOutputStream, 591751049);
            d(dataOutputStream, iArr[i2]);
            d(dataOutputStream, iArr[i2]);
            b(dataOutputStream, i / 2);
        }
    }

    private void b(DataOutputStream dataOutputStream, int i) throws IOException {
        dataOutputStream.writeByte(i & 255);
        dataOutputStream.writeByte((i >> 8) & 255);
        dataOutputStream.writeByte((i >> 16) & 255);
        dataOutputStream.writeByte((i >> 24) & 255);
    }

    private void c(DataOutputStream dataOutputStream, int i) throws IOException {
        dataOutputStream.writeByte(i & 255);
        dataOutputStream.writeByte((i >> 8) & 255);
        dataOutputStream.writeByte((i >> 16) & 255);
    }

    private void d(DataOutputStream dataOutputStream, int i) throws IOException {
        a(dataOutputStream, (((i >> 19) & 31) << 11) + (((i >> 10) & 63) << 5) + ((i >> 3) & 31));
    }

    private int a(DataInputStream dataInputStream) throws IOException {
        return ((dataInputStream.readByte() & 255) << 8) | (dataInputStream.readByte() & 255);
    }

    private int b(DataInputStream dataInputStream) throws IOException {
        return ((dataInputStream.readByte() & 255) << 24) | (dataInputStream.readByte() & 255) | ((dataInputStream.readByte() & 255) << 8) | ((dataInputStream.readByte() & 255) << 16);
    }

    private int c(DataInputStream dataInputStream) throws IOException {
        return ((dataInputStream.readByte() & 255) << 16) | (dataInputStream.readByte() & 255) | ((dataInputStream.readByte() & 255) << 8);
    }

    /* JADX WARN: Not initialized variable reg: 3, insn: 0x00c2: MOVE (r1 I:??[OBJECT, ARRAY]) = (r3 I:??[OBJECT, ARRAY]), block:B:73:0x00c1 */
    /* JADX WARN: Removed duplicated region for block: B:24:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void a(java.lang.String r12, java.lang.String r13) {
        /*
            Method dump skipped, instructions count: 306
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.watchface.utils.WatchFaceBitmapUtil.a(java.lang.String, java.lang.String):void");
    }

    private int[] a(DataInputStream dataInputStream, int i, int i2) throws IOException {
        int[] iArr = new int[i2];
        if (i == 1159956488) {
            return c(dataInputStream, i2);
        }
        if (i == 1159947765) {
            return b(dataInputStream, i2);
        }
        return a(dataInputStream, i2);
    }

    private int[] b(DataInputStream dataInputStream, int i) {
        int[] iArr = new int[i];
        int i2 = 0;
        while (i2 < i) {
            try {
                int b = b(dataInputStream);
                if (b == 591751049) {
                    int b2 = b(dataInputStream);
                    int b3 = b(dataInputStream);
                    for (int i3 = 0; i3 < b3 * 2; i3++) {
                        iArr[i2] = a(b2 >> 16);
                        i2++;
                    }
                } else {
                    iArr[i2] = a(b >> 16);
                    iArr[i2 + 1] = a(b & 65535);
                    i2 += 2;
                }
            } catch (IOException e) {
                HwLog.i("WatchFaceBitmapUtil", "read16BitPixels IOException: " + HwLog.printException((Exception) e));
                return iArr;
            }
        }
        HwLog.i("WatchFaceBitmapUtil", "read16BitPixels length: " + i);
        return iArr;
    }

    private int[] c(DataInputStream dataInputStream, int i) throws IOException {
        int[] iArr = new int[i];
        int i2 = 0;
        while (i2 < i) {
            int c2 = c(dataInputStream);
            if (c2 == 4548489) {
                int c3 = c(dataInputStream) | (-16777216);
                int c4 = c(dataInputStream);
                if (i2 + c4 > i) {
                    iArr[i2] = c2 | (-16777216);
                    iArr[i2 + 1] = c3;
                    i2 += 2;
                    iArr[i2] = c4 | (-16777216);
                } else {
                    for (int i3 = 0; i3 < c4; i3++) {
                        iArr[i2] = c3;
                        i2++;
                    }
                }
            } else {
                iArr[i2] = c2 | (-16777216);
            }
            i2++;
        }
        HwLog.i("WatchFaceBitmapUtil", "read24BitPixels length : " + i);
        return iArr;
    }

    private Bitmap a(Bitmap bitmap) {
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

    public static Bitmap getSafeDecodeFile(String str) {
        try {
            return a(str, (BitmapFactory.Options) null);
        } catch (Exception e) {
            HwLog.e("WatchFaceBitmapUtil", "getSafeDecodeFile Exception" + HwLog.printException(e));
            return null;
        } catch (OutOfMemoryError e2) {
            HwLog.e("WatchFaceBitmapUtil", "getSafeDecodeFile OutOfMemoryError" + HwLog.printException((Error) e2));
            return null;
        }
    }

    public static Bitmap a(String str, BitmapFactory.Options options) {
        Bitmap decodeFile;
        try {
            decodeFile = BitmapFactory.decodeFile(str, options);
        } catch (Exception e) {
            HwLog.e("WatchFaceBitmapUtil", "getSafeDecodeFile Exception " + HwLog.printException(e));
            decodeFile = null;
        } catch (OutOfMemoryError e2) {
            HwLog.e("WatchFaceBitmapUtil", "getSafeDecodeFile OutOfMemoryError " + HwLog.printException((Error) e2));
            if (options == null) {
                options = new BitmapFactory.Options();
            }
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(str, options);
            int i = options.outWidth;
            int i2 = options.outHeight;
            options.inJustDecodeBounds = false;
            DisplayMetrics displayMetrics = Environment.getApplicationContext().getResources().getDisplayMetrics();
            options.inSampleSize = a(i, i2, displayMetrics.widthPixels, displayMetrics.heightPixels);
            decodeFile = BitmapFactory.decodeFile(str, options);
        }
        HwSfpPolicyManagerHelper.setDefaultCeLabel(str);
        return decodeFile;
    }

    private static int a(int i, int i2, int i3, int i4) {
        int i5 = 1;
        while (true) {
            if (i2 / i5 <= i4 && i / i5 <= i3) {
                return i5;
            }
            i5 *= 2;
        }
    }

    public static Bitmap a(Bitmap bitmap, int i) {
        if (bitmap == null) {
            return bitmap;
        }
        Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        paint.setColorFilter(new PorterDuffColorFilter(i, PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, paint);
        return createBitmap;
    }

    public static Bitmap b(Bitmap bitmap, int i) {
        if (bitmap == null || bitmap.isRecycled()) {
            return null;
        }
        if (i == 0) {
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
    /* JADX WARN: Type inference failed for: r0v0 */
    /* JADX WARN: Type inference failed for: r0v1 */
    /* JADX WARN: Type inference failed for: r0v13 */
    /* JADX WARN: Type inference failed for: r0v6 */
    /* JADX WARN: Type inference failed for: r0v7 */
    /* JADX WARN: Type inference failed for: r3v0, types: [android.graphics.Bitmap] */
    /* JADX WARN: Type inference failed for: r6v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r6v10 */
    /* JADX WARN: Type inference failed for: r6v11 */
    /* JADX WARN: Type inference failed for: r6v3 */
    /* JADX WARN: Type inference failed for: r6v7, types: [java.io.Closeable] */
    public static void saveBitmapToFile(Bitmap bitmap, Bitmap.CompressFormat compressFormat, String str, String str2) {
        FileOutputStream fileOutputStream;
        FileOutputStream fileOutputStream2;
        File file;
        BufferedOutputStream bufferedOutputStream;
        if (bitmap == 0) {
            return;
        }
        BufferedOutputStream bufferedOutputStream2 = 0;
        r0 = null;
        BufferedOutputStream bufferedOutputStream3 = null;
        BufferedOutputStream bufferedOutputStream4 = null;
        try {
            try {
                File file2 = PversionSdUtils.getFile(str);
                if (!file2.exists()) {
                    CommonUtils.a(file2);
                }
                file = PversionSdUtils.getFile(str + ((String) str2));
                if (file.exists()) {
                    CommonUtils.b(file);
                }
                fileOutputStream2 = PversionSdUtils.a(file);
                try {
                    bufferedOutputStream = new BufferedOutputStream(fileOutputStream2);
                    bufferedOutputStream2 = 100;
                } catch (IOException e) {
                    e = e;
                }
            } catch (Throwable th) {
                th = th;
                fileOutputStream = str2;
                bufferedOutputStream4 = bufferedOutputStream2;
            }
            try {
                bitmap.compress(compressFormat, 100, bufferedOutputStream);
                bufferedOutputStream.flush();
                HwSfpPolicyManagerHelper.setDefaultCeLabel(file);
                cq.a(bufferedOutputStream);
                str2 = fileOutputStream2;
            } catch (IOException e2) {
                e = e2;
                bufferedOutputStream3 = bufferedOutputStream;
                HwLog.e("WatchFaceBitmapUtil", "saveBitmapToFile IOException " + HwLog.printException((Exception) e));
                cq.a(bufferedOutputStream3);
                bufferedOutputStream2 = bufferedOutputStream3;
                str2 = fileOutputStream2;
                cq.a(str2);
            } catch (Throwable th2) {
                th = th2;
                bufferedOutputStream4 = bufferedOutputStream;
                fileOutputStream = fileOutputStream2;
                cq.a(bufferedOutputStream4);
                cq.a(fileOutputStream);
                throw th;
            }
        } catch (IOException e3) {
            e = e3;
            fileOutputStream2 = null;
        } catch (Throwable th3) {
            th = th3;
            fileOutputStream = null;
        }
        cq.a(str2);
    }

    public static Bitmap getVideoFrameAtTimeOne(String str) {
        Bitmap bitmap = null;
        if (TextUtils.isEmpty(str)) {
            HwLog.w("WatchFaceBitmapUtil", "getVideoFrameAtTimeOne -- videoPath is empty");
            return null;
        }
        File file = PversionSdUtils.getFile(str);
        if (file == null || !file.exists()) {
            HwLog.w("WatchFaceBitmapUtil", "getVideoFrameAtTimeOne -- video not exit");
            return null;
        }
        try {
            MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
            mediaMetadataRetriever.setDataSource(str);
            bitmap = mediaMetadataRetriever.getFrameAtTime(1L, 2);
            mediaMetadataRetriever.release();
            return bitmap;
        } catch (IOException e) {
            HwLog.e("WatchFaceBitmapUtil", "getVideoFrameAtTimeOne -- IOException:" + HwLog.printException((Exception) e));
            return bitmap;
        } catch (IllegalArgumentException e2) {
            HwLog.e("WatchFaceBitmapUtil", "getVideoFrameAtTimeOne -- IllegalArgumentException:" + HwLog.printException((Exception) e2));
            return bitmap;
        } catch (RuntimeException e3) {
            HwLog.e("WatchFaceBitmapUtil", "getVideoFrameAtTimeOne -- RuntimeException:" + HwLog.printException((Exception) e3));
            return bitmap;
        } catch (Exception e4) {
            HwLog.e("WatchFaceBitmapUtil", "getVideoFrameAtTimeOne -- Exception:" + HwLog.printException(e4));
            return bitmap;
        }
    }

    public void cropPngToRound(String str) {
        try {
            Bitmap decodeStream = BitmapFactory.decodeStream(new FileInputStream(str));
            Bitmap createBitmap = Bitmap.createBitmap(decodeStream.getWidth(), decodeStream.getHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(createBitmap);
            Path path = new Path();
            path.addCircle(decodeStream.getWidth() / 2.0f, decodeStream.getHeight() / 2.0f, decodeStream.getWidth() / 2.0f, Path.Direction.CW);
            canvas.clipPath(path);
            Paint paint = new Paint();
            paint.setAntiAlias(true);
            canvas.drawBitmap(decodeStream, 0.0f, 0.0f, paint);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(new File(str)));
            createBitmap.compress(Bitmap.CompressFormat.PNG, 100, bufferedOutputStream);
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
        } catch (IOException unused) {
            HwLog.e("WatchFaceBitmapUtil", "cropPngToRound Exception");
        } catch (Exception unused2) {
            HwLog.e("WatchFaceBitmapUtil", "cropPngToRound Exception");
        } catch (OutOfMemoryError unused3) {
            HwLog.e("WatchFaceBitmapUtil", "cropPngToRound OutOfMemoryError");
        }
    }

    public void a(String str, int i, int i2, int i3) throws IOException {
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
                f = 24.0f;
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
