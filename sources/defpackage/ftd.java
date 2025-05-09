package defpackage;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.ArrayMap;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.huawei.health.device.api.IndoorEquipManagerApi;
import com.huawei.health.sport.CourseUpdateListener;
import com.huawei.health.sport.model.CourseEnvParams;
import com.huawei.health.suggestion.h5pro.AudioConstant;
import com.huawei.health.suggestion.model.FitRunPlayAudio;
import com.huawei.health.suggestion.ui.voice.IFitRunVoiceContentConstructor;
import com.huawei.health.suggestion.ui.voice.helper.IntensityVoiceGuider;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.fitnessdatatype.HeartZoneConf;
import com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify;
import com.huawei.pluginfitnessadvice.AtomicAction;
import com.huawei.pluginfitnessadvice.Audio;
import com.huawei.pluginfitnessadvice.ChoreographedSingleAction;
import com.huawei.pluginfitnessadvice.Comment;
import com.huawei.pluginfitnessadvice.FitWorkout;
import com.huawei.pluginfitnessadvice.TargetConfig;
import health.compact.a.CommonUtil;
import health.compact.a.CommonUtils;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import health.compact.a.util.LogUtil;
import health.compact.a.utils.StringUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public class ftd implements CourseUpdateListener {
    private static volatile ftd d;
    private static final Object e = new Object();

    /* renamed from: a, reason: collision with root package name */
    private gfq f12640a;
    private CourseEnvParams b;
    private List<ChoreographedSingleAction> c;
    private HeartZoneConf i;
    private ffg q;
    private int x;
    private FitWorkout h = null;
    private Map<Integer, List<FitRunPlayAudio>> r = new HashMap();
    private int y = -1;
    private int s = 0;
    private int t = 0;
    private ghd g = null;
    private boolean o = false;
    private long j = 0;
    private boolean n = false;
    private int p = 0;
    private boolean m = false;
    private boolean k = false;
    private boolean l = false;
    private Map<Integer, IntensityVoiceGuider> f = new ArrayMap();

    private ftd() {
    }

    public void b(List<ChoreographedSingleAction> list, FitWorkout fitWorkout, boolean z, CourseEnvParams courseEnvParams) {
        m();
        this.c = list;
        this.h = fitWorkout;
        this.b = courseEnvParams;
        h();
        boolean b = mxb.a().b(BaseApplication.getContext(), "Sport");
        LogUtil.d("Suggestion_FitRunManager", "init() enableCurLang:", Boolean.valueOf(b));
        if (b) {
            j();
        } else {
            g();
        }
        this.o = false;
        this.j = 0L;
        this.g = new ghd(20000L);
        this.n = z;
        FitWorkout fitWorkout2 = this.h;
        if (fitWorkout2 != null) {
            this.l = fitWorkout2.isNewRunCourse();
            this.m = this.h.isMusicRun();
        }
        if (this.m) {
            fis.d().d("fitness_audio_player", "fitness_audio_player", 1);
        }
        this.q = this.b.g();
        this.i = this.b.c();
    }

    @Override // com.huawei.health.sport.CourseUpdateListener
    public void switchAction(int i) {
        if (i <= this.y) {
            LogUtil.c("Suggestion_FitRunManager", "switchAction with the error index:", Integer.valueOf(i));
            return;
        }
        this.y = i;
        if (koq.b(this.c, i)) {
            LogUtil.c("Suggestion_FitRunManager", "switchAction with the error index:", Integer.valueOf(this.y));
            return;
        }
        LogUtil.d("Suggestion_FitRunManager", "switchAction with the index:", Integer.valueOf(this.y));
        this.k = true;
        e(this.c.get(this.y), this.y, this.c.size());
        n();
    }

    @Override // com.huawei.health.sport.CourseUpdateListener
    public void seekToAction(int i) {
        if (i == this.y) {
            LogUtil.c("Suggestion_FitRunManager", "seek to action with the same index:", Integer.valueOf(i));
            return;
        }
        this.y = i;
        if (koq.b(this.c, i)) {
            LogUtil.c("Suggestion_FitRunManager", "seek to action with the error index:", Integer.valueOf(this.y));
            return;
        }
        LogUtil.d("Suggestion_FitRunManager", "seek to action with the index:", Integer.valueOf(this.y));
        o();
        n();
    }

    @Override // com.huawei.hwfoundationmodel.trackmodel.ISportDataCallback
    public void getSportInfo(Bundle bundle) {
        if (bundle == null) {
            LogUtil.e("Suggestion_FitRunManager", "sportInfo == null");
            return;
        }
        int i = bundle.getInt("sportState", 0);
        if (i == 2) {
            this.j = 0L;
            if (l()) {
                fis.d().c("fitness_audio_player", 1);
            }
            this.p = i;
        }
        if (i != 1) {
            LogUtil.c("Suggestion_FitRunManager", "sportState = ", Integer.valueOf(i));
            return;
        }
        int i2 = bundle.getInt("distance", 0);
        int i3 = bundle.getInt("duration", 0);
        this.x = i3;
        if (this.k) {
            this.s = i2;
            this.t = i3;
            this.k = false;
        }
        if (koq.b(this.c, this.y)) {
            LogUtil.c("Suggestion_FitRunManager", "mTargetIndex out of bounds. mTargetIndex = ", Integer.valueOf(this.y));
            return;
        }
        if (this.p == 2 && l()) {
            LogUtil.d("Suggestion_FitRunManager", "continue music");
            fis.d().c("fitness_audio_player", 2);
        }
        this.p = i;
        int i4 = bundle.getInt(IndoorEquipManagerApi.KEY_HEART_RATE, 0);
        int i5 = this.s;
        int i6 = this.x - this.t;
        int c = moe.c(i6);
        float c2 = moe.c(bundle.getFloat("speed", 0.0f));
        d(this.y, i2 - i5, c, i4, c2);
        ChoreographedSingleAction choreographedSingleAction = this.c.get(this.y);
        if (!l()) {
            c(choreographedSingleAction, c2, i4, bundle.getInt("stepRate"), i6);
        }
        e(bundle.getLong("heartRateTime"));
    }

    @Override // com.huawei.hwfoundationmodel.trackmodel.ISportDataCallback
    public void onSummary(MotionPathSimplify motionPathSimplify) {
        i();
    }

    public boolean d() {
        CourseEnvParams courseEnvParams = this.b;
        if (courseEnvParams != null) {
            return courseEnvParams.f();
        }
        return false;
    }

    public boolean a() {
        return this.l;
    }

    public HeartZoneConf b() {
        return this.i;
    }

    private void g() {
        if (this.f12640a != null) {
            return;
        }
        if (LanguageUtil.j(BaseApplication.getContext())) {
            FitWorkout fitWorkout = this.h;
            if (fitWorkout != null) {
                this.f12640a = new gfq(AudioConstant.WOMAN, FitWorkout.acquireComeFrom(fitWorkout.acquireId(), this.h.getCourseDefineType()), AudioConstant.AUDIO);
                return;
            } else {
                this.f12640a = new gfq(AudioConstant.WOMAN, "", AudioConstant.AUDIO);
                return;
            }
        }
        FitWorkout fitWorkout2 = this.h;
        if (fitWorkout2 != null) {
            this.f12640a = new gfq("M", FitWorkout.acquireComeFrom(fitWorkout2.acquireId(), this.h.getCourseDefineType()), AudioConstant.AUDIO);
        } else {
            this.f12640a = new gfq("M", "", AudioConstant.AUDIO);
        }
    }

    private void j() {
        if (this.f12640a != null) {
            return;
        }
        if (LanguageUtil.j(BaseApplication.getContext())) {
            FitWorkout fitWorkout = this.h;
            if (fitWorkout != null) {
                this.f12640a = new gfr(AudioConstant.WOMAN, FitWorkout.acquireComeFrom(fitWorkout.acquireId(), this.h.getCourseDefineType()), AudioConstant.AUDIO);
                return;
            } else {
                this.f12640a = new gfr(AudioConstant.WOMAN, "", AudioConstant.AUDIO);
                return;
            }
        }
        FitWorkout fitWorkout2 = this.h;
        if (fitWorkout2 != null) {
            this.f12640a = new gfr("M", FitWorkout.acquireComeFrom(fitWorkout2.acquireId(), this.h.getCourseDefineType()), AudioConstant.AUDIO);
        } else {
            this.f12640a = new gfr("M", "", AudioConstant.AUDIO);
        }
    }

    private void h() {
        this.r.clear();
        for (int i = 0; i < this.c.size(); i++) {
            ChoreographedSingleAction choreographedSingleAction = this.c.get(i);
            if (choreographedSingleAction != null) {
                ArrayList arrayList = new ArrayList();
                List<Comment> commentaryTraining = choreographedSingleAction.getCommentaryTraining();
                if (commentaryTraining != null) {
                    for (Comment comment : commentaryTraining) {
                        if (comment != null) {
                            String acquirePlayType = comment.acquirePlayType();
                            String acquirePlayValue = comment.acquirePlayValue();
                            String acquireName = comment.acquireName();
                            String id = comment.getId();
                            FitRunPlayAudio a2 = a(acquirePlayValue);
                            a2.saveAudioId(id);
                            a2.saveAudioUrl(acquireName);
                            a2.savePlayType(acquirePlayType);
                            arrayList.add(a2);
                        }
                    }
                    this.r.put(Integer.valueOf(i), arrayList);
                }
            }
        }
    }

    private void m() {
        this.h = null;
        this.y = -1;
        this.r.clear();
        this.f12640a = null;
        this.f.clear();
        this.s = 0;
        this.t = 0;
        this.n = false;
        this.c = null;
        this.l = false;
    }

    public static ftd e() {
        if (d == null) {
            synchronized (e) {
                if (d == null) {
                    d = new ftd();
                }
            }
        }
        return d;
    }

    private boolean l() {
        return this.m;
    }

    private void n() {
        Iterator<IntensityVoiceGuider> it = this.f.values().iterator();
        while (it.hasNext()) {
            it.next().reset();
        }
    }

    private void e(long j) {
        if (this.j <= 0) {
            this.j = System.currentTimeMillis();
        }
        d(Math.max(j, this.j));
    }

    private void d(long j) {
        if (this.n) {
            this.g.b(j);
            if (this.g.d(System.currentTimeMillis()) == 1) {
                if (!this.o) {
                    LogUtil.d("Suggestion_FitRunManager", " heartRateDevice HeartRateDeviceError");
                    e(f().getHeartRateDeviceError());
                }
                this.o = true;
                return;
            }
            if (this.o) {
                LogUtil.d("Suggestion_FitRunManager", " heartRateDevice getHeartRateDeviceConnected");
                e(f().getHeartRateDeviceConnected());
            }
            this.o = false;
        }
    }

    private void e(ChoreographedSingleAction choreographedSingleAction, int i, int i2) {
        LogUtil.d("Suggestion_FitRunManager", "change index = ", Integer.valueOf(i), "  size=", Integer.valueOf(i2));
        if (choreographedSingleAction.getAction() == null) {
            LogUtil.e("Suggestion_FitRunManager", "change workoutAction.getAction() == null");
            return;
        }
        if (l() && choreographedSingleAction.getAction().getExtendProperty() != null) {
            Audio audio = (Audio) new Gson().fromJson(StringUtils.c((Object) choreographedSingleAction.getAction().getExtendProperty().get("actionMusic")), Audio.class);
            if (audio != null) {
                fis.d().d("fitness_audio_player", squ.e(audio.getUrl()), "run background music");
                onChange(this.b.i());
                return;
            }
            return;
        }
        if (i == 0) {
            c(choreographedSingleAction);
        } else if (i == i2 - 1) {
            a(choreographedSingleAction);
        } else if (i < i2) {
            b(choreographedSingleAction);
        }
    }

    private void b(ChoreographedSingleAction choreographedSingleAction) {
        LogUtil.d("Suggestion_FitRunManager", "changeNextAction workoutAction = ", new Gson().toJson(choreographedSingleAction));
        String j = j(choreographedSingleAction);
        int h = CommonUtils.h(choreographedSingleAction.getTargetConfig().getId());
        b(f().getChangeNextAction(j, b(h, ggs.d(choreographedSingleAction)), h, choreographedSingleAction.getIntensityConfig()), "immediately_play");
    }

    private void a(ChoreographedSingleAction choreographedSingleAction) {
        LogUtil.d("Suggestion_FitRunManager", "changeLastAction workoutAction = ", choreographedSingleAction);
        String j = j(choreographedSingleAction);
        int h = CommonUtils.h(choreographedSingleAction.getTargetConfig().getId());
        b(f().getChangeLastAction(j, b(h, ggs.d(choreographedSingleAction)), h, choreographedSingleAction.getIntensityConfig()), "immediately_play");
    }

    private int b(int i, int i2) {
        return (i == 5 || i == 4) ? (int) UnitUtil.a(ghp.c(this.i, i2), 0) : i2;
    }

    private String j(ChoreographedSingleAction choreographedSingleAction) {
        AtomicAction action = choreographedSingleAction.getAction();
        if (action == null || action.getExtendProperty() == null) {
            LogUtil.e("Suggestion_FitRunManager", "getWorkoutActionNameAudios action == null");
            return d(choreographedSingleAction);
        }
        String str = action.getExtendProperty().get("audios");
        if (TextUtils.isEmpty(str)) {
            LogUtil.e("Suggestion_FitRunManager", "getWorkoutActionNameAudios audios null");
            return d(choreographedSingleAction);
        }
        List list = (List) new Gson().fromJson(str, new TypeToken<List<Audio>>() { // from class: ftd.4
        }.getType());
        if (koq.b(list)) {
            LogUtil.e("Suggestion_FitRunManager", "getWorkoutActionNameAudios CollectionUtils.isEmpty(audios)");
            return d(choreographedSingleAction);
        }
        return ((Audio) list.get(0)).getUrl();
    }

    private String d(ChoreographedSingleAction choreographedSingleAction) {
        if (choreographedSingleAction == null) {
            LogUtil.e("Suggestion_FitRunManager", "getActionId workoutAction is null.");
            return "";
        }
        AtomicAction action = choreographedSingleAction.getAction();
        if (action == null) {
            LogUtil.e("Suggestion_FitRunManager", "getActionId action is null.");
            return "";
        }
        String id = action.getId();
        LogUtil.d("Suggestion_FitRunManager", "getActionId actionId = ", id);
        return id;
    }

    private void c(ChoreographedSingleAction choreographedSingleAction) {
        LogUtil.d("Suggestion_FitRunManager", "changeFirstAction workoutAction = ", choreographedSingleAction.toString());
        String j = j(choreographedSingleAction);
        int h = CommonUtils.h(choreographedSingleAction.getTargetConfig().getId());
        e(f().getChangeFirstAction(j, b(h, ggs.d(choreographedSingleAction)), h, choreographedSingleAction.getIntensityConfig()));
    }

    private static FitRunPlayAudio a(String str) {
        FitRunPlayAudio fitRunPlayAudio = new FitRunPlayAudio();
        if (str == null || str.length() < 3) {
            LogUtil.e("Suggestion_FitRunManager", "(playValue == null) || (playValue.length() < 3) ");
            return fitRunPlayAudio;
        }
        try {
            fitRunPlayAudio.savePlayValue(Integer.parseInt(str.substring(2)));
            fitRunPlayAudio.saveOpportunity(str.substring(1, 2));
            return fitRunPlayAudio;
        } catch (NumberFormatException unused) {
            LogUtil.e("Suggestion_FitRunManager", "parsePlayValue parse exception");
            return fitRunPlayAudio;
        }
    }

    private void d(int i, int i2, int i3, int i4, float f) {
        LogUtil.b("Suggestion_FitRunManager", "speakAside");
        List<FitRunPlayAudio> list = this.r.get(Integer.valueOf(i));
        if (koq.b(list)) {
            LogUtil.e("Suggestion_FitRunManager", "speakAside CollectionUtils.isEmpty(fitRunPlayAudios)");
            return;
        }
        for (FitRunPlayAudio fitRunPlayAudio : list) {
            if (fitRunPlayAudio != null) {
                e(fitRunPlayAudio, i2, i3, i4, f);
            }
        }
    }

    private void e(FitRunPlayAudio fitRunPlayAudio, int i, int i2, int i3, float f) {
        LogUtil.b("Suggestion_FitRunManager", "speakAside fitRunPlayAudio=", fitRunPlayAudio);
        if (fitRunPlayAudio.acquireIsPlay()) {
            return;
        }
        String acquirePlayType = fitRunPlayAudio.acquirePlayType();
        if (FitRunPlayAudio.PLAY_TYPE_D.equals(acquirePlayType)) {
            c(fitRunPlayAudio, i);
            return;
        }
        if ("h".equals(acquirePlayType)) {
            c(fitRunPlayAudio, i3);
        } else if (FitRunPlayAudio.PLAY_TYPE_T.equals(acquirePlayType)) {
            c(fitRunPlayAudio, i2);
        } else if (FitRunPlayAudio.PLAY_TYPE_V.equals(acquirePlayType)) {
            e(fitRunPlayAudio, f);
        }
    }

    private void c(FitRunPlayAudio fitRunPlayAudio, int i) {
        float acquirePlayValue = fitRunPlayAudio.acquirePlayValue();
        String acquireOpportunity = fitRunPlayAudio.acquireOpportunity();
        if ("e".equals(acquireOpportunity)) {
            if (acquirePlayValue == i) {
                String acquireAudioUrl = fitRunPlayAudio.acquireAudioUrl();
                fitRunPlayAudio.saveIsPlay(true);
                b(acquireAudioUrl);
                return;
            }
            return;
        }
        if (FitRunPlayAudio.OPPORTUNITY_M.equals(acquireOpportunity)) {
            if (acquirePlayValue > i) {
                String acquireAudioUrl2 = fitRunPlayAudio.acquireAudioUrl();
                fitRunPlayAudio.saveIsPlay(true);
                b(acquireAudioUrl2);
                return;
            }
            return;
        }
        if (!"p".equals(acquireOpportunity) || acquirePlayValue >= i) {
            return;
        }
        String acquireAudioUrl3 = fitRunPlayAudio.acquireAudioUrl();
        fitRunPlayAudio.saveIsPlay(true);
        b(acquireAudioUrl3);
    }

    private void e(FitRunPlayAudio fitRunPlayAudio, float f) {
        float acquirePlayValue = fitRunPlayAudio.acquirePlayValue();
        String acquireOpportunity = fitRunPlayAudio.acquireOpportunity();
        if ("e".equals(acquireOpportunity)) {
            if (acquirePlayValue == f) {
                String acquireAudioUrl = fitRunPlayAudio.acquireAudioUrl();
                fitRunPlayAudio.saveIsPlay(true);
                b(acquireAudioUrl);
                return;
            }
            return;
        }
        if (FitRunPlayAudio.OPPORTUNITY_M.equals(acquireOpportunity)) {
            if (acquirePlayValue > f) {
                String acquireAudioUrl2 = fitRunPlayAudio.acquireAudioUrl();
                fitRunPlayAudio.saveIsPlay(true);
                b(acquireAudioUrl2);
                return;
            }
            return;
        }
        if (!"p".equals(acquireOpportunity) || acquirePlayValue >= f) {
            return;
        }
        String acquireAudioUrl3 = fitRunPlayAudio.acquireAudioUrl();
        fitRunPlayAudio.saveIsPlay(true);
        b(acquireAudioUrl3);
    }

    private void b(String str) {
        String a2 = gfs.a(str);
        LogUtil.d("Suggestion_FitRunManager", "broadcastSpeakAside audioUrl = ", str, "audiosFilePath = ", a2);
        k();
        e(a2);
    }

    private IFitRunVoiceContentConstructor f() {
        return this.f12640a;
    }

    public ffg c() {
        return this.q;
    }

    public void a(int i) {
        LogUtil.d("Suggestion_FitRunManager", "play pull up course broadcast with type=", Integer.valueOf(i));
        Object constructContent = gfu.e().constructContent(101, Integer.valueOf(i));
        if (koq.e(constructContent, Integer.class)) {
            fps.b(BaseApplication.getContext(), new ArrayList((List) constructContent));
        }
    }

    private void e(Object obj) {
        b(obj, (String) null);
    }

    private void b(Object obj, String str) {
        if (UnitUtil.h() || !eme.b().getVoiceEnable()) {
            return;
        }
        if (obj == null) {
            LogUtil.e("Suggestion_FitRunManager", "playSound sound == null");
        } else {
            fis.d().a(obj, str);
        }
    }

    private void c(ChoreographedSingleAction choreographedSingleAction, float f, int i, int i2, int i3) {
        if (choreographedSingleAction == null || choreographedSingleAction.getIntensityConfig() == null) {
            LogUtil.c("Suggestion_FitRunManager", "guide workoutAction is null");
            return;
        }
        if (CommonUtil.c(choreographedSingleAction.getIntensityConfig().getValueL()) && CommonUtil.c(choreographedSingleAction.getIntensityConfig().getValueH())) {
            LogUtil.c("Suggestion_FitRunManager", "guide value == zero");
            return;
        }
        if (i3 < 30) {
            LogUtil.d("Suggestion_FitRunManager", "action duration too short,", Integer.valueOf(i3));
            return;
        }
        int e2 = e(choreographedSingleAction);
        if (e2 != 0) {
            if (e2 != 1 && e2 != 2) {
                if (e2 == 3) {
                    e(0, choreographedSingleAction.getIntensityConfig(), f);
                    e(1, choreographedSingleAction.getIntensityConfig(), i);
                    return;
                } else if (e2 != 4 && e2 != 7 && e2 != 8 && e2 != 9) {
                    if (e2 != 13 && e2 != 15) {
                        a(e2, choreographedSingleAction, f, i, i2);
                        return;
                    }
                }
            }
            if (this.x < 300) {
                return;
            }
            e(e2, choreographedSingleAction.getIntensityConfig(), i);
            return;
        }
        e(e2, choreographedSingleAction.getIntensityConfig(), f);
    }

    private int e(ChoreographedSingleAction choreographedSingleAction) {
        int h = CommonUtils.h(choreographedSingleAction.getIntensityConfig().getId());
        return h == 17 ? this.i.getClassifyMethod() == 0 ? 4 : 8 : h;
    }

    private void a(int i, ChoreographedSingleAction choreographedSingleAction, float f, int i2, int i3) {
        if (i == 5) {
            e(0, choreographedSingleAction.getIntensityConfig(), f);
            e(2, choreographedSingleAction.getIntensityConfig(), i2);
        } else if (i == 6) {
            e(0, choreographedSingleAction.getIntensityConfig(), f);
            e(4, choreographedSingleAction.getIntensityConfig(), f);
        } else if (i == 14) {
            d(choreographedSingleAction, i2, f);
        } else {
            if (i != 16) {
                return;
            }
            e(i, choreographedSingleAction.getIntensityConfig(), i3);
        }
    }

    private void d(ChoreographedSingleAction choreographedSingleAction, int i, float f) {
        if (d()) {
            if (this.x < 300) {
                return;
            }
            e(8, choreographedSingleAction.getIntensityConfig(), i);
            return;
        }
        e(13, choreographedSingleAction.getIntensityConfig(), f);
    }

    private void e(int i, TargetConfig targetConfig, float f) {
        IntensityVoiceGuider intensityVoiceGuider = this.f.get(Integer.valueOf(i));
        if (intensityVoiceGuider == null) {
            intensityVoiceGuider = e(i);
            if (intensityVoiceGuider == null) {
                return;
            } else {
                this.f.put(Integer.valueOf(i), intensityVoiceGuider);
            }
        }
        intensityVoiceGuider.guide(targetConfig, f);
    }

    private IntensityVoiceGuider e(int i) {
        if (i == 0) {
            return new ggc(f());
        }
        if (i != 1) {
            if (i == 2) {
                return new gfy(f());
            }
            if (i != 4) {
                if (i == 13) {
                    gfv gfvVar = new gfv(f());
                    gfvVar.b(this.q);
                    return gfvVar;
                }
                if (i != 7 && i != 8) {
                    if (i == 9) {
                        return new gfw(f());
                    }
                    if (i == 15) {
                        return new gfx(f());
                    }
                    if (i != 16) {
                        return null;
                    }
                    return new ggb(f());
                }
            }
        }
        gfz gfzVar = new gfz(f());
        gfzVar.b(this.i);
        return gfzVar;
    }

    private void k() {
        ash.a("run_course_voice_action_start_time", String.valueOf(this.t));
        ash.a("run_course_voice_action_start_distance", String.valueOf(this.s));
        ash.a("run_course_voice_speak_aside_cache", nrv.a(this.r));
    }

    private void o() {
        Map<Integer, List<FitRunPlayAudio>> emptyMap;
        this.s = CommonUtil.h(ash.b("run_course_voice_action_start_distance"));
        this.t = CommonUtil.h(ash.b("run_course_voice_action_start_time"));
        try {
            emptyMap = (Map) nrv.c(ash.b("run_course_voice_speak_aside_cache"), new TypeToken<ArrayMap<Integer, List<FitRunPlayAudio>>>() { // from class: ftd.5
            }.getType());
        } catch (JsonSyntaxException unused) {
            LogUtil.e("Suggestion_FitRunManager", "JsonSyntaxException");
            emptyMap = Collections.emptyMap();
        }
        if (emptyMap == null || this.r == null || emptyMap.size() != this.r.size()) {
            return;
        }
        this.r = emptyMap;
    }

    private void i() {
        ash.d("run_course_voice_action_start_time");
        ash.d("run_course_voice_action_start_distance");
        ash.d("run_course_voice_speak_aside_cache");
    }

    @Override // com.huawei.health.sportservice.SportComponent
    public void destroy() {
        if (l()) {
            fis.d().c("fitness_audio_player", 5);
            fis.d().b("fitness_audio_player");
        }
    }

    @Override // com.huawei.health.sportservice.SportVoiceEnableListener
    public void onChange(boolean z) {
        if (l()) {
            this.b.e(z);
            fis.d().c("fitness_audio_player", z ? 102 : 101);
        }
    }
}
