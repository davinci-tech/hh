package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.ads.adsrec.e;
import com.huawei.ads.adsrec.e0;
import com.huawei.ads.adsrec.g0;
import com.huawei.ads.adsrec.r0;
import com.huawei.ads.adsrec.u;
import com.huawei.ads.fund.util.AsyncExec;
import com.huawei.openplatform.abl.log.HiAdLog;
import java.util.List;

/* loaded from: classes2.dex */
public class ux implements g0 {

    /* renamed from: a, reason: collision with root package name */
    private Context f17567a;
    private e0 d;

    r0 e(Context context, int i, e0 e0Var) {
        HiAdLog.i("FullRecallTask", "create recall strategy via api: %s", Integer.valueOf(i));
        return (i == 1 || i == 2) ? new vv(context, e0Var) : new vu(context, e0Var);
    }

    r0 d(Context context, int i, e0 e0Var) {
        HiAdLog.i("FullRecallTask", "create recall strategy: %s", Integer.valueOf(i));
        return i != 1 ? i != 2 ? new vu(context, e0Var) : new vn(context, e0Var) : new vv(context, e0Var);
    }

    @Override // com.huawei.ads.adsrec.g0
    public <T> vh a(vt vtVar, T t) {
        HiAdLog.i("FullRecallTask", "recall");
        if (vtVar == null) {
            return null;
        }
        a(vtVar);
        String e = e(vtVar);
        if (TextUtils.isEmpty(e)) {
            HiAdLog.w("FullRecallTask", "no slot id");
            return null;
        }
        int d = wc.d(this.f17567a).d(e);
        vh a2 = d(this.f17567a, d, this.d).a(vtVar, (vt) t);
        if (a2 != null) {
            a2.c(d);
            b(a2.b(), vtVar.h());
        }
        return a2;
    }

    @Override // com.huawei.ads.adsrec.g0
    public <T> vh a(vt vtVar, vh vhVar) {
        HiAdLog.i("FullRecallTask", "recall via api");
        if (vtVar == null) {
            return null;
        }
        a(vtVar);
        String e = e(vtVar);
        if (TextUtils.isEmpty(e)) {
            HiAdLog.w("FullRecallTask", "no slot id");
            return null;
        }
        int d = wc.d(this.f17567a).d(e);
        vh a2 = e(this.f17567a, d, this.d).a(vtVar, vhVar);
        if (a2 != null) {
            a2.c(d);
            b(a2.b(), vtVar.h());
        }
        return a2;
    }

    private void a(vt vtVar) {
        u.a(vtVar.a(), vtVar.h(), vtVar.b());
    }

    private void b(vh vhVar, int i) {
        AsyncExec.submitSeqIO(new c(vhVar, i));
    }

    class c implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ int f17568a;
        final /* synthetic */ vh e;

        @Override // java.lang.Runnable
        public void run() {
            e eVar = new e(ux.this.f17567a);
            vh vhVar = this.e;
            if (vhVar != null) {
                eVar.a(vhVar.e());
                eVar.a(this.e.j(), this.f17568a);
            }
        }

        c(vh vhVar, int i) {
            this.e = vhVar;
            this.f17568a = i;
        }
    }

    private String e(vt vtVar) {
        List<String> d = vtVar.d();
        if (d == null || d.size() <= 0) {
            return null;
        }
        return d.get(0);
    }

    public ux(Context context, e0 e0Var) {
        this.f17567a = context.getApplicationContext();
        this.d = e0Var;
    }
}
