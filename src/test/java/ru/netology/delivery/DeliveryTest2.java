package ru.netology.delivery;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class DeliveryTest2 {

    public String generateDate(long addDays, String pattern) {
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern(pattern));
    }

    @Test
    public void shouldSendTheForm() {
        open("http://localhost:9999/");
        $("[data-test-id='city'] input").setValue("Че");
        $$(".menu-item").find(text("Челябинск")).click();
        $("[data-test-id='date']").click();
        if (!generateDate(7, "MM").equals(generateDate(3, "MM"))) {
        $("[data-step='1']").click(); }
        $$("[data-day]").findBy(text(generateDate(7, "d"))).click();
        $("[data-test-id='name'] input").setValue("Кузнецова Александра");
        $("[data-test-id='phone'] input").setValue("+79820000000");
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $("[data-test-id='notification']").shouldBe(visible, Duration.ofSeconds(15));
    }
}
