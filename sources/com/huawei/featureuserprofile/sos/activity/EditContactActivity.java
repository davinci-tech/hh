package com.huawei.featureuserprofile.sos.activity;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import com.huawei.featureuserprofile.sos.adapter.EditContactAdapter;
import com.huawei.featureuserprofile.sos.interf.ContactInfoChangeListener;
import com.huawei.featureuserprofile.sos.manager.EmergencyInfoManager;
import com.huawei.featureuserprofile.sos.manager.WrapContentLinearLayoutManager;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.networkclient.ResultCallback;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.dialog.CustomAlertDialog;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.commonui.utils.CustomPermissionAction;
import defpackage.btz;
import defpackage.bua;
import defpackage.buh;
import defpackage.gmx;
import defpackage.koq;
import defpackage.nrh;
import health.compact.a.Utils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/* loaded from: classes.dex */
public class EditContactActivity extends BaseActivity implements EditContactAdapter.EditContactAdapterCallback, ContactInfoChangeListener {
    private EditContactAdapter b;
    private EmergencyInfoManager.c c;
    private boolean d;
    private CustomAlertDialog f;
    private RelativeLayout j;

    /* renamed from: a, reason: collision with root package name */
    private List<gmx> f2015a = new ArrayList(10);
    private Handler e = new Handler(Looper.getMainLooper());

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().addFlags(8192);
        setContentView(R.layout.edit_contact_activity_layout);
        e();
        j();
        HealthRecycleView healthRecycleView = (HealthRecycleView) findViewById(R.id.recycler_view);
        healthRecycleView.setLayoutManager(new WrapContentLinearLayoutManager(this, 1, false));
        EditContactAdapter editContactAdapter = new EditContactAdapter(this, this.f2015a);
        this.b = editContactAdapter;
        healthRecycleView.setAdapter(editContactAdapter);
        healthRecycleView.a(false);
        healthRecycleView.d(false);
        h();
        setViewSafeRegion(false, healthRecycleView);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.rel_route_loading);
        this.j = relativeLayout;
        relativeLayout.setVisibility(8);
        LogUtil.a("EditContactActivity", "onCreate");
    }

    private void e() {
        CustomTitleBar customTitleBar = (CustomTitleBar) findViewById(R.id.edit_contact_title_bar);
        customTitleBar.setRightButtonVisibility(8);
        customTitleBar.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.featureuserprofile.sos.activity.EditContactActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                EditContactActivity.this.finish();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    private void j() {
        this.f2015a = btz.c("emergency_contacts", getApplicationContext(), btz.a(this));
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        f();
        LogUtil.a("EditContactActivity", "onResume");
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        c(this.f);
        g();
        this.f2015a.clear();
        this.f2015a = null;
        LogUtil.a("EditContactActivity", "onDestroy");
    }

    private void g() {
        EmergencyInfoManager.c().n();
    }

    private void c(CustomAlertDialog customAlertDialog) {
        if (customAlertDialog == null || !customAlertDialog.isShowing()) {
            return;
        }
        customAlertDialog.dismiss();
    }

    @Override // com.huawei.featureuserprofile.sos.adapter.EditContactAdapter.EditContactAdapterCallback
    public void onAddContact() {
        d();
    }

    @Override // com.huawei.featureuserprofile.sos.adapter.EditContactAdapter.EditContactAdapterCallback
    public void onManuallyAddContact() {
        startActivityForResult(new Intent(this, (Class<?>) ManuallyAddActivity.class), 1002);
    }

    private void d() {
        PermissionUtil.b(this, PermissionUtil.PermissionType.READ_CONTACT, new CustomPermissionAction(this) { // from class: com.huawei.featureuserprofile.sos.activity.EditContactActivity.10
            @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
            public void onGranted() {
                try {
                    EditContactActivity.this.startActivityForResult(new Intent("android.intent.action.PICK", ContactsContract.CommonDataKinds.Phone.CONTENT_URI), 1001);
                } catch (ActivityNotFoundException unused) {
                    LogUtil.b("EditContactActivity", "activity not found: Intent.ACTION_PICK");
                }
            }

            @Override // com.huawei.ui.commonui.utils.CustomPermissionAction, com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
            public void onDenied(String str) {
                LogUtil.h("EditContactActivity", "permission denied");
                super.onDenied(str);
            }

            @Override // com.huawei.ui.commonui.utils.CustomPermissionAction, com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
            public void onForeverDenied(PermissionUtil.PermissionType permissionType) {
                LogUtil.h("EditContactActivity", "permission forever denied, show the guide window");
                super.onForeverDenied(permissionType);
            }
        });
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        Uri data;
        super.onActivityResult(i, i2, intent);
        if (i == 1001 && i2 == -1 && intent != null && (data = intent.getData()) != null) {
            gmx vf_ = EmergencyInfoManager.c().vf_(data);
            LogUtil.c("EditContactActivity", "contactInfo uri: ", data, "contactInfo: ", vf_.toString());
            if (vf_ != null && !TextUtils.isEmpty(vf_.e())) {
                d(vf_);
            }
        }
        if (i != 1002 || intent == null) {
            return;
        }
        Serializable serializableExtra = intent.getSerializableExtra("contactInfo");
        if (serializableExtra instanceof gmx) {
            d((gmx) serializableExtra);
        }
    }

    private void d(final gmx gmxVar) {
        if (this.f2015a.contains(gmxVar) || !this.f2015a.add(gmxVar)) {
            return;
        }
        this.e.post(new Runnable() { // from class: com.huawei.featureuserprofile.sos.activity.EditContactActivity.7
            @Override // java.lang.Runnable
            public void run() {
                EditContactActivity.this.b.notifyItemInserted(EditContactActivity.this.f2015a.indexOf(gmxVar));
            }
        });
        EmergencyInfoManager.c().e(this.f2015a, true);
    }

    @Override // com.huawei.featureuserprofile.sos.adapter.EditContactAdapter.EditContactAdapterCallback
    public void showRemoveContactDialog(EmergencyInfoManager.c cVar) {
        if (cVar == null) {
            LogUtil.h("EditContactActivity", "showRemoveContactDialog contact is null");
            return;
        }
        if (this.f == null) {
            this.f = b().c();
        }
        this.c = cVar;
        String b = cVar.b();
        CustomAlertDialog customAlertDialog = this.f;
        Locale locale = Locale.ENGLISH;
        String string = getString(R.string._2130848881_res_0x7f022c71);
        Object[] objArr = new Object[1];
        if (b.trim().length() == 0) {
            b = cVar.a();
        }
        objArr[0] = b;
        customAlertDialog.a(String.format(locale, string, objArr));
        this.f.show();
    }

    private CustomAlertDialog.Builder b() {
        CustomAlertDialog.Builder builder = new CustomAlertDialog.Builder(this);
        builder.e(R.string._2130848867_res_0x7f022c63);
        builder.cyn_(R.string._2130848866_res_0x7f022c62, new DialogInterface.OnClickListener() { // from class: com.huawei.featureuserprofile.sos.activity.EditContactActivity.9
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                LogUtil.a("EditContactActivity", "Canceling the deletion of a contact");
                ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
            }
        });
        builder.cyo_(R.string._2130848865_res_0x7f022c61, new DialogInterface.OnClickListener() { // from class: com.huawei.featureuserprofile.sos.activity.EditContactActivity.8
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                EditContactActivity.this.a();
                ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
            }
        });
        return builder;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        EmergencyInfoManager.c cVar;
        if (this.f == null || (cVar = this.c) == null) {
            LogUtil.h("EditContactActivity", "deleteContact mRemoveContactDialog or mDelContact is null");
            return;
        }
        if (TextUtils.isEmpty(cVar.b()) || TextUtils.isEmpty(this.c.a())) {
            LogUtil.a("EditContactActivity", "delete contact name or phoneNumber is Null");
            return;
        }
        int i = 0;
        while (true) {
            if (i >= this.f2015a.size()) {
                i = -1;
                break;
            }
            gmx gmxVar = this.f2015a.get(i);
            LogUtil.c("EditContactActivity", "contactInfo uri: ", gmxVar.d(), "contactInfo: ", this.c.vh_().toString());
            if (gmxVar.d().equals(this.c.vh_().toString())) {
                break;
            } else {
                i++;
            }
        }
        if (i < 0) {
            LogUtil.a("EditContactActivity", "mDelContact is not exists in mEmergencyContactInfo");
        } else if (Utils.o()) {
            LogUtil.a("EditContactActivity", "is oversea");
            c(i);
        } else {
            this.j.setVisibility(0);
            a(i);
        }
    }

    private void a(final int i) {
        buh.d(new ResultCallback<bua>() { // from class: com.huawei.featureuserprofile.sos.activity.EditContactActivity.6
            @Override // com.huawei.networkclient.ResultCallback
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public void onSuccess(bua buaVar) {
                if (buaVar.getResultCode().intValue() == 0) {
                    LogUtil.a("EditContactActivity", "queryFollowUsers success");
                    if (!koq.b(buaVar.a())) {
                        EditContactActivity.this.d(buaVar, i);
                        return;
                    }
                    LogUtil.a("EditContactActivity", "familyCareRelations is empty");
                    EditContactActivity.this.c();
                    EditContactActivity.this.c(i);
                    return;
                }
                LogUtil.a("EditContactActivity", "queryFollowUsers failure,code = ", buaVar.getResultCode());
                onFailure(null);
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                LogUtil.a("EditContactActivity", "queryFollowUsers failure");
                EditContactActivity.this.c();
                EditContactActivity.this.b(R.string._2130848904_res_0x7f022c88);
            }
        });
    }

    private void f() {
        final int size = this.f2015a.size();
        boolean z = btz.c(getApplicationContext()) || size >= 3;
        if (this.d != z) {
            return;
        }
        boolean z2 = !z;
        this.d = z2;
        this.b.d(z2);
        if (this.d) {
            this.e.post(new Runnable() { // from class: com.huawei.featureuserprofile.sos.activity.EditContactActivity.15
                @Override // java.lang.Runnable
                public void run() {
                    EditContactActivity.this.b.notifyItemInserted(size);
                }
            });
        } else {
            this.e.post(new Runnable() { // from class: com.huawei.featureuserprofile.sos.activity.EditContactActivity.11
                @Override // java.lang.Runnable
                public void run() {
                    EditContactActivity.this.b.notifyItemRemoved(size);
                }
            });
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        LogUtil.c("EditContactActivity", "onRequestPermissionsResult requestCode ", Integer.valueOf(i));
        super.onRequestPermissionsResult(i, strArr, iArr);
    }

    private void h() {
        if (PermissionUtil.e(this, new String[]{"android.permission.READ_CONTACTS"})) {
            EmergencyInfoManager.c().e((ContactInfoChangeListener) this);
            LogUtil.a("EditContactActivity", "registerContactChangeListener()");
        } else {
            LogUtil.a("EditContactActivity", "permission.READ_CONTACTS not request");
        }
    }

    @Override // com.huawei.featureuserprofile.sos.interf.ContactInfoChangeListener
    public void onContactInfoChange() {
        i();
        f();
    }

    private void i() {
        List<gmx> c = EmergencyInfoManager.c().c(this);
        ArrayList<gmx> arrayList = new ArrayList(10);
        arrayList.addAll(this.f2015a);
        for (gmx gmxVar : arrayList) {
            if (gmxVar == null) {
                LogUtil.h("EditContactActivity", "refreshUi contactInfo is null");
            } else {
                int indexOf = this.f2015a.indexOf(gmxVar);
                for (gmx gmxVar2 : c) {
                    if (gmxVar2 == null) {
                        LogUtil.h("EditContactActivity", "refreshUi newInfo is null");
                    } else if (gmxVar.d() == null || gmxVar2.d() == null) {
                        LogUtil.h("EditContactActivity", "refreshUi getUri() is null");
                    } else if (gmxVar.e() == null || gmxVar2.e() == null) {
                        LogUtil.h("EditContactActivity", "refreshUi getContactVersion() is null");
                    } else if (gmxVar.d().equals(gmxVar2.d()) && !gmxVar.e().equals(gmxVar2.e())) {
                        e(indexOf);
                    }
                }
                if (!c(c, gmxVar.d())) {
                    d(indexOf);
                    this.f2015a.remove(indexOf);
                }
            }
        }
    }

    private void e(final int i) {
        this.e.post(new Runnable() { // from class: com.huawei.featureuserprofile.sos.activity.EditContactActivity.13
            @Override // java.lang.Runnable
            public void run() {
                EditContactActivity.this.b.notifyItemChanged(i);
            }
        });
    }

    private boolean c(List<gmx> list, String str) {
        if (list == null || str == null) {
            LogUtil.h("EditContactActivity", "containsUri info or uri is null");
            return false;
        }
        for (gmx gmxVar : list) {
            if (gmxVar == null || gmxVar.d() == null) {
                LogUtil.h("EditContactActivity", "containsUri contactInfo or getUri is null");
                break;
            }
            if (gmxVar.d().equals(str)) {
                return true;
            }
        }
        return false;
    }

    private void d(final int i) {
        if (btz.b(getApplicationContext()) && this.f2015a.size() == 0) {
            finish();
        } else {
            this.e.post(new Runnable() { // from class: com.huawei.featureuserprofile.sos.activity.EditContactActivity.5
                @Override // java.lang.Runnable
                public void run() {
                    EditContactActivity.this.b.notifyItemRemoved(i);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(bua buaVar, int i) {
        boolean z = false;
        for (bua.b bVar : buaVar.a()) {
            if (this.c.b().equals(bVar.d()) && this.c.a().equals(bVar.c())) {
                a(bVar.a(), i);
                z = true;
            }
        }
        if (z) {
            return;
        }
        LogUtil.a("EditContactActivity", "no one match");
        c();
        c(i);
    }

    private void a(long j, final int i) {
        LogUtil.a("EditContactActivity", "deleteFamilyCareUser,huid: ", Long.valueOf(j));
        ArrayList arrayList = new ArrayList();
        arrayList.add(Long.valueOf(j));
        buh.a(arrayList, new ResultCallback<CloudCommonReponse>() { // from class: com.huawei.featureuserprofile.sos.activity.EditContactActivity.4
            @Override // com.huawei.networkclient.ResultCallback
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void onSuccess(CloudCommonReponse cloudCommonReponse) {
                EditContactActivity.this.c(i);
                EditContactActivity.this.c();
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                EditContactActivity.this.c();
                EditContactActivity.this.b(R.string._2130848904_res_0x7f022c88);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(final int i) {
        this.e.post(new Runnable() { // from class: com.huawei.featureuserprofile.sos.activity.EditContactActivity.3
            @Override // java.lang.Runnable
            public void run() {
                nrh.b(EditContactActivity.this, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        this.e.post(new Runnable() { // from class: com.huawei.featureuserprofile.sos.activity.EditContactActivity.1
            @Override // java.lang.Runnable
            public void run() {
                EditContactActivity.this.j.setVisibility(8);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i) {
        this.f2015a.remove(i);
        EmergencyInfoManager.c().e(this.f2015a, true);
        d(i);
        f();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
