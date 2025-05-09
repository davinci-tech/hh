package com.huawei.hwcloudmodel.hwwear.hag.model.body;

/* loaded from: classes5.dex */
public class IntentAbilitiesBean {
    private String abilityId;
    private String apiKey;
    private String intentName;
    private SlotsBean slots;

    public static class SlotsBean {
    }

    public void setApiKey(String str) {
        this.apiKey = str;
    }

    public void setIntentName(String str) {
        this.intentName = str;
    }

    public void setAbilityId(String str) {
        this.abilityId = str;
    }

    public void setSlots(SlotsBean slotsBean) {
        this.slots = slotsBean;
    }

    public String toString() {
        return "IntentAbilitiesBean{intentName='" + this.intentName + "', abilityId='" + this.abilityId + "', slots=" + this.slots + ", apiKey='" + this.apiKey + "'}";
    }
}
