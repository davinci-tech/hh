package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.networkclient.IRequest;

/* loaded from: classes3.dex */
public class esc implements IRequest {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("ids")
    private String f12215a;

    private esc(e eVar) {
        this.f12215a = eVar.d;
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return eta.bi();
    }

    public static final class e {
        private String d;

        public e a(String str) {
            this.d = str;
            return this;
        }

        public esc b() {
            return new esc(this);
        }
    }
}
