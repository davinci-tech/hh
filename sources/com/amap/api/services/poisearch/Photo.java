package com.amap.api.services.poisearch;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes2.dex */
public final class Photo implements Parcelable {
    public static final Parcelable.Creator<Photo> CREATOR = new Parcelable.Creator<Photo>() { // from class: com.amap.api.services.poisearch.Photo.1
        @Override // android.os.Parcelable.Creator
        public final /* bridge */ /* synthetic */ Photo[] newArray(int i) {
            return null;
        }

        @Override // android.os.Parcelable.Creator
        public final /* synthetic */ Photo createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        private static Photo a(Parcel parcel) {
            return new Photo(parcel);
        }
    };

    /* renamed from: a, reason: collision with root package name */
    private String f1514a;
    private String b;

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public Photo() {
    }

    public Photo(String str, String str2) {
        this.f1514a = str;
        this.b = str2;
    }

    public final String getTitle() {
        return this.f1514a;
    }

    public final void setTitle(String str) {
        this.f1514a = str;
    }

    public final String getUrl() {
        return this.b;
    }

    public final void setUrl(String str) {
        this.b = str;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f1514a);
        parcel.writeString(this.b);
    }

    public Photo(Parcel parcel) {
        this.f1514a = parcel.readString();
        this.b = parcel.readString();
    }
}
