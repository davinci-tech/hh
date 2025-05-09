package com.huawei.health.ecologydevice.ui.measure.fragment;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import com.huawei.health.device.api.HonourDeviceConstantsApi;
import com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceMeasureGuideFragment;
import com.huawei.health.ecologydevice.ui.util.PermissionDialogHelper;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.Services;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

/* loaded from: classes3.dex */
public abstract class BluetoothMeasureFragment extends DeviceMeasureGuideFragment {
    private static final int START_MEASURE = 0;
    private static final int START_MEASURE_DELAY = 2000;
    private static final String TAG = "BluetoothMeasureFragment";
    private static final String TAG_RELEASE = "DEVMGR_EcologyDevice_BluetoothMeasureFragment";
    private Handler mHandler = new Handler() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.BluetoothMeasureFragment.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what != 0) {
                return;
            }
            BluetoothMeasureFragment.this.startMeasureAfterGetPermission();
        }
    };

    @Override // com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceMeasureGuideFragment
    public void startMeasure() {
        LogUtil.a(TAG, "startMeasure");
        if (BluetoothAdapter.getDefaultAdapter().getState() != 12 || !PermissionDialogHelper.Vy_(this.mainActivity)) {
            ReleaseLogUtil.e(TAG_RELEASE, "startMeasure bluetooth is close or not scan permission");
            PermissionDialogHelper.e(this, 1);
        } else {
            startMeasureAfterGetPermission();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startMeasureAfterGetPermission() {
        LogUtil.a(TAG, "startMeasureAfterGetPermission");
        if (((HonourDeviceConstantsApi) Services.c("PluginDevice", HonourDeviceConstantsApi.class)).isHonourWeightDevice(this.mProductId)) {
            if (this.mIsStatus) {
                super.startMeasure();
                return;
            } else {
                super.startMeasure(true);
                return;
            }
        }
        LogUtil.a(TAG, "startMeasureAfterGetPermission is not honour devices");
        super.startMeasure();
    }

    @Override // com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceMeasureGuideFragment, com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.removeMessages(0);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        LogUtil.a(TAG, "onActivityResult requestCode：", Integer.valueOf(i), " resultCode：", Integer.valueOf(i2));
        if (i == 1) {
            if (i2 == -1) {
                if (this.mHandler != null) {
                    LogUtil.a(TAG, "onActivityResult send start measure message");
                    this.mHandler.removeMessages(0);
                    this.mHandler.sendEmptyMessageDelayed(0, 2000L);
                    return;
                }
                return;
            }
            if (getArguments() != null && "honour_device".equals(getArguments().getString("goback", ""))) {
                LogUtil.a(TAG, "onActivityResult onBackPressed");
                onBackPressed();
            } else {
                LogUtil.a(TAG, "onActivityResult popupFragment");
                popupFragment(null);
            }
        }
    }
}
