package com.huawei.health.ecologydevice.ui.measure.fragment;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.json.JsonSanitizer;
import com.huawei.haf.design.pattern.Observer;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.health.R;
import com.huawei.health.device.connectivity.comm.MeasurableDevice;
import com.huawei.health.device.datatype.IntermitentJumpData;
import com.huawei.health.device.model.DeviceExtra;
import com.huawei.health.ecologydevice.callback.PickerChooseCallback;
import com.huawei.health.ecologydevice.fitness.RopeFittingsClient;
import com.huawei.health.ecologydevice.fitness.datastruct.RopeModeSettingData;
import com.huawei.health.ecologydevice.manager.EcologyDevicePickerManager;
import com.huawei.health.ecologydevice.manager.HpkPermissionManager;
import com.huawei.health.ecologydevice.manager.ResourceManager;
import com.huawei.health.ecologydevice.manager.RopeCloudAuthManager;
import com.huawei.health.ecologydevice.ui.measure.adapter.SecondRopeItemAdapter;
import com.huawei.health.ecologydevice.ui.measure.fragment.ropefragment.RopeSkipIntermittentTrainingFragment;
import com.huawei.health.ecologydevice.ui.measure.fragment.ropefragment.RopeSkipSettingFragment;
import com.huawei.health.ecologydevice.ui.measure.fragment.ropefragment.mediamanager.MediaManager;
import com.huawei.health.ecologydevice.ui.measure.fragment.ropefragment.mediamanager.VoiceCourseFragment;
import com.huawei.healthcloud.plugintrack.ui.activity.TrackDetailActivity;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.data.model.HiTrackMetaData;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hms.hihealth.data.Scopes;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginmgr.hwwear.bean.DeviceFittingsBean;
import com.huawei.ui.commonui.adapter.BaseRecyclerAdapter;
import com.huawei.ui.commonui.adapter.RecyclerHolder;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.imageview.HealthImageView;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import defpackage.cei;
import defpackage.czd;
import defpackage.dcz;
import defpackage.dds;
import defpackage.dhe;
import defpackage.dij;
import defpackage.diy;
import defpackage.djr;
import defpackage.dks;
import defpackage.gnm;
import defpackage.gso;
import defpackage.gvv;
import defpackage.gwg;
import defpackage.hcn;
import defpackage.hji;
import defpackage.ixx;
import defpackage.koq;
import defpackage.lql;
import defpackage.mst;
import defpackage.msx;
import defpackage.nrf;
import defpackage.nrz;
import defpackage.nsn;
import health.compact.a.CommonUtil;
import health.compact.a.CommonUtils;
import health.compact.a.LanguageUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.UnitUtil;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes3.dex */
public class SecondRopeIntroductionFragment extends BaseRopeIntroductionFragment implements View.OnClickListener, BaseRecyclerAdapter.OnItemClickListener<dhe> {
    private static final int ADD_FITTING_DEVICE = 0;
    private static final int CLICK_INTERVAL = 500;
    private static final int CONNECT_FITTING_DEVICE = 1;
    private static final int DEFAULT_MILLIS = 60;
    private static final int ENTRY_FITTING_SETTING = 2;
    private static final String FORMAT_HOUR_MIN = "HH:mm";
    private static final int GRID_COUNT = 2;
    private static final int MAXTIME_MILLIS_HIGH = 7200;
    private static final int MAXTIME_MILLIS_LOW = 5400;
    private static final int MAX_NUMBER = 10000;
    private static final int MEDIA_TYPE_COURSE = 0;
    private static final int MEDIA_TYPE_PLAYLISTS = 1;
    private static final int MINTIME_MILLIS = 30;
    private static final int MIN_NUMBER = 50;
    private static final int SECONDE_TO_MILLISECONDS = 1000;
    private static final int SPACING_DP = 6;
    private static final String TAG = "SecondRopeIntroductionFragment";
    private static final float TRANS_ALFA = 0.6f;
    private int mDefaultNum;
    private String mEnduranceAbility;
    private HealthImageView mFancyJumpIv;
    private LinearLayout mFancyJumpLayout;
    private HealthTextView mFancyJumpTv;
    private LinearLayout mFittingConnectLayout;
    private HealthImageView mFittingConnectStatusIcon;
    private HealthTextView mFittingConnectStatusView;
    private RelativeLayout mFittingItem;
    private String mFittingProductId;
    private dcz mFittingProductInfo;
    private String mFittingUniqueId;
    private Typeface mFontFamilyMedium;
    private Typeface mFontFamilyRegular;
    private HealthImageView mFreeJumpIv;
    private LinearLayout mFreeJumpLayout;
    private HealthTextView mFreeJumpTv;
    private boolean mIsActive;
    private boolean mIsInitMoreMenuView;
    private boolean mIsInitSettingMenuView;
    private boolean mIsShowFittingItem;
    private SecondRopeItemAdapter mItemAdapter;
    private RelativeLayout mLastRopeLayout;
    private LinearLayout mModeChooseLayout;
    private LinearLayout mModeSelectLayout;
    private EcologyDevicePickerManager mNumberJumpDialog;
    private HealthImageView mNumberJumpIv;
    private LinearLayout mNumberJumpLayout;
    private HealthTextView mNumberJumpTv;
    private int mNumberSelectIndex;
    private HealthTextView mRopeAverageData;
    private RopeCloudAuthManager mRopeCloudAuthManager;
    private HealthTextView mRopeDataUnit;
    private HealthTextView mRopeLastCount;
    private HealthTextView mRopeLastDay;
    private HealthTextView mRopeLastMonth;
    private HealthTextView mRopeLastTime;
    private RelativeLayout mRopeModeLayout;
    private HealthTextView mRopeModeTv;
    private HealthTextView mRopeModeValueTv;
    private RelativeLayout mRopeMonthDataLayout;
    private HealthImageView mRopeMonthIconRightIv;
    private HealthTextView mRopeMonthTotalDesc;
    private HealthImageView mRopeStartIv;
    private HealthRecycleView mRvSecondRopeItem;
    private HealthTextView mSingleSkippingTime;
    private EcologyDevicePickerManager mTimeJumpDialog;
    private HealthImageView mTimeJumpIv;
    private LinearLayout mTimeJumpLayout;
    private HealthTextView mTimeJumpTv;
    private int mTimeSelectIndex;
    private static final int[] TIME_TARGET_LIST = {1, 3, 5, 10, 15, 30, 60};
    private static final int[] NUMBER_TARGET_LIST = {80, 100, 200, 300, 500, 1000, 2000, 3000, 5000};
    private int mCurrentTargetModle = 6;
    private int mTarget = 0;
    private int mDefaultTime = 60;
    private List<dhe> mMenuItemBeans = new ArrayList(10);
    private String mFittingConnectStatus = "";
    private Observer mDeviceAssociateObserver = new Observer() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.SecondRopeIntroductionFragment.1
        @Override // com.huawei.haf.design.pattern.Observer
        public void notify(String str, Object... objArr) {
            LogUtil.a(SecondRopeIntroductionFragment.TAG, "notify, args = ", Arrays.toString(objArr));
            if (objArr.length == 0) {
                return;
            }
            Object obj = objArr[0];
            if (obj instanceof Integer) {
                int intValue = ((Integer) obj).intValue();
                if (intValue == 1) {
                    SecondRopeIntroductionFragment.this.checkUserAuthorization();
                    return;
                }
                if (intValue == 2) {
                    if (!TextUtils.isEmpty(SecondRopeIntroductionFragment.this.mFittingUniqueId)) {
                        dds.c().c(SecondRopeIntroductionFragment.this.mFittingUniqueId);
                    }
                    SecondRopeIntroductionFragment.this.mFittingConnectStatus = "STATUS_UNBIND";
                    SecondRopeIntroductionFragment.this.mFittingProductId = "";
                    SecondRopeIntroductionFragment.this.mFittingUniqueId = "";
                    SecondRopeIntroductionFragment.this.mFittingProductInfo = null;
                    SecondRopeIntroductionFragment.this.notifyFittingState();
                    return;
                }
                LogUtil.a(SecondRopeIntroductionFragment.TAG, "notify sendValue is other");
            }
        }
    };
    private RopeFittingsClient.FittingsMessageOrStateCallback mFittingsMessageOrStateCallback = new RopeFittingsClient.FittingsMessageOrStateCallback() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.SecondRopeIntroductionFragment.2
        @Override // com.huawei.health.ecologydevice.fitness.RopeFittingsClient.FittingsMessageOrStateCallback
        public void onNewMessage(String str, int i, Bundle bundle) {
            LogUtil.a(SecondRopeIntroductionFragment.TAG, "FittingsMessageOrStateCallback onNewMessage");
        }

        @Override // com.huawei.health.ecologydevice.fitness.RopeFittingsClient.FittingsMessageOrStateCallback
        public void onStateChange(String str, String str2) {
            LogUtil.a(SecondRopeIntroductionFragment.TAG, "FittingsMessageOrStateCallback onStateChange state =", str2);
            SecondRopeIntroductionFragment.this.mFittingConnectStatus = str2;
            SecondRopeIntroductionFragment.this.notifyFittingState();
        }
    };

    @Override // com.huawei.health.ecologydevice.ui.measure.fragment.BaseRopeIntroductionFragment
    protected int getLayoutId() {
        return R.layout.second_rope_introduction_layout;
    }

    @Override // com.huawei.health.ecologydevice.ui.measure.fragment.BaseRopeIntroductionFragment, com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        LogUtil.a(TAG, "in onCreate");
        this.mRopeCloudAuthManager = new RopeCloudAuthManager(this.mainActivity, this.mProductId, this.mUniqueId);
        ObserverManagerUtil.d(this.mDeviceAssociateObserver, "H5_PAGE_EXIT");
        ObserverManagerUtil.d(this.mDeviceAssociateObserver, "H5_UNBIND_DEVICE");
        downloadCoursesAndMusicsProfile();
        this.mRopeCloudAuthManager.d(this.mProductId, new RopeCloudAuthManager.DonwHkpListen() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.SecondRopeIntroductionFragment$$ExternalSyntheticLambda4
            @Override // com.huawei.health.ecologydevice.manager.RopeCloudAuthManager.DonwHkpListen
            public final void downHpkSuccessReSend() {
                SecondRopeIntroductionFragment.this.m337xfa2387ed();
            }
        });
    }

    /* renamed from: lambda$onCreate$0$com-huawei-health-ecologydevice-ui-measure-fragment-SecondRopeIntroductionFragment, reason: not valid java name */
    /* synthetic */ void m337xfa2387ed() {
        if (this.mRopeDeviceDataManager == null || this.mRopeCloudAuthManager == null) {
            return;
        }
        this.mRopeCloudAuthManager.d(this.mRopeDeviceDataManager.e() == 2);
    }

    @Override // com.huawei.health.ecologydevice.ui.measure.fragment.BaseRopeIntroductionFragment
    protected void initChildView() {
        if (this.child == null) {
            LogUtil.h(TAG, "initChildView child is null");
            return;
        }
        initData();
        initLayout();
        initRecyclerView();
        refreshRopeModeLayoutUi();
        initRtLanguageView();
        initTypface();
        initFittingData();
    }

    @Override // com.huawei.health.ecologydevice.ui.measure.fragment.BaseRopeIntroductionFragment
    protected void initUxConfig(czd czdVar) {
        czd.a aVar;
        boolean z;
        czd.b bVar;
        super.initUxConfig(czdVar);
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
        if (z) {
            this.mRopeHistoryLayout = (ViewGroup) this.child.findViewById(R.id.rope_history_layout);
        }
        initRecyclerViewData(aVar);
        initMoreMenuData(bVar);
        initFittingsView(czdVar);
    }

    private void controlModeLayoutByUxConfig(czd.e eVar) {
        boolean c = eVar.c();
        boolean i = eVar.i();
        boolean b = eVar.b();
        boolean d = eVar.d();
        this.mModeSelectLayout.removeAllViews();
        if (c) {
            this.mModeSelectLayout.addView(this.mFreeJumpLayout);
            this.mFreeJumpLayout.setVisibility(0);
        }
        if (i) {
            this.mModeSelectLayout.addView(this.mTimeJumpLayout);
            this.mTimeJumpLayout.setVisibility(0);
        }
        if (b) {
            this.mModeSelectLayout.addView(this.mNumberJumpLayout);
            this.mNumberJumpLayout.setVisibility(0);
        }
        if (d) {
            this.mModeSelectLayout.addView(this.mFancyJumpLayout);
            this.mFancyJumpLayout.setVisibility(0);
        }
        if (this.mModeSelectLayout.getChildCount() < 2) {
            this.mModeChooseLayout.setVisibility(8);
            this.mRopeModeTv.setVisibility(8);
        }
    }

    private void initLayout() {
        this.mRopeModeLayout = (RelativeLayout) this.child.findViewById(R.id.rope_mode_layout);
        HealthImageView healthImageView = (HealthImageView) this.child.findViewById(R.id.rope_start_iv);
        this.mRopeStartIv = healthImageView;
        healthImageView.setOnClickListener(this);
        this.mRopeModeTv = (HealthTextView) this.child.findViewById(R.id.rope_mode_tv);
        this.mRopeModeValueTv = (HealthTextView) this.child.findViewById(R.id.rope_mode_value_tv);
        this.mModeChooseLayout = (LinearLayout) this.child.findViewById(R.id.mode_choose_layout);
        this.mModeSelectLayout = (LinearLayout) this.child.findViewById(R.id.sport_mode_select_ll);
        LinearLayout linearLayout = (LinearLayout) this.child.findViewById(R.id.free_jump_layout);
        this.mFreeJumpLayout = linearLayout;
        linearLayout.setOnClickListener(this);
        LinearLayout linearLayout2 = (LinearLayout) this.child.findViewById(R.id.time_jump_layout);
        this.mTimeJumpLayout = linearLayout2;
        linearLayout2.setOnClickListener(this);
        LinearLayout linearLayout3 = (LinearLayout) this.child.findViewById(R.id.number_jump_layout);
        this.mNumberJumpLayout = linearLayout3;
        linearLayout3.setOnClickListener(this);
        LinearLayout linearLayout4 = (LinearLayout) this.child.findViewById(R.id.fancy_jump_layout);
        this.mFancyJumpLayout = linearLayout4;
        linearLayout4.setOnClickListener(this);
        this.mFancyJumpLayout.setVisibility(dij.g(this.mProductId) ? 0 : 8);
        this.mFreeJumpIv = (HealthImageView) this.child.findViewById(R.id.free_jump_iv);
        this.mFreeJumpTv = (HealthTextView) this.child.findViewById(R.id.free_jump_tv);
        this.mTimeJumpIv = (HealthImageView) this.child.findViewById(R.id.time_jump_iv);
        this.mTimeJumpTv = (HealthTextView) this.child.findViewById(R.id.time_jump_tv);
        this.mNumberJumpIv = (HealthImageView) this.child.findViewById(R.id.number_jump_iv);
        this.mNumberJumpTv = (HealthTextView) this.child.findViewById(R.id.number_jump_tv);
        this.mFancyJumpIv = (HealthImageView) this.child.findViewById(R.id.fancy_jump_iv);
        this.mFancyJumpTv = (HealthTextView) this.child.findViewById(R.id.fancy_jump_tv);
        RelativeLayout relativeLayout = (RelativeLayout) this.child.findViewById(R.id.sport_month_data_layout);
        this.mRopeMonthDataLayout = relativeLayout;
        relativeLayout.setOnClickListener(this);
        RelativeLayout relativeLayout2 = (RelativeLayout) this.child.findViewById(R.id.last_activity_layout);
        this.mLastRopeLayout = relativeLayout2;
        relativeLayout2.setOnClickListener(this);
        this.mRopeLastMonth = (HealthTextView) this.child.findViewById(R.id.activity_last_month);
        this.mRopeMonthTotalDesc = (HealthTextView) this.child.findViewById(R.id.activity_month_total_desc);
        this.mRopeLastDay = (HealthTextView) this.child.findViewById(R.id.activity_last_day);
        this.mRopeLastTime = (HealthTextView) this.child.findViewById(R.id.activity_last_time);
        this.mRopeLastCount = (HealthTextView) this.child.findViewById(R.id.activity_last_count);
        this.mRopeDataUnit = (HealthTextView) this.child.findViewById(R.id.activity_data_unit);
        this.mSingleSkippingTime = (HealthTextView) this.child.findViewById(R.id.single_skipping_time);
        this.mRopeAverageData = (HealthTextView) this.child.findViewById(R.id.activity_average_data);
        this.mRopeMonthIconRightIv = (HealthImageView) this.child.findViewById(R.id.rope_month_icon_right_iv);
        this.mRvSecondRopeItem = (HealthRecycleView) this.child.findViewById(R.id.rv_second_menu_item);
    }

    private void initFittingsView(czd czdVar) {
        this.mFittingItem = (RelativeLayout) this.child.findViewById(R.id.fitting_item);
        this.mFittingConnectLayout = (LinearLayout) this.child.findViewById(R.id.fitting_connect_layout);
        this.mFittingConnectStatusView = (HealthTextView) this.child.findViewById(R.id.fitting_connect_status);
        this.mFittingConnectStatusIcon = (HealthImageView) this.child.findViewById(R.id.fitting_connect_status_icon);
        this.mFittingItem.setOnClickListener(this);
        this.mFittingConnectLayout.setOnClickListener(this);
        List<msx> c = mst.a().c(this.mProductId);
        if (koq.b(c)) {
            return;
        }
        List<DeviceFittingsBean> b = c.get(0).b();
        if (czdVar != null) {
            this.mIsShowFittingItem = czdVar.i();
        } else {
            this.mIsShowFittingItem = koq.c(b);
        }
        if (this.mIsShowFittingItem) {
            this.mFittingItem.setVisibility(0);
            this.mFittingConnectStatusView.setTextColor(getResources().getColor(R.color._2131299012_res_0x7f090ac4));
            this.mFittingConnectStatusIcon.setImageResource(nsn.v(getContext()) ? R.drawable._2131428276_res_0x7f0b03b4 : R.drawable._2131428275_res_0x7f0b03b3);
            if (TextUtils.isEmpty(this.mFittingUniqueId)) {
                LogUtil.h(TAG, "initFittingData mFittingUniqueId is is empty");
                this.mFittingConnectStatusView.setText(this.mainActivity.getResources().getString(R.string._2130849778_res_0x7f022ff2));
                return;
            } else {
                this.mFittingConnectStatusView.setText(this.mainActivity.getResources().getString(R.string.IDS_device_rope_device_not_connected));
                return;
            }
        }
        this.mFittingItem.setVisibility(8);
    }

    private void initFittingData() {
        MeasurableDevice bondedDeviceByUniqueId = cei.b().getBondedDeviceByUniqueId(this.mUniqueId, false);
        if (bondedDeviceByUniqueId == null) {
            LogUtil.h(TAG, "initFittingData device is null");
            return;
        }
        DeviceExtra deviceExtra = (DeviceExtra) HiJsonUtil.e(bondedDeviceByUniqueId.getExtra(), DeviceExtra.class);
        if (deviceExtra != null && koq.c(deviceExtra.getFittings())) {
            List<DeviceExtra.DeviceData> fittings = deviceExtra.getFittings();
            this.mFittingProductId = fittings.get(0).getProductId();
            this.mFittingUniqueId = fittings.get(0).getUniqueId();
            this.mFittingProductInfo = ResourceManager.e().d(this.mFittingProductId);
            this.mFittingConnectStatus = "STATUS_DIS_CONNECT";
            return;
        }
        LogUtil.a(TAG, "initFittingData fittingsList is empty");
        this.mFittingConnectStatus = "STATUS_UNBIND";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyFittingState() {
        HandlerExecutor.e(new Runnable() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.SecondRopeIntroductionFragment$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                SecondRopeIntroductionFragment.this.m335x90fd6b0c();
            }
        });
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* renamed from: lambda$notifyFittingState$1$com-huawei-health-ecologydevice-ui-measure-fragment-SecondRopeIntroductionFragment, reason: not valid java name */
    /* synthetic */ void m335x90fd6b0c() {
        char c;
        if (this.mainActivity == null) {
            LogUtil.h(TAG, "notifyFittingState mainActivity is null");
        }
        LogUtil.a(TAG, "notifyFittingState mFittingConnectStatus = ", this.mFittingConnectStatus);
        this.mFittingConnectStatusIcon.setImageResource(nsn.v(getContext()) ? R.drawable._2131428276_res_0x7f0b03b4 : R.drawable._2131428275_res_0x7f0b03b3);
        this.mFittingConnectStatusView.setTextColor(this.mainActivity.getResources().getColor(R.color._2131299012_res_0x7f090ac4));
        String str = this.mFittingConnectStatus;
        str.hashCode();
        switch (str.hashCode()) {
            case -1108912082:
                if (str.equals("REQUEST_RECONNECT_DEVICE")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -739009232:
                if (str.equals("GET_SERVICE_SUCCESS")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -258967741:
                if (str.equals("STATUS_UNBIND")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -196955196:
                if (str.equals("STATUS_DISCOVERY_SERVICE_FAIL")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 385434494:
                if (str.equals("STATUS_GATT_STATE_CONNECTING")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 1047335072:
                if (str.equals("STATUS_CONNECT_FAIL")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 1527590188:
                if (str.equals("STATUS_DIS_CONNECT")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case 2101081667:
                if (str.equals("GATT_ERROR")) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
            case 4:
                this.mFittingConnectStatusView.setText(this.mainActivity.getResources().getString(R.string.IDS_device_rope_device_connecting));
                break;
            case 1:
                this.mFittingConnectStatusIcon.setImageResource(R.drawable._2131428274_res_0x7f0b03b2);
                this.mFittingConnectStatusView.setText(this.mainActivity.getResources().getString(R.string.IDS_device_rope_device_connected));
                break;
            case 2:
                this.mFittingConnectStatusView.setText(this.mainActivity.getResources().getString(R.string._2130849778_res_0x7f022ff2));
                break;
            case 3:
            case 7:
                if (!TextUtils.isEmpty(this.mFittingUniqueId)) {
                    dds.c().c(this.mFittingUniqueId);
                }
                this.mFittingConnectStatusView.setText(this.mainActivity.getResources().getString(R.string.IDS_device_rope_device_reconnect));
                this.mFittingConnectStatusView.setTextColor(this.mainActivity.getResources().getColor(R.color._2131296570_res_0x7f09013a));
                break;
            case 5:
                this.mFittingConnectStatusView.setText(this.mainActivity.getResources().getString(R.string.IDS_device_rope_device_reconnect));
                this.mFittingConnectStatusView.setTextColor(this.mainActivity.getResources().getColor(R.color._2131296570_res_0x7f09013a));
                break;
            case 6:
                this.mFittingConnectStatusView.setText(this.mainActivity.getResources().getString(R.string.IDS_device_rope_device_not_connected));
                break;
        }
    }

    private void initData() {
        String e = SharedPreferenceManager.e("second_rope_target", "rope_skipping_picker_number", "");
        if (!TextUtils.isEmpty(e)) {
            Map<String, Integer> map = (Map) gvv.a(e, new TypeToken<Map<String, Integer>>() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.SecondRopeIntroductionFragment.3
            });
            this.mDefaultNum = getCacheValue(map, "target_cache_value");
            this.mNumberSelectIndex = getCacheValue(map, "target_cache_select_index");
        }
        String e2 = SharedPreferenceManager.e("second_rope_target", "rope_skipping_picker_time", "");
        if (TextUtils.isEmpty(e2)) {
            return;
        }
        Map<String, Integer> map2 = (Map) gvv.a(e2, new TypeToken<Map<String, Integer>>() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.SecondRopeIntroductionFragment.4
        });
        this.mDefaultTime = getCacheValue(map2, "target_cache_value");
        this.mTimeSelectIndex = getCacheValue(map2, "target_cache_select_index");
    }

    private int getCacheValue(Map<String, Integer> map, String str) {
        if (map == null) {
            LogUtil.h(TAG, "getCacheValue() targetMap is null");
            return 0;
        }
        Integer num = map.get(str);
        if (num != null) {
            return num.intValue();
        }
        return 0;
    }

    private void initRecyclerView() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this.mainActivity, 2);
        gridLayoutManager.setOrientation(1);
        this.mRvSecondRopeItem.setLayoutManager(gridLayoutManager);
        SecondRopeItemAdapter secondRopeItemAdapter = new SecondRopeItemAdapter(this.mMenuItemBeans);
        this.mItemAdapter = secondRopeItemAdapter;
        secondRopeItemAdapter.setOnItemClickListener(this);
        this.mRvSecondRopeItem.setAdapter(this.mItemAdapter);
        this.mRvSecondRopeItem.setIsScroll(false);
        this.mRvSecondRopeItem.addItemDecoration(new RecyclerView.ItemDecoration() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.SecondRopeIntroductionFragment.5
            @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
            public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
                super.getItemOffsets(rect, view, recyclerView, state);
                int childAdapterPosition = recyclerView.getChildAdapterPosition(view);
                int a2 = hcn.a(SecondRopeIntroductionFragment.this.mainActivity, 6.0f);
                boolean bc = LanguageUtil.bc(SecondRopeIntroductionFragment.this.mainActivity);
                int i = childAdapterPosition % 2;
                if ((i == 0 && !bc) || (i == 1 && bc)) {
                    rect.right = a2;
                } else {
                    rect.left = a2;
                }
            }
        });
    }

    private void initRecyclerViewData(czd.a aVar) {
        boolean d;
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5;
        if (this.mIsInitSettingMenuView) {
            LogUtil.h(TAG, "initRecyclerViewData is not init again ");
            return;
        }
        boolean z6 = false;
        if (aVar != null) {
            z5 = aVar.f();
            if (aVar.c() && gwg.i(BaseApplication.getContext()) && CommonUtil.bd()) {
                z6 = true;
            }
            boolean a2 = aVar.a();
            boolean e = aVar.e();
            boolean d2 = aVar.d();
            boolean i = aVar.i();
            d = aVar.b();
            z = a2;
            z2 = e;
            z3 = d2;
            z4 = i;
        } else {
            if (gwg.i(BaseApplication.getContext()) && CommonUtil.bd()) {
                z6 = true;
            }
            boolean g = dij.g(this.mProductId);
            boolean g2 = dij.g(this.mProductId);
            boolean equals = "true".equals(dij.c("isSupportIntermit", this.mProductId));
            d = dij.d(this.mProductId);
            z = g;
            z2 = g2;
            z3 = equals;
            z4 = !d;
            z5 = true;
        }
        boolean z7 = d;
        boolean z8 = z6;
        if (z5) {
            convertItemData(R.string._2130842050_res_0x7f0211c2, R.drawable.hw_health_device_warm_up, 0, true, false);
        }
        if (z8) {
            convertItemData(R.string._2130842049_res_0x7f0211c1, R.drawable.hw_health_device_music, 1, true, false);
        }
        if (z) {
            convertItemData(R.string._2130850457_res_0x7f023299, R.drawable.hw_health_device_voice_course, 4, false, true);
        }
        if (z2) {
            convertItemData(R.string._2130850426_res_0x7f02327a, R.drawable.hw_health_device_exclusive_playlists, 5, false, true);
        }
        if (z3) {
            convertItemData(R.string._2130845806_res_0x7f02206e, R.drawable.hw_health_device_interval_training, 2, false, true);
        }
        if (z4) {
            convertItemData(R.string._2130845965_res_0x7f02210d, R.drawable.hw_health_device_setting, 3, false, true);
        }
        if (z7) {
            convertItemData(R.string._2130847649_res_0x7f0227a1, R.drawable.hw_health_device_multi_player_competition, 6, false, true);
        }
        this.mIsInitSettingMenuView = true;
    }

    private void updateRecyclerViewData(boolean z) {
        if (koq.b(this.mMenuItemBeans)) {
            LogUtil.h(TAG, "updateRecyclerViewData mMenuItemBeans is empty");
            return;
        }
        for (dhe dheVar : this.mMenuItemBeans) {
            if (dheVar.h()) {
                dheVar.d(z);
            }
        }
    }

    private void downloadCoursesAndMusicsProfile() {
        if (dij.g(this.mProductId)) {
            MediaManager mediaManager = new MediaManager();
            mediaManager.downloadJsonFile(0, true, null);
            mediaManager.downloadJsonFile(1, true, null);
        }
    }

    private void convertItemData(int i, int i2, int i3, boolean z, boolean z2) {
        dhe dheVar = new dhe();
        dheVar.d(getResources().getString(i));
        dheVar.d(i2);
        dheVar.e(i3);
        dheVar.d(z);
        dheVar.e(z2);
        this.mMenuItemBeans.add(dheVar);
    }

    @Override // com.huawei.ui.commonui.adapter.BaseRecyclerAdapter.OnItemClickListener
    public void onItemClicked(RecyclerHolder recyclerHolder, int i, dhe dheVar) {
        if (nsn.o()) {
            LogUtil.h(TAG, "onItemClicked click too fast");
        }
        switch (dheVar.b()) {
            case 0:
                diy.b(this.mainActivity, this.mCurrentTargetModle, this.mTarget);
                tickBiRopeLevel2PageBrowsing(this.mainActivity, "warm_up");
                break;
            case 1:
                diy.b(this.mainActivity, 283);
                tickBiRopeLevel2PageBrowsing(this.mainActivity, "music");
                djr.c().a(true);
                break;
            case 2:
                if (dheVar.a()) {
                    addFragment(new RopeSkipIntermittentTrainingFragment());
                    tickBiRopeLevel2PageBrowsing(this.mainActivity, "intermittent_setting");
                    break;
                }
                break;
            case 3:
                if (dheVar.a()) {
                    addFragment(new RopeSkipSettingFragment());
                    tickBiRopeLevel2PageBrowsing(this.mainActivity, "skip_settting");
                    break;
                }
                break;
            case 4:
                goToVoiceCourseFragment(dheVar.a(), 0);
                break;
            case 5:
                goToVoiceCourseFragment(dheVar.a(), 1);
                break;
            case 6:
                if (dheVar.a()) {
                    dks.a(getContext(), this.mProductId, this.mUniqueId, "#/?type=challengeOthersPage");
                    tickBiRopeLevel2PageBrowsing(this.mainActivity, "multi_player_competition");
                    break;
                }
                break;
        }
    }

    private void goToVoiceCourseFragment(boolean z, int i) {
        if (z) {
            Bundle bundle = new Bundle();
            VoiceCourseFragment voiceCourseFragment = new VoiceCourseFragment();
            bundle.putInt(MediaManager.KEY_MEDIA_TYPE, i);
            voiceCourseFragment.setArguments(bundle);
            addFragment(voiceCourseFragment);
            tickBiRopeLevel2PageBrowsing(this.mainActivity, i == 0 ? "voice_course" : "exclusive_playlists");
        }
    }

    private void initTypface() {
        if (this.mFontFamilyRegular == null) {
            this.mFontFamilyRegular = Typeface.create(getString(R.string._2130851582_res_0x7f0236fe), 0);
        }
        if (this.mFontFamilyMedium == null) {
            this.mFontFamilyMedium = Typeface.create(getString(R.string._2130851581_res_0x7f0236fd), 0);
        }
    }

    private void initMoreMenuData(czd.b bVar) {
        boolean equals;
        boolean equals2;
        boolean z;
        boolean z2;
        boolean z3;
        if (this.mIsInitMoreMenuView) {
            LogUtil.a(TAG, "initMoreMenuData is not init again");
            return;
        }
        if (bVar != null) {
            z = bVar.c();
            z2 = bVar.e();
            equals2 = bVar.b();
            z3 = false;
            equals = bVar.d() != null;
            if (equals && bVar.d().d()) {
                z3 = true;
            }
        } else {
            equals = "yes".equals(dij.c("ota", this.mProductId));
            equals2 = "true".equals(dij.c("isSupportWeight", this.mProductId));
            z = true;
            z2 = true;
            z3 = true;
        }
        if (z) {
            this.mMoreMenuList.add(getResources().getString(R.string.IDS_device_wear_home_delete_device));
        }
        if (equals) {
            this.mMoreMenuList.add(getResources().getString(R.string.IDS_device_wear_home_device_ota_upgrade));
        }
        if (z2) {
            this.mMoreMenuList.add(getResources().getString(R.string.IDS_device_rope_device_info));
        }
        if (equals2) {
            this.mMoreMenuList.add(getResources().getString(R.string.IDS_home_rope_device_authorization));
        }
        if (dij.g(this.mProductId) && CommonUtil.as()) {
            this.mMoreMenuList.add(getResources().getString(R.string._2130845807_res_0x7f02206f));
        }
        setOtaRedPoint(z3);
        if (koq.b(this.mMoreMenuList)) {
            this.mCustomTitleBar.setRightButtonVisibility(8);
        }
        this.mIsInitMoreMenuView = true;
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        LogUtil.a(TAG, "onpause");
        this.mIsActive = false;
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        LogUtil.a(TAG, "onHiddenChanged: ", Boolean.valueOf(z));
        this.mIsActive = !z;
    }

    @Override // com.huawei.health.ecologydevice.ui.measure.fragment.BaseRopeIntroductionFragment
    protected void onDeviceStateChanged() {
        super.onDeviceStateChanged();
        if (this.mRopeDeviceDataManager == null || checkMacAddress()) {
            return;
        }
        boolean z = this.mRopeDeviceDataManager.e() == 2;
        setViewClickable(z);
        if (this.mItemAdapter != null) {
            updateRecyclerViewData(z);
            this.mItemAdapter.refreshDataChange(this.mMenuItemBeans);
        }
        LogUtil.a(TAG, "loadH5SignIn in onDeviceStateChanged");
        this.mRopeCloudAuthManager.d(z);
        fittingDeviceConnect(z);
    }

    private void fittingDeviceConnect(boolean z) {
        if (!this.mIsShowFittingItem) {
            LogUtil.a(TAG, "fittingDeviceConnect mIsShowFittingItem is false");
            return;
        }
        if (TextUtils.isEmpty(this.mFittingUniqueId)) {
            LogUtil.a(TAG, "fittingDeviceConnect mFittingUniqueId is empty");
            initFittingData();
        }
        if (z) {
            if ("STATUS_UNBIND".equals(this.mFittingConnectStatus) || "STATUS_GATT_STATE_CONNECTING".equals(this.mFittingConnectStatus) || dds.c().a(this.mFittingUniqueId)) {
                LogUtil.a(TAG, "fittingDeviceConnect fitting device connected or unbind");
                return;
            }
            LogUtil.a(TAG, "fittingDeviceConnect fitting device connecting");
            this.mFittingConnectStatus = "STATUS_GATT_STATE_CONNECTING";
            dds.c().c(this.mFittingUniqueId, this.mFittingsMessageOrStateCallback);
            biEventForFittingDevice(1);
        } else if (TextUtils.isEmpty(this.mFittingUniqueId)) {
            this.mFittingConnectStatus = "STATUS_UNBIND";
        } else {
            this.mFittingConnectStatus = "STATUS_DIS_CONNECT";
            dds.c().c(this.mFittingUniqueId);
        }
        notifyFittingState();
    }

    @Override // com.huawei.health.ecologydevice.ui.measure.fragment.BaseRopeIntroductionFragment
    protected void selectingAuthorizationFunction() {
        if (this.mRopeDeviceDataManager != null) {
            this.mRopeCloudAuthManager.d();
        }
    }

    @Override // com.huawei.health.ecologydevice.ui.measure.fragment.BaseRopeIntroductionFragment
    protected void updataRopeConfigSetting() {
        super.updataRopeConfigSetting();
        LogUtil.a(TAG, "updataRopeConfigSetting");
        if (this.mRopeDeviceDataManager == null) {
            LogUtil.h(TAG, "updataRopeConfigSetting mRopeDeviceDataManager is null");
        } else {
            this.mRopeCloudAuthManager.e(this.mRopeDeviceDataManager.k());
        }
    }

    @Override // com.huawei.health.ecologydevice.ui.measure.fragment.BaseRopeIntroductionFragment, com.huawei.health.ecologydevice.ui.measure.fragment.ProductFragment, com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        LogUtil.a(TAG, "onResume");
        if (isHidden()) {
            return;
        }
        this.mIsActive = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkUserAuthorization() {
        if (this.mRopeDeviceDataManager == null) {
            LogUtil.h(TAG, "onResume mRopeDeviceDataManager is null");
        } else {
            this.mRopeCloudAuthManager.c(this.mRopeDeviceDataManager.e() == 2, this.mRopeDeviceDataManager.k());
        }
    }

    private void setViewClickable(boolean z) {
        this.mRopeStartIv.setClickable(z);
        this.mFreeJumpLayout.setClickable(z);
        this.mNumberJumpLayout.setClickable(z);
        this.mTimeJumpLayout.setClickable(z);
        this.mFancyJumpLayout.setClickable(z);
        float f = z ? 1.0f : TRANS_ALFA;
        this.mRopeModeLayout.setAlpha(f);
        this.mRopeModeLayout.setClickable(z);
        this.mModeChooseLayout.setAlpha(f);
        this.mModeChooseLayout.setClickable(z);
        this.mFittingItem.setAlpha(f);
        this.mFittingItem.setClickable(z);
        this.mFittingConnectLayout.setClickable(z);
    }

    private void initRtLanguageView() {
        if (LanguageUtil.bc(getActivity())) {
            this.mRopeMonthIconRightIv.setBackgroundResource(R.drawable._2131427841_res_0x7f0b0201);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (this.mainActivity == null) {
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        if (nsn.a(500)) {
            LogUtil.h(TAG, "onClick click too fast");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        if (view == this.mRopeStartIv) {
            diy.a(this.mainActivity, this.mCurrentTargetModle, this.mTarget, null, this.mProductId);
            gso.e().d(this.mCurrentTargetModle, this.mTarget);
        } else if (view == this.mFreeJumpLayout) {
            freeJumpClick();
        } else if (view == this.mTimeJumpLayout) {
            timeJumpClick();
        } else if (view == this.mNumberJumpLayout) {
            numberJumpClick();
        } else if (view == this.mFancyJumpLayout) {
            fancyJumpClick();
        } else if (view == this.mRopeMonthDataLayout) {
            diy.d(this.mainActivity, 283);
            tickBiRopeLevel2PageBrowsing(this.mainActivity, "history_list");
        } else if (view == this.mLastRopeLayout) {
            gotoTrackDetailActivity();
            tickBiRopeLevel2PageBrowsing(this.mainActivity, "history_detail");
            biEventForPerformanceDetail();
        } else if (view == this.mFittingItem) {
            String serialNumber = this.mRopeDeviceDataManager.d() == null ? "" : this.mRopeDeviceDataManager.d().getSerialNumber();
            if (TextUtils.isEmpty(this.mFittingProductId)) {
                diy.c(this.mainActivity, this.mProductId, serialNumber);
                biEventForFittingDevice(0);
            } else {
                dds.c().c(this.mFittingUniqueId);
                dks.c(this.mainActivity, this.mFittingProductInfo, this.mFittingProductId, this.mFittingUniqueId, serialNumber);
                biEventForFittingDevice(2);
            }
        } else if (view == this.mFittingConnectLayout) {
            fittingConnectStatusClick();
        } else {
            LogUtil.h(TAG, "onclick else");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void fittingConnectStatusClick() {
        char c;
        LogUtil.a(TAG, "fittingConnectStatusClick mFittingConnectStatus = ", this.mFittingConnectStatus);
        String str = this.mFittingConnectStatus;
        str.hashCode();
        switch (str.hashCode()) {
            case -258967741:
                if (str.equals("STATUS_UNBIND")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -196955196:
                if (str.equals("STATUS_DISCOVERY_SERVICE_FAIL")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 1047335072:
                if (str.equals("STATUS_CONNECT_FAIL")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 1527590188:
                if (str.equals("STATUS_DIS_CONNECT")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 2101081667:
                if (str.equals("GATT_ERROR")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c == 0) {
            diy.c(this.mainActivity, this.mProductId, this.mRopeDeviceDataManager.d() == null ? "" : this.mRopeDeviceDataManager.d().getSerialNumber());
            biEventForFittingDevice(0);
        } else {
            if (c == 1 || c == 2 || c == 3 || c == 4) {
                this.mFittingConnectStatus = "STATUS_GATT_STATE_CONNECTING";
                notifyFittingState();
                dds.c().c(this.mFittingUniqueId, this.mFittingsMessageOrStateCallback);
                biEventForFittingDevice(1);
                return;
            }
            LogUtil.a(TAG, "fittingConnectStatusClick mFittingConnectStatus is other");
        }
    }

    private void biEventForFittingDevice(int i) {
        HashMap hashMap = new HashMap(1);
        hashMap.put("click_type", Integer.valueOf(i));
        ixx.d().d(getContext(), AnalyticsValue.CLICK_FITTING_LIGHT_EFFECT_SETTING.value(), hashMap, 0);
    }

    private void biEventForPerformanceDetail() {
        if (TextUtils.isEmpty(this.mEnduranceAbility)) {
            LogUtil.a(TAG, "biEventForPerformanceDetail mEnduranceAbility is null");
            return;
        }
        try {
            if (Float.parseFloat(this.mEnduranceAbility) > 0.0f) {
                tickBiRopeLevel2PageBrowsing(this.mainActivity, "rope_performance_detail");
            }
        } catch (NumberFormatException unused) {
            LogUtil.b(TAG, "biEventForPerformanceDetail NumberFormatException");
        }
    }

    private void gotoTrackDetailActivity() {
        if (this.mRopeDeviceDataManager == null) {
            return;
        }
        HiHealthData c = this.mRopeDeviceDataManager.c();
        if (c == null) {
            LogUtil.a(TAG, "gotoTrackDetailActivity historyData is null");
        } else {
            this.mRopeDeviceDataManager.e(c.getStartTime(), c.getEndTime());
        }
    }

    private void fancyJumpClick() {
        if (this.mRopeDeviceDataManager != null) {
            LogUtil.a(TAG, "fancy jump click");
            this.mRopeDeviceDataManager.c(4, new int[0]);
        }
    }

    private void numberJumpClick() {
        if (this.mNumberJumpDialog == null) {
            this.mNumberJumpDialog = new EcologyDevicePickerManager.Builder(this.mainActivity).a(50.0d).e(10000.0d).c(this.mDefaultNum).c(this.mainActivity.getString(R.string._2130845803_res_0x7f02206b)).e(R.string._2130843701_res_0x7f021835).c(new PickerChooseCallback() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.SecondRopeIntroductionFragment$$ExternalSyntheticLambda2
                @Override // com.huawei.health.ecologydevice.callback.PickerChooseCallback
                public final void onValueSelected(Object obj, int i) {
                    SecondRopeIntroductionFragment.this.m336x1d69684f((Integer) obj, i);
                }
            }).e();
        }
        this.mNumberJumpDialog.b(NUMBER_TARGET_LIST, 5, R.plurals._2130903274_res_0x7f0300ea, this.mNumberSelectIndex);
    }

    /* renamed from: lambda$numberJumpClick$2$com-huawei-health-ecologydevice-ui-measure-fragment-SecondRopeIntroductionFragment, reason: not valid java name */
    /* synthetic */ void m336x1d69684f(Integer num, int i) {
        saveTargetData(num.intValue(), i, "rope_skipping_picker_number");
        if (this.mRopeDeviceDataManager != null) {
            LogUtil.a(TAG, "set jumpCount = ", num);
            this.mRopeDeviceDataManager.c(3, new int[]{num.intValue()});
        }
    }

    private void timeJumpClick() {
        if (this.mTimeJumpDialog == null) {
            this.mTimeJumpDialog = new EcologyDevicePickerManager.Builder(this.mainActivity).c(this.mainActivity.getString(R.string._2130845802_res_0x7f02206a)).b(dij.g(this.mProductId) ? MAXTIME_MILLIS_HIGH : MAXTIME_MILLIS_LOW).c(30).a(this.mDefaultTime).c(new PickerChooseCallback() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.SecondRopeIntroductionFragment$$ExternalSyntheticLambda1
                @Override // com.huawei.health.ecologydevice.callback.PickerChooseCallback
                public final void onValueSelected(Object obj, int i) {
                    SecondRopeIntroductionFragment.this.m338xbab0aa72((Integer) obj, i);
                }
            }).e();
        }
        this.mTimeJumpDialog.b(TIME_TARGET_LIST, 0, R.plurals._2130903270_res_0x7f0300e6, this.mTimeSelectIndex);
    }

    /* renamed from: lambda$timeJumpClick$3$com-huawei-health-ecologydevice-ui-measure-fragment-SecondRopeIntroductionFragment, reason: not valid java name */
    /* synthetic */ void m338xbab0aa72(Integer num, int i) {
        saveTargetData(num.intValue(), i, "rope_skipping_picker_time");
        if (this.mRopeDeviceDataManager != null) {
            LogUtil.a(TAG, "set jumpTime = ", num);
            this.mRopeDeviceDataManager.c(2, new int[]{num.intValue()});
        }
    }

    private void saveTargetData(int i, int i2, String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("target_cache_value", Integer.valueOf(i));
        hashMap.put("target_cache_select_index", Integer.valueOf(i2));
        SharedPreferenceManager.c("second_rope_target", str, lql.b(hashMap));
    }

    private void freeJumpClick() {
        if (this.mRopeDeviceDataManager != null) {
            LogUtil.a(TAG, "free jump click");
            this.mRopeDeviceDataManager.c(1, new int[0]);
        }
    }

    @Override // com.huawei.health.ecologydevice.ui.measure.fragment.BaseRopeIntroductionFragment
    protected void initDefaultRopeView() {
        LogUtil.a(TAG, "initDefaultRopeView()");
        if (this.mRopeHistoryLayout != null) {
            this.mRopeHistoryLayout.setVisibility(8);
        }
    }

    @Override // com.huawei.health.ecologydevice.ui.measure.fragment.BaseRopeIntroductionFragment
    protected void refreshLastRopeDataUi() {
        LogUtil.a(TAG, "refreshLastRopeDataUi()");
        if (this.mRopeHistoryLayout == null) {
            LogUtil.a(TAG, "is not show history record View");
            return;
        }
        if (this.mRopeDeviceDataManager != null) {
            HiHealthData c = this.mRopeDeviceDataManager.c();
            HiTrackMetaData hiTrackMetaData = (HiTrackMetaData) new Gson().fromJson(JsonSanitizer.sanitize(c.getMetaData()), HiTrackMetaData.class);
            if (hiTrackMetaData == null) {
                LogUtil.h(TAG, "refreshLastRopeDataUi trackMetaData is null");
                return;
            }
            Map<String, String> extendTrackMap = hiTrackMetaData.getExtendTrackMap();
            if (extendTrackMap == null || extendTrackMap.size() == 0) {
                return;
            }
            this.mRopeHistoryLayout.setVisibility(0);
            String str = extendTrackMap.get("skipSpeed");
            String str2 = extendTrackMap.get("skipNum");
            this.mEnduranceAbility = extendTrackMap.get("enduranceAbilityRank");
            this.mRopeLastCount.setText(UnitUtil.e(CommonUtils.h(str2), 1, 0));
            this.mRopeDataUnit.setText(this.mainActivity.getResources().getQuantityString(R.plurals._2130903274_res_0x7f0300ea, 0, ""));
            this.mRopeAverageData.setText(UnitUtil.e(CommonUtils.h(str), 1, 0));
            this.mSingleSkippingTime.setText(UnitUtil.d((int) (hiTrackMetaData.getTotalTime() / 1000)));
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FORMAT_HOUR_MIN, Locale.getDefault());
            long startTime = c.getStartTime();
            this.mRopeLastTime.setText(simpleDateFormat.format(Long.valueOf(startTime)));
            this.mRopeLastDay.setText(DateUtils.formatDateTime(this.mainActivity, startTime, 8));
            this.mRopeDeviceDataManager.b(this.mainActivity, calNaturalMonthStartTime(startTime, 0), calNaturalMonthStartTime(startTime, 1) - 1000, 283);
        }
    }

    private long calNaturalMonthStartTime(long j, int i) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(j));
        calendar.add(2, i);
        calendar.set(5, 1);
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        return calendar.getTimeInMillis();
    }

    @Override // com.huawei.health.ecologydevice.ui.measure.fragment.BaseRopeIntroductionFragment
    protected void refreshMonthDataUi() {
        LogUtil.a(TAG, "refreshMonthDataUi()");
        if (this.mRopeDeviceDataManager != null) {
            HiHealthData m = this.mRopeDeviceDataManager.m();
            this.mRopeMonthTotalDesc.setText(formatMonthSumStr(m));
            this.mRopeLastMonth.setText(DateUtils.formatDateTime(this.mainActivity, m.getStartTime(), 52));
        }
    }

    private String formatMonthSumStr(HiHealthData hiHealthData) {
        String string;
        double d = hiHealthData.getDouble("Track_Walk_Calorie_Sum");
        double d2 = hiHealthData.getDouble("Track_Walk_Duration_Sum");
        double d3 = hiHealthData.getDouble("Track_Walk_Count_Sum");
        double d4 = hiHealthData.getDouble("Track_Walk_Step_Sum");
        String quantityString = this.mainActivity.getResources().getQuantityString(R.plurals._2130903274_res_0x7f0300ea, (int) d4, UnitUtil.e(d4, 1, 0));
        String quantityString2 = this.mainActivity.getResources().getQuantityString(R.plurals._2130903213_res_0x7f0300ad, (int) d3, UnitUtil.e(d3, 1, 0));
        double d5 = d2 / 60000.0d;
        if (d5 > 60.0d) {
            string = this.mainActivity.getResources().getQuantityString(R.plurals._2130903223_res_0x7f0300b7, (int) d5, UnitUtil.e(d5 / 60.0d, 1, 1));
        } else {
            string = this.mainActivity.getResources().getString(R.string._2130837641_res_0x7f020089, UnitUtil.e(d5, 1, 2));
        }
        return this.mainActivity.getResources().getString(R.string._2130845547_res_0x7f021f6b, quantityString, string, quantityString2, hji.b(d / 1000.0d));
    }

    @Override // com.huawei.health.ecologydevice.ui.measure.fragment.BaseRopeIntroductionFragment
    protected void updateRopeMode() {
        if (!this.mIsActive) {
            LogUtil.h(TAG, "updateRopeMode the fragment is not active");
            return;
        }
        if (this.mRopeDeviceDataManager == null || this.mainActivity == null) {
            LogUtil.h(TAG, "updateRopeMode mRopeDeviceDataManager is null or mainActivity is null");
            return;
        }
        RopeModeSettingData g = this.mRopeDeviceDataManager.g();
        LogUtil.a(TAG, "updateRopeMode code is ", Integer.valueOf(g.getOpCode()));
        int opCode = g.getOpCode();
        if (opCode == 1) {
            this.mTarget = 0;
            this.mCurrentTargetModle = 6;
            refreshRopeModeLayoutUi();
            return;
        }
        if (opCode == 2) {
            this.mTarget = g.getConfigTimeJumpTime();
            this.mCurrentTargetModle = 0;
            refreshRopeModeLayoutUi();
        } else if (opCode == 3) {
            this.mTarget = g.getConfigNumberJumpCount();
            this.mCurrentTargetModle = 5;
            refreshRopeModeLayoutUi();
        } else {
            if (opCode != 4) {
                return;
            }
            this.mTarget = 0;
            this.mCurrentTargetModle = 7;
            refreshRopeModeLayoutUi();
        }
    }

    @Override // com.huawei.health.ecologydevice.ui.measure.fragment.BaseRopeIntroductionFragment, com.huawei.health.ecologydevice.callback.RopeDeviceDataListener
    public void onQuaryTrackDetailDataSuccess() {
        MotionPathSimplify h = this.mRopeDeviceDataManager.h();
        if (TextUtils.isEmpty(this.mRopeDeviceDataManager.n())) {
            LogUtil.h(TAG, "gotoTrackDetailActivity fileUrl is null");
            return;
        }
        gso.e().init(BaseApplication.getContext());
        Bundle bundle = new Bundle();
        bundle.putSerializable("simplifyDatakey", h);
        bundle.putString("contentpath", this.mRopeDeviceDataManager.n());
        bundle.putBoolean("isAfterSport", false);
        bundle.putBoolean("isNotNeedDeleteFile", false);
        Intent intent = new Intent(BaseApplication.getContext(), (Class<?>) TrackDetailActivity.class);
        intent.putExtras(bundle);
        intent.addFlags(268435456);
        gnm.aPB_(BaseApplication.getContext(), intent);
    }

    @Override // com.huawei.health.ecologydevice.callback.RopeDeviceDataListener
    public void onRopeSportStatusChange(int i) {
        if (i == 1000) {
            checkPermission(this.mFittingProductId);
        } else if (i == 1001) {
            dds.c().d(false);
        } else {
            LogUtil.a(TAG, "sportStatus ", Integer.valueOf(i));
        }
    }

    private void checkPermission(final String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        new HpkPermissionManager(str).b(Scopes.HEALTHKIT_EXTEND_SPORT_READ, new HpkPermissionManager.PermissionAuthenticationListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.SecondRopeIntroductionFragment$$ExternalSyntheticLambda0
            @Override // com.huawei.health.ecologydevice.manager.HpkPermissionManager.PermissionAuthenticationListener
            public final void onResult(boolean z) {
                SecondRopeIntroductionFragment.lambda$checkPermission$4(str, z);
            }
        });
    }

    static /* synthetic */ void lambda$checkPermission$4(String str, boolean z) {
        SharedPreferenceManager.e("ecology_device_module", str, z);
        dds.c().d(z);
    }

    private void refreshRopeModeLayoutUi() {
        LogUtil.a(TAG, "refreshRopeModeLayoutUi currentTargetModle is ", Integer.valueOf(this.mCurrentTargetModle));
        if (this.mCurrentTargetModle == 6) {
            this.mRopeModeValueTv.setVisibility(8);
            this.mRopeModeTv.setText(this.mainActivity.getResources().getString(R.string._2130845801_res_0x7f022069));
            this.mFreeJumpIv.setImageResource(R.drawable.model_device_ic_free_selected);
        } else {
            this.mFreeJumpIv.setImageDrawable(getTintDrawable(R.drawable.rope_device_ic_free_regular, R.color._2131299011_res_0x7f090ac3));
        }
        if (this.mCurrentTargetModle == 0) {
            this.mRopeModeValueTv.setVisibility(0);
            this.mRopeModeValueTv.setText(R.string._2130845802_res_0x7f02206a);
            this.mRopeModeTv.setText(UnitUtil.d(this.mTarget));
            this.mTimeJumpIv.setImageResource(R.drawable.model_device_ic_time_selected);
        } else {
            this.mTimeJumpIv.setImageDrawable(getTintDrawable(R.drawable.rope_device_ic_time_regular, R.color._2131299011_res_0x7f090ac3));
        }
        if (this.mCurrentTargetModle == 5) {
            this.mRopeModeValueTv.setVisibility(0);
            this.mRopeModeValueTv.setText(R.string._2130845803_res_0x7f02206b);
            HealthTextView healthTextView = this.mRopeModeTv;
            Resources resources = this.mainActivity.getResources();
            int i = this.mTarget;
            healthTextView.setText(resources.getQuantityString(R.plurals._2130903272_res_0x7f0300e8, i, Integer.valueOf(i)));
            this.mNumberJumpIv.setImageResource(R.drawable.model_device_ic_count_selected);
        } else {
            this.mNumberJumpIv.setImageDrawable(getTintDrawable(R.drawable.rope_device_ic_count_regular, R.color._2131299011_res_0x7f090ac3));
        }
        if (this.mCurrentTargetModle == 7) {
            this.mRopeModeValueTv.setVisibility(8);
            this.mRopeModeTv.setText(this.mainActivity.getResources().getString(R.string._2130847626_res_0x7f02278a));
            this.mFancyJumpIv.setImageResource(R.drawable.rope_device_ic_fancy_selected);
        } else {
            this.mFancyJumpIv.setImageDrawable(getTintDrawable(R.drawable.rope_device_ic_fancy_regular, R.color._2131299011_res_0x7f090ac3));
        }
        this.mFreeJumpTv.setTypeface(this.mCurrentTargetModle == 6 ? this.mFontFamilyMedium : this.mFontFamilyRegular);
        this.mTimeJumpTv.setTypeface(this.mCurrentTargetModle == 0 ? this.mFontFamilyMedium : this.mFontFamilyRegular);
        this.mNumberJumpTv.setTypeface(this.mCurrentTargetModle == 5 ? this.mFontFamilyMedium : this.mFontFamilyRegular);
        this.mFancyJumpTv.setTypeface(this.mCurrentTargetModle == 7 ? this.mFontFamilyMedium : this.mFontFamilyRegular);
    }

    private Drawable getTintDrawable(int i, int i2) {
        Drawable cJH_ = nrf.cJH_(getResources().getDrawable(i), this.mainActivity.getResources().getColor(i2));
        return LanguageUtil.bc(this.mainActivity) ? nrz.cKm_(this.mainActivity, cJH_) : cJH_;
    }

    @Override // com.huawei.health.ecologydevice.ui.measure.fragment.BaseRopeIntroductionFragment
    protected void startReverseControl() {
        if (!this.mIsActive) {
            LogUtil.h(TAG, "startReverseControl the fragment is not active");
        }
        if (this.mainActivity == null || this.mRopeDeviceDataManager == null) {
            LogUtil.h(TAG, "startReverseControl mainActivity is null or mRopeDeviceDataManager is null");
            return;
        }
        LogUtil.a(TAG, "startReverseControl() mode:", Integer.valueOf(this.mCurrentTargetModle), " target:", Integer.valueOf(this.mTarget));
        if (checkMacAddress()) {
            LogUtil.a(TAG, "The current device details page does not belong to this device.");
            return;
        }
        RopeModeSettingData g = this.mRopeDeviceDataManager.g();
        switch (g.getOpCode()) {
            case 1:
            case 2:
            case 3:
            case 4:
                diy.a(this.mainActivity, this.mCurrentTargetModle, this.mTarget, null, this.mProductId);
                break;
            case 5:
                IntermitentJumpData intermitentJumpData = new IntermitentJumpData();
                intermitentJumpData.setIntermittentJumpMode(g.getConfigIntermittentJumpTime() == 0 ? 0 : 1);
                intermitentJumpData.setIntermittentJumpExerciseTime(g.getConfigIntermittentJumpTime());
                intermitentJumpData.setIntermittentJumpExerciseNum(g.getConfigIntermittentJumpSingleCount());
                intermitentJumpData.setIntermittentJumpBreakTime(g.getConfigIntermittentJumpResetTime());
                intermitentJumpData.setIntermittentJumpGroups(g.getConfigIntermittentJumpCount());
                diy.a(this.mainActivity, 8, 0, intermitentJumpData, this.mProductId);
                break;
            case 6:
                diy.a(this.mainActivity, 10, g.getCourseId(), null, this.mProductId);
                break;
            case 7:
                diy.a(this.mainActivity, 11, g.getMusicId(), null, this.mProductId);
                break;
        }
    }

    @Override // com.huawei.health.ecologydevice.ui.measure.fragment.BaseRopeIntroductionFragment, com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        Observer observer = this.mDeviceAssociateObserver;
        if (observer != null) {
            ObserverManagerUtil.e(observer, "H5_PAGE_EXIT");
            ObserverManagerUtil.e(this.mDeviceAssociateObserver, "H5_UNBIND_DEVICE");
            this.mDeviceAssociateObserver = null;
        }
        RopeCloudAuthManager ropeCloudAuthManager = this.mRopeCloudAuthManager;
        if (ropeCloudAuthManager != null) {
            ropeCloudAuthManager.c();
        }
        if (TextUtils.isEmpty(this.mFittingUniqueId)) {
            return;
        }
        dds.c().c(this.mFittingUniqueId);
    }
}
