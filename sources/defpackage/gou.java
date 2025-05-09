package defpackage;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.google.gson.annotations.SerializedName;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.marketing.request.CustomConfigFactory;
import com.huawei.health.marketing.request.RespSetCustomConfig;
import com.huawei.health.marketing.request.SetCustomConfigApi;
import com.huawei.health.marketing.request.SetCustomConfigReq;
import com.huawei.hihealth.HiSyncOption;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.util.HiBroadcastUtil;
import com.huawei.hms.framework.network.restclient.Response;
import com.huawei.hms.framework.network.restclient.ResultCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import health.compact.a.HiDateUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.Utils;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes4.dex */
public class gou {
    public static void c(int i, int i2, int i3) {
        HiSyncOption hiSyncOption = new HiSyncOption();
        hiSyncOption.setSyncModel(2);
        hiSyncOption.setSyncScope(1);
        hiSyncOption.setSyncMethod(2);
        hiSyncOption.setSyncAction(i);
        hiSyncOption.setSyncDataType(i2);
        hiSyncOption.setPushAction(i3);
        LogUtil.a("syncCloudUtils", "syncCloud!");
        HiHealthManager.d(BaseApplication.getContext()).synCloud(hiSyncOption, null);
    }

    public static void aRh_(final Handler handler) {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: got
                @Override // java.lang.Runnable
                public final void run() {
                    gou.aRh_(handler);
                }
            });
            return;
        }
        if (handler == null) {
            LogUtil.a("syncCloudUtils", "handler is null!");
            return;
        }
        if (Utils.o()) {
            return;
        }
        Context context = BaseApplication.getContext();
        int e2 = CommonUtil.e(SharedPreferenceManager.b(context, Integer.toString(10000), "syncTime"), 0);
        if (e2 < CommonUtil.e(jah.c().e("app_hot_start_max_sync_times"), 10)) {
            HiBroadcastUtil.i(context);
            handler.sendEmptyMessageDelayed(15, 3000L);
            SharedPreferenceManager.e(context, Integer.toString(10000), "syncTime", Integer.toString(e2 + 1), (StorageParams) null);
        }
        int c = HiDateUtil.c(System.currentTimeMillis());
        if (CommonUtil.e(SharedPreferenceManager.b(context, Integer.toString(10000), "oldDay"), 0) != c) {
            SharedPreferenceManager.e(context, Integer.toString(10000), "syncTime", Integer.toString(0), (StorageParams) null);
            SharedPreferenceManager.e(context, Integer.toString(10000), "oldDay", Integer.toString(c), (StorageParams) null);
        }
    }

    public static void c(long j) {
        LogUtil.a("syncCloudUtils", "enter updateSyncFinishTime: ", Long.valueOf(j));
        HashMap hashMap = new HashMap(1);
        hashMap.put("syncFinishTime", String.valueOf(j));
        e eVar = new e();
        eVar.d(hashMap);
        SetCustomConfigReq setCustomConfigReq = new SetCustomConfigReq();
        SetCustomConfigApi setCustomConfigApi = (SetCustomConfigApi) lqi.d().b(SetCustomConfigApi.class);
        CustomConfigFactory customConfigFactory = new CustomConfigFactory(BaseApplication.getContext());
        setCustomConfigApi.setCustomConfig(setCustomConfigReq.getUrl(), customConfigFactory.getHeaders(), lql.b(customConfigFactory.getBody(eVar))).enqueue(new ResultCallback<RespSetCustomConfig>() { // from class: gou.5
            @Override // com.huawei.hms.framework.network.restclient.ResultCallback
            public void onResponse(Response<RespSetCustomConfig> response) {
                LogUtil.a("syncCloudUtils", "updateSyncFinishTime result: ", Boolean.valueOf(response.isOK()));
            }

            @Override // com.huawei.hms.framework.network.restclient.ResultCallback
            public void onFailure(Throwable th) {
                LogUtil.h("syncCloudUtils", "updateSyncFinishTime response is error.");
            }
        });
    }

    static class e {

        @SerializedName("customConfig")
        private Map<String, String> b;

        private e() {
        }

        public void d(Map<String, String> map) {
            this.b = map;
        }

        public String toString() {
            return "CustomConfigReq{customConfig=" + this.b + '}';
        }
    }
}
