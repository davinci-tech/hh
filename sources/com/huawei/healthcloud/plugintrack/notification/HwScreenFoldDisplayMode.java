package com.huawei.healthcloud.plugintrack.notification;

import com.huawei.android.fsm.HwFoldScreenManagerEx;

/* loaded from: classes4.dex */
public class HwScreenFoldDisplayMode implements HwFoldScreenManagerEx.FoldDisplayModeListener {
    private ScreenFoldDisplayModeListener mFoldDisplayModeListener;

    public interface ScreenFoldDisplayModeListener {
        void onScreenDisplayModeChange(int i);
    }

    public HwScreenFoldDisplayMode(ScreenFoldDisplayModeListener screenFoldDisplayModeListener) {
        this.mFoldDisplayModeListener = screenFoldDisplayModeListener;
    }

    public void onScreenDisplayModeChange(int i) {
        ScreenFoldDisplayModeListener screenFoldDisplayModeListener = this.mFoldDisplayModeListener;
        if (screenFoldDisplayModeListener != null) {
            screenFoldDisplayModeListener.onScreenDisplayModeChange(i);
        }
    }
}
