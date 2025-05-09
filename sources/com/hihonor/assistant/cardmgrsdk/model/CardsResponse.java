package com.hihonor.assistant.cardmgrsdk.model;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

/* loaded from: classes8.dex */
public class CardsResponse implements Parcelable {
    public static final Parcelable.Creator<CardsResponse> CREATOR = new Parcelable.Creator<CardsResponse>() { // from class: com.hihonor.assistant.cardmgrsdk.model.CardsResponse.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CardsResponse[] newArray(int i) {
            return new CardsResponse[i];
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CardsResponse createFromParcel(Parcel parcel) {
            return new CardsResponse(parcel);
        }
    };
    public List<CardInfo> cardInfoList;
    public boolean isCardListChange;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte(this.isCardListChange ? (byte) 1 : (byte) 0);
        parcel.writeTypedList(this.cardInfoList);
    }

    public void setCardListChange(boolean z) {
        this.isCardListChange = z;
    }

    public void setCardInfoList(List<CardInfo> list) {
        this.cardInfoList = list;
    }

    public boolean isCardListChange() {
        return this.isCardListChange;
    }

    public List<CardInfo> getCardInfoList() {
        return this.cardInfoList;
    }

    public CardsResponse(boolean z, List<CardInfo> list) {
        this.isCardListChange = z;
        this.cardInfoList = list;
    }

    public CardsResponse(boolean z) {
        this.isCardListChange = z;
    }

    public CardsResponse(Parcel parcel) {
        this.isCardListChange = parcel.readByte() != 0;
        this.cardInfoList = parcel.createTypedArrayList(CardInfo.CREATOR);
    }
}
