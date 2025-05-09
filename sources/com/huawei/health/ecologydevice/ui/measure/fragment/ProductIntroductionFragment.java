package com.huawei.health.ecologydevice.ui.measure.fragment;

import android.app.NotificationManager;
import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.health.R;
import com.huawei.health.device.api.DeviceFragmentFactoryApi;
import com.huawei.health.device.api.DeviceMainActivityApi;
import com.huawei.health.device.api.HealthDeviceEntryApi;
import com.huawei.health.device.api.HonourDeviceConstantsApi;
import com.huawei.health.device.api.MeasureKitManagerApi;
import com.huawei.health.device.api.WeightDataUtilsApi;
import com.huawei.health.device.connectivity.comm.MeasurableDevice;
import com.huawei.health.device.constants.WeightConstants;
import com.huawei.health.device.manager.ResourceFileListener;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.ecologydevice.callback.IdialogButtonClickCallback;
import com.huawei.health.ecologydevice.clouddevice.DownloadDeviceInfoCallBack;
import com.huawei.health.ecologydevice.manager.BloodPressureStartFromDeviceHelper;
import com.huawei.health.ecologydevice.manager.DeviceUnbindHelper;
import com.huawei.health.ecologydevice.manager.GprsDeviceHelper;
import com.huawei.health.ecologydevice.manager.ResourceManager;
import com.huawei.health.ecologydevice.networkclient.HealthEngineRequestManager;
import com.huawei.health.ecologydevice.open.data.model.CommonUiResponse;
import com.huawei.health.ecologydevice.ui.measure.activity.DeviceUsageDescriptionActivity;
import com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceBindGuidFragment;
import com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceMeasureGuideShowFragment;
import com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceScanningFragment;
import com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceSilentGuideFragment;
import com.huawei.health.ecologydevice.ui.util.PermissionDialogHelper;
import com.huawei.health.messagecenter.model.MessageConstant;
import com.huawei.health.suggestion.model.FitRunPlayAudio;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwbtsdk.btdatatype.callback.BtSwitchStateCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.HealthAccessTokenUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.nfc.PluginPayAdapter;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.dotspageindicator.HealthDotsPageIndicator;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.popupview.PopViewList;
import com.huawei.ui.commonui.progressbar.HealthProgressBar;
import com.huawei.ui.commonui.scrollview.HealthScrollView;
import com.huawei.ui.commonui.viewpager.HealthViewPager;
import com.huawei.watchface.videoedit.presenter.PresenterUtils;
import defpackage.cei;
import defpackage.cez;
import defpackage.cpp;
import defpackage.cwt;
import defpackage.dcq;
import defpackage.dcu;
import defpackage.dcx;
import defpackage.dcz;
import defpackage.ddx;
import defpackage.deb;
import defpackage.dei;
import defpackage.deq;
import defpackage.dfq;
import defpackage.dgo;
import defpackage.dif;
import defpackage.dij;
import defpackage.diy;
import defpackage.dja;
import defpackage.dks;
import defpackage.dkw;
import defpackage.dlb;
import defpackage.gmz;
import defpackage.ixx;
import defpackage.iyl;
import defpackage.jdx;
import defpackage.jeg;
import defpackage.koq;
import defpackage.nkx;
import defpackage.nrh;
import defpackage.nsf;
import defpackage.nsj;
import defpackage.nsn;
import defpackage.nsy;
import health.compact.a.CommonUtil;
import health.compact.a.CompileParameterUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import java.io.File;
import java.lang.ref.WeakReference;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes3.dex */
public class ProductIntroductionFragment extends ProductFragment implements View.OnClickListener {
    private static final String AM16_INFO = "IDS_DEVICE_ERGONOMIC_SH_2260_334";
    private static final double AM16_INFO_2 = 2.0d;
    private static final double AM16_INFO_5 = 5.0d;
    private static final String AM16_INFO_OTHER = "IDS_DEVICE_USING_CSR_CH_2260_500";
    private static final String BLOOD_SUGAR_UNIT_MMOL_L = "mmol/L";
    private static final String BUY_URL = "buy_url";
    private static final int CONVERSION_RATE = 1024;
    private static final int DELETE_DEVICE_POSITION = 0;
    private static final String DEVICE = "Device";
    private static final String DNURSE_GLUCOSE_PRODUCT_ID = "9bf158ba-49b0-46aa-9fdf-ed75da1569cf";
    private static final long ERROR_TIME = 200;
    private static final String EXTRA_BI_ID = "EXTRA_BI_ID";
    private static final String EXTRA_BI_NAME = "EXTRA_BI_NAME";
    private static final String EXTRA_BI_SOURCE = "EXTRA_BI_SOURCE";
    private static final String HAVE_DATA_KEY = "haveDataKey";
    private static final String HONOR_SHOW_PRIVACY_KEY = "honor_show_privacy_key";
    private static final String HUAWEI_FIT = "HUAWEI FIT";
    private static final String IS_BIND_SUCCESS = "isBindSuccess";
    private static final String KEY_PRODUCT_ID = "productId";
    private static final double LEGAL_WEIGHT_BOUNDARY_VALUE = 0.0d;
    private static final int LRU_CACHE_SIZE = 1048576;
    private static final String METIS_INFO = "IDS_device_introductionactivity_metis_introduction_1";
    private static final double METIS_INFO_24 = 24.0d;
    private static final String METIS_PRODUCTID = "9323f6b7-b459-44f4-a698-988d1769832a";
    private static final String OPERATE_DELETE = "Delete";
    private static final int PRIVACY_AGREE = 1;
    private static final int PRIVACY_REQUEST_CODE = 1001;
    private static final int PRIVACY_RESULT_CODE = 3;
    private static final int PRO_TO_FAILED = 2;
    private static final int PRO_TO_REFRESH = 1;
    private static final int PRO_TO_REFRESH_DIALOG = 5;
    private static final int PRO_TO_REFRESH_GROUP = 4;
    private static final int PRO_UNZIP_SUCCESS = 3;
    private static final int REQUEST_GPS_LOCATION = 1;
    private static final int REQUEST_NOTIFICATION_MANAGER = 0;
    private static final String TAG = "ProductIntroductionFragment";
    private static final float TEXT_SIZE = 18.0f;
    private static final int UPDATE_BLOOD_OXYGEN_DATA = 105;
    private static final int UPDATE_BLOOD_PRESSURE_DATA = 101;
    private static final int UPDATE_BLOOD_SUGAR_DATA = 102;
    private static final int UPDATE_BLOOD_SUGAR_MEASURE_COUNT = 107;
    private static final int UPDATE_BODY_TEMPERATURE_DATA = 104;
    private static final int UPDATE_DATA_EMPTY = 103;
    private static final int UPDATE_URIC_ACID_DATA = 106;
    private static final int UPDATE_WEIGHT_DATA = 100;
    private static final String URIC_ACID_UNIT_MICRO_MOLE_L = "μmol/L";
    private RelativeLayout mBloodPressureFaqsCloseVoice;
    private LinearLayout mBloodPressureFaqsLayout;
    private RelativeLayout mBloodPressureFaqsSyncData;
    private LinearLayout mBuyDeviceRemovePairLayout;
    private HealthTextView mBuyDeviceTextView;
    private LinearLayout mCarouselLayout;
    private HealthTextView mConnectStatusTextView;
    private TextView mDataNotificationCancel;
    private TextView mDataNotificationGo;
    private ViewGroup mDataNotificationLayout;
    private DeviceFragmentFactoryApi mDeviceFragmentFactoryApi;
    private HealthTextView mDeviceLastMeasurementView;
    private ViewGroup mDeviceLatestDataAndCountLayout;
    private ViewGroup mDeviceLatestValueLayout;
    private DeviceMainActivityApi mDeviceMainActivityApi;
    private HealthTextView mDeviceMeasureBloodSugarLevelView;
    private HealthTextView mDeviceMeasureCountView;
    private HealthTextView mDeviceMeasureDateView;
    private HealthTextView mDeviceMeasureLatestTimeView;
    private HealthTextView mDeviceMeasureTimePeriodView;
    private ViewGroup mDeviceMeasureValueLayout;
    private HealthTextView mDeviceMeasureValueUnitView;
    private HealthTextView mDeviceMeasureValueView;
    private ImageView mDeviceShowImage;
    private LinearLayout mDeviceStartMeasureLayout;
    private HealthDotsPageIndicator mDotPageIndicator;
    private RelativeLayout mFeatureDescriptionLayout;
    private LinearLayout mHavePairLayout;
    private HealthDeviceEntryApi mHealthDeviceEntryApi;
    private HonourDeviceConstantsApi mHonourDeviceConstantsApi;
    private dgo mImgPagerAdapter;
    private HealthDevice.HealthDeviceKind mKind;
    private RelativeLayout mLastMeasurementLayout;
    private HealthProgressBar mLoadingProgressBar;
    private BroadcastReceiver mLocalBroadcastReceiver;
    private NetWorkChangeBroadcastReceiver mNetWorkChangeReceiver;
    private PermissionDialogHelper mPermissionDialogHelper;
    private PopViewList mPopView;
    private String mPrivacyImagePath;
    private HealthTextView mPrompt;
    private CustomTextAlertDialog mRingerModeDialog;
    private HealthProgressBar mStartMeasureProgressBar;
    private HealthTextView mStartMeasureTextView;
    private HealthButton mStartPairButton;
    private LinearLayout mStartPairLayout;
    private LinearLayout mSwitchSettingLayout;
    private ArrayList<String> mTextList;
    private HealthTextView mUricAcidDateView;
    private ImageView mUricAcidValueArrowImage;
    private RelativeLayout mUricAcidValueLayout;
    private HealthTextView mUricAcidValueLevelView;
    private HealthTextView mUricAcidValueUnitView;
    private HealthTextView mUricAcidValueView;
    private HealthViewPager mViewPager;
    private HealthScrollView mWholeScroll;
    private LruCache<String, Bitmap> mBitmapLruCache = new LruCache<>(1048576);
    private boolean mIsFirstDownload = false;
    private boolean mIsFinishDeviceMainActivity = false;
    private ArrayList<String> mToDownloadList = new ArrayList<>(10);
    private ArrayList<String> mProductIdList = new ArrayList<>(10);
    private ArrayList<String> mImgPathList = new ArrayList<>(10);
    private boolean mIsRebind = false;
    private boolean mIsShowDelete = false;
    private Handler mHandler = new QueryDataHandler(this);
    private final Handler mProHandler = new ProHandler(this);
    private HealthViewPager.OnPageChangeListener mOnPageChangeListener = new HealthViewPager.OnPageChangeListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.ProductIntroductionFragment.1
        @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
        public void onPageSelected(int i) {
            LogUtil.a(ProductIntroductionFragment.TAG, "ProductIntroductionFragment onPageSelected() position = ", Integer.valueOf(i));
            ProductIntroductionFragment productIntroductionFragment = ProductIntroductionFragment.this;
            productIntroductionFragment.setmPromptText(i, productIntroductionFragment.mPrompt);
        }

        @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
        public void onPageScrolled(int i, float f, int i2) {
            LogUtil.a(ProductIntroductionFragment.TAG, "ProductIntroductionFragment onPageSelected() position = ", Integer.valueOf(i));
        }

        @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
        public void onPageScrollStateChanged(int i) {
            LogUtil.a(ProductIntroductionFragment.TAG, "ProductIntroductionFragment stateChanged: position = ", Integer.valueOf(i));
        }
    };
    private BtSwitchStateCallback mBtSwitchStateCallback = new BtSwitchStateCallback() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.ProductIntroductionFragment.2
        @Override // com.huawei.hwbtsdk.btdatatype.callback.BtSwitchStateCallback
        public void onBtSwitchStateCallback(int i) {
            LogUtil.a(ProductIntroductionFragment.TAG, "onBtSwitchStateCallback", Integer.valueOf(i));
            if (i == 3) {
                iyl.d().e(this);
                ProductIntroductionFragment.this.mPermissionDialogHelper.d(ProductIntroductionFragment.this.mProductInfo, ProductIntroductionFragment.this.mProductId);
            }
        }
    };
    private BloodPressureStartFromDeviceHelper.StartFromDeviceListen mStartFromDeviceListen = new BloodPressureStartFromDeviceHelper.StartFromDeviceListen() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.ProductIntroductionFragment.3
        @Override // com.huawei.health.ecologydevice.manager.BloodPressureStartFromDeviceHelper.StartFromDeviceListen
        public void onStatusChanged(int i) {
            Message message = new Message();
            message.what = 10001;
            message.obj = Integer.valueOf(i);
            ProductIntroductionFragment.this.mProHandler.sendMessage(message);
        }

        @Override // com.huawei.health.ecologydevice.manager.BloodPressureStartFromDeviceHelper.StartFromDeviceListen
        public void onMeasureChanged(int i) {
            ProductIntroductionFragment.this.mProHandler.sendEmptyMessage(i);
        }
    };

    static class ProHandler extends BaseHandler<ProductIntroductionFragment> {
        ProHandler(ProductIntroductionFragment productIntroductionFragment) {
            super(productIntroductionFragment);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        public void handleMessageWhenReferenceNotNull(ProductIntroductionFragment productIntroductionFragment, Message message) {
            if (message == null) {
                LogUtil.a(ProductIntroductionFragment.TAG, "ProductIntroductionFragment mProHandler handleMessage:msg == null");
                return;
            }
            boolean isHonourDevice = ((HonourDeviceConstantsApi) Services.c("PluginDevice", HonourDeviceConstantsApi.class)).isHonourDevice(productIntroductionFragment.mProductId);
            int i = message.what;
            if (i == 1) {
                LogUtil.a(ProductIntroductionFragment.TAG, "ProductIntroductionFragment REFRESH");
                productIntroductionFragment.refreshData(message);
                return;
            }
            if (i == 2) {
                LogUtil.a(ProductIntroductionFragment.TAG, "ProductIntroductionFragment FAILED");
                if (!isHonourDevice || new File(dcq.b().a(productIntroductionFragment.mProductId, productIntroductionFragment.mProductInfo.e().get(0).e())).exists()) {
                    return;
                }
                productIntroductionFragment.mStartPairButton.setEnabled(false);
                productIntroductionFragment.mBuyDeviceTextView.setEnabled(false);
                return;
            }
            if (i == 3) {
                productIntroductionFragment.init();
                return;
            }
            if (i == 4) {
                LogUtil.a(ProductIntroductionFragment.TAG, " PRO_TO_REFRESH_GROUP startDialog ");
                if (isHonourDevice) {
                    productIntroductionFragment.startDialog();
                    return;
                }
                return;
            }
            if (i != 5) {
                productIntroductionFragment.otherMsg(message);
            } else if (isHonourDevice && (message.obj instanceof String)) {
                productIntroductionFragment.showDialog((String) message.obj);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void otherMsg(Message message) {
        if (BloodPressureStartFromDeviceHelper.a(dij.e(this.mProductId))) {
            switch (message.what) {
                case 10001:
                    if (message.obj instanceof Integer) {
                        updataBleStatus(((Integer) message.obj).intValue());
                        break;
                    }
                    break;
                case 10002:
                    showMeasureOver();
                    queryMeasureData();
                    break;
                case 10003:
                    BloodPressureStartFromDeviceHelper.d().e(1);
                    showMeasureOver();
                    BloodPressureStartFromDeviceHelper.d().d(this.mainActivity, getString(R.string.IDS_device_measure_fail));
                    break;
                case 10004:
                    showStartMeasureAnimation();
                    break;
                default:
                    LogUtil.h(TAG, "mProHandler is otherMsg");
                    break;
            }
        }
    }

    private void updataBleStatus(int i) {
        if (BloodPressureStartFromDeviceHelper.c()) {
            if (i == 1) {
                this.mConnectStatusTextView.setText(R.string.IDS_device_rope_device_connecting);
                this.mStartMeasureTextView.setText(R.string.IDS_device_measureactivity_guide_start_measure);
                return;
            }
            if (i == 2) {
                this.mConnectStatusTextView.setText(R.string.IDS_device_rope_device_connected);
                this.mStartMeasureTextView.setText(R.string.IDS_device_measureactivity_guide_start_measure);
                return;
            }
            if (i == 3 || i == 5 || i == 16) {
                this.mConnectStatusTextView.setText(R.string.IDS_device_rope_device_not_connected);
                this.mStartMeasureTextView.setText(R.string.IDS_device_rope_device_reconnect);
                this.mStartMeasureProgressBar.setVisibility(4);
                this.mProHandler.removeMessages(10003);
                if (BloodPressureStartFromDeviceHelper.d().b() == 0) {
                    BloodPressureStartFromDeviceHelper.d().e(1);
                    BloodPressureStartFromDeviceHelper.d().d(this.mainActivity, getString(R.string.IDS_device_measure_fail));
                    return;
                }
                return;
            }
            LogUtil.h(TAG, "updataBleStatus is other");
        }
    }

    @Override // com.huawei.health.ecologydevice.ui.measure.fragment.ProductFragment
    protected void updateUIDisconnect() {
        LogUtil.a(TAG, "updateUIDisconnect");
    }

    private void showStartMeasureAnimation() {
        if (BloodPressureStartFromDeviceHelper.d().a() == 2) {
            this.mStartMeasureTextView.setText(R.string.IDS_device_measureactivity_measuring);
            this.mStartMeasureProgressBar.setVisibility(0);
            this.mProHandler.sendEmptyMessageDelayed(10003, 120000L);
        }
    }

    private void showMeasureOver() {
        this.mStartMeasureProgressBar.setVisibility(4);
        updataBleStatus(BloodPressureStartFromDeviceHelper.d().a());
        this.mProHandler.removeMessages(10003);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshData(Message message) {
        dcz dczVar;
        if (message.obj instanceof String) {
            dczVar = ResourceManager.e().d((String) message.obj);
        } else {
            dczVar = null;
        }
        if (dczVar != null) {
            this.mProductInfo = dczVar;
            ArrayList<String> arrayList = new ArrayList<>(10);
            if (dczVar.e().size() == 0) {
                LogUtil.a(TAG, "productInfo.descriptions.size()");
                return;
            }
            addImageAndText(dczVar.e().size(), arrayList, dczVar.t(), dczVar, false);
            if (isAdded()) {
                setmPromptText(0, this.mPrompt);
            }
            hideLoadingProgressBar();
            this.mViewPager.setVisibility(0);
            this.mImgPagerAdapter.c(arrayList);
            this.mImgPagerAdapter.notifyDataSetChanged();
            setBuyBtnVisible();
            this.mStartPairButton.setEnabled(true);
            this.mBuyDeviceTextView.setEnabled(true);
            setTitle(this.mProductId, dczVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startDialog() {
        String e = ResourceManager.e().a().e(this.mProductId);
        if (e != null) {
            this.mToDownloadList.add(e);
        }
        LogUtil.a(TAG, "startDialog fileId = ", e);
        if (!new File(dcq.b().a(this.mProductId, this.mProductInfo.e().get(0).e())).exists()) {
            if (this.mHonourDeviceConstantsApi.getUserSureDownload()) {
                ResourceManager.e().c(this.mToDownloadList);
                return;
            } else {
                ResourceManager.e().c(this.mToDownloadList, true);
                return;
            }
        }
        ResourceManager.e().c(this.mToDownloadList);
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mNeedOpenBlueTooth = true;
        ResourceManager.e().e(new ProResourceFileListener());
        this.mPermissionDialogHelper = new PermissionDialogHelper(this.mainActivity, new PermissionDialogHelper.PermissionDialogCallback() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.ProductIntroductionFragment$$ExternalSyntheticLambda3
            @Override // com.huawei.health.ecologydevice.ui.util.PermissionDialogHelper.PermissionDialogCallback
            public final void onResult(boolean z) {
                ProductIntroductionFragment.this.m314x9e334a7c(z);
            }
        });
        initServicesApi();
        initData();
        updateDeviceResource();
    }

    /* renamed from: lambda$onCreate$0$com-huawei-health-ecologydevice-ui-measure-fragment-ProductIntroductionFragment, reason: not valid java name */
    /* synthetic */ void m314x9e334a7c(boolean z) {
        if (z) {
            checkGpsLocationEnable();
        } else if (Build.VERSION.SDK_INT > 30 && PermissionUtil.e(this.mainActivity, PermissionUtil.PermissionType.SCAN) != PermissionUtil.PermissionResult.GRANTED) {
            LogUtil.h(TAG, "no scan Permission");
            nrh.b(this.mainActivity, R.string._2130846464_res_0x7f022300);
        } else {
            showBlueToothDialog();
        }
    }

    private void showBlueToothDialog() {
        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(this.mainActivity);
        builder.e(R.string.IDS_device_bluetooth_open_request);
        builder.czC_(R.string.IDS_device_ui_dialog_yes, new View.OnClickListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.ProductIntroductionFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ProductIntroductionFragment.this.m316xbb406be1(view);
            }
        });
        builder.czz_(R.string.IDS_device_ui_dialog_no, new View.OnClickListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.ProductIntroductionFragment$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        NoTitleCustomAlertDialog e = builder.e();
        e.setCancelable(false);
        e.show();
    }

    /* renamed from: lambda$showBlueToothDialog$1$com-huawei-health-ecologydevice-ui-measure-fragment-ProductIntroductionFragment, reason: not valid java name */
    /* synthetic */ void m316xbb406be1(View view) {
        iyl.d().d(this.mBtSwitchStateCallback);
        ViewClickInstrumentation.clickOnView(view);
    }

    private void initData() {
        this.mProductId = getArguments().getString("productId");
        ContentValues contentValues = (ContentValues) getArguments().getParcelable("commonDeviceInfo");
        if (contentValues != null) {
            this.mProductId = contentValues.getAsString("productId");
            this.mUniqueId = contentValues.getAsString("uniqueId");
        }
        this.mIsRebind = getArguments().getBoolean("isRebind");
        if (DNURSE_GLUCOSE_PRODUCT_ID.equals(this.mProductId)) {
            this.mIsRebind = false;
        } else if ("bc23383f-d3c8-48fe-977a-da6d200696d9".equals(this.mProductId)) {
            uploadDataToHota();
        } else {
            LogUtil.a(TAG, "is other productId");
        }
        ResourceManager.e().a().c(HealthDevice.HealthDeviceKind.HDK_HEART_RATE);
        this.mProductInfo = ResourceManager.e().d(this.mProductId);
        if (this.mProductInfo == null) {
            LogUtil.a(TAG, "init():", "mProductInfo = null");
            if (getActivity() != null) {
                LogUtil.a(TAG, "init():", "this.getActivity()");
                getActivity().finish();
                return;
            }
            return;
        }
        ((MeasureKitManagerApi) Services.c("PluginDevice", MeasureKitManagerApi.class)).registerExternalKit(this.mProductInfo.s(), ResourceManager.e().c(this.mProductId) + File.separator + this.mProductInfo.k());
        HealthDevice.HealthDeviceKind l = this.mProductInfo.l();
        this.mKind = l;
        LogUtil.a(TAG, "ProductIntroductionFragment init mKind:", l);
        if (OPERATE_DELETE.equals(getArguments().getString("operateCode"))) {
            this.mIsShowDelete = true;
        }
        BloodPressureStartFromDeviceHelper.e(this.mProductInfo);
        BloodPressureStartFromDeviceHelper.d().d(this.mProductId, this.mUniqueId, this.mStartFromDeviceListen);
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (layoutInflater == null) {
            LogUtil.a(TAG, "ProductIntroductionFragment onCreateView inflater is null");
            FragmentActivity activity = getActivity();
            if (activity != null) {
                activity.finish();
            }
            return null;
        }
        ViewGroup viewGroup2 = super.onCreateView(layoutInflater, viewGroup, bundle) instanceof ViewGroup ? (ViewGroup) super.onCreateView(layoutInflater, viewGroup, bundle) : null;
        this.child = layoutInflater.inflate(R.layout.product_introduction_layout, viewGroup, false);
        if (viewGroup2 != null) {
            viewGroup2.addView(this.child);
        }
        initChildView();
        setDeviceMeasureLayout();
        this.mProductIdList.add(this.mProductId);
        dij.UW_(this.mainActivity, this.mStartPairButton);
        init();
        if (!TextUtils.isEmpty(this.mUniqueId)) {
            this.mHonourDeviceConstantsApi.setDevicesUseTime(this.mUniqueId);
        }
        silentDataRefresh();
        updataBleStatus(BloodPressureStartFromDeviceHelper.d().a());
        if (BloodPressureStartFromDeviceHelper.a(dij.e(this.mProductId)) && BloodPressureStartFromDeviceHelper.d().b() == 0) {
            showStartMeasureAnimation();
        }
        return viewGroup2;
    }

    private void initServicesApi() {
        this.mHonourDeviceConstantsApi = (HonourDeviceConstantsApi) Services.c("PluginDevice", HonourDeviceConstantsApi.class);
        this.mHealthDeviceEntryApi = (HealthDeviceEntryApi) Services.c("PluginDevice", HealthDeviceEntryApi.class);
        this.mDeviceMainActivityApi = (DeviceMainActivityApi) Services.c("PluginDevice", DeviceMainActivityApi.class);
        this.mDeviceFragmentFactoryApi = (DeviceFragmentFactoryApi) Services.c("PluginDevice", DeviceFragmentFactoryApi.class);
    }

    private void silentDataRefresh() {
        if (this.mKind == HealthDevice.HealthDeviceKind.HDK_BLOOD_PRESSURE || this.mKind == HealthDevice.HealthDeviceKind.HDK_BLOOD_SUGAR) {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("data_put_to_engine_success");
            if (this.mLocalBroadcastReceiver == null) {
                this.mLocalBroadcastReceiver = getBroadcastReceiver();
            }
            LocalBroadcastManager.getInstance(BaseApplication.getContext()).registerReceiver(this.mLocalBroadcastReceiver, intentFilter);
        }
    }

    private void uploadDataToHota() {
        MeasurableDevice bondedDeviceByUniqueId = cei.b().getBondedDeviceByUniqueId(this.mUniqueId, false);
        if (bondedDeviceByUniqueId != null) {
            dlb.a(bondedDeviceByUniqueId.getDeviceName());
        }
    }

    private void initChildView() {
        this.mViewPager = (HealthViewPager) this.child.findViewById(R.id.vp_device_device_img);
        this.mStartPairButton = (HealthButton) this.child.findViewById(R.id.bt_device_measure_guide_next);
        this.mDeviceStartMeasureLayout = (LinearLayout) this.child.findViewById(R.id.device_start_measure_layout);
        this.mConnectStatusTextView = (HealthTextView) this.child.findViewById(R.id.connect_status_tv);
        this.mStartMeasureTextView = (HealthTextView) this.child.findViewById(R.id.device_start_measure_tv);
        this.mStartMeasureProgressBar = (HealthProgressBar) this.child.findViewById(R.id.device_reconnect_pb);
        this.mDeviceShowImage = (ImageView) this.child.findViewById(R.id.device_show_image);
        this.mFeatureDescriptionLayout = (RelativeLayout) this.child.findViewById(R.id.feature_description_layout);
        this.mBuyDeviceRemovePairLayout = (LinearLayout) this.child.findViewById(R.id.hw_show_buy_device_ll);
        this.mCarouselLayout = (LinearLayout) this.child.findViewById(R.id.carousel_layout);
        this.mStartPairLayout = (LinearLayout) this.child.findViewById(R.id.start_pair_layout);
        this.mWholeScroll = (HealthScrollView) this.child.findViewById(R.id.whole_scroll);
        this.mHavePairLayout = (LinearLayout) this.child.findViewById(R.id.have_pair_layout);
        this.mBuyDeviceTextView = (HealthTextView) this.child.findViewById(R.id.hw_show_buy_device);
        this.mLoadingProgressBar = (HealthProgressBar) this.child.findViewById(R.id.loading_pb);
        this.mDataNotificationCancel = (TextView) this.child.findViewById(R.id.data_notification_cancel);
        this.mDataNotificationGo = (TextView) this.child.findViewById(R.id.data_notification_go);
        this.mLoadingProgressBar.setLayerType(1, null);
        if (this.mHonourDeviceConstantsApi.isHonourDevice(this.mProductId)) {
            showLoadingProgressBar();
            this.mViewPager.setVisibility(8);
            this.mBuyDeviceRemovePairLayout.setVisibility(8);
        }
        this.mBloodPressureFaqsLayout = (LinearLayout) this.child.findViewById(R.id.blood_pressure_faqs);
        this.mBloodPressureFaqsCloseVoice = (RelativeLayout) this.child.findViewById(R.id.faqs_close_voice);
        this.mBloodPressureFaqsSyncData = (RelativeLayout) this.child.findViewById(R.id.faqs_sync_data);
        this.mBloodPressureFaqsCloseVoice.setOnClickListener(this);
        this.mBloodPressureFaqsSyncData.setOnClickListener(this);
        if ("O006".equals(dij.e(this.mProductId)) && !TextUtils.isEmpty(dcq.b().c(this.mProductId))) {
            this.mBloodPressureFaqsLayout.setVisibility(0);
        } else {
            this.mBloodPressureFaqsLayout.setVisibility(8);
        }
    }

    private BroadcastReceiver getBroadcastReceiver() {
        return new BroadcastReceiver() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.ProductIntroductionFragment.4
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                String stringExtra = intent.getStringExtra("data_kind");
                if (ProductIntroductionFragment.this.mKind != HealthDevice.HealthDeviceKind.HDK_BLOOD_PRESSURE || !"com.huawei.ui.main.stories.health.activity.healthdata.KnitBloodPressureActivity".equals(stringExtra)) {
                    if (ProductIntroductionFragment.this.mKind == HealthDevice.HealthDeviceKind.HDK_BLOOD_SUGAR && "com.huawei.ui.main.stories.health.activity.healthdata.BloodSugarDeviceMeasureActivity".equals(stringExtra)) {
                        dei.c().c(ProductIntroductionFragment.this.mUniqueId, new BloodSugarDataUiResponse(ProductIntroductionFragment.this.mHandler, 102));
                        return;
                    } else {
                        LogUtil.a(ProductIntroductionFragment.TAG, "ProductIntroductionFragment don't need to queryData");
                        return;
                    }
                }
                dkw.Wp_(ProductIntroductionFragment.this.mHandler, 101);
            }
        };
    }

    @Override // com.huawei.health.ecologydevice.ui.measure.fragment.ProductFragment, com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        LogUtil.a(TAG, "ProductIntroductionFragment onResume");
        queryMeasureData();
        initNetWorkTipView(isNetWorkActive());
        registerNetWorkReceiver();
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        LogUtil.a(TAG, "ProductIntroductionFragment onDestroy");
        unRegisterNetWorkReceiver();
        HealthEngineRequestManager.getInstance().cancelRequest(TAG);
        BloodPressureStartFromDeviceHelper.d().a(this.mProductInfo);
        iyl.d().e(this.mBtSwitchStateCallback);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        if (this.mLocalBroadcastReceiver != null) {
            LocalBroadcastManager.getInstance(BaseApplication.getContext()).unregisterReceiver(this.mLocalBroadcastReceiver);
        }
        PopViewList popViewList = this.mPopView;
        if (popViewList != null && popViewList.a()) {
            LogUtil.h(TAG, "onDestroyView mPopView dismiss");
            this.mPopView.b();
        }
        ResourceManager.e().f();
        LogUtil.a(TAG, "ProductIntroductionFragment onDestroyView");
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment
    public boolean onBackPressed() {
        ResourceManager.e().f();
        if (dks.b(getContext())) {
            popupFragment(null);
            return false;
        }
        if (this.mIsFinishDeviceMainActivity) {
            LogUtil.a(TAG, "onBackPressed():", "mIsFinishDeviceMainActivity");
            popupFragment(null);
            return false;
        }
        LogUtil.a(TAG, "onBackPressed():", FitRunPlayAudio.OPPORTUNITY_M);
        return super.onBackPressed();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void init() {
        getFirstImage();
        queryMeasureData();
        initView();
        checkNewDataIsDelivered();
        if (this.mIsShowDelete) {
            unbindHaveBindingDevice();
        }
    }

    private void checkNewDataIsDelivered() {
        if (GprsDeviceHelper.c(this.mProductId)) {
            if (SharedPreferenceManager.a("thirdDeviceToApp", HAVE_DATA_KEY, false)) {
                this.mDataNotificationLayout.setVisibility(0);
            } else {
                ddx.a().a(this.mProductInfo.l(), new IBaseResponseCallback() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.ProductIntroductionFragment.5
                    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                    /* renamed from: onResponse */
                    public void d(int i, Object obj) {
                        if (i == 0) {
                            SharedPreferenceManager.e("thirdDeviceToApp", ProductIntroductionFragment.HAVE_DATA_KEY, true);
                            ProductIntroductionFragment.this.mDataNotificationLayout.setVisibility(0);
                        }
                    }
                });
            }
        }
    }

    private void getFirstImage() {
        if (koq.b(this.mProductInfo.e(), 0)) {
            LogUtil.h(TAG, "ProductIntroductionFragment descriptions is out of bounds");
            return;
        }
        File file = new File(dcq.b().a(this.mProductId, this.mProductInfo.e().get(0).e()));
        LogUtil.a(TAG, "ProductIntroductionFragment init productId is ", this.mProductId);
        dcu b = ResourceManager.e().a().b(this.mProductId);
        boolean z = b == null || !b.c().trim().equals(this.mProductInfo.ac().trim());
        if (!file.exists() && isNetWorkActive()) {
            showLoadingProgressBar();
            this.mViewPager.setVisibility(8);
        } else {
            hideLoadingProgressBar();
            this.mViewPager.setVisibility(0);
        }
        if (!file.exists() || z) {
            this.mIsFirstDownload = true;
            String e = ResourceManager.e().a().e(this.mProductId);
            if (e != null) {
                this.mToDownloadList.add(e);
            }
            if (this.mHonourDeviceConstantsApi.isHonourDevice(this.mProductId)) {
                initHonourDevice(file, e);
                return;
            } else {
                ResourceManager.e().c(this.mToDownloadList);
                return;
            }
        }
        this.mStartPairButton.setEnabled(true);
        this.mBuyDeviceTextView.setEnabled(true);
    }

    private void queryMeasureData() {
        if (this.mKind == HealthDevice.HealthDeviceKind.HDK_WEIGHT) {
            setHavePairTextView(R.string._2130841560_res_0x7f020fd8, 0);
            dkw.Ws_(this.mHandler, 100);
            return;
        }
        if (this.mKind == HealthDevice.HealthDeviceKind.HDK_BLOOD_PRESSURE) {
            setHavePairTextView(R.string._2130843507_res_0x7f021773, 0);
            dkw.Wp_(this.mHandler, 101);
            return;
        }
        if (this.mKind == HealthDevice.HealthDeviceKind.HDK_BLOOD_SUGAR) {
            setHavePairBloodSugarTextView();
            dei.c().c(this.mUniqueId, new BloodSugarDataUiResponse(this.mHandler, 102));
            dkw.Wr_(this.mHandler, this.mUniqueId);
        } else {
            if (this.mKind == HealthDevice.HealthDeviceKind.HDK_HEART_RATE) {
                setHavePairTextView(R.string.IDS_main_watch_heart_rate_unit_string, 8);
                return;
            }
            if (this.mKind == HealthDevice.HealthDeviceKind.HDK_BODY_TEMPERATURE) {
                setHavePairBodyTempTextView();
                dkw.Wq_(this.mHandler, 104);
            } else if (this.mKind == HealthDevice.HealthDeviceKind.HDK_BLOOD_OXYGEN) {
                setHavePairSpO2TextView();
                dkw.Wo_(this.mHandler, 105);
            } else {
                LogUtil.a(TAG, "queryMeasureData mKind is not exist");
            }
        }
    }

    private void setHavePairTextView(int i, int i2) {
        this.mDeviceMeasureValueUnitView.setText(BaseApplication.getContext().getResources().getString(i));
        this.mDeviceMeasureValueLayout.setVisibility(i2);
    }

    private void setHavePairBloodSugarTextView() {
        this.mDeviceLatestValueLayout.setMinimumHeight(BaseApplication.getContext().getResources().getDimensionPixelSize(R.dimen._2131363067_res_0x7f0a04fb));
        ViewGroup.LayoutParams layoutParams = this.mDeviceLatestValueLayout.getLayoutParams();
        if (layoutParams instanceof LinearLayout.LayoutParams) {
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) layoutParams;
            layoutParams2.bottomMargin = (int) getResources().getDimension(R.dimen._2131362565_res_0x7f0a0305);
            this.mDeviceLatestValueLayout.setLayoutParams(layoutParams2);
        }
        this.mDeviceStartMeasureLayout.setMinimumHeight(BaseApplication.getContext().getResources().getDimensionPixelSize(R.dimen._2131363112_res_0x7f0a0528));
        this.mConnectStatusTextView.setText(R.string.IDS_device_rope_device_connected);
        this.mStartMeasureTextView.setTextSize(1, TEXT_SIZE);
        this.mDeviceMeasureValueUnitView.setText(BLOOD_SUGAR_UNIT_MMOL_L);
    }

    private void setHavePairBodyTempTextView() {
        this.mDeviceMeasureValueUnitView.setText(BaseApplication.getContext().getResources().getString(R.string._2130843630_res_0x7f0217ee, ""));
        this.mDeviceMeasureValueLayout.setVisibility(0);
    }

    private void setHavePairSpO2TextView() {
        this.mDeviceMeasureValueUnitView.setText("%");
        this.mDeviceMeasureValueLayout.setVisibility(0);
    }

    private void initHonourDevice(File file, String str) {
        if (file.exists()) {
            this.mStartPairButton.setEnabled(true);
            this.mBuyDeviceTextView.setEnabled(true);
            ResourceManager.e().c(this.mToDownloadList);
        } else {
            this.mStartPairButton.setEnabled(false);
            this.mBuyDeviceTextView.setEnabled(false);
            downloadSource(str);
        }
    }

    private void downloadSource(String str) {
        if (isNetWorkActive() && str != null) {
            LogUtil.a(TAG, "fileId = " + str);
            if (!this.mHonourDeviceConstantsApi.getUserSureDownload()) {
                ResourceManager.e().c(this.mToDownloadList, true);
                return;
            } else {
                ResourceManager.e().c(this.mToDownloadList);
                return;
            }
        }
        LogUtil.a(TAG, "fileId = " + str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showDialog(String str) {
        String string;
        try {
            double doubleValue = new BigDecimal((Double.parseDouble(str) / 1024.0d) / 1024.0d).setScale(2, 4).doubleValue();
            hideLoadingProgressBar();
            if (!Utils.o()) {
                if (this.mainActivity != null) {
                    string = this.mainActivity.getResources().getString(R.string.IDS_device_am16_download_tip);
                }
                string = "";
            } else {
                if (this.mainActivity != null) {
                    string = this.mainActivity.getResources().getString(R.string.IDS_device_am16_download_overseas_tip);
                }
                string = "";
            }
            if (this.mainActivity != null) {
                showCustomTextAlertDialog(String.format(Locale.ENGLISH, string, this.mainActivity.getResources().getString(R.string._2130848991_res_0x7f022cdf), UnitUtil.e(doubleValue, 1, 2)));
            }
        } catch (NumberFormatException unused) {
            LogUtil.h(TAG, "the size format is wrong");
        }
    }

    private void showCustomTextAlertDialog(String str) {
        CustomTextAlertDialog a2 = new CustomTextAlertDialog.Builder(this.mainActivity).b(R.string.IDS_service_area_notice_title).e(str).cyU_(R.string._2130841733_res_0x7f021085, new View.OnClickListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.ProductIntroductionFragment$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ProductIntroductionFragment.this.m317x69057c93(view);
            }
        }).cyR_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.ProductIntroductionFragment$$ExternalSyntheticLambda5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ProductIntroductionFragment.this.m318x497ed294(view);
            }
        }).a();
        a2.setCancelable(false);
        if (a2.isShowing()) {
            return;
        }
        a2.show();
    }

    /* renamed from: lambda$showCustomTextAlertDialog$3$com-huawei-health-ecologydevice-ui-measure-fragment-ProductIntroductionFragment, reason: not valid java name */
    /* synthetic */ void m317x69057c93(View view) {
        String e;
        showLoadingProgressBar();
        if (this.mToDownloadList.size() == 0 && (e = ResourceManager.e().a().e(this.mProductId)) != null) {
            this.mToDownloadList.add(e);
        }
        this.mHonourDeviceConstantsApi.setUserSureDownload();
        ResourceManager.e().c(this.mToDownloadList);
        ViewClickInstrumentation.clickOnView(view);
    }

    /* renamed from: lambda$showCustomTextAlertDialog$4$com-huawei-health-ecologydevice-ui-measure-fragment-ProductIntroductionFragment, reason: not valid java name */
    /* synthetic */ void m318x497ed294(View view) {
        this.mainActivity.finish();
        ViewClickInstrumentation.clickOnView(view);
    }

    private String getTitle(String str, dcz dczVar) {
        String d = dcx.d(str, dczVar.n().b());
        if (METIS_PRODUCTID.equals(str)) {
            return (LanguageUtil.ba(cpp.a()) || LanguageUtil.ab(cpp.a()) || LanguageUtil.m(cpp.a())) ? d : HUAWEI_FIT;
        }
        String deviceIdentify = this.mHonourDeviceConstantsApi.getDeviceIdentify(this.mUniqueId);
        if (TextUtils.isEmpty(deviceIdentify)) {
            return d;
        }
        return d + Constants.LINK + deviceIdentify;
    }

    private void setTitle(String str, dcz dczVar) {
        setTitle(getTitle(str, dczVar));
    }

    private void initView() {
        if (this.mProductInfo.e().size() == 0) {
            LogUtil.a(TAG, "ProductIntroductionFragment initView() deviceInfo size is zero");
            return;
        }
        HealthTextView healthTextView = (HealthTextView) this.child.findViewById(R.id.tv_device_device_introduction_prompt);
        this.mPrompt = healthTextView;
        healthTextView.setMovementMethod(ScrollingMovementMethod.getInstance());
        this.mDotPageIndicator = (HealthDotsPageIndicator) this.child.findViewById(R.id.device_navigation_spot);
        setTitle(this.mProductId, this.mProductInfo);
        this.mTextList = new ArrayList<>(10);
        ArrayList<String> arrayList = new ArrayList<>(10);
        addImageAndText(this.mProductInfo.e().size(), arrayList, this.mProductId, this.mProductInfo, true);
        this.mImgPathList = arrayList;
        setmPromptText(0, this.mPrompt);
        LogUtil.a(TAG, "ProductIntroductionFragment ----2----");
        dgo dgoVar = new dgo(arrayList, this.mainActivity);
        this.mImgPagerAdapter = dgoVar;
        this.mViewPager.setAdapter(dgoVar);
        this.mViewPager.addOnPageChangeListener(this.mOnPageChangeListener);
        this.mDotPageIndicator.setRtlEnable(true);
        this.mDotPageIndicator.setViewPager(this.mViewPager);
        this.mBuyDeviceTextView.setText(R.string.IDS_device_buy_bind_device_string);
        setBuyBtnVisible();
        if (isBondedDevice() && !this.mIsRebind) {
            setBindButtonText(true);
            if (this.mKind != HealthDevice.HealthDeviceKind.HDK_HEART_RATE) {
                if (this.mHonourDeviceConstantsApi.isHonourDevice(this.mProductId)) {
                    this.mBuyDeviceRemovePairLayout.setVisibility(8);
                } else {
                    this.mBuyDeviceRemovePairLayout.setVisibility(0);
                }
                this.mStartPairButton.setVisibility(0);
                this.mStartPairButton.setText(R.string.IDS_device_measureactivity_guide_start_measure);
            }
        } else {
            setBindButtonText(false);
        }
        PopViewList popViewList = this.mPopView;
        if (popViewList != null && popViewList.a()) {
            LogUtil.h(TAG, "initView mPopView dismiss");
            this.mPopView.b();
        }
        dij.UW_(this.mainActivity, this.mBuyDeviceTextView);
        setBrowsingClickListener();
        this.mStartMeasureTextView.setOnClickListener(this);
        this.mFeatureDescriptionLayout.setOnClickListener(this);
        this.mDataNotificationGo.setOnClickListener(this);
        this.mDataNotificationCancel.setOnClickListener(this);
    }

    private void setBrowsingClickListener() {
        FragmentActivity activity = getActivity();
        if (activity instanceof BaseActivity) {
            BaseActivity baseActivity = (BaseActivity) activity;
            this.mBuyDeviceRemovePairLayout.setOnClickListener(nkx.cwZ_(this, baseActivity, true, ""));
            this.mStartPairButton.setOnClickListener(nkx.cwZ_(this, baseActivity, true, ""));
        } else {
            LogUtil.h(TAG, "is null or uninstanceof BaseActivity");
            this.mBuyDeviceRemovePairLayout.setOnClickListener(this);
            this.mStartPairButton.setOnClickListener(this);
        }
    }

    private void addImageAndText(int i, ArrayList<String> arrayList, String str, dcz dczVar, boolean z) {
        for (int i2 = 0; i2 < i; i2++) {
            arrayList.add(dcq.b().a(str, dczVar.e().get(i2).e()));
            if (z) {
                this.mTextList.add(dcx.d(str, dczVar.e().get(i2).c()));
            }
        }
        if (arrayList.isEmpty()) {
            return;
        }
        String str2 = arrayList.get(0);
        this.mPrivacyImagePath = str2;
        LogUtil.a(TAG, "privacy image path = ", str2);
    }

    private void hideLoadingProgressBar() {
        this.mLoadingProgressBar.setVisibility(8);
    }

    private void showLoadingProgressBar() {
        this.mLoadingProgressBar.setVisibility(0);
    }

    private boolean isBluetooth() {
        int c = this.mProductInfo.x().c();
        return c == 2 || c == 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setBindButtonText(boolean z) {
        if (isBluetooth()) {
            if (z) {
                unbindPair();
            } else {
                setBuyBtnVisible();
                this.mCarouselLayout.setVisibility(0);
                this.mStartPairLayout.setVisibility(0);
                this.mHavePairLayout.setVisibility(8);
                this.mCustomTitleBar.setRightButtonVisibility(8);
                setTitleBarBackgroundColor(R.color._2131296657_res_0x7f090191);
                setScrollBackgroundColor(R.color._2131296657_res_0x7f090191);
                this.mStartPairButton.setText(R.string.IDS_device_start_paring_title);
            }
        } else if (z) {
            if (this.mKind != HealthDevice.HealthDeviceKind.HDK_HEART_RATE) {
                showUnbindPairAndCancelBindView(0);
                this.mConnectStatusTextView.setText(R.string._2130841450_res_0x7f020f6a);
            } else {
                showUnbindPairAndCancelBindView(0);
                this.mConnectStatusTextView.setText(R.string._2130841578_res_0x7f020fea);
                setBuyBtnVisible();
            }
        } else {
            setBuyBtnVisible();
            this.mCarouselLayout.setVisibility(0);
            this.mStartPairLayout.setVisibility(0);
            this.mCustomTitleBar.setRightButtonVisibility(8);
            setTitleBarBackgroundColor(R.color._2131296657_res_0x7f090191);
            setScrollBackgroundColor(R.color._2131296657_res_0x7f090191);
            this.mHavePairLayout.setVisibility(8);
            this.mStartPairButton.setText(R.string.IDS_device_selection_bind_device);
        }
        Bundle arguments = getArguments();
        if (arguments != null && z && arguments.getBoolean(IS_BIND_SUCCESS)) {
            LogUtil.a(TAG, "set mIsFinishDeviceMainActivity is true");
            this.mIsFinishDeviceMainActivity = true;
        }
        this.mCustomTitleBar.setRightButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.ProductIntroductionFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ProductIntroductionFragment.this.m315x6ba4714a(view);
            }
        });
    }

    /* renamed from: lambda$setBindButtonText$5$com-huawei-health-ecologydevice-ui-measure-fragment-ProductIntroductionFragment, reason: not valid java name */
    /* synthetic */ void m315x6ba4714a(View view) {
        showUnbindDeviceMenu();
        ViewClickInstrumentation.clickOnView(view);
    }

    private void setTitleBarBackgroundColor(int i) {
        Resources resources = BaseApplication.getContext().getResources();
        if (resources != null) {
            this.mCustomTitleBar.setTitleBarBackgroundColor(resources.getColor(i));
        }
    }

    private void setScrollBackgroundColor(int i) {
        Resources resources = BaseApplication.getContext().getResources();
        if (resources != null) {
            this.mWholeScroll.setBackgroundColor(resources.getColor(i));
        }
    }

    private void unbindPair() {
        if (this.mKind != HealthDevice.HealthDeviceKind.HDK_HEART_RATE) {
            if (this.mHonourDeviceConstantsApi.isHuaweiWspScaleProduct(this.mProductId)) {
                showHagridView();
                return;
            } else {
                showUnbindPairAndCancelBindView(0);
                return;
            }
        }
        showUnbindPairAndCancelBindView(8);
        setBuyBtnVisible();
    }

    private void showHagridView() {
        this.mCustomTitleBar.setRightButtonVisibility(8);
        this.mCarouselLayout.setVisibility(0);
        this.mStartPairLayout.setVisibility(0);
        this.mHavePairLayout.setVisibility(8);
        this.mStartPairButton.setText(R.string.IDS_device_measureactivity_guide_start_measure);
        this.mBuyDeviceTextView.setText(R.string.IDS_hw_health_wear_connect_device_unpair_button);
    }

    private void showUnbindPairAndCancelBindView(int i) {
        Bitmap bitmap;
        this.mCarouselLayout.setVisibility(8);
        this.mStartPairLayout.setVisibility(8);
        this.mHavePairLayout.setVisibility(0);
        this.mCustomTitleBar.setRightButtonVisibility(0);
        setScrollBackgroundColor(R.color._2131299340_res_0x7f090c0c);
        setTitleBarBackgroundColor(R.color._2131299340_res_0x7f090c0c);
        this.mCustomTitleBar.setRightButtonDrawable(this.mainActivity.getResources().getDrawable(R.drawable._2131430194_res_0x7f0b0b32), nsf.h(R.string._2130850635_res_0x7f02334b));
        this.mStartMeasureTextView.setText(R.string.IDS_device_measureactivity_guide_start_measure);
        this.mStartMeasureTextView.setVisibility(i);
        this.mDeviceShowImage.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        if (koq.b(this.mImgPathList)) {
            LogUtil.h(TAG, "showUnbindPairAndCancelBindView mImgPathList is null");
            this.mDeviceShowImage.setImageDrawable(this.mainActivity.getResources().getDrawable(R.drawable._2131430194_res_0x7f0b0b32));
            return;
        }
        if (this.mBitmapLruCache.get(this.mImgPathList.get(0)) == null) {
            String str = this.mImgPathList.get(0);
            bitmap = dcx.TK_(str);
            if (new File(str).exists() && bitmap != null) {
                LogUtil.a(TAG, "showUnbindPairAndCancelBindView cache Image");
                this.mBitmapLruCache.put(str, bitmap);
            }
        } else {
            String str2 = this.mImgPathList.get(0);
            LogUtil.h(TAG, "showUnbindPairAndCancelBindView load exists Image");
            bitmap = this.mBitmapLruCache.get(str2);
        }
        if (bitmap == null) {
            LogUtil.h(TAG, "showUnbindPairAndCancelBindView bitmap is null");
        } else {
            this.mDeviceShowImage.setImageBitmap(bitmap);
        }
    }

    private void showUnbindDeviceMenu() {
        if (this.mainActivity == null || this.mCustomTitleBar == null) {
            LogUtil.h(TAG, "mainActivity or mCustomTitleBar is null");
            return;
        }
        PopViewList popViewList = new PopViewList(this.mainActivity, this.mCustomTitleBar, new ArrayList(Arrays.asList(getResources().getString(R.string.IDS_device_wear_home_delete_device))));
        this.mPopView = popViewList;
        popViewList.e(new PopViewList.PopViewClickListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.ProductIntroductionFragment$$ExternalSyntheticLambda6
            @Override // com.huawei.ui.commonui.popupview.PopViewList.PopViewClickListener
            public final void setOnClick(int i) {
                ProductIntroductionFragment.this.m320x795d02ef(i);
            }
        });
        if (dks.i(this.mProductId)) {
            jdx.b(new Runnable() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.ProductIntroductionFragment$$ExternalSyntheticLambda7
                @Override // java.lang.Runnable
                public final void run() {
                    HealthAccessTokenUtil.getAtInstance().refreshAccessToken();
                }
            });
        }
    }

    /* renamed from: lambda$showUnbindDeviceMenu$6$com-huawei-health-ecologydevice-ui-measure-fragment-ProductIntroductionFragment, reason: not valid java name */
    /* synthetic */ void m320x795d02ef(int i) {
        if (i == 0) {
            unbindHaveBindingDevice();
        } else {
            LogUtil.a(TAG, "ProductIntroductionFragment showUnbindDeviceMenu is default");
        }
    }

    private void unbindHaveBindingDevice() {
        LogUtil.a(TAG, "unBindHaveBindDevice to enter");
        new DeviceUnbindHelper(this, this.mProductId, this.mUniqueId, new DeviceUnbindHelper.DeviceUnBingListen() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.ProductIntroductionFragment.6
            @Override // com.huawei.health.ecologydevice.manager.DeviceUnbindHelper.DeviceUnBingListen
            public void onFail() {
            }

            @Override // com.huawei.health.ecologydevice.manager.DeviceUnbindHelper.DeviceUnBingListen
            public void onSuccess(int i) {
                LogUtil.a(ProductIntroductionFragment.TAG, "UnbindSinoDeviceRequest onSuccess");
                ProductIntroductionFragment.this.mHealthDeviceEntryApi.stopMeasure(ProductIntroductionFragment.this.mProductId, ProductIntroductionFragment.this.mUniqueId);
                if (dks.b(ProductIntroductionFragment.this.mainActivity)) {
                    return;
                }
                ProductIntroductionFragment.this.setBindButtonText(false);
                ProductIntroductionFragment.this.mBuyDeviceTextView.setText(R.string.IDS_device_buy_bind_device_string);
                ProductIntroductionFragment.this.setBuyBtnVisible();
            }
        }).d();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setBuyBtnVisible() {
        String c = this.mProductInfo.c(BUY_URL);
        if (c == null || c.trim().isEmpty() || Utils.o() || this.mHonourDeviceConstantsApi.isHonourDevice(this.mProductId) || CommonUtil.bf()) {
            this.mBuyDeviceRemovePairLayout.setVisibility(8);
            this.mBuyDeviceTextView.setVisibility(8);
        } else {
            this.mBuyDeviceRemovePairLayout.setVisibility(0);
            this.mBuyDeviceTextView.setVisibility(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setmPromptText(int i, HealthTextView healthTextView) {
        char c;
        if (this.mProductInfo == null || this.mProductInfo.e() == null) {
            return;
        }
        int size = this.mProductInfo.e().size();
        LogUtil.a(TAG, "ProductIntroductionFragment setmPromptText() length = ", Integer.valueOf(size));
        healthTextView.setScrollY(0);
        if (this.mHonourDeviceConstantsApi.isHonourDevice(this.mProductId)) {
            healthTextView.setGravity(GravityCompat.START);
        } else {
            healthTextView.setGravity(17);
        }
        int i2 = dij.c(this.mainActivity) ? (size - 1) - i : i;
        if (i2 >= this.mProductInfo.e().size() || i2 < 0) {
            return;
        }
        String trim = this.mProductInfo.e().get(i2).c().trim();
        trim.hashCode();
        int hashCode = trim.hashCode();
        if (hashCode == -1267154919) {
            if (trim.equals(METIS_INFO)) {
                c = 0;
            }
            c = 65535;
        } else if (hashCode != -1183654103) {
            if (hashCode == 559526192 && trim.equals(AM16_INFO_OTHER)) {
                c = 2;
            }
            c = 65535;
        } else {
            if (trim.equals(AM16_INFO)) {
                c = 1;
            }
            c = 65535;
        }
        if (c == 0) {
            healthTextView.setText(String.format(Locale.ENGLISH, getResources().getString(R.string.IDS_device_introductionactivity_metis_introduction_1_1), UnitUtil.e(METIS_INFO_24, 1, 0)));
            return;
        }
        if (c == 1) {
            healthTextView.setText(String.format(Locale.ENGLISH, getResources().getString(R.string._2130848992_res_0x7f022ce0), UnitUtil.e(1.0d, 1, 0), UnitUtil.e(2.0d, 1, 0), UnitUtil.e(5.0d, 1, 0)));
            return;
        }
        if (c == 2) {
            healthTextView.setText(getResources().getString(R.string._2130842495_res_0x7f02137f));
        } else {
            if (i >= this.mTextList.size() || i < 0) {
                return;
            }
            healthTextView.setText(this.mTextList.get(i));
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (nsn.o()) {
            LogUtil.a(TAG, "click too fast");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        if (view == null) {
            LogUtil.h(TAG, "ProductIntroductionFragment onClick view is null");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", "1");
        hashMap.put(PluginPayAdapter.KEY_DEVICE_INFO_NAME, dks.e(this.mProductId, this.mProductInfo.n().b()));
        if (view == this.mSwitchSettingLayout) {
            if (!isNetWorkActive()) {
                diy.a(this.mainActivity);
            }
        } else if (view == this.mBuyDeviceRemovePairLayout) {
            clickBuyDeviceRemovePairLayout(hashMap);
        } else if (view == this.mStartPairButton) {
            clickStartPairButton(hashMap);
        } else if (view == this.mStartMeasureTextView) {
            if (this.mKind != HealthDevice.HealthDeviceKind.HDK_HEART_RATE) {
                onClickMeasureButton();
            } else {
                LogUtil.a(TAG, "ProductIntroductionFragment heart rate device no need measure button");
            }
        } else if (view == this.mFeatureDescriptionLayout) {
            openDeviceHelpActivity();
        } else if (view == this.mDeviceLatestDataAndCountLayout) {
            diy.a(this, this.mKind);
        } else if (view == this.mDeviceLatestValueLayout) {
            diy.b(getContext(), this.mKind);
        } else if (view == this.mUricAcidValueLayout) {
            diy.c(this, this.mProductId, this.mUniqueId);
        } else if (view == this.mDataNotificationGo) {
            if (this.mProductInfo.l() == HealthDevice.HealthDeviceKind.HDK_BLOOD_SUGAR) {
                diy.b(getContext(), this.mProductId, this.mUniqueId);
            } else if (this.mProductInfo.l() == HealthDevice.HealthDeviceKind.HDK_BLOOD_PRESSURE) {
                diy.d(getContext());
            } else {
                LogUtil.a(TAG, "Unsupported data type.");
            }
            closeNewDataNotification();
        } else if (view == this.mDataNotificationCancel) {
            closeNewDataNotification();
        } else if (view == this.mBloodPressureFaqsCloseVoice) {
            diy.e(getContext(), this.mProductId, "feature=voice");
        } else if (view == this.mBloodPressureFaqsSyncData) {
            diy.e(getContext(), this.mProductId, "feature=connect");
        } else {
            LogUtil.h(TAG, "ProductIntroductionFragment onClick else");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void closeNewDataNotification() {
        SharedPreferenceManager.e("thirdDeviceToApp", HAVE_DATA_KEY, false);
        this.mDataNotificationLayout.setVisibility(8);
    }

    private void clickBuyDeviceRemovePairLayout(Map<String, Object> map) {
        if (this.mHonourDeviceConstantsApi.isHuaweiWspScaleProduct(this.mProductId) && this.mBuyDeviceTextView.getText().toString().equals(getResources().getString(R.string.IDS_hw_health_wear_connect_device_unpair_button))) {
            unbindHaveBindingDevice();
        } else {
            if (isBondedDevice()) {
                return;
            }
            onClickBuyButton(map);
        }
    }

    private void clickStartPairButton(Map<String, Object> map) {
        if (this.mHonourDeviceConstantsApi.isHuaweiWspScaleProduct(this.mProductId) && this.mStartPairButton.getText().toString().equals(getResources().getString(R.string.IDS_device_measureactivity_guide_start_measure))) {
            if (this.mKind != HealthDevice.HealthDeviceKind.HDK_HEART_RATE) {
                onClickMeasureButton();
                return;
            } else {
                LogUtil.a(TAG, "ProductIntroductionFragment heart rate device no need measure button");
                return;
            }
        }
        ixx.d().d(cpp.a(), AnalyticsValue.HEALTH_PLUGIN_DEVICE_BIND_PRODUCT_2060004.value(), map, 0);
        this.mPermissionDialogHelper.d(this.mProductInfo, this.mProductId);
    }

    class ProResourceFileListener implements ResourceFileListener {
        private ProResourceFileListener() {
        }

        @Override // com.huawei.health.device.manager.ResourceFileListener
        public void onResult(int i, String str) {
            LogUtil.a(ProductIntroductionFragment.TAG, "ProductIntroductionFragment resultCode = ", Integer.valueOf(i), " resultValue = ", str);
            Message obtain = Message.obtain();
            obtain.obj = str;
            if (TextUtils.isEmpty(str) || i == -1) {
                obtain.what = 2;
            } else if (i == 200) {
                if (ProductIntroductionFragment.this.mProductIdList.contains(str)) {
                    obtain.what = 1;
                    ProductIntroductionFragment.this.mIsFirstDownload = false;
                } else if (MessageConstant.GROUP_MEDAL_TYPE.equals(str)) {
                    obtain.what = 4;
                } else {
                    LogUtil.a(ProductIntroductionFragment.TAG, "ProductIntroductionFragment unhandled resultValue");
                }
            } else if (i == 300) {
                obtain.what = 5;
            } else {
                LogUtil.a(ProductIntroductionFragment.TAG, "ProductIntroductionFragment unhandled resultCode = ", Integer.valueOf(i));
                return;
            }
            ProductIntroductionFragment.this.mProHandler.sendMessage(obtain);
        }
    }

    private void bindDevice(int i) {
        if (this.mProductInfo.d().size() > 0) {
            Bundle bundle = new Bundle();
            bundle.putString("productId", this.mProductId);
            bundle.putString("title", getTitle(this.mProductId, this.mProductInfo));
            if (this.mHonourDeviceConstantsApi.isHuaweiWspScaleProduct(this.mProductId)) {
                LogUtil.a(TAG, "isHagridScaleDevice true.");
                boolean e = SharedPreferenceManager.e(BaseApplication.getContext(), LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011));
                boolean d = dks.d(BaseApplication.getContext());
                if (CompileParameterUtil.a("IS_LUPIN_SUPPORT_PRIVACY", false) && d && "25c6df38-ca23-11e9-a32f-2a2ae2dbcce4".equals(this.mProductId) && !e) {
                    diy.Vt_(getActivity(), this.mPrivacyImagePath, 1001);
                    return;
                } else {
                    switchHagridDeviceBindGuidFragment(bundle);
                    return;
                }
            }
            LogUtil.a(TAG, "isHagridScaleDevice.");
            DeviceBindGuidFragment deviceBindGuidFragment = new DeviceBindGuidFragment();
            putContentValuesData(bundle);
            deviceBindGuidFragment.setArguments(bundle);
            switchFragment(deviceBindGuidFragment);
            return;
        }
        if (this.mHealthDeviceEntryApi.isDeviceKitUniversal(this.mProductId)) {
            LogUtil.a(TAG, "only am16 use universal interface");
            switchDeviceBindWaitingUniversalFragment();
        } else {
            switchDeviceScanningFragment(i);
        }
    }

    private void checkGpsLocationEnable() {
        int c = this.mProductInfo.x().c();
        if (c == 1 && !dja.VG_(this.mainActivity)) {
            dif.Vp_(this.mainActivity, isAdded(), new IdialogButtonClickCallback() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.ProductIntroductionFragment.7
                @Override // com.huawei.health.ecologydevice.callback.IdialogButtonClickCallback
                public void onClick(View view) {
                    dja.VY_(ProductIntroductionFragment.this.mainActivity, ProductIntroductionFragment.this.isAdded(), 1);
                    ViewClickInstrumentation.clickOnView(view);
                }

                @Override // com.huawei.health.ecologydevice.callback.IdialogButtonClickCallback
                public void onCancelClick(View view) {
                    LogUtil.a(ProductIntroductionFragment.TAG, "bindDevice onCancelClick");
                }
            });
        } else {
            bindDevice(c);
        }
    }

    private void switchHagridDeviceBindGuidFragment(Bundle bundle) {
        Fragment pluginDeviceFragment = this.mDeviceMainActivityApi.getPluginDeviceFragment(WeightConstants.FragmentType.HAGRID_DEVICE_BIND_GUID);
        if (pluginDeviceFragment == null) {
            LogUtil.h(TAG, "switchHagridDeviceBindGuidFragment bindGuidFragment is null");
            return;
        }
        putContentValuesData(bundle);
        bundle.putBoolean("isRebind", this.mIsRebind);
        pluginDeviceFragment.setArguments(bundle);
        switchFragment(pluginDeviceFragment);
    }

    private void switchDeviceBindWaitingUniversalFragment() {
        Bundle bundle = new Bundle();
        if (this.mHonourDeviceConstantsApi.isHonourDevice(this.mProductId)) {
            if (!hasAudioRecordPermission()) {
                return;
            }
            if (!isRingerModeNormal()) {
                showRingerModeDialog();
                return;
            }
            bundle.putInt("position", 0);
        }
        bundle.putString("productId", this.mProductId);
        bundle.putString("title", getTitle(this.mProductId, this.mProductInfo));
        putContentValuesData(bundle);
        Fragment pluginDeviceFragment = this.mDeviceMainActivityApi.getPluginDeviceFragment(WeightConstants.FragmentType.DEVICE_BIND_WAITING_UNIVERSAL);
        if (pluginDeviceFragment == null) {
            LogUtil.h(TAG, "switchDeviceBindWaitingUniversalFragment fragment is null");
        } else {
            pluginDeviceFragment.setArguments(bundle);
            switchFragment(pluginDeviceFragment);
        }
    }

    private void switchDeviceScanningFragment(int i) {
        Bundle bundle = new Bundle();
        bundle.putString("productId", this.mProductId);
        bundle.putString("title", getTitle(this.mProductId, this.mProductInfo));
        bundle.putInt("scanMode", i);
        DeviceScanningFragment deviceScanningFragment = new DeviceScanningFragment();
        putContentValuesData(bundle);
        bundle.putBoolean("isRebind", this.mIsRebind);
        deviceScanningFragment.setArguments(bundle);
        switchFragment(deviceScanningFragment);
    }

    private void putContentValuesData(Bundle bundle) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("uniqueId", this.mUniqueId);
        contentValues.put("productId", this.mProductId);
        bundle.putParcelable("commonDeviceInfo", contentValues);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isNetWorkActive() {
        return CommonUtil.aa(BaseApplication.getContext());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initNetWorkTipView(boolean z) {
        LinearLayout linearLayout = this.child.findViewById(R.id.nfc_tip_title_layout) instanceof LinearLayout ? (LinearLayout) this.child.findViewById(R.id.nfc_tip_title_layout) : null;
        if (z || !this.mIsFirstDownload) {
            if (linearLayout != null) {
                linearLayout.setVisibility(8);
                return;
            }
            return;
        }
        if (linearLayout != null) {
            linearLayout.setVisibility(0);
        }
        if (this.child.findViewById(R.id.nfc_tip_goto_setting_layout) instanceof LinearLayout) {
            LinearLayout linearLayout2 = (LinearLayout) this.child.findViewById(R.id.nfc_tip_goto_setting_layout);
            this.mSwitchSettingLayout = linearLayout2;
            linearLayout2.setOnClickListener(this);
        }
    }

    private void registerNetWorkReceiver() {
        if (this.mNetWorkChangeReceiver == null) {
            IntentFilter intentFilter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
            intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
            this.mNetWorkChangeReceiver = new NetWorkChangeBroadcastReceiver(this);
            BaseApplication.getContext().registerReceiver(this.mNetWorkChangeReceiver, intentFilter);
        }
    }

    private void unRegisterNetWorkReceiver() {
        if (this.mNetWorkChangeReceiver != null) {
            BaseApplication.getContext().unregisterReceiver(this.mNetWorkChangeReceiver);
        }
    }

    private void onClickBuyButton(Map<String, Object> map) {
        ixx.d().d(cpp.a(), AnalyticsValue.HEALTH_PLUGIN_DEVICE_BUY_PRODUCT_2060003.value(), map, 0);
        String c = this.mProductInfo.c("miaomall_url");
        if (TextUtils.isEmpty(c)) {
            LogUtil.a(TAG, "ProductIntroductionFragment haven't get miaomall url");
            c = this.mProductInfo.c(BUY_URL);
        }
        if (!TextUtils.isEmpty(c)) {
            Intent intent = new Intent();
            intent.setPackage(cez.w);
            intent.setClassName(cez.w, "com.huawei.operation.activity.WebViewActivity");
            intent.putExtra("url", c);
            intent.putExtra("EXTRA_BI_ID", "");
            intent.putExtra("EXTRA_BI_NAME", "");
            intent.putExtra("EXTRA_BI_SOURCE", "Device");
            startActivity(intent);
            return;
        }
        if (this.mainActivity != null) {
            dfq.e(this.mainActivity, R.string.IDS_device_datasourceactivity_no_vmall);
        }
    }

    private void onClickMeasureButton() {
        if (BluetoothAdapter.getDefaultAdapter().getState() != 12 || !PermissionDialogHelper.Vy_(this.mainActivity)) {
            PermissionDialogHelper.e(this, 101);
            return;
        }
        if (getString(R.string.IDS_device_measureactivity_measuring).equals(this.mStartMeasureTextView.getText().toString())) {
            return;
        }
        if (getString(R.string.IDS_device_rope_device_reconnect).equals(this.mStartMeasureTextView.getText().toString()) && BloodPressureStartFromDeviceHelper.a(dij.e(this.mProductId))) {
            BloodPressureStartFromDeviceHelper.d().d(this.mProductId, this.mUniqueId, this.mStartFromDeviceListen);
            return;
        }
        if (this.mKind == HealthDevice.HealthDeviceKind.HDK_WEIGHT || this.mKind == HealthDevice.HealthDeviceKind.HDK_BLOOD_PRESSURE || this.mKind == HealthDevice.HealthDeviceKind.HDK_BLOOD_SUGAR || this.mKind == HealthDevice.HealthDeviceKind.HDK_BODY_TEMPERATURE || this.mKind == HealthDevice.HealthDeviceKind.HDK_BLOOD_OXYGEN) {
            gotoMeasure(this.mProductId, this.mUniqueId);
        } else {
            LogUtil.a(getClass().getName(), "onClickMeasureButton else");
        }
    }

    private void gotoMeasure(String str, String str2) {
        String a2 = this.mProductInfo.n().a();
        boolean z = "huawei".equalsIgnoreCase(a2) || "honor".equalsIgnoreCase(a2) || "hygride".equalsIgnoreCase(a2);
        if (this.mKind == HealthDevice.HealthDeviceKind.HDK_WEIGHT && !z) {
            dks.Wu_(this.mHandler);
        } else {
            jumpFragment(str, str2);
        }
    }

    private void checkHealthKitLinked(String str, String str2) {
        if ("true".equals(gmz.d().c(402))) {
            dks.n(str);
            diy.c(this, str, str2, this.mProductInfo);
        } else {
            dks.WC_(this.mainActivity, str, str2, false);
        }
    }

    private void jumpFragment(String str, String str2) {
        Fragment measureFragment = this.mDeviceFragmentFactoryApi.getMeasureFragment(str);
        if (measureFragment != null) {
            Bundle bundle = new Bundle();
            bundle.putString("view", "measure");
            bundle.putString("productId", str);
            putContentValuesData(bundle);
            measureFragment.setArguments(bundle);
            switchFragment(measureFragment);
            this.mDeviceMainActivityApi.setMeasureBackClass(getActivity(), ProductIntroductionFragment.class);
            return;
        }
        if (this.mProductInfo.r() != null && "H5".equals(this.mProductInfo.b()) && "H5".equals(this.mProductInfo.r())) {
            checkHealthKitLinked(str, str2);
            return;
        }
        if (GprsDeviceHelper.c(str)) {
            Bundle bundle2 = new Bundle();
            putContentValuesData(bundle2);
            DeviceMeasureGuideShowFragment deviceMeasureGuideShowFragment = new DeviceMeasureGuideShowFragment();
            deviceMeasureGuideShowFragment.setArguments(bundle2);
            switchFragment(deviceMeasureGuideShowFragment);
            this.mDeviceMainActivityApi.setMeasureBackClass(getActivity(), ProductIntroductionFragment.class);
            return;
        }
        if (this.mProductInfo.r() != null && "H5".equals(this.mProductInfo.r())) {
            dks.n(str);
            diy.c(this, str, str2, this.mProductInfo);
        } else {
            jumpToDeviceSilentGuideFragment(str);
        }
    }

    private void jumpToDeviceSilentGuideFragment(String str) {
        HealthDevice bondedDevice = this.mHealthDeviceEntryApi.getBondedDevice(str);
        MeasurableDevice measurableDevice = bondedDevice instanceof MeasurableDevice ? (MeasurableDevice) bondedDevice : null;
        boolean equals = HiAnalyticsConstant.KeyAndValue.NUMBER_01.equals(this.mProductInfo.p());
        if (measurableDevice != null) {
            if ((measurableDevice.isAutoDevice() && equals) || BloodPressureStartFromDeviceHelper.c()) {
                LogUtil.c(TAG, "the productId " + str + " is not res, and this is auto");
                enterDeviceSilentGuideFragment(str);
                return;
            }
            LogUtil.c(TAG, " the productId : ", str, " is not res, and this is not auto ", " kind.name is ", this.mKind.name());
            jumpToFragment();
            return;
        }
        LogUtil.b(TAG, "the device is null");
    }

    private void enterDeviceSilentGuideFragment(String str) {
        if (PermissionDialogHelper.b(str)) {
            LogUtil.h(TAG, "this phone not support this device");
            nrh.b(BaseApplication.getContext(), R.string.IDS_device_phone_not_support);
            return;
        }
        DeviceSilentGuideFragment deviceSilentGuideFragment = new DeviceSilentGuideFragment();
        Bundle bundle = new Bundle();
        bundle.putString("view", "bond");
        bundle.putString("productId", str);
        putContentValuesData(bundle);
        deviceSilentGuideFragment.setArguments(bundle);
        switchFragment(deviceSilentGuideFragment);
        this.mDeviceMainActivityApi.setMeasureBackClass(getActivity(), ProductIntroductionFragment.class);
    }

    private void jumpToFragment() {
        ArrayList<ContentValues> bondedDeviceByProductId = cei.b().getBondedDeviceByProductId(this.mProductId);
        if (bondedDeviceByProductId == null || bondedDeviceByProductId.size() == 0) {
            LogUtil.h(TAG, "deviceList is null");
            return;
        }
        if (bondedDeviceByProductId.size() == 1) {
            jumpToMeasureFragment(this.mDeviceFragmentFactoryApi.getMeasureFragment(this.mKind.name()), bondedDeviceByProductId.get(0));
            return;
        }
        for (ContentValues contentValues : bondedDeviceByProductId) {
            if (contentValues.getAsString("uniqueId").equals(this.mUniqueId)) {
                jumpToMeasureFragment(this.mDeviceFragmentFactoryApi.getMeasureFragment(this.mKind.name()), contentValues);
                return;
            }
        }
        LogUtil.h(TAG, "the device not in device db");
    }

    private void jumpToMeasureFragment(Fragment fragment, ContentValues contentValues) {
        if (fragment != null) {
            Bundle bundle = new Bundle();
            bundle.putString("view", "measure");
            bundle.putString("kind", this.mKind.name());
            bundle.putString("productId", this.mProductId);
            bundle.putParcelable("commonDeviceInfo", contentValues);
            fragment.setArguments(bundle);
            switchFragment(fragment);
            this.mDeviceMainActivityApi.setMeasureBackClass(getActivity(), ProductIntroductionFragment.class);
        }
    }

    private boolean isBondedDevice() {
        if (GprsDeviceHelper.c(this.mProductId)) {
            return true;
        }
        if (this.mHealthDeviceEntryApi.isDeviceKitUniversal(this.mProductId)) {
            return this.mHealthDeviceEntryApi.getBondedDeviceUniversal(this.mProductId, this.mUniqueId) != null;
        }
        if (getArguments().getBoolean("isAdd")) {
            return false;
        }
        Iterator<ContentValues> it = cei.b().getBondedDeviceByProductId(this.mProductId).iterator();
        while (it.hasNext()) {
            if (it.next().getAsString("uniqueId").equals(this.mUniqueId)) {
                return true;
            }
        }
        return false;
    }

    @Override // androidx.fragment.app.Fragment
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        LogUtil.a(TAG, "onRequestPermissionsResult(),permissions:" + strArr.length);
        jeg.d().d(strArr, iArr);
    }

    public void showRingerModeDialog() {
        String format;
        if (this.mainActivity != null) {
            if (nsn.ae(BaseApplication.getContext())) {
                format = String.format(Locale.ENGLISH, getResources().getString(R.string.IDS_pad_device_bind_abnormal), new Object[0]);
            } else {
                format = String.format(Locale.ENGLISH, getResources().getString(R.string.IDS_device_bind_abnormal_mode_prompt), new Object[0]);
            }
            CustomTextAlertDialog a2 = new CustomTextAlertDialog.Builder(this.mainActivity).b(this.mainActivity.getResources().getString(R.string.IDS_service_area_notice_title)).e(format).cyV_(this.mainActivity.getResources().getString(R.string._2130841794_res_0x7f0210c2), new View.OnClickListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.ProductIntroductionFragment$$ExternalSyntheticLambda8
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ProductIntroductionFragment.this.m319xf8a7f16e(view);
                }
            }).a();
            this.mRingerModeDialog = a2;
            a2.show();
        }
    }

    /* renamed from: lambda$showRingerModeDialog$8$com-huawei-health-ecologydevice-ui-measure-fragment-ProductIntroductionFragment, reason: not valid java name */
    /* synthetic */ void m319xf8a7f16e(View view) {
        CustomTextAlertDialog customTextAlertDialog = this.mRingerModeDialog;
        if (customTextAlertDialog != null) {
            customTextAlertDialog.dismiss();
        } else {
            LogUtil.a(TAG, "ProductIntroductionFragment setPositiveButton mRingerModeDialog = null");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v2, types: [android.media.AudioRecord] */
    /* JADX WARN: Type inference failed for: r0v4, types: [int] */
    public boolean hasAudioRecordPermission() {
        LogUtil.a(TAG, "enter hasAudioRecordPermission");
        long currentTimeMillis = System.currentTimeMillis();
        AudioRecord audioRecord = new AudioRecord(1, 44100, 16, 1, 44100);
        boolean z = false;
        try {
            LogUtil.a(TAG, "new AudioRecrod state: " + audioRecord.getRecordingState());
        } catch (IllegalStateException e) {
            LogUtil.a(TAG, e.getMessage());
        } finally {
            audioRecord.release();
        }
        if (audioRecord.getRecordingState() != 1) {
            return false;
        }
        audioRecord.startRecording();
        LogUtil.a(TAG, "startRecording state: " + audioRecord.getRecordingState());
        boolean z2 = audioRecord.getRecordingState() == 3;
        audioRecord.stop();
        audioRecord.release();
        z = z2;
        audioRecord = ((System.currentTimeMillis() - currentTimeMillis) > 200L ? 1 : ((System.currentTimeMillis() - currentTimeMillis) == 200L ? 0 : -1));
        if (audioRecord < 0 && !z) {
            nsn.c(this.mainActivity, this.mainActivity.getString(R.string.IDS_device_am16_permission_str));
        }
        LogUtil.a(TAG, "hasAudioRecordPermission: " + z);
        return z;
    }

    public boolean isRingerModeNormal() {
        AudioManager audioManager = this.mainActivity.getSystemService(PresenterUtils.AUDIO) instanceof AudioManager ? (AudioManager) this.mainActivity.getSystemService(PresenterUtils.AUDIO) : null;
        return audioManager != null && audioManager.getRingerMode() == 2;
    }

    @Override // com.huawei.health.ecologydevice.ui.measure.fragment.ProductFragment, androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        LogUtil.a(TAG, "onActivityResult requestCode", Integer.valueOf(i), " resultCode", Integer.valueOf(i2));
        if (this.mainActivity != null) {
            Object systemService = this.mainActivity.getSystemService(RemoteMessageConst.NOTIFICATION);
            NotificationManager notificationManager = systemService instanceof NotificationManager ? (NotificationManager) systemService : null;
            if (i == 0 && notificationManager != null && notificationManager.isNotificationPolicyAccessGranted()) {
                LogUtil.a(TAG, "return from system settings...requestCode:", Integer.valueOf(i));
                this.mPermissionDialogHelper.b(PermissionUtil.PermissionType.PHONE_STATE);
            }
        }
        if (i == 1001 && i2 == 3) {
            LogUtil.a(TAG, "privacy result");
            if (intent == null) {
                LogUtil.h(TAG, "data is null");
                return;
            } else if (intent.getIntExtra(HONOR_SHOW_PRIVACY_KEY, 0) == 1) {
                LogUtil.a(TAG, "user agree privacy");
                Bundle bundle = new Bundle();
                bundle.putString("productId", this.mProductId);
                bundle.putString("title", getTitle(this.mProductId, this.mProductInfo));
                switchHagridDeviceBindGuidFragment(bundle);
            }
        }
        if ((i == 101 || i == 100) && i2 == -1) {
            LogUtil.a(TAG, "onbluetoothOpened resultcode = ", 101);
            if (BloodPressureStartFromDeviceHelper.a(dij.e(this.mProductId))) {
                LogUtil.a(TAG, "initBloodPressureStartFromDevice");
                BloodPressureStartFromDeviceHelper.d().d(this.mProductId, this.mUniqueId, this.mStartFromDeviceListen);
            } else if (i != 100) {
                LogUtil.a(TAG, "gotoMeasure");
                gotoMeasure(this.mProductId, this.mUniqueId);
            }
        }
    }

    static class QueryDataHandler extends BaseHandler<ProductIntroductionFragment> {
        QueryDataHandler(ProductIntroductionFragment productIntroductionFragment) {
            super(productIntroductionFragment);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        public void handleMessageWhenReferenceNotNull(ProductIntroductionFragment productIntroductionFragment, Message message) {
            HiHealthData hiHealthData = message.obj instanceof HiHealthData ? (HiHealthData) message.obj : null;
            int i = message.what;
            if (i != 10001) {
                switch (i) {
                    case 100:
                        productIntroductionFragment.refreshWeightData(hiHealthData);
                        break;
                    case 101:
                        productIntroductionFragment.refreshBloodPressureData(hiHealthData);
                        break;
                    case 102:
                        productIntroductionFragment.refreshBloodSugarData(hiHealthData);
                        break;
                    case 103:
                        productIntroductionFragment.showEmptyView();
                        break;
                    case 104:
                        if (hiHealthData != null) {
                            productIntroductionFragment.refreshBodyTempData(hiHealthData);
                            break;
                        }
                        break;
                    case 105:
                        if (hiHealthData != null) {
                            productIntroductionFragment.refreshBloodOxygenData(hiHealthData);
                            break;
                        }
                        break;
                    case 106:
                        productIntroductionFragment.refreshUricAcidData(hiHealthData);
                        break;
                    case 107:
                        if (message.obj instanceof Integer) {
                            productIntroductionFragment.refreshBloodSugarMeasureCount(((Integer) message.obj).intValue());
                            break;
                        }
                        break;
                }
            }
            productIntroductionFragment.checkUserInfoForDialog(message);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkUserInfoForDialog(Message message) {
        if (!dks.a(message.obj)) {
            dks.b(getContext(), new IBaseResponseCallback() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.ProductIntroductionFragment$$ExternalSyntheticLambda9
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i, Object obj) {
                    ProductIntroductionFragment.this.m313xdc680afa(i, obj);
                }
            });
        } else {
            jumpFragment(this.mProductId, this.mUniqueId);
        }
    }

    /* renamed from: lambda$checkUserInfoForDialog$9$com-huawei-health-ecologydevice-ui-measure-fragment-ProductIntroductionFragment, reason: not valid java name */
    /* synthetic */ void m313xdc680afa(int i, Object obj) {
        if (i == 0) {
            jumpFragment(this.mProductId, this.mUniqueId);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshWeightData(HiHealthData hiHealthData) {
        if (this.mDeviceMeasureValueView == null || this.mDeviceMeasureDateView == null) {
            LogUtil.h(TAG, "refreshWeightData view is null");
            return;
        }
        if (hiHealthData == null) {
            showEmptyView();
            return;
        }
        double d = hiHealthData.getDouble("bodyWeight");
        int i = hiHealthData.getInt("trackdata_deviceType");
        long startTime = hiHealthData.getStartTime();
        if (d > 0.0d) {
            setDeviceMeasureValueView(d, ((WeightDataUtilsApi) Services.c("PluginDevice", WeightDataUtilsApi.class)).getFractionDigitForWeight(d, i));
            setDeviceMeasureValueUnitView(d);
            this.mDeviceMeasureDateView.setText(nsj.f(startTime));
            this.mLastMeasurementLayout.setVisibility(0);
            return;
        }
        showEmptyView();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshBloodPressureData(HiHealthData hiHealthData) {
        if (this.mDeviceMeasureValueView == null || this.mDeviceMeasureDateView == null) {
            LogUtil.h(TAG, "refreshBloodPressureData view is null");
            return;
        }
        if (hiHealthData == null) {
            showEmptyView();
            return;
        }
        double d = hiHealthData.getDouble("BLOOD_PRESSURE_SYSTOLIC");
        double d2 = hiHealthData.getDouble("BLOOD_PRESSURE_DIASTOLIC");
        long startTime = hiHealthData.getStartTime();
        this.mLastMeasurementLayout.setVisibility(0);
        String e = UnitUtil.e(d, 1, 0);
        String e2 = UnitUtil.e(d2, 1, 0);
        this.mDeviceMeasureValueView.setText(e + "/" + e2);
        this.mDeviceMeasureDateView.setText(nsj.f(startTime));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshBloodSugarData(HiHealthData hiHealthData) {
        if (this.mDeviceMeasureValueView == null || this.mDeviceMeasureDateView == null) {
            LogUtil.h(TAG, "refreshBloodPressureData view is null");
            return;
        }
        if (hiHealthData == null) {
            showEmptyView();
            return;
        }
        this.mDeviceMeasureValueLayout.setVisibility(0);
        this.mLastMeasurementLayout.setVisibility(0);
        this.mDeviceMeasureBloodSugarLevelView.setVisibility(0);
        this.mDeviceMeasureTimePeriodView.setVisibility(0);
        this.mDeviceLatestDataAndCountLayout.setVisibility(0);
        this.mDeviceLastMeasurementView.setVisibility(8);
        dei.c().b(hiHealthData, new BloodSugarDataUiResponse(this.mHandler, 107));
        long startTime = hiHealthData.getStartTime();
        this.mDeviceMeasureDateView.setText(nsj.i(startTime));
        this.mDeviceMeasureLatestTimeView.setText(nsj.g(startTime));
        this.mDeviceMeasureValueView.setText(UnitUtil.e(hiHealthData.getDouble("point_value"), 1, 1));
        deb.d(this.mDeviceMeasureBloodSugarLevelView, deb.c(BaseApplication.getContext(), hiHealthData.getType(), (float) hiHealthData.getDouble("point_value")));
        this.mDeviceMeasureTimePeriodView.setText(deb.a(BigDecimal.valueOf(hiHealthData.getType()).intValue()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshBloodSugarMeasureCount(int i) {
        this.mDeviceMeasureCountView.setVisibility(0);
        this.mDeviceMeasureCountView.setText(BaseApplication.getContext().getResources().getQuantityString(R.plurals._2130903345_res_0x7f030131, i, Integer.valueOf(i)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshUricAcidData(HiHealthData hiHealthData) {
        if (this.mUricAcidValueView == null || this.mUricAcidDateView == null) {
            LogUtil.h(TAG, "refreshUricAcidData view is null");
            return;
        }
        if (hiHealthData == null) {
            this.mUricAcidValueLayout.setVisibility(8);
            return;
        }
        this.mUricAcidValueLayout.setVisibility(0);
        this.mUricAcidValueView.setText(UnitUtil.e(hiHealthData.getDouble("point_value"), 1, 0));
        this.mUricAcidValueUnitView.setText(URIC_ACID_UNIT_MICRO_MOLE_L);
        deq.d(this.mUricAcidValueLevelView, deq.b(BaseApplication.getContext(), (int) hiHealthData.getDouble("point_value")));
        this.mUricAcidDateView.setText(nsj.f(hiHealthData.getStartTime()));
    }

    private void setDeviceMeasureValueView(double d, int i) {
        if (UnitUtil.h()) {
            this.mDeviceMeasureValueView.setText(UnitUtil.e(UnitUtil.h(d), 1, i));
        } else {
            this.mDeviceMeasureValueView.setText(UnitUtil.e(d, 1, i));
        }
    }

    private void setDeviceMeasureValueUnitView(double d) {
        if (UnitUtil.h()) {
            this.mDeviceMeasureValueUnitView.setText(BaseApplication.getContext().getResources().getQuantityString(R.plurals._2130903216_res_0x7f0300b0, dks.c(UnitUtil.h(d)), ""));
        } else {
            this.mDeviceMeasureValueUnitView.setText(BaseApplication.getContext().getResources().getQuantityString(R.plurals._2130903215_res_0x7f0300af, dks.c(d), ""));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showEmptyView() {
        this.mLastMeasurementLayout.setVisibility(8);
        this.mDeviceMeasureTimePeriodView.setVisibility(8);
        this.mDeviceMeasureBloodSugarLevelView.setVisibility(8);
        if (this.mKind == HealthDevice.HealthDeviceKind.HDK_BLOOD_SUGAR) {
            this.mDeviceMeasureValueLayout.setVisibility(8);
            return;
        }
        if (this.mKind == HealthDevice.HealthDeviceKind.HDK_BLOOD_PRESSURE) {
            this.mDeviceMeasureValueView.setText("--/--");
            return;
        }
        this.mDeviceMeasureValueView.setText("--");
        if (this.mKind == HealthDevice.HealthDeviceKind.HDK_WEIGHT) {
            setDeviceMeasureValueUnitView(0.0d);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshBodyTempData(HiHealthData hiHealthData) {
        this.mLastMeasurementLayout.setVisibility(0);
        this.mDeviceMeasureDateView.setText(nsj.f(hiHealthData.getStartTime()));
        this.mDeviceMeasureValueView.setText(UnitUtil.e(hiHealthData.getDouble("point_value"), 1, 1));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshBloodOxygenData(HiHealthData hiHealthData) {
        this.mLastMeasurementLayout.setVisibility(0);
        this.mDeviceMeasureDateView.setText(nsj.f(hiHealthData.getStartTime()));
        this.mDeviceMeasureValueView.setText(UnitUtil.e(hiHealthData.getDouble("point_value"), 1, 0));
    }

    private void openDeviceHelpActivity() {
        String deviceHelpH5Url = this.mHonourDeviceConstantsApi.getDeviceHelpH5Url(this.mProductId);
        if (TextUtils.isEmpty(deviceHelpH5Url)) {
            Intent intent = new Intent(this.mainActivity, (Class<?>) DeviceUsageDescriptionActivity.class);
            intent.putExtra("productId", this.mProductId);
            startActivity(intent);
            return;
        }
        diy.Vr_(this.mainActivity, deviceHelpH5Url);
    }

    private void setDeviceMeasureLayout() {
        if (getActivity() == null || this.child == null) {
            return;
        }
        this.mDeviceMeasureDateView = (HealthTextView) this.child.findViewById(R.id.device_measure_date);
        this.mDeviceMeasureValueView = (HealthTextView) this.child.findViewById(R.id.device_measure_value);
        this.mDeviceMeasureValueUnitView = (HealthTextView) this.child.findViewById(R.id.device_measure_value_unit);
        this.mDeviceMeasureValueLayout = (ViewGroup) this.child.findViewById(R.id.device_measure_value_layout);
        this.mDataNotificationLayout = (ViewGroup) this.child.findViewById(R.id.data_notification_layout);
        this.mDeviceMeasureTimePeriodView = (HealthTextView) this.child.findViewById(R.id.device_measure_time_period);
        this.mDeviceMeasureBloodSugarLevelView = (HealthTextView) this.child.findViewById(R.id.device_measure_bloodsugar_level);
        ViewGroup viewGroup = (ViewGroup) this.child.findViewById(R.id.device_latest_date_and_count_layout);
        this.mDeviceLatestDataAndCountLayout = viewGroup;
        viewGroup.setOnClickListener(this);
        ViewGroup viewGroup2 = (ViewGroup) this.child.findViewById(R.id.device_latest_value_layout);
        this.mDeviceLatestValueLayout = viewGroup2;
        viewGroup2.setOnClickListener(this);
        this.mDeviceMeasureCountView = (HealthTextView) this.child.findViewById(R.id.device_measure_count);
        this.mDeviceMeasureLatestTimeView = (HealthTextView) this.child.findViewById(R.id.device_measure_latest_time);
        this.mDeviceLastMeasurementView = (HealthTextView) this.child.findViewById(R.id.device_last_measurement);
        this.mLastMeasurementLayout = (RelativeLayout) this.child.findViewById(R.id.device_last_measurement_layout);
        this.mUricAcidValueLayout = (RelativeLayout) this.child.findViewById(R.id.device_measure_uric_acid_value_layout);
        this.mUricAcidValueView = (HealthTextView) this.child.findViewById(R.id.device_measure_uric_acid_value);
        this.mUricAcidValueUnitView = (HealthTextView) this.child.findViewById(R.id.device_measure_uric_acid_value_unit);
        this.mUricAcidValueLevelView = (HealthTextView) this.child.findViewById(R.id.device_measure_uric_acid_level);
        this.mUricAcidValueArrowImage = (ImageView) this.child.findViewById(R.id.iv_measure_uric_acid_arrow);
        this.mUricAcidDateView = (HealthTextView) this.child.findViewById(R.id.device_measure_uric_acid_date);
        this.mUricAcidValueLayout.setOnClickListener(this);
        ImageView imageView = (ImageView) nsy.cMd_(this.child, R.id.iv_measure_arrow);
        if (LanguageUtil.bc(this.mainActivity)) {
            imageView.setImageResource(R.drawable._2131427841_res_0x7f0b0201);
            this.mUricAcidValueArrowImage.setImageResource(R.drawable._2131427841_res_0x7f0b0201);
        } else {
            imageView.setImageResource(R.drawable._2131427842_res_0x7f0b0202);
            this.mUricAcidValueArrowImage.setImageResource(R.drawable._2131427842_res_0x7f0b0202);
        }
    }

    static class BloodSugarDataUiResponse<T> implements CommonUiResponse<T> {
        private int mDataType;
        private Handler mHandler;

        private BloodSugarDataUiResponse(Handler handler, int i) {
            this.mHandler = handler;
            this.mDataType = i;
        }

        @Override // com.huawei.health.ecologydevice.open.data.model.CommonUiResponse
        public void onResponse(int i, T t) {
            if (i != 0) {
                LogUtil.h(ProductIntroductionFragment.TAG, "Failed to mDataType = ", Integer.valueOf(this.mDataType), ", errorCode = ", Integer.valueOf(i));
                dks.Wz_(this.mHandler, 103, null);
            } else {
                dks.Wz_(this.mHandler, this.mDataType, t);
            }
        }
    }

    @Override // com.huawei.health.ecologydevice.ui.measure.fragment.ProductFragment
    protected void autoConnectDevice() {
        LogUtil.a(TAG, "connectDevice: None");
    }

    static class NetWorkChangeBroadcastReceiver extends BroadcastReceiver {
        private WeakReference<ProductIntroductionFragment> mReference;

        NetWorkChangeBroadcastReceiver(ProductIntroductionFragment productIntroductionFragment) {
            this.mReference = new WeakReference<>(productIntroductionFragment);
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            LogUtil.a(ProductIntroductionFragment.TAG, "NetWorkChangeBroadcastReceiver onReceive Action...", intent.getAction());
            ProductIntroductionFragment productIntroductionFragment = this.mReference.get();
            if (productIntroductionFragment == null) {
                LogUtil.h(ProductIntroductionFragment.TAG, "NetWorkChangeBroadcastReceiver activity is null");
            } else if ("android.net.conn.CONNECTIVITY_CHANGE".equals(intent.getAction())) {
                productIntroductionFragment.initNetWorkTipView(productIntroductionFragment.isNetWorkActive());
            }
        }
    }

    private void updateDeviceResource() {
        if (this.mKind != HealthDevice.HealthDeviceKind.HDK_BLOOD_PRESSURE) {
            LogUtil.a(TAG, "updateDeviceResource not bloodPressure device");
            return;
        }
        if (this.mainActivity == null) {
            LogUtil.h(TAG, "updateDeviceResource mainActivity is null");
            return;
        }
        final String e = dij.e(this.mProductId);
        if (!cwt.a().c(this.mainActivity, e)) {
            LogUtil.a(TAG, "updateDeviceResource isNeedUpdateResource is false");
        } else {
            cwt.a().c(true);
            cwt.a().b(this.mKind.name(), e, new DownloadDeviceInfoCallBack() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.ProductIntroductionFragment.8
                @Override // com.huawei.health.ecologydevice.clouddevice.DownloadDeviceInfoCallBack
                public void onSuccess() {
                    LogUtil.a(ProductIntroductionFragment.TAG, "updateDeviceResource onSuccess");
                    cwt.a().d(ProductIntroductionFragment.this.mainActivity, e);
                }

                @Override // com.huawei.health.ecologydevice.clouddevice.DownloadDeviceInfoCallBack
                public void onFailure(int i) {
                    LogUtil.a(ProductIntroductionFragment.TAG, "updateDeviceResource onFailure");
                }

                @Override // com.huawei.health.ecologydevice.clouddevice.DownloadDeviceInfoCallBack
                public void onNetworkError() {
                    LogUtil.a(ProductIntroductionFragment.TAG, "updateDeviceResource onNetworkError");
                }
            });
        }
    }
}
