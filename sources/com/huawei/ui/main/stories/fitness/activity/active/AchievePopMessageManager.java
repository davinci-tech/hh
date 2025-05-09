package com.huawei.ui.main.stories.fitness.activity.active;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.ui.main.R$string;
import defpackage.edr;
import defpackage.jdl;
import defpackage.nsf;
import defpackage.nsg;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.UnitUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

/* loaded from: classes9.dex */
public class AchievePopMessageManager {

    /* renamed from: a, reason: collision with root package name */
    private final edr f9720a;
    private int b;
    private final Context e;

    public AchievePopMessageManager(edr edrVar, Context context) {
        this.b = -1;
        this.f9720a = edrVar;
        this.b = a();
        this.e = context;
    }

    public String d() {
        if (this.f9720a == null) {
            return "";
        }
        for (AchieveMessage achieveMessage : AchieveMessage.values()) {
            if (achieveMessage.getPriorityType() == this.b) {
                return achieveMessage.getTitle();
            }
        }
        return "";
    }

    public String c() {
        for (AchieveMessage achieveMessage : AchieveMessage.values()) {
            if (achieveMessage.getPriorityType() == this.b) {
                return achieveMessage.getContent();
            }
        }
        return "";
    }

    private int a() {
        edr edrVar = this.f9720a;
        if (edrVar == null) {
            return -1;
        }
        float e = edrVar.e();
        int c = this.f9720a.c();
        int i = this.f9720a.i();
        int f = this.f9720a.f();
        int s = this.f9720a.s();
        int q = this.f9720a.q();
        if (c * 4 <= e) {
            return 7;
        }
        if (f * 4 <= i) {
            return 6;
        }
        if (c * 3 <= e) {
            return 5;
        }
        if (f * 3 <= i) {
            return 4;
        }
        if (c * 2 <= e) {
            return 3;
        }
        if (f * 2 <= i) {
            return 2;
        }
        return (e < ((float) c) || i < f || s < q) ? -1 : 1;
    }

    public String e() {
        for (AchieveMessage achieveMessage : AchieveMessage.values()) {
            if (achieveMessage.getPriorityType() == this.b) {
                return achieveMessage.getSpKey();
            }
        }
        return "";
    }

    public boolean b() {
        if (this.b == -1) {
            return false;
        }
        edr edrVar = this.f9720a;
        if (edrVar != null && !jdl.d(edrVar.t(), System.currentTimeMillis())) {
            ReleaseLogUtil.e("R_AchievePopMessageManager", "isShowPopWindow is no sameDay");
            return false;
        }
        AchieveMessage[] values = AchieveMessage.values();
        int b = DateFormatUtil.b(System.currentTimeMillis());
        for (AchieveMessage achieveMessage : values) {
            if (achieveMessage.getPriorityType() == this.b) {
                String b2 = SharedPreferenceManager.b(this.e, String.valueOf(10000), achieveMessage.getSpKey());
                ReleaseLogUtil.e("R_AchievePopMessageManager", "isShowPopWindow spDate:", b2);
                if (TextUtils.isEmpty(b2)) {
                    return true;
                }
                return !String.valueOf(b).equals(b2);
            }
        }
        ReleaseLogUtil.e("R_AchievePopMessageManager", "isShowPopWindow not match");
        return false;
    }

    enum AchieveMessage {
        CALORIE_ACHIEVE_FOUR(7, "CALORIE_ACHIEVE_FOUR", getContent("ACHIEVE_TYPE_CALORIE", 400), getTitle(R$string.IDS_achicve_move_title, 400)),
        EXERCISE_ACHIEVE_FOUR(6, "EXERCISE_ACHIEVE_FOUR", getContent("ACHIEVE_TYPE_EXERCISE", 400), getTitle(R$string.IDS_achicve_exercise_title, 400)),
        CALORIE_ACHIEVE_THREE(5, "CALORIE_ACHIEVE_THREE", getContent("ACHIEVE_TYPE_CALORIE", 300), getTitle(R$string.IDS_achicve_move_title, 300)),
        EXERCISE_ACHIEVE_THREE(4, "EXERCISE_ACHIEVE_THREE", getContent("ACHIEVE_TYPE_EXERCISE", 300), getTitle(R$string.IDS_achicve_exercise_title, 300)),
        CALORIE_ACHIEVE_TWO(3, "CALORIE_ACHIEVE_TWO", getContent("ACHIEVE_TYPE_CALORIE", 200), getTitle(R$string.IDS_achicve_move_title, 200)),
        EXERCISE_ACHIEVE_TWO(2, "EXERCISE_ACHIEVE_TWO", getContent("ACHIEVE_TYPE_EXERCISE", 200), getTitle(R$string.IDS_achicve_exercise_title, 200)),
        THREE_ACHIEVE(1, "SP_THREE_ACHIEVE", "", nsf.h(R$string.IDS_achicve_three_circle));

        private final String mContent;
        private final int mPriorityType;
        private final String mSpKey;
        private final String mTitle;

        AchieveMessage(int i, String str, String str2, String str3) {
            this.mPriorityType = i;
            this.mSpKey = str;
            this.mContent = str2;
            this.mTitle = str3;
        }

        public int getPriorityType() {
            return this.mPriorityType;
        }

        public String getSpKey() {
            return this.mSpKey;
        }

        public String getContent() {
            return this.mContent;
        }

        public String getTitle() {
            return this.mTitle;
        }

        private static String getContent(String str, int i) {
            return nsf.b(getRandomContentId(str, i), UnitUtil.e(i, 2, 0));
        }

        private static int getRandomContentId(String str, int i) {
            int b = nsg.b(2);
            int i2 = 0;
            if ("ACHIEVE_TYPE_CALORIE".equals(str)) {
                RandomAchieveContentCalorie[] values = RandomAchieveContentCalorie.values();
                int length = values.length;
                while (i2 < length) {
                    RandomAchieveContentCalorie randomAchieveContentCalorie = values[i2];
                    if (randomAchieveContentCalorie.mMultiple == i) {
                        return b == 0 ? randomAchieveContentCalorie.mContentRandomOne : randomAchieveContentCalorie.mContentRandomTwo;
                    }
                    i2++;
                }
                return -1;
            }
            if (!"ACHIEVE_TYPE_EXERCISE".equals(str)) {
                return -1;
            }
            RandomAchieveContentExercise[] values2 = RandomAchieveContentExercise.values();
            int length2 = values2.length;
            while (i2 < length2) {
                RandomAchieveContentExercise randomAchieveContentExercise = values2[i2];
                if (randomAchieveContentExercise.mMultiple == i) {
                    return b == 0 ? randomAchieveContentExercise.mContentRandomOne : randomAchieveContentExercise.mContentRandomTwo;
                }
                i2++;
            }
            return -1;
        }

        private static String getTitle(int i, int i2) {
            return nsf.b(i, UnitUtil.e(i2, 2, 0));
        }
    }

    enum RandomAchieveContentCalorie {
        CONTENT_200(200, R$string.IDS_achieve_signal_two_1_cal, R$string.IDS_achieve_signal_two_2_cal),
        CONTENT_300(300, R$string.IDS_achieve_signal_three_1_cal, R$string.IDS_achieve_signal_three_2_cal),
        CONTENT_400(400, R$string.IDS_achieve_signal_four_1_cal, R$string.IDS_achieve_signal_four_2_cal);

        private final int mContentRandomOne;
        private final int mContentRandomTwo;
        private final int mMultiple;

        RandomAchieveContentCalorie(int i, int i2, int i3) {
            this.mMultiple = i;
            this.mContentRandomOne = i2;
            this.mContentRandomTwo = i3;
        }
    }

    enum RandomAchieveContentExercise {
        CONTENT_200(200, R$string.IDS_achieve_signal_two_1_time, R$string.IDS_achieve_signal_two_2_time),
        CONTENT_300(300, R$string.IDS_achieve_signal_three_1_time, R$string.IDS_achieve_signal_three_2_time),
        CONTENT_400(400, R$string.IDS_achieve_signal_four_1_time, R$string.IDS_achieve_signal_four_2_time);

        private final int mContentRandomOne;
        private final int mContentRandomTwo;
        private final int mMultiple;

        RandomAchieveContentExercise(int i, int i2, int i3) {
            this.mMultiple = i;
            this.mContentRandomOne = i2;
            this.mContentRandomTwo = i3;
        }
    }
}
