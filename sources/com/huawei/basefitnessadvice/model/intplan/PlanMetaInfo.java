package com.huawei.basefitnessadvice.model.intplan;

import com.huawei.basefitnessadvice.model.intplan.IntPlan;

/* loaded from: classes3.dex */
public interface PlanMetaInfo {
    String getCardImage();

    int getDayCount();

    String getDescription();

    int getDisplayStyle();

    int getExeDayNum();

    String getExeDays();

    String getName();

    String getOpenPageBigImage();

    String getPicture();

    int getPlanCategory();

    String getPlanId();

    int getPlanStatus();

    String getPlanTempId();

    IntPlan.PlanType getPlanType();

    String getVersion();

    int getWeekCount();
}
