package com.huawei.hwbtsdk.btmanager.btlinklayerwrap;

import android.content.Context;
import defpackage.iyq;
import defpackage.izj;
import health.compact.a.LogUtil;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public abstract class BTDeviceDataWrapBase {
    public abstract List<izj> parseResponsePacket(int i, byte[] bArr);

    public abstract ArrayList<byte[]> wrapCommandPackets(int i, byte[] bArr);

    public String spliceMTUPackage(int i, byte[] bArr) {
        if (bArr != null) {
            return "";
        }
        LogUtil.c("BTDeviceDataWrapBase", "dataContent is null");
        return "";
    }

    public void resetPackageInfo() {
        LogUtil.c("BTDeviceDataWrapBase", "resetPackageInfo");
    }

    public void setDeviceMaxFrameSize(int i) {
        LogUtil.c("BTDeviceDataWrapBase", "setDeviceMaxFrameSize");
    }

    protected void copyByHead(int i, ByteBuffer byteBuffer, ArrayList<byte[]> arrayList, Context context, byte[]... bArr) {
        if (byteBuffer == null || arrayList == null || context == null || bArr == null) {
            return;
        }
        byte[] bArr2 = bArr[0];
        byte[] bArr3 = bArr[1];
        System.arraycopy(bArr2, 0, bArr3, i, bArr2.length);
        byteBuffer.put(iyq.b(context).a(bArr3));
        byteBuffer.flip();
        arrayList.add(byteBuffer.array());
    }
}
