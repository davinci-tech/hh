package com.huawei.openalliance.ad;

import android.content.Context;
import android.view.View;
import com.huawei.openalliance.ad.constant.VastAttribute;
import com.huawei.openalliance.ad.db.bean.ContentRecord;

/* loaded from: classes5.dex */
public class lo implements lz {

    /* renamed from: a, reason: collision with root package name */
    private lw f7188a;
    private mi b;
    private boolean c;
    private boolean d = true;
    private boolean e = false;
    private boolean f = false;
    private boolean g = false;
    private boolean h = false;

    @Override // com.huawei.openalliance.ad.mn
    public void l() {
        ho.b("OmPresent", "resume");
        if (!this.d && this.e) {
            ho.c("OmPresent", "resume: Video completed");
            return;
        }
        lw lwVar = this.f7188a;
        if (lwVar instanceof ly) {
            ((ly) lwVar).l();
        }
    }

    @Override // com.huawei.openalliance.ad.mn
    public void k() {
        ho.b("OmPresent", VastAttribute.PAUSE);
        if (!this.d && this.e) {
            ho.c("OmPresent", "pause: Video completed");
            return;
        }
        lw lwVar = this.f7188a;
        if (lwVar instanceof ly) {
            ((ly) lwVar).k();
        }
    }

    @Override // com.huawei.openalliance.ad.mn
    public void j() {
        ho.b("OmPresent", "skipped");
        lw lwVar = this.f7188a;
        if (lwVar instanceof ly) {
            ((ly) lwVar).j();
        }
    }

    @Override // com.huawei.openalliance.ad.mn
    public void i() {
        if (this.d || !this.e) {
            lw lwVar = this.f7188a;
            if (lwVar instanceof ly) {
                ((ly) lwVar).i();
            }
        }
    }

    @Override // com.huawei.openalliance.ad.mn
    public void h() {
        if (this.d || !this.e) {
            lw lwVar = this.f7188a;
            if (lwVar instanceof ly) {
                ((ly) lwVar).h();
            }
        }
    }

    @Override // com.huawei.openalliance.ad.mn
    public void g() {
        ho.b("OmPresent", "complete");
        if (this.d || !this.e) {
            lw lwVar = this.f7188a;
            if (lwVar instanceof ly) {
                ((ly) lwVar).g();
                this.e = true;
            }
        }
    }

    @Override // com.huawei.openalliance.ad.mm
    public void f() {
        ho.b("OmPresent", "load");
        if (this.d || !this.f) {
            lw lwVar = this.f7188a;
            if (lwVar instanceof lr) {
                ((lr) lwVar).f();
            }
        }
    }

    @Override // com.huawei.openalliance.ad.mm
    public void e() {
        ho.b("OmPresent", "impressionOccurred");
        if (this.f) {
            return;
        }
        lw lwVar = this.f7188a;
        if (lwVar instanceof lr) {
            ((lr) lwVar).e();
            this.f = true;
        }
        lw lwVar2 = this.f7188a;
        if (lwVar2 instanceof ly) {
            ((ly) lwVar2).f();
            this.f = true;
        }
    }

    @Override // com.huawei.openalliance.ad.mi
    public void d() {
        mi miVar = this.b;
        if (miVar == null) {
            return;
        }
        miVar.d();
    }

    @Override // com.huawei.openalliance.ad.mi
    public void c() {
        mi miVar = this.b;
        if (miVar == null) {
            ho.b("OmPresent", "AdSessionAgent is null");
        } else {
            miVar.c();
        }
    }

    @Override // com.huawei.openalliance.ad.mn
    public void b(boolean z) {
        lw lwVar = this.f7188a;
        if (lwVar instanceof ly) {
            ((ly) lwVar).b(z);
        }
    }

    @Override // com.huawei.openalliance.ad.mn
    public void b(float f) {
        if (!this.d && this.e) {
            ho.c("OmPresent", "volumeChange: Video completed");
            return;
        }
        lw lwVar = this.f7188a;
        if (lwVar instanceof ly) {
            ((ly) lwVar).b(f);
        }
    }

    @Override // com.huawei.openalliance.ad.lz
    public void b() {
        ho.b("OmPresent", "release");
        if (this.g) {
            this.d = true;
            this.e = false;
            this.f = false;
            lw lwVar = this.f7188a;
            if (lwVar != null) {
                lwVar.b();
            }
            mi miVar = this.b;
            if (miVar != null) {
                miVar.d();
            }
            this.g = false;
        }
    }

    @Override // com.huawei.openalliance.ad.lz
    public void a(boolean z) {
        this.d = z;
    }

    @Override // com.huawei.openalliance.ad.mn
    public void a(mq mqVar) {
        if (!this.d && this.e) {
            ho.c("OmPresent", "loaded: Video completed");
            return;
        }
        if (this.h) {
            if (ho.a()) {
                ho.a("OmPresent", "Already loaded");
            }
        } else {
            lw lwVar = this.f7188a;
            if (lwVar instanceof ly) {
                ((ly) lwVar).a(mqVar);
            }
            this.h = true;
        }
    }

    @Override // com.huawei.openalliance.ad.mn
    public void a(mo moVar) {
        lw lwVar = this.f7188a;
        if (lwVar instanceof ly) {
            ((ly) lwVar).a(moVar);
        }
    }

    @Override // com.huawei.openalliance.ad.mi
    public void a(View view, mh mhVar, String str) {
        mi miVar = this.b;
        if (miVar == null) {
            return;
        }
        miVar.a(view, mhVar, str);
    }

    @Override // com.huawei.openalliance.ad.mi
    public void a(View view) {
        if (this.c) {
            return;
        }
        mi miVar = this.b;
        if (miVar == null) {
            ho.b("OmPresent", "AdSessionAgent is null");
        } else {
            miVar.a(view);
        }
    }

    @Override // com.huawei.openalliance.ad.lz
    public void a(Context context, ContentRecord contentRecord, lm lmVar, boolean z) {
        if ((contentRecord != null ? contentRecord.b(context) : null) == null) {
            ho.b("OmPresent", "om is null, no initialization is required");
            return;
        }
        if (this.g) {
            return;
        }
        ho.b("OmPresent", "init omPresent");
        this.b = lq.a(context, contentRecord, lmVar, z);
        lw a2 = lv.a(contentRecord);
        this.f7188a = a2;
        a2.a(this.b);
        this.c = z;
        this.g = true;
        this.h = false;
        this.f = false;
    }

    @Override // com.huawei.openalliance.ad.mn
    public void a(float f, boolean z) {
        ho.b("OmPresent", "start");
        if (!this.d && this.e) {
            ho.c("OmPresent", "start: Video completed");
            return;
        }
        lw lwVar = this.f7188a;
        if (lwVar instanceof ly) {
            ((ly) lwVar).a(f, z);
        }
    }

    @Override // com.huawei.openalliance.ad.mn
    public void a(float f) {
        if (ho.a()) {
            ho.a("OmPresent", "onProgress, isAllowRepeat= %s, isVideoComplete= %s", Boolean.valueOf(this.d), Boolean.valueOf(this.e));
        }
        if (this.d || !this.e) {
            lw lwVar = this.f7188a;
            if (lwVar instanceof ly) {
                ((ly) lwVar).a(f);
            }
        }
    }

    @Override // com.huawei.openalliance.ad.lz
    public mi a() {
        return this.b;
    }
}
