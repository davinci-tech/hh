package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;
import java.util.List;

/* loaded from: classes7.dex */
public class jas extends CloudCommonReponse {

    @SerializedName("userLabels")
    private List<jau> b;

    public List<jau> b() {
        return this.b;
    }
}
