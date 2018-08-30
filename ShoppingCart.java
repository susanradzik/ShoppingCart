import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class ShoppingCart {

	private static int priceApple = 60;	// quick google indicates 100 pence in a pound
	private static int priceOrange = 25;
	static final String britishPound = "\u00A3"; // another quick google to get the pound symbol
	
	
	private static void calculateAndDisplayCost(int iApples, int iOranges) {
		int iTotal = (iApples * priceApple) + iOranges * priceOrange;
		System.out.printf("The total for your order is: %s%d.%02d",britishPound,iTotal / 100, iTotal % 100);		
	}
	
	private static void countItemsCalculateCosts(String[] allItems) {
		int iApples = 0;
		int iOranges = 0;
		int iSize = allItems.length;
		
		if (iSize > 0) {
			for (int i = 0; i < iSize; ++i) {
				if ("Apple".compareToIgnoreCase(allItems[i]) == 0) {
					++iApples;
				}
				else if ("Orange".compareToIgnoreCase(allItems[i]) == 0){
					++iOranges;
				}
			}
		}
		
		calculateAndDisplayCost(iApples, iOranges);
	}
	
	public static void main(String[] args) {
		System.out.println("ShoppingCart: The only valid options are \"Apple\" and \"Orange\".");
		System.out.println("The test is not case sensitive and invalid items are simply not counted.");
		System.out.println("Please enter all your shopping cart items separated by a space.");

		String stItems = "There was an error";
		boolean isError = false;
		
		//Enter data using BufferReader so program can be run from eclipse IDE
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
       	try {
			stItems = reader.readLine();
		} catch (IOException e) {
			isError = true;
			e.printStackTrace();
		}
       	
       	if (!isError) {
       		System.out.println("Your cart contains: " + stItems);
       		countItemsCalculateCosts(stItems.split(" "));
       	}
	}

}
