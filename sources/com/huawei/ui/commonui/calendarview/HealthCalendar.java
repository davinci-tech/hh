package com.huawei.ui.commonui.calendarview;

import android.os.Parcel;
import android.os.Parcelable;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/* loaded from: classes6.dex */
public final class HealthCalendar implements Serializable, Parcelable, Comparable<HealthCalendar> {
    public static final Parcelable.Creator<HealthCalendar> CREATOR = new Parcelable.Creator<HealthCalendar>() { // from class: com.huawei.ui.commonui.calendarview.HealthCalendar.3
        @Override // android.os.Parcelable.Creator
        /* renamed from: cxe_, reason: merged with bridge method [inline-methods] */
        public HealthCalendar createFromParcel(Parcel parcel) {
            return new HealthCalendar(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public HealthCalendar[] newArray(int i) {
            return new HealthCalendar[i];
        }
    };
    private static final int DECIMAL_UNIT = 10;
    private static final int FIXED_VALUE = 17;
    private static final int HOUR_ONE_DAY = 12;
    private static final int MAX_DAY_ONE_MONTH = 31;
    static final int MAX_YEAR = 2099;
    static final int MIN_YEAR = 1900;
    private static final int MONTH_ONE_YEAR = 12;
    public static final int WEEK_START_ON_MON = 2;
    public static final int WEEK_START_ON_SAT = 7;
    public static final int WEEK_START_ON_SUN = 1;
    private int mDay;
    private boolean mIsInRange;
    private boolean mIsPresentDay;
    private boolean mIsPresentMonth;
    private List<HealthMark> mMarks;
    private int mMonth;
    private int mYear;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public HealthCalendar() {
    }

    private HealthCalendar(Parcel parcel) {
        this.mYear = parcel.readInt();
        this.mMonth = parcel.readInt();
        this.mDay = parcel.readInt();
    }

    public HealthCalendar(int i, int i2, int i3) {
        this.mYear = i;
        this.mMonth = i2;
        this.mDay = i3;
    }

    public HealthCalendar(long j) {
        Date date = new Date();
        date.setTime(j);
        setTime(date);
    }

    public int getYear() {
        return this.mYear;
    }

    public void setYear(int i) {
        this.mYear = i;
    }

    public int getMonth() {
        return this.mMonth;
    }

    public void setMonth(int i) {
        this.mMonth = i;
    }

    public int getDay() {
        return this.mDay;
    }

    public void setDay(int i) {
        this.mDay = i;
    }

    public boolean isPresentMonth() {
        return this.mIsPresentMonth;
    }

    public void setPresentMonth(boolean z) {
        this.mIsPresentMonth = z;
    }

    public boolean isPresentDay() {
        return this.mIsPresentDay;
    }

    public void setPresentDay(boolean z) {
        this.mIsPresentDay = z;
    }

    public void setInRange(boolean z) {
        this.mIsInRange = z;
    }

    public boolean isInRange() {
        return this.mIsInRange;
    }

    public List<HealthMark> getMarks() {
        return this.mMarks;
    }

    public void setMarks(List<HealthMark> list) {
        this.mMarks = list;
    }

    public void setTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        this.mYear = calendar.get(1);
        this.mMonth = calendar.get(2) + 1;
        this.mDay = calendar.get(5);
    }

    public void addMark(HealthMark healthMark) {
        if (this.mMarks == null) {
            this.mMarks = new ArrayList();
        }
        this.mMarks.add(healthMark);
    }

    public boolean hasMark() {
        List<HealthMark> list = this.mMarks;
        return (list == null || list.size() == 0) ? false : true;
    }

    public boolean isSameMonth(HealthCalendar healthCalendar) {
        return this.mYear == healthCalendar.getYear() && this.mMonth == healthCalendar.getMonth();
    }

    @Override // java.lang.Comparable
    public int compareTo(HealthCalendar healthCalendar) {
        if (healthCalendar == null) {
            return 1;
        }
        return toString().compareTo(healthCalendar.toString());
    }

    public boolean isAvailable() {
        int i = this.mYear;
        boolean z = i > 0;
        int i2 = this.mMonth;
        boolean z2 = i2 > 0;
        int i3 = this.mDay;
        boolean z3 = i3 > 0;
        boolean z4 = i >= 1900;
        return (i <= 2099) & z4 & (i2 <= 12) & z & z2 & z3 & (i3 <= 31);
    }

    public Calendar transformCalendar() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(this.mYear, this.mMonth - 1, this.mDay, 12, 0, 0);
        return calendar;
    }

    public HealthCalendar transformFromCalendar(Calendar calendar) {
        this.mYear = calendar.get(1);
        this.mMonth = calendar.get(2) + 1;
        this.mDay = calendar.get(5);
        return this;
    }

    public int getDayOfWeekOffset(int i) {
        int i2 = transformCalendar().get(7);
        if (i == 1) {
            return i2 - 1;
        }
        if (i == 2) {
            if (i2 == 1) {
                return 6;
            }
            return i2 - i;
        }
        if (i2 == 7) {
            return 0;
        }
        return i2;
    }

    public int getWeek() {
        return transformCalendar().get(7);
    }

    public boolean equals(Object obj) {
        if (obj instanceof HealthCalendar) {
            HealthCalendar healthCalendar = (HealthCalendar) obj;
            if (healthCalendar.getYear() == this.mYear && healthCalendar.getMonth() == this.mMonth && healthCalendar.getDay() == this.mDay) {
                return true;
            }
        }
        return super.equals(obj);
    }

    public String toString() {
        Object valueOf;
        Object valueOf2;
        StringBuilder sb = new StringBuilder();
        sb.append(this.mYear);
        sb.append("");
        int i = this.mMonth;
        if (i < 10) {
            valueOf = "0" + this.mMonth;
        } else {
            valueOf = Integer.valueOf(i);
        }
        sb.append(valueOf);
        sb.append("");
        int i2 = this.mDay;
        if (i2 < 10) {
            valueOf2 = "0" + this.mDay;
        } else {
            valueOf2 = Integer.valueOf(i2);
        }
        sb.append(valueOf2);
        return sb.toString();
    }

    public void clearMark() {
        setMarks(null);
    }

    public int hashCode() {
        return (((Integer.valueOf(this.mYear).hashCode() * 17) + Integer.valueOf(this.mMonth).hashCode()) * 17) + Integer.valueOf(this.mDay).hashCode();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mYear);
        parcel.writeInt(this.mMonth);
        parcel.writeInt(this.mDay);
    }
}
