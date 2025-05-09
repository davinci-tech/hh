package com.huawei.hihealth;

import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class HiDataAggregateProOption implements Parcelable {
    public static final Parcelable.Creator<HiDataAggregateProOption> CREATOR = new Parcelable.Creator<HiDataAggregateProOption>() { // from class: com.huawei.hihealth.HiDataAggregateProOption.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: bup_, reason: merged with bridge method [inline-methods] */
        public HiDataAggregateProOption createFromParcel(Parcel parcel) {
            return new HiDataAggregateProOption(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public HiDataAggregateProOption[] newArray(int i) {
            return new HiDataAggregateProOption[i];
        }
    };
    private static final String DATA_FILTER = "dataFilter";
    private static final String PACKAGE_NAME = "packageName";
    private static final String WITHOUT_DEFAULT_ZERO = "withoutDefaultZero";
    private final HiAggregateOption mAggregateOption;
    private final ContentValues mValueHolder;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    private HiDataAggregateProOption(HiAggregateOption hiAggregateOption, ContentValues contentValues) {
        this.mAggregateOption = hiAggregateOption;
        this.mValueHolder = contentValues;
    }

    protected HiDataAggregateProOption(Parcel parcel) {
        this.mAggregateOption = (HiAggregateOption) parcel.readParcelable(HiAggregateOption.class.getClassLoader());
        this.mValueHolder = (ContentValues) parcel.readParcelable(ContentValues.class.getClassLoader());
    }

    public HiAggregateOption getAggregateOption() {
        return this.mAggregateOption;
    }

    public String getPackageName() {
        ContentValues contentValues = this.mValueHolder;
        if (contentValues != null) {
            return contentValues.getAsString("packageName");
        }
        return null;
    }

    public String getDataFilter() {
        ContentValues contentValues = this.mValueHolder;
        if (contentValues != null) {
            return contentValues.getAsString(DATA_FILTER);
        }
        return null;
    }

    public boolean getWithoutDefaultZero() {
        return getBooleanValue(WITHOUT_DEFAULT_ZERO);
    }

    private boolean getBooleanValue(String str) {
        ContentValues contentValues = this.mValueHolder;
        if (contentValues == null || !contentValues.containsKey(str) || this.mValueHolder.getAsBoolean(str) == null) {
            return false;
        }
        return this.mValueHolder.getAsBoolean(str).booleanValue();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.mAggregateOption, i);
        parcel.writeParcelable(this.mValueHolder, i);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        /* renamed from: a, reason: collision with root package name */
        private HiAggregateOption f3982a;
        private ContentValues e;

        public Builder c(HiAggregateOption hiAggregateOption) {
            this.f3982a = hiAggregateOption;
            return this;
        }

        public Builder c(String str) {
            buq_().put("packageName", str);
            return this;
        }

        public Builder b(String str) {
            buq_().put(HiDataAggregateProOption.DATA_FILTER, str);
            return this;
        }

        public Builder b(boolean z) {
            buq_().put(HiDataAggregateProOption.WITHOUT_DEFAULT_ZERO, Boolean.valueOf(z));
            return this;
        }

        public HiDataAggregateProOption c() {
            return new HiDataAggregateProOption(this.f3982a, this.e);
        }

        private ContentValues buq_() {
            if (this.e == null) {
                this.e = new ContentValues();
            }
            return this.e;
        }
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("HiDataAggregateProOption{mAggOpt=");
        stringBuffer.append(this.mAggregateOption);
        stringBuffer.append(",mValHld=").append(this.mValueHolder);
        stringBuffer.append('}');
        return stringBuffer.toString();
    }
}
