package com.huawei.health.device.ui.measure.fragment;

import android.content.ContentValues;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.huawei.health.R;
import com.huawei.health.ecologydevice.manager.ResourceManager;
import com.huawei.health.ecologydevice.ui.BaseFragment;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.dcq;
import defpackage.dcx;
import defpackage.dcz;
import defpackage.dfb;
import defpackage.dij;
import defpackage.koq;
import defpackage.nsy;
import health.compact.a.CommonUtil;
import health.compact.a.LanguageUtil;
import java.util.ArrayList;

/* loaded from: classes3.dex */
public class WiFiDeviceBindGuideFragment extends BaseFragment implements View.OnClickListener {
    private static final int ANIMATION_DELAY_TIME = 500;
    private static final int MEASURE_GUIDE = 994;
    private static final String TAG = "WiFiDeviceBindGuideFragment";
    private dfb mAnimationHandler;
    private ImageView mCancelImage;
    private ContentValues mDeviceInfo;
    private ImageView mGuideImg;
    private HealthTextView mGuidePrompt;
    private ArrayList<Object> mImgArray;
    private ImageView mNextImage;
    private String mProductId;
    private dcz mProductInfo;
    private String mUniqueId;
    private int mCurrentImg = 0;
    private int mBindCount = 0;
    private int mMode = 1;
    private boolean mIsStartAnim = false;
    private String mOuthName = "";
    private String mOuthPwd = "";

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        LogUtil.a(TAG, "onCreateView");
        this.child = layoutInflater.inflate(R.layout.wifi_device_bind_guide_layout, viewGroup, false);
        setTitle(this.mainActivity.getResources().getString(R.string.IDS_device_mgr_device_pair_guide_note));
        initData();
        initView();
        initViewData();
        ViewGroup viewGroup2 = super.onCreateView(layoutInflater, viewGroup, bundle) instanceof ViewGroup ? (ViewGroup) super.onCreateView(layoutInflater, viewGroup, bundle) : null;
        if (viewGroup2 != null) {
            viewGroup2.addView(this.child);
        }
        return viewGroup2;
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        if (this.mMode != 2) {
            cancelGuideImgAnimation();
        }
    }

    private ArrayList<Object> getBindGuideImage() {
        LogUtil.a(TAG, "WeightMeasureGuideFragment getMeasure()");
        ArrayList<Object> arrayList = new ArrayList<>(16);
        for (int i = 0; i < this.mProductInfo.d().size(); i++) {
            arrayList.add(dcq.b().a(this.mProductId, this.mProductInfo.d().get(i).e()));
        }
        LogUtil.a(TAG, "guideImages size: ", Integer.valueOf(arrayList.size()));
        return arrayList;
    }

    private void showGuideImgAnimation() {
        LogUtil.a(TAG, "DeviceMeasureGuideFragment showGuideImgAnimation()");
        ArrayList<Object> arrayList = this.mImgArray;
        if (arrayList == null || arrayList.size() == 0) {
            LogUtil.h(TAG, "showGuideImgAnimation mImgArray is null or size is zero");
            return;
        }
        if (this.mImgArray.size() == this.mCurrentImg) {
            this.mCurrentImg = 0;
        }
        LogUtil.a(TAG, "showGuideImgAnimation() mImgArray size is ", Integer.valueOf(this.mImgArray.size()));
        Object obj = this.mImgArray.get(this.mCurrentImg);
        this.mCurrentImg++;
        LogUtil.a(TAG, "showGuideImgAnimation() mCurrentImgId: ", obj);
        if (obj instanceof Integer) {
            this.mGuideImg.setImageResource(((Integer) obj).intValue());
        }
        if (obj instanceof String) {
            this.mGuideImg.setImageBitmap(dcx.TK_((String) obj));
        }
        dfb dfbVar = new dfb(this.mainActivity, obj, this.mCurrentImg, this.mGuideImg, this.mImgArray);
        this.mAnimationHandler = dfbVar;
        dfbVar.e(500);
        this.mAnimationHandler.sendEmptyMessageDelayed(MEASURE_GUIDE, 500L);
        this.mIsStartAnim = true;
    }

    private void cancelGuideImgAnimation() {
        LogUtil.a(TAG, " cancelGuideImgAnimation()");
        this.mCurrentImg = 0;
        dfb dfbVar = this.mAnimationHandler;
        if (dfbVar == null || !dfbVar.hasMessages(MEASURE_GUIDE)) {
            return;
        }
        this.mAnimationHandler.removeMessages(MEASURE_GUIDE);
    }

    private void initData() {
        if (getArguments() != null) {
            ContentValues contentValues = (ContentValues) getArguments().getParcelable("commonDeviceInfo");
            this.mDeviceInfo = contentValues;
            if (contentValues != null) {
                this.mProductId = contentValues.getAsString("productId");
                this.mUniqueId = this.mDeviceInfo.getAsString("uniqueId");
            } else {
                this.mProductId = getArguments().getString("productId");
            }
            if (TextUtils.isEmpty(this.mProductId) || TextUtils.isEmpty(this.mUniqueId)) {
                LogUtil.h(TAG, "initData mProductId or mUniqueId is empty");
            }
            this.mOuthName = getArguments().getString("outhName");
            this.mOuthPwd = getArguments().getString("outhPd");
            this.mMode = getArguments().getInt("config_mode", 1);
        }
        if (TextUtils.isEmpty(this.mProductId)) {
            LogUtil.b(TAG, "initData mProductId is null");
            this.mainActivity.onBackPressed();
        } else if (this.mMode != 2) {
            dcz d = ResourceManager.e().d(this.mProductId);
            this.mProductInfo = d;
            if (d == null) {
                LogUtil.h(TAG, "initData mProductInfo is null");
                this.mainActivity.onBackPressed();
            } else {
                this.mImgArray = getBindGuideImage();
            }
        }
    }

    private void initViewData() {
        if (dij.c(this.mainActivity) || LanguageUtil.bn(this.mainActivity)) {
            LogUtil.b(TAG, "langIsAr");
            this.mCancelImage.setBackgroundResource(R.drawable.wifi_device_arrow_right);
            this.mNextImage.setBackgroundResource(R.drawable.wifi_device_arrow_left);
        }
        if (this.mMode == 2) {
            this.mGuidePrompt.setText(R.string.IDS_device_wifi_ap_guide_tip);
            this.mGuideImg.setImageResource(R.drawable.wifi_device_ap_bind_guide);
            return;
        }
        dcz dczVar = this.mProductInfo;
        if (dczVar != null) {
            ArrayList<dcz.d> d = dczVar.d();
            if (koq.d(d, 1)) {
                String c = d.get(0).c();
                String c2 = d.get(1).c();
                LogUtil.a(TAG, "initViewData guideTipFirst:", c, " guideTipSecond:", c2);
                this.mGuidePrompt.setText(dcx.d(this.mProductId, c) + System.lineSeparator() + dcx.d(this.mProductId, c2));
            } else {
                LogUtil.b(TAG, "initViewData out of bindGuideList bound");
            }
        } else {
            LogUtil.h(TAG, "initViewData mProductInfo is null");
        }
        showGuideImgAnimation();
    }

    private void initView() {
        this.mGuidePrompt = (HealthTextView) nsy.cMd_(this.child, R.id.wifi_device_bind_guid_tv);
        this.mGuideImg = (ImageView) nsy.cMd_(this.child, R.id.wifi_device_bind_guide_img);
        LinearLayout linearLayout = (LinearLayout) nsy.cMd_(this.child, R.id.wifi_device_bind_cancle);
        LinearLayout linearLayout2 = (LinearLayout) nsy.cMd_(this.child, R.id.wifi_device_bind_next);
        linearLayout.setOnClickListener(this);
        linearLayout2.setOnClickListener(this);
        this.mNextImage = (ImageView) nsy.cMd_(this.child, R.id.wifi_device_guide_next_img);
        this.mCancelImage = (ImageView) nsy.cMd_(this.child, R.id.wifi_device_guide_cancle_img);
    }

    private void onNextClick() {
        if (!this.mIsStartAnim && this.mMode != 2) {
            int i = this.mBindCount + 1;
            this.mBindCount = i;
            if (i >= this.mProductInfo.d().size()) {
                LogUtil.a(TAG, "onNextClick mProductId: ", CommonUtil.l(this.mProductId));
                gotoNextView();
                return;
            } else {
                this.mGuidePrompt.setText(dcx.d(this.mProductId, this.mProductInfo.d().get(this.mBindCount).c()));
                this.mGuideImg.setImageBitmap(dcx.TK_(dcq.b().a(this.mProductId, this.mProductInfo.d().get(this.mBindCount).e())));
                return;
            }
        }
        gotoNextView();
    }

    private void gotoNextView() {
        LogUtil.a(TAG, "gotoNextView() mode: ", Integer.valueOf(this.mMode));
        Bundle bundle = new Bundle();
        bundle.putString("productId", this.mProductId);
        bundle.putString("outhName", this.mOuthName);
        bundle.putString("outhPd", this.mOuthPwd);
        bundle.putParcelable("commonDeviceInfo", this.mDeviceInfo);
        bundle.putInt("config_mode", this.mMode);
        if (this.mMode == 1) {
            WiFiDeviceBindResultFragment wiFiDeviceBindResultFragment = new WiFiDeviceBindResultFragment();
            wiFiDeviceBindResultFragment.setArguments(bundle);
            switchFragment(wiFiDeviceBindResultFragment);
        } else {
            WiFiDeviceScanFragment wiFiDeviceScanFragment = new WiFiDeviceScanFragment();
            wiFiDeviceScanFragment.setArguments(bundle);
            switchFragment(wiFiDeviceScanFragment);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.wifi_device_bind_next) {
            onNextClick();
        } else if (view.getId() == R.id.wifi_device_bind_cancle) {
            this.mainActivity.onBackPressed();
        } else {
            LogUtil.c(TAG, "onClick else");
        }
        ViewClickInstrumentation.clickOnView(view);
    }
}
