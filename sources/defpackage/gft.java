package defpackage;

import com.huawei.health.suggestion.ui.voice.IVoiceContentConstructor;

/* loaded from: classes4.dex */
public class gft implements IVoiceContentConstructor {
    private static volatile gft e;

    @Override // com.huawei.health.suggestion.ui.voice.IVoiceContentConstructor
    public Object constructContent(int i, Object obj) {
        return null;
    }

    private gft() {
    }

    public static gft b() {
        if (e == null) {
            synchronized (gft.class) {
                if (e == null) {
                    e = new gft();
                }
            }
        }
        return e;
    }
}
