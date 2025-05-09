package com.huawei.featureuserprofile.route.bean;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

/* loaded from: classes3.dex */
public class KomootRouteBean {
    private int mPageNumber;
    private int mTotalElements;
    private int mTotalPages;
    private List<Tour> mTours;

    public List<Tour> getTours() {
        return this.mTours;
    }

    public void setTours(List<Tour> list) {
        this.mTours = list;
    }

    public int getTotalPages() {
        return this.mTotalPages;
    }

    public void setTotalPages(int i) {
        this.mTotalPages = i;
    }

    public int getPageNumber() {
        return this.mPageNumber;
    }

    public void setPageNumber(int i) {
        this.mPageNumber = i;
    }

    public int getTotalElements() {
        return this.mTotalElements;
    }

    public void setTotalElements(int i) {
        this.mTotalElements = i;
    }

    public static class Tour implements Parcelable {
        public static final Parcelable.Creator<Tour> CREATOR = new Parcelable.Creator<Tour>() { // from class: com.huawei.featureuserprofile.route.bean.KomootRouteBean.Tour.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public Tour createFromParcel(Parcel parcel) {
                return new Tour(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public Tour[] newArray(int i) {
                return new Tour[i];
            }
        };
        private String date;
        private Double distance;
        private long duration;
        private long id;
        private String name;
        private String previewSrc;
        private String sport;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public Tour() {
            this(null);
        }

        protected Tour(Parcel parcel) {
            if (parcel == null) {
                return;
            }
            this.id = parcel.readLong();
            this.sport = parcel.readString();
            this.date = parcel.readString();
            this.name = parcel.readString();
            if (parcel.readValue(Double.class.getClassLoader()) instanceof Double) {
                this.distance = (Double) parcel.readValue(Double.class.getClassLoader());
            }
            this.duration = parcel.readLong();
            this.previewSrc = parcel.readString();
        }

        public long getId() {
            return this.id;
        }

        public void setId(long j) {
            this.id = j;
        }

        public String getSport() {
            return this.sport;
        }

        public void setSport(String str) {
            this.sport = str;
        }

        public String getDate() {
            return this.date;
        }

        public void setDate(String str) {
            this.date = str;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String str) {
            this.name = str;
        }

        public Double getDistance() {
            return this.distance;
        }

        public void setDistance(Double d) {
            this.distance = d;
        }

        public long getDuration() {
            return this.duration;
        }

        public void setDuration(long j) {
            this.duration = j;
        }

        public String getPreviewSrc() {
            return this.previewSrc;
        }

        public void setPreviewSrc(String str) {
            this.previewSrc = str;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeLong(this.id);
            parcel.writeString(this.sport);
            parcel.writeString(this.date);
            parcel.writeString(this.name);
            parcel.writeValue(this.distance);
            parcel.writeLong(this.duration);
            parcel.writeString(this.previewSrc);
        }
    }
}
