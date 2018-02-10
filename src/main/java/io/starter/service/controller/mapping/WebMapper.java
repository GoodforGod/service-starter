package io.starter.service.controller.mapping;

/**
 * "Default Description"
 *
 * @author GoodforGod
 * @since 04.02.2018
 */
public class WebMapper extends Mapper {

    public static final String SECURED = "/sec";
    public static final String HOME = SECURED + "/home";

    public static final String LOGIN = "/login";
    public static final String LOGOUT = SECURED + "/logout";

    public static final String SWAGGER_UI = "/swagger-ui.html";
    public static final String SWAGGER_DOC = "/swagger*";
}
