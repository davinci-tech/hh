package defpackage;

import android.content.Context;
import android.util.Log;
import com.huawei.hihealth.HealthKitCommonApi;
import com.huawei.hihealth.HealthKitDictQuery;
import com.huawei.hihealth.HiHealthAggregateQuery;
import com.huawei.hihealth.HiHealthKitApi;
import com.huawei.hihealth.HiHealthKitExtendApi;
import com.huawei.hihealth.StartSportParam;
import com.huawei.hihealth.TrendQuery;
import com.huawei.hihealth.error.HiHealthError;
import com.huawei.hihealth.listener.IuniversalCallback;
import com.huawei.hihealth.listener.ResultCallback;
import com.huawei.hihealthkit.HiHealthDataQuery;
import com.huawei.hihealthkit.HiHealthDataQueryOption;
import com.huawei.hihealthkit.context.OutOfBandContext;
import com.huawei.hihealthkit.data.store.HiRealTimeListener;
import com.huawei.hihealthkit.data.store.HiSportDataCallback;
import com.huawei.hihealthkit.data.type.HiHealthDataType;
import java.util.List;
import java.util.Objects;

/* loaded from: classes4.dex */
public class ieb {
    private static boolean a(Context context, String str, IuniversalCallback iuniversalCallback) {
        Log.i("HiHealthDataStore", "Enter " + str);
        if (iuniversalCallback == null) {
            Log.w("HiHealthDataStore", str + " callback is null");
            return false;
        }
        if (context == null) {
            Log.w("HiHealthDataStore", str + " context is null");
            iuniversalCallback.onResult(2, null, " context is null");
            return false;
        }
        if (ife.a(context, ife.e())) {
            return true;
        }
        Log.w("HiHealthDataStore", str + " App does not exist");
        iuniversalCallback.onResult(1, null, "Health application does not exist");
        return false;
    }

    public static void d(Context context, final ResultCallback resultCallback) {
        if (a(context, "getGender", resultCallback != null ? new IuniversalCallback() { // from class: iev
            @Override // com.huawei.hihealth.listener.IuniversalCallback
            public final void onResult(int i, Object obj, String str) {
                ResultCallback.this.onResultHandler(i, str);
            }
        } : null)) {
            if (context instanceof OutOfBandContext) {
                HiHealthKitExtendApi.d((OutOfBandContext) context).b(resultCallback);
            } else {
                HiHealthKitApi.c(context).d(resultCallback);
            }
        }
    }

    public static void a(Context context, final ResultCallback resultCallback) {
        if (a(context, "getBirthday", resultCallback != null ? new IuniversalCallback() { // from class: iet
            @Override // com.huawei.hihealth.listener.IuniversalCallback
            public final void onResult(int i, Object obj, String str) {
                ResultCallback.this.onResultHandler(i, str);
            }
        } : null)) {
            if (context instanceof OutOfBandContext) {
                HiHealthKitExtendApi.d((OutOfBandContext) context).a(resultCallback);
            } else {
                HiHealthKitApi.c(context).e(resultCallback);
            }
        }
    }

    public static void b(Context context, final ResultCallback resultCallback) {
        if (a(context, "getHeight", resultCallback != null ? new IuniversalCallback() { // from class: iex
            @Override // com.huawei.hihealth.listener.IuniversalCallback
            public final void onResult(int i, Object obj, String str) {
                ResultCallback.this.onResultHandler(i, str);
            }
        } : null)) {
            if (context instanceof OutOfBandContext) {
                HiHealthKitExtendApi.d((OutOfBandContext) context).c(resultCallback);
            } else {
                HiHealthKitApi.c(context).b(resultCallback);
            }
        }
    }

    public static void e(Context context, final ResultCallback resultCallback) {
        if (a(context, "getWeight", resultCallback != null ? new IuniversalCallback() { // from class: iey
            @Override // com.huawei.hihealth.listener.IuniversalCallback
            public final void onResult(int i, Object obj, String str) {
                ResultCallback.this.onResultHandler(i, str);
            }
        } : null)) {
            if (context instanceof OutOfBandContext) {
                HiHealthKitExtendApi.d((OutOfBandContext) context).e(resultCallback);
            } else {
                HiHealthKitApi.c(context).a(resultCallback);
            }
        }
    }

    public static void c(Context context, final ResultCallback resultCallback) {
        if (a(context, "getDeviceList", resultCallback != null ? new IuniversalCallback() { // from class: iee
            @Override // com.huawei.hihealth.listener.IuniversalCallback
            public final void onResult(int i, Object obj, String str) {
                ResultCallback.this.onResultHandler(i, str);
            }
        } : null)) {
            if (context instanceof OutOfBandContext) {
                HiHealthKitExtendApi.d((OutOfBandContext) context).d(resultCallback);
            } else {
                HiHealthKitApi.c(context).c(resultCallback);
            }
        }
    }

    public static void j(Context context, final ResultCallback resultCallback) {
        if (a(context, "startReadingAtrial", resultCallback != null ? new IuniversalCallback() { // from class: ief
            @Override // com.huawei.hihealth.listener.IuniversalCallback
            public final void onResult(int i, Object obj, String str) {
                ResultCallback.this.onResultHandler(i, str);
            }
        } : null)) {
            if (context instanceof OutOfBandContext) {
                HiHealthKitExtendApi.d((OutOfBandContext) context).n(resultCallback);
            } else {
                HiHealthKitApi.c(context).o(resultCallback);
            }
        }
    }

    public static void g(Context context, final ResultCallback resultCallback) {
        if (a(context, "stopReadingAtrial", resultCallback != null ? new IuniversalCallback() { // from class: ieu
            @Override // com.huawei.hihealth.listener.IuniversalCallback
            public final void onResult(int i, Object obj, String str) {
                ResultCallback.this.onResultHandler(i, str);
            }
        } : null)) {
            if (context instanceof OutOfBandContext) {
                HiHealthKitExtendApi.d((OutOfBandContext) context).l(resultCallback);
            } else {
                HiHealthKitApi.c(context).k(resultCallback);
            }
        }
    }

    public static void e(Context context, String str, final ResultCallback resultCallback) {
        if (a(context, "sendDeviceCommand", resultCallback != null ? new IuniversalCallback() { // from class: ieo
            @Override // com.huawei.hihealth.listener.IuniversalCallback
            public final void onResult(int i, Object obj, String str2) {
                ResultCallback.this.onResultHandler(i, str2);
            }
        } : null)) {
            if (str == null) {
                Log.w("HiHealthDataStore", "sendDeviceCommand commandOptions is null");
                resultCallback.onResultHandler(4, "commandOptions is null");
            } else if (context instanceof OutOfBandContext) {
                HiHealthKitExtendApi.d((OutOfBandContext) context).e(str, resultCallback);
            } else {
                HiHealthKitApi.c(context).e(str, resultCallback);
            }
        }
    }

    public static void c(Context context, HiHealthDataQuery hiHealthDataQuery, int i, final ResultCallback resultCallback) {
        if (a(context, "execQuery", resultCallback != null ? new IuniversalCallback() { // from class: ier
            @Override // com.huawei.hihealth.listener.IuniversalCallback
            public final void onResult(int i2, Object obj, String str) {
                ResultCallback.this.onResultHandler(i2, str);
            }
        } : null)) {
            if (hiHealthDataQuery == null) {
                Log.w("HiHealthDataStore", "execQuery hiHealthDataQuery is null");
                resultCallback.onResultHandler(4, "hiHealthDataQuery is null");
                return;
            }
            HiHealthDataQueryOption hiHealthDataQueryOption = hiHealthDataQuery.getHiHealthDataQueryOption();
            if (hiHealthDataQueryOption != null && (hiHealthDataQueryOption.getAggregateType() != null || !hiHealthDataQueryOption.getDeviceUuid().isEmpty())) {
                HiHealthAggregateQuery hiHealthAggregateQuery = new HiHealthAggregateQuery(hiHealthDataQuery);
                if (context instanceof OutOfBandContext) {
                    HiHealthKitExtendApi.d((OutOfBandContext) context).c(hiHealthAggregateQuery, i, resultCallback);
                    return;
                } else {
                    resultCallback.onResultHandler(2, "unsupported query option");
                    return;
                }
            }
            com.huawei.hihealth.HiHealthDataQuery hiHealthDataQuery2 = new com.huawei.hihealth.HiHealthDataQuery(hiHealthDataQuery);
            if (context instanceof OutOfBandContext) {
                HiHealthKitExtendApi.d((OutOfBandContext) context).c(hiHealthDataQuery2, i, resultCallback);
            } else {
                HiHealthKitApi.c(context).d(hiHealthDataQuery2, i, resultCallback);
            }
        }
    }

    public static void e(Context context, HiHealthDataQuery hiHealthDataQuery, int i, final ResultCallback resultCallback) {
        if (a(context, "querySleepWakeTime", resultCallback != null ? new IuniversalCallback() { // from class: iem
            @Override // com.huawei.hihealth.listener.IuniversalCallback
            public final void onResult(int i2, Object obj, String str) {
                ResultCallback.this.onResultHandler(i2, str);
            }
        } : null)) {
            if (hiHealthDataQuery == null) {
                Log.w("HiHealthDataStore", "querySleepWakeTime hiHealthDataQuery is null");
                resultCallback.onResultHandler(4, "querySleepWakeTime hiHealthDataQuery is null");
                return;
            }
            com.huawei.hihealth.HiHealthDataQuery c = c(hiHealthDataQuery);
            if (context instanceof OutOfBandContext) {
                HiHealthKitExtendApi.d((OutOfBandContext) context).b(c, i, resultCallback);
            } else {
                HiHealthKitApi.c(context).b(c, i, resultCallback);
            }
        }
    }

    public static void a(Context context, HiHealthDataQuery hiHealthDataQuery, final ResultCallback resultCallback) {
        if (a(context, "getCount", resultCallback != null ? new IuniversalCallback() { // from class: ieh
            @Override // com.huawei.hihealth.listener.IuniversalCallback
            public final void onResult(int i, Object obj, String str) {
                ResultCallback.this.onResultHandler(i, str);
            }
        } : null)) {
            if (hiHealthDataQuery == null) {
                Log.w("HiHealthDataStore", "getCount hiHealthDataQuery is null");
                resultCallback.onResultHandler(4, "hiHealthDataQuery is null");
                return;
            }
            com.huawei.hihealth.HiHealthDataQuery c = c(hiHealthDataQuery);
            if (context instanceof OutOfBandContext) {
                HiHealthKitExtendApi.d((OutOfBandContext) context).b(c, resultCallback);
            } else {
                HiHealthKitApi.c(context).a(c, resultCallback);
            }
        }
    }

    private static com.huawei.hihealth.HiHealthDataQuery c(HiHealthDataQuery hiHealthDataQuery) {
        HiHealthDataQueryOption hiHealthDataQueryOption = hiHealthDataQuery.getHiHealthDataQueryOption();
        return new com.huawei.hihealth.HiHealthDataQuery(hiHealthDataQuery.getSampleType(), hiHealthDataQuery.getStartTime(), hiHealthDataQuery.getEndTime(), hiHealthDataQueryOption != null ? new com.huawei.hihealth.HiHealthDataQueryOption(hiHealthDataQueryOption.getLimit(), hiHealthDataQueryOption.getOffset(), hiHealthDataQueryOption.getOrder()) : null);
    }

    public static void b(Context context, idy idyVar, final ResultCallback resultCallback) {
        if (a(context, "saveSample", resultCallback != null ? new IuniversalCallback() { // from class: iej
            @Override // com.huawei.hihealth.listener.IuniversalCallback
            public final void onResult(int i, Object obj, String str) {
                ResultCallback.this.onResultHandler(i, str);
            }
        } : null)) {
            if (idyVar == null) {
                Log.w("HiHealthDataStore", "saveSample hiHealthDataQuery is null");
                resultCallback.onResultHandler(4, "hiHealthData is null");
            } else if (context instanceof OutOfBandContext) {
                HiHealthKitExtendApi.d((OutOfBandContext) context).c(idyVar, resultCallback);
            } else {
                HiHealthKitApi.c(context).b(idyVar, resultCallback);
            }
        }
    }

    public static void d(Context context, List<idy> list, final ResultCallback resultCallback) {
        if (a(context, "saveSamples", resultCallback != null ? new IuniversalCallback() { // from class: ied
            @Override // com.huawei.hihealth.listener.IuniversalCallback
            public final void onResult(int i, Object obj, String str) {
                ResultCallback.this.onResultHandler(i, str);
            }
        } : null)) {
            if (list == null || list.isEmpty()) {
                Log.w("HiHealthDataStore", "saveSamples hiHealthDataList is null or empty");
                resultCallback.onResultHandler(4, "hiHealthDataList is null or empty");
            } else if (context instanceof OutOfBandContext) {
                HiHealthKitExtendApi.d((OutOfBandContext) context).b(list, resultCallback);
            } else {
                HiHealthKitApi.c(context).c(list, resultCallback);
            }
        }
    }

    public static void b(Context context, List<idy> list, final ResultCallback resultCallback) {
        if (a(context, "deleteSamples", resultCallback != null ? new IuniversalCallback() { // from class: iez
            @Override // com.huawei.hihealth.listener.IuniversalCallback
            public final void onResult(int i, Object obj, String str) {
                ResultCallback.this.onResultHandler(i, str);
            }
        } : null)) {
            if (list == null || list.isEmpty()) {
                Log.w("HiHealthDataStore", "deleteSamples hiHealthDataList is null or empty");
                resultCallback.onResultHandler(4, "hiHealthDataList is null or empty");
            } else if (context instanceof OutOfBandContext) {
                HiHealthKitExtendApi.d((OutOfBandContext) context).e(list, resultCallback);
            } else {
                HiHealthKitApi.c(context).b(list, resultCallback);
            }
        }
    }

    public static void c(Context context, idy idyVar, final ResultCallback resultCallback) {
        if (a(context, "saveSamples", resultCallback != null ? new IuniversalCallback() { // from class: iep
            @Override // com.huawei.hihealth.listener.IuniversalCallback
            public final void onResult(int i, Object obj, String str) {
                ResultCallback.this.onResultHandler(i, str);
            }
        } : null)) {
            if (idyVar == null) {
                Log.w("HiHealthDataStore", "deleteSample hiHealthData is null or empty");
                resultCallback.onResultHandler(4, "hiHealthData is null or empty");
            } else if (context instanceof OutOfBandContext) {
                HiHealthKitExtendApi.d((OutOfBandContext) context).b(idyVar, resultCallback);
            } else {
                HiHealthKitApi.c(context).a(idyVar, resultCallback);
            }
        }
    }

    public static void c(Context context, final HiRealTimeListener hiRealTimeListener) {
        if (a(context, "startReadingHeartRate", hiRealTimeListener != null ? new IuniversalCallback() { // from class: iec
            @Override // com.huawei.hihealth.listener.IuniversalCallback
            public final void onResult(int i, Object obj, String str) {
                HiRealTimeListener.this.onResultHandler(i);
            }
        } : null)) {
            if (context instanceof OutOfBandContext) {
                HiHealthKitExtendApi.d((OutOfBandContext) context).b(hiRealTimeListener);
            } else {
                HiHealthKitApi.c(context).e(hiRealTimeListener);
            }
        }
    }

    public static void a(Context context, final HiRealTimeListener hiRealTimeListener) {
        if (a(context, "stopReadingHeartRate", hiRealTimeListener != null ? new IuniversalCallback() { // from class: ies
            @Override // com.huawei.hihealth.listener.IuniversalCallback
            public final void onResult(int i, Object obj, String str) {
                HiRealTimeListener.this.onResultHandler(i);
            }
        } : null)) {
            if (context instanceof OutOfBandContext) {
                HiHealthKitExtendApi.d((OutOfBandContext) context).j(hiRealTimeListener);
            } else {
                HiHealthKitApi.c(context).h(hiRealTimeListener);
            }
        }
    }

    public static void d(Context context, final HiRealTimeListener hiRealTimeListener) {
        if (a(context, "startReadingRri", hiRealTimeListener != null ? new IuniversalCallback() { // from class: iek
            @Override // com.huawei.hihealth.listener.IuniversalCallback
            public final void onResult(int i, Object obj, String str) {
                HiRealTimeListener.this.onResultHandler(i);
            }
        } : null)) {
            if (context instanceof OutOfBandContext) {
                HiHealthKitExtendApi.d((OutOfBandContext) context).i(hiRealTimeListener);
            } else {
                HiHealthKitApi.c(context).i(hiRealTimeListener);
            }
        }
    }

    public static void e(Context context, final HiRealTimeListener hiRealTimeListener) {
        if (a(context, "stopReadingRri", hiRealTimeListener != null ? new IuniversalCallback() { // from class: ieq
            @Override // com.huawei.hihealth.listener.IuniversalCallback
            public final void onResult(int i, Object obj, String str) {
                HiRealTimeListener.this.onResultHandler(i);
            }
        } : null)) {
            if (context instanceof OutOfBandContext) {
                HiHealthKitExtendApi.d((OutOfBandContext) context).g(hiRealTimeListener);
            } else {
                HiHealthKitApi.c(context).g(hiRealTimeListener);
            }
        }
    }

    private static void d(Context context, final HiSportDataCallback hiSportDataCallback) {
        if (a(context, "startRealTimeSportData", hiSportDataCallback != null ? new IuniversalCallback() { // from class: ien
            @Override // com.huawei.hihealth.listener.IuniversalCallback
            public final void onResult(int i, Object obj, String str) {
                HiSportDataCallback.this.onResultHandler(i);
            }
        } : null)) {
            if (context instanceof OutOfBandContext) {
                HiHealthKitExtendApi.d((OutOfBandContext) context).a(hiSportDataCallback);
            } else {
                HiHealthKitApi.c(context).a(hiSportDataCallback);
            }
        }
    }

    public static void c(Context context, HiSportDataCallback hiSportDataCallback) {
        d(context, hiSportDataCallback);
    }

    private static void b(Context context, final HiSportDataCallback hiSportDataCallback) {
        if (a(context, "stopRealTimeSportData", hiSportDataCallback != null ? new IuniversalCallback() { // from class: iei
            @Override // com.huawei.hihealth.listener.IuniversalCallback
            public final void onResult(int i, Object obj, String str) {
                HiSportDataCallback.this.onResultHandler(i);
            }
        } : null)) {
            if (context instanceof OutOfBandContext) {
                HiHealthKitExtendApi.d((OutOfBandContext) context).d(hiSportDataCallback);
            } else {
                HiHealthKitApi.c(context).e(hiSportDataCallback);
            }
        }
    }

    public static void a(Context context, HiSportDataCallback hiSportDataCallback) {
        b(context, hiSportDataCallback);
    }

    public static void c(Context context, StartSportParam startSportParam, final ResultCallback resultCallback) {
        if (a(context, "startSportEx", resultCallback != null ? new IuniversalCallback() { // from class: iew
            @Override // com.huawei.hihealth.listener.IuniversalCallback
            public final void onResult(int i, Object obj, String str) {
                ResultCallback.this.onResultHandler(i, str);
            }
        } : null)) {
            HealthKitCommonApi.d(context).c(startSportParam, resultCallback);
        }
    }

    public static void h(Context context, final ResultCallback resultCallback) {
        if (a(context, "stopSport", resultCallback != null ? new IuniversalCallback() { // from class: ifb
            @Override // com.huawei.hihealth.listener.IuniversalCallback
            public final void onResult(int i, Object obj, String str) {
                ResultCallback.this.onResultHandler(i, str);
            }
        } : null)) {
            HealthKitCommonApi.d(context).d(resultCallback);
        }
    }

    public static void d(Context context, HealthKitDictQuery healthKitDictQuery, IuniversalCallback iuniversalCallback) {
        if (a(context, "queryData", iuniversalCallback != null ? iuniversalCallback : null)) {
            if (healthKitDictQuery == null) {
                Log.w("HiHealthDataStore", "queryData healthKitDictQuery is null");
                iuniversalCallback.onResultHandler(2, null, HiHealthError.e(2));
            } else if (context instanceof OutOfBandContext) {
                HiHealthKitExtendApi.d((OutOfBandContext) context).e(healthKitDictQuery, iuniversalCallback);
            } else {
                HiHealthKitApi.c(context).e(healthKitDictQuery, iuniversalCallback);
            }
        }
    }

    public static HiHealthDataType.Category d(Context context, int i) {
        Log.i("HiHealthDataStore", "enter getCategory");
        if (context == null) {
            Log.w("HiHealthDataStore", "saveSample context is null");
            return HiHealthDataType.Category.UNKNOWN;
        }
        if (!ife.a(context, ife.e())) {
            Log.w("HiHealthDataStore", "Health application does not exist");
            return HiHealthDataType.Category.UNKNOWN;
        }
        if (context instanceof OutOfBandContext) {
            return HiHealthKitExtendApi.d((OutOfBandContext) context).b(i);
        }
        return HiHealthKitApi.c(context).d(i);
    }

    public static void d(Context context, int[] iArr, final ResultCallback resultCallback) {
        if (a(context, "syncData", resultCallback != null ? new IuniversalCallback() { // from class: iel
            @Override // com.huawei.hihealth.listener.IuniversalCallback
            public final void onResult(int i, Object obj, String str) {
                ResultCallback.this.onResultHandler(i, str);
            }
        } : null)) {
            HealthKitCommonApi.d(context).c(iArr, resultCallback);
        }
    }

    public static void b(Context context, TrendQuery trendQuery, int i, final IuniversalCallback iuniversalCallback) {
        IuniversalCallback iuniversalCallback2;
        if (iuniversalCallback != null) {
            Objects.requireNonNull(iuniversalCallback);
            iuniversalCallback2 = new IuniversalCallback() { // from class: ieg
                @Override // com.huawei.hihealth.listener.IuniversalCallback
                public final void onResult(int i2, Object obj, String str) {
                    IuniversalCallback.this.onResultHandler(i2, obj, str);
                }
            };
        } else {
            iuniversalCallback2 = null;
        }
        if (a(context, "queryTrends", iuniversalCallback2)) {
            HealthKitCommonApi.d(context).d(trendQuery, i, iuniversalCallback);
        }
    }
}
