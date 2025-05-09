package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.hms.support.api.entity.pay.HwPayConstant;

/* loaded from: classes9.dex */
public class mqq {

    @SerializedName("nickName")
    private String c = "";

    @SerializedName(HwPayConstant.KEY_USER_ID)
    private long d;

    @SerializedName("imageURL")
    private String e;

    public void c(long j) {
        this.d = j;
    }

    public void d(String str) {
        this.e = str;
    }

    public void e(String str) {
        this.c = str;
    }
}
