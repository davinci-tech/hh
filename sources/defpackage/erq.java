package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.networkclient.IRequest;
import java.util.List;

/* loaded from: classes3.dex */
public class erq implements IRequest {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("showDetail")
    private Boolean f12196a;

    @SerializedName("workoutInfoQueryParams")
    private List<ffl> e;

    private erq(d dVar) {
        this.e = dVar.c;
        this.f12196a = dVar.e;
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return eta.ar();
    }

    public static final class d {
        private List<ffl> c;
        private Boolean e;

        public d e(List<ffl> list) {
            this.c = list;
            return this;
        }

        public d d(Boolean bool) {
            this.e = bool;
            return this;
        }

        public erq a() {
            return new erq(this);
        }
    }
}
