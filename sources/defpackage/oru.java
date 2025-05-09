package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;
import java.util.List;

/* loaded from: classes6.dex */
public class oru extends CloudCommonReponse {

    @SerializedName("sportTypeInfo")
    private c e;

    public oru() {
    }

    public oru(c cVar) {
        this.e = cVar;
    }

    public c e() {
        return this.e;
    }

    @Override // com.huawei.hwcloudmodel.model.CloudCommonReponse
    public String toString() {
        return "SportTabOrderData{" + super.toString() + "mSportTypeInfo='" + this.e + "'}";
    }

    public static class c {

        @SerializedName("resourceIdList")
        private List<String> e;

        public c() {
        }

        public c(List<String> list) {
            this.e = list;
        }

        public List<String> e() {
            return this.e;
        }

        public String toString() {
            return "SportTypeInfo{mResourceIds='" + this.e + "'}";
        }
    }
}
