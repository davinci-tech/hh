package androidx.core.graphics.drawable;

import android.graphics.Bitmap;
import android.graphics.drawable.Icon;
import android.net.Uri;
import defpackage.uhy;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0000\u001a\r\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0087\b\u001a\r\u0010\u0003\u001a\u00020\u0001*\u00020\u0002H\u0087\b\u001a\r\u0010\u0003\u001a\u00020\u0001*\u00020\u0004H\u0087\b\u001a\r\u0010\u0003\u001a\u00020\u0001*\u00020\u0005H\u0087\b¨\u0006\u0006"}, d2 = {"toAdaptiveIcon", "Landroid/graphics/drawable/Icon;", "Landroid/graphics/Bitmap;", "toIcon", "Landroid/net/Uri;", "", "core-ktx_release"}, k = 2, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes8.dex */
public final class IconKt {
    public static final Icon toAdaptiveIcon(Bitmap bitmap) {
        uhy.e((Object) bitmap, "");
        Icon createWithAdaptiveBitmap = Icon.createWithAdaptiveBitmap(bitmap);
        uhy.a(createWithAdaptiveBitmap, "");
        return createWithAdaptiveBitmap;
    }

    public static final Icon toIcon(Bitmap bitmap) {
        uhy.e((Object) bitmap, "");
        Icon createWithBitmap = Icon.createWithBitmap(bitmap);
        uhy.a(createWithBitmap, "");
        return createWithBitmap;
    }

    public static final Icon toIcon(Uri uri) {
        uhy.e((Object) uri, "");
        Icon createWithContentUri = Icon.createWithContentUri(uri);
        uhy.a(createWithContentUri, "");
        return createWithContentUri;
    }

    public static final Icon toIcon(byte[] bArr) {
        uhy.e((Object) bArr, "");
        Icon createWithData = Icon.createWithData(bArr, 0, bArr.length);
        uhy.a(createWithData, "");
        return createWithData;
    }
}
