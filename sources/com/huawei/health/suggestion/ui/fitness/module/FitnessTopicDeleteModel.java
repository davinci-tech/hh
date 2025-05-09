package com.huawei.health.suggestion.ui.fitness.module;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

/* loaded from: classes7.dex */
public class FitnessTopicDeleteModel implements Parcelable {
    public static final Parcelable.Creator<FitnessTopicDeleteModel> CREATOR = new Parcelable.Creator<FitnessTopicDeleteModel>() { // from class: com.huawei.health.suggestion.ui.fitness.module.FitnessTopicDeleteModel.3
        @Override // android.os.Parcelable.Creator
        /* renamed from: aDi_, reason: merged with bridge method [inline-methods] */
        public FitnessTopicDeleteModel createFromParcel(Parcel parcel) {
            return new FitnessTopicDeleteModel(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public FitnessTopicDeleteModel[] newArray(int i) {
            return new FitnessTopicDeleteModel[i];
        }
    };
    private boolean mIsDeleteMode;
    private boolean mIsPartRefresh;
    private int mPosition;
    private List<Integer> mSelectList;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public FitnessTopicDeleteModel() {
    }

    protected FitnessTopicDeleteModel(Parcel parcel) {
        this.mIsDeleteMode = parcel.readByte() != 0;
        this.mPosition = parcel.readInt();
        this.mIsPartRefresh = parcel.readByte() != 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte(this.mIsDeleteMode ? (byte) 1 : (byte) 0);
        parcel.writeInt(this.mPosition);
        parcel.writeByte(this.mIsPartRefresh ? (byte) 1 : (byte) 0);
    }

    public boolean issDeleteMode() {
        return this.mIsDeleteMode;
    }

    public void saveIssDeleteMode(boolean z) {
        this.mIsDeleteMode = z;
    }

    public boolean isPartRefresh() {
        return this.mIsPartRefresh;
    }

    public void saveIsPartRefresh(boolean z) {
        this.mIsPartRefresh = z;
    }

    public int acquirePosition() {
        return this.mPosition;
    }

    public void savePosition(int i) {
        this.mPosition = i;
    }

    public List<Integer> acquireSelects() {
        return this.mSelectList;
    }

    public void saveSelects(List<Integer> list) {
        this.mSelectList = list;
    }
}
