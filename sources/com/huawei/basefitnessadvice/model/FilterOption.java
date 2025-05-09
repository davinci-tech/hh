package com.huawei.basefitnessadvice.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

/* loaded from: classes3.dex */
public class FilterOption implements Parcelable {
    public static final Parcelable.Creator<FilterOption> CREATOR = new Parcelable.Creator<FilterOption>() { // from class: com.huawei.basefitnessadvice.model.FilterOption.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public FilterOption createFromParcel(Parcel parcel) {
            return new FilterOption(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public FilterOption[] newArray(int i) {
            return new FilterOption[i];
        }
    };

    @SerializedName("categoryId")
    private int mCategoryId;

    @SerializedName("name")
    private String mName;

    @SerializedName("value")
    private long mValue;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public FilterOption() {
    }

    protected FilterOption(Parcel parcel) {
        this.mName = parcel.readString();
        this.mValue = parcel.readLong();
        this.mCategoryId = parcel.readInt();
    }

    private FilterOption(Builder builder) {
        this.mName = builder.mName;
        this.mValue = builder.mValue;
        this.mCategoryId = builder.mCategoryId;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mName);
        parcel.writeLong(this.mValue);
        parcel.writeInt(this.mCategoryId);
    }

    /* loaded from: classes8.dex */
    public static final class Builder {
        private int mCategoryId;
        private String mName;
        private long mValue;

        public Builder name(String str) {
            this.mName = str;
            return this;
        }

        public Builder value(long j) {
            this.mValue = j;
            return this;
        }

        public Builder categoryId(int i) {
            this.mCategoryId = i;
            return this;
        }

        public FilterOption build() {
            return new FilterOption(this);
        }
    }

    public String getName() {
        return this.mName;
    }

    public long getValue() {
        return this.mValue;
    }

    public int getCategoryId() {
        return this.mCategoryId;
    }
}
