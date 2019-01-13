package com.centyun.mail.job;

import java.util.List;

import com.centyun.mail.domain.Mail;
import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.dataflow.DataflowJob;

public class MailSendJob implements DataflowJob<Mail> {

    @Override
    public List<Mail> fetchData(ShardingContext shardingContext) {
        System.out.println(shardingContext.getShardingItem() + "====" + shardingContext.getShardingTotalCount());
        return null;
    }

    @Override
    public void processData(ShardingContext shardingContext, List<Mail> data) {
        // TODO Auto-generated method stub
        
    }

}
