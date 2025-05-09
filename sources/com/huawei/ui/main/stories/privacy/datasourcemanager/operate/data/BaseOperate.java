package com.huawei.ui.main.stories.privacy.datasourcemanager.operate.data;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.SparseArray;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.hihealth.HiAggregateOption;
import com.huawei.hihealth.HiDataAggregateProOption;
import com.huawei.hihealth.HiDataDeleteOption;
import com.huawei.hihealth.HiDataDeleteProOption;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiDataReadProOption;
import com.huawei.hihealth.HiDataSourceFetchOption;
import com.huawei.hihealth.HiHealthClient;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.HiTimeInterval;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiAggregateListener;
import com.huawei.hihealth.data.listener.HiAggregateListenerEx;
import com.huawei.hihealth.data.listener.HiDataClientListener;
import com.huawei.hihealth.data.listener.HiDataOperateListener;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.ui.main.stories.privacy.datasourcemanager.DataSourceCallback;
import com.huawei.ui.main.stories.privacy.datasourcemanager.operate.data.BaseOperate;
import com.huawei.ui.main.stories.privacy.datasourcemanager.util.DivideConditional;
import com.huawei.ui.main.stories.privacy.template.model.bean.PrivacyDataModel;
import defpackage.koq;
import defpackage.mlg;
import defpackage.rkb;
import defpackage.rrb;
import defpackage.rrd;
import defpackage.rrf;
import defpackage.rsb;
import defpackage.rsg;
import defpackage.rsr;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/* loaded from: classes7.dex */
public abstract class BaseOperate {
    private static final String TAG = "BaseOperate";
    private static final String TAG_RELEASE = "R_BaseOperate";
    protected int pageType;
    public List<HiHealthData> mHiHealthDataListRecursive = new ArrayList(10);
    public List<HiHealthData> mMaxDataList = new ArrayList(10);
    public List<HiHealthData> mMinDataList = new ArrayList(10);
    protected final SimpleDateFormat sfYearMonth = new SimpleDateFormat("yyyy-MM");
    protected Context mContext = BaseApplication.getContext();
    public Handler mHandler = new Handler(Looper.getMainLooper());

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: readCateDouGroupDataBySource, reason: merged with bridge method [inline-methods] */
    public void m840x104e9445(rkb rkbVar, DataSourceCallback<Map<Integer, List<rsb>>> dataSourceCallback) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: readCateDouGroupDataNoSource, reason: merged with bridge method [inline-methods] */
    public void m841x17777686(rkb rkbVar, DataSourceCallback<Map<Integer, List<rsb>>> dataSourceCallback) {
    }

    public void readCategoryGroupData(rkb rkbVar, DataSourceCallback<Map<Integer, List<rsg>>> dataSourceCallback) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: readDayDataBySource, reason: merged with bridge method [inline-methods] */
    public void m842xa7339313(rkb rkbVar, DataSourceCallback<List<PrivacyDataModel>> dataSourceCallback) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: readDoubleGroupDataBySource, reason: merged with bridge method [inline-methods] */
    public void m845x53f041a1(rkb rkbVar, DataSourceCallback<List<rsb>> dataSourceCallback) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: readDoubleGroupDataNoSource, reason: merged with bridge method [inline-methods] */
    public void m846x5b1923e2(rkb rkbVar, DataSourceCallback<List<rsb>> dataSourceCallback) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: readGroupDataBySource, reason: merged with bridge method [inline-methods] */
    public void m848xdec3f9b0(rkb rkbVar, DataSourceCallback<List<rsg>> dataSourceCallback) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: readGroupDataNoSource, reason: merged with bridge method [inline-methods] */
    public void m849xe5ecdbf1(rkb rkbVar, DataSourceCallback<List<rsg>> dataSourceCallback) {
    }

    protected void setVarOfHiHealthDataProcess(HiHealthData hiHealthData, PrivacyDataModel privacyDataModel) {
    }

    protected void setVarOfReadByMonthByAppSource(rkb rkbVar) {
    }

    protected void setVarOfReadByMonthByDeviceSource(rkb rkbVar) {
    }

    protected void setVarOfReadByMonthNoSource(rkb rkbVar) {
    }

    protected void setVarOfReadInOneDayByAppSource(rkb rkbVar) {
    }

    protected void setVarOfReadInOneDayByDeviceSource(rkb rkbVar) {
    }

    protected void setVarOfReadInOneDayNoSource(rkb rkbVar) {
    }

    protected void setVarOfRecordDataProcess(HiHealthData hiHealthData, PrivacyDataModel privacyDataModel) {
    }

    public void readByMonthNoSource(DataSourceCallback<List<rsg>> dataSourceCallback) {
        rkb rkbVar = new rkb();
        rkbVar.b(1);
        rkbVar.a(3);
        rkbVar.c(0);
        setVarOfReadByMonthNoSource(rkbVar);
        aggregateHiHealthDataByMonth(rkbVar, dataSourceCallback);
    }

    public void readGroupData(final rkb rkbVar, final DataSourceCallback<List<rsg>> dataSourceCallback) {
        if (rkbVar == null) {
            ReleaseLogUtil.d(TAG_RELEASE, "readGroupData readOption is null");
            dataSourceCallback.onResponse(1, new ArrayList());
            return;
        }
        int m = rkbVar.m();
        if (m == 1 || m == 2 || m == 3) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: rku
                @Override // java.lang.Runnable
                public final void run() {
                    BaseOperate.this.m848xdec3f9b0(rkbVar, dataSourceCallback);
                }
            });
        } else {
            ThreadPoolManager.d().execute(new Runnable() { // from class: rkz
                @Override // java.lang.Runnable
                public final void run() {
                    BaseOperate.this.m849xe5ecdbf1(rkbVar, dataSourceCallback);
                }
            });
        }
    }

    public void readDoubleGroupData(final rkb rkbVar, final DataSourceCallback<List<rsb>> dataSourceCallback) {
        if (rkbVar == null) {
            LogUtil.h(TAG, "readDoubleGroupData readOption is null");
            dataSourceCallback.onResponse(1, new ArrayList());
            return;
        }
        int m = rkbVar.m();
        if (m == 1 || m == 2 || m == 3) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: rlf
                @Override // java.lang.Runnable
                public final void run() {
                    BaseOperate.this.m845x53f041a1(rkbVar, dataSourceCallback);
                }
            });
        } else {
            ThreadPoolManager.d().execute(new Runnable() { // from class: rlc
                @Override // java.lang.Runnable
                public final void run() {
                    BaseOperate.this.m846x5b1923e2(rkbVar, dataSourceCallback);
                }
            });
        }
    }

    public void readCategoryDoubleGroupData(final rkb rkbVar, final DataSourceCallback<Map<Integer, List<rsb>>> dataSourceCallback) {
        if (rkbVar == null) {
            LogUtil.h(TAG, "readCategoryDoubleGroupData readOption is null");
            dataSourceCallback.onResponse(1, new HashMap());
            return;
        }
        int m = rkbVar.m();
        if (m == 1 || m == 2 || m == 3) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: rkl
                @Override // java.lang.Runnable
                public final void run() {
                    BaseOperate.this.m840x104e9445(rkbVar, dataSourceCallback);
                }
            });
        } else {
            ThreadPoolManager.d().execute(new Runnable() { // from class: rki
                @Override // java.lang.Runnable
                public final void run() {
                    BaseOperate.this.m841x17777686(rkbVar, dataSourceCallback);
                }
            });
        }
    }

    public void readDayData(final rkb rkbVar, final DataSourceCallback<List<PrivacyDataModel>> dataSourceCallback) {
        if (rkbVar == null) {
            ReleaseLogUtil.d(TAG_RELEASE, "readGroupData readOption is null");
            dataSourceCallback.onResponse(1, new ArrayList());
            return;
        }
        int m = rkbVar.m();
        if (m == 1 || m == 2 || m == 3) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: rkt
                @Override // java.lang.Runnable
                public final void run() {
                    BaseOperate.this.m842xa7339313(rkbVar, dataSourceCallback);
                }
            });
        } else {
            ThreadPoolManager.d().execute(new Runnable() { // from class: rks
                @Override // java.lang.Runnable
                public final void run() {
                    BaseOperate.this.m843xae5c7554(rkbVar, dataSourceCallback);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: readDayDataNoSource, reason: merged with bridge method [inline-methods] */
    public void m843xae5c7554(final rkb rkbVar, final DataSourceCallback<List<PrivacyDataModel>> dataSourceCallback) {
        this.mHiHealthDataListRecursive.clear();
        setVarOfReadInOneDayNoSource(rkbVar);
        ReleaseLogUtil.e(TAG_RELEASE, "readDayDataNoSource start");
        readInOneDay(rkbVar, new DataSourceCallback() { // from class: rla
            @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.DataSourceCallback
            public final void onResponse(int i, Object obj) {
                BaseOperate.this.m844x76c6a262(dataSourceCallback, rkbVar, i, (List) obj);
            }
        });
    }

    /* renamed from: lambda$readDayDataNoSource$10$com-huawei-ui-main-stories-privacy-datasourcemanager-operate-data-BaseOperate, reason: not valid java name */
    public /* synthetic */ void m844x76c6a262(final DataSourceCallback dataSourceCallback, rkb rkbVar, final int i, List list) {
        ReleaseLogUtil.e(TAG_RELEASE, "readDayDataNoSource end resultCode = ", Integer.valueOf(i));
        if (i != 0 || koq.b(list)) {
            this.mHandler.post(new Runnable() { // from class: rko
                @Override // java.lang.Runnable
                public final void run() {
                    DataSourceCallback.this.onResponse(i, new ArrayList());
                }
            });
            return;
        }
        final List<PrivacyDataModel> proResultOfReadInOneDayNoSource = proResultOfReadInOneDayNoSource(list);
        if (rkbVar.k()) {
            setIconResource(proResultOfReadInOneDayNoSource);
        }
        this.mHandler.post(new Runnable() { // from class: rkr
            @Override // java.lang.Runnable
            public final void run() {
                DataSourceCallback.this.onResponse(i, proResultOfReadInOneDayNoSource);
            }
        });
    }

    protected List<PrivacyDataModel> proResultOfReadInOneDayNoSource(List<HiHealthData> list) {
        return new ArrayList();
    }

    public void deleteDatas(List<PrivacyDataModel> list, rkb rkbVar, final DataSourceCallback<Boolean> dataSourceCallback) {
        HiDataDeleteOption hiDataDeleteOption = new HiDataDeleteOption();
        hiDataDeleteOption.setTypes(getDeleteDataTypes(rkbVar));
        ArrayList arrayList = new ArrayList(10);
        for (PrivacyDataModel privacyDataModel : list) {
            arrayList.add(new HiTimeInterval(privacyDataModel.getStartTime(), privacyDataModel.getEndTime()));
        }
        hiDataDeleteOption.setTimes(arrayList);
        HiDataDeleteProOption d = HiDataDeleteProOption.builder().d(hiDataDeleteOption).e(Integer.valueOf(rkbVar.e())).c(0).e(rkbVar.i()).d(rkbVar.j()).d();
        ReleaseLogUtil.e(TAG_RELEASE, "deleteData start");
        HiHealthManager.d(BaseApplication.getContext()).deleteHiHealthDataPro(d, new HiDataOperateListener() { // from class: rld
            @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
            public final void onResult(int i, Object obj) {
                BaseOperate.this.m835xfa38a593(dataSourceCallback, i, obj);
            }
        });
    }

    /* renamed from: lambda$deleteDatas$13$com-huawei-ui-main-stories-privacy-datasourcemanager-operate-data-BaseOperate, reason: not valid java name */
    public /* synthetic */ void m835xfa38a593(final DataSourceCallback dataSourceCallback, int i, Object obj) {
        ReleaseLogUtil.e(TAG_RELEASE, "deleteData end errorCode = ", Integer.valueOf(i));
        if (i == 0) {
            this.mHandler.post(new Runnable() { // from class: rkx
                @Override // java.lang.Runnable
                public final void run() {
                    DataSourceCallback.this.onResponse(0, true);
                }
            });
        } else {
            this.mHandler.post(new Runnable() { // from class: rjy
                @Override // java.lang.Runnable
                public final void run() {
                    DataSourceCallback.this.onResponse(1, false);
                }
            });
        }
    }

    public void readByMonthByDeviceSource(String str, int i, String str2, DataSourceCallback<List<rsg>> dataSourceCallback) {
        rkb rkbVar = new rkb();
        rkbVar.b(1);
        rkbVar.a(3);
        rkbVar.d(str);
        rkbVar.c(i);
        rkbVar.d(true);
        setVarOfReadByMonthByDeviceSource(rkbVar);
        if (!rrb.c(str2, str)) {
            aggregateHiHealthDataByMonth(rkbVar, dataSourceCallback);
        } else {
            rkbVar.e(str2);
            aggregateHiHealthDataProByMonth(rkbVar, dataSourceCallback);
        }
    }

    public void readByMonthByAppSource(String str, DataSourceCallback<List<rsg>> dataSourceCallback) {
        rkb rkbVar = new rkb();
        rkbVar.e(str);
        rkbVar.b(1);
        rkbVar.a(3);
        rkbVar.c(1);
        setVarOfReadByMonthByAppSource(rkbVar);
        aggregateHiHealthDataProByMonth(rkbVar, dataSourceCallback);
    }

    public void readInOneDayByDeviceSource(long j, String str, final DataSourceCallback<List<PrivacyDataModel>> dataSourceCallback) {
        rkb rkbVar = new rkb();
        this.mHiHealthDataListRecursive.clear();
        rkbVar.c(rrb.c(j));
        rkbVar.d(rrb.e(j));
        rkbVar.c(2);
        rkbVar.d(str);
        rkbVar.d(true);
        setVarOfReadInOneDayByDeviceSource(rkbVar);
        ReleaseLogUtil.e(TAG_RELEASE, "readInOneDayByDeviceSource start");
        readInOneDay(rkbVar, new DataSourceCallback() { // from class: rkv
            @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.DataSourceCallback
            public final void onResponse(int i, Object obj) {
                BaseOperate.this.m851xbaca70c1(dataSourceCallback, i, (List) obj);
            }
        });
    }

    /* renamed from: lambda$readInOneDayByDeviceSource$16$com-huawei-ui-main-stories-privacy-datasourcemanager-operate-data-BaseOperate, reason: not valid java name */
    public /* synthetic */ void m851xbaca70c1(final DataSourceCallback dataSourceCallback, final int i, List list) {
        ReleaseLogUtil.e(TAG_RELEASE, "readInOneDayByDeviceSource end resultCode = ", Integer.valueOf(i));
        if (i != 0 || koq.b(list)) {
            this.mHandler.post(new Runnable() { // from class: rka
                @Override // java.lang.Runnable
                public final void run() {
                    DataSourceCallback.this.onResponse(i, new ArrayList());
                }
            });
        } else {
            final List<PrivacyDataModel> proResultOfReadInOneDayNoSource = proResultOfReadInOneDayNoSource(list);
            this.mHandler.post(new Runnable() { // from class: rkg
                @Override // java.lang.Runnable
                public final void run() {
                    DataSourceCallback.this.onResponse(i, proResultOfReadInOneDayNoSource);
                }
            });
        }
    }

    public void readInOneDayByAppSource(long j, String str, String str2, int i, final DataSourceCallback<List<PrivacyDataModel>> dataSourceCallback) {
        final rkb rkbVar = new rkb();
        this.mHiHealthDataListRecursive.clear();
        rkbVar.c(rrb.c(j));
        rkbVar.d(rrb.e(j));
        rkbVar.c(i);
        rkbVar.e(str2);
        rkbVar.d(str);
        setVarOfReadInOneDayByAppSource(rkbVar);
        ReleaseLogUtil.e(TAG_RELEASE, "readInOneDayByAppSource start");
        readHiHealthDataPro(rkbVar, new DataSourceCallback() { // from class: rke
            @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.DataSourceCallback
            public final void onResponse(int i2, Object obj) {
                BaseOperate.this.m850xfe11d3c3(dataSourceCallback, rkbVar, i2, (SparseArray) obj);
            }
        });
    }

    /* renamed from: lambda$readInOneDayByAppSource$19$com-huawei-ui-main-stories-privacy-datasourcemanager-operate-data-BaseOperate, reason: not valid java name */
    public /* synthetic */ void m850xfe11d3c3(final DataSourceCallback dataSourceCallback, rkb rkbVar, final int i, SparseArray sparseArray) {
        ReleaseLogUtil.e(TAG_RELEASE, "readInOneDayByAppSource end resultCode = ", Integer.valueOf(i));
        if (i != 0 || sparseArray == null || sparseArray.size() == 0) {
            this.mHandler.post(new Runnable() { // from class: rkf
                @Override // java.lang.Runnable
                public final void run() {
                    DataSourceCallback.this.onResponse(i, new ArrayList());
                }
            });
        } else {
            final List<PrivacyDataModel> proResultOfReadInOneDayNoSource = proResultOfReadInOneDayNoSource(dealResultForReadInOneDay(sparseArray, rkbVar));
            this.mHandler.post(new Runnable() { // from class: rkh
                @Override // java.lang.Runnable
                public final void run() {
                    DataSourceCallback.this.onResponse(i, proResultOfReadInOneDayNoSource);
                }
            });
        }
    }

    protected void aggregateHiHealthDataByMonth(rkb rkbVar, DataSourceCallback<List<rsg>> dataSourceCallback) {
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        hiAggregateOption.setStartTime(0L);
        hiAggregateOption.setEndTime(System.currentTimeMillis());
        hiAggregateOption.setConstantsKey(rkbVar.d());
        hiAggregateOption.setAggregateType(rkbVar.b());
        hiAggregateOption.setType(rkbVar.l());
        hiAggregateOption.setGroupUnitType(rkbVar.f());
        hiAggregateOption.setReadType(rkbVar.m());
        hiAggregateOption.setSortOrder(1);
        if (rkbVar.n()) {
            hiAggregateOption.setDeviceUuid(rkbVar.i());
        }
        ReleaseLogUtil.e(TAG_RELEASE, "aggregateHiHealthDataByMonth start");
        HiHealthManager.d(this.mContext).aggregateHiHealthData(hiAggregateOption, new AnonymousClass3(dataSourceCallback));
    }

    /* renamed from: com.huawei.ui.main.stories.privacy.datasourcemanager.operate.data.BaseOperate$3, reason: invalid class name */
    public class AnonymousClass3 implements HiAggregateListener {
        final /* synthetic */ DataSourceCallback d;

        @Override // com.huawei.hihealth.data.listener.HiAggregateListener
        public void onResultIntent(int i, List<HiHealthData> list, int i2, int i3) {
        }

        AnonymousClass3(DataSourceCallback dataSourceCallback) {
            this.d = dataSourceCallback;
        }

        @Override // com.huawei.hihealth.data.listener.HiAggregateListener
        public void onResult(List<HiHealthData> list, int i, int i2) {
            ReleaseLogUtil.e(BaseOperate.TAG_RELEASE, "aggregateHiHealthDataByMonth end errorCode = " + i);
            if (i != 0) {
                Handler handler = BaseOperate.this.mHandler;
                final DataSourceCallback dataSourceCallback = this.d;
                handler.post(new Runnable() { // from class: rlh
                    @Override // java.lang.Runnable
                    public final void run() {
                        DataSourceCallback.this.onResponse(1, new ArrayList());
                    }
                });
            } else if (koq.b(list)) {
                Handler handler2 = BaseOperate.this.mHandler;
                final DataSourceCallback dataSourceCallback2 = this.d;
                handler2.post(new Runnable() { // from class: rlk
                    @Override // java.lang.Runnable
                    public final void run() {
                        DataSourceCallback.this.onResponse(0, new ArrayList());
                    }
                });
            } else {
                final List<rsg> hiHealthDataProcess = BaseOperate.this.hiHealthDataProcess(list);
                Handler handler3 = BaseOperate.this.mHandler;
                final DataSourceCallback dataSourceCallback3 = this.d;
                handler3.post(new Runnable() { // from class: rlj
                    @Override // java.lang.Runnable
                    public final void run() {
                        DataSourceCallback.this.onResponse(0, hiHealthDataProcess);
                    }
                });
            }
        }
    }

    protected void aggregateHiHealthDataProByMonth(rkb rkbVar, DataSourceCallback<List<rsg>> dataSourceCallback) {
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        hiAggregateOption.setStartTime(0L);
        hiAggregateOption.setEndTime(System.currentTimeMillis());
        hiAggregateOption.setConstantsKey(rkbVar.d());
        hiAggregateOption.setAggregateType(rkbVar.b());
        hiAggregateOption.setType(rkbVar.l());
        hiAggregateOption.setGroupUnitType(rkbVar.f());
        hiAggregateOption.setReadType(rkbVar.m());
        hiAggregateOption.setDeviceUuid(rkbVar.i());
        hiAggregateOption.setSortOrder(1);
        HiDataAggregateProOption c = HiDataAggregateProOption.builder().c(hiAggregateOption).c(rkbVar.j()).c();
        ReleaseLogUtil.e(TAG_RELEASE, "aggregateHiHealthDataProByMonth start");
        HiHealthManager.d(this.mContext).aggregateHiHealthDataPro(c, new AnonymousClass5(dataSourceCallback));
    }

    /* renamed from: com.huawei.ui.main.stories.privacy.datasourcemanager.operate.data.BaseOperate$5, reason: invalid class name */
    public class AnonymousClass5 implements HiAggregateListener {
        final /* synthetic */ DataSourceCallback b;

        @Override // com.huawei.hihealth.data.listener.HiAggregateListener
        public void onResultIntent(int i, List<HiHealthData> list, int i2, int i3) {
        }

        AnonymousClass5(DataSourceCallback dataSourceCallback) {
            this.b = dataSourceCallback;
        }

        @Override // com.huawei.hihealth.data.listener.HiAggregateListener
        public void onResult(List<HiHealthData> list, int i, int i2) {
            ReleaseLogUtil.e(BaseOperate.TAG_RELEASE, "aggregateHiHealthDataProByMonth end errorCode = " + i);
            if (i != 0) {
                Handler handler = BaseOperate.this.mHandler;
                final DataSourceCallback dataSourceCallback = this.b;
                handler.post(new Runnable() { // from class: rli
                    @Override // java.lang.Runnable
                    public final void run() {
                        DataSourceCallback.this.onResponse(1, new ArrayList());
                    }
                });
            } else if (koq.b(list)) {
                Handler handler2 = BaseOperate.this.mHandler;
                final DataSourceCallback dataSourceCallback2 = this.b;
                handler2.post(new Runnable() { // from class: rll
                    @Override // java.lang.Runnable
                    public final void run() {
                        DataSourceCallback.this.onResponse(0, new ArrayList());
                    }
                });
            } else {
                final List<rsg> hiHealthDataProcess = BaseOperate.this.hiHealthDataProcess(list);
                Handler handler3 = BaseOperate.this.mHandler;
                final DataSourceCallback dataSourceCallback3 = this.b;
                handler3.post(new Runnable() { // from class: rlm
                    @Override // java.lang.Runnable
                    public final void run() {
                        DataSourceCallback.this.onResponse(0, hiHealthDataProcess);
                    }
                });
            }
        }
    }

    public void sortHiHealthDataToDesc(List<HiHealthData> list) {
        HiHealthData[] hiHealthDataArr = (HiHealthData[]) list.toArray(new HiHealthData[0]);
        Arrays.sort(hiHealthDataArr, new Comparator() { // from class: rkp
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                int compare;
                compare = Long.compare(((HiHealthData) obj2).getStartTime(), ((HiHealthData) obj).getStartTime());
                return compare;
            }
        });
        ListIterator<HiHealthData> listIterator = list.listIterator();
        for (HiHealthData hiHealthData : hiHealthDataArr) {
            listIterator.next();
            listIterator.set(hiHealthData);
        }
    }

    protected void readInOneDay(final rkb rkbVar, final DataSourceCallback<List<HiHealthData>> dataSourceCallback) {
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setReadType(rkbVar.m());
        hiDataReadOption.setStartTime(rkbVar.o());
        hiDataReadOption.setEndTime(rkbVar.h());
        hiDataReadOption.setType(rkbVar.l());
        hiDataReadOption.setSortOrder(1);
        hiDataReadOption.setCount(rkbVar.a());
        if (rkbVar.n()) {
            hiDataReadOption.setDeviceUuid(rkbVar.i());
        }
        HiHealthNativeApi a2 = HiHealthNativeApi.a(this.mContext);
        ReleaseLogUtil.e(TAG_RELEASE, "readInOneDay start");
        a2.readHiHealthData(hiDataReadOption, new HiDataReadResultListener() { // from class: com.huawei.ui.main.stories.privacy.datasourcemanager.operate.data.BaseOperate.1
            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i, Object obj, int i2, int i3) {
            }

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResult(Object obj, int i, int i2) {
                ReleaseLogUtil.e(BaseOperate.TAG_RELEASE, "readInOneDay end errorCode = " + i + ", anchor = " + i2);
                if (i != 0) {
                    dataSourceCallback.onResponse(1, new ArrayList());
                    return;
                }
                SparseArray<List<HiHealthData>> sparseArray = new SparseArray<>();
                if (obj instanceof SparseArray) {
                    sparseArray = (SparseArray) obj;
                }
                if (sparseArray.size() == 0) {
                    dataSourceCallback.onResponse(0, new ArrayList());
                } else {
                    dataSourceCallback.onResponse(0, BaseOperate.this.dealResultForReadInOneDay(sparseArray, rkbVar));
                }
            }
        });
    }

    public void readHiHealthDataProInOneDay(final rkb rkbVar, final DataSourceCallback<List<HiHealthData>> dataSourceCallback) {
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setReadType(rkbVar.m());
        hiDataReadOption.setStartTime(rkbVar.o());
        hiDataReadOption.setEndTime(rkbVar.h());
        hiDataReadOption.setType(rkbVar.l());
        hiDataReadOption.setSortOrder(1);
        hiDataReadOption.setDeviceUuid(rkbVar.i());
        hiDataReadOption.setCount(rkbVar.a());
        HiDataReadProOption e = HiDataReadProOption.builder().e(hiDataReadOption).a(rkbVar.j()).e();
        ReleaseLogUtil.e(TAG_RELEASE, "readHiHealthDataProInOneDay start");
        HiHealthNativeApi.a(this.mContext).readHiHealthDataPro(e, new HiDataReadResultListener() { // from class: com.huawei.ui.main.stories.privacy.datasourcemanager.operate.data.BaseOperate.4
            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i, Object obj, int i2, int i3) {
            }

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResult(Object obj, int i, int i2) {
                ReleaseLogUtil.e(BaseOperate.TAG_RELEASE, "readHiHealthDataProInOneDay end errorCode = " + i + ", anchor = " + i2);
                if (i != 0) {
                    dataSourceCallback.onResponse(1, null);
                    return;
                }
                SparseArray<List<HiHealthData>> sparseArray = new SparseArray<>();
                if (obj instanceof SparseArray) {
                    sparseArray = (SparseArray) obj;
                }
                List<HiHealthData> dealResultForReadInOneDay = BaseOperate.this.dealResultForReadInOneDay(sparseArray, rkbVar);
                if (koq.b(dealResultForReadInOneDay) || dealResultForReadInOneDay.size() < rkbVar.a() || dealResultForReadInOneDay.get(dealResultForReadInOneDay.size() - 1).getStartTime() <= rkbVar.o()) {
                    dataSourceCallback.onResponse(0, BaseOperate.this.mHiHealthDataListRecursive);
                } else {
                    rkbVar.d(dealResultForReadInOneDay.get(dealResultForReadInOneDay.size() - 1).getStartTime() - 1);
                    BaseOperate.this.readHiHealthDataProInOneDay(rkbVar, dataSourceCallback);
                }
            }
        });
    }

    public List<HiHealthData> dealResultForReadInOneDay(SparseArray<List<HiHealthData>> sparseArray, rkb rkbVar) {
        List<HiHealthData> list = sparseArray.get(rkbVar.l()[0]);
        if (koq.c(list)) {
            this.mHiHealthDataListRecursive.addAll(list);
        }
        return list;
    }

    protected List<PrivacyDataModel> convertToDataModels(List<HiHealthData> list, rkb rkbVar) {
        ArrayList arrayList = new ArrayList(10);
        if (koq.b(list)) {
            return arrayList;
        }
        Iterator<HiHealthData> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(convertToDataModel(it.next(), rkbVar));
        }
        return arrayList;
    }

    protected PrivacyDataModel convertToDataModel(HiHealthData hiHealthData, rkb rkbVar) {
        PrivacyDataModel privacyDataModel = new PrivacyDataModel();
        if (hiHealthData == null) {
            return privacyDataModel;
        }
        privacyDataModel.setPageType(this.pageType);
        privacyDataModel.setDataTitle(rrb.b(this.pageType, hiHealthData, rkbVar));
        privacyDataModel.setDataDesc(rrb.d(this.pageType, hiHealthData, rkbVar));
        privacyDataModel.setContentValues(hiHealthData.getValueHolder());
        privacyDataModel.setStartTime(hiHealthData.getStartTime());
        privacyDataModel.setEndTime(hiHealthData.getEndTime());
        privacyDataModel.setModifyTime(hiHealthData.getModifiedTime());
        privacyDataModel.setType(hiHealthData.getType());
        privacyDataModel.setClientId(hiHealthData.getClientId());
        return privacyDataModel;
    }

    protected List<PrivacyDataModel> recordDataProcess(List<HiHealthData> list, int i) {
        ArrayList arrayList = new ArrayList(10);
        if (list == null) {
            return arrayList;
        }
        for (HiHealthData hiHealthData : list) {
            PrivacyDataModel privacyDataModel = new PrivacyDataModel();
            privacyDataModel.setStartTime(hiHealthData.getStartTime());
            privacyDataModel.setEndTime(hiHealthData.getEndTime());
            privacyDataModel.setModifyTime(hiHealthData.getModifiedTime());
            privacyDataModel.setDataDesc(rrb.d(hiHealthData, i));
            privacyDataModel.setPageType(this.pageType);
            privacyDataModel.setType(hiHealthData.getType());
            privacyDataModel.setClientId(hiHealthData.getClientId());
            setVarOfRecordDataProcess(hiHealthData, privacyDataModel);
            arrayList.add(privacyDataModel);
        }
        return arrayList;
    }

    public List<rsg> hiHealthDataProcess(List<HiHealthData> list) {
        List<List<PrivacyDataModel>> d = rrd.d(transPrivacyDataModels(list), new DivideConditional() { // from class: rkq
            @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.util.DivideConditional
            public final boolean isDivide(Object obj, Object obj2) {
                return BaseOperate.this.m839x82902de6((PrivacyDataModel) obj, (PrivacyDataModel) obj2);
            }
        });
        ArrayList arrayList = new ArrayList(10);
        for (List<PrivacyDataModel> list2 : d) {
            if (!koq.b(list2)) {
                rsg rsgVar = new rsg();
                rsgVar.b(list2);
                if (this.pageType == 103) {
                    rsgVar.e(mlg.a(list2.get(0).getStartTime() + 14400000, 0));
                } else {
                    rsgVar.e(mlg.a(list2.get(0).getStartTime(), 0));
                }
                arrayList.add(rsgVar);
            }
        }
        LogUtil.a(TAG, "hiHealthDataProcess end, hiHealthDataProcess list size=", Integer.valueOf(arrayList.size()));
        return arrayList;
    }

    /* renamed from: lambda$hiHealthDataProcess$21$com-huawei-ui-main-stories-privacy-datasourcemanager-operate-data-BaseOperate, reason: not valid java name */
    public /* synthetic */ boolean m839x82902de6(PrivacyDataModel privacyDataModel, PrivacyDataModel privacyDataModel2) {
        boolean a2;
        if (this.pageType == 103) {
            a2 = rsr.a(privacyDataModel.getStartTime() + 14400000, privacyDataModel2.getStartTime() + 14400000);
        } else {
            a2 = rsr.a(privacyDataModel.getStartTime(), privacyDataModel2.getStartTime());
        }
        return !a2;
    }

    private List<PrivacyDataModel> transPrivacyDataModels(List<HiHealthData> list) {
        ArrayList arrayList = new ArrayList(10);
        for (HiHealthData hiHealthData : list) {
            PrivacyDataModel privacyDataModel = new PrivacyDataModel();
            privacyDataModel.setStartTime(hiHealthData.getStartTime());
            privacyDataModel.setEndTime(hiHealthData.getEndTime());
            privacyDataModel.setModifyTime(hiHealthData.getModifiedTime());
            privacyDataModel.setPageType(this.pageType);
            privacyDataModel.setClientId(hiHealthData.getClientId());
            setVarOfHiHealthDataProcess(hiHealthData, privacyDataModel);
            arrayList.add(privacyDataModel);
        }
        return arrayList;
    }

    protected void readForOneDayDatas(final rkb rkbVar, final DataSourceCallback<List<PrivacyDataModel>> dataSourceCallback) {
        readHiHealthDataPro(rkbVar, new DataSourceCallback() { // from class: rkj
            @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.DataSourceCallback
            public final void onResponse(int i, Object obj) {
                BaseOperate.this.m847x8945e7f7(dataSourceCallback, rkbVar, i, (SparseArray) obj);
            }
        });
    }

    /* renamed from: lambda$readForOneDayDatas$24$com-huawei-ui-main-stories-privacy-datasourcemanager-operate-data-BaseOperate, reason: not valid java name */
    public /* synthetic */ void m847x8945e7f7(final DataSourceCallback dataSourceCallback, rkb rkbVar, final int i, SparseArray sparseArray) {
        if (i != 0 || sparseArray == null || sparseArray.size() == 0) {
            this.mHandler.post(new Runnable() { // from class: rkm
                @Override // java.lang.Runnable
                public final void run() {
                    DataSourceCallback.this.onResponse(i, new ArrayList());
                }
            });
            return;
        }
        ArrayList arrayList = new ArrayList(10);
        for (int i2 : rkbVar.l()) {
            if (sparseArray.get(i2) != null) {
                arrayList.addAll((Collection) sparseArray.get(i2));
            }
        }
        sortHiHealthDataToDesc(arrayList);
        final List<PrivacyDataModel> proResultOfReadInOneDayNoSource = proResultOfReadInOneDayNoSource(arrayList);
        if (rkbVar.k()) {
            setIconResource(proResultOfReadInOneDayNoSource);
        }
        this.mHandler.post(new Runnable() { // from class: rkk
            @Override // java.lang.Runnable
            public final void run() {
                DataSourceCallback.this.onResponse(i, proResultOfReadInOneDayNoSource);
            }
        });
    }

    protected void readHiHealthDataPro(rkb rkbVar, final DataSourceCallback<SparseArray<List<HiHealthData>>> dataSourceCallback) {
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setSortOrder(1);
        hiDataReadOption.setType(rkbVar.l());
        hiDataReadOption.setStartTime(rkbVar.o());
        hiDataReadOption.setEndTime(rkbVar.h());
        hiDataReadOption.setReadType(rkbVar.m());
        hiDataReadOption.setDeviceUuid(rkbVar.i());
        HiDataReadProOption e = HiDataReadProOption.builder().e(hiDataReadOption).a(rkbVar.j()).e();
        ReleaseLogUtil.e(TAG_RELEASE, "readHiHealthDataPro start");
        HiHealthNativeApi.a(this.mContext).readHiHealthDataPro(e, new HiDataReadResultListener() { // from class: com.huawei.ui.main.stories.privacy.datasourcemanager.operate.data.BaseOperate.10
            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i, Object obj, int i2, int i3) {
            }

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResult(Object obj, int i, int i2) {
                ReleaseLogUtil.e(BaseOperate.TAG_RELEASE, "readHiHealthDataPro end errorCode = " + i);
                if (i != 0) {
                    dataSourceCallback.onResponse(1, new SparseArray());
                    return;
                }
                SparseArray sparseArray = new SparseArray();
                if (obj instanceof SparseArray) {
                    sparseArray = (SparseArray) obj;
                }
                dataSourceCallback.onResponse(0, sparseArray);
            }
        });
    }

    public void readHiHealthDataPro(HiDataReadProOption hiDataReadProOption, final DataSourceCallback<SparseArray<List<HiHealthData>>> dataSourceCallback) {
        ReleaseLogUtil.e(TAG_RELEASE, "readHiHealthDataPro start");
        HiHealthNativeApi.a(this.mContext).readHiHealthDataPro(hiDataReadProOption, new HiDataReadResultListener() { // from class: com.huawei.ui.main.stories.privacy.datasourcemanager.operate.data.BaseOperate.8
            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i, Object obj, int i2, int i3) {
            }

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResult(Object obj, int i, int i2) {
                ReleaseLogUtil.e(BaseOperate.TAG_RELEASE, "readHiHealthDataPro end errorCode = ", Integer.valueOf(i));
                if (i != 0 || !(obj instanceof SparseArray)) {
                    dataSourceCallback.onResponse(1, new SparseArray());
                } else {
                    dataSourceCallback.onResponse(0, (SparseArray) obj);
                }
            }
        });
    }

    protected List<HiHealthData> mergeMaxMinList(List<HiHealthData> list, List<HiHealthData> list2) {
        return mergeMaxMinList(list, list2, null, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x006d A[LOOP:0: B:15:0x0067->B:17:0x006d, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x00d0  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00e4  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00fa  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0110  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0150  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0112  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00fc  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00e6  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x00d2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected java.util.List<com.huawei.hihealth.HiHealthData> mergeMaxMinList(java.util.List<com.huawei.hihealth.HiHealthData> r25, java.util.List<com.huawei.hihealth.HiHealthData> r26, java.util.List<com.huawei.hihealth.HiHealthData> r27, java.util.List<com.huawei.hihealth.HiHealthData> r28) {
        /*
            Method dump skipped, instructions count: 539
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.ui.main.stories.privacy.datasourcemanager.operate.data.BaseOperate.mergeMaxMinList(java.util.List, java.util.List, java.util.List, java.util.List):java.util.List");
    }

    private List<HiHealthData> handleLongerList(List<HiHealthData> list, List<HiHealthData> list2) {
        final HashSet hashSet = new HashSet();
        Iterator<HiHealthData> it = list2.iterator();
        while (it.hasNext()) {
            hashSet.add(Long.valueOf(it.next().getDay()));
        }
        return (List) list.stream().filter(new Predicate() { // from class: rkd
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                boolean contains;
                contains = hashSet.contains(Long.valueOf(((HiHealthData) obj).getDay()));
                return contains;
            }
        }).collect(Collectors.toList());
    }

    protected void aggregateProMaxAndMin(rkb rkbVar, final DataSourceCallback<List<HiHealthData>> dataSourceCallback) {
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        hiAggregateOption.setStartTime(rkbVar.o());
        hiAggregateOption.setEndTime(rkbVar.h());
        hiAggregateOption.setConstantsKey(rkbVar.d());
        hiAggregateOption.setAggregateType(rkbVar.b());
        hiAggregateOption.setType(rkbVar.l());
        hiAggregateOption.setGroupUnitType(3);
        hiAggregateOption.setReadType(rkbVar.m());
        hiAggregateOption.setSortOrder(1);
        hiAggregateOption.setDeviceUuid(rkbVar.i());
        HiDataAggregateProOption c = HiDataAggregateProOption.builder().c(hiAggregateOption).c(rkbVar.j()).c();
        ReleaseLogUtil.e(TAG_RELEASE, "aggregateProMaxAndMin start");
        HiHealthNativeApi.a(this.mContext).aggregateHiHealthDataPro(c, new HiAggregateListener() { // from class: com.huawei.ui.main.stories.privacy.datasourcemanager.operate.data.BaseOperate.9
            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResultIntent(int i, List<HiHealthData> list, int i2, int i3) {
            }

            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResult(List<HiHealthData> list, int i, int i2) {
                ReleaseLogUtil.e(BaseOperate.TAG_RELEASE, "aggregateProMaxAndMin end errorCode = " + i);
                if (i != 0) {
                    dataSourceCallback.onResponse(1, new ArrayList());
                } else if (koq.b(list)) {
                    dataSourceCallback.onResponse(0, new ArrayList());
                } else {
                    dataSourceCallback.onResponse(0, list);
                }
            }
        });
    }

    protected void transKeyToValue(List<HiHealthData> list, List<HiHealthData> list2, rkb rkbVar) {
        for (HiHealthData hiHealthData : list) {
            HiHealthData hiHealthData2 = new HiHealthData();
            hiHealthData2.setValue(hiHealthData.getDouble(rkbVar.d()[0]));
            hiHealthData2.setStartTime(hiHealthData.getStartTime());
            hiHealthData2.setEndTime(hiHealthData.getEndTime());
            hiHealthData2.setModifiedTime(hiHealthData.getModifiedTime());
            list2.add(hiHealthData2);
        }
    }

    protected List<rsg> groupByMonth(List<PrivacyDataModel> list) {
        ArrayList arrayList = new ArrayList(10);
        if (koq.b(list)) {
            return arrayList;
        }
        for (List<PrivacyDataModel> list2 : rrd.d(list, new DivideConditional() { // from class: rlb
            @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.util.DivideConditional
            public final boolean isDivide(Object obj, Object obj2) {
                return BaseOperate.this.m838x24a5850((PrivacyDataModel) obj, (PrivacyDataModel) obj2);
            }
        })) {
            rsg rsgVar = new rsg();
            rsgVar.b(list2);
            if (this.pageType == 103) {
                rsgVar.e(mlg.a(list2.get(0).getStartTime() + 14400000, 0));
            } else {
                rsgVar.e(mlg.a(list2.get(0).getStartTime(), 0));
            }
            arrayList.add(rsgVar);
        }
        return arrayList;
    }

    /* renamed from: lambda$groupByMonth$27$com-huawei-ui-main-stories-privacy-datasourcemanager-operate-data-BaseOperate, reason: not valid java name */
    public /* synthetic */ boolean m838x24a5850(PrivacyDataModel privacyDataModel, PrivacyDataModel privacyDataModel2) {
        boolean a2;
        if (this.pageType == 103) {
            a2 = rsr.a(privacyDataModel.getStartTime() + 14400000, privacyDataModel2.getStartTime() + 14400000);
        } else {
            a2 = rsr.a(privacyDataModel.getStartTime(), privacyDataModel2.getStartTime());
        }
        return !a2;
    }

    protected List<rsg> groupByDay(List<PrivacyDataModel> list) {
        List<List<PrivacyDataModel>> d = rrd.d(list, new DivideConditional() { // from class: rlg
            @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.util.DivideConditional
            public final boolean isDivide(Object obj, Object obj2) {
                return BaseOperate.lambda$groupByDay$28((PrivacyDataModel) obj, (PrivacyDataModel) obj2);
            }
        });
        ArrayList arrayList = new ArrayList(10);
        for (List<PrivacyDataModel> list2 : d) {
            if (!koq.b(list2)) {
                rsg rsgVar = new rsg();
                rsgVar.b(list2);
                rsgVar.e(mlg.a(list2.get(0).getStartTime(), Integer.MAX_VALUE));
                rsgVar.d(list2.get(0).getStartTime());
                arrayList.add(rsgVar);
            }
        }
        LogUtil.a(TAG, "groupByDay end, groupByDay list size=", Integer.valueOf(arrayList.size()));
        return arrayList;
    }

    public static /* synthetic */ boolean lambda$groupByDay$28(PrivacyDataModel privacyDataModel, PrivacyDataModel privacyDataModel2) {
        return !rsr.d(privacyDataModel.getStartTime(), privacyDataModel2.getStartTime());
    }

    protected List<rsb> doubleGroupSort(List<rsg> list) {
        List<List<rsg>> d = rrd.d(list, new DivideConditional() { // from class: rkw
            @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.util.DivideConditional
            public final boolean isDivide(Object obj, Object obj2) {
                return BaseOperate.this.m836x375d136a((rsg) obj, (rsg) obj2);
            }
        });
        ArrayList arrayList = new ArrayList(10);
        for (List<rsg> list2 : d) {
            if (!koq.b(list2)) {
                rsb rsbVar = new rsb();
                rsbVar.c(list2);
                rsbVar.e(mlg.a(list2.get(0).b(), 0));
                arrayList.add(rsbVar);
            }
        }
        LogUtil.a(TAG, "doubleGroupSort end, doubleGroupSort list size=", Integer.valueOf(arrayList.size()));
        return arrayList;
    }

    /* renamed from: lambda$doubleGroupSort$29$com-huawei-ui-main-stories-privacy-datasourcemanager-operate-data-BaseOperate, reason: not valid java name */
    public /* synthetic */ boolean m836x375d136a(rsg rsgVar, rsg rsgVar2) {
        return !this.sfYearMonth.format(Long.valueOf(rsgVar.b())).equals(this.sfYearMonth.format(Long.valueOf(rsgVar2.b())));
    }

    protected List<rsb> doubleGroupForCache(List<rsg> list, rkb rkbVar) {
        ArrayList arrayList = new ArrayList(10);
        rsb rsbVar = new rsb();
        if (koq.b(list)) {
            return arrayList;
        }
        long o = rkbVar.o();
        long h = rkbVar.h();
        if (isSameYear(o, h)) {
            rsbVar.e(mlg.a(o, Integer.MIN_VALUE) + Constants.LINK + mlg.a(h, 1));
        } else {
            rsbVar.e(mlg.a(o, Integer.MIN_VALUE) + Constants.LINK + mlg.a(h, Integer.MIN_VALUE));
        }
        rsbVar.c(list);
        arrayList.add(rsbVar);
        return arrayList;
    }

    private boolean isSameYear(long j, long j2) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
        String format = simpleDateFormat.format(Long.valueOf(j));
        String format2 = simpleDateFormat.format(Long.valueOf(j2));
        if (TextUtils.isEmpty(format) || TextUtils.isEmpty(format2)) {
            return false;
        }
        return format.equals(format2);
    }

    protected Map<Long, HiHealthData> listToMap(List<HiHealthData> list) {
        HashMap hashMap = new HashMap(16);
        if (list == null) {
            return hashMap;
        }
        for (HiHealthData hiHealthData : list) {
            if (!hashMap.containsKey(Long.valueOf(hiHealthData.getStartTime()))) {
                hashMap.put(Long.valueOf(hiHealthData.getStartTime()), hiHealthData);
            }
        }
        return hashMap;
    }

    protected void aggregateHiHealthDataEx(ArrayList<HiAggregateOption> arrayList, final DataSourceCallback<SparseArray<List<HiHealthData>>> dataSourceCallback) {
        ReleaseLogUtil.e(TAG_RELEASE, "aggregateHiHealthDataEx start");
        HiHealthManager.d(BaseApplication.getContext()).aggregateHiHealthDataEx(arrayList, new HiAggregateListenerEx() { // from class: rle
            @Override // com.huawei.hihealth.data.listener.HiAggregateListenerEx
            public final void onResult(SparseArray sparseArray, int i, int i2) {
                BaseOperate.lambda$aggregateHiHealthDataEx$30(DataSourceCallback.this, sparseArray, i, i2);
            }
        });
    }

    public static /* synthetic */ void lambda$aggregateHiHealthDataEx$30(DataSourceCallback dataSourceCallback, SparseArray sparseArray, int i, int i2) {
        ReleaseLogUtil.e(TAG_RELEASE, "aggregateHiHealthDataEx end errorCode = " + i);
        if (i != 0) {
            dataSourceCallback.onResponse(1, new SparseArray());
        } else if (sparseArray == null || sparseArray.size() == 0) {
            dataSourceCallback.onResponse(0, new SparseArray());
        } else {
            dataSourceCallback.onResponse(0, sparseArray);
        }
    }

    public void aggregateHiHealthDataPro(HiDataAggregateProOption hiDataAggregateProOption, final DataSourceCallback<List<HiHealthData>> dataSourceCallback) {
        ReleaseLogUtil.e(TAG_RELEASE, "aggregateHiHealthDataPro start");
        HiHealthNativeApi.a(BaseApplication.getContext()).aggregateHiHealthDataPro(hiDataAggregateProOption, new HiAggregateListener() { // from class: com.huawei.ui.main.stories.privacy.datasourcemanager.operate.data.BaseOperate.6
            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResultIntent(int i, List<HiHealthData> list, int i2, int i3) {
            }

            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResult(List<HiHealthData> list, int i, int i2) {
                ReleaseLogUtil.e(BaseOperate.TAG_RELEASE, "aggregateHiHealthDataPro end errorCode = " + i + ", anchor = " + i2);
                if (i != 0) {
                    dataSourceCallback.onResponse(1, new ArrayList());
                } else {
                    dataSourceCallback.onResponse(0, list);
                }
            }
        });
    }

    protected void aggregateHiHealthDataProEx(List<HiDataAggregateProOption> list, final DataSourceCallback<SparseArray<List<HiHealthData>>> dataSourceCallback) {
        ReleaseLogUtil.e(TAG_RELEASE, "aggregateHiHealthDataProEx start");
        HiHealthNativeApi.a(BaseApplication.getContext()).aggregateHiHealthDataProEx(list, new HiAggregateListenerEx() { // from class: com.huawei.ui.main.stories.privacy.datasourcemanager.operate.data.BaseOperate.7
            @Override // com.huawei.hihealth.data.listener.HiAggregateListenerEx
            public void onResult(SparseArray<List<HiHealthData>> sparseArray, int i, int i2) {
                ReleaseLogUtil.e(BaseOperate.TAG_RELEASE, "aggregateHiHealthDataProEx end errorCode = " + i + ", anchor = " + i2);
                if (i != 0) {
                    dataSourceCallback.onResponse(1, new SparseArray());
                } else {
                    dataSourceCallback.onResponse(0, sparseArray);
                }
            }
        });
    }

    protected void readHiHealthDataEx(List<HiDataReadProOption> list, final DataSourceCallback<SparseArray<List<HiHealthData>>> dataSourceCallback) {
        ReleaseLogUtil.e(TAG_RELEASE, "readHiHealthDataEx start");
        HiHealthNativeApi.a(BaseApplication.getContext()).readHiHealthDataEx(list, new HiDataReadResultListener() { // from class: com.huawei.ui.main.stories.privacy.datasourcemanager.operate.data.BaseOperate.2
            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i, Object obj, int i2, int i3) {
            }

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResult(Object obj, int i, int i2) {
                ReleaseLogUtil.e(BaseOperate.TAG_RELEASE, "readHiHealthDataEx end errorCode = " + i + ", anchor = " + i2);
                if (i != 0) {
                    dataSourceCallback.onResponse(1, new SparseArray());
                    return;
                }
                SparseArray sparseArray = new SparseArray();
                if (obj instanceof SparseArray) {
                    sparseArray = (SparseArray) obj;
                }
                dataSourceCallback.onResponse(0, sparseArray);
            }
        });
    }

    public void setIconResource(List<PrivacyDataModel> list) {
        if (list.size() == 0) {
            return;
        }
        HashSet hashSet = new HashSet(16);
        Iterator<PrivacyDataModel> it = list.iterator();
        while (it.hasNext()) {
            hashSet.add(Integer.valueOf(it.next().getClientId()));
        }
        HashMap<Integer, String> iconMap = getIconMap(rrb.a((HashSet<Integer>) hashSet));
        for (PrivacyDataModel privacyDataModel : list) {
            int clientId = privacyDataModel.getClientId();
            if (iconMap.containsKey(Integer.valueOf(clientId))) {
                privacyDataModel.putString("iconResource", iconMap.get(Integer.valueOf(clientId)));
            }
        }
    }

    protected HashMap<Integer, String> getIconMap(int[] iArr) {
        final HashMap<Integer, String> hashMap = new HashMap<>(16);
        for (int i : iArr) {
            hashMap.put(Integer.valueOf(i), String.valueOf(rrf.c()));
        }
        HiDataSourceFetchOption e = HiDataSourceFetchOption.builder().a(1).c(iArr).e();
        ReleaseLogUtil.e(TAG_RELEASE, "fetchDataSource start");
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        HiHealthNativeApi.a(this.mContext).fetchDataSource(e, new HiDataClientListener() { // from class: rky
            @Override // com.huawei.hihealth.data.listener.HiDataClientListener
            public final void onResult(List list) {
                BaseOperate.this.m837x871da6c6(countDownLatch, hashMap, list);
            }
        });
        try {
            countDownLatch.await();
        } catch (InterruptedException unused) {
            ReleaseLogUtil.c(TAG_RELEASE, "CountDownLatch Exception");
        }
        return hashMap;
    }

    /* renamed from: lambda$getIconMap$31$com-huawei-ui-main-stories-privacy-datasourcemanager-operate-data-BaseOperate, reason: not valid java name */
    public /* synthetic */ void m837x871da6c6(CountDownLatch countDownLatch, HashMap hashMap, List list) {
        Object[] objArr = new Object[1];
        StringBuilder sb = new StringBuilder("fetchDataSource end clientList size = ");
        sb.append(list == null ? 0 : list.size());
        objArr[0] = sb.toString();
        ReleaseLogUtil.e(TAG_RELEASE, objArr);
        if (koq.b(list)) {
            countDownLatch.countDown();
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            HiHealthClient hiHealthClient = (HiHealthClient) list.get(i);
            if (rrb.c(hiHealthClient)) {
                hashMap.put(Integer.valueOf(hiHealthClient.getClientId()), String.valueOf(rrf.d()));
            } else {
                String valueOf = hiHealthClient.getHiDeviceInfo() != null ? String.valueOf(hiHealthClient.getHiDeviceInfo().getDeviceType()) : "";
                if (this.pageType == 107) {
                    hashMap.put(Integer.valueOf(hiHealthClient.getClientId()), String.valueOf(rrf.a(valueOf)));
                } else {
                    hashMap.put(Integer.valueOf(hiHealthClient.getClientId()), String.valueOf(rrf.d(valueOf)));
                }
            }
        }
        countDownLatch.countDown();
    }

    protected int[] getDeleteDataTypes(rkb rkbVar) {
        return new int[0];
    }
}
