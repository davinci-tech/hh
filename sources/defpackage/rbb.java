package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;

/* loaded from: classes7.dex */
public class rbb extends CloudCommonReponse {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("userAccount")
    private String f16690a;

    @SerializedName("codeCreatedUserId")
    private Long b;

    public Long c() {
        return this.b;
    }
}
