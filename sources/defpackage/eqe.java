package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseConstants;
import com.huawei.networkclient.IRequest;

/* loaded from: classes3.dex */
public class eqe implements IRequest {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName(HwExerciseConstants.METHOD_PARAM_WORKOUT_ID)
    private String f12157a;

    @SerializedName("workoutPackageId")
    private String b;

    @SerializedName("courseBelongType")
    private int e;

    private eqe(d dVar) {
        this.f12157a = dVar.f12158a;
        this.e = dVar.c;
        this.b = dVar.b;
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return eta.f();
    }

    public static final class d {

        /* renamed from: a, reason: collision with root package name */
        private String f12158a;
        private String b;
        private int c;

        public d d(String str) {
            this.f12158a = str;
            return this;
        }

        public d e(int i) {
            this.c = i;
            return this;
        }

        public d c(String str) {
            this.b = str;
            return this;
        }

        public eqe c() {
            return new eqe(this);
        }
    }
}
