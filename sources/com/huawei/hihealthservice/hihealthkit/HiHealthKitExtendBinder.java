package com.huawei.hihealthservice.hihealthkit;

import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.RemoteException;
import android.text.TextUtils;
import com.autonavi.amap.mapcore.AMapEngineUtils;
import com.huawei.health.IBaseCommonCallback;
import com.huawei.health.ITrackDataForDeveloper;
import com.huawei.hihealth.CharacteristicConstant;
import com.huawei.hihealth.DataReportModel;
import com.huawei.hihealth.HealthKitDictQuery;
import com.huawei.hihealth.HiAppInfo;
import com.huawei.hihealth.HiHealthAggregateQuery;
import com.huawei.hihealth.HiHealthDataQuery;
import com.huawei.hihealth.HiHealthKitData;
import com.huawei.hihealth.HiUserPreference;
import com.huawei.hihealth.HiUserPreferenceData;
import com.huawei.hihealth.IBaseCallback;
import com.huawei.hihealth.ICommonCallback;
import com.huawei.hihealth.ICommonListener;
import com.huawei.hihealth.IDataOperateListener;
import com.huawei.hihealth.IDataReadResultListener;
import com.huawei.hihealth.IHiHealthKitEx;
import com.huawei.hihealth.IReadCallback;
import com.huawei.hihealth.IRealTimeDataCallback;
import com.huawei.hihealth.IRegisterRealTimeCallback;
import com.huawei.hihealth.ISportDataCallback;
import com.huawei.hihealth.ISubScribeCallback;
import com.huawei.hihealth.IWriteCallback;
import com.huawei.hihealth.StartSportParam;
import com.huawei.hihealth.TrendQuery;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.type.HiHealthDataType;
import com.huawei.hihealth.model.Notification;
import com.huawei.hihealth.model.SubscribeModel;
import com.huawei.hihealth.option.HiHealthCapabilityQuery;
import com.huawei.hihealthkit.context.H5ProAppInfo;
import com.huawei.hihealthkit.context.OutOfBandData;
import com.huawei.hihealthkit.context.QuickAppInfo;
import com.huawei.hihealthservice.InsertExecutor;
import com.huawei.hihealthservice.hihealthkit.HiHealthKitExtendBinder;
import com.huawei.hihealthservice.hihealthkit.util.AppStatusHelper;
import com.huawei.hihealthservice.hihealthkit.util.HiHealthKitDataChecker;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.MapKeyNames;
import com.huawei.operation.ble.BleConstants;
import com.huawei.up.utils.Constants;
import defpackage.Cint;
import defpackage.ify;
import defpackage.iip;
import defpackage.ikx;
import defpackage.ima;
import defpackage.ini;
import defpackage.ino;
import defpackage.inp;
import defpackage.inq;
import defpackage.ins;
import defpackage.iov;
import defpackage.iox;
import defpackage.ioy;
import defpackage.ipd;
import defpackage.iqr;
import defpackage.iqw;
import defpackage.iqx;
import defpackage.iqy;
import defpackage.iqz;
import defpackage.irc;
import defpackage.irg;
import defpackage.irn;
import defpackage.irp;
import defpackage.ivw;
import defpackage.iwi;
import defpackage.iwu;
import defpackage.koq;
import health.compact.a.AuthorizationUtils;
import health.compact.a.HiCommonUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class HiHealthKitExtendBinder extends IHiHealthKitEx.Stub {

    /* renamed from: a, reason: collision with root package name */
    private AppStatusHelper f4164a;
    private QuickAppInfo c;
    private IBaseCallback f;
    private ima g;
    private Handler h;
    private InsertExecutor i;
    private Context j;
    private static final String d = BaseApplication.getAppPackage();
    private static final byte[] e = new byte[0];
    private static final List b = new ArrayList(0);
    private HandlerThread l = new HandlerThread("HiHealthKitBinder");
    private Bundle o = new Bundle();
    private ConcurrentHashMap<Integer, ITrackDataForDeveloper> k = new ConcurrentHashMap<>();

    /* loaded from: classes7.dex */
    public interface Action {
        void operate(int i) throws RemoteException;
    }

    public HiHealthKitExtendBinder(Context context, InsertExecutor insertExecutor) {
        if (context == null || insertExecutor == null) {
            throw new iwu("HiHealthKitExtendBinder() param is null");
        }
        this.j = context;
        this.g = ima.a();
        this.i = insertExecutor;
        insertExecutor.execute(new Runnable() { // from class: ing
            @Override // java.lang.Runnable
            public final void run() {
                HiHealthKitExtendBinder.this.c();
            }
        });
        this.f4164a = new AppStatusHelper(this.j);
        this.l.start();
        this.h = new Handler(this.l.getLooper()) { // from class: com.huawei.hihealthservice.hihealthkit.HiHealthKitExtendBinder.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                super.handleMessage(message);
                if (message.what != 101) {
                    return;
                }
                try {
                    HiHealthKitExtendBinder.this.bBM_(message);
                } catch (RemoteException e2) {
                    ReleaseLogUtil.c("HiH_HiHlthKitExtBnd", "handleMessage RemoteException = ", e2.getMessage());
                }
            }
        };
        irn.b(context).c();
        ify.e().a(context, insertExecutor);
    }

    public /* synthetic */ void c() {
        try {
            this.g.e();
        } catch (RemoteException e2) {
            ReleaseLogUtil.c("HiH_HiHlthKitExtBnd", "HiHealthKitBinder() getCurrentAppId e = ", e2.getMessage());
        }
    }

    @Override // com.huawei.hihealth.IHiHealthKitEx
    public void getGender(OutOfBandData outOfBandData, ICommonListener iCommonListener) throws RemoteException {
        a(outOfBandData, iCommonListener, "getGender");
    }

    @Override // com.huawei.hihealth.IHiHealthKitEx
    public void getBirthday(OutOfBandData outOfBandData, ICommonListener iCommonListener) throws RemoteException {
        a(outOfBandData, iCommonListener, "getBirthday");
    }

    private void a(OutOfBandData outOfBandData, final ICommonListener iCommonListener, final String str) throws RemoteException {
        d(str);
        if (iCommonListener == null) {
            ReleaseLogUtil.c("HiH_HiHlthKitExtBnd", str, " callback is null");
            return;
        }
        final String e2 = e(outOfBandData);
        final String b2 = b(outOfBandData);
        final irc ircVar = new irc();
        if (c(iov.b(this.j, outOfBandData, this.f4164a, 101001, 1), true, new Action() { // from class: imv
            @Override // com.huawei.hihealthservice.hihealthkit.HiHealthKitExtendBinder.Action
            public final void operate(int i) {
                HiHealthKitExtendBinder.this.b(ircVar, e2, b2, str, iCommonListener, i);
            }
        })) {
            return;
        }
        HiHealthKitQueryHelper hiHealthKitQueryHelper = new HiHealthKitQueryHelper(this.j, e2, b2);
        if ("getGender".equals(str) || "getBirthday".equals(str)) {
            hiHealthKitQueryHelper.c(ircVar, iCommonListener, str);
        } else {
            ReleaseLogUtil.c("HiH_HiHlthKitExtBnd", "getBasicUserInfo unrecognized dataType");
        }
    }

    public /* synthetic */ void b(irc ircVar, String str, String str2, String str3, ICommonListener iCommonListener, int i) throws RemoteException {
        b(ircVar, str, str2, str3, i);
        iCommonListener.onFailure(i, null);
    }

    private boolean c(int i, boolean z, Action action) throws RemoteException {
        if (i == 0 && (!z || !ima.b())) {
            return false;
        }
        if (i == 0) {
            i = 1001;
        }
        action.operate(i);
        return true;
    }

    private void e(OutOfBandData outOfBandData, final ICommonListener iCommonListener, final String str) throws RemoteException {
        d(str);
        if (iCommonListener == null) {
            ReleaseLogUtil.c("HiH_HiHlthKitExtBnd", str, " callback is null");
            return;
        }
        final String e2 = e(outOfBandData);
        final String b2 = b(outOfBandData);
        final irc ircVar = new irc();
        if (c(iov.b(this.j, outOfBandData, this.f4164a, 101002, 1), true, new Action() { // from class: ilz
            @Override // com.huawei.hihealthservice.hihealthkit.HiHealthKitExtendBinder.Action
            public final void operate(int i) {
                HiHealthKitExtendBinder.this.a(ircVar, e2, b2, str, iCommonListener, i);
            }
        })) {
            return;
        }
        HiHealthKitQueryHelper hiHealthKitQueryHelper = new HiHealthKitQueryHelper(this.j, e2, b2);
        if ("getWeight".equals(str) || "getHeight".equals(str)) {
            hiHealthKitQueryHelper.c(ircVar, iCommonListener, str);
        } else {
            LogUtil.b("HiHlthKitExtBnd", "getBasicUserFeature unrecognized dataType");
        }
    }

    public /* synthetic */ void a(irc ircVar, String str, String str2, String str3, ICommonListener iCommonListener, int i) throws RemoteException {
        b(ircVar, str, str2, str3, i);
        iCommonListener.onFailure(i, null);
    }

    @Override // com.huawei.hihealth.IHiHealthKitEx
    public void getWeight(OutOfBandData outOfBandData, ICommonListener iCommonListener) throws RemoteException {
        e(outOfBandData, iCommonListener, "getWeight");
    }

    @Override // com.huawei.hihealth.IHiHealthKitEx
    public void getHeight(OutOfBandData outOfBandData, ICommonListener iCommonListener) throws RemoteException {
        e(outOfBandData, iCommonListener, "getHeight");
    }

    @Override // com.huawei.hihealth.IHiHealthKitEx
    public void execQuery(OutOfBandData outOfBandData, HiHealthDataQuery hiHealthDataQuery, int i, IDataReadResultListener iDataReadResultListener) throws RemoteException {
        c(outOfBandData, hiHealthDataQuery, iDataReadResultListener, "execQuery");
    }

    private void c(OutOfBandData outOfBandData, HiHealthDataQuery hiHealthDataQuery, final IDataReadResultListener iDataReadResultListener, String str) throws RemoteException {
        d(str);
        final irc ircVar = new irc();
        String e2 = e(outOfBandData);
        String b2 = b(outOfBandData);
        if (iDataReadResultListener == null) {
            b(ircVar, e2, b2, str, 2);
            return;
        }
        if (hiHealthDataQuery == null || e2 == null) {
            iDataReadResultListener.onResult(null, 2, 2);
            b(ircVar, e2, b2, str, 2);
            ReleaseLogUtil.d("HiH_HiHlthKitExtBnd", "execQuery query or package name is null");
        } else {
            if (HiHealthKitDataChecker.a(hiHealthDataQuery.getStartTime(), hiHealthDataQuery.getEndTime())) {
                iDataReadResultListener.onResult(null, 2, 2);
                b(ircVar, e2, b2, str, 2);
                return;
            }
            final int sampleType = hiHealthDataQuery.getSampleType();
            final iqy iqyVar = new iqy(str, 0, e2, b2, String.valueOf(sampleType));
            if (c(iov.b(this.j, outOfBandData, this.f4164a, sampleType, 1), false, new Action() { // from class: ims
                @Override // com.huawei.hihealthservice.hihealthkit.HiHealthKitExtendBinder.Action
                public final void operate(int i) {
                    HiHealthKitExtendBinder.this.b(sampleType, iDataReadResultListener, ircVar, iqyVar, i);
                }
            })) {
                return;
            }
            HiHealthKitQueryHelper hiHealthKitQueryHelper = new HiHealthKitQueryHelper(this.j, e2, b2);
            iqr.c(this.j, outOfBandData, str, iwi.c(e2, false));
            boolean a2 = iov.a(outOfBandData, this.j, this.f4164a);
            String a3 = iwi.a(this.j);
            hiHealthKitQueryHelper.c(hiHealthDataQuery, iDataReadResultListener, new Cint(a2, str, iqz.c(a3) <= 5 ? null : a3, String.valueOf(sampleType)));
        }
    }

    public /* synthetic */ void b(int i, IDataReadResultListener iDataReadResultListener, irc ircVar, iqy iqyVar, int i2) throws RemoteException {
        LogUtil.h("HiHlthKitExtBnd", "scope fails ", Integer.valueOf(i), ipd.b(i2));
        iDataReadResultListener.onResult(null, i2, 2);
        ircVar.c(this.j, iqyVar.b(i2));
    }

    @Override // com.huawei.hihealth.IHiHealthKitEx
    public void queryData(OutOfBandData outOfBandData, HealthKitDictQuery healthKitDictQuery, final IDataReadResultListener iDataReadResultListener) throws RemoteException {
        List list;
        final irc ircVar = new irc();
        d("queryData");
        String e2 = e(outOfBandData);
        String b2 = b(outOfBandData);
        if (iDataReadResultListener == null) {
            b(ircVar, e2, b2, "queryData", 2);
            return;
        }
        if (healthKitDictQuery == null || e2 == null) {
            iDataReadResultListener.onResult(null, 2, 2);
            b(ircVar, e2, b2, "queryData", 2);
            ReleaseLogUtil.d("HiH_HiHlthKitExtBnd", "queryData query or package name is null");
            return;
        }
        int sampleType = healthKitDictQuery.getSampleType();
        if (!iqz.c(sampleType)) {
            ReleaseLogUtil.d("HiH_HiHlthKitExtBnd", "queryData type is not support.");
            iDataReadResultListener.onResult(null, 30, 2);
            b(ircVar, e2, b2, "queryData", 30);
            return;
        }
        if (HiHealthKitDataChecker.a(healthKitDictQuery.getStartTime(), healthKitDictQuery.getEndTime())) {
            list = null;
        } else {
            if (HiHealthKitDataChecker.e(healthKitDictQuery)) {
                final iqy iqyVar = new iqy("queryData", 0, e2, b2, String.valueOf(sampleType));
                if (c(iov.b(this.j, outOfBandData, this.f4164a, sampleType, 1), false, new Action() { // from class: imh
                    @Override // com.huawei.hihealthservice.hihealthkit.HiHealthKitExtendBinder.Action
                    public final void operate(int i) {
                        HiHealthKitExtendBinder.this.a(iDataReadResultListener, ircVar, iqyVar, i);
                    }
                })) {
                    return;
                }
                Context context = this.j;
                inp inpVar = new inp(context, iwi.a(context), ircVar);
                boolean booleanValue = healthKitDictQuery.getBoolean("dataSourceQuery").booleanValue();
                boolean booleanValue2 = healthKitDictQuery.getBoolean("aggregateQuery").booleanValue();
                ReleaseLogUtil.e("HiH_HiHlthKitExtBnd", "queryData isDatasourceQuery: ", Boolean.valueOf(booleanValue), ", isAggregateQuery: ", Boolean.valueOf(booleanValue2));
                if (booleanValue) {
                    inpVar.e(healthKitDictQuery, iDataReadResultListener, new Cint(iov.a(outOfBandData, this.j, this.f4164a), "queryData", String.valueOf(sampleType)));
                    return;
                } else if (booleanValue2) {
                    inpVar.d(healthKitDictQuery, iDataReadResultListener, new Cint(iov.a(outOfBandData, this.j, this.f4164a), "queryData", String.valueOf(sampleType)));
                    return;
                } else {
                    inpVar.b(healthKitDictQuery, iDataReadResultListener, new Cint(iov.a(outOfBandData, this.j, this.f4164a), "queryData", String.valueOf(sampleType)));
                    return;
                }
            }
            list = null;
        }
        iDataReadResultListener.onResult(list, 2, 2);
        b(ircVar, e2, b2, "queryData", 2);
    }

    public /* synthetic */ void a(IDataReadResultListener iDataReadResultListener, irc ircVar, iqy iqyVar, int i) throws RemoteException {
        LogUtil.h("HiHlthKitExtBnd", ipd.b(i));
        iDataReadResultListener.onResult(null, i, 2);
        ircVar.c(this.j, iqyVar.b(i));
    }

    private String e(OutOfBandData outOfBandData) {
        if (outOfBandData instanceof H5ProAppInfo) {
            return ((H5ProAppInfo) outOfBandData).getPkgName();
        }
        if (outOfBandData instanceof QuickAppInfo) {
            return ((QuickAppInfo) outOfBandData).getPackageName();
        }
        return null;
    }

    private String b(OutOfBandData outOfBandData) {
        if (outOfBandData instanceof H5ProAppInfo) {
            return ((H5ProAppInfo) outOfBandData).getAppId();
        }
        if (outOfBandData instanceof QuickAppInfo) {
            return ((QuickAppInfo) outOfBandData).getAppId();
        }
        return null;
    }

    @Override // com.huawei.hihealth.IHiHealthKitEx
    public void execAggregateQuery(final OutOfBandData outOfBandData, HiHealthAggregateQuery hiHealthAggregateQuery, int i, final IDataReadResultListener iDataReadResultListener) throws RemoteException {
        d("execAggregateQuery");
        final irc ircVar = new irc();
        if (iDataReadResultListener == null) {
            ReleaseLogUtil.c("HiH_HiHlthKitExtBnd", "execAggregateQuery", " callback is null");
            return;
        }
        if (hiHealthAggregateQuery == null) {
            ReleaseLogUtil.c("HiH_HiHlthKitExtBnd", "execAggregateQuery query is null");
            iDataReadResultListener.onResult(null, 2, 2);
            return;
        }
        final String str = "execAggregateQuery";
        if (c(iov.c(this.j, outOfBandData, hiHealthAggregateQuery.getSampleType(), 1), false, new Action() { // from class: imi
            @Override // com.huawei.hihealthservice.hihealthkit.HiHealthKitExtendBinder.Action
            public final void operate(int i2) {
                HiHealthKitExtendBinder.this.e(ircVar, outOfBandData, str, iDataReadResultListener, i2);
            }
        })) {
            return;
        }
        QuickAppInfo quickAppInfo = (QuickAppInfo) outOfBandData;
        new HiHealthKitQueryHelper(this.j, quickAppInfo.getPackageName(), quickAppInfo.getAppId()).d(hiHealthAggregateQuery, iDataReadResultListener, new Cint(true, "execAggregateQuery", String.valueOf(hiHealthAggregateQuery.getSampleType())));
    }

    public /* synthetic */ void e(irc ircVar, OutOfBandData outOfBandData, String str, IDataReadResultListener iDataReadResultListener, int i) throws RemoteException {
        b(ircVar, e(outOfBandData), b(outOfBandData), str, i);
        iDataReadResultListener.onResult(null, i, 2);
    }

    @Override // com.huawei.hihealth.IHiHealthKitEx
    public void getCount(OutOfBandData outOfBandData, HiHealthDataQuery hiHealthDataQuery, IDataReadResultListener iDataReadResultListener) throws RemoteException {
        ReleaseLogUtil.c("HiH_HiHlthKitExtBnd", "enter getCount");
        c(outOfBandData, hiHealthDataQuery, iDataReadResultListener, "getCount");
    }

    @Override // com.huawei.hihealth.IHiHealthKitEx
    public void saveSample(OutOfBandData outOfBandData, HiHealthKitData hiHealthKitData, final IDataOperateListener iDataOperateListener) throws RemoteException {
        d("saveSample");
        if (iDataOperateListener == null) {
            ReleaseLogUtil.c("HiH_HiHlthKitExtBnd", "saveSample", " callback is null");
            return;
        }
        final String e2 = e(outOfBandData);
        final String b2 = b(outOfBandData);
        final irc ircVar = new irc();
        if (d(outOfBandData, iDataOperateListener, "saveSample", ircVar)) {
            return;
        }
        int type = hiHealthKitData.getType();
        if (iqz.e("saveSample", type, d(outOfBandData, "saveSample", 30), iDataOperateListener)) {
            if (!HiHealthKitDataChecker.a(Arrays.asList(hiHealthKitData), HiHealthKitDataChecker.MethodType.SAVE_SAMPLES, true)) {
                iDataOperateListener.onResult(2, ipd.c(2));
                b(ircVar, e2, b2, "saveSample", 2);
                return;
            }
            if (iqw.d(this.j) && type == 10062) {
                hiHealthKitData.setType(61001);
            }
            final String str = "saveSample";
            if (c(iov.b(this.j, outOfBandData, this.f4164a, type, 2), false, new Action() { // from class: img
                @Override // com.huawei.hihealthservice.hihealthkit.HiHealthKitExtendBinder.Action
                public final void operate(int i) {
                    HiHealthKitExtendBinder.this.c(iDataOperateListener, ircVar, e2, b2, str, i);
                }
            })) {
                return;
            }
            ini c2 = ini.c();
            if (c2 == null) {
                ReleaseLogUtil.c("HiH_HiHlthKitExtBnd", "saveSample insertHelper is null");
                iDataOperateListener.onResult(4, null);
                c(ircVar, e2, b2, "saveSample", 4);
            } else {
                String c3 = iwi.c(e2, hiHealthKitData.getBoolean("belongsToHealth"));
                final String str2 = "saveSample";
                c2.e(Arrays.asList(hiHealthKitData), new IDataOperateListener.Stub() { // from class: com.huawei.hihealthservice.hihealthkit.HiHealthKitExtendBinder.3
                    @Override // com.huawei.hihealth.IDataOperateListener
                    public void onResult(int i, List list) throws RemoteException {
                        iDataOperateListener.onResult(i, list);
                        HiHealthKitExtendBinder.this.c(ircVar, e2, b2, str2, iox.b(i));
                    }
                }, c3);
                iqr.c(this.j, outOfBandData, "saveSample", c3);
                ReleaseLogUtil.e("HiH_HiHlthKitExtBnd", "addPackageNameToSharedPreferences result: ", Boolean.valueOf(iqr.c((List<HiHealthKitData>) Arrays.asList(hiHealthKitData), c3)));
            }
        }
    }

    public /* synthetic */ void c(IDataOperateListener iDataOperateListener, irc ircVar, String str, String str2, String str3, int i) throws RemoteException {
        iDataOperateListener.onResult(i, b);
        b(ircVar, str, str2, str3, i);
    }

    @Override // com.huawei.hihealth.IHiHealthKitEx
    public void saveSamples(OutOfBandData outOfBandData, List list, final IDataOperateListener iDataOperateListener) throws RemoteException {
        OutOfBandData outOfBandData2 = outOfBandData;
        d("saveSamples");
        if (iDataOperateListener == null) {
            ReleaseLogUtil.c("HiH_HiHlthKitExtBnd", "saveSamples", " callback is null");
            return;
        }
        final String e2 = e(outOfBandData);
        final String b2 = b(outOfBandData);
        final irc ircVar = new irc();
        if (d(outOfBandData2, iDataOperateListener, "saveSamples", ircVar)) {
            return;
        }
        List<Integer> e3 = ima.e((List<HiHealthKitData>) list);
        Iterator<Integer> it = e3.iterator();
        while (it.hasNext()) {
            if (!iqz.e("saveSamples", it.next().intValue(), d(outOfBandData2, "saveSamples", 30), iDataOperateListener)) {
                return;
            }
        }
        int i = 2;
        if (!HiHealthKitDataChecker.a(list, HiHealthKitDataChecker.MethodType.SAVE_SAMPLES, false)) {
            iDataOperateListener.onResult(2, ipd.c(2));
            b(ircVar, e2, b2, "saveSamples", 2);
            return;
        }
        ArrayList arrayList = new ArrayList(list.size());
        Iterator it2 = list.iterator();
        while (it2.hasNext()) {
            arrayList.add((HiHealthKitData) it2.next());
        }
        Iterator<Integer> it3 = e3.iterator();
        while (it3.hasNext()) {
            ArrayList arrayList2 = arrayList;
            if (c(iov.b(this.j, outOfBandData2, this.f4164a, it3.next().intValue(), i), false, new Action() { // from class: imf
                @Override // com.huawei.hihealthservice.hihealthkit.HiHealthKitExtendBinder.Action
                public final void operate(int i2) {
                    HiHealthKitExtendBinder.this.e(iDataOperateListener, ircVar, e2, b2, i2);
                }
            })) {
                return;
            }
            outOfBandData2 = outOfBandData;
            arrayList = arrayList2;
            i = 2;
        }
        ArrayList arrayList3 = arrayList;
        ini c2 = ini.c();
        if (c2 == null) {
            ReleaseLogUtil.c("HiH_HiHlthKitExtBnd", "saveSamples insertHelper is null");
            iDataOperateListener.onResult(4, null);
            c(ircVar, e2, b2, "saveSamples", 4);
            return;
        }
        String c3 = iwi.c(e2, ((HiHealthKitData) arrayList3.get(0)).getBoolean("belongsToHealth"));
        if (iqz.c(iwi.a(this.j)) >= 4) {
            c2.e(arrayList3, iDataOperateListener, c3);
        } else {
            Iterator it4 = arrayList3.iterator();
            while (it4.hasNext()) {
                c2.e(Arrays.asList((HiHealthKitData) it4.next()), iDataOperateListener, c3);
            }
        }
        iqr.c(this.j, outOfBandData, "saveSamples", c3);
        ReleaseLogUtil.e("HiH_HiHlthKitExtBnd", "addPackageNameToSharedPreferences result: ", Boolean.valueOf(iqr.c(arrayList3, c3)));
        c(ircVar, e2, b2, "saveSamples", 0);
    }

    public /* synthetic */ void e(IDataOperateListener iDataOperateListener, irc ircVar, String str, String str2, int i) throws RemoteException {
        iDataOperateListener.onResult(i, b);
        b(ircVar, str, str2, "saveSamples", i);
    }

    @Override // com.huawei.hihealth.IHiHealthKitEx
    public void deleteSample(OutOfBandData outOfBandData, HiHealthKitData hiHealthKitData, IDataOperateListener iDataOperateListener) throws RemoteException {
        ArrayList arrayList = new ArrayList();
        arrayList.add(hiHealthKitData);
        c(outOfBandData, arrayList, iDataOperateListener, "deleteSample");
    }

    @Override // com.huawei.hihealth.IHiHealthKitEx
    public void deleteSamples(OutOfBandData outOfBandData, List list, IDataOperateListener iDataOperateListener) throws RemoteException {
        c(outOfBandData, list, iDataOperateListener, "deleteSamples");
    }

    private void c(OutOfBandData outOfBandData, List list, final IDataOperateListener iDataOperateListener, String str) throws RemoteException {
        d(str);
        String e2 = e(outOfBandData);
        String b2 = b(outOfBandData);
        final irc ircVar = new irc();
        if (d(outOfBandData, iDataOperateListener, str, ircVar)) {
            return;
        }
        Iterator<Integer> it = ima.e((List<HiHealthKitData>) list).iterator();
        while (it.hasNext()) {
            if (!iqz.e(str, it.next().intValue(), d(outOfBandData, str, 30), iDataOperateListener)) {
                return;
            }
        }
        final iqy iqyVar = new iqy(str, 0, e2, b2);
        if (!HiHealthKitDataChecker.a(list, HiHealthKitDataChecker.MethodType.DELETE_SAMPLES, true)) {
            iDataOperateListener.onResult(2, ipd.c(2));
            ircVar.c(this.j, iqyVar.b(2));
            LogUtil.h("HiHlthKitExtBnd", str, ipd.b(2));
            return;
        }
        int a2 = ima.a(list);
        if (iqw.d(this.j) && a2 == 10061) {
            ((HiHealthKitData) list.get(0)).setType(31001);
            a2 = 31001;
        }
        iqyVar.e(String.valueOf(a2));
        if (c(iov.b(this.j, outOfBandData, this.f4164a, a2, 2), false, new Action() { // from class: ink
            @Override // com.huawei.hihealthservice.hihealthkit.HiHealthKitExtendBinder.Action
            public final void operate(int i) {
                HiHealthKitExtendBinder.this.c(iDataOperateListener, ircVar, iqyVar, i);
            }
        })) {
            return;
        }
        ini c2 = ini.c();
        String c3 = iwi.c(e2, ((HiHealthKitData) list.get(0)).getBoolean("belongsToHealth"));
        c2.a(iqyVar, list, iDataOperateListener);
        iqr.c(this.j, outOfBandData, str, c3);
    }

    public /* synthetic */ void c(IDataOperateListener iDataOperateListener, irc ircVar, iqy iqyVar, int i) throws RemoteException {
        LogUtil.h("HiHlthKitExtBnd", ipd.b(i));
        iDataOperateListener.onResult(i, b);
        ircVar.c(this.j, iqyVar.b(i));
    }

    @Override // com.huawei.hihealth.IHiHealthKitEx
    public void requestAuthorization(OutOfBandData outOfBandData, int[] iArr, int[] iArr2, IBaseCallback iBaseCallback) throws RemoteException {
        if (!d(outOfBandData)) {
            ReleaseLogUtil.c("HiH_HiHlthKitExtBnd", "requestAuthorization invalid app type, only quick app allow called");
            return;
        }
        QuickAppInfo quickAppInfo = (QuickAppInfo) outOfBandData;
        irc ircVar = new irc();
        try {
            if (!ima.d(iArr2, iArr)) {
                ReleaseLogUtil.c("HiH_HiHlthKitExtBnd", "requestAuthorization isKitValidTypes false");
                if (iBaseCallback != null) {
                    iBaseCallback.onResult(2, null);
                }
                c(ircVar, quickAppInfo.getPackageName(), quickAppInfo.getAppId(), "requestAuthorization", 2);
                return;
            }
            this.f = iBaseCallback;
            this.c = quickAppInfo;
            Message obtain = Message.obtain();
            obtain.what = 101;
            Bundle bundle = new Bundle();
            bundle.putIntArray("writeTypes", iArr);
            bundle.putIntArray("readTypes", iArr2);
            obtain.setData(bundle);
            this.h.sendMessage(obtain);
        } catch (RemoteException e2) {
            ReleaseLogUtil.c("HiH_HiHlthKitExtBnd", "requestAuthorization RemoteException: ", e2.getMessage());
            iBaseCallback.onResult(4, null);
            c(ircVar, quickAppInfo.getPackageName(), quickAppInfo.getAppId(), "requestAuthorization", 4);
        }
    }

    @Override // com.huawei.hihealth.IHiHealthKitEx
    public void getDataAuthStatus(OutOfBandData outOfBandData, int i, IDataOperateListener iDataOperateListener) throws RemoteException {
        final String str = "getDataAuthStatus";
        d("getDataAuthStatus");
        if (iDataOperateListener == null) {
            ReleaseLogUtil.c("HiH_HiHlthKitExtBnd", "getDataAuthStatus", " callback is null");
            return;
        }
        final irc ircVar = new irc();
        final QuickAppInfo quickAppInfo = (QuickAppInfo) outOfBandData;
        if (c(iov.c(this.j, outOfBandData, 0, 0), false, new Action() { // from class: imy
            @Override // com.huawei.hihealthservice.hihealthkit.HiHealthKitExtendBinder.Action
            public final void operate(int i2) {
                HiHealthKitExtendBinder.this.c(ircVar, quickAppInfo, str, i2);
            }
        })) {
            return;
        }
        new HiHealthKitQueryHelper(this.j, quickAppInfo.getPackageName(), quickAppInfo.getAppId()).d(i, iDataOperateListener);
        c(ircVar, quickAppInfo.getPackageName(), quickAppInfo.getAppId(), "getDataAuthStatus", 0);
    }

    public /* synthetic */ void c(irc ircVar, QuickAppInfo quickAppInfo, String str, int i) throws RemoteException {
        b(ircVar, quickAppInfo.getPackageName(), quickAppInfo.getAppId(), str, i);
    }

    @Override // com.huawei.hihealth.IHiHealthKitEx
    public void getDataAuthStatusEx(OutOfBandData outOfBandData, int[] iArr, int[] iArr2, IBaseCallback iBaseCallback) throws RemoteException {
        final String str = "getDataAuthStatusEx";
        d("getDataAuthStatusEx");
        if (iBaseCallback == null) {
            ReleaseLogUtil.c("HiH_HiHlthKitExtBnd", "getDataAuthStatusEx", " callback is null");
            return;
        }
        final QuickAppInfo quickAppInfo = (QuickAppInfo) outOfBandData;
        final irc ircVar = new irc();
        if (c(iov.c(this.j, outOfBandData, 0, 0), false, new Action() { // from class: ina
            @Override // com.huawei.hihealthservice.hihealthkit.HiHealthKitExtendBinder.Action
            public final void operate(int i) {
                HiHealthKitExtendBinder.this.e(ircVar, quickAppInfo, str, i);
            }
        })) {
            return;
        }
        new HiHealthKitQueryHelper(this.j, quickAppInfo.getPackageName(), quickAppInfo.getAppId()).c(iArr, iArr2, iBaseCallback);
        c(ircVar, quickAppInfo.getPackageName(), quickAppInfo.getAppId(), "getDataAuthStatusEx", 0);
    }

    public /* synthetic */ void e(irc ircVar, QuickAppInfo quickAppInfo, String str, int i) throws RemoteException {
        b(ircVar, quickAppInfo.getPackageName(), quickAppInfo.getAppId(), str, i);
    }

    @Override // com.huawei.hihealth.IHiHealthKitEx
    public void getHealthyLivingData(OutOfBandData outOfBandData, HiHealthCapabilityQuery hiHealthCapabilityQuery, final ICommonCallback iCommonCallback) throws RemoteException {
        d("getHealthyLivingData");
        final irc ircVar = new irc();
        final String e2 = e(outOfBandData);
        final String b2 = b(outOfBandData);
        if (iCommonCallback == null) {
            ReleaseLogUtil.c("HiH_HiHlthKitExtBnd", "getHealthyLivingData", " callback is null");
            b(ircVar, e2, b2, "getHealthyLivingData", 2);
        } else if (hiHealthCapabilityQuery == null) {
            ReleaseLogUtil.c("HiH_HiHlthKitExtBnd", "getHealthyLivingData query is null");
            iCommonCallback.onResult(2, "getHealthyLivingData param invalid");
            b(ircVar, e2, b2, "getHealthyLivingData", 2);
        } else {
            final String str = "getHealthyLivingData";
            if (c(iov.b(this.j, outOfBandData, this.f4164a, hiHealthCapabilityQuery.getType(), 1), false, new Action() { // from class: imp
                @Override // com.huawei.hihealthservice.hihealthkit.HiHealthKitExtendBinder.Action
                public final void operate(int i) {
                    HiHealthKitExtendBinder.this.c(ircVar, e2, b2, str, iCommonCallback, i);
                }
            })) {
                return;
            }
            final String str2 = "getHealthyLivingData";
            ikx.a(this.j).d(hiHealthCapabilityQuery.getStartTime(), hiHealthCapabilityQuery.getEndTime(), new IBaseCommonCallback.Stub() { // from class: com.huawei.hihealthservice.hihealthkit.HiHealthKitExtendBinder.4
                @Override // com.huawei.health.IBaseCommonCallback
                public void onResponse(int i, String str3) throws RemoteException {
                    ReleaseLogUtil.c("HiH_HiHlthKitExtBnd", "getHealthyLivingData resultCode: ", Integer.valueOf(i));
                    int a2 = iox.a(i);
                    iCommonCallback.onResult(a2, str3);
                    HiHealthKitExtendBinder.this.c(ircVar, e2, b2, str2, a2);
                }
            });
        }
    }

    public /* synthetic */ void c(irc ircVar, String str, String str2, String str3, ICommonCallback iCommonCallback, int i) throws RemoteException {
        b(ircVar, str, str2, str3, i);
        iCommonCallback.onResult(i, "getHealthyLivingData permission denied");
    }

    @Override // com.huawei.hihealth.IHiHealthKitEx
    public void setKitVersion(String str) {
        ReleaseLogUtil.e("HiH_HiHlthKitExtBnd", "enter setKitVersion ", str);
        iqz.a(iwi.a(this.j), str);
    }

    @Override // com.huawei.hihealth.IHiHealthKitEx
    public void registerPackageName(String str) {
        ReleaseLogUtil.e("HiH_HiHlthKitExtBnd", "registerPackageName enter, packageName: ", str);
        iwi.d(this.j, str);
    }

    @Override // com.huawei.hihealth.IHiHealthKitEx
    public void startSportEnhance(OutOfBandData outOfBandData, StartSportParam startSportParam, final ICommonCallback iCommonCallback) throws RemoteException {
        d("startSportEnhance");
        final irc ircVar = new irc();
        final String e2 = e(outOfBandData);
        final String b2 = b(outOfBandData);
        if (iCommonCallback == null) {
            ReleaseLogUtil.c("HiH_HiHlthKitExtBnd", "startSportEnhance", " callback is null");
            b(ircVar, e2, b2, "startSportEnhance", 2);
            return;
        }
        if (iqz.e("startSportEnhance", startSportParam.getInt(BleConstants.SPORT_TYPE), d(outOfBandData, "startSportEnhance", 30), iCommonCallback)) {
            if (!HiHealthKitDataChecker.e(startSportParam)) {
                iCommonCallback.onResult(2, ipd.b(2));
                return;
            }
            if (ioy.b("startSportEnhance", iCommonCallback)) {
                final String str = "startSportEnhance";
                if (c(iov.b(this.j, outOfBandData, this.f4164a, 101003, 1), true, new Action() { // from class: inj
                    @Override // com.huawei.hihealthservice.hihealthkit.HiHealthKitExtendBinder.Action
                    public final void operate(int i) {
                        HiHealthKitExtendBinder.this.f(ircVar, e2, b2, str, iCommonCallback, i);
                    }
                })) {
                    return;
                }
                final String str2 = "startSportEnhance";
                ino.b(this.j).c(startSportParam, new IBaseCommonCallback.Stub() { // from class: com.huawei.hihealthservice.hihealthkit.HiHealthKitExtendBinder.6
                    @Override // com.huawei.health.IBaseCommonCallback
                    public void onResponse(int i, String str3) throws RemoteException {
                        ReleaseLogUtil.c("HiH_HiHlthKitExtBnd", "startSportEnhance result code ", Integer.valueOf(i));
                        if (iqz.d(e2, 5)) {
                            iCommonCallback.onResult(i, str3);
                        } else {
                            iCommonCallback.onResult(iox.j(i), str3);
                        }
                        HiHealthKitExtendBinder.this.c(ircVar, e2, b2, str2, i);
                    }
                });
            }
        }
    }

    public /* synthetic */ void f(irc ircVar, String str, String str2, String str3, ICommonCallback iCommonCallback, int i) throws RemoteException {
        b(ircVar, str, str2, str3, i);
        iCommonCallback.onResult(i, "startSportEnhance permission denied");
    }

    private boolean d(final ICommonCallback iCommonCallback, final irc ircVar, final String str, OutOfBandData outOfBandData) throws RemoteException {
        final String e2 = e(outOfBandData);
        final String b2 = b(outOfBandData);
        if (iCommonCallback != null) {
            return ioy.b(str, iCommonCallback) && !c(iov.b(this.j, outOfBandData, this.f4164a, 101003, 1), false, new Action() { // from class: imr
                @Override // com.huawei.hihealthservice.hihealthkit.HiHealthKitExtendBinder.Action
                public final void operate(int i) {
                    HiHealthKitExtendBinder.this.a(ircVar, e2, b2, str, iCommonCallback, i);
                }
            });
        }
        ReleaseLogUtil.c("HiH_HiHlthKitExtBnd", str, " callback is null");
        b(ircVar, e2, b2, str, 2);
        return false;
    }

    public /* synthetic */ void a(irc ircVar, String str, String str2, String str3, ICommonCallback iCommonCallback, int i) throws RemoteException {
        b(ircVar, str, str2, str3, i);
        iCommonCallback.onResult(i, ipd.b(i));
    }

    @Override // com.huawei.hihealth.IHiHealthKitEx
    public void stopSport(OutOfBandData outOfBandData, final ICommonCallback iCommonCallback) throws RemoteException {
        d("stopSport");
        final irc ircVar = new irc();
        final String e2 = e(outOfBandData);
        final String b2 = b(outOfBandData);
        if (!d(iCommonCallback, ircVar, "stopSport", outOfBandData)) {
            ReleaseLogUtil.c("HiH_HiHlthKitExtBnd", "stopSport", " API is not ready");
        } else {
            final String str = "stopSport";
            ino.b(this.j).a(new IBaseCommonCallback.Stub() { // from class: com.huawei.hihealthservice.hihealthkit.HiHealthKitExtendBinder.9
                @Override // com.huawei.health.IBaseCommonCallback
                public void onResponse(int i, String str2) throws RemoteException {
                    ReleaseLogUtil.e("HiH_HiHlthKitExtBnd", str, " result code ", Integer.valueOf(i));
                    iCommonCallback.onResult(i, str2);
                    HiHealthKitExtendBinder.this.c(ircVar, e2, b2, str, i);
                }
            });
        }
    }

    @Override // com.huawei.hihealth.IHiHealthKitEx
    public void sendDeviceControlinstruction(OutOfBandData outOfBandData, String str, final ICommonCallback iCommonCallback) throws RemoteException {
        d("sendDeviceControlinstruction");
        final irc ircVar = new irc();
        final String e2 = e(outOfBandData);
        final String b2 = b(outOfBandData);
        if (iCommonCallback == null) {
            ReleaseLogUtil.c("HiH_HiHlthKitExtBnd", "sendDeviceControlinstruction", " callback is null");
            b(ircVar, e2, b2, "sendDeviceControlinstruction", 2);
        } else {
            if (!HiHealthKitDataChecker.a(str)) {
                iCommonCallback.onResult(2, ipd.b(2));
                return;
            }
            if (ioy.b("sendDeviceControlinstruction", iCommonCallback)) {
                final String str2 = "sendDeviceControlinstruction";
                if (c(iov.b(this.j, outOfBandData, this.f4164a, 101204, 2), false, new Action() { // from class: ime
                    @Override // com.huawei.hihealthservice.hihealthkit.HiHealthKitExtendBinder.Action
                    public final void operate(int i) {
                        HiHealthKitExtendBinder.this.g(ircVar, e2, b2, str2, iCommonCallback, i);
                    }
                })) {
                    return;
                }
                final String str3 = "sendDeviceControlinstruction";
                ino.b(this.j).a(str, new IBaseCommonCallback.Stub() { // from class: com.huawei.hihealthservice.hihealthkit.HiHealthKitExtendBinder.7
                    @Override // com.huawei.health.IBaseCommonCallback
                    public void onResponse(int i, String str4) throws RemoteException {
                        ReleaseLogUtil.e("HiH_HiHlthKitExtBnd", "sendDeviceControlinstruction result code ", Integer.valueOf(i));
                        if (!iqz.d(e2, 9)) {
                            i = iox.c(i);
                        }
                        int i2 = i;
                        iCommonCallback.onResult(i2, str4);
                        HiHealthKitExtendBinder.this.c(ircVar, e2, b2, str3, i2);
                    }
                });
            }
        }
    }

    public /* synthetic */ void g(irc ircVar, String str, String str2, String str3, ICommonCallback iCommonCallback, int i) throws RemoteException {
        b(ircVar, str, str2, str3, i);
        iCommonCallback.onResult(i, "sendDeviceControlinstruction permission denied");
    }

    @Override // com.huawei.hihealth.IHiHealthKitEx
    public void pauseSport(OutOfBandData outOfBandData, final ICommonCallback iCommonCallback) throws RemoteException {
        d("pauseSport");
        final irc ircVar = new irc();
        final String e2 = e(outOfBandData);
        final String b2 = b(outOfBandData);
        if (!d(iCommonCallback, ircVar, "pauseSport", outOfBandData)) {
            ReleaseLogUtil.c("HiH_HiHlthKitExtBnd", "pauseSport", " API is not ready");
        } else {
            final String str = "pauseSport";
            ino.b(this.j).a(new IBaseCommonCallback.Stub() { // from class: com.huawei.hihealthservice.hihealthkit.HiHealthKitExtendBinder.8
                @Override // com.huawei.health.IBaseCommonCallback
                public void onResponse(int i, String str2) throws RemoteException {
                    ReleaseLogUtil.e("HiH_HiHlthKitExtBnd", str, " result code ", Integer.valueOf(i));
                    iCommonCallback.onResult(i, str2);
                    HiHealthKitExtendBinder.this.c(ircVar, e2, b2, str, i);
                }
            });
        }
    }

    @Override // com.huawei.hihealth.IHiHealthKitEx
    public void resumeSport(OutOfBandData outOfBandData, final ICommonCallback iCommonCallback) throws RemoteException {
        d("resumeSport");
        final irc ircVar = new irc();
        final String e2 = e(outOfBandData);
        final String b2 = b(outOfBandData);
        if (!d(iCommonCallback, ircVar, "resumeSport", outOfBandData)) {
            ReleaseLogUtil.c("HiH_HiHlthKitExtBnd", "resumeSport", " API is not ready");
        } else {
            final String str = "resumeSport";
            ino.b(this.j).a(new IBaseCommonCallback.Stub() { // from class: com.huawei.hihealthservice.hihealthkit.HiHealthKitExtendBinder.10
                @Override // com.huawei.health.IBaseCommonCallback
                public void onResponse(int i, String str2) throws RemoteException {
                    ReleaseLogUtil.e("HiH_HiHlthKitExtBnd", str, " result code ", Integer.valueOf(i));
                    iCommonCallback.onResult(i, str2);
                    HiHealthKitExtendBinder.this.c(ircVar, e2, b2, str, i);
                }
            });
        }
    }

    @Override // com.huawei.hihealth.IHiHealthKitEx
    public void startReadingHeartRate(OutOfBandData outOfBandData, final IRealTimeDataCallback iRealTimeDataCallback) throws RemoteException {
        d("startReadingHeartRate");
        if (iRealTimeDataCallback == null) {
            ReleaseLogUtil.c("HiH_HiHlthKitExtBnd", "startReadingHeartRate", " callback is null");
            return;
        }
        final irc ircVar = new irc();
        final String e2 = e(outOfBandData);
        final String b2 = b(outOfBandData);
        final String str = "startReadingHeartRate";
        if (c(iov.b(this.j, outOfBandData, this.f4164a, 50001, 1), true, new Action() { // from class: inb
            @Override // com.huawei.hihealthservice.hihealthkit.HiHealthKitExtendBinder.Action
            public final void operate(int i) {
                HiHealthKitExtendBinder.this.c(ircVar, e2, b2, str, iRealTimeDataCallback, i);
            }
        })) {
            return;
        }
        LogUtil.c("HiHlthKitExtBnd", "startReadingHeartRate packageName:", e2);
        ins.a(this.j).j(e2, d(iRealTimeDataCallback, ircVar, "startReadingHeartRate", e2, b2));
    }

    public /* synthetic */ void c(irc ircVar, String str, String str2, String str3, IRealTimeDataCallback iRealTimeDataCallback, int i) throws RemoteException {
        b(ircVar, str, str2, str3, i);
        iRealTimeDataCallback.onResult(i);
    }

    @Override // com.huawei.hihealth.IHiHealthKitEx
    public void stopReadingHeartRate(OutOfBandData outOfBandData, IRealTimeDataCallback iRealTimeDataCallback) throws RemoteException {
        d("stopReadingHeartRate");
        if (iRealTimeDataCallback == null) {
            ReleaseLogUtil.c("HiH_HiHlthKitExtBnd", "stopReadingHeartRate", " callback is null");
            return;
        }
        irc ircVar = new irc();
        String e2 = e(outOfBandData);
        String b2 = b(outOfBandData);
        c("stopReadingHeartRate", e2);
        ins.a(this.j).m(e2, d(iRealTimeDataCallback, ircVar, "stopReadingHeartRate", e2, b2));
    }

    @Override // com.huawei.hihealth.IHiHealthKitEx
    public void startReadingAtrial(OutOfBandData outOfBandData, final IRealTimeDataCallback iRealTimeDataCallback) throws RemoteException {
        d("startReadingAtrial");
        if (iRealTimeDataCallback == null) {
            ReleaseLogUtil.c("HiH_HiHlthKitExtBnd", "startReadingAtrial", " callback is null");
            return;
        }
        final String e2 = e(outOfBandData);
        final String b2 = b(outOfBandData);
        LogUtil.a("HiHlthKitExtBnd", "caller packageName", e2, " appId ", b2);
        final irc ircVar = new irc();
        final String str = "startReadingAtrial";
        if (c(iov.b(this.j, outOfBandData, this.f4164a, 101202, 1), true, new Action() { // from class: imq
            @Override // com.huawei.hihealthservice.hihealthkit.HiHealthKitExtendBinder.Action
            public final void operate(int i) {
                HiHealthKitExtendBinder.this.a(ircVar, e2, b2, str, iRealTimeDataCallback, i);
            }
        })) {
            return;
        }
        ins.a(this.j).g(e2, d(iRealTimeDataCallback, ircVar, "startReadingAtrial", e2, b2));
    }

    public /* synthetic */ void a(irc ircVar, String str, String str2, String str3, IRealTimeDataCallback iRealTimeDataCallback, int i) throws RemoteException {
        b(ircVar, str, str2, str3, i);
        iRealTimeDataCallback.onResult(i);
    }

    @Override // com.huawei.hihealth.IHiHealthKitEx
    public void stopReadingAtrial(OutOfBandData outOfBandData, final IRealTimeDataCallback iRealTimeDataCallback) throws RemoteException {
        d("stopReadingAtrial");
        if (iRealTimeDataCallback == null) {
            ReleaseLogUtil.c("HiH_HiHlthKitExtBnd", "stopReadingAtrial", " callback is null");
            return;
        }
        final irc ircVar = new irc();
        final String e2 = e(outOfBandData);
        final String b2 = b(outOfBandData);
        LogUtil.a("HiHlthKitExtBnd", "caller packageName", e2, " appId ", b2);
        final String str = "stopReadingAtrial";
        if (c(iov.b(this.j, outOfBandData, this.f4164a, 101202, 1), false, new Action() { // from class: imm
            @Override // com.huawei.hihealthservice.hihealthkit.HiHealthKitExtendBinder.Action
            public final void operate(int i) {
                HiHealthKitExtendBinder.this.h(ircVar, e2, b2, str, iRealTimeDataCallback, i);
            }
        })) {
            return;
        }
        ins.a(this.j).k(e2, d(iRealTimeDataCallback, ircVar, "stopReadingAtrial", e2, b2));
    }

    public /* synthetic */ void h(irc ircVar, String str, String str2, String str3, IRealTimeDataCallback iRealTimeDataCallback, int i) throws RemoteException {
        b(ircVar, str, str2, str3, i);
        iRealTimeDataCallback.onResult(i);
    }

    @Override // com.huawei.hihealth.IHiHealthKitEx
    public void startReadingRRI(OutOfBandData outOfBandData, final IRealTimeDataCallback iRealTimeDataCallback) throws RemoteException {
        d("startReadingRRI");
        if (iRealTimeDataCallback == null) {
            ReleaseLogUtil.c("HiH_HiHlthKitExtBnd", "startReadingRRI", " callback is null");
            return;
        }
        final irc ircVar = new irc();
        QuickAppInfo quickAppInfo = (QuickAppInfo) outOfBandData;
        final String e2 = e(outOfBandData);
        final String b2 = b(outOfBandData);
        final String str = "startReadingRRI";
        if (c(iov.c(this.j, outOfBandData, 50001, 1), true, new Action() { // from class: imz
            @Override // com.huawei.hihealthservice.hihealthkit.HiHealthKitExtendBinder.Action
            public final void operate(int i) {
                HiHealthKitExtendBinder.this.e(ircVar, e2, b2, str, iRealTimeDataCallback, i);
            }
        })) {
            return;
        }
        d("startReadingRRI", quickAppInfo);
        ins.a(this.j).i(quickAppInfo.getPackageName(), d(iRealTimeDataCallback, ircVar, "startReadingRRI", e2, b2));
    }

    public /* synthetic */ void e(irc ircVar, String str, String str2, String str3, IRealTimeDataCallback iRealTimeDataCallback, int i) throws RemoteException {
        b(ircVar, str, str2, str3, i);
        iRealTimeDataCallback.onResult(i);
    }

    @Override // com.huawei.hihealth.IHiHealthKitEx
    public void stopReadingRRI(OutOfBandData outOfBandData, IRealTimeDataCallback iRealTimeDataCallback) throws RemoteException {
        d("stopReadingRRI");
        if (iRealTimeDataCallback == null) {
            ReleaseLogUtil.c("HiH_HiHlthKitExtBnd", "stopReadingRRI", " callback is null");
            return;
        }
        QuickAppInfo quickAppInfo = (QuickAppInfo) outOfBandData;
        String e2 = e(outOfBandData);
        String b2 = b(outOfBandData);
        irc ircVar = new irc();
        d("stopReadingRRI", quickAppInfo);
        ins.a(this.j).o(quickAppInfo.getPackageName(), d(iRealTimeDataCallback, ircVar, "stopReadingRRI", e2, b2));
    }

    private IRealTimeDataCallback d(final IRealTimeDataCallback iRealTimeDataCallback, final irc ircVar, final String str, final String str2, final String str3) {
        final String a2 = iwi.a(this.j);
        LogUtil.a("HiHlthKitExtBnd", " getProxyIRealTimeDataListener enginePackageName ", a2);
        return new IRealTimeDataCallback.Stub() { // from class: com.huawei.hihealthservice.hihealthkit.HiHealthKitExtendBinder.13
            @Override // com.huawei.hihealth.IRealTimeDataCallback
            public void onResult(int i) throws RemoteException {
                ReleaseLogUtil.e("HiH_HiHlthKitExtBnd", "IRealTimeDataCallback resultCode: ", Integer.valueOf(i));
                int f = iox.f(i);
                if (iqz.d(a2, 2)) {
                    iRealTimeDataCallback.onResult(f);
                } else {
                    iRealTimeDataCallback.onResult(i);
                }
                HiHealthKitExtendBinder.this.c(ircVar, str2, str3, str, f);
            }

            @Override // com.huawei.hihealth.IRealTimeDataCallback
            public void onChange(int i, String str4) throws RemoteException {
                int f = iox.f(i);
                if (iqz.d(str2, 2) && !"startReadingAtrial".equals(str)) {
                    iRealTimeDataCallback.onChange(f, str4);
                } else {
                    iRealTimeDataCallback.onChange(i, str4);
                }
            }
        };
    }

    @Override // com.huawei.hihealth.IHiHealthKitEx
    public void getDeviceList(OutOfBandData outOfBandData, final IRealTimeDataCallback iRealTimeDataCallback) throws RemoteException {
        d("getDeviceList");
        if (iRealTimeDataCallback == null) {
            ReleaseLogUtil.c("HiH_HiHlthKitExtBnd", "getDeviceList", " callback is null");
            return;
        }
        final irc ircVar = new irc();
        final String e2 = e(outOfBandData);
        final String b2 = b(outOfBandData);
        final String str = "getDeviceList";
        if (c(iov.b(this.j, outOfBandData, this.f4164a, 101201, 1), false, new Action() { // from class: ine
            @Override // com.huawei.hihealthservice.hihealthkit.HiHealthKitExtendBinder.Action
            public final void operate(int i) {
                HiHealthKitExtendBinder.this.d(ircVar, e2, b2, str, iRealTimeDataCallback, i);
            }
        })) {
            return;
        }
        ins.a(this.j).b(iqx.e(iRealTimeDataCallback, ircVar, this.j, new iqy("getDeviceList", 0, e2, b2)));
    }

    public /* synthetic */ void d(irc ircVar, String str, String str2, String str3, IRealTimeDataCallback iRealTimeDataCallback, int i) throws RemoteException {
        b(ircVar, str, str2, str3, i);
        iRealTimeDataCallback.onResult(i);
    }

    @Override // com.huawei.hihealth.IHiHealthKitEx
    public void sendDeviceCommand(OutOfBandData outOfBandData, String str, final IRealTimeDataCallback iRealTimeDataCallback) throws RemoteException {
        d("sendDeviceCommand");
        if (iRealTimeDataCallback == null) {
            ReleaseLogUtil.c("HiH_HiHlthKitExtBnd", "sendDeviceCommand", " callback is null");
            return;
        }
        final String e2 = e(outOfBandData);
        final String b2 = b(outOfBandData);
        final irc ircVar = new irc();
        final String str2 = "sendDeviceCommand";
        if (c(iov.b(this.j, outOfBandData, this.f4164a, 101204, 2), false, new Action() { // from class: imx
            @Override // com.huawei.hihealthservice.hihealthkit.HiHealthKitExtendBinder.Action
            public final void operate(int i) {
                HiHealthKitExtendBinder.this.b(ircVar, e2, b2, str2, iRealTimeDataCallback, i);
            }
        })) {
            return;
        }
        ins.a(this.j).h(str, iRealTimeDataCallback);
        c(ircVar, e2, b2, "sendDeviceCommand", 0);
    }

    public /* synthetic */ void b(irc ircVar, String str, String str2, String str3, IRealTimeDataCallback iRealTimeDataCallback, int i) throws RemoteException {
        b(ircVar, str, str2, str3, i);
        iRealTimeDataCallback.onResult(i);
    }

    @Override // com.huawei.hihealth.IHiHealthKitEx
    public void writeToWearable(OutOfBandData outOfBandData, String str, byte[] bArr, final IWriteCallback iWriteCallback) throws RemoteException {
        d("writeToWearable");
        if (iWriteCallback == null) {
            ReleaseLogUtil.c("HiH_HiHlthKitExtBnd", "writeToWearable", " callback is null");
            return;
        }
        if (str == null) {
            ReleaseLogUtil.c("HiH_HiHlthKitExtBnd", "writeToWearable", " info is null");
            iWriteCallback.onResult(2, "");
            return;
        }
        final irc ircVar = new irc();
        final QuickAppInfo quickAppInfo = (QuickAppInfo) outOfBandData;
        final String str2 = "writeToWearable";
        if (c(iov.c(this.j, outOfBandData, 101202, 2), false, new Action() { // from class: ind
            @Override // com.huawei.hihealthservice.hihealthkit.HiHealthKitExtendBinder.Action
            public final void operate(int i) {
                HiHealthKitExtendBinder.this.e(ircVar, quickAppInfo, str2, iWriteCallback, i);
            }
        })) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            String optString = jSONObject.optString(RemoteMessageConst.INPUT_TYPE);
            String optString2 = jSONObject.optString("inputDescription");
            String optString3 = jSONObject.optString("sizeAndFinish");
            if (optString3.isEmpty()) {
                optString3 = null;
            }
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("isWriteFile", bArr != null);
            jSONObject2.put("sizeAndFinish", optString3);
            ins.a(this.j).e(optString, optString2, bArr, optString3, iqx.a(iWriteCallback, ircVar, this.j, new iqy("writeToWearable", 0, quickAppInfo.getPackageName()), jSONObject2));
        } catch (JSONException e2) {
            ReleaseLogUtil.c("HiH_HiHlthKitExtBnd", "writeToWearable", " JSONException: ", e2.getMessage());
            iWriteCallback.onResult(2, null);
            c(ircVar, quickAppInfo.getPackageName(), quickAppInfo.getAppId(), "writeToWearable", 2);
        }
    }

    public /* synthetic */ void e(irc ircVar, QuickAppInfo quickAppInfo, String str, IWriteCallback iWriteCallback, int i) throws RemoteException {
        b(ircVar, quickAppInfo.getPackageName(), quickAppInfo.getAppId(), str, i);
        iWriteCallback.onResult(i, null);
    }

    @Override // com.huawei.hihealth.IHiHealthKitEx
    public void readFromWearable(OutOfBandData outOfBandData, String str, String str2, final IReadCallback iReadCallback) throws RemoteException {
        d("readFromWearable");
        if (iReadCallback == null) {
            ReleaseLogUtil.c("HiH_HiHlthKitExtBnd", "readFromWearable", " callback is null");
            return;
        }
        final irc ircVar = new irc();
        final QuickAppInfo quickAppInfo = (QuickAppInfo) outOfBandData;
        final String str3 = "readFromWearable";
        if (c(iov.c(this.j, outOfBandData, 101202, 1), true, new Action() { // from class: imk
            @Override // com.huawei.hihealthservice.hihealthkit.HiHealthKitExtendBinder.Action
            public final void operate(int i) {
                HiHealthKitExtendBinder.this.e(ircVar, quickAppInfo, str3, iReadCallback, i);
            }
        })) {
            return;
        }
        ins.a(this.j).c(str, str2, iqx.b(iReadCallback, ircVar, this.j, new iqy("readFromWearable", 0, quickAppInfo.getPackageName())));
    }

    public /* synthetic */ void e(irc ircVar, QuickAppInfo quickAppInfo, String str, IReadCallback iReadCallback, int i) throws RemoteException {
        b(ircVar, quickAppInfo.getPackageName(), quickAppInfo.getAppId(), str, i);
        iReadCallback.onResult(i, null, e);
    }

    @Override // com.huawei.hihealth.IHiHealthKitEx
    public void pushMsgToWearable(OutOfBandData outOfBandData, String str, String str2, final ICommonCallback iCommonCallback) throws RemoteException {
        d("pushMsgToWearable");
        if (iCommonCallback == null) {
            ReleaseLogUtil.c("HiH_HiHlthKitExtBnd", "pushMsgToWearable", " callback is null");
            return;
        }
        final irc ircVar = new irc();
        final QuickAppInfo quickAppInfo = (QuickAppInfo) outOfBandData;
        final String str3 = "pushMsgToWearable";
        if (c(iov.c(this.j, outOfBandData, 101203, 2), false, new Action() { // from class: imo
            @Override // com.huawei.hihealthservice.hihealthkit.HiHealthKitExtendBinder.Action
            public final void operate(int i) {
                HiHealthKitExtendBinder.this.b(ircVar, quickAppInfo, str3, iCommonCallback, i);
            }
        })) {
            return;
        }
        ins.a(this.j).d(ivw.c(quickAppInfo), str, str2, iqx.a(iCommonCallback, ircVar, this.j, new iqy("pushMsgToWearable", 0, quickAppInfo.getPackageName()), "com.huawei.hihealthservice.hihealthkit.constant.BusinessErrorCode.pushMessageFilterCode"));
    }

    public /* synthetic */ void b(irc ircVar, QuickAppInfo quickAppInfo, String str, ICommonCallback iCommonCallback, int i) throws RemoteException {
        b(ircVar, quickAppInfo.getPackageName(), quickAppInfo.getAppId(), str, i);
        iCommonCallback.onResult(i, null);
    }

    @Override // com.huawei.hihealth.IHiHealthKitEx
    public void registerRealTimeSportCallback(OutOfBandData outOfBandData, final ISportDataCallback iSportDataCallback) throws RemoteException {
        d("registerRealTimeSportCallback");
        if (iSportDataCallback == null) {
            ReleaseLogUtil.c("HiH_HiHlthKitExtBnd", "registerRealTimeSportCallback", " callback is null");
            return;
        }
        final irc ircVar = new irc();
        final String e2 = e(outOfBandData);
        final String b2 = b(outOfBandData);
        final String str = "registerRealTimeSportCallback";
        if (c(iov.b(this.j, outOfBandData, this.f4164a, 101003, 1), true, new Action() { // from class: inf
            @Override // com.huawei.hihealthservice.hihealthkit.HiHealthKitExtendBinder.Action
            public final void operate(int i) {
                HiHealthKitExtendBinder.this.b(ircVar, e2, b2, str, iSportDataCallback, i);
            }
        })) {
            return;
        }
        int callingUid = Binder.getCallingUid();
        LogUtil.a("HiHlthKitExtBnd", "registerRealTimeSportCallback callingUid = ", Integer.valueOf(callingUid));
        ITrackDataForDeveloper iTrackDataForDeveloper = this.k.get(Integer.valueOf(callingUid));
        if (iTrackDataForDeveloper != null) {
            ino.b(this.j).d(iTrackDataForDeveloper, new ICommonCallback.Stub() { // from class: com.huawei.hihealthservice.hihealthkit.HiHealthKitExtendBinder.5
                @Override // com.huawei.hihealth.ICommonCallback
                public void onResult(int i, String str2) {
                    ReleaseLogUtil.e("HiH_HiHlthKitExtBnd", "unregister prior callback result ", Integer.valueOf(i));
                }
            });
        }
        c cVar = new c(iSportDataCallback);
        this.k.put(Integer.valueOf(callingUid), cVar);
        ino.b(this.j).c(cVar);
        iSportDataCallback.onResult(0);
        c("registerRealTimeSportCallback", e2);
        c(ircVar, e2, b2, "registerRealTimeSportCallback", 0);
    }

    public /* synthetic */ void b(irc ircVar, String str, String str2, String str3, ISportDataCallback iSportDataCallback, int i) throws RemoteException {
        LogUtil.h("HiHlthKitExtBnd", ipd.b(i));
        b(ircVar, str, str2, str3, i);
        iSportDataCallback.onResult(i);
    }

    @Override // com.huawei.hihealth.IHiHealthKitEx
    public void unregisterRealTimeSportCallback(OutOfBandData outOfBandData, ICommonCallback iCommonCallback) throws RemoteException {
        d("unregisterRealTimeSportCallback");
        if (iCommonCallback == null) {
            ReleaseLogUtil.c("HiH_HiHlthKitExtBnd", "unregisterRealTimeSportCallback", " callback is null");
            return;
        }
        irc ircVar = new irc();
        String e2 = e(outOfBandData);
        String b2 = b(outOfBandData);
        LogUtil.c("HiHlthKitExtBnd", "unregisterRealTimeSportCallback", " packageName:", e2);
        int callingUid = Binder.getCallingUid();
        LogUtil.a("HiHlthKitExtBnd", "unregisterRealTimeSportCallback callingUid = ", Integer.valueOf(callingUid));
        ino.b(this.j).d(this.k.get(Integer.valueOf(callingUid)), iCommonCallback);
        c(ircVar, e2, b2, "unregisterRealTimeSportCallback", 0);
    }

    @Override // com.huawei.hihealth.IHiHealthKitEx
    public void getSwitch(OutOfBandData outOfBandData, String str, final ICommonCallback iCommonCallback) throws RemoteException {
        d("getSwitch");
        final irc ircVar = new irc();
        if (iCommonCallback == null) {
            ReleaseLogUtil.c("HiH_HiHlthKitExtBnd", "getSwitch", " callback is null");
            return;
        }
        if (str == null) {
            ReleaseLogUtil.c("HiH_HiHlthKitExtBnd", "getSwitch", " key is null");
            iCommonCallback.onResult(2, "");
            return;
        }
        final String e2 = e(outOfBandData);
        final String b2 = b(outOfBandData);
        final String str2 = "getSwitch";
        if (c(iov.c(this.j, outOfBandData, 101204, 2), false, new Action() { // from class: inh
            @Override // com.huawei.hihealthservice.hihealthkit.HiHealthKitExtendBinder.Action
            public final void operate(int i) {
                HiHealthKitExtendBinder.this.e(ircVar, e2, b2, str2, iCommonCallback, i);
            }
        })) {
            return;
        }
        LogUtil.c("HiHlthKitExtBnd", "getSwitch", " packageName:", e2);
        int callingUid = Binder.getCallingUid();
        LogUtil.a("HiHlthKitExtBnd", "getSwitch callingUid = ", Integer.valueOf(callingUid));
        ino.b(this.j).d(this.k.get(Integer.valueOf(callingUid)), iCommonCallback);
        c(ircVar, e2, b2, "getSwitch", 0);
    }

    public /* synthetic */ void e(irc ircVar, String str, String str2, String str3, ICommonCallback iCommonCallback, int i) throws RemoteException {
        b(ircVar, str, str2, str3, i);
        iCommonCallback.onResult(i, null);
    }

    @Override // com.huawei.hihealth.IHiHealthKitEx
    public void querySleepWakeTime(OutOfBandData outOfBandData, HiHealthDataQuery hiHealthDataQuery, int i, final IDataReadResultListener iDataReadResultListener) throws RemoteException {
        d("querySleepWakeTime");
        if (iDataReadResultListener == null) {
            ReleaseLogUtil.c("HiH_HiHlthKitExtBnd", "querySleepWakeTime", " callback is null");
            return;
        }
        if (hiHealthDataQuery == null) {
            ReleaseLogUtil.c("HiH_HiHlthKitExtBnd", "querySleepWakeTime", " query is null");
            iDataReadResultListener.onResult(Collections.EMPTY_LIST, 2, 2);
            return;
        }
        final irc ircVar = new irc();
        final String e2 = e(outOfBandData);
        final String b2 = b(outOfBandData);
        final String str = "querySleepWakeTime";
        if (c(iov.b(this.j, outOfBandData, this.f4164a, hiHealthDataQuery.getSampleType(), 1), true, new Action() { // from class: imu
            @Override // com.huawei.hihealthservice.hihealthkit.HiHealthKitExtendBinder.Action
            public final void operate(int i2) {
                HiHealthKitExtendBinder.this.b(ircVar, e2, b2, str, iDataReadResultListener, i2);
            }
        })) {
            return;
        }
        HiHealthKitQueryHelper hiHealthKitQueryHelper = new HiHealthKitQueryHelper(this.j, e2, b2);
        hiHealthDataQuery.setSampleType(22104);
        hiHealthKitQueryHelper.c(hiHealthDataQuery, iDataReadResultListener, new Cint(iov.a(outOfBandData, this.j, this.f4164a), "querySleepWakeTime"));
    }

    public /* synthetic */ void b(irc ircVar, String str, String str2, String str3, IDataReadResultListener iDataReadResultListener, int i) throws RemoteException {
        b(ircVar, str, str2, str3, i);
        iDataReadResultListener.onResult(null, i, 2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bBM_(Message message) throws RemoteException {
        boolean a2 = AuthorizationUtils.a(BaseApplication.getContext());
        ReleaseLogUtil.e("HiH_HiHlthKitExtBnd", "handleMessage requestAuthorization isAuthPermitted = ", Boolean.valueOf(a2));
        HashMap hashMap = new HashMap(16);
        if (a2) {
            hashMap.put("flag", String.valueOf(message.arg1));
            this.f.onResult(0, hashMap);
            Intent bBL_ = bBL_();
            irc ircVar = new irc();
            try {
                Bundle data = message.getData();
                if (data == null) {
                    return;
                }
                bBK_(bBL_, data);
                this.j.startActivity(bBL_);
                c(ircVar, this.c.getPackageName(), this.c.getAppId(), "requestAuthorization", 0);
                return;
            } catch (Exception e2) {
                ReleaseLogUtil.c("HiH_HiHlthKitExtBnd", "handleAuthMessage Exception: ", e2.getMessage());
            }
        }
        this.f.onResult(1003, hashMap);
    }

    private Intent bBL_() {
        Intent intent = new Intent();
        String str = d;
        intent.setClassName(str, "com.huawei.ui.main.stories.me.activity.thirdparty.HealthKitThirdPartyAuthActivity");
        intent.setPackage(str);
        intent.setFlags(268435456);
        intent.addFlags(AMapEngineUtils.HALF_MAX_P20_WIDTH);
        intent.addFlags(524288);
        intent.addFlags(8388608);
        return intent;
    }

    private void bBK_(Intent intent, Bundle bundle) {
        intent.putExtra("third_party_package_name", this.c.getPackageName());
        intent.putExtra("writeTypes", bundle.getIntArray("writeTypes"));
        intent.putExtra("readTypes", bundle.getIntArray("readTypes"));
        intent.putExtra(MapKeyNames.APP_INFO, d(this.c));
    }

    private boolean c(HiAppInfo hiAppInfo, QuickAppInfo quickAppInfo) {
        String appName;
        return (hiAppInfo == null || (appName = hiAppInfo.getAppName()) == null || !appName.equals("QuickApp_".concat(quickAppInfo.getName()))) ? false : true;
    }

    private HiAppInfo d(QuickAppInfo quickAppInfo) {
        LogUtil.c("HiHlthKitExtBnd", "enter saveAppInfo");
        HiAppInfo b2 = iip.b().b(quickAppInfo.getPackageName());
        if (c(b2, quickAppInfo)) {
            return b2;
        }
        if (b2 != null) {
            return b(quickAppInfo);
        }
        iip.b().e(ivw.b(quickAppInfo), 0);
        return iip.b().b(quickAppInfo.getPackageName());
    }

    private HiAppInfo b(QuickAppInfo quickAppInfo) {
        ReleaseLogUtil.e("HiH_HiHlthKitExtBnd", "updateAppInfo ", quickAppInfo.getPackageName(), " name: ", quickAppInfo.getName());
        int c2 = iip.b().c(ivw.b(quickAppInfo));
        if (c2 > 0) {
            ReleaseLogUtil.e("HiH_HiHlthKitExtBnd", "upDateAppName result = ", Integer.valueOf(c2));
            return iip.b().b(quickAppInfo.getPackageName());
        }
        ReleaseLogUtil.c("HiH_HiHlthKitExtBnd", "upDateAppName, fail");
        return null;
    }

    private boolean d(OutOfBandData outOfBandData) {
        return outOfBandData instanceof QuickAppInfo;
    }

    private void d(String str) {
        ReleaseLogUtil.e("HiH_HiHlthKitExtBnd", "enter ", str);
    }

    private void b(irc ircVar, String str, String str2, String str3, int i) {
        ReleaseLogUtil.d("HiH_HiHlthKitExtBnd", str3, ipd.b(i));
        c(ircVar, str, str2, str3, i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(irc ircVar, String str, String str2, String str3, int i) {
        ircVar.c(this.j, new iqy(str3, i, str, str2));
    }

    private void d(String str, QuickAppInfo quickAppInfo) {
        if (quickAppInfo != null) {
            LogUtil.c("HiHlthKitExtBnd", str, " caller:{", quickAppInfo.getPackageName(), "}");
        }
    }

    private void c(String str, String str2) {
        LogUtil.c("HiHlthKitExtBnd", str, " caller:{", str2, "}");
    }

    private boolean d(OutOfBandData outOfBandData, IDataOperateListener iDataOperateListener, String str, irc ircVar) throws RemoteException {
        if (!TextUtils.isEmpty(e(outOfBandData))) {
            return false;
        }
        iDataOperateListener.onResult(2, ipd.c(2));
        c(ircVar, e(outOfBandData), b(outOfBandData), str, 2);
        ReleaseLogUtil.d("HiH_HiHlthKitExtBnd", "package name is null");
        return true;
    }

    private irc d(OutOfBandData outOfBandData, String str, int i) {
        return new irc(this.j, new iqy(str, i, e(outOfBandData), b(outOfBandData)));
    }

    /* loaded from: classes7.dex */
    class c extends ITrackDataForDeveloper.Stub {
        ISportDataCallback b;

        c(ISportDataCallback iSportDataCallback) {
            this.b = iSportDataCallback;
        }

        @Override // com.huawei.health.ITrackDataForDeveloper
        public void onStateChanged(int i) {
            try {
                ReleaseLogUtil.e("HiH_HiHlthKitExtBnd", "onStateChanged state: ", Integer.valueOf(i));
                HiHealthKitExtendBinder.this.o.putInt("sportState", i);
                this.b.onDataChanged(i, HiHealthKitExtendBinder.this.o);
            } catch (RemoteException e) {
                ReleaseLogUtil.c("HiH_HiHlthKitExtBnd", "registerRealTimeSportCallback onDataChange RemoteException: ", e.getMessage());
            }
        }

        @Override // com.huawei.health.ITrackDataForDeveloper
        public void onDataChange(Bundle bundle) {
            try {
                if (bundle == null) {
                    LogUtil.b("HiHlthKitExtBnd", "TrackData onDataChanged, get null");
                    return;
                }
                JSONObject jSONObject = new JSONObject();
                for (String str : bundle.keySet()) {
                    jSONObject.put(str, bundle.get(str));
                }
                LogUtil.a("HiHlthKitExtBnd", "onDataChange data = " + jSONObject.toString());
                this.b.onDataChanged(bundle.getInt("sportState"), bundle);
                HiHealthKitExtendBinder.this.o = (Bundle) bundle.clone();
            } catch (RemoteException | JSONException e) {
                ReleaseLogUtil.d("HiH_HiHlthKitExtBnd", "registerRealTimeSportCallback onDataChange RemoteException", LogAnonymous.b(e));
            }
        }
    }

    @Override // com.huawei.hihealth.IHiHealthKitEx
    public int getAbilityVersion(OutOfBandData outOfBandData) {
        LogUtil.a("HiHlthKitExtBnd", "enter ", "getAbilityVersion");
        return 1;
    }

    @Override // com.huawei.hihealth.IHiHealthKitEx
    public int getServiceApiLevel(OutOfBandData outOfBandData) {
        ReleaseLogUtil.e("HiH_HiHlthKitExtBnd", "enter ", "getServiceApiLevel");
        irc ircVar = new irc();
        String e2 = e(outOfBandData);
        String b2 = b(outOfBandData);
        int c2 = iqz.c(this.j);
        ReleaseLogUtil.e("HiH_HiHlthKitExtBnd", "serviceApiLevel: ", Integer.valueOf(c2));
        c(ircVar, e2, b2, "getServiceApiLevel", 0);
        return c2;
    }

    @Override // com.huawei.hihealth.IHiHealthKitEx
    public String getCategory(int i) {
        return HiHealthDataType.e(i).name();
    }

    @Override // com.huawei.hihealth.IHiHealthKitEx
    public void registerDataAutoReport(OutOfBandData outOfBandData, DataReportModel dataReportModel, final IRegisterRealTimeCallback iRegisterRealTimeCallback) throws RemoteException {
        ReleaseLogUtil.e("HiH_HiHlthKitExtBnd", "enter ", "registerDataAutoReport");
        final irc ircVar = new irc();
        d("registerDataAutoReport");
        String e2 = e(outOfBandData);
        String b2 = b(outOfBandData);
        if (iRegisterRealTimeCallback == null) {
            ReleaseLogUtil.c("HiH_HiHlthKitExtBnd", "registerDataAutoReport", " callback is null");
            b(ircVar, e2, b2, "registerDataAutoReport", 2);
            return;
        }
        if (dataReportModel == null || e2 == null) {
            iRegisterRealTimeCallback.onResult(2, ipd.b(1001));
            b(ircVar, e2, b2, "registerDataAutoReport", 2);
            ReleaseLogUtil.c("HiH_HiHlthKitExtBnd", "registerDataAutoReport query or package name is null");
        } else if (ioy.b("registerDataAutoReport", iRegisterRealTimeCallback)) {
            if (!HiHealthKitDataChecker.e(dataReportModel)) {
                iRegisterRealTimeCallback.onResult(2, ipd.b(2));
                return;
            }
            int dataType = dataReportModel.getDataType();
            final iqy iqyVar = new iqy("registerDataAutoReport", 0, e2, b2, String.valueOf(dataType));
            if (CharacteristicConstant.ReportType.containsAuthIgnore(dataReportModel.getReportType()) || !c(iov.b(this.j, outOfBandData, this.f4164a, dataType, 1), false, new Action() { // from class: imt
                @Override // com.huawei.hihealthservice.hihealthkit.HiHealthKitExtendBinder.Action
                public final void operate(int i) {
                    HiHealthKitExtendBinder.this.c(iRegisterRealTimeCallback, ircVar, iqyVar, i);
                }
            })) {
                irn.b(this.j).c(dataReportModel, iRegisterRealTimeCallback, ircVar);
            }
        }
    }

    public /* synthetic */ void c(IRegisterRealTimeCallback iRegisterRealTimeCallback, irc ircVar, iqy iqyVar, int i) throws RemoteException {
        LogUtil.h("HiHlthKitExtBnd", ipd.b(i));
        iRegisterRealTimeCallback.onResult(i, ipd.b(1001));
        ircVar.c(this.j, iqyVar.b(i));
    }

    @Override // com.huawei.hihealth.IHiHealthKitEx
    public void unregisterDataAutoReport(OutOfBandData outOfBandData, DataReportModel dataReportModel, IRegisterRealTimeCallback iRegisterRealTimeCallback) throws RemoteException {
        ReleaseLogUtil.e("HiH_HiHlthKitExtBnd", "enter ", "unregisterDataAutoReport");
        irc ircVar = new irc();
        if (iRegisterRealTimeCallback == null) {
            ReleaseLogUtil.c("HiH_HiHlthKitExtBnd", "unregisterDataAutoReport", " callback is null");
        } else if (ioy.b("unregisterDataAutoReport", iRegisterRealTimeCallback)) {
            if (!HiHealthKitDataChecker.e(dataReportModel)) {
                iRegisterRealTimeCallback.onResult(2, ipd.b(2));
            } else {
                irn.b(this.j).d(dataReportModel, iRegisterRealTimeCallback, ircVar);
            }
        }
    }

    @Override // com.huawei.hihealth.IHiHealthKitEx
    public void getUserInfo(OutOfBandData outOfBandData, List<String> list, final ICommonCallback iCommonCallback) throws RemoteException {
        final irc ircVar = new irc();
        d(Constants.METHOD_GET_USER_INFO);
        final String e2 = e(outOfBandData);
        final String b2 = b(outOfBandData);
        if (iCommonCallback == null) {
            ReleaseLogUtil.c("HiH_HiHlthKitExtBnd", Constants.METHOD_GET_USER_INFO, " callback is null");
            b(ircVar, e2, b2, Constants.METHOD_GET_USER_INFO, 2);
            return;
        }
        if (koq.b(list)) {
            ReleaseLogUtil.c("HiH_HiHlthKitExtBnd", Constants.METHOD_GET_USER_INFO, " dataTypeList is empty");
            iCommonCallback.onResult(2, ipd.b(2));
            b(ircVar, e2, b2, Constants.METHOD_GET_USER_INFO, 2);
        } else if (ioy.b(Constants.METHOD_GET_USER_INFO, iCommonCallback)) {
            int b3 = iov.b(this.j, outOfBandData, this.f4164a, 101001, 1);
            final String str = Constants.METHOD_GET_USER_INFO;
            if (c(b3, true, new Action() { // from class: imw
                @Override // com.huawei.hihealthservice.hihealthkit.HiHealthKitExtendBinder.Action
                public final void operate(int i) {
                    HiHealthKitExtendBinder.this.d(ircVar, e2, b2, str, iCommonCallback, i);
                }
            })) {
                return;
            }
            irg.d(list, iCommonCallback, ircVar, new iqy(Constants.METHOD_GET_USER_INFO, 0, e2, b2), this.j);
        }
    }

    public /* synthetic */ void d(irc ircVar, String str, String str2, String str3, ICommonCallback iCommonCallback, int i) throws RemoteException {
        b(ircVar, str, str2, str3, i);
        iCommonCallback.onResult(i, ipd.b(i));
    }

    @Override // com.huawei.hihealth.IHiHealthKitEx
    public void setUserPreference(OutOfBandData outOfBandData, HiUserPreferenceData hiUserPreferenceData, boolean z, final IDataOperateListener iDataOperateListener) throws RemoteException {
        final irc ircVar = new irc();
        d("setUserPreference");
        final String e2 = e(outOfBandData);
        final String b2 = b(outOfBandData);
        if (iDataOperateListener == null) {
            ReleaseLogUtil.c("HiH_HiHlthKitExtBnd", "setUserPreference", " callback is null");
            b(ircVar, e2, b2, "setUserPreference", 2);
            return;
        }
        if (hiUserPreferenceData == null) {
            ReleaseLogUtil.c("HiH_HiHlthKitExtBnd", "setUserPreference", " userPreference is null");
            b(ircVar, e2, b2, "setUserPreference", 2);
            iDataOperateListener.onResult(2, null);
        } else if (ioy.b("setUserPreference", iDataOperateListener)) {
            final String str = "setUserPreference";
            if (c(iov.b(this.j, outOfBandData, this.f4164a, 0, 1), false, new Action() { // from class: imd
                @Override // com.huawei.hihealthservice.hihealthkit.HiHealthKitExtendBinder.Action
                public final void operate(int i) {
                    HiHealthKitExtendBinder.this.b(ircVar, e2, b2, str, iDataOperateListener, i);
                }
            })) {
                return;
            }
            boolean userPreference = HiHealthNativeApi.a(this.j).setUserPreference(new HiUserPreference(hiUserPreferenceData.getKey(), hiUserPreferenceData.getValue()), z);
            ReleaseLogUtil.e("HiH_HiHlthKitExtBnd", "setUserPreference result: ", Boolean.valueOf(userPreference));
            int i = !userPreference ? 1 : 0;
            iDataOperateListener.onResult(i, null);
            b(ircVar, e2, b2, "setUserPreference", i);
        }
    }

    public /* synthetic */ void b(irc ircVar, String str, String str2, String str3, IDataOperateListener iDataOperateListener, int i) throws RemoteException {
        b(ircVar, str, str2, str3, i);
        iDataOperateListener.onResult(i, null);
    }

    @Override // com.huawei.hihealth.IHiHealthKitEx
    public void getUserPreference(OutOfBandData outOfBandData, List<String> list, final IDataOperateListener iDataOperateListener) throws RemoteException {
        final irc ircVar = new irc();
        d("getUserPreference");
        final String e2 = e(outOfBandData);
        final String b2 = b(outOfBandData);
        if (HiCommonUtil.d(list)) {
            ReleaseLogUtil.c("HiH_HiHlthKitExtBnd", "getUserPreferenceList keys = null");
            b(ircVar, e2, b2, "getUserPreference", 2);
            iDataOperateListener.onResult(2, null);
            return;
        }
        if (ioy.b("getUserPreference", iDataOperateListener)) {
            final String str = "getUserPreference";
            if (c(iov.b(this.j, outOfBandData, this.f4164a, 0, 1), false, new Action() { // from class: iml
                @Override // com.huawei.hihealthservice.hihealthkit.HiHealthKitExtendBinder.Action
                public final void operate(int i) {
                    HiHealthKitExtendBinder.this.e(ircVar, e2, b2, str, iDataOperateListener, i);
                }
            })) {
                return;
            }
            List<HiUserPreference> userPreferenceList = HiHealthNativeApi.a(this.j).getUserPreferenceList(list);
            ArrayList arrayList = new ArrayList(10);
            for (HiUserPreference hiUserPreference : userPreferenceList) {
                HiUserPreferenceData hiUserPreferenceData = new HiUserPreferenceData();
                hiUserPreferenceData.setKey(hiUserPreference.getKey());
                hiUserPreferenceData.setValue(hiUserPreference.getValue());
                arrayList.add(hiUserPreferenceData);
            }
            iDataOperateListener.onResult(0, arrayList);
            b(ircVar, e2, b2, "getUserPreference", 0);
        }
    }

    public /* synthetic */ void e(irc ircVar, String str, String str2, String str3, IDataOperateListener iDataOperateListener, int i) throws RemoteException {
        LogUtil.h("HiHlthKitExtBnd", ipd.b(i));
        b(ircVar, str, str2, str3, i);
        iDataOperateListener.onResult(i, null);
    }

    @Override // com.huawei.hihealth.IHiHealthKitEx
    public void subscribeData(OutOfBandData outOfBandData, SubscribeModel subscribeModel, ISubScribeCallback iSubScribeCallback) throws RemoteException {
        ReleaseLogUtil.e("HiH_HiHlthKitExtBnd", "enter ", "subscribeData");
        iqy iqyVar = new iqy("subscribeData", 0, e(outOfBandData), b(outOfBandData));
        if (ioy.b("subscribeData", iSubScribeCallback)) {
            irc ircVar = new irc(this.j, iqyVar);
            Notification notification = new Notification(0, 4, ipd.b(4));
            if (!HiHealthKitDataChecker.c(subscribeModel)) {
                ReleaseLogUtil.d("HiH_HiHlthKitExtBnd", "subscribeData", "wrong subscribe model");
                notification.setErrorInfo(2, ipd.b(2));
                iSubScribeCallback.onResult(new ArrayList(), Arrays.asList(notification));
                ircVar.c().b(2);
                for (int i : subscribeModel.getDataTypes()) {
                    ircVar.c().e(String.valueOf(i));
                    ircVar.d();
                }
                return;
            }
            irp.d(this.j).a(subscribeModel, iSubScribeCallback, ircVar);
        }
    }

    @Override // com.huawei.hihealth.IHiHealthKitEx
    public void unSubscribeData(OutOfBandData outOfBandData, SubscribeModel subscribeModel, ISubScribeCallback iSubScribeCallback) throws RemoteException {
        ReleaseLogUtil.e("HiH_HiHlthKitExtBnd", "enter ", "unSubscribeData");
        irc ircVar = new irc(this.j, new iqy("unSubscribeData", 0, e(outOfBandData), b(outOfBandData)));
        if (ioy.b("unSubscribeData", iSubScribeCallback)) {
            Notification notification = new Notification(0, 4, ipd.b(4));
            if (!HiHealthKitDataChecker.c(subscribeModel)) {
                ReleaseLogUtil.d("HiH_HiHlthKitExtBnd", "unSubscribeData", " wrong subscribe model");
                notification.setErrorInfo(2, ipd.b(2));
                iSubScribeCallback.onResult(new ArrayList(), Arrays.asList(notification));
                ircVar.c().b(2);
                for (int i : subscribeModel.getDataTypes()) {
                    ircVar.c().e(String.valueOf(i));
                    ircVar.d();
                }
                return;
            }
            irp.d(this.j).c(subscribeModel, iSubScribeCallback, ircVar);
        }
    }

    @Override // com.huawei.hihealth.IHiHealthKitEx
    public void syncData(OutOfBandData outOfBandData, int[] iArr, final ICommonCallback iCommonCallback) throws RemoteException {
        final irc ircVar = new irc();
        d("syncData");
        final String e2 = e(outOfBandData);
        final String b2 = b(outOfBandData);
        if (iCommonCallback == null) {
            ReleaseLogUtil.c("HiH_HiHlthKitExtBnd", "syncData", " callback is null");
            b(ircVar, e2, b2, "syncData", 2);
        } else if (!HiHealthKitDataChecker.e(iArr)) {
            iCommonCallback.onResult(2, ipd.b(2));
            b(ircVar, e2, b2, "syncData", 2);
        } else if (ioy.b("syncData", iCommonCallback)) {
            final String str = "syncData";
            if (c(iov.b(this.j, outOfBandData, this.f4164a, 0, 2), false, new Action() { // from class: imj
                @Override // com.huawei.hihealthservice.hihealthkit.HiHealthKitExtendBinder.Action
                public final void operate(int i) {
                    HiHealthKitExtendBinder.this.h(ircVar, e2, b2, str, iCommonCallback, i);
                }
            })) {
                return;
            }
            inq.b().e(iArr, iCommonCallback);
        }
    }

    public /* synthetic */ void h(irc ircVar, String str, String str2, String str3, ICommonCallback iCommonCallback, int i) throws RemoteException {
        LogUtil.h("HiHlthKitExtBnd", ipd.b(i));
        b(ircVar, str, str2, str3, i);
        iCommonCallback.onResult(i, null);
    }

    @Override // com.huawei.hihealth.IHiHealthKitEx
    public void queryTrends(OutOfBandData outOfBandData, TrendQuery trendQuery, int i, final IDataReadResultListener iDataReadResultListener) throws RemoteException {
        ReleaseLogUtil.e("HiH_HiHlthKitExtBnd", "enter ", "queryTrends");
        final String e2 = e(outOfBandData);
        final String b2 = b(outOfBandData);
        final irc ircVar = new irc(this.j, new iqy("queryTrends", 0, e2, b2));
        if (iDataReadResultListener == null) {
            ReleaseLogUtil.c("HiH_HiHlthKitExtBnd", "queryTrends", " callback is null");
            iDataReadResultListener.onResult(Collections.EMPTY_LIST, 2, 2);
            b(ircVar, e2, b2, "queryTrends", 2);
        } else if (!HiHealthKitDataChecker.e(trendQuery, i)) {
            iDataReadResultListener.onResult(Collections.EMPTY_LIST, 2, 2);
            b(ircVar, e2, b2, "queryTrends", 2);
        } else if (!iqz.b(trendQuery)) {
            iDataReadResultListener.onResult(Collections.EMPTY_LIST, 30, 2);
            b(ircVar, e2, b2, "queryTrends", 30);
        } else if (ioy.b("queryTrends", iDataReadResultListener)) {
            final String str = "queryTrends";
            if (c(iov.b(this.j, outOfBandData, this.f4164a, 0, 2), false, new Action() { // from class: inc
                @Override // com.huawei.hihealthservice.hihealthkit.HiHealthKitExtendBinder.Action
                public final void operate(int i2) {
                    HiHealthKitExtendBinder.this.e(ircVar, e2, b2, str, iDataReadResultListener, i2);
                }
            })) {
                return;
            }
            new HiHealthKitQueryHelper(this.j, e2, b2).e(trendQuery, i, iDataReadResultListener);
        }
    }

    public /* synthetic */ void e(irc ircVar, String str, String str2, String str3, IDataReadResultListener iDataReadResultListener, int i) throws RemoteException {
        LogUtil.h("HiHlthKitExtBnd", ipd.b(i));
        b(ircVar, str, str2, str3, i);
        iDataReadResultListener.onResult(Collections.EMPTY_LIST, i, 2);
    }

    @Override // com.huawei.hihealth.IHiHealthKitEx
    public void connectSportDevice(OutOfBandData outOfBandData, int i, final ICommonCallback iCommonCallback) throws RemoteException {
        d("connectSportDevice");
        final irc ircVar = new irc();
        final String e2 = e(outOfBandData);
        final String b2 = b(outOfBandData);
        if (iCommonCallback == null) {
            ReleaseLogUtil.c("HiH_HiHlthKitExtBnd", "connectSportDevice", " callback is null");
            b(ircVar, e2, b2, "connectSportDevice", 2);
        } else if (iqz.e("connectSportDevice", i, d(outOfBandData, "connectSportDevice", 30), iCommonCallback) && ioy.b("connectSportDevice", iCommonCallback)) {
            final String str = "connectSportDevice";
            if (c(iov.b(this.j, outOfBandData, this.f4164a, 101003, 1), true, new Action() { // from class: imn
                @Override // com.huawei.hihealthservice.hihealthkit.HiHealthKitExtendBinder.Action
                public final void operate(int i2) {
                    HiHealthKitExtendBinder.this.b(ircVar, e2, b2, str, iCommonCallback, i2);
                }
            })) {
                return;
            }
            final String str2 = "connectSportDevice";
            ino.b(this.j).c(i, new IBaseCommonCallback.Stub() { // from class: com.huawei.hihealthservice.hihealthkit.HiHealthKitExtendBinder.2
                @Override // com.huawei.health.IBaseCommonCallback
                public void onResponse(int i2, String str3) throws RemoteException {
                    ReleaseLogUtil.e("HiH_HiHlthKitExtBnd", str2, " result code ", Integer.valueOf(i2));
                    iCommonCallback.onResult(i2, str3);
                    HiHealthKitExtendBinder.this.c(ircVar, e2, b2, str2, i2);
                }
            });
        }
    }

    public /* synthetic */ void b(irc ircVar, String str, String str2, String str3, ICommonCallback iCommonCallback, int i) throws RemoteException {
        b(ircVar, str, str2, str3, i);
        iCommonCallback.onResult(i, "connectSportDevice permission denied");
    }

    public void onDestroy() {
        ReleaseLogUtil.e("HiH_HiHlthKitExtBnd", "onDestroy");
    }
}
