package com.huawei.devicesdk.strategy;

import com.google.flatbuffers.reflection.BaseType;
import com.huawei.devicesdk.callback.MessageReceiveCallback;
import com.huawei.devicesdk.entity.DeviceInfo;
import com.huawei.devicesdk.entity.SimpleDataHead;
import defpackage.bgu;
import defpackage.bil;
import defpackage.bim;
import defpackage.bir;
import defpackage.biu;
import defpackage.bja;
import defpackage.bje;
import defpackage.bjg;
import defpackage.bjh;
import defpackage.bkv;
import defpackage.blf;
import defpackage.blt;
import health.compact.a.LogUtil;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public abstract class BaseSendStrategyInitialD extends SendStrategy {
    private static final int DEFAULT_SINGLE_PACKAGE_INFO_LENGTH = 5;
    private static final int HIGH_4BIT = 240;
    private static final int INDEX_DATA_HEAD = 0;
    private static final int INDEX_PACKAGE_INFO = 2;
    private static final int INDEX_PACKAGE_LENGTH = 1;
    private static final byte LEN_PACKAGE_CHECK_CODE = 2;
    private static final byte LEN_PACKAGE_LENGTH = 1;
    private static final int LOW_4BIT = 15;
    private static final int MAX_SINGLE_PACKAGE_DATA_LENGTH = 15;
    private static final int PACKAGE_SEND_INTERVAL = 10;
    private static final String TAG = "BaseSendStrategyInitialD";
    private static final int TOTAL_PACKAGE_BIT_COUNT = 4;

    private byte getPackageInfo(int i, int i2) {
        return (byte) ((((i - 1) & 15) << 4) | (i2 & 15));
    }

    @Override // com.huawei.devicesdk.strategy.SendStrategy
    public void destroy(String str) {
    }

    @Override // com.huawei.devicesdk.strategy.SendStrategy
    public void parseReceiveFrames(DeviceInfo deviceInfo, biu biuVar, MessageReceiveCallback messageReceiveCallback) {
        if (deviceInfo == null || biuVar == null || messageReceiveCallback == null) {
            LogUtil.a(TAG, "parseReceiveFrames device frame or callback parameter exception.");
            return;
        }
        byte[] a2 = biuVar.a();
        if (!isResponseDataValid(a2)) {
            LogUtil.e(TAG, "response data is invalid");
            messageReceiveCallback.onDataReceived(deviceInfo, getReceiveFrame(deviceInfo, biuVar.b()), 0);
        }
        bje.c().a(deviceInfo.getDeviceMac(), bjg.e());
        bjh parse = parse(a2);
        boolean isFrameReceiveFinish = isFrameReceiveFinish(parse);
        boolean isEncryptedDataHead = SimpleDataHead.isEncryptedDataHead(parse.d().getDataHead());
        bjg.e().e(deviceInfo.getDeviceMac(), biuVar.b(), parse.a(), isEncryptedDataHead);
        if (!isFrameReceiveFinish) {
            LogUtil.c(TAG, "parseReceiveFrames is not finish.");
        } else {
            messageReceiveCallback.onDataReceived(deviceInfo, getReceiveFrame(deviceInfo, biuVar.b()), 0);
        }
    }

    public boolean isInputDataValid(byte[] bArr) {
        if (bArr != null) {
            return true;
        }
        LogUtil.e(TAG, "input data is empty.");
        return false;
    }

    protected ArrayList<bim> constructBluetoothFrame(boolean z, byte[] bArr, bir birVar) {
        if (bArr == null || birVar == null) {
            LogUtil.a(TAG, "constructBluetoothFrame content or message exception.");
            return new ArrayList<>(0);
        }
        ArrayList<bim> arrayList = new ArrayList<>();
        List<byte[]> separateFramePackages = separateFramePackages((z ? SimpleDataHead.DC : SimpleDataHead.DB).getDataHead(), bArr);
        ArrayList arrayList2 = new ArrayList();
        for (byte[] bArr2 : separateFramePackages) {
            bil bilVar = new bil();
            bilVar.b(bArr2);
            bilVar.e(10);
            arrayList2.add(bilVar);
        }
        arrayList.add(bkv.c(birVar, arrayList2));
        return arrayList;
    }

    private List<byte[]> separateFramePackages(byte b, byte[] bArr) {
        ArrayList arrayList = new ArrayList();
        List<byte[]> separateToGroup = separateToGroup(bArr, 15);
        int size = separateToGroup.size();
        int i = 0;
        for (byte[] bArr2 : separateToGroup) {
            int length = bArr2.length;
            ByteBuffer allocate = ByteBuffer.allocate(length + 5);
            allocate.put(b);
            allocate.put((byte) (length + 3));
            allocate.put(getPackageInfo(size, i));
            allocate.put(bArr2, 0, length);
            byte[] checkCode = getCheckCode(allocate.array());
            allocate.put(checkCode[1]);
            allocate.put(checkCode[0]);
            arrayList.add(allocate.array());
            i++;
        }
        return arrayList;
    }

    private List<byte[]> separateToGroup(byte[] bArr, int i) {
        ArrayList arrayList = new ArrayList();
        if (i == 0 || bArr.length == 0) {
            arrayList.add(bArr);
            return arrayList;
        }
        int length = bArr.length / i;
        int i2 = bArr.length % i != 0 ? 1 : 0;
        int i3 = 0;
        while (i3 < length + i2) {
            int i4 = i3 * i;
            i3++;
            int min = Math.min(i3 * i, bArr.length) - i4;
            byte[] bArr2 = new byte[min];
            System.arraycopy(bArr, i4, bArr2, 0, min);
            arrayList.add(bArr2);
        }
        return arrayList;
    }

    private byte[] getCheckCode(byte[] bArr) {
        blt.d(TAG, bArr, "getCheckCode data: ");
        return blf.b(bArr);
    }

    private biu getReceiveFrame(DeviceInfo deviceInfo, String str) {
        biu biuVar = new biu();
        biuVar.a(str);
        bja b = bjg.e().b(deviceInfo.getDeviceMac(), str, true);
        byte[] a2 = b.a();
        if (b.c()) {
            a2 = new bgu().decrypt(a2, deviceInfo.getDeviceMac());
        }
        blt.d(TAG, a2, "uuid is " + str + "On Received Frame: ");
        biuVar.d(a2);
        return biuVar;
    }

    private boolean isResponseDataValid(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            LogUtil.e(TAG, "[isDataFrameValid]input data is empty.");
            return false;
        }
        if (SimpleDataHead.get(bArr[0]) == SimpleDataHead.INVALID) {
            LogUtil.e(TAG, "[isDataFrameValid]data head is not expected. data head is null.");
            return false;
        }
        if (bArr.length >= 5) {
            return true;
        }
        LogUtil.e(TAG, "[isDataFrameValid]data frame length is less than min data length.");
        return false;
    }

    private bjh parse(byte[] bArr) {
        bjh bjhVar = new bjh();
        try {
            bjhVar.c(SimpleDataHead.get(bArr[0]));
            byte b = bArr[1];
            bjhVar.a(b);
            bjhVar.c(bArr[2]);
            int i = b - 2;
            int i2 = b - 3;
            byte[] bArr2 = new byte[i2];
            System.arraycopy(bArr, 3, bArr2, 0, i2);
            bjhVar.c(bArr2);
            byte[] bArr3 = new byte[2];
            System.arraycopy(bArr, i, bArr3, 0, 2);
            bjhVar.d(bArr3);
        } catch (ArrayIndexOutOfBoundsException unused) {
            LogUtil.e(TAG, "parse ArrayIndexOutOfBoundsException");
        }
        return bjhVar;
    }

    private boolean isFrameReceiveFinish(bjh bjhVar) {
        byte e = bjhVar.e();
        byte b = (byte) ((e & 240) >> 4);
        return b == 0 || b == ((byte) (e & BaseType.Obj));
    }
}
