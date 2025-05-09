package defpackage;

import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.outofprocess.mgr.device.ProfileAgent;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.profile.client.connect.ServiceConnectCallback;
import health.compact.a.AuthorizationUtils;
import health.compact.a.Utils;

/* loaded from: classes3.dex */
public class dzu {
    private dzu() {
    }

    public static final dzu a() {
        return d.b;
    }

    static class d {
        private static final dzu b = new dzu();
    }

    public void c() {
        if (!LoginInit.getInstance(BaseApplication.getContext()).getIsLogined()) {
            LogUtil.h("Login_SmartLifeInterator", "syncDeviceInfoToSmartLife getIsLogined is false");
            return;
        }
        if (!dzn.i()) {
            LogUtil.h("Login_SmartLifeInterator", "syncDeviceInfoToSmartLife PrivacyInteractors.isPrivacyStatementAgreed() is false");
            return;
        }
        if (Utils.o()) {
            LogUtil.h("Login_SmartLifeInterator", "syncDeviceInfoToSmartLife isOversea");
            return;
        }
        job.e().b();
        if (!AuthorizationUtils.a(BaseApplication.getContext())) {
            LogUtil.h("Login_SmartLifeInterator", "syncDeviceInfoToSmartLife isNeedShowBeforeLogin");
        } else {
            ProfileAgent.PROFILE_AGENT.connectProfile(new ServiceConnectCallback() { // from class: dzu.5
                @Override // com.huawei.profile.client.connect.ServiceConnectCallback
                public void onConnect() {
                    LogUtil.a("Login_SmartLifeInterator", "profile connected");
                    ProfileAgent.PROFILE_AGENT.setConnected(true);
                    jdx.b(new Runnable() { // from class: dzu.5.1
                        @Override // java.lang.Runnable
                        public void run() {
                            new jqf().e();
                            ProfileAgent.PROFILE_AGENT.disconnectProfile();
                        }
                    });
                }

                @Override // com.huawei.profile.client.connect.ServiceConnectCallback
                public void onDisconnect() {
                    ProfileAgent.PROFILE_AGENT.setConnected(false);
                    LogUtil.a("Login_SmartLifeInterator", "profile disconnected");
                }
            });
            LogUtil.a("Login_SmartLifeInterator", "syncDeviceInfoToSmartLife enter");
        }
    }
}
