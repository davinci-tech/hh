package com.huawei.basefitnessadvice.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/* loaded from: classes3.dex */
public class FilterCondition implements Parcelable {
    public static final Parcelable.Creator<FilterCondition> CREATOR = new Parcelable.Creator<FilterCondition>() { // from class: com.huawei.basefitnessadvice.model.FilterCondition.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public FilterCondition createFromParcel(Parcel parcel) {
            return new FilterCondition(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public FilterCondition[] newArray(int i) {
            return new FilterCondition[i];
        }
    };

    @SerializedName("categoryId")
    private int mCategoryId;

    @SerializedName("filterOptions")
    private List<FilterOption> mFilterOptions;

    @SerializedName("name")
    private String mName;

    @SerializedName("type")
    private int mType;

    @SerializedName("value")
    private long mValue;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public FilterCondition() {
    }

    protected FilterCondition(Parcel parcel) {
        this.mName = parcel.readString();
        this.mValue = parcel.readLong();
        this.mCategoryId = parcel.readInt();
        this.mType = parcel.readInt();
        ArrayList arrayList = new ArrayList();
        this.mFilterOptions = arrayList;
        parcel.readList(arrayList, FilterOption.class.getClassLoader());
    }

    private FilterCondition(Builder builder) {
        this.mName = builder.mName;
        this.mValue = builder.mValue;
        this.mCategoryId = builder.mCategoryId;
        this.mType = builder.mType;
        this.mFilterOptions = builder.mFilterOptions;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mName);
        parcel.writeLong(this.mValue);
        parcel.writeInt(this.mCategoryId);
        parcel.writeInt(this.mType);
        parcel.writeList(this.mFilterOptions);
    }

    public static final class Builder {
        private int mCategoryId;
        private List<FilterOption> mFilterOptions;
        private String mName;
        private int mType;
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

        public Builder type(int i) {
            this.mType = i;
            return this;
        }

        public Builder filterOptions(List<FilterOption> list) {
            this.mFilterOptions = list;
            return this;
        }

        public FilterCondition build() {
            return new FilterCondition(this);
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

    public int getType() {
        return this.mType;
    }

    public List<FilterOption> getFilterOptions() {
        return this.mFilterOptions;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && this.mValue == ((FilterCondition) obj).mValue;
    }

    public int hashCode() {
        return Objects.hash(this.mName, Long.valueOf(this.mValue), Integer.valueOf(this.mCategoryId), Integer.valueOf(this.mType), this.mFilterOptions);
    }
}
