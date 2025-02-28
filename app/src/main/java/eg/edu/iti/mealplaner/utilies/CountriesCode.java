package eg.edu.iti.mealplaner.utilies;

import java.util.HashMap;
import java.util.Map;

public class CountriesCode {
    private static final Map<String, String> nationalityToCountryMap = new HashMap<>();

    static {
        nationalityToCountryMap.put("American", "US");
        nationalityToCountryMap.put("British", "GB");
        nationalityToCountryMap.put("Canadian", "CA");
        nationalityToCountryMap.put("Chinese", "CN");
        nationalityToCountryMap.put("Croatian", "HR");
        nationalityToCountryMap.put("Dutch", "NL");
        nationalityToCountryMap.put("Egyptian", "EG");
        nationalityToCountryMap.put("Filipino", "PH");
        nationalityToCountryMap.put("French", "FR");
        nationalityToCountryMap.put("Greek", "GR");
        nationalityToCountryMap.put("Indian", "IN");
        nationalityToCountryMap.put("Irish", "IE");
        nationalityToCountryMap.put("Italian", "IT");
        nationalityToCountryMap.put("Jamaican", "JM");
        nationalityToCountryMap.put("Japanese", "JP");
        nationalityToCountryMap.put("Kenyan", "KE");
        nationalityToCountryMap.put("Malaysian", "MY");
        nationalityToCountryMap.put("Mexican", "MX");
        nationalityToCountryMap.put("Moroccan", "MA");
        nationalityToCountryMap.put("Norwegian", "NO");
        nationalityToCountryMap.put("Polish", "PL");
        nationalityToCountryMap.put("Portuguese", "PT");
        nationalityToCountryMap.put("Russian", "RU");
        nationalityToCountryMap.put("Spanish", "ES");
        nationalityToCountryMap.put("Thai", "TH");
        nationalityToCountryMap.put("Tunisian", "TN");
        nationalityToCountryMap.put("Turkish", "TR");
        nationalityToCountryMap.put("Ukrainian", "UA");
        nationalityToCountryMap.put("Uruguayan", "UY");
        nationalityToCountryMap.put("Vietnamese", "VN");
    }

    public static String getCountryCode(String nationality) {
        return nationalityToCountryMap.getOrDefault(nationality, "Unknown");
    }

}
