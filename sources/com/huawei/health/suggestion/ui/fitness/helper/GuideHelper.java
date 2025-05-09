package com.huawei.health.suggestion.ui.fitness.helper;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Handler;
import android.text.TextUtils;
import com.huawei.basefitnessadvice.callback.OnFitnessStatusChangeCallback;
import com.huawei.health.suggestion.h5pro.AudioConstant;
import com.huawei.health.suggestion.ui.fitness.helper.inter.VideoInterface;
import com.huawei.health.suggestion.ui.fitness.helper.inter.VoiceGuideInterface;
import com.huawei.health.suggestion.ui.fitness.module.Motion;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.squ;
import health.compact.a.utils.StringUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes4.dex */
public class GuideHelper extends MediaHelper implements VoiceGuideInterface, MediaPlayer.OnCompletionListener {
    private int f;
    private List<String> g;
    private int h;
    private ArrayList<String> i;
    private int j;
    private boolean k;
    private Motion l;
    private Handler m;
    private OnFitnessStatusChangeCallback n;
    private boolean o;
    private int p;
    private int s;

    /* loaded from: classes8.dex */
    public interface GuideProgress {
        public static final int COUNTDOWN_0 = 35;
        public static final int COUNTDOWN_1 = 25;
        public static final int COUNTDOWN_2 = 15;
        public static final int COUNTDOWN_3 = 5;
        public static final int EACH_GROUP = 42;
        public static final int FIRST_GROUP = 14;
        public static final int FIRST_MOTION = 0;
        public static final int GROUP = 12;
        public static final int GROUP_NUM = 2;
        public static final int MOTION_NAME = 1;
        public static final int MOTION_POINT = 4;
        public static final int NEXT_GROUP = 8;
        public static final int NEXT_MOTION = 9;
        public static final int REST_END_LAST_MOTION = 27;
        public static final int REST_END_NEXT_GROUP = 7;
        public static final int REST_END_NEXT_MOTION = 17;
        public static final int START_REST = 6;
        public static final int TIMES = 3;
        public static final int TIMES_NUM = 22;

        void guideProgress();
    }

    @Override // com.huawei.health.suggestion.ui.fitness.helper.inter.VoiceGuideInterface
    public VoiceGuideInterface nextGroup() {
        return this;
    }

    @Override // com.huawei.health.suggestion.ui.fitness.helper.inter.VoiceGuideInterface
    public VoiceGuideInterface voiceGuideStop() {
        return this;
    }

    public void d(int i) {
        this.f = i;
    }

    public void a(boolean z) {
        this.k = z;
    }

    public Handler aBX_() {
        return this.m;
    }

    public Motion a() {
        return this.l;
    }

    public void b(Motion motion) {
        this.l = motion;
    }

    private GuideHelper(Context context) {
        super(context);
        this.f = 0;
        this.h = -1;
        this.i = new ArrayList<>(10);
        this.g = new ArrayList(4);
        this.o = true;
        this.f3166a = false;
        this.e.setLooping(false);
        this.e.setAudioStreamType(3);
        q();
    }

    public GuideHelper(Context context, Handler handler) {
        this(context);
        this.m = handler;
        this.e.setOnCompletionListener(this);
    }

    @Override // com.huawei.health.suggestion.ui.fitness.helper.MediaHelper, com.huawei.health.suggestion.ui.fitness.helper.inter.VideoInterface
    public VideoInterface setSdSources(String... strArr) {
        super.setSdSources(strArr);
        return this;
    }

    @Override // com.huawei.health.suggestion.ui.fitness.helper.inter.VoiceGuideInterface
    public VoiceGuideInterface voiceGuidePause() {
        this.k = true;
        LogUtil.c("Suggestion_GuiderHelper", "VoiceGuideInterface deviceGuidePause");
        if (this.e != null && this.e.isPlaying()) {
            this.e.pause();
            this.o = true;
        } else {
            LogUtil.h("Suggestion_GuiderHelper", "Pause fail，is not playing");
            this.o = false;
        }
        return this;
    }

    @Override // com.huawei.health.suggestion.ui.fitness.helper.inter.VoiceGuideInterface
    public VoiceGuideInterface voiceGuideContinue() {
        if (this.k) {
            this.k = false;
            LogUtil.c("Suggestion_GuiderHelper", "VoiceGuideInterface continue");
            if (this.e != null && this.o) {
                this.e.start();
            } else {
                LogUtil.c("Suggestion_GuiderHelper", "Continue to the next guide voice manually");
                onCompletion(this.e);
            }
        }
        return this;
    }

    @Override // com.huawei.health.suggestion.ui.fitness.helper.inter.VoiceGuideInterface
    public VoiceGuideInterface firstMotion(Motion motion) {
        if (motion == null) {
            LogUtil.b("Suggestion_GuiderHelper", "firstMotion motion == null");
            return this;
        }
        LogUtil.c("Suggestion_GuiderHelper", "play first motion");
        this.h = 0;
        this.l = motion;
        setSdSources(squ.a("E002", this.d, this.b, AudioConstant.AUDIO));
        start();
        return this;
    }

    @Override // com.huawei.health.suggestion.ui.fitness.helper.inter.VoiceGuideInterface
    public VoiceGuideInterface motionName(String str) {
        LogUtil.a("Suggestion_GuiderHelper", "play motion name", Boolean.valueOf(TextUtils.isEmpty(str)));
        String c = StringUtils.c((Object) str);
        this.h = 1;
        if (this.e != null) {
            if (a(c)) {
                setSdSources(c);
            } else {
                setSdSources(c + this.d + AudioConstant.AUDIO);
            }
            start();
        }
        return this;
    }

    private boolean a(String str) {
        return str.endsWith("m4a") || str.endsWith("aac") || str.endsWith("mp3");
    }

    @Override // com.huawei.health.suggestion.ui.fitness.helper.inter.VoiceGuideInterface
    public VoiceGuideInterface groupRepeats(int i, int i2) {
        this.h = 2;
        LogUtil.a("Suggestion_GuiderHelper", "play groupNumber and times");
        if (this.e != null) {
            a(i, i2);
        }
        return this;
    }

    private void a(int i, int i2) {
        ArrayList arrayList = new ArrayList(5);
        b(i, arrayList);
        if (i > 1) {
            arrayList.add(squ.a("E021", this.d, this.b, AudioConstant.AUDIO));
        }
        a(i2, arrayList);
        this.j = arrayList.size();
        if (i > 1) {
            arrayList.add(squ.a("B145", this.d, this.b, AudioConstant.AUDIO));
        }
        a(arrayList);
        LogUtil.a("Suggestion_GuiderHelper", "playerNotNull", arrayList);
        start();
    }

    private void a(int i, List<String> list) {
        list.addAll(c(i));
        c(list);
    }

    private void c(List<String> list) {
        Motion motion = this.l;
        if (motion == null) {
            LogUtil.b("Suggestion_GuiderHelper", "addUnitPath mMotion == null");
        } else if (!"timer".equals(motion.acquireMotionType())) {
            list.add(squ.a("C030", this.d, this.b, AudioConstant.AUDIO));
        } else {
            list.add(squ.a("C003", this.d, this.b, AudioConstant.AUDIO));
        }
    }

    private void b(int i, List<String> list) {
        if (Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8").contains(String.valueOf(i))) {
            list.add(squ.a("B" + (i + 152), this.d, this.b, AudioConstant.AUDIO));
        } else {
            list.addAll(c(i));
            list.add(squ.a("C001", this.d, this.b, AudioConstant.AUDIO));
        }
    }

    @Override // com.huawei.health.suggestion.ui.fitness.helper.inter.VoiceGuideInterface
    public VoiceGuideInterface motionPoint(String str) {
        LogUtil.a("Suggestion_GuiderHelper", "play motionPoint");
        if (this.e != null) {
            setSdSources(str);
            start();
        }
        return this;
    }

    @Override // com.huawei.health.suggestion.ui.fitness.helper.inter.VoiceGuideInterface
    public VoiceGuideInterface countdownGo() {
        this.h = 5;
        LogUtil.c("Suggestion_GuiderHelper", "play 321GO");
        if (this.e != null) {
            a(this.g);
            start();
        }
        return this;
    }

    @Override // com.huawei.health.suggestion.ui.fitness.helper.inter.VoiceGuideInterface
    public VoiceGuideInterface voiceGuideRest() {
        LogUtil.a("Suggestion_GuiderHelper", "play take a rest");
        this.h = 6;
        if (this.e != null) {
            setSdSources(squ.a("E008", this.d, this.b, AudioConstant.AUDIO));
            start();
        }
        return this;
    }

    @Override // com.huawei.health.suggestion.ui.fitness.helper.inter.VoiceGuideInterface
    public VoiceGuideInterface restEnd(int i) {
        LogUtil.a("Suggestion_GuiderHelper", "play rest end, next motion");
        this.p = 0;
        this.s = 1;
        this.h = 7;
        if (this.e != null) {
            if (i < this.i.size()) {
                ArrayList arrayList = new ArrayList(2);
                arrayList.add(squ.a("E009", this.d, this.b, AudioConstant.AUDIO));
                arrayList.add(this.i.get(i));
                this.p = arrayList.size();
                a(arrayList);
                start();
            } else {
                ArrayList arrayList2 = new ArrayList(4);
                arrayList2.add(squ.a("E009", this.d, this.b, AudioConstant.AUDIO));
                arrayList2.add(squ.a("E022", this.d, this.b, AudioConstant.AUDIO));
                arrayList2.addAll(c(i));
                arrayList2.add(squ.a("C001", this.d, this.b, AudioConstant.AUDIO));
                this.p = arrayList2.size();
                a(arrayList2);
                start();
            }
        }
        return this;
    }

    public VoiceGuideInterface b(boolean z, Motion motion) {
        if (motion == null) {
            LogUtil.b("Suggestion_GuiderHelper", "restEnd motion == null");
            return this;
        }
        LogUtil.a("Suggestion_GuiderHelper", "play rest end, next motion");
        if (z) {
            this.h = 17;
        } else {
            this.h = 27;
        }
        this.l = motion;
        if (this.e != null) {
            setSdSources(squ.a("E009", this.d, this.b, AudioConstant.AUDIO));
            start();
        }
        return this;
    }

    @Override // com.huawei.health.suggestion.ui.fitness.helper.inter.VoiceGuideInterface
    public VoiceGuideInterface nextMotion(Motion motion) {
        if (motion == null) {
            LogUtil.b("Suggestion_GuiderHelper", "nextMotion motion == null");
            return this;
        }
        this.k = false;
        LogUtil.c("Suggestion_GuiderHelper", "play next motion");
        this.h = 9;
        if (this.e != null) {
            this.l = motion;
            setSdSources(squ.a("E019", this.d, this.b, AudioConstant.AUDIO));
            start();
        }
        return this;
    }

    @Override // com.huawei.health.suggestion.ui.fitness.helper.inter.VoiceGuideInterface
    public VoiceGuideInterface preMotion(Motion motion) {
        if (motion == null) {
            LogUtil.b("Suggestion_GuiderHelper", "preMotion motion == null");
            return this;
        }
        this.k = false;
        LogUtil.c("Suggestion_GuiderHelper", "play pre motion");
        this.h = 9;
        if (this.e != null) {
            this.l = motion;
            setSdSources(squ.a("E205", this.d, this.b, AudioConstant.AUDIO));
            start();
        }
        return this;
    }

    @Override // com.huawei.health.suggestion.ui.fitness.helper.inter.VoiceGuideInterface
    public VoiceGuideInterface lastMotion(Motion motion) {
        if (motion == null) {
            LogUtil.b("Suggestion_GuiderHelper", "lastMotion motion == null");
            return this;
        }
        LogUtil.a("Suggestion_GuiderHelper", "play last motion");
        this.h = 9;
        if (this.e != null) {
            this.l = motion;
            setSdSources(squ.a("E065", this.d, this.b, AudioConstant.AUDIO));
            start();
        }
        return this;
    }

    @Override // com.huawei.health.suggestion.ui.fitness.helper.inter.VoiceGuideInterface
    public VoiceGuideInterface wellDone() {
        LogUtil.c("Suggestion_GuiderHelper", "play VoiceGuideInterface wellDone");
        this.h = -1;
        if (this.e != null) {
            this.f = 1;
            this.m.sendEmptyMessage(104);
            setSdSources(squ.a("E068", this.d, this.b, AudioConstant.AUDIO));
            start();
        }
        return this;
    }

    @Override // com.huawei.health.suggestion.ui.fitness.helper.inter.VoiceGuideInterface
    public VoiceGuideInterface lastGroup() {
        LogUtil.c("Suggestion_GuiderHelper", "play lastGroup");
        this.h = 8;
        if (this.e != null) {
            setSdSources(squ.a("E070", this.d, this.b, AudioConstant.AUDIO));
            start();
        }
        return this;
    }

    @Override // com.huawei.health.suggestion.ui.fitness.helper.inter.VoiceGuideInterface
    public VoiceGuideInterface customGuide(String str) {
        LogUtil.c("Suggestion_GuiderHelper", "play guide");
        this.h = -1;
        if (this.e != null) {
            setSdSources(str);
            start();
        }
        return this;
    }

    @Override // android.media.MediaPlayer.OnCompletionListener
    public void onCompletion(MediaPlayer mediaPlayer) {
        LogUtil.a("Suggestion_GuiderHelper", "-------onCompletion---------");
        if (!this.k) {
            d();
        } else {
            this.o = false;
        }
        if (this.f == 1) {
            LogUtil.a("Suggestion_GuiderHelper", "send msg: Congratulations! Train completed");
            this.m.sendEmptyMessage(103);
            this.f = 0;
            OnFitnessStatusChangeCallback onFitnessStatusChangeCallback = this.n;
            if (onFitnessStatusChangeCallback != null) {
                onFitnessStatusChangeCallback.onUpdate();
                this.n = null;
            }
        }
    }

    public void d() {
        Motion motion = this.l;
        if (motion == null) {
            LogUtil.b("Suggestion_GuiderHelper", "doComplete mMotion == null");
            return;
        }
        int i = this.h;
        if (i == 0) {
            motionName(motion.acquireNamePath());
            this.m.sendEmptyMessage(154);
        } else if (i == 1) {
            groupRepeats(motion.acquireGroups(), this.l.acquireRepeat());
        } else {
            r();
        }
    }

    private void r() {
        this.j--;
        if (this.h == 2) {
            w();
        } else {
            u();
        }
    }

    private void u() {
        int i = this.h;
        if (i == 12) {
            this.h = 42;
            next();
        } else if (i == 42) {
            x();
        } else {
            v();
        }
    }

    private void v() {
        int i = this.h;
        if (i == 22) {
            if (this.j <= 1) {
                this.h = 3;
            }
            next();
        } else if (i == 3) {
            y();
        } else {
            p();
        }
    }

    private void p() {
        int i = this.h;
        if (i == 4) {
            LogUtil.c("Suggestion_GuiderHelper", "motion point finish");
            this.m.sendEmptyMessage(3);
            countdownGo();
            LogUtil.c("Suggestion_GuiderHelper", "321 go start");
            return;
        }
        if (i == 14) {
            this.m.sendEmptyMessage(3);
            countdownGo();
            LogUtil.c("Suggestion_GuiderHelper", "321 go start");
            return;
        }
        c();
    }

    private void c() {
        int i = this.h;
        if (i == 5) {
            this.m.sendEmptyMessage(2);
            this.h = 15;
            next();
        } else if (i == 15) {
            this.m.sendEmptyMessage(1);
            this.h = 25;
            next();
        } else {
            if (i == 25) {
                this.m.sendEmptyMessage(0);
                this.h = 35;
                next();
                return;
            }
            l();
        }
    }

    private void l() {
        int i = this.h;
        if (i == 35) {
            this.m.sendEmptyMessage(101);
            LogUtil.c("Suggestion_GuiderHelper", "countdown finish");
            this.h = -1;
        } else if (i == 7) {
            ac();
        } else {
            ad();
        }
    }

    private void y() {
        Motion motion = this.l;
        if (motion == null) {
            LogUtil.b("Suggestion_GuiderHelper", "progressTimes mMotion == null");
        } else if (!motion.acquireMotionType().equals("hotbody")) {
            s();
        } else {
            this.h = 4;
            motionPoint(this.l.acquireTrainPointPath());
        }
    }

    private void s() {
        Motion motion = this.l;
        if (motion == null) {
            LogUtil.b("Suggestion_GuiderHelper", "doTypeWhole mMotion == null");
            return;
        }
        if (motion.acquireGroups() > 1) {
            this.h = 14;
            next();
        } else {
            this.m.sendEmptyMessage(3);
            LogUtil.c("Suggestion_GuiderHelper", "doTypeWhole 321 go start");
            countdownGo();
        }
    }

    private void x() {
        t();
        next();
    }

    private void w() {
        Motion motion = this.l;
        if (motion == null) {
            LogUtil.b("Suggestion_GuiderHelper", "progressGroup mMotion == null");
            return;
        }
        if (motion.acquireGroups() > 8) {
            this.h = 12;
        } else if (this.l.acquireGroups() == 1) {
            t();
        } else {
            this.h = 42;
        }
        next();
    }

    private void t() {
        this.h = 22;
    }

    private void ac() {
        int i = this.s;
        this.s = i + 1;
        if (i < this.p) {
            next();
        } else {
            this.s = 1;
            countdownGo();
        }
    }

    private void ad() {
        Motion motion = this.l;
        if (motion == null) {
            LogUtil.b("Suggestion_GuiderHelper", "restEndMsg mMotion == null");
            return;
        }
        int i = this.h;
        if (i == 17) {
            nextMotion(motion);
            return;
        }
        if (i == 27) {
            lastMotion(motion);
        } else if (i == 9) {
            motionName(motion.acquireNamePath());
            this.m.sendEmptyMessage(154);
        }
    }

    private void q() {
        this.g.clear();
        this.g.add(squ.a("B180", this.d, this.b, AudioConstant.AUDIO));
        this.g.add(squ.a("B181", this.d, this.b, AudioConstant.AUDIO));
        this.g.add(squ.a("B182", this.d, this.b, AudioConstant.AUDIO));
        this.g.add(squ.a("D001", this.d, this.b, AudioConstant.AUDIO));
        this.i.clear();
        this.i.add(squ.a("B145", this.d, this.b, AudioConstant.AUDIO));
        this.i.add(squ.a("B146", this.d, this.b, AudioConstant.AUDIO));
        this.i.add(squ.a("B147", this.d, this.b, AudioConstant.AUDIO));
        this.i.add(squ.a("B148", this.d, this.b, AudioConstant.AUDIO));
        this.i.add(squ.a("B149", this.d, this.b, AudioConstant.AUDIO));
        this.i.add(squ.a("B150", this.d, this.b, AudioConstant.AUDIO));
        this.i.add(squ.a("B151", this.d, this.b, AudioConstant.AUDIO));
        this.i.add(squ.a("B152", this.d, this.b, AudioConstant.AUDIO));
    }

    @Override // com.huawei.health.suggestion.ui.fitness.helper.MediaHelper
    public void b(int i) {
        super.b(i);
        q();
    }

    public void a(OnFitnessStatusChangeCallback onFitnessStatusChangeCallback) {
        this.n = onFitnessStatusChangeCallback;
    }

    public int e() {
        return this.f;
    }
}
