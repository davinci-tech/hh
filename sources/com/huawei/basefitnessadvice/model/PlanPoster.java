package com.huawei.basefitnessadvice.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/* loaded from: classes3.dex */
public class PlanPoster {

    @SerializedName("equityPictureForPad")
    String mEquityPictureForPad;

    @SerializedName("equityPictureForPhone")
    String mEquityPictureForPhone;

    @SerializedName("memberEquityPictureForPad")
    String mMemberEquityPictureForPad;

    @SerializedName("memberEquityPictureForPhone")
    String mMemberEquityPictureForPhone;

    @SerializedName("picturesForPad")
    List<String> mPicturesForPad;

    @SerializedName("picturesForPhone")
    List<String> mPicturesForPhone;

    private PlanPoster(Builder builder) {
        this.mPicturesForPhone = builder.mPicturesForPhone;
        this.mPicturesForPad = builder.mPicturesForPad;
        this.mMemberEquityPictureForPhone = builder.mMemberEquityPictureForPhone;
        this.mMemberEquityPictureForPad = builder.mMemberEquityPictureForPad;
        this.mEquityPictureForPhone = builder.mEquityPictureForPhone;
        this.mEquityPictureForPad = builder.mEquityPictureForPad;
    }

    /* loaded from: classes8.dex */
    public static final class Builder {
        private String mEquityPictureForPad;
        private String mEquityPictureForPhone;
        private String mMemberEquityPictureForPad;
        private String mMemberEquityPictureForPhone;
        private List<String> mPicturesForPad;
        private List<String> mPicturesForPhone;

        public PlanPoster build() {
            return new PlanPoster(this);
        }

        public Builder setPicturesForPhone(List<String> list) {
            this.mPicturesForPhone = list;
            return this;
        }

        public Builder setPicturesForPad(List<String> list) {
            this.mPicturesForPad = list;
            return this;
        }

        public Builder setMemberEquityPictureForPhone(String str) {
            this.mMemberEquityPictureForPhone = str;
            return this;
        }

        public Builder setMemberEquityPictureForPad(String str) {
            this.mMemberEquityPictureForPad = str;
            return this;
        }

        public Builder setEquityPictureForPhone(String str) {
            this.mEquityPictureForPhone = str;
            return this;
        }

        public Builder setEquityPictureForPad(String str) {
            this.mEquityPictureForPad = str;
            return this;
        }
    }

    public List<String> getPicturesForPhone() {
        return this.mPicturesForPhone;
    }

    public List<String> getPicturesForPad() {
        return this.mPicturesForPad;
    }

    public String getMemberEquityPictureForPhone() {
        return this.mMemberEquityPictureForPhone;
    }

    public String getMemberEquityPictureForPad() {
        return this.mMemberEquityPictureForPad;
    }

    public String toString() {
        return "PlanPoster{mPicturesForPhone=" + this.mPicturesForPhone + ", mPicturesForPad=" + this.mPicturesForPad + ", mMemberEquityPictureForPhone='" + this.mMemberEquityPictureForPhone + "', mMemberEquityPictureForPad='" + this.mMemberEquityPictureForPad + "', mEquityPictureForPhone='" + this.mEquityPictureForPhone + "', mEquityPictureForPad='" + this.mEquityPictureForPad + "'}";
    }
}
