package defpackage;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import com.huawei.hihealth.HiUserInfo;
import com.huawei.hwadpaterhealthmgr.PluginAchieveAdapterImpl;
import com.huawei.hwadpaterhealthmgr.PluginOperationAdapterImpl;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.adapter.PluginOperationAdapter;
import com.huawei.operation.operation.PluginOperation;
import com.huawei.pluginachievement.manager.model.PersonalData;
import com.huawei.ui.commonui.linechart.utils.ResponseCallback;
import defpackage.nid;
import health.compact.a.AuthorizationUtils;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* loaded from: classes6.dex */
public class nik extends nid {
    private CountDownLatch c;
    private boolean e;

    public nik(lmy lmyVar) {
        super(lmyVar);
        this.e = false;
    }

    @Override // defpackage.nid
    public boolean d(lmy lmyVar) {
        LogUtil.a("LaunchPageTask", "onTaskStart");
        return c(lmyVar);
    }

    private boolean c(lmy lmyVar) {
        this.e = false;
        this.c = new CountDownLatch(1);
        j(lmyVar);
        try {
            this.c.await(30L, TimeUnit.MINUTES);
        } catch (InterruptedException unused) {
            LogUtil.b("LaunchPageTask", "LightMedalListSuccess catch InterruptedException");
        }
        return this.e;
    }

    private void j(lmy lmyVar) {
        if (!(lmyVar instanceof lmv)) {
            this.c.countDown();
            return;
        }
        lmv lmvVar = (lmv) lmyVar;
        if (lmvVar.a() == 2) {
            LogUtil.h("LaunchPageTask", "ReceiveMessage is end");
            this.e = true;
            if (lmvVar.e() != 200) {
                LogUtil.b("LaunchPageTask", "launchPage fail! code ", Integer.valueOf(lmvVar.e()));
                this.e = false;
            }
            this.c.countDown();
            return;
        }
        d();
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: nik.3
            @Override // java.lang.Runnable
            public void run() {
                nik.this.e(BaseApplication.getContext());
            }
        });
    }

    private void d() {
        lmz a2 = a(4);
        a2.d(200);
        iww.b().sendComand(a(a2), new nid.e(new ResponseCallback<Integer>() { // from class: nik.5
            @Override // com.huawei.ui.commonui.linechart.utils.ResponseCallback
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public void onResult(int i, Integer num) {
                if (i == 207) {
                    nik.this.e = true;
                }
                nik.this.c.countDown();
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(Context context) {
        if (d(context)) {
            LogUtil.h("LaunchPageTask", "PluginAchieveAdapter == null || PluginOperationAdapter == null!");
            c(context);
            a(context, "achieveMedal");
            return;
        }
        LoginInit loginInit = LoginInit.getInstance(context);
        LogUtil.a("LaunchPageTask", "LoginInit ", Boolean.valueOf(loginInit.getIsLogined()));
        if (!loginInit.getIsLogined() || !AuthorizationUtils.a(context)) {
            a(context, "achieveMedal");
        } else {
            h(context);
            LogUtil.a("LaunchPageTask", "jump to loading data activity");
        }
    }

    private void h(Context context) {
        if (context == null) {
            return;
        }
        LogUtil.a("LaunchPageTask", "showAchieveMedal()");
        Intent intent = new Intent();
        intent.setClassName(context, PersonalData.CLASS_NAME_PERSONAL_NEW_MEDALS);
        intent.setFlags(HiUserInfo.DATA_CLOUD);
        context.startActivity(intent);
    }

    private boolean d(Context context) {
        return mcv.d(context).getAdapter() == null || !(PluginOperation.getInstance(context).getAdapter() instanceof PluginOperationAdapter);
    }

    private void c(Context context) {
        if (mcv.d(context).getAdapter() == null) {
            LogUtil.h("LaunchPageTask", "PluginAchieveAdapter == null");
            mcv.d(context).setAdapter(new PluginAchieveAdapterImpl());
        }
        a(context);
    }

    private void a(Context context) {
        if (PluginOperation.getInstance(context).getAdapter() instanceof PluginOperationAdapter) {
            return;
        }
        LogUtil.h("LaunchPageTask", "pluginOperationAdapter == null");
        b(context);
    }

    private void b(Context context) {
        PluginOperation pluginOperation = PluginOperation.getInstance(context);
        pluginOperation.setAdapter(PluginOperationAdapterImpl.getInstance(context));
        pluginOperation.init(context);
    }

    private void a(Context context, String str) {
        Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
        if (launchIntentForPackage != null) {
            launchIntentForPackage.addFlags(268468224);
            launchIntentForPackage.putExtra(str, str);
            launchIntentForPackage.setFlags(268435456);
            context.startActivity(launchIntentForPackage);
            LogUtil.a("LaunchPageTask", "jump to main activity");
        }
    }
}
