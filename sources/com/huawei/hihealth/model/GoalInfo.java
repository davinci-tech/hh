package com.huawei.hihealth.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.hihealth.model.SampleEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

/* loaded from: classes.dex */
public class GoalInfo extends EventTypeInfo {
    public static final Parcelable.Creator<GoalInfo> CREATOR = new Parcelable.Creator<GoalInfo>() { // from class: com.huawei.hihealth.model.GoalInfo.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: bwr_, reason: merged with bridge method [inline-methods] */
        public GoalInfo createFromParcel(Parcel parcel) {
            return new GoalInfo(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public GoalInfo[] newArray(int i) {
            return new GoalInfo[i];
        }
    };
    private List<Goal> goals;
    private Recurrence recurrence;

    public GoalInfo(List<Goal> list, int i, Recurrence recurrence, String str) {
        super(SampleEvent.Type.SCENARIO_GOAL_EVENT, "ACHIEVE", str, i);
        new ArrayList();
        this.goals = list;
        this.recurrence = recurrence;
    }

    public GoalInfo(Parcel parcel) {
        super(parcel);
        this.goals = new ArrayList();
        this.recurrence = (Recurrence) parcel.readParcelable(Recurrence.class.getClassLoader());
        parcel.readTypedList(this.goals, Goal.CREATOR);
    }

    @Override // com.huawei.hihealth.model.EventTypeInfo, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeParcelable(this.recurrence, i);
        parcel.writeTypedList(this.goals);
    }

    @Override // com.huawei.hihealth.model.EventTypeInfo
    public int hashCode() {
        return Objects.hash(this.recurrence, this.goals);
    }

    @Override // com.huawei.hihealth.model.EventTypeInfo
    public boolean equals(Object obj) {
        if (!super.equals(obj) || !(obj instanceof GoalInfo)) {
            return false;
        }
        Recurrence recurrence = this.recurrence;
        if (recurrence != null ? !recurrence.equals(((GoalInfo) obj).getRecurrence()) : ((GoalInfo) obj).getRecurrence() != null) {
            return false;
        }
        if (this.goals == null) {
            return ((GoalInfo) obj).getGoals() == null;
        }
        GoalInfo goalInfo = (GoalInfo) obj;
        return goalInfo.getGoals() != null && this.goals.containsAll(goalInfo.getGoals()) && goalInfo.getGoals().containsAll(this.goals);
    }

    public List<Goal> getGoals() {
        return this.goals;
    }

    public void setGoals(List<Goal> list) {
        this.goals = list;
    }

    public Recurrence getRecurrence() {
        return this.recurrence;
    }

    public void setRecurrence(Recurrence recurrence) {
        this.recurrence = recurrence;
    }

    public boolean isAllAchieved() {
        return this.goals.stream().allMatch(new Predicate() { // from class: com.huawei.hihealth.model.GoalInfo$$ExternalSyntheticLambda0
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                boolean isAchievedFlag;
                isAchievedFlag = ((Goal) obj).isAchievedFlag();
                return isAchievedFlag;
            }
        });
    }
}
