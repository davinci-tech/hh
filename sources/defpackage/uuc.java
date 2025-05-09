package defpackage;

import android.content.pm.PackageInfo;
import android.content.pm.Signature;
import android.util.Base64;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* loaded from: classes7.dex */
public class uuc {

    /* renamed from: a, reason: collision with root package name */
    public final String f17561a;
    public final Boolean b;
    public final String d;
    public final Set<String> e;

    public uuc(PackageInfo packageInfo, boolean z) {
        this(packageInfo.packageName, fgt_(packageInfo.signatures), packageInfo.versionName, z);
    }

    public uuc(String str, Set<String> set, String str2, boolean z) {
        this.d = str;
        this.e = set;
        this.f17561a = str2;
        this.b = Boolean.valueOf(z);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof uuc)) {
            return false;
        }
        uuc uucVar = (uuc) obj;
        return this.d.equals(uucVar.d) && this.f17561a.equals(uucVar.f17561a) && this.b == uucVar.b && this.e.equals(uucVar.e);
    }

    public int hashCode() {
        int hashCode = (((this.d.hashCode() * 92821) + this.f17561a.hashCode()) * 92821) + (this.b.booleanValue() ? 1 : 0);
        Iterator<String> it = this.e.iterator();
        while (it.hasNext()) {
            hashCode = (hashCode * 92821) + it.next().hashCode();
        }
        return hashCode;
    }

    public static String fgs_(Signature signature) {
        try {
            return Base64.encodeToString(MessageDigest.getInstance("SHA-512").digest(signature.toByteArray()), 10);
        } catch (NoSuchAlgorithmException unused) {
            throw new IllegalStateException("Platform does not supportSHA-512 hashing");
        }
    }

    public static Set<String> fgt_(Signature[] signatureArr) {
        HashSet hashSet = new HashSet();
        for (Signature signature : signatureArr) {
            hashSet.add(fgs_(signature));
        }
        return hashSet;
    }
}
