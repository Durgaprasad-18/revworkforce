package com.revature.revworkforce.app;
import com.revature.revworkforce.controller.MainController;

public class AppRunner {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
            // Call existing MainController main method
            MainController.main(args);

        } catch (Exception e) {
            System.out.println("Application failed to start.");
            System.out.println("Error: " + e.getMessage());
        }

	}

}
