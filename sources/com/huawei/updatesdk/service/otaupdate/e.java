package com.huawei.updatesdk.service.otaupdate;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;
import com.huawei.updatesdk.service.appmgr.bean.ApkUpgradeInfo;
import com.huawei.updatesdk.service.appmgr.bean.Param;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes7.dex */
public class e extends AsyncTask<Void, Void, com.huawei.updatesdk.a.b.c.c.d> {
    private final Context b;
    private final CheckUpdateCallBack c;
    private final UpdateParams d;
    private Toast e;
    private com.huawei.updatesdk.a.b.c.b g;

    /* renamed from: a, reason: collision with root package name */
    private final List<String> f10876a = new ArrayList();
    private boolean f = false;
    private boolean h = false;

    @Override // android.os.AsyncTask
    protected void onPreExecute() {
        super.onPreExecute();
        if (c()) {
            Context context = this.b;
            Toast makeText = Toast.makeText(context, com.huawei.updatesdk.b.h.c.c(context, "upsdk_checking_update_prompt"), 1);
            this.e = makeText;
            makeText.show();
        }
    }

    @Override // android.os.AsyncTask
    protected void onCancelled() {
        super.onCancelled();
        com.huawei.updatesdk.a.b.c.b bVar = this.g;
        if (bVar != null) {
            bVar.b();
        }
    }

    public void b(boolean z) {
        this.f = z;
    }

    public void a(boolean z) {
        this.h = z;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onPostExecute(com.huawei.updatesdk.a.b.c.c.d dVar) {
        ArrayList<ApkUpgradeInfo> arrayList;
        com.huawei.updatesdk.b.g.b.a().remove(this);
        a();
        if (this.c == null) {
            com.huawei.updatesdk.a.a.a.c("UpdateSDKCheckTask", "UpdateSDK callback is null");
        }
        if (dVar == null) {
            if (this.c != null) {
                Intent intent = new Intent();
                intent.putExtra("status", 3);
                this.c.onUpdateInfo(intent);
                return;
            }
            return;
        }
        int b = dVar.b();
        if (dVar.e()) {
            com.huawei.updatesdk.service.appmgr.bean.e eVar = (com.huawei.updatesdk.service.appmgr.bean.e) dVar;
            arrayList = eVar.list;
            if (!com.huawei.updatesdk.b.h.d.a(eVar.notRcmList)) {
                ApkUpgradeInfo apkUpgradeInfo = eVar.notRcmList.get(0);
                com.huawei.updatesdk.a.a.a.a("UpdateSDKCheckTask", "Updateinfo is not recommend, reason: " + apkUpgradeInfo.getNotRcmReason_() + " ,is same signature: " + apkUpgradeInfo.getSameS_());
            }
            a((List<ApkUpgradeInfo>) arrayList);
            if (com.huawei.updatesdk.b.h.d.a(arrayList) && this.c != null) {
                com.huawei.updatesdk.a.a.a.b("UpdateSDKCheckTask", "no upgrade info");
                Intent intent2 = new Intent();
                intent2.putExtra("status", 3);
                intent2.putExtra(UpdateKey.RESPONSE_CODE, b);
                this.c.onUpdateInfo(intent2);
            }
        } else {
            b(dVar);
            com.huawei.updatesdk.a.a.a.a("UpdateSDKCheckTask", "get app update msg failed,responseCode is " + dVar.d() + ",failreason: " + dVar.c() + ",response: " + dVar);
            arrayList = null;
        }
        if (com.huawei.updatesdk.b.h.d.a(arrayList)) {
            if (c()) {
                com.huawei.updatesdk.a.a.a.b("UpdateSDKCheckTask", "show no upgrade info toast.");
                Context context = this.b;
                Toast.makeText(context, com.huawei.updatesdk.b.h.c.c(context, "upsdk_update_check_no_new_version"), 0).show();
                return;
            } else {
                com.huawei.updatesdk.a.a.a.b("UpdateSDKCheckTask", "no upgrade info: " + this.f10876a.toString());
                return;
            }
        }
        ApkUpgradeInfo apkUpgradeInfo2 = arrayList.get(0);
        d.a().a(this.c);
        if (this.c != null) {
            Intent intent3 = new Intent();
            intent3.putExtra(UpdateKey.INFO, (Serializable) apkUpgradeInfo2);
            intent3.putParcelableArrayListExtra(UpdateKey.INFO_LIST, arrayList);
            intent3.putExtra("status", 7);
            intent3.putExtra(UpdateKey.RESPONSE_CODE, b);
            this.c.onUpdateInfo(intent3);
        }
        a(arrayList);
        if (this.d.isShowImmediate()) {
            a(this.b, apkUpgradeInfo2);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public com.huawei.updatesdk.a.b.c.c.d doInBackground(Void... voidArr) {
        com.huawei.updatesdk.a.a.c.a.a.a.a("UpdateSDKCheckTask", "CheckOtaAndUpdataTask doInBackground");
        com.huawei.updatesdk.b.g.b.a(this);
        b();
        com.huawei.updatesdk.a.a.a.b("UpdateSDKCheckTask", "start check update and packageNames are: " + this.f10876a.toString());
        com.huawei.updatesdk.b.e.a a2 = com.huawei.updatesdk.b.e.e.a(this.h);
        a2.a(this.f10876a);
        com.huawei.updatesdk.b.b.a.d().c();
        com.huawei.updatesdk.a.b.c.c.c.c(a2.b(this.b));
        com.huawei.updatesdk.b.b.a.d().c(f.e().a());
        return a(this.b, this.f10876a);
    }

    private boolean c() {
        return !this.f && TextUtils.isEmpty(this.d.getTargetPkgName()) && com.huawei.updatesdk.b.h.d.a(this.d.getPackageList()) && com.huawei.updatesdk.b.h.d.a(this.d.getParamList());
    }

    private void b(com.huawei.updatesdk.a.b.c.c.d dVar) {
        if (this.c != null) {
            Intent intent = new Intent();
            intent.putExtra("status", 6);
            if (dVar.a() != null) {
                intent.putExtra(UpdateKey.FAIL_CODE, dVar.a().ordinal());
            }
            intent.putExtra(UpdateKey.FAIL_REASON, dVar.c());
            intent.putExtra(UpdateKey.RESPONSE_CODE, dVar.b());
            this.c.onUpdateInfo(intent);
            this.c.onUpdateStoreError(dVar.d());
        }
    }

    private void b() {
        if (!com.huawei.updatesdk.b.h.d.a(this.d.getParamList())) {
            Iterator<Param> it = this.d.getParamList().iterator();
            while (it.hasNext()) {
                this.f10876a.add(it.next().getPackageName());
            }
            return;
        }
        if (!com.huawei.updatesdk.b.h.d.a(this.d.getPackageList())) {
            this.f10876a.addAll(this.d.getPackageList());
            return;
        }
        String targetPkgName = this.d.getTargetPkgName();
        if (TextUtils.isEmpty(targetPkgName)) {
            targetPkgName = this.b.getPackageName();
        }
        if (TextUtils.isEmpty(targetPkgName)) {
            return;
        }
        this.f10876a.add(targetPkgName);
    }

    private void a(List<ApkUpgradeInfo> list) {
        if (com.huawei.updatesdk.b.h.d.a(list)) {
            return;
        }
        for (ApkUpgradeInfo apkUpgradeInfo : list) {
            if (apkUpgradeInfo != null && !TextUtils.isEmpty(apkUpgradeInfo.getFullDownUrl_())) {
                if (apkUpgradeInfo.getDiffSize_() > 0) {
                    apkUpgradeInfo.setDiffDownUrl_(apkUpgradeInfo.getDownurl_());
                }
                apkUpgradeInfo.setDownurl_(apkUpgradeInfo.getFullDownUrl_());
            }
        }
    }

    private void a(ArrayList<ApkUpgradeInfo> arrayList) {
        Iterator<ApkUpgradeInfo> it = arrayList.iterator();
        while (it.hasNext()) {
            ApkUpgradeInfo next = it.next();
            com.huawei.updatesdk.a.a.a.b("UpdateSDKCheckTask", "ApkUpgradeInfo, version=" + next.getVersion_() + ", versionCode=" + next.getVersionCode_() + ", detailId=" + next.getDetailId_() + ", devType=" + next.getDevType_() + ", packageName=" + next.getPackage_() + ", oldVersionCode=" + next.getOldVersionCode_());
        }
    }

    private void a(Context context, ApkUpgradeInfo apkUpgradeInfo) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, (Class<?>) AppUpdateActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("app_update_parm", apkUpgradeInfo);
        bundle.putBoolean("app_must_btn", this.d.isMustBtnOne());
        bundle.putBoolean("is_apptouch", this.h);
        intent.putExtras(bundle);
        if (!(context instanceof Activity)) {
            intent.setFlags(268435456);
        }
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            com.huawei.updatesdk.a.a.c.a.a.a.b("UpdateSDKCheckTask", "go AppUpdateActivity error: " + e.getMessage());
        }
    }

    private void a() {
        Toast toast = this.e;
        if (toast != null) {
            toast.cancel();
        }
    }

    private com.huawei.updatesdk.a.b.c.c.d a(Context context, List<String> list) {
        com.huawei.updatesdk.service.appmgr.bean.d a2;
        String str;
        if (com.huawei.updatesdk.b.h.d.a(this.d.getParamList())) {
            ArrayList arrayList = new ArrayList();
            Iterator<String> it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(com.huawei.updatesdk.b.h.b.e(context, it.next()));
            }
            a2 = com.huawei.updatesdk.service.appmgr.bean.d.a(arrayList);
        } else {
            a2 = new com.huawei.updatesdk.service.appmgr.bean.d(this.d.getParamList());
        }
        com.huawei.updatesdk.b.e.a a3 = com.huawei.updatesdk.b.e.e.a(this.h);
        String b = a3.b();
        a2.e(a3.a(context, b));
        a2.b(0);
        a2.d(com.huawei.updatesdk.a.a.d.i.c.b(context, b));
        try {
            Context createPackageContext = this.b.createPackageContext(a3.b(), 3);
            str = createPackageContext.getResources().getString(createPackageContext.getResources().getIdentifier("wd_cno", "string", a3.b()));
        } catch (Throwable th) {
            com.huawei.updatesdk.a.a.c.a.a.a.b("UpdateSDKCheckTask", "get cno error: " + th.getMessage());
            str = "";
        }
        a2.e(str);
        if (isCancelled()) {
            com.huawei.updatesdk.a.a.a.c("UpdateSDKCheckTask", "UpdateSDK task is canceled and return empty upgradeInfo");
            return null;
        }
        com.huawei.updatesdk.a.b.c.b bVar = new com.huawei.updatesdk.a.b.c.b(a2, null);
        this.g = bVar;
        return bVar.c();
    }

    public e(Context context, UpdateParams updateParams, CheckUpdateCallBack checkUpdateCallBack) {
        this.d = updateParams;
        this.b = context;
        this.c = checkUpdateCallBack;
        f.e().b(updateParams.getServiceZone());
    }
}
