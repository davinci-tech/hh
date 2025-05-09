package com.huawei.achievement;

import com.huawei.hmf.tasks.Task;
import defpackage.mfo;
import defpackage.mfs;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public interface MedalInfoAble {
    Task<ArrayList<mfs>> obtainCurrentMedalGainStatus(List<Integer> list);

    Task<ArrayList<mfs>> obtainMedalGainStatus(List<Integer> list);

    Task<mfo> obtainMedalInfo(String str);
}
