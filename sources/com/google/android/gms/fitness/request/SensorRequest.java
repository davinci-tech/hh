package com.google.android.gms.fitness.request;

import android.os.SystemClock;
import androidx.core.location.LocationRequestCompat;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.location.LocationRequest;
import java.util.concurrent.TimeUnit;

/* loaded from: classes8.dex */
public class SensorRequest {
    public static final int ACCURACY_MODE_DEFAULT = 2;
    public static final int ACCURACY_MODE_HIGH = 3;
    public static final int ACCURACY_MODE_LOW = 1;
    private final long zzec;
    private final int zzed;
    private final long zzhs;
    private final long zzht;
    private final long zzhx;
    private final DataType zzq;
    private final DataSource zzr;

    private SensorRequest(Builder builder) {
        this.zzr = builder.zzr;
        this.zzq = builder.zzq;
        this.zzec = builder.zzec;
        this.zzht = builder.zzht;
        this.zzhs = builder.zzhs;
        this.zzed = builder.zzed;
        this.zzhx = builder.zzhx;
    }

    public static class Builder {
        private DataType zzq;
        private DataSource zzr;
        private long zzec = -1;
        private long zzht = 0;
        private long zzhs = 0;
        private boolean zzhy = false;
        private int zzed = 2;
        private long zzhx = LocationRequestCompat.PASSIVE_INTERVAL;

        public Builder setDataSource(DataSource dataSource) {
            this.zzr = dataSource;
            return this;
        }

        public Builder setDataType(DataType dataType) {
            this.zzq = dataType;
            return this;
        }

        public Builder setSamplingRate(long j, TimeUnit timeUnit) {
            Preconditions.checkArgument(j >= 0, "Cannot use a negative sampling interval");
            long micros = timeUnit.toMicros(j);
            this.zzec = micros;
            if (!this.zzhy) {
                this.zzht = micros / 2;
            }
            return this;
        }

        public Builder setFastestRate(int i, TimeUnit timeUnit) {
            Preconditions.checkArgument(i >= 0, "Cannot use a negative interval");
            this.zzhy = true;
            this.zzht = timeUnit.toMicros(i);
            return this;
        }

        public Builder setMaxDeliveryLatency(int i, TimeUnit timeUnit) {
            Preconditions.checkArgument(i >= 0, "Cannot use a negative delivery interval");
            this.zzhs = timeUnit.toMicros(i);
            return this;
        }

        public Builder setAccuracyMode(int i) {
            if (i != 1 && i != 3) {
                i = 2;
            }
            this.zzed = i;
            return this;
        }

        public Builder setTimeout(long j, TimeUnit timeUnit) {
            Preconditions.checkArgument(j > 0, "Invalid time out value specified: %d", Long.valueOf(j));
            Preconditions.checkArgument(timeUnit != null, "Invalid time unit specified");
            this.zzhx = timeUnit.toMicros(j);
            return this;
        }

        public SensorRequest build() {
            DataSource dataSource;
            boolean z = true;
            Preconditions.checkState((this.zzr == null && this.zzq == null) ? false : true, "Must call setDataSource() or setDataType()");
            DataType dataType = this.zzq;
            if (dataType != null && (dataSource = this.zzr) != null && !dataType.equals(dataSource.getDataType())) {
                z = false;
            }
            Preconditions.checkState(z, "Specified data type is incompatible with specified data source");
            return new SensorRequest(this);
        }
    }

    private SensorRequest(DataSource dataSource, LocationRequest locationRequest) {
        long micros = TimeUnit.MILLISECONDS.toMicros(locationRequest.getInterval());
        this.zzec = micros;
        this.zzht = TimeUnit.MILLISECONDS.toMicros(locationRequest.getFastestInterval());
        this.zzhs = micros;
        this.zzq = dataSource.getDataType();
        int priority = locationRequest.getPriority();
        this.zzed = priority != 100 ? priority != 104 ? 2 : 1 : 3;
        this.zzr = dataSource;
        long expirationTime = locationRequest.getExpirationTime();
        if (expirationTime == LocationRequestCompat.PASSIVE_INTERVAL) {
            this.zzhx = LocationRequestCompat.PASSIVE_INTERVAL;
        } else {
            this.zzhx = TimeUnit.MILLISECONDS.toMicros(expirationTime - SystemClock.elapsedRealtime());
        }
    }

    @Deprecated
    public static SensorRequest fromLocationRequest(DataSource dataSource, LocationRequest locationRequest) {
        return new SensorRequest(dataSource, locationRequest);
    }

    public DataSource getDataSource() {
        return this.zzr;
    }

    public DataType getDataType() {
        return this.zzq;
    }

    public long getSamplingRate(TimeUnit timeUnit) {
        return timeUnit.convert(this.zzec, TimeUnit.MICROSECONDS);
    }

    public long getFastestRate(TimeUnit timeUnit) {
        return timeUnit.convert(this.zzht, TimeUnit.MICROSECONDS);
    }

    public long getMaxDeliveryLatency(TimeUnit timeUnit) {
        return timeUnit.convert(this.zzhs, TimeUnit.MICROSECONDS);
    }

    public int getAccuracyMode() {
        return this.zzed;
    }

    public final long zzx() {
        return this.zzhx;
    }

    public String toString() {
        return Objects.toStringHelper(this).add("dataSource", this.zzr).add("dataType", this.zzq).add("samplingRateMicros", Long.valueOf(this.zzec)).add("deliveryLatencyMicros", Long.valueOf(this.zzhs)).add("timeOutMicros", Long.valueOf(this.zzhx)).toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SensorRequest)) {
            return false;
        }
        SensorRequest sensorRequest = (SensorRequest) obj;
        return Objects.equal(this.zzr, sensorRequest.zzr) && Objects.equal(this.zzq, sensorRequest.zzq) && this.zzec == sensorRequest.zzec && this.zzht == sensorRequest.zzht && this.zzhs == sensorRequest.zzhs && this.zzed == sensorRequest.zzed && this.zzhx == sensorRequest.zzhx;
    }

    public int hashCode() {
        return Objects.hashCode(this.zzr, this.zzq, Long.valueOf(this.zzec), Long.valueOf(this.zzht), Long.valueOf(this.zzhs), Integer.valueOf(this.zzed), Long.valueOf(this.zzhx));
    }
}
