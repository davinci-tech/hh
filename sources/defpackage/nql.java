package defpackage;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class nql {

    /* renamed from: a, reason: collision with root package name */
    private String f15442a;
    private List<e> d;

    public nql(String str, List<e> list) {
        ArrayList arrayList = new ArrayList();
        this.d = arrayList;
        this.f15442a = str;
        arrayList.addAll(list);
    }

    public String d() {
        return this.f15442a;
    }

    public List<e> a() {
        return this.d;
    }

    public static final class e {
        public final String b;
        public final String d;

        public e(String str, String str2) {
            this.b = str;
            this.d = str2;
        }
    }
}
