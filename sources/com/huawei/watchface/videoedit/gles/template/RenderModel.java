package com.huawei.watchface.videoedit.gles.template;

import com.huawei.watchface.videoedit.sysc.Optional;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes9.dex */
public class RenderModel {
    private static final int MASK_RENDER_INDEX = -1;
    private List<Material> mPlayerList;

    public RenderModel(List<Material> list) {
        this.mPlayerList = list;
    }

    public Optional<Material> getPreMaterial(long j) {
        for (Material material : this.mPlayerList) {
            if (material.getRenderIndex() == 1 && material.contains(j)) {
                return Optional.of(material);
            }
        }
        return Optional.empty();
    }

    public Optional<Material> getNextMaterial(long j) {
        int i;
        int size = this.mPlayerList.size();
        for (int i2 = 0; i2 < size; i2++) {
            Material material = this.mPlayerList.get(i2);
            if (material.getRenderIndex() == 1 && material.contains(j) && (i = i2 + 1) < size && this.mPlayerList.get(i).contains(j)) {
                return Optional.of(this.mPlayerList.get(i));
            }
        }
        return Optional.empty();
    }

    public List<Material> getMaterials() {
        return this.mPlayerList;
    }

    public Optional<Material> getMaskMaterial() {
        for (Material material : this.mPlayerList) {
            if (material.getRenderIndex() == -1) {
                return Optional.of(material);
            }
        }
        return Optional.empty();
    }

    public void release() {
        List<Material> list = this.mPlayerList;
        if (list != null) {
            Iterator<Material> it = list.iterator();
            while (it.hasNext()) {
                it.next().release();
            }
            this.mPlayerList = null;
        }
    }
}
