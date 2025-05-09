package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.haf.application.BaseApplication;
import com.huawei.hms.support.api.entity.pay.HwPayConstant;
import com.huawei.networkclient.IRequest;
import health.compact.a.GRSManager;

/* loaded from: classes6.dex */
public class mup implements IRequest {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName(HwPayConstant.KEY_SIGN)
    private String f15183a;

    @SerializedName("listCode")
    private String b;

    @SerializedName("length")
    private String c;

    @SerializedName("chargeFlag")
    private String d;

    @SerializedName("cursor")
    private String e;

    public void a(String str) {
        this.f15183a = str;
    }

    public void c(String str) {
        this.b = str;
    }

    public void e(String str) {
        this.e = str;
    }

    public void d(String str) {
        this.c = str;
    }

    public void b(String str) {
        this.d = str;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("GetThemeListReq{, sign=");
        stringBuffer.append(this.f15183a);
        stringBuffer.append(", listCode=").append(this.b);
        stringBuffer.append(", cursor=").append(this.e);
        stringBuffer.append(", length=").append(this.c);
        stringBuffer.append(", chargeFlag=").append(this.d);
        stringBuffer.append('}');
        return stringBuffer.toString();
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return GRSManager.a(BaseApplication.e()).getUrl("domainThemeCloud") + "/servicesupport/theme/v2/getStaticWallpaperList.do";
    }
}
