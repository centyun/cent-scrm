package com.centyun.mail.sharding;

import java.util.Collection;

import io.shardingsphere.api.algorithm.sharding.PreciseShardingValue;
import io.shardingsphere.api.algorithm.sharding.standard.PreciseShardingAlgorithm;

/**
 * 分片策略
 * 
 * @author yinww
 *
 */

public class CtyPreciseShardingAlgorithm implements PreciseShardingAlgorithm<Integer> {

    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<Integer> shardingValue) {
        String postfix = "" + (shardingValue.getValue() / 2) % 10;
        for (String tableName : availableTargetNames) {
            if (tableName.endsWith(postfix)) {
                return tableName;
            }
        }
        throw new IllegalArgumentException();
    }

}
