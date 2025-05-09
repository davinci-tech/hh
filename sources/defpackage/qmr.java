package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.haf.application.BaseApplication;
import com.huawei.hms.support.api.entity.pay.HwPayConstant;
import com.huawei.networkclient.IRequest;
import health.compact.a.GRSManager;

/* loaded from: classes8.dex */
public class qmr implements IRequest {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("customerType")
    private String f16488a;

    @SerializedName("content")
    private String b;

    @SerializedName(HwPayConstant.KEY_AMOUNT)
    private Double c;

    @SerializedName("email")
    private String d;

    @SerializedName("customerName")
    private String e;

    @SerializedName("orderCode")
    private String f;

    @SerializedName("invoiceType")
    private String g;

    @SerializedName("taxNo")
    private String h;

    @SerializedName("locale")
    private String j;

    public void e(String str) {
        this.g = str;
    }

    public void b(String str) {
        this.b = str;
    }

    public void a(String str) {
        this.f16488a = str;
    }

    public void c(String str) {
        this.e = str;
    }

    public void j(String str) {
        this.h = str;
    }

    public void d(String str) {
        this.d = str;
    }

    public void h(String str) {
        this.f = str;
    }

    public void e(Double d) {
        this.c = d;
    }

    public void f(String str) {
        this.j = str;
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return GRSManager.a(BaseApplication.e()).getUrl("tradeService") + "/tradeservice/v1/invoice-request";
    }
}
