package com.huawei.pluginachievement;

import android.content.Context;
import com.huawei.health.baseapi.pluginachievement.PluginAchievementApi;
import com.huawei.health.h5pro.jsbridge.base.JsModuleBase;
import com.huawei.hmf.tasks.Task;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.connectivity.https.HttpResCallBack;
import com.huawei.pluginachievement.jsmodule.AchieveUtil;
import com.huawei.pluginachievement.jsmodule.CloudUtil;
import com.huawei.pluginachievement.manager.service.AchieveServiceObserver;
import defpackage.mcv;
import defpackage.mde;
import defpackage.mdf;
import defpackage.mdg;
import defpackage.meh;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public class PluginAchieveImpl implements PluginAchievementApi {
    @Override // com.huawei.health.baseapi.pluginachievement.PluginAchievementApi
    public Class<? extends JsModuleBase> getCommonJsModule(String str) {
        if ("achievement".equals(str)) {
            return AchieveUtil.class;
        }
        if ("CloudUtil".equals(str)) {
            return CloudUtil.class;
        }
        LogUtil.h("PluginAchievement_PluginAchieveImpl", "unknow JsModule:", str);
        return JsModuleBase.class;
    }

    @Override // com.huawei.health.baseapi.pluginachievement.PluginAchievementApi
    public Task<Integer> getTotalKakaValue(Context context) {
        return mcv.d(context).o();
    }

    @Override // com.huawei.health.baseapi.pluginachievement.PluginAchievementApi
    public Task<mde> updateKakaTasks(Context context, List<mdg> list) {
        return mcv.d(context).a(list);
    }

    @Override // com.huawei.health.baseapi.pluginachievement.PluginAchievementApi
    public Task<List<mdf>> getTaskInfoList(Context context, int i) {
        return mcv.d(context).b(i, 2, (int[]) null);
    }

    @Override // com.huawei.health.baseapi.pluginachievement.PluginAchievementApi
    public Task<List<mdf>> getTaskInfoListByScenario(Context context, int i, int... iArr) {
        return mcv.d(context).b(0, i, iArr);
    }

    @Override // com.huawei.health.baseapi.pluginachievement.PluginAchievementApi
    public Task<List<mdf>> getTaskInfoListByRule(Context context, int... iArr) {
        return getTaskInfoListByScenario(context, -1, iArr);
    }

    @Override // com.huawei.health.baseapi.pluginachievement.PluginAchievementApi
    public Task<mde> claimKakaTasksByScenario(Context context, int i, String... strArr) {
        return mcv.d(context).c(i, strArr);
    }

    @Override // com.huawei.health.baseapi.pluginachievement.PluginAchievementApi
    public void finishKakaTask(Context context, int i, Map<String, Object> map) {
        mcv d = mcv.d(context);
        if (map == null) {
            map = new HashMap<>(2);
        }
        d.c(context, String.valueOf(i), map);
    }

    @Override // com.huawei.health.baseapi.pluginachievement.PluginAchievementApi
    public Task<Boolean> checkKakaTaskIsFinished(Context context, int... iArr) {
        return mcv.d(context).d(iArr);
    }

    @Override // com.huawei.health.baseapi.pluginachievement.PluginAchievementApi
    public void doPost(String str, JSONObject jSONObject, HashMap<String, String> hashMap, final IBaseResponseCallback iBaseResponseCallback) {
        mcv.d(BaseApplication.getContext()).b(str, jSONObject, hashMap, new HttpResCallBack() { // from class: com.huawei.pluginachievement.PluginAchieveImpl.4
            @Override // com.huawei.pluginachievement.connectivity.https.HttpResCallBack
            public void onFinished(int i, String str2) {
                IBaseResponseCallback iBaseResponseCallback2 = iBaseResponseCallback;
                if (iBaseResponseCallback2 != null) {
                    iBaseResponseCallback2.d(i, str2);
                }
            }
        });
    }

    @Override // com.huawei.health.baseapi.pluginachievement.PluginAchievementApi
    public void doPostByKey(Context context, int i) {
        if (i == 4) {
            meh.c(context).f();
        }
    }

    @Override // com.huawei.health.baseapi.pluginachievement.PluginAchievementApi
    public void goToTaskPage(Context context, mdf mdfVar) {
        mcv.d(BaseApplication.getContext()).a(context, mdfVar);
    }

    @Override // com.huawei.health.baseapi.pluginachievement.PluginAchievementApi
    public int setEvent(Context context, String str, Map<String, Object> map) {
        return mcv.d(context).c(context, str, map);
    }

    @Override // com.huawei.health.baseapi.pluginachievement.PluginAchievementApi
    public void showMedalDialog(Context context, int i) {
        LogUtil.a("PluginAchievement_PluginAchieveImpl", "showMedalDialog: subScenario -> " + i);
        doPostByKey(context, 4);
        AchieveServiceObserver.d(i);
    }
}
