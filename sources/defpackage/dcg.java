package defpackage;

import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.huawei.health.sport.model.WorkoutRecord;
import com.huawei.operation.ble.BleConstants;
import com.huawei.operation.utils.Constants;
import com.huawei.pluginachievement.manager.model.MedalConstants;
import defpackage.dbn;
import health.compact.a.LogUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class dcg {

    /* renamed from: a, reason: collision with root package name */
    private static final Map<String, dbn.a> f11579a = new ConcurrentHashMap();

    public static void d(dbn.a aVar) {
        if (aVar != null) {
            String productId = aVar.getProductId();
            if (TextUtils.isEmpty(productId)) {
                return;
            }
            f11579a.put(productId, aVar);
        }
    }

    public static dbn.a d(String str) {
        return f11579a.get(str);
    }

    public static boolean a(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.c("MassageGunConfigMap", "parseJsonFile urlJson is null ");
            return false;
        }
        LogUtil.c("MassageGunConfigMap", "parseJsonFile urlJson = ", str);
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (!jSONObject.has("courseRecommended")) {
                return false;
            }
            JSONArray jSONArray = jSONObject.getJSONArray("courseRecommended");
            for (int i = 0; i < jSONArray.length(); i++) {
                if (d(jSONArray.getJSONObject(i), new dbn.a())) {
                    return false;
                }
            }
            return true;
        } catch (JSONException unused) {
            LogUtil.e("MassageGunConfigMap", "parseJsonFile JSONException");
            return false;
        }
    }

    private static boolean d(JSONObject jSONObject, dbn.a aVar) throws JSONException {
        String str;
        JSONArray optJSONArray;
        if (jSONObject.has("productId")) {
            str = jSONObject.getString("productId");
            aVar.setProductId(str);
        } else {
            str = "";
        }
        if (!jSONObject.has("recommendedCourseList") && !jSONObject.has("filterConfiguration")) {
            LogUtil.c("MassageGunConfigMap", "RECOMMENDED_COURSE_LIST or FILTER_CONFIGURATION is null");
            return true;
        }
        if (jSONObject.has("recommendedCourseList") && (optJSONArray = jSONObject.optJSONArray("recommendedCourseList")) != null && optJSONArray.length() != 0) {
            aVar.setRecommendedCourseList(b(optJSONArray));
        }
        boolean b = b(aVar, jSONObject.getJSONObject("filterConfiguration"));
        if (!TextUtils.isEmpty(str) && aVar.getRecommendedCourseList() != null && aVar.getRecommendedCourseList().size() != 0 && b) {
            d(aVar);
            return false;
        }
        LogUtil.c("MassageGunConfigMap", "productId or RecommendedCourseList is null");
        return true;
    }

    private static boolean b(dbn.a aVar, JSONObject jSONObject) {
        LogUtil.c("MassageGunConfigMap", "handleFilterConfiguration");
        if (jSONObject == null || aVar == null) {
            LogUtil.c("MassageGunConfigMap", "jsonObject or courseRecommended is null");
            return false;
        }
        dbn.a.e eVar = new dbn.a.e();
        try {
            if (jSONObject.has("allDataSortCondition")) {
                JSONObject jSONObject2 = jSONObject.getJSONObject("allDataSortCondition");
                dbn.a.b bVar = new dbn.a.b();
                if (jSONObject2.has("paramsList")) {
                    a(jSONObject2.getJSONArray("paramsList"), bVar, (dbn.a.i) null);
                }
                if (jSONObject2.has("sortRules")) {
                    bVar.setSortRules(jSONObject2.getString("sortRules"));
                }
                if (jSONObject2.has(MedalConstants.EVENT_KEY)) {
                    bVar.setKey(jSONObject2.getString(MedalConstants.EVENT_KEY));
                }
                eVar.setAllDataSortCondition(bVar);
            }
            if (jSONObject.has("filterCondition")) {
                eVar.setFilterConditions(a(jSONObject.getJSONArray("filterCondition")));
            }
            aVar.setFilterConfiguration(eVar);
            return true;
        } catch (JSONException unused) {
            LogUtil.e("MassageGunConfigMap", "handleFilterConfiguration JSONException");
            return false;
        }
    }

    private static List<dbn.a.c> a(JSONArray jSONArray) {
        ArrayList arrayList = new ArrayList();
        if (jSONArray != null) {
            try {
                if (jSONArray.length() > 0) {
                    b(jSONArray, arrayList);
                }
            } catch (JSONException unused) {
                LogUtil.e("MassageGunConfigMap", "handleFilterCondition JSONException");
            }
        }
        return arrayList;
    }

    private static void b(JSONArray jSONArray, List<dbn.a.c> list) throws JSONException {
        for (int i = 0; i < jSONArray.length(); i++) {
            dbn.a.c cVar = new dbn.a.c();
            if (jSONArray.get(i) instanceof JSONObject) {
                JSONObject jSONObject = (JSONObject) jSONArray.get(i);
                if (jSONObject.has("conditionRules")) {
                    cVar.setConditionRules(jSONObject.getString("conditionRules"));
                }
                if (jSONObject.has("matchingCondition")) {
                    cVar.setMatchingCondition(c(jSONObject.getJSONArray("matchingCondition")));
                }
                if (jSONObject.has("sortCondition")) {
                    e(jSONObject.getJSONObject("sortCondition"), cVar);
                }
                list.add(cVar);
            }
        }
    }

    private static void e(JSONObject jSONObject, dbn.a.c cVar) {
        if (jSONObject == null || cVar == null) {
            return;
        }
        try {
            dbn.a.i iVar = new dbn.a.i();
            if (jSONObject.has("sortRules")) {
                iVar.setSortRules(jSONObject.getString("sortRules"));
            }
            if (jSONObject.has(MedalConstants.EVENT_KEY)) {
                iVar.setKey(jSONObject.getString(MedalConstants.EVENT_KEY));
            }
            if (jSONObject.has("paramsList")) {
                a(jSONObject.getJSONArray("paramsList"), (dbn.a.b) null, iVar);
            }
            cVar.setSortCondition(iVar);
        } catch (JSONException unused) {
            LogUtil.e("MassageGunConfigMap", "handleSortCondition JSONException");
        }
    }

    private static boolean a(JSONArray jSONArray, dbn.a.b bVar, dbn.a.i iVar) {
        LogUtil.c("MassageGunConfigMap", "handleParamsList");
        if (jSONArray == null || jSONArray.length() <= 0) {
            LogUtil.c("MassageGunConfigMap", "paramsListJsonObject is null");
            return false;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                if (jSONObject.has(MedalConstants.EVENT_KEY)) {
                    dbn.a.C0294a c0294a = new dbn.a.C0294a();
                    c0294a.setKey(jSONObject.getString(MedalConstants.EVENT_KEY));
                    arrayList.add(c0294a);
                    a(bVar, iVar, arrayList);
                }
            } catch (JSONException unused) {
                LogUtil.e("MassageGunConfigMap", "handleParamsList JSONException");
                return false;
            }
        }
        return true;
    }

    private static void a(dbn.a.b bVar, dbn.a.i iVar, List<dbn.a.C0294a> list) {
        if (bVar != null) {
            bVar.setParamsLists(list);
        }
        if (iVar != null) {
            iVar.setParamsLists(list);
        }
    }

    private static List<dbn.a.d> b(JSONArray jSONArray) {
        JSONObject jSONObject;
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                jSONObject = jSONArray.getJSONObject(i);
            } catch (JSONException unused) {
                LogUtil.e("MassageGunConfigMap", "handleRecommendedCourse JSONException");
            }
            if (jSONObject == null) {
                return arrayList;
            }
            dbn.a.d dVar = new dbn.a.d();
            b(jSONObject, dVar);
            arrayList.add(dVar);
        }
        return arrayList;
    }

    private static void b(JSONObject jSONObject, dbn.a.d dVar) {
        try {
            if (jSONObject.has("courseId")) {
                dVar.setCourseId(jSONObject.getString("courseId"));
            }
            if (jSONObject.has("courseName")) {
                dVar.setCourseName(jSONObject.getString("courseName"));
            }
            if (jSONObject.has(WorkoutRecord.Extend.FIT_EXTEND_COURSE_TYPE)) {
                dVar.setCourseType(jSONObject.getString(WorkoutRecord.Extend.FIT_EXTEND_COURSE_TYPE));
            }
            d(jSONObject, dVar);
            if (jSONObject.has("muscleGroup")) {
                dVar.setMuscleGroup(d(jSONObject.getJSONArray("muscleGroup")));
            }
            e(jSONObject, dVar);
        } catch (JSONException unused) {
            LogUtil.e("MassageGunConfigMap", "paresCourseInfo JSONException");
        }
    }

    private static void d(JSONObject jSONObject, dbn.a.d dVar) throws JSONException {
        if (jSONObject.has("muscleGroupImgName")) {
            dVar.setMuscleGroupImgName(jSONObject.getString("muscleGroupImgName"));
        }
        if (jSONObject.has("muscleGroupImgUrl")) {
            dVar.setMuscleGroupImgUrl(jSONObject.getString("muscleGroupImgUrl"));
        }
        if (jSONObject.has("moreData")) {
            dVar.setMoreDataUrl(jSONObject.getString("moreData"));
        }
        if (jSONObject.has("recommendedContent")) {
            dVar.setRecommendedContent(jSONObject.getInt("recommendedContent"));
        }
        if (jSONObject.has("courseInfo")) {
            c(dVar, jSONObject.getJSONArray("courseInfo"));
        }
    }

    private static void e(JSONObject jSONObject, dbn.a.d dVar) throws JSONException {
        if (jSONObject.has("matchingRules")) {
            dVar.setMatchingRules(jSONObject.getString("matchingRules"));
        }
        if (jSONObject.has("matchingCondition")) {
            dVar.setMatchingCondition(c(jSONObject.getJSONArray("matchingCondition")));
        }
    }

    private static void c(dbn.a.d dVar, JSONArray jSONArray) {
        try {
            dVar.setCourseInfo((List) new Gson().fromJson(jSONArray.toString(), new TypeToken<ArrayList<Object>>() { // from class: dcg.3
            }.getType()));
        } catch (JsonSyntaxException unused) {
            LogUtil.e("MassageGunConfigMap", "handleCourseInfo JsonSyntaxException");
        } catch (NumberFormatException unused2) {
            LogUtil.e("MassageGunConfigMap", "handleCourseInfo NumberFormatException");
        }
    }

    private static List<String> d(JSONArray jSONArray) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                if (jSONArray.get(i) instanceof String) {
                    arrayList.add(jSONArray.getString(i));
                }
            } catch (JSONException unused) {
                LogUtil.e("MassageGunConfigMap", "handleMuscleGroup JSONException");
            }
        }
        return arrayList;
    }

    private static List<String> c(JSONArray jSONArray) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                if (jSONArray.get(i) instanceof String) {
                    arrayList.add(jSONArray.getString(i));
                }
            } catch (JSONException unused) {
                LogUtil.e("MassageGunConfigMap", "handleMuscleGroup JSONException");
            }
        }
        return arrayList;
    }

    public static String a(String str, String str2) {
        JSONObject jSONObject;
        LogUtil.c("MassageGunConfigMap", "getCourseRecommendationResult");
        dbn.a d = d(str2);
        if (d == null) {
            LogUtil.c("MassageGunConfigMap", "courseRecommended is null");
            return "";
        }
        ArrayList arrayList = new ArrayList();
        if (TextUtils.isEmpty(str)) {
            LogUtil.c("MassageGunConfigMap", "activityJsonArray is null");
        }
        JSONObject jSONObject2 = null;
        try {
            JSONArray jSONArray = new JSONArray(str);
            if (jSONArray.length() > 0) {
                for (int i = 0; i < jSONArray.length(); i++) {
                    arrayList.add(jSONArray.getJSONObject(i));
                }
            }
            LogUtil.c("MassageGunConfigMap", "jsonObjects.size() ", Integer.valueOf(arrayList.size()));
            jSONObject = dcf.b(d.getFilterConfiguration(), arrayList);
            if (jSONObject == null) {
                try {
                    LogUtil.c("MassageGunConfigMap", "activityJson is null");
                    return b((JSONObject) null, d);
                } catch (JSONException unused) {
                    jSONObject2 = jSONObject;
                    LogUtil.e("MassageGunConfigMap", "jsonArray JSONException");
                    jSONObject = jSONObject2;
                    return b(jSONObject, d);
                }
            }
        } catch (JSONException unused2) {
        }
        return b(jSONObject, d);
    }

    private static String b(JSONObject jSONObject, dbn.a aVar) {
        LogUtil.c("MassageGunConfigMap", "filteringJson");
        if (aVar == null) {
            LogUtil.c("MassageGunConfigMap", "courseRecommended is null");
            return "";
        }
        HashMap hashMap = new HashMap(16);
        List<dbn.a.d> recommendedCourseList = aVar.getRecommendedCourseList();
        if (recommendedCourseList == null || recommendedCourseList.size() <= 0) {
            LogUtil.c("MassageGunConfigMap", "recommendedCourses is null or recommendedCourses.size() <= 0");
            return "";
        }
        return b(jSONObject, hashMap, recommendedCourseList);
    }

    private static String b(JSONObject jSONObject, Map<String, Object> map, List<dbn.a.d> list) {
        for (dbn.a.d dVar : list) {
            if (dVar.getMatchingCondition() == null || dVar.getMatchingCondition().size() <= 0) {
                return "";
            }
            map.clear();
            d(jSONObject, map, dVar);
            LogUtil.c("MassageGunConfigMap", "params ", map, " recommendedCours.getMatchingRules() ", dVar.getMatchingRules());
            if (dcn.c().a(dVar.getMatchingRules(), map)) {
                LogUtil.c("MassageGunConfigMap", "select MatchingRules is ", dVar.getMatchingRules());
                return c(jSONObject, dVar);
            }
        }
        return "";
    }

    private static void d(JSONObject jSONObject, Map<String, Object> map, dbn.a.d dVar) {
        if (jSONObject == null) {
            LogUtil.c("MassageGunConfigMap", "activityJsonObject is null");
            map.put("dataSize", "0");
        } else {
            Iterator<String> it = dVar.getMatchingCondition().iterator();
            while (it.hasNext()) {
                c(jSONObject, map, it.next());
            }
        }
    }

    private static String c(JSONObject jSONObject, dbn.a.d dVar) {
        HashMap hashMap = new HashMap();
        if (dVar == null) {
            return new Gson().toJson(hashMap);
        }
        hashMap.put("courseId", dVar.getCourseId());
        hashMap.put("courseName", dVar.getCourseName());
        hashMap.put("muscleGroupImgName", dVar.getMuscleGroupImgName());
        hashMap.put("muscleGroupImgUrl", dVar.getMuscleGroupImgUrl());
        hashMap.put(WorkoutRecord.Extend.FIT_EXTEND_COURSE_TYPE, dVar.getCourseType());
        hashMap.put("courseInfo", dVar.getCourseInfo());
        hashMap.put("muscleGroup", dVar.getMuscleGroup());
        hashMap.put("recommendedContent", Integer.valueOf(dVar.getRecommendedContent()));
        hashMap.put("moreData", dVar.getMoreDataUrl());
        a(jSONObject, hashMap);
        return new Gson().toJson(hashMap);
    }

    private static void c(JSONObject jSONObject, Map<String, Object> map, String str) {
        if ("currentTime".equals(str)) {
            map.put(str, String.valueOf(System.currentTimeMillis()));
        } else if ("dataSize".equals(str)) {
            map.put(str, "1");
        } else if (jSONObject.has(str)) {
            map.put(str, jSONObject.optString(str));
        }
    }

    private static void a(JSONObject jSONObject, Map<String, Object> map) {
        if (jSONObject == null) {
            return;
        }
        map.put(BleConstants.SPORT_TYPE, Integer.valueOf(jSONObject.has(BleConstants.SPORT_TYPE) ? e(c(jSONObject, BleConstants.SPORT_TYPE)) : 0));
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private static int e(String str) {
        char c;
        str.hashCode();
        switch (str.hashCode()) {
            case 49748:
                if (str.equals(BleConstants.SPORT_TYPE_WALK)) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 49749:
                if (str.equals(BleConstants.SPORT_TYPE_RUN)) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 49750:
                if (str.equals(BleConstants.SPORT_TYPE_BIKE)) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 49776:
                if (str.equals(BleConstants.SPORT_TYPE_TREADMILL)) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 49777:
                if (str.equals(BleConstants.SPORT_TYPE_INDOOR_BIKE)) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 49835:
                if (str.equals(BleConstants.SPORT_TYPE_INDOOR_WALKING)) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c == 0) {
            return 30005;
        }
        if (c == 1) {
            return 30006;
        }
        if (c == 2) {
            return 30007;
        }
        if (c == 3) {
            return 30006;
        }
        if (c == 4) {
            return 30007;
        }
        if (c == 5) {
            return 30005;
        }
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            LogUtil.e("MassageGunConfigMap", e.getMessage());
            return 0;
        }
    }

    private static String c(JSONObject jSONObject, String str) {
        if (jSONObject == null || TextUtils.isEmpty(str)) {
            return String.valueOf(0);
        }
        try {
            if (!TextUtils.isEmpty(jSONObject.getString(str)) && !Constants.NULL.equals(jSONObject.getString(str))) {
                return jSONObject.getString(str);
            }
            return String.valueOf(0);
        } catch (JSONException unused) {
            LogUtil.e("MassageGunConfigMap", "getJsonString JSONException");
            return String.valueOf(0);
        }
    }
}
