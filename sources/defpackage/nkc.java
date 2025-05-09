package defpackage;

/* loaded from: classes9.dex */
public class nkc {

    /* renamed from: a, reason: collision with root package name */
    public int f15340a;
    public int b;
    public int c;
    public int d;
    public boolean e;

    private nkc() {
        this.f15340a = 9728;
        this.c = 9729;
        this.d = 33071;
        this.b = 33071;
        this.e = false;
    }

    public static class a {
        public int d = 9728;
        public int c = 9729;
        public int b = 33071;
        public int e = 33071;

        /* renamed from: a, reason: collision with root package name */
        public boolean f15341a = false;

        public a a(int i) {
            this.d = i;
            return this;
        }

        public a b(int i) {
            this.c = i;
            return this;
        }

        public a a(boolean z) {
            this.f15341a = z;
            return this;
        }

        public nkc d() {
            int i;
            nkc nkcVar = new nkc();
            nkcVar.f15340a = this.d;
            nkcVar.c = this.c;
            nkcVar.d = this.b;
            nkcVar.b = this.e;
            nkcVar.e = this.f15341a;
            if (!this.f15341a || ((i = this.d) != 9728 && i != 9729)) {
                return nkcVar;
            }
            njw.c("TextureOptions", "The value not good for texture when you wanna use mipmap.");
            throw new RuntimeException("the value GLES20.GL_NEAREST or GLES20.GL_LINEAR for GL_TEXTURE_MIN_FILTER is not good for texture when you wanna use mipmap");
        }
    }

    public static nkc e() {
        return new a().d();
    }

    public static nkc d() {
        return new a().a(true).a(9987).b(9987).d();
    }
}
