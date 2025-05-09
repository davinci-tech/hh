package defpackage;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.hmf.annotation.ApiDefine;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.AchieveDataApi;
import com.huawei.pluginachievement.manager.model.SingleDayRecord;
import com.huawei.pluginbase.PluginBaseAdapter;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@ApiDefine(uri = AchieveDataApi.class)
/* loaded from: classes6.dex */
public class mcp implements AchieveDataApi {
    @Override // com.huawei.pluginachievement.AchieveDataApi
    public mcz getData(int i, Map<String, String> map) {
        return meh.c(BaseApplication.e()).d(i, map);
    }

    @Override // com.huawei.pluginachievement.AchieveDataApi
    public mdd getSingleDayData() {
        mdd mddVar = new mdd();
        mcz d = meh.c(BaseApplication.e()).d(2, new HashMap(16));
        if (!(d instanceof SingleDayRecord)) {
            LogUtil.h("PLGACHIEVE_AchieveDataImpl", "getSingleDayData SingleDayRecord is null.");
            return mddVar;
        }
        SingleDayRecord singleDayRecord = (SingleDayRecord) d;
        mddVar.h(singleDayRecord.acquireBestRun3KMPace());
        mddVar.e(b(singleDayRecord.acquireBestRun3KMPace()));
        mddVar.i(singleDayRecord.acquireBestRun5KMPace());
        mddVar.d(b(singleDayRecord.acquireBestRun5KMPace()));
        mddVar.a(singleDayRecord.acquireBestRun10KMPace());
        mddVar.a(b(singleDayRecord.acquireBestRun10KMPace()));
        mddVar.g(singleDayRecord.acquireBestRunHMPace());
        mddVar.c(b(singleDayRecord.acquireBestRunHMPace()));
        mddVar.f(singleDayRecord.acquireBestRunFMPace());
        mddVar.b(b(singleDayRecord.acquireBestRunFMPace()));
        mddVar.c(mdn.a(singleDayRecord.acquireBestRopeSingCount(), "value"));
        mddVar.b(mdn.a(singleDayRecord.acquireBestRopeContinuousCount(), "value"));
        mddVar.d(mdn.a(singleDayRecord.acquireBestRopeSpeed(), "value"));
        mddVar.e(mdn.a(singleDayRecord.acquireBestRopeDuration(), "value"));
        return mddVar;
    }

    @Override // com.huawei.pluginachievement.AchieveDataApi
    public void generateReportHealthData(Context context) {
        LogUtil.a("PLGACHIEVE_AchieveDataImpl", "generateReportHealthData");
        if (context == null) {
            LogUtil.a("PLGACHIEVE_AchieveDataImpl", "context is null");
        } else {
            mcv.d(context).b();
        }
    }

    @Override // com.huawei.pluginachievement.AchieveDataApi
    public boolean getKakaTaskRedDot(Context context) {
        LogUtil.a("PLGACHIEVE_AchieveDataImpl", "getKakaTaskRedDot");
        if (context == null) {
            LogUtil.a("PLGACHIEVE_AchieveDataImpl", "context is null");
            return false;
        }
        return mer.b(context).c();
    }

    @Override // com.huawei.pluginachievement.AchieveDataApi
    public void dealKakaTask(Context context, Handler handler, int i) {
        LogUtil.a("PLGACHIEVE_AchieveDataImpl", "dealKakaTask");
        if (context == null) {
            LogUtil.a("PLGACHIEVE_AchieveDataImpl", "context is null");
        } else {
            mer.b(context).cgp_(handler, i);
        }
    }

    @Override // com.huawei.pluginachievement.AchieveDataApi
    public PluginBaseAdapter getAdapter(Context context) {
        LogUtil.a("PLGACHIEVE_AchieveDataImpl", "getAdapter");
        return mcv.d(context).getAdapter();
    }

    @Override // com.huawei.pluginachievement.AchieveDataApi
    public void initAdapter(Context context, PluginBaseAdapter pluginBaseAdapter) {
        LogUtil.a("PLGACHIEVE_AchieveDataImpl", "setAdapter");
        if (context == null) {
            LogUtil.a("PLGACHIEVE_AchieveDataImpl", "context is null");
        } else if (pluginBaseAdapter == null) {
            LogUtil.a("PLGACHIEVE_AchieveDataImpl", "pluginBaseAdapter is null");
        } else {
            mcv.d(context).setAdapter(pluginBaseAdapter);
        }
    }

    private mcy b(String str) {
        JSONException e;
        LogUtil.a("PLGACHIEVE_AchieveDataImpl", "parseBestMotionPace bestMotionPace.");
        mcy mcyVar = null;
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("PLGACHIEVE_AchieveDataImpl", "parseBestMotionPace reportData is null");
            return null;
        }
        try {
            JSONArray jSONArray = new JSONArray(str);
            if (jSONArray.length() == 0) {
                return null;
            }
            mcy mcyVar2 = new mcy();
            double d = 0.0d;
            for (int i = 0; i < jSONArray.length(); i++) {
                try {
                    JSONObject jSONObject = jSONArray.getJSONObject(i);
                    double a2 = mdn.a("value", jSONObject);
                    long d2 = mdn.d("startTime", jSONObject);
                    if (mlg.e(d, 0.0d) == 0) {
                        mcyVar2.d(a2);
                        mcyVar2.a(d2);
                        d = a2;
                    }
                    if (mlg.e(a2, d) < 0) {
                        mcyVar2.d(a2);
                        mcyVar2.a(d2);
                        mcyVar2.c(mdn.e("source", jSONObject));
                        d = a2;
                    }
                } catch (JSONException e2) {
                    e = e2;
                    mcyVar = mcyVar2;
                    LogUtil.b("PLGACHIEVE_AchieveDataImpl", "getStartTimeAndEndTime Exception:", e.getMessage());
                    return mcyVar;
                }
            }
            return mcyVar2;
        } catch (JSONException e3) {
            e = e3;
        }
    }

    @Override // com.huawei.pluginachievement.AchieveDataApi
    public void dealWeChatTask() {
        mer.b(BaseApplication.e()).e();
    }
}
