package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.hms.support.api.entity.pay.HwPayConstant;
import com.tencent.open.SocialConstants;

/* loaded from: classes9.dex */
public class rji {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("finalPrice")
    private double f16785a;

    @SerializedName("couponId")
    private String b;

    @SerializedName("endTime")
    private int c;

    @SerializedName(SocialConstants.PARAM_APP_DESC)
    private String d;

    @SerializedName(HwPayConstant.KEY_AMOUNT)
    private double e;

    @SerializedName("startTime")
    private int f;

    @SerializedName("originPrice")
    private double h;

    public String a() {
        return this.d;
    }

    public double d() {
        return this.e;
    }
}
