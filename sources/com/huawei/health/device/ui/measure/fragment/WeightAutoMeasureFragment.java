package com.huawei.health.device.ui.measure.fragment;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.huawei.health.R;
import com.huawei.health.device.callback.IHealthDeviceCallback;
import com.huawei.health.device.connectivity.comm.MeasurableDevice;
import com.huawei.health.device.fatscale.multiusers.MultiUsersManager;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.device.open.data.model.HealthData;
import com.huawei.health.device.util.EventBus;
import com.huawei.health.ecologydevice.manager.ResourceManager;
import com.huawei.health.ecologydevice.ui.BaseFragment;
import com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceCategoryFragment;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiUserPreference;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.nfc.PluginPayAdapter;
import com.huawei.pluginachievement.manager.model.KakaConstants;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.wisecloud.drmclient.license.HwDrmConstant;
import defpackage.bzw;
import defpackage.ceo;
import defpackage.cfi;
import defpackage.cjn;
import defpackage.cjx;
import defpackage.ckm;
import defpackage.cnx;
import defpackage.cpa;
import defpackage.cpz;
import defpackage.dcx;
import defpackage.dcz;
import defpackage.dfd;
import defpackage.dfg;
import defpackage.dks;
import defpackage.jdx;
import defpackage.kot;
import defpackage.nrf;
import health.compact.a.CommonUtil;
import health.compact.a.UnitUtil;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes3.dex */
public class WeightAutoMeasureFragment extends BaseFragment implements View.OnClickListener {
    private static final String CLASS_NAME = "WeightAutoMeasureFragment";
    private static final String DEVICE_AVAILABLE = "com.huawei.health.action.DEVICE_AVAILABLE";
    private static final String TAG = "WeightAutoMeasureFragment";
    private HealthTextView mBetweenGoal;
    private HealthTextView mBodyFat;
    private IHealthDeviceCallback mCallback;
    private Context mContext;
    private HealthData mHealthData;
    private ImageView mIconImage;
    private String mProductId;
    private Resources mResources;
    private HealthButton mSaveBtn;
    private String mUniqueId;
    private cfi mUser;
    private HealthTextView mWeight;
    private HealthTextView mWeightUnit;
    private float mWeightValue;

    public WeightAutoMeasureFragment() {
        Context context = BaseApplication.getContext();
        this.mContext = context;
        this.mResources = context.getResources();
        this.mUser = MultiUsersManager.INSTANCE.getMainUser();
        this.mCallback = new IHealthDeviceCallback() { // from class: com.huawei.health.device.ui.measure.fragment.WeightAutoMeasureFragment.1
            @Override // com.huawei.health.device.callback.IHealthDeviceCallback
            public void onDataChanged(HealthDevice healthDevice, final HealthData healthData) {
                if (WeightAutoMeasureFragment.this.mainActivity != null) {
                    WeightAutoMeasureFragment.this.mainActivity.runOnUiThread(new Runnable() { // from class: com.huawei.health.device.ui.measure.fragment.WeightAutoMeasureFragment.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            WeightAutoMeasureFragment.this.handleDataChangedInUiThread(healthData);
                        }
                    });
                } else {
                    LogUtil.h("WeightAutoMeasureFragment", "WeightAutoMeasureFragment", " mCallback onDataChanged mainActivity is null");
                }
            }

            @Override // com.huawei.health.device.callback.IHealthDeviceCallback
            public void onDataChanged(HealthDevice healthDevice, List<HealthData> list) {
                LogUtil.a("WeightAutoMeasureFragment", "WeightAutoMeasureFragment", " mCallback onDataChanged");
            }

            @Override // com.huawei.health.device.callback.IHealthDeviceCallback
            public void onProgressChanged(HealthDevice healthDevice, HealthData healthData) {
                LogUtil.a("WeightAutoMeasureFragment", "WeightAutoMeasureFragment", " mCallback onProgressChanged");
            }

            @Override // com.huawei.health.device.callback.IHealthDeviceCallback
            public void onStatusChanged(HealthDevice healthDevice, int i) {
                LogUtil.a("WeightAutoMeasureFragment", "WeightAutoMeasureFragment", " mCallback onStatusChanged status ", Integer.valueOf(i));
            }

            @Override // com.huawei.health.device.callback.IHealthDeviceCallback
            public void onFailed(HealthDevice healthDevice, int i) {
                LogUtil.h("WeightAutoMeasureFragment", "WeightAutoMeasureFragment", " mCallback onFailed code", Integer.valueOf(i));
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleDataChangedInUiThread(HealthData healthData) {
        if (healthData == null) {
            LogUtil.h("WeightAutoMeasureFragment", "WeightAutoMeasureFragment", " handleDataChangedInUiThread data is null");
            return;
        }
        LogUtil.a("WeightAutoMeasureFragment", "WeightAutoMeasureFragment", " handleDataChangedInUiThread");
        this.mHealthData = healthData;
        initGoalWeight();
        initStartWeight();
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        initGoalWeight();
        jdx.b(new Runnable() { // from class: com.huawei.health.device.ui.measure.fragment.WeightAutoMeasureFragment$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                WeightAutoMeasureFragment.this.m213xfe87bb72();
            }
        });
        cjn.e(this.mainActivity).d(this.mCallback);
        initData();
    }

    /* renamed from: lambda$onCreate$0$com-huawei-health-device-ui-measure-fragment-WeightAutoMeasureFragment, reason: not valid java name */
    /* synthetic */ void m213xfe87bb72() {
        HiUserPreference userPreference = HiHealthManager.d(this.mContext).getUserPreference("custom.start_weight_base");
        if (userPreference != null) {
            this.mUser.c(CommonUtil.j(userPreference.getValue()));
        }
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment
    public void release() {
        super.release();
        if (this.mainActivity != null) {
            this.mainActivity.finish();
        }
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        ViewGroup viewGroup2 = (ViewGroup) super.onCreateView(layoutInflater, viewGroup, bundle);
        this.child = layoutInflater.inflate(R.layout.device_weight_auto_measure, viewGroup, false);
        if (viewGroup2 != null) {
            viewGroup2.addView(this.child);
        }
        if (this.child == null) {
            LogUtil.h("WeightAutoMeasureFragment", "onCreateView child is null");
            return viewGroup2;
        }
        initGoalWeight();
        this.mWeight = (HealthTextView) this.child.findViewById(R.id.tv_weight_mearsure_result_value);
        this.mWeightUnit = (HealthTextView) this.child.findViewById(R.id.tv_weight_mearsure_result_value_unit);
        this.mBetweenGoal = (HealthTextView) this.child.findViewById(R.id.tv_sugar_measure_result_msg);
        this.mBodyFat = (HealthTextView) this.child.findViewById(R.id.tv_body_fat_value);
        this.mIconImage = (ImageView) this.child.findViewById(R.id.weight_user_icon);
        this.child.findViewById(R.id.bt_device_measure_result_remeasure).setVisibility(8);
        ((HealthTextView) this.child.findViewById(R.id.weight_user_name)).setText(this.mUser.h());
        HealthButton healthButton = (HealthButton) this.child.findViewById(R.id.bt_device_measure_result_save);
        this.mSaveBtn = healthButton;
        healthButton.setText(R.string.IDS_device_measureactivity_result_save);
        this.mSaveBtn.setOnClickListener(this);
        initStartWeight();
        return viewGroup2;
    }

    private void initData() {
        Bundle arguments = getArguments();
        if (arguments == null) {
            LogUtil.h("WeightAutoMeasureFragment", "WeightAutoMeasureFragment", " initData bundle is null");
            return;
        }
        Serializable serializable = arguments.getSerializable("HealthData");
        if (serializable instanceof HealthData) {
            this.mHealthData = (HealthData) serializable;
        }
        ContentValues contentValues = (ContentValues) arguments.getParcelable("commonDeviceInfo");
        if (contentValues != null) {
            this.mProductId = contentValues.getAsString("productId");
            this.mUniqueId = contentValues.getAsString("uniqueId");
        }
        if (TextUtils.isEmpty(this.mProductId)) {
            LogUtil.h("WeightAutoMeasureFragment", "WeightAutoMeasureFragment", " initData mProductId is empty");
            return;
        }
        LogUtil.a("WeightAutoMeasureFragment", "WeightAutoMeasureFragment", " initData");
        dfg.d().c(this.mProductId);
        HashMap hashMap = new HashMap(16);
        dcz d = ResourceManager.e().d(this.mProductId);
        if (d != null) {
            dcz.b n = d.n();
            if (n != null) {
                hashMap.put(PluginPayAdapter.KEY_DEVICE_INFO_NAME, n.b());
            }
            HealthDevice.HealthDeviceKind l = d.l();
            if (l != null) {
                hashMap.put(DeviceCategoryFragment.DEVICE_TYPE, l.name());
            }
        }
        hashMap.put("measure_time", new SimpleDateFormat(HwDrmConstant.TIME_FORMAT).format(new Date(System.currentTimeMillis())));
        bzw.e().setEvent(BaseApplication.getContext(), String.valueOf(KakaConstants.TASK_ENTER_TODAY_WEIGHT), hashMap);
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment
    public void saveResultData() {
        super.saveResultData();
        saveWeightData();
    }

    private cnx parseData() {
        String e;
        cnx cnxVar = new cnx();
        HealthData healthData = this.mHealthData;
        if (healthData instanceof ckm) {
            ckm ckmVar = (ckm) healthData;
            this.mWeightValue = ckmVar.getWeight();
            double bodyFatRat = ckmVar.getBodyFatRat();
            if (bodyFatRat <= 0.0d) {
                e = this.mResources.getString(R.string.IDS_device_measure_weight_defualt_value);
                cnxVar.b(this.mResources.getColor(R.color._2131298112_res_0x7f090740));
            } else {
                e = UnitUtil.e(bodyFatRat, 2, 1);
                cnxVar.b(this.mResources.getColor(R.color._2131298111_res_0x7f09073f));
            }
            cnxVar.a(e);
            showViewValue(cnxVar);
            LogUtil.c("WeightAutoMeasureFragment", "WeightAutoMeasureFragment", " parseData mWeight ", Float.valueOf(this.mWeightValue), " bodyFat ", e);
        } else {
            LogUtil.h("WeightAutoMeasureFragment", "WeightAutoMeasureFragment", " parseData mHealthData is null or not WeightAndFatRateData");
        }
        return cnxVar;
    }

    private void showViewValue(cnx cnxVar) {
        String quantityString;
        if (dfg.d().e() > 3) {
            cnxVar.c("");
            return;
        }
        float k = this.mUser.k();
        double d = this.mWeightValue;
        double d2 = k;
        double abs = Math.abs(UnitUtil.a(d, getFractionDigitForWeight(d, this.mProductId)) - d2);
        if (UnitUtil.h()) {
            double h = UnitUtil.h(abs);
            quantityString = this.mResources.getQuantityString(R.plurals._2130903216_res_0x7f0300b0, UnitUtil.e(h), UnitUtil.e(h, 1, getFractionDigitForWeight(UnitUtil.h(this.mWeightValue), this.mProductId)));
        } else {
            quantityString = this.mResources.getQuantityString(R.plurals._2130903215_res_0x7f0300af, UnitUtil.e(abs), UnitUtil.e(abs, 1, getFractionDigitForWeight(this.mWeightValue, this.mProductId)));
        }
        cnxVar.c(dks.c(this.mWeightValue, this.mUser.f(), d2, quantityString));
    }

    private int getFractionDigitForWeight(double d, String str) {
        return (!cpa.x(str) || (((UnitUtil.a(d, 2) - UnitUtil.a(d, 1)) > 0.0d ? 1 : ((UnitUtil.a(d, 2) - UnitUtil.a(d, 1)) == 0.0d ? 0 : -1)) == 0)) ? 1 : 2;
    }

    private void initStartWeight() {
        if (this.mUser.f() > 0.0f) {
            m211xf0dc887c();
        } else {
            jdx.b(new Runnable() { // from class: com.huawei.health.device.ui.measure.fragment.WeightAutoMeasureFragment$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    WeightAutoMeasureFragment.this.m212x73273d5b();
                }
            });
        }
    }

    /* renamed from: lambda$initStartWeight$2$com-huawei-health-device-ui-measure-fragment-WeightAutoMeasureFragment, reason: not valid java name */
    /* synthetic */ void m212x73273d5b() {
        HiUserPreference userPreference = HiHealthManager.d(this.mContext).getUserPreference("custom.start_weight_base");
        if (userPreference != null) {
            this.mUser.c(CommonUtil.j(userPreference.getValue()));
        }
        if (this.mainActivity == null) {
            LogUtil.h("WeightAutoMeasureFragment", "WeightAutoMeasureFragment", " handleDataChangedInUiThread mainActivity is null");
        } else {
            this.mainActivity.runOnUiThread(new Runnable() { // from class: com.huawei.health.device.ui.measure.fragment.WeightAutoMeasureFragment$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    WeightAutoMeasureFragment.this.m211xf0dc887c();
                }
            });
        }
    }

    private void initGoalWeight() {
        if (this.mUser.k() > 0.0f) {
            return;
        }
        kot.a().c(new ResponseCallback<Float>() { // from class: com.huawei.health.device.ui.measure.fragment.WeightAutoMeasureFragment.2
            @Override // com.huawei.hwbasemgr.ResponseCallback
            public void onResponse(int i, Float f) {
                LogUtil.h("WeightAutoMeasureFragment", "initGoalWeight data ", f);
                if (f == null) {
                    return;
                }
                WeightAutoMeasureFragment.this.mUser.d(f.floatValue());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initViewData, reason: merged with bridge method [inline-methods] */
    public void m211xf0dc887c() {
        sendUserInfo();
        cnx parseData = parseData();
        if (UnitUtil.h()) {
            double h = UnitUtil.h(this.mWeightValue);
            this.mWeight.setText(UnitUtil.e(h, 1, getFractionDigitForWeight(h, this.mProductId)));
            this.mWeightUnit.setText(this.mResources.getQuantityString(R.plurals._2130903216_res_0x7f0300b0, UnitUtil.e(h), ""));
        } else {
            HealthTextView healthTextView = this.mWeight;
            double d = this.mWeightValue;
            healthTextView.setText(UnitUtil.e(d, 1, getFractionDigitForWeight(d, this.mProductId)));
            this.mWeightUnit.setText(this.mResources.getQuantityString(R.plurals._2130903215_res_0x7f0300af, UnitUtil.e(this.mWeightValue), ""));
        }
        this.mBetweenGoal.setText(parseData.e());
        this.mBodyFat.setText(parseData.c());
        this.mBodyFat.setTextColor(parseData.d());
        setUserNameAndPhoto();
        dcz d2 = ResourceManager.e().d(this.mProductId);
        if (d2 == null) {
            LogUtil.h("WeightAutoMeasureFragment", "WeightAutoMeasureFragment", " initViewData productInfo is null");
            return;
        }
        dcz.b n = d2.n();
        if (n == null) {
            LogUtil.h("WeightAutoMeasureFragment", "WeightAutoMeasureFragment", " initViewData productManifest is null");
        } else {
            super.setTitle(dcx.d(this.mProductId, n.b()));
        }
    }

    private void sendUserInfo() {
        Bundle bundle = new Bundle();
        bundle.putInt("height", this.mUser.d());
        bundle.putInt("sex", this.mUser.c());
        bundle.putInt("age", this.mUser.a());
        bundle.putInt("birthday", this.mUser.g());
        EventBus.d(new EventBus.b("weight_measure_set_user", bundle));
    }

    private void saveWeightData() {
        cpz.a(this.mProductId);
        if (this.mSaveBtn.isEnabled()) {
            MultiUsersManager.INSTANCE.setCurrentUser(this.mUser);
            MeasurableDevice d = ceo.d().d(this.mUniqueId, true);
            if (d != null) {
                new dfd(10006).onDataChanged(d, this.mHealthData);
            }
            cjx e = cjx.e();
            e.e(this.mProductId, this.mUniqueId, -6);
            e.a(this.mProductId, this.mUniqueId, DEVICE_AVAILABLE);
            if (this.mainActivity != null) {
                this.mainActivity.finish();
            }
        }
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment
    public boolean onBackPressed() {
        if (this.mSaveBtn.isEnabled()) {
            return super.showCustomAlertDialog(R.string.IDS_device_cancel_save_data);
        }
        return false;
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        cjx.e().e(this.mProductId, this.mUniqueId, -6);
        cjn.e(this.mainActivity).e();
    }

    private void setUserNameAndPhoto() {
        String e = this.mUser.e();
        if (TextUtils.isEmpty(e)) {
            Bitmap Ex_ = this.mUser.Ex_();
            if (Ex_ == null) {
                this.mIconImage.setImageResource(R.mipmap._2131821050_res_0x7f1101fa);
                return;
            } else {
                this.mIconImage.setImageBitmap(nrf.cHX_(Ex_));
                return;
            }
        }
        setUserPhoto(e, this.mIconImage);
    }

    private void setUserPhoto(String str, ImageView imageView) {
        if ("default".equals(str)) {
            imageView.setImageResource(R.mipmap._2131821050_res_0x7f1101fa);
            return;
        }
        Bitmap cIe_ = nrf.cIe_(this.mContext, str);
        if (cIe_ != null) {
            imageView.setImageBitmap(cIe_);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == null) {
            LogUtil.h("WeightAutoMeasureFragment", "WeightAutoMeasureFragment", " onClick view is null");
            ViewClickInstrumentation.clickOnView(view);
        } else {
            if (view.getId() == R.id.bt_device_measure_result_save) {
                saveWeightData();
            }
            ViewClickInstrumentation.clickOnView(view);
        }
    }
}
