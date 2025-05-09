package defpackage;

import defpackage.adj;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/* loaded from: classes8.dex */
public class adj {

    /* renamed from: a, reason: collision with root package name */
    private List<b> f173a;

    public adj() {
        this.f173a = Collections.emptyList();
    }

    public adj(List<b> list) {
        this.f173a = Collections.emptyList();
        this.f173a = (List) list.stream().sorted(new Comparator() { // from class: adn
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return adj.e((adj.b) obj, (adj.b) obj2);
            }
        }).collect(Collectors.toList());
    }

    static /* synthetic */ int e(b bVar, b bVar2) {
        return -Float.compare(bVar.d, bVar2.d);
    }

    public List<b> c() {
        return this.f173a;
    }

    public String toString() {
        return this.f173a.toString();
    }

    public static class b {
        private final String b;
        private final float d;

        public b(String str, float f) {
            this.b = str;
            this.d = f;
        }

        public String e() {
            return this.b;
        }

        public String toString() {
            return "{label='" + this.b + "', prob=" + this.d + '}';
        }
    }
}
