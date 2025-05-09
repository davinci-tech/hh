package defpackage;

import android.content.Context;
import com.huawei.haf.bundle.AppBundlePluginProxy;
import com.huawei.health.baseapi.pluginachievement.PluginAchievementApi;
import com.huawei.health.h5pro.jsbridge.base.JsModuleBase;
import com.huawei.hmf.tasks.Task;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class bzw extends AppBundlePluginProxy<PluginAchievementApi> implements PluginAchievementApi {
    private static volatile bzw b;

    /* renamed from: a, reason: collision with root package name */
    private PluginAchievementApi f576a;

    private bzw() {
        super("PluginAchievement_PluginAchievementProxy", "PluginAchievement", "com.huawei.pluginachievement.PluginAchieveImpl");
        this.f576a = createPluginApi();
    }

    public static bzw e() {
        bzw bzwVar;
        if (b == null) {
            synchronized (bzw.class) {
                if (b == null) {
                    b = new bzw();
                }
                bzwVar = b;
            }
            return bzwVar;
        }
        return b;
    }

    @Override // com.huawei.haf.bundle.AppBundlePluginProxy
    public boolean isPluginAvaiable() {
        return this.f576a != null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.haf.bundle.AppBundlePluginProxy
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public void initialize(PluginAchievementApi pluginAchievementApi) {
        this.f576a = pluginAchievementApi;
    }

    @Override // com.huawei.health.baseapi.pluginachievement.PluginAchievementApi
    public Class<? extends JsModuleBase> getCommonJsModule(String str) {
        PluginAchievementApi pluginAchievementApi = this.f576a;
        if (pluginAchievementApi != null) {
            return pluginAchievementApi.getCommonJsModule(str);
        }
        LogUtil.h("PluginAchievement_PluginAchievementProxy", "getCommonJsModule mPluginOperationApi is null");
        return JsModuleBase.class;
    }

    @Override // com.huawei.health.baseapi.pluginachievement.PluginAchievementApi
    public Task<Integer> getTotalKakaValue(Context context) {
        PluginAchievementApi pluginAchievementApi = this.f576a;
        if (pluginAchievementApi != null) {
            return pluginAchievementApi.getTotalKakaValue(context);
        }
        return null;
    }

    @Override // com.huawei.health.baseapi.pluginachievement.PluginAchievementApi
    public Task<mde> updateKakaTasks(Context context, List<mdg> list) {
        PluginAchievementApi pluginAchievementApi = this.f576a;
        if (pluginAchievementApi != null) {
            return pluginAchievementApi.updateKakaTasks(context, list);
        }
        return null;
    }

    @Override // com.huawei.health.baseapi.pluginachievement.PluginAchievementApi
    public Task<List<mdf>> getTaskInfoList(Context context, int i) {
        PluginAchievementApi pluginAchievementApi = this.f576a;
        if (pluginAchievementApi != null) {
            return pluginAchievementApi.getTaskInfoList(context, i);
        }
        return null;
    }

    @Override // com.huawei.health.baseapi.pluginachievement.PluginAchievementApi
    public Task<List<mdf>> getTaskInfoListByScenario(Context context, int i, int... iArr) {
        PluginAchievementApi pluginAchievementApi = this.f576a;
        if (pluginAchievementApi != null) {
            return pluginAchievementApi.getTaskInfoListByScenario(context, i, iArr);
        }
        return null;
    }

    @Override // com.huawei.health.baseapi.pluginachievement.PluginAchievementApi
    public Task<List<mdf>> getTaskInfoListByRule(Context context, int... iArr) {
        PluginAchievementApi pluginAchievementApi = this.f576a;
        if (pluginAchievementApi != null) {
            return pluginAchievementApi.getTaskInfoListByRule(context, iArr);
        }
        return null;
    }

    @Override // com.huawei.health.baseapi.pluginachievement.PluginAchievementApi
    public Task<mde> claimKakaTasksByScenario(Context context, int i, String... strArr) {
        PluginAchievementApi pluginAchievementApi = this.f576a;
        if (pluginAchievementApi != null) {
            return pluginAchievementApi.claimKakaTasksByScenario(context, i, strArr);
        }
        return null;
    }

    @Override // com.huawei.health.baseapi.pluginachievement.PluginAchievementApi
    public void finishKakaTask(Context context, int i, Map<String, Object> map) {
        PluginAchievementApi pluginAchievementApi = this.f576a;
        if (pluginAchievementApi != null) {
            pluginAchievementApi.finishKakaTask(context, i, map);
        }
    }

    @Override // com.huawei.health.baseapi.pluginachievement.PluginAchievementApi
    public Task<Boolean> checkKakaTaskIsFinished(Context context, int... iArr) {
        PluginAchievementApi pluginAchievementApi = this.f576a;
        if (pluginAchievementApi != null) {
            return pluginAchievementApi.checkKakaTaskIsFinished(context, iArr);
        }
        return null;
    }

    @Override // com.huawei.health.baseapi.pluginachievement.PluginAchievementApi
    public void doPost(String str, JSONObject jSONObject, HashMap<String, String> hashMap, IBaseResponseCallback iBaseResponseCallback) {
        PluginAchievementApi pluginAchievementApi = this.f576a;
        if (pluginAchievementApi != null) {
            pluginAchievementApi.doPost(str, jSONObject, hashMap, iBaseResponseCallback);
        } else if (iBaseResponseCallback != null) {
            iBaseResponseCallback.onResponse(-1, null);
        }
    }

    @Override // com.huawei.health.baseapi.pluginachievement.PluginAchievementApi
    public void doPostByKey(Context context, int i) {
        PluginAchievementApi pluginAchievementApi = this.f576a;
        if (pluginAchievementApi != null) {
            pluginAchievementApi.doPostByKey(context, i);
        }
    }

    @Override // com.huawei.health.baseapi.pluginachievement.PluginAchievementApi
    public void goToTaskPage(Context context, mdf mdfVar) {
        PluginAchievementApi pluginAchievementApi = this.f576a;
        if (pluginAchievementApi != null) {
            pluginAchievementApi.goToTaskPage(context, mdfVar);
        }
    }

    @Override // com.huawei.health.baseapi.pluginachievement.PluginAchievementApi
    public int setEvent(Context context, String str, Map<String, Object> map) {
        PluginAchievementApi pluginAchievementApi = this.f576a;
        if (pluginAchievementApi != null) {
            return pluginAchievementApi.setEvent(context, str, map);
        }
        return -1;
    }

    @Override // com.huawei.health.baseapi.pluginachievement.PluginAchievementApi
    public void showMedalDialog(Context context, int i) {
        PluginAchievementApi pluginAchievementApi = this.f576a;
        if (pluginAchievementApi != null) {
            pluginAchievementApi.showMedalDialog(context, i);
        }
    }
}
