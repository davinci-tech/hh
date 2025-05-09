package com.huawei.ui.main.stories.health.fragment.weightbodydata;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.health.R;
import com.huawei.health.device.fatscale.multiusers.MultiUsersManager;
import com.huawei.health.device.fatscale.multiusers.WeightDataManager;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiDataInsertOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiDataOperateListener;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.dialog.CustomViewDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.numberpicker.HealthNumberPicker;
import com.huawei.ui.commonui.subtab.HealthSubTabWidget;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.health.fragment.weightbodydata.WaistToHipRatioFragment;
import defpackage.cfe;
import defpackage.doj;
import defpackage.ixx;
import defpackage.koq;
import defpackage.nsn;
import defpackage.qrd;
import defpackage.qrp;
import defpackage.qsj;
import defpackage.smy;
import health.compact.a.UnitUtil;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

/* loaded from: classes6.dex */
public class WaistToHipRatioFragment extends AbstractWaistToHipRatioFragment {
    private static WaistToHipCallback d;
    private CustomViewDialog e;
    private Handler g;
    private HealthTextView j;

    /* loaded from: classes.dex */
    public interface WaistToHipCallback {
        void onWaistToHipRatioChanged(cfe cfeVar);
    }

    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    public int getBiType() {
        return 25;
    }

    static class a extends BaseHandler<WaistToHipRatioFragment> {
        private a(WaistToHipRatioFragment waistToHipRatioFragment) {
            super(waistToHipRatioFragment);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: dEF_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(WaistToHipRatioFragment waistToHipRatioFragment, Message message) {
            if (message == null || waistToHipRatioFragment == null) {
                LogUtil.h("HealthWeight_WaistToHipRatioFragment", "MyHandler handleMessageWhenReferenceNotNull message or fragment is null");
            } else if (message.what == 0 && waistToHipRatioFragment.isAdded()) {
                LogUtil.a("HealthWeight_WaistToHipRatioFragment", "MyHandler handleMessageWhenReferenceNotNull REFRESH_UI");
                waistToHipRatioFragment.a();
            }
        }
    }

    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    public void initView(View view) {
        super.initView(view);
        this.j = (HealthTextView) view.findViewById(R.id.fragment_weight_body_data_specification_edit);
    }

    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    public void initData() {
        this.g = new a();
        this.mGender = this.mWeightBean.an();
        this.b = this.mWeightBean.ao();
        this.f10191a = this.mResources.getString(R$string.IDS_weight_waist_to_hip_ratio);
        if (this.b == 0.0d) {
            this.b = this.mWeightBean.as();
            this.f10191a = qrd.r(0);
        }
        this.c = doj.b(this.mGender, this.b);
    }

    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    public String getBodyDataKey() {
        return this.mWeightBean.ao() == 0.0d ? "waistHipRatio" : "waistHipRatioUser";
    }

    @Override // com.huawei.ui.main.stories.health.fragment.weightbodydata.AbstractWaistToHipRatioFragment, com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    public void initSpecification() {
        super.initSpecification();
        if (qrp.d() && this.mWeightBean.ao() == 0.0d) {
            this.j.setText(this.mResources.getString(R$string.IDS_weight_adjustment_waist_to_hip_ratio));
            this.j.setVisibility(0);
            this.j.setOnClickListener(new View.OnClickListener() { // from class: qjy
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    WaistToHipRatioFragment.this.dED_(view);
                }
            });
        }
    }

    public /* synthetic */ void dED_(View view) {
        d();
        ViewClickInstrumentation.clickOnView(view);
    }

    private void d() {
        ArrayList arrayList = new ArrayList(16);
        final ArrayList<Double> arrayList2 = new ArrayList<>(16);
        BigDecimal bigDecimal = new BigDecimal("0.01");
        for (int i = 40; i <= 150; i++) {
            BigDecimal multiply = new BigDecimal(i).multiply(bigDecimal);
            arrayList.add(UnitUtil.e(nsn.j(String.valueOf(multiply)), 1, 2));
            arrayList2.add(Double.valueOf(multiply.doubleValue()));
        }
        View inflate = View.inflate(this.mContext, R.layout.health_healthdata_userinfo_multiuserweight_dialog, null);
        ((HealthTextView) inflate.findViewById(R.id.hw_health_multiuserWeight_dialog_tips)).setText(this.mResources.getString(R$string.IDS_weight_waist_to_hip_ratio_description));
        final HealthNumberPicker healthNumberPicker = (HealthNumberPicker) inflate.findViewById(R.id.multiuserweight_number_picker);
        healthNumberPicker.setDisplayedValues((String[]) arrayList.toArray(new String[0]));
        healthNumberPicker.setMinValue(0);
        healthNumberPicker.setMaxValue(arrayList.size() - 1);
        e(arrayList2, healthNumberPicker);
        healthNumberPicker.setWrapSelectorWheel(false);
        CustomViewDialog.Builder builder = new CustomViewDialog.Builder(this.mActivity);
        builder.d(R$string.IDS_weight_adjustment_waist_to_hip_ratio).czg_(inflate).cze_(R$string.IDS_settings_button_ok, new View.OnClickListener() { // from class: qjs
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WaistToHipRatioFragment.this.dEE_(healthNumberPicker, arrayList2, view);
            }
        }).czc_(R$string.IDS_settings_button_cancal, new View.OnClickListener() { // from class: qjw
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        if (this.e == null) {
            this.e = builder.e();
        }
        CustomViewDialog customViewDialog = this.e;
        if (customViewDialog == null || customViewDialog.isShowing()) {
            return;
        }
        this.e.show();
    }

    public /* synthetic */ void dEE_(HealthNumberPicker healthNumberPicker, ArrayList arrayList, View view) {
        e();
        int value = healthNumberPicker.getValue();
        if (koq.b(arrayList, value)) {
            LogUtil.h("HealthWeight_WaistToHipRatioFragment", "showRatioPickerDialog is Out Of Bounds");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        this.b = ((Double) arrayList.get(value)).doubleValue();
        this.c = doj.b(this.mGender, this.b);
        this.mWeightBean.ah(this.b);
        c();
        LogUtil.c("HealthWeight_WaistToHipRatioFragment", "showRatioPickerDialog mWaistToHipRatio ", Double.valueOf(this.b));
        ObserverManagerUtil.c("WEIGHT_ADJUSTMENT_WAIST_TO_H5", new Object[0]);
        ViewClickInstrumentation.clickOnView(view);
    }

    private void e(ArrayList<Double> arrayList, HealthNumberPicker healthNumberPicker) {
        int indexOf = arrayList.indexOf(Double.valueOf(new BigDecimal(this.mWeightBean.as()).setScale(2, 4).doubleValue()));
        if (indexOf != -1) {
            healthNumberPicker.setValue(indexOf);
        } else {
            healthNumberPicker.setValue(1);
        }
    }

    private void c() {
        HiHealthData hiHealthData = new HiHealthData(10006);
        qsj.d(hiHealthData, this.mWeightBean);
        hiHealthData.setOwnerId(0);
        hiHealthData.setDeviceUuid(b());
        e(hiHealthData, MultiUsersManager.INSTANCE.getCurrentUser().i());
        HiDataInsertOption hiDataInsertOption = new HiDataInsertOption();
        hiDataInsertOption.addData(hiHealthData);
        HiHealthManager.d(this.mContext).insertHiHealthData(hiDataInsertOption, new HiDataOperateListener() { // from class: qju
            @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
            public final void onResult(int i, Object obj) {
                WaistToHipRatioFragment.this.d(i, obj);
            }
        });
    }

    public /* synthetic */ void d(int i, Object obj) {
        Handler handler;
        LogUtil.a("HealthWeight_WaistToHipRatioFragment", "insertData onResult errorCode ", Integer.valueOf(i));
        if (i != 0 || (handler = this.g) == null) {
            return;
        }
        handler.sendEmptyMessage(0);
    }

    private String b() {
        if (this.mWeightBean == null) {
            LogUtil.h("HealthWeight_WaistToHipRatioFragment", "getDeviceUuid mWeightBean is null");
            return String.valueOf(-1);
        }
        String m = this.mWeightBean.m();
        if (TextUtils.isEmpty(m)) {
            LogUtil.h("HealthWeight_WaistToHipRatioFragment", "getDeviceUuid deviceUuid is empty");
            return String.valueOf(-1);
        }
        LogUtil.a("HealthWeight_WaistToHipRatioFragment", "getDeviceUuid deviceUuid ", m);
        return m;
    }

    private void e(HiHealthData hiHealthData, String str) {
        String i = MultiUsersManager.INSTANCE.getMainUser().i();
        if (str == null) {
            MultiUsersManager.INSTANCE.setCurrentUser(MultiUsersManager.INSTANCE.getMainUser());
            return;
        }
        if (i == null) {
            hiHealthData.setMetaData(String.valueOf(0));
        } else if (i.equals(str)) {
            hiHealthData.setMetaData(String.valueOf(0));
        } else {
            hiHealthData.setMetaData(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        this.f10191a = this.mResources.getString(R$string.IDS_weight_waist_to_hip_ratio);
        this.b = UnitUtil.a(this.b, 2);
        setLevel(this.b, UnitUtil.e(this.b, 1, 2));
        this.j.setVisibility(8);
        if (this.mActivity == null) {
            LogUtil.h("HealthWeight_WaistToHipRatioFragment", "refreshSubTabTitle mActivity is null");
        } else {
            HealthSubTabWidget b = this.mActivity.b();
            if (b == null) {
                LogUtil.h("HealthWeight_WaistToHipRatioFragment", "refreshSubTabTitle healthSubTabWidget is null");
            } else {
                smy selectedSubTab = b.getSelectedSubTab();
                if (selectedSubTab == null) {
                    LogUtil.h("HealthWeight_WaistToHipRatioFragment", "refreshSubTabTitle hwSubTab is null");
                } else {
                    selectedSubTab.d(this.mResources.getText(R$string.IDS_weight_waist_to_hip_ratio));
                }
                b.d(25);
            }
        }
        WaistToHipCallback waistToHipCallback = d;
        if (waistToHipCallback == null) {
            LogUtil.h("HealthWeight_WaistToHipRatioFragment", "refreshSubTabTitle sWaistToHipCallback is null");
        } else {
            waistToHipCallback.onWaistToHipRatioChanged(this.mWeightBean);
        }
        initAnalysisAndInterpretation();
        WeightDataManager.INSTANCE.setInitFlag(true);
    }

    private void e() {
        if (this.mWeightBean == null) {
            LogUtil.h("HealthWeight_WaistToHipRatioFragment", "doBiEditWaistToHipRatio mWeightBean is null");
            return;
        }
        int l = this.mWeightBean.l();
        LogUtil.c("HealthWeight_WaistToHipRatioFragment", "doBiEditWaistToHipRatio dataType ", Integer.valueOf(l));
        HashMap hashMap = new HashMap(16);
        hashMap.put("type", Integer.valueOf(l));
        hashMap.put("click", "1");
        ixx.d().d(this.mContext, AnalyticsValue.HEALTH_HOME_BODY_DETAIL_WAIST_TO_HIP_RATIO_2030067.value(), hashMap, 0);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDetach() {
        super.onDetach();
        Handler handler = this.g;
        if (handler != null) {
            handler.removeMessages(0);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        Handler handler = this.g;
        if (handler != null) {
            handler.removeMessages(0);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void b(Context context) {
        d = (WaistToHipCallback) context;
    }
}
