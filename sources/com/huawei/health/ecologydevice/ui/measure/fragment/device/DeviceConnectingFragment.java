package com.huawei.health.ecologydevice.ui.measure.fragment.device;

import android.bluetooth.BluetoothManager;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.FragmentActivity;
import com.huawei.btsportdevice.callback.MessageOrStateCallback;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.device.base.AbstractFitnessClient;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.ecologydevice.manager.ResourceManager;
import com.huawei.health.ecologydevice.ui.BaseFragment;
import com.huawei.health.ecologydevice.ui.util.PermissionDialogHelper;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.ble.BleConstants;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.dcq;
import defpackage.dcz;
import defpackage.dds;
import defpackage.dja;
import defpackage.dku;
import defpackage.nsn;
import health.compact.a.EzPluginManager;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

/* loaded from: classes3.dex */
public class DeviceConnectingFragment extends BaseFragment {
    private static final int DELAY_1S = 1000;
    private static final String RELEASE_TAG = "R_DeviceConnectingFragment";
    private static final String TAG = "DeviceConnectingFragment";
    private boolean isFromScanFragment;
    private AnimationDrawable mAnimationDrawable;
    private HealthTextView mBindingTextView;
    private HealthTextView mBindingTipTextView;
    private View mChild;
    private String mDeviceIconPath;
    private ImageView mDeviceImageView;
    private String mDeviceType;
    private ImageView mPairGuideProgressAnim;
    private String mProductId;
    private String mTitle;
    private String mUniqueId;
    private boolean isRopeDevice = false;
    private boolean isCallbackRegistered = false;
    private final dds mRopeDeviceManager = dds.c();
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private final MessageOrStateCallback mRopeSkipMessageOrStateCallback = new MessageOrStateCallback() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceConnectingFragment.1
        @Override // com.huawei.btsportdevice.callback.MessageOrStateCallback
        public void onNewMessage(int i, Bundle bundle) {
        }

        @Override // com.huawei.btsportdevice.callback.MessageOrStateCallback
        public void onStateChange(String str) {
            char c;
            LogUtil.a(DeviceConnectingFragment.TAG, "onStateChange: state=", str);
            str.hashCode();
            int hashCode = str.hashCode();
            if (hashCode == -912943761) {
                if (str.equals(AbstractFitnessClient.ACTION_GATT_STATE_CONNECTED)) {
                    c = 0;
                }
                c = 65535;
            } else if (hashCode != -780096011) {
                if (hashCode == -536360164 && str.equals(AbstractFitnessClient.ACTION_GATT_STATE_RECONNECTED)) {
                    c = 2;
                }
                c = 65535;
            } else {
                if (str.equals(AbstractFitnessClient.ACTION_GATT_STATE_DISCONNECTED)) {
                    c = 1;
                }
                c = 65535;
            }
            if (c != 0) {
                if (c == 1) {
                    DeviceConnectingFragment.this.onResult(false);
                    return;
                } else if (c != 2) {
                    LogUtil.a(DeviceConnectingFragment.TAG, "onStateChange: else state");
                    return;
                }
            }
            DeviceConnectingFragment.this.onResult(true);
        }
    };

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        LogUtil.a(TAG, "onCreate");
        initVariable(getArguments());
        initDeviceByType();
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        if (layoutInflater == null) {
            LogUtil.a(TAG, "onCreateView： inflater is null");
            FragmentActivity activity = getActivity();
            if (activity != null) {
                activity.finish();
            }
            return onCreateView;
        }
        LogUtil.a(TAG, "onCreateView");
        ViewGroup viewGroup2 = onCreateView instanceof ViewGroup ? (ViewGroup) onCreateView : null;
        View inflate = layoutInflater.inflate(R.layout.device_connecting_layout, viewGroup, false);
        this.mChild = inflate;
        if (viewGroup2 != null) {
            viewGroup2.addView(inflate);
        }
        initView();
        initBluetoothAdapter();
        return viewGroup2;
    }

    private void initBluetoothAdapter() {
        if ((getActivity().getSystemService("bluetooth") instanceof BluetoothManager ? (BluetoothManager) getActivity().getSystemService("bluetooth") : null) == null) {
            LogUtil.h(TAG, "no BT Manager in this phone");
            popupFragment(null);
        } else {
            final ActivityResultLauncher registerForActivityResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceConnectingFragment$$ExternalSyntheticLambda0
                @Override // androidx.activity.result.ActivityResultCallback
                public final void onActivityResult(Object obj) {
                    DeviceConnectingFragment.this.m366xf0b9650e((ActivityResult) obj);
                }
            });
            PermissionDialogHelper.Vx_(this.mainActivity, new PermissionDialogHelper.OpenBlueToothAction() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceConnectingFragment.2
                @Override // com.huawei.health.ecologydevice.ui.util.PermissionDialogHelper.OpenBlueToothAction
                public void onPermissionGranted() {
                    ReleaseLogUtil.e(DeviceConnectingFragment.RELEASE_TAG, "initBluetoothAdapter checkScanPermission PermissionGranted");
                    DeviceConnectingFragment.this.openBluetoothAdapter(registerForActivityResult);
                }

                @Override // com.huawei.health.ecologydevice.ui.util.PermissionDialogHelper.OpenBlueToothAction
                public void onPermissionDenied() {
                    ReleaseLogUtil.e(DeviceConnectingFragment.RELEASE_TAG, "initBluetoothAdapter checkScanPermission PermissionDenied");
                    DeviceConnectingFragment.this.popupFragment(null);
                }
            });
        }
    }

    /* renamed from: lambda$initBluetoothAdapter$0$com-huawei-health-ecologydevice-ui-measure-fragment-device-DeviceConnectingFragment, reason: not valid java name */
    /* synthetic */ void m366xf0b9650e(ActivityResult activityResult) {
        if (activityResult.getResultCode() == 0) {
            LogUtil.a(TAG, "initBluetoothAdapter openBluetoothAdapter Denied");
            popupFragment(null);
        } else {
            LogUtil.a(TAG, "initBluetoothAdapter openBluetoothAdapter Access");
            connectRopeDevice();
        }
    }

    public void goToMainRopeTab() {
        Intent intent = new Intent();
        intent.setClassName(getActivity(), "com.huawei.health.MainActivity");
        intent.setFlags(131072);
        intent.putExtra(BleConstants.SPORT_TYPE, 283);
        intent.putExtra("mLaunchSource", 11);
        intent.putExtra("isToSportTab", true);
        startActivity(intent);
        popupFragment(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void openBluetoothAdapter(ActivityResultLauncher activityResultLauncher) {
        try {
            dja.c(activityResultLauncher, "android.bluetooth.adapter.action.REQUEST_ENABLE");
        } catch (SecurityException e) {
            LogUtil.b(TAG, "openBluetoothAdapter SecurityException:", ExceptionUtils.d(e));
        }
    }

    private void initVariable(Bundle bundle) {
        if (bundle != null) {
            this.mProductId = bundle.getString("productId");
            this.mUniqueId = bundle.getString("uniqueId");
            ContentValues contentValues = (ContentValues) bundle.getParcelable("commonDeviceInfo");
            if (contentValues != null) {
                this.mProductId = contentValues.getAsString("productId");
                this.mUniqueId = contentValues.getAsString("uniqueId");
            }
            if (TextUtils.isEmpty(bundle.getString("title"))) {
                this.mTitle = bundle.getString("DeviceName", getString(R.string.IDS_device_bind_connect));
            }
            this.mDeviceType = bundle.getString("DeviceType");
            this.mDeviceIconPath = bundle.getString("DeviceIconPath");
            this.isFromScanFragment = bundle.getBoolean("from_scan_fragment", false);
            this.isRopeDevice = TextUtils.equals(this.mDeviceType, HealthDevice.HealthDeviceKind.HDK_ROPE_SKIPPING.name());
        }
        LogUtil.a(TAG, "initVariable: mProductId=", this.mProductId, ", mUniqueId=", dku.b(this.mUniqueId), ", mTitle=", this.mTitle, ", mDeviceType=", this.mDeviceType, ", mDeviceIconPath=", this.mDeviceIconPath, ", isFromScanFragment=", Boolean.valueOf(this.isFromScanFragment), ", isRopeDevice=", Boolean.valueOf(this.isRopeDevice));
    }

    private void initView() {
        super.setTitle(this.mTitle);
        this.mBindingTextView = (HealthTextView) this.mChild.findViewById(R.id.device_binding_text);
        this.mBindingTipTextView = (HealthTextView) this.mChild.findViewById(R.id.device_binding_under_text);
        if (nsn.ae(BaseApplication.getContext())) {
            this.mBindingTipTextView.setText(R.string.IDS_device_binding_hint_pad);
        }
        ImageView imageView = (ImageView) this.mChild.findViewById(R.id.device_pair_guide_progress_anim);
        this.mPairGuideProgressAnim = imageView;
        if (imageView.getDrawable() instanceof AnimationDrawable) {
            this.mAnimationDrawable = (AnimationDrawable) this.mPairGuideProgressAnim.getDrawable();
        }
        this.mDeviceImageView = (ImageView) this.mChild.findViewById(R.id.device_show_image);
        refreshDeviceImage();
        showBindingAnimation();
    }

    private void initDeviceByType() {
        if (!this.isRopeDevice || this.isCallbackRegistered) {
            return;
        }
        this.mRopeDeviceManager.g();
        this.mRopeDeviceManager.b(this.mProductId);
        this.mRopeDeviceManager.e(TAG, this.mRopeSkipMessageOrStateCallback, false);
        this.isCallbackRegistered = true;
    }

    private void connectRopeDevice() {
        if (this.isRopeDevice) {
            LogUtil.a(TAG, "connectRopeDevice");
            ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceConnectingFragment$$ExternalSyntheticLambda3
                @Override // java.lang.Runnable
                public final void run() {
                    DeviceConnectingFragment.this.m365x80e0af76();
                }
            });
        }
    }

    /* renamed from: lambda$connectRopeDevice$1$com-huawei-health-ecologydevice-ui-measure-fragment-device-DeviceConnectingFragment, reason: not valid java name */
    /* synthetic */ void m365x80e0af76() {
        LogUtil.a(TAG, "connectRopeDevice: start connect");
        this.mRopeDeviceManager.a(true, this.mUniqueId);
    }

    private void refreshDeviceImage() {
        dcz d = ResourceManager.e().d(this.mProductId);
        if (d != null) {
            this.mDeviceIconPath = dcq.b().a(d.t(), d.n().d());
        }
        if (TextUtils.isEmpty(this.mDeviceIconPath)) {
            LogUtil.h(TAG, "refreshDeviceImage: mDeviceIconPath is null");
            return;
        }
        Bitmap coo_ = EzPluginManager.a().coo_(this.mDeviceIconPath);
        if (coo_ != null) {
            this.mDeviceImageView.setImageBitmap(coo_);
        } else {
            LogUtil.h(TAG, "refreshDeviceImage: error");
        }
    }

    private void showBindingAnimation() {
        AnimationDrawable animationDrawable = this.mAnimationDrawable;
        if (animationDrawable == null || animationDrawable.isRunning()) {
            return;
        }
        this.mAnimationDrawable.start();
        this.mPairGuideProgressAnim.setBackground(null);
    }

    private void stopBindingAnimation() {
        AnimationDrawable animationDrawable = this.mAnimationDrawable;
        if (animationDrawable == null || !animationDrawable.isRunning()) {
            return;
        }
        this.mAnimationDrawable.stop();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onResult(final boolean z) {
        this.mHandler.post(new Runnable() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceConnectingFragment$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                DeviceConnectingFragment.this.m367x47b6463b(z);
            }
        });
        this.mHandler.postDelayed(new Runnable() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceConnectingFragment$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                DeviceConnectingFragment.this.goToMainRopeTab();
            }
        }, 1000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: updateViewByResult, reason: merged with bridge method [inline-methods] */
    public void m367x47b6463b(boolean z) {
        this.mBindingTipTextView.setVisibility(4);
        stopBindingAnimation();
        if (z) {
            this.mBindingTextView.setText(getString(R.string._2130845061_res_0x7f021d85));
            this.mPairGuideProgressAnim.setImageResource(R.drawable._2131429883_res_0x7f0b09fb);
        } else {
            this.mBindingTextView.setText(getString(R.string.IDS_device_common_err_connect_fail_tips));
            this.mPairGuideProgressAnim.setImageResource(R.drawable._2131429880_res_0x7f0b09f8);
        }
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment
    public boolean onBackPressed() {
        LogUtil.a(TAG, "onBackPressed");
        if (this.isRopeDevice && this.isCallbackRegistered && !this.mRopeDeviceManager.f()) {
            this.mRopeDeviceManager.c(TAG, false);
            this.mRopeDeviceManager.h();
            this.isCallbackRegistered = false;
        }
        return super.onBackPressed();
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        LogUtil.a(TAG, "onDestroy");
        stopBindingAnimation();
        if (this.isRopeDevice && this.isCallbackRegistered) {
            this.mRopeDeviceManager.c(TAG, false);
            this.isCallbackRegistered = false;
        }
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            this.mHandler = null;
        }
    }
}
