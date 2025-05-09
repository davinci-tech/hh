package com.huawei.healthcloud.plugintrack.ui.fragment;

import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.LinearLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import com.huawei.health.R;
import com.huawei.health.sportservice.SportDataOutputApi;
import com.huawei.healthcloud.plugintrack.ui.activity.SportBaseActivity;
import com.huawei.healthcloud.plugintrack.ui.fragment.SkippingIntroduceFragment;
import com.huawei.healthcloud.plugintrack.ui.viewmodel.SkippingViewModel;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$drawable;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.illustration.IllustrationView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import defpackage.gyn;
import defpackage.nsf;
import health.compact.a.LanguageUtil;
import health.compact.a.Services;

/* loaded from: classes4.dex */
public class SkippingIntroduceFragment extends BaseSportingFragment {

    /* renamed from: a, reason: collision with root package name */
    private IllustrationView f3730a;
    private CustomTitleBar b;
    private LinearLayout c;
    private IllustrationView d;
    private HealthButton e;
    private SkippingViewModel i;

    @Override // com.huawei.healthcloud.plugintrack.ui.fragment.BaseSportingFragment
    public int getLayoutId() {
        return R.layout.skipping_sport_intro_fragment;
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.fragment.BaseSportingFragment
    public void initView(View view) {
        this.b = (CustomTitleBar) view.findViewById(R.id.title_bar);
        this.d = (IllustrationView) view.findViewById(R.id.skipping_intro_1);
        this.f3730a = (IllustrationView) view.findViewById(R.id.skipping_intro_2);
        this.e = (HealthButton) view.findViewById(R.id.known_button);
        this.c = (LinearLayout) view.findViewById(R.id.skipping_intro_layout);
        this.e.setOnClickListener(new View.OnClickListener() { // from class: hij
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                SkippingIntroduceFragment.this.bfu_(view2);
            }
        });
        this.b.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: hil
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                SkippingIntroduceFragment.this.bfv_(view2);
            }
        });
    }

    public /* synthetic */ void bfu_(View view) {
        FragmentActivity activity = getActivity();
        if (activity instanceof SportBaseActivity) {
            ((SportBaseActivity) activity).e(0);
            if (this.i.a()) {
                this.i.e(true);
            }
        } else {
            activity.finish();
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void bfv_(View view) {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            SportDataOutputApi sportDataOutputApi = (SportDataOutputApi) Services.a("SportService", SportDataOutputApi.class);
            if (sportDataOutputApi != null) {
                LogUtil.a("Track_SkippingIntroduceFragment", "sportDataOutputApi != null");
                sportDataOutputApi.destroyModel();
            }
            activity.onBackPressed();
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.fragment.BaseSportingFragment, com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void setUserVisibleHint(boolean z) {
        if (z && getActivity() != null) {
            getActivity().getWindow().clearFlags(1024);
        }
        super.setUserVisibleHint(z);
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.fragment.BaseSportingFragment
    public void initViewModel() {
        this.i = (SkippingViewModel) new ViewModelProvider(requireActivity()).get(SkippingViewModel.class);
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.fragment.BaseSportingFragment
    public void initData() {
        Bitmap bft_ = bft_("pic_rope_introduce_first");
        if (bft_ != null) {
            this.d.setIllustration(bft_);
        }
        Bitmap bft_2 = bft_("pic_rope_introduce_second");
        if (bft_2 != null) {
            this.f3730a.setIllustration(bft_2);
        }
        b();
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        b();
    }

    private void b() {
        CustomTitleBar customTitleBar = this.b;
        if (customTitleBar != null) {
            customTitleBar.setTitleText(getString(R.string._2130839970_res_0x7f0209a2));
            if (LanguageUtil.bc(getContext())) {
                this.b.setLeftButtonDrawable(ContextCompat.getDrawable(getContext(), R$drawable.health_navbar_rtl_back_selector), nsf.h(R$string.accessibility_go_back));
            } else {
                this.b.setLeftButtonDrawable(ContextCompat.getDrawable(getContext(), R$drawable.health_navbar_back_selector), nsf.h(R$string.accessibility_go_back));
            }
        }
        IllustrationView illustrationView = this.d;
        if (illustrationView != null) {
            illustrationView.setSubHeader(getString(R.string._2130839971_res_0x7f0209a3));
        }
        IllustrationView illustrationView2 = this.f3730a;
        if (illustrationView2 != null) {
            illustrationView2.setSubHeader(getString(R.string._2130839972_res_0x7f0209a4));
        }
        HealthButton healthButton = this.e;
        if (healthButton != null) {
            healthButton.setText(getString(R.string._2130839504_res_0x7f0207d0));
        }
        LinearLayout linearLayout = this.c;
        if (linearLayout != null) {
            linearLayout.setVisibility(this.i.getDataSource() == 5 ? 8 : 0);
        }
    }

    private Bitmap bft_(String str) {
        String b = gyn.d().b(str, "png");
        LogUtil.a("Track_SkippingIntroduceFragment", "getSkipBitmap() imagePath: ", b);
        return gyn.d().aWQ_(b);
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.fragment.BaseSportingFragment
    public String getLogTag() {
        return "Track_SkippingIntroduceFragment";
    }
}
