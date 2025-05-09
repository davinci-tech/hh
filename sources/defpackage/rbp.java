package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;
import java.util.List;

/* loaded from: classes7.dex */
public class rbp extends CloudCommonReponse {

    @SerializedName("npsList")
    private List<rbj> c;

    public List<rbj> a() {
        return this.c;
    }

    @Override // com.huawei.hwcloudmodel.model.CloudCommonReponse
    public String toString() {
        return "NpsInfosRsp{npsList=" + this.c + '}';
    }
}
