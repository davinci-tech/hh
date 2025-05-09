package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.hms.support.feature.result.CommonConstant;
import java.util.List;

/* loaded from: classes3.dex */
public class dxe {

    @SerializedName("labelThresholdValue")
    private List<e> b;

    @SerializedName("deliver")
    private int c;

    @SerializedName("chooseNum")
    private int e;

    public List<e> b() {
        return this.b;
    }

    public static class e {

        /* renamed from: a, reason: collision with root package name */
        @SerializedName(CommonConstant.KEY_DISPLAY_NAME)
        private String f11875a;

        @SerializedName("description")
        private String c;

        @SerializedName("thresholdValue")
        private String d;

        @SerializedName("technologyCaliber")
        private String e;

        public String e() {
            return this.d;
        }

        public String d() {
            return this.e;
        }
    }
}
