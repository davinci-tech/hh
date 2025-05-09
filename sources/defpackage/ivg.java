package defpackage;

import android.content.Context;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.SparseArray;
import android.util.SparseIntArray;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.huawei.haf.common.dfx.DfxUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.arkuix.utils.ArkUIXConstants;
import com.huawei.hihealth.HiDeviceInfo;
import com.huawei.hihealth.HiHealthClient;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.ISubscribeListener;
import com.huawei.hihealth.IUnSubscribeListener;
import com.huawei.hihealth.data.type.HiHealthDataType;
import com.huawei.hihealth.data.type.HiSubscribeType;
import com.huawei.hihealth.dictionary.HiHealthDictManager;
import com.huawei.hihealth.dictionary.constants.ProductMap;
import com.huawei.hihealth.dictionary.constants.ProductMapInfo;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hms.hihealth.HiHealthStatusCodes;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.ivg;
import health.compact.a.CommonUtil;
import health.compact.a.HiCommonUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import health.compact.a.utils.StringUtils;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes4.dex */
public class ivg {
    private static SparseIntArray e;

    /* renamed from: a, reason: collision with root package name */
    private HashMap<Integer, ISubscribeListener> f13624a;
    private HashMap<Integer, Integer> b;
    private HashMap<Integer, e> c;
    private SparseArray<RemoteCallbackList<ISubscribeListener>> d;
    private ExecutorService i;

    private boolean c(int i) {
        return i == 22 || i == 13 || i == 24 || i == 25;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        e = sparseIntArray;
        sparseIntArray.append(1, 1);
        e.append(2, 4);
        e.append(3, 2);
        e.append(4, 10);
        e.append(7, 5);
        e.append(9, 3);
        e.append(16, 18);
        e.append(11, 14);
    }

    private ivg() {
        this.d = new SparseArray<>(10);
        this.f13624a = new HashMap<>(16);
        this.b = new HashMap<>(16);
        this.i = Executors.newSingleThreadExecutor();
        this.c = new HashMap<>(16);
    }

    static class c {
        private static final ivg c = new ivg();
    }

    public static ivg c() {
        return c.c;
    }

    public void b(final List list, final ISubscribeListener iSubscribeListener, final List<Integer> list2, final List<Integer> list3) {
        if (this.i.isShutdown()) {
            LogUtil.b("HiH_ListenerManager", "registerListener mSubscribeThread is closed! Restarting...");
            this.i = Executors.newSingleThreadExecutor();
        }
        this.i.execute(new Runnable() { // from class: ivg.1
            @Override // java.lang.Runnable
            public void run() {
                List list4 = list;
                if (list4 == null || list4.isEmpty() || iSubscribeListener == null) {
                    LogUtil.h("HiH_ListenerManager", "registerListener typeList is null or empty");
                    igb.b(iSubscribeListener, (List) null, (List) null);
                    return;
                }
                for (Object obj : new CopyOnWriteArrayList(list)) {
                    if (obj instanceof Integer) {
                        Integer num = (Integer) obj;
                        Integer e2 = ivg.this.e(num, iSubscribeListener);
                        if (e2 == null) {
                            list3.add(num);
                        } else {
                            list2.add(e2);
                        }
                    }
                }
                igb.b(iSubscribeListener, list2, list3);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Integer e(Integer num, ISubscribeListener iSubscribeListener) {
        if (num == null || iSubscribeListener == null) {
            LogUtil.h("registerListener not right", new Object[0]);
            return null;
        }
        int intValue = num.intValue();
        if (!HiSubscribeType.b(intValue)) {
            LogUtil.h("registerListener this type can not be subscribed ,type = ", Integer.valueOf(intValue));
            return null;
        }
        Integer a2 = a(intValue);
        if (a2 == null) {
            LogUtil.h("registerListener can not get a key", new Object[0]);
            return null;
        }
        b(a2, Integer.valueOf(intValue), iSubscribeListener);
        return a2;
    }

    public void d(final List list, final IUnSubscribeListener iUnSubscribeListener) {
        if (this.i.isShutdown()) {
            LogUtil.b("HiH_ListenerManager", "unregisterListener mSubscribeThread is closed! Restarting...");
            this.i = Executors.newSingleThreadExecutor();
        }
        this.i.execute(new Runnable() { // from class: ivg.3
            @Override // java.lang.Runnable
            public void run() {
                LogUtil.a("HiH_ListenerManager", "unregisterListener unsubscribeList = ", list);
                List list2 = list;
                if (list2 == null || list2.isEmpty()) {
                    igb.a(iUnSubscribeListener, false);
                    return;
                }
                for (Object obj : list) {
                    if (obj instanceof Integer) {
                        ivg.this.a((Integer) obj);
                    }
                }
                igb.a(iUnSubscribeListener, true);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Integer num) {
        if (num == null) {
            LogUtil.h("HiH_ListenerManager", "unregisterListenerByKey key = null");
            return;
        }
        Integer num2 = this.b.get(num);
        if (num2 == null) {
            ReleaseLogUtil.d("HiH_ListenerManager", "This key hasn't type, key is ", num);
            return;
        }
        LogUtil.c("HiH_ListenerManager", "unregisterListenerByKey type = ", num2);
        RemoteCallbackList<ISubscribeListener> remoteCallbackList = this.d.get(num2.intValue());
        if (remoteCallbackList == null) {
            LogUtil.h("HiH_ListenerManager", "unregisterListenerByKey listeners == null");
            return;
        }
        ISubscribeListener iSubscribeListener = this.f13624a.get(num);
        LogUtil.c("HiH_ListenerManager", "unregisterListenerByKey size = ", Integer.valueOf(this.f13624a.size()));
        if (iSubscribeListener == null) {
            LogUtil.h("HiH_ListenerManager", "unregisterListenerByKey tempListener == null");
            return;
        }
        remoteCallbackList.unregister(iSubscribeListener);
        this.f13624a.remove(num);
        this.b.remove(num);
        LogUtil.c("HiH_ListenerManager", "unregisterListenerByKey removed key = ", num);
        e eVar = this.c.get(num2);
        if (eVar != null) {
            eVar.c();
            this.c.remove(num2);
            return;
        }
        LogUtil.h("HiH_ListenerManager", "unregisterListenerByType tempMergeData =null");
        Iterator<Integer> it = this.c.keySet().iterator();
        while (it.hasNext()) {
            LogUtil.c("HiH_ListenerManager", "typeKey = ", Integer.valueOf(it.next().intValue()));
        }
        Iterator<Integer> it2 = HiHealthDictManager.d((Context) null).g(num2.intValue()).iterator();
        while (it2.hasNext()) {
            int intValue = it2.next().intValue();
            LogUtil.a("HiH_ListenerManager", "mMergeDataMap.remove subType = ", Integer.valueOf(intValue));
            e eVar2 = this.c.get(Integer.valueOf(intValue));
            if (eVar2 == null) {
                LogUtil.h("HiH_ListenerManager", "unregisterListenerByType subMergeData =null");
            } else {
                eVar2.c();
                this.c.remove(Integer.valueOf(intValue));
            }
        }
    }

    public void b(int i, ikv ikvVar) {
        int d = d(i);
        LogUtil.c("HiH_ListenerManager", "getHomeCardType subscribe type = ", Integer.valueOf(d));
        a(d, "sync download", ikvVar);
    }

    public static int d(int i) {
        return (e.indexOfKey(i) >= 0 || !HiHealthDictManager.d((Context) null).l(i)) ? e.get(i) : i;
    }

    public void c(List<HiHealthData> list, ikv ikvVar) {
        if (list == null || ikvVar == null) {
            ReleaseLogUtil.e("HiH_ListenerManager", "hiHealthContext is null");
            return;
        }
        List<Integer> a2 = iwd.a(list);
        ReleaseLogUtil.e("HiH_ListenerManager", "startLChg subList=", a2);
        if (HiCommonUtil.d(a2)) {
            return;
        }
        HiHealthClient b = b(ikvVar);
        Iterator<Integer> it = a2.iterator();
        HiHealthData hiHealthData = null;
        while (it.hasNext()) {
            int intValue = it.next().intValue();
            switch (intValue) {
                case 13:
                case 22:
                case 24:
                case 25:
                    if (list.size() != 1) {
                        LogUtil.b("HiH_ListenerManager", "startListenerChange hiHealthDatas.size() != 1");
                        break;
                    } else {
                        hiHealthData = list.get(0);
                        if (this.c.get(Integer.valueOf(intValue)) == null) {
                            LogUtil.b("HiH_ListenerManager", "startListenerChange getHiMergeDataByType == null");
                            break;
                        } else {
                            this.c.get(Integer.valueOf(intValue)).c(hiHealthData, b);
                            continue;
                        }
                    }
                case 14:
                case 18:
                case 21:
                case 23:
                default:
                    if (HiHealthDataType.e(intValue) == HiHealthDataType.Category.REALTIME && HiHealthDictManager.d((Context) null).d(intValue) != null) {
                        hiHealthData = list.get(0);
                        break;
                    }
                    break;
                case 15:
                    if (list.size() != 1) {
                        LogUtil.b("HiH_ListenerManager", "running_posture: startListenerChange hiHealthDatas.size() != 1");
                        break;
                    } else {
                        hiHealthData = list.get(0);
                        for (HiHealthData hiHealthData2 : e(hiHealthData)) {
                            LogUtil.a("HiH_ListenerManager", "running_posture hiHealthData = ", hiHealthData2);
                            int subType = hiHealthData2.getSubType();
                            LogUtil.c("HiH_ListenerManager", "runningPostureSubscribeType = ", Integer.valueOf(subType));
                            if (!this.c.containsKey(Integer.valueOf(subType))) {
                                this.c.put(Integer.valueOf(subType), new e());
                            }
                            this.c.get(Integer.valueOf(subType)).c(hiHealthData2, b);
                        }
                        break;
                    }
                case 16:
                case 17:
                case 19:
                case 20:
                    hiHealthData = list.get(0);
                    break;
            }
            List<HashMap<String, Object>> d = d(list);
            if (hiHealthData != null) {
                hiHealthData.setChangeDataInfos(HiJsonUtil.e(d));
            } else {
                hiHealthData = new HiHealthData();
                hiHealthData.setChangeDataInfos(HiJsonUtil.e(d));
            }
            if (intValue != 15) {
                LogUtil.a("HiH_ListenerManager", "subscribeType = ", Integer.valueOf(intValue), ", no running_posture data, startChange");
                a(intValue, ArkUIXConstants.INSERT, b, hiHealthData);
            }
        }
    }

    private List<HiHealthData> e(HiHealthData hiHealthData) {
        LinkedList linkedList = new LinkedList();
        try {
            HashMap hashMap = (HashMap) new Gson().fromJson(hiHealthData.getFieldsValue(), new TypeToken<HashMap<Integer, Double>>() { // from class: ivg.2
            }.getType());
            if (hashMap == null || hashMap.size() == 0) {
                ReleaseLogUtil.d("HiH_ListenerManager", "no fieldValueList parameter");
                return linkedList;
            }
            HashMap hashMap2 = new HashMap();
            try {
                hashMap2 = (HashMap) new Gson().fromJson(hiHealthData.getString("realtime_back_data_interval"), new TypeToken<HashMap<Integer, Integer>>() { // from class: ivg.5
                }.getType());
            } catch (JsonSyntaxException e2) {
                ReleaseLogUtil.d("HiH_ListenerManager", "backDataIntervalMap: FromJson JsonSyntaxException", LogAnonymous.b((Throwable) e2));
            }
            for (Map.Entry entry : hashMap.entrySet()) {
                HiHealthData hiHealthData2 = new HiHealthData(hiHealthData.getType());
                hiHealthData2.setStartTime(hiHealthData.getStartTime());
                hiHealthData2.setEndTime(hiHealthData.getEndTime());
                hiHealthData2.setDeviceUuid(hiHealthData.getDeviceUuid());
                hiHealthData2.setSubType(((Integer) entry.getKey()).intValue());
                hiHealthData2.setValue(((Double) entry.getValue()).doubleValue());
                hiHealthData2.putInt("realtime_judge_interval", hiHealthData.getInt("realtime_judge_interval"));
                hiHealthData2.putInt("realtime_back_data_interval", hiHealthData.getInt("realtime_back_data_interval"));
                if (hiHealthData.getInt("realtime_back_data_interval") == 0 && hashMap2.get(Integer.valueOf(hiHealthData2.getSubType())) != null) {
                    hiHealthData2.putInt("realtime_back_data_interval", ((Integer) hashMap2.get(Integer.valueOf(hiHealthData2.getSubType()))).intValue());
                }
                linkedList.add(hiHealthData2);
            }
            return linkedList;
        } catch (JsonSyntaxException e3) {
            ReleaseLogUtil.d("HiH_ListenerManager", "fieldValueList: FromJson JsonSyntaxException", LogAnonymous.b((Throwable) e3));
            return linkedList;
        }
    }

    private List<HashMap<String, Object>> d(List<HiHealthData> list) {
        ArrayList arrayList = new ArrayList(list.size());
        for (HiHealthData hiHealthData : list) {
            HashMap hashMap = new HashMap(4);
            hashMap.put("start_time", Long.valueOf(hiHealthData.getStartTime()));
            hashMap.put("end_time", Long.valueOf(hiHealthData.getEndTime()));
            hashMap.put("type", Integer.valueOf(hiHealthData.getType()));
            hashMap.put("device_uniquecode", hiHealthData.getDeviceUuid());
            arrayList.add(hashMap);
        }
        return arrayList;
    }

    public void a(int i, String str, ikv ikvVar) {
        if (ikvVar == null) {
            return;
        }
        LogUtil.a("HiH_ListenerManager", "startListenerChange subscribeType = ", Integer.valueOf(i), ",changeKey = ", str, ",hiHealthContext = ", ikvVar);
        a(i, str, b(ikvVar), null);
    }

    public void e(List<Integer> list, String str, ikv ikvVar) {
        if (list == null || ikvVar == null) {
            return;
        }
        LogUtil.a("HiH_ListenerManager", "startListenerChange subscribeTypes = ", list, ",changeKey = ", str, ",hiHealthContext = ", ikvVar);
        if (HiCommonUtil.d(list)) {
            return;
        }
        HiHealthClient b = b(ikvVar);
        Iterator<Integer> it = list.iterator();
        while (it.hasNext()) {
            a(it.next().intValue(), str, b, null);
        }
    }

    public void a() {
        LogUtil.a("HiH_ListenerManager", "onDestroy");
        ExecutorService executorService = this.i;
        if (executorService != null) {
            executorService.shutdown();
        }
        HashMap<Integer, ISubscribeListener> hashMap = this.f13624a;
        if (hashMap != null) {
            hashMap.clear();
        }
        HashMap<Integer, Integer> hashMap2 = this.b;
        if (hashMap2 != null) {
            hashMap2.clear();
        }
        HashMap<Integer, e> hashMap3 = this.c;
        if (hashMap3 != null) {
            hashMap3.clear();
        }
        SparseArray<RemoteCallbackList<ISubscribeListener>> sparseArray = this.d;
        if (sparseArray != null) {
            sparseArray.clear();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final int i, final String str, final HiHealthClient hiHealthClient, final HiHealthData hiHealthData) {
        if (!HiSubscribeType.b(i)) {
            ReleaseLogUtil.d("HiH_ListenerManager", "startListenerChange type is not valid subscribeType = ", Integer.valueOf(i));
            return;
        }
        if (this.i.isShutdown()) {
            ReleaseLogUtil.c("HiH_ListenerManager", "startListenerChange mSubscribeThread is closed! Restarting...");
            this.i = Executors.newSingleThreadExecutor();
        }
        this.i.execute(new Runnable() { // from class: ive
            @Override // java.lang.Runnable
            public final void run() {
                ivg.this.d(i, hiHealthClient, str, hiHealthData);
            }
        });
    }

    /* synthetic */ void d(int i, HiHealthClient hiHealthClient, String str, HiHealthData hiHealthData) {
        int i2;
        int i3;
        RemoteCallbackList<ISubscribeListener> remoteCallbackList = this.d.get(i);
        if (remoteCallbackList == null) {
            return;
        }
        try {
            try {
                int beginBroadcast = remoteCallbackList.beginBroadcast();
                int i4 = 0;
                while (i4 < beginBroadcast) {
                    try {
                        ISubscribeListener broadcastItem = remoteCallbackList.getBroadcastItem(i4);
                        if (broadcastItem != null) {
                            i2 = beginBroadcast;
                            i3 = i4;
                            try {
                                broadcastItem.onChange(i, hiHealthClient, str, hiHealthData, System.currentTimeMillis());
                            } catch (RemoteException e2) {
                                e = e2;
                                String message = e.getMessage();
                                if (TextUtils.isEmpty(message)) {
                                    Throwable cause = e.getCause();
                                    message = cause == null ? "throwable null" : cause.getMessage();
                                }
                                ReleaseLogUtil.c("HiH_ListenerManager", "beginBroadcast RemoteException = ", message);
                                String d = DfxUtils.d(Thread.currentThread(), e);
                                if (CommonUtil.as()) {
                                    ReleaseLogUtil.c("HiH_ListenerManager", "Exception stackTrace ", d);
                                }
                                i4 = i3 + 1;
                                beginBroadcast = i2;
                            }
                        } else {
                            i2 = beginBroadcast;
                            i3 = i4;
                        }
                    } catch (RemoteException e3) {
                        e = e3;
                        i2 = beginBroadcast;
                        i3 = i4;
                    }
                    i4 = i3 + 1;
                    beginBroadcast = i2;
                }
                try {
                    remoteCallbackList.finishBroadcast();
                } catch (IllegalStateException e4) {
                    ReleaseLogUtil.c("HiH_ListenerManager", "finishBroadcast IllegalStateException = ", e4.getMessage());
                }
            } finally {
            }
        } catch (IllegalStateException e5) {
            ReleaseLogUtil.c("HiH_ListenerManager", "beginBroadcast IllegalStateException = ", e5.getMessage());
            try {
                remoteCallbackList.finishBroadcast();
            } catch (IllegalStateException e6) {
                ReleaseLogUtil.c("HiH_ListenerManager", "finishBroadcast IllegalStateException = ", e6.getMessage());
            }
        } catch (Exception e7) {
            ReleaseLogUtil.c("HiH_ListenerManager", "beginBroadcast Exception", LogAnonymous.b((Throwable) e7));
            try {
                remoteCallbackList.finishBroadcast();
            } catch (IllegalStateException e8) {
                ReleaseLogUtil.c("HiH_ListenerManager", "finishBroadcast IllegalStateException = ", e8.getMessage());
            }
        }
    }

    private Integer a(int i) {
        SecureRandom secureRandom = new SecureRandom();
        int i2 = 0;
        Integer num = null;
        while (true) {
            try {
                if (HiHealthDictManager.d(BaseApplication.getContext()).d(i) != null) {
                    num = Integer.valueOf(secureRandom.nextInt());
                } else {
                    num = Integer.valueOf(Integer.parseInt(new StringBuffer(16).append(secureRandom.nextInt(21474)).append(i + 10000).toString()));
                }
            } catch (NumberFormatException e2) {
                ReleaseLogUtil.e("HiH_ListenerManager", "createListenerKey NumberFormatException", LogAnonymous.b((Throwable) e2));
            }
            if (i2 >= 10) {
                return null;
            }
            if (this.f13624a.get(num) == null) {
                return num;
            }
            i2++;
        }
    }

    private void b(Integer num, Integer num2, ISubscribeListener iSubscribeListener) {
        LogUtil.c("HiH_ListenerManager", "subscribeTypeWithKey key = ", num, ",type = ", num2);
        RemoteCallbackList<ISubscribeListener> remoteCallbackList = this.d.get(num2.intValue());
        if (remoteCallbackList == null) {
            remoteCallbackList = new RemoteCallbackList<>();
        }
        remoteCallbackList.register(iSubscribeListener);
        this.d.put(num2.intValue(), remoteCallbackList);
        this.f13624a.put(num, iSubscribeListener);
        this.b.put(num, num2);
        if (c(num2.intValue()) && this.c.get(num2) == null) {
            this.c.put(num2, new e());
        }
    }

    private HiHealthClient b(ikv ikvVar) {
        if (ikvVar == null) {
            return null;
        }
        HiHealthClient hiHealthClient = new HiHealthClient();
        hiHealthClient.setPackageName(ikvVar.f());
        hiHealthClient.setAppId(ikvVar.e());
        return hiHealthClient;
    }

    class e {
        private TimerTask e;
        private TimerTask g;
        private HiHealthData i;
        private String j;
        private HiHealthClient l;
        private int o;
        private final AtomicBoolean n = new AtomicBoolean(true);
        private int m = 10000;

        /* renamed from: a, reason: collision with root package name */
        private int f13627a = 1000;
        private HashMap<String, HiHealthData> b = new HashMap<>();
        private HashMap<String, String> f = new HashMap<>();
        private Timer h = new Timer("HiH_ListenerManager");
        private Timer c = new Timer("HiH_ListenerManager");

        e() {
            this.g = new TimerTask() { // from class: ivg.e.3
                @Override // java.util.TimerTask, java.lang.Runnable
                public void run() {
                    e.this.g();
                }
            };
            this.e = new TimerTask() { // from class: ivg.e.2
                @Override // java.util.TimerTask, java.lang.Runnable
                public void run() {
                    e.this.d();
                }
            };
        }

        private void d(int i) {
            if (i == 50007 || i == 50008 || i == 50009) {
                int d = igm.e().d();
                for (String str : b(i).keySet()) {
                    iwe.d(BaseApplication.getContext(), str + "_lastCount_" + i, 0, d);
                }
                iwe.d(BaseApplication.getContext(), "baseCount_" + i, 0, d);
            }
        }

        void c(final HiHealthData hiHealthData, final HiHealthClient hiHealthClient) {
            ThreadPoolManager.d().c("HiH_ListenerManager", new Runnable() { // from class: ivl
                @Override // java.lang.Runnable
                public final void run() {
                    ivg.e.this.b(hiHealthData, hiHealthClient);
                }
            });
        }

        /* synthetic */ void b(HiHealthData hiHealthData, HiHealthClient hiHealthClient) {
            LogUtil.c("HiH_ListenerManager", Integer.valueOf(this.o), " receiveData data:", hiHealthData);
            if (hiHealthData == null) {
                return;
            }
            this.l = hiHealthClient;
            long currentTimeMillis = System.currentTimeMillis();
            hiHealthData.setCreateTime(currentTimeMillis);
            hiHealthData.setEndTime(currentTimeMillis);
            String d = d(hiHealthData);
            this.b.put(d, hiHealthData);
            int d2 = igm.e().d();
            if (this.n.compareAndSet(true, false)) {
                a(hiHealthData);
                this.j = d;
            }
            int intValue = hiHealthData.getIntValue();
            String string = hiHealthData.getString("realtime_merge_mode");
            if (StringUtils.g(string)) {
                LogUtil.b("HiH_ListenerManager", Integer.valueOf(this.o), " the mergeMode is empty value, set the mergeMode to INSTANTANEOUS_MODE");
                string = "instantaneousMode";
            }
            int type = hiHealthData.getType();
            if (d.equals(this.j)) {
                if ("instantaneousMode".equals(string)) {
                    this.i = hiHealthData;
                    return;
                }
                if ("incrementMode".equals(string)) {
                    int a2 = iwe.a(BaseApplication.getContext(), "baseCount_" + type, d2, 0);
                    int a3 = iwe.a(BaseApplication.getContext(), d + "_lastCount_" + type, d2, 0);
                    int i = a2 + (intValue > a3 ? intValue - a3 : 0);
                    hiHealthData.setValue(i);
                    this.i = hiHealthData;
                    iwe.d(BaseApplication.getContext(), "baseCount_" + type, i, d2);
                } else {
                    ReleaseLogUtil.c("HiH_ListenerManager", Integer.valueOf(this.o), " the mergeMode is invalid: ", string);
                }
            }
            if ("incrementMode".equals(string)) {
                if (intValue <= iwe.a(BaseApplication.getContext(), d + "_lastCount_" + type, d2, 0)) {
                    return;
                }
                iwe.d(BaseApplication.getContext(), d + "_lastCount_" + type, intValue, d2);
            }
        }

        private void a(HiHealthData hiHealthData) {
            LogUtil.a("HiH_ListenerManager", Integer.valueOf(this.o), " receiveData the data is FirstReceiveDate");
            this.o = hiHealthData.getType();
            int i = hiHealthData.getInt("realtime_judge_interval");
            if (i > 0) {
                this.m = i;
            }
            int i2 = hiHealthData.getInt("realtime_back_data_interval");
            if (i2 > 0) {
                this.f13627a = i2;
            }
            d(hiHealthData.getType());
            Timer timer = this.h;
            if (timer == null || this.c == null) {
                LogUtil.h("HiH_ListenerManager", "User stopJudgeAndUpdateTask, no need to dealFirstReceiveData");
                return;
            }
            timer.schedule(this.g, 0L, this.f13627a);
            Timer timer2 = this.c;
            TimerTask timerTask = this.e;
            long j = this.m;
            timer2.schedule(timerTask, j, j);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void g() {
            ThreadPoolManager.d().c("HiH_ListenerManager", new Runnable() { // from class: ivh
                @Override // java.lang.Runnable
                public final void run() {
                    ivg.e.this.b();
                }
            });
        }

        /* synthetic */ void b() {
            LogUtil.a("HiH_ListenerManager", Integer.valueOf(this.o), " begin to postCurrentBestData, currentBestSourceType = ", this.j);
            if (this.i == null) {
                LogUtil.h("HiH_ListenerManager", "mCurrentBestData is null , no need to postCurrentBestData");
                return;
            }
            LogUtil.a("HiH_ListenerManager", Integer.valueOf(this.o), " postCurrentBestData data: ", this.i);
            int a2 = HiSubscribeType.a(this.i.getType());
            ivg.this.a(a2, "scheduledMergeData", this.l, this.i);
            if (a2 == 15) {
                LogUtil.a("HiH_ListenerManager", Integer.valueOf(this.o), " running_posture data clear every period");
                this.i = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void d() {
            ThreadPoolManager.d().c("HiH_ListenerManager", new Runnable() { // from class: ivj
                @Override // java.lang.Runnable
                public final void run() {
                    ivg.e.this.e();
                }
            });
        }

        /* synthetic */ void e() {
            ArrayList arrayList = new ArrayList(this.b.values());
            this.b.clear();
            if (arrayList.isEmpty()) {
                LogUtil.h("HiH_ListenerManager", Integer.valueOf(this.o), " judgeNextPeriodBestSourceType the lastPeriodRealDataList isEmpty,no need to judge");
                HiHealthData hiHealthData = this.i;
                if (hiHealthData != null && HiSubscribeType.a(hiHealthData.getType()) == 13) {
                    LogUtil.a("HiH_ListenerManager", "subscribeType is realTime heartRate, clear currentBestData when no data is received in the previous period.");
                    this.i = null;
                }
            } else {
                Collections.sort(arrayList, new C0308e());
                this.j = d((HiHealthData) arrayList.get(0));
            }
            LogUtil.a("HiH_ListenerManager", Integer.valueOf(this.o), " judgeNextPeriodBestSourceType the currentBestSourceType is ", this.j);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Map<String, Integer> b(int i) {
            if (i == 50001) {
                return iic.e();
            }
            if (i != 300001) {
                switch (i) {
                    case HiHealthStatusCodes.UNSUPPORTED_PLATFORM_ERROR /* 50007 */:
                        return iic.c();
                    case HiHealthStatusCodes.TRY_AGAIN_ERROR /* 50008 */:
                        return iic.b();
                    case HiHealthStatusCodes.ACTIVITY_RECORD_ENDED_ERROR /* 50009 */:
                        return iic.a();
                    default:
                        LogUtil.b("HiH_ListenerManager", Integer.valueOf(this.o), " the dateType is not support.");
                        return new HashMap();
                }
            }
            return iic.d();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public String d(HiHealthData hiHealthData) {
            String deviceUuid = hiHealthData.getDeviceUuid();
            String dataSource = hiHealthData.getDataSource();
            String str = StringUtils.i(dataSource) ? dataSource : deviceUuid;
            if (this.f.containsKey(str)) {
                String str2 = this.f.get(str);
                LogUtil.c("HiH_ListenerManager", Integer.valueOf(this.o), " key: ", str, " source: ", str2);
                return str2;
            }
            if (StringUtils.i(dataSource)) {
                if (b(hiHealthData.getType()).containsKey(dataSource)) {
                    LogUtil.a("HiH_ListenerManager", Integer.valueOf(this.o), " get by dataSource, dataSource: ", dataSource, " source: ", dataSource);
                    this.f.put(dataSource, dataSource);
                    return dataSource;
                }
                ReleaseLogUtil.c("HiH_ListenerManager", Integer.valueOf(this.o), " the dataSource is not in merge range");
                return "";
            }
            HiDeviceInfo d = ijf.d(BaseApplication.getContext()).d(deviceUuid);
            if (d == null) {
                ReleaseLogUtil.c("HiH_ListenerManager", Integer.valueOf(this.o), " the deviceInfo is null, the deviceUuid: ", deviceUuid);
                return "";
            }
            List<ProductMapInfo> b = ProductMap.b("deviceId", String.valueOf(d.getDeviceType()));
            if (koq.c(b)) {
                String e = b.get(0).e();
                LogUtil.a("HiH_ListenerManager", Integer.valueOf(this.o), " get by Uuid, deviceUuid: ", deviceUuid, " source: ", e);
                this.f.put(deviceUuid, e);
                return e;
            }
            ReleaseLogUtil.c("HiH_ListenerManager", Integer.valueOf(this.o), " has not to insert valid deviceTypeMsg");
            return "";
        }

        public void c() {
            ThreadPoolManager.d().c("HiH_ListenerManager", new Runnable() { // from class: ivk
                @Override // java.lang.Runnable
                public final void run() {
                    ivg.e.this.a();
                }
            });
        }

        /* synthetic */ void a() {
            Timer timer = this.h;
            if (timer != null) {
                timer.cancel();
                this.h = null;
            }
            TimerTask timerTask = this.g;
            if (timerTask != null) {
                timerTask.cancel();
                this.g = null;
            }
            Timer timer2 = this.c;
            if (timer2 != null) {
                timer2.cancel();
                this.c = null;
            }
            TimerTask timerTask2 = this.e;
            if (timerTask2 != null) {
                timerTask2.cancel();
                this.e = null;
            }
            d(this.o);
            LogUtil.a("HiH_ListenerManager", Integer.valueOf(this.o), " stopJudgeAndUpdateTask,stop to backData and judge best source type");
        }

        /* renamed from: ivg$e$e, reason: collision with other inner class name */
        class C0308e implements Comparator<HiHealthData> {
            private C0308e() {
            }

            @Override // java.util.Comparator
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public int compare(HiHealthData hiHealthData, HiHealthData hiHealthData2) {
                String d = e.this.d(hiHealthData);
                String d2 = e.this.d(hiHealthData2);
                if (d == null || d2 == null) {
                    LogUtil.c("HiH_ListenerManager", Integer.valueOf(e.this.o), " HiRealDataMergeComparator oldPriorityType==null || newPriorityType==null");
                    return 0;
                }
                e eVar = e.this;
                Integer num = (Integer) eVar.b(eVar.o).get(d2);
                e eVar2 = e.this;
                Integer num2 = (Integer) eVar2.b(eVar2.o).get(d);
                if (num == null) {
                    num = r1;
                }
                return num.compareTo(num2 != null ? num2 : 0);
            }
        }
    }
}
