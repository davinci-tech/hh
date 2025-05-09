package defpackage;

import android.app.Activity;
import android.text.TextUtils;
import android.view.WindowManager;
import android.widget.ListView;
import com.huawei.health.device.connectivity.comm.MeasurableDevice;
import com.huawei.hwcloudmodel.callback.ICloudOperationResult;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.ui.commonui.checkbox.HealthCheckBox;
import health.compact.a.CommonUtil;
import health.compact.a.SharedPreferenceManager;

/* loaded from: classes3.dex */
public class cqb {
    private static final Object c = new Object();
    private static cqb e;

    public static cqb d() {
        cqb cqbVar;
        synchronized (c) {
            if (e == null) {
                e = new cqb();
            }
            cqbVar = e;
        }
        return cqbVar;
    }

    public int c(String str, int i, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            LogUtil.h("PluginDevice_ScaleBusinessHelper", "differentDaysByMillisecond key is null or uniqueId is null");
        }
        String b = SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(10000), str + str2);
        if (TextUtils.isEmpty(b)) {
            LogUtil.h("PluginDevice_ScaleBusinessHelper", "differentDaysByMillisecond is first");
            return i;
        }
        int currentTimeMillis = (int) ((System.currentTimeMillis() - CommonUtil.g(b)) / 86400000);
        LogUtil.a("PluginDevice_ScaleBusinessHelper", "differentDaysByMillisecond get config days is: ", Integer.valueOf(currentTimeMillis));
        return currentTimeMillis;
    }

    public void c(final HealthCheckBox healthCheckBox, final ctk ctkVar) {
        if (healthCheckBox == null || ctkVar == null) {
            LogUtil.h("PluginDevice_ScaleBusinessHelper", "synAutoUpgradeStatus autoUpgradeSwitch is null or wiFiDevice is null");
        } else {
            csf.c(ctkVar.d(), healthCheckBox.isChecked() ? "1" : "0", new ICloudOperationResult() { // from class: cqc
                @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
                public final void operationResult(Object obj, String str, boolean z) {
                    cqb.b(HealthCheckBox.this, ctkVar, obj, str, z);
                }
            });
        }
    }

    static /* synthetic */ void b(HealthCheckBox healthCheckBox, ctk ctkVar, Object obj, String str, boolean z) {
        LogUtil.a("PluginDevice_ScaleBusinessHelper", "showConfigureAutoUpgradeDialog AutoUpgradeStatus isSuccess:", Boolean.valueOf(z));
        if (z) {
            csc.a(ctkVar.d(), healthCheckBox.isChecked() ? "1" : "0");
        }
    }

    public int Kt_(ListView listView, Activity activity) {
        if (listView == null || activity == null) {
            LogUtil.h("PluginDevice_ScaleBusinessHelper", "getDialogHeight listView is null or activity is null");
            return 0;
        }
        int Va_ = dij.Va_(listView);
        Object systemService = activity.getSystemService(Constants.NATIVE_WINDOW_SUB_DIR);
        if (!(systemService instanceof WindowManager)) {
            return 0;
        }
        int height = (((WindowManager) systemService).getDefaultDisplay().getHeight() / 2) - dij.d(BaseApplication.getContext(), 84.0f);
        return Va_ < height ? Va_ : height;
    }

    public boolean c(ctk ctkVar, String str) {
        if (ctkVar == null || ctkVar.b().k() != 2) {
            return false;
        }
        MeasurableDevice d = ceo.d().d(str, true);
        return d == null || (d instanceof ctk);
    }

    public boolean b(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("PluginDevice_ScaleBusinessHelper", "isBluetoothDevice uniqueId is null");
            return false;
        }
        return ceo.d().d(str, true) instanceof cxh;
    }

    public void c() {
        e();
    }

    private static void e() {
        synchronized (c) {
            e = null;
        }
    }
}
