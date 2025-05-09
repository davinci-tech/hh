package kotlin;

import com.huawei.operation.utils.Constants;
import defpackage.enumEntries;
import kotlin.enums.EnumEntries;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lkotlin/DeprecationLevel;", "", "(Ljava/lang/String;I)V", "WARNING", Constants.LOG_ERROR, "HIDDEN", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes.dex */
public final class DeprecationLevel {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ DeprecationLevel[] $VALUES;
    public static final DeprecationLevel WARNING = new DeprecationLevel("WARNING", 0);
    public static final DeprecationLevel ERROR = new DeprecationLevel(Constants.LOG_ERROR, 1);
    public static final DeprecationLevel HIDDEN = new DeprecationLevel("HIDDEN", 2);

    private DeprecationLevel(String str, int i) {
    }

    static {
        DeprecationLevel[] $values = $values();
        $VALUES = $values;
        $ENTRIES = enumEntries.c($values);
    }

    public static DeprecationLevel[] values() {
        return (DeprecationLevel[]) $VALUES.clone();
    }

    public static DeprecationLevel valueOf(String str) {
        return (DeprecationLevel) Enum.valueOf(DeprecationLevel.class, str);
    }

    public static EnumEntries<DeprecationLevel> getEntries() {
        return $ENTRIES;
    }

    private static final /* synthetic */ DeprecationLevel[] $values() {
        return new DeprecationLevel[]{WARNING, ERROR, HIDDEN};
    }
}
