package com.huawei.hihealth.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

/* loaded from: classes.dex */
public class Goal implements Parcelable {
    public static final Parcelable.Creator<Goal> CREATOR = new Parcelable.Creator<Goal>() { // from class: com.huawei.hihealth.model.Goal.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: bwq_, reason: merged with bridge method [inline-methods] */
        public Goal createFromParcel(Parcel parcel) {
            try {
                return (Goal) Class.forName(parcel.readString()).getConstructor(Parcel.class).newInstance(parcel);
            } catch (ClassNotFoundException unused) {
                Log.w(Goal.TAG, "class not found");
                return new Goal(parcel);
            } catch (IllegalAccessException | InstantiationException | InvocationTargetException unused2) {
                Log.w(Goal.TAG, "other exception");
                return new Goal(parcel);
            } catch (NoSuchMethodException unused3) {
                Log.w(Goal.TAG, "no such method");
                return new Goal(parcel);
            }
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public Goal[] newArray(int i) {
            return new Goal[i];
        }
    };
    private static final String TAG = "Goal";
    private volatile boolean achievedFlag;
    private String achievedResult;
    private int dataType;
    private int goalType;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    protected Goal(Parcel parcel) {
        this.goalType = parcel.readInt();
        this.achievedFlag = ((Boolean) parcel.readValue(Boolean.class.getClassLoader())).booleanValue();
        this.dataType = parcel.readInt();
        this.achievedResult = parcel.readString();
    }

    public Goal() {
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(getClass().getName());
        parcel.writeInt(this.goalType);
        parcel.writeValue(Boolean.valueOf(this.achievedFlag));
        parcel.writeInt(this.dataType);
        parcel.writeString(this.achievedResult);
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.goalType), Integer.valueOf(this.dataType));
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Goal)) {
            return false;
        }
        Goal goal = (Goal) obj;
        return Objects.equals(Integer.valueOf(this.goalType), Integer.valueOf(goal.getGoalType())) && Objects.equals(Integer.valueOf(this.dataType), Integer.valueOf(goal.getDataType()));
    }

    public int getDataType() {
        return this.dataType;
    }

    public void setDataType(int i) {
        this.dataType = i;
    }

    public String getAchievedResult() {
        return this.achievedResult;
    }

    public void setAchievedResult(String str) {
        this.achievedResult = str;
    }

    public boolean isAchievedFlag() {
        return this.achievedFlag;
    }

    public void setAchievedFlag(boolean z) {
        this.achievedFlag = z;
    }

    public int getGoalType() {
        return this.goalType;
    }

    public void setGoalType(int i) {
        this.goalType = i;
    }
}
