package com.huawei.featureuserprofile.award.model;

import java.io.Serializable;

/* loaded from: classes7.dex */
public class AddressInfo implements Serializable {
    private static final long serialVersionUID = -5135787674178299448L;
    private int clickPos;
    private SaveInfoItem item;
    private int source;

    public AddressInfo(int i, SaveInfoItem saveInfoItem, int i2) {
        this.source = i;
        this.item = saveInfoItem;
        this.clickPos = i2;
    }

    public int getSource() {
        return this.source;
    }

    public void setSource(int i) {
        this.source = i;
    }

    public SaveInfoItem getItem() {
        return this.item;
    }

    public void setItem(SaveInfoItem saveInfoItem) {
        this.item = saveInfoItem;
    }

    public int getClickPos() {
        return this.clickPos;
    }

    public void setClickPos(int i) {
        this.clickPos = i;
    }
}
