package com.huawei.ucd.medal;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import com.huawei.ucd.medal.MedalBackType;
import defpackage.njw;
import defpackage.njx;
import defpackage.nkf;
import java.util.Arrays;

/* loaded from: classes9.dex */
public class MedalBackContent {

    /* renamed from: a, reason: collision with root package name */
    public MedalBackType.ColorType f8748a;
    public nkf b;
    public Bitmap c;
    public String[] d;
    public MedalBackType.ModelType e;
    public nkf g;
    public int j;

    /* synthetic */ MedalBackContent(AnonymousClass5 anonymousClass5) {
        this();
    }

    private MedalBackContent() {
        this.b = new nkf();
        this.g = new nkf();
    }

    public String toString() {
        return "MedalBackContent{mModelType=" + this.e + ", mColorType=" + this.f8748a + ", mTextColor=" + this.j + ", mFirstStrOffsetXTopY=" + this.b.toString() + ", mSecondStrOffsetXTopY=" + this.g.toString() + ", mContents=" + Arrays.toString(this.d) + ", mIconContent=" + this.c + '}';
    }

    public static class Builder {

        /* renamed from: a, reason: collision with root package name */
        private String[] f8750a;
        public nkf b;
        private Context c;
        public nkf d;
        private MedalBackType.ColorType e;
        private MedalBackType.ModelType g;
        private Bitmap h;
        private Integer j;

        public Builder(Context context) {
            this.c = context;
        }

        public Builder a(MedalBackType.ModelType modelType) {
            this.g = modelType;
            return this;
        }

        public Builder d(MedalBackType.ColorType colorType) {
            this.e = colorType;
            return this;
        }

        public Builder d(String[] strArr) {
            this.f8750a = strArr;
            return this;
        }

        public Builder cwC_(Bitmap bitmap) {
            this.h = bitmap;
            return this;
        }

        public MedalBackContent a() {
            MedalBackContent medalBackContent = new MedalBackContent(null);
            medalBackContent.e = this.g;
            medalBackContent.f8748a = this.e;
            medalBackContent.d = this.f8750a;
            b(medalBackContent);
            a(medalBackContent);
            e(medalBackContent);
            d(medalBackContent);
            if (this.g != null && this.e != null && this.f8750a != null) {
                njw.c("MedalBackContent", "MedalBackContent build mModelType= " + this.g.toString() + "mColorType= " + this.e.toString() + "mContents= " + Arrays.toString(this.f8750a));
            }
            return medalBackContent;
        }

        private void d(MedalBackContent medalBackContent) {
            Bitmap bitmap = this.h;
            if (bitmap == null) {
                switch (AnonymousClass5.f8749a[this.g.ordinal()]) {
                    case 1:
                    case 2:
                    case 3:
                        int i = AnonymousClass5.c[this.e.ordinal()];
                        if (i == 1) {
                            medalBackContent.c = njx.cwt_(this.c, "medal/medal_back_icon_solid_copper.png");
                            break;
                        } else if (i == 2) {
                            medalBackContent.c = njx.cwt_(this.c, "medal/medal_back_icon_solid_silver.png");
                            break;
                        } else if (i == 3) {
                            medalBackContent.c = njx.cwt_(this.c, "medal/medal_back_icon_solid_gold.png");
                            break;
                        }
                        break;
                    case 4:
                    case 5:
                        int i2 = AnonymousClass5.c[this.e.ordinal()];
                        if (i2 == 1) {
                            medalBackContent.c = njx.cwt_(this.c, "medal/medal_back_icon_hollow_hexagon_copper.png");
                            break;
                        } else if (i2 == 2) {
                            medalBackContent.c = njx.cwt_(this.c, "medal/medal_back_icon_hollow_hexagon_silver.png");
                            break;
                        } else if (i2 == 3) {
                            medalBackContent.c = njx.cwt_(this.c, "medal/medal_back_icon_hollow_hexagon_gold.png");
                            break;
                        }
                        break;
                    case 6:
                        int i3 = AnonymousClass5.c[this.e.ordinal()];
                        if (i3 == 1) {
                            medalBackContent.c = njx.cwt_(this.c, "medal/medal_back_icon_hollow_tetragonum_copper.png");
                            break;
                        } else if (i3 == 2) {
                            medalBackContent.c = njx.cwt_(this.c, "medal/medal_back_icon_hollow_tetragonum_silver.png");
                            break;
                        } else if (i3 == 3) {
                            medalBackContent.c = njx.cwt_(this.c, "medal/medal_back_icon_hollow_tetragonum_gold.png");
                            break;
                        }
                        break;
                    case 7:
                        int i4 = AnonymousClass5.c[this.e.ordinal()];
                        if (i4 == 1) {
                            medalBackContent.c = njx.cwt_(this.c, "medal/medal_back_icon_hollow_triangle_copper.png");
                            break;
                        } else if (i4 == 2) {
                            medalBackContent.c = njx.cwt_(this.c, "medal/medal_back_icon_hollow_triangle_silver.png");
                            break;
                        } else if (i4 == 3) {
                            medalBackContent.c = njx.cwt_(this.c, "medal/medal_back_icon_hollow_triangle_gold.png");
                            break;
                        }
                        break;
                }
            }
            medalBackContent.c = bitmap;
        }

        private void e(MedalBackContent medalBackContent) {
            Integer num = this.j;
            if (num == null) {
                int i = AnonymousClass5.c[this.e.ordinal()];
                if (i == 1) {
                    medalBackContent.j = Color.argb(255, 112, 49, 3);
                    return;
                } else if (i == 2) {
                    medalBackContent.j = Color.argb(255, 111, 111, 111);
                    return;
                } else {
                    if (i != 3) {
                        return;
                    }
                    medalBackContent.j = Color.argb(255, 176, 124, 8);
                    return;
                }
            }
            medalBackContent.j = num.intValue();
        }

        private void b(MedalBackContent medalBackContent) {
            if (this.b == null) {
                this.b = new nkf(0.0f, 300.0f);
                switch (AnonymousClass5.f8749a[this.g.ordinal()]) {
                    case 1:
                    case 2:
                    case 3:
                        break;
                    case 4:
                    case 5:
                        this.b.e = 376.0f;
                        break;
                    case 6:
                        this.b.f15343a = -20.0f;
                        this.b.e = 376.0f;
                        break;
                    case 7:
                        this.b.e = 284.0f;
                        break;
                    default:
                        this.b.e = 300.0f;
                        break;
                }
            }
            medalBackContent.b = this.b;
        }

        private void a(MedalBackContent medalBackContent) {
            if (this.d == null) {
                this.d = new nkf(0.0f, 324.0f);
                switch (AnonymousClass5.f8749a[this.g.ordinal()]) {
                    case 1:
                    case 2:
                    case 3:
                        break;
                    case 4:
                    case 5:
                        this.d.e = 390.0f;
                        break;
                    case 6:
                        this.d.f15343a = -20.0f;
                        this.d.e = 390.0f;
                        break;
                    case 7:
                        this.d.e = 316.0f;
                        break;
                    default:
                        this.d.e = 324.0f;
                        break;
                }
            }
            medalBackContent.g = this.d;
        }
    }

    /* renamed from: com.huawei.ucd.medal.MedalBackContent$5, reason: invalid class name */
    static /* synthetic */ class AnonymousClass5 {

        /* renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f8749a;
        static final /* synthetic */ int[] c;

        static {
            int[] iArr = new int[MedalBackType.ModelType.values().length];
            f8749a = iArr;
            try {
                iArr[MedalBackType.ModelType.SOLID_CIRCLE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f8749a[MedalBackType.ModelType.SOLID_FLOWER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f8749a[MedalBackType.ModelType.SOLID_PENTAGON.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f8749a[MedalBackType.ModelType.HOLLOW_CIRCLE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f8749a[MedalBackType.ModelType.HOLLOW_HEXAGON.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f8749a[MedalBackType.ModelType.HOLLOW_TETRAGONUM.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f8749a[MedalBackType.ModelType.HOLLOW_TRIANGLE.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            int[] iArr2 = new int[MedalBackType.ColorType.values().length];
            c = iArr2;
            try {
                iArr2[MedalBackType.ColorType.COPPER.ordinal()] = 1;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                c[MedalBackType.ColorType.SILVER.ordinal()] = 2;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                c[MedalBackType.ColorType.GOLD.ordinal()] = 3;
            } catch (NoSuchFieldError unused10) {
            }
        }
    }
}
