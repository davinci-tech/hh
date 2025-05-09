package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.networkclient.IRequest;
import com.huawei.pluginachievement.manager.db.IAchieveDBMgr;

/* loaded from: classes8.dex */
public class eqv implements IRequest {

    @SerializedName("operType")
    private Integer b;

    @SerializedName("infoType")
    private Integer c;

    @SerializedName(IAchieveDBMgr.PARAM_PAGE_SIZE)
    private Integer d;

    @SerializedName("pageNo")
    private Integer e;

    private eqv(b bVar) {
        this.e = bVar.b;
        this.d = bVar.c;
        this.b = bVar.d;
        this.c = bVar.f12182a;
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return eta.w();
    }

    public static final class b {

        /* renamed from: a, reason: collision with root package name */
        private Integer f12182a;
        private Integer b;
        private Integer c;
        private Integer d;

        public b e(Integer num) {
            this.b = num;
            return this;
        }

        public b b(Integer num) {
            this.c = num;
            return this;
        }

        public b d(Integer num) {
            this.d = num;
            return this;
        }

        public b c(Integer num) {
            this.f12182a = num;
            return this;
        }

        public eqv b() {
            return new eqv(this);
        }
    }
}
