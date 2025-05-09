package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.networkclient.IRequest;
import com.huawei.pluginachievement.manager.db.IAchieveDBMgr;

/* loaded from: classes3.dex */
public class eru implements IRequest {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("pageStart")
    private Integer f12200a;

    @SerializedName(IAchieveDBMgr.PARAM_PAGE_SIZE)
    private Integer e;

    private eru(e eVar) {
        this.f12200a = eVar.d;
        this.e = eVar.b;
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return eta.ba();
    }

    public static final class e {
        private Integer b;
        private Integer d;

        public e d(Integer num) {
            this.d = num;
            return this;
        }

        public e c(Integer num) {
            this.b = num;
            return this;
        }

        public eru e() {
            return new eru(this);
        }
    }
}
