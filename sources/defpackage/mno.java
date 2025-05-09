package defpackage;

import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.WorkoutPackageInfo;
import com.huawei.pluginfitnessadvice.cloudmodelparse.CloudDataParse;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class mno implements CloudDataParse<WorkoutPackageInfo> {
    @Override // com.huawei.pluginfitnessadvice.cloudmodelparse.CloudDataParse
    public List<WorkoutPackageInfo> parse(String str, JSONArray jSONArray) {
        if (jSONArray == null) {
            return new ArrayList(0);
        }
        ArrayList arrayList = new ArrayList(jSONArray.length());
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject optJSONObject = jSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                WorkoutPackageInfo parse = parse(str, optJSONObject);
                LogUtil.a("Suggestion_WorkoutPackageInfoParse", "workoutPackageInfo name:", parse.getWorkoutPackageName());
                arrayList.add(parse);
            }
        }
        return arrayList;
    }

    @Override // com.huawei.pluginfitnessadvice.cloudmodelparse.CloudDataParse
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public WorkoutPackageInfo parse(String str, JSONObject jSONObject) {
        WorkoutPackageInfo workoutPackageInfo = new WorkoutPackageInfo();
        if (jSONObject == null) {
            return workoutPackageInfo;
        }
        workoutPackageInfo.setWorkoutPackageId(jSONObject.optString("workoutPackageId"));
        workoutPackageInfo.setWorkoutPackageName(jSONObject.optString("workoutPackageName"));
        workoutPackageInfo.setCommodityFlag(Integer.valueOf(jSONObject.optInt("commodityFlag")));
        workoutPackageInfo.setTransactionStatus(Integer.valueOf(jSONObject.optInt("transactionStatus")));
        workoutPackageInfo.setWorkoutFinishCount(Integer.valueOf(jSONObject.optInt("workoutFinishCount")));
        workoutPackageInfo.setWorkoutTotalCount(Integer.valueOf(jSONObject.optInt("workoutTotalCount")));
        workoutPackageInfo.setTopicPreviewPicUrl(jSONObject.optString("topicPreviewPicUrl"));
        workoutPackageInfo.setExpireTime(Long.valueOf(jSONObject.optLong("expireTime")));
        workoutPackageInfo.setEnableNewUrl(jSONObject.optInt("enableNewUrl"));
        workoutPackageInfo.setSquarePicture(jSONObject.optString("squarePicture"));
        return workoutPackageInfo;
    }
}
