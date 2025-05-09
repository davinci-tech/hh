package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.networkclient.IRequest;

/* loaded from: classes7.dex */
public class eqx implements IRequest {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("actionId")
    private String f12183a;

    public eqx(String str) {
        this.f12183a = str;
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return eta.ac();
    }
}
