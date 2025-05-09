package defpackage;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import health.compact.a.CommonUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes9.dex */
public class kut extends kuq {

    @SerializedName("sampleSequenceDataType")
    private kun b;

    @SerializedName("readOptions")
    private a e;

    public int[] m() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(Integer.valueOf(this.b.a()));
        return CommonUtil.b(arrayList);
    }

    @Override // defpackage.kuq
    public Map<Integer, List<JsonObject>> j() {
        HashMap hashMap = new HashMap();
        hashMap.put(Integer.valueOf(this.b.a()), kuf.a().e(this.b.a(), this.b.a()));
        return hashMap;
    }

    public a g() {
        return this.e;
    }

    @Override // defpackage.kuq, defpackage.kul
    public String toString() {
        return "SampleSequenceReadRequest{mType=" + this.b + ", mReadOptions=" + this.e + '}';
    }

    public static class a {

        @SerializedName("withSummary")
        private boolean d;

        @SerializedName("withDetails")
        private boolean e;

        public boolean d() {
            return this.e;
        }

        public String toString() {
            return "SampleSequenceReadOptions{mWithSummary=" + this.d + ", mWithDetails=" + this.e + '}';
        }
    }
}
