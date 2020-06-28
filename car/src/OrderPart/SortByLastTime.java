

public class SortByLastTime implements SortStrategy{

	public StringBuilder sortOrder() {
		StringBuilder sqlStatement = new StringBuilder();
		return sqlStatement.append("SELECT * FROM order_list WHERE state = 0 ORDER BY latestTime");
	}

}
