package defpackage;

import android.os.Bundle;
import com.huawei.hwcommonmodel.datatypes.MusicInfo;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes5.dex */
public class khc {
    public static byte[] b(MusicInfo musicInfo) {
        String str;
        String str2;
        if (musicInfo == null) {
            return new byte[0];
        }
        Bundle bundle = musicInfo.getBundle();
        String string = bundle.getString("mSingerName");
        String string2 = bundle.getString("mSongName");
        int i = bundle.getInt("mPlayState");
        int i2 = bundle.getInt("mMaxVolume");
        int i3 = bundle.getInt("mCurrentVolume");
        StringBuffer stringBuffer = new StringBuffer(16);
        if (string == null || "".equals(string)) {
            str = cvx.e(1) + "0100";
        } else {
            str = cvx.e(1) + b(cvx.c(string).length() / 2) + cvx.c(string);
        }
        if (string2 == null || "".equals(string2)) {
            str2 = cvx.e(2) + "0100";
        } else {
            str2 = cvx.e(2) + b(cvx.c(string2).length() / 2) + cvx.c(string2);
        }
        String str3 = cvx.e(3) + cvx.e(1) + cvx.e(i);
        stringBuffer.append(str).append(str2).append(str3).append(cvx.e(4) + cvx.e(1) + cvx.e(i2)).append(cvx.e(5) + cvx.e(1) + cvx.e(i3));
        String stringBuffer2 = stringBuffer.toString();
        LogUtil.a("MusicControlManager", "package packageMusicCommand :", stringBuffer2);
        return cvx.a(stringBuffer2);
    }

    private static String b(int i) {
        if (i > 127) {
            return cvx.e((i >> 7) + 128) + cvx.e(i & 127);
        }
        return cvx.e(i);
    }

    public static byte[] c(int i) {
        StringBuffer stringBuffer = new StringBuffer(16);
        stringBuffer.append(cvx.e(127) + cvx.e(4) + cvx.b(i));
        String stringBuffer2 = stringBuffer.toString();
        LogUtil.a("MusicControlManager", "package Error Code Command:", stringBuffer2);
        return cvx.a(stringBuffer2);
    }
}
