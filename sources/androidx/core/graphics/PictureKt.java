package androidx.core.graphics;

import android.graphics.Canvas;
import android.graphics.Picture;
import defpackage.ueu;
import defpackage.uhx;
import defpackage.uhy;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;

@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u001a9\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0017\u0010\u0005\u001a\u0013\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006¢\u0006\u0002\b\tH\u0086\bø\u0001\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\n"}, d2 = {"record", "Landroid/graphics/Picture;", "width", "", "height", "block", "Lkotlin/Function1;", "Landroid/graphics/Canvas;", "", "Lkotlin/ExtensionFunctionType;", "core-ktx_release"}, k = 2, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes8.dex */
public final class PictureKt {
    public static final Picture record(Picture picture, int i, int i2, Function1<? super Canvas, ueu> function1) {
        uhy.e((Object) picture, "");
        uhy.e((Object) function1, "");
        Canvas beginRecording = picture.beginRecording(i, i2);
        uhy.a(beginRecording, "");
        try {
            function1.invoke(beginRecording);
            return picture;
        } finally {
            uhx.a(1);
            picture.endRecording();
            uhx.b(1);
        }
    }
}
