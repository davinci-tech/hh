package defpackage;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Handler;
import android.os.Message;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.homewear21.home.datasync.DataSyncSwitchContract;
import health.compact.a.CommonUtil;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes6.dex */
public class peh implements DataSyncSwitchContract.Presenter {

    /* renamed from: a, reason: collision with root package name */
    private ThreadPoolManager f16092a;
    private Handler b = new Handler() { // from class: peh.2
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message == null) {
                LogUtil.h("DataSyncSwitchPresenterImpl", "mHandler message is null");
                return;
            }
            super.handleMessage(message);
            if (message.what == 1) {
                peh.this.e = false;
                if (!(message.obj instanceof List)) {
                    LogUtil.h("DataSyncSwitchPresenterImpl", "mHandler message.obj not instanceof List");
                    return;
                }
                List<pef> list = (List) message.obj;
                if (peh.this.d != null) {
                    peh.this.d.refreshList(list);
                    return;
                }
                return;
            }
            LogUtil.h("DataSyncSwitchPresenterImpl", "mHandler message.what is other");
        }
    };
    private DataSyncSwitchContract.View d;
    private boolean e;

    class d implements Runnable {
        private d() {
        }

        @Override // java.lang.Runnable
        public void run() {
            List c = peh.this.c();
            peh.this.d((List<pef>) c);
            peh.this.a((List<pef>) c);
            if (!peg.a() || (CommonUtil.bh() && CommonUtil.ch())) {
                peh.this.c(c);
            } else {
                peh.this.b(c);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(List<pef> list) {
        if (!peg.d()) {
            LogUtil.h("DataSyncSwitchPresenterImpl", "obtainCameraData not support capability");
            return;
        }
        pef pefVar = new pef();
        pefVar.d(false);
        pefVar.c(false);
        pefVar.a("");
        pefVar.b(BaseApplication.getContext().getString(R.string._2130844167_res_0x7f021a07));
        pefVar.dmp_(BaseApplication.getContext().getResources().getDrawable(R.mipmap._2131820766_res_0x7f1100de));
        list.add(pefVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(List<pef> list) {
        boolean z;
        if (!peg.e()) {
            LogUtil.h("DataSyncSwitchPresenterImpl", "otherPhoneCalendarData not support capability");
            return;
        }
        Iterator<pef> it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                z = false;
                break;
            } else if (peg.e(it.next().a())) {
                z = true;
                break;
            }
        }
        LogUtil.a("DataSyncSwitchPresenterImpl", "has calendar item : ", Boolean.valueOf(z));
        if (z) {
            return;
        }
        pef pefVar = new pef();
        pefVar.d(false);
        pefVar.c(false);
        pefVar.a("COM.HUAWEI.HEALTH.CALENDAR");
        pefVar.b(BaseApplication.getContext().getResources().getString(R.string._2130839194_res_0x7f02069a));
        pefVar.dmp_(BaseApplication.getContext().getResources().getDrawable(R.drawable._2131429710_res_0x7f0b094e));
        list.add(pefVar);
    }

    public peh(DataSyncSwitchContract.View view) {
        this.d = view;
        view.setPresenter(this);
        this.f16092a = ThreadPoolManager.a(1, 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<pef> c() {
        ArrayList arrayList = new ArrayList(0);
        Intent intent = new Intent("com.huawei.intent.action.APP_DATA_SYNC_SWITCH");
        intent.addCategory("android.intent.category.DEFAULT");
        PackageManager packageManager = BaseApplication.getContext().getPackageManager();
        List<ResolveInfo> queryIntentActivities = packageManager.queryIntentActivities(intent, 0);
        Collections.sort(queryIntentActivities, new ResolveInfo.DisplayNameComparator(packageManager));
        LogUtil.a("DataSyncSwitchPresenterImpl", "obtainData aps:", Integer.valueOf(queryIntentActivities.size()));
        for (int i = 0; i < queryIntentActivities.size(); i++) {
            ActivityInfo activityInfo = queryIntentActivities.get(i).activityInfo;
            String str = activityInfo.packageName;
            if ((!peg.e(str) || peg.c()) && (!peg.b(str) || peg.h())) {
                if (peg.c(str)) {
                    if (!peg.a()) {
                        LogUtil.h("DataSyncSwitchPresenterImpl", "obtainAllData is not support ContactDataSync");
                    } else if (!CommonUtil.bh() || !CommonUtil.ch()) {
                        LogUtil.h("DataSyncSwitchPresenterImpl", "obtainAllData is not HuaweiSystem or less than Emui1101");
                    }
                }
                pef pefVar = new pef();
                String c = nwy.c(BaseApplication.getContext(), str);
                LogUtil.a("DataSyncSwitchPresenterImpl", "obtainData packageName:", str, " appName:", c);
                pefVar.a(str);
                pefVar.b(c);
                if (activityInfo.applicationInfo != null) {
                    pefVar.dmp_(activityInfo.applicationInfo.loadIcon(packageManager));
                } else {
                    pefVar.dmp_(activityInfo.loadIcon(packageManager));
                }
                arrayList.add(pefVar);
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(final List<pef> list) {
        ReleaseLogUtil.e("R_DataSyncSwitchPresenterImpl", "obtainContactsData getSwitchSetting");
        jqi.a().getSwitchSetting("contacts_data_sync_switch", new IBaseResponseCallback() { // from class: peh.4
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                ReleaseLogUtil.e("R_DataSyncSwitchPresenterImpl", "obtainContactsData getSwitchSetting onResponse errorCode=", Integer.valueOf(i));
                boolean z = !Utils.o();
                if (i == 0 && (obj instanceof String)) {
                    z = !"0".equals(obj);
                    LogUtil.a("DataSyncSwitchPresenterImpl", "obtainContactsData isChecked:", Boolean.valueOf(z));
                }
                if (!kae.c() && z) {
                    LogUtil.a("DataSyncSwitchPresenterImpl", "obtainContactsData READ_CONTACTS permission false and isEnable is true");
                    jqi.a().setSwitchSetting("contacts_data_sync_switch", "0", null);
                    jqi.a().sendSetSwitchSettingCmd(1, "0", null);
                    ReleaseLogUtil.e("R_DataSyncSwitchPresenterImpl", "onResponse setSwitchSetting/sendSetSwitchSettingCmd SWITCH_SETTING_CLOSE");
                    z = false;
                }
                peh.this.c((List<pef>) list, z);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(List<pef> list, boolean z) {
        PackageInfo bJd_ = jrg.bJd_("com.huawei.contacts");
        if (bJd_ == null) {
            bJd_ = jrg.bJd_("com.android.contacts");
        }
        ApplicationInfo applicationInfo = bJd_ != null ? bJd_.applicationInfo : null;
        pef pefVar = new pef();
        pefVar.d(true);
        pefVar.c(z);
        if (applicationInfo == null) {
            LogUtil.a("DataSyncSwitchPresenterImpl", "obtainContactsData default contacts");
            pefVar.a("com.android.contacts");
            pefVar.b(BaseApplication.getContext().getString(R.string._2130842242_res_0x7f021282));
            pefVar.dmp_(BaseApplication.getContext().getResources().getDrawable(R.mipmap._2131820767_res_0x7f1100df));
        } else {
            pefVar.a(bJd_.packageName);
            pefVar.b(applicationInfo.loadLabel(BaseApplication.getContext().getPackageManager()).toString());
            pefVar.dmp_(BaseApplication.getContext().getPackageManager().getApplicationIcon(applicationInfo));
        }
        list.add(pefVar);
        c(list);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(List<pef> list) {
        Message obtain = Message.obtain();
        obtain.what = 1;
        obtain.obj = list;
        this.b.sendMessage(obtain);
    }

    @Override // com.huawei.ui.homewear21.home.datasync.DataSyncSwitchContract.Presenter
    public void startQueryData() {
        ThreadPoolManager threadPoolManager;
        if (this.e || (threadPoolManager = this.f16092a) == null) {
            return;
        }
        this.e = true;
        threadPoolManager.execute(new d());
    }

    @Override // com.huawei.ui.homewear21.home.datasync.DataSyncSwitchContract.Presenter
    public void onDestroy() {
        this.f16092a.shutdown();
        this.d = null;
    }
}
