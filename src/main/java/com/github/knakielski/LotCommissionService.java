package com.github.knakielski;

import com.google.common.collect.ImmutableMap;
import java.math.BigDecimal;
import java.util.Map;

public class LotCommissionService implements CommissionService {

    @Override
    public Map<String, BigDecimal> getCommissions() {
        return ImmutableMap.of(
                "siteVisitCommission", BigDecimal.valueOf(182_72, 2),
                "economyStandardCommission", BigDecimal.valueOf(400_00, 2)
        );
    }

}
