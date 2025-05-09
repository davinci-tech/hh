package com.huawei.ui.main.stories.fitness.interactors.stressAdvice;

import com.huawei.ui.main.R$string;
import defpackage.jec;
import defpackage.pua;
import java.util.Date;

/* loaded from: classes.dex */
public class PressureScoreMapAdvice {
    public static int a(long j, int i) {
        int e = e(j);
        int e2 = e(i);
        if (e == 1) {
            if (e2 == 1) {
                return PressureAdvice.MORNING_RELAX.getAdviceRes();
            }
            if (e2 == 2) {
                return PressureAdvice.MORNING_NORMAL.getAdviceRes();
            }
            if (e2 == 3) {
                return PressureAdvice.MORNING_MEDIUM.getAdviceRes();
            }
            if (e2 == 4) {
                return PressureAdvice.MORNING_HIGH.getAdviceRes();
            }
        }
        if (e == 2) {
            if (e2 == 1) {
                return PressureAdvice.DAYTIME_RELAX.getAdviceRes();
            }
            if (e2 == 2) {
                return PressureAdvice.DAYTIME_NORMAL.getAdviceRes();
            }
            if (e2 == 3) {
                return PressureAdvice.DAYTIME_MEDIUM.getAdviceRes();
            }
            if (e2 == 4) {
                return PressureAdvice.DAYTIME_HIGH.getAdviceRes();
            }
        }
        if (e == 3) {
            if (e2 == 1) {
                return PressureAdvice.EVENING_RELAX.getAdviceRes();
            }
            if (e2 == 2) {
                return PressureAdvice.EVENING_NORMAL.getAdviceRes();
            }
            if (e2 == 3) {
                return PressureAdvice.EVENING_MEDIUM.getAdviceRes();
            }
            if (e2 == 4) {
                return PressureAdvice.EVENING_HIGH.getAdviceRes();
            }
        }
        return PressureAdvice.DEFAULT.getAdviceRes();
    }

    private static int e(long j) {
        int b = b(j);
        if (b >= 0 && b < 52200.0f) {
            return 1;
        }
        float f = b;
        if (f < 52200.0f || f >= 73800.0f) {
            return (f < 73800.0f || b >= 86400) ? 0 : 3;
        }
        return 2;
    }

    private static int e(int i) {
        if (pua.j(i)) {
            return 1;
        }
        if (pua.d(i)) {
            return 2;
        }
        if (pua.b(i)) {
            return 3;
        }
        return pua.e(i) ? 4 : 0;
    }

    private static int b(long j) {
        return ((int) (j - jec.a(new Date(j)).getTime())) / 1000;
    }

    /* loaded from: classes9.dex */
    public enum PressureAdvice {
        MORNING_RELAX(R$string.IDS_pressure_advice_1002),
        MORNING_NORMAL(R$string.IDS_pressure_advice_2004),
        MORNING_MEDIUM(R$string.IDS_pressure_advice_2006),
        MORNING_HIGH(R$string.IDS_pressure_advice_2008),
        DAYTIME_RELAX(R$string.IDS_pressure_advice_1002),
        DAYTIME_NORMAL(R$string.IDS_pressure_advice_2004),
        DAYTIME_MEDIUM(R$string.IDS_pressure_advice_2014),
        DAYTIME_HIGH(R$string.IDS_pressure_advice_2016),
        EVENING_RELAX(R$string.IDS_pressure_advice_1002),
        EVENING_NORMAL(R$string.IDS_pressure_advice_1004),
        EVENING_MEDIUM(R$string.IDS_pressure_advice_1006),
        EVENING_HIGH(R$string.IDS_pressure_advice_1008),
        DEFAULT(R$string.IDS_pressure_advice_3002);

        private final int mAdviceRes;

        PressureAdvice(int i) {
            this.mAdviceRes = i;
        }

        public int getAdviceRes() {
            return this.mAdviceRes;
        }
    }
}
