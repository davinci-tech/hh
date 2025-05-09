package com.huawei.ui.main.stories.devicecapacity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.huawei.haf.handler.HandlerCenter;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.nfc.PluginPayAdapter;
import com.huawei.openalliance.ad.constant.MapKeyNames;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.devicecapacity.DeviceCapacityThirdPartyListActivity;
import com.huawei.ui.main.stories.devicecapacity.ThirdPartAppAdapter;
import com.huawei.wearengine.auth.AuthInfo;
import com.huawei.wearengine.auth.HiAppInfo;
import com.huawei.wearengine.repository.AppInfoRepositoryImpl;
import com.huawei.wearengine.repository.AuthInfoRepositoryImpl;
import com.huawei.wearengine.repository.api.AppInfoRepository;
import com.huawei.wearengine.repository.api.AuthInfoRepository;
import com.huawei.wearengine.scope.ScopeInfo;
import com.huawei.wearengine.scope.ScopeInfoResponse;
import com.huawei.wearengine.scope.ScopeManager;
import com.huawei.wearengine.scope.ScopeServerRequest;
import com.huawei.wearengine.utills.WearEngineReflectUtil;
import defpackage.nrh;
import defpackage.pfw;
import defpackage.tqy;
import health.compact.a.CommonUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes9.dex */
public class DeviceCapacityThirdPartyListActivity extends BaseActivity {

    /* renamed from: a, reason: collision with root package name */
    private LinearLayout f9699a;
    private AuthInfoRepository b;
    private AppInfoRepository c;
    private List<HiAppInfo> d;
    private Context e;
    private LinearLayout g;
    private HealthRecycleView h;
    private ThirdPartAppAdapter i;
    private volatile boolean f = true;
    private final String[][] j = {new String[]{"com.huawei.hiwear.devicemanager", "device_manager"}, new String[]{"com.huawei.hiwear.notification", "notify"}, new String[]{"com.huawei.hiwear.sensors", "sensor"}, new String[]{"com.huawei.hiwear.motionsensor", "motion_sensor"}, new String[]{"com.huawei.hiwear.wearuserstatus", "wear_user_status"}, new String[]{"com.huawei.hiwear.devicesn", PluginPayAdapter.KEY_DEVICE_INFO_SN}};

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.wear_engine_third_part_auth_app_list_layout);
        b();
        c();
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == 16908332) {
            finish();
        }
        boolean onOptionsItemSelected = super.onOptionsItemSelected(menuItem);
        ViewClickInstrumentation.clickOnMenuItem(this, menuItem);
        return onOptionsItemSelected;
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        LogUtil.c("DeviceCapacityThirdPartyListActivity", "requestCode = " + i);
        if (i != 1001) {
            return;
        }
        this.f = true;
    }

    private void b() {
        this.f9699a = (LinearLayout) findViewById(R.id.wear_engine_third_party_have_app_layout_id);
        HealthRecycleView healthRecycleView = (HealthRecycleView) findViewById(R.id.wear_engine_third_part_auth_app_recycler_view_id);
        this.h = healthRecycleView;
        healthRecycleView.a(false);
        this.h.d(false);
        this.g = (LinearLayout) findViewById(R.id.wear_engine_third_party_not_have_app_layout_id);
    }

    private void c() {
        this.e = this;
        this.d = new ArrayList();
        HandlerCenter.d().d(new Runnable() { // from class: pfx
            @Override // java.lang.Runnable
            public final void run() {
                DeviceCapacityThirdPartyListActivity.this.e();
            }
        }, "WearEngine_DeviceCapacityThirdPartyListActivity");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        this.c = new AppInfoRepositoryImpl(this.e);
        this.b = new AuthInfoRepositoryImpl(this.e);
        try {
            List<HiAppInfo> allHiAppInfo = this.c.getAllHiAppInfo();
            if (tqy.c().isEmpty() && allHiAppInfo != null) {
                LogUtil.h("DeviceCapacityThirdPartyListActivity", "hiWearDevices is null and appInfoList is not null");
                allHiAppInfo.clear();
            }
            a(WearEngineReflectUtil.getUserId(this.e), allHiAppInfo);
            HandlerExecutor.a(new Runnable() { // from class: pfv
                @Override // java.lang.Runnable
                public final void run() {
                    DeviceCapacityThirdPartyListActivity.this.d();
                }
            });
        } catch (Exception e) {
            LogUtil.b("DeviceCapacityThirdPartyListActivity", "handleAppInfo Exception:", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(HiAppInfo hiAppInfo) {
        try {
            if (hiAppInfo == null) {
                LogUtil.h("DeviceCapacityThirdPartyListActivity", "handleAuthInfo appInfo == null");
                this.f = true;
                return;
            }
            Intent intent = new Intent();
            intent.setClassName(this.e.getPackageName(), "com.huawei.ui.main.stories.devicecapacity.AuthActivity");
            intent.setPackage(this.e.getPackageName());
            LogUtil.c("DeviceCapacityThirdPartyListActivity", "handleAuthInfo appInfo is: ", hiAppInfo.toString());
            String[] b = b(hiAppInfo);
            if (b != null && b.length != 0) {
                intent.putExtra("third_party_package_name", hiAppInfo.getPackageName());
                intent.putExtra(MapKeyNames.APP_INFO, hiAppInfo);
                intent.putExtra("permissionTypes", b);
                intent.putExtra("come_from", "health_app");
                startActivityForResult(intent, 1001);
                return;
            }
            LogUtil.h("DeviceCapacityThirdPartyListActivity", "handleAuthInfo permissionTypes is empty");
            this.f = true;
        } catch (Exception e) {
            LogUtil.b("DeviceCapacityThirdPartyListActivity", "handleAuthInfo Exception:", e);
            this.f = true;
        }
    }

    private void a(String str, List<HiAppInfo> list) {
        if (TextUtils.isEmpty(str) || list == null || list.isEmpty()) {
            LogUtil.b("DeviceCapacityThirdPartyListActivity", "handleAppInfoList appinfoList is null");
            return;
        }
        for (HiAppInfo hiAppInfo : list) {
            if (a(hiAppInfo) && b(str, hiAppInfo)) {
                String c = pfw.c(this.e, hiAppInfo.getPackageName());
                if (!TextUtils.isEmpty(c)) {
                    LogUtil.c("DeviceCapacityThirdPartyListActivity", "handleAppInfoList appInfo is :", hiAppInfo.toString());
                    hiAppInfo.setAppName(c);
                    this.d.add(hiAppInfo);
                }
            } else if (!a(hiAppInfo)) {
                this.c.deleteAppInfo(hiAppInfo.getKey());
                this.b.deleteAuth(hiAppInfo.getAppUid(), hiAppInfo.getUserId(), hiAppInfo.getAppId());
            } else {
                LogUtil.c("DeviceCapacityThirdPartyListActivity", "handleAppInfoList appInfo invalid");
            }
        }
    }

    private boolean a(HiAppInfo hiAppInfo) {
        if (hiAppInfo == null) {
            return false;
        }
        String[] packagesForUid = this.e.getPackageManager().getPackagesForUid(hiAppInfo.getAppUid());
        return packagesForUid != null && Arrays.asList(packagesForUid).contains(hiAppInfo.getPackageName()) && pfw.b(this.e, hiAppInfo.getPackageName()) == hiAppInfo.getAppId();
    }

    private boolean b(String str, HiAppInfo hiAppInfo) {
        return (hiAppInfo == null || TextUtils.isEmpty(str) || !str.equals(hiAppInfo.getUserId())) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        List<HiAppInfo> list = this.d;
        if (list == null || list.isEmpty()) {
            LogUtil.h("DeviceCapacityThirdPartyListActivity", "addThirdPartyAppsInView list null");
            this.f9699a.setVisibility(8);
            this.g.setVisibility(0);
            return;
        }
        this.f9699a.setVisibility(0);
        this.g.setVisibility(8);
        this.h.setLayoutManager(new LinearLayoutManager(this));
        this.i = new ThirdPartAppAdapter(this.d, this.e);
        LogUtil.c("DeviceCapacityThirdPartyListActivity", "addThirdPartyAppsInView mAppInfoList size = " + this.d.size());
        this.i.c(new AnonymousClass1());
        this.h.setAdapter(this.i);
    }

    /* renamed from: com.huawei.ui.main.stories.devicecapacity.DeviceCapacityThirdPartyListActivity$1, reason: invalid class name */
    public class AnonymousClass1 implements ThirdPartAppAdapter.ItemClickListener {
        AnonymousClass1() {
        }

        @Override // com.huawei.ui.main.stories.devicecapacity.ThirdPartAppAdapter.ItemClickListener
        public void onItemClick(View view, int i) {
            LogUtil.c("DeviceCapacityThirdPartyListActivity", "appInfo onItemClick position = " + i);
            if (i < 0 || i >= DeviceCapacityThirdPartyListActivity.this.d.size()) {
                return;
            }
            if (DeviceCapacityThirdPartyListActivity.this.f) {
                final HiAppInfo hiAppInfo = (HiAppInfo) DeviceCapacityThirdPartyListActivity.this.d.get(i);
                LogUtil.c("DeviceCapacityThirdPartyListActivity", "ItemClick sendMessage");
                HandlerCenter.d().d(new Runnable() { // from class: pfu
                    @Override // java.lang.Runnable
                    public final void run() {
                        DeviceCapacityThirdPartyListActivity.AnonymousClass1.this.c(hiAppInfo);
                    }
                }, "WearEngine_DeviceCapacityThirdPartyListActivity");
            }
            DeviceCapacityThirdPartyListActivity.this.f = false;
        }

        public /* synthetic */ void c(HiAppInfo hiAppInfo) {
            DeviceCapacityThirdPartyListActivity.this.c(hiAppInfo);
        }
    }

    private String[] b(HiAppInfo hiAppInfo) {
        if (hiAppInfo == null) {
            return new String[0];
        }
        AuthInfoRepositoryImpl authInfoRepositoryImpl = new AuthInfoRepositoryImpl(this.e);
        this.b = authInfoRepositoryImpl;
        List<AuthInfo> auth = authInfoRepositoryImpl.getAuth(hiAppInfo.getAppUid(), hiAppInfo.getUserId(), hiAppInfo.getAppId());
        if (auth == null || auth.isEmpty()) {
            if (!CommonUtil.aa(this.e.getApplicationContext())) {
                LogUtil.a("DeviceCapacityThirdPartyListActivity", "getPermissionTypes no network!");
                nrh.b(this.e.getApplicationContext(), R$string.IDS_connect_network);
                return new String[0];
            }
            LogUtil.c("DeviceCapacityThirdPartyListActivity", "getPermissionTypes authInfoList is null");
            return b(hiAppInfo.getAppId());
        }
        ArrayList arrayList = new ArrayList(auth.size());
        for (AuthInfo authInfo : auth) {
            LogUtil.c("DeviceCapacityThirdPartyListActivity", "getPermissionTypes AuthInfo is :", hiAppInfo.toString());
            arrayList.add(authInfo.getPermission());
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    private String[] b(int i) {
        if (i == 0) {
            return new String[0];
        }
        String valueOf = String.valueOf(i);
        String url = new ScopeServerRequest(valueOf).getUrl(WearEngineReflectUtil.getGrsUrl(this.e));
        ScopeManager scopeManager = new ScopeManager(this.e);
        scopeManager.setScopeServerUrl(valueOf, url);
        ScopeInfoResponse scope = scopeManager.getScope(valueOf, "wearEngine");
        if (scope == null) {
            return new String[0];
        }
        LogUtil.c("DeviceCapacityThirdPartyListActivity", "getPermissionByScope scopeInfoResponse is :", scope.toString());
        return e(scope);
    }

    private String[] e(ScopeInfoResponse scopeInfoResponse) {
        if (scopeInfoResponse == null) {
            return new String[0];
        }
        List<ScopeInfo> scopes = scopeInfoResponse.getScopes();
        if (scopes == null || scopes.isEmpty()) {
            LogUtil.b("DeviceCapacityThirdPartyListActivity", "getPermissionByScopeInfoResponse scopeInfoList is null");
            return new String[0];
        }
        ArrayList arrayList = new ArrayList();
        for (ScopeInfo scopeInfo : scopes) {
            LogUtil.c("DeviceCapacityThirdPartyListActivity", "getPermissionByScopeInfoResponse scopeInfo is :", scopeInfo.toString());
            List<String> permissions = scopeInfo.getPermissions();
            if (permissions != null && !permissions.isEmpty()) {
                d(arrayList, permissions);
            }
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    private void d(List<String> list, List<String> list2) {
        for (String[] strArr : this.j) {
            if (strArr != null && strArr.length >= 2 && list2.contains(strArr[0])) {
                list.add(strArr[1]);
                return;
            }
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
