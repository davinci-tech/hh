package defpackage;

import android.os.Handler;
import android.os.Message;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes7.dex */
public class rdl implements IBaseResponseCallback {
    private static final Object d = new Object();

    /* renamed from: a, reason: collision with root package name */
    private int f16714a;
    private int[] b;
    private List<Object> c;
    private Handler e;

    public rdl(Handler handler, int i) {
        synchronized (d) {
            this.f16714a = i;
        }
        this.e = handler;
        this.c = new ArrayList(this.f16714a);
        this.b = new int[this.f16714a];
        for (int i2 = 0; i2 < this.f16714a; i2++) {
            this.b[i2] = -1;
        }
    }

    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
    public void onResponse(int i, Object obj) {
        synchronized (d) {
            this.f16714a--;
            this.c.add(obj);
            int[] iArr = this.b;
            int i2 = this.f16714a;
            iArr[i2] = i;
            if (i2 == 0) {
                Message obtainMessage = this.e.obtainMessage();
                obtainMessage.what = 102;
                obtainMessage.obj = this.c;
                this.e.sendMessage(obtainMessage);
            }
        }
    }
}
