package defpackage;

import com.huawei.health.trusport_ee.Intensity;
import com.huawei.health.trusport_ee.Sex;
import com.huawei.health.trusport_ee.SwimStyle;
import com.huawei.health.trusport_ee_jni.TruSport_EE_profile_t;
import com.huawei.health.trusport_ee_jni.TruSport_EE_result_t;
import com.huawei.health.trusport_ee_jni.TruSport_EE_sample_t;
import java.math.BigInteger;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000X\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0000\u001a\f\u0010\u0003\u001a\u00020\u0004*\u00020\u0005H\u0000\u001a\f\u0010\u0006\u001a\u00020\u0007*\u00020\bH\u0000\u001a\u0014\u0010\t\u001a\n \u000b*\u0004\u0018\u00010\n0\n*\u00020\fH\u0000\u001a\u000e\u0010\r\u001a\u0004\u0018\u00010\u0002*\u00020\u0001H\u0000\u001a\u000e\u0010\u000e\u001a\u0004\u0018\u00010\u000f*\u00020\u0010H\u0000\u001a\u0014\u0010\u0011\u001a\n \u000b*\u0004\u0018\u00010\b0\b*\u00020\u0007H\u0000\u001a\u000e\u0010\u0012\u001a\u0004\u0018\u00010\u0013*\u00020\u0014H\u0000\u001a\u0014\u0010\u0015\u001a\n \u000b*\u0004\u0018\u00010\u00160\u0016*\u00020\u0017H\u0002\u001a\f\u0010\u0018\u001a\u00020\u0019*\u00020\u001aH\u0000¨\u0006\u001b"}, d2 = {"toProfile", "Lcom/huawei/health/trusport_ee/Profile;", "Lcom/huawei/health/trusport_ee_jni/TruSport_EE_profile_t;", "toResult", "Lcom/huawei/health/trusport_ee/Result;", "Lcom/huawei/health/trusport_ee_jni/TruSport_EE_result_t;", "toSex", "Lcom/huawei/health/trusport_ee/Sex;", "Lcom/huawei/health/trusport_ee_jni/TruSport_EE_sex_t;", "toTruSport_EE_Intensity_t", "Lcom/huawei/health/trusport_ee_jni/TruSport_EE_Intensity_t;", "kotlin.jvm.PlatformType", "Lcom/huawei/health/trusport_ee/Intensity;", "toTruSport_EE_profile_t", "toTruSport_EE_sample_t", "Lcom/huawei/health/trusport_ee_jni/TruSport_EE_sample_t;", "Lcom/huawei/health/trusport_ee/Sample;", "toTruSport_EE_sex_t", "toTruSport_EE_swim_sample_t", "Lcom/huawei/health/trusport_ee_jni/TruSport_EE_swim_sample_t;", "Lcom/huawei/health/trusport_ee/SwimSample;", "toTruSport_EE_swim_style_t", "Lcom/huawei/health/trusport_ee_jni/TruSport_EE_swim_style_t;", "Lcom/huawei/health/trusport_ee/SwimStyle;", "toVersion", "Lcom/huawei/health/trusport_ee/Version;", "Lcom/huawei/health/trusport_ee_jni/TruSport_EE_version_t;", "trusport_ee_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* renamed from: gln, reason: from Kotlin metadata */
/* loaded from: classes4.dex */
public final class toProfile {
    public static final gls c(Sex sex) {
        uhy.e((Object) sex, "");
        int i = d.f12852a[sex.ordinal()];
        if (i == 1) {
            return gls.f12855a;
        }
        if (i == 2) {
            return gls.d;
        }
        return gls.c;
    }

    public static final TruSport_EE_profile_t e(glb glbVar) {
        uhy.e((Object) glbVar, "");
        TruSport_EE_profile_t truSport_EE_profile_t = new TruSport_EE_profile_t();
        truSport_EE_profile_t.a((short) glbVar.getE());
        truSport_EE_profile_t.c(glbVar.getB());
        truSport_EE_profile_t.b(glbVar.getD());
        truSport_EE_profile_t.e(c(glbVar.getC()));
        return truSport_EE_profile_t;
    }

    public static final glj e(TruSport_EE_result_t truSport_EE_result_t) {
        uhy.e((Object) truSport_EE_result_t, "");
        return new glj(truSport_EE_result_t.a(), truSport_EE_result_t.c(), truSport_EE_result_t.b());
    }

    public static final TruSport_EE_sample_t b(glf glfVar) {
        uhy.e((Object) glfVar, "");
        TruSport_EE_sample_t truSport_EE_sample_t = new TruSport_EE_sample_t();
        glt.e(truSport_EE_sample_t);
        BigInteger valueOf = BigInteger.valueOf(glfVar.getF12848a());
        uhy.a(valueOf, "");
        truSport_EE_sample_t.b(valueOf);
        truSport_EE_sample_t.d(glfVar.getD());
        truSport_EE_sample_t.e(glfVar.getC());
        truSport_EE_sample_t.b((short) glfVar.getB());
        return truSport_EE_sample_t;
    }

    public static final glo b(Intensity intensity) {
        uhy.e((Object) intensity, "");
        int i = d.b[intensity.ordinal()];
        if (i == 1) {
            return glo.c;
        }
        if (i == 2) {
            return glo.f12853a;
        }
        return glo.b;
    }

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    /* renamed from: gln$d */
    public final /* synthetic */ class d {

        /* renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f12852a;
        public static final /* synthetic */ int[] b;
        public static final /* synthetic */ int[] c;

        static {
            int[] iArr = new int[Sex.values().length];
            try {
                iArr[Sex.MALE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[Sex.FEMALE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            f12852a = iArr;
            int[] iArr2 = new int[SwimStyle.values().length];
            try {
                iArr2[SwimStyle.FREESTYLE.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr2[SwimStyle.BREASTSTROKE.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr2[SwimStyle.BACKSTROKE.ordinal()] = 3;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr2[SwimStyle.BUTTERFLY.ordinal()] = 4;
            } catch (NoSuchFieldError unused6) {
            }
            c = iArr2;
            int[] iArr3 = new int[Intensity.values().length];
            try {
                iArr3[Intensity.LOW.ordinal()] = 1;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                iArr3[Intensity.MEDIUM.ordinal()] = 2;
            } catch (NoSuchFieldError unused8) {
            }
            b = iArr3;
        }
    }
}
