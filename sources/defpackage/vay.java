package defpackage;

import com.huawei.health.suggestion.model.FitRunPlayAudio;
import java.util.concurrent.TimeUnit;
import org.eclipse.californium.elements.config.DocumentedDefinition;

/* loaded from: classes7.dex */
public class vay extends DocumentedDefinition<Long> {
    public vay(String str, String str2) {
        super(str, str2, Long.class, null);
    }

    public vay(String str, String str2, long j, TimeUnit timeUnit) {
        super(str, str2, Long.class, Long.valueOf(TimeUnit.NANOSECONDS.convert(j, timeUnit)));
    }

    @Override // org.eclipse.californium.elements.config.DocumentedDefinition
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public String writeValue(Long l) {
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        if (l.longValue() != 0) {
            timeUnit = TimeUnit.NANOSECONDS;
            if (l.longValue() % 1000 == 0) {
                timeUnit = TimeUnit.MICROSECONDS;
                l = Long.valueOf(l.longValue() / 1000);
                if (l.longValue() % 1000 == 0) {
                    timeUnit = TimeUnit.MILLISECONDS;
                    l = Long.valueOf(l.longValue() / 1000);
                    if (l.longValue() % 1000 == 0) {
                        timeUnit = TimeUnit.SECONDS;
                        l = Long.valueOf(l.longValue() / 1000);
                        if (l.longValue() % 60 == 0) {
                            timeUnit = TimeUnit.MINUTES;
                            l = Long.valueOf(l.longValue() / 60);
                            if (l.longValue() % 60 == 0) {
                                timeUnit = TimeUnit.HOURS;
                                l = Long.valueOf(l.longValue() / 60);
                                if (l.longValue() % 24 == 0) {
                                    timeUnit = TimeUnit.DAYS;
                                    l = Long.valueOf(l.longValue() / 24);
                                }
                            }
                        }
                    }
                }
            }
        }
        return l + "[" + e(timeUnit) + "]";
    }

    @Override // org.eclipse.californium.elements.config.DocumentedDefinition
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public Long checkValue(Long l) throws vbh {
        if (l == null || l.longValue() >= 0) {
            return l;
        }
        throw new vbh("Time " + l + " must be not less than 0!");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.eclipse.californium.elements.config.DocumentedDefinition
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public Long parseValue(String str) throws vbh {
        return d(str);
    }

    public static Long d(String str) throws vbh {
        TimeUnit a2;
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        int indexOf = str.indexOf(91);
        if (indexOf >= 0) {
            int indexOf2 = str.indexOf(93);
            if (indexOf < indexOf2) {
                String trim = str.substring(0, indexOf).trim();
                String trim2 = str.substring(indexOf + 1, indexOf2).trim();
                timeUnit = a(trim2);
                if (timeUnit == null) {
                    throw new vbh(trim2 + " unknown unit!");
                }
                str = trim;
            } else {
                throw new vbh(str + " doesn't match value[unit]!");
            }
        } else if (!Character.isDigit(str.charAt(str.length() - 1)) && (a2 = a(str)) != null) {
            str = str.substring(0, str.length() - e(a2).length()).trim();
            timeUnit = a2;
        }
        return Long.valueOf(TimeUnit.NANOSECONDS.convert(Long.parseLong(str), timeUnit));
    }

    /* renamed from: vay$3, reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] b;

        static {
            int[] iArr = new int[TimeUnit.values().length];
            b = iArr;
            try {
                iArr[TimeUnit.NANOSECONDS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                b[TimeUnit.MICROSECONDS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                b[TimeUnit.MILLISECONDS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                b[TimeUnit.SECONDS.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                b[TimeUnit.MINUTES.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                b[TimeUnit.HOURS.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                b[TimeUnit.DAYS.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    public static String e(TimeUnit timeUnit) {
        switch (AnonymousClass3.b[timeUnit.ordinal()]) {
            case 1:
                return "ns";
            case 2:
                return "ys";
            case 3:
                return "ms";
            case 4:
                return "s";
            case 5:
                return "min";
            case 6:
                return "h";
            case 7:
                return FitRunPlayAudio.PLAY_TYPE_D;
            default:
                return "";
        }
    }

    public static TimeUnit a(String str) {
        String str2 = "";
        TimeUnit timeUnit = null;
        for (TimeUnit timeUnit2 : TimeUnit.values()) {
            String e = e(timeUnit2);
            if (!e.isEmpty()) {
                if (e.equals(str)) {
                    return timeUnit2;
                }
                if (str.endsWith(e) && e.length() > str2.length()) {
                    timeUnit = timeUnit2;
                    str2 = e;
                }
            }
        }
        return timeUnit;
    }

    @Override // org.eclipse.californium.elements.config.DocumentedDefinition
    public String getTypeName() {
        return "Time";
    }
}
