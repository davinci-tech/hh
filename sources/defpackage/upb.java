package defpackage;

import androidx.exifinterface.media.ExifInterface;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ServiceLoader;
import java.util.Set;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;
import kotlin.Metadata;
import kotlinx.coroutines.internal.MainDispatcherFactory;

@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J!\u0010\u0005\u001a\u0004\u0018\u00010\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\b2\u0006\u0010\t\u001a\u00020\u0004H\u0082\bJ1\u0010\n\u001a\u0002H\u000b\"\u0004\b\u0000\u0010\u000b2\u0006\u0010\f\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\u000b0\bH\u0002¢\u0006\u0002\u0010\u0010J*\u0010\u0011\u001a\b\u0012\u0004\u0012\u0002H\u000b0\u0012\"\u0004\b\u0000\u0010\u000b2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\u000b0\b2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u0013\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00060\u0012H\u0000¢\u0006\u0002\b\u0014J/\u0010\u0015\u001a\b\u0012\u0004\u0012\u0002H\u000b0\u0012\"\u0004\b\u0000\u0010\u000b2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\u000b0\b2\u0006\u0010\r\u001a\u00020\u000eH\u0000¢\u0006\u0002\b\u0016J\u0016\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00040\u00122\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J\u0016\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00040\u00122\u0006\u0010\u001b\u001a\u00020\u001cH\u0002J,\u0010\u001d\u001a\u0002H\u001e\"\u0004\b\u0000\u0010\u001e*\u00020\u001f2\u0012\u0010 \u001a\u000e\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u0002H\u001e0!H\u0082\b¢\u0006\u0002\u0010\"R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006#"}, d2 = {"Lkotlinx/coroutines/internal/FastServiceLoader;", "", "()V", "PREFIX", "", "createInstanceOf", "Lkotlinx/coroutines/internal/MainDispatcherFactory;", "baseClass", "Ljava/lang/Class;", "serviceClass", "getProviderInstance", ExifInterface.LATITUDE_SOUTH, "name", "loader", "Ljava/lang/ClassLoader;", "service", "(Ljava/lang/String;Ljava/lang/ClassLoader;Ljava/lang/Class;)Ljava/lang/Object;", "load", "", "loadMainDispatcherFactory", "loadMainDispatcherFactory$kotlinx_coroutines_core", "loadProviders", "loadProviders$kotlinx_coroutines_core", "parse", "url", "Ljava/net/URL;", "parseFile", "r", "Ljava/io/BufferedReader;", "use", "R", "Ljava/util/jar/JarFile;", "block", "Lkotlin/Function1;", "(Ljava/util/jar/JarFile;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes10.dex */
public final class upb {
    public static final upb b = new upb();

    private upb() {
    }

    public final List<MainDispatcherFactory> d() {
        MainDispatcherFactory mainDispatcherFactory;
        if (!ANDROID_DETECTED.a()) {
            return d(MainDispatcherFactory.class, MainDispatcherFactory.class.getClassLoader());
        }
        try {
            ArrayList arrayList = new ArrayList(2);
            MainDispatcherFactory mainDispatcherFactory2 = null;
            try {
                mainDispatcherFactory = (MainDispatcherFactory) MainDispatcherFactory.class.cast(Class.forName("kotlinx.coroutines.android.AndroidDispatcherFactory", true, MainDispatcherFactory.class.getClassLoader()).getDeclaredConstructor(new Class[0]).newInstance(new Object[0]));
            } catch (ClassNotFoundException unused) {
                mainDispatcherFactory = null;
            }
            if (mainDispatcherFactory != null) {
                arrayList.add(mainDispatcherFactory);
            }
            try {
                mainDispatcherFactory2 = (MainDispatcherFactory) MainDispatcherFactory.class.cast(Class.forName("kotlinx.coroutines.test.internal.TestMainDispatcherFactory", true, MainDispatcherFactory.class.getClassLoader()).getDeclaredConstructor(new Class[0]).newInstance(new Object[0]));
            } catch (ClassNotFoundException unused2) {
            }
            if (mainDispatcherFactory2 != null) {
                arrayList.add(mainDispatcherFactory2);
            }
            return arrayList;
        } catch (Throwable unused3) {
            return d(MainDispatcherFactory.class, MainDispatcherFactory.class.getClassLoader());
        }
    }

    private final <S> List<S> d(Class<S> cls, ClassLoader classLoader) {
        try {
            return c(cls, classLoader);
        } catch (Throwable unused) {
            return ufe.g(ServiceLoader.load(cls, classLoader));
        }
    }

    public final <S> List<S> c(Class<S> cls, ClassLoader classLoader) {
        ArrayList list = Collections.list(classLoader.getResources(uhy.b("META-INF/services/", (Object) cls.getName())));
        uhy.a(list, "");
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            ufe.d(arrayList, b.e((URL) it.next()));
        }
        Set i = ufe.i((Iterable) arrayList);
        if (!(!i.isEmpty())) {
            throw new IllegalArgumentException("No providers were loaded with FastServiceLoader".toString());
        }
        Set set = i;
        ArrayList arrayList2 = new ArrayList(ufe.d(set, 10));
        Iterator it2 = set.iterator();
        while (it2.hasNext()) {
            arrayList2.add(b.c((String) it2.next(), classLoader, cls));
        }
        return arrayList2;
    }

    private final <S> S c(String str, ClassLoader classLoader, Class<S> cls) {
        Class<?> cls2 = Class.forName(str, false, classLoader);
        if (!cls.isAssignableFrom(cls2)) {
            throw new IllegalArgumentException(("Expected service of class " + cls + ", but found " + cls2).toString());
        }
        return cls.cast(cls2.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]));
    }

    private final List<String> e(URL url) {
        BufferedReader bufferedReader;
        String url2 = url.toString();
        if (ujy.c(url2, "jar", false, 2, (Object) null)) {
            String a2 = ujy.a(ujy.a(url2, "jar:file:", (String) null, 2, (Object) null), '!', (String) null, 2, (Object) null);
            String a3 = ujy.a(url2, "!/", (String) null, 2, (Object) null);
            JarFile jarFile = new JarFile(a2, false);
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(jarFile.getInputStream(new ZipEntry(a3)), "UTF-8"));
                try {
                    List<String> a4 = b.a(bufferedReader);
                    closeFinally.d(bufferedReader, null);
                    jarFile.close();
                    return a4;
                } finally {
                }
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    try {
                        jarFile.close();
                        throw th2;
                    } catch (Throwable th3) {
                        ued.c(th, th3);
                        throw th;
                    }
                }
            }
        } else {
            bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
            try {
                List<String> a5 = b.a(bufferedReader);
                closeFinally.d(bufferedReader, null);
                return a5;
            } catch (Throwable th4) {
                try {
                    throw th4;
                } finally {
                }
            }
        }
    }

    private final List<String> a(BufferedReader bufferedReader) {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine != null) {
                String obj = ujy.g(ujy.d(readLine, "#", (String) null, 2, (Object) null)).toString();
                String str = obj;
                int i = 0;
                while (i < str.length()) {
                    char charAt = str.charAt(i);
                    i++;
                    if (charAt != '.' && !Character.isJavaIdentifierPart(charAt)) {
                        throw new IllegalArgumentException(uhy.b("Illegal service provider class name: ", (Object) obj).toString());
                    }
                }
                if (str.length() > 0) {
                    linkedHashSet.add(obj);
                }
            } else {
                return ufe.g(linkedHashSet);
            }
        }
    }
}
