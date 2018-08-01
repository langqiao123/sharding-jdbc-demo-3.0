package com.langqiao.sharding.jdbc.algorithm;

import java.util.Collection;

import io.shardingsphere.core.api.algorithm.sharding.PreciseShardingValue;
import io.shardingsphere.core.api.algorithm.sharding.standard.PreciseShardingAlgorithm;

/**
 * tb_listing 分表策略类
 * @author yangmingyang
 *
 */
public class ListingPreciseTableShardingAlgorithm implements PreciseShardingAlgorithm<Long>{

	@Override
	public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<Long> shardingValue) {
		for (String each : availableTargetNames) {
            if (each.endsWith(shardingValue.getValue() % 2 + "")) {
                return each;
            }
        }
        throw new UnsupportedOperationException();
	}

    

}
