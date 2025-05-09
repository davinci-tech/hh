package defpackage;

import java.math.BigDecimal;

/* loaded from: classes2.dex */
public class ne {

    /* renamed from: a, reason: collision with root package name */
    private byte f15268a;
    private boolean b = false;
    private int c;
    private float d;
    private float e;
    private float f;

    public static float a(float f, byte b, float f2, float f3) {
        return ((100.0f - f2) - f3) - (((b == 1 ? 3.0f : 2.5f) / f) * 100.0f);
    }

    private static float c(byte b, float f) {
        float f2;
        float f3;
        if (b == 1) {
            f2 = f - 80.0f;
            f3 = 0.7f;
        } else {
            f2 = f - 70.0f;
            f3 = 0.6f;
        }
        return f2 * f3;
    }

    /* JADX WARN: Code restructure failed: missing block: B:43:0x002c, code lost:
    
        if (r8 < 27.0f) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x00c4, code lost:
    
        r8 = 2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x004c, code lost:
    
        if (r8 < 28.0f) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x0068, code lost:
    
        if (r8 < 30.0f) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x0086, code lost:
    
        if (r8 < 40.0f) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:89:0x00a5, code lost:
    
        if (r8 < 41.0f) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:99:0x00c2, code lost:
    
        if (r8 < 42.0f) goto L85;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static float c(float r8, float r9, byte r10, int r11) {
        /*
            Method dump skipped, instructions count: 258
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ne.c(float, float, byte, int):float");
    }

    public static float c(float f, float f2, byte b, int i, float f3, float f4, float f5) {
        float f6;
        float f7;
        float f8;
        float f9;
        float f10 = (f2 / (f * f)) * 100.0f * 100.0f;
        float f11 = (((f10 * f10) * (-5.686f)) + (f10 * 241.7f)) - 2470.0f;
        if (f11 < 55.0f) {
            f11 = 55.0f;
        }
        if (f11 > 96.0f) {
            f11 = 96.0f;
        }
        float f12 = f3 + (i * 0.03f);
        if (b == 1) {
            float f13 = f12 * f12;
            float f14 = f13 * f12;
            f6 = ((((f14 * f12) * 1.085E-4f) - (f14 * 0.003181f)) - (f13 * 0.2952f)) + (f12 * 10.85f) + 0.4248f;
            f7 = 0.77f;
        } else {
            float f15 = f12 * f12;
            float f16 = f15 * f12;
            f6 = (((((f16 * f12) * 2.469E-4f) - (f16 * 0.02788f)) + (f15 * 0.9597f)) - (f12 * 10.02f)) + 80.42f;
            f7 = 0.735f;
        }
        float f17 = ((double) f6) >= 55.0d ? f6 : 55.0f;
        float f18 = (f4 + 90.0f) - (f2 * f7);
        float f19 = f18 <= 100.0f ? f18 : 100.0f;
        if (f5 >= 15.0d) {
            f8 = -50.0f;
        } else {
            float f20 = f5 * f5;
            float f21 = f20 * f5;
            float f22 = f21 * f5;
            f8 = (((((f22 * f5) * 0.007212f) - (f22 * 0.2825f)) + (f21 * 3.912f)) - (f20 * 22.27f)) + (30.38f * f5) + 89.35f;
        }
        float f23 = f8 >= -50.0f ? f8 : -50.0f;
        float f24 = 0.0f;
        if (f5 == 0.0f) {
            f9 = 0.48f;
        } else {
            f24 = 0.08f;
            f9 = 0.4f;
        }
        int i2 = (int) ((f9 * f11) + (f17 * 0.4f) + (f19 * 0.1f) + (f24 * f23));
        if (i2 < 45) {
            i2 = 45;
        }
        if (i2 > 100) {
            i2 = 100;
        }
        return i2;
    }

    public float j() {
        return this.d;
    }

    public byte g() {
        return this.f15268a;
    }

    public ne(float f, float f2, byte b, int i, float f3) {
        this.d = f;
        this.f = f2;
        this.f15268a = b;
        this.c = i;
        this.e = f3;
    }

    public static int e(String str) {
        if (str == null || str.trim().length() == 0) {
            return 0;
        }
        String[] split = str.trim().split("\\|");
        if (split.length <= 0) {
            return 0;
        }
        for (String str2 : split) {
            String[] split2 = str2.trim().split("\\:");
            if (split2.length > 1 && split2[0].equals("2")) {
                return Integer.parseInt(split2[1]);
            }
        }
        return 0;
    }

    public float c() {
        float f = this.f;
        float f2 = this.d;
        return (f / (f2 * f2)) * 100.0f * 100.0f;
    }

    public float f() {
        return (e() * this.f) / 100.0f;
    }

    private float n() {
        float f;
        float f2;
        float f3;
        if (this.b) {
            if (this.f15268a == 1) {
                float f4 = this.d;
                f = this.f;
                f2 = (f4 * (-0.2893f)) + (0.6143f * f) + (this.c * 0.0355f) + (this.e * 0.0207f);
                f3 = 7.9298f;
            } else {
                float f5 = this.d;
                f = this.f;
                f2 = (f5 * (-0.2923f)) + (0.787f * f) + (this.c * 0.0071f) + (this.e * 0.0139f);
                f3 = 9.6791f;
            }
        } else if (this.f15268a == 1) {
            float f6 = this.d;
            f = this.f;
            f2 = (f6 * (-0.3315f)) + (0.6216f * f) + (this.c * 1.0f * 0.0183f) + (this.e * 0.0085f);
            f3 = 22.554f;
        } else {
            float f7 = this.d;
            f = this.f;
            f2 = (f7 * (-0.3332f)) + (0.7509f * f) + (this.c * 1.0f * 0.0196f) + (this.e * 0.0072f);
            f3 = 22.7193f;
        }
        return ((f2 + f3) / f) * 100.0f;
    }

    public float e() {
        float n = n();
        if (n < 5.0f) {
            n = 5.0f;
        }
        if (n > 45.0f) {
            return 45.0f;
        }
        return n;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x009a  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x00a0  */
    /* JADX WARN: Removed duplicated region for block: B:20:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public float k() {
        /*
            Method dump skipped, instructions count: 171
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ne.k():float");
    }

    public float o() {
        float f;
        float f2;
        float f3;
        int i = this.c;
        if (i <= 17) {
            return 0.0f;
        }
        if (this.b) {
            if (this.f15268a == 1) {
                float f4 = this.d;
                f = this.f;
                f2 = (((f4 * 0.2224f) + (0.167f * f)) - (i * 0.0411f)) - (this.e * 0.0311f);
                f3 = 6.9026f;
            } else {
                float f5 = this.d;
                f = this.f;
                f2 = (((f5 * 0.1058f) + (0.2168f * f)) - (i * 0.0016f)) - (this.e * 0.016f);
                f3 = 9.8471f;
            }
        } else if (this.f15268a == 1) {
            float f6 = this.d;
            f = this.f;
            f2 = (((f6 * 0.0939f) + (0.3758f * f)) - ((i * 1.0f) * 0.0032f)) - (this.e * 0.006925f);
            f3 = 0.097f;
        } else {
            float f7 = this.d;
            f = this.f;
            f2 = (((f7 * 0.0877f) + (0.2973f * f)) + ((i * 1.0f) * 0.0128f)) - (this.e * 0.00603f);
            f3 = 0.5175f;
        }
        float f8 = ((f2 + f3) / f) * 100.0f;
        if (f8 < 20.0f) {
            f8 = 20.0f;
        }
        if (f8 > 85.0f) {
            f8 = 85.0f;
        }
        return new BigDecimal(f8).setScale(2, 4).floatValue();
    }

    private float m() {
        float f;
        float f2;
        float f3;
        float f4;
        float n = n();
        if (n > 45.0f) {
            float f5 = this.f;
            f4 = f5 - (0.45f * f5);
            f = 4.0f;
        } else {
            f = 1.0f;
            if (n < 5.0f) {
                float f6 = this.f;
                f4 = f6 - (0.05f * f6);
            } else {
                if (this.b) {
                    if (this.f15268a == 1) {
                        f2 = (((this.d * 0.2764f) + (this.f * 0.3662f)) - (this.c * 0.0337f)) - (this.e * 0.0199f);
                        f3 = 7.739f;
                    } else {
                        f2 = (((this.d * 0.2676f) + (this.f * 0.1948f)) - (this.c * 0.0063f)) - (this.e * 0.0127f);
                        f3 = 7.8411f;
                    }
                } else if (this.f15268a == 1) {
                    f2 = (((this.d * 0.2867f) + (this.f * 0.3894f)) - ((this.c * 1.0f) * 0.0408f)) - (this.e * 0.01235f);
                    f3 = 15.7665f;
                } else {
                    f2 = (((this.d * 0.3186f) + (this.f * 0.1934f)) - ((this.c * 1.0f) * 0.0206f)) - (this.e * 0.0132f);
                    f3 = 16.4556f;
                }
                float f7 = f2 - f3;
                if (f7 < 7.0f) {
                    f7 = 7.0f;
                }
                if (f7 > 141.5f) {
                    return 141.5f;
                }
                return f7;
            }
        }
        return f4 - f;
    }

    public float e(float f) {
        float f2 = this.f;
        float f3 = 0.0f;
        if (f2 > 0.0f) {
            if (f == 0.0f) {
                f = m();
                f2 = this.f;
            }
            f3 = (f / f2) * 100.0f;
        }
        return new BigDecimal(f3).setScale(2, 4).floatValue();
    }

    public float i() {
        float f = (this.f - f()) - m();
        float f2 = 1.0f;
        if (f >= 1.0f) {
            f2 = 4.0f;
            if (f <= 4.0f) {
                return f;
            }
        }
        return f2;
    }

    public float a() {
        float f;
        float f2;
        if (this.b) {
            if (this.f15268a == 1) {
                f = (((this.d * 7.2819f) + (this.f * 12.3775f)) - (this.c * 4.4731f)) - (this.e * 0.5458f);
                f2 = 84.7412f;
            } else {
                f = (((this.d * 6.3442f) + (this.f * 9.8875f)) - (this.c * 3.4612f)) - (this.e * 0.3056f);
                f2 = 59.9762f;
            }
        } else if (this.f15268a == 1) {
            f = (((this.d * 7.5037f) + (this.f * 13.1523f)) - ((this.c * 1.0f) * 4.3376f)) - (this.e * 0.3486f);
            f2 = 311.7751f;
        } else {
            f = (((this.d * 7.5432f) + (this.f * 9.9474f)) - ((this.c * 1.0f) * 3.4382f)) - (this.e * 0.309f);
            f2 = 288.2821f;
        }
        return new BigDecimal(f - f2).setScale(1, 4).floatValue();
    }

    public int d() {
        float f;
        float f2;
        int i = this.c;
        if (i <= 17) {
            return 0;
        }
        if (this.b) {
            if (this.f15268a == 1) {
                f = (this.d * (-0.5378f)) + (this.f * 0.7642f) + (i * 0.3667f) + (this.e * 0.039f);
                f2 = 33.8131f;
            } else {
                f = (this.d * (-0.9531f)) + (this.f * 1.5246f) + (i * 0.4584f) + (this.e * 0.0374f);
                f2 = 58.5035f;
            }
        } else if (this.f15268a == 1) {
            f = (this.d * (-0.7471f)) + (this.f * 0.9161f) + (i * 1.0f * 0.4184f) + (this.e * 0.0517f);
            f2 = 54.2267f;
        } else {
            f = (this.d * (-1.1165f)) + (this.f * 1.5784f) + (i * 1.0f * 0.4615f) + (this.e * 0.0415f);
            f2 = 83.2548f;
        }
        int i2 = (int) (f + f2);
        if (i2 - i > 10) {
            i2 = i + 10;
        } else if (i - i2 > 10) {
            i2 = i - 10;
        }
        if (i2 < 18) {
            i2 = 18;
        }
        if (i2 > 80) {
            return 80;
        }
        return i2;
    }

    public float b() {
        return c(this.f15268a, this.d);
    }

    public float l() {
        return c(e(), c(), this.f15268a, this.c);
    }

    public float h() {
        return d(this.d, this.f, this.f15268a, this.c, e(), m(), k());
    }

    private float d(float f, float f2, byte b, int i, float f3, float f4, float f5) {
        if (i > 17) {
            return c(f, f2, b, i, f3, f4, f5);
        }
        return 0.0f;
    }

    public static float a(float f, float f2, byte b, int i, float f3) {
        return c(f3, (f2 / (f * f)) * 100.0f * 100.0f, b, i);
    }
}
