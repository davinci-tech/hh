package defpackage;

import android.util.Log;
import com.huawei.animationkit.computationalwallpaper.clock.ClockColorRule;
import com.huawei.animationkit.computationalwallpaper.coloranalysis.ColorRule;
import com.huawei.animationkit.computationalwallpaper.pattern.pattern.Pattern;
import com.huawei.animationkit.computationalwallpaper.pattern.pattern.impl.ColorPattern;
import com.huawei.animationkit.computationalwallpaper.pattern.pattern.impl.FireworksPattern;
import com.huawei.animationkit.computationalwallpaper.pattern.pattern.impl.KaleidoscopePattern;
import com.huawei.animationkit.computationalwallpaper.pattern.pattern.impl.LayerPattern;
import com.huawei.animationkit.computationalwallpaper.pattern.pattern.impl.VectorPattern;
import com.huawei.animationkit.computationalwallpaper.template.TemplateSelector;
import defpackage.aec;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

/* loaded from: classes8.dex */
public class aec {

    /* renamed from: a, reason: collision with root package name */
    private static final Map<String, ClockColorRule> f180a;
    private static final Map<String, ColorRule> b;
    private static final Map<String, Class<? extends Pattern>> c;
    private static aec e;
    private TemplateSelector f;
    private final Map<String, c> d = new HashMap();
    private final Map<String, a> i = new HashMap();

    static {
        HashMap hashMap = new HashMap();
        c = hashMap;
        HashMap hashMap2 = new HashMap();
        b = hashMap2;
        HashMap hashMap3 = new HashMap();
        f180a = hashMap3;
        e = null;
        hashMap.put("vector", VectorPattern.class);
        hashMap.put("kaleidoscope", KaleidoscopePattern.class);
        hashMap.put("color", ColorPattern.class);
        hashMap.put("fireworks", FireworksPattern.class);
        hashMap.put("layer", LayerPattern.class);
        hashMap2.put("flower", new add());
        hashMap2.put("plaid", new ade());
        hashMap2.put("scale", new adb());
        hashMap2.put("digital_hand", new acy());
        hashMap2.put("digital", new acw());
        hashMap3.put("vertical_board", new aby());
        hashMap3.put("horizontal_board", new abz());
        hashMap3.put("hand_clock", new abw());
        hashMap3.put("digital_hand", new abt());
        hashMap3.put("line_digital", new aca());
        hashMap3.put("slash_digital", new abx());
    }

    private aec() {
    }

    public static aec e() throws abv {
        if (e == null) {
            e = new aec();
        }
        if (abo.h()) {
            e.a();
        }
        return e;
    }

    static class c {
        private final List<d> e;

        private c() {
            this.e = new ArrayList();
        }
    }

    public static class d {

        /* renamed from: a, reason: collision with root package name */
        private String f182a;
        private boolean b;
        private List<e> c;
        private String d;
        private ColorRule e;
        private String f;
        private int g;
        private Class<? extends Pattern> h;
        private float i;
        private float j;
        private String k;

        public Class<? extends Pattern> b() {
            return this.h;
        }

        public ColorRule d() {
            return this.e;
        }

        public String i() {
            return this.f;
        }

        public List<e> c() {
            return this.c;
        }

        public String a() {
            return this.f182a;
        }

        public String j() {
            return this.k;
        }

        public float g() {
            return this.j;
        }

        public float h() {
            return this.i;
        }

        public int f() {
            return this.g;
        }

        public boolean e() {
            return this.b;
        }
    }

    static class a {

        /* renamed from: a, reason: collision with root package name */
        private final List<e> f181a;

        private a() {
            this.f181a = new ArrayList();
        }
    }

    public static class e {

        /* renamed from: a, reason: collision with root package name */
        private String f183a;
        private String b;
        private ClockColorRule c;
        private String d;
        private String e;

        public String e() {
            return this.e;
        }

        public String d() {
            return this.f183a;
        }

        public String c() {
            return this.d;
        }

        public String a() {
            return this.b;
        }

        public ClockColorRule b() {
            return this.c;
        }
    }

    public TemplateSelector c() {
        if (this.f == null) {
            this.f = new aef();
        }
        return this.f;
    }

    public List<d> a(String str) throws abv {
        if (this.d.containsKey(str)) {
            return ((c) Objects.requireNonNull(this.d.get(str))).e;
        }
        throw new abv("no category " + str);
    }

    private List<e> b(String str) throws abv {
        if (this.i.containsKey(str)) {
            return ((a) Objects.requireNonNull(this.i.get(str))).f181a;
        }
        throw new abv("no clock " + str);
    }

    private void a() throws abv {
        try {
            Log.i("Templates", "parse xml start");
            f(abo.d());
            d();
        } catch (abv e2) {
            Log.e("Templates", (String) Optional.ofNullable(e2.getMessage()).orElse(""));
            throw e2;
        } catch (IOException | XmlPullParserException unused) {
            Log.e("Templates", "parse xml failed, file name: " + abo.c());
            throw new abv("parse xml failed, file name: " + abo.c());
        } catch (NumberFormatException e3) {
            Log.e("Templates", "number format exception", e3);
            throw new abv(e3);
        }
    }

    private void f(String str) throws XmlPullParserException, IOException, abv {
        FileInputStream fileInputStream = new FileInputStream(str + File.separator + abo.c());
        XmlPullParser newPullParser = XmlPullParserFactory.newInstance().newPullParser();
        newPullParser.setInput(fileInputStream, "UTF-8");
        c cVar = new c();
        a aVar = new a();
        int eventType = newPullParser.getEventType();
        while (eventType != 1) {
            if (eventType != 2) {
                eventType = newPullParser.next();
            } else {
                if ("selector".equals(newPullParser.getName())) {
                    this.f = h(newPullParser.nextText());
                } else if ("category".equals(newPullParser.getName())) {
                    cVar = new c();
                    this.d.put(newPullParser.getAttributeValue("", "name"), cVar);
                } else if ("pattern".equals(newPullParser.getName())) {
                    cVar.e.add(c(newPullParser));
                } else if ("clockset".equals(newPullParser.getName())) {
                    aVar = new a();
                    this.i.put(newPullParser.getAttributeValue("", "name"), aVar);
                } else if ("clock".equals(newPullParser.getName())) {
                    e a2 = a(newPullParser);
                    if (abo.d(a2.e)) {
                        aVar.f181a.add(a2);
                    }
                }
                eventType = newPullParser.next();
            }
        }
    }

    private d c(XmlPullParser xmlPullParser) throws abv {
        d dVar = new d();
        dVar.h = d(xmlPullParser.getAttributeValue("", "pattern_type"));
        dVar.e = c(xmlPullParser.getAttributeValue("", "color_rule"));
        dVar.f = xmlPullParser.getAttributeValue("", "resource");
        dVar.d = xmlPullParser.getAttributeValue("", "clock");
        dVar.f182a = xmlPullParser.getAttributeValue("", "animation");
        dVar.k = xmlPullParser.getAttributeValue("", "tag");
        dVar.g = j(xmlPullParser.getAttributeValue("", "rotate_interval"));
        dVar.j = i(xmlPullParser.getAttributeValue("", "scale_min"));
        dVar.i = i(xmlPullParser.getAttributeValue("", "scale_max"));
        dVar.b = Boolean.parseBoolean(xmlPullParser.getAttributeValue("", "translate"));
        return dVar;
    }

    private e a(XmlPullParser xmlPullParser) throws abv {
        e eVar = new e();
        eVar.e = xmlPullParser.getAttributeValue("", "clock_type");
        eVar.d = xmlPullParser.getAttributeValue("", "resource_en");
        eVar.f183a = xmlPullParser.getAttributeValue("", "resource_zh");
        eVar.b = xmlPullParser.getAttributeValue("", "color_mode");
        eVar.c = e(xmlPullParser.getAttributeValue("", "color_rule"));
        return eVar;
    }

    private void d() throws abv {
        for (c cVar : this.d.values()) {
            for (d dVar : cVar.e) {
                dVar.c = b(dVar.d);
            }
            cVar.e.removeIf(new Predicate() { // from class: aed
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    boolean isEmpty;
                    isEmpty = ((aec.d) obj).c.isEmpty();
                    return isEmpty;
                }
            });
        }
    }

    private Class<? extends Pattern> d(String str) throws abv {
        if (str == null) {
            return null;
        }
        Map<String, Class<? extends Pattern>> map = c;
        if (!map.containsKey(str)) {
            throw new abv("invalidate pattern type " + str);
        }
        return map.get(str);
    }

    private ColorRule c(String str) throws abv {
        if (str == null) {
            return null;
        }
        Map<String, ColorRule> map = b;
        if (!map.containsKey(str)) {
            throw new abv("invalidate color rule " + str);
        }
        return map.get(str);
    }

    private ClockColorRule e(String str) throws abv {
        if (str == null) {
            return null;
        }
        Map<String, ClockColorRule> map = f180a;
        if (!map.containsKey(str)) {
            throw new abv("invalidate clock color rule " + str);
        }
        return map.get(str);
    }

    private TemplateSelector h(String str) {
        char c2;
        str.hashCode();
        int hashCode = str.hashCode();
        if (hashCode == -2021778750) {
            if (str.equals("bali_selector")) {
                c2 = 0;
            }
            c2 = 65535;
        } else if (hashCode != -1602342379) {
            if (hashCode == -118531761 && str.equals("watch_selector")) {
                c2 = 2;
            }
            c2 = 65535;
        } else {
            if (str.equals("watch8_selector")) {
                c2 = 1;
            }
            c2 = 65535;
        }
        if (c2 == 0) {
            return new aei();
        }
        if (c2 == 1) {
            return new aeh();
        }
        if (c2 == 2) {
            return new aeg();
        }
        return new aef();
    }

    private int j(String str) {
        if (str == null || str.isEmpty()) {
            return 0;
        }
        return Integer.parseInt(str);
    }

    private float i(String str) {
        if (str == null || str.isEmpty()) {
            return 1.0f;
        }
        return Float.parseFloat(str);
    }
}
