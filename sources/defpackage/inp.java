package defpackage;

import android.content.Context;
import android.os.RemoteException;
import android.text.TextUtils;
import com.huawei.hihealth.HealthKitDictQuery;
import com.huawei.hihealth.HiAggregateOption;
import com.huawei.hihealth.HiDataAggregateProOption;
import com.huawei.hihealth.HiDataFilter;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiDataReadProOption;
import com.huawei.hihealth.HiDeviceInfo;
import com.huawei.hihealth.HiHealthClient;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.HiHealthDataQueryOption;
import com.huawei.hihealth.HiHealthKitData;
import com.huawei.hihealth.HiMetadataFilter;
import com.huawei.hihealth.HiSportStatDataAggregateOption;
import com.huawei.hihealth.HiTimeInterval;
import com.huawei.hihealth.IAggregateListener;
import com.huawei.hihealth.IAggregateListenerEx;
import com.huawei.hihealth.IDataReadResultListener;
import com.huawei.hihealth.IMultiDataClientListener;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.constant.HiHealthDataKey;
import com.huawei.hihealth.data.listener.HiAggregateListener;
import com.huawei.hihealth.data.type.HiHealthDataType;
import com.huawei.hihealth.dictionary.HiHealthDictManager;
import com.huawei.hihealth.dictionary.model.HiHealthDictField;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.nfc.PluginPayAdapter;
import com.huawei.operation.ble.BleConstants;
import health.compact.a.HiDateUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes7.dex */
public class inp {
    private String b;
    private String c;
    private Context d;
    private irc e;

    public inp(Context context, String str, irc ircVar) {
        this(context, str, ircVar, null);
    }

    inp(Context context, String str, irc ircVar, String str2) {
        this.d = context.getApplicationContext();
        this.c = str;
        this.b = str2;
        this.e = ircVar;
    }

    public void b(HealthKitDictQuery healthKitDictQuery, IDataReadResultListener iDataReadResultListener, Cint cint) throws RemoteException {
        int sampleType = healthKitDictQuery.getSampleType();
        if (sampleType != 10001 && HiHealthDataType.e(sampleType) == HiHealthDataType.Category.SET) {
            a(healthKitDictQuery, iDataReadResultListener, cint);
        } else {
            f(healthKitDictQuery, iDataReadResultListener, cint);
        }
    }

    public void d(HealthKitDictQuery healthKitDictQuery, IDataReadResultListener iDataReadResultListener, Cint cint) throws RemoteException {
        LogUtil.a("HiHealthKitQueryDicHelper", "enter aggregate query");
        if (healthKitDictQuery.getBoolean("isSportType").booleanValue()) {
            c(healthKitDictQuery, iDataReadResultListener, cint);
        } else {
            a(healthKitDictQuery, iDataReadResultListener, cint);
        }
    }

    public void e(final HealthKitDictQuery healthKitDictQuery, final IDataReadResultListener iDataReadResultListener, final Cint cint) throws RemoteException {
        HiTimeInterval hiTimeInterval = new HiTimeInterval(healthKitDictQuery.getStartTime(), healthKitDictQuery.getEndTime());
        ify.e().c(a(healthKitDictQuery.getSampleType()), hiTimeInterval, true, new IMultiDataClientListener.Stub() { // from class: inp.1
            @Override // com.huawei.hihealth.IMultiDataClientListener
            public void onMultiTypeResult(Map map) {
            }

            @Override // com.huawei.hihealth.IMultiDataClientListener
            public void onMergeTypeResult(List list) {
                ReleaseLogUtil.e("HiHealthKitQueryDicHelper", "getDataSource onResult success.");
                inp.this.b(inp.this.c(list, healthKitDictQuery, cint), 0, 2, iDataReadResultListener);
                inp inpVar = inp.this;
                inpVar.c(inpVar.e, cint.b(), 0, cint.d());
            }
        });
    }

    private void c(HealthKitDictQuery healthKitDictQuery, final IDataReadResultListener iDataReadResultListener, final Cint cint) throws RemoteException {
        ReleaseLogUtil.e("HiHealthKitQueryDicHelper", "enter getAggregateSportStatData");
        HiHealthNativeApi.a(this.d).aggregateSportStatData(d(healthKitDictQuery), new HiAggregateListener() { // from class: inp.5
            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResultIntent(int i, List<HiHealthData> list, int i2, int i3) {
            }

            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResult(List list, int i, int i2) {
                ReleaseLogUtil.e("HiHealthKitQueryDicHelper", "aggregateSportStatData onResult: errorCode = ", Integer.valueOf(i), ", resultType = ", Integer.valueOf(i2));
                int b = iox.b(i);
                inp.this.b(inp.this.b(list, cint), b, i2, iDataReadResultListener);
                if (i2 == 2) {
                    inp inpVar = inp.this;
                    inpVar.c(inpVar.e, cint.b(), b, cint.d());
                }
            }
        });
    }

    private HiSportStatDataAggregateOption d(HealthKitDictQuery healthKitDictQuery) {
        int sampleType = healthKitDictQuery.getSampleType() - 30000;
        int i = healthKitDictQuery.getInt("timeUnit");
        long startTime = healthKitDictQuery.getStartTime();
        long endTime = healthKitDictQuery.getEndTime();
        int order = healthKitDictQuery.getHiHealthDataQueryOption().getOrder();
        LogUtil.a("HiHealthKitQueryDicHelper", "requestTrackMonthData sportType = ", Integer.valueOf(sampleType), "timeUnit:", Integer.valueOf(i));
        HiSportStatDataAggregateOption hiSportStatDataAggregateOption = new HiSportStatDataAggregateOption();
        hiSportStatDataAggregateOption.setSortOrder(order);
        hiSportStatDataAggregateOption.setStartTime(startTime);
        hiSportStatDataAggregateOption.setEndTime(endTime);
        hiSportStatDataAggregateOption.setAggregateType(1);
        hiSportStatDataAggregateOption.setHiHealthTypes(new int[]{sampleType});
        int[] sportTypeMap = iqj.getSportTypeMap(sampleType);
        String[] sportTypeQueryKeys = iqj.getSportTypeQueryKeys(sampleType);
        LogUtil.a("HiHealthKitQueryDicHelper", "types", sportTypeMap);
        hiSportStatDataAggregateOption.setConstantsKey(sportTypeQueryKeys);
        hiSportStatDataAggregateOption.setType(sportTypeMap);
        hiSportStatDataAggregateOption.setGroupUnitType(i);
        hiSportStatDataAggregateOption.setReadType(0);
        return hiSportStatDataAggregateOption;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<HiHealthKitData> b(List list, Cint cint) {
        if (list == null) {
            ReleaseLogUtil.d("HiHealthKitQueryDicHelper", "dealAggregateData: hiHealthDataList is null");
            return null;
        }
        ArrayList arrayList = new ArrayList(list.size());
        Iterator it = list.iterator();
        while (it.hasNext()) {
            HiHealthData hiHealthData = (HiHealthData) it.next();
            HiHealthKitData hiHealthKitData = new HiHealthKitData();
            hiHealthKitData.setType(hiHealthData.getType());
            hiHealthKitData.setContentValues(hiHealthData.getValueHolder());
            arrayList.add(hiHealthKitData);
        }
        return arrayList;
    }

    private void f(HealthKitDictQuery healthKitDictQuery, IDataReadResultListener iDataReadResultListener, Cint cint) throws RemoteException {
        ReleaseLogUtil.e("HiHealthKitQueryDicHelper", "enter readHiHealthDataPro");
        HiDataReadOption b = b(healthKitDictQuery);
        IDataReadResultListener c = c(cint, iDataReadResultListener);
        if (healthKitDictQuery.getBoolean("isSportType").booleanValue()) {
            ify.e().c(b, c, false);
            return;
        }
        ify.e().e(new HiDataReadProOption.Builder().e(b).a(healthKitDictQuery.getString("package_name")).b(healthKitDictQuery.getInt("sequence_data_type")).b(c(healthKitDictQuery)).e(), c, false);
    }

    private String c(HealthKitDictQuery healthKitDictQuery) {
        if (HiHealthDataType.e(healthKitDictQuery.getSampleType()) != HiHealthDataType.Category.SEQUENCE) {
            ReleaseLogUtil.d("HiHealthKitQueryDicHelper", "unsupported filter condition");
            return "";
        }
        ArrayList<String> filterKeys = healthKitDictQuery.getFilterKeys();
        ArrayList<String> filterValues = healthKitDictQuery.getFilterValues();
        if (filterKeys == null || filterKeys.isEmpty()) {
            return "";
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < filterKeys.size(); i++) {
            arrayList.add(new HiMetadataFilter.MetadataFilterExpression(filterKeys.get(i), filterValues.get(i), false));
        }
        return HiJsonUtil.e(new HiMetadataFilter(arrayList));
    }

    private IDataReadResultListener c(final Cint cint, final IDataReadResultListener iDataReadResultListener) {
        return new IDataReadResultListener.Stub() { // from class: inp.2
            @Override // com.huawei.hihealth.IDataReadResultListener
            public void onResult(List list, int i, int i2) throws RemoteException {
                ReleaseLogUtil.e("HiHealthKitQueryDicHelper", "getDataReadResultListener onResult: errorCode = ", Integer.valueOf(i), ", resultType = ", Integer.valueOf(i2));
                int b = iox.b(i);
                inp.this.b(inp.this.b(list, cint, (List<Integer>) null), b, i2, iDataReadResultListener);
                if (i2 == 2) {
                    inp inpVar = inp.this;
                    inpVar.c(inpVar.e, cint.b(), b, cint.d());
                }
            }
        };
    }

    private void a(final HealthKitDictQuery healthKitDictQuery, final IDataReadResultListener iDataReadResultListener, final Cint cint) throws RemoteException {
        ReleaseLogUtil.e("HiHealthKitQueryDicHelper", "enter aggregateHiHealthDataPro");
        List<HiDataAggregateProOption> arrayList = new ArrayList<>();
        if (g(healthKitDictQuery)) {
            arrayList = e(healthKitDictQuery);
        } else {
            arrayList.add(a(healthKitDictQuery));
        }
        ify.e().b(arrayList, (IAggregateListenerEx) new IAggregateListenerEx.Stub() { // from class: inp.3
            @Override // com.huawei.hihealth.IAggregateListenerEx
            public void onResult(List list, int i, int i2) {
                ReleaseLogUtil.e("HiHealthKitQueryDicHelper", "onResult: errorCode = ", Integer.valueOf(i), ", resultType = ", Integer.valueOf(i2));
                int b = iox.b(i);
                List b2 = inp.this.b(list, cint, healthKitDictQuery.getSubTypes());
                if (healthKitDictQuery.getSampleType() == 10007) {
                    try {
                        inp.this.d((List<HiHealthKitData>) b2, cint, healthKitDictQuery);
                    } catch (RemoteException e) {
                        ReleaseLogUtil.c("HiH_HiHealthKitQueryDicHelper", "query manual or phone monitor sleep data RemoteException: ", e.getMessage());
                    }
                }
                inp.this.b(b2, b, i2, iDataReadResultListener);
                if (i2 == 2) {
                    inp inpVar = inp.this;
                    inpVar.c(inpVar.e, cint.b(), b, cint.d());
                }
            }
        }, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<HiHealthKitData> b(List list, Cint cint, List<Integer> list2) {
        if (list == null) {
            ReleaseLogUtil.d("HiHealthKitQueryDicHelper", "dealHiHealthKitData: hiHealthDataList is null");
            return null;
        }
        LogUtil.a("HiHealthKitQueryDicHelper", "data size is ", Integer.valueOf(list.size()));
        ArrayList arrayList = new ArrayList(list.size());
        Iterator it = list.iterator();
        while (it.hasNext()) {
            HiHealthData hiHealthData = (HiHealthData) it.next();
            HiHealthKitData hiHealthKitData = new HiHealthKitData();
            d(hiHealthData, hiHealthKitData, cint.e());
            hiHealthKitData.setType(hiHealthData.getType());
            hiHealthKitData.setTimeInterval(hiHealthData.getStartTime(), hiHealthData.getEndTime());
            hiHealthKitData.putLong("update_time", hiHealthData.getModifiedTime());
            c(hiHealthData, hiHealthKitData, cint, list2);
            arrayList.add(hiHealthKitData);
        }
        return arrayList;
    }

    private void c(HiHealthData hiHealthData, HiHealthKitData hiHealthKitData, Cint cint, List<Integer> list) {
        int i;
        try {
            i = Integer.parseInt(cint.d());
        } catch (NumberFormatException e) {
            ReleaseLogUtil.c("HiH_HiHealthKitQueryDicHelper", e.getMessage());
            i = 0;
        }
        int i2 = AnonymousClass7.c[HiHealthDataType.e(i).ordinal()];
        if (i2 == 1) {
            e(hiHealthData, hiHealthKitData);
            return;
        }
        if (i2 == 2) {
            a(hiHealthData, hiHealthKitData);
        } else if (i2 == 3) {
            a(hiHealthData, hiHealthKitData, i, list);
        } else {
            ReleaseLogUtil.e("HiHealthKitQueryDicHelper", "dealHiHealthKitData: query type not support");
        }
    }

    /* renamed from: inp$7, reason: invalid class name */
    static /* synthetic */ class AnonymousClass7 {
        static final /* synthetic */ int[] c;

        static {
            int[] iArr = new int[HiHealthDataType.Category.values().length];
            c = iArr;
            try {
                iArr[HiHealthDataType.Category.SEQUENCE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                c[HiHealthDataType.Category.POINT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                c[HiHealthDataType.Category.SET.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    private void e(HiHealthData hiHealthData, HiHealthKitData hiHealthKitData) {
        hiHealthKitData.putString("meta_data", hiHealthData.getMetaData());
        hiHealthKitData.putString("detail_data", hiHealthData.getSequenceData());
        hiHealthKitData.putString("simple_data", hiHealthData.getSimpleData());
        hiHealthKitData.putInt("deviceType", hiHealthData.getInt("trackdata_deviceType"));
        hiHealthKitData.putString("data_id", String.valueOf(hiHealthData.getDataId()));
        String string = hiHealthData.getString("interpret_data");
        if (TextUtils.isEmpty(string)) {
            return;
        }
        hiHealthKitData.putString("interpret_data", string);
    }

    private void a(HiHealthData hiHealthData, HiHealthKitData hiHealthKitData) {
        HiHealthDictField b = HiHealthDictManager.d(this.d).b(hiHealthData.getType());
        if (b == null) {
            ReleaseLogUtil.d("HiHealthKitQueryDicHelper", "HiHealthDictField is null, type is ", Integer.valueOf(hiHealthData.getType()));
            return;
        }
        String e = b.e();
        if ("Integer".equals(e)) {
            hiHealthKitData.putInt(String.valueOf(hiHealthData.getType()), hiHealthData.getIntValue());
            return;
        }
        if ("Double".equals(e)) {
            hiHealthKitData.putDouble(String.valueOf(hiHealthData.getType()), hiHealthData.getValue());
        } else if ("Float".equals(e)) {
            hiHealthKitData.putFloat(String.valueOf(hiHealthData.getType()), hiHealthData.getFloatValue());
        } else {
            hiHealthKitData.putString("metadata", hiHealthData.getMetaData());
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void a(HiHealthData hiHealthData, HiHealthKitData hiHealthKitData, int i, List<Integer> list) {
        char c;
        if (!HiHealthDictManager.d(this.d).l(i)) {
            b(hiHealthData, hiHealthKitData, i);
            return;
        }
        List<Integer> g = HiHealthDictManager.d(this.d).g(i);
        if (list != null && !list.isEmpty()) {
            g.retainAll(list);
        }
        a(hiHealthData);
        Iterator<Integer> it = g.iterator();
        while (it.hasNext()) {
            HiHealthDictField b = HiHealthDictManager.d(this.d).b(it.next().intValue());
            if (b != null) {
                String e = b.e();
                String a2 = b.a();
                int c2 = b.c();
                if (hiHealthData.get(a2) != null) {
                    e.hashCode();
                    switch (e.hashCode()) {
                        case -672261858:
                            if (e.equals("Integer")) {
                                c = 0;
                                break;
                            }
                            c = 65535;
                            break;
                        case 2374300:
                            if (e.equals("Long")) {
                                c = 1;
                                break;
                            }
                            c = 65535;
                            break;
                        case 67973692:
                            if (e.equals("Float")) {
                                c = 2;
                                break;
                            }
                            c = 65535;
                            break;
                        case 2052876273:
                            if (e.equals("Double")) {
                                c = 3;
                                break;
                            }
                            c = 65535;
                            break;
                        default:
                            c = 65535;
                            break;
                    }
                    if (c == 0) {
                        hiHealthKitData.putInt(String.valueOf(c2), hiHealthData.getInt(a2));
                    } else if (c == 1) {
                        hiHealthKitData.putLong(String.valueOf(c2), hiHealthData.getLong(a2));
                    } else if (c == 2) {
                        hiHealthKitData.putFloat(String.valueOf(c2), hiHealthData.getFloat(a2));
                    } else if (c == 3) {
                        hiHealthKitData.putDouble(String.valueOf(c2), hiHealthData.getDouble(a2));
                    } else {
                        hiHealthKitData.putString("metadata", hiHealthData.getMetaData());
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(List<HiHealthKitData> list, Cint cint, HealthKitDictQuery healthKitDictQuery) throws RemoteException {
        if (list == null) {
            return;
        }
        for (final HiHealthKitData hiHealthKitData : list) {
            int i = hiHealthKitData.getInt(String.valueOf(44110));
            if (i == Integer.parseInt("001", 16)) {
                final HealthKitDictQuery healthKitDictQuery2 = new HealthKitDictQuery(DicDataTypeUtil.DataType.SLEEP_ON_OFF_BED_RECORD.value(), HiDateUtil.l(hiHealthKitData.getStartTime()), HiDateUtil.o(hiHealthKitData.getEndTime()));
                healthKitDictQuery2.putSubTypes(healthKitDictQuery.getSubTypes());
                healthKitDictQuery2.setValueFilterType(DicDataTypeUtil.DataType.SLEEP_ON_OFF_BED_RECORD_BED_TIME.value());
                final Cint cint2 = new Cint(cint.e(), cint.b(), cint.c(), String.valueOf(DicDataTypeUtil.DataType.SLEEP_ON_OFF_BED_RECORD.value()));
                ify.e().b(a(healthKitDictQuery2), (IAggregateListener) new IAggregateListener.Stub() { // from class: inp.4
                    @Override // com.huawei.hihealth.IAggregateListener
                    public void onResult(List list2, int i2, int i3) {
                        LogUtil.a("HiHealthKitQueryDicHelper", "onResult: errorCode = ", Integer.valueOf(i2), ", resultType = ", Integer.valueOf(i3));
                        inp.this.a(hiHealthKitData, (List<HiHealthData>) list2, cint2, healthKitDictQuery2.getSubTypes());
                    }
                }, false);
            } else if (i == Integer.parseInt("011", 16) || i == Integer.parseInt("00E", 16)) {
                final HealthKitDictQuery healthKitDictQuery3 = new HealthKitDictQuery(DicDataTypeUtil.DataType.SLEEP_RECORD.value(), HiDateUtil.l(hiHealthKitData.getStartTime()), HiDateUtil.o(hiHealthKitData.getEndTime()));
                healthKitDictQuery3.putSubTypes(healthKitDictQuery.getSubTypes());
                healthKitDictQuery3.setValueFilterType(DicDataTypeUtil.DataType.BED_TIME.value());
                HiDataAggregateProOption a2 = a(healthKitDictQuery3);
                final Cint cint3 = new Cint(cint.e(), cint.b(), cint.c(), String.valueOf(DicDataTypeUtil.DataType.SLEEP_RECORD.value()));
                ify.e().b(a2, (IAggregateListener) new IAggregateListener.Stub() { // from class: inp.8
                    @Override // com.huawei.hihealth.IAggregateListener
                    public void onResult(List list2, int i2, int i3) {
                        LogUtil.a("HiHealthKitQueryDicHelper", "onResult: errorCode = ", Integer.valueOf(i2), ", resultType = ", Integer.valueOf(i3));
                        inp.this.a(hiHealthKitData, (List<HiHealthData>) list2, cint3, healthKitDictQuery3.getSubTypes());
                    }
                }, false);
            } else {
                LogUtil.a("HiHealthKitQueryDicHelper", "nothing to add");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(HiHealthKitData hiHealthKitData, List<HiHealthData> list, Cint cint, ArrayList<Integer> arrayList) {
        if (list == null || list.isEmpty()) {
            return;
        }
        HiHealthKitData hiHealthKitData2 = b(list, cint, arrayList).get(r4.size() - 1);
        hiHealthKitData2.setTimeInterval(hiHealthKitData.getStartTime(), hiHealthKitData.getEndTime());
        hiHealthKitData.getContentValue().putAll(hiHealthKitData2.getContentValue());
    }

    private void a(HiHealthData hiHealthData) {
        hiHealthData.putDouble("protein", ira.d(hiHealthData));
        ira.bBZ_(hiHealthData.getValueHolder(), BleConstants.BODY_FAT_RATE, "bodyFat");
        ira.bBZ_(hiHealthData.getValueHolder(), "protein", BleConstants.PROTEIN_RATE);
    }

    private void b(HiHealthData hiHealthData, HiHealthKitData hiHealthKitData, int i) {
        hiHealthKitData.putString("metadata", hiHealthData.getMetaData());
        if (i == 10001) {
            hiHealthKitData.putDouble(String.valueOf(hiHealthData.getType()), hiHealthData.getValue());
            return;
        }
        int i2 = 0;
        if (i == 10007) {
            int[] a2 = HiHealthDataType.a();
            String[] b = HiHealthDataKey.b();
            int i3 = 0;
            while (i2 < b.length) {
                if (hiHealthData.containsKey(b[i2]) && i3 < a2.length) {
                    hiHealthKitData.putDouble(String.valueOf(a2[i3]), hiHealthData.getDouble(b[i2]));
                }
                i2++;
                i3++;
            }
            return;
        }
        int[] d = HiHealthDataType.d(i);
        int length = d.length;
        while (i2 < length) {
            int i4 = d[i2];
            hiHealthKitData.putDouble(String.valueOf(i4), hiHealthData.getDouble(HiHealthDataKey.c(i4)));
            i2++;
        }
    }

    private void d(HiHealthData hiHealthData, HiHealthKitData hiHealthKitData, boolean z) {
        String string = hiHealthData.getString("device_uniquecode");
        if (TextUtils.isEmpty(string)) {
            return;
        }
        String string2 = hiHealthData.getString(PluginPayAdapter.KEY_DEVICE_INFO_NAME);
        String string3 = hiHealthData.getString(PluginPayAdapter.KEY_DEVICE_INFO_MODEL);
        if (!z) {
            hiHealthKitData.putString("device_uniquecode", "***");
        } else {
            hiHealthKitData.putString("device_uniquecode", string);
        }
        hiHealthKitData.putString(PluginPayAdapter.KEY_DEVICE_INFO_NAME, string2);
        hiHealthKitData.putString(PluginPayAdapter.KEY_DEVICE_INFO_MODEL, string3);
        hiHealthKitData.putString("trackdata_deviceType", hiHealthData.getString("trackdata_deviceType"));
    }

    private void b(HiDataReadOption hiDataReadOption, HealthKitDictQuery healthKitDictQuery) {
        String string = healthKitDictQuery.getString("device_uniquecode");
        String string2 = healthKitDictQuery.getString("package_name");
        if (!TextUtils.isEmpty(string) && !TextUtils.isEmpty(string2)) {
            hiDataReadOption.setReadType(3);
        } else if (!TextUtils.isEmpty(string)) {
            hiDataReadOption.setReadType(2);
        } else if (!TextUtils.isEmpty(string2)) {
            hiDataReadOption.setReadType(1);
        } else {
            hiDataReadOption.setReadType(0);
        }
        hiDataReadOption.setDeviceUuid(string);
    }

    private HiDataReadOption b(HealthKitDictQuery healthKitDictQuery) {
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        if (healthKitDictQuery.getBoolean("isSportType").booleanValue()) {
            healthKitDictQuery.setSampleType(healthKitDictQuery.getSampleType() - 30000);
        }
        hiDataReadOption.setType(new int[]{healthKitDictQuery.getSampleType()});
        hiDataReadOption.setStartTime(healthKitDictQuery.getStartTime());
        hiDataReadOption.setEndTime(healthKitDictQuery.getEndTime());
        HiHealthDataQueryOption hiHealthDataQueryOption = healthKitDictQuery.getHiHealthDataQueryOption();
        if (hiHealthDataQueryOption != null) {
            hiDataReadOption.setSortOrder(hiHealthDataQueryOption.getOrder());
            hiDataReadOption.setAnchor(hiHealthDataQueryOption.getOffset());
            hiDataReadOption.setCount(hiHealthDataQueryOption.getLimit());
        }
        b(hiDataReadOption, healthKitDictQuery);
        return hiDataReadOption;
    }

    private HiDataAggregateProOption a(HealthKitDictQuery healthKitDictQuery) {
        String str;
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        int sampleType = healthKitDictQuery.getSampleType();
        if (sampleType == 10002 || sampleType == 10007) {
            b(sampleType, hiAggregateOption);
        } else if (HiHealthDictManager.d(this.d).l(sampleType)) {
            b(hiAggregateOption, sampleType, healthKitDictQuery.getSubTypes());
        } else {
            c(hiAggregateOption, sampleType);
        }
        hiAggregateOption.setTimeInterval(healthKitDictQuery.getStartTime(), healthKitDictQuery.getEndTime());
        HiHealthDataQueryOption hiHealthDataQueryOption = healthKitDictQuery.getHiHealthDataQueryOption();
        if (hiHealthDataQueryOption != null) {
            hiAggregateOption.setSortOrder(hiHealthDataQueryOption.getOrder());
            hiAggregateOption.setAnchor(hiHealthDataQueryOption.getOffset());
            hiAggregateOption.setCount(hiHealthDataQueryOption.getLimit());
        }
        hiAggregateOption.setGroupUnitType(healthKitDictQuery.getGroupUnitType());
        hiAggregateOption.setAggregateType(healthKitDictQuery.getAggregateType());
        b(hiAggregateOption, healthKitDictQuery);
        if (healthKitDictQuery.getValueFilterType() > 0) {
            hiAggregateOption.setTimeInterval(0L, System.currentTimeMillis());
            str = d(Long.valueOf(healthKitDictQuery.getStartTime()), Long.valueOf(healthKitDictQuery.getEndTime()), d(healthKitDictQuery.getValueFilterType(), hiAggregateOption.getType(), hiAggregateOption.getConstantsKey()));
        } else {
            str = "";
        }
        if (!TextUtils.isEmpty(healthKitDictQuery.getString("metadata"))) {
            hiAggregateOption.setFilter(healthKitDictQuery.getString("metadata"));
        }
        if (!TextUtils.isEmpty(healthKitDictQuery.getString("filterStartTime")) || !TextUtils.isEmpty(healthKitDictQuery.getString("filterEndTime"))) {
            hiAggregateOption.setFilterTimeInterval(healthKitDictQuery.getString("filterStartTime"), healthKitDictQuery.getString("filterEndTime"));
        }
        return new HiDataAggregateProOption.Builder().c(hiAggregateOption).c(healthKitDictQuery.getString("package_name")).b(str).c();
    }

    private String d(int i, int[] iArr, String[] strArr) {
        for (int i2 = 0; i2 < iArr.length; i2++) {
            if (i == iArr[i2] && i2 < strArr.length) {
                return strArr[i2];
            }
        }
        return "";
    }

    private String d(Long l, Long l2, String str) {
        HiDataFilter.DataFilterExpression dataFilterExpression = new HiDataFilter.DataFilterExpression(str, HiDataFilter.DataFilterExpression.BIGGER_EQUAL, l.longValue());
        HiDataFilter.DataFilterExpression dataFilterExpression2 = new HiDataFilter.DataFilterExpression(str, HiDataFilter.DataFilterExpression.LESS_EQUAL, l2.longValue());
        ArrayList arrayList = new ArrayList();
        arrayList.add(dataFilterExpression);
        arrayList.add(dataFilterExpression2);
        return HiJsonUtil.e(new HiDataFilter(arrayList, Collections.singletonList(0)));
    }

    private void b(int i, HiAggregateOption hiAggregateOption) {
        int[] iArr;
        String[] strArr;
        if (i == 10002) {
            iArr = new int[]{DicDataTypeUtil.DataType.BLOOD_PRESSURE_SYSTOLIC.value(), DicDataTypeUtil.DataType.BLOOD_PRESSURE_DIASTOLIC.value(), DicDataTypeUtil.DataType.BLOOD_PRESSURE_SPHYGMUS.value()};
            strArr = new String[]{"BLOOD_PRESSURE_SYSTOLIC", "BLOOD_PRESSURE_DIASTOLIC", BleConstants.BLOODPRESSURE_SPHYGMUS};
        } else if (i == 10007) {
            iArr = HiHealthDataType.a();
            strArr = HiHealthDataKey.b();
        } else {
            LogUtil.h("HiHealthKitQueryDicHelper", "unsupported type");
            iArr = null;
            strArr = null;
        }
        hiAggregateOption.setType(iArr);
        hiAggregateOption.setConstantsKey(strArr);
    }

    private void b(HiAggregateOption hiAggregateOption, int i, List<Integer> list) {
        List<Integer> arrayList = new ArrayList<>();
        List<String> arrayList2 = new ArrayList<>();
        if (i == 10006) {
            arrayList.add(Integer.valueOf(i));
            arrayList2.add(String.valueOf(i));
        } else {
            arrayList = HiHealthDictManager.d(this.d).g(i);
            arrayList2 = HiHealthDictManager.d(this.d).e(i);
        }
        ArrayList arrayList3 = new ArrayList(arrayList.size());
        ArrayList arrayList4 = new ArrayList(arrayList.size());
        if (list != null && !list.isEmpty()) {
            for (int i2 = 0; i2 < arrayList.size() && list.contains(arrayList.get(i2)); i2++) {
                arrayList3.add(arrayList.get(i2));
                arrayList4.add(arrayList2.get(i2));
            }
            arrayList = arrayList3;
            arrayList2 = arrayList4;
        }
        int[] iArr = new int[arrayList.size()];
        for (int i3 = 0; i3 < arrayList.size(); i3++) {
            iArr[i3] = arrayList.get(i3).intValue();
        }
        hiAggregateOption.setType(iArr);
        hiAggregateOption.setConstantsKey((String[]) arrayList2.toArray(new String[0]));
    }

    private void c(HiDataReadOption hiDataReadOption, int i) {
        int[] d = HiHealthDataType.d(i);
        String[] strArr = new String[d.length];
        for (int i2 = 0; i2 < d.length; i2++) {
            strArr[i2] = HiHealthDataKey.c(d[i2]);
        }
        hiDataReadOption.setType(d);
        hiDataReadOption.setConstantsKey(strArr);
    }

    private boolean g(HealthKitDictQuery healthKitDictQuery) {
        String string = healthKitDictQuery.getString("device_uniquecode");
        return !TextUtils.isEmpty(string) && string.contains(",");
    }

    private List<HiDataAggregateProOption> e(HealthKitDictQuery healthKitDictQuery) {
        ArrayList arrayList = new ArrayList();
        for (String str : healthKitDictQuery.getString("device_uniquecode").split(",")) {
            if (!TextUtils.isEmpty(str)) {
                healthKitDictQuery.putValue("device_uniquecode", str);
                arrayList.add(a(healthKitDictQuery));
            }
        }
        return arrayList;
    }

    private List<Integer> a(int i) {
        ArrayList arrayList = new ArrayList();
        if (HiHealthDictManager.d(this.d).l(i)) {
            return HiHealthDictManager.d(this.d).g(i);
        }
        arrayList.add(Integer.valueOf(i));
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<HiHealthKitData> c(List list, HealthKitDictQuery healthKitDictQuery, Cint cint) {
        ArrayList arrayList = new ArrayList();
        if (koq.c(list)) {
            LogUtil.a("HiHealthKitQueryDicHelper", "data source result size is:", Integer.valueOf(list.size()));
            Iterator it = list.iterator();
            while (it.hasNext()) {
                HiDeviceInfo hiDeviceInfo = ((HiHealthClient) it.next()).getHiDeviceInfo();
                String deviceUniqueCode = hiDeviceInfo.getDeviceUniqueCode();
                if (TextUtils.isEmpty(deviceUniqueCode)) {
                    LogUtil.a("HiHealthKitQueryDicHelper", "uniqueCode is null.");
                } else {
                    HiHealthKitData hiHealthKitData = new HiHealthKitData();
                    hiHealthKitData.setType(healthKitDictQuery.getSampleType());
                    hiHealthKitData.setTimeInterval(healthKitDictQuery.getStartTime(), healthKitDictQuery.getEndTime());
                    if (cint.e()) {
                        hiHealthKitData.putString("device_uniquecode", deviceUniqueCode);
                    } else {
                        hiHealthKitData.putString("device_uniquecode", "***");
                    }
                    hiHealthKitData.putString(PluginPayAdapter.KEY_DEVICE_INFO_NAME, hiDeviceInfo.getDeviceName());
                    hiHealthKitData.putString(PluginPayAdapter.KEY_DEVICE_INFO_MODEL, hiDeviceInfo.getModel());
                    arrayList.add(hiHealthKitData);
                }
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(List list, int i, int i2, IDataReadResultListener iDataReadResultListener) {
        try {
            iDataReadResultListener.onResult(list, i, i2);
        } catch (RemoteException e) {
            ReleaseLogUtil.c("HiH_HiHealthKitQueryDicHelper", "notifyDataReadResult RemoteException", LogAnonymous.b((Throwable) e));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(irc ircVar, String str, int i, String str2) {
        ircVar.c(this.d, new iqy(str, i, this.c, this.b, str2));
    }
}
