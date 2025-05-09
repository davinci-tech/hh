package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;
import java.util.List;

/* loaded from: classes7.dex */
public class rbh extends CloudCommonReponse {

    @SerializedName("memberList")
    private List<rbk> c;

    public List<rbk> c() {
        return this.c;
    }
}
