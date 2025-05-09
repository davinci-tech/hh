package defpackage;

import com.huawei.hms.common.internal.Preconditions;
import java.util.List;
import java.util.Locale;

/* loaded from: classes6.dex */
public class mgu {

    /* renamed from: a, reason: collision with root package name */
    private List<mgr> f14986a;
    private String b;

    public mgu(String str, List<mgr> list) {
        Preconditions.checkNotNull(str);
        this.b = str;
        this.f14986a = list;
    }

    public List<mgr> b() {
        return this.f14986a;
    }

    public String toString() {
        return String.format(Locale.ENGLISH, "DataType{%s%s}", this.b, this.f14986a);
    }
}
