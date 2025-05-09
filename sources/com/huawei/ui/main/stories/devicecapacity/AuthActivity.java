package com.huawei.ui.main.stories.devicecapacity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.BadParcelableException;
import android.os.Binder;
import android.os.Bundle;
import android.view.View;
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
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.devicecapacity.AuthActivity;
import com.huawei.wearengine.auth.AuthInfo;
import com.huawei.wearengine.auth.AuthListenerManagerProxy;
import com.huawei.wearengine.auth.HiAppInfo;
import com.huawei.wearengine.auth.Permission;
import com.huawei.wearengine.repository.AuthInfoRepositoryImpl;
import com.huawei.wearengine.repository.api.AuthInfoRepository;
import com.huawei.wearengine.service.WearEngineExtendService;
import defpackage.pfo;
import defpackage.pfq;
import defpackage.pfr;
import defpackage.pfs;
import defpackage.pfw;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes9.dex */
public class AuthActivity extends BaseActivity {

    /* renamed from: a, reason: collision with root package name */
    private int f9693a;
    private HiAppInfo b;
    private AuthAdapter c;
    private int d;
    private AuthInfoRepository f;
    private AuthListenerManagerProxy h;
    private pfs i;
    private Intent k;
    private Context l;
    private CustomTitleBar m;
    private String n;
    private HealthButton o;
    private String p;
    private HealthRecycleView q;
    private HealthTextView r;
    private boolean s;
    private List<AuthInfo> u;
    private String x;
    private List<pfo> j = new ArrayList();
    private List<pfq> e = new ArrayList();
    private List<Boolean> g = new ArrayList(6);
    private final Map<String, Integer> t = new HashMap(6);

    public interface AuthChangeListener {
        void updateAuth(boolean z);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        LogUtil.a("AuthActivity", "onCreate");
        setContentView(R.layout.wear_engine_third_part_auth_main_layout);
        this.l = this;
        if (getIntent() == null) {
            LogUtil.h("AuthActivity", "Intent == null!");
            finish();
            return;
        }
        LogUtil.c("AuthActivity", "Intent != null!");
        h();
        g();
        try {
            i();
        } catch (Exception unused) {
            LogUtil.b("AuthActivity", "initData Exception");
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        LogUtil.a("AuthActivity", "onResume");
        HiAppInfo hiAppInfo = this.b;
        if (hiAppInfo != null) {
            d(hiAppInfo.getPackageName());
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
        LogUtil.c("AuthActivity", "onBackPressed authListenerOnCancel come from:" + this.n);
        c();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        if ("third_party_app".equals(this.n)) {
            LogUtil.c("AuthActivity", "authListenerOnCancel come from:" + this.n);
            try {
                AuthListenerManagerProxy authListenerManagerProxy = this.h;
                if (authListenerManagerProxy != null) {
                    authListenerManagerProxy.authListenerOnCancel(this.b.getPackageName());
                }
            } catch (Exception unused) {
                LogUtil.b("AuthActivity", "authListenerOnCancel Exception");
            }
        }
        setResult(-1, new Intent());
        finish();
    }

    private void h() {
        this.h = new AuthListenerManagerProxy(this.l.getApplicationContext());
        this.i = new pfs();
        this.f = new AuthInfoRepositoryImpl(this.l);
        this.k = getIntent();
    }

    private void g() {
        HealthRecycleView healthRecycleView = (HealthRecycleView) findViewById(R.id.wear_engine_third_part_auth_main_recycler_view_id);
        this.q = healthRecycleView;
        if (healthRecycleView != null) {
            healthRecycleView.setLayoutManager(new LinearLayoutManager(this.l));
        }
        this.m = (CustomTitleBar) findViewById(R.id.wear_engine_third_part_auth_title_layout);
        this.r = (HealthTextView) findViewById(R.id.wear_engine_third_part_auth_setting_describe_text_view_id);
        CustomTitleBar customTitleBar = this.m;
        if (customTitleBar != null) {
            customTitleBar.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.devicecapacity.AuthActivity.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    AuthActivity.this.c();
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
        }
        HealthButton healthButton = (HealthButton) findViewById(R.id.wear_engine_auth_confirm_id);
        this.o = healthButton;
        if (healthButton != null) {
            healthButton.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.devicecapacity.AuthActivity.5
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    AuthActivity.this.a();
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        Permission[] c = c(this.i.a());
        if ("third_party_app".equals(this.n)) {
            e(c);
        } else {
            finish();
        }
    }

    private void e(final Permission[] permissionArr) {
        LogUtil.a("AuthActivity", "execute callAuthListenerOnOk");
        HandlerCenter.d().d(new Runnable() { // from class: pfj
            @Override // java.lang.Runnable
            public final void run() {
                AuthActivity.this.c(permissionArr);
            }
        }, "WearEngine_AuthActivity");
    }

    public /* synthetic */ void c(Permission[] permissionArr) {
        a(a(permissionArr));
    }

    private ArrayList<String> a(Permission[] permissionArr) {
        ArrayList<String> arrayList = new ArrayList<>();
        for (Permission permission : permissionArr) {
            if (permission != null) {
                arrayList.add(permission.getName());
            }
        }
        return arrayList;
    }

    private Permission[] c(Map<String, AuthInfo> map) {
        if (map == null) {
            return new Permission[0];
        }
        ArrayList arrayList = new ArrayList();
        for (AuthInfo authInfo : map.values()) {
            LogUtil.c("AuthActivity", "storyAuthInfo save authInfo:" + authInfo.toString());
            e(authInfo);
            d(arrayList, authInfo);
        }
        List<AuthInfo> list = this.u;
        if (list == null || list.isEmpty()) {
            LogUtil.a("AuthActivity", "storyAuthInfo storedAuthList is null");
            return (Permission[]) arrayList.toArray(new Permission[arrayList.size()]);
        }
        for (AuthInfo authInfo2 : this.u) {
            LogUtil.c("AuthActivity", "storyAuthInfo authInfo:" + authInfo2.toString());
            d(arrayList, authInfo2);
        }
        LogUtil.a("AuthActivity", "storyAuthInfo permissions size:" + arrayList.size());
        return (Permission[]) arrayList.toArray(new Permission[arrayList.size()]);
    }

    private void d(List<Permission> list, AuthInfo authInfo) {
        if (list == null || authInfo == null || authInfo.getOpenStatus() == 0) {
            return;
        }
        list.add(pfr.b(authInfo.getPermission()));
    }

    private void e(final AuthInfo authInfo) {
        if (authInfo == null) {
            return;
        }
        LogUtil.c("AuthActivity", "buildUpdateAuthMessage sendMessage");
        HandlerCenter.d().d(new Runnable() { // from class: pfm
            @Override // java.lang.Runnable
            public final void run() {
                AuthActivity.this.a(authInfo);
            }
        }, "WearEngine_AuthActivity");
    }

    private void i() {
        LogUtil.c("AuthActivity", "initData entry");
        f();
        if (this.k.hasExtra("permissionTypes")) {
            LogUtil.c("AuthActivity", "initData hasExtra permissionTypes");
            try {
                String[] stringArrayExtra = this.k.getStringArrayExtra("permissionTypes");
                if (stringArrayExtra == null) {
                    LogUtil.b("AuthActivity", "getStringArrayExtra permissionTypes == null");
                    return;
                }
                HiAppInfo hiAppInfo = (HiAppInfo) this.k.getParcelableExtra(MapKeyNames.APP_INFO);
                if (hiAppInfo == null) {
                    LogUtil.b("AuthActivity", "getParcelableExtra hiAppInfo == null");
                    return;
                }
                LogUtil.c("AuthActivity", "hiAppInfo = " + hiAppInfo.toString());
                String stringExtra = this.k.getStringExtra("come_from");
                this.n = stringExtra;
                if (stringExtra == null) {
                    LogUtil.b("AuthActivity", "can not get come from!");
                    return;
                }
                if ("health_app".equals(stringExtra)) {
                    this.r.setVisibility(8);
                    this.m.setTitleText(this.l.getString(R$string.IDS_wear_engine_third_part_auth_title));
                } else {
                    this.m.setTitleText(this.l.getString(R$string.IDS_wear_engine_auth_title));
                }
                LogUtil.c("AuthActivity", "come from:" + this.n);
                this.b = hiAppInfo;
                this.f9693a = hiAppInfo.getAppId();
                this.x = hiAppInfo.getUserId();
                this.d = hiAppInfo.getAppUid();
                String packageName = hiAppInfo.getPackageName();
                this.p = packageName;
                int e = pfw.e(this.l, packageName);
                this.s = e <= 5;
                LogUtil.a("AuthActivity", "mIsOldClientLevel:" + this.s + ",clientApiLevel:" + e);
                d(hiAppInfo);
                e(stringArrayExtra);
            } catch (BadParcelableException unused) {
                LogUtil.b("AuthActivity", " mIntent getStringArray error");
            }
        }
    }

    private void f() {
        this.t.put("device_manager", 1);
        this.t.put("notify", 2);
        this.t.put("wear_user_status", 3);
        this.t.put("sensor", 4);
        this.t.put("motion_sensor", 5);
        this.t.put(PluginPayAdapter.KEY_DEVICE_INFO_SN, 6);
    }

    private void d(HiAppInfo hiAppInfo) {
        byte[] byteDraw = hiAppInfo.getByteDraw();
        if (byteDraw != null) {
            pfq pfqVar = new pfq();
            pfqVar.b(0);
            pfqVar.dox_(pfw.doA_(byteDraw));
            this.e.add(pfqVar);
        }
    }

    private List<String> d(List<AuthInfo> list) {
        ArrayList arrayList = new ArrayList();
        if (list == null || list.size() == 0) {
            LogUtil.h("AuthActivity", "getStrListFromAuthList authInfoList is invalid");
            return arrayList;
        }
        for (AuthInfo authInfo : list) {
            if (authInfo.getOpenStatus() != 0) {
                arrayList.add(authInfo.getPermission());
            }
        }
        return arrayList;
    }

    private void d(String str) {
        LogUtil.a("AuthActivity", "startWearEngineService");
        Intent intent = new Intent(getBaseContext(), (Class<?>) WearEngineExtendService.class);
        intent.setAction("com.huawei.bone.action.DELETE_AUTH_CACHE");
        intent.putExtra("third_party_package_name", str);
        startService(intent);
    }

    private void e(final String[] strArr) {
        if (strArr == null) {
            return;
        }
        LogUtil.c("AuthActivity", "initData buildPermissionData sendMessage");
        HandlerCenter.d().d(new Runnable() { // from class: pfp
            @Override // java.lang.Runnable
            public final void run() {
                AuthActivity.this.c(strArr);
            }
        }, "WearEngine_AuthActivity");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public void a(AuthInfo authInfo) {
        if (authInfo == null) {
            LogUtil.h("AuthActivity", "handleUpdateAuth authInfo == null!");
        } else {
            this.f.updateAuth(authInfo);
        }
    }

    private void a(ArrayList<String> arrayList) {
        LogUtil.c("AuthActivity", "callAuthListenerOnOk handleMessage CALL_ON_OK");
        Permission[] c = arrayList == null ? new Permission[0] : c(arrayList);
        try {
            LogUtil.c("AuthActivity", "authListenerOnOk PackageName:" + this.b.getPackageName());
            LogUtil.c("AuthActivity", "procName:" + this.l.getPackageManager().getNameForUid(Binder.getCallingUid()));
            AuthListenerManagerProxy authListenerManagerProxy = this.h;
            if (authListenerManagerProxy != null) {
                authListenerManagerProxy.authListenerOnOk(this.b.getPackageName(), c);
            }
        } catch (Exception unused) {
            LogUtil.b("AuthActivity", "onOk RemoteException");
        }
        finish();
    }

    private Permission[] c(ArrayList<String> arrayList) {
        if (arrayList.size() <= 0) {
            return new Permission[0];
        }
        ArrayList arrayList2 = new ArrayList();
        for (int i = 0; i < arrayList.size(); i++) {
            arrayList2.add(pfr.b(arrayList.get(i)));
        }
        return (Permission[]) arrayList2.toArray(new Permission[arrayList2.size()]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void c(String[] strArr) {
        try {
            if (strArr == null) {
                LogUtil.h("AuthActivity", "buildPermissionData permissionTypes == null");
                return;
            }
            LogUtil.c("AuthActivity", "buildPermissionData permissionTypes");
            this.u = this.f.getAuth(this.d, this.x, this.f9693a);
            d(strArr);
            d();
            HandlerExecutor.a(new Runnable() { // from class: pfn
                @Override // java.lang.Runnable
                public final void run() {
                    AuthActivity.this.e();
                }
            });
        } catch (Exception unused) {
            LogUtil.b("AuthActivity", "buildPermissionData Exception:");
        }
    }

    public /* synthetic */ void e() {
        LogUtil.a("AuthActivity", "handleMessage UPDATE_UI");
        b();
    }

    private void d(String[] strArr) {
        this.j.clear();
        int i = 0;
        if ("third_party_app".equals(this.n)) {
            List<AuthInfo> list = this.u;
            if (list == null || list.isEmpty()) {
                LogUtil.c("AuthActivity", "mergePermission storedAuthList isEmpty, permissionType");
                int length = strArr.length;
                while (i < length) {
                    a(strArr[i]);
                    i++;
                }
                return;
            }
            List<String> d = d(this.u);
            int length2 = strArr.length;
            while (i < length2) {
                String str = strArr[i];
                if (!d.contains(str)) {
                    LogUtil.c("AuthActivity", "mergePermission storedAuthList not containsKey permissionType = " + str);
                    a(str);
                }
                i++;
            }
        } else {
            List<AuthInfo> list2 = this.u;
            if (list2 == null || list2.isEmpty()) {
                LogUtil.b("AuthActivity", "mergePermission storedAuthList is null");
                return;
            }
            for (AuthInfo authInfo : this.u) {
                this.j.add(new pfo(this.l, authInfo.getPermission(), authInfo.getOpenStatus() != 0));
            }
        }
        Collections.sort(this.j, new Comparator() { // from class: pfk
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return AuthActivity.this.d((pfo) obj, (pfo) obj2);
            }
        });
    }

    public /* synthetic */ int d(pfo pfoVar, pfo pfoVar2) {
        if (this.t.containsKey(pfoVar.a()) && this.t.containsKey(pfoVar2.a())) {
            return this.t.get(pfoVar.a()).compareTo(this.t.get(pfoVar2.a()));
        }
        return 0;
    }

    private void a(String str) {
        this.j.add(new pfo(this.l, str, false));
    }

    private void d() {
        if (this.j.isEmpty()) {
            return;
        }
        pfq pfqVar = new pfq();
        pfqVar.b(1);
        pfqVar.b(e(this.j));
        this.e.add(pfqVar);
        pfq pfqVar2 = new pfq();
        pfqVar2.b(2);
        pfqVar2.d(this.j);
        this.e.add(pfqVar2);
    }

    private boolean e(List<pfo> list) {
        if (list == null || list.isEmpty()) {
            return false;
        }
        Iterator<pfo> it = list.iterator();
        while (it.hasNext()) {
            if (!it.next().e()) {
                return false;
            }
        }
        return true;
    }

    private void b() {
        LogUtil.c("AuthActivity", "buildListView");
        try {
            j();
            AuthAdapter authAdapter = new AuthAdapter(this.l);
            this.c = authAdapter;
            authAdapter.a(this.i);
            this.c.a(this.n);
            this.c.d(this.b);
            this.c.c(this.e);
            this.c.e(this.p);
            this.c.a(this.g);
            this.c.d(this.s);
            this.c.b(new AuthChangeListener() { // from class: com.huawei.ui.main.stories.devicecapacity.AuthActivity.4
                @Override // com.huawei.ui.main.stories.devicecapacity.AuthActivity.AuthChangeListener
                public void updateAuth(boolean z) {
                    AuthActivity.this.o.setEnabled(z);
                }
            });
            this.q.setAdapter(this.c);
        } catch (Exception unused) {
            LogUtil.b("AuthActivity", "buildListView Exception");
        }
    }

    private void j() {
        Iterator<pfo> it = this.j.iterator();
        while (it.hasNext()) {
            this.g.add(Boolean.valueOf(it.next().e()));
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
