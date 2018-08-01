package com.langqiao.sharding.jdbc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.langqiao.sharding.jdbc.entity.TListingDo;
import com.langqiao.sharding.jdbc.mapper.TListingDoMapper;
import com.langqiao.sharding.jdbc.service.IListingService;

@Service
public class ListingServiceImpl implements IListingService{
    
    @Autowired
    public TListingDoMapper tListingDoMapper;

    public boolean insert(TListingDo listingDo) {
        return tListingDoMapper.insert(listingDo) > 0 ? true : false;
    }

}
