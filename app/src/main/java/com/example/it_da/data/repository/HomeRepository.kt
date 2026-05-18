package com.example.it_da.data.repository

import com.example.it_da.domain.model.HomeDashboard

interface HomeRepository {
    // Loads the home dashboard data that will later come from the server.
    suspend fun getHomeDashboard(): Result<HomeDashboard>
}
