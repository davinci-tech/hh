package com.huawei.health.suggestion.ui.fitness.mvp;

import android.view.View;

/* loaded from: classes4.dex */
public interface PreViewStatusContract {
    void hideOpenView(View... viewArr);

    void normalStarPlayView();

    void openVipView();

    void payCourseHasVipPrice();

    void payCourseHasVipPriceByOpenVip();

    void payCourseHasVipPriceNotOpenVip();

    void payCourseNotVipPriceView();
}
