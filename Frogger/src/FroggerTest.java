public class FroggerTest {

	public static void main(String[] args) {

		int count = 0;
		
		new SplashScreen("images/frogjumping.gif");

		while (count != 100){
			
			count = SplashScreen.done();
			System.out.print("");
			
		}
		
		new GameMenu(); 
	
	}
}
