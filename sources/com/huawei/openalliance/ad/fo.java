package com.huawei.openalliance.ad;

import android.content.Context;
import android.content.SharedPreferences;
import com.huawei.openalliance.ad.constant.TagConstants;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public class fo implements gh {
    private static gh b;
    private static final byte[] c = new byte[0];

    /* renamed from: a, reason: collision with root package name */
    private final SharedPreferences f6878a;
    private final byte[] d = new byte[0];
    private Map<String, String> e;

    @Override // com.huawei.openalliance.ad.gh
    public long e(String str) {
        synchronized (this.d) {
            SharedPreferences sharedPreferences = this.f6878a;
            if (sharedPreferences == null) {
                return 0L;
            }
            return sharedPreferences.getLong(str + TagConstants.SYNC_TIME_SUFFIX, 0L);
        }
    }

    @Override // com.huawei.openalliance.ad.gh
    public long d(String str) {
        synchronized (this.d) {
            SharedPreferences sharedPreferences = this.f6878a;
            if (sharedPreferences == null) {
                return 0L;
            }
            return sharedPreferences.getLong(str + TagConstants.UPDATE_TIME_SUFFIX, 0L);
        }
    }

    @Override // com.huawei.openalliance.ad.gh
    public int c(String str) {
        synchronized (this.d) {
            SharedPreferences sharedPreferences = this.f6878a;
            if (sharedPreferences == null) {
                return -1;
            }
            return sharedPreferences.getInt(str + TagConstants.TRIGGER_MODE_SUFFIX, -1);
        }
    }

    @Override // com.huawei.openalliance.ad.gh
    public Map<String, String> b() {
        synchronized (this.d) {
            SharedPreferences sharedPreferences = this.f6878a;
            if (sharedPreferences == null) {
                return null;
            }
            Map<String, String> map = this.e;
            if (map != null) {
                return map;
            }
            String string = sharedPreferences.getString("user_tag", "");
            if (com.huawei.openalliance.ad.utils.cz.b(string)) {
                return null;
            }
            Map<String, String> map2 = (Map) com.huawei.openalliance.ad.utils.be.b(string, Map.class, new Class[0]);
            this.e = map2;
            return map2;
        }
    }

    @Override // com.huawei.openalliance.ad.gh
    public String b(String str) {
        synchronized (this.d) {
            SharedPreferences sharedPreferences = this.f6878a;
            if (sharedPreferences == null) {
                return "";
            }
            return sharedPreferences.getString(str + TagConstants.TAG_VALUE_SUFFIX, "");
        }
    }

    @Override // com.huawei.openalliance.ad.gh
    public void a(List<String> list, long j) {
        synchronized (this.d) {
            if (this.f6878a != null && !com.huawei.openalliance.ad.utils.bg.a(list)) {
                SharedPreferences.Editor edit = this.f6878a.edit();
                Iterator<String> it = list.iterator();
                while (it.hasNext()) {
                    edit.putLong(it.next() + TagConstants.SYNC_TIME_SUFFIX, j);
                }
                edit.commit();
            }
        }
    }

    @Override // com.huawei.openalliance.ad.gh
    public void a(String str, String str2) {
        synchronized (this.d) {
            SharedPreferences sharedPreferences = this.f6878a;
            if (sharedPreferences == null) {
                return;
            }
            sharedPreferences.edit().putString(str + TagConstants.TAG_VALUE_SUFFIX, str2).commit();
        }
    }

    @Override // com.huawei.openalliance.ad.gh
    public void a(String str, long j) {
        synchronized (this.d) {
            SharedPreferences sharedPreferences = this.f6878a;
            if (sharedPreferences == null) {
                return;
            }
            sharedPreferences.edit().putLong(str + TagConstants.UPDATE_TIME_SUFFIX, j).commit();
        }
    }

    @Override // com.huawei.openalliance.ad.gh
    public void a(String str, int i) {
        synchronized (this.d) {
            SharedPreferences sharedPreferences = this.f6878a;
            if (sharedPreferences == null) {
                return;
            }
            sharedPreferences.edit().putInt(str + TagConstants.TRIGGER_MODE_SUFFIX, i).commit();
        }
    }

    @Override // com.huawei.openalliance.ad.gh
    public void a(String str) {
        synchronized (this.d) {
            if (this.f6878a == null) {
                return;
            }
            this.e = (Map) com.huawei.openalliance.ad.utils.be.b(str, Map.class, new Class[0]);
            this.f6878a.edit().putString("user_tag", str).commit();
        }
    }

    @Override // com.huawei.openalliance.ad.gh
    public void a(long j) {
        synchronized (this.d) {
            SharedPreferences sharedPreferences = this.f6878a;
            if (sharedPreferences == null) {
                return;
            }
            sharedPreferences.edit().putLong("last_query_tag_time", j).commit();
        }
    }

    @Override // com.huawei.openalliance.ad.gh
    public long a() {
        synchronized (this.d) {
            SharedPreferences sharedPreferences = this.f6878a;
            if (sharedPreferences == null) {
                return 0L;
            }
            return sharedPreferences.getLong("last_query_tag_time", 0L);
        }
    }

    private static gh b(Context context) {
        gh ghVar;
        synchronized (c) {
            if (b == null) {
                b = new fo(context);
            }
            ghVar = b;
        }
        return ghVar;
    }

    public static gh a(Context context) {
        return b(context);
    }

    private fo(Context context) {
        try {
            this.f6878a = context.getApplicationContext().getSharedPreferences("hiad_user_tags_sp", 0);
        } catch (Throwable th) {
            try {
                ho.c("UserTagSpHandler", "get SharedPreference exception: %s", th.getClass().getSimpleName());
            } finally {
                this.f6878a = null;
            }
        }
    }
}
