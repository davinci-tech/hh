package com.huawei.health.suggestion.ui.fitness.helper;

import android.content.Context;
import android.os.Bundle;
import com.huawei.android.fsm.HwFoldScreenManagerEx;
import com.huawei.android.os.SystemPropertiesEx;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.suggestion.ui.BaseStateActivity;
import com.huawei.health.suggestion.ui.fitness.activity.BaseCoachActivity;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.EnvironmentInfo;

/* loaded from: classes4.dex */
public class HwScreenFoldDisplayMode implements HwFoldScreenManagerEx.FoldableStateListener {
    private static final String TAG = "HwScreenFoldDisplayMode";
    private FoldableStateListener mFoldAbleStateListener;

    public HwScreenFoldDisplayMode(FoldableStateListener foldableStateListener) {
        this.mFoldAbleStateListener = foldableStateListener;
    }

    public void onStateChange(Bundle bundle) {
        LogUtil.a(TAG, "onStateChange");
        FoldableStateListener foldableStateListener = this.mFoldAbleStateListener;
        if (foldableStateListener != null) {
            foldableStateListener.aBY_(bundle);
        }
    }

    public static String getFoldableState() {
        if (!EnvironmentInfo.j()) {
            return "NORMAL_MODE";
        }
        try {
            LogUtil.h(TAG, "getFoldableState ", Integer.valueOf(HwFoldScreenManagerEx.getFoldableState()));
            return HwFoldScreenManagerEx.getFoldableState() == 3 ? "HORIZONTAL_FOLDING_MODE" : "NORMAL_MODE";
        } catch (Exception e) {
            LogUtil.b(TAG, "onStateChange exception.", ExceptionUtils.d(e));
            return "NORMAL_MODE";
        }
    }

    public static class FoldableStateListener {
        private Context e;

        public FoldableStateListener(Context context) {
            this.e = context;
        }

        public void aBY_(Bundle bundle) {
            if (bundle == null) {
                LogUtil.h(HwScreenFoldDisplayMode.TAG, "Bundle extra is null, return.");
                return;
            }
            if (EnvironmentInfo.j()) {
                try {
                    int i = bundle.getInt("fold_state", 0);
                    LogUtil.h(HwScreenFoldDisplayMode.TAG, "receive change, fold state " + i);
                    String str = (SystemPropertiesEx.getBoolean("hw_mc.multiwindow.fat_hover", false) && i == 3) ? "HORIZONTAL_FOLDING_MODE" : "NORMAL_MODE";
                    Context context = this.e;
                    if (context == null || !(context instanceof BaseStateActivity)) {
                        return;
                    }
                    ((BaseCoachActivity) context).reloadView(str);
                } catch (Exception e) {
                    LogUtil.b(HwScreenFoldDisplayMode.TAG, "onStateChange exception.", ExceptionUtils.d(e));
                }
            }
        }
    }
}
