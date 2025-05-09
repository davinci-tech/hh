package defpackage;

import android.text.TextUtils;
import com.huawei.health.marketing.datatype.ResourceResultInfo;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.login.ui.login.LoginInit;
import health.compact.a.LogUtil;
import health.compact.a.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
class dmg {
    private static int d;

    dmg() {
    }

    static Map<Integer, ResourceResultInfo> e(int i, String str, String str2) {
        try {
            ResourceResultInfo d2 = d(new JSONObject(str2));
            if (d2 == null) {
                LogUtil.c("MarketingDataParser", "ResourceResultInfo is null in dealWithSinglePositionIdResource.");
                return null;
            }
            int totalNum = d2.getTotalNum();
            if (totalNum > 500) {
                return e(i, str, (totalNum / 500) + 1);
            }
            HashMap hashMap = new HashMap();
            hashMap.put(Integer.valueOf(i), d2);
            return hashMap;
        } catch (JSONException e) {
            ReleaseLogUtil.c("MarketingDataParser", "processSingleFromString failed, exception:" + e);
            return null;
        }
    }

    static ResourceResultInfo d(JSONObject jSONObject) {
        if (!b(jSONObject)) {
            ReleaseLogUtil.a("MarketingDataParser", "result is invaild.");
            return null;
        }
        try {
            String string = jSONObject.getString("resultInfo");
            if (string.isEmpty()) {
                ReleaseLogUtil.a("MarketingDataParser", "processSingleFromJsonObject failed, resultInfo is null");
                return null;
            }
            ResourceResultInfo resourceResultInfo = (ResourceResultInfo) HiJsonUtil.e(string, ResourceResultInfo.class);
            LogUtil.c("MarketingDataParser", "processSingleFromJsonObject success");
            return resourceResultInfo;
        } catch (JSONException e) {
            ReleaseLogUtil.c("MarketingDataParser", "process single resourceResultInfo failed, exception:" + e);
            return null;
        }
    }

    static Map<Integer, ResourceResultInfo> c(String str, String str2) {
        try {
            return c(new JSONObject(str), str2, true);
        } catch (JSONException e) {
            ReleaseLogUtil.c("MarketingDataParser", "processMultiResultFromString failed, exception:" + e);
            return null;
        }
    }

    static Map<Integer, ResourceResultInfo> c(JSONObject jSONObject, String str, boolean z) {
        HashMap hashMap = new HashMap();
        if (!b(jSONObject)) {
            ReleaseLogUtil.a("MarketingDataParser", "result is invaild.");
            return null;
        }
        try {
            String string = jSONObject.getString("resultInfo");
            if (string.isEmpty()) {
                ReleaseLogUtil.a("MarketingDataParser", "handleFeatureMarketingData no data");
                return null;
            }
            JSONObject jSONObject2 = new JSONObject(string);
            Iterator<String> keys = jSONObject2.keys();
            while (keys.hasNext()) {
                String obj = keys.next().toString();
                ResourceResultInfo resourceResultInfo = (ResourceResultInfo) HiJsonUtil.e(jSONObject2.getString(obj), ResourceResultInfo.class);
                int totalNum = resourceResultInfo.getTotalNum();
                if (z && totalNum > 50) {
                    hashMap.putAll(e(Integer.parseInt(obj), str, (totalNum / 500) + 1));
                } else {
                    hashMap.put(Integer.valueOf(Integer.parseInt(obj)), resourceResultInfo);
                }
                LogUtil.d("MarketingDataParser", "one of multi resourceResultInfo parse success, positionID: " + obj);
            }
            return hashMap;
        } catch (NumberFormatException | JSONException e) {
            ReleaseLogUtil.c("MarketingDataParser", "process multi resourceResultInfo failed, exception:" + e);
            return hashMap;
        }
    }

    private static boolean b(JSONObject jSONObject) {
        try {
            int i = jSONObject.getInt("resultCode");
            if (i != 0) {
                LogUtil.a("MarketingDataParser", "handleFeatureMarketingData resultCode = ", Integer.valueOf(i), ",resultDesc = ", jSONObject.getString("resultDesc"));
                if ((i == 1002 || i == 1004) && d < 20) {
                    LogUtil.a("MarketingDataParser", "Token invaild, try login in.");
                    d++;
                    LoginInit.tryLoginWhenTokenInvalid();
                }
                return false;
            }
            d = 0;
            return true;
        } catch (JSONException unused) {
            LogUtil.e("MarketingDataParser", "check result valid JSONException.");
            return false;
        }
    }

    private static Map<Integer, ResourceResultInfo> e(int i, String str, int i2) {
        ReleaseLogUtil.b("MarketingDataParser", "resource count is more than 500, requesting more...");
        ArrayList arrayList = new ArrayList();
        arrayList.add(Integer.valueOf(i));
        ArrayList arrayList2 = new ArrayList();
        for (int i3 = 1; i3 <= i2; i3++) {
            String a2 = dmo.a(arrayList, str, i3);
            if (!TextUtils.isEmpty(a2)) {
                arrayList2.add(a2);
            }
        }
        if (koq.b(arrayList2)) {
            ReleaseLogUtil.a("MarketingDataParser", "result invalid");
            return new HashMap();
        }
        return e(i, arrayList2);
    }

    private static Map<Integer, ResourceResultInfo> e(int i, List<String> list) {
        Iterator<String> it = list.iterator();
        ResourceResultInfo resourceResultInfo = null;
        while (it.hasNext()) {
            try {
                JSONObject jSONObject = new JSONObject(it.next());
                if (resourceResultInfo == null) {
                    resourceResultInfo = d(jSONObject);
                } else {
                    ResourceResultInfo d2 = d(jSONObject);
                    if (d2 != null) {
                        resourceResultInfo.getResources().addAll(d2.getResources());
                    }
                }
            } catch (JSONException e) {
                ReleaseLogUtil.c("MarketingDataParser", "combineSingleSourcesMultiPage failed, exception:" + e);
                return null;
            }
        }
        HashMap hashMap = new HashMap();
        hashMap.put(Integer.valueOf(i), resourceResultInfo);
        return hashMap;
    }
}
