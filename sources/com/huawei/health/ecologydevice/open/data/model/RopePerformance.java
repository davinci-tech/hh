package com.huawei.health.ecologydevice.open.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

/* loaded from: classes3.dex */
public class RopePerformance {
    private List<Performance> populationStats;
    private int resultCode;

    public int getResultCode() {
        return this.resultCode;
    }

    public void setResultCode(int i) {
        this.resultCode = i;
    }

    public List<Performance> getPopulationStats() {
        return this.populationStats;
    }

    public void setPopulationStats(List<Performance> list) {
        this.populationStats = list;
    }

    public String toString() {
        return "RopePerformance{resultCode=" + this.resultCode + ", populationStats=" + this.populationStats + '}';
    }

    public static class Performance implements Parcelable {
        public static final Parcelable.Creator<Performance> CREATOR = new Parcelable.Creator<Performance>() { // from class: com.huawei.health.ecologydevice.open.data.model.RopePerformance.Performance.2
            @Override // android.os.Parcelable.Creator
            /* renamed from: TN_, reason: merged with bridge method [inline-methods] */
            public Performance createFromParcel(Parcel parcel) {
                return new Performance(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public Performance[] newArray(int i) {
                return new Performance[i];
            }
        };
        private String portraitCategory;
        private List<Rank> portraitValue;
        private String userCategory;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        protected Performance(Parcel parcel) {
            if (parcel != null) {
                this.portraitCategory = parcel.readString();
                this.userCategory = parcel.readString();
                this.portraitValue = parcel.createTypedArrayList(Rank.CREATOR);
            }
        }

        public Performance(String str, String str2) {
            this.portraitCategory = str;
            this.userCategory = str2;
        }

        public Performance(String str, String str2, List<Rank> list) {
            this.portraitCategory = str;
            this.userCategory = str2;
            this.portraitValue = list;
        }

        public String getPortraitCategory() {
            return this.portraitCategory;
        }

        public void setPortraitCategory(String str) {
            this.portraitCategory = str;
        }

        public String getUserCategory() {
            return this.userCategory;
        }

        public void setUserCategory(String str) {
            this.userCategory = str;
        }

        public List<Rank> getPortraitValue() {
            return this.portraitValue;
        }

        public void setPortraitValue(List<Rank> list) {
            this.portraitValue = list;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            if (parcel != null) {
                parcel.writeString(this.portraitCategory);
                parcel.writeString(this.userCategory);
                parcel.writeTypedList(this.portraitValue);
            }
        }

        public String toString() {
            return "Performance{portraitCategory='" + this.portraitCategory + "', userCategory='" + this.userCategory + "', portraitValue=" + this.portraitValue + '}';
        }
    }

    public static class Rank implements Parcelable {
        public static final Parcelable.Creator<Rank> CREATOR = new Parcelable.Creator<Rank>() { // from class: com.huawei.health.ecologydevice.open.data.model.RopePerformance.Rank.1
            @Override // android.os.Parcelable.Creator
            /* renamed from: TO_, reason: merged with bridge method [inline-methods] */
            public Rank createFromParcel(Parcel parcel) {
                return new Rank(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public Rank[] newArray(int i) {
                return new Rank[i];
            }
        };
        private float threshold;
        private float value;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        protected Rank(Parcel parcel) {
            if (parcel != null) {
                this.value = parcel.readFloat();
                this.threshold = parcel.readFloat();
            }
        }

        public Rank(float f, float f2) {
            this.value = f;
            this.threshold = f2;
        }

        public float getValue() {
            return this.value;
        }

        public void setValue(float f) {
            this.value = f;
        }

        public float getThreshold() {
            return this.threshold;
        }

        public void setThreshold(float f) {
            this.threshold = f;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            if (parcel != null) {
                parcel.writeFloat(this.value);
                parcel.writeFloat(this.threshold);
            }
        }

        public String toString() {
            return "Rank{value=" + this.value + ", threshold=" + this.threshold + '}';
        }
    }
}
