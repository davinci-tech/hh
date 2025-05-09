package defpackage;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes9.dex */
public class jaj {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("VideoId")
    private String f13702a;

    @SerializedName("ImageId")
    private String c;

    @SerializedName("ImagePath")
    private String d;

    @SerializedName("VideoPath")
    private String e;

    public jaj(d dVar) {
        this.d = dVar.f13703a;
        this.e = dVar.c;
        this.c = dVar.b;
        this.f13702a = dVar.d;
    }

    public static class d {

        /* renamed from: a, reason: collision with root package name */
        private String f13703a;
        private String b;
        private String c;
        private String d;

        public d a(String str) {
            this.f13703a = str;
            return this;
        }

        public d e(String str) {
            this.c = str;
            return this;
        }

        public d b(String str) {
            this.b = str;
            return this;
        }

        public d c(String str) {
            this.d = str;
            return this;
        }

        public jaj c() {
            return new jaj(this);
        }
    }
}
