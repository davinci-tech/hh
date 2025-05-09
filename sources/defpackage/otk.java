package defpackage;

import android.content.Context;
import android.content.SharedPreferences;
import com.huawei.haf.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.ui.openservice.db.control.OpenServiceControl;
import com.huawei.ui.openservice.db.model.OpenService;
import health.compact.a.CommonUtil;

/* loaded from: classes6.dex */
public class otk {

    /* renamed from: a, reason: collision with root package name */
    private String f15946a;
    private Context b;
    private OpenServiceControl c;
    private SharedPreferences e;

    public otk() {
        Context e = BaseApplication.e();
        this.b = e;
        this.e = e.getSharedPreferences("Service_Card", 0);
        this.f15946a = LoginInit.getInstance(this.b).getAccountInfo(1011);
        this.c = OpenServiceControl.getInstance(this.b);
    }

    public void c() {
        if (CommonUtil.as()) {
            int d = CommonUtil.d(this.b);
            if (a(d)) {
                LogUtil.a("UIHLH_ServiceInteractor", "initService, new Beta Version! refresh DB, version is ", Integer.valueOf(d));
                this.c.initOpenServiceDB(this.f15946a);
                d(d);
            }
        } else {
            this.c.initHomePageService(this.f15946a);
        }
        if (OpenService.isEmpty(this.c.queryAllService())) {
            LogUtil.a("UIHLH_ServiceInteractor", "initService, if no service exist refresh service");
            this.c.initOpenServiceDB(this.f15946a);
        }
    }

    public void a() {
        LogUtil.a("UIHLH_ServiceInteractor", "refreshServiceFW start");
        this.c.initOpenServiceDB(this.f15946a);
    }

    private boolean a(int i) {
        return this.e.getBoolean("Beta_Upgrade_" + i, true);
    }

    private void d(int i) {
        this.e.edit().putBoolean("Beta_Upgrade_" + i, false).apply();
    }
}
