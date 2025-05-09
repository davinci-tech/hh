package defpackage;

import java.net.InetSocketAddress;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import org.eclipse.californium.elements.util.ClockUtil;
import org.eclipse.californium.scandium.dtls.cipher.CipherSuite;

/* loaded from: classes7.dex */
public class vby {
    public static final long b = TimeUnit.SECONDS.toNanos(60);

    /* renamed from: a, reason: collision with root package name */
    private SecretKey f17657a;
    private long c;
    private SecretKey e;
    private final ReentrantReadWriteLock d = new ReentrantReadWriteLock();
    private final SecureRandom i = new SecureRandom();
    private final byte[] f = new byte[32];

    private SecretKey b() {
        this.d.readLock().lock();
        long d = ClockUtil.d();
        try {
            SecretKey secretKey = this.e;
            if (secretKey != null) {
                if (d - this.c < 0) {
                    return secretKey;
                }
            }
            this.d.readLock().unlock();
            this.d.writeLock().lock();
            try {
                SecretKey secretKey2 = this.e;
                if (secretKey2 != null && d - this.c < 0) {
                    return secretKey2;
                }
                this.i.nextBytes(this.f);
                this.c = d + b;
                this.f17657a = this.e;
                SecretKey e = vfh.e(this.f, "MAC");
                this.e = e;
                return e;
            } finally {
                this.d.writeLock().unlock();
            }
        } finally {
            this.d.readLock().unlock();
        }
    }

    private SecretKey a() {
        this.d.readLock().lock();
        try {
            return this.f17657a;
        } finally {
            this.d.readLock().unlock();
        }
    }

    private byte[] d(InetSocketAddress inetSocketAddress, vck vckVar, SecretKey secretKey) throws GeneralSecurityException {
        Mac threadLocalMac = CipherSuite.TLS_PSK_WITH_AES_128_CBC_SHA256.getThreadLocalMac();
        threadLocalMac.init(secretKey);
        threadLocalMac.update(inetSocketAddress.getAddress().getAddress());
        int port = inetSocketAddress.getPort();
        threadLocalMac.update((byte) (port >>> 8));
        threadLocalMac.update((byte) port);
        vckVar.d(threadLocalMac);
        return threadLocalMac.doFinal();
    }

    public byte[] d(InetSocketAddress inetSocketAddress, vck vckVar) throws GeneralSecurityException {
        return d(inetSocketAddress, vckVar, b());
    }

    public byte[] b(InetSocketAddress inetSocketAddress, vck vckVar) throws GeneralSecurityException {
        SecretKey a2 = a();
        if (a2 != null) {
            return d(inetSocketAddress, vckVar, a2);
        }
        return null;
    }
}
