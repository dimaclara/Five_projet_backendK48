package tech.marie.todolist.customValidation;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import tech.marie.todolist.dto.TaskRequestDto;

import java.time.ZonedDateTime;

public class DateRangeValidator implements ConstraintValidator <ValidDateRange, TaskRequestDto>{
    @Override
    public boolean isValid(TaskRequestDto taskRequestDto, ConstraintValidatorContext context) {
        ZonedDateTime startDate = taskRequestDto.getStartDate();
        ZonedDateTime endDate = taskRequestDto.getEndDate();
        return startDate == null || endDate == null || startDate.isAfter(endDate);
    }
}
