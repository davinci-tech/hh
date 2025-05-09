package com.huawei.healthcloud.plugintrack.ui.fragment;

import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.LinearLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import com.huawei.health.R;
import com.huawei.health.sportservice.SportDataOutputApi;
import com.huawei.healthcloud.plugintrack.ui.activity.SportBaseActivity;
import com.huawei.healthcloud.plugintrack.ui.fragment.SportExamIntroduceFragment;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$drawable;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.illustration.IllustrationView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import defpackage.gyr;
import defpackage.nsf;
import health.compact.a.LanguageUtil;
import health.compact.a.Services;

/* loaded from: classes4.dex */
public abstract class SportExamIntroduceFragment extends BaseSportingFragment {
    private static final String TAG = "Track_SportExamIntroduceFragment";
    private CustomTitleBar mCustomTitleBar;
    private HealthTextView mIntroduceDesc;
    private IllustrationView mIntroduceFirst;
    private LinearLayout mIntroduceLayout;
    private IllustrationView mIntroduceSecond;
    private HealthButton mKnownButton;

    protected abstract void enableCanStart();

    protected abstract String getFirstTips();

    protected abstract String getFistBitmapName();

    @Override // com.huawei.healthcloud.plugintrack.ui.fragment.BaseSportingFragment
    public int getLayoutId() {
        return R.layout.skipping_sport_intro_fragment;
    }

    protected abstract String getSecondBitmapName();

    protected abstract String getSecondTips();

    protected abstract String getTitle();

    @Override // com.huawei.healthcloud.plugintrack.ui.fragment.BaseSportingFragment
    public void initViewModel() {
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.fragment.BaseSportingFragment
    public void initView(View view) {
        this.mCustomTitleBar = (CustomTitleBar) view.findViewById(R.id.title_bar);
        this.mIntroduceFirst = (IllustrationView) view.findViewById(R.id.skipping_intro_1);
        this.mIntroduceSecond = (IllustrationView) view.findViewById(R.id.skipping_intro_2);
        this.mKnownButton = (HealthButton) view.findViewById(R.id.known_button);
        this.mIntroduceLayout = (LinearLayout) view.findViewById(R.id.skipping_intro_layout);
        HealthTextView healthTextView = (HealthTextView) view.findViewById(R.id.skipping_intro_desc);
        this.mIntroduceDesc = healthTextView;
        healthTextView.setText(getTitle());
        this.mKnownButton.setOnClickListener(new View.OnClickListener() { // from class: hir
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                SportExamIntroduceFragment.this.m538xc63cb4dd(view2);
            }
        });
        this.mCustomTitleBar.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: hin
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                SportExamIntroduceFragment.this.m539xf9eadf9e(view2);
            }
        });
    }

    /* renamed from: lambda$initView$0$com-huawei-healthcloud-plugintrack-ui-fragment-SportExamIntroduceFragment, reason: not valid java name */
    public /* synthetic */ void m538xc63cb4dd(View view) {
        FragmentActivity activity = getActivity();
        if (activity instanceof SportBaseActivity) {
            ((SportBaseActivity) activity).e(0);
            enableCanStart();
        } else if (activity != null) {
            activity.finish();
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    /* renamed from: lambda$initView$1$com-huawei-healthcloud-plugintrack-ui-fragment-SportExamIntroduceFragment, reason: not valid java name */
    public /* synthetic */ void m539xf9eadf9e(View view) {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            SportDataOutputApi sportDataOutputApi = (SportDataOutputApi) Services.a("SportService", SportDataOutputApi.class);
            if (sportDataOutputApi != null) {
                LogUtil.a(TAG, "sportDataOutputApi != null");
                sportDataOutputApi.destroyModel();
            }
            activity.onBackPressed();
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.fragment.BaseSportingFragment, com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void setUserVisibleHint(boolean z) {
        if (z && getActivity() != null && getActivity().getWindow() != null) {
            getActivity().getWindow().clearFlags(1024);
        }
        super.setUserVisibleHint(z);
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.fragment.BaseSportingFragment
    public void initData() {
        Bitmap aWP_ = gyr.e().aWP_(getFistBitmapName(), "webp");
        if (aWP_ != null) {
            this.mIntroduceFirst.setIllustration(aWP_);
        }
        Bitmap aWP_2 = gyr.e().aWP_(getSecondBitmapName(), "webp");
        if (aWP_2 != null) {
            this.mIntroduceSecond.setIllustration(aWP_2);
        }
        refreshView();
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        refreshView();
    }

    private void refreshView() {
        CustomTitleBar customTitleBar = this.mCustomTitleBar;
        if (customTitleBar != null) {
            customTitleBar.setTitleText(getString(R.string._2130839970_res_0x7f0209a2));
            if (LanguageUtil.bc(getContext())) {
                this.mCustomTitleBar.setLeftButtonDrawable(ContextCompat.getDrawable(getContext(), R$drawable.health_navbar_rtl_back_selector), nsf.h(R$string.accessibility_go_back));
            } else {
                this.mCustomTitleBar.setLeftButtonDrawable(ContextCompat.getDrawable(getContext(), R$drawable.health_navbar_back_selector), nsf.h(R$string.accessibility_go_back));
            }
        }
        IllustrationView illustrationView = this.mIntroduceFirst;
        if (illustrationView != null) {
            illustrationView.setSubHeader(getFirstTips());
        }
        IllustrationView illustrationView2 = this.mIntroduceSecond;
        if (illustrationView2 != null) {
            illustrationView2.setSubHeader(getSecondTips());
        }
        HealthButton healthButton = this.mKnownButton;
        if (healthButton != null) {
            healthButton.setText(getString(R.string._2130839504_res_0x7f0207d0));
        }
        LinearLayout linearLayout = this.mIntroduceLayout;
        if (linearLayout != null) {
            linearLayout.setVisibility(0);
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.fragment.BaseSportingFragment
    public String getLogTag() {
        return TAG;
    }
}
