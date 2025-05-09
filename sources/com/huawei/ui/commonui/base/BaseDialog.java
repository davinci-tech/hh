package com.huawei.ui.commonui.base;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Point;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.dfx.DfxUtils;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwlogsmodel.common.LogConfig;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.constant.ParamConstants;
import com.huawei.ui.commonui.columnsystem.HealthColumnSystem;
import defpackage.nrt;
import defpackage.nsn;
import defpackage.ntd;
import health.compact.a.CommonUtils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;

/* loaded from: classes.dex */
public class BaseDialog extends Dialog {
    private static final String RELEASE_TAG = "R_BaseDialog";
    private static final String TAG = "BaseDialog";
    private static final float WIDESCREEN_JUDGE_THRESHOLD = 0.85f;
    private static final int WIDTH_FROM_MARGIN = 48;
    private static final int WIDTH_HALF = 2;
    private static final int WIDTH_ONE_THIRD = 3;
    private static final int WIDTH_QUARTER = 4;
    private static boolean mIsDialogMapCleaned = false;
    public static int sSplashAdViewId;
    private final Handler mBuildHandler;
    private Context mContext;
    private int mDialogType;
    private int mGravity;
    private boolean mIsDeferredByAd;
    private static WeakHashMap<Dialog, String> mCleanDialogMap = new WeakHashMap<>();
    private static WeakHashMap<Dialog, String> sShowingDialogMap = new WeakHashMap<>();
    private static HashMap<Dialog, String> sAdHideDialogMap = new HashMap<>();

    public BaseDialog(Context context) {
        super(context);
        this.mDialogType = -1;
        this.mBuildHandler = new Handler();
        this.mGravity = 80;
        this.mContext = context;
        registerCleanDialog(context);
    }

    public BaseDialog(Context context, int i) {
        super(context, i);
        this.mDialogType = -1;
        this.mBuildHandler = new Handler();
        this.mGravity = 80;
        this.mContext = context;
        registerCleanDialog(context);
    }

    public BaseDialog(Context context, int i, int i2) {
        super(context, i);
        this.mDialogType = -1;
        this.mBuildHandler = new Handler();
        this.mGravity = 80;
        this.mContext = context;
        this.mDialogType = i2;
        registerCleanDialog(context);
    }

    public BaseDialog(Context context, boolean z, DialogInterface.OnCancelListener onCancelListener) {
        super(context, z, onCancelListener);
        this.mDialogType = -1;
        this.mBuildHandler = new Handler();
        this.mGravity = 80;
        this.mContext = context;
        registerCleanDialog(context);
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        if (!mIsDialogMapCleaned) {
            unregisterCleanDialog();
            unregisterShowingDialog();
        }
        super.dismiss();
    }

    public void configDialog() {
        int i = this.mDialogType;
        if (i != 0) {
            LogUtil.a(TAG, "mDialogType ", Integer.valueOf(i));
            Point cLb_ = nsn.cLb_();
            float f = this.mContext.getResources().getDisplayMetrics().density;
            HealthColumnSystem healthColumnSystem = new HealthColumnSystem(this.mContext, 12);
            healthColumnSystem.d(this.mContext, cLb_.x, cLb_.y, f);
            int dialogWidth = getDialogWidth(healthColumnSystem);
            Window window = getWindow();
            window.setDimAmount(nrt.a(this.mContext) ? 0.4f : 0.2f);
            WindowManager.LayoutParams attributes = window.getAttributes();
            int dimensionPixelSize = this.mContext.getResources().getDimensionPixelSize(R.dimen._2131362464_res_0x7f0a02a0);
            Context context = this.mContext;
            if ((context instanceof BaseActivity) && ((BaseActivity) context).isSupportFoldingModel()) {
                String currFoldModel = ((BaseActivity) this.mContext).getCurrFoldModel();
                LogUtil.h(TAG, "mContext currFoldModel", currFoldModel);
                attributes.width = dialogWidth;
                if (!nsn.ag(this.mContext)) {
                    attributes.y = dimensionPixelSize;
                }
                window.setAttributes(attributes);
                if (nsn.ag(this.mContext) || 2 == this.mContext.getResources().getConfiguration().orientation) {
                    WindowManager xF_ = CommonUtils.xF_();
                    if ("HORIZONTAL_FOLDING_MODE".equals(currFoldModel) && xF_ != null) {
                        DisplayMetrics displayMetrics = new DisplayMetrics();
                        xF_.getDefaultDisplay().getMetrics(displayMetrics);
                        attributes.y = displayMetrics.heightPixels / 4;
                    } else {
                        attributes.y = 0;
                    }
                    window.setAttributes(attributes);
                    LogUtil.h(TAG, "screen is ORIENTATION_LANDSCAPE .");
                    return;
                }
                window.setGravity(this.mGravity);
                return;
            }
            if (attributes.width == dialogWidth) {
                if (nsn.ag(this.mContext)) {
                    return;
                }
                if (!nsn.ag(this.mContext) && attributes.y == dimensionPixelSize) {
                    return;
                }
            }
            attributes.width = dialogWidth;
            if (!nsn.ag(this.mContext)) {
                attributes.y = dimensionPixelSize;
            }
            window.setAttributes(attributes);
            if (nsn.ag(this.mContext) || 2 == this.mContext.getResources().getConfiguration().orientation) {
                window.setGravity(16);
                LogUtil.a(TAG, "screen is ORIENTATION_LANDSCAPE .");
            } else {
                window.setGravity(this.mGravity);
            }
        }
    }

    private static void deferDialogByAd(BaseDialog baseDialog, final View view) {
        view.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() { // from class: com.huawei.ui.commonui.base.BaseDialog.2
            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewAttachedToWindow(View view2) {
            }

            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewDetachedFromWindow(View view2) {
                LogUtil.a(BaseDialog.TAG, "deferDialogPendingShow onViewDetachedFromWindow! tag = ", BaseDialog.this);
                BaseDialog.this.show();
                view.removeOnAttachStateChangeListener(this);
            }
        });
        baseDialog.mIsDeferredByAd = true;
    }

    @Override // android.app.Dialog
    public void show() {
        View findViewById;
        int i = sSplashAdViewId;
        if (i != 0) {
            Context context = this.mContext;
            if ((context instanceof Activity) && (findViewById = ((Activity) context).findViewById(i)) != null && findViewById.getVisibility() == 0 && !this.mIsDeferredByAd) {
                LogUtil.a(TAG, "show dialog but deferred by ad dialog = ", this);
                deferDialogByAd(this, findViewById);
                return;
            }
        }
        LogUtil.a(TAG, ParamConstants.CallbackMethod.ON_SHOW);
        configDialog();
        registerShowingDialog(this.mContext);
        try {
            if (Looper.myLooper() == this.mBuildHandler.getLooper()) {
                super.show();
            } else {
                LogUtil.h(TAG, "show() not in ui thread, post it, context = " + this.mContext);
                this.mBuildHandler.post(new Runnable() { // from class: com.huawei.ui.commonui.base.BaseDialog.5
                    @Override // java.lang.Runnable
                    public void run() {
                        BaseDialog.super.show();
                    }
                });
            }
        } catch (RuntimeException e) {
            LogUtil.b(TAG, "BaseDialog show RuntimeException: ", ExceptionUtils.d(e));
        }
    }

    private int getDialogWidth(HealthColumnSystem healthColumnSystem) {
        int h = healthColumnSystem.h();
        LogUtil.a(TAG, "getDialogWidth dialogWidth :", Integer.valueOf(h));
        Context context = this.mContext;
        Activity activity = context instanceof Activity ? (Activity) context : null;
        if (activity == null) {
            activity = BaseApplication.wa_();
        }
        if (activity == null) {
            return h;
        }
        boolean isInMultiWindowMode = activity.isInMultiWindowMode();
        boolean isHorizontalScreen = isHorizontalScreen(activity);
        boolean cLg_ = nsn.cLg_(activity);
        LogUtil.a(TAG, "getDialogWidth inMultiWindowMode:", Boolean.valueOf(isInMultiWindowMode), " isFreeformMode:", Boolean.valueOf(cLg_), " isHorizontalScreen:", Boolean.valueOf(isHorizontalScreen));
        if (!isInMultiWindowMode) {
            return h;
        }
        if (nsn.ag(activity) || (!cLg_ && !isHorizontalScreen)) {
            LogUtil.a(TAG, "configDialog isWidescreen or portrait split Screen");
            return h;
        }
        int h2 = nsn.h(activity) - 192;
        LogUtil.a(TAG, "getDialogWidth inMultiWindowMode dialogWidth:", Integer.valueOf(h2));
        return h2;
    }

    private boolean isHorizontalScreen(Activity activity) {
        int rotation = activity.getWindowManager().getDefaultDisplay().getRotation();
        return rotation == 1 || rotation == 3;
    }

    private boolean isWidescreenByWidthHeightRatio(Context context) {
        Display defaultDisplay = ((WindowManager) context.getSystemService(Constants.NATIVE_WINDOW_SUB_DIR)).getDefaultDisplay();
        Point point = new Point();
        defaultDisplay.getRealSize(point);
        int min = Math.min(point.x, point.y);
        int max = Math.max(point.x, point.y);
        return max != 0 && min >= 0 && ((float) min) / ((float) max) > WIDESCREEN_JUDGE_THRESHOLD;
    }

    private void registerCleanDialog(Context context) {
        if (isBaseActivity(context)) {
            registerCleanDialogImpl(this, context.toString());
        }
    }

    private boolean isBaseActivity(Context context) {
        if (context instanceof BaseActivity) {
            return true;
        }
        return context != null && context.toString().contains("com.huawei.hwarkuix.EntryAbilityActivity");
    }

    private void unregisterCleanDialog() {
        unregisterCleanDialogImpl(this);
    }

    private void registerShowingDialog(Context context) {
        LogUtil.a(TAG, "sShowingDialogMap,", sShowingDialogMap, "context:", context);
        if (isBaseActivity(context)) {
            sShowingDialogMap.put(this, context.toString());
        }
        if (LogConfig.i() || HandlerExecutor.b()) {
            return;
        }
        LogUtil.h(TAG, "registerShowingDialog ", DfxUtils.d(Thread.currentThread(), null));
    }

    private void unregisterShowingDialog() {
        sShowingDialogMap.remove(this);
    }

    public static void configureChangedShowDialog() {
        LogUtil.a(TAG, "configureChangedShowDialog");
        mIsDialogMapCleaned = true;
        WeakHashMap weakHashMap = new WeakHashMap();
        weakHashMap.putAll(sShowingDialogMap);
        Iterator it = weakHashMap.entrySet().iterator();
        while (it.hasNext()) {
            Dialog dialog = (Dialog) ((Map.Entry) it.next()).getKey();
            if (dialog instanceof BaseDialog) {
                BaseDialog baseDialog = (BaseDialog) dialog;
                Context context = baseDialog.mContext;
                if (context instanceof Activity) {
                    Activity activity = (Activity) context;
                    if (activity == null || activity.isFinishing() || activity.isDestroyed()) {
                        ReleaseLogUtil.d(RELEASE_TAG, "attach activity is gone");
                    } else {
                        baseDialog.configDialog();
                    }
                } else {
                    ReleaseLogUtil.d(RELEASE_TAG, "context not instanceof Activity");
                }
            }
        }
        mIsDialogMapCleaned = false;
    }

    public static void cleanAllDialog(Context context) {
        ArrayList<Dialog> cleanAllDialogImpl = cleanAllDialogImpl(context.toString());
        if (cleanAllDialogImpl == null) {
            return;
        }
        Iterator<Dialog> it = cleanAllDialogImpl.iterator();
        while (it.hasNext()) {
            it.next().dismiss();
        }
    }

    private static void registerCleanDialogImpl(Dialog dialog, String str) {
        mCleanDialogMap.put(dialog, str);
    }

    private static void unregisterCleanDialogImpl(Dialog dialog) {
        mCleanDialogMap.remove(dialog);
    }

    private static ArrayList<Dialog> cleanAllDialogImpl(String str) {
        clearShowDialogMap(str);
        int size = mCleanDialogMap.size();
        if (size == 0) {
            return new ArrayList<>();
        }
        Iterator<Map.Entry<Dialog, String>> it = mCleanDialogMap.entrySet().iterator();
        ArrayList<Dialog> arrayList = null;
        while (it.hasNext()) {
            Map.Entry<Dialog, String> next = it.next();
            if (str.equals(next.getValue())) {
                Dialog key = next.getKey();
                if (key != null) {
                    if (arrayList == null) {
                        arrayList = new ArrayList<>(size);
                    }
                    arrayList.add(key);
                }
                it.remove();
            }
        }
        return arrayList;
    }

    private static void clearShowDialogMap(String str) {
        Iterator<Map.Entry<Dialog, String>> it = sShowingDialogMap.entrySet().iterator();
        while (it.hasNext()) {
            if (str.equals(it.next().getValue())) {
                it.remove();
            }
        }
    }

    private static void registerAdHideDialog(Dialog dialog, Context context) {
        if (context instanceof BaseActivity) {
            LogUtil.a(TAG, "registerAdHideDialog");
            sAdHideDialogMap.put(dialog, context.toString());
        }
    }

    public static void updateShowAdNowStatus(Context context, boolean z) {
        LogUtil.a(TAG, "updateShowAdNowStatus, context: ", context, " isShowAdNow: ", Boolean.valueOf(z));
        if (z) {
            hideAllDialogOnAdShow(context);
        } else {
            restoreAllDialogOnAdFinish(context);
        }
    }

    private static void hideAllDialogOnAdShow(Context context) {
        for (Map.Entry<Dialog, String> entry : sShowingDialogMap.entrySet()) {
            Dialog key = entry.getKey();
            String value = entry.getValue();
            if (key != null && TextUtils.equals(context.toString(), value) && key.isShowing()) {
                LogUtil.a(TAG, "hide dialog on ad show, context: ", context, " dialog: ", key);
                key.hide();
                registerAdHideDialog(key, context);
            }
        }
    }

    private static void restoreAllDialogOnAdFinish(Context context) {
        Iterator<Map.Entry<Dialog, String>> it = sAdHideDialogMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Dialog, String> next = it.next();
            Dialog key = next.getKey();
            String value = next.getValue();
            if (key != null && TextUtils.equals(context.toString(), value)) {
                LogUtil.a(TAG, "show dialog on ad finish, context: ", context, " dialog: ", key);
                key.show();
                it.remove();
            }
        }
    }

    public static void clearResource() {
        clearAdHideDialogMap();
    }

    private static void clearAdHideDialogMap() {
        sAdHideDialogMap.clear();
    }

    @Override // android.app.Dialog
    public void setContentView(int i) {
        super.setContentView(i);
        ntd.b().cMC_(this);
    }

    @Override // android.app.Dialog
    public void setContentView(View view) {
        super.setContentView(view);
        ntd.b().cMC_(this);
    }

    @Override // android.app.Dialog
    public void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        super.setContentView(view, layoutParams);
        ntd.b().cMC_(this);
    }

    public void setWindowGravityParams(int i) {
        this.mGravity = i;
    }
}
