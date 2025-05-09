package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.networkclient.IRequest;
import java.util.List;

/* loaded from: classes7.dex */
public class eqi implements IRequest {

    @SerializedName("idList")
    private List<Integer> e;

    private eqi(e eVar) {
        this.e = eVar.f12163a;
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return eta.o();
    }

    public static final class e {

        /* renamed from: a, reason: collision with root package name */
        private List<Integer> f12163a;

        public e c(List<Integer> list) {
            this.f12163a = list;
            return this;
        }

        public eqi e() {
            return new eqi(this);
        }
    }
}
