package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseConstants;
import com.huawei.networkclient.IRequest;
import java.util.List;

/* loaded from: classes8.dex */
public class erj implements IRequest {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("lang")
    private String f12189a;

    @SerializedName(HwExerciseConstants.METHOD_PARAM_WORKOUT_TYPE)
    private List<Integer> d;

    private erj(e eVar) {
        this.f12189a = eVar.e;
        this.d = eVar.d;
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return eta.ad();
    }

    public static final class e {
        private List<Integer> d;
        private String e;

        public e c(String str) {
            this.e = str;
            return this;
        }

        public e a(List<Integer> list) {
            this.d = list;
            return this;
        }

        public erj a() {
            return new erj(this);
        }
    }
}
