package com.huawei.ui.device.activity.selectcontact;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.huawei.datatype.Contact;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.jsoperation.JsUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.dialog.CustomAlertDialog;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.toolbar.HealthToolBar;
import com.huawei.ui.commonui.utils.CustomPermissionAction;
import com.huawei.ui.device.activity.selectcontact.selectmvp.ContactSelectActivity;
import com.huawei.ui.device.interactors.DeviceSettingsInteractors;
import com.huawei.ui.device.views.selectcontact.ContactMainListAdapter;
import defpackage.ixx;
import defpackage.nrh;
import defpackage.nsy;
import defpackage.nxu;
import defpackage.oae;
import defpackage.sqo;
import health.compact.a.CommonUtil;
import health.compact.a.UnitUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

/* loaded from: classes6.dex */
public class ContactMainActivity extends BaseActivity implements View.OnClickListener, ActivityCompat.OnRequestPermissionsResultCallback {

    /* renamed from: a, reason: collision with root package name */
    private LinearLayout f9214a;
    private HealthTextView b;
    private LinearLayout c;
    private LinearLayout d;
    private ImageView e;
    private HealthTextView f;
    private HealthButton h;
    private ContactMainListAdapter j;
    private DeviceSettingsInteractors k;
    private Context l;
    private String o;
    private ListView p;
    private LinearLayout q;
    private HealthToolBar s;
    private LinearLayout t;
    private boolean n = false;
    private List<Contact> i = null;
    private long m = 0;
    private int r = 5;
    private Handler g = new a(this);

    class a extends Handler {
        WeakReference<ContactMainActivity> e;

        a(ContactMainActivity contactMainActivity) {
            this.e = new WeakReference<>(contactMainActivity);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message == null) {
                LogUtil.h("ContactMainActivity", "handleMessage message is null");
                return;
            }
            if (this.e.get() == null) {
                LogUtil.h("ContactMainActivity", "handleMessage activity is null");
                return;
            }
            LogUtil.a("ContactMainActivity", "handleMessage message.what: ", Integer.valueOf(message.what));
            int i = message.what;
            if (i == 1) {
                ContactMainActivity.this.e();
                return;
            }
            if (i == 2) {
                ContactMainActivity.this.d();
                return;
            }
            if (i == 3) {
                if (message.obj instanceof Boolean) {
                    ContactMainActivity.this.d(((Boolean) message.obj).booleanValue());
                    return;
                } else {
                    LogUtil.h("ContactMainActivity", "handleMessage message.obj not instanceof Boolean");
                    return;
                }
            }
            LogUtil.h("ContactMainActivity", "handleMessage not support message:", Integer.valueOf(message.what));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        LogUtil.a("ContactMainActivity", "enter handleSetSuccess!");
        a(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        LogUtil.a("ContactMainActivity", "enter handleSetFail!");
        nrh.b(this.l, R.string._2130841508_res_0x7f020fa4);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        cancelAdaptRingRegion();
        setContentView(R.layout.activity_device_settings_contact_main_activity_black);
        h();
        cSh_(bundle);
    }

    private void h() {
        this.s = (HealthToolBar) findViewById(R.id.buttomview);
        View inflate = View.inflate(BaseApplication.getContext(), R.layout.activity_device_settings_contact_main_activity_black_bottomview, null);
        this.s.cHd_(inflate);
        this.s.cHf_(this);
        this.f = (HealthTextView) nsy.cMc_(this, R.id.contact_main_null_tv);
        this.t = (LinearLayout) nsy.cMc_(this, R.id.contact_main_listview_layout);
        this.q = (LinearLayout) nsy.cMc_(this, R.id.contact_main_null_layout);
        this.d = (LinearLayout) inflate.findViewById(R.id.contact_main_bottom_add_layout);
        this.c = (LinearLayout) inflate.findViewById(R.id.contact_main_bottom_orderby_layout);
        this.f9214a = (LinearLayout) inflate.findViewById(R.id.contact_main_bottom_delete_layout);
        this.e = (ImageView) inflate.findViewById(R.id.contact_main_bottom_add_image);
        this.b = (HealthTextView) inflate.findViewById(R.id.contact_main_bottom_add);
        this.h = (HealthButton) nsy.cMc_(this, R.id.no_contact_add_button);
        this.d.setOnClickListener(this);
        this.c.setOnClickListener(this);
        this.f9214a.setOnClickListener(this);
        this.h.setOnClickListener(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(boolean z) {
        if (this.n) {
            j();
        } else {
            g();
        }
        if (z) {
            k();
        }
    }

    private void cSh_(Bundle bundle) {
        this.l = getApplicationContext();
        DeviceSettingsInteractors d = DeviceSettingsInteractors.d(getApplicationContext());
        this.k = d;
        int b = d.b(this.l);
        this.r = b;
        LogUtil.a("ContactMainActivity", "initData mMaxContactNumber: ", Integer.valueOf(b));
        if (bundle != null) {
            int i = bundle.getInt("maxContactNumber");
            this.r = i;
            ReleaseLogUtil.e("ContactMainActivity", "initData savedInstanceState mMaxContactNumber: ", Integer.valueOf(i));
        }
        if (this.r <= 0) {
            this.r = 5;
            LogUtil.h("ContactMainActivity", "initData get error mMaxContactNumber");
        }
        String e = oae.c(this.l).e();
        this.o = e;
        if (TextUtils.isEmpty(e) && bundle != null) {
            String string = bundle.getString("deviceName");
            this.o = string;
            ReleaseLogUtil.e("ContactMainActivity", "initData mDeviceName: ", CommonUtil.l(string));
        }
        a(false);
    }

    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putString("deviceName", this.o);
        bundle.putInt("maxContactNumber", this.r);
    }

    private void a(final boolean z) {
        LogUtil.a("ContactMainActivity", "loadData");
        this.k.c(this.l, new IBaseResponseCallback() { // from class: com.huawei.ui.device.activity.selectcontact.ContactMainActivity.5
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                ContactMainActivity contactMainActivity = ContactMainActivity.this;
                contactMainActivity.i = contactMainActivity.k.a(ContactMainActivity.this.l);
                if (ContactMainActivity.this.i == null || ContactMainActivity.this.i.isEmpty()) {
                    ContactMainActivity.this.n = false;
                    ContactMainActivity.this.i = new ArrayList(10);
                    LogUtil.a("ContactMainActivity", "loadDataWhenContactDatabaseNotHasData");
                } else {
                    ContactMainActivity.this.n = true;
                    LogUtil.a("ContactMainActivity", "loadDataWhenContactDatabaseHasData mContactTables: ", ContactMainActivity.this.i);
                }
                Message obtainMessage = ContactMainActivity.this.g.obtainMessage();
                obtainMessage.obj = Boolean.valueOf(z);
                obtainMessage.what = 3;
                ContactMainActivity.this.g.sendMessage(obtainMessage);
            }
        });
    }

    private void j() {
        ListView listView = (ListView) nsy.cMc_(this, R.id.contact_main_listview);
        this.p = listView;
        if (listView != null) {
            listView.setSelector(R.drawable.device_settings_contact_listview_item_selector_black);
            this.j = new ContactMainListAdapter(this, this.i);
            LogUtil.a("ContactMainActivity", "loadViewWhenContactDatabaseHasData getFooterViewsCount size: ", Integer.valueOf(this.p.getFooterViewsCount()));
            if (this.p.getFooterViewsCount() == 0) {
                LogUtil.a("ContactMainActivity", "loadViewWhenContactDatabaseHasData getFooterViewsCount size is 0");
                View inflate = LayoutInflater.from(this).inflate(R.layout.activity_device_settings_contact_main_listview_bottom_item_layout_black, (ViewGroup) null);
                ((HealthTextView) nsy.cMd_(inflate, R.id.footer_tv)).setText(String.format(Locale.ROOT, getResources().getString(R.string._2130841871_res_0x7f02110f), this.o, UnitUtil.e(this.r, 1, 0)));
                this.p.addFooterView(inflate);
            }
            this.p.setAdapter((ListAdapter) this.j);
        }
        this.h.setVisibility(8);
        this.s.setVisibility(0);
        this.t.setVisibility(0);
        this.d.setVisibility(0);
        if (this.i.size() > 1) {
            this.c.setVisibility(0);
        } else {
            this.c.setVisibility(8);
        }
        if (this.i.size() > this.r - 1) {
            this.e.setImageResource(R.mipmap._2131820811_res_0x7f11010b);
            this.b.setTextColor(ContextCompat.getColor(this.l, R.color._2131299244_res_0x7f090bac));
        } else {
            this.e.setImageResource(R.drawable._2131427799_res_0x7f0b01d7);
            this.b.setTextColor(ContextCompat.getColorStateList(this.l, R.color._2131296804_res_0x7f090224));
        }
        this.f9214a.setVisibility(0);
        this.q.setVisibility(8);
    }

    private void g() {
        this.q.setVisibility(0);
        this.s.setVisibility(8);
        this.d.setVisibility(8);
        this.t.setVisibility(8);
        this.c.setVisibility(8);
        this.f9214a.setVisibility(8);
        this.h.setVisibility(0);
        this.f.setText(String.format(Locale.ROOT, getResources().getString(R.string._2130841870_res_0x7f02110e), this.o));
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (i()) {
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        int id = view.getId();
        if (id == R.id.contact_main_bottom_add_layout) {
            LogUtil.a("03", 1, "ContactMainActivity", "contact_main_bottom_add_layout");
            f();
        } else if (id == R.id.contact_main_bottom_orderby_layout) {
            LogUtil.a("ContactMainActivity", "contact_main_bottom_orderby_layout");
            l();
        } else if (id == R.id.contact_main_bottom_delete_layout) {
            LogUtil.a("03", 1, "ContactMainActivity", "contact_main_bottom_delete_layout");
            o();
            HashMap hashMap = new HashMap(16);
            hashMap.put("click", "1");
            hashMap.put("status", "delete");
            ixx.d().d(BaseApplication.getContext(), AnalyticsValue.HOME_1010031.value(), hashMap, 0);
        } else if (id == R.id.no_contact_add_button) {
            LogUtil.a("03", 1, "ContactMainActivity", "no_contact_add_button");
            f();
            HashMap hashMap2 = new HashMap(16);
            hashMap2.put("click", "1");
            hashMap2.put("status", "add");
            ixx.d().d(BaseApplication.getContext(), AnalyticsValue.HOME_1010031.value(), hashMap2, 0);
        } else {
            LogUtil.h("ContactMainActivity", "viewId: ", Integer.valueOf(id));
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private boolean i() {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.m < 1000) {
            LogUtil.a("ContactMainActivity", "isClickFast click too much");
            this.m = currentTimeMillis;
            return true;
        }
        this.m = currentTimeMillis;
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(boolean z) {
        List<Contact> list = this.i;
        if (list == null) {
            ReleaseLogUtil.d("DEVMGR_ContactMainActivity", "startAddContactActivity mContactTables is null");
            sqo.b("ContactMainActivity startAddContactActivity: mContactTables is null");
            return;
        }
        int size = list.size();
        int i = this.r;
        if (size >= i) {
            ReleaseLogUtil.d("DEVMGR_ContactMainActivity", "startAddContactActivity mMaxContactNumber: ", Integer.valueOf(i));
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < this.i.size(); i2++) {
            Contact contact = this.i.get(i2);
            arrayList.add(contact.getName() + contact.getPhoneNumbers().get(0).getPhoneNumber());
        }
        int size2 = this.r - this.i.size();
        if (z) {
            Intent intent = new Intent(this.l, (Class<?>) ContactSelectActivity.class);
            intent.putExtra("com.huawei.community.action.MAX_SELECT_COUNT", size2);
            intent.putExtra("com.huawei.community.action.OLD_CONTACTS", arrayList);
            startActivityForResult(intent, 1);
            return;
        }
        Intent intent2 = new Intent(this.l, (Class<?>) ContactPermissionActivity.class);
        intent2.putExtra("com.huawei.community.action.MAX_SELECT_COUNT", size2);
        startActivityForResult(intent2, 2);
    }

    private void f() {
        List<Contact> list = this.i;
        if (list == null) {
            ReleaseLogUtil.d("DEVMGR_ContactMainActivity", "requestPermissions mContactTables is null");
            sqo.b("ContactMainActivity requestPermissions: mContactTables is null");
            return;
        }
        int size = list.size();
        int i = this.r;
        if (size >= i) {
            ReleaseLogUtil.d("DEVMGR_ContactMainActivity", "requestPermissions mMaxContactNumber: ", Integer.valueOf(i));
        } else {
            PermissionUtil.b(this, PermissionUtil.PermissionType.READ_CONTACT, new CustomPermissionAction(this.l) { // from class: com.huawei.ui.device.activity.selectcontact.ContactMainActivity.2
                @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
                public void onGranted() {
                    ContactMainActivity.this.e(true);
                }

                @Override // com.huawei.ui.commonui.utils.CustomPermissionAction, com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
                public void onDenied(String str) {
                    super.onDenied(str);
                    LogUtil.h("ContactMainActivity", "requestPermissions isContactPermission and isCallLogPermission onDenied");
                    if (CommonUtil.bd()) {
                        ContactMainActivity.this.a();
                    } else {
                        ContactMainActivity.this.b();
                    }
                }

                @Override // com.huawei.ui.commonui.utils.CustomPermissionAction, com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
                public void onForeverDenied(PermissionUtil.PermissionType permissionType) {
                    LogUtil.h("ContactMainActivity", "requestPermissions isContactPermission and isCallLogPermission onForeverDenied");
                    if (CommonUtil.bd()) {
                        ContactMainActivity.this.a();
                    } else {
                        ContactMainActivity.this.b();
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        View inflate = View.inflate(this, R.layout.dialog_contact_permission_layout, null);
        CustomAlertDialog.Builder builder = new CustomAlertDialog.Builder(this);
        builder.cyp_(inflate).a((String) null).cyo_(R.string._2130842041_res_0x7f0211b9, new DialogInterface.OnClickListener() { // from class: com.huawei.ui.device.activity.selectcontact.ContactMainActivity.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                LogUtil.a("ContactMainActivity", "gotoSettingPermission go setting");
                nxu.cSe_(ContactMainActivity.this, 3);
                ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
            }
        }).cyn_(R.string._2130841130_res_0x7f020e2a, new DialogInterface.OnClickListener() { // from class: com.huawei.ui.device.activity.selectcontact.ContactMainActivity.3
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                LogUtil.a("ContactMainActivity", "gotoSettingPermission go add");
                ContactMainActivity.this.e(false);
                ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
            }
        });
        CustomAlertDialog c = builder.c();
        c.setCancelable(false);
        c.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        CustomTextAlertDialog.Builder builder = new CustomTextAlertDialog.Builder(this);
        builder.b(R.string._2130842089_res_0x7f0211e9).d(R.string._2130842367_res_0x7f0212ff).cyU_(R.string._2130842041_res_0x7f0211b9, new View.OnClickListener() { // from class: com.huawei.ui.device.activity.selectcontact.ContactMainActivity.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("ContactMainActivity", "gotoSettingPermissionOtherPhone go setting");
                nxu.cSe_(ContactMainActivity.this, 3);
                ViewClickInstrumentation.clickOnView(view);
            }
        }).cyR_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: com.huawei.ui.device.activity.selectcontact.ContactMainActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("ContactMainActivity", "gotoSettingPermissionOtherPhone go add");
                ContactMainActivity.this.e(false);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        CustomTextAlertDialog a2 = builder.a();
        a2.setCancelable(false);
        a2.show();
    }

    private void l() {
        if (this.i.isEmpty()) {
            nrh.b(this.l, R.string._2130841404_res_0x7f020f3c);
            return;
        }
        Intent intent = new Intent();
        intent.setClass(this.l, ContactOrderbyActivity.class);
        startActivity(intent);
    }

    private void o() {
        if (this.i.isEmpty()) {
            nrh.b(this.l, R.string._2130841404_res_0x7f020f3c);
            return;
        }
        Intent intent = new Intent();
        intent.setClass(this.l, ContactDeleteActivity.class);
        startActivity(intent);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        if (intent == null) {
            ReleaseLogUtil.d("DEVMGR_ContactMainActivity", "onActivityResult data is null");
            return;
        }
        super.onActivityResult(i, i2, intent);
        ReleaseLogUtil.e("DEVMGR_ContactMainActivity", "onActivityResult requestCode:", Integer.valueOf(i), " resultCode:", Integer.valueOf(i2));
        if (i == 1 || i == 2) {
            if (!(intent instanceof List)) {
                LogUtil.h("ContactMainActivity", "onActivityResult SYSTEM_CONTACT_ACTIVITY data not instanceof List");
            }
            List list = intent.getSerializableExtra(JsUtil.ServiceType.DATA) instanceof List ? (List) intent.getSerializableExtra(JsUtil.ServiceType.DATA) : null;
            if (list == null || list.isEmpty()) {
                ReleaseLogUtil.d("DEVMGR_ContactMainActivity", "onActivityResult SYSTEM_CONTACT_ACTIVITY resultList is null or empty");
                sqo.b("ContactMainActivity onActivityResult: resultList is null or empty");
                return;
            } else {
                if (this.i != null) {
                    ReleaseLogUtil.e("DEVMGR_ContactMainActivity", "onActivityResult SYSTEM_CONTACT_ACTIVITY mContactTables not null");
                    this.i.addAll(list);
                    c();
                    return;
                }
                return;
            }
        }
        LogUtil.h("ContactMainActivity", "onActivityResult not support requestCode:", Integer.valueOf(i));
    }

    private void k() {
        HashMap hashMap = new HashMap(16);
        List<Contact> list = this.i;
        if (list != null) {
            hashMap.put("contact_num", Integer.valueOf(list.size()));
        } else {
            LogUtil.h("ContactMainActivity", "sendContactBiEvent mContactTables is null!");
        }
        ixx.d().d(this.l, AnalyticsValue.SETTING_CONTACT_1090033.value(), hashMap, 0);
    }

    public void c() {
        ReleaseLogUtil.e("DEVMGR_ContactMainActivity", "sendToDevice mContactTables size is ", Integer.valueOf(this.i.size()));
        if (this.k == null) {
            ReleaseLogUtil.d("DEVMGR_ContactMainActivity", "sendToDevice mDeviceSettingsInteractors is null");
            sqo.b("ContactMainActivity sendToDevice: mDeviceSettingsInteractors is null");
        } else {
            if (oae.c(this.l).d() != 2) {
                n();
            }
            this.k.a(this.l, this.i, new IBaseResponseCallback() { // from class: com.huawei.ui.device.activity.selectcontact.ContactMainActivity.8
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    if (!(obj instanceof Integer)) {
                        LogUtil.h("ContactMainActivity", "sendToDevice objectData not instanceof Integer");
                        return;
                    }
                    if (i == 0 && ((Integer) obj).intValue() == 100000) {
                        LogUtil.a("ContactMainActivity", "sendToDevice MESSAGE_ADD_SUCCESS_COMMAND");
                        ContactMainActivity.this.g.sendEmptyMessage(1);
                    } else {
                        LogUtil.a("ContactMainActivity", "sendToDevice MESSAGE_ADD_FAIL_COMMAND");
                        ContactMainActivity.this.g.sendEmptyMessage(2);
                    }
                }
            });
        }
    }

    private void n() {
        ReleaseLogUtil.e("DEVMGR_ContactMainActivity", "showNoConnectedToast()");
        nrh.b(this.l, R.string.IDS_device_not_connect);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        LogUtil.a("ContactMainActivity", "onResume");
        a(true);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        CommonUtil.a(this.l);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
