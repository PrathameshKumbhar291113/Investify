package com.prathameshkumbhar.investify.data.mapper

import com.prathameshkumbhar.investify.data.local.entity.CompanyListingEntity
import com.prathameshkumbhar.investify.domain.model.CompanyListing

fun CompanyListingEntity.toCompanyListing(): CompanyListing{
    return CompanyListing(
        name = name,
        symbol = symbol,
        exchange = exchange
    )
}
fun CompanyListing.toCompanyListingEntity(): CompanyListingEntity {
    return CompanyListingEntity(
        name = name,
        symbol = symbol,
        exchange = exchange
    )
}