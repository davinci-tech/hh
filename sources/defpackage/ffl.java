package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseConstants;

/* loaded from: classes4.dex */
public class ffl {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("lang")
    private String f12489a;
    private transient boolean b;

    @SerializedName("mediaType")
    private Integer c;

    @SerializedName("sex")
    private Integer d;

    @SerializedName("operationSchemeId")
    private String e;

    @SerializedName(HwExerciseConstants.METHOD_PARAM_WORKOUT_ID)
    private String f;

    @SerializedName("version")
    private String i;

    @SerializedName("userDefinedType")
    private Integer j;

    private ffl(d dVar) {
        this.f = dVar.h;
        this.d = dVar.b;
        this.i = dVar.j;
        this.f12489a = dVar.c;
        this.e = dVar.f12490a;
        this.j = Integer.valueOf(dVar.g);
        this.c = Integer.valueOf(dVar.e);
        this.b = dVar.d;
    }

    /* loaded from: classes.dex */
    public static final class d {

        /* renamed from: a, reason: collision with root package name */
        private String f12490a;
        private Integer b;
        private String c;
        private boolean d = true;
        private int e;
        private int g;
        private String h;
        private String j;

        public d(String str) {
            this.h = str;
        }

        public d a(Integer num) {
            this.b = num;
            return this;
        }

        public d d(String str) {
            this.j = str;
            return this;
        }

        public d c(String str) {
            this.c = str;
            return this;
        }

        public d e(String str) {
            this.f12490a = str;
            return this;
        }

        public d d(int i) {
            this.g = i;
            return this;
        }

        public d a(int i) {
            this.e = i;
            return this;
        }

        public d c(boolean z) {
            this.d = z;
            return this;
        }

        public ffl b() {
            return new ffl(this);
        }
    }

    public String h() {
        return this.f;
    }

    public Integer a() {
        return this.d;
    }

    public String g() {
        return this.i;
    }

    public String d() {
        return this.f12489a;
    }

    public String e() {
        return this.e;
    }

    public int f() {
        return this.j.intValue();
    }

    public int b() {
        return this.c.intValue();
    }

    public boolean c() {
        return this.b;
    }
}
