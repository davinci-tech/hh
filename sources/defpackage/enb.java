package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;
import java.util.List;

/* loaded from: classes3.dex */
public class enb extends CloudCommonReponse {

    @SerializedName("histories")
    private List<enj> c;

    public List<enj> e() {
        return this.c;
    }
}
