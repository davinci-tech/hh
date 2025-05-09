package com.huawei.health.device.ui.measure.fragment;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import androidx.fragment.app.FragmentActivity;
import com.apprichtap.haptic.base.PrebakedEffectId;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.device.callback.WeightInsertStatusCallback;
import com.huawei.health.device.connectivity.comm.MeasurableDevice;
import com.huawei.health.device.fatscale.multiusers.MultiUsersManager;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.device.open.MeasureController;
import com.huawei.health.device.open.MeasureKit;
import com.huawei.health.device.open.data.model.HealthData;
import com.huawei.health.device.ui.DeviceMainActivity;
import com.huawei.health.device.ui.measure.adapter.WeightResultConfirmAdapter;
import com.huawei.health.device.util.EventBus;
import com.huawei.health.device.wifi.lib.handler.StaticHandler;
import com.huawei.health.ecologydevice.manager.ResourceManager;
import com.huawei.health.ecologydevice.ui.BaseFragment;
import com.huawei.health.ecologydevice.ui.measure.fragment.ProductIntroductionFragment;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.HiSyncOption;
import com.huawei.hihealth.HiUserPreference;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiAggregateListener;
import com.huawei.hihealth.data.listener.HiCommonListener;
import com.huawei.hihealth.data.type.HiSyncType;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hms.support.api.entity.pay.HwPayConstant;
import com.huawei.hms.support.feature.result.CommonConstant;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwbasemgr.WeightBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.operation.ble.BleConstants;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.ash;
import defpackage.ceo;
import defpackage.cff;
import defpackage.cfi;
import defpackage.cgg;
import defpackage.cgk;
import defpackage.cgs;
import defpackage.cgt;
import defpackage.cgu;
import defpackage.cji;
import defpackage.cjx;
import defpackage.ckm;
import defpackage.ckq;
import defpackage.cnx;
import defpackage.cpa;
import defpackage.cpp;
import defpackage.cpr;
import defpackage.cpy;
import defpackage.cpz;
import defpackage.dcz;
import defpackage.dfd;
import defpackage.dfg;
import defpackage.dij;
import defpackage.dks;
import defpackage.gge;
import defpackage.grz;
import defpackage.jdx;
import defpackage.koq;
import defpackage.kot;
import defpackage.nsn;
import health.compact.a.CommonUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.Serializable;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes3.dex */
public class WeightResultFragment extends BaseFragment {
    private static final String ADD_OR_EDIT_USER_ACTIVITY = "com.huawei.ui.main.stories.health.activity.healthdata.AddOrEditWeightUserActivity";
    private static final String ARGUMENTS_KEY = "HealthData";
    private static final double BODY_FAT_DIFFERENCE_TEN = 10.0d;
    private static final double BODY_FAT_DIFFERENCE_THREE = 3.0d;
    private static final int CREATE_NEW_USER_SUCCESS = 101;
    private static final int CREATE_USER_REQUEST_CODE = 0;
    private static final int CREATE_USER_RESULT_CODE = 1;
    private static final int DEFAULT_PADDING = 0;
    private static final String DEVICE_AVAILABLE = "com.huawei.health.action.DEVICE_AVAILABLE";
    private static final int DIALOG_HEAD_AND_BOTTOM_HEIGHT = 84;
    private static final int GET_CURRENT_USER_SUCCESS = 102;
    private static final int GET_TARGET_WEIGHT_SUCCESS = 103;
    private static final int MAX_USER_NUMBER = 10;
    private static final String MEASURE_FOR_PLAN = "measure_weight_for_plan";
    private static final int MEASURE_RESULT_LAGER = 100;
    private static final int MEASURE_RESULT_TYPE_ALL = 2;
    private static final int MEASURE_RESULT_TYPE_ONLY_WEIGHT = 0;
    private static final String TAG = "WeightResultFragment";
    private static final String TAG_RELEASE = "R_Weight_WeightResultFragment";
    private static final String TYPE = "type";
    private static final double WEIGHT_DIFFERENCE_FIVE_KG = 5.0d;
    private static final double WEIGHT_DIFFERENCE_THREE_KG = 3.0d;
    private FrameLayout mAllFrameLayout;
    private HealthTextView mBetweenGoal;
    private HealthTextView mBodyFatResult;
    private NoTitleCustomAlertDialog mCommonTextAlertDialog;
    private Context mContext;
    private cfi mCurrentUser;
    private LinearLayout mCurrentUserLayout;
    private ContentValues mDeviceInfo;
    private View.OnClickListener mDeviceMeasureOnClick;
    private View mDialogView;
    private double mFatRatValue;
    private MyHandler mHandler;
    private HealthData mHealthData;
    private boolean mIsDataException;
    private boolean mIsGotoBaseActivity;
    private volatile boolean mIsSaveData;
    private cfi mNewCreateUser;
    private String mProductId;
    private Resources mResources;
    private LinearLayout mResultLinearLayout;
    private LinearLayout mSelectUserLayout;
    private double mTargetWeightValue;
    private int mType;
    private String mUniqueId;
    private WeightResultConfirmAdapter mUserAdapter;
    private LinearLayout mUserLayout;
    private List<cfi> mUserList;
    private ListView mUserListView;
    private HealthTextView mUserName;
    private ImageView mUserPhoto;
    private double mWeight;

    public WeightResultFragment() {
        Context context = BaseApplication.getContext();
        this.mContext = context;
        this.mResources = context.getResources();
        this.mTargetWeightValue = 0.0d;
        this.mIsGotoBaseActivity = false;
        this.mIsDataException = false;
        this.mHandler = new MyHandler(this);
        this.mDeviceMeasureOnClick = new View.OnClickListener() { // from class: com.huawei.health.device.ui.measure.fragment.WeightResultFragment$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WeightResultFragment.this.m222x999a8c25(view);
            }
        };
    }

    /* renamed from: lambda$new$0$com-huawei-health-device-ui-measure-fragment-WeightResultFragment, reason: not valid java name */
    /* synthetic */ void m222x999a8c25(View view) {
        if (view == null) {
            LogUtil.a(TAG_RELEASE, "WeightResultFragment onClick view is null");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        if (R.id.bt_device_measure_result_save == view.getId()) {
            if (!nsn.o()) {
                saveWeightData(this.mNewCreateUser);
            } else {
                LogUtil.a(TAG_RELEASE, "WeightResultFragment saveWeightBtn click too fast");
            }
        } else if (view.getId() == R.id.weight_result_current_measure_user_right_layout) {
            showConfirmResultDialog(this.mResources.getString(R.string.IDS_hw_device_hygride_select_current_measure_user));
        } else if (view.getId() == R.id.weight_measure_result_confrim_add_new_user_text) {
            enterAddNewUser();
        } else {
            LogUtil.h(TAG_RELEASE, "WeightResultFragment mDeviceMeasureOnClick else");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        MeasurableDevice e;
        LogUtil.a(TAG, "WeightResultFragment onCreate");
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.mType = arguments.getInt("type");
            ContentValues contentValues = (ContentValues) getArguments().getParcelable("commonDeviceInfo");
            this.mDeviceInfo = contentValues;
            if (contentValues != null) {
                this.mProductId = contentValues.getAsString("productId");
                this.mUniqueId = this.mDeviceInfo.getAsString("uniqueId");
            } else {
                LogUtil.h(TAG, "initData mDeviceInfo is empty");
            }
            if (TextUtils.isEmpty(this.mProductId)) {
                LogUtil.h(TAG, "initData mProductId is empty");
                this.mProductId = getArguments().getString("productId");
            }
            if (!TextUtils.isEmpty(this.mProductId) && TextUtils.isEmpty(this.mUniqueId) && (e = ceo.d().e(this.mProductId, false)) != null) {
                this.mUniqueId = e.getUniqueId();
            }
            if (TextUtils.isEmpty(this.mProductId)) {
                this.mProductId = getArguments().getString("productId");
            }
            if (TextUtils.isEmpty(this.mProductId) || TextUtils.isEmpty(this.mUniqueId)) {
                LogUtil.h(TAG, "initData mProductId or mUniqueId is empty");
            }
        }
        jdx.b(new Runnable() { // from class: com.huawei.health.device.ui.measure.fragment.WeightResultFragment$$ExternalSyntheticLambda9
            @Override // java.lang.Runnable
            public final void run() {
                WeightResultFragment.this.m223x8a1d6e1b();
            }
        });
        getCurGoalInfo();
    }

    /* renamed from: lambda$onCreate$1$com-huawei-health-device-ui-measure-fragment-WeightResultFragment, reason: not valid java name */
    /* synthetic */ void m223x8a1d6e1b() {
        HiUserPreference userPreference = HiHealthManager.d(this.mContext).getUserPreference("custom.start_weight_base");
        if (userPreference != null) {
            MultiUsersManager.INSTANCE.getMainUser().c(CommonUtil.j(userPreference.getValue()));
        }
    }

    private void getCurGoalInfo() {
        kot.a().c(new ResponseCallback<Float>() { // from class: com.huawei.health.device.ui.measure.fragment.WeightResultFragment.1
            @Override // com.huawei.hwbasemgr.ResponseCallback
            public void onResponse(int i, Float f) {
                LogUtil.h(WeightResultFragment.TAG, "getCurGoalInfo data ", f);
                if (f == null) {
                    return;
                }
                try {
                    WeightResultFragment.this.mTargetWeightValue = Double.parseDouble(String.valueOf(f));
                } catch (NumberFormatException e) {
                    ReleaseLogUtil.e(WeightResultFragment.TAG, "getCurGoalInfo NumberFormatException: ", ExceptionUtils.d(e));
                }
                WeightResultFragment.this.mHandler.sendEmptyMessage(103);
            }
        });
    }

    private void getCurrentUserInfo() {
        MultiUsersManager.INSTANCE.setIsCheckMainUser(true);
        FragmentActivity activity = getActivity();
        if ((activity instanceof DeviceMainActivity) && ((DeviceMainActivity) activity).f()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.health.device.ui.measure.fragment.WeightResultFragment$$ExternalSyntheticLambda10
                @Override // java.lang.Runnable
                public final void run() {
                    WeightResultFragment.this.m218xd6e22add();
                }
            });
        } else {
            MultiUsersManager.INSTANCE.getCurrentUser(new WeightBaseResponseCallback() { // from class: com.huawei.health.device.ui.measure.fragment.WeightResultFragment$$ExternalSyntheticLambda1
                @Override // com.huawei.hwbasemgr.WeightBaseResponseCallback
                public final void onResponse(int i, Object obj) {
                    WeightResultFragment.this.m219xb2a3a69e(i, (cfi) obj);
                }
            });
        }
    }

    /* renamed from: lambda$getCurrentUserInfo$2$com-huawei-health-device-ui-measure-fragment-WeightResultFragment, reason: not valid java name */
    /* synthetic */ void m218xd6e22add() {
        this.mCurrentUser = MultiUsersManager.INSTANCE.getMainUser();
        MultiUsersManager.INSTANCE.setCurrentUser(this.mCurrentUser);
        this.mHandler.sendEmptyMessage(102);
    }

    /* renamed from: lambda$getCurrentUserInfo$3$com-huawei-health-device-ui-measure-fragment-WeightResultFragment, reason: not valid java name */
    /* synthetic */ void m219xb2a3a69e(int i, cfi cfiVar) {
        if (cfiVar != null && i == 0) {
            this.mCurrentUser = cfiVar;
            this.mHandler.sendEmptyMessage(102);
        } else {
            LogUtil.h(TAG, "WeightResultFragment getCurrentUserInfo getCurrentUser fail, errorCode:", Integer.valueOf(i));
        }
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mIsSaveData = false;
        if (this.mainActivity != null) {
            this.mainActivity.getWindow().clearFlags(128);
        }
        if (layoutInflater == null || viewGroup == null) {
            LogUtil.h(TAG_RELEASE, "InterruptedException onCreateView inflater is null");
            return null;
        }
        ViewGroup viewGroup2 = super.onCreateView(layoutInflater, viewGroup, bundle) instanceof ViewGroup ? (ViewGroup) super.onCreateView(layoutInflater, viewGroup, bundle) : null;
        this.child = layoutInflater.inflate(R.layout.device_weight_measure_result, viewGroup, false);
        if (viewGroup2 != null) {
            viewGroup2.addView(this.child);
        }
        if (getArguments() != null && (getArguments().getSerializable(ARGUMENTS_KEY) instanceof HealthData)) {
            this.mHealthData = (HealthData) getArguments().getSerializable(ARGUMENTS_KEY);
        }
        initChildView();
        LogUtil.c(TAG, "WeightResultFragment onCreateView productId is ", this.mProductId);
        if (this.mProductId != null) {
            initProdInfo();
        }
        String string = getArguments().getString("title");
        if (!TextUtils.isEmpty(string) && string.indexOf(" - ") == -1) {
            String f = cpa.f(this.mUniqueId);
            if (!TextUtils.isEmpty(f)) {
                string = string + Constants.LINK + f;
            }
        }
        setTitle(string);
        if (cpa.aq(this.mProductId) && !Utils.l()) {
            getUserLastData();
        }
        return viewGroup2;
    }

    private void initProdInfo() {
        cpz.d(this.mProductId);
        LogUtil.a(TAG, "blue test sendWeightDetailSyncSuccessBroadcast");
        cpa.c(cpp.a(), this.mProductId, this.mUniqueId);
    }

    private void initChildView() {
        if (this.child != null) {
            this.mAllFrameLayout = (FrameLayout) this.child.findViewById(R.id.device_measure_result_top_circle);
            this.mResultLinearLayout = (LinearLayout) this.child.findViewById(R.id.tv_weight_measure_result);
            this.mCurrentUserLayout = (LinearLayout) this.child.findViewById(R.id.weight_result_user_layout);
            this.mSelectUserLayout = (LinearLayout) this.child.findViewById(R.id.weight_result_current_measure_user_right_layout);
            this.mUserPhoto = (ImageView) this.child.findViewById(R.id.weight_result_current_measure_user_photo);
            this.mUserName = (HealthTextView) this.child.findViewById(R.id.weight_result_current_measure_user_name);
            this.mBetweenGoal = (HealthTextView) this.child.findViewById(R.id.tv_sugar_measure_result_msg);
            this.mBodyFatResult = (HealthTextView) this.child.findViewById(R.id.tv_body_fat_value);
            HealthButton healthButton = (HealthButton) this.child.findViewById(R.id.bt_device_measure_result_remeasure);
            healthButton.setVisibility(8);
            healthButton.setOnClickListener(this.mDeviceMeasureOnClick);
            HealthButton healthButton2 = (HealthButton) this.child.findViewById(R.id.bt_device_measure_result_save);
            healthButton2.setText(R.string.IDS_device_show_complete);
            healthButton2.setOnClickListener(this.mDeviceMeasureOnClick);
            getCurrentUserInfo();
        }
    }

    private cnx parseData(HealthData healthData) {
        String format;
        cnx cnxVar = new cnx();
        if (healthData instanceof ckm) {
            ckm ckmVar = (ckm) healthData;
            this.mWeight = CommonUtil.a("" + ckmVar.getWeight());
            this.mFatRatValue = CommonUtil.a("" + ckmVar.getBodyFatRat());
        }
        cnxVar.e(UnitUtil.e(this.mWeight, 1, 2));
        if (this.mFatRatValue <= 0.0d) {
            format = this.mResources.getString(R.string.IDS_device_measure_weight_defualt_value);
            cnxVar.b(this.mResources.getColor(R.color._2131298112_res_0x7f090740));
        } else {
            NumberFormat percentInstance = NumberFormat.getPercentInstance();
            percentInstance.setMinimumFractionDigits(1);
            format = percentInstance.format(this.mFatRatValue / 100.0d);
            cnxVar.b(this.mResources.getColor(R.color._2131298111_res_0x7f09073f));
        }
        cnxVar.a(format);
        LogUtil.c(TAG, "WeightResultFragment parseData mTargetWeightValue is ", Double.valueOf(this.mTargetWeightValue));
        showViewValue(cnxVar);
        return cnxVar;
    }

    private void showViewValue(cnx cnxVar) {
        String quantityString;
        if (dfg.d().e() > 3) {
            cnxVar.c("");
            return;
        }
        double d = this.mTargetWeightValue;
        double d2 = this.mWeight;
        double abs = Math.abs(UnitUtil.a(d2, cpy.c(d2, this.mProductId)) - d);
        if (UnitUtil.h()) {
            double h = UnitUtil.h(abs);
            quantityString = this.mResources.getQuantityString(R.plurals._2130903216_res_0x7f0300b0, UnitUtil.e(h), UnitUtil.e(h, 1, cpy.c(UnitUtil.h(this.mWeight), this.mProductId)));
        } else {
            quantityString = this.mResources.getQuantityString(R.plurals._2130903215_res_0x7f0300af, UnitUtil.e(abs), UnitUtil.e(abs, 1, cpy.c(this.mWeight, this.mProductId)));
        }
        String str = quantityString;
        if (this.mCurrentUser == null) {
            LogUtil.h(TAG, "showViewValue mCurrentUser is null");
        } else {
            cnxVar.c(dks.c(this.mWeight, r0.f(), d, str));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initViewData() {
        if (this.mHealthData == null) {
            LogUtil.h(TAG, "initViewData mHealthData is null");
            return;
        }
        cfi cfiVar = this.mCurrentUser;
        if (cfiVar == null || cfiVar.n() != 1) {
            setViewData(parseData(this.mHealthData));
        } else if (MultiUsersManager.INSTANCE.getMainUser().f() > 0.0f) {
            setViewData(parseData(this.mHealthData));
        } else {
            jdx.b(new Runnable() { // from class: com.huawei.health.device.ui.measure.fragment.WeightResultFragment$$ExternalSyntheticLambda4
                @Override // java.lang.Runnable
                public final void run() {
                    WeightResultFragment.this.m221x5daaca03();
                }
            });
        }
    }

    /* renamed from: lambda$initViewData$5$com-huawei-health-device-ui-measure-fragment-WeightResultFragment, reason: not valid java name */
    /* synthetic */ void m221x5daaca03() {
        HiUserPreference userPreference = HiHealthManager.d(this.mContext).getUserPreference("custom.start_weight_base");
        if (userPreference != null) {
            MultiUsersManager.INSTANCE.getMainUser().c(CommonUtil.j(userPreference.getValue()));
        }
        if (this.mainActivity == null) {
            LogUtil.h(TAG, "initViewData mainActivity is null");
        } else {
            this.mainActivity.runOnUiThread(new Runnable() { // from class: com.huawei.health.device.ui.measure.fragment.WeightResultFragment$$ExternalSyntheticLambda8
                @Override // java.lang.Runnable
                public final void run() {
                    WeightResultFragment.this.m220x81e94e42();
                }
            });
        }
    }

    /* renamed from: lambda$initViewData$4$com-huawei-health-device-ui-measure-fragment-WeightResultFragment, reason: not valid java name */
    /* synthetic */ void m220x81e94e42() {
        setViewData(parseData(this.mHealthData));
    }

    private void setViewData(cnx cnxVar) {
        if (cnxVar != null) {
            setWeightResultText();
            this.mBetweenGoal.setText(cnxVar.e());
            this.mBodyFatResult.setText(cnxVar.c());
            this.mBodyFatResult.setTextColor(cnxVar.d());
            cfi cfiVar = this.mCurrentUser;
            if (cfiVar == null || cfiVar.n() != 1) {
                this.mBetweenGoal.setVisibility(8);
            } else {
                this.mBetweenGoal.setVisibility(0);
            }
            if (this.mTargetWeightValue == 0.0d) {
                this.mBetweenGoal.setVisibility(8);
            }
            setUserNameAndPhoto();
        }
        initViewTahiti();
        initMirrorView();
        if (!cpa.aq(this.mProductId) || Utils.l()) {
            this.mCurrentUserLayout.setVisibility(8);
        }
    }

    private void setUserNameAndPhoto() {
        LogUtil.a(TAG, "setUserNameAndPhoto enter");
        if (this.mCurrentUser == null) {
            LogUtil.h(TAG, "setUserNameAndPhoto mCurrentUser is null");
            this.mSelectUserLayout.setVisibility(8);
        } else {
            this.mSelectUserLayout.setVisibility(0);
            cpr.Kn_(this.mCurrentUser, this.mUserName, this.mUserPhoto);
        }
    }

    private void setWeightResultText() {
        HealthTextView healthTextView = (HealthTextView) this.child.findViewById(R.id.tv_weight_mearsure_result_value);
        HealthTextView healthTextView2 = (HealthTextView) this.child.findViewById(R.id.tv_weight_mearsure_result_value_unit);
        HealthTextView healthTextView3 = (HealthTextView) this.child.findViewById(R.id.tv_weight_mearsure_result_value_unit_sw);
        if (UnitUtil.h()) {
            showAfterUnit(healthTextView3, healthTextView2);
            healthTextView.setText(UnitUtil.e(UnitUtil.h(this.mWeight), 1, cpy.c(this.mWeight, this.mProductId)));
            healthTextView2.setText(R.string.IDS_device_measure_weight_value_unit_eng);
            return;
        }
        double d = this.mWeight;
        String e = UnitUtil.e(d, 1, cpy.c(d, this.mProductId));
        String trim = this.mContext.getResources().getQuantityString(R.plurals._2130903215_res_0x7f0300af, (int) this.mWeight, e).replace(e, "").trim();
        if (healthTextView != null) {
            healthTextView.setText(e);
        } else {
            LogUtil.h(TAG, "setWeightResultText weightResult is null");
        }
        if (healthTextView2 != null) {
            healthTextView2.setText(trim);
        } else {
            LogUtil.h(TAG, "setWeightResultText weightResultUnit is null");
        }
        if (healthTextView3 != null) {
            healthTextView3.setText(trim);
        } else {
            LogUtil.h(TAG, "setWeightResultText weightResultUnitSw is null");
        }
        if (LanguageUtil.ai(this.mContext)) {
            showBeforeUnit(healthTextView3, healthTextView2);
        } else {
            showAfterUnit(healthTextView3, healthTextView2);
        }
    }

    private void showBeforeUnit(HealthTextView healthTextView, HealthTextView healthTextView2) {
        healthTextView.setVisibility(0);
        healthTextView2.setVisibility(8);
    }

    private void showAfterUnit(HealthTextView healthTextView, HealthTextView healthTextView2) {
        if (healthTextView != null) {
            healthTextView.setVisibility(8);
        } else {
            LogUtil.h(TAG, "showAfterUnit beforeValue is null");
        }
        if (healthTextView2 != null) {
            healthTextView2.setVisibility(0);
        } else {
            LogUtil.h(TAG, "showAfterUnit afterValue is null");
        }
    }

    private void initMirrorView() {
        ImageView imageView = (ImageView) this.child.findViewById(R.id.weight_result_current_measure_user_right_img);
        if (imageView != null) {
            if (LanguageUtil.bc(this.mContext)) {
                imageView.setBackground(this.mResources.getDrawable(R.drawable._2131427841_res_0x7f0b0201));
            } else {
                imageView.setBackground(this.mResources.getDrawable(R.drawable._2131427842_res_0x7f0b0202));
            }
            imageView.setVisibility(8);
        }
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment
    public void initViewTahiti() {
        super.initViewTahiti();
        if (nsn.ag(this.mContext)) {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, 0, 0.5f);
            layoutParams.gravity = 1;
            this.mResultLinearLayout.setLayoutParams(layoutParams);
            this.mAllFrameLayout.setLayoutParams(layoutParams);
        }
    }

    private void saveWeightData(cfi cfiVar) {
        LogUtil.a(TAG, "WeightResultFragment click saveWeightBtn and save data");
        Bundle arguments = getArguments();
        if (arguments == null) {
            LogUtil.h(TAG_RELEASE, "WeightResultFragment saveWeightData argumentBundle is null");
            return;
        }
        MeasurableDevice d = ceo.d().d(this.mUniqueId, true);
        if (d == null) {
            LogUtil.a(TAG_RELEASE, "saveWeightData Failed device null");
            return;
        }
        String b = ash.b(MEASURE_FOR_PLAN);
        if (b != null && b.equals("true")) {
            cpz.d();
            ash.d(MEASURE_FOR_PLAN);
        }
        setUserInfo(cfiVar);
        if (TextUtils.isEmpty(this.mProductId)) {
            LogUtil.a(TAG, "saveWeightData Failed mProductId null");
            return;
        }
        saveWeightData(this.mProductId, arguments, d, refreshUserAge(cfiVar));
        dcz d2 = ResourceManager.e().d(this.mProductId);
        if (d2 == null) {
            LogUtil.h(TAG, "productInfo is null");
            return;
        }
        MeasureKit g = cjx.e().g(d2.s());
        if (g != null) {
            MeasureController measureController = g.getMeasureController();
            Bundle bundle = new Bundle();
            bundle.putString("productId", this.mProductId);
            bundle.putInt("type", this.mType);
            if (measureController != null) {
                measureController.prepare(d, null, bundle);
            }
        } else {
            LogUtil.h(TAG, "saveWeightData kit is null");
        }
        cjx.e().e(this.mProductId, this.mUniqueId, this.mType);
    }

    private cfi refreshUserAge(cfi cfiVar) {
        if (cfiVar != null) {
            cfiVar.a(cgs.e(cfiVar.g(), cfiVar.a()));
            return cfiVar;
        }
        if (this.mCurrentUser == null) {
            return MultiUsersManager.INSTANCE.getCurrentUser();
        }
        cfi singleUserById = MultiUsersManager.INSTANCE.getSingleUserById(this.mCurrentUser.i());
        if (singleUserById != null) {
            singleUserById.a(cgs.e(singleUserById.g(), singleUserById.a()));
        }
        return singleUserById;
    }

    private void setUserInfo(cfi cfiVar) {
        if (cfiVar == null) {
            LogUtil.a(TAG, "setUserInfo : user is null send to currentUser");
            MultiUsersManager.INSTANCE.getCurrentUser(new WeightBaseResponseCallback() { // from class: com.huawei.health.device.ui.measure.fragment.WeightResultFragment$$ExternalSyntheticLambda2
                @Override // com.huawei.hwbasemgr.WeightBaseResponseCallback
                public final void onResponse(int i, Object obj) {
                    WeightResultFragment.this.m225xc0ceae26(i, (cfi) obj);
                }
            });
        } else {
            processUserInfo(cfiVar);
        }
    }

    /* renamed from: lambda$setUserInfo$6$com-huawei-health-device-ui-measure-fragment-WeightResultFragment, reason: not valid java name */
    /* synthetic */ void m225xc0ceae26(int i, cfi cfiVar) {
        if (cfiVar != null && i == 0) {
            processUserInfo(cfiVar);
        } else {
            LogUtil.h(TAG, "setUserInfo : currentUser is null return");
        }
    }

    private void processUserInfo(cfi cfiVar) {
        String o;
        String str;
        if (Utils.i()) {
            str = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
            o = cfiVar.i();
        } else {
            o = cgt.e().o();
            str = "0";
        }
        Bundle bundle = new Bundle();
        bundle.putString("huid", str);
        bundle.putString("uid", o);
        bundle.putInt("height", cfiVar.d());
        bundle.putInt(CommonConstant.KEY_GENDER, cfiVar.c());
        bundle.putInt("age", cfiVar.a());
        bundle.putInt("birthday", cfiVar.g());
        double d = this.mWeight;
        bundle.putFloat("weight", CommonUtil.j(UnitUtil.e(d, 1, cpy.c(d, this.mProductId))));
        if (cji.a(this.mUniqueId, 43)) {
            if (this.mFatRatValue <= 0.0d) {
                bundle.putInt("guestUser", 0);
            } else {
                bundle.putInt("guestUser", 2);
            }
        }
        LogUtil.c(TAG, bundle.toString());
        EventBus.d(new EventBus.b("weight_measure_set_user", bundle));
    }

    private void saveWeightData(String str, Bundle bundle, HealthDevice healthDevice, final cfi cfiVar) {
        dfd dfdVar;
        if (cfiVar == null) {
            LogUtil.h(TAG, "saveWeightData user is null");
            dfdVar = new dfd(10006);
        } else {
            MultiUsersManager.INSTANCE.setCurrentUser(cfiVar);
            dfdVar = new dfd(cfiVar, 10006);
        }
        if (healthDevice instanceof MeasurableDevice) {
            ((MeasurableDevice) healthDevice).setProductId(this.mProductId);
        }
        if (bundle == null) {
            LogUtil.h(TAG, "WeightResultFragment saveWeightData argumentBundle is null");
            return;
        }
        Serializable serializable = bundle.getSerializable(ARGUMENTS_KEY);
        if (serializable instanceof HealthData) {
            final HealthData healthData = (HealthData) serializable;
            final long currentTimeMillis = System.currentTimeMillis();
            dfdVar.b(new WeightInsertStatusCallback() { // from class: com.huawei.health.device.ui.measure.fragment.WeightResultFragment$$ExternalSyntheticLambda0
                @Override // com.huawei.health.device.callback.WeightInsertStatusCallback
                public final void isSuccess(boolean z) {
                    WeightResultFragment.this.m224x358478c5(healthData, cfiVar, currentTimeMillis, z);
                }
            });
            dfdVar.onDataChanged(healthDevice, healthData);
            saveHagrideMeasureTime(str, healthData);
            sendBiEvent(cfiVar);
            return;
        }
        ReleaseLogUtil.d(TAG_RELEASE, "WeightResultFragment serializable is error");
        goWeightManagementPage();
    }

    /* renamed from: lambda$saveWeightData$7$com-huawei-health-device-ui-measure-fragment-WeightResultFragment, reason: not valid java name */
    /* synthetic */ void m224x358478c5(HealthData healthData, cfi cfiVar, long j, boolean z) {
        if (z) {
            syncData();
            cjx.e().a(this.mProductId, DEVICE_AVAILABLE);
        } else {
            ReleaseLogUtil.d(TAG_RELEASE, "WeightResultFragment insert data fail");
        }
        if (this.mIsSaveData) {
            return;
        }
        this.mIsSaveData = true;
        goMeasurementDataPage(healthData, cfiVar);
        ReleaseLogUtil.e(TAG_RELEASE, "save end totalTime = ", Long.valueOf(System.currentTimeMillis() - j));
    }

    private void goMeasurementDataPage(HealthData healthData, cfi cfiVar) {
        Bundle extras;
        if (cfiVar == null) {
            LogUtil.h(TAG, "goMeasurementDataPage user is null");
            cfiVar = MultiUsersManager.INSTANCE.getCurrentUser();
        }
        HashMap hashMap = new HashMap();
        hashMap.put("isFromMeasureResult", true);
        hashMap.put("isTripartiteDevice", Boolean.valueOf(checkIsTripartiteDevice()));
        hashMap.put("startTime", Long.valueOf(healthData.getStartTime()));
        hashMap.put("endTime", Long.valueOf(healthData.getEndTime()));
        hashMap.put("metadata", cff.b(cfiVar.i()) ? "" : cfiVar.i());
        FragmentActivity activity = getActivity();
        if (activity != null && activity.getIntent() != null && (extras = activity.getIntent().getExtras()) != null && extras.getBoolean("isForResult", false)) {
            hashMap.put("uniqueId", this.mUniqueId);
            hashMap.put("productId", this.mProductId);
            Intent intent = new Intent();
            String e = HiJsonUtil.e(hashMap);
            intent.putExtra("result", e);
            activity.setResult(-1, intent);
            ReleaseLogUtil.e(TAG, "goMeasurementDataPage onActivityResult json: ", e);
            activity.finish();
            return;
        }
        grz.a(hashMap);
        ReleaseLogUtil.e(TAG, "goMeasurementDataPage goToWeightDataPage map: ", String.valueOf(hashMap));
        this.mIsGotoBaseActivity = true;
    }

    private void goWeightManagementPage() {
        String str;
        Intent intent = new Intent();
        intent.setPackage(BaseApplication.getAppPackage());
        intent.putExtra("healthdata", "MyHealthData_jump_mainActivity");
        intent.putExtra("base_health_data_type_key", 1);
        intent.putExtra("type", this.mType);
        intent.putExtra("weight_user_id", MultiUsersManager.INSTANCE.getCurrentUser().i());
        intent.putExtra("isFromMeasureResult", true);
        intent.putExtra("isTripartiteDevice", checkIsTripartiteDevice());
        DeviceMainActivity deviceMainActivity = (DeviceMainActivity) getActivity();
        if (deviceMainActivity == null || !deviceMainActivity.f()) {
            str = "";
        } else {
            str = "6";
            gge.e(AnalyticsValue.HEALTH_HOME_WIGHT_DETAIL_2010023.value(), "6");
        }
        this.mIsGotoBaseActivity = true;
        LogUtil.a(TAG, "goWeightManagementPage");
        grz.aST_(str, intent);
    }

    private boolean checkIsTripartiteDevice() {
        if (this.mProductId == null) {
            return true;
        }
        dcz d = ResourceManager.e().d(this.mProductId);
        if (d == null || d.n() == null) {
            LogUtil.h(TAG, "checkIsTripartiteDevice productInfo is null or productInfo.getManifest is null");
            return true;
        }
        String a2 = d.n().a();
        if (!"huawei".equalsIgnoreCase(a2) && !"honor".equalsIgnoreCase(a2) && !"hygride".equalsIgnoreCase(a2)) {
            return true;
        }
        LogUtil.a(TAG, "isHuaweiOrHonor");
        return false;
    }

    private void saveHagrideMeasureTime(String str, HealthData healthData) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h(TAG_RELEASE, "saveHagrideMeasureTime productId is null");
            return;
        }
        LogUtil.a(TAG, "saveHagrideMeasureTime productId:", str);
        if (!cpa.aq(str) || healthData.getStartTime() == 0) {
            return;
        }
        LogUtil.a(TAG, "saveHagrideMeasureTime time:", Long.valueOf(healthData.getStartTime()));
        SharedPreferenceManager.e(BaseApplication.getContext(), Integer.toString(PrebakedEffectId.RT_HEARTBEAT), "health_wifi_notify_TIMESTAMP", String.valueOf(healthData.getStartTime()), new StorageParams());
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        DeviceMainActivity deviceMainActivity = (DeviceMainActivity) getActivity();
        if (!this.mIsGotoBaseActivity || deviceMainActivity == null) {
            return;
        }
        popupFragment(deviceMainActivity.b());
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        if (this.mIsGotoBaseActivity) {
            popupFragment(HagridDeviceManagerFragment.class);
            this.mIsGotoBaseActivity = false;
        }
    }

    private void writeDataBack() {
        LogUtil.a(TAG, "WeightResultFragment writeDataBack..");
        MultiUsersManager.INSTANCE.getCurrentUser(new WeightBaseResponseCallback() { // from class: com.huawei.health.device.ui.measure.fragment.WeightResultFragment$$ExternalSyntheticLambda7
            @Override // com.huawei.hwbasemgr.WeightBaseResponseCallback
            public final void onResponse(int i, Object obj) {
                WeightResultFragment.this.m228xcd82997d(i, (cfi) obj);
            }
        });
    }

    /* renamed from: lambda$writeDataBack$8$com-huawei-health-device-ui-measure-fragment-WeightResultFragment, reason: not valid java name */
    /* synthetic */ void m228xcd82997d(int i, cfi cfiVar) {
        if (cfiVar != null && i == 0) {
            writeUserInfoBean(cfiVar);
        } else {
            LogUtil.h(TAG, "writeDataBack : currentUser is null return");
            cgk.d().e(cgg.b(new cgu()));
        }
    }

    private void writeUserInfoBean(cfi cfiVar) {
        cgu cguVar = new cgu();
        cguVar.b(cfiVar.i());
        cguVar.e(cfiVar.a());
        cguVar.c(cfiVar.d());
        if (cfiVar.m() > 0.0f) {
            cguVar.e(cfiVar.m());
        } else {
            cguVar.e(60.0f);
        }
        if (cfiVar.c() == 1 || cfiVar.c() == 2) {
            cguVar.d(0);
        } else {
            cguVar.d(1);
        }
        cgk.d().e(cgg.b(cguVar));
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment
    public void saveResultData() {
        super.saveResultData();
        saveWeightData(this.mNewCreateUser);
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment
    public void release() {
        super.release();
        DeviceMainActivity deviceMainActivity = (DeviceMainActivity) getActivity();
        String str = this.mProductId;
        if (str != null && cpa.ab(str) && !cpa.ae(this.mProductId)) {
            writeDataBack();
        }
        if (this.mType == -1 && deviceMainActivity != null) {
            popupFragment(deviceMainActivity.b());
        } else {
            popupFragment(ProductIntroductionFragment.class);
        }
        onDestroy();
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment
    public boolean onBackPressed() {
        return super.showCustomAlertDialog(R.string.IDS_device_cancel_save_data);
    }

    private void syncData() {
        HiSyncOption hiSyncOption = new HiSyncOption();
        hiSyncOption.setSyncModel(2);
        hiSyncOption.setSyncAction(0);
        hiSyncOption.setSyncDataType(HiSyncType.HiSyncDataType.c);
        hiSyncOption.setSyncScope(1);
        hiSyncOption.setSyncMethod(2);
        HiHealthNativeApi.a(cpp.a()).synCloud(hiSyncOption, new HiCommonListener() { // from class: com.huawei.health.device.ui.measure.fragment.WeightResultFragment.2
            @Override // com.huawei.hihealth.data.listener.HiCommonListener
            public void onSuccess(int i, Object obj) {
                LogUtil.a(WeightResultFragment.TAG, "syncDataStart onSuccess");
            }

            @Override // com.huawei.hihealth.data.listener.HiCommonListener
            public void onFailure(int i, Object obj) {
                LogUtil.a(WeightResultFragment.TAG_RELEASE, "syncDataStart onFailure");
            }
        });
    }

    private void getUserLastData() {
        cff.a(this.mCurrentUser, new HiAggregateListener() { // from class: com.huawei.health.device.ui.measure.fragment.WeightResultFragment.3
            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResult(List<HiHealthData> list, int i, int i2) {
                LogUtil.a(WeightResultFragment.TAG, "getUserLastData onResult called errorCode:", Integer.valueOf(i));
                WeightResultFragment.this.compareWeightData(list);
            }

            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResultIntent(int i, List<HiHealthData> list, int i2, int i3) {
                LogUtil.a(WeightResultFragment.TAG, "getUserLastData onResultIntent called errorCode:", Integer.valueOf(i2));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void compareWeightData(List<HiHealthData> list) {
        double d;
        double d2;
        if (list == null || list.isEmpty()) {
            LogUtil.h(TAG_RELEASE, "compareWeightData getWeight no data");
            return;
        }
        HiHealthData hiHealthData = list.get(0);
        double d3 = hiHealthData.getDouble("bodyWeight");
        double d4 = hiHealthData.getDouble(BleConstants.BODY_FAT_RATE);
        LogUtil.a(TAG, "compareWeightData weight = ", Double.valueOf(d3), " bodyfat = ", Double.valueOf(d4));
        HealthData healthData = this.mHealthData;
        if (healthData instanceof ckm) {
            ckm ckmVar = (ckm) healthData;
            LogUtil.a(TAG, "compareWeightData healthData.getWeight() = ", Float.valueOf(ckmVar.getWeight()));
            if (cji.a(this.mUniqueId, 43)) {
                d = 3.0d;
                d2 = 3.0d;
            } else {
                d = 5.0d;
                d2 = 10.0d;
            }
            if (d3 > 0.0d && Math.abs(ckmVar.getWeight() - d3) > d) {
                LogUtil.a(TAG, "compareWeightData weight more than 5kg");
                this.mHandler.sendEmptyMessage(100);
                this.mIsDataException = true;
                return;
            }
            LogUtil.a(TAG, "compareWeightData healthData.getBodyFatRat() = ", Float.valueOf(ckmVar.getBodyFatRat()));
            if (d4 <= 0.0d || ckmVar.getBodyFatRat() <= 0.0f || Math.abs(ckmVar.getBodyFatRat() - d4) < d2) {
                return;
            }
            LogUtil.a(TAG, "compareWeightData body fat more than 10%");
            this.mHandler.sendEmptyMessage(100);
            this.mIsDataException = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showConfirmResultDialog(String str) {
        if (getActivity() == null) {
            LogUtil.h(TAG_RELEASE, "showConfirmResultDialog getActivity is null");
            return;
        }
        initUserData();
        initDialogView();
        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(getActivity());
        builder.e(str).czC_(R.string._2130840653_res_0x7f020c4d, new View.OnClickListener() { // from class: com.huawei.health.device.ui.measure.fragment.WeightResultFragment$$ExternalSyntheticLambda5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WeightResultFragment.this.m227x45e4f536(view);
            }
        }).czz_(R.string._2130840652_res_0x7f020c4c, new View.OnClickListener() { // from class: com.huawei.health.device.ui.measure.fragment.WeightResultFragment$$ExternalSyntheticLambda6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WeightResultFragment.this.m226x26ccc1ae(view);
            }
        });
        NoTitleCustomAlertDialog e = builder.e();
        this.mCommonTextAlertDialog = e;
        e.setCancelable(false);
        showDialog();
    }

    /* renamed from: lambda$showConfirmResultDialog$9$com-huawei-health-device-ui-measure-fragment-WeightResultFragment, reason: not valid java name */
    /* synthetic */ void m227x45e4f536(View view) {
        this.mCommonTextAlertDialog.dismiss();
        int d = this.mUserAdapter.d();
        if (koq.d(this.mUserList, d)) {
            saveWeightData(this.mUserList.get(d));
        } else {
            LogUtil.b(TAG, "showConfirmResultDialog out mUserList bound:", Integer.valueOf(d));
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    /* renamed from: lambda$showConfirmResultDialog$10$com-huawei-health-device-ui-measure-fragment-WeightResultFragment, reason: not valid java name */
    /* synthetic */ void m226x26ccc1ae(View view) {
        this.mCommonTextAlertDialog.dismiss();
        gotoGuestMeasureFragment();
        ViewClickInstrumentation.clickOnView(view);
    }

    private void gotoGuestMeasureFragment() {
        BaseFragment c = ckq.c(GuestUserInfoGuideFragment.class.getName());
        if (c != null) {
            Bundle bundle = new Bundle();
            bundle.putString("productId", this.mProductId);
            bundle.putParcelable("commonDeviceInfo", this.mDeviceInfo);
            bundle.putBoolean("deviceMgrMeasure", false);
            bundle.putSerializable(ARGUMENTS_KEY, getArguments().getSerializable(ARGUMENTS_KEY));
            c.setArguments(bundle);
            switchFragment(c);
        }
    }

    private void showDialog() {
        NoTitleCustomAlertDialog noTitleCustomAlertDialog = this.mCommonTextAlertDialog;
        if (noTitleCustomAlertDialog != null && !noTitleCustomAlertDialog.isShowing()) {
            ViewGroup.LayoutParams layoutParams = this.mUserLayout.getLayoutParams();
            layoutParams.height = getDialogHeight(this.mUserListView);
            layoutParams.width = -1;
            this.mUserLayout.setLayoutParams(layoutParams);
            this.mCommonTextAlertDialog.show();
            return;
        }
        LogUtil.h(TAG, "showDialog: mCommonTextAlertDialog is null | isShowing.");
    }

    private void initDialogView() {
        if (getActivity() != null) {
            this.mDialogView = LayoutInflater.from(getActivity()).inflate(R.layout.weight_measure_result_confirm_dialog_view, (ViewGroup) null, false);
        }
        View view = this.mDialogView;
        if (view != null) {
            this.mUserListView = (ListView) view.findViewById(R.id.weight_measure_result_confrim_user_list);
            this.mUserLayout = (LinearLayout) this.mDialogView.findViewById(R.id.weight_measure_result_confrim_list_layout);
            HealthTextView healthTextView = (HealthTextView) this.mDialogView.findViewById(R.id.weight_measure_result_confrim_add_new_user_text);
            this.mUserListView.setAdapter((ListAdapter) this.mUserAdapter);
            if (this.mUserAdapter.getCount() >= 10) {
                healthTextView.setVisibility(8);
            }
            healthTextView.setOnClickListener(this.mDeviceMeasureOnClick);
        }
    }

    private void initUserData() {
        List<cfi> allUser = MultiUsersManager.INSTANCE.getAllUser();
        this.mUserList = allUser;
        LogUtil.a(TAG, "initUserData: user number:", Integer.valueOf(allUser.size()));
        this.mUserAdapter = new WeightResultConfirmAdapter(this.mContext, this.mUserList);
        String i = MultiUsersManager.INSTANCE.getCurrentUser().i();
        if (TextUtils.isEmpty(i)) {
            LogUtil.h(TAG, "initUserData: uuid is empty");
            return;
        }
        for (int i2 = 0; i2 < this.mUserList.size(); i2++) {
            cfi cfiVar = this.mUserList.get(i2);
            if (cfiVar == null) {
                LogUtil.h(TAG, "initUserData: tmpUser is null");
                return;
            } else {
                if (i.equals(cfiVar.i())) {
                    this.mUserAdapter.a(i2);
                    return;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshCurrentMeasureUser() {
        cpr.Kn_(this.mNewCreateUser, this.mUserName, this.mUserPhoto);
    }

    private void enterAddNewUser() {
        Intent intent = new Intent();
        intent.setPackage(BaseApplication.getAppPackage());
        intent.setClassName(BaseApplication.getAppPackage(), ADD_OR_EDIT_USER_ACTIVITY);
        intent.putExtra("weight_user_id_key", "");
        intent.putExtra("claimWeightData", true);
        startActivityForResult(intent, 0);
    }

    private int getDialogHeight(ListView listView) {
        int Va_ = dij.Va_(listView);
        if (getActivity() == null) {
            LogUtil.h(TAG_RELEASE, "getDialogHeight getActivity is null");
            return 0;
        }
        if (!(getActivity().getSystemService(Constants.NATIVE_WINDOW_SUB_DIR) instanceof WindowManager)) {
            return 0;
        }
        int height = (((WindowManager) getActivity().getSystemService(Constants.NATIVE_WINDOW_SUB_DIR)).getDefaultDisplay().getHeight() / 2) - dij.d(BaseApplication.getContext(), 84.0f);
        return Va_ < height ? Va_ : height;
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        NoTitleCustomAlertDialog noTitleCustomAlertDialog = this.mCommonTextAlertDialog;
        if (noTitleCustomAlertDialog != null && noTitleCustomAlertDialog.isShowing()) {
            this.mCommonTextAlertDialog.dismiss();
        }
        if (intent == null) {
            LogUtil.h(TAG_RELEASE, "WeightResultFragment onActivityResult intent is null");
            return;
        }
        if (i2 == 1) {
            String stringExtra = intent.getStringExtra(HwPayConstant.KEY_USER_ID);
            if (TextUtils.isEmpty(stringExtra)) {
                LogUtil.h(TAG_RELEASE, "WeightResultFragment onActivityResult userId is null");
                return;
            }
            this.mNewCreateUser = MultiUsersManager.INSTANCE.getSingleUserById(stringExtra);
            this.mUserAdapter.b(MultiUsersManager.INSTANCE.getAllUser());
            if (this.mNewCreateUser == null) {
                LogUtil.h(TAG_RELEASE, "WeightResultFragment onActivityResult user is null");
                return;
            }
            MyHandler myHandler = this.mHandler;
            if (myHandler != null) {
                myHandler.sendEmptyMessage(101);
            }
        }
    }

    private void sendBiEvent(cfi cfiVar) {
        if (cfiVar == null || this.mCurrentUser == null) {
            LogUtil.h(TAG_RELEASE, "WeightResultFragment Confirm save to the current user, not need biEvent");
        } else if (!this.mIsDataException) {
            LogUtil.h(TAG, "WeightResultFragment weight data not exception, not need biEvent");
        } else {
            if (cfiVar.i().equals(this.mCurrentUser.i())) {
                return;
            }
            cpz.b();
        }
    }

    static class MyHandler extends StaticHandler<WeightResultFragment> {
        MyHandler(WeightResultFragment weightResultFragment) {
            super(weightResultFragment);
        }

        @Override // com.huawei.health.device.wifi.lib.handler.StaticHandler
        public void handleMessage(WeightResultFragment weightResultFragment, Message message) {
            if (weightResultFragment == null) {
                LogUtil.h(WeightResultFragment.TAG_RELEASE, "WeightResultFragment MyHandler object is null");
                return;
            }
            if (message != null) {
                switch (message.what) {
                    case 100:
                        weightResultFragment.showConfirmResultDialog(weightResultFragment.mResources.getString(R.string._2130840654_res_0x7f020c4e));
                        break;
                    case 101:
                        weightResultFragment.refreshCurrentMeasureUser();
                        break;
                    case 102:
                    case 103:
                        weightResultFragment.initViewData();
                        break;
                    default:
                        LogUtil.h(WeightResultFragment.TAG_RELEASE, "WeightResultFragment MyHandler default");
                        break;
                }
                return;
            }
            LogUtil.h(WeightResultFragment.TAG_RELEASE, "WeightResultFragment MyHandler msg is null");
        }
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        MultiUsersManager.INSTANCE.setIsCheckMainUser(false);
        MyHandler myHandler = this.mHandler;
        if (myHandler != null) {
            myHandler.removeCallbacksAndMessages(null);
        }
    }
}
