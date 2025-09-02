package org.example.admin.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.admin.ui.dto.GetDailyRegisterUserResponseDto;
import org.example.admin.ui.query.UserStatsQueryRepository;
import org.example.common.TimeCalculator;
import org.example.user.repository.entity.QUserEntity;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserStatsQueryRepositoryImpl implements UserStatsQueryRepository {

    private final JPAQueryFactory queryFactory;
    private static final QUserEntity userEntity = QUserEntity.userEntity;

    @Override
    public List<GetDailyRegisterUserResponseDto> getDailyRegisterUserStats(int beforeDays) {
        return queryFactory
            .select(
                Projections.fields(
                    GetDailyRegisterUserResponseDto.class,
                    userEntity.regDate.as("date"),
                    userEntity.count().as("count")
                )
            )
            .from(userEntity)
            .where(userEntity.regDate.after(TimeCalculator.getDateDaysAgo(beforeDays)))
            .groupBy(userEntity.regDate)
            .orderBy(userEntity.regDate.asc())
            .fetch();
    }
}
