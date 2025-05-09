package defpackage;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.gson.annotations.SerializedName;
import com.huawei.networkclient.IRequest;

/* loaded from: classes3.dex */
public class eqc implements IRequest {

    @SerializedName("planType")
    private Integer b;

    @SerializedName("pb")
    private Integer c;

    @SerializedName("pbType")
    private Integer d;

    @SerializedName(TypedValues.CycleType.S_WAVE_PERIOD)
    private Integer e;

    private eqc(b bVar) {
        this.d = bVar.e;
        this.c = bVar.b;
        this.b = bVar.c;
        this.e = bVar.d;
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return eta.c();
    }

    public static final class b {
        private Integer b;
        private Integer c;
        private Integer d;
        private Integer e;

        public b a(Integer num) {
            this.e = num;
            return this;
        }

        public b b(Integer num) {
            this.b = num;
            return this;
        }

        public b d(Integer num) {
            this.c = num;
            return this;
        }

        public b e(Integer num) {
            this.d = num;
            return this;
        }

        public eqc e() {
            return new eqc(this);
        }
    }
}
