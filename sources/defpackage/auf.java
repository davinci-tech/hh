package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.networkclient.IRequest;
import health.compact.a.Utils;

/* loaded from: classes3.dex */
public class auf implements IRequest {

    @SerializedName("iVersion")
    private String b;

    @SerializedName("timeZone")
    private String c = jdl.q(System.currentTimeMillis());

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return null;
    }

    public auf() {
        this.b = Utils.o() ? "2" : "3";
    }
}
