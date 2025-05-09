package defpackage;

import com.huawei.health.sport.model.WorkoutRecord;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwsmartinteractmgr.data.ActivitySimple;
import com.huawei.hwsmartinteractmgr.data.SmartResponseWrapper;
import com.huawei.hwsmartinteractmgr.smarthttpmodel.smarthttpparser.SmartHttpBaseParser;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import com.huawei.ui.thirdpartservice.syncdata.constants.SyncDataConstant;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class kwi extends SmartHttpBaseParser<List<ActivitySimple>> {
    public kwi(int i) {
        super(i);
    }

    @Override // com.huawei.hwsmartinteractmgr.smarthttpmodel.smarthttpparser.SmartHttpBaseParser
    public SmartResponseWrapper<List<ActivitySimple>> parseDistinctResponse(SmartResponseWrapper<List<ActivitySimple>> smartResponseWrapper) {
        LogUtil.a("SMART_ActivitiesParser", "enter parseDistinctResponse");
        try {
            smartResponseWrapper.setResponse(a(new JSONObject(this.mResolve).optJSONArray("activities")));
            return smartResponseWrapper;
        } catch (JSONException e) {
            LogUtil.b("SMART_ActivitiesParser", "Json data error! parse activities json:", e.getMessage());
            smartResponseWrapper.setResponseCode(101);
            smartResponseWrapper.setResponseDesc("parse json failed");
            return smartResponseWrapper;
        }
    }

    private List<ActivitySimple> a(JSONArray jSONArray) {
        if (jSONArray == null) {
            LogUtil.a("SMART_ActivitiesParser", "jsonArray is null!");
            return new ArrayList();
        }
        int length = jSONArray.length();
        ArrayList arrayList = new ArrayList(length);
        for (int i = 0; i < length; i++) {
            ActivitySimple c = c(jSONArray.optJSONObject(i));
            if (c != null) {
                arrayList.add(c);
            }
        }
        return arrayList;
    }

    private ActivitySimple c(JSONObject jSONObject) {
        if (jSONObject == null) {
            LogUtil.a("SMART_ActivitiesParser", "jsonActivity is null!");
            return null;
        }
        ActivitySimple activitySimple = new ActivitySimple();
        try {
            activitySimple.setActivityName(jSONObject.getString("activityName"));
            activitySimple.setWordDesc(jSONObject.getString("wordDesc"));
            activitySimple.setActivityId(jSONObject.getString("activityId"));
            activitySimple.setStartDate(jSONObject.getString(ParsedFieldTag.BEGIN_DATE));
            activitySimple.setEndDate(jSONObject.getString("endDate"));
            activitySimple.setJoinNum(jSONObject.getString("numberOfPeople"));
            activitySimple.setActivityStatus(jSONObject.getInt("activityStatus"));
            activitySimple.setActivityType(jSONObject.getInt(SyncDataConstant.BI_KEY_ACTIVITY_TYPE));
            if (jSONObject.has(WorkoutRecord.Extend.COURSE_TARGET_VALUE)) {
                activitySimple.setTargetValue((int) jSONObject.getDouble(WorkoutRecord.Extend.COURSE_TARGET_VALUE));
            }
            activitySimple.setDescription(jSONObject.getString("description"));
            activitySimple.setLastModifyTime(jSONObject.getString("lastModifyTime"));
            activitySimple.setActivityPosition(jSONObject.getInt("activityPosition"));
            LogUtil.c("SMART_ActivitiesParser", "getSingleActivity:", activitySimple.toString());
            return activitySimple;
        } catch (JSONException e) {
            LogUtil.b("SMART_ActivitiesParser", "Json data error! parse single activity:", e.getMessage());
            return null;
        }
    }
}
