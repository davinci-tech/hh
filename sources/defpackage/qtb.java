package defpackage;

import android.content.Context;
import com.huawei.ui.main.stories.health.interactors.healthdata.BodyReportRecycleItem;

/* loaded from: classes7.dex */
public class qtb {
    public static qta c(Context context, BodyReportRecycleItem bodyReportRecycleItem) {
        if (context == null || bodyReportRecycleItem == null || bodyReportRecycleItem.a() == null) {
            return null;
        }
        switch (AnonymousClass2.f16576a[bodyReportRecycleItem.a().ordinal()]) {
            case 1:
                return new qtf(context, bodyReportRecycleItem);
            case 2:
                return new qtl(context, bodyReportRecycleItem);
            case 3:
                return new qti(context, bodyReportRecycleItem);
            case 4:
                return new qtj(context, bodyReportRecycleItem);
            case 5:
            case 6:
                return new qtg(context, bodyReportRecycleItem);
            case 7:
                return new qto(context, bodyReportRecycleItem);
            default:
                return null;
        }
    }

    /* renamed from: qtb$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {

        /* renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f16576a;

        static {
            int[] iArr = new int[BodyReportRecycleItem.BodyReportType.values().length];
            f16576a = iArr;
            try {
                iArr[BodyReportRecycleItem.BodyReportType.BODY_ANALYSIS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f16576a[BodyReportRecycleItem.BodyReportType.PEER_COMPARISON.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f16576a[BodyReportRecycleItem.BodyReportType.MUSCLE_ANALYSIS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f16576a[BodyReportRecycleItem.BodyReportType.FAT_ANALYSIS.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f16576a[BodyReportRecycleItem.BodyReportType.OTHER_INDICATORS.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f16576a[BodyReportRecycleItem.BodyReportType.IMPORTANT_INDICATORS.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f16576a[BodyReportRecycleItem.BodyReportType.PHYSIQUE_PREDICTION.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }
}
