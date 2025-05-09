package okhttp3.internal.platform.android;

import android.util.Log;
import com.huawei.health.suggestion.model.FitRunPlayAudio;
import com.huawei.hms.network.embedded.k;
import com.huawei.hms.network.embedded.y;
import defpackage.ufs;
import defpackage.uhy;
import defpackage.ujy;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.logging.Level;
import java.util.logging.Logger;
import kotlin.Metadata;
import okhttp3.OkHttpClient;
import okhttp3.internal.concurrent.TaskRunner;
import okhttp3.internal.http2.Http2;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u0003\n\u0002\b\u0007\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J/\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\n2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0000¢\u0006\u0002\b\u0012J\u0006\u0010\u0013\u001a\u00020\fJ\u0018\u0010\u0014\u001a\u00020\f2\u0006\u0010\u0015\u001a\u00020\n2\u0006\u0010\u0016\u001a\u00020\nH\u0002J\u0010\u0010\u0017\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\nH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n0\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lokhttp3/internal/platform/android/AndroidLog;", "", "()V", "MAX_LOG_LENGTH", "", "configuredLoggers", "Ljava/util/concurrent/CopyOnWriteArraySet;", "Ljava/util/logging/Logger;", "knownLoggers", "", "", "androidLog", "", "loggerName", "logLevel", "message", FitRunPlayAudio.PLAY_TYPE_T, "", "androidLog$okhttp", k.g, "enableLogging", "logger", "tag", "loggerTag", y.b.j}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes7.dex */
public final class AndroidLog {
    private static final int MAX_LOG_LENGTH = 4000;
    private static final Map<String, String> knownLoggers;
    public static final AndroidLog INSTANCE = new AndroidLog();
    private static final CopyOnWriteArraySet<Logger> configuredLoggers = new CopyOnWriteArraySet<>();

    private AndroidLog() {
    }

    static {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        Package r1 = OkHttpClient.class.getPackage();
        String name = r1 != null ? r1.getName() : null;
        if (name != null) {
            linkedHashMap.put(name, "OkHttp");
        }
        LinkedHashMap linkedHashMap2 = linkedHashMap;
        String name2 = OkHttpClient.class.getName();
        uhy.a(name2, "");
        linkedHashMap2.put(name2, "okhttp.OkHttpClient");
        String name3 = Http2.class.getName();
        uhy.a(name3, "");
        linkedHashMap2.put(name3, "okhttp.Http2");
        String name4 = TaskRunner.class.getName();
        uhy.a(name4, "");
        linkedHashMap2.put(name4, "okhttp.TaskRunner");
        linkedHashMap2.put("okhttp3.mockwebserver.MockWebServer", "okhttp.MockWebServer");
        knownLoggers = ufs.e(linkedHashMap2);
    }

    public final void androidLog$okhttp(String loggerName, int logLevel, String message, Throwable t) {
        int min;
        uhy.e((Object) loggerName, "");
        uhy.e((Object) message, "");
        String loggerTag = loggerTag(loggerName);
        if (Log.isLoggable(loggerTag, logLevel)) {
            if (t != null) {
                message = message + '\n' + Log.getStackTraceString(t);
            }
            int length = message.length();
            int i = 0;
            while (i < length) {
                int a2 = ujy.a((CharSequence) message, '\n', i, false, 4, (Object) null);
                if (a2 == -1) {
                    a2 = length;
                }
                while (true) {
                    min = Math.min(a2, i + 4000);
                    String substring = message.substring(i, min);
                    uhy.a(substring, "");
                    Log.println(logLevel, loggerTag, substring);
                    if (min >= a2) {
                        break;
                    } else {
                        i = min;
                    }
                }
                i = min + 1;
            }
        }
    }

    private final String loggerTag(String loggerName) {
        String str = knownLoggers.get(loggerName);
        return str == null ? ujy.a(loggerName, 23) : str;
    }

    public final void enable() {
        for (Map.Entry<String, String> entry : knownLoggers.entrySet()) {
            enableLogging(entry.getKey(), entry.getValue());
        }
    }

    private final void enableLogging(String logger, String tag) {
        Level level;
        Logger logger2 = Logger.getLogger(logger);
        if (configuredLoggers.add(logger2)) {
            logger2.setUseParentHandlers(false);
            if (Log.isLoggable(tag, 3)) {
                level = Level.FINE;
            } else {
                level = Log.isLoggable(tag, 4) ? Level.INFO : Level.WARNING;
            }
            logger2.setLevel(level);
            logger2.addHandler(AndroidLogHandler.INSTANCE);
        }
    }
}
