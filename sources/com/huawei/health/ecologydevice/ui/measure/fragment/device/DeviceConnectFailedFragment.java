package com.huawei.health.ecologydevice.ui.measure.fragment.device;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.huawei.health.R;
import com.huawei.health.ecologydevice.ui.BaseFragment;
import com.huawei.health.ecologydevice.ui.measure.fragment.BloodPressureMeasureGuidFragment;
import com.huawei.health.ecologydevice.ui.measure.fragment.BloodPressureMeasuringProgressFragment;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import health.compact.a.UnitUtil;
import java.util.Locale;

/* loaded from: classes3.dex */
public class DeviceConnectFailedFragment extends BaseFragment implements View.OnClickListener {
    private static final String TAG = "DeviceConnectFailedFragment";
    private HealthButton mConnectErrorConfirmButton;
    private String mKind;
    private String mProductId;
    private String mTitle;
    private String mUniqueId;

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        LogUtil.a(TAG, "onCreate");
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.mProductId = arguments.getString("productId");
            this.mKind = arguments.getString("kind");
            this.mTitle = arguments.getString("title");
            this.mUniqueId = arguments.getString("uniqueId");
            LogUtil.c(TAG, "mProductId = ", this.mProductId, " mKind = ", this.mKind);
        }
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        LogUtil.c(TAG, "onCreateView");
        ViewGroup viewGroup2 = super.onCreateView(layoutInflater, viewGroup, bundle) instanceof ViewGroup ? (ViewGroup) super.onCreateView(layoutInflater, viewGroup, bundle) : null;
        if (layoutInflater == null) {
            LogUtil.a(TAG, "DeviceConnectFailedFragment onCreateView inflater is null");
            return viewGroup2;
        }
        View inflate = layoutInflater.inflate(R.layout.connect_fail_fragment, (ViewGroup) null);
        if (viewGroup2 != null) {
            viewGroup2.addView(inflate);
        }
        this.mConnectErrorConfirmButton = (HealthButton) inflate.findViewById(R.id.bt_device_connect_error_confirm);
        ((HealthTextView) inflate.findViewById(R.id.device_connect_error_tips_tv)).setText(String.format(Locale.ENGLISH, getResources().getString(R.string.IDS_device_common_err_measure_failed_prompts), UnitUtil.e(1.0d, 1, 0), UnitUtil.e(2.0d, 1, 0)));
        this.mConnectErrorConfirmButton.setOnClickListener(this);
        setTitle(this.mTitle);
        return viewGroup2;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == this.mConnectErrorConfirmButton) {
            BloodPressureMeasuringProgressFragment bloodPressureMeasuringProgressFragment = new BloodPressureMeasuringProgressFragment();
            Bundle bundle = new Bundle();
            bundle.putString("kind", this.mKind);
            bundle.putString("productId", this.mProductId);
            bundle.putString("uniqueId", this.mUniqueId);
            bloodPressureMeasuringProgressFragment.setArguments(bundle);
            switchFragment(bloodPressureMeasuringProgressFragment);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment
    public boolean onBackPressed() {
        popupFragment(BloodPressureMeasureGuidFragment.class);
        return false;
    }
}
