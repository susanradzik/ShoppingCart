import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class ShoppingCart {

	private static int priceApple = 60;	// quick google indicates 100 pence in a pound
	private static int priceOrange = 25;
	private static int priceBanana = 20;
	static final String britishPound = "\u00A3"; // another quick google to get the pound symbol
	
	/**< calculate discounted apples/oranges where 
	 * apples are buy one get one and oranges are 3 oranges for the price of 2
	 * BUT we round up so 3 apples are the price of 2 apples (only 1 free)
	 * AND 5 oranges are the price of 4 (first 3 are the price of 2 and the other 2 are full price */
	private static void calculateAndDisplayCostWithDiscounts(int iApplesTotal, int iOrangesTotal) {
		int iApples = (iApplesTotal / 2) + (iApplesTotal % 2);
		int iOranges = 2*(iOrangesTotal / 3) + (iOrangesTotal % 3);
		int iTotal = (iApples * priceApple) + iOranges * priceOrange;
		System.out.printf("The total for your order with discounts is: %s%d.%02d",britishPound,iTotal / 100, iTotal % 100);		
	}

	/**< this routine calculates the checkout total of oranges and apples without a discount */
	private static void calculateAndDisplayCost(int iApples, int iOranges) {
		int iTotal = (iApples * priceApple) + iOranges * priceOrange;
		System.out.printf("The total for your order is: %s%d.%02d",britishPound,iTotal / 100, iTotal % 100);		
	}
	
	private static void countItemsCalculateCosts(String[] allItems,boolean useDiscounts) {
		int iApples = 0;
		int iOranges = 0;
		int iBananas = 0;
		int iSize = allItems.length;
		
		if (iSize > 0) {
			for (int i = 0; i < iSize; ++i) {
				if ("Apple".compareToIgnoreCase(allItems[i]) == 0) {
					++iApples;
				}
				else if ("Orange".compareToIgnoreCase(allItems[i]) == 0){
					++iOranges;
				}
				else if ("Banana".compareToIgnoreCase(allItems[i]) == 0) {
					++iBananas;
				}
			}
		}
		if (useDiscounts) {
			calculateAndDisplayCostWithDiscounts(iApples, iOranges);
		}
		else {
			calculateAndDisplayCost(iApples, iOranges);
		}
	}
	
	public static void main(String[] args) {
		System.out.println("ShoppingCart: The only valid options are \"Apple\" and \"Orange\".");
		System.out.println("The test is not case sensitive and invalid items are simply not counted.");
		System.out.println("Please enter all your shopping cart items separated by a space.");

		String stItems = "There was an error";
		boolean isError = false;
		boolean useDiscounts = true;
		
		//Enter data using BufferReader so program can be run and tested from Eclipse IDE
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
       	try {
			stItems = reader.readLine();
		} catch (IOException e) {
			isError = true;
			e.printStackTrace();
		}
       	
       	if (!isError) {
       		System.out.println("Your cart contains: " + stItems);
       		//countItemsCalculateCosts(stItems.split(" "),useDiscounts);       		
       		countItemsCalculateCosts2(stItems.split(" "),useDiscounts);
       	}
	}

	private static void countItemsCalculateCosts2(String[] split, boolean useDiscounts) {
		int iApples = 0;
		int iOranges = 0;
		int iBananas = 0;;
		int iSize = split.length;
		String priorFruit = "";
		
		if (iSize > 0) {
			for (int i = 0; i < iSize; i++) {
				if ("Orange".compareToIgnoreCase(split[i]) == 0){
					++iOranges;
					continue;
					
				}				
				if (priorFruit.isEmpty()) {
					priorFruit = split[i];
					if (i != iSize - 1)
						continue;	// where the last item in the list, let it do the compare below
				}
				boolean isPriorFruitBanana = priorFruit.equalsIgnoreCase("Banana");
				boolean isCurrentFruitBanana = split[i].equalsIgnoreCase("Banana");
				if (isPriorFruitBanana || isCurrentFruitBanana) 
					++iBananas;
				else
					++iApples;
				priorFruit = "";
			}
		}
		calculateAndDisplayCostsDiscountOranges(iBananas,iApples,iOranges);

	}

	private static void calculateAndDisplayCostsDiscountOranges(int iBananas, int iApples, int iOrangesTotal) {
		int iOranges = 2*(iOrangesTotal / 3) + (iOrangesTotal % 3);
		int iTotal = (iBananas * priceBanana) + (iApples * priceApple) + iOranges * priceOrange;
		System.out.printf("The total for your order with discounts is: %s%d.%02d",britishPound,iTotal / 100, iTotal % 100);		
		
	}
}
