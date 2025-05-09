package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseConstants;
import com.huawei.networkclient.IRequest;

/* loaded from: classes7.dex */
public class ert implements IRequest {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName(HwExerciseConstants.METHOD_PARAM_WORKOUT_TYPE)
    private Integer f12199a;

    @SerializedName("topicId")
    private Integer c;

    @SerializedName("lang")
    private String e;

    private ert(b bVar) {
        this.c = bVar.e;
        this.f12199a = bVar.d;
        this.e = bVar.c;
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return eta.ao();
    }

    public static final class b {
        private String c;
        private Integer d;
        private Integer e;

        public b e(Integer num) {
            this.e = num;
            return this;
        }

        public b b(Integer num) {
            this.d = num;
            return this;
        }

        public b a(String str) {
            this.c = str;
            return this;
        }

        public ert c() {
            return new ert(this);
        }
    }
}
