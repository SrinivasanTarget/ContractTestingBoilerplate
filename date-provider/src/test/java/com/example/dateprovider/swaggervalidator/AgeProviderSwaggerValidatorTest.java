package com.example.dateprovider.swaggervalidator;

import com.atlassian.oai.validator.pact.PactProviderValidationResults;
import com.atlassian.oai.validator.pact.PactProviderValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

public class AgeProviderSwaggerValidatorTest {

    public static final String SWAGGER_JSON_URL = "http://localhost:8080/v3/api-docs/";

    @Test
    public void validate_withLocalPact_withValidInteractions() {

        final PactProviderValidator validator = PactProviderValidator
                .createFor(SWAGGER_JSON_URL)
                .withConsumer("the-consumer", getClass().getResource("/pacts/valid-interactions.json"))
                .build();

        assertNoBreakingChanges(validator.validate());
    }

    private void assertNoBreakingChanges(final PactProviderValidationResults results) {
        if (results.hasErrors()) {
            fail("Validation errors found.\n\t" + results.getValidationFailureReport().replace("\n", "\n\t"));
        }
    }

}
