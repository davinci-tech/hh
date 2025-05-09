package com.huawei.ui.main.stories.fitness.activity.active;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.adapter.BaseRecyclerAdapter;
import com.huawei.ui.commonui.adapter.RecyclerHolder;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.recycleview.HealthLinearLayoutManager;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.switchbutton.HealthSwitchButton;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.fitness.activity.active.ReminderSettingActivity;
import defpackage.njh;
import defpackage.nsf;
import defpackage.pxm;
import defpackage.pxp;
import defpackage.sdr;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes6.dex */
public class ReminderSettingActivity extends BaseActivity {
    private e c;
    private final List<b> b = new ArrayList();
    private a e = new a(this);

    public interface SwitchButtonListener {
        void onSwitchClick(b bVar);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_reminder_setting);
        a();
        e();
    }

    private void e() {
        ArrayList arrayList = new ArrayList(4);
        arrayList.add("900200004");
        arrayList.add("900200010");
        arrayList.add("900200011");
        arrayList.add("900200021");
        njh.c(arrayList, new d());
    }

    private void a() {
        HealthRecycleView healthRecycleView = (HealthRecycleView) findViewById(R.id.switch_recycle);
        e eVar = new e(new SwitchButtonListener() { // from class: phf
            @Override // com.huawei.ui.main.stories.fitness.activity.active.ReminderSettingActivity.SwitchButtonListener
            public final void onSwitchClick(ReminderSettingActivity.b bVar) {
                ReminderSettingActivity.this.e(bVar);
            }
        });
        this.c = eVar;
        healthRecycleView.setAdapter(eVar);
        healthRecycleView.setLayoutManager(new HealthLinearLayoutManager(this));
        this.b.add(new b("900200004"));
        if (!Utils.o() || sdr.a()) {
            this.b.add(new b("900200010"));
            this.b.add(new b("900200011"));
        }
        if (pxm.e()) {
            this.b.add(new b("900200021"));
        }
        this.c.d(this.b);
    }

    public /* synthetic */ void e(b bVar) {
        String c = bVar.c();
        boolean d2 = bVar.d();
        LogUtil.a("SCUI_ReminderSettingActivity", "RemindSwitchAdapter onClick", c, "statue ", Boolean.valueOf(d2));
        if ("900200021".equals(c) && !d2) {
            a(bVar);
        } else {
            b(c, d2);
        }
    }

    private void a(final b bVar) {
        CustomTextAlertDialog.Builder builder = new CustomTextAlertDialog.Builder(this);
        builder.b(R$string.IDS_active_task_dialog_close_title).d(R$string.IDS_active_task_dialog_close_text).cyU_(R$string.IDS_active_task_dialog_close_button, new View.OnClickListener() { // from class: phe
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ReminderSettingActivity.this.dpC_(view);
            }
        }).cyR_(R$string.IDS_settings_button_cancal, new View.OnClickListener() { // from class: com.huawei.ui.main.stories.fitness.activity.active.ReminderSettingActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                bVar.b(true);
                ReminderSettingActivity.this.c.d(ReminderSettingActivity.this.b);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        CustomTextAlertDialog a2 = builder.a();
        a2.setCancelable(false);
        a2.show();
    }

    public /* synthetic */ void dpC_(View view) {
        b("900200021", false);
        ViewClickInstrumentation.clickOnView(view);
    }

    private void b(String str, boolean z) {
        LogUtil.a("SCUI_ReminderSettingActivity", "changeSwitchAndSend, configId is ", str, ", switchStatus is ", Boolean.valueOf(z));
        njh.b(str, z);
    }

    static class a extends BaseHandler<ReminderSettingActivity> {
        a(ReminderSettingActivity reminderSettingActivity) {
            super(reminderSettingActivity);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: dpE_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(ReminderSettingActivity reminderSettingActivity, Message message) {
            if (message.what != 101) {
                ReleaseLogUtil.d("SCUI_ReminderSettingActivity", "message is ", Integer.valueOf(message.what));
                return;
            }
            Object obj = message.obj;
            if (obj instanceof HashMap) {
                HashMap hashMap = (HashMap) obj;
                for (b bVar : reminderSettingActivity.b) {
                    String str = (String) hashMap.get(bVar.c());
                    if (!TextUtils.isEmpty(str)) {
                        bVar.b("1".equals(str));
                    }
                }
                reminderSettingActivity.c.d(reminderSettingActivity.b);
            }
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        for (b bVar : this.b) {
            pxp.c(1, bVar.c(), bVar.d());
        }
        a aVar = this.e;
        if (aVar != null) {
            aVar.removeCallbacksAndMessages(null);
            this.e = null;
        }
    }

    static class d implements IBaseResponseCallback {
        private final WeakReference<ReminderSettingActivity> e;

        private d(ReminderSettingActivity reminderSettingActivity) {
            this.e = new WeakReference<>(reminderSettingActivity);
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            Object[] objArr = new Object[2];
            objArr[0] = "onResponse: ";
            objArr[1] = obj == null ? null : obj.toString();
            LogUtil.a("SCUI_ReminderSettingActivity", objArr);
            ReminderSettingActivity reminderSettingActivity = this.e.get();
            if (reminderSettingActivity == null) {
                LogUtil.h("SCUI_ReminderSettingActivity", "onResponse: activity is null");
                return;
            }
            if (!(obj instanceof HashMap)) {
                LogUtil.h("SCUI_ReminderSettingActivity", "onResponse: objData is not instanceof HashMap");
                return;
            }
            Message obtain = Message.obtain();
            obtain.what = 101;
            obtain.obj = (HashMap) obj;
            reminderSettingActivity.e.sendMessage(obtain);
        }
    }

    public static class b {
        private final String b;
        private boolean c = true;

        b(String str) {
            this.b = str;
        }

        public String c() {
            return this.b;
        }

        public boolean d() {
            return this.c;
        }

        public void b(boolean z) {
            this.c = z;
        }
    }

    public static class e extends BaseRecyclerAdapter<b> {
        private final SwitchButtonListener d;
        private List<b> e;

        e(SwitchButtonListener switchButtonListener) {
            super(new ArrayList(), R.layout.reminder_setting_item);
            this.e = new ArrayList();
            this.d = switchButtonListener;
        }

        @Override // com.huawei.ui.commonui.adapter.BaseRecyclerAdapter
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public void convert(RecyclerHolder recyclerHolder, int i, final b bVar) {
            if (recyclerHolder == null || bVar == null) {
                ReleaseLogUtil.d("SCUI_ReminderSettingActivity", "convert holder ", recyclerHolder, " itemData ", bVar, " position ", Integer.valueOf(i));
                return;
            }
            SwitchType switchType = SwitchType.getSwitchType(bVar.c());
            if (switchType == null) {
                ReleaseLogUtil.c("SCUI_ReminderSettingActivity", "type is null ");
                return;
            }
            if (i == this.e.size() - 1) {
                recyclerHolder.cwK_(R.id.switch_divider).setVisibility(8);
            } else {
                recyclerHolder.cwK_(R.id.switch_divider).setVisibility(0);
            }
            HealthTextView healthTextView = (HealthTextView) recyclerHolder.cwK_(R.id.reminder_switch_title);
            HealthTextView healthTextView2 = (HealthTextView) recyclerHolder.cwK_(R.id.reminder_switch_description);
            final HealthSwitchButton healthSwitchButton = (HealthSwitchButton) recyclerHolder.cwK_(R.id.reminder_switch_btn);
            healthTextView.setText(switchType.getTitle());
            healthTextView2.setText(switchType.getDescription());
            healthSwitchButton.setChecked(bVar.d());
            healthSwitchButton.setOnClickListener(new View.OnClickListener() { // from class: phg
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ReminderSettingActivity.e.this.dpD_(bVar, healthSwitchButton, view);
                }
            });
        }

        public /* synthetic */ void dpD_(b bVar, HealthSwitchButton healthSwitchButton, View view) {
            bVar.b(healthSwitchButton.isChecked());
            SwitchButtonListener switchButtonListener = this.d;
            if (switchButtonListener != null) {
                switchButtonListener.onSwitchClick(bVar);
            }
            ViewClickInstrumentation.clickOnView(view);
        }

        public void d(List<b> list) {
            this.e = list;
            refreshDataChange(list);
        }
    }

    enum SwitchType {
        MOTION_REMIND(R$string.IDS_settings_moving_remind, R$string.IDS_stood_up_remind_description),
        GUIDE_REMIND(R$string.IDS_progress_remind, R$string.IDS_progress_remind_description),
        ACHIEVE_REMIND(R$string.IDS_achieve_remind, R$string.IDS_achieve_remind_description),
        TASK_REMIND(R$string.IDS_active_task_switch, R$string.IDS_active_task_switch_description);

        private final int mDescriptionId;
        private final int mTitleId;

        SwitchType(int i, int i2) {
            this.mTitleId = i;
            this.mDescriptionId = i2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public String getTitle() {
            return nsf.h(this.mTitleId);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public String getDescription() {
            if (MOTION_REMIND.equals(this)) {
                return nsf.b(this.mDescriptionId, UnitUtil.e(1.0d, 1, 0));
            }
            return nsf.h(this.mDescriptionId);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        public static SwitchType getSwitchType(String str) {
            char c;
            str.hashCode();
            switch (str.hashCode()) {
                case -1062371205:
                    if (str.equals("900200004")) {
                        c = 0;
                        break;
                    }
                    c = 65535;
                    break;
                case -1062371178:
                    if (str.equals("900200010")) {
                        c = 1;
                        break;
                    }
                    c = 65535;
                    break;
                case -1062371177:
                    if (str.equals("900200011")) {
                        c = 2;
                        break;
                    }
                    c = 65535;
                    break;
                case -1062371146:
                    if (str.equals("900200021")) {
                        c = 3;
                        break;
                    }
                    c = 65535;
                    break;
                default:
                    c = 65535;
                    break;
            }
            if (c == 0) {
                return MOTION_REMIND;
            }
            if (c == 1) {
                return GUIDE_REMIND;
            }
            if (c == 2) {
                return ACHIEVE_REMIND;
            }
            if (c != 3) {
                return null;
            }
            return TASK_REMIND;
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
