package com.huawei.health.ecologydevice.ui.measure.fragment.device;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.huawei.health.R;
import com.huawei.health.ecologydevice.manager.ResourceManager;
import com.huawei.health.ecologydevice.ui.BaseFragment;
import com.huawei.health.ecologydevice.ui.measure.adapter.DeviceSilentGuideAdapter;
import com.huawei.health.ecologydevice.ui.measure.fragment.BloodPressureIntroductionFragment;
import com.huawei.health.ecologydevice.ui.measure.fragment.ProductIntroductionFragment;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import defpackage.cpp;
import defpackage.dcz;
import defpackage.dfe;
import health.compact.a.LocalBroadcast;

/* loaded from: classes3.dex */
public class DeviceSilentGuideFragment extends BaseFragment {
    private static final String TAG = "DeviceSelectBindFragment";
    private DeviceSilentGuideAdapter mDeviceSilentGuideAdapter;
    private HealthRecycleView mHealthRecycleView;
    private HealthButton mKnowButton;
    private String mPreviousView = null;
    private String mProductId = null;
    private String mUniqueId = null;
    private dcz mProductInfo = null;

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() == null) {
            return;
        }
        this.mPreviousView = getArguments().getString("view", "bond");
        this.mProductId = getArguments().getString("productId", "0");
        ContentValues contentValues = (ContentValues) getArguments().getParcelable("commonDeviceInfo");
        if (contentValues != null) {
            this.mProductId = contentValues.getAsString("productId");
            this.mUniqueId = contentValues.getAsString("uniqueId");
        }
        LogUtil.a(TAG, "DeviceSilentGuideFragment onCreate the productId is ", this.mProductId);
        this.mProductInfo = ResourceManager.e().d(this.mProductId);
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (layoutInflater == null) {
            LogUtil.h(TAG, "DeviceSilentGuideFragment onCreateView inflater is null");
            FragmentActivity activity = getActivity();
            if (activity != null) {
                activity.finish();
            }
            return null;
        }
        ViewGroup viewGroup2 = super.onCreateView(layoutInflater, viewGroup, bundle) instanceof ViewGroup ? (ViewGroup) super.onCreateView(layoutInflater, viewGroup, bundle) : null;
        View inflate = layoutInflater.inflate(R.layout.device_silent_guid, (ViewGroup) null);
        if (viewGroup2 != null) {
            viewGroup2.addView(inflate);
        }
        LogUtil.a(TAG, "DeviceSilentGuideFragment onCreateView");
        initView(inflate);
        super.setTitle(this.mainActivity.getResources().getString(R.string.IDS_device_auto_read_introduction_title));
        this.mKnowButton.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceSilentGuideFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                DeviceSilentGuideFragment.this.doKnowButtonClickEvent();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        return viewGroup2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doKnowButtonClickEvent() {
        if ("measure".equals(this.mPreviousView)) {
            if (dfe.a().e()) {
                deviceAutoTestSendNoc();
            }
            this.mainActivity.finish();
        } else if ("startMeasureFromDevice".equals(this.mPreviousView)) {
            popupFragment(BloodPressureIntroductionFragment.class);
        } else {
            goToProductIntroductionFragment();
        }
    }

    private void goToProductIntroductionFragment() {
        if (dfe.a().e()) {
            deviceAutoTestSendNoc();
        }
        jumpToProductIntroductionFragment();
    }

    private void initView(View view) {
        this.mKnowButton = (HealthButton) view.findViewById(R.id.hw_device_silent_ok);
        this.mHealthRecycleView = (HealthRecycleView) view.findViewById(R.id.rc_card_silent_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.mainActivity);
        linearLayoutManager.setOrientation(1);
        this.mHealthRecycleView.setLayoutManager(linearLayoutManager);
        dcz dczVar = this.mProductInfo;
        if (dczVar == null) {
            LogUtil.h(TAG, "DeviceSilentGuideFragment mProductInfo is null");
            return;
        }
        LogUtil.a(TAG, "DeviceSilentGuideFragment size is ", Integer.valueOf(dczVar.ab().size()));
        DeviceSilentGuideAdapter deviceSilentGuideAdapter = new DeviceSilentGuideAdapter(this.mainActivity, this.mProductId, this.mProductInfo.ab());
        this.mDeviceSilentGuideAdapter = deviceSilentGuideAdapter;
        this.mHealthRecycleView.setAdapter(deviceSilentGuideAdapter);
        this.mDeviceSilentGuideAdapter.notifyDataSetChanged();
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment
    public boolean onBackPressed() {
        LogUtil.a(TAG, "DeviceSilentGuideFragment onBackPressed");
        if ("measure".equals(this.mPreviousView)) {
            this.mainActivity.finish();
            return false;
        }
        if ("startMeasureFromDevice".equals(this.mPreviousView)) {
            return super.onBackPressed();
        }
        jumpToProductIntroductionFragment();
        return false;
    }

    private void deviceAutoTestSendNoc() {
        Bundle TR_ = dfe.a().TR_(this.mProductInfo.l(), this.mProductId);
        Intent intent = new Intent("com.huawei.health.action.DEVICE_CONNECTED");
        intent.setPackage(cpp.a().getPackageName());
        intent.putExtra("productId", this.mProductId);
        intent.putExtra("uniqueId", this.mUniqueId);
        intent.putExtra("kind", this.mProductInfo.l().name());
        intent.putExtra("autotest", true);
        intent.putExtra("address", this.mProductId);
        intent.putExtra("healthdata", TR_);
        LogUtil.c(TAG, "sendBroadcast for ", this.mProductId, ", kind = ", this.mProductInfo.l().name());
        cpp.a().sendBroadcast(intent, LocalBroadcast.c);
    }

    private void jumpToProductIntroductionFragment() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            try {
                if (arguments.getBoolean("isBindSuccess")) {
                    LogUtil.a(TAG, "jumpToProductIntroductionFragment");
                    Bundle bundle = new Bundle();
                    bundle.putString("productId", arguments.getString("productId"));
                    bundle.putBoolean("isBindSuccess", true);
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("uniqueId", this.mUniqueId);
                    contentValues.put("productId", this.mProductId);
                    bundle.putParcelable("commonDeviceInfo", contentValues);
                    Fragment productIntroductionFragment = new ProductIntroductionFragment();
                    productIntroductionFragment.setArguments(bundle);
                    switchFragment(productIntroductionFragment);
                    return;
                }
            } catch (Exception unused) {
                LogUtil.b(TAG, "jumpToProductIntroductionFragment Exception");
            }
        }
        popupFragment(ProductIntroductionFragment.class);
    }
}
