package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseConstants;
import com.huawei.networkclient.IRequest;

/* loaded from: classes3.dex */
public class eqh implements IRequest {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName(HwExerciseConstants.METHOD_PARAM_WORKOUT_ID)
    private String f12162a;

    @SerializedName("userDefinedType")
    private int d;

    private eqh(e eVar) {
        this.f12162a = eVar.b;
        this.d = eVar.e;
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return eta.cd();
    }

    public static final class e {
        private String b;
        private int e;

        public e c(String str) {
            this.b = str;
            return this;
        }

        public e d(int i) {
            this.e = i;
            return this;
        }

        public eqh d() {
            return new eqh(this);
        }
    }
}
