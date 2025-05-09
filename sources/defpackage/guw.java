package defpackage;

import android.text.TextUtils;
import com.google.gson.JsonObject;
import com.huawei.haf.application.BaseApplication;
import com.huawei.hihealth.HiSampleConfig;
import com.huawei.hihealth.HiSyncOption;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiDataOperateListener;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class guw {
    public static void c() {
        String b2 = SharedPreferenceManager.b(BaseApplication.e(), Integer.toString(20002), "ihealthlabs");
        LogUtil.a("Track_FreeIndoorSwitchConfig", "initIndoorSwitchConfig  isOpenIndoorRunning ", b2);
        if (TextUtils.isEmpty(b2)) {
            return;
        }
        boolean a2 = SharedPreferenceManager.a(Integer.toString(20002), "WRITE_INDOOR_SWITCH", false);
        LogUtil.a("Track_FreeIndoorSwitchConfig", "initIndoorSwitchConfig  hasWriteData ", Boolean.valueOf(a2));
        if (a2) {
            return;
        }
        if (!SharedPreferenceManager.a("HiHealthService", "pullAllStatus", false)) {
            LogUtil.a("Track_FreeIndoorSwitchConfig", "data is not sync finish.");
        } else {
            d(b2);
        }
    }

    private static void d(final String str) {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add("900200027");
        njj.d("9002", arrayList, new HiDataReadResultListener() { // from class: guw.5
            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i, Object obj, int i2, int i3) {
            }

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResult(Object obj, int i, int i2) {
                if (!koq.e(obj, HiSampleConfig.class)) {
                    ReleaseLogUtil.d("Track_FreeIndoorSwitchConfig", "initIndoorSwitchConfig isListTypeMatch false ");
                    guw.b("true".equals(str));
                } else if (koq.b((List) obj)) {
                    ReleaseLogUtil.d("Track_FreeIndoorSwitchConfig", "initIndoorSwitchConfig list is empty ");
                    guw.b("true".equals(str));
                }
            }
        });
    }

    public static void d() {
        LogUtil.a("Track_FreeIndoorSwitchConfig", "uptVibraStepSwitch enter ");
        ArrayList arrayList = new ArrayList(1);
        arrayList.add("900200027");
        njj.d("9002", arrayList, new b());
    }

    static class b implements HiDataReadResultListener {
        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResultIntent(int i, Object obj, int i2, int i3) {
        }

        private b() {
        }

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResult(Object obj, int i, int i2) {
            if (!koq.e(obj, HiSampleConfig.class)) {
                ReleaseLogUtil.d("Track_FreeIndoorSwitchConfig", "initIndoorSwitchConfig isListTypeMatch false ");
                return;
            }
            List list = (List) obj;
            if (koq.b(list)) {
                ReleaseLogUtil.d("Track_FreeIndoorSwitchConfig", "initIndoorSwitchConfig list is empty ");
                return;
            }
            try {
                String str = new JSONObject(((HiSampleConfig) list.get(0)).getConfigData()).getInt("vibraStepSwitch") == 1 ? "true" : "false";
                ReleaseLogUtil.e("Track_FreeIndoorSwitchConfig", "onChange  switch ", str);
                guw.e(true, str);
            } catch (JSONException unused) {
                LogUtil.h("Track_FreeIndoorSwitchConfig", "ConfigDataCallback JSONException ");
            }
        }
    }

    public static void b() {
        e(false, null);
    }

    public static void b(boolean z) {
        ReleaseLogUtil.e("Track_FreeIndoorSwitchConfig", "setSportSwitch  isChecked ", Boolean.valueOf(z));
        e(true, z ? "true" : "false");
        final int i = z ? 1 : 0;
        jdx.b(new Runnable() { // from class: guw.4
            @Override // java.lang.Runnable
            public void run() {
                guw.a(i);
                guw.a();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void e(boolean z, String str) {
        SharedPreferenceManager.e(Integer.toString(20002), "WRITE_INDOOR_SWITCH", z);
        SharedPreferenceManager.e(BaseApplication.e(), Integer.toString(20002), "ihealthlabs", str, new StorageParams());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void a(final int i) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("vibraStepSwitch", Integer.valueOf(i));
        njj.a("9002", "900200027", jsonObject.toString(), new HiDataOperateListener() { // from class: guw.2
            @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
            public void onResult(int i2, Object obj) {
                LogUtil.a("Track_FreeIndoorSwitchConfig", "saveSwitchSampleConfig switchStatus ", Integer.valueOf(i), "errorCode: ", Integer.valueOf(i2), ", object: ", obj);
            }
        }, System.currentTimeMillis());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void a() {
        HiSyncOption hiSyncOption = new HiSyncOption();
        hiSyncOption.setSyncModel(2);
        hiSyncOption.setSyncAction(0);
        hiSyncOption.setSyncDataType(900000000);
        hiSyncOption.setSyncMethod(2);
        HiHealthNativeApi.a(com.huawei.hwcommonmodel.application.BaseApplication.getContext()).synCloud(hiSyncOption, null);
    }
}
