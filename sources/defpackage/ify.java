package defpackage;

import android.content.Context;
import android.os.RemoteException;
import com.huawei.hihealth.HiAccountInfo;
import com.huawei.hihealth.HiAggregateOption;
import com.huawei.hihealth.HiDataAggregateProOption;
import com.huawei.hihealth.HiDataDeleteOption;
import com.huawei.hihealth.HiDataDeleteProOption;
import com.huawei.hihealth.HiDataInsertOption;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiDataReadProOption;
import com.huawei.hihealth.HiDataSourceFetchOption;
import com.huawei.hihealth.HiSampleConfig;
import com.huawei.hihealth.HiSampleConfigProcessOption;
import com.huawei.hihealth.HiSportStatDataAggregateOption;
import com.huawei.hihealth.HiTimeInterval;
import com.huawei.hihealth.IAggregateListener;
import com.huawei.hihealth.IAggregateListenerEx;
import com.huawei.hihealth.ICommonListener;
import com.huawei.hihealth.IDataClientListener;
import com.huawei.hihealth.IDataOperateListener;
import com.huawei.hihealth.IDataReadResultListener;
import com.huawei.hihealth.IDeleteListenerEx;
import com.huawei.hihealth.IMultiDataClientListener;
import com.huawei.hihealth.data.constant.HiErrorCode;
import com.huawei.hihealthservice.InsertExecutor;
import com.huawei.hihealthservice.runnable.SingleRunnable;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes7.dex */
public final class ify {

    /* renamed from: a, reason: collision with root package name */
    private InsertExecutor f13355a;
    private final Object b;
    private Context c;
    private volatile ExecutorService d;
    private volatile ExecutorService e;

    private ify() {
        this.b = new Object();
    }

    static class d {
        private static final ify e = new ify();
    }

    public static ify e() {
        return d.e;
    }

    public void a(Context context, InsertExecutor insertExecutor) {
        if (this.c != null) {
            LogUtil.a("HiH_HiHealthApiManager", "already initialized");
            return;
        }
        if (context == null || insertExecutor == null) {
            LogUtil.b("HiH_HiHealthApiManager", "context or executor is null");
            throw new IllegalArgumentException("context or executor is null");
        }
        this.c = context.getApplicationContext();
        this.f13355a = insertExecutor;
        insertExecutor.execute(new SingleRunnable() { // from class: ify.1
            @Override // com.huawei.hihealthservice.runnable.SingleRunnable
            public void doRun() {
                try {
                    LogUtil.a("HiH_HiHealthApiManager", "getCurrentAppId, appId=", Integer.valueOf(igd.b().e(ify.this.c.getPackageName())), ", cost :", Long.valueOf(System.currentTimeMillis() - System.currentTimeMillis()));
                } catch (RemoteException e) {
                    ReleaseLogUtil.c("HiH_HiHealthApiManager", "initialize ex = ", e.getMessage());
                }
            }
        });
    }

    public void d(HiDataInsertOption hiDataInsertOption, IDataOperateListener iDataOperateListener, boolean z, boolean z2) throws RemoteException {
        ifo ifoVar = new ifo(this.c);
        if (ifoVar.a(hiDataInsertOption, iDataOperateListener, z, z2)) {
            if (z) {
                a().execute(ifoVar);
            } else {
                this.f13355a.execute(ifoVar);
            }
        }
    }

    public void b(HiDataDeleteOption hiDataDeleteOption, IDataOperateListener iDataOperateListener, boolean z, boolean z2) throws RemoteException {
        ifm ifmVar = new ifm(this.c);
        if (ifmVar.c(hiDataDeleteOption, iDataOperateListener, ifl.d(z2).c((Integer) 0).a((Integer) 0).d((Integer) 0).b())) {
            if (z) {
                ifmVar.run();
            } else {
                this.f13355a.execute(ifmVar);
            }
        }
    }

    public void e(HiDataReadOption hiDataReadOption, IDataReadResultListener iDataReadResultListener, boolean z) throws RemoteException {
        ifs ifsVar = new ifs(this.c);
        if (!ifsVar.e(hiDataReadOption, iDataReadResultListener, ifl.d(z).b((Integer) 0).b())) {
            LogUtil.h("HiH_HiHealthApiManager", "readHiHealthData initialize fail");
        } else {
            ifsVar.run();
        }
    }

    public void e(HiAggregateOption hiAggregateOption, IAggregateListener iAggregateListener, boolean z) throws RemoteException {
        ifj ifjVar = new ifj(this.c);
        if (!ifjVar.a(hiAggregateOption, iAggregateListener, ifl.d(z).b())) {
            LogUtil.h("HiH_HiHealthApiManager", "aggregateHiHealthData initialize fail");
        } else {
            ifjVar.run();
        }
    }

    public void a(List list, IAggregateListenerEx iAggregateListenerEx, boolean z) throws RemoteException {
        ifh ifhVar = new ifh(this.c);
        if (!ifhVar.a(list, iAggregateListenerEx, z)) {
            LogUtil.h("HiH_HiHealthApiManager", "aggregateHiHealthDataEx initialize fail");
        } else {
            ifhVar.run();
        }
    }

    public void c(ICommonListener iCommonListener, boolean z) throws RemoteException {
        igk igkVar = new igk(this.c);
        if (!igkVar.b(iCommonListener, z)) {
            LogUtil.h("HiH_HiHealthApiManager", "fetchUserData initialize fail");
        } else {
            igkVar.run();
        }
    }

    public void c(HiAccountInfo hiAccountInfo, ICommonListener iCommonListener) throws RemoteException {
        iga igaVar = new iga(this.c);
        if (!igaVar.c(hiAccountInfo, iCommonListener)) {
            LogUtil.h("HiH_HiHealthApiManager", "hiLogin initialize fail");
        } else {
            c().execute(igaVar);
            this.d.shutdown();
        }
    }

    public void e(ICommonListener iCommonListener) throws RemoteException {
        igc igcVar = new igc(this.c);
        if (!igcVar.a(iCommonListener)) {
            LogUtil.h("HiH_HiHealthApiManager", "hiLogout initialize fail");
        } else {
            c().execute(igcVar);
            this.d.shutdown();
        }
    }

    private ExecutorService a() {
        if (this.e == null || this.e.isShutdown()) {
            synchronized (this.b) {
                if (this.e == null || this.e.isShutdown()) {
                    this.e = Executors.newSingleThreadExecutor();
                }
            }
        }
        return this.e;
    }

    private ExecutorService c() {
        if (this.d == null || this.d.isShutdown()) {
            synchronized (this.b) {
                if (this.d == null || this.d.isShutdown()) {
                    this.d = Executors.newSingleThreadExecutor();
                }
            }
        }
        return this.d;
    }

    public void c(HiDataReadOption hiDataReadOption, ICommonListener iCommonListener, boolean z) throws RemoteException {
        ign ignVar = new ign(this.c);
        if (!ignVar.e(hiDataReadOption, iCommonListener, z)) {
            LogUtil.h("HiH_HiHealthApiManager", "fetchSportTypeList initialize fail");
        } else {
            ignVar.run();
        }
    }

    public void c(HiDataReadOption hiDataReadOption, IDataReadResultListener iDataReadResultListener, boolean z) throws RemoteException {
        igf igfVar = new igf(this.c);
        if (!igfVar.d(hiDataReadOption, iDataReadResultListener, z)) {
            LogUtil.h("HiH_HiHealthApiManager", "fetchSequenceDataBySportType initialize fail");
        } else {
            igfVar.run();
        }
    }

    public void c(HiSportStatDataAggregateOption hiSportStatDataAggregateOption, IAggregateListener iAggregateListener, boolean z) throws RemoteException {
        igi igiVar = new igi(this.c);
        if (!igiVar.a(hiSportStatDataAggregateOption, iAggregateListener, z)) {
            LogUtil.h("HiH_HiHealthApiManager", "aggregateSportStatData initialize fail");
        } else {
            igiVar.run();
        }
    }

    public void b(HiDataSourceFetchOption hiDataSourceFetchOption, IDataClientListener iDataClientListener, boolean z) throws RemoteException {
        ift iftVar = new ift(this.c);
        if (!iftVar.c(hiDataSourceFetchOption, iDataClientListener, z)) {
            LogUtil.h("HiH_HiHealthApiManager", "fetchDataSource initialize fail");
        } else {
            iftVar.run();
        }
    }

    public void e(HiDataReadProOption hiDataReadProOption, IDataReadResultListener iDataReadResultListener, boolean z) throws RemoteException {
        if (hiDataReadProOption == null) {
            ReleaseLogUtil.c("HiH_HiHealthApiManager", "readProOption is null ");
            igb.b(iDataReadResultListener, null, 7, 2);
            return;
        }
        ifs ifsVar = new ifs(this.c);
        if (!ifsVar.e(hiDataReadProOption.getDataReadOption(), iDataReadResultListener, ifl.d(z).d(hiDataReadProOption.getPackageName()).b(Integer.valueOf(hiDataReadProOption.getSequenceDataType())).e(hiDataReadProOption.getDataFilter()).a(hiDataReadProOption.getMetadataFilter()).b(hiDataReadProOption.isLastDayDetail()).c(hiDataReadProOption.isGroupByTime()).b())) {
            LogUtil.h("HiH_HiHealthApiManager", "readHiHealthData initialize fail");
        } else {
            ifsVar.run();
        }
    }

    public void a(List list, IDataReadResultListener iDataReadResultListener, boolean z) throws RemoteException {
        ifk ifkVar = new ifk(this.c);
        if (!ifkVar.d(list, iDataReadResultListener, z)) {
            LogUtil.h("HiH_HiHealthApiManager", "readHiHealthDataEx initialize fail");
        } else {
            ifkVar.run();
        }
    }

    public void b(HiDataAggregateProOption hiDataAggregateProOption, IAggregateListener iAggregateListener, boolean z) throws RemoteException {
        if (hiDataAggregateProOption == null) {
            ReleaseLogUtil.c("HiH_HiHealthApiManager", "aggregateProOption is null ");
            igb.d(iAggregateListener, null, 7, 2);
            return;
        }
        ifj ifjVar = new ifj(this.c);
        if (!ifjVar.a(hiDataAggregateProOption.getAggregateOption(), iAggregateListener, ifl.d(z).d(hiDataAggregateProOption.getPackageName()).e(hiDataAggregateProOption.getDataFilter()).a(hiDataAggregateProOption.getWithoutDefaultZero()).b())) {
            LogUtil.h("HiH_HiHealthApiManager", "aggregateHiHealthData initialize fail");
        } else {
            ifjVar.run();
        }
    }

    public void b(List<HiDataAggregateProOption> list, IAggregateListenerEx iAggregateListenerEx, boolean z) throws RemoteException {
        ifg ifgVar = new ifg(this.c);
        if (!ifgVar.d(list, iAggregateListenerEx, z)) {
            LogUtil.h("HiH_HiHealthApiManager", "aggregateHiHealthDataEx initialize fail");
        } else {
            ifgVar.run();
        }
    }

    public void e(HiDataDeleteProOption hiDataDeleteProOption, IDataOperateListener iDataOperateListener, boolean z, boolean z2) throws RemoteException {
        if (hiDataDeleteProOption == null) {
            ReleaseLogUtil.c("HiH_HiHealthApiManager", "deleteProOption is null ");
            ArrayList arrayList = new ArrayList(10);
            arrayList.add(HiErrorCode.d(7));
            igb.b(iDataOperateListener, 7, arrayList);
            return;
        }
        ifm ifmVar = new ifm(this.c);
        if (ifmVar.c(hiDataDeleteProOption.getDataDeleteOption(), iDataOperateListener, ifl.d(z2).d(hiDataDeleteProOption.getPackageName()).c(hiDataDeleteProOption.getDeleteType()).a(hiDataDeleteProOption.getDeleteLevel()).b(hiDataDeleteProOption.getDeviceUuid()).d(hiDataDeleteProOption.getDeleteInterval()).b())) {
            if (z) {
                ifmVar.run();
            } else {
                this.f13355a.execute(ifmVar);
            }
        }
    }

    public void e(List<HiDataDeleteProOption> list, IDeleteListenerEx iDeleteListenerEx, boolean z, boolean z2) throws RemoteException {
        ifn ifnVar = new ifn(this.c);
        if (!ifnVar.b(list, iDeleteListenerEx, z2)) {
            LogUtil.h("HiH_HiHealthApiManager", "deleteHiHealthDataProEx initialize fail");
        } else if (z) {
            ifnVar.run();
        } else {
            this.f13355a.execute(ifnVar);
        }
    }

    public void e(List<HiSampleConfig> list, IDataOperateListener iDataOperateListener, boolean z) throws RemoteException {
        igg iggVar = new igg(this.c);
        if (!iggVar.d(list, iDataOperateListener, z)) {
            LogUtil.h("HiH_HiHealthApiManager", "sampleConfigSetOperation initialize fail");
        } else {
            this.f13355a.execute(iggVar);
        }
    }

    public void a(HiSampleConfigProcessOption hiSampleConfigProcessOption, IDataReadResultListener iDataReadResultListener, boolean z) throws RemoteException {
        ige igeVar = new ige(this.c);
        if (!igeVar.e(hiSampleConfigProcessOption, iDataReadResultListener, z)) {
            LogUtil.h("HiH_HiHealthApiManager", "sampleConfigGetOperation initialize fail");
        } else {
            igeVar.run();
        }
    }

    public void c(HiSampleConfigProcessOption hiSampleConfigProcessOption, IDataOperateListener iDataOperateListener, boolean z) throws RemoteException {
        igh ighVar = new igh(this.c);
        if (!ighVar.c(hiSampleConfigProcessOption, iDataOperateListener, z)) {
            LogUtil.h("HiH_HiHealthApiManager", "sampleConfigDeleteOperation initialize fail");
        } else {
            this.f13355a.execute(ighVar);
        }
    }

    public void a(int i, HiTimeInterval hiTimeInterval, final IDataClientListener iDataClientListener) throws RemoteException {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(Integer.valueOf(i));
        c(arrayList, hiTimeInterval, true, new IMultiDataClientListener.Stub() { // from class: ify.4
            @Override // com.huawei.hihealth.IMultiDataClientListener
            public void onMultiTypeResult(Map map) throws RemoteException {
            }

            @Override // com.huawei.hihealth.IMultiDataClientListener
            public void onMergeTypeResult(List list) throws RemoteException {
                iDataClientListener.onResult(list);
            }
        });
    }

    public void c(List list, HiTimeInterval hiTimeInterval, boolean z, IMultiDataClientListener iMultiDataClientListener) throws RemoteException {
        ifw ifwVar = new ifw(this.c);
        if (!ifwVar.b(list, hiTimeInterval, z, iMultiDataClientListener)) {
            LogUtil.h("HiH_HiHealthApiManager", "fetchDataSourceByTypes initialize fail");
        } else {
            ifwVar.run();
        }
    }
}
