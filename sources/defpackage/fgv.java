package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes7.dex */
public class fgv extends CloudCommonReponse {

    @SerializedName("voiceBroadcastLists")
    private List<fgy> e = new ArrayList();

    public List<fgy> d() {
        return this.e;
    }

    @Override // com.huawei.hwcloudmodel.model.CloudCommonReponse
    public String toString() {
        return "VoiceBroadcastListResponse{mVoiceBroadcastList=" + this.e + ", super='" + super.toString() + "'}";
    }
}
