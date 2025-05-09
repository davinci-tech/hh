package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.basefitnessadvice.model.intplan.LeaveInfo;
import com.huawei.networkclient.IRequest;

/* loaded from: classes3.dex */
public class eqd implements IRequest {

    @SerializedName("leaveInfo")
    private LeaveInfo b;

    @SerializedName("planId")
    private String c;

    @SerializedName("subCategory")
    private Integer d;

    @SerializedName("category")
    private Integer e;

    private eqd(b bVar) {
        this.c = bVar.b;
        this.e = bVar.d;
        this.d = bVar.f12156a;
        this.b = bVar.c;
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return eta.b();
    }

    public static final class b {

        /* renamed from: a, reason: collision with root package name */
        private Integer f12156a;
        private String b;
        private LeaveInfo c;
        private Integer d;

        public b b(String str) {
            this.b = str;
            return this;
        }

        public b a(Integer num) {
            this.d = num;
            return this;
        }

        public b e(Integer num) {
            this.f12156a = num;
            return this;
        }

        public b b(LeaveInfo leaveInfo) {
            this.c = leaveInfo;
            return this;
        }

        public eqd d() {
            return new eqd(this);
        }
    }
}
