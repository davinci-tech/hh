package com.huawei.health.ecologydevice.ui.measure.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.core.content.ContextCompat;
import com.huawei.health.R;
import com.huawei.health.ecologydevice.ui.BaseFragment;
import com.huawei.health.socialshare.api.SocialShareCenterApi;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.healthcloud.plugintrack.ui.fragment.SkippingPerformanceFrag;
import com.huawei.healthcloud.plugintrack.ui.view.SkippingPerformanceView;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.up.api.UpApi;
import com.huawei.up.model.UserInfomation;
import defpackage.fdu;
import defpackage.jdv;
import defpackage.nrf;
import defpackage.nrh;
import defpackage.nsf;
import defpackage.nsy;
import health.compact.a.LanguageUtil;
import health.compact.a.Services;
import health.compact.a.Utils;

/* loaded from: classes3.dex */
public class RopePerformanceBestFragment extends BaseFragment {
    private static final String TAG = "RopePerformanceBestFragment";
    private Bundle mBundle;
    private String mCustomTitle;
    private float[] mHistoryPercents;
    private float[] mHistoryScores;
    private float[] mPercents;
    private View mPerformanceLayout;
    private float[] mScores;
    private String mTitle;

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        this.mBundle = arguments;
        if (arguments != null) {
            this.mTitle = arguments.getString("skipping_performance_title");
            this.mCustomTitle = getString(R.string._2130847661_res_0x7f0227ad);
            this.mHistoryScores = this.mBundle.getFloatArray("skipping_performance_history_value");
            this.mHistoryPercents = this.mBundle.getFloatArray("skipping_performance_history_rank");
            if (this.mTitle.equals(getString(R.string._2130847656_res_0x7f0227a8))) {
                this.mCustomTitle = getString(R.string._2130847662_res_0x7f0227ae);
                this.mScores = this.mBundle.getFloatArray("skipping_performance_value");
                this.mPercents = this.mBundle.getFloatArray("skipping_performance_rank");
                return;
            }
            return;
        }
        LogUtil.h(TAG, "onCreate bundle is null");
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        LogUtil.a(TAG, "onCreateView");
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        if (layoutInflater == null) {
            LogUtil.h(TAG, "onCreateView inflater is null!");
            return onCreateView;
        }
        View inflate = layoutInflater.inflate(R.layout.fragment_rope_performance_best, viewGroup, false);
        if (inflate != null && onCreateView != null) {
            initView(inflate);
            if (onCreateView instanceof ViewGroup) {
                ((ViewGroup) onCreateView).addView(inflate);
            }
        } else {
            LogUtil.h(TAG, "onCreateView child is null or view is null!");
        }
        return onCreateView;
    }

    private void initView(View view) {
        this.mCustomTitleBar.setTitleBarBackgroundColor(ContextCompat.getColor(requireContext(), R.color._2131296690_res_0x7f0901b2));
        setTitle(this.mCustomTitle);
        if (this.mHistoryScores == null || this.mHistoryPercents == null) {
            view.findViewById(R.id.rope_performance_no_data_layout).setVisibility(0);
            view.findViewById(R.id.rope_performance_container).setVisibility(8);
        } else {
            initShareView(view);
            initTitleBar();
            getChildFragmentManager().beginTransaction().add(R.id.rope_performance_container, SkippingPerformanceFrag.class, this.mBundle, "Track_SkippingPerformanceFrag").commit();
        }
    }

    private void initTitleBar() {
        this.mCustomTitleBar.setRightButtonVisibility(0);
        if (LanguageUtil.bc(getContext())) {
            this.mCustomTitleBar.setRightButtonDrawable(ContextCompat.getDrawable(requireContext(), R.drawable._2131430036_res_0x7f0b0a94), nsf.h(R.string._2130850657_res_0x7f023361));
        } else {
            this.mCustomTitleBar.setRightButtonDrawable(ContextCompat.getDrawable(requireContext(), R.drawable._2131430035_res_0x7f0b0a93), nsf.h(R.string._2130850657_res_0x7f023361));
        }
        this.mCustomTitleBar.setRightButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.RopePerformanceBestFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RopePerformanceBestFragment.this.m321x66cf2745(view);
            }
        });
    }

    /* renamed from: lambda$initTitleBar$0$com-huawei-health-ecologydevice-ui-measure-fragment-RopePerformanceBestFragment, reason: not valid java name */
    /* synthetic */ void m321x66cf2745(View view) {
        shareData();
        ViewClickInstrumentation.clickOnView(view);
    }

    private void initShareView(View view) {
        this.mPerformanceLayout = nsy.cMd_(view, R.id.rope_performance_layout);
        SkippingPerformanceView skippingPerformanceView = (SkippingPerformanceView) nsy.cMd_(view, R.id.rope_performance_view);
        initUserInfo(view);
        skippingPerformanceView.setTitle(this.mTitle);
        skippingPerformanceView.a();
        skippingPerformanceView.setData(this.mHistoryScores, this.mHistoryPercents, true);
        skippingPerformanceView.setData(this.mScores, this.mPercents, false);
    }

    private void shareData() {
        LogUtil.a(TAG, "shareData");
        Bitmap bGg_ = jdv.bGg_(this.mPerformanceLayout);
        if (bGg_ == null) {
            LogUtil.h(TAG, "screenCut is null");
            nrh.e(requireContext(), R.string._2130842310_res_0x7f0212c6);
            return;
        }
        try {
            fdu requestShareContent = requestShareContent(bGg_);
            requestShareContent.c(false);
            ((SocialShareCenterApi) Services.c("PluginSocialShare", SocialShareCenterApi.class)).exeShare(requestShareContent, getContext());
        } catch (OutOfMemoryError unused) {
            nrh.e(requireContext(), R.string._2130842310_res_0x7f0212c6);
            LogUtil.h(TAG, "shareData outOfMemoryError");
        }
    }

    private fdu requestShareContent(Bitmap bitmap) {
        fdu fduVar = new fdu(1);
        fduVar.awp_(bitmap);
        fduVar.c((String) null);
        fduVar.a((String) null);
        fduVar.f(null);
        fduVar.b(AnalyticsValue.HEALTH_ROPE_DEVICE_DATA_2170022.value());
        fduVar.e(1);
        fduVar.i(false);
        fduVar.b(1);
        return fduVar;
    }

    private void initUserInfo(View view) {
        String str;
        String str2;
        UserInfomation userInfo = ((UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class)).getUserInfo();
        HealthTextView healthTextView = (HealthTextView) nsy.cMd_(view, R.id.rope_performance_user_name);
        if (userInfo != null) {
            str = userInfo.getName();
            str2 = userInfo.getPicPath();
        } else {
            str = null;
            str2 = null;
        }
        if (TextUtils.isEmpty(str)) {
            str = new UpApi(BaseApplication.getContext()).getAccountName();
            if (TextUtils.isEmpty(str)) {
                healthTextView.setVisibility(8);
            } else {
                healthTextView.setText(str);
            }
        } else {
            healthTextView.setText(str);
        }
        ImageView imageView = (ImageView) nsy.cMd_(view, R.id.rope_performance_user_image);
        if (!TextUtils.isEmpty(str2)) {
            Bitmap cIe_ = nrf.cIe_(getContext(), str2);
            if (cIe_ != null) {
                imageView.setImageBitmap(cIe_);
            } else {
                LogUtil.h(TAG, "initUserInfo bitmap is null!");
            }
        } else {
            LogUtil.h(TAG, "initUserInfo headImgPath is null!");
        }
        if (Utils.o() && TextUtils.isEmpty(str)) {
            imageView.setVisibility(8);
        }
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment
    public boolean onBackPressed() {
        LogUtil.a(TAG, "onBackPressed");
        popupFragment(RopePerformanceHistoryFragment.class);
        return false;
    }
}
