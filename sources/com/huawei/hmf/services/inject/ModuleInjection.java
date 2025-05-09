package com.huawei.hmf.services.inject;

import android.util.Log;
import com.huawei.hmf.annotation.Inject;
import com.huawei.hmf.repository.ComponentRepository;
import com.huawei.hmf.services.Module;
import com.huawei.hmf.services.inject.InjectValue;
import com.huawei.hmf.services.ui.UIModule;
import com.huawei.hmf.services.ui.internal.PojoGenerator;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* loaded from: classes9.dex */
public class ModuleInjection {
    private static final String TAG = "ModuleInjection";
    static Map<UIModule, Selector> uiModuleSelectorMap = new HashMap();
    private final InjectBindingRegistry mInjectData;

    public ModuleInjection(InjectBindingRegistry injectBindingRegistry) {
        this.mInjectData = injectBindingRegistry;
    }

    public Object inject(Object obj) {
        if (obj instanceof PojoGenerator) {
            PojoGenerator m599clone = ((PojoGenerator) obj).m599clone();
            injectMethod(m599clone);
            return m599clone;
        }
        injectField(obj);
        return obj;
    }

    private void injectField(Object obj) {
        if (obj == null) {
            return;
        }
        for (Field field : obj.getClass().getDeclaredFields()) {
            Inject inject = (Inject) field.getAnnotation(Inject.class);
            if (inject != null) {
                Object createUIModule = createUIModule(inject.value());
                field.setAccessible(true);
                try {
                    field.set(obj, createUIModule);
                } catch (IllegalAccessException unused) {
                }
            }
        }
    }

    private void injectMethod(PojoGenerator pojoGenerator) {
        for (Method method : pojoGenerator.getInterface().getDeclaredMethods()) {
            Inject inject = (Inject) method.getAnnotation(Inject.class);
            if (inject != null) {
                pojoGenerator.setValue(PojoGenerator.resolveName(method.getName()), createUIModule(inject.value()));
            }
        }
    }

    private Object createUIModule(String str) {
        InjectInstanceCreator create;
        Module lookup = ComponentRepository.getRepository().lookup(this.mInjectData.getModuleName());
        if (lookup == null) {
            Log.e(TAG, "lookup module failed with name " + this.mInjectData.getModuleName());
            return null;
        }
        InjectValue injectValue = this.mInjectData.get(str);
        if (injectValue != null && (create = InjectInstanceFactoryRegistry.create(injectValue.getValue())) != null) {
            UIModule uIModule = (UIModule) create.createInstance(lookup, str);
            uIModule.setInjected(injectValue.getType() == InjectValue.Type.EXPLICIT_INJECT);
            return uIModule;
        }
        return lookup.createUIModule(str);
    }

    public static Set<String> getInjectNames(Class cls) {
        HashSet hashSet = new HashSet();
        for (Field field : cls.getDeclaredFields()) {
            Inject inject = (Inject) field.getAnnotation(Inject.class);
            if (inject != null) {
                hashSet.add(inject.value());
            }
        }
        for (Method method : cls.getDeclaredMethods()) {
            Inject inject2 = (Inject) method.getAnnotation(Inject.class);
            if (inject2 != null) {
                hashSet.add(inject2.value());
            }
        }
        return hashSet;
    }

    public static Selector selector(UIModule uIModule) {
        Selector selector = uiModuleSelectorMap.get(uIModule);
        if (selector != null) {
            return selector;
        }
        SelectorImpl selectorImpl = new SelectorImpl();
        uiModuleSelectorMap.put(uIModule, selectorImpl);
        return selectorImpl;
    }

    public static Selector selector(Module module) {
        throw new UnsupportedOperationException("Operation not yet implemented");
    }
}
