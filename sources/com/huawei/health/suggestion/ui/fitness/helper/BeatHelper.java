package com.huawei.health.suggestion.ui.fitness.helper;

import android.content.Context;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.text.TextUtils;
import com.huawei.health.marketing.datatype.SingleDailyMomentContent;
import com.huawei.health.suggestion.h5pro.AudioConstant;
import com.huawei.health.suggestion.ui.fitness.helper.inter.BeatAudioInterface;
import com.huawei.health.suggestion.ui.fitness.helper.inter.VideoInterface;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.koq;
import defpackage.mwz;
import defpackage.mxb;
import defpackage.squ;
import health.compact.a.LogAnonymous;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes4.dex */
public class BeatHelper extends MediaHelper implements BeatAudioInterface, MediaPlayer.OnCompletionListener {
    private boolean f;
    private int g;
    private boolean h;
    private List i;

    public BeatHelper(Context context) {
        super(context);
        this.i = new CopyOnWriteArrayList();
        this.f = true;
        this.f3166a = false;
        this.e.setAudioStreamType(3);
        this.e.setLooping(false);
        this.e.setOnCompletionListener(this);
    }

    @Override // com.huawei.health.suggestion.ui.fitness.helper.inter.BeatAudioInterface
    public BeatAudioInterface resetBeatNum(int i) {
        boolean z = true;
        if (i < 1 || i > 10000) {
            LogUtil.h("Suggestion_BeatHelper", "resetBeatNum input params is invalid.");
            return this;
        }
        this.g = i;
        String[] e = e(i);
        int length = e.length;
        int i2 = 0;
        while (true) {
            if (i2 >= length) {
                break;
            }
            if (TextUtils.isEmpty(e[i2])) {
                z = false;
                break;
            }
            i2++;
        }
        if (this.e != null && z) {
            setSdSources(e);
        } else {
            LogUtil.h("Suggestion_BeatHelper", "resetBeatNum() mPlayer or beatData is null");
        }
        this.c = -1;
        return this;
    }

    private String[] e(int i) {
        String[] strArr = new String[i];
        if (l()) {
            b(strArr);
        } else {
            for (int i2 = 0; i2 < i; i2++) {
                strArr[i2] = i2 > 8 ? squ.a("B0" + (i2 + 1), this.d, this.b, AudioConstant.AUDIO) : squ.a("B00" + (i2 + 1), this.d, this.b, AudioConstant.AUDIO);
            }
        }
        return strArr;
    }

    private void b(String[] strArr) {
        int i = 0;
        while (i < strArr.length) {
            mwz mwzVar = new mwz();
            int i2 = i + 1;
            mwzVar.b(Integer.valueOf(i2));
            List<String> scenarioAudioPaths = mxb.a().getScenarioAudioPaths(FitnessRunAudioScenarioId.NUMBER_X.getId(), mwzVar);
            if (koq.b(scenarioAudioPaths)) {
                LogUtil.h("Suggestion_BeatHelper", "constructNumPath() scenarioAudioPaths is null");
                return;
            }
            LogUtil.a("Suggestion_BeatHelper", "constructNumPath() scenarioAudioPaths:", scenarioAudioPaths.toString());
            if (scenarioAudioPaths.size() == 1) {
                strArr[i] = scenarioAudioPaths.get(0);
            } else {
                strArr[i] = b(i, scenarioAudioPaths);
            }
            i = i2;
        }
        LogUtil.a("Suggestion_BeatHelper", "constructNumPath() numPath.size():", Integer.valueOf(strArr.length), " numPath:", Arrays.toString(strArr));
    }

    private String b(int i, List<String> list) {
        StringBuilder sb = new StringBuilder(16);
        try {
            sb.append(BaseApplication.getContext().getFilesDir().getCanonicalPath());
            sb.append(File.separator);
            sb.append("MergeSound");
            sb.append(File.separator);
            sb.append(i);
            sb.append(File.separator);
            sb.append("sound.mp3");
            d(sb, list);
        } catch (IOException e) {
            LogUtil.b("Suggestion_BeatHelper", "getMergeSoundPath exception", LogAnonymous.b((Throwable) e));
        }
        return sb.toString();
    }

    private void d(StringBuilder sb, List<String> list) throws IOException {
        String sb2 = sb.toString();
        File file = new File(sb2);
        if (file.exists() && !new File(sb2).delete()) {
            LogUtil.b("Suggestion_BeatHelper", "file delete failed");
        }
        Vector<InputStream> vector = new Vector<>();
        try {
            vector = e(list);
        } catch (Resources.NotFoundException e) {
            e = e;
            LogUtil.b("Suggestion_BeatHelper", LogAnonymous.b(e));
        } catch (FileNotFoundException unused) {
            LogUtil.b("Suggestion_BeatHelper", "file not found exception");
        } catch (IOException e2) {
            LogUtil.b("Suggestion_BeatHelper", "mergeSoundFile() IOException: ", LogAnonymous.b((Throwable) e2));
        } catch (IndexOutOfBoundsException e3) {
            e = e3;
            LogUtil.b("Suggestion_BeatHelper", LogAnonymous.b(e));
        } catch (SecurityException e4) {
            e = e4;
            LogUtil.b("Suggestion_BeatHelper", LogAnonymous.b(e));
        }
        b(file, vector);
    }

    private Vector<InputStream> e(List<String> list) throws IOException {
        Vector<InputStream> vector = new Vector<>();
        for (String str : list) {
            if (!TextUtils.isEmpty(str)) {
                vector.add(new FileInputStream(str));
            }
        }
        return vector;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0091 A[LOOP:3: B:35:0x008b->B:37:0x0091, LOOP_END] */
    /* JADX WARN: Type inference failed for: r10v0, types: [java.util.Vector, java.util.Vector<java.io.InputStream>] */
    /* JADX WARN: Type inference failed for: r10v1 */
    /* JADX WARN: Type inference failed for: r10v4 */
    /* JADX WARN: Type inference failed for: r10v8, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r5v0 */
    /* JADX WARN: Type inference failed for: r5v1 */
    /* JADX WARN: Type inference failed for: r5v10 */
    /* JADX WARN: Type inference failed for: r5v11 */
    /* JADX WARN: Type inference failed for: r5v3 */
    /* JADX WARN: Type inference failed for: r5v4, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r5v5 */
    /* JADX WARN: Type inference failed for: r5v6 */
    /* JADX WARN: Type inference failed for: r5v7 */
    /* JADX WARN: Type inference failed for: r5v8, types: [int] */
    /* JADX WARN: Type inference failed for: r9v11, types: [java.io.Closeable, java.io.FileOutputStream] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void b(java.io.File r9, java.util.Vector<java.io.InputStream> r10) {
        /*
            r8 = this;
            java.lang.String r0 = "Suggestion_BeatHelper"
            long r1 = java.lang.System.currentTimeMillis()
            java.util.Enumeration r3 = r10.elements()
            r4 = 0
            r5 = 0
            java.io.SequenceInputStream r6 = new java.io.SequenceInputStream     // Catch: java.lang.Throwable -> L45 java.lang.Throwable -> L48
            r6.<init>(r3)     // Catch: java.lang.Throwable -> L45 java.lang.Throwable -> L48
            java.io.FileOutputStream r9 = org.apache.commons.io.FileUtils.openOutputStream(r9)     // Catch: java.lang.Throwable -> L43 java.lang.Throwable -> L49
            r3 = 1024(0x400, float:1.435E-42)
            byte[] r3 = new byte[r3]     // Catch: java.lang.Throwable -> L3e java.lang.Throwable -> L41
        L19:
            int r5 = r6.read(r3)     // Catch: java.lang.Throwable -> L3e java.lang.Throwable -> L41
            r7 = -1
            if (r5 == r7) goto L24
            r9.write(r3, r4, r5)     // Catch: java.lang.Throwable -> L3e java.lang.Throwable -> L41
            goto L19
        L24:
            com.huawei.haf.common.os.FileUtils.d(r9)
            com.huawei.haf.common.os.FileUtils.d(r6)
            java.util.Iterator r9 = r10.iterator()
        L2e:
            boolean r10 = r9.hasNext()
            if (r10 == 0) goto L6d
            java.lang.Object r10 = r9.next()
            java.io.InputStream r10 = (java.io.InputStream) r10
            com.huawei.haf.common.os.FileUtils.d(r10)
            goto L2e
        L3e:
            r0 = move-exception
            r5 = r9
            goto L80
        L41:
            r5 = r9
            goto L49
        L43:
            r0 = move-exception
            goto L80
        L45:
            r9 = move-exception
            r6 = r5
            goto L81
        L48:
            r6 = r5
        L49:
            r9 = 1
            java.lang.Object[] r9 = new java.lang.Object[r9]     // Catch: java.lang.Throwable -> L43
            java.lang.String r3 = "writeToFile PorcessFused()"
            r9[r4] = r3     // Catch: java.lang.Throwable -> L43
            com.huawei.hwlogsmodel.LogUtil.b(r0, r9)     // Catch: java.lang.Throwable -> L43
            com.huawei.haf.common.os.FileUtils.d(r5)
            com.huawei.haf.common.os.FileUtils.d(r6)
            java.util.Iterator r9 = r10.iterator()
        L5d:
            boolean r10 = r9.hasNext()
            if (r10 == 0) goto L6d
            java.lang.Object r10 = r9.next()
            java.io.InputStream r10 = (java.io.InputStream) r10
            com.huawei.haf.common.os.FileUtils.d(r10)
            goto L5d
        L6d:
            long r9 = java.lang.System.currentTimeMillis()
            long r9 = r9 - r1
            java.lang.Long r9 = java.lang.Long.valueOf(r9)
            java.lang.String r10 = "writeToFile end"
            java.lang.Object[] r9 = new java.lang.Object[]{r10, r9}
            com.huawei.hwlogsmodel.LogUtil.a(r0, r9)
            return
        L80:
            r9 = r0
        L81:
            com.huawei.haf.common.os.FileUtils.d(r5)
            com.huawei.haf.common.os.FileUtils.d(r6)
            java.util.Iterator r10 = r10.iterator()
        L8b:
            boolean r0 = r10.hasNext()
            if (r0 == 0) goto L9b
            java.lang.Object r0 = r10.next()
            java.io.InputStream r0 = (java.io.InputStream) r0
            com.huawei.haf.common.os.FileUtils.d(r0)
            goto L8b
        L9b:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.health.suggestion.ui.fitness.helper.BeatHelper.b(java.io.File, java.util.Vector):void");
    }

    @Override // com.huawei.health.suggestion.ui.fitness.helper.inter.BeatAudioInterface
    public void timer() {
        if (this.e != null) {
            setSdSources(squ.a("D011", this.d, this.b, AudioConstant.AUDIO));
            start();
        }
    }

    @Override // com.huawei.health.suggestion.ui.fitness.helper.inter.BeatAudioInterface
    public void nextBeat() {
        this.h = false;
        if (this.e != null) {
            next();
        }
    }

    public BeatAudioInterface e() {
        if (this.e != null) {
            if (l()) {
                setSdSources(a(FitnessRunAudioScenarioId.HOLD_ON_FIVE_SECONDS));
            } else {
                setSdSources(squ.a("E069", this.d, this.b, AudioConstant.AUDIO));
            }
            start();
        }
        return this;
    }

    @Override // com.huawei.health.suggestion.ui.fitness.helper.MediaHelper, com.huawei.health.suggestion.ui.fitness.helper.inter.VideoInterface
    public VideoInterface release() {
        return super.release();
    }

    @Override // com.huawei.health.suggestion.ui.fitness.helper.inter.BeatAudioInterface
    public BeatAudioInterface playBeatNum(int i) {
        this.h = false;
        if (this.e != null) {
            this.h = true;
            a(c(i));
            start();
        }
        return this;
    }

    private void t() {
        this.i.clear();
        if (l()) {
            this.i.add(a(FitnessRunAudioScenarioId.NUMBER_FIVE));
            this.i.add(a(FitnessRunAudioScenarioId.NUMBER_FOUR));
            this.i.add(a(FitnessRunAudioScenarioId.NUMBER_THREE));
            this.i.add(a(FitnessRunAudioScenarioId.NUMBER_TWO));
            this.i.add(a(FitnessRunAudioScenarioId.NUMBER_ONE));
            return;
        }
        this.i.add(squ.a("B005", this.d, this.b, AudioConstant.AUDIO));
        this.i.add(squ.a("B004", this.d, this.b, AudioConstant.AUDIO));
        this.i.add(squ.a("B003", this.d, this.b, AudioConstant.AUDIO));
        this.i.add(squ.a("B002", this.d, this.b, AudioConstant.AUDIO));
        this.i.add(squ.a("B001", this.d, this.b, AudioConstant.AUDIO));
    }

    private String a(FitnessRunAudioScenarioId fitnessRunAudioScenarioId) {
        List<String> scenarioAudioPaths = mxb.a().getScenarioAudioPaths(fitnessRunAudioScenarioId.getId(), new mwz());
        return koq.b(scenarioAudioPaths) ? "" : scenarioAudioPaths.get(0);
    }

    public void c() {
        this.h = false;
        a(this.i);
        start();
    }

    public BeatAudioInterface d() {
        LogUtil.c("Suggestion_BeatHelper", "PAUSE");
        if (this.e != null && this.e.isPlaying()) {
            this.e.pause();
            this.f = true;
        } else {
            LogUtil.h("Suggestion_BeatHelper", "pause fail, is still playing");
            this.f = false;
        }
        return this;
    }

    public BeatAudioInterface a() {
        LogUtil.c("Suggestion_BeatHelper", "Continue");
        if (this.e != null && this.f && this.e.getCurrentPosition() < this.e.getDuration() * 0.65f) {
            this.e.start();
        }
        return this;
    }

    public void c(String str) {
        if (this.e != null) {
            if (l()) {
                q();
            } else {
                setSdSources(squ.a(str, this.d, this.b, AudioConstant.AUDIO));
                start();
            }
        }
    }

    private void q() {
        setSdSources(a(FitnessRunAudioScenarioId.ENCOURAGE));
        start();
    }

    @Override // android.media.MediaPlayer.OnCompletionListener
    public void onCompletion(MediaPlayer mediaPlayer) {
        if (this.h) {
            this.h = false;
            next();
        }
    }

    @Override // com.huawei.health.suggestion.ui.fitness.helper.MediaHelper
    public void b(String str) {
        super.b(str);
        t();
    }

    private boolean l() {
        boolean b = mxb.a().b(BaseApplication.getContext(), SingleDailyMomentContent.COURSE_TYPE);
        LogUtil.a("Suggestion_BeatHelper", "isEnableCurLang() enableCurLang:", Boolean.valueOf(b));
        return b;
    }
}
