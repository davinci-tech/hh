package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.hihealth.model.HealthTrendReport;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;
import java.util.List;

/* loaded from: classes7.dex */
public class ikj extends CloudCommonReponse {

    @SerializedName("healthTrendReports")
    private List<HealthTrendReport> c;

    public List<HealthTrendReport> b() {
        return this.c;
    }

    @Override // com.huawei.hwcloudmodel.model.CloudCommonReponse
    public String toString() {
        return "HealthTrendResponse{resultCode=" + getResultCode() + ", resultDesc='" + getResultDesc() + "', healthTrendReports=" + this.c + "}";
    }
}
