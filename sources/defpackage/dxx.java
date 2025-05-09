package defpackage;

import android.content.Context;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.userlabelmgr.api.UserLabelServiceApi;
import com.huawei.health.userprofilemgr.model.BaseResponseCallback;
import com.huawei.hmf.annotation.ApiDefine;
import com.huawei.hmf.annotation.Singleton;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import java.util.HashMap;
import java.util.List;

@ApiDefine(uri = UserLabelServiceApi.class)
@Singleton
/* loaded from: classes3.dex */
public class dxx implements UserLabelServiceApi {
    @Override // com.huawei.health.userlabelmgr.api.UserLabelServiceApi
    public void registerCallback(int i) {
        if (i == 1) {
            dxo.o().l();
            return;
        }
        if (i == 2) {
            dxp.d().a();
            return;
        }
        if (i == 3) {
            dxk.d().b();
        } else if (i == 4) {
            dxl.d().f();
        } else {
            if (i != 5) {
                return;
            }
            dxu.e().a();
        }
    }

    @Override // com.huawei.health.userlabelmgr.api.UserLabelServiceApi
    public void unRegisterCallback(int i) {
        if (i == 1) {
            dxo.o().m();
            return;
        }
        if (i == 2) {
            dxp.d().c();
            return;
        }
        if (i == 3) {
            dxk.d().e();
        } else if (i == 4) {
            dxl.d().i();
        } else {
            if (i != 5) {
                return;
            }
            dxu.e().d();
        }
    }

    @Override // com.huawei.health.userlabelmgr.api.UserLabelServiceApi
    public void subscribeTrackDataChanged(Context context) {
        dya.c(context);
    }

    @Override // com.huawei.health.userlabelmgr.api.UserLabelServiceApi
    public void unSubscribeTrackData(Context context) {
        dya.a(context);
    }

    @Override // com.huawei.health.userlabelmgr.api.UserLabelServiceApi
    public void initLabel(String str, String str2, String str3) {
        dxw.a(BaseApplication.getContext()).b(str, str2, str3);
    }

    @Override // com.huawei.health.userlabelmgr.api.UserLabelServiceApi
    public void initDailySleepLabel(fda fdaVar) {
        if (fdaVar == null) {
            return;
        }
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
        dxw.a(BaseApplication.getContext()).b("health_daily_sleep_problem", dyc.d("health_daily_sleep_problem", fdaVar.h()), accountInfo);
        dxw.a(BaseApplication.getContext()).b("health_daily_sleep_target_problem", dyc.d("health_daily_sleep_target_problem", fdaVar.c()), accountInfo);
    }

    @Override // com.huawei.health.userlabelmgr.api.UserLabelServiceApi
    public void initMonthlySleepLabel(fdh fdhVar) {
        if (fdhVar == null) {
            return;
        }
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
        dxw.a(BaseApplication.getContext()).b("health_sleep_rhythm_type", dyc.d("health_sleep_rhythm_type", fdhVar.o()), accountInfo);
        dxw.a(BaseApplication.getContext()).b("health_monthly_sleep_problem", dyc.d("health_monthly_sleep_problem", fdhVar.f()), accountInfo);
    }

    @Override // com.huawei.health.userlabelmgr.api.UserLabelServiceApi
    public void destroyOdmf() {
        dxy.b().d();
    }

    @Override // com.huawei.health.userlabelmgr.api.UserLabelServiceApi
    public void saveThirtyDaysTrackDataToOdmf(Context context) {
        dya.d(context);
    }

    @Override // com.huawei.health.userlabelmgr.api.UserLabelServiceApi
    public void saveTrackDataToOdmf(String str) {
        dxy.b().b(str);
    }

    @Override // com.huawei.health.userlabelmgr.api.UserLabelServiceApi
    public void saveBindingDeviceToOdmf(String str) {
        dxy.b().c(str);
    }

    @Override // com.huawei.health.userlabelmgr.api.UserLabelServiceApi
    public List<String> getAllLabels(String str) {
        return dxw.a(BaseApplication.getContext()).a(str);
    }

    @Override // com.huawei.health.userlabelmgr.api.UserLabelServiceApi
    public List<String> getLabels(List<String> list, String str) {
        return dxw.a(BaseApplication.getContext()).c(list, str);
    }

    @Override // com.huawei.health.userlabelmgr.api.UserLabelServiceApi
    public void clickRecord(int i, String str) {
        dxf.d().a(i, str);
    }

    @Override // com.huawei.health.userlabelmgr.api.UserLabelServiceApi
    public boolean isContainsHealthLabel(List<String> list) {
        return dxw.a(BaseApplication.getContext()).b(list);
    }

    @Override // com.huawei.health.userlabelmgr.api.UserLabelServiceApi
    public boolean isContainsOtherLabel(List<String> list) {
        return dxw.a(BaseApplication.getContext()).d(list);
    }

    @Override // com.huawei.health.userlabelmgr.api.UserLabelServiceApi
    public void getPengineAllLabels(Context context) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: dxx.5
            @Override // java.lang.Runnable
            public void run() {
                try {
                    LogUtil.a("UserLabelServiceImpl", "getPengineAllLabels old sdk map ", dxy.b().a().toString());
                } catch (SecurityException e) {
                    LogUtil.b("UserLabelServiceImpl", "getPengineAllLabels exception ", ExceptionUtils.d(e));
                }
                dxy.b().e(new BaseResponseCallback<HashMap<String, String>>() { // from class: dxx.5.2
                    @Override // com.huawei.health.userprofilemgr.model.BaseResponseCallback
                    /* renamed from: c, reason: merged with bridge method [inline-methods] */
                    public void onResponse(int i, HashMap<String, String> hashMap) {
                        if (hashMap != null) {
                            LogUtil.a("UserLabelServiceImpl", "errorCode:", Integer.valueOf(i), "new sdk map:", hashMap.toString());
                        }
                    }
                });
            }
        });
    }

    @Override // com.huawei.health.userlabelmgr.api.UserLabelServiceApi
    public void uploadSportEvent() {
        dxy.b().g();
    }

    @Override // com.huawei.health.userlabelmgr.api.UserLabelServiceApi
    public void uploadBindingDevice() {
        dxy.b().c();
    }

    @Override // com.huawei.health.userlabelmgr.api.UserLabelServiceApi
    public void uploadSleepData() {
        dxy.b().e();
    }
}
