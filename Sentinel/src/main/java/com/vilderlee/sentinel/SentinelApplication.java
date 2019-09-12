package com.vilderlee.sentinel;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;

@SpringBootApplication @RestController

public class SentinelApplication {

    public static void main(String[] args) {
        instanceFlowRules();
        SpringApplication.run(SentinelApplication.class, args);
    }

    @Autowired private DempService dempService;

    @GetMapping("/hello/{str}") public String hello(@PathVariable String str) throws Exception{
        return dempService.hello(str);
    }


    @GetMapping("/test")
    public String test() {
        return dempService.world("");
    }

    @ResponseBody
    @RequestMapping(value = "test1")
    public void b() {
        Entry entry = null;
        try {
            entry = SphU.entry("HelloWorld");
            System.out.println("HelloWorld");
        } catch (BlockException e) {
            System.out.println("block...");
        } finally {
            if(entry!=null){
                entry.exit();
            }
        }
    }


    private static void instanceFlowRules() {
        //  限流
        List<FlowRule> list = new ArrayList<>();
        FlowRule flowRule = new FlowRule();
        flowRule.setResource("test");
        flowRule.setCount(1).setGrade(RuleConstant.FLOW_GRADE_QPS);
        FlowRule flowRule1 = new FlowRule();
        flowRule1.setResource("test1");
        flowRule1.setCount(1).setGrade(RuleConstant.FLOW_GRADE_QPS);
        list.add(flowRule);
        list.add(flowRule1);
        FlowRuleManager.loadRules(list);

        // 熔断降级
        DegradeRule degradeRule = new DegradeRule();
        degradeRule.setGrade(RuleConstant.DEGRADE_GRADE_RT).setCount(200).setTimeWindow(10).setResource("hello");
        List<DegradeRule> degradeRules = new ArrayList<>();
        degradeRules.add(degradeRule);
        DegradeRuleManager.loadRules(degradeRules);


    }

}
