package com.huawei.hihealth;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class HiMetadataFilter implements Parcelable {
    public static final Parcelable.Creator<HiMetadataFilter> CREATOR = new Parcelable.Creator<HiMetadataFilter>() { // from class: com.huawei.hihealth.HiMetadataFilter.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: buP_, reason: merged with bridge method [inline-methods] */
        public HiMetadataFilter createFromParcel(Parcel parcel) {
            return new HiMetadataFilter(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public HiMetadataFilter[] newArray(int i) {
            return new HiMetadataFilter[i];
        }
    };
    private List<MetadataFilterExpression> mFilterOptions;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public HiMetadataFilter(List<MetadataFilterExpression> list) {
        this.mFilterOptions = new ArrayList(10);
        if (list == null) {
            throw new InvalidParameterException("HiMetadataFilter param null");
        }
        this.mFilterOptions = list;
    }

    public HiMetadataFilter(MetadataFilterExpression metadataFilterExpression) {
        ArrayList arrayList = new ArrayList(10);
        this.mFilterOptions = arrayList;
        if (metadataFilterExpression == null) {
            throw new InvalidParameterException("HiMetadataFilter param error");
        }
        arrayList.add(metadataFilterExpression);
    }

    protected HiMetadataFilter(Parcel parcel) {
        this.mFilterOptions = new ArrayList(10);
        this.mFilterOptions = parcel.createTypedArrayList(MetadataFilterExpression.CREATOR);
    }

    public List<MetadataFilterExpression> getFilterOptions() {
        return this.mFilterOptions;
    }

    public void setFilterOptions(List<MetadataFilterExpression> list) {
        this.mFilterOptions = list;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(this.mFilterOptions);
    }

    public String toString() {
        return "HiMetadataFilter{filterOptions=" + this.mFilterOptions + '}';
    }

    public static class MetadataFilterExpression implements Parcelable {
        public static final Parcelable.Creator<MetadataFilterExpression> CREATOR = new Parcelable.Creator<MetadataFilterExpression>() { // from class: com.huawei.hihealth.HiMetadataFilter.MetadataFilterExpression.1
            @Override // android.os.Parcelable.Creator
            /* renamed from: buQ_, reason: merged with bridge method [inline-methods] */
            public MetadataFilterExpression createFromParcel(Parcel parcel) {
                return new MetadataFilterExpression(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public MetadataFilterExpression[] newArray(int i) {
                return new MetadataFilterExpression[i];
            }
        };
        private boolean mIsString;
        private String mKey;
        private String mValue;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public MetadataFilterExpression(String str, Object obj, boolean z) {
            this.mKey = str;
            this.mValue = String.valueOf(obj);
            this.mIsString = z;
        }

        public MetadataFilterExpression(String str, Object obj) {
            this(str, obj, true);
        }

        protected MetadataFilterExpression(Parcel parcel) {
            this.mKey = parcel.readString();
            this.mValue = parcel.readString();
            this.mIsString = Build.VERSION.SDK_INT >= 29 ? parcel.readBoolean() : parcel.readInt() != 0;
        }

        public String getKey() {
            return this.mKey;
        }

        public void setKey(String str) {
            this.mKey = str;
        }

        public String getValue() {
            return this.mValue;
        }

        public void setValue(String str) {
            this.mValue = str;
        }

        public boolean isIsString() {
            return this.mIsString;
        }

        public void setIsString(boolean z) {
            this.mIsString = z;
        }

        public String toSqlString() {
            if (this.mIsString) {
                return "%\"" + this.mKey + "\":\"" + this.mValue + "\"%";
            }
            return "%\"" + this.mKey + "\":" + this.mValue + "%";
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.mKey);
            parcel.writeString(this.mValue);
            if (Build.VERSION.SDK_INT >= 29) {
                parcel.writeBoolean(this.mIsString);
            } else {
                parcel.writeInt(this.mIsString ? 1 : 0);
            }
        }
    }
}
