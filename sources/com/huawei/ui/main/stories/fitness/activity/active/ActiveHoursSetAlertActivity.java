package com.huawei.ui.main.stories.fitness.activity.active;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Message;
import android.view.MotionEvent;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import androidx.core.content.ContextCompat;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.switchbutton.HealthSwitchButton;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.main.R$string;
import defpackage.gnm;
import defpackage.njh;
import health.compact.a.UnitUtil;
import health.compact.a.util.LogUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;

/* loaded from: classes6.dex */
public class ActiveHoursSetAlertActivity extends BaseActivity {

    /* renamed from: a, reason: collision with root package name */
    private LinearLayout f9721a;
    private HealthTextView b;
    private HealthTextView d;
    private CustomTitleBar i;
    private HealthSwitchButton j;
    private boolean c = false;
    private c e = new c(this);

    public static void a(Context context) {
        gnm.aPB_(context, new Intent(context, (Class<?>) ActiveHoursSetAlertActivity.class));
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_active_hours_set_alert);
        c();
        a();
        d();
    }

    private void c() {
        this.i = (CustomTitleBar) findViewById(R.id.ctb_set_alert_title);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.layout_moving_remind);
        this.f9721a = linearLayout;
        this.b = (HealthTextView) linearLayout.findViewById(R.id.reminder_switch_title);
        this.d = (HealthTextView) this.f9721a.findViewById(R.id.reminder_switch_description);
        HealthSwitchButton healthSwitchButton = (HealthSwitchButton) this.f9721a.findViewById(R.id.reminder_switch_btn);
        this.j = healthSwitchButton;
        healthSwitchButton.setChecked(true);
    }

    private void a() {
        this.i.setTitleText(getString(R$string.IDS_temperature_warning_set));
        this.i.setTitleBarBackgroundColor(ContextCompat.getColor(this, R.color._2131296971_res_0x7f0902cb));
        this.b.setText(getString(R$string.IDS_settings_moving_remind));
        this.d.setText(getString(R$string.IDS_stood_up_remind_description, new Object[]{UnitUtil.e(1.0d, 1, 0)}));
        ArrayList arrayList = new ArrayList(1);
        arrayList.add("900200004");
        njh.c(arrayList, new d());
    }

    private void d() {
        this.j.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.huawei.ui.main.stories.fitness.activity.active.ActiveHoursSetAlertActivity$$ExternalSyntheticLambda0
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                ActiveHoursSetAlertActivity.this.dpu_(compoundButton, z);
            }
        });
    }

    /* synthetic */ void dpu_(CompoundButton compoundButton, boolean z) {
        if (!this.c) {
            LogUtil.c("SCUI_ActiveHoursSetAlertActivity", "onCheckedChangeListener, mIsClickScreen is false");
            ViewClickInstrumentation.clickOnView(compoundButton);
        } else {
            LogUtil.d("SCUI_ActiveHoursSetAlertActivity", "initListener mStandToggle isChecked is ", Boolean.valueOf(z));
            njh.b("900200004", z);
            ViewClickInstrumentation.clickOnView(compoundButton);
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            this.c = true;
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        KnitActiveHoursActivity.d("3");
        c cVar = this.e;
        if (cVar != null) {
            cVar.removeCallbacksAndMessages(null);
            this.e = null;
        }
    }

    static class c extends BaseHandler<ActiveHoursSetAlertActivity> {
        c(ActiveHoursSetAlertActivity activeHoursSetAlertActivity) {
            super(activeHoursSetAlertActivity);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: dpv_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(ActiveHoursSetAlertActivity activeHoursSetAlertActivity, Message message) {
            if (message.what == 101) {
                Object obj = message.obj;
                if (obj instanceof HashMap) {
                    activeHoursSetAlertActivity.a((HashMap<String, String>) obj);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(HashMap<String, String> hashMap) {
        if (hashMap == null) {
            com.huawei.hwlogsmodel.LogUtil.a("SCUI_ActiveHoursSetAlertActivity", "setSwitchButton, switchStatusMap is null");
            return;
        }
        com.huawei.hwlogsmodel.LogUtil.a("SCUI_ActiveHoursSetAlertActivity", "setSwitchButton, switchStatusMap is ", hashMap);
        if (hashMap.containsKey("900200004")) {
            this.j.setChecked("1".equals(hashMap.get("900200004")));
        }
    }

    static class d implements IBaseResponseCallback {
        private final WeakReference<ActiveHoursSetAlertActivity> d;

        private d(ActiveHoursSetAlertActivity activeHoursSetAlertActivity) {
            this.d = new WeakReference<>(activeHoursSetAlertActivity);
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            Object[] objArr = new Object[2];
            objArr[0] = "onResponse: ";
            objArr[1] = obj == null ? null : obj.toString();
            com.huawei.hwlogsmodel.LogUtil.a("SCUI_ActiveHoursSetAlertActivity", objArr);
            ActiveHoursSetAlertActivity activeHoursSetAlertActivity = this.d.get();
            if (activeHoursSetAlertActivity == null) {
                com.huawei.hwlogsmodel.LogUtil.h("SCUI_ActiveHoursSetAlertActivity", "onResponse: activity is null");
                return;
            }
            if (!(obj instanceof HashMap)) {
                com.huawei.hwlogsmodel.LogUtil.h("SCUI_ActiveHoursSetAlertActivity", "onResponse: objData is not instanceof HashMap");
                return;
            }
            Message obtain = Message.obtain();
            obtain.what = 101;
            obtain.obj = (HashMap) obj;
            activeHoursSetAlertActivity.e.sendMessage(obtain);
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
