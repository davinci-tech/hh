package com.huawei.pluginfitnessadvice;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import java.util.List;

/* loaded from: classes6.dex */
public class ChoreographedMultiActions extends BaseChoreographyAction {
    public static final Parcelable.Creator<ChoreographedMultiActions> CREATOR = new Parcelable.Creator<ChoreographedMultiActions>() { // from class: com.huawei.pluginfitnessadvice.ChoreographedMultiActions.3
        @Override // android.os.Parcelable.Creator
        /* renamed from: clG_, reason: merged with bridge method [inline-methods] */
        public ChoreographedMultiActions createFromParcel(Parcel parcel) {
            return new ChoreographedMultiActions(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public ChoreographedMultiActions[] newArray(int i) {
            return new ChoreographedMultiActions[i];
        }
    };
    private static final long serialVersionUID = -5057402986922791479L;

    @SerializedName("actionList")
    private List<ChoreographedSingleAction> mActionList;

    @SerializedName("repeatTimes")
    private int mRepeatTimes;

    public ChoreographedMultiActions() {
    }

    public ChoreographedMultiActions(Parcel parcel) {
        if (parcel != null) {
            this.mRepeatTimes = parcel.readInt();
            this.mActionList = parcel.createTypedArrayList(ChoreographedSingleAction.CREATOR);
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel != null) {
            parcel.writeInt(this.mRepeatTimes);
            parcel.writeList(this.mActionList);
        }
    }

    public int getRepeatTimes() {
        return this.mRepeatTimes;
    }

    public void setRepeatTimes(int i) {
        this.mRepeatTimes = i;
    }

    public List<ChoreographedSingleAction> getActionList() {
        return this.mActionList;
    }

    public void setActionList(List<ChoreographedSingleAction> list) {
        this.mActionList = list;
    }
}
