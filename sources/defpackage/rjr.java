package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.openalliance.ad.constant.JsbMapKeyNames;
import java.util.List;

/* loaded from: classes9.dex */
public class rjr {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("couponInfo")
    private rji[] f16791a;

    @SerializedName("storeName")
    private String aa;

    @SerializedName("similarity")
    private float ab;

    @SerializedName("storeIcon")
    private rjh ac;

    @SerializedName("storeId")
    private String ad;

    @SerializedName("box")
    private String b;

    @SerializedName("appDeeplinkApi")
    private String c;

    @SerializedName("cpAdzoneId")
    private String d;

    @SerializedName(JsbMapKeyNames.H5_BRAND)
    private String e;

    @SerializedName("giftCouponAmount")
    private int f;

    @SerializedName("detailUrl")
    private rjk g;

    @SerializedName("extendInfo")
    private String h;

    @SerializedName("goodCount")
    private double i;

    @SerializedName("goodRate")
    private double j;

    @SerializedName("name")
    private String k;

    @SerializedName("goodsSpecialId")
    private String l;

    @SerializedName("mainImage")
    private rjh m;

    @SerializedName("needConvert")
    private boolean n;

    @SerializedName("itemId")
    private String o;

    @SerializedName("productScore")
    private double p;

    @SerializedName("priceUnit")
    private String q;

    @SerializedName("productType")
    private String r;

    @SerializedName("platformType")
    private int s;

    @SerializedName("noResult")
    private String t;

    @SerializedName("providerIcon")
    private rjh u;

    @SerializedName("sellingPoints")
    private List<String> v;

    @SerializedName("sales")
    private rjs w;

    @SerializedName("provider")
    private String x;

    @SerializedName("selfSupport")
    private String y;

    public List<String> f() {
        return this.v;
    }

    public String j() {
        return this.k;
    }

    public String h() {
        return this.q;
    }

    public rjk b() {
        return this.g;
    }

    public rjh c() {
        return this.m;
    }

    public String i() {
        return this.x;
    }

    public rji[] e() {
        return this.f16791a;
    }

    public rjs g() {
        return this.w;
    }
}
