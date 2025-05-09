package com.huawei.watchface.videoedit.presenter;

import com.huawei.watchface.videoedit.param.PlayerBean;
import com.huawei.watchface.videoedit.param.PlayerMaterial;
import com.huawei.watchface.videoedit.param.PlayerModel;
import com.huawei.watchface.videoedit.param.Segments;
import com.huawei.watchface.videoedit.param.TemplateModel;
import com.huawei.watchface.videoedit.param.Tracks;
import com.huawei.watchface.videoedit.sysc.Optional;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes9.dex */
public abstract class PlayVisitor extends ModelVisitor<PlayerModel> {
    private final String interested;

    protected abstract List<PlayerMaterial> generatePlayerMaterial(TemplateModel templateModel);

    public PlayVisitor(int i, String str) {
        super(i);
        this.interested = str;
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [T, com.huawei.watchface.videoedit.param.PlayerModel] */
    @Override // com.huawei.watchface.videoedit.presenter.ModelVisitor
    public void onDataSetChanged(TemplateModel templateModel) {
        List<PlayerBean> generatePlayerBean = generatePlayerBean(templateModel);
        List<PlayerMaterial> generatePlayerMaterial = generatePlayerMaterial(templateModel);
        for (PlayerBean playerBean : generatePlayerBean) {
            Optional<PlayerMaterial> findPathById = findPathById(playerBean.getMaterialId(), generatePlayerMaterial);
            if (findPathById.isPresent()) {
                playerBean.setPath(findPathById.get().getPath());
                playerBean.setWidth(findPathById.get().getWidth());
                playerBean.setHeight(findPathById.get().getHeight());
                playerBean.setType(tranlateModelTypeToBeanType(findPathById.get().getType()));
            }
        }
        this.selfStorage = new PlayerModel(generatePlayerBean, generatePlayerMaterial);
    }

    private List<PlayerBean> generatePlayerBean(TemplateModel templateModel) {
        Tracks tracks;
        Iterator<Tracks> it = templateModel.getTracks().iterator();
        while (true) {
            if (!it.hasNext()) {
                tracks = null;
                break;
            }
            tracks = it.next();
            if (this.interested.equals(tracks.getType())) {
                break;
            }
        }
        ArrayList arrayList = new ArrayList();
        if (tracks == null) {
            return arrayList;
        }
        for (Segments segments : tracks.getSegments()) {
            PlayerBean playerBean = new PlayerBean();
            playerBean.setSourceTimeRange(segments.getSegmentsSourceTimeRange()).setTargetTimeRange(segments.getSegmentsTargetTimerange()).setId(segments.getId()).setMaterialId(segments.getMaterialId()).setSpeed(segments.getSpeed()).setVolume(segments.getVolume()).setFadeInDuration(segments.getFadeInDuration()).setFadeOutDuration(segments.getFadeOutDuration());
            arrayList.add(playerBean);
        }
        return arrayList;
    }

    private Optional<PlayerMaterial> findPathById(String str, List<PlayerMaterial> list) {
        for (PlayerMaterial playerMaterial : list) {
            if (str.equals(playerMaterial.getId())) {
                return Optional.ofNullable(playerMaterial);
            }
        }
        return Optional.empty();
    }

    private String tranlateModelTypeToBeanType(String str) {
        return "music".equals(str) ? PresenterUtils.AUDIO : str;
    }
}
