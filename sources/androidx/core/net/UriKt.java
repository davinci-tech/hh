package androidx.core.net;

import android.net.Uri;
import defpackage.uhy;
import java.io.File;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001a\r\u0010\u0003\u001a\u00020\u0002*\u00020\u0001H\u0086\b\u001a\r\u0010\u0003\u001a\u00020\u0002*\u00020\u0004H\u0086\b¨\u0006\u0005"}, d2 = {"toFile", "Ljava/io/File;", "Landroid/net/Uri;", "toUri", "", "core-ktx_release"}, k = 2, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes8.dex */
public final class UriKt {
    public static final Uri toUri(String str) {
        uhy.e((Object) str, "");
        Uri parse = Uri.parse(str);
        uhy.a(parse, "");
        return parse;
    }

    public static final Uri toUri(File file) {
        uhy.e((Object) file, "");
        Uri fromFile = Uri.fromFile(file);
        uhy.a(fromFile, "");
        return fromFile;
    }

    public static final File toFile(Uri uri) {
        uhy.e((Object) uri, "");
        if (!uhy.e((Object) uri.getScheme(), (Object) "file")) {
            throw new IllegalArgumentException(("Uri lacks 'file' scheme: " + uri).toString());
        }
        String path = uri.getPath();
        if (path != null) {
            return new File(path);
        }
        throw new IllegalArgumentException(("Uri path is null: " + uri).toString());
    }
}
