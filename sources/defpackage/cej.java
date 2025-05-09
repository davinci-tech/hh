package defpackage;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.huawei.health.device.PluginDeviceAdapter;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.device.model.MeasureResult;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.pluginbase.PluginBaseAdapter;
import health.compact.a.CommonUtil;

/* loaded from: classes3.dex */
public class cej extends mml {
    private static final Object b = new Object();
    private static cej c;

    /* renamed from: a, reason: collision with root package name */
    private dfg f661a = dfg.d();
    private PluginDeviceAdapter e;

    @Override // defpackage.mml
    public void finish() {
    }

    private cej() {
    }

    @Override // defpackage.mml
    public void init(Context context) {
        d();
    }

    private void d() {
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
        LogUtil.a("PluginDevice_PluginDevice", "PluginDevice initDatabase ", accountInfo);
        if (!TextUtils.isEmpty(accountInfo)) {
            SharedPreferences sharedPreferences = BaseApplication.getContext().getSharedPreferences("login_data", 0);
            String string = sharedPreferences.getString("old_user_id", null);
            if (TextUtils.isEmpty(string)) {
                LogUtil.a("PluginDevice_PluginDevice", "PluginDevice initDatabase oldHuid null");
                sharedPreferences.edit().putString("old_user_id", accountInfo).commit();
                return;
            } else {
                if (accountInfo.equals(string)) {
                    return;
                }
                LogUtil.a("PluginDevice_PluginDevice", "PluginDevice initDatabase huid != oldHuid");
                sharedPreferences.edit().putString("old_user_id", accountInfo).commit();
                ceo.d().b();
                if (CommonUtil.ce()) {
                    cvw.c(cpl.c().h(), "PluginDevice_PluginDevice");
                }
                LogUtil.a("PluginDevice_PluginDevice", "PluginDevice initDatabase sendBroadcast");
                return;
            }
        }
        LogUtil.a("PluginDevice_PluginDevice", "PluginDevice initDatabase huid null");
    }

    public static cej e() {
        cej cejVar;
        synchronized (b) {
            if (c == null) {
                c = new cej();
            }
            cejVar = c;
        }
        return cejVar;
    }

    public String c(HealthDevice.HealthDeviceKind healthDeviceKind, MeasureResult.MeasureResultListener measureResultListener) {
        LogUtil.a("PluginDevice_PluginDevice", "PluginDevice startMeasureBackground");
        return this.f661a.c(healthDeviceKind, measureResultListener);
    }

    public void e(String str, String str2) {
        LogUtil.a("PluginDevice_PluginDevice", "PluginDevice stopMeasureBackground");
        this.f661a.e(str, str2);
    }

    public PluginDeviceAdapter c() {
        if (getAdapter() instanceof PluginDeviceAdapter) {
            PluginDeviceAdapter pluginDeviceAdapter = (PluginDeviceAdapter) getAdapter();
            this.e = pluginDeviceAdapter;
            return pluginDeviceAdapter;
        }
        return this.e;
    }

    @Override // defpackage.mml
    public void setAdapter(PluginBaseAdapter pluginBaseAdapter) {
        if (pluginBaseAdapter == null) {
            return;
        }
        super.setAdapter(pluginBaseAdapter);
        if (pluginBaseAdapter instanceof PluginDeviceAdapter) {
            LogUtil.a("PluginDevice_PluginDevice", "adapter is not null");
            this.e = (PluginDeviceAdapter) pluginBaseAdapter;
        }
    }
}
