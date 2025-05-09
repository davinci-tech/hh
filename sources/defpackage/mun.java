package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.hms.support.api.entity.pay.HwPayConstant;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import java.util.List;

/* loaded from: classes6.dex */
public class mun {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("countryCode")
    private String f15182a;

    @SerializedName("fileInfo")
    private mul b;

    @SerializedName("hitopId")
    private String c;

    @SerializedName(HwPayConstant.KEY_CURRENCY)
    private String d;

    @SerializedName("categoryId")
    private String e;

    @SerializedName("label")
    private String f;

    @SerializedName("previews")
    private List<muo> g;

    @SerializedName("id")
    private String h;

    @SerializedName("layoutType")
    private String i;

    @SerializedName("discountList")
    private List<Object> j;

    @SerializedName("type")
    private String k;

    @SerializedName(ParsedFieldTag.PRICE)
    private String l;

    @SerializedName("productId")
    private String m;

    @SerializedName("symbol")
    private String n;

    @SerializedName("processCover")
    private String o;

    public mul a() {
        return this.b;
    }

    public List<muo> c() {
        return this.g;
    }

    public String b() {
        return this.h;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer(255);
        stringBuffer.append("ShareThemeInfo{hitopId=");
        stringBuffer.append(this.c);
        stringBuffer.append(", label=").append(this.f);
        stringBuffer.append(", id=").append(this.h);
        stringBuffer.append(", fileInfo=").append(this.b);
        stringBuffer.append(", countryCode=").append(this.f15182a);
        stringBuffer.append(", previews=").append(this.g);
        stringBuffer.append(", processCover=").append(this.o);
        stringBuffer.append(", price=").append(this.l);
        stringBuffer.append(", categoryId=").append(this.e);
        stringBuffer.append(", symbol=").append(this.n);
        stringBuffer.append(", currency=").append(this.d);
        stringBuffer.append(", layoutType=").append(this.i);
        stringBuffer.append('}');
        return stringBuffer.toString();
    }
}
