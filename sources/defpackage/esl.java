package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.networkclient.IRequest;

/* loaded from: classes3.dex */
public class esl implements IRequest {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("planType")
    private int f12225a;

    @SerializedName("planId")
    private String d;

    private esl(a aVar) {
        this.d = aVar.b;
        this.f12225a = aVar.f12226a;
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return eta.bu();
    }

    public static final class a {

        /* renamed from: a, reason: collision with root package name */
        private int f12226a;
        private String b;

        public a b(String str) {
            this.b = str;
            return this;
        }

        public a e(int i) {
            this.f12226a = i;
            return this;
        }

        public esl a() {
            return new esl(this);
        }
    }

    public int d() {
        return this.f12225a;
    }

    public String a() {
        return this.d;
    }
}
