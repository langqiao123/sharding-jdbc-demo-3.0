package com.langqiao.sharding.jdbc.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.langqiao.sharding.jdbc.entity.TListingDo;
import com.langqiao.sharding.jdbc.entity.TUserDo;
import com.langqiao.sharding.jdbc.mapper.TListingDoMapper;
import com.langqiao.sharding.jdbc.mapper.TUserDoMapper;
import com.langqiao.sharding.jdbc.service.IUserService;

@Service
@Transactional
public class UserServiceImpl implements IUserService {

    @Autowired
    public TUserDoMapper tUserDoMapper;
    
    @Autowired
    public TListingDoMapper tListingDoMapper;

    public boolean insert(TUserDo u) {
        return tUserDoMapper.insert(u) > 0 ? true :false;
    }

    public List<TUserDo> findAll() {
        return tUserDoMapper.findAll();
    }

    public List<TUserDo> findByUserIds(List<Long> ids) {
        return tUserDoMapper.findByUserIds(ids);
    }

    @Transactional(propagation=Propagation.REQUIRED)
    public void transactionTestSucess() {
    	TUserDo u = new TUserDo();
        u.setUserId(13L);
        u.setAge(25);
        u.setName("war3 1.27");
        u.setIsactive(true);
        tUserDoMapper.insert(u);
        
        TListingDo listingDo = new TListingDo();
        listingDo.setAmount(new BigDecimal(20));
        listingDo.setListingId(111L);
        listingDo.setDesc("pdl标的");
        listingDo.setUserId(21L);
        listingDo.setIsactive(true);
        tListingDoMapper.insert(listingDo);
    }

    @Transactional(propagation=Propagation.REQUIRED)
    public void transactionTestFailure() throws IllegalAccessException {
    	TUserDo u = new TUserDo();
        u.setUserId(13L);
        u.setAge(25);
        u.setName("war3 1.27 good");
        tUserDoMapper.insert(u);
        
        TListingDo listingDo = new TListingDo();
        listingDo.setAmount(new BigDecimal(20));
        listingDo.setDesc("pdl标的");
        listingDo.setUserId(22L);
        tListingDoMapper.insert(listingDo);
        throw new IllegalAccessException();
    }
    
}
