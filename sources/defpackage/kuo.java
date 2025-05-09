package defpackage;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import health.compact.a.CommonUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes9.dex */
public class kuo extends kuq {

    @SerializedName("dataSourceId")
    private String b;

    @SerializedName("fields")
    private Map<String, Object> c;

    @SerializedName("samplePointDataType")
    private List<kun> e;

    public int[] g() {
        ArrayList arrayList = new ArrayList();
        Iterator<kun> it = this.e.iterator();
        while (it.hasNext()) {
            arrayList.add(Integer.valueOf(it.next().a()));
        }
        return CommonUtil.b(arrayList);
    }

    @Override // defpackage.kuq
    public Map<Integer, List<JsonObject>> j() {
        HashMap hashMap = new HashMap();
        int[] b = CommonUtil.b(kuf.a().e(g()));
        Map<Integer, Integer> b2 = kuf.a().b(g());
        for (int i : b) {
            hashMap.put(Integer.valueOf(i), kuf.a().e(b2.get(Integer.valueOf(i)).intValue(), i));
        }
        return hashMap;
    }

    @Override // defpackage.kuq, defpackage.kul
    public String toString() {
        return super.toString() + "SamplePointReadRequest{mType=" + this.e + ", mFields=" + this.c + '}';
    }
}
