package defpackage;

import android.content.Context;
import com.huawei.ads.adsrec.e0;
import com.huawei.ads.adsrec.k;
import com.huawei.ads.adsrec.k0;
import com.huawei.ads.adsrec.s;
import java.util.List;
import java.util.concurrent.Callable;

/* loaded from: classes2.dex */
public class vv extends k {
    /* JADX WARN: Removed duplicated region for block: B:12:0x0042 A[Catch: all -> 0x0048, TRY_LEAVE, TryCatch #1 {all -> 0x0048, blocks: (B:3:0x001a, B:10:0x003e, B:12:0x0042), top: B:2:0x001a }] */
    @Override // com.huawei.ads.adsrec.r0
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public <T> defpackage.vh a(defpackage.vt r5, T r6) {
        /*
            r4 = this;
            java.lang.String r0 = "recall"
            java.lang.String r1 = "CRFS"
            com.huawei.openplatform.abl.log.HiAdLog.i(r1, r0)
            vv$d r0 = new vv$d
            r0.<init>(r5, r6)
            java.util.concurrent.Future r0 = com.huawei.ads.fund.util.SyncExec.callWithFuture(r0)
            vv$a r2 = new vv$a
            r2.<init>(r5, r6)
            java.util.concurrent.Future r6 = com.huawei.ads.fund.util.SyncExec.callWithFuture(r2)
            r2 = 0
            java.lang.Object r0 = r0.get()     // Catch: java.lang.Throwable -> L48
            vh r0 = (defpackage.vh) r0     // Catch: java.lang.Throwable -> L48
            if (r0 != 0) goto L2c
            java.lang.String r5 = "no ad response"
            com.huawei.openplatform.abl.log.HiAdLog.i(r1, r5)     // Catch: java.lang.Throwable -> L46
        L27:
            java.lang.Object r5 = r6.get()     // Catch: java.lang.Throwable -> L46
            goto L3b
        L2c:
            java.util.List r2 = r0.j()     // Catch: java.lang.Throwable -> L46
            boolean r3 = com.huawei.ads.fund.util.ListUtil.isEmpty(r2)     // Catch: java.lang.Throwable -> L46
            if (r3 != 0) goto L27
            r4.a(r5, r2, r0)     // Catch: java.lang.Throwable -> L46
            r2 = r0
            goto L3e
        L3b:
            vh r5 = (defpackage.vh) r5     // Catch: java.lang.Throwable -> L46
            r2 = r5
        L3e:
            com.huawei.ads.adsrec.e0 r5 = r4.b     // Catch: java.lang.Throwable -> L48
            if (r5 == 0) goto L45
            r5.a(r2)     // Catch: java.lang.Throwable -> L48
        L45:
            return r2
        L46:
            r5 = move-exception
            goto L4a
        L48:
            r5 = move-exception
            r0 = r2
        L4a:
            java.lang.Class r5 = r5.getClass()
            java.lang.String r5 = r5.getSimpleName()
            java.lang.Object[] r5 = new java.lang.Object[]{r5}
            java.lang.String r6 = "recall with future error: %s"
            com.huawei.openplatform.abl.log.HiAdLog.w(r1, r6, r5)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.vv.a(vt, java.lang.Object):vh");
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x0029  */
    @Override // com.huawei.ads.adsrec.r0
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public <T> defpackage.vh a(defpackage.vt r3, defpackage.vh r4) {
        /*
            r2 = this;
            com.huawei.ads.adsrec.s r0 = new com.huawei.ads.adsrec.s
            android.content.Context r1 = r2.f1677a
            r0.<init>(r1)
            r0.a(r3, r4)
            if (r4 == 0) goto L1a
            java.util.List r0 = r4.j()
            boolean r1 = com.huawei.ads.fund.util.ListUtil.isEmpty(r0)
            if (r1 != 0) goto L21
            r2.a(r3, r0, r4)
            goto L25
        L1a:
            java.lang.String r0 = "CRFS"
            java.lang.String r1 = "no ad response"
            com.huawei.openplatform.abl.log.HiAdLog.i(r0, r1)
        L21:
            vh r4 = r2.b(r3, r4)
        L25:
            com.huawei.ads.adsrec.e0 r3 = r2.b
            if (r3 == 0) goto L2c
            r3.a(r4)
        L2c:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.vv.a(vt, vh):vh");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public <T> vh d(vt vtVar, T t) {
        return new k0(this.f1677a).a(vtVar, (vt) t);
    }

    private vh b(vt vtVar, vh vhVar) {
        return new k0(this.f1677a).a(vtVar, vhVar);
    }

    class a implements Callable<vh> {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ Object f17719a;
        final /* synthetic */ vt c;

        @Override // java.util.concurrent.Callable
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public vh call() {
            return vv.this.d(this.c, this.f17719a);
        }

        a(vt vtVar, Object obj) {
            this.c = vtVar;
            this.f17719a = obj;
        }
    }

    class d implements Callable<vh> {
        final /* synthetic */ Object c;
        final /* synthetic */ vt e;

        @Override // java.util.concurrent.Callable
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public vh call() {
            return new s(vv.this.f1677a).a(this.e, (vt) this.c);
        }

        d(vt vtVar, Object obj) {
            this.e = vtVar;
            this.c = obj;
        }
    }

    private void a(vt vtVar, List<vg> list, vh vhVar) {
        for (vg vgVar : list) {
            if (vgVar.g()) {
                vgVar.h();
                vg c = wd.c(this.f1677a, vtVar.a(), vgVar.h(), vtVar.h());
                if (c != null) {
                    vgVar.a(c);
                }
            }
            if (!vgVar.g()) {
                vhVar.b(200);
            }
        }
    }

    public vv(Context context, e0 e0Var) {
        super(context, e0Var);
    }
}
