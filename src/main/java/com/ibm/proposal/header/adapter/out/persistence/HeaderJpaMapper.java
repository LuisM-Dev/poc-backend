package com.ibm.proposal.header.adapter.out.persistence;

import com.ibm.proposal.header.domain.Header;
import com.ibm.proposal.header.domain.HeaderDirect;
import com.ibm.proposal.header.domain.HeaderDistributor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface HeaderJpaMapper {

    HeaderJpaMapper INSTANCE = Mappers.getMapper( HeaderJpaMapper.class);

//    @Mapping(target = "startDate", source = "startDate", dateFormat = "yyyy-MM-dd")
//    HeaderJpaEntity headerToJpaEntity(Header header);
//
//    Header jpaEntityToHeader(HeaderJpaEntity headerJpaEntity);

    HeaderDirectJpaEntity headerDirectToJpaEntity(Header header);

    HeaderDistributorJpaEntity headerDistributorToJpaEntity(Header header);

    HeaderDistributor jpaEntityToHeaderDistributor(HeaderJpaEntity headerJpaEntity);

    HeaderDirect jpaEntityToHeaderDirect(HeaderJpaEntity headerJpaEntity);
}
