package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.networkclient.IRequest;

/* loaded from: classes3.dex */
public class erl implements IRequest {

    @SerializedName("workoutPackageId")
    private String b;

    @SerializedName("lang")
    private String d;

    private erl(b bVar) {
        this.d = bVar.e;
        this.b = bVar.f12190a;
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return eta.ap();
    }

    public static final class b {

        /* renamed from: a, reason: collision with root package name */
        private String f12190a;
        private String e;

        public b b(String str) {
            this.e = str;
            return this;
        }

        public b a(String str) {
            this.f12190a = str;
            return this;
        }

        public erl b() {
            return new erl(this);
        }
    }
}
