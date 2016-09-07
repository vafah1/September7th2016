package localhost.employees.employeeCreate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;


public class EmployeeUpdate {
	
		/**
		 * 
		 * Updates employee via ID #
		 * @author Vafa Husain
		 * @since 2016-09-07
		 *
		 */

		/**
		 * the URL of the API we want to connect to
		 */
		protected static String endpoint = "http://localhost:1337/employee/";
		/**
		 * The character set to use when encoding URL parameters
		 */
		protected static String charset = "UTF-8";


		public static void main(String[] args) {

			Scanner sc = new Scanner(System.in);

			try {

				//employee FN
				System.out.println("Please enter ID # of employee to be updated:");
				String employeeID = sc.nextLine();

				//employee FN
				System.out.println("Please enter employee's First Name:");
				String firstName = sc.nextLine();

				//employee LN
				System.out.println("Please enter employee's Last Name:");
				String lastName = sc.nextLine();

				//employee email address
				System.out.println("Please enter employee's email address:");
				String email = sc.nextLine();

				//employee Home #
				System.out.println("Please enter employee's home phone num:");
				String homePhone =  sc.nextLine();

				//employee cell #
				System.out.println("Please enter employee's cell phone num:");
				String cellPhone = sc.nextLine();
				
				//password
				System.out.println("Please enter a password for the employee:");
				String password = sc.nextLine();

				//current employee
				System.out.println("Is this a currently active employee? (Please enter yes or no):");
				String active = sc.nextLine();

				//if else statement for active value
				if (active.equalsIgnoreCase("yes")) {
					active = "1";
				} else if(active.equalsIgnoreCase("no")) {
					active = "0";
				}



				//creates the URL parameters as a string encoding them with the defined charset
				String queryString = String.format("%s?firstName=%s&lastName=%s&email=%s&homePhone=%s&cellPhone=%s&password=%s&active=%s" ,
						URLEncoder.encode(employeeID, charset),
						URLEncoder.encode(firstName, charset),
						URLEncoder.encode(lastName, charset),
						URLEncoder.encode(email, charset),
						URLEncoder.encode(homePhone, charset),
						URLEncoder.encode(cellPhone, charset),
						URLEncoder.encode(password, charset),
						URLEncoder.encode(active, charset)
						);

				//creates a new URL out of the endpoint, returnType, and queryString
				URL googleDirections= new URL(endpoint + queryString);
				HttpURLConnection connection = (HttpURLConnection) googleDirections.openConnection();
				connection.setRequestMethod("PUT");

				//if we do not get a 200 (success) throw an exception
				if (connection.getResponseCode() != 200) {
					throw new RuntimeException("Failed : HTTP error code:" + connection.getResponseCode());
				}

				//read response into buffer
				BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));

				//loop of buffer line by line until it return null, meaning there are no more lines

				while(br.readLine() != null){
					//print each line to the screen
					System.out.println(br.readLine());
				}

				//close connection to API
				connection.disconnect();

			} catch (MalformedURLException e) {
				e.printStackTrace();

			}catch (IOException e) {
				e.printStackTrace();
			}

		}

	}


