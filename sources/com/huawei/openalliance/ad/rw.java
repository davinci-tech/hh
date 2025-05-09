package com.huawei.openalliance.ad;

import android.content.Context;
import com.huawei.openalliance.ad.beans.metadata.ReduceDisturbRule;
import com.huawei.openalliance.ad.beans.metadata.Rule;
import com.huawei.openalliance.ad.db.bean.UserCloseRecord;
import java.util.Date;
import java.util.List;

/* loaded from: classes5.dex */
public class rw implements sk {

    /* renamed from: a, reason: collision with root package name */
    private gg f7520a;
    private gc b;

    @Override // com.huawei.openalliance.ad.sk
    public void b() {
        com.huawei.openalliance.ad.utils.m.a(new Runnable() { // from class: com.huawei.openalliance.ad.rw.2
            @Override // java.lang.Runnable
            public void run() {
                String b = com.huawei.openalliance.ad.utils.ao.b("yyyy-MM-dd");
                if (!b.equals(rw.this.b.aK())) {
                    rw.this.b.f(b);
                    rw.this.b.e(0);
                }
                rw.this.b.e(rw.this.b.aJ() + 1);
            }
        });
    }

    @Override // com.huawei.openalliance.ad.sk
    public void a() {
        final UserCloseRecord userCloseRecord = new UserCloseRecord();
        userCloseRecord.a(com.huawei.openalliance.ad.utils.ao.c());
        userCloseRecord.a(com.huawei.openalliance.ad.utils.ao.b("yyyy-MM-dd HH:mm:ss"));
        final long time = com.huawei.openalliance.ad.utils.ao.a(new Date(), this.b.aW()).getTime();
        com.huawei.openalliance.ad.utils.m.a(new Runnable() { // from class: com.huawei.openalliance.ad.rw.1
            @Override // java.lang.Runnable
            public void run() {
                rw.this.f7520a.a(userCloseRecord);
                rw.this.f7520a.a(time);
                rw.this.c();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        ReduceDisturbRule reduceDisturbRule;
        List<Rule> a2;
        String a3 = this.b.a();
        if (com.huawei.openalliance.ad.utils.cz.b(a3) || (reduceDisturbRule = (ReduceDisturbRule) com.huawei.openalliance.ad.utils.be.b(a3, ReduceDisturbRule.class, new Class[0])) == null || (a2 = reduceDisturbRule.a()) == null) {
            return;
        }
        long c = com.huawei.openalliance.ad.utils.ao.c();
        long j = 0;
        for (Rule rule : a2) {
            if (a(rule)) {
                int size = this.f7520a.a(com.huawei.openalliance.ad.utils.ao.a(new Date(), rule.a()).getTime(), c).size();
                if (size >= rule.b() && size <= rule.c() && j <= rule.d()) {
                    j = rule.d();
                }
            }
        }
        this.b.d(j + c);
    }

    private boolean a(Rule rule) {
        return rule != null && rule.a() <= this.b.aW() && rule.a() >= 1 && rule.b() > 0 && rule.c() > 0 && rule.d() > 0;
    }

    public rw(Context context) {
        this.b = null;
        this.b = fh.b(context);
        this.f7520a = fn.a(context);
    }
}
