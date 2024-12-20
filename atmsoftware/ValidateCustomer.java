package atmsoftware;
                import java.util.regex.*;
                public class ValidateCustomer {
                    public static boolean validate(String accno, String password) {
                        if (!accno.matches("\\d{13}")) {
                            System.out.println("❌ Account number must be exactly 13 digits.");
                            return false;
                        }
                        if (!password.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$")) {
                            System.out.println("❌ Password must be 8+ chars, with upper, lower, digit, special.");
                            return false;
                        }
                        return true;
                    }

                }