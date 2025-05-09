package com.huawei.health.baseapi.pluginachievement;

import android.content.Context;
import com.huawei.health.h5pro.jsbridge.base.JsModuleBase;
import com.huawei.hmf.tasks.Task;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import defpackage.mde;
import defpackage.mdf;
import defpackage.mdg;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes.dex */
public interface PluginAchievementApi {
    Task<Boolean> checkKakaTaskIsFinished(Context context, int... iArr);

    Task<mde> claimKakaTasksByScenario(Context context, int i, String... strArr);

    void doPost(String str, JSONObject jSONObject, HashMap<String, String> hashMap, IBaseResponseCallback iBaseResponseCallback);

    void doPostByKey(Context context, int i);

    void finishKakaTask(Context context, int i, Map<String, Object> map);

    Class<? extends JsModuleBase> getCommonJsModule(String str);

    Task<List<mdf>> getTaskInfoList(Context context, int i);

    Task<List<mdf>> getTaskInfoListByRule(Context context, int... iArr);

    Task<List<mdf>> getTaskInfoListByScenario(Context context, int i, int... iArr);

    Task<Integer> getTotalKakaValue(Context context);

    void goToTaskPage(Context context, mdf mdfVar);

    int setEvent(Context context, String str, Map<String, Object> map);

    void showMedalDialog(Context context, int i);

    Task<mde> updateKakaTasks(Context context, List<mdg> list);
}
