package com.huawei.pluginhealthzone.interactors;

import defpackage.mqc;
import java.util.List;

/* loaded from: classes6.dex */
public interface AbnormalDataInformationCallback {
    void onFailure(int i, String str);

    void onSuccess(List<mqc> list);
}
