package kotlin.reflect;

import com.huawei.phoneservice.faq.base.constants.FaqConstants;
import defpackage.enumEntries;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0087\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lkotlin/reflect/KVariance;", "", "(Ljava/lang/String;I)V", "INVARIANT", FaqConstants.OPEN_TYPE_IN, FaqConstants.OPEN_TYPE_OUT, "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes10.dex */
public final class KVariance {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ KVariance[] $VALUES;
    public static final KVariance INVARIANT = new KVariance("INVARIANT", 0);
    public static final KVariance IN = new KVariance(FaqConstants.OPEN_TYPE_IN, 1);
    public static final KVariance OUT = new KVariance(FaqConstants.OPEN_TYPE_OUT, 2);

    private KVariance(String str, int i) {
    }

    static {
        KVariance[] $values = $values();
        $VALUES = $values;
        $ENTRIES = enumEntries.c($values);
    }

    public static KVariance[] values() {
        return (KVariance[]) $VALUES.clone();
    }

    public static KVariance valueOf(String str) {
        return (KVariance) Enum.valueOf(KVariance.class, str);
    }

    public static EnumEntries<KVariance> getEntries() {
        return $ENTRIES;
    }

    private static final /* synthetic */ KVariance[] $values() {
        return new KVariance[]{INVARIANT, IN, OUT};
    }
}
