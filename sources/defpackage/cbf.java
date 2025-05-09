package defpackage;

import com.huawei.health.basesport.wearengine.SportBaseEngineManager;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes3.dex */
public class cbf extends cbc {
    public cbf(SportBaseEngineManager sportBaseEngineManager, String str, int i, int i2, IBaseResponseCallback iBaseResponseCallback) {
        super(sportBaseEngineManager, str, i, i2, iBaseResponseCallback);
    }

    @Override // defpackage.cbc, com.huawei.health.basesport.wearengine.DataReceiver
    public void onDataReceived(cba cbaVar, int i, byte[] bArr) {
        if (cbaVar == null) {
            LogUtil.h(this.c, "SendMsgDataReceiver onDataReceived msg head is null, pls check.");
            return;
        }
        if (cbaVar.d() != this.d || cbaVar.a() != this.e) {
            LogUtil.h(this.c, "SendMsgDataReceiver onDataReceived msg not match, this type is : ", Integer.valueOf(this.d), " received is: ", Integer.valueOf(cbaVar.d()), " this msg id is:", Integer.valueOf(this.e), " received msg id is: ", Integer.valueOf(cbaVar.a()));
            return;
        }
        SportBaseEngineManager sportBaseEngineManager = this.b.get();
        if (sportBaseEngineManager == null) {
            LogUtil.h(this.c, "SendMsgDataReceiver onDataReceived HiWearEngineFitnessManager is null, pls check");
            return;
        }
        LogUtil.h(this.c, "SendMsgDataReceiver onDataReceived type is: ", Integer.valueOf(this.d), " session id is: ", Integer.valueOf(this.e), " dataContents length error");
        if (bArr != null && bArr.length >= 8) {
            LogUtil.a(this.c, "TWO_INT_BYTE_SIZE dataContents is: ", cvx.d(bArr));
            ByteBuffer wrap = ByteBuffer.wrap(bArr);
            wrap.order(ByteOrder.LITTLE_ENDIAN);
            int i2 = wrap.getInt();
            this.f590a.d(i2 != 1 ? 0 : 1, new int[]{i2, wrap.getInt()});
        } else if (bArr != null && bArr.length >= 4) {
            LogUtil.a(this.c, "INT_BYTE_SIZE dataContents is: ", cvx.d(bArr));
            ByteBuffer wrap2 = ByteBuffer.wrap(bArr);
            wrap2.order(ByteOrder.LITTLE_ENDIAN);
            int i3 = wrap2.getInt();
            this.f590a.d(i3 != 1 ? 0 : 1, Integer.valueOf(i3));
        }
        sportBaseEngineManager.unregisterCallback(this, this.d);
    }
}
