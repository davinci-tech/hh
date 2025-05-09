package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.hms.support.api.entity.common.CommonPickerConstant;
import com.huawei.hms.support.api.entity.pay.HwPayConstant;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;

/* loaded from: classes6.dex */
public class njo {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("extendInfo")
    private String f15334a;

    @SerializedName("userType")
    private String ab;

    @SerializedName("validDay")
    private String ac;

    @SerializedName("discountPrice")
    private String b;

    @SerializedName(HwPayConstant.KEY_CURRENCY)
    private String c;

    @SerializedName("guideText")
    private String d;

    @SerializedName("canRenewFlag")
    private String e;

    @SerializedName("maxAge")
    private String f;

    @SerializedName("isReceivedByCondition")
    private String g;

    @SerializedName("markUrl")
    private String h;

    @SerializedName("minAge")
    private String i;

    @SerializedName(ParsedFieldTag.PRICE)
    private String j;

    @SerializedName("productCode")
    private String k;

    @SerializedName(HwPayConstant.KEY_PRODUCTDESC)
    private String l;

    @SerializedName("priceLevelExtend")
    private String m;

    @SerializedName("productInterpretation")
    private String n;

    @SerializedName(HwPayConstant.KEY_PRODUCTNAME)
    private String o;

    @SerializedName("promotiondesc")
    private String p;

    @SerializedName("promotionurl")
    private String q;

    @SerializedName(CommonPickerConstant.RequestParams.KEY_REDIRECT_URL)
    private String r;

    @SerializedName("productType")
    private String s;

    @SerializedName("relProductCode")
    private String t;

    @SerializedName("renewCode")
    private String u;

    @SerializedName("symbol")
    private String v;

    @SerializedName("sendCount")
    private String w;

    @SerializedName("relProductDes")
    private String x;

    @SerializedName("renewPrice")
    private String y;

    public String b() {
        return this.k;
    }

    public String e() {
        return this.g;
    }
}
