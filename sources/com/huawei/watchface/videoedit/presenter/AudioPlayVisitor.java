package com.huawei.watchface.videoedit.presenter;

import com.huawei.watchface.videoedit.param.Audios;
import com.huawei.watchface.videoedit.param.PlayerMaterial;
import com.huawei.watchface.videoedit.param.TemplateModel;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes9.dex */
public class AudioPlayVisitor extends PlayVisitor {
    public AudioPlayVisitor(int i, String str) {
        super(i, str);
    }

    @Override // com.huawei.watchface.videoedit.presenter.PlayVisitor
    protected List<PlayerMaterial> generatePlayerMaterial(TemplateModel templateModel) {
        ArrayList arrayList = new ArrayList();
        List<Audios> audios = templateModel.getMaterials().getAudios();
        if (audios == null) {
            return arrayList;
        }
        for (Audios audios2 : audios) {
            PlayerMaterial playerMaterial = new PlayerMaterial();
            playerMaterial.setId(audios2.getId()).setPath(audios2.getPath()).setType(audios2.getType());
            arrayList.add(playerMaterial);
        }
        return arrayList;
    }
}
