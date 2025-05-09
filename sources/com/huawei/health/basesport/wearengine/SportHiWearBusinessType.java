package com.huawei.health.basesport.wearengine;

/* loaded from: classes3.dex */
public enum SportHiWearBusinessType {
    FITNESS_RUN_PLAN_HANDLE_SHAKE(1),
    FITNESS_RUN_PLAN_INFO_FILE(2),
    FITNESS_CUSTOM_COURSE_FILE(3),
    TRACK_RQ_DATA_INFO_FILE(4),
    TRAJECTORY_HANDLE_SHAKE(5),
    TRAJECTORY_FILE(6),
    FITNESS_RUN_PLAN_FINISH(7),
    FITNESS_AI_FITNESS_PLAN_INFO_FILE(21),
    FITNESS_AI_FITNESS_PLAN_FINISH(22),
    TRAJECTORY_IMAGE_HANDLE_SHAKE(100),
    TRAJECTORY_IMAGE(101),
    TRAJECTORY_IMAGE_SMALL(102);

    int mTypeValue;

    SportHiWearBusinessType(int i) {
        this.mTypeValue = i;
    }

    public int getTypeValue() {
        return this.mTypeValue;
    }
}
