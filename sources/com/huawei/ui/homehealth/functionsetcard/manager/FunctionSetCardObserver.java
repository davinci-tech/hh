package com.huawei.ui.homehealth.functionsetcard.manager;

import java.util.List;

/* loaded from: classes6.dex */
public interface FunctionSetCardObserver {
    void notifyAllCardChanged();

    void notifyCardInsert(String str);

    void notifyCardOrderChanged(String str);

    void notifyCardReplace(String str, String str2);

    void notifyCardShowStatusChanged(String str, boolean z);

    void notifyCardShowStatusChanged(List<String> list, boolean z);
}
