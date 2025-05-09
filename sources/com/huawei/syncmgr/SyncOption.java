package com.huawei.syncmgr;

import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes6.dex */
public class SyncOption implements Parcelable {
    public static final Parcelable.Creator<SyncOption> CREATOR = new Parcelable.Creator<SyncOption>() { // from class: com.huawei.syncmgr.SyncOption.4
        @Override // android.os.Parcelable.Creator
        /* renamed from: cuX_, reason: merged with bridge method [inline-methods] */
        public SyncOption createFromParcel(Parcel parcel) {
            return new SyncOption(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public SyncOption[] newArray(int i) {
            return new SyncOption[i];
        }
    };
    private static final String END_TIME = "endTime";
    private static final String IS_MANUAL_SYNC = "isManualSync";
    private static final String IS_NIGHT_CHECK = "isNightCheck";
    private static final String IS_TEMPERATURE_CHECK = "isTemperatureCheck";
    private static final String MODE = "mode";
    private static final String START_TIME = "startTime";
    private final ContentValues mValueHolder;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    protected SyncOption(Parcel parcel) {
        this.mValueHolder = (ContentValues) parcel.readParcelable(ContentValues.class.getClassLoader());
    }

    private SyncOption(ContentValues contentValues) {
        this.mValueHolder = contentValues;
    }

    public boolean isManualSync() {
        return getBooleanValue(IS_MANUAL_SYNC);
    }

    public boolean isNightCheck() {
        return getBooleanValue(IS_NIGHT_CHECK);
    }

    public boolean isTemperatureCheck() {
        return getBooleanValue(IS_TEMPERATURE_CHECK);
    }

    public int getMode(int i) {
        ContentValues contentValues = this.mValueHolder;
        return (contentValues == null || !contentValues.containsKey("mode") || this.mValueHolder.getAsInteger("mode") == null) ? i : this.mValueHolder.getAsInteger("mode").intValue();
    }

    public long getStartTime() {
        ContentValues contentValues = this.mValueHolder;
        if (contentValues == null || !contentValues.containsKey("startTime") || this.mValueHolder.getAsLong("startTime") == null) {
            return 0L;
        }
        return this.mValueHolder.getAsLong("startTime").longValue();
    }

    public long getEndTime() {
        ContentValues contentValues = this.mValueHolder;
        if (contentValues == null || !contentValues.containsKey("endTime")) {
            return System.currentTimeMillis();
        }
        if (this.mValueHolder.getAsLong("endTime") == null) {
            return System.currentTimeMillis();
        }
        return this.mValueHolder.getAsLong("endTime").longValue();
    }

    public String getCustomParameter(String str) {
        return getString(str);
    }

    private boolean getBooleanValue(String str) {
        ContentValues contentValues = this.mValueHolder;
        if (contentValues == null || !contentValues.containsKey(str) || this.mValueHolder.getAsBoolean(str) == null) {
            return false;
        }
        return this.mValueHolder.getAsBoolean(str).booleanValue();
    }

    public String getString(String str) {
        return this.mValueHolder.getAsString(str);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.mValueHolder, i);
    }

    public static c builder() {
        return new c();
    }

    public static class c {

        /* renamed from: a, reason: collision with root package name */
        private ContentValues f8726a;

        public SyncOption c() {
            return new SyncOption(cuY_());
        }

        public c e(boolean z) {
            cuY_().put(SyncOption.IS_MANUAL_SYNC, Boolean.valueOf(z));
            return this;
        }

        public c b(boolean z) {
            cuY_().put(SyncOption.IS_NIGHT_CHECK, Boolean.valueOf(z));
            return this;
        }

        public c c(boolean z) {
            cuY_().put(SyncOption.IS_TEMPERATURE_CHECK, Boolean.valueOf(z));
            return this;
        }

        public c c(String str, String str2) {
            cuY_().put(str, str2);
            return this;
        }

        private ContentValues cuY_() {
            if (this.f8726a == null) {
                this.f8726a = new ContentValues();
            }
            return this.f8726a;
        }
    }

    public String toString() {
        return "CoreSleepSyncOption {mValueHolder = " + this.mValueHolder + '}';
    }
}
