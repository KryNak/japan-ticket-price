package com.github.knakielski;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import com.google.common.collect.ImmutableMap;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

final class TestCommissionService {

    private final CommissionService serviceMock = spy();

    @Test
    void getCommissionsTotal() {
        //given
        when(serviceMock.getCommissions()).thenReturn(ImmutableMap.of(
                "x1", BigDecimal.valueOf(120_25, 2),
                "x2", BigDecimal.valueOf(100_5, 1)
        ));

        //when
        BigDecimal result = serviceMock.getCommissionsTotal();

        //then
        assertThat(result).isEqualByComparingTo(BigDecimal.valueOf(220_75, 2));
    }

    @Test
    void takeAccountOfCommissions() {
        //given
        when(serviceMock.getCommissionsTotal()).thenReturn(BigDecimal.valueOf(47_00, 2));

        //when
        BigDecimal result = serviceMock.takeAccountOfCommissions(BigDecimal.valueOf(1025_25, 2));

        //then
        assertThat(result).isEqualByComparingTo(BigDecimal.valueOf(1072_25, 2));
    }

}
