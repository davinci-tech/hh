package com.huawei.operation.h5pro.jsmodules.audiocontrol;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import com.huawei.watchface.videoedit.gles.Constant;

/* loaded from: classes5.dex */
class AudioControlParamObj implements Parcelable {
    public static final Parcelable.Creator<AudioControlParamObj> CREATOR = new Parcelable.Creator<AudioControlParamObj>() { // from class: com.huawei.operation.h5pro.jsmodules.audiocontrol.AudioControlParamObj.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AudioControlParamObj createFromParcel(Parcel parcel) {
            return new AudioControlParamObj(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AudioControlParamObj[] newArray(int i) {
            return new AudioControlParamObj[i];
        }
    };

    @SerializedName(Constant.TYPE_PHOTO)
    private String image;

    @SerializedName("name")
    private String name;

    @SerializedName("source")
    private String source;

    @SerializedName("url")
    private String url;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    protected AudioControlParamObj(Parcel parcel) {
        this.name = parcel.readString();
        this.image = parcel.readString();
        this.url = parcel.readString();
        this.source = parcel.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.name);
        parcel.writeString(this.image);
        parcel.writeString(this.url);
        parcel.writeString(this.source);
    }

    public String getSource() {
        return this.source;
    }

    public String getUrl() {
        return this.url;
    }

    public String getImage() {
        return this.image;
    }

    public String getName() {
        return this.name;
    }

    public void setSource(String str) {
        this.source = str;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public void setImage(String str) {
        this.image = str;
    }

    public void setName(String str) {
        this.name = str;
    }
}
