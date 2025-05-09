package com.huawei.health.device.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.device.fatscale.multiusers.MultiUsersManager;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.device.open.data.model.HealthData;
import com.huawei.health.device.ui.measure.adapter.WeightOfflineDataAdapter;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiSyncOption;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiCommonListener;
import com.huawei.hihealth.data.type.HiSyncType;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.cfi;
import defpackage.cgk;
import defpackage.cjx;
import defpackage.ckc;
import defpackage.ckm;
import defpackage.cpp;
import defpackage.dfd;
import defpackage.dij;
import defpackage.ixx;
import defpackage.koq;
import health.compact.a.Utils;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes3.dex */
public class WeightOfflineDataSelectActivity extends Activity implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private dfd f2232a;
    private HealthButton b;
    private WeightOfflineDataAdapter c;
    private String d;
    private Context e;
    private HealthDevice g;
    private RelativeLayout h;
    private String i;
    private HealthButton j;
    private HealthTextView k;
    private ListView m;
    private ckc n;
    private HealthTextView o;
    private ArrayList<ckm> l = new ArrayList<>(10);
    private Handler f = new Handler() { // from class: com.huawei.health.device.ui.WeightOfflineDataSelectActivity.2
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            int i = message.what;
            if (i == -1) {
                WeightOfflineDataSelectActivity weightOfflineDataSelectActivity = WeightOfflineDataSelectActivity.this;
                weightOfflineDataSelectActivity.a(weightOfflineDataSelectActivity.j);
            } else {
                if (i != 0) {
                    return;
                }
                WeightOfflineDataSelectActivity.this.finish();
            }
        }
    };

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        LogUtil.a("WeightOfflineDataSelectActivity", "WeightOfflineDataSelectActivity onCreate ...");
        setContentView(R.layout.activity_layout_weight_offline_data_sync);
        this.e = this;
        this.n = ckc.a(this);
        this.d = MultiUsersManager.INSTANCE.getCurrentUser() == null ? null : MultiUsersManager.INSTANCE.getCurrentUser().i();
        setFinishOnTouchOutside(false);
        Intent intent = getIntent();
        if (intent != null) {
            try {
                this.i = intent.getStringExtra("productId");
            } catch (Exception unused) {
                LogUtil.a("WeightOfflineDataSelectActivity", "WeightOfflineDataSelectActivity onCreate Exception");
            }
            if (TextUtils.isEmpty(this.i)) {
                LogUtil.a("WeightOfflineDataSelectActivity", "WeightOfflineDataSelectActivity onCreate mProductId is null");
                finish();
                return;
            } else {
                LogUtil.a("WeightOfflineDataSelectActivity", "WeightOfflineDataSelectActivity mProductId is::" + this.i);
                c();
                return;
            }
        }
        LogUtil.a("WeightOfflineDataSelectActivity", "WeightOfflineDataSelectActivity onCreate intent is null");
        finish();
    }

    private void c() {
        d();
        if (this.l.size() <= 0) {
            LogUtil.h("WeightOfflineDataSelectActivity", "WeightOfflineDataSelectActivity no suspected data");
            finish();
        }
        a();
        e();
        b();
    }

    private void a() {
        this.h = (RelativeLayout) findViewById(R.id.weight_offline_data_layout);
        this.k = (HealthTextView) findViewById(R.id.weight_offline_data_current_user_tv);
        this.m = (ListView) findViewById(R.id.weight_offline_data_list);
        this.o = (HealthTextView) findViewById(R.id.weight_offline_sync_title_type);
        this.b = (HealthButton) findViewById(R.id.weight_offline_data_cancle);
        this.j = (HealthButton) findViewById(R.id.weight_offline_data_save);
        if (this.i != null) {
            this.g = cjx.e().a(this.i);
        }
        if (this.g != null) {
            this.f2232a = new dfd(10006);
        }
    }

    private void d() {
        this.l.clear();
        if (!Utils.i()) {
            this.d = cgk.d().c();
        }
        Iterator<ckm> it = this.n.b(this.d).iterator();
        while (it.hasNext()) {
            ckm next = it.next();
            if (next != null && next.q()) {
                this.l.add(next);
            }
        }
        LogUtil.a("WeightOfflineDataSelectActivity", "weight offline data size is : ", Integer.valueOf(this.l.size()));
    }

    private void e() {
        this.j.setTextColor(this.e.getResources().getColor(R.color._2131296874_res_0x7f09026a));
        cfi currentUser = MultiUsersManager.INSTANCE.getCurrentUser();
        if (currentUser == null) {
            e("current user is null");
            return;
        }
        if (TextUtils.isEmpty(currentUser.h())) {
            List<cfi> allUser = MultiUsersManager.INSTANCE.getAllUser();
            if (allUser.isEmpty()) {
                e("no cache user info");
                return;
            }
            String i = currentUser.i();
            if (TextUtils.isEmpty(i)) {
                e("current user tag is empty.");
                return;
            }
            for (cfi cfiVar : allUser) {
                if (i.equals(cfiVar.i()) && !TextUtils.isEmpty(cfiVar.h())) {
                    currentUser.b(cfiVar.h());
                    a(currentUser);
                    return;
                }
            }
            e("can not find current name from cache");
            return;
        }
        a(currentUser);
    }

    private void e(String str) {
        LogUtil.a("WeightOfflineDataSelectActivity", "current user is lose info, change user : ", str);
        cfi mainUser = MultiUsersManager.INSTANCE.getMainUser();
        if (!c(mainUser)) {
            LogUtil.a("WeightOfflineDataSelectActivity", "current and main user is empty, please check code.");
            finish();
        } else {
            MultiUsersManager.INSTANCE.setCurrentUser(mainUser);
            a(mainUser);
        }
    }

    private boolean c(cfi cfiVar) {
        return cfiVar != null && (!TextUtils.isEmpty(cfiVar.h()) || Utils.l());
    }

    private void a(cfi cfiVar) {
        if (!c(cfiVar)) {
            LogUtil.a("WeightOfflineDataSelectActivity", "can not get current user, please check getCurrentUser method.");
            finish();
            return;
        }
        this.k.setText(this.e.getResources().getString(R.string.IDS_device_current_user, cfiVar.h()));
        LogUtil.a("WeightOfflineDataSelectActivity", "user num is :" + MultiUsersManager.INSTANCE.getAllUser().size());
        if (MultiUsersManager.INSTANCE.getAllUser().size() == 1) {
            this.k.setVisibility(8);
            this.o.setText(this.e.getResources().getString(R.string.IDS_device_select_data_keep));
        }
        WeightOfflineDataAdapter weightOfflineDataAdapter = new WeightOfflineDataAdapter(this.e, this.l, this.f);
        this.c = weightOfflineDataAdapter;
        weightOfflineDataAdapter.e(this.l);
        this.m.setAdapter((ListAdapter) this.c);
        this.j.setEnabled(false);
        int Va_ = dij.Va_(this.m);
        ViewGroup.LayoutParams layoutParams = this.m.getLayoutParams();
        LogUtil.a("WeightOfflineDataSelectActivity", "listViewHeight ===" + Va_);
        if (Va_ < 650) {
            layoutParams.height = Va_;
        } else {
            layoutParams.height = 650;
        }
        this.m.setLayoutParams(layoutParams);
    }

    private void b() {
        this.j.setOnClickListener(this);
        this.b.setOnClickListener(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(HealthButton healthButton) {
        WeightOfflineDataAdapter weightOfflineDataAdapter = this.c;
        if (weightOfflineDataAdapter != null && weightOfflineDataAdapter.c() > 0) {
            healthButton.setEnabled(true);
            healthButton.setTextColor(this.e.getResources().getColor(R.color._2131296927_res_0x7f09029f));
        } else {
            healthButton.setEnabled(false);
            healthButton.setTextColor(this.e.getResources().getColor(R.color._2131296874_res_0x7f09026a));
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == null) {
            LogUtil.a("WeightOfflineDataSelectActivity", "onClick view is null");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        if (view.getId() == R.id.weight_offline_data_cancle) {
            if (this.h.getVisibility() == 0) {
                this.h.setVisibility(4);
            }
            i();
        } else if (view.getId() == R.id.weight_offline_data_save) {
            b(this.c.d());
            this.f.sendEmptyMessage(0);
        } else {
            LogUtil.a("WeightOfflineDataSelectActivity", "Unknown click event.");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void i() {
        CustomTextAlertDialog.Builder builder = new CustomTextAlertDialog.Builder(this.e);
        builder.b(this.e.getString(R.string.IDS_device_bluetooth_open)).e(this.e.getString(R.string.IDS_device_confirm_once_again_before_abandon_claim_data_tip)).cyV_(this.e.getString(R.string.IDS_device_ui_dialog_yes), new View.OnClickListener() { // from class: com.huawei.health.device.ui.WeightOfflineDataSelectActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (!Utils.i()) {
                    WeightOfflineDataSelectActivity.this.b(cgk.d().c(), WeightOfflineDataSelectActivity.this.l);
                } else {
                    WeightOfflineDataSelectActivity.this.b(MultiUsersManager.INSTANCE.getCurrentUser().i(), WeightOfflineDataSelectActivity.this.l);
                }
                WeightOfflineDataSelectActivity.this.f.sendEmptyMessage(0);
                ViewClickInstrumentation.clickOnView(view);
            }
        }).cyS_(this.e.getString(R.string.IDS_device_ui_dialog_no), new View.OnClickListener() { // from class: com.huawei.health.device.ui.WeightOfflineDataSelectActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("WeightOfflineDataSelectActivity", "onClick cancle.");
                if (WeightOfflineDataSelectActivity.this.h.getVisibility() != 0) {
                    WeightOfflineDataSelectActivity.this.h.setVisibility(0);
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        CustomTextAlertDialog a2 = builder.a();
        a2.show();
        a2.setCancelable(false);
        a2.setCanceledOnTouchOutside(false);
    }

    private void b(ArrayList<Boolean> arrayList) {
        dfd dfdVar;
        ckm ckmVar;
        ArrayList arrayList2 = new ArrayList(10);
        int i = 0;
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            if (arrayList.get(i2).booleanValue() && koq.d(this.l, i2) && (ckmVar = this.l.get(i2)) != null) {
                arrayList2.add(c(ckmVar));
                i++;
            }
        }
        Collections.sort(arrayList2, new Comparator<HealthData>() { // from class: com.huawei.health.device.ui.WeightOfflineDataSelectActivity.3
            @Override // java.util.Comparator
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public int compare(HealthData healthData, HealthData healthData2) {
                long startTime = healthData.getStartTime() - healthData.getStartTime();
                if (startTime > 0) {
                    return -1;
                }
                return startTime < 0 ? 1 : 0;
            }
        });
        LogUtil.a("WeightOfflineDataSelectActivity", "WeightOfflineDataSelectActivity saveData num :", Integer.valueOf(i));
        HealthDevice healthDevice = this.g;
        if (healthDevice != null && (dfdVar = this.f2232a) != null) {
            dfdVar.onDataChanged(healthDevice, arrayList2);
        } else {
            LogUtil.h("WeightOfflineDataSelectActivity", "WeightOfflineDataSelectActivity saveData device or dataHandler is null! ");
        }
        h();
        if (!Utils.i()) {
            b(cgk.d().c(), this.l);
        } else {
            b(MultiUsersManager.INSTANCE.getCurrentUser().i(), this.l);
        }
        d(i, this.l);
    }

    private void d(int i, ArrayList<ckm> arrayList) {
        if (arrayList.size() == 0) {
            LogUtil.h("WeightOfflineDataSelectActivity", "suspectedWeightData is empty");
            return;
        }
        double doubleValue = new BigDecimal(i / arrayList.size()).setScale(2, RoundingMode.DOWN).doubleValue();
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", "1");
        hashMap.put("saveData", Double.valueOf(doubleValue));
        hashMap.put("cancleData", Double.valueOf(1.0d - doubleValue));
        ixx.d().d(this.e, AnalyticsValue.HEALTH_PLUGIN_DEVICE_WEIGHT_BODYFATSCALE_OFFLINE_CLAIM_DATA_2060030.value(), hashMap, 0);
    }

    private ckm c(ckm ckmVar) {
        if (ckmVar.getStartTime() <= 0) {
            LogUtil.h("WeightOfflineDataSelectActivity", "WeightOfflineDataSelectActivity convertTimeStamp time zero");
            return ckmVar;
        }
        ckm mo76clone = ckmVar.mo76clone();
        LogUtil.c("WeightOfflineDataSelectActivity", "convertTimeStamp before :", Long.valueOf(ckmVar.getStartTime()));
        long startTime = ((ckmVar.getStartTime() / 1000) * 1000) + Calendar.getInstance().get(14);
        mo76clone.setStartTime(startTime);
        mo76clone.setEndTime(startTime);
        LogUtil.c("WeightOfflineDataSelectActivity", "convertTimeStamp success :", Long.valueOf(mo76clone.getStartTime()));
        return mo76clone;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(String str, ArrayList<ckm> arrayList) {
        if (arrayList == null || str == null) {
            return;
        }
        Iterator<ckm> it = arrayList.iterator();
        while (it.hasNext()) {
            this.n.d(str, it.next().getStartTime());
        }
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            moveTaskToBack(false);
            return false;
        }
        return super.onKeyDown(i, keyEvent);
    }

    private void h() {
        HiSyncOption hiSyncOption = new HiSyncOption();
        hiSyncOption.setSyncModel(2);
        hiSyncOption.setSyncAction(0);
        hiSyncOption.setSyncDataType(HiSyncType.HiSyncDataType.c);
        hiSyncOption.setSyncScope(1);
        hiSyncOption.setSyncMethod(2);
        HiHealthNativeApi.a(cpp.a()).synCloud(hiSyncOption, new HiCommonListener() { // from class: com.huawei.health.device.ui.WeightOfflineDataSelectActivity.4
            @Override // com.huawei.hihealth.data.listener.HiCommonListener
            public void onSuccess(int i, Object obj) {
                LogUtil.a("WeightOfflineDataSelectActivity", "syncDataStart onSuccess");
            }

            @Override // com.huawei.hihealth.data.listener.HiCommonListener
            public void onFailure(int i, Object obj) {
                LogUtil.a("WeightOfflineDataSelectActivity", "syncDataStart onFailure");
            }
        });
    }

    @Override // android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        c();
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
