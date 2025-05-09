package com.huawei.phoneservice.feedbackcommon.entity;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import com.huawei.operation.jsoperation.JsUtil;
import java.io.Serializable;
import java.util.List;

/* loaded from: classes5.dex */
public class FeedBackResponse implements Serializable, Parcelable {
    public static final Parcelable.Creator<FeedBackResponse> CREATOR = new a();
    private static final long serialVersionUID = -5533984095408646461L;

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("data")
    private List<ProblemEnity> f8308a;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
    }

    public static class ProblemEnity implements Serializable, Parcelable {
        public static final Parcelable.Creator<ProblemEnity> CREATOR = new c();
        private static final long serialVersionUID = 6410105318113796076L;

        /* renamed from: a, reason: collision with root package name */
        @SerializedName("problemId")
        private String f8309a;

        @SerializedName("problemDesc")
        private String b;

        @SerializedName("answer")
        private String c;

        @SerializedName("attaches")
        private List<FeedMedia> d;

        @SerializedName("pic")
        private FeedMedia e;

        @SerializedName("createTime")
        private String f;

        @SerializedName(JsUtil.SCORE)
        private String g;

        @SerializedName("read")
        private boolean h;

        @SerializedName("srcno")
        private String i;

        @SerializedName("answerTime")
        private String j;

        @SerializedName("updateTime")
        private String k;

        @SerializedName("problemCatalogCode")
        private String l;

        @SerializedName("contact")
        private String m;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.f8309a);
            parcel.writeString(this.b);
            parcel.writeString(this.c);
            parcel.writeTypedList(this.d);
            parcel.writeParcelable(this.e, i);
            parcel.writeString(this.f);
            parcel.writeString(this.g);
            parcel.writeByte(this.h ? (byte) 1 : (byte) 0);
            parcel.writeString(this.i);
            parcel.writeString(this.j);
            parcel.writeString(this.k);
            parcel.writeString(this.l);
            parcel.writeString(this.m);
        }

        public void setScore(String str) {
            this.g = str;
        }

        public void setIsRead(boolean z) {
            this.h = z;
        }

        public String getUpdateTime() {
            return this.k;
        }

        public String getSrno() {
            return this.i;
        }

        public String getScore() {
            return this.g;
        }

        public String getProblemId() {
            return this.f8309a;
        }

        public String getProblemDesc() {
            return this.b;
        }

        public String getProblemCatalogCode() {
            return this.l;
        }

        public FeedMedia getPicURL() {
            return this.e;
        }

        public List<FeedMedia> getMediaItemList() {
            return this.d;
        }

        public boolean getIsRead() {
            return this.h;
        }

        public String getCreateTime() {
            return this.f;
        }

        public String getContact() {
            return this.m;
        }

        public String getAnswerTime() {
            return this.j;
        }

        class c implements Parcelable.Creator<ProblemEnity> {
            @Override // android.os.Parcelable.Creator
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public ProblemEnity[] newArray(int i) {
                return new ProblemEnity[i];
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: cfh_, reason: merged with bridge method [inline-methods] */
            public ProblemEnity createFromParcel(Parcel parcel) {
                return new ProblemEnity(parcel);
            }

            c() {
            }
        }

        public String getAnswer() {
            return this.c;
        }

        protected ProblemEnity(Parcel parcel) {
            this.f8309a = parcel.readString();
            this.b = parcel.readString();
            this.c = parcel.readString();
            this.d = parcel.createTypedArrayList(FeedMedia.CREATOR);
            this.e = (FeedMedia) parcel.readParcelable(FeedMedia.class.getClassLoader());
            this.f = parcel.readString();
            this.g = parcel.readString();
            this.h = parcel.readByte() != 0;
            this.i = parcel.readString();
            this.j = parcel.readString();
            this.k = parcel.readString();
            this.l = parcel.readString();
            this.m = parcel.readString();
        }
    }

    class a implements Parcelable.Creator<FeedBackResponse> {
        @Override // android.os.Parcelable.Creator
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public FeedBackResponse[] newArray(int i) {
            return new FeedBackResponse[i];
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: cfi_, reason: merged with bridge method [inline-methods] */
        public FeedBackResponse createFromParcel(Parcel parcel) {
            return new FeedBackResponse(parcel);
        }

        a() {
        }
    }

    public List<ProblemEnity> getDataList() {
        return this.f8308a;
    }

    protected FeedBackResponse(Parcel parcel) {
        this.f8308a = parcel.createTypedArrayList(ProblemEnity.CREATOR);
    }
}
