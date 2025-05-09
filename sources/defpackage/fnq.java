package defpackage;

import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.basefitnessadvice.model.Media;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.course.api.CourseApi;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.FitWorkout;
import com.huawei.pluginfitnessadvice.Workout;
import defpackage.fnq;
import health.compact.a.Services;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.attribute.FileTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes4.dex */
public class fnq {
    public static void b(FitWorkout fitWorkout) {
        CourseApi courseApi = (CourseApi) Services.a("CoursePlanService", CourseApi.class);
        if (courseApi == null) {
            LogUtil.h("DeleteFitnessCourseHelper", "deleteCourseCache: mCourseApi is null.");
            return;
        }
        boolean z = courseApi.getCourseUseCacheSize() > 1073741824;
        LogUtil.a("DeleteFitnessCourseHelper", "deleteCourseCache: isCacheAvailable is ", Boolean.valueOf(z));
        if (z) {
            courseApi.getUserCourseList(0, 50, 0, "FITNESS_COURSE", new d(courseApi.getMediaFiles(fitWorkout)));
        }
    }

    static class d extends UiCallback<List<Workout>> {
        private List<Media> d;

        d(List<Media> list) {
            this.d = list;
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        public void onFailure(int i, String str) {
            LogUtil.h("DeleteFitnessCourseHelper", "DownloadFitnessCourseCallback onFailure ", Integer.valueOf(i));
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onSuccess(List<Workout> list) {
            if (list == null) {
                LogUtil.h("DeleteFitnessCourseHelper", "DownloadFitnessCourseCallback data is null");
                return;
            }
            final List<FitWorkout> a2 = mod.a(list);
            if (a2 == null) {
                LogUtil.h("DeleteFitnessCourseHelper", "DownloadFitnessCourseCallback fitWorkouts is null");
            } else {
                ThreadPoolManager.d().execute(new Runnable() { // from class: fnr
                    @Override // java.lang.Runnable
                    public final void run() {
                        fnq.d.this.b(a2);
                    }
                });
            }
        }

        /* synthetic */ void b(List list) {
            FitWorkout fitWorkout;
            CourseApi courseApi = (CourseApi) Services.a("CoursePlanService", CourseApi.class);
            ArrayList arrayList = new ArrayList(16);
            Iterator it = list.iterator();
            while (it.hasNext()) {
                FitWorkout fitWorkout2 = (FitWorkout) it.next();
                if (!fitWorkout2.isLongVideoCourse() && courseApi.getCourseMediaFilesLength(fitWorkout2.acquireId(), fitWorkout2.accquireVersion()) == 0 && (fitWorkout = courseApi.getFitWorkout(fitWorkout2.acquireId(), fitWorkout2.accquireVersion())) != null) {
                    fitWorkout.setIntervals(fitWorkout2.getIntervals());
                    arrayList.add(fitWorkout);
                }
            }
            fnq.a(arrayList, this.d);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void a(List<FitWorkout> list, List<Media> list2) {
        HashMap hashMap = new HashMap(16);
        HashMap hashMap2 = new HashMap(16);
        CourseApi courseApi = (CourseApi) Services.a("CoursePlanService", CourseApi.class);
        for (FitWorkout fitWorkout : list) {
            hashMap2.put(fitWorkout.acquireId(), courseApi.getMediaFiles(fitWorkout));
        }
        HashSet hashSet = new HashSet(16);
        for (Media media : list2) {
            if (media != null) {
                hashSet.add(media.getUrl());
            }
        }
        for (FitWorkout fitWorkout2 : list) {
            Iterator it = ((List) hashMap2.get(fitWorkout2.acquireId())).iterator();
            long j = 0;
            while (it.hasNext()) {
                File file = new File(((Media) it.next()).getPath());
                if (file.exists()) {
                    j = Math.max(j, file.lastModified());
                }
            }
            hashMap.put(fitWorkout2.acquireId(), Long.valueOf(j));
        }
        c(list, hashMap, hashSet, hashMap2);
    }

    private static void c(List<FitWorkout> list, Map<String, Long> map, Set<String> set, Map<String, List<Media>> map2) {
        for (FitWorkout fitWorkout : list) {
            if (fitWorkout != null && map.get(fitWorkout.acquireId()) != null && a(map.get(fitWorkout.acquireId()).longValue()) && d(fitWorkout)) {
                c(map2.get(fitWorkout.acquireId()), set);
            }
        }
    }

    private static void c(List<Media> list, Set<String> set) {
        if (koq.b(list)) {
            return;
        }
        for (Media media : list) {
            if (media != null && !set.contains(media.getUrl()) && !moh.b(new File(media.getPath()))) {
                LogUtil.a("DeleteFitnessCourseHelper", "deleteCache: delete file failed");
            }
        }
    }

    private static boolean a(long j) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(5, -30);
        return j < calendar.getTimeInMillis();
    }

    private static boolean d(FitWorkout fitWorkout) {
        if (fitWorkout != null) {
            return fitWorkout.getIntervals() < 0 || fitWorkout.getIntervals() > 30;
        }
        LogUtil.h("DeleteFitnessCourseHelper", "ifExerciseInMonth: fitWorkout is null.");
        return false;
    }

    public static void a(final FitWorkout fitWorkout) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: fns
            @Override // java.lang.Runnable
            public final void run() {
                fnq.c(FitWorkout.this);
            }
        });
    }

    static /* synthetic */ void c(FitWorkout fitWorkout) {
        List<Media> mediaFiles = ((CourseApi) Services.a("CoursePlanService", CourseApi.class)).getMediaFiles(fitWorkout);
        if (koq.b(mediaFiles)) {
            LogUtil.h("DeleteFitnessCourseHelper", "refreshCourseTime: mediaList is Empty.");
            return;
        }
        FileTime fromMillis = FileTime.fromMillis(System.currentTimeMillis());
        for (Media media : mediaFiles) {
            if (media != null) {
                File file = new File(media.getPath());
                if (file.exists()) {
                    try {
                        Files.setAttribute(file.toPath(), "lastAccessTime", fromMillis, new LinkOption[0]);
                    } catch (IOException unused) {
                        LogUtil.h("DeleteFitnessCourseHelper", "refreshCourseTime: refresh time failed.");
                    }
                }
            }
        }
    }
}
