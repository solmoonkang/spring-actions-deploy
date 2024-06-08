package com.springactionsdeploy.api.dto.page;

import java.util.List;

public record PageResponse<T>(
	List<T> pages,
	PageInfo pageInfo
) {
}
