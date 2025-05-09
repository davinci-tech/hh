package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseConstants;
import com.huawei.networkclient.IRequest;

/* loaded from: classes3.dex */
public class erx implements IRequest {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName(HwExerciseConstants.METHOD_PARAM_WORKOUT_ID)
    private String f12207a;

    @SerializedName("courseBelongType")
    private Integer b;

    @SerializedName("operType")
    private Integer c;

    @SerializedName("version")
    private String d;

    @SerializedName("workoutPackageId")
    private String e;

    private erx(a aVar) {
        this.f12207a = aVar.f12208a;
        this.e = aVar.b;
        this.b = aVar.c;
        this.d = aVar.e;
        this.c = aVar.d;
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return eta.ay();
    }

    public static final class a {

        /* renamed from: a, reason: collision with root package name */
        private String f12208a;
        private String b;
        private Integer c;
        private Integer d;
        private String e;

        public a c(String str) {
            this.f12208a = str;
            return this;
        }

        public a e(String str) {
            this.b = str;
            return this;
        }

        public a c(Integer num) {
            this.c = num;
            return this;
        }

        public a a(String str) {
            this.e = str;
            return this;
        }

        public a b(Integer num) {
            this.d = num;
            return this;
        }

        public erx d() {
            return new erx(this);
        }
    }
}
