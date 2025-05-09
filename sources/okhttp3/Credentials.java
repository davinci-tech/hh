package okhttp3;

import com.huawei.hms.network.embedded.y;
import com.huawei.watchface.videoedit.gles.Constant;
import defpackage.uhy;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import okio.ByteString;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\"\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\bH\u0007¨\u0006\t"}, d2 = {"Lokhttp3/Credentials;", "", "()V", Constant.BASIC, "", "username", "password", "charset", "Ljava/nio/charset/Charset;", y.b.j}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes7.dex */
public final class Credentials {
    public static final Credentials INSTANCE = new Credentials();

    private Credentials() {
    }

    public static /* synthetic */ String basic$default(String str, String str2, Charset charset, int i, Object obj) {
        if ((i & 4) != 0) {
            charset = StandardCharsets.ISO_8859_1;
            uhy.a(charset, "");
        }
        return basic(str, str2, charset);
    }

    @JvmStatic
    public static final String basic(String username, String password, Charset charset) {
        uhy.e((Object) username, "");
        uhy.e((Object) password, "");
        uhy.e((Object) charset, "");
        return "Basic " + ByteString.INSTANCE.encodeString(username + ':' + password, charset).base64();
    }

    @JvmStatic
    public static final String basic(String str, String str2) {
        uhy.e((Object) str, "");
        uhy.e((Object) str2, "");
        return basic$default(str, str2, null, 4, null);
    }
}
