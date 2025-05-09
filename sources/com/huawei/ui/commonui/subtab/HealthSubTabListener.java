package com.huawei.ui.commonui.subtab;

import androidx.fragment.app.FragmentTransaction;
import com.huawei.uikit.hwsubtab.widget.HwSubTabListener;
import defpackage.smy;

/* loaded from: classes6.dex */
public interface HealthSubTabListener extends HwSubTabListener {
    @Override // com.huawei.uikit.hwsubtab.widget.HwSubTabListener
    void onSubTabReselected(smy smyVar, FragmentTransaction fragmentTransaction);

    @Override // com.huawei.uikit.hwsubtab.widget.HwSubTabListener
    void onSubTabSelected(smy smyVar, FragmentTransaction fragmentTransaction);

    @Override // com.huawei.uikit.hwsubtab.widget.HwSubTabListener
    void onSubTabUnselected(smy smyVar, FragmentTransaction fragmentTransaction);
}
