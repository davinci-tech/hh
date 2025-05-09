package defpackage;

import com.huawei.devicesdk.entity.CharacterOperationType;
import com.huawei.devicesdk.entity.DeviceInfo;
import com.huawei.devicesdk.entity.SimpleDataHead;
import com.huawei.devicesdk.strategy.BaseSendStrategyInitialD;
import health.compact.a.LogUtil;
import java.nio.ByteBuffer;
import java.util.ArrayList;

/* loaded from: classes3.dex */
public class bko extends BaseSendStrategyInitialD {
    @Override // com.huawei.devicesdk.strategy.SendStrategy
    public ArrayList<bim> getSendFrames(bir birVar, DeviceInfo deviceInfo) {
        ArrayList<bim> arrayList = new ArrayList<>(16);
        if (deviceInfo == null) {
            LogUtil.a("SendStrategyInitialFragment", "SendStrategyInitialFragment getSendFrames deviceInfo is null.");
        }
        if (birVar == null) {
            LogUtil.a("SendStrategyInitialFragment", "message is null");
            return arrayList;
        }
        if (birVar.c() != CharacterOperationType.WRITE) {
            LogUtil.c("SendStrategyInitialFragment", "return empty data frames. option type is ", birVar.c());
            bim bimVar = new bim();
            bimVar.b(birVar.j());
            bimVar.c(birVar.b());
            bimVar.b(birVar.c());
            bimVar.a(birVar.f());
            arrayList.add(bimVar);
            return arrayList;
        }
        byte[] e = birVar.e();
        if (!isInputDataValid(e)) {
            LogUtil.a("SendStrategyInitialFragment", "getSendFrame error. input data is invalid");
            return arrayList;
        }
        LogUtil.c("SendStrategyInitialFragment", "isEncrypt: ", Boolean.valueOf(birVar.k()), " dataHead: ", Byte.valueOf(birVar.d()));
        if (birVar.k() && birVar.d() == SimpleDataHead.INVALID.getDataHead() && deviceInfo != null) {
            byte[] d = d(e);
            e = a(new bgu().encrypt(d, deviceInfo.getDeviceMac()), e);
            LogUtil.c("SendStrategyInitialFragment", "content: ", blt.b(blq.d(d)), " encryptContent: ", blt.b(blq.d(d)), " combineFrameData: ", blt.b(blq.d(e)));
        }
        return constructBluetoothFrame(birVar.k(), e, birVar);
    }

    private byte[] a(byte[] bArr, byte[] bArr2) {
        ByteBuffer allocate = ByteBuffer.allocate(bArr.length + 2);
        allocate.put(bArr2, 0, 2);
        allocate.put(bArr);
        return allocate.array();
    }

    private byte[] d(byte[] bArr) {
        int length = bArr.length - 2;
        ByteBuffer allocate = ByteBuffer.allocate(length);
        allocate.put(bArr, 2, length);
        return allocate.array();
    }

    @Override // com.huawei.devicesdk.strategy.BaseSendStrategyInitialD
    public boolean isInputDataValid(byte[] bArr) {
        return super.isInputDataValid(bArr) && bArr.length >= 2;
    }
}
