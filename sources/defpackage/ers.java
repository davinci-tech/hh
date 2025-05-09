package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.basefitnessadvice.model.intplan.LeaveInfo;
import com.huawei.networkclient.IRequest;

/* loaded from: classes3.dex */
public class ers implements IRequest {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("subCategory")
    private Integer f12197a;

    @SerializedName("leaveInfo")
    private LeaveInfo c;

    @SerializedName("planId")
    private String d;

    @SerializedName("category")
    private Integer e;

    private ers(c cVar) {
        this.d = cVar.e;
        this.e = cVar.f12198a;
        this.f12197a = cVar.c;
        this.c = cVar.b;
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return eta.aw();
    }

    public static final class c {

        /* renamed from: a, reason: collision with root package name */
        private Integer f12198a;
        private LeaveInfo b;
        private Integer c;
        private String e;

        public c d(String str) {
            this.e = str;
            return this;
        }

        public c e(Integer num) {
            this.f12198a = num;
            return this;
        }

        public c b(Integer num) {
            this.c = num;
            return this;
        }

        public c c(LeaveInfo leaveInfo) {
            this.b = leaveInfo;
            return this;
        }

        public ers c() {
            return new ers(this);
        }
    }
}
