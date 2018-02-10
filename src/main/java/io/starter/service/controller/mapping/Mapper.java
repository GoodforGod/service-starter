package io.starter.service.controller.mapping;

/**
 * "Default Description"
 *
 * @author GoodforGod
 * @since 04.02.2018
 */
abstract class Mapper {

    public static String redirect(String value) {
        return "redirect:" + value;
    }
}
