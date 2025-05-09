package com.google.android.gms.fitness;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.request.DataDeleteRequest;
import com.google.android.gms.fitness.request.DataReadRequest;
import com.google.android.gms.fitness.request.DataUpdateListenerRegistrationRequest;
import com.google.android.gms.fitness.request.DataUpdateRequest;
import com.google.android.gms.fitness.result.DailyTotalResult;
import com.google.android.gms.fitness.result.DataReadResult;
import java.util.concurrent.TimeUnit;

/* loaded from: classes2.dex */
public interface HistoryApi {
    PendingResult<Status> deleteData(GoogleApiClient googleApiClient, DataDeleteRequest dataDeleteRequest);

    PendingResult<Status> insertData(GoogleApiClient googleApiClient, DataSet dataSet);

    PendingResult<DailyTotalResult> readDailyTotal(GoogleApiClient googleApiClient, DataType dataType);

    PendingResult<DailyTotalResult> readDailyTotalFromLocalDevice(GoogleApiClient googleApiClient, DataType dataType);

    PendingResult<DataReadResult> readData(GoogleApiClient googleApiClient, DataReadRequest dataReadRequest);

    PendingResult<Status> registerDataUpdateListener(GoogleApiClient googleApiClient, DataUpdateListenerRegistrationRequest dataUpdateListenerRegistrationRequest);

    PendingResult<Status> unregisterDataUpdateListener(GoogleApiClient googleApiClient, PendingIntent pendingIntent);

    PendingResult<Status> updateData(GoogleApiClient googleApiClient, DataUpdateRequest dataUpdateRequest);

    /* loaded from: classes8.dex */
    public static class ViewIntentBuilder {
        private final Context zzp;
        private final DataType zzq;
        private DataSource zzr;
        private long zzs;
        private long zzt;
        private String zzu;

        public ViewIntentBuilder(Context context, DataType dataType) {
            this.zzp = context;
            this.zzq = dataType;
        }

        public ViewIntentBuilder setTimeInterval(long j, long j2, TimeUnit timeUnit) {
            this.zzs = timeUnit.toMillis(j);
            this.zzt = timeUnit.toMillis(j2);
            return this;
        }

        public ViewIntentBuilder setDataSource(DataSource dataSource) {
            Preconditions.checkArgument(dataSource.getDataType().equals(this.zzq), "Data source %s is not for the data type %s", dataSource, this.zzq);
            this.zzr = dataSource;
            return this;
        }

        public ViewIntentBuilder setPreferredApplication(String str) {
            this.zzu = str;
            return this;
        }

        public Intent build() {
            Intent intent;
            ResolveInfo resolveActivity;
            Preconditions.checkState(this.zzs > 0, "Start time must be set");
            Preconditions.checkState(this.zzt > this.zzs, "End time must be set and after start time");
            Intent intent2 = new Intent(Fitness.ACTION_VIEW);
            intent2.setType(DataType.getMimeType(this.zzr.getDataType()));
            intent2.putExtra(Fitness.EXTRA_START_TIME, this.zzs);
            intent2.putExtra(Fitness.EXTRA_END_TIME, this.zzt);
            SafeParcelableSerializer.serializeToIntentExtra(this.zzr, intent2, DataSource.EXTRA_DATA_SOURCE);
            if (this.zzu == null || (resolveActivity = this.zzp.getPackageManager().resolveActivity((intent = new Intent(intent2).setPackage(this.zzu)), 0)) == null) {
                return intent2;
            }
            intent.setComponent(new ComponentName(this.zzu, resolveActivity.activityInfo.name));
            return intent;
        }
    }
}
