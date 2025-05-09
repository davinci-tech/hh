package defpackage;

import com.huawei.health.device.model.FitnessData;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.indoorequip.datastruct.FitnessMachineFeature;
import com.huawei.indoorequip.datastruct.IndoorEquipTrainerData;
import com.huawei.indoorequip.datastruct.MachineControlPointResponse;
import com.huawei.indoorequip.datastruct.MachineStatus;
import com.huawei.indoorequip.datastruct.SupportDataRange;
import com.huawei.indoorequip.datastruct.TrainingStatus;
import java.util.Locale;

/* loaded from: classes5.dex */
public class lap extends lan {
    private FitnessMachineFeature e = new FitnessMachineFeature();

    /* renamed from: a, reason: collision with root package name */
    private IndoorEquipTrainerData f14727a = new IndoorEquipTrainerData();
    private TrainingStatus i = new TrainingStatus();
    private MachineStatus b = new MachineStatus();
    private SupportDataRange d = new SupportDataRange();
    private MachineControlPointResponse c = new MachineControlPointResponse();

    public IndoorEquipTrainerData a() {
        return this.f14727a;
    }

    public MachineStatus b() {
        return this.b;
    }

    public TrainingStatus d() {
        return this.i;
    }

    public FitnessMachineFeature e(byte[] bArr) {
        if (bArr != null) {
            this.e.clearData();
            int d = d(bArr, 20, 0);
            int d2 = d(bArr, 20, 4);
            LogUtil.a("Track_IDEQ_FtmsParser", "(FTMP_TS) ParseFitnessMachineFeatureCharacteristic: machineFeatureFlag:", Integer.valueOf(d), ", targetFeatureFlag:", Integer.valueOf(d2));
            this.e.setFitnessMachineFeature(d);
            this.e.setTargetSettingFeature(d2);
        }
        return this.e;
    }

    private IndoorEquipTrainerData o(byte[] bArr) {
        LogUtil.a("Track_IDEQ_FtmsParser", "(FTMP_TS) parseRowerDataCharacteristic");
        int d = d(bArr, 18, 0);
        this.f14727a.setFlags(d);
        LogUtil.a("Track_IDEQ_FtmsParser", "(FTMP_TS) RowerDataCharacteristic, intFlags:", Integer.valueOf(d));
        x(bArr, aa(bArr, q(bArr, k(bArr, p(bArr, f(bArr, ad(bArr, 2, d), d), d), d), d), d), d);
        return this.f14727a;
    }

    private int ad(byte[] bArr, int i, int i2) {
        if (bArr == null) {
            return i;
        }
        if ((i2 & 1) == 0) {
            int d = d(bArr, 17, i);
            this.f14727a.setStrokeRate(d);
            LogUtil.a("Track_IDEQ_FtmsParser", "(FTMP_TS) RowerDataCharacteristic, Stroke_Rate:", Integer.valueOf(d));
            int d2 = d(bArr, 18, i + 1);
            i += 3;
            this.f14727a.setStrokeCount(d2);
            LogUtil.a("Track_IDEQ_FtmsParser", "(FTMP_TS) RowerDataCharacteristic, Stroke_Count:", Integer.valueOf(d2));
        }
        if ((i2 & 2) == 0) {
            return i;
        }
        int d3 = d(bArr, 17, i);
        int i3 = i + 1;
        this.f14727a.setAverageStrokeRate(d3);
        LogUtil.a("Track_IDEQ_FtmsParser", "(FTMP_TS) RowerDataCharacteristic, Average_Stroke_Rate:", Integer.valueOf(d3));
        return i3;
    }

    private int f(byte[] bArr, int i, int i2) {
        if (bArr == null) {
            return i;
        }
        if ((i2 & 4) != 0) {
            int d = d(bArr, i);
            i += 3;
            this.f14727a.setTotalDistance(d);
            LogUtil.a("Track_IDEQ_FtmsParser", "(FTMP_TS) RowerDataCharacteristic, totalDistance:", Integer.valueOf(d));
        }
        if ((i2 & 8) != 0) {
            int d2 = d(bArr, 18, i);
            i += 2;
            this.f14727a.setInstantaneousPace(d2);
            LogUtil.a("Track_IDEQ_FtmsParser", "(FTMP_TS) RowerDataCharacteristic, Instantaneous_Pace:", Integer.valueOf(d2));
        }
        if ((i2 & 16) == 0) {
            return i;
        }
        int d3 = d(bArr, 18, i);
        int i3 = i + 2;
        this.f14727a.setAveragePace(d3);
        LogUtil.a("Track_IDEQ_FtmsParser", "(FTMP_TS) RowerDataCharacteristic, Average_Pace:", Integer.valueOf(d3));
        return i3;
    }

    private int p(byte[] bArr, int i, int i2) {
        if (bArr == null) {
            return i;
        }
        if ((i2 & 32) != 0) {
            int d = d(bArr, 18, i);
            i += 2;
            this.f14727a.setInstantaneousPower(d);
            LogUtil.a("Track_IDEQ_FtmsParser", "(FTMP_TS) RowerDataCharacteristic, Instantaneous_Power:", Integer.valueOf(d));
        }
        if ((i2 & 64) != 0) {
            int d2 = d(bArr, 18, i);
            i += 2;
            this.f14727a.setAveragePower(d2);
            LogUtil.a("Track_IDEQ_FtmsParser", "(FTMP_TS) RowerDataCharacteristic, Average_Power:", Integer.valueOf(d2));
        }
        if ((i2 & 128) == 0) {
            return i;
        }
        int d3 = d(bArr, 18, i);
        int i3 = i + 2;
        this.f14727a.setResistanceLevel(d3);
        LogUtil.a("Track_IDEQ_FtmsParser", "(FTMP_TS) RowerDataCharacteristic, Resistance_Level:", Integer.valueOf(d3));
        return i3;
    }

    private int k(byte[] bArr, int i, int i2) {
        if (bArr == null || (i2 & 256) == 0) {
            return i;
        }
        int d = d(bArr, 18, i);
        this.f14727a.setTotalEnergy(d);
        LogUtil.a("Track_IDEQ_FtmsParser", "(FTMP_TS) RowerDataCharacteristic, Total_Energy:", Integer.valueOf(d));
        int d2 = d(bArr, 18, i + 2);
        this.f14727a.setEnergyPerHour(d2);
        LogUtil.a("Track_IDEQ_FtmsParser", "(FTMP_TS) RowerDataCharacteristic, Energy_Per_Hour:", Integer.valueOf(d2));
        int d3 = d(bArr, 17, i + 4);
        int i3 = i + 5;
        this.f14727a.setEnergyPerMin(d3);
        LogUtil.a("Track_IDEQ_FtmsParser", "(FTMP_TS) RowerDataCharacteristic, Energy_Per_Minute:", Integer.valueOf(d3));
        return i3;
    }

    private int q(byte[] bArr, int i, int i2) {
        if (bArr == null) {
            return i;
        }
        if ((i2 & 512) != 0) {
            int d = d(bArr, 17, i);
            i++;
            this.f14727a.setHeartRate(d);
            LogUtil.a("Track_IDEQ_FtmsParser", "(FTMP_TS) RowerDataCharacteristic, Heart_Rate:", Integer.valueOf(d));
        }
        if ((i2 & 1024) == 0) {
            return i;
        }
        int d2 = d(bArr, 17, i);
        int i3 = i + 1;
        this.f14727a.setMetabolicEquivalent(d2);
        LogUtil.a("Track_IDEQ_FtmsParser", "(FTMP_TS) RowerDataCharacteristic, Metabolic_Equivalent:", Integer.valueOf(d2));
        return i3;
    }

    private int aa(byte[] bArr, int i, int i2) {
        if (bArr == null) {
            return i;
        }
        if ((i2 & 2048) != 0) {
            int d = d(bArr, 18, i);
            i += 2;
            this.f14727a.setElapsedTime(d);
            LogUtil.a("Track_IDEQ_FtmsParser", "(FTMP_TS) RowerDataCharacteristic, Elapsed_Time:", Integer.valueOf(d));
        }
        if ((i2 & 4096) == 0) {
            return i;
        }
        int d2 = d(bArr, 18, i);
        int i3 = i + 2;
        this.f14727a.setRemainingTime(d2);
        LogUtil.a("Track_IDEQ_FtmsParser", "(FTMP_TS) RowerDataCharacteristic, Remaining_Time:", Integer.valueOf(d2));
        return i3;
    }

    private int x(byte[] bArr, int i, int i2) {
        if (bArr == null || (i2 & 8192) == 0) {
            return i;
        }
        int d = d(bArr, 18, i);
        this.f14727a.setWeight(d);
        int d2 = d(bArr, 18, i + 2);
        this.f14727a.setStrokeCountForStrengthModel(d2);
        int d3 = d(bArr, 18, i + 4);
        this.f14727a.setTotalActionGroups(d3);
        int d4 = d(bArr, 18, i + 6);
        this.f14727a.setNumberOfActionsPerGroup(d4);
        int d5 = d(bArr, 18, i + 8);
        int i3 = i + 10;
        this.f14727a.setFrequencyOfAction(d5);
        LogUtil.a("Track_IDEQ_FtmsParser", "(FTMP_TS) RowerDataCharacteristic, weight:", Integer.valueOf(d), " strokeCountForStrength:", Integer.valueOf(d2), " totalActionGroups:", Integer.valueOf(d3), " numberOfActionsPerGroup:", Integer.valueOf(d4), " frequencyOfAction:", Integer.valueOf(d5));
        return i3;
    }

    private IndoorEquipTrainerData j(byte[] bArr) {
        LogUtil.a("Track_IDEQ_FtmsParser", "(FTMP_TS) parseCrossTrainerDataCharacteristic");
        int d = d(bArr, 0);
        this.f14727a.setFlags(d);
        LogUtil.a("Track_IDEQ_FtmsParser", "(FTMP_TS) CrossTrainerDataCharacteristic, intFlags:", Integer.valueOf(d));
        z(bArr, m(bArr, o(bArr, v(bArr, s(bArr, n(bArr, u(bArr, w(bArr, 3, d), d), d), d), d), d), d), d);
        return this.f14727a;
    }

    private int w(byte[] bArr, int i, int i2) {
        if (bArr == null) {
            return i;
        }
        if ((i2 & 1) == 0) {
            int d = d(bArr, 18, i);
            i += 2;
            this.f14727a.setInstantaneousSpeed(d);
            LogUtil.a("Track_IDEQ_FtmsParser", "(FTMP_TS) CrossTrainerDataCharacteristic, Instantaneous_Speed:", Integer.valueOf(d));
        }
        if ((i2 & 2) != 0) {
            int d2 = d(bArr, 18, i);
            i += 2;
            this.f14727a.setAverageSpeed(d2);
            LogUtil.a("Track_IDEQ_FtmsParser", "(FTMP_TS) CrossTrainerDataCharacteristic, Average_Speed:", Integer.valueOf(d2));
        }
        if ((i2 & 4) == 0) {
            return i;
        }
        int d3 = d(bArr, i);
        int i3 = i + 3;
        this.f14727a.setTotalDistance(d3);
        LogUtil.a("Track_IDEQ_FtmsParser", "(FTMP_TS) CrossTrainerDataCharacteristic, totalDistance:", Integer.valueOf(d3));
        return i3;
    }

    private int u(byte[] bArr, int i, int i2) {
        if (bArr == null) {
            return i;
        }
        if ((i2 & 8) != 0) {
            int d = d(bArr, 18, i);
            this.f14727a.setStepPerMinute(d);
            LogUtil.a("Track_IDEQ_FtmsParser", "(FTMP_TS) CrossTrainerDataCharacteristic, Step_Per_Minute:", Integer.valueOf(d));
            int d2 = d(bArr, 18, i + 2);
            i += 4;
            this.f14727a.setAverateStepRate(d2);
            LogUtil.a("Track_IDEQ_FtmsParser", "(FTMP_TS) CrossTrainerDataCharacteristic, Average_Step_Rate:", Integer.valueOf(d2));
        }
        if ((i2 & 16) == 0) {
            return i;
        }
        int d3 = d(bArr, 18, i);
        int i3 = i + 2;
        this.f14727a.setStrideCount(d3 * 2);
        LogUtil.a("Track_IDEQ_FtmsParser", "(FTMP_TS) CrossTrainerDataCharacteristic, STRIDE_COUNT:", Integer.valueOf(d3));
        return i3;
    }

    private int n(byte[] bArr, int i, int i2) {
        if (bArr == null || (i2 & 32) == 0) {
            return i;
        }
        int d = d(bArr, 18, i);
        this.f14727a.setPositiveElevationGain(d);
        LogUtil.a("Track_IDEQ_FtmsParser", "(FTMP_TS) CrossTrainerDataCharacteristic, Positive_Elevation_Gain:", Integer.valueOf(d));
        int d2 = d(bArr, 18, i + 2);
        int i3 = i + 4;
        this.f14727a.setNegativeElevationGain(d2);
        LogUtil.a("Track_IDEQ_FtmsParser", "(FTMP_TS) CrossTrainerDataCharacteristic, Negative_Elevation_Gain:", Integer.valueOf(d2));
        return i3;
    }

    private int s(byte[] bArr, int i, int i2) {
        if (bArr == null || (i2 & 64) == 0) {
            return i;
        }
        int d = d(bArr, 18, i);
        this.f14727a.setInclination(d);
        LogUtil.a("Track_IDEQ_FtmsParser", "(FTMP_TS) CrossTrainerDataCharacteristic, Inclination:", Integer.valueOf(d));
        int d2 = d(bArr, 18, i + 2);
        int i3 = i + 4;
        this.f14727a.setRampAngleSetting(d2);
        LogUtil.a("Track_IDEQ_FtmsParser", "(FTMP_TS) CrossTrainerDataCharacteristic, Ramp_Angle_Setting:", Integer.valueOf(d2));
        return i3;
    }

    private int v(byte[] bArr, int i, int i2) {
        if (bArr == null) {
            return i;
        }
        if ((i2 & 128) != 0) {
            int d = d(bArr, 18, i);
            i += 2;
            this.f14727a.setResistanceLevel(d);
            LogUtil.a("Track_IDEQ_FtmsParser", "(FTMP_TS) CrossTrainerDataCharacteristic, Resistance_Level:", Integer.valueOf(d));
        }
        if ((i2 & 256) != 0) {
            int d2 = d(bArr, 18, i);
            i += 2;
            this.f14727a.setInstantaneousPower(d2);
            LogUtil.a("Track_IDEQ_FtmsParser", "(FTMP_TS) CrossTrainerDataCharacteristic, Instantaneous_Power:", Integer.valueOf(d2));
        }
        if ((i2 & 512) == 0) {
            return i;
        }
        int d3 = d(bArr, 18, i);
        int i3 = i + 2;
        this.f14727a.setAveragePower(d3);
        LogUtil.a("Track_IDEQ_FtmsParser", "(FTMP_TS) CrossTrainerDataCharacteristic, Average_Power:", Integer.valueOf(d3));
        return i3;
    }

    private int o(byte[] bArr, int i, int i2) {
        if (bArr == null || (i2 & 1024) == 0) {
            return i;
        }
        int d = d(bArr, 18, i);
        this.f14727a.setTotalEnergy(d);
        LogUtil.a("Track_IDEQ_FtmsParser", "(FTMP_TS) CrossTrainerDataCharacteristic, Total_Energy:", Integer.valueOf(d));
        int d2 = d(bArr, 18, i + 2);
        this.f14727a.setEnergyPerHour(d2);
        LogUtil.a("Track_IDEQ_FtmsParser", "(FTMP_TS) CrossTrainerDataCharacteristic, Energy_Per_Hour:", Integer.valueOf(d2));
        int d3 = d(bArr, 17, i + 4);
        int i3 = i + 5;
        this.f14727a.setEnergyPerMin(d3);
        LogUtil.a("Track_IDEQ_FtmsParser", "(FTMP_TS) CrossTrainerDataCharacteristic, Energy_Per_Minute:", Integer.valueOf(d3));
        return i3;
    }

    private int m(byte[] bArr, int i, int i2) {
        if (bArr == null) {
            return i;
        }
        if ((i2 & 2048) != 0) {
            int d = d(bArr, 17, i);
            i++;
            this.f14727a.setHeartRate(d);
            LogUtil.a("Track_IDEQ_FtmsParser", "(FTMP_TS) CrossTrainerDataCharacteristic, Heart_Rate:", Integer.valueOf(d));
        }
        if ((i2 & 4096) == 0) {
            return i;
        }
        int d2 = d(bArr, 17, i);
        int i3 = i + 1;
        this.f14727a.setMetabolicEquivalent(d2);
        LogUtil.a("Track_IDEQ_FtmsParser", "(FTMP_TS) CrossTrainerDataCharacteristic, Metabolic_Equivalent:", Integer.valueOf(d2));
        return i3;
    }

    private int z(byte[] bArr, int i, int i2) {
        if (bArr != null) {
            if ((i2 & 8192) != 0) {
                int d = d(bArr, 18, i);
                i += 2;
                this.f14727a.setElapsedTime(d);
                LogUtil.a("Track_IDEQ_FtmsParser", "(FTMP_TS) CrossTrainerDataCharacteristic, Elapsed_Time:", Integer.valueOf(d));
            }
            if ((i2 & 16384) != 0) {
                int d2 = d(bArr, 18, i);
                i += 2;
                this.f14727a.setRemainingTime(d2);
                LogUtil.a("Track_IDEQ_FtmsParser", "(FTMP_TS) CrossTrainerDataCharacteristic, Remaining_Time:", Integer.valueOf(d2));
            }
            if ((32768 & i2) != 0) {
                this.f14727a.setMovementDirection(false);
                LogUtil.a("Track_IDEQ_FtmsParser", "(FTMP_TS) CrossTrainerDataCharacteristic, MovementDirection: Backward");
            } else {
                this.f14727a.setMovementDirection(true);
                LogUtil.a("Track_IDEQ_FtmsParser", "(FTMP_TS) CrossTrainerDataCharacteristic, MovementDirection: Forward");
            }
        }
        return i;
    }

    private IndoorEquipTrainerData n(byte[] bArr) {
        LogUtil.a("Track_IDEQ_FtmsParser", "(FTMP_TS) parseIndoorBikeDataCharacteristic");
        int d = d(bArr, 18, 0);
        this.f14727a.setFlags(d);
        LogUtil.a("Track_IDEQ_FtmsParser", "(FTMP_TS) IndoorBikeDataCharacteristic, intFlags:", Integer.valueOf(d));
        ac(bArr, r(bArr, l(bArr, t(bArr, h(bArr, y(bArr, 2, d), d), d), d), d), d);
        return this.f14727a;
    }

    private int y(byte[] bArr, int i, int i2) {
        if (bArr == null) {
            return i;
        }
        if ((i2 & 1) == 0) {
            int d = d(bArr, 18, i);
            i += 2;
            this.f14727a.setInstantaneousSpeed(d);
            LogUtil.a("Track_IDEQ_FtmsParser", "(FTMP_TS) IndoorBikeDataCharacteristic, Instantaneous_Speed:", Integer.valueOf(d));
        }
        if ((i2 & 2) != 0) {
            int d2 = d(bArr, 18, i);
            i += 2;
            this.f14727a.setAverageSpeed(d2);
            LogUtil.a("Track_IDEQ_FtmsParser", "(FTMP_TS) IndoorBikeDataCharacteristic, Average_Speed:", Integer.valueOf(d2));
        }
        if ((i2 & 4) != 0) {
            int d3 = d(bArr, 18, i);
            i += 2;
            this.f14727a.setInstantaneousCadence(d3);
            LogUtil.a("Track_IDEQ_FtmsParser", "(FTMP_TS) IndoorBikeDataCharacteristic, Instantaneous_Cadence:", Integer.valueOf(d3));
        }
        if ((i2 & 8) == 0) {
            return i;
        }
        int d4 = d(bArr, 18, i);
        int i3 = i + 2;
        this.f14727a.setAverageCadence(d4);
        LogUtil.a("Track_IDEQ_FtmsParser", "(FTMP_TS) IndoorBikeDataCharacteristic, Average_Cadence:", Integer.valueOf(d4));
        return i3;
    }

    private int h(byte[] bArr, int i, int i2) {
        if (bArr == null) {
            return i;
        }
        if ((i2 & 16) != 0) {
            int d = d(bArr, i);
            i += 3;
            this.f14727a.setTotalDistance(d);
            LogUtil.a("Track_IDEQ_FtmsParser", "(FTMP_TS) IndoorBikeDataCharacteristic, totalDistance:", Integer.valueOf(d));
        }
        if ((i2 & 32) == 0) {
            return i;
        }
        int d2 = d(bArr, 18, i);
        int i3 = i + 2;
        this.f14727a.setResistanceLevel(d2);
        LogUtil.a("Track_IDEQ_FtmsParser", "(FTMP_TS) IndoorBikeDataCharacteristic, Resistance_Level:", Integer.valueOf(d2));
        return i3;
    }

    private int t(byte[] bArr, int i, int i2) {
        if (bArr == null) {
            return i;
        }
        if ((i2 & 64) != 0) {
            int d = d(bArr, 18, i);
            i += 2;
            this.f14727a.setInstantaneousPower(d);
            LogUtil.a("Track_IDEQ_FtmsParser", "(FTMP_TS) IndoorBikeDataCharacteristic, Instantaneous_Power:", Integer.valueOf(d));
        }
        if ((i2 & 128) == 0) {
            return i;
        }
        int d2 = d(bArr, 18, i);
        int i3 = i + 2;
        this.f14727a.setAveragePower(d2);
        LogUtil.a("Track_IDEQ_FtmsParser", "(FTMP_TS) IndoorBikeDataCharacteristic, Average_Power:", Integer.valueOf(d2));
        return i3;
    }

    private int l(byte[] bArr, int i, int i2) {
        if (bArr == null || (i2 & 256) == 0) {
            return i;
        }
        int d = d(bArr, 18, i);
        this.f14727a.setTotalEnergy(d);
        LogUtil.a("Track_IDEQ_FtmsParser", "(FTMP_TS) IndoorBikeDataCharacteristic, Total_Energy:", Integer.valueOf(d));
        int d2 = d(bArr, 18, i + 2);
        this.f14727a.setEnergyPerHour(d2);
        LogUtil.a("Track_IDEQ_FtmsParser", "(FTMP_TS) IndoorBikeDataCharacteristic, Energy_Per_Hour:", Integer.valueOf(d2));
        int d3 = d(bArr, 17, i + 4);
        int i3 = i + 5;
        this.f14727a.setEnergyPerMin(d3);
        LogUtil.a("Track_IDEQ_FtmsParser", "(FTMP_TS) IndoorBikeDataCharacteristic, Energy_Per_Minute:", Integer.valueOf(d3));
        return i3;
    }

    private int r(byte[] bArr, int i, int i2) {
        if (bArr == null) {
            return i;
        }
        if ((i2 & 512) != 0) {
            int d = d(bArr, 17, i);
            i++;
            this.f14727a.setHeartRate(d);
            LogUtil.a("Track_IDEQ_FtmsParser", "(FTMP_TS) IndoorBikeDataCharacteristic, Heart_Rate:", Integer.valueOf(d));
        }
        if ((i2 & 1024) == 0) {
            return i;
        }
        int d2 = d(bArr, 17, i);
        int i3 = i + 1;
        this.f14727a.setMetabolicEquivalent(d2);
        LogUtil.a("Track_IDEQ_FtmsParser", "(FTMP_TS) IndoorBikeDataCharacteristic, Metabolic_Equivalent:", Integer.valueOf(d2));
        return i3;
    }

    private int ac(byte[] bArr, int i, int i2) {
        if (bArr == null) {
            return i;
        }
        if ((i2 & 2048) != 0) {
            int d = d(bArr, 18, i);
            i += 2;
            this.f14727a.setElapsedTime(d);
            LogUtil.a("Track_IDEQ_FtmsParser", "(FTMP_TS) IndoorBikeDataCharacteristic, Elapsed_Time:", Integer.valueOf(d));
        }
        if ((i2 & 4096) == 0) {
            return i;
        }
        int d2 = d(bArr, 18, i);
        int i3 = i + 2;
        this.f14727a.setRemainingTime(d2);
        LogUtil.a("Track_IDEQ_FtmsParser", "(FTMP_TS) IndoorBikeDataCharacteristic, Remaining_Time:", Integer.valueOf(d2));
        return i3;
    }

    private IndoorEquipTrainerData m(byte[] bArr) {
        LogUtil.a("Track_IDEQ_FtmsParser", "(FTMP_TS) ParseTreadmillDataCharacteristic");
        int d = d(bArr, 18, 0);
        LogUtil.a("Track_IDEQ_FtmsParser", "(FTMP_TS) ParseTreadmillDataCharacteristic, intFlags:", Integer.valueOf(d));
        this.f14727a.setFlags(d);
        int d2 = d(bArr, 18, 2);
        LogUtil.a("Track_IDEQ_FtmsParser", "(FTMP_TS)  ParseTreadmillDataCharacteristic, instantSpeed:", Integer.valueOf(d2));
        this.f14727a.setInstantaneousSpeed(d2);
        int e = e(bArr, g(bArr, b(bArr, 4, d), d), d);
        if ((d & 16) != 0) {
            int d3 = d(bArr, 18, e);
            LogUtil.a("Track_IDEQ_FtmsParser", "(FTMP_TS) ParseTreadmillDataCharacteristic, positiveGain:", Integer.valueOf(d3));
            this.f14727a.setPositiveElevationGain(d3);
            int d4 = d(bArr, 18, e + 2);
            LogUtil.a("Track_IDEQ_FtmsParser", "(FTMP_TS) ParseTreadmillDataCharacteristic, negativeGain:", Integer.valueOf(d4));
            e += 4;
            this.f14727a.setNegativeElevationGain(d4);
        }
        int c = c(bArr, e, d);
        if ((d & 64) != 0) {
            int d5 = d(bArr, 17, c);
            LogUtil.a("Track_IDEQ_FtmsParser", "(FTMP_TS) ParseTreadmillDataCharacteristic, averagePace:", Integer.valueOf(d5));
            c++;
            this.f14727a.setAveragePace(d5);
        }
        int i = i(bArr, c, d);
        if ((d & 256) != 0) {
            int d6 = d(bArr, 17, i);
            LogUtil.a("Track_IDEQ_FtmsParser", "(FTMP_TS) ParseTreadmillDataCharacteristic, heartRate:", Integer.valueOf(d6));
            i++;
            this.f14727a.setHeartRate(d6);
        }
        if ((d & 512) != 0) {
            int d7 = d(bArr, 17, i);
            LogUtil.a("Track_IDEQ_FtmsParser", "(FTMP_TS) ParseTreadmillDataCharacteristic, metabolic:", Integer.valueOf(d7));
            i++;
            this.f14727a.setMetabolicEquivalent(d7);
        }
        j(bArr, ab(bArr, a(bArr, i, d), d), d);
        return this.f14727a;
    }

    public IndoorEquipTrainerData b(byte[] bArr, String str) {
        if (bArr == null) {
            return null;
        }
        this.f14727a.clearData();
        if ("00002ACD-0000-1000-8000-00805f9b34fb".toUpperCase(Locale.ENGLISH).equals(str)) {
            this.f14727a.setFitnessDataType(3);
            return m(bArr);
        }
        if ("00002AD1-0000-1000-8000-00805f9b34fb".toUpperCase(Locale.ENGLISH).equals(str)) {
            this.f14727a.setFitnessDataType(11);
            return o(bArr);
        }
        if ("00002AD2-0000-1000-8000-00805f9b34fb".toUpperCase(Locale.ENGLISH).equals(str)) {
            this.f14727a.setFitnessDataType(10);
            return n(bArr);
        }
        if (!"00002ACE-0000-1000-8000-00805f9b34fb".toUpperCase(Locale.ENGLISH).equals(str)) {
            return null;
        }
        this.f14727a.setFitnessDataType(6);
        return j(bArr);
    }

    private int b(byte[] bArr, int i, int i2) {
        if (bArr == null || (i2 & 2) == 0) {
            return i;
        }
        int d = d(bArr, 18, i);
        LogUtil.a("Track_IDEQ_FtmsParser", "(FTMP_TS) ParseTreadmillDataCharacteristic, averageSpeed:", Integer.valueOf(d));
        int i3 = i + 2;
        this.f14727a.setAverageSpeed(d);
        return i3;
    }

    private int g(byte[] bArr, int i, int i2) {
        if (bArr == null || (i2 & 4) == 0) {
            return i;
        }
        int d = d(bArr, i);
        LogUtil.a("Track_IDEQ_FtmsParser", "(FTMP_TS) ParseTreadmillDataCharacteristic, totalDistance:", Integer.valueOf(d));
        int i3 = i + 3;
        this.f14727a.setTotalDistance(d);
        return i3;
    }

    private int ab(byte[] bArr, int i, int i2) {
        if (bArr == null || (i2 & 4096) == 0) {
            return i;
        }
        int d = d(bArr, 18, i);
        LogUtil.a("Track_IDEQ_FtmsParser", "(FTMP_TS) ParseTreadmillDataCharacteristic, forceOnBelt:", Integer.valueOf(d));
        int i3 = i + 2;
        this.f14727a.setForceOnBelt(d);
        int d2 = d(bArr, 18, i3);
        LogUtil.a("Track_IDEQ_FtmsParser", "(FTMP_TS) ParseTreadmillDataCharacteristic, powerOutput:", Integer.valueOf(d2));
        this.f14727a.setPowerOutPut(d2);
        return i3;
    }

    private void j(byte[] bArr, int i, int i2) {
        if (bArr == null || (i2 & 8192) == 0) {
            return;
        }
        int d = d(bArr, i);
        LogUtil.a("Track_IDEQ_FtmsParser", "zoro ParseTreadmillDataCharacteristic, totalStep:", Integer.valueOf(d));
        this.f14727a.setTotalStep(d);
    }

    private int a(byte[] bArr, int i, int i2) {
        if (bArr == null) {
            return i;
        }
        if ((i2 & 1024) != 0) {
            int d = d(bArr, 18, i);
            LogUtil.a("Track_IDEQ_FtmsParser", "(FTMP_TS) ParseTreadmillDataCharacteristic, elapsedTime:", Integer.valueOf(d));
            i += 2;
            this.f14727a.setElapsedTime(d);
        }
        if ((i2 & 2048) == 0) {
            return i;
        }
        int d2 = d(bArr, 18, i);
        LogUtil.a("Track_IDEQ_FtmsParser", "(FTMP_TS) ParseTreadmillDataCharacteristic, remainTime:", Integer.valueOf(d2));
        int i3 = i + 2;
        this.f14727a.setRemainingTime(d2);
        return i3;
    }

    private int i(byte[] bArr, int i, int i2) {
        if (bArr == null || (i2 & 128) == 0) {
            return i;
        }
        int d = d(bArr, 18, i);
        LogUtil.a("Track_IDEQ_FtmsParser", "(FTMP_TS) ParseTreadmillDataCharacteristic, totalEnergy:", Integer.valueOf(d));
        this.f14727a.setTotalEnergy(d);
        int d2 = d(bArr, 18, i + 2);
        LogUtil.a("Track_IDEQ_FtmsParser", "(FTMP_TS) ParseTreadmillDataCharacteristic, energyPerHour:", Integer.valueOf(d2));
        this.f14727a.setEnergyPerHour(d2);
        int d3 = d(bArr, 17, i + 4);
        LogUtil.a("Track_IDEQ_FtmsParser", "(FTMP_TS) ParseTreadmillDataCharacteristic, energyPerMin:", Integer.valueOf(d3));
        int i3 = i + 5;
        this.f14727a.setEnergyPerMin(d3);
        return i3;
    }

    private int c(byte[] bArr, int i, int i2) {
        if (bArr == null || (i2 & 32) == 0) {
            return i;
        }
        int d = d(bArr, 17, i);
        LogUtil.a("Track_IDEQ_FtmsParser", "(FTMP_TS) ParseTreadmillDataCharacteristic, instantPace:", Integer.valueOf(d));
        int i3 = i + 1;
        this.f14727a.setInstantaneousPace(d);
        return i3;
    }

    private int e(byte[] bArr, int i, int i2) {
        if (bArr == null || (i2 & 8) == 0) {
            return i;
        }
        int d = d(bArr, 18, i);
        LogUtil.a("Track_IDEQ_FtmsParser", "(FTMP_TS) ParseTreadmillDataCharacteristic, inclination:", Integer.valueOf(d));
        this.f14727a.setInclination(d);
        int d2 = d(bArr, 18, i + 2);
        LogUtil.a("Track_IDEQ_FtmsParser", "(FTMP_TS) ParseTreadmillDataCharacteristic, rampSetting:", Integer.valueOf(d2));
        int i3 = i + 4;
        this.f14727a.setRampAngleSetting(d2);
        return i3;
    }

    public TrainingStatus h(byte[] bArr) {
        if (bArr != null) {
            this.i.clearData();
            int d = d(bArr, 17, 0);
            int d2 = d(bArr, 17, 1);
            LogUtil.a("Track_IDEQ_FtmsParser", "(FTMP_TS) ParseTrainingStatusCharacteristic: flags:", Integer.valueOf(d), ", trainStatus:", Integer.valueOf(d2));
            this.i.setTrainingStatus(d2);
        }
        return this.i;
    }

    public MachineStatus b(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        this.b.clearData();
        int d = d(bArr, 17, 0);
        LogUtil.a("Track_IDEQ_FtmsParser", "(FTMP_TS) ParseMachineStatusCharacteristic: opCode:", Integer.valueOf(d));
        this.b.setOpCode(d);
        if (d == 2) {
            int d2 = d(bArr, 17, 1);
            LogUtil.a("Track_IDEQ_FtmsParser", "(FTMP_TS) ParseMachineStatusCharacteristic: controlInformation:", Integer.valueOf(d2));
            this.b.setMachineStatusCharacteristic(d2);
        } else if (d != 21) {
            switch (d) {
                case 5:
                    int d3 = d(bArr, 18, 1);
                    LogUtil.a("Track_IDEQ_FtmsParser", "(FTMP_TS) ParseMachineStatusCharacteristic: targetSpeed:", Integer.valueOf(d3));
                    this.b.setMachineStatusCharacteristic(d3);
                    break;
                case 6:
                    int d4 = d(bArr, 18, 1);
                    LogUtil.a("Track_IDEQ_FtmsParser", "(FTMP_TS) ParseMachineStatusCharacteristic: targetInclination:", Integer.valueOf(d4));
                    this.b.setMachineStatusCharacteristic(d4);
                    break;
                case 7:
                    int d5 = d(bArr, 17, 1);
                    LogUtil.a("Track_IDEQ_FtmsParser", "(FTMP_TS) ParseMachineStatusCharacteristic: targetResistance:", Integer.valueOf(d5));
                    this.b.setMachineStatusCharacteristic(d5);
                    break;
                case 8:
                    int d6 = d(bArr, 18, 1);
                    LogUtil.a("Track_IDEQ_FtmsParser", "(FTMP_TS) ParseMachineStatusCharacteristic: targetPower:", Integer.valueOf(d6));
                    this.b.setMachineStatusCharacteristic(d6);
                    break;
                case 9:
                    this.b.setMachineStatusCharacteristic(d(bArr, 17, 1));
                    break;
                case 10:
                    int d7 = d(bArr, 18, 1);
                    LogUtil.a("Track_IDEQ_FtmsParser", "(FTMP_TS) ParseMachineStatusCharacteristic: targetExpendedEnergy:", Integer.valueOf(d7));
                    this.b.setMachineStatusCharacteristic(d7);
                    break;
                case 11:
                    int d8 = d(bArr, 18, 1);
                    LogUtil.a("Track_IDEQ_FtmsParser", "(FTMP_TS) ParseMachineStatusCharacteristic: targetExpendedEnergy2:", Integer.valueOf(d8));
                    this.b.setMachineStatusCharacteristic(d8);
                    break;
                case 12:
                    int d9 = d(bArr, 18, 1);
                    LogUtil.a("Track_IDEQ_FtmsParser", "(FTMP_TS) ParseMachineStatusCharacteristic: targetStrides:", Integer.valueOf(d9));
                    this.b.setMachineStatusCharacteristic(d9);
                    break;
                case 13:
                    int d10 = d(bArr, 1);
                    LogUtil.a("Track_IDEQ_FtmsParser", "(FTMP_TS) ParseMachineStatusCharacteristic: targetDistance:", Integer.valueOf(d10));
                    this.b.setMachineStatusCharacteristic(d10);
                    break;
                case 14:
                    int d11 = d(bArr, 18, 1);
                    LogUtil.a("Track_IDEQ_FtmsParser", "(FTMP_TS) ParseMachineStatusCharacteristic: targetTrainingTime:", Integer.valueOf(d11));
                    this.b.setMachineStatusCharacteristic(d11);
                    break;
            }
        } else {
            int d12 = d(bArr, 18, 1);
            LogUtil.a("Track_IDEQ_FtmsParser", "(FTMP_TS) ParseMachineStatusCharacteristic: targetCadence:", Integer.valueOf(d12));
            this.b.setMachineStatusCharacteristic(d12);
        }
        return this.b;
    }

    public FitnessData a(byte[] bArr) {
        if (bArr == null) {
            return this.c;
        }
        this.c.clearData();
        if (bArr.length > 3) {
            this.c.setResultIntParameter(d(bArr, 18, 3));
        }
        this.c.setResponseOpCode(d(bArr, 17, 0));
        this.c.setRequestOpCode(d(bArr, 17, 1));
        this.c.setResultCode(d(bArr, 17, 2));
        return this.c;
    }

    public SupportDataRange i(byte[] bArr) {
        if (bArr != null) {
            this.d.clearData();
            int d = d(bArr, 18, 0);
            int d2 = d(bArr, 18, 2);
            int d3 = d(bArr, 18, 4);
            this.d.setMinSpeed(d);
            this.d.setMaxSpeed(d2);
            this.d.setMinIncrementSpeed(d3);
        }
        return this.d;
    }

    public SupportDataRange c(byte[] bArr) {
        if (bArr != null) {
            this.d.clearData();
            int d = d(bArr, 34, 0);
            int d2 = d(bArr, 34, 2);
            int d3 = d(bArr, 18, 4);
            this.d.setMinInclination(d);
            this.d.setMaxInclination(d2);
            this.d.setMinIncreInclination(d3);
        }
        return this.d;
    }

    public SupportDataRange f(byte[] bArr) {
        if (bArr != null) {
            this.d.clearData();
            int d = d(bArr, 34, 0);
            int d2 = d(bArr, 34, 2);
            int d3 = d(bArr, 18, 4);
            this.d.setMinLevel(d);
            this.d.setMaxLevel(d2);
            this.d.setMinIncrementLevel(d3);
        }
        return this.d;
    }

    public SupportDataRange d(byte[] bArr) {
        if (bArr != null) {
            this.d.clearData();
            int d = d(bArr, 17, 0);
            int d2 = d(bArr, 17, 1);
            int d3 = d(bArr, 17, 2);
            this.d.setMinHeartRate(d);
            this.d.setMaxHeartRate(d2);
            this.d.setMinIncrementHeartRate(d3);
        }
        return this.d;
    }

    public SupportDataRange g(byte[] bArr) {
        if (bArr != null) {
            this.d.clearData();
            int d = d(bArr, 34, 0);
            int d2 = d(bArr, 34, 2);
            int d3 = d(bArr, 18, 4);
            this.d.setMinPower(d);
            this.d.setMaxPower(d2);
            this.d.setMinIncrementPower(d3);
        }
        return this.d;
    }

    public SupportDataRange c() {
        return this.d;
    }
}
