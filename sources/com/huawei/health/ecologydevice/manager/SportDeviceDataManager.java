package com.huawei.health.ecologydevice.manager;

import android.util.SparseArray;
import androidx.core.util.Pair;
import com.google.gson.Gson;
import com.google.json.JsonSanitizer;
import com.huawei.health.device.api.HealthDeviceEntryApi;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hihealth.data.model.HiTrackMetaData;
import com.huawei.hihealth.device.open.HealthDevice;
import com.huawei.hihealthservice.old.model.OldToNewMotionPath;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.ble.BleConstants;
import defpackage.dcz;
import defpackage.iyl;
import health.compact.a.Services;
import java.util.List;

/* loaded from: classes3.dex */
public class SportDeviceDataManager {
    private static final String[] e = {"distance", "duration"};

    /* renamed from: a, reason: collision with root package name */
    private b f2307a;
    private SportDataListener c;

    public interface SportDataListener {
        void onNewLastData(long j, int i, int i2);
    }

    public String a() {
        return null;
    }

    public SportDeviceDataManager(String str, String str2, SportDataListener sportDataListener) {
        this.c = sportDataListener;
        b bVar = new b(str, str2);
        this.f2307a = bVar;
        Object[] objArr = new Object[2];
        objArr[0] = "Sport device product info:";
        objArr[1] = bVar.f2309a ? this.f2307a.h.g() : "setup failed";
        LogUtil.c("PDSPORT_SportDeviceDataMgr", objArr);
    }

    public void i() {
        b(System.currentTimeMillis());
    }

    private void b(long j) {
        if (this.f2307a.e == -1) {
            LogUtil.c("PDSPORT_SportDeviceDataMgr", "Unkown device type, skip history data query");
            return;
        }
        LogUtil.a("PDSPORT_SportDeviceDataMgr", "queryLastHistoryData");
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setType(new int[]{this.f2307a.d});
        hiDataReadOption.setConstantsKey(e);
        hiDataReadOption.setTimeInterval(0L, j);
        hiDataReadOption.setCount(10);
        hiDataReadOption.setSortOrder(1);
        HiHealthManager.d(BaseApplication.getContext()).readHiHealthData(hiDataReadOption, new HiDataReadResultListener() { // from class: com.huawei.health.ecologydevice.manager.SportDeviceDataManager.2
            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResult(Object obj, int i, int i2) {
                LogUtil.a("PDSPORT_SportDeviceDataMgr", "queryLastHistoryData onResult errorCode:", Integer.valueOf(i));
                if (obj instanceof SparseArray) {
                    Object obj2 = ((SparseArray) obj).get(SportDeviceDataManager.this.f2307a.d);
                    if (obj2 instanceof List) {
                        SportDeviceDataManager.this.d((List) obj2);
                        return;
                    } else {
                        LogUtil.h("PDSPORT_SportDeviceDataMgr", "error result list.");
                        return;
                    }
                }
                LogUtil.h("PDSPORT_SportDeviceDataMgr", "error data format");
            }

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i, Object obj, int i2, int i3) {
                LogUtil.a("PDSPORT_SportDeviceDataMgr", "queryLastHistoryData onResultIntent errorCode = ", Integer.valueOf(i2));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(List list) {
        if (list.isEmpty() || !(list.get(0) instanceof HiHealthData)) {
            LogUtil.c("PDSPORT_SportDeviceDataMgr", "error result data: empty list or unrecognized data type.");
            return;
        }
        long j = 0;
        for (Object obj : list) {
            if (obj instanceof HiHealthData) {
                HiHealthData hiHealthData = (HiHealthData) obj;
                LogUtil.c("PDSPORT_SportDeviceDataMgr", "queryLastHistoryData resultData:", hiHealthData);
                HiTrackMetaData hiTrackMetaData = (HiTrackMetaData) new Gson().fromJson(JsonSanitizer.sanitize(hiHealthData.getMetaData()), HiTrackMetaData.class);
                if (hiTrackMetaData != null && hiTrackMetaData.getSportType() == this.f2307a.e) {
                    Pair<Integer, Integer> d = d(hiTrackMetaData);
                    LogUtil.c("PDSPORT_SportDeviceDataMgr", "query lastTime:", Long.valueOf(hiHealthData.getStartTime()), ", lastValue:", d);
                    SportDataListener sportDataListener = this.c;
                    if (sportDataListener != null) {
                        sportDataListener.onNewLastData(hiHealthData.getStartTime(), d.first.intValue(), d.second.intValue());
                        return;
                    }
                    return;
                }
                j = hiHealthData.getStartTime();
            }
        }
        if (list.size() != 10 || j <= 0) {
            return;
        }
        LogUtil.a("PDSPORT_SportDeviceDataMgr", "continue, query startTime:", Long.valueOf(j));
        b(j);
    }

    private Pair<Integer, Integer> d(HiTrackMetaData hiTrackMetaData) {
        int i;
        int totalDistance = hiTrackMetaData.getTotalDistance();
        if (totalDistance <= 0) {
            totalDistance = (int) hiTrackMetaData.getTotalTime();
            i = 0;
        } else {
            i = 1;
        }
        return new Pair<>(Integer.valueOf(i), Integer.valueOf(totalDistance));
    }

    public dcz d() {
        return this.f2307a.h;
    }

    public int j() {
        return this.f2307a.e;
    }

    public String e() {
        return this.f2307a.f2309a ? this.f2307a.h.n().b() : "";
    }

    public String c() {
        return this.f2307a.c;
    }

    public String b() {
        HealthDeviceEntryApi healthDeviceEntryApi = (HealthDeviceEntryApi) Services.c("PluginDevice", HealthDeviceEntryApi.class);
        if (healthDeviceEntryApi.isDeviceKitUniversal(this.f2307a.b)) {
            HealthDevice bondedDeviceUniversal = healthDeviceEntryApi.getBondedDeviceUniversal(this.f2307a.b, this.f2307a.f);
            if (bondedDeviceUniversal == null) {
                return "";
            }
            LogUtil.c("PDSPORT_SportDeviceDataMgr", "isBondedDevice: device = ", iyl.d().e(bondedDeviceUniversal.getAddress()));
            return bondedDeviceUniversal.getAddress();
        }
        com.huawei.health.device.model.HealthDevice bondedDeviceByUniqueId = healthDeviceEntryApi.getBondedDeviceByUniqueId(this.f2307a.f);
        if (bondedDeviceByUniqueId == null) {
            return "";
        }
        LogUtil.c("PDSPORT_SportDeviceDataMgr", "isBondedDevice: device = ", iyl.d().e(bondedDeviceByUniqueId.getAddress()));
        return bondedDeviceByUniqueId.getAddress();
    }

    static class b {

        /* renamed from: a, reason: collision with root package name */
        boolean f2309a;
        String b;
        String f;
        dcz h = null;
        int e = -1;
        String c = null;
        int d = -1;

        b(String str, String str2) {
            this.b = str;
            this.f = str2;
            this.f2309a = b(str);
        }

        private boolean b(String str) {
            dcz d = ResourceManager.e().d(str);
            this.h = d;
            if (d == null) {
                LogUtil.h("PDSPORT_SportDeviceDataMgr", "Unrecognized productId");
                return false;
            }
            int i = AnonymousClass4.f2308a[d.l().ordinal()];
            if (i == 1) {
                this.e = 264;
                this.c = "31";
                this.d = 30006;
            } else if (i == 2) {
                this.e = OldToNewMotionPath.SPORT_TYPE_INDOOR_BIKE;
                this.c = BleConstants.SPORT_TYPE_BIKE;
                this.d = 30002;
            } else if (i == 3) {
                this.e = OldToNewMotionPath.SPORT_TYPE_ROW_MACHINE;
                this.c = "261";
                this.d = 30002;
            } else if (i == 4) {
                this.e = OldToNewMotionPath.SPORT_TYPE_CROSS_TRAINER;
                this.c = "260";
                this.d = 30002;
            } else {
                if (i != 5) {
                    return false;
                }
                this.e = 281;
                this.c = BleConstants.SPORT_TYPE_TREADMILL;
                this.d = 30005;
            }
            return true;
        }
    }

    /* renamed from: com.huawei.health.ecologydevice.manager.SportDeviceDataManager$4, reason: invalid class name */
    static /* synthetic */ class AnonymousClass4 {

        /* renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f2308a;

        static {
            int[] iArr = new int[HealthDevice.HealthDeviceKind.values().length];
            f2308a = iArr;
            try {
                iArr[HealthDevice.HealthDeviceKind.HDK_TREADMILL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f2308a[HealthDevice.HealthDeviceKind.HDK_EXERCISE_BIKE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f2308a[HealthDevice.HealthDeviceKind.HDK_ROWING_MACHINE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f2308a[HealthDevice.HealthDeviceKind.HDK_ELLIPTICAL_MACHINE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f2308a[HealthDevice.HealthDeviceKind.HDK_WALKING_MACHINE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }
}
