package com.huawei.health.ecologydevice.ui.measure.fragment.device;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import com.huawei.health.R;
import com.huawei.health.ecologydevice.ui.BaseFragment;
import com.huawei.health.ecologydevice.ui.measure.fragment.ProductIntroductionFragment;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.progressbar.HealthProgressBar;

/* loaded from: classes3.dex */
public class DeviceMeasuringProgressFragment extends BaseFragment {
    private static final String TAG = "DeviceMeasuringProgressFragment";
    private HealthTextView mSearchDevicePrompt;

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment
    public boolean onBackPressed() {
        LogUtil.a(TAG, "DeviceMeasuringProgressFragment onBackPressed");
        return super.showCustomAlertDialog(R.string.IDS_device_ui_activity_quit_dialog_in_mea);
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment
    public void saveResultData() {
        super.saveResultData();
        onDestroy();
        popupFragment(ProductIntroductionFragment.class);
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        ViewGroup viewGroup2 = super.onCreateView(layoutInflater, viewGroup, bundle) instanceof ViewGroup ? (ViewGroup) super.onCreateView(layoutInflater, viewGroup, bundle) : null;
        if (layoutInflater == null) {
            LogUtil.h(TAG, "DeviceMeasuringProgressFragment onCreateView inflater is null");
            return viewGroup2;
        }
        this.child = layoutInflater.inflate(R.layout.device_show_realtime_result, viewGroup, false);
        init();
        if (viewGroup2 != null) {
            viewGroup2.addView(this.child);
        }
        return viewGroup2;
    }

    private void init() {
        super.setTitle(getArguments().getString("title"));
        if (this.child != null) {
            HealthTextView healthTextView = (HealthTextView) this.child.findViewById(R.id.device_measure_search_prompt);
            this.mSearchDevicePrompt = healthTextView;
            healthTextView.setText(getArguments().getInt("content"));
            this.mSearchDevicePrompt.setVisibility(0);
            ((HealthTextView) this.child.findViewById(R.id.device_measure_unit_center_tv)).setVisibility(4);
            HealthProgressBar healthProgressBar = (HealthProgressBar) this.child.findViewById(R.id.device_measure_top_progress_bar);
            ImageView imageView = (ImageView) this.child.findViewById(R.id.device_measure_top_progress_bar_bg);
            healthProgressBar.setVisibility(0);
            imageView.setVisibility(0);
            healthProgressBar.setIndeterminateDrawable(getResources().getDrawable(R.drawable.hw_device_meausure_progress));
            healthProgressBar.setAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim._2130772005_res_0x7f010025));
        }
    }
}
