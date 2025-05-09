package defpackage;

import android.content.Context;
import com.huawei.hihealth.motion.HealthOpenSDK;
import com.huawei.hihealth.motion.IExecuteResult;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes3.dex */
public class dss {
    private static dss b;
    private static List<IExecuteResult> e = Collections.synchronizedList(new ArrayList());

    /* renamed from: a, reason: collision with root package name */
    private HealthOpenSDK f11823a;
    private Context d;

    private dss(Context context) {
        this.d = context.getApplicationContext();
    }

    public static dss c(Context context) {
        if (b == null) {
            b = new dss(context.getApplicationContext());
        }
        return b;
    }

    public void a() {
        if (this.f11823a == null) {
            CommonUtil.a("TimeEat_HealthOpenSDKUtil", "Enter initHealthOpenSDK");
            HealthOpenSDK healthOpenSDK = new HealthOpenSDK();
            this.f11823a = healthOpenSDK;
            healthOpenSDK.initSDK(this.d, new d(), "HuaweiHealth");
            CommonUtil.a("TimeEat_HealthOpenSDKUtil", "Leave initHealthOpenSDK");
        }
    }

    static class d implements IExecuteResult {
        private d() {
        }

        @Override // com.huawei.hihealth.motion.IExecuteResult
        public void onSuccess(Object obj) {
            LogUtil.a("TimeEat_HealthOpenSDKUtil", "healthOpenSDKCallback initSDK success");
        }

        @Override // com.huawei.hihealth.motion.IExecuteResult
        public void onFailed(Object obj) {
            LogUtil.a("TimeEat_HealthOpenSDKUtil", "healthOpenSDKCallback : initSDK Failed");
        }

        @Override // com.huawei.hihealth.motion.IExecuteResult
        public void onServiceException(Object obj) {
            LogUtil.a("TimeEat_HealthOpenSDKUtil", "healthOpenSDKCallback : onServiceException");
            if (dss.e == null || dss.e.size() <= 0) {
                return;
            }
            for (IExecuteResult iExecuteResult : dss.e) {
                if (iExecuteResult != null) {
                    iExecuteResult.onServiceException(obj);
                }
            }
        }
    }

    public HealthOpenSDK d() {
        LogUtil.a("Track_HealthOpenSDKUtil", "getHealthOpenSDK");
        HealthOpenSDK healthOpenSDK = this.f11823a;
        if (healthOpenSDK != null) {
            return healthOpenSDK;
        }
        LogUtil.a("Track_HealthOpenSDKUtil", "mHealthOpenSDK no init success,init again");
        a();
        return this.f11823a;
    }

    public void c(IExecuteResult iExecuteResult) {
        if (iExecuteResult == null) {
            LogUtil.h("Track_HealthOpenSDKUtil", "addExceptionListener listener is null");
            return;
        }
        List<IExecuteResult> list = e;
        if (list == null || list.contains(iExecuteResult)) {
            return;
        }
        e.add(iExecuteResult);
    }

    public void e(IExecuteResult iExecuteResult) {
        List<IExecuteResult> list = e;
        if (list == null || !list.contains(iExecuteResult)) {
            return;
        }
        e.remove(iExecuteResult);
    }

    public static void a(boolean z) {
        LogUtil.a("Track_HealthOpenSDKUtil", "setNeedNoteRelogin:", Boolean.valueOf(z));
        SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(e()), "need_relogin", String.valueOf(z), (StorageParams) null);
    }

    public static Integer e() {
        return 1007;
    }
}
