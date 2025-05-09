package defpackage;

import android.content.Intent;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes4.dex */
public class gxe {

    /* renamed from: a, reason: collision with root package name */
    private int f12988a;
    private Intent[] b;
    private int c = 0;
    private int d = 0;

    public gxe(int i) {
        this.b = new Intent[i + 1];
        this.f12988a = i;
    }

    public boolean c() {
        return this.d == this.c;
    }

    public int a() {
        int i = this.d;
        int i2 = this.c;
        if (i > i2) {
            return i - i2;
        }
        if (i2 == i) {
            return 0;
        }
        return this.b.length - (i2 - i);
    }

    public boolean b() {
        return (this.d + 1) % this.b.length == this.c;
    }

    public void aVr_(Intent intent) {
        if (intent == null || !"action_play_voice".equals(intent.getAction())) {
            LogUtil.h("Track_VoiceIntentBuffer", "[forceInsert] item is null or invalidation.");
            return;
        }
        Intent[] intentArr = this.b;
        int i = this.c;
        intentArr[i] = intent;
        this.d = (i + 1) % intentArr.length;
        LogUtil.c("Track_VoiceIntentBuffer", "insert voice success");
    }

    public boolean aVs_(Intent intent) {
        if (aVp_(intent)) {
            if (intent.getIntExtra("SPEAK_TYPE", -1) == 4) {
                Intent[] intentArr = this.b;
                int i = this.c;
                intentArr[i] = intent;
                this.d = (i + 1) % intentArr.length;
            } else if (!aVq_(intent)) {
                c(intent.getIntExtra("SPEAK_TYPE", -1));
            } else {
                if (b()) {
                    LogUtil.h("Track_VoiceIntentBuffer", "The voicebuffer is full!");
                    return false;
                }
                Intent[] intentArr2 = this.b;
                int i2 = this.d;
                intentArr2[i2] = intent;
                this.d = (i2 + 1) % intentArr2.length;
                LogUtil.c("Track_VoiceIntentBuffer", "insert voice success");
                return true;
            }
        } else {
            if (b()) {
                LogUtil.h("Track_VoiceIntentBuffer", "The voicebuffer is full!");
                return false;
            }
            if (aVq_(intent)) {
                Intent[] intentArr3 = this.b;
                int i3 = this.d;
                intentArr3[i3] = intent;
                this.d = (i3 + 1) % intentArr3.length;
                LogUtil.c("Track_VoiceIntentBuffer", "insert voice success");
                return true;
            }
        }
        return false;
    }

    private void c(int i) {
        int i2;
        int i3 = this.d;
        Intent[] intentArr = this.b;
        int length = (i3 + intentArr.length) - 1;
        int length2 = intentArr.length;
        while (true) {
            i2 = length % length2;
            if (i2 == this.c || i == this.b[i2].getIntExtra("SPEAK_TYPE", -1)) {
                break;
            }
            Intent[] intentArr2 = this.b;
            length = (i2 + intentArr2.length) - 1;
            length2 = intentArr2.length;
        }
        this.d = (i2 + 1) % this.b.length;
    }

    private boolean aVq_(Intent intent) {
        if (intent == null || !"action_play_voice".equals(intent.getAction())) {
            return false;
        }
        if (a() == 0) {
            return true;
        }
        int intExtra = intent.getIntExtra("SPEAK_TYPE", -1);
        int i = this.c;
        if (i < this.d) {
            while (i < this.d) {
                if (intExtra != 20 && this.b[i].getIntExtra("SPEAK_TYPE", -1) == intExtra) {
                    return false;
                }
                i++;
            }
        } else {
            while (true) {
                Intent[] intentArr = this.b;
                if (i < intentArr.length) {
                    if (intExtra != 20 && intentArr[i].getIntExtra("SPEAK_TYPE", -1) == intExtra) {
                        return false;
                    }
                    i++;
                } else {
                    for (int i2 = 0; i2 < this.d; i2++) {
                        if (intExtra != 20 && this.b[i2].getIntExtra("SPEAK_TYPE", -1) == intExtra) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    public Intent aVt_() {
        if (c()) {
            LogUtil.h("Track_VoiceIntentBuffer", "The voicebuffer is empty!");
            return null;
        }
        Intent[] intentArr = this.b;
        int i = this.c;
        Intent intent = intentArr[i];
        this.c = (i + 1) % intentArr.length;
        return intent;
    }

    public void e() {
        if (c()) {
            return;
        }
        this.c = 0;
        this.d = 0;
    }

    public String toString() {
        String str = "Buffer size: " + this.f12988a + ", items num: " + a();
        if (c()) {
            return str;
        }
        StringBuilder sb = new StringBuilder(str);
        sb.append(" voice type:");
        int i = this.c;
        if (i < this.d) {
            while (i < this.d) {
                sb.append(" ");
                sb.append(this.b[i].getIntExtra("SPEAK_TYPE", -1));
                i++;
            }
        } else {
            while (i < this.b.length) {
                sb.append(" ");
                sb.append(this.b[i].getIntExtra("SPEAK_TYPE", -1));
                i++;
            }
            for (int i2 = 0; i2 < this.d; i2++) {
                sb.append(" ");
                sb.append(this.b[i2].getIntExtra("SPEAK_TYPE", -1));
            }
        }
        return sb.toString();
    }

    private boolean aVp_(Intent intent) {
        int intExtra = intent.getIntExtra("SPEAK_TYPE", -1);
        return intExtra == 3 || intExtra == 5 || intExtra == 4;
    }
}
