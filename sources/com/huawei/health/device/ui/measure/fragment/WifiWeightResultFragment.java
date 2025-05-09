package com.huawei.health.device.ui.measure.fragment;

import android.content.ActivityNotFoundException;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.apprichtap.haptic.base.PrebakedEffectId;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.R;
import com.huawei.health.device.fatscale.multiusers.MultiUsersManager;
import com.huawei.health.device.ui.DeviceMainActivity;
import com.huawei.health.ecologydevice.manager.ResourceManager;
import com.huawei.health.ecologydevice.ui.BaseFragment;
import com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceCategoryFragment;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.nfc.PluginPayAdapter;
import com.huawei.pluginachievement.manager.model.KakaConstants;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.wisecloud.drmclient.license.HwDrmConstant;
import defpackage.bzw;
import defpackage.cfi;
import defpackage.ckm;
import defpackage.cnx;
import defpackage.cpa;
import defpackage.cpp;
import defpackage.cpw;
import defpackage.cpy;
import defpackage.dcx;
import defpackage.dcz;
import defpackage.dfg;
import defpackage.grz;
import defpackage.ixx;
import defpackage.kot;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.UnitUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/* loaded from: classes3.dex */
public class WifiWeightResultFragment extends BaseFragment {
    private static final int PERCENTAGE_100 = 100;
    private static final String TAG = "WifiWeightResultFragment";
    private static final int TARGET_TIMES = 3;
    private HealthTextView mBetweenGoalTv;
    private HealthTextView mBodyFatValueTv;
    private HealthButton mCompleteBtn;
    private ContentValues mDeviceInfo;
    private HealthButton mRemeasureBtn;
    private LinearLayout mUserLayout;
    private double mWeight;
    private HealthTextView mWeightUnitTv;
    private HealthTextView mWeightValueTv;
    private double mTargetWeightValue = 0.0d;
    private String mProductId = "";
    private dcz mProductInfo = null;
    private ckm mWeightAndFatRateData = null;

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        cpw.a(false, TAG, "onCreate");
        super.onCreate(bundle);
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.child = layoutInflater.inflate(R.layout.device_weight_measure_result, viewGroup, false);
        ViewGroup viewGroup2 = super.onCreateView(layoutInflater, viewGroup, bundle) instanceof ViewGroup ? (ViewGroup) super.onCreateView(layoutInflater, viewGroup, bundle) : null;
        initView();
        initData();
        if (viewGroup2 != null) {
            viewGroup2.addView(this.child);
        }
        return viewGroup2;
    }

    private void initView() {
        this.mWeightValueTv = (HealthTextView) this.child.findViewById(R.id.tv_weight_mearsure_result_value);
        this.mWeightUnitTv = (HealthTextView) this.child.findViewById(R.id.tv_weight_mearsure_result_value_unit);
        this.mBodyFatValueTv = (HealthTextView) this.child.findViewById(R.id.tv_body_fat_value);
        this.mBetweenGoalTv = (HealthTextView) this.child.findViewById(R.id.tv_sugar_measure_result_msg);
        this.mCompleteBtn = (HealthButton) this.child.findViewById(R.id.bt_device_measure_result_save);
        this.mRemeasureBtn = (HealthButton) this.child.findViewById(R.id.bt_device_measure_result_remeasure);
        this.mUserLayout = (LinearLayout) this.child.findViewById(R.id.weight_result_user_layout);
        this.mCompleteBtn.setText(R.string.IDS_device_show_complete);
        this.mCompleteBtn.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.device.ui.measure.fragment.WifiWeightResultFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WifiWeightResultFragment.this.m260x8e7060a9(view);
            }
        });
        this.mRemeasureBtn.setVisibility(8);
        this.mUserLayout.setVisibility(8);
    }

    /* renamed from: lambda$initView$0$com-huawei-health-device-ui-measure-fragment-WifiWeightResultFragment, reason: not valid java name */
    /* synthetic */ void m260x8e7060a9(View view) {
        goBackToLastPage();
        ViewClickInstrumentation.clickOnView(view);
    }

    private void initData() {
        String str;
        ContentValues contentValues = (ContentValues) getArguments().getParcelable("commonDeviceInfo");
        this.mDeviceInfo = contentValues;
        if (contentValues != null) {
            this.mProductId = contentValues.getAsString("productId");
            str = cpa.f(this.mDeviceInfo.getAsString("uniqueId"));
        } else {
            cpw.d(false, TAG, "mDeviceInfo is null");
            str = "";
        }
        if (getArguments().getSerializable("HealthData") instanceof ckm) {
            this.mWeightAndFatRateData = (ckm) getArguments().getSerializable("HealthData");
        } else {
            cpw.d(false, TAG, "HealthData is not WeightAndFatRateData");
        }
        dcz d = ResourceManager.e().d(this.mProductId);
        this.mProductInfo = d;
        if (d == null || d.q().size() <= 0) {
            return;
        }
        if (TextUtils.isEmpty(str)) {
            setTitle(dcx.d(this.mProductId, this.mProductInfo.n().b()));
        } else {
            setTitle(dcx.d(this.mProductId, this.mProductInfo.n().b()) + " - " + str);
        }
        showWeightData();
        sendEventToKaKa();
        getUserTargetWeightValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showWeightData() {
        String e;
        cpw.a(false, TAG, "refreshWeightDataView");
        cnx parseData = parseData(this.mWeightAndFatRateData);
        if (UnitUtil.h()) {
            e = UnitUtil.e(UnitUtil.h(this.mWeight), 1, cpy.c(this.mWeight, this.mProductId));
            this.mWeightUnitTv.setText(R.string.IDS_device_measure_weight_value_unit_eng);
        } else {
            double d = this.mWeight;
            e = UnitUtil.e(d, 1, cpy.c(d, this.mProductId));
        }
        this.mWeightValueTv.setText(e);
        this.mBodyFatValueTv.setText(parseData.c());
        this.mBodyFatValueTv.setTextColor(parseData.d());
        this.mBetweenGoalTv.setText(parseData.e());
        cfi singleUserById = MultiUsersManager.INSTANCE.getSingleUserById(this.mWeightAndFatRateData.n());
        if (!this.mWeightAndFatRateData.q()) {
            if (singleUserById != null && singleUserById.n() != 1) {
                this.mBetweenGoalTv.setVisibility(8);
            } else {
                this.mBetweenGoalTv.setVisibility(0);
            }
        } else {
            this.mBetweenGoalTv.setVisibility(8);
        }
        if (this.mTargetWeightValue == 0.0d) {
            this.mBetweenGoalTv.setVisibility(8);
        }
    }

    private void getUserTargetWeightValue() {
        kot.a().c(new ResponseCallback<Float>() { // from class: com.huawei.health.device.ui.measure.fragment.WifiWeightResultFragment.1
            @Override // com.huawei.hwbasemgr.ResponseCallback
            public void onResponse(int i, Float f) {
                LogUtil.h(WifiWeightResultFragment.TAG, "getUserTargetWeightValue data ", f);
                if (f == null) {
                    return;
                }
                try {
                    WifiWeightResultFragment.this.mTargetWeightValue = Double.parseDouble(String.valueOf(f));
                } catch (NumberFormatException e) {
                    ReleaseLogUtil.e(WifiWeightResultFragment.TAG, "getUserTargetWeightValue NumberFormatException: ", ExceptionUtils.d(e));
                }
                WifiWeightResultFragment.this.mainActivity.runOnUiThread(new Runnable() { // from class: com.huawei.health.device.ui.measure.fragment.WifiWeightResultFragment.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        WifiWeightResultFragment.this.showWeightData();
                    }
                });
            }
        });
    }

    private void sendEventToKaKa() {
        String b = this.mProductInfo.n().b();
        cpw.c(false, TAG, "deviceName is ", b);
        HashMap hashMap = new HashMap(16);
        hashMap.put(PluginPayAdapter.KEY_DEVICE_INFO_NAME, b);
        hashMap.put(DeviceCategoryFragment.DEVICE_TYPE, this.mProductInfo.l().name());
        hashMap.put("measure_time", new SimpleDateFormat(HwDrmConstant.TIME_FORMAT).format(new Date(System.currentTimeMillis())));
        ixx.d().d(cpp.a(), AnalyticsValue.HEALTH_PLUGIN_DEVICE_MEASURE_SUCCEED_2060011.value(), hashMap, 0);
        bzw.e().setEvent(BaseApplication.getContext(), String.valueOf(KakaConstants.TASK_MEASURE_TODAY_WEIGHT), hashMap);
        LogUtil.a(TAG, "wifi test sendWeightDetailSyncSuccessBroadcast");
        ContentValues contentValues = this.mDeviceInfo;
        cpa.c(cpp.a(), this.mProductId, contentValues != null ? contentValues.getAsString("uniqueId") : "");
    }

    private cnx parseData(ckm ckmVar) {
        String string;
        String str;
        cnx cnxVar = new cnx();
        this.mWeight = ckmVar.getWeight();
        double bodyFatRat = ckmVar.getBodyFatRat();
        cnxVar.e(new DecimalFormat("0.0").format(this.mWeight));
        setModeFatValue(cnxVar, bodyFatRat);
        cpw.c(false, TAG, "parseData mTargetWeightValue is ", Double.valueOf(this.mTargetWeightValue));
        float f = (float) (this.mWeight - this.mTargetWeightValue);
        if (UnitUtil.h()) {
            string = getResources().getString(R$string.IDS_lb_string, UnitUtil.e(UnitUtil.h(Math.abs(f)), 1, cpy.c(this.mWeight, this.mProductId)));
        } else {
            string = getResources().getString(R$string.IDS_kg_string, UnitUtil.e(Math.abs(f), 1, cpy.c(this.mWeight, this.mProductId)));
        }
        if (dfg.d().e() > 3) {
            cnxVar.c("");
            return cnxVar;
        }
        if (dfg.d().d(this.mTargetWeightValue, this.mWeight)) {
            cnxVar.c(getResources().getString(R.string.IDS_device_wifi_measure_result_equal_target_msg));
            return cnxVar;
        }
        double d = this.mWeight;
        double d2 = this.mTargetWeightValue;
        if (d > d2) {
            str = getResources().getString(R.string.IDS_device_wifi_measure_result_less_target_msg, string);
        } else if (d < d2) {
            str = getResources().getString(R.string.IDS_device_wifi_measure_result_more_target_msg, string);
        } else if (d == d2) {
            str = getResources().getString(R.string.IDS_device_wifi_measure_result_equal_target_msg);
        } else {
            cpw.d(false, TAG, "parseData default");
            str = null;
        }
        cnxVar.c(str);
        return cnxVar;
    }

    private void setModeFatValue(cnx cnxVar, double d) {
        String format;
        if (d <= 0.0d) {
            format = getResources().getString(R.string.IDS_device_measure_weight_defualt_value);
            cnxVar.b(getResources().getColor(R.color._2131298112_res_0x7f090740));
        } else {
            NumberFormat percentInstance = NumberFormat.getPercentInstance();
            percentInstance.setMinimumFractionDigits(1);
            format = percentInstance.format(d / 100.0d);
            cnxVar.b(getResources().getColor(R.color._2131298111_res_0x7f09073f));
        }
        cnxVar.a(format);
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment
    public boolean onBackPressed() {
        goBackToLastPage();
        return false;
    }

    private void goBackToLastPage() {
        SharedPreferenceManager.e(this.mainActivity, Integer.toString(PrebakedEffectId.RT_HEARTBEAT), "health_wifi_notify_TIMESTAMP", this.mWeightAndFatRateData.j(), new StorageParams());
        if (!this.mWeightAndFatRateData.q()) {
            MultiUsersManager.INSTANCE.setCurrentUser(MultiUsersManager.INSTANCE.getSingleUserById(this.mWeightAndFatRateData.n()));
            Intent intent = new Intent();
            intent.setPackage(BaseApplication.getAppPackage());
            intent.putExtra("healthdata", "MyHealthData_jump_mainActivity");
            intent.putExtra("base_health_data_type_key", 1);
            intent.putExtra("weight_user_id", MultiUsersManager.INSTANCE.getCurrentUser().i());
            grz.aST_("", intent);
        } else {
            Intent intent2 = new Intent();
            intent2.setPackage(BaseApplication.getAppPackage());
            intent2.setClassName(BaseApplication.getAppPackage(), "com.huawei.ui.main.stories.health.activity.healthdata.ClaimMeasureDataActivity");
            try {
                this.mainActivity.startActivity(intent2);
            } catch (ActivityNotFoundException unused) {
                LogUtil.b(TAG, "goBackToLastPage ClaimMeasureData ActivityNotFoundException");
            }
            cpw.a(false, TAG, "current data is suspected data");
        }
        if (this.mainActivity instanceof DeviceMainActivity) {
            ((DeviceMainActivity) this.mainActivity).b(true);
        }
        if (cpa.x(this.mProductId)) {
            popupFragment(HagridDeviceManagerFragment.class);
        } else {
            LogUtil.a(TAG, "not show dialog.");
            popupFragment(WiFiProductIntroductionFragment.class);
        }
    }
}
