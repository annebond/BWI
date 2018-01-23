package LendItemManager;

public class LendItemArrayList {
	int INITIAL_SIZE = 20;
	boolean resizeable = false;
	public LendItem[] lendItems = new LendItem[INITIAL_SIZE];
	int next = 0;
	
}
