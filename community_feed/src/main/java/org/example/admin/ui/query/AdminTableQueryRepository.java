package org.example.admin.ui.query;

import org.example.admin.ui.dto.GetTableListResponse;
import org.example.admin.ui.dto.posts.GetPostTableRequestDto;
import org.example.admin.ui.dto.posts.GetPostTableResponseDto;
import org.example.admin.ui.dto.users.GetUserTableRequestDto;
import org.example.admin.ui.dto.users.GetUserTableResponseDto;

public interface AdminTableQueryRepository {
    GetTableListResponse<GetUserTableResponseDto> getUserTableData(GetUserTableRequestDto dto);
    GetTableListResponse<GetPostTableResponseDto> getPostTableData(GetPostTableRequestDto dto);
}
