package androidx.core.content;

import android.content.ContentValues;
import defpackage.ueo;
import defpackage.uhy;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\b\u0002\u001a;\u0010\u0000\u001a\u00020\u00012.\u0010\u0002\u001a\u0018\u0012\u0014\b\u0001\u0012\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u00040\u0003\"\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0004¢\u0006\u0002\u0010\u0007¨\u0006\b"}, d2 = {"contentValuesOf", "Landroid/content/ContentValues;", "pairs", "", "Lkotlin/Pair;", "", "", "([Lkotlin/Pair;)Landroid/content/ContentValues;", "core-ktx_release"}, k = 2, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes8.dex */
public final class ContentValuesKt {
    public static final ContentValues contentValuesOf(ueo<String, ? extends Object>... ueoVarArr) {
        uhy.e((Object) ueoVarArr, "");
        ContentValues contentValues = new ContentValues(ueoVarArr.length);
        for (ueo<String, ? extends Object> ueoVar : ueoVarArr) {
            String b = ueoVar.b();
            Object d = ueoVar.d();
            if (d == null) {
                contentValues.putNull(b);
            } else if (d instanceof String) {
                contentValues.put(b, (String) d);
            } else if (d instanceof Integer) {
                contentValues.put(b, (Integer) d);
            } else if (d instanceof Long) {
                contentValues.put(b, (Long) d);
            } else if (d instanceof Boolean) {
                contentValues.put(b, (Boolean) d);
            } else if (d instanceof Float) {
                contentValues.put(b, (Float) d);
            } else if (d instanceof Double) {
                contentValues.put(b, (Double) d);
            } else if (d instanceof byte[]) {
                contentValues.put(b, (byte[]) d);
            } else if (d instanceof Byte) {
                contentValues.put(b, (Byte) d);
            } else {
                if (!(d instanceof Short)) {
                    throw new IllegalArgumentException("Illegal value type " + d.getClass().getCanonicalName() + " for key \"" + b + '\"');
                }
                contentValues.put(b, (Short) d);
            }
        }
        return contentValues;
    }
}
