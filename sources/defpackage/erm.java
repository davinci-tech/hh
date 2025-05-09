package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseConstants;
import com.huawei.networkclient.IRequest;

/* loaded from: classes3.dex */
public class erm implements IRequest {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("version")
    private String f12191a;

    @SerializedName("userDefinedType")
    private Integer b;

    @SerializedName("operationSchemeId")
    private String c;

    @SerializedName("lang")
    private String d;

    @SerializedName(HwExerciseConstants.METHOD_PARAM_WORKOUT_ID)
    private String e;

    private erm(c cVar) {
        this.e = cVar.f12192a;
        this.d = cVar.b;
        this.f12191a = cVar.e;
        this.c = cVar.d;
        this.b = cVar.c;
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return eta.aj();
    }

    public static final class c {

        /* renamed from: a, reason: collision with root package name */
        private String f12192a;
        private String b;
        private Integer c;
        private String d;
        private String e;

        public c d(String str) {
            this.f12192a = str;
            return this;
        }

        public c b(String str) {
            this.b = str;
            return this;
        }

        public c c(String str) {
            this.e = str;
            return this;
        }

        public c e(String str) {
            this.d = str;
            return this;
        }

        public c d(Integer num) {
            this.c = num;
            return this;
        }

        public erm a() {
            return new erm(this);
        }
    }
}
