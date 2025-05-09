package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.operation.utils.Constants;

/* loaded from: classes5.dex */
public class jbm {

    @SerializedName(Constants.VMALL_ARG_TYPE)
    private Integer b;

    @SerializedName("branchId")
    private int c;

    @SerializedName("country")
    private String d;

    @SerializedName("isAgree")
    private boolean e;

    public void c(Integer num) {
        this.b = num;
    }

    public void d(String str) {
        this.d = str;
    }

    public void b(boolean z) {
        this.e = z;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("HonorDevicePrivacy{agrType=");
        stringBuffer.append(this.b);
        stringBuffer.append(", country=").append(this.d);
        stringBuffer.append(", branchId=").append(this.c);
        stringBuffer.append(", isAgree=").append(this.e);
        stringBuffer.append('}');
        return stringBuffer.toString();
    }
}
