public interface InventoryManager {

	void viewInventory();

	/**
	 * 
	 * @param medicationName
	 */
	void requestReplenishment(Medicine medicationName);

	/**
	 * 
	 * @param medicationName
	 */
	//void approveReplenishment(Medicine medicationName);

}