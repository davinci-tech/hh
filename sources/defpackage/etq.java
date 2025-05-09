package defpackage;

import com.huawei.basefitnessadvice.model.Data;
import com.huawei.basefitnessadvice.model.Media;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.Comment;
import com.huawei.pluginfitnessadvice.FitWorkout;
import com.huawei.pluginfitnessadvice.WorkoutAction;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class etq {

    public static class b {

        /* renamed from: a, reason: collision with root package name */
        private boolean f12298a;
        private int b;
        private boolean c;
        private boolean d;

        public boolean b() {
            return this.f12298a;
        }

        public void a(boolean z) {
            this.f12298a = z;
        }

        public int a() {
            return this.b;
        }

        public void a(int i) {
            this.b = i;
        }

        public boolean c() {
            return this.c;
        }

        public void d(boolean z) {
            this.c = z;
        }

        public boolean e() {
            return this.d;
        }

        public void e(boolean z) {
            this.d = z;
        }
    }

    public static b a(FitWorkout fitWorkout, boolean z, boolean z2) {
        b bVar = new b();
        bVar.d(z);
        bVar.e(z2);
        if (z2) {
            bVar.a(e(fitWorkout));
            bVar.a(d(fitWorkout));
        }
        return bVar;
    }

    public static boolean e(String str) {
        return new File(squ.j(str)).exists();
    }

    public static int a(FitWorkout fitWorkout) {
        return c(fitWorkout, null, false);
    }

    public static int c(FitWorkout fitWorkout, int i) {
        if (fitWorkout == null) {
            ReleaseLogUtil.c("Suggestion_DataMediaFilesHelper", "getMediaFileLength fitWorkout==null");
            return 0;
        }
        HashMap hashMap = new HashMap(16);
        List<WorkoutAction> acquireWorkoutActions = fitWorkout.acquireWorkoutActions();
        if (koq.d(acquireWorkoutActions, i)) {
            return euf.d(acquireWorkoutActions.get(i), hashMap, (List<Media>) null, a(fitWorkout, false, true));
        }
        ReleaseLogUtil.c("Suggestion_DataMediaFilesHelper", "getMediaFileLength checkList(workoutActions)");
        return 0;
    }

    public static int c(FitWorkout fitWorkout, List<Media> list, boolean z) {
        int i = 0;
        if (fitWorkout == null) {
            ReleaseLogUtil.c("Suggestion_DataMediaFilesHelper", "getTotalLength null != fitWorkout");
            return 0;
        }
        HashMap hashMap = new HashMap(16);
        ArrayList<WorkoutAction> arrayList = new ArrayList(16);
        if (fitWorkout.acquireWarmUpRunAction() != null) {
            arrayList.add(fitWorkout.acquireWarmUpRunAction());
        }
        if (fitWorkout.acquireWorkoutActions() != null) {
            arrayList.addAll(fitWorkout.acquireWorkoutActions());
        }
        if (fitWorkout.acquireCoolDownRunAction() != null) {
            arrayList.add(fitWorkout.acquireCoolDownRunAction());
        }
        b a2 = a(fitWorkout, z, true);
        if (koq.c(arrayList)) {
            for (WorkoutAction workoutAction : arrayList) {
                i = i + b(workoutAction, hashMap, list, a2) + euf.b(workoutAction, hashMap, list, a2);
            }
        } else {
            ReleaseLogUtil.c("Suggestion_DataMediaFilesHelper", "getTotalLength CollectionUtils.isNotEmpty(workoutActions)");
        }
        if (z) {
            return i;
        }
        long currentTimeMillis = System.currentTimeMillis();
        int a3 = i + a(list, fitWorkout);
        LogUtil.c("Suggestion_DataMediaFilesHelper", "parseMultiLanguageLength time = ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
        return a3;
    }

    private static boolean d(FitWorkout fitWorkout) {
        return (fitWorkout.isLongVideoCourse() || fitWorkout.isRunModelCourse()) ? false : true;
    }

    private static int e(FitWorkout fitWorkout) {
        int a2 = ggg.a();
        LogUtil.a("Suggestion_DataMediaFilesHelper", "getGender: ", Integer.valueOf(a2));
        return fitWorkout.getCourseAttr() != 2 ? fitWorkout.getCourseAttr() : a2;
    }

    public static int a(FitWorkout fitWorkout, List<Media> list, int i) {
        if (fitWorkout == null) {
            ReleaseLogUtil.c("Suggestion_DataMediaFilesHelper", "saveListMediaFiles fitWorkout==null");
            return 0;
        }
        HashMap hashMap = new HashMap(16);
        List<WorkoutAction> acquireWorkoutActions = fitWorkout.acquireWorkoutActions();
        if (koq.d(acquireWorkoutActions, i)) {
            b a2 = a(fitWorkout, false, true);
            WorkoutAction workoutAction = acquireWorkoutActions.get(i);
            LogUtil.a("Suggestion_DataMediaFilesHelper", "workoutAction:", workoutAction);
            return euf.d(workoutAction, hashMap, list, a2);
        }
        ReleaseLogUtil.c("Suggestion_DataMediaFilesHelper", "saveListMediaFiles checkList(workoutActions)");
        return 0;
    }

    private static int a(List<Media> list, FitWorkout fitWorkout) {
        String acquireComeFrom = fitWorkout.getCourseDefineType() == 1 ? "R" : fitWorkout.acquireComeFrom();
        if (!e(acquireComeFrom)) {
            return 10485760;
        }
        List<Data> e = euf.e(squ.j(acquireComeFrom));
        int i = 0;
        if (koq.b(e)) {
            return 0;
        }
        for (Data data : e) {
            if (data != null) {
                String a2 = squ.a(data.acquireFileName());
                if (!squ.d(data.acquireFileName(), a(data), data.acquireUrl())) {
                    i = (int) (i + data.acquireSize());
                    if (list != null) {
                        Media media = new Media();
                        media.setUrl(data.acquireUrl());
                        media.setPath(a2);
                        media.setLength(data.acquireSize());
                        media.setType(3);
                        list.add(media);
                    }
                }
            }
        }
        return i;
    }

    private static long a(Data data) {
        long o = squ.o(squ.a(data.acquireFileName()));
        return o == 0 ? squ.o(squ.d(data.acquireFileName())) : o;
    }

    private static int b(WorkoutAction workoutAction, Map<String, Object> map, List<Media> list, b bVar) {
        List<Comment> commentaryTraining = workoutAction.getCommentaryTraining();
        int i = 0;
        if (koq.c(commentaryTraining)) {
            i = c(map, list, 0, commentaryTraining, bVar);
        } else {
            ReleaseLogUtil.c("Suggestion_DataMediaFilesHelper", "workoutAction.getCommentaryTraining() == null");
        }
        if (workoutAction.getActionMusic() == null) {
            return i;
        }
        String url = workoutAction.getActionMusic().getUrl();
        long b2 = b(url);
        if (b(bVar) && squ.d(url, b2)) {
            LogUtil.h("Suggestion_DataMediaFilesHelper", "action music has exist and no need update.", url);
            return i;
        }
        int length = workoutAction.getActionMusic().getLength();
        if (list != null) {
            Media media = new Media();
            media.setUrl(url);
            media.setLength(workoutAction.getActionMusic().getLength());
            media.setPath(squ.e(media.getUrl()));
            media.setType(2);
            LogUtil.a("Suggestion_DataMediaFilesHelper", "music url:", media.getUrl(), " path:", media.getPath());
            list.add(media);
        }
        return i + length;
    }

    private static int c(Map<String, Object> map, List<Media> list, int i, List<Comment> list2, b bVar) {
        for (Comment comment : list2) {
            long b2 = b(comment.acquireName());
            if (!map.containsKey(comment.acquireName()) && (!b(bVar) || !squ.d(comment.acquireName(), b2))) {
                map.put(comment.acquireName(), null);
                i += comment.getLength();
                if (list != null) {
                    Media media = new Media();
                    media.setUrl(comment.acquireName());
                    media.setPath(squ.e(media.getUrl()));
                    media.setLength(comment.getLength());
                    media.setType(2);
                    LogUtil.a("Suggestion_DataMediaFilesHelper", "comment url:", media.getUrl(), " path:", media.getPath());
                    list.add(media);
                }
            }
        }
        return i;
    }

    public static List<Media> a(FitWorkout fitWorkout, boolean z) {
        if (fitWorkout == null) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList(16);
        c(fitWorkout, arrayList, z);
        return arrayList;
    }

    public static long b(String str) {
        long o = squ.o(squ.d(str));
        if (o != 0 || !squ.p(str)) {
            return o;
        }
        squ.a(str, ash.b(squ.d(str)));
        return squ.o(squ.d(str));
    }

    public static boolean b(b bVar) {
        if (bVar == null) {
            return true;
        }
        return !bVar.c();
    }
}
