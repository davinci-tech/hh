package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;
import java.util.List;

/* loaded from: classes9.dex */
public class jan extends CloudCommonReponse {

    @SerializedName("fileUploadList")
    private List<jam> d;

    public List<jam> d() {
        return this.d;
    }
}
