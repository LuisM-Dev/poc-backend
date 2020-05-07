package com.ibm.proposal.header.adapter.out;

import com.ibm.proposal.header.adapter.out.persistence.HeaderDirectJpaEntity;
import com.ibm.proposal.header.adapter.out.persistence.HeaderDistributorJpaEntity;
import com.ibm.proposal.header.adapter.out.persistence.HeaderJpaMapper;
import com.ibm.proposal.header.domain.HeaderDirect;
import com.ibm.proposal.header.domain.HeaderDistributor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class HeaderJpaMapperTest {

  private HeaderDirect headerDirect;
  private HeaderDistributor headerDistributor;
  private HeaderDirectJpaEntity headerDirectJpaEntity;
  private HeaderDistributorJpaEntity headerDistributorJpaEntity;

  @BeforeEach
  void setUp() {
    String sampleLeadCountry = "Some Lead Country";

    headerDirect = new HeaderDirect();
    headerDirect.setLeadCountry(sampleLeadCountry);

    headerDistributor = new HeaderDistributor();
    headerDistributor.setLeadCountry(sampleLeadCountry);

    headerDirectJpaEntity = new HeaderDirectJpaEntity();
    headerDirectJpaEntity.setLeadCountry(sampleLeadCountry);

    headerDistributorJpaEntity = new HeaderDistributorJpaEntity();
    headerDistributorJpaEntity.setLeadCountry(sampleLeadCountry);
  }

  @Test
  void mappingEntityToDirectCorrectly() {
    HeaderDirectJpaEntity actualHeaderDirectJpaEntity =
        HeaderJpaMapper.INSTANCE.headerDirectToJpaEntity(headerDirect);
    assertThat(actualHeaderDirectJpaEntity).isInstanceOf(HeaderDirectJpaEntity.class);
    assertThat(actualHeaderDirectJpaEntity).isEqualToComparingFieldByField(headerDirectJpaEntity);
  }

  @Test
  void mappingEntityToDistributorCorrectly() {
    HeaderDistributorJpaEntity actualHeaderDistributorJpaEntity =
        HeaderJpaMapper.INSTANCE.headerDistributorToJpaEntity(headerDistributor);
    assertThat(actualHeaderDistributorJpaEntity).isInstanceOf(HeaderDistributorJpaEntity.class);
    assertThat(actualHeaderDistributorJpaEntity)
        .isEqualToComparingFieldByField(headerDistributorJpaEntity);
  }

  @Test
  void mappingDistributorToEntityCorrectly() {
      HeaderDistributor actualHeaderDistributor = HeaderJpaMapper.INSTANCE.jpaEntityToHeaderDistributor(headerDistributorJpaEntity);
      assertThat(actualHeaderDistributor).isInstanceOf(HeaderDistributor.class);
      assertThat(actualHeaderDistributor).isEqualToComparingFieldByField(headerDistributor);
  }

  @Test
    void mappingDirectToEntityCorrectly() {
      HeaderDirect actualHeaderDirect = HeaderJpaMapper.INSTANCE.jpaEntityToHeaderDirect(headerDirectJpaEntity);
      assertThat(actualHeaderDirect).isInstanceOf(HeaderDirect.class);
      assertThat(actualHeaderDirect).isEqualToComparingFieldByField(headerDirect);
  }
}
