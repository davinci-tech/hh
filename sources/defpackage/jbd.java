package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.operation.utils.Constants;

/* loaded from: classes5.dex */
public class jbd {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("country")
    private String f13708a;

    @SerializedName(Constants.VMALL_ARG_TYPE)
    private Integer c;

    @SerializedName("branchId")
    private int d;

    public void c(Integer num) {
        this.c = num;
    }

    public void d(String str) {
        this.f13708a = str;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("HonorDevicePrivacy{agrType=");
        stringBuffer.append(this.c);
        stringBuffer.append(", country=").append(this.f13708a);
        stringBuffer.append(", branchId=").append(this.d);
        stringBuffer.append('}');
        return stringBuffer.toString();
    }
}
