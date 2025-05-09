package defpackage;

import android.content.Context;
import com.huawei.ads.adsrec.e0;
import com.huawei.ads.adsrec.k;
import com.huawei.ads.adsrec.k0;
import com.huawei.ads.adsrec.s;
import com.huawei.ads.fund.util.SyncExec;
import com.huawei.openplatform.abl.log.HiAdLog;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/* loaded from: classes2.dex */
public class vn extends k {
    @Override // com.huawei.ads.adsrec.r0
    public <T> vh a(vt vtVar, vh vhVar) {
        return vhVar;
    }

    @Override // com.huawei.ads.adsrec.r0
    public <T> vh a(vt vtVar, T t) {
        vh vhVar;
        HiAdLog.i("LRFS", "recall");
        Future callWithFuture = SyncExec.callWithFuture(new a(vtVar, t));
        Future callWithFuture2 = SyncExec.callWithFuture(new e(vtVar, t));
        try {
            vhVar = (vh) callWithFuture.get();
            try {
                e0 e0Var = this.b;
                if (e0Var != null) {
                    e0Var.a(vhVar);
                }
                boolean b = b(vhVar);
                HiAdLog.i("LRFS", "ad rsp empty: %s", Boolean.valueOf(b));
                return b ? (vh) callWithFuture2.get() : vhVar;
            } catch (Throwable th) {
                th = th;
                HiAdLog.w("LRFS", "recall with future error: %s", th.getClass().getSimpleName());
                return vhVar;
            }
        } catch (Throwable th2) {
            th = th2;
            vhVar = null;
        }
    }

    class a implements Callable<vh> {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ Object f17716a;
        final /* synthetic */ vt b;

        @Override // java.util.concurrent.Callable
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public vh call() {
            return new k0(vn.this.f1677a).a(this.b, (vt) this.f17716a);
        }

        a(vt vtVar, Object obj) {
            this.b = vtVar;
            this.f17716a = obj;
        }
    }

    class e implements Callable<vh> {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ Object f17717a;
        final /* synthetic */ vt e;

        @Override // java.util.concurrent.Callable
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public vh call() {
            return new s(vn.this.f1677a).a(this.e, (vt) this.f17717a);
        }

        e(vt vtVar, Object obj) {
            this.e = vtVar;
            this.f17717a = obj;
        }
    }

    private boolean b(vh vhVar) {
        if (vhVar == null) {
            return true;
        }
        return vhVar.g();
    }

    public vn(Context context, e0 e0Var) {
        super(context, e0Var);
    }
}
