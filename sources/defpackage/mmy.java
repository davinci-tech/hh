package defpackage;

import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import com.huawei.pluginfitnessadvice.AtomicAction;
import com.huawei.pluginfitnessadvice.Attribute;
import com.huawei.pluginfitnessadvice.cloudmodelparse.CloudDataParse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class mmy implements CloudDataParse<AtomicAction> {
    @Override // com.huawei.pluginfitnessadvice.cloudmodelparse.CloudDataParse
    public List<AtomicAction> parse(String str, JSONArray jSONArray) {
        if (jSONArray == null) {
            return Collections.EMPTY_LIST;
        }
        ArrayList arrayList = new ArrayList(jSONArray.length());
        for (int i = 0; i < jSONArray.length(); i++) {
            AtomicAction parse = parse(str, jSONArray.optJSONObject(i));
            if (parse != null) {
                arrayList.add(parse);
            }
        }
        return arrayList;
    }

    @Override // com.huawei.pluginfitnessadvice.cloudmodelparse.CloudDataParse
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public AtomicAction parse(String str, JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        AtomicAction atomicAction = new AtomicAction();
        atomicAction.setId(jSONObject.optString("actionId"));
        atomicAction.setName(jSONObject.optString("name"));
        atomicAction.setModifiedTime(jSONObject.optLong(ParsedFieldTag.TASK_MODIFY_TIME));
        atomicAction.setCreateTime(jSONObject.optLong("createTime"));
        atomicAction.setDescription(jSONObject.optString("describe"));
        atomicAction.setActionSportType(jSONObject.optInt(ParsedFieldTag.ACTION_TYPE));
        atomicAction.setOrchestrationType(jSONObject.optInt("scheduleType"));
        atomicAction.setSortWeight(jSONObject.optInt("sortWeight"));
        atomicAction.setDifficulty(jSONObject.optInt("difficulty"));
        atomicAction.setTrainingPoints(mnh.a(jSONObject.optJSONArray("trainingPoints"), Attribute[].class));
        atomicAction.setVersion(jSONObject.optString("version"));
        atomicAction.setDefaultTargetType(jSONObject.optInt("measurement"));
        atomicAction.setLan(str);
        atomicAction.setShowInActionLibrary(jSONObject.optInt("showInActionLibrary"));
        return atomicAction;
    }
}
