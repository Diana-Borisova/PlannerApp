package com.reseller.repository;

import com.reseller.model.entity.Condition;
import com.reseller.model.entity.ConditionEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConditionRepository extends JpaRepository<Condition, Long> {

    Optional<Condition> findByConditionEnum(ConditionEnum conditionEnum);
}
