package com.huawei.healthcloud.plugintrack.manager.voice;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import com.huawei.healthcloud.plugintrack.manager.voice.constructor.ChineseVoiceConstructor;
import com.huawei.healthcloud.plugintrack.manager.voice.constructor.EnglishVoiceConstructor;
import com.huawei.healthcloud.plugintrack.manager.voice.constructor.ISoundResourceConstructor;
import com.huawei.healthcloud.plugintrack.manager.voice.constructor.IVoiceContentConstructor;
import com.huawei.healthcloud.plugintrack.manager.voice.constructor.IVoiceEngine;
import com.huawei.healthcloud.plugintrack.manager.voice.constructor.VoicePlayInterface;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.gwg;
import defpackage.gxa;
import defpackage.gxe;
import defpackage.gxh;
import defpackage.gxi;
import defpackage.mxb;
import health.compact.a.LanguageUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.UnitUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

/* loaded from: classes4.dex */
public class VoicePlayer implements VoicePlayInterface {

    /* renamed from: a, reason: collision with root package name */
    private boolean f3527a;
    private IVoiceContentConstructor b;
    private Context e;
    private Handler f;
    private IVoiceEngine i;
    private gxe j;
    private volatile boolean d = true;
    private MediaPlayer.OnCompletionListener c = new MediaPlayer.OnCompletionListener() { // from class: com.huawei.healthcloud.plugintrack.manager.voice.VoicePlayer.2
        @Override // android.media.MediaPlayer.OnCompletionListener
        public void onCompletion(MediaPlayer mediaPlayer) {
            if (mediaPlayer == null) {
                LogUtil.a("Track_VoicePlayer", "The voice was phoning!");
                VoicePlayer.this.d = true;
                VoicePlayer.this.j.e();
                return;
            }
            Message obtain = Message.obtain();
            obtain.what = 2;
            VoicePlayer.this.d = true;
            VoicePlayer.this.f.sendMessage(obtain);
            if (VoicePlayer.this.j.c()) {
                VoicePlayer.this.i.setVoiceIntentBufferState(true);
            }
        }
    };

    @Override // com.huawei.healthcloud.plugintrack.manager.voice.constructor.VoicePlayInterface
    public void continuePlay() {
    }

    @Override // com.huawei.healthcloud.plugintrack.manager.voice.constructor.VoicePlayInterface
    public int getType() {
        return 0;
    }

    @Override // com.huawei.healthcloud.plugintrack.manager.voice.constructor.VoicePlayInterface
    public void muteVolume() {
    }

    @Override // com.huawei.healthcloud.plugintrack.manager.voice.constructor.VoicePlayInterface
    public void pausePlay() {
    }

    @Override // com.huawei.healthcloud.plugintrack.manager.voice.constructor.VoicePlayInterface
    public void resumeVolume() {
    }

    @Override // com.huawei.healthcloud.plugintrack.manager.voice.constructor.VoicePlayInterface
    public void startPlay(String str) {
    }

    public VoicePlayer(Context context) {
        if (context == null) {
            throw new RuntimeException("VoicePlayer invalid params in constructor");
        }
        this.e = context;
        this.j = new gxe(3);
        this.f = new b();
        e(this.e);
    }

    @Override // com.huawei.healthcloud.plugintrack.manager.voice.constructor.VoicePlayInterface
    public void insertVoice(Intent intent) {
        if (intent == null) {
            LogUtil.b("Track_VoicePlayer", "intent null");
            return;
        }
        if ("immediately_play".equals(intent.getStringExtra("SPEAK_EXTRA_MESSAGE"))) {
            this.j.aVr_(intent);
        } else {
            this.j.aVs_(intent);
        }
        Message obtain = Message.obtain();
        if (intent.getIntExtra("SPEAK_TYPE", -1) == 4) {
            this.d = true;
        }
        obtain.what = 1;
        this.f.sendMessage(obtain);
    }

    @Override // com.huawei.healthcloud.plugintrack.manager.voice.constructor.VoicePlayInterface
    public void resetPlayer() {
        this.d = true;
        this.j.e();
    }

    @Override // com.huawei.healthcloud.plugintrack.manager.voice.constructor.VoicePlayInterface
    public void stopPlay() {
        LogUtil.a("Track_VoicePlayer", "stop() enter");
        this.i.stopVoice();
    }

    @Override // com.huawei.healthcloud.plugintrack.manager.voice.constructor.VoicePlayInterface
    public void destroy() {
        LogUtil.a("Track_VoicePlayer", "destroy() enter");
        this.b.release();
        this.i.unregisterVoicePlayCompletionListener();
        this.i.destroy();
    }

    private void c(boolean z, int i, Object obj) {
        LogUtil.a("Track_VoicePlayer", "play() enter");
        if (UnitUtil.h()) {
            LogUtil.a("Track_VoicePlayer", "unexpected imperial unit status");
            return;
        }
        if (this.f3527a) {
            if (!LanguageUtil.j(this.e)) {
                destroy();
                e(this.e);
            }
        } else if (LanguageUtil.j(this.e)) {
            destroy();
            e(this.e);
        }
        if (i == 10) {
            this.i.playVoice(obj, z);
        } else {
            this.i.playVoice(this.b.constructContent(i, obj), z);
        }
    }

    class b extends Handler {
        private b() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message == null) {
                return;
            }
            super.handleMessage(message);
            int i = message.what;
            if (i != 1) {
                if (i != 2) {
                    return;
                }
                if (!VoicePlayer.this.j.c() && VoicePlayer.this.d) {
                    VoicePlayer.this.i.setVoiceIntentBufferState(false);
                    VoicePlayer voicePlayer = VoicePlayer.this;
                    voicePlayer.aVz_(voicePlayer.j.aVt_());
                    VoicePlayer.this.d = false;
                    return;
                }
                LogUtil.a("Track_VoicePlayer", "itemCount=", Integer.valueOf(VoicePlayer.this.j.a()), " mIsVoicePlayerIdle = ", Boolean.valueOf(VoicePlayer.this.d));
                return;
            }
            if (VoicePlayer.this.j.a() != 1 || !VoicePlayer.this.d) {
                if (VoicePlayer.this.d) {
                    sendMessage(obtainMessage(2));
                    return;
                } else {
                    LogUtil.a("Track_VoicePlayer", "can not insert");
                    return;
                }
            }
            VoicePlayer voicePlayer2 = VoicePlayer.this;
            voicePlayer2.aVz_(voicePlayer2.j.aVt_());
            VoicePlayer.this.d = false;
        }
    }

    public void aVz_(Intent intent) {
        if (intent == null || !"action_play_voice".equals(intent.getAction())) {
            LogUtil.b("Track_VoicePlayer", "managerVoiceIntent is null.");
            return;
        }
        int intExtra = intent.getIntExtra("SPEAK_TYPE", -1);
        ReleaseLogUtil.e("Track_VoicePlayer", aVy_(intent, this.j.a()));
        if (intExtra == 10) {
            int intExtra2 = intent.getIntExtra("SPEAK_PARAMETER_TYPE", -1);
            if (intExtra2 == 0) {
                int intExtra3 = intent.getIntExtra("SPEAK_PARAMETER", -1);
                if (intExtra3 != -1) {
                    ArrayList arrayList = new ArrayList(1);
                    arrayList.add(Integer.valueOf(intExtra3));
                    c(true, 10, arrayList);
                    return;
                }
                return;
            }
            if (intExtra2 != 1) {
                if (intExtra2 == 2) {
                    Object stringExtra = intent.getStringExtra("SPEAK_PARAMETER");
                    LogUtil.a("Track_VoicePlayer", "SPEAK_PARAMETER_TYPE_OTHER_RESOURCE play :", stringExtra);
                    c(true, 10, stringExtra);
                    return;
                } else if (intExtra2 == 3) {
                    aVx_(intent);
                    return;
                } else {
                    LogUtil.h("Track_VoicePlayer", "Unkown resource type");
                    return;
                }
            }
            int[] intArrayExtra = intent.getIntArrayExtra("SPEAK_PARAMETER");
            if (intArrayExtra == null || intArrayExtra.length == 0) {
                LogUtil.b("Track_VoicePlayer", "voiceSourceIndexArray is empty");
                return;
            }
            ArrayList arrayList2 = new ArrayList(intArrayExtra.length);
            for (int i : intArrayExtra) {
                arrayList2.add(Integer.valueOf(i));
            }
            c(true, 10, arrayList2);
            return;
        }
        c(true, intExtra, intent.getSerializableExtra("SPEAK_PARAMETER"));
    }

    public String aVy_(Intent intent, int i) {
        if (intent == null) {
            return null;
        }
        int intExtra = intent.getIntExtra("SPEAK_TYPE", -1);
        StringBuilder sb = new StringBuilder("Playing voice ");
        aVv_(intent, intExtra, sb);
        sb.append(" Pieces of voice to play:");
        sb.append(i);
        return sb.toString();
    }

    private void aVv_(Intent intent, int i, StringBuilder sb) {
        switch (i) {
            case -1:
                sb.append("VOICE INVALID");
                break;
            case 0:
                sb.append("START_BIKING");
                break;
            case 1:
                sb.append("START_RUNNING");
                break;
            case 2:
                sb.append("START_WALKING");
                break;
            case 3:
                sb.append("PAUSE_SPORT");
                break;
            case 4:
                sb.append("SPORT_OVER");
                break;
            case 5:
                sb.append("RESTART_SPORT");
                break;
            case 6:
                sb.append("GOAL_COMPLETE");
                break;
            case 7:
            case 8:
            default:
                aVw_(intent, i, sb);
                break;
            case 9:
                sb.append("DISTANCE_TIME");
                Serializable serializableExtra = intent.getSerializableExtra("SPEAK_PARAMETER");
                if (serializableExtra instanceof gxi) {
                    gxi gxiVar = (gxi) serializableExtra;
                    sb.append(" SportType:");
                    sb.append(gwg.c(gxiVar.n()) + "ms Tip ");
                    sb.append(gxiVar.h());
                    break;
                }
                break;
            case 10:
                sb.append("SPORT_SUGGEST_TYPE");
                break;
        }
    }

    private void aVw_(Intent intent, int i, StringBuilder sb) {
        if (i == 111) {
            sb.append("START_HIKING");
            return;
        }
        if (i != 112) {
            switch (i) {
                case 11:
                    sb.append("SPORT_STATE_BROADCAST_TYPE");
                    Serializable serializableExtra = intent.getSerializableExtra("SPEAK_PARAMETER");
                    if (serializableExtra instanceof gxi) {
                        gxi gxiVar = (gxi) serializableExtra;
                        sb.append(" SportType:");
                        sb.append(gwg.c(gxiVar.n()) + "ms Tip ");
                        sb.append(gxiVar.h());
                        break;
                    }
                    break;
                case 12:
                    sb.append("MARATHON_COMPLETE");
                    break;
                case 13:
                    sb.append("HALF_MARATHON_COMPLETE");
                    break;
                case 14:
                    sb.append("HEART_RATE_WARNING");
                    break;
                default:
                    switch (i) {
                        case 17:
                            sb.append("COUNT_DOWN");
                            break;
                        case 18:
                            sb.append("COUNT_DOWN_GOAL_FINISH");
                            break;
                        case 19:
                            sb.append("COUNT_DOWN_GOAL_FINISH");
                            break;
                        case 20:
                            sb.append("COUNT_DOWN_NUMBER_321");
                            break;
                        default:
                            sb.append("VOICE INVALID");
                            break;
                    }
            }
            return;
        }
        sb.append("START_CLIMB_HILL");
    }

    private void aVx_(Intent intent) {
        try {
            String[] stringArrayExtra = intent.getStringArrayExtra("SPEAK_PARAMETER");
            if (stringArrayExtra != null && stringArrayExtra.length != 0) {
                ArrayList arrayList = new ArrayList(stringArrayExtra.length);
                arrayList.addAll(Arrays.asList(stringArrayExtra));
                c(true, 10, arrayList);
                return;
            }
            LogUtil.h("Track_VoicePlayer", "voiceSourceIndexArrayString is null ");
        } catch (ArrayIndexOutOfBoundsException e) {
            LogUtil.a("Track_VoicePlayer", "speakMultiRes,", LogAnonymous.b((Throwable) e));
        }
    }

    private void e(Context context) {
        LogUtil.a("Track_VoicePlayer", "initialize() enter");
        if (mxb.a().b(context, "Sport")) {
            this.b = new gxh();
            this.f3527a = false;
        } else if (LanguageUtil.j(context)) {
            this.b = new ChineseVoiceConstructor();
            this.f3527a = true;
        } else {
            this.b = new EnglishVoiceConstructor();
            this.f3527a = false;
        }
        IVoiceContentConstructor iVoiceContentConstructor = this.b;
        if (iVoiceContentConstructor instanceof ISoundResourceConstructor) {
            gxa gxaVar = new gxa(context, ((ISoundResourceConstructor) iVoiceContentConstructor).getSoundResource());
            this.i = gxaVar;
            gxaVar.registerVoicePlayCompletionListener(this.c);
        }
    }
}
