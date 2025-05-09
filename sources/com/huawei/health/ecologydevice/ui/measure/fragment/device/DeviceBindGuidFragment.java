package com.huawei.health.ecologydevice.ui.measure.fragment.device;

import android.bluetooth.BluetoothAdapter;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.huawei.health.R;
import com.huawei.health.device.api.HealthDeviceEntryApi;
import com.huawei.health.ecologydevice.manager.ResourceManager;
import com.huawei.health.ecologydevice.ui.BaseFragment;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbtsdk.btdatatype.callback.BtSwitchStateCallback;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.dcq;
import defpackage.dcx;
import defpackage.dcz;
import defpackage.iyl;
import defpackage.koq;
import defpackage.nrh;
import health.compact.a.LanguageUtil;
import health.compact.a.Services;
import java.util.Locale;

/* loaded from: classes3.dex */
public class DeviceBindGuidFragment extends BaseFragment {
    private static final String TAG = "DeviceBindGuidFragment";
    private String mProductId;
    private dcz mProductInfo;
    private String mTitle;
    private int mGuideIndex = 0;
    private BtSwitchStateCallback mBtSwitchStateCallback = new BtSwitchStateCallback() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceBindGuidFragment.1
        @Override // com.huawei.hwbtsdk.btdatatype.callback.BtSwitchStateCallback
        public void onBtSwitchStateCallback(int i) {
            LogUtil.a(DeviceBindGuidFragment.TAG, "onBtSwitchStateCallback", Integer.valueOf(i));
            if (i == 3) {
                iyl.d().e(this);
                DeviceBindGuidFragment.this.switchToDeviceScanning();
            }
        }
    };

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mProductId = getArguments().getString("productId");
        this.mTitle = getArguments().getString("title");
        this.mProductInfo = ResourceManager.e().d(this.mProductId);
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        LogUtil.a(TAG, "DeviceBindGuidFragment onCreateView");
        ViewGroup viewGroup2 = super.onCreateView(layoutInflater, viewGroup, bundle) instanceof ViewGroup ? (ViewGroup) super.onCreateView(layoutInflater, viewGroup, bundle) : null;
        if (layoutInflater == null) {
            LogUtil.a(TAG, "DeviceBindGuidFragment onCreateView inflater is null");
            return viewGroup2;
        }
        View inflate = layoutInflater.inflate(R.layout.device_bind_guide, viewGroup, false);
        if (viewGroup2 != null) {
            viewGroup2.addView(inflate);
        }
        if (LanguageUtil.bc(getActivity())) {
            ImageView imageView = (ImageView) inflate.findViewById(R.id.device_bind_guid_iv_back);
            ImageView imageView2 = (ImageView) inflate.findViewById(R.id.device_bind_guid_iv_next);
            imageView.setImageResource(R.drawable._2131429722_res_0x7f0b095a);
            imageView2.setImageResource(R.drawable._2131429720_res_0x7f0b0958);
        }
        HealthTextView healthTextView = (HealthTextView) inflate.findViewById(R.id.device_bind_guid_tv_back);
        HealthTextView healthTextView2 = (HealthTextView) inflate.findViewById(R.id.device_bind_guid_tv_next);
        healthTextView.setText(getActivity().getResources().getString(R.string._2130841127_res_0x7f020e27).toUpperCase(Locale.ENGLISH));
        healthTextView2.setText(getActivity().getResources().getString(R.string._2130841128_res_0x7f020e28).toUpperCase(Locale.ENGLISH));
        HealthTextView healthTextView3 = (HealthTextView) inflate.findViewById(R.id.device_bind_guid_tv);
        ImageView imageView3 = (ImageView) inflate.findViewById(R.id.device_bind_guid_img);
        loadGuide(healthTextView3, imageView3, this.mGuideIndex);
        clickNextButton((LinearLayout) inflate.findViewById(R.id.device_bind_guid_ll_next), healthTextView3, imageView3);
        clickBack((LinearLayout) inflate.findViewById(R.id.device_bind_guid_ll_back), healthTextView3, imageView3);
        setTitle(this.mTitle);
        return viewGroup2;
    }

    private void clickNextButton(LinearLayout linearLayout, final HealthTextView healthTextView, final ImageView imageView) {
        linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceBindGuidFragment$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DeviceBindGuidFragment.this.m360x7d33d35e(healthTextView, imageView, view);
            }
        });
    }

    /* renamed from: lambda$clickNextButton$0$com-huawei-health-ecologydevice-ui-measure-fragment-device-DeviceBindGuidFragment, reason: not valid java name */
    /* synthetic */ void m360x7d33d35e(HealthTextView healthTextView, ImageView imageView, View view) {
        if (this.mGuideIndex + 1 > this.mProductInfo.d().size() - 1) {
            LogUtil.c(TAG, "mProductId:" + this.mProductId);
            if (((HealthDeviceEntryApi) Services.c("PluginDevice", HealthDeviceEntryApi.class)).isDeviceKitUniversal(this.mProductId)) {
                LogUtil.a(TAG, "error branch,only am16 use universal interface");
            } else {
                onClickNext();
            }
        } else {
            int i = this.mGuideIndex + 1;
            this.mGuideIndex = i;
            loadGuide(healthTextView, imageView, i);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void onClickNext() {
        if (BluetoothAdapter.getDefaultAdapter().getState() == 10) {
            if (Build.VERSION.SDK_INT > 30 && PermissionUtil.e(this.mainActivity, PermissionUtil.PermissionType.SCAN) != PermissionUtil.PermissionResult.GRANTED) {
                LogUtil.h(TAG, "no scan Permission");
                nrh.b(this.mainActivity, R.string._2130846464_res_0x7f022300);
                return;
            } else {
                showBlueToothDialog();
                return;
            }
        }
        switchToDeviceScanning();
    }

    private void showBlueToothDialog() {
        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(getActivity());
        builder.e(R.string.IDS_device_bluetooth_open_request);
        builder.czC_(R.string.IDS_device_ui_dialog_yes, new View.OnClickListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceBindGuidFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DeviceBindGuidFragment.this.m361xd8c90eab(view);
            }
        });
        builder.czz_(R.string.IDS_device_ui_dialog_no, new View.OnClickListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceBindGuidFragment$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        NoTitleCustomAlertDialog e = builder.e();
        e.setCancelable(false);
        e.show();
    }

    /* renamed from: lambda$showBlueToothDialog$1$com-huawei-health-ecologydevice-ui-measure-fragment-device-DeviceBindGuidFragment, reason: not valid java name */
    /* synthetic */ void m361xd8c90eab(View view) {
        iyl.d().d(this.mBtSwitchStateCallback);
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        iyl.d().e(this.mBtSwitchStateCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void switchToDeviceScanning() {
        DeviceScanningFragment deviceScanningFragment = new DeviceScanningFragment();
        Bundle bundle = new Bundle();
        bundle.putString("productId", this.mProductId);
        bundle.putString("title", this.mTitle);
        deviceScanningFragment.setArguments(bundle);
        switchFragment(deviceScanningFragment);
    }

    private void clickBack(LinearLayout linearLayout, final HealthTextView healthTextView, final ImageView imageView) {
        linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceBindGuidFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DeviceBindGuidFragment.this.m359xe03948c3(healthTextView, imageView, view);
            }
        });
    }

    /* renamed from: lambda$clickBack$3$com-huawei-health-ecologydevice-ui-measure-fragment-device-DeviceBindGuidFragment, reason: not valid java name */
    /* synthetic */ void m359xe03948c3(HealthTextView healthTextView, ImageView imageView, View view) {
        int i = this.mGuideIndex;
        if (i - 1 < 0) {
            this.mainActivity.onBackPressed();
        } else {
            int i2 = i - 1;
            this.mGuideIndex = i2;
            loadGuide(healthTextView, imageView, i2);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void loadGuide(HealthTextView healthTextView, ImageView imageView, int i) {
        if (healthTextView == null || imageView == null) {
            LogUtil.h(TAG, "operationGuidePrompt or operationGuideImg is null");
        } else if (!koq.d(this.mProductInfo.d(), i)) {
            LogUtil.h(TAG, "index is", Integer.valueOf(i));
        } else {
            healthTextView.setText(dcx.d(this.mProductId, this.mProductInfo.d().get(i).c()));
            imageView.setImageBitmap(dcx.TK_(dcq.b().a(this.mProductId, this.mProductInfo.d().get(i).e())));
        }
    }
}
