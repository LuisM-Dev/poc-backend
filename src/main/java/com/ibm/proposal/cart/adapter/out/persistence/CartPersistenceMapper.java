package com.ibm.proposal.cart.adapter.out.persistence;

import com.ibm.proposal.cart.domain.Cart;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CartPersistenceMapper {

    CartPersistenceMapper INSTANCE = Mappers.getMapper(CartPersistenceMapper.class);

    CartJpaEntity mapEntityToJpa(Cart.Asset cartAsset);
}
