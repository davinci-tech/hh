package com.huawei.hms.health;

import android.app.PendingIntent;
import android.os.Parcel;
import android.text.TextUtils;
import com.google.gson.reflect.TypeToken;
import com.huawei.hmf.tasks.Task;
import com.huawei.hms.common.internal.Preconditions;
import com.huawei.hms.hihealth.HiHealthStatusCodes;
import com.huawei.hms.hihealth.data.DataCollector;
import com.huawei.hms.hihealth.data.DataType;
import com.huawei.hms.hihealth.data.HealthKitApiInvoker;
import com.huawei.hms.hihealth.data.SamplePoint;
import com.huawei.hms.hihealth.data.SampleSet;
import com.huawei.hms.hihealth.options.DataCollectorsOptions;
import com.huawei.hms.hihealth.options.DeleteOptions;
import com.huawei.hms.hihealth.options.ModifyDataMonitorOptions;
import com.huawei.hms.hihealth.options.ReadOptions;
import com.huawei.hms.hihealth.options.UpdateOptions;
import com.huawei.hms.hihealth.result.ReadDetailResult;
import com.huawei.operation.utils.Constants;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public class aacl implements com.huawei.hms.hihealth.aabm {
    private static volatile aacl aab;
    private static volatile HealthKitApiInvoker aaba;

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Void aab(HealthKitApiInvoker healthKitApiInvoker, SampleSet sampleSet) throws Exception {
        ArrayList arrayList;
        int size;
        healthKitApiInvoker.setInterfaceProvider("DataController");
        healthKitApiInvoker.setInterfaceInvoked("insert");
        if (sampleSet == null || sampleSet.isEmpty()) {
            arrayList = new ArrayList();
        } else {
            Parcel obtain = Parcel.obtain();
            sampleSet.writeToParcel(obtain, sampleSet.describeContents());
            List<SamplePoint> samplePoints = sampleSet.getSamplePoints();
            int dataSize = obtain.dataSize();
            obtain.recycle();
            float f = dataSize / 131072.0f;
            arrayList = new ArrayList();
            if (f >= 1.0f && (size = samplePoints.size() / (((int) f) + 1)) > 1) {
                ArrayList arrayList2 = new ArrayList();
                Iterator<SamplePoint> it = samplePoints.iterator();
                loop0: while (true) {
                    int i = 0;
                    while (it.hasNext()) {
                        arrayList2.add(it.next());
                        i++;
                        if (i >= size) {
                            break;
                        }
                    }
                    SampleSet create = SampleSet.create(sampleSet.getDataCollector());
                    create.addSampleList(arrayList2);
                    arrayList.add(create);
                    arrayList2.clear();
                }
                if (!arrayList2.isEmpty()) {
                    sampleSet = SampleSet.create(sampleSet.getDataCollector());
                    sampleSet.addSampleList(arrayList2);
                }
            }
            arrayList.add(sampleSet);
        }
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            healthKitApiInvoker.setRequestBody(aacs.aab((SampleSet) it2.next()));
            aaci.aabc().aab(healthKitApiInvoker);
        }
        return null;
    }

    public Task<List<SampleSet>> aaba(final List<DataType> list) {
        final HealthKitApiInvoker healthKitApiInvoker = new HealthKitApiInvoker(aaba);
        return aacq.aab(new Callable() { // from class: com.huawei.hms.health.aacl$$ExternalSyntheticLambda6
            @Override // java.util.concurrent.Callable
            public final Object call() {
                List aaba2;
                aaba2 = aacl.this.aaba(list, healthKitApiInvoker);
                return aaba2;
            }
        });
    }

    public Task<SampleSet> aaba(DataType dataType) {
        throw new SecurityException(String.valueOf(HiHealthStatusCodes.API_FUNCTION_UNAVAILABLE));
    }

    public Task<Void> aaba() {
        final HealthKitApiInvoker healthKitApiInvoker = new HealthKitApiInvoker(aaba);
        return aacq.aab(new Callable() { // from class: com.huawei.hms.health.aacl$$ExternalSyntheticLambda1
            @Override // java.util.concurrent.Callable
            public final Object call() {
                Void aaba2;
                aaba2 = aacl.aaba(HealthKitApiInvoker.this);
                return aaba2;
            }
        });
    }

    public Task<List<SampleSet>> aab(final List<DataType> list, final int i, final int i2) {
        aab(i, i2);
        final HealthKitApiInvoker healthKitApiInvoker = new HealthKitApiInvoker(aaba);
        return aacq.aab(new Callable() { // from class: com.huawei.hms.health.aacl$$ExternalSyntheticLambda0
            @Override // java.util.concurrent.Callable
            public final Object call() {
                List aab2;
                aab2 = aacl.this.aab(list, i, i2, healthKitApiInvoker);
                return aab2;
            }
        });
    }

    public Task<Map<DataType, SamplePoint>> aab(final List<DataType> list) {
        Preconditions.checkArgument(aabz.aaba(list).booleanValue(), "Must set the dataTypes");
        Preconditions.checkArgument(list.size() <= 20, "The dataTypes size can not exceed 20");
        final HealthKitApiInvoker healthKitApiInvoker = new HealthKitApiInvoker(aaba);
        return aacq.aab(new Callable() { // from class: com.huawei.hms.health.aacl$$ExternalSyntheticLambda8
            @Override // java.util.concurrent.Callable
            public final Object call() {
                Map aab2;
                aab2 = aacl.aab(list, healthKitApiInvoker);
                return aab2;
            }
        });
    }

    public Task<Void> aab(final UpdateOptions updateOptions) {
        Preconditions.checkNotNull(updateOptions.getSampleSet(), "Must set the sample set");
        Preconditions.checkArgument(updateOptions.getStartTime() != 0, "Must set the non-zero value for startTimeMillis/startTime");
        Preconditions.checkArgument(updateOptions.getEndTime() != 0, "Must set the non-zero value for endTimeMillis/endTime");
        final HealthKitApiInvoker healthKitApiInvoker = new HealthKitApiInvoker(aaba);
        return aacq.aab(new Callable() { // from class: com.huawei.hms.health.aacl$$ExternalSyntheticLambda7
            @Override // java.util.concurrent.Callable
            public final Object call() {
                Void aab2;
                aab2 = aacl.aab(HealthKitApiInvoker.this, updateOptions);
                return aab2;
            }
        });
    }

    public Task<ReadDetailResult> aab(final ReadOptions readOptions) {
        final HealthKitApiInvoker healthKitApiInvoker = new HealthKitApiInvoker(aaba);
        return aacq.aab(new Callable() { // from class: com.huawei.hms.health.aacl$$ExternalSyntheticLambda4
            @Override // java.util.concurrent.Callable
            public final Object call() {
                ReadDetailResult aab2;
                aab2 = aacl.aab(HealthKitApiInvoker.this, readOptions);
                return aab2;
            }
        });
    }

    public Task<Void> aab(ModifyDataMonitorOptions modifyDataMonitorOptions) {
        throw new SecurityException(String.valueOf(HiHealthStatusCodes.API_FUNCTION_UNAVAILABLE));
    }

    public Task<Void> aab(final DeleteOptions deleteOptions) {
        final HealthKitApiInvoker healthKitApiInvoker = new HealthKitApiInvoker(aaba);
        return aacq.aab(new Callable() { // from class: com.huawei.hms.health.aacl$$ExternalSyntheticLambda9
            @Override // java.util.concurrent.Callable
            public final Object call() {
                Void aab2;
                aab2 = aacl.aab(HealthKitApiInvoker.this, deleteOptions);
                return aab2;
            }
        });
    }

    public Task<List<DataCollector>> aab(DataCollectorsOptions dataCollectorsOptions) {
        throw new SecurityException(String.valueOf(HiHealthStatusCodes.API_FUNCTION_UNAVAILABLE));
    }

    public Task<Void> aab(final SampleSet sampleSet) {
        Preconditions.checkNotNull(sampleSet, "Must set the data set");
        Preconditions.checkState(!sampleSet.getSamplePoints().isEmpty(), "Cannot use an empty data set");
        final HealthKitApiInvoker healthKitApiInvoker = new HealthKitApiInvoker(aaba);
        return aacq.aab(new Callable() { // from class: com.huawei.hms.health.aacl$$ExternalSyntheticLambda2
            @Override // java.util.concurrent.Callable
            public final Object call() {
                Void aab2;
                aab2 = aacl.aab(HealthKitApiInvoker.this, sampleSet);
                return aab2;
            }
        });
    }

    public Task<SampleSet> aab(final DataType dataType, final int i, final int i2) {
        aab(i, i2);
        final HealthKitApiInvoker healthKitApiInvoker = new HealthKitApiInvoker(aaba);
        return aacq.aab(new Callable() { // from class: com.huawei.hms.health.aacl$$ExternalSyntheticLambda10
            @Override // java.util.concurrent.Callable
            public final Object call() {
                SampleSet aab2;
                aab2 = aacl.aab(DataType.this, dataType, i, i2, healthKitApiInvoker);
                return aab2;
            }
        });
    }

    public Task<SampleSet> aab(final DataType dataType) {
        final HealthKitApiInvoker healthKitApiInvoker = new HealthKitApiInvoker(aaba);
        return aacq.aab(new Callable() { // from class: com.huawei.hms.health.aacl$$ExternalSyntheticLambda5
            @Override // java.util.concurrent.Callable
            public final Object call() {
                SampleSet aab2;
                aab2 = aacl.aab(DataType.this, healthKitApiInvoker);
                return aab2;
            }
        });
    }

    public Task<Void> aab(PendingIntent pendingIntent) {
        throw new SecurityException(String.valueOf(HiHealthStatusCodes.API_FUNCTION_UNAVAILABLE));
    }

    public Task<Void> aab() {
        final HealthKitApiInvoker healthKitApiInvoker = new HealthKitApiInvoker(aaba);
        return aacq.aab(new Callable() { // from class: com.huawei.hms.health.aacl$$ExternalSyntheticLambda3
            @Override // java.util.concurrent.Callable
            public final Object call() {
                Void aab2;
                aab2 = aacl.aab(HealthKitApiInvoker.this);
                return aab2;
            }
        });
    }

    private List<String> aabb(List<DataType> list) {
        ArrayList arrayList = new ArrayList();
        for (DataType dataType : list) {
            if (dataType == null) {
                throw new SecurityException(String.valueOf(HiHealthStatusCodes.INPUT_PARAM_MISSING));
            }
            arrayList.add(dataType.getName());
        }
        return arrayList;
    }

    public static aacl aabb() {
        if (aab == null) {
            synchronized (aacl.class) {
                if (aab == null) {
                    aab = new aacl();
                    aaba = aaci.aabc().aaba();
                }
            }
        }
        return aab;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ List aaba(List list, HealthKitApiInvoker healthKitApiInvoker) throws Exception {
        if (aabz.aab(list).booleanValue()) {
            return null;
        }
        Preconditions.checkState(list.size() <= 20, "the number of DataType must be less than 20");
        List<String> aabb = aabb(list);
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("dataTypes", new JSONArray((Collection) aabb));
            healthKitApiInvoker.setRequestBody(jSONObject.toString());
            healthKitApiInvoker.setInterfaceProvider("DataController");
            healthKitApiInvoker.setInterfaceInvoked("readTodaySummations");
            return (List) aacs.aab(aaci.aabc().aab(healthKitApiInvoker), new aab(this).getType());
        } catch (Exception unused) {
            throw new SecurityException(String.valueOf(HiHealthStatusCodes.INPUT_PARAM_MISSING));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Void aaba(HealthKitApiInvoker healthKitApiInvoker) throws Exception {
        healthKitApiInvoker.setInterfaceProvider("DataController");
        healthKitApiInvoker.setInterfaceInvoked("syncAll");
        healthKitApiInvoker.setRequestBody("");
        aaci.aabc().aab(healthKitApiInvoker);
        return null;
    }

    private void aab(int i, int i2) {
        Preconditions.checkState(i > 20140101, "start time must be later than default start day: 20140101");
        Preconditions.checkState(i <= i2, "the start time must be less than the end time");
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
            simpleDateFormat.setLenient(false);
            Date parse = simpleDateFormat.parse(String.valueOf(i));
            Date parse2 = simpleDateFormat.parse(String.valueOf(i2));
            if (parse == null || parse2 == null) {
                return;
            }
            aacs.aab(parse.getTime(), parse2.getTime(), TimeUnit.MILLISECONDS);
        } catch (ParseException unused) {
            throw new IllegalStateException("startTime or endTime is illegal");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Map aab(List list, HealthKitApiInvoker healthKitApiInvoker) throws Exception {
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(((DataType) it.next()).getName());
        }
        String join = TextUtils.join(",", arrayList);
        healthKitApiInvoker.setInterfaceProvider("DataController");
        healthKitApiInvoker.setInterfaceInvoked("readLatest");
        healthKitApiInvoker.setRequestBody(join);
        List<SamplePoint> aab2 = aacs.aab(aaci.aabc().aab(healthKitApiInvoker), SamplePoint.class);
        HashMap hashMap = new HashMap();
        for (SamplePoint samplePoint : aab2) {
            hashMap.put(samplePoint.getDataType(), samplePoint);
        }
        return hashMap;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ List aab(List list, int i, int i2, HealthKitApiInvoker healthKitApiInvoker) throws Exception {
        if (aabz.aab(list).booleanValue()) {
            return null;
        }
        Preconditions.checkState(list.size() <= 20, "the number of DataType must be less than 20");
        List<String> aabb = aabb(list);
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("dataTypes", new JSONArray((Collection) aabb));
            jSONObject.put(Constants.START_DATE, i);
            jSONObject.put("endDate", i2);
            healthKitApiInvoker.setRequestBody(jSONObject.toString());
            healthKitApiInvoker.setInterfaceProvider("DataController");
            healthKitApiInvoker.setInterfaceInvoked("readDailySummations");
            return (List) aacs.aab(aaci.aabc().aaba(healthKitApiInvoker), new aaba(this).getType());
        } catch (Exception unused) {
            throw new SecurityException(String.valueOf(HiHealthStatusCodes.INPUT_PARAM_MISSING));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Void aab(HealthKitApiInvoker healthKitApiInvoker, UpdateOptions updateOptions) throws Exception {
        healthKitApiInvoker.setInterfaceProvider("DataController");
        healthKitApiInvoker.setInterfaceInvoked("update");
        healthKitApiInvoker.setRequestBody(aacs.aab(updateOptions));
        aaci.aabc().aab(healthKitApiInvoker);
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Void aab(HealthKitApiInvoker healthKitApiInvoker, DeleteOptions deleteOptions) throws Exception {
        healthKitApiInvoker.setInterfaceProvider("DataController");
        healthKitApiInvoker.setInterfaceInvoked("delete");
        healthKitApiInvoker.setRequestBody(aacs.aab(deleteOptions));
        aaci.aabc().aab(healthKitApiInvoker);
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Void aab(HealthKitApiInvoker healthKitApiInvoker) throws Exception {
        healthKitApiInvoker.setInterfaceProvider("DataController");
        healthKitApiInvoker.setInterfaceInvoked("clearAll");
        healthKitApiInvoker.setRequestBody("");
        aaci.aabc().aab(healthKitApiInvoker);
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ ReadDetailResult aab(HealthKitApiInvoker healthKitApiInvoker, ReadOptions readOptions) throws Exception {
        healthKitApiInvoker.setInterfaceProvider("DataController");
        healthKitApiInvoker.setInterfaceInvoked("read");
        healthKitApiInvoker.setRequestBody(aacs.aab(readOptions));
        return (ReadDetailResult) aacs.aab(aaci.aabc().aaba(healthKitApiInvoker), (Type) ReadDetailResult.class);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ SampleSet aab(DataType dataType, HealthKitApiInvoker healthKitApiInvoker) throws Exception {
        if (dataType == null) {
            return null;
        }
        healthKitApiInvoker.setInterfaceProvider("DataController");
        healthKitApiInvoker.setInterfaceInvoked("readTodaySummation");
        healthKitApiInvoker.setRequestBody(dataType.getName());
        return (SampleSet) aacs.aab(aaci.aabc().aab(healthKitApiInvoker), (Type) SampleSet.class);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ SampleSet aab(DataType dataType, DataType dataType2, int i, int i2, HealthKitApiInvoker healthKitApiInvoker) throws Exception {
        String str;
        if (dataType == null) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("dataType", dataType2.getName());
            jSONObject.put(Constants.START_DATE, i);
            jSONObject.put("endDate", i2);
            healthKitApiInvoker.setRequestBody(jSONObject.toString());
        } catch (JSONException unused) {
            str = "JSONObject put value catch JSONException";
            aabz.aab("DataManagerImpl", str);
            healthKitApiInvoker.setInterfaceProvider("DataController");
            healthKitApiInvoker.setInterfaceInvoked("readDailySummation");
            return (SampleSet) aacs.aab(aaci.aabc().aaba(healthKitApiInvoker), (Type) SampleSet.class);
        } catch (Exception unused2) {
            str = "JSONObject put value catch basic exception";
            aabz.aab("DataManagerImpl", str);
            healthKitApiInvoker.setInterfaceProvider("DataController");
            healthKitApiInvoker.setInterfaceInvoked("readDailySummation");
            return (SampleSet) aacs.aab(aaci.aabc().aaba(healthKitApiInvoker), (Type) SampleSet.class);
        }
        healthKitApiInvoker.setInterfaceProvider("DataController");
        healthKitApiInvoker.setInterfaceInvoked("readDailySummation");
        return (SampleSet) aacs.aab(aaci.aabc().aaba(healthKitApiInvoker), (Type) SampleSet.class);
    }

    class aab extends TypeToken<List<SampleSet>> {
        aab(aacl aaclVar) {
        }
    }

    class aaba extends TypeToken<List<SampleSet>> {
        aaba(aacl aaclVar) {
        }
    }
}
