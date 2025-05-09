package com.huawei.health.ecologydevice.ui.measure.fragment.device;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.fragment.app.Fragment;
import com.huawei.haf.router.AppRouter;
import com.huawei.health.R;
import com.huawei.health.device.connectivity.comm.MeasurableDevice;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.ecologydevice.manager.ResourceManager;
import com.huawei.health.ecologydevice.ui.BaseFragment;
import com.huawei.health.ecologydevice.ui.measure.fragment.BaseRopeIntroductionFragment;
import com.huawei.health.ecologydevice.ui.measure.fragment.HeartRateDeviceRunGuide;
import com.huawei.health.ecologydevice.ui.measure.fragment.ProductFragment;
import com.huawei.health.ecologydevice.ui.measure.fragment.ProductIntroductionFragment;
import com.huawei.health.ecologydevice.ui.measure.fragment.utils.ProductCreateFactory;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.ble.BleConstants;
import defpackage.cei;
import defpackage.dcq;
import defpackage.dcx;
import defpackage.dcz;
import defpackage.dib;
import defpackage.dij;
import defpackage.djr;
import defpackage.dks;
import defpackage.koq;

/* loaded from: classes3.dex */
public class DeviceBindSuccessFragment extends BaseFragment {
    private static final long DELAY_TIME = 2000;
    private static final long DELAY_TIME_H5 = 500;
    private static final String TAG = "DeviceBindSuccessFragment";
    private int mCourseEntryType;
    private String mCourseEquipmentType;
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private boolean mIsGoRopeJump;
    private String mProductId;
    private dcz mProductInfo;
    private Runnable mRunnable;
    private String mTitle;
    private String mUniqueId;

    @Override // androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        LogUtil.a(TAG, "DeviceBindSuccessFragment onAttach");
        super.onAttach(context);
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        LogUtil.a(TAG, "DeviceBindSuccessFragment onCreate");
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.mProductId = arguments.getString("productId");
            this.mIsGoRopeJump = arguments.getBoolean("is_go_rope_jump", false);
            ContentValues contentValues = (ContentValues) arguments.getParcelable("commonDeviceInfo");
            if (contentValues != null) {
                this.mProductId = contentValues.getAsString("productId");
                this.mUniqueId = contentValues.getAsString("uniqueId");
                this.mTitle = arguments.getString("title");
            }
            this.mProductInfo = ResourceManager.e().d(this.mProductId);
            this.mCourseEntryType = arguments.getInt("KEY_INTENT_COURSE_ENTRANCE", 0);
            this.mCourseEquipmentType = arguments.getString("KEY_INTENT_EQUIPMENT_TYPE");
            LogUtil.a(TAG, "DeviceBindSuccessFragment onCreate mCourseEntryType = " + this.mCourseEntryType + ",mCourseEquipmentType = " + this.mCourseEquipmentType);
        }
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        LogUtil.a(TAG, "DeviceBindSuccessFragment onCreateView");
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        if (layoutInflater == null) {
            LogUtil.a(TAG, "DeviceBindSuccessFragment onCreateView inflater is null");
            return onCreateView;
        }
        View inflate = layoutInflater.inflate(R.layout.success_bind_fragment_layout, viewGroup, false);
        if (inflate != null && onCreateView != null) {
            ImageView imageView = (ImageView) inflate.findViewById(R.id.success_bind_show_image);
            dcz dczVar = this.mProductInfo;
            imageView.setImageBitmap((dczVar == null || koq.b(dczVar.e())) ? null : dcx.TK_(dcq.b().a(this.mProductId, this.mProductInfo.e().get(0).e())));
            dij.UX_(getActivity(), imageView, 0.7d, true);
            dij.UX_(getActivity(), (LinearLayout) inflate.findViewById(R.id.image_view_container), 0.8d, true);
            if (onCreateView instanceof ViewGroup) {
                ((ViewGroup) onCreateView).addView(inflate);
            }
        } else {
            LogUtil.a(TAG, "DeviceBindSuccessFragment onCreateView:child == null");
        }
        setTitle(getActivity().getString(R.string.IDS_device_pair_connect));
        this.mRunnable = new Runnable() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceBindSuccessFragment.1
            @Override // java.lang.Runnable
            public void run() {
                DeviceBindSuccessFragment.this.judgeFragment();
            }
        };
        if (this.mProductInfo != null && HealthDevice.HealthDeviceKind.HDK_ROPE_SKIPPING.equals(this.mProductInfo.l())) {
            this.mHandler.postDelayed(this.mRunnable, 500L);
        } else {
            this.mHandler.postDelayed(this.mRunnable, DELAY_TIME);
        }
        return onCreateView;
    }

    private void jumpToMeasureActivity() {
        Bundle bundle = new Bundle();
        bundle.putString("productId", this.mProductId);
        ContentValues contentValues = new ContentValues();
        contentValues.put("uniqueId", this.mUniqueId);
        contentValues.put("productId", this.mProductId);
        bundle.putParcelable("commonDeviceInfo", contentValues);
        bundle.putBoolean("isBindSuccess", true);
        Fragment productIntroductionFragment = new ProductIntroductionFragment();
        productIntroductionFragment.setArguments(bundle);
        switchFragment(productIntroductionFragment);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void judgeFragment() {
        boolean z = false;
        MeasurableDevice bondedDeviceByUniqueId = cei.b().getBondedDeviceByUniqueId(this.mUniqueId, false);
        boolean equals = HiAnalyticsConstant.KeyAndValue.NUMBER_01.equals(this.mProductInfo.p());
        if (bondedDeviceByUniqueId != null && bondedDeviceByUniqueId.isAutoDevice()) {
            z = true;
        }
        if ("1".equals(this.mProductInfo.j())) {
            dks.d(getContext(), this.mProductInfo, this.mProductId, this.mUniqueId);
            popupFragment(null);
            return;
        }
        if (BleConstants.BLE_THIRD_DEVICE_H5.equals(this.mProductInfo.m().d())) {
            if (HealthDevice.HealthDeviceKind.HDK_ROPE_SKIPPING.equals(this.mProductInfo.l())) {
                jumpToProductDescriptionFragment();
                return;
            }
            Intent Wx_ = dks.Wx_(this.mProductInfo, this.mProductId, this.mUniqueId);
            if (Wx_ != null) {
                startActivity(Wx_);
                popupFragment(null);
                return;
            }
            return;
        }
        if (z && equals) {
            Bundle bundle = new Bundle();
            bundle.putString("view", "bond");
            putData(bundle);
            DeviceSilentGuideFragment deviceSilentGuideFragment = new DeviceSilentGuideFragment();
            deviceSilentGuideFragment.setArguments(bundle);
            switchFragment(deviceSilentGuideFragment);
            return;
        }
        if (bondedDeviceByUniqueId != null && HealthDevice.HealthDeviceKind.HDK_HEART_RATE.equals(this.mProductInfo.l())) {
            Bundle bundle2 = new Bundle();
            putData(bundle2);
            HeartRateDeviceRunGuide heartRateDeviceRunGuide = new HeartRateDeviceRunGuide();
            heartRateDeviceRunGuide.setArguments(bundle2);
            switchFragment(heartRateDeviceRunGuide);
            return;
        }
        if (bondedDeviceByUniqueId != null && dib.c().b(this.mCourseEntryType)) {
            dib.c().c(this.mainActivity, this.mCourseEntryType, this.mCourseEquipmentType, bondedDeviceByUniqueId);
        } else if (bondedDeviceByUniqueId != null && (HealthDevice.HealthDeviceKind.HDK_ROPE_SKIPPING.equals(this.mProductInfo.l()) || dks.k(this.mProductId))) {
            enterRopeSkippingOrSport();
        } else {
            jumpToMeasureActivity();
        }
    }

    private void jumpToProductDescriptionFragment() {
        Bundle bundle = new Bundle();
        bundle.putString("title", this.mTitle);
        ContentValues contentValues = new ContentValues();
        contentValues.put("uniqueId", this.mUniqueId);
        contentValues.put("productId", this.mProductId);
        bundle.putParcelable("commonDeviceInfo", contentValues);
        Fragment deviceIllustrationFragment = new DeviceIllustrationFragment();
        deviceIllustrationFragment.setArguments(bundle);
        switchFragment(deviceIllustrationFragment);
    }

    private void enterRopeSkippingOrSport() {
        LogUtil.a(TAG, "enterRopeSkippingOrSport");
        Bundle bundle = new Bundle();
        bundle.putString("productId", this.mProductId);
        bundle.putString("title", this.mTitle);
        bundle.putBoolean("isBindSuccess", true);
        ContentValues contentValues = new ContentValues();
        contentValues.put("uniqueId", this.mUniqueId);
        contentValues.put("productId", this.mProductId);
        bundle.putParcelable("commonDeviceInfo", contentValues);
        if (HealthDevice.HealthDeviceKind.HDK_ROPE_SKIPPING.equals(this.mProductInfo.l())) {
            jumpToProductIntroductionFragment();
            return;
        }
        Fragment createProductFragment = ProductCreateFactory.createProductFragment(this.mProductId);
        createProductFragment.setArguments(bundle);
        switchFragment(createProductFragment);
    }

    private void putData(Bundle bundle) {
        bundle.putString("productId", this.mProductId);
        bundle.putString("title", this.mTitle);
        bundle.putBoolean("isBindSuccess", true);
        ContentValues contentValues = new ContentValues();
        contentValues.put("uniqueId", this.mUniqueId);
        contentValues.put("productId", this.mProductId);
        bundle.putParcelable("commonDeviceInfo", contentValues);
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment
    public boolean onBackPressed() {
        LogUtil.a(TAG, "DeviceBindSuccessFragment onBackPressed");
        this.mHandler.removeCallbacks(this.mRunnable);
        judgeFragment();
        return false;
    }

    private void jumpToProductIntroductionFragment() {
        Bundle arguments = getArguments();
        if (arguments != null && arguments.getBoolean("isBindSuccess")) {
            if (this.mIsGoRopeJump && !"1".equals(dij.c("pageVersion", this.mProductId))) {
                goToMainRopeTab(this.mProductId, this.mUniqueId);
            } else {
                ContentValues contentValues = (ContentValues) arguments.getParcelable("commonDeviceInfo");
                Bundle bundle = new Bundle();
                bundle.putString("productId", this.mProductId);
                bundle.putBoolean("isBindSuccess", true);
                bundle.putBoolean(BaseRopeIntroductionFragment.KEY_FROM_SCAN, true);
                bundle.putParcelable("commonDeviceInfo", contentValues);
                ProductFragment createProductFragment = ProductCreateFactory.createProductFragment(this.mProductId);
                createProductFragment.setArguments(bundle);
                switchFragment(createProductFragment);
            }
            jumpVibrantLifePage();
            return;
        }
        popupFragment(ProductIntroductionFragment.class);
    }

    private void jumpVibrantLifePage() {
        if (!"2GYA".equals(dij.e(this.mProductId))) {
            LogUtil.a(TAG, "is not new HEAD repo device");
        } else if (djr.c().d()) {
            AppRouter.zi_(Uri.parse("huaweihealth://huaweihealth.app/openwith?address=com.huawei.health.h5.vip?isImmerse&h5pro=true&pName=com.huawei.health&path=MembersExchange&urlType=4&from=4")).c(this.mainActivity);
        }
    }
}
