package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;
import java.util.List;

/* loaded from: classes3.dex */
public class emz extends CloudCommonReponse {

    @SerializedName("totalSize")
    private int c;

    @SerializedName("hotPathOperationInfos")
    private List<enf> d;

    @SerializedName("citySupportPathAttribute")
    private emj e;

    public List<enf> e() {
        return this.d;
    }

    public int b() {
        return this.c;
    }

    public emj c() {
        return this.e;
    }

    @Override // com.huawei.hwcloudmodel.model.CloudCommonReponse
    public String toString() {
        return "GetHotPathsRsp{hotPathOperationInfos=" + this.d + ", totalSize=" + this.c + ", citySupportPathAttribute=" + this.e + '}';
    }
}
