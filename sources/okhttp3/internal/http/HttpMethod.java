package okhttp3.internal.http;

import com.huawei.hms.network.embedded.y;
import com.huawei.profile.coordinator.ProfileRequestConstants;
import defpackage.uhy;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u000e\u0010\b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\t\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u0010\u0010\n\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u000b"}, d2 = {"Lokhttp3/internal/http/HttpMethod;", "", "()V", "invalidatesCache", "", "method", "", "permitsRequestBody", "redirectsToGet", "redirectsWithBody", "requiresRequestBody", y.b.j}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class HttpMethod {
    public static final HttpMethod INSTANCE = new HttpMethod();

    private HttpMethod() {
    }

    public final boolean invalidatesCache(String method) {
        uhy.e((Object) method, "");
        return uhy.e((Object) method, (Object) "POST") || uhy.e((Object) method, (Object) "PATCH") || uhy.e((Object) method, (Object) ProfileRequestConstants.PUT_TYPE) || uhy.e((Object) method, (Object) ProfileRequestConstants.DELETE_TYPE) || uhy.e((Object) method, (Object) "MOVE");
    }

    @JvmStatic
    public static final boolean requiresRequestBody(String method) {
        uhy.e((Object) method, "");
        return uhy.e((Object) method, (Object) "POST") || uhy.e((Object) method, (Object) ProfileRequestConstants.PUT_TYPE) || uhy.e((Object) method, (Object) "PATCH") || uhy.e((Object) method, (Object) "PROPPATCH") || uhy.e((Object) method, (Object) "REPORT");
    }

    @JvmStatic
    public static final boolean permitsRequestBody(String method) {
        uhy.e((Object) method, "");
        return (uhy.e((Object) method, (Object) "GET") || uhy.e((Object) method, (Object) "HEAD")) ? false : true;
    }

    public final boolean redirectsWithBody(String method) {
        uhy.e((Object) method, "");
        return uhy.e((Object) method, (Object) "PROPFIND");
    }

    public final boolean redirectsToGet(String method) {
        uhy.e((Object) method, "");
        return !uhy.e((Object) method, (Object) "PROPFIND");
    }
}
