package defpackage;

import android.content.Context;
import com.huawei.haf.bundle.AppBundleLauncher;
import com.huawei.haf.bundle.AppBundlePluginProxy;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.plugindevicedetectdiagnosis.DeviceDetectDiagnosisApi;

/* loaded from: classes6.dex */
public class mmj extends AppBundlePluginProxy<DeviceDetectDiagnosisApi> implements DeviceDetectDiagnosisApi {
    private static volatile mmj d;
    private DeviceDetectDiagnosisApi b;

    private mmj() {
        super("DeviceDetectDiagnosisProxy", "PluginDeviceDetectDiagnosis", "com.huawei.plugindevicedetectdiagnosis.DeviceDetectDiagnosisApiImpl");
        this.b = createPluginApi();
    }

    public static mmj e() {
        mmj mmjVar;
        if (d == null) {
            synchronized (mmj.class) {
                if (d == null) {
                    d = new mmj();
                }
                mmjVar = d;
            }
            return mmjVar;
        }
        return d;
    }

    @Override // com.huawei.haf.bundle.AppBundlePluginProxy
    public boolean isPluginAvaiable() {
        return this.b != null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.haf.bundle.AppBundlePluginProxy
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public void initialize(DeviceDetectDiagnosisApi deviceDetectDiagnosisApi) {
        this.b = deviceDetectDiagnosisApi;
    }

    public void b(AppBundleLauncher.InstallCallback installCallback) {
        loadPlugin(installCallback);
    }

    @Override // com.huawei.plugindevicedetectdiagnosis.DeviceDetectDiagnosisApi
    public void openDiagnosisPage(int i, String str, DeviceInfo deviceInfo, String str2, Context context) {
        DeviceDetectDiagnosisApi deviceDetectDiagnosisApi = this.b;
        if (deviceDetectDiagnosisApi != null) {
            deviceDetectDiagnosisApi.openDiagnosisPage(i, str, deviceInfo, str2, context);
        } else {
            loadPlugin();
            LogUtil.a("DeviceDetectDiagnosisProxy", "PluginDeviceDetectDiagnosis loadPlugin and init");
        }
    }
}
