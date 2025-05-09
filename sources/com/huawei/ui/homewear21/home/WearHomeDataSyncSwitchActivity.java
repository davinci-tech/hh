package com.huawei.ui.homewear21.home;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.LinearLayout;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.homewear21.home.WearHomeDataSyncSwitchActivity;
import com.huawei.ui.homewear21.home.adapter.WearHomeDataSyncAdapter;
import com.huawei.ui.homewear21.home.datasync.DataSyncSwitchContract;
import com.huawei.ui.homewear21.home.listener.WearHomeListener;
import defpackage.gnm;
import defpackage.jdi;
import defpackage.jeg;
import defpackage.jez;
import defpackage.jpt;
import defpackage.jqi;
import defpackage.nsn;
import defpackage.nsy;
import defpackage.obb;
import defpackage.oyc;
import defpackage.pef;
import defpackage.peg;
import defpackage.peh;
import defpackage.pep;
import health.compact.a.CommonUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class WearHomeDataSyncSwitchActivity extends BaseActivity implements WearHomeListener, DataSyncSwitchContract.View {
    private Context c;
    private int d;
    private WearHomeDataSyncAdapter e;
    private String f;
    private View g;
    private LinearLayout h;
    private DataSyncSwitchContract.Presenter j;
    private HealthRecycleView k;
    private List<pef> b = new ArrayList(0);
    private boolean i = false;

    /* renamed from: a, reason: collision with root package name */
    private final BroadcastReceiver f9647a = new BroadcastReceiver() { // from class: com.huawei.ui.homewear21.home.WearHomeDataSyncSwitchActivity.5
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                LogUtil.h("WearHomeDataSyncSwitchActivity", "mBroadcastReceiver intent is null");
                return;
            }
            try {
                Parcelable parcelableExtra = intent.getParcelableExtra("deviceinfo");
                if (parcelableExtra instanceof DeviceInfo) {
                    WearHomeDataSyncSwitchActivity.this.a((DeviceInfo) parcelableExtra);
                } else {
                    LogUtil.h("WearHomeDataSyncSwitchActivity", "mBroadcastReceiver parcelable not instanceof DeviceInfo");
                }
            } catch (ClassCastException unused) {
                LogUtil.b("WearHomeDataSyncSwitchActivity", "mBroadcastReceiver ClassCastException");
            }
        }
    };

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.wear_home_other_sitting_layout);
        this.c = this;
        b();
        d();
        new peh(this);
        if (jpt.d("WearHomeDataSyncSwitchActivity") == null) {
            LogUtil.h("WearHomeDataSyncSwitchActivity", "init phoneService and get connect main device");
            this.i = true;
            this.d = 0;
            HandlerExecutor.d(new oyc(this), 1000L);
        } else {
            this.j.startQueryData();
        }
        Intent intent = getIntent();
        if (intent != null) {
            this.f = intent.getStringExtra("device_id");
        }
        pep.dmT_(this.c, this.f9647a, "com.huawei.bone.action.CONNECTION_STATE_CHANGED");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        int i;
        this.d++;
        if (isFinishing() || isDestroyed()) {
            LogUtil.a("WearHomeDataSyncSwitchActivity", "checkPhoneServiceBinder end, activity is finished");
            return;
        }
        if ((jez.e() == null || jpt.d("WearHomeDataSyncSwitchActivity") == null) && (i = this.d) < 5) {
            LogUtil.h("WearHomeDataSyncSwitchActivity", "getting main device, check times: ", Integer.valueOf(i));
            HandlerExecutor.d(new oyc(this), 1000L);
            return;
        }
        this.i = false;
        if (this.b.isEmpty()) {
            LogUtil.a("WearHomeDataSyncSwitchActivity", "phone service binds success, startQueryData");
            this.j.startQueryData();
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        pep.dmY_(this.c, this.f9647a);
        this.j.onDestroy();
    }

    @Override // com.huawei.ui.homewear21.home.datasync.BaseView
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public void setPresenter(DataSyncSwitchContract.Presenter presenter) {
        this.j = presenter;
    }

    @Override // com.huawei.ui.homewear21.home.datasync.DataSyncSwitchContract.View
    public void refreshList(List<pef> list) {
        this.b.clear();
        this.b.addAll(list);
        if (this.b.isEmpty()) {
            c();
        } else {
            this.e.notifyDataSetChanged();
            e();
        }
    }

    private void b() {
        this.k = (HealthRecycleView) nsy.cMc_(this, R.id.recyclerView);
        this.g = nsy.cMc_(this, R.id.wear_home_loading);
        this.h = (LinearLayout) nsy.cMc_(this, R.id.wear_home_empty_layout);
        ((CustomTitleBar) nsy.cMc_(this, R.id.wear_home_sitting_bar)).setTitleText(getString(R.string._2130844150_res_0x7f0219f6));
        this.e = new WearHomeDataSyncAdapter(this, this.b);
        this.k.setLayoutManager(new LinearLayoutManager(this.c));
        this.k.setAdapter(this.e);
        this.k.setLayerType(2, null);
        this.k.setItemAnimator(new DefaultItemAnimator());
        this.k.a(false);
        this.k.d(false);
        this.e.a(this);
    }

    @Override // com.huawei.ui.homewear21.home.listener.WearHomeListener
    public void onItemClick(int i) {
        pef pefVar = this.b.get(i);
        LogUtil.a("WearHomeDataSyncSwitchActivity", "onItemClick appName:", pefVar.c(), " PkgName:", pefVar.a(), " isChecked:", Boolean.valueOf(pefVar.b()), " isShowSwitch:", Boolean.valueOf(pefVar.e()));
        if (pefVar.c().equals(getString(R.string._2130844167_res_0x7f021a07))) {
            gnm.aPB_(this, new Intent(this, (Class<?>) WearHomeCameraSettingActivity.class));
            return;
        }
        if ("COM.HUAWEI.HEALTH.CALENDAR".equals(pefVar.a())) {
            Intent intent = new Intent(this, (Class<?>) WearHomeCalendarSettingActivity.class);
            intent.putExtra("device_id", this.f);
            gnm.aPB_(this, intent);
        } else if (pefVar.e()) {
            if (peg.c(pefVar.a())) {
                c(pefVar);
            }
        } else {
            try {
                Intent intent2 = new Intent("com.huawei.intent.action.APP_DATA_SYNC_SWITCH");
                intent2.addCategory("android.intent.category.DEFAULT");
                intent2.setPackage(pefVar.a());
                nsn.cLM_(intent2, pefVar.a(), this, pefVar.c());
            } catch (SecurityException unused) {
                LogUtil.b("WearHomeDataSyncSwitchActivity", "onItemClick SecurityException");
            }
        }
    }

    private void c(final pef pefVar) {
        if (!pefVar.b()) {
            LogUtil.a("WearHomeDataSyncSwitchActivity", "clickContactsSwitch switch close");
            b(false);
        } else {
            CustomTextAlertDialog a2 = new CustomTextAlertDialog.Builder(this).b(R.string.IDS_service_area_notice_title).d(R.string._2130844763_res_0x7f021c5b).cyU_(R.string._2130841379_res_0x7f020f23, new View.OnClickListener() { // from class: oyb
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    WearHomeDataSyncSwitchActivity.this.djL_(pefVar, view);
                }
            }).cyR_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: oye
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    WearHomeDataSyncSwitchActivity.this.djM_(pefVar, view);
                }
            }).a();
            a2.setCanceledOnTouchOutside(false);
            a2.setCancelable(false);
            a2.show();
        }
    }

    public /* synthetic */ void djL_(pef pefVar, View view) {
        LogUtil.a("WearHomeDataSyncSwitchActivity", "showContactDialog ok click");
        a(pefVar);
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void djM_(pef pefVar, View view) {
        LogUtil.a("WearHomeDataSyncSwitchActivity", "showContactDialog cancel click");
        pefVar.c(false);
        this.e.notifyDataSetChanged();
        ViewClickInstrumentation.clickOnView(view);
    }

    private void a(final pef pefVar) {
        if (jeg.d().c(this, "android.permission.READ_CONTACTS")) {
            b(true);
            return;
        }
        LogUtil.a("WearHomeDataSyncSwitchActivity", "clickContactsSwitch READ_CONTACTS permission false");
        if (!ActivityCompat.shouldShowRequestPermissionRationale(this, "android.permission.READ_CONTACTS")) {
            LogUtil.a("WearHomeDataSyncSwitchActivity", "clickContactsSwitch READ_CONTACTS permission false and rationale");
            b(this.c, pefVar);
        } else {
            jdi.bFL_(this, new String[]{"android.permission.READ_CONTACTS"}, new PermissionsResultAction() { // from class: com.huawei.ui.homewear21.home.WearHomeDataSyncSwitchActivity.1
                @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
                public void onGranted() {
                    LogUtil.a("WearHomeDataSyncSwitchActivity", "clickContactsSwitch permission onGranted");
                    WearHomeDataSyncSwitchActivity.this.b(true);
                }

                @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
                public void onDenied(String str) {
                    LogUtil.a("WearHomeDataSyncSwitchActivity", "clickContactsSwitch permission onDenied");
                    pefVar.c(false);
                    WearHomeDataSyncSwitchActivity.this.e.notifyDataSetChanged();
                }
            });
        }
    }

    private void b(Context context, final pef pefVar) {
        if (!(context instanceof Activity)) {
            LogUtil.a("WearHomeDataSyncSwitchActivity", "showPermissionDialogNoPrompt context not instanceof Activity");
            return;
        }
        final Activity activity = (Activity) context;
        if (activity.isFinishing()) {
            LogUtil.h("WearHomeDataSyncSwitchActivity", "showPermissionDialogNoPrompt activity is finishing");
        } else {
            new CustomTextAlertDialog.Builder(context).b(R.string._2130842089_res_0x7f0211e9).e(getResources().getString(R.string._2130845002_res_0x7f021d4a)).cyR_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: oyj
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    WearHomeDataSyncSwitchActivity.this.djN_(pefVar, view);
                }
            }).cyU_(R.string._2130842041_res_0x7f0211b9, new View.OnClickListener() { // from class: oyg
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    WearHomeDataSyncSwitchActivity.djK_(activity, view);
                }
            }).a().show();
        }
    }

    public /* synthetic */ void djN_(pef pefVar, View view) {
        LogUtil.h("WearHomeDataSyncSwitchActivity", "showPermissionDialogNoPrompt onClick cancal");
        pefVar.c(false);
        this.e.notifyDataSetChanged();
        ViewClickInstrumentation.clickOnView(view);
    }

    public static /* synthetic */ void djK_(Activity activity, View view) {
        LogUtil.h("WearHomeDataSyncSwitchActivity", "showPermissionDialogNoPrompt onClick go setting");
        obb.cTW_(activity, 10001);
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i != 10001) {
            return;
        }
        LogUtil.h("WearHomeDataSyncSwitchActivity", "onActivityResult");
        for (pef pefVar : this.b) {
            if (peg.c(pefVar.a())) {
                boolean c = jeg.d().c(this, "android.permission.READ_CONTACTS");
                if (c) {
                    b(true);
                }
                pefVar.c(c);
                this.e.notifyDataSetChanged();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(boolean z) {
        LogUtil.a("WearHomeDataSyncSwitchActivity", "setAndSendSwitchStatus isChecked: ", Boolean.valueOf(z));
        String str = z ? "1" : "0";
        jqi.a().setSwitchSetting("contacts_data_sync_switch", str, null);
        jqi.a().sendSetSwitchSettingCmd(1, str, null);
        ReleaseLogUtil.e("R_WearHomeDataSyncSwitchActivity", "setAndSendSwitchStatus setSwitchSetting/sendSetSwitchSettingCmd isChecked: ", Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(DeviceInfo deviceInfo) {
        String str;
        if (deviceInfo == null || (str = this.f) == null) {
            LogUtil.h("WearHomeDataSyncSwitchActivity", "connectedChanged deviceInfo or mDeviceMac is null");
            return;
        }
        LogUtil.a("WearHomeDataSyncSwitchActivity", "connectedChanged, mDeviceMac: ", CommonUtil.l(str), ", deviceInfo.getDeviceIdentify(): ", CommonUtil.l(deviceInfo.getDeviceIdentify()), ", mIsGettingMainDevice: ", Boolean.valueOf(this.i));
        if (!this.f.equalsIgnoreCase(deviceInfo.getDeviceIdentify())) {
            LogUtil.h("WearHomeDataSyncSwitchActivity", "connectedChanged deviceConnectionChange return");
        } else {
            if (this.i || deviceInfo.getDeviceConnectState() == 2) {
                return;
            }
            finish();
        }
    }

    private void d() {
        this.g.setVisibility(0);
        this.k.setVisibility(8);
        this.h.setVisibility(8);
    }

    private void e() {
        this.g.setVisibility(8);
        this.k.setVisibility(0);
        this.h.setVisibility(8);
    }

    private void c() {
        this.g.setVisibility(8);
        this.k.setVisibility(8);
        this.h.setVisibility(0);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
