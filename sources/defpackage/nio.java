package defpackage;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.unitedevice.constant.ConnectState;
import com.huawei.unitedevice.constant.WearEngineModule;
import com.huawei.unitedevice.manager.EngineLogicBaseManager;
import java.io.UnsupportedEncodingException;

/* loaded from: classes6.dex */
public class nio extends EngineLogicBaseManager {
    private static volatile nio b;
    private static final Object c = new Object();

    private nio() {
        super(BaseApplication.getContext());
    }

    public static nio c() {
        LogUtil.a("ThirdAuthorizeMgr", "ThirdAuthorizeMgr getInstance.");
        if (b == null) {
            synchronized (c) {
                if (b == null) {
                    b = new nio();
                }
            }
        }
        return b;
    }

    @Override // com.huawei.unitedevice.manager.EngineLogicBaseManager
    public void onReceiveDeviceCommand(DeviceInfo deviceInfo, int i, spn spnVar) {
        String str;
        LogUtil.a("ThirdAuthorizeMgr", "receiveFromDevice errorCode ", Integer.valueOf(i));
        if (spnVar == null || spnVar.b() == null) {
            LogUtil.h("ThirdAuthorizeMgr", "receiveFromDevice Message is null");
            return;
        }
        try {
            str = new String(spnVar.b(), "UTF_8");
        } catch (UnsupportedEncodingException unused) {
            LogUtil.b("ThirdAuthorizeMgr", "receiveFromDevice get message data error!");
            str = "";
        }
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("ThirdAuthorizeMgr", "receiveFromDevice tempCode is empty ");
        } else {
            LogUtil.a("ThirdAuthorizeMgr", "receiveFromDevice tempCode length == ", Integer.valueOf(str.length()));
            c(str);
        }
    }

    @Override // com.huawei.unitedevice.manager.EngineLogicBaseManager
    public WearEngineModule getManagerModule() {
        return WearEngineModule.LOGIN_AUTHORZE_MODULE;
    }

    @Override // com.huawei.unitedevice.manager.EngineLogicBaseManager
    public void onDeviceConnectionChange(ConnectState connectState, DeviceInfo deviceInfo) {
        if (connectState == ConnectState.CONNECTED) {
            LogUtil.a("ThirdAuthorizeMgr", "onDeviceConnectionChange connect ", deviceInfo.toString(), " device identify: ", deviceInfo.getDeviceIdentify());
        }
    }

    private void c(String str) {
        Context context = BaseApplication.getContext();
        if (context == null) {
            LogUtil.b("ThirdAuthorizeMgr", "startAuthorizePage: context is null!");
            return;
        }
        Intent intent = new Intent();
        intent.setFlags(268435456);
        intent.putExtra("requestCode", 6012);
        intent.putExtra("requestParam", str);
        intent.setClassName(context, "com.huawei.health.HuaweiLoginActivity");
        context.startActivity(intent);
    }

    @Override // com.huawei.unitedevice.manager.EngineLogicBaseManager
    public String getWearPkgName() {
        return "com.huawei.health:accountAuth";
    }

    @Override // com.huawei.unitedevice.manager.EngineLogicBaseManager
    public String getWearFingerprint() {
        return "UniteDeviceManagement";
    }
}
