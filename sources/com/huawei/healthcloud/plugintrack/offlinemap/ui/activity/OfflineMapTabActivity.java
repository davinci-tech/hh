package com.huawei.healthcloud.plugintrack.offlinemap.ui.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.healthcloud.plugintrack.offlinemap.manager.service.OfflineMapService;
import com.huawei.healthcloud.plugintrack.offlinemap.ui.view.OfflineCitiesFragment;
import com.huawei.healthcloud.plugintrack.offlinemap.ui.view.OfflineDownManagerFragment;
import com.huawei.healthcloud.plugintrack.offlinemap.ui.view.OfflineMapCityList;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.subtab.HealthSubTabWidget;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.commonui.utils.CustomPermissionAction;
import com.huawei.ui.commonui.viewpager.HealthViewPager;
import defpackage.gut;
import defpackage.gyf;
import defpackage.jdi;
import defpackage.jeg;
import defpackage.nqx;
import defpackage.nsn;
import defpackage.smy;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class OfflineMapTabActivity extends BaseUiActivity implements ActivityCompat.OnRequestPermissionsResultCallback {

    /* renamed from: a, reason: collision with root package name */
    private Context f3542a;
    private gyf c;
    private List<Fragment> f;
    private CustomTitleBar g;
    private nqx i;
    private e j;
    private HealthViewPager l;
    private HealthSubTabWidget n;
    private BroadcastReceiver d = new b(this);
    private boolean h = false;
    private OfflineCitiesFragment b = null;
    private OfflineDownManagerFragment e = null;

    @Override // com.huawei.healthcloud.plugintrack.offlinemap.ui.activity.BaseUiActivity
    protected int getLayoutId() {
        return R.layout.track_offlinemap_main_tab_layout;
    }

    static class e extends BaseHandler<OfflineMapTabActivity> {
        e(OfflineMapTabActivity offlineMapTabActivity) {
            super(offlineMapTabActivity);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: aWA_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(OfflineMapTabActivity offlineMapTabActivity, Message message) {
            if (offlineMapTabActivity == null || message == null) {
                return;
            }
            int i = message.what;
            if (i != 100) {
                if (i == 201) {
                    offlineMapTabActivity.finish();
                    return;
                } else {
                    if (i != 202) {
                        return;
                    }
                    offlineMapTabActivity.e();
                    return;
                }
            }
            offlineMapTabActivity.b();
            Object obj = message.obj;
            if (obj instanceof Intent) {
                offlineMapTabActivity.b((OfflineMapCityList) ((Intent) obj).getParcelableExtra("TAG_ALL_CITY_LIST_SA"));
            } else {
                LogUtil.b("OfflineMapTabActivity", "DownLoadHandler obj is not intent");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        this.c.b();
    }

    @Override // com.huawei.healthcloud.plugintrack.offlinemap.ui.activity.BaseUiActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        LogUtil.a("OfflineMapTabActivity", "onCreate()");
        this.f3542a = this;
        this.c = gyf.b(this);
        c();
        CustomTitleBar customTitleBar = (CustomTitleBar) findViewById(R.id.offline_titlebar);
        this.g = customTitleBar;
        customTitleBar.setTitleText(this.f3542a.getString(R.string._2130839733_res_0x7f0208b5));
        this.e = new OfflineDownManagerFragment();
        this.b = new OfflineCitiesFragment();
        ArrayList arrayList = new ArrayList(2);
        this.f = arrayList;
        arrayList.add(this.e);
        this.f.add(this.b);
        this.l = (HealthViewPager) findViewById(R.id.viewPager);
        j();
        this.n = (HealthSubTabWidget) findViewById(R.id.offline_subtab);
        this.i = new nqx(this, this.l, this.n);
        smy c = this.n.c(getResources().getString(R.string._2130839732_res_0x7f0208b4));
        smy c2 = this.n.c(getResources().getString(R.string._2130839731_res_0x7f0208b3));
        this.i.c(c, this.e, true);
        this.i.c(c2, this.b, false);
        this.j = new e(this);
        if (!PermissionUtil.c()) {
            a();
        } else {
            this.j.sendEmptyMessage(202);
        }
    }

    private void a() {
        PermissionUtil.b(this.f3542a, PermissionUtil.PermissionType.STORAGE_NETWORK_WIFI, new CustomPermissionAction(this.f3542a) { // from class: com.huawei.healthcloud.plugintrack.offlinemap.ui.activity.OfflineMapTabActivity.5
            @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
            public void onGranted() {
                OfflineMapTabActivity.this.j.sendEmptyMessage(202);
            }

            @Override // com.huawei.ui.commonui.utils.CustomPermissionAction, com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
            public void onForeverDenied(PermissionUtil.PermissionType permissionType) {
                LogUtil.h("OfflineMapTabActivity", "permission forever denied, show the guide window");
                nsn.cLJ_(OfflineMapTabActivity.this.f3542a, permissionType, new View.OnClickListener() { // from class: com.huawei.healthcloud.plugintrack.offlinemap.ui.activity.OfflineMapTabActivity.5.4
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        OfflineMapTabActivity.this.j.sendEmptyMessage(201);
                        ViewClickInstrumentation.clickOnView(view);
                    }
                }, new View.OnClickListener() { // from class: com.huawei.healthcloud.plugintrack.offlinemap.ui.activity.OfflineMapTabActivity.5.2
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        OfflineMapTabActivity.this.j.sendEmptyMessage(201);
                        ViewClickInstrumentation.clickOnView(view);
                    }
                });
            }
        });
    }

    private void j() {
        this.l.addOnPageChangeListener(new HealthViewPager.OnPageChangeListener() { // from class: com.huawei.healthcloud.plugintrack.offlinemap.ui.activity.OfflineMapTabActivity.3
            @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int i) {
            }

            @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
            public void onPageScrolled(int i, float f, int i2) {
            }

            @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
            public void onPageSelected(int i) {
                if (i != 0 || OfflineMapTabActivity.this.b == null) {
                    return;
                }
                LogUtil.a("OfflineMapTabActivity", "onPageChange mCitiesFragment not null ");
                OfflineMapTabActivity.this.b.c();
            }
        });
    }

    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        if (bundle != null) {
            super.onSaveInstanceState(bundle);
            bundle.putParcelable("android:support:fragments", null);
        }
    }

    private void c() {
        this.c.a();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        if (strArr == null) {
            return;
        }
        LogUtil.a("OfflineMapTabActivity", "Activity-onRequestPermissionsResult() PermissionsManager.notifyPermissionsChange()");
        jeg.d().d(strArr, iArr);
        if (jdi.c(this.f3542a, strArr)) {
            LogUtil.a("OfflineMapTabActivity", "onRequestPermissionsResult :enter if");
            this.j.sendEmptyMessage(202);
        } else {
            LogUtil.a("OfflineMapTabActivity", "onRequestPermissionsResult :enter else");
            this.j.sendEmptyMessage(201);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        d();
        f();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        LogUtil.a("OfflineMapTabActivity", "onStart()");
        super.onStart();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        initSystemBar();
    }

    @Override // android.app.Activity
    protected void onRestart() {
        LogUtil.a("OfflineMapTabActivity", "onRestart");
        super.onRestart();
    }

    private void f() {
        LogUtil.a("OfflineMapTabActivity", "startOfflineMapService");
        this.j.postDelayed(new Runnable() { // from class: com.huawei.healthcloud.plugintrack.offlinemap.ui.activity.OfflineMapTabActivity.1
            @Override // java.lang.Runnable
            public void run() {
                LogUtil.a("OfflineMapTabActivity", "startOfflineMapService run");
                Intent intent = new Intent(OfflineMapTabActivity.this.getApplicationContext(), (Class<?>) OfflineMapService.class);
                intent.setAction("ACTION_OFFLINE_MAP_ACTIVTY_START_AS");
                OfflineMapTabActivity.this.startService(intent);
            }
        }, 200L);
    }

    private void d() {
        LogUtil.a("OfflineMapTabActivity", "registerBroadcastReceiver");
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("ACTION_MAP_ONDOWNLOAD_SA");
        intentFilter.addAction("ACTION_REFRESH_ACTIVITY_SA");
        intentFilter.addAction("ACTION_INIT_ACTIVITY_SA");
        intentFilter.addAction("ACTION_MAP_ONREMOVE_SA");
        intentFilter.addAction("ACTION_MAP_LOAD_WAIT_SUCCESS_SA");
        gut.aUm_(this, this.d, intentFilter);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        LogUtil.a("OfflineMapTabActivity", "onDestroy()");
        super.onDestroy();
        h();
        b();
        this.c.d();
        gut.aUp_(this, this.d);
    }

    private void h() {
        LogUtil.a("OfflineMapTabActivity", "stopService()");
        gut.aUn_(getApplicationContext(), new Intent("ACTION_ACITITY_DESTROY_AS"));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(OfflineMapCityList offlineMapCityList) {
        if (offlineMapCityList == null) {
            LogUtil.b("OfflineMapTabActivity", "updateFragment() mList null");
            return;
        }
        LogUtil.a("OfflineMapTabActivity", "updateFragment()");
        OfflineDownManagerFragment offlineDownManagerFragment = this.e;
        if (offlineDownManagerFragment != null && this.b != null) {
            offlineDownManagerFragment.b(offlineMapCityList.getLoadingCityList(), offlineMapCityList.getDownCityList());
            this.b.d(offlineMapCityList.getProvinceList(), offlineMapCityList.getCityMap());
            LogUtil.a("OfflineMapTabActivity", "updateFragment() fragment not null finish");
            return;
        }
        if (this.f.size() == 0) {
            LogUtil.a("OfflineMapTabActivity", "updateFragment fragmentList.size() == 0");
            return;
        }
        for (Fragment fragment : this.f) {
            if (fragment instanceof OfflineCitiesFragment) {
                OfflineCitiesFragment offlineCitiesFragment = (OfflineCitiesFragment) fragment;
                this.b = offlineCitiesFragment;
                offlineCitiesFragment.d(offlineMapCityList.getProvinceList(), offlineMapCityList.getCityMap());
            } else if (fragment instanceof OfflineDownManagerFragment) {
                OfflineDownManagerFragment offlineDownManagerFragment2 = (OfflineDownManagerFragment) fragment;
                this.e = offlineDownManagerFragment2;
                offlineDownManagerFragment2.b(offlineMapCityList.getLoadingCityList(), offlineMapCityList.getDownCityList());
            } else {
                LogUtil.b("OfflineMapTabActivity", "fragment is not normal fragment");
            }
        }
    }

    static class b extends BroadcastReceiver {

        /* renamed from: a, reason: collision with root package name */
        private final WeakReference<OfflineMapTabActivity> f3543a;

        b(OfflineMapTabActivity offlineMapTabActivity) {
            this.f3543a = new WeakReference<>(offlineMapTabActivity);
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                LogUtil.b("OfflineMapTabActivity", "MyBroadcastReceiver onReceive() intent null");
                return;
            }
            OfflineMapTabActivity offlineMapTabActivity = this.f3543a.get();
            if (offlineMapTabActivity == null) {
                LogUtil.b("OfflineMapTabActivity", "MyBroadcastReceiver onReceive() activity null");
                return;
            }
            String action = intent.getAction();
            LogUtil.a("OfflineMapTabActivity", "MyBroadcastReceiver onReceive() action :", action);
            if ("ACTION_MAP_ONDOWNLOAD_SA".equals(action) && offlineMapTabActivity.h) {
                offlineMapTabActivity.j.sendMessage(offlineMapTabActivity.j.obtainMessage(100, 0, 0, intent));
                return;
            }
            if ("ACTION_REFRESH_ACTIVITY_SA".equals(action)) {
                offlineMapTabActivity.j.sendMessage(offlineMapTabActivity.j.obtainMessage(100, 0, 0, intent));
                return;
            }
            if ("ACTION_INIT_ACTIVITY_SA".equals(action)) {
                offlineMapTabActivity.h = true;
                offlineMapTabActivity.j.sendMessage(offlineMapTabActivity.j.obtainMessage(100, 0, 0, intent));
            } else if ("ACTION_MAP_ONREMOVE_SA".equals(action)) {
                offlineMapTabActivity.j.sendMessage(offlineMapTabActivity.j.obtainMessage(100, 0, 0, intent));
            } else if ("ACTION_MAP_LOAD_WAIT_SUCCESS_SA".equals(action)) {
                offlineMapTabActivity.j.sendMessage(offlineMapTabActivity.j.obtainMessage(100, 0, 0, intent));
            } else {
                LogUtil.b("OfflineMapTabActivity", "OfflineMapService action is", action);
            }
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
