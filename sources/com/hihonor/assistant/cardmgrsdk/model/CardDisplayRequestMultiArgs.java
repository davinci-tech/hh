package com.hihonor.assistant.cardmgrsdk.model;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

/* loaded from: classes8.dex */
public class CardDisplayRequestMultiArgs extends CardDisplayRequestArg {
    public static final Parcelable.Creator<CardDisplayRequestMultiArgs> CREATOR = new Parcelable.Creator<CardDisplayRequestMultiArgs>() { // from class: com.hihonor.assistant.cardmgrsdk.model.CardDisplayRequestMultiArgs.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CardDisplayRequestMultiArgs[] newArray(int i) {
            return new CardDisplayRequestMultiArgs[i];
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CardDisplayRequestMultiArgs createFromParcel(Parcel parcel) {
            return new CardDisplayRequestMultiArgs(parcel);
        }
    };
    public ArrayList<ImageDescriptor> imageDescriptor;
    public ArrayList<RemoteViewsDescriptor> remoteViewsParcelArrayList;

    @Override // com.hihonor.assistant.cardmgrsdk.model.CardDisplayRequestArg, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.hihonor.assistant.cardmgrsdk.model.CardDisplayRequestArg, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeTypedList(this.remoteViewsParcelArrayList);
        parcel.writeTypedList(this.imageDescriptor);
    }

    public void setRemoteViewsList(ArrayList<RemoteViewsDescriptor> arrayList) {
        this.remoteViewsParcelArrayList = arrayList;
    }

    public void setImageDescriptor(ArrayList<ImageDescriptor> arrayList) {
        this.imageDescriptor = arrayList;
    }

    public ArrayList<RemoteViewsDescriptor> getRemoteViewsList() {
        return this.remoteViewsParcelArrayList;
    }

    public ArrayList<ImageDescriptor> getImageDescriptors() {
        return this.imageDescriptor;
    }

    public CardDisplayRequestMultiArgs(String str, String str2, int i) {
        super(str, str2, i);
    }

    public CardDisplayRequestMultiArgs(CardDisplayRequestMultiArgs cardDisplayRequestMultiArgs) {
        super(cardDisplayRequestMultiArgs.getBusiness(), cardDisplayRequestMultiArgs.getBusinessId(), cardDisplayRequestMultiArgs.getOperation());
        setBusinessParams(cardDisplayRequestMultiArgs.getBusinessParams());
        setEndTime(cardDisplayRequestMultiArgs.getEndTime());
        setWidgetPackage(cardDisplayRequestMultiArgs.getWidgetPackage());
        setType(cardDisplayRequestMultiArgs.getType());
        setDetailType(cardDisplayRequestMultiArgs.getDetailType());
        setCardRemoteViews(cardDisplayRequestMultiArgs.getCardRemoteViews());
        setViewData(cardDisplayRequestMultiArgs.getViewData());
        setVersionCode(cardDisplayRequestMultiArgs.getVersionCode());
        setClickPendingIntents(cardDisplayRequestMultiArgs.getClickPendingIntents());
        setImageEntities(cardDisplayRequestMultiArgs.getImageEntities());
        setExtras(cardDisplayRequestMultiArgs.getExtras());
        ArrayList<RemoteViewsDescriptor> arrayList = new ArrayList<>();
        this.remoteViewsParcelArrayList = arrayList;
        ArrayList<RemoteViewsDescriptor> arrayList2 = cardDisplayRequestMultiArgs.remoteViewsParcelArrayList;
        if (arrayList2 != null) {
            arrayList.addAll(arrayList2);
        }
        ArrayList<ImageDescriptor> arrayList3 = new ArrayList<>();
        this.imageDescriptor = arrayList3;
        ArrayList<ImageDescriptor> arrayList4 = cardDisplayRequestMultiArgs.imageDescriptor;
        if (arrayList4 != null) {
            arrayList3.addAll(arrayList4);
        }
    }

    public CardDisplayRequestMultiArgs(CardDispReqBuilder cardDispReqBuilder) {
        super(cardDispReqBuilder);
        this.remoteViewsParcelArrayList = cardDispReqBuilder.getCardRemoteViewsList();
        this.imageDescriptor = cardDispReqBuilder.getImageDescriptorList();
    }

    public CardDisplayRequestMultiArgs(Parcel parcel) {
        super(parcel);
        this.remoteViewsParcelArrayList = parcel.createTypedArrayList(RemoteViewsDescriptor.CREATOR);
        this.imageDescriptor = parcel.createTypedArrayList(ImageDescriptor.CREATOR);
    }
}
