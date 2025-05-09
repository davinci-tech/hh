package defpackage;

import com.huawei.pluginfitnessadvice.RoadBook;
import com.huawei.pluginfitnessadvice.cloudmodelparse.CloudDataParse;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class mnk implements CloudDataParse<RoadBook> {
    @Override // com.huawei.pluginfitnessadvice.cloudmodelparse.CloudDataParse
    public List<RoadBook> parse(String str, JSONArray jSONArray) {
        if (jSONArray == null) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList(jSONArray.length());
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject optJSONObject = jSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                arrayList.add(parse(str, optJSONObject));
            }
        }
        return arrayList;
    }

    @Override // com.huawei.pluginfitnessadvice.cloudmodelparse.CloudDataParse
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public RoadBook parse(String str, JSONObject jSONObject) {
        if (jSONObject == null) {
            return new RoadBook();
        }
        return (RoadBook) moj.e(jSONObject.toString(), RoadBook.class);
    }
}
