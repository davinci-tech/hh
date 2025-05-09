package com.huawei.hms.hihealth.data;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.huawei.hms.common.internal.Preconditions;
import com.huawei.hms.common.internal.safeparcel.SafeParcelReader;
import com.huawei.hms.common.internal.safeparcel.SafeParcelWriter;
import com.huawei.hms.common.internal.safeparcel.SafeParcelable;
import com.huawei.hms.common.internal.safeparcel.SafeParcelableSerializer;
import com.huawei.hms.health.aabz;
import com.huawei.hms.health.aacc;
import com.huawei.hms.health.aacs;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/* loaded from: classes9.dex */
public class ActivityRecord implements SafeParcelable {
    private static final int ACTIVITY_SUMMARY_ID = 9;
    private static final int ACTIVITY_TYPE_ID = 6;
    private static final int APP_INFO_ID = 8;
    public static final Parcelable.Creator<ActivityRecord> CREATOR = new aab();
    private static final long DEFAULT_TIME_MILLIS = 0;
    private static final int DESC_ID = 7;
    private static final int DEVICE_INFO_ID = 13;
    private static final int DURATION_TIME_ID = 5;
    private static final String END_TIME_ERROR_MSG = "End time should be later than start time and the value ranges from 1388505600000ms to 4102415999000ms.";
    private static final int END_TIME_ID = 4;

    @Deprecated
    public static final String EXTRA_ACTIVITY_RECORD = "vnd.huawei.hihealth.activityRecord";
    private static final int ID = 2;
    private static final int META_DATA_ID = 12;

    @Deprecated
    public static final String MIME_TYPE_PREFIX = "vnd.huawei.hihealth.mimeType/";
    private static final int NAME_ID = 1;
    private static final int START_TIME_ID = 3;
    private static final String TAG = "ActivityRecord";
    private static final int TIME_ZONE_ID = 10;
    private final Long activeTime;
    private ActivitySummary activitySummary;
    private final int activityTypeId;
    private final AppInfo appInfo;
    private final String desc;
    private List<SampleSetDetail> details;
    private final DeviceInfo deviceInfo;
    private final Long durationTime;
    private final long endTime;
    private String gzipDetail;
    private final String id;
    private String metadata;
    private final String name;
    private final long startTime;
    private List<SubDataRelation> subDataRelationList;
    private String timeZone;

    private boolean canContinueParceling(boolean z, boolean z2) {
        return z && z2;
    }

    private boolean checkCounter(int i, int i2) {
        return i2 <= i;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, getName(), false);
        SafeParcelWriter.writeString(parcel, 2, getId(), false);
        SafeParcelWriter.writeLong(parcel, 3, this.startTime);
        SafeParcelWriter.writeLong(parcel, 4, this.endTime);
        SafeParcelWriter.writeLongObject(parcel, 5, this.durationTime, false);
        SafeParcelWriter.writeString(parcel, 6, getActivityType(), false);
        SafeParcelWriter.writeString(parcel, 7, getDesc(), false);
        SafeParcelWriter.writeParcelable(parcel, 8, this.appInfo, i, false);
        SafeParcelWriter.writeParcelable(parcel, 9, this.activitySummary, i, false);
        SafeParcelWriter.writeString(parcel, 10, this.timeZone, false);
        SafeParcelWriter.writeString(parcel, 12, this.metadata, false);
        SafeParcelWriter.writeParcelable(parcel, 13, this.deviceInfo, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public String toString() {
        String str;
        String str2;
        String str3;
        String str4;
        AppInfo appInfo = this.appInfo;
        if (appInfo != null) {
            str = appInfo.getPackageName();
            str2 = this.appInfo.getDomainName();
            str3 = this.appInfo.getVersion();
            str4 = this.appInfo.getDetailsUrl();
        } else {
            str = "";
            str2 = str;
            str3 = str2;
            str4 = str3;
        }
        DeviceInfo deviceInfo = this.deviceInfo;
        String deviceIdentifier = deviceInfo != null ? deviceInfo.getDeviceIdentifier() : "";
        StringBuilder aab2 = com.huawei.hms.health.aab.aab("SessionInfo{startTimeMillis = ");
        aab2.append(this.startTime);
        aab2.append(", endTimeMillis = ");
        aab2.append(this.endTime);
        aab2.append(", name = ");
        aab2.append(this.name);
        aab2.append(", id = ");
        aab2.append(this.id);
        aab2.append(", desc = ");
        aab2.append(this.desc);
        aab2.append(", typeId = ");
        aab2.append(this.activityTypeId);
        aab2.append(", activeTimeMillis = ");
        aab2.append(this.durationTime);
        aab2.append(", packageName = ");
        aab2.append(str);
        aab2.append(", activitySummary = ");
        aab2.append(this.activitySummary);
        aab2.append(", timeZone = ");
        aab2.append(this.timeZone);
        aab2.append(", domainName = ");
        aab2.append(str2);
        aab2.append(", version = ");
        aab2.append(str3);
        aab2.append(", url = ");
        aab2.append(str4);
        aab2.append(", deviceId = ");
        aab2.append(deviceIdentifier);
        aab2.append("}");
        return aab2.toString();
    }

    public void setSubDataRelationList(List<SubDataRelation> list) {
        this.subDataRelationList = list;
    }

    public void setGzipDetail(String str) {
        this.gzipDetail = str;
    }

    public void setDetails(List<SampleSetDetail> list) {
        this.details = list;
    }

    public boolean isKeepGoing() {
        return this.endTime == 0;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Long.valueOf(this.startTime), Long.valueOf(this.endTime), this.id});
    }

    public boolean hasDurationTime() {
        return this.durationTime != null;
    }

    public String getTimeZone() {
        return this.timeZone;
    }

    public List<SubDataRelation> getSubDataRelationList() {
        return this.subDataRelationList;
    }

    public long getStartTime(TimeUnit timeUnit) {
        return timeUnit.convert(this.startTime, TimeUnit.MILLISECONDS);
    }

    public String getPackageName() {
        AppInfo appInfo = this.appInfo;
        if (appInfo != null) {
            return appInfo.getPackageName();
        }
        return null;
    }

    public String getName() {
        return this.name;
    }

    public String getMetadata() {
        return this.metadata;
    }

    public String getId() {
        return this.id;
    }

    public String getGzipDetail() {
        return this.gzipDetail;
    }

    public long getEndTime(TimeUnit timeUnit) {
        return timeUnit.convert(this.endTime, TimeUnit.MILLISECONDS);
    }

    public long getDurationTime(TimeUnit timeUnit) {
        Long l = this.durationTime;
        if (l != null) {
            return timeUnit.convert(l.longValue(), TimeUnit.MILLISECONDS);
        }
        aabz.aabc(TAG, "Active time is not set.");
        return 0L;
    }

    public DeviceInfo getDeviceInfo() {
        return this.deviceInfo;
    }

    public List<SampleSetDetail> getDetails() {
        return this.details;
    }

    public String getDesc() {
        return this.desc;
    }

    public String getAppVersion() {
        AppInfo appInfo = this.appInfo;
        if (appInfo != null) {
            return appInfo.getVersion();
        }
        return null;
    }

    public AppInfo getAppInfo() {
        return this.appInfo;
    }

    public String getAppId() {
        AppInfo appInfo = this.appInfo;
        if (appInfo != null) {
            return appInfo.getAppId();
        }
        return null;
    }

    public String getAppDomainName() {
        AppInfo appInfo = this.appInfo;
        if (appInfo != null) {
            return appInfo.getDomainName();
        }
        return null;
    }

    public String getAppDetailsUrl() {
        AppInfo appInfo = this.appInfo;
        if (appInfo != null) {
            return appInfo.getDetailsUrl();
        }
        return null;
    }

    public String getActivityType() {
        return aacc.aab(this.activityTypeId);
    }

    public static class Builder {
        private ActivitySummary activitySummary;
        private int activityTypeId;
        private AppInfo appInfo;
        private String desc;
        private List<SampleSetDetail> details;
        private DeviceInfo deviceInfo;
        private Long durationTime;
        private long endTime;
        public String gzipDetail;
        private String id;
        private String metadata;
        private String name;
        private long startTime;
        private List<SubDataRelation> subDataRelationList;
        private String timeZone;

        public ActivityRecord build() {
            Preconditions.checkArgument(aacs.aaba(this.startTime), "Must specify valid start time.");
            long j = this.endTime;
            Preconditions.checkArgument(j == 0 || (aacs.aaba(j) && this.endTime > this.startTime), ActivityRecord.END_TIME_ERROR_MSG);
            Preconditions.checkArgument(this.activityTypeId != 0, "Must set activityTypeId value");
            if (this.id == null) {
                String str = this.name;
                if (str == null) {
                    str = "";
                }
                StringBuilder aab = com.huawei.hms.health.aab.aab(str);
                aab.append(this.startTime);
                this.id = aab.toString();
            }
            if (TextUtils.isEmpty(this.timeZone)) {
                this.timeZone = new SimpleDateFormat("Z", Locale.getDefault()).format(new Date());
            }
            return new ActivityRecord(this, null);
        }

        public Builder setTimeZone(String str) {
            this.timeZone = str;
            return this;
        }

        public Builder setSubDataRelationList(List<SubDataRelation> list) {
            this.subDataRelationList = list;
            return this;
        }

        public Builder setStartTime(long j, TimeUnit timeUnit) {
            long millis = timeUnit.toMillis(j);
            this.startTime = millis;
            Preconditions.checkArgument(aacs.aaba(millis), "Start time has to be greater than 0 and the value ranges from 1388505600000ms to 4102415999000ms.");
            return this;
        }

        public Builder setName(String str) {
            Preconditions.checkArgument(this.name == null || aacs.aab(str), "ActivityRecord Name Is Illegal!");
            this.name = str;
            return this;
        }

        public Builder setMetadata(String str) {
            Preconditions.checkArgument(aacs.aabb(str), "ActivityRecord mataData illegal ");
            this.metadata = str;
            return this;
        }

        public Builder setId(String str) {
            this.id = str;
            return this;
        }

        public Builder setGzipDetail(String str) {
            this.gzipDetail = str;
            return this;
        }

        public Builder setEndTime(long j, TimeUnit timeUnit) {
            long millis = timeUnit.toMillis(j);
            this.endTime = millis;
            Preconditions.checkArgument(millis == 0 || aacs.aaba(millis), "End time has to be equal to 0 or the value ranges from 1388505600000ms to 4102415999000ms.");
            return this;
        }

        public Builder setDurationTime(long j, TimeUnit timeUnit) {
            this.durationTime = Long.valueOf(timeUnit.toMillis(j));
            return this;
        }

        public Builder setDeviceInfo(DeviceInfo deviceInfo) {
            Preconditions.checkArgument(deviceInfo == null || aacs.aabc(deviceInfo.getDeviceIdentifier()), "ActivityRecord deviceInfo Is Illegal!");
            this.deviceInfo = deviceInfo;
            return this;
        }

        public Builder setDesc(String str) {
            this.desc = str;
            return this;
        }

        public Builder setAppInfo(AppInfo appInfo) {
            Preconditions.checkArgument(this.name == null || aacs.aab(appInfo.getPackageName()), "ActivityRecord Name Is Illegal!");
            this.appInfo = appInfo;
            return this;
        }

        public Builder setActivityTypeId(String str) {
            this.activityTypeId = aacc.aab(str);
            return this;
        }

        public Builder setActivitySummary(ActivitySummary activitySummary) {
            this.activitySummary = activitySummary;
            return this;
        }

        public Builder setActiveTime(long j, TimeUnit timeUnit) {
            this.durationTime = Long.valueOf(timeUnit.toMillis(j));
            return this;
        }

        public Builder(ActivityRecord activityRecord) {
            this.name = null;
            this.id = null;
            this.startTime = 0L;
            this.endTime = 0L;
            this.desc = "";
            this.activitySummary = null;
            this.subDataRelationList = new ArrayList();
            this.timeZone = null;
            this.metadata = null;
            this.details = new ArrayList();
            this.name = activityRecord.name;
            this.id = activityRecord.id;
            this.startTime = activityRecord.startTime;
            this.endTime = activityRecord.endTime;
            this.durationTime = activityRecord.durationTime;
            this.activityTypeId = activityRecord.activityTypeId;
            this.desc = activityRecord.desc;
            this.appInfo = activityRecord.appInfo;
            this.activitySummary = activityRecord.activitySummary;
            this.timeZone = activityRecord.timeZone;
            this.details = activityRecord.details;
            this.metadata = activityRecord.metadata;
            this.deviceInfo = activityRecord.deviceInfo;
            this.subDataRelationList = activityRecord.subDataRelationList;
            this.gzipDetail = activityRecord.gzipDetail;
        }

        public Builder() {
            this.name = null;
            this.id = null;
            this.startTime = 0L;
            this.endTime = 0L;
            this.desc = "";
            this.activitySummary = null;
            this.subDataRelationList = new ArrayList();
            this.timeZone = null;
            this.metadata = null;
            this.details = new ArrayList();
        }
    }

    public ActivitySummary getActivitySummary() {
        return this.activitySummary;
    }

    public Long getActiveTime(TimeUnit timeUnit) {
        long convert;
        Long l = this.activeTime;
        if (l == null) {
            aabz.aab(TAG, "Active time is not set.");
            convert = 0;
        } else {
            convert = timeUnit.convert(l.longValue(), TimeUnit.MILLISECONDS);
        }
        return Long.valueOf(convert);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ActivityRecord)) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        ActivityRecord activityRecord = (ActivityRecord) obj;
        return Objects.equals(this.name, activityRecord.name) && Objects.equals(this.id, activityRecord.id) && this.startTime == activityRecord.startTime && this.endTime == activityRecord.endTime && this.activityTypeId == activityRecord.activityTypeId && Objects.equals(this.desc, activityRecord.desc) && Objects.equals(this.appInfo, activityRecord.appInfo) && Objects.equals(this.activitySummary, activityRecord.activitySummary) && Objects.equals(this.timeZone, activityRecord.timeZone) && Objects.equals(this.deviceInfo, activityRecord.deviceInfo);
    }

    private void verifyStartAndEndTime(long j, long j2) {
        Preconditions.checkState(aacs.aaba(j), "Must specify valid start time.");
        Preconditions.checkState(j2 == 0 || (aacs.aaba(j2) && j2 > j), "End time should be later than start time.");
    }

    private void verifyFieldCount(int i, int i2) {
        if (i2 > i) {
            aabz.aab(TAG, "Max loop reached, ActivityRecord parcel failed");
        }
    }

    private void verifyActivityRecord(String str, String str2, String str3) {
        Preconditions.checkArgument(aacs.aabc(str2), "ActivityRecordId Length Is Illegal!");
        boolean z = true;
        Preconditions.checkArgument(str3 == null || "".equals(str3) || aacs.aabc(str3), "Desc Length Is Illegal!");
        if (str != null && !aacs.aab(str)) {
            z = false;
        }
        Preconditions.checkArgument(z, "ActivityRecord Name Is Illegal!");
    }

    @Deprecated
    public static String getMimeType(String str) {
        String valueOf = String.valueOf(str);
        return valueOf.length() != 0 ? MIME_TYPE_PREFIX.concat(valueOf) : MIME_TYPE_PREFIX;
    }

    @Deprecated
    public static ActivityRecord extract(Intent intent) {
        if (intent != null) {
            SafeParcelable deserializeFromIntentExtra = SafeParcelableSerializer.deserializeFromIntentExtra(intent, EXTRA_ACTIVITY_RECORD, CREATOR);
            if (deserializeFromIntentExtra instanceof ActivityRecord) {
                return (ActivityRecord) deserializeFromIntentExtra;
            }
        }
        return null;
    }

    private boolean continueParcel(Parcel parcel, int i) {
        return parcel.dataPosition() < i;
    }

    /* synthetic */ ActivityRecord(Builder builder, aab aabVar) {
        this(builder);
    }

    private ActivityRecord(Builder builder) {
        this(builder.startTime, builder.endTime, builder.name, builder.id, builder.desc, builder.activityTypeId, builder.durationTime, builder.appInfo, builder.activitySummary, builder.timeZone, builder.details, builder.metadata, builder.deviceInfo, builder.subDataRelationList, builder.gzipDetail);
    }

    protected ActivityRecord(Parcel parcel) {
        aabz.aabb(TAG, "ActivityRecord(Parcel parcel) entered");
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        long j = 0;
        AppInfo appInfo = null;
        ActivitySummary activitySummary = null;
        DeviceInfo deviceInfo = null;
        String str = null;
        String str2 = "";
        String str3 = str2;
        String str4 = str3;
        String str5 = "unknown";
        int i = 0;
        long j2 = 0;
        String str6 = null;
        Long l = null;
        while (true) {
            ActivitySummary activitySummary2 = activitySummary;
            if (!canContinueParceling(checkCounter(validateObjectHeader, i), continueParcel(parcel, validateObjectHeader))) {
                verifyFieldCount(validateObjectHeader, i);
                SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
                aabz.aabb(TAG, "appInfoTmp read");
                aabz.aabb(TAG, "activitySummary read");
                aabz.aabb(TAG, "timeZone read");
                aabz.aabb(TAG, "details read");
                this.name = str6;
                this.id = str2;
                this.startTime = j;
                this.endTime = j2;
                this.durationTime = l;
                this.activityTypeId = aacc.aab(str5);
                this.desc = str3;
                this.appInfo = appInfo;
                this.activitySummary = activitySummary2;
                this.timeZone = str4;
                this.metadata = str;
                this.activeTime = l;
                this.deviceInfo = deviceInfo;
                return;
            }
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 1:
                    str6 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 2:
                    str2 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 3:
                    j = SafeParcelReader.readLong(parcel, readHeader);
                    break;
                case 4:
                    j2 = SafeParcelReader.readLong(parcel, readHeader);
                    break;
                case 5:
                    l = Long.valueOf(SafeParcelReader.readLong(parcel, readHeader));
                    break;
                case 6:
                    str5 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 7:
                    str3 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 8:
                    appInfo = (AppInfo) SafeParcelReader.createParcelable(parcel, readHeader, AppInfo.CREATOR);
                    break;
                case 9:
                    activitySummary = (ActivitySummary) SafeParcelReader.createParcelable(parcel, readHeader, ActivitySummary.CREATOR);
                    continue;
                case 10:
                    str4 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 11:
                default:
                    SafeParcelReader.skipUnknownField(parcel, readHeader);
                    break;
                case 12:
                    str = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 13:
                    deviceInfo = (DeviceInfo) SafeParcelReader.createParcelable(parcel, readHeader, DeviceInfo.CREATOR);
                    break;
            }
            activitySummary = activitySummary2;
            i++;
        }
    }

    class aab implements Parcelable.Creator<ActivityRecord> {
        @Override // android.os.Parcelable.Creator
        public ActivityRecord createFromParcel(Parcel parcel) {
            aabz.aabb(ActivityRecord.TAG, "ActivityRecord createFromParcel entered");
            return new ActivityRecord(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public ActivityRecord[] newArray(int i) {
            return new ActivityRecord[i];
        }

        aab() {
        }
    }

    public ActivityRecord(long j, long j2, String str, String str2, String str3, int i, Long l, AppInfo appInfo, ActivitySummary activitySummary, String str4, List<SampleSetDetail> list, String str5, DeviceInfo deviceInfo, List<SubDataRelation> list2, String str6) {
        verifyStartAndEndTime(j, j2);
        verifyActivityRecord(str, str2, str3);
        this.name = str;
        this.id = str2;
        this.startTime = j;
        this.endTime = j2;
        this.durationTime = l;
        this.activityTypeId = i;
        this.desc = str3;
        this.appInfo = appInfo;
        this.activitySummary = activitySummary;
        this.timeZone = str4;
        this.details = list;
        this.metadata = str5;
        this.activeTime = l;
        this.deviceInfo = deviceInfo;
        this.subDataRelationList = list2;
        this.gzipDetail = str6;
    }
}
