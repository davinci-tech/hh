package com.huawei.health.marketing.datatype;

import com.huawei.operation.utils.Constants;
import java.util.List;

/* loaded from: classes3.dex */
public class MarketingRule {
    private final String backupResourceId;
    private final int backupSwitch;
    private final String cloudLabelGroupId;
    private final CycleRuleBase triggerCycle;
    private final List<TriggerEventBase> triggerEvents;
    private final String userLabels;

    public MarketingRule(Builder builder) {
        this.userLabels = builder.userLabels;
        this.triggerEvents = builder.triggerEvents;
        this.triggerCycle = builder.triggerCycle;
        this.cloudLabelGroupId = builder.cloudLabelGroupId;
        this.backupResourceId = builder.backupResourceId;
        this.backupSwitch = builder.backupSwitch;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("triggerEvents: ");
        Object obj = this.triggerEvents;
        if (obj == null) {
            obj = Constants.NULL;
        }
        sb.append(obj);
        return sb.toString();
    }

    /* loaded from: classes8.dex */
    public static class Builder {
        private String backupResourceId;
        private int backupSwitch;
        private String cloudLabelGroupId;
        private CycleRuleBase triggerCycle;
        private List<TriggerEventBase> triggerEvents;
        private String userLabels;

        public Builder setUserLabels(String str) {
            this.userLabels = str;
            return this;
        }

        public Builder setTriggerEvents(List<TriggerEventBase> list) {
            this.triggerEvents = list;
            return this;
        }

        public Builder setTriggerCycle(CycleRuleBase cycleRuleBase) {
            this.triggerCycle = cycleRuleBase;
            return this;
        }

        public Builder setCloudLabelGroupId(String str) {
            this.cloudLabelGroupId = str;
            return this;
        }

        public Builder setBackupResourceId(String str) {
            this.backupResourceId = str;
            return this;
        }

        public Builder setBackupSwitch(int i) {
            this.backupSwitch = i;
            return this;
        }

        public MarketingRule build() {
            return new MarketingRule(this);
        }
    }

    public String getUserLabels() {
        return this.userLabels;
    }

    public List<TriggerEventBase> getTriggerEvents() {
        return this.triggerEvents;
    }

    public CycleRuleBase getTriggerCycle() {
        return this.triggerCycle;
    }

    public String getCloudLabelGroupId() {
        return this.cloudLabelGroupId;
    }

    public String getBackupResourceId() {
        return this.backupResourceId;
    }

    public int getBackupSwitch() {
        return this.backupSwitch;
    }
}
