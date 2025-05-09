package org.eclipse.californium.elements.config;

import defpackage.vac;
import defpackage.vay;
import defpackage.vbb;
import defpackage.vbh;
import defpackage.vcb;
import defpackage.vha;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;

/* loaded from: classes7.dex */
public final class Configuration {

    /* renamed from: a, reason: collision with root package name */
    private static Configuration f15870a;
    private final ConcurrentMap<String, DefinitionsProvider> f;
    private final Map<String, String> g;
    private final vac<DocumentedDefinition<?>> h;
    private final Map<String, Object> i;
    private final Set<String> j;
    public static final File e = new File("Californium3.properties");
    private static final Logger d = vha.a((Class<?>) Configuration.class);
    private static final ConcurrentMap<String, DefinitionsProvider> b = new ConcurrentHashMap();
    private static final vac<DocumentedDefinition<?>> c = new vac<>("Configuration");

    public interface DefinitionsProvider {
        void applyDefinitions(Configuration configuration);
    }

    public interface ModuleDefinitionsProvider extends DefinitionsProvider {
        String getModule();
    }

    private static boolean d(ConcurrentMap<String, DefinitionsProvider> concurrentMap, ModuleDefinitionsProvider moduleDefinitionsProvider) {
        if (concurrentMap == null) {
            throw new NullPointerException("Modules must not be null!");
        }
        if (moduleDefinitionsProvider == null) {
            throw new NullPointerException("DefinitionsProvider must not be null!");
        }
        String module = moduleDefinitionsProvider.getModule();
        if (module == null) {
            throw new IllegalArgumentException("DefinitionsProvider's module must not be null!");
        }
        if (module.isEmpty()) {
            throw new IllegalArgumentException("DefinitionsProvider's module name must not be empty!");
        }
        DefinitionsProvider putIfAbsent = concurrentMap.putIfAbsent(module, moduleDefinitionsProvider);
        if (putIfAbsent == null || putIfAbsent == moduleDefinitionsProvider) {
            return putIfAbsent == null;
        }
        throw new IllegalArgumentException("Module " + module + " already registered with different provider!");
    }

    public static void b(ModuleDefinitionsProvider moduleDefinitionsProvider) {
        if (d(b, moduleDefinitionsProvider)) {
            d.info("defaults added {}", moduleDefinitionsProvider.getModule());
        }
    }

    public static Configuration d() {
        synchronized (Configuration.class) {
            if (f15870a == null) {
                e(e);
            }
        }
        return f15870a;
    }

    public static Configuration e(File file) {
        Configuration c2 = c(file, "Californium3 CoAP Properties file", null);
        f15870a = c2;
        return c2;
    }

    public static Configuration c(File file, String str, DefinitionsProvider definitionsProvider) {
        if (file == null) {
            throw new NullPointerException("file must not be null!");
        }
        Configuration configuration = new Configuration();
        configuration.e(definitionsProvider);
        if (file.exists()) {
            configuration.a(file);
        } else {
            configuration.e(file, str);
        }
        return configuration;
    }

    public Configuration() {
        this.i = new HashMap();
        this.j = new HashSet();
        this.g = new HashMap();
        this.h = c;
        this.f = b;
        b();
    }

    public Configuration(Configuration configuration) {
        HashMap hashMap = new HashMap();
        this.i = hashMap;
        HashSet hashSet = new HashSet();
        this.j = hashSet;
        this.g = new HashMap();
        vac<DocumentedDefinition<?>> vacVar = c;
        vac<DocumentedDefinition<?>> vacVar2 = configuration.h;
        this.h = vacVar != vacVar2 ? new vac<>(vacVar2) : vacVar;
        ConcurrentMap<String, DefinitionsProvider> concurrentMap = b;
        this.f = concurrentMap != configuration.f ? new ConcurrentHashMap<>(configuration.f) : concurrentMap;
        hashSet.addAll(configuration.j);
        hashMap.putAll(configuration.i);
    }

    private void b() {
        Iterator<DefinitionsProvider> it = this.f.values().iterator();
        while (it.hasNext()) {
            it.next().applyDefinitions(this);
        }
    }

    private void e(DefinitionsProvider definitionsProvider) {
        if (definitionsProvider != null) {
            HashSet hashSet = new HashSet(this.f.keySet());
            definitionsProvider.applyDefinitions(this);
            if (hashSet.size() < this.f.size()) {
                Set<String> keySet = this.f.keySet();
                keySet.removeAll(hashSet);
                for (String str : keySet) {
                    d.warn("Add missing module {}", str);
                    this.f.get(str).applyDefinitions(this);
                }
                definitionsProvider.applyDefinitions(this);
            }
        }
    }

    public void a(File file) {
        if (file == null) {
            throw new NullPointerException("file must not be null");
        }
        d.info("loading properties from file {}", file.getAbsolutePath());
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            try {
                a(fileInputStream);
                fileInputStream.close();
            } finally {
            }
        } catch (IOException e2) {
            d.warn("cannot load properties from file {}: {}", file.getAbsolutePath(), e2.getMessage());
        }
    }

    public void a(InputStream inputStream) throws IOException {
        if (inputStream == null) {
            throw new NullPointerException("input stream must not be null");
        }
        Properties properties = new Properties();
        properties.load(inputStream);
        b(properties);
    }

    public void b(Properties properties) {
        if (properties == null) {
            throw new NullPointerException("properties must not be null!");
        }
        for (Object obj : properties.keySet()) {
            if (obj instanceof String) {
                String str = (String) obj;
                DocumentedDefinition<?> c2 = this.h.c(str);
                if (c2 == null) {
                    d.warn("Ignore {}, no configuration definition available!", str);
                } else if (e(str)) {
                    this.i.put(str, b(c2, properties.getProperty(str)));
                }
            }
        }
    }

    private boolean e(String str) {
        if (this.j.contains(str)) {
            d.warn("Ignore {}, definition set transient!", str);
            return false;
        }
        if (this.g.containsKey(str)) {
            String str2 = this.g.get(str);
            if (str2 != null) {
                d.warn("Deprecated {}, please replace it by {}!", str, str2);
                return true;
            }
            d.warn("Deprecated {}, please remove it!", str);
            return true;
        }
        d.debug("Load {}", str);
        return true;
    }

    private Object b(DocumentedDefinition<?> documentedDefinition, String str) {
        if (str != null) {
            String trim = str.trim();
            if (!trim.isEmpty()) {
                try {
                    return documentedDefinition.readValue(trim);
                } catch (RuntimeException e2) {
                    d.warn("{}", e2.getMessage());
                }
            }
        }
        return null;
    }

    public void e(File file, String str) {
        if (file == null) {
            throw new NullPointerException("file must not be null");
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            try {
                a(fileOutputStream, str, file.getAbsolutePath());
                fileOutputStream.close();
            } finally {
            }
        } catch (IOException e2) {
            d.warn("cannot write properties to {}: {}", file.getAbsolutePath(), e2.getMessage());
        }
    }

    public void a(OutputStream outputStream, String str, String str2) {
        if (outputStream == null) {
            throw new NullPointerException("output stream must not be null!");
        }
        if (str == null) {
            throw new NullPointerException("header must not be null!");
        }
        if (str2 != null) {
            d.info("writing properties to {}", str2);
        }
        try {
            Set<String> keySet = this.f.keySet();
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            for (String str3 : this.i.keySet()) {
                if (!this.j.contains(str3) && !this.g.containsKey(str3)) {
                    Iterator<String> it = keySet.iterator();
                    while (true) {
                        if (it.hasNext()) {
                            if (str3.startsWith(it.next())) {
                                arrayList2.add(str3);
                                break;
                            }
                        } else {
                            arrayList.add(str3);
                            break;
                        }
                    }
                }
            }
            Collections.sort(arrayList);
            Collections.sort(arrayList2);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
            try {
                outputStreamWriter.write(vbb.b(str));
                outputStreamWriter.write(vcb.a());
                outputStreamWriter.write(vbb.b(new Date().toString()));
                outputStreamWriter.write(vcb.a());
                outputStreamWriter.write("#");
                outputStreamWriter.write(vcb.a());
                Iterator it2 = arrayList.iterator();
                while (it2.hasNext()) {
                    e((String) it2.next(), outputStreamWriter);
                }
                Iterator it3 = arrayList2.iterator();
                while (it3.hasNext()) {
                    e((String) it3.next(), outputStreamWriter);
                }
                outputStreamWriter.close();
            } finally {
            }
        } catch (IOException e2) {
            if (str2 != null) {
                d.warn("cannot write properties to {}: {}", str2, e2.getMessage());
            } else {
                d.warn("cannot write properties: {}", e2.getMessage());
            }
        }
    }

    private void e(String str, Writer writer) throws IOException {
        String write;
        DocumentedDefinition<?> c2 = this.h.c(str);
        if (c2 == null) {
            throw new IllegalArgumentException("Definition for " + str + " not found!");
        }
        StringBuilder sb = new StringBuilder();
        String documentation = c2.getDocumentation();
        if (documentation != null) {
            sb.append(documentation);
        }
        Object defaultValue = c2.getDefaultValue();
        if (defaultValue != null && (write = c2.write(defaultValue)) != null) {
            if (sb.length() > 0) {
                sb.append('\n');
            }
            sb.append("Default: ");
            sb.append(write);
        }
        if (sb.length() > 0) {
            writer.write(vbb.b(sb.toString()));
            writer.write(vcb.a());
        }
        writer.write(vbb.d(str, true));
        writer.write(61);
        Object obj = this.i.get(str);
        if (obj != null) {
            writer.write(vbb.d(c2.write(obj), false));
        }
        writer.write(vcb.a());
    }

    public <T> Configuration e(DocumentedDefinition<T> documentedDefinition, DocumentedDefinition<T> documentedDefinition2) {
        if (documentedDefinition == null) {
            throw new NullPointerException("Deprecated definition must not be null!");
        }
        this.g.put(documentedDefinition.getKey(), documentedDefinition2 == null ? null : documentedDefinition2.getKey());
        d(documentedDefinition, null, null);
        return this;
    }

    public <T> Configuration e(BasicDefinition<T> basicDefinition, T t) {
        d(basicDefinition, t, null);
        return this;
    }

    public <T> Configuration e(BasicListDefinition<T> basicListDefinition, T... tArr) {
        if (tArr == null) {
            throw new NullPointerException("Values must not be null!");
        }
        d(basicListDefinition, Arrays.asList(tArr), null);
        return this;
    }

    public <T> T a(BasicDefinition<T> basicDefinition) {
        return (T) a((DocumentedDefinition) basicDefinition);
    }

    public Configuration e(vay vayVar, Long l, TimeUnit timeUnit) {
        if (timeUnit == null) {
            throw new NullPointerException("unit must not be null");
        }
        if (l != null) {
            l = Long.valueOf(TimeUnit.NANOSECONDS.convert(l.longValue(), timeUnit));
        }
        d(vayVar, l, null);
        return this;
    }

    public Configuration a(vay vayVar, int i, TimeUnit timeUnit) {
        return e(vayVar, Long.valueOf(i), timeUnit);
    }

    public Long a(vay vayVar, TimeUnit timeUnit) {
        Long l = (Long) a(vayVar);
        if (timeUnit != null) {
            return l != null ? Long.valueOf(timeUnit.convert(l.longValue(), TimeUnit.NANOSECONDS)) : l;
        }
        throw new NullPointerException("unit must not be null");
    }

    public int d(vay vayVar, TimeUnit timeUnit) {
        Long a2 = a(vayVar, timeUnit);
        if (a2 == null) {
            return 0;
        }
        if (a2.longValue() > 2147483647L) {
            throw new IllegalArgumentException(a2 + " doesn't fit to int (Max. 2147483647)!");
        }
        if (a2.longValue() < -2147483648L) {
            throw new IllegalArgumentException(a2 + " doesn't fit to int (Min. -2147483648)!");
        }
        return a2.intValue();
    }

    private <T> T a(DocumentedDefinition<T> documentedDefinition) {
        if (documentedDefinition == null) {
            throw new NullPointerException("definition must not be null");
        }
        DocumentedDefinition<T> documentedDefinition2 = (DocumentedDefinition) this.h.c(documentedDefinition.getKey());
        if (documentedDefinition2 != null && documentedDefinition2 != documentedDefinition) {
            throw new IllegalArgumentException("Definition " + documentedDefinition + " doesn't match " + documentedDefinition2);
        }
        T t = (T) this.i.get(documentedDefinition.getKey());
        return t == null ? documentedDefinition.getDefaultValue() : t;
    }

    private <T> void d(DocumentedDefinition<T> documentedDefinition, T t, String str) {
        T checkValue;
        if (documentedDefinition == null) {
            throw new NullPointerException("definition must not be null");
        }
        DocumentedDefinition<T> documentedDefinition2 = (DocumentedDefinition) this.h.e(documentedDefinition);
        if (documentedDefinition2 != null && documentedDefinition2 != documentedDefinition) {
            throw new IllegalArgumentException("Definition " + documentedDefinition + " doesn't match " + documentedDefinition2);
        }
        if (t == null && str != null) {
            checkValue = documentedDefinition.readValue(str);
        } else {
            if (t != null && !documentedDefinition.isAssignableFrom(t)) {
                throw new IllegalArgumentException(t.getClass().getSimpleName() + " is not a " + documentedDefinition.getTypeName());
            }
            try {
                checkValue = documentedDefinition.checkValue(t);
            } catch (vbh e2) {
                throw new IllegalArgumentException(e2.getMessage());
            }
        }
        this.i.put(documentedDefinition.getKey(), checkValue);
    }
}
