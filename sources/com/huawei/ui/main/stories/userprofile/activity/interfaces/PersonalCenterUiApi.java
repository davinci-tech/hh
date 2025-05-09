package com.huawei.ui.main.stories.userprofile.activity.interfaces;

import com.huawei.pluginachievement.manager.model.MessageObject;
import com.huawei.trade.datatype.AssetMessage;
import java.util.List;

/* loaded from: classes7.dex */
public interface PersonalCenterUiApi {
    void cancelBottomRedDotVisible(int i);

    void dealAssetMessage(List<AssetMessage> list);

    void openMessageCenterPage();

    void openMyAssetPage();

    void setBottomRedDotVisibility(int i);

    void updateBannerAd(String str, MessageObject messageObject);
}
