package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseConstants;

/* loaded from: classes4.dex */
public class fey {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName(HwExerciseConstants.METHOD_PARAM_WORKOUT_ID)
    private String f12476a;

    @SerializedName("userDefinedType")
    private int b;

    @SerializedName("timbre")
    private String c;

    @SerializedName("version")
    private String e;

    private fey(d dVar) {
        this.f12476a = dVar.f12477a;
        this.e = dVar.c;
        this.b = dVar.b;
        this.c = dVar.d;
    }

    public String a() {
        return this.f12476a;
    }

    public String d() {
        return this.e;
    }

    public int c() {
        return this.b;
    }

    public String e() {
        return this.c;
    }

    public static final class d {

        /* renamed from: a, reason: collision with root package name */
        private String f12477a;
        private int b;
        private String c;
        private String d;

        public d e(String str) {
            this.f12477a = str;
            return this;
        }

        public d d(String str) {
            this.c = str;
            return this;
        }

        public d d(int i) {
            this.b = i;
            return this;
        }

        public d c(String str) {
            this.d = str;
            return this;
        }

        public fey a() {
            return new fey(this);
        }
    }
}
