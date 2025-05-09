package com.huawei.health.threeCircle.remind.model;

import android.os.Parcel;
import android.os.Parcelable;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes4.dex */
public class DeviceEventData implements Parcelable {
    public static final Parcelable.Creator<DeviceEventData> CREATOR = new Parcelable.Creator<DeviceEventData>() { // from class: com.huawei.health.threeCircle.remind.model.DeviceEventData.2
        @Override // android.os.Parcelable.Creator
        /* renamed from: aNm_, reason: merged with bridge method [inline-methods] */
        public DeviceEventData createFromParcel(Parcel parcel) {
            return new DeviceEventData(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public DeviceEventData[] newArray(int i) {
            return new DeviceEventData[i];
        }
    };
    private static final int DEFAULT_VALUE = 255;
    private int mAchieveNum;
    private int mAvgActiveCalorie;
    private int mAvgActiveHour;
    private int mAvgStrengthTime;
    private int mCompletedDayNum;
    private int mEventId;
    private int mGoalType;
    private int mNoCompletedDayNum;
    private int mSmartActiveCalorieGoal;
    private long mTimeStamp;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    private DeviceEventData(Parcel parcel) {
        this.mTimeStamp = parcel.readLong();
        this.mEventId = parcel.readInt();
        this.mGoalType = parcel.readInt();
        this.mAchieveNum = parcel.readInt();
        this.mAvgActiveCalorie = parcel.readInt();
        this.mAvgStrengthTime = parcel.readInt();
        this.mAvgActiveHour = parcel.readInt();
        this.mCompletedDayNum = parcel.readInt();
        this.mNoCompletedDayNum = parcel.readInt();
        this.mSmartActiveCalorieGoal = parcel.readInt();
    }

    private DeviceEventData(c cVar) {
        this.mTimeStamp = cVar.h;
        this.mEventId = cVar.j;
        this.mGoalType = cVar.i;
        this.mAchieveNum = cVar.c;
        this.mAvgActiveCalorie = cVar.e;
        this.mAvgStrengthTime = cVar.b;
        this.mAvgActiveHour = cVar.f3445a;
        this.mCompletedDayNum = cVar.d;
        this.mNoCompletedDayNum = cVar.g;
        this.mSmartActiveCalorieGoal = cVar.f;
    }

    public static final class c {
        private long h;
        private int i;
        private int j;
        private int c = 255;
        private int e = Integer.MAX_VALUE;
        private int b = Integer.MAX_VALUE;

        /* renamed from: a, reason: collision with root package name */
        private int f3445a = 255;
        private int d = 255;
        private int g = 255;
        private int f = Integer.MAX_VALUE;

        public DeviceEventData d() {
            return new DeviceEventData(this);
        }

        public c c(long j) {
            this.h = j;
            return this;
        }

        public c j(int i) {
            this.j = i;
            return this;
        }

        public c g(int i) {
            this.i = i;
            return this;
        }

        public c c(int i) {
            this.c = i;
            return this;
        }

        public c e(int i) {
            this.e = i;
            return this;
        }

        public c b(int i) {
            this.b = i;
            return this;
        }

        public c d(int i) {
            this.f3445a = i;
            return this;
        }

        public c a(int i) {
            this.d = i;
            return this;
        }

        public c i(int i) {
            this.g = i;
            return this;
        }

        public c f(int i) {
            this.f = i;
            return this;
        }
    }

    public long getTimeStamp() {
        return this.mTimeStamp;
    }

    public int getEventId() {
        return this.mEventId;
    }

    public int getGoalType() {
        return this.mGoalType;
    }

    public int getAchieveNum() {
        return this.mAchieveNum;
    }

    public int getAvgActiveCalorie() {
        return this.mAvgActiveCalorie;
    }

    public int getAvgStrengthTime() {
        return this.mAvgStrengthTime;
    }

    public int getAvgActiveHour() {
        return this.mAvgActiveHour;
    }

    public int getSmartActiveCalorieGoal() {
        return this.mSmartActiveCalorieGoal;
    }

    public int getCompletedDayNum() {
        return this.mCompletedDayNum;
    }

    public int getNoCompletedDayNum() {
        return this.mNoCompletedDayNum;
    }

    public byte[] getBytes() {
        ByteBuffer allocate = ByteBuffer.allocate(24);
        allocate.order(ByteOrder.LITTLE_ENDIAN);
        allocate.putInt((int) this.mTimeStamp);
        allocate.put((byte) this.mEventId);
        allocate.put((byte) this.mGoalType);
        allocate.put((byte) this.mAchieveNum);
        allocate.put((byte) 0);
        allocate.putInt(this.mAvgActiveCalorie);
        allocate.putInt(this.mAvgStrengthTime);
        allocate.put((byte) this.mAvgActiveHour);
        allocate.put((byte) this.mCompletedDayNum);
        allocate.put((byte) this.mNoCompletedDayNum);
        allocate.put((byte) 0);
        allocate.putInt(this.mSmartActiveCalorieGoal);
        allocate.flip();
        return allocate.array();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.mTimeStamp);
        parcel.writeInt(this.mEventId);
        parcel.writeInt(this.mGoalType);
        parcel.writeInt(this.mAchieveNum);
        parcel.writeInt(this.mAvgActiveCalorie);
        parcel.writeInt(this.mAvgStrengthTime);
        parcel.writeInt(this.mAvgActiveHour);
        parcel.writeInt(this.mCompletedDayNum);
        parcel.writeInt(this.mNoCompletedDayNum);
        parcel.writeInt(this.mSmartActiveCalorieGoal);
    }
}
