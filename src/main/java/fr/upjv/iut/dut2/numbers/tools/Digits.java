package fr.upjv.iut.dut2.numbers.tools;

import java.util.*;

public abstract class Digits {

    private abstract static class One {
        public static String toString(int digit) {

            return switch (digit) {
                case 1 -> "un";
                case 2 -> "deux";
                case 3 -> "trois";
                case 4 -> "quatre";
                case 5 -> "cinq";
                case 6 -> "six";
                case 7 -> "sept";
                case 8 -> "huit";
                case 9 -> "neuf";
                default -> throw new IllegalArgumentException("Number not found");
            };

        }
    }

    private abstract static class Two {
        private static String getInvariableNumber(List<Integer> digits) {
            return switch (Tools.implodeList(digits)) {
                case 10 -> "dix";
                case 11 -> "onze";
                case 12 -> "douze";
                case 13 -> "treize";
                case 14 -> "quatorze";
                case 15 -> "quinze";
                case 16 -> "seize";
                default -> null;
            };
        }

        private static String getConnector(int firstDigit, int lastDigit) {
            String callback = null;

            if (lastDigit == 1 && firstDigit != 9) {
                callback = " et ";
            } else if (lastDigit != 0 || firstDigit == 7 || firstDigit == 9) {
                callback = "-";
            }

            return callback;
        }

        public static String toString(List<Integer> digits, boolean canBeGranted) {

            String callback = getInvariableNumber(digits);

            if (callback == null) {

                int firstDigit = digits.get(0);
                int lastDigit = digits.get(1);

                if (firstDigit == 7 || firstDigit == 9) {
                    callback = Tens.toString(firstDigit - 1);
                } else {
                    callback = Tens.toString(firstDigit);
                    if (firstDigit == 8 && lastDigit == 0 && canBeGranted) {
                        callback += 's';
                    }
                }

                callback += getConnector(firstDigit, lastDigit);

                if (firstDigit == 7 || firstDigit == 9) {
                    List<Integer> subNumberDigits = List.of(1, lastDigit);
                    callback += Digits.toString(subNumberDigits, false);
                } else if (lastDigit != 0) {
                    callback += Digits.One.toString(lastDigit);
                }
            }

            return callback;
        }

    }

    private abstract static class Three {
        public static String toString(List<Integer> digits, boolean canBeGranted) {

            String callback = "";

            int firstDigit = digits.get(0);
            List<Integer> lastTwoDigits = digits.subList(1, 3);

            int numberMinusHundreds = Tools.implodeList(lastTwoDigits);

            if (firstDigit > 1) {
                callback += Digits.One.toString(firstDigit);
            }

            if (firstDigit > 1 && canBeGranted && numberMinusHundreds == 0) {
                callback += " cents ";
            } else {
                callback += " cent ";
            }

            if (numberMinusHundreds > 0) {
                callback += Digits.toString(lastTwoDigits, true);
            }

            return callback;
        }
    }

    private abstract static class FourToSix {
        public static String toString(List<Integer> digits) {

            while (digits.size() < 6) {
                digits.add(0, 0);
            }

            String callback = "";

            List<Integer> firstThreeDigits = digits.subList(0, 3);
            List<Integer> lastThreeDigits = digits.subList(3, 6);

            int thousands = Tools.implodeList(firstThreeDigits);
            int numberMinusThousands = Tools.implodeList(lastThreeDigits);

            if (thousands > 1) {
                callback += Digits.toString(firstThreeDigits, false);
            }

            callback += " mille ";

            if (numberMinusThousands > 0) {
                callback += Digits.toString(lastThreeDigits, true);
            }

            return callback;

        }
    }

    private abstract static class SevenToNine {
        public static String toString(List<Integer> digits) {

            while (digits.size() < 9) {
                digits.add(0, 0);
            }

            String callback = "";

            List<Integer> firstThreeDigits = digits.subList(0, 3);
            List<Integer> lastSixDigits = digits.subList(3, 9);

            int millions = Tools.implodeList(firstThreeDigits);
            int numberMinusMillions = Tools.implodeList(lastSixDigits);

            callback += Digits.toString(firstThreeDigits, true);

            if (millions == 1) {
                callback += " million ";
            } else {
                callback += " millions ";
            }

            if (numberMinusMillions > 0) {
                callback += Digits.toString(lastSixDigits, true);
            }

            return callback;

        }
    }

    public static String toString(List<Integer> digitsReference, boolean canBeGranted) {

        List<Integer> digits = new ArrayList<>(digitsReference);

        while (digits.get(0) == 0) {
            digits.remove(0);
            if (digits.size() == 0) {
                return "zero";
            }
        }

        int digitsCount = digits.size();

        if (digitsCount == 1) {
            return One.toString(digits.get(0));
        } else if (digitsCount == 2) {
            return Two.toString(digits, canBeGranted);
        } else if (digitsCount == 3) {
            return Three.toString(digits, canBeGranted);
        } else if (digitsCount <= 6) {
            return FourToSix.toString(digits);
        } else if (digitsCount <= 9) {
            return SevenToNine.toString(digits);
        } else {
            throw new IllegalArgumentException("Too large number");
        }
    }
}