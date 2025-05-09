package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.basefitnessadvice.model.intplan.AdjustmentExercisePlan;
import com.huawei.basefitnessadvice.model.intplan.DayInfo;
import com.huawei.networkclient.IRequest;
import java.util.List;

/* loaded from: classes3.dex */
public class esr implements IRequest {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("planId")
    private String f12233a;

    @SerializedName("adjustmentExercisePlan")
    private AdjustmentExercisePlan b;

    @SerializedName("category")
    private Integer c;

    @SerializedName("days")
    private List<DayInfo> d;

    @SerializedName("operationType")
    private Integer e;

    @SerializedName("subCategory")
    private Integer j;

    private esr(c cVar) {
        this.f12233a = cVar.f12234a;
        this.c = cVar.c;
        this.j = cVar.f;
        this.e = cVar.e;
        this.d = cVar.d;
        this.b = cVar.b;
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return eta.bz();
    }

    public static final class c {

        /* renamed from: a, reason: collision with root package name */
        private String f12234a;
        private AdjustmentExercisePlan b;
        private Integer c;
        private List<DayInfo> d;
        private Integer e;
        private Integer f;

        public c b(String str) {
            this.f12234a = str;
            return this;
        }

        public c c(Integer num) {
            this.c = num;
            return this;
        }

        public c d(Integer num) {
            this.f = num;
            return this;
        }

        public c b(Integer num) {
            this.e = num;
            return this;
        }

        public c a(List<DayInfo> list) {
            this.d = list;
            return this;
        }

        public c a(AdjustmentExercisePlan adjustmentExercisePlan) {
            this.b = adjustmentExercisePlan;
            return this;
        }

        public esr c() {
            return new esr(this);
        }
    }
}
