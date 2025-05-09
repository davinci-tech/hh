package defpackage;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import com.huawei.health.marketing.datatype.SingleDailyMomentContent;
import com.huawei.health.suggestion.ui.fitness.helper.FitnessRunAudioScenarioId;
import com.huawei.health.suggestion.ui.fitness.helper.GuideHelper;
import com.huawei.health.suggestion.ui.fitness.helper.inter.VoiceGuideInterface;
import com.huawei.health.suggestion.ui.fitness.module.Motion;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class fpd extends GuideHelper {
    private int f;
    private Motion g;
    private int h;
    private List<String> i;

    public fpd(Context context, Handler handler) {
        super(context, handler);
        this.i = new ArrayList(16);
        this.h = -1;
        this.f = 0;
        mxb.a().init(mxh.d(mxb.c(), mxb.a().b(mxb.c()), SingleDailyMomentContent.COURSE_TYPE, BaseApplication.getContext().getResources().getConfiguration().locale));
    }

    @Override // com.huawei.health.suggestion.ui.fitness.helper.GuideHelper, com.huawei.health.suggestion.ui.fitness.helper.inter.VoiceGuideInterface
    public VoiceGuideInterface firstMotion(Motion motion) {
        return a(9, motion, 1);
    }

    @Override // com.huawei.health.suggestion.ui.fitness.helper.GuideHelper, com.huawei.health.suggestion.ui.fitness.helper.inter.VoiceGuideInterface
    public VoiceGuideInterface nextMotion(Motion motion) {
        a(false);
        return a(9, motion, 2);
    }

    @Override // com.huawei.health.suggestion.ui.fitness.helper.GuideHelper, com.huawei.health.suggestion.ui.fitness.helper.inter.VoiceGuideInterface
    public VoiceGuideInterface preMotion(Motion motion) {
        a(false);
        return a(9, motion, 3);
    }

    @Override // com.huawei.health.suggestion.ui.fitness.helper.GuideHelper, com.huawei.health.suggestion.ui.fitness.helper.inter.VoiceGuideInterface
    public VoiceGuideInterface lastMotion(Motion motion) {
        return a(9, motion, 4);
    }

    private VoiceGuideInterface a(int i, Motion motion, int i2) {
        Motion motion2 = this.g;
        if (motion2 != null && TextUtils.equals(motion2.acquireId(), motion.acquireId())) {
            LogUtil.h("Suggestion_MultilingualGuideHelper", "changeAction() motion is same");
            return this;
        }
        if (motion == null || this.e == null) {
            LogUtil.h("Suggestion_MultilingualGuideHelper", "changeAction() motion or mPlayer is null");
            return this;
        }
        this.h = i;
        b(motion);
        a(h(i2), motion);
        return this;
    }

    private FitnessRunAudioScenarioId h(int i) {
        if (!"timer".equals(a().acquireMotionType())) {
            return a(i);
        }
        return e(i);
    }

    private FitnessRunAudioScenarioId a(int i) {
        if (i == 1) {
            return FitnessRunAudioScenarioId.FIRST_ACTION_TIME;
        }
        if (i == 2) {
            return FitnessRunAudioScenarioId.NEXT_ACTION_TIME;
        }
        if (i == 3) {
            return FitnessRunAudioScenarioId.PRE_ACTION_TIME;
        }
        if (i == 4) {
            return FitnessRunAudioScenarioId.LAST_ACTION_TIME;
        }
        LogUtil.h("Suggestion_MultilingualGuideHelper", "getActionTimeScenarioId() changeType:", Integer.valueOf(i));
        return null;
    }

    private FitnessRunAudioScenarioId e(int i) {
        if (i == 1) {
            return FitnessRunAudioScenarioId.FIRST_ACTION_SEC;
        }
        if (i == 2) {
            return FitnessRunAudioScenarioId.NEXT_ACTION_SEC;
        }
        if (i == 3) {
            return FitnessRunAudioScenarioId.PRE_ACTION_SEC;
        }
        if (i == 4) {
            return FitnessRunAudioScenarioId.LAST_ACTION_SEC;
        }
        LogUtil.h("Suggestion_MultilingualGuideHelper", "getActionSecScenarioId() changeType:", Integer.valueOf(i));
        return null;
    }

    @Override // com.huawei.health.suggestion.ui.fitness.helper.GuideHelper, com.huawei.health.suggestion.ui.fitness.helper.inter.VoiceGuideInterface
    public VoiceGuideInterface voiceGuideRest() {
        if (this.e != null) {
            this.h = 6;
            c(FitnessRunAudioScenarioId.TAKE_A_BREAK);
        }
        return this;
    }

    @Override // com.huawei.health.suggestion.ui.fitness.helper.GuideHelper, com.huawei.health.suggestion.ui.fitness.helper.inter.VoiceGuideInterface
    public VoiceGuideInterface restEnd(int i) {
        if (this.e != null) {
            this.h = 17;
            c(FitnessRunAudioScenarioId.BREAK_OVER);
        }
        return this;
    }

    @Override // com.huawei.health.suggestion.ui.fitness.helper.GuideHelper
    public VoiceGuideInterface b(boolean z, Motion motion) {
        if (motion == null) {
            LogUtil.h("Suggestion_MultilingualGuideHelper", "restEnd() motion is null");
            return this;
        }
        if (z) {
            this.h = 17;
        } else {
            this.h = 27;
        }
        b(motion);
        if (this.e != null) {
            c(FitnessRunAudioScenarioId.BREAK_OVER);
        }
        return this;
    }

    @Override // com.huawei.health.suggestion.ui.fitness.helper.GuideHelper, com.huawei.health.suggestion.ui.fitness.helper.inter.VoiceGuideInterface
    public VoiceGuideInterface wellDone() {
        this.h = -1;
        if (this.e != null) {
            d(1);
            aBX_().sendEmptyMessage(104);
            c(FitnessRunAudioScenarioId.CONGRATULATE_COMPLETE_TRAIN);
        }
        return this;
    }

    @Override // com.huawei.health.suggestion.ui.fitness.helper.GuideHelper
    public void d() {
        LogUtil.c("Suggestion_MultilingualGuideHelper", "doComplete()", a().acquireName());
        this.f++;
        int i = this.h;
        if (i == 0 || i == 9) {
            l();
        }
        if (this.f == this.i.size()) {
            LogUtil.h("Suggestion_MultilingualGuideHelper", "doComplete() action node");
            c();
        } else if (!koq.d(this.i, this.f)) {
            LogUtil.h("Suggestion_MultilingualGuideHelper", "doComplete() mVoiceList out bounds");
        } else {
            setSdSources(this.i.get(this.f));
            start();
        }
    }

    private void l() {
        LogUtil.a("Suggestion_MultilingualGuideHelper", "countDownHandler:", Integer.valueOf(this.f));
        if (this.f == f(4) - 1) {
            aBX_().sendEmptyMessage(3);
            return;
        }
        if (this.f == f(3) - 1) {
            aBX_().sendEmptyMessage(2);
            return;
        }
        if (this.f == f(2) - 1) {
            aBX_().sendEmptyMessage(1);
        } else if (this.f == f(1) - 1) {
            aBX_().sendEmptyMessage(0);
        } else {
            LogUtil.h("Suggestion_MultilingualGuideHelper", "countDownHandler() default", Integer.valueOf(this.f));
        }
    }

    private int f(int i) {
        List<String> list = this.i;
        int i2 = 0;
        if (list == null || list.size() < i) {
            return 0;
        }
        for (int size = this.i.size() - 1; size >= 0; size--) {
            if (this.i.get(size) != null && !this.i.get(size).endsWith("empty.mp3")) {
                i2++;
            }
            if (i2 == i) {
                LogUtil.a("Suggestion_MultilingualGuideHelper", "getNonEmptyIndex:", Integer.valueOf(size), " num:", Integer.valueOf(i));
                return size;
            }
        }
        return this.i.size() - 1;
    }

    private void c() {
        int i = this.h;
        if (i == 0 || i == 9) {
            this.h = -1;
            this.f = 0;
            this.i.clear();
            aBX_().sendEmptyMessage(101);
            return;
        }
        if (i == 17) {
            nextMotion(a());
        } else if (i == 27) {
            lastMotion(a());
        } else {
            LogUtil.h("Suggestion_MultilingualGuideHelper", "actionNode() mCurrentMultilingualProgress:", Integer.valueOf(i));
        }
    }

    private void c(FitnessRunAudioScenarioId fitnessRunAudioScenarioId) {
        if (fitnessRunAudioScenarioId == null) {
            LogUtil.h("Suggestion_MultilingualGuideHelper", "playVoicesPath() scenarioId is null");
            return;
        }
        mwz mwzVar = new mwz();
        this.f = 0;
        this.i.clear();
        this.i.addAll(mxb.a().getScenarioAudioPaths(fitnessRunAudioScenarioId.getId(), mwzVar));
        if (koq.b(this.i)) {
            LogUtil.h("Suggestion_MultilingualGuideHelper", "playSingleVoicePath() mVoiceList is null");
        } else {
            setSdSources(this.i.get(0));
            start();
        }
    }

    private void a(FitnessRunAudioScenarioId fitnessRunAudioScenarioId, Motion motion) {
        if (fitnessRunAudioScenarioId == null || motion == null) {
            LogUtil.h("Suggestion_MultilingualGuideHelper", "playVoicesPath() scenarioId or motion is null");
            return;
        }
        mwz mwzVar = new mwz();
        mwzVar.d(motion.acquireNamePath());
        mwzVar.b(Integer.valueOf(motion.acquireGroups()));
        mwzVar.b(Integer.valueOf(motion.acquireRepeat()));
        this.i.clear();
        this.f = 0;
        this.i.addAll(mxb.a().getScenarioAudioPaths(fitnessRunAudioScenarioId.getId(), mwzVar));
        if (koq.b(this.i) || !koq.d(this.i, this.f)) {
            LogUtil.h("Suggestion_MultilingualGuideHelper", "playVoicesPath() mVoiceList is null or out bounds");
            return;
        }
        LogUtil.a("Suggestion_MultilingualGuideHelper", "playVoicesPath:", fitnessRunAudioScenarioId.getId(), " ", moj.e(this.i));
        setSdSources(this.i.get(this.f));
        start();
    }
}
