package defpackage;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes4.dex */
public class ffk {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("workoutPackageId")
    private String f12487a;

    @SerializedName("lang")
    private String c;

    private ffk(d dVar) {
        this.c = dVar.d;
        this.f12487a = dVar.f12488a;
    }

    public static final class d {

        /* renamed from: a, reason: collision with root package name */
        private String f12488a;
        private String d;

        public d(String str) {
            this.f12488a = str;
        }

        public d e(String str) {
            this.d = str;
            return this;
        }

        public ffk c() {
            return new ffk(this);
        }
    }

    public String e() {
        return this.c;
    }

    public String d() {
        return this.f12487a;
    }
}
