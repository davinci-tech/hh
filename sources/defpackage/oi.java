package defpackage;

import com.chipsea.health.CSAlgorithmUtils;
import java.util.Calendar;

/* loaded from: classes2.dex */
public class oi {
    private float b;
    private int d;
    private int f;
    private float g;
    private byte h;
    private float j;
    private final String e = "No Authorized Access!";
    private final String c = "Illegal device!";

    /* renamed from: a, reason: collision with root package name */
    private final long f15689a = 8613800138008L;
    private boolean i = true;
    private int n = Calendar.getInstance().get(1);

    public void b(float f, float f2, byte b, int i, float f3) {
        this.b = f;
        this.g = f2;
        this.h = b;
        this.d = i;
        this.j = f3;
        this.f = 0;
    }

    public int e(float f, float f2, byte b, int i, float f3) {
        if (f < 90.0f || f > 220.0f) {
            return -3;
        }
        if (f2 < 20.0f || f2 > 150.0f) {
            return -2;
        }
        if (i < 10 || i > 99) {
            return -4;
        }
        if (b != 0 && b != 1) {
            return -5;
        }
        this.b = f;
        this.g = f2;
        this.h = b;
        this.d = i;
        this.j = f3;
        this.f = 0;
        return 0;
    }

    public float h() throws og {
        if (!this.i) {
            throw new og("No Authorized Access!");
        }
        if (this.f == 0) {
            return new CSAlgorithmUtils().getTFR(this.b, this.h, this.g, this.d, (int) this.j, this.n);
        }
        return new CSAlgorithmUtils().getTFRS(this.b, this.h, this.g, this.d, (int) this.j, this.n);
    }

    public float j() throws og {
        if (!this.i) {
            throw new og("No Authorized Access!");
        }
        if (this.f == 0) {
            return new CSAlgorithmUtils().getSLM(this.b, this.h, this.g, this.d, (int) this.j, this.n);
        }
        return new CSAlgorithmUtils().getSLMS(this.b, this.h, this.g, this.d, (int) this.j, this.n);
    }

    public float g() throws og {
        if (!this.i) {
            throw new og("No Authorized Access!");
        }
        if (this.f == 0) {
            return new CSAlgorithmUtils().getSMM(this.b, this.h, this.g, this.d, (int) this.j, this.n);
        }
        return new CSAlgorithmUtils().getSMMS(this.b, this.h, this.g, this.d, (int) this.j, this.n);
    }

    public float b() throws og {
        if (!this.i) {
            throw new og("No Authorized Access!");
        }
        if (this.f == 0) {
            return new CSAlgorithmUtils().getPM(this.b, this.h, this.g, this.d, (int) this.j, this.n);
        }
        return new CSAlgorithmUtils().getPMS(this.b, this.h, this.g, this.d, (int) this.j, this.n);
    }

    public float e() throws og {
        if (!this.i) {
            throw new og("No Authorized Access!");
        }
        if (this.f == 0) {
            return new CSAlgorithmUtils().getBFR(this.b, this.h, this.g, this.d, (int) this.j, this.n);
        }
        return new CSAlgorithmUtils().getBFRS(this.b, this.h, this.g, this.d, (int) this.j, this.n);
    }

    public float c() throws og {
        if (!this.i) {
            throw new og("No Authorized Access!");
        }
        if (this.f == 0) {
            return new CSAlgorithmUtils().getBMR(this.b, this.h, this.g, this.d, (int) this.j, this.n);
        }
        return new CSAlgorithmUtils().getBMRS(this.b, this.h, this.g, this.d, (int) this.j, this.n);
    }

    public float d() throws og {
        if (!this.i) {
            throw new og("No Authorized Access!");
        }
        if (this.f == 0) {
            return new CSAlgorithmUtils().getMSW(this.b, this.h, this.g, this.d, (int) this.j, this.n);
        }
        return new CSAlgorithmUtils().getMSWS(this.b, this.h, this.g, this.d, (int) this.j, this.n);
    }

    public float f() throws og {
        if (!this.i) {
            throw new og("No Authorized Access!");
        }
        if (this.f == 0) {
            return new CSAlgorithmUtils().getVFR(this.b, this.h, this.g, this.d, (int) this.j, this.n);
        }
        return new CSAlgorithmUtils().getVFRS(this.b, this.h, this.g, this.d, (int) this.j, this.n);
    }

    public float a() throws og {
        int bodyAgeS;
        if (!this.i) {
            throw new og("No Authorized Access!");
        }
        if (this.f == 0) {
            bodyAgeS = new CSAlgorithmUtils().getBodyAge(this.b, this.h, this.g, this.d, (int) this.j, this.n);
        } else {
            bodyAgeS = new CSAlgorithmUtils().getBodyAgeS(this.b, this.h, this.g, this.d, (int) this.j, this.n);
        }
        int i = this.d;
        int i2 = bodyAgeS - i;
        if (i2 > 10) {
            bodyAgeS = i + 10;
        } else if (i2 < -10) {
            bodyAgeS = i - 10;
        }
        return bodyAgeS;
    }

    public int i() throws og {
        if (!this.i) {
            throw new og("No Authorized Access!");
        }
        if (this.f == 0) {
            return new CSAlgorithmUtils().getScore(this.b, this.h, this.g, this.d, (int) this.j, this.n);
        }
        return new CSAlgorithmUtils().getScoreS(this.b, this.h, this.g, this.d, (int) this.j, this.n);
    }

    public static int b(float f, byte b, float f2, int i, float f3) {
        return new CSAlgorithmUtils().getResistance(f, b, f2, i, f3);
    }
}
