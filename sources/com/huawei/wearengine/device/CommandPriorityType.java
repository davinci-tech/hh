package com.huawei.wearengine.device;

/* loaded from: classes9.dex */
public enum CommandPriorityType {
    COMMAND_PRIORITY_NORMAL(1),
    COMMAND_PRIORITY_URGENT(2);

    private static final int NORMAL_COMMAND_PRIORITY = 1;
    private static final int URGENT_COMMAND_PRIORITY = 2;
    private int mType;

    CommandPriorityType(int i) {
        this.mType = i;
    }

    public static CommandPriorityType valueOf(int i) {
        if (i == 1) {
            return COMMAND_PRIORITY_NORMAL;
        }
        if (i != 2) {
            return null;
        }
        return COMMAND_PRIORITY_URGENT;
    }

    public int value() {
        return this.mType;
    }
}
