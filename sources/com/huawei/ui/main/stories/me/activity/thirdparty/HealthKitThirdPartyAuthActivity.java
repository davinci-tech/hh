package com.huawei.ui.main.stories.me.activity.thirdparty;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.amap.api.services.core.AMapException;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiAppInfo;
import com.huawei.hihealth.HiHealthUserPermission;
import com.huawei.hihealth.HiUserPreference;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiDataOperateListener;
import com.huawei.hihealth.data.type.HiHealthDataType;
import com.huawei.hihealth.util.HiScopeUtil;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.MapKeyNames;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.me.activity.thirdparty.HealthKitThirdPartyAuthActivity;
import com.huawei.ui.main.stories.me.activity.thirdparty.adapter.ThirdPartAuthAdapter;
import defpackage.iip;
import defpackage.ird;
import defpackage.jdx;
import defpackage.koq;
import defpackage.nsj;
import defpackage.nsn;
import defpackage.nsy;
import defpackage.rhg;
import defpackage.rhx;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.Utils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* loaded from: classes9.dex */
public class HealthKitThirdPartyAuthActivity extends BaseActivity {
    private static String c;
    private static int d;
    private HiAppInfo h;
    private String j;
    private HealthButton l;
    private HealthButton m;
    private CustomTitleBar o;
    private Intent p;
    private HealthRecycleView q;
    private ThirdPartAuthAdapter r;
    private boolean s;
    private String t;
    private Drawable u;
    private int v;
    private String w;
    private rhg x;
    private static ArrayList<Integer> g = HiHealthDataType.s();
    private static ArrayList<Integer> e = HiHealthDataType.e();

    /* renamed from: a, reason: collision with root package name */
    private static ArrayList<Integer> f10355a = HiHealthDataType.c();
    private static ArrayList<Integer> b = HiHealthDataType.o();
    private List<rhg> f = new ArrayList(10);
    private List<Boolean> i = new ArrayList(10);
    private List<Boolean> n = new ArrayList(10);
    private String y = "TYPE_ONE";
    private Handler k = new Handler() { // from class: com.huawei.ui.main.stories.me.activity.thirdparty.HealthKitThirdPartyAuthActivity.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message == null) {
                return;
            }
            if (message.what == 0) {
                HealthKitThirdPartyAuthActivity.this.f();
            } else if (message.what == 1) {
                HealthKitThirdPartyAuthActivity.this.m();
            } else {
                LogUtil.a("HealthKitThirdPartyAuthActivity", "Bag handle message.what");
            }
        }
    };

    private boolean a(int i) {
        switch (i) {
            case AMapException.CODE_AMAP_CLIENT_UPLOADAUTO_STARTED_ERROR /* 2200 */:
            case 10006:
            case 10007:
            case 10008:
            case 10010:
            case 30005:
            case 30006:
            case 30007:
            case 31001:
            case 31002:
            case 44000:
            case 50001:
            case 101001:
            case 101002:
            case 101003:
            case 101201:
            case 101202:
            case 101204:
                return true;
            default:
                return false;
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        nsn.cLf_(this, bundle);
        super.onCreate(bundle);
        setContentView(R.layout.activity_third_party_auth);
        if (getIntent() == null) {
            finish();
            return;
        }
        this.p = getIntent();
        a();
        k();
        i();
        this.m.setOnClickListener(new View.OnClickListener() { // from class: rha
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HealthKitThirdPartyAuthActivity.this.dOe_(view);
            }
        });
    }

    public /* synthetic */ void dOe_(View view) {
        if (this.w == null && !o()) {
            long currentTimeMillis = System.currentTimeMillis();
            this.w = String.valueOf(currentTimeMillis);
            String c2 = nsj.c(currentTimeMillis);
            this.t = c2;
            LogUtil.a("HealthKitThirdPartyAuthActivity", "new mModifiedTime: ", c2);
        } else if (o()) {
            this.t = null;
            this.w = null;
            LogUtil.a("HealthKitThirdPartyAuthActivity", "all cancel mModifiedTime: ", null);
        } else {
            LogUtil.a("HealthKitThirdPartyAuthActivity", "save mModifiedTime: ", this.t);
        }
        SharedPreferenceManager.e(BaseApplication.getContext(), Integer.toString(10000), "auth_time_" + c, this.w, new StorageParams());
        for (rhg rhgVar : this.f) {
            int d2 = rhgVar.d();
            int f = rhgVar.f();
            if (d2 == 1 || d2 == 4) {
                if (f == 0) {
                    d(rhgVar.a(), rhgVar.j(), d);
                } else if (f == 1 || f == 2) {
                    e(rhgVar.a(), rhgVar.j(), d);
                } else {
                    LogUtil.a("HealthKitThirdPartyAuthActivity", "onClickCallback other condition");
                }
            }
        }
        finish();
        ViewClickInstrumentation.clickOnView(view);
    }

    private void a() {
        this.q = (HealthRecycleView) nsy.cMc_(this, R.id.rv_third_part_auth);
        this.o = (CustomTitleBar) nsy.cMc_(this, R.id.tb_third_party_auth);
        this.l = (HealthButton) nsy.cMc_(this, R.id.hw_show_delete);
        HealthButton healthButton = (HealthButton) nsy.cMc_(this, R.id.hw_show_confirm);
        this.m = healthButton;
        healthButton.setEnabled(false);
    }

    private void k() {
        this.q.setLayoutManager(new LinearLayoutManager(this));
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x007a A[ADDED_TO_REGION] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void i() {
        /*
            r9 = this;
            java.lang.String r0 = "app_info"
            java.lang.String r1 = "writeTypes"
            java.lang.String r2 = "readTypes"
            android.content.Intent r3 = r9.p     // Catch: java.lang.Exception -> Lae
            boolean r3 = r3.hasExtra(r2)     // Catch: java.lang.Exception -> Lae
            java.lang.String r4 = "HealthKitThirdPartyAuthActivity"
            r5 = 1
            r6 = 0
            if (r3 == 0) goto L96
            android.content.Intent r3 = r9.p     // Catch: java.lang.Exception -> Lae
            boolean r3 = r3.hasExtra(r1)     // Catch: java.lang.Exception -> Lae
            if (r3 == 0) goto L96
            r3 = 0
            android.content.Intent r7 = r9.p     // Catch: java.lang.Exception -> L61 java.lang.ArrayIndexOutOfBoundsException -> L6c
            int[] r2 = r7.getIntArrayExtra(r2)     // Catch: java.lang.Exception -> L61 java.lang.ArrayIndexOutOfBoundsException -> L6c
            android.content.Intent r7 = r9.p     // Catch: java.lang.Exception -> L5b java.lang.ArrayIndexOutOfBoundsException -> L5e
            int[] r3 = r7.getIntArrayExtra(r1)     // Catch: java.lang.Exception -> L5b java.lang.ArrayIndexOutOfBoundsException -> L5e
            android.content.Intent r1 = r9.p     // Catch: java.lang.Exception -> L5b java.lang.ArrayIndexOutOfBoundsException -> L5e
            java.lang.String r7 = "uidTypes"
            int r1 = r1.getIntExtra(r7, r6)     // Catch: java.lang.Exception -> L5b java.lang.ArrayIndexOutOfBoundsException -> L5e
            r9.v = r1     // Catch: java.lang.Exception -> L5b java.lang.ArrayIndexOutOfBoundsException -> L5e
            android.content.Intent r1 = r9.p     // Catch: java.lang.Exception -> L5b java.lang.ArrayIndexOutOfBoundsException -> L5e
            android.os.Parcelable r1 = r1.getParcelableExtra(r0)     // Catch: java.lang.Exception -> L5b java.lang.ArrayIndexOutOfBoundsException -> L5e
            com.huawei.hihealth.HiAppInfo r1 = (com.huawei.hihealth.HiAppInfo) r1     // Catch: java.lang.Exception -> L5b java.lang.ArrayIndexOutOfBoundsException -> L5e
            r7 = 2
            java.lang.Object[] r7 = new java.lang.Object[r7]     // Catch: java.lang.Exception -> L5b java.lang.ArrayIndexOutOfBoundsException -> L5e
            java.lang.String r8 = "hiAppInfo = "
            r7[r6] = r8     // Catch: java.lang.Exception -> L5b java.lang.ArrayIndexOutOfBoundsException -> L5e
            r7[r5] = r1     // Catch: java.lang.Exception -> L5b java.lang.ArrayIndexOutOfBoundsException -> L5e
            com.huawei.hwlogsmodel.LogUtil.c(r4, r7)     // Catch: java.lang.Exception -> L5b java.lang.ArrayIndexOutOfBoundsException -> L5e
            r9.h = r1     // Catch: java.lang.Exception -> L5b java.lang.ArrayIndexOutOfBoundsException -> L5e
            if (r1 == 0) goto L78
            java.lang.String r1 = r1.getPackageName()     // Catch: java.lang.Exception -> L5b java.lang.ArrayIndexOutOfBoundsException -> L5e
            com.huawei.ui.main.stories.me.activity.thirdparty.HealthKitThirdPartyAuthActivity.c = r1     // Catch: java.lang.Exception -> L5b java.lang.ArrayIndexOutOfBoundsException -> L5e
            com.huawei.hihealth.HiAppInfo r1 = r9.h     // Catch: java.lang.Exception -> L5b java.lang.ArrayIndexOutOfBoundsException -> L5e
            int r1 = r1.getAppId()     // Catch: java.lang.Exception -> L5b java.lang.ArrayIndexOutOfBoundsException -> L5e
            com.huawei.ui.main.stories.me.activity.thirdparty.HealthKitThirdPartyAuthActivity.d = r1     // Catch: java.lang.Exception -> L5b java.lang.ArrayIndexOutOfBoundsException -> L5e
            r9.n()     // Catch: java.lang.Exception -> L5b java.lang.ArrayIndexOutOfBoundsException -> L5e
            goto L78
        L5b:
            r1 = r3
            r3 = r2
            goto L62
        L5e:
            r1 = r3
            r3 = r2
            goto L6d
        L61:
            r1 = r3
        L62:
            java.lang.Object[] r2 = new java.lang.Object[r5]     // Catch: java.lang.Exception -> Lae
            java.lang.String r7 = "initData mIntent.getIntArrayExtra Exception"
            r2[r6] = r7     // Catch: java.lang.Exception -> Lae
            com.huawei.hwlogsmodel.LogUtil.a(r4, r2)     // Catch: java.lang.Exception -> Lae
            goto L76
        L6c:
            r1 = r3
        L6d:
            java.lang.Object[] r2 = new java.lang.Object[r5]     // Catch: java.lang.Exception -> Lae
            java.lang.String r7 = "initData ArrayIndexOutOfBoundsException"
            r2[r6] = r7     // Catch: java.lang.Exception -> Lae
            com.huawei.hwlogsmodel.LogUtil.a(r4, r2)     // Catch: java.lang.Exception -> Lae
        L76:
            r2 = r3
            r3 = r1
        L78:
            if (r2 != 0) goto L86
            if (r3 != 0) goto L86
            java.lang.Object[] r0 = new java.lang.Object[r5]     // Catch: java.lang.Exception -> Lae
            java.lang.String r1 = "read write list null"
            r0[r6] = r1     // Catch: java.lang.Exception -> Lae
            com.huawei.hwlogsmodel.LogUtil.c(r4, r0)     // Catch: java.lang.Exception -> Lae
            return
        L86:
            java.lang.String r1 = "TYPE_ONE"
            r9.y = r1     // Catch: java.lang.Exception -> Lae
            android.content.Intent r1 = r9.p     // Catch: java.lang.Exception -> Lae
            android.os.Parcelable r0 = r1.getParcelableExtra(r0)     // Catch: java.lang.Exception -> Lae
            com.huawei.hihealth.HiAppInfo r0 = (com.huawei.hihealth.HiAppInfo) r0     // Catch: java.lang.Exception -> Lae
            r9.b(r2, r3, r0, r6)     // Catch: java.lang.Exception -> Lae
            goto Lb1
        L96:
            android.content.Intent r0 = r9.p     // Catch: java.lang.Exception -> Lae
            java.lang.String r1 = "app_id"
            boolean r0 = r0.hasExtra(r1)     // Catch: java.lang.Exception -> Lae
            if (r0 == 0) goto La4
            r9.c()     // Catch: java.lang.Exception -> Lae
            goto Lb1
        La4:
            java.lang.Object[] r0 = new java.lang.Object[r5]     // Catch: java.lang.Exception -> Lae
            java.lang.String r1 = "invalid intent"
            r0[r6] = r1     // Catch: java.lang.Exception -> Lae
            com.huawei.hwlogsmodel.LogUtil.h(r4, r0)     // Catch: java.lang.Exception -> Lae
            goto Lb1
        Lae:
            r9.finish()
        Lb1:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.ui.main.stories.me.activity.thirdparty.HealthKitThirdPartyAuthActivity.i():void");
    }

    private void c() throws PackageManager.NameNotFoundException {
        this.y = "TYPE_TWO";
        try {
            d = this.p.getIntExtra("app_id", 0);
        } catch (Exception unused) {
            LogUtil.a("HealthKitThirdPartyAuthActivity", "initData mIntent.getIntExtra Exception");
            d = 0;
        }
        HiAppInfo hiAppInfo = (HiAppInfo) this.p.getParcelableExtra(MapKeyNames.APP_INFO);
        PackageManager packageManager = getPackageManager();
        if (hiAppInfo != null) {
            this.h = hiAppInfo;
            n();
            this.o.setTitleText(this.j);
            c = hiAppInfo.getPackageName();
            if (this.s) {
                this.v = 0;
            } else {
                this.v = packageManager.getApplicationInfo(hiAppInfo.getPackageName(), 0).uid;
                c = this.h.getPackageName();
            }
        }
        String b2 = SharedPreferenceManager.b(BaseApplication.getContext(), Integer.toString(10000), "auth_time_" + c);
        this.w = b2;
        if (TextUtils.isEmpty(b2)) {
            this.w = null;
            this.t = null;
        } else {
            this.t = nsj.c(Long.parseLong(this.w));
        }
        b((int[]) null, (int[]) null, hiAppInfo, true);
    }

    private void n() {
        HiAppInfo hiAppInfo = this.h;
        if (hiAppInfo != null) {
            String appName = hiAppInfo.getAppName();
            this.j = appName;
            if (appName == null || !appName.startsWith("QuickApp_")) {
                return;
            }
            this.s = true;
            this.j = this.j.substring(9);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        ThirdPartAuthAdapter thirdPartAuthAdapter = new ThirdPartAuthAdapter(this.f, this.y, this.j, this.n);
        this.r = thirdPartAuthAdapter;
        this.q.setAdapter(thirdPartAuthAdapter);
        this.r.notifyDataSetChanged();
        this.r.b(new ThirdPartAuthAdapter.SetSwitchButtonChanged() { // from class: rhc
            @Override // com.huawei.ui.main.stories.me.activity.thirdparty.adapter.ThirdPartAuthAdapter.SetSwitchButtonChanged
            public final void switchButtonChanged(boolean z, List list) {
                HealthKitThirdPartyAuthActivity.this.c(z, list);
            }
        });
    }

    public /* synthetic */ void c(boolean z, List list) {
        this.f = list;
        if (z) {
            this.m.setEnabled(true);
        } else {
            this.m.setEnabled(false);
        }
    }

    private void b(int[] iArr, int[] iArr2, HiAppInfo hiAppInfo, boolean z) {
        a(hiAppInfo, z);
        LogUtil.a("HealthKitThirdPartyAuthActivity", "buildPermissionData readList = ", iArr, ", writeList = ", iArr2);
        a(iArr, iArr2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(d dVar) {
        boolean z;
        ArrayList<HiHealthUserPermission> arrayList = dVar.f10358a;
        if (koq.b(arrayList)) {
            z = true;
        } else {
            Iterator<HiHealthUserPermission> it = arrayList.iterator();
            z = false;
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                if (it.next().getAllowRead() != 1) {
                    z = false;
                    break;
                }
                z = true;
            }
        }
        ArrayList<HiHealthUserPermission> arrayList2 = dVar.b;
        if (z) {
            Iterator<HiHealthUserPermission> it2 = arrayList2.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    break;
                }
                if (it2.next().getAllowWrite() != 1) {
                    z = false;
                    break;
                }
                z = true;
            }
        }
        if (this.s && Utils.i() && koq.c(arrayList2)) {
            z = z && dVar.b().getAllowWrite() == 1;
        }
        this.x.d(z);
        this.f.set(0, this.x);
        this.f.add(new rhg(3, false, z));
        this.i.add(Boolean.valueOf(z));
        d(b(arrayList));
        a(b(arrayList2));
        c(dVar, true, z);
        b(dVar);
        c(dVar, false, z);
        l();
    }

    private void c(d dVar, boolean z, boolean z2) {
        if (koq.b(dVar.b)) {
            return;
        }
        if (z) {
            this.f.add(new rhg(2, true, z, z2));
        }
        HiAppInfo hiAppInfo = this.h;
        if ((hiAppInfo == null || !"com.huawei.health.ecg.collection".equals(hiAppInfo.getPackageName())) && "TYPE_TWO".equals(this.y) && !z) {
            this.k.sendEmptyMessage(1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        this.l.setVisibility(0);
        this.l.setOnClickListener(new View.OnClickListener() { // from class: rgz
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HealthKitThirdPartyAuthActivity.this.dOf_(view);
            }
        });
    }

    public /* synthetic */ void dOf_(View view) {
        g();
        ViewClickInstrumentation.clickOnView(view);
    }

    private boolean o() {
        Iterator<rhg> it = this.f.iterator();
        while (it.hasNext()) {
            if (it.next().j()) {
                return false;
            }
        }
        return true;
    }

    private void l() {
        Iterator<rhg> it = this.f.iterator();
        while (it.hasNext()) {
            this.n.add(Boolean.valueOf(it.next().j()));
        }
    }

    private void b(d dVar) {
        if (!koq.b(dVar.b) && Utils.i() && this.s) {
            HiHealthUserPermission b2 = dVar.b();
            this.f.add(new rhg(4, true, 2, b2.getScopeId(), b2.getAllowWrite() == 1));
            this.i.add(Boolean.valueOf(b2.getAllowWrite() == 1));
        }
    }

    private void a(ArrayList<HiHealthUserPermission> arrayList) {
        int i = 0;
        while (i < arrayList.size()) {
            HiHealthUserPermission hiHealthUserPermission = arrayList.get(i);
            int scopeId = hiHealthUserPermission.getScopeId();
            boolean z = true;
            this.f.add(new rhg(a(scopeId) ? 4 : 1, i == 0, 1, scopeId, hiHealthUserPermission.getAllowWrite() == 1));
            List<Boolean> list = this.i;
            if (hiHealthUserPermission.getAllowWrite() != 1) {
                z = false;
            }
            list.add(Boolean.valueOf(z));
            i++;
        }
    }

    private void d(ArrayList<HiHealthUserPermission> arrayList) {
        int i = 0;
        while (i < arrayList.size()) {
            HiHealthUserPermission hiHealthUserPermission = arrayList.get(i);
            int scopeId = hiHealthUserPermission.getScopeId();
            boolean z = true;
            this.f.add(new rhg(a(scopeId) ? 4 : 1, i == 0, 0, scopeId, hiHealthUserPermission.getAllowRead() == 1));
            List<Boolean> list = this.i;
            if (hiHealthUserPermission.getAllowRead() != 1) {
                z = false;
            }
            list.add(Boolean.valueOf(z));
            i++;
        }
    }

    static class d {

        /* renamed from: a, reason: collision with root package name */
        public ArrayList<HiHealthUserPermission> f10358a;
        public ArrayList<HiHealthUserPermission> b;
        private HiHealthUserPermission d;

        private d() {
            this.f10358a = new ArrayList<>();
            this.b = new ArrayList<>();
        }

        public HiHealthUserPermission b() {
            return this.d;
        }

        public void c(HiHealthUserPermission hiHealthUserPermission) {
            this.d = hiHealthUserPermission;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("readPermissionType = ");
            Iterator<HiHealthUserPermission> it = this.f10358a.iterator();
            while (it.hasNext()) {
                sb.append(it.next());
                sb.append(", ");
            }
            sb.append("writePermissionType = ");
            Iterator<HiHealthUserPermission> it2 = this.b.iterator();
            while (it2.hasNext()) {
                sb.append(it2.next());
                sb.append(", ");
            }
            return sb.toString();
        }
    }

    HiHealthUserPermission d() {
        HiHealthUserPermission hiHealthUserPermission = new HiHealthUserPermission();
        hiHealthUserPermission.setScopeId(101000);
        hiHealthUserPermission.setAllowWrite(0);
        return hiHealthUserPermission;
    }

    private void a(final HiAppInfo hiAppInfo, boolean z) {
        rhg rhgVar = new rhg(0, this.u, this.t, z, false);
        this.x = rhgVar;
        this.f.add(rhgVar);
        this.k.sendEmptyMessage(0);
        jdx.b(new Runnable() { // from class: rgy
            @Override // java.lang.Runnable
            public final void run() {
                HealthKitThirdPartyAuthActivity.this.d(hiAppInfo);
            }
        });
    }

    public /* synthetic */ void d(HiAppInfo hiAppInfo) {
        if (hiAppInfo != null) {
            if (hiAppInfo.getAppName() != null && hiAppInfo.getAppName().startsWith("QuickApp_")) {
                this.u = rhx.dOD_(this, hiAppInfo.getPackageName());
            } else {
                this.u = rhx.dOC_(this, hiAppInfo.getPackageName());
            }
            this.x.dOo_(this.u);
        }
        this.f.set(0, this.x);
        this.k.sendEmptyMessage(0);
    }

    private void a(final int[] iArr, final int[] iArr2) {
        final ird d2 = ird.d(getApplicationContext());
        HiHealthNativeApi.a(this).queryHealthKitPermission(d, new HiDataOperateListener() { // from class: com.huawei.ui.main.stories.me.activity.thirdparty.HealthKitThirdPartyAuthActivity.4
            @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
            public void onResult(int i, Object obj) {
                if (!(obj instanceof List)) {
                    LogUtil.h("HealthKitThirdPartyAuthActivity", "getAlreadyHasPermission not list");
                    return;
                }
                List<HiHealthUserPermission> list = (List) obj;
                LogUtil.c("HealthKitThirdPartyAuthActivity", "buildPermissionData permissionList = ", list);
                ArrayList<HiHealthUserPermission> arrayList = new ArrayList<>(10);
                ArrayList<HiHealthUserPermission> arrayList2 = new ArrayList<>(10);
                d dVar = new d();
                dVar.c(HealthKitThirdPartyAuthActivity.this.d());
                for (HiHealthUserPermission hiHealthUserPermission : list) {
                    if (hiHealthUserPermission.getScopeId() == 101000) {
                        dVar.c(hiHealthUserPermission);
                    } else {
                        if (hiHealthUserPermission.getAllowRead() != 0) {
                            arrayList.add(hiHealthUserPermission);
                        }
                        if (hiHealthUserPermission.getAllowWrite() != 0) {
                            arrayList2.add(hiHealthUserPermission);
                        }
                    }
                }
                if (HealthKitThirdPartyAuthActivity.this.v != 0) {
                    int j = HealthKitThirdPartyAuthActivity.this.j();
                    d2.a(j, HealthKitThirdPartyAuthActivity.this.v, HealthKitThirdPartyAuthActivity.c);
                    dVar.f10358a = d2.b(arrayList, true, HealthKitThirdPartyAuthActivity.this.v, j);
                    dVar.b = d2.b(arrayList2, false, HealthKitThirdPartyAuthActivity.this.v, j);
                } else {
                    dVar.f10358a = arrayList;
                    dVar.b = arrayList2;
                }
                HealthKitThirdPartyAuthActivity healthKitThirdPartyAuthActivity = HealthKitThirdPartyAuthActivity.this;
                healthKitThirdPartyAuthActivity.a(healthKitThirdPartyAuthActivity.d(dVar, iArr, iArr2));
                HealthKitThirdPartyAuthActivity.this.k.sendEmptyMessage(0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int j() {
        try {
            return Integer.parseInt(HiScopeUtil.c(this, c));
        } catch (NumberFormatException unused) {
            LogUtil.b("HealthKitThirdPartyAuthActivity", "getAlreadyHasPermission NumberFormatException exists");
            return -1;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public d d(d dVar, int[] iArr, int[] iArr2) {
        d dVar2 = new d();
        dVar2.c(dVar.b());
        ArrayList arrayList = new ArrayList(10);
        ArrayList arrayList2 = new ArrayList(10);
        Iterator<HiHealthUserPermission> it = dVar.f10358a.iterator();
        while (it.hasNext()) {
            arrayList2.add(Integer.valueOf(it.next().getScopeId()));
        }
        if (iArr != null) {
            for (int i : iArr) {
                if (!arrayList2.contains(Integer.valueOf(i))) {
                    HiHealthUserPermission hiHealthUserPermission = new HiHealthUserPermission();
                    hiHealthUserPermission.setAllowRead(2);
                    hiHealthUserPermission.setScopeId(i);
                    arrayList.add(hiHealthUserPermission);
                }
            }
        }
        arrayList.addAll(dVar.f10358a);
        dVar2.f10358a.addAll(arrayList);
        ArrayList arrayList3 = new ArrayList(10);
        Iterator<HiHealthUserPermission> it2 = dVar.b.iterator();
        while (it2.hasNext()) {
            arrayList3.add(Integer.valueOf(it2.next().getScopeId()));
        }
        ArrayList arrayList4 = new ArrayList(10);
        if (iArr2 != null) {
            for (int i2 : iArr2) {
                if (!arrayList3.contains(Integer.valueOf(i2))) {
                    HiHealthUserPermission hiHealthUserPermission2 = new HiHealthUserPermission();
                    hiHealthUserPermission2.setAllowWrite(2);
                    hiHealthUserPermission2.setScopeId(i2);
                    arrayList4.add(hiHealthUserPermission2);
                }
            }
        }
        arrayList4.addAll(dVar.b);
        dVar2.b.addAll(arrayList4);
        return dVar2;
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        if ("TYPE_ONE".equals(this.y)) {
            finishAndRemoveTask();
        }
    }

    private ArrayList<HiHealthUserPermission> b(ArrayList<HiHealthUserPermission> arrayList) {
        boolean z = false;
        ArrayList<HiHealthUserPermission> arrayList2 = new ArrayList<>(0);
        Iterator<HiHealthUserPermission> it = arrayList.iterator();
        boolean z2 = false;
        boolean z3 = false;
        boolean z4 = false;
        while (it.hasNext()) {
            HiHealthUserPermission next = it.next();
            int scopeId = next.getScopeId();
            if (HiHealthDataType.q(scopeId)) {
                if (!z) {
                    z = true;
                }
            }
            if (HiHealthDataType.o(scopeId)) {
                if (!z2) {
                    z2 = true;
                }
            }
            if (HiHealthDataType.n(scopeId)) {
                if (!z3) {
                    z3 = true;
                }
            }
            if (HiHealthDataType.l(scopeId)) {
                if (!z4) {
                    z4 = true;
                }
            }
            arrayList2.add(next);
        }
        return arrayList2;
    }

    public static void d(int i, boolean z, int i2) {
        LogUtil.a("HealthKitThirdPartyAuthActivity", "saveReadSelect");
        if (g.contains(Integer.valueOf(i))) {
            Iterator<Integer> it = g.iterator();
            while (it.hasNext()) {
                a(i2, it.next().intValue(), 0, z);
            }
            return;
        }
        if (e.contains(Integer.valueOf(i))) {
            Iterator<Integer> it2 = e.iterator();
            while (it2.hasNext()) {
                a(i2, it2.next().intValue(), 0, z);
            }
        } else if (f10355a.contains(Integer.valueOf(i))) {
            Iterator<Integer> it3 = f10355a.iterator();
            while (it3.hasNext()) {
                a(i2, it3.next().intValue(), 0, z);
            }
        } else {
            if (b.contains(Integer.valueOf(i))) {
                Iterator<Integer> it4 = b.iterator();
                while (it4.hasNext()) {
                    a(i2, it4.next().intValue(), 0, z);
                }
                return;
            }
            a(i2, i, 0, z);
        }
    }

    public static void e(int i, boolean z, int i2) {
        LogUtil.a("HealthKitThirdPartyAuthActivity", "saveWriteSelect");
        a(i2, i, 1, z);
    }

    private static void a(int i, int i2, int i3, boolean z) {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        HiHealthNativeApi.a((Context) null).updateHealthKitPermission(i, i2, i3, z, new HiDataOperateListener() { // from class: rgw
            @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
            public final void onResult(int i4, Object obj) {
                HealthKitThirdPartyAuthActivity.d(countDownLatch, i4, obj);
            }
        });
        try {
            countDownLatch.await(200L, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e2) {
            LogUtil.b("HealthKitThirdPartyAuthActivity", "savePermission InterruptedException ", e2.getMessage());
        }
    }

    public static /* synthetic */ void d(CountDownLatch countDownLatch, int i, Object obj) {
        if (i == 0) {
            LogUtil.a("HealthKitThirdPartyAuthActivity", "savePermission success");
        } else {
            LogUtil.a("HealthKitThirdPartyAuthActivity", "savePermission fail");
        }
        countDownLatch.countDown();
    }

    private void g() {
        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(this);
        builder.e(getString(R$string.IDS_hw_show_main_permission_delete_data_content, new Object[]{this.j})).czC_(R$string.IDS_device_privacy_clear, new AnonymousClass2()).czz_(R$string.IDS_hw_common_ui_dialog_cancel, new View.OnClickListener() { // from class: rgx
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        builder.e().show();
    }

    /* renamed from: com.huawei.ui.main.stories.me.activity.thirdparty.HealthKitThirdPartyAuthActivity$2, reason: invalid class name */
    public class AnonymousClass2 implements View.OnClickListener {
        AnonymousClass2() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            HiHealthManager.d(BaseApplication.getContext()).deleteAllKitHealthData(HealthKitThirdPartyAuthActivity.d, new HiDataOperateListener() { // from class: rhe
                @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
                public final void onResult(int i, Object obj) {
                    HealthKitThirdPartyAuthActivity.AnonymousClass2.b(i, obj);
                }
            });
            HealthKitThirdPartyAuthActivity.this.h();
            ViewClickInstrumentation.clickOnView(view);
        }

        public static /* synthetic */ void b(int i, Object obj) {
            if (!(obj instanceof Boolean)) {
                LogUtil.a("HealthKitThirdPartyAuthActivity", "deleteAllKitHealthData ErrorConstants.ERR_NONE");
            } else if (((Boolean) obj).booleanValue()) {
                LogUtil.a("HealthKitThirdPartyAuthActivity", "deleteAllKitHealthData ErrorConstants.SUCCESS");
            } else {
                LogUtil.a("HealthKitThirdPartyAuthActivity", "deleteAllKitHealthData ErrorConstants.ERR_NONE");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean h() {
        HiAppInfo c2 = iip.b().c(d);
        if (c2 == null || !"com.huawei.health.mc".equals(c2.getPackageName())) {
            return true;
        }
        boolean userPreference = HiHealthNativeApi.a(this).setUserPreference(new HiUserPreference("com.huawei.health.mc", ""));
        LogUtil.a("HealthKitThirdPartyAuthActivity", "deletePeriodConfig result: ", Boolean.valueOf(userPreference));
        return userPreference;
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
