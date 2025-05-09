package com.huawei.health.marketing.datatype;

import android.graphics.Bitmap;
import android.text.SpannableString;
import defpackage.npt;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class RecommendCardBean {
    private static final String TAG = "RecommendCardBean";
    private Bitmap backGround;
    private Integer buttonColor;
    private String buttonText;
    private String cardId;
    private String cardStatus;
    private npt clickSectionListener;
    private String eventId;
    private List<Integer> mLabelWeights;
    private String mSceneShowArticles;
    private String resourceId;
    private String subtitle;
    private SpannableString subtitleDataLine;
    private String title;
    private Integer titleColor;

    public RecommendCardBean(Builder builder) {
        this.mLabelWeights = new ArrayList(3);
        this.cardId = builder.cardId;
        this.cardStatus = builder.cardStatus;
        this.resourceId = builder.resourceId;
        this.eventId = builder.eventId;
        this.title = builder.title;
        this.subtitleDataLine = builder.subtitleDataLine;
        this.subtitle = builder.subtitle;
        this.titleColor = builder.titleColor;
        this.backGround = builder.backGround;
        this.clickSectionListener = builder.clickSectionListener;
        this.buttonColor = builder.buttonColor;
        this.buttonText = builder.buttonText;
        this.mLabelWeights = builder.mLabelWeights;
        this.mSceneShowArticles = builder.mSceneShowArticles;
    }

    public List<Integer> getLabelWeights() {
        return this.mLabelWeights;
    }

    public String getCardId() {
        return this.cardId;
    }

    public String getCardStatus() {
        return this.cardStatus;
    }

    public String getResourceId() {
        return this.resourceId;
    }

    public String getEventId() {
        return this.eventId;
    }

    public String getTitle() {
        return this.title;
    }

    public SpannableString getSubtitleDataLine() {
        return this.subtitleDataLine;
    }

    public String getSubtitle() {
        return this.subtitle;
    }

    public Integer getTitleColor() {
        return this.titleColor;
    }

    public Bitmap getBackGround() {
        return this.backGround;
    }

    public npt getClickSectionListener() {
        return this.clickSectionListener;
    }

    public Integer getButtonColor() {
        return this.buttonColor;
    }

    public String getButtonText() {
        return this.buttonText;
    }

    public String getSceneShowArticles() {
        return this.mSceneShowArticles;
    }

    public void setSceneShowArticles(String str) {
        this.mSceneShowArticles = str;
    }

    /* loaded from: classes8.dex */
    public static class Builder {
        private Bitmap backGround;
        private Integer buttonColor;
        private String buttonText;
        private String cardId;
        private String cardStatus;
        private npt clickSectionListener;
        private String eventId;
        private List<Integer> mLabelWeights = new ArrayList(3);
        private String mSceneShowArticles;
        private String resourceId;
        private String subtitle;
        private SpannableString subtitleDataLine;
        private String title;
        private Integer titleColor;

        public Builder setSceneShowArticles(String str) {
            this.mSceneShowArticles = str;
            return this;
        }

        public Builder setLabelWeights(List<Integer> list) {
            this.mLabelWeights = list;
            return this;
        }

        public Builder setCardId(String str) {
            this.cardId = str;
            return this;
        }

        public Builder setCardStatus(String str) {
            this.cardStatus = str;
            return this;
        }

        public Builder setResourceId(String str) {
            this.resourceId = str;
            return this;
        }

        public Builder setEventId(String str) {
            this.eventId = str;
            return this;
        }

        public Builder setTitle(String str) {
            this.title = str;
            return this;
        }

        public Builder setSubtitleDataLine(SpannableString spannableString) {
            this.subtitleDataLine = spannableString;
            return this;
        }

        public Builder setSubtitle(String str) {
            this.subtitle = str;
            return this;
        }

        public Builder setTitleColor(Integer num) {
            this.titleColor = num;
            return this;
        }

        public Builder setBackGround(Bitmap bitmap) {
            this.backGround = bitmap;
            return this;
        }

        public Builder setClickSectionListener(npt nptVar) {
            this.clickSectionListener = nptVar;
            return this;
        }

        public Builder setButtonColor(Integer num) {
            this.buttonColor = num;
            return this;
        }

        public Builder setButtonText(String str) {
            this.buttonText = str;
            return this;
        }

        public RecommendCardBean build() {
            return new RecommendCardBean(this);
        }
    }
}
