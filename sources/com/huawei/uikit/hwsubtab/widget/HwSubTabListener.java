package com.huawei.uikit.hwsubtab.widget;

import androidx.fragment.app.FragmentTransaction;
import defpackage.smy;

/* loaded from: classes7.dex */
public interface HwSubTabListener {
    void onSubTabReselected(smy smyVar, FragmentTransaction fragmentTransaction);

    void onSubTabSelected(smy smyVar, FragmentTransaction fragmentTransaction);

    void onSubTabUnselected(smy smyVar, FragmentTransaction fragmentTransaction);
}
