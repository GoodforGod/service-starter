package io.starter.service.controller.mapping;

/**
 * "Default Description"
 *
 * @author GoodforGod
 * @since 10.09.2017
 */
public class RestMapper {

    private static final String api = "/api";

    private static final String time_table = "/schedule";
    private static final String exam = "/exam";
    private static final String student = "/student";
    private static final String teacher = "/teacher";

    public static final String STUDENT_TIME_TABLE = api + student + time_table;
    public static final String STUDENT_EXAM = api + student + exam;

    public static final String TEACHER_TIME_TABLE = api + teacher + time_table;
    public static final String TEACHER_EXAM = api + teacher + exam;
}
