package com.huawei.hihealth.model;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Objects;

/* loaded from: classes.dex */
public class DurationGoal extends Goal {
    public static final Parcelable.Creator<DurationGoal> CREATOR = new Parcelable.Creator<DurationGoal>() { // from class: com.huawei.hihealth.model.DurationGoal.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: bwo_, reason: merged with bridge method [inline-methods] */
        public DurationGoal createFromParcel(Parcel parcel) {
            return new DurationGoal(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public DurationGoal[] newArray(int i) {
            return new DurationGoal[i];
        }
    };
    private int duration;

    public DurationGoal(Parcel parcel) {
        super(parcel);
        this.duration = parcel.readInt();
    }

    public DurationGoal(int i, int i2) {
        super.setDataType(i);
        super.setGoalType(2);
        this.duration = i2;
    }

    @Override // com.huawei.hihealth.model.Goal, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeInt(this.duration);
    }

    @Override // com.huawei.hihealth.model.Goal
    public int hashCode() {
        return super.hashCode() + Objects.hash(Integer.valueOf(this.duration));
    }

    @Override // com.huawei.hihealth.model.Goal
    public boolean equals(Object obj) {
        return super.equals(obj) && (obj instanceof DurationGoal) && this.duration == ((DurationGoal) obj).getDuration();
    }

    public int getDuration() {
        return this.duration;
    }

    public void setDuration(int i) {
        this.duration = i;
    }
}
