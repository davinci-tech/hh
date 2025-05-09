package com.huawei.threecircle.bean;

import com.huawei.ui.commonui.dialog.AchieveDialogFactory;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public enum AchieveAniType {
    THREE_LEAF_ACTIVE(1, "vibrancy_today", 2, AchieveDialogFactory.DialogType.THREE_LEAF_ACTIVE),
    THREE_LEAF_PERFECT(2, "perfect_today", 0, AchieveDialogFactory.DialogType.THREE_LEAF_PERFECT),
    THREE_CIRCLE(3, "achieved_show_time", 0, AchieveDialogFactory.DialogType.THREE_CIRCLE, "DoneAll"),
    SINGLE_CIRCLE_WALK(4, "step_achieved_show_time", 0, AchieveDialogFactory.DialogType.SINGLE_CIRCLE_WALK),
    SINGLE_CIRCLE_INTENSITY(5, "intensity_achieved_show_time", 3, AchieveDialogFactory.DialogType.SINGLE_CIRCLE_INTENSITY, "DoneIntensity"),
    SINGLE_CIRCLE_ACTIVE(6, "active_achieved_show_time", 3, AchieveDialogFactory.DialogType.SINGLE_CIRCLE_ACTIVE, "DoneActiveH"),
    SINGLE_CIRCLE_CALORIE(7, "heat_achieved_show_time", 3, AchieveDialogFactory.DialogType.SINGLE_CIRCLE_CALORIE, "DoneCalorie");

    public String mAchieveStrKey;
    public final AchieveDialogFactory.DialogType mDialogType;
    public final String mSpKey;
    public final int mSuperType;
    public final int mType;

    AchieveAniType(int i, String str, int i2, AchieveDialogFactory.DialogType dialogType) {
        this.mType = i;
        this.mSpKey = str;
        this.mSuperType = i2;
        this.mDialogType = dialogType;
    }

    AchieveAniType(int i, String str, int i2, AchieveDialogFactory.DialogType dialogType, String str2) {
        this.mType = i;
        this.mSpKey = str;
        this.mSuperType = i2;
        this.mDialogType = dialogType;
        this.mAchieveStrKey = str2;
    }

    public List<AchieveAniType> getSubTypes() {
        ArrayList arrayList = new ArrayList();
        for (AchieveAniType achieveAniType : values()) {
            if (achieveAniType.mSuperType == this.mType) {
                arrayList.add(achieveAniType);
            }
        }
        return arrayList;
    }

    public AchieveAniType getSuperType() {
        return typeOf(this.mSuperType);
    }

    public static AchieveAniType typeOf(int i) {
        for (AchieveAniType achieveAniType : values()) {
            if (achieveAniType.mType == i) {
                return achieveAniType;
            }
        }
        return null;
    }

    public static boolean isSubType(AchieveAniType achieveAniType) {
        return (achieveAniType == null || achieveAniType.mSuperType == 0) ? false : true;
    }
}
