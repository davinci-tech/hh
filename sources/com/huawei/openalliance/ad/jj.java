package com.huawei.openalliance.ad;

import android.view.View;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.jk;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public class jj<V extends jk> {

    /* renamed from: a, reason: collision with root package name */
    protected ContentRecord f7126a;
    protected qq b;
    protected qq c;
    private V d;
    private Map<String, Boolean> e = new HashMap();
    private String f;

    public String g() {
        V v = this.d;
        if (v instanceof View) {
            return vd.b((View) v);
        }
        return null;
    }

    protected String f() {
        return com.huawei.openalliance.ad.utils.dd.a(d());
    }

    public void e() {
        Map<String, Boolean> map = this.e;
        if (map == null) {
            return;
        }
        map.clear();
    }

    public V d() {
        return this.d;
    }

    public void b(long j) {
        ContentRecord contentRecord = this.f7126a;
        if (contentRecord == null) {
            ho.b("BasePresenter", "contentRecord is null");
        } else if (contentRecord.j() != null && this.f7126a.j().equals(this.f)) {
            ho.b("BasePresenter", "Duplicate escalation videoTime event for %s", this.f7126a.j());
        } else {
            this.b.a(j);
            this.f = this.f7126a.j();
        }
    }

    public void a(String str) {
        ContentRecord contentRecord = this.f7126a;
        if (contentRecord == null) {
            return;
        }
        contentRecord.c(str);
        this.b.a(this.f7126a);
        e();
    }

    public void a(qq qqVar) {
        this.c = qqVar;
    }

    public void a(V v) {
        this.d = v;
    }

    public void a(long j, long j2) {
        String str;
        ContentRecord contentRecord = this.f7126a;
        if (contentRecord == null || !contentRecord.aM()) {
            return;
        }
        if (j >= j2) {
            str = "complete";
            if (b("complete")) {
                return;
            } else {
                this.b.u();
            }
        } else {
            long j3 = j2 / 4;
            if (j > 3 * j3) {
                str = "thirdQuartile";
                if (b("thirdQuartile")) {
                    return;
                } else {
                    this.b.s();
                }
            } else if (j > j2 / 2) {
                str = "midpoint";
                if (b("midpoint")) {
                    return;
                } else {
                    this.b.r();
                }
            } else if (j > j3) {
                str = "firstQuartile";
                if (b("firstQuartile")) {
                    return;
                } else {
                    this.b.q();
                }
            } else {
                if (j <= 0) {
                    return;
                }
                str = "start";
                if (b("start")) {
                    return;
                } else {
                    this.b.t();
                }
            }
        }
        this.e.put(str, true);
    }

    public void a(long j, int i, int i2, int i3) {
        ot otVar = new ot();
        otVar.b(Integer.valueOf(i2));
        otVar.a(Integer.valueOf(i3));
        this.b.a(j, i, otVar);
    }

    public void a(long j) {
        ContentRecord contentRecord = this.f7126a;
        if (contentRecord == null) {
            return;
        }
        contentRecord.f(j);
        this.b.a(this.f7126a);
    }

    private boolean b(String str) {
        return this.e.containsKey(str) && this.e.get(str).booleanValue();
    }
}
