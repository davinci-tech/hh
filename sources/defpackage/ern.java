package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseConstants;
import com.huawei.networkclient.IRequest;

/* loaded from: classes3.dex */
public class ern implements IRequest {

    @SerializedName("mediaType")
    private Integer b;

    @SerializedName("actionVersion")
    private String c;

    @SerializedName(HwExerciseConstants.METHOD_PARAM_WORKOUT_ID)
    private String d;

    @SerializedName("operationSchemeId")
    private String e;

    private ern(b bVar) {
        this.d = bVar.c;
        this.c = bVar.b;
        this.e = bVar.d;
        this.b = bVar.f12193a;
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return eta.as();
    }

    public static final class b {

        /* renamed from: a, reason: collision with root package name */
        private Integer f12193a;
        private String b;
        private String c;
        private String d;

        public b c(String str) {
            this.c = str;
            return this;
        }

        public b d(String str) {
            this.b = str;
            return this;
        }

        public b e(String str) {
            this.d = str;
            return this;
        }

        public b b(Integer num) {
            this.f12193a = num;
            return this;
        }

        public ern d() {
            return new ern(this);
        }
    }
}
