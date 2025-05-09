package com.huawei.hwdevice.mainprocess.mgr.hwfitnessmgr;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.devicemgr.business.callback.DataReceiveCallback;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hihealth.HiSampleConfig;
import com.huawei.hihealth.data.listener.HiDataOperateListener;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwdevice.mainprocess.mgr.hwfitnessmgr.SportGoalMgr;
import com.huawei.hwdevice.phoneprocess.mgr.threecircle.strategy.SyncStrategy;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.cuk;
import defpackage.cvn;
import defpackage.cvq;
import defpackage.cvr;
import defpackage.cwd;
import defpackage.cwg;
import defpackage.cwl;
import defpackage.jhc;
import defpackage.jpt;
import defpackage.koq;
import defpackage.nip;
import defpackage.njc;
import defpackage.nji;
import defpackage.njj;
import health.compact.a.CommonUtil;
import health.compact.a.HEXUtils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/* loaded from: classes5.dex */
public class SportGoalMgr implements DataReceiveCallback {

    /* renamed from: a, reason: collision with root package name */
    private Context f6274a;
    private njc b = new njc();
    private String c;

    /* JADX INFO: Access modifiers changed from: private */
    public static long c(long j, long j2) {
        return j > 0 ? j : j2;
    }

    public SportGoalMgr(Context context) {
        this.f6274a = context;
    }

    @Override // com.huawei.health.devicemgr.business.callback.DataReceiveCallback
    public void onDataReceived(int i, DeviceInfo deviceInfo, cvr cvrVar) {
        long d2;
        String e;
        LogUtil.a("UIDV_SportGoalMgr", "onDataReceived errorCode ", Integer.valueOf(i));
        if (!(cvrVar instanceof cvq)) {
            LogUtil.h("UIDV_SportGoalMgr", "message not instanceOf SampleConfig");
            return;
        }
        List<cvn> configInfoList = ((cvq) cvrVar).getConfigInfoList();
        if (configInfoList == null || configInfoList.size() == 0) {
            LogUtil.h("UIDV_SportGoalMgr", "onDataReceived sampleConfigInfos empty");
            return;
        }
        cvn cvnVar = configInfoList.get(0);
        if (cvnVar == null) {
            LogUtil.h("UIDV_SportGoalMgr", "configInfo == null");
            return;
        }
        long a2 = cvnVar.a();
        String a3 = HEXUtils.a(cvnVar.b());
        if (d(Long.toString(a2))) {
            nji a4 = a(a3);
            d2 = a4.b();
            e = HiJsonUtil.e(a4);
        } else {
            njc c = c(a3);
            d2 = c.d();
            e = HiJsonUtil.e(c);
        }
        ReleaseLogUtil.e("UIDV_SportGoalMgr", "onDataReceived configAction", Integer.valueOf(cvnVar.e()), " configID ", Long.valueOf(a2), "  configData is ", e, " timeStamp ", Long.valueOf(d2));
        njj.a("9002", Long.toString(a2), e, new HiDataOperateListener() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwfitnessmgr.SportGoalMgr.3
            @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
            public void onResult(int i2, Object obj) {
                LogUtil.a("UIDV_SportGoalMgr", "onDataReceived setSampleConfig errorCode: ", Integer.valueOf(i2), ", object: ", obj);
            }
        }, d2);
    }

    private nji a(String str) {
        LogUtil.a("UIDV_SportGoalMgr", "getSportSwitchBean,hexContent: ", str);
        nji njiVar = new nji();
        try {
            List<cwd> e = new cwl().a(str).e();
            if (e != null && e.size() != 0) {
                for (cwd cwdVar : e) {
                    int w = CommonUtil.w(cwdVar.e());
                    if (w == 1) {
                        njiVar.e(CommonUtil.y(cwdVar.c()) * 1000);
                    } else if (w == 2) {
                        njiVar.c(Integer.toString(CommonUtil.w(cwdVar.c())));
                    }
                }
                return njiVar;
            }
            LogUtil.h("UIDV_SportGoalMgr", "tlvList is empty");
            return njiVar;
        } catch (cwg unused) {
            LogUtil.b("UIDV_SportGoalMgr", "parseData is error");
            return njiVar;
        }
    }

    private njc c(String str) {
        LogUtil.a("UIDV_SportGoalMgr", "getGoalModel,hexContent: ", str);
        njc njcVar = new njc();
        try {
            List<cwd> e = new cwl().a(str).e();
            if (e != null && e.size() != 0) {
                for (cwd cwdVar : e) {
                    int w = CommonUtil.w(cwdVar.e());
                    if (w == 1) {
                        njcVar.c(CommonUtil.y(cwdVar.c()) * 1000);
                    } else if (w == 2) {
                        njcVar.e(CommonUtil.w(cwdVar.c()));
                    }
                }
                return njcVar;
            }
            LogUtil.h("UIDV_SportGoalMgr", "getGoalModel tlvList is empty");
            return njcVar;
        } catch (cwg unused) {
            LogUtil.b("UIDV_SportGoalMgr", "getGoalModel parseData is error");
            return njcVar;
        }
    }

    private boolean d(String str) {
        return str.equals("900200011") || str.equals("900200004") || str.equals("900200010");
    }

    public void e() {
        LogUtil.a("UIDV_SportGoalMgr", "setThreeCircleGoal enter ");
        b();
        DeviceInfo d2 = jpt.d("UIDV_SportGoalMgr");
        if (d2 != null) {
            ArrayList arrayList = new ArrayList(4);
            arrayList.add("900200006");
            arrayList.add("900200008");
            arrayList.add("900200009");
            arrayList.add("900200007");
            njj.d("9002", arrayList, new d(d2));
            ArrayList arrayList2 = new ArrayList(3);
            arrayList2.add("900200004");
            arrayList2.add("900200010");
            arrayList2.add("900200011");
            njj.d("9002", arrayList2, new a(d2, arrayList2));
        }
    }

    static class a implements HiDataReadResultListener {
        private final List<String> b;
        private final DeviceInfo d;

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResultIntent(int i, Object obj, int i2, int i3) {
        }

        private a(DeviceInfo deviceInfo, List<String> list) {
            this.d = deviceInfo;
            this.b = list;
        }

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResult(Object obj, int i, int i2) {
            if (i != 0) {
                LogUtil.b("UIDV_SportGoalMgr", "HiErrorCode is ", Integer.valueOf(i));
                return;
            }
            HashMap<String, nji> e = e();
            if (!koq.e(obj, HiSampleConfig.class)) {
                LogUtil.h("UIDV_SportGoalMgr", "list SwitchDataReadListener isListTypeMatch false ");
                d(e);
                return;
            }
            List list = (List) obj;
            if (koq.b(list)) {
                LogUtil.h("UIDV_SportGoalMgr", "SwitchDataReadListener is empty ");
                d(e);
                return;
            }
            for (int i3 = 0; i3 < list.size(); i3++) {
                HiSampleConfig hiSampleConfig = (HiSampleConfig) list.get(i3);
                String configData = hiSampleConfig.getConfigData();
                if (!TextUtils.isEmpty(configData)) {
                    nji njiVar = (nji) HiJsonUtil.e(configData, nji.class);
                    if (njiVar != null) {
                        njiVar.e(SportGoalMgr.c(njiVar.b(), hiSampleConfig.getModifiedTime()));
                    }
                    e.put(hiSampleConfig.getConfigId(), njiVar);
                }
            }
            d(e);
        }

        private void d(HashMap<String, nji> hashMap) {
            for (Map.Entry<String, nji> entry : hashMap.entrySet()) {
                if (entry.getKey() != null && entry.getValue() != null) {
                    SportGoalMgr.d(entry.getKey(), entry.getValue().e(), this.d, entry.getValue().b());
                } else {
                    LogUtil.b("UIDV_SportGoalMgr", "key or value is null.");
                }
            }
        }

        private HashMap<String, nji> e() {
            HashMap<String, nji> hashMap = new HashMap<>();
            if (koq.b(this.b)) {
                LogUtil.b("UIDV_SportGoalMgr", "mSwitchIdLists is null");
                return hashMap;
            }
            for (String str : this.b) {
                nji njiVar = new nji();
                njiVar.c("1");
                hashMap.put(str, njiVar);
            }
            return hashMap;
        }
    }

    public void e(final String str) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: jhi
            @Override // java.lang.Runnable
            public final void run() {
                SportGoalMgr.this.b(str);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: f, reason: merged with bridge method [inline-methods] */
    public void b(String str) {
        LogUtil.a("UIDV_SportGoalMgr", "setThreeCircleGoalOrSwitch configId ", str);
        DeviceInfo d2 = jpt.d("UIDV_SportGoalMgr");
        if (d2 == null) {
            LogUtil.h("UIDV_SportGoalMgr", "setThreeCircleGoalOrSwitch deviceInfo is null or goalValue isEmpty");
            return;
        }
        if (d2.getDeviceConnectState() != 2) {
            LogUtil.h("UIDV_SportGoalMgr", "setThreeCircleGoalOrSwitch device is not connected");
            return;
        }
        if (jhc.b(d2)) {
            if (d(str)) {
                ArrayList arrayList = new ArrayList(1);
                arrayList.add(str);
                njj.d("9002", arrayList, new a(d2, arrayList));
            } else {
                ArrayList arrayList2 = new ArrayList(1);
                arrayList2.add(str);
                njj.d("9002", arrayList2, new d(d2));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void d(String str, String str2, DeviceInfo deviceInfo, long j) {
        cvq cvqVar = new cvq();
        cvqVar.setSrcPkgName(SyncStrategy.SRC_PKG_NAME);
        cvqVar.setWearPkgName(SyncStrategy.WEAR_PKG_NAME);
        cvqVar.setCommandId(1);
        cvn cvnVar = new cvn();
        cvnVar.e(CommonUtil.n(BaseApplication.e(), str));
        cvnVar.d(1);
        StringBuilder sb = new StringBuilder(16);
        long j2 = j / 1000;
        sb.append(HEXUtils.e(1));
        sb.append(HEXUtils.e(HEXUtils.e(j2).length() / 2));
        sb.append(HEXUtils.e(j2));
        long n = CommonUtil.n(BaseApplication.e(), str2);
        sb.append(HEXUtils.e(2));
        sb.append(HEXUtils.e(HEXUtils.e(n).length() / 2));
        sb.append(HEXUtils.e(n));
        cvnVar.c(HEXUtils.c(sb.toString()));
        ArrayList arrayList = new ArrayList(5);
        arrayList.add(cvnVar);
        cvqVar.setConfigInfoList(arrayList);
        ReleaseLogUtil.e("UIDV_SportGoalMgr", "sendThreeCircleGoalWhenChange configId ", str, " curTimestamp ", Long.valueOf(j2), " goal ", Long.valueOf(n), " isSuccess ", Boolean.valueOf(cuk.b().sendSampleConfigCommand(deviceInfo, cvqVar, "UIDV_SportGoalMgr")));
    }

    private void b() {
        cuk.b().registerDeviceSampleListener(SyncStrategy.SRC_PKG_NAME, this);
    }

    static class d implements HiDataReadResultListener {
        private DeviceInfo c;
        private WeakReference<SportGoalMgr> e;

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResultIntent(int i, Object obj, int i2, int i3) {
        }

        private d(SportGoalMgr sportGoalMgr, DeviceInfo deviceInfo) {
            this.e = new WeakReference<>(sportGoalMgr);
            this.c = deviceInfo;
        }

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResult(Object obj, int i, int i2) {
            if (this.c == null) {
                LogUtil.h("UIDV_SportGoalMgr", "SportAchievementGoalDataCallback mWeakReference|mDeviceInfo == null ");
                return;
            }
            SportGoalMgr sportGoalMgr = this.e.get();
            if (sportGoalMgr == null) {
                LogUtil.h("UIDV_SportGoalMgr", "SportAchievementGoalDataCallback hwFitnessManager == null ");
                return;
            }
            if (!koq.e(obj, HiSampleConfig.class)) {
                LogUtil.h("UIDV_SportGoalMgr", "setThreeCircleGoal objData is not instanceof HashMap");
                return;
            }
            for (HiSampleConfig hiSampleConfig : (List) obj) {
                if (hiSampleConfig != null) {
                    String configData = hiSampleConfig.getConfigData();
                    String configId = hiSampleConfig.getConfigId();
                    if (TextUtils.isEmpty(configData)) {
                        continue;
                    } else {
                        njc njcVar = (njc) HiJsonUtil.e(configData, njc.class);
                        if (Objects.equals(configId, sportGoalMgr.c) && njcVar.equals(sportGoalMgr.b)) {
                            return;
                        }
                        int d = SportGoalMgr.d(njcVar, nip.d(configId));
                        long b = SportGoalMgr.b(njcVar, hiSampleConfig.getModifiedTime());
                        sportGoalMgr.c = configId;
                        sportGoalMgr.b = njcVar;
                        SportGoalMgr.d(configId, Integer.toString(d), this.c, b);
                    }
                }
            }
        }
    }

    public void a() {
        c();
    }

    private void c() {
        cuk.b().unRegisterDeviceSampleListener(SyncStrategy.SRC_PKG_NAME);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int d(njc njcVar, int i) {
        return njcVar != null ? njcVar.e() : i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static long b(njc njcVar, long j) {
        return njcVar != null ? c(njcVar.d(), j) : j;
    }
}
