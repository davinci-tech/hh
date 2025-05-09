package com.huawei.hwcloudmodel.hwwear.hag.model.body;

import java.util.List;

/* loaded from: classes5.dex */
public class InquireBean {
    private List<IntentsBean> intents;
    private String selectMode;

    public void setSelectMode(String str) {
        this.selectMode = str;
    }

    public void setIntents(List<IntentsBean> list) {
        this.intents = list;
    }

    public static class IntentsBean {
        private String channel;
        private List<IntentAbilitiesBean> intentAbilities;
        private String intentCategoryId;
        private String intentSN;

        public void setIntentSn(String str) {
            this.intentSN = str;
        }

        public void setChannel(String str) {
            this.channel = str;
        }

        public void setIntentCategoryId(String str) {
            this.intentCategoryId = str;
        }

        public void setIntentAbilities(List<IntentAbilitiesBean> list) {
            this.intentAbilities = list;
        }

        public String toString() {
            return "IntentsBean{intentSN='" + this.intentSN + "', channel='" + this.channel + "', intentCategoryId='" + this.intentCategoryId + "', intentAbilities=" + this.intentAbilities + '}';
        }
    }

    public String toString() {
        return "InquireBean{selectMode='" + this.selectMode + "', intents=" + this.intents + '}';
    }
}
