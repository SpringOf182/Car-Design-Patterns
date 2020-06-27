package car;

public class SortByPrice extends SortStrategy{
	
	@Override
	public StringBuilder sortOrder() {
		return sqlStatement.append("SELECT * FROM order_list WHERE state = 0 ORDER BY price DESC");
	}

}
