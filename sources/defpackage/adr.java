package defpackage;

import com.huawei.animationkit.computationalwallpaper.pattern.arranger.impl.VectorAnimation;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes8.dex */
public class adr {
    private static final Map<String, List<VectorAnimation>> c;

    static {
        HashMap hashMap = new HashMap();
        c = hashMap;
        hashMap.put("rotate", Collections.singletonList(new e(true)));
        hashMap.put("translateX", Collections.singletonList(new f(true)));
        hashMap.put("translateY", Collections.singletonList(new h(true)));
        hashMap.put("translateXY", Collections.singletonList(new j(true)));
        hashMap.put("scale", Collections.singletonList(new g(true)));
        hashMap.put("breath", Collections.singletonList(new d(0L, true)));
        hashMap.put("-rotate", Collections.singletonList(new e(false)));
        hashMap.put("-translateX", Collections.singletonList(new f(false)));
        hashMap.put("-translateY", Collections.singletonList(new h(false)));
        hashMap.put("-translateXY", Collections.singletonList(new j(false)));
        hashMap.put("-scale", Collections.singletonList(new g(false)));
        hashMap.put("-breath", Collections.singletonList(new d(0L, false)));
        hashMap.put("0", Collections.singletonList(new a()));
        hashMap.put("1", Collections.singletonList(new c()));
        hashMap.put("2", Collections.singletonList(new b()));
    }

    public static List<VectorAnimation> a(String str) {
        return c.get(str);
    }

    public static class e implements VectorAnimation {
        private final boolean d;

        @Override // com.huawei.animationkit.computationalwallpaper.pattern.arranger.impl.VectorAnimation
        public int getDuration() {
            return 5000;
        }

        public e(boolean z) {
            this.d = z;
        }

        @Override // com.huawei.animationkit.computationalwallpaper.pattern.arranger.impl.VectorAnimation
        public List<VectorAnimation.b> getScaleKeyFrames() {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new VectorAnimation.b(0.0f, 1.6f));
            arrayList.add(new VectorAnimation.b(1.0f, 1.0f));
            return arrayList;
        }

        @Override // com.huawei.animationkit.computationalwallpaper.pattern.arranger.impl.VectorAnimation
        public List<VectorAnimation.b> getRotateKeyFrame() {
            ArrayList arrayList = new ArrayList();
            if (this.d) {
                arrayList.add(new VectorAnimation.b(0.0f, -45.0f));
            } else {
                arrayList.add(new VectorAnimation.b(0.0f, 45.0f));
            }
            arrayList.add(new VectorAnimation.b(1.0f, 0.0f));
            return arrayList;
        }
    }

    public static class g implements VectorAnimation {
        private final boolean d;

        @Override // com.huawei.animationkit.computationalwallpaper.pattern.arranger.impl.VectorAnimation
        public int getDuration() {
            return 5000;
        }

        public g(boolean z) {
            this.d = z;
        }

        @Override // com.huawei.animationkit.computationalwallpaper.pattern.arranger.impl.VectorAnimation
        public List<VectorAnimation.b> getScaleKeyFrames() {
            ArrayList arrayList = new ArrayList();
            if (this.d) {
                arrayList.add(new VectorAnimation.b(0.0f, 1.5f));
                arrayList.add(new VectorAnimation.b(1.0f, 1.0f));
            } else {
                arrayList.add(new VectorAnimation.b(0.0f, 1.0f));
                arrayList.add(new VectorAnimation.b(1.0f, 1.5f));
            }
            return arrayList;
        }
    }

    public static class f implements VectorAnimation {

        /* renamed from: a, reason: collision with root package name */
        private final boolean f174a;

        @Override // com.huawei.animationkit.computationalwallpaper.pattern.arranger.impl.VectorAnimation
        public int getDuration() {
            return 5000;
        }

        public f(boolean z) {
            this.f174a = z;
        }

        @Override // com.huawei.animationkit.computationalwallpaper.pattern.arranger.impl.VectorAnimation
        public List<VectorAnimation.b> getTranslateXKeyFrames() {
            ArrayList arrayList = new ArrayList();
            if (this.f174a) {
                arrayList.add(new VectorAnimation.b(0.0f, 0.5f));
                arrayList.add(new VectorAnimation.b(1.0f, -0.5f));
            } else {
                arrayList.add(new VectorAnimation.b(0.0f, -0.5f));
                arrayList.add(new VectorAnimation.b(1.0f, 0.5f));
            }
            return arrayList;
        }

        @Override // com.huawei.animationkit.computationalwallpaper.pattern.arranger.impl.VectorAnimation
        public List<VectorAnimation.b> getScaleKeyFrames() {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new VectorAnimation.b(0.0f, 2.0f));
            arrayList.add(new VectorAnimation.b(1.0f, 2.0f));
            return arrayList;
        }
    }

    public static class h implements VectorAnimation {
        private final boolean c;

        @Override // com.huawei.animationkit.computationalwallpaper.pattern.arranger.impl.VectorAnimation
        public int getDuration() {
            return 5000;
        }

        public h(boolean z) {
            this.c = z;
        }

        @Override // com.huawei.animationkit.computationalwallpaper.pattern.arranger.impl.VectorAnimation
        public List<VectorAnimation.b> getTranslateYKeyFrames() {
            ArrayList arrayList = new ArrayList();
            if (this.c) {
                arrayList.add(new VectorAnimation.b(0.0f, -0.5f));
                arrayList.add(new VectorAnimation.b(1.0f, 0.5f));
            } else {
                arrayList.add(new VectorAnimation.b(0.0f, 0.5f));
                arrayList.add(new VectorAnimation.b(1.0f, -0.5f));
            }
            return arrayList;
        }

        @Override // com.huawei.animationkit.computationalwallpaper.pattern.arranger.impl.VectorAnimation
        public List<VectorAnimation.b> getScaleKeyFrames() {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new VectorAnimation.b(0.0f, 2.0f));
            arrayList.add(new VectorAnimation.b(1.0f, 2.0f));
            return arrayList;
        }
    }

    public static class j implements VectorAnimation {

        /* renamed from: a, reason: collision with root package name */
        private final boolean f175a;

        @Override // com.huawei.animationkit.computationalwallpaper.pattern.arranger.impl.VectorAnimation
        public int getDuration() {
            return 5000;
        }

        public j(boolean z) {
            this.f175a = z;
        }

        @Override // com.huawei.animationkit.computationalwallpaper.pattern.arranger.impl.VectorAnimation
        public List<VectorAnimation.b> getTranslateXKeyFrames() {
            ArrayList arrayList = new ArrayList();
            if (this.f175a) {
                arrayList.add(new VectorAnimation.b(0.0f, 0.5f));
                arrayList.add(new VectorAnimation.b(1.0f, -0.5f));
            } else {
                arrayList.add(new VectorAnimation.b(0.0f, -0.5f));
                arrayList.add(new VectorAnimation.b(1.0f, 0.5f));
            }
            return arrayList;
        }

        @Override // com.huawei.animationkit.computationalwallpaper.pattern.arranger.impl.VectorAnimation
        public List<VectorAnimation.b> getTranslateYKeyFrames() {
            ArrayList arrayList = new ArrayList();
            if (this.f175a) {
                arrayList.add(new VectorAnimation.b(0.0f, -0.5f));
                arrayList.add(new VectorAnimation.b(1.0f, 0.5f));
            } else {
                arrayList.add(new VectorAnimation.b(0.0f, 0.5f));
                arrayList.add(new VectorAnimation.b(1.0f, -0.5f));
            }
            return arrayList;
        }

        @Override // com.huawei.animationkit.computationalwallpaper.pattern.arranger.impl.VectorAnimation
        public List<VectorAnimation.b> getScaleKeyFrames() {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new VectorAnimation.b(0.0f, 2.0f));
            arrayList.add(new VectorAnimation.b(1.0f, 2.0f));
            return arrayList;
        }
    }

    public static class d implements VectorAnimation {
        private final boolean c;
        private final long d;

        @Override // com.huawei.animationkit.computationalwallpaper.pattern.arranger.impl.VectorAnimation
        public int getDuration() {
            return 5000;
        }

        public d(long j, boolean z) {
            this.d = j;
            this.c = z;
        }

        @Override // com.huawei.animationkit.computationalwallpaper.pattern.arranger.impl.VectorAnimation
        public List<VectorAnimation.b> getScaleKeyFrames() {
            ArrayList arrayList = new ArrayList();
            if (this.c) {
                arrayList.add(new VectorAnimation.b(0.0f, 1.0f));
                arrayList.add(new VectorAnimation.b(0.5f, 1.5f));
                arrayList.add(new VectorAnimation.b(1.0f, 1.0f));
            } else {
                arrayList.add(new VectorAnimation.b(0.0f, 1.5f));
                arrayList.add(new VectorAnimation.b(0.5f, 1.0f));
                arrayList.add(new VectorAnimation.b(1.0f, 1.5f));
            }
            return arrayList;
        }

        @Override // com.huawei.animationkit.computationalwallpaper.pattern.arranger.impl.VectorAnimation
        public long getStartDelay() {
            return this.d;
        }
    }

    public static class a implements VectorAnimation {
        @Override // com.huawei.animationkit.computationalwallpaper.pattern.arranger.impl.VectorAnimation
        public int getDuration() {
            return 1000;
        }

        @Override // com.huawei.animationkit.computationalwallpaper.pattern.arranger.impl.VectorAnimation
        public List<VectorAnimation.b> getScaleKeyFrames() {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new VectorAnimation.b(0.0f, 1.6f));
            arrayList.add(new VectorAnimation.b(1.0f, 1.0f));
            return arrayList;
        }

        @Override // com.huawei.animationkit.computationalwallpaper.pattern.arranger.impl.VectorAnimation
        public List<VectorAnimation.b> getRotateKeyFrame() {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new VectorAnimation.b(0.0f, -30.0f));
            arrayList.add(new VectorAnimation.b(1.0f, 0.0f));
            return arrayList;
        }
    }

    public static class c implements VectorAnimation {
        @Override // com.huawei.animationkit.computationalwallpaper.pattern.arranger.impl.VectorAnimation
        public int getDuration() {
            return 1000;
        }

        @Override // com.huawei.animationkit.computationalwallpaper.pattern.arranger.impl.VectorAnimation
        public List<VectorAnimation.b> getScaleKeyFrames() {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new VectorAnimation.b(0.0f, 2.0f));
            arrayList.add(new VectorAnimation.b(0.6f, 1.0f));
            arrayList.add(new VectorAnimation.b(0.8f, 1.02f));
            arrayList.add(new VectorAnimation.b(1.0f, 1.0f));
            return arrayList;
        }
    }

    public static class b implements VectorAnimation {
        @Override // com.huawei.animationkit.computationalwallpaper.pattern.arranger.impl.VectorAnimation
        public int getDuration() {
            return 2000;
        }

        @Override // com.huawei.animationkit.computationalwallpaper.pattern.arranger.impl.VectorAnimation
        public List<VectorAnimation.b> getTranslateXKeyFrames() {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new VectorAnimation.b(0.0f, 0.5f));
            arrayList.add(new VectorAnimation.b(1.0f, 0.0f));
            return arrayList;
        }

        @Override // com.huawei.animationkit.computationalwallpaper.pattern.arranger.impl.VectorAnimation
        public List<VectorAnimation.b> getScaleKeyFrames() {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new VectorAnimation.b(0.0f, 2.0f));
            arrayList.add(new VectorAnimation.b(0.25f, 2.0f));
            arrayList.add(new VectorAnimation.b(1.0f, 1.0f));
            return arrayList;
        }
    }
}
