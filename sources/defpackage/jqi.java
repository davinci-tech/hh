package defpackage;

import android.os.Process;
import android.text.TextUtils;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.outofprocess.mgr.switchsetting.SwitchSettingInterface;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import health.compact.a.CompileParameterUtil;
import java.util.List;

/* loaded from: classes5.dex */
public class jqi implements SwitchSettingInterface {

    /* renamed from: a, reason: collision with root package name */
    private static jqi f14024a;

    private jqi() {
    }

    public static jqi a() {
        jqi jqiVar;
        LogUtil.a("HwSwitchSettingsManager", "HwSwitchSettingsManager singleton");
        synchronized (jqi.class) {
            if (f14024a == null) {
                f14024a = new jqi();
            }
            jqiVar = f14024a;
        }
        return jqiVar;
    }

    @Override // com.huawei.hwdevice.outofprocess.mgr.switchsetting.SwitchSettingInterface
    public void setSwitchSetting(String str, String str2, IBaseResponseCallback iBaseResponseCallback) {
        jqk.b(str, str2, iBaseResponseCallback);
    }

    @Override // com.huawei.hwdevice.outofprocess.mgr.switchsetting.SwitchSettingInterface
    public void setSwitchSetting(String str, String str2, String str3, IBaseResponseCallback iBaseResponseCallback) {
        jqk.e(str, str2, str3, iBaseResponseCallback);
    }

    @Override // com.huawei.hwdevice.outofprocess.mgr.switchsetting.SwitchSettingInterface
    public void getSwitchSetting(String str, IBaseResponseCallback iBaseResponseCallback) {
        jqk.a(str, iBaseResponseCallback);
    }

    @Override // com.huawei.hwdevice.outofprocess.mgr.switchsetting.SwitchSettingInterface
    public void getSwitchSetting(List<String> list, IBaseResponseCallback iBaseResponseCallback) {
        jqk.c(list, iBaseResponseCallback);
    }

    @Override // com.huawei.hwdevice.outofprocess.mgr.switchsetting.SwitchSettingInterface
    public void getSwitchSetting(String str, String str2, IBaseResponseCallback iBaseResponseCallback) {
        jqk.d(str, str2, iBaseResponseCallback);
    }

    @Override // com.huawei.hwdevice.outofprocess.mgr.switchsetting.SwitchSettingInterface
    public void sendSetSwitchSettingCmd(boolean z, String str, int i, int i2, IBaseResponseCallback iBaseResponseCallback) {
        b();
        jql.c(z, str, i, i2, iBaseResponseCallback);
    }

    @Override // com.huawei.hwdevice.outofprocess.mgr.switchsetting.SwitchSettingInterface
    public void sendSetSwitchSettingCmd(byte[] bArr, String str, int i, int i2) {
        b();
        jql.b(bArr, str, i, i2);
    }

    @Override // com.huawei.hwdevice.outofprocess.mgr.switchsetting.SwitchSettingInterface
    public void sendSetSwitchSettingCmd(int i, String str, String str2) {
        b();
        jql.a(i, str, str2);
    }

    @Override // com.huawei.hwdevice.outofprocess.mgr.switchsetting.SwitchSettingInterface
    public void sendSettingSwitchCommand(String str, String str2, long j, String str3) {
        b();
        jql.e(str, str2, j, str3);
    }

    @Override // com.huawei.hwdevice.outofprocess.mgr.switchsetting.SwitchSettingInterface
    public void setSwitchSettingToLocal(String str, String str2, int i) {
        b();
        jqm.e(str, str2, i);
    }

    @Override // com.huawei.hwdevice.outofprocess.mgr.switchsetting.SwitchSettingInterface
    public String getSwitchSettingFromLocal(String str, int i) {
        b();
        return jqm.a(str, i);
    }

    @Override // com.huawei.hwdevice.outofprocess.mgr.switchsetting.SwitchSettingInterface
    public void setSwitchSettingToDb(String str, String str2) {
        jqm.c(str, str2);
    }

    @Override // com.huawei.hwdevice.outofprocess.mgr.switchsetting.SwitchSettingInterface
    public String getSwitchSettingFromDb(String str) {
        return jqm.e(str);
    }

    private void b() {
        if (CompileParameterUtil.a("IS_RELEASE_VERSION")) {
            return;
        }
        String d = CommonUtil.d(Process.myPid());
        if (TextUtils.isEmpty(d) || BaseApplication.getAppPackage().equals(d)) {
            return;
        }
        throw new RuntimeException("DeviceConfig must init in main process." + d);
    }
}
