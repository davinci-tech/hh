package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.networkclient.IRequest;

/* loaded from: classes9.dex */
public class rjb implements IRequest {

    @SerializedName("deviceId")
    private String b;

    @SerializedName("phoneType")
    private String d;

    @SerializedName("petalOaid")
    private String e;

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return null;
    }

    protected rjb(d dVar) {
        this.d = dVar.c;
        this.e = dVar.f16780a;
        this.b = dVar.e;
    }

    public static class d<T extends d> {

        /* renamed from: a, reason: collision with root package name */
        private String f16780a;
        private String c;
        private String e;

        public T b(String str) {
            this.f16780a = str;
            return this;
        }

        public T d(String str) {
            this.e = str;
            return this;
        }

        public T e(String str) {
            this.c = str;
            return this;
        }
    }
}
