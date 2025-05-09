package org.eclipse.californium.scandium.dtls.cipher;

import defpackage.vbu;
import java.security.GeneralSecurityException;

/* loaded from: classes7.dex */
public class ThreadLocalCrypto<CryptoFunction> {

    /* renamed from: a, reason: collision with root package name */
    private final GeneralSecurityException f15911a;
    private final ThreadLocal<CryptoFunction> c;
    private final Factory<CryptoFunction> d;

    public interface Factory<CryptoFunction> {
        CryptoFunction getInstance() throws GeneralSecurityException;
    }

    static {
        vbu.c();
    }

    /* JADX WARN: Type inference failed for: r0v0 */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.ThreadLocal<CryptoFunction>] */
    /* JADX WARN: Type inference failed for: r0v4 */
    public ThreadLocalCrypto(Factory<CryptoFunction> factory) {
        GeneralSecurityException e;
        GeneralSecurityException generalSecurityException;
        ?? r0 = (ThreadLocal<CryptoFunction>) null;
        try {
            CryptoFunction factory2 = factory.getInstance();
            if (factory2 != null) {
                try {
                    ThreadLocal threadLocal = new ThreadLocal();
                    try {
                        threadLocal.set(factory2);
                        generalSecurityException = r0;
                    } catch (GeneralSecurityException 
                    /*  JADX ERROR: Method code generation error
                        java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.SSAVar.getCodeVar()" because "ssaVar" is null
                        	at jadx.core.codegen.RegionGen.makeCatchBlock(RegionGen.java:369)
                        	at jadx.core.codegen.RegionGen.makeTryCatch(RegionGen.java:328)
                        	at jadx.core.dex.regions.TryCatchRegion.generate(TryCatchRegion.java:85)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                        	at jadx.core.dex.regions.Region.generate(Region.java:35)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                        	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:83)
                        	at jadx.core.codegen.RegionGen.makeTryCatch(RegionGen.java:317)
                        	at jadx.core.dex.regions.TryCatchRegion.generate(TryCatchRegion.java:85)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                        	at jadx.core.dex.regions.Region.generate(Region.java:35)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                        	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:83)
                        	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:126)
                        	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                        	at jadx.core.dex.regions.Region.generate(Region.java:35)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                        	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:83)
                        	at jadx.core.codegen.RegionGen.makeTryCatch(RegionGen.java:317)
                        	at jadx.core.dex.regions.TryCatchRegion.generate(TryCatchRegion.java:85)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                        	at jadx.core.dex.regions.Region.generate(Region.java:35)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                        	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:297)
                        	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:276)
                        	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:406)
                        	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:335)
                        	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:301)
                        	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
                        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
                        	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                        	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:261)
                        */
                    /*
                        this = this;
                        r3.<init>()
                        r0 = 0
                        java.lang.Object r1 = r4.getInstance()     // Catch: java.security.GeneralSecurityException -> L3a
                        if (r1 == 0) goto L19
                        java.lang.ThreadLocal r2 = new java.lang.ThreadLocal     // Catch: java.security.GeneralSecurityException -> L17
                        r2.<init>()     // Catch: java.security.GeneralSecurityException -> L17
                        r2.set(r1)     // Catch: java.security.GeneralSecurityException -> L15
                    L12:
                        r1 = r0
                        r0 = r2
                        goto L3d
                    L15:
                        r0 = move-exception
                        goto L12
                    L17:
                        r1 = move-exception
                        goto L3d
                    L19:
                        java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.security.GeneralSecurityException -> L3a
                        r1.<init>()     // Catch: java.security.GeneralSecurityException -> L3a
                        java.security.GeneralSecurityException r2 = new java.security.GeneralSecurityException     // Catch: java.security.GeneralSecurityException -> L3a
                        java.lang.Class r4 = r4.getClass()     // Catch: java.security.GeneralSecurityException -> L3a
                        java.lang.String r4 = r4.getSimpleName()     // Catch: java.security.GeneralSecurityException -> L3a
                        r1.append(r4)     // Catch: java.security.GeneralSecurityException -> L3a
                        java.lang.String r4 = " not supported!"
                        r1.append(r4)     // Catch: java.security.GeneralSecurityException -> L3a
                        java.lang.String r4 = r1.toString()     // Catch: java.security.GeneralSecurityException -> L3a
                        r2.<init>(r4)     // Catch: java.security.GeneralSecurityException -> L3a
                        r4 = r0
                        r1 = r2
                        goto L3d
                    L3a:
                        r4 = move-exception
                        r1 = r4
                        r4 = r0
                    L3d:
                        r3.c = r0
                        r3.d = r4
                        r3.f15911a = r1
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: org.eclipse.californium.scandium.dtls.cipher.ThreadLocalCrypto.<init>(org.eclipse.californium.scandium.dtls.cipher.ThreadLocalCrypto$Factory):void");
                }

                public CryptoFunction b() {
                    if (!c()) {
                        return null;
                    }
                    CryptoFunction cryptofunction = this.c.get();
                    if (cryptofunction != null) {
                        return cryptofunction;
                    }
                    try {
                        cryptofunction = this.d.getInstance();
                        this.c.set(cryptofunction);
                        return cryptofunction;
                    } catch (GeneralSecurityException unused) {
                        return cryptofunction;
                    }
                }

                public CryptoFunction d() throws GeneralSecurityException {
                    GeneralSecurityException generalSecurityException = this.f15911a;
                    if (generalSecurityException != null) {
                        throw generalSecurityException;
                    }
                    return b();
                }

                public final boolean c() {
                    return this.f15911a == null;
                }
            }
