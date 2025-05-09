package defpackage;

import a.a.a.a.a.a;
import android.content.Context;
import com.hihonor.assistant.cardmgrsdk.model.CardDisplayRequestArg;
import com.hihonor.assistant.cardmgrsdk.model.DisplayResult;
import java.util.Optional;

/* loaded from: classes2.dex */
public class uj {
    public Context b;

    public static final class e {
        public static final uj e = new uj();
    }

    public static uj b() {
        return e.e;
    }

    public Optional<DisplayResult> b(CardDisplayRequestArg cardDisplayRequestArg) {
        a.b("BizCardMgr", "updateCard dispArg start:" + cardDisplayRequestArg);
        if (cardDisplayRequestArg == null) {
            a.a("BizCardMgr", "updateCard args can not be null");
            return Optional.empty();
        }
        cardDisplayRequestArg.setOperation(2);
        return c(cardDisplayRequestArg);
    }

    public Optional<DisplayResult> a(CardDisplayRequestArg cardDisplayRequestArg) {
        a.b("BizCardMgr", "removeCard dispArg start:" + cardDisplayRequestArg);
        if (cardDisplayRequestArg == null) {
            a.a("BizCardMgr", "removeCard args can not be null");
            return Optional.empty();
        }
        cardDisplayRequestArg.setOperation(3);
        return c(cardDisplayRequestArg);
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x0095, code lost:
    
        if (r3 != 0) goto L34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00a7, code lost:
    
        if (r0 == null) goto L35;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int d(com.hihonor.assistant.cardmgrsdk.model.CardDisplayRequestArg r6) {
        /*
            r5 = this;
            java.lang.String r0 = "queryCardInfo in"
            java.lang.String r1 = "BizCardMgr"
            a.a.a.a.a.a.b(r1, r0)
            android.content.Context r0 = r5.b
            r2 = 4
            if (r0 != 0) goto L12
            java.lang.String r6 = "queryCardInfo should Call BizCardMgr.getInstance().init() first."
            a.a.a.a.a.a.a(r1, r6)
            return r2
        L12:
            if (r6 != 0) goto L1a
            java.lang.String r6 = "queryCardInfo CardDisplayRequestArg is null."
            a.a.a.a.a.a.a(r1, r6)
            return r2
        L1a:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r3 = "queryCardInfo businessId:"
            r0.<init>(r3)
            java.lang.String r3 = r6.getBusinessId()
            r0.append(r3)
            java.lang.String r3 = " business:"
            r0.append(r3)
            java.lang.String r3 = r6.getBusiness()
            r0.append(r3)
            java.lang.String r0 = r0.toString()
            a.a.a.a.a.a.b(r1, r0)
            org.json.JSONObject r0 = new org.json.JSONObject
            r0.<init>()
            java.lang.String r3 = "businessId"
            java.lang.String r4 = r6.getBusinessId()     // Catch: org.json.JSONException -> L53
            r0.put(r3, r4)     // Catch: org.json.JSONException -> L53
            java.lang.String r3 = "business"
            java.lang.String r6 = r6.getBusiness()     // Catch: org.json.JSONException -> L53
            r0.put(r3, r6)     // Catch: org.json.JSONException -> L53
            goto L58
        L53:
            java.lang.String r6 = "queryCardInfo catch JSONException"
            a.a.a.a.a.a.a(r1, r6)
        L58:
            java.lang.String r6 = r0.toString()
            r0 = 0
            android.content.Context r3 = r5.b     // Catch: java.lang.Throwable -> L98 android.os.RemoteException -> L9a java.lang.IllegalArgumentException -> La2
            android.content.ContentResolver r3 = r3.getContentResolver()     // Catch: java.lang.Throwable -> L98 android.os.RemoteException -> L9a java.lang.IllegalArgumentException -> La2
            java.lang.String r4 = "content://com.hihonor.assistant.yoyocard"
            android.net.Uri r4 = android.net.Uri.parse(r4)     // Catch: java.lang.Throwable -> L98 android.os.RemoteException -> L9a java.lang.IllegalArgumentException -> La2
            android.content.ContentProviderClient r3 = r3.acquireContentProviderClient(r4)     // Catch: java.lang.Throwable -> L98 android.os.RemoteException -> L9a java.lang.IllegalArgumentException -> La2
            if (r3 == 0) goto L95
            java.lang.String r4 = "queryCardInfo"
            android.os.Bundle r6 = r3.call(r4, r6, r0)     // Catch: java.lang.Throwable -> L8f android.os.RemoteException -> L91 java.lang.IllegalArgumentException -> L93
            if (r6 == 0) goto L95
            java.lang.String r0 = "state"
            int r2 = r6.getInt(r0, r2)     // Catch: java.lang.Throwable -> L8f android.os.RemoteException -> L91 java.lang.IllegalArgumentException -> L93
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L8f android.os.RemoteException -> L91 java.lang.IllegalArgumentException -> L93
            java.lang.String r0 = "queryCardInfo over:"
            r6.<init>(r0)     // Catch: java.lang.Throwable -> L8f android.os.RemoteException -> L91 java.lang.IllegalArgumentException -> L93
            r6.append(r2)     // Catch: java.lang.Throwable -> L8f android.os.RemoteException -> L91 java.lang.IllegalArgumentException -> L93
            java.lang.String r6 = r6.toString()     // Catch: java.lang.Throwable -> L8f android.os.RemoteException -> L91 java.lang.IllegalArgumentException -> L93
            a.a.a.a.a.a.b(r1, r6)     // Catch: java.lang.Throwable -> L8f android.os.RemoteException -> L91 java.lang.IllegalArgumentException -> L93
            goto L95
        L8f:
            r6 = move-exception
            goto Lc0
        L91:
            r0 = r3
            goto L9a
        L93:
            r0 = r3
            goto La2
        L95:
            if (r3 == 0) goto Lad
            goto Laa
        L98:
            r6 = move-exception
            goto Lbf
        L9a:
            java.lang.String r6 = "queryCardInfo RemoteException"
            a.a.a.a.a.a.a(r1, r6)     // Catch: java.lang.Throwable -> L98
            if (r0 == 0) goto Lad
            goto La9
        La2:
            java.lang.String r6 = "queryCardInfo IllegalArgumentException"
            a.a.a.a.a.a.a(r1, r6)     // Catch: java.lang.Throwable -> L98
            if (r0 == 0) goto Lad
        La9:
            r3 = r0
        Laa:
            r3.close()
        Lad:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r0 = "queryCardInfo over: "
            r6.<init>(r0)
            r6.append(r2)
            java.lang.String r6 = r6.toString()
            a.a.a.a.a.a.b(r1, r6)
            return r2
        Lbf:
            r3 = r0
        Lc0:
            if (r3 == 0) goto Lc5
            r3.close()
        Lc5:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.uj.d(com.hihonor.assistant.cardmgrsdk.model.CardDisplayRequestArg):int");
    }

    public Optional<DisplayResult> e(CardDisplayRequestArg cardDisplayRequestArg) {
        a.b("BizCardMgr", "loadCard dispArg start:" + cardDisplayRequestArg);
        if (cardDisplayRequestArg == null) {
            a.a("BizCardMgr", "loadCard args can not be null");
            return Optional.empty();
        }
        cardDisplayRequestArg.setOperation(1);
        return c(cardDisplayRequestArg);
    }

    public void d(Context context) {
        if (context == null) {
            a.a("BizCardMgr", "init context is null");
        } else {
            this.b = context.getApplicationContext();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0099, code lost:
    
        if (r0 != null) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00ad, code lost:
    
        if (r2 == null) goto L38;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v5, types: [com.hihonor.assistant.cardmgrsdk.model.DisplayResult] */
    /* JADX WARN: Type inference failed for: r3v8, types: [com.hihonor.assistant.cardmgrsdk.model.DisplayResult] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.util.Optional<com.hihonor.assistant.cardmgrsdk.model.DisplayResult> c(com.hihonor.assistant.cardmgrsdk.model.CardDisplayRequestArg r7) {
        /*
            r6 = this;
            java.lang.String r0 = "operateCard in"
            java.lang.String r1 = "BizCardMgr"
            a.a.a.a.a.a.b(r1, r0)
            android.content.Context r0 = r6.b
            if (r0 != 0) goto L15
            java.lang.String r7 = "operateCard should Call BizCardMgr.getInstance().init() first."
            a.a.a.a.a.a.a(r1, r7)
            java.util.Optional r7 = java.util.Optional.empty()
            return r7
        L15:
            r2 = 0
            android.content.ContentResolver r0 = r0.getContentResolver()     // Catch: java.lang.Throwable -> L9c android.os.RemoteException -> L9e java.lang.IllegalArgumentException -> La7
            java.lang.String r3 = "content://com.hihonor.assistant.yoyocard"
            android.net.Uri r3 = android.net.Uri.parse(r3)     // Catch: java.lang.Throwable -> L9c android.os.RemoteException -> L9e java.lang.IllegalArgumentException -> La7
            android.content.ContentProviderClient r0 = r0.acquireContentProviderClient(r3)     // Catch: java.lang.Throwable -> L9c android.os.RemoteException -> L9e java.lang.IllegalArgumentException -> La7
            if (r0 == 0) goto L99
            android.os.Bundle r3 = new android.os.Bundle     // Catch: java.lang.Throwable -> L91 android.os.RemoteException -> L93 java.lang.IllegalArgumentException -> L96
            r3.<init>()     // Catch: java.lang.Throwable -> L91 android.os.RemoteException -> L93 java.lang.IllegalArgumentException -> L96
            java.lang.String r4 = "displayArg"
            r3.putParcelable(r4, r7)     // Catch: java.lang.Throwable -> L91 android.os.RemoteException -> L93 java.lang.IllegalArgumentException -> L96
            java.lang.String r4 = "onDisplayCard"
            java.lang.String r5 = ""
            android.os.Bundle r3 = r0.call(r4, r5, r3)     // Catch: java.lang.Throwable -> L91 android.os.RemoteException -> L93 java.lang.IllegalArgumentException -> L96
            if (r3 != 0) goto L47
            java.lang.String r7 = "operateCard bundle is null"
            a.a.a.a.a.a.c(r1, r7)     // Catch: java.lang.Throwable -> L91 android.os.RemoteException -> L93 java.lang.IllegalArgumentException -> L96
            java.util.Optional r7 = java.util.Optional.empty()     // Catch: java.lang.Throwable -> L91 android.os.RemoteException -> L93 java.lang.IllegalArgumentException -> L96
            r0.close()
            return r7
        L47:
            java.lang.Class<com.hihonor.assistant.cardmgrsdk.model.DisplayResult> r4 = com.hihonor.assistant.cardmgrsdk.model.DisplayResult.class
            java.lang.ClassLoader r4 = r4.getClassLoader()     // Catch: java.lang.Throwable -> L91 android.os.RemoteException -> L93 java.lang.IllegalArgumentException -> L96
            r3.setClassLoader(r4)     // Catch: java.lang.Throwable -> L91 android.os.RemoteException -> L93 java.lang.IllegalArgumentException -> L96
            java.lang.String r4 = "displayResult"
            android.os.Parcelable r3 = r3.getParcelable(r4)     // Catch: java.lang.Throwable -> L91 android.os.RemoteException -> L93 java.lang.IllegalArgumentException -> L96
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L91 android.os.RemoteException -> L93 java.lang.IllegalArgumentException -> L96
            java.lang.String r5 = "parcelable: "
            r4.<init>(r5)     // Catch: java.lang.Throwable -> L91 android.os.RemoteException -> L93 java.lang.IllegalArgumentException -> L96
            r4.append(r3)     // Catch: java.lang.Throwable -> L91 android.os.RemoteException -> L93 java.lang.IllegalArgumentException -> L96
            java.lang.String r4 = r4.toString()     // Catch: java.lang.Throwable -> L91 android.os.RemoteException -> L93 java.lang.IllegalArgumentException -> L96
            a.a.a.a.a.a.b(r1, r4)     // Catch: java.lang.Throwable -> L91 android.os.RemoteException -> L93 java.lang.IllegalArgumentException -> L96
            boolean r4 = r3 instanceof com.hihonor.assistant.cardmgrsdk.model.DisplayResult     // Catch: java.lang.Throwable -> L91 android.os.RemoteException -> L93 java.lang.IllegalArgumentException -> L96
            if (r4 == 0) goto L6f
            com.hihonor.assistant.cardmgrsdk.model.DisplayResult r3 = (com.hihonor.assistant.cardmgrsdk.model.DisplayResult) r3     // Catch: java.lang.Throwable -> L91 android.os.RemoteException -> L93 java.lang.IllegalArgumentException -> L96
        L6d:
            r2 = r3
            goto L7f
        L6f:
            com.hihonor.assistant.cardmgrsdk.model.DisplayResult r3 = new com.hihonor.assistant.cardmgrsdk.model.DisplayResult     // Catch: java.lang.Throwable -> L91 android.os.RemoteException -> L93 java.lang.IllegalArgumentException -> L96
            java.lang.String r4 = r7.getBusinessId()     // Catch: java.lang.Throwable -> L91 android.os.RemoteException -> L93 java.lang.IllegalArgumentException -> L96
            java.lang.String r7 = r7.getBusiness()     // Catch: java.lang.Throwable -> L91 android.os.RemoteException -> L93 java.lang.IllegalArgumentException -> L96
            r5 = 203(0xcb, float:2.84E-43)
            r3.<init>(r4, r7, r5)     // Catch: java.lang.Throwable -> L91 android.os.RemoteException -> L93 java.lang.IllegalArgumentException -> L96
            goto L6d
        L7f:
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L91 android.os.RemoteException -> L93 java.lang.IllegalArgumentException -> L96
            java.lang.String r3 = "operateCard dispArg over:"
            r7.<init>(r3)     // Catch: java.lang.Throwable -> L91 android.os.RemoteException -> L93 java.lang.IllegalArgumentException -> L96
            r7.append(r2)     // Catch: java.lang.Throwable -> L91 android.os.RemoteException -> L93 java.lang.IllegalArgumentException -> L96
            java.lang.String r7 = r7.toString()     // Catch: java.lang.Throwable -> L91 android.os.RemoteException -> L93 java.lang.IllegalArgumentException -> L96
            a.a.a.a.a.a.b(r1, r7)     // Catch: java.lang.Throwable -> L91 android.os.RemoteException -> L93 java.lang.IllegalArgumentException -> L96
            goto L99
        L91:
            r7 = move-exception
            goto Lbc
        L93:
            r7 = r2
            r2 = r0
            goto L9f
        L96:
            r7 = r2
            r2 = r0
            goto La8
        L99:
            if (r0 == 0) goto Lb6
            goto Lb1
        L9c:
            r7 = move-exception
            goto Lbb
        L9e:
            r7 = r2
        L9f:
            java.lang.String r0 = "operateCard RemoteException"
            a.a.a.a.a.a.a(r1, r0)     // Catch: java.lang.Throwable -> L9c
            if (r2 == 0) goto Lb5
            goto Laf
        La7:
            r7 = r2
        La8:
            java.lang.String r0 = "operateCard IllegalArgumentException"
            a.a.a.a.a.a.a(r1, r0)     // Catch: java.lang.Throwable -> L9c
            if (r2 == 0) goto Lb5
        Laf:
            r0 = r2
            r2 = r7
        Lb1:
            r0.close()
            goto Lb6
        Lb5:
            r2 = r7
        Lb6:
            java.util.Optional r7 = java.util.Optional.ofNullable(r2)
            return r7
        Lbb:
            r0 = r2
        Lbc:
            if (r0 == 0) goto Lc1
            r0.close()
        Lc1:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.uj.c(com.hihonor.assistant.cardmgrsdk.model.CardDisplayRequestArg):java.util.Optional");
    }
}
