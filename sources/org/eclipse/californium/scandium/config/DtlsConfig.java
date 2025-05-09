package org.eclipse.californium.scandium.config;

import defpackage.vaq;
import defpackage.vat;
import defpackage.vau;
import defpackage.vav;
import defpackage.vaw;
import defpackage.vay;
import defpackage.vba;
import defpackage.vbh;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.eclipse.californium.elements.config.BasicDefinition;
import org.eclipse.californium.elements.config.BasicListDefinition;
import org.eclipse.californium.elements.config.CertificateAuthenticationMode;
import org.eclipse.californium.elements.config.Configuration;
import org.eclipse.californium.elements.config.DocumentedDefinition;
import org.eclipse.californium.scandium.dtls.ExtendedMasterSecretMode;
import org.eclipse.californium.scandium.dtls.MaxFragmentLengthExtension;
import org.eclipse.californium.scandium.dtls.SignatureAndHashAlgorithm;
import org.eclipse.californium.scandium.dtls.cipher.CipherSuite;
import org.eclipse.californium.scandium.dtls.cipher.XECDHECryptography;

/* loaded from: classes7.dex */
public final class DtlsConfig {

    /* renamed from: a, reason: collision with root package name */
    public static final vay f15899a;
    public static final vat aa;
    public static final vav<CipherSuite> ab;

    @Deprecated
    public static final vat ac;
    public static final vaq ad;
    public static final vaq ae;
    public static final vaq af;
    public static final vat ag;
    public static final vaq ah;
    public static final vat ai;
    public static final vaq aj;
    public static final vau ak;
    public static final vat al;
    public static final vau an;
    public static final vaw<DtlsRole> ao;
    public static final vat ap;
    public static final vaq ar;
    public static final d as;
    public static final vay at;
    public static final vaq au;
    public static final vaq av;
    public static final vaq aw;
    public static final vaq ax;
    public static final vaq ay;
    public static final vaq az;
    public static final vav<CipherSuite> b;
    public static final vat ba;
    public static final vaq bb;
    public static final vat bc;
    public static final vaq bd;
    public static final vaq be;
    public static final vaq bf;
    public static final vaq bg;
    public static final vaq bh;
    public static final vaq bj;
    public static final vat bm;
    public static final vav<CipherSuite.CertificateKeyAlgorithm> c;
    public static final Configuration.ModuleDefinitionsProvider e;
    public static final vaw<CertificateAuthenticationMode> f;
    public static final vav<XECDHECryptography.SupportedGroup> g;
    public static final vat j;
    public static final vba k;
    public static final vat l;
    public static final vat m;
    public static final vay n;
    public static final vaw<ExtendedMasterSecretMode> o;
    public static final vat p;
    public static final vat q;
    public static final vaw<MaxFragmentLengthExtension.Length> r;
    public static final vat s;
    public static final vat t;
    public static final vat u;
    public static final vat v;
    public static final vat w;
    public static final vat x;
    public static final vat z;
    public static final vay aq = new vay("DTLS.SESSION_TIMEOUT", "DTLS session timeout. Currently not supported.", 1, TimeUnit.HOURS);
    public static final vay d = new vay("DTLS.AUTO_HANDSHAKE_TIMEOUT", "DTLS auto-handshake timeout. After that period without exchanging messages, a new message will initiate a handshake. Must not be used with SERVER_ONLY! Common value will be \"30[s]\" in order to compensate assumed NAT timeouts. <blank>, disabled.");
    public static final vat i = new vat("DTLS.CONNECTION_ID_LENGTH", "DTLS connection ID length. <blank> disabled, 0 enables support without active use of CID.", null, 0);
    public static final vat h = new vat("DTLS.CONNECTION_ID_NODE_ID", "DTLS cluster-node ID used for connection ID. <blank> not used.", null, 0);
    public static final vay am = new vay("DTLS.RETRANSMISSION_TIMEOUT", "DTLS initial retransmission timeout.", 2000, TimeUnit.MILLISECONDS);
    public static final vay y = new vay("DTLS.MAX_RETRANSMISSION_TIMEOUT", "DTLS maximum retransmission timeout.", 60000, TimeUnit.MILLISECONDS);

    public enum DtlsRole {
        CLIENT_ONLY,
        SERVER_ONLY,
        BOTH
    }

    public static class d extends BasicListDefinition<SignatureAndHashAlgorithm> {
        public d(String str, String str2) {
            super(str, str2, null);
        }

        @Override // org.eclipse.californium.elements.config.DocumentedDefinition
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public String writeValue(List<SignatureAndHashAlgorithm> list) {
            StringBuilder sb = new StringBuilder();
            Iterator<SignatureAndHashAlgorithm> it = list.iterator();
            while (it.hasNext()) {
                sb.append(it.next().c());
                sb.append(", ");
            }
            sb.setLength(sb.length() - 2);
            return sb.toString();
        }

        @Override // org.eclipse.californium.elements.config.BasicListDefinition, org.eclipse.californium.elements.config.DocumentedDefinition
        public List<SignatureAndHashAlgorithm> checkValue(List<SignatureAndHashAlgorithm> list) throws vbh {
            if (list != null) {
                for (SignatureAndHashAlgorithm signatureAndHashAlgorithm : list) {
                    if (!signatureAndHashAlgorithm.j()) {
                        throw new IllegalArgumentException(signatureAndHashAlgorithm + " is not supported by the JCE!");
                    }
                }
            }
            return super.checkValue((List) list);
        }

        @Override // org.eclipse.californium.elements.config.DocumentedDefinition
        public boolean isAssignableFrom(Object obj) {
            if (!(obj instanceof List)) {
                return false;
            }
            for (Object obj2 : (List) obj) {
                if (!(obj2 instanceof SignatureAndHashAlgorithm)) {
                    throw new IllegalArgumentException(obj2 + " is no SignatureAndHashAlgorithm");
                }
            }
            return true;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // org.eclipse.californium.elements.config.DocumentedDefinition
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public List<SignatureAndHashAlgorithm> parseValue(String str) {
            String[] split = str.split(",");
            ArrayList arrayList = new ArrayList(split.length);
            for (String str2 : split) {
                arrayList.add(SignatureAndHashAlgorithm.d(str2.trim()));
            }
            return arrayList;
        }

        @Override // org.eclipse.californium.elements.config.DocumentedDefinition
        public String getTypeName() {
            return "List<SignatureAndHashAlgorithm>";
        }
    }

    static {
        Float valueOf = Float.valueOf(1.0f);
        an = new vau("DTLS.RETRANSMISSION_INIT_RANDOM", "DTLS random factor for initial retransmission timeout.", valueOf, valueOf);
        ak = new vau("DTLS.RETRANSMISSION_TIMEOUT_SCALE", "DTLS scale factor for retransmission backoff-timeout.", Float.valueOf(2.0f), valueOf);
        f15899a = new vay("DTLS.ADDITIONAL_ECC_TIMEOUT", "DTLS additional initial timeout for ECC related flights.", 0L, TimeUnit.MILLISECONDS);
        w = new vat("DTLS.MAX_RETRANSMISSIONS", "DTLS maximum number of flight retransmissions.", 4, 0);
        al = new vat("DTLS.RETRANSMISSION_BACKOFF", "Number of flight-retransmissions before switching to backoff mode using single handshake messages in single record datagrams.", null, 0);
        ar = new vaq("DTLS.SERVER_USE_SESSION_ID", "Enable server to use a session ID in order to support session resumption.", true);
        bb = new vaq("DTLS.USE_EARLY_STOP_RETRANSMISSION", "Stop retransmission on receiving the first message of the next flight, not waiting for the last message.", true);
        ag = new vat("DTLS.RECORD_SIZE_LIMIT", "DTLS record size limit (RFC 8449). Between 64 and 16K.", null, 64);
        r = new vaw<>("DTLS.MAX_FRAGMENT_SIZE", "DTLS maximum fragment length (RFC 6066).", MaxFragmentLengthExtension.Length.values());
        t = new vat("DTLS.MAX_FRAGMENTED_HANDSHAKE_MESSAGE_LENGTH", "DTLS maximum length of reassembled fragmented handshake message.\nMust be large enough for used certificates.", 8192, 64);
        bh = new vaq("DTLS.USE_MULTI_RECORD_MESSAGES", "Use multiple DTLS records in UDP messages.");
        bd = new vaq("DTLS.USE_MULTI_HANDSHAKE_MESSAGE_RECORDS", "Use multiple handshake messages in DTLS records.\nNot all libraries may have implemented this!");
        f = new vaw<>("DTLS.CLIENT_AUTHENTICATION_MODE", "DTLS client authentication mode for certificate based cipher suites.", CertificateAuthenticationMode.NEEDED, CertificateAuthenticationMode.values());
        bj = new vaq("DTLS.VERIFY_SERVER_CERTIFICATES_SUBJECT", "DTLS verifies the server certificate's subjects.", true);
        ao = new vaw<>("DTLS.ROLE", "DTLS role.", DtlsRole.BOTH, DtlsRole.values());
        v = new vat("DTLS.MAX_TRANSMISSION_UNIT", "DTLS MTU (Maximum Transmission Unit).\nMust be used, if the MTU of the local network doesn't apply, e.g. if ip-tunnels are used.", null, 64);
        aa = new vat("DTLS.MAX_TRANSMISSION_UNIT_LIMIT", "DTLS MTU (Maximum Transmission Unit) limit for local auto detection.", null, 64);
        k = new vba("DTLS.DEFAULT_HANDSHAKE_MODE", "DTLS default handshake mode.", "auto", "none", "auto");
        l = new vat("DTLS.MAX_CONNECTIONS", "DTLS maximum connections.", 150000, 1);
        at = new vay("DTLS.STALE_CONNECTION_THRESHOLD", "DTLS threshold for state connections. Connections will only get removed for new ones, if at least for that threshold no messages are exchanged using that connection.", 1800L, TimeUnit.SECONDS);
        ac = new vat("DTLS.OUTBOUND_MESSAGE_BUFFER_SIZE", "DTLS buffer size for outbound messages");
        x = new vat("DTLS.MAX_PENDING_OUTBOUND_JOBS", "Maximum number of jobs for outbound DTLS messages.", 50000, 64);
        u = new vat("DTLS.MAX_PENDING_INBOUND_JOBS", "Maximum number of jobs for inbound DTLS messages.", 50000, 64);
        s = new vat("DTLS.MAX_PENDING_HANDSHAKE_RESULT_JOBS", "Maximum number of jobs for DTLS handshake results.", 5000, 64);
        p = new vat("DTLS.MAX_DEFERRED_OUTBOUND_APPLICATION_MESSAGES", "DTLS maximum deferred outbound application messages.", 10, 0);
        q = new vat("DTLS.MAX_DEFERRED_INBOUND_RECORDS", "DTLS maximum size of all deferred inbound messages.", 8192, 0);
        z = new vat("DTLS.RECEIVER_THREAD_COUNT", "Number of DTLS receiver threads.", 1, 0);
        j = new vat("DTLS.CONNECTOR_THREAD_COUNT", "Number of DTLS connector threads.", 1, 0);
        ai = new vat("DTLS.RECEIVE_BUFFER_SIZE", "DTLS receive-buffer size. Empty or 0 to use the OS default.", null, 64);
        ap = new vat("DTLS.SEND_BUFFER_SIZE", "DTLS send-buffer size. Empty or 0 to use the OS default.", null, 64);
        bf = new vaq("DTLS.USE_SERVER_NAME_INDICATION", "DTLS use server name indication.", false);
        o = new vaw<>("DTLS.EXTENDED_MASTER_SECRET_MODE", "DTLS extended master secret mode.", ExtendedMasterSecretMode.ENABLED, ExtendedMasterSecretMode.values());
        bm = new vat("DTLS.VERIFY_PEERS_ON_RESUMPTION_THRESHOLD", "DTLS verify peers on resumption threshold in percent.", 30, 0);
        be = new vaq("DTLS.USE_HELLO_VERIFY_REQUEST_FOR_PSK", "DTLS use a HELLO_VERIFY_REQUEST for PSK cipher suites to protect against spoofing.", true);
        bg = new vaq("DTLS.USE_HELLO_VERIFY_REQUEST", "DTLS use a HELLO_VERIFY_REQUESt to protect against spoofing.", true);
        az = new vaq("DTLS.USE_USE_ANTI_REPLAY_FILTER", "DTLS use the anti-replay-filter.", true);
        bc = new vat("DTLS.USE_DISABLED_WINDOW_FOR_ANTI_REPLAY_FILTER", "DTLS use a disabled window for the anti-replay-filter. -1 := extend the disabled window to start of session, 0 := normal window, <n> := disabled window of size <n>.", 0, -1);
        au = new vaq("DTLS.UPDATE_ADDRESS_USING_CID_ON_NEWER_RECORDS", "DTLS update address using CID on newer records.", true);
        aw = new vaq("DTLS.TRUNCATE_CLIENT_CERTIFICATE_PATH", "DTLS truncate client certificate path.", true);
        ax = new vaq("DTLS.TRUNCATE_CERTIFICATE_PATH_FOR_VALIDATION", "DTLS certificate path for validation.", true);
        af = new vaq("DTLS.RECOMMENDED_CIPHER_SUITES_ONLY", "DTLS recommended cipher-suites only.", true);
        ah = new vaq("DTLS.RECOMMENDED_CURVES_ONLY", "DTLS recommended ECC curves/groups only.", true);
        ae = new vaq("DTLS.RECOMMENDED_SIGNATURE_AND_HASH_ALGORITHMS_ONLY", "DTLS recommended signature- and hash-algorithms only.", true);
        ab = new vav<>("DTLS.PRESELECTED_CIPHER_SUITES", "List of preselected DTLS cipher-suites.\nIf not recommended cipher suites are intended to be used, switch off DTLS_RECOMMENDED_CIPHER_SUITES_ONLY.\nThe supported cipher suites are evaluated at runtime and may differ from the ones when creating this properties file.", CipherSuite.getCipherSuites(false, false));
        b = new vav<>("DTLS.CIPHER_SUITES", "List of DTLS cipher-suites.\nIf not recommended cipher suites are intended to be used, switch off DTLS_RECOMMENDED_CIPHER_SUITES_ONLY.\nThe supported cipher suites are evaluated at runtime and may differ from the ones when creating this properties file.", null, 1, CipherSuite.getCipherSuites(false, true));
        g = new vav<>("DTLS.CURVES", "List of DTLS curves (supported groups).\nDefaults to all supported curves of the JCE at runtime.", XECDHECryptography.SupportedGroup.getUsableGroupsArray());
        as = new d("DTLS.SIGNATURE_AND_HASH_ALGORITHMS", "List of DTLS signature- and hash-algorithms.\nValues e.g SHA256withECDSA or ED25519.");
        c = new vav<>("DTLS.CERTIFICATE_KEY_ALGORITHMS", "List of DTLS certificate key algorithms.\nOn the client side used to select the default cipher-suites, on the server side to negotiate the client's certificate.", new CipherSuite.CertificateKeyAlgorithm[]{CipherSuite.CertificateKeyAlgorithm.EC, CipherSuite.CertificateKeyAlgorithm.RSA});
        ba = new vat("DTLS.USE_DEPRECATED_CID", "DTLS use deprecated CID extension code point for client (before version 09 of RFC-CID).", null, 53);
        av = new vaq("DTLS.SUPPORT_DEPRECATED_CID", "DTLS support deprecated CID for server (before version 9).", false);
        ay = new vaq("DTLS.USE_DEFAULT_RECORD_FILTER", "Use default DTLS record filter.", true);
        aj = new vaq("DTLS.REMOVE_STALE_DOUBLE_PRINCIPALS", "Remove stale double principals.\nRequires unique principals and a read-write-lock connection store.", false);
        ad = new vaq("DTLS.READ_WRITE_LOCK_CONNECTION_STORE", "Use read-write-lock connection store.", false);
        n = new vay("DTLS.MAC_ERROR_FILTER_QUIET_TIME", "Quiet time to reset MAC error filter.\nThe MAC error filter blocks all traffic for an endpoint, if since the last quiet period the number of new MAC errors exceeds a threshold.\n0 to disable the MAC error filter.", 0L, TimeUnit.SECONDS);
        m = new vat("DTLS.MAC_ERROR_FILTER_THRESHOLD", "Threshold of current MAC errors to block all traffic for an endpoint. 0 to disable the MAC error filter.", 0, 0);
        Configuration.ModuleDefinitionsProvider moduleDefinitionsProvider = new Configuration.ModuleDefinitionsProvider() { // from class: org.eclipse.californium.scandium.config.DtlsConfig.1
            @Override // org.eclipse.californium.elements.config.Configuration.DefinitionsProvider
            public void applyDefinitions(Configuration configuration) {
                int availableProcessors = Runtime.getRuntime().availableProcessors();
                configuration.a(DtlsConfig.aq, 24, TimeUnit.HOURS);
                configuration.e(DtlsConfig.d, null, TimeUnit.SECONDS);
                configuration.a(DtlsConfig.am, 2000, TimeUnit.MILLISECONDS);
                configuration.a(DtlsConfig.y, 60000, TimeUnit.MILLISECONDS);
                configuration.a(DtlsConfig.f15899a, 0, TimeUnit.MILLISECONDS);
                configuration.e((BasicDefinition<vat>) DtlsConfig.w, (vat) 4);
                configuration.e((BasicDefinition<vau>) DtlsConfig.an, (vau) Float.valueOf(1.0f));
                configuration.e((BasicDefinition<vau>) DtlsConfig.ak, (vau) Float.valueOf(2.0f));
                configuration.e((BasicDefinition<vat>) DtlsConfig.al, (vat) null);
                configuration.e((BasicDefinition<vat>) DtlsConfig.i, (vat) null);
                configuration.e((BasicDefinition<vat>) DtlsConfig.h, (vat) null);
                configuration.e((BasicDefinition<vaq>) DtlsConfig.ar, (vaq) true);
                configuration.e((BasicDefinition<vaq>) DtlsConfig.bb, (vaq) true);
                configuration.e((BasicDefinition<vat>) DtlsConfig.ag, (vat) null);
                configuration.e((BasicDefinition<vaw<MaxFragmentLengthExtension.Length>>) DtlsConfig.r, (vaw<MaxFragmentLengthExtension.Length>) null);
                configuration.e((BasicDefinition<vat>) DtlsConfig.t, (vat) 8192);
                configuration.e((BasicDefinition<vaq>) DtlsConfig.bh, (vaq) null);
                configuration.e((BasicDefinition<vaq>) DtlsConfig.bd, (vaq) null);
                configuration.e((BasicDefinition<vaw<CertificateAuthenticationMode>>) DtlsConfig.f, (vaw<CertificateAuthenticationMode>) CertificateAuthenticationMode.NEEDED);
                configuration.e((BasicDefinition<vaq>) DtlsConfig.bj, (vaq) true);
                configuration.e((BasicDefinition<vaw<DtlsRole>>) DtlsConfig.ao, (vaw<DtlsRole>) DtlsRole.BOTH);
                configuration.e((BasicDefinition<vat>) DtlsConfig.v, (vat) null);
                configuration.e((BasicDefinition<vat>) DtlsConfig.aa, (vat) 1500);
                configuration.e((BasicDefinition<vba>) DtlsConfig.k, (vba) null);
                configuration.e((BasicDefinition<vat>) DtlsConfig.l, (vat) 150000);
                configuration.e(DtlsConfig.at, 1800L, TimeUnit.SECONDS);
                configuration.e((DocumentedDefinition) DtlsConfig.ac, (DocumentedDefinition) DtlsConfig.x);
                configuration.e((BasicDefinition<vat>) DtlsConfig.x, (vat) 50000);
                configuration.e((BasicDefinition<vat>) DtlsConfig.u, (vat) 50000);
                configuration.e((BasicDefinition<vat>) DtlsConfig.s, (vat) 5000);
                configuration.e((BasicDefinition<vat>) DtlsConfig.p, (vat) 10);
                configuration.e((BasicDefinition<vat>) DtlsConfig.q, (vat) 8192);
                configuration.e((BasicDefinition<vat>) DtlsConfig.z, (vat) Integer.valueOf(availableProcessors > 3 ? 2 : 1));
                configuration.e((BasicDefinition<vat>) DtlsConfig.j, (vat) Integer.valueOf(availableProcessors));
                configuration.e((BasicDefinition<vat>) DtlsConfig.ai, (vat) null);
                configuration.e((BasicDefinition<vat>) DtlsConfig.ap, (vat) null);
                configuration.e((BasicDefinition<vaq>) DtlsConfig.bf, (vaq) false);
                configuration.e((BasicDefinition<vaw<ExtendedMasterSecretMode>>) DtlsConfig.o, (vaw<ExtendedMasterSecretMode>) ExtendedMasterSecretMode.ENABLED);
                configuration.e((BasicDefinition<vat>) DtlsConfig.bm, (vat) 30);
                configuration.e((BasicDefinition<vaq>) DtlsConfig.bg, (vaq) true);
                configuration.e((BasicDefinition<vaq>) DtlsConfig.be, (vaq) true);
                configuration.e((BasicDefinition<vaq>) DtlsConfig.az, (vaq) true);
                configuration.e((BasicDefinition<vat>) DtlsConfig.bc, (vat) 0);
                configuration.e((BasicDefinition<vaq>) DtlsConfig.au, (vaq) true);
                configuration.e((BasicDefinition<vaq>) DtlsConfig.aw, (vaq) true);
                configuration.e((BasicDefinition<vaq>) DtlsConfig.ax, (vaq) true);
                configuration.e((BasicDefinition<vaq>) DtlsConfig.af, (vaq) true);
                configuration.e((BasicDefinition<vaq>) DtlsConfig.ah, (vaq) true);
                configuration.e((BasicDefinition<vaq>) DtlsConfig.ae, (vaq) true);
                configuration.e((BasicDefinition<vav<CipherSuite>>) DtlsConfig.ab, (vav<CipherSuite>) null);
                configuration.e((BasicDefinition<vav<CipherSuite>>) DtlsConfig.b, (vav<CipherSuite>) null);
                configuration.e((BasicDefinition<vav<XECDHECryptography.SupportedGroup>>) DtlsConfig.g, (vav<XECDHECryptography.SupportedGroup>) null);
                configuration.e((BasicDefinition<d>) DtlsConfig.as, (d) null);
                configuration.e((BasicDefinition<vav<CipherSuite.CertificateKeyAlgorithm>>) DtlsConfig.c, (vav<CipherSuite.CertificateKeyAlgorithm>) null);
                configuration.e((BasicDefinition<vat>) DtlsConfig.ba, (vat) null);
                configuration.e((BasicDefinition<vaq>) DtlsConfig.av, (vaq) false);
                configuration.e((BasicDefinition<vaq>) DtlsConfig.ay, (vaq) true);
                configuration.e((BasicDefinition<vaq>) DtlsConfig.aj, (vaq) false);
                configuration.e((BasicDefinition<vaq>) DtlsConfig.ad, (vaq) false);
                configuration.a(DtlsConfig.n, 0, TimeUnit.SECONDS);
                configuration.e((BasicDefinition<vat>) DtlsConfig.m, (vat) 0);
            }

            @Override // org.eclipse.californium.elements.config.Configuration.ModuleDefinitionsProvider
            public String getModule() {
                return "DTLS.";
            }
        };
        e = moduleDefinitionsProvider;
        Configuration.b(moduleDefinitionsProvider);
    }
}
