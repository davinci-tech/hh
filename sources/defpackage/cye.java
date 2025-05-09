package defpackage;

import com.huawei.health.ecologydevice.fitness.dataparser.secondropecommand.CommandArrayList;
import com.huawei.health.ecologydevice.fitness.datastruct.BaseRopeData;
import com.huawei.health.ecologydevice.fitness.datastruct.BatteryStatus;
import com.huawei.health.ecologydevice.fitness.datastruct.DeviceInformation;
import com.huawei.health.ecologydevice.fitness.datastruct.ExclusivePlayListData;
import com.huawei.health.ecologydevice.fitness.datastruct.HistoryData;
import com.huawei.health.ecologydevice.fitness.datastruct.LogOperationsData;
import com.huawei.health.ecologydevice.fitness.datastruct.RopeModeSettingData;
import com.huawei.health.ecologydevice.fitness.datastruct.RopeRealData;
import com.huawei.health.ecologydevice.fitness.datastruct.RopeTrainingStatusData;
import com.huawei.health.ecologydevice.fitness.datastruct.RopeVoiceCourseOperationsData;
import com.huawei.health.ecologydevice.fitness.datastruct.SettingStatusData;
import com.huawei.health.ecologydevice.fitness.datastruct.SwitchStatusData;
import com.huawei.health.ecologydevice.fitness.datastruct.VideoCourseOperationsData;
import health.compact.a.util.LogUtil;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes3.dex */
public class cye {
    private Map<Integer, BaseRopeData> d = new HashMap();
    private long e = System.currentTimeMillis();
    private BatteryStatus b = new BatteryStatus();
    private ConcurrentHashMap<Integer, cyq> i = new ConcurrentHashMap<>();
    private ConcurrentHashMap<Integer, cyp> c = new ConcurrentHashMap<>();

    /* renamed from: a, reason: collision with root package name */
    private DeviceInformation f11540a = new DeviceInformation();
    private RopeTrainingStatusData f = new RopeTrainingStatusData();

    public cye() {
        e();
    }

    private void e() {
        this.d.put(100, new SwitchStatusData());
        this.d.put(200, new SettingStatusData());
        this.d.put(300, new RopeModeSettingData());
        this.d.put(400, new RopeVoiceCourseOperationsData());
        this.d.put(500, new VideoCourseOperationsData());
        this.d.put(600, new LogOperationsData());
        this.d.put(700, new ExclusivePlayListData());
    }

    public BaseRopeData b(int i) {
        return this.d.get(Integer.valueOf(i * 100));
    }

    public BaseRopeData a(byte[] bArr) {
        int e = cyw.e(bArr, 17, 0);
        if (e > 255 || e < 0) {
            LogUtil.d("PDROPE_RopeDataParser", "parseRopeDataCharacteristic, invalid command!");
            return null;
        }
        switch (e) {
            case 2:
                return f(bArr, 1);
            case 3:
            default:
                return j(bArr, e);
            case 4:
                return i(bArr, 1);
            case 5:
                return h(bArr, 1);
            case 6:
                return b(bArr, 1);
            case 7:
                return d(bArr, 1);
            case 8:
                return c(bArr, 1);
            case 9:
                return a(bArr, 1);
            case 10:
                return e(bArr, 1);
            case 11:
                return g(bArr, 1);
        }
    }

    public RopeRealData i(byte[] bArr, int i) {
        if (bArr == null || bArr.length == 0 || bArr.length > 32) {
            LogUtil.d("PDROPE_RopeDataParser", "RopeRealData is error");
        }
        long currentTimeMillis = System.currentTimeMillis() - this.e;
        this.e = System.currentTimeMillis();
        if (bArr.length == 20) {
            int e = cyw.e(bArr, 17, 19);
            cyq cyqVar = this.i.get(Integer.valueOf(e));
            if (cyqVar == null) {
                cyqVar = new cyq();
                this.i.put(Integer.valueOf(e), cyqVar);
            }
            if (cyqVar.e(0, cyw.b(bArr, 0, bArr.length - 1))) {
                return c(e, cyqVar, currentTimeMillis);
            }
            return null;
        }
        LogUtil.d("PDROPE_RopeDataParser", "old parseRopeRealDataCharacteristic: data");
        RopeRealData d = new cyq().d(bArr, i);
        if (d == null) {
            return null;
        }
        if (currentTimeMillis > 50) {
            d.setUpdateTime(currentTimeMillis);
        }
        return d;
    }

    private RopeRealData c(int i, cyq cyqVar, long j) {
        RopeRealData e = cyqVar.e();
        if (e != null) {
            if (j > 50 && j != 0) {
                e.setUpdateTime(j);
            } else {
                long currentTimeMillis = System.currentTimeMillis();
                long j2 = this.e;
                this.e = System.currentTimeMillis();
                e.setUpdateTime(currentTimeMillis - j2);
            }
        }
        this.i.remove(Integer.valueOf(i));
        return e;
    }

    public BaseRopeData b(CommandArrayList commandArrayList) {
        BaseRopeData b = b(commandArrayList.c());
        if (b == null) {
            return b;
        }
        b.setCode(commandArrayList.d());
        b.setData(commandArrayList.f());
        b.setFlag(commandArrayList.e());
        b.setDataLength(commandArrayList.a());
        b.setOffset(0);
        return b.parseData();
    }

    public BatteryStatus f(byte[] bArr, int i) {
        this.b.setButteryStatus(cyw.e(bArr, 17, i));
        return this.b;
    }

    public HistoryData h(byte[] bArr, int i) {
        if (bArr != null && bArr.length != 0 && bArr.length <= 20) {
            if (bArr.length == 20) {
                int e = cyw.e(bArr, 17, 19);
                cyp cypVar = this.c.get(Integer.valueOf(e));
                if (cypVar == null) {
                    cypVar = new cyp();
                    this.c.put(Integer.valueOf(e), cypVar);
                }
                if (cypVar.e(0, cyw.b(bArr, 0, bArr.length - 1))) {
                    return d(e, cypVar);
                }
                return null;
            }
            if (bArr == null || bArr.length % 14 != 2) {
                LogUtil.d("PDROPE_RopeDataParser", "parseRopeHistoryDataCharacteristic data is invalid");
            } else {
                int e2 = cyw.e(bArr, 17, i) & 15;
                if (e2 == 0) {
                    return null;
                }
                if ((bArr.length - 2) / 14 != e2) {
                    LogUtil.d("PDROPE_RopeDataParser", "parseRopeHistoryDataCharacteristic data invalid");
                    return null;
                }
                return new cyp().d(bArr, i);
            }
        }
        return null;
    }

    private HistoryData d(int i, cyp cypVar) {
        HistoryData e = cypVar.e();
        this.c.remove(Integer.valueOf(i));
        return e;
    }

    private BaseRopeData j(byte[] bArr, int i) {
        int i2 = i & 63;
        boolean z = (i & 128) != 0;
        int e = cyw.e(bArr, 17, 1);
        int e2 = cyw.e(bArr, 17, 2);
        if (e2 == 4) {
            cyq cyqVar = this.i.get(Integer.valueOf(i2));
            if (cyqVar == null) {
                cyqVar = new cyq();
                this.i.put(Integer.valueOf(i2), cyqVar);
            }
            if (!z) {
                cyqVar.d(true);
                cyqVar.a(e + 1);
            }
            if (cyqVar.e(e, cyw.b(bArr, 3, bArr.length - 3))) {
                return c(i2, cyqVar, 0L);
            }
            return null;
        }
        if (e2 == 5) {
            cyp cypVar = this.c.get(Integer.valueOf(i2));
            if (cypVar == null) {
                cypVar = new cyp();
                this.c.put(Integer.valueOf(i2), cypVar);
            }
            if (!z) {
                cypVar.d(true);
                cypVar.a(e + 1);
            }
            if (cypVar.e(e, cyw.b(bArr, 3, bArr.length - 3))) {
                return d(i2, cypVar);
            }
            return null;
        }
        LogUtil.d("PDROPE_RopeDataParser", "parseRopeDataCharacteristic, Unrecognized Rope Data!");
        return null;
    }

    public DeviceInformation b(byte[] bArr, int i) {
        String str = new String(bArr, i, bArr.length - i, StandardCharsets.UTF_8);
        LogUtil.d("PDROPE_RopeDataParser", "ParseDeviceInformationManufacturer: manufacturerString:", str);
        this.f11540a.setManufacturerString(str);
        LogUtil.d("PDROPE_RopeDataParser", "SPORT_TYPE_ROPE_SKIPPING:", 283);
        this.f11540a.setDeviceType(283);
        return this.f11540a;
    }

    public DeviceInformation d(byte[] bArr, int i) {
        String str = new String(bArr, i, bArr.length - i, StandardCharsets.UTF_8);
        LogUtil.d("PDROPE_RopeDataParser", "ParseDeviceInformationModel: modelNumber:", str);
        this.f11540a.setModelString(str);
        return this.f11540a;
    }

    public DeviceInformation c(byte[] bArr, int i) {
        String str = new String(bArr, i, bArr.length - i, StandardCharsets.UTF_8);
        LogUtil.d("PDROPE_RopeDataParser", "ParseDeviceInformationModel: SN:", knl.d(str));
        this.f11540a.setSerialNumber(str);
        return this.f11540a;
    }

    public DeviceInformation e(byte[] bArr, int i) {
        String str = new String(bArr, i, bArr.length - i, StandardCharsets.UTF_8);
        LogUtil.d("PDROPE_RopeDataParser", "ParseDeviceInformationModel: HardwareVersion:", str);
        this.f11540a.setHardwareVersion(str);
        return this.f11540a;
    }

    public DeviceInformation a(byte[] bArr, int i) {
        String str = new String(bArr, i, bArr.length - i, StandardCharsets.UTF_8);
        LogUtil.d("PDROPE_RopeDataParser", "ParseDeviceInformationModel: SoftwareVersion:", str);
        this.f11540a.setSoftwareVersion(str);
        return this.f11540a;
    }

    public RopeTrainingStatusData g(byte[] bArr, int i) {
        int e = cyw.e(bArr, 17, i);
        this.f.setDeviceTrainingStatus(e);
        LogUtil.d("PDROPE_RopeDataParser", "RopeDataCharacteristic RopeTrainingStatusData status:", Integer.valueOf(e));
        return this.f;
    }
}
