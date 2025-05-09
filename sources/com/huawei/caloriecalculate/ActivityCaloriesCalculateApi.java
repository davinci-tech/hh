package com.huawei.caloriecalculate;

import defpackage.bdw;
import defpackage.bdy;

/* loaded from: classes.dex */
public interface ActivityCaloriesCalculateApi {
    double calculateCalories(long j);

    double calculateCalories(long j, bdy bdyVar);

    double calculateCaloriesFromSteps(long j, int i);

    float calculateCaloriesOnlyStep(long j);

    long calculateDurationByCalories(float f, int i, int i2);

    double calculateTotalCalories(long j, bdy bdyVar);

    double calculateTotalCaloriesFromSteps(long j, int i);

    void initCalculateCalories(long j, int i);

    void initUserInfo(bdw bdwVar);
}
