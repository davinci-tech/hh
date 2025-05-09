package com.huawei.pluginachievement.report.iterface;

import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.PluginAchieveAdapter;
import defpackage.mcv;
import defpackage.mcz;
import defpackage.meh;
import defpackage.mhh;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public abstract class BaseCalculator {
    private static final String TAG = "BaseCalculator";
    private static JSONObject sAcquiredStar = new JSONObject();

    public abstract void compute(int i);

    public PluginAchieveAdapter getPluginAchieveAdapter() {
        return mcv.d(BaseApplication.getContext()).getAdapter();
    }

    public void insertData(int i, String str, int i2, String str2) {
        meh c = meh.c(BaseApplication.getContext());
        mcz a2 = mhh.a(i, str, i2);
        a2.setValues(str2);
        mhh.b(a2, c);
    }

    public void insertData(int i, String str, Map<Integer, String> map) {
        if (map == null || map.isEmpty()) {
            LogUtil.h(TAG, "insertData: dataMap is empty, type -> " + str);
        } else {
            for (Map.Entry<Integer, String> entry : map.entrySet()) {
                insertData(i, str, entry.getKey().intValue(), entry.getValue());
            }
        }
    }

    public String getStarAcquired() {
        return sAcquiredStar.toString();
    }

    public boolean addStarData(String str, int i) {
        try {
            sAcquiredStar.put(str, i);
            return true;
        } catch (JSONException unused) {
            return false;
        }
    }

    public void clearStarData() {
        try {
            JSONArray names = sAcquiredStar.names();
            if (names != null && names.length() != 0) {
                for (int i = 0; i < names.length(); i++) {
                    sAcquiredStar.remove(names.getString(i));
                }
            }
        } catch (JSONException unused) {
            LogUtil.b(TAG, "clearStarData error");
        }
    }
}
