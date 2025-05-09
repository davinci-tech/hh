package com.huawei.ui.device.activity.selectcontact;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import androidx.core.content.ContextCompat;
import com.huawei.datatype.Contact;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.Constants;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.commonui.toolbar.HealthToolBar;
import com.huawei.ui.device.interactors.DeviceSettingsInteractors;
import com.huawei.ui.device.views.selectcontact.ContactDeleteListAdapter;
import defpackage.nrh;
import defpackage.nsy;
import defpackage.oae;
import defpackage.sqo;
import health.compact.a.UnitUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Locale;

/* loaded from: classes6.dex */
public class ContactDeleteActivity extends BaseActivity implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    ContactDeleteListAdapter f9212a;
    DeviceSettingsInteractors b;
    private LinearLayout c;
    NoTitleCustomAlertDialog d;
    List<Contact> e;
    private HealthTextView f;
    private ImageView g;
    private HealthTextView h;
    private LinearLayout i;
    private ImageView j;
    private Context k;
    private CustomTitleBar l;
    private int n;
    private ListView p;
    private HealthToolBar q;
    private long m = 0;
    private Handler o = new d(this);

    static /* synthetic */ int e(ContactDeleteActivity contactDeleteActivity) {
        int i = contactDeleteActivity.n;
        contactDeleteActivity.n = i + 1;
        return i;
    }

    static /* synthetic */ int h(ContactDeleteActivity contactDeleteActivity) {
        int i = contactDeleteActivity.n;
        contactDeleteActivity.n = i - 1;
        return i;
    }

    class d extends Handler {
        WeakReference<ContactDeleteActivity> c;

        d(ContactDeleteActivity contactDeleteActivity) {
            this.c = new WeakReference<>(contactDeleteActivity);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (this.c.get() == null) {
                return;
            }
            LogUtil.a("ContactDeleteActivity", "Enter handleMessage():" + message.what);
            int i = message.what;
            if (i == 1) {
                ContactDeleteActivity.this.c();
                return;
            }
            if (i == 2) {
                ContactDeleteActivity.this.a();
            } else {
                if (i == 3) {
                    ContactDeleteActivity.this.d();
                    return;
                }
                LogUtil.a("ContactDeleteActivity", "do not support message:" + message.what);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        LogUtil.a("ContactDeleteActivity", "enter handleSetSuccess");
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        LogUtil.a("ContactDeleteActivity", "enter handleSetFail");
        nrh.b(this.k, R.string._2130841508_res_0x7f020fa4);
        finish();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_device_settings_contact_delete_activity_black);
        b();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        Handler handler = this.o;
        if (handler != null) {
            handler.removeMessages(1);
            this.o.removeMessages(2);
            this.o = null;
        }
    }

    private void b() {
        Context applicationContext = getApplicationContext();
        this.k = applicationContext;
        this.n = 0;
        DeviceSettingsInteractors d2 = DeviceSettingsInteractors.d(applicationContext);
        this.b = d2;
        List<Contact> a2 = d2.a(this.k);
        this.e = a2;
        if (a2 == null || a2.size() == 0) {
            LogUtil.b("ContactDeleteActivity", "error ContactDB, get null DB, the activity will be finished!");
            return;
        }
        LogUtil.a("ContactDeleteActivity", "mContactTables size = " + this.e.size());
        Message obtainMessage = this.o.obtainMessage();
        obtainMessage.what = 3;
        this.o.sendMessage(obtainMessage);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        this.q = (HealthToolBar) findViewById(R.id.buttomview);
        View inflate = View.inflate(BaseApplication.getContext(), R.layout.activity_device_settings_contact_delete_activity_black_bottomview, null);
        this.q.cHd_(inflate);
        this.q.cHf_(this);
        LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.contact_delete_bottom_delete_layout);
        this.c = linearLayout;
        linearLayout.setOnClickListener(this);
        this.g = (ImageView) inflate.findViewById(R.id.contact_remove_img);
        this.f = (HealthTextView) inflate.findViewById(R.id.contact_remove_txt);
        LinearLayout linearLayout2 = (LinearLayout) inflate.findViewById(R.id.contact_delete_bottom_seleteall_layout);
        this.i = linearLayout2;
        linearLayout2.setOnClickListener(this);
        this.j = (ImageView) inflate.findViewById(R.id.contact_delete_bottom_seleteall_img);
        this.h = (HealthTextView) inflate.findViewById(R.id.contact_delete_bottom_seleteall_textview);
        this.l = (CustomTitleBar) nsy.cMc_(this, R.id.contact_delete_titlebar);
        e(0);
        ListView listView = (ListView) nsy.cMc_(this, R.id.contact_delete_listview);
        this.p = listView;
        listView.setSelector(R.drawable.device_settings_contact_listview_item_selector_black);
        ContactDeleteListAdapter contactDeleteListAdapter = new ContactDeleteListAdapter(this.k, this.e);
        this.f9212a = contactDeleteListAdapter;
        this.p.setAdapter((ListAdapter) contactDeleteListAdapter);
        this.p.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.huawei.ui.device.activity.selectcontact.ContactDeleteActivity.5
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                ContactDeleteListAdapter.d dVar = view.getTag() instanceof ContactDeleteListAdapter.d ? (ContactDeleteListAdapter.d) view.getTag() : null;
                if (dVar == null) {
                    LogUtil.b("ContactDeleteActivity", "holder == null");
                    ViewClickInstrumentation.clickOnListView(adapterView, view, i);
                    return;
                }
                LogUtil.a("ContactDeleteActivity", "ContactDeleteListAdapter ViewHolder = " + dVar);
                dVar.b.toggle();
                ContactDeleteListAdapter.b().put(Integer.valueOf(i), Boolean.valueOf(dVar.b.isChecked()));
                LogUtil.a("ContactDeleteActivity", "position = ", Integer.valueOf(i), "checkBox.isChecked() = ", Boolean.valueOf(dVar.b.isChecked()));
                LogUtil.a("ContactDeleteActivity", "ContactDeleteListAdapter map = " + ContactDeleteListAdapter.b());
                if (dVar.b.isChecked()) {
                    ContactDeleteActivity.e(ContactDeleteActivity.this);
                } else {
                    ContactDeleteActivity.h(ContactDeleteActivity.this);
                }
                LogUtil.a("ContactDeleteActivity", "mCheckNum = " + ContactDeleteActivity.this.n);
                ContactDeleteActivity contactDeleteActivity = ContactDeleteActivity.this;
                contactDeleteActivity.e(contactDeleteActivity.n);
                if (ContactDeleteActivity.this.n == ContactDeleteActivity.this.e.size()) {
                    ContactDeleteActivity.this.h();
                } else {
                    ContactDeleteActivity.this.o();
                }
                ViewClickInstrumentation.clickOnListView(adapterView, view, i);
            }
        });
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.contact_delete_bottom_delete_layout) {
            LogUtil.a("ContactDeleteActivity", "contact_delete_bottom_delete_layout");
            if (i()) {
                ViewClickInstrumentation.clickOnView(view);
                return;
            } else {
                if (this.n == 0) {
                    LogUtil.a("ContactDeleteActivity", "onClick() contact_delete_bottom_delete_layout if (mCheckNum == 0)");
                    ViewClickInstrumentation.clickOnView(view);
                    return;
                }
                k();
            }
        } else if (id == R.id.contact_delete_bottom_seleteall_layout) {
            LogUtil.a("ContactDeleteActivity", "contact_delete_bottom_seleteall_layout");
            e();
        } else {
            LogUtil.a("ContactDeleteActivity", "i = " + id);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i) {
        if (i != 0) {
            this.l.setTitleText(getResources().getQuantityString(R.plurals._2130903044_res_0x7f030004, 1, Integer.valueOf(i)));
            this.g.setImageResource(R.drawable._2131429887_res_0x7f0b09ff);
            this.f.setTextColor(ContextCompat.getColorStateList(this.k, R.color._2131296804_res_0x7f090224));
        } else {
            this.l.setTitleText(getString(R.string._2130837614_res_0x7f02006e));
            this.g.setImageResource(R.drawable._2131429888_res_0x7f0b0a00);
            this.f.setTextColor(ContextCompat.getColor(this.k, R.color._2131299244_res_0x7f090bac));
        }
    }

    private void e() {
        LogUtil.a("ContactDeleteActivity", "handleSelectAll mCheckNum=" + this.n);
        if (this.n == this.e.size()) {
            f();
        } else {
            j();
        }
        e(this.n);
    }

    private void f() {
        for (int i = 0; i < this.e.size(); i++) {
            ContactDeleteListAdapter.b().put(Integer.valueOf(i), false);
        }
        this.n = 0;
        this.f9212a.notifyDataSetChanged();
        o();
    }

    private void j() {
        for (int i = 0; i < this.e.size(); i++) {
            ContactDeleteListAdapter.b().put(Integer.valueOf(i), true);
        }
        this.n = this.e.size();
        this.f9212a.notifyDataSetChanged();
        h();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o() {
        this.h.setText(R.string._2130841399_res_0x7f020f37);
        this.j.setImageDrawable(getResources().getDrawable(R.drawable._2131427833_res_0x7f0b01f9));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        this.h.setText(R.string._2130841400_res_0x7f020f38);
        this.j.setImageDrawable(getResources().getDrawable(R.drawable._2131427805_res_0x7f0b01dd));
    }

    private void k() {
        Resources resources = getResources();
        int i = this.n;
        NoTitleCustomAlertDialog e = new NoTitleCustomAlertDialog.Builder(this).e(resources.getQuantityString(R.plurals._2130903207_res_0x7f0300a7, i, UnitUtil.e(i, 1, 0))).czz_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: com.huawei.ui.device.activity.selectcontact.ContactDeleteActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        }).czD_(getResources().getString(R.string._2130842177_res_0x7f021241).toUpperCase(Locale.ENGLISH), R.color._2131296949_res_0x7f0902b5, new View.OnClickListener() { // from class: com.huawei.ui.device.activity.selectcontact.ContactDeleteActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ContactDeleteActivity.this.g();
                ViewClickInstrumentation.clickOnView(view);
            }
        }).e();
        this.d = e;
        e.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        int size = this.e.size();
        ReleaseLogUtil.e("DEVMGR_ContactDeleteActivity", "saveData() delete data before mContactTables size :", Integer.valueOf(size));
        LogUtil.a("ContactDeleteActivity", "map=" + ContactDeleteListAdapter.b());
        while (true) {
            size--;
            if (size < 0) {
                break;
            }
            LogUtil.a("ContactDeleteActivity", "mContactTables i=" + size);
            if (ContactDeleteListAdapter.b().get(Integer.valueOf(size)).booleanValue()) {
                LogUtil.a("ContactDeleteActivity", "getIsSelected i=" + size + " is selected!");
                this.e.remove(size);
            }
        }
        this.f9212a.notifyDataSetChanged();
        Object[] objArr = new Object[2];
        objArr[0] = "saveData() delete data after mContactTables ";
        List<Contact> list = this.e;
        objArr[1] = list == null ? Constants.NULL : Integer.valueOf(list.size());
        ReleaseLogUtil.e("DEVMGR_ContactDeleteActivity", objArr);
        if (this.b == null) {
            ReleaseLogUtil.d("DEVMGR_ContactDeleteActivity", "mDeviceSettingsInteractors of saveData error null!");
            sqo.b("ContactDeleteActivity saveData: mDeviceSettingsInteractors is null");
        } else {
            if (oae.c(this.k).d() != 2) {
                nrh.b(this.k, R.string.IDS_device_not_connect);
            }
            this.b.a(this.k, this.e, new IBaseResponseCallback() { // from class: com.huawei.ui.device.activity.selectcontact.ContactDeleteActivity.4
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    if (i == 0 && ((Integer) obj).intValue() == 100000) {
                        LogUtil.a("ContactDeleteActivity", "MESSAGE_DELETE_SUCCESS_COMMAND()!");
                        ContactDeleteActivity.this.o.sendEmptyMessage(1);
                    } else {
                        LogUtil.b("ContactDeleteActivity", "MESSAGE_DELETE_FAIL_COMMAND()!");
                        ContactDeleteActivity.this.o.sendEmptyMessage(2);
                    }
                }
            });
        }
    }

    private boolean i() {
        long currentTimeMillis = System.currentTimeMillis();
        if (1000 > currentTimeMillis - this.m) {
            LogUtil.a("ContactDeleteActivity", "onClick", "click too much");
            this.m = currentTimeMillis;
            return true;
        }
        this.m = currentTimeMillis;
        return false;
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
