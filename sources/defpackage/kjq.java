package defpackage;

import android.app.Service;
import android.os.Bundle;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hihealth.motion.HealthOpenSDK;
import com.huawei.hihealth.motion.ICommonReport;
import com.huawei.hihealth.motion.IExecuteResult;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.StepNotificationByHardWareUtils;

/* loaded from: classes.dex */
public class kjq {

    /* renamed from: a, reason: collision with root package name */
    private Service f14421a;
    private HealthOpenSDK b;
    private e c;
    private kjo d;
    private IExecuteResult e;

    public void bOP_(Service service) {
        if (this.d == null) {
            this.d = kjo.e(service);
        }
        this.f14421a = service;
        this.c = new e();
        this.e = new b();
        HealthOpenSDK healthOpenSDK = new HealthOpenSDK();
        this.b = healthOpenSDK;
        healthOpenSDK.initSDK(BaseApplication.getContext(), new IExecuteResult() { // from class: kjq.1
            @Override // com.huawei.hihealth.motion.IExecuteResult
            public void onSuccess(Object obj) {
                LogUtil.a("KeepAliveOptimize", "initSDK Success");
                kjq.this.c();
            }

            @Override // com.huawei.hihealth.motion.IExecuteResult
            public void onFailed(Object obj) {
                LogUtil.h("KeepAliveOptimize", "initSDK failed");
            }

            @Override // com.huawei.hihealth.motion.IExecuteResult
            public void onServiceException(Object obj) {
                LogUtil.b("KeepAliveOptimize", "initSDK exception");
            }
        }, "PhoneService");
    }

    public void b(final DeviceInfo deviceInfo) {
        if (!lcu.e()) {
            LogUtil.h("KeepAliveOptimize", "this is huawei system");
        } else {
            jqi.a().getSwitchSetting("device_notification_key", new IBaseResponseCallback() { // from class: kjq.4
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    LogUtil.a("KeepAliveOptimize", "updateDeviceNotify");
                    boolean z = true;
                    if (i == 0 && (obj instanceof String)) {
                        z = true ^ "0".equals((String) obj);
                        LogUtil.a("KeepAliveOptimize", "onlaod updateDeviceNotify: ", Boolean.valueOf(z));
                    }
                    LogUtil.a("KeepAliveOptimize", "onlaod updateDeviceNotify, result: ", Boolean.valueOf(z));
                    if (z) {
                        kjq.this.d.a(deviceInfo);
                    } else {
                        kjq.this.d.a();
                    }
                }
            });
        }
    }

    public void c() {
        HealthOpenSDK healthOpenSDK = this.b;
        if (healthOpenSDK != null) {
            healthOpenSDK.b(this.c, this.e);
            LogUtil.a("KeepAliveOptimize", "register mStepCallback:", this.c);
            this.b.d(new IExecuteResult() { // from class: kjq.3
                @Override // com.huawei.hihealth.motion.IExecuteResult
                public void onFailed(Object obj) {
                }

                @Override // com.huawei.hihealth.motion.IExecuteResult
                public void onServiceException(Object obj) {
                }

                @Override // com.huawei.hihealth.motion.IExecuteResult
                public void onSuccess(Object obj) {
                    if (obj != null && (obj instanceof Bundle)) {
                        boolean z = ((Bundle) obj).getBoolean("stepsNotifiState");
                        LogUtil.a("KeepAliveOptimize", "isOpenRealTimeStep :", Boolean.valueOf(z));
                        if (z && StepNotificationByHardWareUtils.a()) {
                            kjq.this.d();
                        }
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        this.b.b(new IExecuteResult() { // from class: kjq.5
            @Override // com.huawei.hihealth.motion.IExecuteResult
            public void onFailed(Object obj) {
            }

            @Override // com.huawei.hihealth.motion.IExecuteResult
            public void onServiceException(Object obj) {
            }

            @Override // com.huawei.hihealth.motion.IExecuteResult
            public void onSuccess(Object obj) {
                if (!(obj instanceof Bundle)) {
                    LogUtil.h("KeepAliveOptimize", "getTodaySportData is not bundle");
                    return;
                }
                Bundle bundle = (Bundle) obj;
                StringBuilder sb = new StringBuilder(16);
                sb.append("Fitness data:steps:");
                sb.append(bundle.getInt("step"));
                sb.append("floor:");
                sb.append(bundle.getInt("floor"));
                sb.append("calories:");
                sb.append(bundle.getInt("carior"));
                sb.append("target:");
                sb.append(bundle.getInt("stepTarget"));
                LogUtil.a("KeepAliveOptimize", sb.toString());
                new kjr().a(kjq.this.f14421a, bundle.getInt("step"), bundle.getInt("carior"), bundle.getInt("stepTarget"));
            }
        });
    }

    public void a() {
        if (this.b == null) {
            LogUtil.h("KeepAliveOptimize", "onDestroy mHealthOpenSdk null");
            return;
        }
        e eVar = this.c;
        if (eVar == null) {
            LogUtil.h("KeepAliveOptimize", "onDestroy mStepCallback null");
            return;
        }
        LogUtil.a("KeepAliveOptimize", "unRegister mStepCallback:", eVar);
        this.b.d((ICommonReport) this.c);
        this.f14421a.stopForeground(false);
        kjo kjoVar = this.d;
        if (kjoVar != null) {
            kjoVar.e();
            this.d = null;
        }
    }

    /* loaded from: classes5.dex */
    static class e implements ICommonReport {
        @Override // com.huawei.hihealth.motion.ICommonReport
        public void report(Bundle bundle) {
        }

        private e() {
        }
    }

    /* loaded from: classes5.dex */
    static class b implements IExecuteResult {
        private b() {
        }

        @Override // com.huawei.hihealth.motion.IExecuteResult
        public void onSuccess(Object obj) {
            LogUtil.a("KeepAliveOptimize", "onSuccess");
        }

        @Override // com.huawei.hihealth.motion.IExecuteResult
        public void onFailed(Object obj) {
            LogUtil.h("KeepAliveOptimize", "onFailed");
        }

        @Override // com.huawei.hihealth.motion.IExecuteResult
        public void onServiceException(Object obj) {
            LogUtil.b("KeepAliveOptimize", "onServiceException");
        }
    }
}
