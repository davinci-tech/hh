package defpackage;

import android.content.Context;
import com.huawei.haf.application.BaseApplication;
import com.huawei.hwcrowdtestapi.HealthCrowdTestApi;
import com.huawei.hwcrowdtestapi.HealthFeedbackCallback;
import com.huawei.hwcrowdtestapi.HealthFeedbackParams;
import com.huawei.hwcrowdtestapi.HealthSendLogCallback;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes5.dex */
public class jeq implements HealthCrowdTestApi {
    private static final Object b = new Object();
    private static jeq c;

    /* renamed from: a, reason: collision with root package name */
    private HealthCrowdTestApi f13773a;
    private HealthCrowdTestApi e;

    public jeq() {
        try {
            Object newInstance = Class.forName("com.huawei.healthcrowdtest.HealthCrowdTest").newInstance();
            if (newInstance instanceof HealthCrowdTestApi) {
                this.e = (HealthCrowdTestApi) newInstance;
            }
            Object newInstance2 = Class.forName("com.huawei.healthcrowdtest.HealthHonorCrowdTest").newInstance();
            if (newInstance2 instanceof HealthCrowdTestApi) {
                this.f13773a = (HealthCrowdTestApi) newInstance2;
            }
            LogUtil.a("HealthCrowdTestProxy", "init HealthCrowdTest ok");
        } catch (ClassNotFoundException unused) {
            LogUtil.b("HealthCrowdTestProxy", "ClassNotFoundException");
        } catch (Exception unused2) {
            LogUtil.b("HealthCrowdTestProxy", "Exception");
        }
    }

    public static jeq e() {
        jeq jeqVar;
        synchronized (b) {
            if (c == null) {
                c = new jeq();
            }
            jeqVar = c;
        }
        return jeqVar;
    }

    @Override // com.huawei.hwcrowdtestapi.HealthCrowdTestApi
    public void init(Context context) {
        LogUtil.a("HealthCrowdTestProxy", "init");
        if (context == null) {
            LogUtil.h("HealthCrowdTestProxy", "init context is null");
            if (this.e != null) {
                LogUtil.h("HealthCrowdTestProxy", "init context is null, mHealthCrowdTestApi.init(BaseApplication.getAppContext()");
                this.e.init(BaseApplication.e());
            }
            if (this.f13773a != null) {
                LogUtil.h("HealthCrowdTestProxy", "init context is null, mHonorHealthCrowdTestApi.init(BaseApplication.getAppContext()");
                this.f13773a.init(BaseApplication.e());
                return;
            }
            return;
        }
        HealthCrowdTestApi healthCrowdTestApi = this.e;
        if (healthCrowdTestApi != null) {
            healthCrowdTestApi.init(context.getApplicationContext());
        }
        HealthCrowdTestApi healthCrowdTestApi2 = this.f13773a;
        if (healthCrowdTestApi2 != null) {
            healthCrowdTestApi2.init(context.getApplicationContext());
        }
    }

    @Override // com.huawei.hwcrowdtestapi.HealthCrowdTestApi
    public void gotoFeedback(Context context, jep jepVar, HealthFeedbackParams healthFeedbackParams, HealthFeedbackCallback healthFeedbackCallback) {
        if (healthFeedbackParams != null && healthFeedbackParams.isHonorDevice()) {
            if (this.f13773a != null) {
                LogUtil.a("HealthCrowdTestProxy", "gotoFeedback mHonorHealthCrowdTestApi.gotoFeedback");
                this.f13773a.gotoFeedback(context, jepVar, healthFeedbackParams, healthFeedbackCallback);
                return;
            }
            return;
        }
        if (this.e != null) {
            LogUtil.a("HealthCrowdTestProxy", "gotoFeedback mHealthCrowdTestApi.gotoFeedback");
            this.e.gotoFeedback(context, jepVar, healthFeedbackParams, healthFeedbackCallback);
        }
    }

    @Override // com.huawei.hwcrowdtestapi.HealthCrowdTestApi
    public void uninit(Context context) {
        LogUtil.a("HealthCrowdTestProxy", "uninit");
        if (context == null) {
            LogUtil.h("HealthCrowdTestProxy", "uninit context is null");
            return;
        }
        HealthCrowdTestApi healthCrowdTestApi = this.e;
        if (healthCrowdTestApi != null) {
            healthCrowdTestApi.uninit(context.getApplicationContext());
        }
        HealthCrowdTestApi healthCrowdTestApi2 = this.f13773a;
        if (healthCrowdTestApi2 != null) {
            healthCrowdTestApi2.uninit(context.getApplicationContext());
        }
    }

    @Override // com.huawei.hwcrowdtestapi.HealthCrowdTestApi
    public void sendLog(Context context, HealthFeedbackParams healthFeedbackParams, String str, boolean z, HealthSendLogCallback healthSendLogCallback) {
        if (str != null && str.contains("-HONOR")) {
            if (this.f13773a != null) {
                LogUtil.a("HealthCrowdTestProxy", "sendLog mHonorHealthCrowdTestApi.sendLog");
                this.f13773a.sendLog(context, healthFeedbackParams, str, z, healthSendLogCallback);
                return;
            }
            return;
        }
        if (this.e != null) {
            LogUtil.a("HealthCrowdTestProxy", "sendLog mHealthCrowdTestApi.sendLog");
            this.e.sendLog(context, healthFeedbackParams, str, z, healthSendLogCallback);
        }
    }

    @Override // com.huawei.hwcrowdtestapi.HealthCrowdTestApi
    public void checkLogUploadStatus(Context context) {
        HealthCrowdTestApi healthCrowdTestApi = this.e;
        if (healthCrowdTestApi != null) {
            healthCrowdTestApi.checkLogUploadStatus(context);
        }
        HealthCrowdTestApi healthCrowdTestApi2 = this.f13773a;
        if (healthCrowdTestApi2 != null) {
            healthCrowdTestApi2.checkLogUploadStatus(context);
        }
    }

    @Override // com.huawei.hwcrowdtestapi.HealthCrowdTestApi
    public void logout() {
        LogUtil.a("HealthCrowdTestProxy", "logout");
        HealthCrowdTestApi healthCrowdTestApi = this.e;
        if (healthCrowdTestApi != null) {
            healthCrowdTestApi.logout();
        }
        HealthCrowdTestApi healthCrowdTestApi2 = this.f13773a;
        if (healthCrowdTestApi2 != null) {
            healthCrowdTestApi2.logout();
        }
    }

    @Override // com.huawei.hwcrowdtestapi.HealthCrowdTestApi
    public void setProductType(int i) {
        LogUtil.a("HealthCrowdTestProxy", "setProductType");
        HealthCrowdTestApi healthCrowdTestApi = this.e;
        if (healthCrowdTestApi != null) {
            healthCrowdTestApi.setProductType(i);
        }
        HealthCrowdTestApi healthCrowdTestApi2 = this.f13773a;
        if (healthCrowdTestApi2 != null) {
            healthCrowdTestApi2.setProductType(i);
        }
    }
}
