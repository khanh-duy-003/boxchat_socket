package com.duypk.socket.core.abstractres;

import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum JavaxValidationExceptionCodeEnum {

    // Exception is not define
    NOT_DEFINE("NotDefine", "", "4000000"),
    // must be false
    ASSERT_FALSE("AssertFalse", "javax.validation.constraints.AssertFalse.message", "4000001"),
    // must be true
    ASSERT_TRUE("AssertTrue", "javax.validation.constraints.AssertTrue.message", "4000002"),

    DECIMAL_MAX("DecimalMax", "javax.validation.constraints.DecimalMax.message", "4000003"),

    CRON_EXPRESSION("CronExpression", "SCHEDULE_INVALID_CRON_REGEX", "4022003"),

    DECIMAL_MIN("DecimalMin", "javax.validation.constraints.DecimalMin.message", "4000004"),
    // numeric value out of bounds (<{integer} digits>.<{fraction} digits>
    // expected)
    DIGITS("Digits", "javax.validation.constraints.Digits.message", "4000005"),
    // must be a well-formed email address
    EMAIL("Email", "javax.validation.constraints.Email.message", "4000006"),
    // must be a future date
    FUTURE("Future", "javax.validation.constraints.Future.message", "4000007"),
    // must be a date in the present or in the future
    FUTURE_OR_PRESENT("FutureOrPresent", "javax.validation.constraints.FutureOrPresent.message", "4000008"),

    MAX("Max", "javax.validation.constraints.Max.message", "4000009"),

    MIN("Min", "javax.validation.constraints.Min.message", "4000010"),
    // must be less than 0
    NEGATIVE("Negative", "javax.validation.constraints.Negative.message", "4000011"),
    // must be less than or equal to 0
    NEGATIVE_OR_ZERO("NegativeOrZero", "javax.validation.constraints.NegativeOrZero.message", "4000012"),
    // must not be blank
    NOT_BLANK("NotBlank", "javax.validation.constraints.NotBlank.message", "4000013"),
    // must not be empty
    NOT_EMPTY("NotEmpty", "javax.validation.constraints.NotEmpty.message", "4000014"),
    // must not be null
    NOT_NULL("NotNull", "javax.validation.constraints.NotNull.message", "4000015"),
    // must be null
    NULL("Null", "javax.validation.constraints.Null.message", "4000016"),
    // must be a past date
    PAST("Past", "javax.validation.constraints.Past.message", "4000017"),
    // must be a date in the past or in the present
    PAST_OR_PRESENT("PastOrPresent", "javax.validation.constraints.PastOrPresent.message", "4000018"),
    // must match "{regexp}"
    PATTERN("Pattern", "javax.validation.constraints.Pattern.message", "4000019"),
    // must be greater than 0
    POSITIVE("Positive", "javax.validation.constraints.Positive.message", "4000020"),
    // must be greater than or equal to 0
    POSITIVE_OR_ZERO("PositiveOrZero", "javax.validation.constraints.PositiveOrZero.message", "4000021"),

    SIZE("Size", "javax.validation.constraints.Size.message", "4000022"),

    DATE("Date", "javax.validation.constraints.Date.message", "4000023"),

    CONTAIN_ARRAY("ContainArray", "ANNOTATION_CONTAIN_ARRAY", "4000024"),

    MAP_WITH_ENUM("MapWithEnum", "ANNOTATION_NOT_MATH_ENUM", "4000025"),;

    private String code;
    private String messageCode;
    private String value;

    private static final Map<String, JavaxValidationExceptionCodeEnum> mappings = new HashMap<>(
            JavaxValidationExceptionCodeEnum.values().length);

    static {
        for (JavaxValidationExceptionCodeEnum exceptionCodeEnum : values()) {
            mappings.put(exceptionCodeEnum.getCode(), exceptionCodeEnum);
        }
    }

    public static JavaxValidationExceptionCodeEnum resolveByCode(String code) {
        return mappings.get(code) != null ? mappings.get(code) : NOT_DEFINE;
    }
}
