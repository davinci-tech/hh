package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.networkclient.IRequest;

/* loaded from: classes7.dex */
public class ero implements IRequest {

    @SerializedName("pageNum")
    private Integer b;

    @SerializedName("lang")
    private String d;

    @SerializedName("topicId")
    private Integer e;

    private ero(c cVar) {
        this.b = cVar.c;
        this.e = cVar.e;
        this.d = cVar.b;
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return eta.aq();
    }

    public static final class c {
        private String b;
        private Integer c;
        private Integer e;

        public c d(Integer num) {
            this.c = num;
            return this;
        }

        public c e(Integer num) {
            this.e = num;
            return this;
        }

        public c a(String str) {
            this.b = str;
            return this;
        }

        public ero b() {
            return new ero(this);
        }
    }
}
