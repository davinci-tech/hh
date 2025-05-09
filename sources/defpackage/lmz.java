package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.hms.support.api.entity.pay.HwPayConstant;

/* loaded from: classes5.dex */
public class lmz extends lmy {

    @SerializedName(HwPayConstant.KEY_USER_NAME)
    private String b;

    @SerializedName("timestamp")
    private long c;

    @SerializedName("version")
    private String d;

    @SerializedName("action")
    private int e;

    public lmz(String str, int i, int i2) {
        this.e = i;
        a(str);
        b(i2);
    }

    public void e(String str) {
        this.d = str;
    }

    public void b(String str) {
        this.b = str;
    }
}
