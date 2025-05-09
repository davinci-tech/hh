package com.huawei.watchface.videoedit.param;

import com.huawei.watchface.videoedit.presenter.PresenterUtils;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes9.dex */
public class PlayerModel {
    private static final int PHOTO_ADVANCE_TIME = 800;
    private static final int VIDEO_ADVANCE_TIME = 500;
    private List<PlayerBean> playerBeans;
    private List<PlayerMaterial> playerMaterials;

    public PlayerModel(List<PlayerBean> list, List<PlayerMaterial> list2) {
        this.playerBeans = list;
        this.playerMaterials = list2;
    }

    public List<PlayerBean> getPlayerBeansByTime(int i, String str) {
        ArrayList arrayList = new ArrayList();
        for (PlayerBean playerBean : this.playerBeans) {
            if (str.equals(playerBean.getType()) && isPlayerMaterialActive(playerBean.getTargetTimeRange(), i, str)) {
                arrayList.add(playerBean);
            }
        }
        return arrayList;
    }

    public List<PlayerBean> getPlayerBeans() {
        return this.playerBeans;
    }

    public List<PlayerMaterial> getMaterials() {
        return this.playerMaterials;
    }

    private boolean isPlayerMaterialActive(TargetTimeRange targetTimeRange, int i, String str) {
        int i2 = PresenterUtils.PHOTO.equals(str) ? 800 : 500;
        if (PresenterUtils.AUDIO.equals(str)) {
            i2 = 0;
        }
        return i >= targetTimeRange.getStart() - i2 && i < targetTimeRange.getStart() + targetTimeRange.getDuration();
    }
}
