package defpackage;

import com.huawei.health.ecologydevice.fitness.datastruct.RopeRealData;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes3.dex */
public class cyb {
    private static cyb b;
    private RopeRealData d;

    private cyb() {
    }

    public static void b() {
        cyb cybVar = b;
        if (cybVar == null) {
            b = new cyb();
        } else {
            cybVar.e();
        }
    }

    public static RopeRealData b(RopeRealData ropeRealData) {
        cyb cybVar = b;
        return cybVar != null ? cybVar.d(ropeRealData) : ropeRealData;
    }

    public static void d() {
        b = null;
    }

    private void e() {
        this.d = null;
    }

    private RopeRealData d(RopeRealData ropeRealData) {
        LogUtil.c("PDROPE_RopeRealDataCleaner", "RopeRealData:UpdateTime ", Long.valueOf(ropeRealData.getUpdateTime()), ", Status ", Integer.valueOf(ropeRealData.getStatus()), ", ElapsedTime ", Integer.valueOf(ropeRealData.getElapsedTime()), ", TotalSkip ", Integer.valueOf(ropeRealData.getTotalSkip()), ", InstantaneousSpeed ", Integer.valueOf(ropeRealData.getInstantaneousSpeed()), ", InterruptTimes ", Integer.valueOf(ropeRealData.getInterruptTimes()), ",CurrentContinueSkip ", Integer.valueOf(ropeRealData.getCurrentContinueSkip()), ",TotalEnergy ", Integer.valueOf(ropeRealData.getTotalEnergy()));
        if (crk.b(ropeRealData.getRopeSkippingMode()) != 8) {
            a(ropeRealData);
        }
        return ropeRealData;
    }

    private void a(RopeRealData ropeRealData) {
        if (ropeRealData.getInstantaneousSpeed() > 480) {
            LogUtil.c("PDROPE_RopeRealDataCleaner", "correctData, InstantaneousSpeed:", Integer.valueOf(ropeRealData.getInstantaneousSpeed()));
            ropeRealData.setInstantaneousSpeed(-1);
        }
        if (this.d == null) {
            e(ropeRealData);
            return;
        }
        if (ropeRealData.getElapsedTime() < this.d.getElapsedTime()) {
            LogUtil.c("PDROPE_RopeRealDataCleaner", "correctData, ElapsedTime:", Integer.valueOf(ropeRealData.getElapsedTime()), ", last ElapsedTime:", Integer.valueOf(this.d.getElapsedTime()));
            ropeRealData.setElapsedTime(this.d.getElapsedTime());
        } else {
            this.d.setElapsedTime(ropeRealData.getElapsedTime());
        }
        if (ropeRealData.getTotalSkip() < this.d.getTotalSkip()) {
            LogUtil.c("PDROPE_RopeRealDataCleaner", "correctData, TotalSkip:", Integer.valueOf(ropeRealData.getTotalSkip()), ", last TotalSkip:", Integer.valueOf(this.d.getTotalSkip()));
            ropeRealData.setTotalSkip(this.d.getTotalSkip());
        } else {
            this.d.setTotalSkip(ropeRealData.getTotalSkip());
        }
        if (ropeRealData.getTotalEnergy() < this.d.getTotalEnergy()) {
            LogUtil.c("PDROPE_RopeRealDataCleaner", "correctData, TotalEnergy:", Integer.valueOf(ropeRealData.getTotalEnergy()), ", last TotalEnergy:", Integer.valueOf(this.d.getTotalEnergy()));
            ropeRealData.setTotalEnergy(this.d.getTotalEnergy());
        } else {
            this.d.setTotalEnergy(ropeRealData.getTotalEnergy());
        }
        if (ropeRealData.getInterruptTimes() < this.d.getInterruptTimes() && ropeRealData.getInterruptTimes() != 65535) {
            LogUtil.c("PDROPE_RopeRealDataCleaner", "correctData, InterruptTimes:", Integer.valueOf(ropeRealData.getInterruptTimes()), ", last InterruptTimes:", Integer.valueOf(this.d.getInterruptTimes()));
            ropeRealData.setInterruptTimes(this.d.getInterruptTimes());
        } else {
            this.d.setInterruptTimes(ropeRealData.getInterruptTimes());
        }
        if (ropeRealData.getCurrentContinueSkip() > ropeRealData.getTotalSkip() && ropeRealData.getCurrentContinueSkip() != 65535) {
            LogUtil.c("PDROPE_RopeRealDataCleaner", "correctData, ContinueSkip:", Integer.valueOf(ropeRealData.getCurrentContinueSkip()), ", TotalSkip:", Integer.valueOf(ropeRealData.getTotalSkip()));
            ropeRealData.setCurrentContinueSkip(this.d.getCurrentContinueSkip());
        } else {
            this.d.setCurrentContinueSkip(ropeRealData.getCurrentContinueSkip());
        }
    }

    private void e(RopeRealData ropeRealData) {
        RopeRealData ropeRealData2 = new RopeRealData();
        this.d = ropeRealData2;
        ropeRealData2.setElapsedTime(ropeRealData.getElapsedTime());
        this.d.setTotalSkip(ropeRealData.getTotalSkip());
        this.d.setTotalEnergy(ropeRealData.getTotalEnergy());
        this.d.setInterruptTimes(ropeRealData.getInterruptTimes());
        this.d.setCurrentContinueSkip(ropeRealData.getCurrentContinueSkip());
    }
}
