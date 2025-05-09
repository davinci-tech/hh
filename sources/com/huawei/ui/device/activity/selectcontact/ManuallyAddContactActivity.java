package com.huawei.ui.device.activity.selectcontact;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.huawei.datatype.Contact;
import com.huawei.datatype.PhoneNumber;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.jsoperation.JsUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.device.views.selectcontact.ManuallyAddContactAdapter;
import defpackage.nsf;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes6.dex */
public class ManuallyAddContactActivity extends BaseActivity implements IUpdateListItemListener {

    /* renamed from: a, reason: collision with root package name */
    private CustomTitleBar f9218a;
    private ArrayList<Contact> b = new ArrayList<>(16);
    private int e;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_manually_add_contact);
        c();
        e();
        a();
    }

    private void c() {
        this.f9218a = (CustomTitleBar) findViewById(R.id.manually_add_contact_title);
        LogUtil.a("ManuallyAddContactActivity", "initTitleBar enter");
        this.f9218a.setRightButtonVisibility(0);
        this.f9218a.setRightButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.device.activity.selectcontact.ManuallyAddContactActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ArrayList arrayList = new ArrayList(16);
                Iterator it = ManuallyAddContactActivity.this.b.iterator();
                while (it.hasNext()) {
                    Contact contact = (Contact) it.next();
                    String name = contact.getName();
                    String e = ManuallyAddContactAdapter.e(contact);
                    if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(e)) {
                        arrayList.add(contact);
                    }
                }
                Intent intent = new Intent();
                intent.putExtra(JsUtil.ServiceType.DATA, arrayList);
                ManuallyAddContactActivity.this.setResult(-1, intent);
                ManuallyAddContactActivity.this.finish();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.f9218a.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.device.activity.selectcontact.ManuallyAddContactActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ManuallyAddContactActivity.this.finish();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        updateSaveButtonState(false);
    }

    private void e() {
        Intent intent = getIntent();
        if (intent != null) {
            this.e = intent.getIntExtra("com.huawei.community.action.MAX_SELECT_COUNT", 0);
        }
    }

    private void a() {
        Contact contact = new Contact();
        ArrayList arrayList = new ArrayList(16);
        arrayList.add(new PhoneNumber());
        contact.setPhoneNumbers(arrayList);
        this.b.add(contact);
        HealthRecycleView healthRecycleView = (HealthRecycleView) findViewById(R.id.manually_add_contact_recyclerView);
        ManuallyAddContactAdapter manuallyAddContactAdapter = new ManuallyAddContactAdapter(this, this.b, this, this.e);
        manuallyAddContactAdapter.setHasStableIds(true);
        healthRecycleView.setLayoutManager(new LinearLayoutManager(this));
        healthRecycleView.setAdapter(manuallyAddContactAdapter);
    }

    @Override // com.huawei.ui.device.activity.selectcontact.IUpdateListItemListener
    public void updateSaveButtonState(boolean z) {
        if (z) {
            this.f9218a.setRightButtonClickable(true);
            this.f9218a.setRightButtonDrawable(getResources().getDrawable(R.drawable._2131430292_res_0x7f0b0b94), nsf.h(R.string._2130841395_res_0x7f020f33));
        } else {
            this.f9218a.setRightButtonClickable(false);
            this.f9218a.setRightButtonDrawable(getResources().getDrawable(R.drawable._2131430219_res_0x7f0b0b4b), nsf.h(R.string._2130841395_res_0x7f020f33));
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
