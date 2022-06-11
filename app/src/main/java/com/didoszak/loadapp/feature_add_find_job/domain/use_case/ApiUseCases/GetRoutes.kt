package com.didoszak.loadapp.feature_add_find_job.domain.use_case.ApiUseCases

import com.didoszak.loadapp.feature_add_find_job.data.model.Qualification
import com.didoszak.loadapp.feature_add_find_job.data.model.Route
import com.didoszak.loadapp.feature_add_find_job.domain.repository.ApiRepository
import com.didoszak.loadapp.feature_add_find_job.domain.util.OrderType
import com.didoszak.loadapp.feature_add_find_job.domain.util.Resource
import com.didoszak.loadapp.feature_add_find_job.domain.util.RouteOrder
import java.util.*

class GetRoutes(
    private val repository: ApiRepository
) {
    operator fun invoke(
        routeOrder: RouteOrder = RouteOrder.Date(OrderType.Descending)
        ): List<Route> {
        return listOf(
            Route(
                id = 0,
                start = Date(2022, 5, 6),
                finish = Date(2022, 5, 12),
                description = "First job \n" +
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
                gross_pay = 2400.0,
                organizationId = 0,
                organizationName = "C.B.T. inc.",
                truckName = "volvo",
                city = "Cracow"
            ),
            Route(
                id = 1,
                start = Date(2022, 5, 6),
                finish = Date(2022, 6, 12),
                description = "Second job \n" +
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
                gross_pay = 2700.0,
                organizationId = 1,
                organizationName = "Punjabi IND",
                truckName = "shittake",
                city = "Radom"
            ),
            Route(
                id = 2,
                start = Date(2022, 5, 6),
                finish = Date(2022, 5, 12),
                description = "Third job \n" +
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
                gross_pay = 1000.0,
                organizationId = 0,
                organizationName = "C.B.T. inc.",
                truckName = "volvo",
                city = "Cracow"
            ),
            Route(
                id = 3,
                start = Date(2022, 5, 6),
                finish = Date(2022, 5, 12),
                description = "Fourth job \n" +
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
                gross_pay = 2800.0,
                organizationId = 1,
                organizationName = "Punjabi IND",
                truckName = "volvo",
                city = "Cracow"
            ),
            Route(
                id = 4,
                start = Date(2022, 5, 6),
                finish = Date(2022, 5, 12),
                description = "Fifth job \n" +
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
                gross_pay = 2900.0,
                organizationId = 2,
                organizationName = "Inpost",
                truckName = "car",
                city = "Warsaw"
            ),
            Route(
                id = 5,
                start = Date(2022, 5, 6),
                finish = Date(2022, 5, 12),
                description = "Sixth job \n" +
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
                gross_pay = 2000.0,
                organizationId = 0,
                organizationName = "C.B.T. inc.",
                truckName = "volvo",
                city = "Cracow"
            ),
        )
    }
}