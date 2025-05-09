package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.networkclient.IRequest;
import health.compact.a.GRSManager;

/* loaded from: classes3.dex */
public class bvl implements IRequest {

    @SerializedName("timeZone")
    private String c;

    public void e(String str) {
        this.c = str;
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return GRSManager.a(BaseApplication.getContext()).getUrl("healthCloudUrl") + "/healthExpansion/toDo";
    }

    public String toString() {
        return "TodoTaskBaseReq{, timeZone='" + this.c + "'}";
    }
}
