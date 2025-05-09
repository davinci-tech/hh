package com.huawei.watchface;

import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.constant.WatchFaceConstant;
import com.huawei.watchface.utils.ArrayUtils;
import com.huawei.watchface.utils.CommonUtils;
import com.huawei.watchface.utils.HwLog;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

/* loaded from: classes7.dex */
public class cy {
    public static byte[] a(String str, String str2, cw... cwVarArr) throws IOException {
        if (ArrayUtils.isEmpty(cwVarArr)) {
            HwLog.w("FileMergeUtil", "mergeFile file paths isEmpty");
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ByteBuffer allocate = ByteBuffer.allocate((cwVarArr.length * 30) + 4);
        int length = cwVarArr.length;
        allocate.put((byte) 1);
        allocate.put((byte) length);
        allocate.putShort((short) 0);
        int length2 = cwVarArr.length;
        int i = 0;
        for (cw cwVar : cwVarArr) {
            allocate.putInt((length2 * 30) + 5 + i);
            allocate.putInt(cwVar.b().length);
            allocate.put(!cwVar.a().contains(WatchFaceConstant.BIN_SUFFIX) ? (byte) 1 : (byte) 0);
            byte[] a2 = a(str, 11);
            byte[] a3 = a(str2, 10);
            allocate.put(a2);
            allocate.put(a3);
            i += cwVar.b().length;
        }
        byte[] a4 = CommonUtils.a(allocate.array());
        if (ArrayUtils.a(a4)) {
            HwLog.w("FileMergeUtil", "mergeFile file hashArr isEmpty");
            return null;
        }
        byte b = a4[a4.length - 1];
        byteArrayOutputStream.write(allocate.array());
        byteArrayOutputStream.write(b);
        for (cw cwVar2 : cwVarArr) {
            byteArrayOutputStream.write(cwVar2.b());
        }
        return byteArrayOutputStream.toByteArray();
    }

    public static byte[] a(String str, int i) {
        byte[] bArr = new byte[i];
        int length = str.length();
        for (int i2 = 0; i2 < i; i2++) {
            if (i2 >= length) {
                bArr[i2] = 0;
            } else {
                bArr[i2] = (byte) str.charAt(i2);
            }
        }
        return bArr;
    }
}
