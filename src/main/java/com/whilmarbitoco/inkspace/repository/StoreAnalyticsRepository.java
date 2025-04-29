package com.whilmarbitoco.inkspace.repository;

import com.whilmarbitoco.inkspace.model.StoreAnalytics;
import com.whilmarbitoco.inkspace.utils.QueryResult;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class StoreAnalyticsRepository extends BaseRepository<StoreAnalytics> {
    public StoreAnalyticsRepository() {
        super(StoreAnalytics.class);
    }

    public List<StoreAnalytics> summary() {
        String query = "SELECT AnalyticsID,Month,StoreID, SUM(Sales) AS Sales FROM storeanalytic GROUP BY Month, StoreID ORDER BY FIELD(Month, 'January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December')";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            QueryResult<StoreAnalytics> analytics = executeQuery(stmt);
            if (analytics.list().isEmpty()) return null;

            return analytics.list();
        } catch (SQLException err) {
            throw new RuntimeException("[Repository] SQL Error:: " + err.getMessage());
        }

    }
}

