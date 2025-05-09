package com.amap.api.col.p0003sl;

import android.content.Context;
import android.os.Bundle;
import com.amap.api.col.p0003sl.bq;
import com.amap.api.col.p0003sl.bw;
import java.io.IOException;

/* loaded from: classes2.dex */
public final class bb extends lb implements bq.a {

    /* renamed from: a, reason: collision with root package name */
    private bq f916a;
    private bs b;
    private bv c;
    private Context d;
    private Bundle e;
    private boolean g;

    private bb(bv bvVar, Context context) {
        this.e = new Bundle();
        this.g = false;
        this.c = bvVar;
        this.d = context;
    }

    public bb(bv bvVar, Context context, byte b) {
        this(bvVar, context);
    }

    @Override // com.amap.api.col.p0003sl.lb
    public final void runTask() {
        if (this.c.u()) {
            this.c.a(bw.a.file_io_exception);
            return;
        }
        try {
            e();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public final void a() {
        this.g = true;
        bq bqVar = this.f916a;
        if (bqVar != null) {
            bqVar.b();
        } else {
            cancelTask();
        }
        bs bsVar = this.b;
        if (bsVar != null) {
            bsVar.a();
        }
    }

    private String d() {
        return dv.c(this.d);
    }

    private void e() throws IOException {
        bq bqVar = new bq(new br(this.c.getUrl(), d(), this.c.v(), this.c.w()), this.c.getUrl(), this.d, this.c);
        this.f916a = bqVar;
        bqVar.a(this);
        bv bvVar = this.c;
        this.b = new bs(bvVar, bvVar);
        if (this.g) {
            return;
        }
        this.f916a.a();
    }

    public final void b() {
        Bundle bundle = this.e;
        if (bundle != null) {
            bundle.clear();
            this.e = null;
        }
    }

    @Override // com.amap.api.col.3sl.bq.a
    public final void c() {
        bs bsVar = this.b;
        if (bsVar != null) {
            bsVar.b();
        }
    }
}
