package androidx.lifecycle;

import android.app.Application;
import androidx.exifinterface.media.ExifInterface;
import com.alipay.sdk.m.p.e;
import com.tencent.open.SocialOperation;
import defpackage.uez;
import defpackage.ufe;
import defpackage.uhy;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000*\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0002\u001a6\u0010\u0004\u001a\n\u0012\u0004\u0012\u0002H\u0006\u0018\u00010\u0005\"\u0004\b\u0000\u0010\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\u00060\u00022\u0010\u0010\b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00020\u0001H\u0000\u001aI\u0010\t\u001a\u0002H\u0006\"\n\b\u0000\u0010\u0006*\u0004\u0018\u00010\n2\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\u00060\u00022\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\u00060\u00052\u0012\u0010\f\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u000e0\r\"\u00020\u000eH\u0000¢\u0006\u0002\u0010\u000f\"\u0018\u0010\u0000\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0018\u0010\u0003\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"ANDROID_VIEWMODEL_SIGNATURE", "", "Ljava/lang/Class;", "VIEWMODEL_SIGNATURE", "findMatchingConstructor", "Ljava/lang/reflect/Constructor;", ExifInterface.GPS_DIRECTION_TRUE, "modelClass", SocialOperation.GAME_SIGNATURE, "newInstance", "Landroidx/lifecycle/ViewModel;", "constructor", e.n, "", "", "(Ljava/lang/Class;Ljava/lang/reflect/Constructor;[Ljava/lang/Object;)Landroidx/lifecycle/ViewModel;", "lifecycle-viewmodel-savedstate_release"}, k = 2, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class SavedStateViewModelFactoryKt {
    private static final List<Class<?>> ANDROID_VIEWMODEL_SIGNATURE = ufe.a(Application.class, SavedStateHandle.class);
    private static final List<Class<?>> VIEWMODEL_SIGNATURE = ufe.d(SavedStateHandle.class);

    public static final <T extends ViewModel> T newInstance(Class<T> cls, Constructor<T> constructor, Object... objArr) {
        uhy.e((Object) cls, "");
        uhy.e((Object) constructor, "");
        uhy.e((Object) objArr, "");
        try {
            return constructor.newInstance(Arrays.copyOf(objArr, objArr.length));
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Failed to access " + cls, e);
        } catch (InstantiationException e2) {
            throw new RuntimeException("A " + cls + " cannot be instantiated.", e2);
        } catch (InvocationTargetException e3) {
            throw new RuntimeException("An exception happened in constructor of " + cls, e3.getCause());
        }
    }

    public static final <T> Constructor<T> findMatchingConstructor(Class<T> cls, List<? extends Class<?>> list) {
        uhy.e((Object) cls, "");
        uhy.e((Object) list, "");
        Object[] constructors = cls.getConstructors();
        uhy.a(constructors, "");
        for (Object obj : constructors) {
            Constructor<T> constructor = (Constructor<T>) obj;
            Class<?>[] parameterTypes = constructor.getParameterTypes();
            uhy.a(parameterTypes, "");
            List e = uez.e(parameterTypes);
            if (uhy.e(list, e)) {
                if (constructor != null) {
                    return constructor;
                }
                throw new NullPointerException("null cannot be cast to non-null type java.lang.reflect.Constructor<T of androidx.lifecycle.SavedStateViewModelFactoryKt.findMatchingConstructor>");
            }
            if (list.size() == e.size() && e.containsAll(list)) {
                throw new UnsupportedOperationException("Class " + cls.getSimpleName() + " must have parameters in the proper order: " + list);
            }
        }
        return null;
    }
}
