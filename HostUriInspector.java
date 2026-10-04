import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;

public class HostUriInspector {

    public static void main(String[] args) {
        if (args.length < 2) {
            System.err.println("LOI: Thieu tham so (can 2, nhan " + args.length + ").");
            System.err.println("Cach dung: java HostUriInspector <hostname> <uri>");
            System.err.println("Vi du    : java HostUriInspector localhost \"https://example.com:8080/a/b?x=1#top\"");
            System.exit(1);
        }

        String hostname = args[0];
        String uriText = args[1];

        int ma = 0;
        ma |= inspectHost(hostname);
        System.out.println();
        ma |= inspectUri(uriText);
        System.exit(ma);
    }

    static int inspectHost(String hostname) {
        System.out.println("=== HOST: " + hostname + " ===");
        try {
            InetAddress[] all = InetAddress.getAllByName(hostname);
            for (InetAddress a : all) {
                String loai = (a instanceof Inet4Address) ? "IPv4"
                            : (a instanceof Inet6Address) ? "IPv6" : "Khac";
                System.out.println("IP         : " + a.getHostAddress());
                System.out.println("  Loai     : " + loai);
                System.out.println("  Loopback : " + a.isLoopbackAddress());
                System.out.println("  SiteLocal: " + a.isSiteLocalAddress());
            }
            return 0;
        } catch (UnknownHostException e) {
            System.err.println("LOI: Khong phan giai duoc hostname '" + hostname + "' (" + e.getMessage() + ")");
            return 2;
        }
    }

    static int inspectUri(String uriText) {
        System.out.println("=== URI: " + uriText + " ===");
        try {
            URI uri = new URI(uriText);
            System.out.println("Scheme  : " + show(uri.getScheme()));
            System.out.println("Host    : " + show(uri.getHost()));
            System.out.println("Port    : " + (uri.getPort() == -1 ? "(khong chi dinh)" : String.valueOf(uri.getPort())));
            System.out.println("Path    : " + show(uri.getPath()));
            System.out.println("Query   : " + show(uri.getQuery()));
            System.out.println("Fragment: " + show(uri.getFragment()));
            return 0;
        } catch (URISyntaxException e) {
            System.err.println("LOI: URI khong hop le: " + e.getReason() + " (vi tri " + e.getIndex() + ")");
            return 3;
        }
    }

    private static String show(String s) {
        return (s == null || s.isEmpty()) ? "(khong co)" : s;
    }
}
