package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseConstants;
import com.huawei.networkclient.IRequest;

/* loaded from: classes8.dex */
public class esy implements IRequest {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("planId")
    private String f12242a;

    @SerializedName("dayNum")
    private Integer b;

    @SerializedName(HwExerciseConstants.METHOD_PARAM_WORKOUT_ID)
    private String c;

    @SerializedName("planType")
    private Integer d;

    @SerializedName("weekNum")
    private Integer e;

    private esy(a aVar) {
        this.d = aVar.e;
        this.f12242a = aVar.d;
        this.e = aVar.f12243a;
        this.b = aVar.b;
        this.c = aVar.c;
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return eta.cc();
    }

    public static final class a {

        /* renamed from: a, reason: collision with root package name */
        private Integer f12243a;
        private Integer b;
        private String c;
        private String d;
        private Integer e;

        public a d(Integer num) {
            this.e = num;
            return this;
        }

        public a d(String str) {
            this.d = str;
            return this;
        }

        public a e(Integer num) {
            this.f12243a = num;
            return this;
        }

        public a a(Integer num) {
            this.b = num;
            return this;
        }

        public a a(String str) {
            this.c = str;
            return this;
        }

        public esy c() {
            return new esy(this);
        }
    }
}
