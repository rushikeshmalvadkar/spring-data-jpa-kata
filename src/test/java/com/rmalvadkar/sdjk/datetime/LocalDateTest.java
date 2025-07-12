package com.rmalvadkar.sdjk.datetime;

import org.junit.jupiter.api.Test;

import java.time.*;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

public class LocalDateTest {
    /*
        now ,
        of
        from

        parse
        plus
        minus
        with
        format
     */
    @Test
    void practice_local_date_class_methods() {
        Clock fixedClock = Clock.fixed(LocalDate.of(2025, Month.JULY, 12)
                .atStartOfDay(ZoneId.systemDefault()).toInstant(), ZoneId.systemDefault());

        assertThat(LocalDate.now(fixedClock)).isEqualTo("2025-07-12");

        assertThat(LocalDate.parse("2022-12-01")).hasDayOfMonth(1);
        assertThat(LocalDate.parse("2022-12-01")).hasMonth(Month.DECEMBER);
        assertThat(LocalDate.parse("2022-12-01")).hasYear(2022);

        String joiningDateStr = "01/12/2022";
        DateTimeFormatter joiningDateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate joiningDate = LocalDate.parse(joiningDateStr,joiningDateFormatter);
        assertThat(joiningDate).hasMonth(Month.DECEMBER);
        assertThat(joiningDate).hasDayOfMonth(1);
        assertThat(joiningDate).hasYear(2022);


        LocalDate rechargeExpiryDate = LocalDate.now(fixedClock).plusDays(28);
        assertThat(rechargeExpiryDate).isEqualTo("2025-08-09");

        LocalDate billPayDate = LocalDate.now(fixedClock).plusMonths(2);
        assertThat(billPayDate).hasMonth(Month.SEPTEMBER);

        LocalDate contractEndDate = LocalDate.now(fixedClock).plusYears(2);
        assertThat(contractEndDate).hasYear(2027);
        assertThat(contractEndDate).isEqualTo("2027-07-12");

        LocalDate referenceDate  = LocalDate.now(fixedClock);

        LocalDate lastDayDate = referenceDate.with(TemporalAdjusters.lastDayOfMonth());
        assertThat(lastDayDate).isEqualTo("2025-07-31");

        LocalDate lastDateOfYear = referenceDate.with(TemporalAdjusters.lastDayOfYear());
        assertThat(lastDateOfYear).isEqualTo("2025-12-31");


    }
}
