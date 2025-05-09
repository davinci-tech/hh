package com.huawei.basefitnessadvice.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class Navigation implements Parcelable {
    public static final Parcelable.Creator<Navigation> CREATOR = new Parcelable.Creator<Navigation>() { // from class: com.huawei.basefitnessadvice.model.Navigation.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Navigation createFromParcel(Parcel parcel) {
            return new Navigation(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Navigation[] newArray(int i) {
            return new Navigation[i];
        }
    };

    @SerializedName("aggregateDisplayStyle")
    private int mAggregateDisplayStyle;

    @SerializedName("categoryId")
    private int mCategoryId;

    @SerializedName("type")
    private int mCategoryType;

    @SerializedName("isDisplayNavigation")
    private int mDisplayNavigation;

    @SerializedName("isDisplayNavigationIcon")
    private int mDisplayNavigationIcon;

    @SerializedName("filterConditions")
    private List<FilterCondition> mFilterConditions;

    @SerializedName("hasRecommended")
    private int mHasRecommended;

    @SerializedName("name")
    private String mName;

    @SerializedName("navigationIconUrl")
    private String mNavigationIconUrl;

    @SerializedName("navigationSelectedIconUrl")
    private String mNavigationSelectedIconUrl;

    @SerializedName("positionId")
    private int mPositionId;

    @SerializedName("value")
    private int mValue;

    /* loaded from: classes8.dex */
    public static final class DisplayStyle {
        public static final int RECTANGLE_DISPLAY = 0;
        public static final int SQUARE_DISPLAY = 1;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public Navigation() {
    }

    protected Navigation(Parcel parcel) {
        this.mName = parcel.readString();
        this.mValue = parcel.readInt();
        this.mCategoryType = parcel.readInt();
        this.mDisplayNavigation = parcel.readInt();
        this.mDisplayNavigationIcon = parcel.readInt();
        this.mNavigationIconUrl = parcel.readString();
        this.mNavigationSelectedIconUrl = parcel.readString();
        this.mHasRecommended = parcel.readInt();
        this.mPositionId = parcel.readInt();
        this.mCategoryId = parcel.readInt();
        this.mAggregateDisplayStyle = parcel.readInt();
        ArrayList arrayList = new ArrayList();
        this.mFilterConditions = arrayList;
        parcel.readList(arrayList, FilterCondition.class.getClassLoader());
    }

    private Navigation(Builder builder) {
        this.mName = builder.mName;
        this.mValue = builder.mValue;
        this.mCategoryType = builder.mCategoryType;
        this.mDisplayNavigation = builder.mDisplayNavigation;
        this.mDisplayNavigationIcon = builder.mDisplayNavigationIcon;
        this.mNavigationIconUrl = builder.mNavigationIconUrl;
        this.mNavigationSelectedIconUrl = builder.mNavigationSelectedIconUrl;
        this.mHasRecommended = builder.mHasRecommended;
        this.mPositionId = builder.mPositionId;
        this.mCategoryId = builder.mCategoryId;
        this.mAggregateDisplayStyle = builder.mAggregateDisplayStyle;
        this.mFilterConditions = builder.mFilterConditions;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mName);
        parcel.writeInt(this.mValue);
        parcel.writeInt(this.mCategoryType);
        parcel.writeInt(this.mDisplayNavigation);
        parcel.writeInt(this.mDisplayNavigationIcon);
        parcel.writeString(this.mNavigationIconUrl);
        parcel.writeString(this.mNavigationSelectedIconUrl);
        parcel.writeInt(this.mHasRecommended);
        parcel.writeInt(this.mPositionId);
        parcel.writeInt(this.mCategoryId);
        parcel.writeInt(this.mAggregateDisplayStyle);
        parcel.writeList(this.mFilterConditions);
    }

    public static final class Builder {
        private int mAggregateDisplayStyle;
        private int mCategoryId;
        private int mCategoryType;
        private int mDisplayNavigation;
        private int mDisplayNavigationIcon;
        private List<FilterCondition> mFilterConditions;
        private int mHasRecommended;
        private String mName;
        private String mNavigationIconUrl;
        private String mNavigationSelectedIconUrl;
        private int mPositionId;
        private int mValue;

        public Builder name(String str) {
            this.mName = str;
            return this;
        }

        public Builder value(int i) {
            this.mValue = i;
            return this;
        }

        public Builder categoryType(int i) {
            this.mCategoryType = i;
            return this;
        }

        public Builder displayNavigation(int i) {
            this.mDisplayNavigation = i;
            return this;
        }

        public Builder displayNavigationIcon(int i) {
            this.mDisplayNavigationIcon = i;
            return this;
        }

        public Builder navigationIconUrl(String str) {
            this.mNavigationIconUrl = str;
            return this;
        }

        public Builder navigationSelectedIconUrl(String str) {
            this.mNavigationSelectedIconUrl = str;
            return this;
        }

        public Builder hasRecommended(int i) {
            this.mHasRecommended = i;
            return this;
        }

        public Builder positionId(int i) {
            this.mPositionId = i;
            return this;
        }

        public Builder categoryId(int i) {
            this.mCategoryId = i;
            return this;
        }

        public Builder filterConditions(List<FilterCondition> list) {
            this.mFilterConditions = list;
            return this;
        }

        public Builder displayStyle(int i) {
            this.mAggregateDisplayStyle = i;
            return this;
        }

        public Navigation build() {
            return new Navigation(this);
        }
    }

    public String getName() {
        return this.mName;
    }

    public int getValue() {
        return this.mValue;
    }

    public int getCategoryType() {
        return this.mCategoryType;
    }

    public int getDisplayNavigation() {
        return this.mDisplayNavigation;
    }

    public int getDisplayNavigationIcon() {
        return this.mDisplayNavigationIcon;
    }

    public String getNavigationIconUrl() {
        return this.mNavigationIconUrl;
    }

    public String getNavigationSelectedIconUrl() {
        return this.mNavigationSelectedIconUrl;
    }

    public int getHasRecommended() {
        return this.mHasRecommended;
    }

    public int getPositionId() {
        return this.mPositionId;
    }

    public int getCategoryId() {
        return this.mCategoryId;
    }

    public int getAggregateDisplayStyle() {
        return this.mAggregateDisplayStyle;
    }

    public List<FilterCondition> getFilterConditions() {
        return this.mFilterConditions;
    }

    public boolean isDisplaySquareStyle() {
        return this.mAggregateDisplayStyle == 1;
    }
}
