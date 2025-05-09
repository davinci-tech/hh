package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.networkclient.IRequest;
import com.huawei.pluginachievement.manager.db.IAchieveDBMgr;

/* loaded from: classes3.dex */
public class eqs implements IRequest {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("pageStart")
    private Integer f12178a;

    @SerializedName("fuzzyKeyWords")
    private String b;

    @SerializedName(IAchieveDBMgr.PARAM_PAGE_SIZE)
    private Integer c;

    @SerializedName("userDefinedType")
    private Integer d;

    @SerializedName("trainingPoints")
    private Integer e;

    private eqs(a aVar) {
        this.f12178a = aVar.e;
        this.c = aVar.f12179a;
        this.e = aVar.c;
        this.b = aVar.b;
        this.d = aVar.d;
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return eta.t();
    }

    public static final class a {

        /* renamed from: a, reason: collision with root package name */
        private Integer f12179a;
        private String b;
        private Integer c;
        private Integer d;
        private Integer e;

        public a a(Integer num) {
            this.e = num;
            return this;
        }

        public a d(Integer num) {
            this.f12179a = num;
            return this;
        }

        public a e(Integer num) {
            this.c = num;
            return this;
        }

        public a c(String str) {
            this.b = str;
            return this;
        }

        public a c(Integer num) {
            this.d = num;
            return this;
        }

        public eqs c() {
            return new eqs(this);
        }
    }
}
