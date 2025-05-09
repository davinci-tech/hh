package defpackage;

import com.huawei.health.basesport.wearengine.DataReceiver;
import com.huawei.health.basesport.wearengine.SportBaseEngineManager;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import java.lang.ref.WeakReference;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes3.dex */
public class cbc implements DataReceiver {

    /* renamed from: a, reason: collision with root package name */
    protected IBaseResponseCallback f590a;
    protected WeakReference<SportBaseEngineManager> b;
    protected String c;
    protected int d;
    protected int e;

    public cbc(SportBaseEngineManager sportBaseEngineManager, String str, int i, int i2, IBaseResponseCallback iBaseResponseCallback) {
        this.b = new WeakReference<>(sportBaseEngineManager);
        this.c = str;
        this.d = i;
        this.e = i2;
        this.f590a = iBaseResponseCallback;
    }

    @Override // com.huawei.health.basesport.wearengine.DataReceiver
    public boolean isMatch(int i) {
        return this.e == i;
    }

    @Override // com.huawei.health.basesport.wearengine.DataReceiver
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
        if (bArr != null && bArr.length >= 4) {
            LogUtil.a(this.c, "SendMsgDataReceiver onDataReceived type is: ", Integer.valueOf(this.d), " session id is: ", Integer.valueOf(this.e), " dataContents is: ", cvx.d(bArr));
            ByteBuffer wrap = ByteBuffer.wrap(bArr);
            wrap.order(ByteOrder.LITTLE_ENDIAN);
            int i2 = wrap.getInt();
            this.f590a.d(i2 != 1 ? 0 : 1, Integer.valueOf(i2));
        } else {
            LogUtil.h(this.c, "SendMsgDataReceiver onDataReceived type is: ", Integer.valueOf(this.d), " session id is: ", Integer.valueOf(this.e), " dataContents length error");
        }
        sportBaseEngineManager.unregisterCallback(this, this.d);
    }

    public int a() {
        return this.d;
    }
}
