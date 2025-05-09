package defpackage;

import com.huawei.openplatform.abl.log.a;
import com.huawei.openplatform.abl.log.i;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes5.dex */
public class lsw extends a {
    private final i b;
    private final Executor d = new ThreadPoolExecutor(0, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue(), new ltb("FileLog"));

    @Override // com.huawei.openplatform.abl.log.i
    public void a(lta ltaVar, int i, String str) {
        this.d.execute(new b(ltaVar, i, str));
        i iVar = this.f8220a;
        if (iVar != null) {
            iVar.a(ltaVar, i, str);
        }
    }

    class b implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f14860a;
        final /* synthetic */ lta b;
        final /* synthetic */ int c;

        @Override // java.lang.Runnable
        public void run() {
            lsw.this.b.a(this.b, this.c, this.f14860a);
        }

        b(lta ltaVar, int i, String str) {
            this.b = ltaVar;
            this.c = i;
            this.f14860a = str;
        }
    }

    class d implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f14861a;
        final /* synthetic */ String e;

        @Override // java.lang.Runnable
        public void run() {
            lsw.this.b.a(this.f14861a, this.e);
        }

        d(String str, String str2) {
            this.f14861a = str;
            this.e = str2;
        }
    }

    @Override // com.huawei.openplatform.abl.log.i
    public i a(String str, String str2) {
        this.d.execute(new d(str, str2));
        i iVar = this.f8220a;
        if (iVar != null) {
            iVar.a(str, str2);
        }
        return this;
    }

    public lsw(i iVar) {
        this.b = iVar;
    }
}
