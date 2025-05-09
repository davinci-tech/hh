package defpackage;

import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.cloudmodelparse.CloudDataParse;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class mnj implements CloudDataParse<mmv> {
    @Override // com.huawei.pluginfitnessadvice.cloudmodelparse.CloudDataParse
    public List<mmv> parse(String str, JSONArray jSONArray) {
        if (jSONArray == null) {
            return new ArrayList(0);
        }
        ArrayList arrayList = new ArrayList(jSONArray.length());
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject optJSONObject = jSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                mmv parse = parse(str, optJSONObject);
                LogUtil.a("Suggestion_OrderItemParse", "orderItem id = ", parse.c(), " type = ", parse.a(), " index = ", parse.e(), " newUseWorkoutTime = ", parse.d());
                arrayList.add(parse);
            }
        }
        return arrayList;
    }

    @Override // com.huawei.pluginfitnessadvice.cloudmodelparse.CloudDataParse
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public mmv parse(String str, JSONObject jSONObject) {
        mmv mmvVar = new mmv();
        if (jSONObject == null) {
            return mmvVar;
        }
        mmvVar.c(jSONObject.optString("id"));
        mmvVar.a(Integer.valueOf(jSONObject.optInt("type")));
        mmvVar.c(Integer.valueOf(jSONObject.optInt("index")));
        mmvVar.a(Long.valueOf(jSONObject.optLong("newUseWorkoutTime")));
        return mmvVar;
    }
}
