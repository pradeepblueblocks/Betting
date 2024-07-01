//package com.bet.BettingGame.config;
//
//public class BetCalculator {
//
//    public static double calculatePayout(double betAmount, int odds) {
//        if (odds > 0) {
//            // Positive moneyline odds
//            return betAmount * (1 + odds / 100.0);
//        } else {
//            // Negative moneyline odds
//            return betAmount * (1 + 100.0 / Math.abs(odds));
//        }
//    }
//
//    public static double calculateProfit(double betAmount, int odds) {
//        double payout = calculatePayout(betAmount, odds);
//        return payout - betAmount;
//    }
//}
