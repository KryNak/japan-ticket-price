package com.github.knakielski;

import java.math.BigDecimal;
import java.util.Map;

public interface CommissionService {
    Map<String, BigDecimal> getCommissions();
    default BigDecimal getCommissionsTotal() {
        return getCommissions().values()
                               .stream()
                               .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
    default BigDecimal takeAccountOfCommissions(BigDecimal originalPrice) {
        return originalPrice.add(getCommissionsTotal());
    }
}
