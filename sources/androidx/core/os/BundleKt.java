package androidx.core.os;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcelable;
import android.util.Size;
import android.util.SizeF;
import defpackage.ueo;
import defpackage.uhy;
import java.io.Serializable;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\b\u0002\u001a\u0006\u0010\u0000\u001a\u00020\u0001\u001a;\u0010\u0000\u001a\u00020\u00012.\u0010\u0002\u001a\u0018\u0012\u0014\b\u0001\u0012\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u00040\u0003\"\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0004¢\u0006\u0002\u0010\u0007¨\u0006\b"}, d2 = {"bundleOf", "Landroid/os/Bundle;", "pairs", "", "Lkotlin/Pair;", "", "", "([Lkotlin/Pair;)Landroid/os/Bundle;", "core-ktx_release"}, k = 2, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class BundleKt {
    public static final Bundle bundleOf(ueo<String, ? extends Object>... ueoVarArr) {
        uhy.e((Object) ueoVarArr, "");
        Bundle bundle = new Bundle(ueoVarArr.length);
        for (ueo<String, ? extends Object> ueoVar : ueoVarArr) {
            String b = ueoVar.b();
            Object d = ueoVar.d();
            if (d == null) {
                bundle.putString(b, null);
            } else if (d instanceof Boolean) {
                bundle.putBoolean(b, ((Boolean) d).booleanValue());
            } else if (d instanceof Byte) {
                bundle.putByte(b, ((Number) d).byteValue());
            } else if (d instanceof Character) {
                bundle.putChar(b, ((Character) d).charValue());
            } else if (d instanceof Double) {
                bundle.putDouble(b, ((Number) d).doubleValue());
            } else if (d instanceof Float) {
                bundle.putFloat(b, ((Number) d).floatValue());
            } else if (d instanceof Integer) {
                bundle.putInt(b, ((Number) d).intValue());
            } else if (d instanceof Long) {
                bundle.putLong(b, ((Number) d).longValue());
            } else if (d instanceof Short) {
                bundle.putShort(b, ((Number) d).shortValue());
            } else if (d instanceof Bundle) {
                bundle.putBundle(b, (Bundle) d);
            } else if (d instanceof CharSequence) {
                bundle.putCharSequence(b, (CharSequence) d);
            } else if (d instanceof Parcelable) {
                bundle.putParcelable(b, (Parcelable) d);
            } else if (d instanceof boolean[]) {
                bundle.putBooleanArray(b, (boolean[]) d);
            } else if (d instanceof byte[]) {
                bundle.putByteArray(b, (byte[]) d);
            } else if (d instanceof char[]) {
                bundle.putCharArray(b, (char[]) d);
            } else if (d instanceof double[]) {
                bundle.putDoubleArray(b, (double[]) d);
            } else if (d instanceof float[]) {
                bundle.putFloatArray(b, (float[]) d);
            } else if (d instanceof int[]) {
                bundle.putIntArray(b, (int[]) d);
            } else if (d instanceof long[]) {
                bundle.putLongArray(b, (long[]) d);
            } else if (d instanceof short[]) {
                bundle.putShortArray(b, (short[]) d);
            } else if (d instanceof Object[]) {
                Class<?> componentType = d.getClass().getComponentType();
                uhy.d(componentType);
                if (Parcelable.class.isAssignableFrom(componentType)) {
                    uhy.b(d, "");
                    bundle.putParcelableArray(b, (Parcelable[]) d);
                } else if (String.class.isAssignableFrom(componentType)) {
                    uhy.b(d, "");
                    bundle.putStringArray(b, (String[]) d);
                } else if (CharSequence.class.isAssignableFrom(componentType)) {
                    uhy.b(d, "");
                    bundle.putCharSequenceArray(b, (CharSequence[]) d);
                } else if (Serializable.class.isAssignableFrom(componentType)) {
                    bundle.putSerializable(b, (Serializable) d);
                } else {
                    throw new IllegalArgumentException("Illegal value array type " + componentType.getCanonicalName() + " for key \"" + b + '\"');
                }
            } else if (d instanceof Serializable) {
                bundle.putSerializable(b, (Serializable) d);
            } else if (d instanceof IBinder) {
                BundleApi18ImplKt.putBinder(bundle, b, (IBinder) d);
            } else if (d instanceof Size) {
                BundleApi21ImplKt.putSize(bundle, b, (Size) d);
            } else if (d instanceof SizeF) {
                BundleApi21ImplKt.putSizeF(bundle, b, (SizeF) d);
            } else {
                throw new IllegalArgumentException("Illegal value type " + d.getClass().getCanonicalName() + " for key \"" + b + '\"');
            }
        }
        return bundle;
    }

    public static final Bundle bundleOf() {
        return new Bundle(0);
    }
}
