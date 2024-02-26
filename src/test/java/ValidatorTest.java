import org.example.Validator;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ValidatorTest
{

    @Test
    public void testValidEmail()
    {
        assertTrue( Validator.validateEmail( "test@example.com" ) );
    }

    @Test
    public void testInvalidEmail_MissingAtSymbol()
    {
        assertFalse( Validator.validateEmail( "invalid.email.com" ) );
    }

    @Test
    public void testInvalidEmail_MissingTopLevelDomain()
    {
        assertFalse( Validator.validateEmail( "invalid.email@com" ) );
    }

    @Test
    public void testValidEmail_MultipleDotsInDomain()
    {
        assertTrue( Validator.validateEmail( "valid.email@domain.with.many.dots" ) );
    }

    @Test
    public void testValidEmail_HyphenInDomain()
    {
        assertTrue( Validator.validateEmail( "valid.email@domain.with-hyphen.com" ) );
    }

    @Test
    public void testValidEmail_SpecialCharactersInDomain()
    {
        assertTrue( Validator.validateEmail( "valid.email@domain.with.special_chars" ) );
    }

    @Test
    public void testValidEmail_LeadingTrailingWhitespace()
    {
        assertTrue( Validator.validateEmail( "   test@example.com   " ) );
    }

    @Test
    public void testInvalidEmail_EmptyString()
    {
        assertFalse( Validator.validateEmail( "" ) );
    }

    @Test
    public void testInvalidEmail_NullInput()
    {
        assertFalse( Validator.validateEmail( null ) );
    }

    @Test
    public void testValidPassword()
    {
        assertTrue( Validator.validatePassword( "ValidPassword123!" ) );
    }

    @Test
    public void testPasswordShorterThan8Characters()
    {
        assertFalse( Validator.validatePassword( "Short1!" ) );
    }

    @Test
    public void testPasswordTooLong()
    {
        assertFalse( Validator.validatePassword( "toolongpasswordwithlotsofcharacters123!" ) );
    }

    @Test
    public void testPasswordMissingUppercaseLetter()
    {
        assertFalse( Validator.validatePassword( "nocapitalletter123!" ) );
    }

    @Test
    public void testPasswordMissingLowercaseLetter()
    {
        assertFalse( Validator.validatePassword( "NOLOWERCASELETTERS123!" ) );
    }

    @Test
    public void testPasswordMissingSpecialCharacter()
    {
        assertFalse( Validator.validatePassword( "NoSpecialCharacter123" ) );
    }

    @Test
    public void testPasswordMissingNumberAndSpecialChars()
    {
        assertFalse( Validator.validatePassword( "MissingNumberAndSpecialChars" ) );
    }

    @Test
    public void testPasswordWithConsecutiveRepeatingCharacters()
    {
        assertFalse( Validator.validatePassword( "password123@@@" ) );
    }

    @Test
    public void testPasswordWithOnlySpaces()
    {
        assertFalse( Validator.validatePassword( "     " ) );
    }

    @Test
    public void testEmptyPassword()
    {
        assertFalse( Validator.validatePassword( "" ) );
    }

    @Test
    public void testNullPassword()
    {
        assertFalse( Validator.validatePassword( null ) );
    }

    @Test
    public void testValidDOB()
    {
        assertTrue( Validator.validateDOB( "2000-01-01" ) );
    }

    @Test
    public void testValidDOB_MinimumYear()
    {
        assertTrue( Validator.validateDOB( "1900-01-01" ) );
    }

    @Test
    public void testValidDOB_Today()
    {
        assertTrue( Validator.validateDOB( "2024-02-21" ) );
    }

    @Test
    public void testInvalidDOB_FutureDate()
    {
        assertFalse( Validator.validateDOB( "2025-02-21" ) );
    }

    @Test
    public void testInvalidDOB_NonExistentDate()
    {
        assertFalse( Validator.validateDOB( "1999-02-30" ) );
    }

    @Test
    public void testInvalidDOB_InvalidFormat()
    {
        assertFalse( Validator.validateDOB( "invalid" ) );
    }

    @Test
    public void testInvalidDOB_EmptyString()
    {
        assertFalse( Validator.validateDOB( "" ) );
    }

    @Test
    public void testInvalidDOB_NullInput()
    {
        assertFalse( Validator.validateDOB( null ) );
    }

    @Test
    public void testInvalidDOB_LeadingTrailingWhitespace()
    {
        assertFalse( Validator.validateDOB( " 2000-01-01 " ) );
    }

    @Test
    public void testValidDateTime()
    {
        assertTrue( Validator.validateDateTime( "2024-02-21T12:34:56" ) );
    }

    @Test
    public void testInvalidDateTime_NonExistentDate()
    {
        assertFalse( Validator.validateDateTime( "2024-02-30T12:34:56" ) );
    }

    @Test
    public void testInvalidDateTime_InvalidFormat()
    {
        assertFalse( Validator.validateDateTime( "invalid" ) );
    }

    @Test
    public void testInvalidDateTime_EmptyString()
    {
        assertFalse( Validator.validateDateTime( "" ) );
    }

    @Test
    public void testInvalidDateTime_NullInput()
    {
        assertFalse( Validator.validateDateTime( null ) );
    }

    @Test
    public void testInvalidDateTime_LeadingTrailingWhitespace()
    {
        assertFalse( Validator.validateDateTime( " 2024-02-21T12:34:56 " ) );
    }

    @Test
    public void testInvalidDateTime_TimezoneInformation()
    {
        assertFalse( Validator.validateDateTime( "2024-02-21T12:34:56Z" ) );
    }

    @Test
    public void testValidCountryName()
    {
        assertTrue( Validator.validateCountry( "United States" ) );
    }

    @Test
    public void testValidCountryName_CaseInsensitive()
    {
        assertTrue( Validator.validateCountry( "united states" ) );
    }

    @Test
    public void testInvalidCountryName()
    {
        assertFalse( Validator.validateCountry( "invalid" ) );
    }

    @Test
    public void testEmptyString()
    {
        assertFalse( Validator.validateCountry( "" ) );
    }

    @Test
    public void testNullInput()
    {
        assertFalse( Validator.validateCountry( null ) );
    }

    @Test
    public void testInvalidCountryName_Numeric()
    {
        assertFalse( Validator.validateCountry( "1234" ) );
    }

    @Test
    public void testInvalidCountryName_SpecialCharacters()
    {
        assertFalse( Validator.validateCountry( "United States!" ) );
    }

    @Test
    public void testValidCountryName_TrailingWhitespace()
    {
        assertTrue( Validator.validateCountry( "United States " ) );
    }

    @Test
    public void testValidCountryName_LeadingWhitespace()
    {
        assertTrue( Validator.validateCountry( " United States" ) );
    }

    @Test
    public void testValidHttpURL()
    {
        assertTrue( Validator.validateURL( "http://www.example.com" ) );
    }

    @Test
    public void testValidHttpsURL()
    {
        assertTrue( Validator.validateURL( "https://www.example.com" ) );
    }

    @Test
    public void testValidFtpURL()
    {
        assertTrue( Validator.validateURL( "ftp://ftp.example.com" ) );
    }

    @Test
    public void testInvalidURL_MissingProtocol()
    {
        assertFalse( Validator.validateURL( "www.example.com" ) );
    }

    @Test
    public void testInvalidURL_Malformed()
    {
        assertFalse( Validator.validateURL( "invalid.url" ) );
    }

    @Test
    public void testEmptyURL()
    {
        assertFalse( Validator.validateURL( "" ) );
    }

    @Test
    public void testNullURL()
    {
        assertFalse( Validator.validateURL( null ) );
    }

    @Test
    public void testInvalidURL_TrailingWhitespace()
    {
        assertFalse( Validator.validateURL( "http://www.example.com " ) );
    }

    @Test
    public void testInvalidURL_LeadingWhitespace()
    {
        assertFalse( Validator.validateURL( " http://www.example.com" ) );
    }

    @Test
    public void testInvalidURL_PathWithSpaces()
    {
        assertFalse( Validator.validateURL( "http://www.example.com/path with spaces" ) );
    }

    @Test
    public void testInvalidURL_QueryParamWithSpaces()
    {
        assertFalse( Validator.validateURL( "http://www.example.com/?query=param with spaces" ) );
    }

    @Test
    public void testValidString()
    {
        assertTrue( Validator.validateString( "abcd" ) );
    }

    @Test
    public void testValidString_Uppercase()
    {
        assertTrue( Validator.validateString( "ABCD" ) );
    }

    @Test
    public void testValidString_MixedCase()
    {
        assertTrue( Validator.validateString( "aBcD" ) );
    }

    @Test
    public void testInvalidString_NullInput()
    {
        assertFalse( Validator.validateString( null ) );
    }

    @Test
    public void testInvalidString_EmptyString()
    {
        assertFalse( Validator.validateString( "" ) );
    }

    @Test
    public void testInvalidString_NumericCharacters()
    {
        assertFalse( Validator.validateString( "1234" ) );
    }

    @Test
    public void testInvalidString_SpecialCharacters()
    {
        assertFalse( Validator.validateString( "!@#$" ) );
    }

    @Test
    public void testInvalidString_Whitespace()
    {
        assertFalse( Validator.validateString( "abcd efgh" ) );
    }

    @Test
    public void testInvalidString_Alphanumeric()
    {
        assertFalse( Validator.validateString( "abcd1234" ) );
    }

    @Test
    public void testValidNumber_PositiveInteger()
    {
        assertTrue( Validator.validateNumber( "1234" ) );
    }

    @Test
    public void testValidNumber_NegativeInteger()
    {
        assertTrue( Validator.validateNumber( "-1234" ) );
    }

    @Test
    public void testValidNumber_Zero()
    {
        assertTrue( Validator.validateNumber( "0" ) );
    }

    @Test
    public void testInvalidNumber_NullInput()
    {
        assertFalse( Validator.validateNumber( null ) );
    }

    @Test
    public void testInvalidNumber_EmptyString()
    {
        assertFalse( Validator.validateNumber( "" ) );
    }

    @Test
    public void testInvalidNumber_NonNumericCharacters()
    {
        assertFalse( Validator.validateNumber( "abcd" ) );
    }

    @Test
    public void testInvalidNumber_DecimalNumber()
    {
        assertFalse( Validator.validateNumber( "12.34" ) );
    }

    @Test
    public void testInvalidNumber_LeadingWhitespace()
    {
        assertFalse( Validator.validateNumber( " 1234" ) );
    }

    @Test
    public void testInvalidNumber_TrailingWhitespace()
    {
        assertFalse( Validator.validateNumber( "1234 " ) );
    }
}
