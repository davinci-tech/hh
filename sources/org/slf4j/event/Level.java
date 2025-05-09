package org.slf4j.event;

import com.huawei.operation.utils.Constants;

/* loaded from: classes7.dex */
public enum Level {
    ERROR(40, Constants.LOG_ERROR),
    WARN(30, Constants.LOG_WARN),
    INFO(20, "INFO"),
    DEBUG(10, Constants.LOG_DEBUG),
    TRACE(0, "TRACE");

    private int levelInt;
    private String levelStr;

    Level(int i, String str) {
        this.levelInt = i;
        this.levelStr = str;
    }

    public int toInt() {
        return this.levelInt;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.levelStr;
    }
}
