package defpackage;

import com.huawei.basefitnessadvice.model.Topic;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.cloudmodelparse.CloudDataParse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class mni implements CloudDataParse<Topic> {
    @Override // com.huawei.pluginfitnessadvice.cloudmodelparse.CloudDataParse
    public List<Topic> parse(String str, JSONArray jSONArray) {
        if (jSONArray == null) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(jSONArray.length());
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject optJSONObject = jSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                Topic parse = parse(str, optJSONObject);
                LogUtil.a("Suggestion_TopicDataParse", "topic name:", parse.acquireName());
                arrayList.add(parse);
            }
        }
        return arrayList;
    }

    @Override // com.huawei.pluginfitnessadvice.cloudmodelparse.CloudDataParse
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public Topic parse(String str, JSONObject jSONObject) {
        Topic topic = new Topic();
        if (jSONObject == null) {
            return topic;
        }
        topic.saveId(jSONObject.optInt("id"));
        topic.saveName(jSONObject.optString("name"));
        topic.saveNameId(jSONObject.optInt("nameId"));
        topic.saveVersion(jSONObject.optString("version"));
        topic.saveSerialNum(jSONObject.optString("serialNum"));
        topic.saveDisplayOrder(jSONObject.optInt("displayorder"));
        topic.saveWorkoutPreviewNum(jSONObject.optInt("workoutPreviewNum"));
        topic.saveWorkoutList(mnf.b(jSONObject));
        topic.setSubtitle(jSONObject.optString("subTitle"));
        topic.setTopicBackImgUrl(jSONObject.optString("topicBackImgUrl"));
        topic.setDescription(jSONObject.optString("description"));
        topic.setCourseCategory(jSONObject.optInt("courseCategory"));
        return topic;
    }
}
