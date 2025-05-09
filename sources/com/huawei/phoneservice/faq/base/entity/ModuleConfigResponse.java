package com.huawei.phoneservice.faq.base.entity;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;

/* loaded from: classes5.dex */
public class ModuleConfigResponse implements Serializable, Parcelable {
    public static final Parcelable.Creator<ModuleConfigResponse> CREATOR = new b();
    private static final long serialVersionUID = 5523493960862839428L;

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("moduleList")
    private List<ModuleListBean> f8228a;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public static class ModuleListBean implements Serializable, Parcelable {
        public static final Parcelable.Creator<ModuleListBean> CREATOR = new c();
        private static final long serialVersionUID = -6366144895402746183L;

        /* renamed from: a, reason: collision with root package name */
        @SerializedName("moduleCode")
        private String f8229a;

        @SerializedName("moduleName")
        private String b;

        @SerializedName("moduleLinkAddress")
        private String c;

        @SerializedName("openLinkType")
        private String d;

        @SerializedName("isLink")
        private String e;

        @SerializedName("subModuleList")
        private List<SubModuleListBean> f;

        @SerializedName("appName")
        private String g;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.f8229a);
            parcel.writeString(this.b);
            parcel.writeString(this.c);
            parcel.writeString(this.d);
            parcel.writeString(this.e);
            parcel.writeTypedList(this.f);
            parcel.writeString(this.g);
        }

        public int hashCode() {
            return super.hashCode();
        }

        public String getOpenType() {
            return this.d;
        }

        public String getModuleCode() {
            return this.f8229a;
        }

        public String getLinkAddress() {
            return this.c;
        }

        public boolean equals(Object obj) {
            if (obj != null && getClass() == obj.getClass()) {
                return this.f8229a.equals(((ModuleListBean) obj).getModuleCode());
            }
            return false;
        }

        public List<SubModuleListBean> b() {
            return this.f;
        }

        class c implements Parcelable.Creator<ModuleListBean> {
            @Override // android.os.Parcelable.Creator
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public ModuleListBean[] newArray(int i) {
                return new ModuleListBean[i];
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: ccT_, reason: merged with bridge method [inline-methods] */
            public ModuleListBean createFromParcel(Parcel parcel) {
                return new ModuleListBean(parcel);
            }

            c() {
            }
        }

        public String a() {
            return this.g;
        }

        protected ModuleListBean(Parcel parcel) {
            this.f8229a = parcel.readString();
            this.b = parcel.readString();
            this.c = parcel.readString();
            this.d = parcel.readString();
            this.e = parcel.readString();
            this.f = parcel.createTypedArrayList(SubModuleListBean.CREATOR);
            this.g = parcel.readString();
            parcel.readParcelable(getClass().getClassLoader());
        }

        public ModuleListBean() {
        }
    }

    public static class SubModuleListBean implements Serializable, Parcelable {
        public static final Parcelable.Creator<SubModuleListBean> CREATOR = new a();
        private static final long serialVersionUID = 773325441128424814L;

        /* renamed from: a, reason: collision with root package name */
        @SerializedName("subModuleCode")
        private String f8230a;

        @SerializedName("subModuleName")
        private String b;

        @SerializedName("subModuleLinkAddress")
        private String c;

        @SerializedName("openLinkType")
        private String d;

        @SerializedName("isLink")
        private String e;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.f8230a);
            parcel.writeString(this.b);
            parcel.writeString(this.c);
            parcel.writeString(this.d);
            parcel.writeString(this.e);
        }

        class a implements Parcelable.Creator<SubModuleListBean> {
            @Override // android.os.Parcelable.Creator
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public SubModuleListBean[] newArray(int i) {
                return new SubModuleListBean[i];
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: ccU_, reason: merged with bridge method [inline-methods] */
            public SubModuleListBean createFromParcel(Parcel parcel) {
                return new SubModuleListBean(parcel);
            }

            a() {
            }
        }

        public String a() {
            return this.f8230a;
        }

        protected SubModuleListBean(Parcel parcel) {
            this.f8230a = parcel.readString();
            this.b = parcel.readString();
            this.c = parcel.readString();
            this.d = parcel.readString();
            this.e = parcel.readString();
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(this.f8228a);
    }

    public List<ModuleListBean> getModuleList() {
        return this.f8228a;
    }

    class b implements Parcelable.Creator<ModuleConfigResponse> {
        @Override // android.os.Parcelable.Creator
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public ModuleConfigResponse[] newArray(int i) {
            return new ModuleConfigResponse[i];
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: ccV_, reason: merged with bridge method [inline-methods] */
        public ModuleConfigResponse createFromParcel(Parcel parcel) {
            return new ModuleConfigResponse(parcel);
        }

        b() {
        }
    }

    protected ModuleConfigResponse(Parcel parcel) {
        this.f8228a = parcel.createTypedArrayList(ModuleListBean.CREATOR);
    }

    public ModuleConfigResponse() {
    }
}
