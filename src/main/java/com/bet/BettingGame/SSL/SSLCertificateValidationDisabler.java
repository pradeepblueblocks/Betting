//package com.bet.BettingGame.SSL;
//import javax.net.ssl.*;
//import java.security.cert.X509Certificate;
//
//public class SSLCertificateValidationDisabler {
//
//    public static void disable() {
//        try {
//            // Create a trust manager that trusts all SSL certificates
//            TrustManager[] trustAllCerts = new TrustManager[]{
//                    new X509TrustManager() {
//                        public X509Certificate[] getAcceptedIssuers() {
//                            return null;
//                        }
//
//                        public void checkClientTrusted(X509Certificate[] certs, String authType) {
//                        }
//
//                        public void checkServerTrusted(X509Certificate[] certs, String authType) {
//                        }
//                    }
//            };
//
//            // Create a SSL context with the trust manager that trusts all certificates
//            SSLContext sc = SSLContext.getInstance("SSL");
//            sc.init(null, trustAllCerts, new java.security.SecureRandom());
//
//            // Set the default SSL socket factory to use the SSL context that trusts all certificates
//            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}