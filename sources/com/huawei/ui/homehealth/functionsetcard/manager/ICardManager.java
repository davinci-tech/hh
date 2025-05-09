package com.huawei.ui.homehealth.functionsetcard.manager;

import com.huawei.health.health.utils.functionsetcard.manager.constructor.CardConstructor;
import com.huawei.health.health.utils.functionsetcard.manager.model.CardConfig;
import defpackage.ohz;
import java.util.List;

/* loaded from: classes6.dex */
public interface ICardManager {
    void addTwoModelCard(int i, String str);

    List<CardConfig> getCardConfigList(boolean z);

    CardConstructor getCardConstructorById(String str);

    List<String> getCardIdsByGroupId(int i);

    ohz getCurrentConfigs();

    ohz getDefaultConfigs();

    List<CardConfig> getUninitializedCardsFromCache();

    void onShowStatusChanged(List<String> list, boolean z, int i);

    void replaceTwoModelCard(String str, String str2);
}
