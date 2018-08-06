package com.langqiao.sharding.jdbc;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.langqiao.sharding.jdbc.entity.TListingDo;
import com.langqiao.sharding.jdbc.entity.TUserDo;
import com.langqiao.sharding.jdbc.service.IListingService;
import com.langqiao.sharding.jdbc.service.IUserService;

import io.shardingsphere.core.keygen.DefaultKeyGenerator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { 
		"classpath*:config/spring/spring-database.xml",
        "classpath*:config/spring/spring-sharding_v3.0.xml" })
public class ShardingJdbcTest {

    @Resource
    public IUserService userService;
    
    @Resource
    public IListingService listingService;

    @Test
    public void testUserInsert() {
    	TUserDo u = new TUserDo();
    	u.setUserId(1L);
		u.setAge(25);
		u.setName("war3");
		u.setIsactive(true);
		Assert.assertEquals(userService.insert(u), true);
    }
    
    @Test
    public void testUserBatchInsert() {
    	for(int i=0;i<100;i++) {
    		TUserDo u = new TUserDo();
    		u.setUserId(18L);
    		u.setAge(25);
    		u.setName("war3");
    		u.setIsactive(true);
    		Assert.assertEquals(userService.insert(u), true);
    	}
    	try {
    		Thread.sleep(30000);
		} catch (Exception e) {
			// TODO: handle exception
		}
    	
    }
    
    @Test
    public void testListingnsert() {
    	DefaultKeyGenerator keyGenerator = new DefaultKeyGenerator();
        Long id = keyGenerator.generateKey().longValue();
        TListingDo listingDo = new TListingDo();
//        listingDo.setId(id);
        listingDo.setListingId(10L);
        listingDo.setAmount(new BigDecimal(20));
//        listingDo.setDesc("pdl");
        listingDo.setUserId(1L);
        listingDo.setIsactive(true);
        Assert.assertEquals(listingService.insert(listingDo), true);
    }

    @Test
    public void testFindAll(){
        List<TUserDo> users = userService.findAll();
        if(null != users && !users.isEmpty()){
            for(TUserDo u :users){
                System.out.println(u);
            }
        }
    }
    
    @Test
    public void testSQLIN(){
        List<TUserDo> users = userService.findByUserIds(Arrays.asList(18L,2L,0L));
        if(null != users && !users.isEmpty()){
            for(TUserDo u :users){
                System.out.println(u);
            }
        }
    }
    
    @Test
    public void testTransactionTestSucess(){
        userService.transactionTestSucess();
    }
    
    @Test(expected = IllegalAccessException.class)
    public void testTransactionTestFailure() throws IllegalAccessException{
        userService.transactionTestFailure();
    }
    
    @Test
    public void testGenerateKey() {
    	for(int i=0;i<100;i++) {
    		DefaultKeyGenerator keyGenerator = new DefaultKeyGenerator();
//    		Integer id = keyGenerator.generateKey().intValue();
//    		Long id_long = keyGenerator.generateKey().longValue();
    		System.out.println("ddddddddd:"+keyGenerator.generateKey().toString());
    	}
    }
    
    @Test
    public void testSingleGenerateKey() {
		DefaultKeyGenerator keyGenerator = new DefaultKeyGenerator();
//		Integer id = keyGenerator.generateKey().intValue();
//		Long id_long = keyGenerator.generateKey().longValue();
		System.out.println("ddddddddd:"+keyGenerator.generateKey().toString());
    }
    
}
