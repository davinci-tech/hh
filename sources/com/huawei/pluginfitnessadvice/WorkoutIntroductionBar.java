package com.huawei.pluginfitnessadvice;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import java.util.List;

/* loaded from: classes6.dex */
public class WorkoutIntroductionBar implements Parcelable {
    public static final Parcelable.Creator<WorkoutIntroductionBar> CREATOR = new Parcelable.Creator<WorkoutIntroductionBar>() { // from class: com.huawei.pluginfitnessadvice.WorkoutIntroductionBar.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: cmj_, reason: merged with bridge method [inline-methods] */
        public WorkoutIntroductionBar createFromParcel(Parcel parcel) {
            return new WorkoutIntroductionBar(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public WorkoutIntroductionBar[] newArray(int i) {
            return new WorkoutIntroductionBar[i];
        }
    };

    @SerializedName("layouts")
    private List<ShowLayout> mLayoutList;

    @SerializedName("name")
    private String mName;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    protected WorkoutIntroductionBar(Parcel parcel) {
        this.mName = parcel.readString();
        this.mLayoutList = parcel.createTypedArrayList(ShowLayout.CREATOR);
    }

    private WorkoutIntroductionBar(e eVar) {
        this.mName = eVar.d;
        this.mLayoutList = eVar.e;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mName);
        parcel.writeTypedList(this.mLayoutList);
    }

    /* loaded from: classes9.dex */
    public static final class e {
        private String d;
        private List<ShowLayout> e;
    }

    public String getName() {
        return this.mName;
    }

    public List<ShowLayout> getLayoutList() {
        return this.mLayoutList;
    }

    public String toString() {
        return "WorkoutIntroductionBar{mName='" + this.mName + "', mLayoutList=" + this.mLayoutList + '}';
    }
}
