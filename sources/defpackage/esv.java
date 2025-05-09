package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseConstants;
import com.huawei.pluginfitnessadvice.TargetConfig;
import java.util.List;

/* loaded from: classes3.dex */
public class esv {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("userDefinedType")
    private int f12237a;

    @SerializedName("description")
    private String b;

    @SerializedName("name")
    private String c;

    @SerializedName("actionComposition")
    private List<b> d;

    @SerializedName(HwExerciseConstants.METHOD_PARAM_WORKOUT_ID)
    private String e;

    private esv(a aVar) {
        this.e = aVar.c;
        this.b = aVar.f12238a;
        this.c = aVar.d;
        this.f12237a = aVar.b;
        this.d = aVar.e;
    }

    public static final class a {

        /* renamed from: a, reason: collision with root package name */
        private String f12238a;
        private int b;
        private String c;
        private String d;
        private List<b> e;

        public a d(String str) {
            this.c = str;
            return this;
        }

        public a e(String str) {
            this.f12238a = str;
            return this;
        }

        public a a(String str) {
            this.d = str;
            return this;
        }

        public a b(int i) {
            this.b = i;
            return this;
        }

        public a d(List<b> list) {
            this.e = list;
            return this;
        }

        public esv c() {
            return new esv(this);
        }
    }

    public static class b {

        /* renamed from: a, reason: collision with root package name */
        @SerializedName("actionList")
        private List<c> f12239a;

        @SerializedName("repeatTimes")
        private int b;

        private b(e eVar) {
            this.b = eVar.b;
            this.f12239a = eVar.c;
        }

        public static final class e {
            private int b;
            private List<c> c;

            public e e(int i) {
                this.b = i;
                return this;
            }

            public e d(List<c> list) {
                this.c = list;
                return this;
            }

            public b c() {
                return new b(this);
            }
        }
    }

    public static class c {

        /* renamed from: a, reason: collision with root package name */
        @SerializedName("actionId")
        private String f12240a;

        @SerializedName("strength")
        private TargetConfig b;

        @SerializedName("name")
        private String c;

        @SerializedName("target")
        private TargetConfig d;

        private c(d dVar) {
            this.f12240a = dVar.d;
            this.c = dVar.b;
            this.d = dVar.c;
            this.b = dVar.f12241a;
        }

        public static final class d {

            /* renamed from: a, reason: collision with root package name */
            private TargetConfig f12241a;
            private String b;
            private TargetConfig c;
            private String d;

            public d c(String str) {
                this.d = str;
                return this;
            }

            public d b(String str) {
                this.b = str;
                return this;
            }

            public d b(TargetConfig targetConfig) {
                this.c = targetConfig;
                return this;
            }

            public d d(TargetConfig targetConfig) {
                this.f12241a = targetConfig;
                return this;
            }

            public c a() {
                return new c(this);
            }
        }
    }
}
