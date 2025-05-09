package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.networkclient.IRequest;

/* loaded from: classes3.dex */
public class eqk implements IRequest {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("type")
    private Integer f12166a;

    @SerializedName("logId")
    private Integer c;

    private eqk(b bVar) {
        this.c = bVar.d;
        this.f12166a = bVar.c;
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return eta.n();
    }

    public static final class b {
        private Integer c;
        private Integer d;

        public b d(Integer num) {
            this.d = num;
            return this;
        }

        public b e(Integer num) {
            this.c = num;
            return this;
        }

        public eqk a() {
            return new eqk(this);
        }
    }
}
