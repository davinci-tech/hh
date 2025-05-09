package com.huawei.healthcloud.plugintrack.ui.fragment;

import android.content.res.Configuration;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Pair;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.health.R;
import com.huawei.health.basesport.sportui.OnEndCountdownListener;
import com.huawei.health.sport.utils.SportSupportUtil;
import com.huawei.healthcloud.plugintrack.ui.activity.IOnBackPressed;
import com.huawei.healthcloud.plugintrack.ui.activity.SportBaseActivity;
import com.huawei.healthcloud.plugintrack.ui.fragment.BaseSportExamFragment;
import com.huawei.healthcloud.plugintrack.ui.view.CanvasChangeView;
import com.huawei.healthcloud.plugintrack.ui.view.CircleProgressButton;
import com.huawei.healthcloud.plugintrack.ui.view.SquareProgress;
import com.huawei.healthcloud.plugintrack.ui.view.ToolsLayout;
import com.huawei.healthcloud.plugintrack.ui.view.glrender.CameraGlView;
import com.huawei.healthcloud.plugintrack.ui.viewholder.CountdownDialog;
import com.huawei.healthcloud.plugintrack.ui.viewmodel.SportExamViewModel;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.imageview.HealthImageView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import defpackage.gso;
import defpackage.hjl;
import defpackage.hjx;
import defpackage.mwo;
import defpackage.nsf;
import defpackage.nsn;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

/* loaded from: classes4.dex */
public abstract class BaseSportExamFragment extends BaseSportingFragment implements IOnBackPressed {
    private static final int CLICK_INTERVAL = 600;
    private static final int MESSAGE_SEND_RESET_VIEW = 10001;
    protected HealthImageView mBackImg;
    private View mContentView;
    private CountdownDialog mCountdownDialog;
    protected View mCountdownView;
    protected View mDataPanelLayout;
    protected HealthTextView mPortraitPreTipsTwo;
    protected HealthTextView mPreTips;
    protected CameraGlView mRenderView;
    protected CanvasChangeView mSportDataView;
    protected ViewGroup mSportPreView;
    private int mSportType;
    protected SquareProgress mSquareProgress;
    protected ImageButton mSwitchCameraInSportButton;
    protected HealthTextView mSwitchCameraPopText;
    protected ImageButton mSwitchCameraPreSportButton;
    private LinearLayout mSwitchScreenBg;
    protected CustomTitleBar mTitleBar;
    protected ViewGroup mToIntroduceButtonLayout;
    protected ToolsLayout mToolsLayout;
    protected SportExamViewModel mViewModel;
    protected boolean mIsStartTheTest = false;
    private final Handler mHandler = new c(Looper.getMainLooper(), this);

    protected abstract void dealProgressEnd();

    @Override // com.huawei.healthcloud.plugintrack.ui.fragment.BaseSportingFragment
    public abstract int getLayoutId();

    @Override // com.huawei.healthcloud.plugintrack.ui.fragment.BaseSportingFragment
    public abstract String getLogTag();

    protected abstract SportExamViewModel getViewModel();

    protected abstract boolean isSupportLandScreen();

    @Override // com.huawei.healthcloud.plugintrack.ui.fragment.BaseSportingFragment
    public void initView(View view) {
        if (view == null) {
            LogUtil.h(getLogTag(), "view == null");
            return;
        }
        this.mContentView = view;
        BaseActivity.cancelLayoutById(view);
        Pair<Integer, Integer> safeRegionWidth = BaseActivity.getSafeRegionWidth();
        view.setPaddingRelative(((Integer) safeRegionWidth.first).intValue(), view.getPaddingTop(), ((Integer) safeRegionWidth.second).intValue(), view.getPaddingBottom());
        initSportPreView(view);
        initSportDataView(view);
        this.mRenderView = (CameraGlView) view.findViewById(R.id.camera_pre_view);
        initCameraView(view);
        initCountDownDialog(view);
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (isSupportLandScreen()) {
            switchScreenTips();
            LogUtil.a(getTag(), "onConfigurationChanged:,", Integer.valueOf(getResources().getConfiguration().orientation));
            setSurfaceRotation();
        }
    }

    public void setSurfaceRotation() {
        SportExamViewModel sportExamViewModel;
        if (getActivity() == null || (sportExamViewModel = this.mViewModel) == null) {
            return;
        }
        if (!sportExamViewModel.isAlreadyInit()) {
            ReleaseLogUtil.e(getTag(), "changeOrientation NO isAlreadyInit");
            return;
        }
        Display defaultDisplay = getActivity().getWindowManager().getDefaultDisplay();
        if (defaultDisplay != null) {
            int rotation = defaultDisplay.getRotation();
            ReleaseLogUtil.e(getTag(), "setSurfaceRotation result:", Integer.valueOf(mwo.d().setSurfaceRotation(rotation)), "rotation:", Integer.valueOf(rotation));
        }
    }

    private void initSportDataView(View view) {
        int r;
        this.mSportDataView = (CanvasChangeView) view.findViewById(R.id.sport_data_view);
        this.mDataPanelLayout = view.findViewById(R.id.data_panel_layout);
        if (isSupportLandScreen()) {
            r = getResources().getDimensionPixelSize(R.dimen._2131362922_res_0x7f0a046a);
        } else {
            r = nsn.r(view.getContext());
        }
        View view2 = this.mDataPanelLayout;
        view2.setPaddingRelative(view2.getPaddingStart(), r, this.mDataPanelLayout.getPaddingEnd(), this.mDataPanelLayout.getPaddingBottom());
        if (isSupportLandScreen()) {
            this.mSwitchScreenBg = (LinearLayout) view.findViewById(R.id.switch_screen_bg);
        }
        this.mToolsLayout = (ToolsLayout) view.findViewById(R.id.sport_control_layout);
    }

    private void initCountDownDialog(View view) {
        CountdownDialog countdownDialog = new CountdownDialog(getContext(), view);
        this.mCountdownDialog = countdownDialog;
        countdownDialog.setTimeStart(3);
        this.mCountdownDialog.addEndCountdown(new OnEndCountdownListener() { // from class: hgo
            @Override // com.huawei.health.basesport.sportui.OnEndCountdownListener
            public final void endCountdown() {
                BaseSportExamFragment.this.endCountDown();
            }
        });
        View findViewById = view.findViewById(R.id.track_main_page_perm_bg);
        this.mCountdownView = findViewById;
        findViewById.setBackgroundResource(R.color._2131299296_res_0x7f090be0);
    }

    public void endCountDown() {
        SportExamViewModel sportExamViewModel = this.mViewModel;
        if (sportExamViewModel != null) {
            sportExamViewModel.onStartSport();
        }
    }

    private void initSportPreView(View view) {
        this.mSportPreView = (ViewGroup) view.findViewById(R.id.pre_sport_layout);
        CustomTitleBar customTitleBar = (CustomTitleBar) view.findViewById(R.id.title_bar);
        this.mTitleBar = customTitleBar;
        customTitleBar.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: hgs
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                BaseSportExamFragment.this.m534x152adae2(view2);
            }
        });
        this.mTitleBar.setLeftButtonDrawable(ContextCompat.getDrawable(view.getContext(), R.drawable._2131432090_res_0x7f0b129a), nsf.h(R.string._2130850617_res_0x7f023339));
        SquareProgress squareProgress = (SquareProgress) view.findViewById(R.id.pre_progress);
        this.mSquareProgress = squareProgress;
        squareProgress.setPaddingRelative(squareProgress.getPaddingStart(), this.mSquareProgress.getPaddingTop(), this.mSquareProgress.getPaddingEnd(), this.mSquareProgress.getPaddingBottom());
        this.mPreTips = (HealthTextView) view.findViewById(R.id.use_tips_1);
        this.mPortraitPreTipsTwo = (HealthTextView) view.findViewById(R.id.use_tips_2);
        ViewGroup viewGroup = (ViewGroup) view.findViewById(R.id.how_to_use_layout);
        this.mToIntroduceButtonLayout = viewGroup;
        viewGroup.setOnClickListener(new View.OnClickListener() { // from class: hgt
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                BaseSportExamFragment.this.m535x4e0b3b81(view2);
            }
        });
        HealthImageView healthImageView = (HealthImageView) view.findViewById(R.id.back_img);
        this.mBackImg = healthImageView;
        if (healthImageView != null) {
            healthImageView.setOnClickListener(new View.OnClickListener() { // from class: hgr
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    BaseSportExamFragment.this.m536x86eb9c20(view2);
                }
            });
        }
    }

    /* renamed from: lambda$initSportPreView$0$com-huawei-healthcloud-plugintrack-ui-fragment-BaseSportExamFragment, reason: not valid java name */
    public /* synthetic */ void m534x152adae2(View view) {
        onTitleBarBackBtnClicked();
        ViewClickInstrumentation.clickOnView(view);
    }

    /* renamed from: lambda$initSportPreView$1$com-huawei-healthcloud-plugintrack-ui-fragment-BaseSportExamFragment, reason: not valid java name */
    public /* synthetic */ void m535x4e0b3b81(View view) {
        startUserGuidance();
        ViewClickInstrumentation.clickOnView(view);
    }

    /* renamed from: lambda$initSportPreView$2$com-huawei-healthcloud-plugintrack-ui-fragment-BaseSportExamFragment, reason: not valid java name */
    public /* synthetic */ void m536x86eb9c20(View view) {
        onTitleBarBackBtnClicked();
        ViewClickInstrumentation.clickOnView(view);
    }

    public void startUserGuidance() {
        FragmentActivity activity = getActivity();
        if (activity instanceof SportBaseActivity) {
            ((SportBaseActivity) activity).e(1);
        }
    }

    private void onTitleBarBackBtnClicked() {
        FragmentActivity activity = getActivity();
        if (activity == null) {
            LogUtil.b(getTag(), "onTitleBarBackBtnClicked activity is null");
        } else {
            onBackPressed();
            activity.finish();
        }
    }

    public void onBackPressed() {
        removePreProgressObservers();
    }

    private void removePreProgressObservers() {
        SportExamViewModel sportExamViewModel = this.mViewModel;
        if (sportExamViewModel == null) {
            LogUtil.h(getTag(), "removePreProgressObservers, mViewModel is null");
        } else {
            sportExamViewModel.removeSportDataObservers("preSportProgress", this);
        }
    }

    private void initCameraView(View view) {
        this.mSwitchCameraPreSportButton = (ImageButton) view.findViewById(R.id.switch_camera_pre_sport_button);
        this.mSwitchCameraInSportButton = (ImageButton) view.findViewById(R.id.switch_camera_in_sport_button);
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.fragment.BaseSportingFragment
    public void initViewModel() {
        SportExamViewModel viewModel = getViewModel();
        this.mViewModel = viewModel;
        int sportType = viewModel.getSportType();
        this.mSportType = sportType;
        if (sportType != 400002) {
            this.mCountdownDialog.enableGo();
        }
        if (!this.mViewModel.getIsFirstInit()) {
            this.mViewModel.enableCanStart(true);
        }
        this.mViewModel.observeSportLifeCycle(getLogTag(), this);
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.fragment.BaseSportingFragment
    public void initData() {
        this.mRenderView.setRadio(0.4f);
        this.mRenderView.setIsOptimalSize(true);
        this.mRenderView.setOnPreviewListener(this.mViewModel);
        this.mRenderView.c(true);
        setupSwitchCameraButton();
        initFirstPop();
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        if (getActivity() != null) {
            getActivity().getWindow().addFlags(128);
            getActivity().getWindow().addFlags(1024);
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onPause() {
        LogUtil.a(getLogTag(), "onPause()");
        super.onPause();
        if (getActivity() != null) {
            getActivity().getWindow().clearFlags(128);
        }
    }

    private void setupSwitchCameraButton() {
        boolean c2 = SportSupportUtil.c();
        boolean b = SportSupportUtil.b();
        if (c2 && b) {
            View.OnClickListener onClickListener = new View.OnClickListener() { // from class: hgm
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    BaseSportExamFragment.this.m537x5adf9435(view);
                }
            };
            this.mSwitchCameraPreSportButton.setOnClickListener(onClickListener);
            this.mSwitchCameraInSportButton.setOnClickListener(onClickListener);
            adjustSwitchCameraPreSportButtonPositionIfNeeded();
            return;
        }
        this.mSwitchCameraInSportButton.setVisibility(8);
        this.mSwitchCameraPreSportButton.setVisibility(8);
        if (c2) {
            switchCamera();
        }
    }

    /* renamed from: lambda$setupSwitchCameraButton$3$com-huawei-healthcloud-plugintrack-ui-fragment-BaseSportExamFragment, reason: not valid java name */
    public /* synthetic */ void m537x5adf9435(View view) {
        onSwitchCameraButtonClicked();
        ViewClickInstrumentation.clickOnView(view);
    }

    private void adjustSwitchCameraPreSportButtonPositionIfNeeded() {
        this.mSwitchCameraPreSportButton.post(new Runnable() { // from class: hgq
            @Override // java.lang.Runnable
            public final void run() {
                BaseSportExamFragment.this.m532xd575c89c();
            }
        });
    }

    /* renamed from: lambda$adjustSwitchCameraPreSportButtonPositionIfNeeded$4$com-huawei-healthcloud-plugintrack-ui-fragment-BaseSportExamFragment, reason: not valid java name */
    public /* synthetic */ void m532xd575c89c() {
        hjl.bgI_(this.mSwitchCameraPreSportButton, this.mToIntroduceButtonLayout);
    }

    private void onSwitchCameraButtonClicked() {
        if (nsn.a(600)) {
            LogUtil.b(getTag(), "CLICK IS FAST");
        } else {
            switchCamera();
            this.mSwitchCameraPopText.setVisibility(8);
        }
    }

    private void switchCamera() {
        boolean z = !this.mRenderView.c();
        this.mRenderView.d(z);
        this.mViewModel.switchCamera(z);
    }

    private void initFirstPop() {
        this.mSwitchCameraPopText = (HealthTextView) this.mContentView.findViewById(R.id.switch_camera_pre_sport_pop);
        if (!SportSupportUtil.c() || !SportSupportUtil.b()) {
            this.mSwitchCameraPopText.setVisibility(8);
        } else if (this.mViewModel.getIsFirstInit()) {
            this.mSwitchCameraPopText.setVisibility(0);
            this.mContentView.setOnClickListener(new View.OnClickListener() { // from class: hgn
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    BaseSportExamFragment.this.m533xca511f30(view);
                }
            });
        } else {
            this.mSwitchCameraPopText.setVisibility(8);
        }
    }

    /* renamed from: lambda$initFirstPop$5$com-huawei-healthcloud-plugintrack-ui-fragment-BaseSportExamFragment, reason: not valid java name */
    public /* synthetic */ void m533xca511f30(View view) {
        this.mSwitchCameraPopText.setVisibility(8);
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onCountDown() {
        if (!this.mIsStartTheTest) {
            this.mCountdownView.setVisibility(0);
        }
        this.mCountdownDialog.startCountdown();
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.fragment.BaseSportingFragment, com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void setUserVisibleHint(boolean z) {
        if (z && getActivity() != null) {
            getActivity().getWindow().setFlags(16778240, 16778240);
            if (isSupportLandScreen()) {
                getActivity().setRequestedOrientation(4);
            }
        }
        super.setUserVisibleHint(z);
        SportExamViewModel sportExamViewModel = this.mViewModel;
        if (sportExamViewModel != null) {
            sportExamViewModel.enableCanStart(z);
        }
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onPauseSport() {
        LogUtil.a(getTag(), "onPauseSport");
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onStartSport() {
        LogUtil.a(getTag(), "onStartSport");
        this.mIsStartTheTest = true;
        startDataSport();
    }

    protected void startDataSport() {
        this.mCountdownView.setVisibility(8);
        this.mSportDataView.setVisibility(0);
        this.mDataPanelLayout.startAnimation(hjx.bhc_());
        this.mDataPanelLayout.setVisibility(0);
        this.mToolsLayout.setVisibility(0);
        initControlButton();
    }

    private void initControlButton() {
        CircleProgressButton circleProgressButton = this.mToolsLayout.getCircleProgressButton();
        ToolsLayout toolsLayout = this.mToolsLayout;
        toolsLayout.setShowOrHideView(toolsLayout.getBottomPanel());
        circleProgressButton.a(new CircleProgressButton.CircleProcessListener() { // from class: com.huawei.healthcloud.plugintrack.ui.fragment.BaseSportExamFragment.2
            @Override // com.huawei.healthcloud.plugintrack.ui.view.CircleProgressButton.CircleProcessListener
            public void onCancel() {
            }

            @Override // com.huawei.healthcloud.plugintrack.ui.view.CircleProgressButton.CircleProcessListener
            public void onStarted() {
            }

            @Override // com.huawei.healthcloud.plugintrack.ui.view.CircleProgressButton.CircleProcessListener
            public void onFinished() {
                BaseSportExamFragment.this.clickStopButton();
            }
        });
        this.mToolsLayout.c();
    }

    public void clickStopButton() {
        this.mToolsLayout.setVisibility(8);
        SportExamViewModel sportExamViewModel = this.mViewModel;
        if (sportExamViewModel != null) {
            sportExamViewModel.m134x32b3e3a1();
        }
        this.mSwitchCameraPopText.setVisibility(8);
        if (getActivity() == null || this.mViewModel.getSportType() == 400002) {
            return;
        }
        getActivity().finish();
    }

    protected void dealPreProgress(int i) {
        this.mSquareProgress.setProgress(i);
        if (i >= 100) {
            AnimationSet bhk_ = hjx.bhk_();
            bhk_.setAnimationListener(new Animation.AnimationListener() { // from class: com.huawei.healthcloud.plugintrack.ui.fragment.BaseSportExamFragment.1
                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationRepeat(Animation animation) {
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationStart(Animation animation) {
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationEnd(Animation animation) {
                    BaseSportExamFragment.this.dealProgressEnd();
                }
            });
            this.mSportPreView.startAnimation(bhk_);
        }
    }

    protected void switchScreenTips() {
        if (getActivity() != null && getResources().getConfiguration().orientation == 1) {
            this.mSwitchScreenBg.setVisibility(0);
            this.mSportPreView.setVisibility(8);
            this.mSportDataView.setVisibility(8);
        } else {
            this.mSwitchScreenBg.setVisibility(8);
            this.mSportPreView.setVisibility(0);
            if (this.mIsStartTheTest) {
                this.mSportDataView.setVisibility(0);
            }
        }
        if (this.mIsStartTheTest) {
            this.mSportPreView.setVisibility(8);
        }
    }

    public void finish() {
        if (getActivity() != null) {
            getActivity().finish();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        if (this.mIsStartTheTest) {
            return;
        }
        sendSportResult(-1L);
    }

    public void sendSportResult(long j) {
        LogUtil.a(getTag(), "sendSportResult");
        if (!this.mIsStartTheTest) {
            j = -1;
        }
        gso.e().b(this.mSportType, j);
    }

    public void dealPreStartSportTips(int i, int i2) {
        ViewGroup viewGroup = this.mSportPreView;
        if (viewGroup == null || viewGroup.getVisibility() != 0) {
            return;
        }
        if (i == -10) {
            dealPreStartLightTips(i2);
        } else if (i == -11) {
            dealManyPeopleByPreStart(i2);
        }
    }

    private void dealPreStartLightTips(int i) {
        if (isSupportLandScreen()) {
            this.mPreTips.setText(R.string._2130847756_res_0x7f02280c);
        } else {
            this.mPortraitPreTipsTwo.setVisibility(8);
            this.mPreTips.setText(R.string._2130847816_res_0x7f022848);
            if (this.mPreTips.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.mPreTips.getLayoutParams();
                marginLayoutParams.topMargin = nsn.r(this.mSportPreView.getContext()) * 2;
                this.mPreTips.setLayoutParams(marginLayoutParams);
            }
        }
        this.mHandler.removeMessages(10001);
        Message obtainMessage = this.mHandler.obtainMessage();
        obtainMessage.what = 10001;
        obtainMessage.obj = Integer.valueOf(i);
        this.mHandler.sendMessageDelayed(obtainMessage, 6000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initPreViewDefaultTips(int i, int i2) {
        HealthTextView healthTextView = this.mPreTips;
        if (healthTextView == null) {
            LogUtil.h(getTag(), "initPreViewDefaultTips mPreTips == null");
            return;
        }
        healthTextView.setText(i);
        this.mPreTips.setVisibility(0);
        if (isSupportLandScreen() || this.mPortraitPreTipsTwo == null) {
            return;
        }
        if (this.mPreTips.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.mPreTips.getLayoutParams();
            marginLayoutParams.topMargin = nsn.r(this.mSportPreView.getContext());
            this.mPreTips.setLayoutParams(marginLayoutParams);
        }
        this.mPortraitPreTipsTwo.setVisibility(0);
        this.mPortraitPreTipsTwo.setText(i2);
    }

    private void dealManyPeopleByPreStart(int i) {
        if (isSupportLandScreen()) {
            this.mPreTips.setText(R.string._2130847755_res_0x7f02280b);
        } else {
            this.mPortraitPreTipsTwo.setText(R.string._2130847755_res_0x7f02280b);
        }
        this.mHandler.removeMessages(10001);
        Message obtainMessage = this.mHandler.obtainMessage();
        obtainMessage.what = 10001;
        obtainMessage.obj = Integer.valueOf(i);
        this.mHandler.sendMessageDelayed(obtainMessage, 3000L);
    }

    class c extends BaseHandler<BaseSportExamFragment> {
        c(Looper looper, BaseSportExamFragment baseSportExamFragment) {
            super(looper, baseSportExamFragment);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: bdT_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(BaseSportExamFragment baseSportExamFragment, Message message) {
            if (message.what == 10001) {
                Object obj = message.obj;
                if (obj instanceof Integer) {
                    BaseSportExamFragment.this.initPreViewDefaultTips(((Integer) obj).intValue(), R.string._2130839975_res_0x7f0209a7);
                }
            }
        }
    }
}
