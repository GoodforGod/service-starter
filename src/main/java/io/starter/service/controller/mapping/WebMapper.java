package io.starter.service.controller.mapping;

/**
 * "Default Description"
 *
 * @author GoodforGod
 * @since 10.09.2017
 */
public class WebMapper {

    public static final String SECURED = "/sec";
    public static final String HOME = SECURED + "/home";

    public static final String LOGIN = "/login";
    public static final String LOGOUT = SECURED + "/logout";

    public static final String SWAGGER_UI = "/swagger-ui.html";
    public static final String SWAGGER_DOC = "/swagger*";

    public static String redirect(String value) {
        return "redirect:" + value;
    }
}
