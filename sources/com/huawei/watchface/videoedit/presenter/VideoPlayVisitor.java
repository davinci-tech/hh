package com.huawei.watchface.videoedit.presenter;

import com.huawei.watchface.videoedit.param.PlayerMaterial;
import com.huawei.watchface.videoedit.param.TemplateModel;
import com.huawei.watchface.videoedit.param.Videos;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes9.dex */
public class VideoPlayVisitor extends PlayVisitor {
    public VideoPlayVisitor(int i, String str) {
        super(i, str);
    }

    @Override // com.huawei.watchface.videoedit.presenter.PlayVisitor
    protected List<PlayerMaterial> generatePlayerMaterial(TemplateModel templateModel) {
        ArrayList arrayList = new ArrayList();
        List<Videos> videos = templateModel.getMaterials().getVideos();
        if (videos != null && videos.size() != 0) {
            for (Videos videos2 : videos) {
                PlayerMaterial playerMaterial = new PlayerMaterial();
                playerMaterial.setId(videos2.getId()).setPath(videos2.getReversePath()).setType(videos2.getType()).setWidth(videos2.getWidth()).setHeight(videos2.getHeight());
                arrayList.add(playerMaterial);
            }
        }
        return arrayList;
    }
}
