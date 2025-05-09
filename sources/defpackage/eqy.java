package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.networkclient.IRequest;
import com.huawei.pluginachievement.manager.db.IAchieveDBMgr;

/* loaded from: classes3.dex */
public class eqy implements IRequest {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName(IAchieveDBMgr.PARAM_PAGE_SIZE)
    private Integer f12184a;

    @SerializedName("planSource")
    private Integer b;

    @SerializedName("planCategory")
    private Integer c;

    @SerializedName("pageStart")
    private Integer e;

    private eqy(c cVar) {
        this.e = cVar.b;
        this.f12184a = cVar.d;
        this.b = cVar.c;
        this.c = cVar.e;
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return eta.ab();
    }

    public static final class c {
        private Integer b;
        private Integer c;
        private Integer d;
        private Integer e;

        public c d(Integer num) {
            this.b = num;
            return this;
        }

        public c a(Integer num) {
            this.d = num;
            return this;
        }

        public c e(Integer num) {
            this.c = num;
            return this;
        }

        public c b(Integer num) {
            this.e = num;
            return this;
        }

        public eqy c() {
            return new eqy(this);
        }
    }
}
