package com.huawei.ui.main.stories.health.pressure.provider;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.health.R;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.knit.data.BaseKnitDataProvider;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.h5pro.jsmodules.interfaces.ServiceApiCallback;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.fitness.activity.pressure.activity.PressureMeasureActivity;
import com.huawei.ui.main.stories.fitness.activity.pressurecalibrate.activity.PressureCalibrateActivity;
import com.huawei.ui.main.stories.health.activity.healthdata.KnitPressureActivity;
import com.huawei.ui.main.stories.health.pressure.provider.PressureEntryProvider;
import defpackage.edy;
import defpackage.gge;
import defpackage.jpt;
import defpackage.nkx;
import defpackage.qaz;
import defpackage.qba;
import defpackage.qrp;
import health.compact.a.EnvironmentInfo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes9.dex */
public class PressureEntryProvider extends BaseKnitDataProvider {
    private SectionBean b;
    private List<edy> d = new ArrayList();
    private View.OnClickListener e = new AnonymousClass5();

    /* renamed from: com.huawei.ui.main.stories.health.pressure.provider.PressureEntryProvider$5, reason: invalid class name */
    public class AnonymousClass5 implements OnClickSectionListener {
        @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
        public void onClick(int i) {
        }

        @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
        public void onClick(int i, int i2) {
        }

        @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
        public void onClick(int i, String str) {
        }

        @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
        public void onClick(String str) {
        }

        AnonymousClass5() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (view.getId() == R.id.section_button_view1) {
                PressureEntryProvider.this.a();
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            final Activity wa_ = BaseApplication.wa_();
            if (view.getId() == R.id.section_button_view2 && wa_ != null) {
                PressureEntryProvider.a(wa_, new IBaseResponseCallback() { // from class: qmn
                    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                    /* renamed from: onResponse */
                    public final void d(int i, Object obj) {
                        PressureEntryProvider.AnonymousClass5.this.dFL_(wa_, i, obj);
                    }
                }, null);
            }
            ViewClickInstrumentation.clickOnView(view);
        }

        public /* synthetic */ void dFL_(Activity activity, int i, Object obj) {
            PressureEntryProvider.this.d(1, AnalyticsValue.HEALTH_PRESSUER_ADJUST_CLICK_2160005);
            Intent intent = new Intent(activity, (Class<?>) PressureCalibrateActivity.class);
            intent.putExtra("pressure_is_have_datas", true);
            activity.startActivity(intent);
        }
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    /* renamed from: loadData */
    public void e(Context context, SectionBean sectionBean) {
        this.b = sectionBean;
        sectionBean.e(new Object());
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public boolean isActive(Context context) {
        return !EnvironmentInfo.k();
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void parseParams(Context context, HashMap hashMap, Object obj) {
        LogUtil.a("SleepRecordEntryProvider", "parseParams");
        if (hashMap == null || context == null) {
            return;
        }
        Activity dHL_ = qrp.dHL_(context);
        if (!(dHL_ instanceof KnitPressureActivity)) {
            LogUtil.a("SleepRecordEntryProvider", "activity is null");
            return;
        }
        this.d.clear();
        View.OnClickListener cwZ_ = nkx.cwZ_(this.e, (KnitPressureActivity) dHL_, true, "");
        this.d.add(new edy.d().e(R.drawable.section_entry_card_background).d(R.drawable._2131431103_res_0x7f0b0ebf).a(R$string.IDS_hw_pressure_measuremeant).agQ_(cwZ_).b());
        this.d.add(new edy.d().e(R.drawable.section_entry_card_background).d(R.drawable._2131427542_res_0x7f0b00d6).a(R$string.IDS_hw_pressure_adjust).agQ_(cwZ_).b());
        hashMap.put("SECTION_ENTRY_BUTTON_BEAN_LIST", this.d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        LogUtil.a("SleepRecordEntryProvider", "jumpToPressureMeasurement");
        d(e(), AnalyticsValue.HEALTH_PRESSUER_MEASUREMENT_CLICK_2160009);
        Activity wa_ = BaseApplication.wa_();
        if (wa_ == null) {
            LogUtil.h("SleepRecordEntryProvider", "jumpToPressureMeasurement activity is null");
        } else {
            qba.a(wa_, new IBaseResponseCallback() { // from class: qmq
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i, Object obj) {
                    PressureEntryProvider.this.d(i, obj);
                }
            });
        }
    }

    public /* synthetic */ void d(final int i, Object obj) {
        HandlerExecutor.a(new Runnable() { // from class: com.huawei.ui.main.stories.health.pressure.provider.PressureEntryProvider.2
            @Override // java.lang.Runnable
            public void run() {
                int i2 = i;
                if (i2 == 0) {
                    PressureEntryProvider.this.d();
                } else if (i2 == 100001) {
                    PressureEntryProvider.this.b();
                } else {
                    LogUtil.a("SleepRecordEntryProvider", "errorCode = ", Integer.valueOf(i2));
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i, AnalyticsValue analyticsValue) {
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", 1);
        hashMap.put("havedevice", Integer.valueOf(i));
        gge.e(analyticsValue.value(), hashMap);
    }

    public static void a(Context context, IBaseResponseCallback iBaseResponseCallback, ServiceApiCallback<String> serviceApiCallback) {
        LogUtil.a("SleepRecordEntryProvider", "jumpToPressureAdjust");
        qaz qazVar = new qaz(context, true);
        if (qazVar.c()) {
            qazVar.d();
            return;
        }
        if (qazVar.b()) {
            LogUtil.c("SleepRecordEntryProvider", "isHaveWifiDevice() = ", Boolean.valueOf(qazVar.b()));
            qazVar.a();
        } else {
            qba.b(context, iBaseResponseCallback);
            if (serviceApiCallback != null) {
                serviceApiCallback.onSuccess("success");
            }
        }
    }

    private int e() {
        DeviceInfo a2 = jpt.a("SleepRecordEntryProvider");
        return (a2 == null || a2.getDeviceConnectState() != 2) ? 0 : 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        final Activity wa_ = BaseApplication.wa_();
        if (wa_ == null) {
            LogUtil.h("SleepRecordEntryProvider", "jumpToPressureCalibrate activity is null");
        } else {
            qba.d(wa_, new IBaseResponseCallback() { // from class: qmp
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i, Object obj) {
                    PressureEntryProvider.this.dFK_(wa_, i, obj);
                }
            });
        }
    }

    public /* synthetic */ void dFK_(Activity activity, int i, Object obj) {
        if (i != 0) {
            return;
        }
        LogUtil.a("SleepRecordEntryProvider", "user click adjust button");
        d(1, AnalyticsValue.HEALTH_PRESSUER_ADJUST_CLICK_2160005);
        Intent intent = new Intent(activity, (Class<?>) PressureCalibrateActivity.class);
        intent.putExtra("pressure_is_have_datas", true);
        activity.startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        Activity wa_ = BaseApplication.wa_();
        if (wa_ == null) {
            LogUtil.h("SleepRecordEntryProvider", "jumpToPressureMeasure activity is null");
            return;
        }
        Intent intent = new Intent(wa_, (Class<?>) PressureMeasureActivity.class);
        intent.putExtra("pressure_is_have_datas", true);
        wa_.startActivity(intent);
    }
}
