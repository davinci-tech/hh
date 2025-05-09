package com.huawei.animationkit.computationalwallpaper.pattern.variable;

import com.huawei.animationkit.computationalwallpaper.pattern.variable.ObservableVariable;
import com.huawei.animationkit.computationalwallpaper.pattern.variable.Variable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

/* loaded from: classes8.dex */
public abstract class ObservableVariable implements Variable {
    private final Map<String, List<Variable.VariableObserver>> mObservers = new HashMap();

    public static /* synthetic */ List lambda$addUpdateObserver$0(String str) {
        return new ArrayList();
    }

    @Override // com.huawei.animationkit.computationalwallpaper.pattern.variable.Variable
    public void addUpdateObserver(String str, Variable.VariableObserver variableObserver) {
        this.mObservers.computeIfAbsent(str, new Function() { // from class: adu
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ObservableVariable.lambda$addUpdateObserver$0((String) obj);
            }
        }).add(variableObserver);
    }

    public void notifyObserver(String str) {
        List<Variable.VariableObserver> list = this.mObservers.get(str);
        if (list == null || list.size() <= 0) {
            return;
        }
        list.forEach(new Consumer() { // from class: adv
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((Variable.VariableObserver) obj).onUpdate();
            }
        });
    }

    protected Optional<List<Variable.VariableObserver>> getObservers(String str) {
        return Optional.ofNullable(this.mObservers.get(str));
    }
}
