package com.huawei.phoneservice.feedbackcommon.entity;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes5.dex */
public class ProblemEntity implements Parcelable {
    public static final Parcelable.Creator<ProblemEntity> CREATOR = new d();

    /* renamed from: a, reason: collision with root package name */
    private String f8312a;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f8312a);
    }

    public void a(String str) {
        this.f8312a = str;
    }

    public String a() {
        return this.f8312a;
    }

    class d implements Parcelable.Creator<ProblemEntity> {
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public ProblemEntity[] newArray(int i) {
            return new ProblemEntity[i];
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public ProblemEntity createFromParcel(Parcel parcel) {
            return new ProblemEntity(parcel);
        }

        d() {
        }
    }

    public ProblemEntity(String str) {
        this.f8312a = str;
    }

    protected ProblemEntity(Parcel parcel) {
        this.f8312a = parcel.readString();
    }

    public ProblemEntity() {
    }
}
