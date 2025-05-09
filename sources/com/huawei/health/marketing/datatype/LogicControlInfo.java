package com.huawei.health.marketing.datatype;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class LogicControlInfo {

    @SerializedName("ability")
    private List<String> ability;

    @SerializedName("chipVersion")
    private VersionControlInfo chipVersion;

    @SerializedName("osVersion")
    private VersionControlInfo osVersion;

    public VersionControlInfo getOsVersion() {
        return this.osVersion;
    }

    public VersionControlInfo getChipVersion() {
        return this.chipVersion;
    }

    public List<String> getAbility() {
        return this.ability;
    }

    public static class VersionControlInfo {

        @SerializedName("max")
        private Map<String, String> max;

        @SerializedName("min")
        private Map<String, String> min;

        @SerializedName("supportList")
        private Map<String, List<String>> supportList;

        @SerializedName("unSupportList")
        private Map<String, List<String>> unSupportList;

        public Map<String, String> getMin() {
            return this.min;
        }

        public Map<String, String> getMax() {
            return this.max;
        }

        public Map<String, List<String>> getSupportList() {
            return this.supportList;
        }

        public Map<String, List<String>> getUnSupportList() {
            return this.unSupportList;
        }
    }
}
