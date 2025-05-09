package defpackage;

import com.google.gson.Gson;
import com.huawei.pluginfitnessadvice.EquipmentWorkoutAction;
import com.huawei.pluginfitnessadvice.cloudmodelparse.CloudDataParse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class mne implements CloudDataParse<EquipmentWorkoutAction> {
    @Override // com.huawei.pluginfitnessadvice.cloudmodelparse.CloudDataParse
    public List<EquipmentWorkoutAction> parse(String str, JSONArray jSONArray) {
        if (jSONArray == null) {
            return Collections.EMPTY_LIST;
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
    public EquipmentWorkoutAction parse(String str, JSONObject jSONObject) {
        return (EquipmentWorkoutAction) new Gson().fromJson(jSONObject.toString(), EquipmentWorkoutAction.class);
    }
}
