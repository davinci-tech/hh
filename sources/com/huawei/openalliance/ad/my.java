package com.huawei.openalliance.ad;

import android.content.Context;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.inter.data.MaterialClickInfo;
import com.huawei.openalliance.ad.processor.eventbeans.a;

/* loaded from: classes5.dex */
public class my {

    /* renamed from: a, reason: collision with root package name */
    private Context f7283a;
    private ContentRecord b;
    private MaterialClickInfo c;

    public void a() {
        long j;
        int i;
        if (this.b == null) {
            return;
        }
        ho.b("AppDownBtnPresenter", "rpt imp for app detail activity.");
        Context context = this.f7283a;
        ou ouVar = new ou(context, sc.a(context, this.b.e()));
        ouVar.a(this.b);
        if (this.b.h() != null) {
            j = this.b.h().i();
            i = this.b.h().j();
        } else {
            j = 500;
            i = 50;
        }
        a.C0207a b = new a.C0207a().a(Long.valueOf(j)).a(Integer.valueOf(i)).b((Integer) 2).a(com.huawei.openalliance.ad.utils.b.a((Object) this.f7283a)).d(Constants.DEF_SLOT_POSITION).b(this.b.aA());
        MaterialClickInfo materialClickInfo = this.c;
        if (materialClickInfo != null && com.huawei.openalliance.ad.utils.cz.p(materialClickInfo.c())) {
            b.e(this.c.c());
        }
        ouVar.a(b.a());
    }

    public my(Context context, ContentRecord contentRecord, MaterialClickInfo materialClickInfo) {
        this.f7283a = context;
        this.b = contentRecord;
        this.c = materialClickInfo;
    }
}
