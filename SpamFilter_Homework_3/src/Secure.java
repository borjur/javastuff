//Boris Jurosevic
//CS 1410
//Homework 3 Spam filter

import java.util.Scanner;

public class Secure {

	public static void main(String args[]) {

		Scanner Secure = new Scanner(System.in);
		String user, pass, user2, pass2;
		System.out.println("Enter you new Username please: ");
		user = Secure.nextLine();
		System.out.println("Enter you new Password please: ");
		pass = Secure.nextLine();
		System.out
				.println("Thank you for submitting! Your account has been registered successfully!");
		System.out
				.println("For spam protection related purposes please type the following into the box: 1234578907");
		Scanner Spam = new Scanner(System.in);
		int spam = 1234578907;
		spam = Spam.nextInt();

		if (spam == 1234578907) {

			System.out.println("Successfully passed verification!");

		} else {

			System.out
					.println("Sorry... You have failed the verification process. Please try again.");

			System.out.println("Please enter your login details to log in.");
			System.out.println("Enter you Username please: ");
			user2 = Secure.nextLine();
			if (user.equals(user2)) {

				System.out
						.println("Your Username has been successfully confirmed.");

			} else {

				System.out.println("Sorry, but your Username was incorrect!");

			}

			System.out.println("Enter your Password please: ");
			pass2 = Secure.nextLine();
			if (pass2 == pass) {

				System.out.print("You have been logged in successfully! ");

			} else {

				System.out.println("Sorry, but your password is incorrect!");

			}

			System.out.println("Thank you for your time!");
		}

	}

}