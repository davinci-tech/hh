package defpackage;

import android.widget.CompoundButton;

/* loaded from: classes6.dex */
public class ozf {
    public static obz djU_(boolean z, ozj ozjVar, ozh ozhVar, CompoundButton.OnCheckedChangeListener... onCheckedChangeListenerArr) {
        obz obzVar = new obz();
        if (ozjVar != null && ozhVar != null) {
            obzVar.e(ozjVar.b());
            obzVar.c(ozjVar.c());
            obzVar.a(ozhVar.a());
            obzVar.d(ozhVar.e());
            obzVar.e(ozhVar.d());
            obzVar.b(ozjVar.a());
            obzVar.d(z);
            if (onCheckedChangeListenerArr.length != 0) {
                obzVar.cVo_(onCheckedChangeListenerArr[0]);
            }
        }
        return obzVar;
    }
}
