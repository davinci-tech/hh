package defpackage;

import androidx.core.location.LocationRequestCompat;
import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\u001a\u0010\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u0007H\u0000\u001a\u0010\u0010\u0010\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u0007H\u0000\"\u0016\u0010\u0000\u001a\u00020\u00018\u0002X\u0083\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0002\u0010\u0003\"\u0016\u0010\u0004\u001a\u00020\u00018\u0002X\u0083\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0003\"\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\b\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\t\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\n\u001a\u00020\u000bX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\f\u001a\u00020\u000bX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\r\u001a\u00020\u000bX\u0082T¢\u0006\u0002\n\u0000*\u001e\b\u0002\u0010\u0012\u001a\u0004\b\u0000\u0010\u0013\"\b\u0012\u0004\u0012\u0002H\u00130\u00142\b\u0012\u0004\u0012\u0002H\u00130\u0014¨\u0006\u0015"}, d2 = {"CLOSED_EMPTY", "Lkotlinx/coroutines/internal/Symbol;", "getCLOSED_EMPTY$annotations", "()V", "DISPOSED_TASK", "getDISPOSED_TASK$annotations", "MAX_DELAY_NS", "", "MAX_MS", "MS_TO_NS", "SCHEDULE_COMPLETED", "", "SCHEDULE_DISPOSED", "SCHEDULE_OK", "delayNanosToMillis", "timeNanos", "delayToNanos", "timeMillis", "Queue", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/coroutines/internal/LockFreeTaskQueueCore;", "kotlinx-coroutines-core"}, k = 2, mv = {1, 6, 0}, xi = 48)
/* renamed from: ulx, reason: from Kotlin metadata */
/* loaded from: classes.dex */
public final class CLOSED_EMPTY {

    /* renamed from: a, reason: collision with root package name */
    private static final upu f17461a = new upu("REMOVED_TASK");
    private static final upu e = new upu("CLOSED_EMPTY");

    public static final long b(long j) {
        if (j <= 0) {
            return 0L;
        }
        return j >= 9223372036854L ? LocationRequestCompat.PASSIVE_INTERVAL : 1000000 * j;
    }
}
