package com.huawei.health.suggestion.ui.fitness.adapter;

import androidx.fragment.app.FragmentTransaction;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.subtab.HealthSubTabListener;
import defpackage.smy;

/* loaded from: classes8.dex */
public class FitnessSubTabAdapter implements HealthSubTabListener {
    private FitnessOnSelectPositionListener e;

    public interface FitnessOnSelectPositionListener {
        void selectedPosition(int i, int i2, Object obj);
    }

    @Override // com.huawei.ui.commonui.subtab.HealthSubTabListener, com.huawei.uikit.hwsubtab.widget.HwSubTabListener
    public void onSubTabReselected(smy smyVar, FragmentTransaction fragmentTransaction) {
        LogUtil.c("FitnessSubTabAdapter", "onSubTabReselected:  ==> ");
        FitnessOnSelectPositionListener fitnessOnSelectPositionListener = this.e;
        if (fitnessOnSelectPositionListener != null) {
            fitnessOnSelectPositionListener.selectedPosition(smyVar.e(), smyVar.h(), smyVar.j());
        }
    }

    @Override // com.huawei.ui.commonui.subtab.HealthSubTabListener, com.huawei.uikit.hwsubtab.widget.HwSubTabListener
    public void onSubTabSelected(smy smyVar, FragmentTransaction fragmentTransaction) {
        LogUtil.c("FitnessSubTabAdapter", "onSubTabSelected:  ==> ");
        FitnessOnSelectPositionListener fitnessOnSelectPositionListener = this.e;
        if (fitnessOnSelectPositionListener != null) {
            fitnessOnSelectPositionListener.selectedPosition(smyVar.e(), smyVar.h(), smyVar.j());
        }
    }

    @Override // com.huawei.ui.commonui.subtab.HealthSubTabListener, com.huawei.uikit.hwsubtab.widget.HwSubTabListener
    public void onSubTabUnselected(smy smyVar, FragmentTransaction fragmentTransaction) {
        LogUtil.c("FitnessSubTabAdapter", "onSubTabUnselected:  ==> ");
    }
}
