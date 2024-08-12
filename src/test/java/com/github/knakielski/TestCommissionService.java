package com.github.knakielski;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class TestCommissionService {

    private final CommissionService serviceMock = spy(CommissionService.class);

    @Test
    public void test_get_commissions_total() {
        //given
        when(serviceMock.getCommissions()).thenReturn(Map.of(
                "x1", BigDecimal.valueOf(120_25, 2),
                "x2", BigDecimal.valueOf(100_5, 1)
        ));

        //when
        BigDecimal result = serviceMock.getCommissionsTotal();

        //then
        assertThat(result).isEqualByComparingTo(BigDecimal.valueOf(220_75, 2));
    }

    @Test
    public void test_take_account_of_commissions() {
        //given
        when(serviceMock.getCommissionsTotal()).thenReturn(BigDecimal.valueOf(47_00, 2));

        //when
        BigDecimal result = serviceMock.takeAccountOfCommissions(BigDecimal.valueOf(1025_25, 2));

        //then
        assertThat(result).isEqualByComparingTo(BigDecimal.valueOf(1072_25, 2));
    }

}
