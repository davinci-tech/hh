package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.networkclient.IRequest;

/* loaded from: classes3.dex */
public class cdz implements IRequest {

    @SerializedName("queryDate")
    private int c;

    @SerializedName("timeZone")
    private String d;

    @SerializedName("queryType")
    private String e;

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        if ("weekEncourage".equals(this.e) || "monthEncourage".equals(this.e)) {
            return cef.b();
        }
        return cef.a();
    }

    private cdz(c cVar) {
        this.e = cVar.b;
        this.c = cVar.c;
        this.d = ggl.b();
    }

    public static final class c {
        private String b;
        private int c;

        public c a(String str) {
            this.b = str;
            return this;
        }

        public c e(int i) {
            this.c = i;
            return this;
        }

        public cdz e() {
            return new cdz(this);
        }
    }
}
