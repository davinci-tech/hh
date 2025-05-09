package com.huawei.basefitnessadvice.model.intplan;

/* loaded from: classes3.dex */
public interface StatInfo {
    public static final String STAT_TYPE_CALORIE = "calorie";
    public static final String STAT_TYPE_DISTANCE = "distance";
    public static final String STAT_TYPE_DURATION = "duration";
    public static final String STAT_TYPE_PROGRESS = "progress";
    public static final String STAT_TYPE_WEIGHT = "weight";
    public static final String STAT_TYPE_PUNCH_DAY = "punchDays";
    public static final String[] ALL_STAT_KEY = {"progress", "calorie", "distance", "weight", "duration", STAT_TYPE_PUNCH_DAY};

    String getStatType();

    <T> T getValue();
}
