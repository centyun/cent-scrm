package com.centyun.mail.job;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.centyun.mail.domain.Mail;
import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.dataflow.DataflowJob;

public class MailSendJob implements DataflowJob<Mail> {
    
    private Logger log = LoggerFactory.getLogger(MailSendJob.class);

    @Override
    public List<Mail> fetchData(ShardingContext shardingContext) {
        log.debug(shardingContext.getShardingItem() + "====" + shardingContext.getShardingTotalCount());
        return null;
    }

    @Override
    public void processData(ShardingContext shardingContext, List<Mail> data) {
        // TODO Auto-generated method stub
        
    }

}
