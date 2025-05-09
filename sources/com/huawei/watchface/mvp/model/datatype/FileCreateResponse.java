package com.huawei.watchface.mvp.model.datatype;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import com.huawei.health.tradeservice.pay.PayActivity;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes7.dex */
public class FileCreateResponse implements Parcelable {
    public static final Parcelable.Creator<FileCreateResponse> CREATOR = new Parcelable.Creator<FileCreateResponse>() { // from class: com.huawei.watchface.mvp.model.datatype.FileCreateResponse.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public FileCreateResponse createFromParcel(Parcel parcel) {
            return new FileCreateResponse(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public FileCreateResponse[] newArray(int i) {
            return new FileCreateResponse[i];
        }
    };
    private String createdNums;
    private List<MaterialFileUploadInfo> materialFileUploadInfo;
    private String maxNums;

    @SerializedName("resultcode")
    private int resultCode;

    @SerializedName("resultinfo")
    private String resultInfo;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getResultInfo() {
        return this.resultInfo;
    }

    public void setResultInfo(String str) {
        this.resultInfo = str;
    }

    public int getResultCode() {
        return this.resultCode;
    }

    public void setResultCode(int i) {
        this.resultCode = i;
    }

    public String getCreatedNums() {
        return this.createdNums;
    }

    public void setCreatedNums(String str) {
        this.createdNums = str;
    }

    public String getMaxNums() {
        return this.maxNums;
    }

    public void setMaxNums(String str) {
        this.maxNums = str;
    }

    public List<MaterialFileUploadInfo> getMaterialFileUploadInfo() {
        return this.materialFileUploadInfo;
    }

    public void setMaterialFileUploadInfo(List<MaterialFileUploadInfo> list) {
        this.materialFileUploadInfo = list;
    }

    protected FileCreateResponse(Parcel parcel) {
        this.resultInfo = parcel.readString();
        this.resultCode = parcel.readInt();
        this.createdNums = parcel.readString();
        this.maxNums = parcel.readString();
        this.materialFileUploadInfo = createTypedArrayList(MaterialFileUploadInfo.CREATOR, parcel);
    }

    private final <T> ArrayList<T> createTypedArrayList(Parcelable.Creator<T> creator, Parcel parcel) {
        int readInt = parcel.readInt();
        if (readInt < 0) {
            return null;
        }
        if (readInt >= 1024) {
            readInt = 1024;
        }
        PayActivity.AnonymousClass6 anonymousClass6 = (ArrayList<T>) new ArrayList(readInt);
        while (readInt > 0) {
            anonymousClass6.add(parcel.readTypedObject(creator));
            readInt--;
        }
        return anonymousClass6;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.resultInfo);
        parcel.writeInt(this.resultCode);
        parcel.writeString(this.createdNums);
        parcel.writeString(this.maxNums);
        List<MaterialFileUploadInfo> list = this.materialFileUploadInfo;
        if (list != null) {
            parcel.writeTypedList(list.size() <= 1024 ? this.materialFileUploadInfo : null);
        } else {
            parcel.writeTypedList(null);
        }
    }
}
