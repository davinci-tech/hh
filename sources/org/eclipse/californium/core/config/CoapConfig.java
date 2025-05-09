package org.eclipse.californium.core.config;

import defpackage.vaq;
import defpackage.vat;
import defpackage.vau;
import defpackage.vaw;
import defpackage.vay;
import defpackage.vba;
import java.util.concurrent.TimeUnit;
import org.eclipse.californium.elements.config.BasicDefinition;
import org.eclipse.californium.elements.config.Configuration;

/* loaded from: classes7.dex */
public final class CoapConfig {

    /* renamed from: a, reason: collision with root package name */
    public static final vau f15852a;
    public static final vay aa;
    public static final vay ab;
    public static final vat ac;
    public static final vat ad;
    public static final vaw<TrackerMode> ae;
    public static final vay af;
    public static final vat ag;
    public static final vat ah;
    public static final vat ai;
    public static final vat aj;
    public static final vat ak;
    public static final vay al;
    public static final vay am;
    public static final vat an;
    public static final vat ao;
    public static final vat ap;
    public static final vaq aq;
    public static final vau ar;
    public static final vaw<MatcherMode> as;
    public static final vaq at;
    public static final vat au;
    public static final vaq av;
    public static final vay b;
    public static final vau d;
    public static final vaq e;
    public static final vaq h;
    public static final vaq i;
    public static final vay j;
    public static final vay k;
    public static final vaq l;
    public static final vaw<CongestionControlMode> m;
    public static final vba o;
    public static final vay p;
    public static final vay q;
    public static final vay r;
    public static final Configuration.ModuleDefinitionsProvider t;
    public static final vat x;
    public static final vay y;
    public static final vat z;
    public static final TrackerMode n = TrackerMode.GROUPED;
    public static final MatcherMode s = MatcherMode.STRICT;
    public static final vat w = new vat("COAP.MAX_ACTIVE_PEERS", "Maximum number of active peers.", 150000, 1);
    public static final vay u = new vay("COAP.MAX_PEER_INACTIVITY_PERIOD", "Maximum inactive period of peer.", 600, TimeUnit.SECONDS);
    public static final vat f = new vat("COAP.COAP_PORT", "CoAP port.", 5683, 1);
    public static final vat g = new vat("COAP.COAP_SECURE_PORT", "CoAP DTLS port.", 5684, 1);
    public static final vay c = new vay("COAP.ACK_TIMEOUT", "Initial CoAP acknowledge timeout.", 2000, TimeUnit.MILLISECONDS);
    public static final vay v = new vay("COAP.MAX_ACK_TIMEOUT", "Maximum CoAP acknowledge timeout.", 60000, TimeUnit.MILLISECONDS);

    public enum CongestionControlMode {
        NULL,
        COCOA,
        COCOA_STRONG,
        BASIC_RTO,
        LINUX_RTO,
        PEAKHOPPER_RTO
    }

    public enum MatcherMode {
        STRICT,
        RELAXED,
        PRINCIPAL,
        PRINCIPAL_IDENTITY
    }

    public enum TrackerMode {
        NULL,
        GROUPED,
        MAPBASED
    }

    static {
        Float valueOf = Float.valueOf(1.0f);
        d = new vau("COAP.ACK_INIT_RANDOM", "Random factor for initial CoAP acknowledge timeout.", Float.valueOf(1.5f), valueOf);
        f15852a = new vau("COAP.ACK_TIMEOUT_SCALE", "Scale factor for CoAP acknowledge backoff-timeout.", Float.valueOf(2.0f), valueOf);
        ac = new vat("COAP.MAX_RETRANSMIT", "Maximum number of CoAP retransmissions.", 4, 1);
        p = new vay("COAP.EXCHANGE_LIFETIME", "CoAP maximum exchange lifetime for CON requests.", 247L, TimeUnit.SECONDS);
        af = new vay("COAP.NON_LIFETIME", "CoAP maximum lifetime for NON requests.", 145L, TimeUnit.SECONDS);
        y = new vay("COAP.MAX_LATENCY", "Maximum transmission latency for messages.", 100L, TimeUnit.SECONDS);
        aa = new vay("COAP.MAX_TRANSMIT_WAIT", "Maximum time to wait for ACK or RST after the first transmission of a CON message.", 93L, TimeUnit.SECONDS);
        ab = new vay("COAP.MAX_SERVER_RESPONSE_DELAY", "Maximum server response delay.", 250L, TimeUnit.SECONDS);
        an = new vat("COAP.NSTART", "Maximum concurrent transmissions.", 1, 1);
        q = new vay("COAP.LEISURE", "Timespan a multicast server may spread the response.", 5L, TimeUnit.SECONDS);
        ar = new vau("COAP.PROBING_RATE", "Probing rate to peers, which didn't response before. Currently not used.", valueOf);
        at = new vaq("COAP.USE_MESSAGE_OFFLOADING", "Use message off-loading, when data is not longer required.", false);
        av = new vaq("COAP.USE_RANDOM_MID_START", "Use initially a random value for MID.", true);
        ae = new vaw<>("COAP.MID_TACKER", "MID tracker.", TrackerMode.GROUPED, TrackerMode.values());
        ah = new vat("COAP.MID_TRACKER_GROUPS", "Number of MID tracker groups.", 16, 4);
        ai = new vat("COAP.MULTICAST_BASE_MID", "Base MID for multicast requests.", 65000, 0);
        au = new vat("COAP.TOKEN_SIZE_LIMIT", "Limit of token size.", 8, 1);
        ak = new vat("COAP.PREFERRED_BLOCK_SIZE", "Preferred blocksize for blockwise transfer.", 512, 16);
        x = new vat("COAP.MAX_MESSAGE_SIZE", "Maximum payload size.", 1024, 16);
        ad = new vat("COAP.MAX_RESOURCE_BODY_SIZE", "Maximum size of resource body. 0 to disable transparent blockwise mode.", 8192, 0);
        j = new vay("COAP.BLOCKWISE_STATUS_LIFETIME", "Lifetime of blockwise status.", 300L, TimeUnit.SECONDS);
        b = new vay("COAP.BLOCKWISE_STATUS_INTERVAL", "Interval to validate lifetime of blockwise status.", 5L, TimeUnit.SECONDS);
        ao = new vat("COAP.TCP_NUMBER_OF_BULK_BLOCKS", "Number of block per TCP-blockwise bulk transfer.", 1, 1);
        i = new vaq("COAP.BLOCKWISE_STRICT_BLOCK1_OPTION", "Use block1 option strictly, even for error-responses.", false);
        h = new vaq("COAP.BLOCKWISE_STRICT_BLOCK2_OPTION", "Use block2 option strictly, even if block2 is not required.", false);
        e = new vaq("COAP.BLOCKWISE_ENTITY_TOO_LARGE_AUTO_FAILOVER", "Enable automatic failover on \"entity too large\" response.", true);
        al = new vay("COAP.NOTIFICATION_CHECK_INTERVAL", "Interval time to check notifications receiver using a CON message.", 120L, TimeUnit.SECONDS);
        ag = new vat("COAP.NOTIFICATION_CHECK_INTERVAL_COUNT", "Interval counter to check notifications receiver using a CON message.", 100);
        am = new vay("COAP.NOTIFICATION_REREGISTRATION_BACKOFF", "Additional time (backoff) to the max-age option\nfor waiting for the next notification before reregister.", 2000L, TimeUnit.MILLISECONDS);
        z = new vat("COAP.MAX_SERVER_OBSERVES", "Maximum number of observes on server-side. 0 to disable this limitation.", 50000);
        m = new vaw<>("COAP.CONGESTION_CONTROL_ALGORITHM", "Congestion-Control algorithm (still experimental).", CongestionControlMode.NULL, CongestionControlMode.values());
        ap = new vat("COAP.PROTOCOL_STAGE_THREAD_COUNT", "Protocol stage thread count.", 1, 0);
        o = new vba("COAP.DEDUPLICATOR", "Deduplicator algorithm.", "MARK_AND_SWEEP", "MARK_AND_SWEEP", "PEERS_MARK_AND_SWEEP", "CROP_ROTATION", "NO_DEDUPLICATOR");
        r = new vay("COAP.MARK_AND_SWEEP_INTERVAL", "Mark and sweep interval.", 10L, TimeUnit.SECONDS);
        aj = new vat("COAP.PEERS_MARK_AND_SWEEP_MESSAGES", "Maximum messages kept per peer for PEERS_MARK_AND_SWEEP.", 64, 4);
        k = new vay("COAP.CROP_ROTATION_PERIOD", "Crop rotation period.", 247L, TimeUnit.SECONDS);
        l = new vaq("COAP.DEDUPLICATOR_AUTO_REPLACE", "Automatic replace entries in deduplicator.", true);
        as = new vaw<>("COAP.RESPONSE_MATCHING", "Response matching mode.", MatcherMode.STRICT, MatcherMode.values());
        aq = new vaq("COAP.STRICT_EMPTY_MESSAGE_FORMAT", "Process empty messages strictly according RFC7252, 4.1 as format error. Disable to ignore additional data as tokens or options.", true);
        Configuration.ModuleDefinitionsProvider moduleDefinitionsProvider = new Configuration.ModuleDefinitionsProvider() { // from class: org.eclipse.californium.core.config.CoapConfig.2
            @Override // org.eclipse.californium.elements.config.Configuration.DefinitionsProvider
            public void applyDefinitions(Configuration configuration) {
                int availableProcessors = Runtime.getRuntime().availableProcessors();
                configuration.e((BasicDefinition<vat>) CoapConfig.w, (vat) 150000);
                configuration.e(CoapConfig.u, 600L, TimeUnit.SECONDS);
                configuration.e((BasicDefinition<vat>) CoapConfig.f, (vat) 5683);
                configuration.e((BasicDefinition<vat>) CoapConfig.g, (vat) 5684);
                configuration.a(CoapConfig.c, 2000, TimeUnit.MILLISECONDS);
                configuration.e((BasicDefinition<vau>) CoapConfig.d, (vau) Float.valueOf(1.5f));
                configuration.e((BasicDefinition<vau>) CoapConfig.f15852a, (vau) Float.valueOf(2.0f));
                configuration.e((BasicDefinition<vat>) CoapConfig.ac, (vat) 4);
                configuration.e(CoapConfig.p, 247L, TimeUnit.SECONDS);
                configuration.a(CoapConfig.af, 145, TimeUnit.SECONDS);
                configuration.e((BasicDefinition<vat>) CoapConfig.an, (vat) 1);
                configuration.a(CoapConfig.q, 5, TimeUnit.SECONDS);
                configuration.e((BasicDefinition<vau>) CoapConfig.ar, (vau) Float.valueOf(1.0f));
                configuration.e((BasicDefinition<vaq>) CoapConfig.at, (vaq) false);
                configuration.a(CoapConfig.y, 100, TimeUnit.SECONDS);
                configuration.a(CoapConfig.aa, 93, TimeUnit.SECONDS);
                configuration.a(CoapConfig.ab, 250, TimeUnit.SECONDS);
                configuration.e((BasicDefinition<vaq>) CoapConfig.av, (vaq) true);
                configuration.e((BasicDefinition<vaw<TrackerMode>>) CoapConfig.ae, (vaw<TrackerMode>) CoapConfig.n);
                configuration.e((BasicDefinition<vat>) CoapConfig.ah, (vat) 16);
                configuration.e((BasicDefinition<vat>) CoapConfig.au, (vat) 8);
                configuration.e((BasicDefinition<vat>) CoapConfig.ak, (vat) 512);
                configuration.e((BasicDefinition<vat>) CoapConfig.x, (vat) 1024);
                configuration.e((BasicDefinition<vat>) CoapConfig.ad, (vat) 8192);
                configuration.a(CoapConfig.j, 300, TimeUnit.SECONDS);
                configuration.a(CoapConfig.b, 5, TimeUnit.SECONDS);
                configuration.e((BasicDefinition<vaq>) CoapConfig.h, (vaq) false);
                configuration.e((BasicDefinition<vaq>) CoapConfig.e, (vaq) true);
                configuration.e((BasicDefinition<vat>) CoapConfig.ao, (vat) 4);
                configuration.a(CoapConfig.al, 120, TimeUnit.SECONDS);
                configuration.e((BasicDefinition<vat>) CoapConfig.ag, (vat) 100);
                configuration.a(CoapConfig.am, 2000, TimeUnit.MILLISECONDS);
                configuration.e((BasicDefinition<vaw<CongestionControlMode>>) CoapConfig.m, (vaw<CongestionControlMode>) CongestionControlMode.NULL);
                configuration.e((BasicDefinition<vat>) CoapConfig.ap, (vat) Integer.valueOf(availableProcessors));
                configuration.e((BasicDefinition<vba>) CoapConfig.o, (vba) "MARK_AND_SWEEP");
                configuration.e(CoapConfig.r, 10L, TimeUnit.SECONDS);
                configuration.e((BasicDefinition<vat>) CoapConfig.aj, (vat) 64);
                configuration.e(CoapConfig.k, 247L, TimeUnit.SECONDS);
                configuration.e((BasicDefinition<vaq>) CoapConfig.l, (vaq) true);
                configuration.e((BasicDefinition<vaw<MatcherMode>>) CoapConfig.as, (vaw<MatcherMode>) CoapConfig.s);
                configuration.e((BasicDefinition<vat>) CoapConfig.ai, (vat) 65000);
                configuration.e((BasicDefinition<vaq>) CoapConfig.aq, (vaq) true);
                configuration.e((BasicDefinition<vat>) CoapConfig.z, (vat) 50000);
            }

            @Override // org.eclipse.californium.elements.config.Configuration.ModuleDefinitionsProvider
            public String getModule() {
                return "COAP.";
            }
        };
        t = moduleDefinitionsProvider;
        Configuration.b(moduleDefinitionsProvider);
    }
}
