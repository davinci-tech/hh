package com.huawei.health.device.ui.measure.fragment.device;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.ContentValues;
import android.content.IntentFilter;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.fragment.app.Fragment;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.R;
import com.huawei.health.device.connectivity.comm.BleDeviceHelper;
import com.huawei.health.device.connectivity.comm.MeasurableDevice;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.device.ui.DeviceMainActivity;
import com.huawei.health.device.ui.measure.fragment.HonourDeviceFragment;
import com.huawei.health.device.util.EventBus;
import com.huawei.health.ecologydevice.manager.ResourceManager;
import com.huawei.health.ecologydevice.ui.BaseFragment;
import com.huawei.health.ecologydevice.ui.measure.fragment.HeartRateDeviceRunGuide;
import com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceBindFailedFragment;
import com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceSilentGuideFragment;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.device.open.HealthDevice;
import com.huawei.hihealth.device.open.IDeviceEventHandler;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.profile.profile.ProfileExtendConstants;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.progressbar.HealthProgressBar;
import defpackage.ceo;
import defpackage.ces;
import defpackage.cjx;
import defpackage.cpa;
import defpackage.cpp;
import defpackage.cpz;
import defpackage.dcz;
import defpackage.dfe;
import defpackage.dij;
import defpackage.dks;
import defpackage.nsn;
import defpackage.nsy;
import health.compact.a.LanguageUtil;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;

/* loaded from: classes3.dex */
public class DeviceBindWaitingUniversalFragment extends BaseFragment implements View.OnClickListener {
    private static final int BIND_STATUS_FAIL = 3;
    private static final int BIND_STATUS_ING = 1;
    private static final int BIND_STATUS_SUCCESS = 2;
    private static final int DELAY_TIME = 1500;
    private static final int DEVICE_NOT_FOUND = 3;
    private static final int DEVICE_SCAN_FAIL = 2;
    private static final int DEVICE_SCAN_REFRESH = 0;
    private static final int DEVICE_SCAN_TIMEOUT = 1;
    private static final String KEY_PRODUCTID = "productId";
    private static final String KEY_TITLE = "title";
    private static final Object LOCK = new Object();
    private static final int SCREEN_HEIFHT_THREE = 3;
    private static final String TAG = "DeviceBindWaitingUniversalFragment";
    private AnimationDrawable mAnimHonor;
    private View mBindFailView;
    private View mBindingLayout;
    private View mBottomGuideArrowLayout;
    private HealthButton mDoneButton;
    private BindUniversalCallback mHandlerUniversalCallback;
    private HealthDevice mItem;
    private ArrayList<HealthDevice> mListItems;
    private ImageView mPairGuideProgressAnim;
    private HealthTextView mPairProcessNote;
    private ImageView mPairResultDeviceImg;
    private FrameLayout mPairResultDeviceProgressImgFrameLayout;
    private ImageView mPairResultDeviceShowImg;
    private HealthTextView mPairResultPrivacyTxt;
    private HealthTextView mPairResultTxt;
    private int mPosition;
    private String mProductId;
    private dcz mProductInfo;
    private String mTitle;
    private String mUniqueId;
    private ViewTreeObserver mViewTreeObserver;
    private BleDeviceHelper bleDeviceHelper = null;
    private int mBindStatus = 1;
    private Runnable mRun = new Runnable() { // from class: com.huawei.health.device.ui.measure.fragment.device.DeviceBindWaitingUniversalFragment.1
        @Override // java.lang.Runnable
        public void run() {
            if (cjx.e().e(DeviceBindWaitingUniversalFragment.this.mProductInfo.s(), DeviceBindWaitingUniversalFragment.this.mHandlerUniversalCallback)) {
                return;
            }
            DeviceBindWaitingUniversalFragment.this.mHandlerUniversalCallback.onScanFailed(3);
        }
    };
    private Handler mHandler = new Handler() { // from class: com.huawei.health.device.ui.measure.fragment.device.DeviceBindWaitingUniversalFragment.2
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message == null) {
                LogUtil.h(DeviceBindWaitingUniversalFragment.TAG, "DeviceBindWaitingUniversalFragment handleMessage msg is null");
                return;
            }
            super.handleMessage(message);
            int i = message.what;
            if (i == 0) {
                DeviceBindWaitingUniversalFragment.this.refreshList(message);
                return;
            }
            if (i != 1) {
                if (i == 2) {
                    synchronized (DeviceBindWaitingUniversalFragment.LOCK) {
                        DeviceBindWaitingUniversalFragment.this.bindFail(message.arg1);
                    }
                    return;
                }
                LogUtil.h(DeviceBindWaitingUniversalFragment.TAG, "else msg.what");
            }
        }
    };
    private IDeviceEventHandler mBindingStatusCallback = new IDeviceEventHandler() { // from class: com.huawei.health.device.ui.measure.fragment.device.DeviceBindWaitingUniversalFragment.3
        @Override // com.huawei.hihealth.device.open.IDeviceEventHandler
        public void onDeviceFound(HealthDevice healthDevice) {
        }

        @Override // com.huawei.hihealth.device.open.IDeviceEventHandler
        public void onScanFailed(int i) {
        }

        @Override // com.huawei.hihealth.device.open.IDeviceEventHandler
        public void onStateChanged(int i) {
            LogUtil.a(DeviceBindWaitingUniversalFragment.TAG, "DeviceBindWaitingUniversalFragment pair code: ", Integer.valueOf(i));
            if (DeviceBindWaitingUniversalFragment.this.getActivity() == null) {
                return;
            }
            if (i == 7) {
                LogUtil.a(DeviceBindWaitingUniversalFragment.TAG, "DeviceBindWaitingUniversalFragment PAIRING_PASSED");
                DeviceBindWaitingUniversalFragment.this.pairingPass();
                return;
            }
            if (i != 8) {
                if (i == 10) {
                    cjx.e().e(DeviceBindWaitingUniversalFragment.this.mProductId, DeviceBindWaitingUniversalFragment.this.mProductInfo.s(), DeviceBindWaitingUniversalFragment.this.mItem, this);
                    return;
                } else {
                    LogUtil.h(DeviceBindWaitingUniversalFragment.TAG, "DeviceBindWaitingUniversalFragment else");
                    return;
                }
            }
            LogUtil.a(DeviceBindWaitingUniversalFragment.TAG, "DeviceBindWaitingFragment PAIRING_FAILED");
            ceo.d().f(DeviceBindWaitingUniversalFragment.this.mProductId, DeviceBindWaitingUniversalFragment.this.mUniqueId);
            if (cpa.z(DeviceBindWaitingUniversalFragment.this.mProductId)) {
                DeviceBindWaitingUniversalFragment.this.bindFail(i);
            } else {
                DeviceBindWaitingUniversalFragment.this.bindFailFragment();
            }
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public void bindFailFragment() {
        Bundle bundle = new Bundle();
        bundle.putString("productId", this.mProductId);
        bundle.putString("title", this.mTitle);
        LogUtil.a(TAG, "Fail to bind device.", bundle.toString());
        DeviceBindFailedFragment deviceBindFailedFragment = new DeviceBindFailedFragment();
        ceo.d().f(this.mProductId, this.mUniqueId);
        deviceBindFailedFragment.setArguments(bundle);
        switchFragment(deviceBindFailedFragment);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void pairingPass() {
        cpz.d(this.mItem, this.mProductId, this.mProductInfo);
        dij.e(this.mProductInfo.l());
        cjx.e().b();
        HealthDevice b = ceo.d().b(this.mProductId, this.mUniqueId);
        boolean equals = HiAnalyticsConstant.KeyAndValue.NUMBER_01.equals(this.mProductInfo.p());
        if (b != null && equals) {
            Bundle bundle = new Bundle();
            bundle.putString("view", "bond");
            bundle.putString("productId", this.mProductId);
            bundle.putString("title", this.mTitle);
            ContentValues contentValues = new ContentValues();
            contentValues.put("uniqueId", this.mUniqueId);
            contentValues.put("productId", this.mProductId);
            bundle.putParcelable("commonDeviceInfo", contentValues);
            Fragment deviceSilentGuideFragment = new DeviceSilentGuideFragment();
            deviceSilentGuideFragment.setArguments(bundle);
            switchFragment(deviceSilentGuideFragment);
            return;
        }
        if (b != null && this.mProductInfo.l().equals(HealthDevice.HealthDeviceKind.HDK_HEART_RATE)) {
            if (cpa.z(this.mProductId)) {
                ceo.d().e(this.mProductId, this.mProductInfo.s(), this.mItem);
                bindSuccess();
                return;
            }
            Fragment heartRateDeviceRunGuide = new HeartRateDeviceRunGuide();
            Bundle bundle2 = new Bundle();
            bundle2.putString("title", this.mTitle);
            heartRateDeviceRunGuide.setArguments(bundle2);
            switchFragment(heartRateDeviceRunGuide);
            return;
        }
        LogUtil.a(TAG, "DeviceBindWaitingUniversalFragment startActivity");
        if (cpa.z(this.mProductId)) {
            ces.a().e(this.mProductId, this.mProductInfo.s(), this.mItem);
            bindSuccess();
        } else {
            LogUtil.h(TAG, " else startActivity");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshList(Message message) {
        synchronized (LOCK) {
            if (message.obj instanceof com.huawei.hihealth.device.open.HealthDevice) {
                com.huawei.hihealth.device.open.HealthDevice healthDevice = (com.huawei.hihealth.device.open.HealthDevice) message.obj;
                if (!isDeviceExistInmListItems(healthDevice)) {
                    this.mListItems.add(healthDevice);
                    prepareBind();
                }
            }
        }
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        boolean z;
        MeasurableDevice c;
        LogUtil.a(TAG, "DeviceBindWaitingFragment onCreate");
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments == null) {
            LogUtil.h(TAG, "onCreate Bundle is null");
            return;
        }
        if (getActivity() instanceof DeviceMainActivity) {
            this.mListItems = ((DeviceMainActivity) getActivity()).d();
        }
        this.mProductId = arguments.getString("productId");
        ContentValues contentValues = (ContentValues) arguments.getParcelable("commonDeviceInfo");
        if (contentValues != null) {
            this.mProductId = contentValues.getAsString("productId");
            this.mUniqueId = contentValues.getAsString("uniqueId");
        }
        if (TextUtils.isEmpty(this.mProductId)) {
            LogUtil.h(TAG, "onCreate mProductId is null");
            return;
        }
        if (TextUtils.isEmpty(this.mUniqueId) && (c = ceo.d().c(this.mProductId)) != null) {
            this.mUniqueId = c.getUniqueId();
        }
        this.mPosition = arguments.getInt("position");
        String string = arguments.getString("title");
        this.mTitle = string;
        if (TextUtils.isEmpty(string)) {
            LogUtil.h(TAG, "onCreate mTitle is null");
            this.mTitle = "";
        }
        this.mProductInfo = ResourceManager.e().d(this.mProductId);
        this.mHandlerUniversalCallback = new BindUniversalCallback();
        LogUtil.a(TAG, "DeviceBindWaitingFragment productId is ", this.mProductId);
        LogUtil.c(TAG, "DeviceBindWaitingFragment position is ", Integer.valueOf(this.mPosition));
        ArrayList<com.huawei.hihealth.device.open.HealthDevice> arrayList = this.mListItems;
        if (arrayList != null) {
            LogUtil.a(TAG, "DeviceBindWaitingFragment mListItems size is ", Integer.valueOf(arrayList.size()));
            if (this.mListItems.isEmpty() && !cpa.z(this.mProductId)) {
                z = true;
                if ((this.mListItems != null || z) && getActivity() != null) {
                    LogUtil.a(TAG, "DeviceBindWaitingFragment destroy activity");
                    getActivity().finish();
                }
                return;
            }
        }
        z = false;
        if (this.mListItems != null) {
        }
        LogUtil.a(TAG, "DeviceBindWaitingFragment destroy activity");
        getActivity().finish();
    }

    private void prepareBind() {
        synchronized (LOCK) {
            int i = this.mPosition;
            if (i >= 0 && i < this.mListItems.size()) {
                com.huawei.hihealth.device.open.HealthDevice healthDevice = this.mListItems.get(this.mPosition);
                this.mItem = healthDevice;
                if (healthDevice == null) {
                    LogUtil.h(TAG, "prepareBind mItem is null");
                    return;
                }
                if (this.mProductInfo == null) {
                    getActivity().finish();
                    return;
                }
                this.mUniqueId = healthDevice.getUniqueId();
                LogUtil.c(TAG, "prepareBind item_name: ", this.mItem.getDeviceName());
                LogUtil.a(TAG, "DeviceBindWaitingFragment pair is ", this.mProductInfo.x().a());
                if ("yes".equals(this.mProductInfo.x().a())) {
                    noAutoStatus();
                } else {
                    cjx.e().e(this.mProductId, this.mProductInfo.s(), this.mItem, this.mBindingStatusCallback);
                }
            }
        }
    }

    private void noAutoStatus() {
        if (dfe.a().e()) {
            return;
        }
        try {
            BluetoothDevice remoteDevice = BluetoothAdapter.getDefaultAdapter().getRemoteDevice(this.mItem.getAddress());
            if (remoteDevice.getBondState() != 12) {
                LogUtil.a(TAG, "NOT BOND_BONDED");
                createBond(remoteDevice.getClass(), remoteDevice);
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("android.bluetooth.device.action.BOND_STATE_CHANGED");
                this.bleDeviceHelper = new BleDeviceHelper(remoteDevice);
                cpp.a().registerReceiver(this.bleDeviceHelper, intentFilter);
            } else {
                cjx.e().e(this.mProductId, this.mProductInfo.s(), this.mItem, this.mBindingStatusCallback);
            }
        } catch (SecurityException e) {
            LogUtil.b(TAG, "noAutoStatus SecurityException:", ExceptionUtils.d(e));
        }
    }

    public boolean createBond(Class cls, BluetoothDevice bluetoothDevice) {
        try {
            Method method = cls.getMethod("createBond", new Class[0]);
            if (method.invoke(bluetoothDevice, new Object[0]) instanceof Boolean) {
                return ((Boolean) method.invoke(bluetoothDevice, new Object[0])).booleanValue();
            }
            return false;
        } catch (IllegalAccessException e) {
            LogUtil.b(TAG, "IllegalAccessException ", e.getMessage());
            return false;
        } catch (NoSuchMethodException e2) {
            LogUtil.b(TAG, "NoSuchMethodException ", e2.getMessage());
            return false;
        } catch (InvocationTargetException e3) {
            LogUtil.b(TAG, "InvocationTargetException ", e3.getMessage());
            return false;
        }
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        LogUtil.a(TAG, "DeviceBindWaitingFragment onCreateView");
        View inflate = layoutInflater.inflate(R.layout.device_measure_bind_device, viewGroup, false);
        initChildView(inflate);
        View findViewById = inflate.findViewById(R.id.pair_guide_right_btn_layout);
        View findViewById2 = inflate.findViewById(R.id.pair_guide_left_cancel_layout);
        findViewById.setOnClickListener(this);
        findViewById2.setOnClickListener(this);
        this.mDoneButton.setOnClickListener(this);
        HealthTextView healthTextView = (HealthTextView) inflate.findViewById(R.id.device_measure_search_prompt);
        if (cpa.z(this.mProductId)) {
            initStartView();
        } else {
            this.mBindFailView.setVisibility(8);
            this.mBindingLayout.setVisibility(0);
            healthTextView.setText(R.string.IDS_device_selection_waiting_for_binding);
            ((HealthProgressBar) inflate.findViewById(R.id.hw_device_bind_pb)).setLayerType(1, null);
        }
        healthTextView.setVisibility(0);
        if (cpa.z(this.mProductId)) {
            this.mHandler.postDelayed(this.mRun, ProfileExtendConstants.TIME_OUT);
        } else {
            prepareBind();
        }
        ((HealthTextView) inflate.findViewById(R.id.right_arrow_txt)).setText(inflate.getResources().getString(R.string._2130841467_res_0x7f020f7b).toUpperCase(Locale.ENGLISH));
        ImageView imageView = (ImageView) inflate.findViewById(R.id.pair_guide_left_image);
        ImageView imageView2 = (ImageView) inflate.findViewById(R.id.pair_guide_right_image);
        if (LanguageUtil.bc(getActivity())) {
            imageView.setImageResource(R.drawable._2131429722_res_0x7f0b095a);
            imageView2.setImageResource(R.drawable._2131429720_res_0x7f0b0958);
        } else {
            imageView.setImageResource(R.drawable._2131429720_res_0x7f0b0958);
            imageView2.setImageResource(R.drawable._2131429722_res_0x7f0b095a);
        }
        ViewGroup viewGroup2 = super.onCreateView(layoutInflater, viewGroup, bundle) instanceof ViewGroup ? (ViewGroup) super.onCreateView(layoutInflater, viewGroup, bundle) : null;
        if (viewGroup2 != null) {
            viewGroup2.addView(inflate);
        }
        super.setTitle(this.mTitle);
        return viewGroup2;
    }

    private void initChildView(View view) {
        this.mBindFailView = view.findViewById(R.id.hw_device_failed_fragment);
        this.mBindingLayout = view.findViewById(R.id.hw_binding_layout);
        this.mPairResultTxt = (HealthTextView) nsy.cMd_(view, R.id.pair_result_device_show_txt);
        this.mPairResultDeviceShowImg = (ImageView) nsy.cMd_(view, R.id.pair_result_device_show_img);
        this.mPairProcessNote = (HealthTextView) nsy.cMd_(view, R.id.device_pairing_progress_guide_single_note);
        ImageView imageView = (ImageView) nsy.cMd_(view, R.id.device_pair_guide_progress_anim);
        this.mPairGuideProgressAnim = imageView;
        if (imageView.getDrawable() instanceof AnimationDrawable) {
            this.mAnimHonor = (AnimationDrawable) this.mPairGuideProgressAnim.getDrawable();
        }
        this.mBottomGuideArrowLayout = nsy.cMd_(view, R.id.device_pairing_guide_bottom_arrow_layout);
        this.mPairResultPrivacyTxt = (HealthTextView) nsy.cMd_(view, R.id.pair_result_device_privacy_txt);
        if (nsy.cMd_(view, R.id.done_button) instanceof HealthButton) {
            this.mDoneButton = (HealthButton) nsy.cMd_(view, R.id.done_button);
        }
        this.mPairResultDeviceImg = (ImageView) nsy.cMd_(view, R.id.pair_result_device_img);
        this.mPairResultDeviceProgressImgFrameLayout = (FrameLayout) nsy.cMd_(view, R.id.pair_result_device_progress_img);
        ViewTreeObserver viewTreeObserver = this.mPairResultDeviceImg.getViewTreeObserver();
        this.mViewTreeObserver = viewTreeObserver;
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.huawei.health.device.ui.measure.fragment.device.DeviceBindWaitingUniversalFragment.4
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                ViewGroup.LayoutParams layoutParams = DeviceBindWaitingUniversalFragment.this.mPairResultDeviceProgressImgFrameLayout.getLayoutParams();
                layoutParams.height = nsn.j() / 3;
                layoutParams.width = nsn.n();
                DeviceBindWaitingUniversalFragment.this.mPairResultDeviceProgressImgFrameLayout.setLayoutParams(layoutParams);
            }
        });
    }

    private void initStartView() {
        this.mBindStatus = 1;
        this.mPairResultTxt.setText(R.string.IDS_device_selection_waiting_binding);
        this.mPairGuideProgressAnim.setVisibility(0);
        this.mBindFailView.setVisibility(0);
        this.mBindingLayout.setVisibility(8);
        this.mPairResultDeviceShowImg.setVisibility(8);
        this.mPairProcessNote.setVisibility(8);
        this.mAnimHonor.start();
        this.mBottomGuideArrowLayout.setVisibility(8);
        this.mPairResultPrivacyTxt.setVisibility(8);
        this.mDoneButton.setVisibility(8);
    }

    private void bindSuccess() {
        dks.e();
        cjx.e().f();
        this.mBindStatus = 2;
        this.mPairGuideProgressAnim.setVisibility(8);
        this.mPairResultTxt.setText(R.string.IDS_device_jabra_bind_success_text);
        this.mBindFailView.setVisibility(0);
        this.mBindingLayout.setVisibility(8);
        this.mPairResultDeviceShowImg.setVisibility(0);
        this.mPairResultDeviceShowImg.setImageResource(R.mipmap._2131821042_res_0x7f1101f2);
        this.mPairProcessNote.setVisibility(8);
        this.mBottomGuideArrowLayout.setVisibility(8);
        this.mPairResultPrivacyTxt.setVisibility(0);
        this.mDoneButton.setVisibility(0);
        this.mDoneButton.setText(getActivity().getResources().getString(R.string._2130841524_res_0x7f020fb4).toUpperCase(Locale.ENGLISH));
        AnimationDrawable animationDrawable = this.mAnimHonor;
        if (animationDrawable != null) {
            animationDrawable.stop();
        }
        EventBus.d(new EventBus.b("sub_am16_bind_success"));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bindFail(int i) {
        cjx.e().f();
        this.mBindStatus = 3;
        this.mPairGuideProgressAnim.setVisibility(8);
        this.mPairResultTxt.setText(R.string.IDS_device_binding_fail);
        this.mPairResultDeviceShowImg.setImageResource(R.mipmap._2131821044_res_0x7f1101f4);
        this.mBindFailView.setVisibility(0);
        this.mBindingLayout.setVisibility(8);
        this.mPairResultDeviceShowImg.setVisibility(0);
        this.mPairResultDeviceShowImg.setImageResource(R.mipmap._2131821044_res_0x7f1101f4);
        this.mPairProcessNote.setVisibility(0);
        if (i == 3) {
            this.mPairProcessNote.setText(R.string.IDS_device_am16_bind_fail_tip);
        } else {
            this.mPairProcessNote.setText(R.string.IDS_device_am16_bind_fail_tip2);
        }
        this.mBottomGuideArrowLayout.setVisibility(0);
        this.mPairResultPrivacyTxt.setVisibility(8);
        this.mDoneButton.setVisibility(8);
        AnimationDrawable animationDrawable = this.mAnimHonor;
        if (animationDrawable != null) {
            animationDrawable.stop();
        }
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        dcz dczVar = this.mProductInfo;
        if (dczVar != null && dczVar.x().c() == 2) {
            cjx.e().b();
        }
        if (this.bleDeviceHelper != null) {
            cpp.a().unregisterReceiver(this.bleDeviceHelper);
        }
        AnimationDrawable animationDrawable = this.mAnimHonor;
        if (animationDrawable != null) {
            animationDrawable.stop();
        }
        super.onDestroy();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.pair_guide_right_btn_layout) {
            synchronized (LOCK) {
                try {
                    this.mListItems.clear();
                    initStartView();
                    this.mHandler.postDelayed(this.mRun, ProfileExtendConstants.TIME_OUT);
                } finally {
                    ViewClickInstrumentation.clickOnView(view);
                }
            }
        } else if (id == R.id.pair_guide_left_cancel_layout) {
            if (ceo.d().b(this.mProductId, this.mUniqueId) != null) {
                ceo.d().f(this.mProductId, this.mUniqueId);
            }
            getActivity().finish();
        } else if (id == R.id.done_button) {
            toHonourDeviceFragment();
        } else {
            LogUtil.h(TAG, "onClick else");
        }
    }

    private void toHonourDeviceFragment() {
        Bundle bundle = new Bundle();
        bundle.putString("productId", this.mProductId);
        bundle.putString("goto", "devicebind");
        bundle.putString("title", this.mTitle);
        ContentValues contentValues = new ContentValues();
        contentValues.put("productId", this.mProductId);
        contentValues.put("uniqueId", this.mUniqueId);
        bundle.putParcelable("commonDeviceInfo", contentValues);
        Fragment honourDeviceFragment = new HonourDeviceFragment();
        honourDeviceFragment.setArguments(bundle);
        switchFragment(honourDeviceFragment);
    }

    class BindUniversalCallback implements IDeviceEventHandler {
        @Override // com.huawei.hihealth.device.open.IDeviceEventHandler
        public void onStateChanged(int i) {
        }

        private BindUniversalCallback() {
        }

        @Override // com.huawei.hihealth.device.open.IDeviceEventHandler
        public void onDeviceFound(com.huawei.hihealth.device.open.HealthDevice healthDevice) {
            LogUtil.a(DeviceBindWaitingUniversalFragment.TAG, "Callback, onDeviceFound: ", healthDevice.getDeviceName());
            Message obtain = Message.obtain();
            obtain.what = 0;
            obtain.obj = healthDevice;
            DeviceBindWaitingUniversalFragment.this.mHandler.sendMessage(obtain);
        }

        @Override // com.huawei.hihealth.device.open.IDeviceEventHandler
        public void onScanFailed(int i) {
            LogUtil.a(DeviceBindWaitingUniversalFragment.TAG, "DeviceScanningFragment onScanFailed");
            if (i == 1 || i == 3) {
                Message obtain = Message.obtain();
                obtain.what = 1;
                DeviceBindWaitingUniversalFragment.this.mHandler.sendMessage(obtain);
            }
            Message obtain2 = Message.obtain();
            obtain2.what = 2;
            obtain2.arg1 = i;
            DeviceBindWaitingUniversalFragment.this.mHandler.sendMessage(obtain2);
        }
    }

    private boolean isDeviceExistInmListItems(com.huawei.hihealth.device.open.HealthDevice healthDevice) {
        synchronized (LOCK) {
            Iterator<com.huawei.hihealth.device.open.HealthDevice> it = this.mListItems.iterator();
            while (it.hasNext()) {
                com.huawei.hihealth.device.open.HealthDevice next = it.next();
                if (next.getDeviceName().equals(healthDevice.getDeviceName()) || next.getAddress().equals(healthDevice.getAddress())) {
                    return true;
                }
            }
            return false;
        }
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment
    public boolean onBackPressed() {
        int i = this.mBindStatus;
        if (i == 1) {
            return false;
        }
        if (i == 2) {
            toHonourDeviceFragment();
            return false;
        }
        if (i == 3) {
            if (ceo.d().b(this.mProductId, this.mUniqueId) != null) {
                ceo.d().f(this.mProductId, this.mUniqueId);
            }
        } else {
            LogUtil.h(TAG, "onBackPressed else");
        }
        return super.onBackPressed();
    }
}
