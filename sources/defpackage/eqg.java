package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.health.plan.model.UserFitnessPlanInfo;
import com.huawei.networkclient.IRequest;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;

/* loaded from: classes3.dex */
public class eqg implements IRequest {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("movementTimes")
    private Integer f12160a;

    @SerializedName(ParsedFieldTag.BEGIN_DATE)
    private Long b;

    @SerializedName("difficulty")
    private Integer c;

    @SerializedName("excludedDate")
    private String d;

    @SerializedName("subType")
    private Integer e;

    @SerializedName("weight")
    private Float f;

    @SerializedName("userFitnessPlanInfo")
    private UserFitnessPlanInfo g;

    private eqg(d dVar) {
        this.g = dVar.i;
        this.b = dVar.c;
        this.e = dVar.b;
        this.c = dVar.d;
        this.f12160a = dVar.f12161a;
        this.d = dVar.e;
        this.f = dVar.f;
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return eta.h();
    }

    public static final class d {

        /* renamed from: a, reason: collision with root package name */
        private Integer f12161a;
        private Integer b;
        private Long c;
        private Integer d;
        private String e;
        private Float f;
        private UserFitnessPlanInfo i;

        public d a(UserFitnessPlanInfo userFitnessPlanInfo) {
            this.i = userFitnessPlanInfo;
            return this;
        }

        public d e(Long l) {
            this.c = l;
            return this;
        }

        public d c(Integer num) {
            this.b = num;
            return this;
        }

        public d d(Integer num) {
            this.d = num;
            return this;
        }

        public d e(Integer num) {
            this.f12161a = num;
            return this;
        }

        public d a(String str) {
            this.e = str;
            return this;
        }

        public d e(Float f) {
            this.f = f;
            return this;
        }

        public eqg c() {
            return new eqg(this);
        }
    }
}
