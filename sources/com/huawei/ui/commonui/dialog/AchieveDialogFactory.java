package com.huawei.ui.commonui.dialog;

import android.content.Context;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$color;
import com.huawei.ui.commonui.R$string;
import com.tencent.open.SocialConstants;
import defpackage.nlp;
import defpackage.nlt;
import defpackage.nlu;
import defpackage.nlx;
import health.compact.a.UnitUtil;

/* loaded from: classes6.dex */
public final class AchieveDialogFactory {
    /* synthetic */ AchieveDialogFactory(AnonymousClass4 anonymousClass4) {
        this();
    }

    static final class b {
        private static final AchieveDialogFactory d = new AchieveDialogFactory(null);
    }

    public static AchieveDialogFactory c() {
        return b.d;
    }

    private AchieveDialogFactory() {
    }

    public BaseAchieveDialog cxI_(Context context, DialogType dialogType, Bundle bundle, DialogResourcesListener dialogResourcesListener) {
        String str;
        int i;
        BaseAchieveDialog nluVar;
        boolean z;
        LogUtil.a("AchieveDialogFactory", "createDialog dialogType", dialogType.toString());
        String string = bundle.getString("achieveText", c(context, dialogType.getDefAchieveTextResId(), new String[0]));
        int i2 = bundle.getInt("checkBtnText", dialogType.getDefCheckBtnTextResId());
        String d = d(bundle.getInt("heatCal", 0));
        String d2 = d(bundle.getInt("intensityTime", 0));
        String d3 = d(bundle.getInt("activeHour", 0));
        ThreeCircleShareCallback threeCircleShareCallback = bundle.getSerializable("three_circle_callback") instanceof ThreeCircleShareCallback ? (ThreeCircleShareCallback) bundle.getSerializable("three_circle_callback") : null;
        switch (AnonymousClass4.b[dialogType.ordinal()]) {
            case 1:
            case 2:
                str = string;
                i = i2;
                nluVar = new nlu(context, dialogType, dialogResourcesListener);
                z = true;
                break;
            case 3:
                str = string;
                i = i2;
                nluVar = new nlx(context, dialogType, dialogResourcesListener, threeCircleShareCallback).setHeatText(d).setIntensityTimeText(d2).setActiveHourText(d3);
                z = true;
                break;
            case 4:
            case 5:
            case 6:
                String c = c(context, bundle.getInt("title", dialogType.getDefTitleResId()), new String[0]);
                int i3 = bundle.getInt("target_value", 0);
                i = i2;
                str = string;
                nluVar = new nlp(context, dialogType, dialogResourcesListener, threeCircleShareCallback).e(c).d(d(bundle.getInt("achieve_user_value", 0))).c(c(context, bundle.getInt(SocialConstants.PARAM_APP_DESC, dialogType.getDefDescResId()), new String[0])).b(a(context, bundle.getInt("target_desc", dialogType.getDefTargetDescResId()), i3)).c(dialogType.getColorResId()).setHeatText(d).setIntensityTimeText(d2).setActiveHourText(d3);
                z = false;
                break;
            case 7:
                String c2 = c(context, bundle.getInt("title", dialogType.getDefTitleResId()), new String[0]);
                int i4 = bundle.getInt("target_value", 0);
                nluVar = new nlt(context, dialogType, dialogResourcesListener, threeCircleShareCallback).c(c2).czV_(cxH_(a(context, bundle.getInt("target_desc", dialogType.getDefTargetDescResId()), i4), d(i4)));
                str = string;
                i = i2;
                z = false;
                break;
            default:
                str = string;
                i = i2;
                z = false;
                nluVar = null;
                break;
        }
        if (nluVar != null) {
            nluVar.setAchieveText(str, z).setCheckButtonText(c(context, i, new String[0]));
        }
        return nluVar;
    }

    /* renamed from: com.huawei.ui.commonui.dialog.AchieveDialogFactory$4, reason: invalid class name */
    static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] b;

        static {
            int[] iArr = new int[DialogType.values().length];
            b = iArr;
            try {
                iArr[DialogType.THREE_LEAF_ACTIVE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                b[DialogType.THREE_LEAF_PERFECT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                b[DialogType.THREE_CIRCLE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                b[DialogType.SINGLE_CIRCLE_CALORIE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                b[DialogType.SINGLE_CIRCLE_INTENSITY.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                b[DialogType.SINGLE_CIRCLE_ACTIVE.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                b[DialogType.SINGLE_CIRCLE_WALK.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    private String d(int i) {
        return UnitUtil.e(i, 1, 0);
    }

    private String c(Context context, int i, String... strArr) {
        return i == 0 ? "" : (strArr == null || strArr.length <= 0) ? context.getResources().getString(i) : context.getResources().getString(i, strArr);
    }

    private String a(Context context, int i, int i2) {
        return i == 0 ? "" : context.getResources().getQuantityString(i, i2, d(i2));
    }

    private SpannableString cxH_(String str, String str2) {
        SpannableString spannableString = new SpannableString(str);
        int indexOf = spannableString.toString().indexOf(str2);
        if (indexOf != -1) {
            spannableString.setSpan(new AbsoluteSizeSpan(BaseApplication.getContext().getResources().getDimensionPixelSize(R.dimen._2131362667_res_0x7f0a036b)), indexOf, str2.length() + indexOf, 33);
        }
        return spannableString;
    }

    public enum DialogType {
        THREE_LEAF_ACTIVE("three_leaf_achieve_anim", R$string.IDS_achive_vitality_three_leaf_text, R$string.IDS_achive_ok_check_text),
        THREE_LEAF_PERFECT("three_leaf_green_achieve_anim", R$string.IDS_achive_perfect_three_leaf_text, R$string.IDS_achive_perfect_three_leaf_check_text),
        THREE_CIRCLE("three_circle_achieve_anim", R$string.IDS_achive_three_circle_text_new, R$string.IDS_achive_three_circle_share),
        SINGLE_CIRCLE_WALK("single_circle_walk_achieve_anim", R$color.walk_count_color, R$string.IDS_three_leaf_step_comp_title, R$string.IDS_achive_single_circle_desc, R.plurals._2130903359_res_0x7f03013f, R$string.IDS_three_leaf_step_complete_content, R$string.IDS_achive_three_circle_share),
        SINGLE_CIRCLE_CALORIE("single_circle_heat_achieve_anim", R$color.heat_cal_color, R$string.IDS_active_caloric, R$string.IDS_achive_single_circle_desc, R.plurals._2130903380_res_0x7f030154, R$string.IDS_achieve_single_circle_heat, R$string.IDS_achive_three_circle_share),
        SINGLE_CIRCLE_INTENSITY("single_circle_intensity_achieve_anim", R$color.intensity_time_color, R$string.IDS_active_workout, R$string.IDS_achive_single_circle_desc, R.plurals.IDS_single_circle_intensity_target_desc, R$string.IDS_achieve_single_circle_intensity, R$string.IDS_achive_three_circle_share),
        SINGLE_CIRCLE_ACTIVE("single_circle_active_achieve_anim", R$color.active_time_color, R$string.IDS_three_circle_card_activity_hours, R$string.IDS_achive_single_circle_desc, R.plurals.IDS_single_circle_active_target_desc, R$string.IDS_achive_single_circle_active_hours_congratulation, R$string.IDS_achive_three_circle_share);

        private final String mAnimResName;
        private int mColorResId;
        private final int mDefAchieveTextResId;
        private final int mDefCheckBtnTextResId;
        private int mDefDescResId;
        private int mDefTargetDescResId;
        private int mDefTitleResId;

        DialogType(String str, int i, int i2) {
            this.mAnimResName = str;
            this.mDefAchieveTextResId = i;
            this.mDefCheckBtnTextResId = i2;
        }

        DialogType(String str, int i, int i2, int i3, int i4, int i5, int i6) {
            this(str, i5, i6);
            this.mColorResId = i;
            this.mDefTitleResId = i2;
            this.mDefDescResId = i3;
            this.mDefTargetDescResId = i4;
        }

        public int getColorResId() {
            return this.mColorResId;
        }

        public String getAnimResName() {
            return this.mAnimResName;
        }

        public int getDefTitleResId() {
            return this.mDefTitleResId;
        }

        public int getDefDescResId() {
            return this.mDefDescResId;
        }

        public int getDefTargetDescResId() {
            return this.mDefTargetDescResId;
        }

        public int getDefAchieveTextResId() {
            return this.mDefAchieveTextResId;
        }

        public int getDefCheckBtnTextResId() {
            return this.mDefCheckBtnTextResId;
        }
    }
}
