package com.stocks.constants;

public class Queries
{
	public static String LOAD_DATA_FILE="LOAD DATA LOCAL INFILE '@fileName' INTO TABLE "+DatabaseConstants.SCHEMA+".stocks"
										+ " FIELDS TERMINATED BY ','"
										+ " ENCLOSED BY ''"
										+ " LINES TERMINATED BY '\n'"
										+ " IGNORE 1 LINES"
										+ " (quarter,stock,date,open,high,low,close,volume,percent_change_price,"
										+ "percent_change_volume_over_last_wk,previous_weeks_volume,next_weeks_open,"
										+ "next_weeks_close,percent_change_next_weeks_price,days_to_next_dividend,percent_return_next_dividend)";
}
