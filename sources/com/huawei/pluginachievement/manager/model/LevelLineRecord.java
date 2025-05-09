package com.huawei.pluginachievement.manager.model;

import defpackage.mcz;
import java.util.List;

/* loaded from: classes6.dex */
public class LevelLineRecord extends mcz {
    private List<ExperienceHistoryBean> records;

    public LevelLineRecord() {
        super(25);
    }

    public List<ExperienceHistoryBean> getRecords() {
        return this.records;
    }

    public void setRecords(List<ExperienceHistoryBean> list) {
        this.records = list;
    }
}
