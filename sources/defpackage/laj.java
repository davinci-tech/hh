package defpackage;

import android.os.SystemClock;
import com.huawei.health.device.model.DeviceInformation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.indoorequip.datastruct.IndoorEquipTrainerData;
import com.huawei.indoorequip.datastruct.MachineStatus;
import com.huawei.indoorequip.datastruct.SupportDataRange;
import com.huawei.indoorequip.datastruct.TrainingStatus;
import java.util.Iterator;
import java.util.Locale;

/* loaded from: classes5.dex */
public class laj {
    private int ae;
    private int ah;
    private MachineStatus ai;
    private SupportDataRange ay;
    private int bd;
    private IndoorEquipTrainerData bf;
    private TrainingStatus bg;
    private int bm;
    private DeviceInformation e;
    private boolean r;
    private int aw = 0;
    private int ax = 0;
    private int an = 0;
    private int ar = 0;
    private int o = 0;
    private int n = 0;

    /* renamed from: a, reason: collision with root package name */
    private int f14723a = 0;
    private int be = 0;
    private int g = 0;
    private int bh = 0;
    private int h = 0;
    private int m = 0;
    private int aj = 0;
    private int al = 0;
    private int ak = 0;
    private int am = 0;
    private int af = 0;
    private int ag = 0;
    private int w = 0;
    private int x = 0;
    private int ab = 0;
    private int aa = 0;
    private int z = 0;
    private int ac = 0;
    private int u = 0;
    private int v = 0;
    private int s = 0;
    private int bc = 0;
    private int ba = 0;
    private int az = 0;
    private int au = 0;
    private int b = 0;
    private int j = 0;
    private int av = 0;
    private int at = 0;
    private int as = 0;
    private int i = 0;
    private int y = 0;
    private int ad = 0;
    private long ap = 0;
    private long d = 0;
    private long ao = 0;
    private long c = 0;
    private boolean p = true;
    private boolean q = false;
    private boolean t = false;
    private boolean k = false;
    private boolean l = false;
    private boolean f = false;
    private long bb = 0;
    private long aq = 2500;

    public laj(IndoorEquipTrainerData indoorEquipTrainerData, DeviceInformation deviceInformation, lap lapVar, boolean z) {
        this.bf = indoorEquipTrainerData;
        this.e = deviceInformation;
        if (lapVar != null) {
            this.ai = lapVar.b();
            this.bg = lapVar.d();
            this.ay = lapVar.c();
        }
        this.r = z;
    }

    public void c() {
        IndoorEquipTrainerData indoorEquipTrainerData = this.bf;
        if (indoorEquipTrainerData == null) {
            return;
        }
        int fitnessDataType = indoorEquipTrainerData.getFitnessDataType();
        if (fitnessDataType == 3 || fitnessDataType == 99) {
            y();
            u();
            l();
            ab();
            return;
        }
        LogUtil.a("Track_IDEQ_DataCleaner", "clearData for indoor equipment");
        x();
        v();
        o();
        m();
        p();
        ac();
        z();
    }

    private void v() {
        if (this.ay.getFitnessHashMap().containsKey(30006)) {
            int minLevel = this.ay.getMinLevel();
            this.bm = minLevel;
            LogUtil.a("Track_IDEQ_DataCleaner", "min level=", Integer.valueOf(minLevel));
            int i = this.bm;
            if (i < 0) {
                i = 0;
            }
            this.bm = i;
        }
        if (this.ay.getFitnessHashMap().containsKey(30007)) {
            int maxLevel = this.ay.getMaxLevel();
            this.bd = maxLevel;
            LogUtil.a("Track_IDEQ_DataCleaner", "max level=", Integer.valueOf(maxLevel));
            int i2 = this.bd;
            this.bd = i2 >= 0 ? i2 : 0;
        }
    }

    private void o() {
        f();
        j();
        h();
        g();
        i();
    }

    private void i() {
        if (this.bf.getFitnessHashMap().containsKey(14)) {
            e(14, this.bf.getInstantaneousPace(), 60);
            this.bf.setInstantaneousPace(this.z);
        }
        if (this.bf.getFitnessHashMap().containsKey(15)) {
            e(15, this.bf.getAveragePace(), 60);
            this.bf.setAveragePace(this.ac);
        }
    }

    private void e(int i, int i2, int i3) {
        if (i2 != 0 && i2 < i3) {
            LogUtil.h("Track_IDEQ_DataCleaner", "pace not in valid scope and paceValue =", Integer.valueOf(i2));
            return;
        }
        if (i == 14) {
            this.z = i2;
        } else if (i == 15) {
            this.ac = i2;
        } else {
            LogUtil.h("Track_IDEQ_DataCleaner", "do not have this dataType", Integer.valueOf(i));
        }
    }

    private void g() {
        if (this.bf.getFitnessHashMap().containsKey(26)) {
            a(26, this.bf.getStrokeRate(), 200);
            this.bf.setStrokeRate(this.ab);
        }
        if (this.bf.getFitnessHashMap().containsKey(28)) {
            a(28, this.bf.getAverageStrokeRate(), 200);
            this.bf.setAverageStrokeRate(this.aa);
        }
        if (this.bf.getFitnessHashMap().containsKey(33)) {
            a(33, this.bf.getAverateStepRate(), 360);
            this.bf.setAverateStepRate(this.u);
        }
        if (this.bf.getFitnessHashMap().containsKey(31)) {
            a();
        }
        if (this.bf.getFitnessHashMap().containsKey(32)) {
            a(32, this.bf.getAverageCadence(), 720);
            this.bf.setAverageCadence(this.s);
        }
    }

    private void a() {
        if (this.bf.getFitnessDataType() == 10) {
            int instantaneousCadence = this.bf.getInstantaneousCadence();
            if (instantaneousCadence >= 0 && instantaneousCadence <= 720) {
                this.v = instantaneousCadence;
            } else {
                LogUtil.h("Track_IDEQ_DataCleaner", "cadence not in valid scope and cadenceValue =", Integer.valueOf(instantaneousCadence));
            }
            this.bf.setInstantaneousCadence(this.v);
            return;
        }
        if (this.bf.getFitnessDataType() == 6) {
            int stepPerMinute = this.bf.getStepPerMinute();
            this.aw = stepPerMinute;
            if (stepPerMinute >= 0 && stepPerMinute <= 360) {
                this.ax = stepPerMinute;
            } else {
                LogUtil.h("Track_IDEQ_DataCleaner", "cadence not in valid scope and cadenceValue =", Integer.valueOf(stepPerMinute));
            }
            this.bf.setStepPerMinute(this.ax);
            return;
        }
        LogUtil.h("Track_IDEQ_DataCleaner", "do not support this data");
    }

    private void a(int i, int i2, int i3) {
        if (i2 < 0 || i2 > i3) {
            LogUtil.h("Track_IDEQ_DataCleaner", "value not in valid scope", "dataType =", Integer.valueOf(i), "value =", Integer.valueOf(i2));
            return;
        }
        if (i == 3) {
            this.n = i2;
            return;
        }
        if (i == 9) {
            this.w = i2;
            return;
        }
        if (i == 26) {
            this.ab = i2;
            return;
        }
        if (i == 28) {
            this.aa = i2;
            return;
        }
        if (i == 32) {
            this.s = i2;
        } else if (i == 33) {
            this.u = i2;
        } else {
            LogUtil.h("Track_IDEQ_DataCleaner", "do not have this dataType", Integer.valueOf(i));
        }
    }

    private void h() {
        if (this.bf.getFitnessHashMap().containsKey(22)) {
            a(this.bf.getResistanceLevel());
        }
    }

    private void a(int i) {
        if (this.bf.getFitnessDataType() == 10) {
            int i2 = i * 10;
            if (i2 >= this.bm && i2 <= this.bd) {
                this.bf.setResistanceLevel(i);
                return;
            } else {
                LogUtil.h("Track_IDEQ_DataCleaner", "indoor bike resistanceLevel not in valid scope resistanceLevel =", Integer.valueOf(i));
                this.bf.setResistanceLevel(-1);
                return;
            }
        }
        if (this.bf.getFitnessDataType() == 6) {
            if (i >= this.bm && i <= this.bd) {
                this.bf.setResistanceLevel(i);
                return;
            } else {
                LogUtil.h("Track_IDEQ_DataCleaner", "cross trainer resistanceLevel not in valid scope resistanceLevel =", Integer.valueOf(i));
                this.bf.setResistanceLevel(-1);
                return;
            }
        }
        LogUtil.c("Track_IDEQ_DataCleaner", "this machine not have resistance level");
    }

    private void j() {
        if (this.bf.getFitnessHashMap().containsKey(24)) {
            int instantaneousPower = this.bf.getInstantaneousPower();
            this.h = instantaneousPower;
            c(24, instantaneousPower);
            this.bf.setInstantaneousPower(this.m);
        }
        if (this.bf.getFitnessHashMap().containsKey(25)) {
            c(25, this.bf.getAveragePower());
            this.bf.setAveragePower(this.x);
        }
    }

    private void c(int i, int i2) {
        if (this.bf.getFitnessDataType() == 11) {
            d(i, i2, 2000);
        } else if (this.bf.getFitnessDataType() == 10 || this.bf.getFitnessDataType() == 6) {
            d(i, i2, 1000);
        } else {
            LogUtil.h("Track_IDEQ_DataCleaner", "not support this machine");
        }
    }

    private void d(int i, int i2, int i3) {
        if (i2 < 0 || i2 > i3) {
            LogUtil.h("Track_IDEQ_DataCleaner", "powerValue not in valid scope", "dataType =", Integer.valueOf(i), "powerValue =", Integer.valueOf(i2));
            return;
        }
        if (i == 24) {
            this.m = i2;
        } else if (i == 25) {
            this.x = i2;
        } else {
            LogUtil.h("Track_IDEQ_DataCleaner", "not have this dataType");
        }
    }

    private void f() {
        if (this.bf.getFitnessHashMap().containsKey(3)) {
            int instantaneousSpeed = this.bf.getInstantaneousSpeed();
            this.o = instantaneousSpeed;
            a(3, instantaneousSpeed, 10000);
            this.bf.setInstantaneousSpeed(this.n);
        }
        if (this.bf.getFitnessHashMap().containsKey(9)) {
            a(9, this.bf.getAverageSpeed(), 10000);
            this.bf.setAverageSpeed(this.w);
        }
    }

    private void z() {
        LogUtil.c("Track_IDEQ_DataCleaner", "updateDataForOpcode mSportState:", Integer.valueOf(this.as));
        int i = this.as;
        if (i != 0) {
            if (i == 1) {
                this.bf.setTrainingStatus(13);
                this.bf.setOpCode(4);
                MachineStatus machineStatus = this.ai;
                if (machineStatus != null) {
                    machineStatus.setOpCode(4);
                }
                TrainingStatus trainingStatus = this.bg;
                if (trainingStatus != null) {
                    trainingStatus.setTrainingStatus(13);
                    return;
                }
                return;
            }
            if (i == 2) {
                this.bf.setTrainingStatus(13);
                this.bf.setOpCode(2);
                this.bf.setMachineStatusCharacteristic(2);
                MachineStatus machineStatus2 = this.ai;
                if (machineStatus2 != null) {
                    machineStatus2.setOpCode(2);
                    this.ai.setMachineStatusCharacteristic(2);
                }
                TrainingStatus trainingStatus2 = this.bg;
                if (trainingStatus2 != null) {
                    trainingStatus2.setTrainingStatus(13);
                    return;
                }
                return;
            }
            if (i != 3) {
                return;
            }
        }
        this.bf.setTrainingStatus(1);
        this.bf.setOpCode(2);
        this.bf.setMachineStatusCharacteristic(1);
        MachineStatus machineStatus3 = this.ai;
        if (machineStatus3 != null) {
            machineStatus3.setOpCode(2);
            this.ai.setMachineStatusCharacteristic(1);
        }
        TrainingStatus trainingStatus3 = this.bg;
        if (trainingStatus3 != null) {
            trainingStatus3.setTrainingStatus(1);
        }
    }

    private void ab() {
        int i = this.as;
        if (i == 3 || i == 0) {
            return;
        }
        z();
        this.bf.setInstantaneousSpeed(this.n);
        this.bf.setStepPerMinute(this.ax);
        this.bf.setRevsPerMinute(this.ar);
        this.bf.setTotalEnergy(this.bh);
        this.bf.setInstantaneousPower(this.m);
        this.bf.setPowerInCalPerMin(this.al);
        this.bf.setPositiveElevationGain(this.am);
        this.bf.setNegativeElevationGain(this.ag);
        this.bf.setElapsedTime(this.f14723a);
    }

    private void ac() {
        int i = this.as;
        if (i == 3 || i == 0) {
            return;
        }
        if (this.bf.getFitnessHashMap().containsKey(1)) {
            this.bf.setTotalDistance(this.ba);
        }
        if (this.bf.getFitnessHashMap().containsKey(6)) {
            this.bf.setTotalEnergy(this.bh);
        }
        if (this.bf.getFitnessHashMap().containsKey(2)) {
            this.bf.setElapsedTime(this.j);
        }
        if (this.bf.getFitnessHashMap().containsKey(27)) {
            this.bf.setStrokeCount(this.au);
        }
        if (this.bf.getFitnessHashMap().containsKey(23)) {
            this.bf.setStrideCount(this.at);
        }
    }

    private void u() {
        MachineStatus machineStatus = this.ai;
        if (machineStatus == null) {
            return;
        }
        Iterator<Integer> it = machineStatus.getFitnessHashMap().keySet().iterator();
        while (it.hasNext()) {
            int intValue = it.next().intValue();
            if (intValue == 20008) {
                this.ah = this.ai.getOpCode();
            } else if (intValue == 20009) {
                this.ae = this.ai.getMachineStatusCharacteristic();
            }
        }
    }

    private void y() {
        Iterator<Integer> it = this.bf.getFitnessHashMap().keySet().iterator();
        while (it.hasNext()) {
            int intValue = it.next().intValue();
            if (intValue == 2) {
                this.f14723a = this.bf.getElapsedTime();
            } else if (intValue == 3) {
                this.o = this.bf.getInstantaneousSpeed();
            } else if (intValue == 4) {
                this.aw = this.bf.getStepPerMinute();
            } else if (intValue == 5) {
                this.an = this.bf.getRevsPerMinute();
            } else if (intValue == 6) {
                this.be = this.bf.getTotalEnergy();
            } else if (intValue == 12) {
                this.ak = this.bf.getPositiveElevationGain();
            } else if (intValue == 13) {
                this.af = this.bf.getNegativeElevationGain();
            } else if (intValue == 24) {
                this.h = this.bf.getInstantaneousPower();
            } else if (intValue == 25) {
                this.aj = this.bf.getPowerInCalPerMin();
            }
        }
    }

    private void x() {
        if (this.bf.getFitnessHashMap().containsKey(1)) {
            this.bc = this.bf.getTotalDistance();
        }
        if (this.bf.getFitnessHashMap().containsKey(6)) {
            this.be = this.bf.getTotalEnergy();
        }
        if (this.bf.getFitnessHashMap().containsKey(2)) {
            this.b = this.bf.getElapsedTime();
        }
        if (this.bf.getFitnessHashMap().containsKey(27)) {
            this.az = this.bf.getStrokeCount();
        }
        if (this.bf.getFitnessHashMap().containsKey(23)) {
            this.av = this.bf.getStrideCount();
        }
    }

    private void m() {
        int i = this.bc;
        if (i > 0 && i >= this.ba) {
            this.ba = i;
        }
        int i2 = this.be;
        if (i2 > 0 && i2 >= this.bh) {
            this.bh = i2;
        }
        int i3 = this.b;
        if (i3 > 0 && i3 >= this.j) {
            this.j = i3;
        }
        int i4 = this.az;
        if (i4 > 0 && i4 >= this.au) {
            this.au = i4;
        }
        int i5 = this.av;
        if (i5 <= 0 || i5 < this.at) {
            return;
        }
        this.at = i5;
    }

    private void p() {
        int i = this.j;
        if (i > 0 || this.q) {
            if (this.p) {
                this.p = false;
                LogUtil.a("Track_IDEQ_DataCleaner", "detectPauseState(otherEquip), now will get first time value:", Integer.valueOf(i));
                if (this.j > 2) {
                    LogUtil.a("Track_IDEQ_DataCleaner", "detectPauseState(otherEquip), got first time value and it is larger than 2");
                    this.i = this.j;
                    this.q = true;
                    this.j = 0;
                    return;
                }
                LogUtil.a("Track_IDEQ_DataCleaner", "detectPauseState(otherEquip), got first time value and it is NOT larger than 2");
            }
            if (this.i > 0) {
                LogUtil.a("Track_IDEQ_DataCleaner", "detectPauseState(otherEquip),", "now will handle the situation(got first time value and it is larger than 2)");
                int i2 = this.j;
                int i3 = this.i;
                if (i2 == i3) {
                    LogUtil.a("Track_IDEQ_DataCleaner", "mElapsedTimeValid1(otherEquip) (", Integer.valueOf(i2), ") is equals to mFirstTimeValueWhichIsLargerThan2");
                    this.j = 0;
                    return;
                } else {
                    if (i2 < i3) {
                        this.q = false;
                        LogUtil.a("Track_IDEQ_DataCleaner", "mElapsedTimeValid2(otherEquip) (", Integer.valueOf(i2), ") is smaller than mFirstTimeValueWhichIsLargerThan2");
                        this.i = 0;
                        this.j = 0;
                        return;
                    }
                    LogUtil.a("Track_IDEQ_DataCleaner", "mElapsedTimeValid3(otherEquip) (", Integer.valueOf(i2), ") is larger than mFirstTimeValueWhichIsLargerThan2");
                    this.i = 0;
                }
            }
            this.q = true;
            s();
        }
    }

    private void s() {
        u();
        k();
        d();
        int i = this.ah;
        boolean z = (i == 2 && this.ae == 1) || i == 3;
        if (this.as == 3 && this.f) {
            LogUtil.a("Track_IDEQ_DataCleaner", "detected state and state already stop. do nothing(FTMS)");
        } else if (this.l && this.r && i == 2 && this.ae == 2) {
            LogUtil.a("Track_IDEQ_DataCleaner", "detected onPause state of FTMP (ForOtherEquipment)");
            this.as = 2;
            this.l = false;
            this.bb = SystemClock.elapsedRealtime();
            this.f = true;
        } else if (this.k && this.r && z) {
            LogUtil.a("Track_IDEQ_DataCleaner", "detected STOP state of FTMP (ForOtherEquipment)");
            this.as = 3;
            this.k = false;
            this.l = false;
        } else if (this.d - this.ap >= 2500) {
            LogUtil.a("Track_IDEQ_DataCleaner", "detected time  (ForOtherEquipment) decreasing twice, mElapsedTimeValid is ", Integer.valueOf(this.j));
            this.as = 2;
            this.k = true;
            this.l = false;
            this.f = true;
        } else if (this.c - this.ao >= 2000) {
            LogUtil.a("Track_IDEQ_DataCleaner", "detected onPause state (ForOtherEquipment), mElapsedTimeValid ", Integer.valueOf(this.j));
            this.as = 2;
            this.k = true;
            this.l = false;
            this.f = true;
        } else if (w()) {
            this.as = 1;
            this.k = true;
            this.l = true;
            this.f = true;
            this.bb = 0L;
        } else {
            LogUtil.a("Track_IDEQ_DataCleaner", "detected state and not meet any conditions(ForOtherEquipment)");
        }
        if (this.d <= 0) {
            this.ad = this.j;
        }
        LogUtil.a("Track_IDEQ_DataCleaner", "startDetectPauseState  (ForOtherEquipment) mSportState:", Integer.valueOf(this.as));
    }

    private boolean w() {
        if (this.bf.getFitnessDataType() == 10 && SystemClock.elapsedRealtime() - this.bb >= 2000) {
            LogUtil.a("Track_IDEQ_DataCleaner", "FITNESS_DATA_INDOORBIKE_DATA AllowedToResume");
            return true;
        }
        if (SystemClock.elapsedRealtime() - this.bb < 4500) {
            return false;
        }
        LogUtil.a("Track_IDEQ_DataCleaner", "AllowedToResume");
        return true;
    }

    private void l() {
        q();
        t();
        n();
        r();
    }

    private void r() {
        int i = this.ak;
        if (i > 0 && i >= this.am) {
            this.am = i;
        }
        int i2 = this.af;
        if (i2 <= 0 || i2 < this.ag) {
            return;
        }
        this.ag = i2;
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0056  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void n() {
        /*
            r5 = this;
            int r0 = r5.as
            java.lang.String r1 = "Track_IDEQ_DataCleaner"
            if (r0 != 0) goto L15
            java.lang.String r0 = "in detectInvalidCalAndPowerValue: mSportState is IDLE and set cal 0"
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            com.huawei.hwlogsmodel.LogUtil.a(r1, r0)
            r0 = 0
            r5.be = r0
            r5.bh = r0
            return
        L15:
            int r0 = r5.g
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            int r2 = r5.be
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            java.lang.String r3 = "in detectInvalidCalAndPowerValue: mEnergyRawLast:"
            java.lang.String r4 = ", mTotalEnergyRaw:"
            java.lang.Object[] r0 = new java.lang.Object[]{r3, r0, r4, r2}
            com.huawei.hwlogsmodel.LogUtil.c(r1, r0)
            int r0 = r5.g
            if (r0 <= 0) goto L48
            int r2 = r5.be
            int r3 = r2 - r0
            r4 = 20
            if (r3 > r4) goto L3a
            if (r0 <= r2) goto L48
        L3a:
            java.lang.String r0 = "in detectInvalidCalAndPowerValue: has been identified as invalid cal"
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            com.huawei.hwlogsmodel.LogUtil.a(r1, r0)
            int r0 = r5.g
            r5.be = r0
            goto L4c
        L48:
            int r0 = r5.be
            r5.g = r0
        L4c:
            int r0 = r5.be
            r5.bh = r0
            int r0 = r5.h
            r2 = 1000(0x3e8, float:1.401E-42)
            if (r0 <= r2) goto L67
            java.lang.String r0 = "in detectInvalidCalAndPowerValue: mInstantaneousPowerRaw is invalid"
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            com.huawei.hwlogsmodel.LogUtil.a(r1, r0)
            int r0 = r5.h
            int r0 = r0 + 50
            int r0 = r0 / 100
            r5.h = r0
        L67:
            int r0 = r5.h
            r5.m = r0
            int r0 = r5.aj
            r5.al = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.laj.n():void");
    }

    private void q() {
        int i;
        if (!this.t && (this.o > 0 || this.aw > 0 || this.an > 0)) {
            LogUtil.a("Track_IDEQ_DataCleaner", "in detectInvalidSpeedValue and has get a speed value larger than 0");
            this.t = true;
        }
        if (this.t && this.o == 0 && this.aw == 0 && this.an == 0 && this.y > 0 && (i = this.as) != 0 && i != 3) {
            LogUtil.a("Track_IDEQ_DataCleaner", "in detectInvalidSpeedValue, speed is 0 and LastElapsedTime >0 ", "and state is not idle and stop, make ElapsedTime = LastElapsedTime");
            this.f14723a = this.y;
        }
        int i2 = this.o;
        if (i2 < 0 || i2 > 4000) {
            LogUtil.a("Track_IDEQ_DataCleaner", "in detectInvalidSpeedValue, speed is invalid, now set it 0");
            this.o = 0;
        }
        this.n = this.o;
        int i3 = this.aw;
        if (i3 > 0) {
            this.ax = i3;
        }
        int i4 = this.an;
        if (i4 > 0) {
            this.ar = i4;
        }
    }

    private void t() {
        LogUtil.a("Track_IDEQ_DataCleaner", "in detectPauseState");
        int i = this.f14723a;
        if (i > 0 || this.q) {
            if (this.p) {
                this.p = false;
                LogUtil.a("Track_IDEQ_DataCleaner", "in detectPauseState, now will get first time value:", Integer.valueOf(i));
                if (this.f14723a > 2) {
                    LogUtil.a("Track_IDEQ_DataCleaner", "in detectPauseState, got first time value and it is larger than 2");
                    this.i = this.f14723a;
                    this.q = true;
                    this.f14723a = 0;
                    return;
                }
                LogUtil.a("Track_IDEQ_DataCleaner", "in detectPauseState, got first time value and it is NOT larger than 2");
            }
            if (this.i > 0) {
                LogUtil.a("Track_IDEQ_DataCleaner", "in detectPauseState, now will handle the situation(got first time value and it is larger than 2)");
                int i2 = this.f14723a;
                int i3 = this.i;
                if (i2 == i3) {
                    LogUtil.a("Track_IDEQ_DataCleaner", "mElapsedTimeForShowAndDetectPause1 (", Integer.valueOf(i2), ") is equals to mFirstTimeValueWhichIsLargerThan2");
                    this.f14723a = 0;
                    return;
                } else if (i2 >= i3) {
                    LogUtil.a("Track_IDEQ_DataCleaner", "mElapsedTimeForShowAndDetectPause3 (", Integer.valueOf(i2), ") is larger than mFirstTimeValueWhichIsLargerThan2");
                    this.i = 0;
                } else {
                    this.q = false;
                    LogUtil.a("Track_IDEQ_DataCleaner", "mElapsedTimeForShowAndDetectPause2 (", Integer.valueOf(i2), ") is smaller than mFirstTimeValueWhichIsLargerThan2");
                    this.i = 0;
                    this.f14723a = 0;
                    return;
                }
            }
            this.q = true;
            aa();
        }
    }

    private void aa() {
        LogUtil.a("Track_IDEQ_DataCleaner", "startDetectPauseState entry");
        ad();
        e();
        int i = this.ah;
        boolean z = (i == 2 && this.ae == 1) || i == 3;
        if (this.as == 3 && this.f) {
            LogUtil.a("Track_IDEQ_DataCleaner", "detected state and state already stop. do nothing");
        } else if (this.l && this.r && i == 2 && this.ae == 2) {
            LogUtil.a("Track_IDEQ_DataCleaner", "detected onPause state of FTMP");
            this.as = 2;
            this.l = false;
            this.bb = SystemClock.elapsedRealtime();
            this.f = true;
        } else if (this.k && this.r && z) {
            LogUtil.a("Track_IDEQ_DataCleaner", "detected STOP state of FTMP");
            this.as = 3;
            this.k = false;
            this.l = false;
        } else {
            long j = this.d;
            long j2 = this.ap;
            long j3 = this.aq;
            if (j - j2 >= j3) {
                LogUtil.a("Track_IDEQ_DataCleaner", "detected time decreasing twice, mElapsedTimeForShowAndDetectPause is ", Integer.valueOf(this.f14723a));
                this.as = 2;
                this.k = true;
                this.l = false;
                this.f = true;
            } else if (this.c - this.ao >= j3) {
                LogUtil.a("Track_IDEQ_DataCleaner", "detected onPause state");
                this.as = 2;
                this.k = true;
                this.l = false;
                this.f = true;
            } else if (SystemClock.elapsedRealtime() - this.bb >= 4500) {
                this.as = 1;
                this.k = true;
                this.l = true;
                this.bb = 0L;
                this.f = true;
            } else {
                LogUtil.a("Track_IDEQ_DataCleaner", "detected state and not meet any conditions");
            }
        }
        if (this.d <= 0) {
            this.y = this.f14723a;
        }
        LogUtil.a("Track_IDEQ_DataCleaner", "startDetectPauseState mSportState:", Integer.valueOf(this.as));
    }

    private void ad() {
        this.aq = 2500L;
        DeviceInformation deviceInformation = this.e;
        if (deviceInformation != null && deviceInformation.getModelString() != null && (this.e.getModelString().toUpperCase(Locale.ENGLISH).contains("T7XE") || this.e.getModelString().toUpperCase(Locale.ENGLISH).contains("T-7XE"))) {
            this.aq = 3000L;
        }
        int i = this.y;
        if (i > 0 && i == this.f14723a) {
            if (this.ao == 0) {
                this.ao = SystemClock.elapsedRealtime();
            }
            this.c = SystemClock.elapsedRealtime();
        } else {
            this.ao = 0L;
            this.c = 0L;
        }
    }

    private void e() {
        int i = this.y;
        if (i > 0 && i - this.f14723a > 2) {
            LogUtil.a("Track_IDEQ_DataCleaner", "detected time decreasing, ", ", mLastElapsedTimeForShowAndDetectPause:", Integer.valueOf(i), ", mElapsedTimeForShowAndDetectPause:", Integer.valueOf(this.f14723a));
            this.f14723a = this.y;
            this.d = SystemClock.elapsedRealtime();
            if (this.ap == 0) {
                this.ap = SystemClock.elapsedRealtime();
                return;
            }
            return;
        }
        if (i - this.f14723a <= 2) {
            this.ap = 0L;
            this.d = 0L;
        } else {
            LogUtil.h("Track_IDEQ_DataCleaner", "Unknown state in startDetectPauseState");
        }
    }

    private void d() {
        int i = this.ad;
        if (i > 0 && i - this.j > 2) {
            LogUtil.a("Track_IDEQ_DataCleaner", "detected time  (ForOtherEquipment) decreasing, ", ", mLastElapsedTimeValid:", Integer.valueOf(i), ", mElapsedTimeValid:", Integer.valueOf(this.j));
            this.j = this.ad;
            this.d = SystemClock.elapsedRealtime();
            if (this.ap == 0) {
                this.ap = SystemClock.elapsedRealtime();
                return;
            }
            return;
        }
        if (i - this.j <= 2) {
            this.ap = 0L;
            this.d = 0L;
        } else {
            LogUtil.h("Track_IDEQ_DataCleaner", "Unknown state in startDetectPauseState (ForOtherEquipment)");
        }
    }

    private void k() {
        int i = this.ad;
        if (i > 0 && i == this.j) {
            if (this.ao == 0) {
                this.ao = SystemClock.elapsedRealtime();
            }
            this.c = SystemClock.elapsedRealtime();
        } else {
            this.ao = 0L;
            this.c = 0L;
        }
    }

    public void b() {
        this.an = 0;
        this.o = 0;
        this.f14723a = 0;
        this.be = 0;
        this.g = 0;
        this.bh = 0;
        this.h = 0;
        this.ak = 0;
        this.am = 0;
        this.af = 0;
        this.ag = 0;
        this.bc = 0;
        this.ba = 0;
        this.az = 0;
        this.au = 0;
        this.b = 0;
        this.j = 0;
        this.av = 0;
        this.at = 0;
        this.as = 0;
        this.i = 0;
        this.y = 0;
        this.ad = 0;
        this.ap = 0L;
        this.d = 0L;
        this.ao = 0L;
        this.c = 0L;
        this.p = true;
        this.q = false;
        this.t = false;
        this.k = false;
        this.l = false;
        this.f = false;
        this.bb = 0L;
        this.aq = 2500L;
        ab();
        ac();
    }
}
