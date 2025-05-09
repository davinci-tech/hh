package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.networkclient.IRequest;
import com.huawei.openalliance.ad.constant.JsbMapKeyNames;
import java.util.List;

/* loaded from: classes5.dex */
public class jbi implements IRequest {

    @SerializedName(JsbMapKeyNames.H5_USER_ID)
    private String b;

    @SerializedName("agrInfo")
    private List<jbd> d;

    @SerializedName("obtainVersion")
    private boolean e;

    public void d(String str) {
        this.b = str;
    }

    public void e(boolean z) {
        this.e = z;
    }

    public void a(List<jbd> list) {
        this.d = list;
    }

    public String toString() {
        return "GetHonorDevicePrivacyReq{userId=" + this.b + ", obtainVersion=" + this.e + ", agrInfo=" + this.d + '}';
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return jah.c().e("domain_honor_device_privacy") + "/agreementservice/user/query";
    }
}
