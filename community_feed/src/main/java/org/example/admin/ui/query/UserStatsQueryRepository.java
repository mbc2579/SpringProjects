package org.example.admin.ui.query;

import java.util.List;
import org.example.admin.ui.dto.GetDailyRegisterUserResponseDto;

public interface UserStatsQueryRepository {

    List<GetDailyRegisterUserResponseDto> getDailyRegisterUserStats(int beforeDays);

}
