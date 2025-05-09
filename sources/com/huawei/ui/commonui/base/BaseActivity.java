package com.huawei.ui.commonui.base;

import android.R;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import android.util.SparseArray;
import android.view.DisplayCutout;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.huawei.android.view.DisplaySideRegionEx;
import com.huawei.android.view.WindowManagerEx;
import com.huawei.haf.common.dfx.DfxUtils;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.router.AppRouterExtras;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.db.bean.TemplateStyleRecord;
import com.huawei.ui.commonui.R$color;
import defpackage.ixx;
import defpackage.iya;
import defpackage.jeg;
import defpackage.nkr;
import defpackage.nkx;
import defpackage.nqc;
import defpackage.nrs;
import defpackage.nrt;
import defpackage.nsn;
import health.compact.a.CommonUtil;
import health.compact.a.MagicBuild;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/* loaded from: classes.dex */
public class BaseActivity extends FragmentActivity {
    public static final String CLEAN_ACTIVITY = "com.huawei.commonui.CLEAN_ACTIVITY";
    private static final String DARK_FLAG = "MEIZU_FLAG_DARK_STATUS_BAR_ICON";
    public static final int DEFAULT_THEME_ID = 0;
    private static final String EMUI_STYLE = "androidhwext:style/Theme.Emui.NoActionBar";
    private static final int GRID_CHANGE_STATUS_OTHER = 0;
    private static final int GRID_CHANGE_STATUS_TO_DEFAULT = 2;
    private static final int GRID_CHANGE_STATUS_TO_TAHITI = 1;
    public static final int GRID_VIEW_TYPE = 1;
    private static final String HONOR_FOLDABLE_FLAG = "com.hihonor.hardware.sensor.posture";
    private static final int LIGHT_FLAG = 0;
    private static final String MANUFACTURER_FLAG_GOOGLE = "google";
    private static final String MANUFACTURER_FLAG_MEIZU = "meizu";
    private static final String MANUFACTURER_FLAG_MIUI = "xiaomi";
    private static final String MANUFACTURER_FLAG_OPPO = "oppo";
    private static final String MANUFACTURER_FLAG_SAMSUNG = "samsung";
    private static final String MEIZU_FLAG = "meizuFlags";
    private static final int NO_ID = -1;
    public static final int RING_VIEW_TYPE = 0;
    private static final String STATUS_BAR_MIUI_CLASS_NAME = "android.view.MiuiWindowManager$LayoutParams";
    private static final String STATUS_BAR_MIUI_FIELD = "EXTRA_FLAG_STATUS_BAR_DARK_MODE";
    private static final String STATUS_BAR_MIUI_METHOD = "setExtraFlags";
    private static final String TAG = "BaseActivity";
    private static ixx sBiAnalyticsUtil = null;
    private static boolean sIsGetSafeWidth = false;
    private static int sMaxWidth;
    private static int sSafeLeftWidth;
    private static int sSafeRightWidth;
    private int mInitContentPaddingLeft;
    private int mInitContentPaddingRight;
    private FrameLayout.LayoutParams mMinibarLayoutParams;
    private static List<Integer> sExcludeIdList = new ArrayList();
    private static SparseArray<iya> sUserMotionEvent = new SparseArray<>(5);
    private static int sTahitiModelChangeStatus = 0;
    private boolean mIsRingInit = false;
    private boolean mIsCutoutInit = false;
    private boolean mIsAllowCutout = true;
    private boolean mHasSetCutoutWidth = false;
    private boolean mIsFoldable = false;
    private boolean mIsTahitiModel = false;
    private boolean mIsTahitiModelLast = false;
    private boolean mIsNeedGridAdapt = false;
    private boolean mIsSetSafeWidth = false;
    private List<Integer> mGridExcludeIdList = new ArrayList();
    private List<Integer> mRingExcludeIdList = new ArrayList();
    private int mThemeResources = 0;
    private int mCutoutLeftWidth = 0;
    private int mCutoutRightWidth = 0;
    public boolean mIsPageNeedShowMinibar = true;
    public int mMinibarBottomMargin = 0;
    private BroadcastReceiver mBroadcast = new BroadcastReceiver() { // from class: com.huawei.ui.commonui.base.BaseActivity.5
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null || intent.getAction() == null) {
                return;
            }
            LogUtil.c(BaseActivity.TAG, "mBroadcast Start action = ", intent.getAction());
            if ("com.huawei.commonui.CLEAN_ACTIVITY".equals(intent.getAction())) {
                BaseActivity.this.finish();
            }
        }
    };

    protected float getMaxFontScale() {
        return 1.45f;
    }

    public void initViewTahiti() {
    }

    public boolean isSupportFoldingModel() {
        return false;
    }

    public void onTrimMemory(int i, boolean z) {
    }

    public void setRootViewBackgroundColor(View view) {
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        if (this.mIsFoldable) {
            boolean z = nsn.ag(getApplicationContext()) && nsn.l();
            LogUtil.a(TAG, "onConfigurationChanged isTahitiModel: ", Boolean.valueOf(z));
            if (this.mIsTahitiModel != z) {
                this.mIsTahitiModel = z;
                if (z) {
                    setTahitiModelOrientation();
                } else {
                    setDefaultOrientation();
                }
            }
            tahitiModelChangeStatus();
            initGridAdaptation();
            this.mIsTahitiModelLast = z;
            BaseDialog.configureChangedShowDialog();
            nqc.e();
            initViewTahiti();
        } else if (nsn.ag(getApplicationContext())) {
            BaseDialog.configureChangedShowDialog();
        }
        super.onConfigurationChanged(configuration);
    }

    protected void initGridAdaptation() {
        if (this.mIsNeedGridAdapt) {
            marginAdaptation();
        }
    }

    @Override // android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public Resources getResources() {
        return nrs.cKi_(super.getResources(), 1.45f);
    }

    protected void tahitiModelChangeStatus() {
        boolean z = this.mIsTahitiModel;
        if (z && !this.mIsTahitiModelLast) {
            setTahitiModel(1);
        } else if (!z && this.mIsTahitiModelLast) {
            setTahitiModel(2);
        } else {
            setTahitiModel(0);
        }
    }

    private static void setTahitiModel(int i) {
        sTahitiModelChangeStatus = i;
    }

    protected void marginAdaptation() {
        if (sTahitiModelChangeStatus == 0) {
            return;
        }
        List<View> allChildViews = getAllChildViews(getWindow().getDecorView().findViewById(R.id.content), 1);
        LogUtil.c(TAG, "marginAdaptation list num = ", Integer.valueOf(allChildViews.size()));
        for (View view : allChildViews) {
            if (view != null && !isRequireModifyView(view)) {
                if (view instanceof ViewGroup) {
                    setPaddingAdaptation(view);
                } else if (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
                    setMarginAdaptation(view);
                } else {
                    LogUtil.c(TAG, "No need to deal with!");
                }
            }
        }
    }

    protected static void setMarginAdaptation(View... viewArr) {
        if (viewArr == null) {
            return;
        }
        Context context = BaseApplication.getContext();
        for (View view : viewArr) {
            if (view != null) {
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
                int a2 = nsn.a(context, marginLayoutParams.getMarginStart());
                int a3 = nsn.a(context, marginLayoutParams.getMarginEnd());
                String str = TAG;
                LogUtil.c(str, "viewChild1 = ", view.getAccessibilityClassName().toString(), "leftMargin px = ", Integer.valueOf(marginLayoutParams.leftMargin), " dp = ", Integer.valueOf(a2));
                int i = sTahitiModelChangeStatus;
                if (i == 1) {
                    marginLayoutParams.setMarginStart(nsn.c(context, a2));
                    marginLayoutParams.setMarginEnd(nsn.c(context, a3));
                    LogUtil.c(str, "viewChild Margin increase +", 0);
                } else if (i == 2) {
                    float f = a2 > 0 ? a2 : 0.0f;
                    float f2 = a3 > 0 ? a3 : 0.0f;
                    marginLayoutParams.setMarginStart(nsn.c(context, f));
                    marginLayoutParams.setMarginEnd(nsn.c(context, f2));
                    LogUtil.c(str, "viewChild Margin reduce -", 0);
                } else {
                    LogUtil.c(str, "No need to handle!");
                }
            }
        }
    }

    protected static void setPaddingAdaptation(View... viewArr) {
        if (viewArr == null) {
            return;
        }
        Context context = BaseApplication.getContext();
        for (View view : viewArr) {
            if (view != null) {
                int a2 = nsn.a(context, view.getPaddingStart());
                int a3 = nsn.a(context, view.getPaddingEnd());
                String str = TAG;
                LogUtil.c(str, "viewChild = ", view.getAccessibilityClassName().toString(), "paddingStart px = ", Integer.valueOf(view.getPaddingStart()), " dp = ", Integer.valueOf(a2));
                int i = sTahitiModelChangeStatus;
                if (i == 1) {
                    view.setPadding(nsn.c(context, a2), view.getPaddingTop(), nsn.c(context, a3), view.getPaddingBottom());
                    LogUtil.c(str, "viewChild padding increase +", 0);
                } else if (i == 2) {
                    view.setPadding(nsn.c(context, a2 > 0 ? a2 : 0.0f), view.getPaddingTop(), nsn.c(context, a3 > 0 ? a3 : 0.0f), view.getPaddingBottom());
                    LogUtil.c(str, "viewChild padding reduce -", 0);
                } else {
                    LogUtil.c(str, "No need to deal with!");
                }
            }
        }
    }

    public static void setGridMargins(int i, View... viewArr) {
        if (viewArr == null) {
            return;
        }
        if (i == 1) {
            setMarginAdaptation(viewArr);
        } else {
            if (i != 2) {
                return;
            }
            setPaddingAdaptation(viewArr);
        }
    }

    public void cancelMarginAdaptation() {
        this.mIsNeedGridAdapt = false;
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (sUserMotionEvent.size() > 5) {
            return super.dispatchTouchEvent(motionEvent);
        }
        iya iyaVar = new iya(System.currentTimeMillis());
        iyaVar.e((int) motionEvent.getRawX());
        iyaVar.b((int) motionEvent.getRawY());
        int action = motionEvent.getAction();
        if (action == 0) {
            iyaVar.d(0);
        } else if (action == 1) {
            iyaVar.d(1);
        } else if (action != 2) {
            iyaVar = null;
        } else {
            iyaVar.d(2);
        }
        if (iyaVar != null) {
            SparseArray<iya> sparseArray = sUserMotionEvent;
            sparseArray.put(sparseArray.size(), iyaVar);
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        try {
            super.onStart();
        } catch (ClassCastException e) {
            String str = "onStart exception happened, " + e + ", activity = " + this;
            LogUtil.b(TAG, str);
            throw new ClassCastException(str + System.lineSeparator() + DfxUtils.d(Thread.currentThread(), e));
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
        LogUtil.a(TAG, "onWindowFocusChanged: ", Boolean.valueOf(z));
        if (this.mIsRingInit && z && (CommonUtil.ar() || CommonUtil.bf())) {
            adapterView();
            this.mIsRingInit = false;
        }
        if (this.mIsCutoutInit && z && isHonorFoldableDevice()) {
            setDisplayCutoutMode(3, this);
            setCutoutPadding();
            this.mIsCutoutInit = false;
        }
        super.onWindowFocusChanged(z);
    }

    protected void adapterView() {
        List<View> allChildViews = getAllChildViews(getWindow().getDecorView().findViewById(R.id.content), 0);
        if (!allChildViews.isEmpty()) {
            allChildViews.remove(0);
        }
        for (View view : allChildViews) {
            if (!isRequireModifyView(view) && view != null) {
                if ((view instanceof ViewGroup) && !this.mIsSetSafeWidth) {
                    int paddingStart = view.getPaddingStart();
                    int paddingEnd = view.getPaddingEnd();
                    view.setPadding(sSafeLeftWidth + paddingStart, view.getPaddingTop(), sSafeRightWidth + paddingEnd, view.getPaddingBottom());
                    this.mIsSetSafeWidth = true;
                } else if ((view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) && !this.mIsSetSafeWidth) {
                    ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
                    marginLayoutParams.leftMargin += sSafeLeftWidth;
                    marginLayoutParams.rightMargin += sSafeRightWidth;
                    view.setLayoutParams(marginLayoutParams);
                    this.mIsSetSafeWidth = true;
                }
            }
        }
    }

    public void setCutoutPadding() {
        if (!this.mIsAllowCutout) {
            LogUtil.a(TAG, "setCutoutPadding not allow cutout");
            return;
        }
        View findViewById = getWindow().getDecorView().findViewById(R.id.content);
        LogUtil.a(TAG, "setCutoutPadding left: ", Integer.valueOf(this.mCutoutLeftWidth), " right: ", Integer.valueOf(this.mCutoutRightWidth));
        if (!this.mHasSetCutoutWidth) {
            this.mInitContentPaddingLeft = findViewById.getPaddingLeft();
            this.mInitContentPaddingRight = findViewById.getPaddingRight();
        }
        setRootViewBackgroundColor(findViewById);
        int i = this.mInitContentPaddingLeft;
        int i2 = this.mCutoutLeftWidth;
        int i3 = i + i2;
        findViewById.setPadding(i3, findViewById.getPaddingTop(), this.mInitContentPaddingRight + this.mCutoutRightWidth, findViewById.getPaddingBottom());
        this.mHasSetCutoutWidth = true;
    }

    private boolean isRequireModifyView(View view) {
        return (view instanceof ListView) || (view instanceof ImageView);
    }

    public static Pair<Integer, Integer> getSafeRegionWidth() {
        return new Pair<>(Integer.valueOf(sSafeLeftWidth), Integer.valueOf(sSafeRightWidth));
    }

    protected void cancelAdaptRingRegion() {
        this.mIsRingInit = false;
    }

    public void cancelAdaptCutoutRegion() {
        this.mIsAllowCutout = false;
    }

    public static void cancelLayoutById(View... viewArr) {
        int id;
        if (viewArr != null) {
            sExcludeIdList.clear();
            for (View view : viewArr) {
                if (view != null && (id = view.getId()) != -1) {
                    sExcludeIdList.add(Integer.valueOf(id));
                }
            }
        }
    }

    public static void setViewSafeRegion(boolean z, View... viewArr) {
        if (viewArr != null) {
            if (CommonUtil.ar() || CommonUtil.bf()) {
                int i = 0;
                if (z) {
                    int length = viewArr.length;
                    while (i < length) {
                        View view = viewArr[i];
                        if (view != null && (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams)) {
                            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
                            marginLayoutParams.leftMargin += sSafeLeftWidth;
                            marginLayoutParams.rightMargin += sSafeRightWidth;
                            view.setLayoutParams(marginLayoutParams);
                        }
                        i++;
                    }
                    return;
                }
                int length2 = viewArr.length;
                while (i < length2) {
                    View view2 = viewArr[i];
                    if (view2 != null) {
                        int paddingStart = view2.getPaddingStart();
                        int paddingEnd = view2.getPaddingEnd();
                        view2.setPadding(sSafeLeftWidth + paddingStart, view2.getPaddingTop(), sSafeRightWidth + paddingEnd, view2.getPaddingBottom());
                    }
                    i++;
                }
            }
        }
    }

    private List<View> getAllChildViews(View view, int i) {
        ArrayList arrayList = new ArrayList();
        if (view == null || !(view instanceof ViewGroup)) {
            return arrayList;
        }
        View childAt = ((ViewGroup) view).getChildAt(0);
        if (!(childAt instanceof ViewGroup)) {
            return arrayList;
        }
        sMaxWidth = childAt.getMeasuredWidth();
        return getChildView(childAt, arrayList, i);
    }

    private List<View> getChildView(View view, List<View> list, int i) {
        if (i == 0 && (sExcludeIdList.contains(Integer.valueOf(view.getId())) || this.mRingExcludeIdList.contains(Integer.valueOf(view.getId())))) {
            return list;
        }
        if ((i != 1 || !this.mGridExcludeIdList.contains(Integer.valueOf(view.getId()))) && (view instanceof ViewGroup)) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = viewGroup.getChildAt(i2);
                if (!sExcludeIdList.contains(Integer.valueOf(childAt.getId()))) {
                    if (!isFullScreen(childAt.getMeasuredWidth())) {
                        list.add(childAt);
                    } else if (childAt instanceof ViewGroup) {
                        if (isSmallChildrenNode((ViewGroup) childAt)) {
                            list.add(childAt);
                        } else {
                            getChildView(childAt, list, i);
                        }
                    } else if (!isSetBackground(childAt)) {
                        list.add(childAt);
                    }
                }
            }
        }
        return list;
    }

    private boolean isFullScreen(int i) {
        return i == sMaxWidth;
    }

    private boolean isSetBackground(View view) {
        return view.getBackground() != null || (view instanceof ImageView);
    }

    private boolean isHorizontalLinearLayout(ViewGroup viewGroup) {
        return (viewGroup instanceof LinearLayout) && ((LinearLayout) viewGroup).getOrientation() == 0;
    }

    public static void setDisplaySideMode(Context context) {
        if (MagicBuild.f13130a) {
            return;
        }
        if ((CommonUtil.ar() || CommonUtil.bf()) && (context instanceof Activity)) {
            new WindowManagerEx.LayoutParamsEx(((Activity) context).getWindow().getAttributes()).setDisplaySideMode(1);
        }
    }

    protected void getSideRegionWidth() {
        if (MagicBuild.f13130a) {
            return;
        }
        if (CommonUtil.ar() || CommonUtil.bf()) {
            new WindowManagerEx.LayoutParamsEx(getWindow().getAttributes()).setDisplaySideMode(1);
            if (sIsGetSafeWidth) {
                return;
            }
            getWindow().getDecorView().setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener() { // from class: com.huawei.ui.commonui.base.BaseActivity.2
                @Override // android.view.View.OnApplyWindowInsetsListener
                public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
                    DisplaySideRegionEx displaySideRegion = WindowManagerEx.LayoutParamsEx.getDisplaySideRegion(windowInsets);
                    boolean unused = BaseActivity.sIsGetSafeWidth = true;
                    if (displaySideRegion == null) {
                        BaseActivity.this.mIsRingInit = false;
                    } else {
                        int unused2 = BaseActivity.sSafeLeftWidth = displaySideRegion.getSideWidth(0);
                        int unused3 = BaseActivity.sSafeRightWidth = displaySideRegion.getSideWidth(2);
                        LogUtil.a(BaseActivity.TAG, "sSafeLeftWidth: ", Integer.valueOf(BaseActivity.sSafeLeftWidth), " sSafeRightWidth: ", Integer.valueOf(BaseActivity.sSafeRightWidth));
                        if (BaseActivity.sSafeLeftWidth == 0 && BaseActivity.sSafeRightWidth == 0) {
                            BaseActivity.this.mIsRingInit = false;
                        }
                    }
                    return view.onApplyWindowInsets(windowInsets);
                }
            });
        }
    }

    private void getCutoutWidth() {
        if (!isHonorFoldableDevice()) {
            LogUtil.a(TAG, "getCutoutWidth not honor foldable device");
        } else {
            setDisplayCutoutMode(3, this);
            getWindow().getDecorView().setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener() { // from class: com.huawei.ui.commonui.base.BaseActivity.4
                @Override // android.view.View.OnApplyWindowInsetsListener
                public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
                    if (Build.VERSION.SDK_INT >= 28) {
                        BaseActivity.this.updateCutoutWidth(windowInsets.getDisplayCutout());
                    }
                    return view.onApplyWindowInsets(windowInsets);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateCutoutWidth(DisplayCutout displayCutout) {
        if (Build.VERSION.SDK_INT < 28) {
            return;
        }
        if (displayCutout == null) {
            LogUtil.a(TAG, "cutout is null");
            this.mIsCutoutInit = false;
            if (this.mCutoutLeftWidth == 0 && this.mCutoutRightWidth == 0) {
                return;
            }
            this.mCutoutLeftWidth = 0;
            this.mCutoutRightWidth = 0;
            setCutoutPadding();
            return;
        }
        int safeInsetLeft = displayCutout.getSafeInsetLeft();
        int safeInsetRight = displayCutout.getSafeInsetRight();
        LogUtil.a(TAG, "updateCutoutWidth, cutout left: ", Integer.valueOf(safeInsetLeft), " right: ", Integer.valueOf(safeInsetRight));
        if (safeInsetLeft == 0 && safeInsetRight == 0) {
            this.mIsCutoutInit = false;
        }
        if (safeInsetLeft == this.mCutoutLeftWidth && safeInsetRight == this.mCutoutRightWidth) {
            return;
        }
        this.mCutoutLeftWidth = safeInsetLeft;
        this.mCutoutRightWidth = safeInsetRight;
        if (this.mIsCutoutInit) {
            return;
        }
        setCutoutPadding();
    }

    private boolean isSmallChildrenNode(ViewGroup viewGroup) {
        boolean z = true;
        if (isHorizontalLinearLayout(viewGroup)) {
            return true;
        }
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = viewGroup.getChildAt(i);
            if ((childAt instanceof ViewGroup) && childAt.getMeasuredWidth() == sMaxWidth) {
                z = false;
            }
        }
        return z;
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        this.mRingExcludeIdList.clear();
        this.mGridExcludeIdList.clear();
        super.onCreate(bundle);
        this.mIsFoldable = nsn.l();
        adapterWideScreenAndFold();
        if (this.mIsFoldable) {
            this.mIsNeedGridAdapt = true;
        }
        if (CommonUtil.ar() || CommonUtil.bf()) {
            this.mIsRingInit = true;
            getSideRegionWidth();
        }
        if (sBiAnalyticsUtil == null) {
            sBiAnalyticsUtil = ixx.d();
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.huawei.commonui.CLEAN_ACTIVITY");
        LocalBroadcastManager.getInstance(this).registerReceiver(this.mBroadcast, intentFilter);
        loadApplicationTheme();
        initSystemBar();
        setNavigationBarColor();
        getWindow().setBackgroundDrawableResource(R$color.colorBackground);
    }

    private void setNavigationBarColor() {
        if (isGoogle()) {
            return;
        }
        if (CommonUtil.bh() || isFlyme()) {
            getWindow().setNavigationBarColor(getResources().getColor(R$color.colorBackground));
        } else if (Build.VERSION.SDK_INT >= 29) {
            getWindow().setNavigationBarColor(getResources().getColor(R$color.colorBackground));
        }
    }

    private void adapterWideScreenAndFold() {
        boolean z = nsn.ag(getApplicationContext()) && nsn.l();
        this.mIsTahitiModel = z;
        if (this.mIsFoldable && z) {
            setTahitiModelOrientation();
        } else {
            setDefaultOrientation();
        }
        this.mIsTahitiModelLast = this.mIsTahitiModel;
        if (isHonorFoldableDevice()) {
            this.mIsCutoutInit = true;
            getCutoutWidth();
        }
        if (this.mIsTahitiModel) {
            setDisplayCutoutMode(1, this);
        }
    }

    protected void setTahitiModelOrientation() {
        setRequestedOrientation(-1);
    }

    protected void setDefaultOrientation() {
        setRequestedOrientation(1);
    }

    @Override // android.app.Activity
    public void setRequestedOrientation(int i) {
        if (isKeepDefaultOrientation()) {
            LogUtil.h(TAG, "avoid calling setRequestedOrientation when Oreo.");
        } else {
            super.setRequestedOrientation(i);
        }
    }

    private boolean isKeepDefaultOrientation() {
        return (Build.VERSION.SDK_INT == 26 || Build.VERSION.SDK_INT == 27) && isTranslucentOrFloating();
    }

    private boolean isTranslucentOrFloating() {
        boolean z;
        Throwable e;
        Object obj;
        try {
            obj = Class.forName("com.android.internal.R$styleable").getField("Window").get(null);
        } catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException | NoSuchFieldException | NoSuchMethodException | SecurityException | InvocationTargetException e2) {
            z = false;
            e = e2;
        }
        if (!(obj instanceof int[])) {
            return false;
        }
        TypedArray obtainStyledAttributes = obtainStyledAttributes((int[]) obj);
        Method method = ActivityInfo.class.getMethod("isTranslucentOrFloating", TypedArray.class);
        method.setAccessible(true);
        z = ((Boolean) method.invoke(null, obtainStyledAttributes)).booleanValue();
        try {
            method.setAccessible(false);
        } catch (ClassNotFoundException e3) {
            e = e3;
            LogUtil.b(TAG, "get isTranslucentOrFloating failed with exception:", ExceptionUtils.d(e));
            return z;
        } catch (IllegalAccessException e4) {
            e = e4;
            LogUtil.b(TAG, "get isTranslucentOrFloating failed with exception:", ExceptionUtils.d(e));
            return z;
        } catch (IllegalArgumentException e5) {
            e = e5;
            LogUtil.b(TAG, "get isTranslucentOrFloating failed with exception:", ExceptionUtils.d(e));
            return z;
        } catch (NoSuchFieldException e6) {
            e = e6;
            LogUtil.b(TAG, "get isTranslucentOrFloating failed with exception:", ExceptionUtils.d(e));
            return z;
        } catch (NoSuchMethodException e7) {
            e = e7;
            LogUtil.b(TAG, "get isTranslucentOrFloating failed with exception:", ExceptionUtils.d(e));
            return z;
        } catch (SecurityException e8) {
            e = e8;
            LogUtil.b(TAG, "get isTranslucentOrFloating failed with exception:", ExceptionUtils.d(e));
            return z;
        } catch (InvocationTargetException e9) {
            e = e9;
            LogUtil.b(TAG, "get isTranslucentOrFloating failed with exception:", ExceptionUtils.d(e));
            return z;
        }
        return z;
    }

    protected void loadApplicationTheme() {
        if (getApplicationContext().getTheme() == null) {
            LogUtil.b(TAG, "loadApplicationTheme() if (theme == null)");
        } else {
            loadTheme(EMUI_STYLE, null, null);
        }
    }

    protected void loadTheme(String str) {
        loadTheme(str, TemplateStyleRecord.STYLE, "com.huawei.health");
    }

    protected void loadTheme(String str, String str2, String str3) {
        int identifier = getResources().getIdentifier(str, str2, str3);
        if (identifier == 0) {
            LogUtil.a(TAG, "loadApplicationTheme themeResources is DEFAULT_THEME_ID");
        } else {
            LogUtil.a(TAG, "loadApplicationTheme themeResources is ", Integer.valueOf(identifier));
            setTheme(identifier);
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        ixx ixxVar = sBiAnalyticsUtil;
        if (ixxVar != null) {
            ixxVar.d(this);
        }
        initMinibar();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        ixx ixxVar = sBiAnalyticsUtil;
        if (ixxVar != null) {
            ixxVar.b(this);
        }
        nkr.d().b();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        BaseDialog.cleanAllDialog(this);
        nkx.e(toString());
        super.onDestroy();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(this.mBroadcast);
        this.mBroadcast = null;
        ixx ixxVar = sBiAnalyticsUtil;
        if (ixxVar != null) {
            ixxVar.c(this);
        }
        this.mGridExcludeIdList.clear();
        this.mRingExcludeIdList.clear();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks2
    public void onTrimMemory(int i) {
        super.onTrimMemory(i);
        onTrimMemory(i, BaseApplication.isRunningForeground());
    }

    protected void initSystemBar() {
        if ("com.huawei.bone".equals(BaseApplication.getAppPackage())) {
            initHwWearSystemBar();
        }
        if ("com.huawei.health".equals(BaseApplication.getAppPackage()) || BaseApplication.APP_PACKAGE_GOOGLE_HEALTH.equals(BaseApplication.getAppPackage())) {
            if (this.mThemeResources == 0 && isMiui()) {
                setTranslucentStatus(getWindow());
                setMiuiStatusBarMode(!nrt.a(getApplicationContext()), this);
            } else if (this.mThemeResources == 0 && isFlyme()) {
                setTranslucentStatus(getWindow());
                setMeizuStatusBarDarkIcon(getWindow(), !nrt.a(getApplicationContext()));
            } else {
                setTranslucentStatus(getWindow());
                setStatusBarMode(!nrt.a(getApplicationContext()));
            }
        }
    }

    public void setStatusBarMode(boolean z) {
        if (z) {
            if (CommonUtil.ba()) {
                getWindow().getDecorView().setSystemUiVisibility(getWindow().getDecorView().getSystemUiVisibility() | 9216);
                return;
            } else {
                getWindow().getDecorView().setSystemUiVisibility(9216);
                return;
            }
        }
        setStatusBarColor();
    }

    private void initHwWearSystemBar() {
        getWindow().setNavigationBarColor(0);
        getWindow().setStatusBarColor(0);
        getWindow().getDecorView().setSystemUiVisibility(9216);
    }

    public static boolean isMiui() {
        return MANUFACTURER_FLAG_MIUI.equalsIgnoreCase(Build.MANUFACTURER);
    }

    public static boolean isFlyme() {
        return Build.MANUFACTURER.equalsIgnoreCase(MANUFACTURER_FLAG_MEIZU);
    }

    public static boolean isOppoSystem() {
        return Build.BRAND.toLowerCase(Locale.ENGLISH).contains(MANUFACTURER_FLAG_OPPO);
    }

    public static boolean isSumsung() {
        return Build.BRAND.toLowerCase(Locale.ENGLISH).contains(MANUFACTURER_FLAG_SAMSUNG);
    }

    public static boolean isGoogle() {
        return Build.BRAND.toLowerCase(Locale.ENGLISH).contains(MANUFACTURER_FLAG_GOOGLE);
    }

    public static void setMiuiStatusBarMode(boolean z, Activity activity) {
        Class<?> cls = activity.getWindow().getClass();
        try {
            Class<?> cls2 = Class.forName(STATUS_BAR_MIUI_CLASS_NAME);
            int i = cls2.getField(STATUS_BAR_MIUI_FIELD).getInt(cls2);
            Method method = cls.getMethod(STATUS_BAR_MIUI_METHOD, Integer.TYPE, Integer.TYPE);
            Window window = activity.getWindow();
            Object[] objArr = new Object[2];
            objArr[0] = Integer.valueOf(z ? i : 0);
            objArr[1] = Integer.valueOf(i);
            method.invoke(window, objArr);
        } catch (ClassNotFoundException unused) {
            LogUtil.b(TAG, "xiaomi setStatusBarDarkIcon: failed ClassNotFoundException");
        } catch (IllegalAccessException unused2) {
            LogUtil.b(TAG, "xiaomi setStatusBarDarkIcon: failed IllegalAccessException");
        } catch (NoSuchFieldException unused3) {
            LogUtil.b(TAG, "xiaomi setStatusBarDarkIcon: failed NoSuchFieldException");
        } catch (NoSuchMethodException unused4) {
            LogUtil.b(TAG, "xiaomi setStatusBarDarkIcon: failed NoSuchMethodException");
        } catch (InvocationTargetException unused5) {
            LogUtil.b(TAG, "xiaomi setStatusBarDarkIcon: failed InvocationTargetException");
        }
        if (z) {
            activity.getWindow().getDecorView().setSystemUiVisibility(9216);
        }
    }

    public void setTranslucentStatus(Window window) {
        if (window != null) {
            if (this.mThemeResources == 0) {
                window.clearFlags(AppRouterExtras.COLDSTART);
                window.getDecorView().setSystemUiVisibility(1280);
                window.addFlags(Integer.MIN_VALUE);
                window.setStatusBarColor(0);
                return;
            }
            window.setStatusBarColor(ContextCompat.getColor(this, R$color.common_white_0alpha));
        }
    }

    public static boolean setMeizuStatusBarDarkIcon(Window window, boolean z) {
        boolean z2 = false;
        if (window != null) {
            try {
                WindowManager.LayoutParams attributes = window.getAttributes();
                Field declaredField = WindowManager.LayoutParams.class.getDeclaredField(DARK_FLAG);
                Field declaredField2 = WindowManager.LayoutParams.class.getDeclaredField(MEIZU_FLAG);
                declaredField.setAccessible(true);
                declaredField2.setAccessible(true);
                int i = declaredField.getInt(null);
                int i2 = declaredField2.getInt(attributes);
                declaredField2.setInt(attributes, z ? i | i2 : (~i) & i2);
                window.setAttributes(attributes);
                z2 = true;
            } catch (IllegalAccessException unused) {
                LogUtil.b(TAG, "meizu setStatusBarDarkIcon: failed IllegalAccessException");
            } catch (NoSuchFieldException unused2) {
                LogUtil.b(TAG, "meizu setStatusBarDarkIcon: failed NoSuchFieldException");
            }
            if (z) {
                window.getDecorView().setSystemUiVisibility(9216);
            }
        }
        return z2;
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        LogUtil.a(TAG, "Activity-onRequestPermissionsResult()");
        jeg.d().d(strArr, iArr);
    }

    public boolean isLargerThanEmui910(String str) {
        return CommonUtil.ba();
    }

    public void setStatusBarColor() {
        getWindow().getDecorView().setSystemUiVisibility((getWindow().getDecorView().getSystemUiVisibility() & (-8193)) | 1280);
    }

    public static void setNavigationBarVisibility(Activity activity, int i) {
        if (activity == null) {
            return;
        }
        View decorView = activity.getWindow().getDecorView();
        if (i == 8) {
            decorView.setSystemUiVisibility(decorView.getSystemUiVisibility() | 4098);
        } else {
            decorView.setSystemUiVisibility((decorView.getSystemUiVisibility() | 2) & (-3));
        }
    }

    public static boolean setAttributesShortOrDefault(int i, Activity activity) {
        String str = TAG;
        LogUtil.a(str, "cutoutModel:", Integer.valueOf(i));
        if (activity == null) {
            LogUtil.h(str, "activity = null");
            return false;
        }
        if (Build.VERSION.SDK_INT < 28 || 1 != activity.getResources().getConfiguration().orientation) {
            return false;
        }
        setDisplayCutoutMode(i, activity);
        return true;
    }

    private static void setDisplayCutoutMode(int i, Activity activity) {
        if (Build.VERSION.SDK_INT >= 28) {
            WindowManager.LayoutParams attributes = activity.getWindow().getAttributes();
            attributes.layoutInDisplayCutoutMode = i;
            activity.getWindow().setAttributes(attributes);
        }
    }

    public void clearBackgroundDrawable() {
        Window window;
        if (isHonorFoldableDevice() || (window = getWindow()) == null) {
            return;
        }
        window.setBackgroundDrawable(null);
    }

    public boolean isHonorFoldableDevice() {
        return CommonUtil.bf() && MagicBuild.f13130a && getPackageManager().hasSystemFeature(HONOR_FOLDABLE_FLAG);
    }

    public void setBackgroundDrawableResource(int i) {
        getWindow().setBackgroundDrawableResource(i);
    }

    protected void initMinibar() {
        String str = TAG;
        LogUtil.a(str, "initMinibar");
        View cwW_ = nkr.d().cwW_();
        if (cwW_ == null) {
            return;
        }
        nkr.d().b();
        ComponentName componentName = getComponentName();
        if (componentName == null || TextUtils.isEmpty(componentName.getClassName())) {
            LogUtil.a(str, "getClassName is empty");
            return;
        }
        LogUtil.a(str, "getClassName = ", componentName.getClassName());
        if (!nkr.d().c(componentName.getClassName())) {
            LogUtil.a(str, "showMiniBar current page need not to show minibar");
            return;
        }
        FrameLayout frameLayout = (FrameLayout) getWindow().getDecorView().findViewById(R.id.content);
        if (this.mMinibarLayoutParams == null) {
            this.mMinibarLayoutParams = new FrameLayout.LayoutParams(-2, -2);
        }
        this.mMinibarLayoutParams.gravity = 80;
        this.mMinibarLayoutParams.width = -1;
        this.mMinibarLayoutParams.height = -2;
        this.mMinibarLayoutParams.bottomMargin = this.mMinibarBottomMargin;
        cwW_.setVisibility(this.mIsPageNeedShowMinibar ? 0 : 8);
        frameLayout.addView(cwW_, this.mMinibarLayoutParams);
        LogUtil.a(str, "initMinibar end");
    }

    protected void setMinibarBottomMargin(int i) {
        this.mMinibarBottomMargin = i;
    }

    public void hideMinibar() {
        View cwW_ = nkr.d().cwW_();
        if (cwW_ != null) {
            cwW_.setVisibility(8);
            LogUtil.a(TAG, "hideMinibar");
        }
    }

    public void showMinibar() {
        View cwW_ = nkr.d().cwW_();
        if (cwW_ == null || !this.mIsPageNeedShowMinibar) {
            return;
        }
        cwW_.setVisibility(0);
        LogUtil.a(TAG, "showMinibar");
    }

    public void updateMinibar() {
        FrameLayout.LayoutParams layoutParams;
        View cwW_ = nkr.d().cwW_();
        if (cwW_ == null || (layoutParams = this.mMinibarLayoutParams) == null) {
            return;
        }
        layoutParams.bottomMargin = this.mMinibarBottomMargin;
        cwW_.setLayoutParams(this.mMinibarLayoutParams);
    }

    public String getCurrFoldModel() {
        return "NORMAL_MODE";
    }
}
