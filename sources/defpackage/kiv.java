package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hihealth.HiAggregateOption;
import com.huawei.hihealth.HiHealthClient;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiSubscribeListener;
import com.huawei.hihealth.data.listener.HiUnSubscribeListener;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.kiv;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.LocalBroadcast;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONArray;
import org.json.JSONException;

/* loaded from: classes5.dex */
public class kiv {
    private final BroadcastReceiver b;
    private static final Map<String, Set<Integer>> e = new ConcurrentHashMap();

    /* renamed from: a, reason: collision with root package name */
    private static final Object f14405a = new Object();

    private kiv() {
        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() { // from class: kiv.5
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                if (kiv.e.isEmpty()) {
                    LogUtil.a("DEVMGR_ThreeCircleReSyncMgr", "CloudSyncListener SubscribeMap empty, receive content ignored");
                    return;
                }
                if (intent == null) {
                    LogUtil.a("DEVMGR_ThreeCircleReSyncMgr", "CloudSyncListener Intent empty, receive content ignored");
                    return;
                }
                int intExtra = intent.getIntExtra("com.huawei.hihealth.action_sync_status", 6);
                LogUtil.a("DEVMGR_ThreeCircleReSyncMgr", "CloudSyncListener Action = ", intent.getAction(), ", Status=", Integer.valueOf(intExtra));
                if (2 != intExtra) {
                    return;
                }
                LogUtil.a("DEVMGR_ThreeCircleReSyncMgr", "CloudSyncListener ReSync trigger by cloudSync finish");
                kiv.this.b(keb.d("DEVMGR_ThreeCircleReSyncMgr"));
            }
        };
        this.b = broadcastReceiver;
        BroadcastManagerUtil.bFA_(BaseApplication.e(), broadcastReceiver, new IntentFilter("com.huawei.hihealth.action_sync"), LocalBroadcast.c, null);
    }

    public static kiv b() {
        return c.e;
    }

    static class c {
        private static final kiv e = new kiv();
    }

    public void e(final DeviceInfo deviceInfo) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: kix
            @Override // java.lang.Runnable
            public final void run() {
                kiv.this.a(deviceInfo);
            }
        });
    }

    /* synthetic */ void a(DeviceInfo deviceInfo) {
        if (deviceInfo.getDeviceConnectState() == 2) {
            d(deviceInfo);
            c(deviceInfo);
            ReleaseLogUtil.e("DEVMGR_ThreeCircleReSyncMgr", "processByConnectState() reSync, subscribe and register by device connect");
        } else {
            g(deviceInfo);
            ReleaseLogUtil.e("DEVMGR_ThreeCircleReSyncMgr", "processByConnectState() unsubscribe and unregister by device disconnect, state=", Integer.valueOf(deviceInfo.getDeviceConnectState()));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(DeviceInfo deviceInfo) {
        e(deviceInfo, 60000L);
    }

    private void d(DeviceInfo deviceInfo) {
        e(deviceInfo, 1800000L);
    }

    private void e(DeviceInfo deviceInfo, long j) {
        kjc kjcVar = new kjc();
        if (kjcVar.isNeedSync(deviceInfo, j)) {
            ArrayList arrayList = new ArrayList();
            long currentTimeMillis = System.currentTimeMillis();
            c(arrayList, currentTimeMillis);
            c(arrayList, jdl.v(currentTimeMillis));
            long d = jdl.d(currentTimeMillis, -6);
            long c2 = kiz.c(currentTimeMillis, 86400000L);
            arrayList.add(b(kiz.c(), d, c2, 1, 86400000L));
            int length = kiz.d().length;
            int[] iArr = new int[length + 1];
            System.arraycopy(kiz.d(), 0, iArr, 0, kiz.d().length);
            iArr[length] = DicDataTypeUtil.DataType.ACTIVE_HOUR_IS_ACTIVE.value();
            arrayList.add(b(iArr, d, c2, 1, 86400000L));
            kjcVar.syncDataToDevice(deviceInfo, kjcVar.preProcess(kjcVar.fetchData(arrayList)));
        }
    }

    private void c(DeviceInfo deviceInfo) {
        if (!cwi.c(deviceInfo, 154)) {
            ReleaseLogUtil.d("DEVMGR_ThreeCircleReSyncMgr", "subscribeThreeCircleData() failed: device capability NOT support");
        } else {
            ReleaseLogUtil.e("DEVMGR_ThreeCircleReSyncMgr", "subscribeThreeCircleData() enter");
            HiHealthNativeApi.a(BaseApplication.e()).subscribeHiHealthData(Arrays.asList(Integer.valueOf(DicDataTypeUtil.DataType.ACTIVE_HOUR.value()), Integer.valueOf(DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA.value()), 1), new b(deviceInfo.getDeviceIdentify()));
        }
    }

    private void g(DeviceInfo deviceInfo) {
        if (!cwi.c(deviceInfo, 154)) {
            LogUtil.h("DEVMGR_ThreeCircleReSyncMgr", "unsubscribeThreeCircleData() failed: device capability NOT support");
            return;
        }
        final String deviceIdentify = deviceInfo.getDeviceIdentify();
        Set<Integer> b2 = b(deviceIdentify);
        if (TextUtils.isEmpty(deviceIdentify) || b2 == null) {
            LogUtil.h("DEVMGR_ThreeCircleReSyncMgr", "unsubscribeThreeCircleData() failed, device has NOT subscribe");
        } else {
            ReleaseLogUtil.e("DEVMGR_ThreeCircleReSyncMgr", "unsubscribeThreeCircleData() enter");
            HiHealthNativeApi.a(BaseApplication.e()).unSubscribeHiHealthData(new ArrayList(b2), new HiUnSubscribeListener() { // from class: kiu
                @Override // com.huawei.hihealth.data.listener.HiUnSubscribeListener
                public final void onResult(boolean z) {
                    kiv.this.a(deviceIdentify, z);
                }
            });
        }
    }

    /* synthetic */ void a(String str, boolean z) {
        ReleaseLogUtil.e("DEVMGR_ThreeCircleReSyncMgr", "unsubscribeThreeCircleData onResult() ", Boolean.valueOf(z));
        if (z) {
            d(str, null);
        }
    }

    private Set<Integer> b(String str) {
        Set<Integer> set;
        synchronized (f14405a) {
            set = e.get(str);
        }
        return set;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(String str, Collection<Integer> collection) {
        synchronized (f14405a) {
            if (collection == null) {
                e.remove(str);
                return;
            }
            Map<String, Set<Integer>> map = e;
            Set<Integer> set = map.get(str);
            if (set == null) {
                map.put(str, new HashSet(collection));
            } else {
                set.addAll(collection);
                map.put(str, set);
            }
        }
    }

    private void c(List<HiAggregateOption> list, long j) {
        long b2 = kiz.b(j, 86400000L);
        long c2 = kiz.c(j, 86400000L);
        list.add(b(new int[]{7}, b2, c2, 2, 1800000L));
        list.add(b(kiz.e(), b2, c2, 1, 1800000L));
        list.add(b(new int[]{DicDataTypeUtil.DataType.ACTIVE_HOUR_IS_ACTIVE.value()}, b2, c2, 1, 3600000L));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public HiAggregateOption b(int[] iArr, long j, long j2, int i, long j3) {
        String str;
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        hiAggregateOption.setType(iArr);
        hiAggregateOption.setTimeInterval(j, j2);
        hiAggregateOption.setAggregateType(i);
        if (j3 == 86400000) {
            hiAggregateOption.setGroupUnitType(3);
            str = "WriteBackKey_DAY_";
        } else if (j3 == 3600000) {
            hiAggregateOption.setGroupUnitType(2);
            str = "WriteBackKey_HOUR_";
        } else if (j3 == 1800000) {
            hiAggregateOption.setGroupUnitType(1);
            hiAggregateOption.setGroupUnitSize(30);
            str = "WriteBackKey_HALFHOUR_";
        } else {
            return new HiAggregateOption();
        }
        String[] strArr = new String[iArr.length];
        for (int i2 = 0; i2 < iArr.length; i2++) {
            strArr[i2] = str + iArr[i2];
        }
        hiAggregateOption.setConstantsKey(strArr);
        return hiAggregateOption;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static DeviceInfo a(String str) {
        DeviceInfo d;
        if (TextUtils.isEmpty(str) || (d = keb.d("DEVMGR_ThreeCircleReSyncMgr")) == null || !str.equals(d.getDeviceIdentify())) {
            return null;
        }
        return d;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public long d(HiHealthData hiHealthData) {
        if (hiHealthData == null) {
            return 0L;
        }
        try {
            return new JSONArray(hiHealthData.getChangeDataInfos()).getJSONObject(0).getLong("start_time");
        } catch (JSONException unused) {
            health.compact.a.util.LogUtil.e("DEVMGR_ThreeCircleReSyncMgr", "getSubscribeStartTime JSONException");
            return 0L;
        }
    }

    class b implements HiSubscribeListener {
        private final String d;

        b(String str) {
            this.d = str;
        }

        @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
        public void onResult(List<Integer> list, List<Integer> list2) {
            LogUtil.a("DEVMGR_ThreeCircleReSyncMgr", "SubscribeDataListener onResult() successList=", HiJsonUtil.e(list), ", failList.size=", HiJsonUtil.e(list2));
            if (koq.b(list)) {
                return;
            }
            kiv.this.d(this.d, list);
        }

        @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
        public void onChange(final int i, HiHealthClient hiHealthClient, String str, HiHealthData hiHealthData, long j) {
            final long d = kiv.this.d(hiHealthData);
            if (d <= 0) {
                LogUtil.h("DEVMGR_ThreeCircleReSyncMgr", "data onChange from cloud.");
            } else {
                ThreadPoolManager.d().execute(new Runnable() { // from class: kiy
                    @Override // java.lang.Runnable
                    public final void run() {
                        kiv.b.this.c(i, d);
                    }
                });
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public void c(int i, long j) {
            kjb kjbVar = new kjb();
            DeviceInfo a2 = kiv.a(this.d);
            if (kjbVar.isNeedSync(a2, 60000L)) {
                ArrayList arrayList = new ArrayList(4);
                long b = kiz.b(j, 3600000L);
                long c = kiz.c(j, 3600000L);
                long b2 = kiz.b(j, 86400000L);
                long c2 = kiz.c(j, 86400000L);
                if (i == 1) {
                    long b3 = kiz.b(j, 1800000L);
                    long c3 = kiz.c(j, 1800000L);
                    arrayList.add(kiv.this.b(new int[]{7}, b3, c3, 2, 1800000L));
                    arrayList.add(kiv.this.b(kiz.e(), b3, c3, 1, 1800000L));
                    arrayList.add(kiv.this.b(new int[]{DicDataTypeUtil.DataType.ACTIVE_HOUR_IS_ACTIVE.value()}, b, c, 1, 3600000L));
                    arrayList.add(kiv.this.b(kiz.c(), b2, c2, 1, 86400000L));
                    arrayList.add(kiv.this.b(new int[]{DicDataTypeUtil.DataType.ACTIVE_HOUR_IS_ACTIVE.value()}, b2, c2, 1, 86400000L));
                    LogUtil.a("DEVMGR_ThreeCircleReSyncMgr", "SubscribeDataListener onChange() sportData");
                } else if (i == DicDataTypeUtil.DataType.ACTIVE_HOUR.value()) {
                    arrayList.add(kiv.this.b(new int[]{DicDataTypeUtil.DataType.ACTIVE_HOUR_IS_ACTIVE.value()}, b, c, 1, 3600000L));
                    arrayList.add(kiv.this.b(kiz.c(), b2, c2, 1, 86400000L));
                    arrayList.add(kiv.this.b(new int[]{DicDataTypeUtil.DataType.ACTIVE_HOUR_IS_ACTIVE.value()}, b2, c2, 1, 86400000L));
                    LogUtil.a("DEVMGR_ThreeCircleReSyncMgr", "SubscribeDataListener onChange() activeHour");
                } else {
                    if (i != DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA.value()) {
                        return;
                    }
                    arrayList.add(kiv.this.b(kiz.d(), b2, c2, 1, 86400000L));
                    LogUtil.a("DEVMGR_ThreeCircleReSyncMgr", "SubscribeDataListener onChange() threeCircle");
                }
                kjbVar.syncDataToDevice(a2, kjbVar.preProcess(kjbVar.fetchData(arrayList)));
            }
        }
    }
}
