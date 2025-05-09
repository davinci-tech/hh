package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;
import java.util.List;

/* loaded from: classes7.dex */
public class rhv extends CloudCommonReponse {

    @SerializedName("totalCount")
    private int c;

    @SerializedName("userLabelConfig")
    private List<rhz> d;

    public List<rhz> b() {
        return this.d;
    }
}
