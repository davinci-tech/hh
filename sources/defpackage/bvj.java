package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;
import java.util.List;

/* loaded from: classes3.dex */
public class bvj extends CloudCommonReponse {

    @SerializedName("toDoContentList")
    private List<gka> c;

    public List<gka> e() {
        return this.c;
    }
}
