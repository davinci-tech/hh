package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;
import java.util.List;

/* loaded from: classes5.dex */
public class jae extends CloudCommonReponse {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("bucketId")
    private List<String> f13698a;

    @SerializedName("strategyId")
    private List<String> b;

    @SerializedName("strategyName")
    private List<String> c;

    public List<String> e() {
        return this.b;
    }

    public List<String> b() {
        return this.c;
    }

    public List<String> a() {
        return this.f13698a;
    }

    public static class d {

        @SerializedName("arr_strategy_id")
        private List<String> b;

        @SerializedName("arr_bucket_id")
        private List<String> c;

        public d(List<String> list, List<String> list2) {
            this.b = list;
            this.c = list2;
        }

        public d(jae jaeVar) {
            this.b = jaeVar.b;
            this.c = jaeVar.f13698a;
        }

        public List<String> a() {
            return this.b;
        }

        public List<String> b() {
            return this.c;
        }

        public String toString() {
            return "AbTestBiInfo{strategyId=" + this.b + ", bucketId=" + this.c + '}';
        }
    }
}
