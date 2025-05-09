package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.haf.application.BaseApplication;
import com.huawei.openalliance.ad.db.bean.TemplateStyleRecord;
import defpackage.rjb;
import health.compact.a.GRSManager;

/* loaded from: classes9.dex */
public class rjf extends rjb {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("pagination")
    private rjl f16782a;

    @SerializedName("age")
    private int b;

    @SerializedName("sex")
    private int c;

    @SerializedName("label")
    private String[] d;

    @SerializedName("offeringSource")
    private int e;

    @SerializedName(TemplateStyleRecord.STYLE)
    private String h;

    @SerializedName("timeZone")
    private String i;

    private rjf(a aVar) {
        super(aVar);
        this.c = aVar.f16783a;
        this.b = aVar.b;
        this.h = aVar.g;
        this.d = aVar.e;
        this.f16782a = aVar.d;
        this.i = aVar.h;
        this.e = aVar.c;
    }

    public static final class a extends rjb.d<a> {

        /* renamed from: a, reason: collision with root package name */
        private int f16783a;
        private int b;
        private int c;
        private rjl d;
        private String[] e;
        private String g;
        private String h;

        public a c(int i) {
            this.f16783a = i;
            return this;
        }

        public a e(int i) {
            this.b = i;
            return this;
        }

        public a a(String str) {
            this.g = str;
            return this;
        }

        public a c(String str) {
            this.h = str;
            return this;
        }

        public a e(String[] strArr) {
            this.e = strArr;
            return this;
        }

        public a c(rjl rjlVar) {
            this.d = rjlVar;
            return this;
        }

        public a a(int i) {
            this.c = i;
            return this;
        }

        public rjf c() {
            return new rjf(this);
        }
    }

    @Override // defpackage.rjb, com.huawei.networkclient.IRequest
    public String getUrl() {
        return GRSManager.a(BaseApplication.e()).getUrl("domainHealthrecommendHicloud") + "/mall/targetRecommend";
    }
}
