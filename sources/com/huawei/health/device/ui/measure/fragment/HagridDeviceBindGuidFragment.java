package com.huawei.health.device.ui.measure.fragment;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.huawei.devicesdk.callback.DeviceScanCallback;
import com.huawei.devicesdk.entity.ScanMode;
import com.huawei.featureuserprofile.route.hwgpxmodel.Wpt;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.health.R;
import com.huawei.health.device.callback.BindHealthDeviceCallback;
import com.huawei.health.device.callback.IDeviceEventHandler;
import com.huawei.health.device.connectivity.comm.BleDeviceHelper;
import com.huawei.health.device.connectivity.comm.MeasurableDevice;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.device.open.data.model.HealthData;
import com.huawei.health.device.ui.DeviceMainActivity;
import com.huawei.health.device.ui.measure.adapter.ScanProductListAdapter;
import com.huawei.health.device.util.EventBus;
import com.huawei.health.ecologydevice.connectivity.ScanFilter;
import com.huawei.health.ecologydevice.manager.ResourceManager;
import com.huawei.health.ecologydevice.networkclient.HealthEngineRequestManager;
import com.huawei.health.ecologydevice.ui.BaseFragment;
import com.huawei.health.ecologydevice.ui.measure.fragment.ProductIntroductionFragment;
import com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceCategoryFragment;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcloudmodel.callback.ICloudOperationResult;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.nfc.PluginPayAdapter;
import com.huawei.pluginhealthzone.interactors.HealthZonePushReceiver;
import com.huawei.ui.commonui.dialog.CustomAlertDialog;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import com.huawei.ui.commonui.dialog.CustomViewDialog;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.progressbar.HealthProgressBar;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.commonui.utils.CustomPermissionAction;
import com.huawei.unitedevice.entity.UniteDevice;
import defpackage.bjf;
import defpackage.bzw;
import defpackage.ceo;
import defpackage.cev;
import defpackage.cgt;
import defpackage.cjx;
import defpackage.ckm;
import defpackage.coy;
import defpackage.cpa;
import defpackage.cpp;
import defpackage.cpw;
import defpackage.cpy;
import defpackage.cpz;
import defpackage.ctk;
import defpackage.ctq;
import defpackage.cwz;
import defpackage.cxh;
import defpackage.dcz;
import defpackage.dfe;
import defpackage.dij;
import defpackage.dis;
import defpackage.dja;
import defpackage.gmz;
import defpackage.gnb;
import defpackage.koq;
import defpackage.nrh;
import defpackage.nsn;
import health.compact.a.CommonUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/* loaded from: classes3.dex */
public class HagridDeviceBindGuidFragment extends BaseFragment implements View.OnClickListener {
    private static final int BUILDER_VIEW_DEFAULT_MARGIN = 0;
    private static final String BUNDLE_KEY_AUTO_DEVICE_SSID = "auth_device_id";
    private static final String BUNDLE_KEY_DEVICE_WEIGHT = "deviceWeight";
    private static final String BUNDLE_KEY_HAS_MANAGER = "hasManager";
    private static final String BUNDLE_KEY_IS_BIND_SUCCESS = "isBindSuccess";
    private static final String BUNDLE_KEY_MAIN_HUID = "mainHuid";
    private static final String BUNDLE_KEY_PRODUCT_ID = "productId";
    private static final String BUNDLE_KEY_RESIS_FAT = "deviceResis";
    private static final String BUNDLE_KEY_VIEW = "view";
    private static final String BUNDLE_VALUE_BIND_RESULT_CONFIRM = "bindResultConfirm";
    private static final int DEVICE_REGISTE_SUCCESS = 2;
    private static final int DEVICE_SCAN_REFRESH = 0;
    private static final int DEVICE_SCAN_TIMEOUT = 1;
    private static final int GET_PART_MAC = 24;
    private static final int INDEX_TWO = 2;
    private static final Object LOCK = new Object();
    private static final int MAP_DEFAULT_SIZE = 16;
    private static final int MEASURE_TIMEOUT = 90000;
    private static final int MESSAGE_LOADING_TIMEOUT = 5;
    private static final int MINIMUM_SCAN_INTERVAL = 6000;
    private static final int MSG_BIND_FAIL = 2;
    private static final int MSG_CONNECT_TIMEOUT = 1;
    private static final int MSG_DEVICE_CHECK_FAIL = 3;
    private static final int MSG_SCAN_SECOND_DEVICE_FAIL = 4;
    private static final int MSG_SCAN_TARGET_DEVICE = 7;
    private static final int MSG_START_SCAN = 6;
    private static final int NUMBEN_TWO = 2;
    private static final int RESULT_CODE_NOT_FOUND = 1001;
    private static final int SCAN_SECOND_DEVICE_TIMEOUT = 2000;
    private static final int SCAN_TIMEOUT = 30000;
    private static final int START_SCAN_DELAY_TIME = 2000;
    private static final String TAG = "HagridDeviceBindGuidFragment";
    private static final String TAG_RELEASE = "R_Weight_HagridDeviceBindGuidFragment";
    private static final int UPDATE_DATA_BASE_FAIL_VALUE = 0;
    private static final int WEIGHT_DEFAULTS = -1;
    private static final int ZIP_BIND_PAIRED_BOOT = 0;
    private static final int ZIP_BIND_PAIRING = 2;
    private ScanProductListAdapter mAdapter;
    private LinearLayout mBackLayout;
    private LinearLayout mBackgroundLayout;
    private HealthTextView mBluetoothPairCancelText;
    private HealthTextView mBluetoothPairConfirmText;
    private ImageView mBluetoothPairResultGuideImg;
    private HealthTextView mBluetoothPairStepDescription;
    private HealthTextView mBluetoothPairStepTitle;
    private Context mContext;
    private CustomAlertDialog mCustomAlertDialog;
    private ContentValues mDeviceInfo;
    private CustomViewDialog mDeviceListDialog;
    private cwz mDeviceScanManager;
    private String mDeviceSn;
    private NoTitleCustomAlertDialog mDialog;
    private Handler mHandler;
    private BindCallback mHandlerCallback;
    private gmz mInteractor;
    private boolean mIsAllowBackPress;
    private boolean mIsBeingPaired;
    private boolean mIsNfcCommect;
    private boolean mIsRebind;
    private long mLastScanTime;
    private HealthTextView mListTitleTv;
    private ListView mListView;
    private HealthProgressBar mLoading;
    private LinearLayout mNextLayout;
    private String mProductId;
    private dcz mProductInfo;
    private HealthDevice mSelectedHealthDevice;
    private int mSelectedPosition;
    private String mSsid;
    private String mUniqueId;
    private float mWeight;
    private ckm mWeightAndFatRateData;
    private BindHealthDeviceCallback mBindCallback = new BindHealthDeviceCallback() { // from class: com.huawei.health.device.ui.measure.fragment.HagridDeviceBindGuidFragment.1
        @Override // com.huawei.health.device.callback.IHealthDeviceCallback
        public void onDataChanged(HealthDevice healthDevice, HealthData healthData) {
        }

        @Override // com.huawei.health.device.callback.IHealthDeviceCallback
        public void onDataChanged(HealthDevice healthDevice, List<HealthData> list) {
        }

        @Override // com.huawei.health.device.callback.IHealthDeviceCallback
        public void onProgressChanged(HealthDevice healthDevice, HealthData healthData) {
        }

        @Override // com.huawei.health.device.callback.IHealthDeviceCallback
        public void onStatusChanged(HealthDevice healthDevice, int i) {
        }

        @Override // com.huawei.health.device.callback.BindHealthDeviceCallback
        public void onStatusChanged(HealthDevice healthDevice, int i, Bundle bundle) {
            LogUtil.a(HagridDeviceBindGuidFragment.TAG, "onStatusChanged > status: ", Integer.valueOf(i), ", mIsBingTimeout: ", Boolean.valueOf(HagridDeviceBindGuidFragment.this.mIsBingTimeout));
            HagridDeviceBindGuidFragment.this.processCallbackResult(i, bundle);
        }

        @Override // com.huawei.health.device.callback.IHealthDeviceCallback
        public void onFailed(HealthDevice healthDevice, int i) {
            if (i == 3 && HagridDeviceBindGuidFragment.this.mHandler != null) {
                HagridDeviceBindGuidFragment.this.mHandler.sendEmptyMessage(2);
            }
            LogUtil.a(HagridDeviceBindGuidFragment.TAG_RELEASE, " send user auth command, user authentication is completed, the binding of fail");
        }
    };
    private IDeviceEventHandler mBindingStatusCallback = new IDeviceEventHandler() { // from class: com.huawei.health.device.ui.measure.fragment.HagridDeviceBindGuidFragment.2
        @Override // com.huawei.health.device.callback.IDeviceEventHandler
        public void onDeviceFound(HealthDevice healthDevice) {
        }

        @Override // com.huawei.health.device.callback.IDeviceEventHandler
        public void onScanFailed(int i) {
        }

        @Override // com.huawei.health.device.callback.IDeviceEventHandler
        public void onStateChanged(int i) {
            if (i == 7) {
                HagridDeviceBindGuidFragment.this.passDevicePair();
                return;
            }
            if (i != 8) {
                if (i == 10) {
                    cjx.e().b(HagridDeviceBindGuidFragment.this.mProductId, HagridDeviceBindGuidFragment.this.mProductInfo.s(), HagridDeviceBindGuidFragment.this.mSelectedHealthDevice, this);
                    return;
                } else {
                    LogUtil.h(HagridDeviceBindGuidFragment.TAG_RELEASE, "in else branch");
                    return;
                }
            }
            LogUtil.h(HagridDeviceBindGuidFragment.TAG_RELEASE, "DeviceBindWaitingFragment bind fail");
            Bundle bundle = new Bundle();
            bundle.putString("productId", HagridDeviceBindGuidFragment.this.mProductId);
            LogUtil.bRh_(907127004, HagridDeviceBindGuidFragment.TAG, bundle, false, "Fail to bind device ", bundle);
            ceo.d().n(HagridDeviceBindGuidFragment.this.mUniqueId);
            HagridDeviceBindGuidFragment.this.showFailScanDialog();
        }
    };
    private boolean mIsHealthDataStatus = false;
    private BleDeviceHelper mBleDeviceHelper = null;
    private boolean mIsBingTimeout = false;
    private Timer mConnectTimer = null;
    private ArrayList<HealthDevice> mListItems = new ArrayList<>(10);
    private final Object mListItemLock = new Object();
    private boolean mIsHasManaer = false;
    private boolean mIsHasBondedProduct = false;
    private String mScanKind = HealthDevice.HealthDeviceKind.HDK_UNKNOWN.name();
    private boolean mIsJump = false;
    private boolean isGotoUserInfoActivity = false;
    private byte[] mMainHuid = null;
    private String mDeviceIdFromDevice = "";
    private boolean mIsFromIconnectDialog = false;
    private boolean mIsConnectBluetooth = false;
    private AdapterView.OnItemClickListener mOnItemClickListener = new AdapterView.OnItemClickListener() { // from class: com.huawei.health.device.ui.measure.fragment.HagridDeviceBindGuidFragment$$ExternalSyntheticLambda3
        @Override // android.widget.AdapterView.OnItemClickListener
        public final void onItemClick(AdapterView adapterView, View view, int i, long j) {
            HagridDeviceBindGuidFragment.this.m141xfeb03eb(adapterView, view, i, j);
        }
    };

    static class DeviceScanHandler extends Handler {
        private final WeakReference<HagridDeviceBindGuidFragment> weakRef;

        DeviceScanHandler(HagridDeviceBindGuidFragment hagridDeviceBindGuidFragment) {
            this.weakRef = new WeakReference<>(hagridDeviceBindGuidFragment);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message == null) {
                LogUtil.h(HagridDeviceBindGuidFragment.TAG_RELEASE, "DeviceScanHandler mHandler handleMessage:msg == null");
                return;
            }
            HagridDeviceBindGuidFragment hagridDeviceBindGuidFragment = this.weakRef.get();
            if (hagridDeviceBindGuidFragment == null) {
                LogUtil.h(HagridDeviceBindGuidFragment.TAG_RELEASE, "DeviceScanHandler mHandler handleMessage:context is null");
            } else {
                handleMessagePatchOne(message, hagridDeviceBindGuidFragment);
                handleMessagePatchTwo(message, hagridDeviceBindGuidFragment);
            }
        }

        private void handleMessagePatchOne(Message message, HagridDeviceBindGuidFragment hagridDeviceBindGuidFragment) {
            int i = message.what;
            if (i == 0) {
                synchronized (HagridDeviceBindGuidFragment.LOCK) {
                    if (message.obj instanceof HealthDevice) {
                        hagridDeviceBindGuidFragment.refreshDialogDeviceList((HealthDevice) message.obj);
                    }
                }
                return;
            }
            if (i == 1) {
                synchronized (HagridDeviceBindGuidFragment.LOCK) {
                    hagridDeviceBindGuidFragment.jumpScanFail(hagridDeviceBindGuidFragment);
                }
            } else {
                if (i != 2) {
                    if (i != 5 || hagridDeviceBindGuidFragment.mListTitleTv == null || hagridDeviceBindGuidFragment.mLoading == null) {
                        return;
                    }
                    hagridDeviceBindGuidFragment.mLoading.setVisibility(8);
                    hagridDeviceBindGuidFragment.mListTitleTv.setText(R.string.IDS_device_mgr_device_scan_completed_title);
                    return;
                }
                LogUtil.a(HagridDeviceBindGuidFragment.TAG, "DEVICE_REGISTE_SUCCESS");
            }
        }

        private void handleMessagePatchTwo(Message message, HagridDeviceBindGuidFragment hagridDeviceBindGuidFragment) {
            int i = message.what;
            if (i == 4) {
                hagridDeviceBindGuidFragment.gotoBindingDevicePage();
                return;
            }
            if (i == 6) {
                if (hagridDeviceBindGuidFragment.isBluetoothOpen()) {
                    hagridDeviceBindGuidFragment.startScanDevice();
                    return;
                } else {
                    hagridDeviceBindGuidFragment.showBluetoothOpenDialog();
                    return;
                }
            }
            if (i != 7) {
                return;
            }
            synchronized (HagridDeviceBindGuidFragment.LOCK) {
                if (message.obj instanceof HealthDevice) {
                    hagridDeviceBindGuidFragment.mSelectedHealthDevice = (HealthDevice) message.obj;
                    LogUtil.a(HagridDeviceBindGuidFragment.TAG, "scan the target device gotoBinding");
                    hagridDeviceBindGuidFragment.gotoBindingDevicePage();
                }
            }
        }
    }

    /* renamed from: lambda$new$0$com-huawei-health-device-ui-measure-fragment-HagridDeviceBindGuidFragment, reason: not valid java name */
    /* synthetic */ void m141xfeb03eb(AdapterView adapterView, View view, int i, long j) {
        if (j == -1) {
            LogUtil.h(TAG_RELEASE, "device not found !");
            stopScan();
            ViewClickInstrumentation.clickOnListView(adapterView, view, i);
            return;
        }
        if (ResourceManager.e().d(this.mProductId) == null) {
            if (i >= this.mListItems.size()) {
                LogUtil.a(TAG_RELEASE, "DeviceScanningFragment illegal selected item");
                stopScan();
                ViewClickInstrumentation.clickOnListView(adapterView, view, i);
                return;
            }
            this.mSelectedHealthDevice = this.mListItems.get(i);
            this.mSelectedPosition = i;
        } else {
            this.mSelectedPosition = i;
        }
        stopScan();
        buidDeviceWaiting();
        showSecondStep();
        CustomViewDialog customViewDialog = this.mDeviceListDialog;
        if (customViewDialog != null && customViewDialog.isShowing()) {
            this.mDeviceListDialog.dismiss();
        }
        ViewClickInstrumentation.clickOnListView(adapterView, view, i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshDialogDeviceList(HealthDevice healthDevice) {
        if (isDeviceExistInListItems(healthDevice) || this.mHandler == null) {
            LogUtil.a(TAG, "refreshDialogDeviceList, isDeviceExistInListItems is true, return");
            return;
        }
        CustomViewDialog customViewDialog = this.mDeviceListDialog;
        boolean z = customViewDialog != null && customViewDialog.isShowing();
        LogUtil.a(TAG, "refreshDialogDeviceList, mListItems add " + healthDevice.getDeviceName());
        this.mListItems.add(healthDevice);
        if (!z && this.mListItems.size() == 1) {
            this.mSelectedHealthDevice = healthDevice;
            Message obtain = Message.obtain();
            obtain.what = 4;
            this.mHandler.sendMessageDelayed(obtain, 2000L);
            return;
        }
        if (this.mListItems.size() == 2) {
            this.mHandler.removeMessages(4);
            showDeviceListDialog();
        }
        ScanProductListAdapter scanProductListAdapter = this.mAdapter;
        if (scanProductListAdapter != null) {
            scanProductListAdapter.a(this.mListItems);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void gotoBindingDevicePage() {
        stopScan();
        this.mInteractor = gmz.d();
        this.mHandler = new ConnectHandler(this);
        this.mIsJump = false;
        this.mProductInfo = ResourceManager.e().d(this.mProductId);
        this.mUniqueId = this.mSelectedHealthDevice.getUniqueId();
        insertDeviceData();
        startTimer(90000, 1);
        if (this.mIsFromIconnectDialog) {
            return;
        }
        showSecondStep();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void jumpScanFail(HagridDeviceBindGuidFragment hagridDeviceBindGuidFragment) {
        HagridDeviceBindResultFragment.setBindStatus(2);
        Bundle bundle = new Bundle();
        bundle.putString("productId", hagridDeviceBindGuidFragment.mProductId);
        ContentValues contentValues = new ContentValues();
        contentValues.put("uniqueId", this.mUniqueId);
        contentValues.put("productId", this.mProductId);
        bundle.putParcelable("commonDeviceInfo", contentValues);
        Fragment hagridDeviceBindResultFragment = new HagridDeviceBindResultFragment();
        hagridDeviceBindResultFragment.setArguments(bundle);
        hagridDeviceBindGuidFragment.switchFragment(hagridDeviceBindResultFragment);
    }

    private boolean isDeviceExistInListItems(HealthDevice healthDevice) {
        if (healthDevice == null) {
            LogUtil.h(TAG, "isDeviceExistInListItems device is null");
            return false;
        }
        Iterator<HealthDevice> it = this.mListItems.iterator();
        while (it.hasNext()) {
            String address = it.next().getAddress();
            if (address != null && address.equals(healthDevice.getAddress())) {
                return true;
            }
        }
        if (!this.mIsRebind || !TextUtils.equals(healthDevice.getAddress(), this.mUniqueId)) {
            return ceo.d().d(healthDevice.getAddress(), false) != null;
        }
        LogUtil.a(TAG, "the huid has lost need rebind");
        return false;
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mProductId = getArguments().getString("productId");
        ContentValues contentValues = (ContentValues) getArguments().getParcelable("commonDeviceInfo");
        this.mDeviceInfo = contentValues;
        if (contentValues != null) {
            this.mProductId = contentValues.getAsString("productId");
            String asString = this.mDeviceInfo.getAsString("uniqueId");
            this.mUniqueId = asString;
            LogUtil.c(TAG, "onCreate productId ", this.mProductId, " mUniqueId ", asString);
        } else {
            LogUtil.h(TAG, "start bind guide fragment, mDevcieInfo is null");
        }
        this.mProductInfo = ResourceManager.e().d(this.mProductId);
        this.mIsRebind = getArguments().getBoolean("isRebind");
        this.mIsFromIconnectDialog = getArguments().getBoolean("isFromIconnectDialog");
        this.mIsConnectBluetooth = getArguments().getBoolean("connect_bluetooth");
        boolean z = getArguments().getBoolean("isNfcConnect", false);
        this.mIsNfcCommect = z;
        LogUtil.a(TAG, "onCreate getBoolean mIsNfcCommect = ", Boolean.valueOf(z));
        stop();
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        LogUtil.a(TAG, "HagridDeviceBindGuidFragment onCreateView");
        ViewGroup viewGroup2 = super.onCreateView(layoutInflater, viewGroup, bundle) instanceof ViewGroup ? (ViewGroup) super.onCreateView(layoutInflater, viewGroup, bundle) : null;
        View inflate = layoutInflater.inflate(R.layout.device_bind_guide_hygride, viewGroup, false);
        if (viewGroup2 != null) {
            viewGroup2.removeView(this.mCustomTitleBar);
            viewGroup2.addView(inflate);
        }
        initView(inflate);
        this.mIsAllowBackPress = true;
        this.mIsBeingPaired = false;
        this.mHandler = new DeviceScanHandler(this);
        this.mHandlerCallback = new BindCallback();
        if (this.mIsFromIconnectDialog) {
            LogUtil.a(TAG, "onCreateView is from iconnect dialog showBindingView");
            showBindingView();
        }
        if (this.mIsConnectBluetooth) {
            stopScan();
            buidDeviceWaiting();
            showSecondStep();
        }
        return viewGroup2;
    }

    private void initView(View view) {
        if (LanguageUtil.bc(getActivity())) {
            ImageView imageView = (ImageView) view.findViewById(R.id.device_bind_guide_iv_back);
            ImageView imageView2 = (ImageView) view.findViewById(R.id.device_bind_guide_iv_next);
            imageView.setBackgroundResource(R.drawable._2131429720_res_0x7f0b0958);
            imageView2.setBackgroundResource(R.drawable._2131429722_res_0x7f0b095a);
        }
        this.mCustomTitleBar = (CustomTitleBar) view.findViewById(R.id.device_pair_guidance_title);
        this.mCustomTitleBar.setLeftButtonVisibility(0);
        this.mBluetoothPairCancelText = (HealthTextView) view.findViewById(R.id.device_bind_guide_tv_next);
        this.mBluetoothPairConfirmText = (HealthTextView) view.findViewById(R.id.device_bind_guide_tv_back);
        this.mBluetoothPairCancelText.setText(BaseApplication.getContext().getResources().getString(R.string.IDS_device_hygride_button_cancel).toUpperCase(Locale.ENGLISH));
        this.mBluetoothPairConfirmText.setText(BaseApplication.getContext().getResources().getString(R.string.IDS_device_show_next).toUpperCase(Locale.ENGLISH));
        this.mBluetoothPairStepTitle = (HealthTextView) view.findViewById(R.id.id_device_bind_guide_tv_pair_step_title);
        this.mBluetoothPairStepDescription = (HealthTextView) view.findViewById(R.id.id_device_bind_guide_tv_pair_step_description);
        this.mBluetoothPairResultGuideImg = (ImageView) view.findViewById(R.id.pair_result_guide_img);
        this.mBackgroundLayout = (LinearLayout) view.findViewById(R.id.pair_result_guide_img_layout);
        setBindImageBitmap(this.mBluetoothPairResultGuideImg, 0);
        dcz dczVar = this.mProductInfo;
        if (dczVar != null) {
            this.mBluetoothPairStepTitle.setText(dja.c(dczVar.d(), this.mProductId, 0));
            this.mBluetoothPairStepDescription.setText(dja.c(this.mProductInfo.d(), this.mProductId, 1));
        } else {
            LogUtil.h(TAG, "onCreateView mProductInfo is null");
        }
        this.mBackLayout = (LinearLayout) view.findViewById(R.id.back_step_button_layout);
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.next_step_button_layout);
        this.mNextLayout = linearLayout;
        linearLayout.setOnClickListener(this);
        this.mBackLayout.setOnClickListener(this);
    }

    private void showBindingView() {
        this.mBluetoothPairConfirmText.setText(BaseApplication.getContext().getResources().getString(R.string._2130841128_res_0x7f020e28).toUpperCase(Locale.ENGLISH));
        this.mBluetoothPairStepTitle.setText(R.string._2130841387_res_0x7f020f2b);
        this.mBluetoothPairStepDescription.setText(R.string.IDS_device_hygride_pair_tip2);
        setBindImageBitmap(this.mBluetoothPairResultGuideImg, 2);
        dcz dczVar = this.mProductInfo;
        if (dczVar != null) {
            this.mBluetoothPairStepTitle.setText(dja.c(dczVar.d(), this.mProductId, 2));
            this.mBluetoothPairStepDescription.setText(dja.c(this.mProductInfo.d(), this.mProductId, 3));
        } else {
            LogUtil.h(TAG, "showSecondStep mProductInfo is null");
        }
        this.mBackLayout.setVisibility(4);
        this.mNextLayout.setVisibility(4);
    }

    private void showSecondStep() {
        this.mIsBeingPaired = true;
        this.mIsAllowBackPress = false;
        showBindingView();
    }

    private void setBindImageBitmap(ImageView imageView, int i) {
        if (imageView == null) {
            LogUtil.h(TAG, "setBindImageBitmap ImageView is null");
            return;
        }
        refreshBackgroundLayout();
        dcz dczVar = this.mProductInfo;
        if (dczVar != null) {
            imageView.setImageBitmap(dja.VF_(dczVar.d(), this.mProductId, i));
        } else {
            LogUtil.h(TAG, "setBindImageBitmap mProductInfo is null");
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        FragmentActivity activity = getActivity();
        this.mContext = activity;
        if (activity == null) {
            LogUtil.h(TAG_RELEASE, "onActivityCreated mContext is null");
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.back_step_button_layout) {
            this.mainActivity.onBackPressed();
            Handler handler = this.mHandler;
            if (handler != null) {
                handler.removeCallbacksAndMessages(null);
                this.mHandler = null;
            }
        } else if (id == R.id.next_step_button_layout) {
            Handler handler2 = this.mHandler;
            if (handler2 != null) {
                handler2.removeMessages(4);
            }
            stopScan();
            if (isBluetoothOpen()) {
                showDeviceListDialog();
                startScanDevice();
            } else {
                showBluetoothOpenDialog();
            }
        } else {
            LogUtil.h(TAG_RELEASE, "unknow click item");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showBluetoothOpenDialog() {
        LogUtil.a(TAG, "enter showBluetoothOpenDialog");
        if (this.mDialog == null) {
            NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(this.mainActivity);
            builder.e(R.string.IDS_device_bluetooth_open_request);
            builder.czC_(R.string.IDS_device_ui_dialog_yes, new View.OnClickListener() { // from class: com.huawei.health.device.ui.measure.fragment.HagridDeviceBindGuidFragment$$ExternalSyntheticLambda7
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    HagridDeviceBindGuidFragment.this.m144x9269784f(view);
                }
            });
            builder.czz_(R.string.IDS_device_ui_dialog_no, new View.OnClickListener() { // from class: com.huawei.health.device.ui.measure.fragment.HagridDeviceBindGuidFragment$$ExternalSyntheticLambda8
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    HagridDeviceBindGuidFragment.this.m145xc617a310(view);
                }
            });
            NoTitleCustomAlertDialog e = builder.e();
            this.mDialog = e;
            e.setCancelable(false);
        }
        if (this.mDialog.isShowing()) {
            return;
        }
        this.mDialog.show();
    }

    /* renamed from: lambda$showBluetoothOpenDialog$1$com-huawei-health-device-ui-measure-fragment-HagridDeviceBindGuidFragment, reason: not valid java name */
    /* synthetic */ void m144x9269784f(View view) {
        if (Build.VERSION.SDK_INT > 30 && PermissionUtil.e(this.mainActivity, PermissionUtil.PermissionType.SCAN) != PermissionUtil.PermissionResult.GRANTED) {
            PermissionUtil.b(this.mainActivity, PermissionUtil.PermissionType.SCAN, new CustomPermissionAction(this.mainActivity) { // from class: com.huawei.health.device.ui.measure.fragment.HagridDeviceBindGuidFragment.3
                @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
                public void onGranted() {
                    HagridDeviceBindGuidFragment.this.openBluetoothsatrtScan();
                }

                @Override // com.huawei.ui.commonui.utils.CustomPermissionAction, com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
                public void onDenied(String str) {
                    nrh.b(HagridDeviceBindGuidFragment.this.mainActivity, R.string._2130846464_res_0x7f022300);
                }

                @Override // com.huawei.ui.commonui.utils.CustomPermissionAction, com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
                public void onForeverDenied(PermissionUtil.PermissionType permissionType) {
                    nsn.e(HagridDeviceBindGuidFragment.this.mainActivity, PermissionUtil.PermissionType.SCAN);
                }
            });
        } else {
            openBluetoothsatrtScan();
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    /* renamed from: lambda$showBluetoothOpenDialog$2$com-huawei-health-device-ui-measure-fragment-HagridDeviceBindGuidFragment, reason: not valid java name */
    /* synthetic */ void m145xc617a310(View view) {
        this.mDialog = null;
        this.mainActivity.onBackPressed();
        ViewClickInstrumentation.clickOnView(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void openBluetoothsatrtScan() {
        LogUtil.a(TAG, "showBluetoothOpenDialog isEnable ", Boolean.valueOf(BluetoothAdapter.getDefaultAdapter().enable()));
        this.mDialog.dismiss();
        this.mDialog = null;
        startScanDevice();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isBluetoothOpen() {
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (defaultAdapter != null) {
            if (defaultAdapter.isEnabled()) {
                LogUtil.a(TAG, "bluetooth is open");
                return true;
            }
            LogUtil.h(TAG, "bluetooth is close");
            return false;
        }
        LogUtil.h(TAG_RELEASE, "bluetooth is null");
        return false;
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        LogUtil.a(TAG, "HagridDeviceBindGuidFragment onStart");
        super.onStart();
        if (this.mIsFromIconnectDialog) {
            LogUtil.a(TAG, "onStart, is from iconnect dialog");
            bindTargetDevice();
            return;
        }
        if (this.mIsBeingPaired || !koq.b(this.mListItems)) {
            return;
        }
        if (isBluetoothOpen()) {
            startScanDevice();
            return;
        }
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.removeMessages(6);
            this.mHandler.sendEmptyMessageDelayed(6, 2000L);
        }
    }

    private void setHealthLayout(View view) {
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.health_status_layout);
        HealthTextView healthTextView = (HealthTextView) view.findViewById(R.id.health_tip);
        linearLayout.setVisibility(0);
        if (!Utils.o()) {
            healthTextView.setText(R.string._2130841143_res_0x7f020e37);
        } else {
            healthTextView.setText(R.string._2130841238_res_0x7f020e96);
        }
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment
    public boolean onBackPressed() {
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.removeMessages(6);
        }
        if (!this.mIsAllowBackPress) {
            return false;
        }
        Handler handler2 = this.mHandler;
        if (handler2 != null) {
            handler2.removeCallbacksAndMessages(null);
            this.mHandler = null;
        }
        stopScan();
        stop();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stop() {
        if (this.mProductId != null) {
            Bundle bundle = new Bundle();
            bundle.putInt("type", -6);
            bundle.putString("productId", this.mProductId);
            HealthDevice c = cjx.e().c(this.mUniqueId);
            if (c != null) {
                cgt.e().prepare(c, null, bundle);
            } else {
                cgt.e().prepare(null, null, bundle);
            }
        }
        cjx.e().e(this.mProductId, this.mUniqueId, -6);
    }

    private void startBinding() {
        String str = this.mProductId;
        if (str != null) {
            LogUtil.c(TAG, "DeviceScanningFragment startBinding mProductId is ", str);
            dcz d = ResourceManager.e().d(this.mProductId);
            if (cpa.ar(this.mProductId)) {
                if (d != null) {
                    cjx.e().b(ScanMode.BLE, d.aa(), this.mHandlerCallback);
                    return;
                }
                LogUtil.a(TAG, "DeviceScanningFragment productInfo is null.");
                ArrayList arrayList = new ArrayList(0);
                bjf.a aVar = new bjf.a();
                aVar.e(2);
                aVar.a("moredevice");
                arrayList.add(aVar.a());
                cjx.e().b(ScanMode.BLE, arrayList, this.mHandlerCallback);
                return;
            }
            if (d != null) {
                LogUtil.a(TAG, "DeviceScanningFragment productInfo is not null");
                this.mDeviceScanManager.d(d.x(), d.w(), this.mHandlerCallback);
                return;
            }
            LogUtil.a(TAG, "DeviceScanningFragment for more heart rate device");
            cev.b bVar = new cev.b();
            bVar.a(1);
            bVar.c(CommonUtil.h(HealthZonePushReceiver.SLEEP_SCORE_NOTIFY), TimeUnit.SECONDS);
            this.mDeviceScanManager.d(bVar.c(), ScanFilter.b("moredevice", this.mScanKind, ScanFilter.MatchRule.FRONT), this.mHandlerCallback);
        }
    }

    public void stopScan() {
        cwz cwzVar = this.mDeviceScanManager;
        if (cwzVar != null) {
            cwzVar.c();
        }
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        LogUtil.a(TAG, "DeviceScanningFragment onDestroy");
        super.onDestroy();
        stopScan();
        synchronized (LOCK) {
            if (this.mAdapter != null) {
                this.mAdapter = null;
            }
        }
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            this.mHandler = null;
        }
        cgt.e().b(this.mBindCallback);
        if (this.mHandlerCallback != null) {
            this.mHandlerCallback = null;
        }
    }

    class BindCallback implements IDeviceEventHandler, DeviceScanCallback {
        private boolean isHasDeviceByScan;

        private BindCallback() {
            this.isHasDeviceByScan = false;
        }

        @Override // com.huawei.health.device.callback.IDeviceEventHandler
        public void onDeviceFound(HealthDevice healthDevice) {
            if (healthDevice == null) {
                LogUtil.h(HagridDeviceBindGuidFragment.TAG, "BindCallback onDeviceFound device is null");
                return;
            }
            String deviceName = healthDevice.getDeviceName();
            LogUtil.a(HagridDeviceBindGuidFragment.TAG, "Callback, onDeviceFound: ", deviceName);
            if (TextUtils.isEmpty(deviceName)) {
                LogUtil.h(HagridDeviceBindGuidFragment.TAG, "BindCallback onDeviceFound deviceName is null");
                return;
            }
            Message obtain = Message.obtain();
            String address = healthDevice.getAddress();
            if (HagridDeviceBindGuidFragment.this.mIsFromIconnectDialog && !TextUtils.isEmpty(address) && !TextUtils.isEmpty(HagridDeviceBindGuidFragment.this.mUniqueId) && TextUtils.equals(address, HagridDeviceBindGuidFragment.this.mUniqueId)) {
                LogUtil.a(HagridDeviceBindGuidFragment.TAG, "is from iconnect scan");
                obtain.what = 7;
            } else {
                obtain.what = 0;
            }
            obtain.obj = healthDevice;
            if (HagridDeviceBindGuidFragment.this.mHandler != null) {
                HagridDeviceBindGuidFragment.this.mHandler.sendMessage(obtain);
            }
        }

        @Override // com.huawei.health.device.callback.IDeviceEventHandler
        public void onScanFailed(int i) {
            LogUtil.c(HagridDeviceBindGuidFragment.TAG, "DeviceScanningFragment onScanFailed");
            if (i != 1) {
                if (i == 3) {
                    HagridDeviceBindGuidFragment.this.foundDeviceOfMineNotExist();
                    return;
                } else {
                    LogUtil.h(HagridDeviceBindGuidFragment.TAG, "DeviceScanningFragment, unknown code = ", Integer.valueOf(i));
                    return;
                }
            }
            if (koq.b(HagridDeviceBindGuidFragment.this.mListItems)) {
                HagridDeviceBindGuidFragment.this.foundDeviceOfMineNotExist();
                return;
            }
            Message obtain = Message.obtain();
            obtain.what = 5;
            if (HagridDeviceBindGuidFragment.this.mHandler != null) {
                HagridDeviceBindGuidFragment.this.mHandler.sendMessage(obtain);
            }
        }

        @Override // com.huawei.health.device.callback.IDeviceEventHandler
        public void onStateChanged(int i) {
            if (i == 1001) {
                boolean e = dfe.a().e();
                boolean j = dfe.a().j(HagridDeviceBindGuidFragment.this.mProductId);
                if (e && j) {
                    HealthDevice b = dfe.a().b(HagridDeviceBindGuidFragment.this.mProductId);
                    Message obtain = Message.obtain();
                    obtain.what = 0;
                    obtain.obj = b;
                    if (HagridDeviceBindGuidFragment.this.mHandler != null) {
                        HagridDeviceBindGuidFragment.this.mHandler.sendMessage(obtain);
                    }
                }
            }
        }

        @Override // com.huawei.devicesdk.callback.DeviceScanCallback
        public void scanResult(UniteDevice uniteDevice, byte[] bArr, String str, int i) {
            LogUtil.a(HagridDeviceBindGuidFragment.TAG, "scanResult errorCode: ", Integer.valueOf(i));
            cxh d = cxh.d(uniteDevice);
            if (i != 20 || d == null) {
                if (i == 22) {
                    if (HagridDeviceBindGuidFragment.this.mListItems != null && HagridDeviceBindGuidFragment.this.mListItems.size() == 0 && !this.isHasDeviceByScan) {
                        LogUtil.h(HagridDeviceBindGuidFragment.TAG, "scanResult: scan finish and list has on device, do scan time out");
                        HagridDeviceBindGuidFragment.this.foundDeviceOfMineNotExist();
                        return;
                    } else {
                        LogUtil.c(HagridDeviceBindGuidFragment.TAG, "scanResult: scan finish and list has device, do nothing");
                        return;
                    }
                }
                LogUtil.h(HagridDeviceBindGuidFragment.TAG, "scanResult: scan faild, do scan time out");
                HagridDeviceBindGuidFragment.this.foundDeviceOfMineNotExist();
                return;
            }
            String deviceName = d.getDeviceName();
            LogUtil.a(HagridDeviceBindGuidFragment.TAG, "scanResult deviceName: ", deviceName);
            if (TextUtils.isEmpty(deviceName)) {
                return;
            }
            if (!this.isHasDeviceByScan) {
                this.isHasDeviceByScan = true;
            }
            Message obtain = Message.obtain();
            obtain.what = 0;
            obtain.obj = d;
            if (HagridDeviceBindGuidFragment.this.mHandler != null) {
                HagridDeviceBindGuidFragment.this.mHandler.sendMessage(obtain);
            }
        }
    }

    private void bindTargetDevice() {
        LogUtil.a(TAG, "is from iconnect but code = 5");
        if (!TextUtils.isEmpty(this.mUniqueId)) {
            LogUtil.a(TAG, "is from iconnect but code = 5 mUniqueId = ", CommonUtil.l(this.mUniqueId));
            BluetoothDevice remoteDevice = BluetoothAdapter.getDefaultAdapter().getRemoteDevice(this.mUniqueId);
            if (remoteDevice == null) {
                LogUtil.a(TAG, "bindTargetDevice but bluetoothDevice = null or fromBluetoothDevice = null");
                startScanDevice();
                return;
            }
            cxh Ra_ = cxh.Ra_(remoteDevice);
            LogUtil.a(TAG, "bindTargetDevice name = ", Ra_.getDeviceName(), ",uniqueId = ", CommonUtil.l(Ra_.getUniqueId()));
            Message obtain = Message.obtain();
            obtain.what = 7;
            obtain.obj = Ra_;
            Handler handler = this.mHandler;
            if (handler != null) {
                handler.sendMessage(obtain);
                return;
            }
            return;
        }
        LogUtil.a(TAG, "bindTargetDevice but uniqueId = null");
        startScanDevice();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void passDevicePair() {
        LogUtil.a(TAG, "DeviceBindWaitingFragment PAIRING_PASSED");
        HashMap hashMap = new HashMap(16);
        if (Utils.o()) {
            String b = dis.b(this.mSelectedHealthDevice.getAddress());
            if (b != null && !TextUtils.isEmpty(b) && b.length() > 24) {
                hashMap.put("macAddress for bi:::", b.substring(0, 24));
                LogUtil.a(TAG, "macAddress fuzzyData:", CommonUtil.l(b.substring(0, 24)));
            }
        } else {
            hashMap.put("macAddress", dis.b(this.mSelectedHealthDevice.getAddress()));
            LogUtil.a(TAG, "macAddress fuzzyData:", CommonUtil.l(this.mSelectedHealthDevice.getAddress()));
        }
        hashMap.put(PluginPayAdapter.KEY_DEVICE_INFO_NAME, this.mProductInfo.n().b());
        hashMap.put(DeviceCategoryFragment.DEVICE_TYPE, this.mProductInfo.l().name());
        dcz d = ResourceManager.e().d(this.mProductId);
        if (d != null) {
            hashMap.put("deviceId", d.g());
        }
        String e = dij.e(this.mProductId);
        hashMap.put("prodId", e);
        cpz.a(hashMap);
        LogUtil.a(TAG, "deviceName for bi:", this.mProductInfo.n().b(), "device_type for bi :::", this.mProductInfo.l().name(), "prodId for bi:", e);
        hashMap.put("productId", this.mProductId);
        bzw.e().setEvent(BaseApplication.getContext(), String.valueOf(1500), hashMap);
        if (getDataStatus() && Utils.i() && (!Utils.o() || !isShowAuthorizeDialog())) {
            showAuthorizeAlertDialog();
        } else {
            jumpToActivity();
        }
    }

    private void jumpToActivity() {
        dij.e(this.mProductInfo.l());
        cjx.e().b();
        bindWeightDevice();
    }

    private void bindWeightDevice() {
        HealthDevice healthDevice = this.mSelectedHealthDevice;
        if (healthDevice instanceof MeasurableDevice) {
            MeasurableDevice measurableDevice = (MeasurableDevice) healthDevice;
            measurableDevice.setMeasureKitUuid(this.mProductInfo.s());
            Bundle bundle = new Bundle();
            bundle.putInt("type", -2);
            bundle.putString("productId", this.mProductId);
            cjx.e().Gs_(this.mProductId, this.mUniqueId, this.mBindCallback, bundle, measurableDevice);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processCallbackResult(int i, Bundle bundle) {
        if (bundle == null) {
            LogUtil.h(TAG_RELEASE, "processCallbackResult bundle is null");
            return;
        }
        MeasurableDevice measurableDevice = (MeasurableDevice) this.mSelectedHealthDevice;
        if (i == -7) {
            getHuid(bundle);
            processBindedSameWiFi(bundle);
            return;
        }
        if (i == -8) {
            this.mIsHasManaer = false;
            if (!this.mIsHasBondedProduct || this.mHandler == null) {
                return;
            }
            LogUtil.a(TAG, "try to bond different scales of same product");
            this.mHandler.sendEmptyMessage(3);
            return;
        }
        if (i == -12) {
            if (bundle.getInt("ret") != 0) {
                LogUtil.h(TAG_RELEASE, " send config info failed");
                return;
            } else {
                LogUtil.a(TAG, " send config info success.");
                return;
            }
        }
        if (i == -13) {
            if (bundle.getInt("ret") != 0) {
                LogUtil.h(TAG_RELEASE, " set manager info failed");
                return;
            } else {
                LogUtil.a(TAG, " set manager info success.");
                return;
            }
        }
        if (i == -16) {
            this.mDeviceSn = bundle.getString(HealthEngineRequestManager.PARAMS_DEVICE_SN);
            LogUtil.a(TAG, " get device version and sn.");
            return;
        }
        if (i == -9) {
            getDeviceSsid(bundle, measurableDevice);
            return;
        }
        if (i == -2) {
            processBindType(bundle);
            return;
        }
        if (i == -14) {
            saveDevice(measurableDevice, "");
            if (this.mDeviceInfo == null) {
                this.mDeviceInfo = new ContentValues();
            }
            this.mDeviceInfo.put("uniqueId", measurableDevice.getUniqueId());
            this.mDeviceInfo.put("productId", this.mProductId);
            gotoBluetoothPairSuccessPage(false);
            cancelTimer();
            return;
        }
        LogUtil.h(TAG, " status others");
    }

    private void getHuid(Bundle bundle) {
        try {
            this.mMainHuid = bundle.getByteArray("huid");
        } catch (Exception unused) {
            LogUtil.b(TAG, "huid getByteArray exception");
        }
        byte[] bArr = this.mMainHuid;
        boolean z = bArr == null || bArr.length == 0;
        this.mIsHasManaer = z;
        LogUtil.a(TAG, "mIsHasManager = ", Boolean.valueOf(z));
    }

    private void getDeviceSsid(Bundle bundle, MeasurableDevice measurableDevice) {
        this.mSsid = bundle.getString("deviceSsid");
        LogUtil.a(TAG, " save device info, deviceSn:", cpw.d(this.mDeviceSn));
        cpa.d(this.mDeviceSn, measurableDevice.getUniqueId());
        cpa.a(measurableDevice.getUniqueId(), this.mDeviceSn);
        measurableDevice.setSerialNumber(this.mDeviceSn);
        checkAndUpdateLocalDevice(measurableDevice, this.mDeviceSn);
        if (this.mDeviceInfo == null) {
            this.mDeviceInfo = new ContentValues();
        }
        String uniqueId = measurableDevice.getUniqueId();
        this.mUniqueId = uniqueId;
        this.mDeviceInfo.put("uniqueId", uniqueId);
        this.mDeviceInfo.put("productId", this.mProductId);
        gotoBluetoothPairSuccessPage(this.mIsHasManaer);
        cancelTimer();
    }

    private void checkAndUpdateLocalDevice(MeasurableDevice measurableDevice, final String str) {
        boolean z = false;
        MeasurableDevice c = ceo.d().c(str, false);
        if (c == null) {
            saveDevice(measurableDevice, str);
            return;
        }
        if (c instanceof ctk) {
            ctk ctkVar = (ctk) c;
            if (!TextUtils.equals(this.mDeviceIdFromDevice, ctkVar.d())) {
                LogUtil.h(TAG, "device rebind, unbind local wifi device");
                new coy().d(ctkVar.d(), new ICloudOperationResult() { // from class: com.huawei.health.device.ui.measure.fragment.HagridDeviceBindGuidFragment$$ExternalSyntheticLambda0
                    @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
                    public final void operationResult(Object obj, String str2, boolean z2) {
                        HagridDeviceBindGuidFragment.lambda$checkAndUpdateLocalDevice$3(str, (CloudCommonReponse) obj, str2, z2);
                    }
                });
                z = true;
            }
        }
        upDateDeviceUniqueId(measurableDevice, z);
    }

    static /* synthetic */ void lambda$checkAndUpdateLocalDevice$3(String str, CloudCommonReponse cloudCommonReponse, String str2, boolean z) {
        LogUtil.a(TAG, "operationResult isSuccess ", Boolean.valueOf(z));
        ctq.b(str);
    }

    private void processBindedSameWiFi(Bundle bundle) {
        if (bundle == null) {
            LogUtil.h(TAG, "processBindedSameWiFi bundle is null");
        } else if (bundle.getString("cloudDeviceID") != null) {
            this.mDeviceIdFromDevice = bundle.getString("cloudDeviceID");
        }
    }

    private void upDateDeviceUniqueId(MeasurableDevice measurableDevice, boolean z) {
        Handler handler;
        ContentValues contentValues = new ContentValues();
        contentValues.put("uniqueId", measurableDevice.getUniqueId());
        if (z) {
            contentValues.put(Wpt.MODE, (Integer) 1);
        }
        int Eg_ = ceo.d().Eg_(contentValues, "sn=?", new String[]{measurableDevice.getSerialNumber()});
        LogUtil.a(TAG, "upDateDeviceUniqueId affected rows:", Integer.valueOf(Eg_));
        if (Eg_ > 0 || (handler = this.mHandler) == null) {
            return;
        }
        handler.sendEmptyMessage(2);
    }

    private void processBindType(Bundle bundle) {
        if (bundle == null) {
            LogUtil.h(TAG_RELEASE, "processBindType bundle is null");
            return;
        }
        byte b = bundle.getByte("ret");
        if (b == 2) {
            LogUtil.b(TAG_RELEASE, "binding failed.");
            cancelTimer();
            Handler handler = this.mHandler;
            if (handler != null) {
                handler.sendEmptyMessage(1);
                return;
            }
            return;
        }
        if (b == 1) {
            if (cpa.ae(this.mProductId)) {
                EventBus.d(new EventBus.b("reset_wifi"));
            }
            LogUtil.a(TAG, "processBindType ret = 1 set hasManager = true");
            this.mIsHasManaer = true;
        } else {
            LogUtil.a(TAG, "processBindType default");
        }
        this.mWeight = bundle.getFloat("weight_data", -1.0f);
        if (bundle.getSerializable("resis_data") instanceof ckm) {
            this.mWeightAndFatRateData = (ckm) bundle.getSerializable("resis_data");
        }
        if (!cpy.a(this.mProductId, this.mWeight)) {
            cancelTimer();
            Handler handler2 = this.mHandler;
            if (handler2 != null) {
                handler2.sendEmptyMessage(2);
                return;
            }
            return;
        }
        cancelTimer();
        startTimer(30000, 3);
        LogUtil.a(TAG, "bind is completed, ret is ", Byte.valueOf(b));
    }

    private void saveDevice(MeasurableDevice measurableDevice, String str) {
        ceo.d().b(this.mProductId, this.mProductInfo.s(), measurableDevice, new IDeviceEventHandler() { // from class: com.huawei.health.device.ui.measure.fragment.HagridDeviceBindGuidFragment.4
            @Override // com.huawei.health.device.callback.IDeviceEventHandler
            public void onDeviceFound(HealthDevice healthDevice) {
            }

            @Override // com.huawei.health.device.callback.IDeviceEventHandler
            public void onScanFailed(int i) {
            }

            @Override // com.huawei.health.device.callback.IDeviceEventHandler
            public void onStateChanged(int i) {
            }
        }, str);
        LogUtil.a(TAG, "band sendWeightDetailSyncSuccessBroadcast");
        cpa.c(this.mContext, this.mProductId, this.mUniqueId);
    }

    private void startTimer(int i, int i2) {
        synchronized (this) {
            if (this.mConnectTimer == null) {
                this.mConnectTimer = new Timer(TAG);
                LogUtil.a(TAG, "Start the timer connected devices");
                this.mConnectTimer.schedule(new MyTimerTask(this, i2), i);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cancelTimer() {
        synchronized (this) {
            Timer timer = this.mConnectTimer;
            if (timer != null) {
                timer.cancel();
                this.mConnectTimer = null;
                LogUtil.a(TAG, "Cancel the timer connected devices");
            } else {
                LogUtil.a(TAG_RELEASE, "Connection timer has been canceled");
            }
        }
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        if (this.isGotoUserInfoActivity) {
            Bundle bundle = new Bundle();
            bundle.putString("productId", this.mProductId);
            bundle.putParcelable("commonDeviceInfo", this.mDeviceInfo);
            bundle.putString("goto", "devicebind");
            bundle.putBoolean("isNfcConnect", this.mIsNfcCommect);
            LogUtil.a(TAG, "onResume putBoolean mIsNfcCommect = ", Boolean.valueOf(this.mIsNfcCommect));
            HagridDeviceManagerFragment hagridDeviceManagerFragment = new HagridDeviceManagerFragment();
            hagridDeviceManagerFragment.setArguments(bundle);
            switchFragment(hagridDeviceManagerFragment);
        }
        super.onResume();
    }

    private boolean isShowAuthorizeDialog() {
        return "true".equals(SharedPreferenceManager.b(getActivity(), Integer.toString(10025), "health_manual_record_sync_agree"));
    }

    private void showAuthorizeAlertDialog() {
        CustomAlertDialog customAlertDialog = this.mCustomAlertDialog;
        if (customAlertDialog != null && customAlertDialog.isShowing()) {
            LogUtil.a(TAG, "CustomAlertDialog, is showing.");
            return;
        }
        UserProfileMgrApi userProfileMgrApi = (UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class);
        gnb popOutWindowInfo = userProfileMgrApi.getPopOutWindowInfo(getActivity(), "privacy_health_data_");
        int a2 = popOutWindowInfo.a();
        long c = popOutWindowInfo.c();
        long currentTimeMillis = System.currentTimeMillis();
        if (a2 >= 3 || currentTimeMillis - c <= 86400000) {
            jumpToActivity();
            return;
        }
        userProfileMgrApi.setPopOutWindowInfo(getActivity(), "privacy_health_data_");
        View inflate = getActivity().getLayoutInflater().inflate(R.layout.commonui_dialog_health_tip, (ViewGroup) null);
        ((LinearLayout) inflate.findViewById(R.id.remind_layout)).setVisibility(8);
        ((HealthTextView) inflate.findViewById(R.id.hw_health_service_item_one)).setText(R.string._2130841190_res_0x7f020e66);
        setHealthLayout(inflate);
        CustomAlertDialog.Builder builder = new CustomAlertDialog.Builder(getActivity());
        builder.cyp_(inflate);
        builder.e(R.string.IDS_service_area_notice_title);
        builder.cyo_(R.string._2130841555_res_0x7f020fd3, new DialogInterface.OnClickListener() { // from class: com.huawei.health.device.ui.measure.fragment.HagridDeviceBindGuidFragment$$ExternalSyntheticLambda5
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                HagridDeviceBindGuidFragment.this.m142xb19f3a1(dialogInterface, i);
            }
        });
        builder.cyn_(R.string._2130841129_res_0x7f020e29, new DialogInterface.OnClickListener() { // from class: com.huawei.health.device.ui.measure.fragment.HagridDeviceBindGuidFragment$$ExternalSyntheticLambda6
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                HagridDeviceBindGuidFragment.this.m143x3ec81e62(dialogInterface, i);
            }
        });
        CustomAlertDialog c2 = builder.c();
        this.mCustomAlertDialog = c2;
        c2.setCancelable(false);
        this.mCustomAlertDialog.show();
    }

    /* renamed from: lambda$showAuthorizeAlertDialog$4$com-huawei-health-device-ui-measure-fragment-HagridDeviceBindGuidFragment, reason: not valid java name */
    /* synthetic */ void m142xb19f3a1(DialogInterface dialogInterface, int i) {
        if (!this.mIsHealthDataStatus) {
            this.mInteractor.c(7, true, "DeviceBindWaitingFragment", (IBaseResponseCallback) null);
        }
        jumpToActivity();
        ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
    }

    /* renamed from: lambda$showAuthorizeAlertDialog$5$com-huawei-health-device-ui-measure-fragment-HagridDeviceBindGuidFragment, reason: not valid java name */
    /* synthetic */ void m143x3ec81e62(DialogInterface dialogInterface, int i) {
        jumpToActivity();
        ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
    }

    static class MyTimerTask extends TimerTask {
        private int mType;
        private WeakReference<HagridDeviceBindGuidFragment> mWeakReference;

        MyTimerTask(HagridDeviceBindGuidFragment hagridDeviceBindGuidFragment, int i) {
            this.mWeakReference = new WeakReference<>(hagridDeviceBindGuidFragment);
            this.mType = i;
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            HagridDeviceBindGuidFragment hagridDeviceBindGuidFragment = this.mWeakReference.get();
            if (hagridDeviceBindGuidFragment == null) {
                return;
            }
            LogUtil.h(HagridDeviceBindGuidFragment.TAG_RELEASE, "connect timeout...");
            hagridDeviceBindGuidFragment.mIsBingTimeout = true;
            if (hagridDeviceBindGuidFragment.mProductInfo != null && hagridDeviceBindGuidFragment.mProductInfo.x().c() == 2) {
                cjx.e().b();
            }
            if (hagridDeviceBindGuidFragment.mBleDeviceHelper != null) {
                cpp.a().unregisterReceiver(hagridDeviceBindGuidFragment.mBleDeviceHelper);
            }
            Message obtain = Message.obtain();
            obtain.what = 1;
            obtain.obj = Integer.valueOf(this.mType);
            if (hagridDeviceBindGuidFragment.mHandler != null) {
                hagridDeviceBindGuidFragment.mHandler.sendMessage(obtain);
            }
        }
    }

    private boolean getDataStatus() {
        if ("true".equals(this.mInteractor.c(7))) {
            this.mIsHealthDataStatus = true;
        } else {
            this.mIsHealthDataStatus = false;
        }
        return !this.mIsHealthDataStatus;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showFailScanDialog() {
        String format = String.format(Locale.ENGLISH, getResources().getString(R.string.IDS_device_common_err_bind_failed_prompts), UnitUtil.e(1.0d, 1, 0), UnitUtil.e(1.0d, 1, 0), UnitUtil.e(2.0d, 1, 0));
        CustomTextAlertDialog.Builder builder = new CustomTextAlertDialog.Builder(getContext());
        builder.e(format).cyU_(R.string._2130841131_res_0x7f020e2b, new View.OnClickListener() { // from class: com.huawei.health.device.ui.measure.fragment.HagridDeviceBindGuidFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HagridDeviceBindGuidFragment.this.m146x7512ca6b(view);
            }
        });
        builder.a().show();
    }

    /* renamed from: lambda$showFailScanDialog$6$com-huawei-health-device-ui-measure-fragment-HagridDeviceBindGuidFragment, reason: not valid java name */
    /* synthetic */ void m146x7512ca6b(View view) {
        DeviceMainActivity deviceMainActivity;
        if ((getActivity() instanceof DeviceMainActivity) && (deviceMainActivity = (DeviceMainActivity) getActivity()) != null) {
            deviceMainActivity.b(ProductIntroductionFragment.class);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void showDeviceListDialog() {
        if (this.mDeviceListDialog == null) {
            initDeviceListDialog();
        }
        CustomViewDialog customViewDialog = this.mDeviceListDialog;
        if (customViewDialog == null || !customViewDialog.isShowing()) {
            this.mLoading.setVisibility(0);
            HealthTextView healthTextView = this.mListTitleTv;
            if (healthTextView != null) {
                healthTextView.setText(R.string.IDS_device_mgr_device_scaning_title);
            }
            this.mIsAllowBackPress = false;
            CustomViewDialog customViewDialog2 = this.mDeviceListDialog;
            if (customViewDialog2 != null) {
                customViewDialog2.show();
            }
        }
    }

    private void initDeviceListDialog() {
        Object systemService = this.mContext.getSystemService("layout_inflater");
        View inflate = systemService instanceof LayoutInflater ? ((LayoutInflater) systemService).inflate(R.layout.device_search_list_dialog, (ViewGroup) null) : null;
        Context context = getContext();
        if (context == null) {
            LogUtil.h(TAG_RELEASE, "initDeviceListDialog context is null");
            return;
        }
        CustomViewDialog.Builder builder = new CustomViewDialog.Builder(context);
        if (inflate != null) {
            this.mListView = (ListView) inflate.findViewById(R.id.device_list);
            HealthTextView healthTextView = (HealthTextView) inflate.findViewById(R.id.device_search_listview_title);
            this.mListTitleTv = healthTextView;
            healthTextView.setText(R.string.IDS_device_mgr_device_scaning_title);
            this.mAdapter = new ScanProductListAdapter(this.mListItems, this.mContext, this.mProductId);
            HealthProgressBar healthProgressBar = (HealthProgressBar) inflate.findViewById(R.id.dialog_listview_loading);
            this.mLoading = healthProgressBar;
            healthProgressBar.setLayerType(1, null);
            this.mLoading.setVisibility(0);
            this.mListView.setAdapter((ListAdapter) this.mAdapter);
            addFootView();
        }
        this.mListView.setOnItemClickListener(this.mOnItemClickListener);
        builder.czc_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: com.huawei.health.device.ui.measure.fragment.HagridDeviceBindGuidFragment$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HagridDeviceBindGuidFragment.this.m140xa6afb758(view);
            }
        });
        builder.czh_(inflate, 0, 0);
        builder.c(false);
        CustomViewDialog e = builder.e();
        this.mDeviceListDialog = e;
        e.setCancelable(false);
    }

    /* renamed from: lambda$initDeviceListDialog$7$com-huawei-health-device-ui-measure-fragment-HagridDeviceBindGuidFragment, reason: not valid java name */
    /* synthetic */ void m140xa6afb758(View view) {
        stopScan();
        this.mIsAllowBackPress = true;
        ViewClickInstrumentation.clickOnView(view);
    }

    private void buidDeviceWaiting() {
        this.mInteractor = gmz.d();
        this.mHandler = new ConnectHandler(this);
        this.mIsJump = false;
        if (this.mSelectedPosition < this.mListItems.size()) {
            this.mSelectedHealthDevice = this.mListItems.get(this.mSelectedPosition);
            this.mProductInfo = ResourceManager.e().d(this.mProductId);
            this.mUniqueId = this.mSelectedHealthDevice.getUniqueId();
            dcz dczVar = this.mProductInfo;
            if (dczVar != null && dczVar.x() != null) {
                LogUtil.a(TAG, "DeviceBindWaitingFragment pair is ", this.mProductInfo.x().a());
            } else {
                LogUtil.h(TAG, "buidDeviceWaiting mProductInfo is null");
            }
            insertDeviceData();
        }
        startTimer(90000, 1);
    }

    private void insertDeviceData() {
        dcz dczVar = this.mProductInfo;
        if (dczVar == null) {
            LogUtil.h(TAG, "insertDeviceData mProductInfo is null");
        } else {
            if (dczVar.x().a().equals("yes")) {
                return;
            }
            cjx.e().b(this.mProductId, this.mProductInfo.s(), this.mSelectedHealthDevice, this.mBindingStatusCallback);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startScanDevice() {
        LogUtil.a(TAG, "startScanDevice");
        long elapsedRealtime = SystemClock.elapsedRealtime();
        long j = elapsedRealtime - this.mLastScanTime;
        if (j < 6000) {
            Handler handler = this.mHandler;
            if (handler != null) {
                handler.removeMessages(6);
                this.mHandler.sendEmptyMessageDelayed(6, 6000 - j);
                return;
            }
            return;
        }
        this.mLastScanTime = elapsedRealtime;
        synchronized (this.mListItemLock) {
            LogUtil.a(TAG, "startScanDevice, reset mListItems");
            this.mListItems.clear();
            ScanProductListAdapter scanProductListAdapter = this.mAdapter;
            if (scanProductListAdapter != null) {
                scanProductListAdapter.a(this.mListItems);
            }
        }
        this.mDeviceScanManager = new cwz();
        startBinding();
    }

    private void addFootView() {
        View inflate = ((LayoutInflater) this.mContext.getSystemService("layout_inflater")).inflate(R.layout.device_search_list_not_found_my_device_button, (ViewGroup) null);
        inflate.findViewById(R.id.not_found_device_tip).setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.device.ui.measure.fragment.HagridDeviceBindGuidFragment$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HagridDeviceBindGuidFragment.this.m139x1893df47(view);
            }
        });
        this.mListView.addFooterView(inflate);
    }

    /* renamed from: lambda$addFootView$8$com-huawei-health-device-ui-measure-fragment-HagridDeviceBindGuidFragment, reason: not valid java name */
    /* synthetic */ void m139x1893df47(View view) {
        foundDeviceOfMineNotExist();
        ViewClickInstrumentation.clickOnView(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void foundDeviceOfMineNotExist() {
        CustomViewDialog customViewDialog = this.mDeviceListDialog;
        if (customViewDialog != null && customViewDialog.isShowing()) {
            this.mDeviceListDialog.dismiss();
        }
        stopScan();
        Message obtain = Message.obtain();
        obtain.what = 1;
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.sendMessage(obtain);
        }
    }

    private void gotoBluetoothPairSuccessPage(boolean z) {
        LogUtil.a(TAG, "gotoBluetoothPairSuccessPage = ", Boolean.valueOf(z));
        if (this.mIsJump) {
            return;
        }
        cpz.e(this.mProductId);
        this.mIsJump = true;
        if (getContext() != null) {
            Bundle bundle = new Bundle();
            if (z) {
                HagridDeviceBindResultFragment.setBindStatus(9);
            } else {
                HagridDeviceBindResultFragment.setBindStatus(10);
            }
            bundle.putString(BUNDLE_KEY_VIEW, BUNDLE_VALUE_BIND_RESULT_CONFIRM);
            bundle.putBoolean(BUNDLE_KEY_IS_BIND_SUCCESS, true);
            bundle.putString("productId", this.mProductId);
            this.isGotoUserInfoActivity = true;
            bundle.putFloat(BUNDLE_KEY_DEVICE_WEIGHT, this.mWeight);
            bundle.putString("productId", this.mProductId);
            bundle.putBoolean(BUNDLE_KEY_HAS_MANAGER, z);
            bundle.putByteArray(BUNDLE_KEY_MAIN_HUID, this.mMainHuid);
            bundle.putParcelable("commonDeviceInfo", this.mDeviceInfo);
            if (!cpa.ah(this.mProductId) && !cpa.r(this.mProductId)) {
                bundle.putString("deviceSsid", this.mSsid);
                bundle.putString(BUNDLE_KEY_AUTO_DEVICE_SSID, this.mProductId);
                bundle.putString("cloudDeviceId", this.mDeviceIdFromDevice);
            }
            ckm ckmVar = this.mWeightAndFatRateData;
            if (ckmVar != null) {
                bundle.putSerializable(BUNDLE_KEY_RESIS_FAT, ckmVar);
            }
            HagridDeviceBindResultFragment hagridDeviceBindResultFragment = new HagridDeviceBindResultFragment();
            hagridDeviceBindResultFragment.setArguments(bundle);
            switchFragment(this, hagridDeviceBindResultFragment);
        }
    }

    static class ConnectHandler extends BaseHandler<HagridDeviceBindGuidFragment> {
        ConnectHandler(HagridDeviceBindGuidFragment hagridDeviceBindGuidFragment) {
            super(hagridDeviceBindGuidFragment);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        public void handleMessageWhenReferenceNotNull(HagridDeviceBindGuidFragment hagridDeviceBindGuidFragment, Message message) {
            if (hagridDeviceBindGuidFragment == null) {
                return;
            }
            int i = message.what;
            if (i == 1) {
                LogUtil.c(HagridDeviceBindGuidFragment.TAG, "DeviceBindWaitingFragment connect timeout");
                setConnectFail(hagridDeviceBindGuidFragment, message);
            } else if (i == 2) {
                LogUtil.c(HagridDeviceBindGuidFragment.TAG, "DeviceBindWaitingFragment bind fail...");
                setConnectFail(hagridDeviceBindGuidFragment, message);
            } else {
                if (i != 3) {
                    return;
                }
                switchFragment(hagridDeviceBindGuidFragment, 11);
            }
        }

        private void setConnectFail(HagridDeviceBindGuidFragment hagridDeviceBindGuidFragment, Message message) {
            hagridDeviceBindGuidFragment.cancelTimer();
            LogUtil.h(HagridDeviceBindGuidFragment.TAG_RELEASE, "DeviceBindWaitingFragment connect timeout...");
            switchFragment(hagridDeviceBindGuidFragment, message.obj != null ? ((Integer) message.obj).intValue() : 3);
        }

        private void switchFragment(HagridDeviceBindGuidFragment hagridDeviceBindGuidFragment, int i) {
            HagridDeviceBindResultFragment.setBindStatus(i);
            Bundle bundle = new Bundle();
            bundle.putString("productId", hagridDeviceBindGuidFragment.mProductId);
            ContentValues contentValues = new ContentValues();
            contentValues.put("uniqueId", hagridDeviceBindGuidFragment.mUniqueId);
            contentValues.put("productId", hagridDeviceBindGuidFragment.mProductId);
            bundle.putParcelable("commonDeviceInfo", contentValues);
            HagridDeviceBindResultFragment hagridDeviceBindResultFragment = new HagridDeviceBindResultFragment();
            hagridDeviceBindResultFragment.setArguments(bundle);
            hagridDeviceBindGuidFragment.switchFragment(hagridDeviceBindResultFragment);
            hagridDeviceBindGuidFragment.stop();
        }
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LogUtil.a(TAG, "onConfigurationChanged, refresh page");
        refreshBackgroundLayout();
        super.onConfigurationChanged(configuration);
    }

    private void refreshBackgroundLayout() {
        LinearLayout linearLayout = this.mBackgroundLayout;
        if (linearLayout != null) {
            nsn.cLA_(linearLayout, 2);
        }
    }
}
