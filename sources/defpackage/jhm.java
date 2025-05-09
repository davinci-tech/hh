package defpackage;

import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.hihealthservice.old.model.OldToNewMotionPath;
import com.huawei.hwcommonmodel.fitnessdatatype.MotionGoal;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.List;

/* loaded from: classes5.dex */
public class jhm {

    static class c {
        private int b;
        private String c;
        private int e;

        private c() {
            this.e = 0;
            this.c = "";
            this.b = 0;
        }

        static /* synthetic */ int b(c cVar, int i) {
            int i2 = cVar.b + i;
            cVar.b = i2;
            return i2;
        }

        static /* synthetic */ int e(c cVar, int i) {
            int i2 = cVar.e + i;
            cVar.e = i2;
            return i2;
        }
    }

    public static byte[] a(List<MotionGoal> list) {
        String str = "";
        if (list != null && list.size() > 0) {
            String str2 = "" + cvx.e(129);
            c cVar = new c();
            for (MotionGoal motionGoal : list) {
                cVar.c += cvx.e(3) + cvx.e(1) + cvx.e(motionGoal.getGoalType());
                c.e(cVar, 3);
                c.b(cVar, 3);
                c d = d(motionGoal, cVar);
                int d2 = d(motionGoal);
                int i = -1;
                if (d2 != -1) {
                    d = e(d2, d);
                }
                int d3 = d();
                c c2 = c(d2, d3, (motionGoal.getDataType() & 4) != 0 ? motionGoal.getDutationGoal() : -1, b(d2, d3, (motionGoal.getDataType() & 2) != 0 ? motionGoal.getCalorieGoal() : -1, d));
                if ((motionGoal.getDataType() & 8) != 0) {
                    i = motionGoal.getDutationGoal();
                }
                cVar = d(i, c2);
                cVar.c = cvx.e(OldToNewMotionPath.SPORT_TYPE_TENNIS) + cvx.e(cVar.b) + cVar.c;
                c.e(cVar, 2);
                str = str + cVar.c;
            }
            String e = cvx.e(cVar.e);
            if (cVar.e > 127) {
                e = cvx.e((cVar.e >> 8) + 128) + cvx.e(cVar.e & 255);
            }
            str = str2 + e + str;
        }
        return cvx.a(str);
    }

    private static int d() {
        DeviceCapability d = cvs.d();
        if (d != null) {
            return d.getFitnessFrameType();
        }
        LogUtil.h("FitnessPackageGoal", "setGoal deviceCapability is null");
        return -1;
    }

    private static c d(MotionGoal motionGoal, c cVar) {
        cVar.c += cvx.e(4) + cvx.e(1);
        if (d() == 1) {
            cVar.c += cvx.e(1);
        } else {
            cVar.c += cvx.e(motionGoal.getMotionType());
        }
        c.e(cVar, 3);
        c.b(cVar, 3);
        return cVar;
    }

    private static int d(MotionGoal motionGoal) {
        if ((motionGoal.getDataType() & 1) != 0) {
            return motionGoal.getStepGoal();
        }
        return -1;
    }

    private static c e(int i, c cVar) {
        cVar.c += cvx.e(5) + cvx.e(4) + cvx.e(i >> 24) + cvx.e((i >> 16) & 255) + cvx.e((i >> 8) & 255) + cvx.e(i & 255);
        c.e(cVar, 6);
        c.b(cVar, 6);
        return cVar;
    }

    private static c b(int i, int i2, int i3, c cVar) {
        if (i3 != -1) {
            cVar.c += cvx.e(6) + cvx.e(4) + cvx.b(i3);
            c.e(cVar, 6);
            c.b(cVar, 6);
        } else if (i2 == 1) {
            cVar.c += cvx.e(6) + cvx.e(4) + cvx.b(i / 30);
            c.e(cVar, 6);
            c.b(cVar, 6);
        } else {
            LogUtil.h("FitnessPackageGoal", "goal calorie is invalid");
        }
        return cVar;
    }

    private static c c(int i, int i2, int i3, c cVar) {
        if (i3 != -1) {
            cVar.c += cvx.e(7) + cvx.e(4) + cvx.b(i3);
            c.e(cVar, 6);
            c.b(cVar, 6);
        } else if (i2 == 1) {
            cVar.c += cvx.e(7) + cvx.e(4) + cvx.b(i);
            c.e(cVar, 6);
            c.b(cVar, 6);
        } else {
            LogUtil.h("FitnessPackageGoal", "makeGoalDistance else goalDistance:", Integer.valueOf(i3), ", dataType:", Integer.valueOf(i2));
        }
        return cVar;
    }

    private static c d(int i, c cVar) {
        if (i != -1) {
            cVar.c += cvx.e(8) + cvx.e(2) + cvx.a(i);
            c.e(cVar, 4);
            c.b(cVar, 4);
        }
        return cVar;
    }
}
