package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.networkclient.IRequest;
import com.huawei.openalliance.ad.constant.JsbMapKeyNames;
import com.huawei.operation.utils.Constants;
import java.util.List;

/* loaded from: classes5.dex */
public class jbl implements IRequest {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName(JsbMapKeyNames.H5_USER_ID)
    private String f13712a;

    @SerializedName(Constants.VMALL_SIGN_INFO)
    private List<jbm> e;

    public void e(String str) {
        this.f13712a = str;
    }

    public void e(List<jbm> list) {
        this.e = list;
    }

    public String toString() {
        return "GetHonorDevicePrivacyReq{userId=" + this.f13712a + ", agrInfo=" + this.e + '}';
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return jah.c().e("domain_honor_device_privacy") + "/agreementservice/user/save";
    }
}
