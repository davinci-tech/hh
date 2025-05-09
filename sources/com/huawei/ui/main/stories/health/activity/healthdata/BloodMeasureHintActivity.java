package com.huawei.ui.main.stories.health.activity.healthdata;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.huawei.basichealth.bloodpressure.BloodPressureSyncManager;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.arkuix.utils.ArkUIXConstants;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiHealthClient;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiSubscribeListener;
import com.huawei.hihealth.data.type.HiSubscribeType;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.health.activity.healthdata.BloodMeasureHintActivity;
import defpackage.kor;
import health.compact.a.LanguageUtil;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes.dex */
public class BloodMeasureHintActivity extends BaseActivity {

    /* renamed from: a, reason: collision with root package name */
    private HealthTextView f10019a;
    private LinearLayout b;
    private RelativeLayout c;
    private int d;
    private LinearLayout e;
    private HealthTextView f;
    private String[] g;
    private Handler h;
    private int i;
    private final AtomicBoolean j = new AtomicBoolean(false);
    private long l;
    private List<Integer> n;
    private BloodPressureSyncManager o;

    static /* synthetic */ int o(BloodMeasureHintActivity bloodMeasureHintActivity) {
        int i = bloodMeasureHintActivity.i + 1;
        bloodMeasureHintActivity.i = i;
        return i;
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_blood_measure_hint);
        if (getIntent() != null) {
            this.d = getIntent().getIntExtra(ArkUIXConstants.FROM_TYPE, 0);
        }
        LogUtil.a("BloodMeasureHintActivity_tag", "fromtype: ", Integer.valueOf(this.d));
        a();
        HiHealthNativeApi.a(this).subscribeHiHealthData(HiSubscribeType.f4119a, new e(this));
        this.o = BloodPressureSyncManager.c();
        b();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        if (LanguageUtil.bl(this)) {
            this.f10019a.setHyphenationFrequency(1);
            this.f10019a.setBreakStrategy(2);
        }
    }

    private void a() {
        this.b = (LinearLayout) findViewById(R.id.blood_measure_hint_layout);
        this.e = (LinearLayout) findViewById(R.id.blood_measure_process_layout);
        this.c = (RelativeLayout) findViewById(R.id.blood_measure_failed_layout);
        this.f = (HealthTextView) findViewById(R.id.progress_text);
        HealthTextView healthTextView = (HealthTextView) findViewById(R.id.blood_measure_posture_hint);
        HealthTextView healthTextView2 = (HealthTextView) findViewById(R.id.blood_measure_posture_hint2);
        healthTextView.setText(getString(R$string.IDS_blood_measure_posture_hint1, new Object[]{1, 5}));
        healthTextView2.setText(getString(R$string.IDS_blood_measure_posture_hint2, new Object[]{2}));
        this.f10019a = (HealthTextView) findViewById(R.id.blood_measure_process_hint_text);
        findViewById(R.id.blood_measure_failed_retry).setOnClickListener(new View.OnClickListener() { // from class: qbj
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BloodMeasureHintActivity.this.dyJ_(view);
            }
        });
        this.g = new String[]{getString(R$string.IDS_blood_measure_process_hint), getString(R$string.IDS_blood_measure_process_hint2)};
        this.h = new b(getMainLooper(), this);
    }

    public /* synthetic */ void dyJ_(View view) {
        b();
        ViewClickInstrumentation.clickOnView(view);
    }

    private void b() {
        this.o.e(new a(this));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        kor.a().a(this.l, System.currentTimeMillis(), 1, new d(this));
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        HiHealthNativeApi.a(this).unSubscribeHiHealthData(this.n, null);
        this.h.removeMessages(0);
        this.o.a();
        super.onDestroy();
    }

    /* loaded from: classes8.dex */
    static class a implements BloodPressureSyncManager.IBloodPressureMeasureCallback {
        private final WeakReference<BloodMeasureHintActivity> d;

        a(BloodMeasureHintActivity bloodMeasureHintActivity) {
            this.d = new WeakReference<>(bloodMeasureHintActivity);
        }

        @Override // com.huawei.basichealth.bloodpressure.BloodPressureSyncManager.IBloodPressureMeasureCallback
        public void onNotifyMeasure(int i) {
            BloodMeasureHintActivity bloodMeasureHintActivity = this.d.get();
            if (bloodMeasureHintActivity != null) {
                LogUtil.a("BloodMeasureHintActivity_tag", "onNotifyMeasure=", Integer.valueOf(i));
                Message obtain = Message.obtain();
                obtain.what = 1;
                obtain.arg1 = i;
                bloodMeasureHintActivity.h.sendMessage(obtain);
            }
        }

        @Override // com.huawei.basichealth.bloodpressure.BloodPressureSyncManager.IBloodPressureMeasureCallback
        public void onMeasureStart(long j) {
            BloodMeasureHintActivity bloodMeasureHintActivity = this.d.get();
            if (bloodMeasureHintActivity != null) {
                LogUtil.a("BloodMeasureHintActivity_tag", "onMeasureStart=", Long.valueOf(j));
                bloodMeasureHintActivity.l = j * 1000;
                Message obtain = Message.obtain();
                obtain.what = 2;
                bloodMeasureHintActivity.h.sendMessage(obtain);
            }
        }

        @Override // com.huawei.basichealth.bloodpressure.BloodPressureSyncManager.IBloodPressureMeasureCallback
        public void onMeasureFinish(int i, long j) {
            BloodMeasureHintActivity bloodMeasureHintActivity = this.d.get();
            if (bloodMeasureHintActivity == null) {
                return;
            }
            if (i != 0) {
                bloodMeasureHintActivity.h.sendEmptyMessage(3);
                return;
            }
            LogUtil.a("BloodMeasureHintActivity_tag", "onMeasureFinish=", Integer.valueOf(i), ", endTime=", Long.valueOf(j));
            bloodMeasureHintActivity.j.set(true);
            bloodMeasureHintActivity.c();
            bloodMeasureHintActivity.h.sendEmptyMessage(4);
        }
    }

    /* loaded from: classes8.dex */
    static class b extends BaseHandler<BloodMeasureHintActivity> {
        public b(Looper looper, BloodMeasureHintActivity bloodMeasureHintActivity) {
            super(looper, bloodMeasureHintActivity);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: dyK_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(BloodMeasureHintActivity bloodMeasureHintActivity, Message message) {
            int i = message.what;
            if (i == 0) {
                bloodMeasureHintActivity.f10019a.setText(bloodMeasureHintActivity.g[BloodMeasureHintActivity.o(bloodMeasureHintActivity) % 2]);
                bloodMeasureHintActivity.h.sendEmptyMessageDelayed(0, 5000L);
                return;
            }
            if (i == 1) {
                if (message.arg1 == 0) {
                    bloodMeasureHintActivity.b.setVisibility(0);
                    bloodMeasureHintActivity.c.setVisibility(8);
                } else {
                    bloodMeasureHintActivity.c.setVisibility(0);
                    bloodMeasureHintActivity.b.setVisibility(8);
                }
                bloodMeasureHintActivity.e.setVisibility(8);
                return;
            }
            if (i == 2) {
                bloodMeasureHintActivity.e.setVisibility(0);
                bloodMeasureHintActivity.b.setVisibility(8);
                bloodMeasureHintActivity.c.setVisibility(8);
                bloodMeasureHintActivity.h.sendEmptyMessageDelayed(0, 5000L);
                return;
            }
            if (i == 3) {
                bloodMeasureHintActivity.h.removeMessages(0);
                bloodMeasureHintActivity.c.setVisibility(0);
                bloodMeasureHintActivity.b.setVisibility(8);
                bloodMeasureHintActivity.e.setVisibility(8);
                return;
            }
            if (i == 4) {
                bloodMeasureHintActivity.f.setText(bloodMeasureHintActivity.getString(R$string.IDS_blood_measure_sync));
                return;
            }
            if (i != 5) {
                return;
            }
            Intent intent = new Intent(bloodMeasureHintActivity, (Class<?>) BloodMeasureResultActivity.class);
            intent.putExtra(ArkUIXConstants.FROM_TYPE, bloodMeasureHintActivity.d);
            if (message.obj instanceof Long) {
                LogUtil.a("BloodMeasureHintActivity_tag", "onFinish, dataTime=", message.obj);
                intent.putExtra("dataTime", (Long) message.obj);
            }
            bloodMeasureHintActivity.startActivity(intent);
            bloodMeasureHintActivity.finish();
        }
    }

    /* loaded from: classes8.dex */
    static class e implements HiSubscribeListener {
        private final WeakReference<BloodMeasureHintActivity> b;

        e(BloodMeasureHintActivity bloodMeasureHintActivity) {
            this.b = new WeakReference<>(bloodMeasureHintActivity);
        }

        @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
        public void onResult(List<Integer> list, List<Integer> list2) {
            if (list != null && !list.isEmpty()) {
                LogUtil.a("BloodMeasureHintActivity_tag", "registerBloodPressureListener success");
                BloodMeasureHintActivity bloodMeasureHintActivity = this.b.get();
                if (bloodMeasureHintActivity != null) {
                    bloodMeasureHintActivity.n = list;
                    return;
                }
                return;
            }
            LogUtil.h("BloodMeasureHintActivity_tag", "registerBloodPressureListener failed");
        }

        @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
        public void onChange(int i, HiHealthClient hiHealthClient, String str, HiHealthData hiHealthData, long j) {
            LogUtil.a("BloodMeasureHintActivity_tag", "type=", Integer.valueOf(i), ", changeKey=", str, ", newValue=", hiHealthData, ", time=", Long.valueOf(j));
            BloodMeasureHintActivity bloodMeasureHintActivity = this.b.get();
            if (bloodMeasureHintActivity != null && i == DicDataTypeUtil.DataType.BLOOD_PRESSURE_SET.value() && ArkUIXConstants.INSERT.equals(str) && bloodMeasureHintActivity.j.get()) {
                bloodMeasureHintActivity.c();
            }
        }
    }

    /* loaded from: classes8.dex */
    static class d implements IBaseResponseCallback {
        private final WeakReference<BloodMeasureHintActivity> d;

        d(BloodMeasureHintActivity bloodMeasureHintActivity) {
            this.d = new WeakReference<>(bloodMeasureHintActivity);
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            LogUtil.a("BloodMeasureHintActivity_tag", "ReadComplete onResponse Success");
            BloodMeasureHintActivity bloodMeasureHintActivity = this.d.get();
            if (bloodMeasureHintActivity == null) {
                return;
            }
            if (obj instanceof List) {
                List list = (List) obj;
                if (!list.isEmpty()) {
                    HiHealthData hiHealthData = (HiHealthData) list.get(list.size() - 1);
                    if (hiHealthData.getEndTime() < bloodMeasureHintActivity.l) {
                        LogUtil.a("BloodMeasureHintActivity_tag", "not tag data, time=", Long.valueOf(hiHealthData.getEndTime()));
                        return;
                    }
                    Message obtain = Message.obtain();
                    obtain.what = 5;
                    obtain.obj = Long.valueOf(hiHealthData.getEndTime());
                    bloodMeasureHintActivity.h.sendMessage(obtain);
                    return;
                }
            }
            LogUtil.a("BloodMeasureHintActivity_tag", "data is null");
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
