package defpackage;

import com.huawei.ads.fund.util.ListUtil;
import com.huawei.ads.fund.util.StringUtils;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes2.dex */
public class wb {

    /* renamed from: a, reason: collision with root package name */
    private long f17725a;
    private String b;
    private Map<String, we> c;
    private long e;

    public long d() {
        return this.e;
    }

    public String e() {
        return this.b;
    }

    public void e(long j) {
        this.e = j;
    }

    public long c() {
        return this.f17725a;
    }

    public void b(String str) {
        this.b = str;
    }

    public void b(long j) {
        this.f17725a = j;
    }

    public String b() {
        if (vp.a(this.c)) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (we weVar : this.c.values()) {
            if (sb.length() > 0) {
                sb.append("; ");
            }
            String d = weVar.d();
            if (!StringUtils.isBlank(d)) {
                sb.append(d);
            }
        }
        return sb.toString();
    }

    public wb(List<vg> list) {
        if (ListUtil.isEmpty(list)) {
            return;
        }
        this.c = new LinkedHashMap();
        for (vg vgVar : list) {
            if (vgVar != null && !vgVar.g()) {
                String h = vgVar.h();
                if (!StringUtils.isBlank(h)) {
                    List<vb> a2 = vgVar.a();
                    if (!ListUtil.isEmpty(a2)) {
                        we weVar = new we();
                        for (vb vbVar : a2) {
                            if (vbVar != null) {
                                if (Math.abs(vbVar.h()) > 9.99999993922529E-9d) {
                                    weVar.b();
                                }
                                if (!vbVar.y().booleanValue()) {
                                    weVar.e();
                                }
                            }
                        }
                        weVar.c(a2.size());
                        weVar.d(h);
                        this.c.put(h, weVar);
                    }
                }
            }
        }
    }

    public wb() {
    }
}
