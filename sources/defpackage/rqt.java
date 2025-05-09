package defpackage;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.google.android.gms.common.util.ArrayUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.hihealth.HiAppInfo;
import com.huawei.hihealth.HiDataSourceFetchOption;
import com.huawei.hihealth.HiDeviceInfo;
import com.huawei.hihealth.HiHealthClient;
import com.huawei.hihealth.HiTimeInterval;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiDataClientListener;
import com.huawei.hihealth.data.listener.HiMultiDataClientListener;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.privacy.datasourcemanager.DataSourceCallback;
import com.huawei.ui.main.stories.privacy.datasourcemanager.bean.SourceInfo;
import defpackage.rqt;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* loaded from: classes7.dex */
public class rqt {
    private static volatile rqt d;

    /* renamed from: a, reason: collision with root package name */
    private int f16883a;
    private final Context b;
    private final Handler c;
    private int e;
    private Map<Integer, int[]> g;

    public rqt() {
        HashMap hashMap = new HashMap();
        this.g = hashMap;
        hashMap.put(103, new int[]{22107, 22106, 22101, 22102, 22103, 22105, 22001, 22002});
        this.g.put(102, new int[]{2002, 2018});
        this.g.put(106, new int[]{2104, DicDataTypeUtil.DataType.BODY_TEMPERATURE.value(), DicDataTypeUtil.DataType.SKIN_TEMPERATURE.value(), DicDataTypeUtil.DataType.SUSPECTED_HIGH_TEMPERATURE.value()});
        this.g.put(113, new int[]{DicDataTypeUtil.DataType.SKIN_TEMPERATURE.value()});
        Context context = BaseApplication.getContext();
        this.b = context;
        this.c = new Handler(Looper.getMainLooper());
        rrf.c(context);
    }

    public static rqt e() {
        if (d == null) {
            synchronized (rqt.class) {
                if (d == null) {
                    d = new rqt();
                }
            }
        }
        return d;
    }

    public void b(final DataSourceCallback<List<SourceInfo>> dataSourceCallback) {
        HiDataSourceFetchOption e = HiDataSourceFetchOption.builder().a(0).e();
        ReleaseLogUtil.e("R_SourceManager", "getSource start");
        HiHealthNativeApi.a(this.b).fetchDataSource(e, new HiDataClientListener() { // from class: rqr
            @Override // com.huawei.hihealth.data.listener.HiDataClientListener
            public final void onResult(List list) {
                rqt.this.a(dataSourceCallback, list);
            }
        });
    }

    /* synthetic */ void a(final DataSourceCallback dataSourceCallback, final List list) {
        LogUtil.a("SourceManager", "getSource end clientList = ", list);
        Object[] objArr = new Object[2];
        objArr[0] = "getSource end size = ";
        objArr[1] = Integer.valueOf(list != null ? list.size() : 0);
        ReleaseLogUtil.e("R_SourceManager", objArr);
        if (koq.b(list)) {
            this.c.post(new Runnable() { // from class: rqy
                @Override // java.lang.Runnable
                public final void run() {
                    DataSourceCallback.this.onResponse(0, new ArrayList());
                }
            });
        } else {
            this.c.post(new Runnable() { // from class: rqw
                @Override // java.lang.Runnable
                public final void run() {
                    rqt.this.b(dataSourceCallback, list);
                }
            });
        }
    }

    /* synthetic */ void b(DataSourceCallback dataSourceCallback, List list) {
        dataSourceCallback.onResponse(0, b((List<HiHealthClient>) list));
    }

    /* renamed from: rqt$1, reason: invalid class name */
    class AnonymousClass1 implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ DataSourceCallback f16884a;
        final /* synthetic */ int c;

        AnonymousClass1(int i, DataSourceCallback dataSourceCallback) {
            this.c = i;
            this.f16884a = dataSourceCallback;
        }

        @Override // java.lang.Runnable
        public void run() {
            LogUtil.a("SourceManager", "getSourceByType start");
            rqt rqtVar = rqt.this;
            int i = this.c;
            long currentTimeMillis = System.currentTimeMillis();
            final DataSourceCallback dataSourceCallback = this.f16884a;
            rqtVar.a(i, 0L, currentTimeMillis, new DataSourceCallback() { // from class: rqx
                @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.DataSourceCallback
                public final void onResponse(int i2, Object obj) {
                    rqt.AnonymousClass1.this.b(dataSourceCallback, i2, (List) obj);
                }
            });
        }

        /* synthetic */ void b(final DataSourceCallback dataSourceCallback, final int i, List list) {
            Object[] objArr = new Object[4];
            objArr[0] = "getSourceByType end resultCode = ";
            objArr[1] = Integer.valueOf(i);
            objArr[2] = ", size = ";
            objArr[3] = Integer.valueOf(list != null ? list.size() : 0);
            LogUtil.a("SourceManager", objArr);
            if (i != 0 || koq.b(list)) {
                rqt.this.c.post(new Runnable() { // from class: rra
                    @Override // java.lang.Runnable
                    public final void run() {
                        DataSourceCallback.this.onResponse(i, new ArrayList());
                    }
                });
                return;
            }
            final List<SourceInfo> b = rrf.b((List<SourceInfo>) rqt.this.b((List<HiHealthClient>) list));
            rrf.c(b);
            rqt.this.c.post(new Runnable() { // from class: rqz
                @Override // java.lang.Runnable
                public final void run() {
                    DataSourceCallback.this.onResponse(i, b);
                }
            });
        }
    }

    public void c(int i, DataSourceCallback<List<SourceInfo>> dataSourceCallback) {
        ThreadPoolManager.d().execute(new AnonymousClass1(i, dataSourceCallback));
    }

    public void a(int i, long j, long j2, DataSourceCallback<List<HiHealthClient>> dataSourceCallback) {
        final ArrayList arrayList = new ArrayList(10);
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        int[] iArr = this.g.get(Integer.valueOf(i));
        ReleaseLogUtil.e("R_SourceManager", "getClient start");
        HiHealthNativeApi.a(this.b).fetchDataSourceByTypes(ArrayUtils.toArrayList(ArrayUtils.toWrapperArray(iArr)), new HiTimeInterval(j, j2), true, new HiMultiDataClientListener() { // from class: rqt.3
            @Override // com.huawei.hihealth.data.listener.HiMultiDataClientListener
            public void onMultiTypeResult(Map<Integer, List<HiHealthClient>> map) {
            }

            @Override // com.huawei.hihealth.data.listener.HiMultiDataClientListener
            public void onMergeTypeResult(List<HiHealthClient> list) {
                Object[] objArr = new Object[2];
                objArr[0] = "getClient end size = ";
                objArr[1] = Integer.valueOf(list != null ? list.size() : 0);
                ReleaseLogUtil.e("R_SourceManager", objArr);
                if (koq.c(list)) {
                    arrayList.addAll(list);
                }
                countDownLatch.countDown();
            }
        });
        try {
            boolean await = countDownLatch.await(5000L, TimeUnit.MILLISECONDS);
            ReleaseLogUtil.e("R_SourceManager", "getClient isCountDownZero = ", Boolean.valueOf(await));
            if (!await) {
                dataSourceCallback.onResponse(2, arrayList);
                return;
            }
        } catch (InterruptedException unused) {
            ReleaseLogUtil.d("R_SourceManager", "countDownLatch await exception");
        }
        if (a(iArr)) {
            spx.a(arrayList);
        }
        dataSourceCallback.onResponse(0, arrayList);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<SourceInfo> b(List<HiHealthClient> list) {
        ArrayList arrayList = new ArrayList(10);
        ArrayList arrayList2 = new ArrayList(10);
        SourceInfo sourceInfo = new SourceInfo();
        LogUtil.a("SourceManager", "distinguishSource start");
        for (HiHealthClient hiHealthClient : list) {
            if (hiHealthClient == null) {
                LogUtil.h("SourceManager", "distinguishSource healthClient is null");
            } else {
                HiAppInfo hiAppInfo = hiHealthClient.getHiAppInfo();
                if (hiAppInfo == null) {
                    LogUtil.h("SourceManager", "distinguishSource hiAppInfo is null");
                } else {
                    String packageName = hiAppInfo.getPackageName();
                    HiDeviceInfo hiDeviceInfo = hiHealthClient.getHiDeviceInfo();
                    if (hiDeviceInfo == null) {
                        LogUtil.h("SourceManager", "distinguishSource hiDeviceInfo is null");
                    } else {
                        String deviceUniqueCode = hiDeviceInfo.getDeviceUniqueCode();
                        LogUtil.a("SourceManager", "packageName=", packageName, " deviceUniqueCode=", deviceUniqueCode);
                        if (rrb.c(packageName, deviceUniqueCode)) {
                            sourceInfo.setSourceType(3);
                            sourceInfo.setPackageName(packageName);
                            sourceInfo.setDeviceUniqueCode(deviceUniqueCode);
                            sourceInfo.setDeviceType(Integer.MIN_VALUE);
                            sourceInfo.setDeviceName(BaseApplication.getContext().getResources().getString(R$string.IDS_hw_health_show_healthdata_input));
                            sourceInfo.setItemType(3);
                            LogUtil.a("SourceManager", "add to maual");
                        } else {
                            if (rrf.c(packageName)) {
                                arrayList.add(rrf.e(hiAppInfo));
                                LogUtil.a("SourceManager", "add to App");
                            }
                            if (rrf.b(deviceUniqueCode)) {
                                arrayList2.add(rrf.d(hiDeviceInfo));
                                LogUtil.a("SourceManager", "add to Device");
                            }
                        }
                    }
                }
            }
        }
        LogUtil.a("SourceManager", "distinguishSource end");
        rrf.e(arrayList);
        rrf.d(arrayList2);
        LogUtil.a("SourceManager", "appList size" + arrayList.size() + ", deviceList size" + arrayList2.size());
        return c(rrf.a(arrayList), rrf.a(arrayList2), sourceInfo);
    }

    private List<SourceInfo> c(List<SourceInfo> list, List<SourceInfo> list2, SourceInfo sourceInfo) {
        ArrayList arrayList = new ArrayList(10);
        if (koq.c(list)) {
            SourceInfo sourceInfo2 = new SourceInfo();
            sourceInfo2.setItemType(2);
            sourceInfo2.setTitle(this.b.getResources().getString(R$string.IDS_hwh_info_app));
            arrayList.add(sourceInfo2);
            arrayList.addAll(list);
            this.e = list.size();
            if (koq.b(list2)) {
                SourceInfo sourceInfo3 = new SourceInfo();
                sourceInfo3.setItemType(1);
                arrayList.add(sourceInfo3);
            }
        }
        if (koq.c(list2)) {
            SourceInfo sourceInfo4 = new SourceInfo();
            sourceInfo4.setItemType(2);
            sourceInfo4.setTitle(this.b.getResources().getString(R$string.IDS_hwh_info_device));
            arrayList.add(sourceInfo4);
            arrayList.addAll(list2);
            this.f16883a = list2.size();
            SourceInfo sourceInfo5 = new SourceInfo();
            sourceInfo5.setItemType(1);
            arrayList.add(sourceInfo5);
        }
        if ("-1".equals(sourceInfo.getDeviceUniqueCode())) {
            arrayList.add(sourceInfo);
            SourceInfo sourceInfo6 = new SourceInfo();
            sourceInfo6.setItemType(1);
            arrayList.add(sourceInfo6);
        }
        return arrayList;
    }

    public void c(int i, String str, String str2, int i2, DataSourceCallback<rjz> dataSourceCallback) {
        if (dataSourceCallback == null) {
            LogUtil.b("SourceManager", "Parameter callback is null");
        } else if (TextUtils.isEmpty(str) && TextUtils.isEmpty(str2)) {
            LogUtil.b("SourceManager", "Parameter uuid is null");
            dataSourceCallback.onResponse(1, null);
        } else {
            new rqp().c(i, str, str2, i2, dataSourceCallback);
        }
    }

    public int a() {
        return this.e;
    }

    public int c() {
        return this.f16883a;
    }

    private boolean a(int[] iArr) {
        int i = iArr[0];
        return i == 2002 || i == 2018;
    }
}
