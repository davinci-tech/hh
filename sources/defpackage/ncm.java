package defpackage;

import com.huawei.openalliance.ad.db.bean.TemplateStyleRecord;

/* loaded from: classes6.dex */
public class ncm {

    /* renamed from: a, reason: collision with root package name */
    private String[] f15249a;
    private String b;
    private String c;
    private String[] d;
    private String e;
    private String g;

    public ncm(e eVar) {
        this.c = eVar.c;
        this.g = eVar.j;
        this.b = eVar.b;
        this.e = eVar.e;
        this.f15249a = eVar.d;
        this.d = eVar.f15250a;
    }

    public String e() {
        return this.g;
    }

    public String a() {
        return this.b;
    }

    public String b() {
        return this.e;
    }

    public String[] c() {
        return this.f15249a;
    }

    public String[] d() {
        return this.d;
    }

    public String toString() {
        return "mStyleType: " + this.c + " mSupportWidgetClz: " + this.g + " mStyleableClzName" + this.b + " mStyleableField: " + this.e + " mIndexNames: " + this.f15249a + " mAttrNames: " + this.d;
    }

    public static final class e {

        /* renamed from: a, reason: collision with root package name */
        private String[] f15250a;
        private String b;
        private String c;
        private String[] d;
        private String e;
        private String j;

        public e d(String str) {
            if (TemplateStyleRecord.STYLE.equals(str)) {
                this.c = TemplateStyleRecord.STYLE;
            } else if ("textAppearance".equals(str)) {
                this.c = "textAppearance";
            }
            return this;
        }

        public e e(String str) {
            this.j = str;
            return this;
        }

        public e c(String str) {
            this.b = str;
            return this;
        }

        public e a(String str) {
            this.e = str;
            return this;
        }

        public e e(String[] strArr) {
            this.d = strArr;
            return this;
        }

        public e d(String[] strArr) {
            this.f15250a = strArr;
            return this;
        }

        public ncm d() {
            return new ncm(this);
        }
    }
}
