package com.huawei.ui.commonui.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$color;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.columnsystem.HealthColumnSystem;
import com.huawei.ui.commonui.dialog.AchieveDialogFactory;
import com.huawei.ui.commonui.dialog.BaseAchieveDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.utils.AnimResUtils;
import com.huawei.ui.commonui.utils.ResInfo;
import defpackage.jec;
import defpackage.koq;
import defpackage.nsn;
import health.compact.a.LanguageUtil;
import java.lang.ref.WeakReference;
import java.util.List;

/* loaded from: classes6.dex */
public abstract class BaseAchieveDialog extends QueueDialog implements DialogInterface.OnShowListener, DialogInterface.OnDismissListener {
    private static final boolean IS_LITE_VERSION = false;
    protected static final float RING_EXTRA_BOTTOM_MARGIN_RATIO = 0.06666667f;
    protected static final float RING_EXTRA_TOP_MARGIN_RATIO = 0.31666666f;
    protected static final float RING_OCCUPIED_RATIO = 0.6666667f;
    protected static final String TAG = "BaseAchieveDialog";
    private HealthTextView mAchieveText;
    protected String mActiveHourText;
    private long mAnimFrameDuration;
    private ImageView mAnimImg;
    private List<String> mAnimResList;
    protected long mAnimationDuration;
    protected ThreeCircleShareCallback mCallback;
    protected ImageView mCancelImg;
    private HealthButton mCheckButton;
    private final Context mContext;
    protected AchieveDialogFactory.DialogType mDialogType;
    private final long mExpiredTime;
    private Drawable mFirstFrame;
    protected HealthTextView mGotoDetailText;
    private boolean mHasDoTextAnimation;
    private volatile boolean mHasTryToShown;
    protected String mHeatText;
    protected String mIntensityTimeText;
    private volatile boolean mIsAnimReady;
    private volatile boolean mIsDialogAbandoned;
    private Drawable mLastFrame;
    private byte mLayoutMask;
    private DialogResourcesListener mResourcesListener;
    protected float mRingExtraTopMarginRatio;
    protected ViewGroup mRootView;
    protected RelativeLayout mTextContainer;
    private byte mTextLayoutFlags;
    protected HealthTextView mTipText;

    private int getContainerMarginBottom() {
        return R.dimen._2131362973_res_0x7f0a049d;
    }

    protected abstract byte generateAllTextViewLayoutFlags();

    protected void goToShare() {
    }

    protected void initCancelImageAndTipText() {
    }

    protected abstract void onAnimSizeMeasured(int i, int i2);

    public abstract String toString();

    static /* synthetic */ byte access$480(BaseAchieveDialog baseAchieveDialog, int i) {
        byte b = (byte) (i ^ baseAchieveDialog.mTextLayoutFlags);
        baseAchieveDialog.mTextLayoutFlags = b;
        return b;
    }

    public BaseAchieveDialog(final Context context, final AchieveDialogFactory.DialogType dialogType, final DialogResourcesListener dialogResourcesListener) {
        super(context, R.style.DialogFullScreen, 0);
        this.mRingExtraTopMarginRatio = RING_EXTRA_TOP_MARGIN_RATIO;
        this.mLayoutMask = (byte) 1;
        this.mExpiredTime = jec.a(System.currentTimeMillis());
        this.mAnimResList = null;
        this.mHasTryToShown = false;
        this.mIsAnimReady = false;
        this.mIsDialogAbandoned = false;
        this.mContext = context;
        this.mDialogType = dialogType;
        this.mResourcesListener = dialogResourcesListener;
        init();
        enqueueShowing(this);
        LogUtil.a(TAG, "animResName ", dialogType.getAnimResName());
        ThreadPoolManager.d().execute(new Runnable() { // from class: nlf
            @Override // java.lang.Runnable
            public final void run() {
                BaseAchieveDialog.this.m780lambda$new$0$comhuaweiuicommonuidialogBaseAchieveDialog(context, dialogType, dialogResourcesListener);
            }
        });
    }

    /* renamed from: lambda$new$0$com-huawei-ui-commonui-dialog-BaseAchieveDialog, reason: not valid java name */
    public /* synthetic */ void m780lambda$new$0$comhuaweiuicommonuidialogBaseAchieveDialog(Context context, AchieveDialogFactory.DialogType dialogType, DialogResourcesListener dialogResourcesListener) {
        LogUtil.a(TAG, "start fetchAnimation in thread");
        List<String> c2 = AnimResUtils.c(context, dialogType.getAnimResName(), ResInfo.Location.HOME, new AnimResUtils.d());
        this.mAnimResList = c2;
        if (koq.c(c2)) {
            this.mFirstFrame = AnimResUtils.cHh_(this.mAnimResList.get(0));
            List<String> list = this.mAnimResList;
            this.mLastFrame = AnimResUtils.cHh_(list.get(list.size() - 1));
            this.mAnimFrameDuration = r0.a();
            this.mAnimationDuration = IS_LITE_VERSION ? 0L : this.mAnimResList.size() * this.mAnimFrameDuration;
            this.mIsAnimReady = true;
            LogUtil.a(TAG, "dialog = ", toString(), " frames = ", Integer.valueOf(this.mAnimResList.size()), ", mAnimationDuration = ", Long.valueOf(this.mAnimationDuration));
            if (this.mHasTryToShown) {
                LogUtil.a(TAG, "has try to show achieve dialog: ", toString(), " before, anim ready now, show it");
                HandlerExecutor.e(new Runnable() { // from class: nli
                    @Override // java.lang.Runnable
                    public final void run() {
                        BaseAchieveDialog.this.show();
                    }
                });
                this.mHasTryToShown = false;
                return;
            }
            return;
        }
        LogUtil.b(TAG, "fetchAnimation get empty anim res, failed to fetch first frame!");
        if (dialogResourcesListener != null) {
            dialogResourcesListener.onResourcesDownloadFailed();
        }
        this.mIsDialogAbandoned = true;
    }

    public BaseAchieveDialog setHeatText(String str) {
        this.mHeatText = str;
        return this;
    }

    public BaseAchieveDialog setIntensityTimeText(String str) {
        this.mIntensityTimeText = str;
        return this;
    }

    public BaseAchieveDialog setActiveHourText(String str) {
        this.mActiveHourText = str;
        return this;
    }

    public void setGotoDetailBtnClickListener(final View.OnClickListener onClickListener) {
        HealthTextView healthTextView = this.mGotoDetailText;
        if (healthTextView == null || onClickListener == null) {
            return;
        }
        healthTextView.setOnClickListener(new View.OnClickListener() { // from class: nlh
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BaseAchieveDialog.this.m781xd7a6e814(onClickListener, view);
            }
        });
    }

    /* renamed from: lambda$setGotoDetailBtnClickListener$1$com-huawei-ui-commonui-dialog-BaseAchieveDialog, reason: not valid java name */
    public /* synthetic */ void m781xd7a6e814(View.OnClickListener onClickListener, View view) {
        if (nsn.o()) {
            LogUtil.h(TAG, "setGotoDetailBtnClickListener is fast click");
            ViewClickInstrumentation.clickOnView(view);
        } else {
            dismiss();
            onClickListener.onClick(view);
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    private void init() {
        Window window = getWindow();
        if (window == null) {
            return;
        }
        this.mTextLayoutFlags = generateAllTextViewLayoutFlags();
        ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(this.mContext).inflate(R.layout.base_achieve_dialog_layout, (ViewGroup) null);
        this.mRootView = viewGroup;
        this.mTextContainer = (RelativeLayout) viewGroup.findViewById(R.id.text_container);
        this.mAchieveText = (HealthTextView) this.mRootView.findViewById(R.id.achieve_text);
        initCancelImageAndTipText();
        ImageView imageView = (ImageView) this.mRootView.findViewById(R.id.anim_img);
        this.mAnimImg = imageView;
        imageView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.huawei.ui.commonui.dialog.BaseAchieveDialog.2
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                int width = BaseAchieveDialog.this.mAnimImg.getWidth();
                if (width <= 0 || BaseAchieveDialog.this.mFirstFrame == null) {
                    return;
                }
                int intrinsicHeight = (int) (width * (BaseAchieveDialog.this.mFirstFrame.getIntrinsicHeight() / BaseAchieveDialog.this.mFirstFrame.getIntrinsicWidth()));
                ViewGroup.LayoutParams layoutParams = BaseAchieveDialog.this.mAnimImg.getLayoutParams();
                layoutParams.height = intrinsicHeight;
                BaseAchieveDialog.this.mAnimImg.setLayoutParams(layoutParams);
                BaseAchieveDialog.this.mAnimImg.setImageDrawable(BaseAchieveDialog.IS_LITE_VERSION ? BaseAchieveDialog.this.mLastFrame : BaseAchieveDialog.this.mFirstFrame);
                BaseAchieveDialog.this.onAnimSizeMeasured(width, intrinsicHeight);
                BaseAchieveDialog.this.mAnimImg.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
        HealthButton healthButton = (HealthButton) this.mRootView.findViewById(R.id.button);
        this.mCheckButton = healthButton;
        healthButton.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.commonui.dialog.BaseAchieveDialog.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (nsn.o()) {
                    ViewClickInstrumentation.clickOnView(view);
                    return;
                }
                BaseAchieveDialog.this.dismiss();
                BaseAchieveDialog.this.goToShare();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        setContentView(this.mRootView);
        setLayoutParamsForRootView(this.mRootView);
        window.setType(1000);
        window.setBackgroundDrawableResource(R$color.common_black_50alpha);
        setOnShowListener(this);
        setOnDismissListener(this);
    }

    private void setCenterCardLayout() {
        RelativeLayout relativeLayout = this.mTextContainer;
        if (relativeLayout == null || this.mContext == null) {
            LogUtil.h(TAG, "setCenterCardLayout() mTextContainer or mContext is null.");
            return;
        }
        ViewGroup.LayoutParams layoutParams = relativeLayout.getLayoutParams();
        FrameLayout.LayoutParams layoutParams2 = layoutParams instanceof FrameLayout.LayoutParams ? (FrameLayout.LayoutParams) layoutParams : null;
        int i = 0;
        if (nsn.ag(this.mContext)) {
            LogUtil.a(TAG, "setCenterCardLayout isWidescreen");
            HealthColumnSystem healthColumnSystem = new HealthColumnSystem(this.mContext, 0);
            i = (int) (healthColumnSystem.c() + (healthColumnSystem.g() * 1.8d) + healthColumnSystem.a());
            this.mCheckButton.setMinimumWidth(BaseApplication.getContext().getResources().getDimensionPixelSize(R.dimen._2131362966_res_0x7f0a0496));
        } else {
            this.mCheckButton.setMinimumWidth(BaseApplication.getContext().getResources().getDimensionPixelSize(R.dimen._2131362939_res_0x7f0a047b));
        }
        if (layoutParams2 != null) {
            layoutParams2.leftMargin = i;
            layoutParams2.rightMargin = i;
            LogUtil.a(TAG, "setCenterCardLayout() marginLeft:", Integer.valueOf(i));
            this.mTextContainer.setLayoutParams(layoutParams2);
        }
    }

    private void setLayoutParamsForRootView(View view) {
        if (view.getLayoutParams() instanceof FrameLayout.LayoutParams) {
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view.getLayoutParams();
            layoutParams.bottomMargin = (nsn.ab(getContext()) ? nsn.q(getContext()) : 0) + this.mContext.getResources().getDimensionPixelOffset(getMarginBottom());
            view.setLayoutParams(layoutParams);
        }
        this.mTextContainer.setPadding(0, this.mContext.getResources().getDimensionPixelOffset(getContainerMarginTop()), 0, this.mContext.getResources().getDimensionPixelOffset(getContainerMarginBottom()));
        setCenterCardLayout();
    }

    private int getContainerMarginTop() {
        return nsn.aa(this.mContext) ? R.dimen._2131362886_res_0x7f0a0446 : R.dimen._2131363132_res_0x7f0a053c;
    }

    private int getMarginBottom() {
        return nsn.aa(this.mContext) ? R.dimen._2131362886_res_0x7f0a0446 : R.dimen._2131363060_res_0x7f0a04f4;
    }

    @Override // com.huawei.ui.commonui.dialog.QueueDialog
    protected boolean isReadyToShow() {
        LogUtil.a(TAG, "dialog IsReadyToShow : ", Boolean.valueOf(this.mIsAnimReady));
        return this.mIsAnimReady;
    }

    @Override // com.huawei.ui.commonui.dialog.QueueDialog
    protected boolean isDialogAbandoned() {
        LogUtil.a(TAG, "dialog mIsDialogAbandoned:", Boolean.valueOf(this.mIsDialogAbandoned), " mExpiredTime:", Long.valueOf(this.mExpiredTime));
        return this.mIsDialogAbandoned || System.currentTimeMillis() > this.mExpiredTime;
    }

    public void setIsDialogAbandoned(boolean z) {
        LogUtil.a(TAG, "setIsDialogAbandoned: ", Boolean.valueOf(z));
        this.mIsDialogAbandoned = z;
    }

    @Override // com.huawei.ui.commonui.dialog.QueueDialog, com.huawei.ui.commonui.base.BaseDialog, android.app.Dialog
    public void show() {
        setLayoutParamsForRootView(this.mRootView);
        if (!this.mIsAnimReady) {
            LogUtil.h(TAG, "try to show achieveDialog: ", toString(), ", but anim not ready, abort!");
            this.mHasTryToShown = true;
        } else {
            super.show();
        }
    }

    @Override // android.content.DialogInterface.OnShowListener
    public void onShow(DialogInterface dialogInterface) {
        Drawable drawable;
        DialogResourcesListener dialogResourcesListener = this.mResourcesListener;
        if (dialogResourcesListener != null) {
            dialogResourcesListener.onShown();
        }
        if (koq.b(this.mAnimResList) || (drawable = this.mFirstFrame) == null) {
            LogUtil.b(TAG, "show dialog failed, cause mAnimResList is ", this.mAnimResList, ", mFirstFrame = ", this.mFirstFrame);
            return;
        }
        if (!IS_LITE_VERSION) {
            AnimResUtils.cHi_(this.mAnimResList, drawable, this.mAnimFrameDuration, new c(this));
        }
        LogUtil.a(TAG, "dialog = ", toString(), " show mAnimationDrawable duration =", Long.valueOf(this.mAnimationDuration), ", frames count = ", Integer.valueOf(this.mAnimResList.size()));
        doTextAnimation();
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialogInterface) {
        this.mAnimImg.setImageDrawable(null);
        this.mFirstFrame = null;
    }

    private void scheduleTextViewGlobalLayout(final View view, final int i) {
        view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.huawei.ui.commonui.dialog.BaseAchieveDialog.3
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                BaseAchieveDialog.access$480(BaseAchieveDialog.this, i);
                view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                BaseAchieveDialog.this.doTextAnimation();
            }
        });
    }

    protected HealthTextView createTextViewAbove(int i, int i2, int i3, int i4, String str, int i5, int i6) {
        return createTextView(i, i2, i3, i4, str, 2, i5, i6);
    }

    protected HealthTextView createTextViewBelow(int i, int i2, int i3, int i4, String str, int i5, int i6) {
        return createTextView(i, i2, i3, i4, str, 3, i5, i6);
    }

    private HealthTextView createTextView(int i, int i2, int i3, int i4, String str, int i5, int i6, int i7) {
        HealthTextView healthTextView = new HealthTextView(getContext());
        healthTextView.setTextColor(i4);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        if (i5 == 2) {
            layoutParams.bottomMargin = i3;
        } else {
            layoutParams.topMargin = i3;
        }
        layoutParams.leftMargin = getContext().getResources().getDimensionPixelOffset(R.dimen._2131362973_res_0x7f0a049d);
        layoutParams.rightMargin = getContext().getResources().getDimensionPixelOffset(R.dimen._2131362973_res_0x7f0a049d);
        if (i2 != 0) {
            layoutParams.addRule(i5, i2);
        } else {
            layoutParams.addRule(i5 == 2 ? 8 : 6, R.id.anim_img);
        }
        layoutParams.addRule(14);
        healthTextView.setLayoutParams(layoutParams);
        healthTextView.setTextSize(0, getContext().getResources().getDimensionPixelSize(i6));
        healthTextView.setTypeface(Typeface.create(getContext().getResources().getString(i7), 0));
        healthTextView.setText(str);
        healthTextView.setId(i);
        healthTextView.setAlpha(0.0f);
        scheduleTextViewGlobalLayout(healthTextView, this.mLayoutMask);
        this.mLayoutMask = (byte) (this.mLayoutMask << 1);
        this.mTextContainer.addView(healthTextView);
        return healthTextView;
    }

    protected boolean isAllTextViewsLayout() {
        return this.mTextLayoutFlags == 0;
    }

    public void doTextAnimation() {
        this.mHasDoTextAnimation = true;
    }

    public boolean hasDoTextAnimation() {
        return this.mHasDoTextAnimation;
    }

    public BaseAchieveDialog setAchieveText(String str, boolean z) {
        if (this.mAchieveText == null) {
            return this;
        }
        boolean h = LanguageUtil.h(getContext());
        boolean p = LanguageUtil.p(getContext());
        if ((!h && !p) || !z) {
            this.mAchieveText.setText(str);
            return this;
        }
        String str2 = h ? "，" : ",";
        String[] split = str.split(str2);
        if (split.length <= 0) {
            this.mAchieveText.setText(str);
            return this;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < split.length; i++) {
            sb.append(split[i]);
            if (i < split.length - 1) {
                sb.append(str2);
            }
            if (i == 0) {
                sb.append("\n");
            }
        }
        this.mAchieveText.setText(sb.toString());
        return this;
    }

    public BaseAchieveDialog setCheckButtonText(String str) {
        HealthButton healthButton = this.mCheckButton;
        if (healthButton != null) {
            healthButton.setText(str);
        }
        return this;
    }

    public void onConfigurationChanged(Configuration configuration) {
        setLayoutParamsForRootView(this.mRootView);
    }

    @Override // com.huawei.ui.commonui.base.BaseDialog
    public void configDialog() {
        setLayoutParamsForRootView(this.mRootView);
    }

    static final class c implements AnimResUtils.FrameCallback {
        private WeakReference<BaseAchieveDialog> c;

        public c(BaseAchieveDialog baseAchieveDialog) {
            this.c = new WeakReference<>(baseAchieveDialog);
        }

        @Override // com.huawei.ui.commonui.utils.AnimResUtils.FrameCallback
        public void doFrame(Drawable drawable) {
            BaseAchieveDialog baseAchieveDialog = this.c.get();
            if (baseAchieveDialog != null) {
                baseAchieveDialog.mAnimImg.setImageDrawable(drawable);
            }
        }

        @Override // com.huawei.ui.commonui.utils.AnimResUtils.FrameCallback
        public void showLastFrame() {
            BaseAchieveDialog baseAchieveDialog = this.c.get();
            if (baseAchieveDialog == null || baseAchieveDialog.mLastFrame == null) {
                return;
            }
            LogUtil.a(BaseAchieveDialog.TAG, "showLastFrame ");
            baseAchieveDialog.mAnimImg.setImageDrawable(baseAchieveDialog.mLastFrame);
        }
    }
}
