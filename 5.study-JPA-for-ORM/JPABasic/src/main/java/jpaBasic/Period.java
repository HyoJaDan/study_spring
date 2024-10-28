package jpaBasic;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;

//1. Embeddable추가
@Embeddable
public class Period {
    LocalDateTime startDate;
    LocalDateTime endDate;

    // 2. 기본 생성자 추가
    public Period(){}
    // 3. 귀찮으니까 생성자도 추가
    public Period(LocalDateTime startDate, LocalDateTime endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }
}
