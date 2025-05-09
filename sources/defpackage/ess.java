package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.networkclient.IRequest;
import java.util.List;

/* loaded from: classes3.dex */
public class ess implements IRequest {

    @SerializedName("planId")
    private String b;

    @SerializedName("runDate")
    private List<Integer> d;

    @SerializedName("exeDate")
    private List<Integer> e;

    private ess(e eVar) {
        this.b = eVar.b;
        this.d = eVar.d;
        this.e = eVar.c;
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return eta.ca();
    }

    public static final class e {
        private String b;
        private List<Integer> c;
        private List<Integer> d;

        public e b(String str) {
            this.b = str;
            return this;
        }

        public e d(List<Integer> list) {
            this.d = list;
            return this;
        }

        public e b(List<Integer> list) {
            this.c = list;
            return this;
        }

        public ess d() {
            return new ess(this);
        }
    }
}
