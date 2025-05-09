package okhttp3.internal.http;

import com.huawei.hms.network.embedded.y;
import kotlin.Metadata;
import okhttp3.Interceptor;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lokhttp3/internal/http/CallServerInterceptor;", "Lokhttp3/Interceptor;", "forWebSocket", "", "(Z)V", "intercept", "Lokhttp3/Response;", "chain", "Lokhttp3/Interceptor$Chain;", "shouldIgnoreAndWaitForRealResponse", "code", "", y.b.j}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes7.dex */
public final class CallServerInterceptor implements Interceptor {
    private final boolean forWebSocket;

    private final boolean shouldIgnoreAndWaitForRealResponse(int code) {
        return code == 100 || (102 <= code && code < 200);
    }

    public CallServerInterceptor(boolean z) {
        this.forWebSocket = z;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00e0 A[Catch: IOException -> 0x01cb, TryCatch #3 {IOException -> 0x01cb, blocks: (B:79:0x00a9, B:81:0x00b2, B:22:0x00b6, B:24:0x00e0, B:26:0x00e9, B:27:0x00ec, B:28:0x0110, B:32:0x011b, B:34:0x011f, B:35:0x012a, B:37:0x0130, B:38:0x013c, B:39:0x0169, B:41:0x0177, B:49:0x018d, B:51:0x0193, B:54:0x01a0, B:56:0x01b7, B:57:0x01bf, B:58:0x01c9, B:66:0x0182, B:67:0x0135, B:68:0x0124, B:69:0x0141, B:71:0x0145, B:72:0x0151, B:74:0x0159, B:75:0x0165, B:76:0x015e, B:77:0x014a), top: B:78:0x00a9 }] */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0193 A[Catch: IOException -> 0x01cb, TryCatch #3 {IOException -> 0x01cb, blocks: (B:79:0x00a9, B:81:0x00b2, B:22:0x00b6, B:24:0x00e0, B:26:0x00e9, B:27:0x00ec, B:28:0x0110, B:32:0x011b, B:34:0x011f, B:35:0x012a, B:37:0x0130, B:38:0x013c, B:39:0x0169, B:41:0x0177, B:49:0x018d, B:51:0x0193, B:54:0x01a0, B:56:0x01b7, B:57:0x01bf, B:58:0x01c9, B:66:0x0182, B:67:0x0135, B:68:0x0124, B:69:0x0141, B:71:0x0145, B:72:0x0151, B:74:0x0159, B:75:0x0165, B:76:0x015e, B:77:0x014a), top: B:78:0x00a9 }] */
    /* JADX WARN: Removed duplicated region for block: B:54:0x01a0 A[Catch: IOException -> 0x01cb, TryCatch #3 {IOException -> 0x01cb, blocks: (B:79:0x00a9, B:81:0x00b2, B:22:0x00b6, B:24:0x00e0, B:26:0x00e9, B:27:0x00ec, B:28:0x0110, B:32:0x011b, B:34:0x011f, B:35:0x012a, B:37:0x0130, B:38:0x013c, B:39:0x0169, B:41:0x0177, B:49:0x018d, B:51:0x0193, B:54:0x01a0, B:56:0x01b7, B:57:0x01bf, B:58:0x01c9, B:66:0x0182, B:67:0x0135, B:68:0x0124, B:69:0x0141, B:71:0x0145, B:72:0x0151, B:74:0x0159, B:75:0x0165, B:76:0x015e, B:77:0x014a), top: B:78:0x00a9 }] */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0198  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0145 A[Catch: IOException -> 0x01cb, TryCatch #3 {IOException -> 0x01cb, blocks: (B:79:0x00a9, B:81:0x00b2, B:22:0x00b6, B:24:0x00e0, B:26:0x00e9, B:27:0x00ec, B:28:0x0110, B:32:0x011b, B:34:0x011f, B:35:0x012a, B:37:0x0130, B:38:0x013c, B:39:0x0169, B:41:0x0177, B:49:0x018d, B:51:0x0193, B:54:0x01a0, B:56:0x01b7, B:57:0x01bf, B:58:0x01c9, B:66:0x0182, B:67:0x0135, B:68:0x0124, B:69:0x0141, B:71:0x0145, B:72:0x0151, B:74:0x0159, B:75:0x0165, B:76:0x015e, B:77:0x014a), top: B:78:0x00a9 }] */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0159 A[Catch: IOException -> 0x01cb, TryCatch #3 {IOException -> 0x01cb, blocks: (B:79:0x00a9, B:81:0x00b2, B:22:0x00b6, B:24:0x00e0, B:26:0x00e9, B:27:0x00ec, B:28:0x0110, B:32:0x011b, B:34:0x011f, B:35:0x012a, B:37:0x0130, B:38:0x013c, B:39:0x0169, B:41:0x0177, B:49:0x018d, B:51:0x0193, B:54:0x01a0, B:56:0x01b7, B:57:0x01bf, B:58:0x01c9, B:66:0x0182, B:67:0x0135, B:68:0x0124, B:69:0x0141, B:71:0x0145, B:72:0x0151, B:74:0x0159, B:75:0x0165, B:76:0x015e, B:77:0x014a), top: B:78:0x00a9 }] */
    /* JADX WARN: Removed duplicated region for block: B:76:0x015e A[Catch: IOException -> 0x01cb, TryCatch #3 {IOException -> 0x01cb, blocks: (B:79:0x00a9, B:81:0x00b2, B:22:0x00b6, B:24:0x00e0, B:26:0x00e9, B:27:0x00ec, B:28:0x0110, B:32:0x011b, B:34:0x011f, B:35:0x012a, B:37:0x0130, B:38:0x013c, B:39:0x0169, B:41:0x0177, B:49:0x018d, B:51:0x0193, B:54:0x01a0, B:56:0x01b7, B:57:0x01bf, B:58:0x01c9, B:66:0x0182, B:67:0x0135, B:68:0x0124, B:69:0x0141, B:71:0x0145, B:72:0x0151, B:74:0x0159, B:75:0x0165, B:76:0x015e, B:77:0x014a), top: B:78:0x00a9 }] */
    /* JADX WARN: Removed duplicated region for block: B:77:0x014a A[Catch: IOException -> 0x01cb, TryCatch #3 {IOException -> 0x01cb, blocks: (B:79:0x00a9, B:81:0x00b2, B:22:0x00b6, B:24:0x00e0, B:26:0x00e9, B:27:0x00ec, B:28:0x0110, B:32:0x011b, B:34:0x011f, B:35:0x012a, B:37:0x0130, B:38:0x013c, B:39:0x0169, B:41:0x0177, B:49:0x018d, B:51:0x0193, B:54:0x01a0, B:56:0x01b7, B:57:0x01bf, B:58:0x01c9, B:66:0x0182, B:67:0x0135, B:68:0x0124, B:69:0x0141, B:71:0x0145, B:72:0x0151, B:74:0x0159, B:75:0x0165, B:76:0x015e, B:77:0x014a), top: B:78:0x00a9 }] */
    /* JADX WARN: Removed duplicated region for block: B:78:0x00a9 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r2v1, types: [java.lang.Object, okhttp3.internal.connection.Exchange] */
    /* JADX WARN: Type inference failed for: r9v0 */
    /* JADX WARN: Type inference failed for: r9v1 */
    /* JADX WARN: Type inference failed for: r9v10, types: [okhttp3.Response] */
    /* JADX WARN: Type inference failed for: r9v14, types: [boolean] */
    /* JADX WARN: Type inference failed for: r9v15 */
    /* JADX WARN: Type inference failed for: r9v16 */
    /* JADX WARN: Type inference failed for: r9v17 */
    /* JADX WARN: Type inference failed for: r9v22, types: [okhttp3.Response$Builder] */
    /* JADX WARN: Type inference failed for: r9v24 */
    /* JADX WARN: Type inference failed for: r9v25 */
    /* JADX WARN: Type inference failed for: r9v26 */
    /* JADX WARN: Type inference failed for: r9v27 */
    /* JADX WARN: Type inference failed for: r9v30 */
    /* JADX WARN: Type inference failed for: r9v31 */
    @Override // okhttp3.Interceptor
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public okhttp3.Response intercept(okhttp3.Interceptor.Chain r14) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 474
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.http.CallServerInterceptor.intercept(okhttp3.Interceptor$Chain):okhttp3.Response");
    }
}
