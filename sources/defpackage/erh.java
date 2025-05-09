package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.networkclient.IRequest;

/* loaded from: classes8.dex */
public class erh implements IRequest {

    @SerializedName("courseCategory")
    private Integer c;

    private erh(d dVar) {
        this.c = dVar.b;
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return eta.ai();
    }

    public static final class d {
        private Integer b;

        public d a(Integer num) {
            this.b = num;
            return this;
        }

        public erh d() {
            return new erh(this);
        }
    }
}
