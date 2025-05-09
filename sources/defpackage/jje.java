package defpackage;

import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import java.text.Collator;
import java.util.Comparator;

/* loaded from: classes5.dex */
public class jje {
    private Drawable b = null;
    private String c = null;
    private String j = null;
    private String e = null;

    /* renamed from: a, reason: collision with root package name */
    private int f13888a = 0;
    private int d = -1;

    public void bHw_(Drawable drawable) {
        this.b = drawable;
    }

    public Drawable bHv_() {
        return this.b;
    }

    public void a(String str) {
        this.c = str;
    }

    public String c() {
        return this.c;
    }

    public void c(String str) {
        this.j = str;
    }

    public String g() {
        return this.j;
    }

    public void b(int i) {
        this.f13888a = i;
    }

    public int d() {
        return this.f13888a;
    }

    public void c(int i) {
        this.d = i;
    }

    public int b() {
        return this.d;
    }

    public String e() {
        return this.e;
    }

    public void d(String str) {
        this.e = str;
    }

    public static class b implements Comparator<jje> {
        private final Collator c;

        public b() {
            Collator collator = Collator.getInstance();
            this.c = collator;
            collator.setStrength(0);
        }

        @Override // java.util.Comparator
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public final int compare(jje jjeVar, jje jjeVar2) {
            if (TextUtils.isEmpty(jjeVar.c())) {
                return 1;
            }
            if (TextUtils.isEmpty(jjeVar2.c())) {
                return -1;
            }
            return this.c.compare(jjeVar.c(), jjeVar2.c());
        }
    }
}
