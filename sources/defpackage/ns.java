package defpackage;

import com.careforeyou.library.enums.Protocal_Type;
import com.careforeyou.library.protocal.iStraightFrame;

/* loaded from: classes2.dex */
public class ns {
    private static volatile iStraightFrame c;

    /* renamed from: ns$4, reason: invalid class name */
    static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] e;

        static {
            int[] iArr = new int[Protocal_Type.values().length];
            e = iArr;
            try {
                iArr[Protocal_Type.OKOK.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                e[Protocal_Type.OKOKCloud.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                e[Protocal_Type.OKOKCloudV3.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                e[Protocal_Type.OKOKCloudV4.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public static iStraightFrame d(Protocal_Type protocal_Type) {
        int i = AnonymousClass4.e[protocal_Type.ordinal()];
        if (i != 1) {
            if (i != 2) {
                if (i == 3 || i == 4) {
                    if (c == null) {
                        c = new nw();
                    } else if (!(c instanceof nw)) {
                        c = new nw();
                    }
                }
            } else if (c == null) {
                c = new nv();
            } else if (!(c instanceof nv)) {
                c = new nv();
            }
        } else if (c == null) {
            c = new nx();
        } else if (!(c instanceof nx)) {
            c = new nx();
        }
        return c;
    }
}
