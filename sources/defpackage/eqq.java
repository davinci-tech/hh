package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.networkclient.IRequest;
import java.util.List;

/* loaded from: classes8.dex */
public class eqq implements IRequest {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("actionIdList")
    private List<String> f12175a;

    @SerializedName("sex")
    private int c;

    private eqq(a aVar) {
        this.f12175a = aVar.b;
        this.c = aVar.e;
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return eta.q();
    }

    public static final class a {
        private List<String> b;
        private int e;

        public a b(List<String> list) {
            this.b = list;
            return this;
        }

        public a e(int i) {
            this.e = i;
            return this;
        }

        public eqq e() {
            return new eqq(this);
        }
    }
}
