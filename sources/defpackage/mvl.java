package defpackage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.huawei.health.socialshare.model.socialsharebean.ShareDataInfo;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.utils.StringUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.FileUtils;

/* loaded from: classes6.dex */
public class mvl {
    private static final Object d = new Object();
    private mvw b;
    private List<String> c;
    private Map<Integer, mvq> e;

    private mvl() {
        this.c = new ArrayList();
        c();
        this.b = mvw.d();
    }

    static class d {
        private static final mvl c = new mvl();
    }

    public static final mvl b() {
        return d.c;
    }

    private void c() {
        synchronized (d) {
            if (this.e == null) {
                String c = mwa.c(mus.h);
                if (StringUtils.g(c)) {
                    LogUtil.b("ShareConfigManager", "share config is empty.");
                    return;
                }
                try {
                    this.e = (Map) new GsonBuilder().create().fromJson(CommonUtil.z(c), new TypeToken<Map<Integer, mvq>>() { // from class: mvl.2
                    }.getType());
                } catch (JsonSyntaxException e) {
                    this.e = new HashMap();
                    FileUtils.deleteQuietly(FileUtils.getFile(mus.h));
                    LogUtil.b("ShareConfigManager", "parse json exception.", LogAnonymous.b((Throwable) e));
                }
                Map<Integer, mvq> map = this.e;
                if (map != null) {
                    LogUtil.a("ShareConfigManager", "Share data size:", Integer.valueOf(map.size()));
                } else {
                    LogUtil.h("ShareConfigManager", "Share data is null");
                }
            }
        }
    }

    public void e() {
        c(new GsonBuilder().create());
        this.b.a();
        a();
    }

    private void a() {
        if (this.c.size() > 0) {
            StringBuilder sb = new StringBuilder();
            for (String str : this.c) {
                boolean a2 = mwa.a(str);
                sb.append("delete file: ");
                sb.append(str);
                sb.append(":");
                sb.append(a2);
                sb.append('\n');
            }
            LogUtil.a("ShareConfigManager", sb.toString());
        }
    }

    private void c(Gson gson) {
        synchronized (d) {
            Map<Integer, mvq> map = this.e;
            if (map != null) {
                LogUtil.a("ShareConfigManager", "write share config json result:", Boolean.valueOf(mwa.b(mus.h, gson.toJson(map))));
            }
        }
    }

    mvq a(int i) {
        mvq mvqVar;
        synchronized (d) {
            Map<Integer, mvq> map = this.e;
            mvqVar = map == null ? null : map.get(Integer.valueOf(i));
        }
        return mvqVar;
    }

    public void c(int i, ShareDataInfo shareDataInfo, int i2) {
        synchronized (d) {
            if (this.e == null) {
                this.e = new HashMap();
            }
            mvq mvqVar = this.e.get(Integer.valueOf(i));
            if (mvqVar == null) {
                mvqVar = new mvq(i);
            }
            mvqVar.e(System.currentTimeMillis());
            if (this.b.a(i2) != null) {
                this.b.a(i2).updateData(mvqVar, shareDataInfo);
            }
            this.e.put(Integer.valueOf(i), mvqVar);
        }
    }

    void e(int i, ShareDataInfo shareDataInfo, int i2) {
        synchronized (d) {
            Map<Integer, mvq> map = this.e;
            if (map != null && map.get(Integer.valueOf(i)) != null) {
                mvq mvqVar = this.e.get(Integer.valueOf(i));
                if (this.b.a(i2) != null) {
                    this.b.a(i2).deleteData(mvqVar, shareDataInfo);
                }
                this.e.put(Integer.valueOf(i), mvqVar);
                if (!StringUtils.g(shareDataInfo.getSportTypes()) || (shareDataInfo instanceof mut)) {
                    return;
                }
                this.c.add(shareDataInfo.getPath());
                return;
            }
            LogUtil.b("ShareConfigManager", "empty data map or sportType not exist.");
        }
    }

    Map<Integer, ShareDataInfo> a(List<Integer> list, int i) {
        HashMap hashMap = new HashMap();
        if (koq.b(list)) {
            return hashMap;
        }
        for (Integer num : list) {
            if (num != null) {
                ShareDataInfo a2 = a(i, num.intValue());
                if (a2 == null) {
                    LogUtil.b("ShareConfigManager", "id is not exist.");
                } else {
                    hashMap.put(num, a2);
                }
            }
        }
        return hashMap;
    }

    ShareDataInfo a(int i, int i2) {
        return this.b.a(i).getShareDataById(i2);
    }

    public fea c(int i) {
        mvq a2 = a(i);
        if (a2 == null) {
            return null;
        }
        return this.b.c(a2);
    }
}
