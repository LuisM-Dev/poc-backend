package com.ibm.proposal.header.adapter.in.web;

import com.ibm.proposal.header.adapter.in.web.validator.ErrorMessage;
import com.ibm.proposal.header.adapter.in.web.validator.WebValidator;
import com.ibm.proposal.header.domain.Header;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.validation.Validator;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class ValidatorTest {

    private Header header;
    private HeaderGetWebModel headerGetWebModel;
    private WebValidator webValidator;

    @BeforeEach
    void setUp() {

        header = new Header();
        header.setId("Some Id");
        header.setType("Some Type");

        headerGetWebModel = new HeaderGetWebModel();
        headerGetWebModel.setProposalId("Some Id");
        headerGetWebModel.setProposalType("Some Type");

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        webValidator = new WebValidator();
        webValidator.setValidator(validator);
    }

    @Test
    void createHeaderValidatorIncorrectStartEndDate() {
        header.setStartDate(LocalDate.now());
        header.setEndDate(LocalDate.now());
        assertIncorrectCreateHeader("startDate", "Start Date must be prior to End Date");
    }

    @Test
    void createHeaderValidatorNoStartEndDate() {
        assertIncorrectCreateHeader("startDate", "Start Date must be prior to End Date");
    }

    @Test
    void createHeaderValidatorStartEndDate() {
        header.setStartDate(LocalDate.now());
        header.setEndDate(LocalDate.now().plusMonths(1));
        assertCorrectCreateHeader("startDate");
    }

    @Test
    void createHeaderValidatorIncorrectEndDateTermLength() {
        header.setStartDate(LocalDate.now());
        header.setEndDate(LocalDate.now().plusMonths(1));
        header.setTermLengthMonths("12");
        assertIncorrectCreateHeader("endDate", "End Date must be Start Date plus Term Length");
    }

    @Test
    void createHeaderValidatorNotEndDateTermLength() {
        assertIncorrectCreateHeader("endDate", "End Date must be Start Date plus Term Length");
    }

    @Test
    void createHeaderValidatorEndDateTermLength() {
        header.setStartDate(LocalDate.now());
        header.setEndDate(LocalDate.now().plusMonths(12));
        header.setTermLengthMonths("12");
        assertCorrectCreateHeader("endDate");
    }

    @Test
    void createHeaderValidatorIncorrectAccount() {
        assertIncorrectCreateHeader("account", "Account is invalid");
    }

    @Test
    void createHeaderValidatorAccount() {
        header.setAccount("Some Account");
        assertCorrectCreateHeader("account");
    }

    @Test
    void createHeaderValidatorIncorrectCountry() {
        header.setLeadCountry("PT");
        assertIncorrectCreateHeader("leadCountry", "Lead Country is invalid");
    }

    @Test
    void createHeaderValidatorCountry() {
        header.setLeadCountry("AT");
        assertCorrectCreateHeader("leadCountry");
    }

    @Test
    void createHeaderValidatorIncorrectCurrency() {
        header.setCurrency("CAD");
        assertIncorrectCreateHeader("currency", "Currency is invalid");
    }


    @Test
    void createHeaderValidatorCurrency() {
        header.setCurrency("EUR - Euro");
        assertCorrectCreateHeader("currency");
    }

    @Test
    void createHeaderValidatorCurrencyDollarCharacter() {
        header.setCurrency("$ - Dollar");
        assertCorrectCreateHeader("currency");
    }

    @Test
    void getHeaderCorrectly() {
        headerGetWebModel.setProposalType("TSS Distributor Transactional");
        ErrorMessage errorMessages = webValidator.validateGetRequest(headerGetWebModel);
        assertThat(errorMessages.getErrors()).isEmpty();
    }

    @Test
    void getHeaderIncorrectly() {
        ErrorMessage errorMessages = webValidator.validateGetRequest(headerGetWebModel);
        assertThat(errorMessages.getErrors()).isNotEmpty();
        assertThat(errorMessages.getErrors().get("proposalType"))
                .isEqualTo("Proposal Type is invalid");
    }

    private void assertCorrectCreateHeader(String error) {
        ErrorMessage errorMessage = webValidator.validateCreateHeaderRequest(header);
        assertThat(errorMessage.getErrors().get(error)).isNull();
    }

    private void assertIncorrectCreateHeader(String error, String message) {
        ErrorMessage errorMessage = webValidator.validateCreateHeaderRequest(header);
        assertThat(errorMessage.getErrors()).isNotEmpty();
        assertThat(errorMessage.getErrors().get(error)).isEqualTo(message);
    }
}
