package com.huawei.ui.device.activity.selectcontact;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.huawei.datatype.Contact;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.device.activity.selectcontact.ContactOrderbyActivity;
import com.huawei.ui.device.interactors.DeviceSettingsInteractors;
import com.huawei.ui.device.views.selectcontact.ContactItemDragAdapter;
import com.huawei.ui.device.views.selectcontact.ContactItemDragCallback;
import defpackage.nrh;
import defpackage.nsn;
import defpackage.nsy;
import defpackage.oae;
import defpackage.sqo;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.List;

/* loaded from: classes6.dex */
public class ContactOrderbyActivity extends BaseActivity {

    /* renamed from: a, reason: collision with root package name */
    private DeviceSettingsInteractors f9217a;
    private Context b;
    private CustomTitleBar d;
    private List<Contact> c = null;
    private Handler e = new Handler() { // from class: com.huawei.ui.device.activity.selectcontact.ContactOrderbyActivity.2
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            int i = message.what;
            if (i == 1) {
                ContactOrderbyActivity.this.g();
            } else if (i == 2) {
                ContactOrderbyActivity.this.d();
            } else {
                if (i != 3) {
                    return;
                }
                ContactOrderbyActivity.this.e();
            }
        }
    };

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        cancelAdaptRingRegion();
        setContentView(R.layout.activity_device_settings_contact_orderby_activity);
        b();
    }

    private void b() {
        Context applicationContext = getApplicationContext();
        this.b = applicationContext;
        DeviceSettingsInteractors d = DeviceSettingsInteractors.d(applicationContext);
        this.f9217a = d;
        List<Contact> a2 = d.a(this.b);
        this.c = a2;
        if (a2 == null || a2.size() == 0) {
            LogUtil.b("ContactOrderbyActivity", "get null DBdata, the activity will be shut down!");
            return;
        }
        LogUtil.a("ContactOrderbyActivity", "mContactTables size = " + this.c.size());
        Message obtainMessage = this.e.obtainMessage();
        obtainMessage.what = 3;
        this.e.sendMessage(obtainMessage);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        CustomTitleBar customTitleBar = (CustomTitleBar) nsy.cMc_(this, R.id.contact_orderby_titlebar);
        this.d = customTitleBar;
        customTitleBar.setRightButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.device.activity.selectcontact.ContactOrderbyActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ContactOrderbyActivity.this.d.setRightButtonClickable(false);
                ContactOrderbyActivity.this.c();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        a();
        f();
    }

    private void f() {
        LinearLayout linearLayout = (LinearLayout) nsy.cMc_(this, R.id.ll_contact_orderby_list);
        if (linearLayout == null) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = linearLayout.getLayoutParams();
        if (layoutParams instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
            if (((Integer) BaseActivity.getSafeRegionWidth().first).intValue() > 0 || ((Integer) BaseActivity.getSafeRegionWidth().second).intValue() > 0) {
                layoutParams2.setMarginStart(nsn.c(this.b, 24.0f) + ((Integer) BaseActivity.getSafeRegionWidth().first).intValue());
                layoutParams2.setMarginEnd(nsn.c(this.b, 24.0f) + ((Integer) BaseActivity.getSafeRegionWidth().second).intValue());
            } else {
                layoutParams2.setMarginStart(nsn.c(this.b, 24.0f));
                layoutParams2.setMarginEnd(nsn.c(this.b, 24.0f));
            }
            linearLayout.setLayoutParams(layoutParams2);
        }
    }

    private void a() {
        HealthRecycleView healthRecycleView = (HealthRecycleView) nsy.cMc_(this, R.id.contact_orderby_drag_list);
        final ContactItemDragAdapter contactItemDragAdapter = new ContactItemDragAdapter(this.b, this.c);
        healthRecycleView.setOnTouchListener(new View.OnTouchListener() { // from class: nxt
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return ContactOrderbyActivity.cSj_(ContactItemDragAdapter.this, view, motionEvent);
            }
        });
        healthRecycleView.setLayoutManager(new LinearLayoutManager(this.b));
        healthRecycleView.setAdapter(contactItemDragAdapter);
        healthRecycleView.a(false);
        healthRecycleView.d(false);
        new ItemTouchHelper(new ContactItemDragCallback(contactItemDragAdapter)).attachToRecyclerView(healthRecycleView);
    }

    public static /* synthetic */ boolean cSj_(ContactItemDragAdapter contactItemDragAdapter, View view, MotionEvent motionEvent) {
        if (motionEvent != null && motionEvent.getAction() == 1 && contactItemDragAdapter != null) {
            contactItemDragAdapter.notifyDataSetChanged();
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        ReleaseLogUtil.e("DEVMGR_ContactOrderbyActivity", "saveData() sort");
        if (this.f9217a == null) {
            ReleaseLogUtil.d("DEVMGR_ContactOrderbyActivity", "mDeviceSettingsInteractors of saveData error null!");
            sqo.b("ContactOrderbyActivity saveData: mDeviceSettingsInteractors is null");
        } else {
            if (oae.c(this.b).d() != 2) {
                nrh.b(this.b, R.string.IDS_device_not_connect);
            }
            this.f9217a.a(this.b, this.c, new IBaseResponseCallback() { // from class: com.huawei.ui.device.activity.selectcontact.ContactOrderbyActivity.4
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    if (i == 0 && ((Integer) obj).intValue() == 100000) {
                        LogUtil.a("ContactOrderbyActivity", "MESSAGE_ORDERBY_SUCCESS_COMMAND()!");
                        ContactOrderbyActivity.this.e.sendEmptyMessage(1);
                    } else {
                        LogUtil.b("ContactOrderbyActivity", "MESSAGE_ORDERBY_FAIL_COMMAND()!");
                        ContactOrderbyActivity.this.e.sendEmptyMessage(2);
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        this.d.setRightButtonClickable(true);
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        nrh.b(this.b, R.string._2130841508_res_0x7f020fa4);
        this.d.setRightButtonClickable(true);
        finish();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        Handler handler = this.e;
        if (handler != null) {
            handler.removeMessages(1);
            this.e.removeMessages(2);
            this.e = null;
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
