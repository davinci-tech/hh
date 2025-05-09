package com.huawei.health.marketing.datatype;

/* loaded from: classes8.dex */
public class FunctionCardContent {
    private String buttonName;
    private String cardId;
    private String cardSize;
    private String description;
    private boolean descriptionVisibility;
    private String extend;
    private Long itemEffectiveTime;
    private Long itemExpirationTime;
    private String labelGroupId;
    private String linkValue;
    private String picture;
    private int priority;
    private String smallPicture;
    private String theme;
    private boolean themeVisibility;

    private FunctionCardContent(Builder builder) {
        this.priority = builder.priority;
        this.theme = builder.theme;
        this.themeVisibility = builder.themeVisibility;
        this.description = builder.description;
        this.descriptionVisibility = builder.descriptionVisibility;
        this.picture = builder.picture;
        this.smallPicture = builder.smallPicture;
        this.buttonName = builder.buttonName;
        this.linkValue = builder.linkValue;
        this.labelGroupId = builder.labelGroupId;
        this.itemEffectiveTime = builder.itemEffectiveTime;
        this.itemExpirationTime = builder.itemExpirationTime;
        this.cardSize = builder.cardSize;
        this.cardId = builder.cardId;
        this.extend = builder.extend;
    }

    public static class Builder {
        private String buttonName;
        private String cardId;
        private String cardSize;
        private String description;
        private boolean descriptionVisibility;
        private String extend;
        private Long itemEffectiveTime;
        private Long itemExpirationTime;
        private String labelGroupId;
        private String linkValue;
        private String picture;
        private int priority;
        private String smallPicture;
        private String theme;
        private boolean themeVisibility;

        public Builder setPriority(int i) {
            this.priority = i;
            return this;
        }

        public Builder setTheme(String str) {
            this.theme = str;
            return this;
        }

        public void setSmallPicture(String str) {
            this.smallPicture = str;
        }

        public void setButtonName(String str) {
            this.buttonName = str;
        }

        public Builder setThemeVisibility(boolean z) {
            this.themeVisibility = z;
            return this;
        }

        public Builder setDescription(String str) {
            this.description = str;
            return this;
        }

        public Builder setDescriptionVisibility(boolean z) {
            this.descriptionVisibility = z;
            return this;
        }

        public Builder setPicture(String str) {
            this.picture = str;
            return this;
        }

        public Builder setLinkValue(String str) {
            this.linkValue = str;
            return this;
        }

        public Builder setLabelGroupId(String str) {
            this.labelGroupId = str;
            return this;
        }

        public Builder setItemEffectiveTime(Long l) {
            this.itemEffectiveTime = l;
            return this;
        }

        public Builder setItemExpirationTime(Long l) {
            this.itemExpirationTime = l;
            return this;
        }

        public Builder setCardSize(String str) {
            this.cardSize = str;
            return this;
        }

        public Builder setCardId(String str) {
            this.cardId = str;
            return this;
        }

        public Builder setExtend(String str) {
            this.extend = str;
            return this;
        }

        public FunctionCardContent build() {
            return new FunctionCardContent(this);
        }
    }

    public int getPriority() {
        return this.priority;
    }

    public String getTheme() {
        return this.theme;
    }

    public boolean isThemeVisibility() {
        return this.themeVisibility;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean isDescriptionVisibility() {
        return this.descriptionVisibility;
    }

    public String getPicture() {
        return this.picture;
    }

    public String getSmallPicture() {
        return this.smallPicture;
    }

    public String getButtonName() {
        return this.buttonName;
    }

    public String getLinkValue() {
        return this.linkValue;
    }

    public String getLabelGroupId() {
        return this.labelGroupId;
    }

    public Long getItemEffectiveTime() {
        return this.itemEffectiveTime;
    }

    public Long getItemExpirationTime() {
        return this.itemExpirationTime;
    }

    public String getCardSize() {
        return this.cardSize;
    }

    public String getCardId() {
        return this.cardId;
    }

    public String getExtend() {
        return this.extend;
    }

    public void setCardSize(String str) {
        this.cardSize = str;
    }

    public String toString() {
        return "FunctionCardContent{priority=" + this.priority + ", theme='" + this.theme + "', themeVisibility=" + this.themeVisibility + ", description='" + this.description + "', descriptionVisibility=" + this.descriptionVisibility + ", picture='" + this.picture + "', smallPicture='" + this.smallPicture + "', buttonName='" + this.buttonName + "', linkValue='" + this.linkValue + "', labelGroupId='" + this.labelGroupId + "', itemEffectiveTime=" + this.itemEffectiveTime + ", itemExpirationTime=" + this.itemExpirationTime + ", cardSize='" + this.cardSize + "', cardId='" + this.cardId + "', extend='" + this.extend + "'}";
    }
}
