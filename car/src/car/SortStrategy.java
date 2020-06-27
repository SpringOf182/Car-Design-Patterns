package car;

public abstract class SortStrategy {
	StringBuilder sqlStatement;
	public abstract StringBuilder sortOrder() ;
}
