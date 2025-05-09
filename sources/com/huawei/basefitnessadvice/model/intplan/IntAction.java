package com.huawei.basefitnessadvice.model.intplan;

import defpackage.mnt;

/* loaded from: classes.dex */
public interface IntAction {
    String getActionId();

    mnt getActionInfo();

    ActionType getActionType();

    int getFeedback();

    int getPunchFlag();

    long getPunchTime();

    String getRecordId();

    long getTimePeriod();

    /* loaded from: classes3.dex */
    public enum ActionType {
        WORKOUT(0),
        RUN(1),
        FOOD(2);

        private int type;

        ActionType(int i) {
            this.type = i;
        }

        public int getType() {
            return this.type;
        }

        public static ActionType getActionType(int i) {
            for (ActionType actionType : values()) {
                if (actionType.getType() == i) {
                    return actionType;
                }
            }
            return null;
        }
    }
}
