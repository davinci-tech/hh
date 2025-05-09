package com.google.android.gms.fitness.data;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;
import com.google.android.gms.internal.fitness.zzfa;
import java.util.concurrent.TimeUnit;

/* loaded from: classes2.dex */
public class Session extends AbstractSafeParcelable {
    public static final Parcelable.Creator<Session> CREATOR = new zzad();
    public static final String EXTRA_SESSION = "vnd.google.fitness.session";
    public static final String MIME_TYPE_PREFIX = "vnd.google.fitness.session/";
    private final String description;
    private final String name;
    private final int zzai;
    private final zzb zzay;
    private final String zzdz;
    private final Long zzea;
    private final long zzs;
    private final long zzt;

    public Session(long j, long j2, String str, String str2, String str3, int i, zzb zzbVar, Long l) {
        this.zzs = j;
        this.zzt = j2;
        this.name = str;
        this.zzdz = str2;
        this.description = str3;
        this.zzai = i;
        this.zzay = zzbVar;
        this.zzea = l;
    }

    /* loaded from: classes8.dex */
    public static class Builder {
        private Long zzea;
        private long zzs = 0;
        private long zzt = 0;
        private String name = null;
        private String zzdz = null;
        private String description = "";
        private int zzai = 4;

        public Builder setStartTime(long j, TimeUnit timeUnit) {
            Preconditions.checkState(j > 0, "Start time should be positive.");
            this.zzs = timeUnit.toMillis(j);
            return this;
        }

        public Builder setEndTime(long j, TimeUnit timeUnit) {
            Preconditions.checkState(j >= 0, "End time should be positive.");
            this.zzt = timeUnit.toMillis(j);
            return this;
        }

        public Builder setName(String str) {
            Preconditions.checkArgument(str.length() <= 100, "Session name cannot exceed %d characters", 100);
            this.name = str;
            return this;
        }

        public Builder setIdentifier(String str) {
            Preconditions.checkArgument(str != null && TextUtils.getTrimmedLength(str) > 0);
            this.zzdz = str;
            return this;
        }

        public Builder setDescription(String str) {
            Preconditions.checkArgument(str.length() <= 1000, "Session description cannot exceed %d characters", 1000);
            this.description = str;
            return this;
        }

        public Builder setActivity(String str) {
            this.zzai = zzfa.zzl(str);
            return this;
        }

        public Builder setActiveTime(long j, TimeUnit timeUnit) {
            this.zzea = Long.valueOf(timeUnit.toMillis(j));
            return this;
        }

        public Session build() {
            Preconditions.checkState(this.zzs > 0, "Start time should be specified.");
            long j = this.zzt;
            Preconditions.checkState(j == 0 || j > this.zzs, "End time should be later than start time.");
            if (this.zzdz == null) {
                String str = this.name;
                if (str == null) {
                    str = "";
                }
                long j2 = this.zzs;
                StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 20);
                sb.append(str);
                sb.append(j2);
                this.zzdz = sb.toString();
            }
            return new Session(this);
        }
    }

    private Session(Builder builder) {
        this(builder.zzs, builder.zzt, builder.name, builder.zzdz, builder.description, builder.zzai, null, builder.zzea);
    }

    public static Session extract(Intent intent) {
        if (intent == null) {
            return null;
        }
        return (Session) SafeParcelableSerializer.deserializeFromIntentExtra(intent, EXTRA_SESSION, CREATOR);
    }

    public static String getMimeType(String str) {
        String valueOf = String.valueOf(str);
        return valueOf.length() != 0 ? MIME_TYPE_PREFIX.concat(valueOf) : new String(MIME_TYPE_PREFIX);
    }

    public long getStartTime(TimeUnit timeUnit) {
        return timeUnit.convert(this.zzs, TimeUnit.MILLISECONDS);
    }

    public long getEndTime(TimeUnit timeUnit) {
        return timeUnit.convert(this.zzt, TimeUnit.MILLISECONDS);
    }

    public long getActiveTime(TimeUnit timeUnit) {
        Preconditions.checkState(this.zzea != null, "Active time is not set");
        return timeUnit.convert(this.zzea.longValue(), TimeUnit.MILLISECONDS);
    }

    public boolean hasActiveTime() {
        return this.zzea != null;
    }

    public boolean isOngoing() {
        return this.zzt == 0;
    }

    public String getName() {
        return this.name;
    }

    public String getIdentifier() {
        return this.zzdz;
    }

    public String getDescription() {
        return this.description;
    }

    public String getActivity() {
        return zzfa.getName(this.zzai);
    }

    public String getAppPackageName() {
        zzb zzbVar = this.zzay;
        if (zzbVar == null) {
            return null;
        }
        return zzbVar.getPackageName();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Session)) {
            return false;
        }
        Session session = (Session) obj;
        return this.zzs == session.zzs && this.zzt == session.zzt && Objects.equal(this.name, session.name) && Objects.equal(this.zzdz, session.zzdz) && Objects.equal(this.description, session.description) && Objects.equal(this.zzay, session.zzay) && this.zzai == session.zzai;
    }

    public int hashCode() {
        return Objects.hashCode(Long.valueOf(this.zzs), Long.valueOf(this.zzt), this.zzdz);
    }

    public String toString() {
        return Objects.toStringHelper(this).add("startTime", Long.valueOf(this.zzs)).add("endTime", Long.valueOf(this.zzt)).add("name", this.name).add("identifier", this.zzdz).add("description", this.description).add("activity", Integer.valueOf(this.zzai)).add("application", this.zzay).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeLong(parcel, 1, this.zzs);
        SafeParcelWriter.writeLong(parcel, 2, this.zzt);
        SafeParcelWriter.writeString(parcel, 3, getName(), false);
        SafeParcelWriter.writeString(parcel, 4, getIdentifier(), false);
        SafeParcelWriter.writeString(parcel, 5, getDescription(), false);
        SafeParcelWriter.writeInt(parcel, 7, this.zzai);
        SafeParcelWriter.writeParcelable(parcel, 8, this.zzay, i, false);
        SafeParcelWriter.writeLongObject(parcel, 9, this.zzea, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
