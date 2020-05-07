package com.ibm.proposal.create.adapter.in;

import com.ibm.proposal.create.adapter.in.web.CreateWebModel;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

public class CreateWebModelTest {

    private static Validator validator;

    @BeforeClass
    public static void setUpValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void proposalTypeShouldNotBeEmpty() {
        CreateWebModel createWebModel = new CreateWebModel();
        createWebModel.setProposalType("");
        Set<ConstraintViolation<CreateWebModel>> constraintViolations = validator.validate(createWebModel);

        assertThat(constraintViolations.size()).isEqualTo(1);
        assertThat(constraintViolations.iterator().next().getMessage()).isEqualTo( "Proposal Type is invalid");
    }

    @Test
    public void proposalTypeIsInValid() {
        CreateWebModel createWebModel = new CreateWebModel();
        createWebModel.setProposalType("TSS Distributor");
        Set<ConstraintViolation<CreateWebModel>> constraintViolations = validator.validate(createWebModel);

        assertThat(constraintViolations.size()).isEqualTo(1);
        assertThat(constraintViolations.iterator().next().getMessage()).isEqualTo( "Proposal Type is invalid");
    }

    @Test
    public void proposalTypeMustBeValidDistributor() {
        CreateWebModel createWebModel = new CreateWebModel();
        createWebModel.setProposalType("TSS Distributor Transactional");
        Set<ConstraintViolation<CreateWebModel>> constraintViolations = validator.validate(createWebModel);

        assertThat(constraintViolations.size()).isEqualTo(0);
    }

    @Test
    public void proposalTypeMustBeValidDirect() {
        CreateWebModel createWebModel = new CreateWebModel();
        createWebModel.setProposalType("TSS Direct Transactional");
        Set<ConstraintViolation<CreateWebModel>> constraintViolations = validator.validate(createWebModel);

        assertThat(constraintViolations.size()).isEqualTo(0);
    }
}
