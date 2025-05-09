package com.huawei.phoneservice.faq.base.entity;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import com.huawei.phoneservice.faq.base.constants.FaqConstants;
import java.io.Serializable;
import java.util.List;

/* loaded from: classes5.dex */
public class FaqSdkServiceUrlResponse implements Parcelable, Serializable {
    public static final Parcelable.Creator<FaqSdkServiceUrlResponse> CREATOR = new a();
    private static final long serialVersionUID = 1726106361091289151L;

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("itemList")
    private List<ServiceUrl> f8226a;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public static class ServiceUrl implements Parcelable, Serializable {
        public static final Parcelable.Creator<ServiceUrl> CREATOR = new a();
        private static final long serialVersionUID = -117340691941684418L;

        /* renamed from: a, reason: collision with root package name */
        @SerializedName(FaqConstants.SDK_URL_HA)
        private String f8227a;

        @SerializedName(FaqConstants.SDK_URL_MD)
        private String b;

        @SerializedName("sdkGwRouteAddressV2")
        private String c;

        @SerializedName(FaqConstants.SDK_URL_YUN)
        private String d;

        @SerializedName("countryCode")
        private String e;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.f8227a);
            parcel.writeString(this.b);
            parcel.writeString(this.c);
            parcel.writeString(this.d);
            parcel.writeString(this.e);
        }

        public String e() {
            return this.d;
        }

        public String d() {
            return this.c;
        }

        public String c() {
            return this.b;
        }

        class a implements Parcelable.Creator<ServiceUrl> {
            @Override // android.os.Parcelable.Creator
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public ServiceUrl[] newArray(int i) {
                return new ServiceUrl[i];
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: ccR_, reason: merged with bridge method [inline-methods] */
            public ServiceUrl createFromParcel(Parcel parcel) {
                return new ServiceUrl(parcel);
            }

            a() {
            }
        }

        public String b() {
            return this.f8227a;
        }

        public String a() {
            return this.e;
        }

        protected ServiceUrl(Parcel parcel) {
            this.f8227a = parcel.readString();
            this.b = parcel.readString();
            this.c = parcel.readString();
            this.d = parcel.readString();
            this.e = parcel.readString();
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(this.f8226a);
    }

    class a implements Parcelable.Creator<FaqSdkServiceUrlResponse> {
        @Override // android.os.Parcelable.Creator
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public FaqSdkServiceUrlResponse[] newArray(int i) {
            return new FaqSdkServiceUrlResponse[i];
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: ccS_, reason: merged with bridge method [inline-methods] */
        public FaqSdkServiceUrlResponse createFromParcel(Parcel parcel) {
            return new FaqSdkServiceUrlResponse(parcel);
        }

        a() {
        }
    }

    public List<ServiceUrl> a() {
        return this.f8226a;
    }

    protected FaqSdkServiceUrlResponse(Parcel parcel) {
        this.f8226a = parcel.createTypedArrayList(ServiceUrl.CREATOR);
    }
}
