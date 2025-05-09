package com.huawei.health.suggestion.ui.fitness.activity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.text.TextUtils;
import android.view.View;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.device.api.IndoorEquipManagerApi;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.device.ui.dialog.CustomDeviceSelectDialog;
import com.huawei.health.device.ui.dialog.DeviceCheckAdapter;
import com.huawei.health.sport.model.WorkoutRecord;
import com.huawei.health.suggestion.ui.BaseActivity;
import com.huawei.health.suggestion.ui.fitness.activity.CourseEquipmentConnectActivity;
import com.huawei.health.suggestion.ui.fitness.helper.JumpConnectHelper;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.dialog.CustomAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.ceo;
import defpackage.cjv;
import defpackage.dib;
import defpackage.koq;
import defpackage.nsn;
import health.compact.a.Services;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

/* loaded from: classes4.dex */
public class CourseEquipmentConnectActivity extends BaseActivity implements View.OnClickListener, JumpConnectHelper.JumpActivityHandleInterface {

    /* renamed from: a, reason: collision with root package name */
    private ArrayList<ContentValues> f3077a;
    private HealthDevice.HealthDeviceKind b;
    private CustomDeviceSelectDialog c;
    private int d = 0;
    private HealthTextView e;
    private IndoorEquipManagerApi f;
    private int h;
    private HealthButton i;

    @Override // com.huawei.health.suggestion.ui.BaseActivity
    public void initViewController() {
    }

    @Override // com.huawei.health.suggestion.ui.BaseActivity
    public void initData() {
        Intent intent = getIntent();
        if (intent == null) {
            LogUtil.h("Suggestion_CourseEquipmentConnectActivity", "getIntent is null");
            return;
        }
        this.d = intent.getIntExtra("KEY_INTENT_EQUIPMENT_TYPE", 0);
        this.h = intent.getIntExtra("KEY_INTENT_COURSE_ENTRANCE", 0);
        JumpConnectHelper.c().b().b("Suggestion_CourseEquipmentConnectActivity", new WeakReference<>(this));
        this.f = (IndoorEquipManagerApi) Services.c("PluginLinkIndoorEquip", IndoorEquipManagerApi.class);
    }

    @Override // com.huawei.health.suggestion.ui.BaseActivity
    public void initLayout() {
        setContentView(R.layout.activity_fitness_device_connect);
        this.i = (HealthButton) findViewById(R.id.btn_start_connect_paired);
        findViewById(R.id.btn_start_connect_paired).setOnClickListener(this);
        findViewById(R.id.btn_start_connect_new).setOnClickListener(this);
        this.e = (HealthTextView) findViewById(R.id.connect_tips);
        if (nsn.ae(getApplicationContext())) {
            this.e.setText(getResources().getText(R.string._2130846068_res_0x7f022174));
        } else {
            this.e.setText(getResources().getText(R.string.IDS_course_device_connect_content_tip));
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        b();
    }

    private void b() {
        int i = this.d;
        if (i == 1) {
            this.f3077a = ceo.d().d(HealthDevice.HealthDeviceKind.HDK_TREADMILL);
            this.b = HealthDevice.HealthDeviceKind.HDK_TREADMILL;
        } else if (i == 2) {
            this.f3077a = ceo.d().d(HealthDevice.HealthDeviceKind.HDK_EXERCISE_BIKE);
            this.b = HealthDevice.HealthDeviceKind.HDK_EXERCISE_BIKE;
        } else {
            this.b = null;
            LogUtil.h("Suggestion_CourseEquipmentConnectActivity", "getBondedDevices() courseEquipmentTyp:", Integer.valueOf(i));
        }
        if (koq.c(this.f3077a)) {
            this.i.setVisibility(0);
        } else {
            this.i.setVisibility(8);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (nsn.a(500)) {
            LogUtil.h("Suggestion_CourseEquipmentConnectActivity", "view click too fast.");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        if (view.getId() == R.id.btn_start_connect_paired) {
            if (this.f3077a.size() == 1) {
                ayX_(this.f3077a.get(0));
            } else {
                d();
            }
        } else if (view.getId() == R.id.btn_start_connect_new) {
            JumpConnectHelper.c().j();
        } else {
            LogUtil.a("Suggestion_CourseEquipmentConnectActivity", "click button");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void ayX_(ContentValues contentValues) {
        if (contentValues == null) {
            ReleaseLogUtil.c("Suggestion_CourseEquipmentConnectActivity", "startIndoorEquipment bondedDevice null.");
            return;
        }
        Intent intent = new Intent();
        intent.setClassName(this, "com.huawei.indoorequip.activity.IndoorEquipConnectedActivity");
        intent.putExtra("PAYLOAD_FROM_NFC", dib.c().UU_(contentValues));
        intent.putExtra("KEY_INTENT_COURSE_ENTRANCE", this.h);
        intent.putExtra("KEY_INTENT_EQUIPMENT_TYPE", dib.c().a(this.d));
        String asString = contentValues.getAsString("uniqueId");
        if (this.f.isDeviceBtConnected() && !TextUtils.isEmpty(asString) && asString.equals(this.f.getCurrentMacAddress())) {
            intent.putExtra(WorkoutRecord.Extend.COURSE_TARGET_TYPE, 6);
        }
        startActivity(intent);
    }

    private void d(int i) {
        String str;
        if (i == 1) {
            str = getApplicationContext().getResources().getString(R.string._2130845053_res_0x7f021d7d);
        } else if (i == 2) {
            str = getApplicationContext().getResources().getString(R.string._2130845054_res_0x7f021d7e);
        } else {
            LogUtil.h("Suggestion_CourseEquipmentConnectActivity", "Course equipmentType", Integer.valueOf(i));
            str = "";
        }
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("Suggestion_CourseEquipmentConnectActivity", "showTipDialog content is null");
            return;
        }
        CustomAlertDialog c = new CustomAlertDialog.Builder(this).e(R.string.IDS_course_device_nonmatch_dialog_title).c(str).cyn_(R.string.IDS_course_device_nonmatch_dialog_ok, new DialogInterface.OnClickListener() { // from class: com.huawei.health.suggestion.ui.fitness.activity.CourseEquipmentConnectActivity.5
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i2) {
                CourseEquipmentConnectActivity.this.finish();
                ViewClickInstrumentation.clickOnDialog(dialogInterface, i2);
            }
        }).c();
        c.setCancelable(false);
        c.show();
    }

    @Override // com.huawei.health.suggestion.ui.fitness.helper.JumpConnectHelper.JumpActivityHandleInterface
    public void showEquipmentMatchTip(int i) {
        if (i != 0) {
            d(i);
        }
    }

    @Override // com.huawei.health.suggestion.ui.fitness.helper.JumpConnectHelper.JumpActivityHandleInterface
    public void startLongCoachAfter() {
        finish();
    }

    private void d() {
        if (this.b == null) {
            LogUtil.h("Suggestion_CourseEquipmentConnectActivity", "showDeviceSelectDialog mCurrentDeviceKind is null");
            return;
        }
        CustomDeviceSelectDialog e = new CustomDeviceSelectDialog.Builder(this).b(R.string.IDS_plugin_device_select).e(this.b).d(R.string._2130842267_res_0x7f02129b, new DeviceCheckAdapter.OnItemClickListener() { // from class: fkm
            @Override // com.huawei.health.device.ui.dialog.DeviceCheckAdapter.OnItemClickListener
            public final void onItemClick(cjv cjvVar) {
                CourseEquipmentConnectActivity.this.e(cjvVar);
            }
        }).Hx_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.fitness.activity.CourseEquipmentConnectActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (CourseEquipmentConnectActivity.this.c != null) {
                    CourseEquipmentConnectActivity.this.c.dismiss();
                    CourseEquipmentConnectActivity.this.c = null;
                } else {
                    LogUtil.h("Suggestion_CourseEquipmentConnectActivity", "showDeviceSelectDialog setNegativeButton mUnbindDialog is null");
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        }).e();
        this.c = e;
        e.setCancelable(false);
        this.c.show();
    }

    public /* synthetic */ void e(cjv cjvVar) {
        CustomDeviceSelectDialog customDeviceSelectDialog = this.c;
        if (customDeviceSelectDialog != null) {
            customDeviceSelectDialog.dismiss();
            this.c = null;
            ayX_(cjvVar.FT_());
            return;
        }
        LogUtil.h("Suggestion_CourseEquipmentConnectActivity", "showDeviceSelectDialog setPositiveButton mUnbindDialog is null");
    }

    private void c() {
        LogUtil.a("Suggestion_CourseEquipmentConnectActivity", "release()");
        JumpConnectHelper.c().b().b("Suggestion_CourseEquipmentConnectActivity");
        JumpConnectHelper.c().i();
    }

    @Override // android.app.Activity
    public void finish() {
        LogUtil.a("Suggestion_CourseEquipmentConnectActivity", "finish()");
        super.finish();
        c();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
