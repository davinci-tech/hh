package defpackage;

import android.os.Build;
import android.text.TextUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.File;
import java.io.IOException;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class ntf {

    /* renamed from: a, reason: collision with root package name */
    private static volatile ntf f15481a;

    private ntf() {
        f();
    }

    public static ntf b() {
        if (f15481a == null) {
            synchronized (ntf.class) {
                if (f15481a == null) {
                    f15481a = new ntf();
                }
            }
        }
        return f15481a;
    }

    public void f() {
        ThreadPoolManager.d().execute(new Runnable() { // from class: ntf.1
            @Override // java.lang.Runnable
            public void run() {
                ntf.this.j();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        String t = CommonUtil.t(h() + File.separator + "pageConfigs.json");
        LogUtil.a("TemporaryConfigManager", "temporaryConfigStr: ", t);
        SharedPreferenceManager.e(BaseApplication.getContext(), "SAVE_TEMPORARY_CONFIG_TO_SP_KEY", "SAVE_TEMPORARY_CONFIG_TO_SP_KEY", t, new StorageParams());
    }

    private String h() {
        try {
            return BaseApplication.getContext().getFilesDir().getCanonicalPath() + File.separator + "healthcloud" + File.separator + "com.huawei.health_common_config";
        } catch (IOException unused) {
            LogUtil.a("TemporaryConfigManager", "IOException");
            return "";
        }
    }

    public boolean i() {
        ntb ntbVar = (ntb) HiJsonUtil.e(SharedPreferenceManager.b(BaseApplication.getContext(), "SAVE_TEMPORARY_CONFIG_TO_SP_KEY", "SAVE_TEMPORARY_CONFIG_TO_SP_KEY"), ntb.class);
        if (ntbVar == null) {
            ReleaseLogUtil.d("TemporaryConfigManager", "isOpenCourseNewStyle content == null");
            return true;
        }
        ReleaseLogUtil.e("TemporaryConfigManager", "isOpenCourseNewStyle:", Integer.valueOf(ntbVar.b()));
        return ntbVar.b() == 0;
    }

    public boolean e() {
        ntb ntbVar = (ntb) HiJsonUtil.e(SharedPreferenceManager.b(BaseApplication.getContext(), "SAVE_TEMPORARY_CONFIG_TO_SP_KEY", "SAVE_TEMPORARY_CONFIG_TO_SP_KEY"), ntb.class);
        if (ntbVar == null) {
            ReleaseLogUtil.d("TemporaryConfigManager", "isOpenCourseNewStyle content == null");
            return true;
        }
        int a2 = ntbVar.a();
        ReleaseLogUtil.e("TemporaryConfigManager", "AfterProcessSwitch:", Integer.valueOf(a2));
        return a2 == 0;
    }

    public boolean a() {
        if (Utils.o()) {
            return false;
        }
        if (CommonUtil.as()) {
            return true;
        }
        ntb ntbVar = (ntb) HiJsonUtil.e(SharedPreferenceManager.b(BaseApplication.getContext(), "SAVE_TEMPORARY_CONFIG_TO_SP_KEY", "SAVE_TEMPORARY_CONFIG_TO_SP_KEY"), ntb.class);
        if (ntbVar == null) {
            ReleaseLogUtil.d("TemporaryConfigManager", "isOpenCourseNewStyle content == null");
            return false;
        }
        String c = ntbVar.c();
        ReleaseLogUtil.d("TemporaryConfigManager", "isOpenCourseNewStyle supportDevice: ", c);
        if (TextUtils.isEmpty(c)) {
            return false;
        }
        if ("AllDeviceType".equals(c)) {
            return true;
        }
        if ("ThirdPartyType".equals(c)) {
            return !CommonUtil.bd();
        }
        if (!"defineModelType".equals(c)) {
            return false;
        }
        List<String> f = ntbVar.f();
        if (koq.b(f)) {
            return false;
        }
        return f.contains(Build.MODEL);
    }

    public int c() {
        ntb ntbVar = (ntb) HiJsonUtil.e(SharedPreferenceManager.b(BaseApplication.getContext(), "SAVE_TEMPORARY_CONFIG_TO_SP_KEY", "SAVE_TEMPORARY_CONFIG_TO_SP_KEY"), ntb.class);
        if (ntbVar == null) {
            ReleaseLogUtil.d("TemporaryConfigManager", "isOpenCourseNewStyle content == null");
            return 30;
        }
        int d = ntbVar.d();
        if (d == 0) {
            return 30;
        }
        ReleaseLogUtil.e("TemporaryConfigManager", "abnormalStepTipsPeriod:", Integer.valueOf(d));
        return d;
    }

    public int d() {
        ntb ntbVar = (ntb) HiJsonUtil.e(SharedPreferenceManager.b(BaseApplication.getContext(), "SAVE_TEMPORARY_CONFIG_TO_SP_KEY", "SAVE_TEMPORARY_CONFIG_TO_SP_KEY"), ntb.class);
        if (ntbVar == null) {
            ReleaseLogUtil.d("TemporaryConfigManager", "getAbnormalStepContinue content == null");
            return 2;
        }
        int e = ntbVar.e();
        if (e == 0) {
            return 2;
        }
        ReleaseLogUtil.e("TemporaryConfigManager", "getAbnormalStepContinue:", Integer.valueOf(e));
        return e;
    }

    public <T> T e(String str, Class<T> cls) {
        JSONArray jSONArray;
        int i;
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("TemporaryConfigManager", "configName is empty.");
            return null;
        }
        String b = SharedPreferenceManager.b(BaseApplication.getContext(), "SAVE_TEMPORARY_CONFIG_TO_SP_KEY", "SAVE_TEMPORARY_CONFIG_TO_SP_KEY");
        if (TextUtils.isEmpty(b)) {
            LogUtil.h("TemporaryConfigManager", "arkuixConfigStr is empty.");
            return null;
        }
        LogUtil.a("TemporaryConfigManager", "getPageConfig arkuixConfigStr:", b);
        try {
            jSONArray = new JSONObject(b).getJSONArray("arkuixConfigs");
        } catch (JSONException unused) {
            LogUtil.b("TemporaryConfigManager", "occur json error.");
        }
        if (jSONArray.length() == 0) {
            LogUtil.h("TemporaryConfigManager", "config list is empty.");
            return null;
        }
        for (i = 0; i < jSONArray.length(); i++) {
            JSONObject jSONObject = jSONArray.getJSONObject(i);
            if (jSONObject.getString("configName").equals(str)) {
                return (T) HiJsonUtil.e(jSONObject.toString(), cls);
            }
        }
        LogUtil.h("TemporaryConfigManager", "not found config by name: ", str);
        return null;
    }
}
