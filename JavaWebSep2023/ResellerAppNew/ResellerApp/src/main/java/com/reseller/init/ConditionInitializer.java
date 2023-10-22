package com.reseller.init;

import com.reseller.repository.ConditionRepository;
import com.reseller.model.entity.Condition;
import com.reseller.model.entity.ConditionEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class ConditionInitializer implements CommandLineRunner {

    private final ConditionRepository conditionRepository;

    @Autowired
    public ConditionInitializer(ConditionRepository conditionRepository) {
    this.conditionRepository = conditionRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        if (this.conditionRepository.count() != 0) {
            return;
        }

        Arrays.stream(ConditionEnum.values())
                .forEach(name -> {

                    Condition condition = new Condition();
                    if (name.equals(ConditionEnum.ACCEPTABLE)){
                        condition.setConditionEnum(ConditionEnum.ACCEPTABLE);
                        condition.setDescription("The item is fairly worn but continues to function properly");
                    } else if (name.equals(ConditionEnum.GOOD)) {
                        condition.setConditionEnum(ConditionEnum.GOOD);
                        condition.setDescription("Some signs of wear and tear or minor defects");
                    } else if (name.equals(ConditionEnum.EXCELLENT)) {
                        condition.setConditionEnum(ConditionEnum.EXCELLENT);
                        condition.setDescription("The item is fairly worn but continues to function properly");
                   }

                    this.conditionRepository.save(condition);
                });
    }
}
