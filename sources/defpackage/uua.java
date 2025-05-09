package defpackage;

import android.net.Uri;
import androidx.webkit.ProxyConfig;
import com.huawei.agconnect.apms.instrument.URLConnectionInstrumentation;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import net.openid.appauth.connectivity.ConnectionBuilder;

/* loaded from: classes7.dex */
public final class uua implements ConnectionBuilder {
    public static final uua e = new uua();

    /* renamed from: a, reason: collision with root package name */
    private static final int f17559a = (int) TimeUnit.SECONDS.toMillis(15);
    private static final int b = (int) TimeUnit.SECONDS.toMillis(10);

    private uua() {
    }

    @Override // net.openid.appauth.connectivity.ConnectionBuilder
    public HttpURLConnection openConnection(Uri uri) throws IOException {
        utq.d(uri, "url must not be null");
        utq.e(ProxyConfig.MATCH_HTTPS.equals(uri.getScheme()), "only https connections are permitted");
        HttpURLConnection httpURLConnection = (HttpURLConnection) URLConnectionInstrumentation.openConnection(new URL(uri.toString()).openConnection());
        httpURLConnection.setConnectTimeout(f17559a);
        httpURLConnection.setReadTimeout(b);
        httpURLConnection.setInstanceFollowRedirects(false);
        return httpURLConnection;
    }
}
