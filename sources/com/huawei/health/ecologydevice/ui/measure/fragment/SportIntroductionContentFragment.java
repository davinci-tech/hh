package com.huawei.health.ecologydevice.ui.measure.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.LinearLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.device.model.DeviceInformation;
import com.huawei.health.ecologydevice.callback.PickerChooseCallback;
import com.huawei.health.ecologydevice.manager.EcologyDevicePickerManager;
import com.huawei.health.ecologydevice.ui.measure.fragment.ropefragment.DeviceInfoFragment;
import com.huawei.health.ecologydevice.ui.measure.fragment.sportfragment.RateControlDescriptionFragment;
import com.huawei.health.ecologydevice.ui.measure.fragment.sportfragment.RateControlFragment;
import com.huawei.health.ecologydevice.ui.measure.presenter.SportIntroductionContentPresenter;
import com.huawei.health.ecologydevice.ui.measure.view.SportIntroductionContentView;
import com.huawei.health.sport.model.WorkoutRecord;
import com.huawei.health.suggestion.PluginSuggestion;
import com.huawei.health.suggestion.model.FitRunPlayAudio;
import com.huawei.healthcloud.plugintrack.ui.activity.TrackDetailActivity;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.data.model.HiTrackMetaData;
import com.huawei.hihealthservice.old.model.OldToNewMotionPath;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.profile.coordinator.ProfileRequestConstants;
import com.huawei.ui.commonui.adapter.BaseRecyclerAdapter;
import com.huawei.ui.commonui.adapter.RecyclerHolder;
import com.huawei.ui.commonui.dialog.CustomAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.imageview.HealthImageView;
import com.huawei.ui.commonui.progressbar.HealthProgressBar;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import defpackage.czd;
import defpackage.dhe;
import defpackage.dhf;
import defpackage.dij;
import defpackage.diy;
import defpackage.djr;
import defpackage.djs;
import defpackage.djt;
import defpackage.dks;
import defpackage.gnm;
import defpackage.gwg;
import defpackage.ixx;
import defpackage.koq;
import defpackage.kpj;
import defpackage.kwx;
import defpackage.mmp;
import defpackage.mod;
import defpackage.nrf;
import defpackage.nrz;
import defpackage.nsn;
import health.compact.a.CommonUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.Services;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes3.dex */
public class SportIntroductionContentFragment extends SportDeviceIntroductionFragment implements SportIntroductionContentView, BaseRecyclerAdapter.OnItemClickListener<dhe> {
    private static final String FORMAT_HOUR_MIN = "HH:mm";
    public static final int FULL_MARATHON_CODE = -3;
    public static final int HALF_MARATHON_CODE = -2;
    private static final float KILOMETERS_TO_METER = 1000.0f;
    private static final double MAX_BIKE_DISTANCE = 200.0d;
    private static final double MAX_DISTANCE = 100.0d;
    private static final int MAX_HEAT = 5000;
    private static final int MAX_TIME_MINUTES = 1080;
    private static final float MINUTE_TO_SECOND = 60.0f;
    private static final double MIN_DISTANCE = 0.1d;
    private static final int MIN_HEAT = 100;
    private static final int MIN_TIME_MINUTES = 10;
    private static final int RECONNECT_SHOW_DIALOG_COUNT = 3;
    private static final String RELEASE_TAG = "R_DEVMGR_SportContentFragment";
    private static final String ROPE_DEVICE_INFO = "rope_device_info";
    private static final String SPORT_TARGET_CAL = "sport_target_cal";
    private static final String SPORT_TARGET_DISTANCE = "sport_target_distance";
    private static final String SPORT_TARGET_TIME = "sport_target_time";
    private static final String TAG = "SportContentFragment";
    private static final float TRANS_ALPHA = 0.6f;
    private HealthTextView mAverageData;
    private HealthTextView mAverageDataUnit;
    private HealthTextView mBleConnectStateText;
    private LinearLayout mCalModeLayout;
    private int mCalSelectIndex;
    private View mControlByHeartRateLayout;
    private float mCurrentModeValue;
    private HealthTextView mDataUnit;
    private int mDefaultCal;
    private double mDefaultDistance;
    private CustomAlertDialog mDialogConnectFail;
    private HealthImageView mDistanceImage;
    private LinearLayout mDistanceModeLayout;
    private int mDistanceSelectIndex;
    private HealthTextView mDistanceText;
    private HealthImageView mFreeImage;
    private LinearLayout mFreeModeLayout;
    private HealthTextView mFreeText;
    private HealthImageView mHeatImage;
    private HealthTextView mHeatText;
    private View mHistoryView;
    private HealthTextView mKindName;
    private HealthTextView mLastCount;
    private HealthTextView mLastDay;
    private HealthTextView mLastMonth;
    private HealthTextView mLastTime;
    private djs mMenuItemAreaWidget;
    private LinearLayout mModeSelectLayout;
    private HealthTextView mMonthTotalDesc;
    private SportIntroductionContentPresenter mPresenter;
    private HealthTextView mReConnectTv;
    private HealthProgressBar mReconnectLoadingPb;
    private HealthTextView mSingleSkippingTime;
    private String mSmartProductId;
    private HealthImageView mSportTypeImage;
    private HealthImageView mStartImageView;
    private EcologyDevicePickerManager mTargetDistanceDialog;
    private EcologyDevicePickerManager mTargetHeatDialog;
    private EcologyDevicePickerManager mTargetTimeDialog;
    private HealthImageView mTimeImage;
    private LinearLayout mTimeModeLayout;
    private int mTimeSelectIndex;
    private HealthTextView mTimeText;
    private static final int[] HEAT_TARGET_LIST = {100, 200, 300, 500, 600, 800};
    private static final int[] DISTANCE_TARGET_LIST = {1, 3, 5, 10, -2, -3};
    private static final int[] DISTANCE_TARGET_BIKE_LIST = {1, 3, 5, 20, 30, 40, 120, 180};
    private static final int[] TIME_TARGET_LIST = {10, 20, 30, 60, 120, 180};
    private static final int[] TIME_TARGET_BIKE_LIST = {10, 20, 30, 60, 90, 120, 150, 180};
    private int mType = OldToNewMotionPath.SPORT_TYPE_INDOOR_BIKE;
    private int mCurrentTargetModel = 6;
    private int mDefaultTime = 10;
    private int mConnectCount = 0;

    @Override // com.huawei.health.ecologydevice.ui.measure.view.SportIntroductionContentView
    public void onMonthDataFail(int i) {
    }

    @Override // com.huawei.health.ecologydevice.ui.measure.view.SportIntroductionContentView
    public void queryTrackDetailDataFail() {
    }

    @Override // com.huawei.health.ecologydevice.ui.measure.fragment.SportDeviceIntroductionFragment, com.huawei.health.ecologydevice.ui.BaseFragment
    public boolean onBackPressed() {
        LogUtil.a(TAG, "onBackPressed");
        this.mIndoorEquipManagerApi.releaseResource();
        super.onBackPressed();
        return false;
    }

    @Override // com.huawei.health.ecologydevice.ui.measure.fragment.SportDeviceIntroductionFragment, com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        LogUtil.a(TAG, "onCreate");
        this.mNeedOpenBlueTooth = true;
        int j = this.mSportDataManager.j();
        this.mType = j;
        dhf dhfVar = new dhf(j, this.mUniqueId);
        this.mPresenter = dhfVar;
        dhfVar.onCreate(this);
        LogUtil.c(TAG, "mType:", Integer.valueOf(this.mType));
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.SportIntroductionContentFragment$$ExternalSyntheticLambda9
            @Override // java.lang.Runnable
            public final void run() {
                SportIntroductionContentFragment.this.m354x9b3365d8();
            }
        });
    }

    /* renamed from: lambda$onCreate$0$com-huawei-health-ecologydevice-ui-measure-fragment-SportIntroductionContentFragment, reason: not valid java name */
    /* synthetic */ void m354x9b3365d8() {
        this.mSmartProductId = dij.e(this.mProductId);
    }

    @Override // com.huawei.health.ecologydevice.ui.measure.fragment.SportDeviceIntroductionFragment, com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        view.findViewById(R.id.layout_device_data).setVisibility(8);
        this.mDeviceManualsLayout.setVisibility(8);
        initMenuItem((ViewStub) view.findViewById(R.id.viewstub_item_layout));
        initModel((ViewStub) view.findViewById(R.id.viewstub_model_layout));
        super.onViewCreated(view, bundle);
    }

    @Override // com.huawei.health.ecologydevice.ui.measure.fragment.SportDeviceIntroductionFragment
    protected void initView(View view) {
        super.initView(view);
        this.mStartTv.setVisibility(8);
        HealthTextView healthTextView = (HealthTextView) view.findViewById(R.id.sport_tv_reconnect);
        this.mReConnectTv = healthTextView;
        healthTextView.setOnClickListener(this);
        this.mBleConnectStateText = (HealthTextView) view.findViewById(R.id.sport_connect_state_text);
        this.mReconnectLoadingPb = (HealthProgressBar) view.findViewById(R.id.device_reconnect_pb);
        HealthImageView healthImageView = (HealthImageView) view.findViewById(R.id.sport_start_iv);
        this.mStartImageView = healthImageView;
        healthImageView.setOnClickListener(this);
    }

    @Override // com.huawei.health.ecologydevice.ui.measure.fragment.SportDeviceIntroductionFragment
    protected void initUxConfig(czd czdVar) {
        czd.a aVar;
        boolean z;
        czd.b bVar;
        super.initUxConfig(czdVar);
        LogUtil.a(TAG, "entry initUxConfig");
        if (czdVar != null) {
            controlModeLayoutByUxConfig(czdVar.a());
            aVar = czdVar.c();
            bVar = czdVar.d();
            z = czdVar.h();
        } else {
            aVar = null;
            z = true;
            bVar = null;
        }
        this.mMenuItemAreaWidget.c(initMenu(aVar));
        initControlByHeartRate(((ViewStub) this.child.findViewById(R.id.viewstub_control_by_heart_rate)).inflate(), czdVar);
        initMoreMenuData(bVar);
        if (z) {
            initHistoryLayout();
        }
    }

    private void initMoreMenuData(czd.b bVar) {
        boolean equals;
        boolean z;
        boolean z2 = true;
        if (bVar != null) {
            boolean e = bVar.e();
            z = false;
            equals = bVar.d() != null;
            if (equals && bVar.d().d()) {
                z = true;
            }
            z2 = e;
        } else {
            equals = "yes".equals(dij.c("ota", this.mProductId));
            z = true;
        }
        if (z2) {
            this.mMoreMenuList.add(getResources().getString(R.string.IDS_device_rope_device_info));
        }
        if (equals) {
            this.mMoreMenuList.add(getResources().getString(R.string.IDS_device_wear_home_device_ota_upgrade));
        }
        if (z) {
            setOtaRedPoint();
        }
        if (koq.b(this.mMoreMenuList)) {
            this.mCustomTitleBar.setRightButtonVisibility(8);
            LogUtil.h(TAG, "mMoreMenuList is empty");
        }
    }

    private void controlModeLayoutByUxConfig(czd.e eVar) {
        boolean i;
        boolean a2;
        boolean e;
        boolean z;
        LogUtil.a(TAG, "controlModeLayoutByUxConfig mType is", Integer.valueOf(this.mType));
        int i2 = this.mType;
        if (i2 == 265 || i2 == 264) {
            boolean c = eVar.c();
            i = eVar.i();
            a2 = eVar.a();
            e = eVar.e();
            z = c;
        } else {
            LogUtil.a(TAG, "controlModeLayoutByUxConfig is other type");
            z = true;
            e = true;
            i = true;
            a2 = true;
        }
        this.mModeSelectLayout.removeAllViews();
        if (z) {
            this.mModeSelectLayout.addView(this.mFreeModeLayout);
            this.mFreeModeLayout.setVisibility(0);
        }
        if (i) {
            this.mModeSelectLayout.addView(this.mTimeModeLayout);
            this.mTimeModeLayout.setVisibility(0);
        }
        if (a2) {
            this.mModeSelectLayout.addView(this.mDistanceModeLayout);
            this.mDistanceModeLayout.setVisibility(0);
        }
        if (e) {
            this.mModeSelectLayout.addView(this.mCalModeLayout);
            this.mCalModeLayout.setVisibility(0);
        }
        if (this.mModeSelectLayout.getChildCount() < 2) {
            this.child.findViewById(R.id.mode_choose_layout).setVisibility(8);
            this.mModeValueTv.setVisibility(8);
        }
    }

    @Override // com.huawei.health.ecologydevice.ui.measure.fragment.SportDeviceIntroductionFragment, com.huawei.health.ecologydevice.ui.measure.fragment.ProductFragment, com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        LogUtil.a(TAG, "onResume");
        if (this.mHistoryView != null) {
            this.mPresenter.getLastSportRecord(this.mType);
        }
    }

    private void initModel(ViewStub viewStub) {
        this.mModeLayout.setVisibility(0);
        View inflate = viewStub.inflate();
        this.mFreeImage = (HealthImageView) inflate.findViewById(R.id.free_jump_iv);
        this.mFreeText = (HealthTextView) inflate.findViewById(R.id.free_jump_tv);
        this.mModeSelectLayout = (LinearLayout) inflate.findViewById(R.id.sport_mode_select_ll);
        LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.free_jump_layout);
        this.mFreeModeLayout = linearLayout;
        linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.SportIntroductionContentFragment$$ExternalSyntheticLambda10
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SportIntroductionContentFragment.this.m350x4b6d0893(view);
            }
        });
        this.mFreeText.setText(this.mType == 265 ? R.string._2130850497_res_0x7f0232c1 : R.string._2130850498_res_0x7f0232c2);
        this.mTimeImage = (HealthImageView) inflate.findViewById(R.id.time_jump_iv);
        this.mTimeText = (HealthTextView) inflate.findViewById(R.id.time_jump_tv);
        LinearLayout linearLayout2 = (LinearLayout) inflate.findViewById(R.id.time_jump_layout);
        this.mTimeModeLayout = linearLayout2;
        linearLayout2.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.SportIntroductionContentFragment$$ExternalSyntheticLambda11
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SportIntroductionContentFragment.this.m351x910e4b32(view);
            }
        });
        this.mTimeText.setText(this.mType == 265 ? R.string._2130850501_res_0x7f0232c5 : R.string._2130850502_res_0x7f0232c6);
        this.mDistanceImage = (HealthImageView) inflate.findViewById(R.id.number_jump_iv);
        this.mDistanceText = (HealthTextView) inflate.findViewById(R.id.number_jump_tv);
        LinearLayout linearLayout3 = (LinearLayout) inflate.findViewById(R.id.number_jump_layout);
        this.mDistanceModeLayout = linearLayout3;
        linearLayout3.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.SportIntroductionContentFragment$$ExternalSyntheticLambda12
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SportIntroductionContentFragment.this.m352xd6af8dd1(view);
            }
        });
        this.mDistanceText.setText(this.mType == 265 ? R.string._2130850495_res_0x7f0232bf : R.string._2130850496_res_0x7f0232c0);
        this.mHeatImage = (HealthImageView) inflate.findViewById(R.id.heat_jump_iv);
        this.mHeatText = (HealthTextView) inflate.findViewById(R.id.heat_jump_tv);
        LinearLayout linearLayout4 = (LinearLayout) inflate.findViewById(R.id.heat_jump_layout);
        this.mCalModeLayout = linearLayout4;
        linearLayout4.setVisibility(0);
        this.mCalModeLayout.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.SportIntroductionContentFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SportIntroductionContentFragment.this.m353x1c50d070(view);
            }
        });
        this.mHeatText.setText(this.mType == 265 ? R.string._2130850499_res_0x7f0232c3 : R.string._2130850500_res_0x7f0232c4);
        refreshRopeModeLayoutUi(6, "");
    }

    /* renamed from: lambda$initModel$1$com-huawei-health-ecologydevice-ui-measure-fragment-SportIntroductionContentFragment, reason: not valid java name */
    /* synthetic */ void m350x4b6d0893(View view) {
        LogUtil.c(TAG, "自由跑模式");
        refreshRopeModeLayoutUi(6, "");
        this.mPresenter.setModeSelectBiEvent(this.mProductId, 0, "0", this.mType);
        ViewClickInstrumentation.clickOnView(view);
    }

    /* renamed from: lambda$initModel$2$com-huawei-health-ecologydevice-ui-measure-fragment-SportIntroductionContentFragment, reason: not valid java name */
    /* synthetic */ void m351x910e4b32(View view) {
        timeModelClick();
        ViewClickInstrumentation.clickOnView(view);
    }

    /* renamed from: lambda$initModel$3$com-huawei-health-ecologydevice-ui-measure-fragment-SportIntroductionContentFragment, reason: not valid java name */
    /* synthetic */ void m352xd6af8dd1(View view) {
        distanceModelClick();
        ViewClickInstrumentation.clickOnView(view);
    }

    /* renamed from: lambda$initModel$4$com-huawei-health-ecologydevice-ui-measure-fragment-SportIntroductionContentFragment, reason: not valid java name */
    /* synthetic */ void m353x1c50d070(View view) {
        heatModelClick();
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // com.huawei.health.ecologydevice.ui.measure.fragment.ProductFragment, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        LogUtil.a(TAG, "onStart");
    }

    @Override // androidx.fragment.app.Fragment
    public void onDetach() {
        this.mPresenter.onDetach();
        super.onDetach();
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        LogUtil.a(TAG, "onDestroy");
        djt.e().d();
    }

    private void refreshRopeModeLayoutUi(int i, String str) {
        this.mCurrentTargetModel = i;
        LogUtil.a(TAG, "refreshRopeModeLayoutUi currentTargetModle is ", Integer.valueOf(i));
        if (this.mCurrentTargetModel == 6) {
            this.mModeTv.setVisibility(8);
            this.mModeValueTv.setText(getString(this.mType == 265 ? R.string._2130850497_res_0x7f0232c1 : R.string._2130850498_res_0x7f0232c2));
            this.mFreeImage.setImageResource(this.mType == 265 ? R.drawable._2131430466_res_0x7f0b0c42 : R.drawable._2131430468_res_0x7f0b0c44);
        } else {
            this.mModeTv.setVisibility(0);
            this.mModeValueTv.setText(str);
            this.mFreeImage.setImageDrawable(getTintDrawable(this.mType == 265 ? R.drawable._2131430465_res_0x7f0b0c41 : R.drawable._2131430467_res_0x7f0b0c43, R.color._2131299011_res_0x7f090ac3));
        }
        if (this.mCurrentTargetModel == 0) {
            this.mModeTv.setText(getString(this.mType == 265 ? R.string._2130850501_res_0x7f0232c5 : R.string._2130850502_res_0x7f0232c6));
            this.mTimeImage.setImageResource(R.drawable._2131430472_res_0x7f0b0c48);
        } else {
            this.mTimeImage.setImageDrawable(getTintDrawable(R.drawable._2131430471_res_0x7f0b0c47, R.color._2131299011_res_0x7f090ac3));
        }
        if (this.mCurrentTargetModel == 1) {
            this.mModeTv.setText(getString(this.mType == 265 ? R.string._2130850495_res_0x7f0232bf : R.string._2130850496_res_0x7f0232c0));
            this.mDistanceImage.setImageResource(R.drawable._2131430464_res_0x7f0b0c40);
        } else {
            this.mDistanceImage.setImageDrawable(getTintDrawable(R.drawable._2131430463_res_0x7f0b0c3f, R.color._2131299011_res_0x7f090ac3));
        }
        if (this.mCurrentTargetModel == 2) {
            this.mModeTv.setText(getString(this.mType == 265 ? R.string._2130850499_res_0x7f0232c3 : R.string._2130850500_res_0x7f0232c4));
            this.mHeatImage.setImageResource(R.drawable._2131430470_res_0x7f0b0c46);
        } else {
            this.mHeatImage.setImageDrawable(getTintDrawable(R.drawable._2131430469_res_0x7f0b0c45, R.color._2131299011_res_0x7f090ac3));
        }
    }

    private Drawable getTintDrawable(int i, int i2) {
        Drawable cJH_ = nrf.cJH_(ContextCompat.getDrawable(this.mainActivity, i), ContextCompat.getColor(this.mainActivity, i2));
        return LanguageUtil.bc(this.mainActivity) ? nrz.cKm_(this.mainActivity, cJH_) : cJH_;
    }

    @Override // com.huawei.health.ecologydevice.ui.measure.fragment.SportDeviceIntroductionFragment
    protected Intent createEquipConnectIntent(Bundle bundle) {
        LogUtil.a(TAG, "createEquipConnectIntent.");
        Intent createEquipConnectIntent = super.createEquipConnectIntent(bundle);
        createEquipConnectIntent.putExtra(WorkoutRecord.Extend.COURSE_TARGET_TYPE, this.mCurrentTargetModel);
        createEquipConnectIntent.putExtra(WorkoutRecord.Extend.COURSE_TARGET_VALUE, this.mCurrentModeValue);
        DeviceInformation deviceInformation = this.mPresenter.getDeviceInformation();
        createEquipConnectIntent.putExtra("sn", deviceInformation.getSerialNumber());
        createEquipConnectIntent.putExtra(ProfileRequestConstants.MANU, deviceInformation.getManufacturerString());
        createEquipConnectIntent.putExtra("model", deviceInformation.getModelString());
        createEquipConnectIntent.putExtras(bundle);
        return createEquipConnectIntent;
    }

    private void initMenuItem(ViewStub viewStub) {
        djs djsVar = new djs((HealthRecycleView) viewStub.inflate().findViewById(R.id.rv_second_menu_item));
        this.mMenuItemAreaWidget = djsVar;
        djsVar.d(this);
    }

    private void heatModelClick() {
        if (this.mTargetHeatDialog == null) {
            this.mDefaultCal = this.mPresenter.getTargetCacheValue(SPORT_TARGET_CAL).intValue();
            int i = 0;
            this.mCalSelectIndex = 0;
            while (true) {
                int[] iArr = HEAT_TARGET_LIST;
                if (i >= iArr.length) {
                    break;
                }
                if (this.mDefaultCal == iArr[i]) {
                    this.mCalSelectIndex = i + 1;
                    break;
                }
                i++;
            }
            this.mTargetHeatDialog = new EcologyDevicePickerManager.Builder(this.mainActivity).e(5000.0d).a(100.0d).c(this.mDefaultCal).c(this.mainActivity.getString(this.mType == 265 ? R.string._2130850499_res_0x7f0232c3 : R.string._2130850500_res_0x7f0232c4)).e(R.string._2130842526_res_0x7f02139e).c(new PickerChooseCallback() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.SportIntroductionContentFragment$$ExternalSyntheticLambda7
                @Override // com.huawei.health.ecologydevice.callback.PickerChooseCallback
                public final void onValueSelected(Object obj, int i2) {
                    SportIntroductionContentFragment.this.m346xb765ffff((Integer) obj, i2);
                }
            }).e();
        }
        this.mTargetHeatDialog.b(HEAT_TARGET_LIST, 2, R.plurals._2130903509_res_0x7f0301d5, this.mCalSelectIndex);
    }

    /* renamed from: lambda$heatModelClick$5$com-huawei-health-ecologydevice-ui-measure-fragment-SportIntroductionContentFragment, reason: not valid java name */
    /* synthetic */ void m346xb765ffff(Integer num, int i) {
        LogUtil.c(TAG, "热量跑模式", num, ",", Integer.valueOf(i));
        this.mPresenter.saveTargetData(String.valueOf(num), SPORT_TARGET_CAL);
        refreshRopeModeLayoutUi(2, getResources().getQuantityString(R.plurals._2130903509_res_0x7f0301d5, num.intValue(), String.valueOf(num)));
        this.mCurrentModeValue = num.intValue();
        this.mDefaultCal = num.intValue();
        this.mPresenter.setModeSelectBiEvent(this.mProductId, 3, String.valueOf(num), this.mType);
    }

    private void distanceModelClick() {
        if (this.mTargetDistanceDialog == null) {
            setDefaultDistanceValue();
            this.mTargetDistanceDialog = new EcologyDevicePickerManager.Builder(this.mainActivity).e(this.mType == 265 ? 200.0d : 100.0d).a(0.1d).c(this.mDefaultDistance).c(this.mainActivity.getString(this.mType == 265 ? R.string._2130850495_res_0x7f0232bf : R.string._2130850496_res_0x7f0232c0)).e(R.string._2130842526_res_0x7f02139e).c(new PickerChooseCallback() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.SportIntroductionContentFragment$$ExternalSyntheticLambda8
                @Override // com.huawei.health.ecologydevice.callback.PickerChooseCallback
                public final void onValueSelected(Object obj, int i) {
                    SportIntroductionContentFragment.this.m345x5d7797b9((Double) obj, i);
                }
            }).e();
        }
        this.mTargetDistanceDialog.b(this.mType == 265 ? DISTANCE_TARGET_BIKE_LIST : DISTANCE_TARGET_LIST, 1, R.plurals._2130903301_res_0x7f030105, this.mDistanceSelectIndex);
    }

    /* renamed from: lambda$distanceModelClick$6$com-huawei-health-ecologydevice-ui-measure-fragment-SportIntroductionContentFragment, reason: not valid java name */
    /* synthetic */ void m345x5d7797b9(Double d, int i) {
        String string;
        LogUtil.c(TAG, "距离跑模式", d, ",", Integer.valueOf(i));
        if (d.doubleValue() == -2.0d || d.doubleValue() == 21.0975d) {
            this.mCurrentModeValue = new BigDecimal(21.0975d).floatValue();
            string = getString(R.string._2130841792_res_0x7f0210c0);
        } else if (d.doubleValue() == -3.0d || d.doubleValue() == 42.195d) {
            this.mCurrentModeValue = new BigDecimal(42.195d).floatValue();
            string = getString(R.string._2130841793_res_0x7f0210c1);
        } else {
            this.mCurrentModeValue = d.floatValue();
            string = getResources().getQuantityString(R.plurals._2130903301_res_0x7f030105, d.intValue(), String.valueOf(d));
        }
        this.mDefaultDistance = d.doubleValue();
        this.mPresenter.setModeSelectBiEvent(this.mProductId, 2, String.valueOf(this.mCurrentModeValue), this.mType);
        this.mCurrentModeValue *= 1000.0f;
        this.mPresenter.saveTargetData(String.valueOf(this.mDefaultDistance), SPORT_TARGET_DISTANCE);
        refreshRopeModeLayoutUi(1, string);
    }

    private void setDefaultDistanceValue() {
        this.mDefaultDistance = this.mPresenter.getTargetCacheValue(SPORT_TARGET_DISTANCE).doubleValue();
        int i = 0;
        this.mDistanceSelectIndex = 0;
        int[] iArr = this.mType == 265 ? DISTANCE_TARGET_BIKE_LIST : DISTANCE_TARGET_LIST;
        while (true) {
            if (i >= iArr.length) {
                break;
            }
            if (this.mDefaultDistance == iArr[i]) {
                this.mDistanceSelectIndex = i + 1;
                break;
            }
            i++;
        }
        double d = this.mDefaultDistance;
        if (d == -2.0d) {
            this.mDefaultDistance = 21.0975d;
        } else if (d == -3.0d) {
            this.mDefaultDistance = 42.195d;
        } else {
            LogUtil.c(TAG, "setDefaultDistanceValue else");
        }
    }

    private void timeModelClick() {
        if (this.mTargetTimeDialog == null) {
            this.mDefaultTime = this.mPresenter.getTargetCacheValue(SPORT_TARGET_TIME).intValue();
            int i = 0;
            this.mTimeSelectIndex = 0;
            int[] iArr = this.mType == 265 ? TIME_TARGET_BIKE_LIST : TIME_TARGET_LIST;
            while (true) {
                if (i >= iArr.length) {
                    break;
                }
                if (this.mDefaultTime == iArr[i]) {
                    this.mTimeSelectIndex = i + 1;
                    break;
                }
                i++;
            }
            this.mTargetTimeDialog = new EcologyDevicePickerManager.Builder(this.mainActivity).c(this.mainActivity.getString(this.mType == 265 ? R.string._2130850501_res_0x7f0232c5 : R.string._2130850502_res_0x7f0232c6)).e(R.string._2130842526_res_0x7f02139e).e(1080.0d).a(10.0d).c(this.mDefaultTime).c(new PickerChooseCallback() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.SportIntroductionContentFragment$$ExternalSyntheticLambda6
                @Override // com.huawei.health.ecologydevice.callback.PickerChooseCallback
                public final void onValueSelected(Object obj, int i2) {
                    SportIntroductionContentFragment.this.m357x16583800((Integer) obj, i2);
                }
            }).e();
        }
        this.mTargetTimeDialog.b(this.mType == 265 ? TIME_TARGET_BIKE_LIST : TIME_TARGET_LIST, 5, R.plurals._2130903200_res_0x7f0300a0, this.mTimeSelectIndex);
    }

    /* renamed from: lambda$timeModelClick$7$com-huawei-health-ecologydevice-ui-measure-fragment-SportIntroductionContentFragment, reason: not valid java name */
    /* synthetic */ void m357x16583800(Integer num, int i) {
        LogUtil.h(TAG, "timeJumpClick value", num, ",", Integer.valueOf(i));
        this.mPresenter.saveTargetData(String.valueOf(num), SPORT_TARGET_TIME);
        refreshRopeModeLayoutUi(0, UnitUtil.d(num.intValue() * 60));
        this.mCurrentModeValue = num.intValue() * 60.0f;
        this.mDefaultTime = num.intValue();
        this.mPresenter.setModeSelectBiEvent(this.mProductId, 1, String.valueOf(num), this.mType);
    }

    private dhe convertItemData(int i, int i2, int i3) {
        dhe dheVar = new dhe();
        dheVar.d(getResources().getString(i));
        dheVar.d(i2);
        dheVar.e(i3);
        dheVar.d(true);
        return dheVar;
    }

    private List<dhe> initMenu(czd.a aVar) {
        boolean z;
        boolean z2;
        if (aVar != null) {
            z2 = aVar.f();
            z = aVar.c();
        } else {
            z = true;
            z2 = true;
        }
        ArrayList arrayList = new ArrayList();
        if (LanguageUtil.m(BaseApplication.getContext()) && z2) {
            arrayList.add(convertItemData(R.string._2130842050_res_0x7f0211c2, R.drawable.hw_health_device_warm_up, 0));
        }
        if (gwg.i(BaseApplication.getContext()) && CommonUtil.bd() && z) {
            arrayList.add(convertItemData(R.string._2130842049_res_0x7f0211c1, R.drawable.hw_health_device_music, 1));
        }
        return arrayList;
    }

    private void initControlByHeartRate(View view, czd czdVar) {
        boolean equals;
        if (czdVar != null) {
            equals = czdVar.e();
        } else {
            equals = "true".equals(this.mProductInfo.c("isSupportControl"));
        }
        if (equals) {
            this.mControlByHeartRateLayout = view.findViewById(R.id.second_rope_item_layout);
            HealthTextView healthTextView = (HealthTextView) view.findViewById(R.id.second_rope_item_title);
            HealthTextView healthTextView2 = (HealthTextView) view.findViewById(R.id.second_rope_item_description);
            HealthImageView healthImageView = (HealthImageView) view.findViewById(R.id.second_rope_item_img);
            healthTextView.setText(getResources().getString(this.mType == 265 ? R.string._2130850367_res_0x7f02323f : R.string._2130850369_res_0x7f023241));
            healthTextView2.setText(getResources().getString(R.string._2130850370_res_0x7f023242));
            healthTextView2.setVisibility(0);
            healthImageView.setBackgroundResource(R.drawable._2131427842_res_0x7f0b0202);
            view.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.SportIntroductionContentFragment$$ExternalSyntheticLambda5
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    SportIntroductionContentFragment.this.m347xf4051229(view2);
                }
            });
            return;
        }
        view.setVisibility(8);
    }

    /* renamed from: lambda$initControlByHeartRate$8$com-huawei-health-ecologydevice-ui-measure-fragment-SportIntroductionContentFragment, reason: not valid java name */
    /* synthetic */ void m347xf4051229(View view) {
        jumpToRateControl();
        this.mPresenter.setSubPageBiEvent(this.mProductId, this.mUniqueId, "hrmode", this.mType);
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // com.huawei.health.ecologydevice.ui.measure.fragment.SportDeviceIntroductionFragment
    protected void handleMoreMenuClick(int i) {
        super.handleMoreMenuClick(i);
        String str = this.mMoreMenuList.get(i);
        if (TextUtils.equals(str, getResources().getString(R.string.IDS_device_rope_device_info))) {
            goToDeviceInfoFragment();
        } else {
            if (TextUtils.equals(str, getResources().getString(R.string.IDS_device_wear_home_device_ota_upgrade))) {
                dks.d(this.mainActivity, this.mProductInfo, this.mProductId, this.mUniqueId);
                LogUtil.a(TAG, "handleMoreMenuClick disconnect device");
                this.mPresenter.disconnect(false);
                return;
            }
            LogUtil.h(TAG, "handleMoreMenuClick item is other");
        }
    }

    private void setOtaRedPoint() {
        if (koq.b(this.mMoreMenuList)) {
            LogUtil.b(TAG, "setOtaRedPoint mMoreMenuList is empty");
        } else if (hasNewOtaVersion()) {
            if (!this.mMoreMenuList.contains(File.separator)) {
                this.mMoreMenuList.add(File.separator);
            }
            this.mMoreMenuList.add(getResources().getString(R.string.IDS_device_wear_home_device_ota_upgrade));
        }
    }

    private boolean hasNewOtaVersion() {
        DeviceInformation deviceInformation = this.mPresenter.getDeviceInformation();
        if (this.mProductInfo == null || deviceInformation == null) {
            return false;
        }
        int otaVersion = getOtaVersion(deviceInformation.getSoftwareVersion());
        String c = this.mProductInfo.c("otaVersion");
        if (TextUtils.isEmpty(c)) {
            return false;
        }
        ReleaseLogUtil.e(RELEASE_TAG, "softwareVersion = ", Integer.valueOf(otaVersion), " productInfoOtaVersion = ", c);
        return otaVersion < getOtaVersion(c);
    }

    private int getOtaVersion(String str) {
        return nsn.e(str.toLowerCase(Locale.ENGLISH).replace(FitRunPlayAudio.PLAY_TYPE_V, "").replace(".", ""));
    }

    private void goToDeviceInfoFragment() {
        DeviceInformation deviceInformation = this.mPresenter.getDeviceInformation();
        com.huawei.health.ecologydevice.fitness.datastruct.DeviceInformation deviceInformation2 = new com.huawei.health.ecologydevice.fitness.datastruct.DeviceInformation();
        deviceInformation2.setManufacturerString(deviceInformation.getManufacturerString());
        deviceInformation2.setSerialNumber(deviceInformation.getSerialNumber());
        deviceInformation2.setSoftwareVersion(deviceInformation.getSoftwareVersion());
        deviceInformation2.setModelString(deviceInformation.getModelString());
        Bundle bundle = new Bundle();
        bundle.putParcelable(ROPE_DEVICE_INFO, deviceInformation2);
        bundle.putString("productId", this.mProductId);
        bundle.putString("uniqueId", this.mUniqueId);
        Fragment deviceInfoFragment = new DeviceInfoFragment();
        deviceInfoFragment.setArguments(bundle);
        addFragment(deviceInfoFragment);
    }

    @Override // com.huawei.ui.commonui.adapter.BaseRecyclerAdapter.OnItemClickListener
    public void onItemClicked(RecyclerHolder recyclerHolder, int i, dhe dheVar) {
        if (nsn.o()) {
            LogUtil.h(TAG, "onItemClicked click too fast");
            return;
        }
        int b = dheVar.b();
        if (b == 0) {
            gotoWarmUp(getContext(), this.mType, this.mCurrentTargetModel, this.mCurrentModeValue);
            this.mPresenter.setSubPageBiEvent(this.mProductId, this.mUniqueId, "warm_up", this.mType);
        } else if (b == 1) {
            diy.b(this.mainActivity, this.mType);
            djr.c().a(true);
            this.mPresenter.setSubPageBiEvent(this.mProductId, this.mUniqueId, "music", this.mType);
        } else {
            if (b != 2) {
                return;
            }
            jumpToRateControl();
            this.mPresenter.setSubPageBiEvent(this.mProductId, this.mUniqueId, "hrmode", this.mType);
        }
    }

    private void gotoWarmUp(Context context, int i, int i2, float f) {
        LogUtil.a(TAG, "gotoWarmUp, sportType = ", Integer.valueOf(i));
        PluginSuggestion pluginSuggestion = (PluginSuggestion) Services.a("PluginFitnessAdvice", PluginSuggestion.class);
        if (pluginSuggestion != null && pluginSuggestion.isInitComplete() && Utils.j()) {
            if (context == null) {
                LogUtil.h(TAG, "gotoWarmUp context is null");
                return;
            }
            String str = this.mType == 265 ? "R011" : "R001";
            mmp mmpVar = new mmp(str);
            mmpVar.c(R.anim._2130772059_res_0x7f01005b);
            mmpVar.d(R.anim._2130771981_res_0x7f01000d);
            mmpVar.j(false);
            mmpVar.a(false);
            mmpVar.e(1);
            mod.c(context, mmpVar, getWorkoutRecordList(str));
            HashMap hashMap = new HashMap(2);
            hashMap.put("click", 1);
            hashMap.put("type", 0);
            ixx.d().d(BaseApplication.getContext(), AnalyticsValue.MOTION_TRACK_1040021.value(), hashMap, 0);
        }
    }

    private ArrayList<WorkoutRecord> getWorkoutRecordList(String str) {
        WorkoutRecord workoutRecord = new WorkoutRecord();
        workoutRecord.saveVersion(null);
        workoutRecord.saveExerciseTime(new Date().getTime());
        workoutRecord.saveWorkoutId(str);
        workoutRecord.savePlanId("");
        ArrayList<WorkoutRecord> arrayList = new ArrayList<>(1);
        arrayList.add(workoutRecord);
        return arrayList;
    }

    private void jumpToRateControl() {
        Bundle bundle = new Bundle();
        bundle.putString("productId", this.mProductId);
        bundle.putString("name", this.mDeviceName);
        bundle.putString("uniqueId", this.mUniqueId);
        bundle.putInt("deviceType", this.mType);
        DeviceInformation deviceInformation = this.mPresenter.getDeviceInformation();
        bundle.putString("sn", deviceInformation.getSerialNumber());
        bundle.putString(ProfileRequestConstants.MANU, deviceInformation.getManufacturerString());
        bundle.putString("model", deviceInformation.getModelString());
        bundle.putString("productId", this.mSmartProductId);
        if (this.mPresenter.isNeedShowDescription(this.mUniqueId)) {
            RateControlDescriptionFragment rateControlDescriptionFragment = new RateControlDescriptionFragment();
            rateControlDescriptionFragment.setArguments(bundle);
            addFragment(rateControlDescriptionFragment);
        } else {
            RateControlFragment rateControlFragment = new RateControlFragment();
            rateControlFragment.setArguments(bundle);
            addFragment(rateControlFragment);
        }
    }

    @Override // com.huawei.health.ecologydevice.ui.measure.fragment.SportDeviceIntroductionFragment, android.view.View.OnClickListener
    public void onClick(View view) {
        super.onClick(view);
        if (view == this.mStartImageView) {
            LogUtil.c(TAG, "onClick mStartImageView start training.");
            startIndoorEquipActivity();
        } else if (view == this.mReConnectTv) {
            connectToDevice();
        } else {
            LogUtil.c(TAG, "onClick unknown view");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // com.huawei.health.ecologydevice.ui.measure.fragment.SportDeviceIntroductionFragment
    protected void unBindDeviceSuccess(Map<String, Object> map) {
        this.mPresenter.removeNoShowDescriptionMac(this.mUniqueId);
        this.mPresenter.removeModeCache();
        this.mIndoorEquipManagerApi.releaseResource();
        super.unBindDeviceSuccess(map);
    }

    @Override // com.huawei.health.ecologydevice.ui.measure.view.SportIntroductionContentView
    public void onGetLastHistoryDataFail(int i) {
        View view;
        if (i != 0 || (view = this.mHistoryView) == null) {
            return;
        }
        view.setVisibility(8);
    }

    @Override // com.huawei.health.ecologydevice.ui.measure.view.SportIntroductionContentView
    public void onGetLastHistoryDataSuccess(HiTrackMetaData hiTrackMetaData, long j) {
        if (!isAdded()) {
            LogUtil.h(TAG, "onGetLastHistoryDataSuccess isAdded is false");
            return;
        }
        if (this.mHistoryView == null) {
            LogUtil.h(TAG, "onGetLastHistoryDataSuccess mHistoryView is null");
            return;
        }
        if (hiTrackMetaData == null) {
            LogUtil.h(TAG, "refreshLastDataUi trackMetaData is null");
            return;
        }
        Map<String, String> extendTrackMap = hiTrackMetaData.getExtendTrackMap();
        if (extendTrackMap == null || extendTrackMap.size() == 0) {
            LogUtil.h(TAG, "onGetLastHistoryDataSuccess extendTrackDataMap is null or size is 0");
            return;
        }
        if (this.mHistoryView.getVisibility() != 0) {
            this.mHistoryView.setVisibility(0);
        }
        if (hiTrackMetaData.getAvgPace() <= 0.0f) {
            this.mAverageData.setText("--");
        } else {
            this.mAverageData.setText(UnitUtil.e(kpj.d(false, 3, (1.0f / hiTrackMetaData.getAvgPace()) * 3600.0f), 1, 2));
        }
        this.mAverageDataUnit.setText(getString(R.string._2130844078_res_0x7f0219ae));
        this.mLastCount.setText(UnitUtil.e(hiTrackMetaData.getTotalDistance() / 1000.0d, 1, 2));
        this.mDataUnit.setText(this.mainActivity.getResources().getQuantityString(R.plurals._2130903301_res_0x7f030105, 0, ""));
        this.mSingleSkippingTime.setText(UnitUtil.d((int) (hiTrackMetaData.getTotalTime() / 1000)));
        this.mLastTime.setText(new SimpleDateFormat(FORMAT_HOUR_MIN, Locale.getDefault()).format(Long.valueOf(j)));
        this.mLastDay.setText(DateUtils.formatDateTime(this.mainActivity, j, 8));
        this.mSportTypeImage.setBackground(nrf.cJH_(getResources().getDrawable(R.drawable._2131431552_res_0x7f0b1080), getResources().getColor(R.color._2131298953_res_0x7f090a89)));
        this.mSportTypeImage.setImageResource(this.mType == 265 ? R.drawable.ic_health_list_indoor_riding : R.drawable.ic_health_list_indoor_running);
        this.mKindName.setText(this.mType == 265 ? R.string.IDS_hwh_motiontrack_indoor_cycling : R.string.IDS_start_track_sport_type_indoor_run);
        this.mPresenter.getLastMonthRecord(this.mType, j);
    }

    private void initHistoryLayout() {
        View inflate = ((ViewStub) this.child.findViewById(R.id.viewstub_last_record_layout)).inflate();
        this.mHistoryView = inflate;
        this.mLastCount = (HealthTextView) inflate.findViewById(R.id.activity_last_count);
        this.mDataUnit = (HealthTextView) this.mHistoryView.findViewById(R.id.activity_data_unit);
        this.mAverageData = (HealthTextView) this.mHistoryView.findViewById(R.id.activity_average_data);
        this.mSingleSkippingTime = (HealthTextView) this.mHistoryView.findViewById(R.id.single_skipping_time);
        this.mSportTypeImage = (HealthImageView) this.mHistoryView.findViewById(R.id.activity_img);
        this.mLastTime = (HealthTextView) this.mHistoryView.findViewById(R.id.activity_last_time);
        this.mLastDay = (HealthTextView) this.mHistoryView.findViewById(R.id.activity_last_day);
        this.mMonthTotalDesc = (HealthTextView) this.mHistoryView.findViewById(R.id.activity_month_total_desc);
        this.mLastMonth = (HealthTextView) this.mHistoryView.findViewById(R.id.activity_last_month);
        this.mAverageDataUnit = (HealthTextView) this.mHistoryView.findViewById(R.id.activity_average_data_unit);
        this.mKindName = (HealthTextView) this.mHistoryView.findViewById(R.id.kind_name);
        this.child.findViewById(R.id.last_activity_layout).setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.SportIntroductionContentFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SportIntroductionContentFragment.this.m349x35515b36(view);
            }
        });
        this.child.findViewById(R.id.sport_month_data_layout).setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.SportIntroductionContentFragment$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SportIntroductionContentFragment.this.m348xf56e478a(view);
            }
        });
    }

    /* renamed from: lambda$initHistoryLayout$9$com-huawei-health-ecologydevice-ui-measure-fragment-SportIntroductionContentFragment, reason: not valid java name */
    /* synthetic */ void m349x35515b36(View view) {
        this.mPresenter.queryTrackDetailData();
        ViewClickInstrumentation.clickOnView(view);
    }

    /* renamed from: lambda$initHistoryLayout$10$com-huawei-health-ecologydevice-ui-measure-fragment-SportIntroductionContentFragment, reason: not valid java name */
    /* synthetic */ void m348xf56e478a(View view) {
        diy.d(this.mainActivity, this.mType);
        this.mPresenter.setSubPageBiEvent(this.mProductId, this.mUniqueId, "history_list", this.mType);
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // com.huawei.health.ecologydevice.ui.measure.view.SportIntroductionContentView
    public String getProductId() {
        return this.mProductId;
    }

    @Override // com.huawei.health.ecologydevice.ui.measure.view.SportIntroductionContentView
    public void onMonthDataSuccess(String str, String str2) {
        this.mMonthTotalDesc.setText(str);
        this.mLastMonth.setText(str2);
    }

    @Override // com.huawei.health.ecologydevice.ui.measure.view.SportIntroductionContentView
    public void queryTrackDetailDataSuccess(MotionPathSimplify motionPathSimplify, String str) {
        goToTrackDetailActivity(motionPathSimplify, str);
        this.mPresenter.setSubPageBiEvent(this.mProductId, this.mUniqueId, "history_detail", this.mType);
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x002c, code lost:
    
        if (r7 != 3) goto L24;
     */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0087  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0098  */
    @Override // com.huawei.health.ecologydevice.ui.measure.view.SportIntroductionContentView
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onDeviceBleStateChanged(int r7) {
        /*
            r6 = this;
            android.content.Context r0 = r6.getContext()
            java.lang.String r1 = "SportContentFragment"
            if (r0 != 0) goto L12
            java.lang.String r7 = "onDeviceBleStateChanged getContext  is null"
            java.lang.Object[] r7 = new java.lang.Object[]{r7}
            com.huawei.hwlogsmodel.LogUtil.h(r1, r7)
            return
        L12:
            boolean r0 = r6.isRemoving()
            if (r0 != 0) goto La2
            boolean r0 = r6.isDetached()
            if (r0 == 0) goto L20
            goto La2
        L20:
            r0 = 3
            r2 = 0
            r3 = 8
            if (r7 == 0) goto L62
            r4 = 1
            if (r7 == r4) goto L4a
            r5 = 2
            if (r7 == r5) goto L2f
            if (r7 == r0) goto L62
            goto L80
        L2f:
            com.huawei.ui.commonui.healthtextview.HealthTextView r7 = r6.mBleConnectStateText
            r0 = 2130849682(0x7f022f92, float:1.7304664E38)
            r7.setText(r0)
            com.huawei.ui.commonui.healthtextview.HealthTextView r7 = r6.mReConnectTv
            r7.setVisibility(r3)
            com.huawei.ui.commonui.imageview.HealthImageView r7 = r6.mStartImageView
            r7.setVisibility(r2)
            com.huawei.ui.commonui.progressbar.HealthProgressBar r7 = r6.mReconnectLoadingPb
            r7.setVisibility(r3)
            r6.mConnectCount = r2
            r2 = r4
            goto L80
        L4a:
            com.huawei.ui.commonui.healthtextview.HealthTextView r7 = r6.mBleConnectStateText
            r0 = 2130849683(0x7f022f93, float:1.7304666E38)
            r7.setText(r0)
            com.huawei.ui.commonui.healthtextview.HealthTextView r7 = r6.mReConnectTv
            r7.setVisibility(r3)
            com.huawei.ui.commonui.imageview.HealthImageView r7 = r6.mStartImageView
            r7.setVisibility(r3)
            com.huawei.ui.commonui.progressbar.HealthProgressBar r7 = r6.mReconnectLoadingPb
            r7.setVisibility(r2)
            goto L80
        L62:
            com.huawei.ui.commonui.healthtextview.HealthTextView r7 = r6.mBleConnectStateText
            r4 = 2130849690(0x7f022f9a, float:1.730468E38)
            r7.setText(r4)
            com.huawei.ui.commonui.healthtextview.HealthTextView r7 = r6.mReConnectTv
            r7.setVisibility(r2)
            com.huawei.ui.commonui.imageview.HealthImageView r7 = r6.mStartImageView
            r7.setVisibility(r3)
            com.huawei.ui.commonui.progressbar.HealthProgressBar r7 = r6.mReconnectLoadingPb
            r7.setVisibility(r3)
            int r7 = r6.mConnectCount
            if (r7 != r0) goto L80
            r6.showReconnectCountDialog()
        L80:
            r6.setViewClickable(r2)
            android.view.View r7 = r6.mControlByHeartRateLayout
            if (r7 == 0) goto L98
            if (r2 == 0) goto L8c
            r0 = 1065353216(0x3f800000, float:1.0)
            goto L8f
        L8c:
            r0 = 1058642330(0x3f19999a, float:0.6)
        L8f:
            r7.setAlpha(r0)
            android.view.View r7 = r6.mControlByHeartRateLayout
            r7.setClickable(r2)
            goto La1
        L98:
            java.lang.String r7 = "mControlByHeartRateLayout is null"
            java.lang.Object[] r7 = new java.lang.Object[]{r7}
            com.huawei.hwlogsmodel.LogUtil.h(r1, r7)
        La1:
            return
        La2:
            java.lang.String r7 = "onDeviceBleStateChanged isRemoving"
            java.lang.Object[] r7 = new java.lang.Object[]{r7}
            com.huawei.hwlogsmodel.LogUtil.h(r1, r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.health.ecologydevice.ui.measure.fragment.SportIntroductionContentFragment.onDeviceBleStateChanged(int):void");
    }

    @Override // com.huawei.health.ecologydevice.ui.measure.fragment.SportDeviceIntroductionFragment, com.huawei.health.ecologydevice.ui.measure.fragment.ProductFragment
    protected void updateUIDisconnect() {
        super.updateUIDisconnect();
        LogUtil.a(TAG, "updateUIDisconnect");
        this.mBleConnectStateText.setText(R.string.IDS_device_rope_device_not_connected);
        this.mReConnectTv.setVisibility(0);
        this.mStartImageView.setVisibility(8);
        this.mReconnectLoadingPb.setVisibility(8);
        setViewClickable(false);
    }

    private void showReconnectCountDialog() {
        if (getContext() == null) {
            LogUtil.h(TAG, "showReconnectCountDialog getContext() is null");
            return;
        }
        View inflate = LayoutInflater.from(this.mainActivity).inflate(R.layout.dialog_sport_connect_fail, (ViewGroup) null);
        ((HealthTextView) inflate.findViewById(R.id.tv_connect_fail_close_device)).setText(getString(R.string.IDS_connect_fail_close_to_device, "10"));
        CustomAlertDialog.Builder builder = new CustomAlertDialog.Builder(getContext());
        builder.e(R.string.IDS_device_common_err_connect_fail_tips).cyp_(inflate).cyo_(R.string.IDS_hw_health_wear_connect_device_connect_button, new DialogInterface.OnClickListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.SportIntroductionContentFragment$$ExternalSyntheticLambda2
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                SportIntroductionContentFragment.this.m355xfb0ba952(dialogInterface, i);
            }
        }).cyn_(R.string._2130838834_res_0x7f020532, new DialogInterface.OnClickListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.SportIntroductionContentFragment$$ExternalSyntheticLambda3
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                SportIntroductionContentFragment.this.m356x40acebf1(dialogInterface, i);
            }
        });
        CustomAlertDialog c = builder.c();
        this.mDialogConnectFail = c;
        c.setCancelable(false);
        this.mDialogConnectFail.show();
    }

    /* renamed from: lambda$showReconnectCountDialog$11$com-huawei-health-ecologydevice-ui-measure-fragment-SportIntroductionContentFragment, reason: not valid java name */
    /* synthetic */ void m355xfb0ba952(DialogInterface dialogInterface, int i) {
        if (this.mDialogConnectFail != null) {
            this.mConnectCount = 0;
            connectToDevice();
            this.mDialogConnectFail.dismiss();
            this.mDialogConnectFail = null;
        } else {
            LogUtil.h(TAG, "showReconnectCountDialog setPositiveButton null");
        }
        ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
    }

    /* renamed from: lambda$showReconnectCountDialog$12$com-huawei-health-ecologydevice-ui-measure-fragment-SportIntroductionContentFragment, reason: not valid java name */
    /* synthetic */ void m356x40acebf1(DialogInterface dialogInterface, int i) {
        CustomAlertDialog customAlertDialog = this.mDialogConnectFail;
        if (customAlertDialog != null) {
            this.mConnectCount = 0;
            customAlertDialog.dismiss();
            this.mDialogConnectFail = null;
        } else {
            LogUtil.h(TAG, "showReconnectCountDialog setPositiveButton null");
        }
        ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
    }

    private void setViewClickable(boolean z) {
        this.mStartImageView.setClickable(z);
        this.mFreeModeLayout.setClickable(z);
        this.mTimeModeLayout.setClickable(z);
        this.mDistanceModeLayout.setClickable(z);
        this.mCalModeLayout.setClickable(z);
        float f = z ? 1.0f : TRANS_ALPHA;
        this.mStartImageView.setAlpha(f);
        this.mStartImageView.setClickable(z);
        this.mModeSelectLayout.setAlpha(f);
        this.mModeSelectLayout.setClickable(z);
    }

    private void goToTrackDetailActivity(MotionPathSimplify motionPathSimplify, String str) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("simplifyDatakey", motionPathSimplify);
        bundle.putString("contentpath", str);
        bundle.putBoolean("isAfterSport", false);
        bundle.putBoolean("isNotNeedDeleteFile", false);
        Intent intent = new Intent(BaseApplication.getContext(), (Class<?>) TrackDetailActivity.class);
        intent.putExtras(bundle);
        intent.addFlags(268435456);
        gnm.aPB_(BaseApplication.getContext(), intent);
    }

    private void connectToDevice() {
        int d = djt.e().d(this.mainActivity);
        if (d == 0) {
            popupFragment(null);
            return;
        }
        if (this.mIsBtEnableShowing) {
            LogUtil.a(TAG, "connectToDevice cancle mIsBtEnableShowing =", Boolean.valueOf(this.mIsBtEnableShowing));
            return;
        }
        if (!checkPermission()) {
            LogUtil.a(TAG, "checkPermission cancle mIsBtEnableShowing =", Boolean.valueOf(this.mIsBtEnableShowing));
            return;
        }
        if (d != 1) {
            LogUtil.a(TAG, "judgeBlueTooth cancle mIsBtEnableShowing =", Boolean.valueOf(this.mIsBtEnableShowing));
            openBlueTooth(101);
        } else {
            this.mConnectCount++;
            onDeviceBleStateChanged(1);
            this.mPresenter.connectDevice(this.mType, this.mProductInfo);
        }
    }

    @Override // com.huawei.health.ecologydevice.ui.measure.view.SportIntroductionContentView
    public void startSport() {
        LogUtil.a(TAG, "startSport.");
        if (kwx.c()) {
            LogUtil.h(TAG, "this activity not auto start sport");
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putInt(WorkoutRecord.Extend.COURSE_TARGET_TYPE, 6);
        bundle.putBoolean("sendVoice", false);
        startIndoorEquipActivity(bundle);
    }

    @Override // com.huawei.health.ecologydevice.ui.measure.fragment.SportDeviceIntroductionFragment, com.huawei.health.ecologydevice.ui.measure.fragment.ProductFragment
    protected void autoConnectDevice() {
        if (this.mIndoorEquipManagerApi.isDeviceBtConnected() && !TextUtils.isEmpty(this.mUniqueId) && this.mUniqueId.equals(this.mIndoorEquipManagerApi.getCurrentMacAddress())) {
            onDeviceBleStateChanged(2);
            LogUtil.a(TAG, "isDeviceBtConnected");
            return;
        }
        onDeviceBleStateChanged(0);
        if (this.mIndoorEquipManagerApi.isDeviceBtConnected()) {
            this.mIndoorEquipManagerApi.disconnect(false);
        }
        LogUtil.a(TAG, "connectToDevice");
        this.mConnectCount++;
        onDeviceBleStateChanged(1);
        this.mPresenter.connectDevice(this.mType, this.mProductInfo);
    }

    @Override // com.huawei.health.ecologydevice.ui.measure.fragment.ProductFragment, androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        LogUtil.a(TAG, "onActivityResult requestCode", Integer.valueOf(i), " resultCode", Integer.valueOf(i2));
        if (i == 101) {
            this.mIsBtEnableShowing = false;
            if (i2 == -1) {
                autoConnectDevice();
            } else {
                popupFragment(null);
            }
        }
    }
}
