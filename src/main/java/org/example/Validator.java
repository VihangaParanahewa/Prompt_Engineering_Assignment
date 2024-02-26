package org.example;


import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator
{

    /**
     * Regular expression pattern for validating email addresses.
     * This pattern enforces the following rules for a valid email address:
     * - It starts with optional whitespace.
     * - Followed by one or more characters that can be an underscore, alphabetic character, digit, hyphen, or plus sign.
     * - May contain one or more groups of characters separated by dots, each group starting with an underscore, alphabetic character, digit, hyphen, or plus sign.
     * - Followed by the '@' symbol.
     * - Followed by one or more characters that can be an alphabetic character, digit, hyphen, or plus sign.
     * - May contain one or more groups of characters separated by dots, each group starting with an alphabetic character, digit, or hyphen.
     * - Ends with a top-level domain containing at least two alphabetic characters.
     * - Ends with optional whitespace.
     */
    private static final String EMAIL_REGEX =
            "^\\s*[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9-_]+)*(\\.[A-Za-z0-9-_]{2,})\\s*$";
    /**
     * Regular expression pattern for validating passwords.
     * This pattern requires the password to meet the following criteria:
     * - At least one digit [0-9].
     * - At least one lowercase letter [a-z].
     * - At least one uppercase letter [A-Z].
     * - At least one special character among [@#$%^&+=!*].
     * - No repeated characters allowed (up to three repetitions).
     * - Allowed characters include alphanumeric characters and the specified special characters.
     * - Minimum length of 8 characters.
     */
    private static final String PASSWORD_REGEX =
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!*])(?!.*(.)\\1\\1)[a-zA-Z0-9@#$%^&+=!*]{8,}$";

    /**
     * Regular expression pattern for validating strings containing only alphabetic characters.
     * This pattern matches strings that contain one or more alphabetic characters (uppercase or lowercase).
     */
    private static final String ALPHABETIC_REGEX = "[a-zA-Z]+";

    /**
     * Regular expression pattern for validating strings representing numeric values.
     * This pattern matches strings that represent integer numeric values, including negative integers.
     */
    private static final String NUMERIC_REGEX = "-?\\d+";

    private static final Pattern EMAIL_PATTERN = Pattern.compile( EMAIL_REGEX );

    private static final Pattern PASSWORD_PATTERN = Pattern.compile( PASSWORD_REGEX );

    // Define a map to store country codes and names
    private static final Map<String,String> COUNTRY_CODE_MAP = createCountryCodeMap();

    /**
     * Validates an email address using a regular expression pattern.
     *
     * @param email The email address to validate.
     * @return {@code true} if the email address is valid, {@code false} otherwise.
     */
    public static boolean validateEmail( String email )
    {
        if( email == null || email.isEmpty() )
        {
            return false; // Null or empty string is not a valid email
        }

        Matcher matcher = EMAIL_PATTERN.matcher( email.trim() ); // Trim to remove leading/trailing whitespace
        return matcher.matches();
    }

    /**
     * Validates a password using a regular expression pattern.
     *
     * @param password The password to validate.
     * @return {@code true} if the password is valid, {@code false} otherwise.
     */
    public static boolean validatePassword( String password )
    {
        if( password == null || password.trim().isEmpty() )
        {
            return false; // Null or empty string is not a valid password
        }

        Matcher matcher = PASSWORD_PATTERN.matcher( password );
        return matcher.matches();
    }

    /**
     * Validates a date of birth string.
     *
     * @param dob The date of birth string to validate.
     * @return {@code true} if the date of birth is valid, {@code false} otherwise.
     */
    public static boolean validateDOB( String dob )
    {
        if( dob == null || dob.trim().isEmpty() )
        {
            return false; // Null or empty string is not a valid date of birth
        }

        try
        {
            // Attempt to parse the date string into a LocalDate object using ISO_LOCAL_DATE format
            LocalDate parsedDate = LocalDate.parse( dob, DateTimeFormatter.ISO_LOCAL_DATE );
            // Get the current date
            LocalDate currentDate = LocalDate.now();

            // Assuming DOB must be a date in the past and within a reasonable range
            // Check if the parsed date is not after the current date and the year is not earlier than 1900
            return !parsedDate.isAfter( currentDate ) && parsedDate.getYear() >= 1900;
        }
        catch( DateTimeParseException e )
        {
            // If an exception occurs during parsing, consider the date of birth as invalid
            System.out.println( e.getMessage() );
            return false;
        }
    }

    /**
     * Validates a date and time string.
     *
     * @param dateTime The date and time string to validate.
     * @return {@code true} if the date and time are valid, {@code false} otherwise.
     */
    public static boolean validateDateTime( String dateTime )
    {
        if( dateTime == null || dateTime.trim().isEmpty() )
        {
            return false; // Null or empty string is not a valid datetime
        }

        try
        {
            LocalDateTime.parse( dateTime, DateTimeFormatter.ISO_LOCAL_DATE_TIME );
            // Check if the parsed datetime falls within a reasonable range
            return true; // Assuming all valid datetime formats are acceptable
        }
        catch( DateTimeParseException e )
        {
            System.out.println( e.getMessage() );
            return false;
        }
    }

    /**
     * Validates a country name string.
     *
     * @param country The country name string to validate.
     * @return {@code true} if the country name is valid, {@code false} otherwise.
     */
    public static boolean validateCountry( String country )
    {
        if( country == null || country.trim().isEmpty() )
        {
            return false; // Null or empty string is not a valid country name
        }

        // Convert input country name to lowercase for case-insensitive comparison
        String countryLowerCase = country.trim().toLowerCase();

        // Check if the lowercase country name exists in the map
        return COUNTRY_CODE_MAP.containsKey( countryLowerCase );
    }

    /**
     * Validates a URL string.
     *
     * @param url The URL string to validate.
     * @return {@code true} if the URL is valid, {@code false} otherwise.
     */
    public static boolean validateURL( String url )
    {
        if( url == null || url.trim().isEmpty() )
        {
            return false; // Null or empty string is not a valid URL
        }

        try
        {
            URI uri = new URI( url );
            // Check if the scheme (protocol) is present in the URI
            return uri.getScheme() != null;
        }
        catch( URISyntaxException e )
        {
            // If an exception occurs during URL creation, consider the URL as invalid
            System.out.println( e.getMessage() );
            return false;
        }
    }

    /**
     * Validates a string to ensure it contains only alphabetic characters.
     *
     * @param input The string to validate.
     * @return {@code true} if the string is valid and contains only alphabetic characters, {@code false} otherwise.
     */
    public static boolean validateString( String input )
    {
        // Check for null or empty string
        if( input == null || input.isEmpty() )
        {
            return false;
        }

        // Check for non-alphabetic characters
        return input.matches( ALPHABETIC_REGEX );
    }

    /**
     * Validates a string to ensure it represents a valid number.
     *
     * @param input The string to validate.
     * @return {@code true} if the string is valid and represents a number, {@code false} otherwise.
     */
    public static boolean validateNumber( String input )
    {
        // Check for null or empty string
        if( input == null || input.isEmpty() )
        {
            return false;
        }

        // Check for non-numeric characters
        return input.matches( NUMERIC_REGEX );
    }

    /**
     * Creates a map of country codes and names.
     * This method iterates over all ISO country codes and retrieves their corresponding
     * country names using {@link Locale#getDisplayCountry()}.
     *
     * @return A map where country names are keys and country codes are values.
     */
    private static Map<String,String> createCountryCodeMap()
    {
        Map<String,String> countryCodeMap = new HashMap<>();
        String[] countryCodes = Locale.getISOCountries();
        for( String countryCode : countryCodes )
        {
            Locale locale = new Locale( "", countryCode );
            String countryName = locale.getDisplayCountry();
            countryCodeMap.put( countryName.toLowerCase(), countryCode );
        }
        return countryCodeMap;
    }


    public static void main( String[] args )
    {
        Scanner scanner = new Scanner( System.in );

        // Getting user inputs
        System.out.print( "Enter Email Address: " );
        String email = scanner.nextLine();
        System.out.println( "Email is valid: " + validateEmail( email ) );

        System.out.print( "Enter Password: " );
        String password = scanner.nextLine();
        System.out.println( "Password is valid: " + validatePassword( password ) );

        System.out.print( "Enter Date of Birth (yyyy-MM-dd): " );
        String dob = scanner.nextLine();
        System.out.println( "Date of Birth is valid: " + validateDOB( dob ) );

        System.out.print( "Enter Date & Time (yyyy-MM-ddTHH:mm:ss): " );
        String dateTime = scanner.nextLine();
        System.out.println( "Date & Time is valid: " + validateDateTime( dateTime ) );

        System.out.print( "Enter Country: " );
        String country = scanner.nextLine();
        System.out.println( "Country is valid: " + validateCountry( country ) );

        System.out.print( "Enter Website URL: " );
        String url = scanner.nextLine();
        System.out.println( "URL is valid: " + validateURL( url ) );

        System.out.print( "Enter String: " );
        String stringInput = scanner.nextLine();
        System.out.println( "String is valid: " + validateString( stringInput ) );

        System.out.print( "Enter Number: " );
        String numberInput = scanner.nextLine();
        System.out.println( "Number is valid: " + validateNumber( numberInput ) );

        // Close the scanner
        scanner.close();
    }
}