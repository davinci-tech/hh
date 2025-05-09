package com.huawei.watchface.mvp.base;

import android.R;
import android.app.Activity;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import androidx.core.content.ContextCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.huawei.android.view.DisplaySideRegionEx;
import com.huawei.android.view.WindowManagerEx;
import com.huawei.haf.router.AppRouterExtras;
import com.huawei.secure.android.common.activity.SafeFragmentActivity;
import com.huawei.watchface.R$color;
import com.huawei.watchface.ad;
import com.huawei.watchface.dc;
import com.huawei.watchface.environment.Environment;
import com.huawei.watchface.ey;
import com.huawei.watchface.utils.BackgroundTaskUtils;
import com.huawei.watchface.utils.CommonUtils;
import com.huawei.watchface.utils.HwLog;
import com.huawei.watchface.y;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes7.dex */
public class BaseActivity extends SafeFragmentActivity {
    public static final String CLEAN_ACTIVITY = "com.huawei.commonui.CLEAN_ACTIVITY";
    private static final String DARK_FLAG = "MEIZU_FLAG_DARK_STATUS_BAR_ICON";
    private static final int DEFAULT_THEME_ID = 0;
    private static final String EMUI_STYLE = "androidhwext:style/Theme.Emui.NoActionBar";
    private static final int GRID_CHANGE_STATUS_OTHER = 0;
    private static final int GRID_CHANGE_STATUS_TO_DEFAULT = 2;
    private static final int GRID_CHANGE_STATUS_TO_TAHITI = 1;
    public static final int GRID_VIEW_TYPE = 1;
    private static final int LIGHT_FLAG = 0;
    private static final String MANUFACTURER_FLAG_MEIZU = "meizu";
    private static final String MANUFACTURER_FLAG_MIUI = "xiaomi";
    private static final String MEIZU_FLAG = "meizuFlags";
    public static final int RING_VIEW_TYPE = 0;
    private static final String STATUS_BAR_MIUI_CLASS_NAME = "android.view.MiuiWindowManager$LayoutParams";
    private static final String STATUS_BAR_MIUI_FIELD = "EXTRA_FLAG_STATUS_BAR_DARK_MODE";
    private static final String STATUS_BAR_MIUI_METHOD = "setExtraFlags";
    private static final String TAG = "BaseActivity";
    private static boolean sIsGetSafeWidth = false;
    private static int sSafeLeftWidth;
    private static int sSafeRightWidth;
    private static List<Integer> sExcludeIdList = new ArrayList();
    private static int sTahitiModelChangeStatus = 0;
    private int maxWidth = 0;
    private boolean mIsRingInit = false;
    private boolean mIsMateX = false;
    private boolean mIsTahitiModel = false;
    private boolean mIsTahitiModelLast = false;
    private boolean mIsNeedGridAdapt = false;
    private List<Integer> mGridExcludeIdList = new ArrayList();
    private List<Integer> mRingExcludeIdList = new ArrayList();
    private int mThemeResources = 0;
    private BroadcastReceiver mBroadcast = new BroadcastReceiver() { // from class: com.huawei.watchface.mvp.base.BaseActivity.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null || intent.getAction() == null) {
                return;
            }
            HwLog.d(BaseActivity.TAG, "mBroadcast Start action = " + intent.getAction());
            if (intent.getAction().compareTo("com.huawei.commonui.CLEAN_ACTIVITY") == 0) {
                BaseActivity.this.finish();
            }
        }
    };

    protected void configChangeReload() {
    }

    protected void initViewTahiti() {
    }

    protected boolean isRootActivity() {
        return true;
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        configChangeReload();
        if (this.mIsMateX) {
            boolean d = CommonUtils.d();
            HwLog.i(TAG, "onConfigurationChanged isTahitiModel: " + d);
            if (this.mIsTahitiModel != d) {
                this.mIsTahitiModel = d;
                if (d) {
                    setTahitiModelOrientation();
                } else {
                    setDefaultOrientation();
                }
            }
            tahitiModelChangeStatus();
            initGridAdaptation();
            this.mIsTahitiModelLast = d;
            ad.a();
            initViewTahiti();
        }
        super.onConfigurationChanged(configuration);
    }

    protected void initGridAdaptation() {
        if (this.mIsNeedGridAdapt) {
            marginAdaptation();
        }
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
        HwLog.d(TAG, "marginAdaptation list num = " + allChildViews.size());
        for (View view : allChildViews) {
            if (view != null && !isRequireModifyView(view)) {
                if (view instanceof ViewGroup) {
                    setPaddingAdaptation(view);
                } else if (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
                    setMarginAdaptation(view);
                } else {
                    HwLog.d(TAG, "No need to deal with!");
                }
            }
        }
    }

    protected static void setMarginAdaptation(View... viewArr) {
        if (viewArr == null) {
            return;
        }
        Application applicationContext = Environment.getApplicationContext();
        for (View view : viewArr) {
            if (view != null) {
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
                int b = CommonUtils.b(applicationContext, marginLayoutParams.getMarginStart());
                int b2 = CommonUtils.b(applicationContext, marginLayoutParams.getMarginEnd());
                String str = TAG;
                HwLog.d(str, "leftMargin px = " + marginLayoutParams.leftMargin + " dp = " + b);
                int i = sTahitiModelChangeStatus;
                if (i == 1) {
                    marginLayoutParams.setMarginStart(CommonUtils.a(applicationContext, b));
                    marginLayoutParams.setMarginEnd(CommonUtils.a(applicationContext, b2));
                    HwLog.d(str, "viewChild Margin increase +0");
                } else if (i == 2) {
                    float f = b > 0 ? b : 0.0f;
                    float f2 = b2 > 0 ? b2 : 0.0f;
                    marginLayoutParams.setMarginStart(CommonUtils.a(applicationContext, f));
                    marginLayoutParams.setMarginEnd(CommonUtils.a(applicationContext, f2));
                    HwLog.d(str, "viewChild Margin reduce -0");
                } else {
                    HwLog.d(str, "No need to handle!");
                }
            }
        }
    }

    protected static void setPaddingAdaptation(View... viewArr) {
        if (viewArr == null) {
            return;
        }
        Application applicationContext = Environment.getApplicationContext();
        for (View view : viewArr) {
            if (view != null) {
                int b = CommonUtils.b(applicationContext, view.getPaddingStart());
                int b2 = CommonUtils.b(applicationContext, view.getPaddingEnd());
                String str = TAG;
                HwLog.d(str, "paddingStart px = " + view.getPaddingStart() + " dp = " + b);
                int i = sTahitiModelChangeStatus;
                if (i == 1) {
                    view.setPadding(CommonUtils.a(applicationContext, b), view.getPaddingTop(), CommonUtils.a(applicationContext, b2), view.getPaddingBottom());
                    HwLog.d(str, "viewChild padding increase +0");
                } else if (i == 2) {
                    view.setPadding(CommonUtils.a(applicationContext, b > 0 ? b : 0.0f), view.getPaddingTop(), CommonUtils.a(applicationContext, b2 > 0 ? b2 : 0.0f), view.getPaddingBottom());
                    HwLog.d(str, "viewChild padding reduce -0");
                } else {
                    HwLog.d(str, "No need to deal with!");
                }
            }
        }
    }

    @Override // com.huawei.secure.android.common.activity.SafeFragmentActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
        if (this.mIsRingInit && z && CommonUtils.j()) {
            List<View> allChildViews = getAllChildViews(getWindow().getDecorView().findViewById(R.id.content), 0);
            if (!allChildViews.isEmpty()) {
                allChildViews.remove(0);
            }
            for (View view : allChildViews) {
                if (!isRequireModifyView(view) && view != null) {
                    if (view instanceof ViewGroup) {
                        int paddingStart = view.getPaddingStart();
                        int paddingEnd = view.getPaddingEnd();
                        view.setPadding(sSafeLeftWidth + paddingStart, view.getPaddingTop(), sSafeRightWidth + paddingEnd, view.getPaddingBottom());
                    } else if (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
                        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
                        marginLayoutParams.leftMargin += sSafeLeftWidth;
                        marginLayoutParams.rightMargin += sSafeRightWidth;
                        view.setLayoutParams(marginLayoutParams);
                    }
                }
            }
            this.mIsRingInit = false;
        }
        super.onWindowFocusChanged(z);
    }

    private boolean isRequireModifyView(View view) {
        return (view instanceof ListView) || (view instanceof ImageView);
    }

    public static void setViewSafeRegion(boolean z, View... viewArr) {
        if (viewArr == null || !CommonUtils.j()) {
            return;
        }
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

    private List<View> getAllChildViews(View view, int i) {
        ArrayList arrayList = new ArrayList();
        if (view == null || !(view instanceof ViewGroup)) {
            return arrayList;
        }
        View childAt = ((ViewGroup) view).getChildAt(0);
        if (!(childAt instanceof ViewGroup)) {
            return arrayList;
        }
        this.maxWidth = childAt.getMeasuredWidth();
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
        return i == this.maxWidth;
    }

    private boolean isSetBackground(View view) {
        return view.getBackground() != null || (view instanceof ImageView);
    }

    private boolean isHorizontalLinearLayout(ViewGroup viewGroup) {
        return (viewGroup instanceof LinearLayout) && ((LinearLayout) viewGroup).getOrientation() == 0;
    }

    protected void getSideRegionWidth() {
        if (CommonUtils.j()) {
            new WindowManagerEx.LayoutParamsEx(getWindow().getAttributes()).setDisplaySideMode(1);
            if (sIsGetSafeWidth) {
                return;
            }
            getWindow().getDecorView().setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener() { // from class: com.huawei.watchface.mvp.base.BaseActivity.2
                @Override // android.view.View.OnApplyWindowInsetsListener
                public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
                    DisplaySideRegionEx displaySideRegion = WindowManagerEx.LayoutParamsEx.getDisplaySideRegion(windowInsets);
                    boolean unused = BaseActivity.sIsGetSafeWidth = true;
                    if (displaySideRegion == null) {
                        BaseActivity.this.mIsRingInit = false;
                    } else {
                        int unused2 = BaseActivity.sSafeLeftWidth = displaySideRegion.getSideWidth(0);
                        int unused3 = BaseActivity.sSafeRightWidth = displaySideRegion.getSideWidth(2);
                        if (BaseActivity.sSafeLeftWidth == 0 && BaseActivity.sSafeRightWidth == 0) {
                            BaseActivity.this.mIsRingInit = false;
                        }
                    }
                    return view.onApplyWindowInsets(windowInsets);
                }
            });
        }
    }

    private boolean isSmallChildrenNode(ViewGroup viewGroup) {
        boolean z = true;
        if (isHorizontalLinearLayout(viewGroup)) {
            return true;
        }
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = viewGroup.getChildAt(i);
            if ((childAt instanceof ViewGroup) && childAt.getMeasuredWidth() == this.maxWidth) {
                z = false;
            }
        }
        return z;
    }

    @Override // com.huawei.secure.android.common.activity.SafeFragmentActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        this.mRingExcludeIdList.clear();
        this.mGridExcludeIdList.clear();
        super.onCreate(bundle);
        this.mIsMateX = CommonUtils.e();
        setStatusBarImmerses();
        boolean z = CommonUtils.d() && this.mIsMateX;
        this.mIsTahitiModel = z;
        if (this.mIsMateX && z) {
            setTahitiModelOrientation();
        } else {
            setDefaultOrientation();
        }
        if (this.mIsMateX) {
            this.mIsNeedGridAdapt = true;
        }
        this.mIsTahitiModelLast = this.mIsTahitiModel;
        if (CommonUtils.j()) {
            this.mIsRingInit = true;
            getSideRegionWidth();
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.huawei.commonui.CLEAN_ACTIVITY");
        LocalBroadcastManager.getInstance(this).registerReceiver(this.mBroadcast, intentFilter);
        loadApplicationTheme();
        initSystemBar();
        if (CommonUtils.i() || isFlyme()) {
            getWindow().setNavigationBarColor(getResources().getColor(R$color.aar_colorBackground));
        } else if (Build.VERSION.SDK_INT >= 29) {
            getWindow().setNavigationBarColor(getResources().getColor(R$color.aar_colorBackground));
        }
        getWindow().setBackgroundDrawableResource(R$color.aar_colorBackground);
        if (isRootActivity()) {
            y.a();
        }
    }

    private void setStatusBarImmerses() {
        if (Build.VERSION.SDK_INT >= 28) {
            WindowManager.LayoutParams attributes = getWindow().getAttributes();
            attributes.layoutInDisplayCutoutMode = 1;
            getWindow().setAttributes(attributes);
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
        if (Build.VERSION.SDK_INT == 26 && isTranslucentOrFloating()) {
            HwLog.w(TAG, "avoid calling setRequestedOrientation when Oreo.");
        } else {
            super.setRequestedOrientation(i);
        }
    }

    private boolean isTranslucentOrFloating() {
        boolean z = false;
        try {
            Object obj = Class.forName("com.android.internal.R$styleable").getField("Window").get(null);
            if (!(obj instanceof int[])) {
                return false;
            }
            TypedArray obtainStyledAttributes = obtainStyledAttributes((int[]) obj);
            Method method = ActivityInfo.class.getMethod("isTranslucentOrFloating", TypedArray.class);
            method.setAccessible(true);
            boolean booleanValue = ((Boolean) method.invoke(null, obtainStyledAttributes)).booleanValue();
            try {
                method.setAccessible(false);
                return booleanValue;
            } catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException | NoSuchFieldException | NoSuchMethodException | SecurityException | InvocationTargetException unused) {
                z = booleanValue;
                HwLog.e(TAG, "get isTranslucentOrFloating failed with exception");
                return z;
            }
        } catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException | NoSuchFieldException | NoSuchMethodException | SecurityException | InvocationTargetException unused2) {
        }
    }

    protected void loadApplicationTheme() {
        if (getApplicationContext().getTheme() == null) {
            HwLog.e(TAG, "loadApplicationTheme() if (theme == null)");
            return;
        }
        int identifier = getResources().getIdentifier(EMUI_STYLE, null, null);
        this.mThemeResources = identifier;
        if (identifier == 0) {
            HwLog.i(TAG, "mThemeResources is DEFAULT_THEME_ID");
            return;
        }
        HwLog.i(TAG, "mThemeResources is " + this.mThemeResources);
        setTheme(this.mThemeResources);
    }

    @Override // com.huawei.secure.android.common.activity.SafeFragmentActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        if (isRootActivity()) {
            BackgroundTaskUtils.submit(new Runnable() { // from class: com.huawei.watchface.mvp.base.BaseActivity$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    y.b();
                }
            });
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
    }

    @Override // com.huawei.secure.android.common.activity.SafeFragmentActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        ad.a(this);
        ad.b();
        super.onDestroy();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(this.mBroadcast);
        this.mBroadcast = null;
        this.mGridExcludeIdList.clear();
        this.mRingExcludeIdList.clear();
        if (isRootActivity()) {
            y.c();
        }
    }

    @Override // com.huawei.secure.android.common.activity.SafeFragmentActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
    }

    protected void initSystemBar() {
        if (this.mThemeResources == 0 && isMiui()) {
            setTranslucentStatus(getWindow());
            setMiuiStatusBarMode(!dc.a(getApplicationContext()), this);
        } else if (this.mThemeResources == 0 && isFlyme()) {
            setTranslucentStatus(getWindow());
            setMeizuStatusBarDarkIcon(getWindow(), !dc.a(getApplicationContext()));
        } else {
            setTranslucentStatus(getWindow());
            setStatusBarMode(!dc.a(getApplicationContext()));
        }
    }

    private void setStatusBarMode(boolean z) {
        if (z) {
            if (CommonUtils.k()) {
                getWindow().getDecorView().setSystemUiVisibility(getWindow().getDecorView().getSystemUiVisibility() | 9216);
                return;
            } else {
                getWindow().getDecorView().setSystemUiVisibility(9216);
                return;
            }
        }
        setStatusBarColor();
    }

    public static boolean isMiui() {
        return MANUFACTURER_FLAG_MIUI.equalsIgnoreCase(Build.MANUFACTURER);
    }

    public static boolean isFlyme() {
        return Build.MANUFACTURER.equalsIgnoreCase(MANUFACTURER_FLAG_MEIZU);
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
            HwLog.e(TAG, "xiaomi setStatusBarDarkIcon: failed ClassNotFoundException");
        } catch (IllegalAccessException unused2) {
            HwLog.e(TAG, "xiaomi setStatusBarDarkIcon: failed IllegalAccessException");
        } catch (NoSuchFieldException unused3) {
            HwLog.e(TAG, "xiaomi setStatusBarDarkIcon: failed NoSuchFieldException");
        } catch (NoSuchMethodException unused4) {
            HwLog.e(TAG, "xiaomi setStatusBarDarkIcon: failed NoSuchMethodException");
        } catch (InvocationTargetException unused5) {
            HwLog.e(TAG, "xiaomi setStatusBarDarkIcon: failed InvocationTargetException");
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
            window.setStatusBarColor(ContextCompat.getColor(this, R$color.aar_common_white_0alpha));
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
                HwLog.e(TAG, "meizu setStatusBarDarkIcon: failed IllegalAccessException");
            } catch (NoSuchFieldException unused2) {
                HwLog.e(TAG, "meizu setStatusBarDarkIcon: failed NoSuchFieldException");
            }
            if (z) {
                window.getDecorView().setSystemUiVisibility(9216);
            }
        }
        return z2;
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        HwLog.i(TAG, "Activity-onRequestPermissionsResult()");
        ey.a().a(strArr, iArr);
    }

    public boolean isLargerThanEmui910(String str) {
        return CommonUtils.k();
    }

    public void setStatusBarColor() {
        getWindow().getDecorView().setSystemUiVisibility((getWindow().getDecorView().getSystemUiVisibility() & (-8193)) | 1280);
    }
}
